/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Array$;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.GenMap;
import scala.collection.GenSeq;
import scala.collection.GenSeqLike;
import scala.collection.IndexedSeq;
import scala.collection.IndexedSeqOptimized$class;
import scala.collection.IterableLike;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.SeqLike$CombinationsItr$;
import scala.collection.SeqLike$PermutationsItr$;
import scala.collection.SeqView;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericTraversableTemplate;
import scala.collection.generic.Growable;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayOps;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashMap$;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParSeq;
import scala.math.Numeric$IntIsIntegral$;
import scala.math.Ordering;
import scala.math.Ordering$Int$;
import scala.math.package$;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.RichInt$;

@ScalaSignature(bytes="\u0006\u0001\u0011\u0005f!C\u0001\u0003!\u0003\r\taBBz\u0005\u001d\u0019V-\u001d'jW\u0016T!a\u0001\u0003\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\u0006\u0003\u0015\u00198-\u00197b\u0007\u0001)2\u0001C\n\u001b'\u0015\u0001\u0011\"\u0004\u000f !\tQ1\"D\u0001\u0005\u0013\taAAA\u0002B]f\u0004BAD\b\u001235\t!!\u0003\u0002\u0011\u0005\ta\u0011\n^3sC\ndW\rT5lKB\u0011!c\u0005\u0007\u0001\t\u0019!\u0002\u0001\"b\u0001+\t\t\u0011)\u0005\u0002\u0017\u0013A\u0011!bF\u0005\u00031\u0011\u0011qAT8uQ&tw\r\u0005\u0002\u00135\u001111\u0004\u0001CC\u0002U\u0011AAU3qeB!a\"H\t\u001a\u0013\tq\"A\u0001\u0006HK:\u001cV-\u001d'jW\u0016\u0004BA\u0004\u0011\u0012E%\u0011\u0011E\u0001\u0002\u000f!\u0006\u0014\u0018\r\u001c7fY&T\u0018M\u00197f!\r\u0019c%E\u0007\u0002I)\u0011QEA\u0001\ta\u0006\u0014\u0018\r\u001c7fY&\u0011q\u0005\n\u0002\u0007!\u0006\u00148+Z9\t\u000b%\u0002A\u0011\u0001\u0016\u0002\r\u0011Jg.\u001b;%)\u0005Y\u0003C\u0001\u0006-\u0013\tiCA\u0001\u0003V]&$\bBB\u0018\u0001A\u0013E\u0003'\u0001\buQ&\u001c8i\u001c7mK\u000e$\u0018n\u001c8\u0016\u0003E\u00022A\u0004\u001a\u0012\u0013\t\u0019$AA\u0002TKFDa!\u000e\u0001!\n#2\u0014\u0001\u0004;p\u0007>dG.Z2uS>tGCA\u00198\u0011\u0015AD\u00071\u0001\u001a\u0003\u0011\u0011X\r\u001d:\t\u000bi\u0002a\u0011A\u001e\u0002\r1,gn\u001a;i+\u0005a\u0004C\u0001\u0006>\u0013\tqDAA\u0002J]RDQ\u0001\u0011\u0001\u0007\u0002\u0005\u000bQ!\u00199qYf$\"!\u0005\"\t\u000b\r{\u0004\u0019\u0001\u001f\u0002\u0007%$\u0007\u0010\u0003\u0004F\u0001\u0001&\tFR\u0001\fa\u0006\u00148i\\7cS:,'/F\u0001H!\u0011\u0019\u0003*\u0005\u0012\n\u0005%##\u0001C\"p[\nLg.\u001a:\t\u000b-\u0003A\u0011\u0001'\u0002\u001b1,gn\u001a;i\u0007>l\u0007/\u0019:f)\taT\nC\u0003O\u0015\u0002\u0007A(A\u0002mK:DQ\u0001\u0015\u0001\u0005BE\u000bq![:F[B$\u00180F\u0001S!\tQ1+\u0003\u0002U\t\t9!i\\8mK\u0006t\u0007\"\u0002,\u0001\t\u0003Z\u0014\u0001B:ju\u0016DQ\u0001\u0017\u0001\u0005\u0002e\u000bQb]3h[\u0016tG\u000fT3oORDGc\u0001\u001f[?\")1l\u0016a\u00019\u0006\t\u0001\u000f\u0005\u0003\u000b;F\u0011\u0016B\u00010\u0005\u0005%1UO\\2uS>t\u0017\u0007C\u0003a/\u0002\u0007A(\u0001\u0003ge>l\u0007\"\u00022\u0001\t\u0003\u0019\u0017AC5oI\u0016Dx\u000b[3sKR\u0019A\bZ3\t\u000bm\u000b\u0007\u0019\u0001/\t\u000b\u0001\f\u0007\u0019\u0001\u001f\t\u000b\u001d\u0004A\u0011\u00015\u0002\u001d1\f7\u000f^%oI\u0016Dx\u000b[3sKR\u0019A(\u001b6\t\u000bm3\u0007\u0019\u0001/\t\u000b-4\u0007\u0019\u0001\u001f\u0002\u0007\u0015tG\rC\u0003n\u0001\u0011\u0005a.\u0001\u0007qKJlW\u000f^1uS>t7/F\u0001p!\rq\u0001/G\u0005\u0003c\n\u0011\u0001\"\u0013;fe\u0006$xN\u001d\u0005\u0006g\u0002!\t\u0001^\u0001\rG>l'-\u001b8bi&|gn\u001d\u000b\u0003_VDQA\u001e:A\u0002q\n\u0011A\u001c\u0004\u0005q\u0002!\u0011PA\bQKJlW\u000f^1uS>t7/\u0013;s'\t9(\u0010E\u0002\u000fwfI!\u0001 \u0002\u0003!\u0005\u00137\u000f\u001e:bGRLE/\u001a:bi>\u0014\b\"\u0002@x\t\u0003y\u0018A\u0002\u001fj]&$h\b\u0006\u0002\u0002\u0002A\u0019\u00111A<\u000e\u0003\u0001AA\"a\u0002x!\u0003\u0005\u0019\u0011)A\u0005\u0003\u0013\t1\u0001\u001f\u00132!\u001dQ\u00111BA\b\u00037I1!!\u0004\u0005\u0005\u0019!V\u000f\u001d7feA)\u0011\u0011CA\f#5\u0011\u00111\u0003\u0006\u0004\u0003+\u0011\u0011aB7vi\u0006\u0014G.Z\u0005\u0005\u00033\t\u0019B\u0001\u0004Ck\u001a4WM\u001d\t\u0005\u0015\u0005uA(C\u0002\u0002 \u0011\u0011Q!\u0011:sCfD\u0001\"a\txA\u0003%\u0011qB\u0001\u0005K2l7\u000f\u0003\u0005\u0002(]\u0004\u000b\u0011BA\u000e\u0003\u0011IG\r_:\t\u0011\u0005-r\u000f1A\u0005\nE\u000b\u0001b\u00185bg:+\u0007\u0010\u001e\u0005\n\u0003_9\b\u0019!C\u0005\u0003c\tAb\u00185bg:+\u0007\u0010^0%KF$2aKA\u001a\u0011%\t9!!\f\u0002\u0002\u0003\u0007!\u000bC\u0004\u00028]\u0004\u000b\u0015\u0002*\u0002\u0013}C\u0017m\u001d(fqR\u0004\u0003BBA\u001eo\u0012\u0005\u0011+A\u0004iCNtU\r\u001f;\t\u000f\u0005}r\u000f\"\u0001\u0002B\u0005!a.\u001a=u)\u0005I\u0002bBA#o\u0012%\u0011qI\u0001\u0005g^\f\u0007\u000fF\u0003,\u0003\u0013\ni\u0005C\u0004\u0002L\u0005\r\u0003\u0019\u0001\u001f\u0002\u0003%Dq!a\u0014\u0002D\u0001\u0007A(A\u0001k\u0011!\t\u0019f\u001eQ\u0005\n\u0005U\u0013\u0001B5oSR$\"!!\u0003\u0007\r\u0005e\u0003\u0001BA.\u0005=\u0019u.\u001c2j]\u0006$\u0018n\u001c8t\u0013R\u00148cAA,u\"Ia/a\u0016\u0003\u0002\u0003\u0006I\u0001\u0010\u0005\b}\u0006]C\u0011AA1)\u0011\t\u0019'!\u001a\u0011\t\u0005\r\u0011q\u000b\u0005\u0007m\u0006}\u0003\u0019\u0001\u001f\t\u001b\u0005%\u0014q\u000bI\u0001\u0002\u0007\u0005\u000b\u0011BA6\u0003\rAH\u0005\u000e\t\n\u0015\u00055\u0014\u0011OA\u000e\u00037I1!a\u001c\u0005\u0005\u0019!V\u000f\u001d7fgA!a\"a\u001d\u0012\u0013\r\t)H\u0001\u0002\u000b\u0013:$W\r_3e'\u0016\f\bBCA\u0012\u0003/\u0012\r\u0011\"\u0003\u0002zU\u0011\u0011\u0011\u000f\u0005\n\u0003{\n9\u0006)A\u0005\u0003c\nQ!\u001a7ng\u0002B!\"!!\u0002X\t\u0007I\u0011BAB\u0003\u0011\u0019g\u000e^:\u0016\u0005\u0005m\u0001\"CAD\u0003/\u0002\u000b\u0011BA\u000e\u0003\u0015\u0019g\u000e^:!\u0011)\tY)a\u0016C\u0002\u0013%\u00111Q\u0001\u0005]Vl7\u000fC\u0005\u0002\u0010\u0006]\u0003\u0015!\u0003\u0002\u001c\u0005)a.^7tA!Q\u00111SA,\u0005\u0004%I!a!\u0002\t=4gm\u001d\u0005\n\u0003/\u000b9\u0006)A\u0005\u00037\tQa\u001c4gg\u0002B\u0011\"a\u000b\u0002X\u0001\u0007I\u0011B)\t\u0015\u0005=\u0012q\u000ba\u0001\n\u0013\ti\nF\u0002,\u0003?C\u0011\"a\u0002\u0002\u001c\u0006\u0005\t\u0019\u0001*\t\u0011\u0005]\u0012q\u000bQ!\nICq!a\u000f\u0002X\u0011\u0005\u0011\u000b\u0003\u0005\u0002@\u0005]C\u0011AA!\u0011!\t\u0019&a\u0016\u0005\n\u0005%FCAA6\u0011\u001d\ti\u000b\u0001C\u0001\u0003_\u000bqA]3wKJ\u001cX-F\u0001\u001a\u0011\u001d\t\u0019\f\u0001C\u0001\u0003k\u000b!B]3wKJ\u001cX-T1q+\u0019\t9,a5\u0002>R!\u0011\u0011XAl)\u0011\tY,!1\u0011\u0007I\ti\fB\u0004\u0002@\u0006E&\u0019A\u000b\u0003\tQC\u0017\r\u001e\u0005\t\u0003\u0007\f\t\fq\u0001\u0002F\u0006\u0011!M\u001a\t\n\u0003\u000f\fi-GAi\u0003wk!!!3\u000b\u0007\u0005-'!A\u0004hK:,'/[2\n\t\u0005=\u0017\u0011\u001a\u0002\r\u0007\u0006t')^5mI\u001a\u0013x.\u001c\t\u0004%\u0005MGaBAk\u0003c\u0013\r!\u0006\u0002\u0002\u0005\"A\u0011\u0011\\AY\u0001\u0004\tY.A\u0001g!\u0015QQ,EAi\u0011\u001d\ty\u000e\u0001C\u0001\u0003C\fqB]3wKJ\u001cX-\u0013;fe\u0006$xN]\u000b\u0003\u0003G\u00042A\u00049\u0012\u0011\u001d\t9\u000f\u0001C\u0001\u0003S\f!b\u001d;beR\u001cx+\u001b;i+\u0011\tY/!?\u0015\u000bI\u000bi/a?\t\u0011\u0005=\u0018Q\u001da\u0001\u0003c\fA\u0001\u001e5biB)a\"a=\u0002x&\u0019\u0011Q\u001f\u0002\u0003\r\u001d+gnU3r!\r\u0011\u0012\u0011 \u0003\b\u0003+\f)O1\u0001\u0016\u0011\u001d\ti0!:A\u0002q\naa\u001c4gg\u0016$\bb\u0002B\u0001\u0001\u0011\u0005!1A\u0001\tK:$7oV5uQV!!Q\u0001B\u0007)\r\u0011&q\u0001\u0005\t\u0003_\fy\u00101\u0001\u0003\nA)a\"a=\u0003\fA\u0019!C!\u0004\u0005\u000f\u0005U\u0017q b\u0001+!9!\u0011\u0003\u0001\u0005\u0002\tM\u0011\u0001D5oI\u0016DxJZ*mS\u000e,W\u0003\u0002B\u000b\u0005;!2\u0001\u0010B\f\u0011!\tyOa\u0004A\u0002\te\u0001#\u0002\b\u0002t\nm\u0001c\u0001\n\u0003\u001e\u0011A\u0011Q\u001bB\b\u0005\u0004\u0011y\"\u0005\u0002\u0012\u0013!9!\u0011\u0003\u0001\u0005\u0002\t\rR\u0003\u0002B\u0013\u0005[!R\u0001\u0010B\u0014\u0005_A\u0001\"a<\u0003\"\u0001\u0007!\u0011\u0006\t\u0006\u001d\u0005M(1\u0006\t\u0004%\t5B\u0001CAk\u0005C\u0011\rAa\b\t\r\u0001\u0014\t\u00031\u0001=\u0011\u001d\u0011\u0019\u0004\u0001C\u0001\u0005k\t\u0001\u0003\\1ti&sG-\u001a=PMNc\u0017nY3\u0016\t\t]\"q\b\u000b\u0004y\te\u0002\u0002CAx\u0005c\u0001\rAa\u000f\u0011\u000b9\t\u0019P!\u0010\u0011\u0007I\u0011y\u0004\u0002\u0005\u0002V\nE\"\u0019\u0001B\u0010\u0011\u001d\u0011\u0019\u0004\u0001C\u0001\u0005\u0007*BA!\u0012\u0003NQ)AHa\u0012\u0003P!A\u0011q\u001eB!\u0001\u0004\u0011I\u0005E\u0003\u000f\u0003g\u0014Y\u0005E\u0002\u0013\u0005\u001b\"\u0001\"!6\u0003B\t\u0007!q\u0004\u0005\u0007W\n\u0005\u0003\u0019\u0001\u001f\t\u000f\tM\u0003\u0001\"\u0001\u0003V\u0005i1m\u001c8uC&t7o\u00157jG\u0016,BAa\u0016\u0003`Q\u0019!K!\u0017\t\u0011\u0005=(\u0011\u000ba\u0001\u00057\u0002RADAz\u0005;\u00022A\u0005B0\t\u001d\t)N!\u0015C\u0002UAqAa\u0019\u0001\t\u0003\u0011)'\u0001\u0005d_:$\u0018-\u001b8t+\u0011\u00119Ga\u001c\u0015\u0007I\u0013I\u0007\u0003\u0005\u0003l\t\u0005\u0004\u0019\u0001B7\u0003\u0011)G.Z7\u0011\u0007I\u0011y\u0007\u0002\u0005\u0003r\t\u0005$\u0019\u0001B\u0010\u0005\t\t\u0015\u0007C\u0004\u0003v\u0001!\tEa\u001e\u0002\u000bUt\u0017n\u001c8\u0016\r\te$q\u0011B@)\u0011\u0011YH!#\u0015\t\tu$\u0011\u0011\t\u0004%\t}DaBA`\u0005g\u0012\r!\u0006\u0005\t\u0003\u0007\u0014\u0019\bq\u0001\u0003\u0004BI\u0011qYAg3\t\u0015%Q\u0010\t\u0004%\t\u001dE\u0001CAk\u0005g\u0012\rAa\b\t\u0011\u0005=(1\u000fa\u0001\u0005\u0017\u0003RADAz\u0005\u000bCqAa$\u0001\t\u0003\u0011\t*\u0001\u0003eS\u001a4W\u0003\u0002BJ\u00057#2!\u0007BK\u0011!\tyO!$A\u0002\t]\u0005#\u0002\b\u0002t\ne\u0005c\u0001\n\u0003\u001c\u0012A\u0011Q\u001bBG\u0005\u0004\u0011y\u0002C\u0004\u0003 \u0002!\tA!)\u0002\u0013%tG/\u001a:tK\u000e$X\u0003\u0002BR\u0005W#2!\u0007BS\u0011!\tyO!(A\u0002\t\u001d\u0006#\u0002\b\u0002t\n%\u0006c\u0001\n\u0003,\u0012A\u0011Q\u001bBO\u0005\u0004\u0011y\u0002C\u0004\u00030\u0002!IA!-\u0002\u0013=\u001c7mQ8v]R\u001cX\u0003\u0002BZ\u0005{#BA!.\u0003@B9\u0011\u0011\u0003B\\\u0005wc\u0014\u0002\u0002B]\u0003'\u00111!T1q!\r\u0011\"Q\u0018\u0003\b\u0003+\u0014iK1\u0001\u0016\u0011!\u0011\tM!,A\u0002\t\r\u0017AA:r!\u0011q!Ga/\t\u000f\t\u001d\u0007\u0001\"\u0001\u00020\u0006AA-[:uS:\u001cG\u000fC\u0004\u0003L\u0002!\tA!4\u0002\u000bA\fGo\u00195\u0016\r\t='Q\u001cBk)!\u0011\tNa8\u0003b\n\u0015H\u0003\u0002Bj\u0005/\u00042A\u0005Bk\t\u001d\tyL!3C\u0002UA\u0001\"a1\u0003J\u0002\u000f!\u0011\u001c\t\n\u0003\u000f\fi-\u0007Bn\u0005'\u00042A\u0005Bo\t!\t)N!3C\u0002\t}\u0001B\u00021\u0003J\u0002\u0007A\b\u0003\u0005\u0003L\n%\u0007\u0019\u0001Br!\u0015q\u00111\u001fBn\u0011\u001d\u00119O!3A\u0002q\n\u0001B]3qY\u0006\u001cW\r\u001a\u0005\b\u0005W\u0004A\u0011\u0001Bw\u0003\u001d)\b\u000fZ1uK\u0012,bAa<\u0003~\nUHC\u0002By\u0005\u007f\u001c\u0019\u0001\u0006\u0003\u0003t\n]\bc\u0001\n\u0003v\u00129\u0011q\u0018Bu\u0005\u0004)\u0002\u0002CAb\u0005S\u0004\u001dA!?\u0011\u0013\u0005\u001d\u0017QZ\r\u0003|\nM\bc\u0001\n\u0003~\u0012A\u0011Q\u001bBu\u0005\u0004\u0011y\u0002C\u0004\u0004\u0002\t%\b\u0019\u0001\u001f\u0002\u000b%tG-\u001a=\t\u0011\t-$\u0011\u001ea\u0001\u0005wDqaa\u0002\u0001\t\u0003\u0019I!A\u0006%a2,8\u000fJ2pY>tWCBB\u0006\u00073\u0019\t\u0002\u0006\u0003\u0004\u000e\rmA\u0003BB\b\u0007'\u00012AEB\t\t\u001d\tyl!\u0002C\u0002UA\u0001\"a1\u0004\u0006\u0001\u000f1Q\u0003\t\n\u0003\u000f\fi-GB\f\u0007\u001f\u00012AEB\r\t!\t)n!\u0002C\u0002\t}\u0001\u0002\u0003B6\u0007\u000b\u0001\raa\u0006\t\u000f\r}\u0001\u0001\"\u0001\u0004\"\u0005YAeY8m_:$\u0003\u000f\\;t+\u0019\u0019\u0019c!\r\u0004*Q!1QEB\u001a)\u0011\u00199ca\u000b\u0011\u0007I\u0019I\u0003B\u0004\u0002@\u000eu!\u0019A\u000b\t\u0011\u0005\r7Q\u0004a\u0002\u0007[\u0001\u0012\"a2\u0002Nf\u0019yca\n\u0011\u0007I\u0019\t\u0004\u0002\u0005\u0002V\u000eu!\u0019\u0001B\u0010\u0011!\u0011Yg!\bA\u0002\r=\u0002bBB\u001c\u0001\u0011\u00051\u0011H\u0001\u0006a\u0006$Gk\\\u000b\u0007\u0007w\u0019Ie!\u0011\u0015\r\ru21JB')\u0011\u0019yda\u0011\u0011\u0007I\u0019\t\u0005B\u0004\u0002@\u000eU\"\u0019A\u000b\t\u0011\u0005\r7Q\u0007a\u0002\u0007\u000b\u0002\u0012\"a2\u0002Nf\u00199ea\u0010\u0011\u0007I\u0019I\u0005\u0002\u0005\u0002V\u000eU\"\u0019\u0001B\u0010\u0011\u0019q5Q\u0007a\u0001y!A!1NB\u001b\u0001\u0004\u00199\u0005C\u0004\u0004R\u0001!\taa\u0015\u0002\u0017\r|'O]3ta>tGm]\u000b\u0005\u0007+\u001a\u0019\u0007\u0006\u0003\u0004X\r\u0015Dc\u0001*\u0004Z!91la\u0014A\u0002\rm\u0003c\u0002\u0006\u0004^E\u0019\tGU\u0005\u0004\u0007?\"!!\u0003$v]\u000e$\u0018n\u001c83!\r\u001121\r\u0003\b\u0003+\u001cyE1\u0001\u0016\u0011!\tyoa\u0014A\u0002\r\u001d\u0004#\u0002\b\u0002t\u000e\u0005\u0004bBB6\u0001\u0011\u00051QN\u0001\tg>\u0014HoV5uQR\u0019\u0011da\u001c\t\u0011\rE4\u0011\u000ea\u0001\u0007g\n!\u0001\u001c;\u0011\r)\u0019i&E\tS\u0011\u001d\u00199\b\u0001C\u0001\u0007s\naa]8si\nKX\u0003BB>\u0007##Ba! \u0004\u0014R\u0019\u0011da \t\u0011\r\u00055Q\u000fa\u0002\u0007\u0007\u000b1a\u001c:e!\u0019\u0019)ia#\u0004\u00106\u00111q\u0011\u0006\u0004\u0007\u0013#\u0011\u0001B7bi\"LAa!$\u0004\b\nAqJ\u001d3fe&tw\rE\u0002\u0013\u0007##q!!6\u0004v\t\u0007Q\u0003\u0003\u0005\u0002Z\u000eU\u0004\u0019ABK!\u0015QQ,EBH\u0011\u001d\u0019I\n\u0001C\u0001\u00077\u000baa]8si\u0016$W\u0003BBO\u0007K#2!GBP\u0011!\u0019\tia&A\u0004\r\u0005\u0006CBBC\u0007\u0017\u001b\u0019\u000bE\u0002\u0013\u0007K#\u0001\"!6\u0004\u0018\n\u0007!q\u0004\u0005\u0007\u0007S\u0003A\u0011\t\u0019\u0002\u000bQ|7+Z9\t\u000f\r5\u0006\u0001\"\u0001\u00040\u00069\u0011N\u001c3jG\u0016\u001cXCABY!\u0011\u0019\u0019l!/\u000e\u0005\rU&bAB\\\u0005\u0005I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u0007w\u001b)LA\u0003SC:<W\rC\u0004\u0004@\u0002!\te!1\u0002\tYLWm^\u000b\u0003\u0007\u0007\u0014ba!2\u0004J\u000e=gaBBd\u0007{\u000311\u0019\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0004\u0015\r-\u0017bABg\t\t1\u0011I\\=SK\u001a\u0004RADBi#eI1aa5\u0003\u0005\u001d\u0019V-\u001d,jK^Dqaa0\u0001\t\u0003\u001a9\u000e\u0006\u0004\u0004P\u000ee71\u001c\u0005\u0007A\u000eU\u0007\u0019\u0001\u001f\t\u000f\ru7Q\u001ba\u0001y\u0005)QO\u001c;jY\"91\u0011\u001d\u0001\u0005B\r\r\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\r\u0015\b\u0003BBt\u0007[t1ACBu\u0013\r\u0019Y\u000fB\u0001\u0007!J,G-\u001a4\n\t\r=8\u0011\u001f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\r-H\u0001\u0005\u0003\u000f\u0001EIraBB|\u0005!\u00051\u0011`\u0001\b'\u0016\fH*[6f!\rq11 \u0004\u0007\u0003\tA\ta!@\u0014\t\rm8\u0011\u001a\u0005\b}\u000emH\u0011\u0001C\u0001)\t\u0019I\u0010\u0003\u0005\u0005\u0006\rmH\u0011\u0002C\u0004\u0003=YW\u000e](qi&l\u0017N_3X_J$W\u0003\u0002C\u0005\t\u001f!\"\u0002b\u0003\u0005\u0012\u0011]A1\u0004C\u0010!\u0015q\u00111\u000fC\u0007!\r\u0011Bq\u0002\u0003\b\u0003+$\u0019A1\u0001\u0016\u0011!!\u0019\u0002b\u0001A\u0002\u0011U\u0011!A,\u0011\t9\u0011DQ\u0002\u0005\b\t3!\u0019\u00011\u0001=\u0003\tq\u0007\u0007C\u0004\u0005\u001e\u0011\r\u0001\u0019\u0001\u001f\u0002\u00059\f\u0004b\u0002C\u0011\t\u0007\u0001\rAU\u0001\bM>\u0014x/\u0019:e\u0011!!)ca?\u0005\n\u0011\u001d\u0012\u0001D6na*+X\u000e\u001d+bE2,W\u0003\u0002C\u0015\tg!b!a\u0007\u0005,\u0011U\u0002\u0002\u0003C\u0017\tG\u0001\r\u0001b\f\u0002\t]{\u0007\u000f\u001e\t\u0006\u001d\u0005MD\u0011\u0007\t\u0004%\u0011MBaBAk\tG\u0011\r!\u0006\u0005\b\to!\u0019\u00031\u0001=\u0003\u00119H.\u001a8\t\u0011\u0011m21 C\u0005\t{\t\u0011b[7q'\u0016\f'o\u00195\u0016\t\u0011}B\u0011\n\u000b\u0010y\u0011\u0005C1\nC(\t'\")\u0006b\u0016\u0005Z!AA1\tC\u001d\u0001\u0004!)%A\u0001T!\u0011q!\u0007b\u0012\u0011\u0007I!I\u0005B\u0004\u0002V\u0012e\"\u0019A\u000b\t\u000f\u00115C\u0011\ba\u0001y\u0005\u0011Q\u000e\r\u0005\b\t#\"I\u00041\u0001=\u0003\ti\u0017\u0007\u0003\u0005\u0005\u0014\u0011e\u0002\u0019\u0001C#\u0011\u001d!I\u0002\"\u000fA\u0002qBq\u0001\"\b\u0005:\u0001\u0007A\bC\u0004\u0005\"\u0011e\u0002\u0019\u0001*\t\u0011\u0011u31 C\u0001\t?\nq!\u001b8eKb|e-\u0006\u0003\u0005b\u0011-Dc\u0004\u001f\u0005d\u00115D\u0011\u000fC;\ts\"i\b\"!\t\u0011\u0011\u0015D1\fa\u0001\tO\naa]8ve\u000e,\u0007\u0003\u0002\b3\tS\u00022A\u0005C6\t\u001d\t)\u000eb\u0017C\u0002UAq\u0001b\u001c\u0005\\\u0001\u0007A(\u0001\u0007t_V\u00148-Z(gMN,G\u000fC\u0004\u0005t\u0011m\u0003\u0019\u0001\u001f\u0002\u0017M|WO]2f\u0007>,h\u000e\u001e\u0005\t\to\"Y\u00061\u0001\u0005h\u00051A/\u0019:hKRDq\u0001b\u001f\u0005\\\u0001\u0007A(\u0001\u0007uCJ<W\r^(gMN,G\u000fC\u0004\u0005\u0000\u0011m\u0003\u0019\u0001\u001f\u0002\u0017Q\f'oZ3u\u0007>,h\u000e\u001e\u0005\b\t\u0007#Y\u00061\u0001=\u0003%1'o\\7J]\u0012,\u0007\u0010\u0003\u0005\u0005\b\u000emH\u0011\u0001CE\u0003-a\u0017m\u001d;J]\u0012,\u0007p\u00144\u0016\t\u0011-E1\u0013\u000b\u0010y\u00115EQ\u0013CL\t3#Y\n\"(\u0005 \"AAQ\rCC\u0001\u0004!y\t\u0005\u0003\u000fe\u0011E\u0005c\u0001\n\u0005\u0014\u00129\u0011Q\u001bCC\u0005\u0004)\u0002b\u0002C8\t\u000b\u0003\r\u0001\u0010\u0005\b\tg\")\t1\u0001=\u0011!!9\b\"\"A\u0002\u0011=\u0005b\u0002C>\t\u000b\u0003\r\u0001\u0010\u0005\b\t\u007f\")\t1\u0001=\u0011\u001d!\u0019\t\"\"A\u0002q\u0002")
public interface SeqLike<A, Repr>
extends IterableLike<A, Repr>,
GenSeqLike<A, Repr> {
    @Override
    public Seq<A> thisCollection();

    @Override
    public Seq<A> toCollection(Repr var1);

    @Override
    public int length();

    @Override
    public A apply(int var1);

    @Override
    public Combiner<A, ParSeq<A>> parCombiner();

    public int lengthCompare(int var1);

    @Override
    public boolean isEmpty();

    @Override
    public int size();

    @Override
    public int segmentLength(Function1<A, Object> var1, int var2);

    @Override
    public int indexWhere(Function1<A, Object> var1, int var2);

    @Override
    public int lastIndexWhere(Function1<A, Object> var1, int var2);

    public Iterator<Repr> permutations();

    public Iterator<Repr> combinations(int var1);

    @Override
    public Repr reverse();

    @Override
    public <B, That> That reverseMap(Function1<A, B> var1, CanBuildFrom<Repr, B, That> var2);

    public Iterator<A> reverseIterator();

    @Override
    public <B> boolean startsWith(GenSeq<B> var1, int var2);

    @Override
    public <B> boolean endsWith(GenSeq<B> var1);

    public <B> int indexOfSlice(GenSeq<B> var1);

    public <B> int indexOfSlice(GenSeq<B> var1, int var2);

    public <B> int lastIndexOfSlice(GenSeq<B> var1);

    public <B> int lastIndexOfSlice(GenSeq<B> var1, int var2);

    public <B> boolean containsSlice(GenSeq<B> var1);

    public <A1> boolean contains(A1 var1);

    @Override
    public <B, That> That union(GenSeq<B> var1, CanBuildFrom<Repr, B, That> var2);

    @Override
    public <B> Repr diff(GenSeq<B> var1);

    @Override
    public <B> Repr intersect(GenSeq<B> var1);

    @Override
    public Repr distinct();

    @Override
    public <B, That> That patch(int var1, GenSeq<B> var2, int var3, CanBuildFrom<Repr, B, That> var4);

    @Override
    public <B, That> That updated(int var1, B var2, CanBuildFrom<Repr, B, That> var3);

    @Override
    public <B, That> That $plus$colon(B var1, CanBuildFrom<Repr, B, That> var2);

    @Override
    public <B, That> That $colon$plus(B var1, CanBuildFrom<Repr, B, That> var2);

    @Override
    public <B, That> That padTo(int var1, B var2, CanBuildFrom<Repr, B, That> var3);

    @Override
    public <B> boolean corresponds(GenSeq<B> var1, Function2<A, B, Object> var2);

    public Repr sortWith(Function2<A, A, Object> var1);

    public <B> Repr sortBy(Function1<A, B> var1, Ordering<B> var2);

    public <B> Repr sorted(Ordering<B> var1);

    @Override
    public Seq<A> toSeq();

    public Range indices();

    @Override
    public Object view();

    @Override
    public SeqView<A, Repr> view(int var1, int var2);

    @Override
    public String toString();

    public class PermutationsItr
    extends AbstractIterator<Repr> {
        private final /* synthetic */ Tuple2 x$1;
        private final Buffer<A> elms;
        private final int[] idxs;
        private boolean _hasNext;

        private boolean _hasNext() {
            return this._hasNext;
        }

        private void _hasNext_$eq(boolean x$1) {
            this._hasNext = x$1;
        }

        @Override
        public boolean hasNext() {
            return this._hasNext();
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public Repr next() {
            void var2_2;
            int i;
            java.io.Serializable serializable = this.hasNext() ? BoxedUnit.UNIT : Iterator$.MODULE$.empty().next();
            Growable forcedElms = new ArrayBuffer(this.elms.size()).$plus$plus$eq((TraversableOnce)this.elms);
            Object result2 = ((Builder)this.scala$collection$SeqLike$PermutationsItr$$$outer().newBuilder().$plus$plus$eq(forcedElms)).result();
            for (i = this.idxs.length - 2; i >= 0 && this.idxs[i] >= this.idxs[i + 1]; --i) {
            }
            if (i < 0) {
                this._hasNext_$eq(false);
            } else {
                int j = this.idxs.length - 1;
                while (this.idxs[j] <= this.idxs[i]) {
                    --j;
                }
                this.swap(i, j);
                int len = (this.idxs.length - i) / 2;
                for (int k = 1; k <= len; ++k) {
                    this.swap(i + k, this.idxs.length - k);
                }
            }
            return var2_2;
        }

        private void swap(int i, int j) {
            int tmpI = this.idxs[i];
            this.idxs[i] = this.idxs[j];
            this.idxs[j] = tmpI;
            Object tmpE = this.elms.apply(i);
            this.elms.update(i, this.elms.apply(j));
            this.elms.update(j, tmpE);
        }

        private Tuple2<Buffer<A>, int[]> init() {
            HashMap m = (HashMap)HashMap$.MODULE$.apply(Nil$.MODULE$);
            Tuple2 tuple2 = ((GenericTraversableTemplate)((SeqLike)this.scala$collection$SeqLike$PermutationsItr$$$outer().thisCollection().map(new Serializable(this, m){
                public static final long serialVersionUID = 0L;
                public final HashMap m$1;

                public final Tuple2<A, Object> apply(A e) {
                    return new Tuple2<A, Object>(e, this.m$1.getOrElseUpdate(e, new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        public final /* synthetic */ PermutationsItr$$anonfun$2 $outer;

                        public final int apply() {
                            return this.$outer.m$1.size();
                        }

                        public int apply$mcI$sp() {
                            return this.$outer.m$1.size();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                }
                {
                    this.m$1 = m$1;
                }
            }, Seq$.MODULE$.canBuildFrom())).sortBy(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final int apply(Tuple2<A, Object> x$2) {
                    return x$2._2$mcI$sp();
                }
            }, Ordering$Int$.MODULE$)).unzip(Predef$.MODULE$.$conforms());
            if (tuple2 != null) {
                Tuple2 tuple22 = new Tuple2(tuple2._1(), tuple2._2());
                Seq es = (Seq)tuple22._1();
                Seq is = (Seq)tuple22._2();
                return new Tuple2(es.toBuffer(), is.toArray(ClassTag$.MODULE$.Int()));
            }
            throw new MatchError(tuple2);
        }

        public /* synthetic */ SeqLike scala$collection$SeqLike$PermutationsItr$$$outer() {
            return SeqLike.this;
        }

        public PermutationsItr() {
            if (SeqLike.this == null) {
                throw null;
            }
            Tuple2 tuple2 = this.init();
            if (tuple2 != null) {
                this.x$1 = new Tuple2(tuple2._1(), tuple2._2());
                this.elms = (Buffer)this.x$1._1();
                this.idxs = (int[])this.x$1._2();
                this._hasNext = true;
                return;
            }
            throw new MatchError(tuple2);
        }
    }

    public class CombinationsItr
    extends AbstractIterator<Repr> {
        private final int n;
        private final /* synthetic */ Tuple3 x$4;
        private final IndexedSeq<A> scala$collection$SeqLike$CombinationsItr$$elms;
        private final int[] scala$collection$SeqLike$CombinationsItr$$cnts;
        private final int[] scala$collection$SeqLike$CombinationsItr$$nums;
        private final int[] scala$collection$SeqLike$CombinationsItr$$offs;
        private boolean _hasNext;
        public final /* synthetic */ SeqLike $outer;

        public IndexedSeq<A> scala$collection$SeqLike$CombinationsItr$$elms() {
            return this.scala$collection$SeqLike$CombinationsItr$$elms;
        }

        public int[] scala$collection$SeqLike$CombinationsItr$$cnts() {
            return this.scala$collection$SeqLike$CombinationsItr$$cnts;
        }

        public int[] scala$collection$SeqLike$CombinationsItr$$nums() {
            return this.scala$collection$SeqLike$CombinationsItr$$nums;
        }

        public int[] scala$collection$SeqLike$CombinationsItr$$offs() {
            return this.scala$collection$SeqLike$CombinationsItr$$offs;
        }

        private boolean _hasNext() {
            return this._hasNext;
        }

        private void _hasNext_$eq(boolean x$1) {
            this._hasNext = x$1;
        }

        @Override
        public boolean hasNext() {
            return this._hasNext();
        }

        /*
         * Unable to fully structure code
         * Could not resolve type clashes
         */
        @Override
        public Repr next() {
            v0 /* !! */  = this.hasNext() != false ? BoxedUnit.UNIT : Iterator$.MODULE$.empty().next();
            buf = this.scala$collection$SeqLike$CombinationsItr$$$outer().newBuilder();
            var1_2 = Predef$.MODULE$;
            var3_3 = this.scala$collection$SeqLike$CombinationsItr$$nums().length;
            var2_4 = Range$.MODULE$;
            var8_5 = new Serializable(this, buf){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ CombinationsItr $outer;
                public final Builder buf$1;

                public final void apply(int k) {
                    this.apply$mcVI$sp(k);
                }

                /*
                 * Enabled force condition propagation
                 * Lifted jumps to return sites
                 */
                public void apply$mcVI$sp(int k) {
                    Predef$ predef$ = Predef$.MODULE$;
                    Serializable serializable = new Serializable(this, k){
                        public static final long serialVersionUID = 0L;
                        public final /* synthetic */ CombinationsItr$$anonfun$next$1 $outer;
                        public final int k$1;

                        public final Builder<A, Repr> apply(int j) {
                            return this.$outer.buf$1.$plus$eq(this.$outer.$outer.scala$collection$SeqLike$CombinationsItr$$elms().apply(this.$outer.$outer.scala$collection$SeqLike$CombinationsItr$$offs()[this.k$1] + j));
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.k$1 = k$1;
                        }
                    };
                    Range range2 = RichInt$.MODULE$.until$extension0(0, this.$outer.scala$collection$SeqLike$CombinationsItr$$nums()[k]);
                    if (range2.isEmpty()) return;
                    int i1 = range2.start();
                    while (true) {
                        int n = i1;
                        serializable.apply(n);
                        if (i1 == range2.lastElement()) {
                            return;
                        }
                        i1 += range2.step();
                    }
                }

                public /* synthetic */ CombinationsItr scala$collection$SeqLike$CombinationsItr$$anonfun$$$outer() {
                    return this.$outer;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.buf$1 = buf$1;
                }
            };
            var30_6 = new Range(0, var3_3, 1);
            if (var30_6.isEmpty()) ** GOTO lbl23
            i1 = var30_6.start();
            block0: while (true) {
                var4_10 = Predef$.MODULE$;
                var6_12 = this.scala$collection$SeqLike$CombinationsItr$$nums()[i1];
                var5_11 = Range$.MODULE$;
                var32_8 = new Range(0, var6_12, 1);
                if (var32_8.isEmpty()) ** GOTO lbl22
                i3 = var32_8.start();
                while (true) {
                    block6: {
                        block7: {
                            block8: {
                                var9_13 = i3;
                                buf.$plus$eq(var8_5.$outer.scala$collection$SeqLike$CombinationsItr$$elms().apply(var8_5.$outer.scala$collection$SeqLike$CombinationsItr$$offs()[i1] + var9_13));
                                if (i3 != var32_8.lastElement()) break block6;
lbl22:
                                // 2 sources

                                if (i1 != var30_6.lastElement()) break block7;
lbl23:
                                // 2 sources

                                res = buf.result();
                                for (idx = this.scala$collection$SeqLike$CombinationsItr$$nums().length - 1; idx >= 0 && this.scala$collection$SeqLike$CombinationsItr$$nums()[idx] == this.scala$collection$SeqLike$CombinationsItr$$cnts()[idx]; --idx) {
                                }
                                var12_16 = this.scala$collection$SeqLike$CombinationsItr$$nums();
                                var11_17 = Predef$.MODULE$;
                                if ((idx = IndexedSeqOptimized$class.lastIndexWhere(new ArrayOps.ofInt(var12_16), (Function1)new Serializable(this){
                                    public static final long serialVersionUID = 0L;

                                    public final boolean apply(int x$7) {
                                        return this.apply$mcZI$sp(x$7);
                                    }

                                    public boolean apply$mcZI$sp(int x$7) {
                                        return x$7 > 0;
                                    }
                                }, idx - 1)) >= 0) break block8;
                                this._hasNext_$eq(false);
                                ** GOTO lbl53
                            }
                            var14_18 = this.scala$collection$SeqLike$CombinationsItr$$nums();
                            var13_19 = Predef$.MODULE$;
                            var16_20 = (int[])IndexedSeqOptimized$class.slice(new ArrayOps.ofInt(var14_18), idx + 1, this.scala$collection$SeqLike$CombinationsItr$$nums().length);
                            var15_21 = Predef$.MODULE$;
                            sum = IntRef.create(BoxesRunTime.unboxToInt(TraversableOnce$class.sum(new ArrayOps.ofInt(var16_20), Numeric$IntIsIntegral$.MODULE$)) + 1);
                            this.scala$collection$SeqLike$CombinationsItr$$nums()[idx] = this.scala$collection$SeqLike$CombinationsItr$$nums()[idx] - 1;
                            var20_23 = idx + 1;
                            var18_24 = Predef$.MODULE$;
                            var21_25 = this.scala$collection$SeqLike$CombinationsItr$$nums().length;
                            var19_26 = Range$.MODULE$;
                            var28_27 = new Range(var20_23, var21_25, 1);
                            if (var28_27.isEmpty()) ** GOTO lbl53
                            i2 = var28_27.start();
                            while (true) {
                                block9: {
                                    var24_31 = sum.elem;
                                    var22_29 = Predef$.MODULE$;
                                    var25_32 = this.scala$collection$SeqLike$CombinationsItr$$cnts()[i2];
                                    var23_30 = package$.MODULE$;
                                    this.scala$collection$SeqLike$CombinationsItr$$nums()[i2] = Math.min(var24_31, var25_32);
                                    sum.elem -= this.scala$collection$SeqLike$CombinationsItr$$nums()[i2];
                                    if (i2 != var28_27.lastElement()) break block9;
lbl53:
                                    // 3 sources

                                    return res;
                                }
                                i2 += var28_27.step();
                            }
                        }
                        i1 += var30_6.step();
                        continue block0;
                    }
                    i3 += var32_8.step();
                }
                break;
            }
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private Tuple3<IndexedSeq<A>, int[], int[]> init() {
            Nil$ nil$ = Nil$.MODULE$;
            HashMap$ hashMap$ = HashMap$.MODULE$;
            HashMap m = (HashMap)((GenMap)((Builder)((Builder)new HashMap()).$plus$plus$eq(nil$)).result());
            Tuple2 tuple2 = ((GenericTraversableTemplate)((SeqLike)this.scala$collection$SeqLike$CombinationsItr$$$outer().thisCollection().map(new Serializable(this, m){
                public static final long serialVersionUID = 0L;
                public final HashMap m$2;

                public final Tuple2<A, Object> apply(A e) {
                    return new Tuple2<A, Object>(e, this.m$2.getOrElseUpdate(e, new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        public final /* synthetic */ CombinationsItr$$anonfun$4 $outer;

                        public final int apply() {
                            return this.$outer.m$2.size();
                        }

                        public int apply$mcI$sp() {
                            return this.$outer.m$2.size();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                }
                {
                    this.m$2 = m$2;
                }
            }, Seq$.MODULE$.ReusableCBF())).sortBy(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final int apply(Tuple2<A, Object> x$8) {
                    return x$8._2$mcI$sp();
                }
            }, Ordering$Int$.MODULE$)).unzip(Predef$.MODULE$.$conforms());
            if (tuple2 == null) throw new MatchError(tuple2);
            Tuple2 tuple22 = new Tuple2(tuple2._1(), tuple2._2());
            Seq es = (Seq)tuple22._1();
            Seq is = (Seq)tuple22._2();
            int[] cs = new int[m.size()];
            is.foreach(new Serializable(this, cs){
                public static final long serialVersionUID = 0L;
                private final int[] cs$1;

                public final void apply(int i) {
                    this.apply$mcVI$sp(i);
                }

                public void apply$mcVI$sp(int i) {
                    this.cs$1[i] = this.cs$1[i] + 1;
                }
                {
                    this.cs$1 = (int[])cs$1;
                }
            });
            int[] ns = new int[cs.length];
            IntRef r = IntRef.create(this.n);
            Predef$ predef$ = Predef$.MODULE$;
            int n = ns.length;
            Range$ range$ = Range$.MODULE$;
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;
                public final int[] cs$1;
                public final int[] ns$1;
                public final IntRef r$1;

                public final void apply(int k) {
                    this.apply$mcVI$sp(k);
                }

                public void apply$mcVI$sp(int k) {
                    int n = this.r$1.elem;
                    Predef$ predef$ = Predef$.MODULE$;
                    this.ns$1[k] = RichInt$.MODULE$.min$extension(n, this.cs$1[k]);
                    this.r$1.elem -= this.ns$1[k];
                }
                {
                    this.cs$1 = cs$1;
                    this.ns$1 = ns$1;
                    this.r$1 = r$1;
                }
            };
            Range range2 = new Range(0, n, 1);
            if (range2.isEmpty()) return new Tuple3(es.toIndexedSeq(), cs, ns);
            int i1 = range2.start();
            while (true) {
                int n2 = serializable.r$1.elem;
                Predef$ predef$2 = Predef$.MODULE$;
                int n3 = serializable.cs$1[i1];
                package$ package$2 = package$.MODULE$;
                serializable.ns$1[i1] = Math.min(n2, n3);
                serializable.r$1.elem -= serializable.ns$1[i1];
                if (i1 == range2.lastElement()) {
                    return new Tuple3(es.toIndexedSeq(), cs, ns);
                }
                i1 += range2.step();
            }
        }

        public /* synthetic */ SeqLike scala$collection$SeqLike$CombinationsItr$$$outer() {
            return this.$outer;
        }

        public CombinationsItr(SeqLike<A, Repr> $outer, int n) {
            this.n = n;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Tuple3 tuple3 = this.init();
            if (tuple3 != null) {
                this.x$4 = new Tuple3(tuple3._1(), tuple3._2(), tuple3._3());
                this.scala$collection$SeqLike$CombinationsItr$$elms = (IndexedSeq)this.x$4._1();
                this.scala$collection$SeqLike$CombinationsItr$$cnts = (int[])this.x$4._2();
                this.scala$collection$SeqLike$CombinationsItr$$nums = (int[])this.x$4._3();
                this.scala$collection$SeqLike$CombinationsItr$$offs = (int[])Predef$.MODULE$.intArrayOps(this.scala$collection$SeqLike$CombinationsItr$$cnts()).scanLeft(BoxesRunTime.boxToInteger(0), new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final int apply(int x$5, int x$6) {
                        return x$5 + x$6;
                    }

                    public int apply$mcIII$sp(int x$5, int x$6) {
                        return x$5 + x$6;
                    }
                }, Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.Int()));
                this._hasNext = true;
                return;
            }
            throw new MatchError(tuple3);
        }
    }
}

