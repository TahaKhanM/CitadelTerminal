/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import io.grpc.ServerMethodDefinition;
import io.grpc.ServerServiceDefinition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

final class InternalHandlerRegistry {
    private final List<ServerServiceDefinition> services;
    private final Map<String, ServerMethodDefinition<?, ?>> methods;

    private InternalHandlerRegistry(List<ServerServiceDefinition> services, Map<String, ServerMethodDefinition<?, ?>> methods) {
        this.services = services;
        this.methods = methods;
    }

    public List<ServerServiceDefinition> getServices() {
        return this.services;
    }

    @Nullable
    ServerMethodDefinition<?, ?> lookupMethod(String methodName) {
        return this.methods.get(methodName);
    }

    static class Builder {
        private final HashMap<String, ServerServiceDefinition> services = new LinkedHashMap<String, ServerServiceDefinition>();

        Builder() {
        }

        Builder addService(ServerServiceDefinition service) {
            this.services.put(service.getServiceDescriptor().getName(), service);
            return this;
        }

        InternalHandlerRegistry build() {
            HashMap map2 = new HashMap();
            for (ServerServiceDefinition service : this.services.values()) {
                for (ServerMethodDefinition<?, ?> method : service.getMethods()) {
                    map2.put(method.getMethodDescriptor().getFullMethodName(), method);
                }
            }
            return new InternalHandlerRegistry(Collections.unmodifiableList(new ArrayList<ServerServiceDefinition>(this.services.values())), Collections.unmodifiableMap(map2));
        }
    }
}

