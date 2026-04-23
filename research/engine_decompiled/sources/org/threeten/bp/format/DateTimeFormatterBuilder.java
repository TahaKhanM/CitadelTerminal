/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.format;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.format.DateTimeFormatStyleProvider;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeParseContext;
import org.threeten.bp.format.DateTimePrintContext;
import org.threeten.bp.format.DateTimeTextProvider;
import org.threeten.bp.format.DecimalStyle;
import org.threeten.bp.format.FormatStyle;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.format.SignStyle;
import org.threeten.bp.format.SimpleDateTimeTextProvider;
import org.threeten.bp.format.TextStyle;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.IsoFields;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.ValueRange;
import org.threeten.bp.temporal.WeekFields;
import org.threeten.bp.zone.ZoneRulesProvider;

public final class DateTimeFormatterBuilder {
    private static final TemporalQuery<ZoneId> QUERY_REGION_ONLY = new TemporalQuery<ZoneId>(){

        @Override
        public ZoneId queryFrom(TemporalAccessor temporal) {
            ZoneId zone = temporal.query(TemporalQueries.zoneId());
            return zone != null && !(zone instanceof ZoneOffset) ? zone : null;
        }
    };
    private DateTimeFormatterBuilder active = this;
    private final DateTimeFormatterBuilder parent;
    private final List<DateTimePrinterParser> printerParsers = new ArrayList<DateTimePrinterParser>();
    private final boolean optional;
    private int padNextWidth;
    private char padNextChar;
    private int valueParserIndex = -1;
    private static final Map<Character, TemporalField> FIELD_MAP = new HashMap<Character, TemporalField>();
    static final Comparator<String> LENGTH_SORT;

    public static String getLocalizedDateTimePattern(FormatStyle dateStyle, FormatStyle timeStyle, Chronology chrono, Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        Jdk8Methods.requireNonNull(chrono, "chrono");
        if (dateStyle == null && timeStyle == null) {
            throw new IllegalArgumentException("Either dateStyle or timeStyle must be non-null");
        }
        DateFormat dateFormat = dateStyle != null ? (timeStyle != null ? DateFormat.getDateTimeInstance(dateStyle.ordinal(), timeStyle.ordinal(), locale) : DateFormat.getDateInstance(dateStyle.ordinal(), locale)) : DateFormat.getTimeInstance(timeStyle.ordinal(), locale);
        if (dateFormat instanceof SimpleDateFormat) {
            return ((SimpleDateFormat)dateFormat).toPattern();
        }
        throw new IllegalArgumentException("Unable to determine pattern");
    }

    public DateTimeFormatterBuilder() {
        this.parent = null;
        this.optional = false;
    }

    private DateTimeFormatterBuilder(DateTimeFormatterBuilder parent, boolean optional) {
        this.parent = parent;
        this.optional = optional;
    }

    public DateTimeFormatterBuilder parseCaseSensitive() {
        this.appendInternal(SettingsParser.SENSITIVE);
        return this;
    }

    public DateTimeFormatterBuilder parseCaseInsensitive() {
        this.appendInternal(SettingsParser.INSENSITIVE);
        return this;
    }

    public DateTimeFormatterBuilder parseStrict() {
        this.appendInternal(SettingsParser.STRICT);
        return this;
    }

    public DateTimeFormatterBuilder parseLenient() {
        this.appendInternal(SettingsParser.LENIENT);
        return this;
    }

    public DateTimeFormatterBuilder parseDefaulting(TemporalField field2, long value) {
        Jdk8Methods.requireNonNull(field2, "field");
        this.appendInternal(new DefaultingParser(field2, value));
        return this;
    }

    public DateTimeFormatterBuilder appendValue(TemporalField field2) {
        Jdk8Methods.requireNonNull(field2, "field");
        this.appendValue(new NumberPrinterParser(field2, 1, 19, SignStyle.NORMAL));
        return this;
    }

    public DateTimeFormatterBuilder appendValue(TemporalField field2, int width) {
        Jdk8Methods.requireNonNull(field2, "field");
        if (width < 1 || width > 19) {
            throw new IllegalArgumentException("The width must be from 1 to 19 inclusive but was " + width);
        }
        NumberPrinterParser pp = new NumberPrinterParser(field2, width, width, SignStyle.NOT_NEGATIVE);
        this.appendValue(pp);
        return this;
    }

    public DateTimeFormatterBuilder appendValue(TemporalField field2, int minWidth, int maxWidth2, SignStyle signStyle) {
        if (minWidth == maxWidth2 && signStyle == SignStyle.NOT_NEGATIVE) {
            return this.appendValue(field2, maxWidth2);
        }
        Jdk8Methods.requireNonNull(field2, "field");
        Jdk8Methods.requireNonNull(signStyle, "signStyle");
        if (minWidth < 1 || minWidth > 19) {
            throw new IllegalArgumentException("The minimum width must be from 1 to 19 inclusive but was " + minWidth);
        }
        if (maxWidth2 < 1 || maxWidth2 > 19) {
            throw new IllegalArgumentException("The maximum width must be from 1 to 19 inclusive but was " + maxWidth2);
        }
        if (maxWidth2 < minWidth) {
            throw new IllegalArgumentException("The maximum width must exceed or equal the minimum width but " + maxWidth2 + " < " + minWidth);
        }
        NumberPrinterParser pp = new NumberPrinterParser(field2, minWidth, maxWidth2, signStyle);
        this.appendValue(pp);
        return this;
    }

    public DateTimeFormatterBuilder appendValueReduced(TemporalField field2, int width, int maxWidth2, int baseValue) {
        Jdk8Methods.requireNonNull(field2, "field");
        ReducedPrinterParser pp = new ReducedPrinterParser(field2, width, maxWidth2, baseValue, null);
        this.appendValue(pp);
        return this;
    }

    public DateTimeFormatterBuilder appendValueReduced(TemporalField field2, int width, int maxWidth2, ChronoLocalDate baseDate) {
        Jdk8Methods.requireNonNull(field2, "field");
        Jdk8Methods.requireNonNull(baseDate, "baseDate");
        ReducedPrinterParser pp = new ReducedPrinterParser(field2, width, maxWidth2, 0, baseDate);
        this.appendValue(pp);
        return this;
    }

    private DateTimeFormatterBuilder appendValue(NumberPrinterParser pp) {
        if (this.active.valueParserIndex >= 0 && this.active.printerParsers.get(this.active.valueParserIndex) instanceof NumberPrinterParser) {
            int activeValueParser = this.active.valueParserIndex;
            NumberPrinterParser basePP = (NumberPrinterParser)this.active.printerParsers.get(activeValueParser);
            if (pp.minWidth == pp.maxWidth && pp.signStyle == SignStyle.NOT_NEGATIVE) {
                basePP = basePP.withSubsequentWidth(pp.maxWidth);
                this.appendInternal(pp.withFixedWidth());
                this.active.valueParserIndex = activeValueParser;
            } else {
                basePP = basePP.withFixedWidth();
                this.active.valueParserIndex = this.appendInternal(pp);
            }
            this.active.printerParsers.set(activeValueParser, basePP);
        } else {
            this.active.valueParserIndex = this.appendInternal(pp);
        }
        return this;
    }

    public DateTimeFormatterBuilder appendFraction(TemporalField field2, int minWidth, int maxWidth2, boolean decimalPoint) {
        this.appendInternal(new FractionPrinterParser(field2, minWidth, maxWidth2, decimalPoint));
        return this;
    }

    public DateTimeFormatterBuilder appendText(TemporalField field2) {
        return this.appendText(field2, TextStyle.FULL);
    }

    public DateTimeFormatterBuilder appendText(TemporalField field2, TextStyle textStyle) {
        Jdk8Methods.requireNonNull(field2, "field");
        Jdk8Methods.requireNonNull(textStyle, "textStyle");
        this.appendInternal(new TextPrinterParser(field2, textStyle, DateTimeTextProvider.getInstance()));
        return this;
    }

    public DateTimeFormatterBuilder appendText(TemporalField field2, Map<Long, String> textLookup) {
        Jdk8Methods.requireNonNull(field2, "field");
        Jdk8Methods.requireNonNull(textLookup, "textLookup");
        LinkedHashMap<Long, String> copy2 = new LinkedHashMap<Long, String>(textLookup);
        Map<TextStyle, Map<Long, String>> map2 = Collections.singletonMap(TextStyle.FULL, copy2);
        final SimpleDateTimeTextProvider.LocaleStore store = new SimpleDateTimeTextProvider.LocaleStore(map2);
        DateTimeTextProvider provider = new DateTimeTextProvider(){

            @Override
            public String getText(TemporalField field2, long value, TextStyle style, Locale locale) {
                return store.getText(value, style);
            }

            @Override
            public Iterator<Map.Entry<String, Long>> getTextIterator(TemporalField field2, TextStyle style, Locale locale) {
                return store.getTextIterator(style);
            }
        };
        this.appendInternal(new TextPrinterParser(field2, TextStyle.FULL, provider));
        return this;
    }

    public DateTimeFormatterBuilder appendInstant() {
        this.appendInternal(new InstantPrinterParser(-2));
        return this;
    }

    public DateTimeFormatterBuilder appendInstant(int fractionalDigits) {
        if (fractionalDigits < -1 || fractionalDigits > 9) {
            throw new IllegalArgumentException("Invalid fractional digits: " + fractionalDigits);
        }
        this.appendInternal(new InstantPrinterParser(fractionalDigits));
        return this;
    }

    public DateTimeFormatterBuilder appendOffsetId() {
        this.appendInternal(OffsetIdPrinterParser.INSTANCE_ID);
        return this;
    }

    public DateTimeFormatterBuilder appendOffset(String pattern, String noOffsetText) {
        this.appendInternal(new OffsetIdPrinterParser(noOffsetText, pattern));
        return this;
    }

