/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.export;

import com.google.common.base.Preconditions;
import io.opencensus.trace.export.AutoValue_RunningSpanStore_Filter;
import io.opencensus.trace.export.AutoValue_RunningSpanStore_PerSpanNameSummary;
import io.opencensus.trace.export.AutoValue_RunningSpanStore_Summary;
import io.opencensus.trace.export.SpanData;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class RunningSpanStore {
    private static final RunningSpanStore NOOP_RUNNING_SPAN_STORE = new NoopRunningSpanStore();

    protected RunningSpanStore() {
    }

    static RunningSpanStore getNoopRunningSpanStore() {
        return NOOP_RUNNING_SPAN_STORE;
    }

    public abstract Summary getSummary();

    public abstract Collection<SpanData> getRunningSpans(Filter var1);

    private static final class NoopRunningSpanStore
    extends RunningSpanStore {
        private static final Summary EMPTY_SUMMARY = Summary.create(Collections.<String, PerSpanNameSummary>emptyMap());

        private NoopRunningSpanStore() {
        }

        @Override
        public Summary getSummary() {
            return EMPTY_SUMMARY;
        }

        @Override
        public Collection<SpanData> getRunningSpans(Filter filter2) {
            Preconditions.checkNotNull(filter2, "filter");
            return Collections.emptyList();
        }
    }

    @Immutable
    public static abstract class Filter {
        Filter() {
        }

        public static Filter create(String spanName, int maxSpansToReturn) {
            Preconditions.checkArgument(maxSpansToReturn >= 0, "Negative maxSpansToReturn.");
            return new AutoValue_RunningSpanStore_Filter(spanName, maxSpansToReturn);
        }

        public abstract String getSpanName();

        public abstract int getMaxSpansToReturn();
    }

    @Immutable
    public static abstract class PerSpanNameSummary {
        PerSpanNameSummary() {
        }

        public static PerSpanNameSummary create(int numRunningSpans) {
            Preconditions.checkArgument(numRunningSpans >= 0, "Negative numRunningSpans.");
            return new AutoValue_RunningSpanStore_PerSpanNameSummary(numRunningSpans);
        }

        public abstract int getNumRunningSpans();
    }

    @Immutable
    public static abstract class Summary {
        Summary() {
        }

        public static Summary create(Map<String, PerSpanNameSummary> perSpanNameSummary) {
            return new AutoValue_RunningSpanStore_Summary(Collections.unmodifiableMap(new HashMap<String, PerSpanNameSummary>(Preconditions.checkNotNull(perSpanNameSummary, "perSpanNameSummary"))));
        }

        public abstract Map<String, PerSpanNameSummary> getPerSpanNameSummary();
    }
}

