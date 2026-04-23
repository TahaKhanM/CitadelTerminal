/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.conn.params;

import java.net.InetAddress;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

@Deprecated
@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class ConnRouteParams
implements ConnRoutePNames {
    public static final HttpHost NO_HOST = new HttpHost("127.0.0.255", 0, "no-host");
    public static final HttpRoute NO_ROUTE = new HttpRoute(NO_HOST);

    private ConnRouteParams() {
    }

    public static HttpHost getDefaultProxy(HttpParams params2) {
        Args.notNull(params2, "Parameters");
        HttpHost proxy = (HttpHost)params2.getParameter("http.route.default-proxy");
        if (proxy != null && NO_HOST.equals(proxy)) {
            proxy = null;
        }
        return proxy;
    }

    public static void setDefaultProxy(HttpParams params2, HttpHost proxy) {
        Args.notNull(params2, "Parameters");
        params2.setParameter("http.route.default-proxy", proxy);
    }

    public static HttpRoute getForcedRoute(HttpParams params2) {
        Args.notNull(params2, "Parameters");
        HttpRoute route = (HttpRoute)params2.getParameter("http.route.forced-route");
        if (route != null && NO_ROUTE.equals(route)) {
            route = null;
        }
        return route;
    }

    public static void setForcedRoute(HttpParams params2, HttpRoute route) {
        Args.notNull(params2, "Parameters");
        params2.setParameter("http.route.forced-route", route);
    }

    public static InetAddress getLocalAddress(HttpParams params2) {
        Args.notNull(params2, "Parameters");
        InetAddress local = (InetAddress)params2.getParameter("http.route.local-address");
        return local;
    }

    public static void setLocalAddress(HttpParams params2, InetAddress local) {
        Args.notNull(params2, "Parameters");
        params2.setParameter("http.route.local-address", local);
    }
}

