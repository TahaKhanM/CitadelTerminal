/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.client.params;

import java.net.InetAddress;
import java.util.Collection;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.params.HttpParams;

@Deprecated
public final class HttpClientParamConfig {
    private HttpClientParamConfig() {
    }

    public static RequestConfig getRequestConfig(HttpParams params2) {
        return HttpClientParamConfig.getRequestConfig(params2, RequestConfig.DEFAULT);
    }

    public static RequestConfig getRequestConfig(HttpParams params2, RequestConfig defaultConfig) {
        String cookiePolicy;
        Collection proxySuthPrefs;
        Collection targetAuthPrefs;
        InetAddress localAddress;
        RequestConfig.Builder builder = RequestConfig.copy(defaultConfig).setSocketTimeout(params2.getIntParameter("http.socket.timeout", defaultConfig.getSocketTimeout())).setStaleConnectionCheckEnabled(params2.getBooleanParameter("http.connection.stalecheck", defaultConfig.isStaleConnectionCheckEnabled())).setConnectTimeout(params2.getIntParameter("http.connection.timeout", defaultConfig.getConnectTimeout())).setExpectContinueEnabled(params2.getBooleanParameter("http.protocol.expect-continue", defaultConfig.isExpectContinueEnabled())).setAuthenticationEnabled(params2.getBooleanParameter("http.protocol.handle-authentication", defaultConfig.isAuthenticationEnabled())).setCircularRedirectsAllowed(params2.getBooleanParameter("http.protocol.allow-circular-redirects", defaultConfig.isCircularRedirectsAllowed())).setConnectionRequestTimeout((int)params2.getLongParameter("http.conn-manager.timeout", defaultConfig.getConnectionRequestTimeout())).setMaxRedirects(params2.getIntParameter("http.protocol.max-redirects", defaultConfig.getMaxRedirects())).setRedirectsEnabled(params2.getBooleanParameter("http.protocol.handle-redirects", defaultConfig.isRedirectsEnabled())).setRelativeRedirectsAllowed(!params2.getBooleanParameter("http.protocol.reject-relative-redirect", !defaultConfig.isRelativeRedirectsAllowed()));
        HttpHost proxy = (HttpHost)params2.getParameter("http.route.default-proxy");
        if (proxy != null) {
            builder.setProxy(proxy);
        }
        if ((localAddress = (InetAddress)params2.getParameter("http.route.local-address")) != null) {
            builder.setLocalAddress(localAddress);
        }
        if ((targetAuthPrefs = (Collection)params2.getParameter("http.auth.target-scheme-pref")) != null) {
            builder.setTargetPreferredAuthSchemes(targetAuthPrefs);
        }
        if ((proxySuthPrefs = (Collection)params2.getParameter("http.auth.proxy-scheme-pref")) != null) {
            builder.setProxyPreferredAuthSchemes(proxySuthPrefs);
        }
        if ((cookiePolicy = (String)params2.getParameter("http.protocol.cookie-policy")) != null) {
            builder.setCookieSpec(cookiePolicy);
        }
        return builder.build();
    }
}

