/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.paging;

import com.google.api.gax.paging.AbstractFixedSizeCollection;
import com.google.api.gax.paging.AbstractPage;
import com.google.api.gax.paging.FixedSizeCollection;
import com.google.api.gax.paging.PagedListResponse;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractPagedListResponse<RequestT, ResponseT, ResourceT, PageT extends AbstractPage<RequestT, ResponseT, ResourceT, PageT>, CollectionT extends AbstractFixedSizeCollection<RequestT, ResponseT, ResourceT, PageT, CollectionT>>
implements PagedListResponse<ResourceT> {
    private final PageT page;
    private final CollectionT emptyCollection;

    protected AbstractPagedListResponse(PageT page, CollectionT emptyCollection) {
        this.page = page;
        this.emptyCollection = emptyCollection;
    }

    @Override
    public Iterable<ResourceT> iterateAll() {
        return ((AbstractPage)this.getPage()).iterateAll();
    }

    public PageT getPage() {
        return this.page;
    }

    @Override
    public Iterable<PageT> iteratePages() {
        return new Iterable<PageT>(){

            @Override
            public Iterator<PageT> iterator() {
                return new AllPagesIterator(AbstractPagedListResponse.this, AbstractPagedListResponse.this.page);
            }
        };
    }

    @Override
    public String getNextPageToken() {
        return ((AbstractPage)this.getPage()).getNextPageToken();
    }

    public CollectionT expandToFixedSizeCollection(int collectionSize) {
        List<PageT> pages = ((AbstractFixedSizeCollection)this.emptyCollection).getPages(this.page, collectionSize);
        return ((AbstractFixedSizeCollection)this.emptyCollection).createCollection(pages, collectionSize);
    }

    @Override
    public Iterable<CollectionT> iterateFixedSizeCollections(int collectionSize) {
        FixedSizeCollection firstCollection = this.expandToFixedSizeCollection(collectionSize);
        return new Iterable<CollectionT>((AbstractFixedSizeCollection)firstCollection){
            final /* synthetic */ AbstractFixedSizeCollection val$firstCollection;
            {
                this.val$firstCollection = abstractFixedSizeCollection;
            }

            @Override
            public Iterator<CollectionT> iterator() {
                return new AllCollectionsIterator(AbstractPagedListResponse.this, this.val$firstCollection);
            }
        };
    }

    private class NextIterator<T>
    extends AbstractIterator<T> {
        private T current;
        private final Next<T> fetcher;
        private boolean computeFirst = true;

        private NextIterator(T first, Next<T> fetcher) {
            this.current = Preconditions.checkNotNull(first);
            this.fetcher = fetcher;
        }

        @Override
        protected T computeNext() {
            if (this.computeFirst) {
                this.computeFirst = false;
                return this.current;
            }
            this.current = this.fetcher.next(this.current);
            if (this.current == null) {
                return this.endOfData();
            }
            return this.current;
        }
    }

    private static class AllCollectionsIterator
    extends NextIterator<CollectionT> {
        final /* synthetic */ AbstractPagedListResponse this$0;

        private AllCollectionsIterator(CollectionT firstCollection) {
            this.this$0 = var1_1;
            super(firstCollection, new Next<CollectionT>(){

                @Override
                public CollectionT next(CollectionT current) {
                    return ((AbstractFixedSizeCollection)current).getNextCollection();
                }
            });
        }
    }

    private static class AllPagesIterator
    extends NextIterator<PageT> {
        final /* synthetic */ AbstractPagedListResponse this$0;

        private AllPagesIterator(PageT firstCollection) {
            this.this$0 = var1_1;
            super(firstCollection, new Next<PageT>(){

                @Override
                public PageT next(PageT current) {
                    return ((AbstractPage)current).getNextPage();
                }
            });
        }
    }

    private static interface Next<T> {
        public T next(T var1);
    }
}

