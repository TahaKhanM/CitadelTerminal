/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1;

import com.google.iam.v1.Binding;
import com.google.iam.v1.BindingOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface PolicyOrBuilder
extends MessageOrBuilder {
    public int getVersion();

    public List<Binding> getBindingsList();

    public Binding getBindings(int var1);

    public int getBindingsCount();

    public List<? extends BindingOrBuilder> getBindingsOrBuilderList();

    public BindingOrBuilder getBindingsOrBuilder(int var1);

    public ByteString getEtag();
}

