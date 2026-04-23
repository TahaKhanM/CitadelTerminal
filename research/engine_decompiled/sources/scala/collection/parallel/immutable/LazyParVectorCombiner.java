/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.Function1;
import scala.Serializable;
import scala.collection.Seq;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.immutable.VectorBuilder;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.Combiner$class;
import scala.collection.parallel.TaskSupport;
import scala.collection.parallel.immutable.ParVector;
import scala.reflect.ScalaSignature;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u00154Q!\u0001\u0002\u0001\u0005)\u0011Q\u0003T1{sB\u000b'OV3di>\u00148i\\7cS:,'O\u0003\u0002\u0004\t\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003\u000b\u0019\t\u0001\u0002]1sC2dW\r\u001c\u0006\u0003\u000f!\t!bY8mY\u0016\u001cG/[8o\u0015\u0005I\u0011!B:dC2\fWCA\u0006\u0017'\r\u0001A\u0002\u0005\t\u0003\u001b9i\u0011\u0001C\u0005\u0003\u001f!\u0011a!\u00118z%\u00164\u0007\u0003B\t\u0013)\u0001j\u0011\u0001B\u0005\u0003'\u0011\u0011\u0001bQ8nE&tWM\u001d\t\u0003+Ya\u0001\u0001B\u0003\u0018\u0001\t\u0007\u0011DA\u0001U\u0007\u0001\t\"AG\u000f\u0011\u00055Y\u0012B\u0001\u000f\t\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0004\u0010\n\u0005}A!aA!osB\u0019\u0011E\t\u000b\u000e\u0003\tI!a\t\u0002\u0003\u0013A\u000b'OV3di>\u0014\b\"B\u0013\u0001\t\u00031\u0013A\u0002\u001fj]&$h\bF\u0001(!\r\t\u0003\u0001\u0006\u0005\bS\u0001\u0001\r\u0011\"\u0001+\u0003\t\u0019(0F\u0001,!\tiA&\u0003\u0002.\u0011\t\u0019\u0011J\u001c;\t\u000f=\u0002\u0001\u0019!C\u0001a\u000511O_0%KF$\"!\r\u001b\u0011\u00055\u0011\u0014BA\u001a\t\u0005\u0011)f.\u001b;\t\u000fUr\u0013\u0011!a\u0001W\u0005\u0019\u0001\u0010J\u0019\t\r]\u0002\u0001\u0015)\u0003,\u0003\r\u0019(\u0010\t\u0005\bs\u0001\u0011\r\u0011\"\u0001;\u0003\u001d1Xm\u0019;peN,\u0012a\u000f\t\u0004y}\nU\"A\u001f\u000b\u0005y2\u0011aB7vi\u0006\u0014G.Z\u0005\u0003\u0001v\u00121\"\u0011:sCf\u0014UO\u001a4feB\u0019!\t\u0012\u000b\u000e\u0003\rS!a\u0001\u0004\n\u0005\u0015\u001b%!\u0004,fGR|'OQ;jY\u0012,'\u000f\u0003\u0004H\u0001\u0001\u0006IaO\u0001\tm\u0016\u001cGo\u001c:tA!)\u0011\n\u0001C\u0001U\u0005!1/\u001b>f\u0011\u0015Y\u0005\u0001\"\u0001M\u0003!!\u0003\u000f\\;tI\u0015\fHCA'O\u001b\u0005\u0001\u0001\"B(K\u0001\u0004!\u0012\u0001B3mK6DQ!\u0015\u0001\u0005\u0002I\u000bQa\u00197fCJ$\u0012!\r\u0005\u0006)\u0002!\t!V\u0001\u0007e\u0016\u001cX\u000f\u001c;\u0015\u0003\u0001BQa\u0016\u0001\u0005\u0002a\u000bqaY8nE&tW-F\u0002Z9\u0002$\"AW2\u0011\tE\u00112l\u0018\t\u0003+q#Q!\u0018,C\u0002y\u0013\u0011!V\t\u00035Q\u0001\"!\u00061\u0005\u000b\u00054&\u0019\u00012\u0003\u000b9+w\u000fV8\u0012\u0005\u0001j\u0002\"\u00023W\u0001\u0004Q\u0016!B8uQ\u0016\u0014\b")
public class LazyParVectorCombiner<T>
implements Combiner<T, ParVector<T>> {
    private int sz;
    private final ArrayBuffer<VectorBuilder<T>> vectors;
    private volatile transient TaskSupport _combinerTaskSupport;

    @Override
    public TaskSupport _combinerTaskSupport() {
        return this._combinerTaskSupport;
    }

    @Override
    @TraitSetter
    public void _combinerTaskSupport_$eq(TaskSupport x$1) {
        this._combinerTaskSupport = x$1;
    }

    @Override
    public TaskSupport combinerTaskSupport() {
        return Combiner$class.combinerTaskSupport(this);
    }

    @Override
    public void combinerTaskSupport_$eq(TaskSupport cts) {
        Combiner$class.combinerTaskSupport_$eq(this, cts);
    }

    @Override
    public boolean canBeShared() {
        return Combiner$class.canBeShared(this);
    }

    @Override
    public Object resultWithTaskSupport() {
        return Combiner$class.resultWithTaskSupport(this);
    }

    @Override
    public void sizeHint(int size2) {
        Builder$class.sizeHint((Builder)this, size2);
    }

    @Override
    public void sizeHint(TraversableLike<?, ?> coll) {
        Builder$class.sizeHint((Builder)this, coll);
    }

    @Override
    public void sizeHint(TraversableLike<?, ?> coll, int delta) {
        Builder$class.sizeHint(this, coll, delta);
    }

    @Override
    public void sizeHintBounded(int size2, TraversableLike<?, ?> boundingColl) {
        Builder$class.sizeHintBounded(this, size2, boundingColl);
    }

    @Override
    public <NewTo> Builder<T, NewTo> mapResult(Function1<ParVector<T>, NewTo> f) {
        return Builder$class.mapResult(this, f);
    }

    @Override
    public Growable<T> $plus$eq(T elem1, T elem2, Seq<T> elems) {
        return Growable$class.$plus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Growable<T> $plus$plus$eq(TraversableOnce<T> xs) {
        return Growable$class.$plus$plus$eq(this, xs);
    }

    public int sz() {
        return this.sz;
    }

    public void sz_$eq(int x$1) {
        this.sz = x$1;
    }

    public ArrayBuffer<VectorBuilder<T>> vectors() {
        return this.vectors;
    }

    @Override
    public int size() {
        return this.sz();
    }

    @Override
    public LazyParVectorCombiner<T> $plus$eq(T elem) {
        this.vectors().last().$plus$eq((Object)elem);
        this.sz_$eq(this.sz() + 1);
        return this;
    }

    @Override
    public void clear() {
        this.vectors().clear();
        this.vectors().$plus$eq((Object)new VectorBuilder());
        this.sz_$eq(0);
    }

    @Override
    public ParVector<T> result() {
        VectorBuilder rvb = new VectorBuilder();
        this.vectors().foreach(new Serializable(this, rvb){
            public static final long serialVersionUID = 0L;
            private final VectorBuilder rvb$1;

            public final VectorBuilder<T> apply(VectorBuilder<T> vb) {
                return this.rvb$1.$plus$plus$eq((TraversableOnce)vb.result());
            }
            {
                this.rvb$1 = rvb$1;
            }
        });
        return new ParVector(rvb.result());
    }

    @Override
    public <U extends T, NewTo> Combiner<U, NewTo> combine(Combiner<U, NewTo> other) {
        LazyParVectorCombiner lazyParVectorCombiner;
        if (other == this) {
            lazyParVectorCombiner = this;
        } else {
            LazyParVectorCombiner that = (LazyParVectorCombiner)other;
            this.sz_$eq(this.sz() + that.sz());
            this.vectors().$plus$plus$eq(that.vectors());
            lazyParVectorCombiner = this;
        }
        return lazyParVectorCombiner;
    }

    public LazyParVectorCombiner() {
        Growable$class.$init$(this);
        Builder$class.$init$(this);
        Combiner$class.$init$(this);
        this.sz = 0;
        this.vectors = new ArrayBuffer().$plus$eq(new VectorBuilder());
    }
}

