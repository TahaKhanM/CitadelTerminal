/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.paging;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.InternalApi;
import com.google.api.gax.paging.AsyncPage;
import com.google.api.gax.rpc.ApiExceptions;
import com.google.api.gax.rpc.PageContext;
import com.google.common.base.Strings;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterables;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.Iterator;

public abstract class AbstractPage<RequestT, ResponseT, ResourceT, PageT extends AbstractPage<RequestT, ResponseT, ResourceT, PageT>>
implements AsyncPage<ResourceT> {
    private final PageContext<RequestT, ResponseT, ResourceT> context;
    private final ResponseT response;

    protected AbstractPage(PageContext<RequestT, ResponseT, ResourceT> context, ResponseT response) {
        this.context = context;
        this.response = response;
    }

    protected abstract PageT createPage(PageContext<RequestT, ResponseT, ResourceT> var1, ResponseT var2);

    @InternalApi(value="Visible for testing")
    public ApiFuture<PageT> createPageAsync(final PageContext<RequestT, ResponseT, ResourceT> context, ApiFuture<ResponseT> futureResponse) {
        return ApiFutures.transform(futureResponse, new ApiFunction<ResponseT, PageT>(){

            @Override
            public PageT apply(ResponseT input2) {
                return AbstractPage.this.createPage(context, input2);
            }
        }, MoreExecutors.directExecutor());
    }

    @Override
    public boolean hasNextPage() {
        return !this.getNextPageToken().equals(this.context.getPageDescriptor().emptyToken());
    }

    @Override
    public String getNextPageToken() {
        return Strings.nullToEmpty(this.context.getPageDescriptor().extractNextToken(this.response));
    }

    public PageT getNextPage() {
        return this.getNextPageImpl(null);
    }

    public PageT getNextPage(int pageSize) {
        return this.getNextPageImpl(pageSize);
    }

    @Override
    public ApiFuture<PageT> getNextPageAsync() {
        if (this.hasNextPage()) {
            RequestT request = this.context.getPageDescriptor().injectToken(this.context.getRequest(), this.getNextPageToken());
            PageContext<RequestT, ResponseT, ResourceT> nextContext = this.context.withRequest(request);
            return this.createPageAsync(nextContext, this.callApi(nextContext));
        }
        return ApiFutures.immediateFuture(null);
    }

    private PageT getNextPageImpl(Integer pageSize) {
        if (this.hasNextPage()) {
            RequestT request = this.context.getPageDescriptor().injectToken(this.context.getRequest(), this.getNextPageToken());
            if (pageSize != null) {
                request = this.context.getPageDescriptor().injectPageSize(request, pageSize);
            }
            PageContext<RequestT, ResponseT, ResourceT> nextContext = this.context.withRequest(request);
            ResponseT response = ApiExceptions.callAndTranslateApiException(this.callApi(nextContext));
            return this.createPage(nextContext, response);
        }
        return null;
    }

    private ApiFuture<ResponseT> callApi(PageContext<RequestT, ResponseT, ResourceT> nextContext) {
        return nextContext.getCallable().futureCall(nextContext.getRequest(), nextContext.getCallContext());
    }

    @Override
    public Iterable<ResourceT> iterateAll() {
        return new Iterable<ResourceT>(){

            @Override
            public Iterator<ResourceT> iterator() {
                return new AllResourcesIterator();
            }
        };
    }

    @Override
    public Iterable<ResourceT> getValues() {
        return this.context.getPageDescriptor().extractResources(this.response);
    }

    public ResponseT getResponse() {
        return this.response;
    }

    public RequestT getRequest() {
        return this.context.getRequest();
    }

    public int getPageElementCount() {
        return Iterables.size(this.context.getPageDescriptor().extractResources(this.response));
    }

    PageContext<RequestT, ResponseT, ResourceT> getContext() {
        return this.context;
    }

    private class AllResourcesIterator
    extends AbstractIterator<ResourceT> {
        private AbstractPage<RequestT, ResponseT, ResourceT, PageT> currentPage;
        private Iterator<ResourceT> currentIterator;

        private AllResourcesIterator() {
            this.currentPage = AbstractPage.this;
            this.currentIterator = this.currentPage.getValues().iterator();
        }

        @Override
        protected ResourceT computeNext() {
            while (!this.currentIterator.hasNext()) {
                this.currentPage = this.currentPage.getNextPage();
                if (this.currentPage == null) {
                    return this.endOfData();
                }
                this.currentIterator = this.currentPage.getValues().iterator();
            }
            return this.currentIterator.next();
        }
    }
}

