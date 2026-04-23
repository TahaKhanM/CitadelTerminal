/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Predef$any2stringadd$;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableLike;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.math.Ordering$Int$;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Kinds;
import scala.reflect.internal.Kinds$Kind$;
import scala.reflect.internal.Kinds$Kind$StringState$;
import scala.reflect.internal.Kinds$KindErrors$;
import scala.reflect.internal.Kinds$ProperTypeKind$;
import scala.reflect.internal.Kinds$TypeConKind$;
import scala.reflect.internal.Kinds$inferKind$;
import scala.reflect.internal.Names;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.Variance$;
import scala.reflect.internal.util.StringOps$;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0015Uh!C\u0001\u0003!\u0003\r\t!CCx\u0005\u0015Y\u0015N\u001c3t\u0015\t\u0019A!\u0001\u0005j]R,'O\\1m\u0015\t)a!A\u0004sK\u001adWm\u0019;\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001\u0015A\u00111\u0002D\u0007\u0002\r%\u0011QB\u0002\u0002\u0007\u0003:L(+\u001a4\t\u000b=\u0001A\u0011\u0001\t\u0002\r\u0011Jg.\u001b;%)\u0005\t\u0002CA\u0006\u0013\u0013\t\u0019bA\u0001\u0003V]&$X\u0001B\u000b\u0001\tY\u0011qaU=n!\u0006L'\u000f\u0005\u0003\f/eI\u0012B\u0001\r\u0007\u0005\u0019!V\u000f\u001d7feA\u0011!dG\u0007\u0002\u0001%\u0011A$\b\u0002\u0007'fl'm\u001c7\n\u0005y\u0011!aB*z[\n|Gn\u001d\u0004\u0005A\u0001\u0001\u0015E\u0001\u0006LS:$WI\u001d:peN\u001cBa\b\u0006#KA\u00111bI\u0005\u0003I\u0019\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002\fM%\u0011qE\u0002\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\tS}\u0011)\u001a!C\u0001U\u0005)\u0011M]5usV\t1\u0006E\u0002-_Ir!aC\u0017\n\u000592\u0011a\u00029bG.\fw-Z\u0005\u0003aE\u0012A\u0001T5ti*\u0011aF\u0002\t\u00035QA\u0001\u0002N\u0010\u0003\u0012\u0003\u0006IaK\u0001\u0007CJLG/\u001f\u0011\t\u0011Yz\"Q3A\u0005\u0002)\n\u0001B^1sS\u0006t7-\u001a\u0005\tq}\u0011\t\u0012)A\u0005W\u0005Ia/\u0019:jC:\u001cW\r\t\u0005\tu}\u0011)\u001a!C\u0001U\u0005Q1\u000f\u001e:jGRtWm]:\t\u0011qz\"\u0011#Q\u0001\n-\n1b\u001d;sS\u000e$h.Z:tA!)ah\bC\u0001\u007f\u00051A(\u001b8jiz\"B\u0001Q!C\u0007B\u0011!d\b\u0005\bSu\u0002\n\u00111\u0001,\u0011\u001d1T\b%AA\u0002-BqAO\u001f\u0011\u0002\u0003\u00071\u0006C\u0003F?\u0011\u0005a)A\u0004jg\u0016k\u0007\u000f^=\u0016\u0003\u001d\u0003\"a\u0003%\n\u0005%3!a\u0002\"p_2,\u0017M\u001c\u0005\u0006\u0017~!\t\u0001T\u0001\u000bCJLG/_#se>\u0014HC\u0001!N\u0011\u0015q%\n1\u00013\u0003\u0011\u0019\u00180\\:\t\u000bA{B\u0011A)\u0002\u001bY\f'/[1oG\u0016,%O]8s)\t\u0001%\u000bC\u0003O\u001f\u0002\u0007!\u0007C\u0003U?\u0011\u0005Q+A\btiJL7\r\u001e8fgN,%O]8s)\t\u0001e\u000bC\u0003O'\u0002\u0007!\u0007C\u0003Y?\u0011\u0005\u0011,\u0001\u0006%a2,8\u000f\n9mkN$\"\u0001\u0011.\t\u000bm;\u0006\u0019\u0001!\u0002\t\u0015\u0014(o\u001d\u0005\u0006;~!IAX\u0001\u0007m\u0006\u00148\u000b\u001e:\u0015\u0005}3\u0007C\u00011d\u001d\tY\u0011-\u0003\u0002c\r\u00051\u0001K]3eK\u001aL!\u0001Z3\u0003\rM#(/\u001b8h\u0015\t\u0011g\u0001C\u0003h9\u0002\u0007\u0011$A\u0001t\u0011\u0015Iw\u0004\"\u0003k\u0003\u001d\tX/\u00197jMf$2aX6n\u0011\u0015a\u0007\u000e1\u0001\u001a\u0003\t\t\u0007\u0007C\u0003oQ\u0002\u0007\u0011$\u0001\u0002ca!)\u0001o\bC\u0005c\u0006Y1.\u001b8e\u001b\u0016\u001c8/Y4f)\r\u0011\bP\u001f\u000b\u0003?NDQ\u0001^8A\u0002U\f\u0011A\u001a\t\u0006\u0017Y|vlX\u0005\u0003o\u001a\u0011\u0011BR;oGRLwN\u001c\u001a\t\u000be|\u0007\u0019A\r\u0002\u0003\u0005DQa_8A\u0002e\t\u0011\u0001\u001d\u0005\u0006{~!IA`\u0001\u0012gR\u0014\u0018n\u0019;oKN\u001cX*Z:tC\u001e,G\u0003B0\u0000\u0003\u0003AQ!\u001f?A\u0002eAQa\u001f?A\u0002eAq!!\u0002 \t\u0013\t9!A\bwCJL\u0017M\\2f\u001b\u0016\u001c8/Y4f)\u0015y\u0016\u0011BA\u0006\u0011\u0019I\u00181\u0001a\u00013!110a\u0001A\u0002eAq!a\u0004 \t\u0013\t\t\"\u0001\u0007be&$\u00180T3tg\u0006<W\rF\u0003`\u0003'\t)\u0002\u0003\u0004z\u0003\u001b\u0001\r!\u0007\u0005\u0007w\u00065\u0001\u0019A\r\t\u000f\u0005eq\u0004\"\u0003\u0002\u001c\u0005a!-^5mI6+7o]1hKR1\u0011QDA\u0016\u0003_\u0001B!a\b\u0002*5\u0011\u0011\u0011\u0005\u0006\u0005\u0003G\t)#\u0001\u0003mC:<'BAA\u0014\u0003\u0011Q\u0017M^1\n\u0007\u0011\f\t\u0003C\u0004\u0002.\u0005]\u0001\u0019A\u0016\u0002\u0005a\u001c\bb\u0002;\u0002\u0018\u0001\u0007\u0011\u0011\u0007\t\u0006\u0017YL\u0012d\u0018\u0005\b\u0003kyB\u0011AA\u001c\u00031)'O]8s\u001b\u0016\u001c8/Y4f)\u0015y\u0016\u0011HA$\u0011!\tY$a\rA\u0002\u0005u\u0012\u0001\u0002;be\u001e\u00042AGA \u0013\u0011\t\t%a\u0011\u0003\tQK\b/Z\u0005\u0004\u0003\u000b\u0012!!\u0002+za\u0016\u001c\bbBA%\u0003g\u0001\r!G\u0001\u0007iB\f'/Y7\t\u0013\u00055s$!A\u0005\u0002\u0005=\u0013\u0001B2paf$r\u0001QA)\u0003'\n)\u0006\u0003\u0005*\u0003\u0017\u0002\n\u00111\u0001,\u0011!1\u00141\nI\u0001\u0002\u0004Y\u0003\u0002\u0003\u001e\u0002LA\u0005\t\u0019A\u0016\t\u0013\u0005es$%A\u0005\u0002\u0005m\u0013AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003;R3aKA0W\t\t\t\u0007\u0005\u0003\u0002d\u00055TBAA3\u0015\u0011\t9'!\u001b\u0002\u0013Ut7\r[3dW\u0016$'bAA6\r\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005=\u0014Q\r\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007\"CA:?E\u0005I\u0011AA.\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIIB\u0011\"a\u001e #\u0003%\t!a\u0017\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%g!I\u00111P\u0010\u0002\u0002\u0013\u0005\u0013QP\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005u\u0001\"CAA?\u0005\u0005I\u0011AAB\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\t)\tE\u0002\f\u0003\u000fK1!!#\u0007\u0005\rIe\u000e\u001e\u0005\n\u0003\u001b{\u0012\u0011!C\u0001\u0003\u001f\u000ba\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\u0012\u0006]\u0005cA\u0006\u0002\u0014&\u0019\u0011Q\u0013\u0004\u0003\u0007\u0005s\u0017\u0010\u0003\u0006\u0002\u001a\u0006-\u0015\u0011!a\u0001\u0003\u000b\u000b1\u0001\u001f\u00132\u0011%\tijHA\u0001\n\u0003\ny*A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t\t\u000b\u0005\u0004\u0002$\u0006%\u0016\u0011S\u0007\u0003\u0003KS1!a*\u0007\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003W\u000b)K\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0011%\tykHA\u0001\n\u0003\t\t,\u0001\u0005dC:,\u0015/^1m)\r9\u00151\u0017\u0005\u000b\u00033\u000bi+!AA\u0002\u0005E\u0005\"CA\\?\u0005\u0005I\u0011IA]\u0003!A\u0017m\u001d5D_\u0012,GCAAC\u0011%\tilHA\u0001\n\u0003\ny,\u0001\u0005u_N#(/\u001b8h)\t\ti\u0002C\u0005\u0002D~\t\t\u0011\"\u0011\u0002F\u00061Q-];bYN$2aRAd\u0011)\tI*!1\u0002\u0002\u0003\u0007\u0011\u0011S\u0004\n\u0003\u0017\u0004\u0011\u0011!E\u0001\u0003\u001b\f!bS5oI\u0016\u0013(o\u001c:t!\rQ\u0012q\u001a\u0004\tA\u0001\t\t\u0011#\u0001\u0002RN)\u0011qZAjKAY\u0011Q[An\u0003?\fy.a8A\u001b\t\t9NC\u0002\u0002Z\u001a\tqA];oi&lW-\u0003\u0003\u0002^\u0006]'!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ogA\u0019Af\f\f\t\u000fy\ny\r\"\u0001\u0002dR\u0011\u0011Q\u001a\u0005\u000b\u0003{\u000by-!A\u0005F\u0005}\u0006BCAu\u0003\u001f\f\t\u0011\"!\u0002l\u0006)\u0011\r\u001d9msR9\u0001)!<\u0002p\u0006E\b\u0002C\u0015\u0002hB\u0005\t\u0019A\u0016\t\u0011Y\n9\u000f%AA\u0002-B\u0001BOAt!\u0003\u0005\ra\u000b\u0005\u000b\u0003k\fy-!A\u0005\u0002\u0006]\u0018aB;oCB\u0004H.\u001f\u000b\u0005\u0003s\u0014)\u0001E\u0003\f\u0003w\fy0C\u0002\u0002~\u001a\u0011aa\u00149uS>t\u0007CB\u0006\u0003\u0002-Z3&C\u0002\u0003\u0004\u0019\u0011a\u0001V;qY\u0016\u001c\u0004\"\u0003B\u0004\u0003g\f\t\u00111\u0001A\u0003\rAH\u0005\r\u0005\u000b\u0005\u0017\ty-%A\u0005\u0002\u0005m\u0013aD1qa2LH\u0005Z3gCVdG\u000fJ\u0019\t\u0015\t=\u0011qZI\u0001\n\u0003\tY&A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00133\u0011)\u0011\u0019\"a4\u0012\u0002\u0013\u0005\u00111L\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%g!Q!qCAh#\u0003%\t!a\u0017\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132\u0011)\u0011Y\"a4\u0012\u0002\u0013\u0005\u00111L\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a\t\u0015\t}\u0011qZI\u0001\n\u0003\tY&A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$He\r\u0005\n\u0005G\u0001!\u0019!C\u0001\u0005K\tABT8LS:$WI\u001d:peN,\u0012\u0001\u0011\u0005\b\u0005S\u0001\u0001\u0015!\u0003A\u00035qunS5oI\u0016\u0013(o\u001c:tA!9!Q\u0006\u0001\u0005\u0002\t=\u0012\u0001D6j]\u0012\u001c8i\u001c8g_JlG#C$\u00032\t]\"Q\bB!\u0011!\u0011\u0019Da\u000bA\u0002\tU\u0012a\u0002;qCJ\fWn\u001d\t\u0004Y=J\u0002\u0002\u0003B\u001d\u0005W\u0001\rAa\u000f\u0002\u000bQ\f'oZ:\u0011\t1z\u0013Q\b\u0005\t\u0005\u007f\u0011Y\u00031\u0001\u0002>\u0005\u0019\u0001O]3\t\u000f\t\r#1\u0006a\u00013\u0005)qn\u001e8fe\"9!q\t\u0001\u0005\n\t%\u0013A\u0004<be&\fgnY3t\u001b\u0006$8\r\u001b\u000b\u0006\u000f\n-#q\n\u0005\b\u0005\u001b\u0012)\u00051\u0001\u001a\u0003\u0011\u0019\u00180\\\u0019\t\u000f\tE#Q\ta\u00013\u0005!1/_73\u0011\u001d\u0011)\u0006\u0001C\u0001\u0005/\n\u0001c\u00195fG.\\\u0015N\u001c3C_VtGm\u001d\u0019\u0015\u0019\te#Q\fB0\u0005C\u0012\u0019G!\u001a\u0011\t1z#1\f\t\b\u0017\t\u0005\u0011QH\rA\u0011!\u0011\u0019Da\u0015A\u0002\tU\u0002\u0002\u0003B\u001d\u0005'\u0002\rAa\u000f\t\u0011\t}\"1\u000ba\u0001\u0003{AqAa\u0011\u0003T\u0001\u0007\u0011\u0004C\u0004\u0003h\tM\u0003\u0019A$\u0002\u001b\u0015D\b\u000f\\1j]\u0016\u0013(o\u001c:t\r\u001d\u0011Y\u0007AA\u0001\u0005[\u0012AaS5oIN\u0019!\u0011\u000e\u0006\t\u000fy\u0012I\u0007\"\u0001\u0003rQ\u0011!1\u000f\t\u00045\t%\u0004\u0002\u0003B<\u0005S2\tA!\u001f\u0002\u0017\u0011,7o\u0019:jaRLwN\\\u000b\u0002?\"A!Q\u0010B5\r\u0003\t\u0019)A\u0003pe\u0012,'\u000f\u0003\u0005\u0003\u0002\n%d\u0011\u0001BB\u0003\u0019\u0011w.\u001e8egV\u0011!Q\u0011\t\u00045\t\u001d\u0015\u0002\u0002BE\u0003\u0007\u0012!\u0002V=qK\n{WO\u001c3t\u0011!\u0011iI!\u001b\u0007\u0002\te\u0014!D:dC2\fgj\u001c;bi&|g\u000e\u0003\u0005\u0003\u0012\n%d\u0011\u0001B=\u00031\u0019H/\u0019:O_R\fG/[8o\u0011\u001d\u0011)J!\u001b\u0005\u0002\u0019\u000b\u0011\u0002[1t\u0005>,h\u000eZ:\t\u0013\te%\u0011\u000eD\u0001\u0005\tm\u0015A\u00032vS2$7\u000b^1uKR1!Q\u0014C+\t/\"BAa(\u0005TA!!\u0011UB\\\u001d\rQ\"1U\u0004\b\u0005K\u0003\u0001\u0012\u0001BT\u0003\u0011Y\u0015N\u001c3\u0011\u0007i\u0011IKB\u0004\u0003l\u0001A\tAa+\u0014\u0007\t%&\u0002C\u0004?\u0005S#\tAa,\u0015\u0005\t\u001dfa\u0003BZ\u0005S\u0003\n1%\t\u0003\u0005k\u0013QbU2bY\u0006tu\u000e^1uS>t7c\u0001BY\u0015%2!\u0011\u0017B]\u0007c1\u0001Ba/\u0003*B\u0013!Q\u0018\u0002\u0005\u0011\u0016\fGmE\u0004\u0003:*\u0011yLI\u0013\u0011\t\t\u0005'\u0011W\u0007\u0003\u0005SC1B! \u0003:\nU\r\u0011\"\u0001\u0002\u0004\"Y!q\u0019B]\u0005#\u0005\u000b\u0011BAC\u0003\u0019y'\u000fZ3sA!Y!1\u001aB]\u0005+\u0007I\u0011\u0001Bg\u0003\u0005qWC\u0001Bh!\u0015Y\u00111`AC\u0011-\u0011\u0019N!/\u0003\u0012\u0003\u0006IAa4\u0002\u00059\u0004\u0003b\u0003Bl\u0005s\u0013)\u001a!C\u0001\u00053\fQ!\u00197jCN,\"Aa7\u0011\t-\tYp\u0018\u0005\f\u0005?\u0014IL!E!\u0002\u0013\u0011Y.\u0001\u0004bY&\f7\u000f\t\u0005\b}\teF\u0011\u0001Br)!\u0011)Oa:\u0003j\n-\b\u0003\u0002Ba\u0005sC\u0001B! \u0003b\u0002\u0007\u0011Q\u0011\u0005\t\u0005\u0017\u0014\t\u000f1\u0001\u0003P\"A!q\u001bBq\u0001\u0004\u0011Y\u000e\u0003\u0005\u0002>\neF\u0011\tBx)\u0005y\u0006\u0002\u0003Bz\u0005s#IA!>\u0002\u0013QL\b/Z!mS\u0006\u001cHcA0\u0003x\"A!\u0011 By\u0001\u0004\t))A\u0001y\u0011)\tiE!/\u0002\u0002\u0013\u0005!Q \u000b\t\u0005K\u0014yp!\u0001\u0004\u0004!Q!Q\u0010B~!\u0003\u0005\r!!\"\t\u0015\t-'1 I\u0001\u0002\u0004\u0011y\r\u0003\u0006\u0003X\nm\b\u0013!a\u0001\u00057D!\"!\u0017\u0003:F\u0005I\u0011AB\u0004+\t\u0019IA\u000b\u0003\u0002\u0006\u0006}\u0003BCA:\u0005s\u000b\n\u0011\"\u0001\u0004\u000eU\u00111q\u0002\u0016\u0005\u0005\u001f\fy\u0006\u0003\u0006\u0002x\te\u0016\u0013!C\u0001\u0007')\"a!\u0006+\t\tm\u0017q\f\u0005\u000b\u0003w\u0012I,!A\u0005B\u0005u\u0004BCAA\u0005s\u000b\t\u0011\"\u0001\u0002\u0004\"Q\u0011Q\u0012B]\u0003\u0003%\ta!\b\u0015\t\u0005E5q\u0004\u0005\u000b\u00033\u001bY\"!AA\u0002\u0005\u0015\u0005BCAO\u0005s\u000b\t\u0011\"\u0011\u0002 \"Q\u0011q\u0016B]\u0003\u0003%\ta!\n\u0015\u0007\u001d\u001b9\u0003\u0003\u0006\u0002\u001a\u000e\r\u0012\u0011!a\u0001\u0003#C!\"a.\u0003:\u0006\u0005I\u0011IA]\u0011)\t\u0019M!/\u0002\u0002\u0013\u00053Q\u0006\u000b\u0004\u000f\u000e=\u0002BCAM\u0007W\t\t\u00111\u0001\u0002\u0012\u001aA11\u0007BU!\n\u0019)D\u0001\u0003UKb$8cBB\u0019\u0015\t}&%\n\u0005\f\u0007s\u0019\tD!f\u0001\n\u0003\u0011I(A\u0003wC2,X\r\u0003\u0006\u0004>\rE\"\u0011#Q\u0001\n}\u000baA^1mk\u0016\u0004\u0003b\u0002 \u00042\u0011\u00051\u0011\t\u000b\u0005\u0007\u0007\u001a)\u0005\u0005\u0003\u0003B\u000eE\u0002bBB\u001d\u0007\u007f\u0001\ra\u0018\u0005\t\u0003{\u001b\t\u0004\"\u0011\u0003p\"Q\u0011QJB\u0019\u0003\u0003%\taa\u0013\u0015\t\r\r3Q\n\u0005\n\u0007s\u0019I\u0005%AA\u0002}C!\"!\u0017\u00042E\u0005I\u0011AB)+\t\u0019\u0019FK\u0002`\u0003?B!\"a\u001f\u00042\u0005\u0005I\u0011IA?\u0011)\t\ti!\r\u0002\u0002\u0013\u0005\u00111\u0011\u0005\u000b\u0003\u001b\u001b\t$!A\u0005\u0002\rmC\u0003BAI\u0007;B!\"!'\u0004Z\u0005\u0005\t\u0019AAC\u0011)\tij!\r\u0002\u0002\u0013\u0005\u0013q\u0014\u0005\u000b\u0003_\u001b\t$!A\u0005\u0002\r\rDcA$\u0004f!Q\u0011\u0011TB1\u0003\u0003\u0005\r!!%\t\u0015\u0005]6\u0011GA\u0001\n\u0003\nI\f\u0003\u0006\u0002D\u000eE\u0012\u0011!C!\u0007W\"2aRB7\u0011)\tIj!\u001b\u0002\u0002\u0003\u0007\u0011\u0011S\u0004\f\u0007c\u0012I+!A\t\u0002\t\u0019\u0019(\u0001\u0003IK\u0006$\u0007\u0003\u0002Ba\u0007k21Ba/\u0003*\u0006\u0005\t\u0012\u0001\u0002\u0004xM)1QOB=KAa\u0011Q[An\u0003\u000b\u0013yMa7\u0003f\"9ah!\u001e\u0005\u0002\ruDCAB:\u0011)\til!\u001e\u0002\u0002\u0013\u0015\u0013q\u0018\u0005\u000b\u0003S\u001c)(!A\u0005\u0002\u000e\rE\u0003\u0003Bs\u0007\u000b\u001b9i!#\t\u0011\tu4\u0011\u0011a\u0001\u0003\u000bC\u0001Ba3\u0004\u0002\u0002\u0007!q\u001a\u0005\t\u0005/\u001c\t\t1\u0001\u0003\\\"Q\u0011Q_B;\u0003\u0003%\ti!$\u0015\t\r=51\u0013\t\u0006\u0017\u0005m8\u0011\u0013\t\n\u0017\t\u0005\u0011Q\u0011Bh\u00057D!Ba\u0002\u0004\f\u0006\u0005\t\u0019\u0001Bs\u000f-\u00199J!+\u0002\u0002#\u0005!a!'\u0002\tQ+\u0007\u0010\u001e\t\u0005\u0005\u0003\u001cYJB\u0006\u00044\t%\u0016\u0011!E\u0001\u0005\ru5#BBN\u0007?+\u0003cBAk\u0007C{61I\u0005\u0005\u0007G\u000b9NA\tBEN$(/Y2u\rVt7\r^5p]FBqAPBN\t\u0003\u00199\u000b\u0006\u0002\u0004\u001a\"Q\u0011QXBN\u0003\u0003%)%a0\t\u0015\u0005%81TA\u0001\n\u0003\u001bi\u000b\u0006\u0003\u0004D\r=\u0006bBB\u001d\u0007W\u0003\ra\u0018\u0005\u000b\u0003k\u001cY*!A\u0005\u0002\u000eMF\u0003\u0002Bn\u0007kC!Ba\u0002\u00042\u0006\u0005\t\u0019AB\"\r!\u0019IL!+A\u0005\rm&aC*ue&twm\u0015;bi\u0016\u001cRaa.\u000bE\u0015B1ba0\u00048\nU\r\u0011\"\u0001\u0004B\u00061Ao\\6f]N,\"aa1\u0011\u000b1\u001a)Ma0\n\u0007\r\u001d\u0017GA\u0002TKFD1ba3\u00048\nE\t\u0015!\u0003\u0004D\u00069Ao\\6f]N\u0004\u0003b\u0002 \u00048\u0012\u00051q\u001a\u000b\u0005\u0007#\u001c\u0019\u000e\u0005\u0003\u0003B\u000e]\u0006\u0002CB`\u0007\u001b\u0004\raa1\t\u0011\u0005u6q\u0017C!\u0005_D\u0001b!7\u00048\u0012\u000511\\\u0001\u0007CB\u0004XM\u001c3\u0015\t\rE7Q\u001c\u0005\b\u0007s\u00199\u000e1\u0001`\u0011!\u0019\toa.\u0005\u0002\r\r\u0018AC1qa\u0016tG\rS3bIR11\u0011[Bs\u0007OD\u0001B! \u0004`\u0002\u0007\u0011Q\u0011\u0005\b\u0007S\u001cy\u000e1\u0001\u001a\u0003\r\u0019\u00180\u001c\u0005\t\u0007[\u001c9\f\"\u0001\u0004p\u0006a1m\\;oi\nKxJ\u001d3feR!\u0011QQBy\u0011!\u0019\u0019pa;A\u0002\u0005\u0015\u0015!A8\t\u0011\r]8q\u0017C\u0001\u0007s\f!B]3n_Z,wJ\\3t+\t\u0019\t\u000e\u0003\u0005\u0004~\u000e]F\u0011AB}\u0003-\u0011X-\\8wK\u0006c\u0017.Y:\t\u0015\u000553qWA\u0001\n\u0003!\t\u0001\u0006\u0003\u0004R\u0012\r\u0001BCB`\u0007\u007f\u0004\n\u00111\u0001\u0004D\"Q\u0011\u0011LB\\#\u0003%\t\u0001b\u0002\u0016\u0005\u0011%!\u0006BBb\u0003?B!\"a\u001f\u00048\u0006\u0005I\u0011IA?\u0011)\t\tia.\u0002\u0002\u0013\u0005\u00111\u0011\u0005\u000b\u0003\u001b\u001b9,!A\u0005\u0002\u0011EA\u0003BAI\t'A!\"!'\u0005\u0010\u0005\u0005\t\u0019AAC\u0011)\tija.\u0002\u0002\u0013\u0005\u0013q\u0014\u0005\u000b\u0003_\u001b9,!A\u0005\u0002\u0011eAcA$\u0005\u001c!Q\u0011\u0011\u0014C\f\u0003\u0003\u0005\r!!%\t\u0015\u0005]6qWA\u0001\n\u0003\nI\f\u0003\u0006\u0002D\u000e]\u0016\u0011!C!\tC!2a\u0012C\u0012\u0011)\tI\nb\b\u0002\u0002\u0003\u0007\u0011\u0011S\u0004\n\tO\u0011I\u000b#\u0001\u0003\tS\t1b\u0015;sS:<7\u000b^1uKB!!\u0011\u0019C\u0016\r%\u0019IL!+\t\u0002\t!ic\u0005\u0003\u0005,))\u0003b\u0002 \u0005,\u0011\u0005A\u0011\u0007\u000b\u0003\tSA\u0001\u0002\"\u000e\u0005,\u0011\u00051\u0011`\u0001\u0006K6\u0004H/\u001f\u0005\u000b\u0003S$Y#!A\u0005\u0002\u0012eB\u0003BBi\twA\u0001ba0\u00058\u0001\u000711\u0019\u0005\u000b\u0003k$Y#!A\u0005\u0002\u0012}B\u0003\u0002C!\t\u0007\u0002RaCA~\u0007\u0007D!Ba\u0002\u0005>\u0005\u0005\t\u0019ABi\u0011!!9E!+\u0005\u0002\u0011%\u0013A\u0003$s_6\u0004\u0016M]1ngR!\u0011Q\bC&\u0011!\u0011\u0019\u0004\"\u0012A\u0002\tU\u0002\u0002\u0003C(\u0005S#\t\u0001\"\u0015\u0002\u0011]KG\u000eZ2be\u0012,\"!!\u0010\t\u000f\u001d\u00149\n1\u0001\u0003 \"91\u0011\u001eBL\u0001\u0004I\u0002\u0002\u0003C-\u0005/\u0003\r\u0001b\u0017\u0002\u0003Y\u0004B\u0001\"\u0018\u0005`5\t!!C\u0002\u0005b\t\u0011\u0001BV1sS\u0006t7-\u001a\u0004\u0007\tK\u0002\u0001\u0001b\u001a\u0003\u001dA\u0013x\u000e]3s)f\u0004XmS5oIN!A1\rB:\u0011-\u0011\t\tb\u0019\u0003\u0006\u0004%\tAa!\t\u0017\u00115D1\rB\u0001B\u0003%!QQ\u0001\bE>,h\u000eZ:!\u0011\u001dqD1\rC\u0001\tc\"B\u0001b\u001d\u0005vA\u0019!\u0004b\u0019\t\u0011\t\u0005Eq\u000ea\u0001\u0005\u000bC!Ba\u001e\u0005d\t\u0007I\u0011\u0001B=\u0011!!Y\bb\u0019!\u0002\u0013y\u0016\u0001\u00043fg\u000e\u0014\u0018\u000e\u001d;j_:\u0004\u0003B\u0003B?\tG\u0012\r\u0011\"\u0001\u0002\u0004\"I!q\u0019C2A\u0003%\u0011Q\u0011\u0005\n\u00053#\u0019\u0007\"\u0001\u0003\t\u0007#b\u0001\"\"\u0005\n\u0012-E\u0003\u0002BP\t\u000fCqa\u001aCA\u0001\u0004\u0011y\nC\u0004\u0004j\u0012\u0005\u0005\u0019A\r\t\u0011\u0011eC\u0011\u0011a\u0001\t7B\u0001B!$\u0005d\u0011\u0005!\u0011\u0010\u0005\t\u0005##\u0019\u0007\"\u0001\u0003z\u001d9A1\u0013\u0001\t\u0002\u0011U\u0015A\u0004)s_B,'\u000fV=qK.Kg\u000e\u001a\t\u00045\u0011]ea\u0002C3\u0001!\u0005A\u0011T\n\u0004\t/S\u0001b\u0002 \u0005\u0018\u0012\u0005AQ\u0014\u000b\u0003\t+C\u0001\"!;\u0005\u0018\u0012\u0005A\u0011U\u000b\u0003\tgB\u0001\"!;\u0005\u0018\u0012\u0005AQ\u0015\u000b\u0005\tg\"9\u000b\u0003\u0005\u0003\u0002\u0012\r\u0006\u0019\u0001BC\u0011!\t)\u0010b&\u0005\u0002\u0011-F\u0003\u0002CW\tg\u0003Ra\u0003CX\u0005\u000bK1\u0001\"-\u0007\u0005\u0011\u0019v.\\3\t\u0011\u0011UF\u0011\u0016a\u0001\tg\n1\u0001\u001d;l\r\u0019!I\f\u0001\u0001\u0005<\nYA+\u001f9f\u0007>t7*\u001b8e'\u0011!9La\u001d\t\u0017\t\u0005Eq\u0017BC\u0002\u0013\u0005!1\u0011\u0005\f\t[\"9L!A!\u0002\u0013\u0011)\tC\u0006\u0005D\u0012]&Q1A\u0005\u0002\u0011\u0015\u0017\u0001B1sON,\"\u0001b2\u0011\u000b1\u001a)\r\"3\u0011\t\u0011-Gq\u001f\b\u00045\u00115wa\u0002Ch\u0001!\u0005A\u0011[\u0001\f)f\u0004XmQ8o\u0017&tG\rE\u0002\u001b\t'4q\u0001\"/\u0001\u0011\u0003!)nE\u0002\u0005T*AqA\u0010Cj\t\u0003!I\u000e\u0006\u0002\u0005R\"A\u0011\u0011\u001eCj\t\u0003!i\u000e\u0006\u0003\u0005`\u0012\u0005\bc\u0001\u000e\u00058\"AA1\u0019Cn\u0001\u0004!9\r\u0003\u0005\u0002j\u0012MG\u0011\u0001Cs)\u0019!y\u000eb:\u0005j\"A!\u0011\u0011Cr\u0001\u0004\u0011)\t\u0003\u0005\u0005D\u0012\r\b\u0019\u0001Cd\u0011!\t)\u0010b5\u0005\u0002\u00115H\u0003\u0002Cx\tg\u0004Ra\u0003CX\tc\u0004baC\f\u0003\u0006\u0012\u001d\u0007\u0002\u0003C{\tW\u0004\r\u0001b8\u0002\u0007Q\u001c7NB\u0004\u0005z\u0012M\u0007\tb?\u0003\u0011\u0005\u0013x-^7f]R\u001cR\u0001b>\u000bE\u0015B!B\u000eC|\u0005+\u0007I\u0011\u0001C\u0000+\t!Y\u0006\u0003\u00069\to\u0014\t\u0012)A\u0005\t7B1\"\"\u0002\u0005x\nU\r\u0011\"\u0001\u0006\b\u0005!1.\u001b8e+\t\u0011\u0019\bC\u0006\u0006\f\u0011](\u0011#Q\u0001\n\tM\u0014!B6j]\u0012\u0004\u0003bCBu\to\u0014)\u0019!C\u0001\u000b\u001f)\u0012!\u0007\u0005\u000b\u000b'!9P!A!\u0002\u0013I\u0012\u0001B:z[\u0002BqA\u0010C|\t\u0003)9\u0002\u0006\u0004\u0006\u001a\u0015\u0005R1\u0005\u000b\u0005\u000b7)y\u0002\u0005\u0003\u0006\u001e\u0011]XB\u0001Cj\u0011\u001d\u0019I/\"\u0006A\u0002eAqANC\u000b\u0001\u0004!Y\u0006\u0003\u0005\u0006\u0006\u0015U\u0001\u0019\u0001B:\u0011)\ti\u0005b>\u0002\u0002\u0013\u0005Qq\u0005\u000b\u0007\u000bS)i#b\f\u0015\t\u0015mQ1\u0006\u0005\b\u0007S,)\u00031\u0001\u001a\u0011%1TQ\u0005I\u0001\u0002\u0004!Y\u0006\u0003\u0006\u0006\u0006\u0015\u0015\u0002\u0013!a\u0001\u0005gB!\"!\u0017\u0005xF\u0005I\u0011AC\u001a+\t))D\u000b\u0003\u0005\\\u0005}\u0003BCA:\to\f\n\u0011\"\u0001\u0006:U\u0011Q1\b\u0016\u0005\u0005g\ny\u0006\u0003\u0006\u0002|\u0011]\u0018\u0011!C!\u0003{B!\"!!\u0005x\u0006\u0005I\u0011AAB\u0011)\ti\tb>\u0002\u0002\u0013\u0005Q1\t\u000b\u0005\u0003#+)\u0005\u0003\u0006\u0002\u001a\u0016\u0005\u0013\u0011!a\u0001\u0003\u000bC!\"!(\u0005x\u0006\u0005I\u0011IAP\u0011)\ty\u000bb>\u0002\u0002\u0013\u0005Q1\n\u000b\u0004\u000f\u00165\u0003BCAM\u000b\u0013\n\t\u00111\u0001\u0002\u0012\"Q\u0011q\u0017C|\u0003\u0003%\t%!/\t\u0015\u0005uFq_A\u0001\n\u0003\ny\f\u0003\u0006\u0002D\u0012]\u0018\u0011!C!\u000b+\"2aRC,\u0011)\tI*b\u0015\u0002\u0002\u0003\u0007\u0011\u0011S\u0004\u000b\u000b7\"\u0019.!A\t\u0002\u0015u\u0013\u0001C!sOVlWM\u001c;\u0011\t\u0015uQq\f\u0004\u000b\ts$\u0019.!A\t\u0002\u0015\u00054\u0003BC0\u0015\u0015BqAPC0\t\u0003))\u0007\u0006\u0002\u0006^!Q\u0011QXC0\u0003\u0003%)%a0\t\u0015\u0005%XqLA\u0001\n\u0003+Y\u0007\u0006\u0004\u0006n\u0015ET1\u000f\u000b\u0005\u000b7)y\u0007C\u0004\u0004j\u0016%\u0004\u0019A\r\t\u000fY*I\u00071\u0001\u0005\\!AQQAC5\u0001\u0004\u0011\u0019\b\u0003\u0006\u0002v\u0016}\u0013\u0011!CA\u000bo\"B!\"\u001f\u0006~A)1\"a?\u0006|A11b\u0006C.\u0005gB!Ba\u0002\u0006v\u0005\u0005\t\u0019AC\u000e\u0011-)\t\tb.\u0003\u0002\u0003\u0006I\u0001b2\u0002\u000b\u0005\u0014xm\u001d\u0011\t\u000fy\"9\f\"\u0001\u0006\u0006R1Aq\\CD\u000b\u0013C\u0001B!!\u0006\u0004\u0002\u0007!Q\u0011\u0005\t\t\u0007,\u0019\t1\u0001\u0005H\"Q!Q\u0010C\\\u0005\u0004%\t!a!\t\u0013\t\u001dGq\u0017Q\u0001\n\u0005\u0015\u0005\u0002\u0003B<\to#\tA!\u001f\t\u000f\tUEq\u0017C!\r\"A!Q\u0012C\\\t\u0003\u0011I\bC\u0005\u0003\u001a\u0012]F\u0011\u0001\u0002\u0006\u0018R1Q\u0011TCP\u000bC#BAa(\u0006\u001c\"AQQTCK\u0001\u0004\u0011y*\u0001\u0002ta!91\u0011^CK\u0001\u0004I\u0002\u0002\u0003C-\u000b+\u0003\r\u0001b\u0017\t\u0011\tEEq\u0017C\u0001\u0005s:q!b*\u0001\u0011\u0003)I+A\u0005j]\u001a,'oS5oIB\u0019!$b+\u0007\u000f\u00155\u0006\u0001#\u0001\u00060\nI\u0011N\u001c4fe.Kg\u000eZ\n\u0004\u000bWS\u0001b\u0002 \u0006,\u0012\u0005Q1\u0017\u000b\u0003\u000bS3\u0001\"b.\u0006,\u0006\u0005Q\u0011\u0018\u0002\n\u0013:4WM]&j]\u0012\u001c2!\".\u000b\u0011\u001dqTQ\u0017C\u0001\u000b{#\"!b0\u0011\t\u0015\u0005WQW\u0007\u0003\u000bWC\u0001\"\"2\u00066\u001aEQqY\u0001\u0006S:4WM\u001d\u000b\t\u0005g*I-\"4\u0006P\"AQ1ZCb\u0001\u0004\ti$A\u0002ua\u0016DqAa\u0011\u0006D\u0002\u0007\u0011\u0004C\u0004\u0006R\u0016\r\u0007\u0019A$\u0002\u0011Q|\u0007\u000fT3wK2D\u0001\"\"2\u00066\u0012EQQ\u001b\u000b\u0007\u0005g*9.\"7\t\u000f\r%X1\u001ba\u00013!9Q\u0011[Cj\u0001\u00049\u0005\u0002CAu\u000bk#\t!\"8\u0015\t\tMTq\u001c\u0005\b\u0007S,Y\u000e1\u0001\u001a\u0011!\tI/\".\u0005\u0002\u0015\rHC\u0002B:\u000bK,9\u000f\u0003\u0005\u0006L\u0016\u0005\b\u0019AA\u001f\u0011\u001d\u0011\u0019%\"9A\u0002eA\u0001\"!;\u0006,\u0012\u0005Q1\u001e\u000b\u0005\u000b\u007f+i\u000f\u0003\u0005\u0003@\u0015%\b\u0019AA\u001f!\u0011!i&\"=\n\u0007\u0015M(AA\u0006Ts6\u0014w\u000e\u001c+bE2,\u0007")
public interface Kinds {
    public void scala$reflect$internal$Kinds$_setter_$NoKindErrors_$eq(KindErrors var1);

    public Kinds$KindErrors$ KindErrors();

    public KindErrors NoKindErrors();

    public boolean kindsConform(List<Symbols.Symbol> var1, List<Types.Type> var2, Types.Type var3, Symbols.Symbol var4);

    public List<Tuple3<Types.Type, Symbols.Symbol, KindErrors>> checkKindBounds0(List<Symbols.Symbol> var1, List<Types.Type> var2, Types.Type var3, Symbols.Symbol var4, boolean var5);

    public Kinds$Kind$ Kind();

    public Kinds$ProperTypeKind$ ProperTypeKind();

    public Kinds$TypeConKind$ TypeConKind();

    public Kinds$inferKind$ inferKind();

    public static abstract class Kind {
        public final /* synthetic */ SymbolTable $outer;

        public abstract String description();

        public abstract int order();

        public abstract Types.TypeBounds bounds();

        public abstract String scalaNotation();

        public abstract String starNotation();

        public boolean hasBounds() {
            return !this.bounds().isEmptyBounds();
        }

        public abstract StringState buildState(Symbols.Symbol var1, int var2, StringState var3);

        public /* synthetic */ SymbolTable scala$reflect$internal$Kinds$Kind$$$outer() {
            return this.$outer;
        }

        public Kind(SymbolTable $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }

        public class StringState
        implements Product,
        Serializable {
            private final Seq<ScalaNotation> tokens;
            public final /* synthetic */ Kinds$Kind$ $outer;

            public Seq<ScalaNotation> tokens() {
                return this.tokens;
            }

            public String toString() {
                return this.tokens().mkString();
            }

            public StringState append(String value) {
                return new StringState(this.scala$reflect$internal$Kinds$Kind$StringState$$$outer(), this.tokens().$colon$plus(new Text(this.scala$reflect$internal$Kinds$Kind$StringState$$$outer(), value), Seq$.MODULE$.canBuildFrom()));
            }

            public StringState appendHead(int order, Symbols.Symbol sym) {
                int n = this.countByOrder(order) + 1;
                Option alias = sym == this.scala$reflect$internal$Kinds$Kind$StringState$$$outer().scala$reflect$internal$Kinds$Kind$$$outer().NoSymbol() ? None$.MODULE$ : new Some<String>(sym.nameString());
                return new StringState(this.scala$reflect$internal$Kinds$Kind$StringState$$$outer(), this.tokens().$colon$plus(new Head(this.scala$reflect$internal$Kinds$Kind$StringState$$$outer(), order, new Some<Object>(BoxesRunTime.boxToInteger(n)), alias), Seq$.MODULE$.canBuildFrom()));
            }

            public int countByOrder(int o) {
                return this.tokens().count((Function1<ScalaNotation, Object>)((Object)new Serializable(this, o){
                    public static final long serialVersionUID = 0L;
                    private final int o$1;

                    public final boolean apply(ScalaNotation x0$1) {
                        Head head2;
                        boolean bl = x0$1 instanceof Head && this.o$1 == (head2 = (Head)x0$1).order();
                        return bl;
                    }
                    {
                        this.o$1 = o$1;
                    }
                }));
            }

            public StringState removeOnes() {
                int maxOrder = BoxesRunTime.unboxToInt(((TraversableOnce)this.tokens().map(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final int apply(ScalaNotation x0$2) {
                        int n;
                        if (x0$2 instanceof Head) {
                            Head head2 = (Head)x0$2;
                            n = head2.order();
                        } else {
                            n = 0;
                        }
                        return n;
                    }
                }, Seq$.MODULE$.canBuildFrom())).max(Ordering$Int$.MODULE$));
                Seq<ScalaNotation> seq = this.tokens();
                Predef$ predef$ = Predef$.MODULE$;
                return new StringState(this.scala$reflect$internal$Kinds$Kind$StringState$$$outer(), RichInt$.MODULE$.to$extension0(0, maxOrder).$div$colon(seq, new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    public final /* synthetic */ StringState $outer;

                    public final Seq<ScalaNotation> apply(Seq<ScalaNotation> ts, int o) {
                        return this.$outer.countByOrder(o) <= 1 ? ts.map(new Serializable(this, o){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ Kind$StringState$$anonfun$removeOnes$1 $outer;
                            private final int o$2;

                            public final ScalaNotation apply(ScalaNotation x0$3) {
                                Head head2;
                                ScalaNotation scalaNotation2 = x0$3 instanceof Head && this.o$2 == (head2 = (Head)x0$3).order() ? new Head(this.$outer.$outer.scala$reflect$internal$Kinds$Kind$StringState$$$outer(), this.o$2, None$.MODULE$, head2.alias()) : x0$3;
                                return scalaNotation2;
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                                this.o$2 = o$2;
                            }
                        }, Seq$.MODULE$.canBuildFrom()) : ts;
                    }

                    public /* synthetic */ StringState scala$reflect$internal$Kinds$Kind$StringState$$anonfun$$$outer() {
                        return this.$outer;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }));
            }

            public StringState removeAlias() {
                return new StringState(this.scala$reflect$internal$Kinds$Kind$StringState$$$outer(), this.tokens().map(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ StringState $outer;

                    public final ScalaNotation apply(ScalaNotation x0$4) {
                        Head head2;
                        ScalaNotation scalaNotation2 = x0$4 instanceof Head && (head2 = (Head)x0$4).alias() instanceof Some ? new Head(this.$outer.scala$reflect$internal$Kinds$Kind$StringState$$$outer(), head2.order(), head2.n(), None$.MODULE$) : x0$4;
                        return scalaNotation2;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }, Seq$.MODULE$.canBuildFrom()));
            }

            public StringState copy(Seq<ScalaNotation> tokens) {
                return new StringState(this.scala$reflect$internal$Kinds$Kind$StringState$$$outer(), tokens);
            }

            public Seq<ScalaNotation> copy$default$1() {
                return this.tokens();
            }

            @Override
            public String productPrefix() {
                return "StringState";
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
                return this.tokens();
            }

            @Override
            public Iterator<Object> productIterator() {
                return ScalaRunTime$.MODULE$.typedProductIterator(this);
            }

            @Override
            public boolean canEqual(Object x$1) {
                return x$1 instanceof StringState;
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
                if (!(x$1 instanceof StringState)) return false;
                if (((StringState)x$1).scala$reflect$internal$Kinds$Kind$StringState$$$outer() != this.scala$reflect$internal$Kinds$Kind$StringState$$$outer()) return false;
                boolean bl = true;
                if (!bl) return false;
                StringState stringState = (StringState)x$1;
                Seq<ScalaNotation> seq = this.tokens();
                Seq<ScalaNotation> seq2 = stringState.tokens();
                if (seq == null) {
                    if (seq2 != null) {
                        return false;
                    }
                } else if (!seq.equals(seq2)) return false;
                if (!stringState.canEqual(this)) return false;
                return true;
            }

            public /* synthetic */ Kinds$Kind$ scala$reflect$internal$Kinds$Kind$StringState$$$outer() {
                return this.$outer;
            }

            public StringState(Kinds$Kind$ $outer, Seq<ScalaNotation> tokens) {
                this.tokens = tokens;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                Product$class.$init$(this);
            }
        }
    }

    public class KindErrors
    implements Product,
    Serializable {
        private final List<Tuple2<Symbols.Symbol, Symbols.Symbol>> arity;
        private final List<Tuple2<Symbols.Symbol, Symbols.Symbol>> variance;
        private final List<Tuple2<Symbols.Symbol, Symbols.Symbol>> strictness;
        public final /* synthetic */ SymbolTable $outer;

        public List<Tuple2<Symbols.Symbol, Symbols.Symbol>> arity() {
            return this.arity;
        }

        public List<Tuple2<Symbols.Symbol, Symbols.Symbol>> variance() {
            return this.variance;
        }

        public List<Tuple2<Symbols.Symbol, Symbols.Symbol>> strictness() {
            return this.strictness;
        }

        public boolean isEmpty() {
            return this.arity().isEmpty() && this.variance().isEmpty() && this.strictness().isEmpty();
        }

        public KindErrors arityError(Tuple2<Symbols.Symbol, Symbols.Symbol> syms) {
            return this.copy(this.arity().$colon$plus(syms, List$.MODULE$.canBuildFrom()), this.copy$default$2(), this.copy$default$3());
        }

        public KindErrors varianceError(Tuple2<Symbols.Symbol, Symbols.Symbol> syms) {
            List<Tuple2<Symbols.Symbol, Symbols.Symbol>> x$18 = this.variance().$colon$plus(syms, List$.MODULE$.canBuildFrom());
            List<Tuple2<Symbols.Symbol, Symbols.Symbol>> x$19 = this.copy$default$1();
            List<Tuple2<Symbols.Symbol, Symbols.Symbol>> x$20 = this.copy$default$3();
            return this.copy(x$19, x$18, x$20);
        }

        public KindErrors strictnessError(Tuple2<Symbols.Symbol, Symbols.Symbol> syms) {
            List<Tuple2<Symbols.Symbol, Symbols.Symbol>> x$21 = this.strictness().$colon$plus(syms, List$.MODULE$.canBuildFrom());
            List<Tuple2<Symbols.Symbol, Symbols.Symbol>> x$22 = this.copy$default$1();
            List<Tuple2<Symbols.Symbol, Symbols.Symbol>> x$23 = this.copy$default$2();
            return this.copy(x$22, x$23, x$21);
        }

        public KindErrors $plus$plus(KindErrors errs) {
            return new KindErrors(this.scala$reflect$internal$Kinds$KindErrors$$$outer(), this.arity().$plus$plus(errs.arity(), List$.MODULE$.canBuildFrom()), this.variance().$plus$plus(errs.variance(), List$.MODULE$.canBuildFrom()), this.strictness().$plus$plus(errs.strictness(), List$.MODULE$.canBuildFrom()));
        }

        public String scala$reflect$internal$Kinds$KindErrors$$varStr(Symbols.Symbol s2) {
            return s2.isCovariant() ? "covariant" : (s2.isContravariant() ? "contravariant" : "invariant");
        }

        private String qualify(Symbols.Symbol a0, Symbols.Symbol b0) {
            String string2;
            String string3 = a0.toString();
            String string4 = b0.toString();
            if (!(string3 != null ? !string3.equals(string4) : string4 != null)) {
                if (a0 == b0 || a0.owner() == b0.owner()) {
                    string2 = "";
                } else {
                    Symbols.Symbol a = a0;
                    Symbols.Symbol b = b0;
                    while (true) {
                        Names.Name name = a.owner().name();
                        Names.Name name2 = b.owner().name();
                        if (name != null ? !name.equals(name2) : name2 != null) break;
                        a = a.owner();
                        b = b.owner();
                    }
                    string2 = a.locationString() != "" ? new StringBuilder().append((Object)" (").append((Object)a.locationString().trim()).append((Object)")").toString() : "";
                }
            } else {
                string2 = "";
            }
            return string2;
        }

        private String kindMessage(Symbols.Symbol a, Symbols.Symbol p, Function2<String, String, String> f) {
            return f.apply(Predef$any2stringadd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(a), this.qualify(a, p)), Predef$any2stringadd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(p), this.qualify(p, a)));
        }

        public String scala$reflect$internal$Kinds$KindErrors$$strictnessMessage(Symbols.Symbol a, Symbols.Symbol p) {
            return this.kindMessage(a, p, (Function2<String, String, String>)((Object)new Serializable(this, a, p){
                public static final long serialVersionUID = 0L;
                private final Symbols.Symbol a$1;
                private final Symbols.Symbol p$1;

                public final String apply(String x$1, String x$2) {
                    Types.TypeBounds typeBounds;
                    Predef$ predef$ = Predef$.MODULE$;
                    Object[] objectArray = new Object[4];
                    objectArray[0] = x$1;
                    objectArray[1] = this.a$1.info();
                    objectArray[2] = x$2;
                    Types.Type type = this.p$1.info();
                    String string2 = type instanceof Types.TypeBounds && (typeBounds = (Types.TypeBounds)type).isEmptyBounds() ? " >: Nothing <: Any" : String.valueOf(type);
                    objectArray[3] = string2;
                    return new StringOps("%s's bounds%s are stricter than %s's declared bounds%s").format(Predef$.MODULE$.genericWrapArray(objectArray));
                }
                {
                    this.a$1 = a$1;
                    this.p$1 = p$1;
                }
            }));
        }

        public String scala$reflect$internal$Kinds$KindErrors$$varianceMessage(Symbols.Symbol a, Symbols.Symbol p) {
            return this.kindMessage(a, p, (Function2<String, String, String>)((Object)new Serializable(this, a, p){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ KindErrors $outer;
                private final Symbols.Symbol a$2;
                private final Symbols.Symbol p$2;

                public final String apply(String x$3, String x$4) {
                    Predef$ predef$ = Predef$.MODULE$;
                    return new StringOps("%s is %s, but %s is declared %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{x$3, this.$outer.scala$reflect$internal$Kinds$KindErrors$$varStr(this.a$2), x$4, this.$outer.scala$reflect$internal$Kinds$KindErrors$$varStr(this.p$2)}));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.a$2 = a$2;
                    this.p$2 = p$2;
                }
            }));
        }

        public String scala$reflect$internal$Kinds$KindErrors$$arityMessage(Symbols.Symbol a, Symbols.Symbol p) {
            return this.kindMessage(a, p, (Function2<String, String, String>)((Object)new Serializable(this, a, p){
                public static final long serialVersionUID = 0L;
                private final Symbols.Symbol a$3;
                private final Symbols.Symbol p$3;

                public final String apply(String x$5, String x$6) {
                    Predef$ predef$ = Predef$.MODULE$;
                    return new StringOps("%s has %s, but %s has %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{x$5, StringOps$.MODULE$.countElementsAsString(this.a$3.typeParams().length(), "type parameter"), x$6, StringOps$.MODULE$.countAsString(this.p$3.typeParams().length())}));
                }
                {
                    this.a$3 = a$3;
                    this.p$3 = p$3;
                }
            }));
        }

        private String buildMessage(List<Tuple2<Symbols.Symbol, Symbols.Symbol>> xs, Function2<Symbols.Symbol, Symbols.Symbol, String> f) {
            return xs.isEmpty() ? "" : ((TraversableOnce)xs.map(f.tupled(), List$.MODULE$.canBuildFrom())).mkString("\n", ", ", "");
        }

        public String errorMessage(Types.Type targ, Symbols.Symbol tparam) {
            return new StringBuilder().append((Object)Predef$any2stringadd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(targ), "'s type parameters do not match ")).append(tparam).append((Object)"'s expected parameters:").append((Object)this.buildMessage(this.arity(), (Function2<Symbols.Symbol, Symbols.Symbol, String>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ KindErrors $outer;

                public final String apply(Symbols.Symbol a, Symbols.Symbol p) {
                    return this.$outer.scala$reflect$internal$Kinds$KindErrors$$arityMessage(a, p);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }))).append((Object)this.buildMessage(this.variance(), (Function2<Symbols.Symbol, Symbols.Symbol, String>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ KindErrors $outer;

                public final String apply(Symbols.Symbol a, Symbols.Symbol p) {
                    return this.$outer.scala$reflect$internal$Kinds$KindErrors$$varianceMessage(a, p);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }))).append((Object)this.buildMessage(this.strictness(), (Function2<Symbols.Symbol, Symbols.Symbol, String>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ KindErrors $outer;

                public final String apply(Symbols.Symbol a, Symbols.Symbol p) {
                    return this.$outer.scala$reflect$internal$Kinds$KindErrors$$strictnessMessage(a, p);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }))).toString();
        }

        public KindErrors copy(List<Tuple2<Symbols.Symbol, Symbols.Symbol>> arity, List<Tuple2<Symbols.Symbol, Symbols.Symbol>> variance, List<Tuple2<Symbols.Symbol, Symbols.Symbol>> strictness) {
            return new KindErrors(this.scala$reflect$internal$Kinds$KindErrors$$$outer(), arity, variance, strictness);
        }

        public List<Tuple2<Symbols.Symbol, Symbols.Symbol>> copy$default$1() {
            return this.arity();
        }

        public List<Tuple2<Symbols.Symbol, Symbols.Symbol>> copy$default$2() {
            return this.variance();
        }

        public List<Tuple2<Symbols.Symbol, Symbols.Symbol>> copy$default$3() {
            return this.strictness();
        }

        @Override
        public String productPrefix() {
            return "KindErrors";
        }

        @Override
        public int productArity() {
            return 3;
        }

        @Override
        public Object productElement(int x$1) {
            List<Tuple2<Symbols.Symbol, Symbols.Symbol>> list2;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 2: {
                    list2 = this.strictness();
                    break;
                }
                case 1: {
                    list2 = this.variance();
                    break;
                }
                case 0: {
                    list2 = this.arity();
                }
            }
            return list2;
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof KindErrors;
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
            if (!(x$1 instanceof KindErrors)) return false;
            if (((KindErrors)x$1).scala$reflect$internal$Kinds$KindErrors$$$outer() != this.scala$reflect$internal$Kinds$KindErrors$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            KindErrors kindErrors = (KindErrors)x$1;
            List<Tuple2<Symbols.Symbol, Symbols.Symbol>> list2 = this.arity();
            List<Tuple2<Symbols.Symbol, Symbols.Symbol>> list3 = kindErrors.arity();
            if (list2 == null) {
                if (list3 != null) {
                    return false;
                }
            } else if (!((Object)list2).equals(list3)) return false;
            List<Tuple2<Symbols.Symbol, Symbols.Symbol>> list4 = this.variance();
            List<Tuple2<Symbols.Symbol, Symbols.Symbol>> list5 = kindErrors.variance();
            if (list4 == null) {
                if (list5 != null) {
                    return false;
                }
            } else if (!((Object)list4).equals(list5)) return false;
            List<Tuple2<Symbols.Symbol, Symbols.Symbol>> list6 = this.strictness();
            List<Tuple2<Symbols.Symbol, Symbols.Symbol>> list7 = kindErrors.strictness();
            if (list6 == null) {
                if (list7 != null) {
                    return false;
                }
            } else if (!((Object)list6).equals(list7)) return false;
            if (!kindErrors.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Kinds$KindErrors$$$outer() {
            return this.$outer;
        }

        public KindErrors(SymbolTable $outer, List<Tuple2<Symbols.Symbol, Symbols.Symbol>> arity, List<Tuple2<Symbols.Symbol, Symbols.Symbol>> variance, List<Tuple2<Symbols.Symbol, Symbols.Symbol>> strictness) {
            this.arity = arity;
            this.variance = variance;
            this.strictness = strictness;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Product$class.$init$(this);
        }
    }

    public class TypeConKind
    extends Kind {
        private final Types.TypeBounds bounds;
        private final Seq<Argument> args;
        private final int order;

        @Override
        public Types.TypeBounds bounds() {
            return this.bounds;
        }

        public Seq<Argument> args() {
            return this.args;
        }

        @Override
        public int order() {
            return this.order;
        }

        @Override
        public String description() {
            return this.order() == 1 ? "This is a type constructor: a 1st-order-kinded type." : "This is a type constructor that takes type constructor(s): a higher-kinded type.";
        }

        @Override
        public boolean hasBounds() {
            return super.hasBounds() || this.args().exists((Function1<Argument, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Argument x$15) {
                    return x$15.kind().hasBounds();
                }
            }));
        }

        @Override
        public String scalaNotation() {
            Kind.StringState s2 = this.buildState(this.scala$reflect$internal$Kinds$TypeConKind$$$outer().NoSymbol(), Variance$.MODULE$.Invariant(), this.scala$reflect$internal$Kinds$TypeConKind$$$outer().Kind().StringState().empty()).removeOnes();
            Kind.StringState s22 = this.hasBounds() ? s2 : s2.removeAlias();
            return s22.toString();
        }

        @Override
        public Kind.StringState buildState(Symbols.Symbol sym, int v, Kind.StringState s0) {
            ObjectRef<Kind.StringState> s2 = ObjectRef.create(s0);
            s2.elem = ((Kind.StringState)s2.elem).append(Variance$.MODULE$.symbolicString$extension(v)).appendHead(this.order(), sym).append("[");
            ((IterableLike)this.args().zipWithIndex(Seq$.MODULE$.canBuildFrom())).foreach(new Serializable(this, s2){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TypeConKind $outer;
                private final ObjectRef s$1;

                public final void apply(Tuple2<Argument, Object> x0$5) {
                    if (x0$5 != null) {
                        this.s$1.elem = x0$5._1().kind().buildState(x0$5._1().sym(), x0$5._1().variance(), (Kind.StringState)this.s$1.elem);
                        if (x0$5._2$mcI$sp() != this.$outer.args().size() - 1) {
                            this.s$1.elem = ((Kind.StringState)this.s$1.elem).append(",");
                        }
                        return;
                    }
                    throw new MatchError(x0$5);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.s$1 = s$1;
                }
            });
            s2.elem = ((Kind.StringState)s2.elem).append("]").append(this.bounds().scalaNotation((Function1<Types.Type, String>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final String apply(Types.Type x$16) {
                    return x$16.toString();
                }
            })));
            return (Kind.StringState)s2.elem;
        }

        @Override
        public String starNotation() {
            return new StringBuilder().append((Object)((TraversableOnce)this.args().map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final String apply(Argument arg) {
                    return new StringBuilder().append((Object)(arg.kind().order() == 0 ? arg.kind().starNotation() : new StringBuilder().append((Object)"(").append((Object)arg.kind().starNotation()).append((Object)")").toString())).append((Object)(arg.variance() == Variance$.MODULE$.Invariant() ? " -> " : new StringBuilder().append((Object)" -(").append((Object)Variance$.MODULE$.symbolicString$extension(arg.variance())).append((Object)")-> ").toString())).toString();
                }
            }, Seq$.MODULE$.canBuildFrom())).mkString()).append((Object)"*").append((Object)this.bounds().starNotation((Function1<Types.Type, String>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final String apply(Types.Type x$17) {
                    return x$17.toString();
                }
            }))).toString();
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Kinds$TypeConKind$$$outer() {
            return this.$outer;
        }

        public TypeConKind(SymbolTable $outer, Types.TypeBounds bounds, Seq<Argument> args) {
            this.bounds = bounds;
            this.args = args;
            super($outer);
            this.order = BoxesRunTime.unboxToInt(((TraversableOnce)args.map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final int apply(Argument x$14) {
                    return x$14.kind().order();
                }
            }, Seq$.MODULE$.canBuildFrom())).max(Ordering$Int$.MODULE$)) + 1;
        }
    }

    public class ProperTypeKind
    extends Kind {
        private final Types.TypeBounds bounds;
        private final String description;
        private final int order;

        @Override
        public Types.TypeBounds bounds() {
            return this.bounds;
        }

        @Override
        public String description() {
            return this.description;
        }

        @Override
        public int order() {
            return this.order;
        }

        @Override
        public Kind.StringState buildState(Symbols.Symbol sym, int v, Kind.StringState s2) {
            return s2.append(Variance$.MODULE$.symbolicString$extension(v)).appendHead(this.order(), sym).append(this.bounds().scalaNotation((Function1<Types.Type, String>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final String apply(Types.Type x$11) {
                    return x$11.toString();
                }
            })));
        }

        @Override
        public String scalaNotation() {
            return Predef$any2stringadd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(new Kind.Head(this.scala$reflect$internal$Kinds$ProperTypeKind$$$outer().Kind(), this.order(), None$.MODULE$, None$.MODULE$)), this.bounds().scalaNotation((Function1<Types.Type, String>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final String apply(Types.Type x$12) {
                    return x$12.toString();
                }
            })));
        }

        @Override
        public String starNotation() {
            return new StringBuilder().append((Object)"*").append((Object)this.bounds().starNotation((Function1<Types.Type, String>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final String apply(Types.Type x$13) {
                    return x$13.toString();
                }
            }))).toString();
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Kinds$ProperTypeKind$$$outer() {
            return this.$outer;
        }

        public ProperTypeKind(SymbolTable $outer, Types.TypeBounds bounds) {
            this.bounds = bounds;
            super($outer);
            this.description = "This is a proper type.";
            this.order = 0;
        }
    }
}

