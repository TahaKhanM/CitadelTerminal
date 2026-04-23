/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.temporal;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAdjuster;

public final class TemporalAdjusters {
    private TemporalAdjusters() {
    }

    public static TemporalAdjuster firstDayOfMonth() {
        return Impl.FIRST_DAY_OF_MONTH;
    }

    public static TemporalAdjuster lastDayOfMonth() {
        return Impl.LAST_DAY_OF_MONTH;
    }

    public static TemporalAdjuster firstDayOfNextMonth() {
        return Impl.FIRST_DAY_OF_NEXT_MONTH;
    }

    public static TemporalAdjuster firstDayOfYear() {
        return Impl.FIRST_DAY_OF_YEAR;
    }

    public static TemporalAdjuster lastDayOfYear() {
        return Impl.LAST_DAY_OF_YEAR;
    }

    public static TemporalAdjuster firstDayOfNextYear() {
        return Impl.FIRST_DAY_OF_NEXT_YEAR;
    }

    public static TemporalAdjuster firstInMonth(DayOfWeek dayOfWeek) {
        Jdk8Methods.requireNonNull(dayOfWeek, "dayOfWeek");
        return new DayOfWeekInMonth(1, dayOfWeek);
    }

    public static TemporalAdjuster lastInMonth(DayOfWeek dayOfWeek) {
        Jdk8Methods.requireNonNull(dayOfWeek, "dayOfWeek");
        return new DayOfWeekInMonth(-1, dayOfWeek);
    }

    public static TemporalAdjuster dayOfWeekInMonth(int ordinal, DayOfWeek dayOfWeek) {
        Jdk8Methods.requireNonNull(dayOfWeek, "dayOfWeek");
        return new DayOfWeekInMonth(ordinal, dayOfWeek);
    }

    public static TemporalAdjuster next(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(2, dayOfWeek);
    }

    public static TemporalAdjuster nextOrSame(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(0, dayOfWeek);
    }

    public static TemporalAdjuster previous(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(3, dayOfWeek);
    }

    public static TemporalAdjuster previousOrSame(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(1, dayOfWeek);
    }

    private static final class RelativeDayOfWeek
    implements TemporalAdjuster {
        private final int relative;
        private final int dowValue;

        private RelativeDayOfWeek(int relative, DayOfWeek dayOfWeek) {
            Jdk8Methods.requireNonNull(dayOfWeek, "dayOfWeek");
            this.relative = relative;
            this.dowValue = dayOfWeek.getValue();
        }

        @Override
        public Temporal adjustInto(Temporal temporal) {
            int calDow = temporal.get(ChronoField.DAY_OF_WEEK);
            if (this.relative < 2 && calDow == this.dowValue) {
                return temporal;
            }
            if ((this.relative & 1) == 0) {
                int daysDiff = calDow - this.dowValue;
                return temporal.plus(daysDiff >= 0 ? (long)(7 - daysDiff) : (long)(-daysDiff), ChronoUnit.DAYS);
            }
            int daysDiff = this.dowValue - calDow;
            return temporal.minus(daysDiff >= 0 ? (long)(7 - daysDiff) : (long)(-daysDiff), ChronoUnit.DAYS);
        }
    }

    private static final class DayOfWeekInMonth
    implements TemporalAdjuster {
        private final int ordinal;
        private final int dowValue;

        private DayOfWeekInMonth(int ordinal, DayOfWeek dow) {
            this.ordinal = ordinal;
            this.dowValue = dow.getValue();
        }

        @Override
        public Temporal adjustInto(Temporal temporal) {
            if (this.ordinal >= 0) {
                Temporal temp = temporal.with(ChronoField.DAY_OF_MONTH, 1L);
                int curDow = temp.get(ChronoField.DAY_OF_WEEK);
                int dowDiff = (this.dowValue - curDow + 7) % 7;
                dowDiff = (int)((long)dowDiff + ((long)this.ordinal - 1L) * 7L);
                return temp.plus(dowDiff, ChronoUnit.DAYS);
            }
            Temporal temp = temporal.with(ChronoField.DAY_OF_MONTH, temporal.range(ChronoField.DAY_OF_MONTH).getMaximum());
            int curDow = temp.get(ChronoField.DAY_OF_WEEK);
            int daysDiff = this.dowValue - curDow;
            daysDiff = daysDiff == 0 ? 0 : (daysDiff > 0 ? daysDiff - 7 : daysDiff);
            daysDiff = (int)((long)daysDiff - ((long)(-this.ordinal) - 1L) * 7L);
            return temp.plus(daysDiff, ChronoUnit.DAYS);
        }
    }

    private static class Impl
    implements TemporalAdjuster {
        private static final Impl FIRST_DAY_OF_MONTH = new Impl(0);
        private static final Impl LAST_DAY_OF_MONTH = new Impl(1);
        private static final Impl FIRST_DAY_OF_NEXT_MONTH = new Impl(2);
        private static final Impl FIRST_DAY_OF_YEAR = new Impl(3);
        private static final Impl LAST_DAY_OF_YEAR = new Impl(4);
        private static final Impl FIRST_DAY_OF_NEXT_YEAR = new Impl(5);
        private final int ordinal;

        private Impl(int ordinal) {
            this.ordinal = ordinal;
        }

        @Override
        public Temporal adjustInto(Temporal temporal) {
            switch (this.ordinal) {
                case 0: {
                    return temporal.with(ChronoField.DAY_OF_MONTH, 1L);
                }
                case 1: {
                    return temporal.with(ChronoField.DAY_OF_MONTH, temporal.range(ChronoField.DAY_OF_MONTH).getMaximum());
                }
                case 2: {
                    return temporal.with(ChronoField.DAY_OF_MONTH, 1L).plus(1L, ChronoUnit.MONTHS);
                }
                case 3: {
                    return temporal.with(ChronoField.DAY_OF_YEAR, 1L);
                }
                case 4: {
                    return temporal.with(ChronoField.DAY_OF_YEAR, temporal.range(ChronoField.DAY_OF_YEAR).getMaximum());
                }
                case 5: {
                    return temporal.with(ChronoField.DAY_OF_YEAR, 1L).plus(1L, ChronoUnit.YEARS);
                }
            }
            throw new IllegalStateException("Unreachable");
        }
    }
}

