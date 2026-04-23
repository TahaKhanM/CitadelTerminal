/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.audit;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface AuthenticationInfoOrBuilder
extends MessageOrBuilder {
    public String getPrincipalEmail();

    public ByteString getPrincipalEmailBytes();
}

