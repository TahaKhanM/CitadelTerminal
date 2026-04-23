/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import io.opencensus.common.Function;
import io.opencensus.common.Functions;
import io.opencensus.common.Timestamp;
import io.opencensus.stats.Aggregation;
import io.opencensus.stats.AggregationData;
import io.opencensus.stats.AutoValue_ViewData;
import io.opencensus.stats.AutoValue_ViewData_AggregationWindowData_CumulativeData;
import io.opencensus.stats.AutoValue_ViewData_AggregationWindowData_IntervalData;
import io.opencensus.stats.Measure;
import io.opencensus.stats.View;
import io.opencensus.tags.TagValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class ViewData {
    ViewData() {
    }

    public abstract View getView();

    public abstract Map<List<TagValue>, AggregationData> getAggregationMap();

    public abstract AggregationWindowData getWindowData();

    public static ViewData create(View view, Map<? extends List<TagValue>, ? extends AggregationData> map2, AggregationWindowData windowData) {
        ViewData.checkWindow(view.getWindow(), windowData);
        HashMap deepCopy = Maps.newHashMap();
        for (Map.Entry<? extends List<TagValue>, ? extends AggregationData> entry : map2.entrySet()) {
            ViewData.checkAggregation(view.getAggregation(), entry.getValue(), view.getMeasure());
            deepCopy.put(Collections.unmodifiableList(new ArrayList(entry.getKey())), entry.getValue());
        }
        return new AutoValue_ViewData(view, Collections.unmodifiableMap(deepCopy), windowData);
    }

    private static void checkWindow(View.AggregationWindow window, final AggregationWindowData windowData) {
        window.match(new Function<View.AggregationWindow.Cumulative, Void>(){

            @Override
            public Void apply(View.AggregationWindow.Cumulative arg) {
                Preconditions.checkArgument(windowData instanceof AggregationWindowData.CumulativeData, ViewData.createErrorMessageForWindow(arg, windowData));
                return null;
            }
        }, new Function<View.AggregationWindow.Interval, Void>(){

            @Override
            public Void apply(View.AggregationWindow.Interval arg) {
                Preconditions.checkArgument(windowData instanceof AggregationWindowData.IntervalData, ViewData.createErrorMessageForWindow(arg, windowData));
                return null;
            }
        }, Functions.throwIllegalArgumentException());
    }

    private static String createErrorMessageForWindow(View.AggregationWindow window, AggregationWindowData windowData) {
        return "AggregationWindow and AggregationWindowData types mismatch. AggregationWindow: " + window + " AggregationWindowData: " + windowData;
    }

    private static void checkAggregation(final Aggregation aggregation, final AggregationData aggregationData, final Measure measure) {
        aggregation.match(new Function<Aggregation.Sum, Void>(){

            @Override
            public Void apply(Aggregation.Sum arg) {
                measure.match(new Function<Measure.MeasureDouble, Void>(){

                    @Override
                    public Void apply(Measure.MeasureDouble arg) {
                        Preconditions.checkArgument(aggregationData instanceof AggregationData.SumDataDouble, ViewData.createErrorMessageForAggregation(aggregation, aggregationData));
                        return null;
                    }
                }, new Function<Measure.MeasureLong, Void>(){

                    @Override
                    public Void apply(Measure.MeasureLong arg) {
                        Preconditions.checkArgument(aggregationData instanceof AggregationData.SumDataLong, ViewData.createErrorMessageForAggregation(aggregation, aggregationData));
                        return null;
                    }
                }, Functions.throwAssertionError());
                return null;
            }
        }, new Function<Aggregation.Count, Void>(){

            @Override
            public Void apply(Aggregation.Count arg) {
                Preconditions.checkArgument(aggregationData instanceof AggregationData.CountData, ViewData.createErrorMessageForAggregation(aggregation, aggregationData));
                return null;
            }
        }, new Function<Aggregation.Mean, Void>(){

            @Override
            public Void apply(Aggregation.Mean arg) {
                Preconditions.checkArgument(aggregationData instanceof AggregationData.MeanData, ViewData.createErrorMessageForAggregation(aggregation, aggregationData));
                return null;
            }
        }, new Function<Aggregation.Distribution, Void>(){

            @Override
            public Void apply(Aggregation.Distribution arg) {
                Preconditions.checkArgument(aggregationData instanceof AggregationData.DistributionData, ViewData.createErrorMessageForAggregation(aggregation, aggregationData));
                return null;
            }
        }, Functions.throwAssertionError());
    }

    private static String createErrorMessageForAggregation(Aggregation aggregation, AggregationData aggregationData) {
        return "Aggregation and AggregationData types mismatch. Aggregation: " + aggregation + " AggregationData: " + aggregationData;
    }

    @Immutable
    public static abstract class AggregationWindowData {
        private AggregationWindowData() {
        }

        public abstract <T> T match(Function<? super CumulativeData, T> var1, Function<? super IntervalData, T> var2, Function<? super AggregationWindowData, T> var3);

        @Immutable
        public static abstract class IntervalData
        extends AggregationWindowData {
            IntervalData() {
            }

            public abstract Timestamp getEnd();

            @Override
            public final <T> T match(Function<? super CumulativeData, T> p0, Function<? super IntervalData, T> p1, Function<? super AggregationWindowData, T> defaultFunction) {
                return p1.apply(this);
            }

            public static IntervalData create(Timestamp end) {
                return new AutoValue_ViewData_AggregationWindowData_IntervalData(end);
            }
        }

        @Immutable
        public static abstract class CumulativeData
        extends AggregationWindowData {
            CumulativeData() {
            }

            public abstract Timestamp getStart();

            public abstract Timestamp getEnd();

            @Override
            public final <T> T match(Function<? super CumulativeData, T> p0, Function<? super IntervalData, T> p1, Function<? super AggregationWindowData, T> defaultFunction) {
                return p0.apply(this);
            }

            public static CumulativeData create(Timestamp start, Timestamp end) {
                if (start.compareTo(end) > 0) {
                    throw new IllegalArgumentException("Start time is later than end time.");
                }
                return new AutoValue_ViewData_AggregationWindowData_CumulativeData(start, end);
            }
        }
    }
}

