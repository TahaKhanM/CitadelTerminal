/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging.v2;

import com.google.api.MonitoredResource;
import com.google.api.MonitoredResourceDescriptor;
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
import com.google.cloud.logging.v2.LoggingSettings;
import com.google.cloud.logging.v2.stub.LoggingServiceV2Stub;
import com.google.cloud.logging.v2.stub.LoggingServiceV2StubSettings;
import com.google.logging.v2.DeleteLogRequest;
import com.google.logging.v2.ListLogEntriesRequest;
import com.google.logging.v2.ListLogEntriesResponse;
import com.google.logging.v2.ListLogsRequest;
import com.google.logging.v2.ListLogsResponse;
import com.google.logging.v2.ListMonitoredResourceDescriptorsRequest;
import com.google.logging.v2.ListMonitoredResourceDescriptorsResponse;
import com.google.logging.v2.LogEntry;
import com.google.logging.v2.LogName;
import com.google.logging.v2.ParentName;
import com.google.logging.v2.WriteLogEntriesRequest;
import com.google.logging.v2.WriteLogEntriesResponse;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@BetaApi
public class LoggingClient
implements BackgroundResource {
    private final LoggingSettings settings;
    private final LoggingServiceV2Stub stub;

    public static final LoggingClient create() throws IOException {
        return LoggingClient.create(LoggingSettings.newBuilder().build());
    }

    public static final LoggingClient create(LoggingSettings settings) throws IOException {
        return new LoggingClient(settings);
    }

    @BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
    public static final LoggingClient create(LoggingServiceV2Stub stub) {
        return new LoggingClient(stub);
    }

    protected LoggingClient(LoggingSettings settings) throws IOException {
        this.settings = settings;
        this.stub = ((LoggingServiceV2StubSettings)settings.getStubSettings()).createStub();
    }

    @BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
    protected LoggingClient(LoggingServiceV2Stub stub) {
        this.settings = null;
        this.stub = stub;
    }

    public final LoggingSettings getSettings() {
        return this.settings;
    }

    @BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
    public LoggingServiceV2Stub getStub() {
        return this.stub;
    }

    public final void deleteLog(LogName logName) {
        DeleteLogRequest request = DeleteLogRequest.newBuilder().setLogName(logName == null ? null : logName.toString()).build();
        this.deleteLog(request);
    }

    public final void deleteLog(String logName) {
        DeleteLogRequest request = DeleteLogRequest.newBuilder().setLogName(logName).build();
        this.deleteLog(request);
    }

    private final void deleteLog(DeleteLogRequest request) {
        this.deleteLogCallable().call(request);
    }

    public final UnaryCallable<DeleteLogRequest, Empty> deleteLogCallable() {
        return this.stub.deleteLogCallable();
    }

    public final WriteLogEntriesResponse writeLogEntries(LogName logName, MonitoredResource resource, Map<String, String> labels, List<LogEntry> entries) {
        WriteLogEntriesRequest request = WriteLogEntriesRequest.newBuilder().setLogName(logName == null ? null : logName.toString()).setResource(resource).putAllLabels(labels).addAllEntries(entries).build();
        return this.writeLogEntries(request);
    }

    public final WriteLogEntriesResponse writeLogEntries(String logName, MonitoredResource resource, Map<String, String> labels, List<LogEntry> entries) {
        WriteLogEntriesRequest request = WriteLogEntriesRequest.newBuilder().setLogName(logName).setResource(resource).putAllLabels(labels).addAllEntries(entries).build();
        return this.writeLogEntries(request);
    }

    public final WriteLogEntriesResponse writeLogEntries(WriteLogEntriesRequest request) {
        return this.writeLogEntriesCallable().call(request);
    }

    public final UnaryCallable<WriteLogEntriesRequest, WriteLogEntriesResponse> writeLogEntriesCallable() {
        return this.stub.writeLogEntriesCallable();
    }

    public final ListLogEntriesPagedResponse listLogEntries(List<String> resourceNames, String filter2, String orderBy) {
        ListLogEntriesRequest request = ListLogEntriesRequest.newBuilder().addAllResourceNames(resourceNames).setFilter(filter2).setOrderBy(orderBy).build();
        return this.listLogEntries(request);
    }

    public final ListLogEntriesPagedResponse listLogEntries(ListLogEntriesRequest request) {
        return this.listLogEntriesPagedCallable().call(request);
    }

    public final UnaryCallable<ListLogEntriesRequest, ListLogEntriesPagedResponse> listLogEntriesPagedCallable() {
        return this.stub.listLogEntriesPagedCallable();
    }

    public final UnaryCallable<ListLogEntriesRequest, ListLogEntriesResponse> listLogEntriesCallable() {
        return this.stub.listLogEntriesCallable();
    }

    public final ListMonitoredResourceDescriptorsPagedResponse listMonitoredResourceDescriptors(ListMonitoredResourceDescriptorsRequest request) {
        return this.listMonitoredResourceDescriptorsPagedCallable().call(request);
    }

    public final UnaryCallable<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsPagedResponse> listMonitoredResourceDescriptorsPagedCallable() {
        return this.stub.listMonitoredResourceDescriptorsPagedCallable();
    }

    public final UnaryCallable<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse> listMonitoredResourceDescriptorsCallable() {
        return this.stub.listMonitoredResourceDescriptorsCallable();
    }

    public final ListLogsPagedResponse listLogs(ParentName parent) {
        ListLogsRequest request = ListLogsRequest.newBuilder().setParent(parent == null ? null : parent.toString()).build();
        return this.listLogs(request);
    }

    public final ListLogsPagedResponse listLogs(String parent) {
        ListLogsRequest request = ListLogsRequest.newBuilder().setParent(parent).build();
        return this.listLogs(request);
    }

    public final ListLogsPagedResponse listLogs(ListLogsRequest request) {
        return this.listLogsPagedCallable().call(request);
    }

    public final UnaryCallable<ListLogsRequest, ListLogsPagedResponse> listLogsPagedCallable() {
        return this.stub.listLogsPagedCallable();
    }

    public final UnaryCallable<ListLogsRequest, ListLogsResponse> listLogsCallable() {
        return this.stub.listLogsCallable();
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

    public static class ListLogsFixedSizeCollection
    extends AbstractFixedSizeCollection<ListLogsRequest, ListLogsResponse, String, ListLogsPage, ListLogsFixedSizeCollection> {
        private ListLogsFixedSizeCollection(List<ListLogsPage> pages, int collectionSize) {
            super(pages, collectionSize);
        }

        private static ListLogsFixedSizeCollection createEmptyCollection() {
            return new ListLogsFixedSizeCollection(null, 0);
        }

        @Override
        protected ListLogsFixedSizeCollection createCollection(List<ListLogsPage> pages, int collectionSize) {
            return new ListLogsFixedSizeCollection(pages, collectionSize);
        }
    }

    public static class ListLogsPage
    extends AbstractPage<ListLogsRequest, ListLogsResponse, String, ListLogsPage> {
        private ListLogsPage(PageContext<ListLogsRequest, ListLogsResponse, String> context, ListLogsResponse response) {
            super(context, response);
        }

        private static ListLogsPage createEmptyPage() {
            return new ListLogsPage(null, null);
        }

        @Override
        protected ListLogsPage createPage(PageContext<ListLogsRequest, ListLogsResponse, String> context, ListLogsResponse response) {
            return new ListLogsPage(context, response);
        }

        @Override
        public ApiFuture<ListLogsPage> createPageAsync(PageContext<ListLogsRequest, ListLogsResponse, String> context, ApiFuture<ListLogsResponse> futureResponse) {
            return super.createPageAsync(context, futureResponse);
        }
    }

    public static class ListLogsPagedResponse
    extends AbstractPagedListResponse<ListLogsRequest, ListLogsResponse, String, ListLogsPage, ListLogsFixedSizeCollection> {
        public static ApiFuture<ListLogsPagedResponse> createAsync(PageContext<ListLogsRequest, ListLogsResponse, String> context, ApiFuture<ListLogsResponse> futureResponse) {
            ApiFuture<ListLogsPage> futurePage = ListLogsPage.createEmptyPage().createPageAsync(context, futureResponse);
            return ApiFutures.transform(futurePage, new ApiFunction<ListLogsPage, ListLogsPagedResponse>(){

                @Override
                public ListLogsPagedResponse apply(ListLogsPage input2) {
                    return new ListLogsPagedResponse(input2);
                }
            });
        }

        private ListLogsPagedResponse(ListLogsPage page) {
            super(page, ListLogsFixedSizeCollection.createEmptyCollection());
        }
    }

    public static class ListMonitoredResourceDescriptorsFixedSizeCollection
    extends AbstractFixedSizeCollection<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, MonitoredResourceDescriptor, ListMonitoredResourceDescriptorsPage, ListMonitoredResourceDescriptorsFixedSizeCollection> {
        private ListMonitoredResourceDescriptorsFixedSizeCollection(List<ListMonitoredResourceDescriptorsPage> pages, int collectionSize) {
            super(pages, collectionSize);
        }

        private static ListMonitoredResourceDescriptorsFixedSizeCollection createEmptyCollection() {
            return new ListMonitoredResourceDescriptorsFixedSizeCollection(null, 0);
        }

        @Override
        protected ListMonitoredResourceDescriptorsFixedSizeCollection createCollection(List<ListMonitoredResourceDescriptorsPage> pages, int collectionSize) {
            return new ListMonitoredResourceDescriptorsFixedSizeCollection(pages, collectionSize);
        }
    }

    public static class ListMonitoredResourceDescriptorsPage
    extends AbstractPage<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, MonitoredResourceDescriptor, ListMonitoredResourceDescriptorsPage> {
        private ListMonitoredResourceDescriptorsPage(PageContext<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, MonitoredResourceDescriptor> context, ListMonitoredResourceDescriptorsResponse response) {
            super(context, response);
        }

        private static ListMonitoredResourceDescriptorsPage createEmptyPage() {
            return new ListMonitoredResourceDescriptorsPage(null, null);
        }

        @Override
        protected ListMonitoredResourceDescriptorsPage createPage(PageContext<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, MonitoredResourceDescriptor> context, ListMonitoredResourceDescriptorsResponse response) {
            return new ListMonitoredResourceDescriptorsPage(context, response);
        }

        @Override
        public ApiFuture<ListMonitoredResourceDescriptorsPage> createPageAsync(PageContext<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, MonitoredResourceDescriptor> context, ApiFuture<ListMonitoredResourceDescriptorsResponse> futureResponse) {
            return super.createPageAsync(context, futureResponse);
        }
    }

    public static class ListMonitoredResourceDescriptorsPagedResponse
    extends AbstractPagedListResponse<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, MonitoredResourceDescriptor, ListMonitoredResourceDescriptorsPage, ListMonitoredResourceDescriptorsFixedSizeCollection> {
        public static ApiFuture<ListMonitoredResourceDescriptorsPagedResponse> createAsync(PageContext<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, MonitoredResourceDescriptor> context, ApiFuture<ListMonitoredResourceDescriptorsResponse> futureResponse) {
            ApiFuture<ListMonitoredResourceDescriptorsPage> futurePage = ListMonitoredResourceDescriptorsPage.createEmptyPage().createPageAsync(context, futureResponse);
            return ApiFutures.transform(futurePage, new ApiFunction<ListMonitoredResourceDescriptorsPage, ListMonitoredResourceDescriptorsPagedResponse>(){

                @Override
                public ListMonitoredResourceDescriptorsPagedResponse apply(ListMonitoredResourceDescriptorsPage input2) {
                    return new ListMonitoredResourceDescriptorsPagedResponse(input2);
                }
            });
        }

        private ListMonitoredResourceDescriptorsPagedResponse(ListMonitoredResourceDescriptorsPage page) {
            super(page, ListMonitoredResourceDescriptorsFixedSizeCollection.createEmptyCollection());
        }
    }

    public static class ListLogEntriesFixedSizeCollection
    extends AbstractFixedSizeCollection<ListLogEntriesRequest, ListLogEntriesResponse, LogEntry, ListLogEntriesPage, ListLogEntriesFixedSizeCollection> {
        private ListLogEntriesFixedSizeCollection(List<ListLogEntriesPage> pages, int collectionSize) {
            super(pages, collectionSize);
        }

        private static ListLogEntriesFixedSizeCollection createEmptyCollection() {
            return new ListLogEntriesFixedSizeCollection(null, 0);
        }

        @Override
        protected ListLogEntriesFixedSizeCollection createCollection(List<ListLogEntriesPage> pages, int collectionSize) {
            return new ListLogEntriesFixedSizeCollection(pages, collectionSize);
        }
    }

    public static class ListLogEntriesPage
    extends AbstractPage<ListLogEntriesRequest, ListLogEntriesResponse, LogEntry, ListLogEntriesPage> {
        private ListLogEntriesPage(PageContext<ListLogEntriesRequest, ListLogEntriesResponse, LogEntry> context, ListLogEntriesResponse response) {
            super(context, response);
        }

        private static ListLogEntriesPage createEmptyPage() {
            return new ListLogEntriesPage(null, null);
        }

        @Override
        protected ListLogEntriesPage createPage(PageContext<ListLogEntriesRequest, ListLogEntriesResponse, LogEntry> context, ListLogEntriesResponse response) {
            return new ListLogEntriesPage(context, response);
        }

        @Override
        public ApiFuture<ListLogEntriesPage> createPageAsync(PageContext<ListLogEntriesRequest, ListLogEntriesResponse, LogEntry> context, ApiFuture<ListLogEntriesResponse> futureResponse) {
            return super.createPageAsync(context, futureResponse);
        }
    }

    public static class ListLogEntriesPagedResponse
    extends AbstractPagedListResponse<ListLogEntriesRequest, ListLogEntriesResponse, LogEntry, ListLogEntriesPage, ListLogEntriesFixedSizeCollection> {
        public static ApiFuture<ListLogEntriesPagedResponse> createAsync(PageContext<ListLogEntriesRequest, ListLogEntriesResponse, LogEntry> context, ApiFuture<ListLogEntriesResponse> futureResponse) {
            ApiFuture<ListLogEntriesPage> futurePage = ListLogEntriesPage.createEmptyPage().createPageAsync(context, futureResponse);
            return ApiFutures.transform(futurePage, new ApiFunction<ListLogEntriesPage, ListLogEntriesPagedResponse>(){

                @Override
                public ListLogEntriesPagedResponse apply(ListLogEntriesPage input2) {
                    return new ListLogEntriesPagedResponse(input2);
                }
            });
        }

        private ListLogEntriesPagedResponse(ListLogEntriesPage page) {
            super(page, ListLogEntriesFixedSizeCollection.createEmptyCollection());
        }
    }
}

