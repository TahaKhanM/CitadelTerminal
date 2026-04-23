/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.chrono.ChronoZonedDateTime;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.Era;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.chrono.JapaneseDate;
import org.threeten.bp.chrono.JapaneseEra;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.ValueRange;

public final class JapaneseChronology
extends Chronology
implements Serializable {
    static final Locale LOCALE = new Locale("ja", "JP", "JP");
    public static final JapaneseChronology INSTANCE = new JapaneseChronology();
    private static final long serialVersionUID = 459996390165777884L;
    private static final Map<String, String[]> ERA_NARROW_NAMES = new HashMap<String, String[]>();
    private static final Map<String, String[]> ERA_SHORT_NAMES = new HashMap<String, String[]>();
    private static final Map<String, String[]> ERA_FULL_NAMES = new HashMap<String, String[]>();
    private static final String FALLBACK_LANGUAGE = "en";
    private static final String TARGET_LANGUAGE = "ja";

    private JapaneseChronology() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    @Override
    public String getId() {
        return "Japanese";
    }

    @Override
    public String getCalendarType() {
        return "japanese";
    }

    @Override
    public JapaneseDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
        if (!(era instanceof JapaneseEra)) {
            throw new ClassCastException("Era must be JapaneseEra");
        }
        return JapaneseDate.of((JapaneseEra)era, yearOfEra, month, dayOfMonth);
    }

    @Override
    public JapaneseDate date(int prolepticYear, int month, int dayOfMonth) {
        return new JapaneseDate(LocalDate.of(prolepticYear, month, dayOfMonth));
    }

    @Override
    public JapaneseDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
        if (!(era instanceof JapaneseEra)) {
            throw new ClassCastException("Era must be JapaneseEra");
        }
        return JapaneseDate.ofYearDay((JapaneseEra)era, yearOfEra, dayOfYear);
    }

    @Override
    public JapaneseDate dateYearDay(int prolepticYear, int dayOfYear) {
        LocalDate date = LocalDate.ofYearDay(prolepticYear, dayOfYear);
        return this.date(prolepticYear, date.getMonthValue(), date.getDayOfMonth());
    }

    @Override
    public JapaneseDate dateEpochDay(long epochDay) {
        return new JapaneseDate(LocalDate.ofEpochDay(epochDay));
    }

    @Override
    public JapaneseDate date(TemporalAccessor temporal) {
        if (temporal instanceof JapaneseDate) {
            return (JapaneseDate)temporal;
        }
        return new JapaneseDate(LocalDate.from(temporal));
    }

    public ChronoLocalDateTime<JapaneseDate> localDateTime(TemporalAccessor temporal) {
        return super.localDateTime(temporal);
    }

    public ChronoZonedDateTime<JapaneseDate> zonedDateTime(TemporalAccessor temporal) {
        return super.zonedDateTime(temporal);
    }

    public ChronoZonedDateTime<JapaneseDate> zonedDateTime(Instant instant, ZoneId zone) {
        return super.zonedDateTime(instant, zone);
    }

    @Override
    public JapaneseDate dateNow() {
        return (JapaneseDate)super.dateNow();
    }

    @Override
    public JapaneseDate dateNow(ZoneId zone) {
        return (JapaneseDate)super.dateNow(zone);
    }

    @Override
    public JapaneseDate dateNow(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        return (JapaneseDate)super.dateNow(clock);
    }

    @Override
    public boolean isLeapYear(long prolepticYear) {
        return IsoChronology.INSTANCE.isLeapYear(prolepticYear);
    }

    @Override
    public int prolepticYear(Era era, int yearOfEra) {
        if (!(era instanceof JapaneseEra)) {
            throw new ClassCastException("Era must be JapaneseEra");
        }
        JapaneseEra jera = (JapaneseEra)era;
        int isoYear = jera.startDate().getYear() + yearOfEra - 1;
        ValueRange range2 = ValueRange.of(1L, jera.endDate().getYear() - jera.startDate().getYear() + 1);
        range2.checkValidValue(yearOfEra, ChronoField.YEAR_OF_ERA);
        return isoYear;
    }

    @Override
    public JapaneseEra eraOf(int eraValue) {
        return JapaneseEra.of(eraValue);
    }

    @Override
    public List<Era> eras() {
        return Arrays.asList(JapaneseEra.values());
    }

    @Override
    public ValueRange range(ChronoField field2) {
        switch (field2) {
            case DAY_OF_MONTH: 
            case DAY_OF_WEEK: 
            case MICRO_OF_DAY: 
            case MICRO_OF_SECOND: 
            case HOUR_OF_DAY: 
            case HOUR_OF_AMPM: 
            case MINUTE_OF_DAY: 
            case MINUTE_OF_HOUR: 
            case SECOND_OF_DAY: 
            case SECOND_OF_MINUTE: 
            case MILLI_OF_DAY: 
            case MILLI_OF_SECOND: 
            case NANO_OF_DAY: 
            case NANO_OF_SECOND: 
            case CLOCK_HOUR_OF_DAY: 
            case CLOCK_HOUR_OF_AMPM: 
            case EPOCH_DAY: 
            case PROLEPTIC_MONTH: {
                return field2.range();
            }
        }
        Calendar jcal = Calendar.getInstance(LOCALE);
        switch (field2) {
            case ERA: {
                JapaneseEra[] eras = JapaneseEra.values();
                return ValueRange.of(eras[0].getValue(), eras[eras.length - 1].getValue());
            }
            case YEAR: {
                JapaneseEra[] eras = JapaneseEra.values();
                return ValueRange.of(JapaneseDate.MIN_DATE.getYear(), eras[eras.length - 1].endDate().getYear());
            }
            case YEAR_OF_ERA: {
                JapaneseEra[] eras = JapaneseEra.values();
                int maxIso = eras[eras.length - 1].endDate().getYear();
                int maxJapanese = maxIso - eras[eras.length - 1].startDate().getYear() + 1;
                int min2 = Integer.MAX_VALUE;
                for (int i = 0; i < eras.length; ++i) {
                    min2 = Math.min(min2, eras[i].endDate().getYear() - eras[i].startDate().getYear() + 1);
                }
                return ValueRange.of(1L, 6L, min2, maxJapanese);
            }
            case MONTH_OF_YEAR: {
                return ValueRange.of(jcal.getMinimum(2) + 1, jcal.getGreatestMinimum(2) + 1, jcal.getLeastMaximum(2) + 1, jcal.getMaximum(2) + 1);
            }
            case DAY_OF_YEAR: {
                JapaneseEra[] eras = JapaneseEra.values();
                int min3 = 366;
                for (int i = 0; i < eras.length; ++i) {
                    min3 = Math.min(min3, eras[i].startDate().lengthOfYear() - eras[i].startDate().getDayOfYear() + 1);
                }
                return ValueRange.of(1L, min3, 366L);
            }
        }
        throw new UnsupportedOperationException("Unimplementable field: " + field2);
    }

    @Override
    public JapaneseDate resolveDate(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
        Long yoeLong;
        if (fieldValues.containsKey(ChronoField.EPOCH_DAY)) {
            return this.dateEpochDay(fieldValues.remove(ChronoField.EPOCH_DAY));
        }
        Long prolepticMonth = fieldValues.remove(ChronoField.PROLEPTIC_MONTH);
        if (prolepticMonth != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.PROLEPTIC_MONTH.checkValidValue(prolepticMonth);
            }
            this.updateResolveMap(fieldValues, ChronoField.MONTH_OF_YEAR, Jdk8Methods.floorMod((long)prolepticMonth, 12) + 1);
            this.updateResolveMap(fieldValues, ChronoField.YEAR, Jdk8Methods.floorDiv(prolepticMonth, 12L));
        }
        Long eraLong = fieldValues.get(ChronoField.ERA);
        JapaneseEra era = null;
        if (eraLong != null) {
            era = this.eraOf(this.range(ChronoField.ERA).checkValidIntValue(eraLong, ChronoField.ERA));
        }
        if ((yoeLong = fieldValues.get(ChronoField.YEAR_OF_ERA)) != null) {
            int yoe = this.range(ChronoField.YEAR_OF_ERA).checkValidIntValue(yoeLong, ChronoField.YEAR_OF_ERA);
            if (era == null && resolverStyle != ResolverStyle.STRICT && !fieldValues.containsKey(ChronoField.YEAR)) {
                List<Era> eras = this.eras();
                era = (JapaneseEra)eras.get(eras.size() - 1);
            }
            if (era != null && fieldValues.containsKey(ChronoField.MONTH_OF_YEAR) && fieldValues.containsKey(ChronoField.DAY_OF_MONTH)) {
                fieldValues.remove(ChronoField.ERA);
                fieldValues.remove(ChronoField.YEAR_OF_ERA);
                return this.resolveEYMD(fieldValues, resolverStyle, era, yoe);
            }
            if (era != null && fieldValues.containsKey(ChronoField.DAY_OF_YEAR)) {
                fieldValues.remove(ChronoField.ERA);
                fieldValues.remove(ChronoField.YEAR_OF_ERA);
                return this.resolveEYD(fieldValues, resolverStyle, era, yoe);
            }
        }
        if (fieldValues.containsKey(ChronoField.YEAR)) {
            int y;
            if (fieldValues.containsKey(ChronoField.MONTH_OF_YEAR)) {
                if (fieldValues.containsKey(ChronoField.DAY_OF_MONTH)) {
                    y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long months = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.MONTH_OF_YEAR), 1L);
                        long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_MONTH), 1L);
                        return this.date(y, 1, 1).plusMonths(months).plusDays(days);
                    }
                    int moy = this.range(ChronoField.MONTH_OF_YEAR).checkValidIntValue(fieldValues.remove(ChronoField.MONTH_OF_YEAR), ChronoField.MONTH_OF_YEAR);
                    int dom = this.range(ChronoField.DAY_OF_MONTH).checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_MONTH), ChronoField.DAY_OF_MONTH);
                    if (resolverStyle == ResolverStyle.SMART && dom > 28) {
                        dom = Math.min(dom, this.date(y, moy, 1).lengthOfMonth());
                    }
                    return this.date(y, moy, dom);
                }
                if (fieldValues.containsKey(ChronoField.ALIGNED_WEEK_OF_MONTH)) {
                    if (fieldValues.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH)) {
                        y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
                        if (resolverStyle == ResolverStyle.LENIENT) {
                            long months = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.MONTH_OF_YEAR), 1L);
                            long weeks = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_MONTH), 1L);
                            long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH), 1L);
                            return this.date(y, 1, 1).plus(months, ChronoUnit.MONTHS).plus(weeks, ChronoUnit.WEEKS).plus(days, ChronoUnit.DAYS);
                        }
                        int moy = ChronoField.MONTH_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.MONTH_OF_YEAR));
                        int aw = ChronoField.ALIGNED_WEEK_OF_MONTH.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_MONTH));
                        int ad = ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
                        JapaneseDate date = this.date(y, moy, 1).plus((aw - 1) * 7 + (ad - 1), ChronoUnit.DAYS);
                        if (resolverStyle == ResolverStyle.STRICT && date.get(ChronoField.MONTH_OF_YEAR) != moy) {
                            throw new DateTimeException("Strict mode rejected date parsed to a different month");
                        }
                        return date;
                    }
                    if (fieldValues.containsKey(ChronoField.DAY_OF_WEEK)) {
                        y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
                        if (resolverStyle == ResolverStyle.LENIENT) {
                            long months = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.MONTH_OF_YEAR), 1L);
                            long weeks = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_MONTH), 1L);
                            long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_WEEK), 1L);
                            return this.date(y, 1, 1).plus(months, ChronoUnit.MONTHS).plus(weeks, ChronoUnit.WEEKS).plus(days, ChronoUnit.DAYS);
                        }
                        int moy = ChronoField.MONTH_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.MONTH_OF_YEAR));
                        int aw = ChronoField.ALIGNED_WEEK_OF_MONTH.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_MONTH));
                        int dow = ChronoField.DAY_OF_WEEK.checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_WEEK));
                        JapaneseDate date = this.date(y, moy, 1).plus(aw - 1, ChronoUnit.WEEKS).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(dow)));
                        if (resolverStyle == ResolverStyle.STRICT && date.get(ChronoField.MONTH_OF_YEAR) != moy) {
                            throw new DateTimeException("Strict mode rejected date parsed to a different month");
                        }
                        return date;
                    }
                }
            }
            if (fieldValues.containsKey(ChronoField.DAY_OF_YEAR)) {
                y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
                if (resolverStyle == ResolverStyle.LENIENT) {
                    long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_YEAR), 1L);
                    return this.dateYearDay(y, 1).plusDays(days);
                }
                int doy = ChronoField.DAY_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_YEAR));
                return this.dateYearDay(y, doy);
            }
            if (fieldValues.containsKey(ChronoField.ALIGNED_WEEK_OF_YEAR)) {
                if (fieldValues.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR)) {
                    y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long weeks = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_YEAR), 1L);
                        long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR), 1L);
                        return this.date(y, 1, 1).plus(weeks, ChronoUnit.WEEKS).plus(days, ChronoUnit.DAYS);
                    }
                    int aw = ChronoField.ALIGNED_WEEK_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_YEAR));
                    int ad = ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
                    JapaneseDate date = this.date(y, 1, 1).plusDays((aw - 1) * 7 + (ad - 1));
                    if (resolverStyle == ResolverStyle.STRICT && date.get(ChronoField.YEAR) != y) {
                        throw new DateTimeException("Strict mode rejected date parsed to a different year");
                    }
                    return date;
                }
                if (fieldValues.containsKey(ChronoField.DAY_OF_WEEK)) {
                    y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long weeks = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_YEAR), 1L);
                        long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_WEEK), 1L);
                        return this.date(y, 1, 1).plus(weeks, ChronoUnit.WEEKS).plus(days, ChronoUnit.DAYS);
                    }
                    int aw = ChronoField.ALIGNED_WEEK_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_YEAR));
                    int dow = ChronoField.DAY_OF_WEEK.checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_WEEK));
                    JapaneseDate date = this.date(y, 1, 1).plus(aw - 1, ChronoUnit.WEEKS).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(dow)));
                    if (resolverStyle == ResolverStyle.STRICT && date.get(ChronoField.YEAR) != y) {
                        throw new DateTimeException("Strict mode rejected date parsed to a different month");
                    }
                    return date;
                }
            }
        }
        return null;
    }

    private JapaneseDate resolveEYMD(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle, JapaneseEra era, int yoe) {
        if (resolverStyle == ResolverStyle.LENIENT) {
            int y = era.startDate().getYear() + yoe - 1;
            long months = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.MONTH_OF_YEAR), 1L);
            long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_MONTH), 1L);
            return this.date(y, 1, 1).plus(months, ChronoUnit.MONTHS).plus(days, ChronoUnit.DAYS);
        }
        int moy = this.range(ChronoField.MONTH_OF_YEAR).checkValidIntValue(fieldValues.remove(ChronoField.MONTH_OF_YEAR), ChronoField.MONTH_OF_YEAR);
        int dom = this.range(ChronoField.DAY_OF_MONTH).checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_MONTH), ChronoField.DAY_OF_MONTH);
        if (resolverStyle == ResolverStyle.SMART) {
            JapaneseDate jd;
            if (yoe < 1) {
                throw new DateTimeException("Invalid YearOfEra: " + yoe);
            }
            int y = era.startDate().getYear() + yoe - 1;
            if (dom > 28) {
                dom = Math.min(dom, this.date(y, moy, 1).lengthOfMonth());
            }
            if ((jd = this.date(y, moy, dom)).getEra() != era) {
                if (Math.abs(jd.getEra().getValue() - era.getValue()) > 1) {
                    throw new DateTimeException("Invalid Era/YearOfEra: " + era + " " + yoe);
                }
                if (jd.get(ChronoField.YEAR_OF_ERA) != 1 && yoe != 1) {
                    throw new DateTimeException("Invalid Era/YearOfEra: " + era + " " + yoe);
                }
            }
            return jd;
        }
        return this.date(era, yoe, moy, dom);
    }

    private JapaneseDate resolveEYD(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle, JapaneseEra era, int yoe) {
        if (resolverStyle == ResolverStyle.LENIENT) {
            int y = era.startDate().getYear() + yoe - 1;
            long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_YEAR), 1L);
            return this.dateYearDay(y, 1).plus(days, ChronoUnit.DAYS);
        }
        int doy = this.range(ChronoField.DAY_OF_YEAR).checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_YEAR), ChronoField.DAY_OF_YEAR);
        return this.dateYearDay(era, yoe, doy);
    }

    static {
        ERA_NARROW_NAMES.put(FALLBACK_LANGUAGE, new String[]{"Unknown", "K", "M", "T", "S", "H"});
        ERA_NARROW_NAMES.put(TARGET_LANGUAGE, new String[]{"Unknown", "K", "M", "T", "S", "H"});
        ERA_SHORT_NAMES.put(FALLBACK_LANGUAGE, new String[]{"Unknown", "K", "M", "T", "S", "H"});
        ERA_SHORT_NAMES.put(TARGET_LANGUAGE, new String[]{"Unknown", "\u6176", "\u660e", "\u5927", "\u662d", "\u5e73"});
        ERA_FULL_NAMES.put(FALLBACK_LANGUAGE, new String[]{"Unknown", "Keio", "Meiji", "Taisho", "Showa", "Heisei"});
        ERA_FULL_NAMES.put(TARGET_LANGUAGE, new String[]{"Unknown", "\u6176\u5fdc", "\u660e\u6cbb", "\u5927\u6b63", "\u662d\u548c", "\u5e73\u6210"});
    }
}

