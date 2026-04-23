/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.samplers;

import io.opencensus.trace.Sampler;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.TraceId;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AlwaysSampleSampler
extends Sampler {
    AlwaysSampleSampler() {
    }

    @Override
    public boolean shouldSample(@Nullable SpanContext parentContext, @Nullable Boolean hasRemoteParent, TraceId traceId, SpanId spanId, String name, List<Span> parentLinks) {
        return true;
    }

    @Override
    public String getDescription() {
        return this.toString();
    }

    public String toString() {
        return "AlwaysSampleSampler";
    }
}

