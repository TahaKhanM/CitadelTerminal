/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.ParseException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.chrono.ChronoDateImpl;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.chrono.HijrahChronology;
import org.threeten.bp.chrono.HijrahEra;
import org.threeten.bp.chrono.Ser;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;

public final class HijrahDate
extends ChronoDateImpl<HijrahDate>
implements Serializable {
    private static final long serialVersionUID = -5207853542612002020L;
    public static final int MIN_VALUE_OF_ERA = 1;
    public static final int MAX_VALUE_OF_ERA = 9999;
    private static final int[] NUM_DAYS;
    private static final int[] LEAP_NUM_DAYS;
    private static final int[] MONTH_LENGTH;
    private static final int[] LEAP_MONTH_LENGTH;
    private static final int[] MIN_VALUES;
    private static final int[] LEAST_MAX_VALUES;
    private static final int[] MAX_VALUES;
    private static final int POSITION_DAY_OF_MONTH = 5;
    private static final int POSITION_DAY_OF_YEAR = 6;
    private static final int[] CYCLEYEAR_START_DATE;
    private static final char FILE_SEP;
    private static final String PATH_SEP;
    private static final String DEFAULT_CONFIG_FILENAME = "hijrah_deviation.cfg";
    private static final String DEFAULT_CONFIG_PATH;
    private static final HashMap<Integer, Integer[]> ADJUSTED_MONTH_DAYS;
    private static final HashMap<Integer, Integer[]> ADJUSTED_MONTH_LENGTHS;
    private static final HashMap<Integer, Integer[]> ADJUSTED_CYCLE_YEARS;
    private static final Long[] ADJUSTED_CYCLES;
    private static final Integer[] ADJUSTED_MIN_VALUES;
    private static final Integer[] ADJUSTED_LEAST_MAX_VALUES;
    private static final Integer[] ADJUSTED_MAX_VALUES;
    private static final Integer[] DEFAULT_MONTH_DAYS;
    private static final Integer[] DEFAULT_LEAP_MONTH_DAYS;
    private static final Integer[] DEFAULT_MONTH_LENGTHS;
    private static final Integer[] DEFAULT_LEAP_MONTH_LENGTHS;
    private static final Integer[] DEFAULT_CYCLE_YEARS;
    private static final int MAX_ADJUSTED_CYCLE = 334;
    private static final int HIJRAH_JAN_1_1_GREGORIAN_DAY = -492148;
    private final transient HijrahEra era;
    private final transient int yearOfEra;
    private final transient int monthOfYear;
    private final transient int dayOfMonth;
    private final transient int dayOfYear;
    private final transient DayOfWeek dayOfWeek;
    private final long gregorianEpochDay;
    private final transient boolean isLeapYear;

    public static HijrahDate now() {
        return HijrahDate.now(Clock.systemDefaultZone());
    }

    public static HijrahDate now(ZoneId zone) {
        return HijrahDate.now(Clock.system(zone));
    }

    public static HijrahDate now(Clock clock) {
        return HijrahChronology.INSTANCE.dateNow(clock);
    }

    public static HijrahDate of(int prolepticYear, int monthOfYear, int dayOfMonth) {
        return prolepticYear >= 1 ? HijrahDate.of(HijrahEra.AH, prolepticYear, monthOfYear, dayOfMonth) : HijrahDate.of(HijrahEra.BEFORE_AH, 1 - prolepticYear, monthOfYear, dayOfMonth);
    }

    static HijrahDate of(HijrahEra era, int yearOfEra, int monthOfYear, int dayOfMonth) {
        Jdk8Methods.requireNonNull(era, "era");
        HijrahDate.checkValidYearOfEra(yearOfEra);
        HijrahDate.checkValidMonth(monthOfYear);
        HijrahDate.checkValidDayOfMonth(dayOfMonth);
        long gregorianDays = HijrahDate.getGregorianEpochDay(era.prolepticYear(yearOfEra), monthOfYear, dayOfMonth);
        return new HijrahDate(gregorianDays);
    }

    private static void checkValidYearOfEra(int yearOfEra) {
        if (yearOfEra < 1 || yearOfEra > 9999) {
            throw new DateTimeException("Invalid year of Hijrah Era");
        }
    }

    private static void checkValidDayOfYear(int dayOfYear) {
        if (dayOfYear < 1 || dayOfYear > HijrahDate.getMaximumDayOfYear()) {
            throw new DateTimeException("Invalid day of year of Hijrah date");
        }
    }

    private static void checkValidMonth(int month) {
        if (month < 1 || month > 12) {
            throw new DateTimeException("Invalid month of Hijrah date");
        }
    }

    private static void checkValidDayOfMonth(int dayOfMonth) {
        if (dayOfMonth < 1 || dayOfMonth > HijrahDate.getMaximumDayOfMonth()) {
            throw new DateTimeException("Invalid day of month of Hijrah date, day " + dayOfMonth + " greater than " + HijrahDate.getMaximumDayOfMonth() + " or less than 1");
        }
    }

    static HijrahDate of(LocalDate date) {
        long gregorianDays = date.toEpochDay();
        return new HijrahDate(gregorianDays);
    }

    static HijrahDate ofEpochDay(long epochDay) {
        return new HijrahDate(epochDay);
    }

    public static HijrahDate from(TemporalAccessor temporal) {
        return HijrahChronology.INSTANCE.date(temporal);
    }

    private HijrahDate(long gregorianDay) {
        int[] dateInfo = HijrahDate.getHijrahDateInfo(gregorianDay);
        HijrahDate.checkValidYearOfEra(dateInfo[1]);
        HijrahDate.checkValidMonth(dateInfo[2]);
        HijrahDate.checkValidDayOfMonth(dateInfo[3]);
        HijrahDate.checkValidDayOfYear(dateInfo[4]);
        this.era = HijrahEra.of(dateInfo[0]);
        this.yearOfEra = dateInfo[1];
        this.monthOfYear = dateInfo[2];
        this.dayOfMonth = dateInfo[3];
        this.dayOfYear = dateInfo[4];
        this.dayOfWeek = DayOfWeek.of(dateInfo[5]);
        this.gregorianEpochDay = gregorianDay;
        this.isLeapYear = HijrahDate.isLeapYear(this.yearOfEra);
    }

    private Object readResolve() {
        return new HijrahDate(this.gregorianEpochDay);
    }

    @Override
    public HijrahChronology getChronology() {
        return HijrahChronology.INSTANCE;
    }

    @Override
    public HijrahEra getEra() {
        return this.era;
    }

    @Override
    public ValueRange range(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            if (this.isSupported(field2)) {
                ChronoField f = (ChronoField)field2;
                switch (f) {
                    case DAY_OF_MONTH: {
                        return ValueRange.of(1L, this.lengthOfMonth());
                    }
                    case DAY_OF_YEAR: {
                        return ValueRange.of(1L, this.lengthOfYear());
                    }
                    case ALIGNED_WEEK_OF_MONTH: {
                        return ValueRange.of(1L, 5L);
                    }
                    case YEAR_OF_ERA: {
                        return ValueRange.of(1L, 1000L);
                    }
                }
                return this.getChronology().range(f);
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.rangeRefinedBy(this);
    }

    @Override
    public long getLong(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            switch ((ChronoField)field2) {
                case DAY_OF_WEEK: {
                    return this.dayOfWeek.getValue();
                }
                case ALIGNED_DAY_OF_WEEK_IN_MONTH: {
                    return (this.dayOfMonth - 1) % 7 + 1;
                }
                case ALIGNED_DAY_OF_WEEK_IN_YEAR: {
                    return (this.dayOfYear - 1) % 7 + 1;
                }
                case DAY_OF_MONTH: {
                    return this.dayOfMonth;
                }
                case DAY_OF_YEAR: {
                    return this.dayOfYear;
                }
                case EPOCH_DAY: {
                    return this.toEpochDay();
                }
                case ALIGNED_WEEK_OF_MONTH: {
                    return (this.dayOfMonth - 1) / 7 + 1;
                }
                case ALIGNED_WEEK_OF_YEAR: {
                    return (this.dayOfYear - 1) / 7 + 1;
                }
                case MONTH_OF_YEAR: {
                    return this.monthOfYear;
                }
                case YEAR_OF_ERA: {
                    return this.yearOfEra;
                }
                case YEAR: {
                    return this.yearOfEra;
                }
                case ERA: {
                    return this.era.getValue();
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.getFrom(this);
    }

    @Override
    public HijrahDate with(TemporalAdjuster adjuster) {
        return (HijrahDate)super.with(adjuster);
    }

    @Override
    public HijrahDate with(TemporalField field2, long newValue) {
        if (field2 instanceof ChronoField) {
            ChronoField f = (ChronoField)field2;
            f.checkValidValue(newValue);
            int nvalue = (int)newValue;
            switch (f) {
                case DAY_OF_WEEK: {
                    return this.plusDays(newValue - (long)this.dayOfWeek.getValue());
                }
                case ALIGNED_DAY_OF_WEEK_IN_MONTH: {
                    return this.plusDays(newValue - this.getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
                }
                case ALIGNED_DAY_OF_WEEK_IN_YEAR: {
                    return this.plusDays(newValue - this.getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
                }
                case DAY_OF_MONTH: {
                    return HijrahDate.resolvePreviousValid(this.yearOfEra, this.monthOfYear, nvalue);
                }
                case DAY_OF_YEAR: {
                    return HijrahDate.resolvePreviousValid(this.yearOfEra, (nvalue - 1) / 30 + 1, (nvalue - 1) % 30 + 1);
                }
                case EPOCH_DAY: {
                    return new HijrahDate(nvalue);
                }
                case ALIGNED_WEEK_OF_MONTH: {
                    return this.plusDays((newValue - this.getLong(ChronoField.ALIGNED_WEEK_OF_MONTH)) * 7L);
                }
                case ALIGNED_WEEK_OF_YEAR: {
                    return this.plusDays((newValue - this.getLong(ChronoField.ALIGNED_WEEK_OF_YEAR)) * 7L);
                }
                case MONTH_OF_YEAR: {
                    return HijrahDate.resolvePreviousValid(this.yearOfEra, nvalue, this.dayOfMonth);
                }
                case YEAR_OF_ERA: {
                    return HijrahDate.resolvePreviousValid(this.yearOfEra >= 1 ? nvalue : 1 - nvalue, this.monthOfYear, this.dayOfMonth);
                }
                case YEAR: {
                    return HijrahDate.resolvePreviousValid(nvalue, this.monthOfYear, this.dayOfMonth);
                }
                case ERA: {
                    return HijrahDate.resolvePreviousValid(1 - this.yearOfEra, this.monthOfYear, this.dayOfMonth);
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.adjustInto(this, newValue);
    }

    private static HijrahDate resolvePreviousValid(int yearOfEra, int month, int day) {
        int monthDays = HijrahDate.getMonthDays(month - 1, yearOfEra);
        if (day > monthDays) {
            day = monthDays;
        }
        return HijrahDate.of(yearOfEra, month, day);
    }

    @Override
    public HijrahDate plus(TemporalAmount amount) {
        return (HijrahDate)super.plus(amount);
    }

    @Override
    public HijrahDate plus(long amountToAdd, TemporalUnit unit) {
        return (HijrahDate)super.plus(amountToAdd, unit);
    }

    @Override
    public HijrahDate minus(TemporalAmount amount) {
        return (HijrahDate)super.minus(amount);
    }

    @Override
    public HijrahDate minus(long amountToAdd, TemporalUnit unit) {
        return (HijrahDate)super.minus(amountToAdd, unit);
    }

    @Override
    public final ChronoLocalDateTime<HijrahDate> atTime(LocalTime localTime) {
        return super.atTime(localTime);
    }

    @Override
    public long toEpochDay() {
        return HijrahDate.getGregorianEpochDay(this.yearOfEra, this.monthOfYear, this.dayOfMonth);
    }

    @Override
    public boolean isLeapYear() {
        return this.isLeapYear;
    }

    HijrahDate plusYears(long years) {
        if (years == 0L) {
            return this;
        }
        int newYear = Jdk8Methods.safeAdd(this.yearOfEra, (int)years);
        return HijrahDate.of(this.era, newYear, this.monthOfYear, this.dayOfMonth);
    }

    HijrahDate plusMonths(long months) {
        if (months == 0L) {
            return this;
        }
        int newMonth = this.monthOfYear - 1;
        int years = (newMonth += (int)months) / 12;
        newMonth %= 12;
        while (newMonth < 0) {
            newMonth += 12;
            years = Jdk8Methods.safeSubtract(years, 1);
        }
        int newYear = Jdk8Methods.safeAdd(this.yearOfEra, years);
        return HijrahDate.of(this.era, newYear, newMonth + 1, this.dayOfMonth);
    }

    HijrahDate plusDays(long days) {
        return new HijrahDate(this.gregorianEpochDay + days);
    }

    private static int[] getHijrahDateInfo(long gregorianDays) {
        int era;
        int date;
        int month;
        int year;
        int dayOfYear;
        long epochDay = gregorianDays - -492148L;
        if (epochDay >= 0L) {
            int cycleNumber = HijrahDate.getCycleNumber(epochDay);
            int dayOfCycle = HijrahDate.getDayOfCycle(epochDay, cycleNumber);
            int yearInCycle = HijrahDate.getYearInCycle(cycleNumber, dayOfCycle);
            dayOfYear = HijrahDate.getDayOfYear(cycleNumber, dayOfCycle, yearInCycle);
            year = cycleNumber * 30 + yearInCycle + 1;
            month = HijrahDate.getMonthOfYear(dayOfYear, year);
            date = HijrahDate.getDayOfMonth(dayOfYear, month, year);
            ++date;
            era = HijrahEra.AH.getValue();
        } else {
            int cycleNumber = (int)epochDay / 10631;
            int dayOfCycle = (int)epochDay % 10631;
            if (dayOfCycle == 0) {
                dayOfCycle = -10631;
                ++cycleNumber;
            }
            int yearInCycle = HijrahDate.getYearInCycle(cycleNumber, dayOfCycle);
            dayOfYear = HijrahDate.getDayOfYear(cycleNumber, dayOfCycle, yearInCycle);
            year = cycleNumber * 30 - yearInCycle;
            dayOfYear = HijrahDate.isLeapYear(year = 1 - year) ? dayOfYear + 355 : dayOfYear + 354;
            month = HijrahDate.getMonthOfYear(dayOfYear, year);
            date = HijrahDate.getDayOfMonth(dayOfYear, month, year);
            ++date;
            era = HijrahEra.BEFORE_AH.getValue();
        }
        int dayOfWeek = (int)((epochDay + 5L) % 7L);
        int[] dateInfo = new int[]{era, year, month + 1, date, dayOfYear + 1, dayOfWeek += dayOfWeek <= 0 ? 7 : 0};
        return dateInfo;
    }

    private static long getGregorianEpochDay(int prolepticYear, int monthOfYear, int dayOfMonth) {
        long day = HijrahDate.yearToGregorianEpochDay(prolepticYear);
        day += (long)HijrahDate.getMonthDays(monthOfYear - 1, prolepticYear);
        return day += (long)dayOfMonth;
    }

    private static long yearToGregorianEpochDay(int prolepticYear) {
        Long cycleDays;
        int cycleNumber = (prolepticYear - 1) / 30;
        int yearInCycle = (prolepticYear - 1) % 30;
        int dayInCycle = HijrahDate.getAdjustedCycle(cycleNumber)[Math.abs(yearInCycle)];
        if (yearInCycle < 0) {
            dayInCycle = -dayInCycle;
        }
        try {
            cycleDays = ADJUSTED_CYCLES[cycleNumber];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            cycleDays = null;
        }
        if (cycleDays == null) {
            cycleDays = new Long(cycleNumber * 10631);
        }
        return cycleDays + (long)dayInCycle + -492148L - 1L;
    }

    private static int getCycleNumber(long epochDay) {
        int cycleNumber;
        Long[] days = ADJUSTED_CYCLES;
        try {
            for (int i = 0; i < days.length; ++i) {
                if (epochDay >= days[i]) continue;
                return i - 1;
            }
            cycleNumber = (int)epochDay / 10631;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            cycleNumber = (int)epochDay / 10631;
        }
        return cycleNumber;
    }

    private static int getDayOfCycle(long epochDay, int cycleNumber) {
        Long day;
        try {
            day = ADJUSTED_CYCLES[cycleNumber];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            day = null;
        }
        if (day == null) {
            day = new Long(cycleNumber * 10631);
        }
        return (int)(epochDay - day);
    }

    private static int getYearInCycle(int cycleNumber, long dayOfCycle) {
        Integer[] cycles = HijrahDate.getAdjustedCycle(cycleNumber);
        if (dayOfCycle == 0L) {
            return 0;
        }
        if (dayOfCycle > 0L) {
            for (int i = 0; i < cycles.length; ++i) {
                if (dayOfCycle >= (long)cycles[i].intValue()) continue;
                return i - 1;
            }
            return 29;
        }
        dayOfCycle = -dayOfCycle;
        for (int i = 0; i < cycles.length; ++i) {
            if (dayOfCycle > (long)cycles[i].intValue()) continue;
            return i - 1;
        }
        return 29;
    }

    private static Integer[] getAdjustedCycle(int cycleNumber) {
        Integer[] cycles;
        try {
            cycles = ADJUSTED_CYCLE_YEARS.get(new Integer(cycleNumber));
        }
        catch (ArrayIndexOutOfBoundsException e) {
            cycles = null;
        }
        if (cycles == null) {
            cycles = DEFAULT_CYCLE_YEARS;
        }
        return cycles;
    }

    private static Integer[] getAdjustedMonthDays(int year) {
        Integer[] newMonths;
        try {
            newMonths = ADJUSTED_MONTH_DAYS.get(new Integer(year));
        }
        catch (ArrayIndexOutOfBoundsException e) {
            newMonths = null;
        }
        if (newMonths == null) {
            newMonths = HijrahDate.isLeapYear(year) ? DEFAULT_LEAP_MONTH_DAYS : DEFAULT_MONTH_DAYS;
        }
        return newMonths;
    }

    private static Integer[] getAdjustedMonthLength(int year) {
        Integer[] newMonths;
        try {
            newMonths = ADJUSTED_MONTH_LENGTHS.get(new Integer(year));
        }
        catch (ArrayIndexOutOfBoundsException e) {
            newMonths = null;
        }
        if (newMonths == null) {
            newMonths = HijrahDate.isLeapYear(year) ? DEFAULT_LEAP_MONTH_LENGTHS : DEFAULT_MONTH_LENGTHS;
        }
        return newMonths;
    }

    private static int getDayOfYear(int cycleNumber, int dayOfCycle, int yearInCycle) {
        Integer[] cycles = HijrahDate.getAdjustedCycle(cycleNumber);
        if (dayOfCycle > 0) {
            return dayOfCycle - cycles[yearInCycle];
        }
        return cycles[yearInCycle] + dayOfCycle;
    }

    private static int getMonthOfYear(int dayOfYear, int year) {
        Integer[] newMonths = HijrahDate.getAdjustedMonthDays(year);
        if (dayOfYear >= 0) {
            for (int i = 0; i < newMonths.length; ++i) {
                if (dayOfYear >= newMonths[i]) continue;
                return i - 1;
            }
            return 11;
        }
        dayOfYear = HijrahDate.isLeapYear(year) ? dayOfYear + 355 : dayOfYear + 354;
        for (int i = 0; i < newMonths.length; ++i) {
            if (dayOfYear >= newMonths[i]) continue;
            return i - 1;
        }
        return 11;
    }

    private static int getDayOfMonth(int dayOfYear, int month, int year) {
        Integer[] newMonths = HijrahDate.getAdjustedMonthDays(year);
        if (dayOfYear >= 0) {
            if (month > 0) {
                return dayOfYear - newMonths[month];
            }
            return dayOfYear;
        }
        int n = dayOfYear = HijrahDate.isLeapYear(year) ? dayOfYear + 355 : dayOfYear + 354;
        if (month > 0) {
            return dayOfYear - newMonths[month];
        }
        return dayOfYear;
    }

    static boolean isLeapYear(long year) {
        return (14L + 11L * (year > 0L ? year : -year)) % 30L < 11L;
    }

    private static int getMonthDays(int month, int year) {
        Integer[] newMonths = HijrahDate.getAdjustedMonthDays(year);
        return newMonths[month];
    }

    static int getMonthLength(int month, int year) {
        Integer[] newMonths = HijrahDate.getAdjustedMonthLength(year);
        return newMonths[month];
    }

    @Override
    public int lengthOfMonth() {
        return HijrahDate.getMonthLength(this.monthOfYear - 1, this.yearOfEra);
    }

    static int getYearLength(int year) {
        Integer[] cycleYears;
        int cycleNumber = (year - 1) / 30;
        try {
            cycleYears = ADJUSTED_CYCLE_YEARS.get(cycleNumber);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            cycleYears = null;
        }
        if (cycleYears != null) {
            int yearInCycle = (year - 1) % 30;
            if (yearInCycle == 29) {
                return ADJUSTED_CYCLES[cycleNumber + 1].intValue() - ADJUSTED_CYCLES[cycleNumber].intValue() - cycleYears[yearInCycle];
            }
            return cycleYears[yearInCycle + 1] - cycleYears[yearInCycle];
        }
        return HijrahDate.isLeapYear(year) ? 355 : 354;
    }

    @Override
    public int lengthOfYear() {
        return HijrahDate.getYearLength(this.yearOfEra);
    }

    static int getMaximumDayOfMonth() {
        return ADJUSTED_MAX_VALUES[5];
    }

    static int getSmallestMaximumDayOfMonth() {
        return ADJUSTED_LEAST_MAX_VALUES[5];
    }

    static int getMaximumDayOfYear() {
        return ADJUSTED_MAX_VALUES[6];
    }

    static int getSmallestMaximumDayOfYear() {
        return ADJUSTED_LEAST_MAX_VALUES[6];
    }

    private static void addDeviationAsHijrah(int startYear, int startMonth, int endYear, int endMonth, int offset) {
        if (startYear < 1) {
            throw new IllegalArgumentException("startYear < 1");
        }
        if (endYear < 1) {
            throw new IllegalArgumentException("endYear < 1");
        }
        if (startMonth < 0 || startMonth > 11) {
            throw new IllegalArgumentException("startMonth < 0 || startMonth > 11");
        }
        if (endMonth < 0 || endMonth > 11) {
            throw new IllegalArgumentException("endMonth < 0 || endMonth > 11");
        }
        if (endYear > 9999) {
            throw new IllegalArgumentException("endYear > 9999");
        }
        if (endYear < startYear) {
            throw new IllegalArgumentException("startYear > endYear");
        }
        if (endYear == startYear && endMonth < startMonth) {
            throw new IllegalArgumentException("startYear == endYear && endMonth < startMonth");
        }
        boolean isStartYLeap = HijrahDate.isLeapYear(startYear);
        Integer[] orgStartMonthNums = ADJUSTED_MONTH_DAYS.get(new Integer(startYear));
        if (orgStartMonthNums == null) {
            int l;
            if (isStartYLeap) {
                orgStartMonthNums = new Integer[LEAP_NUM_DAYS.length];
                for (l = 0; l < LEAP_NUM_DAYS.length; ++l) {
                    orgStartMonthNums[l] = new Integer(LEAP_NUM_DAYS[l]);
                }
            } else {
                orgStartMonthNums = new Integer[NUM_DAYS.length];
                for (l = 0; l < NUM_DAYS.length; ++l) {
                    orgStartMonthNums[l] = new Integer(NUM_DAYS[l]);
                }
            }
        }
        Integer[] newStartMonthNums = new Integer[orgStartMonthNums.length];
        for (int month = 0; month < 12; ++month) {
            newStartMonthNums[month] = month > startMonth ? new Integer(orgStartMonthNums[month] - offset) : new Integer(orgStartMonthNums[month]);
        }
        ADJUSTED_MONTH_DAYS.put(new Integer(startYear), newStartMonthNums);
        Integer[] orgStartMonthLengths = ADJUSTED_MONTH_LENGTHS.get(new Integer(startYear));
        if (orgStartMonthLengths == null) {
            int l;
            if (isStartYLeap) {
                orgStartMonthLengths = new Integer[LEAP_MONTH_LENGTH.length];
                for (l = 0; l < LEAP_MONTH_LENGTH.length; ++l) {
                    orgStartMonthLengths[l] = new Integer(LEAP_MONTH_LENGTH[l]);
                }
            } else {
                orgStartMonthLengths = new Integer[MONTH_LENGTH.length];
                for (l = 0; l < MONTH_LENGTH.length; ++l) {
                    orgStartMonthLengths[l] = new Integer(MONTH_LENGTH[l]);
                }
            }
        }
        Integer[] newStartMonthLengths = new Integer[orgStartMonthLengths.length];
        for (int month = 0; month < 12; ++month) {
            newStartMonthLengths[month] = month == startMonth ? new Integer(orgStartMonthLengths[month] - offset) : new Integer(orgStartMonthLengths[month]);
        }
        ADJUSTED_MONTH_LENGTHS.put(new Integer(startYear), newStartMonthLengths);
        if (startYear != endYear) {
            int j;
            int j2;
            int sCycleNumber = (startYear - 1) / 30;
            int sYearInCycle = (startYear - 1) % 30;
            Integer[] startCycles = ADJUSTED_CYCLE_YEARS.get(new Integer(sCycleNumber));
            if (startCycles == null) {
                startCycles = new Integer[CYCLEYEAR_START_DATE.length];
                for (j2 = 0; j2 < startCycles.length; ++j2) {
                    startCycles[j2] = new Integer(CYCLEYEAR_START_DATE[j2]);
                }
            }
            for (j2 = sYearInCycle + 1; j2 < CYCLEYEAR_START_DATE.length; ++j2) {
                startCycles[j2] = new Integer(startCycles[j2] - offset);
            }
            ADJUSTED_CYCLE_YEARS.put(new Integer(sCycleNumber), startCycles);
            int sYearInMaxY = (startYear - 1) / 30;
            int sEndInMaxY = (endYear - 1) / 30;
            if (sYearInMaxY != sEndInMaxY) {
                int j3;
                for (j3 = sYearInMaxY + 1; j3 < ADJUSTED_CYCLES.length; ++j3) {
                    HijrahDate.ADJUSTED_CYCLES[j3] = new Long(ADJUSTED_CYCLES[j3] - (long)offset);
                }
                for (j3 = sEndInMaxY + 1; j3 < ADJUSTED_CYCLES.length; ++j3) {
                    HijrahDate.ADJUSTED_CYCLES[j3] = new Long(ADJUSTED_CYCLES[j3] + (long)offset);
                }
            }
            int eCycleNumber = (endYear - 1) / 30;
            int sEndInCycle = (endYear - 1) % 30;
            Integer[] endCycles = ADJUSTED_CYCLE_YEARS.get(new Integer(eCycleNumber));
            if (endCycles == null) {
                endCycles = new Integer[CYCLEYEAR_START_DATE.length];
                for (j = 0; j < endCycles.length; ++j) {
                    endCycles[j] = new Integer(CYCLEYEAR_START_DATE[j]);
                }
            }
            for (j = sEndInCycle + 1; j < CYCLEYEAR_START_DATE.length; ++j) {
                endCycles[j] = new Integer(endCycles[j] + offset);
            }
            ADJUSTED_CYCLE_YEARS.put(new Integer(eCycleNumber), endCycles);
        }
        boolean isEndYLeap = HijrahDate.isLeapYear(endYear);
        Integer[] orgEndMonthDays = ADJUSTED_MONTH_DAYS.get(new Integer(endYear));
        if (orgEndMonthDays == null) {
            if (isEndYLeap) {
                orgEndMonthDays = new Integer[LEAP_NUM_DAYS.length];
                for (int l = 0; l < LEAP_NUM_DAYS.length; ++l) {
                    orgEndMonthDays[l] = new Integer(LEAP_NUM_DAYS[l]);
                }
            } else {
                orgEndMonthDays = new Integer[NUM_DAYS.length];
                for (int l = 0; l < NUM_DAYS.length; ++l) {
                    orgEndMonthDays[l] = new Integer(NUM_DAYS[l]);
                }
            }
        }
        Integer[] newEndMonthDays = new Integer[orgEndMonthDays.length];
        for (int month = 0; month < 12; ++month) {
            newEndMonthDays[month] = month > endMonth ? new Integer(orgEndMonthDays[month] + offset) : new Integer(orgEndMonthDays[month]);
        }
        ADJUSTED_MONTH_DAYS.put(new Integer(endYear), newEndMonthDays);
        Integer[] orgEndMonthLengths = ADJUSTED_MONTH_LENGTHS.get(new Integer(endYear));
        if (orgEndMonthLengths == null) {
            int l;
            if (isEndYLeap) {
                orgEndMonthLengths = new Integer[LEAP_MONTH_LENGTH.length];
                for (l = 0; l < LEAP_MONTH_LENGTH.length; ++l) {
                    orgEndMonthLengths[l] = new Integer(LEAP_MONTH_LENGTH[l]);
                }
            } else {
                orgEndMonthLengths = new Integer[MONTH_LENGTH.length];
                for (l = 0; l < MONTH_LENGTH.length; ++l) {
                    orgEndMonthLengths[l] = new Integer(MONTH_LENGTH[l]);
                }
            }
        }
        Integer[] newEndMonthLengths = new Integer[orgEndMonthLengths.length];
        for (int month = 0; month < 12; ++month) {
            newEndMonthLengths[month] = month == endMonth ? new Integer(orgEndMonthLengths[month] + offset) : new Integer(orgEndMonthLengths[month]);
        }
        ADJUSTED_MONTH_LENGTHS.put(new Integer(endYear), newEndMonthLengths);
        Integer[] startMonthLengths = ADJUSTED_MONTH_LENGTHS.get(new Integer(startYear));
        Integer[] endMonthLengths = ADJUSTED_MONTH_LENGTHS.get(new Integer(endYear));
        Integer[] startMonthDays = ADJUSTED_MONTH_DAYS.get(new Integer(startYear));
        Integer[] endMonthDays = ADJUSTED_MONTH_DAYS.get(new Integer(endYear));
        int startMonthLength = startMonthLengths[startMonth];
        int endMonthLength = endMonthLengths[endMonth];
        int startMonthDay = startMonthDays[11] + startMonthLengths[11];
        int endMonthDay = endMonthDays[11] + endMonthLengths[11];
        int maxMonthLength = ADJUSTED_MAX_VALUES[5];
        int leastMaxMonthLength = ADJUSTED_LEAST_MAX_VALUES[5];
        if (maxMonthLength < startMonthLength) {
            maxMonthLength = startMonthLength;
        }
        if (maxMonthLength < endMonthLength) {
            maxMonthLength = endMonthLength;
        }
        HijrahDate.ADJUSTED_MAX_VALUES[5] = new Integer(maxMonthLength);
        if (leastMaxMonthLength > startMonthLength) {
            leastMaxMonthLength = startMonthLength;
        }
        if (leastMaxMonthLength > endMonthLength) {
            leastMaxMonthLength = endMonthLength;
        }
        HijrahDate.ADJUSTED_LEAST_MAX_VALUES[5] = new Integer(leastMaxMonthLength);
        int maxMonthDay = ADJUSTED_MAX_VALUES[6];
        int leastMaxMonthDay = ADJUSTED_LEAST_MAX_VALUES[6];
        if (maxMonthDay < startMonthDay) {
            maxMonthDay = startMonthDay;
        }
        if (maxMonthDay < endMonthDay) {
            maxMonthDay = endMonthDay;
        }
        HijrahDate.ADJUSTED_MAX_VALUES[6] = new Integer(maxMonthDay);
        if (leastMaxMonthDay > startMonthDay) {
            leastMaxMonthDay = startMonthDay;
        }
        if (leastMaxMonthDay > endMonthDay) {
            leastMaxMonthDay = endMonthDay;
        }
        HijrahDate.ADJUSTED_LEAST_MAX_VALUES[6] = new Integer(leastMaxMonthDay);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void readDeviationConfig() throws IOException, ParseException {
        InputStream is = HijrahDate.getConfigFileInputStream();
        if (is != null) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(is));
                String line = "";
                int num = 0;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    HijrahDate.parseLine(line, ++num);
                }
            }
            finally {
                if (br != null) {
                    br.close();
                }
            }
        }
    }

    private static void parseLine(String line, int num) throws ParseException {
        StringTokenizer st = new StringTokenizer(line, ";");
        while (st.hasMoreTokens()) {
            String deviationElement = st.nextToken();
            int offsetIndex = deviationElement.indexOf(58);
            if (offsetIndex != -1) {
                int offset;
                String offsetString = deviationElement.substring(offsetIndex + 1, deviationElement.length());
                try {
                    offset = Integer.parseInt(offsetString);
                }
                catch (NumberFormatException ex) {
                    throw new ParseException("Offset is not properly set at line " + num + ".", num);
                }
                int separatorIndex = deviationElement.indexOf(45);
                if (separatorIndex != -1) {
                    String startDateStg = deviationElement.substring(0, separatorIndex);
                    String endDateStg = deviationElement.substring(separatorIndex + 1, offsetIndex);
                    int startDateYearSepIndex = startDateStg.indexOf(47);
                    int endDateYearSepIndex = endDateStg.indexOf(47);
                    int startYear = -1;
                    int endYear = -1;
                    int startMonth = -1;
                    int endMonth = -1;
                    if (startDateYearSepIndex != -1) {
                        String startYearStg = startDateStg.substring(0, startDateYearSepIndex);
                        String startMonthStg = startDateStg.substring(startDateYearSepIndex + 1, startDateStg.length());
                        try {
                            startYear = Integer.parseInt(startYearStg);
                        }
                        catch (NumberFormatException ex) {
                            throw new ParseException("Start year is not properly set at line " + num + ".", num);
                        }
                        try {
                            startMonth = Integer.parseInt(startMonthStg);
                        }
                        catch (NumberFormatException ex) {
                            throw new ParseException("Start month is not properly set at line " + num + ".", num);
                        }
                    }
                    throw new ParseException("Start year/month has incorrect format at line " + num + ".", num);
                    if (endDateYearSepIndex != -1) {
                        String endYearStg = endDateStg.substring(0, endDateYearSepIndex);
                        String endMonthStg = endDateStg.substring(endDateYearSepIndex + 1, endDateStg.length());
                        try {
                            endYear = Integer.parseInt(endYearStg);
                        }
                        catch (NumberFormatException ex) {
                            throw new ParseException("End year is not properly set at line " + num + ".", num);
                        }
                        try {
                            endMonth = Integer.parseInt(endMonthStg);
                        }
                        catch (NumberFormatException ex) {
                            throw new ParseException("End month is not properly set at line " + num + ".", num);
                        }
                    }
                    throw new ParseException("End year/month has incorrect format at line " + num + ".", num);
                    if (startYear != -1 && startMonth != -1 && endYear != -1 && endMonth != -1) {
                        HijrahDate.addDeviationAsHijrah(startYear, startMonth, endYear, endMonth, offset);
                        continue;
                    }
                    throw new ParseException("Unknown error at line " + num + ".", num);
                }
                throw new ParseException("Start and end year/month has incorrect format at line " + num + ".", num);
            }
            throw new ParseException("Offset has incorrect format at line " + num + ".", num);
        }
    }

    private static InputStream getConfigFileInputStream() throws IOException {
        String dir;
        String fileName = System.getProperty("org.threeten.bp.i18n.HijrahDate.deviationConfigFile");
        if (fileName == null) {
            fileName = DEFAULT_CONFIG_FILENAME;
        }
        if ((dir = System.getProperty("org.threeten.bp.i18n.HijrahDate.deviationConfigDir")) != null) {
            File file;
            if (dir.length() != 0 || !dir.endsWith(System.getProperty("file.separator"))) {
                dir = dir + System.getProperty("file.separator");
            }
            if ((file = new File(dir + FILE_SEP + fileName)).exists()) {
                return new FileInputStream(file);
            }
            return null;
        }
        String classPath = System.getProperty("java.class.path");
        StringTokenizer st = new StringTokenizer(classPath, PATH_SEP);
        while (st.hasMoreTokens()) {
            ZipFile zip2;
            String path = st.nextToken();
            File file = new File(path);
            if (!file.exists()) continue;
            if (file.isDirectory()) {
                File f = new File(path + FILE_SEP + DEFAULT_CONFIG_PATH, fileName);
                if (!f.exists()) continue;
                return new FileInputStream(path + FILE_SEP + DEFAULT_CONFIG_PATH + FILE_SEP + fileName);
            }
            try {
                zip2 = new ZipFile(file);
            }
            catch (IOException ioe) {
                zip2 = null;
            }
            if (zip2 == null) continue;
            String targetFile = DEFAULT_CONFIG_PATH + FILE_SEP + fileName;
            ZipEntry entry = zip2.getEntry(targetFile);
            if (entry == null) {
                if (FILE_SEP == '/') {
                    targetFile = targetFile.replace('/', '\\');
                } else if (FILE_SEP == '\\') {
                    targetFile = targetFile.replace('\\', '/');
                }
                entry = zip2.getEntry(targetFile);
            }
            if (entry == null) continue;
            return zip2.getInputStream(entry);
        }
        return null;
    }

    private Object writeReplace() {
        return new Ser(3, this);
    }

    void writeExternal(DataOutput out) throws IOException {
        out.writeInt(this.get(ChronoField.YEAR));
        out.writeByte(this.get(ChronoField.MONTH_OF_YEAR));
        out.writeByte(this.get(ChronoField.DAY_OF_MONTH));
    }

    static ChronoLocalDate readExternal(DataInput in) throws IOException {
        int year = in.readInt();
        byte month = in.readByte();
        byte dayOfMonth = in.readByte();
        return HijrahChronology.INSTANCE.date(year, month, dayOfMonth);
    }

    static {
        int i;
        NUM_DAYS = new int[]{0, 30, 59, 89, 118, 148, 177, 207, 236, 266, 295, 325};
        LEAP_NUM_DAYS = new int[]{0, 30, 59, 89, 118, 148, 177, 207, 236, 266, 295, 325};
        MONTH_LENGTH = new int[]{30, 29, 30, 29, 30, 29, 30, 29, 30, 29, 30, 29};
        LEAP_MONTH_LENGTH = new int[]{30, 29, 30, 29, 30, 29, 30, 29, 30, 29, 30, 30};
        MIN_VALUES = new int[]{0, 1, 0, 1, 0, 1, 1};
        LEAST_MAX_VALUES = new int[]{1, 9999, 11, 51, 5, 29, 354};
        MAX_VALUES = new int[]{1, 9999, 11, 52, 6, 30, 355};
        CYCLEYEAR_START_DATE = new int[]{0, 354, 709, 1063, 1417, 1772, 2126, 2481, 2835, 3189, 3544, 3898, 4252, 4607, 4961, 5315, 5670, 6024, 6379, 6733, 7087, 7442, 7796, 8150, 8505, 8859, 9214, 9568, 9922, 10277};
        FILE_SEP = File.separatorChar;
        PATH_SEP = File.pathSeparator;
        DEFAULT_CONFIG_PATH = "org" + FILE_SEP + "threeten" + FILE_SEP + "bp" + FILE_SEP + "chrono";
        ADJUSTED_MONTH_DAYS = new HashMap();
        ADJUSTED_MONTH_LENGTHS = new HashMap();
        ADJUSTED_CYCLE_YEARS = new HashMap();
        DEFAULT_MONTH_DAYS = new Integer[NUM_DAYS.length];
        for (i = 0; i < NUM_DAYS.length; ++i) {
            HijrahDate.DEFAULT_MONTH_DAYS[i] = new Integer(NUM_DAYS[i]);
        }
        DEFAULT_LEAP_MONTH_DAYS = new Integer[LEAP_NUM_DAYS.length];
        for (i = 0; i < LEAP_NUM_DAYS.length; ++i) {
            HijrahDate.DEFAULT_LEAP_MONTH_DAYS[i] = new Integer(LEAP_NUM_DAYS[i]);
        }
        DEFAULT_MONTH_LENGTHS = new Integer[MONTH_LENGTH.length];
        for (i = 0; i < MONTH_LENGTH.length; ++i) {
            HijrahDate.DEFAULT_MONTH_LENGTHS[i] = new Integer(MONTH_LENGTH[i]);
        }
        DEFAULT_LEAP_MONTH_LENGTHS = new Integer[LEAP_MONTH_LENGTH.length];
        for (i = 0; i < LEAP_MONTH_LENGTH.length; ++i) {
            HijrahDate.DEFAULT_LEAP_MONTH_LENGTHS[i] = new Integer(LEAP_MONTH_LENGTH[i]);
        }
        DEFAULT_CYCLE_YEARS = new Integer[CYCLEYEAR_START_DATE.length];
        for (i = 0; i < CYCLEYEAR_START_DATE.length; ++i) {
            HijrahDate.DEFAULT_CYCLE_YEARS[i] = new Integer(CYCLEYEAR_START_DATE[i]);
        }
        ADJUSTED_CYCLES = new Long[334];
        for (i = 0; i < ADJUSTED_CYCLES.length; ++i) {
            HijrahDate.ADJUSTED_CYCLES[i] = new Long(10631 * i);
        }
        ADJUSTED_MIN_VALUES = new Integer[MIN_VALUES.length];
        for (i = 0; i < MIN_VALUES.length; ++i) {
            HijrahDate.ADJUSTED_MIN_VALUES[i] = new Integer(MIN_VALUES[i]);
        }
        ADJUSTED_LEAST_MAX_VALUES = new Integer[LEAST_MAX_VALUES.length];
        for (i = 0; i < LEAST_MAX_VALUES.length; ++i) {
            HijrahDate.ADJUSTED_LEAST_MAX_VALUES[i] = new Integer(LEAST_MAX_VALUES[i]);
        }
        ADJUSTED_MAX_VALUES = new Integer[MAX_VALUES.length];
        for (i = 0; i < MAX_VALUES.length; ++i) {
            HijrahDate.ADJUSTED_MAX_VALUES[i] = new Integer(MAX_VALUES[i]);
        }
        try {
            HijrahDate.readDeviationConfig();
        }
        catch (IOException e) {
        }
        catch (ParseException parseException) {
            // empty catch block
        }
    }
}

