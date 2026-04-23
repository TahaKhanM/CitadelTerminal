/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.jdk8;

import java.util.Locale;
import org.threeten.bp.chrono.Era;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.TextStyle;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;

public abstract class DefaultInterfaceEra
extends DefaultInterfaceTemporalAccessor
implements Era {
    @Override
    public boolean isSupported(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2 == ChronoField.ERA;
        }
        return field2 != null && field2.isSupportedBy(this);
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
}

