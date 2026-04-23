/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

final class SoundexUtils {
    SoundexUtils() {
    }

    static String clean(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        int len = str.length();
        char[] chars = new char[len];
        int count2 = 0;
        for (int i = 0; i < len; ++i) {
            if (!Character.isLetter(str.charAt(i))) continue;
            chars[count2++] = str.charAt(i);
        }
        if (count2 == len) {
            return str.toUpperCase(Locale.ENGLISH);
        }
        return new String(chars, 0, count2).toUpperCase(Locale.ENGLISH);
    }

    static int difference(StringEncoder encoder, String s1, String s2) throws EncoderException {
        return SoundexUtils.differenceEncoded(encoder.encode(s1), encoder.encode(s2));
    }

    static int differenceEncoded(String es1, String es2) {
        if (es1 == null || es2 == null) {
            return 0;
        }
        int lengthToMatch = Math.min(es1.length(), es2.length());
        int diff2 = 0;
        for (int i = 0; i < lengthToMatch; ++i) {
            if (es1.charAt(i) != es2.charAt(i)) continue;
            ++diff2;
        }
        return diff2;
    }
}

