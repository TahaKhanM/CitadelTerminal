/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import java.util.HashSet;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
abstract class InUseStateAggregator<T> {
    private final HashSet<T> inUseObjects = new HashSet();

    InUseStateAggregator() {
    }

    final void updateObjectInUse(T object, boolean inUse) {
        int origSize = this.inUseObjects.size();
        if (inUse) {
            this.inUseObjects.add(object);
            if (origSize == 0) {
                this.handleInUse();
            }
        } else {
            boolean removed = this.inUseObjects.remove(object);
            if (removed && origSize == 1) {
                this.handleNotInUse();
            }
        }
    }

    final boolean isInUse() {
        return !this.inUseObjects.isEmpty();
    }

    abstract void handleInUse();

    abstract void handleNotInUse();
}

