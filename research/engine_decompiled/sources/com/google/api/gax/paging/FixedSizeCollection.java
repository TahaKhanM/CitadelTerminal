/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.paging;

public interface FixedSizeCollection<ResourceT> {
    public int getCollectionSize();

    public boolean hasNextCollection();

    public String getNextPageToken();

    public FixedSizeCollection<ResourceT> getNextCollection();

    public Iterable<ResourceT> getValues();
}

