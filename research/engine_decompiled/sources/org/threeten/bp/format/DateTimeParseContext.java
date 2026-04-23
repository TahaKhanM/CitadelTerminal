/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.DateTimeBuilder;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.DecimalStyle;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;

final class DateTimeParseContext {
    private Locale locale;
    private DecimalStyle symbols;
    private Chronology overrideChronology;
    private ZoneId overrideZone;
    private boolean caseSensitive = true;
    private boolean strict = true;
    private final ArrayList<Parsed> parsed = new ArrayList();

    DateTimeParseContext(DateTimeFormatter formatter) {
        this.locale = formatter.getLocale();
        this.symbols = formatter.getDecimalStyle();
        this.overrideChronology = formatter.getChronology();
        this.overrideZone = formatter.getZone();
        this.parsed.add(new Parsed());
    }

    DateTimeParseContext(Locale locale, DecimalStyle symbols, Chronology chronology) {
        this.locale = locale;
        this.symbols = symbols;
        this.overrideChronology = chronology;
        this.overrideZone = null;
        this.parsed.add(new Parsed());
    }

    DateTimeParseContext(DateTimeParseContext other) {
        this.locale = other.locale;
        this.symbols = other.symbols;
        this.overrideChronology = other.overrideChronology;
        this.overrideZone = other.overrideZone;
        this.caseSensitive = other.caseSensitive;
        this.strict = other.strict;
        this.parsed.add(new Parsed());
    }

    DateTimeParseContext copy() {
        return new DateTimeParseContext(this);
    }

    Locale getLocale() {
        return this.locale;
    }

    DecimalStyle getSymbols() {
        return this.symbols;
    }

    Chronology getEffectiveChronology() {
        Chronology chrono = this.currentParsed().chrono;
        if (chrono == null && (chrono = this.overrideChronology) == null) {
            chrono = IsoChronology.INSTANCE;
        }
        return chrono;
    }

    boolean isCaseSensitive() {
        return this.caseSensitive;
    }

    void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    boolean subSequenceEquals(CharSequence cs1, int offset1, CharSequence cs2, int offset2, int length) {
        if (offset1 + length > cs1.length() || offset2 + length > cs2.length()) {
            return false;
        }
        if (this.isCaseSensitive()) {
            for (int i = 0; i < length; ++i) {
                char ch2;
                char ch1 = cs1.charAt(offset1 + i);
                if (ch1 == (ch2 = cs2.charAt(offset2 + i))) continue;
                return false;
            }
        } else {
            for (int i = 0; i < length; ++i) {
                char ch2;
                char ch1 = cs1.charAt(offset1 + i);
                if (ch1 == (ch2 = cs2.charAt(offset2 + i)) || Character.toUpperCase(ch1) == Character.toUpperCase(ch2) || Character.toLowerCase(ch1) == Character.toLowerCase(ch2)) continue;
                return false;
            }
        }
        return true;
    }

    boolean charEquals(char ch1, char ch2) {
        if (this.isCaseSensitive()) {
            return ch1 == ch2;
        }
        return DateTimeParseContext.charEqualsIgnoreCase(ch1, ch2);
    }

    static boolean charEqualsIgnoreCase(char c1, char c2) {
        return c1 == c2 || Character.toUpperCase(c1) == Character.toUpperCase(c2) || Character.toLowerCase(c1) == Character.toLowerCase(c2);
    }

    boolean isStrict() {
        return this.strict;
    }

    void setStrict(boolean strict) {
        this.strict = strict;
    }

    void startOptional() {
        this.parsed.add(this.currentParsed().copy());
    }

    void endOptional(boolean successful) {
        if (successful) {
            this.parsed.remove(this.parsed.size() - 2);
        } else {
            this.parsed.remove(this.parsed.size() - 1);
        }
    }

    private Parsed currentParsed() {
        return this.parsed.get(this.parsed.size() - 1);
    }

    Long getParsed(TemporalField field2) {
        return this.currentParsed().fieldValues.get(field2);
    }

