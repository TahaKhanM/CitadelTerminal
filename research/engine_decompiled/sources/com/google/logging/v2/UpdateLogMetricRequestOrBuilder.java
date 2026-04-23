/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogMetric;
import com.google.logging.v2.LogMetricOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface UpdateLogMetricRequestOrBuilder
extends MessageOrBuilder {
    public String getMetricName();

    public ByteString getMetricNameBytes();

    public boolean hasMetric();

    public LogMetric getMetric();

    public LogMetricOrBuilder getMetricOrBuilder();
}

