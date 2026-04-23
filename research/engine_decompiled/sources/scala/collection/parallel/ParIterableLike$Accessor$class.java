/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Serializable;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.ParIterableLike;

public abstract class ParIterableLike$Accessor$class {
    public static boolean shouldSplitFurther(ParIterableLike.Accessor $this) {
        return $this.pit().shouldSplitFurther($this.scala$collection$parallel$ParIterableLike$Accessor$$$outer().repr(), $this.scala$collection$parallel$ParIterableLike$Accessor$$$outer().tasksupport().parallelismLevel());
    }

    public static Seq split(ParIterableLike.Accessor $this) {
        return $this.pit().splitWithSignalling().map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParIterableLike.Accessor $outer;

            public final ParIterableLike.Accessor<R, Tp> apply(IterableSplitter<T> x$28) {
                return this.$outer.newSubtask(x$28);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, Seq$.MODULE$.canBuildFrom());
    }

    public static void signalAbort(ParIterableLike.Accessor $this) {
        $this.pit().abort();
    }

    public static String toString(ParIterableLike.Accessor $this) {
        return new StringBuilder().append((Object)$this.getClass().getSimpleName()).append((Object)"(").append((Object)$this.pit().toString()).append((Object)")(").append($this.result()).append((Object)")(supername: ").append((Object)$this.scala$collection$parallel$ParIterableLike$Accessor$$super$toString()).append((Object)")").toString();
    }

    public static void $init$(ParIterableLike.Accessor $this) {
    }
}

