/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.LogEntry;
import com.google.logging.v2.LogEntryOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface ListLogEntriesResponseOrBuilder
extends MessageOrBuilder {
    public List<LogEntry> getEntriesList();

    public LogEntry getEntries(int var1);

    public int getEntriesCount();

    public List<? extends LogEntryOrBuilder> getEntriesOrBuilderList();

    public LogEntryOrBuilder getEntriesOrBuilder(int var1);

    public String getNextPageToken();

    public ByteString getNextPageTokenBytes();
}

