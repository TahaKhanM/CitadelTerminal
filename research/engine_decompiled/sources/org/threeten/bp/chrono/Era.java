/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.util.Locale;
import org.threeten.bp.format.TextStyle;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;

public interface Era
extends TemporalAccessor,
TemporalAdjuster {
    public int getValue();

    public String getDisplayName(TextStyle var1, Locale var2);
}

