/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.MustBeClosed;
import io.opencensus.common.Scope;
import io.opencensus.trace.BlankSpan;
import io.opencensus.trace.CurrentSpanUtils;
import io.opencensus.trace.Sampler;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanContext;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.Nullable;

public abstract class SpanBuilder {
    public abstract SpanBuilder setSampler(Sampler var1);

    public abstract SpanBuilder setParentLinks(List<Span> var1);

    public abstract SpanBuilder setRecordEvents(boolean var1);

    public abstract Span startSpan();

    @MustBeClosed
    public final Scope startScopedSpan() {
        return CurrentSpanUtils.withSpan(this.startSpan(), true);
    }

    public final void startSpanAndRun(Runnable runnable) {
        Span span2 = this.startSpan();
        CurrentSpanUtils.withSpan(span2, true, runnable).run();
    }

    public final <V> V startSpanAndCall(Callable<V> callable) throws Exception {
        Span span2 = this.startSpan();
        return CurrentSpanUtils.withSpan(span2, true, callable).call();
    }

    static final class NoopSpanBuilder
    extends SpanBuilder {
        static NoopSpanBuilder createWithParent(String spanName, @Nullable Span parent) {
            return new NoopSpanBuilder(spanName);
        }

        static NoopSpanBuilder createWithRemoteParent(String spanName, @Nullable SpanContext remoteParentSpanContext) {
            return new NoopSpanBuilder(spanName);
        }

        @Override
        public Span startSpan() {
            return BlankSpan.INSTANCE;
        }

        @Override
        public SpanBuilder setSampler(@Nullable Sampler sampler) {
            return this;
        }

        @Override
        public SpanBuilder setParentLinks(List<Span> parentLinks) {
            return this;
        }

        @Override
        public SpanBuilder setRecordEvents(boolean recordEvents) {
            return this;
        }

        private NoopSpanBuilder(String name) {
            Preconditions.checkNotNull(name, "name");
        }
    }
}

