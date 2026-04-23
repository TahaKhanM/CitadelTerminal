/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Function1;
import scala.Serializable;
import scala.collection.Seq;
import scala.collection.generic.IdleSignalling$;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.ParIterable;
import scala.collection.parallel.SeqSplitter;
import scala.collection.parallel.package$;
import scala.runtime.ObjectRef;

public abstract class IterableSplitter$class {
    /*
     * WARNING - void declaration
     */
    public static Seq splitWithSignalling(IterableSplitter $this) {
        void var1_1;
        Seq pits = $this.split();
        pits.foreach(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ IterableSplitter $outer;

            public final void apply(IterableSplitter<T> x$2) {
                x$2.signalDelegate_$eq(this.$outer.signalDelegate());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
        return var1_1;
    }

    public static boolean shouldSplitFurther(IterableSplitter $this, ParIterable coll, int parallelismLevel) {
        return $this.remaining() > package$.MODULE$.thresholdFromSize(coll.size(), parallelismLevel);
    }

    public static String buildString(IterableSplitter $this, Function1 closure) {
        ObjectRef<String> output = ObjectRef.create("");
        closure.apply(new Serializable($this, output){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ IterableSplitter $outer;
            private final ObjectRef output$1;

            public final void apply(String s2) {
                IterableSplitter$class.appendln$1(this.$outer, s2, this.output$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.output$1 = output$1;
            }
        });
        return (String)output.elem;
    }

    public static String debugInformation(IterableSplitter $this) {
        return new StringBuilder().append((Object)"Parallel iterator: ").append($this.getClass()).toString();
    }

    public static IterableSplitter.Taken newTaken(IterableSplitter $this, int until2) {
        return new IterableSplitter.Taken($this, until2);
    }

    public static IterableSplitter.Taken newSliceInternal(IterableSplitter $this, IterableSplitter.Taken it, int from1) {
        for (int count2 = from1; count2 > 0 && it.hasNext(); --count2) {
            it.next();
        }
        return it;
    }

    public static IterableSplitter take(IterableSplitter $this, int n) {
        return $this.newTaken(n);
    }

    public static IterableSplitter slice(IterableSplitter $this, int from1, int until1) {
        return $this.newSliceInternal($this.newTaken(until1), from1);
    }

    public static IterableSplitter.Mapped map(IterableSplitter $this, Function1 f) {
        return new IterableSplitter.Mapped($this, f);
    }

    public static IterableSplitter.Appended appendParIterable(IterableSplitter $this, IterableSplitter that) {
        return new IterableSplitter.Appended($this, that);
    }

    public static IterableSplitter.Zipped zipParSeq(IterableSplitter $this, SeqSplitter that) {
        return new IterableSplitter.Zipped($this, that);
    }

    public static IterableSplitter.ZippedAll zipAllParSeq(IterableSplitter $this, SeqSplitter that, Object thisElem, Object thatElem) {
        return new IterableSplitter.ZippedAll<Object, Object>($this, that, thisElem, thatElem);
    }

    public static final void appendln$1(IterableSplitter $this, String s2, ObjectRef output$1) {
        output$1.elem = new StringBuilder().append((Object)((String)output$1.elem)).append((Object)new StringBuilder().append((Object)s2).append((Object)"\n").toString()).toString();
    }

    public static void $init$(IterableSplitter $this) {
        $this.signalDelegate_$eq(IdleSignalling$.MODULE$);
    }
}

