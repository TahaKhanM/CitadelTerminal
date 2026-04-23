/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.ApiCallContext;

@InternalExtensionOnly
public interface TransportChannel
extends BackgroundResource {
    public String getTransportName();

    public ApiCallContext getEmptyCallContext();
}

