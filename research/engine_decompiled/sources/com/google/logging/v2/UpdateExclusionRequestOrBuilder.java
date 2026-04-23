/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogExclusion;
import com.google.logging.v2.LogExclusionOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
import com.google.protobuf.MessageOrBuilder;

public interface UpdateExclusionRequestOrBuilder
extends MessageOrBuilder {
    public String getName();

    public ByteString getNameBytes();

    public boolean hasExclusion();

    public LogExclusion getExclusion();

    public LogExclusionOrBuilder getExclusionOrBuilder();

    public boolean hasUpdateMask();

    public FieldMask getUpdateMask();

    public FieldMaskOrBuilder getUpdateMaskOrBuilder();
}

