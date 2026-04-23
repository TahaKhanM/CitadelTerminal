/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import javax.annotation.Nullable;

@GwtCompatible
public interface Predicate<T> {
    @CanIgnoreReturnValue
    public boolean apply(@Nullable T var1);

    public boolean equals(@Nullable Object var1);
}

