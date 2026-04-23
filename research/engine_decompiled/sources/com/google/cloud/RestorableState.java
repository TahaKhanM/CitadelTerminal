/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.cloud.Restorable;

public interface RestorableState<T extends Restorable<T>> {
    public T restore();
}

