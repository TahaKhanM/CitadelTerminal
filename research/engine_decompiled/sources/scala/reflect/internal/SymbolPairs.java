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
import scala.collection.AbstractIterable;
import scala.collection.BufferedIterator;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Iterator$class;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolPairs$Cursor$;
import scala.reflect.internal.SymbolPairs$SymbolPair$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.HashSet;
import scala.reflect.internal.util.HashSet$;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.StripMarginInterpolator;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing$;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\rud!B\u0001\u0003\u0003\u0003I!aC*z[\n|G\u000eU1jeNT!a\u0001\u0003\u0002\u0011%tG/\u001a:oC2T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\r\u001b\u00051\u0011BA\u0007\u0007\u0005\u0019\te.\u001f*fM\")q\u0002\u0001C\u0001!\u00051A(\u001b8jiz\"\u0012!\u0005\t\u0003%\u0001i\u0011A\u0001\u0005\b)\u0001\u0011\rQ\"\u0001\u0016\u0003\u00199Gn\u001c2bYV\ta\u0003\u0005\u0002\u0013/%\u0011\u0001D\u0001\u0002\f'fl'm\u001c7UC\ndWM\u0002\u0003\u001b\u0001\u0001Y\"A\u0003*fY\u0006$\u0018N^3U_N\u0011\u0011D\u0003\u0005\t;e\u0011)\u0019!C\u0001=\u00051\u0001O]3gSb,\u0012a\b\t\u0003A\tr!!I\n\u000e\u0003\u0001I!a\t\u0013\u0003\tQK\b/Z\u0005\u0003K\t\u0011Q\u0001V=qKND\u0001bJ\r\u0003\u0002\u0003\u0006IaH\u0001\baJ,g-\u001b=!\u0011\u0015y\u0011\u0004\"\u0001*)\tQ3\u0006\u0005\u0002\"3!)Q\u0004\u000ba\u0001?!)q\"\u0007C\u0001[Q\u0011!F\f\u0005\u0006_1\u0002\r\u0001M\u0001\u0006G2\f'P\u001f\t\u0003AEJ!AM\u001a\u0003\rMKXNY8m\u0013\t!$AA\u0004Ts6\u0014w\u000e\\:\t\u000bYJB1B\u001c\u0002\u0019MLXNY8m)>$\u0016\u0010]3\u0015\u0005}A\u0004\"B\u001d6\u0001\u0004\u0001\u0014aA:z[\")1(\u0007C\u0001y\u0005IQM]1tkJ,wJ\u001a\u000b\u0003?uBQ!\u000f\u001eA\u0002ABQaP\r\u0005\u0002\u0001\u000b\u0011b]5h]\u0006$XO]3\u0015\u0005\u0005C\u0005C\u0001\"F\u001d\tY1)\u0003\u0002E\r\u00051\u0001K]3eK\u001aL!AR$\u0003\rM#(/\u001b8h\u0015\t!e\u0001C\u0003:}\u0001\u0007\u0001\u0007C\u0003K3\u0011\u00051*A\bfe\u0006\u001cX\rZ*jO:\fG/\u001e:f)\t\tE\nC\u0003:\u0013\u0002\u0007\u0001\u0007C\u0003O3\u0011\u0005q*\u0001\u0006jgN\u000bW.\u001a+za\u0016$2\u0001U*V!\tY\u0011+\u0003\u0002S\r\t9!i\\8mK\u0006t\u0007\"\u0002+N\u0001\u0004\u0001\u0014\u0001B:z[FBQAV'A\u0002A\nAa]=ne!)\u0001,\u0007C\u00013\u0006I\u0011n]*vERK\b/\u001a\u000b\u0004!j[\u0006\"\u0002+X\u0001\u0004\u0001\u0004\"\u0002,X\u0001\u0004\u0001\u0004\"B/\u001a\t\u0003q\u0016aC5t'V\u0004XM\u001d+za\u0016$2\u0001U0a\u0011\u0015!F\f1\u00011\u0011\u00151F\f1\u00011\u0011\u0015\u0011\u0017\u0004\"\u0001d\u00035I7oU1nK\u0016\u0013\u0018m];sKR\u0019\u0001\u000bZ3\t\u000bQ\u000b\u0007\u0019\u0001\u0019\t\u000bY\u000b\u0007\u0019\u0001\u0019\t\u000b\u001dLB\u0011\u00015\u0002\u000f5\fGo\u00195fgR\u0019\u0001+\u001b6\t\u000bQ3\u0007\u0019\u0001\u0019\t\u000bY3\u0007\u0019\u0001\u0019\t\u000b1LB\u0011I7\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!\u0011\u0005\u0006_\u0002!\t\u0001]\u0001\u0010g\u0006lW-\u00138CCN,7\t\\1tgR\u0011\u0011O\u001e\u000b\u0004!J$\b\"B:o\u0001\u0004y\u0012a\u0001;qc!)QO\u001ca\u0001?\u0005\u0019A\u000f\u001d\u001a\t\u000b]t\u0007\u0019\u0001\u0019\u0002\u0013\t\f7/Z\"mCN\u001ch\u0001B=\u0001\u0001j\u0014!bU=nE>d\u0007+Y5s'\u0011A(b\u001f@\u0011\u0005-a\u0018BA?\u0007\u0005\u001d\u0001&o\u001c3vGR\u0004\"aC@\n\u0007\u0005\u0005aA\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0003\u0006\u0002\u0006a\u0014)\u001a!C\u0001\u0003\u000f\tAAY1tKV\t\u0001\u0007C\u0005\u0002\fa\u0014\t\u0012)A\u0005a\u0005)!-Y:fA!Q\u0011q\u0002=\u0003\u0016\u0004%\t!a\u0002\u0002\u00071|w\u000fC\u0005\u0002\u0014a\u0014\t\u0012)A\u0005a\u0005!An\\<!\u0011)\t9\u0002\u001fBK\u0002\u0013\u0005\u0011qA\u0001\u0005Q&<\u0007\u000eC\u0005\u0002\u001ca\u0014\t\u0012)A\u0005a\u0005)\u0001.[4iA!1q\u0002\u001fC\u0001\u0003?!\u0002\"!\t\u0002$\u0005\u0015\u0012q\u0005\t\u0003CaDq!!\u0002\u0002\u001e\u0001\u0007\u0001\u0007C\u0004\u0002\u0010\u0005u\u0001\u0019\u0001\u0019\t\u000f\u0005]\u0011Q\u0004a\u0001a!9\u00111\u0006=\u0005\u0002\u00055\u0012a\u00019pgV\u0011\u0011q\u0006\t\u0004A\u0005E\u0012\u0002BA\u001a\u0003k\u0011\u0001\u0002U8tSRLwN\\\u0005\u0004\u0003o\u0011!!\u0003)pg&$\u0018n\u001c8t\u0011\u0019\tY\u0004\u001fC\u0001=\u0005!1/\u001a7g\u0011\u0019\ty\u0004\u001fC\u0001=\u0005A!o\\8u)f\u0004X\r\u0003\u0004\u0002Da$\tAH\u0001\bY><H+\u001f9f\u0011\u0019\t9\u0005\u001fC\u0001=\u0005IAn\\<Fe\u0006\u001cX\r\u001a\u0005\u0007\u0003\u0017BH\u0011\u0001\u0010\u0002\u001b1|wo\u00117bgN\u0014u.\u001e8e\u0011\u0019\ty\u0005\u001fC\u0001=\u0005A\u0001.[4i)f\u0004X\r\u0003\u0004\u0002Ta$\tAH\u0001\tQ&<\u0007.\u00138g_\"1\u0011q\u000b=\u0005\u0002y\t!\u0002[5hQ\u0016\u0013\u0018m]3e\u0011\u0019\tY\u0006\u001fC\u0001=\u0005q\u0001.[4i\u00072\f7o\u001d\"pk:$\u0007bBA0q\u0012\u0005\u0011\u0011M\u0001\fSN,%O]8oK>,8/F\u0001Q\u0011\u001d\t)\u0007\u001fC\u0001\u0003C\n\u0001b]1nK.Kg\u000e\u001a\u0005\b\u0003SBH\u0011BA6\u0003A\u0019G.Y:t\u0005>,h\u000eZ!t'\u0016,g\u000eF\u0002 \u0003[Bq!a\u001c\u0002h\u0001\u0007\u0001'\u0001\u0003ugfl\u0007bBA:q\u0012%\u0011QO\u0001\u0010[\u0016l'-\u001a:EK\u001a\u001cFO]5oOR1\u0011qOAC\u0003\u000f\u0003B!!\u001f\u0002\u00046\u0011\u00111\u0010\u0006\u0005\u0003{\ny(\u0001\u0003mC:<'BAAA\u0003\u0011Q\u0017M^1\n\u0007\u0019\u000bY\b\u0003\u0004:\u0003c\u0002\r\u0001\r\u0005\b\u0003\u0013\u000b\t\b1\u0001Q\u0003\u00159\b.\u001a:f\u0011\u001d\ti\t\u001fC\u0005\u0003\u001f\u000b1b\u001e5fe\u0016\u001cFO]5oOR!\u0011qOAI\u0011\u0019I\u00141\u0012a\u0001a!9\u0011Q\u0013=\u0005\u0002\u0005]\u0015!\u00037poN#(/\u001b8h+\t\t9\bC\u0004\u0002\u001cb$\t!a&\u0002\u0015!Lw\r[*ue&tw\r\u0003\u0004mq\u0012\u0005\u0013q\u0014\u000b\u0003\u0003oB\u0011\"a)y\u0003\u0003%\t!!*\u0002\t\r|\u0007/\u001f\u000b\t\u0003C\t9+!+\u0002,\"I\u0011QAAQ!\u0003\u0005\r\u0001\r\u0005\n\u0003\u001f\t\t\u000b%AA\u0002AB\u0011\"a\u0006\u0002\"B\u0005\t\u0019\u0001\u0019\t\u0013\u0005=\u00060%A\u0005\u0002\u0005E\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003gS3\u0001MA[W\t\t9\f\u0005\u0003\u0002:\u0006\rWBAA^\u0015\u0011\ti,a0\u0002\u0013Ut7\r[3dW\u0016$'bAAa\r\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\u0015\u00171\u0018\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007\"CAeqF\u0005I\u0011AAY\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIIB\u0011\"!4y#\u0003%\t!!-\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%g!I\u0011\u0011\u001b=\u0002\u0002\u0013\u0005\u0013qS\u0001\u000eaJ|G-^2u!J,g-\u001b=\t\u0013\u0005U\u00070!A\u0005\u0002\u0005]\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAAm!\rY\u00111\\\u0005\u0004\u0003;4!aA%oi\"I\u0011\u0011\u001d=\u0002\u0002\u0013\u0005\u00111]\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t)/a;\u0011\u0007-\t9/C\u0002\u0002j\u001a\u00111!\u00118z\u0011)\ti/a8\u0002\u0002\u0003\u0007\u0011\u0011\\\u0001\u0004q\u0012\n\u0004\"CAyq\u0006\u0005I\u0011IAz\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA{!\u0019\t90!@\u0002f6\u0011\u0011\u0011 \u0006\u0004\u0003w4\u0011AC2pY2,7\r^5p]&!\u0011q`A}\u0005!IE/\u001a:bi>\u0014\b\"\u0003B\u0002q\u0006\u0005I\u0011\u0001B\u0003\u0003!\u0019\u0017M\\#rk\u0006dGc\u0001)\u0003\b!Q\u0011Q\u001eB\u0001\u0003\u0003\u0005\r!!:\t\u0013\t-\u00010!A\u0005B\t5\u0011\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005e\u0007\"\u0003B\tq\u0006\u0005I\u0011\tB\n\u0003\u0019)\u0017/^1mgR\u0019\u0001K!\u0006\t\u0015\u00055(qBA\u0001\u0002\u0004\t)oB\u0005\u0003\u001a\u0001\t\t\u0011#\u0001\u0003\u001c\u0005Q1+_7c_2\u0004\u0016-\u001b:\u0011\u0007\u0005\u0012iB\u0002\u0005z\u0001\u0005\u0005\t\u0012\u0001B\u0010'\u0015\u0011iB!\t\u007f!%\u0011\u0019C!\u000b1aA\n\t#\u0004\u0002\u0003&)\u0019!q\u0005\u0004\u0002\u000fI,h\u000e^5nK&!!1\u0006B\u0013\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\u0005\b\u001f\tuA\u0011\u0001B\u0018)\t\u0011Y\u0002C\u0005m\u0005;\t\t\u0011\"\u0012\u0002 \"Q!Q\u0007B\u000f\u0003\u0003%\tIa\u000e\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0011\u0005\u0005\"\u0011\bB\u001e\u0005{Aq!!\u0002\u00034\u0001\u0007\u0001\u0007C\u0004\u0002\u0010\tM\u0002\u0019\u0001\u0019\t\u000f\u0005]!1\u0007a\u0001a!Q!\u0011\tB\u000f\u0003\u0003%\tIa\u0011\u0002\u000fUt\u0017\r\u001d9msR!!Q\tB)!\u0015Y!q\tB&\u0013\r\u0011IE\u0002\u0002\u0007\u001fB$\u0018n\u001c8\u0011\r-\u0011i\u0005\r\u00191\u0013\r\u0011yE\u0002\u0002\u0007)V\u0004H.Z\u001a\t\u0015\tM#qHA\u0001\u0002\u0004\t\t#A\u0002yIA2qAa\u0016\u0001\u0003\u0003\u0011IF\u0001\u0004DkJ\u001cxN]\n\u0004\u0005+R\u0001bCA\u0003\u0005+\u0012)\u0019!C\u0001\u0003\u000fA!\"a\u0003\u0003V\t\u0005\t\u0015!\u00031\u0011\u001dy!Q\u000bC\u0001\u0005C\"BAa\u0019\u0003fA\u0019\u0011E!\u0016\t\u000f\u0005\u0015!q\fa\u0001a!I\u00111\bB+\u0005\u0004%)A\b\u0005\t\u0005W\u0012)\u0006)A\u0007?\u0005)1/\u001a7gA!Q!q\u000eB+\u0005\u0004%IA!\u001d\u0002\u000b\u0011,7\r\\:\u0016\u0005\tM\u0004c\u0001\u0011\u0003v%!!q\u000fB=\u0005\u0015\u00196m\u001c9f\u0013\r\u0011YH\u0001\u0002\u0007'\u000e|\u0007/Z:\t\u0013\t}$Q\u000bQ\u0001\n\tM\u0014A\u00023fG2\u001c\b\u0005\u0003\u0006\u0003\u0004\nU#\u0019!C\u0005\u0003/\fAa]5{K\"I!q\u0011B+A\u0003%\u0011\u0011\\\u0001\u0006g&TX\r\t\u0005\t\u0005\u0017\u0013)F\"\u0005\u0003\u000e\u00069Q\r_2mk\u0012,Gc\u0001)\u0003\u0010\"1\u0011H!#A\u0002ABqa\u001aB+\r#\u0011\u0019\nF\u0003Q\u0005+\u0013I\nC\u0004\u0003\u0018\nE\u0005\u0019\u0001\u0019\u0002\u00051|\u0007bBA\f\u0005#\u0003\r\u0001\r\u0005\t\u0005;\u0013)\u0006\"\u0005\u0003 \u00069\u0001/\u0019:f]R\u001cXC\u0001BQ!\u0015\u0011\u0019K!+ \u001d\rY!QU\u0005\u0004\u0005O3\u0011a\u00029bG.\fw-Z\u0005\u0005\u0005W\u0013iK\u0001\u0003MSN$(b\u0001BT\r!A!\u0011\u0017B+\t#\u0011\u0019,A\u0003cCN,7/\u0006\u0002\u00036B)!1\u0015BUa\u00159!\u0011\u0018B+\t\tm&A\u0002\"jiN+G\u000fE\u0003\f\u0005{\u000bI.C\u0002\u0003@\u001a\u0011Q!\u0011:sCfD!Ba1\u0003V\t\u0007I\u0011\u0002Bc\u0003)\u0019XO\u0019)be\u0016tGo]\u000b\u0003\u0005\u000f\u0004Ra\u0003B_\u0005\u0013\u0004BAa3\u000386\u0011!Q\u000b\u0005\n\u0005\u001f\u0014)\u0006)A\u0005\u0005\u000f\f1b];c!\u0006\u0014XM\u001c;tA!Q!1\u001bB+\u0005\u0004%IA!6\u0002\u000b%tG-\u001a=\u0016\u0005\t]\u0007c\u0002Bm\u0005?\u0004\u0014\u0011\\\u0007\u0003\u00057TAA!8\u0002z\u00069Q.\u001e;bE2,\u0017\u0002\u0002Bq\u00057\u0014q\u0001S1tQ6\u000b\u0007\u000fC\u0005\u0003f\nU\u0003\u0015!\u0003\u0003X\u00061\u0011N\u001c3fq\u0002B!B!;\u0003V\t\u0007I\u0011\u0002Bv\u0003\u001d1\u0018n]5uK\u0012,\"A!<\u0011\r\t=(Q\u001fB}\u001b\t\u0011\tPC\u0002\u0003t\n\tA!\u001e;jY&!!q\u001fBy\u0005\u001dA\u0015m\u001d5TKR\u00042\u0001\tB~\u0013\u0011\u0011iP!\u001f\u0003\u0015M\u001bw\u000e]3F]R\u0014\u0018\u0010C\u0005\u0004\u0002\tU\u0003\u0015!\u0003\u0003n\u0006Aa/[:ji\u0016$\u0007\u0005C\u0006\u0004\u0006\tU\u0003\u0019!A!B\u0013\u0001\u0014!\u00037poNKXNY8m\u0011-\u0019IA!\u0016A\u0002\u0003\u0005\u000b\u0015\u0002\u0019\u0002\u0015!Lw\r[*z[\n|G\u000eC\u0005\u0004\u000e\tU\u0003\u0015)\u0003\u0003z\u0006A1-\u001e:F]R\u0014\u0018\u0010C\u0005\u0004\u0012\tU\u0003\u0015)\u0003\u0003z\u0006Ia.\u001a=u\u000b:$(/\u001f\u0005\t\u0007+\u0011)\u0006\"\u0003\u0004\u0018\u0005!\u0011N\\5u)\t\u0019I\u0002E\u0002\f\u00077I1a!\b\u0007\u0005\u0011)f.\u001b;\t\u0011\r\u0005\"Q\u000bC\u0005\u0007G\tq!\u001b8dYV$W\r\u0006\u0004\u0004\u001a\r\u00152\u0011\u0006\u0005\t\u0007O\u0019y\u00021\u0001\u0003J\u0006\u0011!m\u001d\u0005\t\u0007W\u0019y\u00021\u0001\u0002Z\u0006\ta\u000e\u0003\u0005\u00040\tUC\u0011BB\u0019\u0003yIg\u000e^3sg\u0016\u001cG/[8o\u0007>tG/Y5og\u0016cW-\\3oi2+\u0017\u000fF\u0004Q\u0007g\u00199da\u000f\t\u0011\rU2Q\u0006a\u0001\u0005\u0013\f1AY:2\u0011!\u0019Id!\fA\u0002\t%\u0017a\u00012te!A11FB\u0017\u0001\u0004\tI\u000e\u0003\u0005\u0004@\tUC\u0011BB!\u0003eA\u0017m]\"p[6|g\u000eU1sK:$\u0018i]*vE\u000ed\u0017m]:\u0015\u000bA\u001b\u0019e!\u0012\t\rQ\u001bi\u00041\u00011\u0011\u001916Q\ba\u0001a!A1\u0011\nB+\t\u0013\u00199\"\u0001\tbIZ\fgnY3OKb$XI\u001c;ss\"\"1qIB'!\u0011\u0019ye!\u0015\u000e\u0005\u0005}\u0016\u0002BB*\u0003\u007f\u0013q\u0001^1jYJ,7\r\u0003\u0005\u0004X\tUC\u0011BB\f\u0003=\tGM^1oG\u0016\u001cUO]#oiJL\b\u0006BB+\u0007\u001bB\u0001\"a\u0004\u0003V\u0011\u0005\u0011q\u0001\u0005\t\u0003/\u0011)\u0006\"\u0001\u0002\b!A1\u0011\rB+\t\u0003\t\t'A\u0004iCNtU\r\u001f;\t\u0011\r\u0015$Q\u000bC\u0001\u0007O\n1bY;se\u0016tG\u000fU1jeV\u0011\u0011\u0011\u0005\u0005\t\u0007W\u0012)\u0006\"\u0001\u0004n\u0005A\u0011\u000e^3sCR|'/\u0006\u0002\u0004pI)1\u0011\u000f\u0006\u0004v\u0019911OB5\u0001\r=$\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004C\u0002BR\u0007o\n\t#\u0003\u0003\u0002\u0000\n5\u0006\u0002CB>\u0005+\"\taa\u0006\u0002\t9,\u0007\u0010\u001e")
public abstract class SymbolPairs {
    private volatile SymbolPairs$SymbolPair$ SymbolPair$module;