    public DateTimeFormatterBuilder appendLocalizedOffset(TextStyle style) {
        Jdk8Methods.requireNonNull(style, "style");
        if (style != TextStyle.FULL && style != TextStyle.SHORT) {
            throw new IllegalArgumentException("Style must be either full or short");
        }
        this.appendInternal(new LocalizedOffsetPrinterParser(style));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneId() {
        this.appendInternal(new ZoneIdPrinterParser(TemporalQueries.zoneId(), "ZoneId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneRegionId() {
        this.appendInternal(new ZoneIdPrinterParser(QUERY_REGION_ONLY, "ZoneRegionId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneOrOffsetId() {
        this.appendInternal(new ZoneIdPrinterParser(TemporalQueries.zone(), "ZoneOrOffsetId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneText(TextStyle textStyle) {
        this.appendInternal(new ZoneTextPrinterParser(textStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneText(TextStyle textStyle, Set<ZoneId> preferredZones) {
        Jdk8Methods.requireNonNull(preferredZones, "preferredZones");
        this.appendInternal(new ZoneTextPrinterParser(textStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendChronologyId() {
        this.appendInternal(new ChronoPrinterParser(null));
        return this;
    }

    public DateTimeFormatterBuilder appendChronologyText(TextStyle textStyle) {
        Jdk8Methods.requireNonNull(textStyle, "textStyle");
        this.appendInternal(new ChronoPrinterParser(textStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendLocalized(FormatStyle dateStyle, FormatStyle timeStyle) {
        if (dateStyle == null && timeStyle == null) {
            throw new IllegalArgumentException("Either the date or time style must be non-null");
        }
        this.appendInternal(new LocalizedPrinterParser(dateStyle, timeStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendLiteral(char literal) {
        this.appendInternal(new CharLiteralPrinterParser(literal));
        return this;
    }

    public DateTimeFormatterBuilder appendLiteral(String literal) {
        Jdk8Methods.requireNonNull(literal, "literal");
        if (literal.length() > 0) {
            if (literal.length() == 1) {
                this.appendInternal(new CharLiteralPrinterParser(literal.charAt(0)));
            } else {
                this.appendInternal(new StringLiteralPrinterParser(literal));
            }
        }
        return this;
    }

    public DateTimeFormatterBuilder append(DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        this.appendInternal(formatter.toPrinterParser(false));
        return this;
    }

    public DateTimeFormatterBuilder appendOptional(DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        this.appendInternal(formatter.toPrinterParser(true));
        return this;
    }

    public DateTimeFormatterBuilder appendPattern(String pattern) {
        Jdk8Methods.requireNonNull(pattern, "pattern");
        this.parsePattern(pattern);
        return this;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void parsePattern(String pattern) {
        for (int pos = 0; pos < pattern.length(); ++pos) {
            int start;
            char cur = pattern.charAt(pos);
            if (cur >= 'A' && cur <= 'Z' || cur >= 'a' && cur <= 'z') {
                TemporalField field2;
                start = pos++;
                while (pos < pattern.length() && pattern.charAt(pos) == cur) {
                    ++pos;
                }
                int count2 = pos - start;
                if (cur == 'p') {
                    int pad = 0;
                    if (pos < pattern.length() && ((cur = pattern.charAt(pos)) >= 'A' && cur <= 'Z' || cur >= 'a' && cur <= 'z')) {
                        pad = count2;
                        start = pos++;
                        while (pos < pattern.length() && pattern.charAt(pos) == cur) {
                            ++pos;
                        }
                        count2 = pos - start;
                    }
                    if (pad == 0) {
                        throw new IllegalArgumentException("Pad letter 'p' must be followed by valid pad pattern: " + pattern);
                    }
                    this.padNext(pad);
                }
                if ((field2 = FIELD_MAP.get(Character.valueOf(cur))) != null) {
                    this.parseField(cur, count2, field2);
                } else if (cur == 'z') {
                    if (count2 > 4) {
                        throw new IllegalArgumentException("Too many pattern letters: " + cur);
                    }
                    if (count2 == 4) {
                        this.appendZoneText(TextStyle.FULL);
                    } else {
                        this.appendZoneText(TextStyle.SHORT);
                    }
                } else if (cur == 'V') {
                    if (count2 != 2) {
                        throw new IllegalArgumentException("Pattern letter count must be 2: " + cur);
                    }
                    this.appendZoneId();
                } else if (cur == 'Z') {
                    if (count2 < 4) {
                        this.appendOffset("+HHMM", "+0000");
                    } else if (count2 == 4) {
                        this.appendLocalizedOffset(TextStyle.FULL);
                    } else {
                        if (count2 != 5) throw new IllegalArgumentException("Too many pattern letters: " + cur);
                        this.appendOffset("+HH:MM:ss", "Z");
                    }
                } else if (cur == 'O') {
                    if (count2 == 1) {
                        this.appendLocalizedOffset(TextStyle.SHORT);
                    } else {
                        if (count2 != 4) throw new IllegalArgumentException("Pattern letter count must be 1 or 4: " + cur);
                        this.appendLocalizedOffset(TextStyle.FULL);
                    }
                } else if (cur == 'X') {
                    if (count2 > 5) {
                        throw new IllegalArgumentException("Too many pattern letters: " + cur);
                    }
                    this.appendOffset(OffsetIdPrinterParser.PATTERNS[count2 + (count2 == 1 ? 0 : 1)], "Z");
                } else if (cur == 'x') {
                    if (count2 > 5) {
                        throw new IllegalArgumentException("Too many pattern letters: " + cur);
                    }
                    String zero = count2 == 1 ? "+00" : (count2 % 2 == 0 ? "+0000" : "+00:00");
                    this.appendOffset(OffsetIdPrinterParser.PATTERNS[count2 + (count2 == 1 ? 0 : 1)], zero);
                } else if (cur == 'W') {
                    if (count2 > 1) {
                        throw new IllegalArgumentException("Too many pattern letters: " + cur);
                    }
                    this.appendInternal(new WeekFieldsPrinterParser('W', count2));
                } else if (cur == 'w') {
                    if (count2 > 2) {
                        throw new IllegalArgumentException("Too many pattern letters: " + cur);
                    }
                    this.appendInternal(new WeekFieldsPrinterParser('w', count2));
                } else {
                    if (cur != 'Y') throw new IllegalArgumentException("Unknown pattern letter: " + cur);
                    this.appendInternal(new WeekFieldsPrinterParser('Y', count2));
                }
                --pos;
                continue;
            }
            if (cur == '\'') {
                start = pos++;
                while (pos < pattern.length()) {
                    if (pattern.charAt(pos) == '\'') {
                        if (pos + 1 >= pattern.length() || pattern.charAt(pos + 1) != '\'') break;
                        ++pos;
                    }
                    ++pos;
                }
                if (pos >= pattern.length()) {
                    throw new IllegalArgumentException("Pattern ends with an incomplete string literal: " + pattern);
                }
                String str = pattern.substring(start + 1, pos);
                if (str.length() == 0) {
                    this.appendLiteral('\'');
                    continue;
                }
                this.appendLiteral(str.replace("''", "'"));
                continue;
            }
            if (cur == '[') {
                this.optionalStart();
                continue;
            }
            if (cur == ']') {
                if (this.active.parent == null) {
                    throw new IllegalArgumentException("Pattern invalid as it contains ] without previous [");
                }
                this.optionalEnd();
                continue;
            }
            if (cur == '{' || cur == '}' || cur == '#') {
                throw new IllegalArgumentException("Pattern includes reserved character: '" + cur + "'");
            }
            this.appendLiteral(cur);
        }
    }

    private void parseField(char cur, int count2, TemporalField field2) {
        block0 : switch (cur) {
            case 'u': 
            case 'y': {
                if (count2 == 2) {
                    this.appendValueReduced(field2, 2, 2, ReducedPrinterParser.BASE_DATE);
                    break;
                }
                if (count2 < 4) {
                    this.appendValue(field2, count2, 19, SignStyle.NORMAL);
                    break;
                }
                this.appendValue(field2, count2, 19, SignStyle.EXCEEDS_PAD);
                break;
            }
            case 'M': 
            case 'Q': {
                switch (count2) {
                    case 1: {
                        this.appendValue(field2);
                        break block0;
                    }
                    case 2: {
                        this.appendValue(field2, 2);
                        break block0;
                    }
                    case 3: {
                        this.appendText(field2, TextStyle.SHORT);
                        break block0;
                    }
                    case 4: {
                        this.appendText(field2, TextStyle.FULL);
                        break block0;
                    }
                    case 5: {
                        this.appendText(field2, TextStyle.NARROW);
                        break block0;
                    }
                }
                throw new IllegalArgumentException("Too many pattern letters: " + cur);
            }
            case 'L': 
            case 'q': {
                switch (count2) {
                    case 1: {
                        this.appendValue(field2);
                        break block0;
                    }
                    case 2: {
                        this.appendValue(field2, 2);
                        break block0;
                    }
                    case 3: {
                        this.appendText(field2, TextStyle.SHORT_STANDALONE);
                        break block0;
                    }
                    case 4: {
                        this.appendText(field2, TextStyle.FULL_STANDALONE);
                        break block0;
                    }
                    case 5: {
                        this.appendText(field2, TextStyle.NARROW_STANDALONE);
                        break block0;
                    }
                }
                throw new IllegalArgumentException("Too many pattern letters: " + cur);
            }
            case 'e': {
                switch (count2) {
                    case 1: 
                    case 2: {
                        this.appendInternal(new WeekFieldsPrinterParser('e', count2));
                        break block0;
                    }
                    case 3: {
                        this.appendText(field2, TextStyle.SHORT);
                        break block0;
                    }
                    case 4: {
                        this.appendText(field2, TextStyle.FULL);
                        break block0;
                    }
                    case 5: {
                        this.appendText(field2, TextStyle.NARROW);
                        break block0;
                    }
                }
                throw new IllegalArgumentException("Too many pattern letters: " + cur);
            }
            case 'c': {
                switch (count2) {
                    case 1: {
                        this.appendInternal(new WeekFieldsPrinterParser('c', count2));
                        break block0;
                    }
                    case 2: {
                        throw new IllegalArgumentException("Invalid number of pattern letters: " + cur);
                    }
                    case 3: {
                        this.appendText(field2, TextStyle.SHORT_STANDALONE);
                        break block0;
                    }
                    case 4: {
                        this.appendText(field2, TextStyle.FULL_STANDALONE);
                        break block0;
                    }
                    case 5: {
                        this.appendText(field2, TextStyle.NARROW_STANDALONE);
                        break block0;
                    }
                }
                throw new IllegalArgumentException("Too many pattern letters: " + cur);
            }
            case 'a': {
                if (count2 == 1) {
                    this.appendText(field2, TextStyle.SHORT);
                    break;
                }
                throw new IllegalArgumentException("Too many pattern letters: " + cur);
            }
            case 'E': 
            case 'G': {
                switch (count2) {
                    case 1: 
                    case 2: 
                    case 3: {
                        this.appendText(field2, TextStyle.SHORT);
                        break block0;
                    }
                    case 4: {
                        this.appendText(field2, TextStyle.FULL);
                        break block0;
                    }
                    case 5: {
                        this.appendText(field2, TextStyle.NARROW);
                        break block0;
                    }
                }
                throw new IllegalArgumentException("Too many pattern letters: " + cur);
            }
            case 'S': {
                this.appendFraction(ChronoField.NANO_OF_SECOND, count2, count2, false);
                break;
            }
            case 'F': {
                if (count2 == 1) {
                    this.appendValue(field2);
                    break;
                }
                throw new IllegalArgumentException("Too many pattern letters: " + cur);
            }
            case 'H': 
            case 'K': 
            case 'd': 
            case 'h': 
            case 'k': 
            case 'm': 
            case 's': {
                if (count2 == 1) {
                    this.appendValue(field2);
                    break;
                }
                if (count2 == 2) {
                    this.appendValue(field2, count2);
                    break;
                }
                throw new IllegalArgumentException("Too many pattern letters: " + cur);
            }
            case 'D': {
                if (count2 == 1) {
                    this.appendValue(field2);
                    break;
                }
                if (count2 <= 3) {
                    this.appendValue(field2, count2);
                    break;
                }
                throw new IllegalArgumentException("Too many pattern letters: " + cur);
            }
            default: {
                if (count2 == 1) {
                    this.appendValue(field2);
                    break;
                }
                this.appendValue(field2, count2);
            }
        }
    }

    public DateTimeFormatterBuilder padNext(int padWidth) {
        return this.padNext(padWidth, ' ');
    }

    public DateTimeFormatterBuilder padNext(int padWidth, char padChar) {
        if (padWidth < 1) {
            throw new IllegalArgumentException("The pad width must be at least one but was " + padWidth);
        }
        this.active.padNextWidth = padWidth;
        this.active.padNextChar = padChar;
        this.active.valueParserIndex = -1;
        return this;
    }

    public DateTimeFormatterBuilder optionalStart() {
        this.active.valueParserIndex = -1;
        this.active = new DateTimeFormatterBuilder(this.active, true);
        return this;
    }

    public DateTimeFormatterBuilder optionalEnd() {
        if (this.active.parent == null) {
            throw new IllegalStateException("Cannot call optionalEnd() as there was no previous call to optionalStart()");
        }
        if (this.active.printerParsers.size() > 0) {
            CompositePrinterParser cpp = new CompositePrinterParser(this.active.printerParsers, this.active.optional);
            this.active = this.active.parent;
            this.appendInternal(cpp);
        } else {
            this.active = this.active.parent;
        }
        return this;
    }

    private int appendInternal(DateTimePrinterParser pp) {
        Jdk8Methods.requireNonNull(pp, "pp");
        if (this.active.padNextWidth > 0) {
            if (pp != null) {
                pp = new PadPrinterParserDecorator(pp, this.active.padNextWidth, this.active.padNextChar);
            }
            this.active.padNextWidth = 0;
            this.active.padNextChar = '\u0000';
        }
        this.active.printerParsers.add(pp);
        this.active.valueParserIndex = -1;
        return this.active.printerParsers.size() - 1;
    }

    public DateTimeFormatter toFormatter() {
        return this.toFormatter(Locale.getDefault());
    }

    public DateTimeFormatter toFormatter(Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        while (this.active.parent != null) {
            this.optionalEnd();
        }
        CompositePrinterParser pp = new CompositePrinterParser(this.printerParsers, false);
        return new DateTimeFormatter(pp, locale, DecimalStyle.STANDARD, ResolverStyle.SMART, null, null, null);
    }

    DateTimeFormatter toFormatter(ResolverStyle style) {
        return this.toFormatter().withResolverStyle(style);
    }

    static {
        FIELD_MAP.put(Character.valueOf('G'), ChronoField.ERA);
        FIELD_MAP.put(Character.valueOf('y'), ChronoField.YEAR_OF_ERA);
        FIELD_MAP.put(Character.valueOf('u'), ChronoField.YEAR);
        FIELD_MAP.put(Character.valueOf('Q'), IsoFields.QUARTER_OF_YEAR);
        FIELD_MAP.put(Character.valueOf('q'), IsoFields.QUARTER_OF_YEAR);
        FIELD_MAP.put(Character.valueOf('M'), ChronoField.MONTH_OF_YEAR);
        FIELD_MAP.put(Character.valueOf('L'), ChronoField.MONTH_OF_YEAR);
        FIELD_MAP.put(Character.valueOf('D'), ChronoField.DAY_OF_YEAR);
        FIELD_MAP.put(Character.valueOf('d'), ChronoField.DAY_OF_MONTH);
        FIELD_MAP.put(Character.valueOf('F'), ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH);
        FIELD_MAP.put(Character.valueOf('E'), ChronoField.DAY_OF_WEEK);
        FIELD_MAP.put(Character.valueOf('c'), ChronoField.DAY_OF_WEEK);
        FIELD_MAP.put(Character.valueOf('e'), ChronoField.DAY_OF_WEEK);
        FIELD_MAP.put(Character.valueOf('a'), ChronoField.AMPM_OF_DAY);
        FIELD_MAP.put(Character.valueOf('H'), ChronoField.HOUR_OF_DAY);
        FIELD_MAP.put(Character.valueOf('k'), ChronoField.CLOCK_HOUR_OF_DAY);
        FIELD_MAP.put(Character.valueOf('K'), ChronoField.HOUR_OF_AMPM);
        FIELD_MAP.put(Character.valueOf('h'), ChronoField.CLOCK_HOUR_OF_AMPM);
        FIELD_MAP.put(Character.valueOf('m'), ChronoField.MINUTE_OF_HOUR);
        FIELD_MAP.put(Character.valueOf('s'), ChronoField.SECOND_OF_MINUTE);
        FIELD_MAP.put(Character.valueOf('S'), ChronoField.NANO_OF_SECOND);
        FIELD_MAP.put(Character.valueOf('A'), ChronoField.MILLI_OF_DAY);
        FIELD_MAP.put(Character.valueOf('n'), ChronoField.NANO_OF_SECOND);
        FIELD_MAP.put(Character.valueOf('N'), ChronoField.NANO_OF_DAY);
        LENGTH_SORT = new Comparator<String>(){

            @Override
            public int compare(String str1, String str2) {
                return str1.length() == str2.length() ? str1.compareTo(str2) : str1.length() - str2.length();
            }
        };
    }

    static final class WeekFieldsPrinterParser
    implements DateTimePrinterParser {
        private final char letter;
        private final int count;

        public WeekFieldsPrinterParser(char letter, int count2) {
            this.letter = letter;
            this.count = count2;
        }

        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            WeekFields weekFields = WeekFields.of(context.getLocale());
            DateTimePrinterParser pp = this.evaluate(weekFields);
            return pp.print(context, buf);
        }

        @Override
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            WeekFields weekFields = WeekFields.of(context.getLocale());
            DateTimePrinterParser pp = this.evaluate(weekFields);
            return pp.parse(context, text, position);
        }

        private DateTimePrinterParser evaluate(WeekFields weekFields) {
            NumberPrinterParser pp = null;
            switch (this.letter) {
                case 'e': {
                    pp = new NumberPrinterParser(weekFields.dayOfWeek(), this.count, 2, SignStyle.NOT_NEGATIVE);
                    break;
                }
                case 'c': {
                    pp = new NumberPrinterParser(weekFields.dayOfWeek(), this.count, 2, SignStyle.NOT_NEGATIVE);
                    break;
                }
                case 'w': {
                    pp = new NumberPrinterParser(weekFields.weekOfWeekBasedYear(), this.count, 2, SignStyle.NOT_NEGATIVE);
                    break;
                }
                case 'W': {
                    pp = new NumberPrinterParser(weekFields.weekOfMonth(), 1, 2, SignStyle.NOT_NEGATIVE);
                    break;
                }
                case 'Y': {
                    pp = this.count == 2 ? new ReducedPrinterParser(weekFields.weekBasedYear(), 2, 2, 0, ReducedPrinterParser.BASE_DATE) : new NumberPrinterParser(weekFields.weekBasedYear(), this.count, 19, this.count < 4 ? SignStyle.NORMAL : SignStyle.EXCEEDS_PAD, -1);
                }
            }
            return pp;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Localized(");
            if (this.letter == 'Y') {
                if (this.count == 1) {
                    sb.append("WeekBasedYear");
                } else if (this.count == 2) {
                    sb.append("ReducedValue(WeekBasedYear,2,2,2000-01-01)");
                } else {
                    sb.append("WeekBasedYear,").append(this.count).append(",").append(19).append(",").append((Object)(this.count < 4 ? SignStyle.NORMAL : SignStyle.EXCEEDS_PAD));
                }
            } else {
                if (this.letter == 'c' || this.letter == 'e') {
                    sb.append("DayOfWeek");
                } else if (this.letter == 'w') {
                    sb.append("WeekOfWeekBasedYear");
                } else if (this.letter == 'W') {
                    sb.append("WeekOfMonth");
                }
                sb.append(",");
                sb.append(this.count);
            }
            sb.append(")");
            return sb.toString();
        }
    }

    static final class LocalizedPrinterParser
    implements DateTimePrinterParser {
        private final FormatStyle dateStyle;
        private final FormatStyle timeStyle;

        LocalizedPrinterParser(FormatStyle dateStyle, FormatStyle timeStyle) {
            this.dateStyle = dateStyle;
            this.timeStyle = timeStyle;
        }

        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            Chronology chrono = Chronology.from(context.getTemporal());
            return this.formatter(context.getLocale(), chrono).toPrinterParser(false).print(context, buf);
        }

        @Override
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            Chronology chrono = context.getEffectiveChronology();
            return this.formatter(context.getLocale(), chrono).toPrinterParser(false).parse(context, text, position);
        }

        private DateTimeFormatter formatter(Locale locale, Chronology chrono) {
            return DateTimeFormatStyleProvider.getInstance().getFormatter(this.dateStyle, this.timeStyle, chrono, locale);
        }

        public String toString() {
            return "Localized(" + (this.dateStyle != null ? this.dateStyle : "") + "," + (this.timeStyle != null ? this.timeStyle : "") + ")";
        }
    }

    static final class ChronoPrinterParser
    implements DateTimePrinterParser {
        private final TextStyle textStyle;

        ChronoPrinterParser(TextStyle textStyle) {
            this.textStyle = textStyle;
        }

        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            Chronology chrono = context.getValue(TemporalQueries.chronology());
            if (chrono == null) {
                return false;
            }
            if (this.textStyle == null) {
                buf.append(chrono.getId());
            } else {
                ResourceBundle bundle = ResourceBundle.getBundle("org.threeten.bp.format.ChronologyText", context.getLocale(), DateTimeFormatterBuilder.class.getClassLoader());
                try {
                    String text = bundle.getString(chrono.getId());
                    buf.append(text);
                }
                catch (MissingResourceException ex) {
                    buf.append(chrono.getId());
                }
            }
            return true;
        }

        @Override
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            if (position < 0 || position > text.length()) {
                throw new IndexOutOfBoundsException();
            }
            Set<Chronology> chronos = Chronology.getAvailableChronologies();
            Chronology bestMatch = null;
            int matchLen = -1;
            for (Chronology chrono : chronos) {
                String id = chrono.getId();
                int idLen = id.length();
                if (idLen <= matchLen || !context.subSequenceEquals(text, position, id, 0, idLen)) continue;
                bestMatch = chrono;
                matchLen = idLen;
            }
            if (bestMatch == null) {
                return ~position;
            }
            context.setParsed(bestMatch);
            return position + matchLen;
        }
    }

    static final class ZoneIdPrinterParser
    implements DateTimePrinterParser {
        private final TemporalQuery<ZoneId> query;
        private final String description;
        private static volatile Map.Entry<Integer, SubstringTree> cachedSubstringTree;

        ZoneIdPrinterParser(TemporalQuery<ZoneId> query, String description) {
            this.query = query;
            this.description = description;
        }

        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            ZoneId zone = context.getValue(this.query);
            if (zone == null) {
                return false;
            }
            buf.append(zone.getId());
            return true;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            int nodeLength;
            int length = text.length();
            if (position > length) {
                throw new IndexOutOfBoundsException();
            }
            if (position == length) {
                return ~position;
            }
            char nextChar = text.charAt(position);
            if (nextChar == '+' || nextChar == '-') {
                DateTimeParseContext newContext = context.copy();
                int endPos = OffsetIdPrinterParser.INSTANCE_ID.parse(newContext, text, position);
                if (endPos < 0) {
                    return endPos;
                }
                int offset = (int)newContext.getParsed(ChronoField.OFFSET_SECONDS).longValue();
                ZoneOffset zone = ZoneOffset.ofTotalSeconds(offset);
                context.setParsed(zone);
                return endPos;
            }
            if (length >= position + 2) {
                char nextNextChar = text.charAt(position + 1);
                if (context.charEquals(nextChar, 'U') && context.charEquals(nextNextChar, 'T')) {
                    if (length >= position + 3 && context.charEquals(text.charAt(position + 2), 'C')) {
                        return this.parsePrefixedOffset(context, text, position, position + 3);
                    }
                    return this.parsePrefixedOffset(context, text, position, position + 2);
                }
                if (context.charEquals(nextChar, 'G') && length >= position + 3 && context.charEquals(nextNextChar, 'M') && context.charEquals(text.charAt(position + 2), 'T')) {
                    return this.parsePrefixedOffset(context, text, position, position + 3);
                }
            }
            Set<String> regionIds = ZoneRulesProvider.getAvailableZoneIds();
            int regionIdsSize = regionIds.size();
            Map.Entry<Integer, SubstringTree> cached = cachedSubstringTree;
            if (cached == null || cached.getKey() != regionIdsSize) {
                ZoneIdPrinterParser zone = this;
                synchronized (zone) {
                    cached = cachedSubstringTree;
                    if (cached == null || cached.getKey() != regionIdsSize) {
                        cachedSubstringTree = cached = new AbstractMap.SimpleImmutableEntry<Integer, SubstringTree>(regionIdsSize, ZoneIdPrinterParser.prepareParser(regionIds));
                    }
                }
            }
            SubstringTree tree = cached.getValue();
            String parsedZoneId = null;
            String lastZoneId = null;
            while (tree != null && position + (nodeLength = tree.length) <= length) {
                lastZoneId = parsedZoneId;
                parsedZoneId = ((Object)text.subSequence(position, position + nodeLength)).toString();
                tree = tree.get(parsedZoneId, context.isCaseSensitive());
            }
            ZoneId zone = this.convertToZone(regionIds, parsedZoneId, context.isCaseSensitive());
            if (zone == null) {
                zone = this.convertToZone(regionIds, lastZoneId, context.isCaseSensitive());
                if (zone == null) {
                    if (context.charEquals(nextChar, 'Z')) {
                        context.setParsed(ZoneOffset.UTC);
                        return position + 1;
                    }
                    return ~position;
                }
                parsedZoneId = lastZoneId;
            }
            context.setParsed(zone);
            return position + parsedZoneId.length();
        }

        private ZoneId convertToZone(Set<String> regionIds, String parsedZoneId, boolean caseSensitive) {
            if (parsedZoneId == null) {
                return null;
            }
            if (caseSensitive) {
                return regionIds.contains(parsedZoneId) ? ZoneId.of(parsedZoneId) : null;
            }
            for (String regionId : regionIds) {
                if (!regionId.equalsIgnoreCase(parsedZoneId)) continue;
                return ZoneId.of(regionId);
            }
            return null;
        }

        private int parsePrefixedOffset(DateTimeParseContext context, CharSequence text, int prefixPos, int position) {
            String prefix = ((Object)text.subSequence(prefixPos, position)).toString().toUpperCase();
            DateTimeParseContext newContext = context.copy();
            if (position < text.length() && context.charEquals(text.charAt(position), 'Z')) {
                context.setParsed(ZoneId.ofOffset(prefix, ZoneOffset.UTC));
                return position;
            }
            int endPos = OffsetIdPrinterParser.INSTANCE_ID.parse(newContext, text, position);
            if (endPos < 0) {
                context.setParsed(ZoneId.ofOffset(prefix, ZoneOffset.UTC));
                return position;
            }
            int offsetSecs = (int)newContext.getParsed(ChronoField.OFFSET_SECONDS).longValue();
            ZoneOffset offset = ZoneOffset.ofTotalSeconds(offsetSecs);
            context.setParsed(ZoneId.ofOffset(prefix, offset));
            return endPos;
        }

        private static SubstringTree prepareParser(Set<String> availableIDs) {
            ArrayList<String> ids = new ArrayList<String>(availableIDs);
            Collections.sort(ids, LENGTH_SORT);
            SubstringTree tree = new SubstringTree(((String)ids.get(0)).length());
            for (String id : ids) {
                tree.add(id);
            }
            return tree;
        }

        public String toString() {
            return this.description;
        }

        private static final class SubstringTree {
            final int length;
            private final Map<CharSequence, SubstringTree> substringMap = new HashMap<CharSequence, SubstringTree>();
            private final Map<String, SubstringTree> substringMapCI = new HashMap<String, SubstringTree>();

            private SubstringTree(int length) {
                this.length = length;
            }

            private SubstringTree get(CharSequence substring2, boolean caseSensitive) {
                if (caseSensitive) {
                    return this.substringMap.get(substring2);
                }
                return this.substringMapCI.get(((Object)substring2).toString().toLowerCase(Locale.ENGLISH));
            }

            private void add(String newSubstring) {
                int idLen = newSubstring.length();
                if (idLen == this.length) {
                    this.substringMap.put(newSubstring, null);
                    this.substringMapCI.put(newSubstring.toLowerCase(Locale.ENGLISH), null);
                } else if (idLen > this.length) {
                    String substring = newSubstring.substring(0, this.length);
                    SubstringTree parserTree = this.substringMap.get(substring);
                    if (parserTree == null) {
                        parserTree = new SubstringTree(idLen);
                        this.substringMap.put(substring, parserTree);
                        this.substringMapCI.put(substring.toLowerCase(Locale.ENGLISH), parserTree);
                    }
                    parserTree.add(newSubstring);
                }
            }
        }
    }

    static final class ZoneTextPrinterParser
    implements DateTimePrinterParser {
        private static final Comparator<String> LENGTH_COMPARATOR = new Comparator<String>(){

            @Override
            public int compare(String str1, String str2) {
                int cmp = str2.length() - str1.length();
                if (cmp == 0) {
                    cmp = str1.compareTo(str2);
                }
                return cmp;
            }
        };
        private final TextStyle textStyle;

        ZoneTextPrinterParser(TextStyle textStyle) {
            this.textStyle = Jdk8Methods.requireNonNull(textStyle, "textStyle");
        }

        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            ZoneId zone = context.getValue(TemporalQueries.zoneId());
            if (zone == null) {
                return false;
            }
            if (zone.normalized() instanceof ZoneOffset) {
                buf.append(zone.getId());
                return true;
            }
            TemporalAccessor temporal = context.getTemporal();
            boolean daylight = false;
            if (temporal.isSupported(ChronoField.INSTANT_SECONDS)) {
                Instant instant = Instant.ofEpochSecond(temporal.getLong(ChronoField.INSTANT_SECONDS));
                daylight = zone.getRules().isDaylightSavings(instant);
            }
            TimeZone tz = TimeZone.getTimeZone(zone.getId());
            int tzstyle = this.textStyle.asNormal() == TextStyle.FULL ? 1 : 0;
            String text = tz.getDisplayName(daylight, tzstyle, context.getLocale());
            buf.append(text);
            return true;
        }

        @Override
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            TreeMap<String, String> ids = new TreeMap<String, String>(LENGTH_COMPARATOR);
            for (String string2 : ZoneId.getAvailableZoneIds()) {
                ids.put(string2, string2);
                TimeZone tz = TimeZone.getTimeZone(string2);
                int tzstyle = this.textStyle.asNormal() == TextStyle.FULL ? 1 : 0;
                ids.put(tz.getDisplayName(false, tzstyle, context.getLocale()), string2);
                ids.put(tz.getDisplayName(true, tzstyle, context.getLocale()), string2);
            }
            for (Map.Entry entry : ids.entrySet()) {
                String name = (String)entry.getKey();
                if (!context.subSequenceEquals(text, position, name, 0, name.length())) continue;
                context.setParsed(ZoneId.of((String)entry.getValue()));
                return position + name.length();
            }
            return ~position;
        }

        public String toString() {
            return "ZoneText(" + (Object)((Object)this.textStyle) + ")";
        }
    }

    static final class LocalizedOffsetPrinterParser
    implements DateTimePrinterParser {
        private final TextStyle style;

        public LocalizedOffsetPrinterParser(TextStyle style) {
            this.style = style;
        }

        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            Long offsetSecs = context.getValue(ChronoField.OFFSET_SECONDS);
            if (offsetSecs == null) {
                return false;
            }
            buf.append("GMT");
            if (this.style == TextStyle.FULL) {
                return new OffsetIdPrinterParser("", "+HH:MM:ss").print(context, buf);
            }
            int totalSecs = Jdk8Methods.safeToInt(offsetSecs);
            if (totalSecs != 0) {
                int absHours = Math.abs(totalSecs / 3600 % 100);
                int absMinutes = Math.abs(totalSecs / 60 % 60);
                int absSeconds = Math.abs(totalSecs % 60);
                buf.append(totalSecs < 0 ? "-" : "+").append(absHours);
                if (absMinutes > 0 || absSeconds > 0) {
                    buf.append(":").append((char)(absMinutes / 10 + 48)).append((char)(absMinutes % 10 + 48));
                    if (absSeconds > 0) {
                        buf.append(":").append((char)(absSeconds / 10 + 48)).append((char)(absSeconds % 10 + 48));
                    }
                }
            }
            return true;
        }

        @Override
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            char ch;
            int negative;
            if (!context.subSequenceEquals(text, position, "GMT", 0, 3)) {
                return ~position;
            }
            position += 3;
            if (this.style == TextStyle.FULL) {
                return new OffsetIdPrinterParser("", "+HH:MM:ss").parse(context, text, position);
            }
            int end = text.length();
            if (position == end) {
                return context.setParsedField(ChronoField.OFFSET_SECONDS, 0L, position, position);
            }
            char sign = text.charAt(position);
            if (sign != '+' && sign != '-') {
                return context.setParsedField(ChronoField.OFFSET_SECONDS, 0L, position, position);
            }
            int n = negative = sign == '-' ? -1 : 1;
            if (position == end) {
                return ~position;
            }
            if ((ch = text.charAt(++position)) < '0' || ch > '9') {
                return ~position;
            }
            int hour = ch - 48;
            if (++position != end && (ch = text.charAt(position)) >= '0' && ch <= '9') {
                if ((hour = hour * 10 + (ch - 48)) > 23) {
                    return ~position;
                }
                ++position;
            }
            if (position == end || text.charAt(position) != ':') {
                int offset = negative * 3600 * hour;
                return context.setParsedField(ChronoField.OFFSET_SECONDS, offset, position, position);
            }
            if (++position > end - 2) {
                return ~position;
            }
            ch = text.charAt(position);
            if (ch < '0' || ch > '9') {
                return ~position;
            }
            int min2 = ch - 48;
            if ((ch = text.charAt(++position)) < '0' || ch > '9') {
                return ~position;
            }
            ++position;
            if ((min2 = min2 * 10 + (ch - 48)) > 59) {
                return ~position;
            }
            if (position == end || text.charAt(position) != ':') {
                int offset = negative * (3600 * hour + 60 * min2);
                return context.setParsedField(ChronoField.OFFSET_SECONDS, offset, position, position);
            }
            if (++position > end - 2) {
                return ~position;
            }
            ch = text.charAt(position);
            if (ch < '0' || ch > '9') {
                return ~position;
            }
            int sec = ch - 48;
            if ((ch = text.charAt(++position)) < '0' || ch > '9') {
                return ~position;
            }
            ++position;
            if ((sec = sec * 10 + (ch - 48)) > 59) {
                return ~position;
            }
            int offset = negative * (3600 * hour + 60 * min2 + sec);
            return context.setParsedField(ChronoField.OFFSET_SECONDS, offset, position, position);
        }
    }

    static final class OffsetIdPrinterParser
    implements DateTimePrinterParser {
        static final String[] PATTERNS = new String[]{"+HH", "+HHmm", "+HH:mm", "+HHMM", "+HH:MM", "+HHMMss", "+HH:MM:ss", "+HHMMSS", "+HH:MM:SS"};
        static final OffsetIdPrinterParser INSTANCE_ID = new OffsetIdPrinterParser("Z", "+HH:MM:ss");
        private final String noOffsetText;
        private final int type;

        OffsetIdPrinterParser(String noOffsetText, String pattern) {
            Jdk8Methods.requireNonNull(noOffsetText, "noOffsetText");
            Jdk8Methods.requireNonNull(pattern, "pattern");
            this.noOffsetText = noOffsetText;
            this.type = this.checkPattern(pattern);
        }

        private int checkPattern(String pattern) {
            for (int i = 0; i < PATTERNS.length; ++i) {
                if (!PATTERNS[i].equals(pattern)) continue;
                return i;
            }
            throw new IllegalArgumentException("Invalid zone offset pattern: " + pattern);
        }

        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            Long offsetSecs = context.getValue(ChronoField.OFFSET_SECONDS);
            if (offsetSecs == null) {
                return false;
            }
            int totalSecs = Jdk8Methods.safeToInt(offsetSecs);
            if (totalSecs == 0) {
                buf.append(this.noOffsetText);
            } else {
                int absHours = Math.abs(totalSecs / 3600 % 100);
                int absMinutes = Math.abs(totalSecs / 60 % 60);
                int absSeconds = Math.abs(totalSecs % 60);
                int bufPos = buf.length();
                int output = absHours;
                buf.append(totalSecs < 0 ? "-" : "+").append((char)(absHours / 10 + 48)).append((char)(absHours % 10 + 48));
                if (this.type >= 3 || this.type >= 1 && absMinutes > 0) {
                    buf.append(this.type % 2 == 0 ? ":" : "").append((char)(absMinutes / 10 + 48)).append((char)(absMinutes % 10 + 48));
                    output += absMinutes;
                    if (this.type >= 7 || this.type >= 5 && absSeconds > 0) {
                        buf.append(this.type % 2 == 0 ? ":" : "").append((char)(absSeconds / 10 + 48)).append((char)(absSeconds % 10 + 48));
                        output += absSeconds;
                    }
                }
                if (output == 0) {
                    buf.setLength(bufPos);
                    buf.append(this.noOffsetText);
                }
            }
            return true;
        }

        @Override
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            char sign;
            int length = text.length();
            int noOffsetLen = this.noOffsetText.length();
            if (noOffsetLen == 0) {
                if (position == length) {
                    return context.setParsedField(ChronoField.OFFSET_SECONDS, 0L, position, position);
                }
            } else {
                if (position == length) {
                    return ~position;
                }
                if (context.subSequenceEquals(text, position, this.noOffsetText, 0, noOffsetLen)) {
                    return context.setParsedField(ChronoField.OFFSET_SECONDS, 0L, position, position + noOffsetLen);
                }
            }
            if ((sign = text.charAt(position)) == '+' || sign == '-') {
                int negative = sign == '-' ? -1 : 1;
                int[] array = new int[4];
                array[0] = position + 1;
                if (!(this.parseNumber(array, 1, text, true) || this.parseNumber(array, 2, text, this.type >= 3) || this.parseNumber(array, 3, text, false))) {
                    long offsetSecs = (long)negative * ((long)array[1] * 3600L + (long)array[2] * 60L + (long)array[3]);
                    return context.setParsedField(ChronoField.OFFSET_SECONDS, offsetSecs, position, array[0]);
                }
            }
            if (noOffsetLen == 0) {
                return context.setParsedField(ChronoField.OFFSET_SECONDS, 0L, position, position + noOffsetLen);
            }
            return ~position;
        }

        private boolean parseNumber(int[] array, int arrayIndex, CharSequence parseText, boolean required) {
            if ((this.type + 3) / 2 < arrayIndex) {
                return false;
            }
            int pos = array[0];
            if (this.type % 2 == 0 && arrayIndex > 1) {
                if (pos + 1 > parseText.length() || parseText.charAt(pos) != ':') {
                    return required;
                }
                ++pos;
            }
            if (pos + 2 > parseText.length()) {
                return required;
            }
            char ch1 = parseText.charAt(pos++);
            char ch2 = parseText.charAt(pos++);
            if (ch1 < '0' || ch1 > '9' || ch2 < '0' || ch2 > '9') {
                return required;
            }
            int value = (ch1 - 48) * 10 + (ch2 - 48);
            if (value < 0 || value > 59) {
                return required;
            }
            array[arrayIndex] = value;
            array[0] = pos;
            return false;
        }

        public String toString() {
            String converted = this.noOffsetText.replace("'", "''");
            return "Offset(" + PATTERNS[this.type] + ",'" + converted + "')";
        }
    }

    static final class InstantPrinterParser
    implements DateTimePrinterParser {
        private static final long SECONDS_PER_10000_YEARS = 315569520000L;
        private static final long SECONDS_0000_TO_1970 = 62167219200L;
        private final int fractionalDigits;

        InstantPrinterParser(int fractionalDigits) {
            this.fractionalDigits = fractionalDigits;
        }

        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            LocalDateTime ldt;
            long lo;
            long hi;
            long zeroSecs;
            Long inSecs = context.getValue(ChronoField.INSTANT_SECONDS);
            Long inNanos = 0L;
            if (context.getTemporal().isSupported(ChronoField.NANO_OF_SECOND)) {
                inNanos = context.getTemporal().getLong(ChronoField.NANO_OF_SECOND);
            }
            if (inSecs == null) {
                return false;
            }
            long inSec = inSecs;
            int inNano = ChronoField.NANO_OF_SECOND.checkValidIntValue(inNanos);
            if (inSec >= -62167219200L) {
                zeroSecs = inSec - 315569520000L + 62167219200L;
                hi = Jdk8Methods.floorDiv(zeroSecs, 315569520000L) + 1L;
                lo = Jdk8Methods.floorMod(zeroSecs, 315569520000L);
                ldt = LocalDateTime.ofEpochSecond(lo - 62167219200L, 0, ZoneOffset.UTC);
                if (hi > 0L) {
                    buf.append('+').append(hi);
                }
                buf.append(ldt);
                if (ldt.getSecond() == 0) {
                    buf.append(":00");
                }
            } else {
                zeroSecs = inSec + 62167219200L;
                hi = zeroSecs / 315569520000L;
                lo = zeroSecs % 315569520000L;
                ldt = LocalDateTime.ofEpochSecond(lo - 62167219200L, 0, ZoneOffset.UTC);
                int pos = buf.length();
                buf.append(ldt);
                if (ldt.getSecond() == 0) {
                    buf.append(":00");
                }
                if (hi < 0L) {
                    if (ldt.getYear() == -10000) {
                        buf.replace(pos, pos + 2, Long.toString(hi - 1L));
                    } else if (lo == 0L) {
                        buf.insert(pos, hi);
                    } else {
                        buf.insert(pos + 1, Math.abs(hi));
                    }
                }
            }
            if (this.fractionalDigits == -2) {
                if (inNano != 0) {
                    buf.append('.');
                    if (inNano % 1000000 == 0) {
                        buf.append(Integer.toString(inNano / 1000000 + 1000).substring(1));
                    } else if (inNano % 1000 == 0) {
                        buf.append(Integer.toString(inNano / 1000 + 1000000).substring(1));
                    } else {
                        buf.append(Integer.toString(inNano + 1000000000).substring(1));
                    }
                }
            } else if (this.fractionalDigits > 0 || this.fractionalDigits == -1 && inNano > 0) {
                buf.append('.');
                int div = 100000000;
                for (int i = 0; this.fractionalDigits == -1 && inNano > 0 || i < this.fractionalDigits; ++i) {
                    int digit = inNano / div;
                    buf.append((char)(digit + 48));
                    inNano -= digit * div;
                    div /= 10;
                }
            }
            buf.append('Z');
            return true;
        }

        @Override
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            DateTimeParseContext newContext = context.copy();
            int minDigits = this.fractionalDigits < 0 ? 0 : this.fractionalDigits;
            int maxDigits = this.fractionalDigits < 0 ? 9 : this.fractionalDigits;
            CompositePrinterParser parser = new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_LOCAL_DATE).appendLiteral('T').appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral(':').appendValue(ChronoField.MINUTE_OF_HOUR, 2).appendLiteral(':').appendValue(ChronoField.SECOND_OF_MINUTE, 2).appendFraction(ChronoField.NANO_OF_SECOND, minDigits, maxDigits, true).appendLiteral('Z').toFormatter().toPrinterParser(false);
            int pos = parser.parse(newContext, text, position);
            if (pos < 0) {
                return pos;
            }
            long yearParsed = newContext.getParsed(ChronoField.YEAR);
            int month = newContext.getParsed(ChronoField.MONTH_OF_YEAR).intValue();
            int day = newContext.getParsed(ChronoField.DAY_OF_MONTH).intValue();
            int hour = newContext.getParsed(ChronoField.HOUR_OF_DAY).intValue();
            int min2 = newContext.getParsed(ChronoField.MINUTE_OF_HOUR).intValue();
            Long secVal = newContext.getParsed(ChronoField.SECOND_OF_MINUTE);
            Long nanoVal = newContext.getParsed(ChronoField.NANO_OF_SECOND);
            int sec = secVal != null ? secVal.intValue() : 0;
            int nano = nanoVal != null ? nanoVal.intValue() : 0;
            int year = (int)yearParsed % 10000;
            int days = 0;
            if (hour == 24 && min2 == 0 && sec == 0 && nano == 0) {
                hour = 0;
                days = 1;
            } else if (hour == 23 && min2 == 59 && sec == 60) {
                context.setParsedLeapSecond();
                sec = 59;
            }
            try {
                LocalDateTime ldt = LocalDateTime.of(year, month, day, hour, min2, sec, 0).plusDays(days);
                long instantSecs = ldt.toEpochSecond(ZoneOffset.UTC);
            }
            catch (RuntimeException ex) {
                return ~position;
            }
            int successPos = pos;
            successPos = context.setParsedField(ChronoField.INSTANT_SECONDS, instantSecs += Jdk8Methods.safeMultiply(yearParsed / 10000L, 315569520000L), position, successPos);
            return context.setParsedField(ChronoField.NANO_OF_SECOND, nano, position, successPos);
        }

        public String toString() {
            return "Instant()";
        }
    }

    static final class TextPrinterParser
    implements DateTimePrinterParser {
        private final TemporalField field;
        private final TextStyle textStyle;
        private final DateTimeTextProvider provider;
        private volatile NumberPrinterParser numberPrinterParser;

        TextPrinterParser(TemporalField field2, TextStyle textStyle, DateTimeTextProvider provider) {
            this.field = field2;
            this.textStyle = textStyle;
            this.provider = provider;
        }

        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            Long value = context.getValue(this.field);
            if (value == null) {
                return false;
            }
            String text = this.provider.getText(this.field, value, this.textStyle, context.getLocale());
            if (text == null) {
                return this.numberPrinterParser().print(context, buf);
            }
            buf.append(text);
            return true;
        }

        @Override
        public int parse(DateTimeParseContext context, CharSequence parseText, int position) {
            int length = parseText.length();
            if (position < 0 || position > length) {
                throw new IndexOutOfBoundsException();
            }
            TextStyle style = context.isStrict() ? this.textStyle : null;
            Iterator<Map.Entry<String, Long>> it = this.provider.getTextIterator(this.field, style, context.getLocale());
            if (it != null) {
                while (it.hasNext()) {
                    Map.Entry<String, Long> entry = it.next();
                    String itText = entry.getKey();
                    if (!context.subSequenceEquals(itText, 0, parseText, position, itText.length())) continue;
                    return context.setParsedField(this.field, entry.getValue(), position, position + itText.length());
                }
                if (context.isStrict()) {
                    return ~position;
                }
            }
            return this.numberPrinterParser().parse(context, parseText, position);
        }

        private NumberPrinterParser numberPrinterParser() {
            if (this.numberPrinterParser == null) {
                this.numberPrinterParser = new NumberPrinterParser(this.field, 1, 19, SignStyle.NORMAL);
            }
            return this.numberPrinterParser;
        }

        public String toString() {
            if (this.textStyle == TextStyle.FULL) {
                return "Text(" + this.field + ")";
            }
            return "Text(" + this.field + "," + (Object)((Object)this.textStyle) + ")";
        }
    }

    static final class FractionPrinterParser
    implements DateTimePrinterParser {
        private final TemporalField field;
        private final int minWidth;
        private final int maxWidth;
        private final boolean decimalPoint;

        FractionPrinterParser(TemporalField field2, int minWidth, int maxWidth2, boolean decimalPoint) {
            Jdk8Methods.requireNonNull(field2, "field");
            if (!field2.range().isFixed()) {
                throw new IllegalArgumentException("Field must have a fixed set of values: " + field2);
            }
            if (minWidth < 0 || minWidth > 9) {
                throw new IllegalArgumentException("Minimum width must be from 0 to 9 inclusive but was " + minWidth);
            }
            if (maxWidth2 < 1 || maxWidth2 > 9) {
                throw new IllegalArgumentException("Maximum width must be from 1 to 9 inclusive but was " + maxWidth2);
            }
            if (maxWidth2 < minWidth) {
                throw new IllegalArgumentException("Maximum width must exceed or equal the minimum width but " + maxWidth2 + " < " + minWidth);
            }
            this.field = field2;
            this.minWidth = minWidth;
            this.maxWidth = maxWidth2;
            this.decimalPoint = decimalPoint;
        }

        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            Long value = context.getValue(this.field);
            if (value == null) {
                return false;
            }
            DecimalStyle symbols = context.getSymbols();
            BigDecimal fraction = this.convertToFraction(value);
            if (fraction.scale() == 0) {
                if (this.minWidth > 0) {
                    if (this.decimalPoint) {
                        buf.append(symbols.getDecimalSeparator());
                    }
                    for (int i = 0; i < this.minWidth; ++i) {
                        buf.append(symbols.getZeroDigit());
                    }
                }
            } else {
                int outputScale = Math.min(Math.max(fraction.scale(), this.minWidth), this.maxWidth);
                fraction = fraction.setScale(outputScale, RoundingMode.FLOOR);
                String str = fraction.toPlainString().substring(2);
                str = symbols.convertNumberToI18N(str);
                if (this.decimalPoint) {
                    buf.append(symbols.getDecimalSeparator());
                }
                buf.append(str);
            }
            return true;
        }

        @Override
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            int minEndPos;
            int effectiveMin = context.isStrict() ? this.minWidth : 0;
            int effectiveMax = context.isStrict() ? this.maxWidth : 9;
            int length = text.length();
            if (position == length) {
                return effectiveMin > 0 ? ~position : position;
            }
            if (this.decimalPoint) {
                if (text.charAt(position) != context.getSymbols().getDecimalSeparator()) {
                    return effectiveMin > 0 ? ~position : position;
                }
                ++position;
            }
            if ((minEndPos = position + effectiveMin) > length) {
                return ~position;
            }
            int maxEndPos = Math.min(position + effectiveMax, length);
            int total2 = 0;
            int pos = position;
            while (pos < maxEndPos) {
                char ch = text.charAt(pos++);
                int digit = context.getSymbols().convertToDigit(ch);
                if (digit < 0) {
                    if (pos < minEndPos) {
                        return ~position;
                    }
                    --pos;
                    break;
                }
                total2 = total2 * 10 + digit;
            }
            BigDecimal fraction = new BigDecimal(total2).movePointLeft(pos - position);
            long value = this.convertFromFraction(fraction);
            return context.setParsedField(this.field, value, position, pos);
        }

        private BigDecimal convertToFraction(long value) {
            ValueRange range2 = this.field.range();
            range2.checkValidValue(value, this.field);
            BigDecimal minBD = BigDecimal.valueOf(range2.getMinimum());
            BigDecimal rangeBD = BigDecimal.valueOf(range2.getMaximum()).subtract(minBD).add(BigDecimal.ONE);
            BigDecimal valueBD = BigDecimal.valueOf(value).subtract(minBD);
            BigDecimal fraction = valueBD.divide(rangeBD, 9, RoundingMode.FLOOR);
            return fraction.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : fraction.stripTrailingZeros();
        }

        private long convertFromFraction(BigDecimal fraction) {
            ValueRange range2 = this.field.range();
            BigDecimal minBD = BigDecimal.valueOf(range2.getMinimum());
            BigDecimal rangeBD = BigDecimal.valueOf(range2.getMaximum()).subtract(minBD).add(BigDecimal.ONE);
            BigDecimal valueBD = fraction.multiply(rangeBD).setScale(0, RoundingMode.FLOOR).add(minBD);
            return valueBD.longValueExact();
        }

        public String toString() {
            String decimal = this.decimalPoint ? ",DecimalPoint" : "";
            return "Fraction(" + this.field + "," + this.minWidth + "," + this.maxWidth + decimal + ")";
        }
    }

    static final class ReducedPrinterParser
    extends NumberPrinterParser {
        static final LocalDate BASE_DATE = LocalDate.of(2000, 1, 1);
        private final int baseValue;
        private final ChronoLocalDate baseDate;

        ReducedPrinterParser(TemporalField field2, int width, int maxWidth2, int baseValue, ChronoLocalDate baseDate) {
            super(field2, width, maxWidth2, SignStyle.NOT_NEGATIVE);
            if (width < 1 || width > 10) {
                throw new IllegalArgumentException("The width must be from 1 to 10 inclusive but was " + width);
            }
            if (maxWidth2 < 1 || maxWidth2 > 10) {
                throw new IllegalArgumentException("The maxWidth must be from 1 to 10 inclusive but was " + maxWidth2);
            }
            if (maxWidth2 < width) {
                throw new IllegalArgumentException("The maxWidth must be greater than the width");
            }
            if (baseDate == null) {
                if (!field2.range().isValidValue(baseValue)) {
                    throw new IllegalArgumentException("The base value must be within the range of the field");
                }
                if ((long)baseValue + (long)EXCEED_POINTS[width] > Integer.MAX_VALUE) {
                    throw new DateTimeException("Unable to add printer-parser as the range exceeds the capacity of an int");
                }
            }
            this.baseValue = baseValue;
            this.baseDate = baseDate;
        }

        private ReducedPrinterParser(TemporalField field2, int minWidth, int maxWidth2, int baseValue, ChronoLocalDate baseDate, int subsequentWidth) {
            super(field2, minWidth, maxWidth2, SignStyle.NOT_NEGATIVE, subsequentWidth);
            this.baseValue = baseValue;
            this.baseDate = baseDate;
        }

        @Override
        long getValue(DateTimePrintContext context, long value) {
            long absValue = Math.abs(value);
            int baseValue = this.baseValue;
            if (this.baseDate != null) {
                Chronology chrono = Chronology.from(context.getTemporal());
                baseValue = chrono.date(this.baseDate).get(this.field);
            }
            if (value >= (long)baseValue && value < (long)(baseValue + EXCEED_POINTS[this.minWidth])) {
                return absValue % (long)EXCEED_POINTS[this.minWidth];
            }
            return absValue % (long)EXCEED_POINTS[this.maxWidth];
        }

        @Override
        int setValue(DateTimeParseContext context, long value, int errorPos, int successPos) {
            int parseLen;
            int baseValue = this.baseValue;
            if (this.baseDate != null) {
                Chronology chrono = context.getEffectiveChronology();
                baseValue = chrono.date(this.baseDate).get(this.field);
                context.addChronologyChangedParser(this, value, errorPos, successPos);
            }
            if ((parseLen = successPos - errorPos) == this.minWidth && value >= 0L) {
                long range2 = EXCEED_POINTS[this.minWidth];
                long lastPart = (long)baseValue % range2;
                long basePart = (long)baseValue - lastPart;
                value = baseValue > 0 ? basePart + value : basePart - value;
                if (value < (long)baseValue) {
                    value += range2;
                }
            }
            return context.setParsedField(this.field, value, errorPos, successPos);
        }

        @Override
        NumberPrinterParser withFixedWidth() {
            if (this.subsequentWidth == -1) {
                return this;
            }
            return new ReducedPrinterParser(this.field, this.minWidth, this.maxWidth, this.baseValue, this.baseDate, -1);
        }

        @Override
        ReducedPrinterParser withSubsequentWidth(int subsequentWidth) {
            return new ReducedPrinterParser(this.field, this.minWidth, this.maxWidth, this.baseValue, this.baseDate, this.subsequentWidth + subsequentWidth);
        }

        @Override
        boolean isFixedWidth(DateTimeParseContext context) {
            if (!context.isStrict()) {
                return false;
            }
            return super.isFixedWidth(context);
        }

        @Override
        public String toString() {
            return "ReducedValue(" + this.field + "," + this.minWidth + "," + this.maxWidth + "," + (this.baseDate != null ? this.baseDate : Integer.valueOf(this.baseValue)) + ")";
        }
    }

    static class NumberPrinterParser
    implements DateTimePrinterParser {
        static final int[] EXCEED_POINTS = new int[]{0, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
        final TemporalField field;
        final int minWidth;
        final int maxWidth;
        final SignStyle signStyle;
        final int subsequentWidth;

        NumberPrinterParser(TemporalField field2, int minWidth, int maxWidth2, SignStyle signStyle) {
            this.field = field2;
            this.minWidth = minWidth;
            this.maxWidth = maxWidth2;
            this.signStyle = signStyle;
            this.subsequentWidth = 0;
        }

        private NumberPrinterParser(TemporalField field2, int minWidth, int maxWidth2, SignStyle signStyle, int subsequentWidth) {
            this.field = field2;
            this.minWidth = minWidth;
            this.maxWidth = maxWidth2;
            this.signStyle = signStyle;
            this.subsequentWidth = subsequentWidth;
        }

        NumberPrinterParser withFixedWidth() {
            if (this.subsequentWidth == -1) {
                return this;
            }
            return new NumberPrinterParser(this.field, this.minWidth, this.maxWidth, this.signStyle, -1);
        }

        NumberPrinterParser withSubsequentWidth(int subsequentWidth) {
            return new NumberPrinterParser(this.field, this.minWidth, this.maxWidth, this.signStyle, this.subsequentWidth + subsequentWidth);
        }

        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            String str;
            Long valueLong = context.getValue(this.field);
            if (valueLong == null) {
                return false;
            }
            long value = this.getValue(context, valueLong);
            DecimalStyle symbols = context.getSymbols();
            String string2 = str = value == Long.MIN_VALUE ? "9223372036854775808" : Long.toString(Math.abs(value));
            if (str.length() > this.maxWidth) {
                throw new DateTimeException("Field " + this.field + " cannot be printed as the value " + value + " exceeds the maximum print width of " + this.maxWidth);
            }
            str = symbols.convertNumberToI18N(str);
            if (value >= 0L) {
                switch (this.signStyle) {
                    case EXCEEDS_PAD: {
                        if (this.minWidth >= 19 || value < (long)EXCEED_POINTS[this.minWidth]) break;
                        buf.append(symbols.getPositiveSign());
                        break;
                    }
                    case ALWAYS: {
                        buf.append(symbols.getPositiveSign());
                    }
                }
            } else {
                switch (this.signStyle) {
                    case EXCEEDS_PAD: 
                    case ALWAYS: 
                    case NORMAL: {
                        buf.append(symbols.getNegativeSign());
                        break;
                    }
                    case NOT_NEGATIVE: {
                        throw new DateTimeException("Field " + this.field + " cannot be printed as the value " + value + " cannot be negative according to the SignStyle");
                    }
                }
            }
            for (int i = 0; i < this.minWidth - str.length(); ++i) {
                buf.append(symbols.getZeroDigit());
            }
            buf.append(str);
            return true;
        }

        long getValue(DateTimePrintContext context, long value) {
            return value;
        }

        boolean isFixedWidth(DateTimeParseContext context) {
            return this.subsequentWidth == -1 || this.subsequentWidth > 0 && this.minWidth == this.maxWidth && this.signStyle == SignStyle.NOT_NEGATIVE;
        }

        @Override
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            int length = text.length();
            if (position == length) {
                return ~position;
            }
            char sign = text.charAt(position);
            boolean negative = false;
            boolean positive = false;
            if (sign == context.getSymbols().getPositiveSign()) {
                if (!this.signStyle.parse(true, context.isStrict(), this.minWidth == this.maxWidth)) {
                    return ~position;
                }
                positive = true;
                ++position;
            } else if (sign == context.getSymbols().getNegativeSign()) {
                if (!this.signStyle.parse(false, context.isStrict(), this.minWidth == this.maxWidth)) {
                    return ~position;
                }
                negative = true;
                ++position;
            } else if (this.signStyle == SignStyle.ALWAYS && context.isStrict()) {
                return ~position;
            }
            int effMinWidth = context.isStrict() || this.isFixedWidth(context) ? this.minWidth : 1;
            int minEndPos = position + effMinWidth;
            if (minEndPos > length) {
                return ~position;
            }
            int effMaxWidth = (context.isStrict() || this.isFixedWidth(context) ? this.maxWidth : 9) + Math.max(this.subsequentWidth, 0);
            long total2 = 0L;
            BigInteger totalBig = null;
            int pos = position;
            for (int pass = 0; pass < 2; ++pass) {
                int maxEndPos = Math.min(pos + effMaxWidth, length);
                while (pos < maxEndPos) {
                    char ch = text.charAt(pos++);
                    int digit = context.getSymbols().convertToDigit(ch);
                    if (digit < 0) {
                        if (--pos >= minEndPos) break;
                        return ~position;
                    }
                    if (pos - position > 18) {
                        if (totalBig == null) {
                            totalBig = BigInteger.valueOf(total2);
                        }
                        totalBig = totalBig.multiply(BigInteger.TEN).add(BigInteger.valueOf(digit));
                        continue;
                    }
                    total2 = total2 * 10L + (long)digit;
                }
                if (this.subsequentWidth <= 0 || pass != 0) break;
                int parseLen = pos - position;
                effMaxWidth = Math.max(effMinWidth, parseLen - this.subsequentWidth);
                pos = position;
                total2 = 0L;
                totalBig = null;
            }
            if (negative) {
                if (totalBig != null) {
                    if (totalBig.equals(BigInteger.ZERO) && context.isStrict()) {
                        return ~(position - 1);
                    }
                    totalBig = totalBig.negate();
                } else {
                    if (total2 == 0L && context.isStrict()) {
                        return ~(position - 1);
                    }
                    total2 = -total2;
                }
            } else if (this.signStyle == SignStyle.EXCEEDS_PAD && context.isStrict()) {
                int parseLen = pos - position;
                if (positive) {
                    if (parseLen <= this.minWidth) {
                        return ~(position - 1);
                    }
                } else if (parseLen > this.minWidth) {
                    return ~position;
                }
            }
            if (totalBig != null) {
                if (totalBig.bitLength() > 63) {
                    totalBig = totalBig.divide(BigInteger.TEN);
                    --pos;
                }
                return this.setValue(context, totalBig.longValue(), position, pos);
            }
            return this.setValue(context, total2, position, pos);
        }

        int setValue(DateTimeParseContext context, long value, int errorPos, int successPos) {
            return context.setParsedField(this.field, value, errorPos, successPos);
        }

        public String toString() {
            if (this.minWidth == 1 && this.maxWidth == 19 && this.signStyle == SignStyle.NORMAL) {
                return "Value(" + this.field + ")";
            }
            if (this.minWidth == this.maxWidth && this.signStyle == SignStyle.NOT_NEGATIVE) {
                return "Value(" + this.field + "," + this.minWidth + ")";
            }
            return "Value(" + this.field + "," + this.minWidth + "," + this.maxWidth + "," + (Object)((Object)this.signStyle) + ")";
        }
    }

    static final class StringLiteralPrinterParser
    implements DateTimePrinterParser {
        private final String literal;

        StringLiteralPrinterParser(String literal) {
            this.literal = literal;
        }

        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            buf.append(this.literal);
            return true;
        }

        @Override
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            int length = text.length();
            if (position > length || position < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (!context.subSequenceEquals(text, position, this.literal, 0, this.literal.length())) {
                return ~position;
            }
            return position + this.literal.length();
        }

        public String toString() {
            String converted = this.literal.replace("'", "''");
            return "'" + converted + "'";
        }
    }

