/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.format;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.threeten.bp.format.SimpleDateTimeTextProvider;
import org.threeten.bp.format.TextStyle;
import org.threeten.bp.temporal.TemporalField;

abstract class DateTimeTextProvider {
    DateTimeTextProvider() {
    }

    static DateTimeTextProvider getInstance() {
        return new SimpleDateTimeTextProvider();
    }

    public Locale[] getAvailableLocales() {
        throw new UnsupportedOperationException();
    }

    public abstract String getText(TemporalField var1, long var2, TextStyle var4, Locale var5);

    public abstract Iterator<Map.Entry<String, Long>> getTextIterator(TemporalField var1, TextStyle var2, Locale var3);
}

