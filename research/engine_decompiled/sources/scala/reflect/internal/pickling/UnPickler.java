/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.pickling;

import java.io.IOException;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.generic.Growable;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashMap$;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.StringBuilder;
import scala.package$;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Symbols;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Flags$;
import scala.reflect.internal.Mirrors;
import scala.reflect.internal.MissingRequirementError;
import scala.reflect.internal.Names;
import scala.reflect.internal.Phase;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$NoType$;
import scala.reflect.internal.pickling.PickleBuffer;
import scala.reflect.internal.pickling.PickleFormat$;
import scala.reflect.internal.pickling.UnPickler$Scan$;
import scala.reflect.internal.pickling.UnPickler$Scan$$anonfun$readExtSymbol$1$2$;
import scala.reflect.internal.pickling.UnPickler$Scan$$anonfun$readExtSymbol$1$2$$anonfun$apply$1$;
import scala.reflect.internal.pickling.UnPickler$Scan$$anonfun$readExtSymbol$1$2$$anonfun$apply$1$$anonfun$apply$2$;
import scala.reflect.internal.pickling.UnPickler$Scan$LazyTypeRefAndAlias$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(bytes="\u0006\u0001\r=h!B\u0001\u0003\u0003\u0003Y!!C+o!&\u001c7\u000e\\3s\u0015\t\u0019A!\u0001\u0005qS\u000e\\G.\u001b8h\u0015\t)a!\u0001\u0005j]R,'O\\1m\u0015\t9\u0001\"A\u0004sK\u001adWm\u0019;\u000b\u0003%\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001\u0019A\u0011QBD\u0007\u0002\u0011%\u0011q\u0002\u0003\u0002\u0007\u0003:L(+\u001a4\t\u000bE\u0001A\u0011\u0001\n\u0002\rqJg.\u001b;?)\u0005\u0019\u0002C\u0001\u000b\u0001\u001b\u0005\u0011\u0001b\u0002\f\u0001\u0005\u00045\taF\u0001\fgfl'm\u001c7UC\ndW-F\u0001\u0019!\tI\"$D\u0001\u0005\u0013\tYBAA\u0006Ts6\u0014w\u000e\u001c+bE2,\u0007\"B\u000f\u0001\t\u0003q\u0012\u0001C;oa&\u001c7\u000e\\3\u0015\r}\u0011#f\f\u001d;!\ti\u0001%\u0003\u0002\"\u0011\t!QK\\5u\u0011\u0015\u0019C\u00041\u0001%\u0003\u0015\u0011\u0017\u0010^3t!\riQeJ\u0005\u0003M!\u0011Q!\u0011:sCf\u0004\"!\u0004\u0015\n\u0005%B!\u0001\u0002\"zi\u0016DQa\u000b\u000fA\u00021\naa\u001c4gg\u0016$\bCA\u0007.\u0013\tq\u0003BA\u0002J]RDQ\u0001\r\u000fA\u0002E\n\u0011b\u00197bgN\u0014vn\u001c;\u0011\u0005I\"dBA\u001a\u0016\u001b\u0005\u0001\u0011BA\u001b7\u0005\u0019\u0019\u00160\u001c2pY&\u0011q\u0007\u0002\u0002\b'fl'm\u001c7t\u0011\u0015ID\u00041\u00012\u0003)iw\u000eZ;mKJ{w\u000e\u001e\u0005\u0006wq\u0001\r\u0001P\u0001\tM&dWM\\1nKB\u0011Q\b\u0011\b\u0003\u001byJ!a\u0010\u0005\u0002\rA\u0013X\rZ3g\u0013\t\t%I\u0001\u0004TiJLgn\u001a\u0006\u0003\u007f!1A\u0001\u0012\u0001\u0001\u000b\n!1kY1o'\t\u0019e\t\u0005\u0002\u0015\u000f&\u0011\u0001J\u0001\u0002\r!&\u001c7\u000e\\3Ck\u001a4WM\u001d\u0005\t\u0015\u000e\u0013\t\u0011)A\u0005I\u00051qLY=uKND\u0001bK\"\u0003\u0002\u0003\u0006I\u0001\f\u0005\ta\r\u0013\t\u0011)A\u0005c!A\u0011h\u0011B\u0001B\u0003%\u0011\u0007\u0003\u0005<\u0007\n\u0005\t\u0015!\u0003=\u0011\u0015\t2\t\"\u0001Q)\u0019\t&k\u0015+V-B\u00111g\u0011\u0005\u0006\u0015>\u0003\r\u0001\n\u0005\u0006W=\u0003\r\u0001\f\u0005\u0006a=\u0003\r!\r\u0005\u0006s=\u0003\r!\r\u0005\u0006w=\u0003\r\u0001\u0010\u0005\u00061\u000e#\t\"W\u0001\u0006I\u0016\u0014WoZ\u000b\u00025B\u0011QbW\u0005\u00039\"\u0011qAQ8pY\u0016\fg\u000eC\u0004_\u0007\n\u0007I\u0011B0\u0002\u001b1|\u0017\rZ5oO6K'O]8s+\u0005\u0001\u0007C\u0001\u001ab\u0013\t\u00117M\u0001\u0004NSJ\u0014xN]\u0005\u0003I\u0012\u0011q!T5se>\u00148\u000f\u0003\u0004g\u0007\u0002\u0006I\u0001Y\u0001\u000fY>\fG-\u001b8h\u001b&\u0014(o\u001c:!\u00111A7\t\"A\u0001\u0006\u0003\u0015\r\u0011\"\u0003j\u0003U\u001a8-\u00197bII,g\r\\3di\u0012Jg\u000e^3s]\u0006dG\u0005]5dW2Lgn\u001a\u0013V]BK7m\u001b7fe\u0012\u001a6-\u00198%I%tG-\u001a=\u0016\u0003)\u00042!D\u0013-\u0011%a7I!A\u0001B\u0003%!.\u0001\u001ctG\u0006d\u0017\r\n:fM2,7\r\u001e\u0013j]R,'O\\1mIAL7m\u001b7j]\u001e$SK\u001c)jG.dWM\u001d\u0013TG\u0006tG\u0005J5oI\u0016D\b\u0005C\u0004o\u0007\n\u0007I\u0011B8\u0002\u000f\u0015tGO]5fgV\t\u0001\u000fE\u0002\u000eK1AaA]\"!\u0002\u0013\u0001\u0018\u0001C3oiJLWm\u001d\u0011\t\u000fQ\u001c%\u0019!C\u0005k\u0006I1/_7TG>\u0004Xm]\u000b\u0002mB!q\u000f`\u0019\u007f\u001b\u0005A(BA={\u0003\u001diW\u000f^1cY\u0016T!a\u001f\u0005\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002~q\n9\u0001*Y:i\u001b\u0006\u0004\bC\u0001\u001a\u0000\u0013\u0011\t\t!a\u0001\u0003\u000bM\u001bw\u000e]3\n\u0007\u0005\u0015AA\u0001\u0004TG>\u0004Xm\u001d\u0005\b\u0003\u0013\u0019\u0005\u0015!\u0003w\u0003)\u0019\u00180\\*d_B,7\u000f\t\u0005\b\u0003\u001b\u0019E\u0011BA\b\u0003\u0019)\u0007\u0010]3diR)q$!\u0005\u0002\u0016!9\u00111CA\u0006\u0001\u0004a\u0013\u0001C3ya\u0016\u001cG/\u001a3\t\u0013\u0005]\u00111\u0002CA\u0002\u0005e\u0011aA7tOB!Q\"a\u0007=\u0013\r\ti\u0002\u0003\u0002\ty\tLh.Y7f}!9\u0011\u0011E\"\u0005\n\u0005\r\u0012A\u0003:v]\u0006#\u0018J\u001c3fqV!\u0011QEA\u0017)\u0011\t9#!\u0012\u0015\t\u0005%\u0012q\b\t\u0005\u0003W\ti\u0003\u0004\u0001\u0005\u0011\u0005=\u0012q\u0004b\u0001\u0003c\u0011\u0011\u0001V\t\u0005\u0003g\tI\u0004E\u0002\u000e\u0003kI1!a\u000e\t\u0005\u001dqu\u000e\u001e5j]\u001e\u00042!DA\u001e\u0013\r\ti\u0004\u0003\u0002\u0004\u0003:L\b\"CA!\u0003?!\t\u0019AA\"\u0003\u0011\u0011w\u000eZ=\u0011\u000b5\tY\"!\u000b\t\u000f\u0005\u001d\u0013q\u0004a\u0001Y\u0005\t\u0011\u000e\u000b\u0003\u0002 \u0005-\u0003cA\u0007\u0002N%\u0019\u0011q\n\u0005\u0003\r%tG.\u001b8f\u0011\u001d\t\u0019f\u0011C\u0001\u0003+\n1A];o)\u0005y\u0002bBA-\u0007\u0012%\u0011QK\u0001\rG\",7m\u001b,feNLwN\u001c\u0005\b\u0003;\u001aE\u0011CA0\u0003!\u0019\u00180\\*d_B,Gc\u0001@\u0002b!9\u00111MA.\u0001\u0004\t\u0014aA:z[\"9\u0011qM\"\u0005\u0012\u0005%\u0014!D5t'fl'm\u001c7F]R\u0014\u0018\u0010F\u0002[\u0003WBq!a\u0012\u0002f\u0001\u0007A\u0006C\u0004\u0002p\r#\t\"!\u001d\u0002\u0017%\u001c8+_7c_2\u0014VM\u001a\u000b\u00045\u0006M\u0004bBA$\u0003[\u0002\r\u0001\f\u0005\b\u0003o\u001aE\u0011CA=\u0003-I7OT1nK\u0016sGO]=\u0015\u0007i\u000bY\bC\u0004\u0002H\u0005U\u0004\u0019\u0001\u0017\t\u000f\u0005}4\t\"\u0005\u0002\u0002\u00069\u0012n]*z[\n|G.\u00118o_R\fG/[8o\u000b:$(/\u001f\u000b\u00045\u0006\r\u0005bBA$\u0003{\u0002\r\u0001\f\u0005\b\u0003\u000f\u001bE\u0011CAE\u0003=I7o\u00115jY\u0012\u0014XM\\#oiJLHc\u0001.\u0002\f\"9\u0011qIAC\u0001\u0004a\u0003bBAH\u0007\u0012%\u0011\u0011S\u0001\u0010[\u0006L(-\u001a*fC\u0012\u001c\u00160\u001c2pYR\u0011\u00111\u0013\t\u0007\u0003+\u000bY\nL\u0019\u000f\u00075\t9*C\u0002\u0002\u001a\"\tq\u0001]1dW\u0006<W-\u0003\u0003\u0002\u001e\u0006}%AB#ji\",'OC\u0002\u0002\u001a\"Aq!a)D\t#\t)+A\fjgJ+g-\u001b8f[\u0016tGoU=nE>dWI\u001c;ssR\u0019!,a*\t\u000f\u0005\u001d\u0013\u0011\u0015a\u0001Y!9\u00111V\"\u0005\u0012\u00055\u0016AA1u+\u0011\ty+a-\u0015\r\u0005E\u0016qWA]!\u0011\tY#a-\u0005\u0011\u0005=\u0012\u0011\u0016b\u0001\u0003k\u000b2!a\r\r\u0011\u001d\t9%!+A\u00021B\u0001\"a/\u0002*\u0002\u0007\u0011QX\u0001\u0003_B\u0004R!DA`\u0003cK1!!1\t\u0005%1UO\\2uS>t\u0007\u0007C\u0004\u0002F\u000e#\t\"a2\u0002\u0011I,\u0017\r\u001a(b[\u0016$\"!!3\u0011\u0007I\nY-\u0003\u0003\u0002N\u0006='\u0001\u0002(b[\u0016L1!!5\u0005\u0005\u0015q\u0015-\\3t\u0011\u001d\t)n\u0011C\u0005\u0003/\fqA]3bI\u0016sG\rF\u0001-\u0011\u001d\tYn\u0011C\t\u0003;\f!B]3bINKXNY8m)\u0005\t\u0004bBAq\u0007\u0012E\u00111]\u0001\te\u0016\fG\rV=qKR!\u0011Q]Ax!\r\u0011\u0014q]\u0005\u0005\u0003S\fYO\u0001\u0003UsB,\u0017bAAw\t\t)A+\u001f9fg\"I\u0011\u0011_Ap!\u0003\u0005\rAW\u0001\u0010M>\u00148-\u001a)s_B,'\u000fV=qK\"9\u0011Q_\"\u0005\u0002\u0005]\u0018!\u00048p'V\u001c\u0007\u000eV=qKR\u000bw\r\u0006\u0004\u0002f\u0006e\u0018Q \u0005\b\u0003w\f\u0019\u00101\u0001-\u0003\r!\u0018m\u001a\u0005\b\u0003\u007f\f\u0019\u00101\u0001-\u0003\r)g\u000e\u001a\u0005\b\u0005\u0007\u0019E\u0011\u0003B\u0003\u00031\u0011X-\u00193D_:\u001cH/\u00198u)\t\u00119\u0001E\u00023\u0005\u0013IAAa\u0003\u0003\u000e\tA1i\u001c8ti\u0006tG/C\u0002\u0003\u0010\u0011\u0011\u0011bQ8ogR\fg\u000e^:\t\u000f\tM1\t\"\u0001\u0003\u0016\u0005\tbn\\*vG\"\u001cuN\\:uC:$H+Y4\u0015\r\t\u001d!q\u0003B\r\u0011\u001d\tYP!\u0005A\u00021BqAa\u0007\u0003\u0012\u0001\u0007A&A\u0002mK:DqAa\bD\t#\t)&\u0001\u0007sK\u0006$7\t[5mIJ,g\u000eC\u0004\u0003$\r#\tB!\n\u0002\u0019I,\u0017\rZ!o]>$\u0018I]4\u0015\t\t\u001d\"\u0011\u0007\t\u0004e\t%\u0012\u0002\u0002B\u0016\u0005[\u0011A\u0001\u0016:fK&\u0019!q\u0006\u0003\u0003\u000bQ\u0013X-Z:\t\u000f\u0005\u001d#\u0011\u0005a\u0001Y!9!QG\"\u0005\n\t]\u0012A\u0004:fC\u0012\f%O]1z\u0003:tw\u000e\u001e\u000b\u0003\u0005s\u0001B!D\u0013\u0003<A\u0019!G!\u0010\n\t\t}\"\u0011\t\u0002\u0012\u00072\f7o\u001d4jY\u0016\feN\\8u\u0003J<\u0017b\u0001B\"\t\ty\u0011I\u001c8pi\u0006$\u0018n\u001c8J]\u001a|7\u000fC\u0004\u0003H\r#\tB!\u0013\u0002+I,\u0017\rZ\"mCN\u001ch-\u001b7f\u0003:tw\u000e^!sOR!!1\bB&\u0011\u001d\t9E!\u0012A\u00021BqAa\u0014D\t#\u0011\t&\u0001\nsK\u0006$\u0017I\u001c8pi\u0006$\u0018n\u001c8J]\u001a|G\u0003\u0002B*\u00053\u00022A\rB+\u0013\u0011\u00119F!\u0011\u0003\u001d\u0005sgn\u001c;bi&|g.\u00138g_\"9\u0011q B'\u0001\u0004a\u0003b\u0002B/\u0007\u0012E\u0011QK\u0001\u0015e\u0016\fGmU=nE>d\u0017I\u001c8pi\u0006$\u0018n\u001c8\t\u000f\t\u00054\t\"\u0005\u0003d\u0005q!/Z1e\u0003:tw\u000e^1uS>tGC\u0001B*\u0011\u001d\u00119g\u0011C\u0005\u0005S\n\u0001C]3bI:{g.R7qif$&/Z3\u0015\r\t\u001d\"1\u000eB7\u0011\u001d\tYP!\u001aA\u00021Bq!a@\u0003f\u0001\u0007A\u0006C\u0004\u0003r\r#\tBa\u001d\u0002\u0011I,\u0017\r\u001a+sK\u0016$\"Aa\n\t\u000f\t]4\t\"\u0001\u0003z\u0005ian\\*vG\"$&/Z3UC\u001e$b!a\r\u0003|\tu\u0004bBA~\u0005k\u0002\r\u0001\f\u0005\b\u0003\u007f\u0014)\b1\u0001-\u0011\u001d\u0011\ti\u0011C\u0001\u0005\u0007\u000bQB]3bI6{G-\u001b4jKJ\u001cHC\u0001BC!\r\u0011$qQ\u0005\u0005\u0005\u0013\u0013iCA\u0005N_\u0012Lg-[3sg\"9!QR\"\u0005\u0012\u0005u\u0017!\u0004:fC\u0012\u001c\u00160\u001c2pYJ+g\rC\u0004\u0003\u0012\u000e#\t\"a2\u0002\u0017I,\u0017\r\u001a(b[\u0016\u0014VM\u001a\u0005\b\u0005+\u001bE\u0011\u0003BL\u0003-\u0011X-\u00193UsB,'+\u001a4\u0015\u0005\u0005\u0015\bb\u0002BN\u0007\u0012E!QA\u0001\u0010e\u0016\fGmQ8ogR\fg\u000e\u001e*fM\"9!qT\"\u0005\u0012\t\r\u0014!\u0005:fC\u0012\feN\\8uCRLwN\u001c*fM\"9!1U\"\u0005\u0012\t\r\u0015\u0001\u0005:fC\u0012lu\u000eZ5gS\u0016\u00148OU3g\u0011\u001d\u00119k\u0011C\t\u0005g\n1B]3bIR\u0013X-\u001a*fM\"9!1V\"\u0005\u0012\t5\u0016a\u0004:fC\u0012$\u0016\u0010]3OC6,'+\u001a4\u0015\u0005\t=\u0006c\u0001\u001a\u00032&!!1WAh\u0005!!\u0016\u0010]3OC6,\u0007b\u0002B\\\u0007\u0012E!\u0011X\u0001\u0010e\u0016\fG\rV3na2\fG/\u001a*fMR\u0011!1\u0018\t\u0004e\tu\u0016\u0002\u0002B`\u0005[\u0011\u0001\u0002V3na2\fG/\u001a\u0005\b\u0005\u0007\u001cE\u0011\u0003Bc\u00039\u0011X-\u00193DCN,G)\u001a4SK\u001a$\"Aa2\u0011\u0007I\u0012I-\u0003\u0003\u0003L\n5\"aB\"bg\u0016$UM\u001a\u0005\b\u0005\u001f\u001cE\u0011\u0003Bi\u00035\u0011X-\u00193WC2$UM\u001a*fMR\u0011!1\u001b\t\u0004e\tU\u0017\u0002\u0002Bl\u0005[\u0011aAV1m\t\u00164\u0007b\u0002Bn\u0007\u0012E!Q\\\u0001\re\u0016\fG-\u00133f]R\u0014VM\u001a\u000b\u0003\u0005?\u00042A\rBq\u0013\u0011\u0011\u0019O!\f\u0003\u000b%#WM\u001c;\t\u000f\t\u001d8\t\"\u0005\u0003j\u0006q!/Z1e)f\u0004X\rR3g%\u00164GC\u0001Bv!\r\u0011$Q^\u0005\u0005\u0005_\u0014iCA\u0004UsB,G)\u001a4\t\u000f\tM8\t\"\u0005\u0003v\u0006\u0001\"/Z1e\u001b\u0016l'-\u001a:EK\u001a\u0014VM\u001a\u000b\u0003\u0005o\u00042A\rB}\u0013\u0011\u0011YP!\f\u0003\u00135+WNY3s\t\u00164\u0007b\u0002B\u0000\u0007\u0012E1\u0011A\u0001\u0012KJ\u0014xN\u001d\"bINKwM\\1ukJ,G\u0003BA\u001a\u0007\u0007Aq!a\u0006\u0003~\u0002\u0007A\bC\u0004\u0004\b\r#\ta!\u0003\u0002-%tg-\u001a:NKRDw\u000eZ!mi\u0016\u0014h.\u0019;jm\u0016$raHB\u0006\u0007\u001f\u0019I\u0002\u0003\u0005\u0004\u000e\r\u0015\u0001\u0019\u0001B\u0014\u0003\r1WO\u001c\u0005\t\u0007#\u0019)\u00011\u0001\u0004\u0014\u00059\u0011M]4ua\u0016\u001c\bCBAK\u0007+\t)/\u0003\u0003\u0004\u0018\u0005}%\u0001\u0002'jgRD\u0001ba\u0007\u0004\u0006\u0001\u0007\u0011Q]\u0001\u0007e\u0016\u001cH\u000f]3\t\u000f\r}1\t\"\u0001\u0004\"\u0005qa.Z<MCjLH+\u001f9f%\u00164G\u0003BB\u0012\u0007S\u00012AMB\u0013\u0013\u0011\u00199#a;\u0003\u00111\u000b'0\u001f+za\u0016Dq!a\u0012\u0004\u001e\u0001\u0007A\u0006C\u0004\u0004.\r#\taa\f\u0002-9,w\u000fT1{sRK\b/\u001a*fM\u0006sG-\u00117jCN$baa\t\u00042\rM\u0002bBA$\u0007W\u0001\r\u0001\f\u0005\b\u0007k\u0019Y\u00031\u0001-\u0003\u0005Q\u0007bBB\u001d\u0007\u0012\u000511H\u0001\fi>$\u0016\u0010]3FeJ|'\u000f\u0006\u0003\u0004>\r\r\u0003c\u0001\u001a\u0004@%!1\u0011IAv\u0005%!\u0016\u0010]3FeJ|'\u000f\u0003\u0005\u0004F\r]\u0002\u0019AB$\u0003\u0005)\u0007cA\r\u0004J%\u001911\n\u0003\u0003/5K7o]5oOJ+\u0017/^5sK6,g\u000e^#se>\u0014\b\"CB(\u0007\u0002\u0007I\u0011BB)\u0003=\u0019w.\u001c9mKRLgnZ*uC\u000e\\WCAB*!\u0015\u0019)fa\u00172\u001b\t\u00199FC\u0002\u0004Zi\f\u0011\"[7nkR\f'\r\\3\n\t\r]1q\u000b\u0005\n\u0007?\u001a\u0005\u0019!C\u0005\u0007C\n1cY8na2,G/\u001b8h'R\f7m[0%KF$2aHB2\u0011)\u0019)g!\u0018\u0002\u0002\u0003\u000711K\u0001\u0004q\u0012\n\u0004\u0002CB5\u0007\u0002\u0006Kaa\u0015\u0002!\r|W\u000e\u001d7fi&twm\u0015;bG.\u0004cABB7\u0007\u0012\u0019yGA\u0006MCjLH+\u001f9f%\u001647CBB6\u0007G\u0019\t\bE\u00023\u0007gJAa!\u001e\u0002l\n)b\t\\1h\u0003\u001etwn\u001d;jG\u000e{W\u000e\u001d7fi\u0016\u0014\bBCA$\u0007W\u0012\t\u0011)A\u0005Y!9\u0011ca\u001b\u0005\u0002\rmD\u0003BB?\u0007\u0003\u0003Baa \u0004l5\t1\tC\u0004\u0002H\re\u0004\u0019\u0001\u0017\t\u0015\r\u001551\u000eb\u0001\n\u0013\u00199)\u0001\beK\u001aLg.\u001a3BiJ+h.\u00133\u0016\u0005\r%\u0005c\u0001\u001a\u0004\f&\u00191Q\u0012\u000e\u0003\u000bI+h.\u00133\t\u0013\rE51\u000eQ\u0001\n\r%\u0015a\u00043fM&tW\rZ!u%Vt\u0017\n\u001a\u0011\t\u0015\rU51\u000eb\u0001\n\u0013\u00199*A\u0001q+\t\u0019I\nE\u0002\u001a\u00077K1a!(\u0005\u0005\u0015\u0001\u0006.Y:f\u0011%\u0019\tka\u001b!\u0002\u0013\u0019I*\u0001\u0002qA!A1QUB6\t#\u00199+\u0001\td_6\u0004H.\u001a;f\u0013:$XM\u001d8bYR\u0019qd!+\t\u000f\u0005\r41\u0015a\u0001c!A1QVB6\t\u0003\u001ay+\u0001\u0005d_6\u0004H.\u001a;f)\ry2\u0011\u0017\u0005\b\u0003G\u001aY\u000b1\u00012\u0011!\u0019)la\u001b\u0005B\r]\u0016\u0001\u00027pC\u0012$2aHB]\u0011\u001d\t\u0019ga-A\u0002E2aa!0D\t\r}&a\u0005'buf$\u0016\u0010]3SK\u001a\fe\u000eZ!mS\u0006\u001c8\u0003BB^\u0007{B!\"a\u0012\u0004<\n\u0005\t\u0015!\u0003-\u0011)\u0019)da/\u0003\u0002\u0003\u0006I\u0001\f\u0005\b#\rmF\u0011ABd)\u0019\u0019Ima3\u0004NB!1qPB^\u0011\u001d\t9e!2A\u00021Bqa!\u000e\u0004F\u0002\u0007A\u0006\u0003\u0005\u0004&\u000emF\u0011IBi)\ry21\u001b\u0005\b\u0003G\u001ay\r1\u00012\u0011%\u00199nQI\u0001\n#\u0019I.\u0001\nsK\u0006$G+\u001f9fI\u0011,g-Y;mi\u0012\nTCABnU\rQ6Q\\\u0016\u0003\u0007?\u0004Ba!9\u0004l6\u001111\u001d\u0006\u0005\u0007K\u001c9/A\u0005v]\u000eDWmY6fI*\u00191\u0011\u001e\u0005\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0004n\u000e\r(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0002")
public abstract class UnPickler {
    public abstract SymbolTable symbolTable();

