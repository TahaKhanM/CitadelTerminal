/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.api.client.util.Preconditions;
import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.paging.AsyncPage;
import com.google.api.gax.paging.Page;
import com.google.cloud.AsyncPageImpl;
import com.google.cloud.BaseService;
import com.google.cloud.MonitoredResource;
import com.google.cloud.MonitoredResourceDescriptor;
import com.google.cloud.PageImpl;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.Metric;
import com.google.cloud.logging.MetricInfo;
import com.google.cloud.logging.Option;
import com.google.cloud.logging.Severity;
import com.google.cloud.logging.Sink;
import com.google.cloud.logging.SinkInfo;
import com.google.cloud.logging.Synchronicity;
import com.google.cloud.logging.spi.v2.LoggingRpc;
import com.google.common.base.Function;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.Uninterruptibles;
import com.google.logging.v2.CreateLogMetricRequest;
import com.google.logging.v2.CreateSinkRequest;
import com.google.logging.v2.DeleteLogMetricRequest;
import com.google.logging.v2.DeleteLogRequest;
import com.google.logging.v2.DeleteSinkRequest;
import com.google.logging.v2.GetLogMetricRequest;
import com.google.logging.v2.GetSinkRequest;
import com.google.logging.v2.ListLogEntriesRequest;
import com.google.logging.v2.ListLogEntriesResponse;
import com.google.logging.v2.ListLogMetricsRequest;
import com.google.logging.v2.ListLogMetricsResponse;
import com.google.logging.v2.ListMonitoredResourceDescriptorsRequest;
import com.google.logging.v2.ListMonitoredResourceDescriptorsResponse;
import com.google.logging.v2.ListSinksRequest;
import com.google.logging.v2.ListSinksResponse;
import com.google.logging.v2.ProjectLogName;
import com.google.logging.v2.ProjectMetricName;
import com.google.logging.v2.ProjectName;
import com.google.logging.v2.ProjectSinkName;
import com.google.logging.v2.UpdateLogMetricRequest;
import com.google.logging.v2.UpdateSinkRequest;
import com.google.logging.v2.WriteLogEntriesRequest;
import com.google.logging.v2.WriteLogEntriesResponse;
import com.google.protobuf.Empty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

