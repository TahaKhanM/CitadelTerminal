/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.cloud.Restorable;
import com.google.cloud.RestorableState;
import java.io.Closeable;
import java.io.IOException;
import java.nio.channels.ReadableByteChannel;

public interface ReadChannel
extends ReadableByteChannel,
Closeable,
Restorable<ReadChannel> {
    @Override
    public void close();

    public void seek(long var1) throws IOException;

    public void setChunkSize(int var1);

    @Override
    public RestorableState<ReadChannel> capture();
}

