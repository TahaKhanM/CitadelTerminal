/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.paging;

import com.google.api.client.util.Lists;
import com.google.api.gax.paging.AbstractPage;
import com.google.api.gax.paging.FixedSizeCollection;
import com.google.api.gax.paging.Page;
import com.google.api.pathtemplate.ValidationException;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractFixedSizeCollection<RequestT, ResponseT, ResourceT, PageT extends AbstractPage<RequestT, ResponseT, ResourceT, PageT>, CollectionT extends AbstractFixedSizeCollection<RequestT, ResponseT, ResourceT, PageT, CollectionT>>
implements FixedSizeCollection<ResourceT> {
    private final List<PageT> pageList;
    private final int collectionSize;

    protected AbstractFixedSizeCollection(List<PageT> pages, int collectionSize) {
        this.pageList = pages;
        this.collectionSize = collectionSize;
    }

    protected abstract CollectionT createCollection(List<PageT> var1, int var2);

    @Override
    public Iterable<ResourceT> getValues() {
        return new Iterable<ResourceT>(){

            @Override
            public Iterator<ResourceT> iterator() {
                return new CollectionResourcesIterator();
            }
        };
    }

    public CollectionT getNextCollection() {
        if (this.hasNextCollection()) {
            Page nextFirstPage = ((AbstractPage)this.getLastPage()).getNextPage();
            List<Page> pages = this.getPages(nextFirstPage, this.collectionSize);
            return this.createCollection(pages, this.collectionSize);
        }
        return null;
    }

    @Override
    public boolean hasNextCollection() {
        return ((AbstractPage)this.getLastPage()).hasNextPage();
    }

    @Override
    public String getNextPageToken() {
        return ((AbstractPage)this.getLastPage()).getNextPageToken();
    }

    @Override
    public int getCollectionSize() {
        int size2 = 0;
        for (AbstractPage page : this.pageList) {
            size2 += page.getPageElementCount();
        }
        return size2;
    }

    private PageT getLastPage() {
        return (PageT)((AbstractPage)this.pageList.get(this.pageList.size() - 1));
    }

    List<PageT> getPages(PageT firstPage, int collectionSize) {
        int rxElementCount;
        Preconditions.checkNotNull(firstPage);
        Preconditions.checkState(collectionSize > 0);
        Integer requestPageSize = ((AbstractPage)firstPage).getContext().getPageDescriptor().extractPageSize(((AbstractPage)firstPage).getRequest());
        if (requestPageSize == null) {
            throw new ValidationException("Error while expanding Page to FixedSizeCollection: No pageSize parameter found. The pageSize parameter must be set on the request object, and must be less than the collectionSize parameter, in order to create a FixedSizeCollection object.", new Object[0]);
        }
        if (requestPageSize > collectionSize) {
            throw new ValidationException("Error while expanding Page to FixedSizeCollection: collectionSize parameter is less than the pageSize optional argument specified on the request object. collectionSize: " + collectionSize + ", pageSize: " + requestPageSize, new Object[0]);
        }
        if (((AbstractPage)firstPage).getPageElementCount() > collectionSize) {
            throw new ValidationException("Cannot construct a FixedSizeCollection with collectionSize less than the number of elements in the first page", new Object[0]);
        }
        ArrayList<PageT> pages = Lists.newArrayList();
        pages.add(firstPage);
        PageT currentPage = firstPage;
        for (int remainingCount = collectionSize - ((AbstractPage)firstPage).getPageElementCount(); remainingCount > 0 && (currentPage = ((AbstractPage)currentPage).getNextPage(remainingCount)) != null; remainingCount -= rxElementCount) {
            rxElementCount = ((AbstractPage)currentPage).getPageElementCount();
            if (rxElementCount > remainingCount) {
                throw new ValidationException("API returned a number of elements exceeding the specified page_size limit. page_size: " + collectionSize + ", elements received: " + rxElementCount, new Object[0]);
            }
            pages.add(currentPage);
        }
        return pages;
    }

    private class CollectionResourcesIterator
    extends AbstractIterator<ResourceT> {
        private final Iterator<PageT> pageIterator;
        private Iterator<ResourceT> resourceIterator;

        private CollectionResourcesIterator() {
            this.pageIterator = AbstractFixedSizeCollection.this.pageList.iterator();
            this.resourceIterator = ((AbstractPage)this.pageIterator.next()).getValues().iterator();
        }

        @Override
        protected ResourceT computeNext() {
            while (true) {
                if (this.resourceIterator.hasNext()) {
                    return this.resourceIterator.next();
                }
                if (!this.pageIterator.hasNext()) break;
                this.resourceIterator = ((AbstractPage)this.pageIterator.next()).getValues().iterator();
            }
            return this.endOfData();
        }
    }
}

