/*
 * Decompiled with CFR 0.152.
 */
package com.google.longrunning;

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
import com.google.common.util.concurrent.MoreExecutors;
import com.google.longrunning.CancelOperationRequest;
import com.google.longrunning.DeleteOperationRequest;
import com.google.longrunning.GetOperationRequest;
import com.google.longrunning.ListOperationsRequest;
import com.google.longrunning.ListOperationsResponse;
import com.google.longrunning.Operation;
import com.google.longrunning.OperationsSettings;
import com.google.longrunning.stub.OperationsStub;
import com.google.longrunning.stub.OperationsStubSettings;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BetaApi
public class OperationsClient
implements BackgroundResource {
    private final OperationsSettings settings;
    private final OperationsStub stub;

    public static final OperationsClient create(OperationsSettings settings) throws IOException {
        return new OperationsClient(settings);
    }

    @BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
    public static final OperationsClient create(OperationsStub stub) {
        return new OperationsClient(stub);
    }

    protected OperationsClient(OperationsSettings settings) throws IOException {
        this.settings = settings;
        this.stub = ((OperationsStubSettings)settings.getStubSettings()).createStub();
    }

    @BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
    protected OperationsClient(OperationsStub stub) {
        this.settings = null;
        this.stub = stub;
    }

    public final OperationsSettings getSettings() {
        return this.settings;
    }

    @BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
    public OperationsStub getStub() {
        return this.stub;
    }

    public final Operation getOperation(String name) {
        GetOperationRequest request = GetOperationRequest.newBuilder().setName(name).build();
        return this.getOperation(request);
    }

    private final Operation getOperation(GetOperationRequest request) {
        return this.getOperationCallable().call(request);
    }

    public final UnaryCallable<GetOperationRequest, Operation> getOperationCallable() {
        return this.stub.getOperationCallable();
    }

    public final ListOperationsPagedResponse listOperations(String name, String filter2) {
        ListOperationsRequest request = ListOperationsRequest.newBuilder().setName(name).setFilter(filter2).build();
        return this.listOperations(request);
    }

    public final ListOperationsPagedResponse listOperations(ListOperationsRequest request) {
        return this.listOperationsPagedCallable().call(request);
    }

    public final UnaryCallable<ListOperationsRequest, ListOperationsPagedResponse> listOperationsPagedCallable() {
        return this.stub.listOperationsPagedCallable();
    }

    public final UnaryCallable<ListOperationsRequest, ListOperationsResponse> listOperationsCallable() {
        return this.stub.listOperationsCallable();
    }

    public final void cancelOperation(String name) {
        CancelOperationRequest request = CancelOperationRequest.newBuilder().setName(name).build();
        this.cancelOperation(request);
    }

    private final void cancelOperation(CancelOperationRequest request) {
        this.cancelOperationCallable().call(request);
    }

    public final UnaryCallable<CancelOperationRequest, Empty> cancelOperationCallable() {
        return this.stub.cancelOperationCallable();
    }

    public final void deleteOperation(String name) {
        DeleteOperationRequest request = DeleteOperationRequest.newBuilder().setName(name).build();
        this.deleteOperation(request);
    }

    private final void deleteOperation(DeleteOperationRequest request) {
        this.deleteOperationCallable().call(request);
    }

    public final UnaryCallable<DeleteOperationRequest, Empty> deleteOperationCallable() {
        return this.stub.deleteOperationCallable();
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

    public static class ListOperationsFixedSizeCollection
    extends AbstractFixedSizeCollection<ListOperationsRequest, ListOperationsResponse, Operation, ListOperationsPage, ListOperationsFixedSizeCollection> {
        private ListOperationsFixedSizeCollection(List<ListOperationsPage> pages, int collectionSize) {
            super(pages, collectionSize);
        }

        private static ListOperationsFixedSizeCollection createEmptyCollection() {
            return new ListOperationsFixedSizeCollection(null, 0);
        }

        @Override
        protected ListOperationsFixedSizeCollection createCollection(List<ListOperationsPage> pages, int collectionSize) {
            return new ListOperationsFixedSizeCollection(pages, collectionSize);
        }
    }

    public static class ListOperationsPage
    extends AbstractPage<ListOperationsRequest, ListOperationsResponse, Operation, ListOperationsPage> {
        private ListOperationsPage(PageContext<ListOperationsRequest, ListOperationsResponse, Operation> context, ListOperationsResponse response) {
            super(context, response);
        }

        private static ListOperationsPage createEmptyPage() {
            return new ListOperationsPage(null, null);
        }

        @Override
        protected ListOperationsPage createPage(PageContext<ListOperationsRequest, ListOperationsResponse, Operation> context, ListOperationsResponse response) {
            return new ListOperationsPage(context, response);
        }

        @Override
        public ApiFuture<ListOperationsPage> createPageAsync(PageContext<ListOperationsRequest, ListOperationsResponse, Operation> context, ApiFuture<ListOperationsResponse> futureResponse) {
            return super.createPageAsync(context, futureResponse);
        }
    }

    public static class ListOperationsPagedResponse
    extends AbstractPagedListResponse<ListOperationsRequest, ListOperationsResponse, Operation, ListOperationsPage, ListOperationsFixedSizeCollection> {
        public static ApiFuture<ListOperationsPagedResponse> createAsync(PageContext<ListOperationsRequest, ListOperationsResponse, Operation> context, ApiFuture<ListOperationsResponse> futureResponse) {
            ApiFuture<ListOperationsPage> futurePage = ListOperationsPage.createEmptyPage().createPageAsync(context, futureResponse);
            return ApiFutures.transform(futurePage, new ApiFunction<ListOperationsPage, ListOperationsPagedResponse>(){

                @Override
                public ListOperationsPagedResponse apply(ListOperationsPage input2) {
                    return new ListOperationsPagedResponse(input2);
                }
            }, MoreExecutors.directExecutor());
        }

        private ListOperationsPagedResponse(ListOperationsPage page) {
            super(page, ListOperationsFixedSizeCollection.createEmptyCollection());
        }
    }
}

