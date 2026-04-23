/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.paging;

import com.google.api.gax.paging.FixedSizeCollection;
import com.google.api.gax.paging.Page;

public interface PagedListResponse<ResourceT> {
    public Iterable<ResourceT> iterateAll();

    public Page<ResourceT> getPage();

    public Iterable<? extends Page<ResourceT>> iteratePages();

    public String getNextPageToken();

    public FixedSizeCollection<ResourceT> expandToFixedSizeCollection(int var1);

    public Iterable<? extends FixedSizeCollection<ResourceT>> iterateFixedSizeCollections(int var1);
}

