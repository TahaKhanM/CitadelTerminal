/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.util;

import io.grpc.BindableService;
import io.grpc.ExperimentalApi;
import io.grpc.HandlerRegistry;
import io.grpc.MethodDescriptor;
import io.grpc.ServerMethodDefinition;
import io.grpc.ServerServiceDefinition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/933")
public final class MutableHandlerRegistry
extends HandlerRegistry {
    private final ConcurrentMap<String, ServerServiceDefinition> services = new ConcurrentHashMap<String, ServerServiceDefinition>();

    @Nullable
    public ServerServiceDefinition addService(ServerServiceDefinition service) {
        return this.services.put(service.getServiceDescriptor().getName(), service);
    }

    @Nullable
    public ServerServiceDefinition addService(BindableService bindableService) {
        return this.addService(bindableService.bindService());
    }

    public boolean removeService(ServerServiceDefinition service) {
        return this.services.remove(service.getServiceDescriptor().getName(), service);
    }

    @Override
    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/2222")
    public List<ServerServiceDefinition> getServices() {
        return Collections.unmodifiableList(new ArrayList(this.services.values()));
    }

    @Override
    @Nullable
    public ServerMethodDefinition<?, ?> lookupMethod(String methodName, @Nullable String authority) {
        String serviceName = MethodDescriptor.extractFullServiceName(methodName);
        if (serviceName == null) {
            return null;
        }
        ServerServiceDefinition service = (ServerServiceDefinition)this.services.get(serviceName);
        if (service == null) {
            return null;
        }
        return service.getMethod(methodName);
    }
}

