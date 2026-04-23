/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.GenTraversable;
import scala.collection.GenTraversableOnce;
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
import scala.collection.Iterator$;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.SeqView;
import scala.collection.SeqViewLike;
import scala.collection.SeqViewLike$;
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
import scala.collection.SeqViewLike$Zipped$class;
import scala.collection.SeqViewLike$ZippedAll$class;
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
import scala.collection.immutable.List$;
import scala.math.Ordering;
import scala.math.package$;

public abstract class SeqViewLike$class {
    public static SeqViewLike.Transformed newForced(SeqViewLike $this, Function0 xs) {
        return new SeqViewLike.Forced<B>($this, xs){
            private final GenSeq<B> forced;
            private final /* synthetic */ SeqViewLike $outer;

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

    public static SeqViewLike.Transformed newAppended(SeqViewLike $this, GenTraversable that) {
        return new SeqViewLike.Appended<B>($this, that){
            private final GenTraversable<B> rest;
            private final /* synthetic */ SeqViewLike $outer;
            private final GenSeq<B> restSeq;
            private volatile boolean bitmap$0;

            private GenSeq restSeq$lzycompute() {
                SeqViewLike$.anon.2 var1_1 = this;
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

    public static SeqViewLike.Transformed newMapped(SeqViewLike $this, Function1 f) {
        return new SeqViewLike.Mapped<B>($this, f){
            private final Function1<A, B> mapping;
            private final /* synthetic */ SeqViewLike $outer;

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

    public static SeqViewLike.Transformed newFlatMapped(SeqViewLike $this, Function1 f) {
        return new SeqViewLike.FlatMapped<B>($this, f){
            private final Function1<A, GenTraversableOnce<B>> mapping;
            private final /* synthetic */ SeqViewLike $outer;
            private final int[] index;
            private volatile boolean bitmap$0;

            private int[] index$lzycompute() {
                SeqViewLike$.anon.4 var1_1 = this;
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

    public static SeqViewLike.Transformed newFiltered(SeqViewLike $this, Function1 p) {
        return new SeqViewLike.Filtered($this, p){
            private final Function1<A, Object> pred;
            private final /* synthetic */ SeqViewLike $outer;
            private final int[] index;
            private volatile boolean bitmap$0;

            private int[] index$lzycompute() {
                SeqViewLike$.anon.5 var1_1 = this;
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

            public A apply(int idx) {
                return (A)SeqViewLike$Filtered$class.apply(this, idx);
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

    public static SeqViewLike.Transformed newSliced(SeqViewLike $this, SliceInterval _endpoints) {
        return new SeqViewLike.Sliced($this, _endpoints){
            private final SliceInterval endpoints;
            private final /* synthetic */ SeqViewLike $outer;

            public int length() {
                return SeqViewLike$Sliced$class.length(this);
            }

            public A apply(int idx) {
                return (A)SeqViewLike$Sliced$class.apply(this, idx);
            }

            public <U> void foreach(Function1<A, U> f) {
                SeqViewLike$Sliced$class.foreach(this, f);
            }

            public Iterator<A> iterator() {
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

    public static SeqViewLike.Transformed newDroppedWhile(SeqViewLike $this, Function1 p) {
        return new SeqViewLike.DroppedWhile($this, p){
            private final Function1<A, Object> pred;
            private final /* synthetic */ SeqViewLike $outer;
            private final int start;
            private volatile boolean bitmap$0;

            private int start$lzycompute() {
                SeqViewLike$.anon.7 var1_1 = this;
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

            public A apply(int idx) {
                return (A)SeqViewLike$DroppedWhile$class.apply(this, idx);
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

    public static SeqViewLike.Transformed newTakenWhile(SeqViewLike $this, Function1 p) {
        return new SeqViewLike.TakenWhile($this, p){
            private final Function1<A, Object> pred;
            private final /* synthetic */ SeqViewLike $outer;
            private final int len;
            private volatile boolean bitmap$0;

            private int len$lzycompute() {
                SeqViewLike$.anon.8 var1_1 = this;
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

            public A apply(int idx) {
                return (A)SeqViewLike$TakenWhile$class.apply(this, idx);
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

    public static SeqViewLike.Transformed newZipped(SeqViewLike $this, GenIterable that) {
        return new SeqViewLike.Zipped<B>($this, that){
            private final GenIterable<B> other;
            private final /* synthetic */ SeqViewLike $outer;
            private final Seq<B> thatSeq;
            private volatile boolean bitmap$0;

            private Seq thatSeq$lzycompute() {
                SeqViewLike$.anon.9 var1_1 = this;
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

    public static SeqViewLike.Transformed newZippedAll(SeqViewLike $this, GenIterable that, Object _thisElem, Object _thatElem) {
        return new SeqViewLike.ZippedAll<A1, B>($this, that, _thisElem, _thatElem){
            private final GenIterable<B> other;
            private final A1 thisElem;
            private final B thatElem;
            private final /* synthetic */ SeqViewLike $outer;
            private final Seq<B> thatSeq;
            private volatile boolean bitmap$0;

            private Seq thatSeq$lzycompute() {
                SeqViewLike$.anon.10 var1_1 = this;
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

    public static SeqViewLike.Transformed newReversed(SeqViewLike $this) {
        return new SeqViewLike.Reversed($this){
            private final /* synthetic */ SeqViewLike $outer;

            public Iterator<A> iterator() {
                return SeqViewLike$Reversed$class.iterator(this);
            }

            public int length() {
                return SeqViewLike$Reversed$class.length(this);
            }

            public A apply(int idx) {
                return (A)SeqViewLike$Reversed$class.apply(this, idx);
            }

            public final String viewIdentifier() {
                return SeqViewLike$Reversed$class.viewIdentifier(this);
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
            }
        };
    }

    public static SeqViewLike.Transformed newPatched(SeqViewLike $this, int _from, GenSeq _patch, int _replaced) {
        return new SeqViewLike.Patched<B>($this, _from, _patch, _replaced){
            private final int from;
            private final GenSeq<B> patch;
            private final int replaced;
            private final /* synthetic */ SeqViewLike $outer;
            private final int scala$collection$SeqViewLike$Patched$$plen;
            private volatile boolean bitmap$0;

            private int scala$collection$SeqViewLike$Patched$$plen$lzycompute() {
                SeqViewLike$.anon.12 var1_1 = this;
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

    public static SeqViewLike.Transformed newPrepended(SeqViewLike $this, Object elem) {
        return new SeqViewLike.Prepended<B>($this, elem){
            private final B fst;
            private final /* synthetic */ SeqViewLike $outer;

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

    public static SeqViewLike.Transformed newTaken(SeqViewLike $this, int n) {
        return $this.newSliced(SliceInterval$.MODULE$.apply(0, n));
    }

    public static SeqViewLike.Transformed newDropped(SeqViewLike $this, int n) {
        return $this.newSliced(SliceInterval$.MODULE$.apply(n, Integer.MAX_VALUE));
    }

    public static SeqView reverse(SeqViewLike $this) {
        return $this.newReversed();
    }

    public static Object patch(SeqViewLike $this, int from2, GenSeq patch2, int replaced, CanBuildFrom bf) {
        int nonNegFrom = package$.MODULE$.max(0, from2);
        int nonNegRep = package$.MODULE$.max(0, replaced);
        return $this.newPatched(nonNegFrom, patch2, nonNegRep);
    }

    public static Object padTo(SeqViewLike $this, int len, Object elem, CanBuildFrom bf) {
        return $this.patch($this.length(), (GenSeq)Seq$.MODULE$.fill(len - $this.length(), new Serializable($this, elem){
            public static final long serialVersionUID = 0L;
            private final Object elem$2;

            public final B apply() {
                return (B)this.elem$2;
            }
            {
                this.elem$2 = elem$2;
            }
        }), 0, bf);
    }

    public static Object reverseMap(SeqViewLike $this, Function1 f, CanBuildFrom bf) {
        return $this.reverse().map(f, bf);
    }

    public static Object updated(SeqViewLike $this, int index, Object elem, CanBuildFrom bf) {
        Predef$.MODULE$.require(0 <= index && index < $this.length());
        return $this.patch(index, List$.MODULE$.apply((Seq)Predef$.MODULE$.genericWrapArray(new Object[]{elem})), 1, bf);
    }

    public static Object $plus$colon(SeqViewLike $this, Object elem, CanBuildFrom bf) {
        return $this.newPrepended(elem);
    }

    public static Object $colon$plus(SeqViewLike $this, Object elem, CanBuildFrom bf) {
        return $this.$plus$plus(Iterator$.MODULE$.single(elem), bf);
    }

    public static Object union(SeqViewLike $this, GenSeq that, CanBuildFrom bf) {
        return $this.newForced(new Serializable($this, that){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SeqViewLike $outer;
            private final GenSeq that$4;

            public final Seq<B> apply() {
                return this.$outer.thisSeq().union(this.that$4, Seq$.MODULE$.canBuildFrom());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.that$4 = that$4;
            }
        });
    }

    public static SeqView diff(SeqViewLike $this, GenSeq that) {
        return $this.newForced(new Serializable($this, that){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SeqViewLike $outer;
            private final GenSeq that$5;

            public final Seq<A> apply() {
                return (Seq)this.$outer.thisSeq().diff(this.that$5);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.that$5 = that$5;
            }
        });
    }

    public static SeqView intersect(SeqViewLike $this, GenSeq that) {
        return $this.newForced(new Serializable($this, that){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SeqViewLike $outer;
            private final GenSeq that$6;

            public final Seq<A> apply() {
                return (Seq)this.$outer.thisSeq().intersect(this.that$6);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.that$6 = that$6;
            }
        });
    }

    public static SeqView sorted(SeqViewLike $this, Ordering ord) {
        return $this.newForced(new Serializable($this, ord){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SeqViewLike $outer;
            private final Ordering ord$1;

            public final Seq<A> apply() {
                return (Seq)this.$outer.thisSeq().sorted(this.ord$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.ord$1 = ord$1;
            }
        });
    }

    public static SeqView sortWith(SeqViewLike $this, Function2 lt) {
        return $this.newForced(new Serializable($this, lt){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SeqViewLike $outer;
            private final Function2 lt$1;

            public final Seq<A> apply() {
                return (Seq)this.$outer.thisSeq().sortWith(this.lt$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.lt$1 = lt$1;
            }
        });
    }

    public static SeqView sortBy(SeqViewLike $this, Function1 f, Ordering ord) {
        return $this.newForced(new Serializable($this, f, ord){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SeqViewLike $outer;
            private final Function1 f$3;
            private final Ordering ord$2;

            public final Seq<A> apply() {
                return (Seq)this.$outer.thisSeq().sortBy(this.f$3, this.ord$2);
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.f$3 = f$3;
                this.ord$2 = var3_3;
            }
        });
    }

    public static Iterator combinations(SeqViewLike $this, int n) {
        return $this.thisSeq().combinations(n).map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SeqViewLike $outer;

            public final This apply(Seq<A> as) {
                return (This)this.$outer.newForced(new Serializable(this, as){
                    public static final long serialVersionUID = 0L;
                    private final Seq as$1;

                    public final Seq<A> apply() {
                        return this.as$1;
                    }
                    {
                        this.as$1 = as$1;
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

    public static Iterator permutations(SeqViewLike $this) {
        return $this.thisSeq().permutations().map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SeqViewLike $outer;

            public final This apply(Seq<A> as) {
                return (This)this.$outer.newForced(new Serializable(this, as){
                    public static final long serialVersionUID = 0L;
                    private final Seq as$2;

                    public final Seq<A> apply() {
                        return this.as$2;
                    }
                    {
                        this.as$2 = as$2;
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

    public static SeqView distinct(SeqViewLike $this) {
        return $this.newForced(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SeqViewLike $outer;

            public final Seq<A> apply() {
                return (Seq)this.$outer.thisSeq().distinct();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static String stringPrefix(SeqViewLike $this) {
        return "SeqView";
    }

    public static void $init$(SeqViewLike $this) {
    }
}

