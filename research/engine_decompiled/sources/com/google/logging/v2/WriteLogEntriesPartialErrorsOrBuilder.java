/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.protobuf.MessageOrBuilder;
import com.google.rpc.Status;
import java.util.Map;

public interface WriteLogEntriesPartialErrorsOrBuilder
extends MessageOrBuilder {
    public int getLogEntryErrorsCount();

    public boolean containsLogEntryErrors(int var1);

    @Deprecated
    public Map<Integer, Status> getLogEntryErrors();

    public Map<Integer, Status> getLogEntryErrorsMap();

    public Status getLogEntryErrorsOrDefault(int var1, Status var2);

    public Status getLogEntryErrorsOrThrow(int var1);
}

