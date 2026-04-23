/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharTypes;
import java.io.Serializable;

public abstract class CharacterEscapes
implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int ESCAPE_NONE = 0;
    public static final int ESCAPE_STANDARD = -1;
    public static final int ESCAPE_CUSTOM = -2;

    public abstract int[] getEscapeCodesForAscii();

    public abstract SerializableString getEscapeSequence(int var1);

    public static int[] standardAsciiEscapesForJSON() {
        int[] esc = CharTypes.get7BitOutputEscapes();
        int len = esc.length;
        int[] result2 = new int[len];
        System.arraycopy(esc, 0, result2, 0, esc.length);
        return result2;
    }
}

