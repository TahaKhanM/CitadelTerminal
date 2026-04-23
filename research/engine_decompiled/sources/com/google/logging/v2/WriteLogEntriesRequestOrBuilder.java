/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.MonitoredResource;
import com.google.api.MonitoredResourceOrBuilder;
import com.google.logging.v2.LogEntry;
import com.google.logging.v2.LogEntryOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;
import java.util.Map;

public interface WriteLogEntriesRequestOrBuilder
extends MessageOrBuilder {
    public String getLogName();

    public ByteString getLogNameBytes();

    public boolean hasResource();

    public MonitoredResource getResource();

    public MonitoredResourceOrBuilder getResourceOrBuilder();

    public int getLabelsCount();

    public boolean containsLabels(String var1);

    @Deprecated
    public Map<String, String> getLabels();

    public Map<String, String> getLabelsMap();

    public String getLabelsOrDefault(String var1, String var2);

    public String getLabelsOrThrow(String var1);

    public List<LogEntry> getEntriesList();

    public LogEntry getEntries(int var1);

    public int getEntriesCount();

    public List<? extends LogEntryOrBuilder> getEntriesOrBuilderList();

    public LogEntryOrBuilder getEntriesOrBuilder(int var1);

    public boolean getPartialSuccess();

    public boolean getDryRun();
}

