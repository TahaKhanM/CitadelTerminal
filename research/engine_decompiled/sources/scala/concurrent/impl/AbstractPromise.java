/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.impl;

import java.util.concurrent.atomic.AtomicReference;

@Deprecated
abstract class AbstractPromise
extends AtomicReference<Object> {
    AbstractPromise() {
    }

    protected final boolean updateState(Object oldState, Object newState) {
        return this.compareAndSet(oldState, newState);
    }

    protected final Object getState() {
        return this.get();
    }
}

