/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.LocalDate;
import org.threeten.bp.chrono.ChronoPeriod;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.DateTimeParseException;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;

public final class Period
extends ChronoPeriod
implements Serializable {
    public static final Period ZERO = new Period(0, 0, 0);
    private static final long serialVersionUID = -8290556941213247973L;
    private static final Pattern PATTERN = Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)Y)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)W)?(?:([-+]?[0-9]+)D)?", 2);
    private final int years;
    private final int months;
    private final int days;

    public static Period ofYears(int years) {
        return Period.create(years, 0, 0);
    }

    public static Period ofMonths(int months) {
        return Period.create(0, months, 0);
    }

    public static Period ofWeeks(int weeks) {
        return Period.create(0, 0, Jdk8Methods.safeMultiply(weeks, 7));
    }

    public static Period ofDays(int days) {
        return Period.create(0, 0, days);
    }

    public static Period of(int years, int months, int days) {
        return Period.create(years, months, days);
    }

    public static Period from(TemporalAmount amount) {
        if (amount instanceof Period) {
            return (Period)amount;
        }
        if (amount instanceof ChronoPeriod && !IsoChronology.INSTANCE.equals(((ChronoPeriod)amount).getChronology())) {
            throw new DateTimeException("Period requires ISO chronology: " + amount);
        }
        Jdk8Methods.requireNonNull(amount, "amount");
        int years = 0;
        int months = 0;
        int days = 0;
        for (TemporalUnit unit : amount.getUnits()) {
            long unitAmount = amount.get(unit);
            if (unit == ChronoUnit.YEARS) {
                years = Jdk8Methods.safeToInt(unitAmount);
                continue;
            }
            if (unit == ChronoUnit.MONTHS) {
                months = Jdk8Methods.safeToInt(unitAmount);
                continue;
            }
            if (unit == ChronoUnit.DAYS) {
                days = Jdk8Methods.safeToInt(unitAmount);
                continue;
            }
            throw new DateTimeException("Unit must be Years, Months or Days, but was " + unit);
        }
        return Period.create(years, months, days);
    }

    public static Period between(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate);
    }

    public static Period parse(CharSequence text) {
        Jdk8Methods.requireNonNull(text, "text");
        Matcher matcher = PATTERN.matcher(text);
        if (matcher.matches()) {
            int negate = "-".equals(matcher.group(1)) ? -1 : 1;
            String yearMatch = matcher.group(2);
            String monthMatch = matcher.group(3);
            String weekMatch = matcher.group(4);
            String dayMatch = matcher.group(5);
            if (yearMatch != null || monthMatch != null || weekMatch != null || dayMatch != null) {
                try {
                    int years = Period.parseNumber(text, yearMatch, negate);
                    int months = Period.parseNumber(text, monthMatch, negate);
                    int weeks = Period.parseNumber(text, weekMatch, negate);
                    int days = Period.parseNumber(text, dayMatch, negate);
                    days = Jdk8Methods.safeAdd(days, Jdk8Methods.safeMultiply(weeks, 7));
                    return Period.create(years, months, days);
                }
                catch (NumberFormatException ex) {
                    throw (DateTimeParseException)new DateTimeParseException("Text cannot be parsed to a Period", text, 0).initCause(ex);
                }
            }
        }
        throw new DateTimeParseException("Text cannot be parsed to a Period", text, 0);
    }

    private static int parseNumber(CharSequence text, String str, int negate) {
        if (str == null) {
            return 0;
        }
        int val = Integer.parseInt(str);
        try {
            return Jdk8Methods.safeMultiply(val, negate);
        }
        catch (ArithmeticException ex) {
            throw (DateTimeParseException)new DateTimeParseException("Text cannot be parsed to a Period", text, 0).initCause(ex);
        }
    }

    private static Period create(int years, int months, int days) {
        if ((years | months | days) == 0) {
            return ZERO;
        }
        return new Period(years, months, days);
    }

    private Period(int years, int months, int days) {
        this.years = years;
        this.months = months;
        this.days = days;
    }

    private Object readResolve() {
        if ((this.years | this.months | this.days) == 0) {
            return ZERO;
        }
        return this;
    }

    @Override
    public List<TemporalUnit> getUnits() {
        return Collections.unmodifiableList(Arrays.asList(ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS));
    }

    @Override
    public Chronology getChronology() {
        return IsoChronology.INSTANCE;
    }

    @Override
    public long get(TemporalUnit unit) {
        if (unit == ChronoUnit.YEARS) {
            return this.years;
        }
        if (unit == ChronoUnit.MONTHS) {
            return this.months;
        }
        if (unit == ChronoUnit.DAYS) {
            return this.days;
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
    }

    @Override
    public boolean isZero() {
        return this == ZERO;
    }

    @Override
    public boolean isNegative() {
        return this.years < 0 || this.months < 0 || this.days < 0;
    }

    public int getYears() {
        return this.years;
    }

    public int getMonths() {
        return this.months;
    }

    public int getDays() {
        return this.days;
    }

    public Period withYears(int years) {
        if (years == this.years) {
            return this;
        }
        return Period.create(years, this.months, this.days);
    }

    public Period withMonths(int months) {
        if (months == this.months) {
            return this;
        }
        return Period.create(this.years, months, this.days);
    }

    public Period withDays(int days) {
        if (days == this.days) {
            return this;
        }
        return Period.create(this.years, this.months, days);
    }

    @Override
    public Period plus(TemporalAmount amountToAdd) {
        Period amount = Period.from(amountToAdd);
        return Period.create(Jdk8Methods.safeAdd(this.years, amount.years), Jdk8Methods.safeAdd(this.months, amount.months), Jdk8Methods.safeAdd(this.days, amount.days));
    }

    public Period plusYears(long yearsToAdd) {
        if (yearsToAdd == 0L) {
            return this;
        }
        return Period.create(Jdk8Methods.safeToInt(Jdk8Methods.safeAdd((long)this.years, yearsToAdd)), this.months, this.days);
    }

    public Period plusMonths(long monthsToAdd) {
        if (monthsToAdd == 0L) {
            return this;
        }
        return Period.create(this.years, Jdk8Methods.safeToInt(Jdk8Methods.safeAdd((long)this.months, monthsToAdd)), this.days);
    }

    public Period plusDays(long daysToAdd) {
        if (daysToAdd == 0L) {
            return this;
        }
        return Period.create(this.years, this.months, Jdk8Methods.safeToInt(Jdk8Methods.safeAdd((long)this.days, daysToAdd)));
    }

    @Override
    public Period minus(TemporalAmount amountToSubtract) {
        Period amount = Period.from(amountToSubtract);
        return Period.create(Jdk8Methods.safeSubtract(this.years, amount.years), Jdk8Methods.safeSubtract(this.months, amount.months), Jdk8Methods.safeSubtract(this.days, amount.days));
    }

    public Period minusYears(long yearsToSubtract) {
        return yearsToSubtract == Long.MIN_VALUE ? this.plusYears(Long.MAX_VALUE).plusYears(1L) : this.plusYears(-yearsToSubtract);
    }

    public Period minusMonths(long monthsToSubtract) {
        return monthsToSubtract == Long.MIN_VALUE ? this.plusMonths(Long.MAX_VALUE).plusMonths(1L) : this.plusMonths(-monthsToSubtract);
    }

    public Period minusDays(long daysToSubtract) {
        return daysToSubtract == Long.MIN_VALUE ? this.plusDays(Long.MAX_VALUE).plusDays(1L) : this.plusDays(-daysToSubtract);
    }

    @Override
    public Period multipliedBy(int scalar) {
        if (this == ZERO || scalar == 1) {
            return this;
        }
        return Period.create(Jdk8Methods.safeMultiply(this.years, scalar), Jdk8Methods.safeMultiply(this.months, scalar), Jdk8Methods.safeMultiply(this.days, scalar));
    }

    @Override
    public Period negated() {
        return this.multipliedBy(-1);
    }

    @Override
    public Period normalized() {
        long totalMonths = this.toTotalMonths();
        long splitYears = totalMonths / 12L;
        int splitMonths = (int)(totalMonths % 12L);
        if (splitYears == (long)this.years && splitMonths == this.months) {
            return this;
        }
        return Period.create(Jdk8Methods.safeToInt(splitYears), splitMonths, this.days);
    }

    public long toTotalMonths() {
        return (long)this.years * 12L + (long)this.months;
    }

    @Override
    public Temporal addTo(Temporal temporal) {
        Jdk8Methods.requireNonNull(temporal, "temporal");
        if (this.years != 0) {
            temporal = this.months != 0 ? temporal.plus(this.toTotalMonths(), ChronoUnit.MONTHS) : temporal.plus(this.years, ChronoUnit.YEARS);
        } else if (this.months != 0) {
            temporal = temporal.plus(this.months, ChronoUnit.MONTHS);
        }
        if (this.days != 0) {
            temporal = temporal.plus(this.days, ChronoUnit.DAYS);
        }
        return temporal;
    }

    @Override
    public Temporal subtractFrom(Temporal temporal) {
        Jdk8Methods.requireNonNull(temporal, "temporal");
        if (this.years != 0) {
            temporal = this.months != 0 ? temporal.minus(this.toTotalMonths(), ChronoUnit.MONTHS) : temporal.minus(this.years, ChronoUnit.YEARS);
        } else if (this.months != 0) {
            temporal = temporal.minus(this.months, ChronoUnit.MONTHS);
        }
        if (this.days != 0) {
            temporal = temporal.minus(this.days, ChronoUnit.DAYS);
        }
        return temporal;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Period) {
            Period other = (Period)obj;
            return this.years == other.years && this.months == other.months && this.days == other.days;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.years + Integer.rotateLeft(this.months, 8) + Integer.rotateLeft(this.days, 16);
    }

    @Override
    public String toString() {
        if (this == ZERO) {
            return "P0D";
        }
        StringBuilder buf = new StringBuilder();
        buf.append('P');
        if (this.years != 0) {
            buf.append(this.years).append('Y');
        }
        if (this.months != 0) {
            buf.append(this.months).append('M');
        }
        if (this.days != 0) {
            buf.append(this.days).append('D');
        }
        return buf.toString();
    }
}

