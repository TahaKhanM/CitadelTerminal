/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.tpe;

import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Predef$;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.generic.Clearable;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Names;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$NoType$;
import scala.reflect.internal.Variance;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.tpe.TypeConstraints$TypeConstraint$;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\r\u001de!C\u0001\u0003!\u0003\r\t\u0001\u0002\u0006S\u0005=!\u0016\u0010]3D_:\u001cHO]1j]R\u001c(BA\u0002\u0005\u0003\r!\b/\u001a\u0006\u0003\u000b\u0019\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u000f!\tqA]3gY\u0016\u001cGOC\u0001\n\u0003\u0015\u00198-\u00197b'\t\u00011\u0002\u0005\u0002\r\u001b5\t\u0001\"\u0003\u0002\u000f\u0011\t1\u0011I\\=SK\u001aDQ\u0001\u0005\u0001\u0005\u0002I\ta\u0001J5oSR$3\u0001\u0001\u000b\u0002'A\u0011A\u0002F\u0005\u0003+!\u0011A!\u00168ji\"Aq\u0003\u0001EC\u0002\u0013%\u0001$\u0001\u0005`k:$w\u000eT8h+\u0005I\u0002C\u0001\u000e\u001c\u001b\u0005\u0001a\u0001\u0002\u000f\u0001\u0001u\u0011q!\u00168e_2{wmE\u0002\u001c\u0017y\u0001\"a\b\u0013\u000e\u0003\u0001R!!\t\u0012\u0002\u000f\u001d,g.\u001a:jG*\u00111\u0005C\u0001\u000bG>dG.Z2uS>t\u0017BA\u0013!\u0005%\u0019E.Z1sC\ndW\rC\u0003(7\u0011\u0005\u0001&\u0001\u0004=S:LGO\u0010\u000b\u00023\u0015!!f\u0007\u0001,\u0005%)f\u000eZ8QC&\u00148\u000fE\u0002-_Ir!\u0001D\u0017\n\u00059B\u0011a\u00029bG.\fw-Z\u0005\u0003aE\u0012A\u0001T5ti*\u0011a\u0006\u0003\t\u0006gq\u0012y.\u001f\b\u0003iUj\u0011AA\u0004\u0007m\tA\t\u0001B\u001c\u0002\u001fQK\b/Z\"p]N$(/Y5oiN\u0004\"\u0001\u000e\u001d\u0007\r\u0005\u0011\u0001\u0012\u0001\u0003:'\tA4\u0002C\u0003(q\u0011\u00051\bF\u00018\r\u0011i\u0004H\u0011 \u0003\u0011UsGm\u001c)bSJ,2aP&`'\u0011a4\u0002Q\"\u0011\u00051\t\u0015B\u0001\"\t\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\u0004#\n\u0005\u0015C!\u0001D*fe&\fG.\u001b>bE2,\u0007\u0002C$=\u0005+\u0007I\u0011\u0001%\u0002\u0005Q4X#A%\u0011\u0005)[E\u0002\u0001\u0003\u0006\u0019r\u0012\r!\u0014\u0002\b)f\u0004XMV1s#\tq\u0015\u000b\u0005\u0002\r\u001f&\u0011\u0001\u000b\u0003\u0002\b\u001d>$\b.\u001b8h!\t\u0011f\u000b\u0005\u0002T)6\tA!\u0003\u0002V\t\tY1+_7c_2$\u0016M\u00197f\u0013\tau+\u0003\u0002Y\t\t)A+\u001f9fg\"A!\f\u0010B\tB\u0003%\u0011*A\u0002um\u0002B\u0001\u0002\u0018\u001f\u0003\u0016\u0004%\t!X\u0001\fi\u000e{gn\u001d;sC&tG/F\u0001_!\tQu\fB\u0003ay\t\u0007\u0011M\u0001\bUsB,7i\u001c8tiJ\f\u0017N\u001c;\u0012\u00059\u0013\u0007CA2e!\t!\u0004A\u0002\u0003a\u0001\u0001)7C\u00013\f\u0011!9GM!A!\u0002\u0013A\u0017a\u00017paA\u0019AfL5\u0011\u0005iQ\u0017BA6X\u0005\u0011!\u0016\u0010]3\t\u00115$'\u0011!Q\u0001\n!\f1\u0001[51\u0011!yGM!A!\u0002\u0013I\u0017A\u00028v[2|\u0007\u0007\u0003\u0005rI\n\u0005\t\u0015!\u0003j\u0003\u0019qW/\u001c5ja!A1\u000f\u001aB\u0001B\u0003%A/\u0001\bbm>LGmV5eK:Lgn\u001a\u0019\u0011\u00051)\u0018B\u0001<\t\u0005\u001d\u0011un\u001c7fC:DQa\n3\u0005\u0002a$b!\u001f>|yvt\bC\u0001\u000ee\u0011\u00159w\u000f1\u0001i\u0011\u0015iw\u000f1\u0001i\u0011\u0015yw\u000f1\u0001j\u0011\u0015\tx\u000f1\u0001j\u0011\u001d\u0019x\u000f%AA\u0002QDaa\n3\u0005\u0002\u0005\u0005A#B=\u0002\u0004\u0005\u0015\u0001\"B4\u0000\u0001\u0004A\u0007\"B7\u0000\u0001\u0004A\u0007BB\u0014e\t\u0003\tI\u0001F\u0002z\u0003\u0017A\u0001\"!\u0004\u0002\b\u0001\u0007\u0011qB\u0001\u0007E>,h\u000eZ:\u0011\u0007i\t\t\"C\u0002\u0002\u0014]\u0013!\u0002V=qK\n{WO\u001c3t\u0011\u00199C\r\"\u0001\u0002\u0018Q\t\u0011\u0010C\u0005\u0002\u001c\u0011\u0004\r\u0011\"\u0003\u0002\u001e\u0005AAn\u001c2pk:$7/\u0006\u0002\u0002 A)\u0011\u0011EA\u0014S6\u0011\u00111\u0005\u0006\u0004\u0003K\u0011\u0013!C5n[V$\u0018M\u00197f\u0013\r\u0001\u00141\u0005\u0005\n\u0003W!\u0007\u0019!C\u0005\u0003[\tA\u0002\\8c_VtGm]0%KF$2aEA\u0018\u0011)\t\t$!\u000b\u0002\u0002\u0003\u0007\u0011qD\u0001\u0004q\u0012\n\u0004\u0002CA\u001bI\u0002\u0006K!a\b\u0002\u00131|'m\\;oIN\u0004\u0003\"CA\u001dI\u0002\u0007I\u0011BA\u000f\u0003!A\u0017NY8v]\u0012\u001c\b\"CA\u001fI\u0002\u0007I\u0011BA \u00031A\u0017NY8v]\u0012\u001cx\fJ3r)\r\u0019\u0012\u0011\t\u0005\u000b\u0003c\tY$!AA\u0002\u0005}\u0001\u0002CA#I\u0002\u0006K!a\b\u0002\u0013!L'm\\;oIN\u0004\u0003\"CA%I\u0002\u0007I\u0011BA&\u0003\u0015qW/\u001c7p+\u0005I\u0007\"CA(I\u0002\u0007I\u0011BA)\u0003%qW/\u001c7p?\u0012*\u0017\u000fF\u0002\u0014\u0003'B\u0011\"!\r\u0002N\u0005\u0005\t\u0019A5\t\u000f\u0005]C\r)Q\u0005S\u00061a.^7m_\u0002B\u0011\"a\u0017e\u0001\u0004%I!a\u0013\u0002\u000b9,X\u000e[5\t\u0013\u0005}C\r1A\u0005\n\u0005\u0005\u0014!\u00038v[\"Lw\fJ3r)\r\u0019\u00121\r\u0005\n\u0003c\ti&!AA\u0002%Dq!a\u001aeA\u0003&\u0011.\u0001\u0004ok6D\u0017\u000e\t\u0005\n\u0003W\"\u0007\u0019!C\u0005\u0003[\nQ\"\u0019<pS\u0012<\u0016\u000eZ3oS:<W#\u0001;\t\u0013\u0005ED\r1A\u0005\n\u0005M\u0014!E1w_&$w+\u001b3f]&twm\u0018\u0013fcR\u00191#!\u001e\t\u0013\u0005E\u0012qNA\u0001\u0002\u0004!\bbBA=I\u0002\u0006K\u0001^\u0001\u000fCZ|\u0017\u000eZ,jI\u0016t\u0017N\\4!\u0011\u001d\ti\b\u001aC\u0001\u0003\u007f\n\u0001\u0002\\8C_VtGm]\u000b\u0002Q\"9\u00111\u00113\u0005\u0002\u0005}\u0014\u0001\u00035j\u0005>,h\u000eZ:\t\u000f\u0005\u001dE\r\"\u0001\u0002n\u0005Q\u0011M^8jI^KG-\u001a8\t\u000f\u0005-E\r\"\u0001\u0002\u000e\u0006Q\u0011\r\u001a3M_\n{WO\u001c3\u0015\u000bM\ty)a%\t\u000f\u0005E\u0015\u0011\u0012a\u0001S\u0006\u0011A\u000f\u001d\u0005\n\u0003+\u000bI\t%AA\u0002Q\fa\"[:Ok6,'/[2C_VtG\rC\u0004\u0002\u001a\u0012$\t!a'\u0002\u001b\rDWmY6XS\u0012,g.\u001b8h)\r\u0019\u0012Q\u0014\u0005\b\u0003#\u000b9\n1\u0001j\u0011\u001d\t\t\u000b\u001aC\u0001\u0003G\u000b!\"\u00193e\u0011&\u0014u.\u001e8e)\u0015\u0019\u0012QUAT\u0011\u001d\t\t*a(A\u0002%D\u0011\"!&\u0002 B\u0005\t\u0019\u0001;\t\u000f\u0005-F\r\"\u0001\u0002n\u0005\u0001\u0012N\\:u/&$\b.\u001b8C_VtGm\u001d\u0005\b\u0003_#G\u0011AAY\u00039I7oV5uQ&t'i\\;oIN$2\u0001^AZ\u0011\u001d\t\t*!,A\u0002%D\u0011\"a.e\u0001\u0004%\t!a\u0013\u0002\t%t7\u000f\u001e\u0005\n\u0003w#\u0007\u0019!C\u0001\u0003{\u000b\u0001\"\u001b8ti~#S-\u001d\u000b\u0004'\u0005}\u0006\"CA\u0019\u0003s\u000b\t\u00111\u0001j\u0011\u001d\t\u0019\r\u001aQ!\n%\fQ!\u001b8ti\u0002Bq!a2e\t\u0003\ti'A\u0005j]N$h+\u00197jI\"9\u00111\u001a3\u0005\u0002\u00055\u0017!D2m_:,\u0017J\u001c;fe:\fG.F\u0001z\u0011\u001d\t\t\u000e\u001aC!\u0003'\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003+\u0004B!a6\u0002b6\u0011\u0011\u0011\u001c\u0006\u0005\u00037\fi.\u0001\u0003mC:<'BAAp\u0003\u0011Q\u0017M^1\n\t\u0005\r\u0018\u0011\u001c\u0002\u0007'R\u0014\u0018N\\4\t\u0013\u0005\u001dH-%A\u0005\u0002\u0005%\u0018\u0001F1eI2{'i\\;oI\u0012\"WMZ1vYR$#'\u0006\u0002\u0002l*\u001aA/!<,\u0005\u0005=\b\u0003BAy\u0003wl!!a=\u000b\t\u0005U\u0018q_\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!?\t\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003{\f\u0019PA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016D\u0011B!\u0001e#\u0003%\t!!;\u0002)\u0005$G\rS5C_VtG\r\n3fM\u0006,H\u000e\u001e\u00133\u0011%\u0011)\u0001\u0010B\tB\u0003%a,\u0001\u0007u\u0007>t7\u000f\u001e:bS:$\b\u0005\u0003\u0004(y\u0011\u0005!\u0011\u0002\u000b\u0007\u0005\u0017\u0011yA!\u0005\u0011\u000b\t5A(\u00130\u000e\u0003aBaa\u0012B\u0004\u0001\u0004I\u0005B\u0002/\u0003\b\u0001\u0007a\fC\u0005\u0003\u0016q\n\t\u0011\"\u0001\u0003\u0018\u0005!1m\u001c9z+\u0019\u0011IBa\b\u0003$Q1!1\u0004B\u0013\u0005O\u0001rA!\u0004=\u0005;\u0011\t\u0003E\u0002K\u0005?!a\u0001\u0014B\n\u0005\u0004i\u0005c\u0001&\u0003$\u00111\u0001Ma\u0005C\u0002\u0005D\u0011b\u0012B\n!\u0003\u0005\rA!\b\t\u0013q\u0013\u0019\u0002%AA\u0002\t\u0005\u0002\"\u0003B\u0016yE\u0005I\u0011\u0001B\u0017\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*bAa\f\u00034\tURC\u0001B\u0019U\rI\u0015Q\u001e\u0003\u0007\u0019\n%\"\u0019A'\u0005\r\u0001\u0014IC1\u0001b\u0011%\u0011I\u0004PI\u0001\n\u0003\u0011Y$\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\r\tu\"\u0011\tB\"+\t\u0011yDK\u0002_\u0003[$a\u0001\u0014B\u001c\u0005\u0004iEA\u00021\u00038\t\u0007\u0011\rC\u0005\u0003Hq\n\t\u0011\"\u0011\u0003J\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!!6\t\u0013\t5C(!A\u0005\u0002\t=\u0013\u0001\u00049s_\u0012,8\r^!sSRLXC\u0001B)!\ra!1K\u0005\u0004\u0005+B!aA%oi\"I!\u0011\f\u001f\u0002\u0002\u0013\u0005!1L\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\u0011iFa\u0019\u0011\u00071\u0011y&C\u0002\u0003b!\u00111!\u00118z\u0011)\t\tDa\u0016\u0002\u0002\u0003\u0007!\u0011\u000b\u0005\n\u0005Ob\u0014\u0011!C!\u0005S\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0005W\u0002bA!\u001c\u0003p\tuS\"\u0001\u0012\n\u0007\tE$E\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0011%\u0011)\bPA\u0001\n\u0003\u00119(\u0001\u0005dC:,\u0015/^1m)\r!(\u0011\u0010\u0005\u000b\u0003c\u0011\u0019(!AA\u0002\tu\u0003\"\u0003B?y\u0005\u0005I\u0011\tB@\u0003!A\u0017m\u001d5D_\u0012,GC\u0001B)\u0011%\t\t\u000ePA\u0001\n\u0003\n\u0019\u000eC\u0005\u0003\u0006r\n\t\u0011\"\u0011\u0003\b\u00061Q-];bYN$2\u0001\u001eBE\u0011)\t\tDa!\u0002\u0002\u0003\u0007!QL\u0004\n\u0005\u001bC\u0014\u0011!E\u0001\u0005\u001f\u000b\u0001\"\u00168e_B\u000b\u0017N\u001d\t\u0005\u0005\u001b\u0011\tJ\u0002\u0005>q\u0005\u0005\t\u0012\u0001BJ'\u0011\u0011\tjC\"\t\u000f\u001d\u0012\t\n\"\u0001\u0003\u0018R\u0011!q\u0012\u0005\u000b\u0003#\u0014\t*!A\u0005F\u0005M\u0007B\u0003BO\u0005#\u000b\t\u0011\"!\u0003 \u0006)\u0011\r\u001d9msV1!\u0011\u0015BT\u0005W#bAa)\u0003.\n=\u0006c\u0002B\u0007y\t\u0015&\u0011\u0016\t\u0004\u0015\n\u001dFA\u0002'\u0003\u001c\n\u0007Q\nE\u0002K\u0005W#a\u0001\u0019BN\u0005\u0004\t\u0007bB$\u0003\u001c\u0002\u0007!Q\u0015\u0005\b9\nm\u0005\u0019\u0001BU\u0011)\u0011\u0019L!%\u0002\u0002\u0013\u0005%QW\u0001\bk:\f\u0007\u000f\u001d7z+\u0019\u00119La2\u0003LR!!\u0011\u0018Bg!\u0015a!1\u0018B`\u0013\r\u0011i\f\u0003\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000f1\u0011\tM!2\u0003J&\u0019!1\u0019\u0005\u0003\rQ+\b\u000f\\33!\rQ%q\u0019\u0003\u0007\u0019\nE&\u0019A'\u0011\u0007)\u0013Y\r\u0002\u0004a\u0005c\u0013\r!\u0019\u0005\u000b\u0005\u001f\u0014\t,!AA\u0002\tE\u0017a\u0001=%aA9!Q\u0002\u001f\u0003F\n%\u0007B\u0003Bk\u0005#\u000b\t\u0011\"\u0003\u0003X\u0006Y!/Z1e%\u0016\u001cx\u000e\u001c<f)\t\u0011I\u000e\u0005\u0003\u0002X\nm\u0017\u0002\u0002Bo\u00033\u0014aa\u00142kK\u000e$\bC\u0001\u000eW\u0011%\u0011\u0019o\u0007a\u0001\n\u0003\u0011)/A\u0002m_\u001e,\"Aa:\u0011\u0007\t%\u0018&D\u0001\u001c\u0011%\u0011io\u0007a\u0001\n\u0003\u0011y/A\u0004m_\u001e|F%Z9\u0015\u0007M\u0011\t\u0010\u0003\u0006\u00022\t-\u0018\u0011!a\u0001\u0005OD\u0001B!>\u001cA\u0003&!q]\u0001\u0005Y><\u0007\u0005C\u0004\u0003zn!\tAa?\u0002\rUtGm\u001c+p)\r\u0019\"Q \u0005\t\u0005\u007f\u00149\u00101\u0001\u0003h\u0006)A.[7ji\"A11A\u000e\u0005\u0002\u0019\u0019)!\u0001\u0004sK\u000e|'\u000f\u001a\u000b\u0004'\r\u001d\u0001bB$\u0004\u0002\u0001\u0007!q\u001c\u0005\u0007\u0007\u0017YB\u0011\u0001\n\u0002\u000b\rdW-\u0019:\t\u000f\r=1\u0004\"\u0001\u0004\u0012\u0005!QO\u001c3p+\u0011\u0019\u0019ba\u0006\u0015\t\rU1Q\u0004\t\u0004\u0015\u000e]A\u0001CB\r\u0007\u001b\u0011\raa\u0007\u0003\u0003Q\u000b2A\u0014B/\u0011%\u0019yb!\u0004\u0005\u0002\u0004\u0019\t#A\u0003cY>\u001c7\u000eE\u0003\r\u0007G\u0019)\"C\u0002\u0004&!\u0011\u0001\u0002\u00102z]\u0006lWM\u0010\u0005\n\u0007S\u0001\u0001\u0012!Q!\ne\t\u0011bX;oI>dun\u001a\u0011\t\r\r5\u0002\u0001\"\u0001\u0019\u0003\u001d)h\u000eZ8M_\u001eD!b!\r\u0001\u0011\u000b\u0007I\u0011BA&\u00039qW/\\3sS\u000eduNQ8v]\u0012D\u0011b!\u000e\u0001\u0011\u0003\u0005\u000b\u0015B5\u0002\u001f9,X.\u001a:jG2{'i\\;oI\u0002B!b!\u000f\u0001\u0011\u000b\u0007I\u0011BA&\u00039qW/\\3sS\u000eD\u0015NQ8v]\u0012D\u0011b!\u0010\u0001\u0011\u0003\u0005\u000b\u0015B5\u0002\u001f9,X.\u001a:jG\"K'i\\;oI\u0002:\u0011b!\u0011\u0001\u0003\u0003E\taa\u0011\u0002\u001dQK\b/Z\"p]N$(/Y5oiB\u0019!d!\u0012\u0007\u0011\u0001\u0004\u0011\u0011!E\u0001\u0007\u000f\u001a2a!\u0012\f\u0011\u001d93Q\tC\u0001\u0007\u0017\"\"aa\u0011\t\u0015\r=3QII\u0001\n\u0003\tI/A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%\u000e\u0005\b\u0007'\u0002A\u0011AB+\u0003\u0015\u0019x\u000e\u001c<f)-!8qKB/\u0007[\u001aIh! \t\u0011\re3\u0011\u000ba\u0001\u00077\nQ\u0001\u001e<beN\u0004B\u0001L\u0018\u0003`\"A1qLB)\u0001\u0004\u0019\t'A\u0004ua\u0006\u0014\u0018-\\:\u0011\t1z31\r\t\u00045\r\u0015\u0014\u0002BB4\u0007S\u0012aaU=nE>d\u0017bAB6\t\t91+_7c_2\u001c\b\u0002CB8\u0007#\u0002\ra!\u001d\u0002\u0013Y\f'/[1oG\u0016\u001c\b\u0003\u0002\u00170\u0007g\u00022aUB;\u0013\r\u00199\b\u0002\u0002\t-\u0006\u0014\u0018.\u00198dK\"911PB)\u0001\u0004!\u0018!B;qa\u0016\u0014\b\u0002CB@\u0007#\u0002\ra!!\u0002\u000b\u0011,\u0007\u000f\u001e5\u0011\u0007M\u001b\u0019)C\u0002\u0004\u0006\u0012\u0011Q\u0001R3qi\"\u0004")
public interface TypeConstraints {
    public UndoLog scala$reflect$internal$tpe$TypeConstraints$$_undoLog();

