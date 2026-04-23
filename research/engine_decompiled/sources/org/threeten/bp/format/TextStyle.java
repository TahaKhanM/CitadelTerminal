/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.format;

public enum TextStyle {
    FULL,
    FULL_STANDALONE,
    SHORT,
    SHORT_STANDALONE,
    NARROW,
    NARROW_STANDALONE;


    public boolean isStandalone() {
        return (this.ordinal() & 1) == 1;
    }

    public TextStyle asStandalone() {
        return TextStyle.values()[this.ordinal() | 1];
    }

    public TextStyle asNormal() {
        return TextStyle.values()[this.ordinal() & 0xFFFFFFFE];
    }
}

