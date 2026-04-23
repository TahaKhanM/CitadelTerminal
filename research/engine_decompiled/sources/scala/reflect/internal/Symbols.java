/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import java.lang.constant.Constable;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Option$;
import scala.PartialFunction;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Predef$any2stringadd$;
import scala.Product;
import scala.Product$class;
import scala.ScalaReflectionException;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple2$mcII$sp;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.AbstractIterable;
import scala.collection.AbstractTraversable;
import scala.collection.BufferedIterator;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Iterator$class;
import scala.collection.Seq;
import scala.collection.SeqLike;
import scala.collection.SetLike;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.Set$;
import scala.collection.immutable.Stream;
import scala.collection.immutable.StringOps;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.Ordering$Int$;
import scala.package$;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Internals;
import scala.reflect.api.Symbols;
import scala.reflect.api.Symbols$ClassSymbolApi$class;
import scala.reflect.api.Symbols$MethodSymbolApi$class;
import scala.reflect.api.Symbols$ModuleSymbolApi$class;
import scala.reflect.api.Symbols$SymbolApi$class;
import scala.reflect.api.Symbols$TermSymbolApi$class;
import scala.reflect.api.Symbols$TypeSymbolApi$class;
import scala.reflect.api.TypeTags;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.AnnotationInfos$Annotatable$class;
import scala.reflect.internal.AnnotationInfos$AnnotationInfo$;
import scala.reflect.internal.Flags$;
import scala.reflect.internal.HasFlags;
import scala.reflect.internal.HasFlags$class;
import scala.reflect.internal.InfoTransformers;
import scala.reflect.internal.Names;
import scala.reflect.internal.NoPhase$;
import scala.reflect.internal.Phase;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.StdAttachments;
import scala.reflect.internal.StdAttachments$Attachable$class;
import scala.reflect.internal.StdAttachments$KnownDirectSubclassesCalled$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols$CyclicReference$;
import scala.reflect.internal.Symbols$ImplClassSymbol$class;
import scala.reflect.internal.Symbols$StubSymbol$class;
import scala.reflect.internal.Symbols$Symbol$;
import scala.reflect.internal.Symbols$SymbolKind$;
import scala.reflect.internal.Symbols$SymbolOps$;
import scala.reflect.internal.Symbols$TypeHistory$;
import scala.reflect.internal.SymbolsStats$;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$NoType$;
import scala.reflect.internal.Variance$;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;
import scala.reflect.internal.util.TraceSymbolActivity$class;
import scala.reflect.io.AbstractFile;
import scala.reflect.io.NoAbstractFile$;
import scala.reflect.macros.Attachments;
import scala.reflect.runtime.SynchronizedSymbols;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;
import scala.runtime.Statics;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001e}a!C\u0001\u0003!\u0003\r\t!CM\u000f\u0005\u001d\u0019\u00160\u001c2pYNT!a\u0001\u0003\u0002\u0011%tG/\u001a:oC2T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0007\u0001Qa\u0002\u0005\u0002\f\u00195\ta!\u0003\u0002\u000e\r\t1\u0011I\\=SK\u001a\u0004\"a\u0004\n\u000e\u0003AQ!!\u0005\u0003\u0002\u0007\u0005\u0004\u0018.\u0003\u0002\u0002!!)A\u0003\u0001C\u0001+\u00051A%\u001b8ji\u0012\"\u0012A\u0006\t\u0003\u0017]I!\u0001\u0007\u0004\u0003\tUs\u0017\u000e\u001e\u0005\b5\u0001\u0001\r\u0011\"\u0005\u001c\u0003\rIGm]\u000b\u00029A\u00111\"H\u0005\u0003=\u0019\u00111!\u00138u\u0011\u001d\u0001\u0003\u00011A\u0005\u0012\u0005\nq!\u001b3t?\u0012*\u0017\u000f\u0006\u0002\u0017E!91eHA\u0001\u0002\u0004a\u0012a\u0001=%c!1Q\u0005\u0001Q!\nq\tA!\u001b3tA!)q\u0005\u0001C\tQ\u00051a.\u001a=u\u0013\u0012$\u0012\u0001\b\u0005\bU\u0001\u0001\r\u0011\"\u0003,\u0003=y&/Z2veNLwN\u001c+bE2,W#\u0001\u0017\u0011\t5\u0012D\u0007H\u0007\u0002])\u0011q\u0006M\u0001\nS6lW\u000f^1cY\u0016T!!\r\u0004\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u00024]\t\u0019Q*\u00199\u0011\u0005U2T\"\u0001\u0001\u0007\u000b]\u0002\u0011\u0011\u0001\u001d\u0003\rMKXNY8m'!1\u0014\b\"!\u0005\n\u0012=\u0005CA\u001b;\r\u0019Y\u0004!!\u0001=i\t!2+_7c_2\u001cuN\u001c;fqR\f\u0005/[%na2\u001c2A\u000f\u0006>!\t)d(\u0003\u0002@%\tI1+_7c_2\f\u0005/\u001b\u0005\u0006\u0003j\"\tAQ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003eBQ\u0001\u0012\u001e\u0005\u0002\u0015\u000b!\"[:Ge\u0016,G+\u001a:n+\u00051\u0005CA\u0006H\u0013\tAeAA\u0004C_>dW-\u00198\t\u000b)SD\u0011A&\u0002\u0015\u0005\u001chI]3f)\u0016\u0014X.F\u0001M!\t)TJ\u0002\u0003O\u0001\u0001y%A\u0004$sK\u0016$VM]7Ts6\u0014w\u000e\\\n\u0007\u001bB\u000b\u0019P!\u0001\u0011\u0005U\nf\u0001\u0002*\u0001\u0001M\u0013!\u0002V3s[NKXNY8m'\r\tF\u0007\u0016\t\u0003kUK!A\u0016\n\u0003\u001bQ+'/\\*z[\n|G.\u00119j\u0011!A\u0016K!A!\u0002\u0013!\u0014!C5oSR|uO\\3s\u0011!Q\u0016K!A!\u0002\u0013Y\u0016aB5oSR\u0004vn\u001d\t\u0003kqK!!\u00180\u0003\u0011A{7/\u001b;j_:L!a\u0018\u0002\u0003\u0013A{7/\u001b;j_:\u001c\b\u0002C1R\u0005\u0003\u0005\u000b\u0011\u00022\u0002\u0011%t\u0017\u000e\u001e(b[\u0016\u0004\"!N2\n\u0005\u0011,'\u0001\u0003+fe6t\u0015-\\3\n\u0005\u0019\u0014!!\u0002(b[\u0016\u001c\bBB!R\t#\u0001\u0001\u000e\u0006\u0003QS*\\\u0007\"\u0002-h\u0001\u0004!\u0004\"\u0002.h\u0001\u0004Y\u0006\"B1h\u0001\u0004\u0011\u0007BB7RA\u0003&A'A\u0006`e\u00164WM]3oG\u0016$W\u0001B8R\u0001A\u0013!\u0003V=qK>37\t\\8oK\u0012\u001c\u00160\u001c2pY\"1\u0011/\u0015Q!\n\t\f\u0001b\u0018:bo:\fW.\u001a\u0005\u0006gF#\t\u0001^\u0001\be\u0006<h.Y7f+\u0005\u0011\u0007\"\u0002<R\t\u0003!\u0018\u0001\u00028b[\u0016DQ\u0001_)\u0005Be\f\u0001B\\1nK~#S-\u001d\u000b\u0003-iDQA^<A\u0002m\u0004\"!\u000e?\n\u0005u,'\u0001\u0002(b[\u0016Daa`)\u0005\u0006\u0005\u0005\u0011AC1t\u001d\u0006lW\rV=qKR\u0019!-a\u0001\t\r\u0005\u0015a\u00101\u0001|\u0003\u0005q\u0007BBA\u0005#\u0012\u0005S)A\u0004jgZ\u000bG.^3\t\r\u00055\u0011\u000b\"\u0011F\u0003)I7OV1sS\u0006\u0014G.\u001a\u0005\u0007\u0003#\tF\u0011I#\u0002\u0017%\u001cH+\u001a:n\u001b\u0006\u001c'o\u001c\u0005\u0007\u0003+\tF\u0011I#\u0002%%\u001c8)\u00199ukJ,GMV1sS\u0006\u0014G.\u001a\u0005\b\u00033\tF\u0011IA\u000e\u0003=\u0019w.\u001c9b]&|gnU=nE>dW#\u0001\u001b\t\u000f\u0005}\u0011\u000b\"\u0011\u0002\u001c\u0005YQn\u001c3vY\u0016\u001cE.Y:t\u0011\u0019\t\u0019#\u0015C!\u000b\u0006A\u0011n\u001d\"sS\u0012<W\r\u0003\u0004\u0002(E#\t%R\u0001\u0013SN,\u0015M\u001d7z\u0013:LG/[1mSj,G\r\u0003\u0004\u0002,E#\t%R\u0001\tSNlU\r\u001e5pI\"1\u0011qF)\u0005B\u0015\u000b\u0001\"[:N_\u0012,H.\u001a\u0005\u0007\u0003g\tF\u0011I#\u0002\u0019%\u001cxJ^3sY>\fG-\u001a3\t\r\u0005]\u0012\u000b\"\u0011F\u0003AI7OV1mk\u0016\u0004\u0016M]1nKR,'\u000f\u0003\u0004\u0002<E#\t%R\u0001\u0012SN\u001cV\r\u001e;feB\u000b'/Y7fi\u0016\u0014\bBBA #\u0012\u0005S)\u0001\u0006jg\u0006\u001b7-Z:t_JDa!a\u0011R\t\u0003*\u0015\u0001C5t\u000f\u0016$H/\u001a:\t\r\u0005\u001d\u0013\u000b\"\u0011F\u0003=I7\u000fR3gCVdGoR3ui\u0016\u0014\bBBA&#\u0012\u0005S)\u0001\u0005jgN+G\u000f^3s\u0011\u0019\ty%\u0015C!\u000b\u0006a\u0011n\u001d'pG\u0006dG)^7ns\"1\u00111K)\u0005B\u0015\u000b!#[:DY\u0006\u001c8oQ8ogR\u0014Xo\u0019;pe\"1\u0011qK)\u0005B\u0015\u000b!#[:NSbLgnQ8ogR\u0014Xo\u0019;pe\"1\u00111L)\u0005B\u0015\u000bQ\"[:D_:\u001cHO];di>\u0014\bBBA0#\u0012\u0005S)A\bjgB\u000b7m[1hK>\u0013'.Z2u\u0011\u001d\t\u0019'\u0015C!\u0003K\nQC]3t_24Xm\u0014<fe2|\u0017\rZ3e\r2\fw\r\u0006\u0003\u0002h\u0005U\u0004\u0003BA5\u0003_r1aCA6\u0013\r\tiGB\u0001\u0007!J,G-\u001a4\n\t\u0005E\u00141\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u00055d\u0001\u0003\u0005\u0002x\u0005\u0005\u0004\u0019AA=\u0003\u00111G.Y4\u0011\u0007-\tY(C\u0002\u0002~\u0019\u0011A\u0001T8oO\"9\u0011\u0011Q)\u0005\u0002\u0005m\u0011A\u0003:fM\u0016\u0014XM\\2fI\"9\u0011QQ)\u0005\u0002\u0005\u001d\u0015A\u0004:fM\u0016\u0014XM\\2fI~#S-\u001d\u000b\u0004-\u0005%\u0005bBAF\u0003\u0007\u0003\r\u0001N\u0001\u0002q\"9\u0011qR)\u0005\u0002\u0005E\u0015\u0001E3ySN$XM\u001c;jC2\u0014u.\u001e8e+\t\t\u0019\nE\u00026\u0003+KA!a&\u0002\u001a\nQA+\u001f9f\u0005>,h\u000eZ:\n\u0007\u0005m%AA\u0003UsB,7\u000fC\u0004\u0002 F#\t!!)\u0002\u001f\rdwN\\3Ts6\u0014w\u000e\\%na2$R\u0001UAR\u0003OCq!!*\u0002\u001e\u0002\u0007A'A\u0003po:,'\u000f\u0003\u0005\u0002*\u0006u\u0005\u0019AA=\u0003!qWm\u001e$mC\u001e\u001c\bbBAW#\u0012\u0005\u0011qV\u0001\u000eG>\u0004\u00180\u0011;ueN4%o\\7\u0015\t\u0005E\u00161W\u0007\u0002#\"9\u0011QWAV\u0001\u0004\u0001\u0016\u0001C8sS\u001eLg.\u00197\t\u0013\u0005e\u0016K1A\u0005\n\u0005m\u0016a\u0004<bY&$\u0017\t\\5bg\u001ac\u0017mZ:\u0016\u0005\u0005e\u0004\u0002CA`#\u0002\u0006I!!\u001f\u0002!Y\fG.\u001b3BY&\f7O\u00127bON\u0004\u0003bBAb#\u0012\u0005\u00131D\u0001\u0006C2L\u0017m\u001d\u0005\b\u0003\u000f\fF\u0011AAe\u0003!\u0019X\r^!mS\u0006\u001cHc\u0001)\u0002L\"9\u00111YAc\u0001\u0004!\u0004bBAh#\u0012\u0005\u00131D\u0001\f_V$XM]*pkJ\u001cW\rC\u0004\u0002TF#\t!!6\u0002\u001dM,G/T8ek2,7\t\\1tgR\u0019\u0001+a6\t\u000f\u0005e\u0017\u0011\u001ba\u0001i\u0005)1\r\\1{u\"9\u0011Q\\)\u0005\u0002\u0005}\u0017aD:fi2\u000b'0_!dG\u0016\u001c8o\u001c:\u0015\u0007A\u000b\t\u000fC\u0004\u0002d\u0006m\u0007\u0019\u0001\u001b\u0002\u0007MLX\u000eC\u0004\u0002hF#\t%a\u0007\u0002\u00191\f'0_!dG\u0016\u001c8o\u001c:\t\u000f\u0005-\u0018\u000b\"\u0011\u0002n\u0006QQ\r\u001f9b]\u0012t\u0015-\\3\u0015\u0007Y\ty\u000fC\u0004\u0002r\u0006%\b\u0019\u0001\u001b\u0002\t\t\f7/\u001a\t\u0004k\u0005Uh!CA|\u0001A\u0005\u0019\u0013AA}\u0005)1%/Z3Ts6\u0014w\u000e\\\n\u0004\u0003k$\u0004\u0002CA\u007f\u0003k4\t!a@\u0002\r=\u0014\u0018nZ5o+\t\t9\u0007E\u00026\u0005\u0007IAA!\u0002\u0003\b\t\tbI]3f)\u0016\u0014XnU=nE>d\u0017\t]5\n\u0007\t%\u0001CA\u0005J]R,'O\\1mg\"I!QB'\u0003\u0002\u0003\u0006IAY\u0001\u0006]\u0006lW\r\r\u0005\u000b\u0005#i%\u0011!S\u0001\n\tM\u0011A\u0002<bYV,\u0007\u0007E\u0003\f\u0005+\u0011I\"C\u0002\u0003\u0018\u0019\u0011\u0001\u0002\u00102z]\u0006lWM\u0010\t\u0004\u0017\tm\u0011b\u0001B\u000f\r\t\u0019\u0011I\\=\t\u0015\u0005uXJ!b\u0001\n\u0003\ty\u0010\u0003\u0006\u0003$5\u0013\t\u0011)A\u0005\u0003O\nqa\u001c:jO&t\u0007\u0005\u0003\u0004B\u001b\u0012\u0005!q\u0005\u000b\b\u0019\n%\"1\u0006B\u0017\u0011\u001d\u0011iA!\nA\u0002\tD\u0011B!\u0005\u0003&\u0011\u0005\rAa\u0005\t\u0011\u0005u(Q\u0005a\u0001\u0003OBQ\u0001R'\u0005F\u0015CQAS'\u0005F-CqA!\u000eN\t\u0003\u00119$A\u0003wC2,X-\u0006\u0002\u0003\u001a!1!1\b\u001e\u0005\u0002\u0015\u000b!\"[:Ge\u0016,G+\u001f9f\u0011\u001d\u0011yD\u000fC\u0001\u0005\u0003\n!\"Y:Ge\u0016,G+\u001f9f+\t\u0011\u0019\u0005E\u00026\u0005\u000b2aAa\u0012\u0001\u0001\t%#A\u0004$sK\u0016$\u0016\u0010]3Ts6\u0014w\u000e\\\n\t\u0005\u000b\u0012Y%a=\u00040B\u0019QG!\u0014\u0007\r\t=\u0003\u0001\u0001B)\u0005)!\u0016\u0010]3TW>dW-\\\n\u0005\u0005\u001b\u0012\u0019\u0006E\u00026\u0005+2qAa\u0016\u0001\u0003\u0003\u0011IF\u0001\u0006UsB,7+_7c_2\u001cRA!\u00165\u00057\u00022!\u000eB/\u0013\r\u0011yF\u0005\u0002\u000e)f\u0004XmU=nE>d\u0017\t]5\t\u0013a\u0013)F!A!\u0002\u0013!\u0004\"\u0003.\u0003V\t\u0005\t\u0015!\u0003\\\u0011)\t'Q\u000bB\u0001B\u0003%!q\r\t\u0004k\t%\u0014b\u0001B6K\nAA+\u001f9f\u001d\u0006lW\r\u0003\u0005B\u0005+\"\t\u0002\u0001B8)!\u0011\u0019F!\u001d\u0003t\tU\u0004B\u0002-\u0003n\u0001\u0007A\u0007\u0003\u0004[\u0005[\u0002\ra\u0017\u0005\bC\n5\u0004\u0019\u0001B4\u0011!\t(Q\u000bQ!\n\t\u001dDaB8\u0003V\t\u0005!1P\t\u0005\u0005{\u0012\u0019\u0006E\u0002\f\u0005\u007fJ1A!!\u0007\u0005\u0011qU\u000f\u001c7\t\u000fM\u0014)\u0006\"\u0001\u0003\u0006V\u0011!q\r\u0005\bm\nUC\u0011\u0001BC\u0011\u001dy(Q\u000bC\u0003\u0005\u0017#BAa\u001a\u0003\u000e\"9\u0011Q\u0001BE\u0001\u0004Y\bb\u0002BI\u0005+\"\t%R\u0001\u000fSNtuN\\\"mCN\u001cH+\u001f9f\u0011!\t\u0019G!\u0016\u0005B\tUE\u0003BA4\u0005/C\u0001\"a\u001e\u0003\u0014\u0002\u0007\u0011\u0011\u0010\u0005\u000b\u00057\u0013)\u00061A\u0005\n\tu\u0015A\u0003;zG>t7)Y2iKV\u0011!q\u0014\t\u0004k\t\u0005\u0016\u0002\u0002BR\u00033\u0013A\u0001V=qK\"Q!q\u0015B+\u0001\u0004%IA!+\u0002\u001dQL8m\u001c8DC\u000eDWm\u0018\u0013fcR\u0019aCa+\t\u0013\r\u0012)+!AA\u0002\t}\u0005\"\u0003BX\u0005+\u0002\u000b\u0015\u0002BP\u0003-!\u0018pY8o\u0007\u0006\u001c\u0007.\u001a\u0011\t\u0013\tM&Q\u000ba\u0001\n\u0013Y\u0012A\u0003;zG>t'+\u001e8JI\"Q!q\u0017B+\u0001\u0004%IA!/\u0002\u001dQL8m\u001c8Sk:LEm\u0018\u0013fcR\u0019aCa/\t\u0011\r\u0012),!AA\u0002qA\u0001Ba0\u0003V\u0001\u0006K\u0001H\u0001\fif\u001cwN\u001c*v]&#\u0007\u0005\u0003\u0007\u0003D\nU\u0003\u0019!a\u0001\n\u0013\u0011i*\u0001\u0005ua\u0016\u001c\u0015m\u00195f\u00111\u00119M!\u0016A\u0002\u0003\u0007I\u0011\u0002Be\u00031!\b/Z\"bG\",w\fJ3r)\r1\"1\u001a\u0005\nG\t\u0015\u0017\u0011!a\u0001\u0005?C\u0011Ba4\u0003V\u0001\u0006KAa(\u0002\u0013Q\u0004XmQ1dQ\u0016\u0004\u0003\"\u0003Bj\u0005+\u0002\r\u0011\"\u0003\u001c\u0003%!\b/\u001a)fe&|G\r\u0003\u0006\u0003X\nU\u0003\u0019!C\u0005\u00053\fQ\u0002\u001e9f!\u0016\u0014\u0018n\u001c3`I\u0015\fHc\u0001\f\u0003\\\"A1E!6\u0002\u0002\u0003\u0007A\u0004\u0003\u0005\u0003`\nU\u0003\u0015)\u0003\u001d\u0003)!\b/\u001a)fe&|G\r\t\u0005\b\u0005G\u0014)\u0006\"\u0011F\u00039I7/\u00112tiJ\f7\r\u001e+za\u0016DqAa:\u0003V\u0011\u0005S)A\bjg\u000e{g\u000e\u001e:bm\u0006\u0014\u0018.\u00198u\u0011\u001d\u0011YO!\u0016\u0005B\u0015\u000b1\"[:D_Z\f'/[1oi\"9!q\u001eB+\t\u0003*\u0015\u0001F5t\u000bbL7\u000f^3oi&\fG\u000e\\=C_VtG\rC\u0004\u0003t\nUC\u0011I#\u0002\u001f%\u001cH+\u001f9f!\u0006\u0014\u0018-\\3uKJDqAa>\u0003V\u0011\u0005S)A\fjgRK\b/\u001a)be\u0006lW\r^3s\u001fJ\u001c6n\u001c7f[\"A\u0011q\u0012B+\t\u0003\u0011i\nC\u0004y\u0005+\"\tE!@\u0015\u0007Y\u0011y\u0010\u0003\u0004w\u0005w\u0004\ra\u001f\u0005\t\u0007\u0007\u0011)\u0006\"\u0003\u0003\u001e\u0006Ia.Z<Qe\u00164\u0017\u000e\u001f\u0005\t\u0007\u000f\u0011)\u0006\"\u0003\u0004\n\u0005Qa.Z<UsB,'+\u001a4\u0015\t\t}51\u0002\u0005\t\u0007\u001b\u0019)\u00011\u0001\u0004\u0010\u0005)A/\u0019:hgB11\u0011CB\f\u0005?s1aCB\n\u0013\r\u0019)BB\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\u0019Iba\u0007\u0003\t1K7\u000f\u001e\u0006\u0004\u0007+1\u0001\u0002CB\u0010\u0005+\"\tE!(\u0002\u0015Q\u0004Xm\u0018\u0013uS6,7\u000f\u0003\u0005\u0004$\tUC\u0011\tBO\u0003=!\u0018\u0010]3D_:\u001cHO];di>\u0014\b\u0002CB\u0014\u0005+\"\tE!(\u0002\u000bQ\u0004X\rS&\t\u000f\r-\"Q\u000bC\u0005\u000b\u0006)B/_2p]\u000e\u000b7\r[3OK\u0016$7/\u00169eCR,\u0007\u0002CB\u0018\u0005+\"Ia!\r\u0002\u001bM,G\u000fV=d_:\u001c\u0015m\u00195f)\r121\u0007\u0005\t\u0007k\u0019i\u00031\u0001\u0003 \u0006)A/_2p]\"91\u0011\bB+\t\u0013)\u0012\u0001F7bs\n,W\u000b\u001d3bi\u0016$\u0016\u0010]3DC\u000eDW\rC\u0004\u0004>\tUC\u0011B\u000b\u0002\u001fU\u0004H-\u0019;f)f\u0004XmQ1dQ\u0016D\u0001b!\u0011\u0003V\u0011\u000531I\u0001\tS:4wn\u0018\u0013fcR\u0019ac!\u0012\t\u0011\r\u001d3q\ba\u0001\u0005?\u000b!\u0001\u001e9\t\u0011\r-#Q\u000bC#\u0007\u001b\n1#[:O_:\u0014u\u000e\u001e;p[N+(m\u00117bgN$2ARB(\u0011\u001d\u0019\tf!\u0013A\u0002Q\nA\u0001\u001e5bi\"A1Q\u000bB+\t\u0003\u001a9&A\u0003sKN,G\u000f\u0006\u0003\u0004Z\rmSB\u0001B+\u0011!\u0019ifa\u0015A\u0002\t}\u0015!C2p[BdW\r^3s\u0011%A&Q\nB\u0001B\u0003%A\u0007C\u0005[\u0005\u001b\u0012\t\u0011)A\u00057\"Q\u0011M!\u0014\u0003\u0002\u0003\u0006IAa\u001a\t\u0015\u0005u(Q\nB\u0001B\u0003%!\u0002\u0003\u0005B\u0005\u001b\"\t\u0002AB5))\u0011Yea\u001b\u0004n\r=4\u0011\u000f\u0005\u00071\u000e\u001d\u0004\u0019\u0001\u001b\t\ri\u001b9\u00071\u0001\\\u0011\u001d\t7q\ra\u0001\u0005OBq!!@\u0004h\u0001\u0007!\"\u0002\u0004p\u0005\u001b\u0002!1\n\u0005\n\u0007o\u0012iE1A\u0005\u0002m\tQ\u0001\\3wK2D\u0001ba\u001f\u0003N\u0001\u0006I\u0001H\u0001\u0007Y\u00164X\r\u001c\u0011\t\u000f\r}$Q\nC#\u000b\u0006A\u0011n]*l_2,W\u000eC\u0004\u0004\u0004\n5C\u0011I#\u0002'%\u001cX\t_5ti\u0016tG/[1m'.|G.Z7\t\u000f\r\u001d%Q\nC!\u000b\u0006a\u0011n]$B\tR\u001b6n\u001c7f[\"911\u0012B'\t\u0003*\u0015\u0001D5t)f\u0004XmU6pY\u0016l\u0007b\u0002Br\u0005\u001b\"\t%\u0012\u0005\t\u0003\u001f\u0013i\u0005\"\u0011\u0003\u001e\"A11\u0013B'\t\u0003\nY\"A\u0006eKN[w\u000e\\3nSj,\u0007\u0002CBL\u0005\u001b\"\te!'\u0002\u001dUt\u0007/Y2l\u0019>\u001c\u0017\r^5p]V\t!\u0002\u0003\u0005\u0004\u001e\n5C\u0011IBP\u0003)!\u0018\u0010]3QCJ\fWn]\u000b\u0003\u0007C\u0003Ra!\u0005\u0004\u0018QB\u0001\"a(\u0003N\u0011\u00053Q\u0015\u000b\u0007\u0005\u0017\u001a9k!+\t\u000f\u0005\u001561\u0015a\u0001i!A\u0011\u0011VBR\u0001\u0004\tI\b\u0003\u0005\u0004.\n5C\u0011IA\u0000\u0003)q\u0017-\\3TiJLgn\u001a\t\u0004k\rE\u0016\u0002BBZ\u0005\u000f\u0011\u0011C\u0012:fKRK\b/Z*z[\n|G.\u00119j\u0011-\u0011iA!\u0012\u0003\u0002\u0003\u0006IAa\u001a\t\u0017\u0005u(Q\tBC\u0002\u0013\u0005\u0011q \u0005\f\u0005G\u0011)E!A!\u0002\u0013\t9\u0007C\u0004B\u0005\u000b\"\ta!0\u0015\r\t\r3qXBa\u0011!\u0011iaa/A\u0002\t\u001d\u0004\u0002CA\u007f\u0007w\u0003\r!a\u001a\t\u000f\tm\"Q\tC#\u000b\"A!q\bB#\t\u000b\u0012\t\u0005\u0003\u0004\u0004Jj\"\t!R\u0001\u000eSN,\u00050[:uK:$\u0018.\u00197\t\r\r5'\b\"\u0001F\u0003II7\u000fU1sC6<\u0016\u000e\u001e5EK\u001a\fW\u000f\u001c;\t\r\rE'\b\"\u0001F\u00035I7OQ=OC6,\u0007+\u0019:b[\"11Q\u001b\u001e\u0005\u0002\u0015\u000b\u0001$[:J[BdW-\\3oi\u0006$\u0018n\u001c8BeRLg-Y2u\u0011\u0019\u0019IN\u000fC\u0001\u000b\u00061\u0011n\u001d&bm\u0006Daa!8;\t\u0003)\u0015!B5t-\u0006d\u0007BBBqu\u0011\u0005Q)A\u0003jgZ\u000b'\u000f\u0003\u0004\u0004fj\"\t!R\u0001\u000bSN\f%m\u001d;sC\u000e$\bBBBuu\u0011\u0005Q)A\u0007jgB\u0013\u0018N^1uKRC\u0017n\u001d\u0005\u0007\u0007[TD\u0011A#\u0002\u001f%\u001c\bK]8uK\u000e$X\r\u001a+iSNDqa!=;\t\u0003\u0019\u00190A\boK^tUm\u001d;fINKXNY8m)%!4Q_B|\u0007w\u001ci\u0010\u0003\u0004w\u0007_\u0004\ra\u001f\u0005\b\u0007s\u001cy\u000f1\u0001\\\u0003\r\u0001xn\u001d\u0005\t\u0003S\u001by\u000f1\u0001\u0002z!91q`Bx\u0001\u00041\u0015aB5t\u00072\f7o\u001d\u0005\b\t\u0007QD\u0011\u0001C\u0003\u0003UYgn\\<o\t&\u0014Xm\u0019;Tk\n\u001cG.Y:tKN,\"\u0001b\u0002\u0011\u000b\u0005%D\u0011\u0002\u001b\n\t\u0011-\u00111\u000f\u0002\u0004'\u0016$\bb\u0002C\bu\u0011\u0005!QT\u0001\tg\u0016dg\rV=qK\"9A1\u0003\u001e\u0005\u0002\r}\u0015a\u00032bg\u0016\u001cE.Y:tKNDq\u0001b\u0006;\t\u0003\tY\"\u0001\u0004n_\u0012,H.\u001a\u0005\b\t7QD\u0011\u0001BO\u0003)!\b.[:Qe\u00164\u0017\u000e\u001f\u0005\b\t?QD\u0011\u0001C\u0011\u0003-\u0019X\u000f]3s!J,g-\u001b=\u0015\t\t}E1\u0005\u0005\t\tK!i\u00021\u0001\u0003 \u0006A1/\u001e9feR\u0004X\rC\u0004\u0005*i\"\tA!(\u0002\u001bQL\b/Z*jO:\fG/\u001e:f\u0011\u001d!iC\u000fC\u0001\t_\tq\u0002^=qKNKwM\\1ukJ,\u0017J\u001c\u000b\u0005\u0005?#\t\u0004\u0003\u0005\u00054\u0011-\u0002\u0019\u0001BP\u0003\u0011\u0019\u0018\u000e^3\t\u000f\u0011]\"\b\"\u0001\u0003\u001e\u00061Ao\u001c+za\u0016Dq\u0001b\u000f;\t\u0003!i$\u0001\u0005u_RK\b/Z%o)\u0011\u0011y\nb\u0010\t\u0011\u0011MB\u0011\ba\u0001\u0005?Cq\u0001b\u0011;\t\u0003\u0011i*A\tu_RK\b/Z\"p]N$(/^2u_JDq\u0001b\u0012;\t\u0003!I%\u0001\btKR\feN\\8uCRLwN\\:\u0015\t\u0011-CQJ\u0007\u0002u!AAq\nC#\u0001\u0004!\t&\u0001\u0004b]:|Go\u001d\t\u0006\u0017\u0011MCqK\u0005\u0004\t+2!A\u0003\u001fsKB,\u0017\r^3e}A\u0019Q\u0007\"\u0017\n\t\u0011mCQ\f\u0002\u000f\u0003:tw\u000e^1uS>t\u0017J\u001c4p\u0013\r!yF\u0001\u0002\u0010\u0003:tw\u000e^1uS>t\u0017J\u001c4pg\"9A1\r\u001e\u0005\u0002\u0005m\u0011AB4fiR,'\u000fC\u0004\u0005hi\"\t!a\u0007\u0002\rM,G\u000f^3s\u0011\u001d!YG\u000fC\u0001\u00037\t\u0011bY8na\u0006t\u0017n\u001c8\t\u000f\u0011=$\b\"\u0001\u0005r\u00051\u0011N\u001c4p\u0013:$BAa(\u0005t!AA1\u0007C7\u0001\u0004\u0011y\nC\u0004\u0005xi\"\taa(\u0002\u0013=4XM\u001d:jI\u0016\u001c\bb\u0002C>u\u0011\u0005AQP\u0001\u000ba\u0006\u0014\u0018-\u001c'jgR\u001cXC\u0001C@!\u0019\u0019\tba\u0006\u0004\"B!A1\u0011CC\u001b\u0005\u0011\u0011b\u0001CD\u0005\tA\u0001*Y:GY\u0006<7\u000f\u0005\u00036\t\u0017#\u0014\u0002\u0002CG\t;\u00121\"\u00118o_R\fG/\u00192mKB\u0019Q\u0007\"%\n\t\u0011MEQ\u0013\u0002\u000b\u0003R$\u0018m\u00195bE2,\u0017b\u0001CL\u0005\tq1\u000b\u001e3BiR\f7\r[7f]R\u001c\b\u0002\u0003-7\u0005\u0003\u0005\u000b\u0011\u0002\u001b\t\u0011i3$\u0011!Q\u0001\nmC\u0001\"\u0019\u001c\u0003\u0002\u0003\u0006Ia\u001f\u0005\b\u0003Z\"\t\u0002\u0001CQ)\u001d!D1\u0015CS\tOCa\u0001\u0017CP\u0001\u0004!\u0004B\u0002.\u0005 \u0002\u00071\f\u0003\u0004b\t?\u0003\ra\u001f\u0005\u0007\tW3D\u0011B#\u0002\u001d%\u001c8+\u001f8dQJ|g.\u001b>fI\"1Aq\u0016\u001c\u0005\n\u0015\u000b1#[:BaJLwN]5UQJ,\u0017\rZ:bM\u0016,Q\u0001b-7\u0001Q\u0012!#Q2dKN\u001c(i\\;oI\u0006\u0014\u0018\u0010V=qK\u00161Aq\u0017\u001c\u0001\t/\u0012a\"\u00118o_R\fG/[8o)f\u0004X\r\u0002\u0004pm\t\u0005A1X\t\u0005\u0005{\"iLE\u0002\u0005@R2a\u0001\"17\u0001\u0011u&\u0001\u0004\u001fsK\u001aLg.Z7f]RtTa\u0002Cc\t\u007f\u0003Cq\u0019\u0002\t\u001d\u0006lW\rV=qKB!A\u0011\u001aCf\u001b\u00051\u0014b\u0001Cc}!11O\u000eD\u0001\t\u001f,\"\u0001b2\t\rY4d\u0011\u0001Ch\u0011\u0019Ah\u0007\"\u0001\u0005VR\u0019a\u0003b6\t\u000f\u0005\u0015A1\u001ba\u0001w\"1qP\u000eD\u0001\t7$B\u0001b2\u0005^\"9\u0011Q\u0001Cm\u0001\u0004Y\bb\u0002Cqm\u0001\u0006K\u0001N\u0001\n?J\fwo\\<oKJD1\u0002\":7\u0001\u0004\u0005\t\u0015)\u0003\u0002z\u0005IqL]1xM2\fwm\u001d\u0005\b\tS4D\u0011AA\u000e\u0003!\u0011\u0018m^8x]\u0016\u0014\bb\u0002Cwm\u0011\u0005\u00111X\u0001\te\u0006<h\r\\1hg\"AA\u0011\u001f\u001cC\u0002\u0013\u00051$\u0001\u0002jI\"9AQ\u001f\u001c!\u0002\u0013a\u0012aA5eA!AA\u0011 \u001c!B\u0013!Y0\u0001\u0005`m\u0006d\u0017\u000e\u001a+p!\r)DQ`\u0005\u0005\t\u007f,\tA\u0001\u0004QKJLw\u000eZ\u0005\u0004\u000b\u0007\u0011!aC*z[\n|G\u000eV1cY\u0016Dq!b\u00027\t\u0003)I!A\u0004wC2LG\rV8\u0016\u0005\u0011m\bbBC\u0007m\u0011\u0005QqB\u0001\fm\u0006d\u0017\u000e\u001a+p?\u0012*\u0017\u000fF\u0002\u0017\u000b#A\u0001\"a#\u0006\f\u0001\u0007A1 \u0005\b\u000b+1D\u0011AC\f\u0003\u001d\u0019X\r\u001e(b[\u0016$B\u0001\"3\u0006\u001a!1a/b\u0005A\u0002mD\u0001\"\"\b7A\u0013EQqD\u0001\u0013G\"\fgnZ3OC6,\u0017J\\(x]\u0016\u00148\u000fF\u0002\u0017\u000bCAaA^C\u000e\u0001\u0004Y\bbBC\u0013m\u0011\u0005QqE\u0001\u000ee\u0006<h\t\\1h'R\u0014\u0018N\\4\u0015\t\u0005\u001dT\u0011\u0006\u0005\t\u000bW)\u0019\u00031\u0001\u0002z\u0005!Q.Y:l\u0011\u001d))C\u000eC\u0001\u0003\u007fDq!\"\r7\t\u0003\ty0A\beK\n,xM\u00127bON#(/\u001b8h\u0011\u001d))D\u000eC\u0001\u0003\u007f\faB^1sS\u0006t7-Z*ue&tw\rC\u0004\u0006:Y\"\t%a/\u0002\u0011\u0019d\u0017mZ'bg.Dq!\"\u00107\t\u0003)y$\u0001\fgY\u0006<7/\u0012=qY\u0006t\u0017\r^5p]N#(/\u001b8h+\t)\t\u0005\u0005\u0003\u0006D\u00155SBAC#\u0015\u0011)9%\"\u0013\u0002\t1\fgn\u001a\u0006\u0003\u000b\u0017\nAA[1wC&!\u0011\u0011OC#\u0011\u001d)\tF\u000eC\u0001\u0003\u007f\f\u0001c\u001d5peR\u001c\u00160\u001c2pY\u000ec\u0017m]:\t\u000f\u0015Uc\u0007\"\u0001\u0002\u0000\u0006!2/_7c_2\u001c%/Z1uS>t7\u000b\u001e:j]\u001eDq!\"\u00177\t\u000b)Y&\u0001\u0005oK^4\u0016\r\\;f)\u001d\u0001VQLC0\u000bCBaA^C,\u0001\u0004\u0011\u0007\"CB}\u000b/\u0002\n\u00111\u0001\\\u0011)\tI+b\u0016\u0011\u0002\u0003\u0007\u0011\u0011\u0010\u0005\b\u000bK2DQAC4\u0003-qWm\u001e,be&\f'\r\\3\u0015\u000fA+I'b\u001b\u0006n!1a/b\u0019A\u0002\tD\u0011b!?\u0006dA\u0005\t\u0019A.\t\u0015\u0005%V1\rI\u0001\u0002\u0004\tI\bC\u0004\u0006rY\")!b\u001d\u0002#9,wOV1mk\u0016\u0004\u0016M]1nKR,'\u000fF\u0004Q\u000bk*9(\"\u001f\t\rY,y\u00071\u0001c\u0011%\u0019I0b\u001c\u0011\u0002\u0003\u00071\f\u0003\u0006\u0002*\u0016=\u0004\u0013!a\u0001\u0003sBq!\" 7\t\u000b)y(A\u0007oK^dunY1m\tVlW.\u001f\u000b\u0004!\u0016\u0005\u0005bBB}\u000bw\u0002\ra\u0017\u0005\b\u000b\u000b3DQACD\u0003%qWm^'fi\"|G\r\u0006\u0005\u0006\n\u0016\u0005X1]Cs!\r)T1\u0012\u0004\u0007\u000b\u001b\u0003\u0001!b$\u0003\u00195+G\u000f[8e'fl'm\u001c7\u0014\u000b\u0015-\u0005+\"%\u0011\u0007U*\u0019*C\u0002\u0006\u0016J\u0011q\"T3uQ>$7+_7c_2\f\u0005/\u001b\u0005\n1\u0016-%\u0011!Q\u0001\nQB\u0011BWCF\u0005\u0003\u0005\u000b\u0011B.\t\u0013\u0005,YI!A!\u0002\u0013\u0011\u0007\u0002C!\u0006\f\u0012E\u0001!b(\u0015\u0011\u0015%U\u0011UCR\u000bKCa\u0001WCO\u0001\u0004!\u0004B\u0002.\u0006\u001e\u0002\u00071\f\u0003\u0004b\u000b;\u0003\rA\u0019\u0005\t\u000bS+Y\t)Q\u00059\u0005QQ\u000e\u001e9f!\u0016\u0014\u0018n\u001c3\t\u0019\u00155V1\u0012a\u0001\u0002\u0003\u0006KAa(\u0002\u000f5$\b/\u001a)sK\"aQ\u0011WCF\u0001\u0004\u0005\t\u0015)\u0003\u0003 \u0006QQ\u000e\u001e9f%\u0016\u001cX\u000f\u001c;\t\u0019\u0015UV1\u0012a\u0001\u0002\u0003\u0006KAa(\u0002\u00115$\b/Z%oM>Dq!\"/\u0006\f\u0012\u0005S)A\u0004jg2\u000b'-\u001a7\t\u000f\u0015uV1\u0012C!\u000b\u0006y\u0011n\u001d,be\u0006\u0014xm]'fi\"|G\rC\u0004\u0006B\u0016-E\u0011I#\u0002\u001d%\u001cH*\u001b4uK\u0012lU\r\u001e5pI\"9QQYCF\t\u0003*\u0015AD5t'>,(oY3NKRDw\u000e\u001a\u0005\b\u000b\u0013,Y\t\"\u0011F\u0003QI7oQ1tK\u0006\u001b7-Z:t_JlU\r\u001e5pI\"AQQZCF\t\u0003)y-\u0001\busB,\u0017i]'f[\n,'o\u00144\u0015\t\t}U\u0011\u001b\u0005\t\u000b',Y\r1\u0001\u0003 \u0006\u0019\u0001O]3\t\u000f\u0015]W1\u0012C!\u000b\u0006I\u0011n\u001d,be\u0006\u0014xm\u001d\u0005\t\u000b7,Y\t\"\u0011\u0003\u001e\u0006Q!/\u001a;ve:$\u0016\u0010]3\t\u0011\u0015}W1\u0012C!\u0007?\u000b!\"\u001a=dKB$\u0018n\u001c8t\u0011\u00191X1\u0011a\u0001E\"I1\u0011`CB!\u0003\u0005\ra\u0017\u0005\u000b\u0003S+\u0019\t%AA\u0002\u0005e\u0004bBCum\u0011\u0015Q1^\u0001\u0010]\u0016<X*\u001a;i_\u0012\u001c\u00160\u001c2pYRAQ\u0011RCw\u000b_,\t\u0010\u0003\u0004w\u000bO\u0004\rA\u0019\u0005\n\u0007s,9\u000f%AA\u0002mC!\"!+\u0006hB\u0005\t\u0019AA=\u0011\u001d))P\u000eC\u0003\u000bo\f\u0001B\\3x\u0019\u0006\u0014W\r\u001c\u000b\u0007\u000b\u0013+I0b?\t\rY,\u0019\u00101\u0001c\u0011%\u0019I0b=\u0011\u0002\u0003\u00071\fC\u0004\u0006\u0000Z\")A\"\u0001\u0002\u001d9,woQ8ogR\u0014Xo\u0019;peR1Q\u0011\u0012D\u0002\r\u000bAqa!?\u0006~\u0002\u00071\f\u0003\u0006\u0002*\u0016u\b\u0013!a\u0001\u0003sBqA\"\u00037\t\u00031Y!\u0001\u000boK^\u001cF/\u0019;jG\u000e{gn\u001d;sk\u000e$xN\u001d\u000b\u0005\u000b\u00133i\u0001C\u0004\u0004z\u001a\u001d\u0001\u0019A.\t\u000f\u0019Ea\u0007\"\u0001\u0007\u0014\u0005\u0019b.Z<DY\u0006\u001c8oQ8ogR\u0014Xo\u0019;peR!Q\u0011\u0012D\u000b\u0011\u001d\u0019IPb\u0004A\u0002mCqA\"\u00077\t\u00031Y\"A\boK^d\u0015N\\6fI6{G-\u001e7f)\u00191iB\"\u001d\u0007tA\u0019QGb\b\u0007\r\u0019\u0005\u0002\u0001\u0001D\u0012\u00051iu\u000eZ;mKNKXNY8m'\u00151y\u0002\u0015D\u0013!\r)dqE\u0005\u0004\rS\u0011\"aD'pIVdWmU=nE>d\u0017\t]5\t\u0013a3yB!A!\u0002\u0013!\u0004\"\u0003.\u0007 \t\u0005\t\u0015!\u0003\\\u0011%\tgq\u0004B\u0001B\u0003%!\r\u0003\u0005B\r?!\t\u0002\u0001D\u001a)!1iB\"\u000e\u00078\u0019e\u0002B\u0002-\u00072\u0001\u0007A\u0007\u0003\u0004[\rc\u0001\ra\u0017\u0005\u0007C\u001aE\u0002\u0019\u00012\t\u0013\u0019ubq\u0004a\u0001\n\u0013!\u0018\u0001\u00034mCRt\u0017-\\3\t\u0015\u0019\u0005cq\u0004a\u0001\n\u00131\u0019%\u0001\u0007gY\u0006$h.Y7f?\u0012*\u0017\u000fF\u0002\u0017\r\u000bB\u0001b\tD \u0003\u0003\u0005\rA\u0019\u0005\t\r\u00132y\u0002)Q\u0005E\u0006Ia\r\\1u]\u0006lW\r\t\u0005\t\r\u001b2y\u0002\"\u0011\u0007P\u0005q\u0011m]:pG&\fG/\u001a3GS2,WC\u0001D)!\u00111\u0019F\"\u0017\u000e\u0005\u0019U#b\u0001D,\t\u0005\u0011\u0011n\\\u0005\u0005\r72)F\u0001\u0007BEN$(/Y2u\r&dW\r\u0003\u0005\u0007`\u0019}A\u0011\tD1\u0003I\t7o]8dS\u0006$X\r\u001a$jY\u0016|F%Z9\u0015\u0007Y1\u0019\u0007\u0003\u0005\u0007f\u0019u\u0003\u0019\u0001D)\u0003\u00051\u0007\u0002CA\u0010\r?!\t%a\u0007\t\u0011\u0019-dq\u0004C!\u00037\tabY8na\u0006t\u0017n\u001c8DY\u0006\u001c8\u000f\u0003\u0005\u0002&\u001a}A\u0011IA\u000e\u0011\u00191hq\u0004C!i\"9\u0011\u0011\u001cD\f\u0001\u0004!\u0004BCAU\r/\u0001\n\u00111\u0001\u0002z!9aq\u000f\u001c\u0005\u0006\u0019e\u0014!\u00038fo6{G-\u001e7f)!1iBb\u001f\u0007~\u0019}\u0004B\u0002<\u0007v\u0001\u0007!\rC\u0005\u0004z\u001aU\u0004\u0013!a\u00017\"Qa\u0011\u0011D;!\u0003\u0005\r!!\u001f\u0002\u00139,wO\u00127bON\u0004\u0004b\u0002DCm\u0011\u0015aqQ\u0001\u000b]\u0016<\b+Y2lC\u001e,G\u0003\u0003D\u000f\r\u00133YI\"$\t\rY4\u0019\t1\u0001c\u0011%\u0019IPb!\u0011\u0002\u0003\u00071\f\u0003\u0006\u0002*\u001a\r\u0005\u0013!a\u0001\u0003sBqA\"%7\t\u000b1\u0019*\u0001\u0006oK^$\u0006.[:Ts6$R\u0001\u0015DK\r/C\u0001B\u001eDH!\u0003\u0005\rA\u0019\u0005\n\u0007s4y\t%AA\u0002mCqAb'7\t\u000b1i*A\u0005oK^LU\u000e]8siR\u0019\u0001Kb(\t\u000f\reh\u0011\u0014a\u00017\"9a1\u0015\u001c\u0005\u0002\u0019\u0015\u0016A\u00058fo6{G-\u001e7f-\u0006\u00148+_7c_2$2\u0001\u0015DT\u0011\u001d1IK\")A\u0002Q\n\u0001\"Y2dKN\u001cxN\u001d\u0005\b\r[3DQ\u0001DX\u0003=qWm^'pIVdWmU=nE>dG\u0003\u0003D\u000f\rc3\u0019L\".\t\rY4Y\u000b1\u0001c\u0011%\u0019IPb+\u0011\u0002\u0003\u00071\f\u0003\u0006\u0002*\u001a-\u0006\u0013!a\u0001\u0003sBqA\"/7\t\u000b1Y,A\foK^lu\u000eZ;mK\u0006sGm\u00117bgN\u001c\u00160\u001c2pYRAaQXDm\u000f7<i\u000eE\u0004\f\r\u007f3iBb1\n\u0007\u0019\u0005gA\u0001\u0004UkBdWM\r\t\u0004k\u0019\u0015gA\u0002Dd\u0001\u00011IMA\u0006DY\u0006\u001c8oU=nE>d7C\u0002Dc\u0005'2Y\rE\u00026\r\u001bL1Ab4\u0013\u00059\u0019E.Y:t'fl'm\u001c7Ba&D\u0011\u0002\u0017Dc\u0005\u0003\u0005\u000b\u0011\u0002\u001b\t\u0013i3)M!A!\u0002\u0013Y\u0006BC1\u0007F\n\u0005\t\u0015!\u0003\u0003h!A\u0011I\"2\u0005\u0012\u00011I\u000e\u0006\u0005\u0007D\u001amgQ\u001cDp\u0011\u0019Afq\u001ba\u0001i!1!Lb6A\u0002mCq!\u0019Dl\u0001\u0004\u00119'\u0002\u0004p\r\u000b\u0004a1\u0019\u0005\r\r{1)\r1A\u0001B\u0003&!q\r\u0005\r\rO4)\r1A\u0001B\u0003&a\u0011K\u0001\u0010?\u0006\u001c8o\\2jCR,GMR5mK\"Aa1\u001eDcA\u0003&A'A\u0004uQ&\u001c8/_7\t\u0019\u0019=hQ\u0019a\u0001\u0002\u0003\u0006KAa(\u0002\u001bQD\u0017n\u001d+za\u0016\u001c\u0015m\u00195f\u0011!1\u0019P\"2!B\u0013a\u0012A\u0004;iSN$\u0016\u0010]3QKJLw\u000e\u001a\u0005\t\u0003G2)\r\"\u0011\u0007xR!\u0011q\rD}\u0011!\t9H\">A\u0002\u0005e\u0004b\u0002BI\r\u000b$)%\u0012\u0005\b\u0005G4)\r\"\u0012F\u0011\u001d9\tA\"2\u0005F\u0015\u000b1\"[:BY&\f7\u000fV=qK\"9!q\u001dDc\t\u000b*\u0005bBD\u0004\r\u000b$\t%R\u0001\u0010SN\f%m\u001d;sC\u000e$8\t\\1tg\"9q1\u0002Dc\t\u0003*\u0015aC5t\u0007\u0006\u001cXm\u00117bgNDqab\u0004\u0007F\u0012\u0005S)A\rjg\u000ec\u0017m]:M_\u000e\fG\u000eV8D_:\u001cHO];di>\u0014\bbBD\n\r\u000b$\t%R\u0001\fSNLU\u000e\u001d7DY\u0006\u001c8\u000fC\u0004\b\u0018\u0019\u0015G\u0011I#\u0002\u001b%\u001cXj\u001c3vY\u0016\u001cE.Y:t\u0011\u001d9YB\"2\u0005B\u0015\u000ba\"[:QC\u000e\\\u0017mZ3DY\u0006\u001c8\u000fC\u0004\b \u0019\u0015G\u0011I#\u0002\u000f%\u001cHK]1ji\"9q1\u0005Dc\t\u0003*\u0015aF5t\u0003:|gn\u0014:SK\u001aLg.Z7f]R\u001cE.Y:t\u0011\u001d99C\"2\u0005B\u0015\u000b\u0001#[:B]>t\u00170\\8vg\u000ec\u0017m]:\t\u000f\u001d-bQ\u0019C!\u000b\u0006y\u0011n]\"p]\u000e\u0014X\r^3DY\u0006\u001c8\u000fC\u0004\b0\u0019\u0015G\u0011I#\u0002\u001f%\u001c(*\u0019<b\u0013:$XM\u001d4bG\u0016Dqab\r\u0007F\u0012\u0005S)A\u0007jg:+7\u000f^3e\u00072\f7o\u001d\u0005\b\u000fo1)\r\"\u0011F\u0003MI7OT;nKJL7MV1mk\u0016\u001cE.Y:t\u0011\u001d9YD\"2\u0005B\u0015\u000b\u0011\"[:Ok6,'/[2\t\u000f\u001d}bQ\u0019C!\u000b\u0006!\u0012n\u001d)bG.\fw-Z(cU\u0016\u001cGo\u00117bgNDqab\u0011\u0007F\u0012\u0005S)A\u000bjgB\u0013\u0018.\\5uSZ,g+\u00197vK\u000ec\u0017m]:\t\u000f\u001d\u001dcQ\u0019C!\u000b\u0006Y\u0011n\u001d)sS6LG/\u001b<f\u0011!9YE\"2\u0005\n\u0005m\u0011A\u00037bgR\u0004\u0016M]3oi\"Aqq\nDc\t\u0003\nY\"A\u0006u_&sG/\u001a:gC\u000e,\u0007bBD*\r\u000b$\t%R\u0001\rSNdunY1m\u00072\f7o\u001d\u0005\t\u000f/2)\r\"\u0011\bZ\u0005qQM\\2m\u00072\f7o]\"iC&tWCAD.!\u0011isQ\f\u001b\n\u0007\rea\u0006\u0003\u0005\bb\u0019\u0015GQCA\u000e\u0003A\u0019w.\u001c9b]&|g.T8ek2,\u0007\u0007\u0003\u0005\bf\u0019\u0015G\u0011IA\u000e\u0003=\u0019w.\u001c9b]&|g.T8ek2,\u0007\u0002CA\r\r\u000b$\t%a\u0007\t\u0011\u001d-dQ\u0019C!\u00037\t!\u0003\\5oW\u0016$7\t\\1tg>37\t\\1tg\"Aqq\u000eDc\t\u0003\nY\"\u0001\u0007t_V\u00148-Z'pIVdW\r\u0003\u0005\u0002\u0010\u001a\u0015G\u0011\tBO\u0011!9)H\"2\u0005\u0002\u001d]\u0014A\u00069sS6\f'/_\"p]N$(/^2u_Jt\u0015-\\3\u0016\u0005\u001de\u0004\u0003BD>\u000f\u000bs1!ND?\u0013\u00119yh\"!\u0002\u00079lW-C\u0002\b\u0004\n\u0011\u0001b\u0015;e\u001d\u0006lWm]\u0005\u0005\t\u000b<9)\u0003\u0003\b\n\u001e\u0005%!\u0003+fe6t\u0015-\\3t\u0011!9iI\"2\u0005B\u0005m\u0011A\u00059sS6\f'/_\"p]N$(/^2u_JD\u0001B\"\u0014\u0007F\u0012\u0005cq\n\u0005\t\r?2)\r\"\u0011\b\u0014R\u0019ac\"&\t\u0011\u0019\u0015t\u0011\u0013a\u0001\r#B\u0001b!\u0016\u0007F\u0012\u0005s\u0011\u0014\u000b\u0005\u000f7;i*\u0004\u0002\u0007F\"A1QLDL\u0001\u0004\u0011y\n\u0003\u0005\b\"\u001a\u0015G\u0011\tBO\u0003!!\b.[:UsB,\u0007\u0002CAS\r\u000b$\t%a\u0007\t\u000fY4)\r\"\u0011\u0003\u0006\"Aq\u0011\u0016Dc\t\u0003\nY\"A\u0004uQ&\u001c8+_7\t\u0011\u001d5fQ\u0019C!\u000f_\u000ba\u0002^=qK>3G\u000b[5t?\u0012*\u0017\u000fF\u0002\u0017\u000fcC\u0001ba\u0012\b,\u0002\u0007!q\u0014\u0005\t\u0003?3)\r\"\u0011\b6R1a1YD\\\u000fsCq!!*\b4\u0002\u0007A\u0007\u0003\u0005\u0002*\u001eM\u0006\u0019AA=\u0011!9iL\"2\u0005B\u0005m\u0011A\u00063fe&4X\r\u001a,bYV,7\t\\1tgVs'm\u001c=\t\u0013\u001d\u0005gQ\u0019Q!\n\u0011\u001d\u0011\u0001C2iS2$7+\u001a;\t\u0011\u001d\u0015gQ\u0019C!\t\u000b\t\u0001b\u00195jY\u0012\u0014XM\u001c\u0005\t\u000f\u00134)\r\"\u0011\bL\u0006A\u0011\r\u001a3DQ&dG\rF\u0002\u0017\u000f\u001bDq!a9\bH\u0002\u0007A\u0007\u0003\u0005\bR\u001a\u0015G\u0011AA\u0000\u0003Y\tgn\u001c8PeJ+g-\u001b8f[\u0016tGo\u0015;sS:<\u0007\u0002CDk\r\u000b$\teb6\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u001a\t\rY49\f1\u0001|\u0011\u001d\u0019IPb.A\u0002mC\u0001bb8\u00078\u0002\u0007q\u0011]\u0001\u0007M2\fwm\u001d\u0019\u0011\u0007U:\u0019/\u0003\u0003\bf\u001e\u001d(a\u0002$mC\u001e\u001cV\r^\u0005\u0004\u000fS\u0014!\u0001\u0003$mC\u001e\u001cV\r^:\t\u000f\u001d5h\u0007\"\u0002\bp\u0006!b.Z<N_\u0012,H.Z\"mCN\u001c8+_7c_2$\u0002b\"=\tn!=\u0004\u0012\u000f\t\u0004k\u001dMhABD{\u0001\u000199PA\tN_\u0012,H.Z\"mCN\u001c8+_7c_2\u001cBab=\u0007D\"Q\u0011QUDz\u0005\u0003\u0005\u000b\u0011\u0002\u001b\t\u0015\rex1\u001fB\u0001B\u0003%1\f\u0003\u0006w\u000fg\u0014\t\u0011)A\u0005\u0005OB\u0001\"QDz\t#\u0001\u0001\u0012\u0001\u000b\t\u000fcD\u0019\u0001#\u0002\t\b!9\u0011QUD\u0000\u0001\u0004!\u0004bBB}\u000f\u007f\u0004\ra\u0017\u0005\bm\u001e}\b\u0019\u0001B4\u0011-!9bb=A\u0002\u0003\u0005\u000b\u0015\u0002\u001b\t\u0019!5q1\u001fa\u0001\u0002\u0003\u0006KAa(\u0002\u001fQL\b/Z(g)\"L7oQ1dQ\u0016D\u0001\u0002#\u0005\bt\u0002\u0006K\u0001H\u0001\u0011if\u0004Xm\u00144UQ&\u001c\b+\u001a:j_\u0012D!\u0002#\u0006\bt\u0002\u0007I\u0011\u0002E\f\u0003eIW\u000e\u001d7jG&$X*Z7cKJ\u001c8)Y2iKZ\u000bG.^3\u0016\u0005!e\u0001cA\u001b\t\u001c%!\u0001R\u0004E\u0010\u0005\u0015\u00196m\u001c9f\u0013\rA\tC\u0001\u0002\u0007'\u000e|\u0007/Z:\t\u0015!\u0015r1\u001fa\u0001\n\u0013A9#A\u000fj[Bd\u0017nY5u\u001b\u0016l'-\u001a:t\u0007\u0006\u001c\u0007.\u001a,bYV,w\fJ3r)\r1\u0002\u0012\u0006\u0005\nG!\r\u0012\u0011!a\u0001\u00113A\u0011\u0002#\f\bt\u0002\u0006K\u0001#\u0007\u00025%l\u0007\u000f\\5dSRlU-\u001c2feN\u001c\u0015m\u00195f-\u0006dW/\u001a\u0011\t\u0015!Er1\u001fa\u0001\n\u0013\u0011i*\u0001\rj[Bd\u0017nY5u\u001b\u0016l'-\u001a:t\u0007\u0006\u001c\u0007.Z&fsFB!\u0002#\u000e\bt\u0002\u0007I\u0011\u0002E\u001c\u0003qIW\u000e\u001d7jG&$X*Z7cKJ\u001c8)Y2iK.+\u00170M0%KF$2A\u0006E\u001d\u0011%\u0019\u00032GA\u0001\u0002\u0004\u0011y\nC\u0005\t>\u001dM\b\u0015)\u0003\u0003 \u0006I\u0012.\u001c9mS\u000eLG/T3nE\u0016\u00148oQ1dQ\u0016\\U-_\u0019!\u0011)A\teb=A\u0002\u0013%\u00012I\u0001\u0019S6\u0004H.[2ji6+WNY3sg\u000e\u000b7\r[3LKf\u0014TC\u0001E#!\r)\u0004rI\u0005\u0005\u0011\u0013ByB\u0001\u0006TG>\u0004X-\u00128uefD!\u0002#\u0014\bt\u0002\u0007I\u0011\u0002E(\u0003qIW\u000e\u001d7jG&$X*Z7cKJ\u001c8)Y2iK.+\u0017PM0%KF$2A\u0006E)\u0011%\u0019\u00032JA\u0001\u0002\u0004A)\u0005C\u0005\tV\u001dM\b\u0015)\u0003\tF\u0005I\u0012.\u001c9mS\u000eLG/T3nE\u0016\u00148oQ1dQ\u0016\\U-\u001f\u001a!\u0011\u001d99bb=\u0005B\u0015C\u0001bb\u001b\bt\u0012\u0005\u00131\u0004\u0005\t\u0011;:\u0019\u0010\"\u0011\u0003\u001e\u0006QA/\u001f9f\u001f\u001a$\u0006.[:\t\u0011!\u0005t1\u001fC\u0001\u0011/\tq\"[7qY&\u001c\u0017\u000e^'f[\n,'o\u001d\u0005\t\u000f_:\u0019\u0010\"\u0011\u0002\u001c!A\u0001rMDz\t\u0003BI'\u0001\tt_V\u00148-Z'pIVdWm\u0018\u0013fcR\u0019a\u0003c\u001b\t\u000f\u0011]\u0001R\ra\u0001i!9aob;A\u0002\t\u001d\u0004\"CB}\u000fW\u0004\n\u00111\u0001\\\u0011)\tIkb;\u0011\u0002\u0003\u0007\u0011\u0011\u0010\u0005\b\u0011k2DQ\u0001E<\u0003MqWm\u001e+za\u0016\u001c6n\u001c7f[NKXNY8m))\u0011Y\u0005#\u001f\t|!u\u0004r\u0010\u0005\bm\"M\u0004\u0019\u0001B4\u0011\u001d\ti\u0010c\u001dA\u0002)A\u0011b!?\ttA\u0005\t\u0019A.\t\u0015\u0005%\u00062\u000fI\u0001\u0002\u0004\tI\bC\u0004\t\u0004Z\")\u0001#\"\u0002\u001b9,wo\u0014<fe2|\u0017\rZ3e)\u0015\u0001\u0006r\u0011EE\u0011!)\u0019\u000e#!A\u0002\t}\u0005\u0002\u0003EF\u0011\u0003\u0003\ra!)\u0002\u0019\u0005dG/\u001a:oCRLg/Z:\t\u000f!=e\u0007\"\u0002\t\u0012\u0006ia.Z<FeJ|'OV1mk\u0016$2\u0001\u0015EJ\u0011\u00191\bR\u0012a\u0001E\"9\u0001r\u0013\u001c\u0005\u0006!e\u0015\u0001\u00048fo\u0006c\u0017.Y:UsB,G\u0003\u0003EN\u0011\u001fD\t\u000ec5\u0011\u0007UBiJ\u0002\u0004\t \u0002\u0001\u0001\u0012\u0015\u0002\u0010\u00032L\u0017m\u001d+za\u0016\u001c\u00160\u001c2pYN!\u0001R\u0014B*\u0011%A\u0006R\u0014B\u0001B\u0003%A\u0007C\u0005[\u0011;\u0013\t\u0011)A\u00057\"Q\u0011\r#(\u0003\u0002\u0003\u0006IAa\u001a\t\u0011\u0005Ci\n\"\u0005\u0001\u0011W#\u0002\u0002c'\t.\"=\u0006\u0012\u0017\u0005\u00071\"%\u0006\u0019\u0001\u001b\t\riCI\u000b1\u0001\\\u0011\u001d\t\u0007\u0012\u0016a\u0001\u0005O*aa\u001cEO\u0001\tM\u0003\u0002\u0003E\\\u0011;#\t\u0005#/\u0002\u0011Y\f'/[1oG\u0016,\"\u0001c/\u0011\t\u0011\r\u0005RX\u0005\u0004\u0011\u007f\u0013!\u0001\u0003,be&\fgnY3\t\u000f\t\u001d\bR\u0014C!\u000b\"9!1\u001eEO\t\u0003*\u0005bBD\u0001\u0011;#)%\u0012\u0005\t\u0003?Ci\n\"\u0011\tJR1!1\u000bEf\u0011\u001bDq!!*\tH\u0002\u0007A\u0007\u0003\u0005\u0002*\"\u001d\u0007\u0019AA=\u0011\u001d1\bR\u0013a\u0001\u0005OB\u0011b!?\t\u0016B\u0005\t\u0019A.\t\u0015\u0005%\u0006R\u0013I\u0001\u0002\u0004\tI\bC\u0004\tXZ\")\u0001#7\u0002\u001f9,w/\u00112tiJ\f7\r\u001e+za\u0016$\u0002\u0002c7\n\u0002%\r\u0011R\u0001\t\u0004k!ugA\u0002Ep\u0001\u0001A\tO\u0001\nBEN$(/Y2u)f\u0004XmU=nE>d7\u0003\u0002Eo\u0005'B\u0011\u0002\u0017Eo\u0005\u0003\u0005\u000b\u0011\u0002\u001b\t\u0013iCiN!A!\u0002\u0013Y\u0006BC1\t^\n\u0005\t\u0015!\u0003\u0003h!A\u0011\t#8\u0005\u0012\u0001AY\u000f\u0006\u0005\t\\\"5\br\u001eEy\u0011\u0019A\u0006\u0012\u001ea\u0001i!1!\f#;A\u0002mCq!\u0019Eu\u0001\u0004\u00119'\u0002\u0004p\u0011;\u0004!1\u000b\u0005\b\u0005GDi\u000e\"\u0012F\u0011!\ty\t#8\u0005B\tu\u0005\u0002CAP\u0011;$\t\u0005c?\u0015\r\tM\u0003R E\u0000\u0011\u001d\t)\u000b#?A\u0002QB\u0001\"!+\tz\u0002\u0007\u0011\u0011\u0010\u0005\bm\"U\u0007\u0019\u0001B4\u0011%\u0019I\u0010#6\u0011\u0002\u0003\u00071\f\u0003\u0006\u0002*\"U\u0007\u0013!a\u0001\u0003sBq!#\u00037\t\u000bIY!\u0001\toK^$\u0016\u0010]3QCJ\fW.\u001a;feRA!1KE\u0007\u0013\u001fI\t\u0002C\u0004w\u0013\u000f\u0001\rAa\u001a\t\u0013\re\u0018r\u0001I\u0001\u0002\u0004Y\u0006BCAU\u0013\u000f\u0001\n\u00111\u0001\u0002z!9\u0011R\u0003\u001c\u0005\u0006%]\u0011A\u00048fo\u0016C\u0018n\u001d;f]RL\u0017\r\u001c\u000b\t\u0005'JI\"c\u0007\n\u001e!9a/c\u0005A\u0002\t\u001d\u0004\"CB}\u0013'\u0001\n\u00111\u0001\\\u0011)\tI+c\u0005\u0011\u0002\u0003\u0007\u0011\u0011\u0010\u0005\b\u0013C1D\u0011BE\u0012\u0003)1'/Z:i\u001d\u0006lWM]\u000b\u0003\u0013K\u0001BaCE\u0014E&\u0019\u0011\u0012\u0006\u0004\u0003\u0013\u0019+hn\u0019;j_:\u0004\u0004bBE\u0017m\u0011\u0015\u0011rF\u0001\u0018]\u0016<8+\u001f8uQ\u0016$\u0018n\u0019,bYV,\u0007+\u0019:b[N$B!#\r\n4A)1\u0011CB\f!\"A\u0011RGE\u0016\u0001\u0004\u0019y!\u0001\u0005be\u001e$\u0018\u0010]3t\u0011\u001dIiC\u000eC\u0003\u0013s!b!#\r\n<%u\u0002\u0002CE\u001b\u0013o\u0001\raa\u0004\t\u0011%}\u0012r\u0007a\u0001\u0013K\t\u0011B\u001a:fg\"t\u0015-\\3\t\u000f%\rc\u0007\"\u0002\nF\u00051b.Z<Ts:$\b.\u001a;jGZ\u000bG.^3QCJ\fW\u000eF\u0003Q\u0013\u000fJY\u0005\u0003\u0005\nJ%\u0005\u0003\u0019\u0001BP\u0003\u001d\t'o\u001a;za\u0016D\u0001B^E!!\u0003\u0005\rA\u0019\u0005\b\u0013\u001f2D\u0011AE)\u0003UqWm^*z]RDW\r^5d)f\u0004X\rU1sC6$bAa\u0015\nT%U\u0003b\u0002<\nN\u0001\u0007\u0011q\r\u0005\t\u0003SKi\u00051\u0001\u0002z!9\u0011\u0012\f\u001c\u0005\u0002%m\u0013A\u00068foNKh\u000e\u001e5fi&\u001cG+\u001f9f!\u0006\u0014\u0018-\\:\u0015\t%u\u0013r\f\t\u0007\u0007#\u00199Ba\u0015\t\u000f%\u0005\u0014r\u000ba\u00019\u0005\u0019a.^7\t\u000f%\u0015d\u0007\"\u0001\nh\u0005!b.Z<Fq&\u001cH/\u001a8uS\u0006d7k[8mK6$bAa\u0013\nj%5\u0004bBE6\u0013G\u0002\r\u0001N\u0001\u0006E\u0006\u001c\u0018n\u001d\u0005\b\u0003{L\u0019\u00071\u0001\u000b\u0011\u001dI)G\u000eC\u0001\u0013c\"BBa\u0013\nt%U\u0014\u0012PE?\u0013\u007fBqA^E8\u0001\u0004\u00119\u0007\u0003\u0005\nx%=\u0004\u0019\u0001BP\u0003\u0011IgNZ8\t\u0011%m\u0014r\u000ea\u0001\u0003s\nQA\u001a7bONDqa!?\np\u0001\u00071\fC\u0004\u0002~&=\u0004\u0019\u0001\u0006\t\u000f%\re\u0007\"\u0006\u00017\u0005\tr)\u0011#U?N[u\nT#N?\u001ac\u0015iR*\t\u000f%\u001de\u0007\"\u0001\n\n\u0006ia.Z<H\u0003\u0012#6k[8mK6$\u0002Ba\u0013\n\f&5\u0015r\u0012\u0005\bm&\u0015\u0005\u0019\u0001B4\u0011\u001d\ti0#\"A\u0002QB\u0001\"c\u001e\n\u0006\u0002\u0007!q\u0014\u0005\b\u0013'3DQAEK\u0003A1'/Z:i\u000bbL7\u000f^3oi&\fG\u000e\u0006\u0003\u0003T%]\u0005\u0002CEM\u0013#\u0003\r!a\u001a\u0002\rM,hMZ5y\u0011\u001dIiJ\u000eC\u0003\u0013?\u000bQB\\3x)f\u0004XmU6pY\u0016lWC\u0001B&\u0011\u001dI\u0019K\u000eC\u0003\u0013K\u000b\u0001B\\3x\u00072\f7o\u001d\u000b\t\r\u0007L9+#+\n,\"9a/#)A\u0002\t\u001d\u0004\"CB}\u0013C\u0003\n\u00111\u0001\\\u0011)\tI+#)\u0011\u0002\u0003\u0007\u0011\u0011\u0010\u0005\b\u0013_3D\u0011AEY\u0003AqWm^\"mCN\u001cx+\u001b;i\u0013:4w\u000e\u0006\u0007\u0007D&M\u0016RWE]\u0013{Ky\fC\u0004w\u0013[\u0003\rAa\u001a\t\u0011%]\u0016R\u0016a\u0001\u0007\u001f\tq\u0001]1sK:$8\u000f\u0003\u0005\n<&5\u0006\u0019\u0001E\r\u0003\u0015\u00198m\u001c9f\u0011%\u0019I0#,\u0011\u0002\u0003\u00071\f\u0003\u0006\u0002*&5\u0006\u0013!a\u0001\u0003sBq!c17\t\u000bI)-A\u0007oK^,%O]8s\u00072\f7o\u001d\u000b\u0005\r\u0007L9\rC\u0004w\u0013\u0003\u0004\rAa\u001a\t\u000f%-g\u0007\"\u0002\nN\u0006qa.Z<N_\u0012,H.Z\"mCN\u001cH\u0003CDy\u0013\u001fL\t.c5\t\u000fYLI\r1\u0001\u0003h!I1\u0011`Ee!\u0003\u0005\ra\u0017\u0005\u000b\u0003SKI\r%AA\u0002\u0005e\u0004bBElm\u0011\u0015\u0011\u0012\\\u0001\u001a]\u0016<\u0018I\\8os6|Wo\u001d$v]\u000e$\u0018n\u001c8DY\u0006\u001c8\u000f\u0006\u0004\u0007D&m\u0017R\u001c\u0005\n\u0007sL)\u000e%AA\u0002mC!\"!+\nVB\u0005\t\u0019AA=\u0011\u001dI\tO\u000eC\u0003\u0013G\f\u0011D\\3x\u0003:|g._7pkN4UO\\2uS>tg+\u00197vKR)\u0001+#:\nh\"91\u0011`Ep\u0001\u0004Y\u0006BCAU\u0013?\u0004\n\u00111\u0001\u0002z!9\u00112\u001e\u001c\u0005\u0002%5\u0018\u0001\u00048fo&k\u0007\u000f\\\"mCN\u001cH\u0003\u0003Db\u0013_L\t0c=\t\u000fYLI\u000f1\u0001\u0003h!I1\u0011`Eu!\u0003\u0005\ra\u0017\u0005\u000b\u0003SKI\u000f%AA\u0002\u0005e\u0004bBE|m\u0011\u0015\u0011\u0012`\u0001\u0013]\u0016<(+\u001a4j]\u0016lWM\u001c;DY\u0006\u001c8\u000f\u0006\u0003\n|*=\u0002cA\u001b\n~\u001a1\u0011r \u0001\u0001\u0015\u0003\u0011QCU3gS:,W.\u001a8u\u00072\f7o]*z[\n|Gn\u0005\u0003\n~\u001a\r\u0007B\u0003F\u0003\u0013{\u0014\t\u0011)A\u0005i\u00051qn\u001e8feBB!B#\u0003\n~\n\u0005\t\u0015!\u0003\\\u0003\u0011\u0001xn\u001d\u0019\t\u0011\u0005Ki\u0010\"\u0005\u0001\u0015\u001b!b!c?\u000b\u0010)E\u0001b\u0002F\u0003\u0015\u0017\u0001\r\u0001\u000e\u0005\b\u0015\u0013QY\u00011\u0001\\\u0011\u001dA\u0018R C!\u0015+!2A\u0006F\f\u0011\u00191(2\u0003a\u0001w\"9!2DE\u007f\t\u0003*\u0015!E5t%\u00164\u0017N\\3nK:$8\t\\1tg\"9q1EE\u007f\t\u0003*\u0005bBD*\u0013{$\t%\u0012\u0005\b\u0015GIi\u0010\"\u0011F\u0003IA\u0017m]'fC:Lgn\u001a7fgNt\u0015-\\3\t\u0011\u001d\u0015\u0014R C!\u00037A\u0001B#\u000b\n~\u0012\u0005#2F\u0001\u000eQ\u0006\u001cHK]1og>;h.\u001a:\u0015\u0007\u0019Si\u0003C\u0004\u0002d*\u001d\u0002\u0019\u0001\u001b\t\u000f\re\u0018R\u001fa\u00017\"9!2\u0007\u001c\u0005\u0006)U\u0012A\u00048fo\u0016\u0013(o\u001c:Ts6\u0014w\u000e\u001c\u000b\u0004i)]\u0002B\u0002<\u000b2\u0001\u00071\u0010C\u0004\u000b<Y\"\tA#\u0010\u0002\u001b9,wo\u0015;vENKXNY8m)\u001d!$r\bF!\u0015\u000bBaA\u001eF\u001d\u0001\u0004Y\b\u0002\u0003F\"\u0015s\u0001\r!a\u001a\u0002\u001d5L7o]5oO6+7o]1hK\"I!r\tF\u001d!\u0003\u0005\rAR\u0001\nSN\u0004\u0016mY6bO\u0016DqAc\u00137\t\u0003\tY\"A\ntk\u001e\f'/\u001a3Ts6\u0014w\u000e\\(s'\u0016dg\rC\u0004\u000bPY\"\tAB#\u0002\r1|7m[(L\u0011!Q\u0019F\u000eC\u0001\r)U\u0013\u0001\u00027pG.$2A\u0012F,\u0011%QIF#\u0015\u0005\u0002\u0004QY&A\u0004iC:$G.\u001a:\u0011\t-\u0011)B\u0006\u0005\b\u0015?2D\u0011\u0001\u0004\u0016\u0003\u0019)h\u000e\\8dW\"1q\u0011\u0001\u001c\u0005\u0002\u0015CaAa97\t\u0003)\u0005BBB@m\u0011\u0005Q\t\u0003\u0004\u0003\u0012Z\"\t!\u0012\u0005\u0007\u0015W2D\u0011A#\u0002\u001b%\u001c(i\u001c;u_6\u001cE.Y:t\u0011\u001999A\u000eC\u0001\u000b\"1q1\u0005\u001c\u0005\u0002\u0015Caab\n7\t\u0003)\u0005BBD\u0006m\u0011\u0005Q\t\u0003\u0004\b,Y\"\t!\u0012\u0005\u0007\u000f'1D\u0011A#\t\r\u001d=b\u0007\"\u0001F\u0011\u001999D\u000eC\u0001\u000b\"1q1\t\u001c\u0005\u0002\u0015CaAc\u00077\t\u0003)\u0005BBD\u0010m\u0011\u0005S\t\u0003\u0004\u0003hZ\"\t!\u0012\u0005\u0007\u0005W4D\u0011A#\t\r\r\re\u0007\"\u0001F\u0011\u0019\u0011yO\u000eC\u0001\u000b\"11q\u0011\u001c\u0005\u0002\u0015CaAa=7\t\u0003)\u0005B\u0002B|m\u0011\u0005Q\t\u0003\u0004\u0004\fZ\"\t!\u0012\u0005\u0007\u0015+3D\u0011A#\u0002\u0017%\u001c\u0018J\u001c<be&\fg\u000e\u001e\u0005\u0007\u0003\u007f1D\u0011A#\t\r\u0005\rb\u0007\"\u0001F\u0011\u0019\t)B\u000eC\u0001\u000b\"1\u00111\u000b\u001c\u0005\u0002\u0015Ca!a\u00177\t\u0003)\u0005BBA\u0014m\u0011\u0005Q\t\u0003\u0004\u0002DY\"\t!\u0012\u0005\u0007\u0003\u000f2D\u0011A#\t\r\u0005=c\u0007\"\u0001F\u0011\u0019\t9F\u000eC\u0001\u000b\"1\u00111\u0007\u001c\u0005\u0002\u0015Ca!a\u00137\t\u0003)\u0005BBA\u001em\u0011\u0005Q\t\u0003\u0004\u0002\nY\"\t!\u0012\u0005\u0007\u0003o1D\u0011A#\t\r\u00055a\u0007\"\u0001F\u0011\u0019\t\tB\u000eC\u0001\u000b\"1Q\u0011\u001a\u001c\u0005\u0002\u0015Ca!\"17\t\u0003)\u0005BBCcm\u0011\u0005Q\t\u0003\u0004\u0006>Z\"\t!\u0012\u0005\u0007\u000bs3D\u0011I#\t\r\u001dma\u0007\"\u0001F\u0011\u0019\tyF\u000eC\u0001\u000b\"1qq\b\u001c\u0005\u0002\u0015CaAc37\t\u0003)\u0015AF5t!\u0006\u001c7.Y4f\u001f\nTWm\u0019;Pe\u000ec\u0017m]:\t\r)=g\u0007\"\u0001F\u0003UI7/T8ek2,wJ]'pIVdWm\u00117bgNDaAc57\t\u0003)\u0015AB5t%>|G\u000f\u0003\u0004\u000bXZ\"\t!R\u0001\u000eSN\u0014vn\u001c;QC\u000e\\\u0017mZ3\t\r)mg\u0007\"\u0001F\u00031I7OU8piNKXNY8m\u0011\u0019QyN\u000eC\u0001\u000b\u0006q\u0011n]#naRL\b+Y2lC\u001e,\u0007B\u0002Frm\u0011\u0005Q)A\njg\u0016k\u0007\u000f^=QC\u000e\\\u0017mZ3DY\u0006\u001c8\u000f\u0003\u0004\u000bhZ\"\t!R\u0001\u0010SN,eMZ3di&4XMU8pi\"1!2\u001e\u001c\u0005\u0002\u0015\u000bq\u0003[1t\u001f:d\u0017PQ8ui>l7+\u001e2dY\u0006\u001c8/Z:\t\r)=h\u0007\"\u0002F\u00039I7\u000fT1{s\u0006\u001b7-Z:t_JDaAc=7\t\u000b)\u0015aE5t\u001fZ,'O]5eC\ndW-T3nE\u0016\u0014\bB\u0002F|m\u0011\u0015Q)\u0001\u000bjg&sG/\u001a:qe\u0016$XM],sCB\u0004XM\u001d\u0005\b\u0015w4DQ\u0001F\u007f\u0003\u001d9W\r\u001e$mC\u001e$B!!\u001f\u000b\u0000\"AQ1\u0006F}\u0001\u0004\tI\bC\u0004\f\u0004Y\")a#\u0002\u0002\u000f!\f7O\u00127bOR\u0019aic\u0002\t\u0011\u0015-2\u0012\u0001a\u0001\u0003sBqac\u00017\t\u0003YY\u0001F\u0002G\u0017\u001bAq!b\u000b\f\n\u0001\u0007A\u0004C\u0004\f\u0012Y\")ac\u0005\u0002\u0017!\f7/\u00117m\r2\fwm\u001d\u000b\u0004\r.U\u0001\u0002CC\u0016\u0017\u001f\u0001\r!!\u001f\t\u000f-ea\u0007\"\u0001\f\u001c\u000591/\u001a;GY\u0006<G\u0003\u0002Ce\u0017;A\u0001\"b\u000b\f\u0018\u0001\u0007\u0011\u0011\u0010\u0005\b\u0017C1D\u0011AF\u0012\u0003%\u0011Xm]3u\r2\fw\r\u0006\u0003\u0005J.\u0015\u0002\u0002CC\u0016\u0017?\u0001\r!!\u001f\t\r-%b\u0007\"\u0001\u0016\u0003)\u0011Xm]3u\r2\fwm\u001d\u0005\b\u0003G2D\u0011IF\u0017)\u0011\t9gc\f\t\u0011\u0005]42\u0006a\u0001\u0003sBqac\r7\t\u0003Y)$A\u0005j]&$h\t\\1hgR!A\u0011ZF\u001c\u0011!)Yc#\rA\u0002\u0005e\u0004bBE>m\u0011\u0015\u00111\u0018\u0005\b\u0017{1D\u0011AF \u0003%1G.Y4t?\u0012*\u0017\u000fF\u0002\u0017\u0017\u0003B\u0001bc\u0011\f<\u0001\u0007\u0011\u0011P\u0001\u0003MNDqac\u00127\t\u0003YI%\u0001\u0007sC^4G.Y4t?\u0012*\u0017\u000fF\u0002\u0017\u0017\u0017B\u0001\"a#\fF\u0001\u0007\u0011\u0011\u0010\u0005\u0007\u0017\u001f2DQA#\u0002\u0013!\f7oR3ui\u0016\u0014\bBBF*m\u0011\u0005Q)A\tjg6{G-\u001e7f\u001d>$X*\u001a;i_\u0012Daac\u00167\t\u0003)\u0015AD5t'R\fG/[2N_\u0012,H.\u001a\u0005\u0007\u001772DQA#\u0002-%\u001c\u0018J\\5uS\u0006d\u0017N_3e)>$UMZ1vYRDaac\u00187\t\u000b)\u0015!C5t)\"L7oU=n\u0011\u0019Y\u0019G\u000eC\u0003\u000b\u00069\u0011n]#se>\u0014\bBBF4m\u0011\u0015Q)A\u0006jg\u0016\u0013(o\u001c8f_V\u001c\bBBF6m\u0011\u0005Q)\u0001\u000ejg\"Kw\r[3s\u001fJ$WM\u001d+za\u0016\u0004\u0016M]1nKR,'\u000f\u0003\u0004\b\u0010Y\"\t!\u0012\u0005\u0007\u0017c2DQA#\u0002'%\u001cH)\u001a:jm\u0016$g+\u00197vK\u000ec\u0017m]:\t\r-Ud\u0007\"\u0002F\u0003UI7/T3uQ>$w+\u001b;i\u000bb$XM\\:j_:Daa#\u001f7\t\u000b)\u0015aE5t\u0003:|g._7pkN4UO\\2uS>t\u0007BBF?m\u0011\u0015Q)\u0001\u000bjg\u0012+G.Y7cI\u00064\u0017PR;oGRLwN\u001c\u0005\u0007\u0017\u00033DQA#\u0002%%\u001cH)\u001a7b[\n$\u0017MZ=UCJ<W\r\u001e\u0005\u0007\u0017\u000b3DQA#\u0002%%\u001cH)\u001a4j]\u0016$\u0017J\u001c)bG.\fw-\u001a\u0005\u0007\u0017\u00133DQA#\u0002!9,W\rZ:GY\u0006$8\t\\1tg\u0016\u001c\bBBFGm\u0011\u0015Q)A\u000bjgB\u000bG\u000f^3s]RK\b/\u001a,be&\f'\r\\3\t\u000f\u0005-h\u0007\"\u0001\f\u0012R\u0019acc%\t\u000f\u0005E8r\u0012a\u0001i!11r\u0013\u001c\u0005\u0002\u0015\u000bA#[:J]\u0012+g-Y;mi:\u000bW.Z:qC\u000e,\u0007bBFNm\u0011\u0005\u00111D\u0001\u000fK\u001a4Wm\u0019;jm\u0016|uO\\3s\u0011\u001dYyJ\u000eC\u0001\u00037\t\u0011c]6jaB\u000b7m[1hK>\u0013'.Z2u\u0011\u001dY\u0019K\u000eC\u0003\u00037\tqb]6ja\u000e{gn\u001d;sk\u000e$xN\u001d\u0005\u0007\u0017O3DQA#\u0002#%\u001cx*\\5ui\u0006\u0014G.\u001a)sK\u001aL\u0007\u0010\u0003\u0004\f,Z\"\t!R\u0001\u000eSN,U\u000e\u001d;z!J,g-\u001b=\t\r-=f\u0007\"\u0001F\u0003)I7O\u0012\"pk:$W\r\u001a\u0005\u0007\u0017g3DQA#\u0002#%\u001cXj\u001c8p[>\u0014\b\u000f[5d)f\u0004X\r\u0003\u0004\f8Z\"\t!R\u0001\u000bSN\u001cFO]5di\u001a\u0003\u0006BBF^m\u0011\u0005Q)\u0001\bjgN+'/[1mSj\f'\r\\3\t\r-}f\u0007\"\u0001F\u0003MA\u0017m\u001d\"sS\u0012<W-\u00118o_R\fG/[8o\u0011\u0019Y\u0019M\u000eC\u0001\u000b\u0006a\u0011n\u001d#faJ,7-\u0019;fI\"91r\u0019\u001c\u0005\u0002-%\u0017A\u00053faJ,7-\u0019;j_:lUm]:bO\u0016,\"ac3\u0011\u000b-Yi-a\u001a\n\u0007-=gA\u0001\u0004PaRLwN\u001c\u0005\b\u0017'4D\u0011AFe\u0003I!W\r\u001d:fG\u0006$\u0018n\u001c8WKJ\u001c\u0018n\u001c8\t\u000f-]g\u0007\"\u0001\fZ\u0006\u0019B-\u001a9sK\u000e\fG/\u001a3QCJ\fWNT1nKV\u001112\u001c\t\u0005\u0017-5'\r\u0003\u0004\f`Z\"\t!R\u0001#Q\u0006\u001cH)\u001a9sK\u000e\fG/\u001a3J]\",'/\u001b;b]\u000e,\u0017I\u001c8pi\u0006$\u0018n\u001c8\t\u000f-\rh\u0007\"\u0001\fJ\u0006aB-\u001a9sK\u000e\fG/\u001a3J]\",'/\u001b;b]\u000e,W*Z:tC\u001e,\u0007BBFtm\u0011\u0005Q)A\u0011iCN$U\r\u001d:fG\u0006$X\rZ(wKJ\u0014\u0018\u000eZ5oO\u0006sgn\u001c;bi&|g\u000eC\u0004\flZ\"\ta#3\u00027\u0011,\u0007O]3dCR,Gm\u0014<feJLG-\u001b8h\u001b\u0016\u001c8/Y4f\u0011\u0019YyO\u000eC\u0001\u000b\u00061\u0002.Y:NS\u001e\u0014\u0018\r^5p]\u0006sgn\u001c;bi&|g\u000eC\u0004\ftZ\"\ta#3\u0002!5LwM]1uS>tW*Z:tC\u001e,\u0007bBF|m\u0011\u00051\u0012Z\u0001\u0011[&<'/\u0019;j_:4VM]:j_:Dqac?7\t\u0003Yi0\u0001\u0007fY&\u001c\u0018n\u001c8MKZ,G.\u0006\u0002\f\u0000B!1b#4\u001d\u0011\u001da\u0019A\u000eC\u0001\u0017\u0013\f1#[7qY&\u001c\u0017\u000e\u001e(pi\u001a{WO\u001c3Ng\u001eDa\u0001d\u00027\t\u0003)\u0015!E5t\u0007>l\u0007/\u001b7f)&lWm\u00148ms\"9A2\u0002\u001c\u0005\u0002-%\u0017AF2p[BLG.\u001a+j[\u0016|e\u000e\\=NKN\u001c\u0018mZ3\t\r1=a\u0007\"\u0002F\u0003=I7oT;uKJ\f5mY3tg>\u0014\bB\u0002G\nm\u0011\u0015Q)\u0001\u0007jg>+H/\u001a:GS\u0016dG\r\u0003\u0004\r\u0018Y\")!R\u0001\tSN\u001cF/\u00192mK\"1A2\u0004\u001c\u0005\u0006\u0015\u000bq\u0002[1t->d\u0017\r^5mKRK\b/\u001a\u0005\u0007\u0019?1DQA#\u0002)%\u001c\bK]5nCJL8i\u001c8tiJ,8\r^8s\u0011\u0019a\u0019C\u000eC\u0003\u000b\u00061\u0012n]!vq&d\u0017.\u0019:z\u0007>t7\u000f\u001e:vGR|'\u000f\u0003\u0004\r(Y\")!R\u0001\u0015SN\u001c\u0015m]3BaBd\u0017p\u0014:V]\u0006\u0004\b\u000f\\=\t\r1-b\u0007\"\u0002F\u0003)I7oQ1tK\u000e{\u0007/\u001f\u0005\u0007\u0019_1DQA#\u0002\u001d9,W\rZ:J[Bd7\t\\1tg\"1A2\u0007\u001c\u0005\u0006\u0015\u000b!\"[:J[BdwJ\u001c7z\u0011\u0019a9D\u000eC\u0003\u000b\u0006Y\u0011n]'pIVdWMV1s\u0011\u0019aYD\u000eC\u0001\u000b\u0006A\u0011n]*uCRL7\r\u0003\u0004\r@Y\")!R\u0001\u0014SN\u001cF/\u0019;jG\u000e{gn\u001d;sk\u000e$xN\u001d\u0005\u0007\u0019\u00072DQA#\u0002\u001d%\u001c8\u000b^1uS\u000elU-\u001c2fe\"1Ar\t\u001c\u0005\u0006\u0015\u000bQ\"[:Ti\u0006$\u0018nY(x]\u0016\u0014\bB\u0002G&m\u0011%Q)A\bjg:{Go\u0014<feJLG\rZ3o\u0011\u0019ayE\u000eC\u0003\u000b\u0006\u0011\u0012n]#gM\u0016\u001cG/\u001b<fYf4\u0015N\\1m\u0011\u0019a\u0019F\u000eC\u0003\u000b\u0006\t\u0013n]#gM\u0016\u001cG/\u001b<fYf4\u0015N\\1m\u001fJtu\u000e^(wKJ\u0014\u0018\u000e\u001a3f]\"1Ar\u000b\u001c\u0005\u0006\u0015\u000b!\"[:U_BdUM^3m\u0011\u0019aYF\u000eC\u0003\u000b\u00069\u0011n\u001d'pG\u0006d\u0007\u0006\u0003G-\u0019?b)\u0007$\u001b\u0011\u0007-a\t'C\u0002\rd\u0019\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3eC\ta9'\u0001\u000eVg\u0016\u0004\u0013n\u001d'pG\u0006dGk\u001c\"m_\u000e\\\u0007%\u001b8ti\u0016\fG-\t\u0002\rl\u00051!GL\u00192]ABa\u0001d\u001c7\t\u000b)\u0015AD5t\u0019>\u001c\u0017\r\u001c+p\u00052|7m\u001b\u0005\u0007\u0019g2DQA#\u0002\u0015%\u001c8i\u001c8ti\u0006tG\u000f\u0003\u0004\b4Y\"\t!\u0012\u0005\u0007\u000f'2D\u0011A#\t\r1md\u0007\"\u0002F\u0003YI7o\u0015;sk\u000e$XO]1m%\u00164\u0017N\\3nK:$\bB\u0002G@m\u0011\u0005Q)\u0001\fjg>sG.\u001f*fM&tW-\\3oi6+WNY3s\u0011\u0019a\u0019I\u000eC\u0005\u000b\u0006\t\u0012n\u001d#fG2\f'/\u001a3Cs>;h.\u001a:\t\r1\u001de\u0007\"\u0002F\u0003qI7o\u0015;sk\u000e$XO]1m%\u00164\u0017N\\3nK:$X*Z7cKJDa\u0001d#7\t\u000b)\u0015AF5t!>\u001c8/\u001b2mK&s'+\u001a4j]\u0016lWM\u001c;\t\u000f1=e\u0007\"\u0002\r\u0012\u0006q\u0011n]%oG>l\u0007\u000f\\3uK&sGc\u0001$\r\u0014\"9\u0011\u0011\u001fGG\u0001\u0004!\u0004B\u0002GLm\u0011\u0005Q)\u0001\u0004fq&\u001cHo\u001d\u0005\u0007\u001973DQA#\u0002\u001b%\u001c\u0018J\\5uS\u0006d\u0017N_3e\u0011\u001dayJ\u000eC\u0001\u0019C\u000bA\"[:UQJ,\u0017\rZ:bM\u0016$2A\u0012GR\u0011!a)\u000b$(A\u00021\u001d\u0016a\u00029veB|7/\u001a\t\u0004k1%fA\u0002GV\u0001\u0001ciKA\u0005Ts6\u0014w\u000e\\(qgN9A\u0012\u0016\u0006\r02U\u0006cA\u0006\r2&\u0019A2\u0017\u0004\u0003\u000fA\u0013x\u000eZ;diB\u00191\u0002d.\n\u00071efA\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0003\u0006\r>2%&Q3A\u0005\u0002\u0015\u000bQ\"[:GY\u0006<'+\u001a7bi\u0016$\u0007B\u0003Ga\u0019S\u0013\t\u0012)A\u0005\r\u0006q\u0011n\u001d$mC\u001e\u0014V\r\\1uK\u0012\u0004\u0003bCC\u0016\u0019S\u0013)\u001a!C\u0001\u0003wC1\u0002d2\r*\nE\t\u0015!\u0003\u0002z\u0005)Q.Y:lA!9\u0011\t$+\u0005\u00021-GC\u0002GT\u0019\u001bdy\rC\u0004\r>2%\u0007\u0019\u0001$\t\u0011\u0015-B\u0012\u001aa\u0001\u0003sB!\u0002d5\r*\u0006\u0005I\u0011\u0001Gk\u0003\u0011\u0019w\u000e]=\u0015\r1\u001dFr\u001bGm\u0011%ai\f$5\u0011\u0002\u0003\u0007a\t\u0003\u0006\u0006,1E\u0007\u0013!a\u0001\u0003sB!\u0002$8\r*F\u0005I\u0011\u0001Gp\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"\u0001$9+\u0007\u0019c\u0019o\u000b\u0002\rfB!Ar\u001dGy\u001b\taIO\u0003\u0003\rl25\u0018!C;oG\",7m[3e\u0015\rayOB\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002\u0002Gz\u0019S\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0011)a9\u0010$+\u0012\u0002\u0013\u0005A\u0012`\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\taYP\u000b\u0003\u0002z1\r\bB\u0003G\u0000\u0019S\u000b\t\u0011\"\u0011\u0006@\u0005i\u0001O]8ek\u000e$\bK]3gSbD\u0011\"d\u0001\r*\u0006\u0005I\u0011A\u000e\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\t\u00155\u001dA\u0012VA\u0001\n\u0003iI!\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\teQ2\u0002\u0005\tG5\u0015\u0011\u0011!a\u00019!QQr\u0002GU\u0003\u0003%\t%$\u0005\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!d\u0005\u0011\r5UQr\u0003B\r\u001b\u0005\u0001\u0014bAG\ra\tA\u0011\n^3sCR|'\u000f\u0003\u0006\u000e\u001e1%\u0016\u0011!C\u0001\u001b?\t\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004\r6\u0005\u0002\"C\u0012\u000e\u001c\u0005\u0005\t\u0019\u0001B\r\u0011%i)\u0003$+\u0002\u0002\u0013\u0005\u0003&\u0001\u0005iCND7i\u001c3f\u0011)9)\u000e$+\u0002\u0002\u0013\u0005S\u0012\u0006\u000b\u0003\u000b\u0003B!\"$\f\r*\u0006\u0005I\u0011IG\u0018\u0003\u0019)\u0017/^1mgR\u0019a)$\r\t\u0013\rjY#!AA\u0002\te\u0001bBG\u001bm\u0011\u0005QrG\u0001\u0013[\u0006\u00148N\u00127bON\u001cu.\u001c9mKR,G\r\u0006\u0003\u0005J6e\u0002\u0002CC\u0016\u001bg\u0001\r!!\u001f\t\u000f5ub\u0007\"\u0001\u000e@\u0005\u0001R.\u0019:l\u00032d7i\\7qY\u0016$X\r\u001a\u000b\u0003\t\u0013Da!d\u00117\t\u000b)\u0015aC5t\u0019>\u001c\u0017\r^1cY\u0016Dq\u0001c.7\t\u0003AI\f\u0003\u0004\u000eJY\"\taG\u0001\ta\u0006\u0014\u0018-\u001c)pg\"9\u0011Q\u0015\u001c\u0005\u0002\u0005m\u0001bBG(m\u0011\u0015\u00111D\u0001\ng\u00064WmT<oKJDq!d\u00157\t\u000b\tY\"A\u0006bgN,'\u000f^(x]\u0016\u0014\bbBG,m\u0011\u0005\u00111D\u0001\u000e_JLw-\u001b8bY>;h.\u001a:\t\u000f5mc\u0007\"\u0001\u000e^\u0005Iqn\u001e8fe~#S-\u001d\u000b\u0004-5}\u0003bBAS\u001b3\u0002\r\u0001\u000e\u0005\b\u001bG2D\u0011ABP\u0003)ywO\\3s\u0007\"\f\u0017N\u001c\u0005\b\u000f/2D\u0011ABP\u0011\u001diIG\u000eC\u0001\u001bW\nab\\<oKJ\u001c\u0018\n^3sCR|'/\u0006\u0002\u000enA)1\u0011CG8i%!Q\u0012DB\u000e\u0011\u001dQIC\u000eC\u0001\u001bg\"2ARG;\u0011\u001d\t\u0019/$\u001dA\u0002QBq!$\u001f7\t\u0003iY(\u0001\u0007pe&<\u0017N\\1m\u001d\u0006lW-F\u0001|Q!i9\bd\u0018\u000e\u00001%\u0014EAGA\u0003I)6/\u001a\u0011v]\u0016D\b/\u00198eK\u0012t\u0015-\\3\t\u000f5\u0015e\u0007\"\u0001\u000e|\u0005qQO\\3ya\u0006tG-\u001a3OC6,\u0007bBGEm\u0011\u0005\u0011q`\u0001\fK:\u001cw\u000eZ3e\u001d\u0006lW\rC\u0004\u000e\u000eZ\"\t!a@\u0002\u0017\u0011,7m\u001c3fI:\u000bW.\u001a\u0005\b\u001b#3D\u0011BGJ\u0003=\tG\rZ'pIVdWmU;gM&DHcA>\u000e\u0016\"9\u0011QAGH\u0001\u0004Y\bbBGMm\u0011\u0005\u0011q`\u0001\r[>$W\u000f\\3Tk\u001a4\u0017\u000e\u001f\u0005\u0007\u001b;3D\u0011A#\u0002#9,W\rZ:N_\u0012,H.Z*vM\u001aL\u0007\u0010C\u0004\u000e\"Z\"\t!d\u001f\u0002\u001d)\fg/Y*j[BdWMT1nK\"9QR\u0015\u001c\u0005\u00025m\u0014A\u00046bm\u0006\u0014\u0015N\\1ss:\u000bW.\u001a\u0005\b\u001bS3D\u0011AA\u0000\u0003QQ\u0017M^1CS:\f'/\u001f(b[\u0016\u001cFO]5oO\"9QR\u0016\u001c\u0005\u0002\u0005}\u0018!\u00046bm\u0006\u001cE.Y:t\u001d\u0006lW\rC\u0004\u000e2Z\")!d-\u0002\u0011\u0019,H\u000e\u001c(b[\u0016$B!a\u001a\u000e6\"AQrWGX\u0001\u0004iI,A\u0005tKB\f'/\u0019;peB\u00191\"d/\n\u00075ufA\u0001\u0003DQ\u0006\u0014\bbBGYm\u0011%Q\u0012\u0019\u000b\u0007\u0003Oj\u0019-$2\t\u00115]Vr\u0018a\u0001\u001bsC\u0001\"#'\u000e@\u0002\u0007Qr\u0019\t\u0005\u000b\u0007jI-\u0003\u0003\u000eL\u0016\u0015#\u0001D\"iCJ\u001cV-];f]\u000e,\u0007bBGhm\u0011\u0005Q\u0012[\u0001\u000fMVdGNT1nK\u0006\u001bh*Y7f)\rYX2\u001b\u0005\t\u001boki\r1\u0001\u000e:\"9Q\u0012\u0017\u001c\u0005\u0006\u0005}\bbBGmm\u0011EQ2\\\u0001\u0019GJ,\u0017\r^3BEN$(/Y2u)f\u0004XmU=nE>dG\u0003\u0003En\u001b;ly.$9\t\u000fYl9\u000e1\u0001\u0003h!91\u0011`Gl\u0001\u0004Y\u0006\u0002CAU\u001b/\u0004\r!!\u001f\t\u000f5\u0015h\u0007\"\u0005\u000eh\u0006)2M]3bi\u0016\fE.[1t)f\u0004XmU=nE>dG\u0003\u0003EN\u001bSlY/$<\t\u000fYl\u0019\u000f1\u0001\u0003h!91\u0011`Gr\u0001\u0004Y\u0006\u0002CAU\u001bG\u0004\r!!\u001f\t\u000f5Eh\u0007\"\u0005\u000et\u000612M]3bi\u0016$\u0016\u0010]3TW>dW-\\*z[\n|G\u000e\u0006\u0006\u0003L5UXr_G}\u001bwDqA^Gx\u0001\u0004\u00119\u0007C\u0004\u0002~6=\b\u0019\u0001\u0006\t\u000f\reXr\u001ea\u00017\"A\u0011\u0011VGx\u0001\u0004\tI\bC\u0004\u000e\u0000Z\"\tB$\u0001\u0002#\r\u0014X-\u0019;f\u00072\f7o]*z[\n|G\u000e\u0006\u0005\u0007D:\raR\u0001H\u0004\u0011\u001d1XR a\u0001\u0005OBqa!?\u000e~\u0002\u00071\f\u0003\u0005\u0002*6u\b\u0019AA=\u0011\u001dqYA\u000eC\t\u001d\u001b\tqc\u0019:fCR,Wj\u001c3vY\u0016\u001cE.Y:t'fl'm\u001c7\u0015\u0011\u001dEhr\u0002H\t\u001d'AqA\u001eH\u0005\u0001\u0004\u00119\u0007C\u0004\u0004z:%\u0001\u0019A.\t\u0011\u0005%f\u0012\u0002a\u0001\u0003sBqAd\u00067\t#qI\"\u0001\rde\u0016\fG/\u001a)bG.\fw-Z\"mCN\u001c8+_7c_2$\u0002Bd\u0007\u000fB9\rcR\t\t\u0004k9uaA\u0002H\u0010\u0001\u0001q\tC\u0001\nQC\u000e\\\u0017mZ3DY\u0006\u001c8oU=nE>d7\u0003\u0002H\u000f\u000fcD!B#\u0002\u000f\u001e\t\u0005\t\u0015!\u00035\u0011)QIA$\b\u0003\u0002\u0003\u0006Ia\u0017\u0005\f\u0005\u001bqiB!A!\u0002\u0013\u00119\u0007\u0003\u0005B\u001d;!\t\u0002\u0001H\u0016)!qYB$\f\u000f09E\u0002b\u0002F\u0003\u001dS\u0001\r\u0001\u000e\u0005\b\u0015\u0013qI\u00031\u0001\\\u0011!\u0011iA$\u000bA\u0002\t\u001d\u0004\u0002CD8\u001d;!\t%a\u0007\t\u0011\u001d]cR\u0004C!\u001do)\"A$\u000f\u000f\u00075rY$C\u0002\u000f>9\n1AT5m\u0011\u001d9YB$\b\u0005B\u0015CqA\u001eH\u000b\u0001\u0004\u00119\u0007C\u0004\u0004z:U\u0001\u0019A.\t\u0011\u0005%fR\u0003a\u0001\u0003sBqA$\u00137\t#qY%A\u000ede\u0016\fG/\u001a*fM&tW-\\3oi\u000ec\u0017m]:Ts6\u0014w\u000e\u001c\u000b\u0007\u0013wtiEd\u0014\t\u000f\rehr\ta\u00017\"A\u0011\u0011\u0016H$\u0001\u0004\tI\bC\u0004\u000fTY\"\tB$\u0016\u0002=\r\u0014X-\u0019;f!\u0006\u001c7.Y4f\u001f\nTWm\u0019;DY\u0006\u001c8oU=nE>dGC\u0002H,\u001dsrY\bE\u00026\u001d32aAd\u0017\u0001\u00019u#\u0001\u0007)bG.\fw-Z(cU\u0016\u001cGo\u00117bgN\u001c\u00160\u001c2pYN!a\u0012LDy\u0011)Q)A$\u0017\u0003\u0002\u0003\u0006I\u0001\u000e\u0005\u000b\u0015\u0013qIF!A!\u0002\u0013Y\u0006\u0002C!\u000fZ\u0011E\u0001A$\u001a\u0015\r9]cr\rH5\u0011\u001dQ)Ad\u0019A\u0002QBqA#\u0003\u000fd\u0001\u00071\fC\u0004\b@9eCQI#\t\u000f)-g\u0012\fC#\u000b\"A1r\u0014H-\t\u000b\nY\u0002\u0003\u0005\u0006\u00169eCQ\tH:)\u0011q)Hd\u001e\u000e\u00059e\u0003B\u0002<\u000fr\u0001\u00071\u0010C\u0004\u0004z:E\u0003\u0019A.\t\u0011\u0005%f\u0012\u000ba\u0001\u0003sBqAd 7\t#q\t)A\u000bde\u0016\fG/Z%na2\u001cE.Y:t'fl'm\u001c7\u0015\u0011\u0019\rg2\u0011HC\u001d\u000fCqA\u001eH?\u0001\u0004\u00119\u0007C\u0004\u0004z:u\u0004\u0019A.\t\u0011\u0005%fR\u0010a\u0001\u0003sBqAd#7\t#qi)\u0001\nde\u0016\fG/Z'fi\"|GmU=nE>dG\u0003CCE\u001d\u001fs\tJd%\t\rYtI\t1\u0001c\u0011\u001d\u0019IP$#A\u0002mC\u0001\"!+\u000f\n\u0002\u0007\u0011\u0011\u0010\u0005\b\u001d/3D\u0011\u0003HM\u0003I\u0019'/Z1uK6{G-\u001e7f'fl'm\u001c7\u0015\u0011\u0019ua2\u0014HO\u001d?CaA\u001eHK\u0001\u0004\u0011\u0007bBB}\u001d+\u0003\ra\u0017\u0005\t\u0003Ss)\n1\u0001\u0002z!9a2\u0015\u001c\u0005\u00129\u0015\u0016aE2sK\u0006$X\rU1dW\u0006<WmU=nE>dG\u0003\u0003D\u000f\u001dOsIKd+\t\rYt\t\u000b1\u0001c\u0011\u001d\u0019IP$)A\u0002mC\u0001\"!+\u000f\"\u0002\u0007\u0011\u0011\u0010\u0005\b\u001d_3D\u0011\u0003HY\u0003i\u0019'/Z1uKZ\u000bG.^3QCJ\fW.\u001a;feNKXNY8m)\u001d\u0001f2\u0017H[\u001doCaA\u001eHW\u0001\u0004\u0011\u0007bBB}\u001d[\u0003\ra\u0017\u0005\t\u0003Ssi\u000b1\u0001\u0002z!9a2\u0018\u001c\u0005\u00129u\u0016aF2sK\u0006$XMV1mk\u0016lU-\u001c2feNKXNY8m)\u001d\u0001fr\u0018Ha\u001d\u0007DaA\u001eH]\u0001\u0004\u0011\u0007bBB}\u001ds\u0003\ra\u0017\u0005\t\u0003SsI\f1\u0001\u0002z!9ar\u0019\u001c\u0005\u00069%\u0017!\u00048foR+'/\\*z[\n|G\u000eF\u0004Q\u001d\u0017tiMd4\t\rYt)\r1\u0001c\u0011%\u0019IP$2\u0011\u0002\u0003\u00071\f\u0003\u0006\u0002*:\u0015\u0007\u0013!a\u0001\u0003sBqAd57\t\u000bq).\u0001\boK^\u001cE.Y:t'fl'm\u001c7\u0015\u0011\u0019\rgr\u001bHm\u001d7DqA\u001eHi\u0001\u0004\u00119\u0007C\u0005\u0004z:E\u0007\u0013!a\u00017\"Q\u0011\u0011\u0016Hi!\u0003\u0005\r!!\u001f\t\u000f9}g\u0007\"\u0002\u000fb\u0006\tb.Z<O_:\u001cE.Y:t'fl'm\u001c7\u0015\u0011\tMc2\u001dHs\u001dODqA\u001eHo\u0001\u0004\u00119\u0007C\u0005\u0004z:u\u0007\u0013!a\u00017\"Q\u0011\u0011\u0016Ho!\u0003\u0005\r!!\u001f\t\u000f9-h\u0007\"\u0001\u000fn\u0006ia.Z<UsB,7+_7c_2$\u0002Ba\u0015\u000fp:Eh2\u001f\u0005\bm:%\b\u0019\u0001B4\u0011%\u0019IP$;\u0011\u0002\u0003\u00071\f\u0003\u0006\u0002*:%\b\u0013!a\u0001\u0003sBqAd>7\t\u0003qI0\u0001\bbG\u000e,7o\u001d\"pk:$\u0017M]=\u0015\u0007QrY\u0010C\u0004\u0002r:U\b\u0019\u0001\u001b\t\u000f9}h\u0007\"\u0001\u0010\u0002\u0005!\u0012n\u001d'fgN\f5mY3tg&\u0014G.\u001a+iC:$2ARH\u0002\u0011\u001dy)A$@A\u0002Q\nQa\u001c;iKJD!b$\u00037\u0001\u0004\u0005\t\u0015)\u00035\u00039y\u0006O]5wCR,w+\u001b;iS:Dqa$\u00047\t\u0003\tY\"A\u0007qe&4\u0018\r^3XSRD\u0017N\u001c\u0005\b\u001f#1D\u0011AH\n\u0003E\u0001(/\u001b<bi\u0016<\u0016\u000e\u001e5j]~#S-\u001d\u000b\u0004-=U\u0001bBAr\u001f\u001f\u0001\r\u0001\u000e\u0005\b\u001f31D\u0011AH\u000e\u0003A\u0019X\r\u001e)sSZ\fG/Z,ji\"Lg\u000e\u0006\u0003\u0005J>u\u0001bBAr\u001f/\u0001\r\u0001\u000e\u0005\u0007\u001fC1DQA#\u0002#!\f7/Q2dKN\u001c(i\\;oI\u0006\u0014\u0018\u0010\u0003\u0007\u0010&Y\u0012\t\u00111A\u0005\u0002\u0001y9#A\u0015tG\u0006d\u0017\r\n:fM2,7\r\u001e\u0013j]R,'O\\1mIMKXNY8mg\u0012\"\u0013N\u001c4pg~#S-\u001d\u000b\u0004-=%\u0002\"C\u0012\u0010$\u0005\u0005\t\u0019AH\u0016!\r)tR\u0006\u0004\u0007\u001f_\u0001Ai$\r\u0003\u0017QK\b/\u001a%jgR|'/_\n\b\u001f[QAr\u0016G[\u0011-y)d$\f\u0003\u0012\u0004%\t!\"\u0003\u0002\u0013Y\fG.\u001b3Ge>l\u0007bCH\u001d\u001f[\u0011\t\u0019!C\u0001\u001fw\tQB^1mS\u00124%o\\7`I\u0015\fHc\u0001\f\u0010>!I1ed\u000e\u0002\u0002\u0003\u0007A1 \u0005\f\u001f\u0003ziC!E!B\u0013!Y0\u0001\u0006wC2LGM\u0012:p[\u0002B1\"c\u001e\u0010.\tU\r\u0011\"\u0001\u0003\u001e\"YqrIH\u0017\u0005#\u0005\u000b\u0011\u0002BP\u0003\u0015IgNZ8!\u0011-yYe$\f\u0003\u0016\u0004%\ta$\u0014\u0002\tA\u0014XM^\u000b\u0003\u001fWA1b$\u0015\u0010.\tE\t\u0015!\u0003\u0010,\u0005)\u0001O]3wA!9\u0011i$\f\u0005\u0002=UC\u0003CH\u0016\u001f/zIfd\u0017\t\u0011=Ur2\u000ba\u0001\twD\u0001\"c\u001e\u0010T\u0001\u0007!q\u0014\u0005\t\u001f\u0017z\u0019\u00061\u0001\u0010,!AqrLH\u0017\t\u0013\ty0A\u0006qQ\u0006\u001cXm\u0015;sS:<\u0007\u0002CDk\u001f[!\teb6\t\u0011=\u0015tR\u0006C\u0001\u001fO\na\u0001^8MSN$XCAH5!\u0019\u0019\tba\u0006\u0010,!AqRNH\u0017\t\u0003yi%\u0001\u0004pY\u0012,7\u000f\u001e\u0005\u000b\u0019'|i#!A\u0005\u0002=ED\u0003CH\u0016\u001fgz)hd\u001e\t\u0015=Urr\u000eI\u0001\u0002\u0004!Y\u0010\u0003\u0006\nx==\u0004\u0013!a\u0001\u0005?C!bd\u0013\u0010pA\u0005\t\u0019AH\u0016\u0011)ain$\f\u0012\u0002\u0013\u0005q2P\u000b\u0003\u001f{RC\u0001b?\rd\"QAr_H\u0017#\u0003%\ta$!\u0016\u0005=\r%\u0006\u0002BP\u0019GD!bd\"\u0010.E\u0005I\u0011AHE\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"ad#+\t=-B2\u001d\u0005\u000b\u0019\u007f|i#!A\u0005B\u0015}\u0002\"CG\u0002\u001f[\t\t\u0011\"\u0001\u001c\u0011)i9a$\f\u0002\u0002\u0013\u0005q2\u0013\u000b\u0005\u00053y)\n\u0003\u0005$\u001f#\u000b\t\u00111\u0001\u001d\u0011)iya$\f\u0002\u0002\u0013\u0005S\u0012\u0003\u0005\u000b\u001b;yi#!A\u0005\u0002=mEc\u0001$\u0010\u001e\"I1e$'\u0002\u0002\u0003\u0007!\u0011\u0004\u0005\n\u001bKyi#!A\u0005B!B!\"$\f\u0010.\u0005\u0005I\u0011IHR)\r1uR\u0015\u0005\nG=\u0005\u0016\u0011!a\u0001\u00053A1b$+7\u0005\u0003\u0005\t\u0015)\u0003\u0010,\u000513oY1mC\u0012\u0012XM\u001a7fGR$\u0013N\u001c;fe:\fG\u000eJ*z[\n|Gn\u001d\u0013%S:4wn\u001d\u0011\t\u000f=5f\u0007\"\u0001\u0003\u001e\u0006aqN]5hS:\fG.\u00138g_\"9q\u0012\u0017\u001c\u0005\u0006\tu\u0015a\u0001;qK\"91q\u0005\u001c\u0005\u0002\tu\u0005bBB\u0012m\u0011\u0005!Q\u0014\u0005\b\u0007?1D\u0011\u0001BO\u0011\u001dI9H\u000eC\u0001\u0005;Cqa!\u00117\t\u0003yi\fF\u0002\u0017\u001f\u007fC\u0001\"c\u001e\u0010<\u0002\u0007!q\u0014\u0005\b\u001f\u00074D\u0011AHc\u0003\u001d\u0019X\r^%oM>$B\u0001\"3\u0010H\"A\u0011rOHa\u0001\u0004\u0011y\nC\u0004\u0010LZ\"\ta$4\u0002\u00155|G-\u001b4z\u0013:4w\u000e\u0006\u0003\u0005J>=\u0007\u0002\u0003D3\u001f\u0013\u0004\ra$5\u0011\u000f-y\u0019Na(\u0003 &\u0019qR\u001b\u0004\u0003\u0013\u0019+hn\u0019;j_:\f\u0004bBHmm\u0011\u0005q2\\\u0001\ngV\u00147\u000f^%oM>$b\u0001\"3\u0010^>\u0005\b\u0002CHp\u001f/\u0004\ra!)\u0002\u000bMLXn\u001d\u0019\t\u0011=\rxr\u001ba\u0001\u0007C\u000bQa]=ngFBqad:7\t\u0003yI/\u0001\u000btKRLeNZ8Po:,'/\u00113kkN$X\r\u001a\u000b\u0005\t\u0013|Y\u000f\u0003\u0005\nx=\u0015\b\u0019\u0001BP\u0011\u001dyyO\u000eC\u0001\u001fc\fqb]3u\u0013:4w.\u00118e\u000b:$XM\u001d\u000b\u0005\t\u0013|\u0019\u0010\u0003\u0005\nx=5\b\u0019\u0001BP\u0011\u001dy9P\u000eC\u0001\u001fs\f!\"\u001e9eCR,\u0017J\u001c4p)\r!t2 \u0005\t\u0013oz)\u00101\u0001\u0003 \"1qr \u001c\u0005\u0002\u0015\u000b!\u0002[1t%\u0006<\u0018J\u001c4p\u0011\u0019\u0001\u001aA\u000eC\u0001\u000b\u0006y\u0001.Y:D_6\u0004H.\u001a;f\u0013:4w\u000e\u0003\u0004\u0011\bY\")!R\u0001\u0010e\u0006<\u0018J\u001c4p\u0013Ntu\u000eV=qK\"9\u00013\u0002\u001c\u0005\u0002\tu\u0015a\u0002:bo&sgm\u001c\u0005\b!\u001f1D\u0011\u0002I\t\u0003)\tG-\u00199u\u0013:4wn\u001d\u000b\u0005\u001fW\u0001\u001a\u0002\u0003\u0005\u0011\u0016A5\u0001\u0019AH\u0016\u0003\u0015IgNZ8t\u0011\u0019\u0001JB\u000eC\u0001+\u0005Qa-Y5m\u0013\u001a\u001cF/\u001e2\t\u000fAua\u0007\"\u0002\u0011 \u0005Q\u0011N\\5uS\u0006d\u0017N_3\u0016\u0005\u0011%\u0007B\u0002I\u0012m\u0011\u0005Q)A\bnCf\u0014W-\u00138ji&\fG.\u001b>f\u0011\u001d\u0001:C\u000eC\u0003!S\t\u0011\u0002[1t)f\u0004X-\u0011;\u0015\u0007\u0019\u0003Z\u0003C\u0004\u0011.A\u0015\u0002\u0019\u0001\u000f\u0002\u0007ALG\rC\u0004\u00112Y\"\t!d\u0010\u0002\u001f\r|wn\u001b&bm\u0006\u0014\u0016m^%oM>Dq\u0001%\u000e7\t\u0013\u0001:$\u0001\u000bv]N\fg-\u001a+za\u0016\u0004\u0016M]1n!\"\f7/Z\u000b\u0003!s\u0001B\u0001b!\u0011<%\u0019\u0001S\b\u0002\u0003\u000bAC\u0017m]3\t\u000fA\u0005c\u0007\"\u0001\u0004 \u0006\u0001RO\\:bM\u0016$\u0016\u0010]3QCJ\fWn\u001d\u0005\b\u0007;3D\u0011ABP\u0011\u001d\u0001:E\u000eC\u0001\t{\nq\u0001]1sC6\u001c8\u000fC\u0004\u0011LY\"\tA!(\u0002\u0015\rd\u0017m]:C_VtG\rC\u0004\u0002\u0010Z2\tA!(\t\u000f\rUc\u0007\"\u0001\u0011RQ!A\u0011\u001aI*\u0011!\u0019i\u0006e\u0014A\u0002\t}\u0005B\u0002I,m\u0011\u0005Q#\u0001\tnC.,7+\u001a:jC2L'0\u00192mK\"9qQ\u0016\u001c\u0005\u0002AmCc\u0001\f\u0011^!A1q\tI-\u0001\u0004\u0011y\nC\u0004\thY\"\t\u0001%\u0019\u0015\u0007Y\u0001\u001a\u0007C\u0004\u0002dB}\u0003\u0019\u0001\u001b\t\u000f\u001d%g\u0007\"\u0001\u0011hQ\u0019a\u0003%\u001b\t\u000f\u0005\r\bS\ra\u0001i!A\u0001S\u000e\u001c!B\u0013\u0001z'\u0001\u0007`C:tw\u000e^1uS>t7\u000f\u0005\u0004\u0004\u0012\r]Aq\u000b\u0005\b!g2D\u0011AC \u0003E\tgN\\8uCRLwN\\:TiJLgn\u001a\u0005\b!o2D\u0011\u0001I=\u0003-\tgN\\8uCRLwN\\:\u0016\u0005A=\u0004b\u0002C$m\u0011\u0005\u0001S\u0010\u000b\u0005\t\u0013\u0004z\b\u0003\u0005\u0005PAm\u0004\u0019\u0001I8\u0011\u001d\u0001\u001aI\u000eC\u0001!\u000b\u000bqb^5uQ\u0006sgn\u001c;bi&|gn\u001d\u000b\u0005\t\u0013\u0004:\t\u0003\u0005\u0005PA\u0005\u0005\u0019\u0001I8\u0011\u001d\u0001ZI\u000eC\u0001!?\t!c^5uQ>,H/\u00118o_R\fG/[8og\"9\u0001s\u0012\u001c\u0005\u0002AE\u0015!\u00054jYR,'/\u00118o_R\fG/[8ogR!A\u0011\u001aIJ\u0011!\u0001*\n%$A\u0002A]\u0015!\u00019\u0011\r-y\u0019\u000eb\u0016G\u0011\u001d\u0001ZJ\u000eC\u0001!;\u000bQ\"\u00193e\u0003:tw\u000e^1uS>tG\u0003\u0002Ce!?C\u0001\u0002%)\u0011\u001a\u0002\u0007AqK\u0001\u0006C:tw\u000e\u001e\u0005\b!73D\u0011\u0001IS)\u0019!I\re*\u0011*\"9\u00111\u001dIR\u0001\u0004!\u0004\u0002\u0003IV!G\u0003\r\u0001%,\u0002\t\u0005\u0014xm\u001d\t\u0006\u0017\u0011M\u0003s\u0016\t\u0004kAE\u0016\u0002\u0002IZ!k\u0013A\u0001\u0016:fK&\u0019\u0001s\u0017\u0002\u0003\u000bQ\u0013X-Z:\t\u000fAme\u0007\"\u0001\u0011<R1A\u0011\u001aI_!\u007fC\u0001ba\u0012\u0011:\u0002\u0007!q\u0014\u0005\t!W\u0003J\f1\u0001\u0011.\"9\u00013\u0019\u001c\u0005\u0006A\u0015\u0017AB5t\u0019\u0016\u001c8\u000fF\u0002G!\u000fDqa!\u0015\u0011B\u0002\u0007A\u0007C\u0004\u0011LZ\")\u0001%4\u0002\u0015%\u001ch*Z:uK\u0012Le\u000eF\u0002G!\u001fDqa!\u0015\u0011J\u0002\u0007A\u0007C\u0004\u0004LY\"\t\u0001e5\u0015\u0007\u0019\u0003*\u000eC\u0004\u0004RAE\u0007\u0019\u0001\u001b\t\u000fAeg\u0007\"\u0001\u0011\\\u0006\u0001\u0012n\u001d\"piR|WnU;c\u00072\f7o\u001d\u000b\u0004\rBu\u0007bBB)!/\u0004\r\u0001\u000e\u0005\b!C4D\u0011\u0001Ir\u0003)I7oU;c\u00072\f7o\u001d\u000b\u0004\rB\u0015\bbBB)!?\u0004\r\u0001\u000e\u0005\b!S4DQ\u0001Iv\u0003EI7OT;nKJL7mU;c\u00072\f7o\u001d\u000b\u0004\rB5\bbBB)!O\u0004\r\u0001\u000e\u0005\b!c4DQ\u0001Iz\u00039I7oV3bWN+(m\u00117bgN$2A\u0012I{\u0011\u001d\u0019\t\u0006e<A\u0002QBq\u0001c#7\t\u0003\u0019y\nC\u0004\u0011|Z\"\t\u0001%@\u0002\r\u0019LG\u000e^3s)\r!\u0004s \u0005\t#\u0003\u0001J\u00101\u0001\u0012\u0004\u0005!1m\u001c8e!\u0015Yq2\u001b\u001bG\u0011\u001d\t:A\u000eC\u0001#\u0013\t\u0001b];dQRC\u0017\r\u001e\u000b\u0004iE-\u0001\u0002CI\u0001#\u000b\u0001\r!e\u0001\t\u000fE=a\u0007\"\u0002\u0012\u0012\u0005Y1\r\\8oKNKXNY8m+\t\t\u001a\u0002\u0005\u0003\u0005J\u0012e\u0006bBI\bm\u0011\u0015\u0011s\u0003\u000b\u0005#'\tJ\u0002C\u0004\u0012\u001cEU\u0001\u0019\u0001\u001b\u0002\u00119,woT<oKJDq!e\u00047\t\u000b\tz\u0002\u0006\u0004\u0012\u0014E\u0005\u00123\u0005\u0005\b#7\tj\u00021\u00015\u0011!\tI+%\bA\u0002\u0005e\u0004bBI\bm\u0011\u0015\u0011s\u0005\u000b\t#'\tJ#e\u000b\u0012.!9\u00113DI\u0013\u0001\u0004!\u0004\u0002CAU#K\u0001\r!!\u001f\t\u000fE=\u0012S\u0005a\u0001w\u00069a.Z<OC6,\u0007bBAPm\u0019\u0005\u00113\u0007\u000b\u0007#'\t*$e\u000e\t\u000f\u0005\u0015\u0016\u0013\u0007a\u0001i!A\u0011\u0011VI\u0019\u0001\u0004\tI\bC\u0004\u0012<Y\"\t!a\u0007\u0002\u0013\u0015t7\r\\\"mCN\u001c\bbBI m\u0011\u0005\u00111D\u0001\u000bK:\u001cG.T3uQ>$\u0007bBDGm\u0011\u0005\u00111\u0004\u0005\b\u000fS3D\u0011AA\u000e\u0011\u0019\t:E\u000eC\u0001\u000b\u0006Y\u0001.Y:TK24G+\u001f9f\u0011\u001dAiF\u000eC\u0001\u0005;Cqa\")7\t\u0003\u0011i\nC\u0004\u0012PY\")aa(\u0002%\r\f7/\u001a$jK2$\u0017iY2fgN|'o\u001d\u0005\b#'2DQBBP\u0003i\u0019\u0017m]3GS\u0016dG-Q2dKN\u001cxN]:V]N|'\u000f^3e\u0011\u001d\t:F\u000eC\u0003\u0007?\u000bAcY8ogR\u0014\b+\u0019:b[\u0006\u001b7-Z:t_J\u001c\bbBI.m\u0011\u0015\u00111D\u0001\tC\u000e\u001cWm]:fI\"9qq\u000e\u001c\u0005\u0002\u0005m\u0001bBI1m\u0011\u0015\u00111D\u0001\nS6\u0004Hn\u00117bgNDq!%\u001a7\t\u000b\tY\"\u0001\u0006pkR,'o\u00117bgNDq!a17\t\u0003\tY\u0002C\u0004\u0002hZ\"\t!a\u0007\t\u000fE5d\u0007\"\u0001\u0002\u001c\u0005\u0011B.\u0019>z\u0003\u000e\u001cWm]:pe>\u00138+\u001a7g\u0011\u001d\t\nH\u000eC\u0001\u00037\ta\"Y2dKN\u001cX\rZ(s'\u0016dg\rC\u0004\u0002PZ\"\t!a\u0007\t\u000fE]d\u0007\"\u0001\u0002\u001c\u0005Q1/\u001e9fe\u000ec\u0017m]:\t\u000fEmd\u0007\"\u0001\u0004 \u0006i\u0001/\u0019:f]R\u001c\u00160\u001c2pYNDq!e 7\t\u0003\u0019y*\u0001\u0007nSbLgn\u00117bgN,7\u000fC\u0004\u0012\u0004Z\"\taa(\u0002\u0013\u0005t7-Z:u_J\u001c\bbBIDm\u0011\u0015\u0011\u0013R\u0001\u0012K:\u001cGn\\:j]\u001e\u001cVo\u00195UQ\u0006$Hc\u0001\u001b\u0012\f\"A\u0001SSIC\u0001\u0004\t\u001a\u0001\u000b\u0003\u0012\u0006F=\u0005cA\u0006\u0012\u0012&\u0019\u00113\u0013\u0004\u0003\r%tG.\u001b8f\u0011\u001d\t:J\u000eC\u0001\u00037\tQ#\u001a8dY>\u001c\u0018N\\4QC\u000e\\\u0017mZ3DY\u0006\u001c8\u000fC\u0004\u0012\u001cZ\"\t!a\u0007\u0002%\u0015t7\r\\8tS:<'k\\8u\u00072\f7o\u001d\u0005\b#?3D\u0011AA\u000e\u0003A)gn\u00197pg&tw\rU1dW\u0006<W\rC\u0004\u0012$Z\")!a\u0007\u000211|w-[2bY2LXI\\2m_NLgnZ'f[\n,'\u000fC\u0004\u0012(Z\"\t!a\u0007\u0002-\u0015t7\r\\8tS:<Gk\u001c9MKZ,Gn\u00117bgNDq!e+7\t\u0003\tY\"A\u000ff]\u000edwn]5oOR{\u0007\u000fT3wK2\u001cE.Y:t\u001fJ$U/\\7z\u0011\u001d\tzK\u000eC\u0001#c\u000bq\"[:D_\u0012+g-\u001b8fI^KG\u000f\u001b\u000b\u0004\rFM\u0006bBB)#[\u0003\r\u0001\u000e\u0005\b\rW2D\u0011AA\u000e\u0011\u001d9)G\u000eC\u0001\u00037Aq!!\u00077\t\u0003\tY\u0002C\u0004\blY\"\t!a\u0007\t\u000fE}f\u0007\"\u0006\u0003\u001e\u0006ia\r\\1u\u001f^tWM]%oM>Dqab\u00147\t\u0003\tY\u0002C\u0004\u0002 Y\"\t!a\u0007\t\u000fE\u001dg\u0007\"\u0002\u0012J\u0006qQ.\u0019;dQ&twmU=nE>dG#\u0002\u001b\u0012LF=\u0007bBIg#\u000b\u0004\r\u0001N\u0001\b_\u001a\u001cG.\u0019>{\u0011!!\u0019$%2A\u0002\t}\u0005bBIdm\u0011\u0015\u00113\u001b\u000b\u0006iEU\u0017s\u001b\u0005\t\tg\t\n\u000e1\u0001\u0003 \"Q\u0011\u0013\\Ii!\u0003\u0005\r!!\u001f\u0002\u000b\u0005$W.\u001b;\t\u000fEug\u0007\"\u0003\u0012`\u00061R.\u0019;dQ&twmU=nE>d\u0017J\u001c;fe:\fG\u000eF\u00035#C\f\u001a\u000f\u0003\u0005\u00054Em\u0007\u0019\u0001BP\u0011\u001d\t*/e7A\u0002Q\n\u0011bY1oI&$\u0017\r^3\t\u000fE%h\u0007\"\u0002\u0012l\u0006\u0001rN^3se&$G-\u001a8Ts6\u0014w\u000e\u001c\u000b\u0004iE5\bbBIx#O\u0004\r\u0001N\u0001\nE\u0006\u001cXm\u00117bgNDq!e=7\t\u0013\t*0A\rnCR\u001c\u0007.\u001b8h\u0013:DWM]5uK\u0012\u001c\u00160\u001c2pY&sGc\u0001\u001b\u0012x\"9\u0011s^Iy\u0001\u0004!\u0004bBI~m\u0011\u0015\u0011S`\u0001\u0011_Z,'O]5eS:<7+_7c_2$2\u0001NI\u0000\u0011\u001d\tj-%?A\u0002QBaAe\u00017\t\u0013)\u0015\u0001G2b]6\u000bGo\u00195J]\",'/\u001b;fINKXNY8mg\"9!s\u0001\u001c\u0005\u0002\u001de\u0013!D8wKJ\u0014\u0018\u000eZ3DQ\u0006Lg\u000eC\u0004\u0013\fY\")aa(\u0002)\u0005dGn\u0014<feJLG\rZ3o'fl'm\u001c7t\u0011%\u0011zA\u000eEC\u0002\u0013\u0005Q)\u0001\njg>3XM\u001d:jI&twmU=nE>d\u0007\"\u0003J\nm!\u0005\t\u0015)\u0003G\u0003MI7o\u0014<feJLG-\u001b8h'fl'm\u001c7!\u0011\u001d\u0011:B\u000eC\u0001\u00037\tAC\\3yi>3XM\u001d:jI\u0012,gnU=nE>d\u0007b\u0002J\u000em\u0011\u00151qT\u0001\u001aKb$XM\u001c3fI>3XM\u001d:jI\u0012,gnU=nE>d7\u000fC\u0004\u0013 Y\")A%\t\u0002\u0017M,\b/\u001a:Ts6\u0014w\u000e\u001c\u000b\u0004iI\r\u0002bBAy%;\u0001\r\u0001\u000e\u0015\t%;ayFe\n\rj\u0005\u0012!\u0013F\u0001\u001c+N,\u0007\u0005Y:va\u0016\u00148+_7c_2Le\u000e\u0019\u0011j]N$X-\u00193\t\u000fI5b\u0007\"\u0002\u00130\u0005i1/\u001e9feNKXNY8m\u0013:$2\u0001\u000eJ\u0019\u0011\u001d\t\tPe\u000bA\u0002QBq\u0001b\u00197\t\u000b\u0011*\u0004F\u00025%oAq!!=\u00134\u0001\u0007A\u0007\u000b\u0005\u001341}#3\bG5C\t\u0011j$\u0001\fVg\u0016\u0004\u0003mZ3ui\u0016\u0014\u0018J\u001c1!S:\u001cH/Z1e\u0011\u001d\u0011\nE\u000eC\u0003%\u0007\n\u0001bZ3ui\u0016\u0014\u0018J\u001c\u000b\u0004iI\u0015\u0003bBAy%\u007f\u0001\r\u0001\u000e\u0005\u0007%\u00132D\u0011\u0001;\u0002\u0015\u001d,G\u000f^3s\u001d\u0006lW\r\u0003\u0004\u0013NY\"\t\u0001^\u0001\u000bg\u0016$H/\u001a:OC6,\u0007B\u0002J)m\u0011\u0005A/A\u0005m_\u000e\fGNT1nK\"9Aq\r\u001c\u0005\u0006IUC#\u0002\u001b\u0013XIe\u0003bBAy%'\u0002\r\u0001\u000e\u0005\n%7\u0012\u001a\u0006%AA\u0002\u0019\u000bq\u0002[1t\u000bb\u0004\u0018M\u001c3fI:\u000bW.\u001a\u0015\t%'byFe\u0018\rj\u0005\u0012!\u0013M\u0001\u0017+N,\u0007\u0005Y:fiR,'/\u00138aA%t7\u000f^3bI\"9!S\r\u001c\u0005\u0006I\u001d\u0014\u0001C:fiR,'/\u00138\u0015\u000bQ\u0012JGe\u001b\t\u000f\u0005E(3\ra\u0001i!I!3\fJ2!\u0003\u0005\rA\u0012\u0005\u0007%_2D\u0011A#\u0002/9,W\rZ:FqB\fg\u000eZ3e'\u0016$H/\u001a:OC6,\u0007b\u0002J:m\u0011\u0005!SO\u0001\u0011g\u0016$H/\u001a:OC6,\u0017J\u001c\"bg\u0016$RA\u0019J<%sBq!!=\u0013r\u0001\u0007A\u0007C\u0004\u0013|IE\u0004\u0019\u0001$\u0002\u0011\u0015D\b/\u00198eK\u0012Dqa\"07\t\u0003\tY\u0002C\u0004\u0013\u0002Z\")!a\u0007\u0002\u0015\r\f7/Z'pIVdW\rC\u0004\u0004\u0014Z\"\t!a\u0007\t\u000f\r]e\u0007\"\u0001\u0004\u001a\"9!\u0013\u0012\u001c\u0005\u0006I-\u0015AD7bW\u0016tu\u000e\u001e)sSZ\fG/\u001a\u000b\u0004-I5\u0005bBAy%\u000f\u0003\r\u0001\u000e\u0005\b%#3D\u0011AA\u000e\u0003)i\u0017m[3Qk\nd\u0017n\u0019\u0005\b%+3D\u0011AA\u000e\u0003)1\u0017N]:u!\u0006\u0014\u0018-\u001c\u0005\b%33DQ\u0001D(\u0003)\u0019x.\u001e:dK\u001aKG.\u001a\u0005\b\r\u001b2D\u0011\u0001D(\u0011\u001d1yF\u000eC\u0001%?#2A\u0006JQ\u0011!1)G%(A\u0002\u0019E\u0003bBDcm\u0011\u0005AQ\u0001\u0005\b%O3D\u0011\u0001C\u0003\u0003E\u0019X-\u00197fI\u0012+7oY3oI\u0006tGo\u001d\u0005\b%W3DQ\u0001JW\u0003\u0019y'/\u00127tKR\u0019AGe,\t\u0013IE&\u0013\u0016CA\u0002IM\u0016aA1miB!1B!\u00065Q\u0011\u0011J+e$\t\u000fIef\u0007\"\u0002\u0013<\u00069\u0011M\u001c3BYN|Gc\u0001\u001b\u0013>\"AaQ\rJ\\\u0001\u0004\u0011z\fE\u0003\f\u001f'$d\u0003\u000b\u0003\u00138F=\u0005b\u0002Jcm\u0011\u0015!sY\u0001\u0005M>dG-\u0006\u0003\u0013JJEG\u0003\u0002Jf%C$BA%4\u0013^B!!s\u001aJi\u0019\u0001!\u0001Be5\u0013D\n\u0007!S\u001b\u0002\u0002)F!!s\u001bB\r!\rY!\u0013\\\u0005\u0004%74!a\u0002(pi\"Lgn\u001a\u0005\t\rK\u0012\u001a\r1\u0001\u0013`B11bd55%\u001bD\u0011Be9\u0013D\u0012\u0005\rA%:\u0002\t9|g.\u001a\t\u0006\u0017\tU!S\u001a\u0015\u0005%\u0007\fz\tC\u0004\u0013lZ\")A%<\u0002\u00075\f\u0007\u000fF\u00025%_D\u0001B\"\u001a\u0013j\u0002\u0007!\u0013\u001f\t\u0006\u0017=MG\u0007\u000e\u0015\u0005%S\fz\tC\u0004\u0013xZ\")A%?\u0002\u0011Q|w\n\u001d;j_:,\"Ae?\u0011\t-Yi\r\u000e\u0005\b%\u007f4DQAG>\u0003)\u0019\u0018.\u001c9mK:\u000bW.\u001a\u0005\b'\u00071DQAA\u0000\u00039\u0019X-\u00197fIN{'\u000f\u001e(b[\u0016Dqae\u00027\t\u000b\ty0A\u0005lKf\u001cFO]5oO\"913\u0002\u001c\u0005\nM5\u0011AC:z[\n|GnS5oIV\u00111s\u0002\t\u0004kMEaaBJ\n\u0001\u0001#1S\u0003\u0002\u000b'fl'm\u001c7LS:$7cBJ\t\u00151=FR\u0017\u0005\f'3\u0019\nB!f\u0001\n\u0003\ty0\u0001\u0005bG\u000e,(/\u0019;f\u0011-\u0019jb%\u0005\u0003\u0012\u0003\u0006I!a\u001a\u0002\u0013\u0005\u001c7-\u001e:bi\u0016\u0004\u0003bCJ\u0011'#\u0011)\u001a!C\u0001\u0003\u007f\f\u0011b]1oSRL'0\u001a3\t\u0017M\u00152\u0013\u0003B\tB\u0003%\u0011qM\u0001\u000bg\u0006t\u0017\u000e^5{K\u0012\u0004\u0003bCJ\u0015'#\u0011)\u001a!C\u0001\u0003\u007f\fA\"\u00192ce\u00164\u0018.\u0019;j_:D1b%\f\u0014\u0012\tE\t\u0015!\u0003\u0002h\u0005i\u0011M\u00192sKZL\u0017\r^5p]\u0002Bq!QJ\t\t\u0003\u0019\n\u0004\u0006\u0005\u0014\u0010MM2SGJ\u001c\u0011!\u0019Jbe\fA\u0002\u0005\u001d\u0004\u0002CJ\u0011'_\u0001\r!a\u001a\t\u0011M%2s\u0006a\u0001\u0003OB!\u0002d5\u0014\u0012\u0005\u0005I\u0011AJ\u001e)!\u0019za%\u0010\u0014@M\u0005\u0003BCJ\r's\u0001\n\u00111\u0001\u0002h!Q1\u0013EJ\u001d!\u0003\u0005\r!a\u001a\t\u0015M%2\u0013\bI\u0001\u0002\u0004\t9\u0007\u0003\u0006\r^NE\u0011\u0013!C\u0001'\u000b*\"ae\u0012+\t\u0005\u001dD2\u001d\u0005\u000b\u0019o\u001c\n\"%A\u0005\u0002M\u0015\u0003BCHD'#\t\n\u0011\"\u0001\u0014F!QAr`J\t\u0003\u0003%\t%b\u0010\t\u00135\r1\u0013CA\u0001\n\u0003Y\u0002BCG\u0004'#\t\t\u0011\"\u0001\u0014TQ!!\u0011DJ+\u0011!\u00193\u0013KA\u0001\u0002\u0004a\u0002BCG\b'#\t\t\u0011\"\u0011\u000e\u0012!QQRDJ\t\u0003\u0003%\tae\u0017\u0015\u0007\u0019\u001bj\u0006C\u0005$'3\n\t\u00111\u0001\u0003\u001a!IQREJ\t\u0003\u0003%\t\u0005\u000b\u0005\u000b\u000f+\u001c\n\"!A\u0005B5%\u0002BCG\u0017'#\t\t\u0011\"\u0011\u0014fQ\u0019aie\u001a\t\u0013\r\u001a\u001a'!AA\u0002\te\u0001bBJ6m\u0011\u0015\u0011q`\u0001\u0013C\u000e\u001cWO]1uK.Kg\u000eZ*ue&tw\rC\u0004\u0014pY\"I!a@\u0002'M\fg.\u001b;ju\u0016$7*\u001b8e'R\u0014\u0018N\\4\t\u0011MMd\u0007\"\u0005\u0007\u0003\u007f\fQ#\u00192ce\u00164\u0018.\u0019;fI.Kg\u000eZ*ue&tw\rC\u0004\u0014xY\")!a@\u0002\u0015-Lg\u000eZ*ue&tw\r\u0003\u0004\u000b$Y\"\t!\u0012\u0005\b\u0007[3D\u0011AA\u0000\u0011\u001d\u0019zH\u000eC\u0001\u0003\u007f\faBZ;mY:\u000bW.Z*ue&tw\rC\u0004\u0014\u0004Z\")!b\u0010\u0002\u0011%$7\u000b\u001e:j]\u001eDqa\"67\t\u0003:9\u000eC\u0004\u0014\nZ\"\t!a@\u0002\u0015=<hn]*ue&tw\rC\u0004\u0014\u000eZ\"\t!a@\u0002\u001d1|7-\u0019;j_:\u001cFO]5oO\"91\u0013\u0013\u001c\u0005\u0002\u0005}\u0018A\u00054vY2dunY1uS>t7\u000b\u001e:j]\u001eDqa%&7\t\u0003\ty0A\btS\u001et\u0017\r^;sKN#(/\u001b8h\u0011\u001d\u0019JJ\u000eC\u0003'7\u000b!\"\u001b8g_N#(/\u001b8h)\u0011\t9g%(\t\u0011\r\u001d3s\u0013a\u0001\u0005?Cqa%)7\t\u0003\ty0A\u0006j]\u001a|7o\u0015;sS:<\u0007bBJSm\u0011\u0005QqH\u0001\u0014I\u0016\u0014Wo\u001a'pG\u0006$\u0018n\u001c8TiJLgn\u001a\u0005\b'S3D\u0011BJV\u0003A!WMZ*ue&twmQ8na>\u001cX\r\u0006\u0003\u0002hM5\u0006\u0002CJM'O\u0003\r!a\u001a\t\u000fMEf\u0007\"\u0001\u0002\u0000\u0006IA-\u001a4TiJLgn\u001a\u0005\b'k3D\u0011AJ\\\u0003=!WMZ*ue&twmU3f]\u0006\u001bH\u0003BA4'sC\u0001\"c\u001e\u00144\u0002\u0007!q\u0014\u0005\b'{3D\u0011BJ`\u0003\u001d\u0019w.\u001c9pg\u0016$B!a\u001a\u0014B\"A13YJ^\u0001\u0004\u0019*-\u0001\u0002tgB)1\u0002b\u0015\u0002h!11\u0013\u001a\u001c\u0005\u0002\u0015\u000ba#[:TS:<G.\u001a;p]\u0016C\u0018n\u001d;f]RL\u0017\r\u001c\u0005\b'\u001b4D\u0011AC \u0003M)\u00070[:uK:$\u0018.\u00197U_N#(/\u001b8h\u0011%\u0019\nNNI\u0001\n\u000bay.\u0001\ttKR$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%e!I1S\u001b\u001c\u0012\u0002\u0013\u00151s[\u0001\u0019]\u0016<8\t\\1tgNKXNY8mI\u0011,g-Y;mi\u0012\u0012TCAJmU\rYF2\u001d\u0005\n';4\u0014\u0013!C\u0003\u0019s\f\u0001D\\3x\u00072\f7o]*z[\n|G\u000e\n3fM\u0006,H\u000e\u001e\u00134\u0011%\u0019\nONI\u0001\n\u000b\u0019:.A\noK^lU\r\u001e5pI\u0012\"WMZ1vYR$#\u0007C\u0005\u0014fZ\n\n\u0011\"\u0002\rz\u0006\u0019b.Z<NKRDw\u000e\u001a\u0013eK\u001a\fW\u000f\u001c;%g!I1\u0013\u001e\u001c\u0012\u0002\u0013\u00151s[\u0001\u0017]\u0016<\u0018\t\\5bgRK\b/\u001a\u0013eK\u001a\fW\u000f\u001c;%e!I1S\u001e\u001c\u0012\u0002\u0013\u0015A\u0012`\u0001\u0017]\u0016<\u0018\t\\5bgRK\b/\u001a\u0013eK\u001a\fW\u000f\u001c;%g!I1\u0013\u001f\u001c\u0012\u0002\u0013\u00151s[\u0001\u0019]\u0016<X\t_5ti\u0016tG/[1mI\u0011,g-Y;mi\u0012\u0012\u0004\"CJ{mE\u0005IQ\u0001G}\u0003aqWm^#ySN$XM\u001c;jC2$C-\u001a4bk2$He\r\u0005\n's4\u0014\u0013!C\u0003'/\f\u0011D\\3x\u001b>$W\u000f\\3Ts6\u0014w\u000e\u001c\u0013eK\u001a\fW\u000f\u001c;%e!I1S \u001c\u0012\u0002\u0013\u0015A\u0012`\u0001\u001a]\u0016<Xj\u001c3vY\u0016\u001c\u00160\u001c2pY\u0012\"WMZ1vYR$3\u0007C\u0005\u0015\u0002Y\n\n\u0011\"\u0002\u0014X\u0006\u0011b.Z<WC2,X\r\n3fM\u0006,H\u000e\u001e\u00133\u0011%!*ANI\u0001\n\u000baI0\u0001\noK^4\u0016\r\\;fI\u0011,g-Y;mi\u0012\u001a\u0004\"\u0003K\u0005mE\u0005IQAJl\u0003uqWm\u001e+za\u0016\u001c6n\u001c7f[NKXNY8mI\u0011,g-Y;mi\u0012\u001a\u0004\"\u0003K\u0007mE\u0005IQ\u0001G}\u0003uqWm\u001e+za\u0016\u001c6n\u001c7f[NKXNY8mI\u0011,g-Y;mi\u0012\"\u0004\"\u0003K\tmE\u0005IQAJl\u0003aqWm^'pIVdWm\u00117bgN$C-\u001a4bk2$HE\r\u0005\n)+1\u0014\u0013!C\u0003\u0019s\f\u0001D\\3x\u001b>$W\u000f\\3DY\u0006\u001c8\u000f\n3fM\u0006,H\u000e\u001e\u00134\u0011%!JBNI\u0001\n\u0003\u0019:.A\foK^$\u0016\u0010]3Ts6\u0014w\u000e\u001c\u0013eK\u001a\fW\u000f\u001c;%e!IAS\u0004\u001c\u0012\u0002\u0013\u0005A\u0012`\u0001\u0018]\u0016<H+\u001f9f'fl'm\u001c7%I\u00164\u0017-\u001e7uIMB\u0011\u0002&\t7#\u0003%)ae6\u0002/9,w\u000fV3s[NKXNY8mI\u0011,g-Y;mi\u0012\u0012\u0004\"\u0003K\u0013mE\u0005IQ\u0001G}\u0003]qWm\u001e+fe6\u001c\u00160\u001c2pY\u0012\"WMZ1vYR$3\u0007C\u0005\u0015*Y\n\n\u0011\"\u0002\u0014X\u0006Ib.Z<NKRDw\u000eZ*z[\n|G\u000e\n3fM\u0006,H\u000e\u001e\u00133\u0011%!jCNI\u0001\n\u000baI0A\roK^lU\r\u001e5pINKXNY8mI\u0011,g-Y;mi\u0012\u001a\u0004\"\u0003K\u0019mE\u0005IQAJl\u0003mqWm\u001e(p]\u000ec\u0017m]:Ts6\u0014w\u000e\u001c\u0013eK\u001a\fW\u000f\u001c;%e!IAS\u0007\u001c\u0012\u0002\u0013\u0015A\u0012`\u0001\u001c]\u0016<hj\u001c8DY\u0006\u001c8oU=nE>dG\u0005Z3gCVdG\u000fJ\u001a\t\u0013Qeb'%A\u0005\u00061}\u0017AE:fiR,'/\u00138%I\u00164\u0017-\u001e7uIIB\u0011\u0002&\u00107#\u0003%)ae6\u0002+9,wOV1sS\u0006\u0014G.\u001a\u0013eK\u001a\fW\u000f\u001c;%e!IA\u0013\t\u001c\u0012\u0002\u0013\u0015A\u0012`\u0001\u0016]\u0016<h+\u0019:jC\ndW\r\n3fM\u0006,H\u000e\u001e\u00134\u0011%!*ENI\u0001\n\u000b\u0019:.A\u000eoK^4\u0016\r\\;f!\u0006\u0014\u0018-\\3uKJ$C-\u001a4bk2$HE\r\u0005\n)\u00132\u0014\u0013!C\u0003\u0019s\f1D\\3x-\u0006dW/\u001a)be\u0006lW\r^3sI\u0011,g-Y;mi\u0012\u001a\u0004\"\u0003K'mE\u0005IQAJl\u0003IqWm\u001e'bE\u0016dG\u0005Z3gCVdG\u000f\n\u001a\t\u0013QEc'%A\u0005\u00061e\u0018\u0001\u00078fo\u000e{gn\u001d;sk\u000e$xN\u001d\u0013eK\u001a\fW\u000f\u001c;%e!IAS\u000b\u001c\u0012\u0002\u0013\u0005A\u0012`\u0001\u001a]\u0016<H*\u001b8lK\u0012lu\u000eZ;mK\u0012\"WMZ1vYR$#\u0007C\u0005\u0015ZY\n\n\u0011\"\u0002\u0014X\u0006\u0019b.Z<N_\u0012,H.\u001a\u0013eK\u001a\fW\u000f\u001c;%e!IAS\f\u001c\u0012\u0002\u0013\u0015A\u0012`\u0001\u0014]\u0016<Xj\u001c3vY\u0016$C-\u001a4bk2$He\r\u0005\n)C2\u0014\u0013!C\u0003'/\fAC\\3x!\u0006\u001c7.Y4fI\u0011,g-Y;mi\u0012\u0012\u0004\"\u0003K3mE\u0005IQ\u0001G}\u0003QqWm\u001e)bG.\fw-\u001a\u0013eK\u001a\fW\u000f\u001c;%g!IA\u0013\u000e\u001c\u0012\u0002\u0013\u0015A3N\u0001\u0015]\u0016<H\u000b[5t'flG\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005Q5$f\u00012\rd\"IA\u0013\u000f\u001c\u0012\u0002\u0013\u00151s[\u0001\u0015]\u0016<H\u000b[5t'flG\u0005Z3gCVdG\u000f\n\u001a\t\u0013QUd'%A\u0005\u0006M]\u0017A\b8fo6{G-\u001e7f\u00072\f7o]*z[\n|G\u000e\n3fM\u0006,H\u000e\u001e\u00133\u0011%!JHNI\u0001\n\u000baI0\u0001\u0010oK^lu\u000eZ;mK\u000ec\u0017m]:Ts6\u0014w\u000e\u001c\u0013eK\u001a\fW\u000f\u001c;%g!IAS\u0010\u001c\u0012\u0002\u0013\u00151s[\u0001\u001a]\u0016<\u0018IY:ue\u0006\u001cG\u000fV=qK\u0012\"WMZ1vYR$#\u0007C\u0005\u0015\u0002Z\n\n\u0011\"\u0002\rz\u0006Ib.Z<BEN$(/Y2u)f\u0004X\r\n3fM\u0006,H\u000e\u001e\u00134\u0011%!*INI\u0001\n\u000b\u0019:.\u0001\u000eoK^$\u0016\u0010]3QCJ\fW.\u001a;fe\u0012\"WMZ1vYR$#\u0007C\u0005\u0015\nZ\n\n\u0011\"\u0002\rz\u0006Qb.Z<UsB,\u0007+\u0019:b[\u0016$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%g!IAS\u0012\u001c\u0012\u0002\u0013\u0015A3N\u0001!]\u0016<8+\u001f8uQ\u0016$\u0018n\u0019,bYV,\u0007+\u0019:b[\u0012\"WMZ1vYR$#\u0007C\u0005\u0015\u0012Z\n\n\u0011\"\u0002\u0014X\u0006\u0011b.Z<DY\u0006\u001c8\u000f\n3fM\u0006,H\u000e\u001e\u00133\u0011%!*JNI\u0001\n\u000baI0\u0001\noK^\u001cE.Y:tI\u0011,g-Y;mi\u0012\u001a\u0004\"\u0003KMmE\u0005I\u0011AJl\u0003iqWm^\"mCN\u001cx+\u001b;i\u0013:4w\u000e\n3fM\u0006,H\u000e\u001e\u00135\u0011%!jJNI\u0001\n\u0003aI0\u0001\u000eoK^\u001cE.Y:t/&$\b.\u00138g_\u0012\"WMZ1vYR$S\u0007C\u0005\u0015\"Z\n\n\u0011\"\u0002\u0014X\u0006\u0019c.Z<B]>t\u00170\\8vg\u001a+hn\u0019;j_:\u001cE.Y:tI\u0011,g-Y;mi\u0012\n\u0004\"\u0003KSmE\u0005IQ\u0001G}\u0003\rrWm^!o_:LXn\\;t\rVt7\r^5p]\u000ec\u0017m]:%I\u00164\u0017-\u001e7uIIB\u0011\u0002&+7#\u0003%)\u0001$?\u0002G9,w/\u00118p]flw.^:Gk:\u001cG/[8o-\u0006dW/\u001a\u0013eK\u001a\fW\u000f\u001c;%e!IAS\u0016\u001c\u0012\u0002\u0013\u00051s[\u0001\u0017]\u0016<\u0018*\u001c9m\u00072\f7o\u001d\u0013eK\u001a\fW\u000f\u001c;%e!IA\u0013\u0017\u001c\u0012\u0002\u0013\u0005A\u0012`\u0001\u0017]\u0016<\u0018*\u001c9m\u00072\f7o\u001d\u0013eK\u001a\fW\u000f\u001c;%g!IAS\u0017\u001c\u0012\u0002\u0013\u0005Ar\\\u0001\u0018]\u0016<8\u000b^;c'fl'm\u001c7%I\u00164\u0017-\u001e7uIMB\u0011\u0002&/7#\u0003%)\u0001$?\u000215\fGo\u00195j]\u001e\u001c\u00160\u001c2pY\u0012\"WMZ1vYR$#\u0007\u0003\u0007\u0015>Z\u0012\t\u00111A\u0005\u0002\u0001yi%A\u0013tG\u0006d\u0017\r\n:fM2,7\r\u001e\u0013j]R,'O\\1mIMKXNY8mg\u0012\"\u0013N\u001c4pg\"IA\u0013\u0019\u0001A\u0002\u0013%A3Y\u0001\u0014?J,7-\u001e:tS>tG+\u00192mK~#S-\u001d\u000b\u0004-Q\u0015\u0007\u0002C\u0012\u0015@\u0006\u0005\t\u0019\u0001\u0017\t\u000fQ%\u0007\u0001)Q\u0005Y\u0005\u0001rL]3dkJ\u001c\u0018n\u001c8UC\ndW\r\t\u0005\u0007)\u001b\u0004A\u0011A\u0016\u0002\u001dI,7-\u001e:tS>tG+\u00192mK\"9A\u0013\u001b\u0001\u0005\u0002QM\u0017A\u0005:fGV\u00148/[8o)\u0006\u0014G.Z0%KF$2A\u0006Kk\u0011\u001d\u0011)\u0004f4A\u00021B\u0001\u0002&7\u0001\u0001\u0004%IaG\u0001\u000fKbL7\u000f^3oi&\fG.\u00133t\u0011%!j\u000e\u0001a\u0001\n\u0013!z.\u0001\nfq&\u001cH/\u001a8uS\u0006d\u0017\nZ:`I\u0015\fHc\u0001\f\u0015b\"A1\u0005f7\u0002\u0002\u0003\u0007A\u0004C\u0004\u0015f\u0002\u0001\u000b\u0015\u0002\u000f\u0002\u001f\u0015D\u0018n\u001d;f]RL\u0017\r\\%eg\u0002Ba\u0001&;\u0001\t#A\u0013!\u00058fqR,\u00050[:uK:$\u0018.\u00197JI\"9AS\u001e\u0001\u0005\u0012Q=\u0018\u0001\u00064sKNDW\t_5ti\u0016tG/[1m\u001d\u0006lW\r\u0006\u0003\u0003hQE\b\u0002CEM)W\u0004\r!a\u001a\t\u000fQU\b\u0001\"\u0001\u0015x\u0006!2m\u001c8oK\u000e$Xj\u001c3vY\u0016$vn\u00117bgN$bA\"\b\u0015zRu\b\u0002\u0003K~)g\u0004\rA\"\b\u0002\u00035D\u0001\"a\b\u0015t\u0002\u0007a1\u0019\u0005\b+\u0003\u0001A\u0011AK\u0002\u0003EqWm\u001e$sK\u0016$VM]7Ts6\u0014w\u000e\u001c\u000b\n\u0019V\u0015QsAK\u0005+\u0017AaA\u001eK\u0000\u0001\u0004\u0011\u0007\"\u0003B\u001b)\u007f$\t\u0019\u0001B\n\u0011)IY\bf@\u0011\u0002\u0003\u0007\u0011\u0011\u0010\u0005\t\u0003{$z\u00101\u0001\u0002h!9Qs\u0002\u0001\u0005\u0002UE\u0011!\u00058fo\u001a\u0013X-\u001a+za\u0016\u001c\u00160\u001c2pYRA!1IK\n++):\u0002C\u0004w+\u001b\u0001\rAa\u001a\t\u0015%mTS\u0002I\u0001\u0002\u0004\tI\b\u0003\u0005\u0002~V5\u0001\u0019AA4\u0011%)Z\u0002\u0001b\u0001\n\u0013)j\"\u0001\tpe&<\u0017N\\1m\u001f^tWM]'baV\u0011Qs\u0004\t\u0007+C):\u0003\u000e\u001b\u000e\u0005U\r\"bAK\u0013a\u00059Q.\u001e;bE2,\u0017\u0002BK\u0015+G\u0011q\u0001S1tQ6\u000b\u0007\u000f\u0003\u0005\u0016.\u0001\u0001\u000b\u0011BK\u0010\u0003Ey'/[4j]\u0006dwj\u001e8fe6\u000b\u0007\u000f\t\u0005\b+c\u0001A\u0011CK\u001a\u0003E\u0019\u0018M^3Pe&<\u0017N\\1m\u001f^tWM\u001d\u000b\u0004-UU\u0002bBAr+_\u0001\r\u0001\u000e\u0005\b+s\u0001A\u0011AK\u001e\u0003M!WMZ5oK>\u0013\u0018nZ5oC2|uO\\3s)\u00151RSHK \u0011\u001d\t\u0019/f\u000eA\u0002QBq!!*\u00168\u0001\u0007A\u0007C\u0004\u0016D\u0001!\t!&\u0012\u0002\u0011MLXNY8m\u001f\u001a,B!f\u0012\u0016ZQ!!1KK%\u0011))Z%&\u0011\u0002\u0002\u0003\u000fQSJ\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004#B\u001b\u0016PU]\u0013\u0002BK)+'\u00121bV3bWRK\b/\u001a+bO&\u0019QS\u000b\t\u0003\u0011QK\b/\u001a+bON\u0004BAe4\u0016Z\u0011A!3[K!\u0005\u0004\u0011*n\u0002\u0006\u0016^\u0001\t\t\u0011#\u0001\u0005+?\n!bU=nE>d7*\u001b8e!\r)T\u0013\r\u0004\u000b''\u0001\u0011\u0011!E\u0001\tU\r4CBK1+Kb)\f\u0005\u0007\u0016hU5\u0014qMA4\u0003O\u001az!\u0004\u0002\u0016j)\u0019Q3\u000e\u0004\u0002\u000fI,h\u000e^5nK&!QsNK5\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\u0005\b\u0003V\u0005D\u0011AK:)\t)z\u0006\u0003\u0006\bVV\u0005\u0014\u0011!C#\u001bSA!\"&\u001f\u0016b\u0005\u0005I\u0011QK>\u0003\u0015\t\u0007\u000f\u001d7z)!\u0019z!& \u0016\u0000U\u0005\u0005\u0002CJ\r+o\u0002\r!a\u001a\t\u0011M\u0005Rs\u000fa\u0001\u0003OB\u0001b%\u000b\u0016x\u0001\u0007\u0011q\r\u0005\u000b+\u000b+\n'!A\u0005\u0002V\u001d\u0015aB;oCB\u0004H.\u001f\u000b\u0005+\u0013+\n\nE\u0003\f\u0017\u001b,Z\tE\u0005\f+\u001b\u000b9'a\u001a\u0002h%\u0019Qs\u0012\u0004\u0003\rQ+\b\u000f\\34\u0011))\u001a*f!\u0002\u0002\u0003\u00071sB\u0001\u0004q\u0012\u0002\u0004b\u0002F\u001e\u0001\u0011EQs\u0013\u000b\niUeU3TKO+?Cq!!*\u0016\u0016\u0002\u0007A\u0007\u0003\u0004w++\u0003\ra\u001f\u0005\t\u0015\u0007**\n1\u0001\u0002h!I!rIKK!\u0003\u0005\rA\u0012\u0005\n+G\u0003!\u0019!C\u0002+K\u000b\u0011bU=nE>dG+Y4\u0016\u0005U\u001d\u0006#BKU+W#T\"\u0001\u0003\n\u0007U5FA\u0001\u0005DY\u0006\u001c8\u000fV1h\u0011!)\n\f\u0001Q\u0001\nU\u001d\u0016AC*z[\n|G\u000eV1hA!IQS\u0017\u0001C\u0002\u0013\rQsW\u0001\u000e)\u0016\u0014XnU=nE>dG+Y4\u0016\u0005Ue\u0006#BKU+W\u0003\u0006\u0002CK_\u0001\u0001\u0006I!&/\u0002\u001dQ+'/\\*z[\n|G\u000eV1hA!IQ\u0013\u0019\u0001C\u0002\u0013\rQ3Y\u0001\u0010\u001b>$W\u000f\\3Ts6\u0014w\u000e\u001c+bOV\u0011QS\u0019\t\u0007+S+ZK\"\b\t\u0011U%\u0007\u0001)A\u0005+\u000b\f\u0001#T8ek2,7+_7c_2$\u0016m\u001a\u0011\t\u0013U5\u0007A1A\u0005\u0004U=\u0017aD'fi\"|GmU=nE>dG+Y4\u0016\u0005UE\u0007CBKU+W+I\t\u0003\u0005\u0016V\u0002\u0001\u000b\u0011BKi\u0003AiU\r\u001e5pINKXNY8m)\u0006<\u0007\u0005C\u0005\u0016Z\u0002\u0011\r\u0011b\u0001\u0016\\\u0006iA+\u001f9f'fl'm\u001c7UC\u001e,\"!&8\u0011\rU%V3\u0016B*\u0011!)\n\u000f\u0001Q\u0001\nUu\u0017A\u0004+za\u0016\u001c\u00160\u001c2pYR\u000bw\r\t\u0005\n+K\u0004!\u0019!C\u0002+O\fab\u00117bgN\u001c\u00160\u001c2pYR\u000bw-\u0006\u0002\u0016jB1Q\u0013VKV\r\u0007D\u0001\"&<\u0001A\u0003%Q\u0013^\u0001\u0010\u00072\f7o]*z[\n|G\u000eV1hA\u0019IQ\u0013\u001f\u0001\u0011\u0002\u0007\u0005Q3\u001f\u0002\u0010\u00136\u0004Hn\u00117bgN\u001c\u00160\u001c2pYN!Qs\u001eDb\u0011\u0019!Rs\u001eC\u0001+!AqqNKx\t\u0003\nY\u0002\u0003\u0005\t^U=H\u0011\tBO\r%)j\u0010\u0001I\u0001\u0004\u0003)zP\u0001\u0006TiV\u00147+_7c_2\u001c2!f?5\u0011\u0019!R3 C\u0001+!A!2IK~\r\u0003\ty\u0010\u0003\u0005\u0011\u001aUmHQ\tL\u0004)\t\u0011:\u000e\u0003\u0005\u0017\fUmH\u0011\u0002L\u0007\u0003\u00111\u0017-\u001b7\u0016\tY=a3\u0003\u000b\u0005-#1*\u0002\u0005\u0003\u0013PZMA\u0001\u0003Jj-\u0013\u0011\rA%6\t\u0011IEf\u0013\u0002a\u0001-#A\u0001b$,\u0016|\u0012\u0005c\u0013D\u000b\u0003-7q1!\u000eL\u000f\u0013\u00111z\"!'\u0002\r9{G+\u001f9f\u0011!1i%f?\u0005B\u0019=\u0003\u0002CE<+w$\tE&\u0007\t\u0011A-Q3 C!-3A\u0001\"!\u0007\u0016|\u0012\u0005c\u0013F\u000b\u0003-W\u00012!\u000eL\u0017\r\u00191z\u0003\u0001\u0001\u00172\tAaj\\*z[\n|GnE\u0002\u0017.QB\u0001\"\u0011L\u0017\t#\u0001aS\u0007\u000b\u0003-W)a\u0001\"2\u0017.\t\u0011WAB8\u0017.\u00011Z\u0003C\u0004\u0000-[!\tA&\u0010\u0015\u0007\t4z\u0004C\u0004\u0002\u0006Ym\u0002\u0019A>\t\u000fM4j\u0003\"\u0001\bx!9aO&\f\u0005\u0002\u001d]\u0004b\u0002=\u0017.\u0011\u0005cs\t\u000b\u0005%/4J\u0005C\u0004\u0002\u0006Y\u0015\u0003\u0019A>\t\u0011\r\u0005cS\u0006C!-\u001b\"2A\u0006L(\u0011!I9Hf\u0013A\u0002\t}\u0005\u0002CC\u001d-[!\t%a/\t\u000f1]eS\u0006C!\u000b\"912\u000eL\u0017\t\u0003*\u0005\u0002\u0003D6-[!\tE&\u000b\t\u0011\u001d\u0015dS\u0006C!-SA\u0001\"!\u0007\u0017.\u0011\u0005c\u0013\u0006\u0005\t!C4j\u0003\"\u0011\u0017`Q\u0019aI&\u0019\t\u000f\rEcS\fa\u0001i!A\u00013 L\u0017\t\u00032*\u0007\u0006\u0003\u0017,Y\u001d\u0004\u0002CI\u0001-G\u0002\r!e\u0001\t\u0011MEfS\u0006C!\u0003\u007fD\u0001b%$\u0017.\u0011\u0005\u0013q \u0005\t\u000f/2j\u0003\"\u0011\u000f8!A\u00113\bL\u0017\t\u0003\nY\u0002\u0003\u0005\u0012(Z5B\u0011IA\u000e\u0011!\tZK&\f\u0005B\u0005m\u0001\u0002CIL-[!\t%a\u0007\t\u0011E}bS\u0006C!\u00037A\u0001B\"\u0014\u0017.\u0011\u0005c3P\u000b\u0003-{rAAb\u0015\u0017\u0000%!a\u0013\u0011D+\u00039qu.\u00112tiJ\f7\r\u001e$jY\u0016D\u0001\"!*\u0017.\u0011\u0005\u00131\u0004\u0005\t\u001bG2j\u0003\"\u0011\u0004 \"AQ\u0012\u000eL\u0017\t\u0003jY\u0007\u0003\u0005\t\fZ5B\u0011IBP\u0011!\u0019)F&\f\u0005BY5E\u0003\u0002LH-#k!A&\f\t\u0011\ruc3\u0012a\u0001\u0005?C\u0001\"c\u001e\u0017.\u0011\u0005#Q\u0014\u0005\t\u0003\u001f3j\u0003\"\u0011\u0003\u001e\"A\u00013\u0002L\u0017\t\u0003\u0012i\n\u0003\u0005\u000fxZ5B\u0011\tLN)\r!dS\u0014\u0005\b\u0003c4J\n1\u00015\u0011!\tyJ&\f\u0005\u0002Y\u0005FC\u0002Jl-G3*\u000bC\u0004\u0002&Z}\u0005\u0019\u0001\u001b\t\u0011\u0005%fs\u0014a\u0001\u0003s2aA&+\u0001\u0001Y-&aD*uk\n\u001cE.Y:t'fl'm\u001c7\u0014\rY\u001df1\u0019LW!\r)T3 \u0005\u000b\u0015\u000b1:K!A!\u0002\u0013!\u0004b\u0003B\u0007-O\u0013\t\u0011)A\u0005\u0005OB1Bc\u0011\u0017(\n\u0015\r\u0011\"\u0001\u0002\u0000\"Yas\u0017LT\u0005\u0003\u0005\u000b\u0011BA4\u0003=i\u0017n]:j]\u001elUm]:bO\u0016\u0004\u0003bB!\u0017(\u0012\u0005a3\u0018\u000b\t-{3zL&1\u0017DB\u0019QGf*\t\u000f)\u0015a\u0013\u0018a\u0001i!A!Q\u0002L]\u0001\u0004\u00119\u0007\u0003\u0005\u000bDYe\u0006\u0019AA4\r\u00191:\r\u0001\u0001\u0017J\n12\u000b^;c!\u0006\u001c7.Y4f\u00072\f7o]*z[\n|Gn\u0005\u0004\u0017F:maS\u0016\u0005\u000b\u0015\u000b1*M!A!\u0002\u0013!\u0004b\u0003B\u0007-\u000b\u0014\t\u0011)A\u0005\u0005OB1Bc\u0011\u0017F\n\u0015\r\u0011\"\u0001\u0002\u0000\"Yas\u0017Lc\u0005\u0003\u0005\u000b\u0011BA4\u0011\u001d\teS\u0019C\u0001-+$\u0002Bf6\u0017ZZmgS\u001c\t\u0004kY\u0015\u0007b\u0002F\u0003-'\u0004\r\u0001\u000e\u0005\t\u0005\u001b1\u001a\u000e1\u0001\u0003h!A!2\tLj\u0001\u0004\t9G\u0002\u0004\u0017b\u0002\u0001a3\u001d\u0002\u000f'R,(\rV3s[NKXNY8m'\u00151z\u000e\u0015LW\u0011)Q)Af8\u0003\u0002\u0003\u0006I\u0001\u000e\u0005\u000b\u0005\u001b1zN!A!\u0002\u0013\u0011\u0007b\u0003F\"-?\u0014)\u0019!C\u0001\u0003\u007fD1Bf.\u0017`\n\u0005\t\u0015!\u0003\u0002h!9\u0011If8\u0005\u0002Y=H\u0003\u0003Ly-g4*Pf>\u0011\u0007U2z\u000eC\u0004\u000b\u0006Y5\b\u0019\u0001\u001b\t\u000f\t5aS\u001ea\u0001E\"A!2\tLw\u0001\u0004\t9\u0007C\u0005\u0017|\u0002\u0011\r\u0011b\u0001\u0017~\u0006\tbI]3f)\u0016\u0014XnU=nE>dG+Y4\u0016\u0005Y}\b#BKU+Wc\u0005\u0002CL\u0002\u0001\u0001\u0006IAf@\u0002%\u0019\u0013X-\u001a+fe6\u001c\u00160\u001c2pYR\u000bw\r\t\u0005\n/\u000f\u0001!\u0019!C\u0002/\u0013\t\u0011C\u0012:fKRK\b/Z*z[\n|G\u000eV1h+\t9Z\u0001\u0005\u0004\u0016*V-&1\t\u0005\t/\u001f\u0001\u0001\u0015!\u0003\u0018\f\u0005\u0011bI]3f)f\u0004XmU=nE>dG+Y4!\u0011\u001d9\u001a\u0002\u0001C\t-S\tA\"\\1lK:{7+_7c_2D!bf\u0006\u0001\u0011\u000b\u0007I\u0011\u0001L\u0015\u0003!qunU=nE>d\u0007BCL\u000e\u0001!\u0005\t\u0015)\u0003\u0017,\u0005Iaj\\*z[\n|G\u000e\t\u0005\b/?\u0001A\u0011AL\u0011\u00035!WM]5wKNKXNY8mgR11\u0011UL\u0012/OA\u0001b&\n\u0018\u001e\u0001\u00071\u0011U\u0001\u0005gfl7\u000f\u0003\u0005\u0018*]u\u0001\u0019\u0001Jy\u0003\u0015\u0019\u00180\u001c$o\u0011\u001d9j\u0003\u0001C\u0001/_\ta\u0002Z3sSZ,7+_7c_2\u001c('\u0006\u0003\u00182]uB\u0003CBQ/g9*d&\u0011\t\u0011]\u0015r3\u0006a\u0001\u0007CC\u0001bf\u000e\u0018,\u0001\u0007q\u0013H\u0001\u0003CN\u0004ba!\u0005\u0004\u0018]m\u0002\u0003\u0002Jh/{!\u0001bf\u0010\u0018,\t\u0007!S\u001b\u0002\u0002\u0003\"Aq\u0013FL\u0016\u0001\u00049\u001a\u0005E\u0004\f/\u000b\"t3\b\u001b\n\u0007]\u001dcAA\u0005Gk:\u001cG/[8oe!9q3\n\u0001\u0005\u0002]5\u0013A\u00033fe&4X\rV=qKR1qsJL*/+\"BAa(\u0018R!Aq\u0012WL%\u0001\u0004\u0011y\n\u0003\u0005\u0018&]%\u0003\u0019ABQ\u0011!9Jc&\u0013A\u0002IE\bbBL-\u0001\u0011\u0005q3L\u0001\fI\u0016\u0014\u0018N^3UsB,''\u0006\u0003\u0018^]-D\u0003CL0/G:*g&\u001c\u0015\t\t}u\u0013\r\u0005\t\u001fc;:\u00061\u0001\u0003 \"AqSEL,\u0001\u0004\u0019\t\u000b\u0003\u0005\u00188]]\u0003\u0019AL4!\u0019\u0019\tba\u0006\u0018jA!!sZL6\t!9zdf\u0016C\u0002IU\u0007\u0002CL\u0015//\u0002\raf\u001c\u0011\u000f-9*\u0005NL5i!9q3\u000f\u0001\u0005\u0002]U\u0014a\u00063fe&4X\rV=qK^KG\u000f[,jY\u0012\u001c\u0017M\u001d3t)\u00119:hf\u001f\u0015\t\t}u\u0013\u0010\u0005\t\u001fc;\n\b1\u0001\u0003 \"AqSEL9\u0001\u0004\u0019\t\u000bC\u0004\u0018\u0000\u0001!\ta&!\u0002\u0019\rdwN\\3Ts6\u0014w\u000e\\:\u0015\t\r\u0005v3\u0011\u0005\t/K9j\b1\u0001\u0004\"\"9qs\u0011\u0001\u0005\u0002]%\u0015aE2m_:,7+_7c_2\u001c\u0018\t^(x]\u0016\u0014HCBBQ/\u0017;j\t\u0003\u0005\u0018&]\u0015\u0005\u0019ABQ\u0011\u001d\t)k&\"A\u0002QBqa&%\u0001\t\u00039\u001a*A\u000bdY>tWmU=nE>d7/\u00118e\u001b>$\u0017NZ=\u0015\r\r\u0005vSSLL\u0011!9*cf$A\u0002\r\u0005\u0006\u0002CLM/\u001f\u0003\ra$5\u0002\r%tgm\u001c$o\u0011\u001d9j\n\u0001C\u0001/?\u000bAd\u00197p]\u0016\u001c\u00160\u001c2pYN\fEoT<oKJ\fe\u000eZ'pI&4\u0017\u0010\u0006\u0005\u0004\"^\u0005v3ULS\u0011!9*cf'A\u0002\r\u0005\u0006bBAS/7\u0003\r\u0001\u000e\u0005\t/3;Z\n1\u0001\u0010R\"9q\u0013\u0016\u0001\u0005\u0002]-\u0016aF2sK\u0006$XM\u0012:p[\u000ecwN\\3e'fl'm\u001c7t+\u00119jkf-\u0015\r]=v3XL_)\u00119\nl&.\u0011\tI=w3\u0017\u0003\t%'<:K1\u0001\u0013V\"AqsWLT\u0001\u00049J,A\u0004de\u0016\fGo\u001c:\u0011\u0013-9*e!)\u0003 ^E\u0006\u0002CL\u0013/O\u0003\ra!)\t\u0011=Evs\u0015a\u0001\u0005?Cqa&1\u0001\t\u00039\u001a-\u0001\u0010de\u0016\fG/\u001a$s_6\u001cEn\u001c8fINKXNY8mg\u0006#xj\u001e8feV!qSYLf)!9:m&5\u0018T^UG\u0003BLe/\u001b\u0004BAe4\u0018L\u0012A!3[L`\u0005\u0004\u0011*\u000e\u0003\u0005\u00188^}\u0006\u0019ALh!%YqSIBQ\u0005?;J\r\u0003\u0005\u0018&]}\u0006\u0019ABQ\u0011\u001d\t)kf0A\u0002QB\u0001b$-\u0018@\u0002\u0007!q\u0014\u0005\b/3\u0004A\u0011ALn\u0003)i\u0017\r\u001d)be\u0006l7o]\u000b\u0005/;<:\u000f\u0006\u0003\u0018`^5H\u0003BLq/S\u0004ba!\u0005\u0004\u0018]\r\bCBB\t\u0007/9*\u000f\u0005\u0003\u0013P^\u001dH\u0001\u0003Jj//\u0014\rA%6\t\u0011\u0019\u0015ts\u001ba\u0001/W\u0004baCHji]\u0015\bbBAr//\u0004\r\u0001\u000e\u0005\b/c\u0004A\u0011ALz\u0003=)\u00070[:uS:<7+_7c_2\u001cH\u0003BBQ/kD\u0001b&\n\u0018p\u0002\u00071\u0011\u0015\u0005\b/s\u0004AQAL~\u0003E\u0019Gn\\:fgR,en\u00197NKRDw\u000e\u001a\u000b\u0004i]u\bbBL\u0000/o\u0004\r\u0001N\u0001\u0005MJ|WN\u0002\u0004\u0019\u0004\u0001\u0001\u0005T\u0001\u0002\u0010\u0007f\u001cG.[2SK\u001a,'/\u001a8dKNA\u0001\u0014\u0001M\u0004\u0019_c)\fE\u000261\u0013IA\u0001g\u0003\u0002\u001a\nIA+\u001f9f\u000bJ\u0014xN\u001d\u0005\f\u0003GD\nA!f\u0001\n\u0003\tY\u0002\u0003\u0006\u0019\u0012a\u0005!\u0011#Q\u0001\nQ\nAa]=nA!Y\u0011r\u000fM\u0001\u0005+\u0007I\u0011\u0001BO\u0011-y9\u0005'\u0001\u0003\u0012\u0003\u0006IAa(\t\u000f\u0005C\n\u0001\"\u0001\u0019\u001aQ1\u00014\u0004M\u000f1?\u00012!\u000eM\u0001\u0011\u001d\t\u0019\u000fg\u0006A\u0002QB\u0001\"c\u001e\u0019\u0018\u0001\u0007!q\u0014\u0005\u000b\u0019'D\n!!A\u0005\u0002a\rBC\u0002M\u000e1KA:\u0003C\u0005\u0002db\u0005\u0002\u0013!a\u0001i!Q\u0011r\u000fM\u0011!\u0003\u0005\rAa(\t\u00151u\u0007\u0014AI\u0001\n\u0003AZ#\u0006\u0002\u0019.)\u001aA\u0007d9\t\u00151]\b\u0014AI\u0001\n\u0003y\t\t\u0003\u0006\r\u0000b\u0005\u0011\u0011!C!\u000b\u007fA\u0011\"d\u0001\u0019\u0002\u0005\u0005I\u0011A\u000e\t\u00155\u001d\u0001\u0014AA\u0001\n\u0003A:\u0004\u0006\u0003\u0003\u001aae\u0002\u0002C\u0012\u00196\u0005\u0005\t\u0019\u0001\u000f\t\u00155=\u0001\u0014AA\u0001\n\u0003j\t\u0002\u0003\u0006\u000e\u001ea\u0005\u0011\u0011!C\u00011\u007f!2A\u0012M!\u0011%\u0019\u0003THA\u0001\u0002\u0004\u0011I\u0002C\u0005\u000e&a\u0005\u0011\u0011!C!Q!QQR\u0006M\u0001\u0003\u0003%\t\u0005g\u0012\u0015\u0007\u0019CJ\u0005C\u0005$1\u000b\n\t\u00111\u0001\u0003\u001a\u001dI\u0001T\n\u0001\u0002\u0002#\u0005\u0001tJ\u0001\u0010\u0007f\u001cG.[2SK\u001a,'/\u001a8dKB\u0019Q\u0007'\u0015\u0007\u0013a\r\u0001!!A\t\u0002aM3C\u0002M)1+b)\fE\u0005\u0016ha]CGa(\u0019\u001c%!\u0001\u0014LK5\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\u0005\b\u0003bEC\u0011\u0001M/)\tAz\u0005\u0003\u0006\bVbE\u0013\u0011!C#\u001bSA!\"&\u001f\u0019R\u0005\u0005I\u0011\u0011M2)\u0019AZ\u0002'\u001a\u0019h!9\u00111\u001dM1\u0001\u0004!\u0004\u0002CE<1C\u0002\rAa(\t\u0015U\u0015\u0005\u0014KA\u0001\n\u0003CZ\u0007\u0006\u0003\u0019naE\u0004#B\u0006\fNb=\u0004CB\u0006\u0007@R\u0012y\n\u0003\u0006\u0016\u0014b%\u0014\u0011!a\u0001179\u0011\u0002'\u001e\u0001\u0003\u0003EI\u0001g\u001e\u0002\u0017QK\b/\u001a%jgR|'/\u001f\t\u0004kaed!CH\u0018\u0001\u0005\u0005\t\u0012\u0002M>'\u0019AJ\b' \r6BaQsMK7\tw\u0014yjd\u000b\u0010,!9\u0011\t'\u001f\u0005\u0002a\u0005EC\u0001M<\u0011)9)\u000e'\u001f\u0002\u0002\u0013\u0015S\u0012\u0006\u0005\u000b+sBJ(!A\u0005\u0002b\u001dE\u0003CH\u00161\u0013CZ\t'$\t\u0011=U\u0002T\u0011a\u0001\twD\u0001\"c\u001e\u0019\u0006\u0002\u0007!q\u0014\u0005\t\u001f\u0017B*\t1\u0001\u0010,!QQS\u0011M=\u0003\u0003%\t\t'%\u0015\taM\u0005t\u0013\t\u0006\u0017-5\u0007T\u0013\t\n\u0017U5E1 BP\u001fWA!\"f%\u0019\u0010\u0006\u0005\t\u0019AH\u0016\u0011)AZ\n\u0001b\u0001\n\u000b1\u0001TT\u0001\u001dgfl'm\u001c7JgB{7o]5cY\u0016LeNU3gS:,W.\u001a8u+\t\t\u001a\u0001\u0003\u0005\u0019\"\u0002\u0001\u000bQBI\u0002\u0003u\u0019\u00180\u001c2pY&\u001b\bk\\:tS\ndW-\u00138SK\u001aLg.Z7f]R\u0004\u0003\u0002\u0003MS\u0001\u0011\u0015a\u0001g*\u0002'\u0005dGnU=nE>d7\u000fS1wK>;h.\u001a:\u0015\u000b\u0019CJ\u000bg+\t\u0011]\u0015\u00024\u0015a\u0001\u0007CCq!!*\u0019$\u0002\u0007A\u0007\u000b\u0003\u0019$b=\u0006\u0003\u0002MY1gk!\u0001$<\n\taUFR\u001e\u0002\bi\u0006LGN]3d\u000f%AJ\fAA\u0001\u0012\u0003AZ,A\u0005Ts6\u0014w\u000e\\(qgB\u0019Q\u0007'0\u0007\u00131-\u0006!!A\t\u0002a}6C\u0002M_1\u0003d)\fE\u0005\u0016ha]c)!\u001f\r(\"9\u0011\t'0\u0005\u0002a\u0015GC\u0001M^\u0011)9)\u000e'0\u0002\u0002\u0013\u0015S\u0012\u0006\u0005\u000b+sBj,!A\u0005\u0002b-GC\u0002GT1\u001bDz\rC\u0004\r>b%\u0007\u0019\u0001$\t\u0011\u0015-\u0002\u0014\u001aa\u0001\u0003sB!\"&\"\u0019>\u0006\u0005I\u0011\u0011Mj)\u0011A*\u000e'7\u0011\u000b-Yi\rg6\u0011\r-1yLRA=\u0011))\u001a\n'5\u0002\u0002\u0003\u0007Ar\u0015\u0005\n1;\u0004!\u0019!C\u00011?\fa!\u00117m\u001fB\u001cXC\u0001GT\u0011!A\u001a\u000f\u0001Q\u0001\n1\u001d\u0016aB!mY>\u00038\u000f\t\u0005\b1O\u0004A\u0011\u0001Mu\u0003\u001d1E.Y4PaN$B\u0001d*\u0019l\"AQ1\u0006Ms\u0001\u0004\tI\bC\u0004\u0019p\u0002!I\u0001'=\u0002\u001fI,G.\u001a<b]R\u001c\u00160\u001c2pYN$B\u0001g=\u0019zB)QR\u0003M{i%\u0019\u0001t\u001f\u0019\u0003\u0007M+\u0017\u000f\u0003\u0005\u0018&a5\b\u0019\u0001M~!\u0015\u0019\t\u0002'@5\u0013\u0011A:pa\u0007\t\u000f5U\u0002\u0001\"\u0001\u001a\u0002Q!\u00114AM\u0004)\r1\u0012T\u0001\u0005\t\u000bWAz\u00101\u0001\u0002z!AqS\u0005M\u0000\u0001\u0004IJ\u0001\u0005\u0003\f\t'\"\u0004bBG\u001f\u0001\u0011\u0005\u0011T\u0002\u000b\u0004-e=\u0001\u0002CL\u00133\u0017\u0001\r!'\u0003\t\u0013eM\u0001!%A\u0005\u00021e\u0018a\u00078fo\u001a\u0013X-\u001a+fe6\u001c\u00160\u001c2pY\u0012\"WMZ1vYR$3\u0007C\u0005\u001a\u0018\u0001\t\n\u0011\"\u0001\rz\u0006Yb.Z<Ge\u0016,G+\u001f9f'fl'm\u001c7%I\u00164\u0017-\u001e7uIIB\u0011\"g\u0007\u0001#\u0003%\t\u0002d8\u0002/9,wo\u0015;vENKXNY8mI\u0011,g-Y;mi\u0012\"\u0004\u0003\u0002CB\u000b\u0003\u0001")
public interface Symbols
extends scala.reflect.api.Symbols {
    public void scala$reflect$internal$Symbols$_setter_$scala$reflect$internal$Symbols$$originalOwnerMap_$eq(HashMap var1);

    public void scala$reflect$internal$Symbols$_setter_$SymbolTag_$eq(ClassTag var1);

    public void scala$reflect$internal$Symbols$_setter_$TermSymbolTag_$eq(ClassTag var1);

    public void scala$reflect$internal$Symbols$_setter_$ModuleSymbolTag_$eq(ClassTag var1);

    public void scala$reflect$internal$Symbols$_setter_$MethodSymbolTag_$eq(ClassTag var1);

    public void scala$reflect$internal$Symbols$_setter_$TypeSymbolTag_$eq(ClassTag var1);

    public void scala$reflect$internal$Symbols$_setter_$ClassSymbolTag_$eq(ClassTag var1);

    public void scala$reflect$internal$Symbols$_setter_$FreeTermSymbolTag_$eq(ClassTag var1);

    public void scala$reflect$internal$Symbols$_setter_$FreeTypeSymbolTag_$eq(ClassTag var1);

    public void scala$reflect$internal$Symbols$_setter_$symbolIsPossibleInRefinement_$eq(Function1 var1);

    public void scala$reflect$internal$Symbols$_setter_$AllOps_$eq(SymbolOps var1);

    public int ids();

    @TraitSetter
    public void ids_$eq(int var1);

    public int nextId();

    public Map<Symbol, Object> scala$reflect$internal$Symbols$$_recursionTable();

    @TraitSetter
    public void scala$reflect$internal$Symbols$$_recursionTable_$eq(Map<Symbol, Object> var1);

    public Map<Symbol, Object> recursionTable();

    public void recursionTable_$eq(Map<Symbol, Object> var1);

    public int scala$reflect$internal$Symbols$$existentialIds();

    @TraitSetter
    public void scala$reflect$internal$Symbols$$existentialIds_$eq(int var1);

    public int nextExistentialId();

    public Names.TypeName freshExistentialName(String var1);

    public ModuleSymbol connectModuleToClass(ModuleSymbol var1, ClassSymbol var2);

    public FreeTermSymbol newFreeTermSymbol(Names.TermName var1, Function0<Object> var2, long var3, String var5);

    public long newFreeTermSymbol$default$3();

    public FreeTypeSymbol newFreeTypeSymbol(Names.TypeName var1, long var2, String var4);

    public long newFreeTypeSymbol$default$2();

    public HashMap<Symbol, Symbol> scala$reflect$internal$Symbols$$originalOwnerMap();

    public void saveOriginalOwner(Symbol var1);

    public void defineOriginalOwner(Symbol var1, Symbol var2);

    public <T> TypeSymbol symbolOf(TypeTags.WeakTypeTag<T> var1);

    public Symbols$SymbolKind$ SymbolKind();

    public Symbol newStubSymbol(Symbol var1, Names.Name var2, String var3, boolean var4);

    public boolean newStubSymbol$default$4();

    public ClassTag<Symbol> SymbolTag();

    public ClassTag<TermSymbol> TermSymbolTag();

    public ClassTag<ModuleSymbol> ModuleSymbolTag();

    public ClassTag<MethodSymbol> MethodSymbolTag();

    public ClassTag<TypeSymbol> TypeSymbolTag();

    public ClassTag<ClassSymbol> ClassSymbolTag();

    public ClassTag<FreeTermSymbol> FreeTermSymbolTag();

    public ClassTag<FreeTypeSymbol> FreeTypeSymbolTag();

    public NoSymbol makeNoSymbol();

    @Override
    public NoSymbol NoSymbol();

    public List<Symbol> deriveSymbols(List<Symbol> var1, Function1<Symbol, Symbol> var2);

    public <A> List<Symbol> deriveSymbols2(List<Symbol> var1, List<A> var2, Function2<Symbol, A, Symbol> var3);

    public Types.Type deriveType(List<Symbol> var1, Function1<Symbol, Symbol> var2, Types.Type var3);

    public <A> Types.Type deriveType2(List<Symbol> var1, List<A> var2, Function2<Symbol, A, Symbol> var3, Types.Type var4);

    public Types.Type deriveTypeWithWildcards(List<Symbol> var1, Types.Type var2);

    public List<Symbol> cloneSymbols(List<Symbol> var1);

    public List<Symbol> cloneSymbolsAtOwner(List<Symbol> var1, Symbol var2);

    public List<Symbol> cloneSymbolsAndModify(List<Symbol> var1, Function1<Types.Type, Types.Type> var2);

    public List<Symbol> cloneSymbolsAtOwnerAndModify(List<Symbol> var1, Symbol var2, Function1<Types.Type, Types.Type> var3);

    public <T> T createFromClonedSymbols(List<Symbol> var1, Types.Type var2, Function2<List<Symbol>, Types.Type, T> var3);

    public <T> T createFromClonedSymbolsAtOwner(List<Symbol> var1, Symbol var2, Types.Type var3, Function2<List<Symbol>, Types.Type, T> var4);

    public <T> List<List<T>> mapParamss(Symbol var1, Function1<Symbol, T> var2);

    public List<Symbol> existingSymbols(List<Symbol> var1);

    public Symbol closestEnclMethod(Symbol var1);

    public Symbols$CyclicReference$ CyclicReference();

    public Symbols$TypeHistory$ scala$reflect$internal$Symbols$$TypeHistory();

    public Function1<Symbol, Object> symbolIsPossibleInRefinement();

    public boolean allSymbolsHaveOwner(List<Symbol> var1, Symbol var2);

    public Symbols$SymbolOps$ SymbolOps();

    public SymbolOps AllOps();

    public SymbolOps FlagOps(long var1);

    public void markFlagsCompleted(Seq<Symbol> var1, long var2);

    public void markAllCompleted(Seq<Symbol> var1);

    public abstract class Symbol
    extends SymbolContextApiImpl
    implements HasFlags,
    AnnotationInfos.Annotatable<Symbol>,
    StdAttachments.Attachable {
        public final Symbol scala$reflect$internal$Symbols$Symbol$$initOwner;
        public final Names.Name scala$reflect$internal$Symbols$Symbol$$initName;
        private Symbol _rawowner;
        private long _rawflags;
        private final int id;
        private int _validTo;
        private Symbol _privateWithin;
        private TypeHistory scala$reflect$internal$Symbols$$infos;
        private List<AnnotationInfos.AnnotationInfo> _annotations;
        private boolean isOverridingSymbol;
        private Attachments rawatt;
        private volatile boolean bitmap$0;

        private boolean isOverridingSymbol$lzycompute() {
            Symbol symbol = this;
            synchronized (symbol) {
                if (!this.bitmap$0) {
                    this.isOverridingSymbol = this.canMatchInheritedSymbols() && this.owner().ancestors().exists((Function1<Symbol, Object>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Symbol $outer;

                        public final boolean apply(Symbol base) {
                            Symbol symbol = this.$outer.overriddenSymbol(base);
                            NoSymbol noSymbol = this.$outer.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                            return symbol != null ? !symbol.equals(noSymbol) : noSymbol != null;
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                    this.bitmap$0 = true;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.isOverridingSymbol;
            }
        }

        @Override
        public Attachments rawatt() {
            return this.rawatt;
        }

        @Override
        public void rawatt_$eq(Attachments x$1) {
            this.rawatt = x$1;
        }

        @Override
        public Attachments attachments() {
            return StdAttachments$Attachable$class.attachments(this);
        }

        @Override
        public StdAttachments.Attachable setAttachments(Attachments attachments) {
            return StdAttachments$Attachable$class.setAttachments(this, attachments);
        }

        @Override
        public <T> StdAttachments.Attachable updateAttachment(T attachment, ClassTag<T> evidence$1) {
            return StdAttachments$Attachable$class.updateAttachment(this, attachment, evidence$1);
        }

        @Override
        public <T> StdAttachments.Attachable removeAttachment(ClassTag<T> evidence$2) {
            return StdAttachments$Attachable$class.removeAttachment(this, evidence$2);
        }

        @Override
        public <T> boolean hasAttachment(ClassTag<T> evidence$3) {
            return StdAttachments$Attachable$class.hasAttachment(this, evidence$3);
        }

        @Override
        public Position pos() {
            return StdAttachments$Attachable$class.pos(this);
        }

        @Override
        public void pos_$eq(Position pos) {
            StdAttachments$Attachable$class.pos_$eq(this, pos);
        }

        @Override
        public StdAttachments.Attachable setPos(Position newpos) {
            return StdAttachments$Attachable$class.setPos(this, newpos);
        }

        @Override
        public List<AnnotationInfos.AnnotationInfo> staticAnnotations() {
            return AnnotationInfos$Annotatable$class.staticAnnotations(this);
        }

        @Override
        public List<Symbol> throwsAnnotations() {
            return AnnotationInfos$Annotatable$class.throwsAnnotations(this);
        }

        @Override
        public Object addThrowsAnnotation(Symbol throwableSym) {
            return AnnotationInfos$Annotatable$class.addThrowsAnnotation(this, throwableSym);
        }

        @Override
        public boolean hasAnnotation(Symbol cls) {
            return AnnotationInfos$Annotatable$class.hasAnnotation(this, cls);
        }

        @Override
        public Option<AnnotationInfos.AnnotationInfo> getAnnotation(Symbol cls) {
            return AnnotationInfos$Annotatable$class.getAnnotation(this, cls);
        }

        @Override
        public Object removeAnnotation(Symbol cls) {
            return AnnotationInfos$Annotatable$class.removeAnnotation(this, cls);
        }

        @Override
        public final Object withAnnotation(AnnotationInfos.AnnotationInfo annot) {
            return AnnotationInfos$Annotatable$class.withAnnotation(this, annot);
        }

        @Override
        public boolean hasNoFlags(long mask) {
            return HasFlags$class.hasNoFlags(this, mask);
        }

        @Override
        public String flagString() {
            return HasFlags$class.flagString(this);
        }

        @Override
        public String flagString(long mask) {
            return HasFlags$class.flagString(this, mask);
        }

        @Override
        public boolean hasAbstractFlag() {
            return HasFlags$class.hasAbstractFlag(this);
        }

        @Override
        public boolean hasAccessorFlag() {
            return HasFlags$class.hasAccessorFlag(this);
        }

        @Override
        public boolean hasDefault() {
            return HasFlags$class.hasDefault(this);
        }

        @Override
        public boolean hasJavaEnumFlag() {
            return HasFlags$class.hasJavaEnumFlag(this);
        }

        @Override
        public boolean hasJavaAnnotationFlag() {
            return HasFlags$class.hasJavaAnnotationFlag(this);
        }

        @Override
        public boolean hasLocalFlag() {
            return HasFlags$class.hasLocalFlag(this);
        }

        @Override
        public boolean isLocalToThis() {
            return HasFlags$class.isLocalToThis(this);
        }

        @Override
        public boolean hasModuleFlag() {
            return HasFlags$class.hasModuleFlag(this);
        }

        @Override
        public boolean hasPackageFlag() {
            return HasFlags$class.hasPackageFlag(this);
        }

        @Override
        public boolean hasStableFlag() {
            return HasFlags$class.hasStableFlag(this);
        }

        @Override
        public boolean hasStaticFlag() {
            return HasFlags$class.hasStaticFlag(this);
        }

        @Override
        public boolean isAbstractOverride() {
            return HasFlags$class.isAbstractOverride(this);
        }

        @Override
        public boolean isAnyOverride() {
            return HasFlags$class.isAnyOverride(this);
        }

        @Override
        public boolean isCase() {
            return HasFlags$class.isCase(this);
        }

        @Override
        public boolean isCaseAccessor() {
            return HasFlags$class.isCaseAccessor(this);
        }

        @Override
        public boolean isDeferred() {
            return HasFlags$class.isDeferred(this);
        }

        @Override
        public boolean isFinal() {
            return HasFlags$class.isFinal(this);
        }

        @Override
        public boolean isArtifact() {
            return HasFlags$class.isArtifact(this);
        }

        @Override
        public boolean isImplicit() {
            return HasFlags$class.isImplicit(this);
        }

        @Override
        public boolean isInterface() {
            return HasFlags$class.isInterface(this);
        }

        @Override
        public boolean isJavaDefined() {
            return HasFlags$class.isJavaDefined(this);
        }

        @Override
        public boolean isLazy() {
            return HasFlags$class.isLazy(this);
        }

        @Override
        public boolean isLifted() {
            return HasFlags$class.isLifted(this);
        }

        @Override
        public boolean isMacro() {
            return HasFlags$class.isMacro(this);
        }

        @Override
        public boolean isMutable() {
            return HasFlags$class.isMutable(this);
        }

        @Override
        public boolean isOverride() {
            return HasFlags$class.isOverride(this);
        }

        @Override
        public boolean isParamAccessor() {
            return HasFlags$class.isParamAccessor(this);
        }

        @Override
        public boolean isPrivate() {
            return HasFlags$class.isPrivate(this);
        }

        @Override
        public boolean isPackage() {
            return HasFlags$class.isPackage(this);
        }

        @Override
        public boolean isPrivateLocal() {
            return HasFlags$class.isPrivateLocal(this);
        }

        @Override
        public boolean isProtected() {
            return HasFlags$class.isProtected(this);
        }

        @Override
        public boolean isProtectedLocal() {
            return HasFlags$class.isProtectedLocal(this);
        }

        @Override
        public boolean isPublic() {
            return HasFlags$class.isPublic(this);
        }

        @Override
        public boolean isSealed() {
            return HasFlags$class.isSealed(this);
        }

        @Override
        public boolean isSpecialized() {
            return HasFlags$class.isSpecialized(this);
        }

        @Override
        public boolean isSuperAccessor() {
            return HasFlags$class.isSuperAccessor(this);
        }

        @Override
        public boolean isSynthetic() {
            return HasFlags$class.isSynthetic(this);
        }

        @Override
        public boolean isDeferredOrJavaDefault() {
            return HasFlags$class.isDeferredOrJavaDefault(this);
        }

        @Override
        public boolean isDeferredNotJavaDefault() {
            return HasFlags$class.isDeferredNotJavaDefault(this);
        }

        @Override
        public String flagBitsToString(long bits2) {
            return HasFlags$class.flagBitsToString(this, bits2);
        }

        @Override
        public String accessString() {
            return HasFlags$class.accessString(this);
        }

        @Override
        public String calculateFlagString(long basis) {
            return HasFlags$class.calculateFlagString(this, basis);
        }

        @Override
        public boolean isParameter() {
            return HasFlags$class.isParameter(this);
        }

        private boolean isSynchronized() {
            return this instanceof SynchronizedSymbols.SynchronizedSymbol;
        }

        private boolean isAprioriThreadsafe() {
            return this.isThreadsafe(this.scala$reflect$internal$Symbols$Symbol$$$outer().AllOps());
        }

        public abstract Names.Name rawname();

        @Override
        public abstract Names.Name name();

        public void name_$eq(Names.Name n) {
            if (this.scala$reflect$internal$Symbols$Symbol$$$outer().shouldLogAtThisPhase()) {
                if (this.isSpecialized()) {
                    this.scala$reflect$internal$Symbols$Symbol$$$outer().debuglog((Function0<String>)((Object)new Serializable(this, n){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Symbol $outer;
                        private final Names.Name n$1;

                        public final String apply() {
                            return this.$outer.scala$reflect$internal$Symbols$Symbol$$msg$1(this.n$1);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.n$1 = n$1;
                        }
                    }));
                } else {
                    this.scala$reflect$internal$Symbols$Symbol$$$outer().log((Function0<Object>)((Object)new Serializable(this, n){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Symbol $outer;
                        private final Names.Name n$1;

                        public final String apply() {
                            return this.$outer.scala$reflect$internal$Symbols$Symbol$$msg$1(this.n$1);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.n$1 = n$1;
                        }
                    }));
                }
            }
        }

        public abstract Names.Name asNameType(Names.Name var1);

        public Symbol rawowner() {
            return this._rawowner;
        }

        public long rawflags() {
            return this._rawflags;
        }

        public int id() {
            return this.id;
        }

        public int validTo() {
            return this._validTo;
        }

        public void validTo_$eq(int x) {
            this._validTo = x;
        }

        public Symbol setName(Names.Name name) {
            this.name_$eq(this.asNameType(name));
            return this;
        }

        public void changeNameInOwners(Names.Name name) {
            if (this.owner().isClass()) {
                for (TypeHistory ifs = this.owner().scala$reflect$internal$Symbols$$infos(); ifs != null; ifs = ifs.prev()) {
                    ifs.info().decls().rehash(this, name);
                }
            }
        }

        public String rawFlagString(long mask) {
            return this.calculateFlagString(this.rawflags() & mask);
        }

        public String rawFlagString() {
            return this.rawFlagString(this.flagMask());
        }

        public String debugFlagString() {
            return this.flagString(-1L);
        }

        public String varianceString() {
            return Variance$.MODULE$.symbolicString$extension(this.variance());
        }

        @Override
        public long flagMask() {
            MutableSettings.SettingValue settingValue = this.scala$reflect$internal$Symbols$Symbol$$$outer().settings().debug();
            MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
            return BoxesRunTime.unboxToBoolean(settingValue.value()) && !this.isAbstractType() ? -1L : (this.owner().isRefinementClass() ? 0x800080040E2FL & (long)(~2) : 140739636104751L);
        }

        public String flagsExplanationString() {
            return this.isGADTSkolem() ? " (this is a GADT skolem)" : "";
        }

        public String shortSymbolClass() {
            return scala.reflect.internal.util.package$.MODULE$.shortClassOfInstance(this);
        }

        public String symbolCreationString() {
            String string2;
            Predef$ predef$ = Predef$.MODULE$;
            StringOps stringOps = new StringOps("%s%25s | %-40s | %s");
            Object[] objectArray = new Object[4];
            MutableSettings.SettingValue settingValue = this.scala$reflect$internal$Symbols$Symbol$$$outer().settings().uniqid();
            MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
            if (BoxesRunTime.unboxToBoolean(settingValue.value())) {
                Predef$ predef$2 = Predef$.MODULE$;
                string2 = new StringOps("%06d | ").format(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.id())}));
            } else {
                string2 = "";
            }
            objectArray[0] = string2;
            objectArray[1] = this.shortSymbolClass();
            objectArray[2] = new StringBuilder().append((Object)this.name().decode()).append((Object)" in ").append(this.owner()).toString();
            objectArray[3] = this.rawFlagString();
            return stringOps.format(Predef$.MODULE$.genericWrapArray(objectArray));
        }

        public final TermSymbol newValue(Names.TermName name, Position pos, long newFlags) {
            return this.newTermSymbol(name, pos, newFlags);
        }

        public final Position newValue$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newValue$default$3() {
            return 0L;
        }

        public final TermSymbol newVariable(Names.TermName name, Position pos, long newFlags) {
            return this.newTermSymbol(name, pos, 0x1000L | newFlags);
        }

        public final Position newVariable$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newVariable$default$3() {
            return 0L;
        }

        public final TermSymbol newValueParameter(Names.TermName name, Position pos, long newFlags) {
            return this.newTermSymbol(name, pos, 0x2000L | newFlags);
        }

        public final Position newValueParameter$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newValueParameter$default$3() {
            return 0L;
        }

        public final TermSymbol newLocalDummy(Position pos) {
            return (TermSymbol)this.newTermSymbol(this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().localDummyName(this), pos, this.newTermSymbol$default$3()).setInfo(this.scala$reflect$internal$Symbols$Symbol$$$outer().NoType());
        }

        public final MethodSymbol newMethod(Names.TermName name, Position pos, long newFlags) {
            return this.createMethodSymbol(name, pos, 0x40L | newFlags);
        }

        public final Position newMethod$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newMethod$default$3() {
            return 0L;
        }

        public final MethodSymbol newMethodSymbol(Names.TermName name, Position pos, long newFlags) {
            return this.createMethodSymbol(name, pos, 0x40L | newFlags);
        }

        public final Position newMethodSymbol$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newMethodSymbol$default$3() {
            return 0L;
        }

        public final MethodSymbol newLabel(Names.TermName name, Position pos) {
            return this.newMethod(name, pos, 131072L);
        }

        public final Position newLabel$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final MethodSymbol newConstructor(Position pos, long newFlags) {
            return this.newMethod(this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().CONSTRUCTOR(), pos, this.getFlag(0x100000L) | newFlags);
        }

        public final long newConstructor$default$2() {
            return 0L;
        }

        public MethodSymbol newStaticConstructor(Position pos) {
            return (MethodSymbol)this.newConstructor(pos, 0x800000L).setInfo(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().UnitTpe());
        }

        public MethodSymbol newClassConstructor(Position pos) {
            return (MethodSymbol)this.newConstructor(pos, this.newConstructor$default$2()).setInfo(new Types.MethodType(this.scala$reflect$internal$Symbols$Symbol$$$outer(), Nil$.MODULE$, this.tpe()));
        }

        public ModuleSymbol newLinkedModule(Symbol clazz, long newFlags) {
            ModuleSymbol m = this.newModuleSymbol(clazz.name().toTermName(), clazz.pos(), 0x100L | newFlags);
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().connectModuleToClass(m, (ClassSymbol)clazz);
        }

        public long newLinkedModule$default$2() {
            return 0L;
        }

        public final ModuleSymbol newModule(Names.TermName name, Position pos, long newFlags0) {
            long newFlags = newFlags0 | 0x100L;
            ModuleSymbol m = this.newModuleSymbol(name, pos, newFlags);
            ModuleClassSymbol clazz = this.newModuleClass(name.toTypeName(), pos, newFlags & 0x384925L);
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().connectModuleToClass(m, clazz);
        }

        public final Position newModule$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newModule$default$3() {
            return 0L;
        }

        public final ModuleSymbol newPackage(Names.TermName name, Position pos, long newFlags) {
            Names.TermName termName = name;
            Names.Name name2 = this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().ROOT();
            boolean bl = !(termName == null ? name2 != null : !termName.equals(name2)) || this.isPackageClass();
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(this).toString());
            }
            return this.newModule(name, pos, 0x104120L | newFlags);
        }

        public final Position newPackage$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newPackage$default$3() {
            return 0L;
        }

        public final TermSymbol newThisSym(Names.TermName name, Position pos) {
            return this.newTermSymbol(name, pos, 0x200000L);
        }

        public final Names.TermName newThisSym$default$1() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().this_();
        }

        public final Position newThisSym$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final TermSymbol newImport(Position pos) {
            return this.newTermSymbol((Names.TermName)this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().IMPORT(), pos, this.newTermSymbol$default$3());
        }

        public TermSymbol newModuleVarSymbol(Symbol accessor) {
            Names.TermName newName = this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().moduleVarName(accessor.name().toTermName());
            int newFlags = 0x40000000 | (this.isClass() ? 2621444 : 0);
            Types.Type newInfo = accessor.tpe().finalResultType();
            TermSymbol mval = (TermSymbol)this.newVariable(newName, accessor.pos().focus(), newFlags).addAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().VolatileAttr(), Predef$.MODULE$.wrapRefArray((Object[])new Trees.Tree[0]));
            return this.isClass() ? (TermSymbol)mval.setInfoAndEnter(newInfo) : (TermSymbol)mval.setInfo(newInfo);
        }

        public final ModuleSymbol newModuleSymbol(Names.TermName name, Position pos, long newFlags) {
            return (ModuleSymbol)this.newTermSymbol(name, pos, newFlags);
        }

        public final Position newModuleSymbol$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newModuleSymbol$default$3() {
            return 0L;
        }

        public final Tuple2<ModuleSymbol, ClassSymbol> newModuleAndClassSymbol(Names.Name name, Position pos, long flags0) {
            long flags = flags0 | 0x100L;
            ModuleSymbol m = this.newModuleSymbol(name.toTermName(), pos, flags);
            ModuleClassSymbol c = this.newModuleClass(name.toTypeName(), pos, flags & 0x384925L);
            this.scala$reflect$internal$Symbols$Symbol$$$outer().connectModuleToClass(m, c);
            return new Tuple2<ModuleSymbol, ClassSymbol>(m, c);
        }

        public final ModuleClassSymbol newModuleClassSymbol(Names.TypeName name, Position pos, long newFlags) {
            return (ModuleClassSymbol)this.newClassSymbol(name, pos, newFlags);
        }

        public final TypeSkolem newTypeSkolemSymbol(Names.TypeName name, Object origin, Position pos, long newFlags) {
            return this.createTypeSkolemSymbol(name, origin, pos, newFlags);
        }

        public final TermSymbol newOverloaded(Types.Type pre, List<Symbol> alternatives) {
            return (TermSymbol)this.newTermSymbol(alternatives.head().name().toTermName(), ((StdAttachments.Attachable)alternatives.head()).pos(), 0x200000000L).setInfo(new Types.OverloadedType(this.scala$reflect$internal$Symbols$Symbol$$$outer(), pre, alternatives));
        }

        public final TermSymbol newErrorValue(Names.TermName name) {
            return (TermSymbol)this.newTermSymbol(name, this.pos(), 0x100200000L).setInfo(this.scala$reflect$internal$Symbols$Symbol$$$outer().ErrorType());
        }

        public final AliasTypeSymbol newAliasType(Names.TypeName name, Position pos, long newFlags) {
            return this.createAliasTypeSymbol(name, pos, newFlags);
        }

        public final Position newAliasType$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newAliasType$default$3() {
            return 0L;
        }

        public final AbstractTypeSymbol newAbstractType(Names.TypeName name, Position pos, long newFlags) {
            return this.createAbstractTypeSymbol(name, pos, 0x10L | newFlags);
        }

        public final Position newAbstractType$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newAbstractType$default$3() {
            return 0L;
        }

        public final TypeSymbol newTypeParameter(Names.TypeName name, Position pos, long newFlags) {
            return this.newAbstractType(name, pos, 0x2000L | newFlags);
        }

        public final Position newTypeParameter$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newTypeParameter$default$3() {
            return 0L;
        }

        public final TypeSymbol newExistential(Names.TypeName name, Position pos, long newFlags) {
            return this.newAbstractType(name, pos, 0x800000000L | newFlags);
        }

        public final Position newExistential$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newExistential$default$3() {
            return 0L;
        }

        private Function0<Names.TermName> freshNamer() {
            IntRef cnt = IntRef.create(0);
            return new Serializable(this, cnt){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Symbol $outer;
                private final IntRef cnt$1;

                public final Names.TermName apply() {
                    ++this.cnt$1.elem;
                    return this.$outer.scala$reflect$internal$Symbols$Symbol$$$outer().nme().syntheticParamName(this.cnt$1.elem);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.cnt$1 = cnt$1;
                }
            };
        }

        public final List<TermSymbol> newSyntheticValueParams(List<Types.Type> argtypes) {
            return this.newSyntheticValueParams(argtypes, this.freshNamer());
        }

        public final List<TermSymbol> newSyntheticValueParams(List<Types.Type> argtypes, Function0<Names.TermName> freshName) {
            return argtypes.map(new Serializable(this, freshName){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Symbol $outer;
                private final Function0 freshName$1;

                public final TermSymbol apply(Types.Type tp) {
                    return this.$outer.newSyntheticValueParam(tp, (Names.TermName)this.freshName$1.apply());
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.freshName$1 = freshName$1;
                }
            }, List$.MODULE$.canBuildFrom());
        }

        public final TermSymbol newSyntheticValueParam(Types.Type argtype, Names.TermName name) {
            return (TermSymbol)this.newValueParameter(name, this.owner().pos().focus(), 0x200000L).setInfo(argtype);
        }

        public final Names.TermName newSyntheticValueParam$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().syntheticParamName(1);
        }

        public TypeSymbol newSyntheticTypeParam(String name, long newFlags) {
            return (TypeSymbol)this.newTypeParameter(this.scala$reflect$internal$Symbols$Symbol$$$outer().newTypeName(name), this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition(), newFlags).setInfo(this.scala$reflect$internal$Symbols$Symbol$$$outer().TypeBounds().empty());
        }

        public List<TypeSymbol> newSyntheticTypeParams(int num) {
            Predef$ predef$ = Predef$.MODULE$;
            return RichInt$.MODULE$.until$extension0(0, num).toList().map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Symbol $outer;

                public final TypeSymbol apply(int n) {
                    return this.$outer.newSyntheticTypeParam(new StringBuilder().append((Object)"T").append(BoxesRunTime.boxToInteger(n)).toString(), 0L);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom());
        }

        public TypeSkolem newExistentialSkolem(Symbol basis, Object origin) {
            return this.newExistentialSkolem(basis.name().toTypeName(), basis.info(), basis.flags(), basis.pos(), origin);
        }

        public TypeSkolem newExistentialSkolem(Names.TypeName name, Types.Type info2, long flags, Position pos, Object origin) {
            TypeSkolem skolem = this.newTypeSkolemSymbol(name.toTypeName(), origin, pos, (flags | 0x800000000L) & (long)(~8192));
            return (TypeSkolem)skolem.setInfo(info2.cloneInfo(skolem));
        }

        public final int GADT_SKOLEM_FLAGS() {
            return 0x1200000;
        }

        public TypeSkolem newGADTSkolem(Names.TypeName name, Symbol origin, Types.Type info2) {
            return (TypeSkolem)this.newTypeSkolemSymbol(name, origin, origin.pos(), origin.flags() & (0x800002000L ^ 0xFFFFFFFFFFFFFFFFL) | (long)this.GADT_SKOLEM_FLAGS()).setInfo(info2);
        }

        public final TypeSymbol freshExistential(String suffix) {
            return this.newExistential(this.scala$reflect$internal$Symbols$Symbol$$$outer().freshExistentialName(suffix), this.pos(), this.newExistential$default$3());
        }

        public final TypeSkolem newTypeSkolem() {
            return this.owner().newTypeSkolemSymbol(this.name().toTypeName(), this, this.pos(), this.flags());
        }

        public final Position newTypeSkolemSymbol$default$3() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newTypeSkolemSymbol$default$4() {
            return 0L;
        }

        public final ClassSymbol newClass(Names.TypeName name, Position pos, long newFlags) {
            return this.newClassSymbol(name, pos, newFlags);
        }

        public final Position newClass$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newClass$default$3() {
            return 0L;
        }

        public ClassSymbol newClassWithInfo(Names.TypeName name, List<Types.Type> parents2, Scopes.Scope scope, Position pos, long newFlags) {
            ClassSymbol clazz = this.newClass(name, pos, newFlags);
            return (ClassSymbol)clazz.setInfo(new Types.ClassInfoType(this.scala$reflect$internal$Symbols$Symbol$$$outer(), parents2, scope, clazz));
        }

        public Position newClassWithInfo$default$4() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public long newClassWithInfo$default$5() {
            return 0L;
        }

        public final ClassSymbol newErrorClass(Names.TypeName name) {
            return this.newClassWithInfo(name, Nil$.MODULE$, new Scopes.ErrorScope(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this), this.pos(), 0x100200000L);
        }

        public final ModuleClassSymbol newModuleClass(Names.TypeName name, Position pos, long newFlags) {
            return this.newModuleClassSymbol(name, pos, newFlags | 0x100L);
        }

        public final Position newModuleClass$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newModuleClass$default$3() {
            return 0L;
        }

        public final Position newModuleClassSymbol$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newModuleClassSymbol$default$3() {
            return 0L;
        }

        public final ClassSymbol newAnonymousFunctionClass(Position pos, long newFlags) {
            return this.newClassSymbol((Names.TypeName)this.scala$reflect$internal$Symbols$Symbol$$$outer().tpnme().ANON_FUN_NAME(), pos, 0x200020L | newFlags);
        }

        public final Position newAnonymousFunctionClass$default$1() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newAnonymousFunctionClass$default$2() {
            return 0L;
        }

        public final TermSymbol newAnonymousFunctionValue(Position pos, long newFlags) {
            return (TermSymbol)this.newTermSymbol((Names.TermName)this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().ANON_FUN_NAME(), pos, 0x200000L | newFlags).setInfo(this.scala$reflect$internal$Symbols$Symbol$$$outer().NoType());
        }

        public final long newAnonymousFunctionValue$default$2() {
            return 0L;
        }

        public ClassSymbol newImplClass(Names.TypeName name, Position pos, long newFlags) {
            return this.newClassSymbol(name, pos, newFlags | 0x2000000000L);
        }

        public Position newImplClass$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public long newImplClass$default$3() {
            return 0L;
        }

        public final RefinementClassSymbol newRefinementClass(Position pos) {
            return this.createRefinementClassSymbol(pos, 0L);
        }

        public final Symbol newErrorSymbol(Names.Name name) {
            block4: {
                Symbol symbol;
                block3: {
                    block2: {
                        if (!(name instanceof Names.TypeName)) break block2;
                        Names.TypeName typeName = (Names.TypeName)name;
                        symbol = this.newErrorClass(typeName);
                        break block3;
                    }
                    if (!(name instanceof Names.TermName)) break block4;
                    Names.TermName termName = (Names.TermName)name;
                    symbol = this.newErrorValue(termName);
                }
                return symbol;
            }
            throw new MatchError(name);
        }

        public Symbol newStubSymbol(Names.Name name, String missingMessage, boolean isPackage) {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().newStubSymbol(this, name, missingMessage, isPackage);
        }

        public boolean newStubSymbol$default$3() {
            return false;
        }

        /*
         * WARNING - void declaration
         */
        public Symbol sugaredSymbolOrSelf() {
            Symbol symbol;
            Symbol getter2;
            Symbol symbol2 = getter2 = this.getterIn(this.owner());
            NoSymbol noSymbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
            if (!(symbol2 != null ? !symbol2.equals(noSymbol) : noSymbol != null)) {
                symbol = this;
            } else {
                void var3_7;
                Symbol setter2;
                Symbol qual$1 = this.owner();
                Names.TermName x$56 = getter2.name().toTermName();
                long x$57 = getter2.flags() & (long)(~64);
                Position x$58 = qual$1.newValue$default$2();
                TermSymbol result2 = (TermSymbol)qual$1.newValue(x$56, x$58, x$57).setPrivateWithin(getter2.privateWithin()).setInfo(getter2.info().resultType());
                Symbol symbol3 = setter2 = this.setterIn(this.owner(), this.setterIn$default$2());
                NoSymbol noSymbol2 = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                Object object = !(symbol3 != null ? !symbol3.equals(noSymbol2) : noSymbol2 != null) ? BoxedUnit.UNIT : result2.setFlag(4096L);
                symbol = var3_7;
            }
            return symbol;
        }

        public boolean lockOK() {
            boolean bl;
            block5: {
                block1: {
                    Option option;
                    block4: {
                        block0: {
                            boolean bl2;
                            block3: {
                                block2: {
                                    if ((this._rawflags & 0x8000000000L) == 0L) break block0;
                                    if (BoxesRunTime.unboxToInt(this.scala$reflect$internal$Symbols$Symbol$$$outer().settings().Yrecursion().value()) == 0) break block1;
                                    option = this.scala$reflect$internal$Symbols$Symbol$$$outer().recursionTable().get(this);
                                    if (!(option instanceof Some)) break block2;
                                    Some some = (Some)option;
                                    bl2 = BoxesRunTime.unboxToInt(some.x()) <= BoxesRunTime.unboxToInt(this.scala$reflect$internal$Symbols$Symbol$$$outer().settings().Yrecursion().value());
                                    break block3;
                                }
                                if (!None$.MODULE$.equals(option)) break block4;
                                bl2 = true;
                            }
                            if (!bl2) break block1;
                        }
                        bl = true;
                        break block5;
                    }
                    throw new MatchError(option);
                }
                bl = false;
            }
            return bl;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean lock(Function0<BoxedUnit> handler) {
            if ((this._rawflags & 0x8000000000L) != 0L) {
                if (BoxesRunTime.unboxToInt(this.scala$reflect$internal$Symbols$Symbol$$$outer().settings().Yrecursion().value()) != 0) {
                    Option option = this.scala$reflect$internal$Symbols$Symbol$$$outer().recursionTable().get(this);
                    if (option instanceof Some) {
                        Some some = (Some)option;
                        if (BoxesRunTime.unboxToInt(some.x()) > BoxesRunTime.unboxToInt(this.scala$reflect$internal$Symbols$Symbol$$$outer().settings().Yrecursion().value())) {
                            handler.apply$mcV$sp();
                            return false;
                        }
                        Integer n = BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(some.x()) + 1);
                        Symbol symbol = Predef$.MODULE$.ArrowAssoc(this);
                        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
                        this.scala$reflect$internal$Symbols$Symbol$$$outer().recursionTable_$eq(this.scala$reflect$internal$Symbols$Symbol$$$outer().recursionTable().$plus(new Tuple2<Symbol, Integer>(symbol, n)));
                        return true;
                    }
                    if (!None$.MODULE$.equals(option)) throw new MatchError(option);
                    Integer n = BoxesRunTime.boxToInteger(1);
                    Symbol symbol = Predef$.MODULE$.ArrowAssoc(this);
                    Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
                    this.scala$reflect$internal$Symbols$Symbol$$$outer().recursionTable_$eq(this.scala$reflect$internal$Symbols$Symbol$$$outer().recursionTable().$plus(new Tuple2<Symbol, Integer>(symbol, n)));
                    return true;
                }
                handler.apply$mcV$sp();
                return false;
            }
            this._rawflags |= 0x8000000000L;
            return true;
        }

        public void unlock() {
            if ((this._rawflags & 0x8000000000L) != 0L) {
                this._rawflags &= 0x8000000000L ^ 0xFFFFFFFFFFFFFFFFL;
                if (BoxesRunTime.unboxToInt(this.scala$reflect$internal$Symbols$Symbol$$$outer().settings().Yrecursion().value()) != 0) {
                    this.scala$reflect$internal$Symbols$Symbol$$$outer().recursionTable_$eq((Map)this.scala$reflect$internal$Symbols$Symbol$$$outer().recursionTable().$minus(this));
                }
            }
        }

        public boolean isAliasType() {
            return false;
        }

        public boolean isAbstractType() {
            return false;
        }

        public boolean isSkolem() {
            return false;
        }

        public boolean isNonClassType() {
            return false;
        }

        public boolean isBottomClass() {
            return false;
        }

        public boolean isAbstractClass() {
            return false;
        }

        public boolean isAnonOrRefinementClass() {
            return false;
        }

        public boolean isAnonymousClass() {
            return false;
        }

        public boolean isCaseClass() {
            return false;
        }

        public boolean isConcreteClass() {
            return false;
        }

        public boolean isImplClass() {
            return false;
        }

        public boolean isJavaInterface() {
            return false;
        }

        public boolean isNumericValueClass() {
            return false;
        }

        public boolean isPrimitiveValueClass() {
            return false;
        }

        public boolean isRefinementClass() {
            return false;
        }

        @Override
        public boolean isTrait() {
            return false;
        }

        public boolean isContravariant() {
            return false;
        }

        public boolean isCovariant() {
            return false;
        }

        public boolean isExistentialSkolem() {
            return false;
        }

        public boolean isExistentiallyBound() {
            return false;
        }

        public boolean isGADTSkolem() {
            return false;
        }

        public boolean isTypeParameter() {
            return false;
        }

        public boolean isTypeParameterOrSkolem() {
            return false;
        }

        public boolean isTypeSkolem() {
            return false;
        }

        public boolean isInvariant() {
            return !this.isCovariant() && !this.isContravariant();
        }

        public boolean isAccessor() {
            return false;
        }

        public boolean isBridge() {
            return false;
        }

        public boolean isCapturedVariable() {
            return false;
        }

        public boolean isClassConstructor() {
            return false;
        }

        @Override
        public boolean isConstructor() {
            return false;
        }

        public boolean isEarlyInitialized() {
            return false;
        }

        public boolean isGetter() {
            return false;
        }

        public boolean isDefaultGetter() {
            return false;
        }

        public boolean isLocalDummy() {
            return false;
        }

        public boolean isMixinConstructor() {
            return false;
        }

        public boolean isOverloaded() {
            return false;
        }

        public boolean isSetter() {
            return false;
        }

        public boolean isSetterParameter() {
            return false;
        }

        public boolean isValue() {
            return false;
        }

        public boolean isValueParameter() {
            return false;
        }

        public boolean isVariable() {
            return false;
        }

        public boolean isTermMacro() {
            return false;
        }

        public boolean isCaseAccessorMethod() {
            return false;
        }

        public boolean isLiftedMethod() {
            return false;
        }

        public boolean isSourceMethod() {
            return false;
        }

        public boolean isVarargsMethod() {
            return false;
        }

        @Override
        public boolean isLabel() {
            return false;
        }

        @Override
        public boolean isPackageClass() {
            return false;
        }

        public boolean isPackageObject() {
            return false;
        }

        public boolean isPackageObjectClass() {
            return false;
        }

        public boolean isPackageObjectOrClass() {
            return this.isPackageObject() || this.isPackageObjectClass();
        }

        public boolean isModuleOrModuleClass() {
            return this.isModule() || this.isModuleClass();
        }

        public boolean isRoot() {
            return false;
        }

        public boolean isRootPackage() {
            return false;
        }

        public boolean isRootSymbol() {
            return false;
        }

        public boolean isEmptyPackage() {
            return false;
        }

        public boolean isEmptyPackageClass() {
            return false;
        }

        public boolean isEffectiveRoot() {
            return false;
        }

        public boolean hasOnlyBottomSubclasses() {
            return this.isClass() && this.isFinal() && this.loop$1(this.typeParams());
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public final boolean isLazyAccessor() {
            if (!this.isLazy()) return false;
            Symbol symbol = this.lazyAccessor();
            NoSymbol noSymbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
            if (symbol == null) {
                if (noSymbol == null) return false;
                return true;
            } else if (symbol.equals(noSymbol)) return false;
            return true;
        }

        public final boolean isOverridableMember() {
            return !this.isClass() && !this.isEffectivelyFinal() && this.safeOwner().isClass();
        }

        public final boolean isInterpreterWrapper() {
            return this.hasFlag(256) && this.isTopLevel() && this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().isReplWrapperName(this.name());
        }

        public final long getFlag(long mask) {
            Object object = this.scala$reflect$internal$Symbols$Symbol$$$outer().isCompilerUniverse() || this.isThreadsafe(this.scala$reflect$internal$Symbols$Symbol$$$outer().FlagOps(mask)) ? BoxedUnit.UNIT : this.initialize();
            return this.flags() & mask;
        }

        @Override
        public final boolean hasFlag(long mask) {
            Object object = this.scala$reflect$internal$Symbols$Symbol$$$outer().isCompilerUniverse() || this.isThreadsafe(this.scala$reflect$internal$Symbols$Symbol$$$outer().FlagOps(mask)) ? BoxedUnit.UNIT : this.initialize();
            return (this.flags() & mask) != 0L;
        }

        public boolean hasFlag(int mask) {
            return this.hasFlag((long)mask);
        }

        @Override
        public final boolean hasAllFlags(long mask) {
            Object object = this.scala$reflect$internal$Symbols$Symbol$$$outer().isCompilerUniverse() || this.isThreadsafe(this.scala$reflect$internal$Symbols$Symbol$$$outer().FlagOps(mask)) ? BoxedUnit.UNIT : this.initialize();
            return (this.flags() & mask) == mask;
        }

        public Symbol setFlag(long mask) {
            this._rawflags |= mask;
            return this;
        }

        public Symbol resetFlag(long mask) {
            this._rawflags &= mask ^ 0xFFFFFFFFFFFFFFFFL;
            return this;
        }

        public void resetFlags() {
            this.rawflags_$eq(this.rawflags() & 0x104120L);
        }

        @Override
        public String resolveOverloadedFlag(long flag) {
            return Flags$.MODULE$.flagToString(flag);
        }

        public Symbol initFlags(long mask) {
            boolean bl = this.rawflags() == 0L;
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)this.symbolCreationString()).toString());
            }
            this._rawflags = mask;
            return this;
        }

        @Override
        public final long flags() {
            long fs = this._rawflags & this.scala$reflect$internal$Symbols$Symbol$$$outer().phase().flagMask();
            return (fs | (fs & 0xF8000000000000L) >>> 47) & ((fs & 0x700000000000000L) >>> 56 ^ 0xFFFFFFFFFFFFFFFFL);
        }

        public void flags_$eq(long fs) {
            this._rawflags = fs;
        }

        public void rawflags_$eq(long x) {
            this._rawflags = x;
        }

        public final boolean hasGetter() {
            return this.isTerm() && this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().isLocalName(this.name());
        }

        public boolean isModuleNotMethod() {
            return this.isModule() && !this.isMethod();
        }

        public boolean isStaticModule() {
            return this.isModuleNotMethod() && this.isStatic();
        }

        public final boolean isInitializedToDefault() {
            return !this.isType() && this.hasAllFlags(0x20008000000L);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public final boolean isThisSym() {
            if (!this.isTerm()) return false;
            Symbol symbol = this.owner().thisSym();
            if (symbol == null) return false;
            if (!symbol.equals(this)) return false;
            return true;
        }

        public final boolean isError() {
            return this.hasFlag(0x100000000L);
        }

        public final boolean isErroneous() {
            return this.isError() || this.isInitialized() && this.tpe_$times().isErroneous();
        }

        public boolean isHigherOrderTypeParameter() {
            return this.owner().isTypeParameterOrSkolem();
        }

        public boolean isClassLocalToConstructor() {
            return false;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public final boolean isDerivedValueClass() {
            if (!this.isClass()) return false;
            if (this.hasFlag(0x2004000)) return false;
            Symbol symbol = this.info().firstParent().typeSymbol();
            ClassSymbol classSymbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().AnyValClass();
            if (symbol == null) {
                if (classSymbol != null) {
                    return false;
                }
            } else if (!symbol.equals(classSymbol)) return false;
            if (this.isPrimitiveValueClass()) return false;
            return true;
        }

        public final boolean isMethodWithExtension() {
            return this.isMethod() && this.owner().isDerivedValueClass() && !this.isParamAccessor() && !this.isConstructor() && !this.hasFlag(0x10000000) && !this.isMacro() && !this.isSpecialized();
        }

        public final boolean isAnonymousFunction() {
            return this.isSynthetic() && this.name().containsName(this.scala$reflect$internal$Symbols$Symbol$$$outer().tpnme().ANON_FUN_NAME());
        }

        public final boolean isDelambdafyFunction() {
            return this.isSynthetic() && this.name().containsName(this.scala$reflect$internal$Symbols$Symbol$$$outer().tpnme().DELAMBDAFY_LAMBDA_CLASS_NAME());
        }

        public final boolean isDelambdafyTarget() {
            return this.isArtifact() && this.isMethod() && this.name().containsName(this.scala$reflect$internal$Symbols$Symbol$$$outer().tpnme().ANON_FUN_NAME());
        }

        public final boolean isDefinedInPackage() {
            return this.effectiveOwner().isPackageClass();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public final boolean needsFlatClasses() {
            if (!this.scala$reflect$internal$Symbols$Symbol$$$outer().phase().flatClasses()) return false;
            Symbol symbol = this.rawowner();
            NoSymbol noSymbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
            if (symbol == null) {
                if (noSymbol == null) return false;
            } else if (symbol.equals(noSymbol)) return false;
            if (this.rawowner().isPackageClass()) return false;
            return true;
        }

        public final boolean isPatternTypeVariable() {
            return this.isAbstractType() && !this.isExistential() && !this.isTypeParameterOrSkolem() && this.isLocalToBlock();
        }

        public void expandName(Symbol base) {
        }

        public boolean isInDefaultNamespace() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().UnqualifiedOwners().apply(this.effectiveOwner());
        }

        public Symbol effectiveOwner() {
            return this.owner().skipPackageObject();
        }

        public Symbol skipPackageObject() {
            return this;
        }

        public final Symbol skipConstructor() {
            return this.isConstructor() ? this.owner() : this;
        }

        public final boolean isOmittablePrefix() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().UnqualifiedOwners().apply(this.skipPackageObject()) || this.isEmptyPrefix();
        }

        public boolean isEmptyPrefix() {
            return this.isEffectiveRoot() || this.isAnonOrRefinementClass() || this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().isReplWrapperName(this.name());
        }

        public boolean isFBounded() {
            Types.Type type = this.info();
            boolean bl = type instanceof Types.TypeBounds ? this.info().baseTypeSeq().exists((Function1<Types.Type, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Symbol $outer;

                public final boolean apply(Types.Type x$1) {
                    return x$1.contains(this.$outer);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            })) : false;
            return bl;
        }

        public final boolean isMonomorphicType() {
            Types.Type info2;
            return this.isType() && ((info2 = this.originalInfo()) == null || info2.isComplete() && !info2.isHigherKinded());
        }

        public boolean isStrictFP() {
            return this.hasAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().ScalaStrictFPAttr()) || this.enclClass().hasAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().ScalaStrictFPAttr());
        }

        public boolean isSerializable() {
            return this.info().baseClasses().exists((Function1<Symbol, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Symbol $outer;

                /*
                 * Enabled force condition propagation
                 * Lifted jumps to return sites
                 */
                public final boolean apply(Symbol p) {
                    Symbol symbol = p;
                    ClassSymbol classSymbol = this.$outer.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().SerializableClass();
                    if (symbol == null) {
                        if (classSymbol == null) return true;
                    } else if (symbol.equals(classSymbol)) return true;
                    Symbol symbol2 = p;
                    ClassSymbol classSymbol2 = this.$outer.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().JavaSerializableClass();
                    if (symbol2 != null) {
                        if (!symbol2.equals(classSymbol2)) return false;
                        return true;
                    }
                    if (classSymbol2 == null) return true;
                    return false;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        public boolean hasBridgeAnnotation() {
            return this.hasAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().BridgeClass());
        }

        public boolean isDeprecated() {
            return this.hasAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().DeprecatedAttr());
        }

        public Option<String> deprecationMessage() {
            Option<AnnotationInfos.AnnotationInfo> option = this.getAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().DeprecatedAttr());
            return !option.isEmpty() ? option.get().stringArg(0) : None$.MODULE$;
        }

        public Option<String> deprecationVersion() {
            Option<AnnotationInfos.AnnotationInfo> option = this.getAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().DeprecatedAttr());
            return !option.isEmpty() ? option.get().stringArg(1) : None$.MODULE$;
        }

        public Option<Names.TermName> deprecatedParamName() {
            Option<AnnotationInfos.AnnotationInfo> option = this.getAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().DeprecatedNameAttr());
            return !option.isEmpty() ? option.get().symbolArg(0) : None$.MODULE$;
        }

        public boolean hasDeprecatedInheritanceAnnotation() {
            return this.hasAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().DeprecatedInheritanceAttr());
        }

        public Option<String> deprecatedInheritanceMessage() {
            Option<AnnotationInfos.AnnotationInfo> option = this.getAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().DeprecatedInheritanceAttr());
            return !option.isEmpty() ? option.get().stringArg(0) : None$.MODULE$;
        }

        public boolean hasDeprecatedOverridingAnnotation() {
            return this.hasAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().DeprecatedOverridingAttr());
        }

        public Option<String> deprecatedOverridingMessage() {
            Option<AnnotationInfos.AnnotationInfo> option = this.getAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().DeprecatedOverridingAttr());
            return !option.isEmpty() ? option.get().stringArg(0) : None$.MODULE$;
        }

        public boolean hasMigrationAnnotation() {
            return this.hasAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().MigrationAnnotationClass());
        }

        public Option<String> migrationMessage() {
            Option<AnnotationInfos.AnnotationInfo> option = this.getAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().MigrationAnnotationClass());
            return !option.isEmpty() ? option.get().stringArg(0) : None$.MODULE$;
        }

        public Option<String> migrationVersion() {
            Option<AnnotationInfos.AnnotationInfo> option = this.getAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().MigrationAnnotationClass());
            return !option.isEmpty() ? option.get().stringArg(1) : None$.MODULE$;
        }

        public Option<Object> elisionLevel() {
            Option<AnnotationInfos.AnnotationInfo> option = this.getAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().ElidableMethodClass());
            return !option.isEmpty() ? option.get().intArg(0) : None$.MODULE$;
        }

        public Option<String> implicitNotFoundMsg() {
            Option<AnnotationInfos.AnnotationInfo> option = this.getAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().ImplicitNotFoundClass());
            return !option.isEmpty() ? option.get().stringArg(0) : None$.MODULE$;
        }

        public boolean isCompileTimeOnly() {
            return this.hasAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().CompileTimeOnlyAttr());
        }

        public Option<String> compileTimeOnlyMessage() {
            Option<AnnotationInfos.AnnotationInfo> option = this.getAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().CompileTimeOnlyAttr());
            return !option.isEmpty() ? option.get().stringArg(0) : None$.MODULE$;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public final boolean isOuterAccessor() {
            if (!this.hasFlag(0x400000400000L)) return false;
            Names.Name name = this.unexpandedName();
            Names.TermName termName = this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().OUTER();
            if (name != null) {
                if (!name.equals(termName)) return false;
                return true;
            }
            if (termName == null) return true;
            return false;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public final boolean isOuterField() {
            if (!this.isArtifact()) return false;
            Names.Name name = this.unexpandedName();
            Names.TermName termName = this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().OUTER_LOCAL();
            if (name != null) {
                if (!name.equals(termName)) return false;
                return true;
            }
            if (termName == null) return true;
            return false;
        }

        public final boolean isStable() {
            return this.isTerm() && !this.isMutable() && !this.hasFlag(65536) && (!this.isMethod() || this.hasStableFlag());
        }

        public final boolean hasVolatileType() {
            return this.tpe().isVolatile() && !this.hasAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().uncheckedStableClass());
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public final boolean isPrimaryConstructor() {
            if (!this.isConstructor()) return false;
            Symbol symbol = this.owner().primaryConstructor();
            if (symbol == null) return false;
            if (!symbol.equals(this)) return false;
            return true;
        }

        public final boolean isAuxiliaryConstructor() {
            return this.isConstructor() && !this.isPrimaryConstructor();
        }

        public final boolean isCaseApplyOrUnapply() {
            return this.isMethod() && this.isCase() && this.isSynthetic();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public final boolean isCaseCopy() {
            if (!this.isMethod()) return false;
            if (!this.owner().isCase()) return false;
            if (!this.isSynthetic()) return false;
            Names.Name name = this.name();
            Names.TermName termName = this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().copy();
            if (name != null) {
                if (!name.equals(termName)) return false;
                return true;
            }
            if (termName == null) return true;
            return false;
        }

        public final boolean needsImplClass() {
            return this.isTrait() && (!this.isInterface() || this.hasFlag(Flags$.MODULE$.lateINTERFACE())) && !this.isImplClass();
        }

        public final boolean isImplOnly() {
            return this.isPrivate() || (this.owner().isTrait() || this.owner().isImplClass()) && (this.hasAllFlags(0x400000140L) || this.isConstructor() || this.hasFlag(Flags$.MODULE$.notPRIVATE() | 0x400000000L) && !this.hasFlag(0x18000100));
        }

        public final boolean isModuleVar() {
            return this.hasFlag(0x40000000);
        }

        @Override
        public boolean isStatic() {
            return this.hasFlag(0x800000) || this.owner().isStaticOwner();
        }

        public final boolean isStaticConstructor() {
            return this.isStaticMember() && this.isClassConstructor();
        }

        public final boolean isStaticMember() {
            return this.hasFlag(0x800000) || this.owner().isImplClass();
        }

        public final boolean isStaticOwner() {
            return this.isPackageClass() || this.isModuleClass() && this.isStatic();
        }

        private boolean isNotOverridden() {
            return this.owner().isClass() && (this.owner().isEffectivelyFinal() || this.owner().isSealed() && this.owner().children().forall((Function1<Symbol, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Symbol $outer;

                /*
                 * Enabled force condition propagation
                 * Lifted jumps to return sites
                 */
                public final boolean apply(Symbol c) {
                    if (!c.isEffectivelyFinal()) return false;
                    Symbol symbol = this.$outer.overridingSymbol(c);
                    NoSymbol noSymbol = this.$outer.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                    if (symbol != null) {
                        if (!symbol.equals(noSymbol)) return false;
                        return true;
                    }
                    if (noSymbol == null) return true;
                    return false;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            })));
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public final boolean isEffectivelyFinal() {
            if (this.hasFlag(16416)) return true;
            if (this.isModuleOrModuleClass()) {
                if (this.isTopLevel()) return true;
                MutableSettings.SettingValue settingValue = this.scala$reflect$internal$Symbols$Symbol$$$outer().settings().overrideObjects();
                MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
                if (!BoxesRunTime.unboxToBoolean(settingValue.value())) return true;
            }
            if (!this.isTerm()) return false;
            if (this.isPrivate()) return true;
            if (!this.isLocalToBlock()) return false;
            return true;
        }

        public final boolean isEffectivelyFinalOrNotOverridden() {
            return this.isEffectivelyFinal() || this.isTerm() && !this.isDeferred() && this.isNotOverridden();
        }

        public final boolean isTopLevel() {
            return this.owner().isPackageClass();
        }

        public final boolean isLocal() {
            return this.owner().isTerm();
        }

        public final boolean isLocalToBlock() {
            return this.owner().isTerm();
        }

        public final boolean isConstant() {
            return this.isStable() && this.scala$reflect$internal$Symbols$Symbol$$$outer().isConstantType(this.tpe().resultType());
        }

        public boolean isNestedClass() {
            return false;
        }

        public boolean isLocalClass() {
            return false;
        }

        public final boolean isStructuralRefinement() {
            return (this.isClass() || this.isType() || this.isModule()) && this.info().dealiasWiden().isStructuralRefinement();
        }

        public boolean isOnlyRefinementMember() {
            return this.isTerm() && this.owner().isRefinementClass() && this.isPossibleInRefinement() && !this.isConstant() && this.isDeclaredByOwner();
        }

        private boolean isDeclaredByOwner() {
            return this.owner().info().decl(this.name()).alternatives().exists((Function1<Symbol, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Symbol $outer;

                public final boolean apply(Symbol x$12) {
                    return this.$outer.alternatives().contains(x$12);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        public final boolean isStructuralRefinementMember() {
            return this.owner().isStructuralRefinement() && this.isPossibleInRefinement() && this.isPublic();
        }

        public final boolean isPossibleInRefinement() {
            return !this.isConstructor() && this.allOverriddenSymbols().forall((Function1<Symbol, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Symbol x$13) {
                    return x$13.owner().isRefinementClass();
                }
            }));
        }

        public final boolean isIncompleteIn(Symbol base) {
            boolean bl;
            block2: {
                while (true) {
                    Symbol supersym;
                    block4: {
                        block3: {
                            if (this_.isDeferred()) break block3;
                            if (!this_.hasFlag(262144)) break;
                            Symbol symbol = supersym = this_.superSymbolIn(base);
                            NoSymbol noSymbol = this_.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                            if (symbol != null ? !symbol.equals(noSymbol) : noSymbol != null) break block4;
                        }
                        bl = true;
                        break block2;
                    }
                    Symbol this_ = supersym;
                }
                bl = false;
            }
            return bl;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean exists() {
            boolean bl;
            if (!this.isTopLevel()) return true;
            Types.Type type = this.rawInfo();
            if (type instanceof SymbolTable.SymLoader) {
                SymbolTable.SymLoader symLoader = (SymbolTable.SymLoader)type;
                bl = symLoader.fromSource();
            } else {
                bl = false;
            }
            this.rawInfo().load(this);
            Types.Type type2 = this.rawInfo();
            Types$NoType$ types$NoType$ = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoType();
            if (type2 == null) {
                if (types$NoType$ != null) {
                    return true;
                }
            } else if (!type2.equals(types$NoType$)) return true;
            this.warnIfSourceLoader$1(bl);
            return false;
        }

        public final boolean isInitialized() {
            return this.validTo() != 0;
        }

        public boolean isThreadsafe(SymbolOps purpose) {
            return false;
        }

        public Symbol markFlagsCompleted(long mask) {
            return this;
        }

        public Symbol markAllCompleted() {
            return this;
        }

        public final boolean isLocatable() {
            NoSymbol noSymbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
            if (this.equals(noSymbol)) {
                return false;
            }
            if (this.isRoot() || this.isRootPackage()) {
                return true;
            }
            if (this.owner().isLocatable()) {
                if (this.owner().isTerm()) {
                    return false;
                }
                if (this.isLocalDummy()) {
                    return false;
                }
                if (this.isAliasType()) {
                    return true;
                }
                if (this.isType() && this.isNonClassType()) {
                    return false;
                }
                return !this.isRefinementClass();
            }
            return false;
        }

        public int variance() {
            return this.isCovariant() ? Variance$.MODULE$.Covariant() : (this.isContravariant() ? Variance$.MODULE$.Contravariant() : Variance$.MODULE$.Invariant());
        }

        public int paramPos() {
            return this.searchIn$1(this.owner().info(), 0);
        }

        @Override
        public Symbol owner() {
            return this.rawowner();
        }

        public final Symbol safeOwner() {
            return this == this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() : this.owner();
        }

        public final Symbol assertOwner() {
            if (this == this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                throw this.scala$reflect$internal$Symbols$Symbol$$$outer().abort("no-symbol does not have an owner");
            }
            return this.owner();
        }

        public Symbol originalOwner() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().scala$reflect$internal$Symbols$$originalOwnerMap().getOrElse(this, new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Symbol $outer;

                public final Symbol apply() {
                    return this.$outer.rawowner();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            });
        }

        public void owner_$eq(Symbol owner2) {
            this.scala$reflect$internal$Symbols$Symbol$$$outer().saveOriginalOwner(this);
            boolean bl = this.scala$reflect$internal$Symbols$Symbol$$$outer().isCompilerUniverse();
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)"owner_= is not thread-safe; cannot be run in reflexive code").toString());
            }
            if (this.scala$reflect$internal$Symbols$Symbol$$$outer().traceSymbolActivity()) {
                TraceSymbolActivity$class.recordNewSymbolOwner(this.scala$reflect$internal$Symbols$Symbol$$$outer().traceSymbols(), this, owner2);
            }
            this._rawowner = owner2;
        }

        public List<Symbol> ownerChain() {
            return this.owner().ownerChain().$colon$colon(this);
        }

        public List<Symbol> enclClassChain() {
            return this.owner().enclClassChain();
        }

        public Iterator<Symbol> ownersIterator() {
            return new Iterator<Symbol>(this){
                private Symbol current;
                private final /* synthetic */ Symbol $outer;

                public Iterator<Symbol> seq() {
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

                public Iterator<Symbol> take(int n) {
                    return Iterator$class.take(this, n);
                }

                public Iterator<Symbol> drop(int n) {
                    return Iterator$class.drop(this, n);
                }

                public Iterator<Symbol> slice(int from2, int until2) {
                    return Iterator$class.slice(this, from2, until2);
                }

                public <B> Iterator<B> map(Function1<Symbol, B> f) {
                    return Iterator$class.map(this, f);
                }

                public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
                    return Iterator$class.$plus$plus(this, that);
                }

                public <B> Iterator<B> flatMap(Function1<Symbol, GenTraversableOnce<B>> f) {
                    return Iterator$class.flatMap(this, f);
                }

                public Iterator<Symbol> filter(Function1<Symbol, Object> p) {
                    return Iterator$class.filter(this, p);
                }

                public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<Symbol, B, Object> p) {
                    return Iterator$class.corresponds(this, that, p);
                }

                public Iterator<Symbol> withFilter(Function1<Symbol, Object> p) {
                    return Iterator$class.withFilter(this, p);
                }

                public Iterator<Symbol> filterNot(Function1<Symbol, Object> p) {
                    return Iterator$class.filterNot(this, p);
                }

                public <B> Iterator<B> collect(PartialFunction<Symbol, B> pf) {
                    return Iterator$class.collect(this, pf);
                }

                public <B> Iterator<B> scanLeft(B z, Function2<B, Symbol, B> op) {
                    return Iterator$class.scanLeft(this, z, op);
                }

                public <B> Iterator<B> scanRight(B z, Function2<Symbol, B, B> op) {
                    return Iterator$class.scanRight(this, z, op);
                }

                public Iterator<Symbol> takeWhile(Function1<Symbol, Object> p) {
                    return Iterator$class.takeWhile(this, p);
                }

                public Tuple2<Iterator<Symbol>, Iterator<Symbol>> partition(Function1<Symbol, Object> p) {
                    return Iterator$class.partition(this, p);
                }

                public Tuple2<Iterator<Symbol>, Iterator<Symbol>> span(Function1<Symbol, Object> p) {
                    return Iterator$class.span(this, p);
                }

                public Iterator<Symbol> dropWhile(Function1<Symbol, Object> p) {
                    return Iterator$class.dropWhile(this, p);
                }

                public <B> Iterator<Tuple2<Symbol, B>> zip(Iterator<B> that) {
                    return Iterator$class.zip(this, that);
                }

                public <A1> Iterator<A1> padTo(int len, A1 elem) {
                    return Iterator$class.padTo(this, len, elem);
                }

                public Iterator<Tuple2<Symbol, Object>> zipWithIndex() {
                    return Iterator$class.zipWithIndex(this);
                }

                public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
                    return Iterator$class.zipAll(this, that, thisElem, thatElem);
                }

                public <U> void foreach(Function1<Symbol, U> f) {
                    Iterator$class.foreach(this, f);
                }

                public boolean forall(Function1<Symbol, Object> p) {
                    return Iterator$class.forall(this, p);
                }

                public boolean exists(Function1<Symbol, Object> p) {
                    return Iterator$class.exists(this, p);
                }

                public boolean contains(Object elem) {
                    return Iterator$class.contains(this, elem);
                }

                public Option<Symbol> find(Function1<Symbol, Object> p) {
                    return Iterator$class.find(this, p);
                }

                public int indexWhere(Function1<Symbol, Object> p) {
                    return Iterator$class.indexWhere(this, p);
                }

                public <B> int indexOf(B elem) {
                    return Iterator$class.indexOf(this, elem);
                }

                public BufferedIterator<Symbol> buffered() {
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

                public Tuple2<Iterator<Symbol>, Iterator<Symbol>> duplicate() {
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

                public Traversable<Symbol> toTraversable() {
                    return Iterator$class.toTraversable(this);
                }

                public Iterator<Symbol> toIterator() {
                    return Iterator$class.toIterator(this);
                }

                public Stream<Symbol> toStream() {
                    return Iterator$class.toStream(this);
                }

                public String toString() {
                    return Iterator$class.toString(this);
                }

                public <B> int sliding$default$2() {
                    return Iterator$class.sliding$default$2(this);
                }

                public List<Symbol> reversed() {
                    return TraversableOnce$class.reversed(this);
                }

                public int size() {
                    return TraversableOnce$class.size(this);
                }

                public boolean nonEmpty() {
                    return TraversableOnce$class.nonEmpty(this);
                }

                public int count(Function1<Symbol, Object> p) {
                    return TraversableOnce$class.count(this, p);
                }

                public <B> Option<B> collectFirst(PartialFunction<Symbol, B> pf) {
                    return TraversableOnce$class.collectFirst(this, pf);
                }

                public <B> B $div$colon(B z, Function2<B, Symbol, B> op) {
                    return (B)TraversableOnce$class.$div$colon(this, z, op);
                }

                public <B> B $colon$bslash(B z, Function2<Symbol, B, B> op) {
                    return (B)TraversableOnce$class.$colon$bslash(this, z, op);
                }

                public <B> B foldLeft(B z, Function2<B, Symbol, B> op) {
                    return (B)TraversableOnce$class.foldLeft(this, z, op);
                }

                public <B> B foldRight(B z, Function2<Symbol, B, B> op) {
                    return (B)TraversableOnce$class.foldRight(this, z, op);
                }

                public <B> B reduceLeft(Function2<B, Symbol, B> op) {
                    return (B)TraversableOnce$class.reduceLeft(this, op);
                }

                public <B> B reduceRight(Function2<Symbol, B, B> op) {
                    return (B)TraversableOnce$class.reduceRight(this, op);
                }

                public <B> Option<B> reduceLeftOption(Function2<B, Symbol, B> op) {
                    return TraversableOnce$class.reduceLeftOption(this, op);
                }

                public <B> Option<B> reduceRightOption(Function2<Symbol, B, B> op) {
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

                public <B> B aggregate(Function0<B> z, Function2<B, Symbol, B> seqop, Function2<B, B, B> combop) {
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

                public List<Symbol> toList() {
                    return TraversableOnce$class.toList(this);
                }

                public Iterable<Symbol> toIterable() {
                    return TraversableOnce$class.toIterable(this);
                }

                public Seq<Symbol> toSeq() {
                    return TraversableOnce$class.toSeq(this);
                }

                public IndexedSeq<Symbol> toIndexedSeq() {
                    return TraversableOnce$class.toIndexedSeq(this);
                }

                public <B> Buffer<B> toBuffer() {
                    return TraversableOnce$class.toBuffer(this);
                }

                public <B> Set<B> toSet() {
                    return TraversableOnce$class.toSet(this);
                }

                public Vector<Symbol> toVector() {
                    return TraversableOnce$class.toVector(this);
                }

                public <Col> Col to(CanBuildFrom<Nothing$, Symbol, Col> cbf) {
                    return (Col)TraversableOnce$class.to(this, cbf);
                }

                public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Symbol, Tuple2<T, U>> ev) {
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

                private Symbol current() {
                    return this.current;
                }

                private void current_$eq(Symbol x$1) {
                    this.current = x$1;
                }

                public boolean hasNext() {
                    return this.current() != this.$outer.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                }

                /*
                 * WARNING - void declaration
                 */
                public Symbol next() {
                    void var1_1;
                    Symbol r = this.current();
                    this.current_$eq(this.current().owner());
                    return var1_1;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    TraversableOnce$class.$init$(this);
                    Iterator$class.$init$(this);
                    this.current = $outer;
                }
            };
        }

        public boolean hasTransOwner(Symbol sym) {
            Symbol o;
            for (o = this; o != sym && o != this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol(); o = o.owner()) {
            }
            return o == sym;
        }

        public Names.Name originalName() {
            return this.unexpandedName();
        }

        public Names.Name unexpandedName() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().unexpandedName(this.name());
        }

        public String encodedName() {
            return this.name().toString();
        }

        public String decodedName() {
            return this.name().decode();
        }

        private Names.Name addModuleSuffix(Names.Name n) {
            return this.needsModuleSuffix() ? n.append(this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().MODULE_SUFFIX_STRING()) : n;
        }

        public String moduleSuffix() {
            return this.needsModuleSuffix() ? this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().MODULE_SUFFIX_STRING() : "";
        }

        public boolean needsModuleSuffix() {
            return this.hasModuleFlag() && !this.isMethod() && !this.isImplClass() && !this.isJavaDefined();
        }

        public Names.Name javaSimpleName() {
            return this.addModuleSuffix(this.scala$reflect$internal$Symbols$Symbol$$$outer().AnyNameOps(this.simpleName()).dropLocal());
        }

        public Names.Name javaBinaryName() {
            return this.name().newName(this.javaBinaryNameString());
        }

        public String javaBinaryNameString() {
            return this.fullName('/', this.moduleSuffix());
        }

        public String javaClassName() {
            return this.fullName('.', this.moduleSuffix());
        }

        public final String fullName(char separator) {
            return this.fullName(separator, "");
        }

        private String fullName(char separator, CharSequence suffix) {
            ObjectRef<Object> b = ObjectRef.create(null);
            this.loop$2(suffix.length(), this, separator, b);
            ((StringBuffer)b.elem).append(suffix);
            return ((StringBuffer)b.elem).toString();
        }

        public Names.Name fullNameAsName(char separator) {
            return this.name().newName(this.fullName(separator, ""));
        }

        @Override
        public final String fullName() {
            return this.fullName('.');
        }

        public AbstractTypeSymbol createAbstractTypeSymbol(Names.TypeName name, Position pos, long newFlags) {
            return (AbstractTypeSymbol)new AbstractTypeSymbol(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this, pos, name).initFlags(newFlags);
        }

        public AliasTypeSymbol createAliasTypeSymbol(Names.TypeName name, Position pos, long newFlags) {
            return (AliasTypeSymbol)new AliasTypeSymbol(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this, pos, name).initFlags(newFlags);
        }

        public TypeSkolem createTypeSkolemSymbol(Names.TypeName name, Object origin, Position pos, long newFlags) {
            return (TypeSkolem)new TypeSkolem(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this, pos, name, origin).initFlags(newFlags);
        }

        public ClassSymbol createClassSymbol(Names.TypeName name, Position pos, long newFlags) {
            return (ClassSymbol)new ClassSymbol(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this, pos, name).initFlags(newFlags);
        }

        public ModuleClassSymbol createModuleClassSymbol(Names.TypeName name, Position pos, long newFlags) {
            return (ModuleClassSymbol)new ModuleClassSymbol(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this, pos, name).initFlags(newFlags);
        }

        public PackageClassSymbol createPackageClassSymbol(Names.TypeName name, Position pos, long newFlags) {
            return (PackageClassSymbol)new PackageClassSymbol(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this, pos, name).initFlags(newFlags);
        }

        public RefinementClassSymbol createRefinementClassSymbol(Position pos, long newFlags) {
            return (RefinementClassSymbol)new RefinementClassSymbol(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this, pos).initFlags(newFlags);
        }

        public PackageObjectClassSymbol createPackageObjectClassSymbol(Position pos, long newFlags) {
            return (PackageObjectClassSymbol)new PackageObjectClassSymbol(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this, pos).initFlags(newFlags);
        }

        public ClassSymbol createImplClassSymbol(Names.TypeName name, Position pos, long newFlags) {
            return (ClassSymbol)((Symbol)((Object)new ImplClassSymbol(this, name, pos){
                private final /* synthetic */ Symbol $outer;

                public Symbol sourceModule() {
                    return Symbols$ImplClassSymbol$class.sourceModule(this);
                }

                public Types.Type typeOfThis() {
                    return Symbols$ImplClassSymbol$class.typeOfThis(this);
                }

                public /* synthetic */ Symbols scala$reflect$internal$Symbols$ImplClassSymbol$$$outer() {
                    return this.$outer.scala$reflect$internal$Symbols$Symbol$$$outer();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    super($outer.scala$reflect$internal$Symbols$Symbol$$$outer(), $outer, pos$1, name$1);
                    Symbols$ImplClassSymbol$class.$init$(this);
                }
            })).initFlags(newFlags);
        }

        public MethodSymbol createMethodSymbol(Names.TermName name, Position pos, long newFlags) {
            return (MethodSymbol)new MethodSymbol(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this, pos, name).initFlags(newFlags);
        }

        public ModuleSymbol createModuleSymbol(Names.TermName name, Position pos, long newFlags) {
            return (ModuleSymbol)new ModuleSymbol(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this, pos, name).initFlags(newFlags);
        }

        public ModuleSymbol createPackageSymbol(Names.TermName name, Position pos, long newFlags) {
            return (ModuleSymbol)new ModuleSymbol(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this, pos, name).initFlags(newFlags);
        }

        public TermSymbol createValueParameterSymbol(Names.TermName name, Position pos, long newFlags) {
            return (TermSymbol)new TermSymbol(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this, pos, name).initFlags(newFlags);
        }

        public TermSymbol createValueMemberSymbol(Names.TermName name, Position pos, long newFlags) {
            return (TermSymbol)new TermSymbol(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this, pos, name).initFlags(newFlags);
        }

        public final TermSymbol newTermSymbol(Names.TermName name, Position pos, long newFlags) {
            return (newFlags & 0x4000L) != 0L ? this.createPackageSymbol(name, pos, newFlags | 0x104120L) : ((newFlags & 0x100L) != 0L ? this.createModuleSymbol(name, pos, newFlags) : ((newFlags & 0x40L) != 0L ? this.createMethodSymbol(name, pos, newFlags) : ((newFlags & 0x2000L) != 0L ? this.createValueParameterSymbol(name, pos, newFlags) : this.createValueMemberSymbol(name, pos, newFlags))));
        }

        public final Position newTermSymbol$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newTermSymbol$default$3() {
            return 0L;
        }

        public final ClassSymbol newClassSymbol(Names.TypeName name, Position pos, long newFlags) {
            ClassSymbol classSymbol;
            Names.TypeName typeName = name;
            Names.TypeName typeName2 = this.scala$reflect$internal$Symbols$Symbol$$$outer().tpnme().REFINE_CLASS_NAME();
            if (!(typeName != null ? !typeName.equals(typeName2) : typeName2 != null)) {
                classSymbol = this.createRefinementClassSymbol(pos, newFlags);
            } else if ((newFlags & 0x4000L) != 0L) {
                classSymbol = this.createPackageClassSymbol(name, pos, newFlags | 0x104120L);
            } else {
                Names.TypeName typeName3 = name;
                Names.Name name2 = this.scala$reflect$internal$Symbols$Symbol$$$outer().tpnme().PACKAGE();
                classSymbol = !(typeName3 != null ? !typeName3.equals(name2) : name2 != null) ? this.createPackageObjectClassSymbol(pos, newFlags) : ((newFlags & 0x100L) != 0L ? this.createModuleClassSymbol(name, pos, newFlags) : ((newFlags & 0x2000000000L) != 0L ? this.createImplClassSymbol(name, pos, newFlags) : this.createClassSymbol(name, pos, newFlags)));
            }
            return classSymbol;
        }

        public final Position newClassSymbol$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newClassSymbol$default$3() {
            return 0L;
        }

        public final TypeSymbol newNonClassSymbol(Names.TypeName name, Position pos, long newFlags) {
            return (newFlags & 0x10L) != 0L ? this.createAbstractTypeSymbol(name, pos, newFlags) : this.createAliasTypeSymbol(name, pos, newFlags);
        }

        public final Position newNonClassSymbol$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public final long newNonClassSymbol$default$3() {
            return 0L;
        }

        public TypeSymbol newTypeSymbol(Names.TypeName name, Position pos, long newFlags) {
            return this.newNonClassSymbol(name, pos, newFlags);
        }

        public Position newTypeSymbol$default$2() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        }

        public long newTypeSymbol$default$3() {
            return 0L;
        }

        public Symbol accessBoundary(Symbol base) {
            return this.hasFlag(4) || this.isLocalToBlock() ? this.owner() : (this.hasAllFlags(0x900001L) ? this.enclosingRootClass() : (this.hasAccessBoundary() && !this.scala$reflect$internal$Symbols$Symbol$$$outer().phase().erasedTypes() ? this.privateWithin() : (this.hasFlag(1) ? base : this.enclosingRootClass())));
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean isLessAccessibleThan(Symbol other) {
            Symbol tb = this.accessBoundary(this.owner());
            Symbol ob1 = other.accessBoundary(this.owner());
            Symbol ob2 = ob1.linkedClassOfClass();
            Symbol o = tb;
            while (true) {
                block8: {
                    block7: {
                        Symbol symbol = o;
                        NoSymbol noSymbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                        if (!(symbol == null ? noSymbol != null : !symbol.equals(noSymbol))) break block7;
                        Symbol symbol2 = o;
                        if (!(symbol2 == null ? ob1 != null : !symbol2.equals(ob1))) break block7;
                        Symbol symbol3 = o;
                        if (symbol3 != null ? !symbol3.equals(ob2) : ob2 != null) break block8;
                    }
                    Symbol symbol = o;
                    NoSymbol noSymbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                    if (symbol == null) {
                        if (noSymbol == null) return false;
                    } else if (symbol.equals(noSymbol)) return false;
                    Symbol symbol4 = o;
                    if (symbol4 != null) {
                        if (!symbol4.equals(tb)) return true;
                        return false;
                    }
                    if (tb == null) return false;
                    return true;
                }
                o = o.owner();
            }
        }

        @Override
        public Symbol privateWithin() {
            Object object = this.scala$reflect$internal$Symbols$Symbol$$$outer().isCompilerUniverse() || this.isThreadsafe(this.scala$reflect$internal$Symbols$Symbol$$$outer().AllOps()) ? BoxedUnit.UNIT : this.initialize();
            return this._privateWithin;
        }

        public void privateWithin_$eq(Symbol sym) {
            this._privateWithin = sym;
        }

        public Symbol setPrivateWithin(Symbol sym) {
            this.privateWithin_$eq(sym);
            return this;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public final boolean hasAccessBoundary() {
            if (this.privateWithin() == null) return false;
            Symbol symbol = this.privateWithin();
            NoSymbol noSymbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
            if (symbol != null) {
                if (!symbol.equals(noSymbol)) return true;
                return false;
            }
            if (noSymbol == null) return false;
            return true;
        }

        public TypeHistory scala$reflect$internal$Symbols$$infos() {
            return this.scala$reflect$internal$Symbols$$infos;
        }

        public void scala$reflect$internal$Symbols$$infos_$eq(TypeHistory x$1) {
            this.scala$reflect$internal$Symbols$$infos = x$1;
        }

        public Types.Type originalInfo() {
            Types.Type type;
            if (this.scala$reflect$internal$Symbols$$infos() == null) {
                type = null;
            } else {
                TypeHistory is = this.scala$reflect$internal$Symbols$$infos();
                while (is.prev() != null) {
                    is = is.prev();
                }
                type = is.info();
            }
            return type;
        }

        public final Types.Type tpe() {
            return this.tpe_$times();
        }

        public Types.Type tpeHK() {
            return this.info();
        }

        public Types.Type typeConstructor() {
            if (this == this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                throw this.scala$reflect$internal$Symbols$Symbol$$$outer().abort("no-symbol does not have a type constructor (this may indicate scalac cannot find fundamental classes)");
            }
            throw this.scala$reflect$internal$Symbols$Symbol$$$outer().abort(new StringBuilder().append((Object)"typeConstructor inapplicable for ").append(this).toString());
        }

        public Types.Type tpe_$times() {
            return this.info();
        }

        @Override
        public Types.Type info() {
            try {
                Phase current;
                int cnt = 0;
                while (this.validTo() == 0) {
                    java.io.Serializable serializable;
                    boolean bl = this.scala$reflect$internal$Symbols$$infos() != null;
                    Predef$ predef$ = Predef$.MODULE$;
                    if (!bl) {
                        throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(this.name()).toString());
                    }
                    boolean bl2 = this.scala$reflect$internal$Symbols$$infos().prev() == null;
                    Predef$ predef$2 = Predef$.MODULE$;
                    if (!bl2) {
                        throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(this.name()).toString());
                    }
                    Types.Type tp = this.scala$reflect$internal$Symbols$$infos().info();
                    if ((this._rawflags & 0x8000000000L) != 0L) {
                        serializable = BoxesRunTime.boxToBoolean(this.lock((Function0<BoxedUnit>)((Object)new Serializable(this, tp){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ Symbol $outer;
                            private final Types.Type tp$3;

                            public final Nothing$ apply() {
                                this.$outer.setInfo(this.$outer.scala$reflect$internal$Symbols$Symbol$$$outer().ErrorType());
                                throw new CyclicReference(this.$outer.scala$reflect$internal$Symbols$Symbol$$$outer(), this.$outer, this.tp$3);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                                this.tp$3 = tp$3;
                            }
                        })));
                    } else {
                        this._rawflags |= 0x8000000000L;
                        serializable = BoxedUnit.UNIT;
                    }
                    current = this.scala$reflect$internal$Symbols$Symbol$$$outer().phase();
                    this.scala$reflect$internal$Symbols$Symbol$$$outer().assertCorrectThread();
                    int n = this.scala$reflect$internal$Symbols$$infos().validFrom();
                    SymbolTable symbolTable = this.scala$reflect$internal$Symbols$Symbol$$$outer();
                    this.scala$reflect$internal$Symbols$Symbol$$$outer().phase_$eq(symbolTable.phaseWithId()[n & 0xFF]);
                    tp.complete(this);
                    if (++cnt != 3) continue;
                    throw this.scala$reflect$internal$Symbols$Symbol$$$outer().abort(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"no progress in completing ", ": ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this, tp})));
                }
                return this.rawInfo();
                finally {
                    this.unlock();
                    this.scala$reflect$internal$Symbols$Symbol$$$outer().phase_$eq(current);
                }
            }
            catch (CyclicReference cyclicReference) {
                this.scala$reflect$internal$Symbols$Symbol$$$outer().devWarning((Function0<String>)((Object)new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Symbol $outer;

                    public final String apply() {
                        return new StringBuilder().append((Object)"... hit cycle trying to complete ").append((Object)this.$outer.fullLocationString()).toString();
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }));
                throw cyclicReference;
            }
        }

        public void info_$eq(Types.Type info2) {
            Predef$.MODULE$.assert(info2 != null);
            this.scala$reflect$internal$Symbols$$infos_$eq(new TypeHistory(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this.scala$reflect$internal$Symbols$Symbol$$$outer().currentPeriod(), info2, null));
            this.unlock();
            this._validTo = info2.isComplete() ? this.scala$reflect$internal$Symbols$Symbol$$$outer().currentPeriod() : 0;
        }

        public Symbol setInfo(Types.Type info2) {
            this.info_$eq(info2);
            return this;
        }

        public Symbol modifyInfo(Function1<Types.Type, Types.Type> f) {
            return this.setInfo(f.apply(this.info()));
        }

        public Symbol substInfo(List<Symbol> syms0, List<Symbol> syms1) {
            return syms0.isEmpty() ? this : this.modifyInfo((Function1<Types.Type, Types.Type>)((Object)new Serializable(this, syms0, syms1){
                public static final long serialVersionUID = 0L;
                private final List syms0$1;
                private final List syms1$1;

                public final Types.Type apply(Types.Type x$15) {
                    return x$15.substSym(this.syms0$1, this.syms1$1);
                }
                {
                    this.syms0$1 = syms0$1;
                    this.syms1$1 = syms1$1;
                }
            }));
        }

        public Symbol setInfoOwnerAdjusted(Types.Type info2) {
            return this.setInfo(info2.atOwner(this));
        }

        public Symbol setInfoAndEnter(Types.Type info2) {
            this.setInfo(info2);
            this.owner().info().decls().enter(this);
            return this;
        }

        public Symbol updateInfo(Types.Type info2) {
            this.scala$reflect$internal$Symbols$Symbol$$$outer();
            int pid = this.scala$reflect$internal$Symbols$$infos().validFrom() & 0xFF;
            boolean bl = pid <= this.scala$reflect$internal$Symbols$Symbol$$$outer().phase().id();
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(new Tuple2$mcII$sp(pid, this.scala$reflect$internal$Symbols$Symbol$$$outer().phase().id())).toString());
            }
            if (pid == this.scala$reflect$internal$Symbols$Symbol$$$outer().phase().id()) {
                this.scala$reflect$internal$Symbols$$infos_$eq(this.scala$reflect$internal$Symbols$$infos().prev());
            }
            this.scala$reflect$internal$Symbols$$infos_$eq(new TypeHistory(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this.scala$reflect$internal$Symbols$Symbol$$$outer().currentPeriod(), info2, this.scala$reflect$internal$Symbols$$infos()));
            this._validTo = info2.isComplete() ? this.scala$reflect$internal$Symbols$Symbol$$$outer().currentPeriod() : 0;
            return this;
        }

        public boolean hasRawInfo() {
            return this.scala$reflect$internal$Symbols$$infos() != null;
        }

        public boolean hasCompleteInfo() {
            return this.hasRawInfo() && this.rawInfo().isComplete();
        }

        public final boolean rawInfoIsNoType() {
            return this.hasRawInfo() && this.scala$reflect$internal$Symbols$$infos().info() == this.scala$reflect$internal$Symbols$Symbol$$$outer().NoType();
        }

        /*
         * WARNING - void declaration
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public Types.Type rawInfo() {
            TypeHistory infos = this.scala$reflect$internal$Symbols$$infos();
            Predef$.MODULE$.assert(infos != null);
            int curPeriod = this.scala$reflect$internal$Symbols$Symbol$$$outer().currentPeriod();
            int curPid = this.scala$reflect$internal$Symbols$Symbol$$$outer().phaseId(curPeriod);
            if (this.validTo() == 0) return infos.info();
            while (curPid < this.scala$reflect$internal$Symbols$Symbol$$$outer().phaseId(infos.validFrom()) && infos.prev() != null) {
                infos = infos.prev();
            }
            if (this.validTo() >= curPeriod) return infos.info();
            this.scala$reflect$internal$Symbols$Symbol$$$outer().assertCorrectThread();
            Phase current = this.scala$reflect$internal$Symbols$Symbol$$$outer().phase();
            try {
                infos = this.adaptInfos(infos);
                if (this.validTo() < curPeriod) {
                    InfoTransformers.InfoTransformer itr = this.scala$reflect$internal$Symbols$Symbol$$$outer().infoTransformers().nextFrom(this.scala$reflect$internal$Symbols$Symbol$$$outer().phaseId(this.validTo()));
                    this.scala$reflect$internal$Symbols$Symbol$$$outer().infoTransformers_$eq(itr);
                    while (itr.pid() != NoPhase$.MODULE$.id() && itr.pid() < current.id()) {
                        this.scala$reflect$internal$Symbols$Symbol$$$outer().phase_$eq(this.scala$reflect$internal$Symbols$Symbol$$$outer().phaseWithId()[itr.pid()]);
                        Types.Type info1 = itr.transform(this, infos.info());
                        if (info1 != infos.info()) {
                            infos = new TypeHistory(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this.scala$reflect$internal$Symbols$Symbol$$$outer().currentPeriod() + 1, info1, infos);
                            this.scala$reflect$internal$Symbols$$infos_$eq(infos);
                        }
                        this._validTo = this.scala$reflect$internal$Symbols$Symbol$$$outer().currentPeriod() + 1;
                        itr = itr.next();
                    }
                    this._validTo = itr.pid() == NoPhase$.MODULE$.id() ? curPeriod : this.scala$reflect$internal$Symbols$Symbol$$$outer().period(this.scala$reflect$internal$Symbols$Symbol$$$outer().currentRunId(), itr.pid());
                }
                this.scala$reflect$internal$Symbols$Symbol$$$outer().phase_$eq(current);
            }
            catch (Throwable throwable) {
                void var4_4;
                this.scala$reflect$internal$Symbols$Symbol$$$outer().phase_$eq((Phase)var4_4);
                throw throwable;
            }
            return infos.info();
        }

        private TypeHistory adaptInfos(TypeHistory infos) {
            TypeHistory typeHistory;
            block6: {
                while (true) {
                    Predef$.MODULE$.assert(this.scala$reflect$internal$Symbols$Symbol$$$outer().isCompilerUniverse());
                    if (infos == null || this.scala$reflect$internal$Symbols$Symbol$$$outer().runId(infos.validFrom()) == this.scala$reflect$internal$Symbols$Symbol$$$outer().currentRunId()) {
                        typeHistory = infos;
                        break block6;
                    }
                    if (infos == infos.oldest()) break;
                    infos = infos.oldest();
                }
                TypeHistory prev1 = this.adaptInfos(infos.prev());
                if (prev1 != infos.prev()) {
                    typeHistory = prev1;
                } else {
                    int pid = this.scala$reflect$internal$Symbols$Symbol$$$outer().phaseId(infos.validFrom());
                    this._validTo = this.scala$reflect$internal$Symbols$Symbol$$$outer().period(this.scala$reflect$internal$Symbols$Symbol$$$outer().currentRunId(), pid);
                    this.scala$reflect$internal$Symbols$Symbol$$$outer().phase_$eq(this.scala$reflect$internal$Symbols$Symbol$$$outer().phaseWithId()[pid]);
                    Types.Type info1 = this.scala$reflect$internal$Symbols$Symbol$$$outer().adaptToNewRunMap().apply(infos.info());
                    if (info1 == infos.info()) {
                        infos.validFrom_$eq(this.validTo());
                        typeHistory = infos;
                    } else {
                        this.scala$reflect$internal$Symbols$$infos_$eq(new TypeHistory(this.scala$reflect$internal$Symbols$Symbol$$$outer(), this.validTo(), info1, prev1));
                        typeHistory = this.scala$reflect$internal$Symbols$$infos();
                    }
                }
            }
            return typeHistory;
        }

        public void failIfStub() {
        }

        public final Symbol initialize() {
            Object object = this.isInitialized() ? BoxedUnit.UNIT : this.info();
            return this;
        }

        public boolean maybeInitialize() {
            boolean bl;
            try {
                this.initialize();
                bl = true;
            }
            catch (CyclicReference cyclicReference) {
                this.scala$reflect$internal$Symbols$Symbol$$$outer().debuglog((Function0<String>)((Object)new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final String apply() {
                        return "Hit cycle in maybeInitialize of $this";
                    }
                }));
                bl = false;
            }
            return bl;
        }

        public final boolean hasTypeAt(int pid) {
            TypeHistory infos;
            Predef$.MODULE$.assert(this.scala$reflect$internal$Symbols$Symbol$$$outer().isCompilerUniverse());
            for (infos = this.scala$reflect$internal$Symbols$$infos(); infos != null && this.scala$reflect$internal$Symbols$Symbol$$$outer().phaseId(infos.validFrom()) > pid; infos = infos.prev()) {
            }
            return infos != null;
        }

        public Symbol cookJavaRawInfo() {
            Object object;
            if (this.scala$reflect$internal$Symbols$Symbol$$$outer().phase().erasedTypes() || this.hasFlag(0x100000000000L)) {
                return this;
            }
            this.setFlag(0x100000000000L);
            this.info();
            if (this.isJavaDefined() || this.isType() && this.owner().isJavaDefined()) {
                object = this.modifyInfo(this.scala$reflect$internal$Symbols$Symbol$$$outer().rawToExistential());
            } else {
                if (this.isOverloaded()) {
                    this.alternatives().withFilter((Function1<Symbol, Object>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final boolean apply(Symbol x$16) {
                            return x$16.isJavaDefined();
                        }
                    })).foreach(new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Symbol $outer;

                        public final Symbol apply(Symbol x$17) {
                            return x$17.modifyInfo(this.$outer.scala$reflect$internal$Symbols$Symbol$$$outer().rawToExistential());
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    });
                }
                object = BoxedUnit.UNIT;
            }
            return this;
        }

        /*
         * WARNING - void declaration
         */
        private Phase unsafeTypeParamPhase() {
            void var1_1;
            Phase ph = this.scala$reflect$internal$Symbols$Symbol$$$outer().phase();
            while (ph.prev().keepsTypeParams()) {
                ph = ph.prev();
            }
            return var1_1;
        }

        /*
         * WARNING - void declaration
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public List<Symbol> unsafeTypeParams() {
            List<Symbol> list2;
            List list3;
            if (this.isMonomorphicType()) {
                list3 = Nil$.MODULE$;
                return list3;
            }
            Phase phase = this.unsafeTypeParamPhase();
            SymbolTable symbolTable = this.scala$reflect$internal$Symbols$Symbol$$$outer();
            Phase saved1 = symbolTable.pushPhase(phase);
            try {
                list2 = this.rawInfo().typeParams();
                symbolTable.popPhase(saved1);
            }
            catch (Throwable throwable) {
                void var3_3;
                symbolTable.popPhase((Phase)var3_3);
                throw throwable;
            }
            list3 = list2;
            return list3;
        }

        /*
         * Loose catch block
         * WARNING - void declaration
         */
        public List<Symbol> typeParams() {
            SymbolTable symbolTable;
            SymbolTable symbolTable2;
            List list2;
            if (this.isMonomorphicType()) {
                list2 = Nil$.MODULE$;
            } else {
                BoxedUnit boxedUnit;
                BoxedUnit boxedUnit2;
                if (this.validTo() == 0) {
                    int n = this.scala$reflect$internal$Symbols$$infos().validFrom();
                    Phase phase = this.scala$reflect$internal$Symbols$Symbol$$$outer().phaseWithId()[n & 0xFF];
                    symbolTable2 = this.scala$reflect$internal$Symbols$Symbol$$$outer();
                    Phase saved1 = symbolTable2.pushPhase(phase);
                    this.rawInfo().load(this);
                    boxedUnit2 = BoxedUnit.UNIT;
                    symbolTable2.popPhase(saved1);
                }
                boxedUnit2 = BoxedUnit.UNIT;
                if (this.validTo() == 0) {
                    int n = this.scala$reflect$internal$Symbols$$infos().validFrom();
                    Phase phase = this.scala$reflect$internal$Symbols$Symbol$$$outer().phaseWithId()[n & 0xFF];
                    symbolTable = this.scala$reflect$internal$Symbols$Symbol$$$outer();
                    Phase saved2 = symbolTable.pushPhase(phase);
                    this.rawInfo().load(this);
                    boxedUnit = BoxedUnit.UNIT;
                    symbolTable.popPhase(saved2);
                } else {
                    boxedUnit = BoxedUnit.UNIT;
                }
                list2 = this.rawInfo().typeParams();
            }
            return list2;
            catch (Throwable throwable) {
                void var8_4;
                symbolTable2.popPhase((Phase)var8_4);
                throw throwable;
            }
            catch (Throwable throwable) {
                void var11_8;
                symbolTable.popPhase((Phase)var11_8);
                throw throwable;
            }
        }

        public List<List<Symbol>> paramss() {
            return this.info().paramss();
        }

        public Types.Type classBound() {
            Types.Type tp = this.scala$reflect$internal$Symbols$Symbol$$$outer().refinedType(this.info().parents(), this.owner());
            if (!this.scala$reflect$internal$Symbols$Symbol$$$outer().phase().erasedTypes()) {
                Types.Type thistp = tp.typeSymbol().thisType();
                ListBuffer oldsymbuf = new ListBuffer();
                ListBuffer newsymbuf = new ListBuffer();
                this.info().decls().foreach(new Serializable(this, tp, oldsymbuf, newsymbuf){
                    public static final long serialVersionUID = 0L;
                    private final Types.Type tp$4;
                    private final ListBuffer oldsymbuf$1;
                    private final ListBuffer newsymbuf$1;

                    public final Object apply(Symbol sym) {
                        Object object;
                        if (sym.isPublic() && !sym.isConstructor()) {
                            Symbol symbol;
                            this.oldsymbuf$1.$plus$eq(sym);
                            if (sym.isClass()) {
                                Symbol qual$2 = this.tp$4.typeSymbol();
                                Names.TypeName x$59 = sym.name().toTypeName();
                                Position x$60 = sym.pos();
                                long x$61 = qual$2.newAbstractType$default$3();
                                symbol = qual$2.newAbstractType(x$59, x$60, x$61).setInfo(sym.existentialBound());
                            } else {
                                symbol = sym.cloneSymbol(this.tp$4.typeSymbol());
                            }
                            object = this.newsymbuf$1.$plus$eq(symbol);
                        } else {
                            object = BoxedUnit.UNIT;
                        }
                        return object;
                    }
                    {
                        this.tp$4 = tp$4;
                        this.oldsymbuf$1 = oldsymbuf$1;
                        this.newsymbuf$1 = newsymbuf$1;
                    }
                });
                List oldsyms = oldsymbuf.toList();
                List newsyms = newsymbuf.toList();
                Serializable serializable = new Serializable(this, tp, thistp, oldsyms, newsyms){
                    public static final long serialVersionUID = 0L;
                    public final /* synthetic */ Symbol $outer;
                    public final Types.Type tp$4;
                    public final Types.Type thistp$1;
                    public final List oldsyms$1;
                    public final List newsyms$1;

                    public final void apply(Symbol sym) {
                        this.$outer.scala$reflect$internal$Symbols$Symbol$$$outer().addMember(this.thistp$1, this.tp$4, sym.modifyInfo((Function1<Types.Type, Types.Type>)((Object)new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ Symbol$$anonfun$classBound$2 $outer;

                            public final Types.Type apply(Types.Type x$18) {
                                return x$18.substThisAndSym(this.$outer.$outer, this.$outer.thistp$1, this.$outer.oldsyms$1, this.$outer.newsyms$1);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        })));
                    }

                    public /* synthetic */ Symbol scala$reflect$internal$Symbols$Symbol$$anonfun$$$outer() {
                        return this.$outer;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.tp$4 = tp$4;
                        this.thistp$1 = thistp$1;
                        this.oldsyms$1 = oldsyms$1;
                        this.newsyms$1 = newsyms$1;
                    }
                };
                List list2 = newsyms;
                while (!((AbstractIterable)list2).isEmpty()) {
                    Symbol symbol = (Symbol)((AbstractIterable)list2).head();
                    this.scala$reflect$internal$Symbols$Symbol$$$outer().addMember(thistp, tp, symbol.modifyInfo((Function1<Types.Type, Types.Type>)((Object)new /* invalid duplicate definition of identical inner class */)));
                    list2 = (List)list2.tail();
                }
            }
            return tp;
        }

        public abstract Types.Type existentialBound();

        public Symbol reset(Types.Type completer) {
            this.resetFlags();
            this.scala$reflect$internal$Symbols$$infos_$eq(null);
            this._validTo = 0;
            return this.setInfo(completer);
        }

        public void makeSerializable() {
            Types.Type type = this.info();
            if (type instanceof Types.ClassInfoType) {
                Types.ClassInfoType classInfoType = (Types.ClassInfoType)type;
                this.setInfo(classInfoType.copy(classInfoType.parents().$colon$plus(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().SerializableTpe(), List$.MODULE$.canBuildFrom()), classInfoType.copy$default$2(), classInfoType.copy$default$3()));
                return;
            }
            throw this.scala$reflect$internal$Symbols$Symbol$$$outer().abort(new StringBuilder().append((Object)"Only ClassInfoTypes can be made serializable: ").append(type).toString());
        }

        public void typeOfThis_$eq(Types.Type tp) {
            throw new UnsupportedOperationException(new StringBuilder().append((Object)"typeOfThis_= inapplicable for ").append(this).toString());
        }

        public void sourceModule_$eq(Symbol sym) {
            throw new UnsupportedOperationException(new StringBuilder().append((Object)"sourceModule_= inapplicable for ").append(this).toString());
        }

        public void addChild(Symbol sym) {
            throw new UnsupportedOperationException(new StringBuilder().append((Object)"addChild inapplicable for ").append(this).toString());
        }

        public String annotationsString() {
            return this.annotations().isEmpty() ? "" : this.annotations().mkString("(", ", ", ")");
        }

        @Override
        public List<AnnotationInfos.AnnotationInfo> annotations() {
            Object object = this.scala$reflect$internal$Symbols$Symbol$$$outer().isCompilerUniverse() || this.isThreadsafe(this.scala$reflect$internal$Symbols$Symbol$$$outer().AllOps()) ? BoxedUnit.UNIT : this.initialize();
            return this._annotations;
        }

        @Override
        public Symbol setAnnotations(List<AnnotationInfos.AnnotationInfo> annots) {
            this._annotations = annots;
            return this;
        }

        @Override
        public Symbol withAnnotations(List<AnnotationInfos.AnnotationInfo> annots) {
            return this.setAnnotations((List)this.annotations().$colon$colon$colon(annots));
        }

        @Override
        public Symbol withoutAnnotations() {
            return this.setAnnotations((List)Nil$.MODULE$);
        }

        @Override
        public Symbol filterAnnotations(Function1<AnnotationInfos.AnnotationInfo, Object> p) {
            return this.setAnnotations((List)this.annotations().filter((Function1)p));
        }

        public Symbol addAnnotation(AnnotationInfos.AnnotationInfo annot) {
            return this.setAnnotations((List)this.annotations().$colon$colon(annot));
        }

        public Symbol addAnnotation(Symbol sym, Seq<Trees.Tree> args) {
            return this.addAnnotation(this.scala$reflect$internal$Symbols$Symbol$$$outer().AnnotationInfo().apply(sym.tpe(), args.toList(), Nil$.MODULE$));
        }

        public Symbol addAnnotation(Types.Type tp, Seq<Trees.Tree> args) {
            boolean bl = tp.typeParams().isEmpty();
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(tp).toString());
            }
            Nil$ nil$ = Nil$.MODULE$;
            List<Trees.Tree> list2 = args.toList();
            AnnotationInfos$AnnotationInfo$ annotationInfos$AnnotationInfo$ = this.scala$reflect$internal$Symbols$Symbol$$$outer().AnnotationInfo();
            return this.addAnnotation(new AnnotationInfos.CompleteAnnotationInfo(annotationInfos$AnnotationInfo$.$outer, tp, list2, nil$));
        }

        public final boolean isLess(Symbol that) {
            int diff2;
            return this.isType() ? that.isType() && ((diff2 = this.baseTypeSeqLength$1(this) - this.baseTypeSeqLength$1(that)) > 0 || diff2 == 0 && this.id() < that.id()) : that.isType() || this.id() < that.id();
        }

        public final boolean isNestedIn(Symbol that) {
            while (true) {
                block5: {
                    boolean bl;
                    block4: {
                        block3: {
                            Symbol symbol = this_.owner();
                            if (symbol != null ? !symbol.equals(that) : that != null) break block3;
                            bl = true;
                            break block4;
                        }
                        Symbol symbol = this_.owner();
                        NoSymbol noSymbol = this_.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                        if (symbol != null ? !symbol.equals(noSymbol) : noSymbol != null) break block5;
                        bl = false;
                    }
                    return bl;
                }
                Symbol this_ = this_.owner();
            }
        }

        public boolean isNonBottomSubClass(Symbol that) {
            return false;
        }

        public boolean isBottomSubClass(Symbol that) {
            return this == this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().NothingClass() || this == this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().NullClass() && that.isClass() && that != this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().NothingClass() && !that.isNonBottomSubClass(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().AnyValClass());
        }

        public boolean isSubClass(Symbol that) {
            return this.isNonBottomSubClass(that);
        }

        public final boolean isNumericSubClass(Symbol that) {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().isNumericSubClass(this, that);
        }

        public final boolean isWeakSubClass(Symbol that) {
            return this.isSubClass(that) || this.isNumericSubClass(that);
        }

        public List<Symbol> alternatives() {
            return this.isOverloaded() ? ((Types.OverloadedType)this.info()).alternatives() : Nil$.MODULE$.$colon$colon(this);
        }

        public Symbol filter(Function1<Symbol, Object> cond) {
            Symbol symbol;
            if (this.isOverloaded()) {
                boolean changed = false;
                List alts0 = this.alternatives();
                List alts1 = Nil$.MODULE$;
                while (alts0.nonEmpty()) {
                    if (BoxesRunTime.unboxToBoolean(cond.apply(alts0.head()))) {
                        alts1 = alts1.$colon$colon(alts0.head());
                    } else {
                        changed = true;
                    }
                    alts0 = (List)alts0.tail();
                }
                symbol = changed ? (alts1.isEmpty() ? this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() : (((SeqLike)alts1.tail()).isEmpty() ? (Symbol)alts1.head() : this.owner().newOverloaded(this.info().prefix(), (List<Symbol>)alts1.reverse()))) : this;
            } else {
                symbol = BoxesRunTime.unboxToBoolean(cond.apply(this)) ? this : this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
            }
            return symbol;
        }

        public Symbol suchThat(Function1<Symbol, Object> cond) {
            Symbols.SymbolApi result2 = this.filter((Function1)cond);
            boolean bl = !((Symbol)result2).isOverloaded();
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(((Symbol)result2).alternatives()).toString());
            }
            return result2;
        }

        public final Symbol cloneSymbol() {
            return this.cloneSymbol(this.owner());
        }

        public final Symbol cloneSymbol(Symbol newOwner) {
            return this.cloneSymbol(newOwner, this._rawflags);
        }

        public final Symbol cloneSymbol(Symbol newOwner, long newFlags) {
            return this.cloneSymbol(newOwner, newFlags, null);
        }

        public final Symbol cloneSymbol(Symbol newOwner, long newFlags, Names.Name newName) {
            Symbol clone2 = this.cloneSymbolImpl(newOwner, newFlags);
            clone2.setPrivateWithin(this.privateWithin()).setInfo(this.info().cloneInfo(clone2)).setAnnotations((List)this.annotations());
            this.attachments().all().foreach(new Serializable(this, clone2){
                public static final long serialVersionUID = 0L;
                private final Symbol clone$1;

                public final Symbol apply(Object attachment) {
                    return (Symbol)this.clone$1.updateAttachment(attachment, ClassTag$.MODULE$.Any());
                }
                {
                    this.clone$1 = clone$1;
                }
            });
            Symbol symbol = clone2.thisSym();
            if (symbol == null ? clone2 != null : !symbol.equals(clone2)) {
                clone2.typeOfThis_$eq(clone2.typeOfThis().cloneInfo(clone2));
            }
            Object object = newName != null ? clone2.setName(this.asNameType(newName)) : BoxedUnit.UNIT;
            return clone2;
        }

        public abstract Symbol cloneSymbolImpl(Symbol var1, long var2);

        public Symbol enclClass() {
            return this.isClass() ? this : this.owner().enclClass();
        }

        public Symbol enclMethod() {
            return this.isSourceMethod() ? this : this.owner().enclMethod();
        }

        public Symbol primaryConstructor() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
        }

        public Symbol thisSym() {
            return this;
        }

        public boolean hasSelfType() {
            Types.Type type = this.thisSym().tpeHK();
            Types.Type type2 = this.tpeHK();
            return type != null ? !type.equals(type2) : type2 != null;
        }

        public Types.Type typeOfThis() {
            return this.thisSym().tpe_$times();
        }

        public Types.Type thisType() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoPrefix();
        }

        public final List<Symbol> caseFieldAccessors() {
            List primaryNames = this.constrParamAccessors().map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Symbol $outer;

                public final Names.TermName apply(Symbol x$22) {
                    return this.$outer.scala$reflect$internal$Symbols$Symbol$$$outer().AnyNameOps(x$22.name()).dropLocal();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom());
            return (List)this.caseFieldAccessorsUnsorted().sortBy((Function1)((Object)new Serializable(this, primaryNames){
                public static final long serialVersionUID = 0L;
                private final List primaryNames$1;

                public final int apply(Symbol acc) {
                    return this.primaryNames$1.indexWhere(new Serializable(this, acc){
                        public static final long serialVersionUID = 0L;
                        private final Symbol acc$1;

                        public final boolean apply(Names.TermName orig) {
                            Names.Name name = this.acc$1.name();
                            return !(name == null ? orig != null : !name.equals(orig)) || this.acc$1.name().startsWith(orig.append("$"));
                        }
                        {
                            this.acc$1 = acc$1;
                        }
                    });
                }
                {
                    this.primaryNames$1 = primaryNames$1;
                }
            }), (Ordering)Ordering$Int$.MODULE$);
        }

        private final List<Symbol> caseFieldAccessorsUnsorted() {
            return ((Scopes.Scope)this.info().decls().filter((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Symbol x$23) {
                    return x$23.isCaseAccessorMethod();
                }
            }))).toList();
        }

        public final List<Symbol> constrParamAccessors() {
            return ((Scopes.Scope)this.info().decls().filter((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Symbol sym) {
                    return !sym.isMethod() && sym.isParamAccessor();
                }
            }))).toList();
        }

        /*
         * WARNING - void declaration
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public final Symbol accessed() {
            Symbols.SymbolApi symbolApi;
            Symbol localField;
            boolean bl = this.hasAccessorFlag();
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(this).toString());
            }
            Symbol symbol = localField = this.owner().info().decl(this.localName());
            NoSymbol noSymbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
            if (!(symbol != null ? !symbol.equals(noSymbol) : noSymbol != null) && this.hasFlag(0x800000000L)) {
                Phase phase = this.scala$reflect$internal$Symbols$Symbol$$$outer().picklerPhase();
                SymbolTable symbolTable = this.scala$reflect$internal$Symbols$Symbol$$$outer();
                Phase saved1 = symbolTable.pushPhase(phase);
                try {
                    Types.Type type = this.owner().info();
                    symbolTable.popPhase(saved1);
                    symbolApi = type.decl(this.name()).suchThat((Function1)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final boolean apply(Symbol x$24) {
                            return !x$24.isAccessor();
                        }
                    }));
                    return symbolApi;
                }
                catch (Throwable throwable) {
                    void var7_7;
                    symbolTable.popPhase((Phase)var7_7);
                    throw throwable;
                }
            }
            symbolApi = localField;
            return symbolApi;
        }

        public Symbol sourceModule() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
        }

        public final Symbol implClass() {
            return this.owner().info().decl(this.scala$reflect$internal$Symbols$Symbol$$$outer().tpnme().implClassName(this.name()));
        }

        public final Symbol outerClass() {
            while (true) {
                Symbol this_;
                block7: {
                    Symbol symbol;
                    block6: {
                        block5: {
                            Symbol symbol2 = this_;
                            NoSymbol noSymbol = this_.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                            if (symbol2 != null ? !symbol2.equals(noSymbol) : noSymbol != null) break block5;
                            Serializable serializable = new Serializable(this_){
                                public static final long serialVersionUID = 0L;

                                public final String apply() {
                                    return "NoSymbol.outerClass";
                                }
                            };
                            SymbolTable symbolTable = this_.scala$reflect$internal$Symbols$Symbol$$$outer();
                            symbolTable.devWarning((Function0<String>)((Object)new Serializable(symbolTable, (Function0)((Object)serializable), 15){
                                public static final long serialVersionUID = 0L;
                                private final /* synthetic */ SymbolTable $outer;
                                private final Function0 msg$2;
                                private final int maxFrames$1;

                                public final String apply() {
                                    return new StringBuilder().append((Object)((String)this.msg$2.apply())).append((Object)"\n").append((Object)this.$outer.throwableAsString(new Throwable(), this.maxFrames$1)).toString();
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                    this.msg$2 = msg$2;
                                    this.maxFrames$1 = maxFrames$1;
                                }
                            }));
                            symbol = this_.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                            break block6;
                        }
                        if (!this_.owner().isClass()) break block7;
                        symbol = this_.owner();
                    }
                    return symbol;
                }
                if (this_.isClassLocalToConstructor()) {
                    this_ = this_.owner().enclClass();
                    continue;
                }
                this_ = this_.owner();
            }
        }

        public Symbol alias() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
        }

        public Symbol lazyAccessor() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
        }

        public Symbol lazyAccessorOrSelf() {
            return this.isLazy() ? this.lazyAccessor() : this;
        }

        public Symbol accessedOrSelf() {
            return this.hasAccessorFlag() ? this.accessed() : this;
        }

        public Symbol outerSource() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
        }

        public Symbol superClass() {
            return this.info().parents().isEmpty() ? this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() : this.info().parents().head().typeSymbol();
        }

        public List<Symbol> parentSymbols() {
            return this.info().parents().map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final Symbol apply(Types.Type x$25) {
                    return x$25.typeSymbol();
                }
            }, List$.MODULE$.canBuildFrom());
        }

        public List<Symbol> mixinClasses() {
            Symbol sc = this.superClass();
            List list2 = this.ancestors();
            ListBuffer listBuffer = new ListBuffer();
            List list3 = list2;
            Symbol symbol;
            while (!((AbstractIterable)list3).isEmpty() && sc != (symbol = (Symbol)((AbstractIterable)list3).head())) {
                listBuffer.$plus$eq(((AbstractIterable)list3).head());
                list3 = (List)list3.tail();
            }
            return listBuffer.toList();
        }

        public List<Symbol> ancestors() {
            return this.info().baseClasses().drop(1);
        }

        public final Symbol enclosingSuchThat(Function1<Symbol, Object> p) {
            Symbol sym = this;
            while (true) {
                Symbol symbol;
                Symbol symbol2 = sym;
                NoSymbol noSymbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                if (!(symbol2 == null ? noSymbol != null : !symbol2.equals(noSymbol)) || BoxesRunTime.unboxToBoolean(p.apply(sym))) {
                    return symbol;
                }
                symbol = symbol.owner();
            }
        }

        public Symbol enclosingPackageClass() {
            Symbol sym = this.owner();
            while (true) {
                Symbol symbol;
                Symbol symbol2 = sym;
                NoSymbol noSymbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                if (!(symbol2 == null ? noSymbol != null : !symbol2.equals(noSymbol)) || sym.isPackageClass()) {
                    return symbol;
                }
                symbol = symbol.owner();
            }
        }

        public Symbol enclosingRootClass() {
            Symbol sym1 = this;
            while (true) {
                Symbol symbol;
                Symbol symbol2 = sym1;
                NoSymbol noSymbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                if (!(symbol2 == null ? noSymbol != null : !symbol2.equals(noSymbol)) || sym1.isRoot()) {
                    return sym1;
                }
                symbol = symbol.owner();
            }
        }

        public Symbol enclosingPackage() {
            return this.enclosingPackageClass().companionModule();
        }

        public final Symbol logicallyEnclosingMember() {
            while (true) {
                block7: {
                    Symbol symbol;
                    block4: {
                        block6: {
                            block5: {
                                block3: {
                                    if (!this_.isLocalDummy()) break block3;
                                    symbol = this_.enclClass().primaryConstructor();
                                    break block4;
                                }
                                if (this_.isMethod() || this_.isClass()) break block5;
                                Symbol symbol2 = this_;
                                NoSymbol noSymbol = this_.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                                if (symbol2 != null ? !symbol2.equals(noSymbol) : noSymbol != null) break block6;
                            }
                            symbol = this_;
                            break block4;
                        }
                        Symbol symbol3 = this_;
                        NoSymbol noSymbol = this_.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                        if (symbol3 != null ? !symbol3.equals(noSymbol) : noSymbol != null) break block7;
                        Serializable serializable = new Serializable(this_){
                            public static final long serialVersionUID = 0L;

                            public final String apply() {
                                return "NoSymbol.logicallyEnclosingMember";
                            }
                        };
                        SymbolTable symbolTable = this_.scala$reflect$internal$Symbols$Symbol$$$outer();
                        symbolTable.devWarning((Function0<String>)((Object)new /* invalid duplicate definition of identical inner class */));
                        symbol = this_;
                    }
                    return symbol;
                }
                Symbol this_ = this_.owner();
            }
        }

        public Symbol enclosingTopLevelClass() {
            return this.isTopLevel() ? (this.isClass() ? this : this.moduleClass()) : this.owner().enclosingTopLevelClass();
        }

        public Symbol enclosingTopLevelClassOrDummy() {
            Symbol symbol;
            return this.owner().isPackageClass() ? (this.isClass() ? this : ((symbol = this.moduleClass()) != symbol.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? symbol : this)) : this.owner().enclosingTopLevelClassOrDummy();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean isCoDefinedWith(Symbol that) {
            if (this.rawInfoIsNoType()) return false;
            Symbol symbol = this.effectiveOwner();
            Symbol symbol2 = that.effectiveOwner();
            if (symbol == null) {
                if (symbol2 != null) {
                    return false;
                }
            } else if (!symbol.equals(symbol2)) return false;
            if (!this.effectiveOwner().isPackageClass()) return true;
            if (this.associatedFile() == NoAbstractFile$.MODULE$) return true;
            if (that.associatedFile() == NoAbstractFile$.MODULE$) return true;
            String string2 = this.associatedFile().path();
            String string3 = that.associatedFile().path();
            if (string2 == null) {
                if (string3 == null) return true;
            } else if (string2.equals(string3)) return true;
            String string4 = this.associatedFile().canonicalPath();
            String string5 = that.associatedFile().canonicalPath();
            if (string4 == null) {
                if (string5 == null) return true;
                return false;
            } else {
                if (!string4.equals(string5)) return false;
                return true;
            }
        }

        public Symbol companionClass() {
            return this.flatOwnerInfo().decl(this.name().toTypeName()).suchThat((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Symbol $outer;

                public final boolean apply(Symbol x$28) {
                    return x$28.isCoDefinedWith(this.$outer);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        public Symbol companionModule() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
        }

        @Override
        public Symbol companionSymbol() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
        }

        public Symbol linkedClassOfClass() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
        }

        public final Types.Type flatOwnerInfo() {
            Object object = this.needsFlatClasses() ? this.info() : BoxedUnit.UNIT;
            return this.owner().rawInfo();
        }

        public Symbol toInterface() {
            return this;
        }

        public Symbol moduleClass() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
        }

        public final Symbol matchingSymbol(Symbol ofclazz, Types.Type site) {
            return this.matchingSymbolInternal(site, ofclazz.info().nonPrivateDecl(this.name()));
        }

        public final Symbol matchingSymbol(Types.Type site, long admit) {
            return this.matchingSymbolInternal(site, site.nonPrivateMemberAdmitting(this.name(), admit));
        }

        public final long matchingSymbol$default$2() {
            return 0L;
        }

        private Symbol matchingSymbolInternal(Types.Type site, Symbol candidate) {
            return candidate.isOverloaded() ? candidate.filter((Function1)((Object)new Serializable(this, site){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Symbol $outer;
                private final Types.Type site$1;

                public final boolean apply(Symbol sym) {
                    return this.$outer.scala$reflect$internal$Symbols$Symbol$$qualifies$1(sym, this.site$1);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.site$1 = site$1;
                }
            })) : (this.scala$reflect$internal$Symbols$Symbol$$qualifies$1(candidate, site) ? candidate : this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol());
        }

        public final Symbol overriddenSymbol(Symbol baseClass) {
            return this.matchingInheritedSymbolIn(baseClass).filter((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Symbol $outer;

                public final boolean apply(Symbol res) {
                    return res.isDeferred() || !this.$outer.isDeferred();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        private Symbol matchingInheritedSymbolIn(Symbol baseClass) {
            return this.canMatchInheritedSymbols() ? this.matchingSymbol(baseClass, this.owner().thisType()) : this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
        }

        public final Symbol overridingSymbol(Symbol ofclazz) {
            return this.canMatchInheritedSymbols() ? this.matchingSymbol(ofclazz, ofclazz.thisType()) : this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
        }

        private boolean canMatchInheritedSymbols() {
            return this.owner().isClass() && !this.isClass() && !this.isConstructor();
        }

        public List<Symbol> overrideChain() {
            return this == this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? Nil$.MODULE$ : (this.isOverridingSymbol() ? this.allOverriddenSymbols().$colon$colon(this) : Nil$.MODULE$.$colon$colon(this));
        }

        public final List<Symbol> allOverriddenSymbols() {
            return this.isOverridingSymbol() ? this.loop$3(this.owner().ancestors()) : Nil$.MODULE$;
        }

        public boolean isOverridingSymbol() {
            return this.bitmap$0 ? this.isOverridingSymbol : this.isOverridingSymbol$lzycompute();
        }

        public Symbol nextOverriddenSymbol() {
            return this.isOverridingSymbol() ? this.loop$4(this.owner().ancestors()) : this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
        }

        public final List<Symbol> extendedOverriddenSymbols() {
            return this.canMatchInheritedSymbols() ? (List)((TraversableLike)this.owner().thisSym().ancestors().map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Symbol $outer;

                public final Symbol apply(Symbol baseClass) {
                    return this.$outer.overriddenSymbol(baseClass);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom())).filter(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Symbol $outer;

                public final boolean apply(Symbol x$32) {
                    Symbol symbol = x$32;
                    NoSymbol noSymbol = this.$outer.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                    return symbol != null ? !symbol.equals(noSymbol) : noSymbol != null;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }) : Nil$.MODULE$;
        }

        public final Symbol superSymbol(Symbol base) {
            return this.superSymbolIn(base);
        }

        /*
         * WARNING - void declaration
         */
        public final Symbol superSymbolIn(Symbol base) {
            void var3_3;
            Object bcs = ((List)base.info().baseClasses().dropWhile((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Symbol $outer;

                public final boolean apply(Symbol x$33) {
                    Symbol symbol = this.$outer.owner();
                    return symbol != null ? !symbol.equals(x$33) : x$33 != null;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }))).drop(1);
            Symbols.SymbolApi sym = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
            while (!((List)bcs).isEmpty()) {
                NoSymbol noSymbol = sym;
                NoSymbol noSymbol2 = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                if (noSymbol != null ? !noSymbol.equals(noSymbol2) : noSymbol2 != null) break;
                if (!((Symbol)((List)bcs).head()).isImplClass()) {
                    sym = this.matchingSymbol((Symbol)((List)bcs).head(), base.thisType()).suchThat((Function1)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final boolean apply(Symbol x$34) {
                            return !x$34.isDeferred();
                        }
                    }));
                }
                bcs = (List)((AbstractTraversable)bcs).tail();
            }
            return var3_3;
        }

        public final Symbol getter(Symbol base) {
            return this.getterIn(base);
        }

        public final Symbol getterIn(Symbol base) {
            return base.info().decl(this.getterName()).filter((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Symbol x$35) {
                    return x$35.hasAccessorFlag();
                }
            }));
        }

        public Names.TermName getterName() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().AnyNameOps(this.name()).getterName();
        }

        public Names.TermName setterName() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().AnyNameOps(this.name()).setterName();
        }

        public Names.TermName localName() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().AnyNameOps(this.name()).localName();
        }

        public final Symbol setter(Symbol base, boolean hasExpandedName) {
            return this.setterIn(base, hasExpandedName);
        }

        public final boolean setter$default$2() {
            return this.needsExpandedSetterName();
        }

        public final Symbol setterIn(Symbol base, boolean hasExpandedName) {
            return base.info().decl(this.setterNameInBase(base, hasExpandedName)).filter((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Symbol x$36) {
                    return x$36.hasAccessorFlag();
                }
            }));
        }

        public final boolean setterIn$default$2() {
            return this.needsExpandedSetterName();
        }

        public boolean needsExpandedSetterName() {
            return this.isMethod() ? this.hasStableFlag() && !this.isLazy() : this.hasNoFlags(0x80001000L);
        }

        public Names.TermName setterNameInBase(Symbol base, boolean expanded) {
            return expanded ? this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().expandedSetterName(this.setterName(), base) : this.setterName();
        }

        public Symbol derivedValueClassUnbox() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
        }

        public final Symbol caseModule() {
            Names.TermName modname = this.name().toTermName();
            if (this.privateWithin().isClass() && !this.privateWithin().isModuleClass() && !this.hasFlag(0x1000000000L)) {
                modname = this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().expandedName(modname, this.privateWithin());
            }
            return this.initialize().owner().info().decl(modname).suchThat((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Symbol x$37) {
                    return x$37.isModule();
                }
            }));
        }

        public Symbol deSkolemize() {
            return this;
        }

        public Object unpackLocation() {
            return null;
        }

        public final void makeNotPrivate(Symbol base) {
            block4: {
                BoxedUnit boxedUnit;
                while (this_.isPrivate()) {
                    this_.setFlag(Flags$.MODULE$.notPRIVATE());
                    Object object = this_.isMethod() && !this_.isDeferred() ? this_.setFlag(Flags$.MODULE$.lateFINAL()) : BoxedUnit.UNIT;
                    if (this_.isStaticModule() || this_.isClassConstructor()) {
                        boxedUnit = BoxedUnit.UNIT;
                    } else {
                        this_.expandName(base);
                        if (this_.isModule()) {
                            Symbol this_ = this_.moduleClass();
                            continue;
                        }
                        boxedUnit = BoxedUnit.UNIT;
                    }
                    break block4;
                }
                boxedUnit = BoxedUnit.UNIT;
            }
        }

        public Symbol makePublic() {
            return this.setPrivateWithin(this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()).resetFlag(524293L);
        }

        public Symbol firstParam() {
            Symbol symbol;
            List<Symbol> list2 = this.info().params();
            if (list2 instanceof $colon$colon) {
                $colon$colon $colon$colon = ($colon$colon)list2;
                symbol = (Symbol)$colon$colon.head();
            } else {
                symbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
            }
            return symbol;
        }

        public final AbstractFile sourceFile() {
            return this.associatedFile() == NoAbstractFile$.MODULE$ || this.associatedFile().path().endsWith(".class") ? null : this.associatedFile();
        }

        @Override
        public AbstractFile associatedFile() {
            return this.enclosingTopLevelClass().associatedFile();
        }

        public void associatedFile_$eq(AbstractFile f) {
            throw this.scala$reflect$internal$Symbols$Symbol$$$outer().abort(new StringBuilder().append((Object)"associatedFile_= inapplicable for ").append(this).toString());
        }

        public Set<Symbol> children() {
            return (Set)Predef$.MODULE$.Set().apply(Nil$.MODULE$);
        }

        public Set<Symbol> sealedDescendants() {
            return (Set)((SetLike)this.children().flatMap(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final Set<Symbol> apply(Symbol x$38) {
                    return x$38.sealedDescendants();
                }
            }, Set$.MODULE$.canBuildFrom())).$plus(this);
        }

        public final Symbol orElse(Function0<Symbol> alt) {
            return this != this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? this : alt.apply();
        }

        public final Symbol andAlso(Function1<Symbol, BoxedUnit> f) {
            BoxedUnit boxedUnit = this != this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? f.apply(this) : BoxedUnit.UNIT;
            return this;
        }

        public final <T> T fold(Function0<T> none, Function1<Symbol, T> f) {
            return this != this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? f.apply(this) : none.apply();
        }

        public final Symbol map(Function1<Symbol, Symbol> f) {
            return this == this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? this : f.apply(this);
        }

        public final Option<Symbol> toOption() {
            return this.exists() ? new Some<Symbol>(this) : None$.MODULE$;
        }

        public final Names.Name simpleName() {
            return this.name();
        }

        public final String sealedSortName() {
            return new StringBuilder().append((Object)Predef$any2stringadd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(this.scala$reflect$internal$Symbols$Symbol$$initName), "#")).append(BoxesRunTime.boxToInteger(this.id())).toString();
        }

        public final String keyString() {
            return this.isJavaInterface() ? "interface" : (this.isTrait() && !this.isImplClass() ? "trait" : (this.isClass() ? "class" : (this.isType() && !this.isParameter() ? "type" : (this.isVariable() ? "var" : (this.hasPackageFlag() ? "package" : (this.isModule() ? "object" : (this.isSourceMethod() ? "def" : (this.isTerm() && (!this.isParameter() || this.isParamAccessor()) ? "val" : ""))))))));
        }

        private SymbolKind symbolKind() {
            Tuple3<String, String, String> kind;
            Tuple3<String, String, String> tuple3 = this.isTermMacro() ? new Tuple3<String, String, String>("term macro", "macro method", "MACM") : (this instanceof FreeTermSymbol ? new Tuple3<String, String, String>("free term", "free term", "FTE") : (this instanceof FreeTypeSymbol ? new Tuple3<String, String, String>("free type", "free type", "FTY") : (this.isPackageClass() ? new Tuple3<String, String, String>("package class", "package", "PKC") : (this.hasPackageFlag() ? new Tuple3<String, String, String>("package", "package", "PK") : (this.isPackageObject() ? new Tuple3<String, String, String>("package object", "package", "PKO") : (this.isPackageObjectClass() ? new Tuple3<String, String, String>("package object class", "package", "PKOC") : (this.isAnonymousClass() ? new Tuple3<String, String, String>("anonymous class", "anonymous class", "AC") : (this.isRefinementClass() ? new Tuple3<String, String, String>("refinement class", "", "RC") : (this.isModule() ? new Tuple3<String, String, String>("module", "object", "MOD") : (this.isModuleClass() ? new Tuple3<String, String, String>("module class", "object", "MODC") : (this.isGetter() ? new Tuple3<String, String, String>("getter", this.isSourceMethod() ? "method" : "value", "GET") : (this.isSetter() ? new Tuple3<String, String, String>("setter", this.isSourceMethod() ? "method" : "value", "SET") : (this.isTerm() && this.isLazy() ? new Tuple3<String, String, String>("lazy value", "lazy value", "LAZ") : (this.isVariable() ? new Tuple3<String, String, String>("field", "variable", "VAR") : (this.isImplClass() ? new Tuple3<String, String, String>("implementation class", "class", "IMPL") : (this.isTrait() ? new Tuple3<String, String, String>("trait", "trait", "TRT") : (this.isClass() ? new Tuple3<String, String, String>("class", "class", "CLS") : (this.isType() ? new Tuple3<String, String, String>("type", "type", "TPE") : (this.isClassConstructor() && this.owner().hasCompleteInfo() && this.isPrimaryConstructor() ? new Tuple3<String, String, String>("primary constructor", "constructor", "PCTOR") : (this.isClassConstructor() ? new Tuple3<String, String, String>("constructor", "constructor", "CTOR") : (this.isSourceMethod() ? new Tuple3<String, String, String>("method", "method", "METH") : (kind = this.isTerm() ? new Tuple3<String, String, String>("value", "value", "VAL") : new Tuple3<String, String, String>("", "", "???")))))))))))))))))))))));
            if (this.isSkolem()) {
                kind = new Tuple3<String, String, String>((String)kind._1(), (String)kind._2(), new StringBuilder().append((Object)((String)kind._3())).append((Object)"#SKO").toString());
            }
            return new SymbolKind(this.scala$reflect$internal$Symbols$Symbol$$$outer(), (String)kind._1(), kind._2(), (String)kind._3());
        }

        public final String accurateKindString() {
            return this.symbolKind().accurate();
        }

        private String sanitizedKindString() {
            return this.symbolKind().sanitized();
        }

        public String abbreviatedKindString() {
            return this.symbolKind().abbreviation();
        }

        public final String kindString() {
            return BoxesRunTime.unboxToBoolean(this.scala$reflect$internal$Symbols$Symbol$$$outer().settings().debug().value()) ? this.accurateKindString() : this.sanitizedKindString();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean hasMeaninglessName() {
            if (this.isSetterParameter()) return true;
            if (this.isClassConstructor()) return true;
            if (this.isRefinementClass()) return true;
            Names.Name name = this.name();
            Names.Name name2 = this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().PACKAGE();
            if (name != null) {
                if (!name.equals(name2)) return false;
                return true;
            }
            if (name2 == null) return true;
            return false;
        }

        public String nameString() {
            String name_s = BoxesRunTime.unboxToBoolean(this.scala$reflect$internal$Symbols$Symbol$$$outer().settings().debug().value()) ? String.valueOf(this.unexpandedName()) : this.scala$reflect$internal$Symbols$Symbol$$$outer().AnyNameOps(this.unexpandedName()).dropLocal().decode();
            String kind_s = BoxesRunTime.unboxToBoolean(this.scala$reflect$internal$Symbols$Symbol$$$outer().settings().Yshowsymkinds().value()) ? new StringBuilder().append((Object)"#").append((Object)this.abbreviatedKindString()).toString() : "";
            return new StringBuilder().append((Object)name_s).append((Object)this.idString()).append((Object)kind_s).toString();
        }

        public String fullNameString() {
            return this.recur$1(this);
        }

        public final String idString() {
            String id_s = BoxesRunTime.unboxToBoolean(this.scala$reflect$internal$Symbols$Symbol$$$outer().settings().uniqid().value()) ? new StringBuilder().append((Object)"#").append(BoxesRunTime.boxToInteger(this.id())).toString() : "";
            String owner_s = BoxesRunTime.unboxToBoolean(this.scala$reflect$internal$Symbols$Symbol$$$outer().settings().Yshowsymowners().value()) ? new StringBuilder().append((Object)"@").append(BoxesRunTime.boxToInteger(this.owner().id())).toString() : "";
            return new StringBuilder().append((Object)id_s).append((Object)owner_s).toString();
        }

        /*
         * Enabled aggressive block sorting
         */
        public String toString() {
            String string2;
            if (this.isPackageObjectOrClass()) {
                MutableSettings.SettingValue settingValue = this.scala$reflect$internal$Symbols$Symbol$$$outer().settings().debug();
                MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
                if (!BoxesRunTime.unboxToBoolean(settingValue.value())) {
                    string2 = new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"package object ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.owner().decodedName()}));
                    return string2;
                }
            }
            string2 = this.compose(Predef$.MODULE$.wrapRefArray((Object[])new String[]{this.kindString(), this.hasMeaninglessName() ? new StringBuilder().append((Object)this.owner().decodedName()).append((Object)this.idString()).toString() : this.nameString()}));
            return string2;
        }

        public String ownsString() {
            Symbol owns = this.effectiveOwner();
            return owns.isClass() && !owns.isEmptyPrefix() ? String.valueOf(owns) : "";
        }

        public String locationString() {
            String string2 = this.ownsString();
            String string3 = "".equals(string2) ? "" : new StringBuilder().append((Object)" in ").append((Object)string2).toString();
            return string3;
        }

        public String fullLocationString() {
            return new StringBuilder().append((Object)this.toString()).append((Object)this.locationString()).toString();
        }

        public String signatureString() {
            return this.hasRawInfo() ? this.infoString(this.rawInfo()) : "<_>";
        }

        public final String infoString(Types.Type tp) {
            String string2;
            block12: {
                String string3;
                block13: {
                    while (true) {
                        if (this.isType()) {
                            String string4;
                            StringBuilder stringBuilder = new StringBuilder().append((Object)this.scala$reflect$internal$Symbols$Symbol$$$outer().typeParamsString(tp));
                            if (this.isClass()) {
                                string4 = new StringBuilder().append((Object)" extends ").append((Object)this.parents$1(tp)).toString();
                            } else if (this.isAliasType()) {
                                string4 = new StringBuilder().append((Object)" = ").append(tp.resultType()).toString();
                            } else {
                                String string5;
                                Types.Type type = tp.resultType();
                                if (type instanceof Types.TypeBounds) {
                                    Types.TypeBounds typeBounds = (Types.TypeBounds)type;
                                    string5 = String.valueOf(typeBounds);
                                } else {
                                    string5 = new StringBuilder().append((Object)" <: ").append(type).toString();
                                }
                                string4 = string5;
                            }
                            string2 = stringBuilder.append((Object)string4).toString();
                            break block12;
                        }
                        if (this.isModule()) {
                            string2 = "";
                            break block12;
                        }
                        if (tp instanceof Types.PolyType) {
                            Types.PolyType polyType = (Types.PolyType)tp;
                            string3 = new StringBuilder().append((Object)this.scala$reflect$internal$Symbols$Symbol$$$outer().typeParamsString(tp)).append((Object)this.infoString(polyType.resultType())).toString();
                            break block13;
                        }
                        if (!(tp instanceof Types.NullaryMethodType)) break;
                        Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)tp;
                        tp = nullaryMethodType.resultType();
                    }
                    if (tp instanceof Types.MethodType) {
                        Types.MethodType methodType = (Types.MethodType)tp;
                        string3 = new StringBuilder().append((Object)this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().valueParamsString(tp)).append((Object)this.infoString(methodType.resultType())).toString();
                    } else {
                        string3 = this.isStructuralThisType$1(tp) ? new StringBuilder().append((Object)": ").append(this.owner().name()).toString() : new StringBuilder().append((Object)": ").append(tp).toString();
                    }
                }
                string2 = string3;
            }
            return string2;
        }

        public String infosString() {
            return this.scala$reflect$internal$Symbols$$infos().toString();
        }

        public String debugLocationString() {
            String string2;
            String string3 = this.flagString();
            if ("".equals(string3)) {
                string2 = "";
            } else {
                Predef$ predef$ = Predef$.MODULE$;
                string2 = new StringOps(string3).contains(BoxesRunTime.boxToCharacter(' ')) ? new StringBuilder().append((Object)"(").append((Object)string3).append((Object)") ").toString() : new StringBuilder().append((Object)string3).append((Object)" ").toString();
            }
            return new StringBuilder().append((Object)string2).append((Object)this.fullLocationString()).toString();
        }

        private String defStringCompose(String infoString) {
            return this.compose(Predef$.MODULE$.wrapRefArray((Object[])new String[]{this.flagString(), this.keyString(), new StringBuilder().append((Object)this.varianceString()).append((Object)this.nameString()).append((Object)infoString).append((Object)this.flagsExplanationString()).toString()}));
        }

        public String defString() {
            return this.defStringCompose(this.signatureString());
        }

        public String defStringSeenAs(Types.Type info2) {
            return this.defStringCompose(this.infoString(info2));
        }

        private String compose(Seq<String> ss) {
            return ((TraversableOnce)ss.filter((Function1<String, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(String x$39) {
                    String string2 = x$39;
                    return string2 == null || !string2.equals("");
                }
            }))).mkString(" ");
        }

        public boolean isSingletonExistential() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().isSingletonName(this.name()) && this.info().bounds().hi().typeSymbol().isSubClass(this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().SingletonClass());
        }

        public String existentialToString() {
            return this.isSingletonExistential() && !BoxesRunTime.unboxToBoolean(this.scala$reflect$internal$Symbols$Symbol$$$outer().settings().debug().value()) ? new StringBuilder().append((Object)"val ").append(this.scala$reflect$internal$Symbols$Symbol$$$outer().tpnme().dropSingletonName(this.name())).append((Object)": ").append(this.scala$reflect$internal$Symbols$Symbol$$$outer().dropSingletonType().apply(this.info().bounds().hi())).toString() : this.defString();
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$Symbol$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ StdAttachments scala$reflect$internal$StdAttachments$Attachable$$$outer() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer();
        }

        @Override
        public /* synthetic */ AnnotationInfos scala$reflect$internal$AnnotationInfos$Annotatable$$$outer() {
            return this.scala$reflect$internal$Symbols$Symbol$$$outer();
        }

        public final String scala$reflect$internal$Symbols$Symbol$$msg$1(Names.Name n$1) {
            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"In ", ", renaming ", " -> ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.owner(), this.name(), n$1}));
        }

        private final boolean loop$1(List tparams2) {
            block3: {
                boolean bl;
                block2: {
                    while (true) {
                        if (((Object)Nil$.MODULE$).equals(tparams2)) {
                            bl = true;
                            break block2;
                        }
                        if (!(tparams2 instanceof $colon$colon)) break block3;
                        $colon$colon $colon$colon = ($colon$colon)tparams2;
                        if (!Variance$.MODULE$.isInvariant$extension(((Symbol)$colon$colon.head()).variance())) break;
                        tparams2 = $colon$colon.tl$1();
                    }
                    bl = false;
                }
                return bl;
            }
            throw new MatchError(tparams2);
        }

        private final void warnIfSourceLoader$1(boolean isSourceLoader$1) {
            if (isSourceLoader$1) {
                this.scala$reflect$internal$Symbols$Symbol$$$outer().devWarning((Function0<String>)((Object)new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final String apply() {
                        return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"calling Symbol#exists with sourcefile based symbol loader may give incorrect results."})).s(Nil$.MODULE$);
                    }
                }));
            }
        }

        private final int searchList$1(List params2, Types.Type fallback, int base$1) {
            int idx = params2.indexOf(this);
            return idx >= 0 ? idx + base$1 : this.searchIn$1(fallback, base$1 + params2.length());
        }

        private final int searchIn$1(Types.Type tpe, int base) {
            int n;
            if (tpe instanceof Types.PolyType) {
                Types.PolyType polyType = (Types.PolyType)tpe;
                n = this.searchList$1(polyType.typeParams(), polyType.resultType(), base);
            } else if (tpe instanceof Types.MethodType) {
                Types.MethodType methodType = (Types.MethodType)tpe;
                n = this.searchList$1(methodType.params(), methodType.resultType(), base);
            } else {
                n = -1;
            }
            return n;
        }

        /*
         * Enabled aggressive block sorting
         */
        private final void loop$2(int size2, Symbol sym, char separator$1, ObjectRef b$1) {
            Names.Name symName = sym.name();
            int nSize = symName.length() - (symName.endsWith(this.scala$reflect$internal$Symbols$Symbol$$$outer().nme().LOCAL_SUFFIX_STRING()) ? 1 : 0);
            if (!sym.isRoot() && !sym.isRootPackage()) {
                Symbol symbol = sym;
                NoSymbol noSymbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                if ((symbol == null ? noSymbol != null : !symbol.equals(noSymbol)) && !sym.owner().isEffectiveRoot()) {
                    this.loop$2(size2 + nSize + 1, sym.effectiveOwner().enclClass(), separator$1, b$1);
                    ((StringBuffer)b$1.elem).append(separator$1);
                    ((StringBuffer)b$1.elem).append(this.scala$reflect$internal$Symbols$Symbol$$$outer().chrs(), symName.start(), nSize);
                    return;
                }
            }
            int capacity = size2 + nSize;
            b$1.elem = new StringBuffer(capacity);
            ((StringBuffer)b$1.elem).append(this.scala$reflect$internal$Symbols$Symbol$$$outer().chrs(), symName.start(), nSize);
        }

        private final int baseTypeSeqLength$1(Symbol sym) {
            return sym.isAbstractType() ? 1 + sym.info().bounds().hi().baseTypeSeq().length() : sym.info().baseTypeSeq().length();
        }

        public final boolean scala$reflect$internal$Symbols$Symbol$$qualifies$1(Symbol sym, Types.Type site$1) {
            return !sym.isTerm() || site$1.memberType(this).matches(site$1.memberType(sym));
        }

        private final List loop$3(List xs) {
            block3: {
                List list2;
                block2: {
                    Symbol symbol;
                    $colon$colon $colon$colon;
                    while (true) {
                        if (((Object)Nil$.MODULE$).equals(xs)) {
                            list2 = Nil$.MODULE$;
                            break block2;
                        }
                        if (!(xs instanceof $colon$colon)) break block3;
                        $colon$colon = ($colon$colon)xs;
                        symbol = this.overriddenSymbol((Symbol)$colon$colon.head());
                        NoSymbol noSymbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                        if (noSymbol != null ? !noSymbol.equals(symbol) : symbol != null) break;
                        xs = $colon$colon.tl$1();
                    }
                    list2 = this.loop$3($colon$colon.tl$1()).$colon$colon(symbol);
                }
                return list2;
            }
            throw new MatchError(xs);
        }

        private final Symbol loop$4(List bases) {
            block3: {
                Symbol symbol;
                block2: {
                    Symbol sym;
                    while (true) {
                        if (((Object)Nil$.MODULE$).equals(bases)) {
                            symbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                            break block2;
                        }
                        if (!(bases instanceof $colon$colon)) break block3;
                        $colon$colon $colon$colon = ($colon$colon)bases;
                        Symbol symbol2 = sym = this.overriddenSymbol((Symbol)$colon$colon.head());
                        NoSymbol noSymbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                        if (symbol2 != null ? !symbol2.equals(noSymbol) : noSymbol != null) break;
                        bases = $colon$colon.tl$1();
                    }
                    symbol = sym;
                }
                return symbol;
            }
            throw new MatchError(bases);
        }

        /*
         * Enabled aggressive block sorting
         */
        private final String recur$1(Symbol sym) {
            String string2;
            block5: {
                block4: {
                    if (sym.isRootSymbol()) break block4;
                    Symbol symbol = sym;
                    NoSymbol noSymbol = this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol();
                    if (symbol != null ? !symbol.equals(noSymbol) : noSymbol != null) break block5;
                }
                string2 = sym.nameString();
                return string2;
            }
            if (sym.owner().isEffectiveRoot()) {
                string2 = sym.nameString();
                return string2;
            }
            string2 = new StringBuilder().append((Object)this.recur$1(sym.effectiveOwner().enclClass())).append((Object)".").append((Object)sym.nameString()).toString();
            return string2;
        }

        private final String parents$1(Types.Type tp$2) {
            return BoxesRunTime.unboxToBoolean(this.scala$reflect$internal$Symbols$Symbol$$$outer().settings().debug().value()) ? this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().parentsString(tp$2.parents()) : this.scala$reflect$internal$Symbols$Symbol$$$outer().definitions().briefParentsString(tp$2.parents());
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private final boolean isStructuralThisType$1(Types.Type tp$2) {
            if (!this.owner().isInitialized()) return false;
            if (!this.owner().isStructuralRefinement()) return false;
            Types.Type type = tp$2;
            Types.Type type2 = this.owner().tpe();
            if (type != null) {
                if (!type.equals(type2)) return false;
                return true;
            }
            if (type2 == null) return true;
            return false;
        }

        public Symbol(SymbolTable $outer, Symbol initOwner, Position initPos, Names.Name initName) {
            this.scala$reflect$internal$Symbols$Symbol$$initOwner = initOwner;
            this.scala$reflect$internal$Symbols$Symbol$$initName = initName;
            super($outer);
            HasFlags$class.$init$(this);
            AnnotationInfos$Annotatable$class.$init$(this);
            StdAttachments$Attachable$class.$init$(this);
            boolean bl = $outer.isCompilerUniverse() || this instanceof SynchronizedSymbols.SynchronizedSymbol || this.isThreadsafe(this.scala$reflect$internal$Symbols$Symbol$$$outer().AllOps());
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"unsafe symbol ", " (child of ", ") in runtime reflection universe"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$Symbols$Symbol$$initName, this.scala$reflect$internal$Symbols$Symbol$$initOwner}))).toString());
            }
            this._rawowner = initOwner == null ? this : initOwner;
            this.rawatt_$eq(initPos);
            this.id = $outer.nextId();
            this._validTo = 0;
            if ($outer.traceSymbolActivity()) {
                TraceSymbolActivity$class.recordNewSymbol($outer.traceSymbols(), this);
            }
            this.scala$reflect$internal$Symbols$$infos = null;
            this._annotations = Nil$.MODULE$;
        }
    }

    public class NoSymbol
    extends Symbol {
        @Override
        public Names.TermName asNameType(Names.Name n) {
            return n.toTermName();
        }

        @Override
        public Names.TermName rawname() {
            return (Names.TermName)this.scala$reflect$internal$Symbols$NoSymbol$$$outer().nme().NO_NAME();
        }

        @Override
        public Names.TermName name() {
            return (Names.TermName)this.scala$reflect$internal$Symbols$NoSymbol$$$outer().nme().NO_NAME();
        }

        public Nothing$ name_$eq(Names.Name n) {
            return this.scala$reflect$internal$Symbols$NoSymbol$$$outer().abort(new StringBuilder().append((Object)"Cannot set NoSymbol's name to ").append(n).toString());
        }

        @Override
        public void info_$eq(Types.Type info2) {
            this.scala$reflect$internal$Symbols$$infos_$eq(new TypeHistory(this.scala$reflect$internal$Symbols$NoSymbol$$$outer(), 1, this.scala$reflect$internal$Symbols$NoSymbol$$$outer().NoType(), null));
            this.unlock();
            this.validTo_$eq(this.scala$reflect$internal$Symbols$NoSymbol$$$outer().currentPeriod());
        }

        @Override
        public long flagMask() {
            return -1L;
        }

        @Override
        public boolean exists() {
            return false;
        }

        @Override
        public boolean isHigherOrderTypeParameter() {
            return false;
        }

        @Override
        public NoSymbol companionClass() {
            return this.scala$reflect$internal$Symbols$NoSymbol$$$outer().NoSymbol();
        }

        @Override
        public NoSymbol companionModule() {
            return this.scala$reflect$internal$Symbols$NoSymbol$$$outer().NoSymbol();
        }

        @Override
        public NoSymbol companionSymbol() {
            return this.scala$reflect$internal$Symbols$NoSymbol$$$outer().NoSymbol();
        }

        @Override
        public boolean isSubClass(Symbol that) {
            return false;
        }

        @Override
        public NoSymbol filter(Function1<Symbol, Object> cond) {
            return this;
        }

        @Override
        public String defString() {
            return this.toString();
        }

        @Override
        public String locationString() {
            return "";
        }

        public Nil$ enclClassChain() {
            return Nil$.MODULE$;
        }

        @Override
        public Symbol enclClass() {
            return this;
        }

        @Override
        public Symbol enclosingTopLevelClass() {
            return this;
        }

        @Override
        public Symbol enclosingTopLevelClassOrDummy() {
            return this;
        }

        @Override
        public Symbol enclosingPackageClass() {
            return this;
        }

        @Override
        public Symbol enclMethod() {
            return this;
        }

        @Override
        public NoAbstractFile$ associatedFile() {
            return NoAbstractFile$.MODULE$;
        }

        @Override
        public Symbol owner() {
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final String apply() {
                    return "NoSymbol.owner";
                }
            };
            SymbolTable symbolTable = this.scala$reflect$internal$Symbols$NoSymbol$$$outer();
            symbolTable.devWarning((Function0<String>)((Object)new /* invalid duplicate definition of identical inner class */));
            return this;
        }

        @Override
        public List<Symbol> ownerChain() {
            return Nil$.MODULE$;
        }

        @Override
        public Iterator<Symbol> ownersIterator() {
            return package$.MODULE$.Iterator().empty();
        }

        @Override
        public List<Symbol> alternatives() {
            return Nil$.MODULE$;
        }

        @Override
        public NoSymbol reset(Types.Type completer) {
            return this;
        }

        @Override
        public Types.Type info() {
            return this.scala$reflect$internal$Symbols$NoSymbol$$$outer().NoType();
        }

        @Override
        public Types.Type existentialBound() {
            return this.scala$reflect$internal$Symbols$NoSymbol$$$outer().NoType();
        }

        @Override
        public Types.Type rawInfo() {
            return this.scala$reflect$internal$Symbols$NoSymbol$$$outer().NoType();
        }

        @Override
        public Symbol accessBoundary(Symbol base) {
            return this.enclosingRootClass();
        }

        public Nothing$ cloneSymbolImpl(Symbol owner2, long newFlags) {
            return this.scala$reflect$internal$Symbols$NoSymbol$$$outer().abort("NoSymbol.clone()");
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$NoSymbol$$$outer() {
            return this.$outer;
        }

        public NoSymbol(SymbolTable $outer) {
            super($outer, null, $outer.NoPosition(), $outer.nme().NO_NAME());
            this.setInfo($outer.NoType());
            this.privateWithin_$eq(this);
        }
    }

    public class SymbolOps
    implements Product,
    Serializable {
        private final boolean isFlagRelated;
        private final long mask;
        public final /* synthetic */ SymbolTable $outer;

        public boolean isFlagRelated() {
            return this.isFlagRelated;
        }

        public long mask() {
            return this.mask;
        }

        public SymbolOps copy(boolean isFlagRelated, long mask) {
            return new SymbolOps(this.scala$reflect$internal$Symbols$SymbolOps$$$outer(), isFlagRelated, mask);
        }

        public boolean copy$default$1() {
            return this.isFlagRelated();
        }

        public long copy$default$2() {
            return this.mask();
        }

        @Override
        public String productPrefix() {
            return "SymbolOps";
        }

        @Override
        public int productArity() {
            return 2;
        }

        @Override
        public Object productElement(int x$1) {
            Constable constable;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 1: {
                    constable = BoxesRunTime.boxToLong(this.mask());
                    break;
                }
                case 0: {
                    constable = BoxesRunTime.boxToBoolean(this.isFlagRelated());
                }
            }
            return constable;
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof SymbolOps;
        }

        public int hashCode() {
            return Statics.finalizeHash(Statics.mix(Statics.mix(-889275714, this.isFlagRelated() ? 1231 : 1237), Statics.longHash(this.mask())), 2);
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
            if (!(x$1 instanceof SymbolOps)) return false;
            if (((SymbolOps)x$1).scala$reflect$internal$Symbols$SymbolOps$$$outer() != this.scala$reflect$internal$Symbols$SymbolOps$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            SymbolOps symbolOps = (SymbolOps)x$1;
            if (this.isFlagRelated() != symbolOps.isFlagRelated()) return false;
            if (this.mask() != symbolOps.mask()) return false;
            if (!symbolOps.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$SymbolOps$$$outer() {
            return this.$outer;
        }

        public SymbolOps(SymbolTable $outer, boolean isFlagRelated, long mask) {
            this.isFlagRelated = isFlagRelated;
            this.mask = mask;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Product$class.$init$(this);
        }
    }

    public abstract class TypeSymbol
    extends Symbol
    implements Symbols.TypeSymbolApi {
        private Names.TypeName _rawname;
        private Types.Type tyconCache;
        private int tyconRunId;
        private Types.Type tpeCache;
        private int tpePeriod;

        @Override
        public final boolean isType() {
            return Symbols$TypeSymbolApi$class.isType(this);
        }

        @Override
        public final Symbols.TypeSymbolApi asType() {
            return Symbols$TypeSymbolApi$class.asType(this);
        }

        @Override
        public Names.TypeName rawname() {
            return this._rawname;
        }

        @Override
        public Names.TypeName name() {
            return this._rawname;
        }

        @Override
        public final Names.TypeName asNameType(Names.Name n) {
            return n.toTypeName();
        }

        @Override
        public boolean isNonClassType() {
            return true;
        }

        @Override
        public String resolveOverloadedFlag(long flag) {
            String string2 = 0x2000000L == flag ? "<trait>" : (0x800000000L == flag ? "<existential>" : (65536L == flag ? "<covariant>" : (131072L == flag ? "<contravariant>" : super.resolveOverloadedFlag(flag))));
            return string2;
        }

        private Types.Type tyconCache() {
            return this.tyconCache;
        }

        private void tyconCache_$eq(Types.Type x$1) {
            this.tyconCache = x$1;
        }

        private int tyconRunId() {
            return this.tyconRunId;
        }

        private void tyconRunId_$eq(int x$1) {
            this.tyconRunId = x$1;
        }

        private Types.Type tpeCache() {
            return this.tpeCache;
        }

        private void tpeCache_$eq(Types.Type x$1) {
            this.tpeCache = x$1;
        }

        private int tpePeriod() {
            return this.tpePeriod;
        }

        private void tpePeriod_$eq(int x$1) {
            this.tpePeriod = x$1;
        }

        @Override
        public boolean isAbstractType() {
            return this.hasFlag(16);
        }

        @Override
        public boolean isContravariant() {
            return this.hasFlag(131072);
        }

        @Override
        public boolean isCovariant() {
            return this.hasFlag(65536);
        }

        @Override
        public boolean isExistentiallyBound() {
            return this.hasFlag(0x800000000L);
        }

        @Override
        public boolean isTypeParameter() {
            return this.isTypeParameterOrSkolem() && !this.isSkolem();
        }

        @Override
        public boolean isTypeParameterOrSkolem() {
            return this.hasFlag(8192);
        }

        @Override
        public Types.Type existentialBound() {
            throw this.scala$reflect$internal$Symbols$TypeSymbol$$$outer().abort(new StringBuilder().append((Object)"unexpected type: ").append(this.getClass()).append((Object)" ").append((Object)this.debugLocationString()).toString());
        }

        @Override
        public void name_$eq(Names.Name name) {
            Names.Name name2 = name;
            Names.TypeName typeName = this.rawname();
            if (name2 == null ? typeName != null : !name2.equals(typeName)) {
                super.name_$eq(name);
                this.changeNameInOwners(name);
                this._rawname = name.toTypeName();
            }
        }

        private Types.Type newPrefix() {
            return this.hasFlag(0x800002000L) ? this.scala$reflect$internal$Symbols$TypeSymbol$$$outer().NoPrefix() : this.owner().thisType();
        }

        private Types.Type newTypeRef(List<Types.Type> targs) {
            return this.scala$reflect$internal$Symbols$TypeSymbol$$$outer().typeRef(this.newPrefix(), this, targs);
        }

        @Override
        public Types.Type tpe_$times() {
            this.maybeUpdateTypeCache();
            return this.tpeCache();
        }

        @Override
        public Types.Type typeConstructor() {
            if (this.tyconCacheNeedsUpdate()) {
                this.setTyconCache(this.newTypeRef(Nil$.MODULE$));
            }
            return this.tyconCache();
        }

        @Override
        public Types.Type tpeHK() {
            return this.typeConstructor();
        }

        private boolean tyconCacheNeedsUpdate() {
            return this.tyconCache() == null || this.tyconRunId() != this.scala$reflect$internal$Symbols$TypeSymbol$$$outer().currentRunId();
        }

        private void setTyconCache(Types.Type tycon) {
            this.tyconCache_$eq(tycon);
            this.tyconRunId_$eq(this.scala$reflect$internal$Symbols$TypeSymbol$$$outer().currentRunId());
            boolean bl = this.tyconCache() != null;
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(this).toString());
            }
        }

        private void maybeUpdateTypeCache() {
            if (this.tpePeriod() != this.scala$reflect$internal$Symbols$TypeSymbol$$$outer().currentPeriod()) {
                if (this.scala$reflect$internal$Symbols$TypeSymbol$$$outer().isValid(this.tpePeriod())) {
                    this.tpePeriod_$eq(this.scala$reflect$internal$Symbols$TypeSymbol$$$outer().currentPeriod());
                } else {
                    this.updateTypeCache();
                }
            }
        }

        private void updateTypeCache() {
            ClassSymbol classSymbol;
            if (this.tpeCache() == this.scala$reflect$internal$Symbols$TypeSymbol$$$outer().NoType()) {
                throw new CyclicReference(this.scala$reflect$internal$Symbols$TypeSymbol$$$outer(), this, this.typeConstructor());
            }
            if (this.isInitialized()) {
                this.tpePeriod_$eq(this.scala$reflect$internal$Symbols$TypeSymbol$$$outer().currentPeriod());
            }
            this.tpeCache_$eq(this.scala$reflect$internal$Symbols$TypeSymbol$$$outer().NoType());
            boolean noTypeParams = this.scala$reflect$internal$Symbols$TypeSymbol$$$outer().phase().erasedTypes() && !this.equals(classSymbol = this.scala$reflect$internal$Symbols$TypeSymbol$$$outer().definitions().ArrayClass()) || this.unsafeTypeParams().isEmpty();
            this.tpeCache_$eq(this.newTypeRef(noTypeParams ? Nil$.MODULE$ : this.unsafeTypeParams().map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final Types.Type apply(Symbol x$40) {
                    return x$40.typeConstructor();
                }
            }, List$.MODULE$.canBuildFrom())));
            if (noTypeParams && this.tyconCacheNeedsUpdate()) {
                this.setTyconCache(this.tpeCache());
            }
        }

        @Override
        public void info_$eq(Types.Type tp) {
            this.tpePeriod_$eq(0);
            this.tyconCache_$eq(null);
            super.info_$eq(tp);
        }

        @Override
        public final boolean isNonBottomSubClass(Symbol that) {
            return this == that || this.isError() || that.isError() || this.info().baseTypeIndex(that) >= 0;
        }

        @Override
        public TypeSymbol reset(Types.Type completer) {
            super.reset(completer);
            this.tpePeriod_$eq(0);
            this.tyconRunId_$eq(0);
            return this;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$TypeSymbol$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ scala.reflect.api.Symbols scala$reflect$api$Symbols$TypeSymbolApi$$$outer() {
            return this.scala$reflect$internal$Symbols$TypeSymbol$$$outer();
        }

        public TypeSymbol(SymbolTable $outer, Symbol initOwner, Position initPos, Names.TypeName initName) {
            super($outer, initOwner, initPos, initName);
            Symbols$TypeSymbolApi$class.$init$(this);
            this.privateWithin_$eq($outer.NoSymbol());
            this._rawname = initName;
            this.tyconCache = null;
            this.tyconRunId = 0;
            this.tpePeriod = 0;
        }
    }

    public class SymbolKind
    implements Product,
    Serializable {
        private final String accurate;
        private final String sanitized;
        private final String abbreviation;
        public final /* synthetic */ SymbolTable $outer;

        public String accurate() {
            return this.accurate;
        }

        public String sanitized() {
            return this.sanitized;
        }

        public String abbreviation() {
            return this.abbreviation;
        }

        public SymbolKind copy(String accurate, String sanitized, String abbreviation) {
            return new SymbolKind(this.scala$reflect$internal$Symbols$SymbolKind$$$outer(), accurate, sanitized, abbreviation);
        }

        public String copy$default$1() {
            return this.accurate();
        }

        public String copy$default$2() {
            return this.sanitized();
        }

        public String copy$default$3() {
            return this.abbreviation();
        }

        @Override
        public String productPrefix() {
            return "SymbolKind";
        }

        @Override
        public int productArity() {
            return 3;
        }

        @Override
        public Object productElement(int x$1) {
            String string2;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 2: {
                    string2 = this.abbreviation();
                    break;
                }
                case 1: {
                    string2 = this.sanitized();
                    break;
                }
                case 0: {
                    string2 = this.accurate();
                }
            }
            return string2;
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof SymbolKind;
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
            if (!(x$1 instanceof SymbolKind)) return false;
            if (((SymbolKind)x$1).scala$reflect$internal$Symbols$SymbolKind$$$outer() != this.scala$reflect$internal$Symbols$SymbolKind$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            SymbolKind symbolKind = (SymbolKind)x$1;
            String string2 = this.accurate();
            String string3 = symbolKind.accurate();
            if (string2 == null) {
                if (string3 != null) {
                    return false;
                }
            } else if (!string2.equals(string3)) return false;
            String string4 = this.sanitized();
            String string5 = symbolKind.sanitized();
            if (string4 == null) {
                if (string5 != null) {
                    return false;
                }
            } else if (!string4.equals(string5)) return false;
            String string6 = this.abbreviation();
            String string7 = symbolKind.abbreviation();
            if (string6 == null) {
                if (string7 != null) {
                    return false;
                }
            } else if (!string6.equals(string7)) return false;
            if (!symbolKind.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$SymbolKind$$$outer() {
            return this.$outer;
        }

        public SymbolKind(SymbolTable $outer, String accurate, String sanitized, String abbreviation) {
            this.accurate = accurate;
            this.sanitized = sanitized;
            this.abbreviation = abbreviation;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Product$class.$init$(this);
        }
    }

    public class TermSymbol
    extends Symbol
    implements Symbols.TermSymbolApi {
        private Symbol _referenced;
        private Names.TermName _rawname;
        private final long validAliasFlags;

        @Override
        public final boolean isTerm() {
            return Symbols$TermSymbolApi$class.isTerm(this);
        }

        @Override
        public final Symbols.TermSymbolApi asTerm() {
            return Symbols$TermSymbolApi$class.asTerm(this);
        }

        @Override
        public boolean isOverloadedMethod() {
            return Symbols$TermSymbolApi$class.isOverloadedMethod(this);
        }

        @Override
        public Names.TermName rawname() {
            return this._rawname;
        }

        @Override
        public Names.TermName name() {
            return this._rawname;
        }

        @Override
        public void name_$eq(Names.Name name) {
            Names.Name name2 = name;
            Names.TermName termName = this.rawname();
            if (name2 == null ? termName != null : !name2.equals(termName)) {
                super.name_$eq(name);
                this.changeNameInOwners(name);
                this._rawname = name.toTermName();
            }
        }

        @Override
        public final Names.TermName asNameType(Names.Name n) {
            return n.toTermName();
        }

        @Override
        public boolean isValue() {
            return !this.isModule() || !this.hasFlag(0x104000);
        }

        @Override
        public boolean isVariable() {
            return this.isMutable() && !this.isMethod();
        }

        @Override
        public boolean isTermMacro() {
            return this.hasFlag(32768);
        }

        @Override
        public boolean isCapturedVariable() {
            return this.hasAllFlags(69632L) && !this.hasFlag(64);
        }

        @Override
        public Symbol companionSymbol() {
            return this.companionClass();
        }

        @Override
        public Symbol moduleClass() {
            return this.isModule() ? this.referenced() : this.scala$reflect$internal$Symbols$TermSymbol$$$outer().NoSymbol();
        }

        @Override
        public boolean isBridge() {
            return this.hasFlag(0x4000000);
        }

        @Override
        public boolean isEarlyInitialized() {
            return this.hasFlag(0x2000000000L);
        }

        @Override
        public boolean isMethod() {
            return this.hasFlag(64);
        }

        @Override
        public boolean isModule() {
            return this.hasFlag(256);
        }

        @Override
        public boolean isOverloaded() {
            return this.hasFlag(0x200000000L);
        }

        @Override
        public boolean isValueParameter() {
            return this.hasFlag(8192);
        }

        @Override
        public boolean isSetterParameter() {
            return this.isValueParameter() && this.owner().isSetter();
        }

        @Override
        public boolean isAccessor() {
            return this.hasFlag(0x8000000);
        }

        @Override
        public boolean isGetter() {
            return this.isAccessor() && !this.isSetter();
        }

        @Override
        public boolean isDefaultGetter() {
            return this.name().containsName(this.scala$reflect$internal$Symbols$TermSymbol$$$outer().nme().DEFAULT_GETTER_STRING());
        }

        @Override
        public boolean isSetter() {
            return this.isAccessor() && this.scala$reflect$internal$Symbols$TermSymbol$$$outer().nme().isSetterName(this.name());
        }

        @Override
        public boolean isLocalDummy() {
            return this.scala$reflect$internal$Symbols$TermSymbol$$$outer().nme().isLocalDummyName(this.name());
        }

        @Override
        public boolean isClassConstructor() {
            Names.TermName termName = this.name();
            Names.TermName termName2 = this.scala$reflect$internal$Symbols$TermSymbol$$$outer().nme().CONSTRUCTOR();
            return !(termName != null ? !termName.equals(termName2) : termName2 != null);
        }

        @Override
        public boolean isMixinConstructor() {
            Names.TermName termName = this.name();
            Names.TermName termName2 = this.scala$reflect$internal$Symbols$TermSymbol$$$outer().nme().MIXIN_CONSTRUCTOR();
            return !(termName != null ? !termName.equals(termName2) : termName2 != null);
        }

        @Override
        public boolean isConstructor() {
            return this.scala$reflect$internal$Symbols$TermSymbol$$$outer().nme().isConstructorName(this.name());
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean isPackageObject() {
            if (!this.isModule()) return false;
            Names.TermName termName = this.name();
            Names.Name name = this.scala$reflect$internal$Symbols$TermSymbol$$$outer().nme().PACKAGE();
            if (termName != null) {
                if (!termName.equals(name)) return false;
                return true;
            }
            if (name == null) return true;
            return false;
        }

        @Override
        public String resolveOverloadedFlag(long flag) {
            String string2 = 0x2000000L == flag ? "<defaultparam>" : (0x800000000L == flag ? "<mixedin>" : (131072L == flag ? "<label>" : (0x2000000000L == flag ? "<presuper>" : (65536L == flag ? (this.isValueParameter() ? "<bynameparam>" : "<captured>") : super.resolveOverloadedFlag(flag)))));
            return string2;
        }

        public Symbol referenced() {
            return this._referenced;
        }

        public void referenced_$eq(Symbol x) {
            this._referenced = x;
        }

        @Override
        public Types.TypeBounds existentialBound() {
            return this.scala$reflect$internal$Symbols$TermSymbol$$$outer().singletonBounds(this.tpe());
        }

        @Override
        public TermSymbol cloneSymbolImpl(Symbol owner2, long newFlags) {
            return owner2.newTermSymbol(this.name(), this.pos(), newFlags).copyAttrsFrom(this);
        }

        public TermSymbol copyAttrsFrom(TermSymbol original) {
            this.referenced_$eq(original.referenced());
            return this;
        }

        private long validAliasFlags() {
            return this.validAliasFlags;
        }

        @Override
        public Symbol alias() {
            return this.hasFlag(this.validAliasFlags()) ? ((TermSymbol)this.initialize()).referenced() : this.scala$reflect$internal$Symbols$TermSymbol$$$outer().NoSymbol();
        }

        public TermSymbol setAlias(Symbol alias) {
            Symbol symbol = alias;
            NoSymbol noSymbol = this.scala$reflect$internal$Symbols$TermSymbol$$$outer().NoSymbol();
            boolean bl = symbol != null ? !symbol.equals(noSymbol) : noSymbol != null;
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(this).toString());
            }
            boolean bl2 = !alias.isOverloaded();
            Predef$ predef$2 = Predef$.MODULE$;
            if (!bl2) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(alias).toString());
            }
            boolean bl3 = this.hasFlag(this.validAliasFlags());
            Predef$ predef$3 = Predef$.MODULE$;
            if (!bl3) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(this).toString());
            }
            this.referenced_$eq(alias);
            return this;
        }

        @Override
        public Symbol outerSource() {
            return this.unexpandedName().endsWith(this.scala$reflect$internal$Symbols$TermSymbol$$$outer().nme().OUTER()) ? ((TermSymbol)this.initialize()).referenced() : this.scala$reflect$internal$Symbols$TermSymbol$$$outer().NoSymbol();
        }

        public TermSymbol setModuleClass(Symbol clazz) {
            boolean bl = this.isModule();
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(this).toString());
            }
            this.referenced_$eq(clazz);
            return this;
        }

        /*
         * Unable to fully structure code
         */
        public TermSymbol setLazyAccessor(Symbol sym) {
            if (!this.isLazy()) ** GOTO lbl-1000
            v0 = this.referenced();
            var2_2 = this.scala$reflect$internal$Symbols$TermSymbol$$$outer().NoSymbol();
            if (!(v0 == null ? var2_2 != null : v0.equals(var2_2) == false)) ** GOTO lbl-1000
            v1 = this.referenced();
            if (!(v1 != null ? v1.equals(sym) == false : sym != null)) lbl-1000:
            // 2 sources

            {
                v2 = true;
            } else lbl-1000:
            // 2 sources

            {
                v2 = false;
            }
            var4_3 = v2;
            var3_4 = Predef$.MODULE$;
            if (!var4_3) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(new Tuple4<TermSymbol, String, Symbol, Symbol>(this, this.debugFlagString(), this.referenced(), sym)).toString());
            }
            this.referenced_$eq(sym);
            return this;
        }

        @Override
        public Symbol lazyAccessor() {
            boolean bl = this.isLazy();
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(this).toString());
            }
            return this.referenced();
        }

        @Override
        public void expandName(Symbol base) {
            if (!this.hasFlag(0x1000000000L)) {
                this.setFlag(0x1000000000L);
                if (this.hasAccessorFlag() && !this.isDeferred()) {
                    ((Symbol)this.accessed()).expandName(base);
                } else if (this.hasGetter()) {
                    this.getterIn(this.owner()).expandName(base);
                    this.setterIn(this.owner(), this.setterIn$default$2()).expandName(base);
                }
                this.name_$eq(this.scala$reflect$internal$Symbols$TermSymbol$$$outer().nme().expandedName(this.name().toTermName(), base));
            }
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$TermSymbol$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ scala.reflect.api.Symbols scala$reflect$api$Symbols$TermSymbolApi$$$outer() {
            return this.scala$reflect$internal$Symbols$TermSymbol$$$outer();
        }

        public TermSymbol(SymbolTable $outer, Symbol initOwner, Position initPos, Names.TermName initName) {
            super($outer, initOwner, initPos, initName);
            Symbols$TermSymbolApi$class.$init$(this);
            this._referenced = $outer.NoSymbol();
            this.privateWithin_$eq($outer.NoSymbol());
            this._rawname = initName;
            this.validAliasFlags = 1134676672512L;
        }
    }

    public class TypeSkolem
    extends TypeSymbol {
        private final Object origin;
        private final int level;

        public int level() {
            return this.level;
        }

        @Override
        public final boolean isSkolem() {
            return true;
        }

        @Override
        public boolean isExistentialSkolem() {
            return this.hasFlag(0x800000000L);
        }

        @Override
        public boolean isGADTSkolem() {
            return this.hasAllFlags(this.GADT_SKOLEM_FLAGS());
        }

        @Override
        public boolean isTypeSkolem() {
            return this.hasFlag(8192);
        }

        @Override
        public boolean isAbstractType() {
            return this.hasFlag(16);
        }

        @Override
        public Types.Type existentialBound() {
            return this.isAbstractType() ? this.info() : super.existentialBound();
        }

        @Override
        public Symbol deSkolemize() {
            Symbol symbol;
            Object object = this.origin;
            if (object instanceof Symbol && ((Symbol)object).scala$reflect$internal$Symbols$Symbol$$$outer() == this.scala$reflect$internal$Symbols$TypeSkolem$$$outer()) {
                Symbol symbol2 = (Symbol)object;
                symbol = symbol2;
            } else {
                symbol = this;
            }
            return symbol;
        }

        @Override
        public Object unpackLocation() {
            return this.origin;
        }

        @Override
        public List<Symbol> typeParams() {
            return this.info().typeParams();
        }

        @Override
        public TypeSkolem cloneSymbolImpl(Symbol owner2, long newFlags) {
            return owner2.newTypeSkolemSymbol(this.name(), this.origin, this.pos(), newFlags);
        }

        @Override
        public String nameString() {
            return BoxesRunTime.unboxToBoolean(this.scala$reflect$internal$Symbols$TypeSkolem$$$outer().settings().debug().value()) ? new StringBuilder().append((Object)super.nameString()).append((Object)"&").append(BoxesRunTime.boxToInteger(this.level())).toString() : super.nameString();
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$TypeSkolem$$$outer() {
            return this.$outer;
        }

        public TypeSkolem(SymbolTable $outer, Symbol initOwner, Position initPos, Names.TypeName initName, Object origin) {
            this.origin = origin;
            super($outer, initOwner, initPos, initName);
            this.level = $outer.skolemizationLevel();
        }
    }

    public interface StubSymbol {
        public String missingMessage();

        public Nothing$ failIfStub();

        public Types$NoType$ originalInfo();

        public AbstractFile associatedFile();

        public Types$NoType$ info();

        public Types$NoType$ rawInfo();

        public NoSymbol companionSymbol();

        public /* synthetic */ Symbols scala$reflect$internal$Symbols$StubSymbol$$$outer();
    }

    public interface FreeSymbol {
        public String origin();
    }

    public class ClassSymbol
    extends TypeSymbol
    implements Symbols.ClassSymbolApi {
        private Names.TypeName flatname;
        private AbstractFile _associatedFile;
        private Symbol thissym;
        private Types.Type thisTypeCache;
        private int thisTypePeriod;
        private Set<Symbol> childSet;

        @Override
        public final boolean isClass() {
            return Symbols$ClassSymbolApi$class.isClass(this);
        }

        @Override
        public final Symbols.ClassSymbolApi asClass() {
            return Symbols$ClassSymbolApi$class.asClass(this);
        }

        @Override
        public String resolveOverloadedFlag(long flag) {
            String string2 = 131072L == flag ? "<inconstructor>" : (0x800000000L == flag ? "<existential>" : (0x2000000000L == flag ? "<implclass>" : super.resolveOverloadedFlag(flag)));
            return string2;
        }

        @Override
        public final boolean isNonClassType() {
            return false;
        }

        @Override
        public final boolean isAbstractType() {
            return false;
        }

        @Override
        public final boolean isAliasType() {
            return false;
        }

        @Override
        public final boolean isContravariant() {
            return false;
        }

        @Override
        public boolean isAbstractClass() {
            return this.hasFlag(8);
        }

        @Override
        public boolean isCaseClass() {
            return this.hasFlag(2048);
        }

        @Override
        public boolean isClassLocalToConstructor() {
            return this.hasFlag(131072);
        }

        @Override
        public boolean isImplClass() {
            return this.hasFlag(0x2000000000L);
        }

        @Override
        public boolean isModuleClass() {
            return this.hasFlag(256);
        }

        @Override
        public boolean isPackageClass() {
            return this.hasFlag(16384);
        }

        @Override
        public boolean isTrait() {
            return this.hasFlag(0x2000000);
        }

        @Override
        public boolean isAnonOrRefinementClass() {
            return this.isAnonymousClass() || this.isRefinementClass();
        }

        @Override
        public boolean isAnonymousClass() {
            return this.name().containsName(this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().tpnme().ANON_CLASS_NAME());
        }

        @Override
        public boolean isConcreteClass() {
            return !this.hasFlag(0x2000008);
        }

        @Override
        public boolean isJavaInterface() {
            return this.hasAllFlags(0x2100000L) || this.hasJavaAnnotationFlag();
        }

        @Override
        public boolean isNestedClass() {
            return !this.isTopLevel();
        }

        @Override
        public boolean isNumericValueClass() {
            return this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().definitions().isNumericValueClass(this);
        }

        @Override
        public boolean isNumeric() {
            return this.isNumericValueClass();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean isPackageObjectClass() {
            if (!this.isModuleClass()) return false;
            Names.TypeName typeName = this.name();
            Names.Name name = this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().tpnme().PACKAGE();
            if (typeName != null) {
                if (!typeName.equals(name)) return false;
                return true;
            }
            if (name == null) return true;
            return false;
        }

        @Override
        public boolean isPrimitiveValueClass() {
            return this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().definitions().isPrimitiveValueClass(this);
        }

        @Override
        public boolean isPrimitive() {
            return this.isPrimitiveValueClass();
        }

        private Symbol lastParent() {
            return this.tpe().parents().isEmpty() ? this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().NoSymbol() : this.tpe().parents().last().typeSymbol();
        }

        @Override
        public Symbol toInterface() {
            return this.isImplClass() ? (this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().phase().next().erasedTypes() ? this.lastParent() : this.owner().info().decl(this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().tpnme().interfaceName(this.name()))) : super.toInterface();
        }

        @Override
        public boolean isLocalClass() {
            return this.isAnonOrRefinementClass() || this.isLocalToBlock() || !this.isTopLevel() && this.owner().isLocalClass();
        }

        @Override
        public List<Symbol> enclClassChain() {
            return this.owner().enclClassChain().$colon$colon(this);
        }

        public final Symbol companionModule0() {
            return this.flatOwnerInfo().decl(this.name().toTermName()).suchThat((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ClassSymbol $outer;

                public final boolean apply(Symbol sym) {
                    return sym.isModuleNotMethod() && sym.isCoDefinedWith(this.$outer);
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
        public Symbol companionModule() {
            return this.companionModule0();
        }

        @Override
        public Symbol companionSymbol() {
            return this.companionModule0();
        }

        @Override
        public Symbol linkedClassOfClass() {
            return this.companionModule().moduleClass();
        }

        @Override
        public Symbol sourceModule() {
            return this.isModuleClass() ? this.companionModule() : this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().NoSymbol();
        }

        @Override
        public Types.Type existentialBound() {
            return this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().GenPolyType().apply(this.typeParams(), this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().TypeBounds().upper(this.classBound()));
        }

        public Names.TermName primaryConstructorName() {
            return this.hasFlag(0x2002000000L) ? this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().nme().MIXIN_CONSTRUCTOR() : this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().nme().CONSTRUCTOR();
        }

        @Override
        public Symbol primaryConstructor() {
            Symbol c = this.info().decl(this.primaryConstructorName());
            return c.isOverloaded() ? c.alternatives().head() : c;
        }

        @Override
        public AbstractFile associatedFile() {
            return this.isTopLevel() ? (this._associatedFile == null ? NoAbstractFile$.MODULE$ : this._associatedFile) : super.associatedFile();
        }

        @Override
        public void associatedFile_$eq(AbstractFile f) {
            this._associatedFile = f;
        }

        @Override
        public ClassSymbol reset(Types.Type completer) {
            super.reset(completer);
            this.thissym = this;
            return this;
        }

        @Override
        public Types.Type thisType() {
            int period = this.thisTypePeriod;
            if (period != this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().currentPeriod()) {
                if (!this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().isValid(period)) {
                    this.thisTypeCache = this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().ThisType().apply(this);
                }
                this.thisTypePeriod = this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().currentPeriod();
            }
            return this.thisTypeCache;
        }

        @Override
        public Symbol owner() {
            return this.needsFlatClasses() ? this.rawowner().owner() : this.rawowner();
        }

        @Override
        public Names.TypeName name() {
            Names.TypeName typeName;
            if (Statistics$.MODULE$.canEnable()) {
                Statistics.Counter counter = SymbolsStats$.MODULE$.nameCount();
                if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && counter != null) {
                    counter.value_$eq(counter.value() + 1);
                }
            }
            if (this.needsFlatClasses()) {
                if (this.flatname == null) {
                    this.flatname = (Names.TypeName)this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().tpnme().flattenedName(Predef$.MODULE$.wrapRefArray((Object[])new Names.Name[]{this.rawowner().name(), this.rawname()}));
                }
                typeName = this.flatname;
            } else {
                typeName = this.rawname();
            }
            return typeName;
        }

        @Override
        public Symbol thisSym() {
            return this.thissym;
        }

        @Override
        public void typeOfThis_$eq(Types.Type tp) {
            this.thissym = this.newThisSym(this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().nme().this_(), this.pos()).setInfo(tp);
        }

        @Override
        public ClassSymbol cloneSymbolImpl(Symbol owner2, long newFlags) {
            Object object;
            ClassSymbol clone2 = owner2.newClassSymbol(this.name(), this.pos(), newFlags);
            Symbol symbol = this.thisSym();
            if (symbol != null && symbol.equals(this)) {
                object = BoxedUnit.UNIT;
            } else {
                clone2.typeOfThis_$eq(this.typeOfThis());
                object = clone2.thisSym().setName(this.thisSym().name());
            }
            clone2.associatedFile_$eq(this._associatedFile);
            return clone2;
        }

        @Override
        public Symbol derivedValueClassUnbox() {
            Option<Symbol> option = this.info().decls().find((Function1<Symbol, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Symbol x$42) {
                    return x$42.hasAllFlags(0x20000040L);
                }
            }));
            return !option.isEmpty() ? option.get() : this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().NoSymbol();
        }

        @Override
        public Set<Symbol> children() {
            return this.childSet;
        }

        @Override
        public void addChild(Symbol sym) {
            if (!this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().isPastTyper() && this.hasAttachment(ClassTag$.MODULE$.apply(StdAttachments$KnownDirectSubclassesCalled$.class)) && !this.childSet.contains(sym)) {
                this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().globalError(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"knownDirectSubclasses of ", " observed before subclass ", " registered"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.name(), sym.name()})));
            }
            this.childSet = (Set)this.childSet.$plus(sym);
        }

        public String anonOrRefinementString() {
            String string2;
            if (this.hasCompleteInfo()) {
                String label = this.isAnonymousClass() ? "$anon:" : "refinement of";
                String parents2 = this.scala$reflect$internal$Symbols$ClassSymbol$$$outer().definitions().parentsString((List)((TraversableLike)this.info().parents().map(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ ClassSymbol $outer;

                    public final Types.Type apply(Types.Type tp) {
                        return this.$outer.scala$reflect$internal$Symbols$ClassSymbol$$$outer().definitions().functionNBaseType(tp);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }, List$.MODULE$.canBuildFrom())).filterNot(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ ClassSymbol $outer;

                    public final boolean apply(Types.Type x$43) {
                        Symbol symbol = x$43.typeSymbol();
                        ClassSymbol classSymbol = this.$outer.scala$reflect$internal$Symbols$ClassSymbol$$$outer().definitions().SerializableClass();
                        return !(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }));
                string2 = new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"<", " ", ">"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{label, parents2}));
            } else {
                string2 = this.isAnonymousClass() ? "$anon" : this.nameString();
            }
            return string2;
        }

        @Override
        public String toString() {
            return this.isAnonOrRefinementClass() ? this.anonOrRefinementString() : super.toString();
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$ClassSymbol$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ scala.reflect.api.Symbols scala$reflect$api$Symbols$ClassSymbolApi$$$outer() {
            return this.scala$reflect$internal$Symbols$ClassSymbol$$$outer();
        }

        public ClassSymbol(SymbolTable $outer, Symbol initOwner, Position initPos, Names.TypeName initName) {
            super($outer, initOwner, initPos, initName);
            Symbols$ClassSymbolApi$class.$init$(this);
            this.thissym = this;
            this.thisTypePeriod = 0;
            this.childSet = (Set)Predef$.MODULE$.Set().apply(Nil$.MODULE$);
        }
    }

    public class TypeHistory
    implements Product,
    Serializable {
        private int validFrom;
        private final Types.Type info;
        private final TypeHistory prev;
        public final /* synthetic */ SymbolTable $outer;

        public int validFrom() {
            return this.validFrom;
        }

        public void validFrom_$eq(int x$1) {
            this.validFrom = x$1;
        }

        public Types.Type info() {
            return this.info;
        }

        public TypeHistory prev() {
            return this.prev;
        }

        public String scala$reflect$internal$Symbols$TypeHistory$$phaseString() {
            Predef$ predef$ = Predef$.MODULE$;
            return new StringOps("%s: %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$Symbols$TypeHistory$$$outer().phaseOf(this.validFrom()), this.info()}));
        }

        public String toString() {
            return ((TraversableOnce)this.toList().reverseMap(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final String apply(TypeHistory x$52) {
                    return x$52.scala$reflect$internal$Symbols$TypeHistory$$phaseString();
                }
            }, List$.MODULE$.canBuildFrom())).mkString(", ");
        }

        public List<TypeHistory> toList() {
            return (this.prev() == null ? Nil$.MODULE$ : this.prev().toList()).$colon$colon(this);
        }

        public TypeHistory oldest() {
            return this.prev() == null ? this : this.prev().oldest();
        }

        public TypeHistory copy(int validFrom, Types.Type info2, TypeHistory prev) {
            return new TypeHistory(this.scala$reflect$internal$Symbols$TypeHistory$$$outer(), validFrom, info2, prev);
        }

        public int copy$default$1() {
            return this.validFrom();
        }

        public Types.Type copy$default$2() {
            return this.info();
        }

        public TypeHistory copy$default$3() {
            return this.prev();
        }

        @Override
        public String productPrefix() {
            return "TypeHistory";
        }

        @Override
        public int productArity() {
            return 3;
        }

        @Override
        public Object productElement(int x$1) {
            Object object;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 2: {
                    object = this.prev();
                    break;
                }
                case 1: {
                    object = this.info();
                    break;
                }
                case 0: {
                    object = BoxesRunTime.boxToInteger(this.validFrom());
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
            return x$1 instanceof TypeHistory;
        }

        public int hashCode() {
            return Statics.finalizeHash(Statics.mix(Statics.mix(Statics.mix(-889275714, this.validFrom()), Statics.anyHash(this.info())), Statics.anyHash(this.prev())), 3);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object x$1) {
            if (this == x$1) return true;
            if (!(x$1 instanceof TypeHistory)) return false;
            boolean bl = true;
            if (!bl) return false;
            TypeHistory typeHistory = (TypeHistory)x$1;
            if (this.validFrom() != typeHistory.validFrom()) return false;
            Types.Type type = this.info();
            Types.Type type2 = typeHistory.info();
            if (type == null) {
                if (type2 != null) {
                    return false;
                }
            } else if (!type.equals(type2)) return false;
            TypeHistory typeHistory2 = this.prev();
            TypeHistory typeHistory3 = typeHistory.prev();
            if (typeHistory2 == null) {
                if (typeHistory3 != null) {
                    return false;
                }
            } else if (!((Object)typeHistory2).equals(typeHistory3)) return false;
            if (!typeHistory.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$TypeHistory$$$outer() {
            return this.$outer;
        }

        public TypeHistory(SymbolTable $outer, int validFrom, Types.Type info2, TypeHistory prev) {
            this.validFrom = validFrom;
            this.info = info2;
            this.prev = prev;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Product$class.$init$(this);
            boolean bl = prev == null || (this.validFrom() & 0xFF) > (prev.validFrom() & 0xFF);
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(this).toString());
            }
            boolean bl2 = this.validFrom() != 0;
            Predef$ predef$2 = Predef$.MODULE$;
            if (!bl2) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(this).toString());
            }
        }
    }

    public class ModuleSymbol
    extends TermSymbol
    implements Symbols.ModuleSymbolApi {
        private Names.TermName flatname;

        @Override
        public final boolean isModule() {
            return Symbols$ModuleSymbolApi$class.isModule(this);
        }

        @Override
        public final Symbols.ModuleSymbolApi asModule() {
            return Symbols$ModuleSymbolApi$class.asModule(this);
        }

        private Names.TermName flatname() {
            return this.flatname;
        }

        private void flatname_$eq(Names.TermName x$1) {
            this.flatname = x$1;
        }

        @Override
        public AbstractFile associatedFile() {
            return this.moduleClass().associatedFile();
        }

        @Override
        public void associatedFile_$eq(AbstractFile f) {
            this.moduleClass().associatedFile_$eq(f);
        }

        @Override
        public Symbol moduleClass() {
            return this.referenced();
        }

        @Override
        public Symbol companionClass() {
            return this.flatOwnerInfo().decl(this.name().toTypeName()).suchThat((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ModuleSymbol $outer;

                public final boolean apply(Symbol sym) {
                    return sym.isClass() && sym.isCoDefinedWith(this.$outer);
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
        public Symbol owner() {
            return !this.isMethod() && this.needsFlatClasses() ? this.rawowner().owner() : this.rawowner();
        }

        @Override
        public Names.TermName name() {
            Names.TermName termName;
            if (!this.isMethod() && this.needsFlatClasses()) {
                if (this.flatname() == null) {
                    this.flatname_$eq((Names.TermName)this.scala$reflect$internal$Symbols$ModuleSymbol$$$outer().nme().flattenedName(Predef$.MODULE$.wrapRefArray((Object[])new Names.Name[]{this.rawowner().name(), this.rawname()})));
                }
                termName = this.flatname();
            } else {
                termName = this.rawname();
            }
            return termName;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$ModuleSymbol$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ scala.reflect.api.Symbols scala$reflect$api$Symbols$ModuleSymbolApi$$$outer() {
            return this.scala$reflect$internal$Symbols$ModuleSymbol$$$outer();
        }

        public ModuleSymbol(SymbolTable $outer, Symbol initOwner, Position initPos, Names.TermName initName) {
            super($outer, initOwner, initPos, initName);
            Symbols$ModuleSymbolApi$class.$init$(this);
            this.flatname = null;
        }
    }

    public class MethodSymbol
    extends TermSymbol
    implements Symbols.MethodSymbolApi {
        private int mtpePeriod;
        private Types.Type mtpePre;
        private Types.Type mtpeResult;
        private Types.Type mtpeInfo;

        @Override
        public final boolean isMethod() {
            return Symbols$MethodSymbolApi$class.isMethod(this);
        }

        @Override
        public final Symbols.MethodSymbolApi asMethod() {
            return Symbols$MethodSymbolApi$class.asMethod(this);
        }

        @Override
        public boolean isLabel() {
            return this.hasFlag(131072);
        }

        @Override
        public boolean isVarargsMethod() {
            return this.hasFlag(0x80000000000L);
        }

        @Override
        public boolean isLiftedMethod() {
            return this.hasFlag(0x400000000L);
        }

        @Override
        public boolean isSourceMethod() {
            return !this.hasFlag(0x400000);
        }

        @Override
        public boolean isCaseAccessorMethod() {
            return this.isCaseAccessor();
        }

        /*
         * WARNING - void declaration
         */
        public Types.Type typeAsMemberOf(Types.Type pre) {
            void var2_2;
            if (this.mtpePeriod == this.scala$reflect$internal$Symbols$MethodSymbol$$$outer().currentPeriod()) {
                if (this.mtpePre == pre && this.mtpeInfo == this.info()) {
                    return this.mtpeResult;
                }
            } else if (this.scala$reflect$internal$Symbols$MethodSymbol$$$outer().isValid(this.mtpePeriod)) {
                this.mtpePeriod = this.scala$reflect$internal$Symbols$MethodSymbol$$$outer().currentPeriod();
                if (this.mtpePre == pre && this.mtpeInfo == this.info()) {
                    return this.mtpeResult;
                }
            }
            Types.Type res = pre.computeMemberType(this);
            this.mtpePeriod = this.scala$reflect$internal$Symbols$MethodSymbol$$$outer().currentPeriod();
            this.mtpePre = pre;
            this.mtpeInfo = this.info();
            this.mtpeResult = res;
            return var2_2;
        }

        @Override
        public boolean isVarargs() {
            return this.scala$reflect$internal$Symbols$MethodSymbol$$$outer().definitions().isVarArgsList((Seq)this.paramss().flatten((Function1)Predef$.MODULE$.$conforms()));
        }

        @Override
        public Types.Type returnType() {
            return this.loop$5(this.info());
        }

        public List<Symbol> exceptions() {
            return this.annotations().flatMap(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ MethodSymbol $outer;

                public final Iterable<Symbol> apply(AnnotationInfos.AnnotationInfo ann) {
                    return Option$.MODULE$.option2Iterable(this.$outer.scala$reflect$internal$Symbols$MethodSymbol$$$outer().ThrownException().unapply(ann));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom());
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$MethodSymbol$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ scala.reflect.api.Symbols scala$reflect$api$Symbols$MethodSymbolApi$$$outer() {
            return this.scala$reflect$internal$Symbols$MethodSymbol$$$outer();
        }

        private final Types.Type loop$5(Types.Type tpe) {
            while (true) {
                if (tpe instanceof Types.NullaryMethodType) {
                    Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)tpe;
                    tpe = nullaryMethodType.resultType();
                    continue;
                }
                if (tpe instanceof Types.MethodType) {
                    Types.MethodType methodType = (Types.MethodType)tpe;
                    tpe = methodType.resultType();
                    continue;
                }
                if (!(tpe instanceof Types.PolyType)) break;
                Types.PolyType polyType = (Types.PolyType)tpe;
                tpe = polyType.resultType();
            }
            return tpe;
        }

        public MethodSymbol(SymbolTable $outer, Symbol initOwner, Position initPos, Names.TermName initName) {
            super($outer, initOwner, initPos, initName);
            Symbols$MethodSymbolApi$class.$init$(this);
            this.mtpePeriod = 0;
        }
    }

    public class FreeTermSymbol
    extends TermSymbol
    implements FreeSymbol,
    Internals.FreeTermSymbolApi {
        private final Function0<Object> value0;
        private final String origin;

        @Override
        public String origin() {
            return this.origin;
        }

        @Override
        public final boolean isFreeTerm() {
            return true;
        }

        @Override
        public final FreeTermSymbol asFreeTerm() {
            return this;
        }

        @Override
        public Object value() {
            return this.value0.apply();
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$FreeTermSymbol$$$outer() {
            return this.$outer;
        }

        public FreeTermSymbol(SymbolTable $outer, Names.TermName name0, Function0<Object> value0, String origin) {
            this.value0 = value0;
            this.origin = origin;
            super($outer, (Symbol)$outer.NoSymbol(), (Position)$outer.NoPosition(), name0);
        }
    }

    public class FreeTypeSymbol
    extends TypeSkolem
    implements FreeSymbol,
    Internals.FreeTypeSymbolApi {
        private final String origin;

        @Override
        public String origin() {
            return this.origin;
        }

        @Override
        public final boolean isFreeType() {
            return true;
        }

        @Override
        public final FreeTypeSymbol asFreeType() {
            return this;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$FreeTypeSymbol$$$outer() {
            return this.$outer;
        }

        public FreeTypeSymbol(SymbolTable $outer, Names.TypeName name0, String origin) {
            this.origin = origin;
            super($outer, $outer.NoSymbol(), $outer.NoPosition(), name0, $outer.NoSymbol());
        }
    }

    public class StubTermSymbol
    extends TermSymbol
    implements StubSymbol {
        private final String missingMessage;

        @Override
        public final Nothing$ failIfStub() {
            return Symbols$StubSymbol$class.failIfStub(this);
        }

        @Override
        public Types$NoType$ originalInfo() {
            return Symbols$StubSymbol$class.originalInfo(this);
        }

        @Override
        public AbstractFile associatedFile() {
            return Symbols$StubSymbol$class.associatedFile(this);
        }

        @Override
        public Types$NoType$ info() {
            return Symbols$StubSymbol$class.info(this);
        }

        @Override
        public Types$NoType$ rawInfo() {
            return Symbols$StubSymbol$class.rawInfo(this);
        }

        @Override
        public NoSymbol companionSymbol() {
            return Symbols$StubSymbol$class.companionSymbol(this);
        }

        @Override
        public String missingMessage() {
            return this.missingMessage;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$StubTermSymbol$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ Symbols scala$reflect$internal$Symbols$StubSymbol$$$outer() {
            return this.scala$reflect$internal$Symbols$StubTermSymbol$$$outer();
        }

        public StubTermSymbol(SymbolTable $outer, Symbol owner0, Names.TermName name0, String missingMessage) {
            this.missingMessage = missingMessage;
            super($outer, owner0, owner0.pos(), name0);
            Symbols$StubSymbol$class.$init$(this);
        }
    }

    public class AliasTypeSymbol
    extends TypeSymbol {
        @Override
        public int variance() {
            return this.isLocalToThis() ? Variance$.MODULE$.Bivariant() : this.info().typeSymbol().variance();
        }

        @Override
        public boolean isContravariant() {
            return Variance$.MODULE$.isContravariant$extension(this.variance());
        }

        @Override
        public boolean isCovariant() {
            return Variance$.MODULE$.isCovariant$extension(this.variance());
        }

        @Override
        public final boolean isAliasType() {
            return true;
        }

        @Override
        public TypeSymbol cloneSymbolImpl(Symbol owner2, long newFlags) {
            return owner2.newNonClassSymbol(this.name(), this.pos(), newFlags);
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$AliasTypeSymbol$$$outer() {
            return this.$outer;
        }

        public AliasTypeSymbol(SymbolTable $outer, Symbol initOwner, Position initPos, Names.TypeName initName) {
            super($outer, initOwner, initPos, initName);
        }
    }

    public interface ImplClassSymbol {
        public Symbol sourceModule();

        public Types.Type typeOfThis();

        public /* synthetic */ Symbols scala$reflect$internal$Symbols$ImplClassSymbol$$$outer();
    }

    public class StubClassSymbol
    extends ClassSymbol
    implements StubSymbol {
        private final String missingMessage;

        @Override
        public final Nothing$ failIfStub() {
            return Symbols$StubSymbol$class.failIfStub(this);
        }

        @Override
        public Types$NoType$ originalInfo() {
            return Symbols$StubSymbol$class.originalInfo(this);
        }

        @Override
        public AbstractFile associatedFile() {
            return Symbols$StubSymbol$class.associatedFile(this);
        }

        @Override
        public Types$NoType$ info() {
            return Symbols$StubSymbol$class.info(this);
        }

        @Override
        public Types$NoType$ rawInfo() {
            return Symbols$StubSymbol$class.rawInfo(this);
        }

        @Override
        public NoSymbol companionSymbol() {
            return Symbols$StubSymbol$class.companionSymbol(this);
        }

        @Override
        public String missingMessage() {
            return this.missingMessage;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$StubClassSymbol$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ Symbols scala$reflect$internal$Symbols$StubSymbol$$$outer() {
            return this.scala$reflect$internal$Symbols$StubClassSymbol$$$outer();
        }

        public StubClassSymbol(SymbolTable $outer, Symbol owner0, Names.TypeName name0, String missingMessage) {
            this.missingMessage = missingMessage;
            super($outer, owner0, owner0.pos(), name0);
            Symbols$StubSymbol$class.$init$(this);
        }
    }

    public class CyclicReference
    extends Types.TypeError
    implements Product,
    Serializable {
        private final Symbol sym;
        private final Types.Type info;
        public final /* synthetic */ SymbolTable $outer;

        public Symbol sym() {
            return this.sym;
        }

        public Types.Type info() {
            return this.info;
        }

        public CyclicReference copy(Symbol sym, Types.Type info2) {
            return new CyclicReference(this.scala$reflect$internal$Symbols$CyclicReference$$$outer(), sym, info2);
        }

        public Symbol copy$default$1() {
            return this.sym();
        }

        public Types.Type copy$default$2() {
            return this.info();
        }

        @Override
        public String productPrefix() {
            return "CyclicReference";
        }

        @Override
        public int productArity() {
            return 2;
        }

        @Override
        public Object productElement(int x$1) {
            AnnotationInfos.Annotatable<Types.Type> annotatable;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 1: {
                    annotatable = this.info();
                    break;
                }
                case 0: {
                    annotatable = this.sym();
                }
            }
            return annotatable;
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof CyclicReference;
        }

        public int hashCode() {
            return ScalaRunTime$.MODULE$._hashCode(this);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object x$1) {
            if (this == x$1) return true;
            if (!(x$1 instanceof CyclicReference)) return false;
            if (((CyclicReference)x$1).scala$reflect$internal$Symbols$CyclicReference$$$outer() != this.scala$reflect$internal$Symbols$CyclicReference$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            CyclicReference cyclicReference = (CyclicReference)x$1;
            Symbol symbol = this.sym();
            Symbol symbol2 = cyclicReference.sym();
            if (symbol == null) {
                if (symbol2 != null) {
                    return false;
                }
            } else if (!symbol.equals(symbol2)) return false;
            Types.Type type = this.info();
            Types.Type type2 = cyclicReference.info();
            if (type == null) {
                if (type2 != null) {
                    return false;
                }
            } else if (!type.equals(type2)) return false;
            if (!cyclicReference.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$CyclicReference$$$outer() {
            return this.$outer;
        }

        public CyclicReference(SymbolTable $outer, Symbol sym, Types.Type info2) {
            this.sym = sym;
            this.info = info2;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            super($outer, new StringBuilder().append((Object)"illegal cyclic reference involving ").append(sym).toString());
            Product$class.$init$(this);
            if (BoxesRunTime.unboxToBoolean($outer.settings().debug().value())) {
                this.printStackTrace();
            }
        }
    }

    public class ModuleClassSymbol
    extends ClassSymbol {
        private final Symbol owner;
        private Symbol module;
        private Types.Type typeOfThisCache;
        private int typeOfThisPeriod;
        private Scopes.Scope implicitMembersCacheValue;
        private Types.Type implicitMembersCacheKey1;
        private Scopes.ScopeEntry implicitMembersCacheKey2;

        private Scopes.Scope implicitMembersCacheValue() {
            return this.implicitMembersCacheValue;
        }

        private void implicitMembersCacheValue_$eq(Scopes.Scope x$1) {
            this.implicitMembersCacheValue = x$1;
        }

        private Types.Type implicitMembersCacheKey1() {
            return this.implicitMembersCacheKey1;
        }

        private void implicitMembersCacheKey1_$eq(Types.Type x$1) {
            this.implicitMembersCacheKey1 = x$1;
        }

        private Scopes.ScopeEntry implicitMembersCacheKey2() {
            return this.implicitMembersCacheKey2;
        }

        private void implicitMembersCacheKey2_$eq(Scopes.ScopeEntry x$1) {
            this.implicitMembersCacheKey2 = x$1;
        }

        @Override
        public boolean isModuleClass() {
            return true;
        }

        @Override
        public Symbol linkedClassOfClass() {
            return this.companionClass();
        }

        @Override
        public Types.Type typeOfThis() {
            int period = this.typeOfThisPeriod;
            if (period != this.scala$reflect$internal$Symbols$ModuleClassSymbol$$$outer().currentPeriod()) {
                if (!this.scala$reflect$internal$Symbols$ModuleClassSymbol$$$outer().isValid(period)) {
                    this.typeOfThisCache = this.scala$reflect$internal$Symbols$ModuleClassSymbol$$$outer().singleType(this.owner.thisType(), this.sourceModule());
                }
                this.typeOfThisPeriod = this.scala$reflect$internal$Symbols$ModuleClassSymbol$$$outer().currentPeriod();
            }
            return this.typeOfThisCache;
        }

        public Scopes.Scope implicitMembers() {
            Types.Type tp = this.info();
            if (!(this.implicitMembersCacheKey1() == tp && this.implicitMembersCacheKey2() == tp.decls().elems() || this.isPackageObjectClass())) {
                this.implicitMembersCacheValue_$eq(tp.implicitMembers());
                this.implicitMembersCacheKey1_$eq(tp);
                this.implicitMembersCacheKey2_$eq(tp.decls().elems());
            }
            return this.implicitMembersCacheValue();
        }

        @Override
        public Symbol sourceModule() {
            return this.module != null ? this.module : this.companionModule();
        }

        @Override
        public void sourceModule_$eq(Symbol module) {
            this.module = module;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$ModuleClassSymbol$$$outer() {
            return this.$outer;
        }

        public ModuleClassSymbol(SymbolTable $outer, Symbol owner2, Position pos, Names.TypeName name) {
            this.owner = owner2;
            super($outer, owner2, pos, name);
            this.typeOfThisPeriod = 0;
            this.implicitMembersCacheValue = $outer.EmptyScope();
            this.implicitMembersCacheKey1 = $outer.NoType();
            this.implicitMembersCacheKey2 = null;
        }
    }

    public class AbstractTypeSymbol
    extends TypeSymbol {
        @Override
        public final boolean isAbstractType() {
            return true;
        }

        @Override
        public Types.Type existentialBound() {
            return this.info();
        }

        @Override
        public TypeSymbol cloneSymbolImpl(Symbol owner2, long newFlags) {
            return owner2.newNonClassSymbol(this.name(), this.pos(), newFlags);
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$AbstractTypeSymbol$$$outer() {
            return this.$outer;
        }

        public AbstractTypeSymbol(SymbolTable $outer, Symbol initOwner, Position initPos, Names.TypeName initName) {
            super($outer, initOwner, initPos, initName);
        }
    }

    public class PackageClassSymbol
    extends ModuleClassSymbol {
        @Override
        public Symbol sourceModule() {
            return this.companionModule();
        }

        public Nil$ enclClassChain() {
            return Nil$.MODULE$;
        }

        @Override
        public boolean isPackageClass() {
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$PackageClassSymbol$$$outer() {
            return this.$outer;
        }

        public PackageClassSymbol(SymbolTable $outer, Symbol owner0, Position pos0, Names.TypeName name0) {
            super($outer, owner0, pos0, name0);
        }
    }

    public static abstract class SymbolContextApiImpl
    implements Symbols.SymbolApi {
        public final /* synthetic */ SymbolTable $outer;

        @Override
        public boolean isType() {
            return Symbols$SymbolApi$class.isType(this);
        }

        @Override
        public Symbols.TypeSymbolApi asType() {
            return Symbols$SymbolApi$class.asType(this);
        }

        @Override
        public boolean isTerm() {
            return Symbols$SymbolApi$class.isTerm(this);
        }

        @Override
        public Symbols.TermSymbolApi asTerm() {
            return Symbols$SymbolApi$class.asTerm(this);
        }

        @Override
        public boolean isMethod() {
            return Symbols$SymbolApi$class.isMethod(this);
        }

        @Override
        public Symbols.MethodSymbolApi asMethod() {
            return Symbols$SymbolApi$class.asMethod(this);
        }

        @Override
        public boolean isOverloadedMethod() {
            return Symbols$SymbolApi$class.isOverloadedMethod(this);
        }

        @Override
        public boolean isModule() {
            return Symbols$SymbolApi$class.isModule(this);
        }

        @Override
        public Symbols.ModuleSymbolApi asModule() {
            return Symbols$SymbolApi$class.asModule(this);
        }

        @Override
        public boolean isClass() {
            return Symbols$SymbolApi$class.isClass(this);
        }

        @Override
        public boolean isModuleClass() {
            return Symbols$SymbolApi$class.isModuleClass(this);
        }

        @Override
        public Symbols.ClassSymbolApi asClass() {
            return Symbols$SymbolApi$class.asClass(this);
        }

        public boolean isFreeTerm() {
            return false;
        }

        public FreeTermSymbol asFreeTerm() {
            throw new ScalaReflectionException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " is not a free term"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this})));
        }

        public boolean isFreeType() {
            return false;
        }

        public FreeTypeSymbol asFreeType() {
            throw new ScalaReflectionException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " is not a free type"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this})));
        }

        public boolean isExistential() {
            return ((Symbol)this).isExistentiallyBound();
        }

        public boolean isParamWithDefault() {
            return ((HasFlags)((Object)this)).hasDefault();
        }

        public boolean isByNameParam() {
            return ((Symbol)this).isValueParameter() && ((Symbol)this).hasFlag(65536);
        }

        @Override
        public boolean isImplementationArtifact() {
            return ((Symbol)this).hasFlag(0x4000000) || ((Symbol)this).hasFlag(0x40000000000L) || ((Symbol)this).hasFlag(0x400000000000L);
        }

        @Override
        public boolean isJava() {
            return ((HasFlags)((Object)this)).isJavaDefined();
        }

        public boolean isVal() {
            return this.isTerm() && !this.isModule() && !this.isMethod() && !((HasFlags)((Object)this)).isMutable();
        }

        public boolean isVar() {
            return this.isTerm() && !this.isModule() && !this.isMethod() && !((HasFlags)((Object)this)).isLazy() && ((HasFlags)((Object)this)).isMutable();
        }

        @Override
        public boolean isAbstract() {
            return ((Symbol)this).isAbstractClass() || ((HasFlags)((Object)this)).isDeferred() || ((Symbol)this).isAbstractType();
        }

        @Override
        public boolean isPrivateThis() {
            return ((Symbol)this).hasFlag(4) && ((Symbol)this).hasFlag(524288);
        }

        @Override
        public boolean isProtectedThis() {
            return ((Symbol)this).hasFlag(1) && ((Symbol)this).hasFlag(524288);
        }

        public Symbol newNestedSymbol(Names.Name name, Position pos, long newFlags, boolean isClass) {
            block4: {
                Symbol symbol;
                block3: {
                    block2: {
                        if (!(name instanceof Names.TermName)) break block2;
                        Names.TermName termName = (Names.TermName)name;
                        symbol = ((Symbol)this).newTermSymbol(termName, pos, newFlags);
                        break block3;
                    }
                    if (!(name instanceof Names.TypeName)) break block4;
                    Names.TypeName typeName = (Names.TypeName)name;
                    symbol = isClass ? ((Symbol)this).newClassSymbol(typeName, pos, newFlags) : ((Symbol)this).newNonClassSymbol(typeName, pos, newFlags);
                }
                return symbol;
            }
            throw new MatchError(name);
        }

        public Set<Symbol> knownDirectSubclasses() {
            Object object = this.scala$reflect$internal$Symbols$SymbolContextApiImpl$$$outer().isCompilerUniverse() || ((Symbol)this).isThreadsafe(this.scala$reflect$internal$Symbols$SymbolContextApiImpl$$$outer().AllOps()) ? BoxedUnit.UNIT : ((Symbol)this).initialize();
            ((Symbol)this).enclosingPackage().info().decls().foreach(new Serializable((Symbol)this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Symbol $outer;

                public final void apply(Symbol sym) {
                    AbstractFile abstractFile = this.$outer.sourceFile();
                    AbstractFile abstractFile2 = sym.sourceFile();
                    if (!(abstractFile != null ? !abstractFile.equals(abstractFile2) : abstractFile2 != null)) {
                        sym.rawInfo().forceDirectSuperclasses();
                    }
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            });
            Object object2 = this.scala$reflect$internal$Symbols$SymbolContextApiImpl$$$outer().isPastTyper() ? BoxedUnit.UNIT : ((StdAttachments.Attachable)((Object)this)).updateAttachment(this.scala$reflect$internal$Symbols$SymbolContextApiImpl$$$outer().KnownDirectSubclassesCalled(), ClassTag$.MODULE$.apply(StdAttachments$KnownDirectSubclassesCalled$.class));
            return ((Symbol)this).children();
        }

        public Types.Type selfType() {
            Object object = this.scala$reflect$internal$Symbols$SymbolContextApiImpl$$$outer().isCompilerUniverse() || ((Symbol)this).isThreadsafe(this.scala$reflect$internal$Symbols$SymbolContextApiImpl$$$outer().AllOps()) ? BoxedUnit.UNIT : ((Symbol)this).initialize();
            return ((Symbol)this).typeOfThis();
        }

        public List<Symbol> baseClasses() {
            return ((Symbol)this).info().baseClasses();
        }

        public Symbol module() {
            return ((Symbol)this).sourceModule();
        }

        public Types.Type thisPrefix() {
            return ((Symbol)this).thisType();
        }

        public Types.Type superPrefix(Types.Type supertpe) {
            return this.scala$reflect$internal$Symbols$SymbolContextApiImpl$$$outer().SuperType().apply(((Symbol)this).thisType(), supertpe);
        }

        @Override
        public Types.Type typeSignature() {
            return ((Symbol)this).info();
        }

        public Types.Type typeSignatureIn(Types.Type site) {
            return site.memberInfo((Symbol)this);
        }

        public Types.Type toType() {
            return ((Symbol)this).tpe();
        }

        public Types.Type toTypeIn(Types.Type site) {
            return site.memberType((Symbol)this);
        }

        public Types.Type toTypeConstructor() {
            return ((Symbol)this).typeConstructor();
        }

        public Symbol setAnnotations(Seq<AnnotationInfos.AnnotationInfo> annots) {
            ((Symbol)this).setAnnotations(annots.toList());
            return (Symbol)this;
        }

        public Symbol getter() {
            return ((Symbol)this).getterIn(((Symbol)this).owner());
        }

        public Symbol setter() {
            return ((Symbol)this).setterIn(((Symbol)this).owner(), ((Symbol)this).setterIn$default$2());
        }

        @Override
        public Symbol companion() {
            return this.isModule() && !((HasFlags)((Object)this)).hasPackageFlag() ? ((Symbol)this).companionSymbol() : (this.isModuleClass() && !((Symbol)this).isPackageClass() ? ((Symbol)this).sourceModule().companionSymbol() : (this.isClass() && !this.isModuleClass() && !((Symbol)this).isPackageClass() ? ((Symbol)this).companionSymbol() : this.scala$reflect$internal$Symbols$SymbolContextApiImpl$$$outer().NoSymbol()));
        }

        public Types.Type infoIn(Types.Type site) {
            return this.typeSignatureIn(site);
        }

        public List<Symbol> overrides() {
            return ((Symbol)this).allOverriddenSymbols();
        }

        public List<List<Symbol>> paramLists() {
            return ((Symbol)this).paramss();
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$SymbolContextApiImpl$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ scala.reflect.api.Symbols scala$reflect$api$Symbols$SymbolApi$$$outer() {
            return this.scala$reflect$internal$Symbols$SymbolContextApiImpl$$$outer();
        }

        public SymbolContextApiImpl(SymbolTable $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Symbols$SymbolApi$class.$init$(this);
        }
    }

    public class RefinementClassSymbol
    extends ClassSymbol {
        @Override
        public void name_$eq(Names.Name name) {
            throw this.scala$reflect$internal$Symbols$RefinementClassSymbol$$$outer().abort(new StringBuilder().append((Object)"Cannot set name of RefinementClassSymbol to ").append(name).toString());
        }

        @Override
        public boolean isRefinementClass() {
            return true;
        }

        @Override
        public boolean isAnonOrRefinementClass() {
            return true;
        }

        @Override
        public boolean isLocalClass() {
            return true;
        }

        @Override
        public boolean hasMeaninglessName() {
            return true;
        }

        @Override
        public Symbol companionModule() {
            return this.scala$reflect$internal$Symbols$RefinementClassSymbol$$$outer().NoSymbol();
        }

        @Override
        public boolean hasTransOwner(Symbol sym) {
            return super.hasTransOwner(sym) || this.info().parents().exists((Function1<Types.Type, Object>)((Object)new Serializable(this, sym){
                public static final long serialVersionUID = 0L;
                private final Symbol sym$2;

                public final boolean apply(Types.Type x$44) {
                    return x$44.typeSymbol().hasTransOwner(this.sym$2);
                }
                {
                    this.sym$2 = sym$2;
                }
            }));
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$RefinementClassSymbol$$$outer() {
            return this.$outer;
        }

        public RefinementClassSymbol(SymbolTable $outer, Symbol owner0, Position pos0) {
            super($outer, owner0, pos0, $outer.tpnme().REFINE_CLASS_NAME());
        }
    }

    public class StubPackageClassSymbol
    extends PackageClassSymbol
    implements StubSymbol {
        private final String missingMessage;

        @Override
        public final Nothing$ failIfStub() {
            return Symbols$StubSymbol$class.failIfStub(this);
        }

        @Override
        public Types$NoType$ originalInfo() {
            return Symbols$StubSymbol$class.originalInfo(this);
        }

        @Override
        public AbstractFile associatedFile() {
            return Symbols$StubSymbol$class.associatedFile(this);
        }

        @Override
        public Types$NoType$ info() {
            return Symbols$StubSymbol$class.info(this);
        }

        @Override
        public Types$NoType$ rawInfo() {
            return Symbols$StubSymbol$class.rawInfo(this);
        }

        @Override
        public NoSymbol companionSymbol() {
            return Symbols$StubSymbol$class.companionSymbol(this);
        }

        @Override
        public String missingMessage() {
            return this.missingMessage;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$StubPackageClassSymbol$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ Symbols scala$reflect$internal$Symbols$StubSymbol$$$outer() {
            return this.scala$reflect$internal$Symbols$StubPackageClassSymbol$$$outer();
        }

        public StubPackageClassSymbol(SymbolTable $outer, Symbol owner0, Names.TypeName name0, String missingMessage) {
            this.missingMessage = missingMessage;
            super($outer, owner0, owner0.pos(), name0);
            Symbols$StubSymbol$class.$init$(this);
        }
    }

    public class PackageObjectClassSymbol
    extends ModuleClassSymbol {
        @Override
        public final boolean isPackageObjectClass() {
            return true;
        }

        @Override
        public final boolean isPackageObjectOrClass() {
            return true;
        }

        @Override
        public final Symbol skipPackageObject() {
            return this.owner();
        }

        @Override
        public final PackageObjectClassSymbol setName(Names.Name name) {
            throw this.scala$reflect$internal$Symbols$PackageObjectClassSymbol$$$outer().abort(new StringBuilder().append((Object)"Can't rename a package object to ").append(name).toString());
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Symbols$PackageObjectClassSymbol$$$outer() {
            return this.$outer;
        }

        public PackageObjectClassSymbol(SymbolTable $outer, Symbol owner0, Position pos0) {
            super($outer, owner0, pos0, (Names.TypeName)$outer.tpnme().PACKAGE());
        }
    }
}

