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
import com.google.cloud.logging.v2.MetricsSettings;
import com.google.cloud.logging.v2.stub.MetricsServiceV2Stub;
import com.google.cloud.logging.v2.stub.MetricsServiceV2StubSettings;
import com.google.logging.v2.CreateLogMetricRequest;
import com.google.logging.v2.DeleteLogMetricRequest;
import com.google.logging.v2.GetLogMetricRequest;
import com.google.logging.v2.ListLogMetricsRequest;
import com.google.logging.v2.ListLogMetricsResponse;
import com.google.logging.v2.LogMetric;
import com.google.logging.v2.MetricName;
import com.google.logging.v2.ParentName;
import com.google.logging.v2.UpdateLogMetricRequest;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BetaApi
public class MetricsClient
implements BackgroundResource {
    private final MetricsSettings settings;
    private final MetricsServiceV2Stub stub;

    public static final MetricsClient create() throws IOException {
        return MetricsClient.create(MetricsSettings.newBuilder().build());
    }

    public static final MetricsClient create(MetricsSettings settings) throws IOException {
        return new MetricsClient(settings);
    }

    @BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
    public static final MetricsClient create(MetricsServiceV2Stub stub) {
        return new MetricsClient(stub);
    }

    protected MetricsClient(MetricsSettings settings) throws IOException {
        this.settings = settings;
        this.stub = ((MetricsServiceV2StubSettings)settings.getStubSettings()).createStub();
    }

    @BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
    protected MetricsClient(MetricsServiceV2Stub stub) {
        this.settings = null;
        this.stub = stub;
    }

    public final MetricsSettings getSettings() {
        return this.settings;
    }

    @BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
    public MetricsServiceV2Stub getStub() {
        return this.stub;
    }

    public final ListLogMetricsPagedResponse listLogMetrics(ParentName parent) {
        ListLogMetricsRequest request = ListLogMetricsRequest.newBuilder().setParent(parent == null ? null : parent.toString()).build();
        return this.listLogMetrics(request);
    }

    public final ListLogMetricsPagedResponse listLogMetrics(String parent) {
        ListLogMetricsRequest request = ListLogMetricsRequest.newBuilder().setParent(parent).build();
        return this.listLogMetrics(request);
    }

    public final ListLogMetricsPagedResponse listLogMetrics(ListLogMetricsRequest request) {
        return this.listLogMetricsPagedCallable().call(request);
    }

    public final UnaryCallable<ListLogMetricsRequest, ListLogMetricsPagedResponse> listLogMetricsPagedCallable() {
        return this.stub.listLogMetricsPagedCallable();
    }

    public final UnaryCallable<ListLogMetricsRequest, ListLogMetricsResponse> listLogMetricsCallable() {
        return this.stub.listLogMetricsCallable();
    }

    public final LogMetric getLogMetric(MetricName metricName) {
        GetLogMetricRequest request = GetLogMetricRequest.newBuilder().setMetricName(metricName == null ? null : metricName.toString()).build();
        return this.getLogMetric(request);
    }

    public final LogMetric getLogMetric(String metricName) {
        GetLogMetricRequest request = GetLogMetricRequest.newBuilder().setMetricName(metricName).build();
        return this.getLogMetric(request);
    }

    private final LogMetric getLogMetric(GetLogMetricRequest request) {
        return this.getLogMetricCallable().call(request);
    }

    public final UnaryCallable<GetLogMetricRequest, LogMetric> getLogMetricCallable() {
        return this.stub.getLogMetricCallable();
    }

    public final LogMetric createLogMetric(ParentName parent, LogMetric metric) {
        CreateLogMetricRequest request = CreateLogMetricRequest.newBuilder().setParent(parent == null ? null : parent.toString()).setMetric(metric).build();
        return this.createLogMetric(request);
    }

    public final LogMetric createLogMetric(String parent, LogMetric metric) {
        CreateLogMetricRequest request = CreateLogMetricRequest.newBuilder().setParent(parent).setMetric(metric).build();
        return this.createLogMetric(request);
    }

    public final LogMetric createLogMetric(CreateLogMetricRequest request) {
        return this.createLogMetricCallable().call(request);
    }

    public final UnaryCallable<CreateLogMetricRequest, LogMetric> createLogMetricCallable() {
        return this.stub.createLogMetricCallable();
    }

    public final LogMetric updateLogMetric(MetricName metricName, LogMetric metric) {
        UpdateLogMetricRequest request = UpdateLogMetricRequest.newBuilder().setMetricName(metricName == null ? null : metricName.toString()).setMetric(metric).build();
        return this.updateLogMetric(request);
    }

    public final LogMetric updateLogMetric(String metricName, LogMetric metric) {
        UpdateLogMetricRequest request = UpdateLogMetricRequest.newBuilder().setMetricName(metricName).setMetric(metric).build();
        return this.updateLogMetric(request);
    }

    public final LogMetric updateLogMetric(UpdateLogMetricRequest request) {
        return this.updateLogMetricCallable().call(request);
    }

    public final UnaryCallable<UpdateLogMetricRequest, LogMetric> updateLogMetricCallable() {
        return this.stub.updateLogMetricCallable();
    }

    public final void deleteLogMetric(MetricName metricName) {
        DeleteLogMetricRequest request = DeleteLogMetricRequest.newBuilder().setMetricName(metricName == null ? null : metricName.toString()).build();
        this.deleteLogMetric(request);
    }

    public final void deleteLogMetric(String metricName) {
        DeleteLogMetricRequest request = DeleteLogMetricRequest.newBuilder().setMetricName(metricName).build();
        this.deleteLogMetric(request);
    }

    private final void deleteLogMetric(DeleteLogMetricRequest request) {
        this.deleteLogMetricCallable().call(request);
    }

    public final UnaryCallable<DeleteLogMetricRequest, Empty> deleteLogMetricCallable() {
        return this.stub.deleteLogMetricCallable();
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

    public static class ListLogMetricsFixedSizeCollection
    extends AbstractFixedSizeCollection<ListLogMetricsRequest, ListLogMetricsResponse, LogMetric, ListLogMetricsPage, ListLogMetricsFixedSizeCollection> {
        private ListLogMetricsFixedSizeCollection(List<ListLogMetricsPage> pages, int collectionSize) {
            super(pages, collectionSize);
        }

        private static ListLogMetricsFixedSizeCollection createEmptyCollection() {
            return new ListLogMetricsFixedSizeCollection(null, 0);
        }

        @Override
        protected ListLogMetricsFixedSizeCollection createCollection(List<ListLogMetricsPage> pages, int collectionSize) {
            return new ListLogMetricsFixedSizeCollection(pages, collectionSize);
        }
    }

    public static class ListLogMetricsPage
    extends AbstractPage<ListLogMetricsRequest, ListLogMetricsResponse, LogMetric, ListLogMetricsPage> {
        private ListLogMetricsPage(PageContext<ListLogMetricsRequest, ListLogMetricsResponse, LogMetric> context, ListLogMetricsResponse response) {
            super(context, response);
        }

        private static ListLogMetricsPage createEmptyPage() {
            return new ListLogMetricsPage(null, null);
        }

        @Override
        protected ListLogMetricsPage createPage(PageContext<ListLogMetricsRequest, ListLogMetricsResponse, LogMetric> context, ListLogMetricsResponse response) {
            return new ListLogMetricsPage(context, response);
        }

        @Override
        public ApiFuture<ListLogMetricsPage> createPageAsync(PageContext<ListLogMetricsRequest, ListLogMetricsResponse, LogMetric> context, ApiFuture<ListLogMetricsResponse> futureResponse) {
            return super.createPageAsync(context, futureResponse);
        }
    }

    public static class ListLogMetricsPagedResponse
    extends AbstractPagedListResponse<ListLogMetricsRequest, ListLogMetricsResponse, LogMetric, ListLogMetricsPage, ListLogMetricsFixedSizeCollection> {
        public static ApiFuture<ListLogMetricsPagedResponse> createAsync(PageContext<ListLogMetricsRequest, ListLogMetricsResponse, LogMetric> context, ApiFuture<ListLogMetricsResponse> futureResponse) {
            ApiFuture<ListLogMetricsPage> futurePage = ListLogMetricsPage.createEmptyPage().createPageAsync(context, futureResponse);
            return ApiFutures.transform(futurePage, new ApiFunction<ListLogMetricsPage, ListLogMetricsPagedResponse>(){

                @Override
                public ListLogMetricsPagedResponse apply(ListLogMetricsPage input2) {
                    return new ListLogMetricsPagedResponse(input2);
                }
            });
        }

        private ListLogMetricsPagedResponse(ListLogMetricsPage page) {
            super(page, ListLogMetricsFixedSizeCollection.createEmptyCollection());
        }
    }
}

