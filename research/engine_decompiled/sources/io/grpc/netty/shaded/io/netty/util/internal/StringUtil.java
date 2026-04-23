/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.util.internal;

import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.InternalThreadLocalMap;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class StringUtil {
    public static final String EMPTY_STRING = "";
    public static final String NEWLINE = SystemPropertyUtil.get("line.separator", "\n");
    public static final char DOUBLE_QUOTE = '\"';
    public static final char COMMA = ',';
    public static final char LINE_FEED = '\n';
    public static final char CARRIAGE_RETURN = '\r';
    public static final char TAB = '\t';
    public static final char SPACE = ' ';
    private static final String[] BYTE2HEX_PAD = new String[256];
    private static final String[] BYTE2HEX_NOPAD = new String[256];
    private static final int CSV_NUMBER_ESCAPE_CHARACTERS = 7;
    private static final char PACKAGE_SEPARATOR_CHAR = '.';

    private StringUtil() {
    }

    public static String substringAfter(String value, char delim) {
        int pos = value.indexOf(delim);
        if (pos >= 0) {
            return value.substring(pos + 1);
        }
        return null;
    }

    public static boolean commonSuffixOfLength(String s2, String p, int len) {
        return s2 != null && p != null && len >= 0 && s2.regionMatches(s2.length() - len, p, p.length() - len, len);
    }

    public static String byteToHexStringPadded(int value) {
        return BYTE2HEX_PAD[value & 0xFF];
    }

    public static <T extends Appendable> T byteToHexStringPadded(T buf, int value) {
        try {
            buf.append(StringUtil.byteToHexStringPadded(value));
        }
        catch (IOException e) {
            PlatformDependent.throwException(e);
        }
        return buf;
    }

    public static String toHexStringPadded(byte[] src) {
        return StringUtil.toHexStringPadded(src, 0, src.length);
    }

    public static String toHexStringPadded(byte[] src, int offset, int length) {
        return StringUtil.toHexStringPadded(new StringBuilder(length << 1), src, offset, length).toString();
    }

    public static <T extends Appendable> T toHexStringPadded(T dst, byte[] src) {
        return StringUtil.toHexStringPadded(dst, src, 0, src.length);
    }

    public static <T extends Appendable> T toHexStringPadded(T dst, byte[] src, int offset, int length) {
        int end = offset + length;
        for (int i = offset; i < end; ++i) {
            StringUtil.byteToHexStringPadded(dst, src[i]);
        }
        return dst;
    }

    public static String byteToHexString(int value) {
        return BYTE2HEX_NOPAD[value & 0xFF];
    }

    public static <T extends Appendable> T byteToHexString(T buf, int value) {
        try {
            buf.append(StringUtil.byteToHexString(value));
        }
        catch (IOException e) {
            PlatformDependent.throwException(e);
        }
        return buf;
    }

    public static String toHexString(byte[] src) {
        return StringUtil.toHexString(src, 0, src.length);
    }

    public static String toHexString(byte[] src, int offset, int length) {
        return StringUtil.toHexString(new StringBuilder(length << 1), src, offset, length).toString();
    }

    public static <T extends Appendable> T toHexString(T dst, byte[] src) {
        return StringUtil.toHexString(dst, src, 0, src.length);
    }

    public static <T extends Appendable> T toHexString(T dst, byte[] src, int offset, int length) {
        int i;
        assert (length >= 0);
        if (length == 0) {
            return dst;
        }
        int end = offset + length;
        int endMinusOne = end - 1;
        for (i = offset; i < endMinusOne && src[i] == 0; ++i) {
        }
        StringUtil.byteToHexString(dst, src[i++]);
        int remaining = end - i;
        StringUtil.toHexStringPadded(dst, src, i, remaining);
        return dst;
    }

    public static int decodeHexNibble(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'A' && c <= 'F') {
            return c - 55;
        }
        if (c >= 'a' && c <= 'f') {
            return c - 87;
        }
        return -1;
    }

    public static byte decodeHexByte(CharSequence s2, int pos) {
        int hi = StringUtil.decodeHexNibble(s2.charAt(pos));
        int lo = StringUtil.decodeHexNibble(s2.charAt(pos + 1));
        if (hi == -1 || lo == -1) {
            throw new IllegalArgumentException(String.format("invalid hex byte '%s' at index %d of '%s'", s2.subSequence(pos, pos + 2), pos, s2));
        }
        return (byte)((hi << 4) + lo);
    }

    public static byte[] decodeHexDump(CharSequence hexDump, int fromIndex, int length) {
        if (length < 0 || (length & 1) != 0) {
            throw new IllegalArgumentException("length: " + length);
        }
        if (length == 0) {
            return EmptyArrays.EMPTY_BYTES;
        }
        byte[] bytes2 = new byte[length >>> 1];
        for (int i = 0; i < length; i += 2) {
            bytes2[i >>> 1] = StringUtil.decodeHexByte(hexDump, fromIndex + i);
        }
        return bytes2;
    }

    public static byte[] decodeHexDump(CharSequence hexDump) {
        return StringUtil.decodeHexDump(hexDump, 0, hexDump.length());
    }

    public static String simpleClassName(Object o) {
        if (o == null) {
            return "null_object";
        }
        return StringUtil.simpleClassName(o.getClass());
    }

    public static String simpleClassName(Class<?> clazz) {
        String className = ObjectUtil.checkNotNull(clazz, "clazz").getName();
        int lastDotIdx = className.lastIndexOf(46);
        if (lastDotIdx > -1) {
            return className.substring(lastDotIdx + 1);
        }
        return className;
    }

    public static CharSequence escapeCsv(CharSequence value) {
        return StringUtil.escapeCsv(value, false);
    }

    public static CharSequence escapeCsv(CharSequence value, boolean trimWhiteSpace) {
        int last2;
        int start;
        int length = ObjectUtil.checkNotNull(value, "value").length();
        if (trimWhiteSpace) {
            start = StringUtil.indexOfFirstNonOwsChar(value, length);
            last2 = StringUtil.indexOfLastNonOwsChar(value, start, length);
        } else {
            start = 0;
            last2 = length - 1;
        }
        if (start > last2) {
            return EMPTY_STRING;
        }
        int firstUnescapedSpecial = -1;
        boolean quoted = false;
        if (StringUtil.isDoubleQuote(value.charAt(start))) {
            boolean bl = quoted = StringUtil.isDoubleQuote(value.charAt(last2)) && last2 > start;
            if (quoted) {
                ++start;
                --last2;
            } else {
                firstUnescapedSpecial = start;
            }
        }
        if (firstUnescapedSpecial < 0) {
            int i;
            if (quoted) {
                for (i = start; i <= last2; ++i) {
                    if (!StringUtil.isDoubleQuote(value.charAt(i))) continue;
                    if (i == last2 || !StringUtil.isDoubleQuote(value.charAt(i + 1))) {
                        firstUnescapedSpecial = i;
                        break;
                    }
                    ++i;
                }
            } else {
                for (i = start; i <= last2; ++i) {
                    char c = value.charAt(i);
                    if (c == '\n' || c == '\r' || c == ',') {
                        firstUnescapedSpecial = i;
                        break;
                    }
                    if (!StringUtil.isDoubleQuote(c)) continue;
                    if (i == last2 || !StringUtil.isDoubleQuote(value.charAt(i + 1))) {
                        firstUnescapedSpecial = i;
                        break;
                    }
                    ++i;
                }
            }
            if (firstUnescapedSpecial < 0) {
                return quoted ? value.subSequence(start - 1, last2 + 2) : value.subSequence(start, last2 + 1);
            }
        }
        StringBuilder result2 = new StringBuilder(last2 - start + 1 + 7);
        result2.append('\"').append(value, start, firstUnescapedSpecial);
        for (int i = firstUnescapedSpecial; i <= last2; ++i) {
            char c = value.charAt(i);
            if (StringUtil.isDoubleQuote(c)) {
                result2.append('\"');
                if (i < last2 && StringUtil.isDoubleQuote(value.charAt(i + 1))) {
                    ++i;
                }
            }
            result2.append(c);
        }
        return result2.append('\"');
    }

    public static CharSequence unescapeCsv(CharSequence value) {
        boolean quoted;
        int length = ObjectUtil.checkNotNull(value, "value").length();
        if (length == 0) {
            return value;
        }
        int last2 = length - 1;
        boolean bl = quoted = StringUtil.isDoubleQuote(value.charAt(0)) && StringUtil.isDoubleQuote(value.charAt(last2)) && length != 1;
        if (!quoted) {
            StringUtil.validateCsvFormat(value);
            return value;
        }
        StringBuilder unescaped = InternalThreadLocalMap.get().stringBuilder();
        for (int i = 1; i < last2; ++i) {
            char current = value.charAt(i);
            if (current == '\"') {
                if (StringUtil.isDoubleQuote(value.charAt(i + 1)) && i + 1 != last2) {
                    ++i;
                } else {
                    throw StringUtil.newInvalidEscapedCsvFieldException(value, i);
                }
            }
            unescaped.append(current);
        }
        return unescaped.toString();
    }

    public static List<CharSequence> unescapeCsvFields(CharSequence value) {
        ArrayList<CharSequence> unescaped = new ArrayList<CharSequence>(2);
        StringBuilder current = InternalThreadLocalMap.get().stringBuilder();
        boolean quoted = false;
        int last2 = value.length() - 1;
        block8: for (int i = 0; i <= last2; ++i) {
            char c = value.charAt(i);
            if (quoted) {
                switch (c) {
                    case '\"': {
                        char next2;
                        if (i == last2) {
                            unescaped.add(current.toString());
                            return unescaped;
                        }
                        if ((next2 = value.charAt(++i)) == '\"') {
                            current.append('\"');
                            break;
                        }
                        if (next2 == ',') {
                            quoted = false;
                            unescaped.add(current.toString());
                            current.setLength(0);
                            break;
                        }
                        throw StringUtil.newInvalidEscapedCsvFieldException(value, i - 1);
                    }
                    default: {
                        current.append(c);
                        break;
                    }
                }
                continue;
            }
            switch (c) {
                case ',': {
                    unescaped.add(current.toString());
                    current.setLength(0);
                    continue block8;
                }
                case '\"': {
                    if (current.length() == 0) {
                        quoted = true;
                        continue block8;
                    }
                }
                case '\n': 
                case '\r': {
                    throw StringUtil.newInvalidEscapedCsvFieldException(value, i);
                }
                default: {
                    current.append(c);
                }
            }
        }
        if (quoted) {
            throw StringUtil.newInvalidEscapedCsvFieldException(value, last2);
        }
        unescaped.add(current.toString());
        return unescaped;
    }

    private static void validateCsvFormat(CharSequence value) {
        int length = value.length();
        for (int i = 0; i < length; ++i) {
            switch (value.charAt(i)) {
                case '\n': 
                case '\r': 
                case '\"': 
                case ',': {
                    throw StringUtil.newInvalidEscapedCsvFieldException(value, i);
                }
            }
        }
    }

    private static IllegalArgumentException newInvalidEscapedCsvFieldException(CharSequence value, int index) {
        return new IllegalArgumentException("invalid escaped CSV field: " + value + " index: " + index);
    }

    public static int length(String s2) {
        return s2 == null ? 0 : s2.length();
    }

    public static boolean isNullOrEmpty(String s2) {
        return s2 == null || s2.isEmpty();
    }

    public static int indexOfNonWhiteSpace(CharSequence seq, int offset) {
        while (offset < seq.length()) {
            if (!Character.isWhitespace(seq.charAt(offset))) {
                return offset;
            }
            ++offset;
        }
        return -1;
    }

    public static boolean isSurrogate(char c) {
        return c >= '\ud800' && c <= '\udfff';
    }

    private static boolean isDoubleQuote(char c) {
        return c == '\"';
    }

    public static boolean endsWith(CharSequence s2, char c) {
        int len = s2.length();
        return len > 0 && s2.charAt(len - 1) == c;
    }

    public static CharSequence trimOws(CharSequence value) {
        int length = value.length();
        if (length == 0) {
            return value;
        }
        int start = StringUtil.indexOfFirstNonOwsChar(value, length);
        int end = StringUtil.indexOfLastNonOwsChar(value, start, length);
        return start == 0 && end == length - 1 ? value : value.subSequence(start, end + 1);
    }

    private static int indexOfFirstNonOwsChar(CharSequence value, int length) {
        int i;
        for (i = 0; i < length && StringUtil.isOws(value.charAt(i)); ++i) {
        }
        return i;
    }

    private static int indexOfLastNonOwsChar(CharSequence value, int start, int length) {
        int i;
        for (i = length - 1; i > start && StringUtil.isOws(value.charAt(i)); --i) {
        }
        return i;
    }

    private static boolean isOws(char c) {
        return c == ' ' || c == '\t';
    }

    static {
        for (int i = 0; i < BYTE2HEX_PAD.length; ++i) {
            String str = Integer.toHexString(i);
            StringUtil.BYTE2HEX_PAD[i] = i > 15 ? str : '0' + str;
            StringUtil.BYTE2HEX_NOPAD[i] = str;
        }
    }
}

