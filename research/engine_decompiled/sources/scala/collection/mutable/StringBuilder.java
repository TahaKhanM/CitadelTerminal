/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import java.util.Locale;
import java.util.regex.PatternSyntaxException;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.IndexedSeq$class;
import scala.collection.IndexedSeqLike$class;
import scala.collection.IndexedSeqOptimized$class;
import scala.collection.IterableLike$class;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.SeqLike$class;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.immutable.StringLike;
import scala.collection.immutable.StringLike$class;
import scala.collection.mutable.AbstractSeq;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.GrowingBuilder;
import scala.collection.mutable.IndexedSeq;
import scala.collection.mutable.IndexedSeqView;
import scala.math.Ordered$class;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.util.matching.Regex;

@ScalaSignature(bytes="\u0006\u0001\rEa\u0001B\u0001\u0003\u0005%\u0011Qb\u0015;sS:<')^5mI\u0016\u0014(BA\u0002\u0005\u0003\u001diW\u000f^1cY\u0016T!!\u0002\u0004\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019r\u0001\u0001\u0006\u00135u!c\u0006E\u0002\f\u00199i\u0011AA\u0005\u0003\u001b\t\u00111\"\u00112tiJ\f7\r^*fcB\u0011q\u0002E\u0007\u0002\r%\u0011\u0011C\u0002\u0002\u0005\u0007\"\f'\u000f\u0005\u0002\u001415\tAC\u0003\u0002\u0016-\u0005!A.\u00198h\u0015\u00059\u0012\u0001\u00026bm\u0006L!!\u0007\u000b\u0003\u0019\rC\u0017M]*fcV,gnY3\u0011\u0007-Yb\"\u0003\u0002\u001d\u0005\tQ\u0011J\u001c3fq\u0016$7+Z9\u0011\u0007y\t3%D\u0001 \u0015\t\u0001C!A\u0005j[6,H/\u00192mK&\u0011!e\b\u0002\u000b'R\u0014\u0018N\\4MS.,\u0007CA\u0006\u0001!\u0011YQED\u0014\n\u0005\u0019\u0012!a\u0002\"vS2$WM\u001d\t\u0003Q-r!aD\u0015\n\u0005)2\u0011A\u0002)sK\u0012,g-\u0003\u0002-[\t11\u000b\u001e:j]\u001eT!A\u000b\u0004\u0011\u0005=y\u0013B\u0001\u0019\u0007\u00051\u0019VM]5bY&T\u0018M\u00197f\u0011!\u0011\u0004A!b\u0001\n\u0013\u0019\u0014AC;oI\u0016\u0014H._5oOV\tA\u0007\u0005\u0002\u0014k%\u0011\u0011\u0001\u0006\u0005\to\u0001\u0011\t\u0011)A\u0005i\u0005YQO\u001c3fe2L\u0018N\\4!\u0011\u0015I\u0004\u0001\"\u0001;\u0003\u0019a\u0014N\\5u}Q\u00111e\u000f\u0005\u0006ea\u0002\r\u0001\u000e\u0005\u0007{\u0001\u0001K\u0011\u000b \u0002\u001dQD\u0017n]\"pY2,7\r^5p]V\t1\u0005\u0003\u0004A\u0001\u0001&\t&Q\u0001\ri>\u001cu\u000e\u001c7fGRLwN\u001c\u000b\u0003G\tCQaQ A\u0002\r\nAA]3qe\"1Q\t\u0001Q\u0005R\u0019\u000b!B\\3x\u0005VLG\u000eZ3s+\u00059\u0005\u0003B\u0006I\u001d\rJ!!\u0013\u0002\u0003\u001d\u001d\u0013xn^5oO\n+\u0018\u000e\u001c3fe\")\u0011\b\u0001C\u0001\u0017R\u00191\u0005T)\t\u000b5S\u0005\u0019\u0001(\u0002\u0019%t\u0017\u000e^\"ba\u0006\u001c\u0017\u000e^=\u0011\u0005=y\u0015B\u0001)\u0007\u0005\rIe\u000e\u001e\u0005\u0006%*\u0003\raJ\u0001\nS:LGOV1mk\u0016DQ!\u000f\u0001\u0005\u0002Q#\u0012a\t\u0005\u0006s\u0001!\tA\u0016\u000b\u0003G]CQ\u0001W+A\u00029\u000b\u0001bY1qC\u000eLG/\u001f\u0005\u0006s\u0001!\tA\u0017\u000b\u0003GmCQ\u0001X-A\u0002\u001d\n1a\u001d;s\u0011\u0015q\u0006\u0001\"\u0001`\u0003\u001d!x.\u0011:sCf,\u0012\u0001\u0019\t\u0004\u001f\u0005t\u0011B\u00012\u0007\u0005\u0015\t%O]1z\u0011\u0015!\u0007\u0001\"\u0011f\u0003\u0019aWM\\4uQV\ta\nC\u0003h\u0001\u0011\u0005\u0001.\u0001\u0006mK:<G\u000f[0%KF$\"!\u001b7\u0011\u0005=Q\u0017BA6\u0007\u0005\u0011)f.\u001b;\t\u000b54\u0007\u0019\u0001(\u0002\u00039DQa\u001c\u0001\u0005\u0002A\fQa\u00197fCJ$\u0012!\u001b\u0005\u0006e\u0002!\ta]\u0001\ng\u0016$H*\u001a8hi\"$\"!\u001b;\t\u000bU\f\b\u0019\u0001(\u0002\u00071,g\u000eC\u0003Y\u0001\u0011\u0005Q\rC\u0003y\u0001\u0011\u0005\u00110\u0001\bf]N,(/Z\"ba\u0006\u001c\u0017\u000e^=\u0015\u0005%T\b\"B>x\u0001\u0004q\u0015a\u00038fo\u000e\u000b\u0007/Y2jifDQ! \u0001\u0005\u0002y\faa\u00195be\u0006#HC\u0001\b\u0000\u0011\u0019\t\t\u0001 a\u0001\u001d\u0006)\u0011N\u001c3fq\"9\u0011Q\u0001\u0001\u0005B\u0005\u001d\u0011!B1qa2LHc\u0001\b\u0002\n!9\u0011\u0011AA\u0002\u0001\u0004q\u0005bBA\u0007\u0001\u0011\u0005\u0011qB\u0001\rI\u0016dW\r^3DQ\u0006\u0014\u0018\t\u001e\u000b\u0004G\u0005E\u0001bBA\u0001\u0003\u0017\u0001\rA\u0014\u0005\b\u0003+\u0001A\u0011AA\f\u0003%\u0019X\r^\"iCJ\fE\u000fF\u0003j\u00033\tY\u0002C\u0004\u0002\u0002\u0005M\u0001\u0019\u0001(\t\u000f\u0005u\u00111\u0003a\u0001\u001d\u0005\u00111\r\u001b\u0005\b\u0003C\u0001A\u0011AA\u0012\u0003\u0019)\b\u000fZ1uKR)\u0011.!\n\u0002*!9\u0011qEA\u0010\u0001\u0004q\u0015!A5\t\u000f\u0005-\u0012q\u0004a\u0001\u001d\u0005\t1\rC\u0004\u00020\u0001!\t!!\r\u0002\u0013M,(m\u001d;sS:<GcA\u0014\u00024!9\u0011QGA\u0017\u0001\u0004q\u0015!B:uCJ$\bbBA\u0018\u0001\u0011\u0005\u0011\u0011\b\u000b\u0006O\u0005m\u0012Q\b\u0005\b\u0003k\t9\u00041\u0001O\u0011\u001d\ty$a\u000eA\u00029\u000b1!\u001a8e\u0011\u001d\t\u0019\u0005\u0001C\u0001\u0003\u000b\n1b];c'\u0016\fX/\u001a8dKR)!#a\u0012\u0002J!9\u0011QGA!\u0001\u0004q\u0005bBA \u0003\u0003\u0002\rA\u0014\u0005\b\u0003\u001b\u0002A\u0011AA(\u0003!!\u0003\u000f\\;tI\u0015\fH\u0003BA)\u0003'j\u0011\u0001\u0001\u0005\b\u0003+\nY\u00051\u0001\u000f\u0003\u0005A\bbBA-\u0001\u0011\u0005\u00111L\u0001\u000eIAdWo\u001d\u0013qYV\u001cH%Z9\u0015\t\u0005E\u0013Q\f\u0005\b\u0003?\n9\u00061\u0001(\u0003\u0005\u0019\bbBA2\u0001\u0011\u0005\u0011QM\u0001\nCB\u0004XM\u001c3BY2$2aIA4\u0011\u001d\tI'!\u0019A\u0002\u001d\n!\u0001_:\t\u000f\u00055\u0004\u0001\"\u0001\u0002p\u0005)A\u0005\u001d7vgR!\u0011\u0011KA9\u0011\u001d\t)&a\u001bA\u00029Aq!!\u001e\u0001\t\u0003\t9(\u0001\u0004baB,g\u000e\u001a\u000b\u0004G\u0005e\u0004\u0002CA+\u0003g\u0002\r!a\u001f\u0011\u0007=\ti(C\u0002\u0002\u0000\u0019\u00111!\u00118z\u0011\u001d\t)\b\u0001C\u0001\u0003\u0007#2aIAC\u0011\u001d\ty&!!A\u0002\u001dBq!!\u001e\u0001\t\u0003\tI\tF\u0002$\u0003\u0017Cq!!$\u0002\b\u0002\u00071%\u0001\u0002tE\"9\u00111\r\u0001\u0005\u0002\u0005EEcA\u0012\u0002\u0014\"A\u0011\u0011NAH\u0001\u0004\t)\nE\u0003\u0002\u0018\u0006ee\"D\u0001\u0005\u0013\r\tY\n\u0002\u0002\u0010)J\fg/\u001a:tC\ndWm\u00148dK\"9\u00111\r\u0001\u0005\u0002\u0005}EcA\u0012\u0002\"\"9\u0011\u0011NAO\u0001\u0004\u0001\u0007bBA2\u0001\u0011\u0005\u0011Q\u0015\u000b\bG\u0005\u001d\u0016\u0011VAW\u0011\u001d\tI'a)A\u0002\u0001Dq!a+\u0002$\u0002\u0007a*\u0001\u0004pM\u001a\u001cX\r\u001e\u0005\u0007k\u0006\r\u0006\u0019\u0001(\t\u000f\u0005U\u0004\u0001\"\u0001\u00022R\u00191%a-\t\u0011\u0005U\u0013q\u0016a\u0001\u0003k\u00032aDA\\\u0013\r\tIL\u0002\u0002\b\u0005>|G.Z1o\u0011\u001d\t)\b\u0001C\u0001\u0003{#2aIA`\u0011!\t)&a/A\u0002\u0005\u0005\u0007cA\b\u0002D&\u0019\u0011Q\u0019\u0004\u0003\t\tKH/\u001a\u0005\b\u0003k\u0002A\u0011AAe)\r\u0019\u00131\u001a\u0005\t\u0003+\n9\r1\u0001\u0002NB\u0019q\"a4\n\u0007\u0005EgAA\u0003TQ>\u0014H\u000fC\u0004\u0002v\u0001!\t!!6\u0015\u0007\r\n9\u000eC\u0004\u0002V\u0005M\u0007\u0019\u0001(\t\u000f\u0005U\u0004\u0001\"\u0001\u0002\\R\u00191%!8\t\u0011\u0005U\u0013\u0011\u001ca\u0001\u0003?\u00042aDAq\u0013\r\t\u0019O\u0002\u0002\u0005\u0019>tw\rC\u0004\u0002v\u0001!\t!a:\u0015\u0007\r\nI\u000f\u0003\u0005\u0002V\u0005\u0015\b\u0019AAv!\ry\u0011Q^\u0005\u0004\u0003_4!!\u0002$m_\u0006$\bbBA;\u0001\u0011\u0005\u00111\u001f\u000b\u0004G\u0005U\b\u0002CA+\u0003c\u0004\r!a>\u0011\u0007=\tI0C\u0002\u0002|\u001a\u0011a\u0001R8vE2,\u0007bBA;\u0001\u0011\u0005\u0011q \u000b\u0004G\t\u0005\u0001bBA+\u0003{\u0004\rA\u0004\u0005\b\u0005\u000b\u0001A\u0011\u0001B\u0004\u0003\u0019!W\r\\3uKR)1E!\u0003\u0003\f!9\u0011Q\u0007B\u0002\u0001\u0004q\u0005bBA \u0005\u0007\u0001\rA\u0014\u0005\b\u0005\u001f\u0001A\u0011\u0001B\t\u0003\u001d\u0011X\r\u001d7bG\u0016$ra\tB\n\u0005+\u00119\u0002C\u0004\u00026\t5\u0001\u0019\u0001(\t\u000f\u0005}\"Q\u0002a\u0001\u001d\"1AL!\u0004A\u0002\u001dBqAa\u0007\u0001\t\u0003\u0011i\"A\u0005j]N,'\u000f^!mYRI1Ea\b\u0003\"\t\r\"Q\u0005\u0005\b\u0003\u0003\u0011I\u00021\u0001O\u0011\u0019a&\u0011\u0004a\u0001A\"9\u00111\u0016B\r\u0001\u0004q\u0005BB;\u0003\u001a\u0001\u0007a\nC\u0004\u0003*\u0001!\tAa\u000b\u0002\r%t7/\u001a:u)\u0015\u0019#Q\u0006B\u0018\u0011\u001d\t\tAa\nA\u00029C\u0001\"!\u0016\u0003(\u0001\u0007\u00111\u0010\u0005\b\u0005S\u0001A\u0011\u0001B\u001a)\u0015\u0019#Q\u0007B\u001c\u0011\u001d\t\tA!\rA\u00029Cq!!\u0016\u00032\u0001\u0007q\u0005C\u0004\u0003\u001c\u0001!\tAa\u000f\u0015\u000b\r\u0012iDa\u0010\t\u000f\u0005\u0005!\u0011\ba\u0001\u001d\"A\u0011\u0011\u000eB\u001d\u0001\u0004\t)\nC\u0004\u0003\u001c\u0001!\tAa\u0011\u0015\u000b\r\u0012)Ea\u0012\t\u000f\u0005\u0005!\u0011\ta\u0001\u001d\"9\u0011\u0011\u000eB!\u0001\u0004\u0001\u0007b\u0002B\u0015\u0001\u0011\u0005!1\n\u000b\u0006G\t5#q\n\u0005\b\u0003\u0003\u0011I\u00051\u0001O\u0011!\t)F!\u0013A\u0002\u0005U\u0006b\u0002B\u0015\u0001\u0011\u0005!1\u000b\u000b\u0006G\tU#q\u000b\u0005\b\u0003\u0003\u0011\t\u00061\u0001O\u0011!\t)F!\u0015A\u0002\u0005\u0005\u0007b\u0002B\u0015\u0001\u0011\u0005!1\f\u000b\u0006G\tu#q\f\u0005\b\u0003\u0003\u0011I\u00061\u0001O\u0011!\t)F!\u0017A\u0002\u00055\u0007b\u0002B\u0015\u0001\u0011\u0005!1\r\u000b\u0006G\t\u0015$q\r\u0005\b\u0003\u0003\u0011\t\u00071\u0001O\u0011\u001d\t)F!\u0019A\u00029CqA!\u000b\u0001\t\u0003\u0011Y\u0007F\u0003$\u0005[\u0012y\u0007C\u0004\u0002\u0002\t%\u0004\u0019\u0001(\t\u0011\u0005U#\u0011\u000ea\u0001\u0003?DqA!\u000b\u0001\t\u0003\u0011\u0019\bF\u0003$\u0005k\u00129\bC\u0004\u0002\u0002\tE\u0004\u0019\u0001(\t\u0011\u0005U#\u0011\u000fa\u0001\u0003WDqA!\u000b\u0001\t\u0003\u0011Y\bF\u0003$\u0005{\u0012y\bC\u0004\u0002\u0002\te\u0004\u0019\u0001(\t\u0011\u0005U#\u0011\u0010a\u0001\u0003oDqA!\u000b\u0001\t\u0003\u0011\u0019\tF\u0003$\u0005\u000b\u00139\tC\u0004\u0002\u0002\t\u0005\u0005\u0019\u0001(\t\u000f\u0005U#\u0011\u0011a\u0001\u001d!9!1\u0012\u0001\u0005\u0002\t5\u0015aB5oI\u0016DxJ\u001a\u000b\u0004\u001d\n=\u0005B\u0002/\u0003\n\u0002\u0007q\u0005C\u0004\u0003\f\u0002!\tAa%\u0015\u000b9\u0013)Ja&\t\rq\u0013\t\n1\u0001(\u0011\u001d\u0011IJ!%A\u00029\u000b\u0011B\u001a:p[&sG-\u001a=\t\u000f\tu\u0005\u0001\"\u0001\u0003 \u0006YA.Y:u\u0013:$W\r_(g)\rq%\u0011\u0015\u0005\u00079\nm\u0005\u0019A\u0014\t\u000f\tu\u0005\u0001\"\u0001\u0003&R)aJa*\u0003*\"1ALa)A\u0002\u001dBqA!'\u0003$\u0002\u0007a\n\u0003\u0004\u0003.\u0002!\tEP\u0001\be\u00164XM]:fQ!\u0011YK!-\u0003>\n\u0005\u0007\u0003\u0002BZ\u0005sk!A!.\u000b\u0007\t]f!\u0001\u0006b]:|G/\u0019;j_:LAAa/\u00036\nIQ.[4sCRLwN\\\u0011\u0003\u0005\u007f\u000b\u0011\u000f\u0019:fm\u0016\u00148/\u001a1!e\u0016$XO\u001d8tA\u0005\u0004c.Z<!S:\u001cH/\u00198dK:\u0002\u0003%V:fA\u0001\u0014XM^3sg\u0016\u001cuN\u001c;f]R\u001c\b\r\t;pAU\u0004H-\u0019;fA%t\u0007\u0005\u001d7bG\u0016\u0004\u0013M\u001c3!e\u0016$XO\u001d8!i\"\fG\u000fI*ue&twMQ;jY\u0012,'\u000fI5ug\u0016dgML\u0011\u0003\u0005\u0007\fQA\r\u00189]ABaAa2\u0001\t\u0003\"\u0016!B2m_:,\u0007B\u0002Bf\u0001\u0011\u0005A+A\bsKZ,'o]3D_:$XM\u001c;t\u0011\u001d\u0011y\r\u0001C!\u0005#\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0005'\u00042a\u0005Bk\u0013\taC\u0003C\u0004\u0003Z\u0002!\tEa7\u0002\u00115\\7\u000b\u001e:j]\u001e,\"Aa5\t\u000f\t}\u0007\u0001\"\u0001\u0003b\u00061!/Z:vYR$\u0012a\n\u0015\b\u0001\t\u0015(1\u001eBw!\ry!q]\u0005\u0004\u0005S4!\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=!IyVk\u00010.T\u000f\u0000a\u0002By\u0005!\u0005!1_\u0001\u000e'R\u0014\u0018N\\4Ck&dG-\u001a:\u0011\u0007-\u0011)P\u0002\u0004\u0002\u0005!\u0005!q_\n\u0006\u0005k\u0014IP\f\t\u0004\u001f\tm\u0018b\u0001B\u007f\r\t1\u0011I\\=SK\u001aDq!\u000fB{\t\u0003\u0019\t\u0001\u0006\u0002\u0003t\"1QI!>\u0005\u0002yB!ba\u0002\u0003v\u0006\u0005I\u0011BB\u0005\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0005\r-\u0001cA\n\u0004\u000e%\u00191q\u0002\u000b\u0003\r=\u0013'.Z2u\u0001")
public final class StringBuilder
extends AbstractSeq<Object>
implements CharSequence,
IndexedSeq<Object>,
StringLike<StringBuilder>,
Builder<Object, String>,
Serializable {
    public static final long serialVersionUID = -8525408645367278351L;
    private final java.lang.StringBuilder underlying;

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
    public <NewTo> Builder<Object, NewTo> mapResult(Function1<String, NewTo> f) {
        return Builder$class.mapResult(this, f);
    }

    @Override
    public Growable $plus$eq(Object elem1, Object elem2, Seq elems) {
        return Growable$class.$plus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Growable<Object> $plus$plus$eq(TraversableOnce<Object> xs) {
        return Growable$class.$plus$plus$eq(this, xs);
    }

    @Override
    public Object slice(int from2, int until2) {
        return StringLike$class.slice(this, from2, until2);
    }

    @Override
    public String $times(int n) {
        return StringLike$class.$times(this, n);
    }

    @Override
    public int compare(String other) {
        return StringLike$class.compare(this, other);
    }

    @Override
    public String stripLineEnd() {
        return StringLike$class.stripLineEnd(this);
    }

    @Override
    public Iterator<String> linesWithSeparators() {
        return StringLike$class.linesWithSeparators(this);
    }

    @Override
    public Iterator<String> lines() {
        return StringLike$class.lines(this);
    }

    @Override
    public Iterator<String> linesIterator() {
        return StringLike$class.linesIterator(this);
    }

    @Override
    public String capitalize() {
        return StringLike$class.capitalize(this);
    }

    @Override
    public String stripPrefix(String prefix) {
        return StringLike$class.stripPrefix(this, prefix);
    }

    @Override
    public String stripSuffix(String suffix) {
        return StringLike$class.stripSuffix(this, suffix);
    }

    @Override
    public String replaceAllLiterally(String literal, String replacement) {
        return StringLike$class.replaceAllLiterally(this, literal, replacement);
    }

    @Override
    public String stripMargin(char marginChar) {
        return StringLike$class.stripMargin(this, marginChar);
    }

    @Override
    public String stripMargin() {
        return StringLike$class.stripMargin(this);
    }

    @Override
    public String[] split(char separator) {
        return StringLike$class.split((StringLike)this, separator);
    }

    @Override
    public String[] split(char[] separators) throws PatternSyntaxException {
        return StringLike$class.split((StringLike)this, separators);
    }

    @Override
    public Regex r() {
        return StringLike$class.r(this);
    }

    @Override
    public Regex r(Seq<String> groupNames) {
        return StringLike$class.r(this, groupNames);
    }

    @Override
    public boolean toBoolean() {
        return StringLike$class.toBoolean(this);
    }

    @Override
    public byte toByte() {
        return StringLike$class.toByte(this);
    }

    @Override
    public short toShort() {
        return StringLike$class.toShort(this);
    }

    @Override
    public int toInt() {
        return StringLike$class.toInt(this);
    }

    @Override
    public long toLong() {
        return StringLike$class.toLong(this);
    }

    @Override
    public float toFloat() {
        return StringLike$class.toFloat(this);
    }

    @Override
    public double toDouble() {
        return StringLike$class.toDouble(this);
    }

    @Override
    public <B> Object toArray(ClassTag<B> evidence$1) {
        return StringLike$class.toArray(this, evidence$1);
    }

    @Override
    public String format(Seq<Object> args) {
        return StringLike$class.format(this, args);
    }

    @Override
    public String formatLocal(Locale l, Seq<Object> args) {
        return StringLike$class.formatLocal(this, l, args);
    }

    @Override
    public boolean $less(Object that) {
        return Ordered$class.$less(this, that);
    }

    @Override
    public boolean $greater(Object that) {
        return Ordered$class.$greater(this, that);
    }

    @Override
    public boolean $less$eq(Object that) {
        return Ordered$class.$less$eq(this, that);
    }

    @Override
    public boolean $greater$eq(Object that) {
        return Ordered$class.$greater$eq(this, that);
    }

    @Override
    public int compareTo(Object that) {
        return Ordered$class.compareTo(this, that);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$reduceLeft(Function2 op) {
        return TraversableOnce$class.reduceLeft(this, op);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$reduceRight(Function2 op) {
        return IterableLike$class.reduceRight(this, op);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$zip(GenIterable that, CanBuildFrom bf) {
        return IterableLike$class.zip(this, that, bf);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$head() {
        return IterableLike$class.head(this);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$tail() {
        return TraversableLike$class.tail(this);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$last() {
        return TraversableLike$class.last(this);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$init() {
        return TraversableLike$class.init(this);
    }

    @Override
    public /* synthetic */ boolean scala$collection$IndexedSeqOptimized$$super$sameElements(GenIterable that) {
        return IterableLike$class.sameElements(this, that);
    }

    @Override
    public /* synthetic */ boolean scala$collection$IndexedSeqOptimized$$super$endsWith(GenSeq that) {
        return SeqLike$class.endsWith(this, that);
    }

    @Override
    public boolean isEmpty() {
        return IndexedSeqOptimized$class.isEmpty(this);
    }

    @Override
    public <U> void foreach(Function1<Object, U> f) {
        IndexedSeqOptimized$class.foreach(this, f);
    }

    @Override
    public boolean forall(Function1<Object, Object> p) {
        return IndexedSeqOptimized$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<Object, Object> p) {
        return IndexedSeqOptimized$class.exists(this, p);
    }

    @Override
    public Option<Object> find(Function1<Object, Object> p) {
        return IndexedSeqOptimized$class.find(this, p);
    }

    @Override
    public <B> B foldLeft(B z, Function2<B, Object, B> op) {
        return (B)IndexedSeqOptimized$class.foldLeft(this, z, op);
    }

    @Override
    public <B> B foldRight(B z, Function2<Object, B, B> op) {
        return (B)IndexedSeqOptimized$class.foldRight(this, z, op);
    }

    @Override
    public <B> B reduceLeft(Function2<B, Object, B> op) {
        return (B)IndexedSeqOptimized$class.reduceLeft(this, op);
    }

    @Override
    public <B> B reduceRight(Function2<Object, B, B> op) {
        return (B)IndexedSeqOptimized$class.reduceRight(this, op);
    }

    @Override
    public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<StringBuilder, Tuple2<A1, B>, That> bf) {
        return (That)IndexedSeqOptimized$class.zip(this, that, bf);
    }

    @Override
    public <A1, That> That zipWithIndex(CanBuildFrom<StringBuilder, Tuple2<A1, Object>, That> bf) {
        return (That)IndexedSeqOptimized$class.zipWithIndex(this, bf);
    }

    @Override
    public Object head() {
        return IndexedSeqOptimized$class.head(this);
    }

    @Override
    public Object tail() {
        return IndexedSeqOptimized$class.tail(this);
    }

    @Override
    public Object last() {
        return IndexedSeqOptimized$class.last(this);
    }

    @Override
    public Object init() {
        return IndexedSeqOptimized$class.init(this);
    }

    @Override
    public Object take(int n) {
        return IndexedSeqOptimized$class.take(this, n);
    }

    @Override
    public Object drop(int n) {
        return IndexedSeqOptimized$class.drop(this, n);
    }

    @Override
    public Object takeRight(int n) {
        return IndexedSeqOptimized$class.takeRight(this, n);
    }

    @Override
    public Object dropRight(int n) {
        return IndexedSeqOptimized$class.dropRight(this, n);
    }

    @Override
    public Tuple2<StringBuilder, StringBuilder> splitAt(int n) {
        return IndexedSeqOptimized$class.splitAt(this, n);
    }

    @Override
    public Object takeWhile(Function1 p) {
        return IndexedSeqOptimized$class.takeWhile(this, p);
    }

    @Override
    public Object dropWhile(Function1 p) {
        return IndexedSeqOptimized$class.dropWhile(this, p);
    }

    @Override
    public Tuple2<StringBuilder, StringBuilder> span(Function1<Object, Object> p) {
        return IndexedSeqOptimized$class.span(this, p);
    }

    @Override
    public <B> boolean sameElements(GenIterable<B> that) {
        return IndexedSeqOptimized$class.sameElements(this, that);
    }

    @Override
    public <B> void copyToArray(Object xs, int start, int len) {
        IndexedSeqOptimized$class.copyToArray(this, xs, start, len);
    }

    @Override
    public int lengthCompare(int len) {
        return IndexedSeqOptimized$class.lengthCompare(this, len);
    }

    @Override
    public int segmentLength(Function1<Object, Object> p, int from2) {
        return IndexedSeqOptimized$class.segmentLength(this, p, from2);
    }

    @Override
    public int indexWhere(Function1<Object, Object> p, int from2) {
        return IndexedSeqOptimized$class.indexWhere(this, p, from2);
    }

    @Override
    public int lastIndexWhere(Function1<Object, Object> p, int end) {
        return IndexedSeqOptimized$class.lastIndexWhere(this, p, end);
    }

    @Override
    public Iterator<Object> reverseIterator() {
        return IndexedSeqOptimized$class.reverseIterator(this);
    }

    @Override
    public <B> boolean startsWith(GenSeq<B> that, int offset) {
        return IndexedSeqOptimized$class.startsWith(this, that, offset);
    }

    @Override
    public <B> boolean endsWith(GenSeq<B> that) {
        return IndexedSeqOptimized$class.endsWith(this, that);
    }

    @Override
    public GenericCompanion<IndexedSeq> companion() {
        return scala.collection.mutable.IndexedSeq$class.companion(this);
    }

    @Override
    public IndexedSeq<Object> seq() {
        return scala.collection.mutable.IndexedSeq$class.seq(this);
    }

    @Override
    public IndexedSeq toCollection(Object repr) {
        return scala.collection.mutable.IndexedSeqLike$class.toCollection(this, repr);
    }

    @Override
    public Object view() {
        return scala.collection.mutable.IndexedSeqLike$class.view(this);
    }

    @Override
    public IndexedSeqView<Object, IndexedSeq<Object>> view(int from2, int until2) {
        return scala.collection.mutable.IndexedSeqLike$class.view(this, from2, until2);
    }

    @Override
    public int hashCode() {
        return IndexedSeqLike$class.hashCode(this);
    }

    @Override
    public Iterator<Object> iterator() {
        return IndexedSeqLike$class.iterator(this);
    }

    @Override
    public <A1> Buffer<A1> toBuffer() {
        return IndexedSeqLike$class.toBuffer(this);
    }

    private java.lang.StringBuilder underlying() {
        return this.underlying;
    }

    @Override
    public StringBuilder thisCollection() {
        return this;
    }

    public StringBuilder toCollection(StringBuilder repr) {
        return repr;
    }

    @Override
    public GrowingBuilder<Object, StringBuilder> newBuilder() {
        return new GrowingBuilder<Object, StringBuilder>(new StringBuilder());
    }

    /*
     * WARNING - void declaration
     */
    public char[] toArray() {
        void var1_1;
        char[] arr = new char[this.length()];
        this.underlying().getChars(0, this.length(), arr, 0);
        return var1_1;
    }

    @Override
    public int length() {
        return this.underlying().length();
    }

    public void length_$eq(int n) {
        this.underlying().setLength(n);
    }

    @Override
    public void clear() {
        this.setLength(0);
    }

    public void setLength(int len) {
        this.underlying().setLength(len);
    }

    public int capacity() {
        return this.underlying().capacity();
    }

    public void ensureCapacity(int newCapacity) {
        this.underlying().ensureCapacity(newCapacity);
    }

    @Override
    public char charAt(int index) {
        return this.underlying().charAt(index);
    }

    @Override
    public char apply(int index) {
        return this.underlying().charAt(index);
    }

    public StringBuilder deleteCharAt(int index) {
        this.underlying().deleteCharAt(index);
        return this;
    }

    public void setCharAt(int index, char ch) {
        this.underlying().setCharAt(index, ch);
    }

    @Override
    public void update(int i, char c) {
        this.setCharAt(i, c);
    }

    public String substring(int start) {
        return this.substring(start, this.length());
    }

    public String substring(int start, int end) {
        return this.underlying().substring(start, end);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return this.substring(start, end);
    }

    public StringBuilder $plus$eq(char x) {
        this.append(x);
        return this;
    }

    public StringBuilder $plus$plus$eq(String s2) {
        this.underlying().append(s2);
        return this;
    }

    public StringBuilder appendAll(String xs) {
        this.underlying().append(xs);
        return this;
    }

    public StringBuilder $plus(char x) {
        this.$plus$eq(x);
        return this;
    }

    public StringBuilder append(Object x) {
        this.underlying().append(String.valueOf(x));
        return this;
    }

    public StringBuilder append(String s2) {
        this.underlying().append(s2);
        return this;
    }

    public StringBuilder append(StringBuilder sb) {
        this.underlying().append(sb);
        return this;
    }

    public StringBuilder appendAll(TraversableOnce<Object> xs) {
        return this.appendAll((char[])xs.toArray(ClassTag$.MODULE$.Char()));
    }

    public StringBuilder appendAll(char[] xs) {
        this.underlying().append(xs);
        return this;
    }

    public StringBuilder appendAll(char[] xs, int offset, int len) {
        this.underlying().append(xs, offset, len);
        return this;
    }

    public StringBuilder append(boolean x) {
        this.underlying().append(x);
        return this;
    }

    public StringBuilder append(byte x) {
        return this.append((int)x);
    }

    public StringBuilder append(short x) {
        return this.append((int)x);
    }

    public StringBuilder append(int x) {
        this.underlying().append(x);
        return this;
    }

    public StringBuilder append(long x) {
        this.underlying().append(x);
        return this;
    }

    public StringBuilder append(float x) {
        this.underlying().append(x);
        return this;
    }

    public StringBuilder append(double x) {
        this.underlying().append(x);
        return this;
    }

    public StringBuilder append(char x) {
        this.underlying().append(x);
        return this;
    }

    public StringBuilder delete(int start, int end) {
        this.underlying().delete(start, end);
        return this;
    }

    public StringBuilder replace(int start, int end, String str) {
        this.underlying().replace(start, end, str);
        return this;
    }

    public StringBuilder insertAll(int index, char[] str, int offset, int len) {
        this.underlying().insert(index, str, offset, len);
        return this;
    }

    public StringBuilder insert(int index, Object x) {
        return this.insert(index, String.valueOf(x));
    }

    public StringBuilder insert(int index, String x) {
        this.underlying().insert(index, x);
        return this;
    }

    public StringBuilder insertAll(int index, TraversableOnce<Object> xs) {
        return this.insertAll(index, (char[])xs.toArray(ClassTag$.MODULE$.Char()));
    }

    public StringBuilder insertAll(int index, char[] xs) {
        this.underlying().insert(index, xs);
        return this;
    }

    public StringBuilder insert(int index, boolean x) {
        return this.insert(index, String.valueOf(x));
    }

    public StringBuilder insert(int index, byte x) {
        return this.insert(index, (int)x);
    }

    public StringBuilder insert(int index, short x) {
        return this.insert(index, (int)x);
    }

    public StringBuilder insert(int index, int x) {
        return this.insert(index, String.valueOf(x));
    }

    public StringBuilder insert(int index, long x) {
        return this.insert(index, String.valueOf(x));
    }

    public StringBuilder insert(int index, float x) {
        return this.insert(index, String.valueOf(x));
    }

    public StringBuilder insert(int index, double x) {
        return this.insert(index, String.valueOf(x));
    }

    public StringBuilder insert(int index, char x) {
        return this.insert(index, String.valueOf(x));
    }

    @Override
    public int indexOf(String str) {
        return this.underlying().indexOf(str);
    }

    @Override
    public int indexOf(String str, int fromIndex) {
        return this.underlying().indexOf(str, fromIndex);
    }

    @Override
    public int lastIndexOf(String str) {
        return this.underlying().lastIndexOf(str);
    }

    @Override
    public int lastIndexOf(String str, int fromIndex) {
        return this.underlying().lastIndexOf(str, fromIndex);
    }

    @Override
    public StringBuilder reverse() {
        return new StringBuilder(new java.lang.StringBuilder(this.underlying()).reverse());
    }

    @Override
    public StringBuilder clone() {
        return new StringBuilder(new java.lang.StringBuilder(this.underlying()));
    }

    public StringBuilder reverseContents() {
        this.underlying().reverse();
        return this;
    }

    @Override
    public String toString() {
        return this.underlying().toString();
    }

    @Override
    public String mkString() {
        return this.toString();
    }

    @Override
    public String result() {
        return this.toString();
    }

    public StringBuilder(java.lang.StringBuilder underlying) {
        this.underlying = underlying;
        IndexedSeqLike$class.$init$(this);
        IndexedSeq$class.$init$(this);
        scala.collection.mutable.IndexedSeqLike$class.$init$(this);
        scala.collection.mutable.IndexedSeq$class.$init$(this);
        IndexedSeqOptimized$class.$init$(this);
        Ordered$class.$init$(this);
        StringLike$class.$init$(this);
        Growable$class.$init$(this);
        Builder$class.$init$(this);
    }

    public StringBuilder(int initCapacity, String initValue) {
        this(new java.lang.StringBuilder(initValue.length() + initCapacity).append(initValue));
    }

    public StringBuilder() {
        this(16, "");
    }

    public StringBuilder(int capacity) {
        this(capacity, "");
    }

    public StringBuilder(String str) {
        this(16, str);
    }
}

