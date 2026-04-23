/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Predef$;
import scala.Tuple2;
import scala.collection.GenIterable;
import scala.collection.GenMap;
import scala.collection.GenSeq;
import scala.collection.GenSet;
import scala.collection.GenTraversable;
import scala.collection.Iterator;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\r]gaB\u0001\u0003!\u0003\r\na\u0002\u0002\u0013\u000f\u0016tGK]1wKJ\u001c\u0018M\u00197f\u001f:\u001cWM\u0003\u0002\u0004\t\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u0015\tQa]2bY\u0006\u001c\u0001!\u0006\u0002\t7M\u0011\u0001!\u0003\t\u0003\u0015-i\u0011\u0001B\u0005\u0003\u0019\u0011\u00111!\u00118z\u0011\u0015q\u0001A\"\u0001\u0010\u0003\u001d1wN]3bG\",\"\u0001\u0005\u0012\u0015\u0005E!\u0002C\u0001\u0006\u0013\u0013\t\u0019BA\u0001\u0003V]&$\b\"B\u000b\u000e\u0001\u00041\u0012!\u00014\u0011\t)9\u0012$I\u0005\u00031\u0011\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005iYB\u0002\u0001\u0003\u00079\u0001!)\u0019A\u000f\u0003\u0003\u0005\u000b\"AH\u0005\u0011\u0005)y\u0012B\u0001\u0011\u0005\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0007\u0012\u0005\u000b\rj!\u0019A\u000f\u0003\u0003UCQ!\n\u0001\u0007\u0002\u0019\nq\u0002[1t\t\u00164\u0017N\\5uKNK'0Z\u000b\u0002OA\u0011!\u0002K\u0005\u0003S\u0011\u0011qAQ8pY\u0016\fg\u000eC\u0003,\u0001\u0019\u0005A&A\u0002tKF,\u0012!\f\t\u0004]=JR\"\u0001\u0002\n\u0005A\u0012!a\u0004+sCZ,'o]1cY\u0016|enY3\t\u000bI\u0002a\u0011A\u001a\u0002\tML'0Z\u000b\u0002iA\u0011!\"N\u0005\u0003m\u0011\u00111!\u00138u\u0011\u0015A\u0004A\"\u0001'\u0003\u001dI7/R7qifDQA\u000f\u0001\u0007\u0002\u0019\n\u0001B\\8o\u000b6\u0004H/\u001f\u0005\u0006y\u00011\tAJ\u0001\u0013SN$&/\u0019<feN\f'\r\\3BO\u0006Lg\u000eC\u0003?\u0001\u0019\u0005q(\u0001\u0004sK\u0012,8-Z\u000b\u0003\u0001\n#\"!Q#\u0011\u0005i\u0011E!B\">\u0005\u0004!%AA!2#\tI\u0012\u0002C\u0003G{\u0001\u0007q)\u0001\u0002paB)!\u0002S!B\u0003&\u0011\u0011\n\u0002\u0002\n\rVt7\r^5p]JBQa\u0013\u0001\u0007\u00021\u000bAB]3ek\u000e,w\n\u001d;j_:,\"!\u0014*\u0015\u00059\u001b\u0006c\u0001\u0006P#&\u0011\u0001\u000b\u0002\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005i\u0011F!B\"K\u0005\u0004!\u0005\"\u0002$K\u0001\u0004!\u0006#\u0002\u0006I#F\u000b\u0006\"\u0002,\u0001\r\u00039\u0016\u0001\u00024pY\u0012,\"\u0001W.\u0015\u0005esFC\u0001.]!\tQ2\fB\u0003D+\n\u0007A\tC\u0003G+\u0002\u0007Q\fE\u0003\u000b\u0011jS&\fC\u0003`+\u0002\u0007!,A\u0001{\u0011\u0015\t\u0007A\"\u0001c\u0003)!C-\u001b<%G>dwN\\\u000b\u0003G\u001a$\"\u0001\u001a6\u0015\u0005\u0015D\u0007C\u0001\u000eg\t\u00159\u0007M1\u0001\u001e\u0005\u0005\u0011\u0005\"\u0002$a\u0001\u0004I\u0007#\u0002\u0006IKf)\u0007\"B0a\u0001\u0004)\u0007\"\u00027\u0001\r\u0003i\u0017!\u0004\u0013d_2|g\u000e\n2tY\u0006\u001c\b.\u0006\u0002ocR\u0011q\u000e\u001e\u000b\u0003aJ\u0004\"AG9\u0005\u000b\u001d\\'\u0019A\u000f\t\u000b\u0019[\u0007\u0019A:\u0011\u000b)A\u0015\u0004\u001d9\t\u000b}[\u0007\u0019\u00019\t\u000bY\u0004a\u0011A<\u0002\u0011\u0019|G\u000e\u001a'fMR,\"\u0001_>\u0015\u0005etHC\u0001>}!\tQ2\u0010B\u0003hk\n\u0007Q\u0004C\u0003Gk\u0002\u0007Q\u0010E\u0003\u000b\u0011jL\"\u0010C\u0003`k\u0002\u0007!\u0010C\u0004\u0002\u0002\u00011\t!a\u0001\u0002\u0013\u0019|G\u000e\u001a*jO\"$X\u0003BA\u0003\u0003\u0017!B!a\u0002\u0002\u0012Q!\u0011\u0011BA\u0007!\rQ\u00121\u0002\u0003\u0006O~\u0014\r!\b\u0005\u0007\r~\u0004\r!a\u0004\u0011\u000f)A\u0015$!\u0003\u0002\n!1ql a\u0001\u0003\u0013Aq!!\u0006\u0001\r\u0003\t9\"A\u0005bO\u001e\u0014XmZ1uKV!\u0011\u0011DA\u0010)\u0011\tY\"!\f\u0015\r\u0005u\u0011\u0011EA\u0014!\rQ\u0012q\u0004\u0003\u0007O\u0006M!\u0019A\u000f\t\u0011\u0005\r\u00121\u0003a\u0001\u0003K\tQa]3r_B\u0004rA\u0003%\u0002\u001ee\ti\u0002\u0003\u0005\u0002*\u0005M\u0001\u0019AA\u0016\u0003\u0019\u0019w.\u001c2paBA!\u0002SA\u000f\u0003;\ti\u0002\u0003\u0005`\u0003'!\t\u0019AA\u0018!\u0015Q\u0011\u0011GA\u000f\u0013\r\t\u0019\u0004\u0002\u0002\ty\tLh.Y7f}!9\u0011q\u0007\u0001\u0007\u0002\u0005e\u0012a\u0003:fIV\u001cWMU5hQR,B!a\u000f\u0002@Q!\u0011QHA!!\rQ\u0012q\b\u0003\u0007O\u0006U\"\u0019\u0001#\t\u000f\u0019\u000b)\u00041\u0001\u0002DA9!\u0002S\r\u0002>\u0005u\u0002bBA$\u0001\u0019\u0005\u0011\u0011J\u0001\u0011e\u0016$WoY3MK\u001a$x\n\u001d;j_:,B!a\u0013\u0002RQ!\u0011QJA*!\u0011Qq*a\u0014\u0011\u0007i\t\t\u0006\u0002\u0004h\u0003\u000b\u0012\r\u0001\u0012\u0005\b\r\u0006\u0015\u0003\u0019AA+!\u001dQ\u0001*a\u0014\u001a\u0003\u001fBq!!\u0017\u0001\r\u0003\tY&A\tsK\u0012,8-\u001a*jO\"$x\n\u001d;j_:,B!!\u0018\u0002dQ!\u0011qLA3!\u0011Qq*!\u0019\u0011\u0007i\t\u0019\u0007\u0002\u0004h\u0003/\u0012\r\u0001\u0012\u0005\b\r\u0006]\u0003\u0019AA4!\u001dQ\u0001*GA1\u0003CBq!a\u001b\u0001\r\u0003\ti'A\u0003d_VtG\u000fF\u00025\u0003_B\u0001\"!\u001d\u0002j\u0001\u0007\u00111O\u0001\u0002aB!!bF\r(\u0011\u001d\t9\b\u0001D\u0001\u0003s\n1a];n+\u0011\tY(a \u0015\t\u0005u\u0014\u0011\u0011\t\u00045\u0005}DAB\"\u0002v\t\u0007A\t\u0003\u0005\u0002\u0004\u0006U\u00049AAC\u0003\rqW/\u001c\t\u0007\u0003\u000f\u000bi)! \u000f\u0007)\tI)C\u0002\u0002\f\u0012\tq\u0001]1dW\u0006<W-\u0003\u0003\u0002\u0010\u0006E%a\u0002(v[\u0016\u0014\u0018n\u0019\u0006\u0004\u0003\u0017#\u0001bBAK\u0001\u0019\u0005\u0011qS\u0001\baJ|G-^2u+\u0011\tI*!(\u0015\t\u0005m\u0015q\u0014\t\u00045\u0005uEAB\"\u0002\u0014\n\u0007A\t\u0003\u0005\u0002\u0004\u0006M\u00059AAQ!\u0019\t9)!$\u0002\u001c\"9\u0011Q\u0015\u0001\u0007\u0002\u0005\u001d\u0016aA7j]V!\u0011\u0011VA\\)\rI\u00121\u0016\u0005\t\u0003[\u000b\u0019\u000bq\u0001\u00020\u0006\u0019qN\u001d3\u0011\r\u0005\u001d\u0015\u0011WA[\u0013\u0011\t\u0019,!%\u0003\u0011=\u0013H-\u001a:j]\u001e\u00042AGA\\\t\u0019\u0019\u00151\u0015b\u0001\t\"9\u00111\u0018\u0001\u0007\u0002\u0005u\u0016aA7bqV!\u0011qXAd)\rI\u0012\u0011\u0019\u0005\t\u0003[\u000bI\fq\u0001\u0002DB1\u0011qQAY\u0003\u000b\u00042AGAd\t\u0019\u0019\u0015\u0011\u0018b\u0001\t\"9\u00111\u001a\u0001\u0007\u0002\u00055\u0017!B7bq\nKX\u0003BAh\u00037$B!!5\u0002^R\u0019\u0011$a5\t\u0011\u0005U\u0017\u0011\u001aa\u0002\u0003/\f1aY7q!\u0019\t9)!-\u0002ZB\u0019!$a7\u0005\r\u001d\fIM1\u0001\u001e\u0011\u001d)\u0012\u0011\u001aa\u0001\u0003?\u0004RAC\f\u001a\u00033Dq!a9\u0001\r\u0003\t)/A\u0003nS:\u0014\u00150\u0006\u0003\u0002h\u0006EH\u0003BAu\u0003g$2!GAv\u0011!\t).!9A\u0004\u00055\bCBAD\u0003c\u000by\u000fE\u0002\u001b\u0003c$aaZAq\u0005\u0004i\u0002bB\u000b\u0002b\u0002\u0007\u0011Q\u001f\t\u0006\u0015]I\u0012q\u001e\u0005\b\u0003s\u0004a\u0011AA~\u0003\u00191wN]1mYR\u0019q%!@\t\u0011\u0005E\u0014q\u001fa\u0001\u0003gBc!!@\u0003\u0002\t\u001d\u0001c\u0001\u0006\u0003\u0004%\u0019!Q\u0001\u0003\u0003\u001d\u0011,\u0007O]3dCR,GMT1nKF:qD!\u0003\u0003\u0010\t}\u0002c\u0001\u0006\u0003\f%\u0019!Q\u0002\u0003\u0003\rMKXNY8mc%\u0019#\u0011\u0003B\f\u0005[\u0011I\u0002\u0006\u0003\u0003\n\tM\u0001b\u0002B\u000b\r\u0001\u0007!qD\u0001\u0005]\u0006lW-\u0003\u0003\u0003\u001a\tm\u0011!B1qa2L(b\u0001B\u000f\t\u000511+_7c_2\u0004BA!\t\u0003(9\u0019!Ba\t\n\u0007\t\u0015B!\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0005S\u0011YC\u0001\u0004TiJLgn\u001a\u0006\u0004\u0005K!\u0011'C\u0012\u00030\tm\"Q\bB\u000f\u001d\u0011\u0011\tDa\u000f\u000f\t\tM\"\u0011H\u0007\u0003\u0005kQ1Aa\u000e\u0007\u0003\u0019a$o\\8u}%\tQ!C\u0002\u0003\u001e\u0011\td\u0001\nB\u0019\u0005s)\u0011'B\u0013\u0003B\t\rsB\u0001B\"C\t\u0011)%\u0001\u0003qe\u0016$\u0007b\u0002B%\u0001\u0019\u0005!1J\u0001\u0007KbL7\u000f^:\u0015\u0007\u001d\u0012i\u0005\u0003\u0005\u0002r\t\u001d\u0003\u0019AA:Q\u0019\u0011iE!\u0001\u0003RE:qD!\u0003\u0003T\te\u0013'C\u0012\u0003\u0012\t]!Q\u000bB\rc%\u0019#q\u0006B\u001e\u0005/\u0012i\"\r\u0004%\u0005c\u0011I$B\u0019\u0006K\t\u0005#1\t\u0005\b\u0005;\u0002a\u0011\u0001B0\u0003\u00111\u0017N\u001c3\u0015\t\t\u0005$1\r\t\u0004\u0015=K\u0002\u0002CA9\u00057\u0002\r!a\u001d)\r\t\r$\u0011\u0001B4c\u001dy\"\u0011\u0002B5\u0005_\n\u0014b\tB\t\u0005/\u0011YG!\u00072\u0013\r\u0012yCa\u000f\u0003n\tu\u0011G\u0002\u0013\u00032\teR!M\u0003&\u0005\u0003\u0012\u0019\u0005C\u0004\u0003t\u00011\tA!\u001e\u0002\u0017\r|\u0007/\u001f+p\u0003J\u0014\u0018-_\u000b\u0005\u0005o\u0012)\tF\u0002\u0012\u0005sB\u0001Ba\u001f\u0003r\u0001\u0007!QP\u0001\u0003qN\u0004RA\u0003B@\u0005\u0007K1A!!\u0005\u0005\u0015\t%O]1z!\rQ\"Q\u0011\u0003\u0007O\nE$\u0019\u0001#\t\u000f\tM\u0004A\"\u0001\u0003\nV!!1\u0012BJ)\u0015\t\"Q\u0012BK\u0011!\u0011YHa\"A\u0002\t=\u0005#\u0002\u0006\u0003\u0000\tE\u0005c\u0001\u000e\u0003\u0014\u00121qMa\"C\u0002\u0011CqAa&\u0003\b\u0002\u0007A'A\u0003ti\u0006\u0014H\u000fC\u0004\u0003t\u00011\tAa'\u0016\t\tu%Q\u0015\u000b\b#\t}%q\u0015BU\u0011!\u0011YH!'A\u0002\t\u0005\u0006#\u0002\u0006\u0003\u0000\t\r\u0006c\u0001\u000e\u0003&\u00121qM!'C\u0002\u0011CqAa&\u0003\u001a\u0002\u0007A\u0007C\u0004\u0003,\ne\u0005\u0019\u0001\u001b\u0002\u00071,g\u000eC\u0004\u00030\u00021\tA!-\u0002\u00115\\7\u000b\u001e:j]\u001e$\u0002Ba\b\u00034\nU&\u0011\u0018\u0005\t\u0005/\u0013i\u000b1\u0001\u0003 !A!q\u0017BW\u0001\u0004\u0011y\"A\u0002tKBD\u0001Ba/\u0003.\u0002\u0007!qD\u0001\u0004K:$\u0007b\u0002BX\u0001\u0019\u0005!q\u0018\u000b\u0005\u0005?\u0011\t\r\u0003\u0005\u00038\nu\u0006\u0019\u0001B\u0010\u0011\u001d\u0011y\u000b\u0001D\u0001\u0005\u000b,\"Aa\b\t\u000f\t%\u0007A\"\u0001\u0003L\u00069Ao\\!se\u0006LX\u0003\u0002Bg\u0005'$BAa4\u0003VB)!Ba \u0003RB\u0019!Da5\u0005\r\r\u00139M1\u0001E\u0011)\u00119Na2\u0002\u0002\u0003\u000f!\u0011\\\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004C\u0002Bn\u0005C\u0014\t.\u0004\u0002\u0003^*\u0019!q\u001c\u0003\u0002\u000fI,g\r\\3di&!!1\u001dBo\u0005!\u0019E.Y:t)\u0006<\u0007b\u0002Bt\u0001\u0019\u0005!\u0011^\u0001\u0007i>d\u0015n\u001d;\u0016\u0005\t-\b#BAD\u0005[L\u0012\u0002\u0002Bx\u0003#\u0013A\u0001T5ti\"9!1\u001f\u0001\u0007\u0002\tU\u0018\u0001\u0004;p\u0013:$W\r_3e'\u0016\fXC\u0001B|!\u0015\u0011IPa@\u001a\u001b\t\u0011YPC\u0002\u0003~\n\t\u0011\"[7nkR\f'\r\\3\n\t\r\u0005!1 \u0002\u000b\u0013:$W\r_3e'\u0016\f\bbBB\u0003\u0001\u0019\u00051qA\u0001\ti>\u001cFO]3b[V\u00111\u0011\u0002\t\u0006\u0003\u000f\u001bY!G\u0005\u0005\u0007\u001b\t\tJ\u0001\u0004TiJ,\u0017-\u001c\u0005\b\u0007#\u0001a\u0011AB\n\u0003)!x.\u0013;fe\u0006$xN]\u000b\u0003\u0007+\u0001BALB\f3%\u00191\u0011\u0004\u0002\u0003\u0011%#XM]1u_JDqa!\b\u0001\r\u0003\u0019y\"\u0001\u0005u_\n+hMZ3s+\u0011\u0019\tc!\r\u0016\u0005\r\r\u0002CBB\u0013\u0007W\u0019y#\u0004\u0002\u0004()\u00191\u0011\u0006\u0002\u0002\u000f5,H/\u00192mK&!1QFB\u0014\u0005\u0019\u0011UO\u001a4feB\u0019!d!\r\u0005\r\r\u001bYB1\u0001E\u0011\u001d\u0019)\u0004\u0001D\u0001\u0007o\tQ\u0002^8Ue\u00064XM]:bE2,WCAB\u001d!\u0011q31H\r\n\u0007\ru\"A\u0001\bHK:$&/\u0019<feN\f'\r\\3\t\u000f\r\u0005\u0003A\"\u0001\u0004D\u0005QAo\\%uKJ\f'\r\\3\u0016\u0005\r\u0015\u0003\u0003\u0002\u0018\u0004HeI1a!\u0013\u0003\u0005-9UM\\%uKJ\f'\r\\3\t\u000f\r5\u0003A\"\u0001\u0004P\u0005)Ao\\*fcV\u00111\u0011\u000b\t\u0005]\rM\u0013$C\u0002\u0004V\t\u0011aaR3o'\u0016\f\bbBB-\u0001\u0019\u000511L\u0001\u0006i>\u001cV\r^\u000b\u0005\u0007;\u001a9'\u0006\u0002\u0004`A)af!\u0019\u0004f%\u001911\r\u0002\u0003\r\u001d+gnU3u!\rQ2q\r\u0003\u0007\u0007\u000e]#\u0019\u0001#\t\u000f\r-\u0004A\"\u0001\u0004n\u0005)Ao\\'baV11qNB=\u0007\u007f\"Ba!\u001d\u0004\u0004B9afa\u001d\u0004x\ru\u0014bAB;\u0005\t1q)\u001a8NCB\u00042AGB=\t\u001d\u0019Yh!\u001bC\u0002u\u0011\u0011a\u0013\t\u00045\r}DaBBA\u0007S\u0012\r!\b\u0002\u0002-\"A1QQB5\u0001\b\u00199)\u0001\u0002fmB9!\u0011EBE3\r5\u0015\u0002BBF\u0005W\u0011\u0001\u0003\n7fgN$3m\u001c7p]\u0012bWm]:\u0011\u000f)\u0019yia\u001e\u0004~%\u00191\u0011\u0013\u0003\u0003\rQ+\b\u000f\\33\u0011\u001d\u0019)\n\u0001D\u0001\u0007/\u000b\u0001\u0002^8WK\u000e$xN]\u000b\u0003\u00073\u0003R!a\"\u0004\u001cfIAa!(\u0002\u0012\n1a+Z2u_JDqa!)\u0001\r\u0003\u0019\u0019+\u0001\u0002u_V!1QUBU)\u0011\u00199ka2\u0011\u000bi\u0019Ika-\u0005\u0011\r-6q\u0014b\u0001\u0007[\u00131aQ8m+\ri2q\u0016\u0003\b\u0007c\u001bIK1\u0001\u001e\u0005\u0005y&fA\r\u00046.\u00121q\u0017\t\u0005\u0007s\u001b\u0019-\u0004\u0002\u0004<*!1QXB`\u0003%)hn\u00195fG.,GMC\u0002\u0004B\u0012\t!\"\u00198o_R\fG/[8o\u0013\u0011\u0019)ma/\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\r\u0003\u0005\u0004J\u000e}\u00059ABf\u0003\r\u0019'M\u001a\t\t\u0007\u001b\u001c\u0019NH\r\u0004(6\u00111q\u001a\u0006\u0004\u0007#\u0014\u0011aB4f]\u0016\u0014\u0018nY\u0005\u0005\u0007+\u001cyM\u0001\u0007DC:\u0014U/\u001b7e\rJ|W\u000e")
public interface GenTraversableOnce<A> {
    public <U> void foreach(Function1<A, U> var1);

