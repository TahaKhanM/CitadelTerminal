/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import io.opencensus.common.Function;
import io.opencensus.stats.AutoValue_AggregationData_CountData;
import io.opencensus.stats.AutoValue_AggregationData_DistributionData;
import io.opencensus.stats.AutoValue_AggregationData_MeanData;
import io.opencensus.stats.AutoValue_AggregationData_SumDataDouble;
import io.opencensus.stats.AutoValue_AggregationData_SumDataLong;
import java.util.Collections;
import java.util.List;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class AggregationData {
    private AggregationData() {
    }

    public abstract <T> T match(Function<? super SumDataDouble, T> var1, Function<? super SumDataLong, T> var2, Function<? super CountData, T> var3, Function<? super MeanData, T> var4, Function<? super DistributionData, T> var5, Function<? super AggregationData, T> var6);

    @Immutable
    public static abstract class DistributionData
    extends AggregationData {
        DistributionData() {
        }

        public static DistributionData create(double mean, long count2, double min2, double max2, double sumOfSquaredDeviations, List<Long> bucketCounts) {
            if (min2 != Double.POSITIVE_INFINITY || max2 != Double.NEGATIVE_INFINITY) {
                Preconditions.checkArgument(min2 <= max2, "max should be greater or equal to min.");
            }
            Preconditions.checkNotNull(bucketCounts, "bucket counts should not be null.");
            List<Long> bucketCountsCopy = Collections.unmodifiableList(Lists.newArrayList(bucketCounts));
            for (Long bucket : bucketCountsCopy) {
                Preconditions.checkNotNull(bucket, "bucket should not be null.");
            }
            return new AutoValue_AggregationData_DistributionData(mean, count2, min2, max2, sumOfSquaredDeviations, bucketCountsCopy);
        }

        public abstract double getMean();

        public abstract long getCount();

        public abstract double getMin();

        public abstract double getMax();

        public abstract double getSumOfSquaredDeviations();

        public abstract List<Long> getBucketCounts();

        @Override
        public final <T> T match(Function<? super SumDataDouble, T> p0, Function<? super SumDataLong, T> p1, Function<? super CountData, T> p2, Function<? super MeanData, T> p3, Function<? super DistributionData, T> p4, Function<? super AggregationData, T> defaultFunction) {
            return p4.apply(this);
        }
    }

    @Immutable
    public static abstract class MeanData
    extends AggregationData {
        MeanData() {
        }

        public static MeanData create(double mean, long count2) {
            return new AutoValue_AggregationData_MeanData(mean, count2);
        }

        public abstract double getMean();

        public abstract long getCount();

        @Override
        public final <T> T match(Function<? super SumDataDouble, T> p0, Function<? super SumDataLong, T> p1, Function<? super CountData, T> p2, Function<? super MeanData, T> p3, Function<? super DistributionData, T> p4, Function<? super AggregationData, T> defaultFunction) {
            return p3.apply(this);
        }
    }

    @Immutable
    public static abstract class CountData
    extends AggregationData {
        CountData() {
        }

        public static CountData create(long count2) {
            return new AutoValue_AggregationData_CountData(count2);
        }

        public abstract long getCount();

        @Override
        public final <T> T match(Function<? super SumDataDouble, T> p0, Function<? super SumDataLong, T> p1, Function<? super CountData, T> p2, Function<? super MeanData, T> p3, Function<? super DistributionData, T> p4, Function<? super AggregationData, T> defaultFunction) {
            return p2.apply(this);
        }
    }

    @Immutable
    public static abstract class SumDataLong
    extends AggregationData {
        SumDataLong() {
        }

        public static SumDataLong create(long sum2) {
            return new AutoValue_AggregationData_SumDataLong(sum2);
        }

        public abstract long getSum();

        @Override
        public final <T> T match(Function<? super SumDataDouble, T> p0, Function<? super SumDataLong, T> p1, Function<? super CountData, T> p2, Function<? super MeanData, T> p3, Function<? super DistributionData, T> p4, Function<? super AggregationData, T> defaultFunction) {
            return p1.apply(this);
        }
    }

    @Immutable
    public static abstract class SumDataDouble
    extends AggregationData {
        SumDataDouble() {
        }

        public static SumDataDouble create(double sum2) {
            return new AutoValue_AggregationData_SumDataDouble(sum2);
        }

        public abstract double getSum();

        @Override
        public final <T> T match(Function<? super SumDataDouble, T> p0, Function<? super SumDataLong, T> p1, Function<? super CountData, T> p2, Function<? super MeanData, T> p3, Function<? super DistributionData, T> p4, Function<? super AggregationData, T> defaultFunction) {
            return p0.apply(this);
        }
    }
}

