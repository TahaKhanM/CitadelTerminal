/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.format;

public enum SignStyle {
    NORMAL,
    ALWAYS,
    NEVER,
    NOT_NEGATIVE,
    EXCEEDS_PAD;


    boolean parse(boolean positive, boolean strict, boolean fixedWidth) {
        switch (this.ordinal()) {
            case 0: {
                return !positive || !strict;
            }
            case 1: 
            case 4: {
                return true;
            }
        }
        return !strict && !fixedWidth;
    }
}

