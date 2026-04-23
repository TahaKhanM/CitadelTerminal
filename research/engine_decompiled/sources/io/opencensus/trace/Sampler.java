/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import io.opencensus.trace.Span;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.TraceId;
import java.util.List;
import javax.annotation.Nullable;

public abstract class Sampler {
    public abstract boolean shouldSample(@Nullable SpanContext var1, @Nullable Boolean var2, TraceId var3, SpanId var4, String var5, List<Span> var6);

    public abstract String getDescription();
}

