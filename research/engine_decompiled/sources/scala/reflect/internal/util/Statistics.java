/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple2$mcII$sp;
import scala.Tuple2$mcJJ$sp;
import scala.Tuple3;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.Set;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.Map;
import scala.collection.mutable.MapLike$class;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.SynchronizedMap;
import scala.collection.mutable.SynchronizedMap$class;
import scala.math.Ordered;
import scala.math.Ordered$class;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Statistics$;
import scala.reflect.internal.util.Statistics$Quantity$class;
import scala.reflect.internal.util.Statistics$SubQuantity$class;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0011\rt!B\u0001\u0003\u0011\u0003Y\u0011AC*uCRL7\u000f^5dg*\u00111\u0001B\u0001\u0005kRLGN\u0003\u0002\u0006\r\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\b\u0011\u00059!/\u001a4mK\u000e$(\"A\u0005\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001A\u0011A\"D\u0007\u0002\u0005\u0019)aB\u0001E\u0001\u001f\tQ1\u000b^1uSN$\u0018nY:\u0014\u00055\u0001\u0002CA\t\u0013\u001b\u0005A\u0011BA\n\t\u0005\u0019\te.\u001f*fM\")Q#\u0004C\u0001-\u00051A(\u001b8jiz\"\u0012aC\u0003\u000515\u0001\u0011DA\u0007US6,'o\u00158baNDw\u000e\u001e\t\u0005#iaB$\u0003\u0002\u001c\u0011\t1A+\u001e9mKJ\u0002\"!E\u000f\n\u0005yA!\u0001\u0002'p]\u001eDQ\u0001I\u0007\u0005\u0006\u0005\n!\"\u001b8d\u0007>,h\u000e^3s)\t\u0011S\u0005\u0005\u0002\u0012G%\u0011A\u0005\u0003\u0002\u0005+:LG\u000fC\u0003'?\u0001\u0007q%A\u0001d!\tA\u0013&D\u0001\u000e\r\u0011QS\u0002A\u0016\u0003\u000f\r{WO\u001c;feN!\u0011\u0006\u0005\u0017b!\tASFB\u0004/\u001bA\u0005\u0019\u0011A\u0018\u0003\u0011E+\u0018M\u001c;jif\u001c\"!\f\t\t\u000bEjC\u0011\u0001\u001a\u0002\r\u0011Jg.\u001b;%)\u0005\u0011\u0003b\u0002\u001b.\u0005\u00045\t!N\u0001\u0007aJ,g-\u001b=\u0016\u0003Y\u0002\"a\u000e\u001e\u000f\u0005EA\u0014BA\u001d\t\u0003\u0019\u0001&/\u001a3fM&\u00111\b\u0010\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005eB\u0001b\u0002 .\u0005\u00045\taP\u0001\u0007a\"\f7/Z:\u0016\u0003\u0001\u00032!\u0011#7\u001d\t\t\")\u0003\u0002D\u0011\u00059\u0001/Y2lC\u001e,\u0017BA#G\u0005\r\u0019V-\u001d\u0006\u0003\u0007\"AQ\u0001S\u0017\u0005\u0002%\u000b!\"\u001e8eKJd\u00170\u001b8h+\u0005a\u0003\"B&.\t\u0003a\u0015AB:i_^\fE\u000f\u0006\u0002N!B\u0011\u0011CT\u0005\u0003\u001f\"\u0011qAQ8pY\u0016\fg\u000eC\u0003R\u0015\u0002\u0007a'A\u0003qQ\u0006\u001cX\rC\u0003T[\u0011\u0005Q'\u0001\u0003mS:,\u0007bB+.\u0005\u0004%\tAV\u0001\tG\"LG\u000e\u001a:f]V\tq\u000bE\u0002Y;2j\u0011!\u0017\u0006\u00035n\u000bq!\\;uC\ndWM\u0003\u0002]\u0011\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005yK&A\u0003'jgR\u0014UO\u001a4fe\"1\u0001-\fQ\u0001\n]\u000b\u0011b\u00195jY\u0012\u0014XM\u001c\u0011\u0011\u0007\u0005\u0013w%\u0003\u0002d\r\n9qJ\u001d3fe\u0016$\u0007\u0002\u0003\u001b*\u0005\u000b\u0007I\u0011A\u001b\t\u0011\u0019L#\u0011!Q\u0001\nY\nq\u0001\u001d:fM&D\b\u0005\u0003\u0005?S\t\u0015\r\u0011\"\u0001@\u0011!I\u0017F!A!\u0002\u0013\u0001\u0015a\u00029iCN,7\u000f\t\u0005\u0006+%\"\ta\u001b\u000b\u0004O1l\u0007\"\u0002\u001bk\u0001\u00041\u0004\"\u0002 k\u0001\u0004\u0001\u0005bB8*\u0001\u0004%\t\u0001]\u0001\u0006m\u0006dW/Z\u000b\u0002cB\u0011\u0011C]\u0005\u0003g\"\u00111!\u00138u\u0011\u001d)\u0018\u00061A\u0005\u0002Y\f\u0011B^1mk\u0016|F%Z9\u0015\u0005\t:\bb\u0002=u\u0003\u0003\u0005\r!]\u0001\u0004q\u0012\n\u0004B\u0002>*A\u0003&\u0011/\u0001\u0004wC2,X\r\t\u0005\u0006y&\"\t!`\u0001\bG>l\u0007/\u0019:f)\t\th\u0010C\u0003\u0000w\u0002\u0007q%\u0001\u0003uQ\u0006$\bbBA\u0002S\u0011\u0005\u0013QA\u0001\u0007KF,\u0018\r\\:\u0015\u00075\u000b9\u0001C\u0004\u0000\u0003\u0003\u0001\r!!\u0003\u0011\u0007E\tY!C\u0002\u0002\u000e!\u00111!\u00118z\u0011\u001d\t\t\"\u000bC!\u0003'\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002c\"9\u0011qC\u0015\u0005B\u0005e\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005m\u0001\u0003BA\u000f\u0003Oi!!a\b\u000b\t\u0005\u0005\u00121E\u0001\u0005Y\u0006twM\u0003\u0002\u0002&\u0005!!.\u0019<b\u0013\rY\u0014q\u0004\u0015\u0004?\u0005-\u0002cA\t\u0002.%\u0019\u0011q\u0006\u0005\u0003\r%tG.\u001b8f\u0011\u0019\u0001S\u0002\"\u0002\u00024Q)!%!\u000e\u00028!1a%!\rA\u0002\u001dBq!!\u000f\u00022\u0001\u0007\u0011/A\u0003eK2$\u0018\r\u000b\u0003\u00022\u0005-\u0002B\u0002\u0011\u000e\t\u000b\ty$\u0006\u0003\u0002B\u0005EF#\u0002\u0012\u0002D\u0005M\u0006\u0002CA#\u0003{\u0001\r!a\u0012\u0002\t\r$(o\u001d\t\u0007Q\u0005%\u0013qV\u0014\u0007\r\u0005-S\u0002AA'\u0005!\tV/\u00198u\u001b\u0006\u0004XCBA(\u00037\nIgE\u0004\u0002J\u0005E\u0013Q\u000e\u0017\u0011\u000fa\u000b\u0019&a\u0016\u0002h%\u0019\u0011QK-\u0003\u000f!\u000b7\u000f['baB!\u0011\u0011LA.\u0019\u0001!\u0001\"!\u0018\u0002J\t\u0007\u0011q\f\u0002\u0002\u0017F!\u0011\u0011MA\u0005!\r\t\u00121M\u0005\u0004\u0003KB!a\u0002(pi\"Lgn\u001a\t\u0005\u00033\nI\u0007\u0002\u0005\u0002l\u0005%#\u0019AA0\u0005\u00051\u0006c\u0002-\u0002p\u0005]\u0013qM\u0005\u0004\u0003cJ&aD*z]\u000eD'o\u001c8ju\u0016$W*\u00199\t\u0013Q\nIE!b\u0001\n\u0003)\u0004\"\u00034\u0002J\t\u0005\t\u0015!\u00037\u0011%q\u0014\u0011\nBC\u0002\u0013\u0005q\bC\u0005j\u0003\u0013\u0012\t\u0011)A\u0005\u0001\"Y\u0011QPA%\u0005\u0003%\u000b\u0011BA@\u0003%Ig.\u001b;WC2,X\rE\u0003\u0012\u0003\u0003\u000b9'C\u0002\u0002\u0004\"\u0011\u0001\u0002\u00102z]\u0006lWM\u0010\u0005\f\u0003\u000f\u000bIEaA!\u0002\u0017\tI)\u0001\u0006fm&$WM\\2fIM\u0002r!EAF\u0003O\ny)C\u0002\u0002\u000e\"\u0011\u0011BR;oGRLwN\\\u0019\u0011\t\u0005\u0013\u0017q\r\u0005\b+\u0005%C\u0011AAJ)!\t)*a'\u0002\u001e\u0006}E\u0003BAL\u00033\u0003r\u0001KA%\u0003/\n9\u0007\u0003\u0005\u0002\b\u0006E\u00059AAE\u0011\u0019!\u0014\u0011\u0013a\u0001m!1a(!%A\u0002\u0001C\u0011\"! \u0002\u0012\u0012\u0005\r!a \t\u0011\u0005\r\u0016\u0011\nC!\u0003K\u000bq\u0001Z3gCVdG\u000f\u0006\u0003\u0002h\u0005\u001d\u0006\u0002CAU\u0003C\u0003\r!a\u0016\u0002\u0007-,\u0017\u0010\u0003\u0005\u0002\u0018\u0005%C\u0011IAW)\u00051\u0004\u0003BA-\u0003c#\u0001\"!\u0018\u0002>\t\u0007\u0011q\f\u0005\t\u0003S\u000bi\u00041\u0001\u00020\"\"\u0011QHA\u0016\u0011\u001d\tI,\u0004C\u0003\u0003w\u000bAb\u001d;beR\u001cu.\u001e8uKJ$B!!0\u0002@B!\u0011CG9r\u0011!\t\t-a.A\u0002\u0005\r\u0017AA:d!\rA\u0013Q\u0019\u0004\u0007\u0003\u000fl\u0001!!3\u0003\u0015M+(mQ8v]R,'oE\u0003\u0002F\u001e\nY\rE\u0002)\u0003\u001b4\u0011\"a4\u000e!\u0003\r\t!!5\u0003\u0017M+(-U;b]RLG/_\n\u0005\u0003\u001b\u0004B\u0006\u0003\u00042\u0003\u001b$\tA\r\u0005\u0007\u0011\u00065g\u0011C%\t\u0015Q\n)M!A!\u0002\u00131D\r\u0003\u0006I\u0003\u000b\u0014)\u0019!C!\u00037,\u0012a\n\u0005\u000b\u0003?\f)M!A!\u0002\u00139\u0013aC;oI\u0016\u0014H._5oO\u0002Bq!FAc\t\u0003\t\u0019\u000f\u0006\u0004\u0002D\u0006\u0015\u0018q\u001d\u0005\u0007i\u0005\u0005\b\u0019\u0001\u001c\t\r!\u000b\t\u000f1\u0001(\u0011!\tY/!2\u0005\u0002\u00055\u0018!B:uCJ$HCAA_\u0011!\t\t0!2\u0005\u0002\u0005M\u0018\u0001B:u_B$2AIA{\u0011!\t90a<A\u0002\u0005u\u0016\u0001\u00029sKZD\u0001\"a\u0006\u0002F\u0012\u0005\u0013Q\u0016\u0015\u0005\u0003o\u000bY\u0003C\u0004\u0002\u00006!)A!\u0001\u0002\u0017M$x\u000e]\"pk:$XM\u001d\u000b\u0006E\t\r!Q\u0001\u0005\t\u0003\u0003\fi\u00101\u0001\u0002D\"A\u00111^A\u007f\u0001\u0004\ti\f\u000b\u0003\u0002~\u0006-\u0002b\u0002B\u0006\u001b\u0011\u0015!QB\u0001\u000bgR\f'\u000f\u001e+j[\u0016\u0014H\u0003\u0002B\b\u0005#\u0001\"\u0001K\f\t\u0011\tM!\u0011\u0002a\u0001\u0005+\t!\u0001^7\u0011\u0007!\u00129B\u0002\u0004\u0003\u001a5\u0001!1\u0004\u0002\u0006)&lWM]\n\u0005\u0005/\u0001B\u0006C\u00055\u0005/\u0011)\u0019!C\u0001k!IaMa\u0006\u0003\u0002\u0003\u0006IA\u000e\u0005\n}\t]!Q1A\u0005\u0002}B\u0011\"\u001bB\f\u0005\u0003\u0005\u000b\u0011\u0002!\t\u000fU\u00119\u0002\"\u0001\u0003(Q1!Q\u0003B\u0015\u0005WAa\u0001\u000eB\u0013\u0001\u00041\u0004B\u0002 \u0003&\u0001\u0007\u0001\t\u0003\u0006\u00030\t]\u0001\u0019!C\u0001\u0005c\tQA\\1o_N,\u0012\u0001\b\u0005\u000b\u0005k\u00119\u00021A\u0005\u0002\t]\u0012!\u00038b]>\u001cx\fJ3r)\r\u0011#\u0011\b\u0005\tq\nM\u0012\u0011!a\u00019!A!Q\bB\fA\u0003&A$\u0001\u0004oC:|7\u000f\t\u0005\n\u0005\u0003\u00129\u00021A\u0005\u0002A\fq\u0001^5nS:<7\u000f\u0003\u0006\u0003F\t]\u0001\u0019!C\u0001\u0005\u000f\n1\u0002^5nS:<7o\u0018\u0013fcR\u0019!E!\u0013\t\u0011a\u0014\u0019%!AA\u0002ED\u0001B!\u0014\u0003\u0018\u0001\u0006K!]\u0001\ti&l\u0017N\\4tA!A\u00111\u001eB\f\t\u0003\u0011\t\u0006F\u0001\u001a\u0011!\t\tPa\u0006\u0005\u0002\tUCc\u0001\u0012\u0003X!A\u0011q\u001fB*\u0001\u0004\u0011y\u0001\u0003\u0005\u0003\\\t]A\u0011\u0003B/\u0003\u0011\u0019\bn\\<\u0015\u0007Y\u0012y\u0006C\u0004\u0003b\te\u0003\u0019\u0001\u000f\u0002\u00059\u001c\b\u0002CA\f\u0005/!\t%!,)\t\t%\u00111\u0006\u0005\b\u0005SjAQ\u0001B6\u0003%\u0019Ho\u001c9US6,'\u000fF\u0003#\u0005[\u0012y\u0007\u0003\u0005\u0003\u0014\t\u001d\u0004\u0019\u0001B\u000b\u0011!\tYOa\u001aA\u0002\t=\u0001\u0006\u0002B4\u0003WAqA!\u001e\u000e\t\u000b\u00119(A\u0005qkNDG+[7feR1!q\u0002B=\u0007\u0013A\u0001Ba\u001f\u0003t\u0001\u0007!QP\u0001\u0007i&lWM]:\u0011\u0007!\u0012yH\u0002\u0004\u0003\u00026\u0001!1\u0011\u0002\u000b)&lWM]*uC\u000e\\7c\u0001B@!!9QCa \u0005\u0002\t\u001dEC\u0001B?\u0011)\u0011YIa A\u0002\u0013%!QR\u0001\u0006K2,Wn]\u000b\u0003\u0005\u001f\u0003R!\u0011BI\u0005+K1Aa%G\u0005\u0011a\u0015n\u001d;\u0011\u000bEQ\"q\u0013\u000f\u0011\u0007!\u0012IJ\u0002\u0004\u0003\u001c6\u0001!Q\u0014\u0002\u000f'R\f7m[1cY\u0016$\u0016.\\3s'\u0019\u0011IJa(\u0003>B\u0019\u0001F!)\u0007\r\t\rV\u0002\u0001BS\u0005!\u0019VO\u0019+j[\u0016\u00148C\u0002BQ\u0005+\tY\rC\u00065\u0005C\u0013\t\u0011)A\u0005m\tu\u0001B\u0003%\u0003\"\n\u0015\r\u0011\"\u0011\u0003,V\u0011!Q\u0003\u0005\f\u0003?\u0014\tK!A!\u0002\u0013\u0011)\u0002C\u0004\u0016\u0005C#\tA!-\u0015\r\t}%1\u0017B[\u0011\u0019!$q\u0016a\u0001m!9\u0001Ja,A\u0002\tU\u0001\u0002\u0003B.\u0005C#\tF!/\u0015\t\u0005m!1\u0018\u0005\b\u0005C\u00129\f1\u0001\u001d!\u0011\t%Ma&\t\u0017Q\u0012IJ!A!\u0002\u00131$Q\u0004\u0005\r\u0011\ne%\u0011!Q\u0001\n\tU!\u0011\u0016\u0005\b+\teE\u0011\u0001Bc)\u0019\u00119Ja2\u0003J\"1AGa1A\u0002YBq\u0001\u0013Bb\u0001\u0004\u0011)\u0002\u0003\u0006\u0003N\ne\u0005\u0019!C\u0001\u0005c\tQb\u001d9fG&4\u0017n\u0019(b]>\u001c\bB\u0003Bi\u00053\u0003\r\u0011\"\u0001\u0003T\u0006\t2\u000f]3dS\u001aL7MT1o_N|F%Z9\u0015\u0007\t\u0012)\u000e\u0003\u0005y\u0005\u001f\f\t\u00111\u0001\u001d\u0011!\u0011IN!'!B\u0013a\u0012AD:qK\u000eLg-[2OC:|7\u000f\t\u0005\by\neE\u0011\u0001Bo)\r\t(q\u001c\u0005\b\u007f\nm\u0007\u0019\u0001BL\u0011!\t\u0019A!'\u0005B\t\rHcA'\u0003f\"9qP!9A\u0002\u0005%\u0001\u0002CA\t\u00053#\t%a\u0005\t\u0011\u0005]!\u0011\u0014C!\u0003[C!B!<\u0003\u0000\u0001\u0007I\u0011\u0002Bx\u0003%)G.Z7t?\u0012*\u0017\u000fF\u0002#\u0005cD\u0011\u0002\u001fBv\u0003\u0003\u0005\rAa$\t\u0013\tU(q\u0010Q!\n\t=\u0015AB3mK6\u001c\b\u0005\u0003\u0005\u0003z\n}D\u0011\u0001B~\u0003\u0011\u0001Xo\u001d5\u0015\t\t=!Q \u0005\t\u0005\u007f\u00149\u00101\u0001\u0003\u0018\u0006\tA\u000f\u0003\u0005\u0004\u0004\t}D\u0011AB\u0003\u0003\r\u0001x\u000e\u001d\u000b\u0004E\r\u001d\u0001\u0002CA|\u0007\u0003\u0001\rAa\u0004\t\u0013\r-!1\u000fCA\u0002\r5\u0011!\u0002;j[\u0016\u0014\b#B\t\u0002\u0002\n]\u0005\u0006\u0002B:\u0003WAqaa\u0005\u000e\t\u000b\u0019)\"\u0001\u0005q_B$\u0016.\\3s)\u0015\u00113qCB\r\u0011!\u0011Yh!\u0005A\u0002\tu\u0004\u0002CA|\u0007#\u0001\rAa\u0004)\t\rE\u00111\u0006\u0005\b\u0007?iA\u0011AB\u0011\u0003)qWm^\"pk:$XM\u001d\u000b\u0006O\r\r2Q\u0005\u0005\u0007i\ru\u0001\u0019\u0001\u001c\t\u000fy\u001ai\u00021\u0001\u0004(A!\u0011c!\u000b7\u0013\r\u0019Y\u0003\u0003\u0002\u000byI,\u0007/Z1uK\u0012t\u0004bBB\u0018\u001b\u0011\u00051\u0011G\u0001\u000e]\u0016<(+\u001a7D_VtG/\u001a:\u0015\u000b\u001d\u001a\u0019d!\u000e\t\rQ\u001ai\u00031\u00017\u0011\u001d\u00199d!\fA\u0002\u001d\n1a\u0019;s\u0011\u001d\u0019Y$\u0004C\u0001\u0007{\tQB\\3x'V\u00147i\\;oi\u0016\u0014HCBAb\u0007\u007f\u0019\t\u0005\u0003\u00045\u0007s\u0001\rA\u000e\u0005\b\u0007o\u0019I\u00041\u0001(\u0011\u001d\u0019)%\u0004C\u0001\u0007\u000f\n\u0001B\\3x)&lWM\u001d\u000b\u0007\u0005+\u0019Iea\u0013\t\rQ\u001a\u0019\u00051\u00017\u0011\u001dq41\ta\u0001\u0007OAqaa\u0014\u000e\t\u0003\u0019\t&A\u0006oK^\u001cVO\u0019+j[\u0016\u0014HC\u0002B\u000b\u0007'\u001a)\u0006\u0003\u00045\u0007\u001b\u0002\rA\u000e\u0005\t\u0007\u0017\u0019i\u00051\u0001\u0003\u0016!91\u0011L\u0007\u0005\u0002\rm\u0013!\u00058foN#\u0018mY6bE2,G+[7feR1!qSB/\u0007?Ba\u0001NB,\u0001\u00041\u0004\u0002CB\u0006\u0007/\u0002\rA!\u0006\t\u000f\r\rT\u0002\"\u0001\u0004f\u00059a.Z<WS\u0016<HCBB4\u0007\u001b\u001by\t\u0006\u0003\u0004j\r-\u0005c\u0001\u0015\u0004l\u001911QN\u0007\u0001\u0007_\u0012AAV5foN!11\u000e\t-\u0011%!41\u000eBC\u0002\u0013\u0005Q\u0007C\u0005g\u0007W\u0012\t\u0011)A\u0005m!Iaha\u001b\u0003\u0006\u0004%\ta\u0010\u0005\nS\u000e-$\u0011!Q\u0001\n\u0001C1ba\u001f\u0004l\t\u0005I\u0015!\u0003\u0004~\u0005)\u0011/^1oiB)\u0011#!!\u0002\n!9Qca\u001b\u0005\u0002\r\u0005E\u0003CB5\u0007\u0007\u001b)ia\"\t\rQ\u001ay\b1\u00017\u0011\u0019q4q\u0010a\u0001\u0001\"I11PB@\t\u0003\u00071Q\u0010\u0005\t\u0003/\u0019Y\u0007\"\u0011\u0002\u001a!I11PB1\t\u0003\u00071Q\u0010\u0005\u0007i\r\u0005\u0004\u0019\u0001\u001c\t\u000fy\u001a\t\u00071\u0001\u0004(!911S\u0007\u0005\u0002\rU\u0015a\u00038foF+\u0018M\u001c;NCB,baa&\u0004\"\u000e\u0015FCBBM\u0007g\u001b)\f\u0006\u0003\u0004\u001c\u000e=F\u0003BBO\u0007O\u0003r\u0001KA%\u0007?\u001b\u0019\u000b\u0005\u0003\u0002Z\r\u0005F\u0001CA/\u0007#\u0013\r!a\u0018\u0011\t\u0005e3Q\u0015\u0003\t\u0003W\u001a\tJ1\u0001\u0002`!Q1\u0011VBI\u0003\u0003\u0005\u001daa+\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0004\u0012\u0003\u0017\u001b\u0019k!,\u0011\t\u0005\u001371\u0015\u0005\n\u0003{\u001a\t\n\"a\u0001\u0007c\u0003R!EAA\u0007GCa\u0001NBI\u0001\u00041\u0004b\u0002 \u0004\u0012\u0002\u00071q\u0005\u0005\b\u0007skA\u0011AB^\u0003)qWm\u001e\"z\u00072\f7o]\u000b\u0005\u0007{\u001b)\u000e\u0006\u0004\u0004@\u000e\r8Q\u001d\u000b\u0005\u0007\u0003\u001cy\u000e\u0006\u0003\u0004D\u000e]\u0007c\u0002\u0015\u0002J\r\u001571\u001b\u0019\u0005\u0007\u000f\u001cy\rE\u00038\u0007\u0013\u001ci-C\u0002\u0004Lr\u0012Qa\u00117bgN\u0004B!!\u0017\u0004P\u0012a1\u0011[B\\\u0003\u0003\u0005\tQ!\u0001\u0002`\t\u0019q\fJ\u0019\u0011\t\u0005e3Q\u001b\u0003\t\u0003W\u001a9L1\u0001\u0002`!Q1\u0011\\B\\\u0003\u0003\u0005\u001daa7\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u0004\u0012\u0003\u0017\u001b\u0019n!8\u0011\t\u0005\u001371\u001b\u0005\n\u0003{\u001a9\f\"a\u0001\u0007C\u0004R!EAA\u0007'Da\u0001NB\\\u0001\u00041\u0004b\u0002 \u00048\u0002\u00071q\u0005\u0005\b\u0007SlA\u0011\u0001BD\u00035qWm\u001e+j[\u0016\u00148\u000b^1dW\"91Q^\u0007\u0005\u0002\r=\u0018!D1mYF+\u0018M\u001c;ji&,7/\u0006\u0002\u0004rB!\u0011ia=-\u0013\r\u0019)P\u0012\u0002\t\u0013R,'/\u00192mK\"91\u0011`\u0007\u0005\n\rm\u0018aC:i_^\u0004VM]2f]R$b!a\u0007\u0004~\u0012\u0005\u0001bBB\u0000\u0007o\u0004\r\u0001H\u0001\u0002q\"9A1AB|\u0001\u0004a\u0012\u0001\u00022bg\u00164a\u0001b\u0002\u000e\t\u0011%!A\u0003*fY\u000e{WO\u001c;feN)AQA\u0014\u0002L\"QA\u0007\"\u0002\u0003\u0002\u0003\u0006IA\u000e3\t\u0015!#)A!b\u0001\n\u0003\nY\u000e\u0003\u0006\u0002`\u0012\u0015!\u0011!Q\u0001\n\u001dBq!\u0006C\u0003\t\u0003!\u0019\u0002\u0006\u0004\u0005\u0016\u0011]A\u0011\u0004\t\u0004Q\u0011\u0015\u0001B\u0002\u001b\u0005\u0012\u0001\u0007a\u0007\u0003\u0004I\t#\u0001\ra\n\u0005\t\u0003/!)\u0001\"\u0011\u0002\u001a!iAq\u0004C\u0003!\u0003\r\t\u0011!C\u0005k\u0011\fAb];qKJ$\u0003O]3gSbDa\u0002b\t\u000e\t\u0003\u0005)\u0011!a\u0001\n\u0013!)#\u0001\u0019tG\u0006d\u0017\r\n:fM2,7\r\u001e\u0013j]R,'O\\1mIU$\u0018\u000e\u001c\u0013Ti\u0006$\u0018n\u001d;jGN$CeX3oC\ndW\rZ\u000b\u0002\u001b\"YA\u0011F\u0007\u0003\u0002\u0003\u0007I\u0011\u0002C\u0016\u0003Q\u001a8-\u00197bII,g\r\\3di\u0012Jg\u000e^3s]\u0006dG%\u001e;jY\u0012\u001aF/\u0019;jgRL7m\u001d\u0013%?\u0016t\u0017M\u00197fI~#S-\u001d\u000b\u0004E\u00115\u0002\u0002\u0003=\u0005(\u0005\u0005\t\u0019A'\t\u0015\u0011ERB!A\u0001B\u0003&Q*A\u0019tG\u0006d\u0017\r\n:fM2,7\r\u001e\u0013j]R,'O\\1mIU$\u0018\u000e\u001c\u0013Ti\u0006$\u0018n\u001d;jGN$CeX3oC\ndW\r\u001a\u0011\t\u0013\u0011URB1A\u0005\n\u0011]\u0012AA9t+\t!I\u0004E\u0003Y\u0003'2D\u0006\u0003\u0005\u0005>5\u0001\u000b\u0011\u0002C\u001d\u0003\r\t8\u000f\t\u0005\n\t\u0003j!\u0019!C\u0003\tK\t\u0011bY1o\u000b:\f'\r\\3\t\u000f\u0011\u0015S\u0002)A\u0007\u001b\u0006Q1-\u00198F]\u0006\u0014G.\u001a\u0011\t\u0013\u0011%SB1A\u0005\u0006\u0011-\u0013A\u00035pi\u0016s\u0017M\u00197fIV\u0011AQJ\b\u0003\t\u001fJ\u0012\u0001\u0001\u0005\t\t'j\u0001\u0015!\u0004\u0005N\u0005Y\u0001n\u001c;F]\u0006\u0014G.\u001a3!\u0011\u001d!9&\u0004C\u0001\tK\tq!\u001a8bE2,G\rC\u0004\u0005\\5!\t\u0001\"\u0018\u0002\u0017\u0015t\u0017M\u00197fI~#S-\u001d\u000b\u0004E\u0011}\u0003b\u0002C1\t3\u0002\r!T\u0001\u0005G>tG\r")
public final class Statistics {
    public static void enabled_$eq(boolean bl) {
        Statistics$.MODULE$.enabled_$eq(bl);
    }

