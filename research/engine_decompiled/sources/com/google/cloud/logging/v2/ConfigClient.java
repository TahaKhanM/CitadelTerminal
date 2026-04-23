/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging.v2;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.paging.AbstractFixedSizeCollection;
import com.google.api.gax.paging.AbstractPage;
import com.google.api.gax.paging.AbstractPagedListResponse;
import com.google.api.gax.rpc.PageContext;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.logging.v2.ConfigSettings;
import com.google.cloud.logging.v2.stub.ConfigServiceV2Stub;
import com.google.cloud.logging.v2.stub.ConfigServiceV2StubSettings;
import com.google.logging.v2.CreateExclusionRequest;
import com.google.logging.v2.CreateSinkRequest;
import com.google.logging.v2.DeleteExclusionRequest;
import com.google.logging.v2.DeleteSinkRequest;
import com.google.logging.v2.ExclusionName;
import com.google.logging.v2.GetExclusionRequest;
import com.google.logging.v2.GetSinkRequest;
import com.google.logging.v2.ListExclusionsRequest;
import com.google.logging.v2.ListExclusionsResponse;
import com.google.logging.v2.ListSinksRequest;
import com.google.logging.v2.ListSinksResponse;
import com.google.logging.v2.LogExclusion;
import com.google.logging.v2.LogSink;
import com.google.logging.v2.ParentName;
import com.google.logging.v2.SinkName;
import com.google.logging.v2.UpdateExclusionRequest;
import com.google.logging.v2.UpdateSinkRequest;
import com.google.protobuf.Empty;
import com.google.protobuf.FieldMask;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BetaApi
public class ConfigClient
implements BackgroundResource {
    private final ConfigSettings settings;
    private final ConfigServiceV2Stub stub;

    public static final ConfigClient create() throws IOException {
        return ConfigClient.create(ConfigSettings.newBuilder().build());
    }

    public static final ConfigClient create(ConfigSettings settings) throws IOException {
        return new ConfigClient(settings);
    }

    @BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
    public static final ConfigClient create(ConfigServiceV2Stub stub) {
        return new ConfigClient(stub);
    }

    protected ConfigClient(ConfigSettings settings) throws IOException {
        this.settings = settings;
        this.stub = ((ConfigServiceV2StubSettings)settings.getStubSettings()).createStub();
    }

    @BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
    protected ConfigClient(ConfigServiceV2Stub stub) {
        this.settings = null;
        this.stub = stub;
    }

    public final ConfigSettings getSettings() {
        return this.settings;
    }

    @BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
    public ConfigServiceV2Stub getStub() {
        return this.stub;
    }

    public final ListSinksPagedResponse listSinks(ParentName parent) {
        ListSinksRequest request = ListSinksRequest.newBuilder().setParent(parent == null ? null : parent.toString()).build();
        return this.listSinks(request);
    }

    public final ListSinksPagedResponse listSinks(String parent) {
        ListSinksRequest request = ListSinksRequest.newBuilder().setParent(parent).build();
        return this.listSinks(request);
    }

    public final ListSinksPagedResponse listSinks(ListSinksRequest request) {
        return this.listSinksPagedCallable().call(request);
    }

    public final UnaryCallable<ListSinksRequest, ListSinksPagedResponse> listSinksPagedCallable() {
        return this.stub.listSinksPagedCallable();
    }

    public final UnaryCallable<ListSinksRequest, ListSinksResponse> listSinksCallable() {
        return this.stub.listSinksCallable();
    }

    public final LogSink getSink(SinkName sinkName) {
        GetSinkRequest request = GetSinkRequest.newBuilder().setSinkName(sinkName == null ? null : sinkName.toString()).build();
        return this.getSink(request);
    }

    public final LogSink getSink(String sinkName) {
        GetSinkRequest request = GetSinkRequest.newBuilder().setSinkName(sinkName).build();
        return this.getSink(request);
    }

    private final LogSink getSink(GetSinkRequest request) {
        return this.getSinkCallable().call(request);
    }

    public final UnaryCallable<GetSinkRequest, LogSink> getSinkCallable() {
        return this.stub.getSinkCallable();
    }

    public final LogSink createSink(ParentName parent, LogSink sink) {
        CreateSinkRequest request = CreateSinkRequest.newBuilder().setParent(parent == null ? null : parent.toString()).setSink(sink).build();
        return this.createSink(request);
    }

    public final LogSink createSink(String parent, LogSink sink) {
        CreateSinkRequest request = CreateSinkRequest.newBuilder().setParent(parent).setSink(sink).build();
        return this.createSink(request);
    }

    public final LogSink createSink(CreateSinkRequest request) {
        return this.createSinkCallable().call(request);
    }

    public final UnaryCallable<CreateSinkRequest, LogSink> createSinkCallable() {
        return this.stub.createSinkCallable();
    }

    public final LogSink updateSink(SinkName sinkName, LogSink sink, FieldMask updateMask) {
        UpdateSinkRequest request = UpdateSinkRequest.newBuilder().setSinkName(sinkName == null ? null : sinkName.toString()).setSink(sink).setUpdateMask(updateMask).build();
        return this.updateSink(request);
    }

    public final LogSink updateSink(String sinkName, LogSink sink, FieldMask updateMask) {
        UpdateSinkRequest request = UpdateSinkRequest.newBuilder().setSinkName(sinkName).setSink(sink).setUpdateMask(updateMask).build();
        return this.updateSink(request);
    }

    public final LogSink updateSink(SinkName sinkName, LogSink sink) {
        UpdateSinkRequest request = UpdateSinkRequest.newBuilder().setSinkName(sinkName == null ? null : sinkName.toString()).setSink(sink).build();
        return this.updateSink(request);
    }

    public final LogSink updateSink(String sinkName, LogSink sink) {
        UpdateSinkRequest request = UpdateSinkRequest.newBuilder().setSinkName(sinkName).setSink(sink).build();
        return this.updateSink(request);
    }

    public final LogSink updateSink(UpdateSinkRequest request) {
        return this.updateSinkCallable().call(request);
    }

    public final UnaryCallable<UpdateSinkRequest, LogSink> updateSinkCallable() {
        return this.stub.updateSinkCallable();
    }

    public final void deleteSink(SinkName sinkName) {
        DeleteSinkRequest request = DeleteSinkRequest.newBuilder().setSinkName(sinkName == null ? null : sinkName.toString()).build();
        this.deleteSink(request);
    }

    public final void deleteSink(String sinkName) {
        DeleteSinkRequest request = DeleteSinkRequest.newBuilder().setSinkName(sinkName).build();
        this.deleteSink(request);
    }

    private final void deleteSink(DeleteSinkRequest request) {
        this.deleteSinkCallable().call(request);
    }

    public final UnaryCallable<DeleteSinkRequest, Empty> deleteSinkCallable() {
        return this.stub.deleteSinkCallable();
    }

    public final ListExclusionsPagedResponse listExclusions(ParentName parent) {
        ListExclusionsRequest request = ListExclusionsRequest.newBuilder().setParent(parent == null ? null : parent.toString()).build();
        return this.listExclusions(request);
    }

    public final ListExclusionsPagedResponse listExclusions(String parent) {
        ListExclusionsRequest request = ListExclusionsRequest.newBuilder().setParent(parent).build();
        return this.listExclusions(request);
    }

    public final ListExclusionsPagedResponse listExclusions(ListExclusionsRequest request) {
        return this.listExclusionsPagedCallable().call(request);
    }

    public final UnaryCallable<ListExclusionsRequest, ListExclusionsPagedResponse> listExclusionsPagedCallable() {
        return this.stub.listExclusionsPagedCallable();
    }

    public final UnaryCallable<ListExclusionsRequest, ListExclusionsResponse> listExclusionsCallable() {
        return this.stub.listExclusionsCallable();
    }

    public final LogExclusion getExclusion(ExclusionName name) {
        GetExclusionRequest request = GetExclusionRequest.newBuilder().setName(name == null ? null : name.toString()).build();
        return this.getExclusion(request);
    }

    public final LogExclusion getExclusion(String name) {
        GetExclusionRequest request = GetExclusionRequest.newBuilder().setName(name).build();
        return this.getExclusion(request);
    }

    private final LogExclusion getExclusion(GetExclusionRequest request) {
        return this.getExclusionCallable().call(request);
    }

    public final UnaryCallable<GetExclusionRequest, LogExclusion> getExclusionCallable() {
        return this.stub.getExclusionCallable();
    }

    public final LogExclusion createExclusion(ParentName parent, LogExclusion exclusion) {
        CreateExclusionRequest request = CreateExclusionRequest.newBuilder().setParent(parent == null ? null : parent.toString()).setExclusion(exclusion).build();
        return this.createExclusion(request);
    }

    public final LogExclusion createExclusion(String parent, LogExclusion exclusion) {
        CreateExclusionRequest request = CreateExclusionRequest.newBuilder().setParent(parent).setExclusion(exclusion).build();
        return this.createExclusion(request);
    }

    public final LogExclusion createExclusion(CreateExclusionRequest request) {
        return this.createExclusionCallable().call(request);
    }

    public final UnaryCallable<CreateExclusionRequest, LogExclusion> createExclusionCallable() {
        return this.stub.createExclusionCallable();
    }

    public final LogExclusion updateExclusion(ExclusionName name, LogExclusion exclusion, FieldMask updateMask) {
        UpdateExclusionRequest request = UpdateExclusionRequest.newBuilder().setName(name == null ? null : name.toString()).setExclusion(exclusion).setUpdateMask(updateMask).build();
        return this.updateExclusion(request);
    }

    public final LogExclusion updateExclusion(String name, LogExclusion exclusion, FieldMask updateMask) {
        UpdateExclusionRequest request = UpdateExclusionRequest.newBuilder().setName(name).setExclusion(exclusion).setUpdateMask(updateMask).build();
        return this.updateExclusion(request);
    }

    public final LogExclusion updateExclusion(UpdateExclusionRequest request) {
        return this.updateExclusionCallable().call(request);
    }

    public final UnaryCallable<UpdateExclusionRequest, LogExclusion> updateExclusionCallable() {
        return this.stub.updateExclusionCallable();
    }

    public final void deleteExclusion(ExclusionName name) {
        DeleteExclusionRequest request = DeleteExclusionRequest.newBuilder().setName(name == null ? null : name.toString()).build();
        this.deleteExclusion(request);
    }

    public final void deleteExclusion(String name) {
        DeleteExclusionRequest request = DeleteExclusionRequest.newBuilder().setName(name).build();
        this.deleteExclusion(request);
    }

    private final void deleteExclusion(DeleteExclusionRequest request) {
        this.deleteExclusionCallable().call(request);
    }

    public final UnaryCallable<DeleteExclusionRequest, Empty> deleteExclusionCallable() {
        return this.stub.deleteExclusionCallable();
    }

    @Override
    public final void close() {
        this.stub.close();
    }

    @Override
    public void shutdown() {
        this.stub.shutdown();
    }

    @Override
    public boolean isShutdown() {
        return this.stub.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return this.stub.isTerminated();
    }

    @Override
    public void shutdownNow() {
        this.stub.shutdownNow();
    }

    @Override
    public boolean awaitTermination(long duration, TimeUnit unit) throws InterruptedException {
        return this.stub.awaitTermination(duration, unit);
    }

    public static class ListExclusionsFixedSizeCollection
    extends AbstractFixedSizeCollection<ListExclusionsRequest, ListExclusionsResponse, LogExclusion, ListExclusionsPage, ListExclusionsFixedSizeCollection> {
        private ListExclusionsFixedSizeCollection(List<ListExclusionsPage> pages, int collectionSize) {
            super(pages, collectionSize);
        }

        private static ListExclusionsFixedSizeCollection createEmptyCollection() {
            return new ListExclusionsFixedSizeCollection(null, 0);
        }

        @Override
        protected ListExclusionsFixedSizeCollection createCollection(List<ListExclusionsPage> pages, int collectionSize) {
            return new ListExclusionsFixedSizeCollection(pages, collectionSize);
        }
    }

    public static class ListExclusionsPage
    extends AbstractPage<ListExclusionsRequest, ListExclusionsResponse, LogExclusion, ListExclusionsPage> {
        private ListExclusionsPage(PageContext<ListExclusionsRequest, ListExclusionsResponse, LogExclusion> context, ListExclusionsResponse response) {
            super(context, response);
        }

        private static ListExclusionsPage createEmptyPage() {
            return new ListExclusionsPage(null, null);
        }

        @Override
        protected ListExclusionsPage createPage(PageContext<ListExclusionsRequest, ListExclusionsResponse, LogExclusion> context, ListExclusionsResponse response) {
            return new ListExclusionsPage(context, response);
        }

        @Override
        public ApiFuture<ListExclusionsPage> createPageAsync(PageContext<ListExclusionsRequest, ListExclusionsResponse, LogExclusion> context, ApiFuture<ListExclusionsResponse> futureResponse) {
            return super.createPageAsync(context, futureResponse);
        }
    }

    public static class ListExclusionsPagedResponse
    extends AbstractPagedListResponse<ListExclusionsRequest, ListExclusionsResponse, LogExclusion, ListExclusionsPage, ListExclusionsFixedSizeCollection> {
        public static ApiFuture<ListExclusionsPagedResponse> createAsync(PageContext<ListExclusionsRequest, ListExclusionsResponse, LogExclusion> context, ApiFuture<ListExclusionsResponse> futureResponse) {
            ApiFuture<ListExclusionsPage> futurePage = ListExclusionsPage.createEmptyPage().createPageAsync(context, futureResponse);
            return ApiFutures.transform(futurePage, new ApiFunction<ListExclusionsPage, ListExclusionsPagedResponse>(){

                @Override
                public ListExclusionsPagedResponse apply(ListExclusionsPage input2) {
                    return new ListExclusionsPagedResponse(input2);
                }
            });
        }

        private ListExclusionsPagedResponse(ListExclusionsPage page) {
            super(page, ListExclusionsFixedSizeCollection.createEmptyCollection());
        }
    }

    public static class ListSinksFixedSizeCollection
    extends AbstractFixedSizeCollection<ListSinksRequest, ListSinksResponse, LogSink, ListSinksPage, ListSinksFixedSizeCollection> {
        private ListSinksFixedSizeCollection(List<ListSinksPage> pages, int collectionSize) {
            super(pages, collectionSize);
        }

        private static ListSinksFixedSizeCollection createEmptyCollection() {
            return new ListSinksFixedSizeCollection(null, 0);
        }

        @Override
        protected ListSinksFixedSizeCollection createCollection(List<ListSinksPage> pages, int collectionSize) {
            return new ListSinksFixedSizeCollection(pages, collectionSize);
        }
    }

    public static class ListSinksPage
    extends AbstractPage<ListSinksRequest, ListSinksResponse, LogSink, ListSinksPage> {
        private ListSinksPage(PageContext<ListSinksRequest, ListSinksResponse, LogSink> context, ListSinksResponse response) {
            super(context, response);
        }

        private static ListSinksPage createEmptyPage() {
            return new ListSinksPage(null, null);
        }

        @Override
        protected ListSinksPage createPage(PageContext<ListSinksRequest, ListSinksResponse, LogSink> context, ListSinksResponse response) {
            return new ListSinksPage(context, response);
        }

        @Override
        public ApiFuture<ListSinksPage> createPageAsync(PageContext<ListSinksRequest, ListSinksResponse, LogSink> context, ApiFuture<ListSinksResponse> futureResponse) {
            return super.createPageAsync(context, futureResponse);
        }
    }

    public static class ListSinksPagedResponse
    extends AbstractPagedListResponse<ListSinksRequest, ListSinksResponse, LogSink, ListSinksPage, ListSinksFixedSizeCollection> {
        public static ApiFuture<ListSinksPagedResponse> createAsync(PageContext<ListSinksRequest, ListSinksResponse, LogSink> context, ApiFuture<ListSinksResponse> futureResponse) {
            ApiFuture<ListSinksPage> futurePage = ListSinksPage.createEmptyPage().createPageAsync(context, futureResponse);
            return ApiFutures.transform(futurePage, new ApiFunction<ListSinksPage, ListSinksPagedResponse>(){

                @Override
                public ListSinksPagedResponse apply(ListSinksPage input2) {
                    return new ListSinksPagedResponse(input2);
                }
            });
        }

        private ListSinksPagedResponse(ListSinksPage page) {
            super(page, ListSinksFixedSizeCollection.createEmptyCollection());
        }
    }
}

