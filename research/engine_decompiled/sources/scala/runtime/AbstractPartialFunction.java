/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function1;
import scala.Function1$class;
import scala.Option;
import scala.PartialFunction;
import scala.PartialFunction$;
import scala.PartialFunction$class;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001m3a!\u0001\u0002\u0002\u0002\u001d!&aF!cgR\u0014\u0018m\u0019;QCJ$\u0018.\u00197Gk:\u001cG/[8o\u0015\t\u0019A!A\u0004sk:$\u0018.\\3\u000b\u0003\u0015\tQa]2bY\u0006\u001c\u0001!F\u0002\t%e\u001aB\u0001A\u0005\u000e\u001dB\u0011!bC\u0007\u0002\t%\u0011A\u0002\u0002\u0002\u0007\u0003:L(+\u001a4\u0011\t)q\u0001\u0003O\u0005\u0003\u001f\u0011\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005E\u0011B\u0002\u0001\u0003\n'\u0001\u0001\u000b\u0011!EC\u0002Q\u0011!\u0001V\u0019\u0012\u0005UA\u0002C\u0001\u0006\u0017\u0013\t9BAA\u0004O_RD\u0017N\\4\u0011\u0005)I\u0012B\u0001\u000e\u0005\u0005\r\te.\u001f\u0015\u0007%qy\u0012FL\u001a\u0011\u0005)i\u0012B\u0001\u0010\u0005\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\r\u0002c\u0005K\u0014\u000f\u0005\u00052cB\u0001\u0012&\u001b\u0005\u0019#B\u0001\u0013\u0007\u0003\u0019a$o\\8u}%\tQ!\u0003\u0002(\t\u0005\u0019\u0011J\u001c;2\t\u0011\nS%B\u0019\u0006G)ZS\u0006\f\b\u0003C-J!\u0001\f\u0003\u0002\t1{gnZ\u0019\u0005I\u0005*S!M\u0003$_A\u0012\u0014G\u0004\u0002\"a%\u0011\u0011\u0007B\u0001\u0006\r2|\u0017\r^\u0019\u0005I\u0005*S!M\u0003$iU:dG\u0004\u0002\"k%\u0011a\u0007B\u0001\u0007\t>,(\r\\32\t\u0011\nS%\u0002\t\u0003#e\"\u0011B\u000f\u0001!\u0002\u0003%)\u0019\u0001\u000b\u0003\u0003IC\u0003\"\u000f\u000f=\u0003\u001aC%\nT\u0019\u0006Gur\u0004i\u0010\b\u0003CyJ!a\u0010\u0003\u0002\tUs\u0017\u000e^\u0019\u0005I\u0005*S!M\u0003$\u0005\u000e+EI\u0004\u0002\"\u0007&\u0011A\tB\u0001\b\u0005>|G.Z1oc\u0011!\u0013%J\u00032\u000b\r\u0002ceR\u00142\t\u0011\nS%B\u0019\u0006G=\u0002\u0014*M\u0019\u0005I\u0005*S!M\u0003$U-ZE&\r\u0003%C\u0015*\u0011'B\u00125k53\u0014\u0007\u0002\u0013\"K\u0015\u0001BAC(\u0011q%\u0011\u0001\u000b\u0002\u0002\u0010!\u0006\u0014H/[1m\rVt7\r^5p]\")!\u000b\u0001C\u0001'\u00061A(\u001b8jiz\"\u0012\u0001\u0016\t\u0005+\u0002\u0001\u0002(D\u0001\u0003\u0011\u00159\u0006\u0001\"\u0001Y\u0003\u0015\t\u0007\u000f\u001d7z)\tA\u0014\fC\u0003[-\u0002\u0007\u0001#A\u0001y\u0001")
public abstract class AbstractPartialFunction<T1, R>
implements PartialFunction<T1, R> {
    @Override
    public <A1 extends T1, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> that) {
        return PartialFunction$class.orElse(this, that);
    }

    @Override
    public <C> PartialFunction<T1, C> andThen(Function1<R, C> k) {
        return PartialFunction$class.andThen(this, k);
    }

    @Override
    public Function1<T1, Option<R>> lift() {
        return PartialFunction$class.lift(this);
    }

    @Override
    public <A1 extends T1, B1> B1 applyOrElse(A1 x, Function1<A1, B1> function1) {
        return (B1)PartialFunction$class.applyOrElse(this, x, function1);
    }

    @Override
    public <U> Function1<T1, Object> runWith(Function1<R, U> action) {
        return PartialFunction$class.runWith(this, action);
    }

    @Override
    public <A> Function1<A, R> compose(Function1<A, T1> g) {
        return Function1$class.compose(this, g);
    }

    @Override
    public String toString() {
        return Function1$class.toString(this);
    }

    @Override
    public R apply(T1 x) {
        return (R)this.applyOrElse(x, PartialFunction$.MODULE$.empty());
    }

    @Override
    public boolean apply$mcZD$sp(double x) {
        return BoxesRunTime.unboxToBoolean(this.apply(BoxesRunTime.boxToDouble(x)));
    }

    @Override
    public double apply$mcDD$sp(double x) {
        return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(x)));
    }

    @Override
    public float apply$mcFD$sp(double x) {
        return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(x)));
    }

    @Override
    public int apply$mcID$sp(double x) {
        return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(x)));
    }

    @Override
    public long apply$mcJD$sp(double x) {
        return BoxesRunTime.unboxToLong(this.apply(BoxesRunTime.boxToDouble(x)));
    }

    @Override
    public void apply$mcVD$sp(double x) {
        this.apply(BoxesRunTime.boxToDouble(x));
    }

    @Override
    public boolean apply$mcZF$sp(float x) {
        return BoxesRunTime.unboxToBoolean(this.apply(BoxesRunTime.boxToFloat(x)));
    }

    @Override
    public double apply$mcDF$sp(float x) {
        return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(x)));
    }

    @Override
    public float apply$mcFF$sp(float x) {
        return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(x)));
    }

    @Override
    public int apply$mcIF$sp(float x) {
        return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(x)));
    }

    @Override
    public long apply$mcJF$sp(float x) {
        return BoxesRunTime.unboxToLong(this.apply(BoxesRunTime.boxToFloat(x)));
    }

    @Override
    public void apply$mcVF$sp(float x) {
        this.apply(BoxesRunTime.boxToFloat(x));
    }

    @Override
    public boolean apply$mcZI$sp(int x) {
        return BoxesRunTime.unboxToBoolean(this.apply(BoxesRunTime.boxToInteger(x)));
    }

    @Override
    public double apply$mcDI$sp(int x) {
        return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(x)));
    }

    @Override
    public float apply$mcFI$sp(int x) {
        return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(x)));
    }

    @Override
    public int apply$mcII$sp(int x) {
        return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(x)));
    }

    @Override
    public long apply$mcJI$sp(int x) {
        return BoxesRunTime.unboxToLong(this.apply(BoxesRunTime.boxToInteger(x)));
    }

    @Override
    public void apply$mcVI$sp(int x) {
        this.apply(BoxesRunTime.boxToInteger(x));
    }

    @Override
    public boolean apply$mcZJ$sp(long x) {
        return BoxesRunTime.unboxToBoolean(this.apply(BoxesRunTime.boxToLong(x)));
    }

    @Override
    public double apply$mcDJ$sp(long x) {
        return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToLong(x)));
    }

    @Override
    public float apply$mcFJ$sp(long x) {
        return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToLong(x)));
    }

    @Override
    public int apply$mcIJ$sp(long x) {
        return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToLong(x)));
    }

    @Override
    public long apply$mcJJ$sp(long x) {
        return BoxesRunTime.unboxToLong(this.apply(BoxesRunTime.boxToLong(x)));
    }

    @Override
    public void apply$mcVJ$sp(long x) {
        this.apply(BoxesRunTime.boxToLong(x));
    }

    public AbstractPartialFunction() {
        Function1$class.$init$(this);
        PartialFunction$class.$init$(this);
    }
}

