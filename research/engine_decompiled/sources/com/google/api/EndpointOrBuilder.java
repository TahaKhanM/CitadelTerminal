/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface EndpointOrBuilder
extends MessageOrBuilder {
    public String getName();

    public ByteString getNameBytes();

    public List<String> getAliasesList();

    public int getAliasesCount();

    public String getAliases(int var1);

    public ByteString getAliasesBytes(int var1);

    public List<String> getFeaturesList();

    public int getFeaturesCount();

    public String getFeatures(int var1);

    public ByteString getFeaturesBytes(int var1);

    public String getTarget();

    public ByteString getTargetBytes();

    public boolean getAllowCors();
}

