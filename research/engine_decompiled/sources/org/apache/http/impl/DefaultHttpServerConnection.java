/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.impl;

import java.io.IOException;
import java.net.Socket;
import org.apache.http.impl.SocketHttpServerConnection;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

@Deprecated
public class DefaultHttpServerConnection
extends SocketHttpServerConnection {
    @Override
    public void bind(Socket socket, HttpParams params2) throws IOException {
        Args.notNull(socket, "Socket");
        Args.notNull(params2, "HTTP parameters");
        this.assertNotOpen();
        socket.setTcpNoDelay(params2.getBooleanParameter("http.tcp.nodelay", true));
        socket.setSoTimeout(params2.getIntParameter("http.socket.timeout", 0));
        socket.setKeepAlive(params2.getBooleanParameter("http.socket.keepalive", false));
        int linger = params2.getIntParameter("http.socket.linger", -1);
        if (linger >= 0) {
            socket.setSoLinger(linger > 0, linger);
        }
        if (linger >= 0) {
            socket.setSoLinger(linger > 0, linger);
        }
        super.bind(socket, params2);
    }
}

