/*
 * Decompiled with CFR 0.152.
 */
package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@GwtIncompatible
public final class Defaults {
    private static final Map<Class<?>, Object> DEFAULTS;

    private Defaults() {
    }

    private static <T> void put(Map<Class<?>, Object> map2, Class<T> type, T value) {
        map2.put(type, value);
    }

    @Nullable
    public static <T> T defaultValue(Class<T> type) {
        Object t = DEFAULTS.get(Preconditions.checkNotNull(type));
        return (T)t;
    }

    static {
        HashMap map2 = new HashMap();
        Defaults.put(map2, Boolean.TYPE, false);
        Defaults.put(map2, Character.TYPE, Character.valueOf('\u0000'));
        Defaults.put(map2, Byte.TYPE, (byte)0);
        Defaults.put(map2, Short.TYPE, (short)0);
        Defaults.put(map2, Integer.TYPE, 0);
        Defaults.put(map2, Long.TYPE, 0L);
        Defaults.put(map2, Float.TYPE, Float.valueOf(0.0f));
        Defaults.put(map2, Double.TYPE, 0.0);
        DEFAULTS = Collections.unmodifiableMap(map2);
    }
}

