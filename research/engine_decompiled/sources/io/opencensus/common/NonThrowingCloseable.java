/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.common;

import java.io.Closeable;

@Deprecated
public interface NonThrowingCloseable
extends Closeable {
    @Override
    public void close();
}

