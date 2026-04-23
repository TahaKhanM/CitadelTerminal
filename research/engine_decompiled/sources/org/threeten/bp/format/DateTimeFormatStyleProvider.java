/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.format;

import java.util.Locale;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.FormatStyle;
import org.threeten.bp.format.SimpleDateTimeFormatStyleProvider;

abstract class DateTimeFormatStyleProvider {
    DateTimeFormatStyleProvider() {
    }

    static DateTimeFormatStyleProvider getInstance() {
        return new SimpleDateTimeFormatStyleProvider();
    }

    public Locale[] getAvailableLocales() {
        throw new UnsupportedOperationException();
    }

    public abstract DateTimeFormatter getFormatter(FormatStyle var1, FormatStyle var2, Chronology var3, Locale var4);
}

