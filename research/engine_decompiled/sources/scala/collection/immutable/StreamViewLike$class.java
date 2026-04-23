/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.PartialFunction$class;
import scala.Predef$;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenSeq;
import scala.collection.GenSeq$class;
import scala.collection.GenSeqLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterable$class;
import scala.collection.IterableLike$class;
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
import scala.collection.IterableViewLike$Transformed$class;
import scala.collection.IterableViewLike$Zipped$class;
import scala.collection.IterableViewLike$ZippedAll$class;
import scala.collection.IterableViewLike$class;
import scala.collection.Iterator;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Seq$class;
import scala.collection.SeqLike$class;
import scala.collection.SeqView;
import scala.collection.SeqViewLike;
import scala.collection.SeqViewLike$Appended$class;
import scala.collection.SeqViewLike$DroppedWhile$class;
import scala.collection.SeqViewLike$Filtered$class;
import scala.collection.SeqViewLike$FlatMapped$class;
import scala.collection.SeqViewLike$Forced$class;
import scala.collection.SeqViewLike$Mapped$class;
import scala.collection.SeqViewLike$Patched$class;
import scala.collection.SeqViewLike$Prepended$class;
import scala.collection.SeqViewLike$Reversed$class;
import scala.collection.SeqViewLike$Sliced$class;
import scala.collection.SeqViewLike$TakenWhile$class;
import scala.collection.SeqViewLike$Transformed$class;
import scala.collection.SeqViewLike$Zipped$class;
import scala.collection.SeqViewLike$ZippedAll$class;
import scala.collection.SeqViewLike$class;
import scala.collection.Traversable;
import scala.collection.Traversable$class;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.TraversableView;
import scala.collection.TraversableViewLike;
import scala.collection.TraversableViewLike$Appended$class;
import scala.collection.TraversableViewLike$DroppedWhile$class;
import scala.collection.TraversableViewLike$Filtered$class;
import scala.collection.TraversableViewLike$FlatMapped$class;
import scala.collection.TraversableViewLike$Forced$class;
import scala.collection.TraversableViewLike$Mapped$class;
import scala.collection.TraversableViewLike$Sliced$class;
import scala.collection.TraversableViewLike$TakenWhile$class;
import scala.collection.TraversableViewLike$Transformed$class;
import scala.collection.TraversableViewLike$class;
import scala.collection.ViewMkString$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.SliceInterval;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.StreamView;
import scala.collection.immutable.StreamViewLike;
import scala.collection.immutable.StreamViewLike$;
import scala.collection.immutable.StreamViewLike$Transformed$class;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParSeq;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.Nothing$;

public abstract class StreamViewLike$class {
    public static Object force(StreamViewLike $this, CanBuildFrom bf) {
        return $this.iterator().toStream();
    }

    public static StreamViewLike.Transformed newForced(StreamViewLike $this, Function0 xs) {
        return new StreamViewLike.Forced<B>($this, xs){
            private final GenSeq<B> forced;
            private final /* synthetic */ StreamViewLike $outer;

            public int length() {
                return SeqViewLike$Forced$class.length(this);
            }

            public B apply(int idx) {
                return (B)SeqViewLike$Forced$class.apply(this, idx);
            }

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

            public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Forced$$$outer() {
                return this.$outer;
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
                SeqViewLike$Forced$class.$init$(this);
            }
        };
    }

