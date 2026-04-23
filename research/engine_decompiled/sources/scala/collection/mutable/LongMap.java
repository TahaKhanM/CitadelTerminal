/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import java.util.Arrays;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Iterator$class;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.AbstractMap;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.LongMap$;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@ScalaSignature(bytes="\u0006\u0001\r\u0005d\u0001B\u0001\u0003\u0005%\u0011q\u0001T8oO6\u000b\u0007O\u0003\u0002\u0004\t\u00059Q.\u001e;bE2,'BA\u0003\u0007\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u000f\u0005)1oY1mC\u000e\u0001QC\u0001\u0006\u0016'\u0015\u00011BH\u0011&!\u0011aQbD\n\u000e\u0003\tI!A\u0004\u0002\u0003\u0017\u0005\u00137\u000f\u001e:bGRl\u0015\r\u001d\t\u0003!Ei\u0011AB\u0005\u0003%\u0019\u0011A\u0001T8oOB\u0011A#\u0006\u0007\u0001\t\u00151\u0002A1\u0001\u0018\u0005\u00051\u0016C\u0001\r\u001c!\t\u0001\u0012$\u0003\u0002\u001b\r\t9aj\u001c;iS:<\u0007C\u0001\t\u001d\u0013\tibAA\u0002B]f\u0004B\u0001D\u0010\u0010'%\u0011\u0001E\u0001\u0002\u0004\u001b\u0006\u0004\b#\u0002\u0007#\u001fM!\u0013BA\u0012\u0003\u0005\u001di\u0015\r\u001d'jW\u0016\u00042\u0001\u0004\u0001\u0014!\t\u0001b%\u0003\u0002(\r\ta1+\u001a:jC2L'0\u00192mK\"A\u0011\u0006\u0001B\u0001B\u0003%!&\u0001\u0007eK\u001a\fW\u000f\u001c;F]R\u0014\u0018\u0010\u0005\u0003\u0011W=\u0019\u0012B\u0001\u0017\u0007\u0005%1UO\\2uS>t\u0017\u0007\u0003\u0005/\u0001\t\u0005\t\u0015!\u00030\u0003EIg.\u001b;jC2\u0014UO\u001a4feNK'0\u001a\t\u0003!AJ!!\r\u0004\u0003\u0007%sG\u000f\u0003\u00054\u0001\t\u0005\t\u0015!\u00035\u0003%Ig.\u001b;CY\u0006t7\u000e\u0005\u0002\u0011k%\u0011aG\u0002\u0002\b\u0005>|G.Z1o\u0011\u0019A\u0004\u0001\"\u0001\u0005s\u00051A(\u001b8jiz\"B\u0001\n\u001e<y!)\u0011f\u000ea\u0001U!)af\u000ea\u0001_!)1g\u000ea\u0001i!)\u0001\b\u0001C\u0001}Q\tA\u0005C\u00039\u0001\u0011\u0005\u0001\t\u0006\u0002%\u0003\")\u0011f\u0010a\u0001U!)\u0001\b\u0001C\u0001\u0007R\u0011A\u0005\u0012\u0005\u0006]\t\u0003\ra\f\u0005\u0006q\u0001!\tA\u0012\u000b\u0004I\u001dC\u0005\"B\u0015F\u0001\u0004Q\u0003\"\u0002\u0018F\u0001\u0004y\u0003B\u0002&\u0001A\u0003&q&\u0001\u0003nCN\\\u0007B\u0002'\u0001A\u0003&q&A\u0005fqR\u0014\u0018mS3zg\"1a\n\u0001Q!\n=\u000b\u0011B_3s_Z\u000bG.^3\u0011\u0005A\u0001\u0016BA)\u0007\u0005\u0019\te.\u001f*fM\"11\u000b\u0001Q!\n=\u000b\u0001\"\\5o-\u0006dW/\u001a\u0005\u0007+\u0002\u0001\u000b\u0015B\u0018\u0002\u000b}\u001b\u0018N_3\t\r]\u0003\u0001\u0015)\u00030\u0003\u001dyf/Y2b]RDa!\u0017\u0001!B\u0013Q\u0016!B0lKf\u001c\bc\u0001\t\\\u001f%\u0011AL\u0002\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\u0007=\u0002\u0001\u000b\u0015B0\u0002\u000f}3\u0018\r\\;fgB\u0019\u0001cW(\t\r\u0005\u0004\u0001\u0015\"\u0003c\u0003E!WMZ1vYRLe.\u001b;jC2L'0\u001a\u000b\u0003G\u001a\u0004\"\u0001\u00053\n\u0005\u00154!\u0001B+oSRDQa\u001a1A\u0002=\n\u0011A\u001c\u0005\u0007S\u0002!\t\u0001\u00026\u0002\u0019%t\u0017\u000e^5bY&TX\rV8\u0015\u0013\r\\Wn\\9tk^L\b\"\u00027i\u0001\u0004y\u0013!A7\t\u000b9D\u0007\u0019A\u0018\u0002\u0005\u0015\\\u0007\"\u00029i\u0001\u0004y\u0015A\u0001>w\u0011\u0015\u0011\b\u000e1\u0001P\u0003\tig\u000fC\u0003uQ\u0002\u0007q&\u0001\u0002tu\")a\u000f\u001ba\u0001_\u0005\u0011ao\u0019\u0005\u0006q\"\u0004\rAW\u0001\u0003WjDQA\u001f5A\u0002}\u000b!A\u001e>\t\u000bq\u0004A\u0011I?\u0002\tML'0Z\u000b\u0002_!1q\u0010\u0001C!\u0003\u0003\tQ!Z7qif,\u0012\u0001\n\u0005\b\u0003\u000b\u0001A\u0011BA\u0004\u0003)IWNY1mC:\u001cW\rZ\u000b\u0002i!9\u00111\u0002\u0001\u0005\n\u00055\u0011a\u0002;p\u0013:$W\r\u001f\u000b\u0004_\u0005=\u0001bBA\t\u0003\u0013\u0001\raD\u0001\u0002W\"9\u0011Q\u0003\u0001\u0005\n\u0005]\u0011!C:fK.,U\u000e\u001d;z)\ry\u0013\u0011\u0004\u0005\b\u0003#\t\u0019\u00021\u0001\u0010\u0011\u001d\ti\u0002\u0001C\u0005\u0003?\t\u0011b]3fW\u0016sGO]=\u0015\u0007=\n\t\u0003C\u0004\u0002\u0012\u0005m\u0001\u0019A\b\t\u000f\u0005\u0015\u0002\u0001\"\u0003\u0002(\u0005y1/Z3l\u000b:$(/_(s\u001fB,g\u000eF\u00020\u0003SAq!!\u0005\u0002$\u0001\u0007q\u0002C\u0004\u0002.\u0001!\t%a\f\u0002\u0011\r|g\u000e^1j]N$2\u0001NA\u0019\u0011\u001d\t\u0019$a\u000bA\u0002=\t1a[3z\u0011\u001d\t9\u0004\u0001C!\u0003s\t1aZ3u)\u0011\tY$!\u0011\u0011\tA\tidE\u0005\u0004\u0003\u007f1!AB(qi&|g\u000eC\u0004\u00024\u0005U\u0002\u0019A\b\t\u000f\u0005\u0015\u0003\u0001\"\u0011\u0002H\u0005Iq-\u001a;Pe\u0016c7/Z\u000b\u0005\u0003\u0013\ni\u0005\u0006\u0004\u0002L\u0005M\u0013Q\u000b\t\u0004)\u00055C\u0001CA(\u0003\u0007\u0012\r!!\u0015\u0003\u0005Y\u000b\u0014CA\n\u001c\u0011\u001d\t\u0019$a\u0011A\u0002=A\u0011\"a\u0016\u0002D\u0011\u0005\r!!\u0017\u0002\u000f\u0011,g-Y;miB)\u0001#a\u0017\u0002L%\u0019\u0011Q\f\u0004\u0003\u0011q\u0012\u0017P\\1nKzBq!!\u0019\u0001\t\u0003\n\u0019'A\bhKR|%/\u00127tKV\u0003H-\u0019;f)\u0015\u0019\u0012QMA4\u0011\u001d\t\u0019$a\u0018A\u0002=A\u0011\"!\u001b\u0002`\u0011\u0005\r!a\u001b\u0002\u0019\u0011,g-Y;miZ\u000bG.^3\u0011\tA\tYf\u0005\u0005\b\u0003_\u0002A\u0011AA9\u0003%9W\r^(s\u001dVdG\u000eF\u0002\u0014\u0003gBq!a\r\u0002n\u0001\u0007q\u0002C\u0004\u0002x\u0001!\t%!\u001f\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007M\tY\bC\u0004\u00024\u0005U\u0004\u0019A\b\t\u000f\u0005]\u0003\u0001\"\u0011\u0002\u0000Q\u00191#!!\t\u000f\u0005M\u0012Q\u0010a\u0001\u001f!9\u0011Q\u0011\u0001\u0005\n\u0005\u001d\u0015A\u0002:fa\u0006\u001c7\u000eF\u0002d\u0003\u0013Cq!a#\u0002\u0004\u0002\u0007q&A\u0004oK^l\u0015m]6\t\u000f\u0005\u0015\u0005\u0001\"\u0001\u0002\u0010R\t1\rC\u0004\u0002\u0014\u0002!\t%!&\u0002\u0007A,H\u000f\u0006\u0004\u0002<\u0005]\u0015\u0011\u0014\u0005\b\u0003g\t\t\n1\u0001\u0010\u0011\u001d\tY*!%A\u0002M\tQA^1mk\u0016Dq!a(\u0001\t\u0003\n\t+\u0001\u0004va\u0012\fG/\u001a\u000b\u0006G\u0006\r\u0016Q\u0015\u0005\b\u0003g\ti\n1\u0001\u0010\u0011\u001d\tY*!(A\u0002MAq!!+\u0001\t\u0003\tY+\u0001\u0005%a2,8\u000fJ3r)\u0019\ti+a,\u000226\t\u0001\u0001C\u0004\u00024\u0005\u001d\u0006\u0019A\b\t\u000f\u0005m\u0015q\u0015a\u0001'!9\u0011\u0011\u0016\u0001\u0005\u0002\u0005UF\u0003BAW\u0003oC\u0001\"!/\u00024\u0002\u0007\u00111X\u0001\u0003WZ\u0004R\u0001EA_\u001fMI1!a0\u0007\u0005\u0019!V\u000f\u001d7fe!9\u00111\u0019\u0001\u0005\u0002\u0005\u0015\u0017!\u0003\u0013nS:,8\u000fJ3r)\u0011\ti+a2\t\u000f\u0005M\u0012\u0011\u0019a\u0001\u001f!9\u00111\u001a\u0001\u0005\u0002\u00055\u0017\u0001C5uKJ\fGo\u001c:\u0016\u0005\u0005=\u0007CBAi\u0003'\fY,D\u0001\u0005\u0013\r\t)\u000e\u0002\u0002\t\u0013R,'/\u0019;pe\"9\u0011\u0011\u001c\u0001\u0005B\u0005m\u0017a\u00024pe\u0016\f7\r[\u000b\u0005\u0003;\f9\u000fF\u0002d\u0003?D\u0001\"!9\u0002X\u0002\u0007\u00111]\u0001\u0002MB1\u0001cKA^\u0003K\u00042\u0001FAt\t\u001d\tI/a6C\u0002]\u0011\u0011!\u0016\u0005\u0007\u0003[\u0004A\u0011\t \u0002\u000b\rdwN\\3\t\u000f\u0005E\b\u0001\"\u0001\u0002t\u0006Qam\u001c:fC\u000eD7*Z=\u0016\t\u0005U\u0018Q \u000b\u0004G\u0006]\b\u0002CAq\u0003_\u0004\r!!?\u0011\u000bAYs\"a?\u0011\u0007Q\ti\u0010B\u0004\u0002\u0000\u0006=(\u0019A\f\u0003\u0003\u0005CqAa\u0001\u0001\t\u0003\u0011)!\u0001\u0007g_J,\u0017m\u00195WC2,X-\u0006\u0003\u0003\b\t=AcA2\u0003\n!A\u0011\u0011\u001dB\u0001\u0001\u0004\u0011Y\u0001E\u0003\u0011WM\u0011i\u0001E\u0002\u0015\u0005\u001f!q!a@\u0003\u0002\t\u0007q\u0003C\u0004\u0003\u0014\u0001!\tA!\u0006\u0002\u00195\f\u0007OV1mk\u0016\u001chj\\<\u0016\t\t]!Q\u0004\u000b\u0005\u00053\u0011y\u0002\u0005\u0003\r\u0001\tm\u0001c\u0001\u000b\u0003\u001e\u00119\u0011q\nB\t\u0005\u00049\u0002\u0002CAq\u0005#\u0001\rA!\t\u0011\u000bAY3Ca\u0007\t\u000f\t\u0015\u0002\u0001\"\u0001\u0003(\u0005yAO]1og\u001a|'/\u001c,bYV,7\u000f\u0006\u0003\u0002.\n%\u0002\u0002CAq\u0005G\u0001\rAa\u000b\u0011\tAY3cE\u0004\b\u0005_\u0011\u0001\u0012\u0001B\u0019\u0003\u001dauN\\4NCB\u00042\u0001\u0004B\u001a\r\u0019\t!\u0001#\u0001\u00036M!!1G(&\u0011\u001dA$1\u0007C\u0001\u0005s!\"A!\r\t\u0015\tu\"1\u0007b\u0001\n\u001b\u0011y$A\u0005J]\u0012,\u00070T1tWV\u0011!\u0011I\b\u0003\u0005\u0007jBaP\u0000\u0000\u0000$I!q\tB\u001aA\u00035!\u0011I\u0001\u000b\u0013:$W\r_'bg.\u0004\u0003B\u0003B&\u0005g\u0011\r\u0011\"\u0004\u0003N\u0005QQ*[:tS:<')\u001b;\u0016\u0005\t=sB\u0001B);\u0011\u0001\t\u0001\u0001\u0001\t\u0013\tU#1\u0007Q\u0001\u000e\t=\u0013aC'jgNLgn\u001a\"ji\u0002B!B!\u0017\u00034\t\u0007IQ\u0002B.\u0003%1\u0016mY1oi\nKG/\u0006\u0002\u0003^=\u0011!qL\u000f\u0005\u0001\u0002\u0001\u0001\u0001C\u0005\u0003d\tM\u0002\u0015!\u0004\u0003^\u0005Qa+Y2b]R\u0014\u0015\u000e\u001e\u0011\t\u0015\t\u001d$1\u0007b\u0001\n\u001b\u0011I'\u0001\u0006NSN\u001ch+Y2b]R,\"Aa\u001b\u0010\u0005\t5T\u0004\u0002a\u0001\u0001\u0001A\u0011B!\u001d\u00034\u0001\u0006iAa\u001b\u0002\u00175K7o\u001d,bG\u0006tG\u000f\t\u0005\u000b\u0005k\u0012\u0019D1A\u0005\n\t]\u0014\u0001E3yG\u0016\u0004H/[8o\t\u00164\u0017-\u001e7u+\t\u0011I\b\u0005\u0003\u0011W=A\u0002\"\u0003B?\u0005g\u0001\u000b\u0011\u0002B=\u0003E)\u0007pY3qi&|g\u000eR3gCVdG\u000f\t\u0005\t\u0005\u0003\u0013\u0019\u0004b\u0001\u0003\u0004\u0006a1-\u00198Ck&dGM\u0012:p[V1!Q\u0011BL\u0005;+\"Aa\"\u0011\u0015\t%%q\u0012BJ\u00053\u0013y*\u0004\u0002\u0003\f*\u0019!Q\u0012\u0003\u0002\u000f\u001d,g.\u001a:jG&!!\u0011\u0013BF\u00051\u0019\u0015M\u001c\"vS2$gI]8n!\u0011a\u0001A!&\u0011\u0007Q\u00119\n\u0002\u0004\u0017\u0005\u007f\u0012\ra\u0006\t\u0007!\u0005uvBa'\u0011\u0007Q\u0011i\nB\u0004\u0002j\n}$\u0019A\f\u0011\t1\u0001!1\u0014\u0004\b\u0005G\u0013\u0019D\u0001BS\u00059auN\\4NCB\u0014U/\u001b7eKJ,BAa*\u00034N)!\u0011U(\u0003*B9ABa+\u00030\nU\u0016b\u0001BW\u0005\t9!)^5mI\u0016\u0014\bC\u0002\t\u0002>>\u0011\t\fE\u0002\u0015\u0005g#aA\u0006BQ\u0005\u00049\u0002\u0003\u0002\u0007\u0001\u0005cCq\u0001\u000fBQ\t\u0003\u0011I\f\u0006\u0002\u0003<B1!Q\u0018BQ\u0005ck!Aa\r\t\u0017\t\u0005'\u0011\u0015a\u0001\n\u0003!!1Y\u0001\u0006K2,Wn]\u000b\u0003\u0005kC1Ba2\u0003\"\u0002\u0007I\u0011\u0001\u0003\u0003J\u0006IQ\r\\3ng~#S-\u001d\u000b\u0004G\n-\u0007B\u0003Bg\u0005\u000b\f\t\u00111\u0001\u00036\u0006\u0019\u0001\u0010J\u0019\t\u0013\tE'\u0011\u0015Q!\n\tU\u0016AB3mK6\u001c\b\u0005\u0003\u0005\u0002*\n\u0005F\u0011\u0001Bk)\u0011\u00119N!7\u000e\u0005\t\u0005\u0006\u0002\u0003Bn\u0005'\u0004\rAa,\u0002\u000b\u0015tGO]=\t\u0011\t}'\u0011\u0015C\u0001\u0003\u001f\u000bQa\u00197fCJD\u0001Ba9\u0003\"\u0012\u0005!Q]\u0001\u0007e\u0016\u001cX\u000f\u001c;\u0015\u0005\tU\u0006\u0002CA<\u0005g!\tA!;\u0016\t\t-(\u0011\u001f\u000b\u0005\u0005[\u0014\u0019\u0010\u0005\u0003\r\u0001\t=\bc\u0001\u000b\u0003r\u00121aCa:C\u0002]A\u0001B!1\u0003h\u0002\u0007!Q\u001f\t\u0006!\t](1`\u0005\u0004\u0005s4!A\u0003\u001fsKB,\u0017\r^3e}A1\u0001#!0\u0010\u0005_Dqa B\u001a\t\u0003\u0011y0\u0006\u0003\u0004\u0002\r\u001dQCAB\u0002!\u0011a\u0001a!\u0002\u0011\u0007Q\u00199\u0001\u0002\u0004\u0017\u0005{\u0014\ra\u0006\u0005\t\u0007\u0017\u0011\u0019\u0004\"\u0001\u0004\u000e\u0005Yq/\u001b;i\t\u00164\u0017-\u001e7u+\u0011\u0019ya!\u0006\u0015\t\rE1q\u0003\t\u0005\u0019\u0001\u0019\u0019\u0002E\u0002\u0015\u0007+!aAFB\u0005\u0005\u00049\u0002\u0002CA,\u0007\u0013\u0001\ra!\u0007\u0011\u000bAYsba\u0005\t\u0011\ru!1\u0007C\u0001\u0007?\tqA\u001a:p[jK\u0007/\u0006\u0003\u0004\"\r\u001dBCBB\u0012\u0007S\u0019i\u0003\u0005\u0003\r\u0001\r\u0015\u0002c\u0001\u000b\u0004(\u00111aca\u0007C\u0002]Aqaa\u000b\u0004\u001c\u0001\u0007!,\u0001\u0003lKf\u001c\b\u0002CB\u0018\u00077\u0001\ra!\r\u0002\rY\fG.^3t!\u0011\u00012l!\n\t\u0011\ru!1\u0007C\u0001\u0007k)Baa\u000e\u0004>Q11\u0011HB \u0007\u000f\u0002B\u0001\u0004\u0001\u0004<A\u0019Ac!\u0010\u0005\rY\u0019\u0019D1\u0001\u0018\u0011!\u0019Yca\rA\u0002\r\u0005\u0003\u0003\u0002\u0007\u0004D=I1a!\u0012\u0003\u0005!IE/\u001a:bE2,\u0007\u0002CB\u0018\u0007g\u0001\ra!\u0013\u0011\u000b1\u0019\u0019ea\u000f\t\u0015\r5#1GA\u0001\n\u0013\u0019y%A\u0006sK\u0006$'+Z:pYZ,GCAB)!\u0011\u0019\u0019f!\u0018\u000e\u0005\rU#\u0002BB,\u00073\nA\u0001\\1oO*\u001111L\u0001\u0005U\u00064\u0018-\u0003\u0003\u0004`\rU#AB(cU\u0016\u001cG\u000f")
public final class LongMap<V>
extends AbstractMap<Object, V>
implements Serializable {
    private final Function1<Object, V> defaultEntry;
    private int mask;
    public int scala$collection$mutable$LongMap$$extraKeys;
    public Object scala$collection$mutable$LongMap$$zeroValue;
    public Object scala$collection$mutable$LongMap$$minValue;
    private int _size;
    private int _vacant;
    public long[] scala$collection$mutable$LongMap$$_keys;
    public Object[] scala$collection$mutable$LongMap$$_values;

    public static <V> LongMap<V> fromZip(scala.collection.mutable.Iterable<Object> iterable, scala.collection.mutable.Iterable<V> iterable2) {
        return LongMap$.MODULE$.fromZip(iterable, iterable2);
    }

    public static <V> LongMap<V> fromZip(long[] lArray, Object object) {
        return LongMap$.MODULE$.fromZip(lArray, object);
    }

    public static <V, U> CanBuildFrom<LongMap<V>, Tuple2<Object, U>, LongMap<U>> canBuildFrom() {
        return LongMap$.MODULE$.canBuildFrom();
    }

    private void defaultInitialize(int n) {
        this.mask = n < 0 ? 7 : (1 << 32 - Integer.numberOfLeadingZeros(n - 1)) - 1 & 0x3FFFFFFF | 7;
        this.scala$collection$mutable$LongMap$$_keys = new long[this.mask + 1];
        this.scala$collection$mutable$LongMap$$_values = new Object[this.mask + 1];
    }

    public void initializeTo(int m, int ek, Object zv, Object mv, int sz, int vc, long[] kz, Object[] vz) {
        this.mask = m;
        this.scala$collection$mutable$LongMap$$extraKeys = ek;
        this.scala$collection$mutable$LongMap$$zeroValue = zv;
        this.scala$collection$mutable$LongMap$$minValue = mv;
        this._size = sz;
        this._vacant = vc;
        this.scala$collection$mutable$LongMap$$_keys = kz;
        this.scala$collection$mutable$LongMap$$_values = vz;
    }

    @Override
    public int size() {
        return this._size + (this.scala$collection$mutable$LongMap$$extraKeys + 1) / 2;
    }

    @Override
    public LongMap<V> empty() {
        return new LongMap<V>();
    }

    private boolean imbalanced() {
        return (double)(this._size + this._vacant) > 0.5 * (double)this.mask || this._vacant > this._size;
    }

    private int toIndex(long k) {
        int h = (int)((k ^ k >>> 32) & 0xFFFFFFFFL);
        int x = (h ^ h >>> 16) * -2048144789;
        return (x ^ x >>> 13) & this.mask;
    }

    /*
     * WARNING - void declaration
     */
    private int seekEmpty(long k) {
        void var3_2;
        int e = this.toIndex(k);
        int x = 0;
        while (this.scala$collection$mutable$LongMap$$_keys[e] != 0L) {
            e = e + 2 * (++x + 1) * x - 3 & this.mask;
        }
        return (int)var3_2;
    }

    private int seekEntry(long k) {
        int e = this.toIndex(k);
        int x = 0;
        while (true) {
            long q;
            if ((q = this.scala$collection$mutable$LongMap$$_keys[e]) == k) {
                return e;
            }
            if (!(q != 0L)) break;
            e = e + 2 * (++x + 1) * x - 3 & this.mask;
        }
        return e | Integer.MIN_VALUE;
    }

    private int seekEntryOrOpen(long k) {
        long q;
        int e = this.toIndex(k);
        int x = 0;
        while (true) {
            if ((q = this.scala$collection$mutable$LongMap$$_keys[e]) == k) {
                return e;
            }
            if (!(q + q != 0L)) break;
            e = e + 2 * (++x + 1) * x - 3 & this.mask;
        }
        if (q == 0L) {
            return e | Integer.MIN_VALUE;
        }
        int o = e | 0xC0000000;
        while (true) {
            if ((q = this.scala$collection$mutable$LongMap$$_keys[e]) == k) {
                return e;
            }
            if (!(q != 0L)) break;
            e = e + 2 * (++x + 1) * x - 3 & this.mask;
        }
        return o;
    }

    @Override
    public boolean contains(long key) {
        return key == -key ? ((int)(key >>> 63) + 1 & this.scala$collection$mutable$LongMap$$extraKeys) != 0 : this.seekEntry(key) >= 0;
    }

    @Override
    public Option<V> get(long key) {
        int i;
        Option option = key == -key ? (((int)(key >>> 63) + 1 & this.scala$collection$mutable$LongMap$$extraKeys) == 0 ? None$.MODULE$ : (key == 0L ? new Some<Object>(this.scala$collection$mutable$LongMap$$zeroValue) : new Some<Object>(this.scala$collection$mutable$LongMap$$minValue))) : ((i = this.seekEntry(key)) < 0 ? None$.MODULE$ : new Some<Object>(this.scala$collection$mutable$LongMap$$_values[i]));
        return option;
    }

    @Override
    public <V1> V1 getOrElse(long key, Function0<V1> function0) {
        int i;
        Object object = key == -key ? (((int)(key >>> 63) + 1 & this.scala$collection$mutable$LongMap$$extraKeys) == 0 ? function0.apply() : (key == 0L ? this.scala$collection$mutable$LongMap$$zeroValue : this.scala$collection$mutable$LongMap$$minValue)) : ((i = this.seekEntry(key)) < 0 ? function0.apply() : this.scala$collection$mutable$LongMap$$_values[i]);
        return object;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public V getOrElseUpdate(long key, Function0<V> defaultValue) {
        Object object;
        if (key == -key) {
            int kbits = (int)(key >>> 63) + 1;
            if ((kbits & this.scala$collection$mutable$LongMap$$extraKeys) == 0) {
                V value = defaultValue.apply();
                this.scala$collection$mutable$LongMap$$extraKeys |= kbits;
                if (key == 0L) {
                    this.scala$collection$mutable$LongMap$$zeroValue = value;
                } else {
                    this.scala$collection$mutable$LongMap$$minValue = value;
                }
                object = value;
            } else {
                object = key == 0L ? this.scala$collection$mutable$LongMap$$zeroValue : this.scala$collection$mutable$LongMap$$minValue;
            }
        } else {
            int i = this.seekEntryOrOpen(key);
            if (i < 0) {
                void var8_7;
                long[] ok = this.scala$collection$mutable$LongMap$$_keys;
                V ans = defaultValue.apply();
                if (ok != this.scala$collection$mutable$LongMap$$_keys && (i = this.seekEntryOrOpen(key)) >= 0) {
                    --this._size;
                }
                ++this._size;
                int j = i & 0x3FFFFFFF;
                this.scala$collection$mutable$LongMap$$_keys[j] = key;
                this.scala$collection$mutable$LongMap$$_values[j] = var8_7;
                if ((i & 0x40000000) != 0) {
                    --this._vacant;
                } else if (this.imbalanced()) {
                    this.repack();
                }
                object = var8_7;
            } else {
                object = this.scala$collection$mutable$LongMap$$_values[i];
            }
        }
        return (V)object;
    }

    public V getOrNull(long key) {
        int i;
        Object object = key == -key ? (((int)(key >>> 63) + 1 & this.scala$collection$mutable$LongMap$$extraKeys) == 0 ? null : (key == 0L ? this.scala$collection$mutable$LongMap$$zeroValue : this.scala$collection$mutable$LongMap$$minValue)) : ((i = this.seekEntry(key)) < 0 ? null : this.scala$collection$mutable$LongMap$$_values[i]);
        return (V)object;
    }

    @Override
    public V apply(long key) {
        int i;
        Object object = key == -key ? (((int)(key >>> 63) + 1 & this.scala$collection$mutable$LongMap$$extraKeys) == 0 ? this.defaultEntry.apply(BoxesRunTime.boxToLong(key)) : (key == 0L ? this.scala$collection$mutable$LongMap$$zeroValue : this.scala$collection$mutable$LongMap$$minValue)) : ((i = this.seekEntry(key)) < 0 ? this.defaultEntry.apply(BoxesRunTime.boxToLong(key)) : this.scala$collection$mutable$LongMap$$_values[i]);
        return (V)object;
    }

    @Override
    public V default(long key) {
        return this.defaultEntry.apply(BoxesRunTime.boxToLong(key));
    }

    private void repack(int newMask) {
        long[] ok = this.scala$collection$mutable$LongMap$$_keys;
        Object[] ov = this.scala$collection$mutable$LongMap$$_values;
        this.mask = newMask;
        this.scala$collection$mutable$LongMap$$_keys = new long[this.mask + 1];
        this.scala$collection$mutable$LongMap$$_values = new Object[this.mask + 1];
        this._vacant = 0;
        for (int i = 0; i < ok.length; ++i) {
            long k = ok[i];
            if (k == -k) continue;
            int j = this.seekEmpty(k);
            this.scala$collection$mutable$LongMap$$_keys[j] = k;
            this.scala$collection$mutable$LongMap$$_values[j] = ov[i];
        }
    }

    public void repack() {
        int m = this.mask;
        if ((double)(this._size + this._vacant) >= 0.5 * (double)this.mask && !((double)this._vacant > 0.2 * (double)this.mask)) {
            m = (m << 1) + 1 & 0x3FFFFFFF;
        }
        while (m > 8 && 8 * this._size < m) {
            m >>>= 1;
        }
        this.repack(m);
    }

    @Override
    public Option<V> put(long key, V value) {
        Option option;
        if (key == -key) {
            if (key == 0L) {
                Option ans = (this.scala$collection$mutable$LongMap$$extraKeys & 1) == 1 ? new Some<Object>(this.scala$collection$mutable$LongMap$$zeroValue) : None$.MODULE$;
                this.scala$collection$mutable$LongMap$$zeroValue = value;
                this.scala$collection$mutable$LongMap$$extraKeys |= 1;
                option = ans;
            } else {
                Option ans = (this.scala$collection$mutable$LongMap$$extraKeys & 2) == 1 ? new Some<Object>(this.scala$collection$mutable$LongMap$$minValue) : None$.MODULE$;
                this.scala$collection$mutable$LongMap$$minValue = value;
                this.scala$collection$mutable$LongMap$$extraKeys |= 2;
                option = ans;
            }
        } else {
            int i = this.seekEntryOrOpen(key);
            if (i < 0) {
                int j = i & 0x3FFFFFFF;
                this.scala$collection$mutable$LongMap$$_keys[j] = key;
                this.scala$collection$mutable$LongMap$$_values[j] = value;
                ++this._size;
                if ((i & 0x40000000) != 0) {
                    --this._vacant;
                } else if (this.imbalanced()) {
                    this.repack();
                }
                option = None$.MODULE$;
            } else {
                Some<Object> ans = new Some<Object>(this.scala$collection$mutable$LongMap$$_values[i]);
                this.scala$collection$mutable$LongMap$$_keys[i] = key;
                this.scala$collection$mutable$LongMap$$_values[i] = value;
                option = ans;
            }
        }
        return option;
    }

    @Override
    public void update(long key, V value) {
        if (key == -key) {
            if (key == 0L) {
                this.scala$collection$mutable$LongMap$$zeroValue = value;
                this.scala$collection$mutable$LongMap$$extraKeys |= 1;
            } else {
                this.scala$collection$mutable$LongMap$$minValue = value;
                this.scala$collection$mutable$LongMap$$extraKeys |= 2;
            }
        } else {
            int i = this.seekEntryOrOpen(key);
            if (i < 0) {
                int j = i & 0x3FFFFFFF;
                this.scala$collection$mutable$LongMap$$_keys[j] = key;
                this.scala$collection$mutable$LongMap$$_values[j] = value;
                ++this._size;
                if ((i & 0x40000000) != 0) {
                    --this._vacant;
                } else if (this.imbalanced()) {
                    this.repack();
                }
            } else {
                this.scala$collection$mutable$LongMap$$_keys[i] = key;
                this.scala$collection$mutable$LongMap$$_values[i] = value;
            }
        }
    }

    public LongMap<V> $plus$eq(long key, V value) {
        this.update(key, value);
        return this;
    }

    @Override
    public LongMap<V> $plus$eq(Tuple2<Object, V> kv) {
        this.update(kv._1$mcJ$sp(), kv._2());
        return this;
    }

    @Override
    public LongMap<V> $minus$eq(long key) {
        if (key == -key) {
            if (key == 0L) {
                this.scala$collection$mutable$LongMap$$extraKeys &= 2;
                this.scala$collection$mutable$LongMap$$zeroValue = null;
            } else {
                this.scala$collection$mutable$LongMap$$extraKeys &= 1;
                this.scala$collection$mutable$LongMap$$minValue = null;
            }
        } else {
            int i = this.seekEntry(key);
            if (i >= 0) {
                --this._size;
                ++this._vacant;
                this.scala$collection$mutable$LongMap$$_keys[i] = Long.MIN_VALUE;
                this.scala$collection$mutable$LongMap$$_values[i] = null;
            }
        }
        return this;
    }

    @Override
    public Iterator<Tuple2<Object, V>> iterator() {
        return new Iterator<Tuple2<Object, V>>(this){
            private final long[] kz;
            private final Object[] vz;
            private Tuple2<Object, V> nextPair;
            private Tuple2<Object, V> anotherPair;
            private int index;

            public Iterator<Tuple2<Object, V>> seq() {
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

            public Iterator<Tuple2<Object, V>> take(int n) {
                return Iterator$class.take(this, n);
            }

            public Iterator<Tuple2<Object, V>> drop(int n) {
                return Iterator$class.drop(this, n);
            }

            public Iterator<Tuple2<Object, V>> slice(int from2, int until2) {
                return Iterator$class.slice(this, from2, until2);
            }

            public <B> Iterator<B> map(Function1<Tuple2<Object, V>, B> f) {
                return Iterator$class.map(this, f);
            }

            public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
                return Iterator$class.$plus$plus(this, that);
            }

            public <B> Iterator<B> flatMap(Function1<Tuple2<Object, V>, GenTraversableOnce<B>> f) {
                return Iterator$class.flatMap(this, f);
            }

            public Iterator<Tuple2<Object, V>> filter(Function1<Tuple2<Object, V>, Object> p) {
                return Iterator$class.filter(this, p);
            }

            public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<Tuple2<Object, V>, B, Object> p) {
                return Iterator$class.corresponds(this, that, p);
            }

            public Iterator<Tuple2<Object, V>> withFilter(Function1<Tuple2<Object, V>, Object> p) {
                return Iterator$class.withFilter(this, p);
            }

            public Iterator<Tuple2<Object, V>> filterNot(Function1<Tuple2<Object, V>, Object> p) {
                return Iterator$class.filterNot(this, p);
            }

            public <B> Iterator<B> collect(PartialFunction<Tuple2<Object, V>, B> pf) {
                return Iterator$class.collect(this, pf);
            }

            public <B> Iterator<B> scanLeft(B z, Function2<B, Tuple2<Object, V>, B> op) {
                return Iterator$class.scanLeft(this, z, op);
            }

            public <B> Iterator<B> scanRight(B z, Function2<Tuple2<Object, V>, B, B> op) {
                return Iterator$class.scanRight(this, z, op);
            }

            public Iterator<Tuple2<Object, V>> takeWhile(Function1<Tuple2<Object, V>, Object> p) {
                return Iterator$class.takeWhile(this, p);
            }

            public Tuple2<Iterator<Tuple2<Object, V>>, Iterator<Tuple2<Object, V>>> partition(Function1<Tuple2<Object, V>, Object> p) {
                return Iterator$class.partition(this, p);
            }

            public Tuple2<Iterator<Tuple2<Object, V>>, Iterator<Tuple2<Object, V>>> span(Function1<Tuple2<Object, V>, Object> p) {
                return Iterator$class.span(this, p);
            }

            public Iterator<Tuple2<Object, V>> dropWhile(Function1<Tuple2<Object, V>, Object> p) {
                return Iterator$class.dropWhile(this, p);
            }

            public <B> Iterator<Tuple2<Tuple2<Object, V>, B>> zip(Iterator<B> that) {
                return Iterator$class.zip(this, that);
            }

            public <A1> Iterator<A1> padTo(int len, A1 elem) {
                return Iterator$class.padTo(this, len, elem);
            }

            public Iterator<Tuple2<Tuple2<Object, V>, Object>> zipWithIndex() {
                return Iterator$class.zipWithIndex(this);
            }

            public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
                return Iterator$class.zipAll(this, that, thisElem, thatElem);
            }

            public <U> void foreach(Function1<Tuple2<Object, V>, U> f) {
                Iterator$class.foreach(this, f);
            }

            public boolean forall(Function1<Tuple2<Object, V>, Object> p) {
                return Iterator$class.forall(this, p);
            }

            public boolean exists(Function1<Tuple2<Object, V>, Object> p) {
                return Iterator$class.exists(this, p);
            }

            public boolean contains(Object elem) {
                return Iterator$class.contains(this, elem);
            }

            public Option<Tuple2<Object, V>> find(Function1<Tuple2<Object, V>, Object> p) {
                return Iterator$class.find(this, p);
            }

            public int indexWhere(Function1<Tuple2<Object, V>, Object> p) {
                return Iterator$class.indexWhere(this, p);
            }

            public <B> int indexOf(B elem) {
                return Iterator$class.indexOf(this, elem);
            }

            public BufferedIterator<Tuple2<Object, V>> buffered() {
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

            public Tuple2<Iterator<Tuple2<Object, V>>, Iterator<Tuple2<Object, V>>> duplicate() {
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

            public Traversable<Tuple2<Object, V>> toTraversable() {
                return Iterator$class.toTraversable(this);
            }

            public Iterator<Tuple2<Object, V>> toIterator() {
                return Iterator$class.toIterator(this);
            }

            public Stream<Tuple2<Object, V>> toStream() {
                return Iterator$class.toStream(this);
            }

            public String toString() {
                return Iterator$class.toString(this);
            }

            public <B> int sliding$default$2() {
                return Iterator$class.sliding$default$2(this);
            }

            public List<Tuple2<Object, V>> reversed() {
                return TraversableOnce$class.reversed(this);
            }

            public int size() {
                return TraversableOnce$class.size(this);
            }

            public boolean nonEmpty() {
                return TraversableOnce$class.nonEmpty(this);
            }

            public int count(Function1<Tuple2<Object, V>, Object> p) {
                return TraversableOnce$class.count(this, p);
            }

            public <B> Option<B> collectFirst(PartialFunction<Tuple2<Object, V>, B> pf) {
                return TraversableOnce$class.collectFirst(this, pf);
            }

            public <B> B $div$colon(B z, Function2<B, Tuple2<Object, V>, B> op) {
                return (B)TraversableOnce$class.$div$colon(this, z, op);
            }

            public <B> B $colon$bslash(B z, Function2<Tuple2<Object, V>, B, B> op) {
                return (B)TraversableOnce$class.$colon$bslash(this, z, op);
            }

            public <B> B foldLeft(B z, Function2<B, Tuple2<Object, V>, B> op) {
                return (B)TraversableOnce$class.foldLeft(this, z, op);
            }

            public <B> B foldRight(B z, Function2<Tuple2<Object, V>, B, B> op) {
                return (B)TraversableOnce$class.foldRight(this, z, op);
            }

            public <B> B reduceLeft(Function2<B, Tuple2<Object, V>, B> op) {
                return (B)TraversableOnce$class.reduceLeft(this, op);
            }

            public <B> B reduceRight(Function2<Tuple2<Object, V>, B, B> op) {
                return (B)TraversableOnce$class.reduceRight(this, op);
            }

            public <B> Option<B> reduceLeftOption(Function2<B, Tuple2<Object, V>, B> op) {
                return TraversableOnce$class.reduceLeftOption(this, op);
            }

            public <B> Option<B> reduceRightOption(Function2<Tuple2<Object, V>, B, B> op) {
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

            public <B> B aggregate(Function0<B> z, Function2<B, Tuple2<Object, V>, B> seqop, Function2<B, B, B> combop) {
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

            public List<Tuple2<Object, V>> toList() {
                return TraversableOnce$class.toList(this);
            }

            public Iterable<Tuple2<Object, V>> toIterable() {
                return TraversableOnce$class.toIterable(this);
            }

            public Seq<Tuple2<Object, V>> toSeq() {
                return TraversableOnce$class.toSeq(this);
            }

            public IndexedSeq<Tuple2<Object, V>> toIndexedSeq() {
                return TraversableOnce$class.toIndexedSeq(this);
            }

            public <B> Buffer<B> toBuffer() {
                return TraversableOnce$class.toBuffer(this);
            }

            public <B> Set<B> toSet() {
                return TraversableOnce$class.toSet(this);
            }

            public Vector<Tuple2<Object, V>> toVector() {
                return TraversableOnce$class.toVector(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, Tuple2<Object, V>, Col> cbf) {
                return (Col)TraversableOnce$class.to(this, cbf);
            }

            public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Tuple2<Object, V>, Tuple2<T, U>> ev) {
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

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public boolean hasNext() {
                if (this.nextPair != null) return true;
                if (this.index >= this.kz.length) return false;
                long q = this.kz[this.index];
                while (q == -q) {
                    ++this.index;
                    if (this.index >= this.kz.length) {
                        return false;
                    }
                    q = this.kz[this.index];
                }
                this.nextPair = new Tuple2<Long, Object>(BoxesRunTime.boxToLong(this.kz[this.index]), this.vz[this.index]);
                ++this.index;
                return true;
            }

            /*
             * WARNING - void declaration
             */
            public Tuple2<Object, V> next() {
                void var1_1;
                if (this.nextPair == null && !this.hasNext()) {
                    throw new NoSuchElementException("next");
                }
                Tuple2<Object, V> ans = this.nextPair;
                if (this.anotherPair == null) {
                    this.nextPair = null;
                } else {
                    this.nextPair = this.anotherPair;
                    this.anotherPair = null;
                }
                return var1_1;
            }
            {
                TraversableOnce$class.$init$(this);
                Iterator$class.$init$(this);
                this.kz = $outer.scala$collection$mutable$LongMap$$_keys;
                this.vz = $outer.scala$collection$mutable$LongMap$$_values;
                this.nextPair = $outer.scala$collection$mutable$LongMap$$extraKeys == 0 ? null : (($outer.scala$collection$mutable$LongMap$$extraKeys & 1) == 1 ? new Tuple2<Long, Object>(BoxesRunTime.boxToLong(0L), $outer.scala$collection$mutable$LongMap$$zeroValue) : new Tuple2<Long, Object>(BoxesRunTime.boxToLong(Long.MIN_VALUE), $outer.scala$collection$mutable$LongMap$$minValue));
                this.anotherPair = $outer.scala$collection$mutable$LongMap$$extraKeys == 3 ? new Tuple2<Long, Object>(BoxesRunTime.boxToLong(Long.MIN_VALUE), $outer.scala$collection$mutable$LongMap$$minValue) : null;
                this.index = 0;
            }
        };
    }

    @Override
    public <U> void foreach(Function1<Tuple2<Object, V>, U> f) {
        BoxedUnit boxedUnit = (this.scala$collection$mutable$LongMap$$extraKeys & 1) == 1 ? f.apply(new Tuple2<Long, Object>(BoxesRunTime.boxToLong(0L), this.scala$collection$mutable$LongMap$$zeroValue)) : BoxedUnit.UNIT;
        BoxedUnit boxedUnit2 = (this.scala$collection$mutable$LongMap$$extraKeys & 2) == 2 ? f.apply(new Tuple2<Long, Object>(BoxesRunTime.boxToLong(Long.MIN_VALUE), this.scala$collection$mutable$LongMap$$minValue)) : BoxedUnit.UNIT;
        int i = 0;
        int j = 0;
        while (i < this.scala$collection$mutable$LongMap$$_keys.length & j < this._size) {
            BoxedUnit boxedUnit3;
            long k = this.scala$collection$mutable$LongMap$$_keys[i];
            if (k != -k) {
                ++j;
                boxedUnit3 = f.apply(new Tuple2<Long, Object>(BoxesRunTime.boxToLong(k), this.scala$collection$mutable$LongMap$$_values[i]));
            } else {
                boxedUnit3 = BoxedUnit.UNIT;
            }
            ++i;
        }
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public LongMap<V> clone() {
        void var3_3;
        long[] kz = Arrays.copyOf(this.scala$collection$mutable$LongMap$$_keys, this.scala$collection$mutable$LongMap$$_keys.length);
        Object[] vz = Arrays.copyOf(this.scala$collection$mutable$LongMap$$_values, this.scala$collection$mutable$LongMap$$_values.length);
        LongMap<V> lm = new LongMap<V>(this.defaultEntry, 1, false);
        lm.initializeTo(this.mask, this.scala$collection$mutable$LongMap$$extraKeys, this.scala$collection$mutable$LongMap$$zeroValue, this.scala$collection$mutable$LongMap$$minValue, this._size, this._vacant, kz, vz);
        return var3_3;
    }

    public <A> void foreachKey(Function1<Object, A> f) {
        BoxedUnit boxedUnit = (this.scala$collection$mutable$LongMap$$extraKeys & 1) == 1 ? f.apply(BoxesRunTime.boxToLong(0L)) : BoxedUnit.UNIT;
        BoxedUnit boxedUnit2 = (this.scala$collection$mutable$LongMap$$extraKeys & 2) == 2 ? f.apply(BoxesRunTime.boxToLong(Long.MIN_VALUE)) : BoxedUnit.UNIT;
        int i = 0;
        int j = 0;
        while (i < this.scala$collection$mutable$LongMap$$_keys.length & j < this._size) {
            BoxedUnit boxedUnit3;
            long k = this.scala$collection$mutable$LongMap$$_keys[i];
            if (k != -k) {
                ++j;
                boxedUnit3 = f.apply(BoxesRunTime.boxToLong(k));
            } else {
                boxedUnit3 = BoxedUnit.UNIT;
            }
            ++i;
        }
    }

    public <A> void foreachValue(Function1<V, A> f) {
        BoxedUnit boxedUnit = (this.scala$collection$mutable$LongMap$$extraKeys & 1) == 1 ? f.apply(this.scala$collection$mutable$LongMap$$zeroValue) : BoxedUnit.UNIT;
        BoxedUnit boxedUnit2 = (this.scala$collection$mutable$LongMap$$extraKeys & 2) == 2 ? f.apply(this.scala$collection$mutable$LongMap$$minValue) : BoxedUnit.UNIT;
        int i = 0;
        int j = 0;
        while (i < this.scala$collection$mutable$LongMap$$_keys.length & j < this._size) {
            BoxedUnit boxedUnit3;
            long k = this.scala$collection$mutable$LongMap$$_keys[i];
            if (k != -k) {
                ++j;
                boxedUnit3 = f.apply(this.scala$collection$mutable$LongMap$$_values[i]);
            } else {
                boxedUnit3 = BoxedUnit.UNIT;
            }
            ++i;
        }
    }

    public <V1> LongMap<V1> mapValuesNow(Function1<V, V1> f) {
        Object zv = (this.scala$collection$mutable$LongMap$$extraKeys & 1) == 1 ? f.apply(this.scala$collection$mutable$LongMap$$zeroValue) : null;
        Object mv = (this.scala$collection$mutable$LongMap$$extraKeys & 2) == 2 ? f.apply(this.scala$collection$mutable$LongMap$$minValue) : null;
        LongMap<Nothing$> lm = new LongMap<Nothing$>(LongMap$.MODULE$.scala$collection$mutable$LongMap$$exceptionDefault(), 1, false);
        long[] kz = Arrays.copyOf(this.scala$collection$mutable$LongMap$$_keys, this.scala$collection$mutable$LongMap$$_keys.length);
        Object[] vz = new Object[this.scala$collection$mutable$LongMap$$_values.length];
        int i = 0;
        int j = 0;
        while (i < this.scala$collection$mutable$LongMap$$_keys.length & j < this._size) {
            long k = this.scala$collection$mutable$LongMap$$_keys[i];
            if (k != -k) {
                ++j;
                vz[i] = f.apply(this.scala$collection$mutable$LongMap$$_values[i]);
            }
            ++i;
        }
        lm.initializeTo(this.mask, this.scala$collection$mutable$LongMap$$extraKeys, zv, mv, this._size, this._vacant, kz, vz);
        return lm;
    }

    public LongMap<V> transformValues(Function1<V, V> f) {
        if ((this.scala$collection$mutable$LongMap$$extraKeys & 1) == 1) {
            this.scala$collection$mutable$LongMap$$zeroValue = f.apply(this.scala$collection$mutable$LongMap$$zeroValue);
        }
        if ((this.scala$collection$mutable$LongMap$$extraKeys & 2) == 2) {
            this.scala$collection$mutable$LongMap$$minValue = f.apply(this.scala$collection$mutable$LongMap$$minValue);
        }
        int i = 0;
        int j = 0;
        while (i < this.scala$collection$mutable$LongMap$$_keys.length & j < this._size) {
            long k = this.scala$collection$mutable$LongMap$$_keys[i];
            if (k != -k) {
                ++j;
                this.scala$collection$mutable$LongMap$$_values[i] = f.apply(this.scala$collection$mutable$LongMap$$_values[i]);
            }
            ++i;
        }
        return this;
    }

    public LongMap(Function1<Object, V> defaultEntry, int initialBufferSize, boolean initBlank) {
        this.defaultEntry = defaultEntry;
        this.mask = 0;
        this.scala$collection$mutable$LongMap$$extraKeys = 0;
        this.scala$collection$mutable$LongMap$$zeroValue = null;
        this.scala$collection$mutable$LongMap$$minValue = null;
        this._size = 0;
        this._vacant = 0;
        this.scala$collection$mutable$LongMap$$_keys = null;
        this.scala$collection$mutable$LongMap$$_values = null;
        if (initBlank) {
            this.defaultInitialize(initialBufferSize);
        }
    }

    public LongMap() {
        this(LongMap$.MODULE$.scala$collection$mutable$LongMap$$exceptionDefault(), 16, true);
    }

    public LongMap(Function1<Object, V> defaultEntry) {
        this(defaultEntry, 16, true);
    }

    public LongMap(int initialBufferSize) {
        this(LongMap$.MODULE$.scala$collection$mutable$LongMap$$exceptionDefault(), initialBufferSize, true);
    }

    public LongMap(Function1<Object, V> defaultEntry, int initialBufferSize) {
        this(defaultEntry, initialBufferSize, true);
    }

    public static final class LongMapBuilder<V>
    implements Builder<Tuple2<Object, V>, LongMap<V>> {
        private LongMap<V> elems;

        @Override
        public void sizeHint(int size2) {
            Builder$class.sizeHint((Builder)this, size2);
        }

        @Override
        public void sizeHint(TraversableLike<?, ?> coll) {
            Builder$class.sizeHint((Builder)this, coll);
        }

        @Override
        public void sizeHint(TraversableLike<?, ?> coll, int delta) {
            Builder$class.sizeHint(this, coll, delta);
        }

        @Override
        public void sizeHintBounded(int size2, TraversableLike<?, ?> boundingColl) {
            Builder$class.sizeHintBounded(this, size2, boundingColl);
        }

        @Override
        public <NewTo> Builder<Tuple2<Object, V>, NewTo> mapResult(Function1<LongMap<V>, NewTo> f) {
            return Builder$class.mapResult(this, f);
        }

        @Override
        public Growable $plus$eq(Object elem1, Object elem2, Seq elems) {
            return Growable$class.$plus$eq(this, elem1, elem2, elems);
        }

        @Override
        public Growable<Tuple2<Object, V>> $plus$plus$eq(TraversableOnce<Tuple2<Object, V>> xs) {
            return Growable$class.$plus$plus$eq(this, xs);
        }

        public LongMap<V> elems() {
            return this.elems;
        }

        public void elems_$eq(LongMap<V> x$1) {
            this.elems = x$1;
        }

        @Override
        public LongMapBuilder<V> $plus$eq(Tuple2<Object, V> entry) {
            this.elems().$plus$eq((Tuple2)entry);
            return this;
        }

        @Override
        public void clear() {
            this.elems_$eq(new LongMap());
        }

        @Override
        public LongMap<V> result() {
            return this.elems();
        }

        public LongMapBuilder() {
            Growable$class.$init$(this);
            Builder$class.$init$(this);
            this.elems = new LongMap();
        }
    }
}

