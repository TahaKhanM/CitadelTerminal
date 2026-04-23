/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import java.util.NoSuchElementException;
import scala.Predef$;
import scala.collection.AbstractIterator;
import scala.collection.immutable.Vector;
import scala.collection.immutable.VectorPointer;
import scala.collection.immutable.VectorPointer$class;
import scala.math.package$;
import scala.reflect.ScalaSignature;
import scala.runtime.RichInt$;

@ScalaSignature(bytes="\u0006\u0001=4A!\u0001\u0002\u0001\u0013\tqa+Z2u_JLE/\u001a:bi>\u0014(BA\u0002\u0005\u0003%IW.\\;uC\ndWM\u0003\u0002\u0006\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001!\u0006\u0002\u000b#M!\u0001aC\u000e\u001f!\raQbD\u0007\u0002\t%\u0011a\u0002\u0002\u0002\u0011\u0003\n\u001cHO]1di&#XM]1u_J\u0004\"\u0001E\t\r\u0001\u00111!\u0003\u0001CC\u0002M\u0011\u0011!Q\t\u0003)a\u0001\"!\u0006\f\u000e\u0003\u0019I!a\u0006\u0004\u0003\u000f9{G\u000f[5oOB\u0011Q#G\u0005\u00035\u0019\u00111!\u00118z!\raAdD\u0005\u0003;\u0011\u0011\u0001\"\u0013;fe\u0006$xN\u001d\t\u0004?\u0001\u0012S\"\u0001\u0002\n\u0005\u0005\u0012!!\u0004,fGR|'\u000fU8j]R,'O\u000b\u0002\u0010G-\nA\u0005\u0005\u0002&U5\taE\u0003\u0002(Q\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0003S\u0019\t!\"\u00198o_R\fG/[8o\u0013\tYcEA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016D\u0001\"\f\u0001\u0003\u0002\u0003\u0006IAL\u0001\f?N$\u0018M\u001d;J]\u0012,\u0007\u0010\u0005\u0002\u0016_%\u0011\u0001G\u0002\u0002\u0004\u0013:$\b\u0002\u0003\u001a\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0018\u0002\u0011\u0015tG-\u00138eKbDQ\u0001\u000e\u0001\u0005\u0002U\na\u0001P5oSRtDc\u0001\u001c8qA\u0019q\u0004A\b\t\u000b5\u001a\u0004\u0019\u0001\u0018\t\u000bI\u001a\u0004\u0019\u0001\u0018\t\u000fi\u0002\u0001\u0019!C\u0005w\u0005Q!\r\\8dW&sG-\u001a=\u0016\u00039Bq!\u0010\u0001A\u0002\u0013%a(\u0001\bcY>\u001c7.\u00138eKb|F%Z9\u0015\u0005}\u0012\u0005CA\u000bA\u0013\t\teA\u0001\u0003V]&$\bbB\"=\u0003\u0003\u0005\rAL\u0001\u0004q\u0012\n\u0004BB#\u0001A\u0003&a&A\u0006cY>\u001c7.\u00138eKb\u0004\u0003bB$\u0001\u0001\u0004%IaO\u0001\u0003Y>Dq!\u0013\u0001A\u0002\u0013%!*\u0001\u0004m_~#S-\u001d\u000b\u0003\u007f-Cqa\u0011%\u0002\u0002\u0003\u0007a\u0006\u0003\u0004N\u0001\u0001\u0006KAL\u0001\u0004Y>\u0004\u0003bB(\u0001\u0001\u0004%IaO\u0001\u0006K:$Gj\u001c\u0005\b#\u0002\u0001\r\u0011\"\u0003S\u0003%)g\u000e\u001a'p?\u0012*\u0017\u000f\u0006\u0002@'\"91\tUA\u0001\u0002\u0004q\u0003BB+\u0001A\u0003&a&\u0001\u0004f]\u0012du\u000e\t\u0005\u0006/\u0002!\t\u0001W\u0001\bQ\u0006\u001ch*\u001a=u+\u0005I\u0006CA\u000b[\u0013\tYfAA\u0004C_>dW-\u00198\t\u000fu\u0003\u0001\u0019!C\u00051\u0006Aq\f[1t\u001d\u0016DH\u000fC\u0004`\u0001\u0001\u0007I\u0011\u00021\u0002\u0019}C\u0017m\u001d(fqR|F%Z9\u0015\u0005}\n\u0007bB\"_\u0003\u0003\u0005\r!\u0017\u0005\u0007G\u0002\u0001\u000b\u0015B-\u0002\u0013}C\u0017m\u001d(fqR\u0004\u0003\"B3\u0001\t\u00031\u0017\u0001\u00028fqR$\u0012a\u0004\u0005\u0007Q\u0002!\t\u0001B\u001e\u0002+I,W.Y5oS:<W\t\\3nK:$8i\\;oi\"1!\u000e\u0001C\u0001\t-\fqB]3nC&t\u0017N\\4WK\u000e$xN]\u000b\u0002YB\u0019q$\\\b\n\u00059\u0014!A\u0002,fGR|'\u000f")
public class VectorIterator<A>
extends AbstractIterator<A>
implements VectorPointer<A> {
    private final int endIndex;
    private int blockIndex;
    private int lo;
    private int endLo;
    private boolean _hasNext;
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

    private int endLo() {
        return this.endLo;
    }

    private void endLo_$eq(int x$1) {
        this.endLo = x$1;
    }

    @Override
    public boolean hasNext() {
        return this._hasNext();
    }

    private boolean _hasNext() {
        return this._hasNext;
    }

    private void _hasNext_$eq(boolean x$1) {
        this._hasNext = x$1;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public A next() {
        if (this._hasNext()) {
            void var1_1;
            Object res = this.display0()[this.lo()];
            this.lo_$eq(this.lo() + 1);
            if (this.lo() == this.endLo()) {
                if (this.blockIndex() + this.lo() < this.endIndex) {
                    int newBlockIndex = this.blockIndex() + 32;
                    this.gotoNextBlockStart(newBlockIndex, this.blockIndex() ^ newBlockIndex);
                    this.blockIndex_$eq(newBlockIndex);
                    this.endLo_$eq(package$.MODULE$.min(this.endIndex - this.blockIndex(), 32));
                    this.lo_$eq(0);
                } else {
                    this._hasNext_$eq(false);
                }
            }
            return var1_1;
        }
        throw new NoSuchElementException("reached iterator end");
    }

    public int remainingElementCount() {
        int n = this.endIndex - (this.blockIndex() + this.lo());
        Predef$ predef$ = Predef$.MODULE$;
        return RichInt$.MODULE$.max$extension(n, 0);
    }

    /*
     * WARNING - void declaration
     */
    public Vector<A> remainingVector() {
        void var1_1;
        Vector v = new Vector(this.blockIndex() + this.lo(), this.endIndex, this.blockIndex() + this.lo());
        v.initFrom(this);
        return var1_1;
    }

    public VectorIterator(int _startIndex, int endIndex) {
        this.endIndex = endIndex;
        VectorPointer$class.$init$(this);
        this.blockIndex = _startIndex & ~31;
        this.lo = _startIndex & 0x1F;
        this.endLo = package$.MODULE$.min(endIndex - this.blockIndex(), 32);
        this._hasNext = this.blockIndex() + this.lo() < endIndex;
    }
}

