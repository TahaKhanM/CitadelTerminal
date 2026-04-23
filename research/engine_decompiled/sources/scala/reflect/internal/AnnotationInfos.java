/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Array$;
import scala.Function0;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.TraversableLike;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.ListMap;
import scala.collection.immutable.ListMap$;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Annotations;
import scala.reflect.api.Annotations$AnnotationApi$class;
import scala.reflect.api.Trees;
import scala.reflect.internal.AnnotationInfos$Annotation$;
import scala.reflect.internal.AnnotationInfos$AnnotationInfo$;
import scala.reflect.internal.AnnotationInfos$ArrayAnnotArg$;
import scala.reflect.internal.AnnotationInfos$LiteralAnnotArg$;
import scala.reflect.internal.AnnotationInfos$NestedAnnotArg$;
import scala.reflect.internal.AnnotationInfos$ScalaSigBytes$;
import scala.reflect.internal.AnnotationInfos$ThrownException$;
import scala.reflect.internal.AnnotationInfos$UnmappableAnnotArg$;
import scala.reflect.internal.AnnotationInfos$UnmappableAnnotation$;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Names;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.pickling.ByteCodecs$;
import scala.reflect.internal.util.Position;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0019-d!C\u0001\u0003!\u0003\r\t!\u0003D2\u0005=\teN\\8uCRLwN\\%oM>\u001c(BA\u0002\u0005\u0003!Ig\u000e^3s]\u0006d'BA\u0003\u0007\u0003\u001d\u0011XM\u001a7fGRT\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001'\r\u0001!B\u0004\t\u0003\u00171i\u0011AB\u0005\u0003\u001b\u0019\u0011a!\u00118z%\u00164\u0007CA\b\u0013\u001b\u0005\u0001\"BA\t\u0005\u0003\r\t\u0007/[\u0005\u0003'A\u00111\"\u00118o_R\fG/[8og\")Q\u0003\u0001C\u0001-\u00051A%\u001b8ji\u0012\"\u0012a\u0006\t\u0003\u0017aI!!\u0007\u0004\u0003\tUs\u0017\u000e\u001e\u0004\b7\u0001\u0001\n1!\u0001\u001d\u0005-\teN\\8uCR\f'\r\\3\u0016\u0007u\u0019Ij\u0005\u0002\u001b\u0015!)QC\u0007C\u0001-!)\u0001E\u0007D\u0001C\u0005Y\u0011M\u001c8pi\u0006$\u0018n\u001c8t+\u0005\u0011\u0003cA\u0012'S9\u00111\u0002J\u0005\u0003K\u0019\tq\u0001]1dW\u0006<W-\u0003\u0002(Q\t!A*[:u\u0015\t)c\u0001\u0005\u0002+W5\t\u0001AB\u0003-\u0001\u0005\u0005QF\u0001\bB]:|G/\u0019;j_:LeNZ8\u0014\u0007-Ra\u0006\u0005\u0002+_%\u0011\u0001G\u0005\u0002\u000e\u0003:tw\u000e^1uS>t\u0017\t]5\t\u000bIZC\u0011A\u001a\u0002\rqJg.\u001b;?)\u0005I\u0003\"B\u001b,\r\u00031\u0014aA1uaV\tq\u0007\u0005\u0002+q%\u0011\u0011H\u000f\u0002\u0005)f\u0004X-\u0003\u0002<\u0005\t)A+\u001f9fg\")Qh\u000bD\u0001}\u0005!\u0011M]4t+\u0005y\u0004cA\u0012'\u0001B\u0011!&Q\u0005\u0003\u0005\u000e\u0013A\u0001\u0016:fK&\u0011AI\u0001\u0002\u0006)J,Wm\u001d\u0005\u0006\r.2\taR\u0001\u0007CN\u001cxnY:\u0016\u0003!\u00032a\t\u0014J!\u0011Y!\nT)\n\u0005-3!A\u0002+va2,'\u0007\u0005\u0002+\u001b&\u0011aj\u0014\u0002\u0005\u001d\u0006lW-\u0003\u0002Q\u0005\t)a*Y7fgB\u0011!F\u0015\u0004\u0006'\u0002\t\t\u0003\u0016\u0002\u0012\u00072\f7o\u001d4jY\u0016\feN\\8u\u0003J<7\u0003\u0002*\u000b+b\u0003\"a\u0003,\n\u0005]3!a\u0002)s_\u0012,8\r\u001e\t\u0003UeK!A\u0017\n\u0003\u001f)\u000bg/Y!sOVlWM\u001c;Ba&DQA\r*\u0005\u0002q#\u0012!U\u0015\u000b%z\u000bI(!4\u0003\u0018\tmd\u0001B0\u0001\u0001\u0002\u0014Q\"\u0011:sCf\feN\\8u\u0003J<7#\u00020RCV#\u0007C\u0001\u0016c\u0013\t\u0019'C\u0001\tBeJ\f\u00170\u0011:hk6,g\u000e^!qSB\u00111\"Z\u0005\u0003M\u001a\u0011AbU3sS\u0006d\u0017N_1cY\u0016D\u0001\"\u00100\u0003\u0016\u0004%\t\u0001[\u000b\u0002SB\u00191B[)\n\u0005-4!!B!se\u0006L\b\u0002C7_\u0005#\u0005\u000b\u0011B5\u0002\u000b\u0005\u0014xm\u001d\u0011\t\u000bIrF\u0011A8\u0015\u0005A\f\bC\u0001\u0016_\u0011\u0015id\u000e1\u0001j\u0011\u0015\u0019h\f\"\u0011u\u0003!!xn\u0015;sS:<G#A;\u0011\u0005YLhBA\u0006x\u0013\tAh!\u0001\u0004Qe\u0016$WMZ\u0005\u0003un\u0014aa\u0015;sS:<'B\u0001=\u0007\u0011\u001dih,!A\u0005\u0002y\fAaY8qsR\u0011\u0001o \u0005\b{q\u0004\n\u00111\u0001j\u0011%\t\u0019AXI\u0001\n\u0003\t)!\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005\u001d!fA5\u0002\n-\u0012\u00111\u0002\t\u0005\u0003\u001b\t9\"\u0004\u0002\u0002\u0010)!\u0011\u0011CA\n\u0003%)hn\u00195fG.,GMC\u0002\u0002\u0016\u0019\t!\"\u00198o_R\fG/[8o\u0013\u0011\tI\"a\u0004\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rC\u0005\u0002\u001ey\u000b\t\u0011\"\u0011\u0002 \u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!!\t\u0011\t\u0005\r\u0012QF\u0007\u0003\u0003KQA!a\n\u0002*\u0005!A.\u00198h\u0015\t\tY#\u0001\u0003kCZ\f\u0017b\u0001>\u0002&!I\u0011\u0011\u00070\u0002\u0002\u0013\u0005\u00111G\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003k\u00012aCA\u001c\u0013\r\tID\u0002\u0002\u0004\u0013:$\b\"CA\u001f=\u0006\u0005I\u0011AA \u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\u0011\u0002HA\u00191\"a\u0011\n\u0007\u0005\u0015cAA\u0002B]fD!\"!\u0013\u0002<\u0005\u0005\t\u0019AA\u001b\u0003\rAH%\r\u0005\n\u0003\u001br\u0016\u0011!C!\u0003\u001f\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003#\u0002b!a\u0015\u0002Z\u0005\u0005SBAA+\u0015\r\t9FB\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA.\u0003+\u0012\u0001\"\u0013;fe\u0006$xN\u001d\u0005\n\u0003?r\u0016\u0011!C\u0001\u0003C\n\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003G\nI\u0007E\u0002\f\u0003KJ1!a\u001a\u0007\u0005\u001d\u0011un\u001c7fC:D!\"!\u0013\u0002^\u0005\u0005\t\u0019AA!\u0011%\tiGXA\u0001\n\u0003\ny'\u0001\u0005iCND7i\u001c3f)\t\t)\u0004C\u0005\u0002ty\u000b\t\u0011\"\u0011\u0002v\u00051Q-];bYN$B!a\u0019\u0002x!Q\u0011\u0011JA9\u0003\u0003\u0005\r!!\u0011\u0007\r\u0005m\u0004\u0001QA?\u0005=a\u0015\u000e^3sC2\feN\\8u\u0003J<7cBA=#\u0006}T\u000b\u001a\t\u0004U\u0005\u0005\u0015bAAB%\t\u0011B*\u001b;fe\u0006d\u0017I]4v[\u0016tG/\u00119j\u0011-\t9)!\u001f\u0003\u0016\u0004%\t!!#\u0002\u000b\r|gn\u001d;\u0016\u0005\u0005-\u0005c\u0001\u0016\u0002\u000e&!\u0011qRAI\u0005!\u0019uN\\:uC:$\u0018bAAJ\u0005\tI1i\u001c8ti\u0006tGo\u001d\u0005\f\u0003/\u000bIH!E!\u0002\u0013\tY)\u0001\u0004d_:\u001cH\u000f\t\u0005\be\u0005eD\u0011AAN)\u0011\ti*a(\u0011\u0007)\nI\b\u0003\u0005\u0002\b\u0006e\u0005\u0019AAF\u0011!\t\u0019+!\u001f\u0005\u0002\u0005%\u0015!\u0002<bYV,\u0007BB:\u0002z\u0011\u0005C\u000fC\u0005~\u0003s\n\t\u0011\"\u0001\u0002*R!\u0011QTAV\u0011)\t9)a*\u0011\u0002\u0003\u0007\u00111\u0012\u0005\u000b\u0003\u0007\tI(%A\u0005\u0002\u0005=VCAAYU\u0011\tY)!\u0003\t\u0015\u0005u\u0011\u0011PA\u0001\n\u0003\ny\u0002\u0003\u0006\u00022\u0005e\u0014\u0011!C\u0001\u0003gA!\"!\u0010\u0002z\u0005\u0005I\u0011AA])\u0011\t\t%a/\t\u0015\u0005%\u0013qWA\u0001\u0002\u0004\t)\u0004\u0003\u0006\u0002N\u0005e\u0014\u0011!C!\u0003\u001fB!\"a\u0018\u0002z\u0005\u0005I\u0011AAa)\u0011\t\u0019'a1\t\u0015\u0005%\u0013qXA\u0001\u0002\u0004\t\t\u0005\u0003\u0006\u0002n\u0005e\u0014\u0011!C!\u0003_B!\"a\u001d\u0002z\u0005\u0005I\u0011IAe)\u0011\t\u0019'a3\t\u0015\u0005%\u0013qYA\u0001\u0002\u0004\t\tE\u0002\u0004\u0002P\u0002\u0001\u0015\u0011\u001b\u0002\u000f\u001d\u0016\u001cH/\u001a3B]:|G/\u0011:h'\u001d\ti-UAj+\u0012\u00042AKAk\u0013\r\t9N\u0005\u0002\u0012\u001d\u0016\u001cH/\u001a3Be\u001e,X.\u001a8u\u0003BL\u0007bCAn\u0003\u001b\u0014)\u001a!C\u0001\u0003;\fq!\u00198o\u0013:4w.F\u0001*\u0011)\t\t/!4\u0003\u0012\u0003\u0006I!K\u0001\tC:t\u0017J\u001c4pA!9!'!4\u0005\u0002\u0005\u0015H\u0003BAt\u0003S\u00042AKAg\u0011\u001d\tY.a9A\u0002%B\u0001\"!\u0006\u0002N\u0012\u0005\u0011Q\u001c\u0005\bg\u00065G\u0011IAx)\t\t\t\u0003C\u0005~\u0003\u001b\f\t\u0011\"\u0001\u0002tR!\u0011q]A{\u0011%\tY.!=\u0011\u0002\u0003\u0007\u0011\u0006\u0003\u0006\u0002\u0004\u00055\u0017\u0013!C\u0001\u0003s,\"!a?+\u0007%\nI\u0001\u0003\u0006\u0002\u001e\u00055\u0017\u0011!C!\u0003?A!\"!\r\u0002N\u0006\u0005I\u0011AA\u001a\u0011)\ti$!4\u0002\u0002\u0013\u0005!1\u0001\u000b\u0005\u0003\u0003\u0012)\u0001\u0003\u0006\u0002J\t\u0005\u0011\u0011!a\u0001\u0003kA!\"!\u0014\u0002N\u0006\u0005I\u0011IA(\u0011)\ty&!4\u0002\u0002\u0013\u0005!1\u0002\u000b\u0005\u0003G\u0012i\u0001\u0003\u0006\u0002J\t%\u0011\u0011!a\u0001\u0003\u0003B!\"!\u001c\u0002N\u0006\u0005I\u0011IA8\u0011)\t\u0019(!4\u0002\u0002\u0013\u0005#1\u0003\u000b\u0005\u0003G\u0012)\u0002\u0003\u0006\u0002J\tE\u0011\u0011!a\u0001\u0003\u00032aA!\u0007\u0001\u0001\nm!!D*dC2\f7+[4CsR,7oE\u0003\u0003\u0018E+F\rC\u0006\u0003 \t]!Q3A\u0005\u0002\t\u0005\u0012!\u00022zi\u0016\u001cXC\u0001B\u0012!\u0011Y!N!\n\u0011\u0007-\u00119#C\u0002\u0003*\u0019\u0011AAQ=uK\"Y!Q\u0006B\f\u0005#\u0005\u000b\u0011\u0002B\u0012\u0003\u0019\u0011\u0017\u0010^3tA!9!Ga\u0006\u0005\u0002\tEB\u0003\u0002B\u001a\u0005k\u00012A\u000bB\f\u0011!\u0011yBa\fA\u0002\t\r\u0002BB:\u0003\u0018\u0011\u0005C\u000fC\u0006\u0003<\t]\u0001R1A\u0005\u0002\t\u0005\u0012AE:fm\u0016t')\u001b;t\u001b\u0006L()\u001a.fe>D1Ba\u0010\u0003\u0018!\u0005\t\u0015)\u0003\u0003$\u0005\u00192/\u001a<f]\nKGo]'bs\n+',\u001a:pA!A!1\tB\f\t\u0003\u0011)%A\bgSR\u001c\u0018J\\(oKN#(/\u001b8h+\t\t\u0019\u0007C\u0004\u0003J\t]A\u0011\u0001\u001c\u0002\u0011MLw-\u00118o_RD\u0001B!\u0014\u0003\u0018\u0011%!qJ\u0001\u0016[\u0006\u0004Hk\u001c(fqRlu\u000eZ*fm\u0016t')\u001b;t)\u0011\u0011\u0019C!\u0015\t\u0011\tM#1\na\u0001\u0005G\t1a\u001d:d\u0011%i(qCA\u0001\n\u0003\u00119\u0006\u0006\u0003\u00034\te\u0003B\u0003B\u0010\u0005+\u0002\n\u00111\u0001\u0003$!Q\u00111\u0001B\f#\u0003%\tA!\u0018\u0016\u0005\t}#\u0006\u0002B\u0012\u0003\u0013A!\"!\b\u0003\u0018\u0005\u0005I\u0011IA\u0010\u0011)\t\tDa\u0006\u0002\u0002\u0013\u0005\u00111\u0007\u0005\u000b\u0003{\u00119\"!A\u0005\u0002\t\u001dD\u0003BA!\u0005SB!\"!\u0013\u0003f\u0005\u0005\t\u0019AA\u001b\u0011)\tiEa\u0006\u0002\u0002\u0013\u0005\u0013q\n\u0005\u000b\u0003?\u00129\"!A\u0005\u0002\t=D\u0003BA2\u0005cB!\"!\u0013\u0003n\u0005\u0005\t\u0019AA!\u0011)\tiGa\u0006\u0002\u0002\u0013\u0005\u0013q\u000e\u0005\u000b\u0003g\u00129\"!A\u0005B\t]D\u0003BA2\u0005sB!\"!\u0013\u0003v\u0005\u0005\t\u0019AA!\r\u001d\u0011i\b\u0001EA\u0005\u007f\u0012!#\u00168nCB\u0004\u0018M\u00197f\u0003:tw\u000e^!sON)!1P)VI\"9!Ga\u001f\u0005\u0002\t\rEC\u0001BC!\rQ#1\u0010\u0005\u000b\u0003;\u0011Y(!A\u0005B\u0005}\u0001BCA\u0019\u0005w\n\t\u0011\"\u0001\u00024!Q\u0011Q\bB>\u0003\u0003%\tA!$\u0015\t\u0005\u0005#q\u0012\u0005\u000b\u0003\u0013\u0012Y)!AA\u0002\u0005U\u0002BCA'\u0005w\n\t\u0011\"\u0011\u0002P!Q\u0011q\fB>\u0003\u0003%\tA!&\u0015\t\u0005\r$q\u0013\u0005\u000b\u0003\u0013\u0012\u0019*!AA\u0002\u0005\u0005\u0003BCA7\u0005w\n\t\u0011\"\u0011\u0002p!I1Oa\u001f\u0002\u0002\u0013\u0005\u0013q\u001e\u0005\u0007\u0005?[C\u0011\u0001\u001c\u0002\u0007Q\u0004X\r\u0003\u0004\u0003$.\"\tAP\u0001\ng\u000e\fG.Y!sONDqAa*,\t\u0003\u0011I+\u0001\u0005kCZ\f\u0017I]4t+\t\u0011Y\u000b\u0005\u0004\u0003.\nMF*U\u0007\u0003\u0005_SAA!-\u0002V\u0005I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u0005k\u0013yKA\u0004MSN$X*\u00199\t\u000f\te6F\"\u0001\u0003<\u0006AqN]5hS:\fG.F\u0001A\u0011\u001d\u0011yl\u000bD\u0001\u0005\u0003\f1b]3u\u001fJLw-\u001b8bYR!!1\u0019Bc\u001b\u0005Y\u0003b\u0002Bd\u0005{\u0003\r\u0001Q\u0001\u0002i\"Q!1Z\u0016\t\u0006\u0004%\tA!\u0012\u0002\u0013%\u001cHK]5wS\u0006d\u0007B\u0003BhW!\u0005\t\u0015)\u0003\u0002d\u0005Q\u0011n\u001d+sSZL\u0017\r\u001c\u0011\t\u0013\tM7\u00061A\u0005\n\tU\u0017A\u0002:boB|7/\u0006\u0002\u0003XB\u0019!F!7\n\t\tm'Q\u001c\u0002\t!>\u001c\u0018\u000e^5p]&\u0019!q\u001c\u0002\u0003\u0013A{7/\u001b;j_:\u001c\b\"\u0003BrW\u0001\u0007I\u0011\u0002Bs\u0003)\u0011\u0018m\u001e9pg~#S-\u001d\u000b\u0004/\t\u001d\bBCA%\u0005C\f\t\u00111\u0001\u0003X\"A!1^\u0016!B\u0013\u00119.A\u0004sC^\u0004xn\u001d\u0011\t\u000f\t=8\u0006\"\u0001\u0003V\u0006\u0019\u0001o\\:\t\u000f\tM8\u0006\"\u0001\u0003v\u000611/\u001a;Q_N$BAa1\u0003x\"A!q\u001eBy\u0001\u0004\u00119\u000e\u0003\u0004\u0003|.\"\tAF\u0001\rG>l\u0007\u000f\\3uK&sgm\u001c\u0005\b\u0005\u007f\\C\u0011AB\u0001\u0003\u0019\u0019\u00180\u001c2pYV\u001111\u0001\t\u0004U\r\u0015\u0011\u0002BB\u0004\u0007\u0013\u0011aaU=nE>d\u0017bAB\u0006\u0005\t91+_7c_2\u001c\bBBB\bW\u0011\u0005\u0011%A\bnKR\f\u0017I\u001c8pi\u0006$\u0018n\u001c8t\u0011\u001d\u0019\u0019b\u000bC\u0001\u0007+\ta\u0002Z3gCVdG\u000fV1sO\u0016$8/\u0006\u0002\u0004\u0018A1!QVB\r\u0007\u0007I1a\nBX\u0011\u001d\u0019ib\u000bC\u0001\u0007?\tq!\\1uG\",7\u000f\u0006\u0003\u0002d\r\u0005\u0002\u0002CB\u0012\u00077\u0001\raa\u0001\u0002\u000b\rd\u0017M\u001f>\t\u000f\r\u001d2\u0006\"\u0001\u0004*\u0005Y\u0001.Y:Be\u001e<\u0006.[2i)\u0011\t\u0019ga\u000b\t\u0011\r52Q\u0005a\u0001\u0007_\t\u0011\u0001\u001d\t\u0007\u0017\rE\u0002)a\u0019\n\u0007\rMbAA\u0005Gk:\u001cG/[8oc!91qG\u0016\u0005\u0002\t\u0015\u0013aC5t\u000bJ\u0014xN\\3pkNDqaa\u000f,\t\u0003\u0011)%\u0001\u0005jgN#\u0018\r^5d\u0011\u001d\u0019yd\u000bC\u0001\u0007\u0003\n!B]3ggNKXNY8m)\u0011\t\u0019ga\u0011\t\u0011\r\u00153Q\ba\u0001\u0007\u0007\t1a]=n\u0011\u001d\u0019Ie\u000bC\u0001\u0007\u0017\n\u0011b\u001d;sS:<\u0017I]4\u0015\t\r531\u000b\t\u0005\u0017\r=S/C\u0002\u0004R\u0019\u0011aa\u00149uS>t\u0007\u0002CB+\u0007\u000f\u0002\r!!\u000e\u0002\u000b%tG-\u001a=\t\u000f\re3\u0006\"\u0001\u0004\\\u00051\u0011N\u001c;Be\u001e$Ba!\u0018\u0004`A)1ba\u0014\u00026!A1QKB,\u0001\u0004\t)\u0004C\u0004\u0004d-\"\ta!\u001a\u0002\u0013MLXNY8m\u0003J<G\u0003BB4\u0007_\u0002RaCB(\u0007S\u00022AKB6\u0013\r\u0019ig\u0014\u0002\t)\u0016\u0014XNT1nK\"A1QKB1\u0001\u0004\t)\u0004C\u0004\u0004t-\"\ta!\u001e\u0002\u001f\r|gn\u001d;b]R\fE/\u00138eKb$Baa\u001e\u0004zA)1ba\u0014\u0002\f\"A1QKB9\u0001\u0004\t)\u0004C\u0004\u0004~-\"\taa \u0002\u0015\u0005\u0014x-\u0011;J]\u0012,\u0007\u0010\u0006\u0003\u0004\u0002\u000e\r\u0005\u0003B\u0006\u0004P\u0001C\u0001b!\u0016\u0004|\u0001\u0007\u0011Q\u0007\u0005\b\u0003[ZC\u0011IA8\u0011\u001d\t\u0019h\u000bC!\u0007\u0013#B!a\u0019\u0004\f\"A1QRBD\u0001\u0004\t\t%A\u0003pi\",'\u000fC\u0004\u0004\u0012j1\taa%\u0002\u001dM,G/\u00118o_R\fG/[8ogR!1QSBS!\u0011\u00199j!'\r\u0001\u0011911\u0014\u000eC\u0002\ru%\u0001B*fY\u001a\fBaa(\u0002BA\u00191b!)\n\u0007\r\rfAA\u0004O_RD\u0017N\\4\t\u000f\r\u001d6q\u0012a\u0001E\u00051\u0011M\u001c8piNDqaa+\u001b\r\u0003\u0019i+A\bxSRD\u0017I\u001c8pi\u0006$\u0018n\u001c8t)\u0011\u0019)ja,\t\u000f\r\u001d6\u0011\u0016a\u0001E!911\u0017\u000e\u0007\u0002\rU\u0016!\u00054jYR,'/\u00118o_R\fG/[8ogR!1QSB\\\u0011!\u0019ic!-A\u0002\re\u0006CB\u0006\u00042%\n\u0019\u0007C\u0004\u0004>j1\taa0\u0002%]LG\u000f[8vi\u0006sgn\u001c;bi&|gn]\u000b\u0003\u0007+Cqaa1\u001b\t\u0003\u0019)-A\tti\u0006$\u0018nY!o]>$\u0018\r^5p]N,\"aa2\u0011\u000b\t56\u0011D\u0015\t\u000f\r-'\u0004\"\u0001\u0004N\u0006\tB\u000f\u001b:poN\feN\\8uCRLwN\\:\u0015\u0005\r=\u0007\u0003B\u0012'\u0007\u0007Aqaa5\u001b\t\u0003\u0019).A\nbI\u0012$\u0006N]8xg\u0006sgn\u001c;bi&|g\u000e\u0006\u0003\u0004\u0016\u000e]\u0007\u0002CBm\u0007#\u0004\raa\u0001\u0002\u0019QD'o\\<bE2,7+_7\t\u000f\ru'\u0004\"\u0001\u0004`\u0006i\u0001.Y:B]:|G/\u0019;j_:$B!a\u0019\u0004b\"A11]Bn\u0001\u0004\u0019\u0019!A\u0002dYNDqaa:\u001b\t\u0003\u0019I/A\u0007hKR\feN\\8uCRLwN\u001c\u000b\u0005\u0007W\u001ci\u000f\u0005\u0003\f\u0007\u001fJ\u0003\u0002CBr\u0007K\u0004\raa\u0001\t\u000f\rE(\u0004\"\u0001\u0004t\u0006\u0001\"/Z7pm\u0016\feN\\8uCRLwN\u001c\u000b\u0005\u0007+\u001b)\u0010\u0003\u0005\u0004d\u000e=\b\u0019AB\u0002\u0011\u001d\u0019IP\u0007C\u0003\u0007w\fab^5uQ\u0006sgn\u001c;bi&|g\u000e\u0006\u0003\u0004\u0016\u000eu\bbBB\u0000\u0007o\u0004\r!K\u0001\u0006C:tw\u000e\u001e\u0005\b\t\u0007QB\u0011\u0002C\u0003\u0003Q!'o\u001c9Pi\",'/\u00118o_R\fG/[8ogR)!\u0005b\u0002\u0005\f!9A\u0011\u0002C\u0001\u0001\u0004\u0011\u0013\u0001B1o]ND\u0001ba9\u0005\u0002\u0001\u000711\u0001\u0015\u0005\t\u0003!y\u0001\u0005\u0003\u0005\u0012\u0011MQBAA\n\u0013\u0011!)\"a\u0005\u0003\u000fQ\f\u0017\u000e\u001c:fG\"IA\u0011\u0004\u0001C\u0002\u0013\rA1D\u0001\u0010\u0015\u00064\u0018-\u0011:hk6,g\u000e\u001e+bOV\u0011AQ\u0004\t\u0006\t?!\t#U\u0007\u0002\t%\u0019A1\u0005\u0003\u0003\u0011\rc\u0017m]:UC\u001eD\u0001\u0002b\n\u0001A\u0003%AQD\u0001\u0011\u0015\u00064\u0018-\u0011:hk6,g\u000e\u001e+bO\u0002:q\u0001b\u000b\u0001\u0011\u0003\u0013))\u0001\nV]6\f\u0007\u000f]1cY\u0016\feN\\8u\u0003J<wa\u0002C\u0018\u0001!\u0005A\u0011G\u0001\u0010\u0019&$XM]1m\u0003:tw\u000e^!sOB\u0019!\u0006b\r\u0007\u000f\u0005m\u0004\u0001#\u0001\u00056M)A1\u0007C\u001cIB\u0019!\u0006\"\u000f\n\u0007\u0011m\"C\u0001\rMSR,'/\u00197Be\u001e,X.\u001a8u\u000bb$(/Y2u_JDqA\rC\u001a\t\u0003!y\u0004\u0006\u0002\u00052!QA1\tC\u001a\u0003\u0003%\t\t\"\u0012\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\u0005uEq\t\u0005\t\u0003\u000f#\t\u00051\u0001\u0002\f\"QA1\nC\u001a\u0003\u0003%\t\t\"\u0014\u0002\u000fUt\u0017\r\u001d9msR!1q\u000fC(\u0011)!\t\u0006\"\u0013\u0002\u0002\u0003\u0007\u0011QT\u0001\u0004q\u0012\u0002ta\u0002C+\u0001!\u0005AqK\u0001\u000e\u0003J\u0014\u0018-_!o]>$\u0018I]4\u0011\u0007)\"IF\u0002\u0004`\u0001!\u0005A1L\n\u0006\t3\"i\u0006\u001a\t\u0004U\u0011}\u0013b\u0001C1%\t1\u0012I\u001d:bs\u0006\u0013x-^7f]R,\u0005\u0010\u001e:bGR|'\u000fC\u00043\t3\"\t\u0001\"\u001a\u0015\u0005\u0011]\u0003B\u0003C\"\t3\n\t\u0011\"!\u0005jQ\u0019\u0001\u000fb\u001b\t\ru\"9\u00071\u0001j\u0011)!Y\u0005\"\u0017\u0002\u0002\u0013\u0005Eq\u000e\u000b\u0005\tc\"Y\bE\u0003\f\u0007\u001f\"\u0019\b\u0005\u0003\fU\u0012U\u0004c\u0001\u0016\u0005x\u0015)A\u0011\u0010\u0001\u0001#\na!*\u0019<b\u0003J<W/\\3oi\"IA\u0011\u000bC7\u0003\u0003\u0005\r\u0001]\u0004\b\t\u007f\u0002\u0001\u0012\u0001CA\u00039qUm\u001d;fI\u0006sgn\u001c;Be\u001e\u00042A\u000bCB\r\u001d\ty\r\u0001E\u0001\t\u000b\u001bR\u0001b!\u0005\b\u0012\u00042A\u000bCE\u0013\r!YI\u0005\u0002\u0018\u001d\u0016\u001cH/\u001a3Be\u001e,X.\u001a8u\u000bb$(/Y2u_JDqA\rCB\t\u0003!y\t\u0006\u0002\u0005\u0002\"QA1\tCB\u0003\u0003%\t\tb%\u0015\t\u0005\u001dHQ\u0013\u0005\b\u00037$\t\n1\u0001*\u0011)!Y\u0005b!\u0002\u0002\u0013\u0005E\u0011\u0014\u000b\u0005\t7#\u0019\u000bE\u0003\f\u0007\u001f\"i\nE\u0002+\t?+Q\u0001\")\u0001\u0001%\u0012!\"\u00118o_R\fG/[8o\u0011)!\t\u0006b&\u0002\u0002\u0003\u0007\u0011q]\u0003\u0007\tO\u0003\u0001!!(\u0003\u001f1KG/\u001a:bY\u0006\u0013x-^7f]RD\u0011\u0002b+\u0001\u0005\u0004%\t\u0001\",\u0002\u001f1KG/\u001a:bY\u0006\u0013x-^7f]R,\"\u0001b,\u000f\u0007)\"i\u0003\u0003\u0005\u00054\u0002\u0001\u000b\u0011\u0002CX\u0003Aa\u0015\u000e^3sC2\f%oZ;nK:$\b\u0005C\u0005\u00058\u0002\u0011\r\u0011b\u0001\u0005:\u0006\u0011B*\u001b;fe\u0006d\u0017I]4v[\u0016tG\u000fV1h+\t!Y\f\u0005\u0004\u0005 \u0011\u0005\u0012Q\u0014\u0005\t\t\u007f\u0003\u0001\u0015!\u0003\u0005<\u0006\u0019B*\u001b;fe\u0006d\u0017I]4v[\u0016tG\u000fV1hA\u0015)A1\u0019\u0001\u0001a\ni\u0011I\u001d:bs\u0006\u0013x-^7f]RD\u0011\u0002b2\u0001\u0005\u0004%\t\u0001\"3\u0002\u001b\u0005\u0013(/Y=Be\u001e,X.\u001a8u+\t!YMD\u0002+\t'B\u0001\u0002b4\u0001A\u0003%A1Z\u0001\u000f\u0003J\u0014\u0018-_!sOVlWM\u001c;!\u0011%!\u0019\u000e\u0001b\u0001\n\u0007!).\u0001\tBeJ\f\u00170\u0011:hk6,g\u000e\u001e+bOV\u0011Aq\u001b\t\u0006\t?!\t\u0003\u001d\u0005\t\t7\u0004\u0001\u0015!\u0003\u0005X\u0006\t\u0012I\u001d:bs\u0006\u0013x-^7f]R$\u0016m\u001a\u0011\u0006\r\u0011}\u0007\u0001AAt\u00059qUm\u001d;fI\u0006\u0013x-^7f]RD\u0011\u0002b9\u0001\u0005\u0004%\t\u0001\":\u0002\u001d9+7\u000f^3e\u0003J<W/\\3oiV\u0011Aq\u001d\b\u0004U\u0011u\u0004\u0002\u0003Cv\u0001\u0001\u0006I\u0001b:\u0002\u001f9+7\u000f^3e\u0003J<W/\\3oi\u0002B\u0011\u0002b<\u0001\u0005\u0004%\u0019\u0001\"=\u0002#9+7\u000f^3e\u0003J<W/\\3oiR\u000bw-\u0006\u0002\u0005tB1Aq\u0004C\u0011\u0003OD\u0001\u0002b>\u0001A\u0003%A1_\u0001\u0013\u001d\u0016\u001cH/\u001a3Be\u001e,X.\u001a8u)\u0006<\u0007eB\u0005\u0005|\u0002\t\t\u0011#\u0001\u0005~\u0006i1kY1mCNKwMQ=uKN\u00042A\u000bC\u0000\r%\u0011I\u0002AA\u0001\u0012\u0003)\taE\u0003\u0005\u0000\u0016\rA\r\u0005\u0005\u0006\u0006\u0015-!1\u0005B\u001a\u001b\t)9AC\u0002\u0006\n\u0019\tqA];oi&lW-\u0003\u0003\u0006\u000e\u0015\u001d!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oc!9!\u0007b@\u0005\u0002\u0015EAC\u0001C\u007f\u0011%\u0019Hq`A\u0001\n\u000b\ny\u000f\u0003\u0006\u0005D\u0011}\u0018\u0011!CA\u000b/!BAa\r\u0006\u001a!A!qDC\u000b\u0001\u0004\u0011\u0019\u0003\u0003\u0006\u0005L\u0011}\u0018\u0011!CA\u000b;!B!b\b\u0006\"A)1ba\u0014\u0003$!QA\u0011KC\u000e\u0003\u0003\u0005\rAa\r\b\u000f\u0015\u0015\u0002\u0001#\u0001\u0006(\u0005q\u0011I\u001c8pi\u0006$\u0018n\u001c8J]\u001a|\u0007c\u0001\u0016\u0006*\u00191A\u0006\u0001E\u0001\u000bW\u00192!\"\u000b\u000b\u0011\u001d\u0011T\u0011\u0006C\u0001\u000b_!\"!b\n\t\u0011\u0015MR\u0011\u0006C\u0001\u000bk\ta!\\1sW\u0016\u0014HcA\u0015\u00068!1Q'\"\rA\u0002]B\u0001\"b\u000f\u0006*\u0011\u0005QQH\u0001\u0007Y\u0006T\u0018\u000e\\=\u0015\t\u0015}RQ\u0011\t\u0004U\u0015\u0005cABC\"\u0001\t))E\u0001\nMCjL\u0018I\u001c8pi\u0006$\u0018n\u001c8J]\u001a|7cAC!S!YQ\u0011JC!\u0005\u0003%\u000b\u0011BC&\u0003!a\u0017M_=J]\u001a|\u0007\u0003B\u0006\u0006N%J1!b\u0014\u0007\u0005!a$-\u001f8b[\u0016t\u0004b\u0002\u001a\u0006B\u0011\u0005Q1\u000b\u000b\u0005\u000b\u007f))\u0006C\u0005\u0006J\u0015EC\u00111\u0001\u0006L!QQ\u0011LC!\u0001\u0004%IA!\u0012\u0002\r\u0019|'oY3e\u0011))i&\"\u0011A\u0002\u0013%QqL\u0001\u000bM>\u00148-\u001a3`I\u0015\fHcA\f\u0006b!Q\u0011\u0011JC.\u0003\u0003\u0005\r!a\u0019\t\u0013\u0015\u0015T\u0011\tQ!\n\u0005\r\u0014a\u00024pe\u000e,G\r\t\u0005\f\u000bS*\t\u0005#b\u0001\n\u0013\ti.\u0001\u0006g_J\u001cW\rZ%oM>D!\"\"\u001c\u0006B!\u0005\t\u0015)\u0003*\u0003-1wN]2fI&sgm\u001c\u0011\t\rU*\t\u0005\"\u00017\u0011\u0019iT\u0011\tC\u0001}!1a)\"\u0011\u0005\u0002\u001dC\u0001B!/\u0006B\u0011\u0005!1\u0018\u0005\t\u0005\u007f+\t\u0005\"\u0001\u0006zQ!Q1PC?\u001b\t)\t\u0005C\u0004\u0003H\u0016]\u0004\u0019\u0001!\t\u000fM,\t\u0005\"\u0011\u0002p\"A!q^C!\t\u0003\u0012)\u000eC\u0004\u0003|\u0016\u0005C\u0011\t\f\t\u0013\u0015%S\u0011\bCA\u0002\u0015-\u0003\u0002\u0003C\"\u000bS!\t!\"#\u0015\u000f%*Y)\"$\u0006\u0010\"1Q'b\"A\u0002]Ba!PCD\u0001\u0004y\u0004B\u0002$\u0006\b\u0002\u0007\u0001\n\u0003\u0005\u0005L\u0015%B\u0011ACJ)\u0011))*\"(\u0011\u000b-\u0019y%b&\u0011\r-)IjN I\u0013\r)YJ\u0002\u0002\u0007)V\u0004H.Z\u001a\t\u000f\u0015}U\u0011\u0013a\u0001S\u0005!\u0011N\u001c4p\r\u0019)\u0019\u000b\u0001\u0001\u0006&\n12i\\7qY\u0016$X-\u00118o_R\fG/[8o\u0013:4wnE\u0002\u0006\"&B\u0011\"NCQ\u0005\u000b\u0007I\u0011\u0001\u001c\t\u0015\u0015-V\u0011\u0015B\u0001B\u0003%q'\u0001\u0003biB\u0004\u0003\"C\u001f\u0006\"\n\u0015\r\u0011\"\u0001?\u0011%iW\u0011\u0015B\u0001B\u0003%q\bC\u0005G\u000bC\u0013)\u0019!C\u0001\u000f\"QQQWCQ\u0005\u0003\u0005\u000b\u0011\u0002%\u0002\u000f\u0005\u001c8o\\2tA!9!'\")\u0005\u0002\u0015eF\u0003CC^\u000b{+y,\"1\u0011\u0007)*\t\u000b\u0003\u00046\u000bo\u0003\ra\u000e\u0005\u0007{\u0015]\u0006\u0019A \t\r\u0019+9\f1\u0001I\u0011)))-\")A\u0002\u0013%!1X\u0001\u0005_JLw\r\u0003\u0006\u0006J\u0016\u0005\u0006\u0019!C\u0005\u000b\u0017\f\u0001b\u001c:jO~#S-\u001d\u000b\u0004/\u00155\u0007\"CA%\u000b\u000f\f\t\u00111\u0001A\u0011!)\t.\")!B\u0013\u0001\u0015!B8sS\u001e\u0004\u0003\u0002\u0003B]\u000bC#\tAa/\t\u0011\t}V\u0011\u0015C\u0001\u000b/$B!\"7\u0006\\6\u0011Q\u0011\u0015\u0005\b\u0005\u000f,)\u000e1\u0001A\u0011\u0019\u0019X\u0011\u0015C!i\"AQ\u0011\u001d\u0001\u0005\u0002\u0019)\u0019/\u0001\u000ed_6\u0004H.\u001a;f\u0003:tw\u000e^1uS>tGk\\*ue&tw\rF\u0002v\u000bKDq!a7\u0006`\u0002\u0007\u0011fB\u0004\u0006j\u0002A\t!b;\u0002\u0015\u0005sgn\u001c;bi&|g\u000eE\u0002+\u000b[4q\u0001\")\u0001\u0011\u0003)yo\u0005\u0003\u0006n\u0016E\bc\u0001\u0016\u0006t&\u0019QQ\u001f\n\u0003'\u0005sgn\u001c;bi&|g.\u0012=ue\u0006\u001cGo\u001c:\t\u000fI*i\u000f\"\u0001\u0006zR\u0011Q1\u001e\u0005\t\t\u0007*i\u000f\"\u0001\u0006~RAAQTC\u0000\r\u00031\u0019\u0001C\u0004\u0003 \u0016m\b\u0019A\u001c\t\u000f\t\rV1 a\u0001\u007f!A!qUC~\u0001\u0004\u0011Y\u000b\u0003\u0005\u0005L\u00155H\u0011\u0001D\u0004)\u00111IA\"\u0004\u0011\u000b-\u0019yEb\u0003\u0011\u000f-)IjN \u0003,\"A\u0011Q\u0003D\u0003\u0001\u0004!i\nC\u0005\u0007\u0012\u0001\u0011\r\u0011b\u0001\u0007\u0014\u0005i\u0011I\u001c8pi\u0006$\u0018n\u001c8UC\u001e,\"A\"\u0006\u0011\u000b\u0011}A\u0011E\u0015\t\u0011\u0019e\u0001\u0001)A\u0005\r+\ta\"\u00118o_R\fG/[8o)\u0006<\u0007\u0005\u0003\u0005\u0007\u001e\u0001!\tB\u0002D\u0010\u0003A\tgN\\8uCRLwN\u001c+p)J,W\rF\u0002A\rCA\u0001Bb\t\u0007\u001c\u0001\u0007AQT\u0001\u0004C:t\u0007\u0002\u0003D\u0014\u0001\u0011EaA\"\u000b\u0002!Q\u0014X-\u001a+p\u0003:tw\u000e^1uS>tG\u0003\u0002CO\rWAqA\"\f\u0007&\u0001\u0007\u0001)\u0001\u0003ue\u0016,wa\u0002D\u0019\u0001!\u0005a1G\u0001\u0015+:l\u0017\r\u001d9bE2,\u0017I\u001c8pi\u0006$\u0018n\u001c8\u0011\u0007)2)DB\u0004\u00078\u0001A\tA\"\u000f\u0003)UsW.\u00199qC\ndW-\u00118o_R\fG/[8o'\u00111)$b/\t\u000fI2)\u0004\"\u0001\u0007>Q\u0011a1\u0007\u0004\u0007\r\u0003\u0002\u0001Ab\u0011\u0003'\u0015\u0013(o\u001c8f_V\u001c\u0018I\u001c8pi\u0006$\u0018n\u001c8\u0014\t\u0019}R1\u0018\u0005\be\u0019}B\u0011\u0001D$)\t1I\u0005E\u0002+\r\u007f9qA\"\u0014\u0001\u0011\u00031y%A\bUQJ|wO\\#yG\u0016\u0004H/[8o!\rQc\u0011\u000b\u0004\b\r'\u0002\u0001\u0012\u0001D+\u0005=!\u0006N]8x]\u0016C8-\u001a9uS>t7c\u0001D)\u0015!9!G\"\u0015\u0005\u0002\u0019eCC\u0001D(\u0011!!YE\"\u0015\u0005\u0002\u0019uC\u0003\u0002D0\rC\u0002RaCB(\u0007\u0007AqAb\t\u0007\\\u0001\u0007\u0011\u0006\u0005\u0003\u0007f\u0019\u001dT\"\u0001\u0002\n\u0007\u0019%$AA\u0006Ts6\u0014w\u000e\u001c+bE2,\u0007")
public interface AnnotationInfos
extends Annotations {
    public void scala$reflect$internal$AnnotationInfos$_setter_$JavaArgumentTag_$eq(ClassTag var1);

    public void scala$reflect$internal$AnnotationInfos$_setter_$LiteralArgument_$eq(AnnotationInfos$LiteralAnnotArg$ var1);

    public void scala$reflect$internal$AnnotationInfos$_setter_$LiteralArgumentTag_$eq(ClassTag var1);

    public void scala$reflect$internal$AnnotationInfos$_setter_$ArrayArgument_$eq(AnnotationInfos$ArrayAnnotArg$ var1);

    public void scala$reflect$internal$AnnotationInfos$_setter_$ArrayArgumentTag_$eq(ClassTag var1);

    public void scala$reflect$internal$AnnotationInfos$_setter_$NestedArgument_$eq(AnnotationInfos$NestedAnnotArg$ var1);

    public void scala$reflect$internal$AnnotationInfos$_setter_$NestedArgumentTag_$eq(ClassTag var1);

    public void scala$reflect$internal$AnnotationInfos$_setter_$AnnotationTag_$eq(ClassTag var1);

    public ClassTag<ClassfileAnnotArg> JavaArgumentTag();

    public AnnotationInfos$UnmappableAnnotArg$ UnmappableAnnotArg();

    public AnnotationInfos$LiteralAnnotArg$ LiteralAnnotArg();

    public AnnotationInfos$ArrayAnnotArg$ ArrayAnnotArg();

    public AnnotationInfos$NestedAnnotArg$ NestedAnnotArg();

    @Override
    public AnnotationInfos$LiteralAnnotArg$ LiteralArgument();

    public ClassTag<LiteralAnnotArg> LiteralArgumentTag();

    @Override
    public AnnotationInfos$ArrayAnnotArg$ ArrayArgument();

    public ClassTag<ArrayAnnotArg> ArrayArgumentTag();

    @Override
    public AnnotationInfos$NestedAnnotArg$ NestedArgument();

    public ClassTag<NestedAnnotArg> NestedArgumentTag();

    public AnnotationInfos$ScalaSigBytes$ ScalaSigBytes();

    public AnnotationInfos$AnnotationInfo$ AnnotationInfo();

    public String completeAnnotationToString(AnnotationInfo var1);

    @Override
    public AnnotationInfos$Annotation$ Annotation();

    public ClassTag<AnnotationInfo> AnnotationTag();

    public Trees.Tree annotationToTree(AnnotationInfo var1);

    public AnnotationInfo treeToAnnotation(Trees.Tree var1);

    public AnnotationInfos$UnmappableAnnotation$ UnmappableAnnotation();

    public AnnotationInfos$ThrownException$ ThrownException();

    public interface Annotatable<Self> {
        public List<AnnotationInfo> annotations();

        public Self setAnnotations(List<AnnotationInfo> var1);

        public Self withAnnotations(List<AnnotationInfo> var1);

        public Self filterAnnotations(Function1<AnnotationInfo, Object> var1);

        public Self withoutAnnotations();

        public List<AnnotationInfo> staticAnnotations();

        public List<Symbols.Symbol> throwsAnnotations();

        public Self addThrowsAnnotation(Symbols.Symbol var1);

        public boolean hasAnnotation(Symbols.Symbol var1);

        public Option<AnnotationInfo> getAnnotation(Symbols.Symbol var1);

        public Self removeAnnotation(Symbols.Symbol var1);

        public Self withAnnotation(AnnotationInfo var1);

        public /* synthetic */ AnnotationInfos scala$reflect$internal$AnnotationInfos$Annotatable$$$outer();
    }

    public class ArrayAnnotArg
    extends ClassfileAnnotArg
    implements Annotations.ArrayArgumentApi,
    Serializable {
        private final ClassfileAnnotArg[] args;

        public ClassfileAnnotArg[] args() {
            return this.args;
        }

        public String toString() {
            return Predef$.MODULE$.refArrayOps((Object[])this.args()).mkString("[", ", ", "]");
        }

        public ArrayAnnotArg copy(ClassfileAnnotArg[] args) {
            return new ArrayAnnotArg(this.scala$reflect$internal$AnnotationInfos$ArrayAnnotArg$$$outer(), args);
        }

        public ClassfileAnnotArg[] copy$default$1() {
            return this.args();
        }

        @Override
        public String productPrefix() {
            return "ArrayAnnotArg";
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
            return this.args();
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof ArrayAnnotArg;
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
            if (!(x$1 instanceof ArrayAnnotArg)) return false;
            if (((ArrayAnnotArg)x$1).scala$reflect$internal$AnnotationInfos$ArrayAnnotArg$$$outer() != this.scala$reflect$internal$AnnotationInfos$ArrayAnnotArg$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            ArrayAnnotArg arrayAnnotArg = (ArrayAnnotArg)x$1;
            if (this.args() != arrayAnnotArg.args()) return false;
            if (!arrayAnnotArg.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$AnnotationInfos$ArrayAnnotArg$$$outer() {
            return this.$outer;
        }

        public ArrayAnnotArg(SymbolTable $outer, ClassfileAnnotArg[] args) {
            this.args = args;
            super($outer);
        }
    }

    public class ScalaSigBytes
    extends ClassfileAnnotArg
    implements Serializable {
        private final byte[] bytes;
        private byte[] sevenBitsMayBeZero;
        private volatile boolean bitmap$0;

        private byte[] sevenBitsMayBeZero$lzycompute() {
            ScalaSigBytes scalaSigBytes = this;
            synchronized (scalaSigBytes) {
                if (!this.bitmap$0) {
                    this.sevenBitsMayBeZero = this.mapToNextModSevenBits(ByteCodecs$.MODULE$.encode8to7(this.bytes()));
                    this.bitmap$0 = true;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.sevenBitsMayBeZero;
            }
        }

        public byte[] bytes() {
            return this.bytes;
        }

        public String toString() {
            return Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.byteArrayOps(this.bytes()).map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final String apply(byte by2) {
                    int n = by2 & 0xFF;
                    Predef$ predef$ = Predef$.MODULE$;
                    return RichInt$.MODULE$.toHexString$extension(n);
                }
            }, Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(String.class)))).mkString("[ ", " ", " ]");
        }

        public byte[] sevenBitsMayBeZero() {
            return this.bitmap$0 ? this.sevenBitsMayBeZero : this.sevenBitsMayBeZero$lzycompute();
        }

        public boolean fitsInOneString() {
            int numZeros = Predef$.MODULE$.byteArrayOps(this.sevenBitsMayBeZero()).count(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(byte b) {
                    return b == 0;
                }
            });
            return this.sevenBitsMayBeZero().length + numZeros <= 65535;
        }

        public Types.Type sigAnnot() {
            return this.fitsInOneString() ? this.scala$reflect$internal$AnnotationInfos$ScalaSigBytes$$$outer().definitions().ScalaSignatureAnnotation().tpe() : this.scala$reflect$internal$AnnotationInfos$ScalaSigBytes$$$outer().definitions().ScalaLongSignatureAnnotation().tpe();
        }

        private byte[] mapToNextModSevenBits(byte[] src) {
            int srclen = src.length;
            for (int i = 0; i < srclen; ++i) {
                byte in = src[i];
                src[i] = in == 127 ? (byte)0 : (byte)(in + 1);
            }
            return src;
        }

        public ScalaSigBytes copy(byte[] bytes2) {
            return new ScalaSigBytes(this.scala$reflect$internal$AnnotationInfos$ScalaSigBytes$$$outer(), bytes2);
        }

        public byte[] copy$default$1() {
            return this.bytes();
        }

        @Override
        public String productPrefix() {
            return "ScalaSigBytes";
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
            return this.bytes();
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof ScalaSigBytes;
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
            if (!(x$1 instanceof ScalaSigBytes)) return false;
            if (((ScalaSigBytes)x$1).scala$reflect$internal$AnnotationInfos$ScalaSigBytes$$$outer() != this.scala$reflect$internal$AnnotationInfos$ScalaSigBytes$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            ScalaSigBytes scalaSigBytes = (ScalaSigBytes)x$1;
            if (this.bytes() != scalaSigBytes.bytes()) return false;
            if (!scalaSigBytes.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$AnnotationInfos$ScalaSigBytes$$$outer() {
            return this.$outer;
        }

        public ScalaSigBytes(SymbolTable $outer, byte[] bytes2) {
            this.bytes = bytes2;
            super($outer);
        }
    }

    public static abstract class AnnotationInfo
    implements Annotations.AnnotationApi {
        private boolean isTrivial;
        private Position rawpos;
        public final /* synthetic */ SymbolTable $outer;
        private volatile boolean bitmap$0;

        private boolean isTrivial$lzycompute() {
            AnnotationInfo annotationInfo = this;
            synchronized (annotationInfo) {
                if (!this.bitmap$0) {
                    this.isTrivial = this.atp().isTrivial() && !this.hasArgWhich((Function1<Trees.Tree, Object>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final boolean apply(Trees.Tree x$2) {
                            return x$2 instanceof Trees.This;
                        }
                    }));
                    this.bitmap$0 = true;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.isTrivial;
            }
        }

        @Override
        public Trees.TreeApi tree() {
            return Annotations$AnnotationApi$class.tree(this);
        }

        public abstract Types.Type atp();

        public abstract List<Trees.Tree> args();

        public abstract List<Tuple2<Names.Name, ClassfileAnnotArg>> assocs();

        @Override
        public Types.Type tpe() {
            return this.atp();
        }

        public List<Trees.Tree> scalaArgs() {
            return this.args();
        }

        public ListMap<Names.Name, ClassfileAnnotArg> javaArgs() {
            return (ListMap)ListMap$.MODULE$.apply(this.assocs());
        }

        public abstract Trees.Tree original();

        public abstract AnnotationInfo setOriginal(Trees.Tree var1);

        public boolean isTrivial() {
            return this.bitmap$0 ? this.isTrivial : this.isTrivial$lzycompute();
        }

        private Position rawpos() {
            return this.rawpos;
        }

        private void rawpos_$eq(Position x$1) {
            this.rawpos = x$1;
        }

        public Position pos() {
            return this.rawpos();
        }

        public AnnotationInfo setPos(Position pos) {
            this.rawpos_$eq(pos);
            return this;
        }

        public void completeInfo() {
        }

        public Symbols.Symbol symbol() {
            return this.atp().typeSymbol();
        }

        public List<AnnotationInfo> metaAnnotations() {
            List list2;
            Types.Type type = this.atp();
            if (type instanceof Types.AnnotatedType) {
                Types.AnnotatedType annotatedType = (Types.AnnotatedType)type;
                list2 = annotatedType.annotations();
            } else {
                list2 = Nil$.MODULE$;
            }
            return list2;
        }

        public List<Symbols.Symbol> defaultTargets() {
            return (List)((TraversableLike)this.symbol().annotations().map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final Symbols.Symbol apply(AnnotationInfo x$3) {
                    return x$3.symbol();
                }
            }, List$.MODULE$.canBuildFrom())).filter(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ AnnotationInfo $outer;

                public final boolean apply(Symbols.Symbol sym) {
                    return this.$outer.scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer().definitions().isMetaAnnotation(sym);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            });
        }

        public boolean matches(Symbols.Symbol clazz) {
            return !(this.symbol() instanceof Symbols.StubSymbol) && this.symbol().isNonBottomSubClass(clazz);
        }

        public boolean hasArgWhich(Function1<Trees.Tree, Object> p) {
            return this.args().exists((Function1<Trees.Tree, Object>)((Object)new Serializable(this, p){
                public static final long serialVersionUID = 0L;
                private final Function1 p$1;

                public final boolean apply(Trees.Tree x$4) {
                    return x$4.exists(this.p$1);
                }
                {
                    this.p$1 = p$1;
                }
            }));
        }

        public boolean isErroneous() {
            return this.atp().isErroneous() || this.args().exists((Function1<Trees.Tree, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Trees.Tree x$5) {
                    return x$5.isErroneous();
                }
            }));
        }

        public boolean isStatic() {
            return this.symbol().isNonBottomSubClass(this.scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer().definitions().StaticAnnotationClass());
        }

        public boolean refsSymbol(Symbols.Symbol sym) {
            return this.hasArgWhich((Function1<Trees.Tree, Object>)((Object)new Serializable(this, sym){
                public static final long serialVersionUID = 0L;
                private final Symbols.Symbol sym$1;

                public final boolean apply(Trees.Tree x$6) {
                    Symbols.Symbol symbol = x$6.symbol();
                    Symbols.Symbol symbol2 = this.sym$1;
                    return !(symbol != null ? !symbol.equals(symbol2) : symbol2 != null);
                }
                {
                    this.sym$1 = sym$1;
                }
            }));
        }

        public Option<String> stringArg(int index) {
            Option<Constants.Constant> option = this.constantAtIndex(index);
            return !option.isEmpty() ? new Some<String>(option.get().stringValue()) : None$.MODULE$;
        }

        public Option<Object> intArg(int index) {
            Option<Constants.Constant> option = this.constantAtIndex(index);
            return !option.isEmpty() ? new Some<Integer>(BoxesRunTime.boxToInteger(option.get().intValue())) : None$.MODULE$;
        }

        public Option<Names.TermName> symbolArg(int index) {
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ AnnotationInfo $outer;

                /*
                 * WARNING - void declaration
                 * Enabled aggressive block sorting
                 */
                public final <A1 extends Trees.Tree, B1> B1 applyOrElse(A1 x2, Function1<A1, B1> function1) {
                    void var7_9;
                    $colon$colon $colon$colon;
                    Trees.Apply apply2;
                    if (x2 instanceof Trees.Apply && (apply2 = (Trees.Apply)x2).args() instanceof $colon$colon && ($colon$colon = ($colon$colon)apply2.args()).head() instanceof Trees.Literal) {
                        Trees.Literal literal = (Trees.Literal)$colon$colon.head();
                        if (((Object)Nil$.MODULE$).equals($colon$colon.tl$1())) {
                            Symbols.Symbol symbol = apply2.fun().symbol();
                            Symbols.TermSymbol termSymbol = this.$outer.scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer().definitions().Symbol_apply();
                            if (!(symbol != null ? !symbol.equals(termSymbol) : termSymbol != null)) {
                                Names.TermName termName = this.$outer.scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer().newTermName(literal.value().stringValue());
                                return var7_9;
                            }
                        }
                    }
                    B1 B1 = function1.apply(x2);
                    return var7_9;
                }

                /*
                 * Enabled force condition propagation
                 * Lifted jumps to return sites
                 */
                public final boolean isDefinedAt(Trees.Tree x2) {
                    if (!(x2 instanceof Trees.Apply)) return false;
                    Trees.Apply apply2 = (Trees.Apply)x2;
                    if (!(apply2.args() instanceof $colon$colon)) return false;
                    $colon$colon $colon$colon = ($colon$colon)apply2.args();
                    if (!($colon$colon.head() instanceof Trees.Literal)) return false;
                    if (!((Object)Nil$.MODULE$).equals($colon$colon.tl$1())) return false;
                    Symbols.Symbol symbol = apply2.fun().symbol();
                    Symbols.TermSymbol termSymbol = this.$outer.scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer().definitions().Symbol_apply();
                    if (symbol != null) {
                        if (!symbol.equals(termSymbol)) return false;
                        return true;
                    }
                    if (termSymbol == null) return true;
                    return false;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
            Option<Trees.Tree> option = this.argAtIndex(index);
            return !option.isEmpty() ? serializable.lift().apply(option.get()) : None$.MODULE$;
        }

        public Option<Constants.Constant> constantAtIndex(int index) {
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final <A1 extends Trees.Tree, B1> B1 applyOrElse(A1 x3, Function1<A1, B1> function1) {
                    Object object;
                    if (x3 instanceof Trees.Literal) {
                        Trees.Literal literal = (Trees.Literal)x3;
                        object = literal.value();
                    } else {
                        object = function1.apply(x3);
                    }
                    return object;
                }

                public final boolean isDefinedAt(Trees.Tree x3) {
                    boolean bl = x3 instanceof Trees.Literal;
                    return bl;
                }
            };
            Option<Trees.Tree> option = this.argAtIndex(index);
            return !option.isEmpty() ? serializable.lift().apply(option.get()) : None$.MODULE$;
        }

        public Option<Trees.Tree> argAtIndex(int index) {
            return index < this.args().size() ? new Some<Trees.Tree>(this.args().apply(index)) : None$.MODULE$;
        }

        public int hashCode() {
            return ScalaRunTime$.MODULE$.hash(this.atp()) + ScalaRunTime$.MODULE$.hash(this.args()) + ScalaRunTime$.MODULE$.hash(this.assocs());
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean equals(Object other) {
            if (!(other instanceof AnnotationInfo)) return false;
            if (((AnnotationInfo)other).scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer() != this.scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer()) return false;
            AnnotationInfo annotationInfo = (AnnotationInfo)other;
            Types.Type type = this.atp();
            Types.Type type2 = annotationInfo.atp();
            if (type == null) {
                if (type2 != null) {
                    return false;
                }
            } else if (!type.equals(type2)) return false;
            List<Trees.Tree> list2 = this.args();
            List<Trees.Tree> list3 = annotationInfo.args();
            if (list2 == null) {
                if (list3 != null) {
                    return false;
                }
            } else if (!((Object)list2).equals(list3)) return false;
            List<Tuple2<Names.Name, ClassfileAnnotArg>> list4 = this.assocs();
            List<Tuple2<Names.Name, ClassfileAnnotArg>> list5 = annotationInfo.assocs();
            if (list4 == null) {
                if (list5 == null) return true;
                return false;
            } else {
                if (!((Object)list4).equals(list5)) return false;
                return true;
            }
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ Annotations scala$reflect$api$Annotations$AnnotationApi$$$outer() {
            return this.scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer();
        }

        public AnnotationInfo(SymbolTable $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Annotations$AnnotationApi$class.$init$(this);
            this.rawpos = $outer.NoPosition();
        }
    }

    public class NestedAnnotArg
    extends ClassfileAnnotArg
    implements Annotations.NestedArgumentApi,
    Serializable {
        private final AnnotationInfo annInfo;

        public AnnotationInfo annInfo() {
            return this.annInfo;
        }

        @Override
        public AnnotationInfo annotation() {
            return this.annInfo();
        }

        public String toString() {
            return this.annInfo().toString();
        }

        public NestedAnnotArg copy(AnnotationInfo annInfo) {
            return new NestedAnnotArg(this.scala$reflect$internal$AnnotationInfos$NestedAnnotArg$$$outer(), annInfo);
        }

        public AnnotationInfo copy$default$1() {
            return this.annInfo();
        }

        @Override
        public String productPrefix() {
            return "NestedAnnotArg";
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
            return this.annInfo();
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof NestedAnnotArg;
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
            if (!(x$1 instanceof NestedAnnotArg)) return false;
            if (((NestedAnnotArg)x$1).scala$reflect$internal$AnnotationInfos$NestedAnnotArg$$$outer() != this.scala$reflect$internal$AnnotationInfos$NestedAnnotArg$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            NestedAnnotArg nestedAnnotArg = (NestedAnnotArg)x$1;
            AnnotationInfo annotationInfo = this.annInfo();
            AnnotationInfo annotationInfo2 = nestedAnnotArg.annInfo();
            if (annotationInfo == null) {
                if (annotationInfo2 != null) {
                    return false;
                }
            } else if (!((Object)annotationInfo).equals(annotationInfo2)) return false;
            if (!nestedAnnotArg.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$AnnotationInfos$NestedAnnotArg$$$outer() {
            return this.$outer;
        }

        public NestedAnnotArg(SymbolTable $outer, AnnotationInfo annInfo) {
            this.annInfo = annInfo;
            super($outer);
            boolean bl = annInfo.args().isEmpty();
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(this.annInfo().args()).toString());
            }
        }
    }

    /*
     * Illegal identifiers - consider using --renameillegalidents true
     */
    public class LiteralAnnotArg
    extends ClassfileAnnotArg
    implements Annotations.LiteralArgumentApi,
    Serializable {
        private final Constants.Constant const;

        public Constants.Constant const() {
            return this.const;
        }

        @Override
        public Constants.Constant value() {
            return this.const();
        }

        public String toString() {
            return this.const().escapedStringValue();
        }

        public LiteralAnnotArg copy(Constants.Constant constant) {
            return new LiteralAnnotArg(this.scala$reflect$internal$AnnotationInfos$LiteralAnnotArg$$$outer(), constant);
        }

        public Constants.Constant copy$default$1() {
            return this.const();
        }

        @Override
        public String productPrefix() {
            return "LiteralAnnotArg";
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
            return this.const();
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof LiteralAnnotArg;
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
            if (!(x$1 instanceof LiteralAnnotArg)) return false;
            if (((LiteralAnnotArg)x$1).scala$reflect$internal$AnnotationInfos$LiteralAnnotArg$$$outer() != this.scala$reflect$internal$AnnotationInfos$LiteralAnnotArg$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            LiteralAnnotArg literalAnnotArg = (LiteralAnnotArg)x$1;
            Constants.Constant constant = this.const();
            Constants.Constant constant2 = literalAnnotArg.const();
            if (constant == null) {
                if (constant2 != null) {
                    return false;
                }
            } else if (!((Object)constant).equals(constant2)) return false;
            if (!literalAnnotArg.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$AnnotationInfos$LiteralAnnotArg$$$outer() {
            return this.$outer;
        }

        public LiteralAnnotArg(SymbolTable $outer, Constants.Constant constant) {
            this.const = constant;
            super($outer);
        }
    }

    public static abstract class ClassfileAnnotArg
    implements Product,
    Annotations.JavaArgumentApi {
        public final /* synthetic */ SymbolTable $outer;

        @Override
        public Iterator<Object> productIterator() {
            return Product$class.productIterator(this);
        }

        @Override
        public String productPrefix() {
            return Product$class.productPrefix(this);
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$AnnotationInfos$ClassfileAnnotArg$$$outer() {
            return this.$outer;
        }

        public ClassfileAnnotArg(SymbolTable $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Product$class.$init$(this);
        }
    }

    public final class LazyAnnotationInfo
    extends AnnotationInfo {
        private final Function0<AnnotationInfo> lazyInfo;
        private boolean forced;
        private AnnotationInfo forcedInfo;
        private volatile boolean bitmap$0;

        /*
         * Loose catch block
         */
        private AnnotationInfo forcedInfo$lzycompute() {
            LazyAnnotationInfo lazyAnnotationInfo = this;
            synchronized (lazyAnnotationInfo) {
                Throwable throwable;
                if (!this.bitmap$0) {
                    AnnotationInfo annotationInfo = this.lazyInfo.apply();
                    this.forced_$eq(true);
                    this.forcedInfo = annotationInfo;
                    this.bitmap$0 = true;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                this.lazyInfo = null;
                return this.forcedInfo;
                catch (Throwable throwable2) {
                    this.forced_$eq(true);
                    throwable = throwable2;
                }
                throw throwable;
            }
        }

        private boolean forced() {
            return this.forced;
        }

        private void forced_$eq(boolean x$1) {
            this.forced = x$1;
        }

        private AnnotationInfo forcedInfo() {
            return this.bitmap$0 ? this.forcedInfo : this.forcedInfo$lzycompute();
        }

        @Override
        public Types.Type atp() {
            return this.forcedInfo().atp();
        }

        @Override
        public List<Trees.Tree> args() {
            return this.forcedInfo().args();
        }

        @Override
        public List<Tuple2<Names.Name, ClassfileAnnotArg>> assocs() {
            return this.forcedInfo().assocs();
        }

        @Override
        public Trees.Tree original() {
            return this.forcedInfo().original();
        }

        @Override
        public LazyAnnotationInfo setOriginal(Trees.Tree t) {
            this.forcedInfo().setOriginal(t);
            return this;
        }

        public String toString() {
            return this.forced() ? this.forcedInfo().toString() : "@<?>";
        }

        @Override
        public Position pos() {
            return this.forced() ? this.forcedInfo().pos() : this.scala$reflect$internal$AnnotationInfos$LazyAnnotationInfo$$$outer().NoPosition();
        }

        @Override
        public void completeInfo() {
            this.forcedInfo();
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$AnnotationInfos$LazyAnnotationInfo$$$outer() {
            return this.$outer;
        }

        public LazyAnnotationInfo(SymbolTable $outer, Function0<AnnotationInfo> lazyInfo) {
            this.lazyInfo = lazyInfo;
            super($outer);
            this.forced = false;
        }
    }

    public class ErroneousAnnotation
    extends CompleteAnnotationInfo {
        public /* synthetic */ SymbolTable scala$reflect$internal$AnnotationInfos$ErroneousAnnotation$$$outer() {
            return this.$outer;
        }

        public ErroneousAnnotation(SymbolTable $outer) {
            super($outer, $outer.ErrorType(), Nil$.MODULE$, Nil$.MODULE$);
        }
    }

    public class CompleteAnnotationInfo
    extends AnnotationInfo {
        private final Types.Type atp;
        private final List<Trees.Tree> args;
        private final List<Tuple2<Names.Name, ClassfileAnnotArg>> assocs;
        private Trees.Tree orig;

        @Override
        public Types.Type atp() {
            return this.atp;
        }

        @Override
        public List<Trees.Tree> args() {
            return this.args;
        }

        @Override
        public List<Tuple2<Names.Name, ClassfileAnnotArg>> assocs() {
            return this.assocs;
        }

        private Trees.Tree orig() {
            return this.orig;
        }

        private void orig_$eq(Trees.Tree x$1) {
            this.orig = x$1;
        }

        @Override
        public Trees.Tree original() {
            return this.orig();
        }

        @Override
        public CompleteAnnotationInfo setOriginal(Trees.Tree t) {
            this.orig_$eq(t);
            this.setPos(t.pos());
            return this;
        }

        public String toString() {
            return this.scala$reflect$internal$AnnotationInfos$CompleteAnnotationInfo$$$outer().completeAnnotationToString(this);
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$AnnotationInfos$CompleteAnnotationInfo$$$outer() {
            return this.$outer;
        }

        public CompleteAnnotationInfo(SymbolTable $outer, Types.Type atp, List<Trees.Tree> args, List<Tuple2<Names.Name, ClassfileAnnotArg>> assocs2) {
            this.atp = atp;
            this.args = args;
            this.assocs = assocs2;
            super($outer);
            boolean bl = args.isEmpty() || assocs2.isEmpty();
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(this.atp()).toString());
            }
            this.orig = $outer.EmptyTree();
        }
    }
}

