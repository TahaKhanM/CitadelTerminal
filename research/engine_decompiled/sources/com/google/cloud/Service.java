/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.cloud.ServiceOptions;

public interface Service<OptionsT extends ServiceOptions<?, OptionsT>> {
    public OptionsT getOptions();
}

