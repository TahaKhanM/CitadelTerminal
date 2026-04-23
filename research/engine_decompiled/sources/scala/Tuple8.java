/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Option;
import scala.Product$class;
import scala.Product8;
import scala.Product8$class;
import scala.Serializable;
import scala.Tuple8$;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\teg\u0001B\u0001\u0003\u0001\u0016\u0011a\u0001V;qY\u0016D$\"A\u0002\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001UIa\u0001\u0005\u000e\u001eA\r2\u0013\u0006L\n\u0006\u0001\u001dYa&\r\t\u0003\u0011%i\u0011AA\u0005\u0003\u0015\t\u0011a!\u00118z%\u00164\u0007C\u0003\u0005\r\u001dearDI\u0013)W%\u0011QB\u0001\u0002\t!J|G-^2uqA\u0011q\u0002\u0005\u0007\u0001\t\u0019\t\u0002\u0001\"b\u0001%\t\u0011A+M\t\u0003'Y\u0001\"\u0001\u0003\u000b\n\u0005U\u0011!a\u0002(pi\"Lgn\u001a\t\u0003\u0011]I!\u0001\u0007\u0002\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u00105\u001111\u0004\u0001CC\u0002I\u0011!\u0001\u0016\u001a\u0011\u0005=iBA\u0002\u0010\u0001\t\u000b\u0007!C\u0001\u0002UgA\u0011q\u0002\t\u0003\u0007C\u0001!)\u0019\u0001\n\u0003\u0005Q#\u0004CA\b$\t\u0019!\u0003\u0001\"b\u0001%\t\u0011A+\u000e\t\u0003\u001f\u0019\"aa\n\u0001\u0005\u0006\u0004\u0011\"A\u0001+7!\ty\u0011\u0006\u0002\u0004+\u0001\u0011\u0015\rA\u0005\u0002\u0003)^\u0002\"a\u0004\u0017\u0005\r5\u0002AQ1\u0001\u0013\u0005\t!\u0006\b\u0005\u0002\t_%\u0011\u0001G\u0001\u0002\b!J|G-^2u!\tA!'\u0003\u00024\u0005\ta1+\u001a:jC2L'0\u00192mK\"AQ\u0007\u0001BK\u0002\u0013\u0005a'\u0001\u0002`cU\ta\u0002\u0003\u00059\u0001\tE\t\u0015!\u0003\u000f\u0003\ry\u0016\u0007\t\u0005\tu\u0001\u0011)\u001a!C\u0001w\u0005\u0011qLM\u000b\u00023!AQ\b\u0001B\tB\u0003%\u0011$A\u0002`e\u0001B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001Q\u0001\u0003?N*\u0012\u0001\b\u0005\t\u0005\u0002\u0011\t\u0012)A\u00059\u0005\u0019ql\r\u0011\t\u0011\u0011\u0003!Q3A\u0005\u0002\u0015\u000b!a\u0018\u001b\u0016\u0003}A\u0001b\u0012\u0001\u0003\u0012\u0003\u0006IaH\u0001\u0004?R\u0002\u0003\u0002C%\u0001\u0005+\u0007I\u0011\u0001&\u0002\u0005}+T#\u0001\u0012\t\u00111\u0003!\u0011#Q\u0001\n\t\n1aX\u001b!\u0011!q\u0005A!f\u0001\n\u0003y\u0015AA07+\u0005)\u0003\u0002C)\u0001\u0005#\u0005\u000b\u0011B\u0013\u0002\u0007}3\u0004\u0005\u0003\u0005T\u0001\tU\r\u0011\"\u0001U\u0003\tyv'F\u0001)\u0011!1\u0006A!E!\u0002\u0013A\u0013aA08A!A\u0001\f\u0001BK\u0002\u0013\u0005\u0011,\u0001\u0002`qU\t1\u0006\u0003\u0005\\\u0001\tE\t\u0015!\u0003,\u0003\ry\u0006\b\t\u0005\u0006;\u0002!\tAX\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0013}\u0003\u0017MY2eK\u001a<\u0007C\u0003\u0005\u0001\u001dearDI\u0013)W!)Q\u0007\u0018a\u0001\u001d!)!\b\u0018a\u00013!)q\b\u0018a\u00019!)A\t\u0018a\u0001?!)\u0011\n\u0018a\u0001E!)a\n\u0018a\u0001K!)1\u000b\u0018a\u0001Q!)\u0001\f\u0018a\u0001W!)\u0011\u000e\u0001C!U\u0006AAo\\*ue&tw\rF\u0001l!\ta\u0017/D\u0001n\u0015\tqw.\u0001\u0003mC:<'\"\u00019\u0002\t)\fg/Y\u0005\u0003e6\u0014aa\u0015;sS:<\u0007b\u0002;\u0001\u0003\u0003%\t!^\u0001\u0005G>\u0004\u00180F\u0007wsnlx0a\u0001\u0002\b\u0005-\u0011q\u0002\u000b\u0012o\u0006E\u00111CA\u000b\u0003/\tI\"a\u0007\u0002\u001e\u0005}\u0001C\u0004\u0005\u0001qjdh0!\u0001\u0002\u0006\u0005%\u0011Q\u0002\t\u0003\u001fe$Q!E:C\u0002I\u0001\"aD>\u0005\u000bm\u0019(\u0019\u0001\n\u0011\u0005=iH!\u0002\u0010t\u0005\u0004\u0011\u0002CA\b\u0000\t\u0015\t3O1\u0001\u0013!\ry\u00111\u0001\u0003\u0006IM\u0014\rA\u0005\t\u0004\u001f\u0005\u001dA!B\u0014t\u0005\u0004\u0011\u0002cA\b\u0002\f\u0011)!f\u001db\u0001%A\u0019q\"a\u0004\u0005\u000b5\u001a(\u0019\u0001\n\t\u000fU\u001a\b\u0013!a\u0001q\"9!h\u001dI\u0001\u0002\u0004Q\bbB t!\u0003\u0005\r\u0001 \u0005\b\tN\u0004\n\u00111\u0001\u007f\u0011!I5\u000f%AA\u0002\u0005\u0005\u0001\u0002\u0003(t!\u0003\u0005\r!!\u0002\t\u0011M\u001b\b\u0013!a\u0001\u0003\u0013A\u0001\u0002W:\u0011\u0002\u0003\u0007\u0011Q\u0002\u0005\n\u0003G\u0001\u0011\u0013!C\u0001\u0003K\tabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\n\u0002(\u0005u\u0012qHA!\u0003\u0007\n)%a\u0012\u0002J\u0005-SCAA\u0015U\rq\u00111F\u0016\u0003\u0003[\u0001B!a\f\u0002:5\u0011\u0011\u0011\u0007\u0006\u0005\u0003g\t)$A\u0005v]\u000eDWmY6fI*\u0019\u0011q\u0007\u0002\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002<\u0005E\"!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u00121\u0011#!\tC\u0002I!aaGA\u0011\u0005\u0004\u0011BA\u0002\u0010\u0002\"\t\u0007!\u0003\u0002\u0004\"\u0003C\u0011\rA\u0005\u0003\u0007I\u0005\u0005\"\u0019\u0001\n\u0005\r\u001d\n\tC1\u0001\u0013\t\u0019Q\u0013\u0011\u0005b\u0001%\u00111Q&!\tC\u0002IA\u0011\"a\u0014\u0001#\u0003%\t!!\u0015\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u00121KA,\u00033\nY&!\u0018\u0002`\u0005\u0005\u00141MA3+\t\t)FK\u0002\u001a\u0003W!a!EA'\u0005\u0004\u0011BAB\u000e\u0002N\t\u0007!\u0003\u0002\u0004\u001f\u0003\u001b\u0012\rA\u0005\u0003\u0007C\u00055#\u0019\u0001\n\u0005\r\u0011\niE1\u0001\u0013\t\u00199\u0013Q\nb\u0001%\u00111!&!\u0014C\u0002I!a!LA'\u0005\u0004\u0011\u0002\"CA5\u0001E\u0005I\u0011AA6\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"#!\u001c\u0002r\u0005M\u0014QOA<\u0003s\nY(! \u0002\u0000U\u0011\u0011q\u000e\u0016\u00049\u0005-BAB\t\u0002h\t\u0007!\u0003\u0002\u0004\u001c\u0003O\u0012\rA\u0005\u0003\u0007=\u0005\u001d$\u0019\u0001\n\u0005\r\u0005\n9G1\u0001\u0013\t\u0019!\u0013q\rb\u0001%\u00111q%a\u001aC\u0002I!aAKA4\u0005\u0004\u0011BAB\u0017\u0002h\t\u0007!\u0003C\u0005\u0002\u0004\u0002\t\n\u0011\"\u0001\u0002\u0006\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\"TCEAD\u0003\u0017\u000bi)a$\u0002\u0012\u0006M\u0015QSAL\u00033+\"!!#+\u0007}\tY\u0003\u0002\u0004\u0012\u0003\u0003\u0013\rA\u0005\u0003\u00077\u0005\u0005%\u0019\u0001\n\u0005\ry\t\tI1\u0001\u0013\t\u0019\t\u0013\u0011\u0011b\u0001%\u00111A%!!C\u0002I!aaJAA\u0005\u0004\u0011BA\u0002\u0016\u0002\u0002\n\u0007!\u0003\u0002\u0004.\u0003\u0003\u0013\rA\u0005\u0005\n\u0003;\u0003\u0011\u0013!C\u0001\u0003?\u000babY8qs\u0012\"WMZ1vYR$S'\u0006\n\u0002\"\u0006\u0015\u0016qUAU\u0003W\u000bi+a,\u00022\u0006MVCAARU\r\u0011\u00131\u0006\u0003\u0007#\u0005m%\u0019\u0001\n\u0005\rm\tYJ1\u0001\u0013\t\u0019q\u00121\u0014b\u0001%\u00111\u0011%a'C\u0002I!a\u0001JAN\u0005\u0004\u0011BAB\u0014\u0002\u001c\n\u0007!\u0003\u0002\u0004+\u00037\u0013\rA\u0005\u0003\u0007[\u0005m%\u0019\u0001\n\t\u0013\u0005]\u0006!%A\u0005\u0002\u0005e\u0016AD2paf$C-\u001a4bk2$HEN\u000b\u0013\u0003w\u000by,!1\u0002D\u0006\u0015\u0017qYAe\u0003\u0017\fi-\u0006\u0002\u0002>*\u001aQ%a\u000b\u0005\rE\t)L1\u0001\u0013\t\u0019Y\u0012Q\u0017b\u0001%\u00111a$!.C\u0002I!a!IA[\u0005\u0004\u0011BA\u0002\u0013\u00026\n\u0007!\u0003\u0002\u0004(\u0003k\u0013\rA\u0005\u0003\u0007U\u0005U&\u0019\u0001\n\u0005\r5\n)L1\u0001\u0013\u0011%\t\t\u000eAI\u0001\n\u0003\t\u0019.\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001c\u0016%\u0005U\u0017\u0011\\An\u0003;\fy.!9\u0002d\u0006\u0015\u0018q]\u000b\u0003\u0003/T3\u0001KA\u0016\t\u0019\t\u0012q\u001ab\u0001%\u001111$a4C\u0002I!aAHAh\u0005\u0004\u0011BAB\u0011\u0002P\n\u0007!\u0003\u0002\u0004%\u0003\u001f\u0014\rA\u0005\u0003\u0007O\u0005='\u0019\u0001\n\u0005\r)\nyM1\u0001\u0013\t\u0019i\u0013q\u001ab\u0001%!I\u00111\u001e\u0001\u0012\u0002\u0013\u0005\u0011Q^\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00139+I\ty/a=\u0002v\u0006]\u0018\u0011`A~\u0003{\fyP!\u0001\u0016\u0005\u0005E(fA\u0016\u0002,\u00111\u0011#!;C\u0002I!aaGAu\u0005\u0004\u0011BA\u0002\u0010\u0002j\n\u0007!\u0003\u0002\u0004\"\u0003S\u0014\rA\u0005\u0003\u0007I\u0005%(\u0019\u0001\n\u0005\r\u001d\nIO1\u0001\u0013\t\u0019Q\u0013\u0011\u001eb\u0001%\u00111Q&!;C\u0002IA\u0011B!\u0002\u0001\u0003\u0003%\tEa\u0002\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005Y\u0007\"\u0003B\u0006\u0001\u0005\u0005I\u0011\tB\u0007\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XC\u0001B\b!\u0015\u0011\tBa\u0006\u0017\u001b\t\u0011\u0019BC\u0002\u0003\u0016\t\t!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011IBa\u0005\u0003\u0011%#XM]1u_JD\u0011B!\b\u0001\u0003\u0003%\tAa\b\u0002\u0011\r\fg.R9vC2$BA!\t\u0003(A\u0019\u0001Ba\t\n\u0007\t\u0015\"AA\u0004C_>dW-\u00198\t\u0013\t%\"1DA\u0001\u0002\u00041\u0012a\u0001=%c!I!Q\u0006\u0001\u0002\u0002\u0013\u0005#qF\u0001\tQ\u0006\u001c\bnQ8eKR\u0011!\u0011\u0007\t\u0004\u0011\tM\u0012b\u0001B\u001b\u0005\t\u0019\u0011J\u001c;\t\u0013\te\u0002!!A\u0005B\tm\u0012AB3rk\u0006d7\u000f\u0006\u0003\u0003\"\tu\u0002\"\u0003B\u0015\u0005o\t\t\u00111\u0001\u0017Q\u001d\u0001!\u0011\tB$\u0005\u0017\u00022\u0001\u0003B\"\u0013\r\u0011)E\u0001\u0002\u0016I\u0016\u0004(/Z2bi\u0016$\u0017J\u001c5fe&$\u0018M\\2fC\t\u0011I%\u0001\u0018UkBdWm\u001d\u0011xS2d\u0007EY3![\u0006$W\r\t4j]\u0006d\u0007%\u001b8!C\u00022W\u000f^;sK\u00022XM]:j_:t\u0013E\u0001B'\u0003\u0019\u0011d&M\u0019/a\u001dI!\u0011\u000b\u0002\u0002\u0002#\u0005!1K\u0001\u0007)V\u0004H.\u001a\u001d\u0011\u0007!\u0011)F\u0002\u0005\u0002\u0005\u0005\u0005\t\u0012\u0001B,'\u0011\u0011)fB\u0019\t\u000fu\u0013)\u0006\"\u0001\u0003\\Q\u0011!1\u000b\u0005\tS\nU\u0013\u0011!C#U\"Q!\u0011\rB+\u0003\u0003%\tIa\u0019\u0002\u000b\u0005\u0004\b\u000f\\=\u0016%\t\u0015$1\u000eB8\u0005g\u00129Ha\u001f\u0003\u0000\t\r%q\u0011\u000b\u0013\u0005O\u0012IIa#\u0003\u000e\n=%\u0011\u0013BJ\u0005+\u00139\n\u0005\n\t\u0001\t%$Q\u000eB9\u0005k\u0012IH! \u0003\u0002\n\u0015\u0005cA\b\u0003l\u00111\u0011Ca\u0018C\u0002I\u00012a\u0004B8\t\u0019Y\"q\fb\u0001%A\u0019qBa\u001d\u0005\ry\u0011yF1\u0001\u0013!\ry!q\u000f\u0003\u0007C\t}#\u0019\u0001\n\u0011\u0007=\u0011Y\b\u0002\u0004%\u0005?\u0012\rA\u0005\t\u0004\u001f\t}DAB\u0014\u0003`\t\u0007!\u0003E\u0002\u0010\u0005\u0007#aA\u000bB0\u0005\u0004\u0011\u0002cA\b\u0003\b\u00121QFa\u0018C\u0002IAq!\u000eB0\u0001\u0004\u0011I\u0007C\u0004;\u0005?\u0002\rA!\u001c\t\u000f}\u0012y\u00061\u0001\u0003r!9AIa\u0018A\u0002\tU\u0004bB%\u0003`\u0001\u0007!\u0011\u0010\u0005\b\u001d\n}\u0003\u0019\u0001B?\u0011\u001d\u0019&q\fa\u0001\u0005\u0003Cq\u0001\u0017B0\u0001\u0004\u0011)\t\u0003\u0006\u0003\u001c\nU\u0013\u0011!CA\u0005;\u000bq!\u001e8baBd\u00170\u0006\n\u0003 \n-&q\u0016BZ\u0005o\u0013YLa0\u0003D\n\u001dG\u0003\u0002BQ\u0005\u0013\u0004R\u0001\u0003BR\u0005OK1A!*\u0003\u0005\u0019y\u0005\u000f^5p]B\u0011\u0002\u0002\u0001BU\u0005[\u0013\tL!.\u0003:\nu&\u0011\u0019Bc!\ry!1\u0016\u0003\u0007#\te%\u0019\u0001\n\u0011\u0007=\u0011y\u000b\u0002\u0004\u001c\u00053\u0013\rA\u0005\t\u0004\u001f\tMFA\u0002\u0010\u0003\u001a\n\u0007!\u0003E\u0002\u0010\u0005o#a!\tBM\u0005\u0004\u0011\u0002cA\b\u0003<\u00121AE!'C\u0002I\u00012a\u0004B`\t\u00199#\u0011\u0014b\u0001%A\u0019qBa1\u0005\r)\u0012IJ1\u0001\u0013!\ry!q\u0019\u0003\u0007[\te%\u0019\u0001\n\t\u0015\t-'\u0011TA\u0001\u0002\u0004\u00119+A\u0002yIAB!Ba4\u0003V\u0005\u0005I\u0011\u0002Bi\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0005\tM\u0007c\u00017\u0003V&\u0019!q[7\u0003\r=\u0013'.Z2u\u0001")
public class Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>
implements Product8<T1, T2, T3, T4, T5, T6, T7, T8>,
Serializable {
    private final T1 _1;
    private final T2 _2;
    private final T3 _3;
    private final T4 _4;
    private final T5 _5;
    private final T6 _6;
    private final T7 _7;
    private final T8 _8;

    public static <T1, T2, T3, T4, T5, T6, T7, T8> Option<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>> unapply(Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> tuple8) {
        return Tuple8$.MODULE$.unapply(tuple8);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> apply(T1 T1, T2 T2, T3 T3, T4 T4, T5 T5, T6 T6, T7 T7, T8 T8) {
        return Tuple8$.MODULE$.apply(T1, T2, T3, T4, T5, T6, T7, T8);
    }

    @Override
    public int productArity() {
        return Product8$class.productArity(this);
    }

    @Override
    public Object productElement(int n) throws IndexOutOfBoundsException {
        return Product8$class.productElement(this, n);
    }

    @Override
    public T1 _1() {
        return this._1;
    }

    @Override
    public T2 _2() {
        return this._2;
    }

    @Override
    public T3 _3() {
        return this._3;
    }

    @Override
    public T4 _4() {
        return this._4;
    }

    @Override
    public T5 _5() {
        return this._5;
    }

    @Override
    public T6 _6() {
        return this._6;
    }

    @Override
    public T7 _7() {
        return this._7;
    }

    @Override
    public T8 _8() {
        return this._8;
    }

    public String toString() {
        return new StringBuilder().append((Object)"(").append(this._1()).append((Object)",").append(this._2()).append((Object)",").append(this._3()).append((Object)",").append(this._4()).append((Object)",").append(this._5()).append((Object)",").append(this._6()).append((Object)",").append(this._7()).append((Object)",").append(this._8()).append((Object)")").toString();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> copy(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8) {
        return new Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>(_1, _2, _3, _4, _5, _6, _7, _8);
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8> T1 copy$default$1() {
        return this._1();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8> T2 copy$default$2() {
        return this._2();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8> T3 copy$default$3() {
        return this._3();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8> T4 copy$default$4() {
        return this._4();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8> T5 copy$default$5() {
        return this._5();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8> T6 copy$default$6() {
        return this._6();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8> T7 copy$default$7() {
        return this._7();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8> T8 copy$default$8() {
        return this._8();
    }

    @Override
    public String productPrefix() {
        return "Tuple8";
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof Tuple8;
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
        boolean bl3;
        boolean bl4;
        boolean bl5;
        boolean bl6;
        boolean bl7;
        boolean bl8;
        if (this == x$1) return true;
        if (!(x$1 instanceof Tuple8)) return false;
        boolean bl9 = true;
        if (!bl9) return false;
        Tuple8 tuple8 = (Tuple8)x$1;
        T1 T1 = tuple8._1();
        T1 T12 = this._1();
        if (T12 == T1) {
            bl8 = true;
        } else {
            if (T12 == null) {
                return false;
            }
            bl8 = T12 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T12, T1) : (T12 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T12, T1) : T12.equals(T1));
        }
        if (!bl8) return false;
        T2 T2 = tuple8._2();
        T2 T22 = this._2();
        if (T22 == T2) {
            bl7 = true;
        } else {
            if (T22 == null) {
                return false;
            }
            bl7 = T22 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T22, T2) : (T22 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T22, T2) : T22.equals(T2));
        }
        if (!bl7) return false;
        T3 T3 = tuple8._3();
        T3 T32 = this._3();
        if (T32 == T3) {
            bl6 = true;
        } else {
            if (T32 == null) {
                return false;
            }
            bl6 = T32 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T32, T3) : (T32 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T32, T3) : T32.equals(T3));
        }
        if (!bl6) return false;
        T4 T4 = tuple8._4();
        T4 T42 = this._4();
        if (T42 == T4) {
            bl5 = true;
        } else {
            if (T42 == null) {
                return false;
            }
            bl5 = T42 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T42, T4) : (T42 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T42, T4) : T42.equals(T4));
        }
        if (!bl5) return false;
        T5 T5 = tuple8._5();
        T5 T52 = this._5();
        if (T52 == T5) {
            bl4 = true;
        } else {
            if (T52 == null) {
                return false;
            }
            bl4 = T52 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T52, T5) : (T52 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T52, T5) : T52.equals(T5));
        }
        if (!bl4) return false;
        T6 T6 = tuple8._6();
        T6 T62 = this._6();
        if (T62 == T6) {
            bl3 = true;
        } else {
            if (T62 == null) {
                return false;
            }
            bl3 = T62 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T62, T6) : (T62 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T62, T6) : T62.equals(T6));
        }
        if (!bl3) return false;
        T7 T7 = tuple8._7();
        T7 T72 = this._7();
        if (T72 == T7) {
            bl2 = true;
        } else {
            if (T72 == null) {
                return false;
            }
            bl2 = T72 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T72, T7) : (T72 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T72, T7) : T72.equals(T7));
        }
        if (!bl2) return false;
        T8 T8 = tuple8._8();
        T8 T82 = this._8();
        if (T82 == T8) {
            bl = true;
        } else {
            if (T82 == null) {
                return false;
            }
            bl = T82 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T82, T8) : (T82 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T82, T8) : T82.equals(T8));
        }
        if (!bl) return false;
        if (!tuple8.canEqual(this)) return false;
        return true;
    }

    public Tuple8(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
        this._5 = _5;
        this._6 = _6;
        this._7 = _7;
        this._8 = _8;
        Product$class.$init$(this);
        Product8$class.$init$(this);
    }
}

