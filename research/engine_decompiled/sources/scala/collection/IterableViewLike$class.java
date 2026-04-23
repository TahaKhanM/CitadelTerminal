/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function0;
import scala.Function1;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.GenTraversable;
import scala.collection.GenTraversableOnce;
import scala.collection.IterableView;
import scala.collection.IterableViewLike;
import scala.collection.IterableViewLike$Appended$class;
import scala.collection.IterableViewLike$DroppedWhile$class;
import scala.collection.IterableViewLike$Filtered$class;
import scala.collection.IterableViewLike$FlatMapped$class;
import scala.collection.IterableViewLike$Forced$class;
import scala.collection.IterableViewLike$Mapped$class;
import scala.collection.IterableViewLike$Sliced$class;
import scala.collection.IterableViewLike$TakenWhile$class;
import scala.collection.IterableViewLike$Zipped$class;
import scala.collection.IterableViewLike$ZippedAll$class;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.TraversableViewLike;
import scala.collection.TraversableViewLike$Appended$class;
import scala.collection.TraversableViewLike$DroppedWhile$class;
import scala.collection.TraversableViewLike$Filtered$class;
import scala.collection.TraversableViewLike$FlatMapped$class;
import scala.collection.TraversableViewLike$Forced$class;
import scala.collection.TraversableViewLike$Mapped$class;
import scala.collection.TraversableViewLike$Sliced$class;
import scala.collection.TraversableViewLike$TakenWhile$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SliceInterval;
import scala.collection.generic.SliceInterval$;
import scala.collection.immutable.Stream$;
import scala.math.package$;

public abstract class IterableViewLike$class {
    public static IterableView scala$collection$IterableViewLike$$asThis(IterableViewLike $this, IterableViewLike.Transformed xs) {
        return xs;
    }

