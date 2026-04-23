/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging.spi.v2;

import com.google.api.core.ApiFuture;
import com.google.cloud.ServiceRpc;
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
import com.google.logging.v2.LogMetric;
import com.google.logging.v2.LogSink;
import com.google.logging.v2.UpdateLogMetricRequest;
import com.google.logging.v2.UpdateSinkRequest;
import com.google.logging.v2.WriteLogEntriesRequest;
import com.google.logging.v2.WriteLogEntriesResponse;
import com.google.protobuf.Empty;

public interface LoggingRpc
extends AutoCloseable,
ServiceRpc {
    public ApiFuture<LogSink> create(CreateSinkRequest var1);

    public ApiFuture<LogSink> update(UpdateSinkRequest var1);

    public ApiFuture<LogSink> get(GetSinkRequest var1);

    public ApiFuture<ListSinksResponse> list(ListSinksRequest var1);

    public ApiFuture<Empty> delete(DeleteSinkRequest var1);

    public ApiFuture<Empty> delete(DeleteLogRequest var1);

    public ApiFuture<WriteLogEntriesResponse> write(WriteLogEntriesRequest var1);

    public ApiFuture<ListLogEntriesResponse> list(ListLogEntriesRequest var1);

    public ApiFuture<ListMonitoredResourceDescriptorsResponse> list(ListMonitoredResourceDescriptorsRequest var1);

    public ApiFuture<LogMetric> create(CreateLogMetricRequest var1);

    public ApiFuture<LogMetric> update(UpdateLogMetricRequest var1);

    public ApiFuture<LogMetric> get(GetLogMetricRequest var1);

    public ApiFuture<ListLogMetricsResponse> list(ListLogMetricsRequest var1);

    public ApiFuture<Empty> delete(DeleteLogMetricRequest var1);
}

