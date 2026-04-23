/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.PartialFunction$class;
import scala.Predef$;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenSeq;
import scala.collection.GenSeq$class;
import scala.collection.GenSeqLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterable$class;
import scala.collection.IterableLike$class;
import scala.collection.IterableView;
import scala.collection.IterableViewLike;
import scala.collection.IterableViewLike$Transformed$class;
import scala.collection.IterableViewLike$class;
import scala.collection.Iterator;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Seq$class;
import scala.collection.SeqLike$class;
import scala.collection.SeqView;
import scala.collection.SeqViewLike$Transformed$class;
import scala.collection.SeqViewLike$class;
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
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParSeq;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u0015]b\u0001C\u0001\u0003!\u0003\r\ta\u0002\u0017\u0003\u0017M+\u0017OV5fo2K7.\u001a\u0006\u0003\u0007\u0011\t!bY8mY\u0016\u001cG/[8o\u0015\u0005)\u0011!B:dC2\f7\u0001A\u000b\u0005\u0011MQ\u0003e\u0005\u0004\u0001\u00135aR\u0006\r\t\u0003\u0015-i\u0011\u0001B\u0005\u0003\u0019\u0011\u0011a!\u00118z%\u00164\u0007c\u0001\b\u0010#5\t!!\u0003\u0002\u0011\u0005\t\u00191+Z9\u0011\u0005I\u0019B\u0002\u0001\u0003\u0007)\u0001!)\u0019A\u000b\u0003\u0003\u0005\u000b\"AF\r\u0011\u0005)9\u0012B\u0001\r\u0005\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0003\u000e\n\u0005m!!aA!osB!a\"H\t \u0013\tq\"AA\u0004TKFd\u0015n[3\u0011\u0005I\u0001CAB\u0011\u0001\t\u000b\u0007!E\u0001\u0003UQ&\u001c\u0018C\u0001\f$%\r!c\u0005\f\u0004\u0005K\u0001\u00011E\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0005\u0003\u000fOEI\u0013B\u0001\u0015\u0003\u0005\u001d\u0019V-\u001d,jK^\u0004\"A\u0005\u0016\u0005\r-\u0002AQ1\u0001\u0016\u0005\u0011\u0019u\u000e\u001c7\u0011\u000b9\u0001\u0011#K\u0010\u0011\t9q\u0013#K\u0005\u0003_\t\u0011A\"\u0013;fe\u0006\u0014G.\u001a,jK^\u0004RAD\u0019\u0012S}I!A\r\u0002\u0003!%#XM]1cY\u00164\u0016.Z<MS.,\u0007\"\u0002\u001b\u0001\t\u0003)\u0014A\u0002\u0013j]&$H\u0005F\u00017!\tQq'\u0003\u00029\t\t!QK\\5u\r\u0019Q\u0004!!\u0001\u0003w\t\u0019\u0012IY:ue\u0006\u001cG\u000f\u0016:b]N4wN]7fIV\u0011AhP\n\u0006s%i\u0014)\u0012\t\u0004\u001d=q\u0004C\u0001\n@\t\u0019\u0001\u0015\b\"b\u0001+\t\t!\tE\u0002C\u0007zj\u0011\u0001A\u0005\u0003\tF\u00121\u0002\u0016:b]N4wN]7fIB\u0019!I\u0012 \u0007\u000f\u0011\u0003\u0001\u0013aA\u0001\u000fV\u0011\u0001jS\n\u0005\r&IE\n\u0005\u0003\u000fO)K\u0003C\u0001\nL\t\u0019\u0001e\t\"b\u0001+A\u0019!i\u0011&\t\u000bQ2E\u0011A\u001b\t\u000b=3e\u0011\u0001)\u0002\r1,gn\u001a;i+\u0005\t\u0006C\u0001\u0006S\u0013\t\u0019FAA\u0002J]RDQ!\u0016$\u0007\u0002Y\u000bQ!\u00199qYf$\"AS,\t\u000ba#\u0006\u0019A)\u0002\u0007%$\u0007\u0010C\u0003[\r\u0012\u00053,\u0001\u0005u_N#(/\u001b8h)\u0005a\u0006CA/c\u001b\u0005q&BA0a\u0003\u0011a\u0017M\\4\u000b\u0003\u0005\fAA[1wC&\u00111M\u0018\u0002\u0007'R\u0014\u0018N\\4\t\u000b\u0015LD\u0011\u00014\u0002\rqJg.\u001b;?)\u00059\u0007c\u0001\":}\u00199\u0011\u000e\u0001I\u0001\u0004\u0003Q'!C#naRLh+[3x'\u0011A\u0017b\u001b7\u0011\u0007\t3e\u0003\u0005\u0002C[&\u0011\u0011.\r\u0005\u0006i!$\t!\u000e\u0005\u0006\u001f\"$)\u0005\u0015\u0005\u0006+\"$)%\u001d\u000b\u0003-IDQa\u001d9A\u0002E\u000b\u0011A\u001c\u0004\bk\u0002\u0001\n1!\u0001w\u0005\u00191uN]2fIV\u0011qo_\n\u0005i&AH\u0010E\u0002CsjL!!^\u0019\u0011\u0005IYH!\u0002!u\u0005\u0004)\u0002c\u0001\"Gu\")A\u0007\u001eC\u0001k!)q\n\u001eC\u0001!\"1Q\u000b\u001eC\u0001\u0003\u0003!2A_A\u0002\u0011\u0015Av\u00101\u0001R\r%\t9\u0001\u0001I\u0001\u0004\u0003\tIA\u0001\u0004TY&\u001cW\rZ\n\b\u0003\u000bI\u00111BA\b!\r\u0011\u0015QB\u0005\u0004\u0003\u000f\t\u0004c\u0001\"G#!1A'!\u0002\u0005\u0002UBaaTA\u0003\t\u0003\u0001\u0006bB+\u0002\u0006\u0011\u0005\u0011q\u0003\u000b\u0004#\u0005e\u0001B\u0002-\u0002\u0016\u0001\u0007\u0011\u000b\u0003\u0005\u0002\u001e\u0005\u0015A\u0011IA\u0010\u0003\u001d1wN]3bG\",B!!\t\u00020Q\u0019a'a\t\t\u0011\u0005\u0015\u00121\u0004a\u0001\u0003O\t\u0011A\u001a\t\u0007\u0015\u0005%\u0012#!\f\n\u0007\u0005-BAA\u0005Gk:\u001cG/[8ocA\u0019!#a\f\u0005\u000f\u0005E\u00121\u0004b\u0001+\t\tQ\u000b\u0003\u0005\u00026\u0005\u0015A\u0011IA\u001c\u0003!IG/\u001a:bi>\u0014XCAA\u001d!\u0011q\u00111H\t\n\u0007\u0005u\"A\u0001\u0005Ji\u0016\u0014\u0018\r^8s\r%\t\t\u0005\u0001I\u0001\u0004\u0003\t\u0019E\u0001\u0004NCB\u0004X\rZ\u000b\u0005\u0003\u000b\nieE\u0004\u0002@%\t9%a\u0014\u0011\u000b\t\u000bI%a\u0013\n\u0007\u0005\u0005\u0013\u0007E\u0002\u0013\u0003\u001b\"a\u0001QA \u0005\u0004)\u0002\u0003\u0002\"G\u0003\u0017Ba\u0001NA \t\u0003)\u0004BB(\u0002@\u0011\u0005\u0001\u000bC\u0004V\u0003\u007f!\t!a\u0016\u0015\t\u0005-\u0013\u0011\f\u0005\u00071\u0006U\u0003\u0019A)\u0007\u0013\u0005u\u0003\u0001%A\u0002\u0002\u0005}#A\u0003$mCRl\u0015\r\u001d9fIV!\u0011\u0011MA5'\u001d\tY&CA2\u0003W\u0002RAQA3\u0003OJ1!!\u00182!\r\u0011\u0012\u0011\u000e\u0003\u0007\u0001\u0006m#\u0019A\u000b\u0011\t\t3\u0015q\r\u0005\u0007i\u0005mC\u0011A\u001b\t\u0017\u0005E\u00141\fECB\u0013E\u00111O\u0001\u0006S:$W\r_\u000b\u0003\u0003k\u0002BACA<#&\u0019\u0011\u0011\u0010\u0003\u0003\u000b\u0005\u0013(/Y=\t\u0017\u0005u\u00141\fE\u0001B\u0003&\u0011QO\u0001\u0007S:$W\r\u001f\u0011\t\u0013\u0005\u0005\u00151\fQ\u0005\u0012\u0005\r\u0015a\u00024j]\u0012\u0014vn\u001e\u000b\b#\u0006\u0015\u0015qQAF\u0011\u0019A\u0016q\u0010a\u0001#\"9\u0011\u0011RA@\u0001\u0004\t\u0016A\u00017p\u0011\u001d\ti)a A\u0002E\u000b!\u0001[5\t\r=\u000bY\u0006\"\u0001Q\u0011\u001d)\u00161\fC\u0001\u0003'#B!a\u001a\u0002\u0016\"1\u0001,!%A\u0002E3\u0011\"!'\u0001!\u0003\r\t!a'\u0003\u0011\u0005\u0003\b/\u001a8eK\u0012,B!!(\u0002&N9\u0011qS\u0005\u0002 \u0006%\u0006#\u0002\"\u0002\"\u0006\r\u0016bAAMcA\u0019!#!*\u0005\u000f\u0001\u000b9J1\u0001\u0002(F\u0011\u0011#\u0007\t\u0005\u0005\u001a\u000b\u0019\u000b\u0003\u00045\u0003/#\t!\u000e\u0005\f\u0003_\u000b9\n#b!\n#\t\t,A\u0004sKN$8+Z9\u0016\u0005\u0005M\u0006#\u0002\b\u00026\u0006\r\u0016bAA\\\u0005\t1q)\u001a8TKFD1\"a/\u0002\u0018\"\u0005\t\u0015)\u0003\u00024\u0006A!/Z:u'\u0016\f\b\u0005\u0003\u0004P\u0003/#\t\u0001\u0015\u0005\b+\u0006]E\u0011AAa)\u0011\t\u0019+a1\t\ra\u000by\f1\u0001R\r%\t9\r\u0001I\u0001\u0004\u0003\tIM\u0001\u0005GS2$XM]3e'\u001d\t)-CAf\u0003\u001f\u00012AQAg\u0013\r\t9-\r\u0005\u0007i\u0005\u0015G\u0011A\u001b\t\u0017\u0005E\u0014Q\u0019ECB\u0013E\u00111\u000f\u0005\f\u0003{\n)\r#A!B\u0013\t)\b\u0003\u0004P\u0003\u000b$\t\u0001\u0015\u0005\b+\u0006\u0015G\u0011AAm)\r\t\u00121\u001c\u0005\u00071\u0006]\u0007\u0019A)\u0007\u0013\u0005}\u0007\u0001%A\u0002\u0002\u0005\u0005(A\u0003+bW\u0016tw\u000b[5mKN9\u0011Q\\\u0005\u0002d\u0006=\u0001c\u0001\"\u0002f&\u0019\u0011q\\\u0019\t\rQ\ni\u000e\"\u00016\u0011)\tY/!8\t\u0006\u0004&\t\u0002U\u0001\u0004Y\u0016t\u0007BCAx\u0003;D\t\u0011)Q\u0005#\u0006!A.\u001a8!\u0011\u0019y\u0015Q\u001cC\u0001!\"9Q+!8\u0005\u0002\u0005UHcA\t\u0002x\"1\u0001,a=A\u0002E3\u0011\"a?\u0001!\u0003\r\t!!@\u0003\u0019\u0011\u0013x\u000e\u001d9fI^C\u0017\u000e\\3\u0014\u000f\u0005e\u0018\"a@\u0002\u0010A\u0019!I!\u0001\n\u0007\u0005m\u0018\u0007\u0003\u00045\u0003s$\t!\u000e\u0005\u000b\u0005\u000f\tI\u0010#b!\n#\u0001\u0016!B:uCJ$\bB\u0003B\u0006\u0003sD\t\u0011)Q\u0005#\u000611\u000f^1si\u0002BaaTA}\t\u0003\u0001\u0006bB+\u0002z\u0012\u0005!\u0011\u0003\u000b\u0004#\tM\u0001B\u0002-\u0003\u0010\u0001\u0007\u0011KB\u0005\u0003\u0018\u0001\u0001\n1!\u0001\u0003\u001a\t1!,\u001b9qK\u0012,BAa\u0007\u0003$M9!QC\u0005\u0003\u001e\t\u0015\u0002#\u0002\"\u0003 \t\u0005\u0012b\u0001B\fcA\u0019!Ca\t\u0005\r\u0001\u0013)B1\u0001\u0016!\u0011\u0011eIa\n\u0011\r)\u0011I#\u0005B\u0011\u0013\r\u0011Y\u0003\u0002\u0002\u0007)V\u0004H.\u001a\u001a\t\rQ\u0012)\u0002\"\u00016\u0011-\u0011\tD!\u0006\t\u0006\u0004&\tBa\r\u0002\u000fQD\u0017\r^*fcV\u0011!Q\u0007\t\u0005\u001d=\u0011\t\u0003C\u0006\u0003:\tU\u0001\u0012!Q!\n\tU\u0012\u0001\u0003;iCR\u001cV-\u001d\u0011\t\r=\u0013)\u0002\"\u0001Q\u0011\u001d)&Q\u0003C\u0001\u0005\u007f!BAa\n\u0003B!1\u0001L!\u0010A\u0002E3\u0011B!\u0012\u0001!\u0003\r\tAa\u0012\u0003\u0013iK\u0007\u000f]3e\u00032dWC\u0002B%\u0005#\u00129fE\u0004\u0003D%\u0011YE!\u0017\u0011\u000f\t\u0013iEa\u0014\u0003V%\u0019!QI\u0019\u0011\u0007I\u0011\t\u0006\u0002\u0005\u0003T\t\r#\u0019AAT\u0005\t\t\u0015\u0007E\u0002\u0013\u0005/\"a\u0001\u0011B\"\u0005\u0004)\u0002\u0003\u0002\"G\u00057\u0002rA\u0003B\u0015\u0005\u001f\u0012)\u0006\u0003\u00045\u0005\u0007\"\t!\u000e\u0005\f\u0005c\u0011\u0019\u0005#b!\n#\u0011\t'\u0006\u0002\u0003dA!ab\u0004B+\u0011-\u0011IDa\u0011\t\u0002\u0003\u0006KAa\u0019\t\r=\u0013\u0019\u0005\"\u0001Q\u0011\u001d)&1\tC\u0001\u0005W\"BAa\u0017\u0003n!1\u0001L!\u001bA\u0002E3\u0011B!\u001d\u0001!\u0003\r\tAa\u001d\u0003\u0011I+g/\u001a:tK\u0012\u001cRAa\u001c\n\u0003\u001fAa\u0001\u000eB8\t\u0003)\u0004\u0002CA\u001b\u0005_\"\t%a\u000e\t\r=\u0013y\u0007\"\u0001Q\u0011\u001d)&q\u000eC\u0001\u0005{\"2!\u0005B@\u0011\u0019A&1\u0010a\u0001#\"I!1\u0011B8A\u0013U#QQ\u0001\u000fm&,w/\u00133f]RLg-[3s+\u0005a\u0006\u0002\u0003BE\u0005_\"I!a\u000e\u0002-\r\u0014X-\u0019;f%\u00164XM]:fI&#XM]1u_J4\u0011B!$\u0001!\u0003\r\tAa$\u0003\u000fA\u000bGo\u00195fIV!!\u0011\u0013BL'\u0015\u0011Y)\u0003BJ!\u0011\u0011eI!&\u0011\u0007I\u00119\nB\u0004A\u0005\u0017\u0013\r!a*\t\rQ\u0012Y\t\"\u00016\u0011%\u0011iJa#CB\u001bE\u0001+\u0001\u0003ge>l\u0007B\u0003BQ\u0005\u0017\u0013\rU\"\u0005\u0003$\u0006)\u0001/\u0019;dQV\u0011!Q\u0015\t\u0006\u001d\u0005U&Q\u0013\u0005\n\u0005S\u0013YI1Q\u0007\u0012A\u000b\u0001B]3qY\u0006\u001cW\r\u001a\u0005\u000b\u0005[\u0013Y\t#b\u0001\n\u0013\u0001\u0016\u0001\u00029mK:D!B!-\u0003\f\"\u0005\t\u0015)\u0003R\u0003\u0015\u0001H.\u001a8!\u0011!\t)Da#\u0005B\tUVC\u0001B\\!\u0015q\u00111\bBK\u0011\u0019y%1\u0012C\u0001!\"9QKa#\u0005\u0002\tuF\u0003\u0002BK\u0005\u007fCa\u0001\u0017B^\u0001\u0004\t\u0006\"\u0003BB\u0005\u0017\u0003KQ\u000bBC\r%\u0011)\r\u0001I\u0001\u0004\u0003\u00119MA\u0005Qe\u0016\u0004XM\u001c3fIV!!\u0011\u001aBh'\u0015\u0011\u0019-\u0003Bf!\u0011\u0011eI!4\u0011\u0007I\u0011y\rB\u0004A\u0005\u0007\u0014\r!a*\t\rQ\u0012\u0019\r\"\u00016\u0011)\u0011)Na1CB\u001bE!q[\u0001\u0004MN$XC\u0001Bg\u0011!\t)Da1\u0005B\tmWC\u0001Bo!\u0015q\u00111\bBg\u0011\u0019y%1\u0019C\u0001!\"9QKa1\u0005\u0002\t\rH\u0003\u0002Bg\u0005KDa\u0001\u0017Bq\u0001\u0004\t\u0006\"\u0003BB\u0005\u0007\u0004KQ\u000bBC\u0011\u001d\u0011Y\u000f\u0001C)\u0005[\f\u0011B\\3x\r>\u00148-\u001a3\u0016\t\t=(Q\u001f\u000b\u0005\u0005c\u00149\u0010\u0005\u0003C\r\nM\bc\u0001\n\u0003v\u00121\u0001I!;C\u0002UA\u0011B!?\u0003j\u0012\u0005\rAa?\u0002\u0005a\u001c\b#\u0002\u0006\u0003~\u000e\u0005\u0011b\u0001B\u0000\t\tAAHY=oC6,g\bE\u0003\u000f\u0003k\u0013\u0019\u0010C\u0004\u0004\u0006\u0001!\tfa\u0002\u0002\u00179,w/\u00119qK:$W\rZ\u000b\u0005\u0007\u0013\u0019y\u0001\u0006\u0003\u0004\f\rE\u0001\u0003\u0002\"G\u0007\u001b\u00012AEB\b\t\u001d\u000151\u0001b\u0001\u0003OC\u0001ba\u0005\u0004\u0004\u0001\u00071QC\u0001\u0005i\"\fG\u000fE\u0003\u000f\u0007/\u0019i!C\u0002\u0004\u001a\t\u0011abR3o)J\fg/\u001a:tC\ndW\rC\u0004\u0004\u001e\u0001!\tfa\b\u0002\u00139,w/T1qa\u0016$W\u0003BB\u0011\u0007O!Baa\t\u0004*A!!IRB\u0013!\r\u00112q\u0005\u0003\u0007\u0001\u000em!\u0019A\u000b\t\u0011\u0005\u001521\u0004a\u0001\u0007W\u0001bACA\u0015#\r\u0015\u0002bBB\u0018\u0001\u0011E3\u0011G\u0001\u000e]\u0016<h\t\\1u\u001b\u0006\u0004\b/\u001a3\u0016\t\rM2\u0011\b\u000b\u0005\u0007k\u0019Y\u0004\u0005\u0003C\r\u000e]\u0002c\u0001\n\u0004:\u00111\u0001i!\fC\u0002UA\u0001\"!\n\u0004.\u0001\u00071Q\b\t\u0007\u0015\u0005%\u0012ca\u0010\u0011\u000b9\u0019\tea\u000e\n\u0007\r\r#A\u0001\nHK:$&/\u0019<feN\f'\r\\3P]\u000e,\u0007bBB$\u0001\u0011E3\u0011J\u0001\f]\u0016<h)\u001b7uKJ,G\r\u0006\u0003\u0002\u0010\r-\u0003\u0002CB'\u0007\u000b\u0002\raa\u0014\u0002\u0003A\u0004bACA\u0015#\rE\u0003c\u0001\u0006\u0004T%\u00191Q\u000b\u0003\u0003\u000f\t{w\u000e\\3b]\"91\u0011\f\u0001\u0005R\rm\u0013!\u00038foNc\u0017nY3e)\u0011\tya!\u0018\t\u0011\r}3q\u000ba\u0001\u0007C\n!bX3oIB|\u0017N\u001c;t!\u0011\u0019\u0019g!\u001b\u000e\u0005\r\u0015$bAB4\u0005\u00059q-\u001a8fe&\u001c\u0017\u0002BB6\u0007K\u0012Qb\u00157jG\u0016Le\u000e^3sm\u0006d\u0007bBB8\u0001\u0011E3\u0011O\u0001\u0010]\u0016<HI]8qa\u0016$w\u000b[5mKR!\u0011qBB:\u0011!\u0019ie!\u001cA\u0002\r=\u0003bBB<\u0001\u0011E3\u0011P\u0001\u000e]\u0016<H+Y6f]^C\u0017\u000e\\3\u0015\t\u0005=11\u0010\u0005\t\u0007\u001b\u001a)\b1\u0001\u0004P!91q\u0010\u0001\u0005R\r\u0005\u0015!\u00038fojK\u0007\u000f]3e+\u0011\u0019\u0019ia#\u0015\t\r\u00155Q\u0012\t\u0005\u0005\u001a\u001b9\t\u0005\u0004\u000b\u0005S\t2\u0011\u0012\t\u0004%\r-EA\u0002!\u0004~\t\u0007Q\u0003\u0003\u0005\u0004\u0014\ru\u0004\u0019ABH!\u0015q1\u0011SBE\u0013\r\u0019\u0019J\u0001\u0002\f\u000f\u0016t\u0017\n^3sC\ndW\rC\u0004\u0004\u0018\u0002!\tf!'\u0002\u00199,wOW5qa\u0016$\u0017\t\u001c7\u0016\r\rm51UBT)!\u0019ij!+\u0004.\u000eE\u0006\u0003\u0002\"G\u0007?\u0003rA\u0003B\u0015\u0007C\u001b)\u000bE\u0002\u0013\u0007G#\u0001Ba\u0015\u0004\u0016\n\u0007\u0011q\u0015\t\u0004%\r\u001dFA\u0002!\u0004\u0016\n\u0007Q\u0003\u0003\u0005\u0004\u0014\rU\u0005\u0019ABV!\u0015q1\u0011SBS\u0011!\u0019yk!&A\u0002\r\u0005\u0016!C0uQ&\u001cX\t\\3n\u0011!\u0019\u0019l!&A\u0002\r\u0015\u0016!C0uQ\u0006$X\t\\3n\u0011\u001d\u00199\f\u0001C\t\u0007s\u000b1B\\3x%\u00164XM]:fIV\u0011\u0011q\u0002\u0005\b\u0007{\u0003A\u0011CB`\u0003)qWm\u001e)bi\u000eDW\rZ\u000b\u0005\u0007\u0003\u001c9\r\u0006\u0005\u0004D\u000e%7QZBj!\u0011\u0011ei!2\u0011\u0007I\u00199\rB\u0004A\u0007w\u0013\r!a*\t\u000f\r-71\u0018a\u0001#\u0006)qL\u001a:p[\"A1qZB^\u0001\u0004\u0019\t.\u0001\u0004`a\u0006$8\r\u001b\t\u0006\u001d\u0005U6Q\u0019\u0005\b\u0007+\u001cY\f1\u0001R\u0003%y&/\u001a9mC\u000e,G\rC\u0004\u0004Z\u0002!\tba7\u0002\u00199,w\u000f\u0015:fa\u0016tG-\u001a3\u0016\t\ru71\u001d\u000b\u0005\u0007?\u001c)\u000f\u0005\u0003C\r\u000e\u0005\bc\u0001\n\u0004d\u00129\u0001ia6C\u0002\u0005\u001d\u0006\u0002CBt\u0007/\u0004\ra!9\u0002\t\u0015dW-\u001c\u0005\b\u0007W\u0004A\u0011KBw\u0003!qWm\u001e+bW\u0016tG\u0003BA\b\u0007_Daa]Bu\u0001\u0004\t\u0006bBBz\u0001\u0011E3Q_\u0001\u000b]\u0016<HI]8qa\u0016$G\u0003BA\b\u0007oDaa]By\u0001\u0004\t\u0006bBB~\u0001\u0011\u00053Q`\u0001\be\u00164XM]:f+\u0005y\u0002b\u0002BQ\u0001\u0011\u0005C\u0011A\u000b\u0007\t\u0007!I\u0002\"\u0003\u0015\u0011\u0011\u0015A1\u0004C\u000f\tC!B\u0001b\u0002\u0005\u000eA\u0019!\u0003\"\u0003\u0005\u000f\u0011-1q b\u0001+\t!A\u000b[1u\u0011!!yaa@A\u0004\u0011E\u0011A\u00012g!%\u0019\u0019\u0007b\u0005 \t/!9!\u0003\u0003\u0005\u0016\r\u0015$\u0001D\"b]\n+\u0018\u000e\u001c3Ge>l\u0007c\u0001\n\u0005\u001a\u00119\u0001ia@C\u0002\u0005\u001d\u0006b\u0002BO\u0007\u007f\u0004\r!\u0015\u0005\t\u0005C\u001by\u00101\u0001\u0005 A)a\"!.\u0005\u0018!9!\u0011VB\u0000\u0001\u0004\t\u0006b\u0002C\u0013\u0001\u0011\u0005CqE\u0001\u0006a\u0006$Gk\\\u000b\u0007\tS!9\u0004b\f\u0015\r\u0011-B\u0011\bC\u001e)\u0011!i\u0003\"\r\u0011\u0007I!y\u0003B\u0004\u0005\f\u0011\r\"\u0019A\u000b\t\u0011\u0011=A1\u0005a\u0002\tg\u0001\u0012ba\u0019\u0005\u0014}!)\u0004\"\f\u0011\u0007I!9\u0004B\u0004A\tG\u0011\r!a*\t\u000f\u0005-H1\u0005a\u0001#\"A1q\u001dC\u0012\u0001\u0004!)\u0004C\u0004\u0005@\u0001!\t\u0005\"\u0011\u0002\u0015I,g/\u001a:tK6\u000b\u0007/\u0006\u0004\u0005D\u0011EC\u0011\n\u000b\u0005\t\u000b\"\u0019\u0006\u0006\u0003\u0005H\u0011-\u0003c\u0001\n\u0005J\u00119A1\u0002C\u001f\u0005\u0004)\u0002\u0002\u0003C\b\t{\u0001\u001d\u0001\"\u0014\u0011\u0013\r\rD1C\u0010\u0005P\u0011\u001d\u0003c\u0001\n\u0005R\u00111\u0001\t\"\u0010C\u0002UA\u0001\"!\n\u0005>\u0001\u0007AQ\u000b\t\u0007\u0015\u0005%\u0012\u0003b\u0014\t\u000f\u0011e\u0003\u0001\"\u0011\u0005\\\u00059Q\u000f\u001d3bi\u0016$WC\u0002C/\tW\"\u0019\u0007\u0006\u0004\u0005`\u00115Dq\u000e\u000b\u0005\tC\")\u0007E\u0002\u0013\tG\"q\u0001b\u0003\u0005X\t\u0007Q\u0003\u0003\u0005\u0005\u0010\u0011]\u00039\u0001C4!%\u0019\u0019\u0007b\u0005 \tS\"\t\u0007E\u0002\u0013\tW\"q\u0001\u0011C,\u0005\u0004\t9\u000bC\u0004\u0002r\u0011]\u0003\u0019A)\t\u0011\r\u001dHq\u000ba\u0001\tSBq\u0001b\u001d\u0001\t\u0003\")(A\u0006%a2,8\u000fJ2pY>tWC\u0002C<\t\u000b#i\b\u0006\u0003\u0005z\u0011\u001dE\u0003\u0002C>\t\u007f\u00022A\u0005C?\t\u001d!Y\u0001\"\u001dC\u0002UA\u0001\u0002b\u0004\u0005r\u0001\u000fA\u0011\u0011\t\n\u0007G\"\u0019b\bCB\tw\u00022A\u0005CC\t\u001d\u0001E\u0011\u000fb\u0001\u0003OC\u0001ba:\u0005r\u0001\u0007A1\u0011\u0005\b\t\u0017\u0003A\u0011\tCG\u0003-!3m\u001c7p]\u0012\u0002H.^:\u0016\r\u0011=EQ\u0014CK)\u0011!\t\nb(\u0015\t\u0011MEq\u0013\t\u0004%\u0011UEa\u0002C\u0006\t\u0013\u0013\r!\u0006\u0005\t\t\u001f!I\tq\u0001\u0005\u001aBI11\rC\n?\u0011mE1\u0013\t\u0004%\u0011uEa\u0002!\u0005\n\n\u0007\u0011q\u0015\u0005\t\u0007O$I\t1\u0001\u0005\u001c\"9A1\u0015\u0001\u0005B\u0011\u0015\u0016!B;oS>tWC\u0002CT\tk#i\u000b\u0006\u0003\u0005*\u0012]F\u0003\u0002CV\t_\u00032A\u0005CW\t\u001d!Y\u0001\")C\u0002UA\u0001\u0002b\u0004\u0005\"\u0002\u000fA\u0011\u0017\t\n\u0007G\"\u0019b\bCZ\tW\u00032A\u0005C[\t\u001d\u0001E\u0011\u0015b\u0001\u0003OC\u0001ba\u0005\u0005\"\u0002\u0007A\u0011\u0018\t\u0006\u001d\u0005UF1\u0017\u0005\b\t{\u0003A\u0011\tC`\u0003\u0011!\u0017N\u001a4\u0016\t\u0011\u0005G\u0011\u001a\u000b\u0004?\u0011\r\u0007\u0002CB\n\tw\u0003\r\u0001\"2\u0011\u000b9\t)\fb2\u0011\u0007I!I\rB\u0004A\tw\u0013\r!a*\t\u000f\u00115\u0007\u0001\"\u0011\u0005P\u0006I\u0011N\u001c;feN,7\r^\u000b\u0005\t#$I\u000eF\u0002 \t'D\u0001ba\u0005\u0005L\u0002\u0007AQ\u001b\t\u0006\u001d\u0005UFq\u001b\t\u0004%\u0011eGa\u0002!\u0005L\n\u0007\u0011q\u0015\u0005\b\t;\u0004A\u0011\tCp\u0003\u0019\u0019xN\u001d;fIV!A\u0011\u001dC|)\ryB1\u001d\u0005\t\tK$Y\u000eq\u0001\u0005h\u0006\u0019qN\u001d3\u0011\r\u0011%Hq\u001eC{\u001d\rQA1^\u0005\u0004\t[$\u0011a\u00029bG.\fw-Z\u0005\u0005\tc$\u0019P\u0001\u0005Pe\u0012,'/\u001b8h\u0015\r!i\u000f\u0002\t\u0004%\u0011]Ha\u0002!\u0005\\\n\u0007\u0011q\u0015\u0005\b\tw\u0004A\u0011\tC\u007f\u0003!\u0019xN\u001d;XSRDGcA\u0010\u0005\u0000\"AQ\u0011\u0001C}\u0001\u0004)\u0019!\u0001\u0002miB9!\"\"\u0002\u0012#\rE\u0013bAC\u0004\t\tIa)\u001e8di&|gN\r\u0005\b\u000b\u0017\u0001A\u0011IC\u0007\u0003\u0019\u0019xN\u001d;CsV!QqBC\r)\u0011)\t\"b\u0007\u0015\u0007})\u0019\u0002\u0003\u0005\u0005f\u0016%\u00019AC\u000b!\u0019!I\u000fb<\u0006\u0018A\u0019!#\"\u0007\u0005\r\u0001+IA1\u0001\u0016\u0011!\t)#\"\u0003A\u0002\u0015u\u0001C\u0002\u0006\u0002*E)9\u0002C\u0004\u0006\"\u0001!\t%b\t\u0002\u0019\r|WNY5oCRLwN\\:\u0015\t\u0015\u0015Rq\u0005\t\u0005\u001d\u0005mr\u0004\u0003\u0004t\u000b?\u0001\r!\u0015\u0005\b\u000bW\u0001A\u0011IC\u0017\u00031\u0001XM]7vi\u0006$\u0018n\u001c8t+\t))\u0003C\u0004\u00062\u0001!\te!@\u0002\u0011\u0011L7\u000f^5oGRDq!\"\u000e\u0001\t\u0003\u0012))\u0001\u0007tiJLgn\u001a)sK\u001aL\u0007\u0010")
public interface SeqViewLike<A, Coll, This extends SeqView<A, Coll> & SeqViewLike<A, Coll, This>>
extends Seq<A>,
IterableView<A, Coll> {
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
    public <B> Transformed<Tuple2<A, B>> newZipped(GenIterable<B> var1);

    @Override
    public <A1, B> Transformed<Tuple2<A1, B>> newZippedAll(GenIterable<B> var1, A1 var2, B var3);

    public Transformed<A> newReversed();

    public <B> Transformed<B> newPatched(int var1, GenSeq<B> var2, int var3);

    public <B> Transformed<B> newPrepended(B var1);

    @Override
    public Transformed<A> newTaken(int var1);

    @Override
    public Transformed<A> newDropped(int var1);

    @Override
    public This reverse();

    @Override
    public <B, That> That patch(int var1, GenSeq<B> var2, int var3, CanBuildFrom<This, B, That> var4);

    @Override
    public <B, That> That padTo(int var1, B var2, CanBuildFrom<This, B, That> var3);

    @Override
    public <B, That> That reverseMap(Function1<A, B> var1, CanBuildFrom<This, B, That> var2);

    @Override
    public <B, That> That updated(int var1, B var2, CanBuildFrom<This, B, That> var3);

    @Override
    public <B, That> That $plus$colon(B var1, CanBuildFrom<This, B, That> var2);

    @Override
    public <B, That> That $colon$plus(B var1, CanBuildFrom<This, B, That> var2);

    @Override
    public <B, That> That union(GenSeq<B> var1, CanBuildFrom<This, B, That> var2);

    @Override
    public <B> This diff(GenSeq<B> var1);

    @Override
    public <B> This intersect(GenSeq<B> var1);

    @Override
    public <B> This sorted(Ordering<B> var1);

    @Override
    public This sortWith(Function2<A, A, Object> var1);

    @Override
    public <B> This sortBy(Function1<A, B> var1, Ordering<B> var2);

    @Override
    public Iterator<This> combinations(int var1);

    @Override
    public Iterator<This> permutations();

    @Override
    public This distinct();

    @Override
    public String stringPrefix();

    public interface Forced<B>
    extends IterableViewLike.Forced<B>,
    Transformed<B> {
        @Override
        public int length();

        @Override
        public B apply(int var1);

        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Forced$$$outer();
    }

    public interface Sliced
    extends IterableViewLike.Sliced,
    Transformed<A> {
        @Override
        public int length();

        @Override
        public A apply(int var1);

        @Override
        public <U> void foreach(Function1<A, U> var1);

        @Override
        public Iterator<A> iterator();

        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Sliced$$$outer();
    }

    public interface Mapped<B>
    extends IterableViewLike.Mapped<B>,
    Transformed<B> {
        @Override
        public int length();

        @Override
        public B apply(int var1);

        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Mapped$$$outer();
    }

    public interface Zipped<B>
    extends IterableViewLike.Zipped<B>,
    Transformed<Tuple2<A, B>> {
        public Seq<B> thatSeq();

        @Override
        public int length();

        @Override
        public Tuple2<A, B> apply(int var1);

        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Zipped$$$outer();
    }

    public interface Patched<B>
    extends Transformed<B> {
        public int from();

        public GenSeq<B> patch();

        public int replaced();

        public int scala$collection$SeqViewLike$Patched$$plen();

        @Override
        public Iterator<B> iterator();

        @Override
        public int length();

        @Override
        public B apply(int var1);

        @Override
        public String viewIdentifier();

        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Patched$$$outer();
    }

    public interface Appended<B>
    extends IterableViewLike.Appended<B>,
    Transformed<B> {
        public GenSeq<B> restSeq();

        @Override
        public int length();

        @Override
        public B apply(int var1);

        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Appended$$$outer();
    }

    public interface Filtered
    extends IterableViewLike.Filtered,
    Transformed<A> {
        public int[] index();

        @Override
        public int length();

        @Override
        public A apply(int var1);

        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Filtered$$$outer();
    }

    public interface Reversed
    extends Transformed<A> {
        @Override
        public Iterator<A> iterator();

        @Override
        public int length();

        @Override
        public A apply(int var1);

        @Override
        public String viewIdentifier();

        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Reversed$$$outer();
    }

    public interface EmptyView
    extends Transformed<Nothing$>,
    IterableViewLike.EmptyView {
        @Override
        public int length();

        @Override
        public Nothing$ apply(int var1);

        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$EmptyView$$$outer();
    }

    public interface ZippedAll<A1, B>
    extends IterableViewLike.ZippedAll<A1, B>,
    Transformed<Tuple2<A1, B>> {
        public Seq<B> thatSeq();

        @Override
        public int length();

        @Override
        public Tuple2<A1, B> apply(int var1);

        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$ZippedAll$$$outer();
    }

    public interface Prepended<B>
    extends Transformed<B> {
        public B fst();

        @Override
        public Iterator<B> iterator();

        @Override
        public int length();

        @Override
        public B apply(int var1);

        @Override
        public String viewIdentifier();

        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Prepended$$$outer();
    }

    public interface FlatMapped<B>
    extends IterableViewLike.FlatMapped<B>,
    Transformed<B> {
        public int[] index();

        public int findRow(int var1, int var2, int var3);

        @Override
        public int length();

        @Override
        public B apply(int var1);

        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$FlatMapped$$$outer();
    }

    public interface TakenWhile
    extends IterableViewLike.TakenWhile,
    Transformed<A> {
        public int len();

        @Override
        public int length();

        @Override
        public A apply(int var1);

        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$TakenWhile$$$outer();
    }

    public interface Transformed<B>
    extends SeqView<B, Coll>,
    IterableViewLike.Transformed<B> {
        @Override
        public int length();

        @Override
        public B apply(int var1);

        @Override
        public String toString();

        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Transformed$$$outer();
    }

    public interface DroppedWhile
    extends IterableViewLike.DroppedWhile,
    Transformed<A> {
        public int start();

        @Override
        public int length();

        @Override
        public A apply(int var1);

        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$DroppedWhile$$$outer();
    }

    public abstract class AbstractTransformed<B>
    implements Transformed<B> {
        private final Object underlying;
        private volatile boolean bitmap$0;

        @Override
        public String toString() {
            return SeqViewLike$Transformed$class.toString(this);
        }

        @Override
        public <B> Transformed<B> newForced(Function0<GenSeq<B>> xs) {
            return SeqViewLike$class.newForced(this, xs);
        }

        @Override
        public <B> Transformed<B> newAppended(GenTraversable<B> that) {
            return SeqViewLike$class.newAppended(this, that);
        }

        @Override
        public <B> Transformed<B> newMapped(Function1<B, B> f) {
            return SeqViewLike$class.newMapped(this, f);
        }

        @Override
        public <B> Transformed<B> newFlatMapped(Function1<B, GenTraversableOnce<B>> f) {
            return SeqViewLike$class.newFlatMapped(this, f);
        }

        @Override
        public Transformed<B> newFiltered(Function1<B, Object> p) {
            return SeqViewLike$class.newFiltered(this, p);
        }

        @Override
        public Transformed<B> newSliced(SliceInterval _endpoints) {
            return SeqViewLike$class.newSliced(this, _endpoints);
        }

        @Override
        public Transformed<B> newDroppedWhile(Function1<B, Object> p) {
            return SeqViewLike$class.newDroppedWhile(this, p);
        }

        @Override
        public Transformed<B> newTakenWhile(Function1<B, Object> p) {
            return SeqViewLike$class.newTakenWhile(this, p);
        }

        @Override
        public <B> Transformed<Tuple2<B, B>> newZipped(GenIterable<B> that) {
            return SeqViewLike$class.newZipped(this, that);
        }

        @Override
        public <A1, B> Transformed<Tuple2<A1, B>> newZippedAll(GenIterable<B> that, A1 _thisElem, B _thatElem) {
            return SeqViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
        }

        @Override
        public Transformed<B> newReversed() {
            return SeqViewLike$class.newReversed(this);
        }

        @Override
        public <B> Transformed<B> newPatched(int _from, GenSeq<B> _patch, int _replaced) {
            return SeqViewLike$class.newPatched(this, _from, _patch, _replaced);
        }

        @Override
        public <B> Transformed<B> newPrepended(B elem) {
            return SeqViewLike$class.newPrepended(this, elem);
        }

        @Override
        public Transformed<B> newTaken(int n) {
            return SeqViewLike$class.newTaken(this, n);
        }

        @Override
        public Transformed<B> newDropped(int n) {
            return SeqViewLike$class.newDropped(this, n);
        }

        @Override
        public SeqView<B, Coll> reverse() {
            return SeqViewLike$class.reverse(this);
        }

        @Override
        public <B, That> That patch(int from2, GenSeq<B> patch2, int replaced, CanBuildFrom<SeqView<B, Coll>, B, That> bf) {
            return (That)SeqViewLike$class.patch(this, from2, patch2, replaced, bf);
        }

        @Override
        public <B, That> That padTo(int len, B elem, CanBuildFrom<SeqView<B, Coll>, B, That> bf) {
            return (That)SeqViewLike$class.padTo(this, len, elem, bf);
        }

        @Override
        public <B, That> That reverseMap(Function1<B, B> f, CanBuildFrom<SeqView<B, Coll>, B, That> bf) {
            return (That)SeqViewLike$class.reverseMap(this, f, bf);
        }

        @Override
        public <B, That> That updated(int index, B elem, CanBuildFrom<SeqView<B, Coll>, B, That> bf) {
            return (That)SeqViewLike$class.updated(this, index, elem, bf);
        }

        @Override
        public <B, That> That $plus$colon(B elem, CanBuildFrom<SeqView<B, Coll>, B, That> bf) {
            return (That)SeqViewLike$class.$plus$colon(this, elem, bf);
        }

        @Override
        public <B, That> That $colon$plus(B elem, CanBuildFrom<SeqView<B, Coll>, B, That> bf) {
            return (That)SeqViewLike$class.$colon$plus(this, elem, bf);
        }

        @Override
        public <B, That> That union(GenSeq<B> that, CanBuildFrom<SeqView<B, Coll>, B, That> bf) {
            return (That)SeqViewLike$class.union(this, that, bf);
        }

        @Override
        public <B> SeqView<B, Coll> diff(GenSeq<B> that) {
            return SeqViewLike$class.diff(this, that);
        }

        @Override
        public <B> SeqView<B, Coll> intersect(GenSeq<B> that) {
            return SeqViewLike$class.intersect(this, that);
        }

        @Override
        public <B> SeqView<B, Coll> sorted(Ordering<B> ord) {
            return SeqViewLike$class.sorted(this, ord);
        }

        @Override
        public SeqView<B, Coll> sortWith(Function2<B, B, Object> lt) {
            return SeqViewLike$class.sortWith(this, lt);
        }

        @Override
        public <B> SeqView<B, Coll> sortBy(Function1<B, B> f, Ordering<B> ord) {
            return SeqViewLike$class.sortBy(this, f, ord);
        }

        @Override
        public Iterator<SeqView<B, Coll>> combinations(int n) {
            return SeqViewLike$class.combinations(this, n);
        }

        @Override
        public Iterator<SeqView<B, Coll>> permutations() {
            return SeqViewLike$class.permutations(this);
        }

        @Override
        public SeqView<B, Coll> distinct() {
            return SeqViewLike$class.distinct(this);
        }

        @Override
        public String stringPrefix() {
            return SeqViewLike$class.stringPrefix(this);
        }

        @Override
        public <U> void foreach(Function1<B, U> f) {
            IterableViewLike$Transformed$class.foreach(this, f);
        }

        @Override
        public boolean isEmpty() {
            return IterableViewLike$Transformed$class.isEmpty(this);
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
        public IterableView drop(int n) {
            return IterableViewLike$class.drop(this, n);
        }

        @Override
        public IterableView take(int n) {
            return IterableViewLike$class.take(this, n);
        }

        @Override
        public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<SeqView<B, Coll>, Tuple2<A1, B>, That> bf) {
            return (That)IterableViewLike$class.zip(this, that, bf);
        }

        @Override
        public <A1, That> That zipWithIndex(CanBuildFrom<SeqView<B, Coll>, Tuple2<A1, Object>, That> bf) {
            return (That)IterableViewLike$class.zipWithIndex(this, bf);
        }

        @Override
        public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<SeqView<B, Coll>, Tuple2<A1, B>, That> bf) {
            return (That)IterableViewLike$class.zipAll(this, that, thisElem, thatElem, bf);
        }

        @Override
        public Iterator<SeqView<B, Coll>> grouped(int size2) {
            return IterableViewLike$class.grouped(this, size2);
        }

        @Override
        public Iterator<SeqView<B, Coll>> sliding(int size2, int step) {
            return IterableViewLike$class.sliding(this, size2, step);
        }

        @Override
        public Iterator<SeqView<B, Coll>> sliding(int size2) {
            return IterableViewLike$class.sliding(this, size2);
        }

        @Override
        public IterableView dropRight(int n) {
            return IterableViewLike$class.dropRight(this, n);
        }

        @Override
        public IterableView takeRight(int n) {
            return IterableViewLike$class.takeRight(this, n);
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
        public Builder<B, SeqView<B, Coll>> newBuilder() {
            return TraversableViewLike$class.newBuilder(this);
        }

        @Override
        public <B, That> That force(CanBuildFrom<Coll, B, That> bf) {
            return (That)TraversableViewLike$class.force(this, bf);
        }

        @Override
        public <B, That> That $plus$plus(GenTraversableOnce<B> xs, CanBuildFrom<SeqView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.$plus$plus(this, xs, bf);
        }

        @Override
        public <B, That> That map(Function1<B, B> f, CanBuildFrom<SeqView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.map(this, f, bf);
        }

        @Override
        public <B, That> That collect(PartialFunction<B, B> pf, CanBuildFrom<SeqView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.collect(this, pf, bf);
        }

        @Override
        public <B, That> That flatMap(Function1<B, GenTraversableOnce<B>> f, CanBuildFrom<SeqView<B, Coll>, B, That> bf) {
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
        public Tuple2<SeqView<B, Coll>, SeqView<B, Coll>> partition(Function1<B, Object> p) {
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
        public Tuple2<SeqView<B, Coll>, SeqView<B, Coll>> span(Function1<B, Object> p) {
            return TraversableViewLike$class.span(this, p);
        }

        @Override
        public Tuple2<SeqView<B, Coll>, SeqView<B, Coll>> splitAt(int n) {
            return TraversableViewLike$class.splitAt(this, n);
        }

        @Override
        public <B, That> That scanLeft(B z, Function2<B, B, B> op, CanBuildFrom<SeqView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.scanLeft(this, z, op, bf);
        }

        @Override
        public <B, That> That scanRight(B z, Function2<B, B, B> op, CanBuildFrom<SeqView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.scanRight(this, z, op, bf);
        }

        @Override
        public <K> Map<K, SeqView<B, Coll>> groupBy(Function1<B, K> f) {
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
        public Iterator<SeqView<B, Coll>> inits() {
            return TraversableViewLike$class.inits(this);
        }

        @Override
        public Iterator<SeqView<B, Coll>> tails() {
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
        public GenericCompanion<Seq> companion() {
            return Seq$class.companion(this);
        }

        @Override
        public Seq<B> seq() {
            return Seq$class.seq(this);
        }

        @Override
        public Seq<B> thisCollection() {
            return SeqLike$class.thisCollection(this);
        }

        @Override
        public Seq toCollection(Object repr) {
            return SeqLike$class.toCollection(this, repr);
        }

        @Override
        public Combiner<B, ParSeq<B>> parCombiner() {
            return SeqLike$class.parCombiner(this);
        }

        @Override
        public int lengthCompare(int len) {
            return SeqLike$class.lengthCompare(this, len);
        }

        @Override
        public int size() {
            return SeqLike$class.size(this);
        }

        @Override
        public int segmentLength(Function1<B, Object> p, int from2) {
            return SeqLike$class.segmentLength(this, p, from2);
        }

        @Override
        public int indexWhere(Function1<B, Object> p, int from2) {
            return SeqLike$class.indexWhere(this, p, from2);
        }

        @Override
        public int lastIndexWhere(Function1<B, Object> p, int end) {
            return SeqLike$class.lastIndexWhere(this, p, end);
        }

        @Override
        public Iterator<B> reverseIterator() {
            return SeqLike$class.reverseIterator(this);
        }

        @Override
        public <B> boolean startsWith(GenSeq<B> that, int offset) {
            return SeqLike$class.startsWith(this, that, offset);
        }

        @Override
        public <B> boolean endsWith(GenSeq<B> that) {
            return SeqLike$class.endsWith(this, that);
        }

        @Override
        public <B> int indexOfSlice(GenSeq<B> that) {
            return SeqLike$class.indexOfSlice(this, that);
        }

        @Override
        public <B> int indexOfSlice(GenSeq<B> that, int from2) {
            return SeqLike$class.indexOfSlice(this, that, from2);
        }

        @Override
        public <B> int lastIndexOfSlice(GenSeq<B> that) {
            return SeqLike$class.lastIndexOfSlice(this, that);
        }

        @Override
        public <B> int lastIndexOfSlice(GenSeq<B> that, int end) {
            return SeqLike$class.lastIndexOfSlice(this, that, end);
        }

        @Override
        public <B> boolean containsSlice(GenSeq<B> that) {
            return SeqLike$class.containsSlice(this, that);
        }

        @Override
        public <A1> boolean contains(A1 elem) {
            return SeqLike$class.contains(this, elem);
        }

        @Override
        public <B> boolean corresponds(GenSeq<B> that, Function2<B, B, Object> p) {
            return SeqLike$class.corresponds(this, that, p);
        }

        @Override
        public Seq<B> toSeq() {
            return SeqLike$class.toSeq(this);
        }

        @Override
        public Range indices() {
            return SeqLike$class.indices(this);
        }

        @Override
        public Object view() {
            return SeqLike$class.view(this);
        }

        @Override
        public SeqView<B, SeqView<B, Coll>> view(int from2, int until2) {
            return SeqLike$class.view(this, from2, until2);
        }

        @Override
        public boolean isDefinedAt(int idx) {
            return GenSeqLike$class.isDefinedAt(this, idx);
        }

        @Override
        public int prefixLength(Function1<B, Object> p) {
            return GenSeqLike$class.prefixLength(this, p);
        }

        @Override
        public int indexWhere(Function1<B, Object> p) {
            return GenSeqLike$class.indexWhere(this, p);
        }

        @Override
        public <B> int indexOf(B elem) {
            return GenSeqLike$class.indexOf(this, elem);
        }

        @Override
        public <B> int indexOf(B elem, int from2) {
            return GenSeqLike$class.indexOf(this, elem, from2);
        }

        @Override
        public <B> int lastIndexOf(B elem) {
            return GenSeqLike$class.lastIndexOf(this, elem);
        }

        @Override
        public <B> int lastIndexOf(B elem, int end) {
            return GenSeqLike$class.lastIndexOf(this, elem, end);
        }

        @Override
        public int lastIndexWhere(Function1<B, Object> p) {
            return GenSeqLike$class.lastIndexWhere(this, p);
        }

        @Override
        public <B> boolean startsWith(GenSeq<B> that) {
            return GenSeqLike$class.startsWith(this, that);
        }

        @Override
        public int hashCode() {
            return GenSeqLike$class.hashCode(this);
        }

        @Override
        public boolean equals(Object that) {
            return GenSeqLike$class.equals(this, that);
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
        public <B> Builder<B, Seq<B>> genericBuilder() {
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
        public boolean hasDefiniteSize() {
            return TraversableLike$class.hasDefiniteSize(this);
        }

        @Override
        public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<SeqView<B, Coll>, B, That> bf) {
            return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
        }

        @Override
        public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<SeqView<B, Coll>, B, That> bf) {
            return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
        }

        @Override
        public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<SeqView<B, Coll>, B, That> cbf) {
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

        @Override
        public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> that) {
            return PartialFunction$class.orElse(this, that);
        }

        @Override
        public <C> PartialFunction<Object, C> andThen(Function1<B, C> k) {
            return PartialFunction$class.andThen(this, k);
        }

        @Override
        public Function1<Object, Option<B>> lift() {
            return PartialFunction$class.lift(this);
        }

        @Override
        public Object applyOrElse(Object x, Function1 function1) {
            return PartialFunction$class.applyOrElse(this, x, function1);
        }

        @Override
        public <U> Function1<Object, Object> runWith(Function1<B, U> action) {
            return PartialFunction$class.runWith(this, action);
        }

        @Override
        public boolean apply$mcZD$sp(double v1) {
            return Function1$class.apply$mcZD$sp(this, v1);
        }

        @Override
        public double apply$mcDD$sp(double v1) {
            return Function1$class.apply$mcDD$sp(this, v1);
        }

        @Override
        public float apply$mcFD$sp(double v1) {
            return Function1$class.apply$mcFD$sp(this, v1);
        }

        @Override
        public int apply$mcID$sp(double v1) {
            return Function1$class.apply$mcID$sp(this, v1);
        }

        @Override
        public long apply$mcJD$sp(double v1) {
            return Function1$class.apply$mcJD$sp(this, v1);
        }

        @Override
        public void apply$mcVD$sp(double v1) {
            Function1$class.apply$mcVD$sp(this, v1);
        }

        @Override
        public boolean apply$mcZF$sp(float v1) {
            return Function1$class.apply$mcZF$sp(this, v1);
        }

        @Override
        public double apply$mcDF$sp(float v1) {
            return Function1$class.apply$mcDF$sp(this, v1);
        }

        @Override
        public float apply$mcFF$sp(float v1) {
            return Function1$class.apply$mcFF$sp(this, v1);
        }

        @Override
        public int apply$mcIF$sp(float v1) {
            return Function1$class.apply$mcIF$sp(this, v1);
        }

        @Override
        public long apply$mcJF$sp(float v1) {
            return Function1$class.apply$mcJF$sp(this, v1);
        }

        @Override
        public void apply$mcVF$sp(float v1) {
            Function1$class.apply$mcVF$sp(this, v1);
        }

        @Override
        public boolean apply$mcZI$sp(int v1) {
            return Function1$class.apply$mcZI$sp(this, v1);
        }

        @Override
        public double apply$mcDI$sp(int v1) {
            return Function1$class.apply$mcDI$sp(this, v1);
        }

        @Override
        public float apply$mcFI$sp(int v1) {
            return Function1$class.apply$mcFI$sp(this, v1);
        }

        @Override
        public int apply$mcII$sp(int v1) {
            return Function1$class.apply$mcII$sp(this, v1);
        }

        @Override
        public long apply$mcJI$sp(int v1) {
            return Function1$class.apply$mcJI$sp(this, v1);
        }

        @Override
        public void apply$mcVI$sp(int v1) {
            Function1$class.apply$mcVI$sp(this, v1);
        }

        @Override
        public boolean apply$mcZJ$sp(long v1) {
            return Function1$class.apply$mcZJ$sp(this, v1);
        }

        @Override
        public double apply$mcDJ$sp(long v1) {
            return Function1$class.apply$mcDJ$sp(this, v1);
        }

        @Override
        public float apply$mcFJ$sp(long v1) {
            return Function1$class.apply$mcFJ$sp(this, v1);
        }

        @Override
        public int apply$mcIJ$sp(long v1) {
            return Function1$class.apply$mcIJ$sp(this, v1);
        }

        @Override
        public long apply$mcJJ$sp(long v1) {
            return Function1$class.apply$mcJJ$sp(this, v1);
        }

        @Override
        public void apply$mcVJ$sp(long v1) {
            Function1$class.apply$mcVJ$sp(this, v1);
        }

        @Override
        public <A> Function1<A, B> compose(Function1<A, Object> g) {
            return Function1$class.compose(this, g);
        }

        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$AbstractTransformed$$$outer() {
            return SeqViewLike.this;
        }

        @Override
        public /* synthetic */ SeqViewLike scala$collection$SeqViewLike$Transformed$$$outer() {
            return this.scala$collection$SeqViewLike$AbstractTransformed$$$outer();
        }

        @Override
        public /* synthetic */ IterableViewLike scala$collection$IterableViewLike$Transformed$$$outer() {
            return this.scala$collection$SeqViewLike$AbstractTransformed$$$outer();
        }

        @Override
        public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Transformed$$$outer() {
            return this.scala$collection$SeqViewLike$AbstractTransformed$$$outer();
        }

        public AbstractTransformed() {
            if (SeqViewLike.this == null) {
                throw null;
            }
            Function1$class.$init$(this);
            PartialFunction$class.$init$(this);
            TraversableOnce$class.$init$(this);
            Parallelizable$class.$init$(this);
            TraversableLike$class.$init$(this);
            GenericTraversableTemplate$class.$init$(this);
            GenTraversable$class.$init$(this);
            Traversable$class.$init$(this);
            GenIterable$class.$init$(this);
            IterableLike$class.$init$(this);
            Iterable$class.$init$(this);
            GenSeqLike$class.$init$(this);
            GenSeq$class.$init$(this);
            SeqLike$class.$init$(this);
            Seq$class.$init$(this);
            ViewMkString$class.$init$(this);
            TraversableViewLike$class.$init$(this);
            IterableViewLike$class.$init$(this);
            TraversableViewLike$Transformed$class.$init$(this);
            IterableViewLike$Transformed$class.$init$(this);
            SeqViewLike$class.$init$(this);
            SeqViewLike$Transformed$class.$init$(this);
        }
    }
}

