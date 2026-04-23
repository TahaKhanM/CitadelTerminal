/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.gax.rpc.QueuingResponseObserver;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class ServerStreamIterator<V>
implements Iterator<V> {
    private final QueuingResponseObserver<V> observer;
    private Object last;

    ServerStreamIterator(QueuingResponseObserver<V> observer) {
        this.observer = observer;
    }

    boolean isReady() {
        return this.last != null || this.observer.isReady();
    }

    @Override
    public V next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            Object tmp;
            this.observer.request();
            Object object = tmp = this.last;
            return (V)object;
        }
        finally {
            this.last = null;
        }
    }

    @Override
    public boolean hasNext() {
        if (this.last == null) {
            try {
                this.last = this.observer.getNext();
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        if (this.last instanceof Throwable) {
            Throwable throwable = (Throwable)this.last;
            throw new RuntimeException(throwable);
        }
        return this.last != QueuingResponseObserver.EOF_MARKER;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

