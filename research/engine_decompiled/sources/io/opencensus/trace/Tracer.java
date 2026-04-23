/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.MustBeClosed;
import io.opencensus.common.Scope;
import io.opencensus.trace.BlankSpan;
import io.opencensus.trace.CurrentSpanUtils;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanBuilder;
import io.opencensus.trace.SpanContext;
import java.util.concurrent.Callable;
import javax.annotation.Nullable;

public abstract class Tracer {
    private static final NoopTracer noopTracer = new NoopTracer();

    static Tracer getNoopTracer() {
        return noopTracer;
    }

    public final Span getCurrentSpan() {
        Span currentSpan = CurrentSpanUtils.getCurrentSpan();
        return currentSpan != null ? currentSpan : BlankSpan.INSTANCE;
    }

    @MustBeClosed
    public final Scope withSpan(Span span2) {
        return CurrentSpanUtils.withSpan(Preconditions.checkNotNull(span2, "span"), false);
    }

    public final Runnable withSpan(Span span2, Runnable runnable) {
        return CurrentSpanUtils.withSpan(span2, false, runnable);
    }

    public final <C> Callable<C> withSpan(Span span2, Callable<C> callable) {
        return CurrentSpanUtils.withSpan(span2, false, callable);
    }

    public final SpanBuilder spanBuilder(String spanName) {
        return this.spanBuilderWithExplicitParent(spanName, CurrentSpanUtils.getCurrentSpan());
    }

    public abstract SpanBuilder spanBuilderWithExplicitParent(String var1, @Nullable Span var2);

    public abstract SpanBuilder spanBuilderWithRemoteParent(String var1, @Nullable SpanContext var2);

    protected Tracer() {
    }

    private static final class NoopTracer
    extends Tracer {
        @Override
        public SpanBuilder spanBuilderWithExplicitParent(String spanName, @Nullable Span parent) {
            return SpanBuilder.NoopSpanBuilder.createWithParent(spanName, parent);
        }

        @Override
        public SpanBuilder spanBuilderWithRemoteParent(String spanName, @Nullable SpanContext remoteParentSpanContext) {
            return SpanBuilder.NoopSpanBuilder.createWithRemoteParent(spanName, remoteParentSpanContext);
        }

        private NoopTracer() {
        }
    }
}

