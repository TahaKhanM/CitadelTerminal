/*
 * Decompiled with CFR 0.152.
 */
package org.joda.time;

import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;

public class IllegalInstantException
extends IllegalArgumentException {
    private static final long serialVersionUID = 2858712538216L;

    public IllegalInstantException(String string2) {
        super(string2);
    }

    public IllegalInstantException(long l, String string2) {
        super(IllegalInstantException.createMessage(l, string2));
    }

    private static String createMessage(long l, String string2) {
        String string3 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").print(new Instant(l));
        String string4 = string2 != null ? " (" + string2 + ")" : "";
        return "Illegal instant due to time zone offset transition (daylight savings time 'gap'): " + string3 + string4;
    }

    public static boolean isIllegalInstant(Throwable throwable) {
        if (throwable instanceof IllegalInstantException) {
            return true;
        }
        if (throwable.getCause() != null && throwable.getCause() != throwable) {
            return IllegalInstantException.isIllegalInstant(throwable.getCause());
        }
        return false;
    }
}

