/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.internal;

import java.util.ServiceConfigurationError;

public final class Provider {
    private Provider() {
    }

    public static <T> T createInstance(Class<?> rawClass, Class<T> superclass) {
        try {
            return rawClass.asSubclass(superclass).getConstructor(new Class[0]).newInstance(new Object[0]);
        }
        catch (Exception e) {
            throw new ServiceConfigurationError("Provider " + rawClass.getName() + " could not be instantiated.", e);
        }
    }
}

