/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
class ChannelExecutor {
    private static final Logger log = Logger.getLogger(ChannelExecutor.class.getName());
    private final Object lock = new Object();
    @GuardedBy(value="lock")
    private final Queue<Runnable> queue = new ArrayDeque<Runnable>();
    @GuardedBy(value="lock")
    private boolean draining;

    ChannelExecutor() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    final void drain() {
        boolean drainLeaseAcquired = false;
        while (true) {
            Runnable runnable;
            Object object = this.lock;
            synchronized (object) {
                if (!drainLeaseAcquired) {
                    if (this.draining) {
                        return;
                    }
                    this.draining = true;
                    drainLeaseAcquired = true;
                }
                if ((runnable = this.queue.poll()) == null) {
                    this.draining = false;
                    break;
                }
            }
            try {
                runnable.run();
            }
            catch (Throwable t) {
                this.handleUncaughtThrowable(t);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    final ChannelExecutor executeLater(Runnable runnable) {
        Object object = this.lock;
        synchronized (object) {
            this.queue.add(Preconditions.checkNotNull(runnable, "runnable is null"));
        }
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @VisibleForTesting
    final int numPendingTasks() {
        Object object = this.lock;
        synchronized (object) {
            return this.queue.size();
        }
    }

    void handleUncaughtThrowable(Throwable t) {
        log.log(Level.WARNING, "Runnable threw exception in ChannelExecutor", t);
    }
}

