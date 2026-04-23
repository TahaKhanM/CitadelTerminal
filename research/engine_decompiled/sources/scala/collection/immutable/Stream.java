/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Console$;
import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Option;
import scala.PartialFunction;
import scala.PartialFunction$class;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractSeq;
import scala.collection.AbstractTraversable;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenSeq;
import scala.collection.GenSeq$class;
import scala.collection.GenSeqLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.IterableLike$class;
import scala.collection.IterableView;
import scala.collection.IterableViewLike$class;
import scala.collection.Iterator;
import scala.collection.LinearSeqLike;
import scala.collection.LinearSeqLike$class;
import scala.collection.LinearSeqOptimized;
import scala.collection.LinearSeqOptimized$class;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Seq$class;
import scala.collection.SeqLike;
import scala.collection.SeqLike$class;
import scala.collection.SeqView;
import scala.collection.SeqViewLike;
import scala.collection.SeqViewLike$class;
import scala.collection.Traversable;
import scala.collection.Traversable$class;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.TraversableView;
import scala.collection.TraversableViewLike;
import scala.collection.TraversableViewLike$class;
import scala.collection.ViewMkString$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.SliceInterval;
import scala.collection.generic.TraversableForwarder$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.LinearSeq;
import scala.collection.immutable.LinearSeq$class;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.Set$;
import scala.collection.immutable.Stream$;
import scala.collection.immutable.Stream$Empty$;
import scala.collection.immutable.Stream$cons$;
import scala.collection.immutable.StreamIterator;
import scala.collection.immutable.StreamView;
import scala.collection.immutable.StreamViewLike;
import scala.collection.immutable.StreamViewLike$class;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.LazyBuilder;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WrappedArray;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParSeq;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;
import scala.runtime.RichInt$;

