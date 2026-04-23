/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterator;
import scala.collection.BufferedIterator;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator$;
import scala.collection.Iterator$class;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.StringLike$class;
import scala.collection.immutable.StringOps;
import scala.collection.immutable.Vector;
import scala.collection.immutable.Vector$;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayBuffer$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.package$;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing$;
import scala.runtime.RichInt$;
import scala.runtime.VolatileByteRef;

@ScalaSignature(bytes="\u0006\u0001\u0015]t!B\u0001\u0003\u0011\u00039\u0011\u0001C%uKJ\fGo\u001c:\u000b\u0005\r!\u0011AC2pY2,7\r^5p]*\tQ!A\u0003tG\u0006d\u0017m\u0001\u0001\u0011\u0005!IQ\"\u0001\u0002\u0007\u000b)\u0011\u0001\u0012A\u0006\u0003\u0011%#XM]1u_J\u001c\"!\u0003\u0007\u0011\u00055qQ\"\u0001\u0003\n\u0005=!!AB!osJ+g\rC\u0003\u0012\u0013\u0011\u0005!#\u0001\u0004=S:LGO\u0010\u000b\u0002\u000f!)A#\u0003C\u0002+\u0005!\u0012\n^3sCR|'oQ1o\u0005VLG\u000e\u001a$s_6,\"A\u0006\u0011\u0016\u0003]\u0001B\u0001G\u000e\u001fS9\u0011\u0001\"G\u0005\u00035\t\tq\u0002\u0016:bm\u0016\u00148/\u00192mK>s7-Z\u0005\u00039u\u0011ACQ;gM\u0016\u0014X\rZ\"b]\n+\u0018\u000e\u001c3Ge>l'B\u0001\u000e\u0003!\ty\u0002\u0005\u0004\u0001\u0005\u000b\u0005\u001a\"\u0019\u0001\u0012\u0003\u0003\u0005\u000b\"a\t\u0014\u0011\u00055!\u0013BA\u0013\u0005\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!D\u0014\n\u0005!\"!aA!osB\u0011\u0001B\u000b\u0004\t\u0015\t\u0001\n1!\u0001,wU\u0011A&M\n\u0004U1i\u0003c\u0001\u0005/a%\u0011qF\u0001\u0002\u0010)J\fg/\u001a:tC\ndWm\u00148dKB\u0011q$\r\u0003\u0007C)\")\u0019\u0001\u0012\t\u000bMRC\u0011\u0001\u001b\u0002\r\u0011Jg.\u001b;%)\u0005)\u0004CA\u00077\u0013\t9DA\u0001\u0003V]&$\b\"B\u001d+\t\u0003Q\u0014aA:fcV\t1\bE\u0002\tUABQ!\u0010\u0016\u0007\u0002y\nq\u0001[1t\u001d\u0016DH/F\u0001@!\ti\u0001)\u0003\u0002B\t\t9!i\\8mK\u0006t\u0007\"B\"+\r\u0003!\u0015\u0001\u00028fqR$\u0012\u0001\r\u0005\u0006\r*\"\tAP\u0001\bSN,U\u000e\u001d;z\u0011\u0015A%\u0006\"\u0001?\u0003II7\u000f\u0016:bm\u0016\u00148/\u00192mK\u0006;\u0017-\u001b8\t\u000b)SC\u0011\u0001 \u0002\u001f!\f7\u000fR3gS:LG/Z*ju\u0016DQ\u0001\u0014\u0016\u0005\u00025\u000bA\u0001^1lKR\u00111H\u0014\u0005\u0006\u001f.\u0003\r\u0001U\u0001\u0002]B\u0011Q\"U\u0005\u0003%\u0012\u00111!\u00138u\u0011\u0015!&\u0006\"\u0001V\u0003\u0011!'o\u001c9\u0015\u0005m2\u0006\"B(T\u0001\u0004\u0001\u0006\"\u0002-+\t\u0003I\u0016!B:mS\u000e,GcA\u001e[9\")1l\u0016a\u0001!\u0006!aM]8n\u0011\u0015iv\u000b1\u0001Q\u0003\u0015)h\u000e^5m\u0011\u0015y&\u0006\"\u0001a\u0003\ri\u0017\r]\u000b\u0003C\u0012$\"A\u00194\u0011\u0007!Q3\r\u0005\u0002 I\u0012)QM\u0018b\u0001E\t\t!\tC\u0003h=\u0002\u0007\u0001.A\u0001g!\u0011i\u0011\u000eM2\n\u0005)$!!\u0003$v]\u000e$\u0018n\u001c82\u0011\u0015a'\u0006\"\u0001n\u0003)!\u0003\u000f\\;tIAdWo]\u000b\u0003]F$\"a\\:\u0011\u0007!Q\u0003\u000f\u0005\u0002 c\u0012)Qm\u001bb\u0001eF\u0011\u0001G\n\u0005\u0007i.$\t\u0019A;\u0002\tQD\u0017\r\u001e\t\u0004\u001bYD\u0018BA<\u0005\u0005!a$-\u001f8b[\u0016t\u0004c\u0001\u0005za&\u0011!P\u0001\u0002\u0013\u000f\u0016tGK]1wKJ\u001c\u0018M\u00197f\u001f:\u001cW\rC\u0003}U\u0011\u0005Q0A\u0004gY\u0006$X*\u00199\u0016\u0007y\f\u0019\u0001F\u0002\u0000\u0003\u000b\u0001B\u0001\u0003\u0016\u0002\u0002A\u0019q$a\u0001\u0005\u000b\u0015\\(\u0019\u0001\u0012\t\r\u001d\\\b\u0019AA\u0004!\u0015i\u0011\u000eMA\u0005!\u0011A\u00110!\u0001\t\u000f\u00055!\u0006\"\u0001\u0002\u0010\u00051a-\u001b7uKJ$2aOA\t\u0011!\t\u0019\"a\u0003A\u0002\u0005U\u0011!\u00019\u0011\t5I\u0007g\u0010\u0005\b\u00033QC\u0011AA\u000e\u0003-\u0019wN\u001d:fgB|g\u000eZ:\u0016\t\u0005u\u00111\u0006\u000b\u0005\u0003?\ti\u0003F\u0002@\u0003CA\u0001\"a\u0005\u0002\u0018\u0001\u0007\u00111\u0005\t\b\u001b\u0005\u0015\u0002'!\u000b@\u0013\r\t9\u0003\u0002\u0002\n\rVt7\r^5p]J\u00022aHA\u0016\t\u0019)\u0017q\u0003b\u0001E!9A/a\u0006A\u0002\u0005=\u0002\u0003\u0002\u0005z\u0003SAq!a\r+\t\u0003\t)$\u0001\u0006xSRDg)\u001b7uKJ$2aOA\u001c\u0011!\t\u0019\"!\rA\u0002\u0005U\u0001bBA\u001eU\u0011\u0005\u0011QH\u0001\nM&dG/\u001a:O_R$2aOA \u0011!\t\u0019\"!\u000fA\u0002\u0005U\u0001bBA\"U\u0011\u0005\u0011QI\u0001\bG>dG.Z2u+\u0011\t9%!\u0014\u0015\t\u0005%\u0013q\n\t\u0005\u0011)\nY\u0005E\u0002 \u0003\u001b\"a!ZA!\u0005\u0004\u0011\u0003\u0002CA)\u0003\u0003\u0002\r!a\u0015\u0002\u0005A4\u0007CB\u0007\u0002VA\nY%C\u0002\u0002X\u0011\u0011q\u0002U1si&\fGNR;oGRLwN\u001c\u0015\t\u0003\u0003\nY&a\u001a\u0002lA!\u0011QLA2\u001b\t\tyFC\u0002\u0002b\u0011\t!\"\u00198o_R\fG/[8o\u0013\u0011\t)'a\u0018\u0003\u00135LwM]1uS>t\u0017EAA5\u00031\u00037m\u001c7mK\u000e$\b\r\t5bg\u0002\u001a\u0007.\u00198hK\u0012t\u0003\u0005\u00165fAA\u0014XM^5pkN\u0004#-\u001a5bm&|'\u000fI2b]\u0002\u0012W\r\t:faJ|G-^2fI\u0002:\u0018\u000e\u001e5!AR|7+Z9a]\u0005\u0012\u0011QN\u0001\u0006e9Bd\u0006\r\u0005\b\u0003cRC\u0011AA:\u0003!\u00198-\u00198MK\u001a$X\u0003BA;\u0003{\"B!a\u001e\u0002\u0006R!\u0011\u0011PA@!\u0011A!&a\u001f\u0011\u0007}\ti\b\u0002\u0004f\u0003_\u0012\rA\t\u0005\t\u0003\u0003\u000by\u00071\u0001\u0002\u0004\u0006\u0011q\u000e\u001d\t\t\u001b\u0005\u0015\u00121\u0010\u0019\u0002|!A\u0011qQA8\u0001\u0004\tY(A\u0001{\u0011\u001d\tYI\u000bC\u0001\u0003\u001b\u000b\u0011b]2b]JKw\r\u001b;\u0016\t\u0005=\u0015q\u0013\u000b\u0005\u0003#\u000bi\n\u0006\u0003\u0002\u0014\u0006e\u0005\u0003\u0002\u0005+\u0003+\u00032aHAL\t\u0019)\u0017\u0011\u0012b\u0001E!A\u0011\u0011QAE\u0001\u0004\tY\n\u0005\u0005\u000e\u0003K\u0001\u0014QSAK\u0011!\t9)!#A\u0002\u0005U\u0005bBAQU\u0011\u0005\u00111U\u0001\ni\u0006\\Wm\u00165jY\u0016$2aOAS\u0011!\t\u0019\"a(A\u0002\u0005U\u0001bBAUU\u0011\u0005\u00111V\u0001\na\u0006\u0014H/\u001b;j_:$B!!,\u00024B)Q\"a,<w%\u0019\u0011\u0011\u0017\u0003\u0003\rQ+\b\u000f\\33\u0011!\t\u0019\"a*A\u0002\u0005U\u0001bBA\\U\u0011\u0005\u0011\u0011X\u0001\u0005gB\fg\u000e\u0006\u0003\u0002.\u0006m\u0006\u0002CA\n\u0003k\u0003\r!!\u0006\t\u000f\u0005}&\u0006\"\u0001\u0002B\u0006IAM]8q/\"LG.\u001a\u000b\u0004w\u0005\r\u0007\u0002CA\n\u0003{\u0003\r!!\u0006\t\u000f\u0005\u001d'\u0006\"\u0001\u0002J\u0006\u0019!0\u001b9\u0016\t\u0005-\u00171\u001b\u000b\u0005\u0003\u001b\f)\u000e\u0005\u0003\tU\u0005=\u0007CB\u0007\u00020B\n\t\u000eE\u0002 \u0003'$a!ZAc\u0005\u0004\u0011\u0003b\u0002;\u0002F\u0002\u0007\u0011q\u001b\t\u0005\u0011)\n\t\u000eC\u0004\u0002\\*\"\t!!8\u0002\u000bA\fG\rV8\u0016\t\u0005}\u0017Q\u001d\u000b\u0007\u0003C\fI/!<\u0011\t!Q\u00131\u001d\t\u0004?\u0005\u0015HaBAt\u00033\u0014\rA\u001d\u0002\u0003\u0003FBq!a;\u0002Z\u0002\u0007\u0001+A\u0002mK:D\u0001\"a<\u0002Z\u0002\u0007\u00111]\u0001\u0005K2,W\u000eC\u0004\u0002t*\"\t!!>\u0002\u0019iL\u0007oV5uQ&sG-\u001a=\u0016\u0005\u0005]\b\u0003\u0002\u0005+\u0003s\u0004R!DAXaACq!!@+\t\u0003\ty0\u0001\u0004{SB\fE\u000e\\\u000b\t\u0005\u0003\u0011)B!\u0003\u0003\u000eQA!1\u0001B\f\u00057\u0011y\u0002\u0005\u0003\tU\t\u0015\u0001cB\u0007\u00020\n\u001d!1\u0002\t\u0004?\t%AaBAt\u0003w\u0014\rA\u001d\t\u0004?\t5A\u0001\u0003B\b\u0003w\u0014\rA!\u0005\u0003\u0005\t\u000b\u0014c\u0001B\nMA\u0019qD!\u0006\u0005\r\u0015\fYP1\u0001#\u0011\u001d!\u00181 a\u0001\u00053\u0001B\u0001\u0003\u0016\u0003\u0014!A!QDA~\u0001\u0004\u00119!\u0001\u0005uQ&\u001cX\t\\3n\u0011!\u0011\t#a?A\u0002\t-\u0011\u0001\u0003;iCR,E.Z7\t\u000f\t\u0015\"\u0006\"\u0001\u0003(\u00059am\u001c:fC\u000eDW\u0003\u0002B\u0015\u0005c!2!\u000eB\u0016\u0011\u001d9'1\u0005a\u0001\u0005[\u0001R!D51\u0005_\u00012a\bB\u0019\t\u001d\u0011\u0019Da\tC\u0002\t\u0012\u0011!\u0016\u0005\b\u0005oQC\u0011\u0001B\u001d\u0003\u00191wN]1mYR\u0019qHa\u000f\t\u0011\u0005M!Q\u0007a\u0001\u0003+AqAa\u0010+\t\u0003\u0011\t%\u0001\u0004fq&\u001cHo\u001d\u000b\u0004\u007f\t\r\u0003\u0002CA\n\u0005{\u0001\r!!\u0006\t\u000f\t\u001d#\u0006\"\u0001\u0003J\u0005A1m\u001c8uC&t7\u000fF\u0002@\u0005\u0017Bq!a<\u0003F\u0001\u0007a\u0005C\u0004\u0003P)\"\tA!\u0015\u0002\t\u0019Lg\u000e\u001a\u000b\u0005\u0005'\u0012I\u0006\u0005\u0003\u000e\u0005+\u0002\u0014b\u0001B,\t\t1q\n\u001d;j_:D\u0001\"a\u0005\u0003N\u0001\u0007\u0011Q\u0003\u0005\b\u0005;RC\u0011\u0001B0\u0003)Ig\u000eZ3y/\",'/\u001a\u000b\u0004!\n\u0005\u0004\u0002CA\n\u00057\u0002\r!!\u0006\t\u000f\t\u0015$\u0006\"\u0001\u0003h\u00059\u0011N\u001c3fq>3W\u0003\u0002B5\u0005_\"2\u0001\u0015B6\u0011!\tyOa\u0019A\u0002\t5\u0004cA\u0010\u0003p\u00111QMa\u0019C\u0002IDqAa\u001d+\t\u0003\u0011)(\u0001\u0005ck\u001a4WM]3e+\t\u00119\b\u0005\u0003\t\u0005s\u0002\u0014b\u0001B>\u0005\t\u0001\")\u001e4gKJ,G-\u0013;fe\u0006$xN\u001d\u0004\u0007\u0005\u007fR\u0003A!!\u0003\u001f\u001d\u0013x.\u001e9fI&#XM]1u_J,BAa!\u0003\u0014N1!Q\u0010BC\u0005+\u0003R\u0001\u0003BD\u0005\u0017K1A!#\u0003\u0005A\t%m\u001d;sC\u000e$\u0018\n^3sCR|'\u000fE\u0003\t\u0005\u001b\u0013\t*C\u0002\u0003\u0010\n\u00111aU3r!\ry\"1\u0013\u0003\u0007K\nu$\u0019\u0001:\u0011\t!Q#1\u0012\u0005\u000b\u00053\u0013iH!A!\u0002\u0013Y\u0014\u0001B:fY\u001aD!B!(\u0003~\t\u0005\t\u0015!\u0003Q\u0003\u0011\u0019\u0018N_3\t\u0015\t\u0005&Q\u0010B\u0001B\u0003%\u0001+\u0001\u0003ti\u0016\u0004\bbB\t\u0003~\u0011\u0005!Q\u0015\u000b\t\u0005O\u0013YK!,\u00030B1!\u0011\u0016B?\u0005#k\u0011A\u000b\u0005\b\u00053\u0013\u0019\u000b1\u0001<\u0011\u001d\u0011iJa)A\u0002ACqA!)\u0003$\u0002\u0007\u0001\u000bC\u0005\u00034\nu\u0004\u0015)\u0003\u00036\u00061!-\u001e4gKJ\u0004bAa.\u0003>\nEUB\u0001B]\u0015\r\u0011YLA\u0001\b[V$\u0018M\u00197f\u0013\u0011\u0011yL!/\u0003\u0017\u0005\u0013(/Y=Ck\u001a4WM\u001d\u0005\t\u0005\u0007\u0014i\b)Q\u0005\u007f\u00051a-\u001b7mK\u0012D\u0001Ba2\u0003~\u0001\u0006KaP\u0001\t?B\f'\u000f^5bY\"I!1\u001aB?A\u0003&!QZ\u0001\u0004a\u0006$\u0007#B\u0007\u0003V\t=\u0007#B\u0007\u0003R\nE\u0015b\u0001Bj\t\tIa)\u001e8di&|g\u000e\r\u0005\t\u0005/\u0014i\b\"\u0001\u0003Z\u0006Yq/\u001b;i!\u0006$G-\u001b8h)\u0011\u0011YN!8\u000e\u0005\tu\u0004\"\u0003Bp\u0005+$\t\u0019\u0001Bq\u0003\u0005A\b\u0003B\u0007w\u0005#C\u0001B!:\u0003~\u0011\u0005!q]\u0001\fo&$\b\u000eU1si&\fG\u000e\u0006\u0003\u0003\\\n%\bb\u0002Bp\u0005G\u0004\ra\u0010\u0005\t\u0005[\u0014i\b\"\u0003\u0003p\u0006\tB/Y6f\t\u0016\u001cHO];di&4X\r\\=\u0015\t\tE(1\u001f\t\u0005\u0011\t5\u0005\u0007C\u0004\u0003\u001e\n-\b\u0019\u0001)\t\u0011\t](Q\u0010C\u0005\u0005s\fq\u0001]1eI&tw\r\u0006\u0003\u0003|\u000e\u001d\u0001C\u0002B\u007f\u0007\u0007\u0011\t*\u0004\u0002\u0003\u0000*\u00191\u0011\u0001\u0002\u0002\u0013%lW.\u001e;bE2,\u0017\u0002BB\u0003\u0005\u007f\u0014A\u0001T5ti\"9!q\u001cB{\u0001\u0004\u0001\u0006\u0002CB\u0006\u0005{\"Ia!\u0004\u0002\u0007\u001d\f\u0007/F\u0001Q\u0011!\u0019\tB! \u0005\n\rM\u0011AA4p)\ry4Q\u0003\u0005\b\u0007/\u0019y\u00011\u0001Q\u0003\u0015\u0019w.\u001e8u\u0011!\u0019YB! \u0005\n\ru\u0011\u0001\u00024jY2$\u0012a\u0010\u0005\u0007{\tuD\u0011\u0001 \t\u000f\r\u0013i\b\"\u0001\u0004$Q\u00111Q\u0005\t\u0007\u0007O\u0019iC!%\u000f\u00075\u0019I#C\u0002\u0004,\u0011\tq\u0001]1dW\u0006<W-\u0003\u0003\u0004\u0006\r=\"bAB\u0016\t!911\u0007\u0016\u0005\u0002\rU\u0012aB4s_V\u0004X\rZ\u000b\u0005\u0007o\u0019i\u0004\u0006\u0003\u0004:\r}\u0002C\u0002BU\u0005{\u001aY\u0004E\u0002 \u0007{!a!ZB\u0019\u0005\u0004\u0011\bb\u0002BO\u0007c\u0001\r\u0001\u0015\u0005\b\u0007\u0007RC\u0011AB#\u0003\u001d\u0019H.\u001b3j]\u001e,Baa\u0012\u0004NQ11\u0011JB(\u0007#\u0002bA!+\u0003~\r-\u0003cA\u0010\u0004N\u00111Qm!\u0011C\u0002IDqA!(\u0004B\u0001\u0007\u0001\u000bC\u0005\u0003\"\u000e\u0005\u0003\u0013!a\u0001!\"91Q\u000b\u0016\u0005\u0002\r5\u0011A\u00027f]\u001e$\b\u000eC\u0004\u0004Z)\"\taa\u0017\u0002\u0013\u0011,\b\u000f\\5dCR,WCAAW\u0011\u001d\u0019yF\u000bC\u0001\u0007C\nQ\u0001]1uG\",Baa\u0019\u0004jQA1QMB6\u0007[\u001a\t\b\u0005\u0003\tU\r\u001d\u0004cA\u0010\u0004j\u00111Qm!\u0018C\u0002IDaaWB/\u0001\u0004\u0001\u0006\u0002CB8\u0007;\u0002\ra!\u001a\u0002\u0015A\fGo\u00195FY\u0016l7\u000fC\u0004\u0004t\ru\u0003\u0019\u0001)\u0002\u0011I,\u0007\u000f\\1dK\u0012Dqaa\u001e+\t\u0003\u0019I(A\u0006d_BLHk\\!se\u0006LX\u0003BB>\u0007\u0013#r!NB?\u0007\u0017\u001by\t\u0003\u0005\u0004\u0000\rU\u0004\u0019ABA\u0003\tA8\u000fE\u0003\u000e\u0007\u0007\u001b9)C\u0002\u0004\u0006\u0012\u0011Q!\u0011:sCf\u00042aHBE\t\u0019)7Q\u000fb\u0001e\"91QRB;\u0001\u0004\u0001\u0016!B:uCJ$\bbBAv\u0007k\u0002\r\u0001\u0015\u0005\b\u0007'SC\u0011ABK\u00031\u0019\u0018-\\3FY\u0016lWM\u001c;t)\ry4q\u0013\u0005\bi\u000eE\u0005\u0019ABMa\u0011\u0019Yja(\u0011\t!Q3Q\u0014\t\u0004?\r}EaCBQ\u0007/\u000b\t\u0011!A\u0003\u0002\t\u00121a\u0018\u00132\u0011\u001d\u0019)K\u000bC\u0001\u0007O\u000bQ\u0002^8Ue\u00064XM]:bE2,WCABU!\u0011A11\u0016\u0019\n\u0007\r5&AA\u0006Ue\u00064XM]:bE2,\u0007BBBYU\u0011\u0005!(\u0001\u0006u_&#XM]1u_JDqa!.+\t\u0003\u00199,\u0001\u0005u_N#(/Z1n+\t\u0019I\fE\u0003\u0003~\u000em\u0006'\u0003\u0003\u0004>\n}(AB*ue\u0016\fW\u000eC\u0004\u0004B*\"\tea1\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"a!2\u0011\t\r\u001d7\u0011[\u0007\u0003\u0007\u0013TAaa3\u0004N\u0006!A.\u00198h\u0015\t\u0019y-\u0001\u0003kCZ\f\u0017\u0002BBj\u0007\u0013\u0014aa\u0015;sS:<\u0007\"CBlUE\u0005I\u0011ABm\u0003E\u0019H.\u001b3j]\u001e$C-\u001a4bk2$HEM\u000b\u0005\u00077\u001ci/\u0006\u0002\u0004^*\u001a\u0001ka8,\u0005\r\u0005\b\u0003BBr\u0007Sl!a!:\u000b\t\r\u001d\u0018qL\u0001\nk:\u001c\u0007.Z2lK\u0012LAaa;\u0004f\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\r\u0015\u001c)N1\u0001s\u0011%\u0019\t0\u0003b\u0001\n\u0003\u0019\u00190A\u0003f[B$\u00180\u0006\u0002\u0004vB\u0019\u0001BK\u0012\t\u0011\re\u0018\u0002)A\u0005\u0007k\fa!Z7qif\u0004\u0003bBB\u007f\u0013\u0011\u00051q`\u0001\u0007g&tw\r\\3\u0016\t\u0011\u0005Aq\u0001\u000b\u0005\t\u0007!I\u0001\u0005\u0003\tU\u0011\u0015\u0001cA\u0010\u0005\b\u00111\u0011ea?C\u0002\tB\u0001\"a<\u0004|\u0002\u0007AQ\u0001\u0005\b\t\u001bIA\u0011\u0001C\b\u0003\u0015\t\u0007\u000f\u001d7z+\u0011!\t\u0002b\u0006\u0015\t\u0011MA\u0011\u0004\t\u0005\u0011)\")\u0002E\u0002 \t/!a!\tC\u0006\u0005\u0004\u0011\u0003\u0002\u0003C\u000e\t\u0017\u0001\r\u0001\"\b\u0002\u000b\u0015dW-\\:\u0011\u000b5!y\u0002\"\u0006\n\u0007\u0011\u0005BA\u0001\u0006=e\u0016\u0004X-\u0019;fIzBqaa\u0007\n\t\u0003!)#\u0006\u0003\u0005(\u0011=B\u0003\u0002C\u0015\tk!B\u0001b\u000b\u00052A!\u0001B\u000bC\u0017!\ryBq\u0006\u0003\u0007C\u0011\r\"\u0019\u0001\u0012\t\u0013\u0005=H1\u0005CA\u0002\u0011M\u0002\u0003B\u0007w\t[Aq!a;\u0005$\u0001\u0007\u0001\u000bC\u0004\u0005:%!\t\u0001b\u000f\u0002\u0011Q\f'-\u001e7bi\u0016,B\u0001\"\u0010\u0005FQ!Aq\bC&)\u0011!\t\u0005b\u0012\u0011\t!QC1\t\t\u0004?\u0011\u0015CAB\u0011\u00058\t\u0007!\u0005C\u0004h\to\u0001\r\u0001\"\u0013\u0011\u000b5I\u0007\u000bb\u0011\t\u000f\u00115Cq\u0007a\u0001!\u0006\u0019QM\u001c3\t\u000f\u0011E\u0013\u0002\"\u0001\u0005T\u0005)!/\u00198hKR1AQ\u000bC,\t3\u00022\u0001\u0003\u0016Q\u0011\u001d\u0019i\tb\u0014A\u0002ACq\u0001\"\u0014\u0005P\u0001\u0007\u0001\u000bC\u0004\u0005R%!\t\u0001\"\u0018\u0015\u0011\u0011UCq\fC1\tGBqa!$\u0005\\\u0001\u0007\u0001\u000bC\u0004\u0005N\u0011m\u0003\u0019\u0001)\t\u000f\t\u0005F1\fa\u0001!\"9AqM\u0005\u0005\u0002\u0011%\u0014aB5uKJ\fG/Z\u000b\u0005\tW\"\u0019\b\u0006\u0003\u0005n\u0011mD\u0003\u0002C8\to\u0002B\u0001\u0003\u0016\u0005rA\u0019q\u0004b\u001d\u0005\u000f\u0011UDQ\rb\u0001E\t\tA\u000bC\u0004h\tK\u0002\r\u0001\"\u001f\u0011\r5IG\u0011\u000fC9\u0011!\u0019i\t\"\u001aA\u0002\u0011E\u0004BB.\n\t\u0003!y\b\u0006\u0003\u0005V\u0011\u0005\u0005bBBG\t{\u0002\r\u0001\u0015\u0005\u00077&!\t\u0001\"\"\u0015\r\u0011UCq\u0011CE\u0011\u001d\u0019i\tb!A\u0002ACqA!)\u0005\u0004\u0002\u0007\u0001\u000bC\u0004\u0005\u000e&!\t\u0001b$\u0002\u0017\r|g\u000e^5ok\u0006dG._\u000b\u0005\t##9\n\u0006\u0003\u0005\u0014\u0012e\u0005\u0003\u0002\u0005+\t+\u00032a\bCL\t\u0019\tC1\u0012b\u0001E!I\u0011q\u001eCF\t\u0003\u0007A1\u0014\t\u0005\u001bY$)JB\u0004\u0005 &\u0011A\u0001\")\u0003\u001d\r{gnY1u\u0013R,'/\u0019;peV!A1\u0015CU'\u0015!i\n\u0004CS!\u0011A!\u0006b*\u0011\u0007}!I\u000bB\u0004\"\t;#)\u0019\u0001\u0012\t\u0017\u00115FQ\u0014B\u0001B\u0003&AQU\u0001\bGV\u0014(/\u001a8u\u0011-!\t\f\"(\u0003\u0002\u0003\u0006I\u0001b-\u0002\u000f%t\u0017\u000e^5bYB11q\u0005C[\tsKA\u0001b.\u00040\t1a+Z2u_J\u0004R!\u0004Bi\tKCq!\u0005CO\t\u0003!i\f\u0006\u0004\u0005@\u0012\rGQ\u0019\t\u0007\t\u0003$i\nb*\u000e\u0003%A\u0001\u0002\",\u0005<\u0002\u0007AQ\u0015\u0005\t\tc#Y\f1\u0001\u00054\"9\u0011\u0003\"(\u0005\u0002\u0011%G\u0003\u0002C`\t\u0017D\u0001\u0002\"-\u0005H\u0002\u0007A1\u0017\u0015\t\t\u000f$y\r\"6\u0005vB\u0019Q\u0002\"5\n\u0007\u0011MGA\u0001\u0006eKB\u0014XmY1uK\u0012\f\u0014b\tCl\tG$Y\u000f\":\u0011\t\u0011eGq\u001c\b\u0004\u001b\u0011m\u0017b\u0001Co\t\u00051\u0001K]3eK\u001aLAaa5\u0005b*\u0019AQ\u001c\u0003\n\t\u0011\u0015Hq]\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u0019\u000b\u0007\u0011%H!\u0001\u0006eKB\u0014XmY1uK\u0012\f\u0014b\tCw\t_$\t\u0010\";\u000f\u00075!y/C\u0002\u0005j\u0012\tTAI\u0007\u0005\tg\u0014Qa]2bY\u0006\f\u0014b\tCl\to$Y\u0010\"?\n\t\u0011eHq]\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a2\u0013\r\"i\u000fb<\u0005~\u0012%\u0018'\u0002\u0012\u000e\t\u0011M\b\"CC\u0001\t;\u0003\u000b\u0015\u0002CZ\u0003\u0015\tX/Z;f\u0011!))\u0001\"(!B\u0013y\u0014!F2veJ,g\u000e\u001e%bg:+\u0007\u0010^\"iK\u000e\\W\r\u001a\u0005\n\u000b\u0013!i\n)C\u0005\u0007;\tq!\u00193wC:\u001cW\r\u000b\u0003\u0006\b\u00155\u0001\u0003BA/\u000b\u001fIA!\"\u0005\u0002`\t9A/Y5me\u0016\u001c\u0007BB\u001f\u0005\u001e\u0012\u0005a\bC\u0004D\t;#\t!b\u0006\u0015\u0005\u0011\u001d\u0006b\u00027\u0005\u001e\u0012\u0005S1D\u000b\u0005\u000b;)\u0019\u0003\u0006\u0003\u0006 \u0015\u001d\u0002\u0003\u0002\u0005+\u000bC\u00012aHC\u0012\t\u001d)W\u0011\u0004b\u0001\u000bK\t2\u0001b*'\u0011!!X\u0011\u0004CA\u0002\u0015%\u0002\u0003B\u0007w\u000bW\u0001B\u0001C=\u0006\"\u00199QqF\u0005\u0003\t\u0015E\"\u0001\u0004&pS:LE/\u001a:bi>\u0014X\u0003BC\u001a\u000bs\u0019R!\"\f\r\u000bk\u0001B\u0001\u0003\u0016\u00068A\u0019q$\"\u000f\u0005\u000f\u0005*i\u0003\"b\u0001E!YQQHC\u0017\u0005\u0003\u0005\u000b\u0011BC\u001b\u0003\ra\u0007n\u001d\u0005\u000bi\u00165\"\u0011!S\u0001\n\u0015\u0005\u0003\u0003B\u0007w\u000b\u0007\u0002B\u0001C=\u00068!9\u0011#\"\f\u0005\u0002\u0015\u001dCCBC%\u000b\u0017*i\u0005\u0005\u0004\u0005B\u00165Rq\u0007\u0005\t\u000b{))\u00051\u0001\u00066!AA/\"\u0012\u0005\u0002\u0004)\t\u0005\u0003\u0005\u0006R\u00155\u0002\u0015)\u0003Q\u0003\u0015\u0019H/\u0019;f\u0011-))&\"\f\t\u0006\u0004&I!b\u0016\u0002\u0007ID7/\u0006\u0002\u00066!YQ1LC\u0017\u0011\u0003\u0005\u000b\u0015BC\u001b\u0003\u0011\u0011\bn\u001d\u0011\t\ru*i\u0003\"\u0001?\u0011\u001d\u0019UQ\u0006C\u0001\u000bC\"\"!b\u000e\t\u000f1,i\u0003\"\u0011\u0006fU!QqMC7)\u0011)I'\"\u001d\u0011\r\u0011\u0005GQTC6!\ryRQ\u000e\u0003\bK\u0016\r$\u0019AC8#\r)9D\n\u0005\ti\u0016\rD\u00111\u0001\u0006tA!QB^C;!\u0011A\u00110b\u001b")
public interface Iterator<A>
extends TraversableOnce<A> {
    @Override
    public Iterator<A> seq();

    public boolean hasNext();

    public A next();

    @Override
    public boolean isEmpty();

    @Override
    public boolean isTraversableAgain();

    @Override
    public boolean hasDefiniteSize();

    public Iterator<A> take(int var1);

    public Iterator<A> drop(int var1);

    public Iterator<A> slice(int var1, int var2);

    public <B> Iterator<B> map(Function1<A, B> var1);

    public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> var1);

    public <B> Iterator<B> flatMap(Function1<A, GenTraversableOnce<B>> var1);

    public Iterator<A> filter(Function1<A, Object> var1);

    public <B> boolean corresponds(GenTraversableOnce<B> var1, Function2<A, B, Object> var2);

    public Iterator<A> withFilter(Function1<A, Object> var1);

    public Iterator<A> filterNot(Function1<A, Object> var1);

    public <B> Iterator<B> collect(PartialFunction<A, B> var1);

    public <B> Iterator<B> scanLeft(B var1, Function2<B, A, B> var2);

    public <B> Iterator<B> scanRight(B var1, Function2<A, B, B> var2);

    public Iterator<A> takeWhile(Function1<A, Object> var1);

    public Tuple2<Iterator<A>, Iterator<A>> partition(Function1<A, Object> var1);

    public Tuple2<Iterator<A>, Iterator<A>> span(Function1<A, Object> var1);

    public Iterator<A> dropWhile(Function1<A, Object> var1);

    public <B> Iterator<Tuple2<A, B>> zip(Iterator<B> var1);

    public <A1> Iterator<A1> padTo(int var1, A1 var2);

    public Iterator<Tuple2<A, Object>> zipWithIndex();

    public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> var1, A1 var2, B1 var3);

    @Override
    public <U> void foreach(Function1<A, U> var1);

    @Override
    public boolean forall(Function1<A, Object> var1);

    @Override
    public boolean exists(Function1<A, Object> var1);

    public boolean contains(Object var1);

    @Override
    public Option<A> find(Function1<A, Object> var1);

    public int indexWhere(Function1<A, Object> var1);

    public <B> int indexOf(B var1);

    public BufferedIterator<A> buffered();

    public <B> GroupedIterator<B> grouped(int var1);

    public <B> GroupedIterator<B> sliding(int var1, int var2);

    public <B> int sliding$default$2();

    public int length();

    public Tuple2<Iterator<A>, Iterator<A>> duplicate();

    public <B> Iterator<B> patch(int var1, Iterator<B> var2, int var3);

    @Override
    public <B> void copyToArray(Object var1, int var2, int var3);

    public boolean sameElements(Iterator<?> var1);

    @Override
    public Traversable<A> toTraversable();

    @Override
    public Iterator<A> toIterator();

    @Override
    public Stream<A> toStream();

    public String toString();

    public static final class JoinIterator<A>
    implements Iterator<A> {
        private final Iterator<A> lhs;
        private final Function0<GenTraversableOnce<A>> that;
        private int state;
        private Iterator<A> rhs;
        private volatile boolean bitmap$0;

        private Iterator rhs$lzycompute() {
            JoinIterator joinIterator = this;
            synchronized (joinIterator) {
                if (!this.bitmap$0) {
                    this.rhs = this.that.apply().toIterator();
                    this.bitmap$0 = true;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                this.that = null;
                return this.rhs;
            }
        }

        @Override
        public Iterator<A> seq() {
            return Iterator$class.seq(this);
        }

        @Override
        public boolean isEmpty() {
            return Iterator$class.isEmpty(this);
        }

        @Override
        public boolean isTraversableAgain() {
            return Iterator$class.isTraversableAgain(this);
        }

        @Override
        public boolean hasDefiniteSize() {
            return Iterator$class.hasDefiniteSize(this);
        }

        @Override
        public Iterator<A> take(int n) {
            return Iterator$class.take(this, n);
        }

        @Override
        public Iterator<A> drop(int n) {
            return Iterator$class.drop(this, n);
        }

        @Override
        public Iterator<A> slice(int from2, int until2) {
            return Iterator$class.slice(this, from2, until2);
        }

        @Override
        public <B> Iterator<B> map(Function1<A, B> f) {
            return Iterator$class.map(this, f);
        }

        @Override
        public <B> Iterator<B> flatMap(Function1<A, GenTraversableOnce<B>> f) {
            return Iterator$class.flatMap(this, f);
        }

        @Override
        public Iterator<A> filter(Function1<A, Object> p) {
            return Iterator$class.filter(this, p);
        }

        @Override
        public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<A, B, Object> p) {
            return Iterator$class.corresponds(this, that, p);
        }

        @Override
        public Iterator<A> withFilter(Function1<A, Object> p) {
            return Iterator$class.withFilter(this, p);
        }

        @Override
        public Iterator<A> filterNot(Function1<A, Object> p) {
            return Iterator$class.filterNot(this, p);
        }

        @Override
        public <B> Iterator<B> collect(PartialFunction<A, B> pf) {
            return Iterator$class.collect(this, pf);
        }

        @Override
        public <B> Iterator<B> scanLeft(B z, Function2<B, A, B> op) {
            return Iterator$class.scanLeft(this, z, op);
        }

        @Override
        public <B> Iterator<B> scanRight(B z, Function2<A, B, B> op) {
            return Iterator$class.scanRight(this, z, op);
        }

        @Override
        public Iterator<A> takeWhile(Function1<A, Object> p) {
            return Iterator$class.takeWhile(this, p);
        }

        @Override
        public Tuple2<Iterator<A>, Iterator<A>> partition(Function1<A, Object> p) {
            return Iterator$class.partition(this, p);
        }

        @Override
        public Tuple2<Iterator<A>, Iterator<A>> span(Function1<A, Object> p) {
            return Iterator$class.span(this, p);
        }

        @Override
        public Iterator<A> dropWhile(Function1<A, Object> p) {
            return Iterator$class.dropWhile(this, p);
        }

        @Override
        public <B> Iterator<Tuple2<A, B>> zip(Iterator<B> that) {
            return Iterator$class.zip(this, that);
        }

        @Override
        public <A1> Iterator<A1> padTo(int len, A1 elem) {
            return Iterator$class.padTo(this, len, elem);
        }

        @Override
        public Iterator<Tuple2<A, Object>> zipWithIndex() {
            return Iterator$class.zipWithIndex(this);
        }

        @Override
        public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
            return Iterator$class.zipAll(this, that, thisElem, thatElem);
        }

        @Override
        public <U> void foreach(Function1<A, U> f) {
            Iterator$class.foreach(this, f);
        }

        @Override
        public boolean forall(Function1<A, Object> p) {
            return Iterator$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<A, Object> p) {
            return Iterator$class.exists(this, p);
        }

        @Override
        public boolean contains(Object elem) {
            return Iterator$class.contains(this, elem);
        }

        @Override
        public Option<A> find(Function1<A, Object> p) {
            return Iterator$class.find(this, p);
        }

        @Override
        public int indexWhere(Function1<A, Object> p) {
            return Iterator$class.indexWhere(this, p);
        }

        @Override
        public <B> int indexOf(B elem) {
            return Iterator$class.indexOf(this, elem);
        }

        @Override
        public BufferedIterator<A> buffered() {
            return Iterator$class.buffered(this);
        }

        @Override
        public <B> GroupedIterator<B> grouped(int size2) {
            return Iterator$class.grouped(this, size2);
        }

        @Override
        public <B> GroupedIterator<B> sliding(int size2, int step) {
            return Iterator$class.sliding(this, size2, step);
        }

        @Override
        public int length() {
            return Iterator$class.length(this);
        }

        @Override
        public Tuple2<Iterator<A>, Iterator<A>> duplicate() {
            return Iterator$class.duplicate(this);
        }

        @Override
        public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
            return Iterator$class.patch(this, from2, patchElems, replaced);
        }

        @Override
        public <B> void copyToArray(Object xs, int start, int len) {
            Iterator$class.copyToArray(this, xs, start, len);
        }

        @Override
        public boolean sameElements(Iterator<?> that) {
            return Iterator$class.sameElements(this, that);
        }

        @Override
        public Traversable<A> toTraversable() {
            return Iterator$class.toTraversable(this);
        }

        @Override
        public Iterator<A> toIterator() {
            return Iterator$class.toIterator(this);
        }

        @Override
        public Stream<A> toStream() {
            return Iterator$class.toStream(this);
        }

        @Override
        public String toString() {
            return Iterator$class.toString(this);
        }

        @Override
        public <B> int sliding$default$2() {
            return Iterator$class.sliding$default$2(this);
        }

        @Override
        public List<A> reversed() {
            return TraversableOnce$class.reversed(this);
        }

        @Override
        public int size() {
            return TraversableOnce$class.size(this);
        }

        @Override
        public boolean nonEmpty() {
            return TraversableOnce$class.nonEmpty(this);
        }

        @Override
        public int count(Function1<A, Object> p) {
            return TraversableOnce$class.count(this, p);
        }

        @Override
        public <B> Option<B> collectFirst(PartialFunction<A, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, A, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<A, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, A, B> op) {
            return (B)TraversableOnce$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B foldRight(B z, Function2<A, B, B> op) {
            return (B)TraversableOnce$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, A, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> B reduceRight(Function2<A, B, B> op) {
            return (B)TraversableOnce$class.reduceRight(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, A, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<A, B, B> op) {
            return TraversableOnce$class.reduceRightOption(this, op);
        }

        @Override
        public <A1> A1 reduce(Function2<A1, A1, A1> op) {
            return (A1)TraversableOnce$class.reduce(this, op);
        }

        @Override
        public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
            return TraversableOnce$class.reduceOption(this, op);
        }

        @Override
        public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
            return (A1)TraversableOnce$class.fold(this, z, op);
        }

        @Override
        public <B> B aggregate(Function0<B> z, Function2<B, A, B> seqop, Function2<B, B, B> combop) {
            return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
        }

        @Override
        public <B> B sum(Numeric<B> num) {
            return (B)TraversableOnce$class.sum(this, num);
        }

        @Override
        public <B> B product(Numeric<B> num) {
            return (B)TraversableOnce$class.product(this, num);
        }

        @Override
        public <B> A min(Ordering<B> cmp) {
            return (A)TraversableOnce$class.min(this, cmp);
        }

        @Override
        public <B> A max(Ordering<B> cmp) {
            return (A)TraversableOnce$class.max(this, cmp);
        }

        @Override
        public <B> A maxBy(Function1<A, B> f, Ordering<B> cmp) {
            return (A)TraversableOnce$class.maxBy(this, f, cmp);
        }

        @Override
        public <B> A minBy(Function1<A, B> f, Ordering<B> cmp) {
            return (A)TraversableOnce$class.minBy(this, f, cmp);
        }

        @Override
        public <B> void copyToBuffer(Buffer<B> dest) {
            TraversableOnce$class.copyToBuffer(this, dest);
        }

        @Override
        public <B> void copyToArray(Object xs, int start) {
            TraversableOnce$class.copyToArray(this, xs, start);
        }

        @Override
        public <B> void copyToArray(Object xs) {
            TraversableOnce$class.copyToArray(this, xs);
        }

        @Override
        public <B> Object toArray(ClassTag<B> evidence$1) {
            return TraversableOnce$class.toArray(this, evidence$1);
        }

        @Override
        public List<A> toList() {
            return TraversableOnce$class.toList(this);
        }

        @Override
        public Iterable<A> toIterable() {
            return TraversableOnce$class.toIterable(this);
        }

        @Override
        public Seq<A> toSeq() {
            return TraversableOnce$class.toSeq(this);
        }

        @Override
        public IndexedSeq<A> toIndexedSeq() {
            return TraversableOnce$class.toIndexedSeq(this);
        }

        @Override
        public <B> Buffer<B> toBuffer() {
            return TraversableOnce$class.toBuffer(this);
        }

        @Override
        public <B> Set<B> toSet() {
            return TraversableOnce$class.toSet(this);
        }

        @Override
        public Vector<A> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, A, Col> cbf) {
            return (Col)TraversableOnce$class.to(this, cbf);
        }

        @Override
        public <T, U> Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> ev) {
            return TraversableOnce$class.toMap(this, ev);
        }

        @Override
        public String mkString(String start, String sep, String end) {
            return TraversableOnce$class.mkString(this, start, sep, end);
        }

        @Override
        public String mkString(String sep) {
            return TraversableOnce$class.mkString(this, sep);
        }

        @Override
        public String mkString() {
            return TraversableOnce$class.mkString(this);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
            return TraversableOnce$class.addString(this, b, start, sep, end);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String sep) {
            return TraversableOnce$class.addString(this, b, sep);
        }

        @Override
        public StringBuilder addString(StringBuilder b) {
            return TraversableOnce$class.addString(this, b);
        }

        private Iterator<A> rhs() {
            return this.bitmap$0 ? this.rhs : this.rhs$lzycompute();
        }

        @Override
        public boolean hasNext() {
            boolean bl;
            int n = this.state;
            switch (n) {
                default: {
                    bl = this.rhs().hasNext();
                    break;
                }
                case 1: {
                    bl = true;
                    break;
                }
                case 0: {
                    if (this.lhs.hasNext()) {
                        this.state = 1;
                        bl = true;
                        break;
                    }
                    this.state = 2;
                    bl = this.rhs().hasNext();
                }
            }
            return bl;
        }

        @Override
        public A next() {
            A a;
            int n = this.state;
            switch (n) {
                default: {
                    a = this.rhs().next();
                    break;
                }
                case 1: {
                    this.state = 0;
                    a = this.lhs.next();
                    break;
                }
                case 0: {
                    if (this.lhs.hasNext()) {
                        a = this.lhs.next();
                        break;
                    }
                    this.state = 2;
                    a = this.rhs().next();
                }
            }
            return a;
        }

        @Override
        public <B> ConcatIterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
            return new ConcatIterator(this, (Vector)package$.MODULE$.Vector().apply(Predef$.MODULE$.wrapRefArray((Object[])new Function0[]{new Serializable(this, that){
                public static final long serialVersionUID = 0L;
                private final Function0 that$2;

                public final Iterator<B> apply() {
                    return ((GenTraversableOnce)this.that$2.apply()).toIterator();
                }
                {
                    this.that$2 = that$2;
                }
            }})));
        }

        public JoinIterator(Iterator<A> lhs, Function0<GenTraversableOnce<A>> that) {
            this.lhs = lhs;
            this.that = that;
            TraversableOnce$class.$init$(this);
            Iterator$class.$init$(this);
            this.state = 0;
        }
    }

    public static final class ConcatIterator<A>
    implements Iterator<A> {
        private Iterator<A> current;
        private Vector<Function0<Iterator<A>>> queue;
        private boolean currentHasNextChecked;

        @Override
        public Iterator<A> seq() {
            return Iterator$class.seq(this);
        }

        @Override
        public boolean isEmpty() {
            return Iterator$class.isEmpty(this);
        }

        @Override
        public boolean isTraversableAgain() {
            return Iterator$class.isTraversableAgain(this);
        }

        @Override
        public boolean hasDefiniteSize() {
            return Iterator$class.hasDefiniteSize(this);
        }

        @Override
        public Iterator<A> take(int n) {
            return Iterator$class.take(this, n);
        }

        @Override
        public Iterator<A> drop(int n) {
            return Iterator$class.drop(this, n);
        }

        @Override
        public Iterator<A> slice(int from2, int until2) {
            return Iterator$class.slice(this, from2, until2);
        }

        @Override
        public <B> Iterator<B> map(Function1<A, B> f) {
            return Iterator$class.map(this, f);
        }

        @Override
        public <B> Iterator<B> flatMap(Function1<A, GenTraversableOnce<B>> f) {
            return Iterator$class.flatMap(this, f);
        }

        @Override
        public Iterator<A> filter(Function1<A, Object> p) {
            return Iterator$class.filter(this, p);
        }

        @Override
        public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<A, B, Object> p) {
            return Iterator$class.corresponds(this, that, p);
        }

        @Override
        public Iterator<A> withFilter(Function1<A, Object> p) {
            return Iterator$class.withFilter(this, p);
        }

        @Override
        public Iterator<A> filterNot(Function1<A, Object> p) {
            return Iterator$class.filterNot(this, p);
        }

        @Override
        public <B> Iterator<B> collect(PartialFunction<A, B> pf) {
            return Iterator$class.collect(this, pf);
        }

        @Override
        public <B> Iterator<B> scanLeft(B z, Function2<B, A, B> op) {
            return Iterator$class.scanLeft(this, z, op);
        }

        @Override
        public <B> Iterator<B> scanRight(B z, Function2<A, B, B> op) {
            return Iterator$class.scanRight(this, z, op);
        }

        @Override
        public Iterator<A> takeWhile(Function1<A, Object> p) {
            return Iterator$class.takeWhile(this, p);
        }

        @Override
        public Tuple2<Iterator<A>, Iterator<A>> partition(Function1<A, Object> p) {
            return Iterator$class.partition(this, p);
        }

        @Override
        public Tuple2<Iterator<A>, Iterator<A>> span(Function1<A, Object> p) {
            return Iterator$class.span(this, p);
        }

        @Override
        public Iterator<A> dropWhile(Function1<A, Object> p) {
            return Iterator$class.dropWhile(this, p);
        }

        @Override
        public <B> Iterator<Tuple2<A, B>> zip(Iterator<B> that) {
            return Iterator$class.zip(this, that);
        }

        @Override
        public <A1> Iterator<A1> padTo(int len, A1 elem) {
            return Iterator$class.padTo(this, len, elem);
        }

        @Override
        public Iterator<Tuple2<A, Object>> zipWithIndex() {
            return Iterator$class.zipWithIndex(this);
        }

        @Override
        public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
            return Iterator$class.zipAll(this, that, thisElem, thatElem);
        }

        @Override
        public <U> void foreach(Function1<A, U> f) {
            Iterator$class.foreach(this, f);
        }

        @Override
        public boolean forall(Function1<A, Object> p) {
            return Iterator$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<A, Object> p) {
            return Iterator$class.exists(this, p);
        }

        @Override
        public boolean contains(Object elem) {
            return Iterator$class.contains(this, elem);
        }

        @Override
        public Option<A> find(Function1<A, Object> p) {
            return Iterator$class.find(this, p);
        }

        @Override
        public int indexWhere(Function1<A, Object> p) {
            return Iterator$class.indexWhere(this, p);
        }

        @Override
        public <B> int indexOf(B elem) {
            return Iterator$class.indexOf(this, elem);
        }

        @Override
        public BufferedIterator<A> buffered() {
            return Iterator$class.buffered(this);
        }

        @Override
        public <B> GroupedIterator<B> grouped(int size2) {
            return Iterator$class.grouped(this, size2);
        }

        @Override
        public <B> GroupedIterator<B> sliding(int size2, int step) {
            return Iterator$class.sliding(this, size2, step);
        }

        @Override
        public int length() {
            return Iterator$class.length(this);
        }

        @Override
        public Tuple2<Iterator<A>, Iterator<A>> duplicate() {
            return Iterator$class.duplicate(this);
        }

        @Override
        public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
            return Iterator$class.patch(this, from2, patchElems, replaced);
        }

        @Override
        public <B> void copyToArray(Object xs, int start, int len) {
            Iterator$class.copyToArray(this, xs, start, len);
        }

        @Override
        public boolean sameElements(Iterator<?> that) {
            return Iterator$class.sameElements(this, that);
        }

        @Override
        public Traversable<A> toTraversable() {
            return Iterator$class.toTraversable(this);
        }

        @Override
        public Iterator<A> toIterator() {
            return Iterator$class.toIterator(this);
        }

        @Override
        public Stream<A> toStream() {
            return Iterator$class.toStream(this);
        }

        @Override
        public String toString() {
            return Iterator$class.toString(this);
        }

        @Override
        public <B> int sliding$default$2() {
            return Iterator$class.sliding$default$2(this);
        }

        @Override
        public List<A> reversed() {
            return TraversableOnce$class.reversed(this);
        }

        @Override
        public int size() {
            return TraversableOnce$class.size(this);
        }

        @Override
        public boolean nonEmpty() {
            return TraversableOnce$class.nonEmpty(this);
        }

        @Override
        public int count(Function1<A, Object> p) {
            return TraversableOnce$class.count(this, p);
        }

        @Override
        public <B> Option<B> collectFirst(PartialFunction<A, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, A, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<A, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, A, B> op) {
            return (B)TraversableOnce$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B foldRight(B z, Function2<A, B, B> op) {
            return (B)TraversableOnce$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, A, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> B reduceRight(Function2<A, B, B> op) {
            return (B)TraversableOnce$class.reduceRight(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, A, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<A, B, B> op) {
            return TraversableOnce$class.reduceRightOption(this, op);
        }

        @Override
        public <A1> A1 reduce(Function2<A1, A1, A1> op) {
            return (A1)TraversableOnce$class.reduce(this, op);
        }

        @Override
        public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
            return TraversableOnce$class.reduceOption(this, op);
        }

        @Override
        public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
            return (A1)TraversableOnce$class.fold(this, z, op);
        }

        @Override
        public <B> B aggregate(Function0<B> z, Function2<B, A, B> seqop, Function2<B, B, B> combop) {
            return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
        }

        @Override
        public <B> B sum(Numeric<B> num) {
            return (B)TraversableOnce$class.sum(this, num);
        }

        @Override
        public <B> B product(Numeric<B> num) {
            return (B)TraversableOnce$class.product(this, num);
        }

        @Override
        public <B> A min(Ordering<B> cmp) {
            return (A)TraversableOnce$class.min(this, cmp);
        }

        @Override
        public <B> A max(Ordering<B> cmp) {
            return (A)TraversableOnce$class.max(this, cmp);
        }

        @Override
        public <B> A maxBy(Function1<A, B> f, Ordering<B> cmp) {
            return (A)TraversableOnce$class.maxBy(this, f, cmp);
        }

        @Override
        public <B> A minBy(Function1<A, B> f, Ordering<B> cmp) {
            return (A)TraversableOnce$class.minBy(this, f, cmp);
        }

        @Override
        public <B> void copyToBuffer(Buffer<B> dest) {
            TraversableOnce$class.copyToBuffer(this, dest);
        }

        @Override
        public <B> void copyToArray(Object xs, int start) {
            TraversableOnce$class.copyToArray(this, xs, start);
        }

        @Override
        public <B> void copyToArray(Object xs) {
            TraversableOnce$class.copyToArray(this, xs);
        }

        @Override
        public <B> Object toArray(ClassTag<B> evidence$1) {
            return TraversableOnce$class.toArray(this, evidence$1);
        }

        @Override
        public List<A> toList() {
            return TraversableOnce$class.toList(this);
        }

        @Override
        public Iterable<A> toIterable() {
            return TraversableOnce$class.toIterable(this);
        }

        @Override
        public Seq<A> toSeq() {
            return TraversableOnce$class.toSeq(this);
        }

        @Override
        public IndexedSeq<A> toIndexedSeq() {
            return TraversableOnce$class.toIndexedSeq(this);
        }

        @Override
        public <B> Buffer<B> toBuffer() {
            return TraversableOnce$class.toBuffer(this);
        }

        @Override
        public <B> Set<B> toSet() {
            return TraversableOnce$class.toSet(this);
        }

        @Override
        public Vector<A> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, A, Col> cbf) {
            return (Col)TraversableOnce$class.to(this, cbf);
        }

        @Override
        public <T, U> Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> ev) {
            return TraversableOnce$class.toMap(this, ev);
        }

        @Override
        public String mkString(String start, String sep, String end) {
            return TraversableOnce$class.mkString(this, start, sep, end);
        }

        @Override
        public String mkString(String sep) {
            return TraversableOnce$class.mkString(this, sep);
        }

        @Override
        public String mkString() {
            return TraversableOnce$class.mkString(this);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
            return TraversableOnce$class.addString(this, b, start, sep, end);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String sep) {
            return TraversableOnce$class.addString(this, b, sep);
        }

        @Override
        public StringBuilder addString(StringBuilder b) {
            return TraversableOnce$class.addString(this, b);
        }

        private boolean advance() {
            boolean bl;
            block2: {
                do {
                    if (this.queue.isEmpty()) {
                        this.current = null;
                        bl = false;
                        break block2;
                    }
                    this.current = this.queue.head().apply();
                    this.queue = this.queue.tail();
                } while (!this.current.hasNext());
                this.currentHasNextChecked = true;
                bl = true;
            }
            return bl;
        }

        @Override
        public boolean hasNext() {
            boolean bl;
            if (this.currentHasNextChecked) {
                bl = true;
            } else if (this.current == null) {
                bl = false;
            } else if (this.current.hasNext()) {
                this.currentHasNextChecked = true;
                bl = true;
            } else {
                bl = this.advance();
            }
            return bl;
        }

        @Override
        public A next() {
            Nothing$ nothing$;
            if (this.hasNext()) {
                this.currentHasNextChecked = false;
                nothing$ = this.current.next();
            } else {
                nothing$ = Iterator$.MODULE$.empty().next();
            }
            return (A)nothing$;
        }

        @Override
        public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
            return this.current == null ? new JoinIterator<Nothing$>(Iterator$.MODULE$.empty(), that) : new ConcatIterator<A>(this.current, this.queue.$colon$plus(new Serializable(this, that){
                public static final long serialVersionUID = 0L;
                private final Function0 that$1;

                public final Iterator<B> apply() {
                    return ((GenTraversableOnce)this.that$1.apply()).toIterator();
                }
                {
                    this.that$1 = that$1;
                }
            }, Vector$.MODULE$.canBuildFrom()));
        }

        public ConcatIterator(Iterator<A> current, Vector<Function0<Iterator<A>>> initial) {
            this.current = current;
            TraversableOnce$class.$init$(this);
            Iterator$class.$init$(this);
            this.queue = initial;
            this.currentHasNextChecked = false;
        }

        public ConcatIterator(Vector<Function0<Iterator<A>>> initial) {
            this(Iterator$.MODULE$.empty(), initial);
        }
    }

    public class GroupedIterator<B>
    extends AbstractIterator<Seq<B>> {
        private final Iterator<A> self;
        public final int scala$collection$Iterator$GroupedIterator$$size;
        public final int scala$collection$Iterator$GroupedIterator$$step;
        private ArrayBuffer<B> buffer;
        private boolean filled;
        private boolean _partial;
        public Option<Function0<B>> scala$collection$Iterator$GroupedIterator$$pad;
        public final /* synthetic */ Iterator $outer;

        public GroupedIterator<B> withPadding(Function0<B> x) {
            this.scala$collection$Iterator$GroupedIterator$$pad = new Some<Function0<B>>(x);
            return this;
        }

        public GroupedIterator<B> withPartial(boolean x) {
            this._partial = x;
            if (this._partial) {
                this.scala$collection$Iterator$GroupedIterator$$pad = None$.MODULE$;
            }
            return this;
        }

        /*
         * WARNING - void declaration
         */
        private Seq<A> takeDestructively(int size2) {
            void var2_2;
            ArrayBuffer buf = new ArrayBuffer();
            for (int i = 0; i < size2 && this.self.hasNext(); ++i) {
                buf.$plus$eq(this.self.next());
            }
            return var2_2;
        }

        private List<B> padding(int x) {
            return (List)List$.MODULE$.fill(x, new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ GroupedIterator $outer;

                public final B apply() {
                    return this.$outer.scala$collection$Iterator$GroupedIterator$$pad.get().apply();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            });
        }

        private int gap() {
            int n = this.scala$collection$Iterator$GroupedIterator$$step - this.scala$collection$Iterator$GroupedIterator$$size;
            Predef$ predef$ = Predef$.MODULE$;
            return RichInt$.MODULE$.max$extension(n, 0);
        }

        private boolean go(int count2) {
            boolean bl;
            Seq xs;
            IntRef len$lzy = IntRef.zero();
            BooleanRef incomplete$lzy = BooleanRef.zero();
            VolatileByteRef bitmap$0 = VolatileByteRef.create((byte)0);
            int prevSize = this.buffer.size();
            Seq res = this.takeDestructively(count2);
            int shortBy = count2 - res.length();
            Seq seq = xs = shortBy > 0 && this.scala$collection$Iterator$GroupedIterator$$pad.isDefined() ? res.$plus$plus(this.padding(shortBy), Seq$.MODULE$.canBuildFrom()) : res;
            if (xs.isEmpty()) {
                bl = false;
            } else if (this._partial) {
                int n = this.len$2(xs, len$lzy, bitmap$0);
                Predef$ predef$ = Predef$.MODULE$;
                bl = this.deliver$1(RichInt$.MODULE$.min$extension(n, this.scala$collection$Iterator$GroupedIterator$$size), prevSize, xs, len$lzy, bitmap$0);
            } else if (this.incomplete$1(count2, xs, len$lzy, incomplete$lzy, bitmap$0)) {
                bl = false;
            } else if (this.isFirst$1(prevSize)) {
                bl = this.deliver$1(this.len$2(xs, len$lzy, bitmap$0), prevSize, xs, len$lzy, bitmap$0);
            } else {
                int n = this.scala$collection$Iterator$GroupedIterator$$step;
                Predef$ predef$ = Predef$.MODULE$;
                bl = this.deliver$1(RichInt$.MODULE$.min$extension(n, this.scala$collection$Iterator$GroupedIterator$$size), prevSize, xs, len$lzy, bitmap$0);
            }
            return bl;
        }

        private boolean fill() {
            return this.self.hasNext() ? (this.buffer.isEmpty() ? this.go(this.scala$collection$Iterator$GroupedIterator$$size) : this.go(this.scala$collection$Iterator$GroupedIterator$$step)) : false;
        }

        @Override
        public boolean hasNext() {
            return this.filled || this.fill();
        }

        @Override
        public List<B> next() {
            java.io.Serializable serializable = this.filled ? BoxedUnit.UNIT : BoxesRunTime.boxToBoolean(this.fill());
            if (this.filled) {
                this.filled = false;
                return this.buffer.toList();
            }
            throw new NoSuchElementException("next on empty iterator");
        }

        public /* synthetic */ Iterator scala$collection$Iterator$GroupedIterator$$$outer() {
            return this.$outer;
        }

        private final boolean isFirst$1(int prevSize$1) {
            return prevSize$1 == 0;
        }

        private final int len$lzycompute$1(Seq xs$1, IntRef len$lzy$1, VolatileByteRef bitmap$0$1) {
            GroupedIterator groupedIterator = this;
            synchronized (groupedIterator) {
                if ((byte)(bitmap$0$1.elem & 1) == 0) {
                    len$lzy$1.elem = xs$1.length();
                    bitmap$0$1.elem = (byte)(bitmap$0$1.elem | 1);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return len$lzy$1.elem;
            }
        }

        private final int len$2(Seq xs$1, IntRef len$lzy$1, VolatileByteRef bitmap$0$1) {
            return (byte)(bitmap$0$1.elem & 1) == 0 ? this.len$lzycompute$1(xs$1, len$lzy$1, bitmap$0$1) : len$lzy$1.elem;
        }

        private final boolean incomplete$lzycompute$1(int count$1, Seq xs$1, IntRef len$lzy$1, BooleanRef incomplete$lzy$1, VolatileByteRef bitmap$0$1) {
            GroupedIterator groupedIterator = this;
            synchronized (groupedIterator) {
                if ((byte)(bitmap$0$1.elem & 2) == 0) {
                    incomplete$lzy$1.elem = this.len$2(xs$1, len$lzy$1, bitmap$0$1) < count$1;
                    bitmap$0$1.elem = (byte)(bitmap$0$1.elem | 2);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return incomplete$lzy$1.elem;
            }
        }

        private final boolean incomplete$1(int count$1, Seq xs$1, IntRef len$lzy$1, BooleanRef incomplete$lzy$1, VolatileByteRef bitmap$0$1) {
            return (byte)(bitmap$0$1.elem & 2) == 0 ? this.incomplete$lzycompute$1(count$1, xs$1, len$lzy$1, incomplete$lzy$1, bitmap$0$1) : incomplete$lzy$1.elem;
        }

        private final boolean deliver$1(int howMany, int prevSize$1, Seq xs$1, IntRef len$lzy$1, VolatileByteRef bitmap$0$1) {
            boolean bl;
            if (howMany > 0 && (this.isFirst$1(prevSize$1) || this.len$2(xs$1, len$lzy$1, bitmap$0$1) > this.gap())) {
                int n;
                if (!this.isFirst$1(prevSize$1)) {
                    int n2 = this.scala$collection$Iterator$GroupedIterator$$step;
                    Predef$ predef$ = Predef$.MODULE$;
                    this.buffer.trimStart(RichInt$.MODULE$.min$extension(n2, prevSize$1));
                }
                if (this.isFirst$1(prevSize$1)) {
                    n = this.len$2(xs$1, len$lzy$1, bitmap$0$1);
                } else {
                    Predef$ predef$ = Predef$.MODULE$;
                    n = RichInt$.MODULE$.min$extension(howMany, this.len$2(xs$1, len$lzy$1, bitmap$0$1) - this.gap());
                }
                int available = n;
                this.buffer.$plus$plus$eq((TraversableOnce)xs$1.takeRight(available));
                this.filled = true;
                bl = true;
            } else {
                bl = false;
            }
            return bl;
        }

        public GroupedIterator(Iterator<A> $outer, Iterator<A> self, int size2, int step) {
            this.self = self;
            this.scala$collection$Iterator$GroupedIterator$$size = size2;
            this.scala$collection$Iterator$GroupedIterator$$step = step;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            boolean bl = size2 >= 1 && step >= 1;
            Predef$ predef$ = Predef$.MODULE$;
            if (bl) {
                this.buffer = (ArrayBuffer)ArrayBuffer$.MODULE$.apply(Nil$.MODULE$);
                this.filled = false;
                this._partial = true;
                this.scala$collection$Iterator$GroupedIterator$$pad = None$.MODULE$;
                return;
            }
            Predef$ predef$2 = Predef$.MODULE$;
            throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append((Object)StringLike$class.format(new StringOps("size=%d and step=%d, but both must be positive"), Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.scala$collection$Iterator$GroupedIterator$$size), BoxesRunTime.boxToInteger(this.scala$collection$Iterator$GroupedIterator$$step)}))).toString());
        }
    }
}

