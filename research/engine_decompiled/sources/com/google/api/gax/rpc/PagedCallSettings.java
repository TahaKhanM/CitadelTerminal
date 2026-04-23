/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.PagedListResponseFactory;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.UnaryCallSettings;
import java.util.Set;

@InternalExtensionOnly
public final class PagedCallSettings<RequestT, ResponseT, PagedListResponseT>
extends UnaryCallSettings<RequestT, ResponseT> {
    private final PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> pagedListResponseFactory;

    public PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> getPagedListResponseFactory() {
        return this.pagedListResponseFactory;
    }

    public static <RequestT, ResponseT, PagedListResponseT> Builder<RequestT, ResponseT, PagedListResponseT> newBuilder(PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> pagedListResponseFactory) {
        return new Builder<RequestT, ResponseT, PagedListResponseT>(pagedListResponseFactory);
    }

    public final Builder<RequestT, ResponseT, PagedListResponseT> toBuilder() {
        return new Builder(this);
    }

    private PagedCallSettings(Builder<RequestT, ResponseT, PagedListResponseT> builder) {
        super(builder);
        this.pagedListResponseFactory = ((Builder)builder).pagedListResponseFactory;
    }

    public static class Builder<RequestT, ResponseT, PagedListResponseT>
    extends UnaryCallSettings.Builder<RequestT, ResponseT> {
        private PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> pagedListResponseFactory;

        public Builder(PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> pagedListResponseFactory) {
            this.pagedListResponseFactory = pagedListResponseFactory;
        }

        public Builder(PagedCallSettings<RequestT, ResponseT, PagedListResponseT> settings) {
            super(settings);
            this.pagedListResponseFactory = ((PagedCallSettings)settings).pagedListResponseFactory;
        }

        public Builder<RequestT, ResponseT, PagedListResponseT> setRetryableCodes(Set<StatusCode.Code> retryableCodes) {
            super.setRetryableCodes(retryableCodes);
            return this;
        }

        public Builder<RequestT, ResponseT, PagedListResponseT> setRetryableCodes(StatusCode.Code ... codes) {
            super.setRetryableCodes(codes);
            return this;
        }

        public Builder<RequestT, ResponseT, PagedListResponseT> setRetrySettings(RetrySettings retrySettings) {
            super.setRetrySettings(retrySettings);
            return this;
        }

        public PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> getPagedListResponseFactory() {
            return this.pagedListResponseFactory;
        }

        public PagedCallSettings<RequestT, ResponseT, PagedListResponseT> build() {
            return new PagedCallSettings(this);
        }
    }
}

