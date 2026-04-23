/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.unsafe;

import io.grpc.Context;
import io.opencensus.trace.Span;

public final class ContextUtils {
    public static final Context.Key<Span> CONTEXT_SPAN_KEY = Context.key("opencensus-trace-span-key");

    private ContextUtils() {
    }
}

