/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.core.BetaApi;
import com.google.api.gax.retrying.StreamResumptionStrategy;
import com.google.common.base.Preconditions;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public final class SimpleStreamResumptionStrategy<RequestT, ResponseT>
implements StreamResumptionStrategy<RequestT, ResponseT> {
    private boolean seenFirstResponse;

    @Override
    public StreamResumptionStrategy<RequestT, ResponseT> createNew() {
        return new SimpleStreamResumptionStrategy<RequestT, ResponseT>();
    }

    @Override
    public ResponseT processResponse(ResponseT response) {
        this.seenFirstResponse = true;
        return response;
    }

    @Override
    public RequestT getResumeRequest(RequestT originalRequest) {
        Preconditions.checkState(!this.seenFirstResponse, "Tried to resume an unresumeable stream.");
        return originalRequest;
    }

    @Override
    public boolean canResume() {
        return !this.seenFirstResponse;
    }
}

