/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.paging;

import com.google.api.core.ApiFuture;
import com.google.api.gax.paging.Page;

public interface AsyncPage<ResourceT>
extends Page<ResourceT> {
    public ApiFuture<? extends AsyncPage<ResourceT>> getNextPageAsync();
}

