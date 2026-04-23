/*
 * Decompiled with CFR 0.152.
 */
package scala.ref;

import java.lang.ref.Reference;
import scala.Function0$class;
import scala.Option;
import scala.Proxy$class;
import scala.ref.PhantomReferenceWithWrapper;
import scala.ref.Reference$class;
import scala.ref.ReferenceQueue;
import scala.ref.ReferenceWrapper;
import scala.ref.ReferenceWrapper$class;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001q2A!\u0001\u0002\u0001\u000f\t\u0001\u0002\u000b[1oi>l'+\u001a4fe\u0016t7-\u001a\u0006\u0003\u0007\u0011\t1A]3g\u0015\u0005)\u0011!B:dC2\f7\u0001A\u000b\u0003\u0011M\u00192\u0001A\u0005\u000e!\tQ1\"D\u0001\u0005\u0013\taAA\u0001\u0004B]f\u0014VM\u001a\t\u0004\u001d=\tR\"\u0001\u0002\n\u0005A\u0011!\u0001\u0005*fM\u0016\u0014XM\\2f/J\f\u0007\u000f]3s!\t\u00112\u0003\u0004\u0001\u0005\rQ\u0001AQ1\u0001\u0016\u0005\u0005!\u0016C\u0001\f\n!\tQq#\u0003\u0002\u0019\t\t9aj\u001c;iS:<\u0007\u0002\u0003\u000e\u0001\u0005\u0003\u0005\u000b\u0011B\t\u0002\u000bY\fG.^3\t\u0011q\u0001!\u0011!Q\u0001\nu\tQ!];fk\u0016\u00042A\u0004\u0010\u0012\u0013\ty\"A\u0001\bSK\u001a,'/\u001a8dKF+X-^3\t\u000b\u0005\u0002A\u0011\u0001\u0012\u0002\rqJg.\u001b;?)\r\u0019C%\n\t\u0004\u001d\u0001\t\u0002\"\u0002\u000e!\u0001\u0004\t\u0002\"\u0002\u000f!\u0001\u0004i\u0002bB\u0014\u0001\u0005\u0004%\t\u0001K\u0001\u000bk:$WM\u001d7zS:<W#A\u00151\u0005)\u001a\u0004cA\u00162e5\tAF\u0003\u0002\u0004[)\u0011afL\u0001\u0005Y\u0006twMC\u00011\u0003\u0011Q\u0017M^1\n\u0005\u0005a\u0003C\u0001\n4\t%!T'!A\u0001\u0002\u000b\u00051HA\u0002`IEBaA\u000e\u0001!\u0002\u00139\u0014aC;oI\u0016\u0014H._5oO\u0002\u0002$\u0001\u000f\u001e\u0011\u0007-\n\u0014\b\u0005\u0002\u0013u\u0011IA'NA\u0001\u0002\u0003\u0015\taO\t\u0003-E\u0001")
public class PhantomReference<T>
implements ReferenceWrapper<T> {
    private final java.lang.ref.PhantomReference<? extends T> underlying;

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
    public java.lang.ref.PhantomReference<? extends T> underlying() {
        return this.underlying;
    }

    public PhantomReference(T value, ReferenceQueue<T> queue) {
        Function0$class.$init$(this);
        Reference$class.$init$(this);
        Proxy$class.$init$(this);
        ReferenceWrapper$class.$init$(this);
        this.underlying = new PhantomReferenceWithWrapper<T>(value, queue, this);
    }
}

