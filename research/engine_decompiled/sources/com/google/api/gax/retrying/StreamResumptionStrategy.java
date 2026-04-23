/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.core.BetaApi;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public interface StreamResumptionStrategy<RequestT, ResponseT> {
    @Nonnull
    public StreamResumptionStrategy<RequestT, ResponseT> createNew();

    @Nonnull
    public ResponseT processResponse(ResponseT var1);

    @Nullable
    public RequestT getResumeRequest(RequestT var1);

    public boolean canResume();
}

