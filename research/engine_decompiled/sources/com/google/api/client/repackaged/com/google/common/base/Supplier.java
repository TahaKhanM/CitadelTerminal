/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@GwtCompatible
public interface Supplier<T> {
    @CanIgnoreReturnValue
    public T get();
}

