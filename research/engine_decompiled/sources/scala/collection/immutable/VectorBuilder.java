/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function1;
import scala.collection.Seq;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.immutable.Vector;
import scala.collection.immutable.Vector$;
import scala.collection.immutable.VectorPointer;
import scala.collection.immutable.VectorPointer$class;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\t4A!\u0001\u0002\u0003\u0013\tia+Z2u_J\u0014U/\u001b7eKJT!a\u0001\u0003\u0002\u0013%lW.\u001e;bE2,'BA\u0003\u0007\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u000f\u0005)1oY1mC\u000e\u0001QC\u0001\u0006\u0018'\u0011\u00011b\u0004\u0013\u0011\u00051iQ\"\u0001\u0004\n\u000591!AB!osJ+g\r\u0005\u0003\u0011'U\u0001S\"A\t\u000b\u0005I!\u0011aB7vi\u0006\u0014G.Z\u0005\u0003)E\u0011qAQ;jY\u0012,'\u000f\u0005\u0002\u0017/1\u0001A!\u0002\r\u0001\u0005\u0004I\"!A!\u0012\u0005ii\u0002C\u0001\u0007\u001c\u0013\tabAA\u0004O_RD\u0017N\\4\u0011\u00051q\u0012BA\u0010\u0007\u0005\r\te.\u001f\t\u0004C\t*R\"\u0001\u0002\n\u0005\r\u0012!A\u0002,fGR|'\u000fE\u0002\"K\u001dJ!A\n\u0002\u0003\u001bY+7\r^8s!>Lg\u000e^3sU\t)\u0002fK\u0001*!\tQs&D\u0001,\u0015\taS&A\u0005v]\u000eDWmY6fI*\u0011aFB\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001\u0019,\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0005\u0006e\u0001!\taM\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003Q\u00022!\t\u0001\u0016\u0011\u001d1\u0004\u00011A\u0005\n]\n!B\u00197pG.Le\u000eZ3y+\u0005A\u0004C\u0001\u0007:\u0013\tQdAA\u0002J]RDq\u0001\u0010\u0001A\u0002\u0013%Q(\u0001\bcY>\u001c7.\u00138eKb|F%Z9\u0015\u0005y\n\u0005C\u0001\u0007@\u0013\t\u0001eA\u0001\u0003V]&$\bb\u0002\"<\u0003\u0003\u0005\r\u0001O\u0001\u0004q\u0012\n\u0004B\u0002#\u0001A\u0003&\u0001(A\u0006cY>\u001c7.\u00138eKb\u0004\u0003b\u0002$\u0001\u0001\u0004%IaN\u0001\u0003Y>Dq\u0001\u0013\u0001A\u0002\u0013%\u0011*\u0001\u0004m_~#S-\u001d\u000b\u0003})CqAQ$\u0002\u0002\u0003\u0007\u0001\b\u0003\u0004M\u0001\u0001\u0006K\u0001O\u0001\u0004Y>\u0004\u0003\"\u0002(\u0001\t\u0003y\u0015\u0001\u0003\u0013qYV\u001cH%Z9\u0015\u0005A\u000bV\"\u0001\u0001\t\u000bIk\u0005\u0019A\u000b\u0002\t\u0015dW-\u001c\u0005\u0006)\u0002!\t%V\u0001\u000eIAdWo\u001d\u0013qYV\u001cH%Z9\u0015\u0005A3\u0006\"B,T\u0001\u0004A\u0016A\u0001=t!\rI&,F\u0007\u0002\t%\u00111\f\u0002\u0002\u0010)J\fg/\u001a:tC\ndWm\u00148dK\")Q\f\u0001C\u0001=\u00061!/Z:vYR$\u0012\u0001\t\u0005\u0006A\u0002!\t!Y\u0001\u0006G2,\u0017M\u001d\u000b\u0002}\u0001")
public final class VectorBuilder<A>
implements Builder<A, Vector<A>>,
VectorPointer<A> {
    private int blockIndex;
    private int lo;
    private int depth;
    private Object[] display0;
    private Object[] display1;
    private Object[] display2;
    private Object[] display3;
    private Object[] display4;
    private Object[] display5;

    @Override
    public int depth() {
        return this.depth;
    }

    @Override
    public void depth_$eq(int x$1) {
        this.depth = x$1;
    }

    @Override
    public Object[] display0() {
        return this.display0;
    }

    @Override
    public void display0_$eq(Object[] x$1) {
        this.display0 = x$1;
    }

    @Override
    public Object[] display1() {
        return this.display1;
    }

    @Override
    public void display1_$eq(Object[] x$1) {
        this.display1 = x$1;
    }

    @Override
    public Object[] display2() {
        return this.display2;
    }

    @Override
    public void display2_$eq(Object[] x$1) {
        this.display2 = x$1;
    }

    @Override
    public Object[] display3() {
        return this.display3;
    }

    @Override
    public void display3_$eq(Object[] x$1) {
        this.display3 = x$1;
    }

    @Override
    public Object[] display4() {
        return this.display4;
    }

    @Override
    public void display4_$eq(Object[] x$1) {
        this.display4 = x$1;
    }

    @Override
    public Object[] display5() {
        return this.display5;
    }

    @Override
    public void display5_$eq(Object[] x$1) {
        this.display5 = x$1;
    }

    @Override
    public final <U> void initFrom(VectorPointer<U> that) {
        VectorPointer$class.initFrom(this, that);
    }

    @Override
    public final <U> void initFrom(VectorPointer<U> that, int depth) {
        VectorPointer$class.initFrom(this, that, depth);
    }

    @Override
    public final A getElem(int index, int xor) {
        return (A)VectorPointer$class.getElem(this, index, xor);
    }

    @Override
    public final void gotoPos(int index, int xor) {
        VectorPointer$class.gotoPos(this, index, xor);
    }

    @Override
    public final void gotoNextBlockStart(int index, int xor) {
        VectorPointer$class.gotoNextBlockStart(this, index, xor);
    }

    @Override
    public final void gotoNextBlockStartWritable(int index, int xor) {
        VectorPointer$class.gotoNextBlockStartWritable(this, index, xor);
    }

    @Override
    public final Object[] copyOf(Object[] a) {
        return VectorPointer$class.copyOf(this, a);
    }

    @Override
    public final Object[] nullSlotAndCopy(Object[] array, int index) {
        return VectorPointer$class.nullSlotAndCopy(this, array, index);
    }

    @Override
    public final void stabilize(int index) {
        VectorPointer$class.stabilize(this, index);
    }

    @Override
    public final void gotoPosWritable0(int newIndex, int xor) {
        VectorPointer$class.gotoPosWritable0(this, newIndex, xor);
    }

    @Override
    public final void gotoPosWritable1(int oldIndex, int newIndex, int xor) {
        VectorPointer$class.gotoPosWritable1(this, oldIndex, newIndex, xor);
    }

    @Override
    public final Object[] copyRange(Object[] array, int oldLeft, int newLeft) {
        return VectorPointer$class.copyRange(this, array, oldLeft, newLeft);
    }

    @Override
    public final void gotoFreshPosWritable0(int oldIndex, int newIndex, int xor) {
        VectorPointer$class.gotoFreshPosWritable0(this, oldIndex, newIndex, xor);
    }

    @Override
    public final void gotoFreshPosWritable1(int oldIndex, int newIndex, int xor) {
        VectorPointer$class.gotoFreshPosWritable1(this, oldIndex, newIndex, xor);
    }

    @Override
    public void debug() {
        VectorPointer$class.debug(this);
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
    public <NewTo> Builder<A, NewTo> mapResult(Function1<Vector<A>, NewTo> f) {
        return Builder$class.mapResult(this, f);
    }

    @Override
    public Growable<A> $plus$eq(A elem1, A elem2, Seq<A> elems) {
        return Growable$class.$plus$eq(this, elem1, elem2, elems);
    }

    private int blockIndex() {
        return this.blockIndex;
    }

    private void blockIndex_$eq(int x$1) {
        this.blockIndex = x$1;
    }

    private int lo() {
        return this.lo;
    }

    private void lo_$eq(int x$1) {
        this.lo = x$1;
    }

    @Override
    public VectorBuilder<A> $plus$eq(A elem) {
        if (this.lo() >= this.display0().length) {
            int newBlockIndex = this.blockIndex() + 32;
            this.gotoNextBlockStartWritable(newBlockIndex, this.blockIndex() ^ newBlockIndex);
            this.blockIndex_$eq(newBlockIndex);
            this.lo_$eq(0);
        }
        this.display0()[this.lo()] = elem;
        this.lo_$eq(this.lo() + 1);
        return this;
    }

    @Override
    public VectorBuilder<A> $plus$plus$eq(TraversableOnce<A> xs) {
        return (VectorBuilder)Growable$class.$plus$plus$eq(this, xs);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public Vector<A> result() {
        void var2_2;
        int size2 = this.blockIndex() + this.lo();
        if (size2 == 0) {
            return Vector$.MODULE$.empty();
        }
        Vector s2 = new Vector(0, size2, 0);
        s2.initFrom(this);
        if (this.depth() > 1) {
            s2.gotoPos(0, size2 - 1);
        }
        return var2_2;
    }

    @Override
    public void clear() {
        this.display0_$eq(new Object[32]);
        this.depth_$eq(1);
        this.blockIndex_$eq(0);
        this.lo_$eq(0);
    }

    public VectorBuilder() {
        Growable$class.$init$(this);
        Builder$class.$init$(this);
        VectorPointer$class.$init$(this);
        this.display0_$eq(new Object[32]);
        this.depth_$eq(1);
        this.blockIndex = 0;
        this.lo = 0;
    }
}

