/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.Month;
import org.threeten.bp.Year;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.Era;
import org.threeten.bp.chrono.IsoEra;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.ValueRange;

public final class IsoChronology
extends Chronology
implements Serializable {
    public static final IsoChronology INSTANCE = new IsoChronology();
    private static final long serialVersionUID = -1440403870442975015L;

    private IsoChronology() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    @Override
    public String getId() {
        return "ISO";
    }

    @Override
    public String getCalendarType() {
        return "iso8601";
    }

    @Override
    public LocalDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
        return this.date(this.prolepticYear(era, yearOfEra), month, dayOfMonth);
    }

    @Override
    public LocalDate date(int prolepticYear, int month, int dayOfMonth) {
        return LocalDate.of(prolepticYear, month, dayOfMonth);
    }

    @Override
    public LocalDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
        return this.dateYearDay(this.prolepticYear(era, yearOfEra), dayOfYear);
    }

    @Override
    public LocalDate dateYearDay(int prolepticYear, int dayOfYear) {
        return LocalDate.ofYearDay(prolepticYear, dayOfYear);
    }

    @Override
    public LocalDate dateEpochDay(long epochDay) {
        return LocalDate.ofEpochDay(epochDay);
    }

    @Override
    public LocalDate date(TemporalAccessor temporal) {
        return LocalDate.from(temporal);
    }

    public LocalDateTime localDateTime(TemporalAccessor temporal) {
        return LocalDateTime.from(temporal);
    }

    public ZonedDateTime zonedDateTime(TemporalAccessor temporal) {
        return ZonedDateTime.from(temporal);
    }

    public ZonedDateTime zonedDateTime(Instant instant, ZoneId zone) {
        return ZonedDateTime.ofInstant(instant, zone);
    }

    @Override
    public LocalDate dateNow() {
        return this.dateNow(Clock.systemDefaultZone());
    }

    @Override
    public LocalDate dateNow(ZoneId zone) {
        return this.dateNow(Clock.system(zone));
    }

    @Override
    public LocalDate dateNow(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        return this.date(LocalDate.now(clock));
    }

    @Override
    public boolean isLeapYear(long prolepticYear) {
        return (prolepticYear & 3L) == 0L && (prolepticYear % 100L != 0L || prolepticYear % 400L == 0L);
    }

    @Override
    public int prolepticYear(Era era, int yearOfEra) {
        if (!(era instanceof IsoEra)) {
            throw new ClassCastException("Era must be IsoEra");
        }
        return era == IsoEra.CE ? yearOfEra : 1 - yearOfEra;
    }

    @Override
    public IsoEra eraOf(int eraValue) {
        return IsoEra.of(eraValue);
    }

    @Override
    public List<Era> eras() {
        return Arrays.asList(IsoEra.values());
    }

    @Override
    public ValueRange range(ChronoField field2) {
        return field2.range();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public LocalDate resolveDate(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
        Long yoeLong;
        if (fieldValues.containsKey(ChronoField.EPOCH_DAY)) {
            return LocalDate.ofEpochDay(fieldValues.remove(ChronoField.EPOCH_DAY));
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
                int moy = Jdk8Methods.safeToInt(fieldValues.remove(ChronoField.MONTH_OF_YEAR));
                int dom = Jdk8Methods.safeToInt(fieldValues.remove(ChronoField.DAY_OF_MONTH));
                if (resolverStyle == ResolverStyle.LENIENT) {
                    long months = Jdk8Methods.safeSubtract(moy, 1);
                    long days = Jdk8Methods.safeSubtract(dom, 1);
                    return LocalDate.of(y, 1, 1).plusMonths(months).plusDays(days);
                }
                if (resolverStyle != ResolverStyle.SMART) return LocalDate.of(y, moy, dom);
                ChronoField.DAY_OF_MONTH.checkValidValue(dom);
                if (moy == 4 || moy == 6 || moy == 9 || moy == 11) {
                    dom = Math.min(dom, 30);
                    return LocalDate.of(y, moy, dom);
                } else {
                    if (moy != 2) return LocalDate.of(y, moy, dom);
                    dom = Math.min(dom, Month.FEBRUARY.length(Year.isLeap(y)));
                }
                return LocalDate.of(y, moy, dom);
            }
            if (fieldValues.containsKey(ChronoField.ALIGNED_WEEK_OF_MONTH)) {
                if (fieldValues.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH)) {
                    int y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long months = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.MONTH_OF_YEAR), 1L);
                        long weeks = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_MONTH), 1L);
                        long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH), 1L);
                        return LocalDate.of(y, 1, 1).plusMonths(months).plusWeeks(weeks).plusDays(days);
                    }
                    int moy = ChronoField.MONTH_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.MONTH_OF_YEAR));
                    int aw = ChronoField.ALIGNED_WEEK_OF_MONTH.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_MONTH));
                    int ad = ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
                    LocalDate date = LocalDate.of(y, moy, 1).plusDays((aw - 1) * 7 + (ad - 1));
                    if (resolverStyle != ResolverStyle.STRICT || date.get(ChronoField.MONTH_OF_YEAR) == moy) return date;
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                }
                if (fieldValues.containsKey(ChronoField.DAY_OF_WEEK)) {
                    int y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long months = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.MONTH_OF_YEAR), 1L);
                        long weeks = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_MONTH), 1L);
                        long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_WEEK), 1L);
                        return LocalDate.of(y, 1, 1).plusMonths(months).plusWeeks(weeks).plusDays(days);
                    }
                    int moy = ChronoField.MONTH_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.MONTH_OF_YEAR));
                    int aw = ChronoField.ALIGNED_WEEK_OF_MONTH.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_MONTH));
                    int dow = ChronoField.DAY_OF_WEEK.checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_WEEK));
                    LocalDate date = LocalDate.of(y, moy, 1).plusWeeks(aw - 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(dow)));
                    if (resolverStyle != ResolverStyle.STRICT || date.get(ChronoField.MONTH_OF_YEAR) == moy) return date;
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                }
            }
        }
        if (fieldValues.containsKey(ChronoField.DAY_OF_YEAR)) {
            int y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
            if (resolverStyle == ResolverStyle.LENIENT) {
                long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_YEAR), 1L);
                return LocalDate.ofYearDay(y, 1).plusDays(days);
            }
            int doy = ChronoField.DAY_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_YEAR));
            return LocalDate.ofYearDay(y, doy);
        }
        if (!fieldValues.containsKey(ChronoField.ALIGNED_WEEK_OF_YEAR)) return null;
        if (fieldValues.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR)) {
            int y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
            if (resolverStyle == ResolverStyle.LENIENT) {
                long weeks = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_YEAR), 1L);
                long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR), 1L);
                return LocalDate.of(y, 1, 1).plusWeeks(weeks).plusDays(days);
            }
            int aw = ChronoField.ALIGNED_WEEK_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_YEAR));
            int ad = ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
            LocalDate date = LocalDate.of(y, 1, 1).plusDays((aw - 1) * 7 + (ad - 1));
            if (resolverStyle != ResolverStyle.STRICT || date.get(ChronoField.YEAR) == y) return date;
            throw new DateTimeException("Strict mode rejected date parsed to a different year");
        }
        if (!fieldValues.containsKey(ChronoField.DAY_OF_WEEK)) return null;
        int y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR));
        if (resolverStyle == ResolverStyle.LENIENT) {
            long weeks = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_YEAR), 1L);
            long days = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_WEEK), 1L);
            return LocalDate.of(y, 1, 1).plusWeeks(weeks).plusDays(days);
        }
        int aw = ChronoField.ALIGNED_WEEK_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_YEAR));
        int dow = ChronoField.DAY_OF_WEEK.checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_WEEK));
        LocalDate date = LocalDate.of(y, 1, 1).plusWeeks(aw - 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(dow)));
        if (resolverStyle != ResolverStyle.STRICT || date.get(ChronoField.YEAR) == y) return date;
        throw new DateTimeException("Strict mode rejected date parsed to a different month");
    }
}

