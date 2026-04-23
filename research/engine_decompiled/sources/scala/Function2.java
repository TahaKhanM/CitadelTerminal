/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Function1;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\u0005\u0005a\u0001C\u0001\u0003!\u0003\r\t!B@\u0003\u0013\u0019+hn\u0019;j_:\u0014$\"A\u0002\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U!a!S+\u0017'\t\u0001q\u0001\u0005\u0002\t\u00135\t!!\u0003\u0002\u000b\u0005\t1\u0011I\\=SK\u001aDQ\u0001\u0004\u0001\u0005\u00025\ta\u0001J5oSR$C#\u0001\b\u0011\u0005!y\u0011B\u0001\t\u0003\u0005\u0011)f.\u001b;\t\u000bI\u0001a\u0011A\n\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007Q1%\u000b\u0005\u0002\u0016-1\u0001A!C\f\u0001A\u0003\u0005IQ1\u0001\u0019\u0005\u0005\u0011\u0016CA\r\u001d!\tA!$\u0003\u0002\u001c\u0005\t9aj\u001c;iS:<\u0007C\u0001\u0005\u001e\u0013\tq\"AA\u0002B]fD\u0003B\u0006\u0011$[I:D(\u0011\t\u0003\u0011\u0005J!A\t\u0002\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G\u0011RCf\u000b\b\u0003K)r!AJ\u0015\u000e\u0003\u001dR!\u0001\u000b\u0003\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0019\u0011BA\u0016\u0003\u0003\u0011)f.\u001b;2\t\u0011*\u0013fA\u0019\u0006G9z\u0013\u0007\r\b\u0003K=J!\u0001\r\u0002\u0002\u000f\t{w\u000e\\3b]F\"A%J\u0015\u0004c\u0015\u00193\u0007\u000e\u001c6\u001d\t)C'\u0003\u00026\u0005\u0005\u0019\u0011J\u001c;2\t\u0011*\u0013fA\u0019\u0006GaJ4H\u000f\b\u0003KeJ!A\u000f\u0002\u0002\u000b\u0019cw.\u0019;2\t\u0011*\u0013fA\u0019\u0006Gur\u0004i\u0010\b\u0003KyJ!a\u0010\u0002\u0002\t1{gnZ\u0019\u0005I\u0015J3!M\u0003$\u0005\u000e+EI\u0004\u0002&\u0007&\u0011AIA\u0001\u0007\t>,(\r\\32\t\u0011*\u0013f\u0001\u0005\u0006\u000fF\u0001\r\u0001S\u0001\u0003mF\u0002\"!F%\u0005\u0013)\u0003\u0001\u0015!A\t\u0006\u0004A\"A\u0001+2Q\u0015I\u0005\u0005\u0014(Qc\u0015\u00193\u0007N'6c\u0011!S%K\u00022\u000b\rjdhT 2\t\u0011*\u0013fA\u0019\u0006G\t\u001b\u0015\u000bR\u0019\u0005I\u0015J3\u0001C\u0003T#\u0001\u0007A+\u0001\u0002weA\u0011Q#\u0016\u0003\n-\u0002\u0001\u000b\u0011!EC\u0002a\u0011!\u0001\u0016\u001a)\u000bU\u0003\u0003L\u0017/2\u000b\r\u001aD'W\u001b2\t\u0011*\u0013fA\u0019\u0006Gur4lP\u0019\u0005I\u0015J3!M\u0003$\u0005\u000ekF)\r\u0003%K%\u001a\u0001\"B0\u0001\t\u0003\u0001\u0017aB2veJLW\rZ\u000b\u0002CB!\u0001B\u0019%e\u0013\t\u0019'AA\u0005Gk:\u001cG/[8ocA!\u0001B\u0019+\u0015Q\tqf\r\u0005\u0002hU6\t\u0001N\u0003\u0002j\u0005\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005-D'!D;ogB,7-[1mSj,G\rC\u0003n\u0001\u0011\u0005a.\u0001\u0004ukBdW\rZ\u000b\u0002_B!\u0001B\u00199\u0015!\u0011A\u0011\u000f\u0013+\n\u0005I\u0014!A\u0002+va2,'\u0007\u000b\u0002mM\")Q\u000f\u0001C!m\u0006AAo\\*ue&tw\rF\u0001x!\tAX0D\u0001z\u0015\tQ80\u0001\u0003mC:<'\"\u0001?\u0002\t)\fg/Y\u0005\u0003}f\u0014aa\u0015;sS:<\u0007#\u0002\u0005\u0001\u0011R#\u0002")
public interface Function2<T1, T2, R> {
    public R apply(T1 var1, T2 var2);

