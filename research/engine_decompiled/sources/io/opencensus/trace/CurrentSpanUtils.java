/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import com.google.common.base.Throwables;
import io.grpc.Context;
import io.opencensus.common.Scope;
import io.opencensus.trace.Span;
import io.opencensus.trace.Status;
import io.opencensus.trace.unsafe.ContextUtils;
import java.util.concurrent.Callable;

final class CurrentSpanUtils {
    private CurrentSpanUtils() {
    }

    static Span getCurrentSpan() {
        return ContextUtils.CONTEXT_SPAN_KEY.get();
    }

    static Scope withSpan(Span span2, boolean endSpan) {
        return new ScopeInSpan(span2, endSpan);
    }

    static Runnable withSpan(Span span2, boolean endSpan, Runnable runnable) {
        return new RunnableInSpan(span2, runnable, endSpan);
    }

    static <C> Callable<C> withSpan(Span span2, boolean endSpan, Callable<C> callable) {
        return new CallableInSpan(span2, callable, endSpan);
    }

    private static void setErrorStatus(Span span2, Throwable t) {
        span2.setStatus(Status.UNKNOWN.withDescription(t.getMessage() == null ? t.getClass().getSimpleName() : t.getMessage()));
    }

    private static final class CallableInSpan<V>
    implements Callable<V> {
        private final Span span;
        private final Callable<V> callable;
        private final boolean endSpan;

        private CallableInSpan(Span span2, Callable<V> callable, boolean endSpan) {
            this.span = span2;
            this.callable = callable;
            this.endSpan = endSpan;
        }

        @Override
        public V call() throws Exception {
            Context origContext = Context.current().withValue(ContextUtils.CONTEXT_SPAN_KEY, this.span).attach();
            try {
                V v = this.callable.call();
                return v;
            }
            catch (Exception e) {
                CurrentSpanUtils.setErrorStatus(this.span, e);
                throw e;
            }
            catch (Throwable t) {
                CurrentSpanUtils.setErrorStatus(this.span, t);
                Throwables.propagateIfPossible(t);
                throw new RuntimeException("unexpected", t);
            }
            finally {
                Context.current().detach(origContext);
                if (this.endSpan) {
                    this.span.end();
                }
            }
        }
    }

    private static final class RunnableInSpan
    implements Runnable {
        private final Span span;
        private final Runnable runnable;
        private final boolean endSpan;

        private RunnableInSpan(Span span2, Runnable runnable, boolean endSpan) {
            this.span = span2;
            this.runnable = runnable;
            this.endSpan = endSpan;
        }

        @Override
        public void run() {
            Context origContext = Context.current().withValue(ContextUtils.CONTEXT_SPAN_KEY, this.span).attach();
            try {
                this.runnable.run();
            }
            catch (Throwable t) {
                CurrentSpanUtils.setErrorStatus(this.span, t);
                Throwables.propagateIfPossible(t);
                throw new RuntimeException("unexpected", t);
            }
            finally {
                Context.current().detach(origContext);
                if (this.endSpan) {
                    this.span.end();
                }
            }
        }
    }

    private static final class ScopeInSpan
    implements Scope {
        private final Context origContext;
        private final Span span;
        private boolean endSpan;

        private ScopeInSpan(Span span2, boolean endSpan) {
            this.span = span2;
            this.endSpan = endSpan;
            this.origContext = Context.current().withValue(ContextUtils.CONTEXT_SPAN_KEY, span2).attach();
        }

        @Override
        public void close() {
            Context.current().detach(this.origContext);
            if (this.endSpan) {
                this.span.end();
            }
        }
    }
}

