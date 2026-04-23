/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Locale;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.chrono.Era;
import org.threeten.bp.chrono.Ser;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.TextStyle;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;

public enum MinguoEra implements Era
{
    BEFORE_ROC,
    ROC;


    public static MinguoEra of(int era) {
        switch (era) {
            case 0: {
                return BEFORE_ROC;
            }
            case 1: {
                return ROC;
            }
        }
        throw new DateTimeException("Invalid era: " + era);
    }

    @Override
    public int getValue() {
        return this.ordinal();
    }

    @Override
    public boolean isSupported(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2 == ChronoField.ERA;
        }
        return field2 != null && field2.isSupportedBy(this);
    }

    @Override
    public ValueRange range(TemporalField field2) {
        if (field2 == ChronoField.ERA) {
            return field2.range();
        }
        if (field2 instanceof ChronoField) {
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.rangeRefinedBy(this);
    }

    @Override
    public int get(TemporalField field2) {
        if (field2 == ChronoField.ERA) {
            return this.getValue();
        }
        return this.range(field2).checkValidIntValue(this.getLong(field2), field2);
    }

    @Override
    public long getLong(TemporalField field2) {
        if (field2 == ChronoField.ERA) {
            return this.getValue();
        }
        if (field2 instanceof ChronoField) {
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.getFrom(this);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.ERA, this.getValue());
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.precision()) {
            return (R)ChronoUnit.ERAS;
        }
        if (query == TemporalQueries.chronology() || query == TemporalQueries.zone() || query == TemporalQueries.zoneId() || query == TemporalQueries.offset() || query == TemporalQueries.localDate() || query == TemporalQueries.localTime()) {
            return null;
        }
        return query.queryFrom(this);
    }

    @Override
    public String getDisplayName(TextStyle style, Locale locale) {
        return new DateTimeFormatterBuilder().appendText((TemporalField)ChronoField.ERA, style).toFormatter(locale).format(this);
    }

    private Object writeReplace() {
        return new Ser(6, this);
    }

    void writeExternal(DataOutput out) throws IOException {
        out.writeByte(this.getValue());
    }

    static MinguoEra readExternal(DataInput in) throws IOException {
        byte eraValue = in.readByte();
        return MinguoEra.of(eraValue);
    }
}

