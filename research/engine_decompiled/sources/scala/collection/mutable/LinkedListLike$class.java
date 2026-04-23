/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import java.util.NoSuchElementException;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.collection.mutable.Builder;
import scala.collection.mutable.LinkedListLike;
import scala.collection.mutable.Seq;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxesRunTime;

public abstract class LinkedListLike$class {
    public static boolean isEmpty(LinkedListLike $this) {
        return $this.next() == $this;
    }

    public static int length(LinkedListLike $this) {
        return LinkedListLike$class.length0($this, (Seq)$this.repr(), 0);
    }

    private static int length0(LinkedListLike $this, Seq elem, int acc) {
        while (!((LinkedListLike)((Object)elem)).isEmpty()) {
            ++acc;
            elem = ((LinkedListLike)((Object)elem)).next();
        }
        return acc;
    }

    public static Object head(LinkedListLike $this) {
        if ($this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return $this.elem();
    }

    public static Seq tail(LinkedListLike $this) {
        boolean bl = $this.nonEmpty();
        Predef$ predef$ = Predef$.MODULE$;
        if (bl) {
            return $this.next();
        }
        throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append((Object)"tail of empty list").toString());
    }

    public static Seq append(LinkedListLike $this, Seq that) {
        Seq seq;
        if ($this.isEmpty()) {
            seq = that;
        } else {
            LinkedListLike$class.loop$1($this, (Seq)$this.repr(), that);
            seq = (Seq)$this.repr();
        }
        return seq;
    }

    public static void insert(LinkedListLike $this, Seq that) {
        boolean bl = $this.nonEmpty();
        Predef$ predef$ = Predef$.MODULE$;
        if (bl) {
            if (that.nonEmpty()) {
                ((LinkedListLike)((Object)that)).append($this.next());
                $this.next_$eq(that);
            }
            return;
        }
        throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append((Object)"insert into empty list").toString());
    }

    /*
     * WARNING - void declaration
     */
    public static Seq drop(LinkedListLike $this, int n) {
        void var3_3;
        Seq<Object> these = (Seq)$this.repr();
        for (int i = 0; i < n && !((LinkedListLike)((Object)these)).isEmpty(); ++i) {
            these = ((LinkedListLike)((Object)these)).next();
        }
        return var3_3;
    }

    private static Object atLocation(LinkedListLike $this, int n, Function1 f) {
        Object loc = $this.drop(n);
        if (loc.nonEmpty()) {
            return f.apply(loc);
        }
        throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(n)).toString());
    }

    public static Object apply(LinkedListLike $this, int n) {
        return LinkedListLike$class.atLocation($this, n, (Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final A apply(This x$1) {
                return ((LinkedListLike)x$1).elem();
            }
        }));
    }

    public static void update(LinkedListLike $this, int n, Object x) {
        LinkedListLike$class.atLocation($this, n, (Function1)((Object)new Serializable($this, x){
            public static final long serialVersionUID = 0L;
            private final Object x$3;

            public final void apply(This x$2) {
                ((LinkedListLike)x$2).elem_$eq(this.x$3);
            }
            {
                this.x$3 = x$3;
            }
        }));
    }

    public static Option get(LinkedListLike $this, int n) {
        Object loc = $this.drop(n);
        return loc.nonEmpty() ? new Some(((LinkedListLike)loc).elem()) : None$.MODULE$;
    }

    public static Iterator iterator(LinkedListLike $this) {
        return new AbstractIterator<A>($this){
            private LinkedListLike<A, This> elems;

            private LinkedListLike<A, This> elems() {
                return this.elems;
            }

            private void elems_$eq(LinkedListLike<A, This> x$1) {
                this.elems = x$1;
            }

            public boolean hasNext() {
                return this.elems().nonEmpty();
            }

            /*
             * WARNING - void declaration
             */
            public A next() {
                void var1_1;
                A res = this.elems().elem();
                this.elems_$eq((LinkedListLike)this.elems().next());
                return var1_1;
            }
            {
                this.elems = $outer;
            }
        };
    }

    public static void foreach(LinkedListLike $this, Function1 f) {
        LinkedListLike these = $this;
        while (these.nonEmpty()) {
            f.apply(these.elem());
            these = (LinkedListLike)these.next();
        }
    }

    public static Seq clone(LinkedListLike $this) {
        Builder bf = $this.newBuilder();
        bf.$plus$plus$eq($this);
        return (Seq)bf.result();
    }

    private static final void loop$1(LinkedListLike $this, Seq x, Seq that$1) {
        while (true) {
            if (((LinkedListLike)((LinkedListLike)((Object)x)).next()).isEmpty()) {
                ((LinkedListLike)((Object)x)).next_$eq(that$1);
                return;
            }
            x = ((LinkedListLike)((Object)x)).next();
        }
    }

    public static void $init$(LinkedListLike $this) {
    }
}