    public static IterableViewLike.Transformed newZipped(IterableViewLike $this, GenIterable that) {
        return new IterableViewLike.Zipped<B>($this, that){
            private final GenIterable<B> other;
            private final /* synthetic */ IterableViewLike $outer;

            public Iterator<Tuple2<A, B>> iterator() {
                return IterableViewLike$Zipped$class.iterator(this);
            }

            public final String viewIdentifier() {
                return IterableViewLike$Zipped$class.viewIdentifier(this);
            }

            public GenIterable<B> other() {
                return this.other;
            }

            public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Zipped$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.other = that$1;
                super($outer);
                IterableViewLike$Zipped$class.$init$(this);
            }
        };
    }

    public static IterableViewLike.Transformed newZippedAll(IterableViewLike $this, GenIterable that, Object _thisElem, Object _thatElem) {
        return new IterableViewLike.ZippedAll<A1, B>($this, that, _thisElem, _thatElem){
            private final GenIterable<B> other;
            private final A1 thisElem;
            private final B thatElem;
            private final /* synthetic */ IterableViewLike $outer;

            public final String viewIdentifier() {
                return IterableViewLike$ZippedAll$class.viewIdentifier(this);
            }

            public Iterator<Tuple2<A1, B>> iterator() {
                return IterableViewLike$ZippedAll$class.iterator(this);
            }

            public GenIterable<B> other() {
                return this.other;
            }

            public A1 thisElem() {
                return this.thisElem;
            }

            public B thatElem() {
                return this.thatElem;
            }

            public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$ZippedAll$$$outer() {
                return this.$outer;
            }
            {
                void var4_4;
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.other = that$2;
                this.thisElem = var3_3;
                this.thatElem = var4_4;
                super($outer);
                IterableViewLike$ZippedAll$class.$init$(this);
            }
        };
    }

    public static IterableViewLike.Transformed newForced(IterableViewLike $this, Function0 xs) {
        return new IterableViewLike.Forced<B>($this, xs){
            private final GenSeq<B> forced;
            private final /* synthetic */ IterableViewLike $outer;

            public Iterator<B> iterator() {
                return IterableViewLike$Forced$class.iterator(this);
            }

            public <U> void foreach(Function1<B, U> f) {
                TraversableViewLike$Forced$class.foreach(this, f);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$Forced$class.viewIdentifier(this);
            }

            public GenSeq<B> forced() {
                return this.forced;
            }

            public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Forced$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Forced$$$outer() {
                return this.$outer;
            }
            {
                GenSeq forced;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.forced = forced = (GenSeq)xs$1.apply();
                super($outer);
                TraversableViewLike$Forced$class.$init$(this);
                IterableViewLike$Forced$class.$init$(this);
            }
        };
    }

    public static IterableViewLike.Transformed newAppended(IterableViewLike $this, GenTraversable that) {
        return new IterableViewLike.Appended<B>($this, that){
            private final GenTraversable<B> rest;
            private final /* synthetic */ IterableViewLike $outer;

            public Iterator<B> iterator() {
                return IterableViewLike$Appended$class.iterator(this);
            }

            public <U> void foreach(Function1<B, U> f) {
                TraversableViewLike$Appended$class.foreach(this, f);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$Appended$class.viewIdentifier(this);
            }

            public GenTraversable<B> rest() {
                return this.rest;
            }

            public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Appended$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Appended$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.rest = that$3;
                super($outer);
                TraversableViewLike$Appended$class.$init$(this);
                IterableViewLike$Appended$class.$init$(this);
            }
        };
    }

    public static IterableViewLike.Transformed newMapped(IterableViewLike $this, Function1 f) {
        return new IterableViewLike.Mapped<B>($this, f){
            private final Function1<A, B> mapping;
            private final /* synthetic */ IterableViewLike $outer;

            public Iterator<B> iterator() {
                return IterableViewLike$Mapped$class.iterator(this);
            }

            public <U> void foreach(Function1<B, U> f) {
                TraversableViewLike$Mapped$class.foreach(this, f);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$Mapped$class.viewIdentifier(this);
            }

            public Function1<A, B> mapping() {
                return this.mapping;
            }

            public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Mapped$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Mapped$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.mapping = f$1;
                super($outer);
                TraversableViewLike$Mapped$class.$init$(this);
                IterableViewLike$Mapped$class.$init$(this);
            }
        };
    }

    public static IterableViewLike.Transformed newFlatMapped(IterableViewLike $this, Function1 f) {
        return new IterableViewLike.FlatMapped<B>($this, f){
            private final Function1<A, GenTraversableOnce<B>> mapping;
            private final /* synthetic */ IterableViewLike $outer;

            public Iterator<B> iterator() {
                return IterableViewLike$FlatMapped$class.iterator(this);
            }

            public <U> void foreach(Function1<B, U> f) {
                TraversableViewLike$FlatMapped$class.foreach(this, f);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$FlatMapped$class.viewIdentifier(this);
            }

            public Function1<A, GenTraversableOnce<B>> mapping() {
                return this.mapping;
            }

            public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$FlatMapped$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$FlatMapped$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.mapping = f$2;
                super($outer);
                TraversableViewLike$FlatMapped$class.$init$(this);
                IterableViewLike$FlatMapped$class.$init$(this);
            }
        };
    }

    public static IterableViewLike.Transformed newFiltered(IterableViewLike $this, Function1 p) {
        return new IterableViewLike.Filtered($this, p){
            private final Function1<A, Object> pred;
            private final /* synthetic */ IterableViewLike $outer;

            public Iterator<A> iterator() {
                return IterableViewLike$Filtered$class.iterator(this);
            }

            public <U> void foreach(Function1<Object, U> f) {
                TraversableViewLike$Filtered$class.foreach(this, f);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$Filtered$class.viewIdentifier(this);
            }

            public Function1<A, Object> pred() {
                return this.pred;
            }

            public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Filtered$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Filtered$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.pred = p$1;
                super($outer);
                TraversableViewLike$Filtered$class.$init$(this);
                IterableViewLike$Filtered$class.$init$(this);
            }
        };
    }

    public static IterableViewLike.Transformed newSliced(IterableViewLike $this, SliceInterval _endpoints) {
        return new IterableViewLike.Sliced($this, _endpoints){
            private final SliceInterval endpoints;
            private final /* synthetic */ IterableViewLike $outer;

            public Iterator<A> iterator() {
                return IterableViewLike$Sliced$class.iterator(this);
            }

            public int from() {
                return TraversableViewLike$Sliced$class.from(this);
            }

            public int until() {
                return TraversableViewLike$Sliced$class.until(this);
            }

            public <U> void foreach(Function1<Object, U> f) {
                TraversableViewLike$Sliced$class.foreach(this, f);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$Sliced$class.viewIdentifier(this);
            }

            public SliceInterval endpoints() {
                return this.endpoints;
            }

            public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Sliced$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Sliced$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.endpoints = _endpoints$1;
                super($outer);
                TraversableViewLike$Sliced$class.$init$(this);
                IterableViewLike$Sliced$class.$init$(this);
            }
        };
    }

    public static IterableViewLike.Transformed newDroppedWhile(IterableViewLike $this, Function1 p) {
        return new IterableViewLike.DroppedWhile($this, p){
            private final Function1<A, Object> pred;
            private final /* synthetic */ IterableViewLike $outer;

            public Iterator<A> iterator() {
                return IterableViewLike$DroppedWhile$class.iterator(this);
            }

            public <U> void foreach(Function1<Object, U> f) {
                TraversableViewLike$DroppedWhile$class.foreach(this, f);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$DroppedWhile$class.viewIdentifier(this);
            }

            public Function1<A, Object> pred() {
                return this.pred;
            }

            public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$DroppedWhile$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$DroppedWhile$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.pred = p$2;
                super($outer);
                TraversableViewLike$DroppedWhile$class.$init$(this);
                IterableViewLike$DroppedWhile$class.$init$(this);
            }
        };
    }

    public static IterableViewLike.Transformed newTakenWhile(IterableViewLike $this, Function1 p) {
        return new IterableViewLike.TakenWhile($this, p){
            private final Function1<A, Object> pred;
            private final /* synthetic */ IterableViewLike $outer;

            public Iterator<A> iterator() {
                return IterableViewLike$TakenWhile$class.iterator(this);
            }

            public <U> void foreach(Function1<Object, U> f) {
                TraversableViewLike$TakenWhile$class.foreach(this, f);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$TakenWhile$class.viewIdentifier(this);
            }

            public Function1<A, Object> pred() {
                return this.pred;
            }

            public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$TakenWhile$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$TakenWhile$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.pred = p$3;
                super($outer);
                TraversableViewLike$TakenWhile$class.$init$(this);
                IterableViewLike$TakenWhile$class.$init$(this);
            }
        };
    }

    public static IterableViewLike.Transformed newTaken(IterableViewLike $this, int n) {
        return $this.newSliced(SliceInterval$.MODULE$.apply(0, n));
    }

    public static IterableViewLike.Transformed newDropped(IterableViewLike $this, int n) {
        return $this.newSliced(SliceInterval$.MODULE$.apply(n, Integer.MAX_VALUE));
    }

    public static IterableView drop(IterableViewLike $this, int n) {
        return IterableViewLike$class.scala$collection$IterableViewLike$$asThis($this, $this.newDropped(n));
    }

    public static IterableView take(IterableViewLike $this, int n) {
        return IterableViewLike$class.scala$collection$IterableViewLike$$asThis($this, $this.newTaken(n));
    }

    public static Object zip(IterableViewLike $this, GenIterable that, CanBuildFrom bf) {
        return $this.newZipped(that);
    }

    public static Object zipWithIndex(IterableViewLike $this, CanBuildFrom bf) {
        return $this.zip(Stream$.MODULE$.from(0), bf);
    }

    public static Object zipAll(IterableViewLike $this, GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
        return $this.newZippedAll(that, thisElem, thatElem);
    }

    public static Iterator grouped(IterableViewLike $this, int size2) {
        return $this.iterator().grouped(size2).map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ IterableViewLike $outer;

            public final This apply(Seq<A> x) {
                return (This)this.$outer.newForced(new Serializable(this, x){
                    public static final long serialVersionUID = 0L;
                    private final Seq x$1;

                    public final Seq<A> apply() {
                        return this.x$1;
                    }
                    {
                        this.x$1 = x$1;
                    }
                });
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Iterator sliding(IterableViewLike $this, int size2, int step) {
        return $this.iterator().sliding(size2, step).map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ IterableViewLike $outer;

            public final This apply(Seq<A> x) {
                return (This)this.$outer.newForced(new Serializable(this, x){
                    public static final long serialVersionUID = 0L;
                    private final Seq x$2;

                    public final Seq<A> apply() {
                        return this.x$2;
                    }
                    {
                        this.x$2 = x$2;
                    }
                });
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Iterator sliding(IterableViewLike $this, int size2) {
        return $this.sliding(size2, 1);
    }

    public static IterableView dropRight(IterableViewLike $this, int n) {
        return $this.take($this.thisSeq().length() - package$.MODULE$.max(n, 0));
    }

    public static IterableView takeRight(IterableViewLike $this, int n) {
        return $this.drop($this.thisSeq().length() - package$.MODULE$.max(n, 0));
    }

    public static String stringPrefix(IterableViewLike $this) {
        return "IterableView";
    }

    public static void $init$(IterableViewLike $this) {
    }
}

