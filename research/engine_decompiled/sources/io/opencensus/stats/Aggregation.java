/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import com.google.common.base.Preconditions;
import io.opencensus.common.Function;
import io.opencensus.stats.AutoValue_Aggregation_Count;
import io.opencensus.stats.AutoValue_Aggregation_Distribution;
import io.opencensus.stats.AutoValue_Aggregation_Mean;
import io.opencensus.stats.AutoValue_Aggregation_Sum;
import io.opencensus.stats.BucketBoundaries;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Aggregation {
    private Aggregation() {
    }

    public abstract <T> T match(Function<? super Sum, T> var1, Function<? super Count, T> var2, Function<? super Mean, T> var3, Function<? super Distribution, T> var4, Function<? super Aggregation, T> var5);

    @Immutable
    public static abstract class Distribution
    extends Aggregation {
        Distribution() {
        }

        public static Distribution create(BucketBoundaries bucketBoundaries) {
            Preconditions.checkNotNull(bucketBoundaries, "bucketBoundaries should not be null.");
            return new AutoValue_Aggregation_Distribution(bucketBoundaries);
        }

        public abstract BucketBoundaries getBucketBoundaries();

        @Override
        public final <T> T match(Function<? super Sum, T> p0, Function<? super Count, T> p1, Function<? super Mean, T> p2, Function<? super Distribution, T> p3, Function<? super Aggregation, T> defaultFunction) {
            return p3.apply(this);
        }
    }

    @Immutable
    public static abstract class Mean
    extends Aggregation {
        private static final Mean INSTANCE = new AutoValue_Aggregation_Mean();

        Mean() {
        }

        public static Mean create() {
            return INSTANCE;
        }

        @Override
        public final <T> T match(Function<? super Sum, T> p0, Function<? super Count, T> p1, Function<? super Mean, T> p2, Function<? super Distribution, T> p3, Function<? super Aggregation, T> defaultFunction) {
            return p2.apply(this);
        }
    }

    @Immutable
    public static abstract class Count
    extends Aggregation {
        private static final Count INSTANCE = new AutoValue_Aggregation_Count();

        Count() {
        }

        public static Count create() {
            return INSTANCE;
        }

        @Override
        public final <T> T match(Function<? super Sum, T> p0, Function<? super Count, T> p1, Function<? super Mean, T> p2, Function<? super Distribution, T> p3, Function<? super Aggregation, T> defaultFunction) {
            return p1.apply(this);
        }
    }

    @Immutable
    public static abstract class Sum
    extends Aggregation {
        private static final Sum INSTANCE = new AutoValue_Aggregation_Sum();

        Sum() {
        }

        public static Sum create() {
            return INSTANCE;
        }

        @Override
        public final <T> T match(Function<? super Sum, T> p0, Function<? super Count, T> p1, Function<? super Mean, T> p2, Function<? super Distribution, T> p3, Function<? super Aggregation, T> defaultFunction) {
            return p0.apply(this);
        }
    }
}

