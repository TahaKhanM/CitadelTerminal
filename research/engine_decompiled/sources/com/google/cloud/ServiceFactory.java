/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.cloud.Service;
import com.google.cloud.ServiceOptions;

public interface ServiceFactory<ServiceT extends Service, ServiceOptionsT extends ServiceOptions> {
    public ServiceT create(ServiceOptionsT var1);
}