    public static StreamViewLike.Transformed newAppended(StreamViewLike $this, GenTraversable that) {
        return new StreamViewLike.Appended<B>($this, that){
            private final GenTraversable<B> rest;
            private final /* synthetic */ StreamViewLike $outer;
            private final GenSeq<Object> restSeq;
            private volatile boolean bitmap$0;

            private GenSeq restSeq$lzycompute() {
                StreamViewLike$.anon.2 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.restSeq = SeqViewLike$Appended$class.restSeq(this);
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.restSeq;
                }
            }

            public GenSeq<B> restSeq() {
                return this.bitmap$0 ? this.restSeq : this.restSeq$lzycompute();
            }

            public int length() {
                return SeqViewLike$Appended$class.length(this);
            }

            public B apply(int idx) {
                return (B)SeqViewLike$Appended$class.apply(this, idx);
            }

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

            public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Appended$$$outer() {
                return this.$outer;
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
                this.rest = that$1;
                super($outer);
                TraversableViewLike$Appended$class.$init$(this);
                IterableViewLike$Appended$class.$init$(this);
                SeqViewLike$Appended$class.$init$(this);
            }
        };
    }

    public static StreamViewLike.Transformed newMapped(StreamViewLike $this, Function1 f) {
        return new StreamViewLike.Mapped<B>($this, f){
            private final Function1<A, B> mapping;
            private final /* synthetic */ StreamViewLike $outer;

            public int length() {
                return SeqViewLike$Mapped$class.length(this);
            }

            public B apply(int idx) {
                return (B)SeqViewLike$Mapped$class.apply(this, idx);
            }

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

            public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Mapped$$$outer() {
                return this.$outer;
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
                SeqViewLike$Mapped$class.$init$(this);
            }
        };
    }

    public static StreamViewLike.Transformed newFlatMapped(StreamViewLike $this, Function1 f) {
        return new StreamViewLike.FlatMapped<B>($this, f){
            private final Function1<A, GenTraversableOnce<B>> mapping;
            private final /* synthetic */ StreamViewLike $outer;
            private final int[] index;
            private volatile boolean bitmap$0;

            private int[] index$lzycompute() {
                StreamViewLike$.anon.4 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.index = SeqViewLike$FlatMapped$class.index(this);
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.index;
                }
            }

            public int[] index() {
                return this.bitmap$0 ? this.index : this.index$lzycompute();
            }

            public int findRow(int idx, int lo, int hi) {
                return SeqViewLike$FlatMapped$class.findRow(this, idx, lo, hi);
            }

            public int length() {
                return SeqViewLike$FlatMapped$class.length(this);
            }

            public B apply(int idx) {
                return (B)SeqViewLike$FlatMapped$class.apply(this, idx);
            }

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

            public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$FlatMapped$$$outer() {
                return this.$outer;
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
                SeqViewLike$FlatMapped$class.$init$(this);
            }
        };
    }

    public static StreamViewLike.Transformed newFiltered(StreamViewLike $this, Function1 p) {
        return new StreamViewLike.Filtered($this, p){
            private final Function1<A, Object> pred;
            private final /* synthetic */ StreamViewLike $outer;
            private final int[] index;
            private volatile boolean bitmap$0;

            private int[] index$lzycompute() {
                StreamViewLike$.anon.5 var1_1 = this;
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
            }
        };
    }

    public static StreamViewLike.Transformed newSliced(StreamViewLike $this, SliceInterval _endpoints) {
        return new StreamViewLike.Sliced($this, _endpoints){
            private final SliceInterval endpoints;
            private final /* synthetic */ StreamViewLike $outer;

            public int length() {
                return SeqViewLike$Sliced$class.length(this);
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
            }
        };
    }

    public static StreamViewLike.Transformed newDroppedWhile(StreamViewLike $this, Function1 p) {
        return new StreamViewLike.DroppedWhile($this, p){
            private final Function1<A, Object> pred;
            private final /* synthetic */ StreamViewLike $outer;
            private final int start;
            private volatile boolean bitmap$0;

            private int start$lzycompute() {
                StreamViewLike$.anon.7 var1_1 = this;
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
            }
        };
    }

    public static StreamViewLike.Transformed newTakenWhile(StreamViewLike $this, Function1 p) {
        return new StreamViewLike.TakenWhile($this, p){
            private final Function1<A, Object> pred;
            private final /* synthetic */ StreamViewLike $outer;
            private final int len;
            private volatile boolean bitmap$0;

            private int len$lzycompute() {
                StreamViewLike$.anon.8 var1_1 = this;
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
            }
        };
    }

    public static StreamViewLike.Transformed newZipped(StreamViewLike $this, GenIterable that) {
        return new StreamViewLike.Zipped<B>($this, that){
            private final GenIterable<B> other;
            private final /* synthetic */ StreamViewLike $outer;
            private final Seq<Object> thatSeq;
            private volatile boolean bitmap$0;

            private Seq thatSeq$lzycompute() {
                StreamViewLike$.anon.9 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.thatSeq = SeqViewLike$Zipped$class.thatSeq(this);
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.thatSeq;
                }
            }

            public Seq<B> thatSeq() {
                return this.bitmap$0 ? this.thatSeq : this.thatSeq$lzycompute();
            }

            public int length() {
                return SeqViewLike$Zipped$class.length(this);
            }

            public Tuple2<A, B> apply(int idx) {
                return SeqViewLike$Zipped$class.apply(this, idx);
            }

            public Iterator<Tuple2<A, B>> iterator() {
                return IterableViewLike$Zipped$class.iterator(this);
            }

            public final String viewIdentifier() {
                return IterableViewLike$Zipped$class.viewIdentifier(this);
            }

            public GenIterable<B> other() {
                return this.other;
            }

            public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Zipped$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Zipped$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.other = that$2;
                super($outer);
                IterableViewLike$Zipped$class.$init$(this);
                SeqViewLike$Zipped$class.$init$(this);
            }
        };
    }

    public static StreamViewLike.Transformed newZippedAll(StreamViewLike $this, GenIterable that, Object _thisElem, Object _thatElem) {
        return new StreamViewLike.ZippedAll<A1, B>($this, that, _thisElem, _thatElem){
            private final GenIterable<B> other;
            private final A1 thisElem;
            private final B thatElem;
            private final /* synthetic */ StreamViewLike $outer;
            private final Seq<Object> thatSeq;
            private volatile boolean bitmap$0;

            private Seq thatSeq$lzycompute() {
                StreamViewLike$.anon.10 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.thatSeq = SeqViewLike$ZippedAll$class.thatSeq(this);
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.thatSeq;
                }
            }

            public Seq<B> thatSeq() {
                return this.bitmap$0 ? this.thatSeq : this.thatSeq$lzycompute();
            }

            public int length() {
                return SeqViewLike$ZippedAll$class.length(this);
            }

            public Tuple2<A1, B> apply(int idx) {
                return SeqViewLike$ZippedAll$class.apply(this, idx);
            }

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

            public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$ZippedAll$$$outer() {
                return this.$outer;
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
                this.other = that$3;
                this.thisElem = var3_3;
                this.thatElem = var4_4;
                super($outer);
                IterableViewLike$ZippedAll$class.$init$(this);
                SeqViewLike$ZippedAll$class.$init$(this);
            }
        };
    }

    public static StreamViewLike.Transformed newReversed(StreamViewLike $this) {
        return new StreamViewLike.Reversed($this){
            private final /* synthetic */ StreamViewLike $outer;
            private final Object underlying;
            private volatile boolean bitmap$0;

            public String toString() {
                return StreamViewLike$Transformed$class.toString(this);
            }

            public <B, That> That force(CanBuildFrom<Coll, B, That> bf) {
                return (That)StreamViewLike$class.force(this, bf);
            }

            public <B> StreamViewLike.Transformed<B> newForced(Function0<GenSeq<B>> xs) {
                return StreamViewLike$class.newForced(this, xs);
            }

            public <B> StreamViewLike.Transformed<B> newAppended(GenTraversable<B> that) {
                return StreamViewLike$class.newAppended(this, that);
            }

            public <B> StreamViewLike.Transformed<B> newMapped(Function1<A, B> f) {
                return StreamViewLike$class.newMapped(this, f);
            }

            public <B> StreamViewLike.Transformed<B> newFlatMapped(Function1<A, GenTraversableOnce<B>> f) {
                return StreamViewLike$class.newFlatMapped(this, f);
            }

            public StreamViewLike.Transformed<A> newFiltered(Function1<A, Object> p) {
                return StreamViewLike$class.newFiltered(this, p);
            }

            public StreamViewLike.Transformed<A> newSliced(SliceInterval _endpoints) {
                return StreamViewLike$class.newSliced(this, _endpoints);
            }

            public StreamViewLike.Transformed<A> newDroppedWhile(Function1<A, Object> p) {
                return StreamViewLike$class.newDroppedWhile(this, p);
            }

            public StreamViewLike.Transformed<A> newTakenWhile(Function1<A, Object> p) {
                return StreamViewLike$class.newTakenWhile(this, p);
            }

            public <B> StreamViewLike.Transformed<Tuple2<A, B>> newZipped(GenIterable<B> that) {
                return StreamViewLike$class.newZipped(this, that);
            }

            public <A1, B> StreamViewLike.Transformed<Tuple2<A1, B>> newZippedAll(GenIterable<B> that, A1 _thisElem, B _thatElem) {
                return StreamViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
            }

            public StreamViewLike.Transformed<A> newReversed() {
                return StreamViewLike$class.newReversed(this);
            }

            public <B> StreamViewLike.Transformed<B> newPatched(int _from, GenSeq<B> _patch, int _replaced) {
                return StreamViewLike$class.newPatched(this, _from, _patch, _replaced);
            }

            public <B> StreamViewLike.Transformed<B> newPrepended(B elem) {
                return StreamViewLike$class.newPrepended(this, elem);
            }

            public String stringPrefix() {
                return StreamViewLike$class.stringPrefix(this);
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

            public <U> void foreach(Function1<A, U> f) {
                IterableViewLike$Transformed$class.foreach(this, f);
            }

            public boolean isEmpty() {
                return IterableViewLike$Transformed$class.isEmpty(this);
            }

            private Object underlying$lzycompute() {
                StreamViewLike$.anon.13 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.underlying = TraversableViewLike$Transformed$class.underlying(this);
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.underlying;
                }
            }

            public Coll underlying() {
                return (Coll)(this.bitmap$0 ? this.underlying : this.underlying$lzycompute());
            }

            public final String viewIdString() {
                return TraversableViewLike$Transformed$class.viewIdString(this);
            }

            public Option<A> headOption() {
                return TraversableViewLike$Transformed$class.headOption(this);
            }

            public Option<A> lastOption() {
                return TraversableViewLike$Transformed$class.lastOption(this);
            }

            public SeqViewLike.Transformed<A> newTaken(int n) {
                return SeqViewLike$class.newTaken(this, n);
            }

            public SeqViewLike.Transformed<A> newDropped(int n) {
                return SeqViewLike$class.newDropped(this, n);
            }

            public SeqView reverse() {
                return SeqViewLike$class.reverse(this);
            }

            public <B, That> That patch(int from2, GenSeq<B> patch2, int replaced, CanBuildFrom<StreamView<A, Coll>, B, That> bf) {
                return (That)SeqViewLike$class.patch(this, from2, patch2, replaced, bf);
            }

            public <B, That> That padTo(int len, B elem, CanBuildFrom<StreamView<A, Coll>, B, That> bf) {
                return (That)SeqViewLike$class.padTo(this, len, elem, bf);
            }

            public <B, That> That reverseMap(Function1<A, B> f, CanBuildFrom<StreamView<A, Coll>, B, That> bf) {
                return (That)SeqViewLike$class.reverseMap(this, f, bf);
            }

            public <B, That> That updated(int index, B elem, CanBuildFrom<StreamView<A, Coll>, B, That> bf) {
                return (That)SeqViewLike$class.updated(this, index, elem, bf);
            }

            public <B, That> That $plus$colon(B elem, CanBuildFrom<StreamView<A, Coll>, B, That> bf) {
                return (That)SeqViewLike$class.$plus$colon(this, elem, bf);
            }

            public <B, That> That $colon$plus(B elem, CanBuildFrom<StreamView<A, Coll>, B, That> bf) {
                return (That)SeqViewLike$class.$colon$plus(this, elem, bf);
            }

            public <B, That> That union(GenSeq<B> that, CanBuildFrom<StreamView<A, Coll>, B, That> bf) {
                return (That)SeqViewLike$class.union(this, that, bf);
            }

            public SeqView diff(GenSeq that) {
                return SeqViewLike$class.diff(this, that);
            }

            public SeqView intersect(GenSeq that) {
                return SeqViewLike$class.intersect(this, that);
            }

            public SeqView sorted(Ordering ord) {
                return SeqViewLike$class.sorted(this, ord);
            }

            public SeqView sortWith(Function2 lt) {
                return SeqViewLike$class.sortWith(this, lt);
            }

            public SeqView sortBy(Function1 f, Ordering ord) {
                return SeqViewLike$class.sortBy(this, f, ord);
            }

            public Iterator<StreamView<A, Coll>> combinations(int n) {
                return SeqViewLike$class.combinations(this, n);
            }

            public Iterator<StreamView<A, Coll>> permutations() {
                return SeqViewLike$class.permutations(this);
            }

            public SeqView distinct() {
                return SeqViewLike$class.distinct(this);
            }

            public IterableView drop(int n) {
                return IterableViewLike$class.drop(this, n);
            }

            public IterableView take(int n) {
                return IterableViewLike$class.take(this, n);
            }

            public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<StreamView<A, Coll>, Tuple2<A1, B>, That> bf) {
                return (That)IterableViewLike$class.zip(this, that, bf);
            }

            public <A1, That> That zipWithIndex(CanBuildFrom<StreamView<A, Coll>, Tuple2<A1, Object>, That> bf) {
                return (That)IterableViewLike$class.zipWithIndex(this, bf);
            }

            public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<StreamView<A, Coll>, Tuple2<A1, B>, That> bf) {
                return (That)IterableViewLike$class.zipAll(this, that, thisElem, thatElem, bf);
            }

            public Iterator<StreamView<A, Coll>> grouped(int size2) {
                return IterableViewLike$class.grouped(this, size2);
            }

            public Iterator<StreamView<A, Coll>> sliding(int size2, int step) {
                return IterableViewLike$class.sliding(this, size2, step);
            }

            public Iterator<StreamView<A, Coll>> sliding(int size2) {
                return IterableViewLike$class.sliding(this, size2);
            }

            public IterableView dropRight(int n) {
                return IterableViewLike$class.dropRight(this, n);
            }

            public IterableView takeRight(int n) {
                return IterableViewLike$class.takeRight(this, n);
            }

            public /* synthetic */ TraversableView scala$collection$TraversableViewLike$$super$tail() {
                return (TraversableView)TraversableLike$class.tail(this);
            }

            public String viewToString() {
                return TraversableViewLike$class.viewToString(this);
            }

            public Builder<A, StreamView<A, Coll>> newBuilder() {
                return TraversableViewLike$class.newBuilder(this);
            }

            public <B, That> That $plus$plus(GenTraversableOnce<B> xs, CanBuildFrom<StreamView<A, Coll>, B, That> bf) {
                return (That)TraversableViewLike$class.$plus$plus(this, xs, bf);
            }

            public <B, That> That map(Function1<A, B> f, CanBuildFrom<StreamView<A, Coll>, B, That> bf) {
                return (That)TraversableViewLike$class.map(this, f, bf);
            }

            public <B, That> That collect(PartialFunction<A, B> pf, CanBuildFrom<StreamView<A, Coll>, B, That> bf) {
                return (That)TraversableViewLike$class.collect(this, pf, bf);
            }

            public <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> f, CanBuildFrom<StreamView<A, Coll>, B, That> bf) {
                return (That)TraversableViewLike$class.flatMap(this, f, bf);
            }

            public <B> TraversableViewLike.Transformed<B> flatten(Function1<A, GenTraversableOnce<B>> asTraversable) {
                return TraversableViewLike$class.flatten(this, asTraversable);
            }

            public TraversableView filter(Function1 p) {
                return TraversableViewLike$class.filter(this, p);
            }

            public TraversableView withFilter(Function1 p) {
                return TraversableViewLike$class.withFilter(this, p);
            }

            public Tuple2<StreamView<A, Coll>, StreamView<A, Coll>> partition(Function1<A, Object> p) {
                return TraversableViewLike$class.partition(this, p);
            }

            public TraversableView init() {
                return TraversableViewLike$class.init(this);
            }

            public TraversableView slice(int from2, int until2) {
                return TraversableViewLike$class.slice(this, from2, until2);
            }

            public TraversableView dropWhile(Function1 p) {
                return TraversableViewLike$class.dropWhile(this, p);
            }

            public TraversableView takeWhile(Function1 p) {
                return TraversableViewLike$class.takeWhile(this, p);
            }

            public Tuple2<StreamView<A, Coll>, StreamView<A, Coll>> span(Function1<A, Object> p) {
                return TraversableViewLike$class.span(this, p);
            }

            public Tuple2<StreamView<A, Coll>, StreamView<A, Coll>> splitAt(int n) {
                return TraversableViewLike$class.splitAt(this, n);
            }

            public <B, That> That scanLeft(B z, Function2<B, A, B> op, CanBuildFrom<StreamView<A, Coll>, B, That> bf) {
                return (That)TraversableViewLike$class.scanLeft(this, z, op, bf);
            }

            public <B, That> That scanRight(B z, Function2<A, B, B> op, CanBuildFrom<StreamView<A, Coll>, B, That> bf) {
                return (That)TraversableViewLike$class.scanRight(this, z, op, bf);
            }

            public <K> Map<K, StreamView<A, Coll>> groupBy(Function1<A, K> f) {
                return TraversableViewLike$class.groupBy(this, f);
            }

            public <A1, A2> Tuple2<TraversableViewLike.Transformed<A1>, TraversableViewLike.Transformed<A2>> unzip(Function1<A, Tuple2<A1, A2>> asPair) {
                return TraversableViewLike$class.unzip(this, asPair);
            }

            public <A1, A2, A3> Tuple3<TraversableViewLike.Transformed<A1>, TraversableViewLike.Transformed<A2>, TraversableViewLike.Transformed<A3>> unzip3(Function1<A, Tuple3<A1, A2, A3>> asTriple) {
                return TraversableViewLike$class.unzip3(this, asTriple);
            }

            public TraversableView filterNot(Function1 p) {
                return TraversableViewLike$class.filterNot(this, p);
            }

            public Iterator<StreamView<A, Coll>> inits() {
                return TraversableViewLike$class.inits(this);
            }

            public Iterator<StreamView<A, Coll>> tails() {
                return TraversableViewLike$class.tails(this);
            }

            public TraversableView tail() {
                return TraversableViewLike$class.tail(this);
            }

            public Seq<A> thisSeq() {
                return ViewMkString$class.thisSeq(this);
            }

            public String mkString() {
                return ViewMkString$class.mkString(this);
            }

            public String mkString(String sep) {
                return ViewMkString$class.mkString(this, sep);
            }

            public String mkString(String start, String sep, String end) {
                return ViewMkString$class.mkString(this, start, sep, end);
            }

            public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
                return ViewMkString$class.addString(this, b, start, sep, end);
            }

            public GenericCompanion<Seq> companion() {
                return Seq$class.companion(this);
            }

            public Seq<A> seq() {
                return Seq$class.seq(this);
            }

            public Seq<A> thisCollection() {
                return SeqLike$class.thisCollection(this);
            }

            public Seq toCollection(Object repr) {
                return SeqLike$class.toCollection(this, repr);
            }

            public Combiner<A, ParSeq<A>> parCombiner() {
                return SeqLike$class.parCombiner(this);
            }

            public int lengthCompare(int len) {
                return SeqLike$class.lengthCompare(this, len);
            }

            public int size() {
                return SeqLike$class.size(this);
            }

            public int segmentLength(Function1<A, Object> p, int from2) {
                return SeqLike$class.segmentLength(this, p, from2);
            }

            public int indexWhere(Function1<A, Object> p, int from2) {
                return SeqLike$class.indexWhere(this, p, from2);
            }

            public int lastIndexWhere(Function1<A, Object> p, int end) {
                return SeqLike$class.lastIndexWhere(this, p, end);
            }

            public Iterator<A> reverseIterator() {
                return SeqLike$class.reverseIterator(this);
            }

            public <B> boolean startsWith(GenSeq<B> that, int offset) {
                return SeqLike$class.startsWith(this, that, offset);
            }

            public <B> boolean endsWith(GenSeq<B> that) {
                return SeqLike$class.endsWith(this, that);
            }

            public <B> int indexOfSlice(GenSeq<B> that) {
                return SeqLike$class.indexOfSlice(this, that);
            }

            public <B> int indexOfSlice(GenSeq<B> that, int from2) {
                return SeqLike$class.indexOfSlice(this, that, from2);
            }

            public <B> int lastIndexOfSlice(GenSeq<B> that) {
                return SeqLike$class.lastIndexOfSlice(this, that);
            }

            public <B> int lastIndexOfSlice(GenSeq<B> that, int end) {
                return SeqLike$class.lastIndexOfSlice(this, that, end);
            }

            public <B> boolean containsSlice(GenSeq<B> that) {
                return SeqLike$class.containsSlice(this, that);
            }

            public <A1> boolean contains(A1 elem) {
                return SeqLike$class.contains(this, elem);
            }

            public <B> boolean corresponds(GenSeq<B> that, Function2<A, B, Object> p) {
                return SeqLike$class.corresponds(this, that, p);
            }

            public Seq<A> toSeq() {
                return SeqLike$class.toSeq(this);
            }

            public Range indices() {
                return SeqLike$class.indices(this);
            }

            public Object view() {
                return SeqLike$class.view(this);
            }

            public SeqView<A, StreamView<A, Coll>> view(int from2, int until2) {
                return SeqLike$class.view(this, from2, until2);
            }

            public boolean isDefinedAt(int idx) {
                return GenSeqLike$class.isDefinedAt(this, idx);
            }

            public int prefixLength(Function1<A, Object> p) {
                return GenSeqLike$class.prefixLength(this, p);
            }

            public int indexWhere(Function1<A, Object> p) {
                return GenSeqLike$class.indexWhere(this, p);
            }

            public <B> int indexOf(B elem) {
                return GenSeqLike$class.indexOf(this, elem);
            }

            public <B> int indexOf(B elem, int from2) {
                return GenSeqLike$class.indexOf(this, elem, from2);
            }

            public <B> int lastIndexOf(B elem) {
                return GenSeqLike$class.lastIndexOf(this, elem);
            }

            public <B> int lastIndexOf(B elem, int end) {
                return GenSeqLike$class.lastIndexOf(this, elem, end);
            }

            public int lastIndexWhere(Function1<A, Object> p) {
                return GenSeqLike$class.lastIndexWhere(this, p);
            }

            public <B> boolean startsWith(GenSeq<B> that) {
                return GenSeqLike$class.startsWith(this, that);
            }

            public int hashCode() {
                return GenSeqLike$class.hashCode(this);
            }

            public boolean equals(Object that) {
                return GenSeqLike$class.equals(this, that);
            }

            public boolean forall(Function1<A, Object> p) {
                return IterableLike$class.forall(this, p);
            }

            public boolean exists(Function1<A, Object> p) {
                return IterableLike$class.exists(this, p);
            }

            public Option<A> find(Function1<A, Object> p) {
                return IterableLike$class.find(this, p);
            }

            public <B> B foldRight(B z, Function2<A, B, B> op) {
                return (B)IterableLike$class.foldRight(this, z, op);
            }

            public <B> B reduceRight(Function2<A, B, B> op) {
                return (B)IterableLike$class.reduceRight(this, op);
            }

            public Iterable<A> toIterable() {
                return IterableLike$class.toIterable(this);
            }

            public Iterator<A> toIterator() {
                return IterableLike$class.toIterator(this);
            }

            public A head() {
                return (A)IterableLike$class.head(this);
            }

            public <B> void copyToArray(Object xs, int start, int len) {
                IterableLike$class.copyToArray(this, xs, start, len);
            }

            public <B> boolean sameElements(GenIterable<B> that) {
                return IterableLike$class.sameElements(this, that);
            }

            public Stream<A> toStream() {
                return IterableLike$class.toStream(this);
            }

            public boolean canEqual(Object that) {
                return IterableLike$class.canEqual(this, that);
            }

            public <B> Builder<B, Seq<B>> genericBuilder() {
                return GenericTraversableTemplate$class.genericBuilder(this);
            }

            public GenTraversable transpose(Function1 asTraversable) {
                return GenericTraversableTemplate$class.transpose(this, asTraversable);
            }

            public Object repr() {
                return TraversableLike$class.repr(this);
            }

            public final boolean isTraversableAgain() {
                return TraversableLike$class.isTraversableAgain(this);
            }

            public boolean hasDefiniteSize() {
                return TraversableLike$class.hasDefiniteSize(this);
            }

            public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<StreamView<A, Coll>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<StreamView<A, Coll>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<StreamView<A, Coll>, B, That> cbf) {
                return (That)TraversableLike$class.scan(this, z, op, cbf);
            }

            public A last() {
                return (A)TraversableLike$class.last(this);
            }

            public Object sliceWithKnownDelta(int from2, int until2, int delta) {
                return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
            }

            public Object sliceWithKnownBound(int from2, int until2) {
                return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
            }

            public Traversable<A> toTraversable() {
                return TraversableLike$class.toTraversable(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, A, Col> cbf) {
                return (Col)TraversableLike$class.to(this, cbf);
            }

            public Parallel par() {
                return Parallelizable$class.par(this);
            }

            public List<A> reversed() {
                return TraversableOnce$class.reversed(this);
            }

            public boolean nonEmpty() {
                return TraversableOnce$class.nonEmpty(this);
            }

            public int count(Function1<A, Object> p) {
                return TraversableOnce$class.count(this, p);
            }

            public <B> Option<B> collectFirst(PartialFunction<A, B> pf) {
                return TraversableOnce$class.collectFirst(this, pf);
            }

            public <B> B $div$colon(B z, Function2<B, A, B> op) {
                return (B)TraversableOnce$class.$div$colon(this, z, op);
            }

            public <B> B $colon$bslash(B z, Function2<A, B, B> op) {
                return (B)TraversableOnce$class.$colon$bslash(this, z, op);
            }

            public <B> B foldLeft(B z, Function2<B, A, B> op) {
                return (B)TraversableOnce$class.foldLeft(this, z, op);
            }

            public <B> B reduceLeft(Function2<B, A, B> op) {
                return (B)TraversableOnce$class.reduceLeft(this, op);
            }

            public <B> Option<B> reduceLeftOption(Function2<B, A, B> op) {
                return TraversableOnce$class.reduceLeftOption(this, op);
            }

            public <B> Option<B> reduceRightOption(Function2<A, B, B> op) {
                return TraversableOnce$class.reduceRightOption(this, op);
            }

            public <A1> A1 reduce(Function2<A1, A1, A1> op) {
                return (A1)TraversableOnce$class.reduce(this, op);
            }

            public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
                return TraversableOnce$class.reduceOption(this, op);
            }

            public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
                return (A1)TraversableOnce$class.fold(this, z, op);
            }

            public <B> B aggregate(Function0<B> z, Function2<B, A, B> seqop, Function2<B, B, B> combop) {
                return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
            }

            public <B> B sum(Numeric<B> num) {
                return (B)TraversableOnce$class.sum(this, num);
            }

            public <B> B product(Numeric<B> num) {
                return (B)TraversableOnce$class.product(this, num);
            }

            public <B> A min(Ordering<B> cmp) {
                return (A)TraversableOnce$class.min(this, cmp);
            }

            public <B> A max(Ordering<B> cmp) {
                return (A)TraversableOnce$class.max(this, cmp);
            }

            public <B> A maxBy(Function1<A, B> f, Ordering<B> cmp) {
                return (A)TraversableOnce$class.maxBy(this, f, cmp);
            }

            public <B> A minBy(Function1<A, B> f, Ordering<B> cmp) {
                return (A)TraversableOnce$class.minBy(this, f, cmp);
            }

            public <B> void copyToBuffer(Buffer<B> dest) {
                TraversableOnce$class.copyToBuffer(this, dest);
            }

            public <B> void copyToArray(Object xs, int start) {
                TraversableOnce$class.copyToArray(this, xs, start);
            }

            public <B> void copyToArray(Object xs) {
                TraversableOnce$class.copyToArray(this, xs);
            }

            public <B> Object toArray(ClassTag<B> evidence$1) {
                return TraversableOnce$class.toArray(this, evidence$1);
            }

            public List<A> toList() {
                return TraversableOnce$class.toList(this);
            }

            public IndexedSeq<A> toIndexedSeq() {
                return TraversableOnce$class.toIndexedSeq(this);
            }

            public <B> Buffer<B> toBuffer() {
                return TraversableOnce$class.toBuffer(this);
            }

            public <B> Set<B> toSet() {
                return TraversableOnce$class.toSet(this);
            }

            public Vector<A> toVector() {
                return TraversableOnce$class.toVector(this);
            }

            public <T, U> Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> ev) {
                return TraversableOnce$class.toMap(this, ev);
            }

            public StringBuilder addString(StringBuilder b, String sep) {
                return TraversableOnce$class.addString(this, b, sep);
            }

            public StringBuilder addString(StringBuilder b) {
                return TraversableOnce$class.addString(this, b);
            }

            public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> that) {
                return PartialFunction$class.orElse(this, that);
            }

            public <C> PartialFunction<Object, C> andThen(Function1<A, C> k) {
                return PartialFunction$class.andThen(this, k);
            }

            public Function1<Object, Option<A>> lift() {
                return PartialFunction$class.lift(this);
            }

            public Object applyOrElse(Object x, Function1 function1) {
                return PartialFunction$class.applyOrElse(this, x, function1);
            }

            public <U> Function1<Object, Object> runWith(Function1<A, U> action) {
                return PartialFunction$class.runWith(this, action);
            }

            public boolean apply$mcZD$sp(double v1) {
                return Function1$class.apply$mcZD$sp(this, v1);
            }

            public double apply$mcDD$sp(double v1) {
                return Function1$class.apply$mcDD$sp(this, v1);
            }

            public float apply$mcFD$sp(double v1) {
                return Function1$class.apply$mcFD$sp(this, v1);
            }

            public int apply$mcID$sp(double v1) {
                return Function1$class.apply$mcID$sp(this, v1);
            }

            public long apply$mcJD$sp(double v1) {
                return Function1$class.apply$mcJD$sp(this, v1);
            }

            public void apply$mcVD$sp(double v1) {
                Function1$class.apply$mcVD$sp(this, v1);
            }

            public boolean apply$mcZF$sp(float v1) {
                return Function1$class.apply$mcZF$sp(this, v1);
            }

            public double apply$mcDF$sp(float v1) {
                return Function1$class.apply$mcDF$sp(this, v1);
            }

            public float apply$mcFF$sp(float v1) {
                return Function1$class.apply$mcFF$sp(this, v1);
            }

            public int apply$mcIF$sp(float v1) {
                return Function1$class.apply$mcIF$sp(this, v1);
            }

            public long apply$mcJF$sp(float v1) {
                return Function1$class.apply$mcJF$sp(this, v1);
            }

            public void apply$mcVF$sp(float v1) {
                Function1$class.apply$mcVF$sp(this, v1);
            }

            public boolean apply$mcZI$sp(int v1) {
                return Function1$class.apply$mcZI$sp(this, v1);
            }

            public double apply$mcDI$sp(int v1) {
                return Function1$class.apply$mcDI$sp(this, v1);
            }

            public float apply$mcFI$sp(int v1) {
                return Function1$class.apply$mcFI$sp(this, v1);
            }

            public int apply$mcII$sp(int v1) {
                return Function1$class.apply$mcII$sp(this, v1);
            }

            public long apply$mcJI$sp(int v1) {
                return Function1$class.apply$mcJI$sp(this, v1);
            }

            public void apply$mcVI$sp(int v1) {
                Function1$class.apply$mcVI$sp(this, v1);
            }

            public boolean apply$mcZJ$sp(long v1) {
                return Function1$class.apply$mcZJ$sp(this, v1);
            }

            public double apply$mcDJ$sp(long v1) {
                return Function1$class.apply$mcDJ$sp(this, v1);
            }

            public float apply$mcFJ$sp(long v1) {
                return Function1$class.apply$mcFJ$sp(this, v1);
            }

            public int apply$mcIJ$sp(long v1) {
                return Function1$class.apply$mcIJ$sp(this, v1);
            }

            public long apply$mcJJ$sp(long v1) {
                return Function1$class.apply$mcJJ$sp(this, v1);
            }

            public void apply$mcVJ$sp(long v1) {
                Function1$class.apply$mcVJ$sp(this, v1);
            }

            public <A> Function1<A, A> compose(Function1<A, Object> g) {
                return Function1$class.compose(this, g);
            }

            public /* synthetic */ StreamViewLike scala$collection$immutable$StreamViewLike$Transformed$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Reversed$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Transformed$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Transformed$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Transformed$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                Function1$class.$init$(this);
                PartialFunction$class.$init$(this);
                TraversableOnce$class.$init$(this);
                Parallelizable$class.$init$(this);
                TraversableLike$class.$init$(this);
                GenericTraversableTemplate$class.$init$(this);
                GenTraversable$class.$init$(this);
                Traversable$class.$init$(this);
                GenIterable$class.$init$(this);
                IterableLike$class.$init$(this);
                Iterable$class.$init$(this);
                GenSeqLike$class.$init$(this);
                GenSeq$class.$init$(this);
                SeqLike$class.$init$(this);
                Seq$class.$init$(this);
                ViewMkString$class.$init$(this);
                TraversableViewLike$class.$init$(this);
                IterableViewLike$class.$init$(this);
                SeqViewLike$class.$init$(this);
                TraversableViewLike$Transformed$class.$init$(this);
                IterableViewLike$Transformed$class.$init$(this);
                SeqViewLike$Transformed$class.$init$(this);
                SeqViewLike$Reversed$class.$init$(this);
                StreamViewLike$class.$init$(this);
                StreamViewLike$Transformed$class.$init$(this);
            }
        };
    }

    public static StreamViewLike.Transformed newPatched(StreamViewLike $this, int _from, GenSeq _patch, int _replaced) {
        return new StreamViewLike.Patched<B>($this, _from, _patch, _replaced){
            private final int from;
            private final GenSeq<B> patch;
            private final int replaced;
            private final /* synthetic */ StreamViewLike $outer;
            private final int scala$collection$SeqViewLike$Patched$$plen;
            private volatile boolean bitmap$0;

            private int scala$collection$SeqViewLike$Patched$$plen$lzycompute() {
                StreamViewLike$.anon.11 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.scala$collection$SeqViewLike$Patched$$plen = SeqViewLike$Patched$class.scala$collection$SeqViewLike$Patched$$plen(this);
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.scala$collection$SeqViewLike$Patched$$plen;
                }
            }

            public int scala$collection$SeqViewLike$Patched$$plen() {
                return this.bitmap$0 ? this.scala$collection$SeqViewLike$Patched$$plen : this.scala$collection$SeqViewLike$Patched$$plen$lzycompute();
            }

            public Iterator<B> iterator() {
                return SeqViewLike$Patched$class.iterator(this);
            }

            public int length() {
                return SeqViewLike$Patched$class.length(this);
            }

            public B apply(int idx) {
                return (B)SeqViewLike$Patched$class.apply(this, idx);
            }

            public final String viewIdentifier() {
                return SeqViewLike$Patched$class.viewIdentifier(this);
            }

            public int from() {
                return this.from;
            }

            public GenSeq<B> patch() {
                return this.patch;
            }

            public int replaced() {
                return this.replaced;
            }

            public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Patched$$$outer() {
                return this.$outer;
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.from = _from$1;
                this.patch = var3_3;
                this.replaced = _replaced$1;
                super($outer);
                SeqViewLike$Patched$class.$init$(this);
            }
        };
    }

    public static StreamViewLike.Transformed newPrepended(StreamViewLike $this, Object elem) {
        return new StreamViewLike.Prepended<B>($this, elem){
            private final B fst;
            private final /* synthetic */ StreamViewLike $outer;

            public Iterator<B> iterator() {
                return SeqViewLike$Prepended$class.iterator(this);
            }

            public int length() {
                return SeqViewLike$Prepended$class.length(this);
            }

            public B apply(int idx) {
                return (B)SeqViewLike$Prepended$class.apply(this, idx);
            }

            public final String viewIdentifier() {
                return SeqViewLike$Prepended$class.viewIdentifier(this);
            }

            public B fst() {
                return this.fst;
            }

            public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Prepended$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.fst = elem$1;
                super($outer);
                SeqViewLike$Prepended$class.$init$(this);
            }
        };
    }

    public static String stringPrefix(StreamViewLike $this) {
        return "StreamView";
    }

    public static void $init$(StreamViewLike $this) {
    }
}

