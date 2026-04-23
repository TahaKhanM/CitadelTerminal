/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.cloud.Service;
import com.google.cloud.ServiceFactory;
import com.google.cloud.ServiceOptions;
import com.google.cloud.TransportOptions;
import com.google.cloud.spi.ServiceRpcFactory;
import java.io.Serializable;

public interface ServiceDefaults<ServiceT extends Service<OptionsT>, OptionsT extends ServiceOptions<ServiceT, OptionsT>>
extends Serializable {
    public ServiceFactory<ServiceT, OptionsT> getDefaultServiceFactory();

    public ServiceRpcFactory<OptionsT> getDefaultRpcFactory();

    public TransportOptions getDefaultTransportOptions();
}