class LoggingImpl
extends BaseService<LoggingOptions>
implements Logging {
    private final LoggingRpc rpc;
    private final Object writeLock = new Object();
    private final Set<ApiFuture<Void>> pendingWrites = Collections.newSetFromMap(new IdentityHashMap());
    private volatile Synchronicity writeSynchronicity = Synchronicity.ASYNC;
    private volatile Severity flushSeverity = Severity.ERROR;
    private boolean closed;
    private static final Function<Empty, Boolean> EMPTY_TO_BOOLEAN_FUNCTION = new Function<Empty, Boolean>(){

        @Override
        public Boolean apply(Empty input2) {
            return input2 != null;
        }
    };
    private static final Function<WriteLogEntriesResponse, Void> WRITE_RESPONSE_TO_VOID_FUNCTION = new Function<WriteLogEntriesResponse, Void>(){

        @Override
        public Void apply(WriteLogEntriesResponse input2) {
            return null;
        }
    };
    private static final ThreadLocal<Boolean> inWriteCall = new ThreadLocal();

    LoggingImpl(LoggingOptions options) {
        super(options);
        this.rpc = options.getLoggingRpcV2();
    }

    @Override
    public void setWriteSynchronicity(Synchronicity writeSynchronicity) {
        this.writeSynchronicity = writeSynchronicity;
    }

    @Override
    public void setFlushSeverity(Severity flushSeverity) {
        this.flushSeverity = flushSeverity;
    }

    @Override
    public Synchronicity getWriteSynchronicity() {
        return this.writeSynchronicity;
    }

    @Override
    public Severity getFlushSeverity() {
        return this.flushSeverity;
    }

    private static <V> V get(ApiFuture<V> future) {
        try {
            return Uninterruptibles.getUninterruptibly(future);
        }
        catch (ExecutionException ex) {
            Throwables.throwIfUnchecked(ex.getCause());
            throw new RuntimeException(ex);
        }
    }

    private static <I, O> ApiFuture<O> transform(ApiFuture<I> future, final Function<? super I, ? extends O> function) {
        return ApiFutures.transform(future, new ApiFunction<I, O>(){

            @Override
            public O apply(I i) {
                return function.apply(i);
            }
        });
    }

    @Override
    public Sink create(SinkInfo sink) {
        return LoggingImpl.get(this.createAsync(sink));
    }

    @Override
    public ApiFuture<Sink> createAsync(SinkInfo sink) {
        CreateSinkRequest request = CreateSinkRequest.newBuilder().setParent(ProjectName.of(((LoggingOptions)this.getOptions()).getProjectId()).toString()).setSink(sink.toPb(((LoggingOptions)this.getOptions()).getProjectId())).build();
        return LoggingImpl.transform(this.rpc.create(request), Sink.fromPbFunction(this));
    }

    @Override
    public Sink update(SinkInfo sink) {
        return LoggingImpl.get(this.updateAsync(sink));
    }

    @Override
    public ApiFuture<Sink> updateAsync(SinkInfo sink) {
        UpdateSinkRequest request = UpdateSinkRequest.newBuilder().setSinkName(ProjectSinkName.of(((LoggingOptions)this.getOptions()).getProjectId(), sink.getName()).toString()).setSink(sink.toPb(((LoggingOptions)this.getOptions()).getProjectId())).build();
        return LoggingImpl.transform(this.rpc.update(request), Sink.fromPbFunction(this));
    }

    @Override
    public Sink getSink(String sink) {
        return LoggingImpl.get(this.getSinkAsync(sink));
    }

    @Override
    public ApiFuture<Sink> getSinkAsync(String sink) {
        GetSinkRequest request = GetSinkRequest.newBuilder().setSinkName(ProjectSinkName.of(((LoggingOptions)this.getOptions()).getProjectId(), sink).toString()).build();
        return LoggingImpl.transform(this.rpc.get(request), Sink.fromPbFunction(this));
    }

    private static ListSinksRequest listSinksRequest(LoggingOptions serviceOptions, Map<Option.OptionType, ?> options) {
        ListSinksRequest.Builder builder = ListSinksRequest.newBuilder();
        builder.setParent(ProjectName.of(serviceOptions.getProjectId()).toString());
        Integer pageSize = (Integer)Logging.ListOption.OptionType.PAGE_SIZE.get(options);
        String pageToken = (String)Logging.ListOption.OptionType.PAGE_TOKEN.get(options);
        if (pageSize != null) {
            builder.setPageSize(pageSize);
        }
        if (pageToken != null) {
            builder.setPageToken(pageToken);
        }
        return builder.build();
    }

    private static ApiFuture<AsyncPage<Sink>> listSinksAsync(final LoggingOptions serviceOptions, final Map<Option.OptionType, ?> options) {
        ListSinksRequest request = LoggingImpl.listSinksRequest(serviceOptions, options);
        ApiFuture<ListSinksResponse> list2 = serviceOptions.getLoggingRpcV2().list(request);
        return LoggingImpl.transform(list2, new Function<ListSinksResponse, AsyncPage<Sink>>(){

            @Override
            public AsyncPage<Sink> apply(ListSinksResponse listSinksResponse) {
                ImmutableList sinks = listSinksResponse.getSinksList() == null ? ImmutableList.of() : Lists.transform(listSinksResponse.getSinksList(), Sink.fromPbFunction((Logging)serviceOptions.getService()));
                String cursor = listSinksResponse.getNextPageToken().equals("") ? null : listSinksResponse.getNextPageToken();
                return new AsyncPageImpl<Sink>(new SinkPageFetcher(serviceOptions, cursor, options), cursor, sinks);
            }
        });
    }

    @Override
    public Page<Sink> listSinks(Logging.ListOption ... options) {
        return LoggingImpl.get(this.listSinksAsync(options));
    }

    @Override
    public ApiFuture<AsyncPage<Sink>> listSinksAsync(Logging.ListOption ... options) {
        return LoggingImpl.listSinksAsync((LoggingOptions)this.getOptions(), LoggingImpl.optionMap(options));
    }

    @Override
    public boolean deleteSink(String sink) {
        return LoggingImpl.get(this.deleteSinkAsync(sink));
    }

    @Override
    public ApiFuture<Boolean> deleteSinkAsync(String sink) {
        DeleteSinkRequest request = DeleteSinkRequest.newBuilder().setSinkName(ProjectSinkName.of(((LoggingOptions)this.getOptions()).getProjectId(), sink).toString()).build();
        return LoggingImpl.transform(this.rpc.delete(request), EMPTY_TO_BOOLEAN_FUNCTION);
    }

    @Override
    public boolean deleteLog(String log) {
        return LoggingImpl.get(this.deleteLogAsync(log));
    }

    @Override
    public ApiFuture<Boolean> deleteLogAsync(String log) {
        DeleteLogRequest request = DeleteLogRequest.newBuilder().setLogName(ProjectLogName.of(((LoggingOptions)this.getOptions()).getProjectId(), log).toString()).build();
        return LoggingImpl.transform(this.rpc.delete(request), EMPTY_TO_BOOLEAN_FUNCTION);
    }

    private static ListMonitoredResourceDescriptorsRequest listMonitoredResourceDescriptorsRequest(Map<Option.OptionType, ?> options) {
        ListMonitoredResourceDescriptorsRequest.Builder builder = ListMonitoredResourceDescriptorsRequest.newBuilder();
        Integer pageSize = (Integer)Logging.ListOption.OptionType.PAGE_SIZE.get(options);
        String pageToken = (String)Logging.ListOption.OptionType.PAGE_TOKEN.get(options);
        if (pageSize != null) {
            builder.setPageSize(pageSize);
        }
        if (pageToken != null) {
            builder.setPageToken(pageToken);
        }
        return builder.build();
    }

    private static ApiFuture<AsyncPage<MonitoredResourceDescriptor>> listMonitoredResourceDescriptorsAsync(final LoggingOptions serviceOptions, final Map<Option.OptionType, ?> options) {
        ListMonitoredResourceDescriptorsRequest request = LoggingImpl.listMonitoredResourceDescriptorsRequest(options);
        ApiFuture<ListMonitoredResourceDescriptorsResponse> list2 = serviceOptions.getLoggingRpcV2().list(request);
        return LoggingImpl.transform(list2, new Function<ListMonitoredResourceDescriptorsResponse, AsyncPage<MonitoredResourceDescriptor>>(){

            @Override
            public AsyncPage<MonitoredResourceDescriptor> apply(ListMonitoredResourceDescriptorsResponse listDescriptorsResponse) {
                ImmutableList descriptors = listDescriptorsResponse.getResourceDescriptorsList() == null ? ImmutableList.of() : Lists.transform(listDescriptorsResponse.getResourceDescriptorsList(), new Function<com.google.api.MonitoredResourceDescriptor, MonitoredResourceDescriptor>(){

                    @Override
                    public MonitoredResourceDescriptor apply(com.google.api.MonitoredResourceDescriptor monitoredResourceDescriptor) {
                        return MonitoredResourceDescriptor.FROM_PB_FUNCTION.apply(monitoredResourceDescriptor);
                    }
                });
                String cursor = listDescriptorsResponse.getNextPageToken().equals("") ? null : listDescriptorsResponse.getNextPageToken();
                return new AsyncPageImpl<MonitoredResourceDescriptor>(new MonitoredResourceDescriptorPageFetcher(serviceOptions, cursor, options), cursor, descriptors);
            }
        });
    }

    @Override
    public Page<MonitoredResourceDescriptor> listMonitoredResourceDescriptors(Logging.ListOption ... options) {
        return LoggingImpl.get(this.listMonitoredResourceDescriptorsAsync(options));
    }

    @Override
    public ApiFuture<AsyncPage<MonitoredResourceDescriptor>> listMonitoredResourceDescriptorsAsync(Logging.ListOption ... options) {
        return LoggingImpl.listMonitoredResourceDescriptorsAsync((LoggingOptions)this.getOptions(), LoggingImpl.optionMap(options));
    }

    @Override
    public Metric create(MetricInfo metric) {
        return LoggingImpl.get(this.createAsync(metric));
    }

    @Override
    public ApiFuture<Metric> createAsync(MetricInfo metric) {
        CreateLogMetricRequest request = CreateLogMetricRequest.newBuilder().setParent(ProjectName.of(((LoggingOptions)this.getOptions()).getProjectId()).toString()).setMetric(metric.toPb()).build();
        return LoggingImpl.transform(this.rpc.create(request), Metric.fromPbFunction(this));
    }

    @Override
    public Metric update(MetricInfo metric) {
        return LoggingImpl.get(this.updateAsync(metric));
    }

    @Override
    public ApiFuture<Metric> updateAsync(MetricInfo metric) {
        UpdateLogMetricRequest request = UpdateLogMetricRequest.newBuilder().setMetricName(ProjectMetricName.of(((LoggingOptions)this.getOptions()).getProjectId(), metric.getName()).toString()).setMetric(metric.toPb()).build();
        return LoggingImpl.transform(this.rpc.update(request), Metric.fromPbFunction(this));
    }

    @Override
    public Metric getMetric(String metric) {
        return LoggingImpl.get(this.getMetricAsync(metric));
    }

    @Override
    public ApiFuture<Metric> getMetricAsync(String metric) {
        GetLogMetricRequest request = GetLogMetricRequest.newBuilder().setMetricName(ProjectMetricName.of(((LoggingOptions)this.getOptions()).getProjectId(), metric).toString()).build();
        return LoggingImpl.transform(this.rpc.get(request), Metric.fromPbFunction(this));
    }

    private static ListLogMetricsRequest listMetricsRequest(LoggingOptions serviceOptions, Map<Option.OptionType, ?> options) {
        ListLogMetricsRequest.Builder builder = ListLogMetricsRequest.newBuilder();
        builder.setParent(ProjectName.of(serviceOptions.getProjectId()).toString());
        Integer pageSize = (Integer)Logging.ListOption.OptionType.PAGE_SIZE.get(options);
        String pageToken = (String)Logging.ListOption.OptionType.PAGE_TOKEN.get(options);
        if (pageSize != null) {
            builder.setPageSize(pageSize);
        }
        if (pageToken != null) {
            builder.setPageToken(pageToken);
        }
        return builder.build();
    }

    private static ApiFuture<AsyncPage<Metric>> listMetricsAsync(final LoggingOptions serviceOptions, final Map<Option.OptionType, ?> options) {
        ListLogMetricsRequest request = LoggingImpl.listMetricsRequest(serviceOptions, options);
        ApiFuture<ListLogMetricsResponse> list2 = serviceOptions.getLoggingRpcV2().list(request);
        return LoggingImpl.transform(list2, new Function<ListLogMetricsResponse, AsyncPage<Metric>>(){

            @Override
            public AsyncPage<Metric> apply(ListLogMetricsResponse listMetricsResponse) {
                ImmutableList metrics = listMetricsResponse.getMetricsList() == null ? ImmutableList.of() : Lists.transform(listMetricsResponse.getMetricsList(), Metric.fromPbFunction((Logging)serviceOptions.getService()));
                String cursor = listMetricsResponse.getNextPageToken().equals("") ? null : listMetricsResponse.getNextPageToken();
                return new AsyncPageImpl<Metric>(new MetricPageFetcher(serviceOptions, cursor, options), cursor, metrics);
            }
        });
    }

    @Override
    public Page<Metric> listMetrics(Logging.ListOption ... options) {
        return LoggingImpl.get(this.listMetricsAsync(options));
    }

    @Override
    public ApiFuture<AsyncPage<Metric>> listMetricsAsync(Logging.ListOption ... options) {
        return LoggingImpl.listMetricsAsync((LoggingOptions)this.getOptions(), LoggingImpl.optionMap(options));
    }

    @Override
    public boolean deleteMetric(String metric) {
        return LoggingImpl.get(this.deleteMetricAsync(metric));
    }

    @Override
    public ApiFuture<Boolean> deleteMetricAsync(String metric) {
        DeleteLogMetricRequest request = DeleteLogMetricRequest.newBuilder().setMetricName(ProjectMetricName.of(((LoggingOptions)this.getOptions()).getProjectId(), metric).toString()).build();
        return LoggingImpl.transform(this.rpc.delete(request), EMPTY_TO_BOOLEAN_FUNCTION);
    }

    private static WriteLogEntriesRequest writeLogEntriesRequest(LoggingOptions serviceOptions, Iterable<LogEntry> logEntries, Map<Option.OptionType, ?> options) {
        Map labels;
        MonitoredResource resource;
        String projectId = serviceOptions.getProjectId();
        WriteLogEntriesRequest.Builder builder = WriteLogEntriesRequest.newBuilder();
        String logName = (String)Logging.WriteOption.OptionType.LOG_NAME.get(options);
        if (logName != null) {
            builder.setLogName(ProjectLogName.of(projectId, logName).toString());
        }
        if ((resource = (MonitoredResource)Logging.WriteOption.OptionType.RESOURCE.get(options)) != null) {
            builder.setResource(resource.toPb());
        }
        if ((labels = (Map)Logging.WriteOption.OptionType.LABELS.get(options)) != null) {
            builder.putAllLabels(labels);
        }
        builder.addAllEntries(Iterables.transform(logEntries, LogEntry.toPbFunction(projectId)));
        return builder.build();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void write(Iterable<LogEntry> logEntries, Logging.WriteOption ... options) {
        if (inWriteCall.get() != null) {
            return;
        }
        inWriteCall.set(true);
        try {
            this.writeLogEntries(logEntries, options);
            for (LogEntry logEntry : logEntries) {
                if (logEntry.getSeverity().compareTo(this.flushSeverity) < 0) continue;
                this.flush();
                break;
            }
        }
        finally {
            inWriteCall.remove();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void flush() {
        ArrayList<ApiFuture<Void>> writesToFlush = new ArrayList<ApiFuture<Void>>();
        Object object = this.writeLock;
        synchronized (object) {
            writesToFlush.addAll(this.pendingWrites);
        }
        try {
            ApiFutures.allAsList(writesToFlush).get();
        }
        catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void writeLogEntries(Iterable<LogEntry> logEntries, Logging.WriteOption ... writeOptions) {
        switch (this.writeSynchronicity) {
            case SYNC: {
                LoggingImpl.get(this.writeAsync(logEntries, writeOptions));
                break;
            }
            default: {
                final ApiFuture<Void> writeFuture = this.writeAsync(logEntries, writeOptions);
                ApiFutures.addCallback(writeFuture, new ApiFutureCallback<Void>(){

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     */
                    private void removeFromPending() {
                        Object object = LoggingImpl.this.writeLock;
                        synchronized (object) {
                            LoggingImpl.this.pendingWrites.remove(writeFuture);
                        }
                    }

                    @Override
                    public void onSuccess(Void v) {
                        this.removeFromPending();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        try {
                            Exception ex = t instanceof Exception ? (Exception)t : new Exception(t);
                            throw new RuntimeException(ex);
                        }
                        catch (Throwable throwable) {
                            this.removeFromPending();
                            throw throwable;
                        }
                    }
                });
                Object object = this.writeLock;
                synchronized (object) {
                    this.pendingWrites.add(writeFuture);
                    break;
                }
            }
        }
    }

    private ApiFuture<Void> writeAsync(Iterable<LogEntry> logEntries, Logging.WriteOption ... options) {
        return LoggingImpl.transform(this.rpc.write(LoggingImpl.writeLogEntriesRequest((LoggingOptions)this.getOptions(), logEntries, LoggingImpl.optionMap(options))), WRITE_RESPONSE_TO_VOID_FUNCTION);
    }

    static ListLogEntriesRequest listLogEntriesRequest(String projectId, Map<Option.OptionType, ?> options) {
        String filter2;
        String orderBy;
        String pageToken;
        ListLogEntriesRequest.Builder builder = ListLogEntriesRequest.newBuilder();
        builder.addProjectIds(projectId);
        Integer pageSize = (Integer)Logging.ListOption.OptionType.PAGE_SIZE.get(options);
        if (pageSize != null) {
            builder.setPageSize(pageSize);
        }
        if ((pageToken = (String)Logging.ListOption.OptionType.PAGE_TOKEN.get(options)) != null) {
            builder.setPageToken(pageToken);
        }
        if ((orderBy = (String)Logging.EntryListOption.OptionType.ORDER_BY.get(options)) != null) {
            builder.setOrderBy(orderBy);
        }
        if ((filter2 = (String)Logging.EntryListOption.OptionType.FILTER.get(options)) != null) {
            builder.setFilter(filter2);
        }
        return builder.build();
    }

    private static ApiFuture<AsyncPage<LogEntry>> listLogEntriesAsync(final LoggingOptions serviceOptions, final Map<Option.OptionType, ?> options) {
        ListLogEntriesRequest request = LoggingImpl.listLogEntriesRequest(serviceOptions.getProjectId(), options);
        ApiFuture<ListLogEntriesResponse> list2 = serviceOptions.getLoggingRpcV2().list(request);
        return LoggingImpl.transform(list2, new Function<ListLogEntriesResponse, AsyncPage<LogEntry>>(){

            @Override
            public AsyncPage<LogEntry> apply(ListLogEntriesResponse listLogEntrysResponse) {
                ImmutableList entries = listLogEntrysResponse.getEntriesList() == null ? ImmutableList.of() : Lists.transform(listLogEntrysResponse.getEntriesList(), LogEntry.FROM_PB_FUNCTION);
                String cursor = listLogEntrysResponse.getNextPageToken().equals("") ? null : listLogEntrysResponse.getNextPageToken();
                return new AsyncPageImpl<LogEntry>(new LogEntryPageFetcher(serviceOptions, cursor, options), cursor, entries);
            }
        });
    }

    @Override
    public Page<LogEntry> listLogEntries(Logging.EntryListOption ... options) {
        return LoggingImpl.get(this.listLogEntriesAsync(options));
    }

    @Override
    public ApiFuture<AsyncPage<LogEntry>> listLogEntriesAsync(Logging.EntryListOption ... options) {
        return LoggingImpl.listLogEntriesAsync((LoggingOptions)this.getOptions(), LoggingImpl.optionMap(options));
    }

    @Override
    public void close() throws Exception {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.rpc.close();
    }

    static <T extends Option.OptionType> Map<Option.OptionType, ?> optionMap(Option ... options) {
        HashMap optionMap = Maps.newHashMap();
        for (Option option : options) {
            Object prev = optionMap.put(option.getOptionType(), option.getValue());
            Preconditions.checkArgument(prev == null, "Duplicate option %s", option);
        }
        return optionMap;
    }

    private static class LogEntryPageFetcher
    extends BasePageFetcher<LogEntry> {
        private static final long serialVersionUID = 4001239712280747734L;

        LogEntryPageFetcher(LoggingOptions serviceOptions, String cursor, Map<Option.OptionType, ?> requestOptions) {
            super(serviceOptions, cursor, requestOptions);
        }

        @Override
        public ApiFuture<AsyncPage<LogEntry>> getNextPage() {
            return LoggingImpl.listLogEntriesAsync(this.serviceOptions(), this.requestOptions());
        }
    }

    private static class MetricPageFetcher
    extends BasePageFetcher<Metric> {
        private static final long serialVersionUID = -316783549651771553L;

        MetricPageFetcher(LoggingOptions serviceOptions, String cursor, Map<Option.OptionType, ?> requestOptions) {
            super(serviceOptions, cursor, requestOptions);
        }

        @Override
        public ApiFuture<AsyncPage<Metric>> getNextPage() {
            return LoggingImpl.listMetricsAsync(this.serviceOptions(), this.requestOptions());
        }
    }

    private static class MonitoredResourceDescriptorPageFetcher
    extends BasePageFetcher<MonitoredResourceDescriptor> {
        private static final long serialVersionUID = -2346495771766629195L;

        MonitoredResourceDescriptorPageFetcher(LoggingOptions serviceOptions, String cursor, Map<Option.OptionType, ?> requestOptions) {
            super(serviceOptions, cursor, requestOptions);
        }

        @Override
        public ApiFuture<AsyncPage<MonitoredResourceDescriptor>> getNextPage() {
            return LoggingImpl.listMonitoredResourceDescriptorsAsync(this.serviceOptions(), this.requestOptions());
        }
    }

    private static class SinkPageFetcher
    extends BasePageFetcher<Sink> {
        private static final long serialVersionUID = 4879364260060886875L;

        SinkPageFetcher(LoggingOptions serviceOptions, String cursor, Map<Option.OptionType, ?> requestOptions) {
            super(serviceOptions, cursor, requestOptions);
        }

        @Override
        public ApiFuture<AsyncPage<Sink>> getNextPage() {
            return LoggingImpl.listSinksAsync(this.serviceOptions(), this.requestOptions());
        }
    }

    private static abstract class BasePageFetcher<T>
    implements AsyncPageImpl.NextPageFetcher<T> {
        private static final long serialVersionUID = 5095123855547444030L;
        private final LoggingOptions serviceOptions;
        private final Map<Option.OptionType, ?> requestOptions;

        private BasePageFetcher(LoggingOptions serviceOptions, String cursor, Map<Option.OptionType, ?> requestOptions) {
            this.serviceOptions = serviceOptions;
            this.requestOptions = PageImpl.nextRequestOptions(Logging.ListOption.OptionType.PAGE_TOKEN, cursor, requestOptions);
        }

        LoggingOptions serviceOptions() {
            return this.serviceOptions;
        }

        Map<Option.OptionType, ?> requestOptions() {
            return this.requestOptions;
        }
    }
}

