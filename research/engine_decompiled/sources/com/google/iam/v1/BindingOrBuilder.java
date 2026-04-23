/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface BindingOrBuilder
extends MessageOrBuilder {
    public String getRole();

    public ByteString getRoleBytes();

    public List<String> getMembersList();

    public int getMembersCount();

    public String getMembers(int var1);

    public ByteString getMembersBytes(int var1);
}

