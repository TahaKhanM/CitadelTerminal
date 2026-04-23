/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.paging;

import com.google.api.core.BetaApi;
import com.google.api.gax.paging.Page;
import java.util.Collections;

@BetaApi
public class Pages {
    private Pages() {
    }

    public static <ResourceT> Page<ResourceT> empty() {
        return new Page<ResourceT>(){

            @Override
            public boolean hasNextPage() {
                return false;
            }

            @Override
            public String getNextPageToken() {
                return "";
            }

            @Override
            public Page<ResourceT> getNextPage() {
                return null;
            }

            @Override
            public Iterable<ResourceT> iterateAll() {
                return Collections.emptyList();
            }

            @Override
            public Iterable<ResourceT> getValues() {
                return Collections.emptyList();
            }
        };
    }
}

