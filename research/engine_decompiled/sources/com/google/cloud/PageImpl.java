/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.InternalApi;
import com.google.api.gax.paging.Page;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableMap;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

@InternalApi
public class PageImpl<T>
implements Page<T>,
Serializable {
    private static final long serialVersionUID = 3914827379823557934L;
    private final String cursor;
    private final Iterable<T> results;
    private final NextPageFetcher<T> pageFetcher;

    public PageImpl(NextPageFetcher<T> pageFetcher, String cursor, Iterable<T> results) {
        this.pageFetcher = pageFetcher;
        this.cursor = cursor;
        this.results = results;
    }

    @Override
    public Iterable<T> getValues() {
        return this.results == null ? Collections.emptyList() : this.results;
    }

    @Override
    public Iterable<T> iterateAll() {
        return new Iterable<T>(){

            @Override
            public Iterator<T> iterator() {
                return new PageIterator(PageImpl.this);
            }
        };
    }

    @Override
    public boolean hasNextPage() {
        return this.getNextPageToken() != null && !this.getNextPageToken().equals("");
    }

    @Override
    public String getNextPageToken() {
        return this.cursor;
    }

    @Override
    public Page<T> getNextPage() {
        if (this.cursor == null || this.pageFetcher == null) {
            return null;
        }
        return this.pageFetcher.getNextPage();
    }

    public int hashCode() {
        return Objects.hash(this.cursor, this.results);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PageImpl)) {
            return false;
        }
        PageImpl other = (PageImpl)obj;
        return Objects.equals(this.cursor, other.cursor) && Objects.equals(this.results, other.results);
    }

    public static <T> Map<T, Object> nextRequestOptions(T pageTokenOption, String cursor, Map<T, ?> optionMap) {
        ImmutableMap.Builder<T, String> builder = ImmutableMap.builder();
        if (cursor != null) {
            builder.put(pageTokenOption, cursor);
        }
        for (Map.Entry<T, ?> option : optionMap.entrySet()) {
            if (Objects.equals(option.getKey(), pageTokenOption)) continue;
            builder.put(option.getKey(), (String)option.getValue());
        }
        return builder.build();
    }

    static class PageIterator<T>
    extends AbstractIterator<T> {
        private Iterator<T> currentPageIterator;
        private Page<T> currentPage;

        PageIterator(Page<T> currentPage) {
            this.currentPageIterator = currentPage.getValues().iterator();
            this.currentPage = currentPage;
        }

        @Override
        protected T computeNext() {
            while (!this.currentPageIterator.hasNext()) {
                this.currentPage = this.currentPage.getNextPage();
                if (this.currentPage == null) {
                    return this.endOfData();
                }
                this.currentPageIterator = this.currentPage.getValues().iterator();
            }
            return this.currentPageIterator.next();
        }
    }

    public static interface NextPageFetcher<T>
    extends Serializable {
        public Page<T> getNextPage();
    }
}

