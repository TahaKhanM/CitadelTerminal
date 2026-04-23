/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Ser;
import org.threeten.bp.format.DateTimeParseException;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;

public final class Duration
implements TemporalAmount,
Comparable<Duration>,
Serializable {
    public static final Duration ZERO = new Duration(0L, 0);
    private static final long serialVersionUID = 3078945930695997490L;
    private static final int NANOS_PER_SECOND = 1000000000;
    private static final int NANOS_PER_MILLI = 1000000;
    private static final BigInteger BI_NANOS_PER_SECOND = BigInteger.valueOf(1000000000L);
    private static final Pattern PATTERN = Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)D)?(T(?:([-+]?[0-9]+)H)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)(?:[.,]([0-9]{0,9}))?S)?)?", 2);
    private final long seconds;
    private final int nanos;

    public static Duration ofDays(long days) {
        return Duration.create(Jdk8Methods.safeMultiply(days, 86400), 0);
    }

    public static Duration ofHours(long hours) {
        return Duration.create(Jdk8Methods.safeMultiply(hours, 3600), 0);
    }

    public static Duration ofMinutes(long minutes) {
        return Duration.create(Jdk8Methods.safeMultiply(minutes, 60), 0);
    }

    public static Duration ofSeconds(long seconds) {
        return Duration.create(seconds, 0);
    }

    public static Duration ofSeconds(long seconds, long nanoAdjustment) {
        long secs = Jdk8Methods.safeAdd(seconds, Jdk8Methods.floorDiv(nanoAdjustment, 1000000000L));
        int nos = Jdk8Methods.floorMod(nanoAdjustment, 1000000000);
        return Duration.create(secs, nos);
    }

    public static Duration ofMillis(long millis) {
        long secs = millis / 1000L;
        int mos = (int)(millis % 1000L);
        if (mos < 0) {
            mos += 1000;
            --secs;
        }
        return Duration.create(secs, mos * 1000000);
    }

    public static Duration ofNanos(long nanos) {
        long secs = nanos / 1000000000L;
        int nos = (int)(nanos % 1000000000L);
        if (nos < 0) {
            nos += 1000000000;
            --secs;
        }
        return Duration.create(secs, nos);
    }

    public static Duration of(long amount, TemporalUnit unit) {
        return ZERO.plus(amount, unit);
    }

    public static Duration from(TemporalAmount amount) {
        Jdk8Methods.requireNonNull(amount, "amount");
        Duration duration = ZERO;
        for (TemporalUnit unit : amount.getUnits()) {
            duration = duration.plus(amount.get(unit), unit);
        }
        return duration;
    }

    public static Duration between(Temporal startInclusive, Temporal endExclusive) {
        long secs = startInclusive.until(endExclusive, ChronoUnit.SECONDS);
        long nanos = 0L;
        if (startInclusive.isSupported(ChronoField.NANO_OF_SECOND) && endExclusive.isSupported(ChronoField.NANO_OF_SECOND)) {
            try {
                long startNos = startInclusive.getLong(ChronoField.NANO_OF_SECOND);
                nanos = endExclusive.getLong(ChronoField.NANO_OF_SECOND) - startNos;
                if (secs > 0L && nanos < 0L) {
                    nanos += 1000000000L;
                } else if (secs < 0L && nanos > 0L) {
                    nanos -= 1000000000L;
                } else if (secs == 0L && nanos != 0L) {
                    Temporal adjustedEnd = endExclusive.with(ChronoField.NANO_OF_SECOND, startNos);
                    secs = startInclusive.until(adjustedEnd, ChronoUnit.SECONDS);
                }
            }
            catch (DateTimeException ex2) {
            }
            catch (ArithmeticException ex2) {
                // empty catch block
            }
        }
        return Duration.ofSeconds(secs, nanos);
    }

    public static Duration parse(CharSequence text) {
        Jdk8Methods.requireNonNull(text, "text");
        Matcher matcher = PATTERN.matcher(text);
        if (matcher.matches() && !"T".equals(matcher.group(3))) {
            boolean negate = "-".equals(matcher.group(1));
            String dayMatch = matcher.group(2);
            String hourMatch = matcher.group(4);
            String minuteMatch = matcher.group(5);
            String secondMatch = matcher.group(6);
            String fractionMatch = matcher.group(7);
            if (dayMatch != null || hourMatch != null || minuteMatch != null || secondMatch != null) {
                long daysAsSecs = Duration.parseNumber(text, dayMatch, 86400, "days");
                long hoursAsSecs = Duration.parseNumber(text, hourMatch, 3600, "hours");
                long minsAsSecs = Duration.parseNumber(text, minuteMatch, 60, "minutes");
                long seconds = Duration.parseNumber(text, secondMatch, 1, "seconds");
                boolean negativeSecs = secondMatch != null && secondMatch.charAt(0) == '-';
                int nanos = Duration.parseFraction(text, fractionMatch, negativeSecs ? -1 : 1);
                try {
                    return Duration.create(negate, daysAsSecs, hoursAsSecs, minsAsSecs, seconds, nanos);
                }
                catch (ArithmeticException ex) {
                    throw (DateTimeParseException)new DateTimeParseException("Text cannot be parsed to a Duration: overflow", text, 0).initCause(ex);
                }
            }
        }
        throw new DateTimeParseException("Text cannot be parsed to a Duration", text, 0);
    }

    private static long parseNumber(CharSequence text, String parsed, int multiplier, String errorText) {
        if (parsed == null) {
            return 0L;
        }
        try {
            if (parsed.startsWith("+")) {
                parsed = parsed.substring(1);
            }
            long val = Long.parseLong(parsed);
            return Jdk8Methods.safeMultiply(val, multiplier);
        }
        catch (NumberFormatException ex) {
            throw (DateTimeParseException)new DateTimeParseException("Text cannot be parsed to a Duration: " + errorText, text, 0).initCause(ex);
        }
        catch (ArithmeticException ex) {
            throw (DateTimeParseException)new DateTimeParseException("Text cannot be parsed to a Duration: " + errorText, text, 0).initCause(ex);
        }
    }

    private static int parseFraction(CharSequence text, String parsed, int negate) {
        if (parsed == null || parsed.length() == 0) {
            return 0;
        }
        try {
            parsed = (parsed + "000000000").substring(0, 9);
            return Integer.parseInt(parsed) * negate;
        }
        catch (NumberFormatException ex) {
            throw (DateTimeParseException)new DateTimeParseException("Text cannot be parsed to a Duration: fraction", text, 0).initCause(ex);
        }
        catch (ArithmeticException ex) {
            throw (DateTimeParseException)new DateTimeParseException("Text cannot be parsed to a Duration: fraction", text, 0).initCause(ex);
        }
    }

    private static Duration create(boolean negate, long daysAsSecs, long hoursAsSecs, long minsAsSecs, long secs, int nanos) {
        long seconds = Jdk8Methods.safeAdd(daysAsSecs, Jdk8Methods.safeAdd(hoursAsSecs, Jdk8Methods.safeAdd(minsAsSecs, secs)));
        if (negate) {
            return Duration.ofSeconds(seconds, nanos).negated();
        }
        return Duration.ofSeconds(seconds, nanos);
    }

    private static Duration create(long seconds, int nanoAdjustment) {
        if ((seconds | (long)nanoAdjustment) == 0L) {
            return ZERO;
        }
        return new Duration(seconds, nanoAdjustment);
    }

    private Duration(long seconds, int nanos) {
        this.seconds = seconds;
        this.nanos = nanos;
    }

    @Override
    public List<TemporalUnit> getUnits() {
        return Collections.unmodifiableList(Arrays.asList(ChronoUnit.SECONDS, ChronoUnit.NANOS));
    }

    @Override
    public long get(TemporalUnit unit) {
        if (unit == ChronoUnit.SECONDS) {
            return this.seconds;
        }
        if (unit == ChronoUnit.NANOS) {
            return this.nanos;
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
    }

    public boolean isZero() {
        return (this.seconds | (long)this.nanos) == 0L;
    }

    public boolean isNegative() {
        return this.seconds < 0L;
    }

    public long getSeconds() {
        return this.seconds;
    }

    public int getNano() {
        return this.nanos;
    }

    public Duration withSeconds(long seconds) {
        return Duration.create(seconds, this.nanos);
    }

    public Duration withNanos(int nanoOfSecond) {
        ChronoField.NANO_OF_SECOND.checkValidIntValue(nanoOfSecond);
        return Duration.create(this.seconds, nanoOfSecond);
    }

    public Duration plus(Duration duration) {
        return this.plus(duration.getSeconds(), duration.getNano());
    }

    public Duration plus(long amountToAdd, TemporalUnit unit) {
        Jdk8Methods.requireNonNull(unit, "unit");
        if (unit == ChronoUnit.DAYS) {
            return this.plus(Jdk8Methods.safeMultiply(amountToAdd, 86400), 0L);
        }
        if (unit.isDurationEstimated()) {
            throw new DateTimeException("Unit must not have an estimated duration");
        }
        if (amountToAdd == 0L) {
            return this;
        }
        if (unit instanceof ChronoUnit) {
            switch ((ChronoUnit)unit) {
                case NANOS: {
                    return this.plusNanos(amountToAdd);
                }
                case MICROS: {
                    return this.plusSeconds(amountToAdd / 1000000000L * 1000L).plusNanos(amountToAdd % 1000000000L * 1000L);
                }
                case MILLIS: {
                    return this.plusMillis(amountToAdd);
                }
                case SECONDS: {
                    return this.plusSeconds(amountToAdd);
                }
            }
            return this.plusSeconds(Jdk8Methods.safeMultiply(unit.getDuration().seconds, amountToAdd));
        }
        Duration duration = unit.getDuration().multipliedBy(amountToAdd);
        return this.plusSeconds(duration.getSeconds()).plusNanos(duration.getNano());
    }

    public Duration plusDays(long daysToAdd) {
        return this.plus(Jdk8Methods.safeMultiply(daysToAdd, 86400), 0L);
    }

    public Duration plusHours(long hoursToAdd) {
        return this.plus(Jdk8Methods.safeMultiply(hoursToAdd, 3600), 0L);
    }

    public Duration plusMinutes(long minutesToAdd) {
        return this.plus(Jdk8Methods.safeMultiply(minutesToAdd, 60), 0L);
    }

    public Duration plusSeconds(long secondsToAdd) {
        return this.plus(secondsToAdd, 0L);
    }

    public Duration plusMillis(long millisToAdd) {
        return this.plus(millisToAdd / 1000L, millisToAdd % 1000L * 1000000L);
    }

    public Duration plusNanos(long nanosToAdd) {
        return this.plus(0L, nanosToAdd);
    }

    private Duration plus(long secondsToAdd, long nanosToAdd) {
        if ((secondsToAdd | nanosToAdd) == 0L) {
            return this;
        }
        long epochSec = Jdk8Methods.safeAdd(this.seconds, secondsToAdd);
        epochSec = Jdk8Methods.safeAdd(epochSec, nanosToAdd / 1000000000L);
        long nanoAdjustment = (long)this.nanos + (nanosToAdd %= 1000000000L);
        return Duration.ofSeconds(epochSec, nanoAdjustment);
    }

    public Duration minus(Duration duration) {
        long secsToSubtract = duration.getSeconds();
        int nanosToSubtract = duration.getNano();
        if (secsToSubtract == Long.MIN_VALUE) {
            return this.plus(Long.MAX_VALUE, -nanosToSubtract).plus(1L, 0L);
        }
        return this.plus(-secsToSubtract, -nanosToSubtract);
    }

    public Duration minus(long amountToSubtract, TemporalUnit unit) {
        return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
    }

    public Duration minusDays(long daysToSubtract) {
        return daysToSubtract == Long.MIN_VALUE ? this.plusDays(Long.MAX_VALUE).plusDays(1L) : this.plusDays(-daysToSubtract);
    }

    public Duration minusHours(long hoursToSubtract) {
        return hoursToSubtract == Long.MIN_VALUE ? this.plusHours(Long.MAX_VALUE).plusHours(1L) : this.plusHours(-hoursToSubtract);
    }

    public Duration minusMinutes(long minutesToSubtract) {
        return minutesToSubtract == Long.MIN_VALUE ? this.plusMinutes(Long.MAX_VALUE).plusMinutes(1L) : this.plusMinutes(-minutesToSubtract);
    }

    public Duration minusSeconds(long secondsToSubtract) {
        return secondsToSubtract == Long.MIN_VALUE ? this.plusSeconds(Long.MAX_VALUE).plusSeconds(1L) : this.plusSeconds(-secondsToSubtract);
    }

    public Duration minusMillis(long millisToSubtract) {
        return millisToSubtract == Long.MIN_VALUE ? this.plusMillis(Long.MAX_VALUE).plusMillis(1L) : this.plusMillis(-millisToSubtract);
    }

    public Duration minusNanos(long nanosToSubtract) {
        return nanosToSubtract == Long.MIN_VALUE ? this.plusNanos(Long.MAX_VALUE).plusNanos(1L) : this.plusNanos(-nanosToSubtract);
    }

    public Duration multipliedBy(long multiplicand) {
        if (multiplicand == 0L) {
            return ZERO;
        }
        if (multiplicand == 1L) {
            return this;
        }
        return Duration.create(this.toSeconds().multiply(BigDecimal.valueOf(multiplicand)));
    }

    public Duration dividedBy(long divisor) {
        if (divisor == 0L) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        if (divisor == 1L) {
            return this;
        }
        return Duration.create(this.toSeconds().divide(BigDecimal.valueOf(divisor), RoundingMode.DOWN));
    }

    private BigDecimal toSeconds() {
        return BigDecimal.valueOf(this.seconds).add(BigDecimal.valueOf(this.nanos, 9));
    }

    private static Duration create(BigDecimal seconds) {
        BigInteger nanos = seconds.movePointRight(9).toBigIntegerExact();
        BigInteger[] divRem = nanos.divideAndRemainder(BI_NANOS_PER_SECOND);
        if (divRem[0].bitLength() > 63) {
            throw new ArithmeticException("Exceeds capacity of Duration: " + nanos);
        }
        return Duration.ofSeconds(divRem[0].longValue(), divRem[1].intValue());
    }

    public Duration negated() {
        return this.multipliedBy(-1L);
    }

    public Duration abs() {
        return this.isNegative() ? this.negated() : this;
    }

    @Override
    public Temporal addTo(Temporal temporal) {
        if (this.seconds != 0L) {
            temporal = temporal.plus(this.seconds, ChronoUnit.SECONDS);
        }
        if (this.nanos != 0) {
            temporal = temporal.plus(this.nanos, ChronoUnit.NANOS);
        }
        return temporal;
    }

    @Override
    public Temporal subtractFrom(Temporal temporal) {
        if (this.seconds != 0L) {
            temporal = temporal.minus(this.seconds, ChronoUnit.SECONDS);
        }
        if (this.nanos != 0) {
            temporal = temporal.minus(this.nanos, ChronoUnit.NANOS);
        }
        return temporal;
    }

    public long toDays() {
        return this.seconds / 86400L;
    }

    public long toHours() {
        return this.seconds / 3600L;
    }

    public long toMinutes() {
        return this.seconds / 60L;
    }

    public long toMillis() {
        long result2 = Jdk8Methods.safeMultiply(this.seconds, 1000);
        result2 = Jdk8Methods.safeAdd(result2, (long)(this.nanos / 1000000));
        return result2;
    }

    public long toNanos() {
        long result2 = Jdk8Methods.safeMultiply(this.seconds, 1000000000);
        result2 = Jdk8Methods.safeAdd(result2, (long)this.nanos);
        return result2;
    }

    @Override
    public int compareTo(Duration otherDuration) {
        int cmp = Jdk8Methods.compareLongs(this.seconds, otherDuration.seconds);
        if (cmp != 0) {
            return cmp;
        }
        return this.nanos - otherDuration.nanos;
    }

    public boolean equals(Object otherDuration) {
        if (this == otherDuration) {
            return true;
        }
        if (otherDuration instanceof Duration) {
            Duration other = (Duration)otherDuration;
            return this.seconds == other.seconds && this.nanos == other.nanos;
        }
        return false;
    }

    public int hashCode() {
        return (int)(this.seconds ^ this.seconds >>> 32) + 51 * this.nanos;
    }

    public String toString() {
        if (this == ZERO) {
            return "PT0S";
        }
        long hours = this.seconds / 3600L;
        int minutes = (int)(this.seconds % 3600L / 60L);
        int secs = (int)(this.seconds % 60L);
        StringBuilder buf = new StringBuilder(24);
        buf.append("PT");
        if (hours != 0L) {
            buf.append(hours).append('H');
        }
        if (minutes != 0) {
            buf.append(minutes).append('M');
        }
        if (secs == 0 && this.nanos == 0 && buf.length() > 2) {
            return buf.toString();
        }
        if (secs < 0 && this.nanos > 0) {
            if (secs == -1) {
                buf.append("-0");
            } else {
                buf.append(secs + 1);
            }
        } else {
            buf.append(secs);
        }
        if (this.nanos > 0) {
            int pos = buf.length();
            if (secs < 0) {
                buf.append(2000000000 - this.nanos);
            } else {
                buf.append(this.nanos + 1000000000);
            }
            while (buf.charAt(buf.length() - 1) == '0') {
                buf.setLength(buf.length() - 1);
            }
            buf.setCharAt(pos, '.');
        }
        buf.append('S');
        return buf.toString();
    }

    private Object writeReplace() {
        return new Ser(1, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    void writeExternal(DataOutput out) throws IOException {
        out.writeLong(this.seconds);
        out.writeInt(this.nanos);
    }

    static Duration readExternal(DataInput in) throws IOException {
        long seconds = in.readLong();
        int nanos = in.readInt();
        return Duration.ofSeconds(seconds, nanos);
    }
}

