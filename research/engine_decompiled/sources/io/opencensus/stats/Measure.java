/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.opencensus.common.Function;
import io.opencensus.internal.StringUtil;
import io.opencensus.stats.AutoValue_Measure_MeasureDouble;
import io.opencensus.stats.AutoValue_Measure_MeasureLong;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Measure {
    @VisibleForTesting
    static final int NAME_MAX_LENGTH = 255;

    public abstract <T> T match(Function<? super MeasureDouble, T> var1, Function<? super MeasureLong, T> var2, Function<? super Measure, T> var3);

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getUnit();

    private Measure() {
    }

    @Immutable
    public static abstract class MeasureLong
    extends Measure {
        MeasureLong() {
        }

        public static MeasureLong create(String name, String description, String unit) {
            Preconditions.checkArgument(StringUtil.isPrintableString(name) && name.length() <= 255, "Name should be a ASCII string with a length no greater than 255 characters.");
            return new AutoValue_Measure_MeasureLong(name, description, unit);
        }

        @Override
        public <T> T match(Function<? super MeasureDouble, T> p0, Function<? super MeasureLong, T> p1, Function<? super Measure, T> defaultFunction) {
            return p1.apply(this);
        }

        @Override
        public abstract String getName();

        @Override
        public abstract String getDescription();

        @Override
        public abstract String getUnit();
    }

    @Immutable
    public static abstract class MeasureDouble
    extends Measure {
        MeasureDouble() {
        }

        public static MeasureDouble create(String name, String description, String unit) {
            Preconditions.checkArgument(StringUtil.isPrintableString(name) && name.length() <= 255, "Name should be a ASCII string with a length no greater than 255 characters.");
            return new AutoValue_Measure_MeasureDouble(name, description, unit);
        }

        @Override
        public <T> T match(Function<? super MeasureDouble, T> p0, Function<? super MeasureLong, T> p1, Function<? super Measure, T> defaultFunction) {
            return p0.apply(this);
        }

        @Override
        public abstract String getName();

        @Override
        public abstract String getDescription();

        @Override
        public abstract String getUnit();
    }
}

