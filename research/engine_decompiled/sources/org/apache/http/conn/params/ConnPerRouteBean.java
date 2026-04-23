/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.conn.params;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.util.Args;

@Deprecated
@Contract(threading=ThreadingBehavior.SAFE)
public final class ConnPerRouteBean
implements ConnPerRoute {
    public static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 2;
    private final ConcurrentHashMap<HttpRoute, Integer> maxPerHostMap = new ConcurrentHashMap();
    private volatile int defaultMax;

    public ConnPerRouteBean(int defaultMax) {
        this.setDefaultMaxPerRoute(defaultMax);
    }

    public ConnPerRouteBean() {
        this(2);
    }

    public int getDefaultMax() {
        return this.defaultMax;
    }

    public int getDefaultMaxPerRoute() {
        return this.defaultMax;
    }

    public void setDefaultMaxPerRoute(int max2) {
        Args.positive(max2, "Default max per route");
        this.defaultMax = max2;
    }

    public void setMaxForRoute(HttpRoute route, int max2) {
        Args.notNull(route, "HTTP route");
        Args.positive(max2, "Max per route");
        this.maxPerHostMap.put(route, max2);
    }

    @Override
    public int getMaxForRoute(HttpRoute route) {
        Args.notNull(route, "HTTP route");
        Integer max2 = this.maxPerHostMap.get(route);
        if (max2 != null) {
            return max2;
        }
        return this.defaultMax;
    }

    public void setMaxForRoutes(Map<HttpRoute, Integer> map2) {
        if (map2 == null) {
            return;
        }
        this.maxPerHostMap.clear();
        this.maxPerHostMap.putAll(map2);
    }

    public String toString() {
        return this.maxPerHostMap.toString();
    }
}

