/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Function0;
import scala.Option;
import scala.Serializable;
import scala.collection.parallel.Task;
import scala.runtime.BoxedUnit;
import scala.util.control.BreakControl;
import scala.util.control.Breaks;
import scala.util.control.Breaks$;

public abstract class Task$class {
    public static Object repr(Task $this) {
        return $this;
    }

    public static void merge(Task $this, Object that) {
    }

    public static void forwardThrowable(Task $this) {
        if ($this.throwable() == null) {
            return;
        }
        throw $this.throwable();
    }

    public static void tryLeaf(Task $this, Option lastres) {
        Object object;
        Serializable serializable = new Serializable($this, lastres){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Task $outer;
            private final Option lastres$1;

            public final void apply() {
                this.apply$mcV$sp();
            }

            public void apply$mcV$sp() {
                this.$outer.leaf(this.lastres$1);
                this.$outer.result_$eq(this.$outer.result());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.lastres$1 = lastres$1;
            }
        };
        Breaks$ breaks$ = Breaks$.MODULE$;
        Serializable serializable2 = new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ Task $outer;

            public final void apply() {
                this.$outer.signalAbort();
            }

            public void apply$mcV$sp() {
                this.$outer.signalAbort();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
        Breaks.TryBlock tryBlock = new Breaks.TryBlock<T>(breaks$, (Function0)((Object)serializable)){
            public final /* synthetic */ Breaks $outer;
            public final Function0 op$1;

            public T catchBreak(Function0<T> onBreak) {
                Object object;
                try {
                    object = this.op$1.apply();
                }
                catch (BreakControl breakControl) {
                    BreakControl breakControl2 = breakControl;
                    if (breakControl != this.$outer.scala$util$control$Breaks$$breakException()) {
                        throw breakControl2;
                    }
                    object = onBreak.apply();
                }
                return (T)object;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.op$1 = op$1;
            }
        };
        try {
            object = serializable.apply();
        }
        catch (BreakControl breakControl) {
            BreakControl breakControl2 = breakControl;
            if (breakControl != tryBlock.$outer.scala$util$control$Breaks$$breakException()) {
                $this.result_$eq($this.result());
                $this.throwable_$eq(breakControl2);
                $this.signalAbort();
            }
            try {
                serializable2.$outer.signalAbort();
                object = BoxedUnit.UNIT;
            }
            catch (Throwable throwable) {
                $this.result_$eq($this.result());
                $this.throwable_$eq(throwable);
                $this.signalAbort();
            }
        }
    }

    public static void tryMerge(Task $this, Object t) {
        Task that = (Task)t;
        if ($this.throwable() == null && that.throwable() == null) {
            $this.merge(t);
        }
        $this.mergeThrowables(that);
    }

    public static void mergeThrowables(Task $this, Task that) {
        if ($this.throwable() == null && that.throwable() != null) {
            $this.throwable_$eq(that.throwable());
        }
    }

    public static void signalAbort(Task $this) {
    }

    public static void $init$(Task $this) {
        $this.throwable_$eq(null);
    }
}

