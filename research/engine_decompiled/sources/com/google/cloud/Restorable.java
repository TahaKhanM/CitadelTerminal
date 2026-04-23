/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.cloud.RestorableState;

public interface Restorable<T extends Restorable<T>> {
    public RestorableState<T> capture();
}

