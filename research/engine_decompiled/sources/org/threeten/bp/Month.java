/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp;

import java.util.Locale;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.LocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
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

public enum Month implements TemporalAccessor,
TemporalAdjuster
{
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER;

    public static final TemporalQuery<Month> FROM;
    private static final Month[] ENUMS;

    public static Month of(int month) {
        if (month < 1 || month > 12) {
            throw new DateTimeException("Invalid value for MonthOfYear: " + month);
        }
        return ENUMS[month - 1];
    }

    public static Month from(TemporalAccessor temporal) {
        if (temporal instanceof Month) {
            return (Month)temporal;
        }
        try {
            if (!IsoChronology.INSTANCE.equals(Chronology.from(temporal))) {
                temporal = LocalDate.from(temporal);
            }
            return Month.of(temporal.get(ChronoField.MONTH_OF_YEAR));
        }
        catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain Month from TemporalAccessor: " + temporal + ", type " + temporal.getClass().getName(), ex);
        }
    }

    public int getValue() {
        return this.ordinal() + 1;
    }

    public String getDisplayName(TextStyle style, Locale locale) {
        return new DateTimeFormatterBuilder().appendText((TemporalField)ChronoField.MONTH_OF_YEAR, style).toFormatter(locale).format(this);
    }

    @Override
    public boolean isSupported(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2 == ChronoField.MONTH_OF_YEAR;
        }
        return field2 != null && field2.isSupportedBy(this);
    }

    @Override
    public ValueRange range(TemporalField field2) {
        if (field2 == ChronoField.MONTH_OF_YEAR) {
            return field2.range();
        }
        if (field2 instanceof ChronoField) {
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.rangeRefinedBy(this);
    }

    @Override
    public int get(TemporalField field2) {
        if (field2 == ChronoField.MONTH_OF_YEAR) {
            return this.getValue();
        }
        return this.range(field2).checkValidIntValue(this.getLong(field2), field2);
    }

    @Override
    public long getLong(TemporalField field2) {
        if (field2 == ChronoField.MONTH_OF_YEAR) {
            return this.getValue();
        }
        if (field2 instanceof ChronoField) {
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.getFrom(this);
    }

    public Month plus(long months) {
        int amount = (int)(months % 12L);
        return ENUMS[(this.ordinal() + (amount + 12)) % 12];
    }

    public Month minus(long months) {
        return this.plus(-(months % 12L));
    }

    public int length(boolean leapYear) {
        switch (this) {
            case FEBRUARY: {
                return leapYear ? 29 : 28;
            }
            case APRIL: 
            case JUNE: 
            case SEPTEMBER: 
            case NOVEMBER: {
                return 30;
            }
        }
        return 31;
    }

    public int minLength() {
        switch (this) {
            case FEBRUARY: {
                return 28;
            }
            case APRIL: 
            case JUNE: 
            case SEPTEMBER: 
            case NOVEMBER: {
                return 30;
            }
        }
        return 31;
    }

    public int maxLength() {
        switch (this) {
            case FEBRUARY: {
                return 29;
            }
            case APRIL: 
            case JUNE: 
            case SEPTEMBER: 
            case NOVEMBER: {
                return 30;
            }
        }
        return 31;
    }

    public int firstDayOfYear(boolean leapYear) {
        int leap = leapYear ? 1 : 0;
        switch (this) {
            case JANUARY: {
                return 1;
            }
            case FEBRUARY: {
                return 32;
            }
            case MARCH: {
                return 60 + leap;
            }
            case APRIL: {
                return 91 + leap;
            }
            case MAY: {
                return 121 + leap;
            }
            case JUNE: {
                return 152 + leap;
            }
            case JULY: {
                return 182 + leap;
            }
            case AUGUST: {
                return 213 + leap;
            }
            case SEPTEMBER: {
                return 244 + leap;
            }
            case OCTOBER: {
                return 274 + leap;
            }
            case NOVEMBER: {
                return 305 + leap;
            }
        }
        return 335 + leap;
    }

    public Month firstMonthOfQuarter() {
        return ENUMS[this.ordinal() / 3 * 3];
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.chronology()) {
            return (R)IsoChronology.INSTANCE;
        }
        if (query == TemporalQueries.precision()) {
            return (R)ChronoUnit.MONTHS;
        }
        if (query == TemporalQueries.localDate() || query == TemporalQueries.localTime() || query == TemporalQueries.zone() || query == TemporalQueries.zoneId() || query == TemporalQueries.offset()) {
            return null;
        }
        return query.queryFrom(this);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        if (!Chronology.from(temporal).equals(IsoChronology.INSTANCE)) {
            throw new DateTimeException("Adjustment only supported on ISO date-time");
        }
        return temporal.with(ChronoField.MONTH_OF_YEAR, this.getValue());
    }

    static {
        FROM = new TemporalQuery<Month>(){

            @Override
            public Month queryFrom(TemporalAccessor temporal) {
                return Month.from(temporal);
            }
        };
        ENUMS = Month.values();
    }
}