    public Function1<T1, Function1<T2, R>> curried();

    public Function1<Tuple2<T1, T2>, R> tupled();

    public String toString();

    public boolean apply$mcZDD$sp(double var1, double var3);

    public double apply$mcDDD$sp(double var1, double var3);

    public float apply$mcFDD$sp(double var1, double var3);

    public int apply$mcIDD$sp(double var1, double var3);

    public long apply$mcJDD$sp(double var1, double var3);

    public void apply$mcVDD$sp(double var1, double var3);

    public boolean apply$mcZDI$sp(double var1, int var3);

    public double apply$mcDDI$sp(double var1, int var3);

    public float apply$mcFDI$sp(double var1, int var3);

    public int apply$mcIDI$sp(double var1, int var3);

    public long apply$mcJDI$sp(double var1, int var3);

    public void apply$mcVDI$sp(double var1, int var3);

    public boolean apply$mcZDJ$sp(double var1, long var3);

    public double apply$mcDDJ$sp(double var1, long var3);

    public float apply$mcFDJ$sp(double var1, long var3);

    public int apply$mcIDJ$sp(double var1, long var3);

    public long apply$mcJDJ$sp(double var1, long var3);

    public void apply$mcVDJ$sp(double var1, long var3);

    public boolean apply$mcZID$sp(int var1, double var2);

    public double apply$mcDID$sp(int var1, double var2);

    public float apply$mcFID$sp(int var1, double var2);

    public int apply$mcIID$sp(int var1, double var2);

    public long apply$mcJID$sp(int var1, double var2);

    public void apply$mcVID$sp(int var1, double var2);

    public boolean apply$mcZII$sp(int var1, int var2);

    public double apply$mcDII$sp(int var1, int var2);

    public float apply$mcFII$sp(int var1, int var2);

    public int apply$mcIII$sp(int var1, int var2);

    public long apply$mcJII$sp(int var1, int var2);

    public void apply$mcVII$sp(int var1, int var2);

    public boolean apply$mcZIJ$sp(int var1, long var2);

    public double apply$mcDIJ$sp(int var1, long var2);

    public float apply$mcFIJ$sp(int var1, long var2);

    public int apply$mcIIJ$sp(int var1, long var2);

    public long apply$mcJIJ$sp(int var1, long var2);

    public void apply$mcVIJ$sp(int var1, long var2);

    public boolean apply$mcZJD$sp(long var1, double var3);

    public double apply$mcDJD$sp(long var1, double var3);

    public float apply$mcFJD$sp(long var1, double var3);

    public int apply$mcIJD$sp(long var1, double var3);

    public long apply$mcJJD$sp(long var1, double var3);

    public void apply$mcVJD$sp(long var1, double var3);

    public boolean apply$mcZJI$sp(long var1, int var3);

    public double apply$mcDJI$sp(long var1, int var3);

    public float apply$mcFJI$sp(long var1, int var3);

    public int apply$mcIJI$sp(long var1, int var3);

    public long apply$mcJJI$sp(long var1, int var3);

    public void apply$mcVJI$sp(long var1, int var3);

    public boolean apply$mcZJJ$sp(long var1, long var3);

    public double apply$mcDJJ$sp(long var1, long var3);

    public float apply$mcFJJ$sp(long var1, long var3);

    public int apply$mcIJJ$sp(long var1, long var3);

    public long apply$mcJJJ$sp(long var1, long var3);

    public void apply$mcVJJ$sp(long var1, long var3);
}

