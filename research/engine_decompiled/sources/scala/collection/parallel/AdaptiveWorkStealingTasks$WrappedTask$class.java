/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.None$;
import scala.Predef$;
import scala.Predef$any2stringadd$;
import scala.Serializable;
import scala.Some;
import scala.collection.IterableLike;
import scala.collection.Seq;
import scala.collection.SeqLike;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.AdaptiveWorkStealingTasks;
import scala.runtime.ObjectRef;

public abstract class AdaptiveWorkStealingTasks$WrappedTask$class {
    public static void compute(AdaptiveWorkStealingTasks.WrappedTask $this) {
        if ($this.body().shouldSplitFurther()) {
            $this.internal();
            $this.release();
        } else {
            $this.body().tryLeaf(None$.MODULE$);
            $this.release();
        }
    }

    public static void internal(AdaptiveWorkStealingTasks.WrappedTask $this) {
        AdaptiveWorkStealingTasks.WrappedTask last2 = $this.spawnSubtasks();
        last2.body().tryLeaf(None$.MODULE$);
        last2.release();
        $this.body().result_$eq(last2.body().result());
        $this.body().throwable_$eq(last2.body().throwable());
        while (last2.next() != null) {
            if ((last2 = last2.next()).tryCancel()) {
                last2.body().tryLeaf(new Some($this.body().result()));
                last2.release();
            } else {
                last2.sync();
            }
            $this.body().tryMerge(last2.body().repr());
        }
        return;
    }

    /*
     * WARNING - void declaration
     */
    public static AdaptiveWorkStealingTasks.WrappedTask spawnSubtasks(AdaptiveWorkStealingTasks.WrappedTask $this) {
        void var2_2;
        ObjectRef<Object> last2 = ObjectRef.create(null);
        AdaptiveWorkStealingTasks.WrappedTask head2 = $this;
        do {
            Seq subtasks = head2.split();
            head2 = (AdaptiveWorkStealingTasks.WrappedTask)subtasks.head();
            ((IterableLike)((SeqLike)subtasks.tail()).reverse()).foreach(new Serializable($this, last2){
                public static final long serialVersionUID = 0L;
                private final ObjectRef last$1;

                public final void apply(AdaptiveWorkStealingTasks.WrappedTask<R, Tp> t) {
                    t.next_$eq((AdaptiveWorkStealingTasks.WrappedTask)this.last$1.elem);
                    this.last$1.elem = t;
                    t.start();
                }
                {
                    this.last$1 = last$1;
                }
            });
        } while (head2.body().shouldSplitFurther());
        head2.next_$eq((AdaptiveWorkStealingTasks.WrappedTask)last2.elem);
        return var2_2;
    }

    public static void printChain(AdaptiveWorkStealingTasks.WrappedTask $this) {
        AdaptiveWorkStealingTasks.WrappedTask curr = $this;
        String chain2 = "chain: ";
        while (true) {
            AdaptiveWorkStealingTasks.WrappedTask wrappedTask;
            String string2;
            if (curr == null) {
                Predef$.MODULE$.println(chain2);
                return;
            }
            string2 = new StringBuilder().append((Object)string2).append((Object)Predef$any2stringadd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(wrappedTask), " ---> ")).toString();
            wrappedTask = wrappedTask.next();
        }
    }

    public static void $init$(AdaptiveWorkStealingTasks.WrappedTask $this) {
        $this.next_$eq(null);
        $this.shouldWaitFor_$eq(true);
    }
}

