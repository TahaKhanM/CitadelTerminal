/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.base.Preconditions;

public final class MoreThrowables {
    public static void throwIfUnchecked(Throwable t) {
        Preconditions.checkNotNull(t);
        if (t instanceof RuntimeException) {
            throw (RuntimeException)t;
        }
        if (t instanceof Error) {
            throw (Error)t;
        }
    }

    private MoreThrowables() {
    }
}

