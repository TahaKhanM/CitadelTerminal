/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.temporal;

import java.util.Locale;
import java.util.Map;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;

public final class IsoFields {
    public static final TemporalField DAY_OF_QUARTER = Field.DAY_OF_QUARTER;
    public static final TemporalField QUARTER_OF_YEAR = Field.QUARTER_OF_YEAR;
    public static final TemporalField WEEK_OF_WEEK_BASED_YEAR = Field.WEEK_OF_WEEK_BASED_YEAR;
    public static final TemporalField WEEK_BASED_YEAR = Field.WEEK_BASED_YEAR;
    public static final TemporalUnit WEEK_BASED_YEARS = Unit.WEEK_BASED_YEARS;
    public static final TemporalUnit QUARTER_YEARS = Unit.QUARTER_YEARS;

    private IsoFields() {
        throw new AssertionError((Object)"Not instantiable");
    }

    private static enum Unit implements TemporalUnit
    {
        WEEK_BASED_YEARS("WeekBasedYears", Duration.ofSeconds(31556952L)),
        QUARTER_YEARS("QuarterYears", Duration.ofSeconds(7889238L));

        private final String name;
        private final Duration duration;

        private Unit(String name, Duration estimatedDuration) {
            this.name = name;
            this.duration = estimatedDuration;
        }

        @Override
        public Duration getDuration() {
            return this.duration;
        }

        @Override
        public boolean isDurationEstimated() {
            return true;
        }

        @Override
        public boolean isDateBased() {
            return true;
        }

        @Override
        public boolean isTimeBased() {
            return false;
        }

        @Override
        public boolean isSupportedBy(Temporal temporal) {
            return temporal.isSupported(ChronoField.EPOCH_DAY);
        }

        @Override
        public <R extends Temporal> R addTo(R temporal, long periodToAdd) {
            switch (this) {
                case WEEK_BASED_YEARS: {
                    long added = Jdk8Methods.safeAdd((long)temporal.get(WEEK_BASED_YEAR), periodToAdd);
                    return (R)temporal.with(WEEK_BASED_YEAR, added);
                }
                case QUARTER_YEARS: {
                    return (R)temporal.plus(periodToAdd / 256L, ChronoUnit.YEARS).plus(periodToAdd % 256L * 3L, ChronoUnit.MONTHS);
                }
            }
            throw new IllegalStateException("Unreachable");
        }

