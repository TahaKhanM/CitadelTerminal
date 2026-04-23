/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.BindableService;
import io.grpc.ExperimentalApi;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.PartialForwardingServerCall;
import io.grpc.PartialForwardingServerCallListener;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.ServerMethodDefinition;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServiceDescriptor;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ServerInterceptors {
    private ServerInterceptors() {
    }

    public static ServerServiceDefinition interceptForward(ServerServiceDefinition serviceDef, ServerInterceptor ... interceptors) {
        return ServerInterceptors.interceptForward(serviceDef, Arrays.asList(interceptors));
    }

    public static ServerServiceDefinition interceptForward(BindableService bindableService, ServerInterceptor ... interceptors) {
        return ServerInterceptors.interceptForward(bindableService.bindService(), Arrays.asList(interceptors));
    }

    public static ServerServiceDefinition interceptForward(ServerServiceDefinition serviceDef, List<? extends ServerInterceptor> interceptors) {
        ArrayList<? extends ServerInterceptor> copy2 = new ArrayList<ServerInterceptor>(interceptors);
        Collections.reverse(copy2);
        return ServerInterceptors.intercept(serviceDef, copy2);
    }

    public static ServerServiceDefinition interceptForward(BindableService bindableService, List<? extends ServerInterceptor> interceptors) {
        return ServerInterceptors.interceptForward(bindableService.bindService(), interceptors);
    }

    public static ServerServiceDefinition intercept(ServerServiceDefinition serviceDef, ServerInterceptor ... interceptors) {
        return ServerInterceptors.intercept(serviceDef, Arrays.asList(interceptors));
    }

    public static ServerServiceDefinition intercept(BindableService bindableService, ServerInterceptor ... interceptors) {
        Preconditions.checkNotNull(bindableService, "bindableService");
        return ServerInterceptors.intercept(bindableService.bindService(), Arrays.asList(interceptors));
    }

    public static ServerServiceDefinition intercept(ServerServiceDefinition serviceDef, List<? extends ServerInterceptor> interceptors) {
        Preconditions.checkNotNull(serviceDef, "serviceDef");
        if (interceptors.isEmpty()) {
            return serviceDef;
        }
        ServerServiceDefinition.Builder serviceDefBuilder = ServerServiceDefinition.builder(serviceDef.getServiceDescriptor());
        for (ServerMethodDefinition<?, ?> method : serviceDef.getMethods()) {
            ServerInterceptors.wrapAndAddMethod(serviceDefBuilder, method, interceptors);
        }
        return serviceDefBuilder.build();
    }

    public static ServerServiceDefinition intercept(BindableService bindableService, List<? extends ServerInterceptor> interceptors) {
        Preconditions.checkNotNull(bindableService, "bindableService");
        return ServerInterceptors.intercept(bindableService.bindService(), interceptors);
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1712")
    public static ServerServiceDefinition useInputStreamMessages(ServerServiceDefinition serviceDef) {
        MethodDescriptor.Marshaller<InputStream> marshaller = new MethodDescriptor.Marshaller<InputStream>(){

            @Override
            public InputStream stream(InputStream value) {
                return value;
            }

            @Override
            public InputStream parse(InputStream stream) {
                if (stream.markSupported()) {
                    return stream;
                }
                return new BufferedInputStream(stream);
            }
        };
        return ServerInterceptors.useMarshalledMessages(serviceDef, marshaller);
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1712")
    public static <T> ServerServiceDefinition useMarshalledMessages(ServerServiceDefinition serviceDef, MethodDescriptor.Marshaller<T> marshaller) {
        ArrayList<ServerMethodDefinition<T, T>> wrappedMethods = new ArrayList<ServerMethodDefinition<T, T>>();
        ArrayList wrappedDescriptors = new ArrayList();
        for (ServerMethodDefinition<?, ?> definition : serviceDef.getMethods()) {
            MethodDescriptor<?, ?> methodDescriptor = definition.getMethodDescriptor();
            MethodDescriptor<T, T> wrappedMethodDescriptor = methodDescriptor.toBuilder(marshaller, marshaller).build();
            wrappedDescriptors.add(wrappedMethodDescriptor);
            wrappedMethods.add(ServerInterceptors.wrapMethod(definition, wrappedMethodDescriptor));
        }
        ServerServiceDefinition.Builder serviceBuilder = ServerServiceDefinition.builder(new ServiceDescriptor(serviceDef.getServiceDescriptor().getName(), wrappedDescriptors));
        for (ServerMethodDefinition serverMethodDefinition : wrappedMethods) {
            serviceBuilder.addMethod(serverMethodDefinition);
        }
        return serviceBuilder.build();
    }

    private static <ReqT, RespT> void wrapAndAddMethod(ServerServiceDefinition.Builder serviceDefBuilder, ServerMethodDefinition<ReqT, RespT> method, List<? extends ServerInterceptor> interceptors) {
        ServerCallHandler<ReqT, RespT> callHandler = method.getServerCallHandler();
        for (ServerInterceptor serverInterceptor : interceptors) {
            callHandler = InterceptCallHandler.create(serverInterceptor, callHandler);
        }
        serviceDefBuilder.addMethod(method.withServerCallHandler(callHandler));
    }

    static <OReqT, ORespT, WReqT, WRespT> ServerMethodDefinition<WReqT, WRespT> wrapMethod(ServerMethodDefinition<OReqT, ORespT> definition, MethodDescriptor<WReqT, WRespT> wrappedMethod) {
        ServerCallHandler<WReqT, WRespT> wrappedHandler = ServerInterceptors.wrapHandler(definition.getServerCallHandler(), definition.getMethodDescriptor(), wrappedMethod);
        return ServerMethodDefinition.create(wrappedMethod, wrappedHandler);
    }

    private static <OReqT, ORespT, WReqT, WRespT> ServerCallHandler<WReqT, WRespT> wrapHandler(final ServerCallHandler<OReqT, ORespT> originalHandler, final MethodDescriptor<OReqT, ORespT> originalMethod, final MethodDescriptor<WReqT, WRespT> wrappedMethod) {
        return new ServerCallHandler<WReqT, WRespT>(){

            @Override
            public ServerCall.Listener<WReqT> startCall(final ServerCall<WReqT, WRespT> call, Metadata headers) {
                PartialForwardingServerCall unwrappedCall = new PartialForwardingServerCall<OReqT, ORespT>(){

                    @Override
                    protected ServerCall<WReqT, WRespT> delegate() {
                        return call;
                    }

                    @Override
                    public void sendMessage(ORespT message) {
                        InputStream is = originalMethod.streamResponse(message);
                        Object wrappedMessage = wrappedMethod.parseResponse(is);
                        this.delegate().sendMessage(wrappedMessage);
                    }

                    @Override
                    public MethodDescriptor<OReqT, ORespT> getMethodDescriptor() {
                        return originalMethod;
                    }
                };
                final ServerCall.Listener originalListener = originalHandler.startCall(unwrappedCall, headers);
                return new PartialForwardingServerCallListener<WReqT>(){

                    @Override
                    protected ServerCall.Listener<OReqT> delegate() {
                        return originalListener;
                    }

                    @Override
                    public void onMessage(WReqT message) {
                        InputStream is = wrappedMethod.streamRequest(message);
                        Object originalMessage = originalMethod.parseRequest(is);
                        this.delegate().onMessage(originalMessage);
                    }
                };
            }
        };
    }

    static final class InterceptCallHandler<ReqT, RespT>
    implements ServerCallHandler<ReqT, RespT> {
        private final ServerInterceptor interceptor;
        private final ServerCallHandler<ReqT, RespT> callHandler;

        public static <ReqT, RespT> InterceptCallHandler<ReqT, RespT> create(ServerInterceptor interceptor, ServerCallHandler<ReqT, RespT> callHandler) {
            return new InterceptCallHandler<ReqT, RespT>(interceptor, callHandler);
        }

        private InterceptCallHandler(ServerInterceptor interceptor, ServerCallHandler<ReqT, RespT> callHandler) {
            this.interceptor = Preconditions.checkNotNull(interceptor, "interceptor");
            this.callHandler = callHandler;
        }

        @Override
        public ServerCall.Listener<ReqT> startCall(ServerCall<ReqT, RespT> call, Metadata headers) {
            return this.interceptor.interceptCall(call, headers, this.callHandler);
        }
    }
}

