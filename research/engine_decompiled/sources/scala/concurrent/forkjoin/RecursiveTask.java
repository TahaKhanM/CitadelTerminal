/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.forkjoin;

import scala.concurrent.forkjoin.ForkJoinTask;

public abstract class RecursiveTask<V>
extends ForkJoinTask<V> {
    private static final long serialVersionUID = 5232453952276485270L;
    V result;

    protected abstract V compute();

    @Override
    public final V getRawResult() {
        return this.result;
    }

    @Override
    protected final void setRawResult(V value) {
        this.result = value;
    }

    @Override
    protected final boolean exec() {
        this.result = this.compute();
        return true;
    }
}

