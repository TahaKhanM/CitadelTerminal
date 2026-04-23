/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.collection.IterableLike$class;
import scala.collection.mutable.DoubleLinkedListLike;
import scala.collection.mutable.LinkedListLike;
import scala.collection.mutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

public abstract class DoubleLinkedListLike$class {
    public static Seq append(DoubleLinkedListLike $this, Seq that) {
        Seq seq;
        if ($this.isEmpty()) {
            seq = that;
        } else {
            Object object;
            if (((LinkedListLike)$this.next()).isEmpty()) {
                $this.next_$eq(that);
                if (that.nonEmpty()) {
                    ((DoubleLinkedListLike)((Object)that)).prev_$eq((Seq)$this.repr());
                }
                object = BoxedUnit.UNIT;
            } else {
                object = ((DoubleLinkedListLike)$this.next()).append(that);
            }
            seq = (Seq)$this.repr();
        }
        return seq;
    }

    public static void insert(DoubleLinkedListLike $this, Seq that) {
        $this.scala$collection$mutable$DoubleLinkedListLike$$super$insert(that);
        if (that.nonEmpty()) {
            ((DoubleLinkedListLike)((Object)that)).prev_$eq((Seq)$this.repr());
        }
    }

    public static void remove(DoubleLinkedListLike $this) {
        if ($this.nonEmpty()) {
            ((DoubleLinkedListLike)$this.next()).prev_$eq($this.prev());
            if ($this.prev() != null) {
                ((LinkedListLike)$this.prev()).next_$eq($this.next());
            }
        }
    }

    private static Object atLocation(DoubleLinkedListLike $this, int n, Function1 f, Function0 onOutOfBounds) {
        Object r;
        if ($this.isEmpty()) {
            r = onOutOfBounds.apply();
        } else {
            Seq<Object> loc = (Seq)$this.repr();
            for (int left = n; left > 0; --left) {
                loc = ((LinkedListLike)((Object)loc)).next();
                BoxedUnit boxedUnit = ((LinkedListLike)((Object)loc)).isEmpty() ? onOutOfBounds.apply() : BoxedUnit.UNIT;
            }
            r = f.apply(loc);
        }
        return r;
    }

    public static Nothing$ scala$collection$mutable$DoubleLinkedListLike$$outofbounds(DoubleLinkedListLike $this, int n) {
        throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(n)).toString());
    }

    public static Seq drop(DoubleLinkedListLike $this, int n) {
        return (Seq)IterableLike$class.drop($this, n);
    }

    public static Seq tail(DoubleLinkedListLike $this) {
        return $this.drop(1);
    }

    public static Object apply(DoubleLinkedListLike $this, int n) {
        return DoubleLinkedListLike$class.atLocation($this, n, (Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final A apply(This x$1) {
                return ((LinkedListLike)x$1).elem();
            }
        }), (Function0)((Object)new Serializable($this, n){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ DoubleLinkedListLike $outer;
            private final int n$1;

            public final Nothing$ apply() {
                return DoubleLinkedListLike$class.scala$collection$mutable$DoubleLinkedListLike$$outofbounds(this.$outer, this.n$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.n$1 = n$1;
            }
        }));
    }

    public static void update(DoubleLinkedListLike $this, int n, Object x) {
        DoubleLinkedListLike$class.atLocation($this, n, (Function1)((Object)new Serializable($this, x){
            public static final long serialVersionUID = 0L;
            private final Object x$3;

            public final void apply(This x$2) {
                ((LinkedListLike)x$2).elem_$eq(this.x$3);
            }
            {
                this.x$3 = x$3;
            }
        }), (Function0)((Object)new Serializable($this, n){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ DoubleLinkedListLike $outer;
            private final int n$2;

            public final Nothing$ apply() {
                return DoubleLinkedListLike$class.scala$collection$mutable$DoubleLinkedListLike$$outofbounds(this.$outer, this.n$2);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.n$2 = n$2;
            }
        }));
    }

    public static Option get(DoubleLinkedListLike $this, int n) {
        return (Option)DoubleLinkedListLike$class.atLocation($this, n, (Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Some<A> apply(This x) {
                return new Some<A>(((LinkedListLike)x).elem());
            }
        }), (Function0)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final None$ apply() {
                return None$.MODULE$;
            }
        }));
    }

    public static void $init$(DoubleLinkedListLike $this) {
    }
}

