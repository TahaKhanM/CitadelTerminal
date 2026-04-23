/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1.logging;

import com.google.iam.v1.PolicyDelta;
import com.google.iam.v1.PolicyDeltaOrBuilder;
import com.google.protobuf.MessageOrBuilder;

public interface AuditDataOrBuilder
extends MessageOrBuilder {
    public boolean hasPolicyDelta();

    public PolicyDelta getPolicyDelta();

    public PolicyDeltaOrBuilder getPolicyDeltaOrBuilder();
}

