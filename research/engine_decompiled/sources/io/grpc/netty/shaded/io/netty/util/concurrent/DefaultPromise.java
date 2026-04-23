/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.util.concurrent;

import io.grpc.netty.shaded.io.netty.util.concurrent.AbstractFuture;
import io.grpc.netty.shaded.io.netty.util.concurrent.BlockingOperationException;
import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultFutureListeners;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericProgressiveFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.ProgressiveFuture;
import io.grpc.netty.shaded.io.netty.util.concurrent.Promise;
import io.grpc.netty.shaded.io.netty.util.internal.InternalThreadLocalMap;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ThrowableUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class DefaultPromise<V>
extends AbstractFuture<V>
implements Promise<V> {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(DefaultPromise.class);
    private static final InternalLogger rejectedExecutionLogger = InternalLoggerFactory.getInstance(DefaultPromise.class.getName() + ".rejectedExecution");
    private static final int MAX_LISTENER_STACK_DEPTH = Math.min(8, SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.defaultPromise.maxListenerStackDepth", 8));
    private static final AtomicReferenceFieldUpdater<DefaultPromise, Object> RESULT_UPDATER = AtomicReferenceFieldUpdater.newUpdater(DefaultPromise.class, Object.class, "result");
    private static final Object SUCCESS = new Object();
    private static final Object UNCANCELLABLE = new Object();
    private static final CauseHolder CANCELLATION_CAUSE_HOLDER = new CauseHolder(ThrowableUtil.unknownStackTrace(new CancellationException(), DefaultPromise.class, "cancel(...)"));
    private volatile Object result;
    private final EventExecutor executor;
    private Object listeners;
    private short waiters;
    private boolean notifyingListeners;

    public DefaultPromise(EventExecutor executor) {
        this.executor = ObjectUtil.checkNotNull(executor, "executor");
    }

    protected DefaultPromise() {
        this.executor = null;
    }

    @Override
    public Promise<V> setSuccess(V result2) {
        if (this.setSuccess0(result2)) {
            this.notifyListeners();
            return this;
        }
        throw new IllegalStateException("complete already: " + this);
    }

    @Override
    public boolean trySuccess(V result2) {
        if (this.setSuccess0(result2)) {
            this.notifyListeners();
            return true;
        }
        return false;
    }

    @Override
    public Promise<V> setFailure(Throwable cause) {
        if (this.setFailure0(cause)) {
            this.notifyListeners();
            return this;
        }
        throw new IllegalStateException("complete already: " + this, cause);
    }

    @Override
    public boolean tryFailure(Throwable cause) {
        if (this.setFailure0(cause)) {
            this.notifyListeners();
            return true;
        }
        return false;
    }

    @Override
    public boolean setUncancellable() {
        if (RESULT_UPDATER.compareAndSet(this, null, UNCANCELLABLE)) {
            return true;
        }
        Object result2 = this.result;
        return !DefaultPromise.isDone0(result2) || !DefaultPromise.isCancelled0(result2);
    }

    @Override
    public boolean isSuccess() {
        Object result2 = this.result;
        return result2 != null && result2 != UNCANCELLABLE && !(result2 instanceof CauseHolder);
    }

    @Override
    public boolean isCancellable() {
        return this.result == null;
    }

    @Override
    public Throwable cause() {
        Object result2 = this.result;
        return result2 instanceof CauseHolder ? ((CauseHolder)result2).cause : null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Promise<V> addListener(GenericFutureListener<? extends Future<? super V>> listener) {
        ObjectUtil.checkNotNull(listener, "listener");
        DefaultPromise defaultPromise = this;
        synchronized (defaultPromise) {
            this.addListener0(listener);
        }
        if (this.isDone()) {
            this.notifyListeners();
        }
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Promise<V> addListeners(GenericFutureListener<? extends Future<? super V>> ... listeners) {
        ObjectUtil.checkNotNull(listeners, "listeners");
        DefaultPromise defaultPromise = this;
        synchronized (defaultPromise) {
            for (GenericFutureListener<? extends Future<? super V>> listener : listeners) {
                if (listener == null) break;
                this.addListener0(listener);
            }
        }
        if (this.isDone()) {
            this.notifyListeners();
        }
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Promise<V> removeListener(GenericFutureListener<? extends Future<? super V>> listener) {
        ObjectUtil.checkNotNull(listener, "listener");
        DefaultPromise defaultPromise = this;
        synchronized (defaultPromise) {
            this.removeListener0(listener);
        }
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Promise<V> removeListeners(GenericFutureListener<? extends Future<? super V>> ... listeners) {
        ObjectUtil.checkNotNull(listeners, "listeners");
        DefaultPromise defaultPromise = this;
        synchronized (defaultPromise) {
            for (GenericFutureListener<? extends Future<? super V>> listener : listeners) {
                if (listener == null) break;
                this.removeListener0(listener);
            }
        }
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Promise<V> await() throws InterruptedException {
        if (this.isDone()) {
            return this;
        }
        if (Thread.interrupted()) {
            throw new InterruptedException(this.toString());
        }
        this.checkDeadLock();
        DefaultPromise defaultPromise = this;
        synchronized (defaultPromise) {
            while (!this.isDone()) {
                this.incWaiters();
                try {
                    this.wait();
                }
                finally {
                    this.decWaiters();
                }
            }
        }
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Promise<V> awaitUninterruptibly() {
        if (this.isDone()) {
            return this;
        }
        this.checkDeadLock();
        boolean interrupted = false;
        DefaultPromise defaultPromise = this;
        synchronized (defaultPromise) {
            while (!this.isDone()) {
                this.incWaiters();
                try {
                    this.wait();
                }
                catch (InterruptedException e) {
                    interrupted = true;
                }
                finally {
                    this.decWaiters();
                }
            }
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
        return this;
    }

    @Override
    public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
        return this.await0(unit.toNanos(timeout), true);
    }

    @Override
    public boolean await(long timeoutMillis) throws InterruptedException {
        return this.await0(TimeUnit.MILLISECONDS.toNanos(timeoutMillis), true);
    }

    @Override
    public boolean awaitUninterruptibly(long timeout, TimeUnit unit) {
        try {
            return this.await0(unit.toNanos(timeout), false);
        }
        catch (InterruptedException e) {
            throw new InternalError();
        }
    }

    @Override
    public boolean awaitUninterruptibly(long timeoutMillis) {
        try {
            return this.await0(TimeUnit.MILLISECONDS.toNanos(timeoutMillis), false);
        }
        catch (InterruptedException e) {
            throw new InternalError();
        }
    }

    @Override
    public V getNow() {
        Object result2 = this.result;
        if (result2 instanceof CauseHolder || result2 == SUCCESS) {
            return null;
        }
        return (V)result2;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        if (RESULT_UPDATER.compareAndSet(this, null, CANCELLATION_CAUSE_HOLDER)) {
            this.checkNotifyWaiters();
            this.notifyListeners();
            return true;
        }
        return false;
    }

    @Override
    public boolean isCancelled() {
        return DefaultPromise.isCancelled0(this.result);
    }

    @Override
    public boolean isDone() {
        return DefaultPromise.isDone0(this.result);
    }

    @Override
    public Promise<V> sync() throws InterruptedException {
        this.await();
        this.rethrowIfFailed();
        return this;
    }

    @Override
    public Promise<V> syncUninterruptibly() {
        this.awaitUninterruptibly();
        this.rethrowIfFailed();
        return this;
    }

    public String toString() {
        return this.toStringBuilder().toString();
    }

    protected StringBuilder toStringBuilder() {
        StringBuilder buf = new StringBuilder(64).append(StringUtil.simpleClassName(this)).append('@').append(Integer.toHexString(this.hashCode()));
        Object result2 = this.result;
        if (result2 == SUCCESS) {
            buf.append("(success)");
        } else if (result2 == UNCANCELLABLE) {
            buf.append("(uncancellable)");
        } else if (result2 instanceof CauseHolder) {
            buf.append("(failure: ").append(((CauseHolder)result2).cause).append(')');
        } else if (result2 != null) {
            buf.append("(success: ").append(result2).append(')');
        } else {
            buf.append("(incomplete)");
        }
        return buf;
    }

    protected EventExecutor executor() {
        return this.executor;
    }

    protected void checkDeadLock() {
        EventExecutor e = this.executor();
        if (e != null && e.inEventLoop()) {
            throw new BlockingOperationException(this.toString());
        }
    }

    protected static void notifyListener(EventExecutor eventExecutor, Future<?> future, GenericFutureListener<?> listener) {
        ObjectUtil.checkNotNull(eventExecutor, "eventExecutor");
        ObjectUtil.checkNotNull(future, "future");
        ObjectUtil.checkNotNull(listener, "listener");
        DefaultPromise.notifyListenerWithStackOverFlowProtection(eventExecutor, future, listener);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void notifyListeners() {
        InternalThreadLocalMap threadLocals;
        int stackDepth;
        EventExecutor executor = this.executor();
        if (executor.inEventLoop() && (stackDepth = (threadLocals = InternalThreadLocalMap.get()).futureListenerStackDepth()) < MAX_LISTENER_STACK_DEPTH) {
            threadLocals.setFutureListenerStackDepth(stackDepth + 1);
            try {
                this.notifyListenersNow();
            }
            finally {
                threadLocals.setFutureListenerStackDepth(stackDepth);
            }
            return;
        }
        DefaultPromise.safeExecute(executor, new Runnable(){

            @Override
            public void run() {
                DefaultPromise.this.notifyListenersNow();
            }
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void notifyListenerWithStackOverFlowProtection(EventExecutor executor, final Future<?> future, final GenericFutureListener<?> listener) {
        InternalThreadLocalMap threadLocals;
        int stackDepth;
        if (executor.inEventLoop() && (stackDepth = (threadLocals = InternalThreadLocalMap.get()).futureListenerStackDepth()) < MAX_LISTENER_STACK_DEPTH) {
            threadLocals.setFutureListenerStackDepth(stackDepth + 1);
            try {
                DefaultPromise.notifyListener0(future, listener);
            }
            finally {
                threadLocals.setFutureListenerStackDepth(stackDepth);
            }
            return;
        }
        DefaultPromise.safeExecute(executor, new Runnable(){

            @Override
            public void run() {
                DefaultPromise.notifyListener0(future, listener);
            }
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void notifyListenersNow() {
        Object listeners;
        DefaultPromise defaultPromise = this;
        synchronized (defaultPromise) {
            if (this.notifyingListeners || this.listeners == null) {
                return;
            }
            this.notifyingListeners = true;
            listeners = this.listeners;
            this.listeners = null;
        }
        while (true) {
            if (listeners instanceof DefaultFutureListeners) {
                this.notifyListeners0((DefaultFutureListeners)listeners);
            } else {
                DefaultPromise.notifyListener0(this, (GenericFutureListener)listeners);
            }
            defaultPromise = this;
            synchronized (defaultPromise) {
                if (this.listeners == null) {
                    this.notifyingListeners = false;
                    return;
                }
                listeners = this.listeners;
                this.listeners = null;
            }
        }
    }

    private void notifyListeners0(DefaultFutureListeners listeners) {
        GenericFutureListener<? extends Future<?>>[] a = listeners.listeners();
        int size2 = listeners.size();
        for (int i = 0; i < size2; ++i) {
            DefaultPromise.notifyListener0(this, a[i]);
        }
    }

    private static void notifyListener0(Future future, GenericFutureListener l) {
        try {
            l.operationComplete(future);
        }
        catch (Throwable t) {
            logger.warn("An exception was thrown by " + l.getClass().getName() + ".operationComplete()", t);
        }
    }

    private void addListener0(GenericFutureListener<? extends Future<? super V>> listener) {
        if (this.listeners == null) {
            this.listeners = listener;
        } else if (this.listeners instanceof DefaultFutureListeners) {
            ((DefaultFutureListeners)this.listeners).add(listener);
        } else {
            this.listeners = new DefaultFutureListeners((GenericFutureListener)this.listeners, listener);
        }
    }

    private void removeListener0(GenericFutureListener<? extends Future<? super V>> listener) {
        if (this.listeners instanceof DefaultFutureListeners) {
            ((DefaultFutureListeners)this.listeners).remove(listener);
        } else if (this.listeners == listener) {
            this.listeners = null;
        }
    }

    private boolean setSuccess0(V result2) {
        return this.setValue0(result2 == null ? SUCCESS : result2);
    }

    private boolean setFailure0(Throwable cause) {
        return this.setValue0(new CauseHolder(ObjectUtil.checkNotNull(cause, "cause")));
    }

    private boolean setValue0(Object objResult) {
        if (RESULT_UPDATER.compareAndSet(this, null, objResult) || RESULT_UPDATER.compareAndSet(this, UNCANCELLABLE, objResult)) {
            this.checkNotifyWaiters();
            return true;
        }
        return false;
    }

    private synchronized void checkNotifyWaiters() {
        if (this.waiters > 0) {
            this.notifyAll();
        }
    }

    private void incWaiters() {
        if (this.waiters == Short.MAX_VALUE) {
            throw new IllegalStateException("too many waiters: " + this);
        }
        this.waiters = (short)(this.waiters + 1);
    }

    private void decWaiters() {
        this.waiters = (short)(this.waiters - 1);
    }

    private void rethrowIfFailed() {
        Throwable cause = this.cause();
        if (cause == null) {
            return;
        }
        PlatformDependent.throwException(cause);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean await0(long timeoutNanos, boolean interruptable) throws InterruptedException {
        if (this.isDone()) {
            return true;
        }
        if (timeoutNanos <= 0L) {
            return this.isDone();
        }
        if (interruptable && Thread.interrupted()) {
            throw new InterruptedException(this.toString());
        }
        this.checkDeadLock();
        long startTime = System.nanoTime();
        long waitTime = timeoutNanos;
        boolean interrupted = false;
        try {
            do {
                DefaultPromise defaultPromise = this;
                synchronized (defaultPromise) {
                    block20: {
                        if (!this.isDone()) break block20;
                        boolean bl = true;
                        return bl;
                    }
                    this.incWaiters();
                    try {
                        this.wait(waitTime / 1000000L, (int)(waitTime % 1000000L));
                    }
                    catch (InterruptedException e) {
                        if (interruptable) {
                            throw e;
                        }
                        interrupted = true;
                    }
                    finally {
                        this.decWaiters();
                    }
                }
                if (!this.isDone()) continue;
                boolean bl = true;
                return bl;
            } while ((waitTime = timeoutNanos - (System.nanoTime() - startTime)) > 0L);
            boolean bl = this.isDone();
            return bl;
        }
        finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    void notifyProgressiveListeners(final long progress, final long total2) {
        Object listeners = this.progressiveListeners();
        if (listeners == null) {
            return;
        }
        final ProgressiveFuture self = (ProgressiveFuture)((Object)this);
        EventExecutor executor = this.executor();
        if (executor.inEventLoop()) {
            if (listeners instanceof GenericProgressiveFutureListener[]) {
                DefaultPromise.notifyProgressiveListeners0(self, (GenericProgressiveFutureListener[])listeners, progress, total2);
            } else {
                DefaultPromise.notifyProgressiveListener0(self, (GenericProgressiveFutureListener)listeners, progress, total2);
            }
        } else if (listeners instanceof GenericProgressiveFutureListener[]) {
            final GenericProgressiveFutureListener[] array = (GenericProgressiveFutureListener[])listeners;
            DefaultPromise.safeExecute(executor, new Runnable(){

                @Override
                public void run() {
                    DefaultPromise.notifyProgressiveListeners0(self, array, progress, total2);
                }
            });
        } else {
            final GenericProgressiveFutureListener l = (GenericProgressiveFutureListener)listeners;
            DefaultPromise.safeExecute(executor, new Runnable(){

                @Override
                public void run() {
                    DefaultPromise.notifyProgressiveListener0(self, l, progress, total2);
                }
            });
        }
    }

    /*
     * WARNING - void declaration
     */
    private synchronized Object progressiveListeners() {
        Object listeners = this.listeners;
        if (listeners == null) {
            return null;
        }
        if (listeners instanceof DefaultFutureListeners) {
            void var7_12;
            DefaultFutureListeners dfl = (DefaultFutureListeners)listeners;
            int progressiveSize = dfl.progressiveSize();
            switch (progressiveSize) {
                case 0: {
                    return null;
                }
                case 1: {
                    for (GenericFutureListener<Future<?>> genericFutureListener : dfl.listeners()) {
                        if (!(genericFutureListener instanceof GenericProgressiveFutureListener)) continue;
                        return genericFutureListener;
                    }
                    return null;
                }
            }
            GenericFutureListener<? extends Future<?>>[] array = dfl.listeners();
            GenericProgressiveFutureListener[] copy2 = new GenericProgressiveFutureListener[progressiveSize];
            int i = 0;
            boolean bl = false;
            while (var7_12 < progressiveSize) {
                GenericFutureListener<Future<?>> l = array[i];
                if (l instanceof GenericProgressiveFutureListener) {
                    copy2[++var7_12] = (GenericProgressiveFutureListener)l;
                }
                ++i;
            }
            return copy2;
        }
        if (listeners instanceof GenericProgressiveFutureListener) {
            return listeners;
        }
        return null;
    }

    private static void notifyProgressiveListeners0(ProgressiveFuture<?> future, GenericProgressiveFutureListener<?>[] listeners, long progress, long total2) {
        for (GenericProgressiveFutureListener<?> l : listeners) {
            if (l == null) break;
            DefaultPromise.notifyProgressiveListener0(future, l, progress, total2);
        }
    }

    private static void notifyProgressiveListener0(ProgressiveFuture future, GenericProgressiveFutureListener l, long progress, long total2) {
        try {
            l.operationProgressed(future, progress, total2);
        }
        catch (Throwable t) {
            logger.warn("An exception was thrown by " + l.getClass().getName() + ".operationProgressed()", t);
        }
    }

    private static boolean isCancelled0(Object result2) {
        return result2 instanceof CauseHolder && ((CauseHolder)result2).cause instanceof CancellationException;
    }

    private static boolean isDone0(Object result2) {
        return result2 != null && result2 != UNCANCELLABLE;
    }

    private static void safeExecute(EventExecutor executor, Runnable task) {
        try {
            executor.execute(task);
        }
        catch (Throwable t) {
            rejectedExecutionLogger.error("Failed to submit a listener notification task. Event loop shut down?", t);
        }
    }

    private static final class CauseHolder {
        final Throwable cause;

        CauseHolder(Throwable cause) {
            this.cause = cause;
        }
    }
}

