/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.temporal;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.TemporalUnit;

public final class TemporalQueries {
    static final TemporalQuery<ZoneId> ZONE_ID = new TemporalQuery<ZoneId>(){

        @Override
        public ZoneId queryFrom(TemporalAccessor temporal) {
            return temporal.query(this);
        }
    };
    static final TemporalQuery<Chronology> CHRONO = new TemporalQuery<Chronology>(){

        @Override
        public Chronology queryFrom(TemporalAccessor temporal) {
            return temporal.query(this);
        }
    };
    static final TemporalQuery<TemporalUnit> PRECISION = new TemporalQuery<TemporalUnit>(){

        @Override
        public TemporalUnit queryFrom(TemporalAccessor temporal) {
            return temporal.query(this);
        }
    };
    static final TemporalQuery<ZoneId> ZONE = new TemporalQuery<ZoneId>(){

        @Override
        public ZoneId queryFrom(TemporalAccessor temporal) {
            ZoneId zone = temporal.query(ZONE_ID);
            return zone != null ? zone : (ZoneId)temporal.query(OFFSET);
        }
    };
    static final TemporalQuery<ZoneOffset> OFFSET = new TemporalQuery<ZoneOffset>(){

        @Override
        public ZoneOffset queryFrom(TemporalAccessor temporal) {
            if (temporal.isSupported(ChronoField.OFFSET_SECONDS)) {
                return ZoneOffset.ofTotalSeconds(temporal.get(ChronoField.OFFSET_SECONDS));
            }
            return null;
        }
    };
    static final TemporalQuery<LocalDate> LOCAL_DATE = new TemporalQuery<LocalDate>(){

        @Override
        public LocalDate queryFrom(TemporalAccessor temporal) {
            if (temporal.isSupported(ChronoField.EPOCH_DAY)) {
                return LocalDate.ofEpochDay(temporal.getLong(ChronoField.EPOCH_DAY));
            }
            return null;
        }
    };
    static final TemporalQuery<LocalTime> LOCAL_TIME = new TemporalQuery<LocalTime>(){

        @Override
        public LocalTime queryFrom(TemporalAccessor temporal) {
            if (temporal.isSupported(ChronoField.NANO_OF_DAY)) {
                return LocalTime.ofNanoOfDay(temporal.getLong(ChronoField.NANO_OF_DAY));
            }
            return null;
        }
    };

    private TemporalQueries() {
    }

    public static final TemporalQuery<ZoneId> zoneId() {
        return ZONE_ID;
    }

    public static final TemporalQuery<Chronology> chronology() {
        return CHRONO;
    }

    public static final TemporalQuery<TemporalUnit> precision() {
        return PRECISION;
    }

    public static final TemporalQuery<ZoneId> zone() {
        return ZONE;
    }

    public static final TemporalQuery<ZoneOffset> offset() {
        return OFFSET;
    }

    public static final TemporalQuery<LocalDate> localDate() {
        return LOCAL_DATE;
    }

    public static final TemporalQuery<LocalTime> localTime() {
        return LOCAL_TIME;
    }
}

