/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
import org.threeten.bp.chrono.ThaiBuddhistDate;
import org.threeten.bp.chrono.ThaiBuddhistEra;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.ValueRange;

public final class ThaiBuddhistChronology
extends Chronology
implements Serializable {
    public static final ThaiBuddhistChronology INSTANCE = new ThaiBuddhistChronology();
    private static final long serialVersionUID = 2775954514031616474L;
    static final int YEARS_DIFFERENCE = 543;
    private static final HashMap<String, String[]> ERA_NARROW_NAMES = new HashMap();
    private static final HashMap<String, String[]> ERA_SHORT_NAMES = new HashMap();
    private static final HashMap<String, String[]> ERA_FULL_NAMES = new HashMap();
    private static final String FALLBACK_LANGUAGE = "en";
    private static final String TARGET_LANGUAGE = "th";

    private ThaiBuddhistChronology() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    @Override
    public String getId() {
        return "ThaiBuddhist";
    }

    @Override
    public String getCalendarType() {
        return "buddhist";
    }

    @Override
    public ThaiBuddhistDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
        return (ThaiBuddhistDate)super.date(era, yearOfEra, month, dayOfMonth);
    }

    @Override
    public ThaiBuddhistDate date(int prolepticYear, int month, int dayOfMonth) {
        return new ThaiBuddhistDate(LocalDate.of(prolepticYear - 543, month, dayOfMonth));
    }

    @Override
    public ThaiBuddhistDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
        return (ThaiBuddhistDate)super.dateYearDay(era, yearOfEra, dayOfYear);
    }

    @Override
    public ThaiBuddhistDate dateYearDay(int prolepticYear, int dayOfYear) {
        return new ThaiBuddhistDate(LocalDate.ofYearDay(prolepticYear - 543, dayOfYear));
    }

    @Override
    public ThaiBuddhistDate dateEpochDay(long epochDay) {
        return new ThaiBuddhistDate(LocalDate.ofEpochDay(epochDay));
    }

    @Override
    public ThaiBuddhistDate date(TemporalAccessor temporal) {
        if (temporal instanceof ThaiBuddhistDate) {
            return (ThaiBuddhistDate)temporal;
        }
        return new ThaiBuddhistDate(LocalDate.from(temporal));
    }

    public ChronoLocalDateTime<ThaiBuddhistDate> localDateTime(TemporalAccessor temporal) {
        return super.localDateTime(temporal);
    }

    public ChronoZonedDateTime<ThaiBuddhistDate> zonedDateTime(TemporalAccessor temporal) {
        return super.zonedDateTime(temporal);
    }

    public ChronoZonedDateTime<ThaiBuddhistDate> zonedDateTime(Instant instant, ZoneId zone) {
        return super.zonedDateTime(instant, zone);
    }

    @Override
    public ThaiBuddhistDate dateNow() {
        return (ThaiBuddhistDate)super.dateNow();
    }

    @Override
    public ThaiBuddhistDate dateNow(ZoneId zone) {
        return (ThaiBuddhistDate)super.dateNow(zone);
    }

    @Override
    public ThaiBuddhistDate dateNow(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        return (ThaiBuddhistDate)super.dateNow(clock);
    }

    @Override
    public boolean isLeapYear(long prolepticYear) {
        return IsoChronology.INSTANCE.isLeapYear(prolepticYear - 543L);
    }

    @Override
    public int prolepticYear(Era era, int yearOfEra) {
        if (!(era instanceof ThaiBuddhistEra)) {
            throw new ClassCastException("Era must be BuddhistEra");
        }
        return era == ThaiBuddhistEra.BE ? yearOfEra : 1 - yearOfEra;
    }

    @Override
    public ThaiBuddhistEra eraOf(int eraValue) {
        return ThaiBuddhistEra.of(eraValue);
    }

    @Override
    public List<Era> eras() {
        return Arrays.asList(ThaiBuddhistEra.values());
    }

    @Override
    public ValueRange range(ChronoField field2) {
        switch (field2) {
            case PROLEPTIC_MONTH: {
                ValueRange range2 = ChronoField.PROLEPTIC_MONTH.range();
                return ValueRange.of(range2.getMinimum() + 6516L, range2.getMaximum() + 6516L);
            }
            case YEAR_OF_ERA: {
                ValueRange range3 = ChronoField.YEAR.range();
                return ValueRange.of(1L, -(range3.getMinimum() + 543L) + 1L, range3.getMaximum() + 543L);
            }
            case YEAR: {
                ValueRange range4 = ChronoField.YEAR.range();
                return ValueRange.of(range4.getMinimum() + 543L, range4.getMaximum() + 543L);
            }
        }
        return field2.range();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public ThaiBuddhistDate resolveDate(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
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
        if ((yoeLong = fieldValues.remove(ChronoField.YEAR_OF_ERA)) != null) {
            Long era;
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.YEAR_OF_ERA.checkValidValue(yoeLong);
            }
            if ((era = fieldValues.remove(ChronoField.ERA)) == null) {
                Long year = fieldValues.get(ChronoField.YEAR);
                if (resolverStyle == ResolverStyle.STRICT) {
                    if (year != null) {
                        this.updateResolveMap(fieldValues, ChronoField.YEAR, year > 0L ? yoeLong : Jdk8Methods.safeSubtract(1L, yoeLong));
                    } else {
                        fieldValues.put(ChronoField.YEAR_OF_ERA, yoeLong);
                    }
                } else {
                    this.updateResolveMap(fieldValues, ChronoField.YEAR, year == null || year > 0L ? yoeLong : Jdk8Methods.safeSubtract(1L, yoeLong));
                }
            } else if (era == 1L) {
                this.updateResolveMap(fieldValues, ChronoField.YEAR, yoeLong);
            } else {
                if (era != 0L) throw new DateTimeException("Invalid value for era: " + era);
                this.updateResolveMap(fieldValues, ChronoField.YEAR, Jdk8Methods.safeSubtract(1L, yoeLong));
            }
        } else if (fieldValues.containsKey(ChronoField.ERA)) {
            ChronoField.ERA.checkValidValue(fieldValues.get(ChronoField.ERA));
        }
        if (!fieldValues.containsKey(ChronoField.YEAR)) return null;
        if (fieldValues.containsKey(ChronoField.MONTH_OF_YEAR)) {
            if (fieldValues.containsKey(ChronoField.DAY_OF_MONTH)) {
                int y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
                if (resolverStyle == ResolverStyle.LENIENT) {
                    long months = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.MONTH_OF_YEAR), 1L);
                    long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_MONTH), 1L);
                    return this.date(y, 1, 1).plusMonths(months).plusDays(days);
                }
                int moy = this.range(ChronoField.MONTH_OF_YEAR).checkValidIntValue(fieldValues.remove(ChronoField.MONTH_OF_YEAR), ChronoField.MONTH_OF_YEAR);
                int dom = this.range(ChronoField.DAY_OF_MONTH).checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_MONTH), ChronoField.DAY_OF_MONTH);
                if (resolverStyle != ResolverStyle.SMART || dom <= 28) return this.date(y, moy, dom);
                dom = Math.min(dom, this.date(y, moy, 1).lengthOfMonth());
                return this.date(y, moy, dom);
            }
            if (fieldValues.containsKey(ChronoField.ALIGNED_WEEK_OF_MONTH)) {
                if (fieldValues.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH)) {
                    int y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long months = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.MONTH_OF_YEAR), 1L);
                        long weeks = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_MONTH), 1L);
                        long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH), 1L);
                        return this.date(y, 1, 1).plus(months, ChronoUnit.MONTHS).plus(weeks, ChronoUnit.WEEKS).plus(days, ChronoUnit.DAYS);
                    }
                    int moy = ChronoField.MONTH_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.MONTH_OF_YEAR));
                    int aw = ChronoField.ALIGNED_WEEK_OF_MONTH.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_MONTH));
                    int ad = ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
                    ThaiBuddhistDate date = this.date(y, moy, 1).plus((aw - 1) * 7 + (ad - 1), ChronoUnit.DAYS);
                    if (resolverStyle != ResolverStyle.STRICT || date.get(ChronoField.MONTH_OF_YEAR) == moy) return date;
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                }
                if (fieldValues.containsKey(ChronoField.DAY_OF_WEEK)) {
                    int y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long months = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.MONTH_OF_YEAR), 1L);
                        long weeks = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_MONTH), 1L);
                        long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_WEEK), 1L);
                        return this.date(y, 1, 1).plus(months, ChronoUnit.MONTHS).plus(weeks, ChronoUnit.WEEKS).plus(days, ChronoUnit.DAYS);
                    }
                    int moy = ChronoField.MONTH_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.MONTH_OF_YEAR));
                    int aw = ChronoField.ALIGNED_WEEK_OF_MONTH.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_MONTH));
                    int dow = ChronoField.DAY_OF_WEEK.checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_WEEK));
                    ThaiBuddhistDate date = this.date(y, moy, 1).plus(aw - 1, ChronoUnit.WEEKS).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(dow)));
                    if (resolverStyle != ResolverStyle.STRICT || date.get(ChronoField.MONTH_OF_YEAR) == moy) return date;
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                }
            }
        }
        if (fieldValues.containsKey(ChronoField.DAY_OF_YEAR)) {
            int y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
            if (resolverStyle == ResolverStyle.LENIENT) {
                long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_YEAR), 1L);
                return this.dateYearDay(y, 1).plusDays(days);
            }
            int doy = ChronoField.DAY_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_YEAR));
            return this.dateYearDay(y, doy);
        }
        if (!fieldValues.containsKey(ChronoField.ALIGNED_WEEK_OF_YEAR)) return null;
        if (fieldValues.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR)) {
            int y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
            if (resolverStyle == ResolverStyle.LENIENT) {
                long weeks = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_YEAR), 1L);
                long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR), 1L);
                return this.date(y, 1, 1).plus(weeks, ChronoUnit.WEEKS).plus(days, ChronoUnit.DAYS);
            }
            int aw = ChronoField.ALIGNED_WEEK_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_YEAR));
            int ad = ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
            ThaiBuddhistDate date = this.date(y, 1, 1).plusDays((aw - 1) * 7 + (ad - 1));
            if (resolverStyle != ResolverStyle.STRICT || date.get(ChronoField.YEAR) == y) return date;
            throw new DateTimeException("Strict mode rejected date parsed to a different year");
        }
        if (!fieldValues.containsKey(ChronoField.DAY_OF_WEEK)) return null;
        int y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
        if (resolverStyle == ResolverStyle.LENIENT) {
            long weeks = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_YEAR), 1L);
            long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_WEEK), 1L);
            return this.date(y, 1, 1).plus(weeks, ChronoUnit.WEEKS).plus(days, ChronoUnit.DAYS);
        }
        int aw = ChronoField.ALIGNED_WEEK_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_YEAR));
        int dow = ChronoField.DAY_OF_WEEK.checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_WEEK));
        ThaiBuddhistDate date = this.date(y, 1, 1).plus(aw - 1, ChronoUnit.WEEKS).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(dow)));
        if (resolverStyle != ResolverStyle.STRICT || date.get(ChronoField.YEAR) == y) return date;
        throw new DateTimeException("Strict mode rejected date parsed to a different month");
    }

    static {
        ERA_NARROW_NAMES.put(FALLBACK_LANGUAGE, new String[]{"BB", "BE"});
        ERA_NARROW_NAMES.put(TARGET_LANGUAGE, new String[]{"BB", "BE"});
        ERA_SHORT_NAMES.put(FALLBACK_LANGUAGE, new String[]{"B.B.", "B.E."});
        ERA_SHORT_NAMES.put(TARGET_LANGUAGE, new String[]{"\u0e1e.\u0e28.", "\u0e1b\u0e35\u0e01\u0e48\u0e2d\u0e19\u0e04\u0e23\u0e34\u0e2a\u0e15\u0e4c\u0e01\u0e32\u0e25\u0e17\u0e35\u0e48"});
        ERA_FULL_NAMES.put(FALLBACK_LANGUAGE, new String[]{"Before Buddhist", "Budhhist Era"});
        ERA_FULL_NAMES.put(TARGET_LANGUAGE, new String[]{"\u0e1e\u0e38\u0e17\u0e18\u0e28\u0e31\u0e01\u0e23\u0e32\u0e0a", "\u0e1b\u0e35\u0e01\u0e48\u0e2d\u0e19\u0e04\u0e23\u0e34\u0e2a\u0e15\u0e4c\u0e01\u0e32\u0e25\u0e17\u0e35\u0e48"});
    }
}