    public boolean hasDefiniteSize();

    public TraversableOnce<A> seq();

    public int size();

    public boolean isEmpty();

    public boolean nonEmpty();

    public boolean isTraversableAgain();

    public <A1> A1 reduce(Function2<A1, A1, A1> var1);

    public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> var1);

    public <A1> A1 fold(A1 var1, Function2<A1, A1, A1> var2);

    public <B> B $div$colon(B var1, Function2<B, A, B> var2);

    public <B> B $colon$bslash(B var1, Function2<A, B, B> var2);

    public <B> B foldLeft(B var1, Function2<B, A, B> var2);

    public <B> B foldRight(B var1, Function2<A, B, B> var2);

    public <B> B aggregate(Function0<B> var1, Function2<B, A, B> var2, Function2<B, B, B> var3);

    public <B> B reduceRight(Function2<A, B, B> var1);

    public <B> Option<B> reduceLeftOption(Function2<B, A, B> var1);

    public <B> Option<B> reduceRightOption(Function2<A, B, B> var1);

    public int count(Function1<A, Object> var1);

    public <A1> A1 sum(Numeric<A1> var1);

    public <A1> A1 product(Numeric<A1> var1);

    public <A1> A min(Ordering<A1> var1);

    public <A1> A max(Ordering<A1> var1);

    public <B> A maxBy(Function1<A, B> var1, Ordering<B> var2);

    public <B> A minBy(Function1<A, B> var1, Ordering<B> var2);

    public boolean forall(Function1<A, Object> var1);

    public boolean exists(Function1<A, Object> var1);

    public Option<A> find(Function1<A, Object> var1);

    public <B> void copyToArray(Object var1);

    public <B> void copyToArray(Object var1, int var2);

    public <B> void copyToArray(Object var1, int var2, int var3);

    public String mkString(String var1, String var2, String var3);

    public String mkString(String var1);

    public String mkString();

    public <A1> Object toArray(ClassTag<A1> var1);

    public List<A> toList();

    public IndexedSeq<A> toIndexedSeq();

    public Stream<A> toStream();

    public Iterator<A> toIterator();

    public <A1> Buffer<A1> toBuffer();

    public GenTraversable<A> toTraversable();

    public GenIterable<A> toIterable();

    public GenSeq<A> toSeq();

    public <A1> GenSet<A1> toSet();

    public <K, V> GenMap<K, V> toMap(Predef$.less.colon.less<A, Tuple2<K, V>> var1);

    public Vector<A> toVector();

    public <Col> Col to(CanBuildFrom<Nothing$, A, Col> var1);
}

