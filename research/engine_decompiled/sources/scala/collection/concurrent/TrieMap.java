/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.concurrent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Option$;
import scala.PartialFunction;
import scala.PartialFunction$class;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.CustomParallelizable;
import scala.collection.CustomParallelizable$class;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenMapLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterable$class;
import scala.collection.IterableLike$class;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.Map$class;
import scala.collection.MapLike$class;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.Traversable;
import scala.collection.Traversable$class;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.concurrent.Gen;
import scala.collection.concurrent.INode;
import scala.collection.concurrent.INode$;
import scala.collection.concurrent.INodeBase;
import scala.collection.concurrent.MainNode;
import scala.collection.concurrent.RDCSS_Descriptor;
import scala.collection.concurrent.TrieMap$;
import scala.collection.concurrent.TrieMapIterator;
import scala.collection.concurrent.TrieMapIterator$;
import scala.collection.concurrent.TrieMapSerializationEnd$;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.generic.Shrinkable;
import scala.collection.generic.Shrinkable$class;
import scala.collection.generic.Subtractable$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.Cloneable$class;
import scala.collection.mutable.MapLike;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParTrieMap;
import scala.math.Equiv;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.package$;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;
import scala.util.hashing.Hashing;
import scala.util.hashing.Hashing$;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@ScalaSignature(bytes="\u0006\u0001\r\rg\u0001B\u0001\u0003\u0005%\u0011q\u0001\u0016:jK6\u000b\u0007O\u0003\u0002\u0004\t\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005\u00151\u0011AC2pY2,7\r^5p]*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0007))rd\u0005\u0004\u0001\u0017=\t\u0003F\u000e\t\u0003\u00195i\u0011AB\u0005\u0003\u001d\u0019\u0011a!\u00118z%\u00164\u0007\u0003\u0002\t\u0012'yi\u0011AA\u0005\u0003%\t\u00111!T1q!\t!R\u0003\u0004\u0001\u0005\u000bY\u0001!\u0019A\f\u0003\u0003-\u000b\"\u0001G\u000e\u0011\u00051I\u0012B\u0001\u000e\u0007\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0004\u000f\n\u0005u1!aA!osB\u0011Ac\b\u0003\u0006A\u0001\u0011\ra\u0006\u0002\u0002-B)!%J\n\u001fO5\t1E\u0003\u0002%\t\u00059Q.\u001e;bE2,\u0017B\u0001\u0014$\u0005\u001di\u0015\r\u001d'jW\u0016\u0004B\u0001\u0005\u0001\u0014=A!\u0011F\u000b\u00170\u001b\u0005!\u0011BA\u0016\u0005\u0005Q\u0019Uo\u001d;p[B\u000b'/\u00197mK2L'0\u00192mKB!A\"L\n\u001f\u0013\tqcA\u0001\u0004UkBdWM\r\t\u0005aQ\u001ab$D\u00012\u0015\t!#G\u0003\u00024\t\u0005A\u0001/\u0019:bY2,G.\u0003\u00026c\tQ\u0001+\u0019:Ue&,W*\u00199\u0011\u000519\u0014B\u0001\u001d\u0007\u00051\u0019VM]5bY&T\u0018M\u00197f\u0011!Q\u0004A!A!\u0002\u0013Y\u0011!\u0001:\t\u0011q\u0002!\u0011!Q\u0001\nu\nQA\u001d;va\u0012\u0004BA\u0010$(\u00175\tqH\u0003\u0002A\u0003\u00061\u0011\r^8nS\u000eT!a\u0001\"\u000b\u0005\r#\u0015\u0001B;uS2T\u0011!R\u0001\u0005U\u00064\u0018-\u0003\u0002H\u007f\tY\u0012\t^8nS\u000e\u0014VMZ3sK:\u001cWMR5fY\u0012,\u0006\u000fZ1uKJD\u0001\"\u0013\u0001\u0003\u0002\u0003\u0006IAS\u0001\u0006Q\u0006\u001c\bN\u001a\t\u0004\u0017>\u001bR\"\u0001'\u000b\u00055s\u0015a\u00025bg\"Lgn\u001a\u0006\u0003\u0007\u001aI!\u0001\u0015'\u0003\u000f!\u000b7\u000f[5oO\"A!\u000b\u0001B\u0001B\u0003%1+\u0001\u0002fMB\u0019AkV\n\u000f\u00051)\u0016B\u0001,\u0007\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001W-\u0003\u000b\u0015\u000bX/\u001b<\u000b\u0005Y3\u0001\"B.\u0001\t\u0013a\u0016A\u0002\u001fj]&$h\bF\u0003(;z{\u0006\rC\u0003;5\u0002\u00071\u0002C\u0003=5\u0002\u0007Q\bC\u0003J5\u0002\u0007!\nC\u0003S5\u0002\u00071\u000bC\u0004c\u0001\u0001\u0007I\u0011B2\u0002\u0015!\f7\u000f[5oO>\u0014'.F\u0001K\u0011\u001d)\u0007\u00011A\u0005\n\u0019\fa\u0002[1tQ&twm\u001c2k?\u0012*\u0017\u000f\u0006\u0002hUB\u0011A\u0002[\u0005\u0003S\u001a\u0011A!\u00168ji\"91\u000eZA\u0001\u0002\u0004Q\u0015a\u0001=%c!1Q\u000e\u0001Q!\n)\u000b1\u0002[1tQ&twm\u001c2kA!9q\u000e\u0001a\u0001\n\u0013\u0001\u0018aC3rk\u0006d\u0017\u000e^=pE*,\u0012a\u0015\u0005\be\u0002\u0001\r\u0011\"\u0003t\u0003=)\u0017/^1mSRLxN\u00196`I\u0015\fHCA4u\u0011\u001dY\u0017/!AA\u0002MCaA\u001e\u0001!B\u0013\u0019\u0016\u0001D3rk\u0006d\u0017\u000e^=pE*\u0004\u0003b\u0002=\u0001\u0001\u0004%I!_\u0001\fe>|G/\u001e9eCR,'/F\u0001>\u0011\u001dY\b\u00011A\u0005\nq\fqB]8piV\u0004H-\u0019;fe~#S-\u001d\u000b\u0003OvDqa\u001b>\u0002\u0002\u0003\u0007Q\b\u0003\u0004\u0000\u0001\u0001\u0006K!P\u0001\re>|G/\u001e9eCR,'\u000f\t\u0005\u0006\u001b\u0002!\ta\u0019\u0005\u0007\u0003\u000b\u0001A\u0011\u00019\u0002\u0011\u0015\fX/\u00197jifD\u0011\"!\u0003\u0001\u0001\u0004%\t!a\u0003\u0002\tI|w\u000e^\u000b\u0002\u0017!I\u0011q\u0002\u0001A\u0002\u0013\u0005\u0011\u0011C\u0001\te>|Go\u0018\u0013fcR\u0019q-a\u0005\t\u0011-\fi!!AA\u0002-Aq!a\u0006\u0001A\u0003&1\"A\u0003s_>$\b\u0005\u000b\u0003\u0002\u0016\u0005m\u0001c\u0001\u0007\u0002\u001e%\u0019\u0011q\u0004\u0004\u0003\u0011Y|G.\u0019;jY\u0016Daa\u0017\u0001\u0005\u0002\u0005\rB#B\u0014\u0002&\u0005\u001d\u0002BB%\u0002\"\u0001\u0007!\n\u0003\u0004S\u0003C\u0001\ra\u0015\u0005\u00077\u0002!\t!a\u000b\u0015\u0003\u001dBq!a\f\u0001\t\u0013\t\t$A\u0006xe&$Xm\u00142kK\u000e$HcA4\u00024!A\u0011QGA\u0017\u0001\u0004\t9$A\u0002pkR\u0004B!!\u000f\u0002@5\u0011\u00111\b\u0006\u0004\u0003{!\u0015AA5p\u0013\u0011\t\t%a\u000f\u0003%=\u0013'.Z2u\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u0005\b\u0003\u000b\u0002A\u0011BA$\u0003)\u0011X-\u00193PE*,7\r\u001e\u000b\u0004O\u0006%\u0003\u0002CA&\u0003\u0007\u0002\r!!\u0014\u0002\u0005%t\u0007\u0003BA\u001d\u0003\u001fJA!!\u0015\u0002<\t\trJ\u00196fGRLe\u000e];u'R\u0014X-Y7\t\u000f\u0005U\u0003\u0001\"\u0001\u0002X\u0005A1)Q*`%>{E\u000b\u0006\u0004\u0002Z\u0005}\u00131\r\t\u0004\u0019\u0005m\u0013bAA/\r\t9!i\\8mK\u0006t\u0007bBA1\u0003'\u0002\raC\u0001\u0003_ZDq!!\u001a\u0002T\u0001\u00071\"\u0001\u0002om\"9\u0011\u0011\u000e\u0001\u0005\u0002\u0005-\u0014\u0001\u0003:fC\u0012\u0014vn\u001c;\u0015\t\u00055\u00141\u000f\t\u0006!\u0005=4CH\u0005\u0004\u0003c\u0012!!B%O_\u0012,\u0007BCA;\u0003O\u0002\n\u00111\u0001\u0002Z\u0005)\u0011MY8si\"9\u0011\u0011\u0010\u0001\u0005\u0002\u0005m\u0014a\u0004*E\u0007N\u001bvLU#B\t~\u0013vj\u0014+\u0015\t\u00055\u0014Q\u0010\u0005\u000b\u0003k\n9\b%AA\u0002\u0005e\u0003bBAA\u0001\u0011%\u00111Q\u0001\u000f%\u0012\u001b5kU0D_6\u0004H.\u001a;f)\u0011\ti'!\"\t\u0011\u0005U\u0014q\u0010a\u0001\u00033BC!a \u0002\nB!\u00111RAI\u001b\t\tiIC\u0002\u0002\u0010\u001a\t!\"\u00198o_R\fG/[8o\u0013\u0011\t\u0019*!$\u0003\u000fQ\f\u0017\u000e\u001c:fG\"9\u0011q\u0013\u0001\u0005\n\u0005e\u0015A\u0003*E\u0007N\u001bvLU(P)RA\u0011\u0011LAN\u0003;\u000b9\u000b\u0003\u0005\u0002b\u0005U\u0005\u0019AA7\u0011!\ty*!&A\u0002\u0005\u0005\u0016\u0001D3ya\u0016\u001cG/\u001a3nC&t\u0007#\u0002\t\u0002$Nq\u0012bAAS\u0005\tAQ*Y5o\u001d>$W\r\u0003\u0005\u0002f\u0005U\u0005\u0019AA7\u0011\u001d\tY\u000b\u0001C\u0005\u0003[\u000b\u0001\"\u001b8tKJ$\bn\u0019\u000b\bO\u0006=\u00161WA_\u0011\u001d\t\t,!+A\u0002M\t\u0011a\u001b\u0005\t\u0003k\u000bI\u000b1\u0001\u00028\u0006\u0011\u0001n\u0019\t\u0004\u0019\u0005e\u0016bAA^\r\t\u0019\u0011J\u001c;\t\u000f\u0005}\u0016\u0011\u0016a\u0001=\u0005\ta\u000f\u000b\u0003\u0002*\u0006%\u0005bBAc\u0001\u0011%\u0011qY\u0001\u000bS:\u001cXM\u001d;jM\"\u001cGCCAe\u0003\u001f\f\t.a5\u0002VB!A\"a3\u001f\u0013\r\tiM\u0002\u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\u0005E\u00161\u0019a\u0001'!A\u0011QWAb\u0001\u0004\t9\fC\u0004\u0002@\u0006\r\u0007\u0019\u0001\u0010\t\u000f\u0005]\u00171\u0019a\u0001\u0017\u0005!1m\u001c8eQ\u0011\t\u0019-!#\t\u000f\u0005u\u0007\u0001\"\u0003\u0002`\u0006AAn\\8lkBD7\rF\u0003\f\u0003C\f\u0019\u000fC\u0004\u00022\u0006m\u0007\u0019A\n\t\u0011\u0005U\u00161\u001ca\u0001\u0003oCC!a7\u0002\n\"9\u0011\u0011\u001e\u0001\u0005\n\u0005-\u0018\u0001\u0003:f[>4X\r[2\u0015\u0011\u0005%\u0017Q^Ax\u0003cDq!!-\u0002h\u0002\u00071\u0003C\u0004\u0002@\u0006\u001d\b\u0019\u0001\u0010\t\u0011\u0005U\u0016q\u001da\u0001\u0003oCC!a:\u0002\n\"9\u0011q\u001f\u0001\u0005\u0002\u0005e\u0018AB:ue&tw-\u0006\u0002\u0002|B!\u0011Q B\u0002\u001d\ra\u0011q`\u0005\u0004\u0005\u00031\u0011A\u0002)sK\u0012,g-\u0003\u0003\u0003\u0006\t\u001d!AB*ue&twMC\u0002\u0003\u0002\u0019AqAa\u0003\u0001\t\u0003\u0012i!A\u0002tKF,\u0012a\n\u0005\b\u0005#\u0001A\u0011\tB\n\u0003\r\u0001\u0018M]\u000b\u0002_!9!q\u0003\u0001\u0005B\t5\u0011!B3naRL\bb\u0002B\u000e\u0001\u0011\u0005!QD\u0001\u000bSN\u0014V-\u00193P]2LXCAA-\u0011\u001d\u0011\t\u0003\u0001C\u0001\u0005;\t1B\\8o%\u0016\fGm\u00148ms\"9!Q\u0005\u0001\u0005\u0002\u0005-\u0012\u0001C:oCB\u001c\bn\u001c;)\t\t\r\u0012\u0011\u0012\u0005\b\u0005W\u0001A\u0011\u0001B\u0017\u0003A\u0011X-\u00193P]2L8K\\1qg\"|G\u000f\u0006\u0002\u00030A)\u0011F!\r\u0014=%\u0011!\u0003\u0002\u0015\u0005\u0005S\tI\tC\u0004\u00038\u0001!\tE!\u000f\u0002\u000b\rdW-\u0019:\u0015\u0003\u001dDCA!\u000e\u0002\n\"9!q\b\u0001\u0005\u0002\t\u0005\u0013aC2p[B,H/\u001a%bg\"$B!a.\u0003D!9\u0011\u0011\u0017B\u001f\u0001\u0004\u0019\u0002b\u0002B$\u0001\u0011\u0005!\u0011J\u0001\u0007Y>|7.\u001e9\u0015\u0007y\u0011Y\u0005C\u0004\u00022\n\u0015\u0003\u0019A\n\t\u000f\t=\u0003\u0001\"\u0011\u0003R\u0005)\u0011\r\u001d9msR\u0019aDa\u0015\t\u000f\u0005E&Q\na\u0001'!9!q\u000b\u0001\u0005\u0002\te\u0013aA4fiR!\u0011\u0011\u001aB.\u0011\u001d\t\tL!\u0016A\u0002MAqAa\u0018\u0001\t\u0003\u0012\t'A\u0002qkR$b!!3\u0003d\t\u001d\u0004b\u0002B3\u0005;\u0002\raE\u0001\u0004W\u0016L\bb\u0002B5\u0005;\u0002\rAH\u0001\u0006m\u0006dW/\u001a\u0005\b\u0005[\u0002A\u0011\tB8\u0003\u0019)\b\u000fZ1uKR)qM!\u001d\u0003t!9\u0011\u0011\u0017B6\u0001\u0004\u0019\u0002bBA`\u0005W\u0002\rA\b\u0005\b\u0005o\u0002A\u0011\u0001B=\u0003!!\u0003\u000f\\;tI\u0015\fH\u0003\u0002B>\u0005{j\u0011\u0001\u0001\u0005\b\u0005\u007f\u0012)\b1\u0001-\u0003\tYg\u000fC\u0004\u0003\u0004\u0002!\tE!\"\u0002\rI,Wn\u001c<f)\u0011\tIMa\"\t\u000f\u0005E&\u0011\u0011a\u0001'!9!1\u0012\u0001\u0005\u0002\t5\u0015!\u0003\u0013nS:,8\u000fJ3r)\u0011\u0011YHa$\t\u000f\u0005E&\u0011\u0012a\u0001'!9!1\u0013\u0001\u0005\u0002\tU\u0015a\u00039vi&3\u0017IY:f]R$b!!3\u0003\u0018\ne\u0005bBAY\u0005#\u0003\ra\u0005\u0005\b\u0003\u007f\u0013\t\n1\u0001\u001f\u0011\u001d\u0011i\n\u0001C!\u0005?\u000bqbZ3u\u001fJ,En]3Va\u0012\fG/\u001a\u000b\u0006=\t\u0005&1\u0015\u0005\b\u0003c\u0013Y\n1\u0001\u0014\u0011%\u0011)Ka'\u0005\u0002\u0004\u00119+\u0001\u0002paB!AB!+\u001f\u0013\r\u0011YK\u0002\u0002\ty\tLh.Y7f}!9!1\u0011\u0001\u0005\u0002\t=FCBA-\u0005c\u0013\u0019\fC\u0004\u00022\n5\u0006\u0019A\n\t\u000f\u0005}&Q\u0016a\u0001=!9!q\u0017\u0001\u0005\u0002\te\u0016a\u0002:fa2\f7-\u001a\u000b\t\u00033\u0012YL!0\u0003B\"9\u0011\u0011\u0017B[\u0001\u0004\u0019\u0002b\u0002B`\u0005k\u0003\rAH\u0001\t_2$g/\u00197vK\"9!1\u0019B[\u0001\u0004q\u0012\u0001\u00038foZ\fG.^3\t\u000f\t]\u0006\u0001\"\u0001\u0003HR1\u0011\u0011\u001aBe\u0005\u0017Dq!!-\u0003F\u0002\u00071\u0003C\u0004\u0002@\n\u0015\u0007\u0019\u0001\u0010\t\u000f\t=\u0007\u0001\"\u0001\u0003R\u0006A\u0011\u000e^3sCR|'/\u0006\u0002\u0003TB!\u0011F!6-\u0013\r\u00119\u000e\u0002\u0002\t\u0013R,'/\u0019;pe\"9!1\u001c\u0001\u0005\n\tu\u0017AC2bG\",GmU5{KR\u0011\u0011q\u0017\u0005\b\u0005C\u0004A\u0011\tBr\u0003\u0011\u0019\u0018N_3\u0016\u0005\u0005]\u0006b\u0002Bt\u0001\u0011\u0005#\u0011^\u0001\rgR\u0014\u0018N\\4Qe\u00164\u0017\u000e_\u000b\u0003\u0005W\u0004BA!<\u0003t6\u0011!q\u001e\u0006\u0004\u0005c$\u0015\u0001\u00027b]\u001eLAA!\u0002\u0003p\"I!q\u001f\u0001\u0012\u0002\u0013\u0005!\u0011`\u0001\u0013e\u0016\fGMU8pi\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0003|*\"\u0011\u0011\fB\u007fW\t\u0011y\u0010\u0005\u0003\u0004\u0002\r\u001dQBAB\u0002\u0015\u0011\u0019)!!$\u0002\u0013Ut7\r[3dW\u0016$\u0017\u0002BB\u0005\u0007\u0007\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0011%\u0019i\u0001AI\u0001\n\u0003\u0011I0A\rS\t\u000e\u001b6k\u0018*F\u0003\u0012{&kT(UI\u0011,g-Y;mi\u0012\n\u0004f\u0002\u0001\u0004\u0012\t%4q\u0003\t\u0004\u0019\rM\u0011bAB\u000b\r\t\u00012+\u001a:jC24VM]:j_:,\u0016\n\u0012\u0010\tO\u00132\rTD\u0011\u0002  911\u0004\u0002\t\u0002\ru\u0011a\u0002+sS\u0016l\u0015\r\u001d\t\u0004!\r}aAB\u0001\u0003\u0011\u0003\u0019\tcE\u0003\u0004 \r\rb\u0007\u0005\u0004\u0004&\r-2qF\u0007\u0003\u0007OQ1a!\u000b\u0005\u0003\u001d9WM\\3sS\u000eLAa!\f\u0004(\t\tR*\u001e;bE2,W*\u00199GC\u000e$xN]=\u0011\u0005A\u0001\u0001bB.\u0004 \u0011\u000511\u0007\u000b\u0003\u0007;A!ba\u000e\u0004 \t\u0007I\u0011AB\u001d\u00031Ign\u001c3fkB$\u0017\r^3s+\t\u0019Y\u0004\u0005\u0004?\r\u000eu2Q\u000b\u0019\u0007\u0007\u007f\u00199e!\u0015\u0011\u000fA\u0019\te!\u0012\u0004P%\u001911\t\u0002\u0003\u0013%su\u000eZ3CCN,\u0007c\u0001\u000b\u0004H\u0011Y1\u0011JB&\u0003\u0003\u0005\tQ!\u0001\u0018\u0005\ryF\u0005\u000e\u0005\n\u0007\u001b\u001ay\u0002)A\u0005\u0007w\tQ\"\u001b8pI\u0016,\b\u000fZ1uKJ\u0004\u0003c\u0001\u000b\u0004R\u0011Y11KB&\u0003\u0003\u0005\tQ!\u0001\u0018\u0005\ryF%\u000e\u0019\u0007\u0007/\u001aYf!\u0019\u0011\u000fA\t\u0019k!\u0017\u0004`A\u0019Aca\u0017\u0005\u0017\ru31JA\u0001\u0002\u0003\u0015\ta\u0006\u0002\u0004?\u00122\u0004c\u0001\u000b\u0004b\u0011Y11MB&\u0003\u0003\u0005\tQ!\u0001\u0018\u0005\ryFe\u000e\u0005\t\u0007O\u001ay\u0002b\u0001\u0004j\u0005a1-\u00198Ck&dGM\u0012:p[V111NBB\u0007\u000f+\"a!\u001c\u0011\u0015\r\u00152qNB:\u0007\u007f\u001aI)\u0003\u0003\u0004r\r\u001d\"\u0001D\"b]\n+\u0018\u000e\u001c3Ge>l\u0007\u0003BB;\u0007oj!aa\b\n\t\re41\u0010\u0002\u0005\u0007>dG.\u0003\u0003\u0004~\r\u001d\"!D$f]6\u000b\u0007OR1di>\u0014\u0018\u0010\u0005\u0004\r[\r\u00055Q\u0011\t\u0004)\r\rEA\u0002\f\u0004f\t\u0007q\u0003E\u0002\u0015\u0007\u000f#a\u0001IB3\u0005\u00049\u0002C\u0002\t\u0001\u0007\u0003\u001b)\t\u0003\u0005\u0003\u0018\r}A\u0011ABG+\u0019\u0019yi!&\u0004\u001aV\u00111\u0011\u0013\t\u0007!\u0001\u0019\u0019ja&\u0011\u0007Q\u0019)\n\u0002\u0004\u0017\u0007\u0017\u0013\ra\u0006\t\u0004)\reEA\u0002\u0011\u0004\f\n\u0007qCB\u0004\u0004\u001e\u000e}\u0001aa(\u0003\u001d5\u000bgn\u001a7fI\"\u000b7\u000f[5oOV!1\u0011UBT'\u0015\u0019YjCBR!\u0011Yuj!*\u0011\u0007Q\u00199\u000b\u0002\u0004\u0017\u00077\u0013\ra\u0006\u0005\b7\u000emE\u0011ABV)\t\u0019i\u000b\u0005\u0004\u0004v\rm5Q\u0015\u0005\t\u0007c\u001bY\n\"\u0001\u00044\u0006!\u0001.Y:i)\u0011\t9l!.\t\u0011\u0005E6q\u0016a\u0001\u0007KC!b!/\u0004 \u0005\u0005I\u0011BB^\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0005\ru\u0006\u0003\u0002Bw\u0007\u007fKAa!1\u0003p\n1qJ\u00196fGR\u0004")
public final class TrieMap<K, V>
implements scala.collection.concurrent.Map<K, V>,
CustomParallelizable<Tuple2<K, V>, ParTrieMap<K, V>>,
Serializable {
    public static final long serialVersionUID = -6402774413839597105L;
    private Hashing<K> hashingobj;
    private Equiv<K> equalityobj;
    private AtomicReferenceFieldUpdater<TrieMap<K, V>, Object> rootupdater;
    private volatile Object root;

    public static <K, V> CanBuildFrom<TrieMap<?, ?>, Tuple2<K, V>, TrieMap<K, V>> canBuildFrom() {
        return TrieMap$.MODULE$.canBuildFrom();
    }

    public static AtomicReferenceFieldUpdater<INodeBase<?, ?>, MainNode<?, ?>> inodeupdater() {
        return TrieMap$.MODULE$.inodeupdater();
    }

    @Override
    public Combiner<Tuple2<K, V>, ParTrieMap<K, V>> parCombiner() {
        return CustomParallelizable$class.parCombiner(this);
    }

    @Override
    public scala.collection.mutable.Map<K, V> withDefault(Function1<K, V> d) {
        return scala.collection.mutable.Map$class.withDefault(this, d);
    }

    @Override
    public scala.collection.mutable.Map<K, V> withDefaultValue(V d) {
        return scala.collection.mutable.Map$class.withDefaultValue(this, d);
    }

    @Override
    public Builder<Tuple2<K, V>, TrieMap<K, V>> newBuilder() {
        return scala.collection.mutable.MapLike$class.newBuilder(this);
    }

    @Override
    public <B1> scala.collection.mutable.Map<K, B1> updated(K key, B1 value) {
        return scala.collection.mutable.MapLike$class.updated(this, key, value);
    }

    @Override
    public <B1> scala.collection.mutable.Map<K, B1> $plus(Tuple2<K, B1> kv) {
        return scala.collection.mutable.MapLike$class.$plus(this, kv);
    }

    @Override
    public <B1> scala.collection.mutable.Map<K, B1> $plus(Tuple2<K, B1> elem1, Tuple2<K, B1> elem2, Seq<Tuple2<K, B1>> elems) {
        return scala.collection.mutable.MapLike$class.$plus(this, elem1, elem2, elems);
    }

    @Override
    public <B1> scala.collection.mutable.Map<K, B1> $plus$plus(GenTraversableOnce<Tuple2<K, B1>> xs) {
        return scala.collection.mutable.MapLike$class.$plus$plus(this, xs);
    }

    @Override
    public scala.collection.mutable.Map $minus(Object key) {
        return scala.collection.mutable.MapLike$class.$minus(this, key);
    }

    @Override
    public MapLike<K, V, TrieMap<K, V>> transform(Function2<K, V, V> f) {
        return scala.collection.mutable.MapLike$class.transform(this, f);
    }

    @Override
    public MapLike<K, V, TrieMap<K, V>> retain(Function2<K, V, Object> p) {
        return scala.collection.mutable.MapLike$class.retain(this, p);
    }

    @Override
    public scala.collection.mutable.Map clone() {
        return scala.collection.mutable.MapLike$class.clone(this);
    }

    @Override
    public scala.collection.mutable.Map result() {
        return scala.collection.mutable.MapLike$class.result(this);
    }

    @Override
    public scala.collection.mutable.Map $minus(Object elem1, Object elem2, Seq elems) {
        return scala.collection.mutable.MapLike$class.$minus(this, elem1, elem2, elems);
    }

    @Override
    public scala.collection.mutable.Map $minus$minus(GenTraversableOnce xs) {
        return scala.collection.mutable.MapLike$class.$minus$minus(this, xs);
    }

    @Override
    public /* synthetic */ Object scala$collection$mutable$Cloneable$$super$clone() {
        return super.clone();
    }

    @Override
    public Shrinkable<K> $minus$eq(K elem1, K elem2, Seq<K> elems) {
        return Shrinkable$class.$minus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Shrinkable<K> $minus$minus$eq(TraversableOnce<K> xs) {
        return Shrinkable$class.$minus$minus$eq(this, xs);
    }

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
    public <NewTo> Builder<Tuple2<K, V>, NewTo> mapResult(Function1<TrieMap<K, V>, NewTo> f) {
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

    @Override
    public boolean isEmpty() {
        return MapLike$class.isEmpty(this);
    }

    @Override
    public <B1> B1 getOrElse(K key, Function0<B1> function0) {
        return (B1)MapLike$class.getOrElse(this, key, function0);
    }

    @Override
    public boolean contains(K key) {
        return MapLike$class.contains(this, key);
    }

    @Override
    public boolean isDefinedAt(K key) {
        return MapLike$class.isDefinedAt(this, key);
    }

    @Override
    public Set<K> keySet() {
        return MapLike$class.keySet(this);
    }

    @Override
    public Iterator<K> keysIterator() {
        return MapLike$class.keysIterator(this);
    }

    @Override
    public Iterable<K> keys() {
        return MapLike$class.keys(this);
    }

    @Override
    public Iterable<V> values() {
        return MapLike$class.values(this);
    }

    @Override
    public Iterator<V> valuesIterator() {
        return MapLike$class.valuesIterator(this);
    }

    @Override
    public V default(K key) {
        return (V)MapLike$class.default(this, key);
    }

    @Override
    public Map<K, V> filterKeys(Function1<K, Object> p) {
        return MapLike$class.filterKeys(this, p);
    }

    @Override
    public <C> Map<K, C> mapValues(Function1<V, C> f) {
        return MapLike$class.mapValues(this, f);
    }

    @Override
    public Map filterNot(Function1 p) {
        return MapLike$class.filterNot(this, p);
    }

    @Override
    public Seq<Tuple2<K, V>> toSeq() {
        return MapLike$class.toSeq(this);
    }

    @Override
    public <C> Buffer<C> toBuffer() {
        return MapLike$class.toBuffer(this);
    }

    @Override
    public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
        return MapLike$class.addString(this, b, start, sep, end);
    }

    @Override
    public String toString() {
        return MapLike$class.toString(this);
    }

    @Override
    public <A1 extends K, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> that) {
        return PartialFunction$class.orElse(this, that);
    }

    @Override
    public <C> PartialFunction<K, C> andThen(Function1<V, C> k) {
        return PartialFunction$class.andThen(this, k);
    }

    @Override
    public Function1<K, Option<V>> lift() {
        return PartialFunction$class.lift(this);
    }

    @Override
    public <A1 extends K, B1> B1 applyOrElse(A1 x, Function1<A1, B1> function1) {
        return (B1)PartialFunction$class.applyOrElse(this, x, function1);
    }

    @Override
    public <U> Function1<K, Object> runWith(Function1<V, U> action) {
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
    public <A> Function1<A, V> compose(Function1<A, K> g) {
        return Function1$class.compose(this, g);
    }

    @Override
    public int hashCode() {
        return GenMapLike$class.hashCode(this);
    }

    @Override
    public boolean equals(Object that) {
        return GenMapLike$class.equals(this, that);
    }

    @Override
    public GenericCompanion<scala.collection.mutable.Iterable> companion() {
        return scala.collection.mutable.Iterable$class.companion(this);
    }

    @Override
    public Iterable<Tuple2<K, V>> thisCollection() {
        return IterableLike$class.thisCollection(this);
    }

    @Override
    public Iterable toCollection(Object repr) {
        return IterableLike$class.toCollection(this, repr);
    }

    @Override
    public <U> void foreach(Function1<Tuple2<K, V>, U> f) {
        IterableLike$class.foreach(this, f);
    }

    @Override
    public boolean forall(Function1<Tuple2<K, V>, Object> p) {
        return IterableLike$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<Tuple2<K, V>, Object> p) {
        return IterableLike$class.exists(this, p);
    }

    @Override
    public Option<Tuple2<K, V>> find(Function1<Tuple2<K, V>, Object> p) {
        return IterableLike$class.find(this, p);
    }

    @Override
    public <B> B foldRight(B z, Function2<Tuple2<K, V>, B, B> op) {
        return (B)IterableLike$class.foldRight(this, z, op);
    }

    @Override
    public <B> B reduceRight(Function2<Tuple2<K, V>, B, B> op) {
        return (B)IterableLike$class.reduceRight(this, op);
    }

    @Override
    public Iterable<Tuple2<K, V>> toIterable() {
        return IterableLike$class.toIterable(this);
    }

    @Override
    public Iterator<Tuple2<K, V>> toIterator() {
        return IterableLike$class.toIterator(this);
    }

    @Override
    public Object head() {
        return IterableLike$class.head(this);
    }

    @Override
    public Object slice(int from2, int until2) {
        return IterableLike$class.slice(this, from2, until2);
    }

    @Override
    public Object take(int n) {
        return IterableLike$class.take(this, n);
    }

    @Override
    public Object drop(int n) {
        return IterableLike$class.drop(this, n);
    }

    @Override
    public Object takeWhile(Function1 p) {
        return IterableLike$class.takeWhile(this, p);
    }

    @Override
    public Iterator<TrieMap<K, V>> grouped(int size2) {
        return IterableLike$class.grouped(this, size2);
    }

    @Override
    public Iterator<TrieMap<K, V>> sliding(int size2) {
        return IterableLike$class.sliding(this, size2);
    }

    @Override
    public Iterator<TrieMap<K, V>> sliding(int size2, int step) {
        return IterableLike$class.sliding(this, size2, step);
    }

    @Override
    public Object takeRight(int n) {
        return IterableLike$class.takeRight(this, n);
    }

    @Override
    public Object dropRight(int n) {
        return IterableLike$class.dropRight(this, n);
    }

    @Override
    public <B> void copyToArray(Object xs, int start, int len) {
        IterableLike$class.copyToArray(this, xs, start, len);
    }

    @Override
    public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<TrieMap<K, V>, Tuple2<A1, B>, That> bf) {
        return (That)IterableLike$class.zip(this, that, bf);
    }

    @Override
    public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<TrieMap<K, V>, Tuple2<A1, B>, That> bf) {
        return (That)IterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
    }

    @Override
    public <A1, That> That zipWithIndex(CanBuildFrom<TrieMap<K, V>, Tuple2<A1, Object>, That> bf) {
        return (That)IterableLike$class.zipWithIndex(this, bf);
    }

    @Override
    public <B> boolean sameElements(GenIterable<B> that) {
        return IterableLike$class.sameElements(this, that);
    }

    @Override
    public Stream<Tuple2<K, V>> toStream() {
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
    public IterableView<Tuple2<K, V>, TrieMap<K, V>> view(int from2, int until2) {
        return IterableLike$class.view(this, from2, until2);
    }

    @Override
    public <B> Builder<B, scala.collection.mutable.Iterable<B>> genericBuilder() {
        return GenericTraversableTemplate$class.genericBuilder(this);
    }

    @Override
    public <A1, A2> Tuple2<scala.collection.mutable.Iterable<A1>, scala.collection.mutable.Iterable<A2>> unzip(Function1<Tuple2<K, V>, Tuple2<A1, A2>> asPair) {
        return GenericTraversableTemplate$class.unzip(this, asPair);
    }

    @Override
    public <A1, A2, A3> Tuple3<scala.collection.mutable.Iterable<A1>, scala.collection.mutable.Iterable<A2>, scala.collection.mutable.Iterable<A3>> unzip3(Function1<Tuple2<K, V>, Tuple3<A1, A2, A3>> asTriple) {
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
    public <B, That> That $plus$plus(GenTraversableOnce<B> that, CanBuildFrom<TrieMap<K, V>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus(this, that, bf);
    }

    @Override
    public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<TrieMap<K, V>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
    }

    @Override
    public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<TrieMap<K, V>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
    }

    @Override
    public <B, That> That map(Function1<Tuple2<K, V>, B> f, CanBuildFrom<TrieMap<K, V>, B, That> bf) {
        return (That)TraversableLike$class.map(this, f, bf);
    }

    @Override
    public <B, That> That flatMap(Function1<Tuple2<K, V>, GenTraversableOnce<B>> f, CanBuildFrom<TrieMap<K, V>, B, That> bf) {
        return (That)TraversableLike$class.flatMap(this, f, bf);
    }

    @Override
    public Object filter(Function1 p) {
        return TraversableLike$class.filter(this, p);
    }

    @Override
    public <B, That> That collect(PartialFunction<Tuple2<K, V>, B> pf, CanBuildFrom<TrieMap<K, V>, B, That> bf) {
        return (That)TraversableLike$class.collect(this, pf, bf);
    }

    @Override
    public Tuple2<TrieMap<K, V>, TrieMap<K, V>> partition(Function1<Tuple2<K, V>, Object> p) {
        return TraversableLike$class.partition(this, p);
    }

    @Override
    public <K> scala.collection.immutable.Map<K, TrieMap<K, V>> groupBy(Function1<Tuple2<K, V>, K> f) {
        return TraversableLike$class.groupBy(this, f);
    }

    @Override
    public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<TrieMap<K, V>, B, That> cbf) {
        return (That)TraversableLike$class.scan(this, z, op, cbf);
    }

    @Override
    public <B, That> That scanLeft(B z, Function2<B, Tuple2<K, V>, B> op, CanBuildFrom<TrieMap<K, V>, B, That> bf) {
        return (That)TraversableLike$class.scanLeft(this, z, op, bf);
    }

    @Override
    public <B, That> That scanRight(B z, Function2<Tuple2<K, V>, B, B> op, CanBuildFrom<TrieMap<K, V>, B, That> bf) {
        return (That)TraversableLike$class.scanRight(this, z, op, bf);
    }

    @Override
    public Option<Tuple2<K, V>> headOption() {
        return TraversableLike$class.headOption(this);
    }

    @Override
    public Object tail() {
        return TraversableLike$class.tail(this);
    }

    @Override
    public Object last() {
        return TraversableLike$class.last(this);
    }

    @Override
    public Option<Tuple2<K, V>> lastOption() {
        return TraversableLike$class.lastOption(this);
    }

    @Override
    public Object init() {
        return TraversableLike$class.init(this);
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
    public Object dropWhile(Function1 p) {
        return TraversableLike$class.dropWhile(this, p);
    }

    @Override
    public Tuple2<TrieMap<K, V>, TrieMap<K, V>> span(Function1<Tuple2<K, V>, Object> p) {
        return TraversableLike$class.span(this, p);
    }

    @Override
    public Tuple2<TrieMap<K, V>, TrieMap<K, V>> splitAt(int n) {
        return TraversableLike$class.splitAt(this, n);
    }

    @Override
    public Iterator<TrieMap<K, V>> tails() {
        return TraversableLike$class.tails(this);
    }

    @Override
    public Iterator<TrieMap<K, V>> inits() {
        return TraversableLike$class.inits(this);
    }

    @Override
    public Traversable<Tuple2<K, V>> toTraversable() {
        return TraversableLike$class.toTraversable(this);
    }

    @Override
    public <Col> Col to(CanBuildFrom<Nothing$, Tuple2<K, V>, Col> cbf) {
        return (Col)TraversableLike$class.to(this, cbf);
    }

    @Override
    public FilterMonadic<Tuple2<K, V>, TrieMap<K, V>> withFilter(Function1<Tuple2<K, V>, Object> p) {
        return TraversableLike$class.withFilter(this, p);
    }

    @Override
    public List<Tuple2<K, V>> reversed() {
        return TraversableOnce$class.reversed(this);
    }

    @Override
    public boolean nonEmpty() {
        return TraversableOnce$class.nonEmpty(this);
    }

    @Override
    public int count(Function1<Tuple2<K, V>, Object> p) {
        return TraversableOnce$class.count(this, p);
    }

    @Override
    public <B> Option<B> collectFirst(PartialFunction<Tuple2<K, V>, B> pf) {
        return TraversableOnce$class.collectFirst(this, pf);
    }

    @Override
    public <B> B $div$colon(B z, Function2<B, Tuple2<K, V>, B> op) {
        return (B)TraversableOnce$class.$div$colon(this, z, op);
    }

    @Override
    public <B> B $colon$bslash(B z, Function2<Tuple2<K, V>, B, B> op) {
        return (B)TraversableOnce$class.$colon$bslash(this, z, op);
    }

    @Override
    public <B> B foldLeft(B z, Function2<B, Tuple2<K, V>, B> op) {
        return (B)TraversableOnce$class.foldLeft(this, z, op);
    }

    @Override
    public <B> B reduceLeft(Function2<B, Tuple2<K, V>, B> op) {
        return (B)TraversableOnce$class.reduceLeft(this, op);
    }

    @Override
    public <B> Option<B> reduceLeftOption(Function2<B, Tuple2<K, V>, B> op) {
        return TraversableOnce$class.reduceLeftOption(this, op);
    }

    @Override
    public <B> Option<B> reduceRightOption(Function2<Tuple2<K, V>, B, B> op) {
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
    public <B> B aggregate(Function0<B> z, Function2<B, Tuple2<K, V>, B> seqop, Function2<B, B, B> combop) {
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
    public Object min(Ordering cmp) {
        return TraversableOnce$class.min(this, cmp);
    }

    @Override
    public Object max(Ordering cmp) {
        return TraversableOnce$class.max(this, cmp);
    }

    @Override
    public Object maxBy(Function1 f, Ordering cmp) {
        return TraversableOnce$class.maxBy(this, f, cmp);
    }

    @Override
    public Object minBy(Function1 f, Ordering cmp) {
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
    public List<Tuple2<K, V>> toList() {
        return TraversableOnce$class.toList(this);
    }

    @Override
    public IndexedSeq<Tuple2<K, V>> toIndexedSeq() {
        return TraversableOnce$class.toIndexedSeq(this);
    }

    @Override
    public <B> scala.collection.immutable.Set<B> toSet() {
        return TraversableOnce$class.toSet(this);
    }

    @Override
    public Vector<Tuple2<K, V>> toVector() {
        return TraversableOnce$class.toVector(this);
    }

    @Override
    public <T, U> scala.collection.immutable.Map<T, U> toMap(Predef$.less.colon.less<Tuple2<K, V>, Tuple2<T, U>> ev) {
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
    public StringBuilder addString(StringBuilder b, String sep) {
        return TraversableOnce$class.addString(this, b, sep);
    }

    @Override
    public StringBuilder addString(StringBuilder b) {
        return TraversableOnce$class.addString(this, b);
    }

    private Hashing<K> hashingobj() {
        return this.hashingobj;
    }

    private void hashingobj_$eq(Hashing<K> x$1) {
        this.hashingobj = x$1;
    }

    private Equiv<K> equalityobj() {
        return this.equalityobj;
    }

    private void equalityobj_$eq(Equiv<K> x$1) {
        this.equalityobj = x$1;
    }

    private AtomicReferenceFieldUpdater<TrieMap<K, V>, Object> rootupdater() {
        return this.rootupdater;
    }

    private void rootupdater_$eq(AtomicReferenceFieldUpdater<TrieMap<K, V>, Object> x$1) {
        this.rootupdater = x$1;
    }

    public Hashing<K> hashing() {
        return this.hashingobj();
    }

    public Equiv<K> equality() {
        return this.equalityobj();
    }

    public Object root() {
        return this.root;
    }

    public void root_$eq(Object x$1) {
        this.root = x$1;
    }

    private void writeObject(ObjectOutputStream out) {
        out.writeObject(this.hashingobj());
        out.writeObject(this.equalityobj());
        Iterator<Tuple2<K, V>> it = this.iterator();
        while (it.hasNext()) {
            Tuple2<K, V> tuple2 = it.next();
            if (tuple2 != null) {
                Tuple2<K, V> tuple22 = new Tuple2<K, V>(tuple2._1(), tuple2._2());
                K k = tuple22._1();
                V v = tuple22._2();
                out.writeObject(k);
                out.writeObject(v);
                continue;
            }
            throw new MatchError(tuple2);
        }
        out.writeObject(TrieMapSerializationEnd$.MODULE$);
    }

    private void readObject(ObjectInputStream in) {
        TrieMapSerializationEnd$ trieMapSerializationEnd$;
        Object object;
        this.root_$eq(INode$.MODULE$.newRootNode());
        this.rootupdater_$eq(AtomicReferenceFieldUpdater.newUpdater(TrieMap.class, Object.class, "root"));
        this.hashingobj_$eq((Hashing)in.readObject());
        this.equalityobj_$eq((Equiv)in.readObject());
        do {
            Object obj = in.readObject();
            TrieMapSerializationEnd$ trieMapSerializationEnd$2 = TrieMapSerializationEnd$.MODULE$;
            if (obj == null || !obj.equals(trieMapSerializationEnd$2)) {
                Object v = in.readObject();
                this.update((K)obj, (V)v);
            }
            object = obj;
            trieMapSerializationEnd$ = TrieMapSerializationEnd$.MODULE$;
        } while (object == null || !object.equals(trieMapSerializationEnd$));
    }

    public boolean CAS_ROOT(Object ov, Object nv) {
        return this.rootupdater().compareAndSet(this, ov, nv);
    }

    public INode<K, V> readRoot(boolean abort) {
        return this.RDCSS_READ_ROOT(abort);
    }

    public boolean readRoot$default$1() {
        return false;
    }

    /*
     * WARNING - void declaration
     */
    public INode<K, V> RDCSS_READ_ROOT(boolean abort) {
        void var4_2;
        block4: {
            INode<K, V> iNode;
            block3: {
                Object r;
                block2: {
                    INode<K, V> iNode2;
                    r = this.root();
                    if (!(r instanceof INode)) break block2;
                    iNode = iNode2 = (INode<K, V>)r;
                    break block3;
                }
                if (!(r instanceof RDCSS_Descriptor)) break block4;
                iNode = this.RDCSS_Complete(abort);
            }
            return iNode;
        }
        throw new MatchError(var4_2);
    }

    public boolean RDCSS_READ_ROOT$default$1() {
        return false;
    }

    /*
     * WARNING - void declaration
     */
    private INode<K, V> RDCSS_Complete(boolean abort) {
        void var10_8;
        block5: {
            RDCSS_Descriptor rDCSS_Descriptor;
            block6: {
                INode iNode;
                block4: {
                    INode iNode2;
                    block7: {
                        INode ov;
                        while (true) {
                            Object v;
                            if ((v = this.root()) instanceof INode) {
                                INode iNode3;
                                iNode = iNode3 = (INode)v;
                                break block4;
                            }
                            if (!(v instanceof RDCSS_Descriptor)) break block5;
                            rDCSS_Descriptor = (RDCSS_Descriptor)v;
                            if (rDCSS_Descriptor == null) break block6;
                            Tuple3 tuple3 = new Tuple3(rDCSS_Descriptor.old(), rDCSS_Descriptor.expectedmain(), rDCSS_Descriptor.nv());
                            ov = tuple3._1();
                            MainNode exp = tuple3._2();
                            INode nv = tuple3._3();
                            if (abort) {
                                if (!this.CAS_ROOT(rDCSS_Descriptor, ov)) continue;
                                iNode2 = ov;
                                break block7;
                            }
                            MainNode oldmain = ov.gcasRead(this);
                            if (oldmain == exp) {
                                if (!this.CAS_ROOT(rDCSS_Descriptor, nv)) continue;
                                rDCSS_Descriptor.committed_$eq(true);
                                iNode2 = nv;
                                break block7;
                            }
                            if (this.CAS_ROOT(rDCSS_Descriptor, ov)) break;
                        }
                        iNode2 = ov;
                    }
                    iNode = iNode2;
                }
                return iNode;
            }
            throw new MatchError(rDCSS_Descriptor);
        }
        throw new MatchError(var10_8);
    }

    private boolean RDCSS_ROOT(INode<K, V> ov, MainNode<K, V> expectedmain, INode<K, V> nv) {
        boolean bl;
        RDCSS_Descriptor<K, V> desc = new RDCSS_Descriptor<K, V>(ov, expectedmain, nv);
        if (this.CAS_ROOT(ov, desc)) {
            this.RDCSS_Complete(false);
            bl = desc.committed();
        } else {
            bl = false;
        }
        return bl;
    }

    private void inserthc(K k, int hc, V v) {
        INode<K, V> r;
        while (!(r = this.RDCSS_READ_ROOT(this.RDCSS_READ_ROOT$default$1())).rec_insert(k, v, hc, 0, null, r.gen, this)) {
        }
    }

    private Option<V> insertifhc(K k, int hc, V v, Object cond) {
        INode<K, V> r;
        Option<V> ret;
        while ((ret = (r = this.RDCSS_READ_ROOT(this.RDCSS_READ_ROOT$default$1())).rec_insertif(k, v, hc, cond, 0, null, r.gen, this)) == null) {
        }
        return ret;
    }

    private Object lookuphc(K k, int hc) {
        INode r;
        Object res;
        while ((res = (r = this.RDCSS_READ_ROOT(this.RDCSS_READ_ROOT$default$1())).rec_lookup(k, hc, 0, null, r.gen, this)) == INodeBase.RESTART) {
        }
        return res;
    }

    private Option<V> removehc(K k, V v, int hc) {
        INode<K, V> r;
        Option<V> res;
        while ((res = (r = this.RDCSS_READ_ROOT(this.RDCSS_READ_ROOT$default$1())).rec_remove(k, v, hc, 0, null, r.gen, this)) == null) {
        }
        return res;
    }

    public String string() {
        return this.RDCSS_READ_ROOT(this.RDCSS_READ_ROOT$default$1()).string(0);
    }

    @Override
    public TrieMap<K, V> seq() {
        return this;
    }

    @Override
    public ParTrieMap<K, V> par() {
        return new ParTrieMap(this);
    }

    @Override
    public TrieMap<K, V> empty() {
        return new TrieMap<K, V>();
    }

    public boolean isReadOnly() {
        return this.rootupdater() == null;
    }

    public boolean nonReadOnly() {
        return this.rootupdater() != null;
    }

    public TrieMap<K, V> snapshot() {
        MainNode<K, V> expmain;
        INode<K, V> r;
        while (!this.RDCSS_ROOT(r = this.RDCSS_READ_ROOT(this.RDCSS_READ_ROOT$default$1()), expmain = r.gcasRead(this), r.copyToGen(new Gen(), this))) {
        }
        return new TrieMap<K, V>(r.copyToGen(new Gen(), this), this.rootupdater(), this.hashing(), this.equality());
    }

    public Map<K, V> readOnlySnapshot() {
        MainNode<K, V> expmain;
        INode<K, V> r;
        while (!this.RDCSS_ROOT(r = this.RDCSS_READ_ROOT(this.RDCSS_READ_ROOT$default$1()), expmain = r.gcasRead(this), r.copyToGen(new Gen(), this))) {
        }
        return new TrieMap<K, V>(r, null, this.hashing(), this.equality());
    }

    @Override
    public void clear() {
        INode<K, V> r;
        while (!this.RDCSS_ROOT(r = this.RDCSS_READ_ROOT(this.RDCSS_READ_ROOT$default$1()), r.gcasRead(this), INode$.MODULE$.newRootNode())) {
        }
    }

    public int computeHash(K k) {
        return this.hashingobj().hash(k);
    }

    public V lookup(K k) {
        int hc = this.computeHash(k);
        return (V)this.lookuphc(k, hc);
    }

    @Override
    public V apply(K k) {
        int hc = this.computeHash(k);
        Object res = this.lookuphc(k, hc);
        if (res == null) {
            throw new NoSuchElementException();
        }
        return (V)res;
    }

    @Override
    public Option<V> get(K k) {
        int hc = this.computeHash(k);
        return Option$.MODULE$.apply(this.lookuphc(k, hc));
    }

    @Override
    public Option<V> put(K key, V value) {
        int hc = this.computeHash(key);
        return this.insertifhc(key, hc, value, null);
    }

    @Override
    public void update(K k, V v) {
        int hc = this.computeHash(k);
        this.inserthc(k, hc, v);
    }

    @Override
    public TrieMap<K, V> $plus$eq(Tuple2<K, V> kv) {
        this.update(kv._1(), kv._2());
        return this;
    }

    @Override
    public Option<V> remove(K k) {
        int hc = this.computeHash(k);
        return this.removehc(k, null, hc);
    }

    public TrieMap<K, V> $minus$eq(K k) {
        this.remove(k);
        return this;
    }

    @Override
    public Option<V> putIfAbsent(K k, V v) {
        int hc = this.computeHash(k);
        return this.insertifhc(k, hc, v, INode$.MODULE$.KEY_ABSENT());
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public V getOrElseUpdate(K k, Function0<V> op) {
        V v;
        V oldv = this.lookup(k);
        if (oldv == null) {
            void var8_10;
            V v2 = op.apply();
            if (v2 == null) {
                throw new NullPointerException("Concurrent TrieMap values cannot be null.");
            }
            int hc = this.computeHash(k);
            Option<V> option = this.insertifhc(k, hc, v2, INode$.MODULE$.KEY_ABSENT());
            if (option instanceof Some) {
                Some some = (Some)option;
                Object a = some.x();
            } else {
                if (!None$.MODULE$.equals(option)) throw new MatchError(option);
                V v3 = v2;
            }
            v = var8_10;
            return v;
        } else {
            v = oldv;
        }
        return v;
    }

    @Override
    public boolean remove(K k, V v) {
        int hc = this.computeHash(k);
        return this.removehc(k, v, hc).nonEmpty();
    }

    @Override
    public boolean replace(K k, V oldvalue, V newvalue) {
        int hc = this.computeHash(k);
        return this.insertifhc(k, hc, newvalue, oldvalue).nonEmpty();
    }

    @Override
    public Option<V> replace(K k, V v) {
        int hc = this.computeHash(k);
        return this.insertifhc(k, hc, v, INode$.MODULE$.KEY_PRESENT());
    }

    @Override
    public Iterator<Tuple2<K, V>> iterator() {
        return this.nonReadOnly() ? this.readOnlySnapshot().iterator() : new TrieMapIterator(0, this, TrieMapIterator$.MODULE$.$lessinit$greater$default$3());
    }

    private int cachedSize() {
        INode<K, V> r = this.RDCSS_READ_ROOT(this.RDCSS_READ_ROOT$default$1());
        return r.cachedSize(this);
    }

    @Override
    public int size() {
        return this.nonReadOnly() ? this.readOnlySnapshot().size() : this.cachedSize();
    }

    @Override
    public String stringPrefix() {
        return "TrieMap";
    }

    private TrieMap(Object r, AtomicReferenceFieldUpdater<TrieMap<K, V>, Object> rtupd, Hashing<K> hashf, Equiv<K> ef) {
        TraversableOnce$class.$init$(this);
        Parallelizable$class.$init$(this);
        TraversableLike$class.$init$(this);
        GenericTraversableTemplate$class.$init$(this);
        GenTraversable$class.$init$(this);
        Traversable$class.$init$(this);
        scala.collection.mutable.Traversable$class.$init$(this);
        GenIterable$class.$init$(this);
        IterableLike$class.$init$(this);
        Iterable$class.$init$(this);
        scala.collection.mutable.Iterable$class.$init$(this);
        GenMapLike$class.$init$(this);
        Function1$class.$init$(this);
        PartialFunction$class.$init$(this);
        Subtractable$class.$init$(this);
        MapLike$class.$init$(this);
        Map$class.$init$(this);
        Growable$class.$init$(this);
        Builder$class.$init$(this);
        Shrinkable$class.$init$(this);
        Cloneable$class.$init$(this);
        scala.collection.mutable.MapLike$class.$init$(this);
        scala.collection.mutable.Map$class.$init$(this);
        CustomParallelizable$class.$init$(this);
        this.hashingobj = hashf instanceof Hashing.Default ? new MangledHashing() : hashf;
        this.equalityobj = ef;
        this.rootupdater = rtupd;
        this.root = r;
    }

    public TrieMap(Hashing<K> hashf, Equiv<K> ef) {
        this(INode$.MODULE$.newRootNode(), AtomicReferenceFieldUpdater.newUpdater(TrieMap.class, Object.class, "root"), hashf, ef);
    }

    public TrieMap() {
        this(Hashing$.MODULE$.default(), package$.MODULE$.Equiv().universal());
    }

    public static class MangledHashing<K>
    implements Hashing<K> {
        @Override
        public int hash(K k) {
            return scala.util.hashing.package$.MODULE$.byteswap32(ScalaRunTime$.MODULE$.hash(k));
        }
    }
}

