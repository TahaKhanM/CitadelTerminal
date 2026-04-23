/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function1;
import scala.Option;
import scala.Serializable;
import scala.collection.AbstractSeq;
import scala.collection.AbstractTraversable;
import scala.collection.GenSeqLike$class;
import scala.collection.IndexedSeq;
import scala.collection.IndexedSeqLike$class;
import scala.collection.Iterator;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.IndexedSeq$class;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.NumericRange$;
import scala.collection.immutable.Range$;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Seq$class;
import scala.collection.immutable.Traversable$class;
import scala.collection.mutable.Buffer;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParSeq;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Numeric$BigDecimalIsFractional$;
import scala.math.Numeric$BigIntIsIntegral$;
import scala.math.Numeric$ByteIsIntegral$;
import scala.math.Numeric$CharIsIntegral$;
import scala.math.Numeric$DoubleAsIfIntegral$;
import scala.math.Numeric$FloatAsIfIntegral$;
import scala.math.Numeric$IntIsIntegral$;
import scala.math.Numeric$LongIsIntegral$;
import scala.math.Numeric$ShortIsIntegral$;
import scala.math.Ordering;
import scala.math.package$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001\tmg!B\u0001\u0003\u0003\u0003I!\u0001\u0004(v[\u0016\u0014\u0018n\u0019*b]\u001e,'BA\u0002\u0005\u0003%IW.\\;uC\ndWM\u0003\u0002\u0006\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001!\u0006\u0002\u000b#M!\u0001aC\u000e !\raQbD\u0007\u0002\t%\u0011a\u0002\u0002\u0002\f\u0003\n\u001cHO]1diN+\u0017\u000f\u0005\u0002\u0011#1\u0001A!\u0002\n\u0001\u0005\u0004\u0019\"!\u0001+\u0012\u0005QA\u0002CA\u000b\u0017\u001b\u00051\u0011BA\f\u0007\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!F\r\n\u0005i1!aA!osB\u0019A$H\b\u000e\u0003\tI!A\b\u0002\u0003\u0015%sG-\u001a=fIN+\u0017\u000f\u0005\u0002\u0016A%\u0011\u0011E\u0002\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\tG\u0001\u0011)\u0019!C\u0001I\u0005)1\u000f^1siV\tq\u0002\u0003\u0005'\u0001\t\u0005\t\u0015!\u0003\u0010\u0003\u0019\u0019H/\u0019:uA!A\u0001\u0006\u0001BC\u0002\u0013\u0005A%A\u0002f]\u0012D\u0001B\u000b\u0001\u0003\u0002\u0003\u0006IaD\u0001\u0005K:$\u0007\u0005\u0003\u0005-\u0001\t\u0015\r\u0011\"\u0001%\u0003\u0011\u0019H/\u001a9\t\u00119\u0002!\u0011!Q\u0001\n=\tQa\u001d;fa\u0002B\u0001\u0002\r\u0001\u0003\u0006\u0004%\t!M\u0001\fSNLen\u00197vg&4X-F\u00013!\t)2'\u0003\u00025\r\t9!i\\8mK\u0006t\u0007\u0002\u0003\u001c\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001a\u0002\u0019%\u001c\u0018J\\2mkNLg/\u001a\u0011\t\u0011a\u0002!\u0011!Q\u0001\fe\n1A\\;n!\rQTh\u0004\b\u0003+mJ!\u0001\u0010\u0004\u0002\u000fA\f7m[1hK&\u0011ah\u0010\u0002\t\u0013:$Xm\u001a:bY*\u0011AH\u0002\u0005\u0006\u0003\u0002!\tAQ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000b\r3u\tS%\u0015\u0005\u0011+\u0005c\u0001\u000f\u0001\u001f!)\u0001\b\u0011a\u0002s!)1\u0005\u0011a\u0001\u001f!)\u0001\u0006\u0011a\u0001\u001f!)A\u0006\u0011a\u0001\u001f!)\u0001\u0007\u0011a\u0001e!A1\n\u0001EC\u0002\u0013%A*\u0001\tok6\u0014\u0016M\\4f\u000b2,W.\u001a8ugV\tQ\n\u0005\u0002\u0016\u001d&\u0011qJ\u0002\u0002\u0004\u0013:$\b\u0002C)\u0001\u0011\u0003\u0005\u000b\u0015B'\u0002#9,XNU1oO\u0016,E.Z7f]R\u001c\b\u0005C\u0003T\u0001\u0011\u0005C*\u0001\u0004mK:<G\u000f\u001b\u0005\u0006+\u0002!\t%M\u0001\bSN,U\u000e\u001d;z\u0011!9\u0006\u0001#b\u0001\n\u0003\"\u0013\u0001\u00027bgRD\u0001\"\u0017\u0001\t\u0002\u0003\u0006KaD\u0001\u0006Y\u0006\u001cH\u000f\t\u0005\u00067\u0002!\t\u0001X\u0001\u0003Ef$\"\u0001R/\t\u000byS\u0006\u0019A\b\u0002\u000f9,wo\u0015;fa\")\u0001\r\u0001D\u0001C\u0006!1m\u001c9z)\u0011!%m\u00193\t\u000b\rz\u0006\u0019A\b\t\u000b!z\u0006\u0019A\b\t\u000b1z\u0006\u0019A\b\t\u000b\u0019\u0004A\u0011I4\u0002\u000f\u0019|'/Z1dQV\u0011\u0001N\u001d\u000b\u0003S2\u0004\"!\u00066\n\u0005-4!\u0001B+oSRDQ!\\3A\u00029\f\u0011A\u001a\t\u0005+=|\u0011/\u0003\u0002q\r\tIa)\u001e8di&|g.\r\t\u0003!I$Qa]3C\u0002M\u0011\u0011!\u0016\u0005\u0006k\u0002!IA^\u0001\u0013SN<\u0016\u000e\u001e5j]\n{WO\u001c3be&,7\u000f\u0006\u00023o\")\u0001\u0010\u001ea\u0001\u001f\u0005!Q\r\\3n\u0011\u0015Q\b\u0001\"\u0003|\u00039awnY1uS>t\u0017I\u001a;fe:#\"a\u0004?\t\u000buL\b\u0019A'\u0002\u00039Daa \u0001\u0005\n\u0005\u0005\u0011!\u00048fo\u0016k\u0007\u000f^=SC:<W\r\u0006\u0003\u0002\u0004\t\u0015\u0002#BA\u0003\u0003czab\u0001\u000f\u0002\b\u001d9\u0011\u0011\u0002\u0002\t\u0002\u0005-\u0011\u0001\u0004(v[\u0016\u0014\u0018n\u0019*b]\u001e,\u0007c\u0001\u000f\u0002\u000e\u00191\u0011A\u0001E\u0001\u0003\u001f\u0019R!!\u0004\u0002\u0012}\u00012!FA\n\u0013\r\t)B\u0002\u0002\u0007\u0003:L(+\u001a4\t\u000f\u0005\u000bi\u0001\"\u0001\u0002\u001aQ\u0011\u00111\u0002\u0005\t\u0003;\ti\u0001\"\u0001\u0002 \u0005)1m\\;oiV!\u0011\u0011EA\u0016))\t\u0019#!\f\u00020\u0005E\u00121\u0007\u000b\u0004\u001b\u0006\u0015\u0002b\u0002\u001d\u0002\u001c\u0001\u000f\u0011q\u0005\t\u0005uu\nI\u0003E\u0002\u0011\u0003W!aAEA\u000e\u0005\u0004\u0019\u0002bB\u0012\u0002\u001c\u0001\u0007\u0011\u0011\u0006\u0005\bQ\u0005m\u0001\u0019AA\u0015\u0011\u001da\u00131\u0004a\u0001\u0003SAa\u0001MA\u000e\u0001\u0004\u0011daBA\u001c\u0003\u001b\u0001\u0011\u0011\b\u0002\n\u0013:\u001cG.^:jm\u0016,B!a\u000f\u0002BM!\u0011QGA\u001f!\u0011a\u0002!a\u0010\u0011\u0007A\t\t\u0005\u0002\u0004\u0013\u0003k\u0011\ra\u0005\u0005\fG\u0005U\"\u0011!Q\u0001\n\u0005}\"\u0005C\u0006)\u0003k\u0011\t\u0011)A\u0005\u0003\u007f9\u0003b\u0003\u0017\u00026\t\u0005\t\u0015!\u0003\u0002@-B!\u0002OA\u001b\u0005\u0003\u0005\u000b1BA&!\u0011QT(a\u0010\t\u000f\u0005\u000b)\u0004\"\u0001\u0002PQA\u0011\u0011KA-\u00037\ni\u0006\u0006\u0003\u0002T\u0005]\u0003CBA+\u0003k\ty$\u0004\u0002\u0002\u000e!9\u0001(!\u0014A\u0004\u0005-\u0003bB\u0012\u0002N\u0001\u0007\u0011q\b\u0005\bQ\u00055\u0003\u0019AA \u0011\u001da\u0013Q\na\u0001\u0003\u007fAq\u0001YA\u001b\t\u0003\t\t\u0007\u0006\u0005\u0002T\u0005\r\u0014QMA4\u0011\u001d\u0019\u0013q\fa\u0001\u0003\u007fAq\u0001KA0\u0001\u0004\ty\u0004C\u0004-\u0003?\u0002\r!a\u0010\t\u0011\u0005-\u0014Q\u0007C\u0001\u0003[\n\u0011\"\u001a=dYV\u001c\u0018N^3\u0016\u0005\u0005=\u0004CBA+\u0003c\nyDB\u0004\u0002t\u00055\u0001!!\u001e\u0003\u0013\u0015C8\r\\;tSZ,W\u0003BA<\u0003{\u001aB!!\u001d\u0002zA!A\u0004AA>!\r\u0001\u0012Q\u0010\u0003\u0007%\u0005E$\u0019A\n\t\u0017\r\n\tH!A!\u0002\u0013\tYH\t\u0005\fQ\u0005E$\u0011!Q\u0001\n\u0005mt\u0005C\u0006-\u0003c\u0012\t\u0011)A\u0005\u0003wZ\u0003B\u0003\u001d\u0002r\t\u0005\t\u0015a\u0003\u0002\bB!!(PA>\u0011\u001d\t\u0015\u0011\u000fC\u0001\u0003\u0017#\u0002\"!$\u0002\u0014\u0006U\u0015q\u0013\u000b\u0005\u0003\u001f\u000b\t\n\u0005\u0004\u0002V\u0005E\u00141\u0010\u0005\bq\u0005%\u00059AAD\u0011\u001d\u0019\u0013\u0011\u0012a\u0001\u0003wBq\u0001KAE\u0001\u0004\tY\bC\u0004-\u0003\u0013\u0003\r!a\u001f\t\u000f\u0001\f\t\b\"\u0001\u0002\u001cRA\u0011qRAO\u0003?\u000b\t\u000bC\u0004$\u00033\u0003\r!a\u001f\t\u000f!\nI\n1\u0001\u0002|!9A&!'A\u0002\u0005m\u0004\u0002CAS\u0003c\"\t!a*\u0002\u0013%t7\r\\;tSZ,WCAAU!\u0019\t)&!\u000e\u0002|!A\u0011QVA\u0007\t\u0003\ty+A\u0003baBd\u00170\u0006\u0003\u00022\u0006eF\u0003CAZ\u0003\u007f\u000b\t-a1\u0015\t\u0005U\u00161\u0018\t\u0007\u0003+\n\t(a.\u0011\u0007A\tI\f\u0002\u0004\u0013\u0003W\u0013\ra\u0005\u0005\bq\u0005-\u00069AA_!\u0011QT(a.\t\u000f\r\nY\u000b1\u0001\u00028\"9\u0001&a+A\u0002\u0005]\u0006b\u0002\u0017\u0002,\u0002\u0007\u0011q\u0017\u0005\t\u0003K\u000bi\u0001\"\u0001\u0002HV!\u0011\u0011ZAi)!\tY-a6\u0002Z\u0006mG\u0003BAg\u0003'\u0004b!!\u0016\u00026\u0005=\u0007c\u0001\t\u0002R\u00121!#!2C\u0002MAq\u0001OAc\u0001\b\t)\u000e\u0005\u0003;{\u0005=\u0007bB\u0012\u0002F\u0002\u0007\u0011q\u001a\u0005\bQ\u0005\u0015\u0007\u0019AAh\u0011\u001da\u0013Q\u0019a\u0001\u0003\u001fD1\"a8\u0002\u000e\t\u0007I\u0011\u0001\u0003\u0002b\u0006yA-\u001a4bk2$xJ\u001d3fe&tw-\u0006\u0002\u0002dB9A$!:\u0002j\n\u0005\u0011bAAt\u0005\t\u0019Q*\u001991\t\u0005-\u0018\u0011 \t\u0007\u0003[\f\u00190a>\u000e\u0005\u0005=(bAAy\r\u0005!Q.\u0019;i\u0013\u0011\t)0a<\u0003\u000f9+X.\u001a:jGB\u0019\u0001#!?\u0005\u0017\u0005m\u0018Q`A\u0001\u0002\u0003\u0015\ta\u0005\u0002\u0004?\u0012\n\u0004\"CA\u0000\u0003\u001b\u0001\u000b\u0011BAr\u0003A!WMZ1vYR|%\u000fZ3sS:<\u0007\u0005\r\u0003\u0003\u0004\t-\u0001CBAw\u0005\u000b\u0011I!\u0003\u0003\u0003\b\u0005=(\u0001C(sI\u0016\u0014\u0018N\\4\u0011\u0007A\u0011Y\u0001B\u0006\u0003\u000e\u0005u\u0018\u0011!A\u0001\u0006\u0003\u0019\"aA0%e!Q!\u0011CA\u0007\u0003\u0003%IAa\u0005\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0003\u0005+\u0001BAa\u0006\u0003\"5\u0011!\u0011\u0004\u0006\u0005\u00057\u0011i\"\u0001\u0003mC:<'B\u0001B\u0010\u0003\u0011Q\u0017M^1\n\t\t\r\"\u0011\u0004\u0002\u0007\u001f\nTWm\u0019;\t\r\t\u001db\u00101\u0001\u0010\u0003\u00151\u0018\r\\;f\u0011\u001d\u0011Y\u0003\u0001C#\u0005[\tA\u0001^1lKR\u0019AIa\f\t\ru\u0014I\u00031\u0001N\u0011\u001d\u0011\u0019\u0004\u0001C#\u0005k\tA\u0001\u001a:paR\u0019AIa\u000e\t\ru\u0014\t\u00041\u0001N\u0011\u001d\ti\u000b\u0001C\u0001\u0005w!2a\u0004B\u001f\u0011\u001d\u0011yD!\u000fA\u00025\u000b1!\u001b3y\u0011\u001d\u0011\u0019\u0005\u0001C!\u0005\u000b\n1!\\5o+\u0011\u00119Ea\u0015\u0015\u0007=\u0011I\u0005\u0003\u0005\u0003L\t\u0005\u00039\u0001B'\u0003\ry'\u000f\u001a\t\u0006u\t=#\u0011K\u0005\u0004\u0005\u000fy\u0004c\u0001\t\u0003T\u0011A!Q\u000bB!\u0005\u0004\u00119F\u0001\u0002UcE\u0011q\u0002\u0007\u0005\b\u00057\u0002A\u0011\tB/\u0003\ri\u0017\r_\u000b\u0005\u0005?\u00129\u0007F\u0002\u0010\u0005CB\u0001Ba\u0013\u0003Z\u0001\u000f!1\r\t\u0006u\t=#Q\r\t\u0004!\t\u001dD\u0001\u0003B+\u00053\u0012\rAa\u0016\t\u0011\t-\u0004\u0001\"\u0001\u0003\u0005[\n\u0001\"\\1q%\u0006tw-Z\u000b\u0005\u0005_\u00129\b\u0006\u0003\u0003r\t\u0005E\u0003\u0002B:\u0005w\u0002B\u0001\b\u0001\u0003vA\u0019\u0001Ca\u001e\u0005\u000f\te$\u0011\u000eb\u0001'\t\t\u0011\t\u0003\u0005\u0003~\t%\u00049\u0001B@\u0003\u0011)h.^7\u0011\tij$Q\u000f\u0005\t\u0005\u0007\u0013I\u00071\u0001\u0003\u0006\u0006\u0011a-\u001c\t\u0006+=|!Q\u000f\u0005\b\u0005\u0013\u0003A\u0011\u0001BF\u00035\u0019wN\u001c;bS:\u001cH+\u001f9fIR\u0019!G!$\t\u000f\t=%q\u0011a\u0001\u001f\u0005\t\u0001\u0010C\u0004\u0003\u0014\u0002!\tE!&\u0002\u0011\r|g\u000e^1j]N,BAa&\u0003\u001eR\u0019!G!'\t\u0011\t=%\u0011\u0013a\u0001\u00057\u00032\u0001\u0005BO\t!\u0011yJ!%C\u0002\t]#AA!2\u0011\u001d\u0011\u0019\u000b\u0001C#\u0005K\u000b1a];n+\u0011\u00119Ka+\u0015\t\t%&q\u0016\t\u0004!\t-F\u0001\u0003BW\u0005C\u0013\rAa\u0016\u0003\u0003\tCq\u0001\u000fBQ\u0001\b\u0011\t\fE\u0003;\u0005g\u0013I+C\u0002\u0002v~B\u0011Ba.\u0001\u0011\u000b\u0007I\u0011\t'\u0002\u0011!\f7\u000f[\"pI\u0016D\u0011Ba/\u0001\u0011\u0003\u0005\u000b\u0015B'\u0002\u0013!\f7\u000f[\"pI\u0016\u0004\u0003b\u0002B`\u0001\u0011\u0005#\u0011Y\u0001\u0007KF,\u0018\r\\:\u0015\u0007I\u0012\u0019\rC\u0004\u0003F\nu\u0006\u0019\u0001\r\u0002\u000b=$\b.\u001a:\t\u000f\t%\u0007\u0001\"\u0011\u0003L\u0006AAo\\*ue&tw\r\u0006\u0002\u0003NB!!q\u001aBk\u001d\r)\"\u0011[\u0005\u0004\u0005'4\u0011A\u0002)sK\u0012,g-\u0003\u0003\u0003X\ne'AB*ue&twMC\u0002\u0003T\u001a\u0001")
public abstract class NumericRange<T>
extends AbstractSeq<T>
implements scala.collection.immutable.IndexedSeq<T>,
Serializable {
    private final T start;
    private final T end;
    private final T step;
    private final boolean isInclusive;
    private final Integral<T> num;
    private int numRangeElements;
    private T last;
    private int hashCode;
    private volatile byte bitmap$0;

    public static <T> Inclusive<T> inclusive(T t, T t2, T t3, Integral<T> integral) {
        return NumericRange$.MODULE$.inclusive(t, t2, t3, integral);
    }

    private int numRangeElements$lzycompute() {
        NumericRange numericRange = this;
        synchronized (numericRange) {
            if ((byte)(this.bitmap$0 & 1) == 0) {
                this.numRangeElements = NumericRange$.MODULE$.count(this.start(), this.end(), this.step(), this.isInclusive(), this.num);
                this.bitmap$0 = (byte)(this.bitmap$0 | 1);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.numRangeElements;
        }
    }

    private Object last$lzycompute() {
        NumericRange numericRange = this;
        synchronized (numericRange) {
            if ((byte)(this.bitmap$0 & 2) == 0) {
                this.last = this.length() == 0 ? Nil$.MODULE$.last() : this.locationAfterN(this.length() - 1);
                this.bitmap$0 = (byte)(this.bitmap$0 | 2);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.last;
        }
    }

    private int hashCode$lzycompute() {
        NumericRange numericRange = this;
        synchronized (numericRange) {
            if ((byte)(this.bitmap$0 & 4) == 0) {
                this.hashCode = IndexedSeqLike$class.hashCode(this);
                this.bitmap$0 = (byte)(this.bitmap$0 | 4);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.hashCode;
        }
    }

    @Override
    public GenericCompanion<scala.collection.immutable.IndexedSeq> companion() {
        return IndexedSeq$class.companion(this);
    }

    @Override
    public scala.collection.immutable.IndexedSeq<T> toIndexedSeq() {
        return IndexedSeq$class.toIndexedSeq(this);
    }

    @Override
    public scala.collection.immutable.IndexedSeq<T> seq() {
        return IndexedSeq$class.seq(this);
    }

    @Override
    public IndexedSeq<T> thisCollection() {
        return IndexedSeqLike$class.thisCollection(this);
    }

    @Override
    public IndexedSeq toCollection(Object repr) {
        return IndexedSeqLike$class.toCollection(this, repr);
    }

    @Override
    public Iterator<T> iterator() {
        return IndexedSeqLike$class.iterator(this);
    }

    @Override
    public <A1> Buffer<A1> toBuffer() {
        return IndexedSeqLike$class.toBuffer(this);
    }

    @Override
    public Seq<T> toSeq() {
        return Seq$class.toSeq(this);
    }

    @Override
    public Combiner<T, ParSeq<T>> parCombiner() {
        return Seq$class.parCombiner(this);
    }

    public T start() {
        return this.start;
    }

    public T end() {
        return this.end;
    }

    public T step() {
        return this.step;
    }

    public boolean isInclusive() {
        return this.isInclusive;
    }

    private int numRangeElements() {
        return (byte)(this.bitmap$0 & 1) == 0 ? this.numRangeElements$lzycompute() : this.numRangeElements;
    }

    @Override
    public int length() {
        return this.numRangeElements();
    }

    @Override
    public boolean isEmpty() {
        return this.length() == 0;
    }

    @Override
    public T last() {
        return (T)((byte)(this.bitmap$0 & 2) == 0 ? this.last$lzycompute() : this.last);
    }

    public NumericRange<T> by(T newStep) {
        return this.copy(this.start(), this.end(), newStep);
    }

    public abstract NumericRange<T> copy(T var1, T var2, T var3);

    @Override
    public <U> void foreach(Function1<T, U> f) {
        T current = this.start();
        for (int count2 = 0; count2 < this.length(); ++count2) {
            f.apply(current);
            current = this.num.mkNumericOps(current).$plus(this.step());
        }
    }

    private boolean isWithinBoundaries(T elem) {
        return !this.isEmpty() && (this.num.mkOrderingOps(this.step()).$greater(this.num.zero()) && this.num.mkOrderingOps(this.start()).$less$eq(elem) && this.num.mkOrderingOps(elem).$less$eq(this.last()) || this.num.mkOrderingOps(this.step()).$less(this.num.zero()) && this.num.mkOrderingOps(this.last()).$less$eq(elem) && this.num.mkOrderingOps(elem).$less$eq(this.start()));
    }

    private T locationAfterN(int n) {
        return this.num.mkNumericOps(this.start()).$plus(this.num.mkNumericOps(this.step()).$times(this.num.fromInt(n)));
    }

    private Exclusive<T> newEmptyRange(T value) {
        return NumericRange$.MODULE$.apply(value, value, this.step(), this.num);
    }

    @Override
    public final NumericRange<T> take(int n) {
        return n <= 0 || this.length() == 0 ? this.newEmptyRange(this.start()) : (n >= this.length() ? this : new Inclusive<T>(this.start(), this.locationAfterN(n - 1), this.step(), this.num));
    }

    @Override
    public final NumericRange<T> drop(int n) {
        return n <= 0 || this.length() == 0 ? this : (n >= this.length() ? this.newEmptyRange(this.end()) : ((NumericRange)this).copy(super.locationAfterN(n), this.end(), this.step()));
    }

    @Override
    public T apply(int idx) {
        if (idx < 0 || idx >= this.length()) {
            throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(idx)).toString());
        }
        return this.locationAfterN(idx);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public <T1> T min(Ordering<T1> ord) {
        Object object;
        if (ord != this.num) {
            Ordering ordering;
            Serializable serializable = new Serializable(this, ord){
                public static final long serialVersionUID = 0L;
                public final Ordering ord$1;

                public final boolean apply(Ordering<?> x$1) {
                    return this.ord$1 == x$1;
                }
                {
                    this.ord$1 = ord$1;
                }
            };
            Option option = NumericRange$.MODULE$.defaultOrdering().get(this.num);
            if (!(!option.isEmpty() && serializable.ord$1 == (ordering = (Ordering)option.get()))) {
                object = TraversableOnce$class.min(this, ord);
                return object;
            }
        }
        if (this.num.signum(this.step()) > 0) {
            object = this.start();
            return object;
        }
        object = this.last();
        return object;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public <T1> T max(Ordering<T1> ord) {
        Object object;
        if (ord != this.num) {
            Ordering ordering;
            Serializable serializable = new Serializable(this, ord){
                public static final long serialVersionUID = 0L;
                public final Ordering ord$2;

                public final boolean apply(Ordering<?> x$2) {
                    return this.ord$2 == x$2;
                }
                {
                    this.ord$2 = ord$2;
                }
            };
            Option option = NumericRange$.MODULE$.defaultOrdering().get(this.num);
            if (!(!option.isEmpty() && serializable.ord$2 == (ordering = (Ordering)option.get()))) {
                object = TraversableOnce$class.max(this, ord);
                return object;
            }
        }
        if (this.num.signum(this.step()) > 0) {
            object = this.last();
            return object;
        }
        object = this.start();
        return object;
    }

    public <A> NumericRange<A> mapRange(Function1<T, A> fm, Integral<A> unum) {
        return new NumericRange<A>(this, fm, unum, this){
            private NumericRange<T> underlyingRange;
            public final Function1 fm$1;
            private final Integral unum$1;
            private final NumericRange self$1;
            private volatile boolean bitmap$0;

            private NumericRange underlyingRange$lzycompute() {
                $anon$1 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.underlyingRange = this.self$1;
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    this.self$1 = null;
                    return this.underlyingRange;
                }
            }

            public NumericRange<A> copy(A start, A end, A step) {
                return this.isInclusive() ? NumericRange$.MODULE$.inclusive(start, end, step, this.unum$1) : NumericRange$.MODULE$.apply(start, end, step, this.unum$1);
            }

            private NumericRange<T> underlyingRange() {
                return this.bitmap$0 ? this.underlyingRange : this.underlyingRange$lzycompute();
            }

            public <U> void foreach(Function1<A, U> f) {
                this.underlyingRange().foreach(new Serializable(this, f){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ $anon$1 $outer;
                    private final Function1 f$1;

                    public final U apply(T x) {
                        return (U)this.f$1.apply(this.$outer.fm$1.apply(x));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.f$1 = f$1;
                    }
                });
            }

            public boolean isEmpty() {
                return this.underlyingRange().isEmpty();
            }

            public A apply(int idx) {
                return (A)this.fm$1.apply(this.underlyingRange().apply(idx));
            }

            public boolean containsTyped(A el) {
                return this.underlyingRange().exists(new Serializable(this, el){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ $anon$1 $outer;
                    private final Object el$1;

                    public final boolean apply(T x) {
                        Object object = this.el$1;
                        R r = this.$outer.fm$1.apply(x);
                        return r == object ? true : (r == null ? false : (r instanceof Number ? BoxesRunTime.equalsNumObject((Number)r, object) : (r instanceof Character ? BoxesRunTime.equalsCharObject((Character)r, object) : r.equals(object))));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.el$1 = el$1;
                    }
                });
            }
            {
                void var4_4;
                void var3_3;
                this.fm$1 = fm$1;
                this.unum$1 = var3_3;
                this.self$1 = var4_4;
                super(fm$1.apply($outer.start()), fm$1.apply($outer.end()), fm$1.apply($outer.step()), $outer.isInclusive(), var3_3);
            }
        };
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean containsTyped(T x) {
        if (!this.isWithinBoundaries(x)) return false;
        Object t = this.num.zero();
        Object t2 = this.num.mkNumericOps(this.num.mkNumericOps(x).$minus(this.start())).$percent(this.step());
        if (t2 == t) {
            return true;
        }
        if (t2 == null) {
            return false;
        }
        boolean bl = t2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)t2, t) : (t2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)t2, t) : t2.equals(t));
        if (!bl) return false;
        return true;
    }

    @Override
    public <A1> boolean contains(A1 x) {
        boolean bl;
        try {
            bl = this.containsTyped(x);
        }
        catch (ClassCastException classCastException) {
            bl = false;
        }
        return bl;
    }

    @Override
    public final <B> B sum(Numeric<B> num) {
        Object object;
        if (this.isEmpty()) {
            object = num.zero();
        } else if (this.numRangeElements() == 1) {
            object = this.head();
        } else if (num == Numeric$IntIsIntegral$.MODULE$ || num == Numeric$ShortIsIntegral$.MODULE$ || num == Numeric$ByteIsIntegral$.MODULE$ || num == Numeric$CharIsIntegral$.MODULE$) {
            long exact = (long)this.numRangeElements() * (num.toLong(this.head()) + (long)num.toInt(this.last())) / 2L;
            object = num.fromInt((int)exact);
        } else if (num == Numeric$LongIsIntegral$.MODULE$) {
            long l;
            long a = this.num.mkNumericOps(this.head()).toLong();
            long b = this.num.mkNumericOps(this.last()).toLong();
            if ((this.numRangeElements() & 1) == 0) {
                l = (long)(this.numRangeElements() / 2) * (a + b);
            } else {
                long ha = a / 2L;
                long hb = b / 2L;
                l = (long)this.numRangeElements() * (ha + hb + (a - 2L * ha + (b - 2L * hb)) / 2L);
            }
            long ans = l;
            object = BoxesRunTime.boxToLong(ans);
        } else if (num == Numeric$FloatAsIfIntegral$.MODULE$ || num == Numeric$DoubleAsIfIntegral$.MODULE$) {
            Integral numAsIntegral = (Integral)num;
            double a = package$.MODULE$.abs(numAsIntegral.mkNumericOps(this.head()).toDouble());
            double b = package$.MODULE$.abs(numAsIntegral.mkNumericOps(this.last()).toDouble());
            B two = num.fromInt(2);
            B nre = num.fromInt(this.numRangeElements());
            object = a > 1.0E38 || b > 1.0E38 ? numAsIntegral.mkNumericOps(nre).$times(numAsIntegral.mkNumericOps(numAsIntegral.mkNumericOps(this.head()).$div(two)).$plus(numAsIntegral.mkNumericOps(this.last()).$div(two))) : numAsIntegral.mkNumericOps(numAsIntegral.mkNumericOps(nre).$div(two)).$times(numAsIntegral.mkNumericOps(this.head()).$plus(this.last()));
        } else if (num == Numeric$BigIntIsIntegral$.MODULE$ || num == Numeric$BigDecimalIsFractional$.MODULE$) {
            Integral numAsIntegral = (Integral)num;
            object = numAsIntegral.mkNumericOps(numAsIntegral.mkNumericOps(num.fromInt(this.numRangeElements())).$times(numAsIntegral.mkNumericOps(this.head()).$plus(this.last()))).$div(num.fromInt(2));
        } else if (this.isEmpty()) {
            object = num.zero();
        } else {
            B acc = num.zero();
            Object i = this.head();
            for (int idx = 0; idx < this.length(); ++idx) {
                acc = num.plus(acc, i);
                i = this.num.mkNumericOps(i).$plus(this.step());
            }
            object = acc;
        }
        return object;
    }

    @Override
    public int hashCode() {
        return (byte)(this.bitmap$0 & 4) == 0 ? this.hashCode$lzycompute() : this.hashCode;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean equals(Object other) {
        boolean bl;
        if (!(other instanceof NumericRange)) return GenSeqLike$class.equals(this, other);
        NumericRange numericRange = (NumericRange)other;
        if (!numericRange.canEqual(this)) return false;
        if (this.length() != numericRange.length()) return false;
        if (this.length() == 0) return true;
        T t = numericRange.start();
        T t2 = this.start();
        if (t2 == t) {
            bl = true;
        } else {
            if (t2 == null) {
                return false;
            }
            bl = t2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)t2, t) : (t2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)t2, t) : t2.equals(t));
        }
        if (!bl) return false;
        T t3 = numericRange.last();
        T t4 = this.last();
        if (t4 == t3) {
            return true;
        }
        if (t4 == null) {
            return false;
        }
        boolean bl2 = t4 instanceof Number ? BoxesRunTime.equalsNumObject((Number)t4, t3) : (t4 instanceof Character ? BoxesRunTime.equalsCharObject((Character)t4, t3) : t4.equals(t3));
        if (!bl2) return false;
        return true;
    }

    @Override
    public String toString() {
        String endStr = this.length() > Range$.MODULE$.MAX_PRINT() ? ", ... )" : ")";
        return ((AbstractTraversable)this.take(Range$.MODULE$.MAX_PRINT())).mkString("NumericRange(", ", ", endStr);
    }

    public NumericRange(T start, T end, T step, boolean isInclusive, Integral<T> num) {
        this.start = start;
        this.end = end;
        this.step = step;
        this.isInclusive = isInclusive;
        this.num = num;
        Traversable$class.$init$(this);
        Iterable$class.$init$(this);
        Seq$class.$init$(this);
        IndexedSeqLike$class.$init$(this);
        scala.collection.IndexedSeq$class.$init$(this);
        IndexedSeq$class.$init$(this);
    }

    public static class Exclusive<T>
    extends NumericRange<T> {
        private final Integral<T> num;

        @Override
        public Exclusive<T> copy(T start, T end, T step) {
            return NumericRange$.MODULE$.apply(start, end, step, this.num);
        }

        public Inclusive<T> inclusive() {
            return NumericRange$.MODULE$.inclusive(super.start(), super.end(), super.step(), this.num);
        }

        public Exclusive(T start, T end, T step, Integral<T> num) {
            this.num = num;
            super(start, end, step, false, num);
        }
    }

    public static class Inclusive<T>
    extends NumericRange<T> {
        private final Integral<T> num;

        @Override
        public Inclusive<T> copy(T start, T end, T step) {
            return NumericRange$.MODULE$.inclusive(start, end, step, this.num);
        }

        public Exclusive<T> exclusive() {
            return NumericRange$.MODULE$.apply(super.start(), super.end(), super.step(), this.num);
        }

        public Inclusive(T start, T end, T step, Integral<T> num) {
            this.num = num;
            super(start, end, step, true, num);
        }
    }
}

