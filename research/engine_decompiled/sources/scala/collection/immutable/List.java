/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractSeq;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.GenTraversable;
import scala.collection.GenTraversableOnce;
import scala.collection.IterableLike$class;
import scala.collection.Iterator;
import scala.collection.LinearSeqLike;
import scala.collection.LinearSeqLike$class;
import scala.collection.LinearSeqOptimized;
import scala.collection.LinearSeqOptimized$class;
import scala.collection.Seq;
import scala.collection.SeqLike$class;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.LinearSeq;
import scala.collection.immutable.LinearSeq$class;
import scala.collection.immutable.List$;
import scala.collection.immutable.ListSerializeEnd$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Seq$class;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Stream$Empty$;
import scala.collection.immutable.Traversable$class;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParSeq;
import scala.math.Integral;
import scala.math.package$;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;

@ScalaSignature(bytes="\u0006\u0001\rUc!B\u0001\u0003\u0003CI!\u0001\u0002'jgRT!a\u0001\u0003\u0002\u0013%lW.\u001e;bE2,'BA\u0003\u0007\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u000f\u0005)1oY1mC\u000e\u0001QC\u0001\u0006\u0012'\u001d\u00011bG\u0010#S5\u00022\u0001D\u0007\u0010\u001b\u0005!\u0011B\u0001\b\u0005\u0005-\t%m\u001d;sC\u000e$8+Z9\u0011\u0005A\tB\u0002\u0001\u0003\u0007%\u0001!)\u0019A\n\u0003\u0003\u0005\u000b\"\u0001\u0006\r\u0011\u0005U1R\"\u0001\u0004\n\u0005]1!a\u0002(pi\"Lgn\u001a\t\u0003+eI!A\u0007\u0004\u0003\u0007\u0005s\u0017\u0010E\u0002\u001d;=i\u0011AA\u0005\u0003=\t\u0011\u0011\u0002T5oK\u0006\u00148+Z9\u0011\u0005U\u0001\u0013BA\u0011\u0007\u0005\u001d\u0001&o\u001c3vGR\u0004Ba\t\u0014\u0010Q5\tAE\u0003\u0002&\t\u00059q-\u001a8fe&\u001c\u0017BA\u0014%\u0005i9UM\\3sS\u000e$&/\u0019<feN\f'\r\\3UK6\u0004H.\u0019;f!\ta\u0002\u0001\u0005\u0003\rU=a\u0013BA\u0016\u0005\u0005Ia\u0015N\\3beN+\u0017o\u00149uS6L'0\u001a3\u0011\u0007q\u0001q\u0002\u0005\u0002/g5\tqF\u0003\u00021c\u0005\u0011\u0011n\u001c\u0006\u0002e\u0005!!.\u0019<b\u0013\t!tF\u0001\u0007TKJL\u0017\r\\5{C\ndW\rC\u00037\u0001\u0011\u0005q'\u0001\u0004=S:LGO\u0010\u000b\u0002Y!)\u0011\b\u0001C!u\u0005I1m\\7qC:LwN\\\u000b\u0002wA\u00191\u0005\u0010\u0015\n\u0005u\"#\u0001E$f]\u0016\u0014\u0018nY\"p[B\fg.[8o\u0011\u0015y\u0004A\"\u0001A\u0003\u001dI7/R7qif,\u0012!\u0011\t\u0003+\tK!a\u0011\u0004\u0003\u000f\t{w\u000e\\3b]\")Q\t\u0001D\u0001\r\u0006!\u0001.Z1e+\u0005y\u0001\"\u0002%\u0001\r\u0003I\u0015\u0001\u0002;bS2,\u0012\u0001\f\u0005\u0006\u0017\u0002!\t\u0001T\u0001\rI\r|Gn\u001c8%G>dwN\\\u000b\u0003\u001bB#\"AT*\u0011\u0007q\u0001q\n\u0005\u0002\u0011!\u0012)\u0011K\u0013b\u0001%\n\t!)\u0005\u0002\u00101!)AK\u0013a\u0001\u001f\u0006\t\u0001\u0010C\u0003W\u0001\u0011\u0005q+\u0001\n%G>dwN\u001c\u0013d_2|g\u000eJ2pY>tWC\u0001-\\)\tIF\fE\u0002\u001d\u0001i\u0003\"\u0001E.\u0005\u000bE+&\u0019\u0001*\t\u000bu+\u0006\u0019A-\u0002\rA\u0014XMZ5y\u0011\u0015y\u0006\u0001\"\u0001a\u0003i\u0011XM^3sg\u0016|FeY8m_:$3m\u001c7p]\u0012\u001aw\u000e\\8o+\t\tG\r\u0006\u0002cKB\u0019A\u0004A2\u0011\u0005A!G!B)_\u0005\u0004\u0011\u0006\"B/_\u0001\u0004\u0011\u0007\"B4\u0001\t\u000bA\u0017aC7ba\u000e{gn]3sm\u0016,\"!\u001b7\u0015\u0005)\f\bc\u0001\u000f\u0001WB\u0011\u0001\u0003\u001c\u0003\u0006#\u001a\u0014\r!\\\t\u0003\u001f9\u0004\"!F8\n\u0005A4!AB!osJ+g\rC\u0003sM\u0002\u00071/A\u0001g!\u0011)BoD6\n\u0005U4!!\u0003$v]\u000e$\u0018n\u001c82Q\t1w\u000f\u0005\u0002\u0016q&\u0011\u0011P\u0002\u0002\u0007S:d\u0017N\\3\t\u000bm\u0004A\u0011\t?\u0002\u0015\u0011\u0002H.^:%a2,8/F\u0003~\u0003#\t\t\u0001F\u0002\u007f\u0003'!2a`A\u0003!\r\u0001\u0012\u0011\u0001\u0003\u0007\u0003\u0007Q(\u0019A\n\u0003\tQC\u0017\r\u001e\u0005\b\u0003\u000fQ\b9AA\u0005\u0003\t\u0011g\rE\u0004$\u0003\u0017a\u0013qB@\n\u0007\u00055AE\u0001\u0007DC:\u0014U/\u001b7e\rJ|W\u000eE\u0002\u0011\u0003#!Q!\u0015>C\u0002ICq!!\u0006{\u0001\u0004\t9\"\u0001\u0003uQ\u0006$\b#\u0002\u0007\u0002\u001a\u0005=\u0011bAA\u000e\t\t\u0011r)\u001a8Ue\u00064XM]:bE2,wJ\\2f\u0011\u001d\ty\u0002\u0001C!\u0003C\t1\u0002\n9mkN$3m\u001c7p]V1\u00111EA\u0019\u0003S!B!!\n\u00024Q!\u0011qEA\u0016!\r\u0001\u0012\u0011\u0006\u0003\b\u0003\u0007\tiB1\u0001\u0014\u0011!\t9!!\bA\u0004\u00055\u0002\u0003C\u0012\u0002\f1\ny#a\n\u0011\u0007A\t\t\u0004\u0002\u0004R\u0003;\u0011\rA\u0015\u0005\t\u0003k\ti\u00021\u0001\u00020\u0005!Q\r\\3n\u0011\u0019\tI\u0004\u0001C!\u0013\u00061Ao\u001c'jgRDq!!\u0010\u0001\t\u0003\ny$\u0001\u0003uC.,Gc\u0001\u0017\u0002B!A\u00111IA\u001e\u0001\u0004\t)%A\u0001o!\r)\u0012qI\u0005\u0004\u0003\u00132!aA%oi\"9\u0011Q\n\u0001\u0005B\u0005=\u0013\u0001\u00023s_B$2\u0001LA)\u0011!\t\u0019%a\u0013A\u0002\u0005\u0015\u0003bBA+\u0001\u0011\u0005\u0013qK\u0001\u0006g2L7-\u001a\u000b\u0006Y\u0005e\u0013Q\f\u0005\t\u00037\n\u0019\u00061\u0001\u0002F\u0005!aM]8n\u0011!\ty&a\u0015A\u0002\u0005\u0015\u0013!B;oi&d\u0007bBA2\u0001\u0011\u0005\u0013QM\u0001\ni\u0006\\WMU5hQR$2\u0001LA4\u0011!\t\u0019%!\u0019A\u0002\u0005\u0015\u0003bBA6\u0001\u0011\u0005\u0013QN\u0001\bgBd\u0017\u000e^!u)\u0011\ty'!\u001e\u0011\u000bU\t\t\b\f\u0017\n\u0007\u0005MdA\u0001\u0004UkBdWM\r\u0005\t\u0003\u0007\nI\u00071\u0001\u0002F!9\u0011\u0011\u0010\u0001\u0005F\u0005m\u0014aA7baV1\u0011QPAF\u0003\u0007#B!a \u0002\u000eR!\u0011\u0011QAC!\r\u0001\u00121\u0011\u0003\b\u0003\u0007\t9H1\u0001\u0014\u0011!\t9!a\u001eA\u0004\u0005\u001d\u0005\u0003C\u0012\u0002\f1\nI)!!\u0011\u0007A\tY\t\u0002\u0004R\u0003o\u0012\ra\u0005\u0005\be\u0006]\u0004\u0019AAH!\u0015)BoDAEQ\u0011\t9(a%\u0011\u0007U\t)*C\u0002\u0002\u0018\u001a\u0011\u0001B\\8j]2Lg.\u001a\u0005\b\u00037\u0003AQIAO\u0003\u001d\u0019w\u000e\u001c7fGR,b!a(\u0002.\u0006\u0015F\u0003BAQ\u0003_#B!a)\u0002(B\u0019\u0001#!*\u0005\u000f\u0005\r\u0011\u0011\u0014b\u0001'!A\u0011qAAM\u0001\b\tI\u000b\u0005\u0005$\u0003\u0017a\u00131VAR!\r\u0001\u0012Q\u0016\u0003\u0007#\u0006e%\u0019A\n\t\u0011\u0005E\u0016\u0011\u0014a\u0001\u0003g\u000b!\u0001\u001d4\u0011\rU\t)lDAV\u0013\r\t9L\u0002\u0002\u0010!\u0006\u0014H/[1m\rVt7\r^5p]\"\"\u0011\u0011TAJ\u0011\u001d\ti\f\u0001C#\u0003\u007f\u000bqA\u001a7bi6\u000b\u0007/\u0006\u0004\u0002B\u0006=\u0017q\u0019\u000b\u0005\u0003\u0007\f\t\u000e\u0006\u0003\u0002F\u0006%\u0007c\u0001\t\u0002H\u00129\u00111AA^\u0005\u0004\u0019\u0002\u0002CA\u0004\u0003w\u0003\u001d!a3\u0011\u0011\r\nY\u0001LAg\u0003\u000b\u00042\u0001EAh\t\u0019\t\u00161\u0018b\u0001'!9!/a/A\u0002\u0005M\u0007#B\u000bu\u001f\u0005U\u0007#\u0002\u0007\u0002\u001a\u00055\u0007\u0006BA^\u0003'Cq!a7\u0001\t\u000b\ni.A\u0005uC.,w\u000b[5mKR\u0019A&a8\t\u0011\u0005\u0005\u0018\u0011\u001ca\u0001\u0003G\f\u0011\u0001\u001d\t\u0005+Q|\u0011\tK\u0002\u0002Z^Dq!!;\u0001\t\u000b\nY/A\u0005ee>\u0004x\u000b[5mKR\u0019A&!<\t\u0011\u0005\u0005\u0018q\u001da\u0001\u0003GD3!a:x\u0011\u001d\t\u0019\u0010\u0001C#\u0003k\fAa\u001d9b]R!\u0011qNA|\u0011!\t\t/!=A\u0002\u0005\r\bfAAyo\"9\u0011Q \u0001\u0005F\u0005}\u0018a\u00024pe\u0016\f7\r[\u000b\u0005\u0005\u0003\u0011y\u0001\u0006\u0003\u0003\u0004\t%\u0001cA\u000b\u0003\u0006%\u0019!q\u0001\u0004\u0003\tUs\u0017\u000e\u001e\u0005\be\u0006m\b\u0019\u0001B\u0006!\u0015)Bo\u0004B\u0007!\r\u0001\"q\u0002\u0003\b\u0005#\tYP1\u0001\u0014\u0005\u0005)\u0006fAA~o\"1!q\u0003\u0001\u0005B%\u000bqA]3wKJ\u001cX\rC\u0004\u0003\u001c\u0001!\tE!\b\u0002\u0013\u0019|G\u000e\u001a*jO\"$X\u0003\u0002B\u0010\u0005K!BA!\t\u00032Q!!1\u0005B\u0014!\r\u0001\"Q\u0005\u0003\u0007#\ne!\u0019A\n\t\u0011\t%\"\u0011\u0004a\u0001\u0005W\t!a\u001c9\u0011\u0011U\u0011ic\u0004B\u0012\u0005GI1Aa\f\u0007\u0005%1UO\\2uS>t'\u0007\u0003\u0005\u00034\te\u0001\u0019\u0001B\u0012\u0003\u0005Q\bb\u0002B\u001c\u0001\u0011\u0005#\u0011H\u0001\rgR\u0014\u0018N\\4Qe\u00164\u0017\u000e_\u000b\u0003\u0005w\u0001BA!\u0010\u0003D5\u0011!q\b\u0006\u0004\u0005\u0003\n\u0014\u0001\u00027b]\u001eLAA!\u0012\u0003@\t11\u000b\u001e:j]\u001eDqA!\u0013\u0001\t\u0003\u0012Y%\u0001\u0005u_N#(/Z1n+\t\u0011i\u0005\u0005\u0003\u001d\u0005\u001fz\u0011b\u0001B)\u0005\t11\u000b\u001e:fC6DqA!\u0016\u0001\t+\u00119&\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001oS\u0015\u0001!1\fB0\u0013\r\u0011iF\u0001\u0002\rI\r|Gn\u001c8%G>dwN\u001c\u0006\u0004\u0005C\u0012\u0011a\u0001(jY\":\u0001A!\u001a\u0003l\t5\u0004cA\u000b\u0003h%\u0019!\u0011\u000e\u0004\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g\u0004CV\u0011XTJ/{\u0001.\b\u000f\tE$\u0001#\u0001\u0003t\u0005!A*[:u!\ra\"Q\u000f\u0004\u0007\u0003\tA\tAa\u001e\u0014\r\tU$\u0011\u0010B@!\u0011\u0019#1\u0010\u0015\n\u0007\tuDE\u0001\u0006TKF4\u0015m\u0019;pef\u00042!\u0006BA\u0013\t!d\u0001C\u00047\u0005k\"\tA!\"\u0015\u0005\tM\u0004\u0002\u0003BE\u0005k\"\u0019Aa#\u0002\u0019\r\fgNQ;jY\u00124%o\\7\u0016\t\t5%1T\u000b\u0003\u0005\u001f\u0003\u0012bIA\u0006\u0005#\u0013IJ!(\u0011\t\tM%QS\u0007\u0003\u0005kJ1Aa&=\u0005\u0011\u0019u\u000e\u001c7\u0011\u0007A\u0011Y\n\u0002\u0004\u0013\u0005\u000f\u0013\ra\u0005\t\u00059\u0001\u0011I\n\u0003\u0005\u0003\"\nUD\u0011\u0001BR\u0003)qWm\u001e\"vS2$WM]\u000b\u0005\u0005K\u0013),\u0006\u0002\u0003(BA!\u0011\u0016BX\u0005g\u00139,\u0004\u0002\u0003,*\u0019!Q\u0016\u0003\u0002\u000f5,H/\u00192mK&!!\u0011\u0017BV\u0005\u001d\u0011U/\u001b7eKJ\u00042\u0001\u0005B[\t\u0019\u0011\"q\u0014b\u0001'A!A\u0004\u0001BZ\u0011!\u0011YL!\u001e\u0005B\tu\u0016!B3naRLX\u0003\u0002B`\u0005\u000b,\"A!1\u0011\tq\u0001!1\u0019\t\u0004!\t\u0015GA\u0002\n\u0003:\n\u00071\u0003\u0003\u0005\u0003J\nUD\u0011\tBf\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\u0011iMa5\u0015\t\t='Q\u001b\t\u00059\u0001\u0011\t\u000eE\u0002\u0011\u0005'$aA\u0005Bd\u0005\u0004\u0019\u0002\u0002\u0003Bl\u0005\u000f\u0004\rA!7\u0002\u0005a\u001c\b#B\u000b\u0003\\\nE\u0017b\u0001Bo\r\tQAH]3qK\u0006$X\r\u001a \t\u0017\t\u0005(Q\u000fb\u0001\n\u0003!!1]\u0001\u0012a\u0006\u0014H/[1m\u001d>$\u0018\t\u001d9mS\u0016$WC\u0001Bs%\u0015\u00119O\u001cBx\r\u001d\u0011IOa;\u0001\u0005K\u0014A\u0002\u0010:fM&tW-\\3oizB\u0011B!<\u0003v\u0001\u0006IA!:\u0002%A\f'\u000f^5bY:{G/\u00119qY&,G\r\t\t\u0005+QD\u0002DB\u0004\u0003t\nUDA!>\u0003%M+'/[1mSj\fG/[8o!J|\u00070_\u000b\u0005\u0005o\u001c\u0019a\u0005\u0003\u0003r:l\u0003b\u0003B~\u0005c\u0014\t\u0019!C\u0005\u0005{\fAa\u001c:jOV\u0011!q \t\u00059\u0001\u0019\t\u0001E\u0002\u0011\u0007\u0007!aA\u0005By\u0005\u0004\u0019\u0002bCB\u0004\u0005c\u0014\t\u0019!C\u0005\u0007\u0013\t\u0001b\u001c:jO~#S-\u001d\u000b\u0005\u0005\u0007\u0019Y\u0001\u0003\u0006\u0004\u000e\r\u0015\u0011\u0011!a\u0001\u0005\u007f\f1\u0001\u001f\u00132\u0011-\u0019\tB!=\u0003\u0002\u0003\u0006KAa@\u0002\u000b=\u0014\u0018n\u001a\u0011)\t\r=1Q\u0003\t\u0004+\r]\u0011bAB\r\r\tIAO]1og&,g\u000e\u001e\u0005\bm\tEH\u0011AB\u000f)\u0011\u0019yb!\t\u0011\r\tM%\u0011_B\u0001\u0011!\u0011Ypa\u0007A\u0002\t}\b\u0002CB\u0013\u0005c$Iaa\n\u0002\u0017]\u0014\u0018\u000e^3PE*,7\r\u001e\u000b\u0005\u0005\u0007\u0019I\u0003\u0003\u0005\u0004,\r\r\u0002\u0019AB\u0017\u0003\ryW\u000f\u001e\t\u0004]\r=\u0012bAB\u0019_\t\u0011rJ\u00196fGR|U\u000f\u001e9viN#(/Z1n\u0011!\u0019)D!=\u0005\n\r]\u0012A\u0003:fC\u0012|%M[3diR!!1AB\u001d\u0011!\u0019Yda\rA\u0002\ru\u0012AA5o!\rq3qH\u0005\u0004\u0007\u0003z#!E(cU\u0016\u001cG/\u00138qkR\u001cFO]3b[\"A1Q\tBy\t\u0013\u00119&A\u0006sK\u0006$'+Z:pYZ,\u0007\u0006\u0003By\u0005K\u0012Yg!\u0013\u001f\u0003\u0005A!b!\u0012\u0003v\u0005\u0005I\u0011BB')\t\u0019y\u0005\u0005\u0003\u0003>\rE\u0013\u0002BB*\u0005\u007f\u0011aa\u00142kK\u000e$\b")
public abstract class List<A>
extends AbstractSeq<A>
implements LinearSeq<A>,
Product,
LinearSeqOptimized<A, List<A>>,
java.io.Serializable {
    public static final long serialVersionUID = -6084104484083858598L;

    public static <A> List<A> empty() {
        return List$.MODULE$.empty();
    }

    public static <A> CanBuildFrom<List<?>, A, List<A>> canBuildFrom() {
        return List$.MODULE$.canBuildFrom();
    }

    public static Some unapplySeq(Seq seq) {
        return List$.MODULE$.unapplySeq(seq);
    }

    public static GenTraversable iterate(Object object, int n, Function1 function1) {
        return List$.MODULE$.iterate(object, n, function1);
    }

    public static GenTraversable range(Object object, Object object2, Object object3, Integral integral) {
        return List$.MODULE$.range(object, object2, object3, integral);
    }

    public static GenTraversable range(Object object, Object object2, Integral integral) {
        return List$.MODULE$.range(object, object2, integral);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, int n5, Function5 function5) {
        return List$.MODULE$.tabulate(n, n2, n3, n4, n5, function5);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, Function4 function4) {
        return List$.MODULE$.tabulate(n, n2, n3, n4, function4);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, Function3 function3) {
        return List$.MODULE$.tabulate(n, n2, n3, function3);
    }

    public static GenTraversable tabulate(int n, int n2, Function2 function2) {
        return List$.MODULE$.tabulate(n, n2, function2);
    }

    public static GenTraversable tabulate(int n, Function1 function1) {
        return List$.MODULE$.tabulate(n, function1);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, int n5, Function0 function0) {
        return List$.MODULE$.fill(n, n2, n3, n4, n5, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, Function0 function0) {
        return List$.MODULE$.fill(n, n2, n3, n4, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, Function0 function0) {
        return List$.MODULE$.fill(n, n2, n3, function0);
    }

    public static GenTraversable fill(int n, int n2, Function0 function0) {
        return List$.MODULE$.fill(n, n2, function0);
    }

    public static GenTraversable fill(int n, Function0 function0) {
        return List$.MODULE$.fill(n, function0);
    }

    public static GenTraversable concat(Seq seq) {
        return List$.MODULE$.concat(seq);
    }

    public static GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return List$.MODULE$.ReusableCBF();
    }

    public static GenTraversable empty() {
        return List$.MODULE$.empty();
    }

    @Override
    public /* synthetic */ boolean scala$collection$LinearSeqOptimized$$super$sameElements(GenIterable that) {
        return IterableLike$class.sameElements(this, that);
    }

    @Override
    public int length() {
        return LinearSeqOptimized$class.length(this);
    }

    @Override
    public A apply(int n) {
        return (A)LinearSeqOptimized$class.apply(this, n);
    }

    @Override
    public boolean forall(Function1<A, Object> p) {
        return LinearSeqOptimized$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<A, Object> p) {
        return LinearSeqOptimized$class.exists(this, p);
    }

    @Override
    public <A1> boolean contains(A1 elem) {
        return LinearSeqOptimized$class.contains(this, elem);
    }

    @Override
    public Option<A> find(Function1<A, Object> p) {
        return LinearSeqOptimized$class.find(this, p);
    }

    @Override
    public <B> B foldLeft(B z, Function2<B, A, B> op) {
        return (B)LinearSeqOptimized$class.foldLeft(this, z, op);
    }

    @Override
    public <B> B reduceLeft(Function2<B, A, B> f) {
        return (B)LinearSeqOptimized$class.reduceLeft(this, f);
    }

    @Override
    public <B> B reduceRight(Function2<A, B, B> op) {
        return (B)LinearSeqOptimized$class.reduceRight(this, op);
    }

    @Override
    public A last() {
        return (A)LinearSeqOptimized$class.last(this);
    }

    @Override
    public LinearSeqOptimized dropRight(int n) {
        return LinearSeqOptimized$class.dropRight(this, n);
    }

    @Override
    public <B> boolean sameElements(GenIterable<B> that) {
        return LinearSeqOptimized$class.sameElements(this, that);
    }

    @Override
    public int lengthCompare(int len) {
        return LinearSeqOptimized$class.lengthCompare(this, len);
    }

    @Override
    public boolean isDefinedAt(int x) {
        return LinearSeqOptimized$class.isDefinedAt(this, x);
    }

    @Override
    public int segmentLength(Function1<A, Object> p, int from2) {
        return LinearSeqOptimized$class.segmentLength(this, p, from2);
    }

    @Override
    public int indexWhere(Function1<A, Object> p, int from2) {
        return LinearSeqOptimized$class.indexWhere(this, p, from2);
    }

    @Override
    public int lastIndexWhere(Function1<A, Object> p, int end) {
        return LinearSeqOptimized$class.lastIndexWhere(this, p, end);
    }

    @Override
    public Iterator<Object> productIterator() {
        return Product$class.productIterator(this);
    }

    @Override
    public String productPrefix() {
        return Product$class.productPrefix(this);
    }

    @Override
    public LinearSeq<A> seq() {
        return LinearSeq$class.seq(this);
    }

    @Override
    public scala.collection.LinearSeq<A> thisCollection() {
        return LinearSeqLike$class.thisCollection(this);
    }

    @Override
    public scala.collection.LinearSeq toCollection(LinearSeqLike repr) {
        return LinearSeqLike$class.toCollection(this, repr);
    }

    @Override
    public int hashCode() {
        return LinearSeqLike$class.hashCode(this);
    }

    @Override
    public Iterator<A> iterator() {
        return LinearSeqLike$class.iterator(this);
    }

    @Override
    public final <B> boolean corresponds(GenSeq<B> that, Function2<A, B, Object> p) {
        return LinearSeqLike$class.corresponds(this, that, p);
    }

    @Override
    public scala.collection.immutable.Seq<A> toSeq() {
        return Seq$class.toSeq(this);
    }

    @Override
    public Combiner<A, ParSeq<A>> parCombiner() {
        return Seq$class.parCombiner(this);
    }

    @Override
    public GenericCompanion<List> companion() {
        return List$.MODULE$;
    }

    @Override
    public abstract boolean isEmpty();

    @Override
    public abstract A head();

    public <B> List<B> $colon$colon(B x) {
        return new $colon$colon<B>(x, this);
    }

    public <B> List<B> $colon$colon$colon(List<B> prefix) {
        return this.isEmpty() ? prefix : (prefix.isEmpty() ? this : ((ListBuffer)new ListBuffer().$plus$plus$eq((TraversableOnce)prefix)).prependToList(this));
    }

    public <B> List<B> reverse_$colon$colon$colon(List<B> prefix) {
        List these = this;
        List<B> pres = prefix;
        List<A> list2;
        while (!pres.isEmpty()) {
            List list3;
            A a = list3.head();
            list2 = list2.$colon$colon(a);
            list3 = (List)list3.tail();
        }
        return list2;
    }

    public final <B> List<B> mapConserve(Function1<A, B> f) {
        return this.loop$1(null, null, this, this, f);
    }

    @Override
    public <B, That> That $plus$plus(GenTraversableOnce<B> that, CanBuildFrom<List<A>, B, That> bf) {
        return (That)(bf == List$.MODULE$.ReusableCBF() ? that.seq().toList().$colon$colon$colon(this) : TraversableLike$class.$plus$plus(this, that, bf));
    }

    @Override
    public <B, That> That $plus$colon(B elem, CanBuildFrom<List<A>, B, That> bf) {
        Object object = bf instanceof GenTraversableFactory.GenericCanBuildFrom ? this.$colon$colon(elem) : SeqLike$class.$plus$colon(this, elem, bf);
        return (That)object;
    }

    @Override
    public List<A> toList() {
        return this;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public List<A> take(int n) {
        Nil$ nil$;
        if (this.isEmpty() || n <= 0) {
            nil$ = Nil$.MODULE$;
        } else {
            void var2_2;
            $colon$colon<Nothing$> h;
            $colon$colon<Nothing$> t = h = new $colon$colon<Nothing$>((Nothing$)this.head(), Nil$.MODULE$);
            List rest = (List)this.tail();
            int i = 1;
            while (true) {
                if (rest.isEmpty()) {
                    return this;
                }
                if (!(i < n)) break;
                ++i;
                $colon$colon<Nothing$> nx = new $colon$colon<Nothing$>((Nothing$)rest.head(), Nil$.MODULE$);
                t.tl_$eq(nx);
                t = nx;
                rest = (List)rest.tail();
            }
            nil$ = var2_2;
        }
        return nil$;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public List<A> drop(int n) {
        void var2_2;
        List these = this;
        for (int count2 = n; !these.isEmpty() && count2 > 0; --count2) {
            these = (List)these.tail();
        }
        return var2_2;
    }

    @Override
    public List<A> slice(int from2, int until2) {
        int lo = package$.MODULE$.max(from2, 0);
        return until2 <= lo || this.isEmpty() ? Nil$.MODULE$ : ((List)this.drop(lo)).take(until2 - lo);
    }

    @Override
    public List<A> takeRight(int n) {
        return this.loop$2((List)this.drop(n), this);
    }

    @Override
    public Tuple2<List<A>, List<A>> splitAt(int n) {
        ListBuffer b = new ListBuffer();
        int i = 0;
        List these = this;
        while (!these.isEmpty() && i < n) {
            ++i;
            b.$plus$eq((Object)these.head());
            these = (List)these.tail();
        }
        return new Tuple2<List<A>, List<A>>(b.toList(), these);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public final <B, That> That map(Function1<A, B> f, CanBuildFrom<List<A>, B, That> bf) {
        Object object;
        if (bf == List$.MODULE$.ReusableCBF()) {
            if (this == Nil$.MODULE$) {
                object = Nil$.MODULE$;
            } else {
                void var3_3;
                $colon$colon<Nothing$> h;
                $colon$colon<Nothing$> t = h = new $colon$colon<Nothing$>((Nothing$)f.apply(this.head()), Nil$.MODULE$);
                for (List rest = (List)this.tail(); rest != Nil$.MODULE$; rest = (List)rest.tail()) {
                    $colon$colon<Nothing$> nx = new $colon$colon<Nothing$>((Nothing$)f.apply(rest.head()), Nil$.MODULE$);
                    t.tl_$eq(nx);
                    t = nx;
                }
                object = var3_3;
            }
        } else {
            object = TraversableLike$class.map(this, f, bf);
        }
        return (That)object;
    }

    @Override
    public final <B, That> That collect(PartialFunction<A, B> pf, CanBuildFrom<List<A>, B, That> bf) {
        $colon$colon<Nothing$> $colon$colon;
        if (bf == List$.MODULE$.ReusableCBF()) {
            if (this == Nil$.MODULE$) {
                $colon$colon = Nil$.MODULE$;
            } else {
                List rest = this;
                $colon$colon<Nothing$> h = null;
                do {
                    Object x;
                    if ((x = pf.applyOrElse(rest.head(), List$.MODULE$.partialNotApplied())) != List$.MODULE$.partialNotApplied()) {
                        h = new $colon$colon<Nothing$>((Nothing$)x, Nil$.MODULE$);
                    }
                    if ((rest = (List)rest.tail()) != Nil$.MODULE$) continue;
                    return (That)(h == null ? Nil$.MODULE$ : h);
                } while (h == null);
                $colon$colon<Nothing$> t = h;
                do {
                    Object x;
                    if ((x = pf.applyOrElse(rest.head(), List$.MODULE$.partialNotApplied())) == List$.MODULE$.partialNotApplied()) continue;
                    $colon$colon<Nothing$> nx = new $colon$colon<Nothing$>((Nothing$)x, Nil$.MODULE$);
                    t.tl_$eq(nx);
                    t = nx;
                } while ((rest = (List)rest.tail()) != Nil$.MODULE$);
                $colon$colon = h;
            }
        } else {
            $colon$colon = TraversableLike$class.collect(this, pf, bf);
        }
        return (That)$colon$colon;
    }

    @Override
    public final <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> f, CanBuildFrom<List<A>, B, That> bf) {
        Object object;
        if (bf == List$.MODULE$.ReusableCBF()) {
            if (this == Nil$.MODULE$) {
                object = Nil$.MODULE$;
            } else {
                BooleanRef found = BooleanRef.create(false);
                ObjectRef<Object> h = ObjectRef.create(null);
                ObjectRef<Object> t = ObjectRef.create(null);
                for (List rest = this; rest != Nil$.MODULE$; rest = (List)rest.tail()) {
                    f.apply(rest.head()).seq().foreach(new Serializable(this, found, h, t){
                        public static final long serialVersionUID = 0L;
                        private final BooleanRef found$1;
                        private final ObjectRef h$1;
                        private final ObjectRef t$1;

                        public final void apply(B b) {
                            if (this.found$1.elem) {
                                $colon$colon<Nothing$> nx = new $colon$colon<Nothing$>((Nothing$)b, Nil$.MODULE$);
                                (($colon$colon)this.t$1.elem).tl_$eq(nx);
                                this.t$1.elem = nx;
                            } else {
                                this.h$1.elem = new $colon$colon<Nothing$>((Nothing$)b, Nil$.MODULE$);
                                this.t$1.elem = ($colon$colon)this.h$1.elem;
                                this.found$1.elem = true;
                            }
                        }
                        {
                            void var4_4;
                            void var3_3;
                            this.found$1 = found$1;
                            this.h$1 = var3_3;
                            this.t$1 = var4_4;
                        }
                    });
                }
                object = found.elem ? ($colon$colon)h.elem : Nil$.MODULE$;
            }
        } else {
            object = TraversableLike$class.flatMap(this, f, bf);
        }
        return (That)object;
    }

    @Override
    public final List<A> takeWhile(Function1<A, Object> p) {
        ListBuffer b = new ListBuffer();
        List these = this;
        while (!these.isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
            b.$plus$eq((Object)these.head());
            these = (List)these.tail();
        }
        return b.toList();
    }

    @Override
    public final List<A> dropWhile(Function1<A, Object> p) {
        return this.loop$3(this, p);
    }

    @Override
    public final Tuple2<List<A>, List<A>> span(Function1<A, Object> p) {
        ListBuffer b = new ListBuffer();
        List these = this;
        while (!these.isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
            b.$plus$eq((Object)these.head());
            these = (List)these.tail();
        }
        return new Tuple2<List<A>, List<A>>(b.toList(), these);
    }

    @Override
    public final <U> void foreach(Function1<A, U> f) {
        List these = this;
        while (!these.isEmpty()) {
            f.apply(these.head());
            these = (List)these.tail();
        }
        return;
    }

    @Override
    public List<A> reverse() {
        Nil$ result2 = Nil$.MODULE$;
        List these = this;
        List<A> list2;
        while (!these.isEmpty()) {
            List list3;
            A a = list3.head();
            list2 = list2.$colon$colon(a);
            list3 = (List)list3.tail();
        }
        return list2;
    }

    @Override
    public <B> B foldRight(B z, Function2<A, B, B> op) {
        return ((List)this.reverse()).foldLeft(z, (Function2<B, A, B>)((Object)new Serializable(this, op){
            public static final long serialVersionUID = 0L;
            private final Function2 op$1;

            public final B apply(B right, A left) {
                return (B)this.op$1.apply(left, right);
            }
            {
                this.op$1 = op$1;
            }
        }));
    }

    @Override
    public String stringPrefix() {
        return "List";
    }

    @Override
    public Stream<A> toStream() {
        return this.isEmpty() ? Stream$Empty$.MODULE$ : new Stream.Cons<A>(this.head(), new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ List $outer;

            public final Stream<A> apply() {
                return ((List)this.$outer.tail()).toStream();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public final Object writeReplace() {
        return new SerializationProxy(this);
    }

    private final List loop$1(List mappedHead, $colon$colon mappedLast, List unchanged, List pending, Function1 f$1) {
        while (true) {
            List tail0;
            if (pending.isEmpty()) {
                List list2;
                if (mappedHead == null) {
                    list2 = unchanged;
                } else {
                    mappedLast.tl_$eq(unchanged);
                    list2 = mappedHead;
                }
                return list2;
            }
            A head0 = pending.head();
            Object head1 = f$1.apply(head0);
            if (head1 == head0) {
                pending = (List)pending.tail();
                continue;
            }
            $colon$colon<Nothing$> mappedHead1 = mappedHead;
            $colon$colon<Nothing$> mappedLast1 = mappedLast;
            for (List xc = unchanged; xc != pending; xc = (List)xc.tail()) {
                $colon$colon<Nothing$> next2 = new $colon$colon<Nothing$>((Nothing$)xc.head(), Nil$.MODULE$);
                if (mappedHead1 == null) {
                    mappedHead1 = next2;
                }
                if (mappedLast1 != null) {
                    mappedLast1.tl_$eq(next2);
                }
                mappedLast1 = next2;
            }
            $colon$colon<Nothing$> next3 = new $colon$colon<Nothing$>((Nothing$)head1, Nil$.MODULE$);
            if (mappedHead1 == null) {
                mappedHead1 = next3;
            }
            if (mappedLast1 != null) {
                mappedLast1.tl_$eq(next3);
            }
            mappedLast1 = null;
            pending = tail0 = (List)pending.tail();
            unchanged = tail0;
            mappedLast = next3;
            mappedHead = mappedHead1;
        }
    }

    private final List loop$default$1$1() {
        return Nil$.MODULE$;
    }

    private final List loop$2(List lead, List lag) {
        while (true) {
            if (((Object)Nil$.MODULE$).equals(lead)) {
                return lag;
            }
            if (!(lead instanceof $colon$colon)) break;
            $colon$colon $colon$colon = ($colon$colon)lead;
            lag = (List)lag.tail();
            lead = $colon$colon.tl$1();
        }
        throw new MatchError(lead);
    }

    private final List loop$3(List xs, Function1 p$1) {
        while (!xs.isEmpty() && BoxesRunTime.unboxToBoolean(p$1.apply(xs.head()))) {
            xs = (List)xs.tail();
        }
        return xs;
    }

    public List() {
        Traversable$class.$init$(this);
        Iterable$class.$init$(this);
        Seq$class.$init$(this);
        LinearSeqLike$class.$init$(this);
        scala.collection.LinearSeq$class.$init$(this);
        LinearSeq$class.$init$(this);
        Product$class.$init$(this);
        LinearSeqOptimized$class.$init$(this);
    }

    public static class SerializationProxy<A>
    implements java.io.Serializable {
        public static final long serialVersionUID = 1L;
        private transient List<A> orig;

        private List<A> orig() {
            return this.orig;
        }

        private void orig_$eq(List<A> x$1) {
            this.orig = x$1;
        }

        private void writeObject(ObjectOutputStream out) {
            out.defaultWriteObject();
            List<A> xs = this.orig();
            while (true) {
                List list2;
                if (xs.isEmpty()) {
                    out.writeObject(ListSerializeEnd$.MODULE$);
                    return;
                }
                out.writeObject(list2.head());
                list2 = (List)list2.tail();
            }
        }

        private void readObject(ObjectInputStream in) {
            in.defaultReadObject();
            Builder builder = List$.MODULE$.newBuilder();
            while (true) {
                Object object;
                if (ListSerializeEnd$.MODULE$.equals(object = in.readObject())) {
                    this.orig_$eq(builder.result());
                    return;
                }
                builder.$plus$eq(object);
            }
        }

        private Object readResolve() {
            return this.orig();
        }

        public SerializationProxy(List<A> orig) {
            this.orig = orig;
        }
    }
}

