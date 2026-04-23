/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.paging;

public interface Page<ResourceT> {
    public boolean hasNextPage();

    public String getNextPageToken();

    public Page<ResourceT> getNextPage();

    public Iterable<ResourceT> iterateAll();

    public Iterable<ResourceT> getValues();
}

