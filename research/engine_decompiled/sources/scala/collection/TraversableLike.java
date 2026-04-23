/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.GenTraversableLike;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterator;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.TraversableView;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.HasNewBuilder;
import scala.collection.immutable.Map;
import scala.collection.immutable.Stream;
import scala.collection.mutable.Builder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterable;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u0011%b!C\u0001\u0003!\u0003\r\ta\u0002C\u0014\u0005=!&/\u0019<feN\f'\r\\3MS.,'BA\u0002\u0005\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u000b\u0005)1oY1mC\u000e\u0001Qc\u0001\u0005\u00169M9\u0001!C\u0007\u001fC\u0015B\u0003C\u0001\u0006\f\u001b\u0005!\u0011B\u0001\u0007\u0005\u0005\r\te.\u001f\t\u0005\u001dE\u00192$D\u0001\u0010\u0015\t\u0001\"!A\u0004hK:,'/[2\n\u0005Iy!!\u0004%bg:+wOQ;jY\u0012,'\u000f\u0005\u0002\u0015+1\u0001AA\u0002\f\u0001\t\u000b\u0007qCA\u0001B#\tA\u0012\u0002\u0005\u0002\u000b3%\u0011!\u0004\u0002\u0002\b\u001d>$\b.\u001b8h!\t!B\u0004\u0002\u0004\u001e\u0001\u0011\u0015\ra\u0006\u0002\u0005%\u0016\u0004(\u000f\u0005\u0003\u000f?MY\u0012B\u0001\u0011\u0010\u000551\u0015\u000e\u001c;fe6{g.\u00193jGB\u0019!eI\n\u000e\u0003\tI!\u0001\n\u0002\u0003\u001fQ\u0013\u0018M^3sg\u0006\u0014G.Z(oG\u0016\u0004BA\t\u0014\u00147%\u0011qE\u0001\u0002\u0013\u000f\u0016tGK]1wKJ\u001c\u0018M\u00197f\u0019&\\W\r\u0005\u0003#SMY\u0013B\u0001\u0016\u0003\u00059\u0001\u0016M]1mY\u0016d\u0017N_1cY\u0016\u00042\u0001L\u0018\u0014\u001b\u0005i#B\u0001\u0018\u0003\u0003!\u0001\u0018M]1mY\u0016d\u0017B\u0001\u0019.\u0005-\u0001\u0016M]%uKJ\f'\r\\3\t\u000bI\u0002A\u0011A\u001a\u0002\r\u0011Jg.\u001b;%)\u0005!\u0004C\u0001\u00066\u0013\t1DA\u0001\u0003V]&$XA\u0002\u001d\u0001A\u0003E1D\u0001\u0003TK24\u0007\"\u0002\u001e\u0001\t\u0003Y\u0014\u0001\u0002:faJ,\u0012a\u0007\u0005\u0006{\u0001!)AP\u0001\u0013SN$&/\u0019<feN\f'\r\\3BO\u0006Lg.F\u0001@!\tQ\u0001)\u0003\u0002B\t\t9!i\\8mK\u0006t\u0007BB\"\u0001A\u0013EA)\u0001\buQ&\u001c8i\u001c7mK\u000e$\u0018n\u001c8\u0016\u0003\u0015\u00032A\t$\u0014\u0013\t9%AA\u0006Ue\u00064XM]:bE2,\u0007BB%\u0001A\u0013E!*\u0001\u0007u_\u000e{G\u000e\\3di&|g\u000e\u0006\u0002F\u0017\")!\b\u0013a\u00017!1Q\n\u0001Q\u0007\u00129\u000b!B\\3x\u0005VLG\u000eZ3s+\u0005y\u0005\u0003\u0002)T'mi\u0011!\u0015\u0006\u0003%\n\tq!\\;uC\ndW-\u0003\u0002U#\n9!)^5mI\u0016\u0014\bB\u0002,\u0001A\u0013Eq+A\u0006qCJ\u001cu.\u001c2j]\u0016\u0014X#\u0001-\u0011\t1J6cK\u0005\u000356\u0012\u0001bQ8nE&tWM\u001d\u0005\u00069\u00021\t!X\u0001\bM>\u0014X-Y2i+\tqV\r\u0006\u00025?\")\u0001m\u0017a\u0001C\u0006\ta\r\u0005\u0003\u000bEN!\u0017BA2\u0005\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002\u0015K\u0012)am\u0017b\u0001/\t\tQ\u000bC\u0003i\u0001\u0011\u0005a(A\u0004jg\u0016k\u0007\u000f^=\t\u000b)\u0004A\u0011\u0001 \u0002\u001f!\f7\u000fR3gS:LG/Z*ju\u0016DQ\u0001\u001c\u0001\u0005\u00025\f!\u0002\n9mkN$\u0003\u000f\\;t+\rq\u00170\u001d\u000b\u0003_r$\"\u0001]:\u0011\u0005Q\tH!\u0002:l\u0005\u00049\"\u0001\u0002+iCRDQ\u0001^6A\u0004U\f!A\u00194\u0011\u000b918\u0004\u001f9\n\u0005]|!\u0001D\"b]\n+\u0018\u000e\u001c3Ge>l\u0007C\u0001\u000bz\t\u0015Q8N1\u0001|\u0005\u0005\u0011\u0015CA\n\n\u0011\u0015i8\u000e1\u0001\u007f\u0003\u0011!\b.\u0019;\u0011\u0007\tz\b0C\u0002\u0002\u0002\t\u0011!cR3o)J\fg/\u001a:tC\ndWm\u00148dK\"9\u0011Q\u0001\u0001\u0005\u0002\u0005\u001d\u0011\u0001\u0005\u0013qYV\u001cH\u0005\u001d7vg\u0012\u001aw\u000e\\8o+\u0019\tI!a\u0006\u0002\u0010Q!\u00111BA\r)\u0011\ti!!\u0005\u0011\u0007Q\ty\u0001\u0002\u0004s\u0003\u0007\u0011\ra\u0006\u0005\bi\u0006\r\u00019AA\n!\u001dqaoGA\u000b\u0003\u001b\u00012\u0001FA\f\t\u0019Q\u00181\u0001b\u0001w\"9Q0a\u0001A\u0002\u0005m\u0001\u0003\u0002\u0012$\u0003+Aq!!\u0002\u0001\t\u0003\ty\"\u0006\u0004\u0002\"\u0005=\u0012q\u0005\u000b\u0005\u0003G\t\t\u0004\u0006\u0003\u0002&\u0005%\u0002c\u0001\u000b\u0002(\u00111!/!\bC\u0002]Aq\u0001^A\u000f\u0001\b\tY\u0003E\u0004\u000fmn\ti#!\n\u0011\u0007Q\ty\u0003\u0002\u0004{\u0003;\u0011\ra\u001f\u0005\b{\u0006u\u0001\u0019AA\u001a!\u0011\u0011c)!\f\t\u000f\u0005]\u0002\u0001\"\u0001\u0002:\u0005\u0019Q.\u00199\u0016\r\u0005m\u0012\u0011JA!)\u0011\ti$a\u0013\u0015\t\u0005}\u00121\t\t\u0004)\u0005\u0005CA\u0002:\u00026\t\u0007q\u0003C\u0004u\u0003k\u0001\u001d!!\u0012\u0011\u000f918$a\u0012\u0002@A\u0019A#!\u0013\u0005\ri\f)D1\u0001\u0018\u0011\u001d\u0001\u0017Q\u0007a\u0001\u0003\u001b\u0002RA\u00032\u0014\u0003\u000fBq!!\u0015\u0001\t\u0003\t\u0019&A\u0004gY\u0006$X*\u00199\u0016\r\u0005U\u00131MA.)\u0011\t9&!\u001a\u0015\t\u0005e\u0013Q\f\t\u0004)\u0005mCA\u0002:\u0002P\t\u0007q\u0003C\u0004u\u0003\u001f\u0002\u001d!a\u0018\u0011\u000f918$!\u0019\u0002ZA\u0019A#a\u0019\u0005\ri\fyE1\u0001\u0018\u0011\u001d\u0001\u0017q\na\u0001\u0003O\u0002RA\u00032\u0014\u0003S\u0002BAI@\u0002b!9\u0011Q\u000e\u0001\u0005\n\u0005=\u0014A\u00034jYR,'/S7qYR)1$!\u001d\u0002x!A\u00111OA6\u0001\u0004\t)(A\u0001q!\u0011Q!mE \t\u000f\u0005e\u00141\u000ea\u0001\u007f\u0005I\u0011n\u001d$mSB\u0004X\r\u001a\u0005\b\u0003{\u0002A\u0011AA@\u0003\u00191\u0017\u000e\u001c;feR\u00191$!!\t\u0011\u0005M\u00141\u0010a\u0001\u0003kBq!!\"\u0001\t\u0003\t9)A\u0005gS2$XM\u001d(piR\u00191$!#\t\u0011\u0005M\u00141\u0011a\u0001\u0003kBq!!$\u0001\t\u0003\ty)A\u0004d_2dWm\u0019;\u0016\r\u0005E\u0015qTAL)\u0011\t\u0019*!)\u0015\t\u0005U\u0015\u0011\u0014\t\u0004)\u0005]EA\u0002:\u0002\f\n\u0007q\u0003C\u0004u\u0003\u0017\u0003\u001d!a'\u0011\u000f918$!(\u0002\u0016B\u0019A#a(\u0005\ri\fYI1\u0001\u0018\u0011!\t\u0019+a#A\u0002\u0005\u0015\u0016A\u00019g!\u0019Q\u0011qU\n\u0002\u001e&\u0019\u0011\u0011\u0016\u0003\u0003\u001fA\u000b'\u000f^5bY\u001a+hn\u0019;j_:Dq!!,\u0001\t\u0003\ty+A\u0005qCJ$\u0018\u000e^5p]R!\u0011\u0011WA\\!\u0015Q\u00111W\u000e\u001c\u0013\r\t)\f\u0002\u0002\u0007)V\u0004H.\u001a\u001a\t\u0011\u0005M\u00141\u0016a\u0001\u0003kBq!a/\u0001\t\u0003\ti,A\u0004he>,\bOQ=\u0016\t\u0005}\u0016q\u001a\u000b\u0005\u0003\u0003\f\u0019\u000eE\u0004\u0002D\u0006%\u0017QZ\u000e\u000e\u0005\u0005\u0015'bAAd\u0005\u0005I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u0003\u0017\f)MA\u0002NCB\u00042\u0001FAh\t\u001d\t\t.!/C\u0002]\u0011\u0011a\u0013\u0005\bA\u0006e\u0006\u0019AAk!\u0015Q!mEAg\u0011\u001d\tI\u000e\u0001C\u0001\u00037\faAZ8sC2dGcA \u0002^\"A\u00111OAl\u0001\u0004\t)\bC\u0004\u0002b\u0002!\t!a9\u0002\r\u0015D\u0018n\u001d;t)\ry\u0014Q\u001d\u0005\t\u0003g\ny\u000e1\u0001\u0002v!9\u0011\u0011\u001e\u0001\u0005\u0002\u0005-\u0018\u0001\u00024j]\u0012$B!!<\u0002tB!!\"a<\u0014\u0013\r\t\t\u0010\u0002\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005M\u0014q\u001da\u0001\u0003kBq!a>\u0001\t\u0003\tI0\u0001\u0003tG\u0006tWCBA~\u0005\u001b\u0011\u0019\u0001\u0006\u0003\u0002~\neA\u0003BA\u0000\u0005\u001f!BA!\u0001\u0003\u0006A\u0019ACa\u0001\u0005\rI\f)P1\u0001\u0018\u0011!\u00119!!>A\u0004\t%\u0011aA2cMB9aB^\u000e\u0003\f\t\u0005\u0001c\u0001\u000b\u0003\u000e\u00111!0!>C\u0002mD\u0001B!\u0005\u0002v\u0002\u0007!1C\u0001\u0003_B\u0004\u0012B\u0003B\u000b\u0005\u0017\u0011YAa\u0003\n\u0007\t]AAA\u0005Gk:\u001cG/[8oe!A!1DA{\u0001\u0004\u0011Y!A\u0001{\u0011\u001d\u0011y\u0002\u0001C\u0001\u0005C\t\u0001b]2b]2+g\r^\u000b\u0007\u0005G\u0011\u0019Da\u000b\u0015\t\t\u0015\"\u0011\b\u000b\u0005\u0005O\u0011)\u0004\u0006\u0003\u0003*\t5\u0002c\u0001\u000b\u0003,\u00111!O!\bC\u0002]Aq\u0001\u001eB\u000f\u0001\b\u0011y\u0003E\u0004\u000fmn\u0011\tD!\u000b\u0011\u0007Q\u0011\u0019\u0004\u0002\u0004{\u0005;\u0011\ra\u0006\u0005\t\u0005#\u0011i\u00021\u0001\u00038AA!B!\u0006\u00032M\u0011\t\u0004\u0003\u0005\u0003\u001c\tu\u0001\u0019\u0001B\u0019\u0011\u001d\u0011i\u0004\u0001C\u0001\u0005\u007f\t\u0011b]2b]JKw\r\u001b;\u0016\r\t\u0005#\u0011\u000bB%)\u0011\u0011\u0019Ea\u0016\u0015\t\t\u0015#1\u000b\u000b\u0005\u0005\u000f\u0012Y\u0005E\u0002\u0015\u0005\u0013\"aA\u001dB\u001e\u0005\u00049\u0002b\u0002;\u0003<\u0001\u000f!Q\n\t\b\u001dY\\\"q\nB$!\r!\"\u0011\u000b\u0003\u0007u\nm\"\u0019A\f\t\u0011\tE!1\ba\u0001\u0005+\u0002\u0002B\u0003B\u000b'\t=#q\n\u0005\t\u00057\u0011Y\u00041\u0001\u0003P!B!1\bB.\u0005O\u0012Y\u0007\u0005\u0003\u0003^\t\rTB\u0001B0\u0015\r\u0011\t\u0007B\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002\u0002B3\u0005?\u0012\u0011\"\\5he\u0006$\u0018n\u001c8\"\u0005\t%\u0014\u0001\u001b+iK\u0002\u0012W\r[1wS>\u0014\be\u001c4!AN\u001c\u0017M\u001c*jO\"$\b\r\t5bg\u0002\u001a\u0007.\u00198hK\u0012t\u0003\u0005\u00165fAA\u0014XM^5pkN\u0004#-\u001a5bm&|'\u000fI2b]\u0002\u0012W\r\t:faJ|G-^2fI\u0002:\u0018\u000e\u001e5!g\u000e\fgNU5hQRt#/\u001a<feN,g&\t\u0002\u0003n\u0005)!GL\u001d/a!9!\u0011\u000f\u0001\u0005\u0002\tM\u0014\u0001\u00025fC\u0012,\u0012a\u0005\u0005\b\u0005o\u0002A\u0011\u0001B=\u0003)AW-\u00193PaRLwN\\\u000b\u0003\u0003[DaA! \u0001\t\u0003Z\u0014\u0001\u0002;bS2DqA!!\u0001\t\u0003\u0011\u0019(\u0001\u0003mCN$\bb\u0002BC\u0001\u0011\u0005!\u0011P\u0001\u000bY\u0006\u001cHo\u00149uS>t\u0007B\u0002BE\u0001\u0011\u00051(\u0001\u0003j]&$\bb\u0002BG\u0001\u0011\u0005!qR\u0001\u0005i\u0006\\W\rF\u0002\u001c\u0005#C\u0001Ba%\u0003\f\u0002\u0007!QS\u0001\u0002]B\u0019!Ba&\n\u0007\teEAA\u0002J]RDqA!(\u0001\t\u0003\u0011y*\u0001\u0003ee>\u0004HcA\u000e\u0003\"\"A!1\u0013BN\u0001\u0004\u0011)\nC\u0004\u0003&\u0002!\tAa*\u0002\u000bMd\u0017nY3\u0015\u000bm\u0011IK!,\t\u0011\t-&1\u0015a\u0001\u0005+\u000bAA\u001a:p[\"A!q\u0016BR\u0001\u0004\u0011)*A\u0003v]RLG\u000e\u0003\u0005\u00034\u0002\u0001K\u0011\u0002B[\u00035\u0019H.[2f\u0013:$XM\u001d8bYR91Da.\u0003:\nm\u0006\u0002\u0003BV\u0005c\u0003\rA!&\t\u0011\t=&\u0011\u0017a\u0001\u0005+CqA!0\u00032\u0002\u0007q*A\u0001c\u0011!\u0011\t\r\u0001C\u0001\t\t\r\u0017aE:mS\u000e,w+\u001b;i\u0017:|wO\u001c#fYR\fGcB\u000e\u0003F\n\u001d'\u0011\u001a\u0005\t\u0005W\u0013y\f1\u0001\u0003\u0016\"A!q\u0016B`\u0001\u0004\u0011)\n\u0003\u0005\u0003L\n}\u0006\u0019\u0001BK\u0003\u0015!W\r\u001c;b\u0011!\u0011y\r\u0001C\u0001\t\tE\u0017aE:mS\u000e,w+\u001b;i\u0017:|wO\u001c\"pk:$G#B\u000e\u0003T\nU\u0007\u0002\u0003BV\u0005\u001b\u0004\rA!&\t\u0011\t=&Q\u001aa\u0001\u0005+CqA!7\u0001\t\u0003\u0011Y.A\u0005uC.,w\u000b[5mKR\u00191D!8\t\u0011\u0005M$q\u001ba\u0001\u0003kBqA!9\u0001\t\u0003\u0011\u0019/A\u0005ee>\u0004x\u000b[5mKR\u00191D!:\t\u0011\u0005M$q\u001ca\u0001\u0003kBqA!;\u0001\t\u0003\u0011Y/\u0001\u0003ta\u0006tG\u0003BAY\u0005[D\u0001\"a\u001d\u0003h\u0002\u0007\u0011Q\u000f\u0005\b\u0005c\u0004A\u0011\u0001Bz\u0003\u001d\u0019\b\u000f\\5u\u0003R$B!!-\u0003v\"A!1\u0013Bx\u0001\u0004\u0011)\nC\u0004\u0003z\u0002!\tAa?\u0002\u000bQ\f\u0017\u000e\\:\u0016\u0005\tu\b\u0003\u0002\u0012\u0003\u0000nI1a!\u0001\u0003\u0005!IE/\u001a:bi>\u0014\bbBB\u0003\u0001\u0011\u0005!1`\u0001\u0006S:LGo\u001d\u0005\b\u0007\u0013\u0001A\u0011AB\u0006\u0003-\u0019w\u000e]=U_\u0006\u0013(/Y=\u0016\t\r511\u0004\u000b\bi\r=1QDB\u0011\u0011!\u0019\tba\u0002A\u0002\rM\u0011A\u0001=t!\u0015Q1QCB\r\u0013\r\u00199\u0002\u0002\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004)\rmAA\u0002>\u0004\b\t\u00071\u0010\u0003\u0005\u0004 \r\u001d\u0001\u0019\u0001BK\u0003\u0015\u0019H/\u0019:u\u0011!\u0019\u0019ca\u0002A\u0002\tU\u0015a\u00017f]\"11q\u0005\u0001\u0005\u0002\u0011\u000bQ\u0002^8Ue\u00064XM]:bE2,\u0007\u0006CB\u0013\u0007W\u0019\td!\u000e\u0011\u0007)\u0019i#C\u0002\u00040\u0011\u0011A\u0003Z3qe\u0016\u001c\u0017\r^3e\u001fZ,'O]5eS:<\u0017EAB\u001a\u00039+eNZ8sG\u0016\u00043m\u001c8ue\u0006\u001cG\u000fI8gAQ|GK]1wKJ\u001c\u0018M\u00197fAQD\u0017\r\u001e\u0011jM\u0002JG\u000fI5tAQ\u0013\u0018M^3sg\u0006\u0014G.\u001a\u0011ji\u0002\u0012X\r^;s]N\u0004\u0013\u000e^:fY\u001at\u0013EAB\u001c\u0003\u0019\u0011d&M\u0019/a!911\b\u0001\u0005\u0002\ru\u0012A\u0003;p\u0013R,'/\u0019;peV\u00111q\b\t\u0005E\t}8\u0003C\u0004\u0004D\u0001!\ta!\u0012\u0002\u0011Q|7\u000b\u001e:fC6,\"aa\u0012\u0011\u000b\r%3qJ\n\u000f\u0007)\u0019Y%C\u0002\u0004N\u0011\tq\u0001]1dW\u0006<W-\u0003\u0003\u0004R\rM#AB*ue\u0016\fWNC\u0002\u0004N\u0011Aqaa\u0016\u0001\t\u0003\u001aI&\u0001\u0002u_V!11LB0)\u0011\u0019if!\u001f\u0011\u000bQ\u0019yf!\u001b\u0005\u0011\r\u00054Q\u000bb\u0001\u0007G\u00121aQ8m+\r92Q\r\u0003\b\u0007O\u001ayF1\u0001\u0018\u0005\u0005y&fA\n\u0004l-\u00121Q\u000e\t\u0005\u0007_\u001a)(\u0004\u0002\u0004r)!11\u000fB0\u0003%)hn\u00195fG.,G-\u0003\u0003\u0004x\rE$!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\"A!qAB+\u0001\b\u0019Y\b\u0005\u0004\u000fmb\u00192Q\f\u0005\b\u0007\u007f\u0002A\u0011IBA\u0003!!xn\u0015;sS:<GCABB!\u0011\u0019)ia#\u000f\u0007)\u00199)C\u0002\u0004\n\u0012\ta\u0001\u0015:fI\u00164\u0017\u0002BBG\u0007\u001f\u0013aa\u0015;sS:<'bABE\t!911\u0013\u0001\u0005\u0002\rU\u0015\u0001D:ue&tw\r\u0015:fM&DXCABB\u0011\u001d\u0019I\n\u0001C\u0001\u00077\u000bAA^5foV\u00111Q\u0014\n\u0007\u0007?\u001b\u0019k!+\u0007\u000f\r\u00056q\u0013\u0001\u0004\u001e\naAH]3gS:,W.\u001a8u}A\u0019!b!*\n\u0007\r\u001dFA\u0001\u0004B]f\u0014VM\u001a\t\u0006E\r-6cG\u0005\u0004\u0007[\u0013!a\u0004+sCZ,'o]1cY\u00164\u0016.Z<\t\u000f\re\u0005\u0001\"\u0001\u00042R11\u0011VBZ\u0007kC\u0001Ba+\u00040\u0002\u0007!Q\u0013\u0005\t\u0005_\u001by\u000b1\u0001\u0003\u0016\"91\u0011\u0018\u0001\u0005\u0002\rm\u0016AC<ji\"4\u0015\u000e\u001c;feR\u0019ad!0\t\u0011\u0005M4q\u0017a\u0001\u0003k2aa!1\u0001\u0001\r\r'AC,ji\"4\u0015\u000e\u001c;feN)1qXBR=!Y\u00111OB`\u0005\u0003\u0005\u000b\u0011BA;\u0011!\u0019Ima0\u0005\u0002\r-\u0017A\u0002\u001fj]&$h\b\u0006\u0003\u0004N\u000eE\u0007\u0003BBh\u0007\u007fk\u0011\u0001\u0001\u0005\t\u0003g\u001a9\r1\u0001\u0002v!A\u0011qGB`\t\u0003\u0019).\u0006\u0004\u0004X\u000e\u00158Q\u001c\u000b\u0005\u00073\u001c9\u000f\u0006\u0003\u0004\\\u000e}\u0007c\u0001\u000b\u0004^\u00121!oa5C\u0002]Aq\u0001^Bj\u0001\b\u0019\t\u000fE\u0004\u000fmn\u0019\u0019oa7\u0011\u0007Q\u0019)\u000f\u0002\u0004{\u0007'\u0014\ra\u0006\u0005\bA\u000eM\u0007\u0019ABu!\u0015Q!mEBr\u0011!\t\tfa0\u0005\u0002\r5XCBBx\u0007{\u001c)\u0010\u0006\u0003\u0004r\u000e}H\u0003BBz\u0007o\u00042\u0001FB{\t\u0019\u001181\u001eb\u0001/!9Aoa;A\u0004\re\bc\u0002\bw7\rm81\u001f\t\u0004)\ruHA\u0002>\u0004l\n\u0007q\u0003C\u0004a\u0007W\u0004\r\u0001\"\u0001\u0011\u000b)\u00117\u0003b\u0001\u0011\t\tz81 \u0005\b9\u000e}F\u0011\u0001C\u0004+\u0011!I\u0001\"\u0005\u0015\u0007Q\"Y\u0001C\u0004a\t\u000b\u0001\r\u0001\"\u0004\u0011\u000b)\u00117\u0003b\u0004\u0011\u0007Q!\t\u0002\u0002\u0004g\t\u000b\u0011\ra\u0006\u0005\t\u0007s\u001by\f\"\u0001\u0005\u0016Q!1Q\u001aC\f\u0011!!I\u0002b\u0005A\u0002\u0005U\u0014!A9\t\u000f\u0011u\u0001\u0001\"\u0003\u0005 \u0005\t\u0012\u000e^3sCR,WK\u001c;jY\u0016k\u0007\u000f^=\u0015\t\tuH\u0011\u0005\u0005\bA\u0012m\u0001\u0019\u0001C\u0012!\u0019Q!\r\"\n\u0005&A!!ERB5!\u0011\u0011\u0003aE\u000e")
public interface TraversableLike<A, Repr>
extends HasNewBuilder<A, Repr>,
FilterMonadic<A, Repr>,
TraversableOnce<A>,
GenTraversableLike<A, Repr> {
    @Override
    public Repr repr();

    @Override
    public boolean isTraversableAgain();

    public Traversable<A> thisCollection();

    public Traversable<A> toCollection(Repr var1);

    @Override
    public Builder<A, Repr> newBuilder();

    @Override
    public Combiner<A, ParIterable<A>> parCombiner();

    @Override
    public <U> void foreach(Function1<A, U> var1);

    @Override
    public boolean isEmpty();

    @Override
    public boolean hasDefiniteSize();

    @Override
    public <B, That> That $plus$plus(GenTraversableOnce<B> var1, CanBuildFrom<Repr, B, That> var2);

    public <B, That> That $plus$plus$colon(TraversableOnce<B> var1, CanBuildFrom<Repr, B, That> var2);

    public <B, That> That $plus$plus$colon(Traversable<B> var1, CanBuildFrom<Repr, B, That> var2);

    @Override
    public <B, That> That map(Function1<A, B> var1, CanBuildFrom<Repr, B, That> var2);

    @Override
    public <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> var1, CanBuildFrom<Repr, B, That> var2);

    @Override
    public Repr filter(Function1<A, Object> var1);

    @Override
    public Repr filterNot(Function1<A, Object> var1);

    @Override
    public <B, That> That collect(PartialFunction<A, B> var1, CanBuildFrom<Repr, B, That> var2);

    @Override
    public Tuple2<Repr, Repr> partition(Function1<A, Object> var1);

    @Override
    public <K> Map<K, Repr> groupBy(Function1<A, K> var1);

    @Override
    public boolean forall(Function1<A, Object> var1);

    @Override
    public boolean exists(Function1<A, Object> var1);

    @Override
    public Option<A> find(Function1<A, Object> var1);

    @Override
    public <B, That> That scan(B var1, Function2<B, B, B> var2, CanBuildFrom<Repr, B, That> var3);

    @Override
    public <B, That> That scanLeft(B var1, Function2<B, A, B> var2, CanBuildFrom<Repr, B, That> var3);

    @Override
    public <B, That> That scanRight(B var1, Function2<A, B, B> var2, CanBuildFrom<Repr, B, That> var3);

    @Override
    public A head();

    @Override
    public Option<A> headOption();

    @Override
    public Repr tail();

    @Override
    public A last();

    @Override
    public Option<A> lastOption();

    @Override
    public Repr init();

    @Override
    public Repr take(int var1);

    @Override
    public Repr drop(int var1);

    @Override
    public Repr slice(int var1, int var2);

    public Repr sliceWithKnownDelta(int var1, int var2, int var3);

    public Repr sliceWithKnownBound(int var1, int var2);

    @Override
    public Repr takeWhile(Function1<A, Object> var1);

    @Override
    public Repr dropWhile(Function1<A, Object> var1);

    @Override
    public Tuple2<Repr, Repr> span(Function1<A, Object> var1);

    @Override
    public Tuple2<Repr, Repr> splitAt(int var1);

    public Iterator<Repr> tails();

    public Iterator<Repr> inits();

    @Override
    public <B> void copyToArray(Object var1, int var2, int var3);

    @Override
    public Traversable<A> toTraversable();

    @Override
    public Iterator<A> toIterator();

    @Override
    public Stream<A> toStream();

    @Override
    public <Col> Col to(CanBuildFrom<Nothing$, A, Col> var1);

    public String toString();

    @Override
    public String stringPrefix();

    public Object view();

    public TraversableView<A, Repr> view(int var1, int var2);

    @Override
    public FilterMonadic<A, Repr> withFilter(Function1<A, Object> var1);

    public class WithFilter
    implements FilterMonadic<A, Repr> {
        public final Function1<A, Object> scala$collection$TraversableLike$WithFilter$$p;
        public final /* synthetic */ TraversableLike $outer;

        @Override
        public <B, That> That map(Function1<A, B> f, CanBuildFrom<Repr, B, That> bf) {
            Builder<B, That> b = bf.apply(this.scala$collection$TraversableLike$WithFilter$$$outer().repr());
            this.scala$collection$TraversableLike$WithFilter$$$outer().foreach(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ WithFilter $outer;
                private final Function1 f$1;
                private final Builder b$12;

                public final Object apply(A x) {
                    return BoxesRunTime.unboxToBoolean(this.$outer.scala$collection$TraversableLike$WithFilter$$p.apply(x)) ? this.b$12.$plus$eq(this.f$1.apply(x)) : BoxedUnit.UNIT;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.f$1 = f$1;
                    this.b$12 = b$12;
                }
            });
            return b.result();
        }

        @Override
        public <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> f, CanBuildFrom<Repr, B, That> bf) {
            Builder<B, That> b = bf.apply(this.scala$collection$TraversableLike$WithFilter$$$outer().repr());
            this.scala$collection$TraversableLike$WithFilter$$$outer().foreach(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ WithFilter $outer;
                private final Function1 f$2;
                private final Builder b$13;

                public final Object apply(A x) {
                    return BoxesRunTime.unboxToBoolean(this.$outer.scala$collection$TraversableLike$WithFilter$$p.apply(x)) ? this.b$13.$plus$plus$eq(((GenTraversableOnce)this.f$2.apply(x)).seq()) : BoxedUnit.UNIT;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.f$2 = f$2;
                    this.b$13 = b$13;
                }
            });
            return b.result();
        }

        @Override
        public <U> void foreach(Function1<A, U> f) {
            this.scala$collection$TraversableLike$WithFilter$$$outer().foreach(new Serializable(this, f){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ WithFilter $outer;
                private final Function1 f$3;

                public final Object apply(A x) {
                    return BoxesRunTime.unboxToBoolean(this.$outer.scala$collection$TraversableLike$WithFilter$$p.apply(x)) ? this.f$3.apply(x) : BoxedUnit.UNIT;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.f$3 = f$3;
                }
            });
        }

        public WithFilter withFilter(Function1<A, Object> q) {
            return new WithFilter(this.scala$collection$TraversableLike$WithFilter$$$outer(), new Serializable(this, q){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ WithFilter $outer;
                private final Function1 q$1;

                public final boolean apply(A x) {
                    return BoxesRunTime.unboxToBoolean(this.$outer.scala$collection$TraversableLike$WithFilter$$p.apply(x)) && BoxesRunTime.unboxToBoolean(this.q$1.apply(x));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.q$1 = q$1;
                }
            });
        }

        public /* synthetic */ TraversableLike scala$collection$TraversableLike$WithFilter$$$outer() {
            return this.$outer;
        }

        public WithFilter(TraversableLike<A, Repr> $outer, Function1<A, Object> p) {
            this.scala$collection$TraversableLike$WithFilter$$p = p;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }
}

