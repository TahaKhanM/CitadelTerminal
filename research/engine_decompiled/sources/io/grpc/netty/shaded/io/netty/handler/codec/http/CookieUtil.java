/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.handler.codec.http;

import java.util.BitSet;

@Deprecated
final class CookieUtil {
    private static final BitSet VALID_COOKIE_VALUE_OCTETS = CookieUtil.validCookieValueOctets();
    private static final BitSet VALID_COOKIE_NAME_OCTETS = CookieUtil.validCookieNameOctets(VALID_COOKIE_VALUE_OCTETS);

    private static BitSet validCookieValueOctets() {
        BitSet bits2 = new BitSet(8);
        for (int i = 35; i < 127; ++i) {
            bits2.set(i);
        }
        bits2.set(34, false);
        bits2.set(44, false);
        bits2.set(59, false);
        bits2.set(92, false);
        return bits2;
    }

    private static BitSet validCookieNameOctets(BitSet validCookieValueOctets) {
        BitSet bits2 = new BitSet(8);
        bits2.or(validCookieValueOctets);
        bits2.set(40, false);
        bits2.set(41, false);
        bits2.set(60, false);
        bits2.set(62, false);
        bits2.set(64, false);
        bits2.set(58, false);
        bits2.set(47, false);
        bits2.set(91, false);
        bits2.set(93, false);
        bits2.set(63, false);
        bits2.set(61, false);
        bits2.set(123, false);
        bits2.set(125, false);
        bits2.set(32, false);
        bits2.set(9, false);
        return bits2;
    }

    static int firstInvalidCookieNameOctet(CharSequence cs) {
        return CookieUtil.firstInvalidOctet(cs, VALID_COOKIE_NAME_OCTETS);
    }

    static int firstInvalidCookieValueOctet(CharSequence cs) {
        return CookieUtil.firstInvalidOctet(cs, VALID_COOKIE_VALUE_OCTETS);
    }

    static int firstInvalidOctet(CharSequence cs, BitSet bits2) {
        for (int i = 0; i < cs.length(); ++i) {
            char c = cs.charAt(i);
            if (bits2.get(c)) continue;
            return i;
        }
        return -1;
    }

    static CharSequence unwrapValue(CharSequence cs) {
        int len = cs.length();
        if (len > 0 && cs.charAt(0) == '\"') {
            if (len >= 2 && cs.charAt(len - 1) == '\"') {
                return len == 2 ? "" : cs.subSequence(1, len - 1);
            }
            return null;
        }
        return cs;
    }

    private CookieUtil() {
    }
}