    public static boolean enabled() {
        return Statistics$.MODULE$.enabled();
    }

    public static boolean hotEnabled() {
        return Statistics$.MODULE$.hotEnabled();
    }

    public static boolean canEnable() {
        return Statistics$.MODULE$.canEnable();
    }

    public static Iterable<Quantity> allQuantities() {
        return Statistics$.MODULE$.allQuantities();
    }

    public static TimerStack newTimerStack() {
        return Statistics$.MODULE$.newTimerStack();
    }

    public static <V> QuantMap<Class<?>, V> newByClass(String string2, Seq<String> seq, Function0<V> function0, Function1<V, Ordered<V>> function1) {
        return Statistics$.MODULE$.newByClass(string2, seq, function0, function1);
    }

    public static <K, V> QuantMap<K, V> newQuantMap(String string2, Seq<String> seq, Function0<V> function0, Function1<V, Ordered<V>> function1) {
        return Statistics$.MODULE$.newQuantMap(string2, seq, function0, function1);
    }

    public static View newView(String string2, Seq<String> seq, Function0<Object> function0) {
        return Statistics$.MODULE$.newView(string2, seq, function0);
    }

    public static StackableTimer newStackableTimer(String string2, Timer timer) {
        return Statistics$.MODULE$.newStackableTimer(string2, timer);
    }

