/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.format;

import java.util.Locale;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DecimalStyle;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.ValueRange;

final class DateTimePrintContext {
    private TemporalAccessor temporal;
    private Locale locale;
    private DecimalStyle symbols;
    private int optional;

    DateTimePrintContext(TemporalAccessor temporal, DateTimeFormatter formatter) {
        this.temporal = DateTimePrintContext.adjust(temporal, formatter);
        this.locale = formatter.getLocale();
        this.symbols = formatter.getDecimalStyle();
    }

    DateTimePrintContext(TemporalAccessor temporal, Locale locale, DecimalStyle symbols) {
        this.temporal = temporal;
        this.locale = locale;
        this.symbols = symbols;
    }

    private static TemporalAccessor adjust(final TemporalAccessor temporal, DateTimeFormatter formatter) {
        ChronoLocalDate effectiveDate;
        ZoneId effectiveZone;
        Chronology overrideChrono = formatter.getChronology();
        ZoneId overrideZone = formatter.getZone();
        if (overrideChrono == null && overrideZone == null) {
            return temporal;
        }
        Chronology temporalChrono = temporal.query(TemporalQueries.chronology());
        ZoneId temporalZone = temporal.query(TemporalQueries.zoneId());
        if (Jdk8Methods.equals(temporalChrono, overrideChrono)) {
            overrideChrono = null;
        }
        if (Jdk8Methods.equals(temporalZone, overrideZone)) {
            overrideZone = null;
        }
        if (overrideChrono == null && overrideZone == null) {
            return temporal;
        }
        final Chronology effectiveChrono = overrideChrono != null ? overrideChrono : temporalChrono;
        ZoneId zoneId = effectiveZone = overrideZone != null ? overrideZone : temporalZone;
        if (overrideZone != null) {
            if (temporal.isSupported(ChronoField.INSTANT_SECONDS)) {
                Chronology chrono = effectiveChrono != null ? effectiveChrono : IsoChronology.INSTANCE;
                return chrono.zonedDateTime(Instant.from(temporal), overrideZone);
            }
            ZoneId normalizedOffset = overrideZone.normalized();
            ZoneOffset temporalOffset = temporal.query(TemporalQueries.offset());
            if (normalizedOffset instanceof ZoneOffset && temporalOffset != null && !normalizedOffset.equals(temporalOffset)) {
                throw new DateTimeException("Invalid override zone for temporal: " + overrideZone + " " + temporal);
            }
        }
        if (overrideChrono != null) {
            if (temporal.isSupported(ChronoField.EPOCH_DAY)) {
                effectiveDate = effectiveChrono.date(temporal);
            } else {
                if (overrideChrono != IsoChronology.INSTANCE || temporalChrono != null) {
                    for (ChronoField f : ChronoField.values()) {
                        if (!f.isDateBased() || !temporal.isSupported(f)) continue;
                        throw new DateTimeException("Invalid override chronology for temporal: " + overrideChrono + " " + temporal);
                    }
                }
                effectiveDate = null;
            }
        } else {
            effectiveDate = null;
        }
        return new DefaultInterfaceTemporalAccessor(){

            @Override
            public boolean isSupported(TemporalField field2) {
                if (effectiveDate != null && field2.isDateBased()) {
                    return effectiveDate.isSupported(field2);
                }
                return temporal.isSupported(field2);
            }

            @Override
            public ValueRange range(TemporalField field2) {
                if (effectiveDate != null && field2.isDateBased()) {
                    return effectiveDate.range(field2);
                }
                return temporal.range(field2);
            }

            @Override
            public long getLong(TemporalField field2) {
                if (effectiveDate != null && field2.isDateBased()) {
                    return effectiveDate.getLong(field2);
                }
                return temporal.getLong(field2);
            }

            @Override
            public <R> R query(TemporalQuery<R> query) {
                if (query == TemporalQueries.chronology()) {
                    return (R)effectiveChrono;
                }
                if (query == TemporalQueries.zoneId()) {
                    return (R)effectiveZone;
                }
                if (query == TemporalQueries.precision()) {
                    return temporal.query(query);
                }
                return query.queryFrom(this);
            }
        };
    }

    TemporalAccessor getTemporal() {
        return this.temporal;
    }

    Locale getLocale() {
        return this.locale;
    }

    DecimalStyle getSymbols() {
        return this.symbols;
    }

    void startOptional() {
        ++this.optional;
    }

    void endOptional() {
        --this.optional;
    }

    <R> R getValue(TemporalQuery<R> query) {
        R result2 = this.temporal.query(query);
        if (result2 == null && this.optional == 0) {
            throw new DateTimeException("Unable to extract value: " + this.temporal.getClass());
        }
        return result2;
    }

    Long getValue(TemporalField field2) {
        try {
            return this.temporal.getLong(field2);
        }
        catch (DateTimeException ex) {
            if (this.optional > 0) {
                return null;
            }
            throw ex;
        }
    }

    public String toString() {
        return this.temporal.toString();
    }

    void setDateTime(TemporalAccessor temporal) {
        Jdk8Methods.requireNonNull(temporal, "temporal");
        this.temporal = temporal;
    }

    void setLocale(Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        this.locale = locale;
    }
}

