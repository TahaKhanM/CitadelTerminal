/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import java.util.Arrays;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Some;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Iterator$class;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.AbstractMap;
import scala.collection.mutable.AnyRefMap$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@ScalaSignature(bytes="\u0006\u0001\rEd\u0001B\u0001\u0003\u0005%\u0011\u0011\"\u00118z%\u00164W*\u00199\u000b\u0005\r!\u0011aB7vi\u0006\u0014G.\u001a\u0006\u0003\u000b\u0019\t!bY8mY\u0016\u001cG/[8o\u0015\u00059\u0011!B:dC2\f7\u0001A\u000b\u0004\u0015Ea2\u0003\u0002\u0001\fE\u0015\u0002B\u0001D\u0007\u001075\t!!\u0003\u0002\u000f\u0005\tY\u0011IY:ue\u0006\u001cG/T1q!\t\u0001\u0012\u0003\u0004\u0001\u0005\u000bI\u0001!\u0019A\n\u0003\u0003-\u000b\"\u0001\u0006\r\u0011\u0005U1R\"\u0001\u0004\n\u0005]1!a\u0002(pi\"Lgn\u001a\t\u0003+eI!A\u0007\u0004\u0003\r\u0005s\u0017PU3g!\t\u0001B\u0004B\u0003\u001e\u0001\t\u0007aDA\u0001W#\t!r\u0004\u0005\u0002\u0016A%\u0011\u0011E\u0002\u0002\u0004\u0003:L\b\u0003\u0002\u0007$\u001fmI!\u0001\n\u0002\u0003\u00075\u000b\u0007\u000fE\u0003\rM=Y\u0002&\u0003\u0002(\u0005\t9Q*\u00199MS.,\u0007\u0003\u0002\u0007\u0001\u001fmA\u0001B\u000b\u0001\u0003\u0002\u0003\u0006IaK\u0001\rI\u00164\u0017-\u001e7u\u000b:$(/\u001f\t\u0005+1z1$\u0003\u0002.\r\tIa)\u001e8di&|g.\r\u0005\t_\u0001\u0011\t\u0011)A\u0005a\u0005\t\u0012N\\5uS\u0006d')\u001e4gKJ\u001c\u0016N_3\u0011\u0005U\t\u0014B\u0001\u001a\u0007\u0005\rIe\u000e\u001e\u0005\ti\u0001\u0011\t\u0011)A\u0005k\u0005I\u0011N\\5u\u00052\fgn\u001b\t\u0003+YJ!a\u000e\u0004\u0003\u000f\t{w\u000e\\3b]\"1\u0011\b\u0001C\u0001\ti\na\u0001P5oSRtD\u0003\u0002\u0015<yuBQA\u000b\u001dA\u0002-BQa\f\u001dA\u0002ABQ\u0001\u000e\u001dA\u0002UBQ!\u000f\u0001\u0005\u0002}\"\u0012\u0001\u000b\u0005\u0006s\u0001!\t!\u0011\u000b\u0003Q\tCQA\u000b!A\u0002-BQ!\u000f\u0001\u0005\u0002\u0011#\"\u0001K#\t\u000b=\u001a\u0005\u0019\u0001\u0019\t\u000be\u0002A\u0011A$\u0015\u0007!B\u0015\nC\u0003+\r\u0002\u00071\u0006C\u00030\r\u0002\u0007\u0001\u0007\u0003\u0004L\u0001\u0001\u0006K\u0001M\u0001\u0005[\u0006\u001c8\u000e\u0003\u0004N\u0001\u0001\u0006K\u0001M\u0001\u0006?NL'0\u001a\u0005\u0007\u001f\u0002\u0001\u000b\u0015\u0002\u0019\u0002\u000f}3\u0018mY1oi\"1\u0011\u000b\u0001Q!\nI\u000bqa\u00185bg\",7\u000fE\u0002\u0016'BJ!\u0001\u0016\u0004\u0003\u000b\u0005\u0013(/Y=\t\rY\u0003\u0001\u0015)\u0003X\u0003\u0015y6.Z=t!\r)2\u000b\u0007\u0005\u00073\u0002\u0001\u000b\u0015B,\u0002\u000f}3\u0018\r\\;fg\"11\f\u0001Q\u0005\nq\u000b\u0011\u0003Z3gCVdG/\u00138ji&\fG.\u001b>f)\ti\u0006\r\u0005\u0002\u0016=&\u0011qL\u0002\u0002\u0005+:LG\u000fC\u0003b5\u0002\u0007\u0001'A\u0001o\u0011\u0019\u0019\u0007\u0001\"\u0001\u0005I\u0006a\u0011N\\5uS\u0006d\u0017N_3U_R9Q,Z4jW6|\u0007\"\u00024c\u0001\u0004\u0001\u0014!A7\t\u000b!\u0014\u0007\u0019\u0001\u0019\u0002\u0005MT\b\"\u00026c\u0001\u0004\u0001\u0014A\u0001<d\u0011\u0015a'\r1\u0001S\u0003\tA'\u0010C\u0003oE\u0002\u0007q+\u0001\u0002lu\")\u0001O\u0019a\u0001/\u0006\u0011aO\u001f\u0005\u0006e\u0002!\te]\u0001\u0005g&TX-F\u00011\u0011\u0015)\b\u0001\"\u0011w\u0003\u0015)W\u000e\u001d;z+\u0005A\u0003\"\u0002=\u0001\t\u0013I\u0018AC5nE\u0006d\u0017M\\2fIV\tQ\u0007C\u0003|\u0001\u0011%A0\u0001\u0004iCNDwJ\u001a\u000b\u0003auDQA >A\u0002=\t1a[3z\u0011\u001d\t\t\u0001\u0001C\u0005\u0003\u0007\t\u0011b]3fW\u0016sGO]=\u0015\u000bA\n)!!\u0003\t\r\u0005\u001dq\u00101\u00011\u0003\u0005A\u0007BBA\u0006\u007f\u0002\u0007\u0001$A\u0001l\u0011\u001d\ty\u0001\u0001C\u0005\u0003#\tqb]3fW\u0016sGO]=Pe>\u0003XM\u001c\u000b\u0006a\u0005M\u0011Q\u0003\u0005\b\u0003\u000f\ti\u00011\u00011\u0011\u001d\tY!!\u0004A\u0002aAq!!\u0007\u0001\t\u0003\nY\"\u0001\u0005d_:$\u0018-\u001b8t)\r)\u0014Q\u0004\u0005\u0007}\u0006]\u0001\u0019A\b\t\u000f\u0005\u0005\u0002\u0001\"\u0011\u0002$\u0005\u0019q-\u001a;\u0015\t\u0005\u0015\u00121\u0006\t\u0005+\u0005\u001d2$C\u0002\u0002*\u0019\u0011aa\u00149uS>t\u0007B\u0002@\u0002 \u0001\u0007q\u0002C\u0004\u00020\u0001!\t%!\r\u0002\u0013\u001d,Go\u0014:FYN,W\u0003BA\u001a\u0003o!b!!\u000e\u0002>\u0005}\u0002c\u0001\t\u00028\u0011A\u0011\u0011HA\u0017\u0005\u0004\tYD\u0001\u0002WcE\u00111d\b\u0005\u0007}\u00065\u0002\u0019A\b\t\u0013\u0005\u0005\u0013Q\u0006CA\u0002\u0005\r\u0013a\u00023fM\u0006,H\u000e\u001e\t\u0006+\u0005\u0015\u0013QG\u0005\u0004\u0003\u000f2!\u0001\u0003\u001fcs:\fW.\u001a \t\u000f\u0005-\u0003\u0001\"\u0011\u0002N\u0005yq-\u001a;Pe\u0016c7/Z+qI\u0006$X\rF\u0003\u001c\u0003\u001f\n\t\u0006\u0003\u0004\u007f\u0003\u0013\u0002\ra\u0004\u0005\n\u0003'\nI\u0005\"a\u0001\u0003+\nA\u0002Z3gCVdGOV1mk\u0016\u0004B!FA#7!9\u0011\u0011\f\u0001\u0005\u0002\u0005m\u0013!C4fi>\u0013h*\u001e7m)\rY\u0012Q\f\u0005\u0007}\u0006]\u0003\u0019A\b\t\u000f\u0005\u0005\u0004\u0001\"\u0011\u0002d\u0005)\u0011\r\u001d9msR\u00191$!\u001a\t\ry\fy\u00061\u0001\u0010\u0011\u001d\t\t\u0005\u0001C!\u0003S\"2aGA6\u0011\u0019q\u0018q\ra\u0001\u001f!9\u0011q\u000e\u0001\u0005\n\u0005E\u0014A\u0002:fa\u0006\u001c7\u000eF\u0002^\u0003gBq!!\u001e\u0002n\u0001\u0007\u0001'A\u0004oK^l\u0015m]6\t\u000f\u0005=\u0004\u0001\"\u0001\u0002zQ\tQ\fC\u0004\u0002~\u0001!\t%a \u0002\u0007A,H\u000f\u0006\u0004\u0002&\u0005\u0005\u00151\u0011\u0005\u0007}\u0006m\u0004\u0019A\b\t\u000f\u0005\u0015\u00151\u0010a\u00017\u0005)a/\u00197vK\"9\u0011\u0011\u0012\u0001\u0005B\u0005-\u0015AB;qI\u0006$X\rF\u0003^\u0003\u001b\u000by\t\u0003\u0004\u007f\u0003\u000f\u0003\ra\u0004\u0005\b\u0003\u000b\u000b9\t1\u0001\u001c\u0011\u001d\t\u0019\n\u0001C\u0001\u0003+\u000b\u0001\u0002\n9mkN$S-\u001d\u000b\u0007\u0003/\u000bI*a'\u000e\u0003\u0001AaA`AI\u0001\u0004y\u0001bBAC\u0003#\u0003\ra\u0007\u0005\b\u0003'\u0003A\u0011AAP)\u0011\t9*!)\t\u0011\u0005\r\u0016Q\u0014a\u0001\u0003K\u000b!a\u001b<\u0011\u000bU\t9kD\u000e\n\u0007\u0005%fA\u0001\u0004UkBdWM\r\u0005\b\u0003[\u0003A\u0011AAX\u0003%!S.\u001b8vg\u0012*\u0017\u000f\u0006\u0003\u0002\u0018\u0006E\u0006B\u0002@\u0002,\u0002\u0007q\u0002C\u0004\u00026\u0002!\t!a.\u0002\u0011%$XM]1u_J,\"!!/\u0011\r\u0005m\u0016QXAS\u001b\u0005!\u0011bAA`\t\tA\u0011\n^3sCR|'\u000fC\u0004\u0002D\u0002!\t%!2\u0002\u000f\u0019|'/Z1dQV!\u0011qYAi)\ri\u0016\u0011\u001a\u0005\t\u0003\u0017\f\t\r1\u0001\u0002N\u0006\ta\r\u0005\u0004\u0016Y\u0005\u0015\u0016q\u001a\t\u0004!\u0005EGaBAj\u0003\u0003\u0014\rA\b\u0002\u0002+\"1\u0011q\u001b\u0001\u0005B}\nQa\u00197p]\u0016D\u0001\"a7\u0001A\u0013%\u0011Q\\\u0001\u000fM>\u0014X-Y2i\u000b2,W.\u001a8u+\u0019\ty.a;\u0002rR)Q,!9\u0002f\"9\u00111]Am\u0001\u00049\u0016!B3mK6\u001c\b\u0002CAf\u00033\u0004\r!a:\u0011\rUa\u0013\u0011^Ax!\r\u0001\u00121\u001e\u0003\b\u0003[\fIN1\u0001\u001f\u0005\u0005\t\u0005c\u0001\t\u0002r\u00129\u00111_Am\u0005\u0004q\"!\u0001\"\t\u000f\u0005]\b\u0001\"\u0001\u0002z\u0006Qam\u001c:fC\u000eD7*Z=\u0016\t\u0005m(1\u0001\u000b\u0004;\u0006u\b\u0002CAf\u0003k\u0004\r!a@\u0011\u000bUasB!\u0001\u0011\u0007A\u0011\u0019\u0001B\u0004\u0002n\u0006U(\u0019\u0001\u0010\t\u000f\t\u001d\u0001\u0001\"\u0001\u0003\n\u0005aam\u001c:fC\u000eDg+\u00197vKV!!1\u0002B\n)\ri&Q\u0002\u0005\t\u0003\u0017\u0014)\u00011\u0001\u0003\u0010A)Q\u0003L\u000e\u0003\u0012A\u0019\u0001Ca\u0005\u0005\u000f\u00055(Q\u0001b\u0001=!9!q\u0003\u0001\u0005\u0002\te\u0011\u0001D7baZ\u000bG.^3t\u001d><X\u0003\u0002B\u000e\u0005C!BA!\b\u0003$A)A\u0002A\b\u0003 A\u0019\u0001C!\t\u0005\u000f\u0005e\"Q\u0003b\u0001=!A\u00111\u001aB\u000b\u0001\u0004\u0011)\u0003E\u0003\u0016Ym\u0011y\u0002C\u0004\u0003*\u0001!\tAa\u000b\u0002\u001fQ\u0014\u0018M\\:g_Jlg+\u00197vKN$B!a&\u0003.!A\u00111\u001aB\u0014\u0001\u0004\u0011y\u0003\u0005\u0003\u0016YmYra\u0002B\u001a\u0005!\u0005!QG\u0001\n\u0003:L(+\u001a4NCB\u00042\u0001\u0004B\u001c\r\u0019\t!\u0001#\u0001\u0003:M\u0019!q\u0007\r\t\u000fe\u00129\u0004\"\u0001\u0003>Q\u0011!Q\u0007\u0005\u000b\u0005\u0003\u00129D1A\u0005\u000e\t\r\u0013!C%oI\u0016DX*Y:l+\t\u0011)e\u0004\u0002\u0003Hu!qh\u0000\u0000\u0000\u0012%\u0011YEa\u000e!\u0002\u001b\u0011)%\u0001\u0006J]\u0012,\u00070T1tW\u0002B!Ba\u0014\u00038\t\u0007IQ\u0002B)\u0003)i\u0015n]:j]\u001e\u0014\u0015\u000e^\u000b\u0003\u0005'z!A!\u0016\u001e\t\u0001\u0005\u0001\u0001\u0001\u0005\n\u00053\u00129\u0004)A\u0007\u0005'\n1\"T5tg&twMQ5uA!Q!Q\fB\u001c\u0005\u0004%iAa\u0018\u0002\u0013Y\u000b7-\u00198u\u0005&$XC\u0001B1\u001f\t\u0011\u0019'\b\u0003A\u0001\u0001\u0001\u0001\"\u0003B4\u0005o\u0001\u000bQ\u0002B1\u0003)1\u0016mY1oi\nKG\u000f\t\u0005\u000b\u0005W\u00129D1A\u0005\u000e\t5\u0014AC'jgN4\u0016mY1oiV\u0011!qN\b\u0003\u0005cjB\u00011\u0001\u0001\u0001!I!Q\u000fB\u001cA\u00035!qN\u0001\f\u001b&\u001c8OV1dC:$\b\u0005\u0003\u0006\u0003z\t]\"\u0019!C\u0005\u0005w\n\u0001#\u001a=dKB$\u0018n\u001c8EK\u001a\fW\u000f\u001c;\u0016\u0005\tu\u0004\u0003B\u000b-?QA\u0011B!!\u00038\u0001\u0006IA! \u0002#\u0015D8-\u001a9uS>tG)\u001a4bk2$\b\u0005\u0003\u0005\u0003\u0006\n]B1\u0001BD\u00031\u0019\u0017M\u001c\"vS2$gI]8n+)\u0011IIa'\u0003 \n\u0015&1V\u000b\u0003\u0005\u0017\u0003\"B!$\u0003\u0014\n]%\u0011\u0015BW\u001b\t\u0011yIC\u0002\u0003\u0012\u0012\tqaZ3oKJL7-\u0003\u0003\u0003\u0016\n=%\u0001D\"b]\n+\u0018\u000e\u001c3Ge>l\u0007C\u0002\u0007\u0001\u00053\u0013i\nE\u0002\u0011\u00057#aA\u0005BB\u0005\u0004\u0019\u0002c\u0001\t\u0003 \u00121QDa!C\u0002y\u0001r!FAT\u0005G\u0013I\u000bE\u0002\u0011\u0005K#qAa*\u0003\u0004\n\u00071CA\u0001K!\r\u0001\"1\u0016\u0003\b\u0003'\u0014\u0019I1\u0001\u001f!\u0019a\u0001Aa)\u0003*\u001a9!\u0011\u0017B\u001c\u0005\tM&\u0001E!osJ+g-T1q\u0005VLG\u000eZ3s+\u0019\u0011)L!1\u0003FN)!q\u0016\r\u00038B9AB!/\u0003>\n\u001d\u0017b\u0001B^\u0005\t9!)^5mI\u0016\u0014\bcB\u000b\u0002(\n}&1\u0019\t\u0004!\t\u0005GA\u0002\n\u00030\n\u00071\u0003E\u0002\u0011\u0005\u000b$a!\bBX\u0005\u0004q\u0002C\u0002\u0007\u0001\u0005\u007f\u0013\u0019\rC\u0004:\u0005_#\tAa3\u0015\u0005\t5\u0007\u0003\u0003Bh\u0005_\u0013yLa1\u000e\u0005\t]\u0002bCAr\u0005_\u0003\r\u0011\"\u0001\u0005\u0005',\"Aa2\t\u0017\t]'q\u0016a\u0001\n\u0003!!\u0011\\\u0001\nK2,Wn]0%KF$2!\u0018Bn\u0011)\u0011iN!6\u0002\u0002\u0003\u0007!qY\u0001\u0004q\u0012\n\u0004\"\u0003Bq\u0005_\u0003\u000b\u0015\u0002Bd\u0003\u0019)G.Z7tA!A\u00111\u0013BX\t\u0003\u0011)\u000f\u0006\u0003\u0003h\n%XB\u0001BX\u0011!\u0011YOa9A\u0002\tu\u0016!B3oiJL\b\u0002\u0003Bx\u0005_#\t!!\u001f\u0002\u000b\rdW-\u0019:\t\u0011\tM(q\u0016C\u0001\u0005k\faA]3tk2$HC\u0001Bd\u0011!\t\tGa\u000e\u0005\u0002\teXC\u0002B~\u0007\u0003\u0019)\u0001\u0006\u0003\u0003~\u000e\u001d\u0001C\u0002\u0007\u0001\u0005\u007f\u001c\u0019\u0001E\u0002\u0011\u0007\u0003!aA\u0005B|\u0005\u0004\u0019\u0002c\u0001\t\u0004\u0006\u00111QDa>C\u0002yA\u0001\"a9\u0003x\u0002\u00071\u0011\u0002\t\u0006+\r-1qB\u0005\u0004\u0007\u001b1!A\u0003\u001fsKB,\u0017\r^3e}A9Q#a*\u0003\u0000\u000e\r\u0001bB;\u00038\u0011\u000511C\u000b\u0007\u0007+\u0019Yba\b\u0016\u0005\r]\u0001C\u0002\u0007\u0001\u00073\u0019i\u0002E\u0002\u0011\u00077!aAEB\t\u0005\u0004\u0019\u0002c\u0001\t\u0004 \u00111Qd!\u0005C\u0002yA\u0001ba\t\u00038\u0011\u00051QE\u0001\fo&$\b\u000eR3gCVdG/\u0006\u0004\u0004(\r52\u0011\u0007\u000b\u0005\u0007S\u0019\u0019\u0004\u0005\u0004\r\u0001\r-2q\u0006\t\u0004!\r5BA\u0002\n\u0004\"\t\u00071\u0003E\u0002\u0011\u0007c!a!HB\u0011\u0005\u0004q\u0002\u0002CA!\u0007C\u0001\ra!\u000e\u0011\rUa31FB\u0018\u0011!\u0019IDa\u000e\u0005\u0002\rm\u0012a\u00024s_6T\u0016\u000e]\u000b\u0007\u0007{\u0019\u0019ea\u0012\u0015\r\r}2\u0011JB(!\u0019a\u0001a!\u0011\u0004FA\u0019\u0001ca\u0011\u0005\rI\u00199D1\u0001\u0014!\r\u00012q\t\u0003\u0007;\r]\"\u0019\u0001\u0010\t\u0011\r-3q\u0007a\u0001\u0007\u001b\nAa[3zgB!QcUB!\u0011!\u0019\tfa\u000eA\u0002\rM\u0013A\u0002<bYV,7\u000f\u0005\u0003\u0016'\u000e\u0015\u0003\u0002CB\u001d\u0005o!\taa\u0016\u0016\r\re3qLB2)\u0019\u0019Yf!\u001a\u0004nA1A\u0002AB/\u0007C\u00022\u0001EB0\t\u0019\u00112Q\u000bb\u0001'A\u0019\u0001ca\u0019\u0005\ru\u0019)F1\u0001\u001f\u0011!\u0019Ye!\u0016A\u0002\r\u001d\u0004#\u0002\u0007\u0004j\ru\u0013bAB6\u0005\tA\u0011\n^3sC\ndW\r\u0003\u0005\u0004R\rU\u0003\u0019AB8!\u0015a1\u0011NB1\u0001")
public final class AnyRefMap<K, V>
extends AbstractMap<K, V> {
    private final Function1<K, V> defaultEntry;
    private int mask;
    private int _size;
    private int _vacant;
    public int[] scala$collection$mutable$AnyRefMap$$_hashes;
    public Object[] scala$collection$mutable$AnyRefMap$$_keys;
    public Object[] scala$collection$mutable$AnyRefMap$$_values;

    public static <K, V> AnyRefMap<K, V> fromZip(scala.collection.mutable.Iterable<K> iterable, scala.collection.mutable.Iterable<V> iterable2) {
        return AnyRefMap$.MODULE$.fromZip(iterable, iterable2);
    }

    public static <K, V> AnyRefMap<K, V> fromZip(K[] KArray, Object object) {
        return AnyRefMap$.MODULE$.fromZip(KArray, object);
    }

    public static <K, V, J, U> CanBuildFrom<AnyRefMap<K, V>, Tuple2<J, U>, AnyRefMap<J, U>> canBuildFrom() {
        return AnyRefMap$.MODULE$.canBuildFrom();
    }

    private void defaultInitialize(int n) {
        this.mask = n < 0 ? 7 : (1 << 32 - Integer.numberOfLeadingZeros(n - 1)) - 1 & 0x3FFFFFFF | 7;
        this.scala$collection$mutable$AnyRefMap$$_hashes = new int[this.mask + 1];
        this.scala$collection$mutable$AnyRefMap$$_keys = new Object[this.mask + 1];
        this.scala$collection$mutable$AnyRefMap$$_values = new Object[this.mask + 1];
    }

    public void initializeTo(int m, int sz, int vc, int[] hz, Object[] kz, Object[] vz) {
        this.mask = m;
        this._size = sz;
        this._vacant = vc;
        this.scala$collection$mutable$AnyRefMap$$_hashes = hz;
        this.scala$collection$mutable$AnyRefMap$$_keys = kz;
        this.scala$collection$mutable$AnyRefMap$$_values = vz;
    }

    @Override
    public int size() {
        return this._size;
    }

    @Override
    public AnyRefMap<K, V> empty() {
        return new AnyRefMap<K, V>(this.defaultEntry);
    }

    private boolean imbalanced() {
        return (double)(this._size + this._vacant) > 0.5 * (double)this.mask || this._vacant > this._size;
    }

    private int hashOf(K key) {
        int h;
        int i;
        int j;
        return key == null ? 1091049865 : ((j = ((i = ((h = key.hashCode()) ^ h >>> 16) * -2048144789) ^ i >>> 13) & Integer.MAX_VALUE) == 0 ? 1091049865 : j);
    }

    private int seekEntry(int h, Object k) {
        int g;
        int e = h & this.mask;
        int x = 0;
        while ((g = this.scala$collection$mutable$AnyRefMap$$_hashes[e]) != 0) {
            Object q;
            if (g == h && ((q = this.scala$collection$mutable$AnyRefMap$$_keys[e]) == k || q != null && q.equals(k))) {
                return e;
            }
            e = e + 2 * (++x + 1) * x - 3 & this.mask;
        }
        return e | Integer.MIN_VALUE;
    }

    private int seekEntryOrOpen(int h, Object k) {
        int g;
        int e = h & this.mask;
        int x = 0;
        int o = -1;
        while ((g = this.scala$collection$mutable$AnyRefMap$$_hashes[e]) != 0) {
            Object q;
            if (g == h && ((q = this.scala$collection$mutable$AnyRefMap$$_keys[e]) == k || q != null && q.equals(k))) {
                return e;
            }
            if (o == -1 && g + g == 0) {
                o = e;
            }
            e = e + 2 * (++x + 1) * x - 3 & this.mask;
        }
        return o >= 0 ? o | 0xC0000000 : e | Integer.MIN_VALUE;
    }

    @Override
    public boolean contains(K key) {
        return this.seekEntry(this.hashOf(key), key) >= 0;
    }

    @Override
    public Option<V> get(K key) {
        int i = this.seekEntry(this.hashOf(key), key);
        return i < 0 ? None$.MODULE$ : new Some<Object>(this.scala$collection$mutable$AnyRefMap$$_values[i]);
    }

    @Override
    public <V1> V1 getOrElse(K key, Function0<V1> function0) {
        int i = this.seekEntry(this.hashOf(key), key);
        return (V1)(i < 0 ? function0.apply() : this.scala$collection$mutable$AnyRefMap$$_values[i]);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public V getOrElseUpdate(K key, Function0<V> defaultValue) {
        Object object;
        int h = this.hashOf(key);
        int i = this.seekEntryOrOpen(h, key);
        if (i < 0) {
            void var6_6;
            int[] oh = this.scala$collection$mutable$AnyRefMap$$_hashes;
            V ans = defaultValue.apply();
            if (oh != this.scala$collection$mutable$AnyRefMap$$_hashes && (i = this.seekEntryOrOpen(h, key)) >= 0) {
                --this._size;
            }
            ++this._size;
            int j = i & 0x3FFFFFFF;
            this.scala$collection$mutable$AnyRefMap$$_hashes[j] = h;
            this.scala$collection$mutable$AnyRefMap$$_keys[j] = key;
            this.scala$collection$mutable$AnyRefMap$$_values[j] = var6_6;
            if ((i & 0x40000000) != 0) {
                --this._vacant;
            } else if (this.imbalanced()) {
                this.repack();
            }
            object = var6_6;
        } else {
            object = this.scala$collection$mutable$AnyRefMap$$_values[i];
        }
        return (V)object;
    }

    public V getOrNull(K key) {
        int i = this.seekEntry(this.hashOf(key), key);
        return (V)(i < 0 ? null : this.scala$collection$mutable$AnyRefMap$$_values[i]);
    }

    @Override
    public V apply(K key) {
        int i = this.seekEntry(this.hashOf(key), key);
        return (V)(i < 0 ? this.defaultEntry.apply(key) : this.scala$collection$mutable$AnyRefMap$$_values[i]);
    }

    @Override
    public V default(K key) {
        return this.defaultEntry.apply(key);
    }

    private void repack(int newMask) {
        int[] oh = this.scala$collection$mutable$AnyRefMap$$_hashes;
        Object[] ok = this.scala$collection$mutable$AnyRefMap$$_keys;
        Object[] ov = this.scala$collection$mutable$AnyRefMap$$_values;
        this.mask = newMask;
        this.scala$collection$mutable$AnyRefMap$$_hashes = new int[this.mask + 1];
        this.scala$collection$mutable$AnyRefMap$$_keys = new Object[this.mask + 1];
        this.scala$collection$mutable$AnyRefMap$$_values = new Object[this.mask + 1];
        this._vacant = 0;
        for (int i = 0; i < oh.length; ++i) {
            int h = oh[i];
            if (h + h == 0) continue;
            int e = h & this.mask;
            int x = 0;
            while (this.scala$collection$mutable$AnyRefMap$$_hashes[e] != 0) {
                e = e + 2 * (++x + 1) * x - 3 & this.mask;
            }
            this.scala$collection$mutable$AnyRefMap$$_hashes[e] = h;
            this.scala$collection$mutable$AnyRefMap$$_keys[e] = ok[i];
            this.scala$collection$mutable$AnyRefMap$$_values[e] = ov[i];
        }
    }

    public void repack() {
        int m = this.mask;
        if ((double)(this._size + this._vacant) >= 0.5 * (double)this.mask && !((double)this._vacant > 0.2 * (double)this.mask)) {
            m = (m << 1) + 1 & 0x3FFFFFFF;
        }
        while (m > 8 && 8 * this._size < m) {
            m >>>= 1;
        }
        this.repack(m);
    }

    @Override
    public Option<V> put(K key, V value) {
        Option option;
        int h = this.hashOf(key);
        int i = this.seekEntryOrOpen(h, key);
        if (i < 0) {
            int j = i & 0x3FFFFFFF;
            this.scala$collection$mutable$AnyRefMap$$_hashes[j] = h;
            this.scala$collection$mutable$AnyRefMap$$_keys[j] = key;
            this.scala$collection$mutable$AnyRefMap$$_values[j] = value;
            ++this._size;
            if ((i & 0x40000000) != 0) {
                --this._vacant;
            } else if (this.imbalanced()) {
                this.repack();
            }
            option = None$.MODULE$;
        } else {
            Some<Object> ans = new Some<Object>(this.scala$collection$mutable$AnyRefMap$$_values[i]);
            this.scala$collection$mutable$AnyRefMap$$_hashes[i] = h;
            this.scala$collection$mutable$AnyRefMap$$_keys[i] = key;
            this.scala$collection$mutable$AnyRefMap$$_values[i] = value;
            option = ans;
        }
        return option;
    }

    @Override
    public void update(K key, V value) {
        int h = this.hashOf(key);
        int i = this.seekEntryOrOpen(h, key);
        if (i < 0) {
            int j = i & 0x3FFFFFFF;
            this.scala$collection$mutable$AnyRefMap$$_hashes[j] = h;
            this.scala$collection$mutable$AnyRefMap$$_keys[j] = key;
            this.scala$collection$mutable$AnyRefMap$$_values[j] = value;
            ++this._size;
            if ((i & 0x40000000) != 0) {
                --this._vacant;
            } else if (this.imbalanced()) {
                this.repack();
            }
        } else {
            this.scala$collection$mutable$AnyRefMap$$_hashes[i] = h;
            this.scala$collection$mutable$AnyRefMap$$_keys[i] = key;
            this.scala$collection$mutable$AnyRefMap$$_values[i] = value;
        }
    }

    public AnyRefMap<K, V> $plus$eq(K key, V value) {
        this.update(key, value);
        return this;
    }

    @Override
    public AnyRefMap<K, V> $plus$eq(Tuple2<K, V> kv) {
        this.update(kv._1(), kv._2());
        return this;
    }

    public AnyRefMap<K, V> $minus$eq(K key) {
        int i = this.seekEntry(this.hashOf(key), key);
        if (i >= 0) {
            --this._size;
            ++this._vacant;
            this.scala$collection$mutable$AnyRefMap$$_hashes[i] = Integer.MIN_VALUE;
            this.scala$collection$mutable$AnyRefMap$$_keys[i] = null;
            this.scala$collection$mutable$AnyRefMap$$_values[i] = null;
        }
        return this;
    }

    @Override
    public Iterator<Tuple2<K, V>> iterator() {
        return new Iterator<Tuple2<K, V>>(this){
            private final int[] hz;
            private final Object[] kz;
            private final Object[] vz;
            private int index;

            public Iterator<Tuple2<K, V>> seq() {
                return Iterator$class.seq(this);
            }

            public boolean isEmpty() {
                return Iterator$class.isEmpty(this);
            }

            public boolean isTraversableAgain() {
                return Iterator$class.isTraversableAgain(this);
            }

            public boolean hasDefiniteSize() {
                return Iterator$class.hasDefiniteSize(this);
            }

            public Iterator<Tuple2<K, V>> take(int n) {
                return Iterator$class.take(this, n);
            }

            public Iterator<Tuple2<K, V>> drop(int n) {
                return Iterator$class.drop(this, n);
            }

            public Iterator<Tuple2<K, V>> slice(int from2, int until2) {
                return Iterator$class.slice(this, from2, until2);
            }

            public <B> Iterator<B> map(Function1<Tuple2<K, V>, B> f) {
                return Iterator$class.map(this, f);
            }

            public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
                return Iterator$class.$plus$plus(this, that);
            }

            public <B> Iterator<B> flatMap(Function1<Tuple2<K, V>, GenTraversableOnce<B>> f) {
                return Iterator$class.flatMap(this, f);
            }

            public Iterator<Tuple2<K, V>> filter(Function1<Tuple2<K, V>, Object> p) {
                return Iterator$class.filter(this, p);
            }

            public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<Tuple2<K, V>, B, Object> p) {
                return Iterator$class.corresponds(this, that, p);
            }

            public Iterator<Tuple2<K, V>> withFilter(Function1<Tuple2<K, V>, Object> p) {
                return Iterator$class.withFilter(this, p);
            }

            public Iterator<Tuple2<K, V>> filterNot(Function1<Tuple2<K, V>, Object> p) {
                return Iterator$class.filterNot(this, p);
            }

            public <B> Iterator<B> collect(PartialFunction<Tuple2<K, V>, B> pf) {
                return Iterator$class.collect(this, pf);
            }

            public <B> Iterator<B> scanLeft(B z, Function2<B, Tuple2<K, V>, B> op) {
                return Iterator$class.scanLeft(this, z, op);
            }

            public <B> Iterator<B> scanRight(B z, Function2<Tuple2<K, V>, B, B> op) {
                return Iterator$class.scanRight(this, z, op);
            }

            public Iterator<Tuple2<K, V>> takeWhile(Function1<Tuple2<K, V>, Object> p) {
                return Iterator$class.takeWhile(this, p);
            }

            public Tuple2<Iterator<Tuple2<K, V>>, Iterator<Tuple2<K, V>>> partition(Function1<Tuple2<K, V>, Object> p) {
                return Iterator$class.partition(this, p);
            }

            public Tuple2<Iterator<Tuple2<K, V>>, Iterator<Tuple2<K, V>>> span(Function1<Tuple2<K, V>, Object> p) {
                return Iterator$class.span(this, p);
            }

            public Iterator<Tuple2<K, V>> dropWhile(Function1<Tuple2<K, V>, Object> p) {
                return Iterator$class.dropWhile(this, p);
            }

            public <B> Iterator<Tuple2<Tuple2<K, V>, B>> zip(Iterator<B> that) {
                return Iterator$class.zip(this, that);
            }

            public <A1> Iterator<A1> padTo(int len, A1 elem) {
                return Iterator$class.padTo(this, len, elem);
            }

            public Iterator<Tuple2<Tuple2<K, V>, Object>> zipWithIndex() {
                return Iterator$class.zipWithIndex(this);
            }

            public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
                return Iterator$class.zipAll(this, that, thisElem, thatElem);
            }

            public <U> void foreach(Function1<Tuple2<K, V>, U> f) {
                Iterator$class.foreach(this, f);
            }

            public boolean forall(Function1<Tuple2<K, V>, Object> p) {
                return Iterator$class.forall(this, p);
            }

            public boolean exists(Function1<Tuple2<K, V>, Object> p) {
                return Iterator$class.exists(this, p);
            }

            public boolean contains(Object elem) {
                return Iterator$class.contains(this, elem);
            }

            public Option<Tuple2<K, V>> find(Function1<Tuple2<K, V>, Object> p) {
                return Iterator$class.find(this, p);
            }

            public int indexWhere(Function1<Tuple2<K, V>, Object> p) {
                return Iterator$class.indexWhere(this, p);
            }

            public <B> int indexOf(B elem) {
                return Iterator$class.indexOf(this, elem);
            }

            public BufferedIterator<Tuple2<K, V>> buffered() {
                return Iterator$class.buffered(this);
            }

            public <B> Iterator.GroupedIterator<B> grouped(int size2) {
                return Iterator$class.grouped(this, size2);
            }

            public <B> Iterator.GroupedIterator<B> sliding(int size2, int step) {
                return Iterator$class.sliding(this, size2, step);
            }

            public int length() {
                return Iterator$class.length(this);
            }

            public Tuple2<Iterator<Tuple2<K, V>>, Iterator<Tuple2<K, V>>> duplicate() {
                return Iterator$class.duplicate(this);
            }

            public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
                return Iterator$class.patch(this, from2, patchElems, replaced);
            }

            public <B> void copyToArray(Object xs, int start, int len) {
                Iterator$class.copyToArray(this, xs, start, len);
            }

            public boolean sameElements(Iterator<?> that) {
                return Iterator$class.sameElements(this, that);
            }

            public Traversable<Tuple2<K, V>> toTraversable() {
                return Iterator$class.toTraversable(this);
            }

            public Iterator<Tuple2<K, V>> toIterator() {
                return Iterator$class.toIterator(this);
            }

            public Stream<Tuple2<K, V>> toStream() {
                return Iterator$class.toStream(this);
            }

            public String toString() {
                return Iterator$class.toString(this);
            }

            public <B> int sliding$default$2() {
                return Iterator$class.sliding$default$2(this);
            }

            public List<Tuple2<K, V>> reversed() {
                return TraversableOnce$class.reversed(this);
            }

            public int size() {
                return TraversableOnce$class.size(this);
            }

            public boolean nonEmpty() {
                return TraversableOnce$class.nonEmpty(this);
            }

            public int count(Function1<Tuple2<K, V>, Object> p) {
                return TraversableOnce$class.count(this, p);
            }

            public <B> Option<B> collectFirst(PartialFunction<Tuple2<K, V>, B> pf) {
                return TraversableOnce$class.collectFirst(this, pf);
            }

            public <B> B $div$colon(B z, Function2<B, Tuple2<K, V>, B> op) {
                return (B)TraversableOnce$class.$div$colon(this, z, op);
            }

            public <B> B $colon$bslash(B z, Function2<Tuple2<K, V>, B, B> op) {
                return (B)TraversableOnce$class.$colon$bslash(this, z, op);
            }

            public <B> B foldLeft(B z, Function2<B, Tuple2<K, V>, B> op) {
                return (B)TraversableOnce$class.foldLeft(this, z, op);
            }

            public <B> B foldRight(B z, Function2<Tuple2<K, V>, B, B> op) {
                return (B)TraversableOnce$class.foldRight(this, z, op);
            }

            public <B> B reduceLeft(Function2<B, Tuple2<K, V>, B> op) {
                return (B)TraversableOnce$class.reduceLeft(this, op);
            }

            public <B> B reduceRight(Function2<Tuple2<K, V>, B, B> op) {
                return (B)TraversableOnce$class.reduceRight(this, op);
            }

            public <B> Option<B> reduceLeftOption(Function2<B, Tuple2<K, V>, B> op) {
                return TraversableOnce$class.reduceLeftOption(this, op);
            }

            public <B> Option<B> reduceRightOption(Function2<Tuple2<K, V>, B, B> op) {
                return TraversableOnce$class.reduceRightOption(this, op);
            }

            public <A1> A1 reduce(Function2<A1, A1, A1> op) {
                return (A1)TraversableOnce$class.reduce(this, op);
            }

            public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
                return TraversableOnce$class.reduceOption(this, op);
            }

            public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
                return (A1)TraversableOnce$class.fold(this, z, op);
            }

            public <B> B aggregate(Function0<B> z, Function2<B, Tuple2<K, V>, B> seqop, Function2<B, B, B> combop) {
                return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
            }

            public <B> B sum(Numeric<B> num) {
                return (B)TraversableOnce$class.sum(this, num);
            }

            public <B> B product(Numeric<B> num) {
                return (B)TraversableOnce$class.product(this, num);
            }

            public Object min(Ordering cmp) {
                return TraversableOnce$class.min(this, cmp);
            }

            public Object max(Ordering cmp) {
                return TraversableOnce$class.max(this, cmp);
            }

            public Object maxBy(Function1 f, Ordering cmp) {
                return TraversableOnce$class.maxBy(this, f, cmp);
            }

            public Object minBy(Function1 f, Ordering cmp) {
                return TraversableOnce$class.minBy(this, f, cmp);
            }

            public <B> void copyToBuffer(Buffer<B> dest) {
                TraversableOnce$class.copyToBuffer(this, dest);
            }

            public <B> void copyToArray(Object xs, int start) {
                TraversableOnce$class.copyToArray(this, xs, start);
            }

            public <B> void copyToArray(Object xs) {
                TraversableOnce$class.copyToArray(this, xs);
            }

            public <B> Object toArray(ClassTag<B> evidence$1) {
                return TraversableOnce$class.toArray(this, evidence$1);
            }

            public List<Tuple2<K, V>> toList() {
                return TraversableOnce$class.toList(this);
            }

            public Iterable<Tuple2<K, V>> toIterable() {
                return TraversableOnce$class.toIterable(this);
            }

            public Seq<Tuple2<K, V>> toSeq() {
                return TraversableOnce$class.toSeq(this);
            }

            public IndexedSeq<Tuple2<K, V>> toIndexedSeq() {
                return TraversableOnce$class.toIndexedSeq(this);
            }

            public <B> Buffer<B> toBuffer() {
                return TraversableOnce$class.toBuffer(this);
            }

            public <B> Set<B> toSet() {
                return TraversableOnce$class.toSet(this);
            }

            public Vector<Tuple2<K, V>> toVector() {
                return TraversableOnce$class.toVector(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, Tuple2<K, V>, Col> cbf) {
                return (Col)TraversableOnce$class.to(this, cbf);
            }

            public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Tuple2<K, V>, Tuple2<T, U>> ev) {
                return TraversableOnce$class.toMap(this, ev);
            }

            public String mkString(String start, String sep, String end) {
                return TraversableOnce$class.mkString(this, start, sep, end);
            }

            public String mkString(String sep) {
                return TraversableOnce$class.mkString(this, sep);
            }

            public String mkString() {
                return TraversableOnce$class.mkString(this);
            }

            public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
                return TraversableOnce$class.addString(this, b, start, sep, end);
            }

            public StringBuilder addString(StringBuilder b, String sep) {
                return TraversableOnce$class.addString(this, b, sep);
            }

            public StringBuilder addString(StringBuilder b) {
                return TraversableOnce$class.addString(this, b);
            }

            public boolean hasNext() {
                boolean bl;
                if (this.index < this.hz.length) {
                    int h = this.hz[this.index];
                    while (h + h == 0) {
                        ++this.index;
                        if (this.index >= this.hz.length) {
                            return false;
                        }
                        h = this.hz[this.index];
                    }
                    bl = true;
                } else {
                    bl = false;
                }
                return bl;
            }

            /*
             * WARNING - void declaration
             */
            public Tuple2<K, V> next() {
                if (this.hasNext()) {
                    void var1_1;
                    Tuple2<Object, Object> ans = new Tuple2<Object, Object>(this.kz[this.index], this.vz[this.index]);
                    ++this.index;
                    return var1_1;
                }
                throw new NoSuchElementException("next");
            }
            {
                TraversableOnce$class.$init$(this);
                Iterator$class.$init$(this);
                this.hz = $outer.scala$collection$mutable$AnyRefMap$$_hashes;
                this.kz = $outer.scala$collection$mutable$AnyRefMap$$_keys;
                this.vz = $outer.scala$collection$mutable$AnyRefMap$$_values;
                this.index = 0;
            }
        };
    }

    @Override
    public <U> void foreach(Function1<Tuple2<K, V>, U> f) {
        int i = 0;
        for (int e = this._size; e > 0; --e) {
            int h;
            while (i < this.scala$collection$mutable$AnyRefMap$$_hashes.length && (h = this.scala$collection$mutable$AnyRefMap$$_hashes[i]) + h == 0 && i < this.scala$collection$mutable$AnyRefMap$$_hashes.length) {
                ++i;
            }
            if (i < this.scala$collection$mutable$AnyRefMap$$_hashes.length) {
                f.apply(new Tuple2<Object, Object>(this.scala$collection$mutable$AnyRefMap$$_keys[i], this.scala$collection$mutable$AnyRefMap$$_values[i]));
                ++i;
                continue;
            }
            return;
        }
    }

    @Override
    public AnyRefMap<K, V> clone() {
        int[] hz = Arrays.copyOf(this.scala$collection$mutable$AnyRefMap$$_hashes, this.scala$collection$mutable$AnyRefMap$$_hashes.length);
        Object[] kz = Arrays.copyOf(this.scala$collection$mutable$AnyRefMap$$_keys, this.scala$collection$mutable$AnyRefMap$$_keys.length);
        Object[] vz = Arrays.copyOf(this.scala$collection$mutable$AnyRefMap$$_values, this.scala$collection$mutable$AnyRefMap$$_values.length);
        AnyRefMap<K, V> arm = new AnyRefMap<K, V>(this.defaultEntry, 1, false);
        arm.initializeTo(this.mask, this._size, this._vacant, hz, kz, vz);
        return arm;
    }

    private <A, B> void foreachElement(Object[] elems, Function1<A, B> f) {
        int i = 0;
        int j = 0;
        while (i < this.scala$collection$mutable$AnyRefMap$$_hashes.length & j < this._size) {
            BoxedUnit boxedUnit;
            int h = this.scala$collection$mutable$AnyRefMap$$_hashes[i];
            if (h + h != 0) {
                ++j;
                boxedUnit = f.apply(elems[i]);
            } else {
                boxedUnit = BoxedUnit.UNIT;
            }
            ++i;
        }
    }

    public <A> void foreachKey(Function1<K, A> f) {
        this.foreachElement(this.scala$collection$mutable$AnyRefMap$$_keys, f);
    }

    public <A> void foreachValue(Function1<V, A> f) {
        this.foreachElement(this.scala$collection$mutable$AnyRefMap$$_values, f);
    }

    /*
     * WARNING - void declaration
     */
    public <V1> AnyRefMap<K, V1> mapValuesNow(Function1<V, V1> f) {
        void var2_2;
        AnyRefMap<Object, Nothing$> arm = new AnyRefMap<Object, Nothing$>(AnyRefMap$.MODULE$.scala$collection$mutable$AnyRefMap$$exceptionDefault(), 1, false);
        int[] hz = Arrays.copyOf(this.scala$collection$mutable$AnyRefMap$$_hashes, this.scala$collection$mutable$AnyRefMap$$_hashes.length);
        Object[] kz = Arrays.copyOf(this.scala$collection$mutable$AnyRefMap$$_keys, this.scala$collection$mutable$AnyRefMap$$_keys.length);
        Object[] vz = new Object[this.scala$collection$mutable$AnyRefMap$$_values.length];
        int i = 0;
        int j = 0;
        while (i < this.scala$collection$mutable$AnyRefMap$$_hashes.length & j < this._size) {
            int h = this.scala$collection$mutable$AnyRefMap$$_hashes[i];
            if (h + h != 0) {
                ++j;
                vz[i] = f.apply(this.scala$collection$mutable$AnyRefMap$$_values[i]);
            }
            ++i;
        }
        arm.initializeTo(this.mask, this._size, this._vacant, hz, kz, vz);
        return var2_2;
    }

    public AnyRefMap<K, V> transformValues(Function1<V, V> f) {
        int i = 0;
        int j = 0;
        while (i < this.scala$collection$mutable$AnyRefMap$$_hashes.length & j < this._size) {
            int h = this.scala$collection$mutable$AnyRefMap$$_hashes[i];
            if (h + h != 0) {
                ++j;
                this.scala$collection$mutable$AnyRefMap$$_values[i] = f.apply(this.scala$collection$mutable$AnyRefMap$$_values[i]);
            }
            ++i;
        }
        return this;
    }

    public AnyRefMap(Function1<K, V> defaultEntry, int initialBufferSize, boolean initBlank) {
        this.defaultEntry = defaultEntry;
        this.mask = 0;
        this._size = 0;
        this._vacant = 0;
        this.scala$collection$mutable$AnyRefMap$$_hashes = null;
        this.scala$collection$mutable$AnyRefMap$$_keys = null;
        this.scala$collection$mutable$AnyRefMap$$_values = null;
        if (initBlank) {
            this.defaultInitialize(initialBufferSize);
        }
    }

    public AnyRefMap() {
        this(AnyRefMap$.MODULE$.scala$collection$mutable$AnyRefMap$$exceptionDefault(), 16, true);
    }

    public AnyRefMap(Function1<K, V> defaultEntry) {
        this(defaultEntry, 16, true);
    }

    public AnyRefMap(int initialBufferSize) {
        this(AnyRefMap$.MODULE$.scala$collection$mutable$AnyRefMap$$exceptionDefault(), initialBufferSize, true);
    }

    public AnyRefMap(Function1<K, V> defaultEntry, int initialBufferSize) {
        this(defaultEntry, initialBufferSize, true);
    }

    public static final class AnyRefMapBuilder<K, V>
    implements Builder<Tuple2<K, V>, AnyRefMap<K, V>> {
        private AnyRefMap<K, V> elems;

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
        public <NewTo> Builder<Tuple2<K, V>, NewTo> mapResult(Function1<AnyRefMap<K, V>, NewTo> f) {
            return Builder$class.mapResult(this, f);
        }

        @Override
        public Growable $plus$eq(Object elem1, Object elem2, Seq elems) {
            return Growable$class.$plus$eq(this, elem1, elem2, elems);
        }

        @Override
        public Growable<Tuple2<K, V>> $plus$plus$eq(TraversableOnce<Tuple2<K, V>> xs) {
            return Growable$class.$plus$plus$eq(this, xs);
        }

        public AnyRefMap<K, V> elems() {
            return this.elems;
        }

        public void elems_$eq(AnyRefMap<K, V> x$1) {
            this.elems = x$1;
        }

        @Override
        public AnyRefMapBuilder<K, V> $plus$eq(Tuple2<K, V> entry) {
            this.elems().$plus$eq((Tuple2)entry);
            return this;
        }

        @Override
        public void clear() {
            this.elems_$eq(new AnyRefMap());
        }

        @Override
        public AnyRefMap<K, V> result() {
            return this.elems();
        }

        public AnyRefMapBuilder() {
            Growable$class.$init$(this);
            Builder$class.$init$(this);
            this.elems = new AnyRefMap();
        }
    }
}

