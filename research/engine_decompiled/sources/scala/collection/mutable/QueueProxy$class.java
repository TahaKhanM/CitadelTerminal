/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Proxy$class;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.mutable.Queue;
import scala.collection.mutable.QueueProxy;

public abstract class QueueProxy$class {
    public static Object apply(QueueProxy $this, int n) {
        return $this.self().apply(n);
    }

    public static int length(QueueProxy $this) {
        return $this.self().length();
    }

    public static boolean isEmpty(QueueProxy $this) {
        return $this.self().isEmpty();
    }

    public static QueueProxy $plus$eq(QueueProxy $this, Object elem) {
        $this.self().$plus$eq(elem);
        return $this;
    }

    public static QueueProxy $plus$plus$eq(QueueProxy $this, TraversableOnce it) {
        $this.self().$plus$plus$eq(it);
        return $this;
    }

    public static void enqueue(QueueProxy $this, Seq elems) {
        $this.self().$plus$plus$eq(elems);
    }

    public static Object dequeue(QueueProxy $this) {
        return $this.self().dequeue();
    }

    public static Object front(QueueProxy $this) {
        return $this.self().front();
    }

    public static void clear(QueueProxy $this) {
        $this.self().clear();
    }

    public static Iterator iterator(QueueProxy $this) {
        return $this.self().iterator();
    }

    public static Queue clone(QueueProxy $this) {
        return new QueueProxy<A>($this){
            private final /* synthetic */ QueueProxy $outer;

            public A apply(int n) {
                return (A)QueueProxy$class.apply(this, n);
            }

            public int length() {
                return QueueProxy$class.length(this);
            }

            public boolean isEmpty() {
                return QueueProxy$class.isEmpty(this);
            }

            public QueueProxy<A> $plus$eq(A elem) {
                return QueueProxy$class.$plus$eq(this, elem);
            }

            public QueueProxy<A> $plus$plus$eq(TraversableOnce<A> it) {
                return QueueProxy$class.$plus$plus$eq(this, it);
            }

            public void enqueue(Seq<A> elems) {
                QueueProxy$class.enqueue(this, elems);
            }

            public A dequeue() {
                return (A)QueueProxy$class.dequeue(this);
            }

            public A front() {
                return (A)QueueProxy$class.front(this);
            }

            public void clear() {
                QueueProxy$class.clear(this);
            }

            public Iterator<A> iterator() {
                return QueueProxy$class.iterator(this);
            }

            public Queue<A> clone() {
                return QueueProxy$class.clone(this);
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

            public Queue<A> self() {
                return this.$outer.self().clone();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                Proxy$class.$init$(this);
                QueueProxy$class.$init$(this);
            }
        };
    }

    public static void $init$(QueueProxy $this) {
    }
}

