/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1;

import com.google.iam.v1.Policy;
import com.google.iam.v1.PolicyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface SetIamPolicyRequestOrBuilder
extends MessageOrBuilder {
    public String getResource();

    public ByteString getResourceBytes();

    public boolean hasPolicy();

    public Policy getPolicy();

    public PolicyOrBuilder getPolicyOrBuilder();
}