    public void unpickle(byte[] bytes2, int offset, Symbols.Symbol classRoot, Symbols.Symbol moduleRoot, String filename) {
        try {
            new Scan(this, bytes2, offset, classRoot, moduleRoot, filename).run();
            return;
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new RuntimeException(new StringBuilder().append((Object)"error reading Scala signature of ").append((Object)filename).append((Object)": ").append((Object)throwable.getMessage()).toString());
        }
        catch (MissingRequirementError missingRequirementError) {
            throw missingRequirementError;
        }
        catch (IOException iOException) {
            throw iOException;
        }
    }

    public class Scan
    extends PickleBuffer {
        private final Symbols.Symbol classRoot;
        private final Symbols.Symbol moduleRoot;
        public final String scala$reflect$internal$pickling$UnPickler$Scan$$filename;
        private final Mirrors.RootsBase loadingMirror;
        private final int[] scala$reflect$internal$pickling$UnPickler$Scan$$index;
        private final Object[] scala$reflect$internal$pickling$UnPickler$Scan$$entries;
        private final HashMap<Symbols.Symbol, Scopes.Scope> symScopes;
        private List<Symbols.Symbol> scala$reflect$internal$pickling$UnPickler$Scan$$completingStack;
        public final /* synthetic */ UnPickler $outer;

        public boolean debug() {
            return BoxesRunTime.unboxToBoolean(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().settings().debug().value());
        }

        private Mirrors.RootsBase loadingMirror() {
            return this.loadingMirror;
        }

        public int[] scala$reflect$internal$pickling$UnPickler$Scan$$index() {
            return this.scala$reflect$internal$pickling$UnPickler$Scan$$index;
        }

        public Object[] scala$reflect$internal$pickling$UnPickler$Scan$$entries() {
            return this.scala$reflect$internal$pickling$UnPickler$Scan$$entries;
        }

        private HashMap<Symbols.Symbol, Scopes.Scope> symScopes() {
            return this.symScopes;
        }

        private void expect(int expected, Function0<String> msg) {
            int tag = this.readByte();
            if (tag != expected) {
                throw this.errorBadSignature(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " (", ")"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{msg.apply(), BoxesRunTime.boxToInteger(tag)})));
            }
        }

        /*
         * WARNING - void declaration
         */
        private <T> T runAtIndex(int i, Function0<T> body2) {
            T t;
            int saved = this.readIndex();
            this.readIndex_$eq(this.scala$reflect$internal$pickling$UnPickler$Scan$$index()[i]);
            try {
                t = body2.apply();
            }
            catch (Throwable throwable) {
                void var3_3;
                this.readIndex_$eq((int)var3_3);
                throw throwable;
            }
            this.readIndex_$eq(saved);
            return t;
        }

        /*
         * WARNING - void declaration
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void run() {
            IntRef i = IntRef.create(0);
            while (i.elem < this.scala$reflect$internal$pickling$UnPickler$Scan$$index().length) {
                BoxedUnit boxedUnit;
                if (this.scala$reflect$internal$pickling$UnPickler$Scan$$entries()[i.elem] == null && this.isSymbolEntry(i.elem)) {
                    int n = i.elem;
                    Scan scan4 = this;
                    int saved1 = this.readIndex();
                    this.readIndex_$eq(this.scala$reflect$internal$pickling$UnPickler$Scan$$index()[n]);
                    try {
                        this.scala$reflect$internal$pickling$UnPickler$Scan$$entries()[i.elem] = this.readSymbol();
                        boxedUnit = BoxedUnit.UNIT;
                    }
                    catch (Throwable throwable) {
                        void var6_4;
                        scan4.readIndex_$eq((int)var6_4);
                        throw throwable;
                    }
                    this.readIndex_$eq(saved1);
                } else {
                    boxedUnit = BoxedUnit.UNIT;
                }
                ++i.elem;
            }
            i.elem = 0;
            while (true) {
                block13: {
                    BoxedUnit boxedUnit;
                    block11: {
                        block14: {
                            block12: {
                                if (i.elem >= this.scala$reflect$internal$pickling$UnPickler$Scan$$index().length) {
                                    return;
                                }
                                if (this.scala$reflect$internal$pickling$UnPickler$Scan$$entries()[i.elem] != null) break block11;
                                if (!this.isSymbolAnnotationEntry(i.elem)) break block12;
                                int n = i.elem;
                                Scan scan3 = this;
                                int saved2 = this.readIndex();
                                this.readIndex_$eq(this.scala$reflect$internal$pickling$UnPickler$Scan$$index()[n]);
                                try {
                                    this.readSymbolAnnotation();
                                    boxedUnit = BoxedUnit.UNIT;
                                }
                                catch (Throwable throwable) {
                                    void var9_8;
                                    scan3.readIndex_$eq((int)var9_8);
                                    throw throwable;
                                }
                                this.readIndex_$eq(saved2);
                                break block13;
                            }
                            if (!this.isChildrenEntry(i.elem)) break block14;
                            int n = i.elem;
                            Scan scan2 = this;
                            int saved3 = this.readIndex();
                            this.readIndex_$eq(this.scala$reflect$internal$pickling$UnPickler$Scan$$index()[n]);
                            try {}
                            catch (Throwable throwable) {
                                void var12_10;
                                scan2.readIndex_$eq((int)var12_10);
                                throw throwable;
                            }
                            this.readChildren();
                            boxedUnit = BoxedUnit.UNIT;
                            this.readIndex_$eq(saved3);
                            break block13;
                        }
                        boxedUnit = BoxedUnit.UNIT;
                        break block13;
                    }
                    boxedUnit = BoxedUnit.UNIT;
                }
                ++i.elem;
            }
        }

        private void checkVersion() {
            int major = this.readNat();
            int minor = this.readNat();
            if (major != PickleFormat$.MODULE$.MajorVersion() || minor > PickleFormat$.MODULE$.MinorVersion()) {
                throw new IOException(new StringBuilder().append((Object)"Scala signature ").append((Object)this.classRoot.decodedName()).append((Object)" has wrong version\n expected: ").append(BoxesRunTime.boxToInteger(PickleFormat$.MODULE$.MajorVersion())).append((Object)".").append(BoxesRunTime.boxToInteger(PickleFormat$.MODULE$.MinorVersion())).append((Object)"\n found: ").append(BoxesRunTime.boxToInteger(major)).append((Object)".").append(BoxesRunTime.boxToInteger(minor)).append((Object)" in ").append((Object)this.scala$reflect$internal$pickling$UnPickler$Scan$$filename).toString());
            }
        }

        public Scopes.Scope symScope(Symbols.Symbol sym) {
            return this.symScopes().getOrElseUpdate(sym, (Function0<Scopes.Scope>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Scan $outer;

                public final Scopes.Scope apply() {
                    return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().newScope();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        public boolean isSymbolEntry(int i) {
            byte tag = this.bytes()[this.scala$reflect$internal$pickling$UnPickler$Scan$$index()[i]];
            return 3 <= tag && tag <= 8 && (tag != 6 || !this.isRefinementSymbolEntry(i));
        }

        public boolean isSymbolRef(int i) {
            byte tag = this.bytes()[this.scala$reflect$internal$pickling$UnPickler$Scan$$index()[i]];
            return 3 <= tag && tag <= 10;
        }

        public boolean isNameEntry(int i) {
            byte tag = this.bytes()[this.scala$reflect$internal$pickling$UnPickler$Scan$$index()[i]];
            return tag == 1 || tag == 2;
        }

        public boolean isSymbolAnnotationEntry(int i) {
            byte tag = this.bytes()[this.scala$reflect$internal$pickling$UnPickler$Scan$$index()[i]];
            return tag == 40;
        }

        public boolean isChildrenEntry(int i) {
            byte tag = this.bytes()[this.scala$reflect$internal$pickling$UnPickler$Scan$$index()[i]];
            return tag == 41;
        }

        private Either<Object, Symbols.Symbol> maybeReadSymbol() {
            int n = this.readNat();
            return this.isSymbolRef(n) ? package$.MODULE$.Right().apply(this.at(n, (Function0)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Scan $outer;

                public final Symbols.Symbol apply() {
                    return this.$outer.readSymbol();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }))) : package$.MODULE$.Left().apply(BoxesRunTime.boxToInteger(n));
        }

        public boolean isRefinementSymbolEntry(int i) {
            int savedIndex = this.readIndex();
            this.readIndex_$eq(this.scala$reflect$internal$pickling$UnPickler$Scan$$index()[i]);
            int tag = this.readByte();
            Predef$.MODULE$.assert(tag == 6);
            this.readNat();
            Names.Name name = this.readNameRef();
            Names.TypeName typeName = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().tpnme().REFINE_CLASS_NAME();
            boolean result2 = !(name != null ? !name.equals(typeName) : typeName != null);
            this.readIndex_$eq(savedIndex);
            return result2;
        }

        public <T> T at(int i, Function0<T> op) {
            Object r = this.scala$reflect$internal$pickling$UnPickler$Scan$$entries()[i];
            if (r == null) {
                int savedIndex = this.readIndex();
                this.readIndex_$eq(this.scala$reflect$internal$pickling$UnPickler$Scan$$index()[i]);
                r = op.apply();
                boolean bl = this.scala$reflect$internal$pickling$UnPickler$Scan$$entries()[i] == null;
                Predef$ predef$ = Predef$.MODULE$;
                if (!bl) {
                    throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(this.scala$reflect$internal$pickling$UnPickler$Scan$$entries()[i]).toString());
                }
                this.scala$reflect$internal$pickling$UnPickler$Scan$$entries()[i] = r;
                this.readIndex_$eq(savedIndex);
            }
            return (T)r;
        }

        public Names.Name readName() {
            Names.Name name;
            int tag = this.readByte();
            int len = this.readNat();
            switch (tag) {
                default: {
                    throw this.errorBadSignature(new StringBuilder().append((Object)"bad name tag: ").append(BoxesRunTime.boxToInteger(tag)).toString());
                }
                case 2: {
                    name = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().newTypeName(this.bytes(), this.readIndex(), len);
                    break;
                }
                case 1: {
                    name = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().newTermName(this.bytes(), this.readIndex(), len);
                }
            }
            return name;
        }

        private int readEnd() {
            return this.readNat() + this.readIndex();
        }

        /*
         * WARNING - void declaration
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public Symbols.Symbol readSymbol() {
            int tag = this.readByte();
            int end = this.readEnd();
            switch (tag) {
                default: {
                    Symbols.Symbol symbol;
                    Tuple2<Symbols.NoSymbol, Object> tuple2;
                    int nameref = this.readNat();
                    Names.Name name = this.at(nameref, (Function0)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Names.Name apply() {
                            return this.$outer.readName();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                    Symbols.Symbol owner2 = this.readSymbolRef();
                    long flags = Flags$.MODULE$.pickledToRawFlags().apply$mcJJ$sp(this.readLongNat());
                    Either<Object, Symbols.Symbol> either2 = this.maybeReadSymbol();
                    if (either2 instanceof Left) {
                        Left left = (Left)either2;
                        Object a = left.a();
                        Symbols.NoSymbol noSymbol = Predef$.MODULE$.ArrowAssoc(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol());
                        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
                        tuple2 = new Tuple2(noSymbol, a);
                    } else {
                        if (!(either2 instanceof Right)) throw new MatchError(either2);
                        Right right = (Right)either2;
                        Integer n = BoxesRunTime.boxToInteger(this.readNat());
                        Object b = Predef$.MODULE$.ArrowAssoc(right.b());
                        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
                        tuple2 = new Tuple2(b, n);
                    }
                    Tuple2<Symbols.NoSymbol, Integer> tuple22 = new Tuple2<Symbols.NoSymbol, Integer>(tuple2._1(), BoxesRunTime.boxToInteger(tuple2._2$mcI$sp()));
                    Symbols.Symbol privateWithin = tuple22._1();
                    int inforef = tuple22._2$mcI$sp();
                    switch (tag) {
                        default: {
                            throw this.errorBadSignature(new StringBuilder().append((Object)"bad symbol tag: ").append(BoxesRunTime.boxToInteger(tag)).toString());
                        }
                        case 8: {
                            if (this.isModuleRoot$1(name, owner2)) {
                                throw this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().abort(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"VALsym at module root: owner = ", ", name = ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{owner2, name})));
                            }
                            symbol = owner2.newTermSymbol(name.toTermName(), this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoPosition(), this.pflags$1(flags));
                            return this.finishSym$1(symbol, end, privateWithin, inforef);
                        }
                        case 7: {
                            Symbols.Symbol clazz = this.at(inforef, (Function0)((Object)new Serializable(this){
                                public static final long serialVersionUID = 0L;
                                private final /* synthetic */ Scan $outer;

                                public final Types.Type apply() {
                                    return this.$outer.readType(this.$outer.readType$default$1());
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                }
                            })).typeSymbol();
                            if (this.isModuleRoot$1(name, owner2)) {
                                symbol = this.moduleRoot.setFlag(this.pflags$1(flags));
                                return this.finishSym$1(symbol, end, privateWithin, inforef);
                            }
                            symbol = owner2.newLinkedModule(clazz, this.pflags$1(flags));
                            return this.finishSym$1(symbol, end, privateWithin, inforef);
                        }
                        case 6: {
                            Symbols.Symbol sym;
                            Symbols.Symbol symbol2 = this.isClassRoot$1(name, owner2) ? (this.isModuleFlag$1(flags) ? this.moduleRoot.moduleClass().setFlag(this.pflags$1(flags)) : this.classRoot.setFlag(this.pflags$1(flags))) : (sym = owner2.newClassSymbol(name.toTypeName(), this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoPosition(), this.pflags$1(flags)));
                            if (!this.atEnd$1(end)) {
                                sym.typeOfThis_$eq(this.newLazyTypeRef(this.readNat()));
                            }
                            symbol = sym;
                            return this.finishSym$1(symbol, end, privateWithin, inforef);
                        }
                        case 4: 
                        case 5: {
                            symbol = owner2.newNonClassSymbol(name.toTypeName(), this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoPosition(), this.pflags$1(flags));
                        }
                    }
                    return this.finishSym$1(symbol, end, privateWithin, inforef);
                }
                case 9: 
                case 10: {
                    void var22_2;
                    void var21_1;
                    return this.readExtSymbol$1((int)var21_1, (int)var22_2);
                }
                case 3: 
            }
            return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol();
        }

        public Types.Type readType(boolean forceProperType) {
            Types.Type type;
            int tag = this.readByte();
            int end = this.readEnd();
            switch (tag) {
                default: {
                    throw new MatchError(BoxesRunTime.boxToInteger(tag));
                }
                case 42: {
                    Types.Type x$10 = this.readTypeRef();
                    List x$11 = this.readAnnots$1(end);
                    type = new Types.AnnotatedType(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), x$11, x$10);
                    break;
                }
                case 48: {
                    Types.Type x$8 = this.readTypeRef();
                    List x$9 = this.readSymbols$1(end);
                    type = new Types.ExistentialType(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), x$9, x$8);
                    break;
                }
                case 21: {
                    type = this.PolyOrNullaryType$1(this.readTypeRef(), this.readSymbols$1(end));
                    break;
                }
                case 20: {
                    type = this.MethodTypeRef$1(this.readTypeRef(), this.readSymbols$1(end));
                    break;
                }
                case 18: 
                case 19: {
                    type = this.CompoundType$1(this.readSymbolRef(), this.readTypes$1(end), tag);
                    break;
                }
                case 17: {
                    type = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().TypeBounds().apply(this.readTypeRef(), this.readTypeRef());
                    break;
                }
                case 16: {
                    type = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().TypeRef().apply(this.readTypeRef(), this.readSymbolRef(), this.readTypes$1(end));
                    break;
                }
                case 15: {
                    type = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().ConstantType().apply(this.readConstantRef());
                    break;
                }
                case 46: {
                    type = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().SuperType().apply(this.readTypeRef(), this.readTypeRef());
                    break;
                }
                case 14: {
                    type = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().SingleType().apply(this.readTypeRef(), (Symbols.Symbol)this.readSymbolRef().filter((Function1)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final boolean apply(Symbols.Symbol x$2) {
                            return x$2.isStable();
                        }
                    })));
                    break;
                }
                case 13: {
                    type = this.readThisType$1();
                    break;
                }
                case 12: {
                    type = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoPrefix();
                    break;
                }
                case 11: {
                    type = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoType();
                }
            }
            return type;
        }

        public boolean readType$default$1() {
            return false;
        }

        public Types.Type noSuchTypeTag(int tag, int end) {
            throw this.errorBadSignature(new StringBuilder().append((Object)"bad type tag: ").append(BoxesRunTime.boxToInteger(tag)).toString());
        }

        public Constants.Constant readConstant() {
            Constants.Constant constant;
            int tag = this.readByte();
            int len = this.readNat();
            switch (tag) {
                default: {
                    constant = this.noSuchConstantTag(tag, len);
                    break;
                }
                case 36: {
                    constant = new Constants.Constant(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.readSymbolRef());
                    break;
                }
                case 35: {
                    constant = new Constants.Constant(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.readTypeRef());
                    break;
                }
                case 34: {
                    constant = new Constants.Constant(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), null);
                    break;
                }
                case 33: {
                    constant = new Constants.Constant(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.readNameRef().toString());
                    break;
                }
                case 32: {
                    constant = new Constants.Constant(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), BoxesRunTime.boxToDouble(Double.longBitsToDouble(this.readLong(len))));
                    break;
                }
                case 31: {
                    constant = new Constants.Constant(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), BoxesRunTime.boxToFloat(Float.intBitsToFloat((int)this.readLong(len))));
                    break;
                }
                case 30: {
                    constant = new Constants.Constant(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), BoxesRunTime.boxToLong(this.readLong(len)));
                    break;
                }
                case 29: {
                    constant = new Constants.Constant(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), BoxesRunTime.boxToInteger((int)this.readLong(len)));
                    break;
                }
                case 28: {
                    constant = new Constants.Constant(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), BoxesRunTime.boxToCharacter((char)this.readLong(len)));
                    break;
                }
                case 27: {
                    constant = new Constants.Constant(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), BoxesRunTime.boxToShort((short)this.readLong(len)));
                    break;
                }
                case 26: {
                    constant = new Constants.Constant(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), BoxesRunTime.boxToByte((byte)this.readLong(len)));
                    break;
                }
                case 25: {
                    constant = new Constants.Constant(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), BoxesRunTime.boxToBoolean(this.readLong(len) != 0L));
                    break;
                }
                case 24: {
                    constant = new Constants.Constant(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), BoxedUnit.UNIT);
                }
            }
            return constant;
        }

        public Constants.Constant noSuchConstantTag(int tag, int len) {
            throw this.errorBadSignature(new StringBuilder().append((Object)"bad constant tag: ").append(BoxesRunTime.boxToInteger(tag)).toString());
        }

        public void readChildren() {
            int tag = this.readByte();
            Predef$.MODULE$.assert(tag == 41);
            int end = this.readEnd();
            Symbols.Symbol target = this.readSymbolRef();
            while (this.readIndex() != end) {
                target.addChild(this.readSymbolRef());
            }
        }

        public Trees.Tree readAnnotArg(int i) {
            Trees.Tree tree;
            byte by2 = this.bytes()[this.scala$reflect$internal$pickling$UnPickler$Scan$$index()[i]];
            switch (by2) {
                default: {
                    Constants.Constant constant = this.at(i, (Function0)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Constants.Constant apply() {
                            return this.$outer.readConstant();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                    tree = new Trees.Literal(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), constant).setType(constant.tpe());
                    break;
                }
                case 49: {
                    tree = this.at(i, (Function0)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.Tree apply() {
                            return this.$outer.readTree();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                }
            }
            return tree;
        }

        public AnnotationInfos.ClassfileAnnotArg[] scala$reflect$internal$pickling$UnPickler$Scan$$readArrayAnnot() {
            this.readByte();
            int end = this.readEnd();
            return (AnnotationInfos.ClassfileAnnotArg[])this.until(end, new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Scan $outer;

                public final AnnotationInfos.ClassfileAnnotArg apply() {
                    return this.$outer.readClassfileAnnotArg(this.$outer.readNat());
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }).toArray(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().JavaArgumentTag());
        }

        public AnnotationInfos.ClassfileAnnotArg readClassfileAnnotArg(int i) {
            AnnotationInfos.ClassfileAnnotArg classfileAnnotArg;
            byte by2 = this.bytes()[this.scala$reflect$internal$pickling$UnPickler$Scan$$index()[i]];
            switch (by2) {
                default: {
                    classfileAnnotArg = new AnnotationInfos.LiteralAnnotArg(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.at(i, (Function0)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Constants.Constant apply() {
                            return this.$outer.readConstant();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    })));
                    break;
                }
                case 44: {
                    classfileAnnotArg = this.at(i, (Function0)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final AnnotationInfos.ArrayAnnotArg apply() {
                            return new AnnotationInfos.ArrayAnnotArg(this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$readArrayAnnot());
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                    break;
                }
                case 43: {
                    classfileAnnotArg = new AnnotationInfos.NestedAnnotArg(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.at(i, (Function0)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final AnnotationInfos.AnnotationInfo apply() {
                            return this.$outer.readAnnotation();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    })));
                }
            }
            return classfileAnnotArg;
        }

        public AnnotationInfos.AnnotationInfo readAnnotationInfo(int end) {
            Types.Type atp = this.readTypeRef();
            ListBuffer args = new ListBuffer();
            ListBuffer assocs2 = new ListBuffer();
            while (this.readIndex() != end) {
                Growable growable;
                int argref = this.readNat();
                if (this.isNameEntry(argref)) {
                    Names.Name name = this.at(argref, (Function0)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Names.Name apply() {
                            return this.$outer.readName();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                    AnnotationInfos.ClassfileAnnotArg arg = this.readClassfileAnnotArg(this.readNat());
                    growable = assocs2.$plus$eq(new Tuple2<Names.Name, AnnotationInfos.ClassfileAnnotArg>(name, arg));
                    continue;
                }
                growable = args.$plus$eq(this.readAnnotArg(argref));
            }
            return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().AnnotationInfo().apply(atp, args.toList(), assocs2.toList());
        }

        public void readSymbolAnnotation() {
            this.expect(40, (Function0<String>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final String apply() {
                    return "symbol annotation expected";
                }
            }));
            int end = this.readEnd();
            Symbols.Symbol target = this.readSymbolRef();
            target.addAnnotation(this.readAnnotationInfo(end));
        }

        public AnnotationInfos.AnnotationInfo readAnnotation() {
            int tag = this.readByte();
            if (tag != 43) {
                throw this.errorBadSignature(new StringBuilder().append((Object)"annotation expected (").append(BoxesRunTime.boxToInteger(tag)).append((Object)")").toString());
            }
            int end = this.readEnd();
            return this.readAnnotationInfo(end);
        }

        private Trees.Tree readNonEmptyTree(int tag, int end) {
            Types.Type tpe = this.readTypeRef();
            Symbols.Symbol sym = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().isTreeSymbolPickled(tag) ? this.readSymbolRef() : null;
            Trees.Tree result2 = this.readTree$1(tpe, tag, end);
            Object object = sym != null ? result2.setSymbol(sym) : BoxedUnit.UNIT;
            return result2.setType(tpe);
        }

        public Trees.Tree readTree() {
            Trees.Tree tree;
            this.expect(49, (Function0<String>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final String apply() {
                    return "tree expected";
                }
            }));
            int end = this.readEnd();
            int n = this.readByte();
            switch (n) {
                default: {
                    tree = this.readNonEmptyTree(n, end);
                    break;
                }
                case 1: {
                    tree = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().EmptyTree();
                }
            }
            return tree;
        }

        public Nothing$ noSuchTreeTag(int tag, int end) {
            return this.errorBadSignature(new StringBuilder().append((Object)"unknown tree type (").append(BoxesRunTime.boxToInteger(tag)).append((Object)")").toString());
        }

        public Trees.Modifiers readModifiers() {
            int tag = this.readNat();
            if (tag != 50) {
                throw this.errorBadSignature(new StringBuilder().append((Object)"expected a modifiers tag (").append(BoxesRunTime.boxToInteger(tag)).append((Object)")").toString());
            }
            this.readEnd();
            int pflagsHi = this.readNat();
            int pflagsLo = this.readNat();
            long pflags = ((long)pflagsHi << 32) + (long)pflagsLo;
            long flags = Flags$.MODULE$.pickledToRawFlags().apply$mcJJ$sp(pflags);
            Names.Name privateWithin = this.readNameRef();
            return new Trees.Modifiers(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), flags, privateWithin, Nil$.MODULE$);
        }

        public Symbols.Symbol readSymbolRef() {
            int i = this.readNat();
            Object r = this.scala$reflect$internal$pickling$UnPickler$Scan$$entries()[i];
            if (r == null) {
                int savedIndex = this.readIndex();
                this.readIndex_$eq(this.scala$reflect$internal$pickling$UnPickler$Scan$$index()[i]);
                r = this.readSymbol();
                boolean bl = this.scala$reflect$internal$pickling$UnPickler$Scan$$entries()[i] == null;
                Predef$ predef$ = Predef$.MODULE$;
                if (!bl) {
                    throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(this.scala$reflect$internal$pickling$UnPickler$Scan$$entries()[i]).toString());
                }
                this.scala$reflect$internal$pickling$UnPickler$Scan$$entries()[i] = r;
                this.readIndex_$eq(savedIndex);
            }
            return (Symbols.Symbol)r;
        }

        public Names.Name readNameRef() {
            return this.at(this.readNat(), (Function0)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Scan $outer;

                public final Names.Name apply() {
                    return this.$outer.readName();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        public Types.Type readTypeRef() {
            return this.at(this.readNat(), (Function0)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Scan $outer;

                public final Types.Type apply() {
                    return this.$outer.readType(this.$outer.readType$default$1());
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        public Constants.Constant readConstantRef() {
            return this.at(this.readNat(), (Function0)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Scan $outer;

                public final Constants.Constant apply() {
                    return this.$outer.readConstant();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        public AnnotationInfos.AnnotationInfo readAnnotationRef() {
            return this.at(this.readNat(), (Function0)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Scan $outer;

                public final AnnotationInfos.AnnotationInfo apply() {
                    return this.$outer.readAnnotation();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        public Trees.Modifiers readModifiersRef() {
            return this.at(this.readNat(), (Function0)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Scan $outer;

                public final Trees.Modifiers apply() {
                    return this.$outer.readModifiers();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        public Trees.Tree readTreeRef() {
            return this.at(this.readNat(), (Function0)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Scan $outer;

                public final Trees.Tree apply() {
                    return this.$outer.readTree();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        public Names.TypeName readTypeNameRef() {
            return this.readNameRef().toTypeName();
        }

        public Trees.Template readTemplateRef() {
            Trees.Tree tree = this.readTreeRef();
            if (tree instanceof Trees.Template) {
                Trees.Template template = (Trees.Template)tree;
                return template;
            }
            throw this.errorBadSignature(new StringBuilder().append((Object)"expected a template (").append(tree).append((Object)")").toString());
        }

        public Trees.CaseDef readCaseDefRef() {
            Trees.Tree tree = this.readTreeRef();
            if (tree instanceof Trees.CaseDef) {
                Trees.CaseDef caseDef = (Trees.CaseDef)tree;
                return caseDef;
            }
            throw this.errorBadSignature(new StringBuilder().append((Object)"expected a case def (").append(tree).append((Object)")").toString());
        }

        public Trees.ValDef readValDefRef() {
            Trees.Tree tree = this.readTreeRef();
            if (tree instanceof Trees.ValDef) {
                Trees.ValDef valDef = (Trees.ValDef)tree;
                return valDef;
            }
            throw this.errorBadSignature(new StringBuilder().append((Object)"expected a ValDef (").append(tree).append((Object)")").toString());
        }

        public Trees.Ident readIdentRef() {
            Trees.Tree tree = this.readTreeRef();
            if (tree instanceof Trees.Ident) {
                Trees.Ident ident = (Trees.Ident)tree;
                return ident;
            }
            throw this.errorBadSignature(new StringBuilder().append((Object)"expected an Ident (").append(tree).append((Object)")").toString());
        }

        public Trees.TypeDef readTypeDefRef() {
            Trees.Tree tree = this.readTreeRef();
            if (tree instanceof Trees.TypeDef) {
                Trees.TypeDef typeDef = (Trees.TypeDef)tree;
                return typeDef;
            }
            throw this.errorBadSignature(new StringBuilder().append((Object)"expected an TypeDef (").append(tree).append((Object)")").toString());
        }

        public Trees.MemberDef readMemberDefRef() {
            Trees.Tree tree = this.readTreeRef();
            if (tree instanceof Trees.MemberDef) {
                Trees.MemberDef memberDef = (Trees.MemberDef)tree;
                return memberDef;
            }
            throw this.errorBadSignature(new StringBuilder().append((Object)"expected an MemberDef (").append(tree).append((Object)")").toString());
        }

        public Nothing$ errorBadSignature(String msg) {
            throw new RuntimeException(new StringBuilder().append((Object)"malformed Scala signature of ").append(this.classRoot.name()).append((Object)" at ").append(BoxesRunTime.boxToInteger(this.readIndex())).append((Object)"; ").append((Object)msg).toString());
        }

        public void inferMethodAlternative(Trees.Tree fun, List<Types.Type> argtpes, Types.Type restpe) {
        }

        public Types.LazyType newLazyTypeRef(int i) {
            return new LazyTypeRef(this, i);
        }

        public Types.LazyType newLazyTypeRefAndAlias(int i, int j) {
            return new LazyTypeRefAndAlias(this, i, j);
        }

        public Types.TypeError toTypeError(MissingRequirementError e) {
            return new Types.TypeError(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), e.msg());
        }

        public List<Symbols.Symbol> scala$reflect$internal$pickling$UnPickler$Scan$$completingStack() {
            return this.scala$reflect$internal$pickling$UnPickler$Scan$$completingStack;
        }

        public void scala$reflect$internal$pickling$UnPickler$Scan$$completingStack_$eq(List<Symbols.Symbol> x$1) {
            this.scala$reflect$internal$pickling$UnPickler$Scan$$completingStack = x$1;
        }

        public /* synthetic */ UnPickler scala$reflect$internal$pickling$UnPickler$Scan$$$outer() {
            return this.$outer;
        }

        private final boolean atEnd$1(int end$1) {
            return this.readIndex() == end$1;
        }

        public final Symbols.Symbol scala$reflect$internal$pickling$UnPickler$Scan$$adjust$1(Symbols.Symbol sym, int tag$1) {
            return tag$1 == 9 ? sym : sym.moduleClass();
        }

        public final Symbols.Symbol scala$reflect$internal$pickling$UnPickler$Scan$$fromName$1(Names.Name name, int tag$1, Symbols.Symbol owner$2) {
            Symbols.Symbol symbol;
            Names.TermName termName = name.toTermName();
            Names.Name name2 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().nme().ROOT();
            if (!(name2 != null ? !name2.equals(termName) : termName != null)) {
                symbol = this.loadingMirror().RootClass();
            } else {
                Names.TermName termName2 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().nme().ROOTPKG();
                if (!(termName2 != null ? !termName2.equals(termName) : termName != null)) {
                    symbol = this.loadingMirror().RootPackage();
                } else {
                    Symbols.Symbol symbol2 = owner$2 instanceof Symbols.StubSymbol ? this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol() : owner$2.info().decl(name);
                    symbol = this.scala$reflect$internal$pickling$UnPickler$Scan$$adjust$1(symbol2, tag$1);
                }
            }
            return symbol;
        }

        public final Symbols.Symbol scala$reflect$internal$pickling$UnPickler$Scan$$nestedObjectSymbol$1(int tag$1, Names.Name name$2, Symbols.Symbol owner$2) {
            Symbols.Symbol moduleVar;
            if (owner$2.isOverloaded()) {
                return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol();
            }
            if (tag$1 == 10 && (moduleVar = owner$2.info().decl(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().nme().moduleVarName(name$2.toTermName()))).isLazyAccessor()) {
                return moduleVar.lazyAccessor().lazyAccessor();
            }
            return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public final String scala$reflect$internal$pickling$UnPickler$Scan$$moduleAdvice$1(String missing) {
            Option option;
            Some<Tuple2<String, String>> module;
            Option option2 = missing.startsWith("scala.xml") ? new Some<Tuple2<String, String>>(new Tuple2<String, String>("org.scala-lang.modules", "scala-xml")) : (missing.startsWith("scala.util.parsing") ? new Some<Tuple2<String, String>>(new Tuple2<String, String>("org.scala-lang.modules", "scala-parser-combinators")) : (missing.startsWith("scala.swing") ? new Some<Tuple2<String, String>>(new Tuple2<String, String>("org.scala-lang.modules", "scala-swing")) : (module = missing.startsWith("scala.util.continuations") ? new Some<Tuple2<String, String>>(new Tuple2<String, String>("org.scala-lang.plugins", "scala-continuations-library")) : None$.MODULE$)));
            if (!((Option)module).isEmpty()) {
                Tuple2 tuple2 = (Tuple2)((Option)module).get();
                if (tuple2 == null) throw new MatchError(tuple2);
                String string2 = new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"\\n(NOTE: It looks like the ", " module is missing; try adding a dependency on \"", "\" : \"", "\".\n               |       See http://docs.scala-lang.org/overviews/ for more information.)"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{tuple2._2(), tuple2._1(), tuple2._2()}));
                Predef$ predef$ = Predef$.MODULE$;
                Some<String> some = new Some<String>(new StringOps(string2).stripMargin());
                option = some;
            } else {
                option = None$.MODULE$;
            }
            Option option3 = option;
            if (option.isEmpty()) return "";
            String string3 = option3.get();
            return string3;
        }

        private final Symbols.Symbol localDummy$1(Names.Name name$2, Symbols.Symbol owner$2) {
            return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().nme().isLocalDummyName(name$2) ? owner$2.newLocalDummy(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoPosition()) : this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol();
        }

        private final Symbols.Symbol readExtSymbol$1(int tag$1, int end$1) {
            Symbols.Symbol symbol;
            Names.Name name = this.readNameRef();
            Symbols.Symbol owner2 = this.atEnd$1(end$1) ? this.loadingMirror().RootClass() : this.readSymbolRef();
            Symbols.Symbol symbol2 = this.localDummy$1(name, owner2);
            Serializable serializable = new Serializable(this, tag$1, name, owner2){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ Scan $outer;
                public final int tag$1;
                public final Names.Name name$2;
                public final Symbols.Symbol owner$2;

                public final Symbols.Symbol apply() {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        public final /* synthetic */ Scan$$anonfun$readExtSymbol$1$2 $outer;

                        public final Symbols.Symbol apply() {
                            Serializable serializable = new Serializable(this){
                                public static final long serialVersionUID = 0L;
                                public final /* synthetic */ Scan$$anonfun$readExtSymbol$1$2$$anonfun$apply$1 $outer;

                                public final Symbols.Symbol apply() {
                                    Serializable serializable = new Serializable(this){
                                        public static final long serialVersionUID = 0L;
                                        public final /* synthetic */ Scan$$anonfun$readExtSymbol$1$2$$anonfun$apply$1$$anonfun$apply$2 $outer;

                                        public final Symbols.Symbol apply() {
                                            String advice = this.$outer.$outer.$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$moduleAdvice$1(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", ".", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$anonfun$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$$outer().owner$2.fullName(), this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$anonfun$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$$outer().name$2})));
                                            Serializable serializable = new Serializable(this){
                                                public static final long serialVersionUID = 0L;
                                                public final /* synthetic */ Scan$$anonfun$readExtSymbol$1$2$$anonfun$apply$1$$anonfun$apply$2$$anonfun$apply$3 $outer;

                                                public final Symbols.NoSymbol apply() {
                                                    return this.$outer.$outer.$outer.$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol();
                                                }
                                                {
                                                    if ($outer == null) {
                                                        throw null;
                                                    }
                                                    this.$outer = $outer;
                                                }
                                            };
                                            Option<A> option = this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$anonfun$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$completingStack().headOption();
                                            Symbols.Symbol lazyCompletingSymbol = (Symbols.Symbol)(!option.isEmpty() ? option.get() : serializable.apply());
                                            String string2 = new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"|Symbol '", " ", ".", "' is missing from the classpath.\n                      |This symbol is required by '", " ", "'.\n                      |Make sure that ", " is in your classpath and check for conflicting dependencies with `-Ylog-classpath`.\n                      |A full rebuild may help if '", "' was compiled against an incompatible version of ", ".", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$anonfun$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$$outer().name$2.nameKind(), this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$anonfun$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$$outer().owner$2.fullName(), this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$anonfun$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$$outer().name$2, lazyCompletingSymbol.kindString(), lazyCompletingSymbol.fullName(), this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$anonfun$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$$outer().name$2.longString(), this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$anonfun$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$filename, this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$anonfun$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$$outer().owner$2.fullName(), advice}));
                                            Predef$ predef$ = Predef$.MODULE$;
                                            String missingMessage = new StringOps(string2).stripMargin();
                                            Names.Name stubName = this.$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$$outer().tag$1 == 9 ? this.$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$$outer().name$2 : this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$anonfun$$$outer().$outer.name$2.toTypeName();
                                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$anonfun$$$outer().$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol().newStubSymbol(stubName, missingMessage, this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$anonfun$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$$outer().$outer.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol().newStubSymbol$default$3());
                                        }

                                        public /* synthetic */ Scan$$anonfun$readExtSymbol$1$2$$anonfun$apply$1$$anonfun$apply$2 scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$anonfun$$anonfun$$$outer() {
                                            return this.$outer;
                                        }
                                        {
                                            if ($outer == null) {
                                                throw null;
                                            }
                                            this.$outer = $outer;
                                        }
                                    };
                                    Symbols.Symbol symbol = this.$outer.$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$adjust$1(this.$outer.$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().mirrorThatLoaded(this.$outer.$outer.owner$2).missingHook(this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$$outer().owner$2, this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$$outer().name$2), this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$$outer().tag$1);
                                    return symbol != symbol.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? symbol : serializable.apply();
                                }

                                public /* synthetic */ Scan$$anonfun$readExtSymbol$1$2$$anonfun$apply$1 scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$anonfun$$$outer() {
                                    return this.$outer;
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                }
                            };
                            Symbols.Symbol symbol = this.$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$nestedObjectSymbol$1(this.$outer.tag$1, this.$outer.name$2, this.$outer.owner$2);
                            return symbol != symbol.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? symbol : serializable.apply();
                        }

                        public /* synthetic */ Scan$$anonfun$readExtSymbol$1$2 scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$anonfun$$$outer() {
                            return this.$outer;
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Symbols.Symbol symbol = this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$fromName$1(this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().nme().expandedName(this.name$2.toTermName(), this.owner$2), this.tag$1, this.owner$2);
                    return symbol != symbol.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? symbol : serializable.apply();
                }

                public /* synthetic */ Scan scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$$outer() {
                    return this.$outer;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.tag$1 = tag$1;
                    this.name$2 = name$2;
                    this.owner$2 = owner$2;
                }
            };
            Symbols.Symbol symbol3 = symbol2 != symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? symbol2 : this.scala$reflect$internal$pickling$UnPickler$Scan$$fromName$1(name, tag$1, owner2);
            if (symbol3 != symbol3.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                symbol = symbol3;
            } else {
                Symbols.Symbol symbol4;
                Serializable serializable2 = new /* invalid duplicate definition of identical inner class */;
                Symbols.Symbol symbol5 = this.scala$reflect$internal$pickling$UnPickler$Scan$$fromName$1(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().nme().expandedName(name.toTermName(), owner2), tag$1, owner2);
                if (symbol5 != symbol5.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                    symbol4 = symbol5;
                } else {
                    Symbols.Symbol symbol6;
                    Serializable serializable3 = new /* invalid duplicate definition of identical inner class */;
                    Symbols.Symbol symbol7 = serializable.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$nestedObjectSymbol$1(tag$1, name, owner2);
                    if (symbol7 != symbol7.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                        symbol6 = symbol7;
                    } else {
                        Symbols.Symbol symbol8;
                        Serializable serializable4 = new /* invalid duplicate definition of identical inner class */;
                        Symbols.Symbol symbol9 = serializable2.$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$adjust$1(serializable2.$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().mirrorThatLoaded(serializable2.$outer.owner$2).missingHook(serializable2.$outer.owner$2, serializable2.$outer.name$2), serializable2.$outer.tag$1);
                        if (symbol9 != symbol9.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                            symbol8 = symbol9;
                        } else {
                            String advice1 = serializable3.$outer.$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$moduleAdvice$1(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", ".", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{serializable3.$outer.$outer.owner$2.fullName('.'), serializable3.$outer.$outer.name$2})));
                            Option option = serializable3.$outer.$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$completingStack().headOption();
                            Symbols.Symbol lazyCompletingSymbol1 = (Symbols.Symbol)(!option.isEmpty() ? option.get() : serializable4.$outer.$outer.$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol());
                            String string2 = new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"|Symbol '", " ", ".", "' is missing from the classpath.\n                      |This symbol is required by '", " ", "'.\n                      |Make sure that ", " is in your classpath and check for conflicting dependencies with `-Ylog-classpath`.\n                      |A full rebuild may help if '", "' was compiled against an incompatible version of ", ".", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{serializable3.$outer.$outer.name$2.nameKind(), serializable3.$outer.$outer.owner$2.fullName('.'), serializable3.$outer.$outer.name$2, lazyCompletingSymbol1.kindString(), lazyCompletingSymbol1.fullName('.'), serializable3.$outer.$outer.name$2.longString(), serializable3.$outer.$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$filename, serializable3.$outer.$outer.owner$2.fullName(), advice1}));
                            Predef$ predef$ = Predef$.MODULE$;
                            String missingMessage1 = new StringOps(string2).stripMargin();
                            Names.Name stubName1 = serializable3.$outer.$outer.tag$1 == 9 ? serializable3.$outer.$outer.name$2 : serializable3.$outer.$outer.name$2.toTypeName();
                            symbol8 = serializable3.$outer.$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol().newStubSymbol(stubName1, missingMessage1, serializable3.$outer.$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol().newStubSymbol$default$3());
                        }
                        symbol6 = symbol8;
                    }
                    symbol4 = symbol6;
                }
                symbol = symbol4;
            }
            return symbol;
        }

        private final boolean isModuleFlag$1(long flags$1) {
            return (flags$1 & 0x100L) != 0L;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private final boolean isClassRoot$1(Names.Name name$1, Symbols.Symbol owner$1) {
            Names.Name name = name$1;
            Names.Name name2 = this.classRoot.name();
            if (name == null) {
                if (name2 != null) {
                    return false;
                }
            } else if (!name.equals(name2)) return false;
            Symbols.Symbol symbol = owner$1;
            Symbols.Symbol symbol2 = this.classRoot.owner();
            if (symbol == null) {
                if (symbol2 == null) return true;
                return false;
            } else {
                if (!symbol.equals(symbol2)) return false;
                return true;
            }
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private final boolean isModuleRoot$1(Names.Name name$1, Symbols.Symbol owner$1) {
            Names.Name name = name$1;
            Names.Name name2 = this.moduleRoot.name();
            if (name == null) {
                if (name2 != null) {
                    return false;
                }
            } else if (!name.equals(name2)) return false;
            Symbols.Symbol symbol = owner$1;
            Symbols.Symbol symbol2 = this.moduleRoot.owner();
            if (symbol == null) {
                if (symbol2 == null) return true;
                return false;
            } else {
                if (!symbol.equals(symbol2)) return false;
                return true;
            }
        }

        private final long pflags$1(long flags$1) {
            return flags$1 & Flags$.MODULE$.PickledFlags();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private final boolean shouldEnterInOwnerScope$1(Symbols.Symbol sym$1) {
            if (!sym$1.owner().isClass()) return false;
            Symbols.Symbol symbol = sym$1;
            Symbols.Symbol symbol2 = this.classRoot;
            if (symbol == null) {
                if (symbol2 == null) return false;
            } else if (symbol.equals(symbol2)) return false;
            Symbols.Symbol symbol3 = sym$1;
            Symbols.Symbol symbol4 = this.moduleRoot;
            if (symbol3 == null) {
                if (symbol4 == null) return false;
            } else if (symbol3.equals(symbol4)) return false;
            if (sym$1.isModuleClass()) return false;
            if (sym$1.isRefinementClass()) return false;
            if (sym$1.isTypeParameter()) return false;
            if (sym$1.isExistentiallyBound()) return false;
            Names.Name name = sym$1.rawname();
            Names.TypeName typeName = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().tpnme().LOCAL_CHILD();
            if (name == null) {
                if (typeName == null) return false;
            } else if (name.equals(typeName)) return false;
            if (this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().nme().isLocalDummyName(sym$1.rawname())) return false;
            return true;
        }

        private final Symbols.Symbol finishSym$1(Symbols.Symbol sym, int end$1, Symbols.Symbol privateWithin$1, int inforef$1) {
            Types.LazyType lazyType;
            this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().markFlagsCompleted(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.Symbol[]{sym}), -1L);
            sym.privateWithin_$eq(privateWithin$1);
            if (this.atEnd$1(end$1)) {
                boolean bl = !sym.isSuperAccessor();
                Predef$ predef$ = Predef$.MODULE$;
                if (!bl) {
                    throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(sym).toString());
                }
                lazyType = this.newLazyTypeRef(inforef$1);
            } else {
                boolean bl = sym.isSuperAccessor() || sym.isParamAccessor();
                Predef$ predef$ = Predef$.MODULE$;
                if (!bl) {
                    throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(sym).toString());
                }
                lazyType = this.newLazyTypeRefAndAlias(inforef$1, this.readNat());
            }
            sym.info_$eq(lazyType);
            Object object = this.shouldEnterInOwnerScope$1(sym) ? this.symScope(sym.owner()).enter(sym) : BoxedUnit.UNIT;
            return sym;
        }

        private final List all$1(Function0 body2, int end$2) {
            return this.until(end$2, body2);
        }

        private final List readTypes$1(int end$2) {
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Scan $outer;

                public final Types.Type apply() {
                    return this.$outer.readTypeRef();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
            return this.until(end$2, serializable);
        }

        private final List readSymbols$1(int end$2) {
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Scan $outer;

                public final Symbols.Symbol apply() {
                    return this.$outer.readSymbolRef();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
            return this.until(end$2, serializable);
        }

        private final List readAnnots$1(int end$2) {
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Scan $outer;

                public final AnnotationInfos.AnnotationInfo apply() {
                    return this.$outer.readAnnotationRef();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
            return this.until(end$2, serializable);
        }

        private final Types.Type MethodTypeRef$1(Types.Type restpe, List params2) {
            Types.Type type = restpe;
            Types$NoType$ types$NoType$ = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoType();
            return !(type == null ? types$NoType$ != null : !type.equals(types$NoType$)) || params2.contains(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol()) ? this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoType() : new Types.MethodType(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), params2, restpe);
        }

        private final Types.Type PolyOrNullaryType$1(Types.Type restpe, List tparams2) {
            Types.Type type = ((Object)Nil$.MODULE$).equals(tparams2) ? new Types.NullaryMethodType(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), restpe) : new Types.PolyType(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), tparams2, restpe);
            return type;
        }

        private final Types.Type CompoundType$1(Symbols.Symbol clazz, List parents2, int tag$2) {
            Types.CompoundType compoundType;
            switch (tag$2) {
                default: {
                    throw new MatchError(BoxesRunTime.boxToInteger(tag$2));
                }
                case 19: {
                    compoundType = new Types.ClassInfoType(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), parents2, this.symScope(clazz), clazz);
                    break;
                }
                case 18: {
                    compoundType = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().RefinedType().apply(parents2, this.symScope(clazz), clazz);
                }
            }
            return compoundType;
        }

        private final Types.Type readThisType$1() {
            Symbols.StubSymbol stubSymbol;
            Symbols.Symbol symbol = this.readSymbolRef();
            Symbols.Symbol symbol2 = symbol instanceof Symbols.StubSymbol && !((Symbols.SymbolApi)((Object)(stubSymbol = (Symbols.StubSymbol)((Object)symbol)))).isClass() ? ((Symbols.Symbol)((Object)stubSymbol)).owner().newStubSymbol(((Symbols.Symbol)((Object)stubSymbol)).name().toTypeName(), stubSymbol.missingMessage(), true) : symbol;
            return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().ThisType().apply(symbol2);
        }

        private final List all$2(Function0 body2, int end$3) {
            return this.until(end$3, body2);
        }

        public final List scala$reflect$internal$pickling$UnPickler$Scan$$rep$1(Function0 body2) {
            return this.times(this.readNat(), body2);
        }

        private final Trees.Apply fixApply$1(Trees.Apply tree, Types.Type tpe) {
            if (tree != null) {
                Tuple2<Trees.Tree, List<Trees.Tree>> tuple2 = new Tuple2<Trees.Tree, List<Trees.Tree>>(tree.fun(), tree.args());
                Trees.Tree fun = tuple2._1();
                List<Trees.Tree> args = tuple2._2();
                if (fun.symbol().isOverloaded()) {
                    fun.setType(fun.symbol().info());
                    this.inferMethodAlternative(fun, args.map(new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final Types.Type apply(Trees.Tree x$4) {
                            return x$4.tpe();
                        }
                    }, List$.MODULE$.canBuildFrom()), tpe);
                }
                return tree;
            }
            throw new MatchError(tree);
        }

        public final Trees.Tree scala$reflect$internal$pickling$UnPickler$Scan$$ref$1() {
            return this.readTreeRef();
        }

        public final Trees.CaseDef scala$reflect$internal$pickling$UnPickler$Scan$$caseRef$1() {
            return this.readCaseDefRef();
        }

        private final Trees.Modifiers modsRef$1() {
            return this.readModifiersRef();
        }

        private final Trees.Template implRef$1() {
            return this.readTemplateRef();
        }

        public final Names.Name scala$reflect$internal$pickling$UnPickler$Scan$$nameRef$1() {
            return this.readNameRef();
        }

        public final Trees.TypeDef scala$reflect$internal$pickling$UnPickler$Scan$$tparamRef$1() {
            return this.readTypeDefRef();
        }

        public final Trees.ValDef scala$reflect$internal$pickling$UnPickler$Scan$$vparamRef$1() {
            return this.readValDefRef();
        }

        public final Trees.MemberDef scala$reflect$internal$pickling$UnPickler$Scan$$memberRef$1() {
            return this.readMemberDefRef();
        }

        private final Constants.Constant constRef$1() {
            return this.readConstantRef();
        }

        public final Trees.Ident scala$reflect$internal$pickling$UnPickler$Scan$$idRef$1() {
            return this.readIdentRef();
        }

        private final Names.TermName termNameRef$1() {
            return this.readNameRef().toTermName();
        }

        private final Names.TypeName typeNameRef$1() {
            return this.readNameRef().toTypeName();
        }

        private final Trees.RefTree refTreeRef$1() {
            Trees.Tree tree = this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1();
            if (tree instanceof Trees.RefTree) {
                Trees.RefTree refTree = (Trees.RefTree)((Object)tree);
                return refTree;
            }
            throw this.errorBadSignature(new StringBuilder().append((Object)"RefTree expected, found ").append((Object)tree.shortClass()).toString());
        }

        private final List selectorsRef$1(int end$3) {
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Scan $outer;

                public final Trees.ImportSelector apply() {
                    return new Trees.ImportSelector(this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$nameRef$1(), -1, this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$nameRef$1(), -1);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
            return this.until(end$3, serializable);
        }

        private final Trees.Tree readTree$1(Types.Type tpe, int tag$3, int end$3) {
            Trees.Tree tree;
            switch (tag$3) {
                default: {
                    throw this.noSuchTreeTag(tag$3, end$3);
                }
                case 5: {
                    tree = new Trees.ValDef(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.modsRef$1(), this.termNameRef$1(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    break;
                }
                case 19: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.Tree apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Trees.UnApply unApply = new Trees.UnApply(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.until(end$3, serializable));
                    tree = unApply;
                    break;
                }
                case 38: {
                    tree = new Trees.TypeTree(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable());
                    break;
                }
                case 7: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.TypeDef apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$tparamRef$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Trees.TypeDef typeDef = new Trees.TypeDef(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.modsRef$1(), this.typeNameRef$1(), this.times(this.readNat(), serializable), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    tree = typeDef;
                    break;
                }
                case 44: {
                    tree = new Trees.TypeBoundsTree(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    break;
                }
                case 26: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.CaseDef apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$caseRef$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Trees.Try try_ = new Trees.Try(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.times(this.readNat(), serializable), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    tree = try_;
                    break;
                }
                case 27: {
                    tree = new Trees.Throw(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    break;
                }
                case 34: {
                    tree = new Trees.This(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.typeNameRef$1());
                    break;
                }
                case 12: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.Tree apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Trees.Template template = new Trees.Template(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.times(this.readNat(), serializable), this.scala$reflect$internal$pickling$UnPickler$Scan$$vparamRef$1(), this.all$2((Function0)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.Tree apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }), end$3));
                    tree = template;
                    break;
                }
                case 33: {
                    tree = new Trees.Super(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.typeNameRef$1());
                    break;
                }
                case 17: {
                    tree = new Trees.Star(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    break;
                }
                case 40: {
                    tree = new Trees.SingletonTypeTree(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    break;
                }
                case 41: {
                    tree = new Trees.SelectFromTypeTree(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.typeNameRef$1());
                    break;
                }
                case 25: {
                    tree = new Trees.Return(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    break;
                }
                case 2: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.Tree apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Trees.PackageDef packageDef = new Trees.PackageDef(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.refTreeRef$1(), this.until(end$3, serializable));
                    tree = packageDef;
                    break;
                }
                case 28: {
                    tree = new Trees.New(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    break;
                }
                case 4: {
                    tree = new Trees.ModuleDef(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.modsRef$1(), this.termNameRef$1(), this.implRef$1());
                    break;
                }
                case 24: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.CaseDef apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$caseRef$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Trees.Match match = new Trees.Match(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.until(end$3, serializable));
                    tree = match;
                    break;
                }
                case 8: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.Ident apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$idRef$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Trees.LabelDef labelDef = new Trees.LabelDef(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.termNameRef$1(), this.times(this.readNat(), serializable), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    tree = labelDef;
                    break;
                }
                case 9: {
                    tree = new Trees.Import(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.selectorsRef$1(end$3));
                    break;
                }
                case 21: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.ValDef apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$vparamRef$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Trees.Function function = new Trees.Function(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.times(this.readNat(), serializable), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    tree = function;
                    break;
                }
                case 45: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.MemberDef apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$memberRef$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Trees.ExistentialTypeTree existentialTypeTree = new Trees.ExistentialTypeTree(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.until(end$3, serializable));
                    tree = existentialTypeTree;
                    break;
                }
                case 6: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.TypeDef apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$tparamRef$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Trees.DefDef defDef = new Trees.DefDef(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.modsRef$1(), this.termNameRef$1(), this.times(this.readNat(), serializable), this.scala$reflect$internal$pickling$UnPickler$Scan$$rep$1((Function0)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        public final /* synthetic */ Scan $outer;

                        public final List<Trees.ValDef> apply() {
                            Serializable serializable = new Serializable(this){
                                public static final long serialVersionUID = 0L;
                                private final /* synthetic */ Scan$$anonfun$readTree$1$9 $outer;

                                public final Trees.ValDef apply() {
                                    return this.$outer.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$vparamRef$1();
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                }
                            };
                            Scan scan2 = this.$outer;
                            return scan2.times(scan2.readNat(), serializable);
                        }

                        public /* synthetic */ Scan scala$reflect$internal$pickling$UnPickler$Scan$$anonfun$$$outer() {
                            return this.$outer;
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    })), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    tree = defDef;
                    break;
                }
                case 42: {
                    tree = new Trees.CompoundTypeTree(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.implRef$1());
                    break;
                }
                case 3: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.TypeDef apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$tparamRef$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Trees.ClassDef classDef = new Trees.ClassDef(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.modsRef$1(), this.typeNameRef$1(), this.times(this.readNat(), serializable), this.implRef$1());
                    tree = classDef;
                    break;
                }
                case 14: {
                    tree = new Trees.CaseDef(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    break;
                }
                case 22: {
                    tree = new Trees.Assign(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    break;
                }
                case 20: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.Tree apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Trees.ArrayValue arrayValue = new Trees.ArrayValue(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.until(end$3, serializable));
                    tree = arrayValue;
                    break;
                }
                case 32: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.Tree apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Trees.ApplyDynamic applyDynamic = new Trees.ApplyDynamic(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.until(end$3, serializable));
                    tree = applyDynamic;
                    break;
                }
                case 43: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.Tree apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Trees.AppliedTypeTree appliedTypeTree = new Trees.AppliedTypeTree(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.until(end$3, serializable));
                    tree = appliedTypeTree;
                    break;
                }
                case 39: {
                    tree = new Trees.Annotated(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    break;
                }
                case 16: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.Tree apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Trees.Alternative alternative = new Trees.Alternative(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.until(end$3, serializable));
                    tree = alternative;
                    break;
                }
                case 29: {
                    tree = new Trees.Typed(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    break;
                }
                case 30: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.Tree apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    Trees.TypeApply typeApply = new Trees.TypeApply(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.until(end$3, serializable));
                    tree = typeApply;
                    break;
                }
                case 37: {
                    tree = new Trees.Literal(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.constRef$1());
                    break;
                }
                case 23: {
                    tree = new Trees.If(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    break;
                }
                case 13: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.Tree apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    List<Trees.Tree> list2 = this.until(end$3, serializable);
                    Option option = package$.MODULE$.$colon$plus().unapply(list2);
                    if (option.isEmpty()) {
                        throw new MatchError(list2);
                    }
                    tree = new Trees.Block(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), option.get()._1(), (Trees.Tree)option.get()._2());
                    break;
                }
                case 18: {
                    tree = new Trees.Bind(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$nameRef$1(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1());
                    break;
                }
                case 31: {
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scan $outer;

                        public final Trees.Tree apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    };
                    tree = this.fixApply$1(new Trees.Apply(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.until(end$3, serializable)), tpe);
                    break;
                }
                case 35: {
                    tree = new Trees.Select(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$ref$1(), this.scala$reflect$internal$pickling$UnPickler$Scan$$nameRef$1());
                    break;
                }
                case 36: {
                    tree = new Trees.Ident(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), this.scala$reflect$internal$pickling$UnPickler$Scan$$nameRef$1());
                }
            }
            return tree;
        }

        public Scan(UnPickler $outer, byte[] _bytes, int offset, Symbols.Symbol classRoot, Symbols.Symbol moduleRoot, String filename) {
            this.classRoot = classRoot;
            this.moduleRoot = moduleRoot;
            this.scala$reflect$internal$pickling$UnPickler$Scan$$filename = filename;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            super(_bytes, offset, -1);
            this.checkVersion();
            this.loadingMirror = $outer.symbolTable().mirrorThatLoaded(classRoot);
            this.scala$reflect$internal$pickling$UnPickler$Scan$$index = this.createIndex();
            this.scala$reflect$internal$pickling$UnPickler$Scan$$entries = new Object[this.scala$reflect$internal$pickling$UnPickler$Scan$$index().length];
            this.symScopes = (HashMap)HashMap$.MODULE$.apply(Nil$.MODULE$);
            this.scala$reflect$internal$pickling$UnPickler$Scan$$completingStack = List$.MODULE$.empty();
        }

        public class LazyTypeRef
        extends Types.LazyType
        implements Types.FlagAgnosticCompleter {
            private final int i;
            private final int definedAtRunId;
            private final Phase p;
            public final /* synthetic */ Scan $outer;

            private int definedAtRunId() {
                return this.definedAtRunId;
            }

            private Phase p() {
                return this.p;
            }

            public void completeInternal(Symbols.Symbol sym) {
                Types.TypeError typeError;
                try {
                    Types.Type type;
                    this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$completingStack_$eq(this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$completingStack().$colon$colon(sym));
                    Types.Type tp = this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().at(this.i, new Serializable(this, sym){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ LazyTypeRef $outer;
                        private final Symbols.Symbol sym$2;

                        public final Types.Type apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().readType(this.sym$2.isTerm());
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.sym$2 = sym$2;
                        }
                    });
                    Names.Name name = sym.rawname();
                    Names.TypeName typeName = this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().tpnme().LOCAL_CHILD();
                    if (!(name != null ? !name.equals(typeName) : typeName != null)) {
                        Types.Type type2;
                        $colon$colon $colon$colon;
                        Types.ClassInfoType classInfoType;
                        if (tp instanceof Types.ClassInfoType && (classInfoType = (Types.ClassInfoType)tp).parents() instanceof $colon$colon && ((Types.Type)($colon$colon = ($colon$colon)classInfoType.parents()).head()).typeSymbol().isTrait()) {
                            Types.Type type3 = this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().definitions().ObjectTpe();
                            Types.Type type4 = (Types.Type)$colon$colon.head();
                            type2 = new Types.ClassInfoType(this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable(), $colon$colon.tl$1().$colon$colon(type4).$colon$colon(type3), classInfoType.decls(), classInfoType.typeSymbol());
                        } else {
                            type2 = tp;
                        }
                        type = type2;
                    } else {
                        type = tp;
                    }
                    Types.Type fixLocalChildTp = type;
                    Object object = this.p() != null ? this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().slowButSafeEnteringPhase(this.p(), new Serializable(this, sym, fixLocalChildTp){
                        public static final long serialVersionUID = 0L;
                        private final Symbols.Symbol sym$2;
                        private final Types.Type fixLocalChildTp$1;

                        public final Symbols.Symbol apply() {
                            return this.sym$2.setInfo(this.fixLocalChildTp$1);
                        }
                        {
                            this.sym$2 = sym$2;
                            this.fixLocalChildTp$1 = fixLocalChildTp$1;
                        }
                    }) : BoxedUnit.UNIT;
                    if (this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().currentRunId() != this.definedAtRunId()) {
                        sym.setInfo(this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().adaptToNewRunMap().apply(fixLocalChildTp));
                    }
                    return;
                }
                catch (MissingRequirementError missingRequirementError) {
                    typeError = this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().toTypeError(missingRequirementError);
                }
                finally {
                    this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$completingStack_$eq((List)this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$completingStack().tail());
                }
                throw typeError;
            }

            @Override
            public void complete(Symbols.Symbol sym) {
                this.completeInternal(sym);
                if (!this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().isCompilerUniverse()) {
                    this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().markAllCompleted(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.Symbol[]{sym}));
                }
            }

            @Override
            public void load(Symbols.Symbol sym) {
                this.complete(sym);
            }

            public /* synthetic */ Scan scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer() {
                return this.$outer;
            }

            public LazyTypeRef(Scan $outer, int i) {
                this.i = i;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super($outer.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable());
                this.definedAtRunId = $outer.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().currentRunId();
                this.p = $outer.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().phase();
            }
        }

        public class LazyTypeRefAndAlias
        extends LazyTypeRef {
            private final int j;

            @Override
            public void completeInternal(Symbols.Symbol sym) {
                try {
                    super.completeInternal(sym);
                    ObjectRef<Symbols.Symbol> alias = ObjectRef.create(this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRefAndAlias$$$outer().at(this.j, new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ LazyTypeRefAndAlias $outer;

                        public final Symbols.Symbol apply() {
                            return this.$outer.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRefAndAlias$$$outer().readSymbol();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                    if (((Symbols.Symbol)alias.elem).isOverloaded()) {
                        alias.elem = this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRefAndAlias$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().slowButSafeEnteringPhase(this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRefAndAlias$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().picklerPhase(), new Serializable(this, sym, alias){
                            public static final long serialVersionUID = 0L;
                            public final Symbols.Symbol sym$3;
                            private final ObjectRef alias$1;

                            public final Symbols.Symbol apply() {
                                return ((Symbols.Symbol)this.alias$1.elem).suchThat((Function1)((Object)new Serializable(this){
                                    public static final long serialVersionUID = 0L;
                                    private final /* synthetic */ Scan$LazyTypeRefAndAlias$$anonfun$completeInternal$2 $outer;

                                    public final boolean apply(Symbols.Symbol alt) {
                                        return this.$outer.sym$3.tpe().$eq$colon$eq(this.$outer.sym$3.owner().thisType().memberType(alt));
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
                                this.sym$3 = sym$3;
                                this.alias$1 = alias$1;
                            }
                        });
                    }
                    ((Symbols.TermSymbol)sym).setAlias((Symbols.Symbol)alias.elem);
                    return;
                }
                catch (MissingRequirementError missingRequirementError) {
                    throw this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRefAndAlias$$$outer().toTypeError(missingRequirementError);
                }
            }

            public /* synthetic */ Scan scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRefAndAlias$$$outer() {
                return this.$outer;
            }

            public LazyTypeRefAndAlias(Scan $outer, int i, int j) {
                this.j = j;
                super($outer, i);
            }
        }
    }
}

