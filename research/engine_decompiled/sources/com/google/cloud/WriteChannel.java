/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.cloud.Restorable;
import com.google.cloud.RestorableState;
import java.io.Closeable;
import java.nio.channels.WritableByteChannel;

public interface WriteChannel
extends WritableByteChannel,
Closeable,
Restorable<WriteChannel> {
    public void setChunkSize(int var1);

    @Override
    public RestorableState<WriteChannel> capture();
}

