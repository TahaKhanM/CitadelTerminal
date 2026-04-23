/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function1;
import scala.Predef$;
import scala.Tuple2;
import scala.collection.IterableViewLike;
import scala.collection.IterableViewLike$DroppedWhile$class;
import scala.collection.IterableViewLike$Filtered$class;
import scala.collection.IterableViewLike$Sliced$class;
import scala.collection.IterableViewLike$TakenWhile$class;
import scala.collection.Iterator;
import scala.collection.SeqViewLike;
import scala.collection.SeqViewLike$DroppedWhile$class;
import scala.collection.SeqViewLike$Filtered$class;
import scala.collection.SeqViewLike$Reversed$class;
import scala.collection.SeqViewLike$Sliced$class;
import scala.collection.SeqViewLike$TakenWhile$class;
import scala.collection.TraversableViewLike;
import scala.collection.TraversableViewLike$DroppedWhile$class;
import scala.collection.TraversableViewLike$Filtered$class;
import scala.collection.TraversableViewLike$Sliced$class;
import scala.collection.TraversableViewLike$TakenWhile$class;
import scala.collection.generic.SliceInterval;
import scala.collection.generic.SliceInterval$;
import scala.collection.mutable.IndexedSeqView;
import scala.collection.mutable.IndexedSeqView$;
import scala.collection.mutable.IndexedSeqView$DroppedWhile$class;
import scala.collection.mutable.IndexedSeqView$Filtered$class;
import scala.collection.mutable.IndexedSeqView$Reversed$class;
import scala.collection.mutable.IndexedSeqView$Sliced$class;
import scala.collection.mutable.IndexedSeqView$TakenWhile$class;
import scala.runtime.RichInt$;

public abstract class IndexedSeqView$class {
    public static IndexedSeqView.Transformed newFiltered(IndexedSeqView $this, Function1 p) {
        return new IndexedSeqView.Filtered($this, p){
            private final Function1<A, Object> pred;
            private final /* synthetic */ IndexedSeqView $outer;
            private final int[] index;
            private volatile boolean bitmap$0;

            public void update(int idx, A elem) {
                IndexedSeqView$Filtered$class.update(this, idx, elem);
            }

            private int[] index$lzycompute() {
                IndexedSeqView$.anon.1 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.index = SeqViewLike$Filtered$class.index(this);
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.index;
                }
            }

            public int[] index() {
                return this.bitmap$0 ? this.index : this.index$lzycompute();
            }

            public int length() {
                return SeqViewLike$Filtered$class.length(this);
            }

            public Object apply(int idx) {
                return SeqViewLike$Filtered$class.apply(this, idx);
            }

            public Iterator<Object> iterator() {
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

            public /* synthetic */ IndexedSeqView scala$collection$mutable$IndexedSeqView$Filtered$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Filtered$$$outer() {
                return this.$outer;
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
                SeqViewLike$Filtered$class.$init$(this);
                IndexedSeqView$Filtered$class.$init$(this);
            }
        };
    }

    public static IndexedSeqView.Transformed newSliced(IndexedSeqView $this, SliceInterval _endpoints) {
        return new IndexedSeqView.Sliced($this, _endpoints){
            private final SliceInterval endpoints;
            private final /* synthetic */ IndexedSeqView $outer;

            public int length() {
                return IndexedSeqView$Sliced$class.length(this);
            }

            public void update(int idx, A elem) {
                IndexedSeqView$Sliced$class.update(this, idx, elem);
            }

            public Object apply(int idx) {
                return SeqViewLike$Sliced$class.apply(this, idx);
            }

            public <U> void foreach(Function1<Object, U> f) {
                SeqViewLike$Sliced$class.foreach(this, f);
            }

            public Iterator<Object> iterator() {
                return SeqViewLike$Sliced$class.iterator(this);
            }

            public int from() {
                return TraversableViewLike$Sliced$class.from(this);
            }

            public int until() {
                return TraversableViewLike$Sliced$class.until(this);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$Sliced$class.viewIdentifier(this);
            }

            public SliceInterval endpoints() {
                return this.endpoints;
            }

            public /* synthetic */ IndexedSeqView scala$collection$mutable$IndexedSeqView$Sliced$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Sliced$$$outer() {
                return this.$outer;
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
                SeqViewLike$Sliced$class.$init$(this);
                IndexedSeqView$Sliced$class.$init$(this);
            }
        };
    }

