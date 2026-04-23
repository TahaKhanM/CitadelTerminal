/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.internal.MoreThrowables;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class LogExceptionRunnable
implements Runnable {
    private static final Logger log = Logger.getLogger(LogExceptionRunnable.class.getName());
    private final Runnable task;

    public LogExceptionRunnable(Runnable task) {
        this.task = Preconditions.checkNotNull(task, "task");
    }

    @Override
    public void run() {
        try {
            this.task.run();
        }
        catch (Throwable t) {
            log.log(Level.SEVERE, "Exception while executing runnable " + this.task, t);
            MoreThrowables.throwIfUnchecked(t);
            throw new AssertionError((Object)t);
        }
    }

    public String toString() {
        return "LogExceptionRunnable(" + this.task + ")";
    }
}

