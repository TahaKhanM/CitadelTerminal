/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging.v2.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.core.BackgroundResourceAggregation;
import com.google.api.gax.grpc.GrpcCallSettings;
import com.google.api.gax.grpc.GrpcStubCallableFactory;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.logging.v2.ConfigClient;
import com.google.cloud.logging.v2.stub.ConfigServiceV2Stub;
import com.google.cloud.logging.v2.stub.ConfigServiceV2StubSettings;
import com.google.cloud.logging.v2.stub.GrpcConfigServiceV2CallableFactory;
import com.google.logging.v2.CreateExclusionRequest;
import com.google.logging.v2.CreateSinkRequest;
import com.google.logging.v2.DeleteExclusionRequest;
import com.google.logging.v2.DeleteSinkRequest;
import com.google.logging.v2.GetExclusionRequest;
import com.google.logging.v2.GetSinkRequest;
import com.google.logging.v2.ListExclusionsRequest;
import com.google.logging.v2.ListExclusionsResponse;
import com.google.logging.v2.ListSinksRequest;
import com.google.logging.v2.ListSinksResponse;
import com.google.logging.v2.LogExclusion;
import com.google.logging.v2.LogSink;
import com.google.logging.v2.UpdateExclusionRequest;
import com.google.logging.v2.UpdateSinkRequest;
import com.google.protobuf.Empty;
import io.grpc.MethodDescriptor;
import io.grpc.protobuf.ProtoUtils;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
public class GrpcConfigServiceV2Stub
extends ConfigServiceV2Stub {
    private static final MethodDescriptor<ListSinksRequest, ListSinksResponse> listSinksMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.ConfigServiceV2/ListSinks").setRequestMarshaller(ProtoUtils.marshaller(ListSinksRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ListSinksResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<GetSinkRequest, LogSink> getSinkMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.ConfigServiceV2/GetSink").setRequestMarshaller(ProtoUtils.marshaller(GetSinkRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(LogSink.getDefaultInstance())).build();
    private static final MethodDescriptor<CreateSinkRequest, LogSink> createSinkMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.ConfigServiceV2/CreateSink").setRequestMarshaller(ProtoUtils.marshaller(CreateSinkRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(LogSink.getDefaultInstance())).build();
    private static final MethodDescriptor<UpdateSinkRequest, LogSink> updateSinkMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.ConfigServiceV2/UpdateSink").setRequestMarshaller(ProtoUtils.marshaller(UpdateSinkRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(LogSink.getDefaultInstance())).build();
    private static final MethodDescriptor<DeleteSinkRequest, Empty> deleteSinkMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.ConfigServiceV2/DeleteSink").setRequestMarshaller(ProtoUtils.marshaller(DeleteSinkRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).build();
    private static final MethodDescriptor<ListExclusionsRequest, ListExclusionsResponse> listExclusionsMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.ConfigServiceV2/ListExclusions").setRequestMarshaller(ProtoUtils.marshaller(ListExclusionsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ListExclusionsResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<GetExclusionRequest, LogExclusion> getExclusionMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.ConfigServiceV2/GetExclusion").setRequestMarshaller(ProtoUtils.marshaller(GetExclusionRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(LogExclusion.getDefaultInstance())).build();
    private static final MethodDescriptor<CreateExclusionRequest, LogExclusion> createExclusionMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.ConfigServiceV2/CreateExclusion").setRequestMarshaller(ProtoUtils.marshaller(CreateExclusionRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(LogExclusion.getDefaultInstance())).build();
    private static final MethodDescriptor<UpdateExclusionRequest, LogExclusion> updateExclusionMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.ConfigServiceV2/UpdateExclusion").setRequestMarshaller(ProtoUtils.marshaller(UpdateExclusionRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(LogExclusion.getDefaultInstance())).build();
    private static final MethodDescriptor<DeleteExclusionRequest, Empty> deleteExclusionMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.ConfigServiceV2/DeleteExclusion").setRequestMarshaller(ProtoUtils.marshaller(DeleteExclusionRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).build();
    private final BackgroundResource backgroundResources;
    private final UnaryCallable<ListSinksRequest, ListSinksResponse> listSinksCallable;
    private final UnaryCallable<ListSinksRequest, ConfigClient.ListSinksPagedResponse> listSinksPagedCallable;
    private final UnaryCallable<GetSinkRequest, LogSink> getSinkCallable;
    private final UnaryCallable<CreateSinkRequest, LogSink> createSinkCallable;
    private final UnaryCallable<UpdateSinkRequest, LogSink> updateSinkCallable;
    private final UnaryCallable<DeleteSinkRequest, Empty> deleteSinkCallable;
    private final UnaryCallable<ListExclusionsRequest, ListExclusionsResponse> listExclusionsCallable;
    private final UnaryCallable<ListExclusionsRequest, ConfigClient.ListExclusionsPagedResponse> listExclusionsPagedCallable;
    private final UnaryCallable<GetExclusionRequest, LogExclusion> getExclusionCallable;
    private final UnaryCallable<CreateExclusionRequest, LogExclusion> createExclusionCallable;
    private final UnaryCallable<UpdateExclusionRequest, LogExclusion> updateExclusionCallable;
    private final UnaryCallable<DeleteExclusionRequest, Empty> deleteExclusionCallable;
    private final GrpcStubCallableFactory callableFactory;

    public static final GrpcConfigServiceV2Stub create(ConfigServiceV2StubSettings settings) throws IOException {
        return new GrpcConfigServiceV2Stub(settings, ClientContext.create(settings));
    }

    public static final GrpcConfigServiceV2Stub create(ClientContext clientContext) throws IOException {
        return new GrpcConfigServiceV2Stub(ConfigServiceV2StubSettings.newBuilder().build(), clientContext);
    }

    public static final GrpcConfigServiceV2Stub create(ClientContext clientContext, GrpcStubCallableFactory callableFactory) throws IOException {
        return new GrpcConfigServiceV2Stub(ConfigServiceV2StubSettings.newBuilder().build(), clientContext, callableFactory);
    }

    protected GrpcConfigServiceV2Stub(ConfigServiceV2StubSettings settings, ClientContext clientContext) throws IOException {
        this(settings, clientContext, new GrpcConfigServiceV2CallableFactory());
    }

    protected GrpcConfigServiceV2Stub(ConfigServiceV2StubSettings settings, ClientContext clientContext, GrpcStubCallableFactory callableFactory) throws IOException {
        this.callableFactory = callableFactory;
        GrpcCallSettings<ListSinksRequest, ListSinksResponse> listSinksTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(listSinksMethodDescriptor).build();
        GrpcCallSettings<GetSinkRequest, LogSink> getSinkTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(getSinkMethodDescriptor).build();
        GrpcCallSettings<CreateSinkRequest, LogSink> createSinkTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(createSinkMethodDescriptor).build();
        GrpcCallSettings<UpdateSinkRequest, LogSink> updateSinkTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(updateSinkMethodDescriptor).build();
        GrpcCallSettings<DeleteSinkRequest, Empty> deleteSinkTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(deleteSinkMethodDescriptor).build();
        GrpcCallSettings<ListExclusionsRequest, ListExclusionsResponse> listExclusionsTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(listExclusionsMethodDescriptor).build();
        GrpcCallSettings<GetExclusionRequest, LogExclusion> getExclusionTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(getExclusionMethodDescriptor).build();
        GrpcCallSettings<CreateExclusionRequest, LogExclusion> createExclusionTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(createExclusionMethodDescriptor).build();
        GrpcCallSettings<UpdateExclusionRequest, LogExclusion> updateExclusionTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(updateExclusionMethodDescriptor).build();
        GrpcCallSettings<DeleteExclusionRequest, Empty> deleteExclusionTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(deleteExclusionMethodDescriptor).build();
        this.listSinksCallable = callableFactory.createUnaryCallable(listSinksTransportSettings, settings.listSinksSettings(), clientContext);
        this.listSinksPagedCallable = callableFactory.createPagedCallable(listSinksTransportSettings, settings.listSinksSettings(), clientContext);
        this.getSinkCallable = callableFactory.createUnaryCallable(getSinkTransportSettings, settings.getSinkSettings(), clientContext);
        this.createSinkCallable = callableFactory.createUnaryCallable(createSinkTransportSettings, settings.createSinkSettings(), clientContext);
        this.updateSinkCallable = callableFactory.createUnaryCallable(updateSinkTransportSettings, settings.updateSinkSettings(), clientContext);
        this.deleteSinkCallable = callableFactory.createUnaryCallable(deleteSinkTransportSettings, settings.deleteSinkSettings(), clientContext);
        this.listExclusionsCallable = callableFactory.createUnaryCallable(listExclusionsTransportSettings, settings.listExclusionsSettings(), clientContext);
        this.listExclusionsPagedCallable = callableFactory.createPagedCallable(listExclusionsTransportSettings, settings.listExclusionsSettings(), clientContext);
        this.getExclusionCallable = callableFactory.createUnaryCallable(getExclusionTransportSettings, settings.getExclusionSettings(), clientContext);
        this.createExclusionCallable = callableFactory.createUnaryCallable(createExclusionTransportSettings, settings.createExclusionSettings(), clientContext);
        this.updateExclusionCallable = callableFactory.createUnaryCallable(updateExclusionTransportSettings, settings.updateExclusionSettings(), clientContext);
        this.deleteExclusionCallable = callableFactory.createUnaryCallable(deleteExclusionTransportSettings, settings.deleteExclusionSettings(), clientContext);
        this.backgroundResources = new BackgroundResourceAggregation(clientContext.getBackgroundResources());
    }

    @Override
    public UnaryCallable<ListSinksRequest, ConfigClient.ListSinksPagedResponse> listSinksPagedCallable() {
        return this.listSinksPagedCallable;
    }

    @Override
    public UnaryCallable<ListSinksRequest, ListSinksResponse> listSinksCallable() {
        return this.listSinksCallable;
    }

    @Override
    public UnaryCallable<GetSinkRequest, LogSink> getSinkCallable() {
        return this.getSinkCallable;
    }

    @Override
    public UnaryCallable<CreateSinkRequest, LogSink> createSinkCallable() {
        return this.createSinkCallable;
    }

    @Override
    public UnaryCallable<UpdateSinkRequest, LogSink> updateSinkCallable() {
        return this.updateSinkCallable;
    }

    @Override
    public UnaryCallable<DeleteSinkRequest, Empty> deleteSinkCallable() {
        return this.deleteSinkCallable;
    }

    @Override
    public UnaryCallable<ListExclusionsRequest, ConfigClient.ListExclusionsPagedResponse> listExclusionsPagedCallable() {
        return this.listExclusionsPagedCallable;
    }

    @Override
    public UnaryCallable<ListExclusionsRequest, ListExclusionsResponse> listExclusionsCallable() {
        return this.listExclusionsCallable;
    }

    @Override
    public UnaryCallable<GetExclusionRequest, LogExclusion> getExclusionCallable() {
        return this.getExclusionCallable;
    }

    @Override
    public UnaryCallable<CreateExclusionRequest, LogExclusion> createExclusionCallable() {
        return this.createExclusionCallable;
    }

    @Override
    public UnaryCallable<UpdateExclusionRequest, LogExclusion> updateExclusionCallable() {
        return this.updateExclusionCallable;
    }

    @Override
    public UnaryCallable<DeleteExclusionRequest, Empty> deleteExclusionCallable() {
        return this.deleteExclusionCallable;
    }

    @Override
    public final void close() {
        this.shutdown();
    }

    @Override
    public void shutdown() {
        this.backgroundResources.shutdown();
    }

    @Override
    public boolean isShutdown() {
        return this.backgroundResources.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return this.backgroundResources.isTerminated();
    }

    @Override
    public void shutdownNow() {
        this.backgroundResources.shutdownNow();
    }

    @Override
    public boolean awaitTermination(long duration, TimeUnit unit) throws InterruptedException {
        return this.backgroundResources.awaitTermination(duration, unit);
    }
}

