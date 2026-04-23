/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.opencensus.common.Duration;
import io.opencensus.common.Function;
import io.opencensus.internal.StringUtil;
import io.opencensus.stats.Aggregation;
import io.opencensus.stats.AutoValue_View;
import io.opencensus.stats.AutoValue_View_AggregationWindow_Cumulative;
import io.opencensus.stats.AutoValue_View_AggregationWindow_Interval;
import io.opencensus.stats.AutoValue_View_Name;
import io.opencensus.stats.Measure;
import io.opencensus.tags.TagKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class View {
    @VisibleForTesting
    static final int NAME_MAX_LENGTH = 255;

    View() {
    }

    public abstract Name getName();

    public abstract String getDescription();

    public abstract Measure getMeasure();

    public abstract Aggregation getAggregation();

    public abstract List<TagKey> getColumns();

    public abstract AggregationWindow getWindow();

    public static View create(Name name, String description, Measure measure, Aggregation aggregation, List<TagKey> columns, AggregationWindow window) {
        Preconditions.checkArgument(new HashSet<TagKey>(columns).size() == columns.size(), "Columns have duplicate.");
        return new AutoValue_View(name, description, measure, aggregation, Collections.unmodifiableList(new ArrayList<TagKey>(columns)), window);
    }

    @Immutable
    public static abstract class AggregationWindow {
        private AggregationWindow() {
        }

        public abstract <T> T match(Function<? super Cumulative, T> var1, Function<? super Interval, T> var2, Function<? super AggregationWindow, T> var3);

        @Immutable
        public static abstract class Interval
        extends AggregationWindow {
            private static final Duration ZERO = Duration.create(0L, 0);

            Interval() {
            }

            public abstract Duration getDuration();

            public static Interval create(Duration duration) {
                Preconditions.checkArgument(duration.compareTo(ZERO) > 0, "Duration must be positive");
                return new AutoValue_View_AggregationWindow_Interval(duration);
            }

            @Override
            public final <T> T match(Function<? super Cumulative, T> p0, Function<? super Interval, T> p1, Function<? super AggregationWindow, T> defaultFunction) {
                return p1.apply(this);
            }
        }

        @Immutable
        public static abstract class Cumulative
        extends AggregationWindow {
            private static final Cumulative CUMULATIVE = new AutoValue_View_AggregationWindow_Cumulative();

            Cumulative() {
            }

            public static Cumulative create() {
                return CUMULATIVE;
            }

            @Override
            public final <T> T match(Function<? super Cumulative, T> p0, Function<? super Interval, T> p1, Function<? super AggregationWindow, T> defaultFunction) {
                return p0.apply(this);
            }
        }
    }

    @Immutable
    public static abstract class Name {
        Name() {
        }

        public abstract String asString();

        public static Name create(String name) {
            Preconditions.checkArgument(StringUtil.isPrintableString(name) && name.length() <= 255, "Name should be a ASCII string with a length no greater than 255 characters.");
            return new AutoValue_View_Name(name);
        }
    }
}

