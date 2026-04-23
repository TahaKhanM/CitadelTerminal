/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.forkjoin;

import scala.concurrent.forkjoin.ForkJoinTask;
import sun.misc.Unsafe;

abstract class CountedCompleter<T>
extends ForkJoinTask<T> {
    private static final long serialVersionUID = 5232453752276485070L;
    final CountedCompleter<?> completer;
    volatile int pending;
    private static final Unsafe U;
    private static final long PENDING;

    protected CountedCompleter(CountedCompleter<?> completer, int initialPendingCount) {
        this.completer = completer;
        this.pending = initialPendingCount;
    }

    protected CountedCompleter(CountedCompleter<?> completer) {
        this.completer = completer;
    }

    protected CountedCompleter() {
        this.completer = null;
    }

    public abstract void compute();

    public void onCompletion(CountedCompleter<?> caller) {
    }

    public boolean onExceptionalCompletion(Throwable ex, CountedCompleter<?> caller) {
        return true;
    }

    public final CountedCompleter<?> getCompleter() {
        return this.completer;
    }

    public final int getPendingCount() {
        return this.pending;
    }

    public final void setPendingCount(int count2) {
        this.pending = count2;
    }

    public final void addToPendingCount(int delta) {
        int c;
        while (!U.compareAndSwapInt(this, PENDING, c = this.pending, c + delta)) {
        }
    }

    public final boolean compareAndSetPendingCount(int expected, int count2) {
        return U.compareAndSwapInt(this, PENDING, expected, count2);
    }

    public final int decrementPendingCountUnlessZero() {
        int c;
        while ((c = this.pending) != 0 && !U.compareAndSwapInt(this, PENDING, c, c - 1)) {
        }
        return c;
    }

    public final CountedCompleter<?> getRoot() {
        CountedCompleter<?> p;
        CountedCompleter<?> a = this;
        while ((p = a.completer) != null) {
            a = p;
        }
        return a;
    }

    public final void tryComplete() {
        CountedCompleter<?> a;
        CountedCompleter<?> s2 = a = this;
        while (true) {
            int c;
            if ((c = a.pending) == 0) {
                a.onCompletion(s2);
                s2 = a;
                a = s2.completer;
                if (a != null) continue;
                s2.quietlyComplete();
                return;
            }
            if (U.compareAndSwapInt(a, PENDING, c, c - 1)) break;
        }
    }

    public final void propagateCompletion() {
        CountedCompleter<?> a;
        CountedCompleter<?> s2 = a = this;
        while (true) {
            int c;
            if ((c = a.pending) == 0) {
                s2 = a;
                a = s2.completer;
                if (a != null) continue;
                s2.quietlyComplete();
                return;
            }
            if (U.compareAndSwapInt(a, PENDING, c, c - 1)) break;
        }
    }

    @Override
    public void complete(T rawResult) {
        this.setRawResult(rawResult);
        this.onCompletion(this);
        this.quietlyComplete();
        CountedCompleter<?> p = this.completer;
        if (p != null) {
            p.tryComplete();
        }
    }

    public final CountedCompleter<?> firstComplete() {
        int c;
        do {
            if ((c = this.pending) != 0) continue;
            return this;
        } while (!U.compareAndSwapInt(this, PENDING, c, c - 1));
        return null;
    }

    public final CountedCompleter<?> nextComplete() {
        CountedCompleter<?> p = this.completer;
        if (p != null) {
            return p.firstComplete();
        }
        this.quietlyComplete();
        return null;
    }

    public final void quietlyCompleteRoot() {
        CountedCompleter<?> a = this;
        while (true) {
            CountedCompleter<?> p;
            if ((p = a.completer) == null) {
                a.quietlyComplete();
                return;
            }
            a = p;
        }
    }

    @Override
    void internalPropagateException(Throwable ex) {
        CountedCompleter<?> a;
        CountedCompleter<?> s2 = a = this;
        while (a.onExceptionalCompletion(ex, s2)) {
            s2 = a;
            a = s2.completer;
            if (a == null || a.status < 0) break;
            a.recordExceptionalCompletion(ex);
        }
    }

    @Override
    protected final boolean exec() {
        this.compute();
        return false;
    }

    @Override
    public T getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(T t) {
    }

    private static Unsafe getUnsafe() {
        return scala.concurrent.util.Unsafe.instance;
    }

    static {
        try {
            U = CountedCompleter.getUnsafe();
            PENDING = U.objectFieldOffset(CountedCompleter.class.getDeclaredField("pending"));
        }
        catch (Exception e) {
            throw new Error(e);
        }
    }
}