    static final class CharLiteralPrinterParser
    implements DateTimePrinterParser {
        private final char literal;

        CharLiteralPrinterParser(char literal) {
            this.literal = literal;
        }

        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            buf.append(this.literal);
            return true;
        }

        @Override
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            int length = text.length();
            if (position == length) {
                return ~position;
            }
            char ch = text.charAt(position);
            if (!context.charEquals(this.literal, ch)) {
                return ~position;
            }
            return position + 1;
        }

        public String toString() {
            if (this.literal == '\'') {
                return "''";
            }
            return "'" + this.literal + "'";
        }
    }

    static class DefaultingParser
    implements DateTimePrinterParser {
        private final TemporalField field;
        private final long value;

        DefaultingParser(TemporalField field2, long value) {
            this.field = field2;
            this.value = value;
        }

        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            return true;
        }

        @Override
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            if (context.getParsed(this.field) == null) {
                context.setParsedField(this.field, this.value, position, position);
            }
            return position;
        }
    }

    static enum SettingsParser implements DateTimePrinterParser
    {
        SENSITIVE,
        INSENSITIVE,
        STRICT,
        LENIENT;


        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            return true;
        }

        @Override
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            switch (this.ordinal()) {
                case 0: {
                    context.setCaseSensitive(true);
                    break;
                }
                case 1: {
                    context.setCaseSensitive(false);
                    break;
                }
                case 2: {
                    context.setStrict(true);
                    break;
                }
                case 3: {
                    context.setStrict(false);
                }
            }
            return position;
        }

        public String toString() {
            switch (this.ordinal()) {
                case 0: {
                    return "ParseCaseSensitive(true)";
                }
                case 1: {
                    return "ParseCaseSensitive(false)";
                }
                case 2: {
                    return "ParseStrict(true)";
                }
                case 3: {
                    return "ParseStrict(false)";
                }
            }
            throw new IllegalStateException("Unreachable");
        }
    }

    static final class PadPrinterParserDecorator
    implements DateTimePrinterParser {
        private final DateTimePrinterParser printerParser;
        private final int padWidth;
        private final char padChar;

        PadPrinterParserDecorator(DateTimePrinterParser printerParser, int padWidth, char padChar) {
            this.printerParser = printerParser;
            this.padWidth = padWidth;
            this.padChar = padChar;
        }

        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            int preLen = buf.length();
            if (!this.printerParser.print(context, buf)) {
                return false;
            }
            int len = buf.length() - preLen;
            if (len > this.padWidth) {
                throw new DateTimeException("Cannot print as output of " + len + " characters exceeds pad width of " + this.padWidth);
            }
            for (int i = 0; i < this.padWidth - len; ++i) {
                buf.insert(preLen, this.padChar);
            }
            return true;
        }

        @Override
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            int pos;
            boolean strict = context.isStrict();
            boolean caseSensitive = context.isCaseSensitive();
            if (position > text.length()) {
                throw new IndexOutOfBoundsException();
            }
            if (position == text.length()) {
                return ~position;
            }
            int endPos = position + this.padWidth;
            if (endPos > text.length()) {
                if (strict) {
                    return ~position;
                }
                endPos = text.length();
            }
            for (pos = position; pos < endPos && (caseSensitive ? text.charAt(pos) == this.padChar : context.charEquals(text.charAt(pos), this.padChar)); ++pos) {
            }
            int resultPos = this.printerParser.parse(context, text = text.subSequence(0, endPos), pos);
            if (resultPos != endPos && strict) {
                return ~(position + pos);
            }
            return resultPos;
        }

        public String toString() {
            return "Pad(" + this.printerParser + "," + this.padWidth + (this.padChar == ' ' ? ")" : ",'" + this.padChar + "')");
        }
    }

    static final class CompositePrinterParser
    implements DateTimePrinterParser {
        private final DateTimePrinterParser[] printerParsers;
        private final boolean optional;

        CompositePrinterParser(List<DateTimePrinterParser> printerParsers, boolean optional) {
            this(printerParsers.toArray(new DateTimePrinterParser[printerParsers.size()]), optional);
        }

        CompositePrinterParser(DateTimePrinterParser[] printerParsers, boolean optional) {
            this.printerParsers = printerParsers;
            this.optional = optional;
        }

        public CompositePrinterParser withOptional(boolean optional) {
            if (optional == this.optional) {
                return this;
            }
            return new CompositePrinterParser(this.printerParsers, optional);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean print(DateTimePrintContext context, StringBuilder buf) {
            int length = buf.length();
            if (this.optional) {
                context.startOptional();
            }
            try {
                for (DateTimePrinterParser pp : this.printerParsers) {
                    if (pp.print(context, buf)) continue;
                    buf.setLength(length);
                    boolean bl = true;
                    return bl;
                }
            }
            finally {
                if (this.optional) {
                    context.endOptional();
                }
            }
            return true;
        }

        @Override
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            DateTimePrinterParser pp;
            if (this.optional) {
                context.startOptional();
                int pos = position;
                for (DateTimePrinterParser pp2 : this.printerParsers) {
                    if ((pos = pp2.parse(context, text, pos)) >= 0) continue;
                    context.endOptional(false);
                    return position;
                }
                context.endOptional(true);
                return pos;
            }
            DateTimePrinterParser[] arr$ = this.printerParsers;
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$ && (position = (pp = arr$[i$]).parse(context, text, position)) >= 0; ++i$) {
            }
            return position;
        }

        public String toString() {
            StringBuilder buf = new StringBuilder();
            if (this.printerParsers != null) {
                buf.append(this.optional ? "[" : "(");
                for (DateTimePrinterParser pp : this.printerParsers) {
                    buf.append(pp);
                }
                buf.append(this.optional ? "]" : ")");
            }
            return buf.toString();
        }
    }

    static interface DateTimePrinterParser {
        public boolean print(DateTimePrintContext var1, StringBuilder var2);

        public int parse(DateTimeParseContext var1, CharSequence var2, int var3);
    }
}

