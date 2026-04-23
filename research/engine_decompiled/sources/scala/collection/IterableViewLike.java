/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenSeq;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterable$class;
import scala.collection.IterableLike$class;
import scala.collection.IterableView;
import scala.collection.IterableViewLike$Transformed$class;
import scala.collection.IterableViewLike$class;
import scala.collection.Iterator;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.Traversable$class;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.TraversableView;
import scala.collection.TraversableViewLike;
import scala.collection.TraversableViewLike$Transformed$class;
import scala.collection.TraversableViewLike$class;
import scala.collection.ViewMkString$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.SliceInterval;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterable;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\rEe\u0001C\u0001\u0003!\u0003\r\ta\u0002\u0017\u0003!%#XM]1cY\u00164\u0016.Z<MS.,'BA\u0002\u0005\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u000b\u0005)1oY1mC\u000e\u0001Q\u0003\u0002\u0005\u0014U\u0001\u001ab\u0001A\u0005\u000e95\u0002\u0004C\u0001\u0006\f\u001b\u0005!\u0011B\u0001\u0007\u0005\u0005\u0019\te.\u001f*fMB\u0019abD\t\u000e\u0003\tI!\u0001\u0005\u0002\u0003\u0011%#XM]1cY\u0016\u0004\"AE\n\r\u0001\u00111A\u0003\u0001CC\u0002U\u0011\u0011!Q\t\u0003-e\u0001\"AC\f\n\u0005a!!a\u0002(pi\"Lgn\u001a\t\u0003\u0015iI!a\u0007\u0003\u0003\u0007\u0005s\u0017\u0010\u0005\u0003\u000f;Ey\u0012B\u0001\u0010\u0003\u00051IE/\u001a:bE2,G*[6f!\t\u0011\u0002\u0005\u0002\u0004\"\u0001\u0011\u0015\rA\t\u0002\u0005)\"L7/\u0005\u0002\u0017GI\u0019AE\n\u0017\u0007\t\u0015\u0002\u0001a\t\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0005\u001d\u001d\n\u0012&\u0003\u0002)\u0005\ta\u0011\n^3sC\ndWMV5foB\u0011!C\u000b\u0003\u0007W\u0001!)\u0019A\u000b\u0003\t\r{G\u000e\u001c\t\u0006\u001d\u0001\t\u0012f\b\t\u0005\u001d9\n\u0012&\u0003\u00020\u0005\tyAK]1wKJ\u001c\u0018M\u00197f-&,w\u000fE\u0003\u000fcEIs$\u0003\u00023\u0005\t\u0019BK]1wKJ\u001c\u0018M\u00197f-&,w\u000fT5lK\")A\u0007\u0001C\u0001k\u00051A%\u001b8ji\u0012\"\u0012A\u000e\t\u0003\u0015]J!\u0001\u000f\u0003\u0003\tUs\u0017\u000e\u001e\u0004\u0007u\u0001\t\tAA\u001e\u0003'\u0005\u00137\u000f\u001e:bGR$&/\u00198tM>\u0014X.\u001a3\u0016\u0005qz4#B\u001d\n{\u0005+\u0005c\u0001\b\u0010}A\u0011!c\u0010\u0003\u0007\u0001f\")\u0019A\u000b\u0003\u0003\t\u00032AQ\"?\u001b\u0005\u0001\u0011B\u0001#2\u0005-!&/\u00198tM>\u0014X.\u001a3\u0011\u0007\t3eHB\u0004E\u0001A\u0005\u0019\u0011A$\u0016\u0005![5\u0003\u0002$\n\u00132\u0003BAD\u0014KSA\u0011!c\u0013\u0003\u0007\u0001\u001a#)\u0019A\u000b\u0011\u0007\t\u001b%\nC\u00035\r\u0012\u0005Q\u0007C\u0003P\r\u001a\u0005\u0001+\u0001\u0005ji\u0016\u0014\u0018\r^8s+\u0005\t\u0006c\u0001\bS\u0015&\u00111K\u0001\u0002\t\u0013R,'/\u0019;pe\")QK\u0012C!-\u00069am\u001c:fC\u000eDWCA,_)\t1\u0004\fC\u0003Z)\u0002\u0007!,A\u0001g!\u0011Q1LS/\n\u0005q#!!\u0003$v]\u000e$\u0018n\u001c82!\t\u0011b\fB\u0003`)\n\u0007QCA\u0001V\u0011\u0015\tg\t\"\u0011c\u0003!!xn\u0015;sS:<G#A2\u0011\u0005\u0011LW\"A3\u000b\u0005\u0019<\u0017\u0001\u00027b]\u001eT\u0011\u0001[\u0001\u0005U\u00064\u0018-\u0003\u0002kK\n11\u000b\u001e:j]\u001eDQ\u0001\u001c$\u0005B5\fq![:F[B$\u00180F\u0001o!\tQq.\u0003\u0002q\t\t9!i\\8mK\u0006t\u0007\"\u0002::\t\u0003\u0019\u0018A\u0002\u001fj]&$h\bF\u0001u!\r\u0011\u0015H\u0010\u0004\bm\u0002\u0001\n1!\u0001x\u0005%)U\u000e\u001d;z-&,wo\u0005\u0003v\u0013aL\bc\u0001\"G-A\u0011!I_\u0005\u0003mFBQ\u0001N;\u0005\u0002UBQaT;\u0005\u0006u,\u0012A \t\u0004\u001dI3b!CA\u0001\u0001A\u0005\u0019\u0011AA\u0002\u0005\u00191uN]2fIV!\u0011QAA\u0007'\u0019y\u0018\"a\u0002\u0002\u0010A)!)!\u0003\u0002\f%\u0019\u0011\u0011A\u0019\u0011\u0007I\ti\u0001B\u0003A\u007f\n\u0007Q\u0003\u0005\u0003C\r\u0006-\u0001\"\u0002\u001b\u0000\t\u0003)\u0004BB(\u0000\t\u0003\t)\"\u0006\u0002\u0002\u0018A!aBUA\u0006\r%\tY\u0002\u0001I\u0001\u0004\u0003\tiB\u0001\u0004TY&\u001cW\rZ\n\b\u00033I\u0011qDA\u0012!\r\u0011\u0015\u0011E\u0005\u0004\u00037\t\u0004c\u0001\"G#!1A'!\u0007\u0005\u0002UBqaTA\r\t\u0003\tI#\u0006\u0002\u0002,A\u0019aBU\t\u0007\u0013\u0005=\u0002\u0001%A\u0002\u0002\u0005E\"AB'baB,G-\u0006\u0003\u00024\u0005m2cBA\u0017\u0013\u0005U\u0012Q\b\t\u0006\u0005\u0006]\u0012\u0011H\u0005\u0004\u0003_\t\u0004c\u0001\n\u0002<\u00111\u0001)!\fC\u0002U\u0001BA\u0011$\u0002:!1A'!\f\u0005\u0002UBqaTA\u0017\t\u0003\t\u0019%\u0006\u0002\u0002FA!aBUA\u001d\r%\tI\u0005\u0001I\u0001\u0004\u0003\tYE\u0001\u0006GY\u0006$X*\u00199qK\u0012,B!!\u0014\u0002VM9\u0011qI\u0005\u0002P\u0005]\u0003#\u0002\"\u0002R\u0005M\u0013bAA%cA\u0019!#!\u0016\u0005\r\u0001\u000b9E1\u0001\u0016!\u0011\u0011e)a\u0015\t\rQ\n9\u0005\"\u00016\u0011\u001dy\u0015q\tC\u0001\u0003;*\"!a\u0018\u0011\t9\u0011\u00161\u000b\u0004\n\u0003G\u0002\u0001\u0013aA\u0001\u0003K\u0012\u0001\"\u00119qK:$W\rZ\u000b\u0005\u0003O\nygE\u0004\u0002b%\tI'a\u001d\u0011\u000b\t\u000bY'!\u001c\n\u0007\u0005\r\u0014\u0007E\u0002\u0013\u0003_\"q\u0001QA1\u0005\u0004\t\t(\u0005\u0002\u00123A!!IRA7\u0011\u0019!\u0014\u0011\rC\u0001k!9q*!\u0019\u0005\u0002\u0005eTCAA>!\u0011q!+!\u001c\u0007\u0013\u0005}\u0004\u0001%A\u0002\u0002\u0005\u0005%\u0001\u0003$jYR,'/\u001a3\u0014\u000f\u0005u\u0014\"a!\u0002$A\u0019!)!\"\n\u0007\u0005}\u0014\u0007\u0003\u00045\u0003{\"\t!\u000e\u0005\b\u001f\u0006uD\u0011AA\u0015\r%\ti\t\u0001I\u0001\u0004\u0003\tyI\u0001\u0006UC.,gn\u00165jY\u0016\u001cr!a#\n\u0003#\u000b\u0019\u0003E\u0002C\u0003'K1!!$2\u0011\u0019!\u00141\u0012C\u0001k!9q*a#\u0005\u0002\u0005%b!CAN\u0001A\u0005\u0019\u0011AAO\u00051!%o\u001c9qK\u0012<\u0006.\u001b7f'\u001d\tI*CAP\u0003G\u00012AQAQ\u0013\r\tY*\r\u0005\u0007i\u0005eE\u0011A\u001b\t\u000f=\u000bI\n\"\u0001\u0002*\u0019I\u0011\u0011\u0016\u0001\u0011\u0002\u0007\u0005\u00111\u0016\u0002\u00075&\u0004\b/\u001a3\u0016\t\u00055\u0016\u0011X\n\u0006\u0003OK\u0011q\u0016\t\u0005\u0005\u001a\u000b\t\f\u0005\u0004\u000b\u0003g\u000b\u0012qW\u0005\u0004\u0003k#!A\u0002+va2,'\u0007E\u0002\u0013\u0003s#a\u0001QAT\u0005\u0004)\u0002B\u0002\u001b\u0002(\u0012\u0005Q\u0007\u0003\u0006\u0002@\u0006\u001d&\u0019)D\t\u0003\u0003\fQa\u001c;iKJ,\"!a1\u0011\u000b9\t)-a.\n\u0007\u0005\u001d'AA\u0006HK:LE/\u001a:bE2,\u0007bB(\u0002(\u0012\u0005\u00111Z\u000b\u0003\u0003\u001b\u0004BA\u0004*\u00022\"I\u0011\u0011[ATA\u0013U\u00131[\u0001\u000fm&,w/\u00133f]RLg-[3s+\u0005\u0019g!CAl\u0001A\u0005\u0019\u0011AAm\u0005%Q\u0016\u000e\u001d9fI\u0006cG.\u0006\u0004\u0002\\\u0006\r\u0018\u0011^\n\u0006\u0003+L\u0011Q\u001c\t\u0005\u0005\u001a\u000by\u000eE\u0004\u000b\u0003g\u000b\t/a:\u0011\u0007I\t\u0019\u000f\u0002\u0005\u0002f\u0006U'\u0019AA9\u0005\t\t\u0015\u0007E\u0002\u0013\u0003S$a\u0001QAk\u0005\u0004)\u0002B\u0002\u001b\u0002V\u0012\u0005Q\u0007\u0003\u0006\u0002@\u0006U'\u0019)D\t\u0003_,\"!!=\u0011\u000b9\t)-a:\t\u0015\u0005U\u0018Q\u001bb!\u000e#\t90\u0001\u0005uQ&\u001cX\t\\3n+\t\t\t\u000f\u0003\u0006\u0002|\u0006U'\u0019)D\t\u0003{\f\u0001\u0002\u001e5bi\u0016cW-\\\u000b\u0003\u0003OD\u0011\"!5\u0002V\u0002&)&a5\t\u000f=\u000b)\u000e\"\u0001\u0003\u0004U\u0011!Q\u0001\t\u0005\u001dI\u000by\u000e\u0003\u0005\u0003\n\u0001\u0001K1\u0002B\u0006\u0003\u0019\t7\u000f\u00165jgR\u0019qD!\u0004\t\u0011\t=!q\u0001a\u0001\u0003G\t!\u0001_:\t\u000f\tM\u0001\u0001\"\u0005\u0003\u0016\u0005Ia.Z<[SB\u0004X\rZ\u000b\u0005\u0005/\u0011y\u0002\u0006\u0003\u0003\u001a\t\u0005\u0002\u0003\u0002\"G\u00057\u0001bACAZ#\tu\u0001c\u0001\n\u0003 \u00111\u0001I!\u0005C\u0002UA\u0001Ba\t\u0003\u0012\u0001\u0007!QE\u0001\u0005i\"\fG\u000fE\u0003\u000f\u0003\u000b\u0014i\u0002C\u0004\u0003*\u0001!\tBa\u000b\u0002\u00199,wOW5qa\u0016$\u0017\t\u001c7\u0016\r\t5\"Q\u0007B\u001d)!\u0011yCa\u000f\u0003@\t\r\u0003\u0003\u0002\"G\u0005c\u0001rACAZ\u0005g\u00119\u0004E\u0002\u0013\u0005k!\u0001\"!:\u0003(\t\u0007\u0011\u0011\u000f\t\u0004%\teBA\u0002!\u0003(\t\u0007Q\u0003\u0003\u0005\u0003$\t\u001d\u0002\u0019\u0001B\u001f!\u0015q\u0011Q\u0019B\u001c\u0011!\u0011\tEa\nA\u0002\tM\u0012!C0uQ&\u001cX\t\\3n\u0011!\u0011)Ea\nA\u0002\t]\u0012!C0uQ\u0006$X\t\\3n\u0011\u001d\u0011I\u0005\u0001C)\u0005\u0017\n\u0011B\\3x\r>\u00148-\u001a3\u0016\t\t5#1\u000b\u000b\u0005\u0005\u001f\u0012)\u0006\u0005\u0003C\r\nE\u0003c\u0001\n\u0003T\u00111\u0001Ia\u0012C\u0002UA\u0011Ba\u0004\u0003H\u0011\u0005\rAa\u0016\u0011\u000b)\u0011IF!\u0018\n\u0007\tmCA\u0001\u0005=Eft\u0017-\\3?!\u0015q!q\fB)\u0013\r\u0011\tG\u0001\u0002\u0007\u000f\u0016t7+Z9\t\u000f\t\u0015\u0004\u0001\"\u0015\u0003h\u0005Ya.Z<BaB,g\u000eZ3e+\u0011\u0011IGa\u001c\u0015\t\t-$\u0011\u000f\t\u0005\u0005\u001a\u0013i\u0007E\u0002\u0013\u0005_\"q\u0001\u0011B2\u0005\u0004\t\t\b\u0003\u0005\u0003$\t\r\u0004\u0019\u0001B:!\u0015q!Q\u000fB7\u0013\r\u00119H\u0001\u0002\u000f\u000f\u0016tGK]1wKJ\u001c\u0018M\u00197f\u0011\u001d\u0011Y\b\u0001C)\u0005{\n\u0011B\\3x\u001b\u0006\u0004\b/\u001a3\u0016\t\t}$Q\u0011\u000b\u0005\u0005\u0003\u00139\t\u0005\u0003C\r\n\r\u0005c\u0001\n\u0003\u0006\u00121\u0001I!\u001fC\u0002UAq!\u0017B=\u0001\u0004\u0011I\tE\u0003\u000b7F\u0011\u0019\tC\u0004\u0003\u000e\u0002!\tFa$\u0002\u001b9,wO\u00127bi6\u000b\u0007\u000f]3e+\u0011\u0011\tJa&\u0015\t\tM%\u0011\u0014\t\u0005\u0005\u001a\u0013)\nE\u0002\u0013\u0005/#a\u0001\u0011BF\u0005\u0004)\u0002bB-\u0003\f\u0002\u0007!1\u0014\t\u0006\u0015m\u000b\"Q\u0014\t\u0006\u001d\t}%QS\u0005\u0004\u0005C\u0013!AE$f]R\u0013\u0018M^3sg\u0006\u0014G.Z(oG\u0016DqA!*\u0001\t#\u00129+A\u0006oK^4\u0015\u000e\u001c;fe\u0016$G\u0003BA\u0012\u0005SC\u0001Ba+\u0003$\u0002\u0007!QV\u0001\u0002aB!!bW\to\u0011\u001d\u0011\t\f\u0001C)\u0005g\u000b\u0011B\\3x'2L7-\u001a3\u0015\t\u0005\r\"Q\u0017\u0005\t\u0005o\u0013y\u000b1\u0001\u0003:\u0006Qq,\u001a8ea>Lg\u000e^:\u0011\t\tm&\u0011Y\u0007\u0003\u0005{S1Aa0\u0003\u0003\u001d9WM\\3sS\u000eLAAa1\u0003>\ni1\u000b\\5dK&sG/\u001a:wC2DqAa2\u0001\t#\u0012I-A\boK^$%o\u001c9qK\u0012<\u0006.\u001b7f)\u0011\t\u0019Ca3\t\u0011\t-&Q\u0019a\u0001\u0005[CqAa4\u0001\t#\u0012\t.A\u0007oK^$\u0016m[3o/\"LG.\u001a\u000b\u0005\u0003G\u0011\u0019\u000e\u0003\u0005\u0003,\n5\u0007\u0019\u0001BW\u0011\u001d\u00119\u000e\u0001C)\u00053\f\u0001B\\3x)\u0006\\WM\u001c\u000b\u0005\u0003G\u0011Y\u000e\u0003\u0005\u0003^\nU\u0007\u0019\u0001Bp\u0003\u0005q\u0007c\u0001\u0006\u0003b&\u0019!1\u001d\u0003\u0003\u0007%sG\u000fC\u0004\u0003h\u0002!\tF!;\u0002\u00159,w\u000f\u0012:paB,G\r\u0006\u0003\u0002$\t-\b\u0002\u0003Bo\u0005K\u0004\rAa8\t\u000f\t=\b\u0001\"\u0011\u0003r\u0006!AM]8q)\ry\"1\u001f\u0005\t\u0005;\u0014i\u000f1\u0001\u0003`\"9!q\u001f\u0001\u0005B\te\u0018\u0001\u0002;bW\u0016$2a\bB~\u0011!\u0011iN!>A\u0002\t}\u0007b\u0002B\u0000\u0001\u0011\u00053\u0011A\u0001\u0004u&\u0004X\u0003CB\u0002\u00077\u0019yb!\u0003\u0015\t\r\u00151\u0011\u0005\u000b\u0005\u0007\u000f\u0019i\u0001E\u0002\u0013\u0007\u0013!qaa\u0003\u0003~\n\u0007QC\u0001\u0003UQ\u0006$\b\u0002CB\b\u0005{\u0004\u001da!\u0005\u0002\u0005\t4\u0007#\u0003B^\u0007'y2qCB\u0004\u0013\u0011\u0019)B!0\u0003\u0019\r\u000bgNQ;jY\u00124%o\\7\u0011\u000f)\t\u0019l!\u0007\u0004\u001eA\u0019!ca\u0007\u0005\u0011\u0005\u0015(Q b\u0001\u0003c\u00022AEB\u0010\t\u0019\u0001%Q b\u0001+!A!1\u0005B\u007f\u0001\u0004\u0019\u0019\u0003E\u0003\u000f\u0003\u000b\u001ci\u0002C\u0004\u0004(\u0001!\te!\u000b\u0002\u0019iL\u0007oV5uQ&sG-\u001a=\u0016\r\r-2\u0011HB\u0018)\u0011\u0019ic!\r\u0011\u0007I\u0019y\u0003B\u0004\u0004\f\r\u0015\"\u0019A\u000b\t\u0011\r=1Q\u0005a\u0002\u0007g\u0001\u0012Ba/\u0004\u0014}\u0019)d!\f\u0011\u000f)\t\u0019la\u000e\u0003`B\u0019!c!\u000f\u0005\u0011\u0005\u00158Q\u0005b\u0001\u0003cBqa!\u0010\u0001\t\u0003\u001ay$\u0001\u0004{SB\fE\u000e\\\u000b\t\u0007\u0003\u001a)f!\u0015\u0004HQA11IB,\u00077\u001ai\u0006\u0006\u0003\u0004F\r%\u0003c\u0001\n\u0004H\u0011911BB\u001e\u0005\u0004)\u0002\u0002CB\b\u0007w\u0001\u001daa\u0013\u0011\u0013\tm61C\u0010\u0004N\r\u0015\u0003c\u0002\u0006\u00024\u000e=31\u000b\t\u0004%\rEC\u0001CAs\u0007w\u0011\r!!\u001d\u0011\u0007I\u0019)\u0006\u0002\u0004A\u0007w\u0011\r!\u0006\u0005\t\u0005G\u0019Y\u00041\u0001\u0004ZA)a\"!2\u0004T!A\u0011Q_B\u001e\u0001\u0004\u0019y\u0005\u0003\u0005\u0002|\u000em\u0002\u0019AB*\u0011\u001d\u0019\t\u0007\u0001C!\u0007G\nqa\u001a:pkB,G\r\u0006\u0003\u0004f\r\u001d\u0004c\u0001\bS?!A1\u0011NB0\u0001\u0004\u0011y.\u0001\u0003tSj,\u0007bBB7\u0001\u0011\u00053qN\u0001\bg2LG-\u001b8h)\u0019\u0019)g!\u001d\u0004t!A1\u0011NB6\u0001\u0004\u0011y\u000e\u0003\u0005\u0004v\r-\u0004\u0019\u0001Bp\u0003\u0011\u0019H/\u001a9\t\u000f\r5\u0004\u0001\"\u0011\u0004zQ!1QMB>\u0011!\u0019Iga\u001eA\u0002\t}\u0007bBB@\u0001\u0011\u00053\u0011Q\u0001\nIJ|\u0007OU5hQR$2aHBB\u0011!\u0011in! A\u0002\t}\u0007bBBD\u0001\u0011\u00053\u0011R\u0001\ni\u0006\\WMU5hQR$2aHBF\u0011!\u0011in!\"A\u0002\t}\u0007bBBH\u0001\u0011\u0005\u00131[\u0001\rgR\u0014\u0018N\\4Qe\u00164\u0017\u000e\u001f")
public interface IterableViewLike<A, Coll, This extends IterableView<A, Coll> & IterableViewLike<A, Coll, This>>
extends Iterable<A>,
TraversableView<A, Coll> {
    public <B> Transformed<Tuple2<A, B>> newZipped(GenIterable<B> var1);

    public <A1, B> Transformed<Tuple2<A1, B>> newZippedAll(GenIterable<B> var1, A1 var2, B var3);

    @Override
    public <B> Transformed<B> newForced(Function0<GenSeq<B>> var1);

    @Override
    public <B> Transformed<B> newAppended(GenTraversable<B> var1);

    @Override
    public <B> Transformed<B> newMapped(Function1<A, B> var1);

    @Override
    public <B> Transformed<B> newFlatMapped(Function1<A, GenTraversableOnce<B>> var1);

    @Override
    public Transformed<A> newFiltered(Function1<A, Object> var1);

    @Override
    public Transformed<A> newSliced(SliceInterval var1);

    @Override
    public Transformed<A> newDroppedWhile(Function1<A, Object> var1);

    @Override
    public Transformed<A> newTakenWhile(Function1<A, Object> var1);

    @Override
    public Transformed<A> newTaken(int var1);

    @Override
    public Transformed<A> newDropped(int var1);

    @Override
    public This drop(int var1);

    @Override
    public This take(int var1);

    @Override
    public <A1, B, That> That zip(GenIterable<B> var1, CanBuildFrom<This, Tuple2<A1, B>, That> var2);

    @Override
    public <A1, That> That zipWithIndex(CanBuildFrom<This, Tuple2<A1, Object>, That> var1);

    @Override
    public <B, A1, That> That zipAll(GenIterable<B> var1, A1 var2, B var3, CanBuildFrom<This, Tuple2<A1, B>, That> var4);

    @Override
    public Iterator<This> grouped(int var1);

    @Override
    public Iterator<This> sliding(int var1, int var2);

    @Override
    public Iterator<This> sliding(int var1);

    @Override
    public This dropRight(int var1);

    @Override
    public This takeRight(int var1);

    @Override
    public String stringPrefix();

    public interface Forced<B>
    extends TraversableViewLike.Forced<B>,
    Transformed<B> {
        @Override
        public Iterator<B> iterator();

        public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Forced$$$outer();
    }

    public interface Sliced
    extends TraversableViewLike.Sliced,
    Transformed<A> {
        @Override
        public Iterator<A> iterator();

        public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Sliced$$$outer();
    }

    public interface Mapped<B>
    extends TraversableViewLike.Mapped<B>,
    Transformed<B> {
        @Override
        public Iterator<B> iterator();

        public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Mapped$$$outer();
    }

    public interface Zipped<B>
    extends Transformed<Tuple2<A, B>> {
        public GenIterable<B> other();

        @Override
        public Iterator<Tuple2<A, B>> iterator();

        @Override
        public String viewIdentifier();

        public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Zipped$$$outer();
    }

    public interface Appended<B>
    extends TraversableViewLike.Appended<B>,
    Transformed<B> {
        @Override
        public Iterator<B> iterator();

        public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Appended$$$outer();
    }

    public interface Filtered
    extends TraversableViewLike.Filtered,
    Transformed<A> {
        @Override
        public Iterator<A> iterator();

        public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Filtered$$$outer();
    }

    public interface EmptyView
    extends Transformed<Nothing$>,
    TraversableViewLike.EmptyView {
        @Override
        public Iterator<Nothing$> iterator();

        public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$EmptyView$$$outer();
    }

    public interface ZippedAll<A1, B>
    extends Transformed<Tuple2<A1, B>> {
        public GenIterable<B> other();

        public A1 thisElem();

        public B thatElem();

        @Override
        public String viewIdentifier();

        @Override
        public Iterator<Tuple2<A1, B>> iterator();

        public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$ZippedAll$$$outer();
    }

    public interface FlatMapped<B>
    extends TraversableViewLike.FlatMapped<B>,
    Transformed<B> {
        @Override
        public Iterator<B> iterator();

        public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$FlatMapped$$$outer();
    }

    public interface TakenWhile
    extends TraversableViewLike.TakenWhile,
    Transformed<A> {
        @Override
        public Iterator<A> iterator();

        public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$TakenWhile$$$outer();
    }

    public interface Transformed<B>
    extends IterableView<B, Coll>,
    TraversableViewLike.Transformed<B> {
        @Override
        public Iterator<B> iterator();

        @Override
        public <U> void foreach(Function1<B, U> var1);

        @Override
        public String toString();

        @Override
        public boolean isEmpty();

        public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Transformed$$$outer();
    }

    public interface DroppedWhile
    extends TraversableViewLike.DroppedWhile,
    Transformed<A> {
        @Override
        public Iterator<A> iterator();

        public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$DroppedWhile$$$outer();
    }

    public abstract class AbstractTransformed<B>
    implements Transformed<B> {
        private final Object underlying;
        private volatile boolean bitmap$0;

        @Override
        public <U> void foreach(Function1<B, U> f) {
            IterableViewLike$Transformed$class.foreach(this, f);
        }

        @Override
        public String toString() {
            return IterableViewLike$Transformed$class.toString(this);
        }

        @Override
        public boolean isEmpty() {
            return IterableViewLike$Transformed$class.isEmpty(this);
        }

        @Override
        public <B> Transformed<Tuple2<B, B>> newZipped(GenIterable<B> that) {
            return IterableViewLike$class.newZipped(this, that);
        }

        @Override
        public <A1, B> Transformed<Tuple2<A1, B>> newZippedAll(GenIterable<B> that, A1 _thisElem, B _thatElem) {
            return IterableViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
        }

        @Override
        public <B> Transformed<B> newForced(Function0<GenSeq<B>> xs) {
            return IterableViewLike$class.newForced(this, xs);
        }

        @Override
        public <B> Transformed<B> newAppended(GenTraversable<B> that) {
            return IterableViewLike$class.newAppended(this, that);
        }

        @Override
        public <B> Transformed<B> newMapped(Function1<B, B> f) {
            return IterableViewLike$class.newMapped(this, f);
        }

        @Override
        public <B> Transformed<B> newFlatMapped(Function1<B, GenTraversableOnce<B>> f) {
            return IterableViewLike$class.newFlatMapped(this, f);
        }

        @Override
        public Transformed<B> newFiltered(Function1<B, Object> p) {
            return IterableViewLike$class.newFiltered(this, p);
        }

        @Override
        public Transformed<B> newSliced(SliceInterval _endpoints) {
            return IterableViewLike$class.newSliced(this, _endpoints);
        }

        @Override
        public Transformed<B> newDroppedWhile(Function1<B, Object> p) {
            return IterableViewLike$class.newDroppedWhile(this, p);
        }

        @Override
        public Transformed<B> newTakenWhile(Function1<B, Object> p) {
            return IterableViewLike$class.newTakenWhile(this, p);
        }

        @Override
        public Transformed<B> newTaken(int n) {
            return IterableViewLike$class.newTaken(this, n);
        }

        @Override
        public Transformed<B> newDropped(int n) {
            return IterableViewLike$class.newDropped(this, n);
        }

        @Override
        public IterableView<B, Coll> drop(int n) {
            return IterableViewLike$class.drop(this, n);
        }

        @Override
        public IterableView<B, Coll> take(int n) {
            return IterableViewLike$class.take(this, n);
        }

        @Override
        public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<IterableView<B, Coll>, Tuple2<A1, B>, That> bf) {
            return (That)IterableViewLike$class.zip(this, that, bf);
        }

        @Override
        public <A1, That> That zipWithIndex(CanBuildFrom<IterableView<B, Coll>, Tuple2<A1, Object>, That> bf) {
            return (That)IterableViewLike$class.zipWithIndex(this, bf);
        }

        @Override
        public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<IterableView<B, Coll>, Tuple2<A1, B>, That> bf) {
            return (That)IterableViewLike$class.zipAll(this, that, thisElem, thatElem, bf);
        }

        @Override
        public Iterator<IterableView<B, Coll>> grouped(int size2) {
            return IterableViewLike$class.grouped(this, size2);
        }

        @Override
        public Iterator<IterableView<B, Coll>> sliding(int size2, int step) {
            return IterableViewLike$class.sliding(this, size2, step);
        }

        @Override
        public Iterator<IterableView<B, Coll>> sliding(int size2) {
            return IterableViewLike$class.sliding(this, size2);
        }

        @Override
        public IterableView<B, Coll> dropRight(int n) {
            return IterableViewLike$class.dropRight(this, n);
        }

        @Override
        public IterableView<B, Coll> takeRight(int n) {
            return IterableViewLike$class.takeRight(this, n);
        }

        @Override
        public String stringPrefix() {
            return IterableViewLike$class.stringPrefix(this);
        }

        private Object underlying$lzycompute() {
            AbstractTransformed abstractTransformed = this;
            synchronized (abstractTransformed) {
                if (!this.bitmap$0) {
                    this.underlying = TraversableViewLike$Transformed$class.underlying(this);
                    this.bitmap$0 = true;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.underlying;
            }
        }

        @Override
        public Coll underlying() {
            return this.bitmap$0 ? this.underlying : this.underlying$lzycompute();
        }

        @Override
        public final String viewIdString() {
            return TraversableViewLike$Transformed$class.viewIdString(this);
        }

        @Override
        public Option<B> headOption() {
            return TraversableViewLike$Transformed$class.headOption(this);
        }

        @Override
        public Option<B> lastOption() {
            return TraversableViewLike$Transformed$class.lastOption(this);
        }

        @Override
        public /* synthetic */ TraversableView scala$collection$TraversableViewLike$$super$tail() {
            return (TraversableView)TraversableLike$class.tail(this);
        }

        @Override
        public String viewIdentifier() {
            return TraversableViewLike$class.viewIdentifier(this);
        }

        @Override
        public String viewToString() {
            return TraversableViewLike$class.viewToString(this);
        }

        @Override
        public Builder<B, IterableView<B, Coll>> newBuilder() {
            return TraversableViewLike$class.newBuilder(this);
        }

        @Override
        public <B, That> That force(CanBuildFrom<Coll, B, That> bf) {
            return (That)TraversableViewLike$class.force(this, bf);
        }

        @Override
        public <B, That> That $plus$plus(GenTraversableOnce<B> xs, CanBuildFrom<IterableView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.$plus$plus(this, xs, bf);
        }

        @Override
        public <B, That> That map(Function1<B, B> f, CanBuildFrom<IterableView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.map(this, f, bf);
        }

        @Override
        public <B, That> That collect(PartialFunction<B, B> pf, CanBuildFrom<IterableView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.collect(this, pf, bf);
        }

        @Override
        public <B, That> That flatMap(Function1<B, GenTraversableOnce<B>> f, CanBuildFrom<IterableView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.flatMap(this, f, bf);
        }

        @Override
        public <B> TraversableViewLike.Transformed<B> flatten(Function1<B, GenTraversableOnce<B>> asTraversable) {
            return TraversableViewLike$class.flatten(this, asTraversable);
        }

        @Override
        public TraversableView filter(Function1 p) {
            return TraversableViewLike$class.filter(this, p);
        }

        @Override
        public TraversableView withFilter(Function1 p) {
            return TraversableViewLike$class.withFilter(this, p);
        }

        @Override
        public Tuple2<IterableView<B, Coll>, IterableView<B, Coll>> partition(Function1<B, Object> p) {
            return TraversableViewLike$class.partition(this, p);
        }

        @Override
        public TraversableView init() {
            return TraversableViewLike$class.init(this);
        }

        @Override
        public TraversableView slice(int from2, int until2) {
            return TraversableViewLike$class.slice(this, from2, until2);
        }

        @Override
        public TraversableView dropWhile(Function1 p) {
            return TraversableViewLike$class.dropWhile(this, p);
        }

        @Override
        public TraversableView takeWhile(Function1 p) {
            return TraversableViewLike$class.takeWhile(this, p);
        }

        @Override
        public Tuple2<IterableView<B, Coll>, IterableView<B, Coll>> span(Function1<B, Object> p) {
            return TraversableViewLike$class.span(this, p);
        }

        @Override
        public Tuple2<IterableView<B, Coll>, IterableView<B, Coll>> splitAt(int n) {
            return TraversableViewLike$class.splitAt(this, n);
        }

        @Override
        public <B, That> That scanLeft(B z, Function2<B, B, B> op, CanBuildFrom<IterableView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.scanLeft(this, z, op, bf);
        }

        @Override
        public <B, That> That scanRight(B z, Function2<B, B, B> op, CanBuildFrom<IterableView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.scanRight(this, z, op, bf);
        }

        @Override
        public <K> Map<K, IterableView<B, Coll>> groupBy(Function1<B, K> f) {
            return TraversableViewLike$class.groupBy(this, f);
        }

        @Override
        public <A1, A2> Tuple2<TraversableViewLike.Transformed<A1>, TraversableViewLike.Transformed<A2>> unzip(Function1<B, Tuple2<A1, A2>> asPair) {
            return TraversableViewLike$class.unzip(this, asPair);
        }

        @Override
        public <A1, A2, A3> Tuple3<TraversableViewLike.Transformed<A1>, TraversableViewLike.Transformed<A2>, TraversableViewLike.Transformed<A3>> unzip3(Function1<B, Tuple3<A1, A2, A3>> asTriple) {
            return TraversableViewLike$class.unzip3(this, asTriple);
        }

        @Override
        public TraversableView filterNot(Function1 p) {
            return TraversableViewLike$class.filterNot(this, p);
        }

        @Override
        public Iterator<IterableView<B, Coll>> inits() {
            return TraversableViewLike$class.inits(this);
        }

        @Override
        public Iterator<IterableView<B, Coll>> tails() {
            return TraversableViewLike$class.tails(this);
        }

        @Override
        public TraversableView tail() {
            return TraversableViewLike$class.tail(this);
        }

        @Override
        public Seq<B> thisSeq() {
            return ViewMkString$class.thisSeq(this);
        }

        @Override
        public String mkString() {
            return ViewMkString$class.mkString(this);
        }

        @Override
        public String mkString(String sep) {
            return ViewMkString$class.mkString(this, sep);
        }

        @Override
        public String mkString(String start, String sep, String end) {
            return ViewMkString$class.mkString(this, start, sep, end);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
            return ViewMkString$class.addString(this, b, start, sep, end);
        }

        @Override
        public GenericCompanion<Iterable> companion() {
            return Iterable$class.companion(this);
        }

        @Override
        public Iterable<B> seq() {
            return Iterable$class.seq(this);
        }

        @Override
        public Iterable<B> thisCollection() {
            return IterableLike$class.thisCollection(this);
        }

        @Override
        public Iterable toCollection(Object repr) {
            return IterableLike$class.toCollection(this, repr);
        }

        @Override
        public boolean forall(Function1<B, Object> p) {
            return IterableLike$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<B, Object> p) {
            return IterableLike$class.exists(this, p);
        }

        @Override
        public Option<B> find(Function1<B, Object> p) {
            return IterableLike$class.find(this, p);
        }

        @Override
        public <B> B foldRight(B z, Function2<B, B, B> op) {
            return (B)IterableLike$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceRight(Function2<B, B, B> op) {
            return (B)IterableLike$class.reduceRight(this, op);
        }

        @Override
        public Iterable<B> toIterable() {
            return IterableLike$class.toIterable(this);
        }

        @Override
        public Iterator<B> toIterator() {
            return IterableLike$class.toIterator(this);
        }

        @Override
        public B head() {
            return (B)IterableLike$class.head(this);
        }

        @Override
        public <B> void copyToArray(Object xs, int start, int len) {
            IterableLike$class.copyToArray(this, xs, start, len);
        }

        @Override
        public <B> boolean sameElements(GenIterable<B> that) {
            return IterableLike$class.sameElements(this, that);
        }

        @Override
        public Stream<B> toStream() {
            return IterableLike$class.toStream(this);
        }

        @Override
        public boolean canEqual(Object that) {
            return IterableLike$class.canEqual(this, that);
        }

        @Override
        public Object view() {
            return IterableLike$class.view(this);
        }

        @Override
        public IterableView<B, IterableView<B, Coll>> view(int from2, int until2) {
            return IterableLike$class.view(this, from2, until2);
        }

        @Override
        public <B> Builder<B, Iterable<B>> genericBuilder() {
            return GenericTraversableTemplate$class.genericBuilder(this);
        }

        @Override
        public GenTraversable transpose(Function1 asTraversable) {
            return GenericTraversableTemplate$class.transpose(this, asTraversable);
        }

        @Override
        public Object repr() {
            return TraversableLike$class.repr(this);
        }

        @Override
        public final boolean isTraversableAgain() {
            return TraversableLike$class.isTraversableAgain(this);
        }

        @Override
        public Combiner<B, ParIterable<B>> parCombiner() {
            return TraversableLike$class.parCombiner(this);
        }

        @Override
        public boolean hasDefiniteSize() {
            return TraversableLike$class.hasDefiniteSize(this);
        }

        @Override
        public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<IterableView<B, Coll>, B, That> bf) {
            return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
        }

        @Override
        public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<IterableView<B, Coll>, B, That> bf) {
            return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
        }

        @Override
        public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<IterableView<B, Coll>, B, That> cbf) {
            return (That)TraversableLike$class.scan(this, z, op, cbf);
        }

        @Override
        public B last() {
            return (B)TraversableLike$class.last(this);
        }

        @Override
        public Object sliceWithKnownDelta(int from2, int until2, int delta) {
            return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
        }

        @Override
        public Object sliceWithKnownBound(int from2, int until2) {
            return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
        }

        @Override
        public Traversable<B> toTraversable() {
            return TraversableLike$class.toTraversable(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, B, Col> cbf) {
            return (Col)TraversableLike$class.to(this, cbf);
        }

        @Override
        public Parallel par() {
            return Parallelizable$class.par(this);
        }

        @Override
        public List<B> reversed() {
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
        public int count(Function1<B, Object> p) {
            return TraversableOnce$class.count(this, p);
        }

        @Override
        public <B> Option<B> collectFirst(PartialFunction<B, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, B, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<B, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, B, B> op) {
            return (B)TraversableOnce$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, B, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, B, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<B, B, B> op) {
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
        public <B> B aggregate(Function0<B> z, Function2<B, B, B> seqop, Function2<B, B, B> combop) {
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
        public <B> B min(Ordering<B> cmp) {
            return (B)TraversableOnce$class.min(this, cmp);
        }

        @Override
        public <B> B max(Ordering<B> cmp) {
            return (B)TraversableOnce$class.max(this, cmp);
        }

        @Override
        public <B> B maxBy(Function1<B, B> f, Ordering<B> cmp) {
            return (B)TraversableOnce$class.maxBy(this, f, cmp);
        }

        @Override
        public <B> B minBy(Function1<B, B> f, Ordering<B> cmp) {
            return (B)TraversableOnce$class.minBy(this, f, cmp);
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
        public List<B> toList() {
            return TraversableOnce$class.toList(this);
        }

        @Override
        public Seq<B> toSeq() {
            return TraversableOnce$class.toSeq(this);
        }

        @Override
        public IndexedSeq<B> toIndexedSeq() {
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
        public Vector<B> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <T, U> Map<T, U> toMap(Predef$.less.colon.less<B, Tuple2<T, U>> ev) {
            return TraversableOnce$class.toMap(this, ev);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String sep) {
            return TraversableOnce$class.addString(this, b, sep);
        }

        @Override
        public StringBuilder addString(StringBuilder b) {
            return TraversableOnce$class.addString(this, b);
        }

        public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$AbstractTransformed$$$outer() {
            return IterableViewLike.this;
        }

        @Override
        public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Transformed$$$outer() {
            return this.scala$collection$IterableViewLike$AbstractTransformed$$$outer();
        }

        @Override
        public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Transformed$$$outer() {
            return this.scala$collection$IterableViewLike$AbstractTransformed$$$outer();
        }

        public AbstractTransformed() {
            if (IterableViewLike.this == null) {
                throw null;
            }
            TraversableOnce$class.$init$(this);
            Parallelizable$class.$init$(this);
            TraversableLike$class.$init$(this);
            GenericTraversableTemplate$class.$init$(this);
            GenTraversable$class.$init$(this);
            Traversable$class.$init$(this);
            GenIterable$class.$init$(this);
            IterableLike$class.$init$(this);
            Iterable$class.$init$(this);
            ViewMkString$class.$init$(this);
            TraversableViewLike$class.$init$(this);
            TraversableViewLike$Transformed$class.$init$(this);
            IterableViewLike$class.$init$(this);
            IterableViewLike$Transformed$class.$init$(this);
        }
    }
}