    public static IndexedSeqView.Transformed newDroppedWhile(IndexedSeqView $this, Function1 p) {
        return new IndexedSeqView.DroppedWhile($this, p){
            private final Function1<A, Object> pred;
            private final /* synthetic */ IndexedSeqView $outer;
            private final int start;
            private volatile boolean bitmap$0;

            public void update(int idx, A elem) {
                IndexedSeqView$DroppedWhile$class.update(this, idx, elem);
            }

            private int start$lzycompute() {
                IndexedSeqView$.anon.3 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.start = SeqViewLike$DroppedWhile$class.start(this);
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.start;
                }
            }

            public int start() {
                return this.bitmap$0 ? this.start : this.start$lzycompute();
            }

            public int length() {
                return SeqViewLike$DroppedWhile$class.length(this);
            }

            public Object apply(int idx) {
                return SeqViewLike$DroppedWhile$class.apply(this, idx);
            }

            public Iterator<Object> iterator() {
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

            public /* synthetic */ IndexedSeqView scala$collection$mutable$IndexedSeqView$DroppedWhile$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$DroppedWhile$$$outer() {
                return this.$outer;
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
                SeqViewLike$DroppedWhile$class.$init$(this);
                IndexedSeqView$DroppedWhile$class.$init$(this);
            }
        };
    }

    public static IndexedSeqView.Transformed newTakenWhile(IndexedSeqView $this, Function1 p) {
        return new IndexedSeqView.TakenWhile($this, p){
            private final Function1<A, Object> pred;
            private final /* synthetic */ IndexedSeqView $outer;
            private final int len;
            private volatile boolean bitmap$0;

            public void update(int idx, A elem) {
                IndexedSeqView$TakenWhile$class.update(this, idx, elem);
            }

            private int len$lzycompute() {
                IndexedSeqView$.anon.4 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.len = SeqViewLike$TakenWhile$class.len(this);
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.len;
                }
            }

            public int len() {
                return this.bitmap$0 ? this.len : this.len$lzycompute();
            }

            public int length() {
                return SeqViewLike$TakenWhile$class.length(this);
            }

            public Object apply(int idx) {
                return SeqViewLike$TakenWhile$class.apply(this, idx);
            }

            public Iterator<Object> iterator() {
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

            public /* synthetic */ IndexedSeqView scala$collection$mutable$IndexedSeqView$TakenWhile$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$TakenWhile$$$outer() {
                return this.$outer;
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
                SeqViewLike$TakenWhile$class.$init$(this);
                IndexedSeqView$TakenWhile$class.$init$(this);
            }
        };
    }

    public static IndexedSeqView.Transformed newReversed(IndexedSeqView $this) {
        return new IndexedSeqView.Reversed($this){
            private final /* synthetic */ IndexedSeqView $outer;

            public void update(int idx, A elem) {
                IndexedSeqView$Reversed$class.update(this, idx, elem);
            }

            public Iterator<Object> iterator() {
                return SeqViewLike$Reversed$class.iterator(this);
            }

            public int length() {
                return SeqViewLike$Reversed$class.length(this);
            }

            public Object apply(int idx) {
                return SeqViewLike$Reversed$class.apply(this, idx);
            }

            public final String viewIdentifier() {
                return SeqViewLike$Reversed$class.viewIdentifier(this);
            }

            public /* synthetic */ IndexedSeqView scala$collection$mutable$IndexedSeqView$Reversed$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Reversed$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super($outer);
                SeqViewLike$Reversed$class.$init$(this);
                IndexedSeqView$Reversed$class.$init$(this);
            }
        };
    }

    public static IndexedSeqView filter(IndexedSeqView $this, Function1 p) {
        return $this.newFiltered(p);
    }

    public static IndexedSeqView init(IndexedSeqView $this) {
        return $this.newSliced(SliceInterval$.MODULE$.apply(0, $this.length() - 1));
    }

    public static IndexedSeqView drop(IndexedSeqView $this, int n) {
        return $this.newSliced(SliceInterval$.MODULE$.apply(n, $this.length()));
    }

    public static IndexedSeqView take(IndexedSeqView $this, int n) {
        Predef$ predef$ = Predef$.MODULE$;
        return $this.newSliced(SliceInterval$.MODULE$.apply(0, RichInt$.MODULE$.min$extension(n, $this.length())));
    }

    public static IndexedSeqView slice(IndexedSeqView $this, int from2, int until2) {
        Predef$ predef$ = Predef$.MODULE$;
        return $this.newSliced(SliceInterval$.MODULE$.apply(from2, RichInt$.MODULE$.min$extension(until2, $this.length())));
    }

    public static IndexedSeqView dropWhile(IndexedSeqView $this, Function1 p) {
        return $this.newDroppedWhile(p);
    }

    public static IndexedSeqView takeWhile(IndexedSeqView $this, Function1 p) {
        return $this.newTakenWhile(p);
    }

    public static Tuple2 span(IndexedSeqView $this, Function1 p) {
        return new Tuple2($this.newTakenWhile(p), $this.newDroppedWhile(p));
    }

    public static Tuple2 splitAt(IndexedSeqView $this, int n) {
        return new Tuple2($this.take(n), $this.drop(n));
    }

    public static IndexedSeqView reverse(IndexedSeqView $this) {
        return $this.newReversed();
    }

    public static IndexedSeqView tail(IndexedSeqView $this) {
        return $this.isEmpty() ? $this.scala$collection$mutable$IndexedSeqView$$super$tail() : $this.slice(1, $this.length());
    }

    public static void $init$(IndexedSeqView $this) {
    }
}

