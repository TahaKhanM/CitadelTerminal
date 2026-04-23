/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.core;

import com.google.api.core.ApiFuture;
import com.google.api.core.InternalApi;
import com.google.common.util.concurrent.ForwardingListenableFuture;
import com.google.common.util.concurrent.ListenableFuture;

@InternalApi
public class ListenableFutureToApiFuture<V>
extends ForwardingListenableFuture.SimpleForwardingListenableFuture<V>
implements ApiFuture<V> {
    public ListenableFutureToApiFuture(ListenableFuture<V> delegate) {
        super(delegate);
    }
}