    public UndoLog undoLog();

    public Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericLoBound();

    public Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericHiBound();

    public TypeConstraints$TypeConstraint$ TypeConstraint();

    public boolean solve(List<Types.TypeVar> var1, List<Symbols.Symbol> var2, List<Variance> var3, boolean var4, int var5);

    public static class UndoLog
    implements Clearable {
        private List<UndoPair<Types.TypeVar, TypeConstraint>> log;
        public final /* synthetic */ SymbolTable $outer;

        public List<UndoPair<Types.TypeVar, TypeConstraint>> log() {
            return this.log;
        }

        public void log_$eq(List<UndoPair<Types.TypeVar, TypeConstraint>> x$1) {
            this.log = x$1;
        }

        public void undoTo(List<UndoPair<Types.TypeVar, TypeConstraint>> limit) {
            this.scala$reflect$internal$tpe$TypeConstraints$UndoLog$$$outer().assertCorrectThread();
            while (this.log() != limit && this.log().nonEmpty()) {
                UndoPair<Types.TypeVar, TypeConstraint> undoPair = this.log().head();
                if (undoPair != null) {
                    Tuple2<Types.TypeVar, TypeConstraint> tuple2 = new Tuple2<Types.TypeVar, TypeConstraint>(undoPair.tv(), undoPair.tConstraint());
                    Types.TypeVar tv = tuple2._1();
                    TypeConstraint constr = tuple2._2();
                    tv.constr_$eq(constr);
                    this.log_$eq((List)this.log().tail());
                    continue;
                }
                throw new MatchError(undoPair);
            }
        }

        public void record(Types.TypeVar tv) {
            this.log_$eq(this.log().$colon$colon(new UndoPair<Types.TypeVar, TypeConstraint>(tv, tv.constr().cloneInternal())));
        }

        @Override
        public void clear() {
            MutableSettings.SettingValue settingValue = this.scala$reflect$internal$tpe$TypeConstraints$UndoLog$$$outer().settings().debug();
            MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
            if (BoxesRunTime.unboxToBoolean(settingValue.value())) {
                this.scala$reflect$internal$tpe$TypeConstraints$UndoLog$$$outer().log((Function0<Object>)((Object)new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ UndoLog $outer;

                    public final String apply() {
                        return new StringBuilder().append((Object)"Clearing ").append(BoxesRunTime.boxToInteger(this.$outer.log().size())).append((Object)" entries from the undoLog.").toString();
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }));
            }
            this.log_$eq(Nil$.MODULE$);
        }

        /*
         * WARNING - void declaration
         */
        public <T> T undo(Function0<T> block) {
            T t;
            List<UndoPair<Types.TypeVar, TypeConstraint>> before = this.log();
            try {
                t = block.apply();
                this.undoTo(before);
            }
            catch (Throwable throwable) {
                void var2_2;
                this.undoTo((List<UndoPair<Types.TypeVar, TypeConstraint>>)var2_2);
                throw throwable;
            }
            return t;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeConstraints$UndoLog$$$outer() {
            return this.$outer;
        }

        public UndoLog(SymbolTable $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            this.log = Nil$.MODULE$;
            $outer.perRunCaches().recordCache(this);
        }
    }

    public static final class UndoPair<TypeVar extends Types.TypeVar, TypeConstraint extends TypeConstraint>
    implements Product,
    Serializable {
        private final TypeVar tv;
        private final TypeConstraint tConstraint;

        public TypeVar tv() {
            return this.tv;
        }

        public TypeConstraint tConstraint() {
            return this.tConstraint;
        }

        public <TypeVar extends Types.TypeVar, TypeConstraint extends TypeConstraint> UndoPair<TypeVar, TypeConstraint> copy(TypeVar tv, TypeConstraint tConstraint) {
            return new UndoPair<TypeVar, TypeConstraint>(tv, tConstraint);
        }

        public <TypeVar extends Types.TypeVar, TypeConstraint extends TypeConstraint> TypeVar copy$default$1() {
            return this.tv();
        }

        public <TypeVar extends Types.TypeVar, TypeConstraint extends TypeConstraint> TypeConstraint copy$default$2() {
            return this.tConstraint();
        }

        @Override
        public String productPrefix() {
            return "UndoPair";
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
                    object = this.tConstraint();
                    break;
                }
                case 0: {
                    object = this.tv();
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
            return x$1 instanceof UndoPair;
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
            if (!(x$1 instanceof UndoPair)) return false;
            boolean bl = true;
            if (!bl) return false;
            UndoPair undoPair = (UndoPair)x$1;
            TypeVar TypeVar2 = this.tv();
            TypeVar TypeVar3 = undoPair.tv();
            if (TypeVar2 == null) {
                if (TypeVar3 != null) {
                    return false;
                }
            } else if (!TypeVar2.equals(TypeVar3)) return false;
            TypeConstraint TypeConstraint2 = this.tConstraint();
            TypeConstraint TypeConstraint3 = undoPair.tConstraint();
            if (TypeConstraint2 == null) {
                if (TypeConstraint3 == null) return true;
                return false;
            } else {
                if (!TypeConstraint2.equals(TypeConstraint3)) return false;
                return true;
            }
        }

        public UndoPair(TypeVar tv, TypeConstraint tConstraint) {
            this.tv = tv;
            this.tConstraint = tConstraint;
            Product$class.$init$(this);
        }
    }

    public static class TypeConstraint {
        private List<Types.Type> lobounds;
        private List<Types.Type> hibounds;
        private Types.Type numlo;
        private Types.Type numhi;
        private boolean avoidWidening;
        private Types.Type inst;
        public final /* synthetic */ SymbolTable $outer;

        private List<Types.Type> lobounds() {
            return this.lobounds;
        }

        private void lobounds_$eq(List<Types.Type> x$1) {
            this.lobounds = x$1;
        }

        private List<Types.Type> hibounds() {
            return this.hibounds;
        }

        private void hibounds_$eq(List<Types.Type> x$1) {
            this.hibounds = x$1;
        }

        private Types.Type numlo() {
            return this.numlo;
        }

        private void numlo_$eq(Types.Type x$1) {
            this.numlo = x$1;
        }

        private Types.Type numhi() {
            return this.numhi;
        }

        private void numhi_$eq(Types.Type x$1) {
            this.numhi = x$1;
        }

        private boolean avoidWidening() {
            return this.avoidWidening;
        }

        private void avoidWidening_$eq(boolean x$1) {
            this.avoidWidening = x$1;
        }

        public List<Types.Type> loBounds() {
            List<Types.Type> list2;
            Types.Type type = this.numlo();
            Types$NoType$ types$NoType$ = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().NoType();
            if (!(type != null ? !type.equals(types$NoType$) : types$NoType$ != null)) {
                list2 = this.lobounds();
            } else {
                Types.Type type2 = this.numlo();
                list2 = this.lobounds().$colon$colon(type2);
            }
            return list2;
        }

        public List<Types.Type> hiBounds() {
            List<Types.Type> list2;
            Types.Type type = this.numhi();
            Types$NoType$ types$NoType$ = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().NoType();
            if (!(type != null ? !type.equals(types$NoType$) : types$NoType$ != null)) {
                list2 = this.hibounds();
            } else {
                Types.Type type2 = this.numhi();
                list2 = this.hibounds().$colon$colon(type2);
            }
            return list2;
        }

        public boolean avoidWiden() {
            return this.avoidWidening();
        }

        public void addLoBound(Types.Type tp, boolean isNumericBound) {
            boolean bl;
            Symbols.Symbol symbol = tp.typeSymbol();
            if (this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().definitions().NothingClass().equals(symbol)) {
                bl = true;
            } else {
                boolean bl2 = bl = !this.lobounds().contains(tp);
            }
            if (bl) {
                if (isNumericBound && this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().definitions().isNumericValueType(tp)) {
                    Types.Type type = this.numlo();
                    Types$NoType$ types$NoType$ = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().NoType();
                    if (!(type == null ? types$NoType$ != null : !type.equals(types$NoType$)) || this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().isNumericSubType(this.numlo(), tp)) {
                        this.numlo_$eq(tp);
                    } else if (!this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().isNumericSubType(tp, this.numlo())) {
                        this.numlo_$eq(this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().scala$reflect$internal$tpe$TypeConstraints$$numericLoBound());
                    }
                } else {
                    this.lobounds_$eq(this.lobounds().$colon$colon(tp));
                }
            }
        }

        public boolean addLoBound$default$2() {
            return false;
        }

        public void checkWidening(Types.Type tp) {
            if (tp.isStable()) {
                this.avoidWidening_$eq(true);
            } else {
                Option<Tuple2<Names.TypeName, Types.Type>> option = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().HasTypeMember().unapply(tp);
                if (option.isEmpty()) {
                } else {
                    this.avoidWidening_$eq(true);
                }
            }
        }

        public void addHiBound(Types.Type tp, boolean isNumericBound) {
            boolean bl;
            Symbols.Symbol symbol = tp.typeSymbol();
            Symbols.ClassSymbol classSymbol = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().definitions().AnyClass();
            if (!(classSymbol != null ? !classSymbol.equals(symbol) : symbol != null)) {
                bl = true;
            } else {
                boolean bl2 = bl = !this.hibounds().contains(tp);
            }
            if (bl) {
                this.checkWidening(tp);
                if (isNumericBound && this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().definitions().isNumericValueType(tp)) {
                    Types.Type type = this.numhi();
                    Types$NoType$ types$NoType$ = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().NoType();
                    if (!(type == null ? types$NoType$ != null : !type.equals(types$NoType$)) || this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().isNumericSubType(tp, this.numhi())) {
                        this.numhi_$eq(tp);
                    } else if (!this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().isNumericSubType(this.numhi(), tp)) {
                        this.numhi_$eq(this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().scala$reflect$internal$tpe$TypeConstraints$$numericHiBound());
                    }
                } else {
                    this.hibounds_$eq(this.hibounds().$colon$colon(tp));
                }
            }
        }

        public boolean addHiBound$default$2() {
            return false;
        }

        public boolean instWithinBounds() {
            return this.instValid() && this.isWithinBounds(this.inst());
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean isWithinBounds(Types.Type tp) {
            if (!this.lobounds().forall((Function1<Types.Type, Object>)((Object)new Serializable(this, tp){
                public static final long serialVersionUID = 0L;
                private final Types.Type tp$1;

                public final boolean apply(Types.Type x$4) {
                    return x$4.$less$colon$less(this.tp$1);
                }
                {
                    this.tp$1 = tp$1;
                }
            }))) return false;
            if (!this.hibounds().forall((Function1<Types.Type, Object>)((Object)new Serializable(this, tp){
                public static final long serialVersionUID = 0L;
                private final Types.Type tp$1;

                public final boolean apply(Types.Type x$5) {
                    return this.tp$1.$less$colon$less(x$5);
                }
                {
                    this.tp$1 = tp$1;
                }
            }))) return false;
            Types.Type type = this.numlo();
            Types$NoType$ types$NoType$ = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().NoType();
            if (type == null ? types$NoType$ != null : !type.equals(types$NoType$)) {
                if (!this.numlo().weak_$less$colon$less(tp)) return false;
            }
            Types.Type type2 = this.numhi();
            Types$NoType$ types$NoType$2 = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().NoType();
            if (type2 == null) {
                if (types$NoType$2 == null) return true;
            } else if (type2.equals(types$NoType$2)) return true;
            if (!tp.weak_$less$colon$less(this.numhi())) return false;
            return true;
        }

        public Types.Type inst() {
            return this.inst;
        }

        public void inst_$eq(Types.Type x$1) {
            this.inst = x$1;
        }

        public boolean instValid() {
            return this.inst() != null && this.inst() != this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().NoType();
        }

        /*
         * WARNING - void declaration
         */
        public TypeConstraint cloneInternal() {
            void var1_1;
            TypeConstraint tc = new TypeConstraint(this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer(), this.lobounds(), this.hibounds(), this.numlo(), this.numhi(), this.avoidWidening());
            tc.inst_$eq(this.inst());
            return var1_1;
        }

        public String toString() {
            $colon$colon $colon$colon;
            $colon$colon $colon$colon2;
            List list2 = (List)this.loBounds().filterNot((Function1)this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().typeIsNothing());
            String string2 = ((Object)Nil$.MODULE$).equals(list2) ? "" : (list2 instanceof $colon$colon && ((Object)Nil$.MODULE$).equals(($colon$colon2 = ($colon$colon)list2).tl$1()) ? new StringBuilder().append((Object)" >: ").append($colon$colon2.head()).toString() : list2.mkString(" >: (", ", ", ")"));
            List list3 = (List)this.hiBounds().filterNot((Function1)this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().typeIsAny());
            String string3 = ((Object)Nil$.MODULE$).equals(list3) ? "" : (list3 instanceof $colon$colon && ((Object)Nil$.MODULE$).equals(($colon$colon = ($colon$colon)list3).tl$1()) ? new StringBuilder().append((Object)" <: ").append($colon$colon.head()).toString() : list3.mkString(" <: (", ", ", ")"));
            String boundsStr = new StringBuilder().append((Object)string2).append((Object)string3).toString();
            return this.inst() == this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().NoType() ? boundsStr : new StringBuilder().append((Object)boundsStr).append((Object)" _= ").append((Object)this.inst().safeToString()).toString();
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer() {
            return this.$outer;
        }

        public TypeConstraint(SymbolTable $outer, List<Types.Type> lo0, List<Types.Type> hi0, Types.Type numlo0, Types.Type numhi0, boolean avoidWidening0) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            this.lobounds = (List)lo0.filterNot((Function1)$outer.typeIsNothing());
            this.hibounds = (List)hi0.filterNot((Function1)$outer.typeIsAny());
            this.numlo = numlo0;
            this.numhi = numhi0;
            this.avoidWidening = avoidWidening0;
            this.inst = $outer.NoType();
        }

        public TypeConstraint(SymbolTable $outer, List<Types.Type> lo0, List<Types.Type> hi0) {
            this($outer, lo0, hi0, $outer.NoType(), $outer.NoType(), $outer.TypeConstraint().$lessinit$greater$default$5());
        }

        public TypeConstraint(SymbolTable $outer, Types.TypeBounds bounds) {
            this($outer, (List<Types.Type>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{bounds.lo()})), (List<Types.Type>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{bounds.hi()})));
        }

        public TypeConstraint(SymbolTable $outer) {
            this($outer, Nil$.MODULE$, Nil$.MODULE$);
        }
    }
}

