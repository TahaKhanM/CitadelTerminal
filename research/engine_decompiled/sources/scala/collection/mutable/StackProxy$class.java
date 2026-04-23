/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Proxy$class;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.collection.mutable.Stack;
import scala.collection.mutable.StackProxy;

public abstract class StackProxy$class {
    public static Object apply(StackProxy $this, int n) {
        return $this.self().apply(n);
    }

    public static int length(StackProxy $this) {
        return $this.self().length();
    }

    public static boolean isEmpty(StackProxy $this) {
        return $this.self().isEmpty();
    }

    public static StackProxy $plus$eq(StackProxy $this, Object elem) {
        $this.self().push(elem);
        return $this;
    }

    public static StackProxy pushAll(StackProxy $this, TraversableOnce xs) {
        $this.self().pushAll(xs);
        return $this;
    }

    public static StackProxy push(StackProxy $this, Object elem1, Object elem2, Seq elems) {
        $this.self().push(elem1).push(elem2).pushAll(elems);
        return $this;
    }

    public static StackProxy push(StackProxy $this, Object elem) {
        $this.self().push(elem);
        return $this;
    }

    public static Object top(StackProxy $this) {
        return $this.self().top();
    }

    public static Object pop(StackProxy $this) {
        return $this.self().pop();
    }

    public static void clear(StackProxy $this) {
        $this.self().clear();
    }

    public static Iterator iterator(StackProxy $this) {
        return $this.self().iterator();
    }

    public static List toList(StackProxy $this) {
        return $this.self().toList();
    }

    public static Stack clone(StackProxy $this) {
        return new StackProxy<A>($this){
            private final /* synthetic */ StackProxy $outer;

            public A apply(int n) {
                return (A)StackProxy$class.apply(this, n);
            }

            public int length() {
                return StackProxy$class.length(this);
            }

            public boolean isEmpty() {
                return StackProxy$class.isEmpty(this);
            }

            public StackProxy<A> $plus$eq(A elem) {
                return StackProxy$class.$plus$eq(this, elem);
            }

            public StackProxy<A> pushAll(TraversableOnce<A> xs) {
                return StackProxy$class.pushAll(this, xs);
            }

            public StackProxy<A> push(A elem1, A elem2, Seq<A> elems) {
                return StackProxy$class.push(this, elem1, elem2, elems);
            }

            public StackProxy<A> push(A elem) {
                return StackProxy$class.push(this, elem);
            }

            public A top() {
                return (A)StackProxy$class.top(this);
            }

            public A pop() {
                return (A)StackProxy$class.pop(this);
            }

            public void clear() {
                StackProxy$class.clear(this);
            }

            public Iterator<A> iterator() {
                return StackProxy$class.iterator(this);
            }

            public List<A> toList() {
                return StackProxy$class.toList(this);
            }

            public Stack<A> clone() {
                return StackProxy$class.clone(this);
            }

            public int hashCode() {
                return Proxy$class.hashCode(this);
            }

            public boolean equals(Object that) {
                return Proxy$class.equals(this, that);
            }

            public String toString() {
                return Proxy$class.toString(this);
            }

            public Stack<A> self() {
                return this.$outer.self().clone();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                Proxy$class.$init$(this);
                StackProxy$class.$init$(this);
            }
        };
    }

    public static void $init$(StackProxy $this) {
    }
}