    public static Timer newSubTimer(String string2, Timer timer) {
        return Statistics$.MODULE$.newSubTimer(string2, timer);
    }

    public static Timer newTimer(String string2, Seq<String> seq) {
        return Statistics$.MODULE$.newTimer(string2, seq);
    }

    public static SubCounter newSubCounter(String string2, Counter counter) {
        return Statistics$.MODULE$.newSubCounter(string2, counter);
    }

    public static Counter newRelCounter(String string2, Counter counter) {
        return Statistics$.MODULE$.newRelCounter(string2, counter);
    }

    public static Counter newCounter(String string2, Seq<String> seq) {
        return Statistics$.MODULE$.newCounter(string2, seq);
    }

    public static void popTimer(TimerStack timerStack, Tuple2<Object, Object> tuple2) {
        Statistics$.MODULE$.popTimer(timerStack, tuple2);
    }

    public static Tuple2<Object, Object> pushTimer(TimerStack timerStack, Function0<StackableTimer> function0) {
        return Statistics$.MODULE$.pushTimer(timerStack, function0);
    }

    public static void stopTimer(Timer timer, Tuple2<Object, Object> tuple2) {
        Statistics$.MODULE$.stopTimer(timer, tuple2);
    }

    public static Tuple2<Object, Object> startTimer(Timer timer) {
        return Statistics$.MODULE$.startTimer(timer);
    }