        @Override
        public long between(Temporal temporal1, Temporal temporal2) {
            switch (this) {
                case WEEK_BASED_YEARS: {
                    return Jdk8Methods.safeSubtract(temporal2.getLong(WEEK_BASED_YEAR), temporal1.getLong(WEEK_BASED_YEAR));
                }
                case QUARTER_YEARS: {
                    return temporal1.until(temporal2, ChronoUnit.MONTHS) / 3L;
                }
            }
            throw new IllegalStateException("Unreachable");
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    private static enum Field implements TemporalField
    {
        DAY_OF_QUARTER{

            public String toString() {
                return "DayOfQuarter";
            }

            @Override
            public TemporalUnit getBaseUnit() {
                return ChronoUnit.DAYS;
            }

            @Override
            public TemporalUnit getRangeUnit() {
                return QUARTER_YEARS;
            }

            @Override
            public ValueRange range() {
                return ValueRange.of(1L, 90L, 92L);
            }

            @Override
            public boolean isSupportedBy(TemporalAccessor temporal) {
                return temporal.isSupported(ChronoField.DAY_OF_YEAR) && temporal.isSupported(ChronoField.MONTH_OF_YEAR) && temporal.isSupported(ChronoField.YEAR) && Field.isIso(temporal);
            }

            @Override
            public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
                if (!temporal.isSupported(this)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: DayOfQuarter");
                }
                long qoy = temporal.getLong(QUARTER_OF_YEAR);
                if (qoy == 1L) {
                    long year = temporal.getLong(ChronoField.YEAR);
                    return IsoChronology.INSTANCE.isLeapYear(year) ? ValueRange.of(1L, 91L) : ValueRange.of(1L, 90L);
                }
                if (qoy == 2L) {
                    return ValueRange.of(1L, 91L);
                }
                if (qoy == 3L || qoy == 4L) {
                    return ValueRange.of(1L, 92L);
                }
                return this.range();
            }

            @Override
            public long getFrom(TemporalAccessor temporal) {
                if (!temporal.isSupported(this)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: DayOfQuarter");
                }
                int doy = temporal.get(ChronoField.DAY_OF_YEAR);
                int moy = temporal.get(ChronoField.MONTH_OF_YEAR);
                long year = temporal.getLong(ChronoField.YEAR);
                return doy - QUARTER_DAYS[(moy - 1) / 3 + (IsoChronology.INSTANCE.isLeapYear(year) ? 4 : 0)];
            }

            @Override
            public <R extends Temporal> R adjustInto(R temporal, long newValue) {
                long curValue = this.getFrom(temporal);
                this.range().checkValidValue(newValue, this);
                return (R)temporal.with(ChronoField.DAY_OF_YEAR, temporal.getLong(ChronoField.DAY_OF_YEAR) + (newValue - curValue));
            }

            @Override
            public TemporalAccessor resolve(Map<TemporalField, Long> fieldValues, TemporalAccessor partialTemporal, ResolverStyle resolverStyle) {
                LocalDate date;
                Long yearLong = fieldValues.get(ChronoField.YEAR);
                Long qoyLong = fieldValues.get(QUARTER_OF_YEAR);
                if (yearLong == null || qoyLong == null) {
                    return null;
                }
                int y = ChronoField.YEAR.checkValidIntValue(yearLong);
                long doq = fieldValues.get(DAY_OF_QUARTER);
                if (resolverStyle == ResolverStyle.LENIENT) {
                    long qoy = qoyLong;
                    date = LocalDate.of(y, 1, 1);
                    date = date.plusMonths(Jdk8Methods.safeMultiply(Jdk8Methods.safeSubtract(qoy, 1L), 3));
                    date = date.plusDays(Jdk8Methods.safeSubtract(doq, 1L));
                } else {
                    int qoy = QUARTER_OF_YEAR.range().checkValidIntValue(qoyLong, QUARTER_OF_YEAR);
                    if (resolverStyle == ResolverStyle.STRICT) {
                        int max2 = 92;
                        if (qoy == 1) {
                            max2 = IsoChronology.INSTANCE.isLeapYear(y) ? 91 : 90;
                        } else if (qoy == 2) {
                            max2 = 91;
                        }
                        ValueRange.of(1L, max2).checkValidValue(doq, this);
                    } else {
                        this.range().checkValidValue(doq, this);
                    }
                    date = LocalDate.of(y, (qoy - 1) * 3 + 1, 1).plusDays(doq - 1L);
                }
                fieldValues.remove(this);
                fieldValues.remove(ChronoField.YEAR);
                fieldValues.remove(QUARTER_OF_YEAR);
                return date;
            }
        }
        ,
        QUARTER_OF_YEAR{

            public String toString() {
                return "QuarterOfYear";
            }

            @Override
            public TemporalUnit getBaseUnit() {
                return QUARTER_YEARS;
            }

            @Override
            public TemporalUnit getRangeUnit() {
                return ChronoUnit.YEARS;
            }

            @Override
            public ValueRange range() {
                return ValueRange.of(1L, 4L);
            }

            @Override
            public boolean isSupportedBy(TemporalAccessor temporal) {
                return temporal.isSupported(ChronoField.MONTH_OF_YEAR) && Field.isIso(temporal);
            }

            @Override
            public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
                return this.range();
            }

            @Override
            public long getFrom(TemporalAccessor temporal) {
                if (!temporal.isSupported(this)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: QuarterOfYear");
                }
                long moy = temporal.getLong(ChronoField.MONTH_OF_YEAR);
                return (moy + 2L) / 3L;
            }

            @Override
            public <R extends Temporal> R adjustInto(R temporal, long newValue) {
                long curValue = this.getFrom(temporal);
                this.range().checkValidValue(newValue, this);
                return (R)temporal.with(ChronoField.MONTH_OF_YEAR, temporal.getLong(ChronoField.MONTH_OF_YEAR) + (newValue - curValue) * 3L);
            }
        }
        ,
        WEEK_OF_WEEK_BASED_YEAR{

            public String toString() {
                return "WeekOfWeekBasedYear";
            }

            @Override
            public TemporalUnit getBaseUnit() {
                return ChronoUnit.WEEKS;
            }

            @Override
            public TemporalUnit getRangeUnit() {
                return WEEK_BASED_YEARS;
            }

            @Override
            public String getDisplayName(Locale locale) {
                Jdk8Methods.requireNonNull(locale, "locale");
                return "Week";
            }

            @Override
            public ValueRange range() {
                return ValueRange.of(1L, 52L, 53L);
            }

            @Override
            public boolean isSupportedBy(TemporalAccessor temporal) {
                return temporal.isSupported(ChronoField.EPOCH_DAY) && Field.isIso(temporal);
            }

            @Override
            public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
                if (!temporal.isSupported(this)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: WeekOfWeekBasedYear");
                }
                return Field.getWeekRange(LocalDate.from(temporal));
            }

