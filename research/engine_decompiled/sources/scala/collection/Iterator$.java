/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.collection.AbstractIterator;
import scala.collection.GenTraversable;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.mutable.ArrayBuffer;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

public final class Iterator$ {
    public static final Iterator$ MODULE$;
    private final Iterator<Nothing$> empty;

    static {
        new Iterator$();
    }

    public <A> TraversableOnce.BufferedCanBuildFrom<A, Iterator> IteratorCanBuildFrom() {
        return new TraversableOnce.BufferedCanBuildFrom<A, Iterator>(){

            public <B> Iterator<B> bufferToColl(ArrayBuffer<B> coll) {
                return coll.iterator();
            }

            public <B> Iterator<B> traversableToColl(GenTraversable<B> t) {
                return t.toIterator();
            }
        };
    }

    public Iterator<Nothing$> empty() {
        return this.empty;
    }

    public <A> Iterator<A> single(A elem) {
        return new AbstractIterator<A>(elem){
            private boolean hasnext;
            private final Object elem$1;

            private boolean hasnext() {
                return this.hasnext;
            }

            private void hasnext_$eq(boolean x$1) {
                this.hasnext = x$1;
            }

            public boolean hasNext() {
                return this.hasnext();
            }

            public A next() {
                Object object;
                if (this.hasnext()) {
                    this.hasnext_$eq(false);
                    object = this.elem$1;
                } else {
                    object = Iterator$.MODULE$.empty().next();
                }
                return (A)object;
            }
            {
                this.elem$1 = elem$1;
                this.hasnext = true;
            }
        };
    }

    public <A> Iterator<A> apply(Seq<A> elems) {
        return elems.iterator();
    }

    public <A> Iterator<A> fill(int len, Function0<A> elem) {
        return new AbstractIterator<A>(len, elem){
            private int i;
            private final int len$1;
            private final Function0 elem$2;

            private int i() {
                return this.i;
            }

            private void i_$eq(int x$1) {
                this.i = x$1;
            }

            public boolean hasNext() {
                return this.i() < this.len$1;
            }

            public A next() {
                Nothing$ nothing$;
                if (this.hasNext()) {
                    this.i_$eq(this.i() + 1);
                    nothing$ = this.elem$2.apply();
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (A)nothing$;
            }
            {
                this.len$1 = len$1;
                this.elem$2 = elem$2;
                this.i = 0;
            }
        };
    }

    public <A> Iterator<A> tabulate(int end, Function1<Object, A> f) {
        return new AbstractIterator<A>(end, f){
            private int i;
            private final int end$1;
            private final Function1 f$1;

            private int i() {
                return this.i;
            }

            private void i_$eq(int x$1) {
                this.i = x$1;
            }

            public boolean hasNext() {
                return this.i() < this.end$1;
            }

            /*
             * WARNING - void declaration
             */
            public A next() {
                Nothing$ nothing$;
                if (this.hasNext()) {
                    void var1_1;
                    R result2 = this.f$1.apply(BoxesRunTime.boxToInteger(this.i()));
                    this.i_$eq(this.i() + 1);
                    nothing$ = var1_1;
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (A)nothing$;
            }
            {
                this.end$1 = end$1;
                this.f$1 = f$1;
                this.i = 0;
            }
        };
    }

    public Iterator<Object> range(int start, int end) {
        return this.range(start, end, 1);
    }

    public Iterator<Object> range(int start, int end, int step) {
        return new AbstractIterator<Object>(start, end, step){
            private int i;
            private final int end$2;
            private final int step$1;

            private int i() {
                return this.i;
            }

            private void i_$eq(int x$1) {
                this.i = x$1;
            }

            public boolean hasNext() {
                return !(this.step$1 > 0 && this.i() >= this.end$2 || this.step$1 < 0 && this.i() <= this.end$2);
            }

            /*
             * WARNING - void declaration
             */
            public int next() {
                int n;
                if (this.hasNext()) {
                    void var1_1;
                    int result2 = this.i();
                    this.i_$eq(this.i() + this.step$1);
                    n = var1_1;
                } else {
                    n = BoxesRunTime.unboxToInt(Iterator$.MODULE$.empty().next());
                }
                return n;
            }
            {
                this.end$2 = end$2;
                this.step$1 = step$1;
                if (step$1 == 0) {
                    throw new IllegalArgumentException("zero step");
                }
                this.i = start$1;
            }
        };
    }

    public <T> Iterator<T> iterate(T start, Function1<T, T> f) {
        return new AbstractIterator<T>(start, f){
            private boolean first;
            private T acc;
            private final Function1 f$2;

            public boolean hasNext() {
                return true;
            }

            public T next() {
                if (this.first) {
                    this.first = false;
                } else {
                    this.acc = this.f$2.apply(this.acc);
                }
                return this.acc;
            }
            {
                this.f$2 = f$2;
                this.first = true;
                this.acc = start$2;
            }
        };
    }

    public Iterator<Object> from(int start) {
        return this.from(start, 1);
    }

    public Iterator<Object> from(int start, int step) {
        return new AbstractIterator<Object>(start, step){
            private int i;
            private final int step$2;

            private int i() {
                return this.i;
            }

            private void i_$eq(int x$1) {
                this.i = x$1;
            }

            public boolean hasNext() {
                return true;
            }

            /*
             * WARNING - void declaration
             */
            public int next() {
                void var1_1;
                int result2 = this.i();
                this.i_$eq(this.i() + this.step$2);
                return (int)var1_1;
            }
            {
                this.step$2 = step$2;
                this.i = start$3;
            }
        };
    }

    public <A> Iterator<A> continually(Function0<A> elem) {
        return new AbstractIterator<A>(elem){
            private final Function0 elem$3;

            public boolean hasNext() {
                return true;
            }

            public A next() {
                return (A)this.elem$3.apply();
            }
            {
                this.elem$3 = elem$3;
            }
        };
    }

    private Iterator$() {
        MODULE$ = this;
        this.empty = new AbstractIterator<Nothing$>(){

            public boolean hasNext() {
                return false;
            }

            public Nothing$ next() {
                throw new NoSuchElementException("next on empty iterator");
            }
        };
    }
}