    int setParsedField(TemporalField field2, long value, int errorPos, int successPos) {
        Jdk8Methods.requireNonNull(field2, "field");
        Long old = this.currentParsed().fieldValues.put(field2, value);
        return old != null && old != value ? ~errorPos : successPos;
    }

    void setParsed(Chronology chrono) {
        Jdk8Methods.requireNonNull(chrono, "chrono");
        Parsed currentParsed = this.currentParsed();
        currentParsed.chrono = chrono;
        if (currentParsed.callbacks != null) {
            ArrayList<Object[]> callbacks = new ArrayList<Object[]>(currentParsed.callbacks);
            currentParsed.callbacks.clear();
            for (Object[] objects : callbacks) {
                DateTimeFormatterBuilder.ReducedPrinterParser pp = (DateTimeFormatterBuilder.ReducedPrinterParser)objects[0];
                pp.setValue(this, (Long)objects[1], (Integer)objects[2], (Integer)objects[3]);
            }
        }
    }

    void addChronologyChangedParser(DateTimeFormatterBuilder.ReducedPrinterParser reducedPrinterParser, long value, int errorPos, int successPos) {
        Parsed currentParsed = this.currentParsed();
        if (currentParsed.callbacks == null) {
            currentParsed.callbacks = new ArrayList<Object[]>(2);
        }
        currentParsed.callbacks.add(new Object[]{reducedPrinterParser, value, errorPos, successPos});
    }

    void setParsed(ZoneId zone) {
        Jdk8Methods.requireNonNull(zone, "zone");
        this.currentParsed().zone = zone;
    }

    void setParsedLeapSecond() {
        this.currentParsed().leapSecond = true;
    }

    Parsed toParsed() {
        return this.currentParsed();
    }

    public String toString() {
        return this.currentParsed().toString();
    }

    void setLocale(Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        this.locale = locale;
    }

    final class Parsed
    extends DefaultInterfaceTemporalAccessor {
        Chronology chrono = null;
        ZoneId zone = null;
        final Map<TemporalField, Long> fieldValues = new HashMap<TemporalField, Long>();
        boolean leapSecond;
        Period excessDays = Period.ZERO;
        List<Object[]> callbacks;

        private Parsed() {
        }

        protected Parsed copy() {
            Parsed cloned = new Parsed();
            cloned.chrono = this.chrono;
            cloned.zone = this.zone;
            cloned.fieldValues.putAll(this.fieldValues);
            cloned.leapSecond = this.leapSecond;
            return cloned;
        }

        public String toString() {
            return this.fieldValues.toString() + "," + this.chrono + "," + this.zone;
        }

        @Override
        public boolean isSupported(TemporalField field2) {
            return this.fieldValues.containsKey(field2);
        }

        @Override
        public int get(TemporalField field2) {
            if (!this.fieldValues.containsKey(field2)) {
                throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
            }
            long value = this.fieldValues.get(field2);
            return Jdk8Methods.safeToInt(value);
        }

        @Override
        public long getLong(TemporalField field2) {
            if (!this.fieldValues.containsKey(field2)) {
                throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
            }
            return this.fieldValues.get(field2);
        }

        @Override
        public <R> R query(TemporalQuery<R> query) {
            if (query == TemporalQueries.chronology()) {
                return (R)this.chrono;
            }
            if (query == TemporalQueries.zoneId() || query == TemporalQueries.zone()) {
                return (R)this.zone;
            }
            return super.query(query);
        }

        DateTimeBuilder toBuilder() {
            DateTimeBuilder builder = new DateTimeBuilder();
            builder.fieldValues.putAll(this.fieldValues);
            builder.chrono = DateTimeParseContext.this.getEffectiveChronology();
            builder.zone = this.zone != null ? this.zone : DateTimeParseContext.this.overrideZone;
            builder.leapSecond = this.leapSecond;
            builder.excessDays = this.excessDays;
            return builder;
        }
    }
}

