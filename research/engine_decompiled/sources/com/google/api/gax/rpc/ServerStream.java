/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.gax.rpc.QueuingResponseObserver;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.ServerStreamIterator;
import java.util.Iterator;
import javax.annotation.Nonnull;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public class ServerStream<V>
implements Iterable<V> {
    private final QueuingResponseObserver<V> observer = new QueuingResponseObserver();
    private final ServerStreamIterator<V> iterator = new ServerStreamIterator<V>(this.observer);
    private boolean consumed;

    @InternalApi(value="For use by ServerStreamingCallable only.")
    ServerStream() {
    }

    @InternalApi(value="For use by ServerStreamingCallable only.")
    ResponseObserver<V> observer() {
        return this.observer;
    }

    @Override
    @Nonnull
    public Iterator<V> iterator() {
        if (this.consumed) {
            throw new IllegalStateException("Iterator already consumed");
        }
        this.consumed = true;
        return this.iterator;
    }

    public boolean isReceiveReady() {
        return this.iterator.isReady();
    }

    public void cancel() {
        this.observer.cancel();
    }
}

