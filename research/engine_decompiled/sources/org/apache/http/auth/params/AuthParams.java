/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.auth.params;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;

@Deprecated
@Contract(threading=ThreadingBehavior.IMMUTABLE)
public final class AuthParams {
    private AuthParams() {
    }

    public static String getCredentialCharset(HttpParams params2) {
        Args.notNull(params2, "HTTP parameters");
        String charset = (String)params2.getParameter("http.auth.credential-charset");
        if (charset == null) {
            charset = HTTP.DEF_PROTOCOL_CHARSET.name();
        }
        return charset;
    }

    public static void setCredentialCharset(HttpParams params2, String charset) {
        Args.notNull(params2, "HTTP parameters");
        params2.setParameter("http.auth.credential-charset", charset);
    }
}

