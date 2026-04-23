/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface LogEntryOperationOrBuilder
extends MessageOrBuilder {
    public String getId();

    public ByteString getIdBytes();

    public String getProducer();

    public ByteString getProducerBytes();

    public boolean getFirst();

    public boolean getLast();
}

