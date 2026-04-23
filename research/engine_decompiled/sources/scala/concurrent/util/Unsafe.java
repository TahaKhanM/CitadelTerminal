/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.util;

import java.lang.reflect.Field;

public final class Unsafe {
    public static final sun.misc.Unsafe instance;

    static {
        try {
            sun.misc.Unsafe found = null;
            for (Field field2 : sun.misc.Unsafe.class.getDeclaredFields()) {
                if (field2.getType() != sun.misc.Unsafe.class) continue;
                field2.setAccessible(true);
                found = (sun.misc.Unsafe)field2.get(null);
                break;
            }
            if (found == null) {
                throw new IllegalStateException("Can't find instance of sun.misc.Unsafe");
            }
            instance = found;
        }
        catch (Throwable t) {
            throw new ExceptionInInitializerError(t);
        }
    }
}