    public static void stopCounter(SubCounter subCounter, Tuple2<Object, Object> tuple2) {
        Statistics$.MODULE$.stopCounter(subCounter, tuple2);
    }

    public static Tuple2<Object, Object> startCounter(SubCounter subCounter) {
        return Statistics$.MODULE$.startCounter(subCounter);
    }

    public static <K> void incCounter(QuantMap<K, Counter> quantMap, K k) {
        Statistics$.MODULE$.incCounter(quantMap, k);
    }

    public static void incCounter(Counter counter, int n) {
        Statistics$.MODULE$.incCounter(counter, n);
    }

    public static void incCounter(Counter counter) {
        Statistics$.MODULE$.incCounter(counter);
    }

    public static class View
    implements Quantity {
        private final String prefix;
        private final Seq<String> phases;
        private final Function0<Object> quant;
        private final ListBuffer<Quantity> children;

        @Override
        public ListBuffer<Quantity> children() {
            return this.children;
        }

        @Override
        public void scala$reflect$internal$util$Statistics$Quantity$_setter_$children_$eq(ListBuffer x$1) {
            this.children = x$1;
        }

        @Override
        public Quantity underlying() {
            return Statistics$Quantity$class.underlying(this);
        }

        @Override
        public boolean showAt(String phase) {
            return Statistics$Quantity$class.showAt(this, phase);
        }

        @Override
        public String line() {
            return Statistics$Quantity$class.line(this);
        }

        @Override
        public String prefix() {
            return this.prefix;
        }

        @Override
        public Seq<String> phases() {
            return this.phases;
        }

        public String toString() {
            return this.quant.apply().toString();
        }

        public View(String prefix, Seq<String> phases, Function0<Object> quant) {
            this.prefix = prefix;
            this.phases = phases;
            this.quant = quant;
            Statistics$Quantity$class.$init$(this);
        }
    }

