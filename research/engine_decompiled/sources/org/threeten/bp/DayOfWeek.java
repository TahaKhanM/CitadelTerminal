/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp;

import java.util.Locale;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.TextStyle;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;

public enum DayOfWeek implements TemporalAccessor,
TemporalAdjuster
{
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public static final TemporalQuery<DayOfWeek> FROM;
    private static final DayOfWeek[] ENUMS;

    public static DayOfWeek of(int dayOfWeek) {
        if (dayOfWeek < 1 || dayOfWeek > 7) {
            throw new DateTimeException("Invalid value for DayOfWeek: " + dayOfWeek);
        }
        return ENUMS[dayOfWeek - 1];
    }

    public static DayOfWeek from(TemporalAccessor temporal) {
        if (temporal instanceof DayOfWeek) {
            return (DayOfWeek)temporal;
        }
        try {
            return DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        }
        catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain DayOfWeek from TemporalAccessor: " + temporal + ", type " + temporal.getClass().getName(), ex);
        }
    }

    public int getValue() {
        return this.ordinal() + 1;
    }

    public String getDisplayName(TextStyle style, Locale locale) {
        return new DateTimeFormatterBuilder().appendText((TemporalField)ChronoField.DAY_OF_WEEK, style).toFormatter(locale).format(this);
    }

    @Override
    public boolean isSupported(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2 == ChronoField.DAY_OF_WEEK;
        }
        return field2 != null && field2.isSupportedBy(this);
    }

    @Override
    public ValueRange range(TemporalField field2) {
        if (field2 == ChronoField.DAY_OF_WEEK) {
            return field2.range();
        }
        if (field2 instanceof ChronoField) {
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.rangeRefinedBy(this);
    }

    @Override
    public int get(TemporalField field2) {
        if (field2 == ChronoField.DAY_OF_WEEK) {
            return this.getValue();
        }
        return this.range(field2).checkValidIntValue(this.getLong(field2), field2);
    }

    @Override
    public long getLong(TemporalField field2) {
        if (field2 == ChronoField.DAY_OF_WEEK) {
            return this.getValue();
        }
        if (field2 instanceof ChronoField) {
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.getFrom(this);
    }

    public DayOfWeek plus(long days) {
        int amount = (int)(days % 7L);
        return ENUMS[(this.ordinal() + (amount + 7)) % 7];
    }

    public DayOfWeek minus(long days) {
        return this.plus(-(days % 7L));
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.precision()) {
            return (R)ChronoUnit.DAYS;
        }
        if (query == TemporalQueries.localDate() || query == TemporalQueries.localTime() || query == TemporalQueries.chronology() || query == TemporalQueries.zone() || query == TemporalQueries.zoneId() || query == TemporalQueries.offset()) {
            return null;
        }
        return query.queryFrom(this);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.DAY_OF_WEEK, this.getValue());
    }

    static {
        FROM = new TemporalQuery<DayOfWeek>(){

            @Override
            public DayOfWeek queryFrom(TemporalAccessor temporal) {
                return DayOfWeek.from(temporal);
            }
        };
        ENUMS = DayOfWeek.values();
    }
}

