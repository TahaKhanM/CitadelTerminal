/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.params;

import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.SocketConfig;
import org.apache.http.params.HttpParams;

@Deprecated
public final class HttpParamConfig {
    private HttpParamConfig() {
    }

    public static SocketConfig getSocketConfig(HttpParams params2) {
        return SocketConfig.custom().setSoTimeout(params2.getIntParameter("http.socket.timeout", 0)).setSoReuseAddress(params2.getBooleanParameter("http.socket.reuseaddr", false)).setSoKeepAlive(params2.getBooleanParameter("http.socket.keepalive", false)).setSoLinger(params2.getIntParameter("http.socket.linger", -1)).setTcpNoDelay(params2.getBooleanParameter("http.tcp.nodelay", true)).build();
    }

    public static MessageConstraints getMessageConstraints(HttpParams params2) {
        return MessageConstraints.custom().setMaxHeaderCount(params2.getIntParameter("http.connection.max-header-count", -1)).setMaxLineLength(params2.getIntParameter("http.connection.max-line-length", -1)).build();
    }

    public static ConnectionConfig getConnectionConfig(HttpParams params2) {
        MessageConstraints messageConstraints = HttpParamConfig.getMessageConstraints(params2);
        String csname = (String)params2.getParameter("http.protocol.element-charset");
        return ConnectionConfig.custom().setCharset(csname != null ? Charset.forName(csname) : null).setMalformedInputAction((CodingErrorAction)params2.getParameter("http.malformed.input.action")).setMalformedInputAction((CodingErrorAction)params2.getParameter("http.unmappable.input.action")).setMessageConstraints(messageConstraints).build();
    }
}

