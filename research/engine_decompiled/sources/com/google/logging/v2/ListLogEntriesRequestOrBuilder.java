/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface ListLogEntriesRequestOrBuilder
extends MessageOrBuilder {
    @Deprecated
    public List<String> getProjectIdsList();

    @Deprecated
    public int getProjectIdsCount();

    @Deprecated
    public String getProjectIds(int var1);

    @Deprecated
    public ByteString getProjectIdsBytes(int var1);

    public List<String> getResourceNamesList();

    public int getResourceNamesCount();

    public String getResourceNames(int var1);

    public ByteString getResourceNamesBytes(int var1);

    public String getFilter();

    public ByteString getFilterBytes();

    public String getOrderBy();

    public ByteString getOrderByBytes();

    public int getPageSize();

    public String getPageToken();

    public ByteString getPageTokenBytes();
}

