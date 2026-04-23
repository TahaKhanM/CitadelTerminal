/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.InternalApi;
import com.google.api.gax.paging.AsyncPage;
import com.google.api.gax.paging.Page;
import com.google.cloud.PageImpl;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.Uninterruptibles;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;

@InternalApi
public class AsyncPageImpl<T>
extends PageImpl<T>
implements AsyncPage<T> {
    private static final long serialVersionUID = -6009473188630364906L;
    private final NextPageFetcher<T> asyncPageFetcher;

    public AsyncPageImpl(NextPageFetcher<T> asyncPageFetcher, String cursor, Iterable<T> results) {
        super(new SyncNextPageFetcher(asyncPageFetcher), cursor, results);
        this.asyncPageFetcher = asyncPageFetcher;
    }

    @Override
    public ApiFuture<AsyncPage<T>> getNextPageAsync() {
        if (this.getNextPageToken() == null || this.asyncPageFetcher == null) {
            return ApiFutures.immediateFuture(null);
        }
        return this.asyncPageFetcher.getNextPage();
    }

    private static class SyncNextPageFetcher<T>
    implements PageImpl.NextPageFetcher<T> {
        private static final long serialVersionUID = -4124568632363525351L;
        private final NextPageFetcher<T> asyncPageFetcher;

        private SyncNextPageFetcher(NextPageFetcher<T> asyncPageFetcher) {
            this.asyncPageFetcher = asyncPageFetcher;
        }

        @Override
        public Page<T> getNextPage() {
            try {
                return this.asyncPageFetcher != null ? Uninterruptibles.getUninterruptibly(this.asyncPageFetcher.getNextPage()) : null;
            }
            catch (ExecutionException ex) {
                Throwables.throwIfUnchecked(ex.getCause());
                throw new RuntimeException(ex);
            }
        }
    }

    public static interface NextPageFetcher<T>
    extends Serializable {
        public ApiFuture<AsyncPage<T>> getNextPage();
    }
}

