/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogMetric;
import com.google.logging.v2.LogMetricOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface ListLogMetricsResponseOrBuilder
extends MessageOrBuilder {
    public List<LogMetric> getMetricsList();

    public LogMetric getMetrics(int var1);

    public int getMetricsCount();

    public List<? extends LogMetricOrBuilder> getMetricsOrBuilderList();

    public LogMetricOrBuilder getMetricsOrBuilder(int var1);

    public String getNextPageToken();

    public ByteString getNextPageTokenBytes();
}

