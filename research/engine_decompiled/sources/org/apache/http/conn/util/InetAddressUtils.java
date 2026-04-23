/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.conn.util;

import java.util.regex.Pattern;

public class InetAddressUtils {
    private static final String IPV4_BASIC_PATTERN_STRING = "(([1-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){1}(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){2}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])";
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(([1-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){1}(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){2}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");
    private static final Pattern IPV4_MAPPED_IPV6_PATTERN = Pattern.compile("^::[fF]{4}:(([1-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){1}(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){2}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");
    private static final Pattern IPV6_STD_PATTERN = Pattern.compile("^[0-9a-fA-F]{1,4}(:[0-9a-fA-F]{1,4}){7}$");
    private static final Pattern IPV6_HEX_COMPRESSED_PATTERN = Pattern.compile("^(([0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4}){0,5})?)::(([0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4}){0,5})?)$");
    private static final char COLON_CHAR = ':';
    private static final int MAX_COLON_COUNT = 7;

    private InetAddressUtils() {
    }

    public static boolean isIPv4Address(String input2) {
        return IPV4_PATTERN.matcher(input2).matches();
    }

    public static boolean isIPv4MappedIPv64Address(String input2) {
        return IPV4_MAPPED_IPV6_PATTERN.matcher(input2).matches();
    }

    public static boolean isIPv6StdAddress(String input2) {
        return IPV6_STD_PATTERN.matcher(input2).matches();
    }

    public static boolean isIPv6HexCompressedAddress(String input2) {
        int colonCount = 0;
        for (int i = 0; i < input2.length(); ++i) {
            if (input2.charAt(i) != ':') continue;
            ++colonCount;
        }
        return colonCount <= 7 && IPV6_HEX_COMPRESSED_PATTERN.matcher(input2).matches();
    }

    public static boolean isIPv6Address(String input2) {
        return InetAddressUtils.isIPv6StdAddress(input2) || InetAddressUtils.isIPv6HexCompressedAddress(input2);
    }
}

