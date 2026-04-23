/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.BetaApi;
import io.grpc.Metadata;

@BetaApi(value="The surface for response metadata is not stable yet and may change in the future.")
public interface ResponseMetadataHandler {
    public void onHeaders(Metadata var1);

    public void onTrailers(Metadata var1);
}

