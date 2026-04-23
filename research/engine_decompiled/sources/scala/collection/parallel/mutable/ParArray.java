/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import scala.Array$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.CustomParallelizable$class;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenSeq;
import scala.collection.GenSeq$class;
import scala.collection.GenSeqLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Iterator$class;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.SeqLike;
import scala.collection.Traversable;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.DelegatedSignalling$class;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericParTemplate$class;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Growable;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.AugmentedIterableIterator$class;
import scala.collection.parallel.AugmentedSeqIterator$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.IterableSplitter$class;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.ParIterableLike$ScanLeaf$;
import scala.collection.parallel.ParIterableLike$ScanNode$;
import scala.collection.parallel.ParIterableLike$class;
import scala.collection.parallel.ParSeq;
import scala.collection.parallel.ParSeq$class;
import scala.collection.parallel.ParSeqLike$class;
import scala.collection.parallel.PreciseSplitter;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.RemainsIterator$class;
import scala.collection.parallel.SeqSplitter;
import scala.collection.parallel.SeqSplitter$class;
import scala.collection.parallel.Task;
import scala.collection.parallel.Task$class;
import scala.collection.parallel.TaskSupport;
import scala.collection.parallel.immutable.ParMap;
import scala.collection.parallel.immutable.ParSet;
import scala.collection.parallel.mutable.ExposedArrayBuffer;
import scala.collection.parallel.mutable.ParArray$;
import scala.collection.parallel.mutable.ParArray$ParArrayIterator$;
import scala.collection.parallel.mutable.ParIterable;
import scala.collection.parallel.mutable.ParIterable$class;
import scala.collection.parallel.mutable.ResizableParArrayCombiner;
import scala.collection.parallel.mutable.UnrolledParArrayCombiner;
import scala.collection.parallel.package$;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing$;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u001dmg!B\u0001\u0003\u0001-Y#\u0001\u0003)be\u0006\u0013(/Y=\u000b\u0005\r!\u0011aB7vi\u0006\u0014G.\u001a\u0006\u0003\u000b\u0019\t\u0001\u0002]1sC2dW\r\u001c\u0006\u0003\u000f!\t!bY8mY\u0016\u001cG/[8o\u0015\u0005I\u0011!B:dC2\f7\u0001A\u000b\u0003\u0019]\u0019b\u0001A\u0007\u0012A\u001d\n\u0004C\u0001\b\u0010\u001b\u0005A\u0011B\u0001\t\t\u0005\u0019\te.\u001f*fMB\u0019!cE\u000b\u000e\u0003\tI!\u0001\u0006\u0002\u0003\rA\u000b'oU3r!\t1r\u0003\u0004\u0001\u0005\u000ba\u0001!\u0019A\r\u0003\u0003Q\u000b\"AG\u000f\u0011\u00059Y\u0012B\u0001\u000f\t\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0004\u0010\n\u0005}A!aA!osB!\u0011\u0005J\u000b'\u001b\u0005\u0011#BA\u0012\u0007\u0003\u001d9WM\\3sS\u000eL!!\n\u0012\u0003%\u001d+g.\u001a:jGB\u000b'\u000fV3na2\fG/\u001a\t\u0003%\u0001\u0001R\u0001K\u0015\u0016W1j\u0011\u0001B\u0005\u0003U\u0011\u0011!\u0002U1s'\u0016\fH*[6f!\r\u0011\u0002!\u0006\t\u0004[=*R\"\u0001\u0018\u000b\u0005\r1\u0011B\u0001\u0019/\u0005!\t%O]1z'\u0016\f\bC\u0001\b3\u0013\t\u0019\u0004B\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0003\u00056\u0001\t\u0015\r\u0011\"\u00017\u0003!\t'O]1zg\u0016\fX#\u0001\u0017\t\u0011a\u0002!\u0011!Q\u0001\n1\n\u0011\"\u0019:sCf\u001cX-\u001d\u0011\t\ri\u0002A\u0011\u0001\u0002<\u0003\u0019a\u0014N\\5u}Q\u00111\u0006\u0010\u0005\u0006ke\u0002\r\u0001\f\u0005\b}\u0001\u0001\r\u0011\"\u0003@\u0003\u0015\t'O]1z+\u0005\u0001\u0005c\u0001\bB;%\u0011!\t\u0003\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\b\t\u0002\u0001\r\u0011\"\u0003F\u0003%\t'O]1z?\u0012*\u0017\u000f\u0006\u0002G\u0013B\u0011abR\u0005\u0003\u0011\"\u0011A!\u00168ji\"9!jQA\u0001\u0002\u0004\u0001\u0015a\u0001=%c!1A\n\u0001Q!\n\u0001\u000ba!\u0019:sCf\u0004\u0003FA&O!\tqq*\u0003\u0002Q\u0011\tIAO]1og&,g\u000e\u001e\u0005\u0006%\u0002!\teU\u0001\nG>l\u0007/\u00198j_:,\u0012\u0001\u0016\n\u0004+^Sf\u0001\u0002,\u0001\u0001Q\u0013A\u0002\u0010:fM&tW-\\3oiz\u00022!\t-'\u0013\tI&E\u0001\tHK:,'/[2D_6\u0004\u0018M\\5p]B\u0019\u0011e\u0017\u0014\n\u0005q\u0013#aE$f]\u0016\u0014\u0018n\u0019)be\u000e{W\u000e]1oS>t\u0007\"\u0002\u001e\u0001\t\u0003qFCA\u0016`\u0011\u0015\u0001W\f1\u0001b\u0003\t\u0019(\u0010\u0005\u0002\u000fE&\u00111\r\u0003\u0002\u0004\u0013:$\b\"B3\u0001\t\u00031\u0017!B1qa2LHCA\u000bh\u0011\u0015AG\r1\u0001b\u0003\u0005I\u0007\"\u00026\u0001\t\u0003Y\u0017AB;qI\u0006$X\rF\u0002GY6DQ\u0001[5A\u0002\u0005DQA\\5A\u0002U\tA!\u001a7f[\")\u0001\u000f\u0001C\u0001c\u00061A.\u001a8hi\",\u0012!\u0019\u0005\u0006g\u0002!\tEN\u0001\u0004g\u0016\f\bBB;\u0001\t#!a/\u0001\u0005ta2LG\u000f^3s+\u00059\bC\u0001=z\u001b\u0005\u0001a\u0001\u0002>\u0001\u0001m\u0014\u0001\u0003U1s\u0003J\u0014\u0018-_%uKJ\fGo\u001c:\u0014\u0007elA\u0010E\u0002){VI!A \u0003\u0003\u0017M+\u0017o\u00159mSR$XM\u001d\u0005\tQf\u0014\t\u0019!C\u0001c\"Q\u00111A=\u0003\u0002\u0004%\t!!\u0002\u0002\u000b%|F%Z9\u0015\u0007\u0019\u000b9\u0001\u0003\u0005K\u0003\u0003\t\t\u00111\u0001b\u0011%\tY!\u001fB\u0001B\u0003&\u0011-\u0001\u0002jA!I\u0011qB=\u0003\u0006\u0004%\t!]\u0001\u0006k:$\u0018\u000e\u001c\u0005\n\u0003'I(\u0011!Q\u0001\n\u0005\fa!\u001e8uS2\u0004\u0003\"CA\fs\n\u0015\r\u0011\"\u0001@\u0003\r\t'O\u001d\u0005\n\u00037I(\u0011!Q\u0001\n\u0001\u000bA!\u0019:sA!1!(\u001fC\u0001\u0003?!ra^A\u0011\u0003G\t)\u0003\u0003\u0005i\u0003;\u0001\n\u00111\u0001b\u0011%\ty!!\b\u0011\u0002\u0003\u0007\u0011\rC\u0005\u0002\u0018\u0005u\u0001\u0013!a\u0001\u0001\"9\u0011\u0011F=\u0005\u0002\u0005-\u0012a\u00025bg:+\u0007\u0010^\u000b\u0003\u0003[\u00012ADA\u0018\u0013\r\t\t\u0004\u0003\u0002\b\u0005>|G.Z1o\u0011\u001d\t)$\u001fC\u0001\u0003o\tAA\\3yiR\tQ\u0003\u0003\u0004\u0002<e$\t!]\u0001\ne\u0016l\u0017-\u001b8j]\u001eDa!a\u0010z\t\u00031\u0018a\u00013va\"9\u00111I=\u0005\u0002\u0005\u0015\u0013A\u00029ta2LG\u000f\u0006\u0003\u0002H\u0005U\u0003#BA%\u0003\u001f:hb\u0001\b\u0002L%\u0019\u0011Q\n\u0005\u0002\u000fA\f7m[1hK&!\u0011\u0011KA*\u0005\r\u0019V-\u001d\u0006\u0004\u0003\u001bB\u0001\u0002CA,\u0003\u0003\u0002\r!!\u0017\u0002\u001fML'0Z:J]\u000e|W\u000e\u001d7fi\u0016\u0004BADA.C&\u0019\u0011Q\f\u0005\u0003\u0015q\u0012X\r]3bi\u0016$g\bC\u0004\u0002be$\t%a\u0019\u0002\u000bM\u0004H.\u001b;\u0016\u0005\u0005\u001d\u0003bBA4s\u0012\u0005\u0013\u0011N\u0001\ti>\u001cFO]5oOR\u0011\u00111\u000e\t\u0005\u0003[\n9(\u0004\u0002\u0002p)!\u0011\u0011OA:\u0003\u0011a\u0017M\\4\u000b\u0005\u0005U\u0014\u0001\u00026bm\u0006LA!!\u001f\u0002p\t11\u000b\u001e:j]\u001eDq!! z\t\u0003\ny(A\u0004g_J,\u0017m\u00195\u0016\t\u0005\u0005\u0015q\u0012\u000b\u0004\r\u0006\r\u0005\u0002CAC\u0003w\u0002\r!a\"\u0002\u0003\u0019\u0004bADAE+\u00055\u0015bAAF\u0011\tIa)\u001e8di&|g.\r\t\u0004-\u0005=EaBAI\u0003w\u0012\r!\u0007\u0002\u0002+\"9\u0011QS=\u0005\n\u0005]\u0015!\u00044pe\u0016\f7\r[0rk&\u001c7.\u0006\u0003\u0002\u001a\u0006\u0005F#\u0003$\u0002\u001c\u0006\r\u0016qUAV\u0011!\t))a%A\u0002\u0005u\u0005C\u0002\b\u0002\nV\ty\nE\u0002\u0017\u0003C#q!!%\u0002\u0014\n\u0007\u0011\u0004C\u0004\u0002&\u0006M\u0005\u0019\u0001!\u0002\u0003\u0005Dq!!+\u0002\u0014\u0002\u0007\u0011-\u0001\u0003oi&d\u0007bBAW\u0003'\u0003\r!Y\u0001\u0005MJ|W\u000eC\u0004\u00022f$\t%a-\u0002\u000b\r|WO\u001c;\u0015\u0007\u0005\f)\f\u0003\u0005\u00028\u0006=\u0006\u0019AA]\u0003\u0005\u0001\bC\u0002\b\u0002\nV\ti\u0003C\u0004\u0002>f$I!a0\u0002\u0017\r|WO\u001c;`cVL7m\u001b\u000b\nC\u0006\u0005\u00171YAc\u0003\u000fD\u0001\"a.\u0002<\u0002\u0007\u0011\u0011\u0018\u0005\b\u0003K\u000bY\f1\u0001A\u0011\u001d\tI+a/A\u0002\u0005Dq!!,\u0002<\u0002\u0007\u0011\rC\u0004\u0002Lf$\t%!4\u0002\u0011\u0019|G\u000e\u001a'fMR,B!a4\u0002VR!\u0011\u0011[Ar)\u0011\t\u0019.!7\u0011\u0007Y\t)\u000eB\u0004\u0002X\u0006%'\u0019A\r\u0003\u0003MC\u0001\"a7\u0002J\u0002\u0007\u0011Q\\\u0001\u0003_B\u0004\u0002BDAp\u0003',\u00121[\u0005\u0004\u0003CD!!\u0003$v]\u000e$\u0018n\u001c83\u0011!\t)/!3A\u0002\u0005M\u0017!\u0001>\t\u000f\u0005%\u0018\u0010\"\u0003\u0002l\u0006qam\u001c7e\u0019\u00164GoX9vS\u000e\\W\u0003BAw\u0003c$\"\"a<\u0002t\u0006U\u0018q_A~!\r1\u0012\u0011\u001f\u0003\b\u0003/\f9O1\u0001\u001a\u0011\u001d\t)+a:A\u0002\u0001Cq!!+\u0002h\u0002\u0007\u0011\r\u0003\u0005\u0002\\\u0006\u001d\b\u0019AA}!!q\u0011q\\Ax+\u0005=\b\u0002CAs\u0003O\u0004\r!a<\t\u000f\u0005}\u0018\u0010\"\u0011\u0003\u0002\u0005!am\u001c7e+\u0011\u0011\u0019A!\u0003\u0015\t\t\u0015!\u0011\u0003\u000b\u0005\u0005\u000f\u0011i\u0001E\u0002\u0017\u0005\u0013!\u0001\"!%\u0002~\n\u0007!1B\t\u0003+uA\u0001\"a7\u0002~\u0002\u0007!q\u0002\t\n\u001d\u0005}'q\u0001B\u0004\u0005\u000fA\u0001\"!:\u0002~\u0002\u0007!q\u0001\u0005\b\u0005+IH\u0011\tB\f\u0003%\twm\u001a:fO\u0006$X-\u0006\u0003\u0003\u001a\t}A\u0003\u0002B\u000e\u0005[!bA!\b\u0003\"\t\u001d\u0002c\u0001\f\u0003 \u00119\u0011q\u001bB\n\u0005\u0004I\u0002\u0002\u0003B\u0012\u0005'\u0001\rA!\n\u0002\u000bM,\u0017o\u001c9\u0011\u00119\tyN!\b\u0016\u0005;A\u0001B!\u000b\u0003\u0014\u0001\u0007!1F\u0001\u0007G>l'm\u001c9\u0011\u00139\tyN!\b\u0003\u001e\tu\u0001\"CAs\u0005'!\t\u0019\u0001B\u0018!\u0015q!\u0011\u0007B\u000f\u0013\r\u0011\u0019\u0004\u0003\u0002\ty\tLh.Y7f}!9!qG=\u0005B\te\u0012aA:v[V!!1\bB )\u0011\u0011iD!\u0011\u0011\u0007Y\u0011y\u0004\u0002\u0005\u0002\u0012\nU\"\u0019\u0001B\u0006\u0011!\u0011\u0019E!\u000eA\u0004\t\u0015\u0013a\u00018v[B1\u0011\u0011\nB$\u0005{IAA!\u0013\u0002T\t9a*^7fe&\u001c\u0007b\u0002B's\u0012%!qJ\u0001\ngVlw,];jG.,BA!\u0015\u0003VQa!1\u000bB,\u00057\u0012iFa\u0018\u0003bA\u0019aC!\u0016\u0005\u0011\u0005E%1\nb\u0001\u0005\u0017A\u0001Ba\u0011\u0003L\u0001\u0007!\u0011\f\t\u0007\u0003\u0013\u00129Ea\u0015\t\u000f\u0005\u0015&1\na\u0001\u0001\"9\u0011\u0011\u0016B&\u0001\u0004\t\u0007bBAW\u0005\u0017\u0002\r!\u0019\u0005\t\u0005G\u0012Y\u00051\u0001\u0003T\u0005!!0\u001a:p\u0011\u001d\u00119'\u001fC!\u0005S\nq\u0001\u001d:pIV\u001cG/\u0006\u0003\u0003l\t=D\u0003\u0002B7\u0005c\u00022A\u0006B8\t!\t\tJ!\u001aC\u0002\t-\u0001\u0002\u0003B\"\u0005K\u0002\u001dAa\u001d\u0011\r\u0005%#q\tB7\u0011\u001d\u00119(\u001fC\u0005\u0005s\nQ\u0002\u001d:pIV\u001cGoX9vS\u000e\\W\u0003\u0002B>\u0005\u007f\"BB! \u0003\u0002\n\u0015%q\u0011BE\u0005\u0017\u00032A\u0006B@\t!\t\tJ!\u001eC\u0002\t-\u0001\u0002\u0003B\"\u0005k\u0002\rAa!\u0011\r\u0005%#q\tB?\u0011\u001d\t)K!\u001eA\u0002\u0001Cq!!+\u0003v\u0001\u0007\u0011\rC\u0004\u0002.\nU\u0004\u0019A1\t\u0011\t5%Q\u000fa\u0001\u0005{\n1a\u001c8f\u0011\u001d\u0011\t*\u001fC!\u0005'\u000baAZ8sC2dG\u0003BA\u0017\u0005+C\u0001\"a.\u0003\u0010\u0002\u0007\u0011\u0011\u0018\u0005\b\u00053KH\u0011\u0002BN\u000311wN]1mY~\u000bX/[2l))\tiC!(\u0003 \n\u0005&Q\u0015\u0005\t\u0003o\u00139\n1\u0001\u0002:\"9\u0011Q\u0015BL\u0001\u0004\u0001\u0005b\u0002BR\u0005/\u0003\r!Y\u0001\n]\u0016DH/\u001e8uS2DqAa*\u0003\u0018\u0002\u0007\u0011-A\u0003ti\u0006\u0014H\u000fC\u0004\u0003,f$\tE!,\u0002\r\u0015D\u0018n\u001d;t)\u0011\tiCa,\t\u0011\u0005]&\u0011\u0016a\u0001\u0003sCqAa-z\t\u0013\u0011),\u0001\u0007fq&\u001cHo]0rk&\u001c7\u000e\u0006\u0006\u0002.\t]&\u0011\u0018B^\u0005{C\u0001\"a.\u00032\u0002\u0007\u0011\u0011\u0018\u0005\b\u0003K\u0013\t\f1\u0001A\u0011\u001d\u0011\u0019K!-A\u0002\u0005DqAa*\u00032\u0002\u0007\u0011\rC\u0004\u0003Bf$\tEa1\u0002\t\u0019Lg\u000e\u001a\u000b\u0005\u0005\u000b\u0014Y\r\u0005\u0003\u000f\u0005\u000f,\u0012b\u0001Be\u0011\t1q\n\u001d;j_:D\u0001\"a.\u0003@\u0002\u0007\u0011\u0011\u0018\u0005\b\u0005\u001fLH\u0011\u0002Bi\u0003)1\u0017N\u001c3`cVL7m\u001b\u000b\u000b\u0005\u000b\u0014\u0019N!6\u0003X\ne\u0007\u0002CA\\\u0005\u001b\u0004\r!!/\t\u000f\u0005\u0015&Q\u001aa\u0001\u0001\"9!1\u0015Bg\u0001\u0004\t\u0007b\u0002BT\u0005\u001b\u0004\r!\u0019\u0005\b\u0005;LH\u0011\tBp\u0003\u0011!'o\u001c9\u0015\u0007]\u0014\t\u000fC\u0004\u0003d\nm\u0007\u0019A1\u0002\u00039DqAa:z\t\u0003\u0012I/A\u0006d_BLHk\\!se\u0006LX\u0003\u0002Bv\u0005g$rA\u0012Bw\u0005k\u00149\u0010C\u0004?\u0005K\u0004\rAa<\u0011\t9\t%\u0011\u001f\t\u0004-\tMH\u0001CAI\u0005K\u0014\rAa\u0003\t\u000f\u00055&Q\u001da\u0001C\"9!\u0011 Bs\u0001\u0004\t\u0017a\u00017f]\"9!Q`=\u0005B\t}\u0018\u0001\u00049sK\u001aL\u0007\u0010T3oORDGcA1\u0004\u0002!A11\u0001B~\u0001\u0004\tI,\u0001\u0003qe\u0016$\u0007bBB\u0004s\u0012%1\u0011B\u0001\u0013aJ,g-\u001b=MK:<G\u000f[0rk&\u001c7\u000eF\u0005b\u0007\u0017\u0019iaa\u0004\u0004\u0012!A11AB\u0003\u0001\u0004\tI\fC\u0004\u0002&\u000e\u0015\u0001\u0019\u0001!\t\u000f\u0005%6Q\u0001a\u0001C\"911CB\u0003\u0001\u0004\t\u0017\u0001C:uCJ$\bo\\:\t\u000f\r]\u0011\u0010\"\u0011\u0004\u001a\u0005Q\u0011N\u001c3fq^CWM]3\u0015\u0007\u0005\u001cY\u0002\u0003\u0005\u0004\u0004\rU\u0001\u0019AA]\u0011\u001d\u0019y\"\u001fC\u0005\u0007C\t\u0001#\u001b8eKb<\u0006.\u001a:f?F,\u0018nY6\u0015\u0013\u0005\u001c\u0019c!\n\u0004(\r%\u0002\u0002CB\u0002\u0007;\u0001\r!!/\t\u000f\u0005\u00156Q\u0004a\u0001\u0001\"9\u0011\u0011VB\u000f\u0001\u0004\t\u0007bBAW\u0007;\u0001\r!\u0019\u0005\b\u0007[IH\u0011IB\u0018\u00039a\u0017m\u001d;J]\u0012,\u0007p\u00165fe\u0016$2!YB\u0019\u0011!\u0019\u0019aa\u000bA\u0002\u0005e\u0006bBB\u001bs\u0012%1qG\u0001\u0015Y\u0006\u001cH/\u00138eKb<\u0006.\u001a:f?F,\u0018nY6\u0015\u0013\u0005\u001cIda\u000f\u0004>\r}\u0002\u0002CB\u0002\u0007g\u0001\r!!/\t\u000f\u0005\u001561\u0007a\u0001\u0001\"9\u0011QVB\u001a\u0001\u0004\t\u0007bBAU\u0007g\u0001\r!\u0019\u0005\b\u0007\u0007JH\u0011IB#\u00031\u0019\u0018-\\3FY\u0016lWM\u001c;t)\u0011\tica\u0012\t\u0011\r%3\u0011\ta\u0001\u0007\u0017\nA\u0001\u001e5biB\"1QJB+!\u0019\tIea\u0014\u0004T%!1\u0011KA*\u0005!IE/\u001a:bi>\u0014\bc\u0001\f\u0004V\u0011Y1qKB$\u0003\u0003\u0005\tQ!\u0001\u001a\u0005\ryF%\r\u0005\b\u00077JH\u0011IB/\u00031i\u0017\r\u001d\u001ad_6\u0014\u0017N\\3s+\u0019\u0019yf!\u001b\u0004nQ11\u0011MB9\u0007k\u0002r\u0001KB2\u0007O\u001aY'C\u0002\u0004f\u0011\u0011\u0001bQ8nE&tWM\u001d\t\u0004-\r%DaBAl\u00073\u0012\r!\u0007\t\u0004-\r5DaBB8\u00073\u0012\r!\u0007\u0002\u0005)\"\fG\u000f\u0003\u0005\u0002\u0006\u000ee\u0003\u0019AB:!\u0019q\u0011\u0011R\u000b\u0004h!A1qOB-\u0001\u0004\u0019\t'\u0001\u0002dE\"911P=\u0005\n\ru\u0014AE7baJ\u001aw.\u001c2j]\u0016\u0014x,];jG.,baa \u0004\b\u000eUEc\u0003$\u0004\u0002\u000e%51RBL\u00073C\u0001\"!\"\u0004z\u0001\u000711\u0011\t\u0007\u001d\u0005%Uc!\"\u0011\u0007Y\u00199\tB\u0004\u0002X\u000ee$\u0019A\r\t\u000f\u0005\u00156\u0011\u0010a\u0001\u0001\"A1qOB=\u0001\u0004\u0019i\tE\u0004.\u0007\u001f\u001b)ia%\n\u0007\rEeFA\u0004Ck&dG-\u001a:\u0011\u0007Y\u0019)\nB\u0004\u0004p\re$\u0019A\r\t\u000f\u0005%6\u0011\u0010a\u0001C\"9\u0011QVB=\u0001\u0004\t\u0007bBBOs\u0012\u00053qT\u0001\u0011G>dG.Z2ue\r|WNY5oKJ,ba!)\u0004(\u000e-FCBBR\u0007[\u001b9\fE\u0004)\u0007G\u001a)k!+\u0011\u0007Y\u00199\u000bB\u0004\u0002X\u000em%\u0019A\r\u0011\u0007Y\u0019Y\u000bB\u0004\u0004p\rm%\u0019A\r\t\u0011\r=61\u0014a\u0001\u0007c\u000b!\u0001\u001d4\u0011\r9\u0019\u0019,FBS\u0013\r\u0019)\f\u0003\u0002\u0010!\u0006\u0014H/[1m\rVt7\r^5p]\"A1qOBN\u0001\u0004\u0019\u0019\u000bC\u0004\u0004<f$Ia!0\u0002-\r|G\u000e\\3diJ\u001aw.\u001c2j]\u0016\u0014x,];jG.,baa0\u0004H\u000eEGc\u0003$\u0004B\u000e%71ZBj\u0007+D\u0001ba,\u0004:\u0002\u000711\u0019\t\u0007\u001d\rMVc!2\u0011\u0007Y\u00199\rB\u0004\u0002X\u000ee&\u0019A\r\t\u000f\u0005\u00156\u0011\u0018a\u0001\u0001\"A1qOB]\u0001\u0004\u0019i\rE\u0004.\u0007\u001f\u001b)ma4\u0011\u0007Y\u0019\t\u000eB\u0004\u0004p\re&\u0019A\r\t\u000f\u0005%6\u0011\u0018a\u0001C\"9\u0011QVB]\u0001\u0004\t\u0007bBBms\u0012\u000531\\\u0001\u0011M2\fG/\\1qe\r|WNY5oKJ,ba!8\u0004d\u000e\u001dHCBBp\u0007S\u001c)\u0010E\u0004)\u0007G\u001a\to!:\u0011\u0007Y\u0019\u0019\u000fB\u0004\u0002X\u000e]'\u0019A\r\u0011\u0007Y\u00199\u000fB\u0004\u0004p\r]'\u0019A\r\t\u0011\u0005\u00155q\u001ba\u0001\u0007W\u0004bADAE+\r5\bCBBx\u0007c\u001c\t/D\u0001\u0007\u0013\r\u0019\u0019P\u0002\u0002\u0013\u000f\u0016tGK]1wKJ\u001c\u0018M\u00197f\u001f:\u001cW\r\u0003\u0005\u0004x\r]\u0007\u0019ABp\u0011\u001d\u0019I0\u001fC!\u0007w\fqBZ5mi\u0016\u0014(gY8nE&tWM]\u000b\u0007\u0007{$\u0019\u0001b\u0002\u0015\r\r}H1\u0002C\u0007!\u001dA31\rC\u0001\t\u000b\u00012A\u0006C\u0002\t!\t\tja>C\u0002\t-\u0001c\u0001\f\u0005\b\u00119A\u0011BB|\u0005\u0004I\"\u0001\u0002+iSND\u0001ba\u0001\u0004x\u0002\u0007\u0011\u0011\u0018\u0005\t\u0007o\u001a9\u00101\u0001\u0004\u0000\"9A\u0011C=\u0005\n\u0011M\u0011!\u00064jYR,'OM2p[\nLg.\u001a:`cVL7m[\u000b\u0007\t+!y\u0002b\t\u0015\u0017\u0019#9\u0002\"\u0007\u0005&\u0011\u001dB\u0011\u0006\u0005\t\u0007\u0007!y\u00011\u0001\u0002:\"A1q\u000fC\b\u0001\u0004!Y\u0002E\u0004.\u0007\u001f#i\u0002\"\t\u0011\u0007Y!y\u0002\u0002\u0005\u0002\u0012\u0012=!\u0019\u0001B\u0006!\r1B1\u0005\u0003\b\t\u0013!yA1\u0001\u001a\u0011\u001d\t)\u000bb\u0004A\u0002\u0001Cq!!+\u0005\u0010\u0001\u0007\u0011\rC\u0004\u0002.\u0012=\u0001\u0019A1\t\u000f\u00115\u0012\u0010\"\u0011\u00050\u0005\u0011b-\u001b7uKJtu\u000e\u001e\u001ad_6\u0014\u0017N\\3s+\u0019!\t\u0004b\u000e\u0005<Q1A1\u0007C\u001f\t\u007f\u0001r\u0001KB2\tk!I\u0004E\u0002\u0017\to!\u0001\"!%\u0005,\t\u0007!1\u0002\t\u0004-\u0011mBa\u0002C\u0005\tW\u0011\r!\u0007\u0005\t\u0007\u0007!Y\u00031\u0001\u0002:\"A1q\u000fC\u0016\u0001\u0004!\u0019\u0004C\u0004\u0005De$I\u0001\"\u0012\u00021\u0019LG\u000e^3s\u001d>$(gY8nE&tWM]0rk&\u001c7.\u0006\u0004\u0005H\u0011ECQ\u000b\u000b\f\r\u0012%C1\nC,\t3\"Y\u0006\u0003\u0005\u0004\u0004\u0011\u0005\u0003\u0019AA]\u0011!\u00199\b\"\u0011A\u0002\u00115\u0003cB\u0017\u0004\u0010\u0012=C1\u000b\t\u0004-\u0011EC\u0001CAI\t\u0003\u0012\rAa\u0003\u0011\u0007Y!)\u0006B\u0004\u0005\n\u0011\u0005#\u0019A\r\t\u000f\u0005\u0015F\u0011\ta\u0001\u0001\"9\u0011\u0011\u0016C!\u0001\u0004\t\u0007bBAW\t\u0003\u0002\r!\u0019\u0005\b\t?JH\u0011\tC1\u00031\u0019w\u000e]=3EVLG\u000eZ3s+!!\u0019\u0007\"\u001d\u0005v\u0011\u001dD\u0003\u0002C3\ts\u00022A\u0006C4\t!!I\u0007\"\u0018C\u0002\u0011-$a\u0001\"mIF\u0019!\u0004\"\u001c\u0011\u000f5\u001ay\tb\u001c\u0005tA\u0019a\u0003\"\u001d\u0005\u0011\u0005EEQ\fb\u0001\u0005\u0017\u00012A\u0006C;\t\u001d!9\b\"\u0018C\u0002e\u0011AaQ8mY\"A1q\u000fC/\u0001\u0004!)\u0007C\u0004\u0005~e$I\u0001b \u0002%\r|\u0007/\u001f\u001ack&dG-\u001a:`cVL7m[\u000b\u0007\t\u0003#Y\tb$\u0015\u0013\u0019#\u0019\t\"%\u0005\u0014\u0012U\u0005\u0002\u0003CC\tw\u0002\r\u0001b\"\u0002\u0003\t\u0004r!LBH\t\u0013#i\tE\u0002\u0017\t\u0017#\u0001\"!%\u0005|\t\u0007!1\u0002\t\u0004-\u0011=Ea\u0002C<\tw\u0012\r!\u0007\u0005\b\u0003K#Y\b1\u0001A\u0011\u001d\tI\u000bb\u001fA\u0002\u0005Dq!!,\u0005|\u0001\u0007\u0011\rC\u0004\u0005\u001af$\t\u0005b'\u0002'A\f'\u000f^5uS>t'gY8nE&tWM]:\u0016\r\u0011uE\u0011\u0016CW)!!y\nb,\u00052\u0012U\u0006c\u0002\b\u0005\"\u0012\u0015FQU\u0005\u0004\tGC!A\u0002+va2,'\u0007E\u0004)\u0007G\"9\u000bb+\u0011\u0007Y!I\u000b\u0002\u0005\u0002\u0012\u0012]%\u0019\u0001B\u0006!\r1BQ\u0016\u0003\b\t\u0013!9J1\u0001\u001a\u0011!\u0019\u0019\u0001b&A\u0002\u0005e\u0006\u0002\u0003CZ\t/\u0003\r\u0001\"*\u0002\u000b\t$(/^3\t\u0011\u0011]Fq\u0013a\u0001\tK\u000baA\u00194bYN,\u0007b\u0002C^s\u0012%AQX\u0001\u001aa\u0006\u0014H/\u001b;j_:\u00144m\\7cS:,'o]0rk&\u001c7.\u0006\u0004\u0005@\u0012%GQ\u001a\u000b\u000e\r\u0012\u0005G1\u0019Ch\t#$\u0019\u000e\"6\t\u0011\u0005]F\u0011\u0018a\u0001\u0003sC\u0001\u0002b-\u0005:\u0002\u0007AQ\u0019\t\b[\r=Eq\u0019Cf!\r1B\u0011\u001a\u0003\t\u0003##IL1\u0001\u0003\fA\u0019a\u0003\"4\u0005\u000f\u0011%A\u0011\u0018b\u00013!AAq\u0017C]\u0001\u0004!)\rC\u0004\u0002&\u0012e\u0006\u0019\u0001!\t\u000f\u0005%F\u0011\u0018a\u0001C\"9\u0011Q\u0016C]\u0001\u0004\t\u0007b\u0002Cms\u0012\u0005C1\\\u0001\u000ei\u0006\\WMM2p[\nLg.\u001a:\u0016\r\u0011uG1\u001dCt)\u0019!y\u000e\";\u0005lB9\u0001fa\u0019\u0005b\u0012\u0015\bc\u0001\f\u0005d\u0012A\u0011\u0011\u0013Cl\u0005\u0004\u0011Y\u0001E\u0002\u0017\tO$q\u0001\"\u0003\u0005X\n\u0007\u0011\u0004C\u0004\u0003d\u0012]\u0007\u0019A1\t\u0011\r]Dq\u001ba\u0001\t?Dq\u0001b<z\t\u0003\"\t0A\u0007ee>\u0004(gY8nE&tWM]\u000b\u0007\tg$I\u0010\"@\u0015\r\u0011UHq`C\u0001!\u001dA31\rC|\tw\u00042A\u0006C}\t!\t\t\n\"<C\u0002\t-\u0001c\u0001\f\u0005~\u00129A\u0011\u0002Cw\u0005\u0004I\u0002b\u0002Br\t[\u0004\r!\u0019\u0005\t\u0007o\"i\u000f1\u0001\u0005v\"9QQA=\u0005B\u0015\u001d\u0011\u0001\u0005:fm\u0016\u00148/\u001a\u001ad_6\u0014\u0017N\\3s+\u0019)I!b\u0004\u0006\u0014Q!Q1BC\u000b!\u001dA31MC\u0007\u000b#\u00012AFC\b\t!\t\t*b\u0001C\u0002\t-\u0001c\u0001\f\u0006\u0014\u00119A\u0011BC\u0002\u0005\u0004I\u0002\u0002CB<\u000b\u0007\u0001\r!b\u0003\t\u000f\u0015e\u0011\u0010\"\u0003\u0006\u001c\u00051\"/\u001a<feN,'gY8nE&tWM]0rk&\u001c7\u000eF\u0006G\u000b;)\t#b\t\u0006(\u0015-\u0002bBC\u0010\u000b/\u0001\r\u0001Q\u0001\u0005i\u0006\u0014x\rC\u0004\u0002&\u0016]\u0001\u0019\u0001!\t\u000f\u0015\u0015Rq\u0003a\u0001C\u0006AA/\u0019:hMJ|W\u000eC\u0004\u0006*\u0015]\u0001\u0019A1\u0002\u000fM\u00148M\u001a:p[\"9QQFC\f\u0001\u0004\t\u0017\u0001C:sGVtG/\u001b7\t\u000f\u0015E\u0012\u0010\"\u0011\u00064\u0005Y1oY1o)>\f%O]1z+\u0019))$b\u000f\u0006JQIa)b\u000e\u0006>\u0015\u0005Sq\n\u0005\t\u0003K,y\u00031\u0001\u0006:A\u0019a#b\u000f\u0005\u0011\u0005EUq\u0006b\u0001\u0005\u0017A\u0001\"a7\u00060\u0001\u0007Qq\b\t\n\u001d\u0005}W\u0011HC\u001d\u000bsA\u0001\"b\u0011\u00060\u0001\u0007QQI\u0001\bI\u0016\u001cH/\u0019:s!\u0011q\u0011)b\u0012\u0011\u0007Y)I\u0005\u0002\u0005\u0006L\u0015=\"\u0019AC'\u0005\u0005\t\u0015cAC\u001d;!9\u0011QVC\u0018\u0001\u0004\t\u0007bBC*s\u0012EQQK\u0001\u0012g\u000e\fg\u000eV8BeJ\f\u0017pX9vS\u000e\\W\u0003BC,\u000bK\"rBRC-\u000b;*y&b\u001a\u0006j\u0015-Tq\u000e\u0005\b\u000b7*\t\u00061\u0001A\u0003\u0019\u0019(oY1se\"9Q1IC)\u0001\u0004\u0001\u0005\u0002CAn\u000b#\u0002\r!\"\u0019\u0011\u00139\ty.b\u0019\u0006d\u0015\r\u0004c\u0001\f\u0006f\u00119\u0011\u0011SC)\u0005\u0004I\u0002\u0002CAs\u000b#\u0002\r!b\u0019\t\u000f\u0015%R\u0011\u000ba\u0001C\"9QQNC)\u0001\u0004\t\u0017aB:sG:$\u0018\u000e\u001c\u0005\b\u000bc*\t\u00061\u0001b\u0003!!Wm\u001d;ge>l\u0007BDC;sB\u0005\u0019\u0011!A\u0005\n\u0015]TqQ\u0001\u0017gV\u0004XM\u001d\u0013sKZ,'o]33G>l'-\u001b8feV1Q\u0011PC@\u000b\u0007#B!b\u001f\u0006\u0006B9\u0001fa\u0019\u0006~\u0015\u0005\u0005c\u0001\f\u0006\u0000\u0011A\u0011\u0011SC:\u0005\u0004\u0011Y\u0001E\u0002\u0017\u000b\u0007#q\u0001\"\u0003\u0006t\t\u0007\u0011\u0004\u0003\u0005\u0004x\u0015M\u0004\u0019AC>\u0013\u0011))!\"#\n\u0007\u0015-EA\u0001\u000bBk\u001elWM\u001c;fIN+\u0017/\u0013;fe\u0006$xN]\u0004\n\u000b\u001f\u0003\u0011\u0011!E\u0001\u000b#\u000b\u0001\u0003U1s\u0003J\u0014\u0018-_%uKJ\fGo\u001c:\u0011\u0007a,\u0019J\u0002\u0005{\u0001\u0005\u0005\t\u0012ACK'\r)\u0019*\u0004\u0005\bu\u0015ME\u0011ACM)\t)\t\n\u0003\u0006\u0006\u001e\u0016M\u0015\u0013!C\u0001\u000b?\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\nTCACQU\r\tW1U\u0016\u0003\u000bK\u0003B!b*\u000626\u0011Q\u0011\u0016\u0006\u0005\u000bW+i+A\u0005v]\u000eDWmY6fI*\u0019Qq\u0016\u0005\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u00064\u0016%&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\"QQqWCJ#\u0003%\t!b(\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00133\u0011))Y,b%\u0012\u0002\u0013\u0005QQX\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0015}&f\u0001!\u0006$\"9Q1\u0019\u0001\u0005\n\u0015\u0015\u0017a\u00032vS2$7/\u0011:sCf,b!b2\u0006R\u0016UG\u0003BA\u0017\u000b\u0013D\u0001\"b3\u0006B\u0002\u0007QQZ\u0001\u0002GB9Qfa$\u0006P\u0016M\u0007c\u0001\f\u0006R\u00129\u0011q[Ca\u0005\u0004I\u0002c\u0001\f\u0006V\u001291qNCa\u0005\u0004I\u0002bBCm\u0001\u0011\u0005S1\\\u0001\u0004[\u0006\u0004XCBCo\u000bc,\u0019\u000f\u0006\u0003\u0006`\u0016MH\u0003BCq\u000bK\u00042AFCr\t\u001d\u0019y'b6C\u0002eA\u0001\"b:\u0006X\u0002\u000fQ\u0011^\u0001\u0003E\u001a\u0004\u0002\"ICvW\u0015=X\u0011]\u0005\u0004\u000b[\u0014#\u0001D\"b]\n+\u0018\u000e\u001c3Ge>l\u0007c\u0001\f\u0006r\u00129\u0011q[Cl\u0005\u0004I\u0002\u0002CAC\u000b/\u0004\r!\">\u0011\r9\tI)FCx\u0011\u001d)I\u0010\u0001C!\u000bw\fAa]2b]V1QQ D\b\r\u000b!B!b@\u0007\u0016Q!a\u0011\u0001D\t)\u00111\u0019Ab\u0002\u0011\u0007Y1)\u0001B\u0004\u0004p\u0015](\u0019A\r\t\u0011\u0019%Qq\u001fa\u0002\r\u0017\t1a\u00192g!!\tS1^\u0016\u0007\u000e\u0019\r\u0001c\u0001\f\u0007\u0010\u0011A\u0011\u0011SC|\u0005\u0004\u0011Y\u0001\u0003\u0005\u0002\\\u0016]\b\u0019\u0001D\n!%q\u0011q\u001cD\u0007\r\u001b1i\u0001\u0003\u0005\u0002f\u0016]\b\u0019\u0001D\u0007\r\u00191I\u0002\u0001\u0001\u0007\u001c\tY1kY1o)>\f%O]1z+\u00111iB\"\u000b\u0014\u000b\u0019]QBb\b\u0011\r!2\tC\u0012D\u0013\u0013\r1\u0019\u0003\u0002\u0002\u0005)\u0006\u001c8\u000eE\u0003y\r/19\u0003E\u0002\u0017\rS!\u0001\"!%\u0007\u0018\t\u0007!1\u0002\u0005\f\r[19B!A!\u0002\u00131y#\u0001\u0003ue\u0016,\u0007#\u0002=\u00072\u0019\u001d\u0012\u0002\u0002D\u001a\rk\u0011\u0001bU2b]R\u0013X-Z\u0005\u0004\ro!!a\u0004)be&#XM]1cY\u0016d\u0015n[3\t\u0017\u0005\u0015hq\u0003B\u0001B\u0003%aq\u0005\u0005\f\u0003749B!A!\u0002\u00131i\u0004E\u0005\u000f\u0003?49Cb\n\u0007(!Qa\u0011\tD\f\u0005\u0003\u0005\u000b\u0011\u0002!\u0002\u0013Q\f'oZ3uCJ\u0014\bb\u0002\u001e\u0007\u0018\u0011\u0005aQ\t\u000b\u000b\rK19E\"\u0013\u0007L\u00195\u0003\u0002\u0003D\u0017\r\u0007\u0002\rAb\f\t\u0011\u0005\u0015h1\ta\u0001\rOA\u0001\"a7\u0007D\u0001\u0007aQ\b\u0005\b\r\u00032\u0019\u00051\u0001A\u0011)1\tFb\u0006A\u0002\u0013\u0005a1K\u0001\u0007e\u0016\u001cX\u000f\u001c;\u0016\u0003\u0019C!Bb\u0016\u0007\u0018\u0001\u0007I\u0011\u0001D-\u0003)\u0011Xm];mi~#S-\u001d\u000b\u0004\r\u001am\u0003\u0002\u0003&\u0007V\u0005\u0005\t\u0019\u0001$\t\u0011\u0019}cq\u0003Q!\n\u0019\u000bqA]3tk2$\b\u0005\u0003\u0005\u0007d\u0019]A\u0011\u0001D3\u0003\u0011aW-\u00194\u0015\u0007\u001939\u0007\u0003\u0005\u0007j\u0019\u0005\u0004\u0019\u0001D6\u0003\u0011\u0001(/\u001a<\u0011\t9\u00119M\u0012\u0005\t\r_29\u0002\"\u0003\u0007r\u00059\u0011\u000e^3sCR,Gc\u0001$\u0007t!AaQ\u0006D7\u0001\u00041y\u0003\u0003\u0005\u0007x\u0019]A\u0011\u0002D=\u0003!\u00198-\u00198MK\u00064Gc\u0003$\u0007|\u0019udq\u0010DA\r\u0007Cq!b\u0017\u0007v\u0001\u0007\u0001\tC\u0004\u0007B\u0019U\u0004\u0019\u0001!\t\u000f\u00055fQ\u000fa\u0001C\"9!\u0011 D;\u0001\u0004\t\u0007\u0002\u0003DC\rk\u0002\rAb\n\u0002\u0011M$\u0018M\u001d;wC2D\u0001\"!\u0019\u0007\u0018\u0011\u0005a\u0011R\u000b\u0003\r\u0017\u0003b!!\u0013\u0002P\u0019}\u0001\u0002\u0003DH\r/!\t!a\u000b\u0002%MDw.\u001e7e'Bd\u0017\u000e\u001e$veRDWM\u001d\u0004\u0007\r'\u0003\u0001A\"&\u0003\u00075\u000b\u0007/\u0006\u0003\u0007\u0018\u001a}5#\u0002DI\u001b\u0019e\u0005C\u0002\u0015\u0007\"\u00193Y\nE\u0003y\r#3i\nE\u0002\u0017\r?#q!a6\u0007\u0012\n\u0007\u0011\u0004C\u0006\u0002\u0006\u001aE%\u0011!Q\u0001\n\u0019\r\u0006C\u0002\b\u0002\nV1i\n\u0003\u0006\u0007B\u0019E%\u0011!Q\u0001\n\u0001C!B\"+\u0007\u0012\n\u0005\t\u0015!\u0003b\u0003\u0019ygMZ:fi\"QaQ\u0016DI\u0005\u0003\u0005\u000b\u0011B1\u0002\u000f!|w/\\1os\"9!H\"%\u0005\u0002\u0019EFC\u0003DN\rg3)Lb.\u0007:\"A\u0011Q\u0011DX\u0001\u00041\u0019\u000bC\u0004\u0007B\u0019=\u0006\u0019\u0001!\t\u000f\u0019%fq\u0016a\u0001C\"9aQ\u0016DX\u0001\u0004\t\u0007B\u0003D)\r#\u0003\r\u0011\"\u0001\u0007T!Qaq\u000bDI\u0001\u0004%\tAb0\u0015\u0007\u00193\t\r\u0003\u0005K\r{\u000b\t\u00111\u0001G\u0011!1yF\"%!B\u00131\u0005\u0002\u0003D2\r##\tAb2\u0015\u0007\u00193I\r\u0003\u0005\u0007j\u0019\u0015\u0007\u0019\u0001D6\u0011!\t\tG\"%\u0005\u0002\u00195WC\u0001Dh!\u00191\tNb6\u0007\u001c6\u0011a1\u001b\u0006\u0004\r+4\u0011!C5n[V$\u0018M\u00197f\u0013\u00111INb5\u0003\t1K7\u000f\u001e\u0005\t\r\u001f3\t\n\"\u0001\u0002,!9aq\u001c\u0001\u0005\n\u0019\u0005\u0018aC<sSR,wJ\u00196fGR$2A\u0012Dr\u0011!1)O\"8A\u0002\u0019\u001d\u0018aA8viB!a\u0011\u001eDx\u001b\t1YO\u0003\u0003\u0007n\u0006M\u0014AA5p\u0013\u00111\tPb;\u0003%=\u0013'.Z2u\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u0005\b\rk\u0004A\u0011\u0002D|\u0003)\u0011X-\u00193PE*,7\r\u001e\u000b\u0004\r\u001ae\b\u0002\u0003D~\rg\u0004\rA\"@\u0002\u0005%t\u0007\u0003\u0002Du\r\u007fLAa\"\u0001\u0007l\n\trJ\u00196fGRLe\u000e];u'R\u0014X-Y7)\u000f\u00019)ab\u0003\b\u000eA\u0019abb\u0002\n\u0007\u001d%\u0001B\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t\u0011aB\u0004\b\u0012\tA\tab\u0005\u0002\u0011A\u000b'/\u0011:sCf\u00042AED\u000b\r\u0019\t!\u0001#\u0001\b\u0018M)qQCD\rcA!\u0011eb\u0007'\u0013\r9iB\t\u0002\u000b!\u0006\u0014h)Y2u_JL\bb\u0002\u001e\b\u0016\u0011\u0005q\u0011\u0005\u000b\u0003\u000f'A\u0001b\"\n\b\u0016\u0011\rqqE\u0001\rG\u0006t')^5mI\u001a\u0013x.\\\u000b\u0005\u000fS9I$\u0006\u0002\b,AI\u0011e\"\f\b2\u001d]r1H\u0005\u0004\u000f_\u0011#AD\"b]\u000e{WNY5oK\u001a\u0013x.\u001c\t\u0005\u000fg9)$\u0004\u0002\b\u0016%\u0019Aq\u000f-\u0011\u0007Y9I\u0004\u0002\u0004\u0019\u000fG\u0011\r!\u0007\t\u0005%\u000199\u0004\u0003\u0005\b@\u001dUA\u0011AD!\u0003)qWm\u001e\"vS2$WM]\u000b\u0005\u000f\u0007:I%\u0006\u0002\bFA9\u0001fa\u0019\bH\u001d-\u0003c\u0001\f\bJ\u00111\u0001d\"\u0010C\u0002e\u0001BA\u0005\u0001\bH!AqqJD\u000b\t\u00039\t&A\u0006oK^\u001cu.\u001c2j]\u0016\u0014X\u0003BD*\u000f3*\"a\"\u0016\u0011\u000f!\u001a\u0019gb\u0016\b\\A\u0019ac\"\u0017\u0005\ra9iE1\u0001\u001a!\u0011\u0011\u0002ab\u0016\t\u0011\u001d}sQ\u0003C\u0001\u000fC\nq\u0001[1oI>4g-\u0006\u0003\bd\u001d%D\u0003BD3\u000fW\u0002BA\u0005\u0001\bhA\u0019ac\"\u001b\u0005\ra9iF1\u0001\u001a\u0011!\t9b\"\u0018A\u0002\u001d5\u0004\u0003\u0002\bB\u000fOB\u0001bb\u0018\b\u0016\u0011\u0005q\u0011O\u000b\u0005\u000fg:I\b\u0006\u0004\bv\u001dmtq\u0010\t\u0005%\u000199\bE\u0002\u0017\u000fs\"a\u0001GD8\u0005\u0004I\u0002\u0002CA\f\u000f_\u0002\ra\" \u0011\t9\tuq\u000f\u0005\u0007A\u001e=\u0004\u0019A1\t\u0011\u001d\ruQ\u0003C\u0005\u000f\u000b\u000bQb\u001e:ba>\u0013(+\u001a2vS2$W\u0003BDD\u000f\u001b#ba\"#\b\u0010\u001eE\u0005\u0003\u0002\n\u0001\u000f\u0017\u00032AFDG\t\u0019Ar\u0011\u0011b\u00013!9\u0011qCDA\u0001\u0004i\u0001B\u00021\b\u0002\u0002\u0007\u0011\r\u0003\u0005\b\u0016\u001eUA\u0011ADL\u00039\u0019'/Z1uK\u001a\u0013x.\\\"paf,Ba\"'\b\"R!q1TD[)\u00119ij\"*\u0011\tI\u0001qq\u0014\t\u0004-\u001d\u0005Fa\u0002\r\b\u0014\n\u0007q1U\t\u000355A!bb*\b\u0014\u0006\u0005\t9ADU\u0003))g/\u001b3f]\u000e,G%\r\t\u0007\u000fW;\tlb(\u000e\u0005\u001d5&bADX\u0011\u00059!/\u001a4mK\u000e$\u0018\u0002BDZ\u000f[\u0013\u0001b\u00117bgN$\u0016m\u001a\u0005\t\u0003/9\u0019\n1\u0001\b8B!a\"QDP\u0011!9Yl\"\u0006\u0005\u0002\u001du\u0016\u0001\u00054s_6$&/\u0019<feN\f'\r\\3t+\u00119yl\"2\u0015\t\u001d\u0005wq\u0019\t\u0005%\u00019\u0019\rE\u0002\u0017\u000f\u000b$a\u0001GD]\u0005\u0004I\u0002\u0002CDe\u000fs\u0003\rab3\u0002\u0007a\u001c8\u000fE\u0003\u000f\u00037:i\r\u0005\u0004\u0004p\u000eEx1\u0019\u0005\u000b\u000f#<)\"!A\u0005\n\u001dM\u0017a\u0003:fC\u0012\u0014Vm]8mm\u0016$\"a\"6\u0011\t\u00055tq[\u0005\u0005\u000f3\fyG\u0001\u0004PE*,7\r\u001e")
public class ParArray<T>
implements scala.collection.parallel.mutable.ParSeq<T>,
Serializable {
    public static final long serialVersionUID = 1L;
    private final ArraySeq<T> arrayseq;
    private transient Object[] scala$collection$parallel$mutable$ParArray$$array;
    private volatile ParArray$ParArrayIterator$ ParArrayIterator$module;
    private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
    private volatile ParIterableLike$ScanNode$ ScanNode$module;
    private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;

    public static <T> ParArray<T> fromTraversables(Seq<GenTraversableOnce<T>> seq) {
        return ParArray$.MODULE$.fromTraversables(seq);
    }

    public static <T> ParArray<T> createFromCopy(T[] TArray, ClassTag<T> classTag) {
        return ParArray$.MODULE$.createFromCopy(TArray, classTag);
    }

    public static <T> ParArray<T> handoff(Object object, int n) {
        return ParArray$.MODULE$.handoff(object, n);
    }

    public static <T> ParArray<T> handoff(Object object) {
        return ParArray$.MODULE$.handoff(object);
    }

    public static <T> CanCombineFrom<ParArray<?>, T, ParArray<T>> canBuildFrom() {
        return ParArray$.MODULE$.canBuildFrom();
    }

    public static GenTraversable iterate(Object object, int n, Function1 function1) {
        return ParArray$.MODULE$.iterate(object, n, function1);
    }

    public static GenTraversable range(Object object, Object object2, Object object3, Integral integral) {
        return ParArray$.MODULE$.range(object, object2, object3, integral);
    }

    public static GenTraversable range(Object object, Object object2, Integral integral) {
        return ParArray$.MODULE$.range(object, object2, integral);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, int n5, Function5 function5) {
        return ParArray$.MODULE$.tabulate(n, n2, n3, n4, n5, function5);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, Function4 function4) {
        return ParArray$.MODULE$.tabulate(n, n2, n3, n4, function4);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, Function3 function3) {
        return ParArray$.MODULE$.tabulate(n, n2, n3, function3);
    }

    public static GenTraversable tabulate(int n, int n2, Function2 function2) {
        return ParArray$.MODULE$.tabulate(n, n2, function2);
    }

    public static GenTraversable tabulate(int n, Function1 function1) {
        return ParArray$.MODULE$.tabulate(n, function1);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, int n5, Function0 function0) {
        return ParArray$.MODULE$.fill(n, n2, n3, n4, n5, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, Function0 function0) {
        return ParArray$.MODULE$.fill(n, n2, n3, n4, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, Function0 function0) {
        return ParArray$.MODULE$.fill(n, n2, n3, function0);
    }

    public static GenTraversable fill(int n, int n2, Function0 function0) {
        return ParArray$.MODULE$.fill(n, n2, function0);
    }

    public static GenTraversable fill(int n, Function0 function0) {
        return ParArray$.MODULE$.fill(n, function0);
    }

    public static GenTraversable concat(Seq seq) {
        return ParArray$.MODULE$.concat(seq);
    }

    public static GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return ParArray$.MODULE$.ReusableCBF();
    }

    public static GenTraversable empty() {
        return ParArray$.MODULE$.empty();
    }

    private ParArray$ParArrayIterator$ ParArrayIterator$lzycompute() {
        ParArray parArray = this;
        synchronized (parArray) {
            if (this.ParArrayIterator$module == null) {
                this.ParArrayIterator$module = new ParArray$ParArrayIterator$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ParArrayIterator$module;
        }
    }

    @Override
    public scala.collection.parallel.mutable.ParSeq<T> toSeq() {
        return scala.collection.parallel.mutable.ParSeq$class.toSeq(this);
    }

    @Override
    public String toString() {
        return ParSeq$class.toString(this);
    }

    @Override
    public String stringPrefix() {
        return ParSeq$class.stringPrefix(this);
    }

    @Override
    public /* synthetic */ Object scala$collection$parallel$ParSeqLike$$super$zip(GenIterable that, CanBuildFrom bf) {
        return ParIterableLike$class.zip(this, that, bf);
    }

    @Override
    public PreciseSplitter<T> iterator() {
        return ParSeqLike$class.iterator(this);
    }

    @Override
    public int size() {
        return ParSeqLike$class.size(this);
    }

    @Override
    public int segmentLength(Function1<T, Object> p, int from2) {
        return ParSeqLike$class.segmentLength(this, p, from2);
    }

    @Override
    public int indexWhere(Function1<T, Object> p, int from2) {
        return ParSeqLike$class.indexWhere(this, p, from2);
    }

    @Override
    public int lastIndexWhere(Function1<T, Object> p, int end) {
        return ParSeqLike$class.lastIndexWhere(this, p, end);
    }

    @Override
    public ParSeq reverse() {
        return ParSeqLike$class.reverse(this);
    }

    @Override
    public <S, That> That reverseMap(Function1<T, S> f, CanBuildFrom<ParArray<T>, S, That> bf) {
        return (That)ParSeqLike$class.reverseMap(this, f, bf);
    }

    @Override
    public <S> boolean startsWith(GenSeq<S> that, int offset) {
        return ParSeqLike$class.startsWith(this, that, offset);
    }

    @Override
    public <U> boolean sameElements(GenIterable<U> that) {
        return ParSeqLike$class.sameElements(this, that);
    }

    @Override
    public <S> boolean endsWith(GenSeq<S> that) {
        return ParSeqLike$class.endsWith(this, that);
    }

    @Override
    public <U, That> That patch(int from2, GenSeq<U> patch2, int replaced, CanBuildFrom<ParArray<T>, U, That> bf) {
        return (That)ParSeqLike$class.patch(this, from2, patch2, replaced, bf);
    }

    @Override
    public <U, That> That updated(int index, U elem, CanBuildFrom<ParArray<T>, U, That> bf) {
        return (That)ParSeqLike$class.updated(this, index, elem, bf);
    }

    @Override
    public <U, That> That $plus$colon(U elem, CanBuildFrom<ParArray<T>, U, That> bf) {
        return (That)ParSeqLike$class.$plus$colon(this, elem, bf);
    }

    @Override
    public <U, That> That $colon$plus(U elem, CanBuildFrom<ParArray<T>, U, That> bf) {
        return (That)ParSeqLike$class.$colon$plus(this, elem, bf);
    }

    @Override
    public <U, That> That padTo(int len, U elem, CanBuildFrom<ParArray<T>, U, That> bf) {
        return (That)ParSeqLike$class.padTo(this, len, elem, bf);
    }

    @Override
    public <U, S, That> That zip(GenIterable<S> that, CanBuildFrom<ParArray<T>, Tuple2<U, S>, That> bf) {
        return (That)ParSeqLike$class.zip(this, that, bf);
    }

    @Override
    public <S> boolean corresponds(GenSeq<S> that, Function2<T, S, Object> p) {
        return ParSeqLike$class.corresponds(this, that, p);
    }

    @Override
    public ParSeq diff(GenSeq that) {
        return ParSeqLike$class.diff(this, that);
    }

    @Override
    public ParSeq intersect(GenSeq that) {
        return ParSeqLike$class.intersect(this, that);
    }

    @Override
    public ParSeq distinct() {
        return ParSeqLike$class.distinct(this);
    }

    @Override
    public Object view() {
        return ParSeqLike$class.view(this);
    }

    @Override
    public SeqSplitter<T> down(IterableSplitter<?> p) {
        return ParSeqLike$class.down(this, p);
    }

    @Override
    public ParIterable<T> toIterable() {
        return ParIterable$class.toIterable(this);
    }

    @Override
    public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
        return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
    }

    @Override
    @TraitSetter
    public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
        this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
    }

    private ParIterableLike$ScanNode$ ScanNode$lzycompute() {
        ParArray parArray = this;
        synchronized (parArray) {
            if (this.ScanNode$module == null) {
                this.ScanNode$module = new ParIterableLike$ScanNode$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ScanNode$module;
        }
    }

    @Override
    public ParIterableLike$ScanNode$ ScanNode() {
        return this.ScanNode$module == null ? this.ScanNode$lzycompute() : this.ScanNode$module;
    }

    private ParIterableLike$ScanLeaf$ ScanLeaf$lzycompute() {
        ParArray parArray = this;
        synchronized (parArray) {
            if (this.ScanLeaf$module == null) {
                this.ScanLeaf$module = new ParIterableLike$ScanLeaf$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ScanLeaf$module;
        }
    }

    @Override
    public ParIterableLike$ScanLeaf$ ScanLeaf() {
        return this.ScanLeaf$module == null ? this.ScanLeaf$lzycompute() : this.ScanLeaf$module;
    }

    @Override
    public void initTaskSupport() {
        ParIterableLike$class.initTaskSupport(this);
    }

    @Override
    public TaskSupport tasksupport() {
        return ParIterableLike$class.tasksupport(this);
    }

    @Override
    public void tasksupport_$eq(TaskSupport ts) {
        ParIterableLike$class.tasksupport_$eq(this, ts);
    }

    @Override
    public scala.collection.parallel.ParIterable repr() {
        return ParIterableLike$class.repr(this);
    }

    @Override
    public final boolean isTraversableAgain() {
        return ParIterableLike$class.isTraversableAgain(this);
    }

    @Override
    public boolean hasDefiniteSize() {
        return ParIterableLike$class.hasDefiniteSize(this);
    }

    @Override
    public boolean isEmpty() {
        return ParIterableLike$class.isEmpty(this);
    }

    @Override
    public boolean nonEmpty() {
        return ParIterableLike$class.nonEmpty(this);
    }

    @Override
    public T head() {
        return (T)ParIterableLike$class.head(this);
    }

    @Override
    public Option<T> headOption() {
        return ParIterableLike$class.headOption(this);
    }

    @Override
    public scala.collection.parallel.ParIterable tail() {
        return ParIterableLike$class.tail(this);
    }

    @Override
    public T last() {
        return (T)ParIterableLike$class.last(this);
    }

    @Override
    public Option<T> lastOption() {
        return ParIterableLike$class.lastOption(this);
    }

    @Override
    public scala.collection.parallel.ParIterable init() {
        return ParIterableLike$class.init(this);
    }

    @Override
    public scala.collection.parallel.ParIterable par() {
        return ParIterableLike$class.par(this);
    }

    @Override
    public boolean isStrictSplitterCollection() {
        return ParIterableLike$class.isStrictSplitterCollection(this);
    }

    @Override
    public <S, That> Combiner<S, That> reuse(Option<Combiner<S, That>> oldc, Combiner<S, That> newc) {
        return ParIterableLike$class.reuse(this, oldc, newc);
    }

    @Override
    public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask<R, Tp> tsk) {
        return ParIterableLike$class.task2ops(this, tsk);
    }

    @Override
    public <R> Object wrap(Function0<R> body2) {
        return ParIterableLike$class.wrap(this, body2);
    }

    @Override
    public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(PI it) {
        return ParIterableLike$class.delegatedSignalling2ops(this, it);
    }

    @Override
    public <Elem, To> Object builder2ops(Builder<Elem, To> cb) {
        return ParIterableLike$class.builder2ops(this, cb);
    }

    @Override
    public <S, That> Object bf2seq(CanBuildFrom<ParArray<T>, S, That> bf) {
        return ParIterableLike$class.bf2seq(this, bf);
    }

    @Override
    public scala.collection.parallel.ParIterable sequentially(Function1 b) {
        return ParIterableLike$class.sequentially(this, b);
    }

    @Override
    public String mkString(String start, String sep, String end) {
        return ParIterableLike$class.mkString(this, start, sep, end);
    }

    @Override
    public String mkString(String sep) {
        return ParIterableLike$class.mkString(this, sep);
    }

    @Override
    public String mkString() {
        return ParIterableLike$class.mkString(this);
    }

    @Override
    public boolean canEqual(Object other) {
        return ParIterableLike$class.canEqual(this, other);
    }

    @Override
    public <U> U reduce(Function2<U, U, U> op) {
        return (U)ParIterableLike$class.reduce(this, op);
    }

    @Override
    public <U> Option<U> reduceOption(Function2<U, U, U> op) {
        return ParIterableLike$class.reduceOption(this, op);
    }

    @Override
    public <U> U fold(U z, Function2<U, U, U> op) {
        return (U)ParIterableLike$class.fold(this, z, op);
    }

    @Override
    public <S> S aggregate(Function0<S> z, Function2<S, T, S> seqop, Function2<S, S, S> combop) {
        return (S)ParIterableLike$class.aggregate(this, z, seqop, combop);
    }

    @Override
    public <S> S foldLeft(S z, Function2<S, T, S> op) {
        return (S)ParIterableLike$class.foldLeft(this, z, op);
    }

    @Override
    public <S> S foldRight(S z, Function2<T, S, S> op) {
        return (S)ParIterableLike$class.foldRight(this, z, op);
    }

    @Override
    public <U> U reduceLeft(Function2<U, T, U> op) {
        return (U)ParIterableLike$class.reduceLeft(this, op);
    }

    @Override
    public <U> U reduceRight(Function2<T, U, U> op) {
        return (U)ParIterableLike$class.reduceRight(this, op);
    }

    @Override
    public <U> Option<U> reduceLeftOption(Function2<U, T, U> op) {
        return ParIterableLike$class.reduceLeftOption(this, op);
    }

    @Override
    public <U> Option<U> reduceRightOption(Function2<T, U, U> op) {
        return ParIterableLike$class.reduceRightOption(this, op);
    }

    @Override
    public <U> void foreach(Function1<T, U> f) {
        ParIterableLike$class.foreach(this, f);
    }

    @Override
    public int count(Function1<T, Object> p) {
        return ParIterableLike$class.count(this, p);
    }

    @Override
    public <U> U sum(Numeric<U> num) {
        return (U)ParIterableLike$class.sum(this, num);
    }

    @Override
    public <U> U product(Numeric<U> num) {
        return (U)ParIterableLike$class.product(this, num);
    }

    @Override
    public <U> T min(Ordering<U> ord) {
        return (T)ParIterableLike$class.min(this, ord);
    }

    @Override
    public <U> T max(Ordering<U> ord) {
        return (T)ParIterableLike$class.max(this, ord);
    }

    @Override
    public <S> T maxBy(Function1<T, S> f, Ordering<S> cmp) {
        return (T)ParIterableLike$class.maxBy(this, f, cmp);
    }

    @Override
    public <S> T minBy(Function1<T, S> f, Ordering<S> cmp) {
        return (T)ParIterableLike$class.minBy(this, f, cmp);
    }

    @Override
    public <S, That> That collect(PartialFunction<T, S> pf, CanBuildFrom<ParArray<T>, S, That> bf) {
        return (That)ParIterableLike$class.collect(this, pf, bf);
    }

    @Override
    public <S, That> That flatMap(Function1<T, GenTraversableOnce<S>> f, CanBuildFrom<ParArray<T>, S, That> bf) {
        return (That)ParIterableLike$class.flatMap(this, f, bf);
    }

    @Override
    public boolean forall(Function1<T, Object> p) {
        return ParIterableLike$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<T, Object> p) {
        return ParIterableLike$class.exists(this, p);
    }

    @Override
    public Option<T> find(Function1<T, Object> p) {
        return ParIterableLike$class.find(this, p);
    }

    @Override
    public Object combinerFactory() {
        return ParIterableLike$class.combinerFactory(this);
    }

    @Override
    public <S, That> Object combinerFactory(Function0<Combiner<S, That>> cbf) {
        return ParIterableLike$class.combinerFactory(this, cbf);
    }

    @Override
    public scala.collection.parallel.ParIterable withFilter(Function1 pred) {
        return ParIterableLike$class.withFilter(this, pred);
    }

    @Override
    public scala.collection.parallel.ParIterable filter(Function1 pred) {
        return ParIterableLike$class.filter(this, pred);
    }

    @Override
    public scala.collection.parallel.ParIterable filterNot(Function1 pred) {
        return ParIterableLike$class.filterNot(this, pred);
    }

    @Override
    public <U, That> That $plus$plus(GenTraversableOnce<U> that, CanBuildFrom<ParArray<T>, U, That> bf) {
        return (That)ParIterableLike$class.$plus$plus(this, that, bf);
    }

    @Override
    public Tuple2<ParArray<T>, ParArray<T>> partition(Function1<T, Object> pred) {
        return ParIterableLike$class.partition(this, pred);
    }

    @Override
    public <K> ParMap<K, ParArray<T>> groupBy(Function1<T, K> f) {
        return ParIterableLike$class.groupBy(this, f);
    }

    @Override
    public scala.collection.parallel.ParIterable take(int n) {
        return ParIterableLike$class.take(this, n);
    }

    @Override
    public scala.collection.parallel.ParIterable drop(int n) {
        return ParIterableLike$class.drop(this, n);
    }

    @Override
    public scala.collection.parallel.ParIterable slice(int unc_from, int unc_until) {
        return ParIterableLike$class.slice(this, unc_from, unc_until);
    }

    @Override
    public Tuple2<ParArray<T>, ParArray<T>> splitAt(int n) {
        return ParIterableLike$class.splitAt(this, n);
    }

    @Override
    public <S, That> That scanLeft(S z, Function2<S, T, S> op, CanBuildFrom<ParArray<T>, S, That> bf) {
        return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
    }

    @Override
    public <S, That> That scanRight(S z, Function2<T, S, S> op, CanBuildFrom<ParArray<T>, S, That> bf) {
        return (That)ParIterableLike$class.scanRight(this, z, op, bf);
    }

    @Override
    public scala.collection.parallel.ParIterable takeWhile(Function1 pred) {
        return ParIterableLike$class.takeWhile(this, pred);
    }

    @Override
    public Tuple2<ParArray<T>, ParArray<T>> span(Function1<T, Object> pred) {
        return ParIterableLike$class.span(this, pred);
    }

    @Override
    public scala.collection.parallel.ParIterable dropWhile(Function1 pred) {
        return ParIterableLike$class.dropWhile(this, pred);
    }

    @Override
    public <U> void copyToArray(Object xs) {
        ParIterableLike$class.copyToArray(this, xs);
    }

    @Override
    public <U> void copyToArray(Object xs, int start) {
        ParIterableLike$class.copyToArray(this, xs, start);
    }

    @Override
    public <U> void copyToArray(Object xs, int start, int len) {
        ParIterableLike$class.copyToArray(this, xs, start, len);
    }

    @Override
    public <U, That> That zipWithIndex(CanBuildFrom<ParArray<T>, Tuple2<U, Object>, That> bf) {
        return (That)ParIterableLike$class.zipWithIndex(this, bf);
    }

    @Override
    public <S, U, That> That zipAll(GenIterable<S> that, U thisElem, S thatElem, CanBuildFrom<ParArray<T>, Tuple2<U, S>, That> bf) {
        return (That)ParIterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
    }

    @Override
    public <U, That> That toParCollection(Function0<Combiner<U, That>> cbf) {
        return (That)ParIterableLike$class.toParCollection(this, cbf);
    }

    @Override
    public <K, V, That> That toParMap(Function0<Combiner<Tuple2<K, V>, That>> cbf, Predef$.less.colon.less<T, Tuple2<K, V>> ev) {
        return (That)ParIterableLike$class.toParMap(this, cbf, ev);
    }

    @Override
    public <U> Object toArray(ClassTag<U> evidence$1) {
        return ParIterableLike$class.toArray(this, evidence$1);
    }

    @Override
    public List<T> toList() {
        return ParIterableLike$class.toList(this);
    }

    @Override
    public IndexedSeq<T> toIndexedSeq() {
        return ParIterableLike$class.toIndexedSeq(this);
    }

    @Override
    public Stream<T> toStream() {
        return ParIterableLike$class.toStream(this);
    }

    @Override
    public Iterator<T> toIterator() {
        return ParIterableLike$class.toIterator(this);
    }

    @Override
    public <U> Buffer<U> toBuffer() {
        return ParIterableLike$class.toBuffer(this);
    }

    @Override
    public GenTraversable<T> toTraversable() {
        return ParIterableLike$class.toTraversable(this);
    }

    @Override
    public <U> ParSet<U> toSet() {
        return ParIterableLike$class.toSet(this);
    }

    @Override
    public <K, V> ParMap<K, V> toMap(Predef$.less.colon.less<T, Tuple2<K, V>> ev) {
        return ParIterableLike$class.toMap(this, ev);
    }

    @Override
    public Vector<T> toVector() {
        return ParIterableLike$class.toVector(this);
    }

    @Override
    public <Col> Col to(CanBuildFrom<Nothing$, T, Col> cbf) {
        return (Col)ParIterableLike$class.to(this, cbf);
    }

    @Override
    public int scanBlockSize() {
        return ParIterableLike$class.scanBlockSize(this);
    }

    @Override
    public <S> S $div$colon(S z, Function2<S, T, S> op) {
        return (S)ParIterableLike$class.$div$colon(this, z, op);
    }

    @Override
    public <S> S $colon$bslash(S z, Function2<T, S, S> op) {
        return (S)ParIterableLike$class.$colon$bslash(this, z, op);
    }

    @Override
    public String debugInformation() {
        return ParIterableLike$class.debugInformation(this);
    }

    @Override
    public Seq<String> brokenInvariants() {
        return ParIterableLike$class.brokenInvariants(this);
    }

    @Override
    public ArrayBuffer<String> debugBuffer() {
        return ParIterableLike$class.debugBuffer(this);
    }

    @Override
    public void debugclear() {
        ParIterableLike$class.debugclear(this);
    }

    @Override
    public ArrayBuffer<String> debuglog(String s2) {
        return ParIterableLike$class.debuglog(this, s2);
    }

    @Override
    public void printDebugBuffer() {
        ParIterableLike$class.printDebugBuffer(this);
    }

    @Override
    public Combiner<T, ParArray<T>> parCombiner() {
        return CustomParallelizable$class.parCombiner(this);
    }

    @Override
    public Builder<T, ParArray<T>> newBuilder() {
        return GenericParTemplate$class.newBuilder(this);
    }

    @Override
    public Combiner<T, ParArray<T>> newCombiner() {
        return GenericParTemplate$class.newCombiner(this);
    }

    @Override
    public <B> Combiner<B, ParArray<B>> genericBuilder() {
        return GenericParTemplate$class.genericBuilder(this);
    }

    @Override
    public <B> Combiner<B, ParArray<B>> genericCombiner() {
        return GenericParTemplate$class.genericCombiner(this);
    }

    @Override
    public <A1, A2> Tuple2<ParArray<A1>, ParArray<A2>> unzip(Function1<T, Tuple2<A1, A2>> asPair) {
        return GenericTraversableTemplate$class.unzip(this, asPair);
    }

    @Override
    public <A1, A2, A3> Tuple3<ParArray<A1>, ParArray<A2>, ParArray<A3>> unzip3(Function1<T, Tuple3<A1, A2, A3>> asTriple) {
        return GenericTraversableTemplate$class.unzip3(this, asTriple);
    }

    @Override
    public GenTraversable flatten(Function1 asTraversable) {
        return GenericTraversableTemplate$class.flatten(this, asTraversable);
    }

    @Override
    public GenTraversable transpose(Function1 asTraversable) {
        return GenericTraversableTemplate$class.transpose(this, asTraversable);
    }

    @Override
    public boolean isDefinedAt(int idx) {
        return GenSeqLike$class.isDefinedAt(this, idx);
    }

    @Override
    public int prefixLength(Function1<T, Object> p) {
        return GenSeqLike$class.prefixLength(this, p);
    }

    @Override
    public int indexWhere(Function1<T, Object> p) {
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
    public int lastIndexWhere(Function1<T, Object> p) {
        return GenSeqLike$class.lastIndexWhere(this, p);
    }

    @Override
    public <B> boolean startsWith(GenSeq<B> that) {
        return GenSeqLike$class.startsWith(this, that);
    }

    @Override
    public <B, That> That union(GenSeq<B> that, CanBuildFrom<ParArray<T>, B, That> bf) {
        return (That)GenSeqLike$class.union(this, that, bf);
    }

    @Override
    public int hashCode() {
        return GenSeqLike$class.hashCode(this);
    }

    @Override
    public boolean equals(Object that) {
        return GenSeqLike$class.equals(this, that);
    }

    public ArraySeq<T> arrayseq() {
        return this.arrayseq;
    }

    public Object[] scala$collection$parallel$mutable$ParArray$$array() {
        return this.scala$collection$parallel$mutable$ParArray$$array;
    }

    private void scala$collection$parallel$mutable$ParArray$$array_$eq(Object[] x$1) {
        this.scala$collection$parallel$mutable$ParArray$$array = x$1;
    }

    @Override
    public GenericCompanion<ParArray> companion() {
        return ParArray$.MODULE$;
    }

    @Override
    public T apply(int i) {
        return (T)this.scala$collection$parallel$mutable$ParArray$$array()[i];
    }

    @Override
    public void update(int i, T elem) {
        this.scala$collection$parallel$mutable$ParArray$$array()[i] = elem;
    }

    @Override
    public int length() {
        return this.arrayseq().length();
    }

    @Override
    public ArraySeq<T> seq() {
        return this.arrayseq();
    }

    /*
     * WARNING - void declaration
     */
    public ParArrayIterator splitter() {
        void var1_1;
        ParArrayIterator pit = new ParArrayIterator(this, this.ParArrayIterator().$lessinit$greater$default$1(), this.ParArrayIterator().$lessinit$greater$default$2(), this.ParArrayIterator().$lessinit$greater$default$3());
        return var1_1;
    }

    public ParArray$ParArrayIterator$ ParArrayIterator() {
        return this.ParArrayIterator$module == null ? this.ParArrayIterator$lzycompute() : this.ParArrayIterator$module;
    }

    private <S, That> boolean buildsArray(Builder<S, That> c) {
        return c instanceof ResizableParArrayCombiner;
    }

    @Override
    public <S, That> That map(Function1<T, S> f, CanBuildFrom<ParArray<T>, S, That> bf) {
        Object object;
        if (this.buildsArray(bf.apply((ParArray<T>)this.repr()))) {
            ArraySeq targarrseq = new ArraySeq(this.length());
            Object[] targetarr = targarrseq.array();
            this.tasksupport().executeAndWaitResult(new Map<S>(this, f, targetarr, 0, this.length()));
            object = new ParArray(targarrseq);
        } else {
            object = ParIterableLike$class.map(this, f, bf);
        }
        return (That)object;
    }

    @Override
    public <U, That> That scan(U z, Function2<U, U, U> op, CanBuildFrom<ParArray<T>, U, That> cbf) {
        Object object;
        if (this.tasksupport().parallelismLevel() > 1 && this.buildsArray(cbf.apply((ParArray<T>)this.repr()))) {
            ArraySeq targarrseq = new ArraySeq(this.length() + 1);
            Object[] targetarr = targarrseq.array();
            targetarr[0] = z;
            BoxedUnit boxedUnit = this.length() > 0 ? this.tasksupport().executeAndWaitResult(this.task2ops(new ParIterableLike.CreateScanTree<U>(this, 0, this.size(), z, op, this.splitter())).mapResult(new Serializable(this, z, op, targetarr){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ParArray $outer;
                private final Object z$1;
                private final Function2 op$1;
                private final Object[] targetarr$1;

                public final void apply(ParIterableLike.ScanTree<U> tree) {
                    this.$outer.tasksupport().executeAndWaitResult(new ScanToArray<Object>(this.$outer, tree, this.z$1, this.op$1, this.targetarr$1));
                }
                {
                    void var4_4;
                    void var3_3;
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.z$1 = z$1;
                    this.op$1 = var3_3;
                    this.targetarr$1 = var4_4;
                }
            })) : BoxedUnit.UNIT;
            object = new ParArray(targarrseq);
        } else {
            object = ParIterableLike$class.scan(this, z, op, cbf);
        }
        return (That)object;
    }

    private void writeObject(ObjectOutputStream out) {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) {
        in.defaultReadObject();
        this.scala$collection$parallel$mutable$ParArray$$array_$eq(this.arrayseq().array());
    }

    public ParArray(ArraySeq<T> arrayseq) {
        this.arrayseq = arrayseq;
        Parallelizable$class.$init$(this);
        GenSeqLike$class.$init$(this);
        GenericTraversableTemplate$class.$init$(this);
        GenTraversable$class.$init$(this);
        GenIterable$class.$init$(this);
        GenSeq$class.$init$(this);
        GenericParTemplate$class.$init$(this);
        CustomParallelizable$class.$init$(this);
        ParIterableLike$class.$init$(this);
        scala.collection.parallel.ParIterable$class.$init$(this);
        ParIterable$class.$init$(this);
        ParSeqLike$class.$init$(this);
        ParSeq$class.$init$(this);
        scala.collection.parallel.mutable.ParSeq$class.$init$(this);
        this.scala$collection$parallel$mutable$ParArray$$array = arrayseq.array();
    }

    public ParArray(int sz) {
        Predef$.MODULE$.require(sz >= 0);
        this(new ArraySeq(sz));
    }

    public class Map<S>
    implements Task<BoxedUnit, Map<S>> {
        private final Function1<T, S> f;
        private final Object[] targetarr;
        private final int offset;
        private final int howmany;
        private BoxedUnit result;
        public final /* synthetic */ ParArray $outer;
        private volatile Throwable throwable;

        @Override
        public Throwable throwable() {
            return this.throwable;
        }

        @Override
        @TraitSetter
        public void throwable_$eq(Throwable x$1) {
            this.throwable = x$1;
        }

        @Override
        public Object repr() {
            return Task$class.repr(this);
        }

        @Override
        public void merge(Object that) {
            Task$class.merge(this, that);
        }

        @Override
        public void forwardThrowable() {
            Task$class.forwardThrowable(this);
        }

        @Override
        public void tryLeaf(Option<BoxedUnit> lastres) {
            Task$class.tryLeaf(this, lastres);
        }

        @Override
        public void tryMerge(Object t) {
            Task$class.tryMerge(this, t);
        }

        @Override
        public void mergeThrowables(Task<?, ?> that) {
            Task$class.mergeThrowables(this, that);
        }

        @Override
        public void signalAbort() {
            Task$class.signalAbort(this);
        }

        @Override
        public void result() {
        }

        @Override
        public void result_$eq(BoxedUnit x$1) {
            this.result = x$1;
        }

        @Override
        public void leaf(Option<BoxedUnit> prev) {
            Object[] tarr = this.targetarr;
            Object[] sarr = this.scala$collection$parallel$mutable$ParArray$Map$$$outer().scala$collection$parallel$mutable$ParArray$$array();
            int until2 = this.offset + this.howmany;
            for (int i = this.offset; i < until2; ++i) {
                tarr[i] = this.f.apply(sarr[i]);
            }
        }

        public List<Map<S>> split() {
            int fp = this.howmany / 2;
            return List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Map[]{new Map<S>(this.scala$collection$parallel$mutable$ParArray$Map$$$outer(), this.f, this.targetarr, this.offset, fp), new Map<S>(this.scala$collection$parallel$mutable$ParArray$Map$$$outer(), this.f, this.targetarr, this.offset + fp, this.howmany - fp)}));
        }

        @Override
        public boolean shouldSplitFurther() {
            return this.howmany > package$.MODULE$.thresholdFromSize(this.scala$collection$parallel$mutable$ParArray$Map$$$outer().length(), this.scala$collection$parallel$mutable$ParArray$Map$$$outer().tasksupport().parallelismLevel());
        }

        public /* synthetic */ ParArray scala$collection$parallel$mutable$ParArray$Map$$$outer() {
            return this.$outer;
        }

        public Map(ParArray<T> $outer, Function1<T, S> f, Object[] targetarr, int offset, int howmany) {
            this.f = f;
            this.targetarr = targetarr;
            this.offset = offset;
            this.howmany = howmany;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            this.result = BoxedUnit.UNIT;
        }
    }

    public class ScanToArray<U>
    implements Task<BoxedUnit, ScanToArray<U>> {
        private final ParIterableLike.ScanTree<U> tree;
        private final U z;
        private final Function2<U, U, U> op;
        private final Object[] targetarr;
        private BoxedUnit result;
        public final /* synthetic */ ParArray $outer;
        private volatile Throwable throwable;

        @Override
        public Throwable throwable() {
            return this.throwable;
        }

        @Override
        @TraitSetter
        public void throwable_$eq(Throwable x$1) {
            this.throwable = x$1;
        }

        @Override
        public Object repr() {
            return Task$class.repr(this);
        }

        @Override
        public void merge(Object that) {
            Task$class.merge(this, that);
        }

        @Override
        public void forwardThrowable() {
            Task$class.forwardThrowable(this);
        }

        @Override
        public void tryLeaf(Option<BoxedUnit> lastres) {
            Task$class.tryLeaf(this, lastres);
        }

        @Override
        public void tryMerge(Object t) {
            Task$class.tryMerge(this, t);
        }

        @Override
        public void mergeThrowables(Task<?, ?> that) {
            Task$class.mergeThrowables(this, that);
        }

        @Override
        public void signalAbort() {
            Task$class.signalAbort(this);
        }

        @Override
        public void result() {
        }

        @Override
        public void result_$eq(BoxedUnit x$1) {
            this.result = x$1;
        }

        @Override
        public void leaf(Option<BoxedUnit> prev) {
            this.iterate(this.tree);
        }

        private void iterate(ParIterableLike.ScanTree<U> tree) {
            block5: {
                block4: {
                    ParIterableLike.ScanLeaf scanLeaf;
                    boolean bl;
                    block3: {
                        while (true) {
                            bl = false;
                            scanLeaf = null;
                            if (!(tree instanceof ParIterableLike.ScanNode)) break;
                            ParIterableLike.ScanNode scanNode = (ParIterableLike.ScanNode)tree;
                            this.iterate(scanNode.left());
                            tree = scanNode.right();
                        }
                        if (!(tree instanceof ParIterableLike.ScanLeaf)) break block3;
                        bl = true;
                        scanLeaf = (ParIterableLike.ScanLeaf)tree;
                        if (!(scanLeaf.prev() instanceof Some)) break block3;
                        Some some = (Some)scanLeaf.prev();
                        this.scanLeaf(this.scala$collection$parallel$mutable$ParArray$ScanToArray$$$outer().scala$collection$parallel$mutable$ParArray$$array(), this.targetarr, scanLeaf.from(), scanLeaf.len(), ((ParIterableLike.ScanLeaf)some.x()).acc());
                        break block4;
                    }
                    if (!bl || !None$.MODULE$.equals(scanLeaf.prev())) break block5;
                    this.scanLeaf(this.scala$collection$parallel$mutable$ParArray$ScanToArray$$$outer().scala$collection$parallel$mutable$ParArray$$array(), this.targetarr, scanLeaf.from(), scanLeaf.len(), this.z);
                }
                return;
            }
            throw new MatchError(tree);
        }

        private void scanLeaf(Object[] srcarr, Object[] targetarr, int from2, int len, U startval) {
            int i = from2;
            int until2 = from2 + len;
            U curr = startval;
            Function2<U, U, U> operation = this.op;
            while (i < until2) {
                curr = operation.apply(curr, srcarr[i]);
                targetarr[++i] = curr;
            }
        }

        @Override
        public Seq<Task<BoxedUnit, ScanToArray<U>>> split() {
            ParIterableLike.ScanTree<U> scanTree = this.tree;
            if (scanTree instanceof ParIterableLike.ScanNode) {
                ParIterableLike.ScanNode scanNode = (ParIterableLike.ScanNode)scanTree;
                return (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new ScanToArray[]{new ScanToArray(this.scala$collection$parallel$mutable$ParArray$ScanToArray$$$outer(), scanNode.left(), this.z, this.op, this.targetarr), new ScanToArray(this.scala$collection$parallel$mutable$ParArray$ScanToArray$$$outer(), scanNode.right(), this.z, this.op, this.targetarr)}));
            }
            throw scala.sys.package$.MODULE$.error("Can only split scan tree internal nodes.");
        }

        @Override
        public boolean shouldSplitFurther() {
            ParIterableLike.ScanTree<U> scanTree = this.tree;
            boolean bl = scanTree instanceof ParIterableLike.ScanNode;
            return bl;
        }

        public /* synthetic */ ParArray scala$collection$parallel$mutable$ParArray$ScanToArray$$$outer() {
            return this.$outer;
        }

        public ScanToArray(ParArray<T> $outer, ParIterableLike.ScanTree<U> tree, U z, Function2<U, U, U> op, Object[] targetarr) {
            this.tree = tree;
            this.z = z;
            this.op = op;
            this.targetarr = targetarr;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            this.result = BoxedUnit.UNIT;
        }
    }

    public class ParArrayIterator
    implements SeqSplitter<T> {
        private int i;
        private final int until;
        private final Object[] arr;
        public final /* synthetic */ ParArray $outer;
        private Signalling signalDelegate;

        @Override
        public Seq<SeqSplitter<T>> splitWithSignalling() {
            return SeqSplitter$class.splitWithSignalling(this);
        }

        @Override
        public Seq<SeqSplitter<T>> psplitWithSignalling(Seq<Object> sizes) {
            return SeqSplitter$class.psplitWithSignalling(this, sizes);
        }

        @Override
        public SeqSplitter.Taken newTaken(int until2) {
            return SeqSplitter$class.newTaken(this, until2);
        }

        @Override
        public SeqSplitter<T> take(int n) {
            return SeqSplitter$class.take(this, n);
        }

        @Override
        public SeqSplitter<T> slice(int from1, int until1) {
            return SeqSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> SeqSplitter.Mapped<S> map(Function1<T, S> f) {
            return SeqSplitter$class.map(this, f);
        }

        @Override
        public <U, PI extends SeqSplitter<U>> SeqSplitter.Appended<U, PI> appendParSeq(PI that) {
            return SeqSplitter$class.appendParSeq(this, that);
        }

        @Override
        public <S> SeqSplitter.Zipped<S> zipParSeq(SeqSplitter<S> that) {
            return SeqSplitter$class.zipParSeq(this, that);
        }

        @Override
        public <S, U, R> SeqSplitter.ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
            return SeqSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
        }

        @Override
        public SeqSplitter<T> reverse() {
            return SeqSplitter$class.reverse(this);
        }

        @Override
        public <U> SeqSplitter.Patched<U> patchParSeq(int from2, SeqSplitter<U> patchElems, int replaced) {
            return SeqSplitter$class.patchParSeq(this, from2, patchElems, replaced);
        }

        @Override
        public <S> boolean corresponds(Function2<T, S, Object> corr, Iterator<S> that) {
            return AugmentedSeqIterator$class.corresponds(this, corr, that);
        }

        @Override
        public <S, That> Combiner<S, That> reverseMap2combiner(Function1<T, S> f, Combiner<S, That> cb) {
            return AugmentedSeqIterator$class.reverseMap2combiner(this, f, cb);
        }

        @Override
        public <U, That> Combiner<U, That> updated2combiner(int index, U elem, Combiner<U, That> cb) {
            return AugmentedSeqIterator$class.updated2combiner(this, index, elem, cb);
        }

        @Override
        public Signalling signalDelegate() {
            return this.signalDelegate;
        }

        @Override
        @TraitSetter
        public void signalDelegate_$eq(Signalling x$1) {
            this.signalDelegate = x$1;
        }

        @Override
        public <S> boolean shouldSplitFurther(scala.collection.parallel.ParIterable<S> coll, int parallelismLevel) {
            return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
        }

        @Override
        public String buildString(Function1<Function1<String, BoxedUnit>, BoxedUnit> closure) {
            return IterableSplitter$class.buildString(this, closure);
        }

        @Override
        public String debugInformation() {
            return IterableSplitter$class.debugInformation(this);
        }

        @Override
        public <U extends IterableSplitter.Taken> U newSliceInternal(U it, int from1) {
            return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
        }

        @Override
        public <U, PI extends IterableSplitter<U>> IterableSplitter.Appended<U, PI> appendParIterable(PI that) {
            return IterableSplitter$class.appendParIterable(this, that);
        }

        @Override
        public boolean isAborted() {
            return DelegatedSignalling$class.isAborted(this);
        }

        @Override
        public void abort() {
            DelegatedSignalling$class.abort(this);
        }

        @Override
        public int indexFlag() {
            return DelegatedSignalling$class.indexFlag(this);
        }

        @Override
        public void setIndexFlag(int f) {
            DelegatedSignalling$class.setIndexFlag(this, f);
        }

        @Override
        public void setIndexFlagIfGreater(int f) {
            DelegatedSignalling$class.setIndexFlagIfGreater(this, f);
        }

        @Override
        public void setIndexFlagIfLesser(int f) {
            DelegatedSignalling$class.setIndexFlagIfLesser(this, f);
        }

        @Override
        public int tag() {
            return DelegatedSignalling$class.tag(this);
        }

        @Override
        public <U> U reduce(Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.reduce(this, op);
        }

        @Override
        public <U> T min(Ordering<U> ord) {
            return AugmentedIterableIterator$class.min(this, ord);
        }

        @Override
        public <U> T max(Ordering<U> ord) {
            return AugmentedIterableIterator$class.max(this, ord);
        }

        @Override
        public <U> U reduceLeft(int howmany, Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
        }

        @Override
        public <U, This> Combiner<U, This> slice2combiner(int from2, int until2, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.slice2combiner(this, from2, until2, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner<U, This> before, Combiner<U, This> after) {
            return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1<T, Object> p, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1<T, Object> p, Combiner<U, This> before, Combiner<U, This> after) {
            return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
        }

        @Override
        public <U, That> Combiner<U, That> scanToCombiner(U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
            return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
        }

        @Override
        public <U, That> Combiner<U, That> scanToCombiner(int howmany, U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
            return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
        }

        @Override
        public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator<S> otherpit, Combiner<Tuple2<U, S>, That> cb) {
            return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
        }

        @Override
        public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator<S> that, U thiselem, S thatelem, Combiner<Tuple2<U, S>, That> cb) {
            return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
        }

        @Override
        public boolean isRemainingCheap() {
            return RemainsIterator$class.isRemainingCheap(this);
        }

        @Override
        public Iterator<T> seq() {
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
        public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
            return Iterator$class.$plus$plus(this, that);
        }

        @Override
        public <B> Iterator<B> flatMap(Function1<T, GenTraversableOnce<B>> f) {
            return Iterator$class.flatMap(this, f);
        }

        @Override
        public Iterator<T> filter(Function1<T, Object> p) {
            return Iterator$class.filter(this, p);
        }

        @Override
        public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<T, B, Object> p) {
            return Iterator$class.corresponds(this, that, p);
        }

        @Override
        public Iterator<T> withFilter(Function1<T, Object> p) {
            return Iterator$class.withFilter(this, p);
        }

        @Override
        public Iterator<T> filterNot(Function1<T, Object> p) {
            return Iterator$class.filterNot(this, p);
        }

        @Override
        public <B> Iterator<B> collect(PartialFunction<T, B> pf) {
            return Iterator$class.collect(this, pf);
        }

        @Override
        public <B> Iterator<B> scanLeft(B z, Function2<B, T, B> op) {
            return Iterator$class.scanLeft(this, z, op);
        }

        @Override
        public <B> Iterator<B> scanRight(B z, Function2<T, B, B> op) {
            return Iterator$class.scanRight(this, z, op);
        }

        @Override
        public Iterator<T> takeWhile(Function1<T, Object> p) {
            return Iterator$class.takeWhile(this, p);
        }

        @Override
        public Tuple2<Iterator<T>, Iterator<T>> partition(Function1<T, Object> p) {
            return Iterator$class.partition(this, p);
        }

        @Override
        public Tuple2<Iterator<T>, Iterator<T>> span(Function1<T, Object> p) {
            return Iterator$class.span(this, p);
        }

        @Override
        public Iterator<T> dropWhile(Function1<T, Object> p) {
            return Iterator$class.dropWhile(this, p);
        }

        @Override
        public <B> Iterator<Tuple2<T, B>> zip(Iterator<B> that) {
            return Iterator$class.zip(this, that);
        }

        @Override
        public <A1> Iterator<A1> padTo(int len, A1 elem) {
            return Iterator$class.padTo(this, len, elem);
        }

        @Override
        public Iterator<Tuple2<T, Object>> zipWithIndex() {
            return Iterator$class.zipWithIndex(this);
        }

        @Override
        public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
            return Iterator$class.zipAll(this, that, thisElem, thatElem);
        }

        @Override
        public boolean contains(Object elem) {
            return Iterator$class.contains(this, elem);
        }

        @Override
        public <B> int indexOf(B elem) {
            return Iterator$class.indexOf(this, elem);
        }

        @Override
        public BufferedIterator<T> buffered() {
            return Iterator$class.buffered(this);
        }

        @Override
        public <B> Iterator.GroupedIterator<B> grouped(int size2) {
            return Iterator$class.grouped(this, size2);
        }

        @Override
        public <B> Iterator.GroupedIterator<B> sliding(int size2, int step) {
            return Iterator$class.sliding(this, size2, step);
        }

        @Override
        public int length() {
            return Iterator$class.length(this);
        }

        @Override
        public Tuple2<Iterator<T>, Iterator<T>> duplicate() {
            return Iterator$class.duplicate(this);
        }

        @Override
        public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
            return Iterator$class.patch(this, from2, patchElems, replaced);
        }

        @Override
        public Traversable<T> toTraversable() {
            return Iterator$class.toTraversable(this);
        }

        @Override
        public Iterator<T> toIterator() {
            return Iterator$class.toIterator(this);
        }

        @Override
        public Stream<T> toStream() {
            return Iterator$class.toStream(this);
        }

        @Override
        public <B> int sliding$default$2() {
            return Iterator$class.sliding$default$2(this);
        }

        @Override
        public List<T> reversed() {
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
        public <B> Option<B> collectFirst(PartialFunction<T, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, T, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<T, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldRight(B z, Function2<T, B, B> op) {
            return (B)TraversableOnce$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, T, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> B reduceRight(Function2<T, B, B> op) {
            return (B)TraversableOnce$class.reduceRight(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, T, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<T, B, B> op) {
            return TraversableOnce$class.reduceRightOption(this, op);
        }

        @Override
        public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
            return TraversableOnce$class.reduceOption(this, op);
        }

        @Override
        public <B> T maxBy(Function1<T, B> f, Ordering<B> cmp) {
            return TraversableOnce$class.maxBy(this, f, cmp);
        }

        @Override
        public <B> T minBy(Function1<T, B> f, Ordering<B> cmp) {
            return TraversableOnce$class.minBy(this, f, cmp);
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
        public List<T> toList() {
            return TraversableOnce$class.toList(this);
        }

        @Override
        public Iterable<T> toIterable() {
            return TraversableOnce$class.toIterable(this);
        }

        @Override
        public Seq<T> toSeq() {
            return TraversableOnce$class.toSeq(this);
        }

        @Override
        public IndexedSeq<T> toIndexedSeq() {
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
        public Vector<T> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, T, Col> cbf) {
            return (Col)TraversableOnce$class.to(this, cbf);
        }

        @Override
        public <T, U> scala.collection.immutable.Map<T, U> toMap(Predef$.less.colon.less<T, Tuple2<T, U>> ev) {
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

        public /* synthetic */ Combiner scala$collection$parallel$mutable$ParArray$ParArrayIterator$$super$reverse2combiner(Combiner cb) {
            return AugmentedSeqIterator$class.reverse2combiner(this, cb);
        }

        public int i() {
            return this.i;
        }

        public void i_$eq(int x$1) {
            this.i = x$1;
        }

        public int until() {
            return this.until;
        }

        public Object[] arr() {
            return this.arr;
        }

        @Override
        public boolean hasNext() {
            return this.i() < this.until();
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public T next() {
            void var1_1;
            Object elem = this.arr()[this.i()];
            this.i_$eq(this.i() + 1);
            return var1_1;
        }

        @Override
        public int remaining() {
            return this.until() - this.i();
        }

        public ParArrayIterator dup() {
            return new ParArrayIterator(this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer(), this.i(), this.until(), this.arr());
        }

        @Override
        public Seq<ParArrayIterator> psplit(Seq<Object> sizesIncomplete) {
            int left;
            IntRef traversed = IntRef.create(this.i());
            int total2 = BoxesRunTime.unboxToInt(sizesIncomplete.reduceLeft(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final int apply(int x$1, int x$2) {
                    return x$1 + x$2;
                }

                public int apply$mcIII$sp(int x$1, int x$2) {
                    return x$1 + x$2;
                }
            }));
            Seq<Object> sizes = total2 >= (left = this.remaining()) ? sizesIncomplete : sizesIncomplete.$colon$plus(BoxesRunTime.boxToInteger(left - total2), Seq$.MODULE$.canBuildFrom());
            return sizes.map(new Serializable(this, traversed){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ParArrayIterator $outer;
                private final IntRef traversed$1;

                public final ParArrayIterator apply(int sz) {
                    ParArrayIterator parArrayIterator;
                    if (this.traversed$1.elem < this.$outer.until()) {
                        int end;
                        int start = this.traversed$1.elem;
                        int n = this.traversed$1.elem + sz;
                        Predef$ predef$ = Predef$.MODULE$;
                        this.traversed$1.elem = end = RichInt$.MODULE$.min$extension(n, this.$outer.until());
                        parArrayIterator = new ParArrayIterator(this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer(), start, end, this.$outer.arr());
                    } else {
                        parArrayIterator = new ParArrayIterator(this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer(), this.traversed$1.elem, this.traversed$1.elem, this.$outer.arr());
                    }
                    return parArrayIterator;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.traversed$1 = traversed$1;
                }
            }, Seq$.MODULE$.canBuildFrom());
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public Seq<ParArrayIterator> split() {
            Seq seq;
            int left = this.remaining();
            if (left >= 2) {
                void var3_3;
                int splitpoint = left / 2;
                Seq sq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new ParArrayIterator[]{new ParArrayIterator(this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer(), this.i(), this.i() + splitpoint, this.arr()), new ParArrayIterator(this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer(), this.i() + splitpoint, this.until(), this.arr())}));
                this.i_$eq(this.until());
                seq = var3_3;
            } else {
                seq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new ParArrayIterator[]{this}));
            }
            return seq;
        }

        @Override
        public String toString() {
            return new StringBuilder().append((Object)"ParArrayIterator(").append(BoxesRunTime.boxToInteger(this.i())).append((Object)", ").append(BoxesRunTime.boxToInteger(this.until())).append((Object)")").toString();
        }

        @Override
        public <U> void foreach(Function1<T, U> f) {
            this.foreach_quick(f, this.arr(), this.until(), this.i());
            this.i_$eq(this.until());
        }

        private <U> void foreach_quick(Function1<T, U> f, Object[] a, int ntil, int from2) {
            for (int j = from2; j < ntil; ++j) {
                f.apply(a[j]);
            }
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public int count(Function1<T, Object> p) {
            void var2_2;
            int c = this.count_quick(p, this.arr(), this.until(), this.i());
            this.i_$eq(this.until());
            return (int)var2_2;
        }

        private int count_quick(Function1<T, Object> p, Object[] a, int ntil, int from2) {
            int cnt = 0;
            for (int j = from2; j < ntil; ++j) {
                if (!BoxesRunTime.unboxToBoolean(p.apply(a[j]))) continue;
                ++cnt;
            }
            return cnt;
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public <S> S foldLeft(S z, Function2<S, T, S> op) {
            void var3_3;
            S r = this.foldLeft_quick(this.arr(), this.until(), op, z);
            this.i_$eq(this.until());
            return var3_3;
        }

        private <S> S foldLeft_quick(Object[] a, int ntil, Function2<S, T, S> op, S z) {
            S sum2 = z;
            for (int j = this.i(); j < ntil; ++j) {
                sum2 = op.apply(sum2, a[j]);
            }
            return sum2;
        }

        @Override
        public <U> U fold(U z, Function2<U, U, U> op) {
            return this.foldLeft((S)z, (Function2)op);
        }

        @Override
        public <S> S aggregate(Function0<S> z, Function2<S, T, S> seqop, Function2<S, S, S> combop) {
            return this.foldLeft(z.apply(), seqop);
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public <U> U sum(Numeric<U> num) {
            void var2_2;
            U s2 = this.sum_quick(num, this.arr(), this.until(), this.i(), num.zero());
            this.i_$eq(this.until());
            return var2_2;
        }

        private <U> U sum_quick(Numeric<U> num, Object[] a, int ntil, int from2, U zero) {
            Object sum2 = zero;
            for (int j = from2; j < ntil; ++j) {
                sum2 = num.plus(sum2, a[j]);
            }
            return sum2;
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public <U> U product(Numeric<U> num) {
            void var2_2;
            U p = this.product_quick(num, this.arr(), this.until(), this.i(), num.one());
            this.i_$eq(this.until());
            return var2_2;
        }

        private <U> U product_quick(Numeric<U> num, Object[] a, int ntil, int from2, U one) {
            Object prod = one;
            for (int j = from2; j < ntil; ++j) {
                prod = num.times(prod, a[j]);
            }
            return prod;
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public boolean forall(Function1<T, Object> p) {
            void var2_2;
            if (this.isAborted()) {
                return false;
            }
            boolean all = true;
            while (this.i() < this.until()) {
                int nextuntil = this.i() + package$.MODULE$.CHECK_RATE() > this.until() ? this.until() : this.i() + package$.MODULE$.CHECK_RATE();
                all = this.forall_quick(p, this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().scala$collection$parallel$mutable$ParArray$$array(), nextuntil, this.i());
                if (all) {
                    this.i_$eq(nextuntil);
                } else {
                    this.i_$eq(this.until());
                    this.abort();
                }
                if (!this.isAborted()) continue;
                return false;
            }
            return (boolean)var2_2;
        }

        private boolean forall_quick(Function1<T, Object> p, Object[] a, int nextuntil, int start) {
            for (int j = start; j < nextuntil; ++j) {
                if (BoxesRunTime.unboxToBoolean(p.apply(a[j]))) {
                    continue;
                }
                return false;
            }
            return true;
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public boolean exists(Function1<T, Object> p) {
            void var2_2;
            if (this.isAborted()) {
                return true;
            }
            boolean some = false;
            while (this.i() < this.until()) {
                int nextuntil = this.i() + package$.MODULE$.CHECK_RATE() > this.until() ? this.until() : this.i() + package$.MODULE$.CHECK_RATE();
                some = this.exists_quick(p, this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().scala$collection$parallel$mutable$ParArray$$array(), nextuntil, this.i());
                if (some) {
                    this.i_$eq(this.until());
                    this.abort();
                } else {
                    this.i_$eq(nextuntil);
                }
                if (!this.isAborted()) continue;
                return true;
            }
            return (boolean)var2_2;
        }

        private boolean exists_quick(Function1<T, Object> p, Object[] a, int nextuntil, int start) {
            for (int j = start; j < nextuntil; ++j) {
                if (!BoxesRunTime.unboxToBoolean(p.apply(a[j]))) continue;
                return true;
            }
            return false;
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public Option<T> find(Function1<T, Object> p) {
            void var2_2;
            if (this.isAborted()) {
                return None$.MODULE$;
            }
            Option r = None$.MODULE$;
            while (this.i() < this.until()) {
                int nextuntil = this.i() + package$.MODULE$.CHECK_RATE() < this.until() ? this.i() + package$.MODULE$.CHECK_RATE() : this.until();
                r = this.find_quick(p, this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().scala$collection$parallel$mutable$ParArray$$array(), nextuntil, this.i());
                None$ none$ = None$.MODULE$;
                if (r != null && r.equals(none$)) {
                    this.i_$eq(nextuntil);
                } else {
                    this.i_$eq(this.until());
                    this.abort();
                }
                if (!this.isAborted()) continue;
                return r;
            }
            return var2_2;
        }

        private Option<T> find_quick(Function1<T, Object> p, Object[] a, int nextuntil, int start) {
            for (int j = start; j < nextuntil; ++j) {
                Object elem = a[j];
                if (!BoxesRunTime.unboxToBoolean(p.apply(elem))) continue;
                return new Some<Object>(elem);
            }
            return None$.MODULE$;
        }

        public ParArrayIterator drop(int n) {
            this.i_$eq(this.i() + n);
            return this;
        }

        @Override
        public <U> void copyToArray(Object array, int from2, int len) {
            int n = this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().length() - this.i();
            Predef$ predef$ = Predef$.MODULE$;
            int n2 = RichInt$.MODULE$.min$extension(n, len);
            Predef$ predef$2 = Predef$.MODULE$;
            int totallen = RichInt$.MODULE$.min$extension(n2, ScalaRunTime$.MODULE$.array_length(array) - from2);
            Array$.MODULE$.copy(this.arr(), this.i(), array, from2, totallen);
            this.i_$eq(this.i() + totallen);
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public int prefixLength(Function1<T, Object> pred) {
            void var2_2;
            int r = this.prefixLength_quick(pred, this.arr(), this.until(), this.i());
            this.i_$eq(this.i() + (r + 1));
            return (int)var2_2;
        }

        private int prefixLength_quick(Function1<T, Object> pred, Object[] a, int ntil, int startpos) {
            int j = startpos;
            int endpos = ntil;
            while (j < endpos) {
                if (BoxesRunTime.unboxToBoolean(pred.apply(a[j]))) {
                    ++j;
                    continue;
                }
                endpos = j;
            }
            return endpos - startpos;
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public int indexWhere(Function1<T, Object> pred) {
            void var3_3;
            int r = this.indexWhere_quick(pred, this.arr(), this.until(), this.i());
            int ret = r != -1 ? r - this.i() : r;
            this.i_$eq(this.until());
            return (int)var3_3;
        }

        private int indexWhere_quick(Function1<T, Object> pred, Object[] a, int ntil, int from2) {
            int j = from2;
            int pos = -1;
            while (j < ntil) {
                if (BoxesRunTime.unboxToBoolean(pred.apply(a[j]))) {
                    pos = j;
                    j = ntil;
                    continue;
                }
                ++j;
            }
            return pos;
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public int lastIndexWhere(Function1<T, Object> pred) {
            void var3_3;
            int r = this.lastIndexWhere_quick(pred, this.arr(), this.i(), this.until());
            int ret = r != -1 ? r - this.i() : r;
            this.i_$eq(this.until());
            return (int)var3_3;
        }

        private int lastIndexWhere_quick(Function1<T, Object> pred, Object[] a, int from2, int ntil) {
            int pos = -1;
            int j = ntil - 1;
            while (j >= from2) {
                if (BoxesRunTime.unboxToBoolean(pred.apply(a[j]))) {
                    pos = j;
                    j = -1;
                    continue;
                }
                --j;
            }
            return pos;
        }

        @Override
        public boolean sameElements(Iterator<?> that) {
            boolean same = true;
            while (this.i() < this.until() && that.hasNext()) {
                Object obj = that.next();
                Object object = this.arr()[this.i()];
                if (!(object == obj ? true : (object == null ? false : (object instanceof Number ? BoxesRunTime.equalsNumObject((Number)object, obj) : (object instanceof Character ? BoxesRunTime.equalsCharObject((Character)object, obj) : object.equals(obj)))))) {
                    this.i_$eq(this.until());
                    same = false;
                }
                this.i_$eq(this.i() + 1);
            }
            return same;
        }

        @Override
        public <S, That> Combiner<S, That> map2combiner(Function1<T, S> f, Combiner<S, That> cb) {
            cb.sizeHint(this.remaining());
            this.map2combiner_quick(f, this.arr(), cb, this.until(), this.i());
            this.i_$eq(this.until());
            return cb;
        }

        private <S, That> void map2combiner_quick(Function1<T, S> f, Object[] a, Builder<S, That> cb, int ntil, int from2) {
            for (int j = from2; j < ntil; ++j) {
                cb.$plus$eq(f.apply(a[j]));
            }
        }

        @Override
        public <S, That> Combiner<S, That> collect2combiner(PartialFunction<T, S> pf, Combiner<S, That> cb) {
            this.collect2combiner_quick(pf, this.arr(), cb, this.until(), this.i());
            this.i_$eq(this.until());
            return cb;
        }

        private <S, That> void collect2combiner_quick(PartialFunction<T, S> pf, Object[] a, Builder<S, That> cb, int ntil, int from2) {
            Function1 runWith2 = pf.runWith(new Serializable(this, cb){
                public static final long serialVersionUID = 0L;
                private final Builder cb$2;

                public final Builder<S, That> apply(S b) {
                    return this.cb$2.$plus$eq(b);
                }
                {
                    this.cb$2 = cb$2;
                }
            });
            for (int j = from2; j < ntil; ++j) {
                Object curr = a[j];
                runWith2.apply(curr);
            }
        }

        @Override
        public <S, That> Combiner<S, That> flatmap2combiner(Function1<T, GenTraversableOnce<S>> f, Combiner<S, That> cb) {
            while (this.i() < this.until()) {
                GenTraversableOnce<S> traversable = f.apply(this.arr()[this.i()]);
                Growable<Object> growable = traversable instanceof Iterable ? cb.$plus$plus$eq(((Iterable)traversable).iterator()) : cb.$plus$plus$eq(traversable.seq());
                this.i_$eq(this.i() + 1);
            }
            return cb;
        }

        @Override
        public <U, This> Combiner<U, This> filter2combiner(Function1<T, Object> pred, Combiner<U, This> cb) {
            this.filter2combiner_quick(pred, cb, this.arr(), this.until(), this.i());
            this.i_$eq(this.until());
            return cb;
        }

        private <U, This> void filter2combiner_quick(Function1<T, Object> pred, Builder<U, This> cb, Object[] a, int ntil, int from2) {
            for (int j = this.i(); j < ntil; ++j) {
                Object curr = a[j];
                Object object = BoxesRunTime.unboxToBoolean(pred.apply(curr)) ? cb.$plus$eq((U)curr) : BoxedUnit.UNIT;
            }
        }

        @Override
        public <U, This> Combiner<U, This> filterNot2combiner(Function1<T, Object> pred, Combiner<U, This> cb) {
            this.filterNot2combiner_quick(pred, cb, this.arr(), this.until(), this.i());
            this.i_$eq(this.until());
            return cb;
        }

        private <U, This> void filterNot2combiner_quick(Function1<T, Object> pred, Builder<U, This> cb, Object[] a, int ntil, int from2) {
            for (int j = this.i(); j < ntil; ++j) {
                Object curr = a[j];
                Object object = BoxesRunTime.unboxToBoolean(pred.apply(curr)) ? BoxedUnit.UNIT : cb.$plus$eq((U)curr);
            }
        }

        @Override
        public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Bld cb) {
            cb.sizeHint(this.remaining());
            this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().builder2ops(cb).ifIs(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ParArrayIterator $outer;

                public final void apply(ResizableParArrayCombiner<T> pac) {
                    Object[] targetarr = ((ExposedArrayBuffer)pac.lastbuff()).internalArray();
                    Array$.MODULE$.copy(this.$outer.arr(), this.$outer.i(), targetarr, ((SeqLike)pac.lastbuff()).size(), this.$outer.until() - this.$outer.i());
                    ((ExposedArrayBuffer)pac.lastbuff()).setInternalSize(this.$outer.remaining());
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }).otherwise((Function0<BoxedUnit>)((Object)new Serializable(this, cb){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ ParArrayIterator $outer;
                public final Builder cb$3;

                public final void apply() {
                    this.apply$mcV$sp();
                }

                public void apply$mcV$sp() {
                    this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().builder2ops(this.cb$3).ifIs(new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ ParArrayIterator$$anonfun$copy2builder$1 $outer;

                        public final void apply(UnrolledParArrayCombiner<T> pac) {
                            Object[] targetarr = (Object[])pac.buff().lastPtr().array();
                            Array$.MODULE$.copy(this.$outer.$outer.arr(), this.$outer.$outer.i(), targetarr, 0, this.$outer.$outer.until() - this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i());
                            pac.buff().size_$eq(pac.buff().size() + this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().until() - this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i());
                            pac.buff().lastPtr().size_$eq(this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().until() - this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i());
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }).otherwise((Function0<BoxedUnit>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ ParArrayIterator$$anonfun$copy2builder$1 $outer;

                        public final void apply() {
                            this.apply$mcV$sp();
                        }

                        public void apply$mcV$sp() {
                            this.$outer.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$copy2builder_quick(this.$outer.cb$3, this.$outer.$outer.arr(), this.$outer.$outer.until(), this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i());
                            this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i_$eq(this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().until());
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }), ClassTag$.MODULE$.apply(UnrolledParArrayCombiner.class));
                }

                public /* synthetic */ ParArrayIterator scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer() {
                    return this.$outer;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.cb$3 = cb$3;
                }
            }), ClassTag$.MODULE$.apply(ResizableParArrayCombiner.class));
            return cb;
        }

        public <U, Coll> void scala$collection$parallel$mutable$ParArray$ParArrayIterator$$copy2builder_quick(Builder<U, Coll> b, Object[] a, int ntil, int from2) {
            for (int j = from2; j < ntil; ++j) {
                b.$plus$eq((U)a[j]);
            }
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1<T, Object> pred, Combiner<U, This> btrue, Combiner<U, This> bfalse) {
            this.partition2combiners_quick(pred, btrue, bfalse, this.arr(), this.until(), this.i());
            this.i_$eq(this.until());
            return new Tuple2<Combiner<U, This>, Combiner<U, This>>(btrue, bfalse);
        }

        private <U, This> void partition2combiners_quick(Function1<T, Object> p, Builder<U, This> btrue, Builder<U, This> bfalse, Object[] a, int ntil, int from2) {
            for (int j = from2; j < ntil; ++j) {
                Object curr = a[j];
                Builder<Object, This> builder = BoxesRunTime.unboxToBoolean(p.apply(curr)) ? btrue.$plus$eq((U)curr) : bfalse.$plus$eq((U)curr);
            }
        }

        @Override
        public <U, This> Combiner<U, This> take2combiner(int n, Combiner<U, This> cb) {
            cb.sizeHint(n);
            int ntil = this.i() + n;
            Object[] a = this.arr();
            while (this.i() < ntil) {
                cb.$plus$eq((U)a[this.i()]);
                this.i_$eq(this.i() + 1);
            }
            return cb;
        }

        @Override
        public <U, This> Combiner<U, This> drop2combiner(int n, Combiner<U, This> cb) {
            this.drop(n);
            cb.sizeHint(this.remaining());
            while (this.i() < this.until()) {
                cb.$plus$eq((U)this.arr()[this.i()]);
                this.i_$eq(this.i() + 1);
            }
            return cb;
        }

        @Override
        public <U, This> Combiner<U, This> reverse2combiner(Combiner<U, This> cb) {
            this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().builder2ops(cb).ifIs(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ParArrayIterator $outer;

                public final void apply(ResizableParArrayCombiner<T> pac) {
                    int sz = this.$outer.remaining();
                    pac.sizeHint(sz);
                    Object[] targetarr = ((ExposedArrayBuffer)pac.lastbuff()).internalArray();
                    this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$reverse2combiner_quick(targetarr, this.$outer.arr(), 0, this.$outer.i(), this.$outer.until());
                    ((ExposedArrayBuffer)pac.lastbuff()).setInternalSize(sz);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }).otherwise((Function0<BoxedUnit>)((Object)new Serializable(this, cb){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ ParArrayIterator $outer;
                public final Combiner cb$4;

                public final void apply() {
                    this.apply$mcV$sp();
                }

                public void apply$mcV$sp() {
                    this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().builder2ops(this.cb$4).ifIs(new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ ParArrayIterator$$anonfun$reverse2combiner$1 $outer;

                        public final void apply(UnrolledParArrayCombiner<T> pac) {
                            int sz = this.$outer.$outer.remaining();
                            pac.sizeHint(sz);
                            Object[] targetarr = (Object[])pac.buff().lastPtr().array();
                            this.$outer.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$reverse2combiner_quick(targetarr, this.$outer.$outer.arr(), 0, this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i(), this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().until());
                            pac.buff().size_$eq(pac.buff().size() + sz);
                            pac.buff().lastPtr().size_$eq(sz);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }).otherwise((Function0<BoxedUnit>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        public final /* synthetic */ ParArrayIterator$$anonfun$reverse2combiner$1 $outer;

                        public final void apply() {
                            this.$outer.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$super$reverse2combiner(this.$outer.cb$4);
                        }

                        public void apply$mcV$sp() {
                            this.$outer.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$super$reverse2combiner(this.$outer.cb$4);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }), ClassTag$.MODULE$.apply(UnrolledParArrayCombiner.class));
                }

                public /* synthetic */ ParArrayIterator scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer() {
                    return this.$outer;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.cb$4 = cb$4;
                }
            }), ClassTag$.MODULE$.apply(ResizableParArrayCombiner.class));
            return cb;
        }

        public void scala$collection$parallel$mutable$ParArray$ParArrayIterator$$reverse2combiner_quick(Object[] targ, Object[] a, int targfrom, int srcfrom, int srcuntil) {
            int j = srcfrom;
            int k = targfrom + srcuntil - srcfrom - 1;
            while (j < srcuntil) {
                targ[k] = a[j];
                ++j;
                --k;
            }
        }

        @Override
        public <U, A> void scanToArray(U z, Function2<U, U, U> op, Object destarr, int from2) {
            this.scanToArray_quick(this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().scala$collection$parallel$mutable$ParArray$$array(), (Object[])destarr, op, z, this.i(), this.until(), from2);
            this.i_$eq(this.until());
        }

        public <U> void scanToArray_quick(Object[] srcarr, Object[] destarr, Function2<U, U, U> op, U z, int srcfrom, int srcntil, int destfrom) {
            U last2 = z;
            int j = srcfrom;
            int k = destfrom;
            while (j < srcntil) {
                last2 = op.apply(last2, srcarr[j]);
                destarr[k] = last2;
                ++j;
                ++k;
            }
        }

        public /* synthetic */ ParArray scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer() {
            return this.$outer;
        }

        public ParArrayIterator(ParArray<T> $outer, int i, int until2, Object[] arr) {
            this.i = i;
            this.until = until2;
            this.arr = arr;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            TraversableOnce$class.$init$(this);
            Iterator$class.$init$(this);
            RemainsIterator$class.$init$(this);
            AugmentedIterableIterator$class.$init$(this);
            DelegatedSignalling$class.$init$(this);
            IterableSplitter$class.$init$(this);
            AugmentedSeqIterator$class.$init$(this);
            SeqSplitter$class.$init$(this);
        }
    }
}

