/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.propagation;

import com.google.common.base.Preconditions;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.propagation.SpanContextParseException;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public abstract class TextFormat {
    private static final NoopTextFormat NOOP_TEXT_FORMAT = new NoopTextFormat();

    public abstract List<String> fields();

    public abstract <C> void inject(SpanContext var1, C var2, Setter<C> var3);

    public abstract <C> SpanContext extract(C var1, Getter<C> var2) throws SpanContextParseException;

    static TextFormat getNoopTextFormat() {
        return NOOP_TEXT_FORMAT;
    }

    private static final class NoopTextFormat
    extends TextFormat {
        private NoopTextFormat() {
        }

        @Override
        public List<String> fields() {
            return Collections.emptyList();
        }

        @Override
        public <C> void inject(SpanContext spanContext, C carrier, Setter<C> setter2) {
            Preconditions.checkNotNull(spanContext, "spanContext");
            Preconditions.checkNotNull(carrier, "carrier");
            Preconditions.checkNotNull(setter2, "setter");
        }

        @Override
        public <C> SpanContext extract(C carrier, Getter<C> getter2) {
            Preconditions.checkNotNull(carrier, "carrier");
            Preconditions.checkNotNull(getter2, "getter");
            return SpanContext.INVALID;
        }
    }

    public static abstract class Getter<C> {
        @Nullable
        public abstract String get(C var1, String var2);
    }

    public static abstract class Setter<C> {
        public abstract void put(C var1, String var2, String var3);
    }
}

