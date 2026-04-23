/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterable;
import scala.collection.AbstractTraversable;
import scala.collection.BufferedIterator;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterable$class;
import scala.collection.IterableLike$class;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.Iterator$class;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.Traversable$class;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
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
import scala.package$;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Scopes;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes$EmptyScope$;
import scala.reflect.internal.Scopes$LookupAmbiguous$;
import scala.reflect.internal.Scopes$LookupInaccessible$;
import scala.reflect.internal.Scopes$LookupNotFound$;
import scala.reflect.internal.Scopes$LookupSucceeded$;
import scala.reflect.internal.Scopes$NameLookup$class;
import scala.reflect.internal.Scopes$Scope$;
import scala.reflect.internal.Scopes$class;
import scala.reflect.internal.StdAttachments;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0015Md!C\u0001\u0003!\u0003\r\t!CC6\u0005\u0019\u00196m\u001c9fg*\u00111\u0001B\u0001\tS:$XM\u001d8bY*\u0011QAB\u0001\be\u00164G.Z2u\u0015\u00059\u0011!B:dC2\f7\u0001A\n\u0004\u0001)q\u0001CA\u0006\r\u001b\u00051\u0011BA\u0007\u0007\u0005\u0019\te.\u001f*fMB\u0011qBE\u0007\u0002!)\u0011\u0011\u0003B\u0001\u0004CBL\u0017BA\u0001\u0011\u0011\u0015!\u0002\u0001\"\u0001\u0016\u0003\u0019!\u0013N\\5uIQ\ta\u0003\u0005\u0002\f/%\u0011\u0001D\u0002\u0002\u0005+:LGOB\u0004\u001b\u0001A\u0005\u0019\u0011E\u000e\u0003\u00159\u000bW.\u001a'p_.,\bo\u0005\u0002\u001a\u0015!)A#\u0007C\u0001+!)a$\u0007D\u0001?\u000511/_7c_2,\u0012\u0001\t\t\u0003C\tj\u0011\u0001A\u0005\u0003G\u0011\u0012aaU=nE>d\u0017BA\u0013\u0003\u0005\u001d\u0019\u00160\u001c2pYNDQaJ\r\u0005\u0002!\n\u0011\"[:Tk\u000e\u001cWm]:\u0016\u0003%\u0002\"a\u0003\u0016\n\u0005-2!a\u0002\"p_2,\u0017M\\\u0015\t35\nY\"a\u0019\u0002\b\u001a!a\u0006\u0001!0\u0005=aun\\6va\u0006k'-[4v_V\u001c8#B\u0017\u000baE\"\u0004CA\u0011\u001a!\tY!'\u0003\u00024\r\t9\u0001K]8ek\u000e$\bCA\u00066\u0013\t1dA\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0003\u00059[\tU\r\u0011\"\u0001:\u0003\ri7oZ\u000b\u0002uA\u00111H\u0010\b\u0003\u0017qJ!!\u0010\u0004\u0002\rA\u0013X\rZ3g\u0013\ty\u0004I\u0001\u0004TiJLgn\u001a\u0006\u0003{\u0019A\u0001BQ\u0017\u0003\u0012\u0003\u0006IAO\u0001\u0005[N<\u0007\u0005C\u0003E[\u0011\u0005Q)\u0001\u0004=S:LGO\u0010\u000b\u0003\r\u001e\u0003\"!I\u0017\t\u000ba\u001a\u0005\u0019\u0001\u001e\t\u000byiC\u0011A%\u0016\u0003)\u0003\"!I&\n\u00051##\u0001\u0003(p'fl'm\u001c7\t\u000f9k\u0013\u0011!C\u0001\u001f\u0006!1m\u001c9z)\t1\u0005\u000bC\u00049\u001bB\u0005\t\u0019\u0001\u001e\t\u000fIk\u0013\u0013!C\u0001'\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001++\u0005i*6&\u0001,\u0011\u0005]cV\"\u0001-\u000b\u0005eS\u0016!C;oG\",7m[3e\u0015\tYf!\u0001\u0006b]:|G/\u0019;j_:L!!\u0018-\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rC\u0004`[\u0005\u0005I\u0011\t1\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005\t\u0007C\u00012h\u001b\u0005\u0019'B\u00013f\u0003\u0011a\u0017M\\4\u000b\u0003\u0019\fAA[1wC&\u0011qh\u0019\u0005\bS6\n\t\u0011\"\u0001k\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005Y\u0007CA\u0006m\u0013\tigAA\u0002J]RDqa\\\u0017\u0002\u0002\u0013\u0005\u0001/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005E$\bCA\u0006s\u0013\t\u0019hAA\u0002B]fDq!\u001e8\u0002\u0002\u0003\u00071.A\u0002yIEBqa^\u0017\u0002\u0002\u0013\u0005\u00030A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005I\bc\u0001>~c6\t1P\u0003\u0002}\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005y\\(\u0001C%uKJ\fGo\u001c:\t\u0013\u0005\u0005Q&!A\u0005\u0002\u0005\r\u0011\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007%\n)\u0001C\u0004v\u007f\u0006\u0005\t\u0019A9\t\u0013\u0005%Q&!A\u0005B\u0005-\u0011\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003-D\u0011\"a\u0004.\u0003\u0003%\t%!\u0005\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!\u0019\u0005\n\u0003+i\u0013\u0011!C!\u0003/\ta!Z9vC2\u001cHcA\u0015\u0002\u001a!AQ/a\u0005\u0002\u0002\u0003\u0007\u0011O\u0002\u0004\u0002\u001e\u0001\u0001\u0015q\u0004\u0002\u0013\u0019>|7.\u001e9J]\u0006\u001c7-Z:tS\ndWm\u0005\u0004\u0002\u001c)\u0001\u0014\u0007\u000e\u0005\n=\u0005m!Q3A\u0005\u0002}A!\"!\n\u0002\u001c\tE\t\u0015!\u0003!\u0003\u001d\u0019\u00180\u001c2pY\u0002B\u0011\u0002OA\u000e\u0005+\u0007I\u0011A\u001d\t\u0013\t\u000bYB!E!\u0002\u0013Q\u0004b\u0002#\u0002\u001c\u0011\u0005\u0011Q\u0006\u000b\u0007\u0003_\t\t$a\r\u0011\u0007\u0005\nY\u0002\u0003\u0004\u001f\u0003W\u0001\r\u0001\t\u0005\u0007q\u0005-\u0002\u0019\u0001\u001e\t\u00139\u000bY\"!A\u0005\u0002\u0005]BCBA\u0018\u0003s\tY\u0004\u0003\u0005\u001f\u0003k\u0001\n\u00111\u0001!\u0011!A\u0014Q\u0007I\u0001\u0002\u0004Q\u0004\"\u0003*\u0002\u001cE\u0005I\u0011AA +\t\t\tE\u000b\u0002!+\"I\u0011QIA\u000e#\u0003%\taU\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u0011!y\u00161DA\u0001\n\u0003\u0002\u0007\u0002C5\u0002\u001c\u0005\u0005I\u0011\u00016\t\u0013=\fY\"!A\u0005\u0002\u00055CcA9\u0002P!AQ/a\u0013\u0002\u0002\u0003\u00071\u000e\u0003\u0005x\u00037\t\t\u0011\"\u0011y\u0011)\t\t!a\u0007\u0002\u0002\u0013\u0005\u0011Q\u000b\u000b\u0004S\u0005]\u0003\u0002C;\u0002T\u0005\u0005\t\u0019A9\t\u0015\u0005%\u00111DA\u0001\n\u0003\nY\u0001\u0003\u0006\u0002\u0010\u0005m\u0011\u0011!C!\u0003#A!\"!\u0006\u0002\u001c\u0005\u0005I\u0011IA0)\rI\u0013\u0011\r\u0005\tk\u0006u\u0013\u0011!a\u0001c\u001a9\u0011Q\r\u0001\t\u0002\u0006\u001d$A\u0004'p_.,\bOT8u\r>,h\u000eZ\n\u0007\u0003GR\u0001'\r\u001b\t\u000f\u0011\u000b\u0019\u0007\"\u0001\u0002lQ\u0011\u0011Q\u000e\t\u0004C\u0005\r\u0004B\u0002\u0010\u0002d\u0011\u0005\u0011\n\u0003\u0005`\u0003G\n\t\u0011\"\u0011a\u0011!I\u00171MA\u0001\n\u0003Q\u0007\"C8\u0002d\u0005\u0005I\u0011AA<)\r\t\u0018\u0011\u0010\u0005\tk\u0006U\u0014\u0011!a\u0001W\"Aq/a\u0019\u0002\u0002\u0013\u0005\u0003\u0010\u0003\u0006\u0002\u0002\u0005\r\u0014\u0011!C\u0001\u0003\u007f\"2!KAA\u0011!)\u0018QPA\u0001\u0002\u0004\t\bBCA\u0005\u0003G\n\t\u0011\"\u0011\u0002\f!Q\u0011qBA2\u0003\u0003%\t%!\u0005\u0007\r\u0005%\u0005\u0001QAF\u0005=aun\\6vaN+8mY3fI\u0016$7CBAD\u0015A\nD\u0007C\u0006\u0002\u0010\u0006\u001d%Q3A\u0005\u0002\u0005E\u0015!C9vC2Lg-[3s+\t\t\u0019\nE\u0002\"\u0003+KA!a&\u0002\u001a\n!AK]3f\u0013\r\tYJ\u0001\u0002\u0006)J,Wm\u001d\u0005\f\u0003?\u000b9I!E!\u0002\u0013\t\u0019*\u0001\u0006rk\u0006d\u0017NZ5fe\u0002B\u0011BHAD\u0005+\u0007I\u0011A\u0010\t\u0015\u0005\u0015\u0012q\u0011B\tB\u0003%\u0001\u0005C\u0004E\u0003\u000f#\t!a*\u0015\r\u0005%\u00161VAW!\r\t\u0013q\u0011\u0005\t\u0003\u001f\u000b)\u000b1\u0001\u0002\u0014\"1a$!*A\u0002\u0001BaaJAD\t\u0003B\u0003\"\u0003(\u0002\b\u0006\u0005I\u0011AAZ)\u0019\tI+!.\u00028\"Q\u0011qRAY!\u0003\u0005\r!a%\t\u0011y\t\t\f%AA\u0002\u0001B\u0011BUAD#\u0003%\t!a/\u0016\u0005\u0005u&fAAJ+\"Q\u0011QIAD#\u0003%\t!a\u0010\t\u0011}\u000b9)!A\u0005B\u0001D\u0001\"[AD\u0003\u0003%\tA\u001b\u0005\n_\u0006\u001d\u0015\u0011!C\u0001\u0003\u000f$2!]Ae\u0011!)\u0018QYA\u0001\u0002\u0004Y\u0007\u0002C<\u0002\b\u0006\u0005I\u0011\t=\t\u0015\u0005\u0005\u0011qQA\u0001\n\u0003\ty\rF\u0002*\u0003#D\u0001\"^Ag\u0003\u0003\u0005\r!\u001d\u0005\u000b\u0003\u0013\t9)!A\u0005B\u0005-\u0001BCA\b\u0003\u000f\u000b\t\u0011\"\u0011\u0002\u0012!Q\u0011QCAD\u0003\u0003%\t%!7\u0015\u0007%\nY\u000e\u0003\u0005v\u0003/\f\t\u00111\u0001r\u000f%\ty\u000eAA\u0001\u0012\u0003\t\t/A\bM_>\\W\u000f]*vG\u000e,W\rZ3e!\r\t\u00131\u001d\u0004\n\u0003\u0013\u0003\u0011\u0011!E\u0001\u0003K\u001cR!a9\u0002hR\u0002\u0012\"!;\u0002p\u0006M\u0005%!+\u000e\u0005\u0005-(bAAw\r\u00059!/\u001e8uS6,\u0017\u0002BAy\u0003W\u0014\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83\u0011\u001d!\u00151\u001dC\u0001\u0003k$\"!!9\t\u0015\u0005=\u00111]A\u0001\n\u000b\n\t\u0002\u0003\u0006\u0002|\u0006\r\u0018\u0011!CA\u0003{\fQ!\u00199qYf$b!!+\u0002\u0000\n\u0005\u0001\u0002CAH\u0003s\u0004\r!a%\t\ry\tI\u00101\u0001!\u0011)\u0011)!a9\u0002\u0002\u0013\u0005%qA\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011IA!\u0006\u0011\u000b-\u0011YAa\u0004\n\u0007\t5aA\u0001\u0004PaRLwN\u001c\t\u0007\u0017\tE\u00111\u0013\u0011\n\u0007\tMaA\u0001\u0004UkBdWM\r\u0005\u000b\u0005/\u0011\u0019!!AA\u0002\u0005%\u0016a\u0001=%a\u001dI!1\u0004\u0001\u0002\u0002#\u0005!QD\u0001\u0010\u0019>|7.\u001e9B[\nLw-^8vgB\u0019\u0011Ea\b\u0007\u00119\u0002\u0011\u0011!E\u0001\u0005C\u0019RAa\b\u0003$Q\u0002b!!;\u0003&i2\u0015\u0002\u0002B\u0014\u0003W\u0014\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82\u0011\u001d!%q\u0004C\u0001\u0005W!\"A!\b\t\u0015\u0005=!qDA\u0001\n\u000b\n\t\u0002\u0003\u0006\u0002|\n}\u0011\u0011!CA\u0005c!2A\u0012B\u001a\u0011\u0019A$q\u0006a\u0001u!Q!Q\u0001B\u0010\u0003\u0003%\tIa\u000e\u0015\t\te\"1\b\t\u0005\u0017\t-!\bC\u0005\u0003\u0018\tU\u0012\u0011!a\u0001\r\u001eI!q\b\u0001\u0002\u0002#\u0005!\u0011I\u0001\u0013\u0019>|7.\u001e9J]\u0006\u001c7-Z:tS\ndW\rE\u0002\"\u0005\u00072\u0011\"!\b\u0001\u0003\u0003E\tA!\u0012\u0014\u000b\t\r#q\t\u001b\u0011\u0011\u0005%\u0018q\u001e\u0011;\u0003_Aq\u0001\u0012B\"\t\u0003\u0011Y\u0005\u0006\u0002\u0003B!Q\u0011q\u0002B\"\u0003\u0003%)%!\u0005\t\u0015\u0005m(1IA\u0001\n\u0003\u0013\t\u0006\u0006\u0004\u00020\tM#Q\u000b\u0005\u0007=\t=\u0003\u0019\u0001\u0011\t\ra\u0012y\u00051\u0001;\u0011)\u0011)Aa\u0011\u0002\u0002\u0013\u0005%\u0011\f\u000b\u0005\u00057\u0012y\u0006E\u0003\f\u0005\u0017\u0011i\u0006E\u0003\f\u0005#\u0001#\b\u0003\u0006\u0003\u0018\t]\u0013\u0011!a\u0001\u0003_9qAa\u0019\u0001\u0011\u0003\u000bi'\u0001\bM_>\\W\u000f\u001d(pi\u001a{WO\u001c3\u0007\r\t\u001d\u0004\u0001\u0001B5\u0005)\u00196m\u001c9f\u000b:$(/_\n\u0004\u0005KR\u0001B\u0003B7\u0005K\u0012)\u0019!C\u0001?\u0005\u00191/_7\t\u0015\tE$Q\rB\u0001B\u0003%\u0001%\u0001\u0003ts6\u0004\u0003b\u0003B;\u0005K\u0012)\u0019!C\u0001\u0005o\nQa\\<oKJ,\"A!\u001f\u0011\u0007\u0005\u0012YH\u0002\u0004\u0003~\u0001\u0001!q\u0010\u0002\u0006'\u000e|\u0007/Z\n\b\u0005wR!\u0011\u0011BD!\r\t#1Q\u0005\u0004\u0005\u000b\u0013\"\u0001C*d_B,\u0017\t]5\u0011\u0007\u0005\u0012I)C\u0002\u0003\fJ\u0011a\"T3nE\u0016\u00148kY8qK\u0006\u0003\u0018\u000e\u0003\u0005E\u0005w\"\t\u0002\u0001BH)\t\u0011I\bC\u0007\u0003\u0014\nm\u0004\u0019!a\u0001\n\u00031!QS\u0001\u0006K2,Wn]\u000b\u0003\u0005/\u00032!\tB3\u00115\u0011YJa\u001fA\u0002\u0003\u0007I\u0011\u0001\u0004\u0003\u001e\u0006IQ\r\\3ng~#S-\u001d\u000b\u0004-\t}\u0005\"C;\u0003\u001a\u0006\u0005\t\u0019\u0001BL\u0011%\u0011\u0019Ka\u001f!B\u0013\u00119*\u0001\u0004fY\u0016l7\u000f\t\u0005\u000e\u0005O\u0013YH!AA\u0002\u0013\u0005\u0001A!+\u0002_M\u001c\u0017\r\\1%e\u00164G.Z2uI%tG/\u001a:oC2$3kY8qKN$CE\\3ti&tw\r\\3wK2|F%Z9\u0015\u0007Y\u0011Y\u000b\u0003\u0005v\u0005K\u000b\t\u00111\u0001l\u0011-\u0011yKa\u001f\u0003\u0002\u0003\u0005\u000b\u0015B6\u0002YM\u001c\u0017\r\\1%e\u00164G.Z2uI%tG/\u001a:oC2$3kY8qKN$CE\\3ti&tw\r\\3wK2\u0004\u0003\"\u0004BZ\u0005w\u0012\t\u00111A\u0005\u0002\u0001\u0011),\u0001\u0017tG\u0006d\u0017\r\n:fM2,7\r\u001e\u0013j]R,'O\\1mIM\u001bw\u000e]3tI\u0011B\u0017m\u001d5uC\ndWm\u0018\u0013fcR\u0019aCa.\t\u0013U\u0014\t,!AA\u0002\te\u0006#B\u0006\u0003<\n]\u0015b\u0001B_\r\t)\u0011I\u001d:bs\"a!\u0011\u0019B>\u0005\u0003\u0005\t\u0015)\u0003\u0003:\u0006I3oY1mC\u0012\u0012XM\u001a7fGR$\u0013N\u001c;fe:\fG\u000eJ*d_B,7\u000f\n\u0013iCNDG/\u00192mK\u0002B!B!2\u0003|\u0001\u0007I\u0011\u0002Bd\u0003))G.Z7t\u0007\u0006\u001c\u0007.Z\u000b\u0003\u0005\u0013\u0004RAa3\u0003R\u0002r1a\u0003Bg\u0013\r\u0011yMB\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\u0011\u0019N!6\u0003\t1K7\u000f\u001e\u0006\u0004\u0005\u001f4\u0001B\u0003Bm\u0005w\u0002\r\u0011\"\u0003\u0003\\\u0006qQ\r\\3ng\u000e\u000b7\r[3`I\u0015\fHc\u0001\f\u0003^\"IQOa6\u0002\u0002\u0003\u0007!\u0011\u001a\u0005\n\u0005C\u0014Y\b)Q\u0005\u0005\u0013\f1\"\u001a7f[N\u001c\u0015m\u00195fA!I!Q\u001dB>\u0001\u0004%IA[\u0001\u000bG\u0006\u001c\u0007.\u001a3TSj,\u0007B\u0003Bu\u0005w\u0002\r\u0011\"\u0003\u0003l\u0006q1-Y2iK\u0012\u001c\u0016N_3`I\u0015\fHc\u0001\f\u0003n\"AQOa:\u0002\u0002\u0003\u00071\u000e\u0003\u0005\u0003r\nm\u0004\u0015)\u0003l\u0003-\u0019\u0017m\u00195fINK'0\u001a\u0011\t\u000f\tU(1\u0010C\u0005+\u0005ya\r\\;tQ\u0016cW-\\:DC\u000eDW\rC\u0005\u0003z\nm$\u0019!C\u0005U\u0006A\u0001*Q*I'&SV\t\u0003\u0005\u0003~\nm\u0004\u0015!\u0003l\u0003%A\u0015i\u0015%T\u0013j+\u0005\u0005C\u0005\u0004\u0002\tm$\u0019!C\u0005U\u0006A\u0001*Q*I\u001b\u0006\u001b6\n\u0003\u0005\u0004\u0006\tm\u0004\u0015!\u0003l\u0003%A\u0015i\u0015%N\u0003N[\u0005\u0005C\u0005\u0004\n\tm$\u0019!C\u0005U\u0006AQ*\u0013(`\u0011\u0006\u001b\u0006\n\u0003\u0005\u0004\u000e\tm\u0004\u0015!\u0003l\u0003%i\u0015JT0I\u0003NC\u0005\u0005\u0003\u0005\u0004\u0012\tmD\u0011\u0001B<\u0003)\u0019Gn\u001c8f'\u000e|\u0007/\u001a\u0005\b\u0007+\u0011Y\b\"\u0011)\u0003\u001dI7/R7qifDqa!\u0007\u0003|\u0011\u0005#.\u0001\u0003tSj,\u0007bBB\u000f\u0005w\"IA[\u0001\u000bI&\u0014Xm\u0019;TSj,\u0007\u0002CB\u0011\u0005w\"\tba\t\u0002\u0015\u0015tG/\u001a:F]R\u0014\u0018\u0010F\u0002\u0017\u0007KA\u0001ba\n\u0004 \u0001\u0007!qS\u0001\u0002K\"A11\u0006B>\t\u0013\u0019i#A\u0006f]R,'/\u00138ICNDGc\u0001\f\u00040!A1qEB\u0015\u0001\u0004\u00119\n\u0003\u0005\u00044\tmD\u0011AB\u001b\u0003\u0015)g\u000e^3s+\u0011\u00199d!\u0010\u0015\t\re2\u0011\n\t\u0005\u0007w\u0019i\u0004\u0004\u0001\u0005\u0011\r}2\u0011\u0007b\u0001\u0007\u0003\u0012\u0011\u0001V\t\u0004\u0007\u0007\u0002\u0003cA\u0006\u0004F%\u00191q\t\u0004\u0003\u000f9{G\u000f[5oO\"A!QNB\u0019\u0001\u0004\u0019I\u0004\u0003\u0005\u0004N\tmD\u0011AB(\u0003-)g\u000e^3s+:L\u0017/^3\u0015\u0007Y\u0019\t\u0006C\u0004\u0003n\r-\u0003\u0019\u0001\u0011\t\u0011\rU#1\u0010C\u0001\u0007/\n!\"\u001a8uKJLeMT3x+\u0011\u0019If!\u0018\u0015\t\rm3q\f\t\u0005\u0007w\u0019i\u0006\u0002\u0005\u0004@\rM#\u0019AB!\u0011!\u0011iga\u0015A\u0002\rm\u0003bBB2\u0005w\"I!F\u0001\u000bGJ,\u0017\r^3ICND\u0007\u0002CB4\u0005w\"Ia!\u001b\u0002\u001d\u0015tG/\u001a:BY2Le\u000eS1tQR)aca\u001b\u0004n!A1qEB3\u0001\u0004\u00119\nC\u0005\u0004p\r\u0015\u0004\u0013!a\u0001W\u0006\ta\u000e\u0003\u0005\u0004t\tmD\u0011AB;\u0003\u0019\u0011X\r[1tQR)aca\u001e\u0004z!9!QNB9\u0001\u0004\u0001\u0003\u0002CB>\u0007c\u0002\ra! \u0002\u000f9,wO\\1nKB\u0019\u0011ea \n\t\r\u000551\u0011\u0002\u0005\u001d\u0006lW-C\u0002\u0004\u0006\n\u0011QAT1nKND\u0001b!#\u0003|\u0011\u000511R\u0001\u0007k:d\u0017N\\6\u0015\u0007Y\u0019i\t\u0003\u0005\u0004(\r\u001d\u0005\u0019\u0001BL\u0011!\u0019IIa\u001f\u0005\u0002\rEEc\u0001\f\u0004\u0014\"9!QNBH\u0001\u0004\u0001\u0003\u0002CBL\u0005w\"\ta!'\u0002\u00191|wn[;q\u001b>$W\u000f\\3\u0015\u0007\u0001\u001aY\n\u0003\u0005\u0004\u001e\u000eU\u0005\u0019AB?\u0003\u0011q\u0017-\\3\t\u0011\r\u0005&1\u0010C\u0001\u0007G\u000b1\u0002\\8pWV\u00048\t\\1tgR\u0019\u0001e!*\t\u0011\ru5q\u0014a\u0001\u0007{B\u0001b!+\u0003|\u0011\u000511V\u0001\rG>tG/Y5og:\u000bW.\u001a\u000b\u0004S\r5\u0006\u0002CBO\u0007O\u0003\ra! \t\u0011\rE&1\u0010C\u0001\u0007g\u000ba\u0001\\8pWV\u0004Hc\u0001\u0011\u00046\"A1QTBX\u0001\u0004\u0019i\b\u0003\u0005\u0004:\nmD\u0011AB^\u0003%awn\\6va\u0006cG\u000e\u0006\u0003\u0004>\u000e\u0005\u0007#\u0002Bf\u0007\u007f\u0003\u0013b\u0001@\u0003V\"A1QTB\\\u0001\u0004\u0019i\b\u0003\u0005\u0004F\nmD\u0011ABd\u0003Aawn\\6va\u0006cG.\u00128ue&,7\u000f\u0006\u0003\u0004J\u000e-\u0007C\u0002Bf\u0007\u007f\u00139\n\u0003\u0005\u0004\u001e\u000e\r\u0007\u0019AB?\u0011!\u0019yMa\u001f\u0005\u0002\rE\u0017a\u00067p_.,\b/\u00168tQ\u0006$wn^3e\u000b:$(/[3t)\u0011\u0019Ima5\t\u0011\ru5Q\u001aa\u0001\u0007{B\u0001ba6\u0003|\u0011\u00051\u0011\\\u0001\fY>|7.\u001e9F]R\u0014\u0018\u0010\u0006\u0003\u0003\u0018\u000em\u0007\u0002CBO\u0007+\u0004\ra! \t\u0011\r}'1\u0010C\u0001\u0007C\fq\u0002\\8pWV\u0004h*\u001a=u\u000b:$(/\u001f\u000b\u0005\u0005/\u001b\u0019\u000f\u0003\u0005\u0004f\u000eu\u0007\u0019\u0001BL\u0003\u0015)g\u000e\u001e:z\u0011!\u0019IOa\u001f\u0005\u0002\r-\u0018aC5t'\u0006lWmU2pa\u0016$2!KBw\u0011!\u0019yoa:A\u0002\te\u0014!B8uQ\u0016\u0014\b\u0002CBz\u0005w\"\ta!>\u0002\u0015%\u001c8+\u001e2TG>\u0004X\rF\u0002*\u0007oD\u0001ba<\u0004r\u0002\u0007!\u0011\u0010\u0005\t\u0007w\u0014Y\b\"\u0011\u0003H\u00061Ao\u001c'jgRD\u0001ba@\u0003|\u0011\u0005!qY\u0001\u0007g>\u0014H/\u001a3\t\u000f\u0011\r!1\u0010C\u0001U\u0006aa.Z:uS:<G*\u001a<fY\"AAq\u0001B>\t\u0003!I!\u0001\u0005ji\u0016\u0014\u0018\r^8s+\t\u0019i\f\u0003\u0005\u0005\u000e\tmD\u0011\tC\b\u0003\u001d1wN]3bG\",B\u0001\"\u0005\u0005 Q\u0019a\u0003b\u0005\t\u0011\u0011UA1\u0002a\u0001\t/\t\u0011\u0001\u001d\t\u0007\u0017\u0011e\u0001\u0005\"\b\n\u0007\u0011maAA\u0005Gk:\u001cG/[8ocA!11\bC\u0010\t!!\t\u0003b\u0003C\u0002\u0011\r\"!A+\u0012\u0007\r\r\u0013\u000f\u0003\u0005\u0005(\tmD\u0011\tC\u0015\u0003%1\u0017\u000e\u001c;fe:{G\u000f\u0006\u0003\u0003z\u0011-\u0002\u0002\u0003C\u000b\tK\u0001\r\u0001\"\f\u0011\u000b-!I\u0002I\u0015\t\u0011\u0011E\"1\u0010C!\tg\taAZ5mi\u0016\u0014H\u0003\u0002B=\tkA\u0001\u0002\"\u0006\u00050\u0001\u0007AQ\u0006\u0005\t\ts\u0011Y\b\"\u0001\u0003H\u00069!/\u001a<feN,\u0007\u0006\u0003C\u001c\t{!\u0019\u0005b\u0012\u0011\u0007-!y$C\u0002\u0005B\u0019\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3eC\t!)%\u0001\u000fVg\u0016\u0004\u0003\r^8MSN$hF]3wKJ\u001cX\r\u0019\u0011j]N$X-\u00193\"\u0005\u0011%\u0013A\u0002\u001a/cAr\u0003\u0007\u0003\u0005\u0005N\tmD\u0011\tC(\u0003!i7n\u0015;sS:<Gc\u0002\u001e\u0005R\u0011UC\u0011\f\u0005\b\t'\"Y\u00051\u0001;\u0003\u0015\u0019H/\u0019:u\u0011\u001d!9\u0006b\u0013A\u0002i\n1a]3q\u0011\u001d!Y\u0006b\u0013A\u0002i\n1!\u001a8e\u0011!\tyAa\u001f\u0005B\u0011}C#\u0001\u001e\t\u0015\u0011\r$1PI\u0001\n\u0013!)'\u0001\rf]R,'/\u00117m\u0013:D\u0015m\u001d5%I\u00164\u0017-\u001e7uII*\"\u0001b\u001a+\u0005-,\u0006\u0002\u0004C6\u0005w\u0012\t\u00111A\u0005\u0002\u0001Q\u0017aK:dC2\fGE]3gY\u0016\u001cG\u000fJ5oi\u0016\u0014h.\u00197%'\u000e|\u0007/Z:%I9,7\u000f^5oO2,g/\u001a7\t\u001b\u0011=$1\u0010B\u0001\u0002\u0004%\t\u0001\u0001C9\u0003!\u001a8-\u00197bII,g\r\\3di\u0012Jg\u000e^3s]\u0006dGeU2pa\u0016\u001cH\u0005\n5bg\"$\u0018M\u00197f+\t\u0011I\fC\u0006\u0005v\t\u0015$\u0011!Q\u0001\n\te\u0014AB8x]\u0016\u0014\b\u0005C\u0004E\u0005K\"\t\u0001\"\u001f\u0015\r\t]E1\u0010C?\u0011\u001d\u0011i\u0007b\u001eA\u0002\u0001B\u0001B!\u001e\u0005x\u0001\u0007!\u0011\u0010\u0005\u000b\t\u0003\u0013)\u00071A\u0005\u0002\tU\u0015\u0001\u0002;bS2D!\u0002\"\"\u0003f\u0001\u0007I\u0011\u0001CD\u0003!!\u0018-\u001b7`I\u0015\fHc\u0001\f\u0005\n\"IQ\u000fb!\u0002\u0002\u0003\u0007!q\u0013\u0005\n\t\u001b\u0013)\u0007)Q\u0005\u0005/\u000bQ\u0001^1jY\u0002B!\u0002\"%\u0003f\u0001\u0007I\u0011\u0001BK\u0003\u0011qW\r\u001f;\t\u0015\u0011U%Q\ra\u0001\n\u0003!9*\u0001\u0005oKb$x\fJ3r)\r1B\u0011\u0014\u0005\nk\u0012M\u0015\u0011!a\u0001\u0005/C\u0011\u0002\"(\u0003f\u0001\u0006KAa&\u0002\u000b9,\u0007\u0010\u001e\u0011\t\u000f\u0011\u0005&Q\rC\u0001U\u0006)A-\u001a9uQ\"A\u0011\u0011\u0002B3\t\u0003\nY\u0001\u0003\u0005\u0002\u0010\t\u0015D\u0011\tC0\u0011\u001d!I\u000b\u0001C\u0005\tW\u000bQB\\3x'\u000e|\u0007/Z#oiJLHC\u0002BL\t[#y\u000bC\u0004\u0003n\u0011\u001d\u0006\u0019\u0001\u0011\t\u0011\tUDq\u0015a\u0001\u0005s:q\u0001b-\u0001\u0011\u0003!),A\u0003TG>\u0004X\rE\u0002\"\to3qA! \u0001\u0011\u0003!IlE\u0002\u00058*Aq\u0001\u0012C\\\t\u0003!i\f\u0006\u0002\u00056\"AA\u0011\u0019C\\\t\u0003!\u0019-\u0001\u0006v]\u0006\u0004\b\u000f\\=TKF$B\u0001\"2\u0005RB)1\u0002b2\u0005L&\u0019A\u0011\u001a\u0004\u0003\tM{W.\u001a\t\u0006\u0005\u0017$i\rI\u0005\u0005\t\u001f\u0014)NA\u0002TKFD\u0001\u0002b5\u0005@\u0002\u0007!\u0011P\u0001\u0006I\u0016\u001cGn\u001d\u0005\n\t/\u0004!\u0019!C\u0002\t3\f\u0001bU2pa\u0016$\u0016mZ\u000b\u0003\t7\u0004b\u0001\"8\u0005`\neT\"\u0001\u0003\n\u0007\u0011\u0005HA\u0001\u0005DY\u0006\u001c8\u000fV1h\u0011!!)\u000f\u0001Q\u0001\n\u0011m\u0017!C*d_B,G+Y4!\u000b\u0019!I\u000f\u0001\u0001\u0003z\tYQ*Z7cKJ\u001c6m\u001c9f\u0011%!i\u000f\u0001b\u0001\n\u0007!y/\u0001\bNK6\u0014WM]*d_B,G+Y4\u0016\u0005\u0011E\bC\u0002Co\t?$\u0019\u0010E\u0002\"\tOD\u0001\u0002b>\u0001A\u0003%A\u0011_\u0001\u0010\u001b\u0016l'-\u001a:TG>\u0004X\rV1hA!9A1 \u0001\u0005\u0002\t]\u0014\u0001\u00038foN\u001bw\u000e]3\t\u0011\u0011}\b\u0001\"\u0001\u0007\u0005o\n!C\\3x\r&tG-T3nE\u0016\u00148kY8qK\"9Q1\u0001\u0001\u0005\u0006\u0015\u0015\u0011A\u00048fo:+7\u000f^3e'\u000e|\u0007/\u001a\u000b\u0005\u0005s*9\u0001\u0003\u0005\u0006\n\u0015\u0005\u0001\u0019\u0001B=\u0003\u0015yW\u000f^3s\u0011\u001d)i\u0001\u0001C\u0001\u000b\u001f\tAB\\3x'\u000e|\u0007/Z,ji\"$BA!\u001f\u0006\u0012!A!1SC\u0006\u0001\u0004)\u0019\u0002\u0005\u0003\f\u000b+\u0001\u0013bAC\f\r\tQAH]3qK\u0006$X\r\u001a \t\u000f\u0015m\u0001\u0001\"\u0001\u0006\u001e\u0005ya.Z<QC\u000e\\\u0017mZ3TG>\u0004X\r\u0006\u0003\u0003z\u0015}\u0001bBC\u0011\u000b3\u0001\r\u0001I\u0001\ta.<7\t\\1tg\"9QQ\u0005\u0001\u0005\u0002\u0015\u001d\u0012AD:d_B,GK]1og\u001a|'/\u001c\u000b\u0005\u000bS))\u0004\u0006\u0003\u0003z\u0015-\u0002\"CC\u0017\u000bG!\t\u0019AC\u0018\u0003\ty\u0007\u000fE\u0003\f\u000bc\u0011I(C\u0002\u00064\u0019\u0011\u0001\u0002\u00102z]\u0006lWM\u0010\u0005\b\u0005k*\u0019\u00031\u0001!\u000f\u001d)I\u0004\u0001E\u0001\u000bw\t!\"R7qif\u001c6m\u001c9f!\r\tSQ\b\u0004\b\u000b\u007f\u0001\u0001\u0012AC!\u0005))U\u000e\u001d;z'\u000e|\u0007/Z\n\u0005\u000b{\u0011I\bC\u0004E\u000b{!\t!\"\u0012\u0015\u0005\u0015m\u0002\u0002CB\u0011\u000b{!\t%\"\u0013\u0015\u0007Y)Y\u0005\u0003\u0005\u0004(\u0015\u001d\u0003\u0019\u0001BL\r\u0019)y\u0005\u0001\u0001\u0006R\tQQI\u001d:peN\u001bw\u000e]3\u0014\t\u00155#\u0011\u0010\u0005\u000b\u0005k*iE!A!\u0002\u0013\u0001\u0003b\u0002#\u0006N\u0011\u0005Qq\u000b\u000b\u0005\u000b3*Y\u0006E\u0002\"\u000b\u001bBqA!\u001e\u0006V\u0001\u0007\u0001\u0005C\u0005\u0006`\u0001\u0011\r\u0011\"\u0004\u0006b\u0005iQ.\u0019=SK\u000e,(o]5p]N,\"!b\u0019\u0010\u0005\u0015\u0015TDA\u0002i\u0012!)I\u0007\u0001Q\u0001\u000e\u0015\r\u0014AD7bqJ+7-\u001e:tS>t7\u000f\t\t\u0005\u000b[*y'D\u0001\u0003\u0013\r)\tH\u0001\u0002\f'fl'm\u001c7UC\ndW\r")
public interface Scopes
extends scala.reflect.api.Scopes {
    public void scala$reflect$internal$Scopes$_setter_$ScopeTag_$eq(ClassTag var1);

    public void scala$reflect$internal$Scopes$_setter_$MemberScopeTag_$eq(ClassTag var1);

    public Scopes$LookupSucceeded$ LookupSucceeded();

    public Scopes$LookupAmbiguous$ LookupAmbiguous();

    public Scopes$LookupInaccessible$ LookupInaccessible();

    public Scopes$LookupNotFound$ LookupNotFound();

    public Scopes$Scope$ Scope();

    public ClassTag<Scope> ScopeTag();

    public ClassTag<Scope> MemberScopeTag();

    public Scope newScope();

    public Scope newFindMemberScope();

    public Scope newNestedScope(Scope var1);

    public Scope newScopeWith(Seq<Symbols.Symbol> var1);

    public Scope newPackageScope(Symbols.Symbol var1);

    public Scope scopeTransform(Symbols.Symbol var1, Function0<Scope> var2);

    public Scopes$EmptyScope$ EmptyScope();

    public int scala$reflect$internal$Scopes$$maxRecursions();

    public static class Scope
    implements Scopes.MemberScopeApi {
        private ScopeEntry elems;
        private int scala$reflect$internal$Scopes$$nestinglevel;
        private ScopeEntry[] scala$reflect$internal$Scopes$$hashtable;
        private List<Symbols.Symbol> elemsCache;
        private int cachedSize;
        private final int HASHSIZE;
        private final int HASHMASK;
        private final int MIN_HASH;
        public final /* synthetic */ SymbolTable $outer;

        @Override
        public GenericCompanion<Iterable> companion() {
            return Iterable$class.companion(this);
        }

        @Override
        public Iterable<Symbols.Symbol> seq() {
            return Iterable$class.seq(this);
        }

        @Override
        public Iterable<Symbols.Symbol> thisCollection() {
            return IterableLike$class.thisCollection(this);
        }

        @Override
        public Iterable toCollection(Object repr) {
            return IterableLike$class.toCollection(this, repr);
        }

        @Override
        public boolean forall(Function1<Symbols.Symbol, Object> p) {
            return IterableLike$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<Symbols.Symbol, Object> p) {
            return IterableLike$class.exists(this, p);
        }

        @Override
        public Option<Symbols.Symbol> find(Function1<Symbols.Symbol, Object> p) {
            return IterableLike$class.find(this, p);
        }

        @Override
        public <B> B foldRight(B z, Function2<Symbols.Symbol, B, B> op) {
            return (B)IterableLike$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceRight(Function2<Symbols.Symbol, B, B> op) {
            return (B)IterableLike$class.reduceRight(this, op);
        }

        @Override
        public Iterable<Symbols.Symbol> toIterable() {
            return IterableLike$class.toIterable(this);
        }

        @Override
        public Iterator<Symbols.Symbol> toIterator() {
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
        public Iterator<Iterable<Symbols.Symbol>> grouped(int size2) {
            return IterableLike$class.grouped(this, size2);
        }

        @Override
        public Iterator<Iterable<Symbols.Symbol>> sliding(int size2) {
            return IterableLike$class.sliding(this, size2);
        }

        @Override
        public Iterator<Iterable<Symbols.Symbol>> sliding(int size2, int step) {
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
        public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<Iterable<Symbols.Symbol>, Tuple2<A1, B>, That> bf) {
            return (That)IterableLike$class.zip(this, that, bf);
        }

        @Override
        public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<Iterable<Symbols.Symbol>, Tuple2<A1, B>, That> bf) {
            return (That)IterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
        }

        @Override
        public <A1, That> That zipWithIndex(CanBuildFrom<Iterable<Symbols.Symbol>, Tuple2<A1, Object>, That> bf) {
            return (That)IterableLike$class.zipWithIndex(this, bf);
        }

        @Override
        public <B> boolean sameElements(GenIterable<B> that) {
            return IterableLike$class.sameElements(this, that);
        }

        @Override
        public Stream<Symbols.Symbol> toStream() {
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
        public IterableView<Symbols.Symbol, Iterable<Symbols.Symbol>> view(int from2, int until2) {
            return IterableLike$class.view(this, from2, until2);
        }

        @Override
        public Builder<Symbols.Symbol, Iterable<Symbols.Symbol>> newBuilder() {
            return GenericTraversableTemplate$class.newBuilder(this);
        }

        @Override
        public <B> Builder<B, Iterable<B>> genericBuilder() {
            return GenericTraversableTemplate$class.genericBuilder(this);
        }

        @Override
        public <A1, A2> Tuple2<Iterable<A1>, Iterable<A2>> unzip(Function1<Symbols.Symbol, Tuple2<A1, A2>> asPair) {
            return GenericTraversableTemplate$class.unzip(this, asPair);
        }

        @Override
        public <A1, A2, A3> Tuple3<Iterable<A1>, Iterable<A2>, Iterable<A3>> unzip3(Function1<Symbols.Symbol, Tuple3<A1, A2, A3>> asTriple) {
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
        public Combiner<Symbols.Symbol, ParIterable<Symbols.Symbol>> parCombiner() {
            return TraversableLike$class.parCombiner(this);
        }

        @Override
        public boolean hasDefiniteSize() {
            return TraversableLike$class.hasDefiniteSize(this);
        }

        @Override
        public <B, That> That $plus$plus(GenTraversableOnce<B> that, CanBuildFrom<Iterable<Symbols.Symbol>, B, That> bf) {
            return (That)TraversableLike$class.$plus$plus(this, that, bf);
        }

        @Override
        public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<Iterable<Symbols.Symbol>, B, That> bf) {
            return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
        }

        @Override
        public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<Iterable<Symbols.Symbol>, B, That> bf) {
            return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
        }

        @Override
        public <B, That> That map(Function1<Symbols.Symbol, B> f, CanBuildFrom<Iterable<Symbols.Symbol>, B, That> bf) {
            return (That)TraversableLike$class.map(this, f, bf);
        }

        @Override
        public <B, That> That flatMap(Function1<Symbols.Symbol, GenTraversableOnce<B>> f, CanBuildFrom<Iterable<Symbols.Symbol>, B, That> bf) {
            return (That)TraversableLike$class.flatMap(this, f, bf);
        }

        @Override
        public <B, That> That collect(PartialFunction<Symbols.Symbol, B> pf, CanBuildFrom<Iterable<Symbols.Symbol>, B, That> bf) {
            return (That)TraversableLike$class.collect(this, pf, bf);
        }

        @Override
        public Tuple2<Iterable<Symbols.Symbol>, Iterable<Symbols.Symbol>> partition(Function1<Symbols.Symbol, Object> p) {
            return TraversableLike$class.partition(this, p);
        }

        @Override
        public <K> Map<K, Iterable<Symbols.Symbol>> groupBy(Function1<Symbols.Symbol, K> f) {
            return TraversableLike$class.groupBy(this, f);
        }

        @Override
        public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<Iterable<Symbols.Symbol>, B, That> cbf) {
            return (That)TraversableLike$class.scan(this, z, op, cbf);
        }

        @Override
        public <B, That> That scanLeft(B z, Function2<B, Symbols.Symbol, B> op, CanBuildFrom<Iterable<Symbols.Symbol>, B, That> bf) {
            return (That)TraversableLike$class.scanLeft(this, z, op, bf);
        }

        @Override
        public <B, That> That scanRight(B z, Function2<Symbols.Symbol, B, B> op, CanBuildFrom<Iterable<Symbols.Symbol>, B, That> bf) {
            return (That)TraversableLike$class.scanRight(this, z, op, bf);
        }

        @Override
        public Option<Symbols.Symbol> headOption() {
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
        public Option<Symbols.Symbol> lastOption() {
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
        public Tuple2<Iterable<Symbols.Symbol>, Iterable<Symbols.Symbol>> span(Function1<Symbols.Symbol, Object> p) {
            return TraversableLike$class.span(this, p);
        }

        @Override
        public Tuple2<Iterable<Symbols.Symbol>, Iterable<Symbols.Symbol>> splitAt(int n) {
            return TraversableLike$class.splitAt(this, n);
        }

        @Override
        public Iterator<Iterable<Symbols.Symbol>> tails() {
            return TraversableLike$class.tails(this);
        }

        @Override
        public Iterator<Iterable<Symbols.Symbol>> inits() {
            return TraversableLike$class.inits(this);
        }

        @Override
        public Traversable<Symbols.Symbol> toTraversable() {
            return TraversableLike$class.toTraversable(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, Symbols.Symbol, Col> cbf) {
            return (Col)TraversableLike$class.to(this, cbf);
        }

        @Override
        public String stringPrefix() {
            return TraversableLike$class.stringPrefix(this);
        }

        @Override
        public FilterMonadic<Symbols.Symbol, Iterable<Symbols.Symbol>> withFilter(Function1<Symbols.Symbol, Object> p) {
            return TraversableLike$class.withFilter(this, p);
        }

        @Override
        public Parallel par() {
            return Parallelizable$class.par(this);
        }

        @Override
        public List<Symbols.Symbol> reversed() {
            return TraversableOnce$class.reversed(this);
        }

        @Override
        public boolean nonEmpty() {
            return TraversableOnce$class.nonEmpty(this);
        }

        @Override
        public int count(Function1<Symbols.Symbol, Object> p) {
            return TraversableOnce$class.count(this, p);
        }

        @Override
        public <B> Option<B> collectFirst(PartialFunction<Symbols.Symbol, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, Symbols.Symbol, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<Symbols.Symbol, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, Symbols.Symbol, B> op) {
            return (B)TraversableOnce$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, Symbols.Symbol, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, Symbols.Symbol, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<Symbols.Symbol, B, B> op) {
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
        public <B> B aggregate(Function0<B> z, Function2<B, Symbols.Symbol, B> seqop, Function2<B, B, B> combop) {
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
        public Seq<Symbols.Symbol> toSeq() {
            return TraversableOnce$class.toSeq(this);
        }

        @Override
        public IndexedSeq<Symbols.Symbol> toIndexedSeq() {
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
        public Vector<Symbols.Symbol> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Symbols.Symbol, Tuple2<T, U>> ev) {
            return TraversableOnce$class.toMap(this, ev);
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

        public ScopeEntry elems() {
            return this.elems;
        }

        public void elems_$eq(ScopeEntry x$1) {
            this.elems = x$1;
        }

        public int scala$reflect$internal$Scopes$$nestinglevel() {
            return this.scala$reflect$internal$Scopes$$nestinglevel;
        }

        public void scala$reflect$internal$Scopes$$nestinglevel_$eq(int x$1) {
            this.scala$reflect$internal$Scopes$$nestinglevel = x$1;
        }

        public ScopeEntry[] scala$reflect$internal$Scopes$$hashtable() {
            return this.scala$reflect$internal$Scopes$$hashtable;
        }

        public void scala$reflect$internal$Scopes$$hashtable_$eq(ScopeEntry[] x$1) {
            this.scala$reflect$internal$Scopes$$hashtable = x$1;
        }

        private List<Symbols.Symbol> elemsCache() {
            return this.elemsCache;
        }

        private void elemsCache_$eq(List<Symbols.Symbol> x$1) {
            this.elemsCache = x$1;
        }

        private int cachedSize() {
            return this.cachedSize;
        }

        private void cachedSize_$eq(int x$1) {
            this.cachedSize = x$1;
        }

        private void flushElemsCache() {
            this.elemsCache_$eq(null);
            this.cachedSize_$eq(-1);
        }

        private int HASHSIZE() {
            return this.HASHSIZE;
        }

        private int HASHMASK() {
            return this.HASHMASK;
        }

        private int MIN_HASH() {
            return this.MIN_HASH;
        }

        public Scope cloneScope() {
            return this.scala$reflect$internal$Scopes$Scope$$$outer().newScopeWith(this.toList());
        }

        @Override
        public boolean isEmpty() {
            return this.elems() == null;
        }

        @Override
        public int size() {
            if (this.cachedSize() < 0) {
                this.cachedSize_$eq(this.directSize());
            }
            return this.cachedSize();
        }

        /*
         * WARNING - void declaration
         */
        private int directSize() {
            void var1_1;
            int s2 = 0;
            for (ScopeEntry e = this.elems(); e != null; e = e.next()) {
                ++s2;
            }
            return (int)var1_1;
        }

        public void enterEntry(ScopeEntry e) {
            this.flushElemsCache();
            if (this.scala$reflect$internal$Scopes$$hashtable() != null) {
                this.scala$reflect$internal$Scopes$Scope$$enterInHash(e);
            } else if (this.size() >= this.MIN_HASH()) {
                this.createHash();
            }
        }

        public void scala$reflect$internal$Scopes$Scope$$enterInHash(ScopeEntry e) {
            int i = e.sym().name().start() & this.HASHMASK();
            e.tail_$eq(this.scala$reflect$internal$Scopes$$hashtable()[i]);
            this.scala$reflect$internal$Scopes$$hashtable()[i] = e;
        }

        public <T extends Symbols.Symbol> T enter(T sym) {
            this.enterEntry(Scopes$class.scala$reflect$internal$Scopes$$newScopeEntry(this.scala$reflect$internal$Scopes$Scope$$$outer(), sym, this));
            return sym;
        }

        public void enterUnique(Symbols.Symbol sym) {
            Symbols.Symbol symbol = this.lookup(sym.name());
            Symbols.NoSymbol noSymbol = this.scala$reflect$internal$Scopes$Scope$$$outer().NoSymbol();
            boolean bl = !(symbol != null ? !symbol.equals(noSymbol) : noSymbol != null);
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(new Tuple2<String, String>(sym.fullLocationString(), this.lookup(sym.name()).fullLocationString())).toString());
            }
            this.enter(sym);
        }

        public <T extends Symbols.Symbol> T enterIfNew(T sym) {
            ScopeEntry existing = this.lookupEntry(sym.name());
            return (T)(existing == null ? this.enter(sym) : existing.sym());
        }

        private void createHash() {
            this.scala$reflect$internal$Scopes$$hashtable_$eq(new ScopeEntry[this.HASHSIZE()]);
            this.enterAllInHash(this.elems(), this.enterAllInHash$default$2());
        }

        private void enterAllInHash(ScopeEntry e, int n) {
            if (e != null) {
                if (n < 1000) {
                    this.enterAllInHash(e.next(), n + 1);
                    this.scala$reflect$internal$Scopes$Scope$$enterInHash(e);
                } else {
                    List entries = Nil$.MODULE$;
                    for (ScopeEntry ee = e; ee != null; ee = ee.next()) {
                        entries = new $colon$colon<Nothing$>((Nothing$)((Object)ee), entries);
                    }
                    List list2 = entries;
                    while (!((AbstractIterable)list2).isEmpty()) {
                        ScopeEntry scopeEntry = (ScopeEntry)((AbstractIterable)list2).head();
                        this.scala$reflect$internal$Scopes$Scope$$enterInHash(scopeEntry);
                        list2 = (List)((AbstractTraversable)list2).tail();
                    }
                }
            }
        }

        private int enterAllInHash$default$2() {
            return 0;
        }

        /*
         * Enabled aggressive block sorting
         */
        public void rehash(Symbols.Symbol sym, Names.Name newname) {
            if (this.scala$reflect$internal$Scopes$$hashtable() == null) return;
            int index = sym.name().start() & this.HASHMASK();
            ScopeEntry e1 = this.scala$reflect$internal$Scopes$$hashtable()[index];
            ScopeEntry e = null;
            if (e1 != null) {
                Symbols.Symbol symbol = e1.sym();
                if (!(symbol != null ? !symbol.equals(sym) : sym != null)) {
                    this.scala$reflect$internal$Scopes$$hashtable()[index] = e1.tail();
                    e = e1;
                } else {
                    while (e1.tail() != null) {
                        Symbols.Symbol symbol2 = e1.tail().sym();
                        if (!(symbol2 == null ? sym != null : !symbol2.equals(sym))) break;
                        e1 = e1.tail();
                    }
                    if (e1.tail() != null) {
                        e = e1.tail();
                        e1.tail_$eq(e.tail());
                    }
                }
            }
            if (e == null) return;
            int newindex = newname.start() & this.HASHMASK();
            e.tail_$eq(this.scala$reflect$internal$Scopes$$hashtable()[newindex]);
            this.scala$reflect$internal$Scopes$$hashtable()[newindex] = e;
        }

        /*
         * Unable to fully structure code
         */
        public void unlink(ScopeEntry e) {
            block9: {
                v0 = this.elems();
                if (v0 != null ? v0.equals(e) == false : e != null) break block9;
                this.elems_$eq(e.next());
                ** GOTO lbl11
            }
            e1 = this.elems();
            while (true) {
                block10: {
                    v1 = e1.next();
                    if (v1 != null ? v1.equals(e) == false : e != null) break block10;
                    e1.next_$eq(e.next());
lbl11:
                    // 2 sources

                    if (this.scala$reflect$internal$Scopes$$hashtable() != null) {
                        index = e.sym().name().start() & this.HASHMASK();
                        v2 = e1 = this.scala$reflect$internal$Scopes$$hashtable()[index];
                        if (!(v2 != null ? v2.equals(e) == false : e != null)) {
                            this.scala$reflect$internal$Scopes$$hashtable()[index] = e.tail();
                        } else {
                            while (true) {
                                v3 = e1.tail();
                                if (!(v3 != null ? v3.equals(e) == false : e != null)) {
                                    e1.tail_$eq(e.tail());
                                    break;
                                }
                                e1 = e1.tail();
                            }
                        }
                    }
                    this.flushElemsCache();
                    return;
                }
                var4_2 = var4_2.next();
            }
        }

        public void unlink(Symbols.Symbol sym) {
            ScopeEntry e = this.lookupEntry(sym.name());
            while (e != null) {
                Symbols.Symbol symbol = e.sym();
                if (!(symbol != null ? !symbol.equals(sym) : sym != null)) {
                    this.unlink(e);
                }
                e = this.lookupNextEntry(e);
            }
        }

        public Symbols.Symbol lookupModule(Names.Name name) {
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Symbols.Symbol x$2) {
                    return x$2.isModule();
                }
            };
            Iterator<Symbols.Symbol> iterator2 = this.lookupAll(name.toTermName());
            SymbolTable symbolTable = this.scala$reflect$internal$Scopes$Scope$$$outer();
            Option<Symbols.Symbol> option = iterator2.find((Function1<Symbols.Symbol, Object>)((Object)serializable));
            return !option.isEmpty() ? option.get() : symbolTable.NoSymbol();
        }

        public Symbols.Symbol lookupClass(Names.Name name) {
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Symbols.Symbol x$3) {
                    return x$3.isClass();
                }
            };
            Iterator<Symbols.Symbol> iterator2 = this.lookupAll(name.toTypeName());
            SymbolTable symbolTable = this.scala$reflect$internal$Scopes$Scope$$$outer();
            Option<Symbols.Symbol> option = iterator2.find((Function1<Symbols.Symbol, Object>)((Object)serializable));
            return !option.isEmpty() ? option.get() : symbolTable.NoSymbol();
        }

        public boolean containsName(Names.Name name) {
            return this.lookupEntry(name) != null;
        }

        public Symbols.Symbol lookup(Names.Name name) {
            Symbols.Symbol symbol;
            ScopeEntry e = this.lookupEntry(name);
            if (e == null) {
                symbol = this.scala$reflect$internal$Scopes$Scope$$$outer().NoSymbol();
            } else if (this.lookupNextEntry(e) == null) {
                symbol = e.sym();
            } else {
                List<Symbols.Symbol> alts = this.lookupAll(name).toList();
                this.scala$reflect$internal$Scopes$Scope$$$outer().devWarning((Function0<String>)((Object)new Serializable(this, name, alts){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Scope $outer;
                    private final Names.Name name$1;
                    private final List alts$1;

                    public final String apply() {
                        return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"scope lookup of ", " found multiple symbols: ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.name$1, this.$outer.scala$reflect$internal$Scopes$Scope$$alts_s$1(this.alts$1)}));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.name$1 = name$1;
                        this.alts$1 = alts$1;
                    }
                }));
                symbol = this.scala$reflect$internal$Scopes$Scope$$$outer().NoSymbol().newOverloaded(this.scala$reflect$internal$Scopes$Scope$$$outer().NoPrefix(), alts);
            }
            return symbol;
        }

        public Iterator<Symbols.Symbol> lookupAll(Names.Name name) {
            return new Iterator<Symbols.Symbol>(this, name){
                private ScopeEntry e;
                private final /* synthetic */ Scope $outer;

                public Iterator<Symbols.Symbol> seq() {
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

                public Iterator<Symbols.Symbol> take(int n) {
                    return Iterator$class.take(this, n);
                }

                public Iterator<Symbols.Symbol> drop(int n) {
                    return Iterator$class.drop(this, n);
                }

                public Iterator<Symbols.Symbol> slice(int from2, int until2) {
                    return Iterator$class.slice(this, from2, until2);
                }

                public <B> Iterator<B> map(Function1<Symbols.Symbol, B> f) {
                    return Iterator$class.map(this, f);
                }

                public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
                    return Iterator$class.$plus$plus(this, that);
                }

                public <B> Iterator<B> flatMap(Function1<Symbols.Symbol, GenTraversableOnce<B>> f) {
                    return Iterator$class.flatMap(this, f);
                }

                public Iterator<Symbols.Symbol> filter(Function1<Symbols.Symbol, Object> p) {
                    return Iterator$class.filter(this, p);
                }

                public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<Symbols.Symbol, B, Object> p) {
                    return Iterator$class.corresponds(this, that, p);
                }

                public Iterator<Symbols.Symbol> withFilter(Function1<Symbols.Symbol, Object> p) {
                    return Iterator$class.withFilter(this, p);
                }

                public Iterator<Symbols.Symbol> filterNot(Function1<Symbols.Symbol, Object> p) {
                    return Iterator$class.filterNot(this, p);
                }

                public <B> Iterator<B> collect(PartialFunction<Symbols.Symbol, B> pf) {
                    return Iterator$class.collect(this, pf);
                }

                public <B> Iterator<B> scanLeft(B z, Function2<B, Symbols.Symbol, B> op) {
                    return Iterator$class.scanLeft(this, z, op);
                }

                public <B> Iterator<B> scanRight(B z, Function2<Symbols.Symbol, B, B> op) {
                    return Iterator$class.scanRight(this, z, op);
                }

                public Iterator<Symbols.Symbol> takeWhile(Function1<Symbols.Symbol, Object> p) {
                    return Iterator$class.takeWhile(this, p);
                }

                public Tuple2<Iterator<Symbols.Symbol>, Iterator<Symbols.Symbol>> partition(Function1<Symbols.Symbol, Object> p) {
                    return Iterator$class.partition(this, p);
                }

                public Tuple2<Iterator<Symbols.Symbol>, Iterator<Symbols.Symbol>> span(Function1<Symbols.Symbol, Object> p) {
                    return Iterator$class.span(this, p);
                }

                public Iterator<Symbols.Symbol> dropWhile(Function1<Symbols.Symbol, Object> p) {
                    return Iterator$class.dropWhile(this, p);
                }

                public <B> Iterator<Tuple2<Symbols.Symbol, B>> zip(Iterator<B> that) {
                    return Iterator$class.zip(this, that);
                }

                public <A1> Iterator<A1> padTo(int len, A1 elem) {
                    return Iterator$class.padTo(this, len, elem);
                }

                public Iterator<Tuple2<Symbols.Symbol, Object>> zipWithIndex() {
                    return Iterator$class.zipWithIndex(this);
                }

                public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
                    return Iterator$class.zipAll(this, that, thisElem, thatElem);
                }

                public <U> void foreach(Function1<Symbols.Symbol, U> f) {
                    Iterator$class.foreach(this, f);
                }

                public boolean forall(Function1<Symbols.Symbol, Object> p) {
                    return Iterator$class.forall(this, p);
                }

                public boolean exists(Function1<Symbols.Symbol, Object> p) {
                    return Iterator$class.exists(this, p);
                }

                public boolean contains(Object elem) {
                    return Iterator$class.contains(this, elem);
                }

                public Option<Symbols.Symbol> find(Function1<Symbols.Symbol, Object> p) {
                    return Iterator$class.find(this, p);
                }

                public int indexWhere(Function1<Symbols.Symbol, Object> p) {
                    return Iterator$class.indexWhere(this, p);
                }

                public <B> int indexOf(B elem) {
                    return Iterator$class.indexOf(this, elem);
                }

                public BufferedIterator<Symbols.Symbol> buffered() {
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

                public Tuple2<Iterator<Symbols.Symbol>, Iterator<Symbols.Symbol>> duplicate() {
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

                public Traversable<Symbols.Symbol> toTraversable() {
                    return Iterator$class.toTraversable(this);
                }

                public Iterator<Symbols.Symbol> toIterator() {
                    return Iterator$class.toIterator(this);
                }

                public Stream<Symbols.Symbol> toStream() {
                    return Iterator$class.toStream(this);
                }

                public String toString() {
                    return Iterator$class.toString(this);
                }

                public <B> int sliding$default$2() {
                    return Iterator$class.sliding$default$2(this);
                }

                public List<Symbols.Symbol> reversed() {
                    return TraversableOnce$class.reversed(this);
                }

                public int size() {
                    return TraversableOnce$class.size(this);
                }

                public boolean nonEmpty() {
                    return TraversableOnce$class.nonEmpty(this);
                }

                public int count(Function1<Symbols.Symbol, Object> p) {
                    return TraversableOnce$class.count(this, p);
                }

                public <B> Option<B> collectFirst(PartialFunction<Symbols.Symbol, B> pf) {
                    return TraversableOnce$class.collectFirst(this, pf);
                }

                public <B> B $div$colon(B z, Function2<B, Symbols.Symbol, B> op) {
                    return (B)TraversableOnce$class.$div$colon(this, z, op);
                }

                public <B> B $colon$bslash(B z, Function2<Symbols.Symbol, B, B> op) {
                    return (B)TraversableOnce$class.$colon$bslash(this, z, op);
                }

                public <B> B foldLeft(B z, Function2<B, Symbols.Symbol, B> op) {
                    return (B)TraversableOnce$class.foldLeft(this, z, op);
                }

                public <B> B foldRight(B z, Function2<Symbols.Symbol, B, B> op) {
                    return (B)TraversableOnce$class.foldRight(this, z, op);
                }

                public <B> B reduceLeft(Function2<B, Symbols.Symbol, B> op) {
                    return (B)TraversableOnce$class.reduceLeft(this, op);
                }

                public <B> B reduceRight(Function2<Symbols.Symbol, B, B> op) {
                    return (B)TraversableOnce$class.reduceRight(this, op);
                }

                public <B> Option<B> reduceLeftOption(Function2<B, Symbols.Symbol, B> op) {
                    return TraversableOnce$class.reduceLeftOption(this, op);
                }

                public <B> Option<B> reduceRightOption(Function2<Symbols.Symbol, B, B> op) {
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

                public <B> B aggregate(Function0<B> z, Function2<B, Symbols.Symbol, B> seqop, Function2<B, B, B> combop) {
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

                public List<Symbols.Symbol> toList() {
                    return TraversableOnce$class.toList(this);
                }

                public Iterable<Symbols.Symbol> toIterable() {
                    return TraversableOnce$class.toIterable(this);
                }

                public Seq<Symbols.Symbol> toSeq() {
                    return TraversableOnce$class.toSeq(this);
                }

                public IndexedSeq<Symbols.Symbol> toIndexedSeq() {
                    return TraversableOnce$class.toIndexedSeq(this);
                }

                public <B> Buffer<B> toBuffer() {
                    return TraversableOnce$class.toBuffer(this);
                }

                public <B> Set<B> toSet() {
                    return TraversableOnce$class.toSet(this);
                }

                public Vector<Symbols.Symbol> toVector() {
                    return TraversableOnce$class.toVector(this);
                }

                public <Col> Col to(CanBuildFrom<Nothing$, Symbols.Symbol, Col> cbf) {
                    return (Col)TraversableOnce$class.to(this, cbf);
                }

                public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Symbols.Symbol, Tuple2<T, U>> ev) {
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

                private ScopeEntry e() {
                    return this.e;
                }

                private void e_$eq(ScopeEntry x$1) {
                    this.e = x$1;
                }

                public boolean hasNext() {
                    return this.e() != null;
                }

                public Symbols.Symbol next() {
                    try {
                        return this.e().sym();
                    }
                    finally {
                        this.e_$eq(this.$outer.lookupNextEntry(this.e()));
                    }
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    TraversableOnce$class.$init$(this);
                    Iterator$class.$init$(this);
                    this.e = $outer.lookupEntry(name$2);
                }
            };
        }

        public Iterator<ScopeEntry> lookupAllEntries(Names.Name name) {
            return new Iterator<ScopeEntry>(this, name){
                private ScopeEntry e;
                private final /* synthetic */ Scope $outer;

                public Iterator<ScopeEntry> seq() {
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

                public Iterator<ScopeEntry> take(int n) {
                    return Iterator$class.take(this, n);
                }

                public Iterator<ScopeEntry> drop(int n) {
                    return Iterator$class.drop(this, n);
                }

                public Iterator<ScopeEntry> slice(int from2, int until2) {
                    return Iterator$class.slice(this, from2, until2);
                }

                public <B> Iterator<B> map(Function1<ScopeEntry, B> f) {
                    return Iterator$class.map(this, f);
                }

                public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
                    return Iterator$class.$plus$plus(this, that);
                }

                public <B> Iterator<B> flatMap(Function1<ScopeEntry, GenTraversableOnce<B>> f) {
                    return Iterator$class.flatMap(this, f);
                }

                public Iterator<ScopeEntry> filter(Function1<ScopeEntry, Object> p) {
                    return Iterator$class.filter(this, p);
                }

                public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<ScopeEntry, B, Object> p) {
                    return Iterator$class.corresponds(this, that, p);
                }

                public Iterator<ScopeEntry> withFilter(Function1<ScopeEntry, Object> p) {
                    return Iterator$class.withFilter(this, p);
                }

                public Iterator<ScopeEntry> filterNot(Function1<ScopeEntry, Object> p) {
                    return Iterator$class.filterNot(this, p);
                }

                public <B> Iterator<B> collect(PartialFunction<ScopeEntry, B> pf) {
                    return Iterator$class.collect(this, pf);
                }

                public <B> Iterator<B> scanLeft(B z, Function2<B, ScopeEntry, B> op) {
                    return Iterator$class.scanLeft(this, z, op);
                }

                public <B> Iterator<B> scanRight(B z, Function2<ScopeEntry, B, B> op) {
                    return Iterator$class.scanRight(this, z, op);
                }

                public Iterator<ScopeEntry> takeWhile(Function1<ScopeEntry, Object> p) {
                    return Iterator$class.takeWhile(this, p);
                }

                public Tuple2<Iterator<ScopeEntry>, Iterator<ScopeEntry>> partition(Function1<ScopeEntry, Object> p) {
                    return Iterator$class.partition(this, p);
                }

                public Tuple2<Iterator<ScopeEntry>, Iterator<ScopeEntry>> span(Function1<ScopeEntry, Object> p) {
                    return Iterator$class.span(this, p);
                }

                public Iterator<ScopeEntry> dropWhile(Function1<ScopeEntry, Object> p) {
                    return Iterator$class.dropWhile(this, p);
                }

                public <B> Iterator<Tuple2<ScopeEntry, B>> zip(Iterator<B> that) {
                    return Iterator$class.zip(this, that);
                }

                public <A1> Iterator<A1> padTo(int len, A1 elem) {
                    return Iterator$class.padTo(this, len, elem);
                }

                public Iterator<Tuple2<ScopeEntry, Object>> zipWithIndex() {
                    return Iterator$class.zipWithIndex(this);
                }

                public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
                    return Iterator$class.zipAll(this, that, thisElem, thatElem);
                }

                public <U> void foreach(Function1<ScopeEntry, U> f) {
                    Iterator$class.foreach(this, f);
                }

                public boolean forall(Function1<ScopeEntry, Object> p) {
                    return Iterator$class.forall(this, p);
                }

                public boolean exists(Function1<ScopeEntry, Object> p) {
                    return Iterator$class.exists(this, p);
                }

                public boolean contains(Object elem) {
                    return Iterator$class.contains(this, elem);
                }

                public Option<ScopeEntry> find(Function1<ScopeEntry, Object> p) {
                    return Iterator$class.find(this, p);
                }

                public int indexWhere(Function1<ScopeEntry, Object> p) {
                    return Iterator$class.indexWhere(this, p);
                }

                public <B> int indexOf(B elem) {
                    return Iterator$class.indexOf(this, elem);
                }

                public BufferedIterator<ScopeEntry> buffered() {
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

                public Tuple2<Iterator<ScopeEntry>, Iterator<ScopeEntry>> duplicate() {
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

                public Traversable<ScopeEntry> toTraversable() {
                    return Iterator$class.toTraversable(this);
                }

                public Iterator<ScopeEntry> toIterator() {
                    return Iterator$class.toIterator(this);
                }

                public Stream<ScopeEntry> toStream() {
                    return Iterator$class.toStream(this);
                }

                public String toString() {
                    return Iterator$class.toString(this);
                }

                public <B> int sliding$default$2() {
                    return Iterator$class.sliding$default$2(this);
                }

                public List<ScopeEntry> reversed() {
                    return TraversableOnce$class.reversed(this);
                }

                public int size() {
                    return TraversableOnce$class.size(this);
                }

                public boolean nonEmpty() {
                    return TraversableOnce$class.nonEmpty(this);
                }

                public int count(Function1<ScopeEntry, Object> p) {
                    return TraversableOnce$class.count(this, p);
                }

                public <B> Option<B> collectFirst(PartialFunction<ScopeEntry, B> pf) {
                    return TraversableOnce$class.collectFirst(this, pf);
                }

                public <B> B $div$colon(B z, Function2<B, ScopeEntry, B> op) {
                    return (B)TraversableOnce$class.$div$colon(this, z, op);
                }

                public <B> B $colon$bslash(B z, Function2<ScopeEntry, B, B> op) {
                    return (B)TraversableOnce$class.$colon$bslash(this, z, op);
                }

                public <B> B foldLeft(B z, Function2<B, ScopeEntry, B> op) {
                    return (B)TraversableOnce$class.foldLeft(this, z, op);
                }

                public <B> B foldRight(B z, Function2<ScopeEntry, B, B> op) {
                    return (B)TraversableOnce$class.foldRight(this, z, op);
                }

                public <B> B reduceLeft(Function2<B, ScopeEntry, B> op) {
                    return (B)TraversableOnce$class.reduceLeft(this, op);
                }

                public <B> B reduceRight(Function2<ScopeEntry, B, B> op) {
                    return (B)TraversableOnce$class.reduceRight(this, op);
                }

                public <B> Option<B> reduceLeftOption(Function2<B, ScopeEntry, B> op) {
                    return TraversableOnce$class.reduceLeftOption(this, op);
                }

                public <B> Option<B> reduceRightOption(Function2<ScopeEntry, B, B> op) {
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

                public <B> B aggregate(Function0<B> z, Function2<B, ScopeEntry, B> seqop, Function2<B, B, B> combop) {
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

                public List<ScopeEntry> toList() {
                    return TraversableOnce$class.toList(this);
                }

                public Iterable<ScopeEntry> toIterable() {
                    return TraversableOnce$class.toIterable(this);
                }

                public Seq<ScopeEntry> toSeq() {
                    return TraversableOnce$class.toSeq(this);
                }

                public IndexedSeq<ScopeEntry> toIndexedSeq() {
                    return TraversableOnce$class.toIndexedSeq(this);
                }

                public <B> Buffer<B> toBuffer() {
                    return TraversableOnce$class.toBuffer(this);
                }

                public <B> Set<B> toSet() {
                    return TraversableOnce$class.toSet(this);
                }

                public Vector<ScopeEntry> toVector() {
                    return TraversableOnce$class.toVector(this);
                }

                public <Col> Col to(CanBuildFrom<Nothing$, ScopeEntry, Col> cbf) {
                    return (Col)TraversableOnce$class.to(this, cbf);
                }

                public <T, U> Map<T, U> toMap(Predef$.less.colon.less<ScopeEntry, Tuple2<T, U>> ev) {
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

                private ScopeEntry e() {
                    return this.e;
                }

                private void e_$eq(ScopeEntry x$1) {
                    this.e = x$1;
                }

                public boolean hasNext() {
                    return this.e() != null;
                }

                public ScopeEntry next() {
                    try {
                        return this.e();
                    }
                    finally {
                        this.e_$eq(this.$outer.lookupNextEntry(this.e()));
                    }
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    TraversableOnce$class.$init$(this);
                    Iterator$class.$init$(this);
                    this.e = $outer.lookupEntry(name$3);
                }
            };
        }

        public Iterator<ScopeEntry> lookupUnshadowedEntries(Names.Name name) {
            ScopeEntry scopeEntry = this.lookupEntry(name);
            Iterator<Object> iterator2 = scopeEntry == null ? package$.MODULE$.Iterator().empty() : this.lookupAllEntries(name).filter((Function1<ScopeEntry, Object>)((Object)new Serializable(this, scopeEntry){
                public static final long serialVersionUID = 0L;
                private final ScopeEntry x1$1;

                /*
                 * Enabled force condition propagation
                 * Lifted jumps to return sites
                 */
                public final boolean apply(ScopeEntry e1) {
                    if (this.x1$1 == e1) return true;
                    if (this.x1$1.depth() != e1.depth()) return false;
                    Symbols.Symbol symbol = this.x1$1.sym();
                    Symbols.Symbol symbol2 = e1.sym();
                    if (symbol == null) {
                        if (symbol2 == null) return false;
                        return true;
                    } else if (symbol.equals(symbol2)) return false;
                    return true;
                }
                {
                    this.x1$1 = x1$1;
                }
            }));
            return iterator2;
        }

        /*
         * WARNING - void declaration
         */
        public ScopeEntry lookupEntry(Names.Name name) {
            void var2_2;
            ScopeEntry e;
            if (this.scala$reflect$internal$Scopes$$hashtable() != null) {
                for (e = this.scala$reflect$internal$Scopes$$hashtable()[name.start() & this.HASHMASK()]; e != null; e = e.tail()) {
                    Names.Name name2 = e.sym().name();
                    if (name2 == null ? name != null : !name2.equals(name)) {
                        continue;
                    }
                    break;
                }
            } else {
                for (e = this.elems(); e != null; e = e.next()) {
                    Names.Name name3 = e.sym().name();
                    if (name3 == null ? name != null : !name3.equals(name)) {
                        continue;
                    }
                    break;
                }
            }
            return var2_2;
        }

        /*
         * WARNING - void declaration
         */
        public ScopeEntry lookupNextEntry(ScopeEntry entry) {
            void var2_2;
            ScopeEntry e = entry;
            if (this.scala$reflect$internal$Scopes$$hashtable() != null) {
                while ((e = e.tail()) != null) {
                    Names.Name name = e.sym().name();
                    Names.Name name2 = entry.sym().name();
                    if (name == null ? name2 != null : !name.equals(name2)) {
                        continue;
                    }
                    break;
                }
            } else {
                while ((e = e.next()) != null) {
                    Names.Name name = e.sym().name();
                    Names.Name name3 = entry.sym().name();
                    if (name != null ? !name.equals(name3) : name3 != null) continue;
                    break;
                }
            }
            return var2_2;
        }

        public boolean isSameScope(Scope other) {
            return this.size() == other.size() && this.isSubScope(other) && other.isSubScope(this);
        }

        public boolean isSubScope(Scope other) {
            return other.toList().forall((Function1<Symbols.Symbol, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Scope $outer;

                public final boolean apply(Symbols.Symbol sym) {
                    return this.$outer.scala$reflect$internal$Scopes$Scope$$scopeContainsSym$1(sym);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        @Override
        public List<Symbols.Symbol> toList() {
            if (this.elemsCache() == null) {
                List symbols = Nil$.MODULE$;
                int count2 = 0;
                for (ScopeEntry e = this.elems(); e != null; e = e.next()) {
                    Scope scope = e.owner();
                    if (scope == null || !scope.equals(this)) break;
                    ++count2;
                    symbols = symbols.$colon$colon(e.sym());
                }
                this.elemsCache_$eq(symbols);
                this.cachedSize_$eq(count2);
            }
            return this.elemsCache();
        }

        public List<Symbols.Symbol> sorted() {
            return this.toList();
        }

        public int nestingLevel() {
            return this.scala$reflect$internal$Scopes$$nestinglevel();
        }

        @Override
        public Iterator<Symbols.Symbol> iterator() {
            return this.toList().iterator();
        }

        @Override
        public <U> void foreach(Function1<Symbols.Symbol, U> p) {
            List list2 = this.toList();
            while (!((AbstractIterable)list2).isEmpty()) {
                p.apply((Symbols.Symbol)((AbstractIterable)list2).head());
                list2 = (List)list2.tail();
            }
        }

        @Override
        public Scope filterNot(Function1<Symbols.Symbol, Object> p) {
            return this.toList().exists(p) ? this.scala$reflect$internal$Scopes$Scope$$$outer().newScopeWith((Seq)this.toList().filterNot((Function1)p)) : this;
        }

        @Override
        public Scope filter(Function1<Symbols.Symbol, Object> p) {
            return this.toList().forall(p) ? this : this.scala$reflect$internal$Scopes$Scope$$$outer().newScopeWith((Seq)this.toList().filter((Function1)p));
        }

        public List<Symbols.Symbol> reverse() {
            return this.toList().reverse();
        }

        @Override
        public String mkString(String start, String sep, String end) {
            return ((TraversableOnce)this.toList().map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final String apply(Symbols.Symbol x$4) {
                    return x$4.defString();
                }
            }, List$.MODULE$.canBuildFrom())).mkString(start, sep, end);
        }

        @Override
        public String toString() {
            return this.mkString("Scope{\n  ", ";\n  ", "\n}");
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Scopes$Scope$$$outer() {
            return this.$outer;
        }

        public final String scala$reflect$internal$Scopes$Scope$$alts_s$1(List alts$1) {
            return ((TraversableOnce)alts$1.map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final String apply(Symbols.Symbol s2) {
                    return s2.defString();
                }
            }, List$.MODULE$.canBuildFrom())).mkString(" <and> ");
        }

        private final boolean entryContainsSym$1(ScopeEntry e, Symbols.Symbol sym$2) {
            while (true) {
                block5: {
                    boolean bl;
                    block4: {
                        block3: {
                            if (e != null) break block3;
                            bl = false;
                            break block4;
                        }
                        Types.Type comparableInfo = sym$2.info().substThis(sym$2.owner(), e.sym().owner());
                        if (!e.sym().info().$eq$colon$eq(comparableInfo)) break block5;
                        bl = true;
                    }
                    return bl;
                }
                e = this.lookupNextEntry(e);
            }
        }

        public final boolean scala$reflect$internal$Scopes$Scope$$scopeContainsSym$1(Symbols.Symbol sym) {
            return this.entryContainsSym$1(this.lookupEntry(sym.name()), sym);
        }

        public Scope(SymbolTable $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            TraversableOnce$class.$init$(this);
            Parallelizable$class.$init$(this);
            TraversableLike$class.$init$(this);
            GenericTraversableTemplate$class.$init$(this);
            GenTraversable$class.$init$(this);
            Traversable$class.$init$(this);
            GenIterable$class.$init$(this);
            IterableLike$class.$init$(this);
            Iterable$class.$init$(this);
            this.scala$reflect$internal$Scopes$$nestinglevel = 0;
            this.scala$reflect$internal$Scopes$$hashtable = null;
            this.elemsCache = null;
            this.cachedSize = -1;
            this.HASHSIZE = 128;
            this.HASHMASK = 127;
            this.MIN_HASH = 8;
        }
    }

    public interface NameLookup {
        public Symbols.Symbol symbol();

        public boolean isSuccess();

        public /* synthetic */ Scopes scala$reflect$internal$Scopes$NameLookup$$$outer();
    }

    public class ScopeEntry {
        private final Symbols.Symbol sym;
        private final Scope owner;
        private ScopeEntry tail;
        private ScopeEntry next;
        public final /* synthetic */ SymbolTable $outer;

        public Symbols.Symbol sym() {
            return this.sym;
        }

        public Scope owner() {
            return this.owner;
        }

        public ScopeEntry tail() {
            return this.tail;
        }

        public void tail_$eq(ScopeEntry x$1) {
            this.tail = x$1;
        }

        public ScopeEntry next() {
            return this.next;
        }

        public void next_$eq(ScopeEntry x$1) {
            this.next = x$1;
        }

        public int depth() {
            return this.owner().nestingLevel();
        }

        public int hashCode() {
            return this.sym().name().start();
        }

        public String toString() {
            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " (depth=", ")"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.sym(), BoxesRunTime.boxToInteger(this.depth())}));
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Scopes$ScopeEntry$$$outer() {
            return this.$outer;
        }

        public ScopeEntry(SymbolTable $outer, Symbols.Symbol sym, Scope owner2) {
            this.sym = sym;
            this.owner = owner2;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            this.tail = null;
            this.next = null;
        }
    }

    public class ErrorScope
    extends Scope {
        public /* synthetic */ SymbolTable scala$reflect$internal$Scopes$ErrorScope$$$outer() {
            return this.$outer;
        }

        public ErrorScope(SymbolTable $outer, Symbols.Symbol owner2) {
            super($outer);
        }
    }

    public class LookupSucceeded
    implements NameLookup,
    Product,
    Serializable {
        private final Trees.Tree qualifier;
        private final Symbols.Symbol symbol;
        public final /* synthetic */ SymbolTable $outer;

        public Trees.Tree qualifier() {
            return this.qualifier;
        }

        @Override
        public Symbols.Symbol symbol() {
            return this.symbol;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        public LookupSucceeded copy(Trees.Tree qualifier, Symbols.Symbol symbol) {
            return new LookupSucceeded(this.scala$reflect$internal$Scopes$LookupSucceeded$$$outer(), qualifier, symbol);
        }

        public Trees.Tree copy$default$1() {
            return this.qualifier();
        }

        public Symbols.Symbol copy$default$2() {
            return this.symbol();
        }

        @Override
        public String productPrefix() {
            return "LookupSucceeded";
        }

        @Override
        public int productArity() {
            return 2;
        }

        @Override
        public Object productElement(int x$1) {
            StdAttachments.Attachable attachable;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 1: {
                    attachable = this.symbol();
                    break;
                }
                case 0: {
                    attachable = this.qualifier();
                }
            }
            return attachable;
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof LookupSucceeded;
        }

        public int hashCode() {
            return ScalaRunTime$.MODULE$._hashCode(this);
        }

        public String toString() {
            return ScalaRunTime$.MODULE$._toString(this);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object x$1) {
            if (this == x$1) return true;
            if (!(x$1 instanceof LookupSucceeded)) return false;
            if (((LookupSucceeded)x$1).scala$reflect$internal$Scopes$LookupSucceeded$$$outer() != this.scala$reflect$internal$Scopes$LookupSucceeded$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            LookupSucceeded lookupSucceeded = (LookupSucceeded)x$1;
            Trees.Tree tree = this.qualifier();
            Trees.Tree tree2 = lookupSucceeded.qualifier();
            if (tree == null) {
                if (tree2 != null) {
                    return false;
                }
            } else if (!((Object)tree).equals(tree2)) return false;
            Symbols.Symbol symbol = this.symbol();
            Symbols.Symbol symbol2 = lookupSucceeded.symbol();
            if (symbol == null) {
                if (symbol2 != null) {
                    return false;
                }
            } else if (!symbol.equals(symbol2)) return false;
            if (!lookupSucceeded.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Scopes$LookupSucceeded$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ Scopes scala$reflect$internal$Scopes$NameLookup$$$outer() {
            return this.scala$reflect$internal$Scopes$LookupSucceeded$$$outer();
        }

        public LookupSucceeded(SymbolTable $outer, Trees.Tree qualifier, Symbols.Symbol symbol) {
            this.qualifier = qualifier;
            this.symbol = symbol;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Scopes$NameLookup$class.$init$(this);
            Product$class.$init$(this);
        }
    }

    public class LookupAmbiguous
    implements NameLookup,
    Product,
    Serializable {
        private final String msg;
        public final /* synthetic */ SymbolTable $outer;

        @Override
        public boolean isSuccess() {
            return Scopes$NameLookup$class.isSuccess(this);
        }

        public String msg() {
            return this.msg;
        }

        @Override
        public Symbols.NoSymbol symbol() {
            return this.scala$reflect$internal$Scopes$LookupAmbiguous$$$outer().NoSymbol();
        }

        public LookupAmbiguous copy(String msg) {
            return new LookupAmbiguous(this.scala$reflect$internal$Scopes$LookupAmbiguous$$$outer(), msg);
        }

        public String copy$default$1() {
            return this.msg();
        }

        @Override
        public String productPrefix() {
            return "LookupAmbiguous";
        }

        @Override
        public int productArity() {
            return 1;
        }

        @Override
        public Object productElement(int x$1) {
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 0: 
            }
            return this.msg();
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof LookupAmbiguous;
        }

        public int hashCode() {
            return ScalaRunTime$.MODULE$._hashCode(this);
        }

        public String toString() {
            return ScalaRunTime$.MODULE$._toString(this);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object x$1) {
            if (this == x$1) return true;
            if (!(x$1 instanceof LookupAmbiguous)) return false;
            if (((LookupAmbiguous)x$1).scala$reflect$internal$Scopes$LookupAmbiguous$$$outer() != this.scala$reflect$internal$Scopes$LookupAmbiguous$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            LookupAmbiguous lookupAmbiguous = (LookupAmbiguous)x$1;
            String string2 = this.msg();
            String string3 = lookupAmbiguous.msg();
            if (string2 == null) {
                if (string3 != null) {
                    return false;
                }
            } else if (!string2.equals(string3)) return false;
            if (!lookupAmbiguous.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Scopes$LookupAmbiguous$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ Scopes scala$reflect$internal$Scopes$NameLookup$$$outer() {
            return this.scala$reflect$internal$Scopes$LookupAmbiguous$$$outer();
        }

        public LookupAmbiguous(SymbolTable $outer, String msg) {
            this.msg = msg;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Scopes$NameLookup$class.$init$(this);
            Product$class.$init$(this);
        }
    }

    public class LookupInaccessible
    implements NameLookup,
    Product,
    Serializable {
        private final Symbols.Symbol symbol;
        private final String msg;
        public final /* synthetic */ SymbolTable $outer;

        @Override
        public boolean isSuccess() {
            return Scopes$NameLookup$class.isSuccess(this);
        }

        @Override
        public Symbols.Symbol symbol() {
            return this.symbol;
        }

        public String msg() {
            return this.msg;
        }

        public LookupInaccessible copy(Symbols.Symbol symbol, String msg) {
            return new LookupInaccessible(this.scala$reflect$internal$Scopes$LookupInaccessible$$$outer(), symbol, msg);
        }

        public Symbols.Symbol copy$default$1() {
            return this.symbol();
        }

        public String copy$default$2() {
            return this.msg();
        }

        @Override
        public String productPrefix() {
            return "LookupInaccessible";
        }

        @Override
        public int productArity() {
            return 2;
        }

        @Override
        public Object productElement(int x$1) {
            Object object;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 1: {
                    object = this.msg();
                    break;
                }
                case 0: {
                    object = this.symbol();
                }
            }
            return object;
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof LookupInaccessible;
        }

        public int hashCode() {
            return ScalaRunTime$.MODULE$._hashCode(this);
        }

        public String toString() {
            return ScalaRunTime$.MODULE$._toString(this);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object x$1) {
            if (this == x$1) return true;
            if (!(x$1 instanceof LookupInaccessible)) return false;
            if (((LookupInaccessible)x$1).scala$reflect$internal$Scopes$LookupInaccessible$$$outer() != this.scala$reflect$internal$Scopes$LookupInaccessible$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            LookupInaccessible lookupInaccessible = (LookupInaccessible)x$1;
            Symbols.Symbol symbol = this.symbol();
            Symbols.Symbol symbol2 = lookupInaccessible.symbol();
            if (symbol == null) {
                if (symbol2 != null) {
                    return false;
                }
            } else if (!symbol.equals(symbol2)) return false;
            String string2 = this.msg();
            String string3 = lookupInaccessible.msg();
            if (string2 == null) {
                if (string3 != null) {
                    return false;
                }
            } else if (!string2.equals(string3)) return false;
            if (!lookupInaccessible.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Scopes$LookupInaccessible$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ Scopes scala$reflect$internal$Scopes$NameLookup$$$outer() {
            return this.scala$reflect$internal$Scopes$LookupInaccessible$$$outer();
        }

        public LookupInaccessible(SymbolTable $outer, Symbols.Symbol symbol, String msg) {
            this.symbol = symbol;
            this.msg = msg;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Scopes$NameLookup$class.$init$(this);
            Product$class.$init$(this);
        }
    }
}

