/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.temporal;

import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Year;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.IsoFields;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.ValueRange;

public final class WeekFields
implements Serializable {
    private static final ConcurrentMap<String, WeekFields> CACHE = new ConcurrentHashMap<String, WeekFields>(4, 0.75f, 2);
    public static final WeekFields ISO = new WeekFields(DayOfWeek.MONDAY, 4);
    public static final WeekFields SUNDAY_START = WeekFields.of(DayOfWeek.SUNDAY, 1);
    private static final long serialVersionUID = -1177360819670808121L;
    private final DayOfWeek firstDayOfWeek;
    private final int minimalDays;
    private final transient TemporalField dayOfWeek = ComputedDayOfField.ofDayOfWeekField(this);
    private final transient TemporalField weekOfMonth = ComputedDayOfField.ofWeekOfMonthField(this);
    private final transient TemporalField weekOfYear = ComputedDayOfField.ofWeekOfYearField(this);
    private final transient TemporalField weekOfWeekBasedYear = ComputedDayOfField.ofWeekOfWeekBasedYearField(this);
    private final transient TemporalField weekBasedYear = ComputedDayOfField.ofWeekBasedYearField(this);

    public static WeekFields of(Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        locale = new Locale(locale.getLanguage(), locale.getCountry());
        GregorianCalendar gcal = new GregorianCalendar(locale);
        int calDow = gcal.getFirstDayOfWeek();
        DayOfWeek dow = DayOfWeek.SUNDAY.plus(calDow - 1);
        int minDays = gcal.getMinimalDaysInFirstWeek();
        return WeekFields.of(dow, minDays);
    }

    public static WeekFields of(DayOfWeek firstDayOfWeek, int minimalDaysInFirstWeek) {
        String key = firstDayOfWeek.toString() + minimalDaysInFirstWeek;
        WeekFields rules = (WeekFields)CACHE.get(key);
        if (rules == null) {
            rules = new WeekFields(firstDayOfWeek, minimalDaysInFirstWeek);
            CACHE.putIfAbsent(key, rules);
            rules = (WeekFields)CACHE.get(key);
        }
        return rules;
    }

    private WeekFields(DayOfWeek firstDayOfWeek, int minimalDaysInFirstWeek) {
        Jdk8Methods.requireNonNull(firstDayOfWeek, "firstDayOfWeek");
        if (minimalDaysInFirstWeek < 1 || minimalDaysInFirstWeek > 7) {
            throw new IllegalArgumentException("Minimal number of days is invalid");
        }
        this.firstDayOfWeek = firstDayOfWeek;
        this.minimalDays = minimalDaysInFirstWeek;
    }

    private Object readResolve() throws InvalidObjectException {
        try {
            return WeekFields.of(this.firstDayOfWeek, this.minimalDays);
        }
        catch (IllegalArgumentException ex) {
            throw new InvalidObjectException("Invalid WeekFields" + ex.getMessage());
        }
    }

    public DayOfWeek getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }

    public int getMinimalDaysInFirstWeek() {
        return this.minimalDays;
    }

    public TemporalField dayOfWeek() {
        return this.dayOfWeek;
    }

    public TemporalField weekOfMonth() {
        return this.weekOfMonth;
    }

    public TemporalField weekOfYear() {
        return this.weekOfYear;
    }

    public TemporalField weekOfWeekBasedYear() {
        return this.weekOfWeekBasedYear;
    }

    public TemporalField weekBasedYear() {
        return this.weekBasedYear;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof WeekFields) {
            return this.hashCode() == object.hashCode();
        }
        return false;
    }

    public int hashCode() {
        return this.firstDayOfWeek.ordinal() * 7 + this.minimalDays;
    }

    public String toString() {
        return "WeekFields[" + this.firstDayOfWeek + ',' + this.minimalDays + ']';
    }

    static class ComputedDayOfField
    implements TemporalField {
        private final String name;
        private final WeekFields weekDef;
        private final TemporalUnit baseUnit;
        private final TemporalUnit rangeUnit;
        private final ValueRange range;
        private static final ValueRange DAY_OF_WEEK_RANGE = ValueRange.of(1L, 7L);
        private static final ValueRange WEEK_OF_MONTH_RANGE = ValueRange.of(0L, 1L, 4L, 6L);
        private static final ValueRange WEEK_OF_YEAR_RANGE = ValueRange.of(0L, 1L, 52L, 54L);
        private static final ValueRange WEEK_OF_WEEK_BASED_YEAR_RANGE = ValueRange.of(1L, 52L, 53L);
        private static final ValueRange WEEK_BASED_YEAR_RANGE = ChronoField.YEAR.range();

        static ComputedDayOfField ofDayOfWeekField(WeekFields weekDef) {
            return new ComputedDayOfField("DayOfWeek", weekDef, ChronoUnit.DAYS, ChronoUnit.WEEKS, DAY_OF_WEEK_RANGE);
        }

        static ComputedDayOfField ofWeekOfMonthField(WeekFields weekDef) {
            return new ComputedDayOfField("WeekOfMonth", weekDef, ChronoUnit.WEEKS, ChronoUnit.MONTHS, WEEK_OF_MONTH_RANGE);
        }

        static ComputedDayOfField ofWeekOfYearField(WeekFields weekDef) {
            return new ComputedDayOfField("WeekOfYear", weekDef, ChronoUnit.WEEKS, ChronoUnit.YEARS, WEEK_OF_YEAR_RANGE);
        }

        static ComputedDayOfField ofWeekOfWeekBasedYearField(WeekFields weekDef) {
            return new ComputedDayOfField("WeekOfWeekBasedYear", weekDef, ChronoUnit.WEEKS, IsoFields.WEEK_BASED_YEARS, WEEK_OF_WEEK_BASED_YEAR_RANGE);
        }

        static ComputedDayOfField ofWeekBasedYearField(WeekFields weekDef) {
            return new ComputedDayOfField("WeekBasedYear", weekDef, IsoFields.WEEK_BASED_YEARS, ChronoUnit.FOREVER, WEEK_BASED_YEAR_RANGE);
        }

        private ComputedDayOfField(String name, WeekFields weekDef, TemporalUnit baseUnit, TemporalUnit rangeUnit, ValueRange range2) {
            this.name = name;
            this.weekDef = weekDef;
            this.baseUnit = baseUnit;
            this.rangeUnit = rangeUnit;
            this.range = range2;
        }

        @Override
        public long getFrom(TemporalAccessor temporal) {
            int sow = this.weekDef.getFirstDayOfWeek().getValue();
            int isoDow = temporal.get(ChronoField.DAY_OF_WEEK);
            int dow = Jdk8Methods.floorMod(isoDow - sow, 7) + 1;
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                return dow;
            }
            if (this.rangeUnit == ChronoUnit.MONTHS) {
                int dom = temporal.get(ChronoField.DAY_OF_MONTH);
                int offset = this.startOfWeekOffset(dom, dow);
                return this.computeWeek(offset, dom);
            }
            if (this.rangeUnit == ChronoUnit.YEARS) {
                int doy = temporal.get(ChronoField.DAY_OF_YEAR);
                int offset = this.startOfWeekOffset(doy, dow);
                return this.computeWeek(offset, doy);
            }
            if (this.rangeUnit == IsoFields.WEEK_BASED_YEARS) {
                return this.localizedWOWBY(temporal);
            }
            if (this.rangeUnit == ChronoUnit.FOREVER) {
                return this.localizedWBY(temporal);
            }
            throw new IllegalStateException("unreachable");
        }

        private int localizedDayOfWeek(TemporalAccessor temporal, int sow) {
            int isoDow = temporal.get(ChronoField.DAY_OF_WEEK);
            return Jdk8Methods.floorMod(isoDow - sow, 7) + 1;
        }

        private long localizedWeekOfMonth(TemporalAccessor temporal, int dow) {
            int dom = temporal.get(ChronoField.DAY_OF_MONTH);
            int offset = this.startOfWeekOffset(dom, dow);
            return this.computeWeek(offset, dom);
        }

        private long localizedWeekOfYear(TemporalAccessor temporal, int dow) {
            int doy = temporal.get(ChronoField.DAY_OF_YEAR);
            int offset = this.startOfWeekOffset(doy, dow);
            return this.computeWeek(offset, doy);
        }

        private int localizedWOWBY(TemporalAccessor temporal) {
            int year;
            int yearLen;
            int offset;
            int weekIndexOfFirstWeekNextYear;
            int sow = this.weekDef.getFirstDayOfWeek().getValue();
            int isoDow = temporal.get(ChronoField.DAY_OF_WEEK);
            int dow = Jdk8Methods.floorMod(isoDow - sow, 7) + 1;
            long woy = this.localizedWeekOfYear(temporal, dow);
            if (woy == 0L) {
                ChronoLocalDate previous = Chronology.from(temporal).date(temporal).minus(1L, ChronoUnit.WEEKS);
                return (int)this.localizedWeekOfYear(previous, dow) + 1;
            }
            if (woy >= 53L && woy >= (long)(weekIndexOfFirstWeekNextYear = this.computeWeek(offset = this.startOfWeekOffset(temporal.get(ChronoField.DAY_OF_YEAR), dow), (yearLen = Year.isLeap(year = temporal.get(ChronoField.YEAR)) ? 366 : 365) + this.weekDef.getMinimalDaysInFirstWeek()))) {
                return (int)(woy - (long)(weekIndexOfFirstWeekNextYear - 1));
            }
            return (int)woy;
        }

        private int localizedWBY(TemporalAccessor temporal) {
            int yearLen;
            int sow = this.weekDef.getFirstDayOfWeek().getValue();
            int isoDow = temporal.get(ChronoField.DAY_OF_WEEK);
            int dow = Jdk8Methods.floorMod(isoDow - sow, 7) + 1;
            int year = temporal.get(ChronoField.YEAR);
            long woy = this.localizedWeekOfYear(temporal, dow);
            if (woy == 0L) {
                return year - 1;
            }
            if (woy < 53L) {
                return year;
            }
            int offset = this.startOfWeekOffset(temporal.get(ChronoField.DAY_OF_YEAR), dow);
            int weekIndexOfFirstWeekNextYear = this.computeWeek(offset, (yearLen = Year.isLeap(year) ? 366 : 365) + this.weekDef.getMinimalDaysInFirstWeek());
            if (woy >= (long)weekIndexOfFirstWeekNextYear) {
                return year + 1;
            }
            return year;
        }

        private int startOfWeekOffset(int day, int dow) {
            int weekStart = Jdk8Methods.floorMod(day - dow, 7);
            int offset = -weekStart;
            if (weekStart + 1 > this.weekDef.getMinimalDaysInFirstWeek()) {
                offset = 7 - weekStart;
            }
            return offset;
        }

        private int computeWeek(int offset, int day) {
            return (7 + offset + (day - 1)) / 7;
        }

        @Override
        public <R extends Temporal> R adjustInto(R temporal, long newValue) {
            int currentVal;
            int newVal = this.range.checkValidIntValue(newValue, this);
            if (newVal == (currentVal = temporal.get(this))) {
                return temporal;
            }
            if (this.rangeUnit == ChronoUnit.FOREVER) {
                int baseWowby = temporal.get(this.weekDef.weekOfWeekBasedYear);
                long diffWeeks = (long)((double)(newValue - (long)currentVal) * 52.1775);
                Temporal result2 = temporal.plus(diffWeeks, ChronoUnit.WEEKS);
                if (result2.get(this) > newVal) {
                    int newWowby = result2.get(this.weekDef.weekOfWeekBasedYear);
                    result2 = result2.minus(newWowby, ChronoUnit.WEEKS);
                } else {
                    int newWowby;
                    if (result2.get(this) < newVal) {
                        result2 = result2.plus(2L, ChronoUnit.WEEKS);
                    }
                    if ((result2 = result2.plus(baseWowby - (newWowby = result2.get(this.weekDef.weekOfWeekBasedYear)), ChronoUnit.WEEKS)).get(this) > newVal) {
                        result2 = result2.minus(1L, ChronoUnit.WEEKS);
                    }
                }
                return (R)result2;
            }
            int delta = newVal - currentVal;
            return (R)temporal.plus(delta, this.baseUnit);
        }

        @Override
        public TemporalAccessor resolve(Map<TemporalField, Long> fieldValues, TemporalAccessor partialTemporal, ResolverStyle resolverStyle) {
            int sow = this.weekDef.getFirstDayOfWeek().getValue();
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                long value = fieldValues.remove(this);
                int localDow = this.range.checkValidIntValue(value, this);
                int isoDow = Jdk8Methods.floorMod(sow - 1 + (localDow - 1), 7) + 1;
                fieldValues.put(ChronoField.DAY_OF_WEEK, Long.valueOf(isoDow));
                return null;
            }
            if (!fieldValues.containsKey(ChronoField.DAY_OF_WEEK)) {
                return null;
            }
            if (this.rangeUnit == ChronoUnit.FOREVER) {
                long days;
                ChronoLocalDate date;
                if (!fieldValues.containsKey(this.weekDef.weekOfWeekBasedYear)) {
                    return null;
                }
                Chronology chrono = Chronology.from(partialTemporal);
                int isoDow = ChronoField.DAY_OF_WEEK.checkValidIntValue(fieldValues.get(ChronoField.DAY_OF_WEEK));
                int dow = Jdk8Methods.floorMod(isoDow - sow, 7) + 1;
                int wby = this.range().checkValidIntValue(fieldValues.get(this), this);
                if (resolverStyle == ResolverStyle.LENIENT) {
                    date = chrono.date(wby, 1, this.weekDef.getMinimalDaysInFirstWeek());
                    long wowby = fieldValues.get(this.weekDef.weekOfWeekBasedYear);
                    int dateDow = this.localizedDayOfWeek(date, sow);
                    long weeks = wowby - this.localizedWeekOfYear(date, dateDow);
                    days = weeks * 7L + (long)(dow - dateDow);
                } else {
                    date = chrono.date(wby, 1, this.weekDef.getMinimalDaysInFirstWeek());
                    long wowby = this.weekDef.weekOfWeekBasedYear.range().checkValidIntValue(fieldValues.get(this.weekDef.weekOfWeekBasedYear), this.weekDef.weekOfWeekBasedYear);
                    int dateDow = this.localizedDayOfWeek(date, sow);
                    long weeks = wowby - this.localizedWeekOfYear(date, dateDow);
                    days = weeks * 7L + (long)(dow - dateDow);
                }
                date = date.plus(days, ChronoUnit.DAYS);
                if (resolverStyle == ResolverStyle.STRICT && date.getLong(this) != fieldValues.get(this).longValue()) {
                    throw new DateTimeException("Strict mode rejected date parsed to a different year");
                }
                fieldValues.remove(this);
                fieldValues.remove(this.weekDef.weekOfWeekBasedYear);
                fieldValues.remove(ChronoField.DAY_OF_WEEK);
                return date;
            }
            if (!fieldValues.containsKey(ChronoField.YEAR)) {
                return null;
            }
            int isoDow = ChronoField.DAY_OF_WEEK.checkValidIntValue(fieldValues.get(ChronoField.DAY_OF_WEEK));
            int dow = Jdk8Methods.floorMod(isoDow - sow, 7) + 1;
            int year = ChronoField.YEAR.checkValidIntValue(fieldValues.get(ChronoField.YEAR));
            Chronology chrono = Chronology.from(partialTemporal);
            if (this.rangeUnit == ChronoUnit.MONTHS) {
                long days;
                ChronoLocalDate date;
                if (!fieldValues.containsKey(ChronoField.MONTH_OF_YEAR)) {
                    return null;
                }
                long value = fieldValues.remove(this);
                if (resolverStyle == ResolverStyle.LENIENT) {
                    long month = fieldValues.get(ChronoField.MONTH_OF_YEAR);
                    date = chrono.date(year, 1, 1);
                    date = date.plus(month - 1L, ChronoUnit.MONTHS);
                    int dateDow = this.localizedDayOfWeek(date, sow);
                    long weeks = value - this.localizedWeekOfMonth(date, dateDow);
                    days = weeks * 7L + (long)(dow - dateDow);
                } else {
                    int month = ChronoField.MONTH_OF_YEAR.checkValidIntValue(fieldValues.get(ChronoField.MONTH_OF_YEAR));
                    date = chrono.date(year, month, 8);
                    int dateDow = this.localizedDayOfWeek(date, sow);
                    int wom = this.range.checkValidIntValue(value, this);
                    long weeks = (long)wom - this.localizedWeekOfMonth(date, dateDow);
                    days = weeks * 7L + (long)(dow - dateDow);
                }
                date = date.plus(days, ChronoUnit.DAYS);
                if (resolverStyle == ResolverStyle.STRICT && date.getLong(ChronoField.MONTH_OF_YEAR) != fieldValues.get(ChronoField.MONTH_OF_YEAR).longValue()) {
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                }
                fieldValues.remove(this);
                fieldValues.remove(ChronoField.YEAR);
                fieldValues.remove(ChronoField.MONTH_OF_YEAR);
                fieldValues.remove(ChronoField.DAY_OF_WEEK);
                return date;
            }
            if (this.rangeUnit == ChronoUnit.YEARS) {
                long days;
                long value = fieldValues.remove(this);
                ChronoLocalDate date = chrono.date(year, 1, 1);
                if (resolverStyle == ResolverStyle.LENIENT) {
                    int dateDow = this.localizedDayOfWeek(date, sow);
                    long weeks = value - this.localizedWeekOfYear(date, dateDow);
                    days = weeks * 7L + (long)(dow - dateDow);
                } else {
                    int dateDow = this.localizedDayOfWeek(date, sow);
                    int woy = this.range.checkValidIntValue(value, this);
                    long weeks = (long)woy - this.localizedWeekOfYear(date, dateDow);
                    days = weeks * 7L + (long)(dow - dateDow);
                }
                date = date.plus(days, ChronoUnit.DAYS);
                if (resolverStyle == ResolverStyle.STRICT && date.getLong(ChronoField.YEAR) != fieldValues.get(ChronoField.YEAR).longValue()) {
                    throw new DateTimeException("Strict mode rejected date parsed to a different year");
                }
                fieldValues.remove(this);
                fieldValues.remove(ChronoField.YEAR);
                fieldValues.remove(ChronoField.DAY_OF_WEEK);
                return date;
            }
            throw new IllegalStateException("unreachable");
        }

        @Override
        public TemporalUnit getBaseUnit() {
            return this.baseUnit;
        }

        @Override
        public TemporalUnit getRangeUnit() {
            return this.rangeUnit;
        }

        @Override
        public ValueRange range() {
            return this.range;
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
        public boolean isSupportedBy(TemporalAccessor temporal) {
            if (temporal.isSupported(ChronoField.DAY_OF_WEEK)) {
                if (this.rangeUnit == ChronoUnit.WEEKS) {
                    return true;
                }
                if (this.rangeUnit == ChronoUnit.MONTHS) {
                    return temporal.isSupported(ChronoField.DAY_OF_MONTH);
                }
                if (this.rangeUnit == ChronoUnit.YEARS) {
                    return temporal.isSupported(ChronoField.DAY_OF_YEAR);
                }
                if (this.rangeUnit == IsoFields.WEEK_BASED_YEARS) {
                    return temporal.isSupported(ChronoField.EPOCH_DAY);
                }
                if (this.rangeUnit == ChronoUnit.FOREVER) {
                    return temporal.isSupported(ChronoField.EPOCH_DAY);
                }
            }
            return false;
        }

        @Override
        public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                return this.range;
            }
            ChronoField field2 = null;
            if (this.rangeUnit == ChronoUnit.MONTHS) {
                field2 = ChronoField.DAY_OF_MONTH;
            } else if (this.rangeUnit == ChronoUnit.YEARS) {
                field2 = ChronoField.DAY_OF_YEAR;
            } else {
                if (this.rangeUnit == IsoFields.WEEK_BASED_YEARS) {
                    return this.rangeWOWBY(temporal);
                }
                if (this.rangeUnit == ChronoUnit.FOREVER) {
                    return temporal.range(ChronoField.YEAR);
                }
                throw new IllegalStateException("unreachable");
            }
            int sow = this.weekDef.getFirstDayOfWeek().getValue();
            int isoDow = temporal.get(ChronoField.DAY_OF_WEEK);
            int dow = Jdk8Methods.floorMod(isoDow - sow, 7) + 1;
            int offset = this.startOfWeekOffset(temporal.get(field2), dow);
            ValueRange fieldRange = temporal.range(field2);
            return ValueRange.of(this.computeWeek(offset, (int)fieldRange.getMinimum()), this.computeWeek(offset, (int)fieldRange.getMaximum()));
        }

        private ValueRange rangeWOWBY(TemporalAccessor temporal) {
            int year;
            int yearLen;
            int sow = this.weekDef.getFirstDayOfWeek().getValue();
            int isoDow = temporal.get(ChronoField.DAY_OF_WEEK);
            int dow = Jdk8Methods.floorMod(isoDow - sow, 7) + 1;
            long woy = this.localizedWeekOfYear(temporal, dow);
            if (woy == 0L) {
                return this.rangeWOWBY(Chronology.from(temporal).date(temporal).minus(2L, ChronoUnit.WEEKS));
            }
            int offset = this.startOfWeekOffset(temporal.get(ChronoField.DAY_OF_YEAR), dow);
            int weekIndexOfFirstWeekNextYear = this.computeWeek(offset, (yearLen = Year.isLeap(year = temporal.get(ChronoField.YEAR)) ? 366 : 365) + this.weekDef.getMinimalDaysInFirstWeek());
            if (woy >= (long)weekIndexOfFirstWeekNextYear) {
                return this.rangeWOWBY(Chronology.from(temporal).date(temporal).plus(2L, ChronoUnit.WEEKS));
            }
            return ValueRange.of(1L, weekIndexOfFirstWeekNextYear - 1);
        }

        @Override
        public String getDisplayName(Locale locale) {
            Jdk8Methods.requireNonNull(locale, "locale");
            if (this.rangeUnit == ChronoUnit.YEARS) {
                return "Week";
            }
            return this.toString();
        }

        public String toString() {
            return this.name + "[" + this.weekDef.toString() + "]";
        }
    }
}

