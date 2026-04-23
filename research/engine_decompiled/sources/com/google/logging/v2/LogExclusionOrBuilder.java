/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface LogExclusionOrBuilder
extends MessageOrBuilder {
    public String getName();

    public ByteString getNameBytes();

    public String getDescription();

    public ByteString getDescriptionBytes();

    public String getFilter();

    public ByteString getFilterBytes();

    public boolean getDisabled();
}

