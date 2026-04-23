/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Trees;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.Positions$Range$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Trees$EmptyTree$;
import scala.reflect.internal.util.NoPosition$;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.SourceFile;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\r5c!C\u0001\u0003!\u0003\r\t!CB#\u0005%\u0001vn]5uS>t7O\u0003\u0002\u0004\t\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0019\u0001A\u0003\b\u0011\u0005-aQ\"\u0001\u0004\n\u000551!AB!osJ+g\r\u0005\u0002\u0010%5\t\u0001C\u0003\u0002\u0012\t\u0005\u0019\u0011\r]5\n\u0005\u0005\u0001\u0002\"\u0002\u000b\u0001\t\u0003)\u0012A\u0002\u0013j]&$H\u0005F\u0001\u0017!\tYq#\u0003\u0002\u0019\r\t!QK\\5u\u000b\u0011Q\u0002\u0001A\u000e\u0003\u0011A{7/\u001b;j_:\u0004\"\u0001H\u0010\u000e\u0003uQ!A\b\u0002\u0002\tU$\u0018\u000e\\\u0005\u00035uAq!\t\u0001C\u0002\u0013\u0005!%\u0001\u0006O_B{7/\u001b;j_:,\u0012a\t\b\u0003I=r!!\n\u0018\u000f\u0005\u0019jcBA\u0014-\u001d\tA3&D\u0001*\u0015\tQ\u0003\"\u0001\u0004=e>|GOP\u0005\u0002\u000f%\u0011QAB\u0005\u0003\u0007\u0011I!A\b\u0002\n\u0005\u0005j\u0002BB\u0019\u0001A\u0003%1%A\u0006O_B{7/\u001b;j_:\u0004\u0003bB\u001a\u0001\u0005\u0004%\u0019\u0001N\u0001\f!>\u001c\u0018\u000e^5p]R\u000bw-F\u00016!\r1t'O\u0007\u0002\t%\u0011\u0001\b\u0002\u0002\t\u00072\f7o\u001d+bOB\u0011!(G\u0007\u0002\u0001!1A\b\u0001Q\u0001\nU\nA\u0002U8tSRLwN\u001c+bO\u0002BQA\u0010\u0001\u0005\u0002}\n!#^:f\u001f\u001a47/\u001a;Q_NLG/[8ogV\t\u0001\t\u0005\u0002\f\u0003&\u0011!I\u0002\u0002\b\u0005>|G.Z1o\u0011\u0015!\u0005\u0001\"\u0001F\u0003-9(/\u00199qS:<\u0007k\\:\u0015\u0007e2\u0005\nC\u0003H\u0007\u0002\u0007\u0011(A\u0004eK\u001a\fW\u000f\u001c;\t\u000b%\u001b\u0005\u0019\u0001&\u0002\u000bQ\u0014X-Z:\u0011\u0007-s\u0015K\u0004\u0002\f\u0019&\u0011QJB\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0005K\u0001\u0003MSN$(BA'\u0007!\tQ$+\u0003\u0002T)\n!AK]3f\u0013\t)&AA\u0003Ue\u0016,7\u000fC\u0003E\u0001\u0011\u0005q\u000b\u0006\u0003:1fS\u0006\"B$W\u0001\u0004I\u0004\"B%W\u0001\u0004Q\u0005\"B.W\u0001\u0004\u0001\u0015!\u00024pGV\u001c\b\"\u0002#\u0001\t\u0003iFCA\u001d_\u0011\u0015IE\f1\u0001K\u0011\u0015\u0001\u0007\u0001\"\u0001b\u0003Q)gn];sK:{gn\u0014<fe2\f\u0007\u000f]5oOR\u0019aC\u00193\t\u000b\r|\u0006\u0019A)\u0002\tQ\u0014X-\u001a\u0005\u0006K~\u0003\rAS\u0001\u0007_RDWM]:\t\u000b\u0001\u0004A\u0011A4\u0015\tYA\u0017N\u001b\u0005\u0006G\u001a\u0004\r!\u0015\u0005\u0006K\u001a\u0004\rA\u0013\u0005\u00067\u001a\u0004\r\u0001\u0011\u0005\u0006Y\u0002!\t!\\\u0001\te\u0006tw-\u001a)pgR)\u0011H\\:yu\")qn\u001ba\u0001a\u000611o\\;sG\u0016\u0004\"\u0001H9\n\u0005Il\"AC*pkJ\u001cWMR5mK\")Ao\u001ba\u0001k\u0006)1\u000f^1siB\u00111B^\u0005\u0003o\u001a\u00111!\u00138u\u0011\u0015I8\u000e1\u0001v\u0003\u0015\u0001x.\u001b8u\u0011\u0015Y8\u000e1\u0001v\u0003\r)g\u000e\u001a\u0005\u0006{\u0002!\tA`\u0001\u0012m\u0006d\u0017\u000eZ1uKB{7/\u001b;j_:\u001cHC\u0001\f\u0000\u0011\u0015\u0019G\u00101\u0001R\u0011\u001d\t\u0019\u0001\u0001C\u0001\u0003\u000b\t\u0001c]8mS\u0012$Um]2f]\u0012\fg\u000e^:\u0015\u0007)\u000b9\u0001\u0003\u0004d\u0003\u0003\u0001\r!\u0015\u0005\b\u0003\u0017\u0001A\u0011BA\u0007\u0003\u00111'/Z3\u0015\r\u0005=\u0011\u0011ZAg!\rQ\u0014\u0011\u0003\u0004\u0007\u0003'\u0001\u0001)!\u0006\u0003\u000bI\u000bgnZ3\u0014\u000f\u0005E!\"a\u0006\u0002\u001eA\u00191\"!\u0007\n\u0007\u0005maAA\u0004Qe>$Wo\u0019;\u0011\u0007-\ty\"C\u0002\u0002\"\u0019\u0011AbU3sS\u0006d\u0017N_1cY\u0016D1\"!\n\u0002\u0012\tU\r\u0011\"\u0001\u0002(\u0005\u0019\u0001o\\:\u0016\u0003eB!\"a\u000b\u0002\u0012\tE\t\u0015!\u0003:\u0003\u0011\u0001xn\u001d\u0011\t\u0015\r\f\tB!f\u0001\n\u0003\ty#F\u0001R\u0011)\t\u0019$!\u0005\u0003\u0012\u0003\u0006I!U\u0001\u0006iJ,W\r\t\u0005\t\u0003o\t\t\u0002\"\u0001\u0002:\u00051A(\u001b8jiz\"b!a\u0004\u0002<\u0005u\u0002bBA\u0013\u0003k\u0001\r!\u000f\u0005\u0007G\u0006U\u0002\u0019A)\t\u000f\u0005\u0005\u0013\u0011\u0003C\u0001\u007f\u00051\u0011n\u001d$sK\u0016D!\"!\u0012\u0002\u0012\u0005\u0005I\u0011AA$\u0003\u0011\u0019w\u000e]=\u0015\r\u0005=\u0011\u0011JA&\u0011%\t)#a\u0011\u0011\u0002\u0003\u0007\u0011\b\u0003\u0005d\u0003\u0007\u0002\n\u00111\u0001R\u0011)\ty%!\u0005\u0012\u0002\u0013\u0005\u0011\u0011K\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\t\u0019FK\u0002:\u0003+Z#!a\u0016\u0011\t\u0005e\u00131M\u0007\u0003\u00037RA!!\u0018\u0002`\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003C2\u0011AC1o]>$\u0018\r^5p]&!\u0011QMA.\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0005\u000b\u0003S\n\t\"%A\u0005\u0002\u0005-\u0014AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003[R3!UA+\u0011)\t\t(!\u0005\u0002\u0002\u0013\u0005\u00131O\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005U\u0004\u0003BA<\u0003\u0003k!!!\u001f\u000b\t\u0005m\u0014QP\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u0000\u0005!!.\u0019<b\u0013\u0011\t\u0019)!\u001f\u0003\rM#(/\u001b8h\u0011)\t9)!\u0005\u0002\u0002\u0013\u0005\u0011\u0011R\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002k\"Q\u0011QRA\t\u0003\u0003%\t!a$\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011\u0011SAL!\rY\u00111S\u0005\u0004\u0003+3!aA!os\"I\u0011\u0011TAF\u0003\u0003\u0005\r!^\u0001\u0004q\u0012\n\u0004BCAO\u0003#\t\t\u0011\"\u0011\u0002 \u0006y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\"B1\u00111UAU\u0003#k!!!*\u000b\u0007\u0005\u001df!\u0001\u0006d_2dWm\u0019;j_:LA!a+\u0002&\nA\u0011\n^3sCR|'\u000f\u0003\u0006\u00020\u0006E\u0011\u0011!C\u0001\u0003c\u000b\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004\u0001\u0006M\u0006BCAM\u0003[\u000b\t\u00111\u0001\u0002\u0012\"Q\u0011qWA\t\u0003\u0003%\t%!/\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012!\u001e\u0005\u000b\u0003{\u000b\t\"!A\u0005B\u0005}\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005U\u0004BCAb\u0003#\t\t\u0011\"\u0011\u0002F\u00061Q-];bYN$2\u0001QAd\u0011)\tI*!1\u0002\u0002\u0003\u0007\u0011\u0011\u0013\u0005\b\u0003\u0017\fI\u00011\u0001v\u0003\taw\u000eC\u0004\u0002P\u0006%\u0001\u0019A;\u0002\u0005!L\u0007BCAj\u0001!\u0015\r\u0011\"\u0003\u0002V\u00069Q.\u0019=Ge\u0016,WCAA\b\u0011)\tI\u000e\u0001E\u0001B\u0003&\u0011qB\u0001\t[\u0006DhI]3fA!9\u0011Q\u001c\u0001\u0005\n\u0005}\u0017!C7bs\n,gI]3f)\u0019\t\t/a;\u0002nB1\u00111]Au\u0003\u001fi!!!:\u000b\t\u0005\u001d\u0018QU\u0001\nS6lW\u000f^1cY\u0016L1aTAs\u0011\u001d\tY-a7A\u0002UDq!a4\u0002\\\u0002\u0007Q\u000fC\u0004\u0002r\u0002!I!a=\u0002\r%t7/\u001a:u)!\t)0a>\u0002|\u0006}\b\u0003B&O\u0003\u001fA\u0001\"!?\u0002p\u0002\u0007\u0011Q_\u0001\u0003eNDq!!@\u0002p\u0002\u0007\u0011+A\u0001u\u0011!\u0011\t!a<A\u0002\t\r\u0011aC2p]\u001ad\u0017n\u0019;j]\u001e\u0004RA!\u0002\u0003\fEk!Aa\u0002\u000b\t\t%\u0011QU\u0001\b[V$\u0018M\u00197f\u0013\u0011\u0011iAa\u0002\u0003\u00151K7\u000f\u001e\"vM\u001a,'\u000fC\u0004\u0003\u0012\u0001!IAa\u0005\u0002\u000fI,\u0007\u000f\\1dKR9!J!\u0006\u0003\u001a\tm\u0001b\u0002B\f\u0005\u001f\u0001\rAS\u0001\u0003iNDq!!@\u0003\u0010\u0001\u0007\u0011\u000bC\u0004\u0003\u001e\t=\u0001\u0019\u0001&\u0002\u0017I,\u0007\u000f\\1dK6,g\u000e\u001e\u0005\b\u0005C\u0001A\u0011\u0001B\u0012\u0003=1\u0017N\u001c3Pm\u0016\u0014H.\u00199qS:<G\u0003\u0002B\u0013\u0005[\u0001Ba\u0013(\u0003(A)1B!\u000bR#&\u0019!1\u0006\u0004\u0003\rQ+\b\u000f\\33\u0011\u001d\u0011yCa\bA\u0002)\u000b1a\u0019;t\u0011\u001d\u0011\u0019\u0004\u0001C\u0005\u0005k\tab]3u\u0007\"LG\u000e\u001a:f]B{7\u000fF\u0003\u0017\u0005o\u0011I\u0004C\u0004\u0002&\tE\u0002\u0019A\u001d\t\r%\u0013\t\u00041\u0001K\r\u0019\u0011i\u0004\u0001\u0001\u0003@\t\tb+\u00197jI\u0006$X-\u0012=dKB$\u0018n\u001c8\u0014\t\tm\"\u0011\t\t\u0004\u0017\n\r\u0013b\u0001B#!\nIQ\t_2faRLwN\u001c\u0005\f\u0005\u0013\u0012YD!A!\u0002\u0013\u0011Y%A\u0002ng\u001e\u0004BA!\u0014\u0003T9\u00191Ba\u0014\n\u0007\tEc!\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003\u0007\u0013)FC\u0002\u0003R\u0019A\u0001\"a\u000e\u0003<\u0011\u0005!\u0011\f\u000b\u0005\u00057\u0012i\u0006E\u0002;\u0005wA\u0001B!\u0013\u0003X\u0001\u0007!1\n\u0004\u0007\u0005C\u0002\u0001Aa\u0019\u0003\u000f1{7-\u0019;peN!!q\fB3!\rQ$qM\u0005\u0005\u0005S\u0012YGA\u0005Ue\u00064XM]:fe&\u0011Q\u000b\u0005\u0005\u000b\u0003K\u0011yF!A!\u0002\u0013I\u0004\u0002CA\u001c\u0005?\"\tA!\u001d\u0015\t\tM$Q\u000f\t\u0004u\t}\u0003bBA\u0013\u0005_\u0002\r!\u000f\u0005\r\u0005s\u0012y\u00061AA\u0002\u0013\u0005\u0011qF\u0001\u0005Y\u0006\u001cH\u000f\u0003\u0007\u0003~\t}\u0003\u0019!a\u0001\n\u0003\u0011y(\u0001\u0005mCN$x\fJ3r)\r1\"\u0011\u0011\u0005\n\u00033\u0013Y(!AA\u0002EC\u0001B!\"\u0003`\u0001\u0006K!U\u0001\u0006Y\u0006\u001cH\u000f\t\u0005\t\u0005\u0013\u0013y\u0006\"\u0001\u0003\f\u0006AAn\\2bi\u0016Le\u000eF\u0002R\u0005\u001bCqAa$\u0003\b\u0002\u0007\u0011+\u0001\u0003s_>$\b\u0002\u0003BJ\u0005?\"\tB!&\u0002\u0015%\u001cX\t\\5hS\ndW\rF\u0002A\u0005/Cq!!@\u0003\u0012\u0002\u0007\u0011\u000b\u0003\u0005\u0003\u001c\n}C\u0011\tBO\u0003!!(/\u0019<feN,Gc\u0001\f\u0003 \"9\u0011Q BM\u0001\u0004\tv!\u0003BR\u0001\u0005\u0005\t\u0012\u0001BS\u0003\u0015\u0011\u0016M\\4f!\rQ$q\u0015\u0004\n\u0003'\u0001\u0011\u0011!E\u0001\u0005S\u001bbAa*\u0003,\u0006u\u0001\u0003\u0003BW\u0005gK\u0014+a\u0004\u000e\u0005\t=&b\u0001BY\r\u00059!/\u001e8uS6,\u0017\u0002\u0002B[\u0005_\u0013\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83\u0011!\t9Da*\u0005\u0002\teFC\u0001BS\u0011)\tiLa*\u0002\u0002\u0013\u0015\u0013q\u0018\u0005\u000b\u0005\u007f\u00139+!A\u0005\u0002\n\u0005\u0017!B1qa2LHCBA\b\u0005\u0007\u0014)\rC\u0004\u0002&\tu\u0006\u0019A\u001d\t\r\r\u0014i\f1\u0001R\u0011)\u0011IMa*\u0002\u0002\u0013\u0005%1Z\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011iM!6\u0011\u000b-\u0011yMa5\n\u0007\tEgA\u0001\u0004PaRLwN\u001c\t\u0006\u0017\t%\u0012(\u0015\u0005\u000b\u0005/\u00149-!AA\u0002\u0005=\u0011a\u0001=%a\u00191!1\u001c\u0001\u0001\u0005;\u0014A\u0002V=qK\u0012dunY1u_J\u001cBA!7\u0003t!Q\u0011Q\u0005Bm\u0005\u0003\u0005\u000b\u0011B\u001d\t\u0011\u0005]\"\u0011\u001cC\u0001\u0005G$BA!:\u0003hB\u0019!H!7\t\u000f\u0005\u0015\"\u0011\u001da\u0001s!A!1\u0013Bm\t#\u0012Y\u000fF\u0002A\u0005[Dq!!@\u0003j\u0002\u0007\u0011KB\u0005\u0003r\u0002\u0001\n1%\u0001\u0003t\nY\u0001k\\:BgNLwM\\3s'\u0011\u0011yO!\u001a\t\u0015\u0005\u0015\"q\u001ea\u0001\u000e\u0003\t9\u0003\u0003\u0006\u0003z\n=\b\u0019!D\u0001\u0005w\fq\u0001]8t?\u0012*\u0017\u000fF\u0002\u0017\u0005{D\u0011\"!'\u0003x\u0006\u0005\t\u0019A\u001d\t\u0015\r\u0005\u0001\u0001#b!\n#\u0019\u0019!A\u0006q_N\f5o]5h]\u0016\u0014XCAB\u0003!\rQ$q\u001e\u0005\u000b\u0007\u0013\u0001\u0001\u0012!Q!\n\r\u0015\u0011\u0001\u00049pg\u0006\u001b8/[4oKJ\u0004cABB\u0007\u0001!\u0019yA\u0001\nEK\u001a\fW\u000f\u001c;Q_N\f5o]5h]\u0016\u00148CBB\u0006\u0005K\u001a)\u0001\u0003\u0005\u00028\r-A\u0011AB\n)\t\u0019)\u0002E\u0002;\u0007\u0017AA\"!\n\u0004\f\u0001\u0007\t\u0019!C\u0001\u0003OAAB!?\u0004\f\u0001\u0007\t\u0019!C\u0001\u00077!2AFB\u000f\u0011%\tIj!\u0007\u0002\u0002\u0003\u0007\u0011\b\u0003\u0005\u0002,\r-\u0001\u0015)\u0003:\u0011!\u0011Yja\u0003\u0005B\r\rBc\u0001\f\u0004&!9\u0011Q`B\u0011\u0001\u0004\t\u0006bBB\u0015\u0001\u0011\u000511F\u0001\u0006CR\u0004vn]\u000b\u0005\u0007[\u0019)\u0004\u0006\u0003\u00040\r\rC\u0003BB\u0019\u0007\u0003\u0002Baa\r\u000461\u0001A\u0001CB\u001c\u0007O\u0011\ra!\u000f\u0003\u0003Q\u000b2aa\u000fR!\rY1QH\u0005\u0004\u0007\u007f1!a\u0002(pi\"Lgn\u001a\u0005\bG\u000e\u001d\u0002\u0019AB\u0019\u0011\u001d\t)ca\nA\u0002e\u0002Baa\u0012\u0004J5\t!!C\u0002\u0004L\t\u00111bU=nE>dG+\u00192mK\u0002")
public interface Positions
extends scala.reflect.api.Positions {
    public void scala$reflect$internal$Positions$_setter_$NoPosition_$eq(NoPosition$ var1);

    public void scala$reflect$internal$Positions$_setter_$PositionTag_$eq(ClassTag var1);

    @Override
    public NoPosition$ NoPosition();

    public ClassTag<Position> PositionTag();

    public boolean useOffsetPositions();

    public Position wrappingPos(Position var1, List<Trees.Tree> var2);

    public Position wrappingPos(Position var1, List<Trees.Tree> var2, boolean var3);

    public Position wrappingPos(List<Trees.Tree> var1);

    public void ensureNonOverlapping(Trees.Tree var1, List<Trees.Tree> var2);

    public void ensureNonOverlapping(Trees.Tree var1, List<Trees.Tree> var2, boolean var3);

    public Position rangePos(SourceFile var1, int var2, int var3, int var4);

    public void validatePositions(Trees.Tree var1);

    public List<Trees.Tree> solidDescendants(Trees.Tree var1);

    public Range scala$reflect$internal$Positions$$maxFree();

    public List<Tuple2<Trees.Tree, Trees.Tree>> findOverlapping(List<Trees.Tree> var1);

    public Positions$Range$ Range();

    public PosAssigner posAssigner();

    public <T extends Trees.Tree> T atPos(Position var1, T var2);

    public class Range
    implements Product,
    Serializable {
        private final Position pos;
        private final Trees.Tree tree;
        public final /* synthetic */ SymbolTable $outer;

        public Position pos() {
            return this.pos;
        }

        public Trees.Tree tree() {
            return this.tree;
        }

        public boolean isFree() {
            Trees.Tree tree = this.tree();
            Trees$EmptyTree$ trees$EmptyTree$ = this.scala$reflect$internal$Positions$Range$$$outer().EmptyTree();
            return !(tree != null ? !((Object)tree).equals(trees$EmptyTree$) : trees$EmptyTree$ != null);
        }

        public Range copy(Position pos, Trees.Tree tree) {
            return new Range(this.scala$reflect$internal$Positions$Range$$$outer(), pos, tree);
        }

        public Position copy$default$1() {
            return this.pos();
        }

        public Trees.Tree copy$default$2() {
            return this.tree();
        }

        @Override
        public String productPrefix() {
            return "Range";
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
                    object = this.tree();
                    break;
                }
                case 0: {
                    object = this.pos();
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
            return x$1 instanceof Range;
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
            if (!(x$1 instanceof Range)) return false;
            if (((Range)x$1).scala$reflect$internal$Positions$Range$$$outer() != this.scala$reflect$internal$Positions$Range$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            Range range2 = (Range)x$1;
            Position position = this.pos();
            Position position2 = range2.pos();
            if (position == null) {
                if (position2 != null) {
                    return false;
                }
            } else if (!position.equals(position2)) return false;
            Trees.Tree tree = this.tree();
            Trees.Tree tree2 = range2.tree();
            if (tree == null) {
                if (tree2 != null) {
                    return false;
                }
            } else if (!((Object)tree).equals(tree2)) return false;
            if (!range2.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Positions$Range$$$outer() {
            return this.$outer;
        }

        public Range(SymbolTable $outer, Position pos, Trees.Tree tree) {
            this.pos = pos;
            this.tree = tree;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Product$class.$init$(this);
        }
    }

    public class Locator
    extends Trees.Traverser {
        private final Position pos;
        private Trees.Tree last;
        public final /* synthetic */ SymbolTable $outer;

        public Trees.Tree last() {
            return this.last;
        }

        public void last_$eq(Trees.Tree x$1) {
            this.last = x$1;
        }

        public Trees.Tree locateIn(Trees.Tree root) {
            this.last_$eq(this.scala$reflect$internal$Positions$Locator$$$outer().EmptyTree());
            this.traverse(root);
            return this.last();
        }

        public boolean isEligible(Trees.Tree t) {
            return !t.pos().isTransparent();
        }

        public void traverse(Trees.Tree t) {
            block2: {
                block3: {
                    block1: {
                        Trees.TypeTree typeTree;
                        if (!(t instanceof Trees.TypeTree) || (typeTree = (Trees.TypeTree)t).original() == null || !typeTree.pos().includes(typeTree.original().pos())) break block1;
                        this.traverse(typeTree.original());
                        break block2;
                    }
                    if (!t.pos().includes(this.pos)) break block3;
                    if (this.isEligible(t)) {
                        this.last_$eq(t);
                    }
                    super.traverse(t);
                    break block2;
                }
                if (!(t instanceof Trees.MemberDef)) break block2;
                Trees.MemberDef memberDef = (Trees.MemberDef)t;
                List<Trees.TreeApi> list2 = memberDef.mods().annotations();
                List<Trees.TreeApi> list3 = ((Object)Nil$.MODULE$).equals(list2) && memberDef.symbol() != null ? memberDef.symbol().annotations().map(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final Trees.Tree apply(AnnotationInfos.AnnotationInfo x$13) {
                        return x$13.original();
                    }
                }, List$.MODULE$.canBuildFrom()) : list2;
                this.traverseTrees(list3);
            }
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Positions$Locator$$$outer() {
            return this.$outer;
        }

        public Locator(SymbolTable $outer, Position pos) {
            this.pos = pos;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            super($outer);
        }
    }

    public interface PosAssigner {
        public Position pos();

        public void pos_$eq(Position var1);
    }

    public class TypedLocator
    extends Locator {
        @Override
        public boolean isEligible(Trees.Tree t) {
            return super.isEligible(t) && t.tpe() != null;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Positions$TypedLocator$$$outer() {
            return this.$outer;
        }

        public TypedLocator(SymbolTable $outer, Position pos) {
            super($outer, pos);
        }
    }

    public static class ValidateException
    extends Exception {
        public final /* synthetic */ SymbolTable $outer;

        public /* synthetic */ SymbolTable scala$reflect$internal$Positions$ValidateException$$$outer() {
            return this.$outer;
        }

        public ValidateException(SymbolTable $outer, String msg) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            super(msg);
        }
    }

    public static class DefaultPosAssigner
    extends Trees.Traverser
    implements PosAssigner {
        private Position pos;
        public final /* synthetic */ SymbolTable $outer;

        @Override
        public Position pos() {
            return this.pos;
        }

        @Override
        public void pos_$eq(Position x$1) {
            this.pos = x$1;
        }

        public void traverse(Trees.Tree t) {
            if (t.canHaveAttrs()) {
                Position position = t.pos();
                NoPosition$ noPosition$ = this.scala$reflect$internal$Positions$DefaultPosAssigner$$$outer().NoPosition();
                if (!(position != null ? !position.equals(noPosition$) : noPosition$ != null)) {
                    t.setPos(this.pos());
                    super.traverse(t);
                }
            }
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Positions$DefaultPosAssigner$$$outer() {
            return this.$outer;
        }

        public DefaultPosAssigner(SymbolTable $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            super($outer);
        }
    }
}

