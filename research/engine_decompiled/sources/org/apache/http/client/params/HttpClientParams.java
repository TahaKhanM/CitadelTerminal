/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.client.params;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

@Deprecated
public class HttpClientParams {
    private HttpClientParams() {
    }

    public static boolean isRedirecting(HttpParams params2) {
        Args.notNull(params2, "HTTP parameters");
        return params2.getBooleanParameter("http.protocol.handle-redirects", true);
    }

    public static void setRedirecting(HttpParams params2, boolean value) {
        Args.notNull(params2, "HTTP parameters");
        params2.setBooleanParameter("http.protocol.handle-redirects", value);
    }

    public static boolean isAuthenticating(HttpParams params2) {
        Args.notNull(params2, "HTTP parameters");
        return params2.getBooleanParameter("http.protocol.handle-authentication", true);
    }

    public static void setAuthenticating(HttpParams params2, boolean value) {
        Args.notNull(params2, "HTTP parameters");
        params2.setBooleanParameter("http.protocol.handle-authentication", value);
    }

    public static String getCookiePolicy(HttpParams params2) {
        Args.notNull(params2, "HTTP parameters");
        String cookiePolicy = (String)params2.getParameter("http.protocol.cookie-policy");
        if (cookiePolicy == null) {
            return "best-match";
        }
        return cookiePolicy;
    }

    public static void setCookiePolicy(HttpParams params2, String cookiePolicy) {
        Args.notNull(params2, "HTTP parameters");
        params2.setParameter("http.protocol.cookie-policy", cookiePolicy);
    }

    public static void setConnectionManagerTimeout(HttpParams params2, long timeout) {
        Args.notNull(params2, "HTTP parameters");
        params2.setLongParameter("http.conn-manager.timeout", timeout);
    }

    public static long getConnectionManagerTimeout(HttpParams params2) {
        Args.notNull(params2, "HTTP parameters");
        Long timeout = (Long)params2.getParameter("http.conn-manager.timeout");
        if (timeout != null) {
            return timeout;
        }
        return HttpConnectionParams.getConnectionTimeout(params2);
    }
}

