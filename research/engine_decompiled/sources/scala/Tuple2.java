/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Option;
import scala.Product$class;
import scala.Product2;
import scala.Product2$class;
import scala.Serializable;
import scala.Tuple2$;
import scala.Tuple2$mcCC$sp;
import scala.Tuple2$mcCD$sp;
import scala.Tuple2$mcCI$sp;
import scala.Tuple2$mcCJ$sp;
import scala.Tuple2$mcCZ$sp;
import scala.Tuple2$mcDC$sp;
import scala.Tuple2$mcDD$sp;
import scala.Tuple2$mcDI$sp;
import scala.Tuple2$mcDJ$sp;
import scala.Tuple2$mcDZ$sp;
import scala.Tuple2$mcIC$sp;
import scala.Tuple2$mcID$sp;
import scala.Tuple2$mcII$sp;
import scala.Tuple2$mcIJ$sp;
import scala.Tuple2$mcIZ$sp;
import scala.Tuple2$mcJC$sp;
import scala.Tuple2$mcJD$sp;
import scala.Tuple2$mcJI$sp;
import scala.Tuple2$mcJJ$sp;
import scala.Tuple2$mcJZ$sp;
import scala.Tuple2$mcZC$sp;
import scala.Tuple2$mcZD$sp;
import scala.Tuple2$mcZI$sp;
import scala.Tuple2$mcZJ$sp;
import scala.Tuple2$mcZZ$sp;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\tUa\u0001B\u0001\u0003\u0001\u0016\u0011a\u0001V;qY\u0016\u0014$\"A\u0002\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0019a\u0001\u0005\u001a\u0014\u000b\u000191BO\u001f\u0011\u0005!IQ\"\u0001\u0002\n\u0005)\u0011!AB!osJ+g\r\u0005\u0003\t\u00199\t\u0014BA\u0007\u0003\u0005!\u0001&o\u001c3vGR\u0014\u0004CA\b\u0011\u0019\u0001!\u0011\"\u0005\u0001!\u0002\u0003%)\u0019\u0001\n\u0003\u0005Q\u000b\u0014CA\n\u0017!\tAA#\u0003\u0002\u0016\u0005\t9aj\u001c;iS:<\u0007C\u0001\u0005\u0018\u0013\tA\"AA\u0002B]fDs\u0001\u0005\u000e\u001eC\u0015JS\u0006\u0005\u0002\t7%\u0011AD\u0001\u0002\fgB,7-[1mSj,G-\r\u0003%=}\u0001cB\u0001\u0005 \u0013\t\u0001#!A\u0002J]R\fD\u0001\n\u0012$I9\u0011\u0001bI\u0005\u0003I\t\tA\u0001T8oOF\"AEJ\u0014)\u001d\tAq%\u0003\u0002)\u0005\u00051Ai\\;cY\u0016\fD\u0001\n\u0016,Y9\u0011\u0001bK\u0005\u0003Y\t\tAa\u00115beF\"AEL\u00181\u001d\tAq&\u0003\u00021\u0005\u00059!i\\8mK\u0006t\u0007CA\b3\t%\u0019\u0004\u0001)A\u0001\n\u000b\u0007!C\u0001\u0002Ue!:!GG\u001b7oaJ\u0014\u0007\u0002\u0013\u001f?\u0001\nD\u0001\n\u0012$IE\"AEJ\u0014)c\u0011!#f\u000b\u00172\t\u0011rs\u0006\r\t\u0003\u0011mJ!\u0001\u0010\u0002\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0001BP\u0005\u0003\u007f\t\u0011AbU3sS\u0006d\u0017N_1cY\u0016D\u0001\"\u0011\u0001\u0003\u0016\u0004%\tAQ\u0001\u0003?F*\u0012A\u0004\u0005\t\t\u0002\u0011\t\u0012)A\u0005\u001d\u0005\u0019q,\r\u0011\t\u0011\u0019\u0003!Q3A\u0005\u0002\u001d\u000b!a\u0018\u001a\u0016\u0003EB\u0001\"\u0013\u0001\u0003\u0012\u0003\u0006I!M\u0001\u0004?J\u0002\u0003\"B&\u0001\t\u0003a\u0015A\u0002\u001fj]&$h\bF\u0002N\u001d>\u0003B\u0001\u0003\u0001\u000fc!)\u0011I\u0013a\u0001\u001d!)aI\u0013a\u0001c!)\u0011\u000b\u0001C!%\u0006AAo\\*ue&tw\rF\u0001T!\t!\u0016,D\u0001V\u0015\t1v+\u0001\u0003mC:<'\"\u0001-\u0002\t)\fg/Y\u0005\u00035V\u0013aa\u0015;sS:<\u0007\"\u0002/\u0001\t\u0003i\u0016\u0001B:xCB,\u0012A\u0018\t\u0005\u0011\u0001\td\u0002C\u0004a\u0001\u0005\u0005I\u0011A1\u0002\t\r|\u0007/_\u000b\u0004E\u0016lGcA2ukB!\u0001\u0002\u00013m!\tyQ\rB\u0005\u0012?\u0002\u0006\t\u0011!b\u0001%!:QMG4iS*\\\u0017\u0007\u0002\u0013\u001f?\u0001\nD\u0001\n\u0012$IE\"AEJ\u0014)c\u0011!#f\u000b\u00172\t\u0011rs\u0006\r\t\u0003\u001f5$\u0011bM0!\u0002\u0003\u0005)\u0019\u0001\n)\u000f5Tr\u000e]9sgF\"AEH\u0010!c\u0011!#e\t\u00132\t\u00112s\u0005K\u0019\u0005I)ZC&\r\u0003%]=\u0002\u0004bB!`!\u0003\u0005\r\u0001\u001a\u0005\b\r~\u0003\n\u00111\u0001m\u0011\u001d9\b!%A\u0005\u0002a\fabY8qs\u0012\"WMZ1vYR$\u0013'F\u0003z\u0003\u0013\t9\"F\u0001{U\tq1pK\u0001}!\ri\u0018QA\u0007\u0002}*\u0019q0!\u0001\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0002\u0005\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0007\u0005\u001daPA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$\u0011\"\u0005<!\u0002\u0003\u0005)\u0019\u0001\n)\u001b\u0005%!$!\u0004\u0002\u0010\u0005E\u00111CA\u000bc\u0011!cd\b\u00112\t\u0011\u00123\u0005J\u0019\u0005I\u0019:\u0003&\r\u0003%U-b\u0013\u0007\u0002\u0013/_A\"\u0011b\r<!\u0002\u0003\u0005)\u0019\u0001\n)\u001b\u0005]!$a\u0007\u0002\u001e\u0005}\u0011\u0011EA\u0012c\u0011!cd\b\u00112\t\u0011\u00123\u0005J\u0019\u0005I\u0019:\u0003&\r\u0003%U-b\u0013\u0007\u0002\u0013/_AB\u0011\"a\n\u0001#\u0003%\t!!\u000b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU1\u00111FA\u0018\u0003{)\"!!\f+\u0005EZHAC\t\u0002&\u0001\u0006\t\u0011!b\u0001%!j\u0011q\u0006\u000e\u00024\u0005U\u0012qGA\u001d\u0003w\tD\u0001\n\u0010 AE\"AEI\u0012%c\u0011!ce\n\u00152\t\u0011R3\u0006L\u0019\u0005I9z\u0003\u0007\u0002\u00064\u0003K\u0001\u000b\u0011!AC\u0002IAS\"!\u0010\u001b\u0003\u0003\n\u0019%!\u0012\u0002H\u0005%\u0013\u0007\u0002\u0013\u001f?\u0001\nD\u0001\n\u0012$IE\"AEJ\u0014)c\u0011!#f\u000b\u00172\t\u0011rs\u0006\r\u0005\n\u0003\u001b\u0002\u0011\u0011!C!\u0003\u001f\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A*\t\u0013\u0005M\u0003!!A\u0005B\u0005U\u0013a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005]\u0003#BA-\u0003?2RBAA.\u0015\r\tiFA\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA1\u00037\u0012\u0001\"\u0013;fe\u0006$xN\u001d\u0005\n\u0003K\u0002\u0011\u0011!C\u0001\u0003O\n\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003S\ny\u0007E\u0002\t\u0003WJ1!!\u001c\u0003\u0005\u001d\u0011un\u001c7fC:D\u0011\"!\u001d\u0002d\u0005\u0005\t\u0019\u0001\f\u0002\u0007a$\u0013\u0007C\u0005\u0002v\u0001\t\t\u0011\"\u0011\u0002x\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002zA\u0019\u0001\"a\u001f\n\u0007\u0005u$AA\u0002J]RD\u0011\"!!\u0001\u0003\u0003%\t%a!\u0002\r\u0015\fX/\u00197t)\u0011\tI'!\"\t\u0013\u0005E\u0014qPA\u0001\u0002\u00041\u0002f\u0002\u0001\u0002\n\u0006=\u00151\u0013\t\u0004\u0011\u0005-\u0015bAAG\u0005\t)B-\u001a9sK\u000e\fG/\u001a3J]\",'/\u001b;b]\u000e,\u0017EAAI\u00039\"V\u000f\u001d7fg\u0002:\u0018\u000e\u001c7!E\u0016\u0004S.\u00193fA\u0019Lg.\u00197!S:\u0004\u0013\r\t4viV\u0014X\r\t<feNLwN\u001c\u0018\"\u0005\u0005U\u0015A\u0002\u001a/cEr\u0003gB\u0005\u0002\u001a\n\t\t\u0011#\u0001\u0002\u001c\u00061A+\u001e9mKJ\u00022\u0001CAO\r!\t!!!A\t\u0002\u0005}5\u0003BAO\u000fuBqaSAO\t\u0003\t\u0019\u000b\u0006\u0002\u0002\u001c\"A\u0011+!(\u0002\u0002\u0013\u0015#\u000b\u0003\u0006\u0002*\u0006u\u0015\u0011!CA\u0003W\u000bQ!\u00199qYf,b!!,\u00024\u0006\rGCBAX\u0003#\f\u0019\u000e\u0005\u0004\t\u0001\u0005E\u0016\u0011\u0019\t\u0004\u001f\u0005MFAC\t\u0002(\u0002\u0006\t\u0011!b\u0001%!j\u00111\u0017\u000e\u00028\u0006e\u00161XA_\u0003\u007f\u000bD\u0001\n\u0010 AE\"AEI\u0012%c\u0011!ce\n\u00152\t\u0011R3\u0006L\u0019\u0005I9z\u0003\u0007E\u0002\u0010\u0003\u0007$!bMATA\u0003\u0005\tQ1\u0001\u0013Q5\t\u0019MGAd\u0003\u0013\fY-!4\u0002PF\"AEH\u0010!c\u0011!#e\t\u00132\t\u00112s\u0005K\u0019\u0005I)ZC&\r\u0003%]=\u0002\u0004bB!\u0002(\u0002\u0007\u0011\u0011\u0017\u0005\b\r\u0006\u001d\u0006\u0019AAa\u0011)\t9.!(\u0002\u0002\u0013\u0005\u0015\u0011\\\u0001\bk:\f\u0007\u000f\u001d7z+\u0019\tY.a:\u0002xR!\u0011Q\u001cB\u0003!\u0015A\u0011q\\Ar\u0013\r\t\tO\u0001\u0002\u0007\u001fB$\u0018n\u001c8\u0011\r!\u0001\u0011Q]A{!\ry\u0011q\u001d\u0003\u000b#\u0005U\u0007\u0015!A\u0001\u0006\u0004\u0011\u0002&DAt5\u0005-\u0018Q^Ax\u0003c\f\u00190\r\u0003%=}\u0001\u0013\u0007\u0002\u0013#G\u0011\nD\u0001\n\u0014(QE\"AEK\u0016-c\u0011!cf\f\u0019\u0011\u0007=\t9\u0010\u0002\u00064\u0003+\u0004\u000b\u0011!AC\u0002IAS\"a>\u001b\u0003w\fi0a@\u0003\u0002\t\r\u0011\u0007\u0002\u0013\u001f?\u0001\nD\u0001\n\u0012$IE\"AEJ\u0014)c\u0011!#f\u000b\u00172\t\u0011rs\u0006\r\u0005\u000b\u0005\u000f\t).!AA\u0002\u0005\r\u0018a\u0001=%a!Q!1BAO\u0003\u0003%IA!\u0004\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0003\u0005\u001f\u00012\u0001\u0016B\t\u0013\r\u0011\u0019\"\u0016\u0002\u0007\u001f\nTWm\u0019;")
public class Tuple2<T1, T2>
implements Product2<T1, T2>,
Serializable {
    public final T1 _1;
    public final T2 _2;

    public static <T1, T2> Option<Tuple2<T1, T2>> unapply(Tuple2<T1, T2> tuple2) {
        return Tuple2$.MODULE$.unapply(tuple2);
    }

    public static <T1, T2> Tuple2<T1, T2> apply(T1 T1, T2 T2) {
        return Tuple2$.MODULE$.apply(T1, T2);
    }

    @Override
    public int productArity() {
        return Product2$class.productArity(this);
    }

    @Override
    public Object productElement(int n) throws IndexOutOfBoundsException {
        return Product2$class.productElement(this, n);
    }

    @Override
    public T1 _1() {
        return this._1;
    }

    @Override
    public T2 _2() {
        return this._2;
    }

    public String toString() {
        return new StringBuilder().append((Object)"(").append(this._1()).append((Object)",").append(this._2()).append((Object)")").toString();
    }

    public Tuple2<T2, T1> swap() {
        return new Tuple2<T2, T1>(this._2(), this._1());
    }

    public <T1, T2> Tuple2<T1, T2> copy(T1 _1, T2 _2) {
        return new Tuple2<T1, T2>(_1, _2);
    }

    public <T1, T2> T1 copy$default$1() {
        return this._1();
    }

    public <T1, T2> T2 copy$default$2() {
        return this._2();
    }

    @Override
    public String productPrefix() {
        return "Tuple2";
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof Tuple2;
    }

    public int hashCode() {
        return ScalaRunTime$.MODULE$._hashCode(this);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean equals(Object x$1) {
        boolean bl;
        boolean bl2;
        if (this == x$1) return true;
        if (!(x$1 instanceof Tuple2)) return false;
        boolean bl3 = true;
        if (!bl3) return false;
        Tuple2 tuple2 = (Tuple2)x$1;
        T1 T1 = tuple2._1();
        T1 T12 = this._1();
        if (T12 == T1) {
            bl2 = true;
        } else {
            if (T12 == null) {
                return false;
            }
            bl2 = T12 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T12, T1) : (T12 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T12, T1) : T12.equals(T1));
        }
        if (!bl2) return false;
        T2 T2 = tuple2._2();
        T2 T22 = this._2();
        if (T22 == T2) {
            bl = true;
        } else {
            if (T22 == null) {
                return false;
            }
            bl = T22 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T22, T2) : (T22 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T22, T2) : T22.equals(T2));
        }
        if (!bl) return false;
        if (!tuple2.canEqual(this)) return false;
        return true;
    }

    public boolean _1$mcZ$sp() {
        return BoxesRunTime.unboxToBoolean(this._1());
    }

    public char _1$mcC$sp() {
        return BoxesRunTime.unboxToChar(this._1());
    }

    @Override
    public double _1$mcD$sp() {
        return BoxesRunTime.unboxToDouble(this._1());
    }

    @Override
    public int _1$mcI$sp() {
        return BoxesRunTime.unboxToInt(this._1());
    }

    @Override
    public long _1$mcJ$sp() {
        return BoxesRunTime.unboxToLong(this._1());
    }

    public boolean _2$mcZ$sp() {
        return BoxesRunTime.unboxToBoolean(this._2());
    }

    public char _2$mcC$sp() {
        return BoxesRunTime.unboxToChar(this._2());
    }

    @Override
    public double _2$mcD$sp() {
        return BoxesRunTime.unboxToDouble(this._2());
    }

    @Override
    public int _2$mcI$sp() {
        return BoxesRunTime.unboxToInt(this._2());
    }

    @Override
    public long _2$mcJ$sp() {
        return BoxesRunTime.unboxToLong(this._2());
    }

    public Tuple2<Object, Object> swap$mcZZ$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcZC$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcZD$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcZI$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcZJ$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcCZ$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcCC$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcCD$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcCI$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcCJ$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcDZ$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcDC$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcDD$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcDI$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcDJ$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcIZ$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcIC$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcID$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcII$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcIJ$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcJZ$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcJC$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcJD$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcJI$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> swap$mcJJ$sp() {
        return this.swap();
    }

    public Tuple2<Object, Object> copy$mZZc$sp(boolean _1, boolean _2) {
        return new Tuple2$mcZZ$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mZCc$sp(boolean _1, char _2) {
        return new Tuple2$mcZC$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mZDc$sp(boolean _1, double _2) {
        return new Tuple2$mcZD$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mZIc$sp(boolean _1, int _2) {
        return new Tuple2$mcZI$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mZJc$sp(boolean _1, long _2) {
        return new Tuple2$mcZJ$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mCZc$sp(char _1, boolean _2) {
        return new Tuple2$mcCZ$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mCCc$sp(char _1, char _2) {
        return new Tuple2$mcCC$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mCDc$sp(char _1, double _2) {
        return new Tuple2$mcCD$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mCIc$sp(char _1, int _2) {
        return new Tuple2$mcCI$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mCJc$sp(char _1, long _2) {
        return new Tuple2$mcCJ$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mDZc$sp(double _1, boolean _2) {
        return new Tuple2$mcDZ$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mDCc$sp(double _1, char _2) {
        return new Tuple2$mcDC$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mDDc$sp(double _1, double _2) {
        return new Tuple2$mcDD$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mDIc$sp(double _1, int _2) {
        return new Tuple2$mcDI$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mDJc$sp(double _1, long _2) {
        return new Tuple2$mcDJ$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mIZc$sp(int _1, boolean _2) {
        return new Tuple2$mcIZ$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mICc$sp(int _1, char _2) {
        return new Tuple2$mcIC$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mIDc$sp(int _1, double _2) {
        return new Tuple2$mcID$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mIIc$sp(int _1, int _2) {
        return new Tuple2$mcII$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mIJc$sp(int _1, long _2) {
        return new Tuple2$mcIJ$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mJZc$sp(long _1, boolean _2) {
        return new Tuple2$mcJZ$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mJCc$sp(long _1, char _2) {
        return new Tuple2$mcJC$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mJDc$sp(long _1, double _2) {
        return new Tuple2$mcJD$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mJIc$sp(long _1, int _2) {
        return new Tuple2$mcJI$sp(_1, _2);
    }

    public Tuple2<Object, Object> copy$mJJc$sp(long _1, long _2) {
        return new Tuple2$mcJJ$sp(_1, _2);
    }

    public <T1, T2> boolean copy$default$1$mcZ$sp() {
        return BoxesRunTime.unboxToBoolean(this.copy$default$1());
    }

    public <T1, T2> char copy$default$1$mcC$sp() {
        return BoxesRunTime.unboxToChar(this.copy$default$1());
    }

    public <T1, T2> double copy$default$1$mcD$sp() {
        return BoxesRunTime.unboxToDouble(this.copy$default$1());
    }

    public <T1, T2> int copy$default$1$mcI$sp() {
        return BoxesRunTime.unboxToInt(this.copy$default$1());
    }

    public <T1, T2> long copy$default$1$mcJ$sp() {
        return BoxesRunTime.unboxToLong(this.copy$default$1());
    }

    public <T1, T2> boolean copy$default$2$mcZ$sp() {
        return BoxesRunTime.unboxToBoolean(this.copy$default$2());
    }

    public <T1, T2> char copy$default$2$mcC$sp() {
        return BoxesRunTime.unboxToChar(this.copy$default$2());
    }

    public <T1, T2> double copy$default$2$mcD$sp() {
        return BoxesRunTime.unboxToDouble(this.copy$default$2());
    }

    public <T1, T2> int copy$default$2$mcI$sp() {
        return BoxesRunTime.unboxToInt(this.copy$default$2());
    }

    public <T1, T2> long copy$default$2$mcJ$sp() {
        return BoxesRunTime.unboxToLong(this.copy$default$2());
    }

    public boolean specInstance$() {
        return false;
    }

    public Tuple2(T1 _1, T2 _2) {
        this._1 = _1;
        this._2 = _2;
        Product$class.$init$(this);
        Product2$class.$init$(this);
    }
}

