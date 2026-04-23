/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1;

import com.google.iam.v1.BindingDelta;
import com.google.iam.v1.BindingDeltaOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface PolicyDeltaOrBuilder
extends MessageOrBuilder {
    public List<BindingDelta> getBindingDeltasList();

    public BindingDelta getBindingDeltas(int var1);

    public int getBindingDeltasCount();

    public List<? extends BindingDeltaOrBuilder> getBindingDeltasOrBuilderList();

    public BindingDeltaOrBuilder getBindingDeltasOrBuilder(int var1);
}

