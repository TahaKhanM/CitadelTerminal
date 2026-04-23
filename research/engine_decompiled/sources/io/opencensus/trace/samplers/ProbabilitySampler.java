/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.samplers;

import com.google.common.base.Preconditions;
import io.opencensus.trace.Sampler;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.TraceId;
import io.opencensus.trace.samplers.AutoValue_ProbabilitySampler;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
abstract class ProbabilitySampler
extends Sampler {
    ProbabilitySampler() {
    }

    abstract double getProbability();

    abstract long getIdUpperBound();

    static ProbabilitySampler create(double probability) {
        Preconditions.checkArgument(probability >= 0.0 && probability <= 1.0, "probability must be in range [0.0, 1.0]");
        long idUpperBound = probability == 0.0 ? Long.MIN_VALUE : (probability == 1.0 ? Long.MAX_VALUE : (long)(probability * 9.223372036854776E18));
        return new AutoValue_ProbabilitySampler(probability, idUpperBound);
    }

    @Override
    public final boolean shouldSample(@Nullable SpanContext parentContext, @Nullable Boolean hasRemoteParent, TraceId traceId, SpanId spanId, String name, @Nullable List<Span> parentLinks) {
        if (parentContext != null && parentContext.getTraceOptions().isSampled()) {
            return true;
        }
        if (parentLinks != null) {
            for (Span parentLink : parentLinks) {
                if (!parentLink.getContext().getTraceOptions().isSampled()) continue;
                return true;
            }
        }
        return Math.abs(traceId.getLowerLong()) < this.getIdUpperBound();
    }

    @Override
    public final String getDescription() {
        return String.format("ProbabilitySampler{%.6f}", this.getProbability());
    }
}

