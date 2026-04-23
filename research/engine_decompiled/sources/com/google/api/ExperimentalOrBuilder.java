/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.AuthorizationConfig;
import com.google.api.AuthorizationConfigOrBuilder;
import com.google.protobuf.MessageOrBuilder;

public interface ExperimentalOrBuilder
extends MessageOrBuilder {
    public boolean hasAuthorization();

    public AuthorizationConfig getAuthorization();

    public AuthorizationConfigOrBuilder getAuthorizationOrBuilder();
}

