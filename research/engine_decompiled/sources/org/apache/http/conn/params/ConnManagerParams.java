/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.conn.params;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

@Deprecated
@Contract(threading=ThreadingBehavior.IMMUTABLE)
public final class ConnManagerParams
implements ConnManagerPNames {
    public static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 20;
    private static final ConnPerRoute DEFAULT_CONN_PER_ROUTE = new ConnPerRoute(){

        @Override
        public int getMaxForRoute(HttpRoute route) {
            return 2;
        }
    };

    @Deprecated
    public static long getTimeout(HttpParams params2) {
        Args.notNull(params2, "HTTP parameters");
        return params2.getLongParameter("http.conn-manager.timeout", 0L);
    }

    @Deprecated
    public static void setTimeout(HttpParams params2, long timeout) {
        Args.notNull(params2, "HTTP parameters");
        params2.setLongParameter("http.conn-manager.timeout", timeout);
    }

    public static void setMaxConnectionsPerRoute(HttpParams params2, ConnPerRoute connPerRoute) {
        Args.notNull(params2, "HTTP parameters");
        params2.setParameter("http.conn-manager.max-per-route", connPerRoute);
    }

    public static ConnPerRoute getMaxConnectionsPerRoute(HttpParams params2) {
        Args.notNull(params2, "HTTP parameters");
        ConnPerRoute connPerRoute = (ConnPerRoute)params2.getParameter("http.conn-manager.max-per-route");
        if (connPerRoute == null) {
            connPerRoute = DEFAULT_CONN_PER_ROUTE;
        }
        return connPerRoute;
    }

    public static void setMaxTotalConnections(HttpParams params2, int maxTotalConnections) {
        Args.notNull(params2, "HTTP parameters");
        params2.setIntParameter("http.conn-manager.max-total", maxTotalConnections);
    }

    public static int getMaxTotalConnections(HttpParams params2) {
        Args.notNull(params2, "HTTP parameters");
        return params2.getIntParameter("http.conn-manager.max-total", 20);
    }
}

