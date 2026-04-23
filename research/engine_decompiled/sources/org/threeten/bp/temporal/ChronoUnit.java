/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.temporal;

import org.threeten.bp.Duration;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.chrono.ChronoZonedDateTime;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalUnit;

public enum ChronoUnit implements TemporalUnit
{
    NANOS("Nanos", Duration.ofNanos(1L)),
    MICROS("Micros", Duration.ofNanos(1000L)),
    MILLIS("Millis", Duration.ofNanos(1000000L)),
    SECONDS("Seconds", Duration.ofSeconds(1L)),
    MINUTES("Minutes", Duration.ofSeconds(60L)),
    HOURS("Hours", Duration.ofSeconds(3600L)),
    HALF_DAYS("HalfDays", Duration.ofSeconds(43200L)),
    DAYS("Days", Duration.ofSeconds(86400L)),
    WEEKS("Weeks", Duration.ofSeconds(604800L)),
    MONTHS("Months", Duration.ofSeconds(2629746L)),
    YEARS("Years", Duration.ofSeconds(31556952L)),
    DECADES("Decades", Duration.ofSeconds(315569520L)),
    CENTURIES("Centuries", Duration.ofSeconds(3155695200L)),
    MILLENNIA("Millennia", Duration.ofSeconds(31556952000L)),
    ERAS("Eras", Duration.ofSeconds(31556952000000000L)),
    FOREVER("Forever", Duration.ofSeconds(Long.MAX_VALUE, 999999999L));

    private final String name;
    private final Duration duration;

    private ChronoUnit(String name, Duration estimatedDuration) {
        this.name = name;
        this.duration = estimatedDuration;
    }

    @Override
    public Duration getDuration() {
        return this.duration;
    }

    @Override
    public boolean isDurationEstimated() {
        return this.isDateBased() || this == FOREVER;
    }

    @Override
    public boolean isDateBased() {
        return this.compareTo(DAYS) >= 0 && this != FOREVER;
    }

    @Override
    public boolean isTimeBased() {
        return this.compareTo(DAYS) < 0;
    }

    @Override
    public boolean isSupportedBy(Temporal temporal) {
        if (this == FOREVER) {
            return false;
        }
        if (temporal instanceof ChronoLocalDate) {
            return this.isDateBased();
        }
        if (temporal instanceof ChronoLocalDateTime || temporal instanceof ChronoZonedDateTime) {
            return true;
        }
        try {
            temporal.plus(1L, this);
            return true;
        }
        catch (RuntimeException ex) {
            try {
                temporal.plus(-1L, this);
                return true;
            }
            catch (RuntimeException ex2) {
                return false;
            }
        }
    }

    @Override
    public <R extends Temporal> R addTo(R dateTime, long periodToAdd) {
        return (R)dateTime.plus(periodToAdd, this);
    }

    @Override
    public long between(Temporal temporal1, Temporal temporal2) {
        return temporal1.until(temporal2, this);
    }

    @Override
    public String toString() {
        return this.name;
    }
}

