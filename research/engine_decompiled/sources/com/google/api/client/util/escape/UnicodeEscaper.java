/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util.escape;

import com.google.api.client.util.escape.Escaper;
import com.google.api.client.util.escape.Platform;

public abstract class UnicodeEscaper
extends Escaper {
    private static final int DEST_PAD = 32;

    protected abstract char[] escape(int var1);

    protected abstract int nextEscapeIndex(CharSequence var1, int var2, int var3);

    public abstract String escape(String var1);

    protected final String escapeSlow(String s2, int index) {
        int end = s2.length();
        char[] dest = Platform.charBufferFromThreadLocal();
        int destIndex = 0;
        int unescapedChunkStart = 0;
        while (index < end) {
            int cp = UnicodeEscaper.codePointAt(s2, index, end);
            if (cp < 0) {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
            char[] escaped2 = this.escape(cp);
            int nextIndex = index + (Character.isSupplementaryCodePoint(cp) ? 2 : 1);
            if (escaped2 != null) {
                int charsSkipped = index - unescapedChunkStart;
                int sizeNeeded = destIndex + charsSkipped + escaped2.length;
                if (dest.length < sizeNeeded) {
                    int destLength = sizeNeeded + end - index + 32;
                    dest = UnicodeEscaper.growBuffer(dest, destIndex, destLength);
                }
                if (charsSkipped > 0) {
                    s2.getChars(unescapedChunkStart, index, dest, destIndex);
                    destIndex += charsSkipped;
                }
                if (escaped2.length > 0) {
                    System.arraycopy(escaped2, 0, dest, destIndex, escaped2.length);
                    destIndex += escaped2.length;
                }
                unescapedChunkStart = nextIndex;
            }
            index = this.nextEscapeIndex(s2, nextIndex, end);
        }
        int charsSkipped = end - unescapedChunkStart;
        if (charsSkipped > 0) {
            int endIndex = destIndex + charsSkipped;
            if (dest.length < endIndex) {
                dest = UnicodeEscaper.growBuffer(dest, destIndex, endIndex);
            }
            s2.getChars(unescapedChunkStart, end, dest, destIndex);
            destIndex = endIndex;
        }
        return new String(dest, 0, destIndex);
    }

    protected static int codePointAt(CharSequence seq, int index, int end) {
        if (index < end) {
            char c1;
            if ((c1 = seq.charAt(index++)) < '\ud800' || c1 > '\udfff') {
                return c1;
            }
            if (c1 <= '\udbff') {
                if (index == end) {
                    return -c1;
                }
                char c2 = seq.charAt(index);
                if (Character.isLowSurrogate(c2)) {
                    return Character.toCodePoint(c1, c2);
                }
                throw new IllegalArgumentException("Expected low surrogate but got char '" + c2 + "' with value " + c2 + " at index " + index);
            }
            throw new IllegalArgumentException("Unexpected low surrogate character '" + c1 + "' with value " + c1 + " at index " + (index - 1));
        }
        throw new IndexOutOfBoundsException("Index exceeds specified range");
    }

    private static char[] growBuffer(char[] dest, int index, int size2) {
        char[] copy2 = new char[size2];
        if (index > 0) {
            System.arraycopy(dest, 0, copy2, 0, index);
        }
        return copy2;
    }
}

