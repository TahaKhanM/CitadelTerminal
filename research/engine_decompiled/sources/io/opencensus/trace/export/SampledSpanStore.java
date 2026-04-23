/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.export;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.opencensus.trace.Status;
import io.opencensus.trace.export.AutoValue_SampledSpanStore_ErrorFilter;
import io.opencensus.trace.export.AutoValue_SampledSpanStore_LatencyFilter;
import io.opencensus.trace.export.AutoValue_SampledSpanStore_PerSpanNameSummary;
import io.opencensus.trace.export.AutoValue_SampledSpanStore_Summary;
import io.opencensus.trace.export.SpanData;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class SampledSpanStore {
    protected SampledSpanStore() {
    }

    static SampledSpanStore newNoopSampledSpanStore() {
        return new NoopSampledSpanStore();
    }

    public abstract Summary getSummary();

    public abstract Collection<SpanData> getLatencySampledSpans(LatencyFilter var1);

    public abstract Collection<SpanData> getErrorSampledSpans(ErrorFilter var1);

    public abstract void registerSpanNamesForCollection(Collection<String> var1);

    public abstract void unregisterSpanNamesForCollection(Collection<String> var1);

    @VisibleForTesting
    public abstract Set<String> getRegisteredSpanNamesForCollection();

    @ThreadSafe
    private static final class NoopSampledSpanStore
    extends SampledSpanStore {
        private static final PerSpanNameSummary EMPTY_PER_SPAN_NAME_SUMMARY = PerSpanNameSummary.create(Collections.<LatencyBucketBoundaries, Integer>emptyMap(), Collections.<Status.CanonicalCode, Integer>emptyMap());
        @GuardedBy(value="registeredSpanNames")
        private final Set<String> registeredSpanNames = Sets.newHashSet();

        private NoopSampledSpanStore() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Summary getSummary() {
            HashMap<String, PerSpanNameSummary> result2 = Maps.newHashMap();
            Set<String> set = this.registeredSpanNames;
            synchronized (set) {
                for (String registeredSpanName : this.registeredSpanNames) {
                    result2.put(registeredSpanName, EMPTY_PER_SPAN_NAME_SUMMARY);
                }
            }
            return Summary.create(result2);
        }

        @Override
        public Collection<SpanData> getLatencySampledSpans(LatencyFilter filter2) {
            Preconditions.checkNotNull(filter2, "latencyFilter");
            return Collections.emptyList();
        }

        @Override
        public Collection<SpanData> getErrorSampledSpans(ErrorFilter filter2) {
            Preconditions.checkNotNull(filter2, "errorFilter");
            return Collections.emptyList();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void registerSpanNamesForCollection(Collection<String> spanNames) {
            Preconditions.checkNotNull(spanNames, "spanNames");
            Set<String> set = this.registeredSpanNames;
            synchronized (set) {
                this.registeredSpanNames.addAll(spanNames);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unregisterSpanNamesForCollection(Collection<String> spanNames) {
            Preconditions.checkNotNull(spanNames);
            Set<String> set = this.registeredSpanNames;
            synchronized (set) {
                this.registeredSpanNames.removeAll(spanNames);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Set<String> getRegisteredSpanNamesForCollection() {
            Set<String> set = this.registeredSpanNames;
            synchronized (set) {
                return Collections.unmodifiableSet(Sets.newHashSet(this.registeredSpanNames));
            }
        }
    }

    @Immutable
    public static abstract class ErrorFilter {
        ErrorFilter() {
        }

        public static ErrorFilter create(String spanName, @Nullable Status.CanonicalCode canonicalCode, int maxSpansToReturn) {
            if (canonicalCode != null) {
                Preconditions.checkArgument(canonicalCode != Status.CanonicalCode.OK, "Invalid canonical code.");
            }
            Preconditions.checkArgument(maxSpansToReturn >= 0, "Negative maxSpansToReturn.");
            return new AutoValue_SampledSpanStore_ErrorFilter(spanName, canonicalCode, maxSpansToReturn);
        }

        public abstract String getSpanName();

        @Nullable
        public abstract Status.CanonicalCode getCanonicalCode();

        public abstract int getMaxSpansToReturn();
    }

    @Immutable
    public static abstract class LatencyFilter {
        LatencyFilter() {
        }

        public static LatencyFilter create(String spanName, long latencyLowerNs, long latencyUpperNs, int maxSpansToReturn) {
            Preconditions.checkArgument(maxSpansToReturn >= 0, "Negative maxSpansToReturn.");
            Preconditions.checkArgument(latencyLowerNs >= 0L, "Negative latencyLowerNs");
            Preconditions.checkArgument(latencyUpperNs >= 0L, "Negative latencyUpperNs");
            return new AutoValue_SampledSpanStore_LatencyFilter(spanName, latencyLowerNs, latencyUpperNs, maxSpansToReturn);
        }

        public abstract String getSpanName();

        public abstract long getLatencyLowerNs();

        public abstract long getLatencyUpperNs();

        public abstract int getMaxSpansToReturn();
    }

    public static enum LatencyBucketBoundaries {
        ZERO_MICROSx10(0L, TimeUnit.MICROSECONDS.toNanos(10L)),
        MICROSx10_MICROSx100(TimeUnit.MICROSECONDS.toNanos(10L), TimeUnit.MICROSECONDS.toNanos(100L)),
        MICROSx100_MILLIx1(TimeUnit.MICROSECONDS.toNanos(100L), TimeUnit.MILLISECONDS.toNanos(1L)),
        MILLIx1_MILLIx10(TimeUnit.MILLISECONDS.toNanos(1L), TimeUnit.MILLISECONDS.toNanos(10L)),
        MILLIx10_MILLIx100(TimeUnit.MILLISECONDS.toNanos(10L), TimeUnit.MILLISECONDS.toNanos(100L)),
        MILLIx100_SECONDx1(TimeUnit.MILLISECONDS.toNanos(100L), TimeUnit.SECONDS.toNanos(1L)),
        SECONDx1_SECONDx10(TimeUnit.SECONDS.toNanos(1L), TimeUnit.SECONDS.toNanos(10L)),
        SECONDx10_SECONDx100(TimeUnit.SECONDS.toNanos(10L), TimeUnit.SECONDS.toNanos(100L)),
        SECONDx100_MAX(TimeUnit.SECONDS.toNanos(100L), Long.MAX_VALUE);

        private final long latencyLowerNs;
        private final long latencyUpperNs;

        private LatencyBucketBoundaries(long latencyLowerNs, long latencyUpperNs) {
            this.latencyLowerNs = latencyLowerNs;
            this.latencyUpperNs = latencyUpperNs;
        }

        public long getLatencyLowerNs() {
            return this.latencyLowerNs;
        }

        public long getLatencyUpperNs() {
            return this.latencyUpperNs;
        }
    }

    @Immutable
    public static abstract class PerSpanNameSummary {
        PerSpanNameSummary() {
        }

        public static PerSpanNameSummary create(Map<LatencyBucketBoundaries, Integer> numbersOfLatencySampledSpans, Map<Status.CanonicalCode, Integer> numbersOfErrorSampledSpans) {
            return new AutoValue_SampledSpanStore_PerSpanNameSummary(Collections.unmodifiableMap(new HashMap<LatencyBucketBoundaries, Integer>(Preconditions.checkNotNull(numbersOfLatencySampledSpans, "numbersOfLatencySampledSpans"))), Collections.unmodifiableMap(new HashMap<Status.CanonicalCode, Integer>(Preconditions.checkNotNull(numbersOfErrorSampledSpans, "numbersOfErrorSampledSpans"))));
        }

        public abstract Map<LatencyBucketBoundaries, Integer> getNumbersOfLatencySampledSpans();

        public abstract Map<Status.CanonicalCode, Integer> getNumbersOfErrorSampledSpans();
    }

    @Immutable
    public static abstract class Summary {
        Summary() {
        }

        public static Summary create(Map<String, PerSpanNameSummary> perSpanNameSummary) {
            return new AutoValue_SampledSpanStore_Summary(Collections.unmodifiableMap(new HashMap<String, PerSpanNameSummary>(Preconditions.checkNotNull(perSpanNameSummary, "perSpanNameSummary"))));
        }

        public abstract Map<String, PerSpanNameSummary> getPerSpanNameSummary();
    }
}

