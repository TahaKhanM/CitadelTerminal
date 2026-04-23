/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.format.DateTimeFormatStyleProvider;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.FormatStyle;

final class SimpleDateTimeFormatStyleProvider
extends DateTimeFormatStyleProvider {
    private static final ConcurrentMap<String, Object> FORMATTER_CACHE = new ConcurrentHashMap<String, Object>(16, 0.75f, 2);

    SimpleDateTimeFormatStyleProvider() {
    }

    @Override
    public Locale[] getAvailableLocales() {
        return DateFormat.getAvailableLocales();
    }

    @Override
    public DateTimeFormatter getFormatter(FormatStyle dateStyle, FormatStyle timeStyle, Chronology chrono, Locale locale) {
        if (dateStyle == null && timeStyle == null) {
            throw new IllegalArgumentException("Date and Time style must not both be null");
        }
        String key = chrono.getId() + '|' + locale.toString() + '|' + (Object)((Object)dateStyle) + (Object)((Object)timeStyle);
        Object cached = FORMATTER_CACHE.get(key);
        if (cached != null) {
            if (cached.equals("")) {
                throw new IllegalArgumentException("Unable to convert DateFormat to DateTimeFormatter");
            }
            return (DateTimeFormatter)cached;
        }
        DateFormat dateFormat = dateStyle != null ? (timeStyle != null ? DateFormat.getDateTimeInstance(this.convertStyle(dateStyle), this.convertStyle(timeStyle), locale) : DateFormat.getDateInstance(this.convertStyle(dateStyle), locale)) : DateFormat.getTimeInstance(this.convertStyle(timeStyle), locale);
        if (dateFormat instanceof SimpleDateFormat) {
            String pattern = ((SimpleDateFormat)dateFormat).toPattern();
            DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(pattern).toFormatter(locale);
            FORMATTER_CACHE.putIfAbsent(key, formatter);
            return formatter;
        }
        FORMATTER_CACHE.putIfAbsent(key, "");
        throw new IllegalArgumentException("Unable to convert DateFormat to DateTimeFormatter");
    }

    private int convertStyle(FormatStyle style) {
        return style.ordinal();
    }
}