    public static class Timer
    implements Quantity {
        private final String prefix;
        private final Seq<String> phases;
        private long nanos;
        private int timings;
        private final ListBuffer<Quantity> children;

        @Override
        public ListBuffer<Quantity> children() {
            return this.children;
        }

        @Override
        public void scala$reflect$internal$util$Statistics$Quantity$_setter_$children_$eq(ListBuffer x$1) {
            this.children = x$1;
        }

        @Override
        public Quantity underlying() {
            return Statistics$Quantity$class.underlying(this);
        }

        @Override
        public boolean showAt(String phase) {
            return Statistics$Quantity$class.showAt(this, phase);
        }

        @Override
        public String line() {
            return Statistics$Quantity$class.line(this);
        }

        @Override
        public String prefix() {
            return this.prefix;
        }

        @Override
        public Seq<String> phases() {
            return this.phases;
        }

        public long nanos() {
            return this.nanos;
        }

        public void nanos_$eq(long x$1) {
            this.nanos = x$1;
        }

        public int timings() {
            return this.timings;
        }

        public void timings_$eq(int x$1) {
            this.timings = x$1;
        }

        public Tuple2<Object, Object> start() {
            return new Tuple2$mcJJ$sp(this.nanos(), System.nanoTime());
        }

        public void stop(Tuple2<Object, Object> prev) {
            if (prev != null) {
                Tuple2$mcJJ$sp tuple2$mcJJ$sp = new Tuple2$mcJJ$sp(prev._1$mcJ$sp(), prev._2$mcJ$sp());
                long nanos0 = ((Tuple2)tuple2$mcJJ$sp)._1$mcJ$sp();
                long start = ((Tuple2)tuple2$mcJJ$sp)._2$mcJ$sp();
                this.nanos_$eq(nanos0 + System.nanoTime() - start);
                this.timings_$eq(this.timings() + 1);
                return;
            }
            throw new MatchError(prev);
        }

        public String show(long ns) {
            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", "ms"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToLong(ns / 1000000L)}));
        }

        public String toString() {
            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " spans, ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.timings()), this.show(this.nanos())}));
        }

        public Timer(String prefix, Seq<String> phases) {
            this.prefix = prefix;
            this.phases = phases;
            Statistics$Quantity$class.$init$(this);
            this.nanos = 0L;
            this.timings = 0;
        }
    }

