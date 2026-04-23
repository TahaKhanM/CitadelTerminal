/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogExclusion;
import com.google.logging.v2.LogExclusionOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface CreateExclusionRequestOrBuilder
extends MessageOrBuilder {
    public String getParent();

    public ByteString getParentBytes();

    public boolean hasExclusion();

    public LogExclusion getExclusion();

    public LogExclusionOrBuilder getExclusionOrBuilder();
}