            @Override
            public long getFrom(TemporalAccessor temporal) {
                if (!temporal.isSupported(this)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: WeekOfWeekBasedYear");
                }
                return Field.getWeek(LocalDate.from(temporal));
            }

            @Override
            public <R extends Temporal> R adjustInto(R temporal, long newValue) {
                this.range().checkValidValue(newValue, this);
                return (R)temporal.plus(Jdk8Methods.safeSubtract(newValue, this.getFrom(temporal)), ChronoUnit.WEEKS);
            }

            @Override
            public TemporalAccessor resolve(Map<TemporalField, Long> fieldValues, TemporalAccessor partialTemporal, ResolverStyle resolverStyle) {
                LocalDate date;
                Long wbyLong = fieldValues.get(WEEK_BASED_YEAR);
                Long dowLong = fieldValues.get(ChronoField.DAY_OF_WEEK);
                if (wbyLong == null || dowLong == null) {
                    return null;
                }
                int wby = WEEK_BASED_YEAR.range().checkValidIntValue(wbyLong, WEEK_BASED_YEAR);
                long wowby = fieldValues.get(WEEK_OF_WEEK_BASED_YEAR);
                if (resolverStyle == ResolverStyle.LENIENT) {
                    long dow = dowLong;
                    long weeks = 0L;
                    if (dow > 7L) {
                        weeks = (dow - 1L) / 7L;
                        dow = (dow - 1L) % 7L + 1L;
                    } else if (dow < 1L) {
                        weeks = dow / 7L - 1L;
                        dow = dow % 7L + 7L;
                    }
                    date = LocalDate.of(wby, 1, 4).plusWeeks(wowby - 1L).plusWeeks(weeks).with(ChronoField.DAY_OF_WEEK, dow);
                } else {
                    int dow = ChronoField.DAY_OF_WEEK.checkValidIntValue(dowLong);
                    if (resolverStyle == ResolverStyle.STRICT) {
                        LocalDate temp = LocalDate.of(wby, 1, 4);
                        ValueRange range2 = Field.getWeekRange(temp);
                        range2.checkValidValue(wowby, this);
                    } else {
                        this.range().checkValidValue(wowby, this);
                    }
                    date = LocalDate.of(wby, 1, 4).plusWeeks(wowby - 1L).with(ChronoField.DAY_OF_WEEK, dow);
                }
                fieldValues.remove(this);
                fieldValues.remove(WEEK_BASED_YEAR);
                fieldValues.remove(ChronoField.DAY_OF_WEEK);
                return date;
            }
        }
        ,
        WEEK_BASED_YEAR{

            public String toString() {
                return "WeekBasedYear";
            }

            @Override
            public TemporalUnit getBaseUnit() {
                return WEEK_BASED_YEARS;
            }

            @Override
            public TemporalUnit getRangeUnit() {
                return ChronoUnit.FOREVER;
            }

            @Override
            public ValueRange range() {
                return ChronoField.YEAR.range();
            }

            @Override
            public boolean isSupportedBy(TemporalAccessor temporal) {
                return temporal.isSupported(ChronoField.EPOCH_DAY) && Field.isIso(temporal);
            }

            @Override
            public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
                return ChronoField.YEAR.range();
            }

