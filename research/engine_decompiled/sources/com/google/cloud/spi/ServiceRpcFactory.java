/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.spi;

import com.google.cloud.ServiceOptions;
import com.google.cloud.ServiceRpc;

public interface ServiceRpcFactory<OptionsT extends ServiceOptions> {
    public ServiceRpc create(OptionsT var1);
}

