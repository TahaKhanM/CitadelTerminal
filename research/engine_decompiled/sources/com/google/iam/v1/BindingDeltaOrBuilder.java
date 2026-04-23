/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1;

import com.google.iam.v1.BindingDelta;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface BindingDeltaOrBuilder
extends MessageOrBuilder {
    public int getActionValue();

    public BindingDelta.Action getAction();

    public String getRole();

    public ByteString getRoleBytes();

    public String getMember();

    public ByteString getMemberBytes();
}

