/*
 * Decompiled with CFR 0.152.
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@GwtCompatible
interface Constraint<E> {
    @CanIgnoreReturnValue
    public E checkElement(E var1);

    public String toString();
}

