/*
 * Decompiled with CFR 0.152.
 */
package scala.ref;

import java.lang.ref.Reference;
import scala.Function0$class;
import scala.Option;
import scala.Proxy$class;
import scala.ref.Reference$class;
import scala.ref.ReferenceQueue;
import scala.ref.ReferenceWrapper;
import scala.ref.ReferenceWrapper$class;
import scala.ref.SoftReferenceWithWrapper;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001}2A!\u0001\u0002\u0001\u000f\ti1k\u001c4u%\u00164WM]3oG\u0016T!a\u0001\u0003\u0002\u0007I,gMC\u0001\u0006\u0003\u0015\u00198-\u00197b\u0007\u0001)\"\u0001C\n\u0014\u0007\u0001IQ\u0002\u0005\u0002\u000b\u00175\tA!\u0003\u0002\r\t\t1\u0011I\\=SK\u001a\u00042AD\b\u0012\u001b\u0005\u0011\u0011B\u0001\t\u0003\u0005A\u0011VMZ3sK:\u001cWm\u0016:baB,'\u000f\u0005\u0002\u0013'1\u0001AA\u0002\u000b\u0001\t\u000b\u0007QCA\u0001U#\t1\u0012\u0002\u0005\u0002\u000b/%\u0011\u0001\u0004\u0002\u0002\b\u001d>$\b.\u001b8h\u0011!Q\u0002A!A!\u0002\u0013\t\u0012!\u0002<bYV,\u0007\u0002\u0003\u000f\u0001\u0005\u0003\u0005\u000b\u0011B\u000f\u0002\u000bE,X-^3\u0011\u00079q\u0012#\u0003\u0002 \u0005\tq!+\u001a4fe\u0016t7-Z)vKV,\u0007\"B\u0011\u0001\t\u0003\u0011\u0013A\u0002\u001fj]&$h\bF\u0002$I\u0015\u00022A\u0004\u0001\u0012\u0011\u0015Q\u0002\u00051\u0001\u0012\u0011\u0015a\u0002\u00051\u0001\u001e\u0011\u0015\t\u0003\u0001\"\u0001()\t\u0019\u0003\u0006C\u0003\u001bM\u0001\u0007\u0011\u0003C\u0004+\u0001\t\u0007I\u0011A\u0016\u0002\u0015UtG-\u001a:ms&tw-F\u0001-a\tic\u0007E\u0002/iUj\u0011a\f\u0006\u0003\u0007AR!!\r\u001a\u0002\t1\fgn\u001a\u0006\u0002g\u0005!!.\u0019<b\u0013\t\tq\u0006\u0005\u0002\u0013m\u0011Iq\u0007OA\u0001\u0002\u0003\u0015\tA\u0010\u0002\u0004?\u0012\n\u0004BB\u001d\u0001A\u0003%!(A\u0006v]\u0012,'\u000f\\=j]\u001e\u0004\u0003GA\u001e>!\rqC\u0007\u0010\t\u0003%u\"\u0011b\u000e\u001d\u0002\u0002\u0003\u0005)\u0011\u0001 \u0012\u0005Y\t\u0002")
public class SoftReference<T>
implements ReferenceWrapper<T> {
    private final java.lang.ref.SoftReference<? extends T> underlying;

    @Override
    public Option<T> get() {
        return ReferenceWrapper$class.get(this);
    }

    @Override
    public T apply() {
        return (T)ReferenceWrapper$class.apply(this);
    }

    @Override
    public void clear() {
        ReferenceWrapper$class.clear(this);
    }

    @Override
    public boolean enqueue() {
        return ReferenceWrapper$class.enqueue(this);
    }

    @Override
    public boolean isEnqueued() {
        return ReferenceWrapper$class.isEnqueued(this);
    }

    @Override
    public Reference<? extends T> self() {
        return ReferenceWrapper$class.self(this);
    }

    @Override
    public int hashCode() {
        return Proxy$class.hashCode(this);
    }

    @Override
    public boolean equals(Object that) {
        return Proxy$class.equals(this, that);
    }

    @Override
    public String toString() {
        return Proxy$class.toString(this);
    }

    @Override
    public boolean apply$mcZ$sp() {
        return Function0$class.apply$mcZ$sp(this);
    }

    @Override
    public byte apply$mcB$sp() {
        return Function0$class.apply$mcB$sp(this);
    }

    @Override
    public char apply$mcC$sp() {
        return Function0$class.apply$mcC$sp(this);
    }

    @Override
    public double apply$mcD$sp() {
        return Function0$class.apply$mcD$sp(this);
    }

    @Override
    public float apply$mcF$sp() {
        return Function0$class.apply$mcF$sp(this);
    }

    @Override
    public int apply$mcI$sp() {
        return Function0$class.apply$mcI$sp(this);
    }

    @Override
    public long apply$mcJ$sp() {
        return Function0$class.apply$mcJ$sp(this);
    }

    @Override
    public short apply$mcS$sp() {
        return Function0$class.apply$mcS$sp(this);
    }

    @Override
    public void apply$mcV$sp() {
        Function0$class.apply$mcV$sp(this);
    }

    @Override
    public java.lang.ref.SoftReference<? extends T> underlying() {
        return this.underlying;
    }

    public SoftReference(T value, ReferenceQueue<T> queue) {
        Function0$class.$init$(this);
        Reference$class.$init$(this);
        Proxy$class.$init$(this);
        ReferenceWrapper$class.$init$(this);
        this.underlying = new SoftReferenceWithWrapper<T>(value, queue, this);
    }

    public SoftReference(T value) {
        this(value, null);
    }
}