    private SymbolPairs$SymbolPair$ SymbolPair$lzycompute() {
        SymbolPairs symbolPairs = this;
        synchronized (symbolPairs) {
            if (this.SymbolPair$module == null) {
                this.SymbolPair$module = new SymbolPairs$SymbolPair$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.SymbolPair$module;
        }
    }

    public abstract SymbolTable global();

    public boolean sameInBaseClass(Symbols.Symbol baseClass, Types.Type tp1, Types.Type tp2) {
        return tp1.baseType(baseClass).$eq$colon$eq(tp2.baseType(baseClass));
    }

    public SymbolPairs$SymbolPair$ SymbolPair() {
        return this.SymbolPair$module == null ? this.SymbolPair$lzycompute() : this.SymbolPair$module;
    }

    public abstract class Cursor {
        private final Symbols.Symbol base;
        private final Types.Type self;
        private final Scopes.Scope decls;
        private final int scala$reflect$internal$SymbolPairs$Cursor$$size;
        private final int[][] scala$reflect$internal$SymbolPairs$Cursor$$subParents;
        private final HashMap<Symbols.Symbol, Object> scala$reflect$internal$SymbolPairs$Cursor$$index;
        private final HashSet<Scopes.ScopeEntry> visited;
        private Symbols.Symbol lowSymbol;
        private Symbols.Symbol highSymbol;
        private Scopes.ScopeEntry curEntry;
        private Scopes.ScopeEntry nextEntry;
        public final /* synthetic */ SymbolPairs $outer;

        public Symbols.Symbol base() {
            return this.base;
        }

        public final Types.Type self() {
            return this.self;
        }

        private Scopes.Scope decls() {
            return this.decls;
        }

        public int scala$reflect$internal$SymbolPairs$Cursor$$size() {
            return this.scala$reflect$internal$SymbolPairs$Cursor$$size;
        }

        public abstract boolean exclude(Symbols.Symbol var1);

        public abstract boolean matches(Symbols.Symbol var1, Symbols.Symbol var2);

        public List<Types.Type> parents() {
            return this.base().info().parents();
        }

        public List<Symbols.Symbol> bases() {
            return this.base().info().baseClasses();
        }

        public int[][] scala$reflect$internal$SymbolPairs$Cursor$$subParents() {
            return this.scala$reflect$internal$SymbolPairs$Cursor$$subParents;
        }

        public HashMap<Symbols.Symbol, Object> scala$reflect$internal$SymbolPairs$Cursor$$index() {
            return this.scala$reflect$internal$SymbolPairs$Cursor$$index;
        }

        private HashSet<Scopes.ScopeEntry> visited() {
            return this.visited;
        }

        private void init() {
            IntRef i = IntRef.create(0);
            List list2 = this.bases();
            while (!((AbstractIterable)list2).isEmpty()) {
                Symbols.Symbol symbol = (Symbols.Symbol)((AbstractIterable)list2).head();
                this.scala$reflect$internal$SymbolPairs$Cursor$$index().update(symbol, BoxesRunTime.boxToInteger(i.elem));
                this.scala$reflect$internal$SymbolPairs$Cursor$$subParents()[i.elem] = new int[this.scala$reflect$internal$SymbolPairs$Cursor$$size()];
                ++i.elem;
                list2 = (List)list2.tail();
            }
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ Cursor $outer;

                public final void apply(Types.Type p) {
                    int pIndex = BoxesRunTime.unboxToInt(this.$outer.scala$reflect$internal$SymbolPairs$Cursor$$index().apply(p.typeSymbol()));
                    if (pIndex >= 0) {
                        p.baseClasses().withFilter((Function1<Symbols.Symbol, Object>)((Object)new Serializable(this, p){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ Cursor$$anonfun$init$2 $outer;
                            private final Types.Type p$1;

                            public final boolean apply(Symbols.Symbol bc) {
                                return this.$outer.$outer.scala$reflect$internal$SymbolPairs$Cursor$$$outer().sameInBaseClass(bc, this.p$1, this.$outer.$outer.self());
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                                this.p$1 = p$1;
                            }
                        })).foreach(new Serializable(this, pIndex){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ Cursor$$anonfun$init$2 $outer;
                            private final int pIndex$1;

                            public final void apply(Symbols.Symbol bc) {
                                int bcIndex = BoxesRunTime.unboxToInt(this.$outer.$outer.scala$reflect$internal$SymbolPairs$Cursor$$index().apply(bc));
                                if (bcIndex >= 0) {
                                    this.$outer.$outer.scala$reflect$internal$SymbolPairs$Cursor$$include(this.$outer.$outer.scala$reflect$internal$SymbolPairs$Cursor$$subParents()[bcIndex], this.pIndex$1);
                                }
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                                this.pIndex$1 = pIndex$1;
                            }
                        });
                    }
                }

                public /* synthetic */ Cursor scala$reflect$internal$SymbolPairs$Cursor$$anonfun$$$outer() {
                    return this.$outer;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
            List list3 = this.parents();
            while (!((AbstractIterable)list3).isEmpty()) {
                Types.Type type = (Types.Type)((AbstractIterable)list3).head();
                int pIndex1 = BoxesRunTime.unboxToInt(this.scala$reflect$internal$SymbolPairs$Cursor$$index().apply(type.typeSymbol()));
                if (pIndex1 >= 0) {
                    type.baseClasses().withFilter((Function1<Symbols.Symbol, Object>)((Object)new /* invalid duplicate definition of identical inner class */)).foreach(new /* invalid duplicate definition of identical inner class */);
                }
                list3 = (List)list3.tail();
            }
            this.fillDecls$1(this.bases(), true);
            this.fillDecls$1(this.bases(), false);
        }

        public void scala$reflect$internal$SymbolPairs$Cursor$$include(int[] bs, int n) {
            int nshifted = n >> 5;
            int nmask = 1 << (n & 0x1F);
            bs[nshifted] = bs[nshifted] | nmask;
        }

        private boolean intersectionContainsElementLeq(int[] bs1, int[] bs2, int n) {
            int nshifted = n >> 5;
            int nmask = 1 << (n & 0x1F);
            for (int i = 0; i < nshifted; ++i) {
                if ((bs1[i] & bs2[i]) == 0) continue;
                return true;
            }
            return (bs1[nshifted] & bs2[nshifted] & (nmask | nmask - 1)) != 0;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private boolean hasCommonParentAsSubclass(Symbols.Symbol sym1, Symbols.Symbol sym2) {
            int index1 = BoxesRunTime.unboxToInt(this.scala$reflect$internal$SymbolPairs$Cursor$$index().apply(sym1.owner()));
            if (index1 < 0) return false;
            int index2 = BoxesRunTime.unboxToInt(this.scala$reflect$internal$SymbolPairs$Cursor$$index().apply(sym2.owner()));
            if (index2 < 0) return false;
            Predef$ predef$ = Predef$.MODULE$;
            if (!this.intersectionContainsElementLeq(this.scala$reflect$internal$SymbolPairs$Cursor$$subParents()[index1], this.scala$reflect$internal$SymbolPairs$Cursor$$subParents()[index2], RichInt$.MODULE$.min$extension(index1, index2))) return false;
            return true;
        }

        private void advanceNextEntry() {
            block5: {
                BoxedUnit boxedUnit;
                while (this.nextEntry != null) {
                    this.nextEntry = this.decls().lookupNextEntry(this.nextEntry);
                    if (this.nextEntry != null) {
                        boolean isMatch;
                        boolean bl;
                        Symbols.Symbol high = this.nextEntry.sym();
                        if (this.matches(this.lowSymbol, high)) {
                            this.visited().addEntry(this.nextEntry);
                            bl = true;
                        } else {
                            bl = false;
                        }
                        if (!(isMatch = bl) || this.hasCommonParentAsSubclass(this.lowSymbol, high)) continue;
                        this.highSymbol = high;
                        boxedUnit = BoxedUnit.UNIT;
                    } else {
                        boxedUnit = BoxedUnit.UNIT;
                    }
                    break block5;
                }
                boxedUnit = BoxedUnit.UNIT;
            }
        }

        private void advanceCurEntry() {
            block3: {
                BoxedUnit boxedUnit;
                while (this.curEntry != null) {
                    this.curEntry = this.curEntry.next();
                    if (this.curEntry != null) {
                        if (this.visited().apply(this.curEntry) || this.exclude(this.curEntry.sym())) continue;
                        this.nextEntry = this.curEntry;
                        boxedUnit = BoxedUnit.UNIT;
                    } else {
                        boxedUnit = BoxedUnit.UNIT;
                    }
                    break block3;
                }
                boxedUnit = BoxedUnit.UNIT;
            }
        }

        public Symbols.Symbol low() {
            return this.lowSymbol;
        }

        public Symbols.Symbol high() {
            return this.highSymbol;
        }

        public boolean hasNext() {
            return this.curEntry != null;
        }

        public SymbolPair currentPair() {
            return new SymbolPair(this.scala$reflect$internal$SymbolPairs$Cursor$$$outer(), this.base(), this.low(), this.high());
        }

        public Object iterator() {
            return new Iterator<SymbolPair>(this){
                private final /* synthetic */ Cursor $outer;

                public Iterator<SymbolPair> seq() {
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

                public Iterator<SymbolPair> take(int n) {
                    return Iterator$class.take(this, n);
                }

                public Iterator<SymbolPair> drop(int n) {
                    return Iterator$class.drop(this, n);
                }

                public Iterator<SymbolPair> slice(int from2, int until2) {
                    return Iterator$class.slice(this, from2, until2);
                }

                public <B> Iterator<B> map(Function1<SymbolPair, B> f) {
                    return Iterator$class.map(this, f);
                }

                public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
                    return Iterator$class.$plus$plus(this, that);
                }

                public <B> Iterator<B> flatMap(Function1<SymbolPair, GenTraversableOnce<B>> f) {
                    return Iterator$class.flatMap(this, f);
                }

                public Iterator<SymbolPair> filter(Function1<SymbolPair, Object> p) {
                    return Iterator$class.filter(this, p);
                }

                public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<SymbolPair, B, Object> p) {
                    return Iterator$class.corresponds(this, that, p);
                }

                public Iterator<SymbolPair> withFilter(Function1<SymbolPair, Object> p) {
                    return Iterator$class.withFilter(this, p);
                }

                public Iterator<SymbolPair> filterNot(Function1<SymbolPair, Object> p) {
                    return Iterator$class.filterNot(this, p);
                }

                public <B> Iterator<B> collect(PartialFunction<SymbolPair, B> pf) {
                    return Iterator$class.collect(this, pf);
                }

                public <B> Iterator<B> scanLeft(B z, Function2<B, SymbolPair, B> op) {
                    return Iterator$class.scanLeft(this, z, op);
                }

                public <B> Iterator<B> scanRight(B z, Function2<SymbolPair, B, B> op) {
                    return Iterator$class.scanRight(this, z, op);
                }

                public Iterator<SymbolPair> takeWhile(Function1<SymbolPair, Object> p) {
                    return Iterator$class.takeWhile(this, p);
                }

                public Tuple2<Iterator<SymbolPair>, Iterator<SymbolPair>> partition(Function1<SymbolPair, Object> p) {
                    return Iterator$class.partition(this, p);
                }

                public Tuple2<Iterator<SymbolPair>, Iterator<SymbolPair>> span(Function1<SymbolPair, Object> p) {
                    return Iterator$class.span(this, p);
                }

                public Iterator<SymbolPair> dropWhile(Function1<SymbolPair, Object> p) {
                    return Iterator$class.dropWhile(this, p);
                }

                public <B> Iterator<Tuple2<SymbolPair, B>> zip(Iterator<B> that) {
                    return Iterator$class.zip(this, that);
                }

                public <A1> Iterator<A1> padTo(int len, A1 elem) {
                    return Iterator$class.padTo(this, len, elem);
                }

                public Iterator<Tuple2<SymbolPair, Object>> zipWithIndex() {
                    return Iterator$class.zipWithIndex(this);
                }

                public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
                    return Iterator$class.zipAll(this, that, thisElem, thatElem);
                }

                public <U> void foreach(Function1<SymbolPair, U> f) {
                    Iterator$class.foreach(this, f);
                }

                public boolean forall(Function1<SymbolPair, Object> p) {
                    return Iterator$class.forall(this, p);
                }

                public boolean exists(Function1<SymbolPair, Object> p) {
                    return Iterator$class.exists(this, p);
                }

                public boolean contains(Object elem) {
                    return Iterator$class.contains(this, elem);
                }

                public Option<SymbolPair> find(Function1<SymbolPair, Object> p) {
                    return Iterator$class.find(this, p);
                }

                public int indexWhere(Function1<SymbolPair, Object> p) {
                    return Iterator$class.indexWhere(this, p);
                }

                public <B> int indexOf(B elem) {
                    return Iterator$class.indexOf(this, elem);
                }

                public BufferedIterator<SymbolPair> buffered() {
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

                public Tuple2<Iterator<SymbolPair>, Iterator<SymbolPair>> duplicate() {
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

                public Traversable<SymbolPair> toTraversable() {
                    return Iterator$class.toTraversable(this);
                }

                public Iterator<SymbolPair> toIterator() {
                    return Iterator$class.toIterator(this);
                }

                public Stream<SymbolPair> toStream() {
                    return Iterator$class.toStream(this);
                }

                public String toString() {
                    return Iterator$class.toString(this);
                }

                public <B> int sliding$default$2() {
                    return Iterator$class.sliding$default$2(this);
                }

                public List<SymbolPair> reversed() {
                    return TraversableOnce$class.reversed(this);
                }

                public int size() {
                    return TraversableOnce$class.size(this);
                }

                public boolean nonEmpty() {
                    return TraversableOnce$class.nonEmpty(this);
                }

                public int count(Function1<SymbolPair, Object> p) {
                    return TraversableOnce$class.count(this, p);
                }

                public <B> Option<B> collectFirst(PartialFunction<SymbolPair, B> pf) {
                    return TraversableOnce$class.collectFirst(this, pf);
                }

                public <B> B $div$colon(B z, Function2<B, SymbolPair, B> op) {
                    return (B)TraversableOnce$class.$div$colon(this, z, op);
                }

                public <B> B $colon$bslash(B z, Function2<SymbolPair, B, B> op) {
                    return (B)TraversableOnce$class.$colon$bslash(this, z, op);
                }

                public <B> B foldLeft(B z, Function2<B, SymbolPair, B> op) {
                    return (B)TraversableOnce$class.foldLeft(this, z, op);
                }

                public <B> B foldRight(B z, Function2<SymbolPair, B, B> op) {
                    return (B)TraversableOnce$class.foldRight(this, z, op);
                }

                public <B> B reduceLeft(Function2<B, SymbolPair, B> op) {
                    return (B)TraversableOnce$class.reduceLeft(this, op);
                }

                public <B> B reduceRight(Function2<SymbolPair, B, B> op) {
                    return (B)TraversableOnce$class.reduceRight(this, op);
                }

                public <B> Option<B> reduceLeftOption(Function2<B, SymbolPair, B> op) {
                    return TraversableOnce$class.reduceLeftOption(this, op);
                }

                public <B> Option<B> reduceRightOption(Function2<SymbolPair, B, B> op) {
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

                public <B> B aggregate(Function0<B> z, Function2<B, SymbolPair, B> seqop, Function2<B, B, B> combop) {
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

                public List<SymbolPair> toList() {
                    return TraversableOnce$class.toList(this);
                }

                public Iterable<SymbolPair> toIterable() {
                    return TraversableOnce$class.toIterable(this);
                }

                public Seq<SymbolPair> toSeq() {
                    return TraversableOnce$class.toSeq(this);
                }

                public IndexedSeq<SymbolPair> toIndexedSeq() {
                    return TraversableOnce$class.toIndexedSeq(this);
                }

                public <B> Buffer<B> toBuffer() {
                    return TraversableOnce$class.toBuffer(this);
                }

                public <B> Set<B> toSet() {
                    return TraversableOnce$class.toSet(this);
                }

                public Vector<SymbolPair> toVector() {
                    return TraversableOnce$class.toVector(this);
                }

                public <Col> Col to(CanBuildFrom<Nothing$, SymbolPair, Col> cbf) {
                    return (Col)TraversableOnce$class.to(this, cbf);
                }

                public <T, U> Map<T, U> toMap(Predef$.less.colon.less<SymbolPair, Tuple2<T, U>> ev) {
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
                    return this.$outer.hasNext();
                }

                public SymbolPair next() {
                    try {
                        return this.$outer.currentPair();
                    }
                    finally {
                        this.$outer.next();
                    }
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    TraversableOnce$class.$init$(this);
                    Iterator$class.$init$(this);
                }
            };
        }

        public void next() {
            if (this.curEntry != null) {
                this.lowSymbol = this.curEntry.sym();
                this.advanceNextEntry();
                if (this.nextEntry == null) {
                    this.advanceCurEntry();
                    this.next();
                }
            }
        }

        public /* synthetic */ SymbolPairs scala$reflect$internal$SymbolPairs$Cursor$$$outer() {
            return this.$outer;
        }

        private final void fillDecls$1(List bcs, boolean deferred) {
            if (!bcs.isEmpty()) {
                this.fillDecls$1((List)bcs.tail(), deferred);
                for (Scopes.ScopeEntry e = ((Symbols.Symbol)bcs.head()).info().decls().elems(); e != null; e = e.next()) {
                    Object object = e.sym().initialize().isDeferred() == deferred && !this.exclude(e.sym()) ? this.decls().enter(e.sym()) : BoxedUnit.UNIT;
                }
            }
        }

        public Cursor(SymbolPairs $outer, Symbols.Symbol base) {
            this.base = base;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            this.self = base.thisType();
            this.decls = $outer.global().newScope();
            this.scala$reflect$internal$SymbolPairs$Cursor$$size = this.bases().length();
            this.scala$reflect$internal$SymbolPairs$Cursor$$subParents = new int[this.scala$reflect$internal$SymbolPairs$Cursor$$size()][];
            this.scala$reflect$internal$SymbolPairs$Cursor$$index = new HashMap<Symbols.Symbol, Object>(this){

                public int default(Symbols.Symbol key) {
                    return -1;
                }
            };
            this.visited = HashSet$.MODULE$.apply("visited", 64);
            this.init();
            this.nextEntry = this.curEntry = this.decls().elems();
            this.next();
        }
    }

    public class RelativeTo {
        private final Types.Type prefix;
        public final /* synthetic */ SymbolPairs $outer;

        public Types.Type prefix() {
            return this.prefix;
        }

        private Types.Type symbolToType(Symbols.Symbol sym) {
            return this.prefix().memberType(sym);
        }

        public Types.Type erasureOf(Symbols.Symbol sym) {
            return this.scala$reflect$internal$SymbolPairs$RelativeTo$$$outer().global().erasure().erasure(sym).apply(this.symbolToType(sym));
        }

        public String signature(Symbols.Symbol sym) {
            return sym.defStringSeenAs(this.symbolToType(sym));
        }

        public String erasedSignature(Symbols.Symbol sym) {
            return sym.defStringSeenAs(this.erasureOf(sym));
        }

        public boolean isSameType(Symbols.Symbol sym1, Symbols.Symbol sym2) {
            return this.symbolToType(sym1).$eq$colon$eq(this.symbolToType(sym2));
        }

        public boolean isSubType(Symbols.Symbol sym1, Symbols.Symbol sym2) {
            return this.symbolToType(sym1).$less$colon$less(this.symbolToType(sym2));
        }

        public boolean isSuperType(Symbols.Symbol sym1, Symbols.Symbol sym2) {
            return this.symbolToType(sym2).$less$colon$less(this.symbolToType(sym1));
        }

        public boolean isSameErasure(Symbols.Symbol sym1, Symbols.Symbol sym2) {
            return this.erasureOf(sym1).$eq$colon$eq(this.erasureOf(sym2));
        }

        public boolean matches(Symbols.Symbol sym1, Symbols.Symbol sym2) {
            return this.symbolToType(sym1).matches(this.symbolToType(sym2));
        }

        public String toString() {
            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"RelativeTo(", ")"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.prefix()}));
        }

        public /* synthetic */ SymbolPairs scala$reflect$internal$SymbolPairs$RelativeTo$$$outer() {
            return this.$outer;
        }

        public RelativeTo(SymbolPairs $outer, Types.Type prefix) {
            this.prefix = prefix;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }

        public RelativeTo(SymbolPairs $outer, Symbols.Symbol clazz) {
            this($outer, clazz.thisType());
        }
    }

    public class SymbolPair
    implements Product,
    Serializable {
        private final Symbols.Symbol base;
        private final Symbols.Symbol low;
        private final Symbols.Symbol high;
        public final /* synthetic */ SymbolPairs $outer;

        public Symbols.Symbol base() {
            return this.base;
        }

        public Symbols.Symbol low() {
            return this.low;
        }

        public Symbols.Symbol high() {
            return this.high;
        }

        public Position pos() {
            Position position;
            Symbols.Symbol symbol = this.low().owner();
            Symbols.Symbol symbol2 = this.base();
            if (!(symbol != null ? !symbol.equals(symbol2) : symbol2 != null)) {
                position = this.low().pos();
            } else {
                Symbols.Symbol symbol3 = this.high().owner();
                Symbols.Symbol symbol4 = this.base();
                position = !(symbol3 != null ? !symbol3.equals(symbol4) : symbol4 != null) ? this.high().pos() : this.base().pos();
            }
            return position;
        }

        public Types.Type self() {
            return this.base().thisType();
        }

        public Types.Type rootType() {
            return this.base().thisType();
        }

        public Types.Type lowType() {
            return this.self().memberType(this.low());
        }

        public Types.Type lowErased() {
            return this.scala$reflect$internal$SymbolPairs$SymbolPair$$$outer().global().erasure().specialErasure(this.base(), this.low().tpe());
        }

        public Types.Type lowClassBound() {
            return this.classBoundAsSeen(this.low().tpe().typeSymbol());
        }

        public Types.Type highType() {
            return this.self().memberType(this.high());
        }

        public Types.Type highInfo() {
            return this.self().memberInfo(this.high());
        }

        public Types.Type highErased() {
            return this.scala$reflect$internal$SymbolPairs$SymbolPair$$$outer().global().erasure().specialErasure(this.base(), this.high().tpe());
        }

        public Types.Type highClassBound() {
            return this.classBoundAsSeen(this.high().tpe().typeSymbol());
        }

        public boolean isErroneous() {
            return this.low().tpe().isErroneous() || this.high().tpe().isErroneous();
        }

        public boolean sameKind() {
            return this.scala$reflect$internal$SymbolPairs$SymbolPair$$$outer().global().sameLength(this.low().typeParams(), this.high().typeParams());
        }

        private Types.Type classBoundAsSeen(Symbols.Symbol tsym) {
            return tsym.classBound().asSeenFrom(this.rootType(), tsym.owner());
        }

        private String memberDefString(Symbols.Symbol sym, boolean where) {
            String def_s = sym.isConstructor() ? new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", ": ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{sym, this.self().memberType(sym)})) : sym.defStringSeenAs(this.self().memberType(sym));
            return new StringBuilder().append((Object)def_s).append((Object)this.whereString(sym)).toString();
        }

        private String whereString(Symbols.Symbol sym) {
            Symbols.Symbol symbol = sym.owner();
            Symbols.Symbol symbol2 = this.base();
            return !(symbol != null ? !symbol.equals(symbol2) : symbol2 != null) ? new StringBuilder().append((Object)" at line ").append(BoxesRunTime.boxToInteger(sym.pos().line())).toString() : sym.locationString();
        }

        public String lowString() {
            return this.memberDefString(this.low(), true);
        }

        public String highString() {
            return this.memberDefString(this.high(), true);
        }

        public String toString() {
            return ((StripMarginInterpolator)this.scala$reflect$internal$SymbolPairs$SymbolPair$$$outer().global().StringContextStripMarginOps().apply(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"\n      |Cursor(in ", ") {\n      |   high  ", "\n      | erased  ", "\n      |  infos  ", "\n      |    low  ", "\n      | erased  ", "\n      |  infos  ", "\n      |}"})))).sm(Predef$.MODULE$.genericWrapArray(new Object[]{this.base(), this.highString(), this.highErased(), this.high().infosString(), this.lowString(), this.lowErased(), this.low().infosString()})).trim();
        }

        public SymbolPair copy(Symbols.Symbol base, Symbols.Symbol low, Symbols.Symbol high) {
            return new SymbolPair(this.scala$reflect$internal$SymbolPairs$SymbolPair$$$outer(), base, low, high);
        }

        public Symbols.Symbol copy$default$1() {
            return this.base();
        }

        public Symbols.Symbol copy$default$2() {
            return this.low();
        }

        public Symbols.Symbol copy$default$3() {
            return this.high();
        }

        @Override
        public String productPrefix() {
            return "SymbolPair";
        }

        @Override
        public int productArity() {
            return 3;
        }

        @Override
        public Object productElement(int x$1) {
            Symbols.Symbol symbol;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 2: {
                    symbol = this.high();
                    break;
                }
                case 1: {
                    symbol = this.low();
                    break;
                }
                case 0: {
                    symbol = this.base();
                }
            }
            return symbol;
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof SymbolPair;
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
            if (!(x$1 instanceof SymbolPair)) return false;
            if (((SymbolPair)x$1).scala$reflect$internal$SymbolPairs$SymbolPair$$$outer() != this.scala$reflect$internal$SymbolPairs$SymbolPair$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            SymbolPair symbolPair = (SymbolPair)x$1;
            Symbols.Symbol symbol = this.base();
            Symbols.Symbol symbol2 = symbolPair.base();
            if (symbol == null) {
                if (symbol2 != null) {
                    return false;
                }
            } else if (!symbol.equals(symbol2)) return false;
            Symbols.Symbol symbol3 = this.low();
            Symbols.Symbol symbol4 = symbolPair.low();
            if (symbol3 == null) {
                if (symbol4 != null) {
                    return false;
                }
            } else if (!symbol3.equals(symbol4)) return false;
            Symbols.Symbol symbol5 = this.high();
            Symbols.Symbol symbol6 = symbolPair.high();
            if (symbol5 == null) {
                if (symbol6 != null) {
                    return false;
                }
            } else if (!symbol5.equals(symbol6)) return false;
            if (!symbolPair.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolPairs scala$reflect$internal$SymbolPairs$SymbolPair$$$outer() {
            return this.$outer;
        }

        public SymbolPair(SymbolPairs $outer, Symbols.Symbol base, Symbols.Symbol low, Symbols.Symbol high) {
            this.base = base;
            this.low = low;
            this.high = high;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Product$class.$init$(this);
        }
    }
}

