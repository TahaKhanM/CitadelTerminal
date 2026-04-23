/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogMetric;
import com.google.logging.v2.LogMetricOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface CreateLogMetricRequestOrBuilder
extends MessageOrBuilder {
    public String getParent();

    public ByteString getParentBytes();

    public boolean hasMetric();

    public LogMetric getMetric();

    public LogMetricOrBuilder getMetricOrBuilder();
}

