/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function1;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.collection.AbstractSet;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.Seq;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.HashSet;
import scala.collection.immutable.Iterable;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.Set$;
import scala.collection.immutable.Set$class;
import scala.collection.immutable.Traversable$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001\u0011EbaB\u0001\u0003!\u0003\r\t!\u0003\u0002\u0004'\u0016$(BA\u0002\u0005\u0003%IW.\\;uC\ndWM\u0003\u0002\u0006\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001!\u0006\u0002\u000b+M9\u0001aC\b\u001fC!b\u0003C\u0001\u0007\u000e\u001b\u00051\u0011B\u0001\b\u0007\u0005\u0019\te.\u001f*fMB\u0019\u0001#E\n\u000e\u0003\tI!A\u0005\u0002\u0003\u0011%#XM]1cY\u0016\u0004\"\u0001F\u000b\r\u0001\u0011)a\u0003\u0001b\u0001/\t\t\u0011)\u0005\u0002\u00197A\u0011A\"G\u0005\u00035\u0019\u0011qAT8uQ&tw\r\u0005\u0002\r9%\u0011QD\u0002\u0002\u0004\u0003:L\bcA\u0010!'5\tA!\u0003\u0002\u0002\tA!!%J\n(\u001b\u0005\u0019#B\u0001\u0013\u0005\u0003\u001d9WM\\3sS\u000eL!AJ\u0012\u0003%\u001d+g.\u001a:jGN+G\u000fV3na2\fG/\u001a\t\u0003!\u0001\u0001BaH\u0015\u0014W%\u0011!\u0006\u0002\u0002\b'\u0016$H*[6f!\r\u0001\u0002a\u0005\t\u0005?5\u001ar&\u0003\u0002/\t\tq\u0001+\u0019:bY2,G.\u001b>bE2,\u0007c\u0001\u00195'5\t\u0011G\u0003\u0002\u0004e)\u00111\u0007B\u0001\ta\u0006\u0014\u0018\r\u001c7fY&\u0011Q'\r\u0002\u0007!\u0006\u00148+\u001a;\t\u000b]\u0002A\u0011\u0001\u001d\u0002\r\u0011Jg.\u001b;%)\u0005I\u0004C\u0001\u0007;\u0013\tYdA\u0001\u0003V]&$\b\"B\u001f\u0001\t\u0003r\u0014!C2p[B\fg.[8o+\u0005y\u0004c\u0001\u0012AO%\u0011\u0011i\t\u0002\u0011\u000f\u0016tWM]5d\u0007>l\u0007/\u00198j_:DQa\u0011\u0001\u0005B\u0011\u000bQ\u0001^8TKR,\"!\u0012%\u0016\u0003\u0019\u00032\u0001\u0005\u0001H!\t!\u0002\nB\u0003J\u0005\n\u0007!JA\u0001C#\t\u00192\u0004C\u0003M\u0001\u0011\u0005S*A\u0002tKF,\u0012a\u000b\u0005\u0006\u001f\u0002!\t\u0006U\u0001\fa\u0006\u00148i\\7cS:,'/F\u0001R!\u0011\u00116kE\u0018\u000e\u0003IJ!\u0001\u0016\u001a\u0003\u0011\r{WNY5oKJ<QA\u0016\u0002\t\u0002]\u000b1aU3u!\t\u0001\u0002LB\u0003\u0002\u0005!\u0005\u0011l\u0005\u0002Y5B\u0019!eW\u0014\n\u0005q\u001b#aE%n[V$\u0018M\u00197f'\u0016$h)Y2u_JL\b\"\u00020Y\t\u0003y\u0016A\u0002\u001fj]&$h\bF\u0001X\u0011\u0015\t\u0007\fb\u0001c\u00031\u0019\u0017M\u001c\"vS2$gI]8n+\t\u0019G.F\u0001e!\u0015\u0011SmZ6n\u0013\t17E\u0001\u0007DC:\u0014U/\u001b7e\rJ|W\u000e\u0005\u0002iS6\t\u0001,\u0003\u0002k\u0001\n!1i\u001c7m!\t!B\u000eB\u0003\u0017A\n\u0007q\u0003E\u0002\u0011\u0001-<Qa\u001c-\t\nA\f\u0001\"R7qif\u001cV\r\u001e\t\u0003QF4QA\u001d-\t\nM\u0014\u0001\"R7qif\u001cV\r^\n\u0005cR<\b\u0010E\u0002 knI!A\u001e\u0003\u0003\u0017\u0005\u00137\u000f\u001e:bGR\u001cV\r\u001e\t\u0004!\u0001Y\u0002C\u0001\u0007z\u0013\tQhA\u0001\u0007TKJL\u0017\r\\5{C\ndW\rC\u0003_c\u0012\u0005A\u0010F\u0001q\u0011\u0015q\u0018\u000f\"\u0011\u0000\u0003\u0011\u0019\u0018N_3\u0016\u0005\u0005\u0005\u0001c\u0001\u0007\u0002\u0004%\u0019\u0011Q\u0001\u0004\u0003\u0007%sG\u000fC\u0004\u0002\nE$\t!a\u0003\u0002\u0011\r|g\u000e^1j]N$B!!\u0004\u0002\u0014A\u0019A\"a\u0004\n\u0007\u0005EaAA\u0004C_>dW-\u00198\t\u000f\u0005U\u0011q\u0001a\u00017\u0005!Q\r\\3n\u0011\u001d\tI\"\u001dC\u0001\u00037\tQ\u0001\n9mkN$2a^A\u000f\u0011\u001d\t)\"a\u0006A\u0002mAq!!\tr\t\u0003\t\u0019#\u0001\u0004%[&tWo\u001d\u000b\u0004o\u0006\u0015\u0002bBA\u000b\u0003?\u0001\ra\u0007\u0005\b\u0003S\tH\u0011AA\u0016\u0003!IG/\u001a:bi>\u0014XCAA\u0017!\u0011y\u0012qF\u000e\n\u0007\u0005EBA\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0011\u001d\t)$\u001dC!\u0003o\tqAZ8sK\u0006\u001c\u0007.\u0006\u0003\u0002:\u0005\u001dCcA\u001d\u0002<!A\u0011QHA\u001a\u0001\u0004\ty$A\u0001g!\u0019a\u0011\u0011I\u000e\u0002F%\u0019\u00111\t\u0004\u0003\u0013\u0019+hn\u0019;j_:\f\u0004c\u0001\u000b\u0002H\u00119\u0011\u0011JA\u001a\u0005\u00049\"!A+\t\r\r\u000bH\u0011IA'+\u0011\ty%!\u0016\u0016\u0005\u0005E\u0003\u0003\u0002\t\u0001\u0003'\u00022\u0001FA+\t\u001dI\u00151\nb\u0001\u0003/\n\"aG\u000e\t\u0013\u0005m\u0013/!A\u0005\n\u0005u\u0013a\u0003:fC\u0012\u0014Vm]8mm\u0016$\"!a\u0018\u0011\t\u0005\u0005\u00141N\u0007\u0003\u0003GRA!!\u001a\u0002h\u0005!A.\u00198h\u0015\t\tI'\u0001\u0003kCZ\f\u0017\u0002BA7\u0003G\u0012aa\u00142kK\u000e$\b\u0002CA91\u0012\u0005A!a\u001d\u0002\u001b\u0015l\u0007\u000f^=J]N$\u0018M\\2f+\u00059hABA<1\u0002\tIH\u0001\u0003TKR\fT\u0003BA>\u0003\u0003\u001br!!\u001e\u0002~\u0005\r\u0005\u0010\u0005\u0003 k\u0006}\u0004c\u0001\u000b\u0002\u0002\u00121a#!\u001eC\u0002]\u0001B\u0001\u0005\u0001\u0002\u0000!Y\u0011qQA;\u0005\u0003\u0005\u000b\u0011BA@\u0003\u0015)G.Z72\u0011!q\u0016Q\u000fC\u0001\t\u0005-E\u0003BAG\u0003\u001f\u0003R\u0001[A;\u0003\u007fB\u0001\"a\"\u0002\n\u0002\u0007\u0011q\u0010\u0005\u0007}\u0006UD\u0011I@\t\u0011\u0005%\u0011Q\u000fC\u0001\u0003+#B!!\u0004\u0002\u0018\"A\u0011QCAJ\u0001\u0004\ty\b\u0003\u0005\u0002\u001a\u0005UD\u0011AAN)\u0011\t\u0019)!(\t\u0011\u0005U\u0011\u0011\u0014a\u0001\u0003\u007fB\u0001\"!\t\u0002v\u0011\u0005\u0011\u0011\u0015\u000b\u0005\u0003\u0007\u000b\u0019\u000b\u0003\u0005\u0002\u0016\u0005}\u0005\u0019AA@\u0011!\tI#!\u001e\u0005\u0002\u0005\u001dVCAAU!\u0015y\u0012qFA@\u0011!\t)$!\u001e\u0005B\u00055V\u0003BAX\u0003o#2!OAY\u0011!\ti$a+A\u0002\u0005M\u0006c\u0002\u0007\u0002B\u0005}\u0014Q\u0017\t\u0004)\u0005]FaBA%\u0003W\u0013\ra\u0006\u0005\t\u0003w\u000b)\b\"\u0011\u0002>\u00061Q\r_5tiN$B!!\u0004\u0002@\"A\u0011\u0011YA]\u0001\u0004\t\u0019-A\u0001q!\u001da\u0011\u0011IA@\u0003\u001bAc!a0\u0002H\u00065\u0007c\u0001\u0007\u0002J&\u0019\u00111\u001a\u0004\u0003\u001d\u0011,\u0007O]3dCR,GMT1nKF:q$a4\u0002V\n\u0015\u0001c\u0001\u0007\u0002R&\u0019\u00111\u001b\u0004\u0003\rMKXNY8mc%\u0019\u0013q[Ao\u0003g\fy\u000e\u0006\u0003\u0002P\u0006e\u0007bBAn\u0011\u0001\u0007\u0011Q]\u0001\u0005]\u0006lW-\u0003\u0003\u0002`\u0006\u0005\u0018!B1qa2L(bAAr\r\u000511+_7c_2\u0004B!a:\u0002n:\u0019A\"!;\n\u0007\u0005-h!\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003_\f\tP\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003W4\u0011'C\u0012\u0002v\n\u0005!1AAr\u001d\u0011\t9P!\u0001\u000f\t\u0005e\u0018q`\u0007\u0003\u0003wT1!!@\t\u0003\u0019a$o\\8u}%\tq!C\u0002\u0002d\u001a\td\u0001JA|\u0003\u007f<\u0011'B\u0013\u0003\b\t%qB\u0001B\u0005C\t\ti\u0004\u0003\u0005\u0003\u000e\u0005UD\u0011\tB\b\u0003\u00191wN]1mYR!\u0011Q\u0002B\t\u0011!\t\tMa\u0003A\u0002\u0005\r\u0007F\u0002B\t\u0003\u000f\u0014)\"M\u0004 \u0003\u001f\u00149B!\b2\u0013\r\n9.!8\u0003\u001a\u0005}\u0017'C\u0012\u0002v\n\u0005!1DArc\u0019!\u0013q_A\u0000\u000fE*QEa\u0002\u0003\n!A!\u0011EA;\t\u0003\u0012\u0019#\u0001\u0003gS:$G\u0003\u0002B\u0013\u0005W\u0001R\u0001\u0004B\u0014\u0003\u007fJ1A!\u000b\u0007\u0005\u0019y\u0005\u000f^5p]\"A\u0011\u0011\u0019B\u0010\u0001\u0004\t\u0019\r\u000b\u0004\u0003,\u0005\u001d'qF\u0019\b?\u0005='\u0011\u0007B\u001cc%\u0019\u0013q[Ao\u0005g\ty.M\u0005$\u0003k\u0014\tA!\u000e\u0002dF2A%a>\u0002\u0000\u001e\tT!\nB\u0004\u0005\u0013AqaQA;\t\u0003\u0012Y$\u0006\u0003\u0003>\t\rSC\u0001B !\u0011\u0001\u0002A!\u0011\u0011\u0007Q\u0011\u0019\u0005B\u0004J\u0005s\u0011\rA!\u0012\u0012\u0007\u0005}4\u0004\u000b\u0005\u0003:\t%#q\nB*!\ra!1J\u0005\u0004\u0005\u001b2!\u0001\u00063faJ,7-\u0019;fI>3XM\u001d:jI&tw-\t\u0002\u0003R\u0005\tG\u000b[5tA%lW.\u001e;bE2,\u0007e]3uAMDw.\u001e7eA\u0011|\u0007E\\8uQ&tw\rI8oAQ|7+\u001a;!EV$\beY1ti\u0002JGo]3mM\u0002\"x\u000eI1!'\u0016$\be^5uQ\u0002\n\u0007e^5eKJ\u0004S\r\\3nK:$\b\u0005^=qK:\n#A!\u0016\u0002\rIr\u0013'\r\u00189Q!\t)H!\u0017\u0003`\t\u0005\u0004c\u0001\u0007\u0003\\%\u0019!Q\f\u0004\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g\u0004C\t\u001e;<\u0017$6vJ\u0007\r\t\u0015\u0004\f\u0001B4\u0005\u0011\u0019V\r\u001e\u001a\u0016\t\t%$qN\n\b\u0005G\u0012YG!\u001dy!\u0011yRO!\u001c\u0011\u0007Q\u0011y\u0007\u0002\u0004\u0017\u0005G\u0012\ra\u0006\t\u0005!\u0001\u0011i\u0007C\u0006\u0002\b\n\r$\u0011!Q\u0001\n\t5\u0004b\u0003B<\u0005G\u0012\t\u0011)A\u0005\u0005[\nQ!\u001a7f[JB\u0001B\u0018B2\t\u0003!!1\u0010\u000b\u0007\u0005{\u0012yH!!\u0011\u000b!\u0014\u0019G!\u001c\t\u0011\u0005\u001d%\u0011\u0010a\u0001\u0005[B\u0001Ba\u001e\u0003z\u0001\u0007!Q\u000e\u0005\u0007}\n\rD\u0011I@\t\u0011\u0005%!1\rC\u0001\u0005\u000f#B!!\u0004\u0003\n\"A\u0011Q\u0003BC\u0001\u0004\u0011i\u0007\u0003\u0005\u0002\u001a\t\rD\u0011\u0001BG)\u0011\u0011\tHa$\t\u0011\u0005U!1\u0012a\u0001\u0005[B\u0001\"!\t\u0003d\u0011\u0005!1\u0013\u000b\u0005\u0005c\u0012)\n\u0003\u0005\u0002\u0016\tE\u0005\u0019\u0001B7\u0011!\tICa\u0019\u0005\u0002\teUC\u0001BN!\u0015y\u0012q\u0006B7\u0011!\t)Da\u0019\u0005B\t}U\u0003\u0002BQ\u0005S#2!\u000fBR\u0011!\tiD!(A\u0002\t\u0015\u0006c\u0002\u0007\u0002B\t5$q\u0015\t\u0004)\t%FaBA%\u0005;\u0013\ra\u0006\u0005\t\u0003w\u0013\u0019\u0007\"\u0011\u0003.R!\u0011Q\u0002BX\u0011!\t\tMa+A\u0002\tE\u0006c\u0002\u0007\u0002B\t5\u0014Q\u0002\u0015\u0007\u0005_\u000b9M!.2\u000f}\tyMa.\u0003>FJ1%a6\u0002^\ne\u0016q\\\u0019\nG\u0005U(\u0011\u0001B^\u0003G\fd\u0001JA|\u0003\u007f<\u0011'B\u0013\u0003\b\t%\u0001\u0002\u0003B\u0007\u0005G\"\tE!1\u0015\t\u00055!1\u0019\u0005\t\u0003\u0003\u0014y\f1\u0001\u00032\"2!1YAd\u0005\u000f\ftaHAh\u0005\u0013\u0014y-M\u0005$\u0003/\fiNa3\u0002`FJ1%!>\u0003\u0002\t5\u00171]\u0019\u0007I\u0005]\u0018q`\u00042\u000b\u0015\u00129A!\u0003\t\u0011\t\u0005\"1\rC!\u0005'$BA!6\u0003XB)ABa\n\u0003n!A\u0011\u0011\u0019Bi\u0001\u0004\u0011\t\f\u000b\u0004\u0003X\u0006\u001d'1\\\u0019\b?\u0005='Q\u001cBrc%\u0019\u0013q[Ao\u0005?\fy.M\u0005$\u0003k\u0014\tA!9\u0002dF2A%a>\u0002\u0000\u001e\tT!\nB\u0004\u0005\u0013Aqa\u0011B2\t\u0003\u00129/\u0006\u0003\u0003j\n=XC\u0001Bv!\u0011\u0001\u0002A!<\u0011\u0007Q\u0011y\u000fB\u0004J\u0005K\u0014\rA!=\u0012\u0007\t54\u0004\u000b\u0005\u0003f\n%#q\nB*Q!\u0011\u0019G!\u0017\u0003`\t]h\u0004\u0003T\u0016(0L2)\u000e;\u0007\r\tm\b\f\u0001B\u007f\u0005\u0011\u0019V\r^\u001a\u0016\t\t}8QA\n\b\u0005s\u001c\taa\u0002y!\u0011yRoa\u0001\u0011\u0007Q\u0019)\u0001\u0002\u0004\u0017\u0005s\u0014\ra\u0006\t\u0005!\u0001\u0019\u0019\u0001C\u0006\u0002\b\ne(\u0011!Q\u0001\n\r\r\u0001b\u0003B<\u0005s\u0014\t\u0011)A\u0005\u0007\u0007A1ba\u0004\u0003z\n\u0005\t\u0015!\u0003\u0004\u0004\u0005)Q\r\\3ng!AaL!?\u0005\u0002\u0011\u0019\u0019\u0002\u0006\u0005\u0004\u0016\r]1\u0011DB\u000e!\u0015A'\u0011`B\u0002\u0011!\t9i!\u0005A\u0002\r\r\u0001\u0002\u0003B<\u0007#\u0001\raa\u0001\t\u0011\r=1\u0011\u0003a\u0001\u0007\u0007AaA B}\t\u0003z\b\u0002CA\u0005\u0005s$\ta!\t\u0015\t\u0005511\u0005\u0005\t\u0003+\u0019y\u00021\u0001\u0004\u0004!A\u0011\u0011\u0004B}\t\u0003\u00199\u0003\u0006\u0003\u0004\b\r%\u0002\u0002CA\u000b\u0007K\u0001\raa\u0001\t\u0011\u0005\u0005\"\u0011 C\u0001\u0007[!Baa\u0002\u00040!A\u0011QCB\u0016\u0001\u0004\u0019\u0019\u0001\u0003\u0005\u0002*\teH\u0011AB\u001a+\t\u0019)\u0004E\u0003 \u0003_\u0019\u0019\u0001\u0003\u0005\u00026\teH\u0011IB\u001d+\u0011\u0019Yda\u0011\u0015\u0007e\u001ai\u0004\u0003\u0005\u0002>\r]\u0002\u0019AB !\u001da\u0011\u0011IB\u0002\u0007\u0003\u00022\u0001FB\"\t\u001d\tIea\u000eC\u0002]A\u0001\"a/\u0003z\u0012\u00053q\t\u000b\u0005\u0003\u001b\u0019I\u0005\u0003\u0005\u0002B\u000e\u0015\u0003\u0019AB&!\u001da\u0011\u0011IB\u0002\u0003\u001bAca!\u0013\u0002H\u000e=\u0013gB\u0010\u0002P\u000eE3qK\u0019\nG\u0005]\u0017Q\\B*\u0003?\f\u0014bIA{\u0005\u0003\u0019)&a92\r\u0011\n90a@\bc\u0015)#q\u0001B\u0005\u0011!\u0011iA!?\u0005B\rmC\u0003BA\u0007\u0007;B\u0001\"!1\u0004Z\u0001\u000711\n\u0015\u0007\u0007;\n9m!\u00192\u000f}\tyma\u0019\u0004jEJ1%a6\u0002^\u000e\u0015\u0014q\\\u0019\nG\u0005U(\u0011AB4\u0003G\fd\u0001JA|\u0003\u007f<\u0011'B\u0013\u0003\b\t%\u0001\u0002\u0003B\u0011\u0005s$\te!\u001c\u0015\t\r=4\u0011\u000f\t\u0006\u0019\t\u001d21\u0001\u0005\t\u0003\u0003\u001cY\u00071\u0001\u0004L!21\u0011OAd\u0007k\ntaHAh\u0007o\u001ai(M\u0005$\u0003/\fin!\u001f\u0002`FJ1%!>\u0003\u0002\rm\u00141]\u0019\u0007I\u0005]\u0018q`\u00042\u000b\u0015\u00129A!\u0003\t\u000f\r\u0013I\u0010\"\u0011\u0004\u0002V!11QBE+\t\u0019)\t\u0005\u0003\u0011\u0001\r\u001d\u0005c\u0001\u000b\u0004\n\u00129\u0011ja C\u0002\r-\u0015cAB\u00027!B1q\u0010B%\u0005\u001f\u0012\u0019\u0006\u000b\u0005\u0003z\ne#qLBI=!qM&s\u001f:W\u0014\u0003kABBK1\u0002\u00199J\u0001\u0003TKR$T\u0003BBM\u0007?\u001braa%\u0004\u001c\u000e\u0005\u0006\u0010\u0005\u0003 k\u000eu\u0005c\u0001\u000b\u0004 \u00121aca%C\u0002]\u0001B\u0001\u0005\u0001\u0004\u001e\"Y\u0011qQBJ\u0005\u0003\u0005\u000b\u0011BBO\u0011-\u00119ha%\u0003\u0002\u0003\u0006Ia!(\t\u0017\r=11\u0013B\u0001B\u0003%1Q\u0014\u0005\f\u0007W\u001b\u0019J!A!\u0002\u0013\u0019i*A\u0003fY\u0016lG\u0007\u0003\u0005_\u0007'#\t\u0001BBX))\u0019\tla-\u00046\u000e]6\u0011\u0018\t\u0006Q\u000eM5Q\u0014\u0005\t\u0003\u000f\u001bi\u000b1\u0001\u0004\u001e\"A!qOBW\u0001\u0004\u0019i\n\u0003\u0005\u0004\u0010\r5\u0006\u0019ABO\u0011!\u0019Yk!,A\u0002\ru\u0005B\u0002@\u0004\u0014\u0012\u0005s\u0010\u0003\u0005\u0002\n\rME\u0011AB`)\u0011\tia!1\t\u0011\u0005U1Q\u0018a\u0001\u0007;C\u0001\"!\u0007\u0004\u0014\u0012\u00051Q\u0019\u000b\u0005\u0007C\u001b9\r\u0003\u0005\u0002\u0016\r\r\u0007\u0019ABO\u0011!\t\tca%\u0005\u0002\r-G\u0003BBQ\u0007\u001bD\u0001\"!\u0006\u0004J\u0002\u00071Q\u0014\u0005\t\u0003S\u0019\u0019\n\"\u0001\u0004RV\u001111\u001b\t\u0006?\u0005=2Q\u0014\u0005\t\u0003k\u0019\u0019\n\"\u0011\u0004XV!1\u0011\\Bq)\rI41\u001c\u0005\t\u0003{\u0019)\u000e1\u0001\u0004^B9A\"!\u0011\u0004\u001e\u000e}\u0007c\u0001\u000b\u0004b\u00129\u0011\u0011JBk\u0005\u00049\u0002\u0002CA^\u0007'#\te!:\u0015\t\u000551q\u001d\u0005\t\u0003\u0003\u001c\u0019\u000f1\u0001\u0004jB9A\"!\u0011\u0004\u001e\u00065\u0001FBBt\u0003\u000f\u001ci/M\u0004 \u0003\u001f\u001cyo!>2\u0013\r\n9.!8\u0004r\u0006}\u0017'C\u0012\u0002v\n\u000511_Arc\u0019!\u0013q_A\u0000\u000fE*QEa\u0002\u0003\n!A!QBBJ\t\u0003\u001aI\u0010\u0006\u0003\u0002\u000e\rm\b\u0002CAa\u0007o\u0004\ra!;)\r\rm\u0018qYB\u0000c\u001dy\u0012q\u001aC\u0001\t\u000f\t\u0014bIAl\u0003;$\u0019!a82\u0013\r\n)P!\u0001\u0005\u0006\u0005\r\u0018G\u0002\u0013\u0002x\u0006}x!M\u0003&\u0005\u000f\u0011I\u0001\u0003\u0005\u0003\"\rME\u0011\tC\u0006)\u0011!i\u0001b\u0004\u0011\u000b1\u00119c!(\t\u0011\u0005\u0005G\u0011\u0002a\u0001\u0007SDc\u0001b\u0004\u0002H\u0012M\u0011gB\u0010\u0002P\u0012UA1D\u0019\nG\u0005]\u0017Q\u001cC\f\u0003?\f\u0014bIA{\u0005\u0003!I\"a92\r\u0011\n90a@\bc\u0015)#q\u0001B\u0005\u0011\u001d\u001951\u0013C!\t?)B\u0001\"\t\u0005(U\u0011A1\u0005\t\u0005!\u0001!)\u0003E\u0002\u0015\tO!q!\u0013C\u000f\u0005\u0004!I#E\u0002\u0004\u001enA\u0003\u0002\"\b\u0003J\t=#1\u000b\u0015\t\u0007'\u0013IFa\u0018\u00050yAQZ/TE$6D_\u0017")
public interface Set<A>
extends Iterable<A>,
scala.collection.Set<A> {
    @Override
    public GenericCompanion<Set> companion();

    @Override
    public <B> Set<B> toSet();

    @Override
    public Set<A> seq();

    @Override
    public Combiner<A, ParSet<A>> parCombiner();

    public static class Set1<A>
    extends AbstractSet<A>
    implements Set<A>,
    Serializable {
        public static final long serialVersionUID = 1233385750652442003L;
        private final A elem1;

        @Override
        public GenericCompanion<Set> companion() {
            return Set$class.companion(this);
        }

        @Override
        public Set<A> seq() {
            return Set$class.seq(this);
        }

        @Override
        public Combiner<A, ParSet<A>> parCombiner() {
            return Set$class.parCombiner(this);
        }

        @Override
        public int size() {
            return 1;
        }

        @Override
        public boolean contains(A elem) {
            A a = this.elem1;
            return elem == a ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a) : elem.equals(a))));
        }

        @Override
        public Set<A> $plus(A elem) {
            return this.contains(elem) ? this : new Set2<A>(this.elem1, elem);
        }

        @Override
        public Set<A> $minus(A elem) {
            A a = this.elem1;
            return (elem == a ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a) : elem.equals(a))))) ? Set$.MODULE$.empty() : this;
        }

        @Override
        public Iterator<A> iterator() {
            return Iterator$.MODULE$.apply(Predef$.MODULE$.genericWrapArray(new Object[]{this.elem1}));
        }

        @Override
        public <U> void foreach(Function1<A, U> f) {
            f.apply(this.elem1);
        }

        @Override
        public boolean exists(Function1<A, Object> p) {
            return BoxesRunTime.unboxToBoolean(p.apply(this.elem1));
        }

        @Override
        public boolean forall(Function1<A, Object> p) {
            return BoxesRunTime.unboxToBoolean(p.apply(this.elem1));
        }

        @Override
        public Option<A> find(Function1<A, Object> p) {
            return BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) ? new Some<A>(this.elem1) : None$.MODULE$;
        }

        @Override
        public <B> Set<B> toSet() {
            return this;
        }

        public Set1(A elem1) {
            this.elem1 = elem1;
            Traversable$class.$init$(this);
            Iterable$class.$init$(this);
            Set$class.$init$(this);
        }
    }

    public static class Set2<A>
    extends AbstractSet<A>
    implements Set<A>,
    Serializable {
        public static final long serialVersionUID = -6443011234944830092L;
        private final A elem1;
        private final A elem2;

        @Override
        public GenericCompanion<Set> companion() {
            return Set$class.companion(this);
        }

        @Override
        public Set<A> seq() {
            return Set$class.seq(this);
        }

        @Override
        public Combiner<A, ParSet<A>> parCombiner() {
            return Set$class.parCombiner(this);
        }

        @Override
        public int size() {
            return 2;
        }

        @Override
        public boolean contains(A elem) {
            A a;
            A a2 = this.elem1;
            return (elem == a2 ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a2) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a2) : elem.equals(a2))))) || (elem == (a = this.elem2) ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a) : elem.equals(a)))));
        }

        @Override
        public Set<A> $plus(A elem) {
            return this.contains(elem) ? this : new Set3<A>(this.elem1, this.elem2, elem);
        }

        @Override
        public Set<A> $minus(A elem) {
            A a;
            A a2 = this.elem1;
            return (elem == a2 ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a2) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a2) : elem.equals(a2))))) ? new Set1<A>(this.elem2) : ((elem == (a = this.elem2) ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a) : elem.equals(a))))) ? new Set1<A>(this.elem1) : this);
        }

        @Override
        public Iterator<A> iterator() {
            return Iterator$.MODULE$.apply(Predef$.MODULE$.genericWrapArray(new Object[]{this.elem1, this.elem2}));
        }

        @Override
        public <U> void foreach(Function1<A, U> f) {
            f.apply(this.elem1);
            f.apply(this.elem2);
        }

        @Override
        public boolean exists(Function1<A, Object> p) {
            return BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) || BoxesRunTime.unboxToBoolean(p.apply(this.elem2));
        }

        @Override
        public boolean forall(Function1<A, Object> p) {
            return BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) && BoxesRunTime.unboxToBoolean(p.apply(this.elem2));
        }

        @Override
        public Option<A> find(Function1<A, Object> p) {
            return BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) ? new Some<A>(this.elem1) : (BoxesRunTime.unboxToBoolean(p.apply(this.elem2)) ? new Some<A>(this.elem2) : None$.MODULE$);
        }

        @Override
        public <B> Set<B> toSet() {
            return this;
        }

        public Set2(A elem1, A elem2) {
            this.elem1 = elem1;
            this.elem2 = elem2;
            Traversable$class.$init$(this);
            Iterable$class.$init$(this);
            Set$class.$init$(this);
        }
    }

    public static class Set3<A>
    extends AbstractSet<A>
    implements Set<A>,
    Serializable {
        public static final long serialVersionUID = -3590273538119220064L;
        private final A elem1;
        private final A elem2;
        private final A elem3;

        @Override
        public GenericCompanion<Set> companion() {
            return Set$class.companion(this);
        }

        @Override
        public Set<A> seq() {
            return Set$class.seq(this);
        }

        @Override
        public Combiner<A, ParSet<A>> parCombiner() {
            return Set$class.parCombiner(this);
        }

        @Override
        public int size() {
            return 3;
        }

        @Override
        public boolean contains(A elem) {
            A a;
            A a2;
            A a3 = this.elem1;
            return (elem == a3 ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a3) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a3) : elem.equals(a3))))) || (elem == (a2 = this.elem2) ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a2) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a2) : elem.equals(a2))))) || (elem == (a = this.elem3) ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a) : elem.equals(a)))));
        }

        @Override
        public Set<A> $plus(A elem) {
            return this.contains(elem) ? this : new Set4<A>(this.elem1, this.elem2, this.elem3, elem);
        }

        @Override
        public Set<A> $minus(A elem) {
            A a;
            A a2;
            A a3 = this.elem1;
            return (elem == a3 ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a3) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a3) : elem.equals(a3))))) ? new Set2<A>(this.elem2, this.elem3) : ((elem == (a2 = this.elem2) ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a2) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a2) : elem.equals(a2))))) ? new Set2<A>(this.elem1, this.elem3) : ((elem == (a = this.elem3) ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a) : elem.equals(a))))) ? new Set2<A>(this.elem1, this.elem2) : this));
        }

        @Override
        public Iterator<A> iterator() {
            return Iterator$.MODULE$.apply(Predef$.MODULE$.genericWrapArray(new Object[]{this.elem1, this.elem2, this.elem3}));
        }

        @Override
        public <U> void foreach(Function1<A, U> f) {
            f.apply(this.elem1);
            f.apply(this.elem2);
            f.apply(this.elem3);
        }

        @Override
        public boolean exists(Function1<A, Object> p) {
            return BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) || BoxesRunTime.unboxToBoolean(p.apply(this.elem2)) || BoxesRunTime.unboxToBoolean(p.apply(this.elem3));
        }

        @Override
        public boolean forall(Function1<A, Object> p) {
            return BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) && BoxesRunTime.unboxToBoolean(p.apply(this.elem2)) && BoxesRunTime.unboxToBoolean(p.apply(this.elem3));
        }

        @Override
        public Option<A> find(Function1<A, Object> p) {
            return BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) ? new Some<A>(this.elem1) : (BoxesRunTime.unboxToBoolean(p.apply(this.elem2)) ? new Some<A>(this.elem2) : (BoxesRunTime.unboxToBoolean(p.apply(this.elem3)) ? new Some<A>(this.elem3) : None$.MODULE$));
        }

        @Override
        public <B> Set<B> toSet() {
            return this;
        }

        public Set3(A elem1, A elem2, A elem3) {
            this.elem1 = elem1;
            this.elem2 = elem2;
            this.elem3 = elem3;
            Traversable$class.$init$(this);
            Iterable$class.$init$(this);
            Set$class.$init$(this);
        }
    }

    public static class Set4<A>
    extends AbstractSet<A>
    implements Set<A>,
    Serializable {
        public static final long serialVersionUID = -3622399588156184395L;
        private final A elem1;
        private final A elem2;
        private final A elem3;
        private final A elem4;

        @Override
        public GenericCompanion<Set> companion() {
            return Set$class.companion(this);
        }

        @Override
        public Set<A> seq() {
            return Set$class.seq(this);
        }

        @Override
        public Combiner<A, ParSet<A>> parCombiner() {
            return Set$class.parCombiner(this);
        }

        @Override
        public int size() {
            return 4;
        }

        @Override
        public boolean contains(A elem) {
            A a;
            A a2;
            A a3;
            A a4 = this.elem1;
            return (elem == a4 ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a4) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a4) : elem.equals(a4))))) || (elem == (a3 = this.elem2) ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a3) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a3) : elem.equals(a3))))) || (elem == (a2 = this.elem3) ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a2) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a2) : elem.equals(a2))))) || (elem == (a = this.elem4) ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a) : elem.equals(a)))));
        }

        @Override
        public Set<A> $plus(A elem) {
            return this.contains(elem) ? this : new HashSet().$plus((Object)this.elem1, (Object)this.elem2, (Seq)Predef$.MODULE$.genericWrapArray(new Object[]{this.elem3, this.elem4, elem}));
        }

        @Override
        public Set<A> $minus(A elem) {
            A a;
            A a2;
            A a3;
            A a4 = this.elem1;
            return (elem == a4 ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a4) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a4) : elem.equals(a4))))) ? new Set3<A>(this.elem2, this.elem3, this.elem4) : ((elem == (a3 = this.elem2) ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a3) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a3) : elem.equals(a3))))) ? new Set3<A>(this.elem1, this.elem3, this.elem4) : ((elem == (a2 = this.elem3) ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a2) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a2) : elem.equals(a2))))) ? new Set3<A>(this.elem1, this.elem2, this.elem4) : ((elem == (a = this.elem4) ? true : (elem == null ? false : (elem instanceof Number ? BoxesRunTime.equalsNumObject((Number)elem, a) : (elem instanceof Character ? BoxesRunTime.equalsCharObject((Character)elem, a) : elem.equals(a))))) ? new Set3<A>(this.elem1, this.elem2, this.elem3) : this)));
        }

        @Override
        public Iterator<A> iterator() {
            return Iterator$.MODULE$.apply(Predef$.MODULE$.genericWrapArray(new Object[]{this.elem1, this.elem2, this.elem3, this.elem4}));
        }

        @Override
        public <U> void foreach(Function1<A, U> f) {
            f.apply(this.elem1);
            f.apply(this.elem2);
            f.apply(this.elem3);
            f.apply(this.elem4);
        }

        @Override
        public boolean exists(Function1<A, Object> p) {
            return BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) || BoxesRunTime.unboxToBoolean(p.apply(this.elem2)) || BoxesRunTime.unboxToBoolean(p.apply(this.elem3)) || BoxesRunTime.unboxToBoolean(p.apply(this.elem4));
        }

        @Override
        public boolean forall(Function1<A, Object> p) {
            return BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) && BoxesRunTime.unboxToBoolean(p.apply(this.elem2)) && BoxesRunTime.unboxToBoolean(p.apply(this.elem3)) && BoxesRunTime.unboxToBoolean(p.apply(this.elem4));
        }

        @Override
        public Option<A> find(Function1<A, Object> p) {
            return BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) ? new Some<A>(this.elem1) : (BoxesRunTime.unboxToBoolean(p.apply(this.elem2)) ? new Some<A>(this.elem2) : (BoxesRunTime.unboxToBoolean(p.apply(this.elem3)) ? new Some<A>(this.elem3) : (BoxesRunTime.unboxToBoolean(p.apply(this.elem4)) ? new Some<A>(this.elem4) : None$.MODULE$)));
        }

        @Override
        public <B> Set<B> toSet() {
            return this;
        }

        public Set4(A elem1, A elem2, A elem3, A elem4) {
            this.elem1 = elem1;
            this.elem2 = elem2;
            this.elem3 = elem3;
            this.elem4 = elem4;
            Traversable$class.$init$(this);
            Iterable$class.$init$(this);
            Set$class.$init$(this);
        }
    }
}