    public static class Counter
    implements Quantity,
    Ordered<Counter> {
        private final String prefix;
        private final Seq<String> phases;
        private int value;
        private final ListBuffer<Quantity> children;

        @Override
        public boolean $less(Object that) {
            return Ordered$class.$less(this, that);
        }

        @Override
        public boolean $greater(Object that) {
            return Ordered$class.$greater(this, that);
        }

        @Override
        public boolean $less$eq(Object that) {
            return Ordered$class.$less$eq(this, that);
        }

        @Override
        public boolean $greater$eq(Object that) {
            return Ordered$class.$greater$eq(this, that);
        }

        @Override
        public int compareTo(Object that) {
            return Ordered$class.compareTo(this, that);
        }

        @Override
        public ListBuffer<Quantity> children() {
            return this.children;
        }

        @Override
        public void scala$reflect$internal$util$Statistics$Quantity$_setter_$children_$eq(ListBuffer x$1) {
            this.children = x$1;
        }

        @Override
        public Quantity underlying() {
            return Statistics$Quantity$class.underlying(this);
        }

        @Override
        public boolean showAt(String phase) {
            return Statistics$Quantity$class.showAt(this, phase);
        }

        @Override
        public String line() {
            return Statistics$Quantity$class.line(this);
        }

        @Override
        public String prefix() {
            return this.prefix;
        }

        @Override
        public Seq<String> phases() {
            return this.phases;
        }

        public int value() {
            return this.value;
        }

        public void value_$eq(int x$1) {
            this.value = x$1;
        }

        @Override
        public int compare(Counter that) {
            return this.value() < that.value() ? -1 : (this.value() > that.value() ? 1 : 0);
        }

        public boolean equals(Object that) {
            Counter counter;
            boolean bl = that instanceof Counter ? this.compare(counter = (Counter)that) == 0 : false;
            return bl;
        }

        public int hashCode() {
            return this.value();
        }

        public String toString() {
            return ((Object)BoxesRunTime.boxToInteger(this.value())).toString();
        }

        public Counter(String prefix, Seq<String> phases) {
            this.prefix = prefix;
            this.phases = phases;
            Statistics$Quantity$class.$init$(this);
            Ordered$class.$init$(this);
            this.value = 0;
        }
    }