            @Override
            public long getFrom(TemporalAccessor temporal) {
                if (!temporal.isSupported(this)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
                }
                return Field.getWeekBasedYear(LocalDate.from(temporal));
            }

            @Override
            public <R extends Temporal> R adjustInto(R temporal, long newValue) {
                if (!this.isSupportedBy(temporal)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
                }
                int newWby = this.range().checkValidIntValue(newValue, WEEK_BASED_YEAR);
                LocalDate date = LocalDate.from(temporal);
                int dow = date.get(ChronoField.DAY_OF_WEEK);
                int week = Field.getWeek(date);
                if (week == 53 && Field.getWeekRange(newWby) == 52) {
                    week = 52;
                }
                LocalDate resolved = LocalDate.of(newWby, 1, 4);
                int days = dow - resolved.get(ChronoField.DAY_OF_WEEK) + (week - 1) * 7;
                resolved = resolved.plusDays(days);
                return (R)temporal.with(resolved);
            }
        };

        private static final int[] QUARTER_DAYS;

        @Override
        public String getDisplayName(Locale locale) {
            Jdk8Methods.requireNonNull(locale, "locale");
            return this.toString();
        }

        @Override
        public TemporalAccessor resolve(Map<TemporalField, Long> fieldValues, TemporalAccessor partialTemporal, ResolverStyle resolverStyle) {
            return null;
        }

        @Override
        public boolean isDateBased() {
            return true;
        }

        @Override
        public boolean isTimeBased() {
            return false;
        }

        private static boolean isIso(TemporalAccessor temporal) {
            return Chronology.from(temporal).equals(IsoChronology.INSTANCE);
        }

        private static ValueRange getWeekRange(LocalDate date) {
            int wby = Field.getWeekBasedYear(date);
            return ValueRange.of(1L, Field.getWeekRange(wby));
        }

        private static int getWeekRange(int wby) {
            LocalDate date = LocalDate.of(wby, 1, 1);
            if (date.getDayOfWeek() == DayOfWeek.THURSDAY || date.getDayOfWeek() == DayOfWeek.WEDNESDAY && date.isLeapYear()) {
                return 53;
            }
            return 52;
        }

        private static int getWeek(LocalDate date) {
            int alignedWeek;
            int dow0 = date.getDayOfWeek().ordinal();
            int doy0 = date.getDayOfYear() - 1;
            int doyThu0 = doy0 + (3 - dow0);
            int firstThuDoy0 = doyThu0 - (alignedWeek = doyThu0 / 7) * 7;
            int firstMonDoy0 = firstThuDoy0 - 3;
            if (firstMonDoy0 < -3) {
                firstMonDoy0 += 7;
            }
            if (doy0 < firstMonDoy0) {
                return (int)Field.getWeekRange(date.withDayOfYear(180).minusYears(1L)).getMaximum();
            }
            int week = (doy0 - firstMonDoy0) / 7 + 1;
            if (week == 53 && !(firstMonDoy0 == -3 || firstMonDoy0 == -2 && date.isLeapYear())) {
                week = 1;
            }
            return week;
        }

        private static int getWeekBasedYear(LocalDate date) {
            int year = date.getYear();
            int doy = date.getDayOfYear();
            if (doy <= 3) {
                int dow = date.getDayOfWeek().ordinal();
                if (doy - dow < -2) {
                    --year;
                }
            } else if (doy >= 363) {
                int dow = date.getDayOfWeek().ordinal();
                if ((doy = doy - 363 - (date.isLeapYear() ? 1 : 0)) - dow >= 0) {
                    ++year;
                }
            }
            return year;
        }

        static {
            QUARTER_DAYS = new int[]{0, 90, 181, 273, 0, 91, 182, 274};
        }
    }
}

