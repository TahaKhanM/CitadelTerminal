/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.Distribution;
import com.google.api.MetricDescriptor;
import com.google.api.MetricDescriptorOrBuilder;
import com.google.logging.v2.LogMetric;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.Map;

public interface LogMetricOrBuilder
extends MessageOrBuilder {
    public String getName();

    public ByteString getNameBytes();

    public String getDescription();

    public ByteString getDescriptionBytes();

    public String getFilter();

    public ByteString getFilterBytes();

    public boolean hasMetricDescriptor();

    public MetricDescriptor getMetricDescriptor();

    public MetricDescriptorOrBuilder getMetricDescriptorOrBuilder();

    public String getValueExtractor();

    public ByteString getValueExtractorBytes();

    public int getLabelExtractorsCount();

    public boolean containsLabelExtractors(String var1);

    @Deprecated
    public Map<String, String> getLabelExtractors();

    public Map<String, String> getLabelExtractorsMap();

    public String getLabelExtractorsOrDefault(String var1, String var2);

    public String getLabelExtractorsOrThrow(String var1);

    public boolean hasBucketOptions();

    public Distribution.BucketOptions getBucketOptions();

    public Distribution.BucketOptionsOrBuilder getBucketOptionsOrBuilder();

    @Deprecated
    public int getVersionValue();

    @Deprecated
    public LogMetric.ApiVersion getVersion();
}