    /*
     * Illegal identifiers - consider using --renameillegalidents true
     */
    public static class QuantMap<K, V>
    extends HashMap<K, V>
    implements SynchronizedMap<K, V>,
    Quantity {
        private final String prefix;
        private final Seq<String> phases;
        private final Function0<V> initValue;
        public final Function1<V, Ordered<V>> scala$reflect$internal$util$Statistics$QuantMap$$evidence$3;
        private final ListBuffer<Quantity> children;

        @Override
        public ListBuffer<Quantity> children() {
            return this.children;
        }

        @Override
        public void scala$reflect$internal$util$Statistics$Quantity$_setter_$children_$eq(ListBuffer x$1) {
            this.children = x$1;
        }

        @Override
        public Quantity underlying() {
            return Statistics$Quantity$class.underlying(this);
        }

        @Override
        public boolean showAt(String phase) {
            return Statistics$Quantity$class.showAt(this, phase);
        }

        @Override
        public String line() {
            return Statistics$Quantity$class.line(this);
        }

        @Override
        public /* synthetic */ Option scala$collection$mutable$SynchronizedMap$$super$get(Object key) {
            return super.get(key);
        }

        @Override
        public /* synthetic */ Iterator scala$collection$mutable$SynchronizedMap$$super$iterator() {
            return super.iterator();
        }

        @Override
        public /* synthetic */ SynchronizedMap scala$collection$mutable$SynchronizedMap$$super$$plus$eq(Tuple2 kv) {
            return (SynchronizedMap)super.$plus$eq(kv);
        }

        @Override
        public /* synthetic */ SynchronizedMap scala$collection$mutable$SynchronizedMap$$super$$minus$eq(Object key) {
            return (SynchronizedMap)super.$minus$eq(key);
        }

        @Override
        public /* synthetic */ int scala$collection$mutable$SynchronizedMap$$super$size() {
            return super.size();
        }

        @Override
        public /* synthetic */ Option scala$collection$mutable$SynchronizedMap$$super$put(Object key, Object value) {
            return super.put(key, value);
        }

        @Override
        public /* synthetic */ void scala$collection$mutable$SynchronizedMap$$super$update(Object key, Object value) {
            super.update(key, value);
        }

        @Override
        public /* synthetic */ Option scala$collection$mutable$SynchronizedMap$$super$remove(Object key) {
            return super.remove(key);
        }

        @Override
        public /* synthetic */ void scala$collection$mutable$SynchronizedMap$$super$clear() {
            super.clear();
        }

        @Override
        public /* synthetic */ Object scala$collection$mutable$SynchronizedMap$$super$getOrElseUpdate(Object key, Function0 op) {
            return super.getOrElseUpdate(key, op);
        }

        @Override
        public /* synthetic */ SynchronizedMap scala$collection$mutable$SynchronizedMap$$super$transform(Function2 f) {
            return (SynchronizedMap)MapLike$class.transform(this, f);
        }

        @Override
        public /* synthetic */ SynchronizedMap scala$collection$mutable$SynchronizedMap$$super$retain(Function2 p) {
            return (SynchronizedMap)MapLike$class.retain(this, p);
        }

        @Override
        public /* synthetic */ Iterable scala$collection$mutable$SynchronizedMap$$super$values() {
            return super.values();
        }

        @Override
        public /* synthetic */ Iterator scala$collection$mutable$SynchronizedMap$$super$valuesIterator() {
            return super.valuesIterator();
        }

        @Override
        public /* synthetic */ Map scala$collection$mutable$SynchronizedMap$$super$clone() {
            return MapLike$class.clone(this);
        }

        @Override
        public /* synthetic */ void scala$collection$mutable$SynchronizedMap$$super$foreach(Function1 f) {
            super.foreach(f);
        }

        @Override
        public /* synthetic */ Object scala$collection$mutable$SynchronizedMap$$super$apply(Object key) {
            return super.apply(key);
        }

        @Override
        public /* synthetic */ Set scala$collection$mutable$SynchronizedMap$$super$keySet() {
            return super.keySet();
        }

        @Override
        public /* synthetic */ Iterable scala$collection$mutable$SynchronizedMap$$super$keys() {
            return scala.collection.MapLike$class.keys(this);
        }

        @Override
        public /* synthetic */ Iterator scala$collection$mutable$SynchronizedMap$$super$keysIterator() {
            return super.keysIterator();
        }

        @Override
        public /* synthetic */ boolean scala$collection$mutable$SynchronizedMap$$super$isEmpty() {
            return scala.collection.MapLike$class.isEmpty(this);
        }

        @Override
        public /* synthetic */ boolean scala$collection$mutable$SynchronizedMap$$super$contains(Object key) {
            return super.contains(key);
        }

        @Override
        public /* synthetic */ boolean scala$collection$mutable$SynchronizedMap$$super$isDefinedAt(Object key) {
            return scala.collection.MapLike$class.isDefinedAt(this, key);
        }

        @Override
        public Option<V> get(K key) {
            return SynchronizedMap$class.get(this, key);
        }

        @Override
        public Iterator<Tuple2<K, V>> iterator() {
            return SynchronizedMap$class.iterator(this);
        }

        @Override
        public SynchronizedMap<K, V> $plus$eq(Tuple2<K, V> kv) {
            return SynchronizedMap$class.$plus$eq(this, kv);
        }

        @Override
        public SynchronizedMap<K, V> $minus$eq(K key) {
            return SynchronizedMap$class.$minus$eq(this, key);
        }

        @Override
        public int size() {
            return SynchronizedMap$class.size(this);
        }

        @Override
        public Option<V> put(K key, V value) {
            return SynchronizedMap$class.put(this, key, value);
        }

        @Override
        public void update(K key, V value) {
            SynchronizedMap$class.update(this, key, value);
        }

        @Override
        public Option<V> remove(K key) {
            return SynchronizedMap$class.remove(this, key);
        }

        @Override
        public void clear() {
            SynchronizedMap$class.clear(this);
        }

        @Override
        public V getOrElseUpdate(K key, Function0<V> function0) {
            return (V)SynchronizedMap$class.getOrElseUpdate(this, key, function0);
        }

        @Override
        public SynchronizedMap<K, V> transform(Function2<K, V, V> f) {
            return SynchronizedMap$class.transform(this, f);
        }

        @Override
        public SynchronizedMap<K, V> retain(Function2<K, V, Object> p) {
            return SynchronizedMap$class.retain(this, p);
        }

        @Override
        public Iterable<V> values() {
            return SynchronizedMap$class.values(this);
        }

        @Override
        public Iterator<V> valuesIterator() {
            return SynchronizedMap$class.valuesIterator(this);
        }

        @Override
        public Map<K, V> clone() {
            return SynchronizedMap$class.clone(this);
        }

        @Override
        public <U> void foreach(Function1<Tuple2<K, V>, U> f) {
            SynchronizedMap$class.foreach(this, f);
        }

        @Override
        public V apply(K key) {
            return (V)SynchronizedMap$class.apply(this, key);
        }

        @Override
        public Set<K> keySet() {
            return SynchronizedMap$class.keySet(this);
        }

        @Override
        public Iterable<K> keys() {
            return SynchronizedMap$class.keys(this);
        }

        @Override
        public Iterator<K> keysIterator() {
            return SynchronizedMap$class.keysIterator(this);
        }

        @Override
        public boolean isEmpty() {
            return SynchronizedMap$class.isEmpty(this);
        }

        @Override
        public boolean contains(K key) {
            return SynchronizedMap$class.contains(this, key);
        }

        @Override
        public boolean isDefinedAt(K key) {
            return SynchronizedMap$class.isDefinedAt(this, key);
        }

        @Override
        public String prefix() {
            return this.prefix;
        }

        @Override
        public Seq<String> phases() {
            return this.phases;
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public V default(K key) {
            void var2_2;
            V elem = this.initValue.apply();
            this.update(key, elem);
            return var2_2;
        }

        @Override
        public String toString() {
            return ((TraversableOnce)((TraversableLike)this.toSeq().sortWith(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ QuantMap $outer;

                public final boolean apply(Tuple2<K, V> x$6, Tuple2<K, V> x$7) {
                    return this.$outer.scala$reflect$internal$util$Statistics$QuantMap$$evidence$3.apply(x$6._2()).$greater(x$7._2());
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            })).map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final String apply(Tuple2<K, V> x0$1) {
                    block4: {
                        String string2;
                        block3: {
                            block2: {
                                if (x0$1 == null || !(x0$1._1() instanceof Class)) break block2;
                                Class clazz = (Class)x0$1._1();
                                string2 = new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", ": ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{clazz.toString().substring(clazz.toString().lastIndexOf("$") + 1), x0$1._2()}));
                                break block3;
                            }
                            if (x0$1 == null) break block4;
                            string2 = new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", ": ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{x0$1._1(), x0$1._2()}));
                        }
                        return string2;
                    }
                    throw new MatchError(x0$1);
                }
            }, Seq$.MODULE$.canBuildFrom())).mkString(", ");
        }

        public QuantMap(String prefix, Seq<String> phases, Function0<V> initValue, Function1<V, Ordered<V>> evidence$3) {
            this.prefix = prefix;
            this.phases = phases;
            this.initValue = initValue;
            this.scala$reflect$internal$util$Statistics$QuantMap$$evidence$3 = evidence$3;
            SynchronizedMap$class.$init$(this);
            Statistics$Quantity$class.$init$(this);
        }
    }

    public static interface Quantity {
        public void scala$reflect$internal$util$Statistics$Quantity$_setter_$children_$eq(ListBuffer var1);

        public String prefix();

        public Seq<String> phases();

        public Quantity underlying();

        public boolean showAt(String var1);

        public String line();

        public ListBuffer<Quantity> children();
    }

    public static class SubTimer
    extends Timer
    implements SubQuantity {
        private final Timer underlying;

        @Override
        public Timer underlying() {
            return this.underlying;
        }

        @Override
        public String show(long ns) {
            return new StringBuilder().append((Object)super.show(ns)).append((Object)Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$showPercent(ns, this.underlying().nanos())).toString();
        }

        public SubTimer(String prefix, Timer underlying) {
            this.underlying = underlying;
            super(prefix, underlying.phases());
            Statistics$SubQuantity$class.$init$(this);
        }
    }

    public static class TimerStack {
        private List<Tuple2<StackableTimer, Object>> elems = Nil$.MODULE$;

        private List<Tuple2<StackableTimer, Object>> elems() {
            return this.elems;
        }

        private void elems_$eq(List<Tuple2<StackableTimer, Object>> x$1) {
            this.elems = x$1;
        }

        public Tuple2<Object, Object> push(StackableTimer t) {
            Tuple2<StackableTimer, Long> tuple2 = new Tuple2<StackableTimer, Long>(t, BoxesRunTime.boxToLong(0L));
            this.elems_$eq(this.elems().$colon$colon(tuple2));
            return t.start();
        }

        /*
         * WARNING - void declaration
         */
        public void pop(Tuple2<Object, Object> prev) {
            block2: {
                List<Tuple2<StackableTimer, Object>> list2;
                block3: {
                    void var17_11;
                    block6: {
                        List list3;
                        block5: {
                            List rest;
                            block4: {
                                $colon$colon $colon$colon;
                                $colon$colon $colon$colon2;
                                if (prev == null) break block2;
                                Tuple2$mcJJ$sp tuple2$mcJJ$sp = new Tuple2$mcJJ$sp(prev._1$mcJ$sp(), prev._2$mcJ$sp());
                                long nanos0 = ((Tuple2)tuple2$mcJJ$sp)._1$mcJ$sp();
                                long start = ((Tuple2)tuple2$mcJJ$sp)._2$mcJ$sp();
                                long duration = System.nanoTime() - start;
                                list2 = this.elems();
                                if (!(list2 instanceof $colon$colon) || ($colon$colon2 = ($colon$colon)list2).head() == null) break block3;
                                Tuple3 tuple3 = new Tuple3(((Tuple2)$colon$colon2.head())._1(), BoxesRunTime.boxToLong(((Tuple2)$colon$colon2.head())._2$mcJ$sp()), $colon$colon2.tl$1());
                                StackableTimer topTimer = (StackableTimer)tuple3._1();
                                long nestedNanos = BoxesRunTime.unboxToLong(tuple3._2());
                                rest = tuple3._3();
                                topTimer.nanos_$eq(nanos0 + duration);
                                topTimer.specificNanos_$eq(topTimer.specificNanos() + (duration - nestedNanos));
                                topTimer.timings_$eq(topTimer.timings() + 1);
                                if (!(rest instanceof $colon$colon) || ($colon$colon = ($colon$colon)rest).head() == null) break block4;
                                Tuple2 tuple2 = new Tuple2(((Tuple2)$colon$colon.head())._1(), BoxesRunTime.boxToLong(((Tuple2)$colon$colon.head())._2$mcJ$sp() + duration));
                                list3 = $colon$colon.tl$1().$colon$colon(tuple2);
                                break block5;
                            }
                            if (!((Object)Nil$.MODULE$).equals(rest)) break block6;
                            list3 = Nil$.MODULE$;
                        }
                        this.elems_$eq(list3);
                        return;
                    }
                    throw new MatchError(var17_11);
                }
                throw new MatchError(list2);
            }
            throw new MatchError(prev);
        }
    }

    public static class SubCounter
    extends Counter
    implements SubQuantity {
        private final Counter underlying;

        @Override
        public Counter underlying() {
            return this.underlying;
        }

        public Tuple2<Object, Object> start() {
            return new Tuple2$mcII$sp(this.value(), this.underlying().value());
        }

        public void stop(Tuple2<Object, Object> prev) {
            if (prev != null) {
                Tuple2$mcII$sp tuple2$mcII$sp = new Tuple2$mcII$sp(prev._1$mcI$sp(), prev._2$mcI$sp());
                int value0 = ((Tuple2)tuple2$mcII$sp)._1$mcI$sp();
                int uvalue0 = ((Tuple2)tuple2$mcII$sp)._2$mcI$sp();
                this.value_$eq(value0 + this.underlying().value() - uvalue0);
                return;
            }
            throw new MatchError(prev);
        }

        @Override
        public String toString() {
            return new StringBuilder().append(this.value()).append((Object)Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$showPercent(this.value(), this.underlying().value())).toString();
        }

        public SubCounter(String prefix, Counter underlying) {
            this.underlying = underlying;
            super(prefix, underlying.phases());
            Statistics$SubQuantity$class.$init$(this);
        }
    }

    public static class RelCounter
    extends Counter
    implements SubQuantity {
        private final Counter underlying;

        public /* synthetic */ String scala$reflect$internal$util$Statistics$RelCounter$$super$prefix() {
            return super.prefix();
        }

        @Override
        public Counter underlying() {
            return this.underlying;
        }

        @Override
        public String toString() {
            String string2;
            if (this.value() == 0) {
                string2 = "0";
            } else {
                boolean bl = this.underlying().value() != 0;
                Predef$ predef$ = Predef$.MODULE$;
                if (!bl) {
                    throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringBuilder().append((Object)super.prefix()).append((Object)"/").append((Object)this.underlying().line()).toString()).toString());
                }
                float arg$macro$11 = (float)this.value() / (float)this.underlying().value();
                string2 = new StringOps("%2.1f").format(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToFloat(arg$macro$11)}));
            }
            return string2;
        }

        public RelCounter(String prefix, Counter underlying) {
            this.underlying = underlying;
            super(prefix, underlying.phases());
            Statistics$SubQuantity$class.$init$(this);
        }
    }

    public static interface SubQuantity
    extends Quantity {
        @Override
        public Quantity underlying();
    }

    public static class StackableTimer
    extends SubTimer
    implements Ordered<StackableTimer> {
        private long specificNanos;

        @Override
        public boolean $less(Object that) {
            return Ordered$class.$less(this, that);
        }

        @Override
        public boolean $greater(Object that) {
            return Ordered$class.$greater(this, that);
        }

        @Override
        public boolean $less$eq(Object that) {
            return Ordered$class.$less$eq(this, that);
        }

        @Override
        public boolean $greater$eq(Object that) {
            return Ordered$class.$greater$eq(this, that);
        }

        @Override
        public int compareTo(Object that) {
            return Ordered$class.compareTo(this, that);
        }

        public long specificNanos() {
            return this.specificNanos;
        }

        public void specificNanos_$eq(long x$1) {
            this.specificNanos = x$1;
        }

        @Override
        public int compare(StackableTimer that) {
            return this.specificNanos() < that.specificNanos() ? -1 : (this.specificNanos() > that.specificNanos() ? 1 : 0);
        }

        public boolean equals(Object that) {
            StackableTimer stackableTimer;
            boolean bl = that instanceof StackableTimer ? this.compare(stackableTimer = (StackableTimer)that) == 0 : false;
            return bl;
        }

        public int hashCode() {
            return ScalaRunTime$.MODULE$.hash(this.specificNanos());
        }

        @Override
        public String toString() {
            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " aggregate, ", " specific"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{super.toString(), this.show(this.specificNanos())}));
        }

        public StackableTimer(String prefix, Timer underlying) {
            super(prefix, underlying);
            Ordered$class.$init$(this);
            this.specificNanos = 0L;
        }
    }
}

