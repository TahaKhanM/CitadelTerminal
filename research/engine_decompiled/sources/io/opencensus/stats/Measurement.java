/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.common.Function;
import io.opencensus.stats.AutoValue_Measurement_MeasurementDouble;
import io.opencensus.stats.AutoValue_Measurement_MeasurementLong;
import io.opencensus.stats.Measure;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Measurement {
    public abstract <T> T match(Function<? super MeasurementDouble, T> var1, Function<? super MeasurementLong, T> var2, Function<? super Measurement, T> var3);

    public abstract Measure getMeasure();

    private Measurement() {
    }

    @Immutable
    public static abstract class MeasurementLong
    extends Measurement {
        MeasurementLong() {
        }

        public static MeasurementLong create(Measure.MeasureLong measure, long value) {
            return new AutoValue_Measurement_MeasurementLong(measure, value);
        }

        @Override
        public abstract Measure.MeasureLong getMeasure();

        public abstract long getValue();

        @Override
        public <T> T match(Function<? super MeasurementDouble, T> p0, Function<? super MeasurementLong, T> p1, Function<? super Measurement, T> defaultFunction) {
            return p1.apply(this);
        }
    }

    @Immutable
    public static abstract class MeasurementDouble
    extends Measurement {
        MeasurementDouble() {
        }

        public static MeasurementDouble create(Measure.MeasureDouble measure, double value) {
            return new AutoValue_Measurement_MeasurementDouble(measure, value);
        }

        @Override
        public abstract Measure.MeasureDouble getMeasure();

        public abstract double getValue();

        @Override
        public <T> T match(Function<? super MeasurementDouble, T> p0, Function<? super MeasurementLong, T> p1, Function<? super Measurement, T> defaultFunction) {
            return p0.apply(this);
        }
    }
}

