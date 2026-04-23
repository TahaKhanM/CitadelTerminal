/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging.v2.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.logging.v2.MetricsClient;
import com.google.logging.v2.CreateLogMetricRequest;
import com.google.logging.v2.DeleteLogMetricRequest;
import com.google.logging.v2.GetLogMetricRequest;
import com.google.logging.v2.ListLogMetricsRequest;
import com.google.logging.v2.ListLogMetricsResponse;
import com.google.logging.v2.LogMetric;
import com.google.logging.v2.UpdateLogMetricRequest;
import com.google.protobuf.Empty;

@BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
public abstract class MetricsServiceV2Stub
implements BackgroundResource {
    public UnaryCallable<ListLogMetricsRequest, MetricsClient.ListLogMetricsPagedResponse> listLogMetricsPagedCallable() {
        throw new UnsupportedOperationException("Not implemented: listLogMetricsPagedCallable()");
    }

    public UnaryCallable<ListLogMetricsRequest, ListLogMetricsResponse> listLogMetricsCallable() {
        throw new UnsupportedOperationException("Not implemented: listLogMetricsCallable()");
    }

    public UnaryCallable<GetLogMetricRequest, LogMetric> getLogMetricCallable() {
        throw new UnsupportedOperationException("Not implemented: getLogMetricCallable()");
    }

    public UnaryCallable<CreateLogMetricRequest, LogMetric> createLogMetricCallable() {
        throw new UnsupportedOperationException("Not implemented: createLogMetricCallable()");
    }

    public UnaryCallable<UpdateLogMetricRequest, LogMetric> updateLogMetricCallable() {
        throw new UnsupportedOperationException("Not implemented: updateLogMetricCallable()");
    }

    public UnaryCallable<DeleteLogMetricRequest, Empty> deleteLogMetricCallable() {
        throw new UnsupportedOperationException("Not implemented: deleteLogMetricCallable()");
    }

    @Override
    public abstract void close();
}

