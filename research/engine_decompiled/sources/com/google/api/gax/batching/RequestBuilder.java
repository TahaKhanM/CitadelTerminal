/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.core.BetaApi;

@BetaApi(value="The surface for batching is not stable yet and may change in the future.")
public interface RequestBuilder<RequestT> {
    public void appendRequest(RequestT var1);

    public RequestT build();
}

