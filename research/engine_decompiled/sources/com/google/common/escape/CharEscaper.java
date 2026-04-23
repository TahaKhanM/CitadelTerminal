/*
 * Decompiled with CFR 0.152.
 */
package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.escape.Escaper;
import com.google.common.escape.Platform;

@Beta
@GwtCompatible
public abstract class CharEscaper
extends Escaper {
    private static final int DEST_PAD_MULTIPLIER = 2;

    protected CharEscaper() {
    }

    @Override
    public String escape(String string2) {
        Preconditions.checkNotNull(string2);
        int length = string2.length();
        for (int index = 0; index < length; ++index) {
            if (this.escape(string2.charAt(index)) == null) continue;
            return this.escapeSlow(string2, index);
        }
        return string2;
    }

    protected final String escapeSlow(String s2, int index) {
        int slen = s2.length();
        char[] dest = Platform.charBufferFromThreadLocal();
        int destSize = dest.length;
        int destIndex = 0;
        int lastEscape = 0;
        while (index < slen) {
            char[] r = this.escape(s2.charAt(index));
            if (r != null) {
                int charsSkipped = index - lastEscape;
                int rlen = r.length;
                int sizeNeeded = destIndex + charsSkipped + rlen;
                if (destSize < sizeNeeded) {
                    destSize = sizeNeeded + 2 * (slen - index);
                    dest = CharEscaper.growBuffer(dest, destIndex, destSize);
                }
                if (charsSkipped > 0) {
                    s2.getChars(lastEscape, index, dest, destIndex);
                    destIndex += charsSkipped;
                }
                if (rlen > 0) {
                    System.arraycopy(r, 0, dest, destIndex, rlen);
                    destIndex += rlen;
                }
                lastEscape = index + 1;
            }
            ++index;
        }
        int charsLeft = slen - lastEscape;
        if (charsLeft > 0) {
            int sizeNeeded = destIndex + charsLeft;
            if (destSize < sizeNeeded) {
                dest = CharEscaper.growBuffer(dest, destIndex, sizeNeeded);
            }
            s2.getChars(lastEscape, slen, dest, destIndex);
            destIndex = sizeNeeded;
        }
        return new String(dest, 0, destIndex);
    }

    protected abstract char[] escape(char var1);

    private static char[] growBuffer(char[] dest, int index, int size2) {
        char[] copy2 = new char[size2];
        if (index > 0) {
            System.arraycopy(dest, 0, copy2, 0, index);
        }
        return copy2;
    }
}

