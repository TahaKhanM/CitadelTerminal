/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.format;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.chrono.ChronoZonedDateTime;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;

final class DateTimeBuilder
extends DefaultInterfaceTemporalAccessor
implements TemporalAccessor,
Cloneable {
    final Map<TemporalField, Long> fieldValues = new HashMap<TemporalField, Long>();
    Chronology chrono;
    ZoneId zone;
    ChronoLocalDate date;
    LocalTime time;
    boolean leapSecond;
    Period excessDays;

    public DateTimeBuilder() {
    }

    public DateTimeBuilder(TemporalField field2, long value) {
        this.addFieldValue(field2, value);
    }

    private Long getFieldValue0(TemporalField field2) {
        return this.fieldValues.get(field2);
    }

    DateTimeBuilder addFieldValue(TemporalField field2, long value) {
        Jdk8Methods.requireNonNull(field2, "field");
        Long old = this.getFieldValue0(field2);
        if (old != null && old != value) {
            throw new DateTimeException("Conflict found: " + field2 + " " + old + " differs from " + field2 + " " + value + ": " + this);
        }
        return this.putFieldValue0(field2, value);
    }

    private DateTimeBuilder putFieldValue0(TemporalField field2, long value) {
        this.fieldValues.put(field2, value);
        return this;
    }

    void addObject(ChronoLocalDate date) {
        this.date = date;
    }

    void addObject(LocalTime time) {
        this.time = time;
    }

    public DateTimeBuilder resolve(ResolverStyle resolverStyle, Set<TemporalField> resolverFields) {
        if (resolverFields != null) {
            this.fieldValues.keySet().retainAll(resolverFields);
        }
        this.mergeInstantFields();
        this.mergeDate(resolverStyle);
        this.mergeTime(resolverStyle);
        if (this.resolveFields(resolverStyle)) {
            this.mergeInstantFields();
            this.mergeDate(resolverStyle);
            this.mergeTime(resolverStyle);
        }
        this.resolveTimeInferZeroes(resolverStyle);
        this.crossCheck();
        if (this.excessDays != null && !this.excessDays.isZero() && this.date != null && this.time != null) {
            this.date = this.date.plus(this.excessDays);
            this.excessDays = Period.ZERO;
        }
        this.resolveFractional();
        this.resolveInstant();
        return this;
    }

    private boolean resolveFields(ResolverStyle resolverStyle) {
        int changes = 0;
        block0: while (changes < 100) {
            for (Map.Entry<TemporalField, Long> entry : this.fieldValues.entrySet()) {
                TemporalField targetField = entry.getKey();
                ChronoLocalDateTime resolvedObject = targetField.resolve(this.fieldValues, this, resolverStyle);
                if (resolvedObject != null) {
                    if (resolvedObject instanceof ChronoZonedDateTime) {
                        ChronoZonedDateTime czdt = (ChronoZonedDateTime)((Object)resolvedObject);
                        if (this.zone == null) {
                            this.zone = czdt.getZone();
                        } else if (!this.zone.equals(czdt.getZone())) {
                            throw new DateTimeException("ChronoZonedDateTime must use the effective parsed zone: " + this.zone);
                        }
                        resolvedObject = czdt.toLocalDateTime();
                    }
                    if (resolvedObject instanceof ChronoLocalDate) {
                        this.resolveMakeChanges(targetField, (ChronoLocalDate)((Object)resolvedObject));
                        ++changes;
                        continue block0;
                    }
                    if (resolvedObject instanceof LocalTime) {
                        this.resolveMakeChanges(targetField, (LocalTime)((Object)resolvedObject));
                        ++changes;
                        continue block0;
                    }
                    if (resolvedObject instanceof ChronoLocalDateTime) {
                        ChronoLocalDateTime cldt = resolvedObject;
                        this.resolveMakeChanges(targetField, (ChronoLocalDate)cldt.toLocalDate());
                        this.resolveMakeChanges(targetField, cldt.toLocalTime());
                        ++changes;
                        continue block0;
                    }
                    throw new DateTimeException("Unknown type: " + resolvedObject.getClass().getName());
                }
                if (this.fieldValues.containsKey(targetField)) continue;
                ++changes;
                continue block0;
            }
        }
        if (changes == 100) {
            throw new DateTimeException("Badly written field");
        }
        return changes > 0;
    }

    private void resolveMakeChanges(TemporalField targetField, ChronoLocalDate date) {
        if (!this.chrono.equals(date.getChronology())) {
            throw new DateTimeException("ChronoLocalDate must use the effective parsed chronology: " + this.chrono);
        }
        long epochDay = date.toEpochDay();
        Long old = this.fieldValues.put(ChronoField.EPOCH_DAY, epochDay);
        if (old != null && old != epochDay) {
            throw new DateTimeException("Conflict found: " + LocalDate.ofEpochDay(old) + " differs from " + LocalDate.ofEpochDay(epochDay) + " while resolving  " + targetField);
        }
    }

    private void resolveMakeChanges(TemporalField targetField, LocalTime time) {
        long nanOfDay = time.toNanoOfDay();
        Long old = this.fieldValues.put(ChronoField.NANO_OF_DAY, nanOfDay);
        if (old != null && old != nanOfDay) {
            throw new DateTimeException("Conflict found: " + LocalTime.ofNanoOfDay(old) + " differs from " + time + " while resolving  " + targetField);
        }
    }

    private void mergeDate(ResolverStyle resolverStyle) {
        if (this.chrono instanceof IsoChronology) {
            this.checkDate((LocalDate)IsoChronology.INSTANCE.resolveDate((Map)this.fieldValues, resolverStyle));
        } else if (this.fieldValues.containsKey(ChronoField.EPOCH_DAY)) {
            this.checkDate(LocalDate.ofEpochDay(this.fieldValues.remove(ChronoField.EPOCH_DAY)));
            return;
        }
    }

    private void checkDate(LocalDate date) {
        if (date != null) {
            this.addObject(date);
            for (TemporalField field2 : this.fieldValues.keySet()) {
                long val1;
                if (!(field2 instanceof ChronoField) || !field2.isDateBased()) continue;
                try {
                    val1 = date.getLong(field2);
                }
                catch (DateTimeException ex) {
                    continue;
                }
                Long val2 = this.fieldValues.get(field2);
                if (val1 == val2) continue;
                throw new DateTimeException("Conflict found: Field " + field2 + " " + val1 + " differs from " + field2 + " " + val2 + " derived from " + date);
            }
        }
    }

    private void mergeTime(ResolverStyle resolverStyle) {
        long nos;
        long los;
        long ch;
        if (this.fieldValues.containsKey(ChronoField.CLOCK_HOUR_OF_DAY)) {
            ch = this.fieldValues.remove(ChronoField.CLOCK_HOUR_OF_DAY);
            if (resolverStyle != ResolverStyle.LENIENT && (resolverStyle != ResolverStyle.SMART || ch != 0L)) {
                ChronoField.CLOCK_HOUR_OF_DAY.checkValidValue(ch);
            }
            this.addFieldValue(ChronoField.HOUR_OF_DAY, ch == 24L ? 0L : ch);
        }
        if (this.fieldValues.containsKey(ChronoField.CLOCK_HOUR_OF_AMPM)) {
            ch = this.fieldValues.remove(ChronoField.CLOCK_HOUR_OF_AMPM);
            if (resolverStyle != ResolverStyle.LENIENT && (resolverStyle != ResolverStyle.SMART || ch != 0L)) {
                ChronoField.CLOCK_HOUR_OF_AMPM.checkValidValue(ch);
            }
            this.addFieldValue(ChronoField.HOUR_OF_AMPM, ch == 12L ? 0L : ch);
        }
        if (resolverStyle != ResolverStyle.LENIENT) {
            if (this.fieldValues.containsKey(ChronoField.AMPM_OF_DAY)) {
                ChronoField.AMPM_OF_DAY.checkValidValue(this.fieldValues.get(ChronoField.AMPM_OF_DAY));
            }
            if (this.fieldValues.containsKey(ChronoField.HOUR_OF_AMPM)) {
                ChronoField.HOUR_OF_AMPM.checkValidValue(this.fieldValues.get(ChronoField.HOUR_OF_AMPM));
            }
        }
        if (this.fieldValues.containsKey(ChronoField.AMPM_OF_DAY) && this.fieldValues.containsKey(ChronoField.HOUR_OF_AMPM)) {
            long ap = this.fieldValues.remove(ChronoField.AMPM_OF_DAY);
            long hap = this.fieldValues.remove(ChronoField.HOUR_OF_AMPM);
            this.addFieldValue(ChronoField.HOUR_OF_DAY, ap * 12L + hap);
        }
        if (this.fieldValues.containsKey(ChronoField.NANO_OF_DAY)) {
            long nod = this.fieldValues.remove(ChronoField.NANO_OF_DAY);
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.NANO_OF_DAY.checkValidValue(nod);
            }
            this.addFieldValue(ChronoField.SECOND_OF_DAY, nod / 1000000000L);
            this.addFieldValue(ChronoField.NANO_OF_SECOND, nod % 1000000000L);
        }
        if (this.fieldValues.containsKey(ChronoField.MICRO_OF_DAY)) {
            long cod = this.fieldValues.remove(ChronoField.MICRO_OF_DAY);
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.MICRO_OF_DAY.checkValidValue(cod);
            }
            this.addFieldValue(ChronoField.SECOND_OF_DAY, cod / 1000000L);
            this.addFieldValue(ChronoField.MICRO_OF_SECOND, cod % 1000000L);
        }
        if (this.fieldValues.containsKey(ChronoField.MILLI_OF_DAY)) {
            long lod = this.fieldValues.remove(ChronoField.MILLI_OF_DAY);
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.MILLI_OF_DAY.checkValidValue(lod);
            }
            this.addFieldValue(ChronoField.SECOND_OF_DAY, lod / 1000L);
            this.addFieldValue(ChronoField.MILLI_OF_SECOND, lod % 1000L);
        }
        if (this.fieldValues.containsKey(ChronoField.SECOND_OF_DAY)) {
            long sod = this.fieldValues.remove(ChronoField.SECOND_OF_DAY);
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.SECOND_OF_DAY.checkValidValue(sod);
            }
            this.addFieldValue(ChronoField.HOUR_OF_DAY, sod / 3600L);
            this.addFieldValue(ChronoField.MINUTE_OF_HOUR, sod / 60L % 60L);
            this.addFieldValue(ChronoField.SECOND_OF_MINUTE, sod % 60L);
        }
        if (this.fieldValues.containsKey(ChronoField.MINUTE_OF_DAY)) {
            long mod = this.fieldValues.remove(ChronoField.MINUTE_OF_DAY);
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.MINUTE_OF_DAY.checkValidValue(mod);
            }
            this.addFieldValue(ChronoField.HOUR_OF_DAY, mod / 60L);
            this.addFieldValue(ChronoField.MINUTE_OF_HOUR, mod % 60L);
        }
        if (resolverStyle != ResolverStyle.LENIENT) {
            if (this.fieldValues.containsKey(ChronoField.MILLI_OF_SECOND)) {
                ChronoField.MILLI_OF_SECOND.checkValidValue(this.fieldValues.get(ChronoField.MILLI_OF_SECOND));
            }
            if (this.fieldValues.containsKey(ChronoField.MICRO_OF_SECOND)) {
                ChronoField.MICRO_OF_SECOND.checkValidValue(this.fieldValues.get(ChronoField.MICRO_OF_SECOND));
            }
        }
        if (this.fieldValues.containsKey(ChronoField.MILLI_OF_SECOND) && this.fieldValues.containsKey(ChronoField.MICRO_OF_SECOND)) {
            los = this.fieldValues.remove(ChronoField.MILLI_OF_SECOND);
            long cos = this.fieldValues.get(ChronoField.MICRO_OF_SECOND);
            this.addFieldValue(ChronoField.MICRO_OF_SECOND, los * 1000L + cos % 1000L);
        }
        if (this.fieldValues.containsKey(ChronoField.MICRO_OF_SECOND) && this.fieldValues.containsKey(ChronoField.NANO_OF_SECOND)) {
            nos = this.fieldValues.get(ChronoField.NANO_OF_SECOND);
            this.addFieldValue(ChronoField.MICRO_OF_SECOND, nos / 1000L);
            this.fieldValues.remove(ChronoField.MICRO_OF_SECOND);
        }
        if (this.fieldValues.containsKey(ChronoField.MILLI_OF_SECOND) && this.fieldValues.containsKey(ChronoField.NANO_OF_SECOND)) {
            nos = this.fieldValues.get(ChronoField.NANO_OF_SECOND);
            this.addFieldValue(ChronoField.MILLI_OF_SECOND, nos / 1000000L);
            this.fieldValues.remove(ChronoField.MILLI_OF_SECOND);
        }
        if (this.fieldValues.containsKey(ChronoField.MICRO_OF_SECOND)) {
            long cos = this.fieldValues.remove(ChronoField.MICRO_OF_SECOND);
            this.addFieldValue(ChronoField.NANO_OF_SECOND, cos * 1000L);
        } else if (this.fieldValues.containsKey(ChronoField.MILLI_OF_SECOND)) {
            los = this.fieldValues.remove(ChronoField.MILLI_OF_SECOND);
            this.addFieldValue(ChronoField.NANO_OF_SECOND, los * 1000000L);
        }
    }

    private void resolveTimeInferZeroes(ResolverStyle resolverStyle) {
        Long hod = this.fieldValues.get(ChronoField.HOUR_OF_DAY);
        Long moh = this.fieldValues.get(ChronoField.MINUTE_OF_HOUR);
        Long som = this.fieldValues.get(ChronoField.SECOND_OF_MINUTE);
        Long nos = this.fieldValues.get(ChronoField.NANO_OF_SECOND);
        if (hod == null) {
            return;
        }
        if (moh == null && (som != null || nos != null)) {
            return;
        }
        if (moh != null && som == null && nos != null) {
            return;
        }
        if (resolverStyle != ResolverStyle.LENIENT) {
            if (hod != null) {
                if (!(resolverStyle != ResolverStyle.SMART || hod != 24L || moh != null && moh != 0L || som != null && som != 0L || nos != null && nos != 0L)) {
                    hod = 0L;
                    this.excessDays = Period.ofDays(1);
                }
                int hodVal = ChronoField.HOUR_OF_DAY.checkValidIntValue(hod);
                if (moh != null) {
                    int mohVal = ChronoField.MINUTE_OF_HOUR.checkValidIntValue(moh);
                    if (som != null) {
                        int somVal = ChronoField.SECOND_OF_MINUTE.checkValidIntValue(som);
                        if (nos != null) {
                            int nosVal = ChronoField.NANO_OF_SECOND.checkValidIntValue(nos);
                            this.addObject(LocalTime.of(hodVal, mohVal, somVal, nosVal));
                        } else {
                            this.addObject(LocalTime.of(hodVal, mohVal, somVal));
                        }
                    } else if (nos == null) {
                        this.addObject(LocalTime.of(hodVal, mohVal));
                    }
                } else if (som == null && nos == null) {
                    this.addObject(LocalTime.of(hodVal, 0));
                }
            }
        } else if (hod != null) {
            long hodVal = hod;
            if (moh != null) {
                if (som != null) {
                    if (nos == null) {
                        nos = 0L;
                    }
                    long totalNanos = Jdk8Methods.safeMultiply(hodVal, 3600000000000L);
                    totalNanos = Jdk8Methods.safeAdd(totalNanos, Jdk8Methods.safeMultiply((long)moh, 60000000000L));
                    totalNanos = Jdk8Methods.safeAdd(totalNanos, Jdk8Methods.safeMultiply((long)som, 1000000000L));
                    totalNanos = Jdk8Methods.safeAdd(totalNanos, nos);
                    int excessDays = (int)Jdk8Methods.floorDiv(totalNanos, 86400000000000L);
                    long nod = Jdk8Methods.floorMod(totalNanos, 86400000000000L);
                    this.addObject(LocalTime.ofNanoOfDay(nod));
                    this.excessDays = Period.ofDays(excessDays);
                } else {
                    long totalSecs = Jdk8Methods.safeMultiply(hodVal, 3600L);
                    totalSecs = Jdk8Methods.safeAdd(totalSecs, Jdk8Methods.safeMultiply((long)moh, 60L));
                    int excessDays = (int)Jdk8Methods.floorDiv(totalSecs, 86400L);
                    long sod = Jdk8Methods.floorMod(totalSecs, 86400L);
                    this.addObject(LocalTime.ofSecondOfDay(sod));
                    this.excessDays = Period.ofDays(excessDays);
                }
            } else {
                int excessDays = Jdk8Methods.safeToInt(Jdk8Methods.floorDiv(hodVal, 24L));
                hodVal = Jdk8Methods.floorMod(hodVal, 24);
                this.addObject(LocalTime.of((int)hodVal, 0));
                this.excessDays = Period.ofDays(excessDays);
            }
        }
        this.fieldValues.remove(ChronoField.HOUR_OF_DAY);
        this.fieldValues.remove(ChronoField.MINUTE_OF_HOUR);
        this.fieldValues.remove(ChronoField.SECOND_OF_MINUTE);
        this.fieldValues.remove(ChronoField.NANO_OF_SECOND);
    }

    private void mergeInstantFields() {
        if (this.fieldValues.containsKey(ChronoField.INSTANT_SECONDS)) {
            if (this.zone != null) {
                this.mergeInstantFields0(this.zone);
            } else {
                Long offsetSecs = this.fieldValues.get(ChronoField.OFFSET_SECONDS);
                if (offsetSecs != null) {
                    ZoneOffset offset = ZoneOffset.ofTotalSeconds(offsetSecs.intValue());
                    this.mergeInstantFields0(offset);
                }
            }
        }
    }

    private void mergeInstantFields0(ZoneId selectedZone) {
        Instant instant = Instant.ofEpochSecond(this.fieldValues.remove(ChronoField.INSTANT_SECONDS));
        ChronoZonedDateTime<?> zdt = this.chrono.zonedDateTime(instant, selectedZone);
        if (this.date == null) {
            this.addObject((ChronoLocalDate)zdt.toLocalDate());
        } else {
            this.resolveMakeChanges((TemporalField)ChronoField.INSTANT_SECONDS, (ChronoLocalDate)zdt.toLocalDate());
        }
        this.addFieldValue(ChronoField.SECOND_OF_DAY, zdt.toLocalTime().toSecondOfDay());
    }

    private void crossCheck() {
        if (this.fieldValues.size() > 0) {
            if (this.date != null && this.time != null) {
                this.crossCheck(this.date.atTime(this.time));
            } else if (this.date != null) {
                this.crossCheck(this.date);
            } else if (this.time != null) {
                this.crossCheck(this.time);
            }
        }
    }

    private void crossCheck(TemporalAccessor temporal) {
        Iterator<Map.Entry<TemporalField, Long>> it = this.fieldValues.entrySet().iterator();
        while (it.hasNext()) {
            long temporalValue;
            Map.Entry<TemporalField, Long> entry = it.next();
            TemporalField field2 = entry.getKey();
            long value = entry.getValue();
            if (!temporal.isSupported(field2)) continue;
            try {
                temporalValue = temporal.getLong(field2);
            }
            catch (RuntimeException ex) {
                continue;
            }
            if (temporalValue != value) {
                throw new DateTimeException("Cross check failed: " + field2 + " " + temporalValue + " vs " + field2 + " " + value);
            }
            it.remove();
        }
    }

    private void resolveFractional() {
        if (this.time == null && (this.fieldValues.containsKey(ChronoField.INSTANT_SECONDS) || this.fieldValues.containsKey(ChronoField.SECOND_OF_DAY) || this.fieldValues.containsKey(ChronoField.SECOND_OF_MINUTE))) {
            if (this.fieldValues.containsKey(ChronoField.NANO_OF_SECOND)) {
                long nos = this.fieldValues.get(ChronoField.NANO_OF_SECOND);
                this.fieldValues.put(ChronoField.MICRO_OF_SECOND, nos / 1000L);
                this.fieldValues.put(ChronoField.MILLI_OF_SECOND, nos / 1000000L);
            } else {
                this.fieldValues.put(ChronoField.NANO_OF_SECOND, 0L);
                this.fieldValues.put(ChronoField.MICRO_OF_SECOND, 0L);
                this.fieldValues.put(ChronoField.MILLI_OF_SECOND, 0L);
            }
        }
    }

    private void resolveInstant() {
        if (this.date != null && this.time != null) {
            if (this.zone != null) {
                long instant = this.date.atTime(this.time).atZone(this.zone).getLong(ChronoField.INSTANT_SECONDS);
                this.fieldValues.put(ChronoField.INSTANT_SECONDS, instant);
            } else {
                Long offsetSecs = this.fieldValues.get(ChronoField.OFFSET_SECONDS);
                if (offsetSecs != null) {
                    ZoneOffset offset = ZoneOffset.ofTotalSeconds(offsetSecs.intValue());
                    long instant = this.date.atTime(this.time).atZone(offset).getLong(ChronoField.INSTANT_SECONDS);
                    this.fieldValues.put(ChronoField.INSTANT_SECONDS, instant);
                }
            }
        }
    }

    public <R> R build(TemporalQuery<R> type) {
        return type.queryFrom(this);
    }

    @Override
    public boolean isSupported(TemporalField field2) {
        if (field2 == null) {
            return false;
        }
        return this.fieldValues.containsKey(field2) || this.date != null && this.date.isSupported(field2) || this.time != null && this.time.isSupported(field2);
    }

    @Override
    public long getLong(TemporalField field2) {
        Jdk8Methods.requireNonNull(field2, "field");
        Long value = this.getFieldValue0(field2);
        if (value == null) {
            if (this.date != null && this.date.isSupported(field2)) {
                return this.date.getLong(field2);
            }
            if (this.time != null && this.time.isSupported(field2)) {
                return this.time.getLong(field2);
            }
            throw new DateTimeException("Field not found: " + field2);
        }
        return value;
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.zoneId()) {
            return (R)this.zone;
        }
        if (query == TemporalQueries.chronology()) {
            return (R)this.chrono;
        }
        if (query == TemporalQueries.localDate()) {
            return (R)(this.date != null ? LocalDate.from(this.date) : null);
        }
        if (query == TemporalQueries.localTime()) {
            return (R)this.time;
        }
        if (query == TemporalQueries.zone() || query == TemporalQueries.offset()) {
            return query.queryFrom(this);
        }
        if (query == TemporalQueries.precision()) {
            return null;
        }
        return query.queryFrom(this);
    }

    public String toString() {
        StringBuilder buf = new StringBuilder(128);
        buf.append("DateTimeBuilder[");
        if (this.fieldValues.size() > 0) {
            buf.append("fields=").append(this.fieldValues);
        }
        buf.append(", ").append(this.chrono);
        buf.append(", ").append(this.zone);
        buf.append(", ").append(this.date);
        buf.append(", ").append(this.time);
        buf.append(']');
        return buf.toString();
    }
}