@ScalaSignature(bytes="\u0006\u0001\u0019eeAB\u0001\u0003\u0003\u0003I\u0011F\u0001\u0004TiJ,\u0017-\u001c\u0006\u0003\u0007\u0011\t\u0011\"[7nkR\f'\r\\3\u000b\u0005\u00151\u0011AC2pY2,7\r^5p]*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0005)\t2C\u0002\u0001\f7}1#\u0006E\u0002\r\u001b=i\u0011\u0001B\u0005\u0003\u001d\u0011\u00111\"\u00112tiJ\f7\r^*fcB\u0011\u0001#\u0005\u0007\u0001\t\u0019\u0011\u0002\u0001\"b\u0001'\t\t\u0011)\u0005\u0002\u00151A\u0011QCF\u0007\u0002\r%\u0011qC\u0002\u0002\b\u001d>$\b.\u001b8h!\t)\u0012$\u0003\u0002\u001b\r\t\u0019\u0011I\\=\u0011\u0007qir\"D\u0001\u0003\u0013\tq\"AA\u0005MS:,\u0017M]*fcB!\u0001eI\b&\u001b\u0005\t#B\u0001\u0012\u0005\u0003\u001d9WM\\3sS\u000eL!\u0001J\u0011\u00035\u001d+g.\u001a:jGR\u0013\u0018M^3sg\u0006\u0014G.\u001a+f[Bd\u0017\r^3\u0011\u0005q\u0001\u0001\u0003\u0002\u0007(\u001f%J!\u0001\u000b\u0003\u0003%1Kg.Z1s'\u0016\fx\n\u001d;j[&TX\r\u001a\t\u00049\u0001y\u0001CA\u000b,\u0013\tacA\u0001\u0007TKJL\u0017\r\\5{C\ndW\rC\u0003/\u0001\u0011\u0005q&\u0001\u0004=S:LGO\u0010\u000b\u0002S!)\u0011\u0007\u0001C!e\u0005I1m\\7qC:LwN\\\u000b\u0002gA\u0019\u0001\u0005N\u0013\n\u0005U\n#\u0001E$f]\u0016\u0014\u0018nY\"p[B\fg.[8o\u0011\u00159\u0004A\"\u00019\u0003\u001dI7/R7qif,\u0012!\u000f\t\u0003+iJ!a\u000f\u0004\u0003\u000f\t{w\u000e\\3b]\")Q\b\u0001D\u0001}\u0005!\u0001.Z1e+\u0005y\u0001\"\u0002!\u0001\r\u0003\t\u0015\u0001\u0002;bS2,\u0012!\u000b\u0005\u0006\u0007\u00021\t\u0002O\u0001\fi\u0006LG\u000eR3gS:,G\rC\u0003F\u0001\u0011\u0005a)\u0001\u0004baB,g\u000eZ\u000b\u0003\u000f*#\"\u0001S'\u0011\u0007q\u0001\u0011\n\u0005\u0002\u0011\u0015\u0012)1\n\u0012b\u0001\u0019\n\t!)\u0005\u0002\u00101!1a\n\u0012CA\u0002=\u000bAA]3tiB\u0019Q\u0003\u0015*\n\u0005E3!\u0001\u0003\u001fcs:\fW.\u001a \u0011\u00071\u0019\u0016*\u0003\u0002U\t\tyAK]1wKJ\u001c\u0018M\u00197f\u001f:\u001cW\rC\u0003W\u0001\u0011\u0005\u0011)A\u0003g_J\u001cW\rC\u0003Y\u0001\u0011\u0005\u0011,A\u0003qe&tG\u000fF\u0001[!\t)2,\u0003\u0002]\r\t!QK\\5u\u0011\u0015A\u0006\u0001\"\u0001_)\tQv\fC\u0003a;\u0002\u0007\u0011-A\u0002tKB\u0004\"AY3\u000f\u0005U\u0019\u0017B\u00013\u0007\u0003\u0019\u0001&/\u001a3fM&\u0011am\u001a\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u00114\u0001\"B5\u0001\t\u0003R\u0017A\u00027f]\u001e$\b.F\u0001l!\t)B.\u0003\u0002n\r\t\u0019\u0011J\u001c;\t\u000b=\u0004A\u0011\u00029\u0002\r\u0005\u001cH\u000b[1u+\t\t8\u000f\u0006\u0002skB\u0011\u0001c\u001d\u0003\u0006i:\u0014\ra\u0005\u0002\u0005)\"\fG\u000fC\u0003w]\u0002\u0007q/A\u0001y!\t)\u00020\u0003\u0002z\r\t1\u0011I\\=SK\u001aD#A\\>\u0011\u0005Ua\u0018BA?\u0007\u0005\u0019Ig\u000e\\5oK\"1q\u0010\u0001C\u0005\u0003\u0003\t\u0001\"Y:TiJ,\u0017-\\\u000b\u0005\u0003\u0007\tI\u0001\u0006\u0003\u0002\u0006\u0005-\u0001\u0003\u0002\u000f\u0001\u0003\u000f\u00012\u0001EA\u0005\t\u0015YeP1\u0001\u0014\u0011\u00151h\u00101\u0001xQ\tq8\u0010C\u0004\u0002\u0012\u0001!I!a\u0005\u0002\u001f%\u001c8\u000b\u001e:fC6\u0014U/\u001b7eKJ,b!!\u0006\u0002$\u0005\u001dBcA\u001d\u0002\u0018!A\u0011\u0011DA\b\u0001\u0004\tY\"\u0001\u0002cMBA\u0001%!\b*\u0003C\t)#C\u0002\u0002 \u0005\u0012AbQ1o\u0005VLG\u000e\u001a$s_6\u00042\u0001EA\u0012\t\u0019Y\u0015q\u0002b\u0001'A\u0019\u0001#a\n\u0005\rQ\fyA1\u0001\u0014Q\r\tya\u001f\u0005\u0007\u0003[\u0001A\u0011I!\u0002\u0011Q|7\u000b\u001e:fC6Da!!\r\u0001\t\u0003B\u0014a\u00045bg\u0012+g-\u001b8ji\u0016\u001c\u0016N_3\t\u000f\u0005U\u0002\u0001\"\u0011\u00028\u0005QA\u0005\u001d7vg\u0012\u0002H.^:\u0016\r\u0005e\u0012qIA )\u0011\tY$!\u0013\u0015\t\u0005u\u0012\u0011\t\t\u0004!\u0005}BA\u0002;\u00024\t\u00071\u0003\u0003\u0005\u0002\u001a\u0005M\u00029AA\"!!\u0001\u0013QD\u0015\u0002F\u0005u\u0002c\u0001\t\u0002H\u001111*a\rC\u00021C\u0001\"a\u0013\u00024\u0001\u0007\u0011QJ\u0001\u0005i\"\fG\u000fE\u0003\r\u0003\u001f\n)%C\u0002\u0002R\u0011\u0011!cR3o)J\fg/\u001a:tC\ndWm\u00148dK\"9\u0011Q\u000b\u0001\u0005B\u0005]\u0013a\u0003\u0013qYV\u001cHeY8m_:,b!!\u0017\u0002h\u0005}C\u0003BA.\u0003S\"B!!\u0018\u0002bA\u0019\u0001#a\u0018\u0005\rQ\f\u0019F1\u0001\u0014\u0011!\tI\"a\u0015A\u0004\u0005\r\u0004\u0003\u0003\u0011\u0002\u001e%\n)'!\u0018\u0011\u0007A\t9\u0007\u0002\u0004L\u0003'\u0012\r\u0001\u0014\u0005\t\u0003W\n\u0019\u00061\u0001\u0002f\u0005!Q\r\\3n\u0011\u001d\ty\u0007\u0001C#\u0003c\n\u0001b]2b]2+g\r^\u000b\u0007\u0003g\n\u0019)a\u001f\u0015\t\u0005U\u0014q\u0012\u000b\u0005\u0003o\n)\t\u0006\u0003\u0002z\u0005u\u0004c\u0001\t\u0002|\u00111A/!\u001cC\u0002MA\u0001\"!\u0007\u0002n\u0001\u000f\u0011q\u0010\t\tA\u0005u\u0011&!!\u0002zA\u0019\u0001#a!\u0005\r-\u000biG1\u0001\u0014\u0011!\t9)!\u001cA\u0002\u0005%\u0015AA8q!!)\u00121RAA\u001f\u0005\u0005\u0015bAAG\r\tIa)\u001e8di&|gN\r\u0005\t\u0003#\u000bi\u00071\u0001\u0002\u0002\u0006\t!\u0010C\u0004\u0002\u0016\u0002!)%a&\u0002\u00075\f\u0007/\u0006\u0004\u0002\u001a\u0006\u001d\u0016q\u0014\u000b\u0005\u00037\u000bI\u000b\u0006\u0003\u0002\u001e\u0006\u0005\u0006c\u0001\t\u0002 \u00121A/a%C\u0002MA\u0001\"!\u0007\u0002\u0014\u0002\u000f\u00111\u0015\t\tA\u0005u\u0011&!*\u0002\u001eB\u0019\u0001#a*\u0005\r-\u000b\u0019J1\u0001\u0014\u0011!\tY+a%A\u0002\u00055\u0016!\u00014\u0011\rU\tykDAS\u0013\r\t\tL\u0002\u0002\n\rVt7\r^5p]FBq!!.\u0001\t\u000b\n9,A\u0004d_2dWm\u0019;\u0016\r\u0005e\u0016qYA`)\u0011\tY,!3\u0015\t\u0005u\u0016\u0011\u0019\t\u0004!\u0005}FA\u0002;\u00024\n\u00071\u0003\u0003\u0005\u0002\u001a\u0005M\u00069AAb!!\u0001\u0013QD\u0015\u0002F\u0006u\u0006c\u0001\t\u0002H\u001211*a-C\u0002MA\u0001\"a3\u00024\u0002\u0007\u0011QZ\u0001\u0003a\u001a\u0004b!FAh\u001f\u0005\u0015\u0017bAAi\r\ty\u0001+\u0019:uS\u0006dg)\u001e8di&|g\u000eC\u0004\u0002V\u0002!)%a6\u0002\u000f\u0019d\u0017\r^'baV1\u0011\u0011\\At\u0003?$B!a7\u0002jR!\u0011Q\\Aq!\r\u0001\u0012q\u001c\u0003\u0007i\u0006M'\u0019A\n\t\u0011\u0005e\u00111\u001ba\u0002\u0003G\u0004\u0002\u0002IA\u000fS\u0005\u0015\u0018Q\u001c\t\u0004!\u0005\u001dHAB&\u0002T\n\u00071\u0003\u0003\u0005\u0002,\u0006M\u0007\u0019AAv!\u0019)\u0012qV\b\u0002nB)A\"a\u0014\u0002f\"9\u0011\u0011\u001f\u0001\u0005B\u0005M\u0018A\u00024jYR,'\u000fF\u0002*\u0003kD\u0001\"a>\u0002p\u0002\u0007\u0011\u0011`\u0001\u0002aB)Q#a,\u0010s!9\u0011Q \u0001\u0005F\u0005}\u0018AC<ji\"4\u0015\u000e\u001c;feR!!\u0011\u0001B5!\u0011\u0011\u0019A!\u0002\u000e\u0003\u00011aAa\u0002\u0001\u0005\t%!\u0001E*ue\u0016\fWnV5uQ\u001aKG\u000e^3s'\u0011\u0011)Aa\u0003\u0011\t\t\r!QB\u0005\u0005\u0005\u001f\u0011\tB\u0001\u0006XSRDg)\u001b7uKJL1Aa\u0005\u0005\u0005=!&/\u0019<feN\f'\r\\3MS.,\u0007bCA|\u0005\u000b\u0011\t\u0011)A\u0005\u0003sDqA\fB\u0003\t\u0003\u0011I\u0002\u0006\u0003\u0003\u0002\tm\u0001\u0002CA|\u0005/\u0001\r!!?\t\u0011\u0005U%Q\u0001C!\u0005?)bA!\t\u00030\t\u001dB\u0003\u0002B\u0012\u0005c!BA!\n\u0003*A\u0019\u0001Ca\n\u0005\rQ\u0014iB1\u0001\u0014\u0011!\tIB!\bA\u0004\t-\u0002\u0003\u0003\u0011\u0002\u001e%\u0012iC!\n\u0011\u0007A\u0011y\u0003\u0002\u0004L\u0005;\u0011\ra\u0005\u0005\t\u0003W\u0013i\u00021\u0001\u00034A1Q#a,\u0010\u0005[A\u0001\"!6\u0003\u0006\u0011\u0005#qG\u000b\u0007\u0005s\u00119Ea\u0010\u0015\t\tm\"\u0011\n\u000b\u0005\u0005{\u0011\t\u0005E\u0002\u0011\u0005\u007f!a\u0001\u001eB\u001b\u0005\u0004\u0019\u0002\u0002CA\r\u0005k\u0001\u001dAa\u0011\u0011\u0011\u0001\ni\"\u000bB#\u0005{\u00012\u0001\u0005B$\t\u0019Y%Q\u0007b\u0001'!A\u00111\u0016B\u001b\u0001\u0004\u0011Y\u0005\u0005\u0004\u0016\u0003_{!Q\n\t\u0006\u0019\u0005=#Q\t\u0005\t\u0005#\u0012)\u0001\"\u0011\u0003T\u00059am\u001c:fC\u000eDW\u0003\u0002B+\u0005;\"2A\u0017B,\u0011!\tYKa\u0014A\u0002\te\u0003CB\u000b\u00020>\u0011Y\u0006E\u0002\u0011\u0005;\"qAa\u0018\u0003P\t\u00071CA\u0001V\u0011!\tiP!\u0002\u0005B\t\rD\u0003\u0002B\u0001\u0005KB\u0001Ba\u001a\u0003b\u0001\u0007\u0011\u0011`\u0001\u0002c\"A\u0011q_A~\u0001\u0004\tI\u0010C\u0004\u0003n\u0001!\tEa\u001c\u0002\u0011%$XM]1u_J,\"A!\u001d\u0011\t1\u0011\u0019hD\u0005\u0004\u0005k\"!\u0001C%uKJ\fGo\u001c:\t\u000f\tE\u0003\u0001\"\u0012\u0003zU!!1\u0010BB)\rQ&Q\u0010\u0005\t\u0003W\u00139\b1\u0001\u0003\u0000A1Q#a,\u0010\u0005\u0003\u00032\u0001\u0005BB\t\u001d\u0011yFa\u001eC\u0002MACAa\u001e\u0003\bB!!\u0011\u0012BH\u001b\t\u0011YIC\u0002\u0003\u000e\u001a\t!\"\u00198o_R\fG/[8o\u0013\u0011\u0011\tJa#\u0003\u000fQ\f\u0017\u000e\u001c:fG\"9!Q\u0013\u0001\u0005F\t]\u0015\u0001\u00034pY\u0012dUM\u001a;\u0016\t\te%q\u0014\u000b\u0005\u00057\u0013)\u000b\u0006\u0003\u0003\u001e\n\u0005\u0006c\u0001\t\u0003 \u001211Ja%C\u0002MA\u0001\"a\"\u0003\u0014\u0002\u0007!1\u0015\t\t+\u0005-%QT\b\u0003\u001e\"A\u0011\u0011\u0013BJ\u0001\u0004\u0011i\n\u000b\u0003\u0003\u0014\n\u001d\u0005b\u0002BV\u0001\u0011\u0015#QV\u0001\u000be\u0016$WoY3MK\u001a$X\u0003\u0002BX\u0005g#BA!-\u00036B\u0019\u0001Ca-\u0005\r-\u0013IK1\u0001M\u0011!\tYK!+A\u0002\t]\u0006\u0003C\u000b\u0002\f\nEvB!-\t\u000f\tm\u0006\u0001\"\u0011\u0003>\u0006I\u0001/\u0019:uSRLwN\u001c\u000b\u0005\u0005\u007f\u0013)\rE\u0003\u0016\u0005\u0003L\u0013&C\u0002\u0003D\u001a\u0011a\u0001V;qY\u0016\u0014\u0004\u0002CA|\u0005s\u0003\r!!?\t\u000f\t%\u0007\u0001\"\u0012\u0003L\u0006\u0019!0\u001b9\u0016\u0011\t5'Q\u001cBr\u0005'$BAa4\u0003fR!!\u0011\u001bBk!\r\u0001\"1\u001b\u0003\u0007i\n\u001d'\u0019A\n\t\u0011\u0005e!q\u0019a\u0002\u0005/\u0004\u0002\u0002IA\u000fS\te'\u0011\u001b\t\b+\t\u0005'1\u001cBq!\r\u0001\"Q\u001c\u0003\b\u0005?\u00149M1\u0001M\u0005\t\t\u0015\u0007E\u0002\u0011\u0005G$aa\u0013Bd\u0005\u0004\u0019\u0002\u0002CA&\u0005\u000f\u0004\rAa:\u0011\u000b1\u0011IO!9\n\u0007\t-HAA\u0006HK:LE/\u001a:bE2,\u0007b\u0002Bx\u0001\u0011\u0005#\u0011_\u0001\ru&\u0004x+\u001b;i\u0013:$W\r_\u000b\u0007\u0005g\u001c\tAa>\u0015\t\tU(\u0011 \t\u0004!\t]HA\u0002;\u0003n\n\u00071\u0003\u0003\u0005\u0002\u001a\t5\b9\u0001B~!!\u0001\u0013QD\u0015\u0003~\nU\bCB\u000b\u0003B\n}8\u000eE\u0002\u0011\u0007\u0003!qAa8\u0003n\n\u0007A\nC\u0004\u0004\u0006\u0001!\tea\u0002\u0002\u0013\u0005$Gm\u0015;sS:<GCCB\u0005\u0007+\u0019Ib!\b\u0004 A!11BB\t\u001b\t\u0019iAC\u0002\u0004\u0010\u0011\tq!\\;uC\ndW-\u0003\u0003\u0004\u0014\r5!!D*ue&twMQ;jY\u0012,'\u000f\u0003\u0005\u0004\u0018\r\r\u0001\u0019AB\u0005\u0003\u0005\u0011\u0007bBB\u000e\u0007\u0007\u0001\r!Y\u0001\u0006gR\f'\u000f\u001e\u0005\u0007A\u000e\r\u0001\u0019A1\t\u000f\r\u000521\u0001a\u0001C\u0006\u0019QM\u001c3\t\u000f\r\u0015\u0002\u0001\"\u0011\u0004(\u0005AQn[*ue&tw\rF\u0002b\u0007SAa\u0001YB\u0012\u0001\u0004\t\u0007bBB\u0013\u0001\u0011\u00053QF\u000b\u0002C\"91Q\u0005\u0001\u0005B\rEBcB1\u00044\rU2q\u0007\u0005\b\u00077\u0019y\u00031\u0001b\u0011\u0019\u00017q\u0006a\u0001C\"91\u0011EB\u0018\u0001\u0004\t\u0007bBB\u001e\u0001\u0011\u00053QH\u0001\ti>\u001cFO]5oOR\t\u0011\rC\u0004\u0004B\u0001!\tea\u0011\u0002\u000fM\u0004H.\u001b;BiR!!qXB#\u0011\u001d\u00199ea\u0010A\u0002-\f\u0011A\u001c\u0005\b\u0007\u0017\u0002A\u0011IB'\u0003\u0011!\u0018m[3\u0015\u0007%\u001ay\u0005C\u0004\u0004H\r%\u0003\u0019A6\t\u000f\rM\u0003\u0001\"\u0012\u0004V\u0005!AM]8q)\rI3q\u000b\u0005\b\u0007\u000f\u001a\t\u00061\u0001lQ\u0011\u0019\tFa\"\t\u000f\ru\u0003\u0001\"\u0011\u0004`\u0005)1\u000f\\5dKR)\u0011f!\u0019\u0004f!911MB.\u0001\u0004Y\u0017\u0001\u00024s_6Dqaa\u001a\u0004\\\u0001\u00071.A\u0003v]RLG\u000e\u0003\u0004\u0004l\u0001!\t%Q\u0001\u0005S:LG\u000fC\u0004\u0004p\u0001!\te!\u001d\u0002\u0013Q\f7.\u001a*jO\"$HcA\u0015\u0004t!91qIB7\u0001\u0004Y\u0007bBB<\u0001\u0011\u00053\u0011P\u0001\nIJ|\u0007OU5hQR$2!KB>\u0011\u001d\u00199e!\u001eA\u0002-Dqaa \u0001\t\u0003\u001a\t)A\u0005uC.,w\u000b[5mKR\u0019\u0011fa!\t\u0011\u0005]8Q\u0010a\u0001\u0003sDqaa\"\u0001\t\u0003\u001aI)A\u0005ee>\u0004x\u000b[5mKR\u0019\u0011fa#\t\u0011\u0005]8Q\u0011a\u0001\u0003sDaaa$\u0001\t\u0003\n\u0015\u0001\u00033jgRLgn\u0019;\t\u000f\rM\u0005\u0001\"\u0011\u0004\u0016\u0006)\u0001/\u00193U_V11qSBS\u0007;#ba!'\u0004(\u000e-F\u0003BBN\u0007?\u00032\u0001EBO\t\u0019!8\u0011\u0013b\u0001'!A\u0011\u0011DBI\u0001\b\u0019\t\u000b\u0005\u0005!\u0003;I31UBN!\r\u00012Q\u0015\u0003\u0007\u0017\u000eE%\u0019\u0001'\t\u000f\r%6\u0011\u0013a\u0001W\u0006\u0019A.\u001a8\t\u0011\u0005-4\u0011\u0013a\u0001\u0007GCaaa,\u0001\t\u0003\n\u0015a\u0002:fm\u0016\u00148/\u001a\u0005\b\u0007g\u0003A\u0011IB[\u0003\u001d1G.\u0019;uK:,Baa.\u0004>R!1\u0011XB`!\u0011a\u0002aa/\u0011\u0007A\u0019i\f\u0002\u0004L\u0007c\u0013\ra\u0005\u0005\t\u0007\u0003\u001c\t\fq\u0001\u0004D\u0006i\u0011m\u001d+sCZ,'o]1cY\u0016\u0004b!FAX\u001f\r\u0015\u0007#\u0002\u0007\u0002P\rm\u0006bBBe\u0001\u0011\u000531Z\u0001\u0005m&,w/\u0006\u0002\u0004NJ)1qZ<\u0004T\u001a91\u0011[Bd\u0001\r5'\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004#\u0002\u000f\u0004V>I\u0013bABl\u0005\tQ1\u000b\u001e:fC64\u0016.Z<\t\u000f\rm\u0007\u0001\"\u0011\u0004^\u0006a1\u000f\u001e:j]\u001e\u0004&/\u001a4jqV\u00111q\u001c\t\u0005\u0007C\u001cY/\u0004\u0002\u0004d*!1Q]Bt\u0003\u0011a\u0017M\\4\u000b\u0005\r%\u0018\u0001\u00026bm\u0006L1AZBrQ\u001d\u00011q^B{\u0007s\u00042!FBy\u0013\r\u0019\u0019P\u0002\u0002\u0016I\u0016\u0004(/Z2bi\u0016$\u0017J\u001c5fe&$\u0018M\\2fC\t\u001990\u0001\u000eUQ&\u001c\be\u00197bgN\u0004s/\u001b7mA\t,\u0007e]3bY\u0016$g&\t\u0002\u0004|\u00061!GL\u00192]A:qaa@\u0003\u0011\u0003!\t!\u0001\u0004TiJ,\u0017-\u001c\t\u00049\u0011\raAB\u0001\u0003\u0011\u0003!)aE\u0003\u0005\u0004\u0011\u001d!\u0006\u0005\u0003!\t\u0013)\u0013b\u0001C\u0006C\tQ1+Z9GC\u000e$xN]=\t\u000f9\"\u0019\u0001\"\u0001\u0005\u0010Q\u0011A\u0011\u0001\u0004\b\t'!\u0019\u0001\u0001C\u000b\u0005I\u0019FO]3b[\u000e\u000bgNQ;jY\u00124%o\\7\u0016\t\u0011]AqE\n\u0005\t#!I\u0002\u0005\u0004\u0005\u001c\u0011uAQE\u0007\u0003\t\u0007IA\u0001b\b\u0005\"\t\u0019r)\u001a8fe&\u001c7)\u00198Ck&dGM\u0012:p[&\u0019A1E\u0011\u0003+\u001d+g\u000e\u0016:bm\u0016\u00148/\u00192mK\u001a\u000b7\r^8ssB\u0019\u0001\u0003b\n\u0005\rI!\tB1\u0001\u0014\u0011\u001dqC\u0011\u0003C\u0001\tW!\"\u0001\"\f\u0011\r\u0011mA\u0011\u0003C\u0013\u0011!!\t\u0004b\u0001\u0005\u0004\u0011M\u0012\u0001D2b]\n+\u0018\u000e\u001c3Ge>lW\u0003\u0002C\u001b\t\u0003*\"\u0001b\u000e\u0011\u0013\u0001\ni\u0002\"\u000f\u0005@\u0011\r\u0003\u0003\u0002C\u000e\twI1\u0001\"\u00105\u0005\u0011\u0019u\u000e\u001c7\u0011\u0007A!\t\u0005\u0002\u0004\u0013\t_\u0011\ra\u0005\t\u00059\u0001!y\u0004\u0003\u0005\u0005H\u0011\rA\u0011\u0001C%\u0003)qWm\u001e\"vS2$WM]\u000b\u0005\t\u0017\")&\u0006\u0002\u0005NAA11\u0002C(\t'\"9&\u0003\u0003\u0005R\r5!a\u0002\"vS2$WM\u001d\t\u0004!\u0011UCA\u0002\n\u0005F\t\u00071\u0003\u0005\u0003\u001d\u0001\u0011Mca\u0002C.\t\u0007\u0001AQ\f\u0002\u000e'R\u0014X-Y7Ck&dG-\u001a:\u0016\t\u0011}C\u0011N\n\u0005\t3\"\t\u0007\u0005\u0005\u0004\f\u0011\rDq\rC6\u0013\u0011!)g!\u0004\u0003\u00171\u000b'0\u001f\"vS2$WM\u001d\t\u0004!\u0011%DA\u0002\n\u0005Z\t\u00071\u0003\u0005\u0003\u001d\u0001\u0011\u001d\u0004b\u0002\u0018\u0005Z\u0011\u0005Aq\u000e\u000b\u0003\tc\u0002b\u0001b\u0007\u0005Z\u0011\u001d\u0004\u0002\u0003C;\t3\"\t\u0001b\u001e\u0002\rI,7/\u001e7u)\t!Yg\u0002\u0005\u0005|\u0011\r\u0001\u0012\u0001C?\u0003\u0015)U\u000e\u001d;z!\u0011!Y\u0002b \u0007\u0011\u0011\u0005E1\u0001E\u0001\t\u0007\u0013Q!R7qif\u001cB\u0001b \u0005\u0006B\u0019A\u0004\u0001\u000b\t\u000f9\"y\b\"\u0001\u0005\nR\u0011AQ\u0010\u0005\u0007o\u0011}D\u0011\t\u001d\t\u000fu\"y\b\"\u0011\u0005\u0010V\tA\u0003C\u0004A\t\u007f\"\t\u0005b$\t\r\r#y\b\"\u00019\u0011)!9\nb \u0002\u0002\u0013%A\u0011T\u0001\fe\u0016\fGMU3t_24X\r\u0006\u0002\u0005\u001cB!1\u0011\u001dCO\u0013\u0011!yja9\u0003\r=\u0013'.Z2u\u0011!!\u0019\u000bb\u0001\u0005B\u0011\u0015\u0016!B3naRLX\u0003\u0002CT\t[+\"\u0001\"+\u0011\tq\u0001A1\u0016\t\u0004!\u00115FA\u0002\n\u0005\"\n\u00071\u0003\u0003\u0005\u00052\u0012\rA\u0011\tCZ\u0003\u0015\t\u0007\u000f\u001d7z+\u0011!)\fb/\u0015\t\u0011]FQ\u0018\t\u00059\u0001!I\fE\u0002\u0011\tw#aA\u0005CX\u0005\u0004\u0019\u0002\u0002\u0003C`\t_\u0003\r\u0001\"1\u0002\u0005a\u001c\b#B\u000b\u0005D\u0012e\u0016b\u0001Cc\r\tQAH]3qK\u0006$X\r\u001a \u0007\u000f\u0011%G1\u0001\u0001\u0005L\nY1i\u001c8t/J\f\u0007\u000f]3s+\u0011!i\r\"7\u0014\u0007\u0011\u001dw\u000fC\u0006\u0005R\u0012\u001d'\u0011!S\u0001\n\u0011M\u0017A\u0001;m!\u0011)\u0002\u000b\"6\u0011\tq\u0001Aq\u001b\t\u0004!\u0011eGA\u0002\n\u0005H\n\u00071\u0003C\u0004/\t\u000f$\t\u0001\"8\u0015\t\u0011}G\u0011\u001d\t\u0007\t7!9\rb6\t\u0013\u0011EG1\u001cCA\u0002\u0011M\u0007\u0002\u0003Cs\t\u000f$\t\u0001b:\u0002#\u0011B\u0017m\u001d5%G>dwN\u001c\u0013d_2|g\u000e\u0006\u0003\u0005V\u0012%\b\u0002\u0003Cv\tG\u0004\r\u0001b6\u0002\u0005!$\u0007\u0002\u0003Cx\t\u000f$\t\u0001\"=\u0002/\u0011B\u0017m\u001d5%G>dwN\u001c\u0013d_2|g\u000eJ2pY>tG\u0003\u0002Ck\tgD\u0001\u0002\">\u0005n\u0002\u0007AQ[\u0001\u0007aJ,g-\u001b=\t\u0011\u0011eH1\u0001C\u0002\tw\f1bY8og^\u0013\u0018\r\u001d9feV!AQ`C\u0002)\u0011!y0\"\u0002\u0011\r\u0011mAqYC\u0001!\r\u0001R1\u0001\u0003\u0007%\u0011](\u0019A\n\t\u0013\u0015\u001dAq\u001fCA\u0002\u0015%\u0011AB:ue\u0016\fW\u000e\u0005\u0003\u0016!\u0016-\u0001\u0003\u0002\u000f\u0001\u000b\u00039\u0001\u0002\":\u0005\u0004!\u0005Qq\u0002\t\u0005\t7)\tB\u0002\u0005\u0006\u0014\u0011\r\u0001\u0012AC\u000b\u0005E!\u0003.Y:iI\r|Gn\u001c8%G>dwN\\\n\u0004\u000b#9\bb\u0002\u0018\u0006\u0012\u0011\u0005Q\u0011\u0004\u000b\u0003\u000b\u001fA\u0001\"\"\b\u0006\u0012\u0011\u0005QqD\u0001\bk:\f\u0007\u000f\u001d7z+\u0011)\t#\"\f\u0015\t\u0015\rR\u0011\u0007\t\u0006+\u0015\u0015R\u0011F\u0005\u0004\u000bO1!AB(qi&|g\u000eE\u0004\u0016\u0005\u0003,Y#b\f\u0011\u0007A)i\u0003\u0002\u0004\u0013\u000b7\u0011\ra\u0005\t\u00059\u0001)Y\u0003\u0003\u0005\u0005@\u0016m\u0001\u0019AC\u0018\u000f!))\u0004b\u0001\t\u0002\u0015]\u0012\u0001B2p]N\u0004B\u0001b\u0007\u0006:\u0019AQ1\bC\u0002\u0011\u0003)iD\u0001\u0003d_:\u001c8cAC\u001do\"9a&\"\u000f\u0005\u0002\u0015\u0005CCAC\u001c\u0011!!\t,\"\u000f\u0005\u0002\u0015\u0015S\u0003BC$\u000b7#b!\"\u0013\u0006\u001e\u0016}\u0005C\u0002C\u000e\u000b\u0017*IJB\u0004\u0006N\u0011\r!!b\u0014\u0003\t\r{gn]\u000b\u0005\u000b#*9f\u0005\u0003\u0006L\u0015M\u0003\u0003\u0002\u000f\u0001\u000b+\u00022\u0001EC,\t\u001d\u0011R1\nCC\u0002MA1\u0002b;\u0006L\t\u0005\t\u0015!\u0003\u0006V!YA\u0011[C&\u0005\u0003%\u000b\u0011BC/!\u0011)\u0002+b\u0015\t\u000f9*Y\u0005\"\u0001\u0006bQ1Q1MC3\u000bO\u0002b\u0001b\u0007\u0006L\u0015U\u0003\u0002\u0003Cv\u000b?\u0002\r!\"\u0016\t\u0013\u0011EWq\fCA\u0002\u0015u\u0003BB\u001c\u0006L\u0011\u0005\u0003\bC\u0004>\u000b\u0017\"\t%\"\u001c\u0016\u0005\u0015U\u0003\u0002DC9\u000b\u0017\u0002\r\u0011!Q!\n\u0015M\u0013!\u0002;m-\u0006d\u0007\u0006BC8\u000bk\u00022!FC<\u0013\r)IH\u0002\u0002\tm>d\u0017\r^5mK\"IQQPC&A\u0003&QqP\u0001\u0006i2<UM\u001c\t\u0006+\u0015\u0005U1K\u0005\u0004\u000b\u00073!!\u0003$v]\u000e$\u0018n\u001c81Q\u0011)Y(\"\u001e\t\r\r+Y\u0005\"\u00019\u0011\u001d\u0001U1\nC!\u000b\u0017+\"!b\u0015)\u0011\u0015-SqRCK\u000b/\u00032!FCI\u0013\r)\u0019J\u0002\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0002b>S\r,1o\u001e(\u0012\t\u0004!\u0015mEA\u0002\n\u0006D\t\u00071\u0003\u0003\u0005\u0005l\u0016\r\u0003\u0019ACM\u0011%!\t.b\u0011\u0005\u0002\u0004)\t\u000b\u0005\u0003\u0016!\u0016\r\u0006\u0003\u0002\u000f\u0001\u000b3C\u0001\"\"\b\u0006:\u0011\u0005QqU\u000b\u0005\u000bS+\t\f\u0006\u0003\u0006,\u0016U\u0006#B\u000b\u0006&\u00155\u0006cB\u000b\u0003B\u0016=V1\u0017\t\u0004!\u0015EFA\u0002\n\u0006&\n\u00071\u0003\u0005\u0003\u001d\u0001\u0015=\u0006\u0002\u0003C`\u000bK\u0003\r!b-\t\u0011\u0015eF1\u0001C\u0001\u000bw\u000bq!\u001b;fe\u0006$X-\u0006\u0003\u0006>\u0016\u0015G\u0003BC`\u000b\u0017$B!\"1\u0006HB!A\u0004ACb!\r\u0001RQ\u0019\u0003\u0007%\u0015]&\u0019A\n\t\u0011\u0005-Vq\u0017a\u0001\u000b\u0013\u0004r!FAX\u000b\u0007,\u0019\r\u0003\u0005\u0004\u001c\u0015]\u0006\u0019ACb\u0011!)I\fb\u0001\u0005B\u0015=W\u0003BCi\u000b3$b!b5\u0006`\u0016\u0005H\u0003BCk\u000b7\u0004B\u0001\b\u0001\u0006XB\u0019\u0001#\"7\u0005\rI)iM1\u0001\u0014\u0011!\tY+\"4A\u0002\u0015u\u0007cB\u000b\u00020\u0016]Wq\u001b\u0005\t\u00077)i\r1\u0001\u0006X\"91\u0011VCg\u0001\u0004Y\u0007\u0002CB2\t\u0007!\t!\":\u0015\r\u0015\u001dX\u0011^Cv!\ra\u0002a\u001b\u0005\b\u00077)\u0019\u000f1\u0001l\u0011\u001d)i/b9A\u0002-\fAa\u001d;fa\"A11\rC\u0002\t\u0003)\t\u0010\u0006\u0003\u0006h\u0016M\bbBB\u000e\u000b_\u0004\ra\u001b\u0005\t\u000bo$\u0019\u0001\"\u0001\u0006z\u0006Y1m\u001c8uS:,\u0018\r\u001c7z+\u0011)YP\"\u0001\u0015\t\u0015uh1\u0001\t\u00059\u0001)y\u0010E\u0002\u0011\r\u0003!aAEC{\u0005\u0004\u0019\u0002\"CA6\u000bk$\t\u0019\u0001D\u0003!\u0011)\u0002+b@\t\u0011\u0019%A1\u0001C!\r\u0017\tAAZ5mYV!aQ\u0002D\u000b)\u00111yAb\u0007\u0015\t\u0019Eaq\u0003\t\u00059\u00011\u0019\u0002E\u0002\u0011\r+!aA\u0005D\u0004\u0005\u0004\u0019\u0002\"CA6\r\u000f!\t\u0019\u0001D\r!\u0011)\u0002Kb\u0005\t\u000f\r\u001dcq\u0001a\u0001W\"Aaq\u0004C\u0002\t\u00032\t#\u0001\u0005uC\n,H.\u0019;f+\u00111\u0019Cb\u000b\u0015\t\u0019\u0015b\u0011\u0007\u000b\u0005\rO1i\u0003\u0005\u0003\u001d\u0001\u0019%\u0002c\u0001\t\u0007,\u00111!C\"\bC\u0002MA\u0001\"a+\u0007\u001e\u0001\u0007aq\u0006\t\u0007+\u0005=6N\"\u000b\t\u000f\r\u001dcQ\u0004a\u0001W\"AaQ\u0007C\u0002\t\u000329$A\u0003sC:<W-\u0006\u0003\u0007:\u0019\u0005C\u0003\u0003D\u001e\r/2IFb\u0017\u0015\t\u0019ubQ\t\t\u00059\u00011y\u0004E\u0002\u0011\r\u0003\"qAb\u0011\u00074\t\u00071CA\u0001U\u0011)19Eb\r\u0002\u0002\u0003\u000fa\u0011J\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004C\u0002D&\r#2yDD\u0002\u0016\r\u001bJ1Ab\u0014\u0007\u0003\u001d\u0001\u0018mY6bO\u0016LAAb\u0015\u0007V\tA\u0011J\u001c;fOJ\fGNC\u0002\u0007P\u0019A\u0001ba\u0007\u00074\u0001\u0007aq\b\u0005\t\u0007C1\u0019\u00041\u0001\u0007@!AQQ\u001eD\u001a\u0001\u00041y\u0004C\u0005\u0007`\u0011\rA\u0011\u0001\u0002\u0007b\u0005aa-\u001b7uKJ,G\rV1jYV!a1\rD5)\u00191)Gb\u001b\u0007pA1A1DC&\rO\u00022\u0001\u0005D5\t\u0019\u0011bQ\fb\u0001'!AQq\u0001D/\u0001\u00041i\u0007\u0005\u0003\u001d\u0001\u0019\u001d\u0004\u0002CA|\r;\u0002\rA\"\u001d\u0011\rU\tyKb\u001a:\u0011%1)\bb\u0001\u0005\u0002\t19(A\u0007d_2dWm\u0019;fIR\u000b\u0017\u000e\\\u000b\t\rs2IIb \u0007\u0016RQa1\u0010DA\r\u00073YIb$\u0011\r\u0011mQ1\nD?!\r\u0001bq\u0010\u0003\u0007\u0017\u001aM$\u0019A\n\t\u000fu2\u0019\b1\u0001\u0007~!AQq\u0001D:\u0001\u00041)\t\u0005\u0003\u001d\u0001\u0019\u001d\u0005c\u0001\t\u0007\n\u00121!Cb\u001dC\u0002MA\u0001\"a3\u0007t\u0001\u0007aQ\u0012\t\b+\u0005=gq\u0011D?\u0011!\tIBb\u001dA\u0002\u0019E\u0005#\u0003\u0011\u0002\u001e\u0019\u0015eQ\u0010DJ!\r\u0001bQ\u0013\u0003\u0007i\u001aM$\u0019A\n\t\u0015\u0011]E1AA\u0001\n\u0013!I\n")
public abstract class Stream<A>
extends AbstractSeq<A>
implements LinearSeq<A>,
LinearSeqOptimized<A, Stream<A>>,
Serializable {
    public static <T> Stream<T> range(T t, T t2, T t3, Integral<T> integral) {
        return Stream$.MODULE$.range((Object)t, (Object)t2, (Object)t3, (Integral)integral);
    }

    public static <A> Stream<A> tabulate(int n, Function1<Object, A> function1) {
        return Stream$.MODULE$.tabulate(n, (Function1)function1);
    }

    public static <A> Stream<A> fill(int n, Function0<A> function0) {
        return Stream$.MODULE$.fill(n, (Function0)function0);
    }

    public static <A> Stream<A> continually(Function0<A> function0) {
        return Stream$.MODULE$.continually(function0);
    }

    public static Stream<Object> from(int n) {
        return Stream$.MODULE$.from(n);
    }

    public static Stream<Object> from(int n, int n2) {
        return Stream$.MODULE$.from(n, n2);
    }

    public static <A> Stream<A> iterate(A a, int n, Function1<A, A> function1) {
        return Stream$.MODULE$.iterate((Object)a, n, (Function1)function1);
    }

    public static <A> Stream<A> iterate(A a, Function1<A, A> function1) {
        return Stream$.MODULE$.iterate(a, function1);
    }

    public static <A> ConsWrapper<A> consWrapper(Function0<Stream<A>> function0) {
        return Stream$.MODULE$.consWrapper(function0);
    }

    public static <A> Stream<A> empty() {
        return Stream$.MODULE$.empty();
    }

    public static <A> CanBuildFrom<Stream<?>, A, Stream<A>> canBuildFrom() {
        return Stream$.MODULE$.canBuildFrom();
    }

    public static Some unapplySeq(Seq seq) {
        return Stream$.MODULE$.unapplySeq(seq);
    }

    public static GenTraversable iterate(Object object, int n, Function1 function1) {
        return Stream$.MODULE$.iterate(object, n, function1);
    }

    public static GenTraversable range(Object object, Object object2, Object object3, Integral integral) {
        return Stream$.MODULE$.range(object, object2, object3, integral);
    }

    public static GenTraversable range(Object object, Object object2, Integral integral) {
        return Stream$.MODULE$.range(object, object2, integral);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, int n5, Function5 function5) {
        return Stream$.MODULE$.tabulate(n, n2, n3, n4, n5, function5);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, Function4 function4) {
        return Stream$.MODULE$.tabulate(n, n2, n3, n4, function4);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, Function3 function3) {
        return Stream$.MODULE$.tabulate(n, n2, n3, function3);
    }

    public static GenTraversable tabulate(int n, int n2, Function2 function2) {
        return Stream$.MODULE$.tabulate(n, n2, function2);
    }

    public static GenTraversable tabulate(int n, Function1 function1) {
        return Stream$.MODULE$.tabulate(n, function1);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, int n5, Function0 function0) {
        return Stream$.MODULE$.fill(n, n2, n3, n4, n5, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, Function0 function0) {
        return Stream$.MODULE$.fill(n, n2, n3, n4, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, Function0 function0) {
        return Stream$.MODULE$.fill(n, n2, n3, function0);
    }

    public static GenTraversable fill(int n, int n2, Function0 function0) {
        return Stream$.MODULE$.fill(n, n2, function0);
    }

    public static GenTraversable fill(int n, Function0 function0) {
        return Stream$.MODULE$.fill(n, function0);
    }

    public static GenTraversable concat(Seq seq) {
        return Stream$.MODULE$.concat(seq);
    }

    public static GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return Stream$.MODULE$.ReusableCBF();
    }

    public static GenTraversable empty() {
        return Stream$.MODULE$.empty();
    }

    @Override
    public /* synthetic */ boolean scala$collection$LinearSeqOptimized$$super$sameElements(GenIterable that) {
        return IterableLike$class.sameElements(this, that);
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
    public <B> B foldRight(B z, Function2<A, B, B> op) {
        return (B)LinearSeqOptimized$class.foldRight(this, z, op);
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
    public Tuple2<Stream<A>, Stream<A>> span(Function1<A, Object> p) {
        return LinearSeqOptimized$class.span(this, p);
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
    public final <B> boolean corresponds(GenSeq<B> that, Function2<A, B, Object> p) {
        return LinearSeqLike$class.corresponds(this, that, p);
    }

    @Override
    public scala.collection.immutable.Seq<A> toSeq() {
        return scala.collection.immutable.Seq$class.toSeq(this);
    }

    @Override
    public Combiner<A, scala.collection.parallel.immutable.ParSeq<A>> parCombiner() {
        return scala.collection.immutable.Seq$class.parCombiner(this);
    }

    @Override
    public GenericCompanion<Stream> companion() {
        return Stream$.MODULE$;
    }

    @Override
    public abstract boolean isEmpty();

    @Override
    public abstract A head();

    public abstract boolean tailDefined();

    public <B> Stream<B> append(Function0<TraversableOnce<B>> rest) {
        Cons<A> cons;
        if (this.isEmpty()) {
            cons = ((GenTraversableOnce)rest.apply()).toStream();
        } else {
            Serializable serializable = new Serializable(this, rest){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Stream $outer;
                private final Function0 rest$1;

                public final Stream<B> apply() {
                    return ((Stream)this.$outer.tail()).append(this.rest$1);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.rest$1 = rest$1;
                }
            };
            A a = this.head();
            Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
            cons = new Cons<A>(a, serializable);
        }
        return cons;
    }

    public Stream<A> force() {
        Stream these = this;
        Stream those = this;
        if (!this.isEmpty()) {
            these = (Stream)this.tail();
        }
        while (those != these) {
            if (these.isEmpty()) {
                return this;
            }
            if ((these = (Stream)these.tail()).isEmpty()) {
                return this;
            }
            if ((these = (Stream)these.tail()) == those) {
                return this;
            }
            those = (Stream)those.tail();
        }
        return this;
    }

    public void print() {
        this.print(", ");
    }

    public void print(String sep) {
        this.loop$1(this, "", sep);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public int length() {
        boolean len = false;
        Stream left = this;
        void var1_1;
        while (!left.isEmpty()) {
            Stream stream;
            ++var1_1;
            stream = (Stream)stream.tail();
        }
        return (int)var1_1;
    }

    public <That> That scala$collection$immutable$Stream$$asThat(Object x) {
        return (That)x;
    }

    public <B> Stream<B> scala$collection$immutable$Stream$$asStream(Object x) {
        return (Stream)x;
    }

    public <B, That> boolean scala$collection$immutable$Stream$$isStreamBuilder(CanBuildFrom<Stream<A>, B, That> bf) {
        return bf.apply((Stream<A>)this.repr()) instanceof StreamBuilder;
    }

    @Override
    public Stream<A> toStream() {
        return this;
    }

    @Override
    public boolean hasDefiniteSize() {
        boolean bl;
        if (this.isEmpty()) {
            bl = true;
        } else {
            if (this.tailDefined()) {
                Stream these = (Stream)this.tail();
                for (Stream those = this; those != these; those = (Stream)those.tail()) {
                    if (these.isEmpty()) {
                        return true;
                    }
                    if (these.tailDefined()) {
                        if ((these = (Stream)these.tail()).isEmpty()) {
                            return true;
                        }
                        if (these.tailDefined()) {
                            if (those != (these = (Stream)these.tail())) continue;
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
            }
            bl = false;
        }
        return bl;
    }

    @Override
    public <B, That> That $plus$plus(GenTraversableOnce<B> that, CanBuildFrom<Stream<A>, B, That> bf) {
        Cons<A> cons;
        if (bf.apply((Stream<A>)this.repr()) instanceof StreamBuilder) {
            if (this.isEmpty()) {
                cons = that.toStream();
            } else {
                Serializable serializable = new Serializable(this, that){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Stream $outer;
                    private final GenTraversableOnce that$1;

                    public final Stream<A> apply() {
                        return ((Stream)this.$outer.tail()).$plus$plus(this.that$1, Stream$.MODULE$.canBuildFrom());
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.that$1 = that$1;
                    }
                };
                A a = this.head();
                Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
                cons = new Cons<A>(a, serializable);
            }
        } else {
            cons = TraversableLike$class.$plus$plus(this, that, bf);
        }
        return (That)cons;
    }

    @Override
    public <B, That> That $plus$colon(B elem, CanBuildFrom<Stream<A>, B, That> bf) {
        Object object;
        if (bf.apply((Stream<A>)this.repr()) instanceof StreamBuilder) {
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Stream $outer;

                public final Stream<A> apply() {
                    return this.$outer;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
            Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
            object = new Cons<B>(elem, serializable);
        } else {
            object = SeqLike$class.$plus$colon(this, elem, bf);
        }
        return (That)object;
    }

    @Override
    public final <B, That> That scanLeft(B z, Function2<B, A, B> op, CanBuildFrom<Stream<A>, B, That> bf) {
        Stream stream;
        if (bf.apply((Stream<A>)this.repr()) instanceof StreamBuilder) {
            if (this.isEmpty()) {
                WrappedArray wrappedArray = Predef$.MODULE$.genericWrapArray(new Object[]{z});
                Stream$ stream$ = Stream$.MODULE$;
                stream = wrappedArray.toStream();
            } else {
                Serializable serializable = new Serializable(this, z, op){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Stream $outer;
                    private final Object z$1;
                    private final Function2 op$1;

                    public final Stream<B> apply() {
                        return ((Stream)this.$outer.tail()).scanLeft(this.op$1.apply(this.z$1, this.$outer.head()), this.op$1, Stream$.MODULE$.canBuildFrom());
                    }
                    {
                        void var3_3;
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.z$1 = z$1;
                        this.op$1 = var3_3;
                    }
                };
                Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
                stream = new Cons<B>(z, serializable);
            }
        } else {
            stream = TraversableLike$class.scanLeft(this, z, op, bf);
        }
        return (That)stream;
    }

    @Override
    public final <B, That> That map(Function1<A, B> f, CanBuildFrom<Stream<A>, B, That> bf) {
        Object object;
        if (bf.apply((Stream<A>)this.repr()) instanceof StreamBuilder) {
            if (this.isEmpty()) {
                object = Stream$Empty$.MODULE$;
            } else {
                Serializable serializable = new Serializable(this, f){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Stream $outer;
                    private final Function1 f$1;

                    public final Stream<B> apply() {
                        CanBuildFrom<Stream<?>, A, Stream<A>> canBuildFrom = Stream$.MODULE$.canBuildFrom();
                        Function1 function1 = this.f$1;
                        Stream stream = (Stream)this.$outer.tail();
                        return (Stream)(canBuildFrom.apply((Stream<?>)stream.repr()) instanceof StreamBuilder ? (stream.isEmpty() ? Stream$Empty$.MODULE$ : Stream$cons$.MODULE$.apply(function1.apply(stream.head()), new /* invalid duplicate definition of identical inner class */)) : TraversableLike$class.map(stream, function1, canBuildFrom));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.f$1 = f$1;
                    }
                };
                B b = f.apply(this.head());
                Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
                object = new Cons<B>(b, serializable);
            }
        } else {
            object = TraversableLike$class.map(this, f, bf);
        }
        return (That)object;
    }

    @Override
    public final <B, That> That collect(PartialFunction<A, B> pf, CanBuildFrom<Stream<A>, B, That> bf) {
        Stream stream;
        if (bf.apply((Stream<A>)this.repr()) instanceof StreamBuilder) {
            Stream rest = this;
            ObjectRef<Object> newHead = ObjectRef.create(null);
            Function1<A, Object> runWith2 = pf.runWith(new Serializable(this, newHead){
                public static final long serialVersionUID = 0L;
                private final ObjectRef newHead$1;

                public final void apply(B b) {
                    this.newHead$1.elem = b;
                }
                {
                    this.newHead$1 = newHead$1;
                }
            });
            while (rest.nonEmpty() && !BoxesRunTime.unboxToBoolean(runWith2.apply(rest.head()))) {
                rest = (Stream)rest.tail();
            }
            stream = rest.isEmpty() ? Stream$Empty$.MODULE$ : Stream$.MODULE$.collectedTail(newHead.elem, rest, pf, bf);
        } else {
            stream = TraversableLike$class.collect(this, pf, bf);
        }
        return (That)stream;
    }

    @Override
    public final <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> f, CanBuildFrom<Stream<A>, B, That> bf) {
        GenTraversable<Object> genTraversable;
        if (bf.apply((Stream<A>)this.repr()) instanceof StreamBuilder) {
            if (this.isEmpty()) {
                genTraversable = Stream$Empty$.MODULE$;
            } else {
                ObjectRef<Stream> nonEmptyPrefix = ObjectRef.create(this);
                Stream<B> prefix = f.apply(((Stream)nonEmptyPrefix.elem).head()).toStream();
                while (!((Stream)nonEmptyPrefix.elem).isEmpty() && prefix.isEmpty()) {
                    nonEmptyPrefix.elem = (Stream)((Stream)nonEmptyPrefix.elem).tail();
                    if (((Stream)nonEmptyPrefix.elem).isEmpty()) continue;
                    prefix = f.apply(((Stream)nonEmptyPrefix.elem).head()).toStream();
                }
                genTraversable = ((Stream)nonEmptyPrefix.elem).isEmpty() ? Stream$.MODULE$.empty() : prefix.append((Function0<TraversableOnce<B>>)((Object)new Serializable(this, f, nonEmptyPrefix){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Stream $outer;
                    private final Function1 f$2;
                    private final ObjectRef nonEmptyPrefix$1;

                    public final Stream<B> apply() {
                        GenTraversable<Object> genTraversable;
                        CanBuildFrom<Stream<?>, A, Stream<A>> canBuildFrom = Stream$.MODULE$.canBuildFrom();
                        Function1 function1 = this.f$2;
                        Stream stream = (Stream)((Stream)this.nonEmptyPrefix$1.elem).tail();
                        if (canBuildFrom.apply((Stream<?>)stream.repr()) instanceof StreamBuilder) {
                            if (stream.isEmpty()) {
                                genTraversable = Stream$Empty$.MODULE$;
                            } else {
                                ObjectRef<Stream> nonEmptyPrefix1 = ObjectRef.create(stream);
                                Stream<A> prefix1 = ((GenTraversableOnce)function1.apply(((Stream)nonEmptyPrefix1.elem).head())).toStream();
                                while (!((Stream)nonEmptyPrefix1.elem).isEmpty() && prefix1.isEmpty()) {
                                    nonEmptyPrefix1.elem = (Stream)((Stream)nonEmptyPrefix1.elem).tail();
                                    if (((Stream)nonEmptyPrefix1.elem).isEmpty()) continue;
                                    prefix1 = ((GenTraversableOnce)function1.apply(((Stream)nonEmptyPrefix1.elem).head())).toStream();
                                }
                                genTraversable = ((Stream)nonEmptyPrefix1.elem).isEmpty() ? Stream$.MODULE$.empty() : prefix1.append(new /* invalid duplicate definition of identical inner class */);
                            }
                        } else {
                            genTraversable = TraversableLike$class.flatMap(stream, function1, canBuildFrom);
                        }
                        return genTraversable;
                    }
                    {
                        void var3_3;
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.f$2 = f$2;
                        this.nonEmptyPrefix$1 = var3_3;
                    }
                }));
            }
        } else {
            genTraversable = TraversableLike$class.flatMap(this, f, bf);
        }
        return (That)genTraversable;
    }

    @Override
    public Stream<A> filter(Function1<A, Object> p) {
        Stream rest = this;
        while (!rest.isEmpty() && !BoxesRunTime.unboxToBoolean(p.apply(rest.head()))) {
            rest = (Stream)rest.tail();
        }
        return rest.nonEmpty() ? Stream$.MODULE$.filteredTail(rest, p) : Stream$Empty$.MODULE$;
    }

    public final StreamWithFilter withFilter(Function1<A, Object> p) {
        return new StreamWithFilter(this, p);
    }

    @Override
    public Iterator<A> iterator() {
        return new StreamIterator(this);
    }

    @Override
    public final <U> void foreach(Function1<A, U> f) {
        while (true) {
            if (this_.isEmpty()) {
                return;
            }
            f.apply(this_.head());
            Stream this_ = (Stream)this_.tail();
        }
    }

    @Override
    public final <B> B foldLeft(B z, Function2<B, A, B> op) {
        while (!this_.isEmpty()) {
            z = op.apply(z, this_.head());
            Stream this_ = (Stream)this_.tail();
        }
        return z;
    }

    @Override
    public final <B> B reduceLeft(Function2<B, A, B> f) {
        if (this.isEmpty()) {
            throw new UnsupportedOperationException("empty.reduceLeft");
        }
        A reducedRes = this.head();
        Stream left = (Stream)this.tail();
        B b;
        while (!left.isEmpty()) {
            Stream stream;
            b = f.apply(b, stream.head());
            stream = (Stream)stream.tail();
        }
        return b;
    }

    @Override
    public Tuple2<Stream<A>, Stream<A>> partition(Function1<A, Object> p) {
        return new Tuple2<Object, Object>(this.filter((Function1)((Object)new Serializable(this, p){
            public static final long serialVersionUID = 0L;
            private final Function1 p$2;

            public final boolean apply(A x$1) {
                return BoxesRunTime.unboxToBoolean(this.p$2.apply(x$1));
            }
            {
                this.p$2 = p$2;
            }
        })), this.filterNot((Function1)((Object)new Serializable(this, p){
            public static final long serialVersionUID = 0L;
            private final Function1 p$2;

            public final boolean apply(A x$2) {
                return BoxesRunTime.unboxToBoolean(this.p$2.apply(x$2));
            }
            {
                this.p$2 = p$2;
            }
        })));
    }

    @Override
    public final <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<Stream<A>, Tuple2<A1, B>, That> bf) {
        Object object;
        if (bf.apply((Stream<A>)this.repr()) instanceof StreamBuilder) {
            if (this.isEmpty() || that.isEmpty()) {
                object = Stream$Empty$.MODULE$;
            } else {
                Serializable serializable = new Serializable(this, that){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Stream $outer;
                    private final GenIterable that$2;

                    public final Stream<Tuple2<A1, B>> apply() {
                        return ((Stream)this.$outer.tail()).zip((GenIterable)this.that$2.tail(), Stream$.MODULE$.canBuildFrom());
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.that$2 = that$2;
                    }
                };
                Tuple2 tuple2 = new Tuple2(this.head(), that.head());
                Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
                object = new Cons(tuple2, serializable);
            }
        } else {
            object = IterableLike$class.zip(this, that, bf);
        }
        return (That)object;
    }

    @Override
    public <A1, That> That zipWithIndex(CanBuildFrom<Stream<A>, Tuple2<A1, Object>, That> bf) {
        return this.zip((GenIterable<B>)Stream$.MODULE$.from(0), (CanBuildFrom)bf);
    }

    @Override
    public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
        java.io.Serializable serializable;
        b.append(start);
        if (this.isEmpty()) {
            serializable = BoxedUnit.UNIT;
        } else {
            java.io.Serializable serializable2;
            b.append(this.head());
            Stream cursor = this;
            if (this.tailDefined()) {
                Stream scout = (Stream)this.tail();
                if (scout.isEmpty()) {
                    b.append(end);
                    return b;
                }
                if (this != scout) {
                    cursor = scout;
                    if (scout.tailDefined()) {
                        scout = (Stream)scout.tail();
                        while (cursor != scout && scout.tailDefined()) {
                            b.append(sep).append(cursor.head());
                            cursor = (Stream)cursor.tail();
                            if (!(scout = (Stream)scout.tail()).tailDefined()) continue;
                            scout = (Stream)scout.tail();
                        }
                    }
                }
                if (scout.tailDefined()) {
                    Stream runner = this;
                    int k = 0;
                    while (runner != scout) {
                        runner = (Stream)runner.tail();
                        scout = (Stream)scout.tail();
                        ++k;
                    }
                    if (cursor == scout && k > 0) {
                        b.append(sep).append(cursor.head());
                        cursor = (Stream)cursor.tail();
                    }
                    while (cursor != scout) {
                        b.append(sep).append(cursor.head());
                        cursor = (Stream)cursor.tail();
                    }
                    serializable2 = BoxedUnit.UNIT;
                } else {
                    while (cursor != scout) {
                        b.append(sep).append(cursor.head());
                        cursor = (Stream)cursor.tail();
                    }
                    serializable2 = cursor.nonEmpty() ? b.append(sep).append(cursor.head()) : BoxedUnit.UNIT;
                }
            } else {
                serializable2 = BoxedUnit.UNIT;
            }
            serializable = cursor.isEmpty() ? BoxedUnit.UNIT : (cursor.tailDefined() ? b.append(sep).append("...") : b.append(sep).append("?"));
        }
        b.append(end);
        return b;
    }

    @Override
    public String mkString(String sep) {
        return this.mkString("", sep, "");
    }

    @Override
    public String mkString() {
        return this.mkString("");
    }

    @Override
    public String mkString(String start, String sep, String end) {
        this.force();
        return TraversableOnce$class.mkString(this, start, sep, end);
    }

    @Override
    public String toString() {
        return TraversableOnce$class.mkString(this, new StringBuilder().append((Object)this.stringPrefix()).append((Object)"(").toString(), ", ", ")");
    }

    @Override
    public Tuple2<Stream<A>, Stream<A>> splitAt(int n) {
        return new Tuple2<Object, Object>(this.take(n), this.drop(n));
    }

    @Override
    public Stream<A> take(int n) {
        Stream stream;
        if (n <= 0 || this.isEmpty()) {
            Stream$ stream$ = Stream$.MODULE$;
            stream = Stream$Empty$.MODULE$;
        } else if (n == 1) {
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final Stream<Nothing$> apply() {
                    return Stream$.MODULE$.empty();
                }
            };
            A a = this.head();
            Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
            stream = new Cons<Nothing$>((Nothing$)a, (Function0<Stream<Nothing$>>)((Object)serializable));
        } else {
            Serializable serializable = new Serializable(this, n){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Stream $outer;
                private final int n$1;

                public final Stream<A> apply() {
                    return ((Stream)this.$outer.tail()).take(this.n$1 - 1);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.n$1 = n$1;
                }
            };
            A a = this.head();
            Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
            stream = new Cons<A>(a, serializable);
        }
        return stream;
    }

    @Override
    public final Stream<A> drop(int n) {
        while (n > 0 && !this_.isEmpty()) {
            --n;
            Stream this_ = (Stream)this_.tail();
        }
        return this_;
    }

    @Override
    public Stream<A> slice(int from2, int until2) {
        Predef$ predef$ = Predef$.MODULE$;
        int lo = RichInt$.MODULE$.max$extension(from2, 0);
        return until2 <= lo || this.isEmpty() ? Stream$.MODULE$.empty() : ((Stream)this.drop(lo)).take(until2 - lo);
    }

    @Override
    public Stream<A> init() {
        Stream stream;
        if (this.isEmpty()) {
            stream = (Stream)TraversableLike$class.init(this);
        } else if (((SeqLike)this.tail()).isEmpty()) {
            stream = Stream$Empty$.MODULE$;
        } else {
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Stream $outer;

                public final Stream<A> apply() {
                    return ((Stream)this.$outer.tail()).init();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
            A a = this.head();
            Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
            stream = new Cons<A>(a, serializable);
        }
        return stream;
    }

    @Override
    public Stream<A> takeRight(int n) {
        Stream these = this;
        Object lead = this.drop(n);
        Stream stream;
        while (!((Stream)lead).isEmpty()) {
            Stream stream2;
            stream = (Stream)stream.tail();
            stream2 = (Stream)stream2.tail();
        }
        return stream;
    }

    @Override
    public Stream<A> dropRight(int n) {
        return n <= 0 ? this : this.scala$collection$immutable$Stream$$advance$1(((AbstractTraversable)this.take(n)).toList(), Nil$.MODULE$, (Stream)this.drop(n));
    }

    @Override
    public Stream<A> takeWhile(Function1<A, Object> p) {
        Stream stream;
        if (!this.isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(this.head()))) {
            Serializable serializable = new Serializable(this, p){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Stream $outer;
                private final Function1 p$3;

                public final Stream<A> apply() {
                    return ((Stream)this.$outer.tail()).takeWhile(this.p$3);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.p$3 = p$3;
                }
            };
            A a = this.head();
            Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
            stream = new Cons<A>(a, serializable);
        } else {
            stream = Stream$Empty$.MODULE$;
        }
        return stream;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public Stream<A> dropWhile(Function1<A, Object> p) {
        void var2_2;
        Stream these = this;
        while (!these.isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
            these = (Stream)these.tail();
        }
        return var2_2;
    }

    @Override
    public Stream<A> distinct() {
        return this.scala$collection$immutable$Stream$$loop$2((Set)Set$.MODULE$.apply(Nil$.MODULE$), this);
    }

    @Override
    public <B, That> That padTo(int len, B elem, CanBuildFrom<Stream<A>, B, That> bf) {
        return (That)(bf.apply((Stream<A>)this.repr()) instanceof StreamBuilder ? this.scala$collection$immutable$Stream$$loop$3(len, this, elem) : SeqLike$class.padTo(this, len, elem, bf));
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public Stream<A> reverse() {
        ObjectRef<Stream$Empty$> result2 = ObjectRef.create(Stream$Empty$.MODULE$);
        Stream these = this;
        while (!these.isEmpty()) {
            Stream stream;
            void var3_1;
            Serializable serializable = new Serializable(this, (ObjectRef)var3_1){
                public static final long serialVersionUID = 0L;
                private final ObjectRef result$1;

                public final Stream<A> apply() {
                    return (Stream)this.result$1.elem;
                }
                {
                    this.result$1 = result$1;
                }
            };
            Stream$ stream$ = Stream$.MODULE$;
            Stream r = new ConsWrapper<A>(serializable).$hash$colon$colon(stream.head());
            r.tail();
            var3_1.elem = r;
            stream = (Stream)stream.tail();
        }
        return (Stream)result2.elem;
    }

    @Override
    public <B> Stream<B> flatten(Function1<A, GenTraversableOnce<B>> asTraversable) {
        ObjectRef<Stream> st = ObjectRef.create(this);
        while (((Stream)st.elem).nonEmpty()) {
            GenTraversableOnce<B> h = asTraversable.apply(((Stream)st.elem).head());
            if (h.isEmpty()) {
                st.elem = (Stream)((Stream)st.elem).tail();
                continue;
            }
            Stream<B> stream = h.toStream();
            Serializable serializable = new Serializable(this, asTraversable, st){
                public static final long serialVersionUID = 0L;
                private final Function1 asTraversable$1;
                private final ObjectRef st$1;

                public final Stream<B> apply() {
                    return ((Stream)((Stream)this.st$1.elem).tail()).flatten(this.asTraversable$1);
                }
                {
                    void var3_3;
                    this.asTraversable$1 = asTraversable$1;
                    this.st$1 = var3_3;
                }
            };
            Stream$ stream$ = Stream$.MODULE$;
            return new ConsWrapper<B>(serializable).$hash$colon$colon$colon(stream);
        }
        Stream$ stream$ = Stream$.MODULE$;
        return Stream$Empty$.MODULE$;
    }

    @Override
    public Object view() {
        return new StreamView<A, Stream<A>>(this){
            private Stream<A> underlying;
            private final /* synthetic */ Stream $outer;
            private volatile boolean bitmap$0;

            private Stream underlying$lzycompute() {
                $anon$1 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.underlying = (Stream)this.$outer.repr();
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.underlying;
                }
            }

            public <B, That> That force(CanBuildFrom<Stream<A>, B, That> bf) {
                return (That)StreamViewLike$class.force(this, bf);
            }

            public <B> StreamViewLike.Transformed<B> newForced(Function0<GenSeq<B>> xs) {
                return StreamViewLike$class.newForced(this, xs);
            }

            public <B> StreamViewLike.Transformed<B> newAppended(GenTraversable<B> that) {
                return StreamViewLike$class.newAppended(this, that);
            }

            public <B> StreamViewLike.Transformed<B> newMapped(Function1<A, B> f) {
                return StreamViewLike$class.newMapped(this, f);
            }

            public <B> StreamViewLike.Transformed<B> newFlatMapped(Function1<A, GenTraversableOnce<B>> f) {
                return StreamViewLike$class.newFlatMapped(this, f);
            }

            public StreamViewLike.Transformed<A> newFiltered(Function1<A, Object> p) {
                return StreamViewLike$class.newFiltered(this, p);
            }

            public StreamViewLike.Transformed<A> newSliced(SliceInterval _endpoints) {
                return StreamViewLike$class.newSliced(this, _endpoints);
            }

            public StreamViewLike.Transformed<A> newDroppedWhile(Function1<A, Object> p) {
                return StreamViewLike$class.newDroppedWhile(this, p);
            }

            public StreamViewLike.Transformed<A> newTakenWhile(Function1<A, Object> p) {
                return StreamViewLike$class.newTakenWhile(this, p);
            }

            public <B> StreamViewLike.Transformed<Tuple2<A, B>> newZipped(GenIterable<B> that) {
                return StreamViewLike$class.newZipped(this, that);
            }

            public <A1, B> StreamViewLike.Transformed<Tuple2<A1, B>> newZippedAll(GenIterable<B> that, A1 _thisElem, B _thatElem) {
                return StreamViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
            }

            public StreamViewLike.Transformed<A> newReversed() {
                return StreamViewLike$class.newReversed(this);
            }

            public <B> StreamViewLike.Transformed<B> newPatched(int _from, GenSeq<B> _patch, int _replaced) {
                return StreamViewLike$class.newPatched(this, _from, _patch, _replaced);
            }

            public <B> StreamViewLike.Transformed<B> newPrepended(B elem) {
                return StreamViewLike$class.newPrepended(this, elem);
            }

            public String stringPrefix() {
                return StreamViewLike$class.stringPrefix(this);
            }

            public SeqViewLike.Transformed<A> newTaken(int n) {
                return SeqViewLike$class.newTaken(this, n);
            }

            public SeqViewLike.Transformed<A> newDropped(int n) {
                return SeqViewLike$class.newDropped(this, n);
            }

            public SeqView reverse() {
                return SeqViewLike$class.reverse(this);
            }

            public <B, That> That patch(int from2, GenSeq<B> patch2, int replaced, CanBuildFrom<StreamView<A, Stream<A>>, B, That> bf) {
                return (That)SeqViewLike$class.patch(this, from2, patch2, replaced, bf);
            }

            public <B, That> That padTo(int len, B elem, CanBuildFrom<StreamView<A, Stream<A>>, B, That> bf) {
                return (That)SeqViewLike$class.padTo(this, len, elem, bf);
            }

            public <B, That> That reverseMap(Function1<A, B> f, CanBuildFrom<StreamView<A, Stream<A>>, B, That> bf) {
                return (That)SeqViewLike$class.reverseMap(this, f, bf);
            }

            public <B, That> That updated(int index, B elem, CanBuildFrom<StreamView<A, Stream<A>>, B, That> bf) {
                return (That)SeqViewLike$class.updated(this, index, elem, bf);
            }

            public <B, That> That $plus$colon(B elem, CanBuildFrom<StreamView<A, Stream<A>>, B, That> bf) {
                return (That)SeqViewLike$class.$plus$colon(this, elem, bf);
            }

            public <B, That> That $colon$plus(B elem, CanBuildFrom<StreamView<A, Stream<A>>, B, That> bf) {
                return (That)SeqViewLike$class.$colon$plus(this, elem, bf);
            }

            public <B, That> That union(GenSeq<B> that, CanBuildFrom<StreamView<A, Stream<A>>, B, That> bf) {
                return (That)SeqViewLike$class.union(this, that, bf);
            }

            public SeqView diff(GenSeq that) {
                return SeqViewLike$class.diff(this, that);
            }

            public SeqView intersect(GenSeq that) {
                return SeqViewLike$class.intersect(this, that);
            }

            public SeqView sorted(Ordering ord) {
                return SeqViewLike$class.sorted(this, ord);
            }

            public SeqView sortWith(Function2 lt) {
                return SeqViewLike$class.sortWith(this, lt);
            }

            public SeqView sortBy(Function1 f, Ordering ord) {
                return SeqViewLike$class.sortBy(this, f, ord);
            }

            public Iterator<StreamView<A, Stream<A>>> combinations(int n) {
                return SeqViewLike$class.combinations(this, n);
            }

            public Iterator<StreamView<A, Stream<A>>> permutations() {
                return SeqViewLike$class.permutations(this);
            }

            public SeqView distinct() {
                return SeqViewLike$class.distinct(this);
            }

            public IterableView drop(int n) {
                return IterableViewLike$class.drop(this, n);
            }

            public IterableView take(int n) {
                return IterableViewLike$class.take(this, n);
            }

            public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<StreamView<A, Stream<A>>, Tuple2<A1, B>, That> bf) {
                return (That)IterableViewLike$class.zip(this, that, bf);
            }

            public <A1, That> That zipWithIndex(CanBuildFrom<StreamView<A, Stream<A>>, Tuple2<A1, Object>, That> bf) {
                return (That)IterableViewLike$class.zipWithIndex(this, bf);
            }

            public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<StreamView<A, Stream<A>>, Tuple2<A1, B>, That> bf) {
                return (That)IterableViewLike$class.zipAll(this, that, thisElem, thatElem, bf);
            }

            public Iterator<StreamView<A, Stream<A>>> grouped(int size2) {
                return IterableViewLike$class.grouped(this, size2);
            }

            public Iterator<StreamView<A, Stream<A>>> sliding(int size2, int step) {
                return IterableViewLike$class.sliding(this, size2, step);
            }

            public Iterator<StreamView<A, Stream<A>>> sliding(int size2) {
                return IterableViewLike$class.sliding(this, size2);
            }

            public IterableView dropRight(int n) {
                return IterableViewLike$class.dropRight(this, n);
            }

            public IterableView takeRight(int n) {
                return IterableViewLike$class.takeRight(this, n);
            }

            public /* synthetic */ TraversableView scala$collection$TraversableViewLike$$super$tail() {
                return (TraversableView)TraversableLike$class.tail(this);
            }

            public String viewIdentifier() {
                return TraversableViewLike$class.viewIdentifier(this);
            }

            public String viewIdString() {
                return TraversableViewLike$class.viewIdString(this);
            }

            public String viewToString() {
                return TraversableViewLike$class.viewToString(this);
            }

            public Builder<A, StreamView<A, Stream<A>>> newBuilder() {
                return TraversableViewLike$class.newBuilder(this);
            }

            public <B, That> That $plus$plus(GenTraversableOnce<B> xs, CanBuildFrom<StreamView<A, Stream<A>>, B, That> bf) {
                return (That)TraversableViewLike$class.$plus$plus(this, xs, bf);
            }

            public <B, That> That map(Function1<A, B> f, CanBuildFrom<StreamView<A, Stream<A>>, B, That> bf) {
                return (That)TraversableViewLike$class.map(this, f, bf);
            }

            public <B, That> That collect(PartialFunction<A, B> pf, CanBuildFrom<StreamView<A, Stream<A>>, B, That> bf) {
                return (That)TraversableViewLike$class.collect(this, pf, bf);
            }

            public <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> f, CanBuildFrom<StreamView<A, Stream<A>>, B, That> bf) {
                return (That)TraversableViewLike$class.flatMap(this, f, bf);
            }

            public <B> TraversableViewLike.Transformed<B> flatten(Function1<A, GenTraversableOnce<B>> asTraversable) {
                return TraversableViewLike$class.flatten(this, asTraversable);
            }

            public TraversableView filter(Function1 p) {
                return TraversableViewLike$class.filter(this, p);
            }

            public TraversableView withFilter(Function1 p) {
                return TraversableViewLike$class.withFilter(this, p);
            }

            public Tuple2<StreamView<A, Stream<A>>, StreamView<A, Stream<A>>> partition(Function1<A, Object> p) {
                return TraversableViewLike$class.partition(this, p);
            }

            public TraversableView init() {
                return TraversableViewLike$class.init(this);
            }

            public TraversableView slice(int from2, int until2) {
                return TraversableViewLike$class.slice(this, from2, until2);
            }

            public TraversableView dropWhile(Function1 p) {
                return TraversableViewLike$class.dropWhile(this, p);
            }

            public TraversableView takeWhile(Function1 p) {
                return TraversableViewLike$class.takeWhile(this, p);
            }

            public Tuple2<StreamView<A, Stream<A>>, StreamView<A, Stream<A>>> span(Function1<A, Object> p) {
                return TraversableViewLike$class.span(this, p);
            }

            public Tuple2<StreamView<A, Stream<A>>, StreamView<A, Stream<A>>> splitAt(int n) {
                return TraversableViewLike$class.splitAt(this, n);
            }

            public <B, That> That scanLeft(B z, Function2<B, A, B> op, CanBuildFrom<StreamView<A, Stream<A>>, B, That> bf) {
                return (That)TraversableViewLike$class.scanLeft(this, z, op, bf);
            }

            public <B, That> That scanRight(B z, Function2<A, B, B> op, CanBuildFrom<StreamView<A, Stream<A>>, B, That> bf) {
                return (That)TraversableViewLike$class.scanRight(this, z, op, bf);
            }

            public <K> Map<K, StreamView<A, Stream<A>>> groupBy(Function1<A, K> f) {
                return TraversableViewLike$class.groupBy(this, f);
            }

            public <A1, A2> Tuple2<TraversableViewLike.Transformed<A1>, TraversableViewLike.Transformed<A2>> unzip(Function1<A, Tuple2<A1, A2>> asPair) {
                return TraversableViewLike$class.unzip(this, asPair);
            }

            public <A1, A2, A3> Tuple3<TraversableViewLike.Transformed<A1>, TraversableViewLike.Transformed<A2>, TraversableViewLike.Transformed<A3>> unzip3(Function1<A, Tuple3<A1, A2, A3>> asTriple) {
                return TraversableViewLike$class.unzip3(this, asTriple);
            }

            public TraversableView filterNot(Function1 p) {
                return TraversableViewLike$class.filterNot(this, p);
            }

            public Iterator<StreamView<A, Stream<A>>> inits() {
                return TraversableViewLike$class.inits(this);
            }

            public Iterator<StreamView<A, Stream<A>>> tails() {
                return TraversableViewLike$class.tails(this);
            }

            public TraversableView tail() {
                return TraversableViewLike$class.tail(this);
            }

            public String toString() {
                return TraversableViewLike$class.toString(this);
            }

            public Seq<A> thisSeq() {
                return ViewMkString$class.thisSeq(this);
            }

            public String mkString() {
                return ViewMkString$class.mkString(this);
            }

            public String mkString(String sep) {
                return ViewMkString$class.mkString(this, sep);
            }

            public String mkString(String start, String sep, String end) {
                return ViewMkString$class.mkString(this, start, sep, end);
            }

            public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
                return ViewMkString$class.addString(this, b, start, sep, end);
            }

            public GenericCompanion<Seq> companion() {
                return Seq$class.companion(this);
            }

            public Seq<A> seq() {
                return Seq$class.seq(this);
            }

            public Seq<A> thisCollection() {
                return SeqLike$class.thisCollection(this);
            }

            public Seq toCollection(Object repr) {
                return SeqLike$class.toCollection(this, repr);
            }

            public Combiner<A, ParSeq<A>> parCombiner() {
                return SeqLike$class.parCombiner(this);
            }

            public int lengthCompare(int len) {
                return SeqLike$class.lengthCompare(this, len);
            }

            public boolean isEmpty() {
                return SeqLike$class.isEmpty(this);
            }

            public int size() {
                return SeqLike$class.size(this);
            }

            public int segmentLength(Function1<A, Object> p, int from2) {
                return SeqLike$class.segmentLength(this, p, from2);
            }

            public int indexWhere(Function1<A, Object> p, int from2) {
                return SeqLike$class.indexWhere(this, p, from2);
            }

            public int lastIndexWhere(Function1<A, Object> p, int end) {
                return SeqLike$class.lastIndexWhere(this, p, end);
            }

            public Iterator<A> reverseIterator() {
                return SeqLike$class.reverseIterator(this);
            }

            public <B> boolean startsWith(GenSeq<B> that, int offset) {
                return SeqLike$class.startsWith(this, that, offset);
            }

            public <B> boolean endsWith(GenSeq<B> that) {
                return SeqLike$class.endsWith(this, that);
            }

            public <B> int indexOfSlice(GenSeq<B> that) {
                return SeqLike$class.indexOfSlice(this, that);
            }

            public <B> int indexOfSlice(GenSeq<B> that, int from2) {
                return SeqLike$class.indexOfSlice(this, that, from2);
            }

            public <B> int lastIndexOfSlice(GenSeq<B> that) {
                return SeqLike$class.lastIndexOfSlice(this, that);
            }

            public <B> int lastIndexOfSlice(GenSeq<B> that, int end) {
                return SeqLike$class.lastIndexOfSlice(this, that, end);
            }

            public <B> boolean containsSlice(GenSeq<B> that) {
                return SeqLike$class.containsSlice(this, that);
            }

            public <A1> boolean contains(A1 elem) {
                return SeqLike$class.contains(this, elem);
            }

            public <B> boolean corresponds(GenSeq<B> that, Function2<A, B, Object> p) {
                return SeqLike$class.corresponds(this, that, p);
            }

            public Seq<A> toSeq() {
                return SeqLike$class.toSeq(this);
            }

            public Range indices() {
                return SeqLike$class.indices(this);
            }

            public Object view() {
                return SeqLike$class.view(this);
            }

            public SeqView<A, StreamView<A, Stream<A>>> view(int from2, int until2) {
                return SeqLike$class.view(this, from2, until2);
            }

            public boolean isDefinedAt(int idx) {
                return GenSeqLike$class.isDefinedAt(this, idx);
            }

            public int prefixLength(Function1<A, Object> p) {
                return GenSeqLike$class.prefixLength(this, p);
            }

            public int indexWhere(Function1<A, Object> p) {
                return GenSeqLike$class.indexWhere(this, p);
            }

            public <B> int indexOf(B elem) {
                return GenSeqLike$class.indexOf(this, elem);
            }

            public <B> int indexOf(B elem, int from2) {
                return GenSeqLike$class.indexOf(this, elem, from2);
            }

            public <B> int lastIndexOf(B elem) {
                return GenSeqLike$class.lastIndexOf(this, elem);
            }

            public <B> int lastIndexOf(B elem, int end) {
                return GenSeqLike$class.lastIndexOf(this, elem, end);
            }

            public int lastIndexWhere(Function1<A, Object> p) {
                return GenSeqLike$class.lastIndexWhere(this, p);
            }

            public <B> boolean startsWith(GenSeq<B> that) {
                return GenSeqLike$class.startsWith(this, that);
            }

            public int hashCode() {
                return GenSeqLike$class.hashCode(this);
            }

            public boolean equals(Object that) {
                return GenSeqLike$class.equals(this, that);
            }

            public <U> void foreach(Function1<A, U> f) {
                IterableLike$class.foreach(this, f);
            }

            public boolean forall(Function1<A, Object> p) {
                return IterableLike$class.forall(this, p);
            }

            public boolean exists(Function1<A, Object> p) {
                return IterableLike$class.exists(this, p);
            }

            public Option<A> find(Function1<A, Object> p) {
                return IterableLike$class.find(this, p);
            }

            public <B> B foldRight(B z, Function2<A, B, B> op) {
                return (B)IterableLike$class.foldRight(this, z, op);
            }

            public <B> B reduceRight(Function2<A, B, B> op) {
                return (B)IterableLike$class.reduceRight(this, op);
            }

            public Iterable<A> toIterable() {
                return IterableLike$class.toIterable(this);
            }

            public Iterator<A> toIterator() {
                return IterableLike$class.toIterator(this);
            }

            public A head() {
                return (A)IterableLike$class.head(this);
            }

            public <B> void copyToArray(Object xs, int start, int len) {
                IterableLike$class.copyToArray(this, xs, start, len);
            }

            public <B> boolean sameElements(GenIterable<B> that) {
                return IterableLike$class.sameElements(this, that);
            }

            public Stream<A> toStream() {
                return IterableLike$class.toStream(this);
            }

            public boolean canEqual(Object that) {
                return IterableLike$class.canEqual(this, that);
            }

            public <B> Builder<B, Seq<B>> genericBuilder() {
                return GenericTraversableTemplate$class.genericBuilder(this);
            }

            public GenTraversable transpose(Function1 asTraversable) {
                return GenericTraversableTemplate$class.transpose(this, asTraversable);
            }

            public Object repr() {
                return TraversableLike$class.repr(this);
            }

            public final boolean isTraversableAgain() {
                return TraversableLike$class.isTraversableAgain(this);
            }

            public boolean hasDefiniteSize() {
                return TraversableLike$class.hasDefiniteSize(this);
            }

            public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<StreamView<A, Stream<A>>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<StreamView<A, Stream<A>>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<StreamView<A, Stream<A>>, B, That> cbf) {
                return (That)TraversableLike$class.scan(this, z, op, cbf);
            }

            public Option<A> headOption() {
                return TraversableLike$class.headOption(this);
            }

            public A last() {
                return (A)TraversableLike$class.last(this);
            }

            public Option<A> lastOption() {
                return TraversableLike$class.lastOption(this);
            }

            public Object sliceWithKnownDelta(int from2, int until2, int delta) {
                return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
            }

            public Object sliceWithKnownBound(int from2, int until2) {
                return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
            }

            public Traversable<A> toTraversable() {
                return TraversableLike$class.toTraversable(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, A, Col> cbf) {
                return (Col)TraversableLike$class.to(this, cbf);
            }

            public Parallel par() {
                return Parallelizable$class.par(this);
            }

            public List<A> reversed() {
                return TraversableOnce$class.reversed(this);
            }

            public boolean nonEmpty() {
                return TraversableOnce$class.nonEmpty(this);
            }

            public int count(Function1<A, Object> p) {
                return TraversableOnce$class.count(this, p);
            }

            public <B> Option<B> collectFirst(PartialFunction<A, B> pf) {
                return TraversableOnce$class.collectFirst(this, pf);
            }

            public <B> B $div$colon(B z, Function2<B, A, B> op) {
                return (B)TraversableOnce$class.$div$colon(this, z, op);
            }

            public <B> B $colon$bslash(B z, Function2<A, B, B> op) {
                return (B)TraversableOnce$class.$colon$bslash(this, z, op);
            }

            public <B> B foldLeft(B z, Function2<B, A, B> op) {
                return (B)TraversableOnce$class.foldLeft(this, z, op);
            }

            public <B> B reduceLeft(Function2<B, A, B> op) {
                return (B)TraversableOnce$class.reduceLeft(this, op);
            }

            public <B> Option<B> reduceLeftOption(Function2<B, A, B> op) {
                return TraversableOnce$class.reduceLeftOption(this, op);
            }

            public <B> Option<B> reduceRightOption(Function2<A, B, B> op) {
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

            public <B> B aggregate(Function0<B> z, Function2<B, A, B> seqop, Function2<B, B, B> combop) {
                return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
            }

            public <B> B sum(Numeric<B> num) {
                return (B)TraversableOnce$class.sum(this, num);
            }

            public <B> B product(Numeric<B> num) {
                return (B)TraversableOnce$class.product(this, num);
            }

            public <B> A min(Ordering<B> cmp) {
                return (A)TraversableOnce$class.min(this, cmp);
            }

            public <B> A max(Ordering<B> cmp) {
                return (A)TraversableOnce$class.max(this, cmp);
            }

            public <B> A maxBy(Function1<A, B> f, Ordering<B> cmp) {
                return (A)TraversableOnce$class.maxBy(this, f, cmp);
            }

            public <B> A minBy(Function1<A, B> f, Ordering<B> cmp) {
                return (A)TraversableOnce$class.minBy(this, f, cmp);
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

            public List<A> toList() {
                return TraversableOnce$class.toList(this);
            }

            public IndexedSeq<A> toIndexedSeq() {
                return TraversableOnce$class.toIndexedSeq(this);
            }

            public <B> Buffer<B> toBuffer() {
                return TraversableOnce$class.toBuffer(this);
            }

            public <B> Set<B> toSet() {
                return TraversableOnce$class.toSet(this);
            }

            public Vector<A> toVector() {
                return TraversableOnce$class.toVector(this);
            }

            public <T, U> Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> ev) {
                return TraversableOnce$class.toMap(this, ev);
            }

            public StringBuilder addString(StringBuilder b, String sep) {
                return TraversableOnce$class.addString(this, b, sep);
            }

            public StringBuilder addString(StringBuilder b) {
                return TraversableOnce$class.addString(this, b);
            }

            public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> that) {
                return PartialFunction$class.orElse(this, that);
            }

            public <C> PartialFunction<Object, C> andThen(Function1<A, C> k) {
                return PartialFunction$class.andThen(this, k);
            }

            public Function1<Object, Option<A>> lift() {
                return PartialFunction$class.lift(this);
            }

            public Object applyOrElse(Object x, Function1 function1) {
                return PartialFunction$class.applyOrElse(this, x, function1);
            }

            public <U> Function1<Object, Object> runWith(Function1<A, U> action) {
                return PartialFunction$class.runWith(this, action);
            }

            public boolean apply$mcZD$sp(double v1) {
                return Function1$class.apply$mcZD$sp(this, v1);
            }

            public double apply$mcDD$sp(double v1) {
                return Function1$class.apply$mcDD$sp(this, v1);
            }

            public float apply$mcFD$sp(double v1) {
                return Function1$class.apply$mcFD$sp(this, v1);
            }

            public int apply$mcID$sp(double v1) {
                return Function1$class.apply$mcID$sp(this, v1);
            }

            public long apply$mcJD$sp(double v1) {
                return Function1$class.apply$mcJD$sp(this, v1);
            }

            public void apply$mcVD$sp(double v1) {
                Function1$class.apply$mcVD$sp(this, v1);
            }

            public boolean apply$mcZF$sp(float v1) {
                return Function1$class.apply$mcZF$sp(this, v1);
            }

            public double apply$mcDF$sp(float v1) {
                return Function1$class.apply$mcDF$sp(this, v1);
            }

            public float apply$mcFF$sp(float v1) {
                return Function1$class.apply$mcFF$sp(this, v1);
            }

            public int apply$mcIF$sp(float v1) {
                return Function1$class.apply$mcIF$sp(this, v1);
            }

            public long apply$mcJF$sp(float v1) {
                return Function1$class.apply$mcJF$sp(this, v1);
            }

            public void apply$mcVF$sp(float v1) {
                Function1$class.apply$mcVF$sp(this, v1);
            }

            public boolean apply$mcZI$sp(int v1) {
                return Function1$class.apply$mcZI$sp(this, v1);
            }

            public double apply$mcDI$sp(int v1) {
                return Function1$class.apply$mcDI$sp(this, v1);
            }

            public float apply$mcFI$sp(int v1) {
                return Function1$class.apply$mcFI$sp(this, v1);
            }

            public int apply$mcII$sp(int v1) {
                return Function1$class.apply$mcII$sp(this, v1);
            }

            public long apply$mcJI$sp(int v1) {
                return Function1$class.apply$mcJI$sp(this, v1);
            }

            public void apply$mcVI$sp(int v1) {
                Function1$class.apply$mcVI$sp(this, v1);
            }

            public boolean apply$mcZJ$sp(long v1) {
                return Function1$class.apply$mcZJ$sp(this, v1);
            }

            public double apply$mcDJ$sp(long v1) {
                return Function1$class.apply$mcDJ$sp(this, v1);
            }

            public float apply$mcFJ$sp(long v1) {
                return Function1$class.apply$mcFJ$sp(this, v1);
            }

            public int apply$mcIJ$sp(long v1) {
                return Function1$class.apply$mcIJ$sp(this, v1);
            }

            public long apply$mcJJ$sp(long v1) {
                return Function1$class.apply$mcJJ$sp(this, v1);
            }

            public void apply$mcVJ$sp(long v1) {
                Function1$class.apply$mcVJ$sp(this, v1);
            }

            public <A> Function1<A, A> compose(Function1<A, Object> g) {
                return Function1$class.compose(this, g);
            }

            public Stream<A> underlying() {
                return this.bitmap$0 ? this.underlying : this.underlying$lzycompute();
            }

            public Iterator<A> iterator() {
                return this.$outer.iterator();
            }

            public int length() {
                return this.$outer.length();
            }

            public A apply(int idx) {
                return this.$outer.apply(idx);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
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
                scala.collection.Iterable$class.$init$(this);
                GenSeqLike$class.$init$(this);
                GenSeq$class.$init$(this);
                SeqLike$class.$init$(this);
                Seq$class.$init$(this);
                ViewMkString$class.$init$(this);
                TraversableViewLike$class.$init$(this);
                IterableViewLike$class.$init$(this);
                SeqViewLike$class.$init$(this);
                StreamViewLike$class.$init$(this);
            }
        };
    }

    @Override
    public String stringPrefix() {
        return "Stream";
    }

    private final void loop$1(Stream these, String start, String sep$1) {
        while (true) {
            Console$.MODULE$.print(start);
            if (these.isEmpty()) {
                Console$.MODULE$.print("empty");
                return;
            }
            Console$.MODULE$.print(these.head());
            start = sep$1;
            these = (Stream)these.tail();
        }
    }

    public final Stream scala$collection$immutable$Stream$$advance$1(List stub0, List stub1, Stream rest) {
        Stream stream;
        block2: {
            while (true) {
                if (rest.isEmpty()) {
                    Stream$ stream$ = Stream$.MODULE$;
                    stream = Stream$Empty$.MODULE$;
                    break block2;
                }
                if (!((List)stub0).isEmpty()) break;
                Object object = stub1.reverse();
                stub1 = Nil$.MODULE$;
                stub0 = object;
            }
            Serializable serializable = new Serializable(this, (List)stub0, stub1, rest){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Stream $outer;
                private final List stub0$1;
                private final List stub1$1;
                private final Stream rest$2;

                public final Stream<A> apply() {
                    A a = this.rest$2.head();
                    return this.$outer.scala$collection$immutable$Stream$$advance$1((List)this.stub0$1.tail(), this.stub1$1.$colon$colon(a), (Stream)this.rest$2.tail());
                }
                {
                    void var4_4;
                    void var3_3;
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.stub0$1 = stub0$1;
                    this.stub1$1 = var3_3;
                    this.rest$2 = var4_4;
                }
            };
            Object a = ((List)stub0).head();
            Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
            stream = new Cons(a, serializable);
        }
        return stream;
    }

    public final Stream scala$collection$immutable$Stream$$loop$2(Set seen, Stream rest) {
        Stream stream;
        block2: {
            while (true) {
                if (rest.isEmpty()) {
                    stream = rest;
                    break block2;
                }
                if (!seen.apply(rest.head())) break;
                rest = (Stream)rest.tail();
            }
            Serializable serializable = new Serializable(this, seen, rest){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Stream $outer;
                private final Set seen$1;
                private final Stream rest$3;

                public final Stream<A> apply() {
                    return this.$outer.scala$collection$immutable$Stream$$loop$2((Set)this.seen$1.$plus(this.rest$3.head()), (Stream)this.rest$3.tail());
                }
                {
                    void var3_3;
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.seen$1 = seen$1;
                    this.rest$3 = var3_3;
                }
            };
            A a = rest.head();
            Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
            stream = new Cons<A>(a, serializable);
        }
        return stream;
    }

    public final Stream scala$collection$immutable$Stream$$loop$3(int len, Stream these, Object elem$1) {
        Stream stream;
        if (these.isEmpty()) {
            Serializable serializable = new Serializable(this, elem$1){
                public static final long serialVersionUID = 0L;
                public final Object elem$1;

                public final B apply() {
                    return (B)this.elem$1;
                }
                {
                    this.elem$1 = elem$1;
                }
            };
            Stream$ stream$ = Stream$.MODULE$;
            if (len <= 0) {
                stream = Stream$Empty$.MODULE$;
            } else {
                Serializable serializable2 = new Serializable(len, (Function0)((Object)serializable)){
                    public static final long serialVersionUID = 0L;
                    private final int n$2;
                    private final Function0 elem$2;

                    public final Stream<A> apply() {
                        return Stream$.MODULE$.fill(this.n$2 - 1, this.elem$2);
                    }
                    {
                        this.n$2 = n$2;
                        this.elem$2 = elem$2;
                    }
                };
                Object object = serializable.elem$1;
                Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
                stream = new Cons<Object>(object, (Function0<Stream<Object>>)((Object)serializable2));
            }
        } else {
            Serializable serializable = new Serializable(this, elem$1, len, these){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Stream $outer;
                private final Object elem$1;
                private final int len$1;
                private final Stream these$1;

                public final Stream<B> apply() {
                    return this.$outer.scala$collection$immutable$Stream$$loop$3(this.len$1 - 1, (Stream)this.these$1.tail(), this.elem$1);
                }
                {
                    void var4_4;
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.elem$1 = elem$1;
                    this.len$1 = len$1;
                    this.these$1 = var4_4;
                }
            };
            A a = these.head();
            Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
            stream = new Cons<A>(a, serializable);
        }
        return stream;
    }

    public Stream() {
        scala.collection.immutable.Traversable$class.$init$(this);
        Iterable$class.$init$(this);
        scala.collection.immutable.Seq$class.$init$(this);
        LinearSeqLike$class.$init$(this);
        scala.collection.LinearSeq$class.$init$(this);
        LinearSeq$class.$init$(this);
        LinearSeqOptimized$class.$init$(this);
    }

    public static final class Cons<A>
    extends Stream<A> {
        public static final long serialVersionUID = -602202424901551803L;
        private final A hd;
        private volatile Stream<A> tlVal;
        private volatile Function0<Stream<A>> tlGen;

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public A head() {
            return this.hd;
        }

        @Override
        public boolean tailDefined() {
            return this.tlGen == null;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public Stream<A> tail() {
            BoxedUnit boxedUnit;
            if (this.tailDefined()) {
                boxedUnit = BoxedUnit.UNIT;
                return this.tlVal;
            }
            Cons cons = this;
            synchronized (cons) {
                BoxedUnit boxedUnit2;
                if (this.tailDefined()) {
                    boxedUnit2 = BoxedUnit.UNIT;
                } else {
                    this.tlVal = this.tlGen.apply();
                    this.tlGen = null;
                    boxedUnit2 = BoxedUnit.UNIT;
                }
                BoxedUnit boxedUnit3 = boxedUnit2;
                // ** MonitorExit[this] (shouldn't be in output)
                boxedUnit = boxedUnit3;
                return this.tlVal;
            }
        }

        public Cons(A hd, Function0<Stream<A>> tl) {
            this.hd = hd;
            this.tlGen = tl;
        }
    }

    public static class ConsWrapper<A> {
        private final Function0<Stream<A>> tl;

        public Stream<A> $hash$colon$colon(A hd) {
            Function0<Stream<A>> function0 = this.tl;
            Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
            return new Cons<A>(hd, function0);
        }

        public Stream<A> $hash$colon$colon$colon(Stream<A> prefix) {
            return prefix.append(this.tl);
        }

        public ConsWrapper(Function0<Stream<A>> tl) {
            this.tl = tl;
        }
    }

    public static class StreamBuilder<A>
    extends LazyBuilder<A, Stream<A>> {
        @Override
        public Stream<A> result() {
            Stream stream;
            Stream$ stream$ = Stream$.MODULE$;
            StreamCanBuildFrom streamCanBuildFrom = new StreamCanBuildFrom();
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final Stream<A> apply(TraversableOnce<A> x$5) {
                    return x$5.toStream();
                }
            };
            Stream stream2 = TraversableForwarder$class.toStream(this.parts());
            if (streamCanBuildFrom.apply(stream2.repr()) instanceof StreamBuilder) {
                if (stream2.isEmpty()) {
                    stream = Stream$Empty$.MODULE$;
                } else {
                    ObjectRef<Stream> nonEmptyPrefix1 = ObjectRef.create(stream2);
                    Stream prefix1 = ((GenTraversableOnce)((TraversableOnce)((Stream)nonEmptyPrefix1.elem).head()).toStream()).toStream();
                    while (!((Stream)nonEmptyPrefix1.elem).isEmpty() && prefix1.isEmpty()) {
                        nonEmptyPrefix1.elem = (Stream)((Stream)nonEmptyPrefix1.elem).tail();
                        if (((Stream)nonEmptyPrefix1.elem).isEmpty()) continue;
                        prefix1 = ((GenTraversableOnce)((TraversableOnce)((Stream)nonEmptyPrefix1.elem).head()).toStream()).toStream();
                    }
                    if (((Stream)nonEmptyPrefix1.elem).isEmpty()) {
                        Stream$ stream$2 = Stream$.MODULE$;
                        stream = Stream$Empty$.MODULE$;
                    } else {
                        stream = prefix1.append(new /* invalid duplicate definition of identical inner class */);
                    }
                }
            } else {
                stream = TraversableLike$class.flatMap(stream2, (Function1)((Object)serializable), streamCanBuildFrom);
            }
            return stream;
        }
    }

    public final class StreamWithFilter
    extends TraversableLike.WithFilter {
        public final Function1<A, Object> scala$collection$immutable$Stream$StreamWithFilter$$p;

        @Override
        public <B, That> That map(Function1<A, B> f, CanBuildFrom<Stream<A>, B, That> bf) {
            Object object;
            Stream stream = this.scala$collection$immutable$Stream$StreamWithFilter$$$outer();
            if (bf.apply((Stream)stream.repr()) instanceof StreamBuilder) {
                this.scala$collection$immutable$Stream$StreamWithFilter$$$outer();
                object = this.scala$collection$immutable$Stream$StreamWithFilter$$tailMap$1(this.scala$collection$immutable$Stream$StreamWithFilter$$$outer(), f);
            } else {
                object = super.map(f, bf);
            }
            return object;
        }

        @Override
        public <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> f, CanBuildFrom<Stream<A>, B, That> bf) {
            Object object;
            Stream stream = this.scala$collection$immutable$Stream$StreamWithFilter$$$outer();
            if (bf.apply((Stream)stream.repr()) instanceof StreamBuilder) {
                this.scala$collection$immutable$Stream$StreamWithFilter$$$outer();
                object = this.scala$collection$immutable$Stream$StreamWithFilter$$tailFlatMap$1(this.scala$collection$immutable$Stream$StreamWithFilter$$$outer(), f);
            } else {
                object = super.flatMap(f, bf);
            }
            return object;
        }

        @Override
        public <U> void foreach(Function1<A, U> f) {
            Serializable serializable = new Serializable(this, f){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ StreamWithFilter $outer;
                public final Function1 f$5;

                public final Object apply(A x) {
                    return BoxesRunTime.unboxToBoolean(this.$outer.scala$collection$immutable$Stream$StreamWithFilter$$p.apply(x)) ? this.f$5.apply(x) : BoxedUnit.UNIT;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.f$5 = f$5;
                }
            };
            Stream stream = (Stream)this.$outer;
            while (true) {
                if (stream.isEmpty()) {
                    return;
                }
                Object a = stream.head();
                BoxedUnit boxedUnit = BoxesRunTime.unboxToBoolean(serializable.$outer.scala$collection$immutable$Stream$StreamWithFilter$$p.apply(a)) ? serializable.f$5.apply(a) : BoxedUnit.UNIT;
                stream = (Stream)stream.tail();
            }
        }

        @Override
        public StreamWithFilter withFilter(Function1<A, Object> q) {
            return new StreamWithFilter(this.scala$collection$immutable$Stream$StreamWithFilter$$$outer(), new Serializable(this, q){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ StreamWithFilter $outer;
                private final Function1 q$1;

                public final boolean apply(A x) {
                    return BoxesRunTime.unboxToBoolean(this.$outer.scala$collection$immutable$Stream$StreamWithFilter$$p.apply(x)) && BoxesRunTime.unboxToBoolean(this.q$1.apply(x));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.q$1 = q$1;
                }
            });
        }

        public /* synthetic */ Stream scala$collection$immutable$Stream$StreamWithFilter$$$outer() {
            return (Stream)this.$outer;
        }

        public final Stream scala$collection$immutable$Stream$StreamWithFilter$$tailMap$1(Stream coll, Function1 f$3) {
            Object head2;
            ObjectRef<Stream> tail = ObjectRef.create(coll);
            do {
                if (((Stream)tail.elem).isEmpty()) {
                    return Stream$Empty$.MODULE$;
                }
                head2 = ((Stream)tail.elem).head();
                tail.elem = (Stream)((Stream)tail.elem).tail();
            } while (!BoxesRunTime.unboxToBoolean(this.scala$collection$immutable$Stream$StreamWithFilter$$p.apply(head2)));
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ StreamWithFilter $outer;
                private final Function1 f$3;
                private final ObjectRef tail$1;

                public final Stream<B> apply() {
                    return this.$outer.scala$collection$immutable$Stream$StreamWithFilter$$tailMap$1((Stream)this.tail$1.elem, this.f$3);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.f$3 = f$3;
                    this.tail$1 = tail$1;
                }
            };
            Object r = f$3.apply(head2);
            Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
            return new Cons(r, serializable);
        }

        public final Stream scala$collection$immutable$Stream$StreamWithFilter$$tailFlatMap$1(Stream coll, Function1 f$4) {
            Object head2;
            ObjectRef<Stream> tail = ObjectRef.create(coll);
            do {
                if (((Stream)tail.elem).isEmpty()) {
                    return Stream$Empty$.MODULE$;
                }
                head2 = ((Stream)tail.elem).head();
                tail.elem = (Stream)((Stream)tail.elem).tail();
            } while (!BoxesRunTime.unboxToBoolean(this.scala$collection$immutable$Stream$StreamWithFilter$$p.apply(head2)));
            return ((GenTraversableOnce)f$4.apply(head2)).toStream().append(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ StreamWithFilter $outer;
                private final Function1 f$4;
                private final ObjectRef tail$2;

                public final Stream<B> apply() {
                    return this.$outer.scala$collection$immutable$Stream$StreamWithFilter$$tailFlatMap$1((Stream)this.tail$2.elem, this.f$4);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.f$4 = f$4;
                    this.tail$2 = tail$2;
                }
            });
        }

        public StreamWithFilter(Stream<A> $outer, Function1<A, Object> p) {
            this.scala$collection$immutable$Stream$StreamWithFilter$$p = p;
            super($outer, p);
        }
    }

    public static class StreamCanBuildFrom<A>
    extends GenTraversableFactory.GenericCanBuildFrom<A> {
    }
}

