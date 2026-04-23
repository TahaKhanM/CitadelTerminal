/*
 * Decompiled with CFR 0.152.
 */
package scala.io;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URL;
import scala.Console$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.AbstractIterator;
import scala.collection.BufferedIterator;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Iterator$class;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.StringOps;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.io.BufferedSource;
import scala.io.Codec;
import scala.io.Position;
import scala.io.Position$;
import scala.io.Source$;
import scala.io.Source$NoPositioner$;
import scala.io.Source$RelaxedPosition$;
import scala.io.Source$RelaxedPositioner$;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\rEw!B\u0001\u0003\u0011\u00039\u0011AB*pkJ\u001cWM\u0003\u0002\u0004\t\u0005\u0011\u0011n\u001c\u0006\u0002\u000b\u0005)1oY1mC\u000e\u0001\u0001C\u0001\u0005\n\u001b\u0005\u0011a!\u0002\u0006\u0003\u0011\u0003Y!AB*pkJ\u001cWm\u0005\u0002\n\u0019A\u0011QBD\u0007\u0002\t%\u0011q\u0002\u0002\u0002\u0007\u0003:L(+\u001a4\t\u000bEIA\u0011\u0001\n\u0002\rqJg.\u001b;?)\u00059\u0001b\u0002\u000b\n\u0005\u0004%\t!F\u0001\u000f\t\u00164\u0017-\u001e7u\u0005V47+\u001b>f+\u00051\u0002CA\u0007\u0018\u0013\tABAA\u0002J]RDaAG\u0005!\u0002\u00131\u0012a\u0004#fM\u0006,H\u000e\u001e\"vMNK'0\u001a\u0011\t\u000bqIA\u0011A\u000f\u0002\u000bM$H-\u001b8\u0016\u0003y\u0001\"\u0001C\u0010\n\u0005\u0001\u0012!A\u0004\"vM\u001a,'/\u001a3T_V\u00148-\u001a\u0005\u0006E%!\taI\u0001\rMJ|W.\u0013;fe\u0006\u0014G.\u001a\u000b\u0004I\t%\u0005C\u0001\u0005&\r\u0015Q!!!\u0001''\r)Cb\n\t\u0004Q-rcBA\u0007*\u0013\tQC!A\u0004qC\u000e\\\u0017mZ3\n\u00051j#\u0001C%uKJ\fGo\u001c:\u000b\u0005)\"\u0001CA\u00070\u0013\t\u0001DA\u0001\u0003DQ\u0006\u0014\b\"B\t&\t\u0003\u0011D#\u0001\u0013\t\u000fQ*#\u0019!D\tk\u0005!\u0011\u000e^3s+\u00059\u0003bB\u001c&\u0001\u0004%\t\u0001O\u0001\u0006I\u0016\u001c8M]\u000b\u0002sA\u0011!(\u0010\b\u0003\u001bmJ!\u0001\u0010\u0003\u0002\rA\u0013X\rZ3g\u0013\tqtH\u0001\u0004TiJLgn\u001a\u0006\u0003y\u0011Aq!Q\u0013A\u0002\u0013\u0005!)A\u0005eKN\u001c'o\u0018\u0013fcR\u00111I\u0012\t\u0003\u001b\u0011K!!\u0012\u0003\u0003\tUs\u0017\u000e\u001e\u0005\b\u000f\u0002\u000b\t\u00111\u0001:\u0003\rAH%\r\u0005\u0007\u0013\u0016\u0002\u000b\u0015B\u001d\u0002\r\u0011,7o\u0019:!\u0011\u001dYU\u00051A\u0005\u0002U\tqA\\3se>\u00148\u000fC\u0004NK\u0001\u0007I\u0011\u0001(\u0002\u00179,'O]8sg~#S-\u001d\u000b\u0003\u0007>Cqa\u0012'\u0002\u0002\u0003\u0007a\u0003\u0003\u0004RK\u0001\u0006KAF\u0001\t]\u0016\u0014(o\u001c:tA!91+\na\u0001\n\u0003)\u0012!\u00038xCJt\u0017N\\4t\u0011\u001d)V\u00051A\u0005\u0002Y\u000bQB\\<be:LgnZ:`I\u0015\fHCA\"X\u0011\u001d9E+!AA\u0002YAa!W\u0013!B\u00131\u0012A\u00038xCJt\u0017N\\4tA!)1,\nC\u00059\u00069A.\u001b8f\u001dVlGCA\u001d^\u0011\u0015q&\f1\u0001\u0017\u0003\u0011a\u0017N\\3\u0007\t\u0001,\u0003!\u0019\u0002\r\u0019&tW-\u0013;fe\u0006$xN]\n\u0004?\nD\u0007cA2gs5\tAM\u0003\u0002f\t\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\u001d$'\u0001E!cgR\u0014\u0018m\u0019;Ji\u0016\u0014\u0018\r^8s!\rA3&\u000f\u0005\u0006#}#\tA\u001b\u000b\u0002WB\u0011AnX\u0007\u0002K!1an\u0018Q\u0001\n=\f!a\u001d2\u0011\u0005A\u001cX\"A9\u000b\u0005I$\u0017aB7vi\u0006\u0014G.Z\u0005\u0003iF\u0014Qb\u0015;sS:<')^5mI\u0016\u0014\b\u0002\u0003\u001b`\u0011\u000b\u0007I\u0011\u0001<\u0016\u0003]\u00042\u0001\u000b=/\u0013\tIXF\u0001\tCk\u001a4WM]3e\u0013R,'/\u0019;pe\"A1p\u0018E\u0001B\u0003&q/A\u0003ji\u0016\u0014\b\u0005C\u0003~?\u0012\u0005a0A\u0005jg:+w\u000f\\5oKR\u0019q0!\u0002\u0011\u00075\t\t!C\u0002\u0002\u0004\u0011\u0011qAQ8pY\u0016\fg\u000e\u0003\u0004\u0002\bq\u0004\rAL\u0001\u0003G\"Dq!a\u0003`\t\u0003\ti!\u0001\u0003hKR\u001cG#A@\t\u000f\u0005Eq\f\"\u0001\u0002\u0014\u00059\u0001.Y:OKb$X#A@\t\u000f\u0005]q\f\"\u0001\u0002\u001a\u0005!a.\u001a=u)\t\tY\u0002\u0005\u0003\u0002\u001e\u0005\u001dRBAA\u0010\u0015\u0011\t\t#a\t\u0002\t1\fgn\u001a\u0006\u0003\u0003K\tAA[1wC&\u0019a(a\b\t\u000f\u0005-R\u0005\"\u0001\u0002.\u0005Aq-\u001a;MS:,7\u000fF\u0001i\u0011\u001d\t\t\"\nC\u0001\u0003'Aq!a\u0006&\t\u0003\t\u0019\u0004F\u0001/\r\u0019\t9$\n\u0001\u0002:\tQ\u0001k\\:ji&|g.\u001a:\u0014\u0007\u0005UB\u0002C\u0006\u0002>\u0005U\"\u0011!Q\u0001\n\u0005}\u0012aB3oG>$WM\u001d\t\u0004\u0011\u0005\u0005\u0013bAA\"\u0005\tA\u0001k\\:ji&|g\u000eC\u0004\u0012\u0003k!\t!a\u0012\u0015\t\u0005%\u00131\n\t\u0004Y\u0006U\u0002\u0002CA\u001f\u0003\u000b\u0002\r!a\u0010\t\u000fE\t)\u0004\"\u0001\u0002PQ\u0011\u0011\u0011\n\u0005\r\u0003\u000f\t)\u00041AA\u0002\u0013\u0005\u00111K\u000b\u0002]!a\u0011qKA\u001b\u0001\u0004\u0005\r\u0011\"\u0001\u0002Z\u000511\r[0%KF$2aQA.\u0011!9\u0015QKA\u0001\u0002\u0004q\u0003\u0002CA0\u0003k\u0001\u000b\u0015\u0002\u0018\u0002\u0007\rD\u0007\u0005C\u0005\u0002d\u0005U\u0002\u0019!C\u0001+\u0005\u0019\u0001o\\:\t\u0015\u0005\u001d\u0014Q\u0007a\u0001\n\u0003\tI'A\u0004q_N|F%Z9\u0015\u0007\r\u000bY\u0007\u0003\u0005H\u0003K\n\t\u00111\u0001\u0017\u0011!\ty'!\u000e!B\u00131\u0012\u0001\u00029pg\u0002B\u0011\"a\u001d\u00026\u0001\u0007I\u0011A\u000b\u0002\u000b\rd\u0017N\\3\t\u0015\u0005]\u0014Q\u0007a\u0001\n\u0003\tI(A\u0005dY&tWm\u0018\u0013fcR\u00191)a\u001f\t\u0011\u001d\u000b)(!AA\u0002YA\u0001\"a \u00026\u0001\u0006KAF\u0001\u0007G2Lg.\u001a\u0011\t\u0013\u0005\r\u0015Q\u0007a\u0001\n\u0003)\u0012\u0001B2d_2D!\"a\"\u00026\u0001\u0007I\u0011AAE\u0003!\u00197m\u001c7`I\u0015\fHcA\"\u0002\f\"Aq)!\"\u0002\u0002\u0003\u0007a\u0003\u0003\u0005\u0002\u0010\u0006U\u0002\u0015)\u0003\u0017\u0003\u0015\u00197m\u001c7!\u0011%\t\u0019*!\u000eA\u0002\u0013\u0005Q#\u0001\u0004uC\nLgn\u0019\u0005\u000b\u0003/\u000b)\u00041A\u0005\u0002\u0005e\u0015A\u0003;bE&t7m\u0018\u0013fcR\u00191)a'\t\u0011\u001d\u000b)*!AA\u0002YA\u0001\"a(\u00026\u0001\u0006KAF\u0001\bi\u0006\u0014\u0017N\\2!\u0011!\t9\"!\u000e\u0005\u0002\u0005MraBASK!\u0005\u0011qU\u0001\u0010%\u0016d\u0017\r_3e!>\u001c\u0018\u000e^5p]B\u0019A.!+\u0007\u000f\u0005-V\u0005#\u0001\u0002.\ny!+\u001a7bq\u0016$\u0007k\\:ji&|gn\u0005\u0003\u0002*\u0006}\u0002bB\t\u0002*\u0012\u0005\u0011\u0011\u0017\u000b\u0003\u0003OC\u0001\"!.\u0002*\u0012\u0005\u0011qW\u0001\u000bG\",7m[%oaV$H#B\"\u0002:\u0006m\u0006B\u00020\u00024\u0002\u0007a\u0003C\u0004\u0002>\u0006M\u0006\u0019\u0001\f\u0002\r\r|G.^7o\u000f\u001d\t\t-\nE\u0001\u0003\u0007\f\u0011CU3mCb,G\rU8tSRLwN\\3s!\ra\u0017Q\u0019\u0004\b\u0003\u000f,\u0003\u0012AAe\u0005E\u0011V\r\\1yK\u0012\u0004vn]5uS>tWM]\n\u0005\u0003\u000b\fI\u0005C\u0004\u0012\u0003\u000b$\t!!4\u0015\u0005\u0005\rwaBAiK!\u0005\u00111[\u0001\r\u001d>\u0004vn]5uS>tWM\u001d\t\u0004Y\u0006UgaBAlK!\u0005\u0011\u0011\u001c\u0002\r\u001d>\u0004vn]5uS>tWM]\n\u0005\u0003+\fI\u0005C\u0004\u0012\u0003+$\t!!8\u0015\u0005\u0005M\u0007\u0002CA\f\u0003+$\t%a\r\t\u000f\u0005\u001dQ\u0005\"\u0001\u0002T!1\u00111M\u0013\u0005\u0002UAq!a:&\t\u0003\tI/A\u0006sKB|'\u000f^#se>\u0014HcB\"\u0002l\u00065\u0018\u0011\u001f\u0005\b\u0003G\n)\u000f1\u0001\u0017\u0011\u001d\ty/!:A\u0002e\n1!\\:h\u0011)\t\u00190!:\u0011\u0002\u0003\u0007\u0011Q_\u0001\u0004_V$\b\u0003BA|\u0003wl!!!?\u000b\u0007\r\t\u0019#\u0003\u0003\u0002~\u0006e(a\u0003)sS:$8\u000b\u001e:fC6DqA!\u0001&\t\u0013\u0011\u0019!\u0001\u0004ta\u0006\u001cWm\u001d\u000b\u0004s\t\u0015\u0001b\u0002B\u0004\u0003\u007f\u0004\rAF\u0001\u0002]\"9!1B\u0013\u0005\u0002\t5\u0011A\u0002:fa>\u0014H\u000fF\u0004D\u0005\u001f\u0011\tBa\u0005\t\u000f\u0005\r$\u0011\u0002a\u0001-!9\u0011q\u001eB\u0005\u0001\u0004I\u0004\u0002CAz\u0005\u0013\u0001\r!!>\t\u000f\t]Q\u0005\"\u0001\u0003\u001a\u0005i!/\u001a9peR<\u0016M\u001d8j]\u001e$ra\u0011B\u000e\u0005;\u0011y\u0002C\u0004\u0002d\tU\u0001\u0019\u0001\f\t\u000f\u0005=(Q\u0003a\u0001s!Q\u00111\u001fB\u000b!\u0003\u0005\r!!>\t\u0011\t\rR\u0005)Q\u0005\u0005K\tQB]3tKR4UO\\2uS>t\u0007\u0003B\u0007\u0003(\u0011J1A!\u000b\u0005\u0005%1UO\\2uS>t\u0007\u0007\u0003\u0005\u0003.\u0015\u0002\u000b\u0015\u0002B\u0018\u00035\u0019Gn\\:f\rVt7\r^5p]B!QBa\nD\u0011!\u0011\u0019$\nQ!\n\u0005%\u0013A\u00039pg&$\u0018n\u001c8fe\"9!qG\u0013\u0005\u0002\te\u0012!C<ji\"\u0014Vm]3u)\ra'1\b\u0005\t\u0005{\u0011)\u00041\u0001\u0003&\u0005\ta\rC\u0004\u0003B\u0015\"\tAa\u0011\u0002\u0013]LG\u000f[\"m_N,Gc\u00017\u0003F!A!Q\bB \u0001\u0004\u0011y\u0003C\u0004\u0003J\u0015\"\tAa\u0013\u0002\u001f]LG\u000f\u001b#fg\u000e\u0014\u0018\u000e\u001d;j_:$2\u0001\u001cB'\u0011\u001d\u0011yEa\u0012A\u0002e\nA\u0001^3yi\"9!1K\u0013\u0005\u0002\tU\u0013aD<ji\"\u0004vn]5uS>t\u0017N\\4\u0015\u00071\u00149\u0006C\u0004\u0003Z\tE\u0003\u0019A@\u0002\u0005=t\u0007b\u0002B*K\u0011\u0005!Q\f\u000b\u0004Y\n}\u0003\u0002CA2\u00057\u0002\r!!\u0013\t\u000f\t\rT\u0005\"\u0001\u0003f\u0005)1\r\\8tKR\t1\t\u0003\u0004\u0003j\u0015\"\tAM\u0001\u0006e\u0016\u001cX\r\u001e\u0005\n\u0005[*\u0013\u0013!C\u0001\u0005_\nQC]3q_J$XI\u001d:pe\u0012\"WMZ1vYR$3'\u0006\u0002\u0003r)\"\u0011Q\u001fB:W\t\u0011)\b\u0005\u0003\u0003x\t\u0005UB\u0001B=\u0015\u0011\u0011YH! \u0002\u0013Ut7\r[3dW\u0016$'b\u0001B@\t\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\t\r%\u0011\u0010\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007\"\u0003BDKE\u0005I\u0011\u0001B8\u0003]\u0011X\r]8si^\u000b'O\\5oO\u0012\"WMZ1vYR$3\u0007C\u0004\u0003\f\u0006\u0002\rA!$\u0002\u0011%$XM]1cY\u0016\u0004B\u0001\u000bBH]%\u0019!\u0011S\u0017\u0003\u0011%#XM]1cY\u0016DqA!&\n\t\u0003\u00119*\u0001\u0005ge>l7\t[1s)\r!#\u0011\u0014\u0005\b\u00057\u0013\u0019\n1\u0001/\u0003\u0005\u0019\u0007b\u0002BP\u0013\u0011\u0005!\u0011U\u0001\nMJ|Wn\u00115beN$2\u0001\nBR\u0011!\u0011)K!(A\u0002\t\u001d\u0016!B2iCJ\u001c\b\u0003B\u0007\u0003*:J1Aa+\u0005\u0005\u0015\t%O]1z\u0011\u001d\u0011y+\u0003C\u0001\u0005c\u000b!B\u001a:p[N#(/\u001b8h)\r!#1\u0017\u0005\b\u0005k\u0013i\u000b1\u0001:\u0003\u0005\u0019\bb\u0002B]\u0013\u0011\u0005!1X\u0001\tMJ|WNR5mKR!!Q\u0018Be)\rq\"q\u0018\u0005\t\u0005\u0003\u00149\fq\u0001\u0003D\u0006)1m\u001c3fGB\u0019\u0001B!2\n\u0007\t\u001d'AA\u0003D_\u0012,7\rC\u0004\u0003L\n]\u0006\u0019A\u001d\u0002\t9\fW.\u001a\u0005\b\u0005sKA\u0011\u0001Bh)\u0015q\"\u0011\u001bBj\u0011\u001d\u0011YM!4A\u0002eBqA!6\u0003N\u0002\u0007\u0011(A\u0002f]\u000eDqA!/\n\t\u0003\u0011I\u000e\u0006\u0003\u0003\\\n}Gc\u0001\u0010\u0003^\"A!\u0011\u0019Bl\u0001\b\u0011\u0019\r\u0003\u0005\u0003b\n]\u0007\u0019\u0001Br\u0003\r)(/\u001b\t\u0005\u0005K\u0014Y/\u0004\u0002\u0003h*!!\u0011^A\u0012\u0003\rqW\r^\u0005\u0005\u0005[\u00149OA\u0002V%&CqA!/\n\t\u0003\u0011\t\u0010F\u0003\u001f\u0005g\u0014)\u0010\u0003\u0005\u0003b\n=\b\u0019\u0001Br\u0011\u001d\u0011)Na<A\u0002eBqA!/\n\t\u0003\u0011I\u0010\u0006\u0003\u0003|\n}Hc\u0001\u0010\u0003~\"A!\u0011\u0019B|\u0001\b\u0011\u0019\r\u0003\u0005\u0004\u0002\t]\b\u0019AB\u0002\u0003\u00111\u0017\u000e\\3\u0011\t\u0005]8QA\u0005\u0005\u0007\u000f\tIP\u0001\u0003GS2,\u0007b\u0002B]\u0013\u0011\u000511\u0002\u000b\u0006=\r51q\u0002\u0005\t\u0007\u0003\u0019I\u00011\u0001\u0004\u0004!9!Q[B\u0005\u0001\u0004I\u0004b\u0002B]\u0013\u0011\u000511\u0003\u000b\b=\rU1qCB\r\u0011!\u0019\ta!\u0005A\u0002\r\r\u0001b\u0002Bk\u0007#\u0001\r!\u000f\u0005\b\u00077\u0019\t\u00021\u0001\u0017\u0003)\u0011WO\u001a4feNK'0\u001a\u0005\b\u0005sKA\u0011AB\u0010)\u0019\u0019\tc!\n\u0004(Q\u0019ada\t\t\u0011\t\u00057Q\u0004a\u0002\u0005\u0007D\u0001b!\u0001\u0004\u001e\u0001\u000711\u0001\u0005\b\u00077\u0019i\u00021\u0001\u0017\u0011\u001d\u0019Y#\u0003C\u0001\u0007[\t\u0011B\u001a:p[\nKH/Z:\u0015\t\r=21\u0007\u000b\u0004I\rE\u0002\u0002\u0003Ba\u0007S\u0001\u001dAa1\t\u0011\rU2\u0011\u0006a\u0001\u0007o\tQAY=uKN\u0004R!\u0004BU\u0007s\u00012!DB\u001e\u0013\r\u0019i\u0004\u0002\u0002\u0005\u0005f$X\rC\u0004\u0004,%!\ta!\u0011\u0015\u000b\u0011\u001a\u0019e!\u0012\t\u0011\rU2q\ba\u0001\u0007oAqA!6\u0004@\u0001\u0007\u0011\bC\u0004\u0004J%!\taa\u0013\u0002\u0019\u0019\u0014x.\u001c*bo\nKH/Z:\u0015\u0007\u0011\u001ai\u0005\u0003\u0005\u00046\r\u001d\u0003\u0019AB\u001c\u0011\u001d\u0019\t&\u0003C\u0001\u0007'\nqA\u001a:p[V\u0013\u0016\n\u0006\u0003\u0004V\reCc\u0001\u0010\u0004X!A!\u0011YB(\u0001\b\u0011\u0019\r\u0003\u0005\u0003b\u000e=\u0003\u0019\u0001Br\u0011\u001d\u0019i&\u0003C\u0001\u0007?\nqA\u001a:p[V\u0013F\nF\u0003\u001f\u0007C\u001a\u0019\u0007C\u0004\u00036\u000em\u0003\u0019A\u001d\t\u000f\tU71\fa\u0001s!91QL\u0005\u0005\u0002\r\u001dD\u0003BB5\u0007[\"2AHB6\u0011!\u0011\tm!\u001aA\u0004\t\r\u0007b\u0002B[\u0007K\u0002\r!\u000f\u0005\b\u0007;JA\u0011AB9)\u0015q21OB?\u0011!\u0019)ha\u001cA\u0002\r]\u0014aA;sYB!!Q]B=\u0013\u0011\u0019YHa:\u0003\u0007U\u0013F\nC\u0004\u0003V\u000e=\u0004\u0019A\u001d\t\u000f\ru\u0013\u0002\"\u0001\u0004\u0002R!11QBD)\rq2Q\u0011\u0005\t\u0005\u0003\u001cy\bq\u0001\u0003D\"A1QOB@\u0001\u0004\u00199\bC\u0004\u0004\f&!\ta!$\u0002)\r\u0014X-\u0019;f\u0005V4g-\u001a:fIN{WO]2f))\u0019yia%\u0004\u001e\u000e}5\u0011\u0015\u000b\u0004=\rE\u0005\u0002\u0003Ba\u0007\u0013\u0003\u001dAa1\t\u0011\rU5\u0011\u0012a\u0001\u0007/\u000b1\"\u001b8qkR\u001cFO]3b[B!\u0011q_BM\u0013\u0011\u0019Y*!?\u0003\u0017%s\u0007/\u001e;TiJ,\u0017-\u001c\u0005\n\u00077\u0019I\t%AA\u0002YA!B!\u001b\u0004\nB\u0005\t\u0019\u0001B\u0013\u0011)\u0011\u0019g!#\u0011\u0002\u0003\u0007!q\u0006\u0005\b\u0007KKA\u0011ABT\u0003=1'o\\7J]B,Ho\u0015;sK\u0006lG#\u0002\u0010\u0004*\u000e5\u0006\u0002CBV\u0007G\u0003\raa&\u0002\u0005%\u001c\bb\u0002Bk\u0007G\u0003\r!\u000f\u0005\b\u0007KKA\u0011ABY)\u0011\u0019\u0019la.\u0015\u0007y\u0019)\f\u0003\u0005\u0003B\u000e=\u00069\u0001Bb\u0011!\u0019Yka,A\u0002\r]\u0005\"CB^\u0013E\u0005I\u0011AB_\u0003y\u0019'/Z1uK\n+hMZ3sK\u0012\u001cv.\u001e:dK\u0012\"WMZ1vYR$#'\u0006\u0002\u0004@*\u001aaCa\u001d\t\u0013\r\r\u0017\"%A\u0005\u0002\r\u0015\u0017AH2sK\u0006$XMQ;gM\u0016\u0014X\rZ*pkJ\u001cW\r\n3fM\u0006,H\u000e\u001e\u00134+\t\u00199M\u000b\u0003\u0003&\tM\u0004\"CBf\u0013E\u0005I\u0011ABg\u0003y\u0019'/Z1uK\n+hMZ3sK\u0012\u001cv.\u001e:dK\u0012\"WMZ1vYR$C'\u0006\u0002\u0004P*\"!q\u0006B:\u0001")
public abstract class Source
implements Iterator<Object> {
    private String descr;
    private int nerrors;
    private int nwarnings;
    private Function0<Source> resetFunction;
    private Function0<BoxedUnit> closeFunction;
    private Positioner positioner;
    private volatile Source$RelaxedPosition$ RelaxedPosition$module;
    private volatile Source$RelaxedPositioner$ RelaxedPositioner$module;
    private volatile Source$NoPositioner$ NoPositioner$module;

    public static Function0<BoxedUnit> createBufferedSource$default$4() {
        return Source$.MODULE$.createBufferedSource$default$4();
    }

    public static Function0<Source> createBufferedSource$default$3() {
        return Source$.MODULE$.createBufferedSource$default$3();
    }

    public static int createBufferedSource$default$2() {
        return Source$.MODULE$.createBufferedSource$default$2();
    }

    public static BufferedSource fromInputStream(InputStream inputStream, Codec codec) {
        return Source$.MODULE$.fromInputStream(inputStream, codec);
    }

    public static BufferedSource fromInputStream(InputStream inputStream, String string2) {
        return Source$.MODULE$.fromInputStream(inputStream, string2);
    }

    public static BufferedSource createBufferedSource(InputStream inputStream, int n, Function0<Source> function0, Function0<BoxedUnit> function02, Codec codec) {
        return Source$.MODULE$.createBufferedSource(inputStream, n, function0, function02, codec);
    }

    public static BufferedSource fromURL(URL uRL, Codec codec) {
        return Source$.MODULE$.fromURL(uRL, codec);
    }

    public static BufferedSource fromURL(URL uRL, String string2) {
        return Source$.MODULE$.fromURL(uRL, string2);
    }

    public static BufferedSource fromURL(String string2, Codec codec) {
        return Source$.MODULE$.fromURL(string2, codec);
    }

    public static BufferedSource fromURL(String string2, String string3) {
        return Source$.MODULE$.fromURL(string2, string3);
    }

    public static BufferedSource fromURI(URI uRI, Codec codec) {
        return Source$.MODULE$.fromURI(uRI, codec);
    }

    public static Source fromRawBytes(byte[] byArray) {
        return Source$.MODULE$.fromRawBytes(byArray);
    }

    public static Source fromBytes(byte[] byArray, String string2) {
        return Source$.MODULE$.fromBytes(byArray, string2);
    }

    public static Source fromBytes(byte[] byArray, Codec codec) {
        return Source$.MODULE$.fromBytes(byArray, codec);
    }

    public static BufferedSource fromFile(File file, int n, Codec codec) {
        return Source$.MODULE$.fromFile(file, n, codec);
    }

    public static BufferedSource fromFile(File file, String string2, int n) {
        return Source$.MODULE$.fromFile(file, string2, n);
    }

    public static BufferedSource fromFile(File file, String string2) {
        return Source$.MODULE$.fromFile(file, string2);
    }

    public static BufferedSource fromFile(File file, Codec codec) {
        return Source$.MODULE$.fromFile(file, codec);
    }

    public static BufferedSource fromFile(URI uRI, String string2) {
        return Source$.MODULE$.fromFile(uRI, string2);
    }

    public static BufferedSource fromFile(URI uRI, Codec codec) {
        return Source$.MODULE$.fromFile(uRI, codec);
    }

    public static BufferedSource fromFile(String string2, String string3) {
        return Source$.MODULE$.fromFile(string2, string3);
    }

    public static BufferedSource fromFile(String string2, Codec codec) {
        return Source$.MODULE$.fromFile(string2, codec);
    }

    public static Source fromString(String string2) {
        return Source$.MODULE$.fromString(string2);
    }

    public static Source fromChars(char[] cArray) {
        return Source$.MODULE$.fromChars(cArray);
    }

    public static Source fromChar(char c) {
        return Source$.MODULE$.fromChar(c);
    }

    public static Source fromIterable(Iterable<Object> iterable) {
        return Source$.MODULE$.fromIterable(iterable);
    }

    public static BufferedSource stdin() {
        return Source$.MODULE$.stdin();
    }

    public static int DefaultBufSize() {
        return Source$.MODULE$.DefaultBufSize();
    }

    private Source$RelaxedPosition$ RelaxedPosition$lzycompute() {
        Source source = this;
        synchronized (source) {
            if (this.RelaxedPosition$module == null) {
                this.RelaxedPosition$module = new Source$RelaxedPosition$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.RelaxedPosition$module;
        }
    }

    private Source$RelaxedPositioner$ RelaxedPositioner$lzycompute() {
        Source source = this;
        synchronized (source) {
            if (this.RelaxedPositioner$module == null) {
                this.RelaxedPositioner$module = new Source$RelaxedPositioner$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.RelaxedPositioner$module;
        }
    }

    private Source$NoPositioner$ NoPositioner$lzycompute() {
        Source source = this;
        synchronized (source) {
            if (this.NoPositioner$module == null) {
                this.NoPositioner$module = new Source$NoPositioner$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.NoPositioner$module;
        }
    }

    @Override
    public Iterator<Object> seq() {
        return Iterator$class.seq(this);
    }

    @Override
    public boolean isEmpty() {
        return Iterator$class.isEmpty(this);
    }

    @Override
    public boolean isTraversableAgain() {
        return Iterator$class.isTraversableAgain(this);
    }

    @Override
    public boolean hasDefiniteSize() {
        return Iterator$class.hasDefiniteSize(this);
    }

    @Override
    public Iterator<Object> take(int n) {
        return Iterator$class.take(this, n);
    }

    @Override
    public Iterator<Object> drop(int n) {
        return Iterator$class.drop(this, n);
    }

    @Override
    public Iterator<Object> slice(int from2, int until2) {
        return Iterator$class.slice(this, from2, until2);
    }

    @Override
    public <B> Iterator<B> map(Function1<Object, B> f) {
        return Iterator$class.map(this, f);
    }

    @Override
    public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
        return Iterator$class.$plus$plus(this, that);
    }

    @Override
    public <B> Iterator<B> flatMap(Function1<Object, GenTraversableOnce<B>> f) {
        return Iterator$class.flatMap(this, f);
    }

    @Override
    public Iterator<Object> filter(Function1<Object, Object> p) {
        return Iterator$class.filter(this, p);
    }

    @Override
    public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<Object, B, Object> p) {
        return Iterator$class.corresponds(this, that, p);
    }

    @Override
    public Iterator<Object> withFilter(Function1<Object, Object> p) {
        return Iterator$class.withFilter(this, p);
    }

    @Override
    public Iterator<Object> filterNot(Function1<Object, Object> p) {
        return Iterator$class.filterNot(this, p);
    }

    @Override
    public <B> Iterator<B> collect(PartialFunction<Object, B> pf) {
        return Iterator$class.collect(this, pf);
    }

    @Override
    public <B> Iterator<B> scanLeft(B z, Function2<B, Object, B> op) {
        return Iterator$class.scanLeft(this, z, op);
    }

    @Override
    public <B> Iterator<B> scanRight(B z, Function2<Object, B, B> op) {
        return Iterator$class.scanRight(this, z, op);
    }

    @Override
    public Iterator<Object> takeWhile(Function1<Object, Object> p) {
        return Iterator$class.takeWhile(this, p);
    }

    @Override
    public Tuple2<Iterator<Object>, Iterator<Object>> partition(Function1<Object, Object> p) {
        return Iterator$class.partition(this, p);
    }

    @Override
    public Tuple2<Iterator<Object>, Iterator<Object>> span(Function1<Object, Object> p) {
        return Iterator$class.span(this, p);
    }

    @Override
    public Iterator<Object> dropWhile(Function1<Object, Object> p) {
        return Iterator$class.dropWhile(this, p);
    }

    @Override
    public <B> Iterator<Tuple2<Object, B>> zip(Iterator<B> that) {
        return Iterator$class.zip(this, that);
    }

    @Override
    public <A1> Iterator<A1> padTo(int len, A1 elem) {
        return Iterator$class.padTo(this, len, elem);
    }

    @Override
    public Iterator<Tuple2<Object, Object>> zipWithIndex() {
        return Iterator$class.zipWithIndex(this);
    }

    @Override
    public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
        return Iterator$class.zipAll(this, that, thisElem, thatElem);
    }

    @Override
    public <U> void foreach(Function1<Object, U> f) {
        Iterator$class.foreach(this, f);
    }

    @Override
    public boolean forall(Function1<Object, Object> p) {
        return Iterator$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<Object, Object> p) {
        return Iterator$class.exists(this, p);
    }

    @Override
    public boolean contains(Object elem) {
        return Iterator$class.contains(this, elem);
    }

    @Override
    public Option<Object> find(Function1<Object, Object> p) {
        return Iterator$class.find(this, p);
    }

    @Override
    public int indexWhere(Function1<Object, Object> p) {
        return Iterator$class.indexWhere(this, p);
    }

    @Override
    public <B> int indexOf(B elem) {
        return Iterator$class.indexOf(this, elem);
    }

    @Override
    public BufferedIterator<Object> buffered() {
        return Iterator$class.buffered(this);
    }

    @Override
    public <B> Iterator.GroupedIterator<B> grouped(int size2) {
        return Iterator$class.grouped(this, size2);
    }

    @Override
    public <B> Iterator.GroupedIterator<B> sliding(int size2, int step) {
        return Iterator$class.sliding(this, size2, step);
    }

    @Override
    public int length() {
        return Iterator$class.length(this);
    }

    @Override
    public Tuple2<Iterator<Object>, Iterator<Object>> duplicate() {
        return Iterator$class.duplicate(this);
    }

    @Override
    public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
        return Iterator$class.patch(this, from2, patchElems, replaced);
    }

    @Override
    public <B> void copyToArray(Object xs, int start, int len) {
        Iterator$class.copyToArray(this, xs, start, len);
    }

    @Override
    public boolean sameElements(Iterator<?> that) {
        return Iterator$class.sameElements(this, that);
    }

    @Override
    public Traversable<Object> toTraversable() {
        return Iterator$class.toTraversable(this);
    }

    @Override
    public Iterator<Object> toIterator() {
        return Iterator$class.toIterator(this);
    }

    @Override
    public Stream<Object> toStream() {
        return Iterator$class.toStream(this);
    }

    @Override
    public String toString() {
        return Iterator$class.toString(this);
    }

    @Override
    public <B> int sliding$default$2() {
        return Iterator$class.sliding$default$2(this);
    }

    @Override
    public List<Object> reversed() {
        return TraversableOnce$class.reversed(this);
    }

    @Override
    public int size() {
        return TraversableOnce$class.size(this);
    }

    @Override
    public boolean nonEmpty() {
        return TraversableOnce$class.nonEmpty(this);
    }

    @Override
    public int count(Function1<Object, Object> p) {
        return TraversableOnce$class.count(this, p);
    }

    @Override
    public <B> Option<B> collectFirst(PartialFunction<Object, B> pf) {
        return TraversableOnce$class.collectFirst(this, pf);
    }

    @Override
    public <B> B $div$colon(B z, Function2<B, Object, B> op) {
        return (B)TraversableOnce$class.$div$colon(this, z, op);
    }

    @Override
    public <B> B $colon$bslash(B z, Function2<Object, B, B> op) {
        return (B)TraversableOnce$class.$colon$bslash(this, z, op);
    }

    @Override
    public <B> B foldLeft(B z, Function2<B, Object, B> op) {
        return (B)TraversableOnce$class.foldLeft(this, z, op);
    }

    @Override
    public <B> B foldRight(B z, Function2<Object, B, B> op) {
        return (B)TraversableOnce$class.foldRight(this, z, op);
    }

    @Override
    public <B> B reduceLeft(Function2<B, Object, B> op) {
        return (B)TraversableOnce$class.reduceLeft(this, op);
    }

    @Override
    public <B> B reduceRight(Function2<Object, B, B> op) {
        return (B)TraversableOnce$class.reduceRight(this, op);
    }

    @Override
    public <B> Option<B> reduceLeftOption(Function2<B, Object, B> op) {
        return TraversableOnce$class.reduceLeftOption(this, op);
    }

    @Override
    public <B> Option<B> reduceRightOption(Function2<Object, B, B> op) {
        return TraversableOnce$class.reduceRightOption(this, op);
    }

    @Override
    public <A1> A1 reduce(Function2<A1, A1, A1> op) {
        return (A1)TraversableOnce$class.reduce(this, op);
    }

    @Override
    public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
        return TraversableOnce$class.reduceOption(this, op);
    }

    @Override
    public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
        return (A1)TraversableOnce$class.fold(this, z, op);
    }

    @Override
    public <B> B aggregate(Function0<B> z, Function2<B, Object, B> seqop, Function2<B, B, B> combop) {
        return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
    }

    @Override
    public <B> B sum(Numeric<B> num) {
        return (B)TraversableOnce$class.sum(this, num);
    }

    @Override
    public <B> B product(Numeric<B> num) {
        return (B)TraversableOnce$class.product(this, num);
    }

    @Override
    public Object min(Ordering cmp) {
        return TraversableOnce$class.min(this, cmp);
    }

    @Override
    public Object max(Ordering cmp) {
        return TraversableOnce$class.max(this, cmp);
    }

    @Override
    public Object maxBy(Function1 f, Ordering cmp) {
        return TraversableOnce$class.maxBy(this, f, cmp);
    }

    @Override
    public Object minBy(Function1 f, Ordering cmp) {
        return TraversableOnce$class.minBy(this, f, cmp);
    }

    @Override
    public <B> void copyToBuffer(Buffer<B> dest) {
        TraversableOnce$class.copyToBuffer(this, dest);
    }

    @Override
    public <B> void copyToArray(Object xs, int start) {
        TraversableOnce$class.copyToArray(this, xs, start);
    }

    @Override
    public <B> void copyToArray(Object xs) {
        TraversableOnce$class.copyToArray(this, xs);
    }

    @Override
    public <B> Object toArray(ClassTag<B> evidence$1) {
        return TraversableOnce$class.toArray(this, evidence$1);
    }

    @Override
    public List<Object> toList() {
        return TraversableOnce$class.toList(this);
    }

    @Override
    public Iterable<Object> toIterable() {
        return TraversableOnce$class.toIterable(this);
    }

    @Override
    public Seq<Object> toSeq() {
        return TraversableOnce$class.toSeq(this);
    }

    @Override
    public IndexedSeq<Object> toIndexedSeq() {
        return TraversableOnce$class.toIndexedSeq(this);
    }

    @Override
    public <B> Buffer<B> toBuffer() {
        return TraversableOnce$class.toBuffer(this);
    }

    @Override
    public <B> Set<B> toSet() {
        return TraversableOnce$class.toSet(this);
    }

    @Override
    public Vector<Object> toVector() {
        return TraversableOnce$class.toVector(this);
    }

    @Override
    public <Col> Col to(CanBuildFrom<Nothing$, Object, Col> cbf) {
        return (Col)TraversableOnce$class.to(this, cbf);
    }

    @Override
    public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Object, Tuple2<T, U>> ev) {
        return TraversableOnce$class.toMap(this, ev);
    }

    @Override
    public String mkString(String start, String sep, String end) {
        return TraversableOnce$class.mkString(this, start, sep, end);
    }

    @Override
    public String mkString(String sep) {
        return TraversableOnce$class.mkString(this, sep);
    }

    @Override
    public String mkString() {
        return TraversableOnce$class.mkString(this);
    }

    @Override
    public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
        return TraversableOnce$class.addString(this, b, start, sep, end);
    }

    @Override
    public StringBuilder addString(StringBuilder b, String sep) {
        return TraversableOnce$class.addString(this, b, sep);
    }

    @Override
    public StringBuilder addString(StringBuilder b) {
        return TraversableOnce$class.addString(this, b);
    }

    public abstract Iterator<Object> iter();

    public String descr() {
        return this.descr;
    }

    public void descr_$eq(String x$1) {
        this.descr = x$1;
    }

    public int nerrors() {
        return this.nerrors;
    }

    public void nerrors_$eq(int x$1) {
        this.nerrors = x$1;
    }

    public int nwarnings() {
        return this.nwarnings;
    }

    public void nwarnings_$eq(int x$1) {
        this.nwarnings = x$1;
    }

    private String lineNum(int line) {
        return this.getLines().drop(line - 1).take(1).mkString();
    }

    public Iterator<String> getLines() {
        return new LineIterator();
    }

    @Override
    public boolean hasNext() {
        return this.iter().hasNext();
    }

    @Override
    public char next() {
        return this.positioner.next();
    }

    public Source$RelaxedPosition$ RelaxedPosition() {
        return this.RelaxedPosition$module == null ? this.RelaxedPosition$lzycompute() : this.RelaxedPosition$module;
    }

    public Source$RelaxedPositioner$ RelaxedPositioner() {
        return this.RelaxedPositioner$module == null ? this.RelaxedPositioner$lzycompute() : this.RelaxedPositioner$module;
    }

    public Source$NoPositioner$ NoPositioner() {
        return this.NoPositioner$module == null ? this.NoPositioner$lzycompute() : this.NoPositioner$module;
    }

    public char ch() {
        return this.positioner.ch();
    }

    public int pos() {
        return this.positioner.pos();
    }

    public void reportError(int pos, String msg, PrintStream out) {
        this.nerrors_$eq(this.nerrors() + 1);
        this.report(pos, msg, out);
    }

    private String spaces(int n) {
        return ((TraversableOnce)List$.MODULE$.fill(n, new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final char apply() {
                return ' ';
            }

            public char apply$mcC$sp() {
                return ' ';
            }
        })).mkString();
    }

    public void report(int pos, String msg, PrintStream out) {
        int line = Position$.MODULE$.line(pos);
        int col = Position$.MODULE$.column(pos);
        Predef$ predef$ = Predef$.MODULE$;
        out.println(new StringOps("%s:%d:%d: %s%s%s^").format(Predef$.MODULE$.genericWrapArray(new Object[]{this.descr(), BoxesRunTime.boxToInteger(line), BoxesRunTime.boxToInteger(col), msg, this.lineNum(line), this.spaces(col - 1)})));
    }

    public PrintStream reportError$default$3() {
        return Console$.MODULE$.err();
    }

    public void reportWarning(int pos, String msg, PrintStream out) {
        this.nwarnings_$eq(this.nwarnings() + 1);
        this.report(pos, new StringBuilder().append((Object)"warning! ").append((Object)msg).toString(), out);
    }

    public PrintStream reportWarning$default$3() {
        return Console$.MODULE$.out();
    }

    public Source withReset(Function0<Source> f) {
        this.resetFunction = f;
        return this;
    }

    public Source withClose(Function0<BoxedUnit> f) {
        this.closeFunction = f;
        return this;
    }

    public Source withDescription(String text) {
        this.descr_$eq(text);
        return this;
    }

    public Source withPositioning(boolean on) {
        this.positioner = on ? this.RelaxedPositioner() : this.NoPositioner();
        return this;
    }

    public Source withPositioning(Positioner pos) {
        this.positioner = pos;
        return this;
    }

    public void close() {
        if (this.closeFunction != null) {
            this.closeFunction.apply$mcV$sp();
        }
    }

    public Source reset() {
        if (this.resetFunction == null) {
            throw new UnsupportedOperationException("Source's reset() method was not set.");
        }
        return this.resetFunction.apply();
    }

    public Source() {
        TraversableOnce$class.$init$(this);
        Iterator$class.$init$(this);
        this.descr = "";
        this.nerrors = 0;
        this.nwarnings = 0;
        this.resetFunction = null;
        this.closeFunction = null;
        this.positioner = this.RelaxedPositioner();
    }

    public class Positioner {
        private final Position encoder;
        private char ch;
        private int pos;
        private int cline;
        private int ccol;
        private int tabinc;
        public final /* synthetic */ Source $outer;

        public char ch() {
            return this.ch;
        }

        public void ch_$eq(char x$1) {
            this.ch = x$1;
        }

        public int pos() {
            return this.pos;
        }

        public void pos_$eq(int x$1) {
            this.pos = x$1;
        }

        public int cline() {
            return this.cline;
        }

        public void cline_$eq(int x$1) {
            this.cline = x$1;
        }

        public int ccol() {
            return this.ccol;
        }

        public void ccol_$eq(int x$1) {
            this.ccol = x$1;
        }

        public int tabinc() {
            return this.tabinc;
        }

        public void tabinc_$eq(int x$1) {
            this.tabinc = x$1;
        }

        public char next() {
            this.ch_$eq(BoxesRunTime.unboxToChar(this.scala$io$Source$Positioner$$$outer().iter().next()));
            this.pos_$eq(this.encoder.encode(this.cline(), this.ccol()));
            char c = this.ch();
            switch (c) {
                default: {
                    this.ccol_$eq(this.ccol() + 1);
                    break;
                }
                case '\t': {
                    this.ccol_$eq(this.ccol() + this.tabinc());
                    break;
                }
                case '\n': {
                    this.ccol_$eq(1);
                    this.cline_$eq(this.cline() + 1);
                }
            }
            return this.ch();
        }

        public /* synthetic */ Source scala$io$Source$Positioner$$$outer() {
            return this.$outer;
        }

        public Positioner(Source $outer, Position encoder) {
            this.encoder = encoder;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            this.pos = 0;
            this.cline = 1;
            this.ccol = 1;
            this.tabinc = 4;
        }

        public Positioner(Source $outer) {
            this($outer, $outer.RelaxedPosition());
        }
    }

    public class LineIterator
    extends AbstractIterator<String> {
        private final StringBuilder sb;
        private BufferedIterator<Object> iter;
        private volatile boolean bitmap$0;

        private BufferedIterator iter$lzycompute() {
            LineIterator lineIterator = this;
            synchronized (lineIterator) {
                if (!this.bitmap$0) {
                    this.iter = this.scala$io$Source$LineIterator$$$outer().iter().buffered();
                    this.bitmap$0 = true;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.iter;
            }
        }

        public BufferedIterator<Object> iter() {
            return this.bitmap$0 ? this.iter : this.iter$lzycompute();
        }

        public boolean isNewline(char ch) {
            return ch == '\r' || ch == '\n';
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean getc() {
            if (!this.iter().hasNext()) return false;
            char ch = BoxesRunTime.unboxToChar(this.iter().next());
            if (ch == '\n') {
                return false;
            }
            if (ch == '\r') {
                BoxedUnit boxedUnit = this.iter().hasNext() && BoxesRunTime.unboxToChar(this.iter().head()) == '\n' ? this.iter().next() : BoxedUnit.UNIT;
                return false;
            }
            this.sb.append(ch);
            return true;
        }

        @Override
        public boolean hasNext() {
            return this.iter().hasNext();
        }

        @Override
        public String next() {
            this.sb.clear();
            while (this.getc()) {
            }
            return this.sb.toString();
        }

        public /* synthetic */ Source scala$io$Source$LineIterator$$$outer() {
            return Source.this;
        }

        public LineIterator() {
            if (Source.this == null) {
                throw null;
            }
            this.sb = new StringBuilder();
        }
    }
}

