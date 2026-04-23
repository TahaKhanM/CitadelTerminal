/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ObservableBuffer;
import scala.collection.mutable.Undoable;
import scala.collection.script.End$;
import scala.collection.script.Include;
import scala.collection.script.Index;
import scala.collection.script.Script;
import scala.collection.script.Start$;
import scala.runtime.IntRef;

public abstract class ObservableBuffer$class {
    public static ObservableBuffer $plus$eq(ObservableBuffer $this, Object element) {
        $this.scala$collection$mutable$ObservableBuffer$$super$$plus$eq(element);
        $this.publish(new Undoable($this, element){
            private final /* synthetic */ ObservableBuffer $outer;

            public void undo() {
                this.$outer.trimEnd(1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super(End$.MODULE$, element$1);
            }
        });
        return $this;
    }

    public static ObservableBuffer $plus$plus$eq(ObservableBuffer $this, TraversableOnce xs) {
        xs.foreach(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ObservableBuffer $outer;

            public final ObservableBuffer<A> apply(A x) {
                return this.$outer.$plus$eq(x);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
        return $this;
    }

    public static ObservableBuffer $plus$eq$colon(ObservableBuffer $this, Object element) {
        $this.scala$collection$mutable$ObservableBuffer$$super$$plus$eq$colon(element);
        $this.publish(new Undoable($this, element){
            private final /* synthetic */ ObservableBuffer $outer;

            public void undo() {
                this.$outer.trimStart(1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super(Start$.MODULE$, element$2);
            }
        });
        return $this;
    }

    public static void update(ObservableBuffer $this, int n, Object newelement) {
        Object oldelement = $this.apply(n);
        $this.scala$collection$mutable$ObservableBuffer$$super$update(n, newelement);
        $this.publish(new Undoable($this, oldelement, n, newelement){
            private final /* synthetic */ ObservableBuffer $outer;
            private final Object oldelement$1;
            private final int n$1;

            public void undo() {
                this.$outer.update(this.n$1, this.oldelement$1);
            }
            {
                void var4_4;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.oldelement$1 = oldelement$1;
                this.n$1 = n$1;
                super(new Index(n$1), var4_4);
            }
        });
    }

    /*
     * WARNING - void declaration
     */
    public static Object remove(ObservableBuffer $this, int n) {
        void var2_2;
        Object oldelement = $this.apply(n);
        $this.scala$collection$mutable$ObservableBuffer$$super$remove(n);
        $this.publish(new Undoable($this, oldelement, n){
            private final /* synthetic */ ObservableBuffer $outer;
            private final Object oldelement$2;
            private final int n$2;

            public void undo() {
                this.$outer.insert(this.n$2, Predef$.MODULE$.genericWrapArray(new Object[]{this.oldelement$2}));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.oldelement$2 = oldelement$2;
                this.n$2 = n$2;
                super(new Index(n$2), oldelement$2);
            }
        });
        return var2_2;
    }

    public static void clear(ObservableBuffer $this) {
        $this.scala$collection$mutable$ObservableBuffer$$super$clear();
        $this.publish(new Undoable($this){

            public void undo() {
                throw new UnsupportedOperationException("cannot undo");
            }
        });
    }

    public static void insertAll(ObservableBuffer $this, int n, Traversable elems) {
        $this.scala$collection$mutable$ObservableBuffer$$super$insertAll(n, elems);
        IntRef curr = IntRef.create(n - 1);
        Script msg = elems.foldLeft(new Undoable($this){

            public void undo() {
                throw new UnsupportedOperationException("cannot undo");
            }
        }, new Serializable($this, curr){
            public static final long serialVersionUID = 0L;
            private final IntRef curr$1;

            public final Script<A> apply(Script<A> x0$1, A x1$1) {
                Tuple2<Script<A>, A> tuple2 = new Tuple2<Script<A>, A>(x0$1, x1$1);
                ++this.curr$1.elem;
                return (Script)((ArrayBuffer)tuple2._1()).$plus$eq(new Include<A>(new Index(this.curr$1.elem), tuple2._2()));
            }
            {
                this.curr$1 = curr$1;
            }
        });
        $this.publish(msg);
    }

    public static void $init$(ObservableBuffer $this) {
    }
}

