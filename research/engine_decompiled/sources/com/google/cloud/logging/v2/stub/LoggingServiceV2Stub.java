/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging.v2.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.logging.v2.LoggingClient;
import com.google.logging.v2.DeleteLogRequest;
import com.google.logging.v2.ListLogEntriesRequest;
import com.google.logging.v2.ListLogEntriesResponse;
import com.google.logging.v2.ListLogsRequest;
import com.google.logging.v2.ListLogsResponse;
import com.google.logging.v2.ListMonitoredResourceDescriptorsRequest;
import com.google.logging.v2.ListMonitoredResourceDescriptorsResponse;
import com.google.logging.v2.WriteLogEntriesRequest;
import com.google.logging.v2.WriteLogEntriesResponse;
import com.google.protobuf.Empty;

@BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
public abstract class LoggingServiceV2Stub
implements BackgroundResource {
    public UnaryCallable<DeleteLogRequest, Empty> deleteLogCallable() {
        throw new UnsupportedOperationException("Not implemented: deleteLogCallable()");
    }

    public UnaryCallable<WriteLogEntriesRequest, WriteLogEntriesResponse> writeLogEntriesCallable() {
        throw new UnsupportedOperationException("Not implemented: writeLogEntriesCallable()");
    }

    public UnaryCallable<ListLogEntriesRequest, LoggingClient.ListLogEntriesPagedResponse> listLogEntriesPagedCallable() {
        throw new UnsupportedOperationException("Not implemented: listLogEntriesPagedCallable()");
    }

    public UnaryCallable<ListLogEntriesRequest, ListLogEntriesResponse> listLogEntriesCallable() {
        throw new UnsupportedOperationException("Not implemented: listLogEntriesCallable()");
    }

    public UnaryCallable<ListMonitoredResourceDescriptorsRequest, LoggingClient.ListMonitoredResourceDescriptorsPagedResponse> listMonitoredResourceDescriptorsPagedCallable() {
        throw new UnsupportedOperationException("Not implemented: listMonitoredResourceDescriptorsPagedCallable()");
    }

    public UnaryCallable<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse> listMonitoredResourceDescriptorsCallable() {
        throw new UnsupportedOperationException("Not implemented: listMonitoredResourceDescriptorsCallable()");
    }

    public UnaryCallable<ListLogsRequest, LoggingClient.ListLogsPagedResponse> listLogsPagedCallable() {
        throw new UnsupportedOperationException("Not implemented: listLogsPagedCallable()");
    }

    public UnaryCallable<ListLogsRequest, ListLogsResponse> listLogsCallable() {
        throw new UnsupportedOperationException("Not implemented: listLogsCallable()");
    }

    @Override
    public abstract void close();
}

