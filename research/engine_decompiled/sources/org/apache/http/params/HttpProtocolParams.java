/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.params;

import java.nio.charset.CodingErrorAction;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;

@Deprecated
public final class HttpProtocolParams
implements CoreProtocolPNames {
    private HttpProtocolParams() {
    }

    public static String getHttpElementCharset(HttpParams params2) {
        Args.notNull(params2, "HTTP parameters");
        String charset = (String)params2.getParameter("http.protocol.element-charset");
        if (charset == null) {
            charset = HTTP.DEF_PROTOCOL_CHARSET.name();
        }
        return charset;
    }

    public static void setHttpElementCharset(HttpParams params2, String charset) {
        Args.notNull(params2, "HTTP parameters");
        params2.setParameter("http.protocol.element-charset", charset);
    }

    public static String getContentCharset(HttpParams params2) {
        Args.notNull(params2, "HTTP parameters");
        String charset = (String)params2.getParameter("http.protocol.content-charset");
        if (charset == null) {
            charset = HTTP.DEF_CONTENT_CHARSET.name();
        }
        return charset;
    }

    public static void setContentCharset(HttpParams params2, String charset) {
        Args.notNull(params2, "HTTP parameters");
        params2.setParameter("http.protocol.content-charset", charset);
    }

    public static ProtocolVersion getVersion(HttpParams params2) {
        Args.notNull(params2, "HTTP parameters");
        Object param2 = params2.getParameter("http.protocol.version");
        if (param2 == null) {
            return HttpVersion.HTTP_1_1;
        }
        return (ProtocolVersion)param2;
    }

    public static void setVersion(HttpParams params2, ProtocolVersion version) {
        Args.notNull(params2, "HTTP parameters");
        params2.setParameter("http.protocol.version", version);
    }

    public static String getUserAgent(HttpParams params2) {
        Args.notNull(params2, "HTTP parameters");
        return (String)params2.getParameter("http.useragent");
    }

    public static void setUserAgent(HttpParams params2, String useragent) {
        Args.notNull(params2, "HTTP parameters");
        params2.setParameter("http.useragent", useragent);
    }

    public static boolean useExpectContinue(HttpParams params2) {
        Args.notNull(params2, "HTTP parameters");
        return params2.getBooleanParameter("http.protocol.expect-continue", false);
    }

    public static void setUseExpectContinue(HttpParams params2, boolean b) {
        Args.notNull(params2, "HTTP parameters");
        params2.setBooleanParameter("http.protocol.expect-continue", b);
    }

    public static CodingErrorAction getMalformedInputAction(HttpParams params2) {
        Args.notNull(params2, "HTTP parameters");
        Object param2 = params2.getParameter("http.malformed.input.action");
        if (param2 == null) {
            return CodingErrorAction.REPORT;
        }
        return (CodingErrorAction)param2;
    }

    public static void setMalformedInputAction(HttpParams params2, CodingErrorAction action) {
        Args.notNull(params2, "HTTP parameters");
        params2.setParameter("http.malformed.input.action", action);
    }

    public static CodingErrorAction getUnmappableInputAction(HttpParams params2) {
        Args.notNull(params2, "HTTP parameters");
        Object param2 = params2.getParameter("http.unmappable.input.action");
        if (param2 == null) {
            return CodingErrorAction.REPORT;
        }
        return (CodingErrorAction)param2;
    }

    public static void setUnmappableInputAction(HttpParams params2, CodingErrorAction action) {
        Args.notNull(params2, "HTTP parameters");
        params2.setParameter("http.unmappable.input.action", action);
    }
}

