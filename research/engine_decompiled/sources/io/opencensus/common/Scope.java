/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.common;

import io.opencensus.common.NonThrowingCloseable;

public interface Scope
extends NonThrowingCloseable {
    @Override
    public void close();
}

