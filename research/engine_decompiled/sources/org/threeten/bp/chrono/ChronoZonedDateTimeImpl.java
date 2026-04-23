/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.List;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.chrono.ChronoLocalDateTimeImpl;
import org.threeten.bp.chrono.ChronoZonedDateTime;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.Ser;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.zone.ZoneOffsetTransition;
import org.threeten.bp.zone.ZoneRules;

final class ChronoZonedDateTimeImpl<D extends ChronoLocalDate>
extends ChronoZonedDateTime<D>
implements Serializable {
    private static final long serialVersionUID = -5261813987200935591L;
    private final ChronoLocalDateTimeImpl<D> dateTime;
    private final ZoneOffset offset;
    private final ZoneId zone;

    static <R extends ChronoLocalDate> ChronoZonedDateTime<R> ofBest(ChronoLocalDateTimeImpl<R> localDateTime, ZoneId zone, ZoneOffset preferredOffset) {
        ZoneOffset offset;
        LocalDateTime isoLDT;
        Jdk8Methods.requireNonNull(localDateTime, "localDateTime");
        Jdk8Methods.requireNonNull(zone, "zone");
        if (zone instanceof ZoneOffset) {
            return new ChronoZonedDateTimeImpl<R>(localDateTime, (ZoneOffset)zone, zone);
        }
        ZoneRules rules = zone.getRules();
        List<ZoneOffset> validOffsets = rules.getValidOffsets(isoLDT = LocalDateTime.from(localDateTime));
        if (validOffsets.size() == 1) {
            offset = validOffsets.get(0);
        } else if (validOffsets.size() == 0) {
            ZoneOffsetTransition trans = rules.getTransition(isoLDT);
            localDateTime = localDateTime.plusSeconds(trans.getDuration().getSeconds());
            offset = trans.getOffsetAfter();
        } else {
            offset = preferredOffset != null && validOffsets.contains(preferredOffset) ? preferredOffset : validOffsets.get(0);
        }
        Jdk8Methods.requireNonNull(offset, "offset");
        return new ChronoZonedDateTimeImpl<R>(localDateTime, offset, zone);
    }

    static <R extends ChronoLocalDate> ChronoZonedDateTimeImpl<R> ofInstant(Chronology chrono, Instant instant, ZoneId zone) {
        ZoneRules rules = zone.getRules();
        ZoneOffset offset = rules.getOffset(instant);
        Jdk8Methods.requireNonNull(offset, "offset");
        LocalDateTime ldt = LocalDateTime.ofEpochSecond(instant.getEpochSecond(), instant.getNano(), offset);
        ChronoLocalDateTimeImpl cldt = (ChronoLocalDateTimeImpl)chrono.localDateTime(ldt);
        return new ChronoZonedDateTimeImpl(cldt, offset, zone);
    }

    private ChronoZonedDateTimeImpl<D> create(Instant instant, ZoneId zone) {
        return ChronoZonedDateTimeImpl.ofInstant(((ChronoLocalDate)this.toLocalDate()).getChronology(), instant, zone);
    }

    private ChronoZonedDateTimeImpl(ChronoLocalDateTimeImpl<D> dateTime, ZoneOffset offset, ZoneId zone) {
        this.dateTime = Jdk8Methods.requireNonNull(dateTime, "dateTime");
        this.offset = Jdk8Methods.requireNonNull(offset, "offset");
        this.zone = Jdk8Methods.requireNonNull(zone, "zone");
    }

    @Override
    public boolean isSupported(TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            return unit.isDateBased() || unit.isTimeBased();
        }
        return unit != null && unit.isSupportedBy(this);
    }

    @Override
    public ZoneOffset getOffset() {
        return this.offset;
    }

    @Override
    public ChronoZonedDateTime<D> withEarlierOffsetAtOverlap() {
        ZoneOffset earlierOffset;
        ZoneOffsetTransition trans = this.getZone().getRules().getTransition(LocalDateTime.from(this));
        if (trans != null && trans.isOverlap() && !(earlierOffset = trans.getOffsetBefore()).equals(this.offset)) {
            return new ChronoZonedDateTimeImpl<D>(this.dateTime, earlierOffset, this.zone);
        }
        return this;
    }

    @Override
    public ChronoZonedDateTime<D> withLaterOffsetAtOverlap() {
        ZoneOffset offset;
        ZoneOffsetTransition trans = this.getZone().getRules().getTransition(LocalDateTime.from(this));
        if (trans != null && !(offset = trans.getOffsetAfter()).equals(this.getOffset())) {
            return new ChronoZonedDateTimeImpl<D>(this.dateTime, offset, this.zone);
        }
        return this;
    }

    @Override
    public ChronoLocalDateTime<D> toLocalDateTime() {
        return this.dateTime;
    }

    @Override
    public ZoneId getZone() {
        return this.zone;
    }

    @Override
    public ChronoZonedDateTime<D> withZoneSameLocal(ZoneId zone) {
        return ChronoZonedDateTimeImpl.ofBest(this.dateTime, zone, this.offset);
    }

    @Override
    public ChronoZonedDateTime<D> withZoneSameInstant(ZoneId zone) {
        Jdk8Methods.requireNonNull(zone, "zone");
        return this.zone.equals(zone) ? this : this.create(this.dateTime.toInstant(this.offset), zone);
    }

    @Override
    public boolean isSupported(TemporalField field2) {
        return field2 instanceof ChronoField || field2 != null && field2.isSupportedBy(this);
    }

    @Override
    public ChronoZonedDateTime<D> with(TemporalField field2, long newValue) {
        if (field2 instanceof ChronoField) {
            ChronoField f = (ChronoField)field2;
            switch (f) {
                case INSTANT_SECONDS: {
                    return this.plus(newValue - this.toEpochSecond(), ChronoUnit.SECONDS);
                }
                case OFFSET_SECONDS: {
                    ZoneOffset offset = ZoneOffset.ofTotalSeconds(f.checkValidIntValue(newValue));
                    return this.create(this.dateTime.toInstant(offset), this.zone);
                }
            }
            return ChronoZonedDateTimeImpl.ofBest(this.dateTime.with(field2, newValue), this.zone, this.offset);
        }
        return ((ChronoLocalDate)this.toLocalDate()).getChronology().ensureChronoZonedDateTime(field2.adjustInto(this, newValue));
    }

    @Override
    public ChronoZonedDateTime<D> plus(long amountToAdd, TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            return this.with(this.dateTime.plus(amountToAdd, unit));
        }
        return ((ChronoLocalDate)this.toLocalDate()).getChronology().ensureChronoZonedDateTime(unit.addTo(this, amountToAdd));
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        ChronoZonedDateTime<?> end = ((ChronoLocalDate)this.toLocalDate()).getChronology().zonedDateTime(endExclusive);
        if (unit instanceof ChronoUnit) {
            end = end.withZoneSameInstant(this.offset);
            return this.dateTime.until(end.toLocalDateTime(), unit);
        }
        return unit.between(this, end);
    }

    private Object writeReplace() {
        return new Ser(13, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.dateTime);
        out.writeObject(this.offset);
        out.writeObject(this.zone);
    }

    static ChronoZonedDateTime<?> readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ChronoLocalDateTime dateTime = (ChronoLocalDateTime)in.readObject();
        ZoneOffset offset = (ZoneOffset)in.readObject();
        ZoneId zone = (ZoneId)in.readObject();
        return dateTime.atZone(offset).withZoneSameLocal(zone);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ChronoZonedDateTime) {
            return this.compareTo((ChronoZonedDateTime)obj) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toLocalDateTime().hashCode() ^ this.getOffset().hashCode() ^ Integer.rotateLeft(this.getZone().hashCode(), 3);
    }

    @Override
    public String toString() {
        String str = this.toLocalDateTime().toString() + this.getOffset().toString();
        if (this.getOffset() != this.getZone()) {
            str = str + '[' + this.getZone().toString() + ']';
        }
        return str;
    }
}

