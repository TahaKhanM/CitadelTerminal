/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import javax.annotation.Nullable;

@GwtCompatible
public interface Function<F, T> {
    @Nullable
    @CanIgnoreReturnValue
    public T apply(@Nullable F var1);

    public boolean equals(@Nullable Object var1);
}

