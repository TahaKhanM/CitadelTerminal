/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.impl.client;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.impl.client.DefaultRedirectStrategy;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class LaxRedirectStrategy
extends DefaultRedirectStrategy {
    public static final LaxRedirectStrategy INSTANCE = new LaxRedirectStrategy();
    private static final String[] REDIRECT_METHODS = new String[]{"GET", "POST", "HEAD", "DELETE"};

    @Override
    protected boolean isRedirectable(String method) {
        for (String m : REDIRECT_METHODS) {
            if (!m.equalsIgnoreCase(method)) continue;
            return true;
        }
        return false;
    }
}

