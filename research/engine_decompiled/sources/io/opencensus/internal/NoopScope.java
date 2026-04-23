/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.internal;

import io.opencensus.common.Scope;

public final class NoopScope
implements Scope {
    private static final Scope INSTANCE = new NoopScope();

    private NoopScope() {
    }

    public static Scope getInstance() {
        return INSTANCE;
    }

    @Override
    public void close() {
    }
}

