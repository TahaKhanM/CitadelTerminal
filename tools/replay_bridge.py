#!/usr/bin/env python3
"""
replay_bridge.py — tiny local HTTPS sink so the authenticated Chrome tab can
POST fetched replay bodies here, and we save them to replays/ranked/.

Why HTTPS: the Terminal site is HTTPS, so a mixed-content HTTP fetch from the
page would be blocked. We serve over TLS with a self-signed cert auto-
generated on first run. You visit https://127.0.0.1:8765/ping once in Chrome,
click "Advanced → proceed", and after that JS fetches to it work.

Usage:
    python3 tools/replay_bridge.py                 # runs until Ctrl-C
    # then drive Chrome to POST to https://127.0.0.1:8765/save
    # request body: {"filename": "<name>.replay", "body": "<replay text>"}
"""

import datetime as _dt
import ipaddress as _ip
import json
import os
import ssl
import subprocess
import sys
from http.server import BaseHTTPRequestHandler, ThreadingHTTPServer
from pathlib import Path

REPO = Path(__file__).resolve().parent.parent
OUT_DIR = REPO / "replays" / "ranked"
OUT_DIR.mkdir(parents=True, exist_ok=True)

PORT = int(os.environ.get("REPLAY_BRIDGE_PORT", "8765"))
SAVED = []


class Handler(BaseHTTPRequestHandler):
    def _cors(self):
        # Echo the requesting origin so Chrome's Private Network Access
        # preflight check passes. `*` isn't enough when credentials / PNA
        # are in play.
        origin = self.headers.get("Origin", "*")
        self.send_header("Access-Control-Allow-Origin", origin)
        self.send_header("Vary", "Origin")
        self.send_header("Access-Control-Allow-Methods", "POST, GET, OPTIONS")
        self.send_header("Access-Control-Allow-Headers", "Content-Type")
        # Chrome 94+ requires this for HTTPS-to-private-IP fetches.
        self.send_header("Access-Control-Allow-Private-Network", "true")

    def do_OPTIONS(self):
        self.send_response(204)
        self._cors()
        self.end_headers()

    def do_GET(self):
        if self.path == "/ping":
            self.send_response(200)
            self._cors()
            self.send_header("Content-Type", "application/json")
            self.end_headers()
            self.wfile.write(json.dumps({"ok": True, "saved": len(SAVED)}).encode())
            return
        if self.path == "/list":
            self.send_response(200)
            self._cors()
            self.send_header("Content-Type", "application/json")
            self.end_headers()
            self.wfile.write(json.dumps({"saved": SAVED}).encode())
            return
        self.send_response(404)
        self._cors()
        self.end_headers()

    def do_POST(self):
        if self.path != "/save":
            self.send_response(404)
            self._cors()
            self.end_headers()
            return
        length = int(self.headers.get("Content-Length", "0"))
        try:
            payload = json.loads(self.rfile.read(length).decode("utf-8"))
            name = payload["filename"]
            body = payload["body"]
        except Exception as e:
            self.send_response(400)
            self._cors()
            self.end_headers()
            self.wfile.write(str(e).encode())
            return
        # Sanitize filename — no path traversal.
        name = os.path.basename(name)
        if not name.endswith(".replay"):
            name += ".replay"
        dest = OUT_DIR / name
        try:
            with open(dest, "w", encoding="utf-8") as f:
                f.write(body)
            SAVED.append({"path": str(dest), "bytes": len(body.encode())})
        except Exception as e:
            self.send_response(500)
            self._cors()
            self.end_headers()
            self.wfile.write(str(e).encode())
            return
        self.send_response(200)
        self._cors()
        self.send_header("Content-Type", "application/json")
        self.end_headers()
        self.wfile.write(json.dumps({"ok": True, "path": str(dest)}).encode())

    def log_message(self, fmt, *args):
        # Keep console quiet; print only saves.
        if "POST /save" in fmt % args:
            sys.stderr.write("bridge ← %s\n" % (fmt % args))


CERT_DIR = REPO / ".replay_bridge_cert"
CERT_FILE = CERT_DIR / "cert.pem"
KEY_FILE = CERT_DIR / "key.pem"


def ensure_cert():
    """Generate a self-signed cert for localhost if one doesn't already exist."""
    if CERT_FILE.exists() and KEY_FILE.exists():
        return
    CERT_DIR.mkdir(parents=True, exist_ok=True)
    try:
        from cryptography import x509
        from cryptography.hazmat.primitives import hashes, serialization
        from cryptography.hazmat.primitives.asymmetric import rsa
        from cryptography.x509.oid import NameOID
    except ImportError:
        # Fallback: shell out to openssl.
        subprocess.check_call([
            "openssl", "req", "-x509", "-newkey", "rsa:2048", "-nodes", "-days", "365",
            "-keyout", str(KEY_FILE), "-out", str(CERT_FILE),
            "-subj", "/CN=127.0.0.1",
            "-addext", "subjectAltName=IP:127.0.0.1,DNS:localhost",
        ])
        return
    key = rsa.generate_private_key(public_exponent=65537, key_size=2048)
    name = x509.Name([x509.NameAttribute(NameOID.COMMON_NAME, "127.0.0.1")])
    san = x509.SubjectAlternativeName([
        x509.IPAddress(_ip.ip_address("127.0.0.1")),
        x509.DNSName("localhost"),
    ])
    cert = (
        x509.CertificateBuilder()
        .subject_name(name).issuer_name(name).public_key(key.public_key())
        .serial_number(x509.random_serial_number())
        .not_valid_before(_dt.datetime.utcnow())
        .not_valid_after(_dt.datetime.utcnow() + _dt.timedelta(days=365))
        .add_extension(san, critical=False)
        .sign(key, hashes.SHA256())
    )
    with open(KEY_FILE, "wb") as f:
        f.write(key.private_bytes(
            encoding=serialization.Encoding.PEM,
            format=serialization.PrivateFormat.TraditionalOpenSSL,
            encryption_algorithm=serialization.NoEncryption(),
        ))
    with open(CERT_FILE, "wb") as f:
        f.write(cert.public_bytes(serialization.Encoding.PEM))


def main():
    ensure_cert()
    server = ThreadingHTTPServer(("127.0.0.1", PORT), Handler)
    ctx = ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER)
    ctx.load_cert_chain(certfile=str(CERT_FILE), keyfile=str(KEY_FILE))
    server.socket = ctx.wrap_socket(server.socket, server_side=True)
    sys.stderr.write(f"replay_bridge listening on https://127.0.0.1:{PORT}  (out: {OUT_DIR})\n")
    sys.stderr.write("endpoints: GET /ping, GET /list, POST /save\n")
    sys.stderr.write("Private Network Access CORS is enabled — HTTPS pages can fetch this over plain HTTP.\n")
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        sys.stderr.write(f"\nstopped. saved {len(SAVED)} file(s).\n")


if __name__ == "__main__":
    main()
