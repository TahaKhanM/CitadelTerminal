/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;
import scala.util.Either$;
import scala.util.Either$MergeableEither$;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(bytes="\u0006\u0001\u00115f!B\u0001\u0003\u0003C9!AB#ji\",'O\u0003\u0002\u0004\t\u0005!Q\u000f^5m\u0015\u0005)\u0011!B:dC2\f7\u0001A\u000b\u0004\u0011Qq2C\u0001\u0001\n!\tQ1\"D\u0001\u0005\u0013\taAA\u0001\u0004B]f\u0014VM\u001a\u0005\u0006\u001d\u0001!\taD\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003A\u0001B!\u0005\u0001\u0013;5\t!\u0001\u0005\u0002\u0014)1\u0001AAB\u000b\u0001\t\u000b\u0007aCA\u0001B#\t9\"\u0004\u0005\u0002\u000b1%\u0011\u0011\u0004\u0002\u0002\b\u001d>$\b.\u001b8h!\tQ1$\u0003\u0002\u001d\t\t\u0019\u0011I\\=\u0011\u0005MqBAB\u0010\u0001\t\u000b\u0007aCA\u0001C\u0011\u0015\t\u0003\u0001\"\u0001#\u0003\u0011aWM\u001a;\u0016\u0003\r\u0002B\u0001J.\u0013;9\u0011\u0011#J\u0004\u0006M\tA\taJ\u0001\u0007\u000b&$\b.\u001a:\u0011\u0005EAc!B\u0001\u0003\u0011\u0003I3C\u0001\u0015\n\u0011\u0015q\u0001\u0006\"\u0001,)\u00059c\u0001B\u0017)\u00079\u0012q\"T3sO\u0016\f'\r\\3FSRDWM]\u000b\u0003_a\u001a\"\u0001\f\u0019\u0011\u0005)\t\u0014B\u0001\u001a\u0005\u0005\u0019\te.\u001f,bY\"aA\u0007\fC\u0001\u0002\u000b\u0015)\u0019!C\u0005k\u0005!3oY1mC\u0012*H/\u001b7%\u000b&$\b.\u001a:%\u001b\u0016\u0014x-Z1cY\u0016,\u0015\u000e\u001e5fe\u0012\"\u00030F\u00017!\u0011\t\u0002aN\u001c\u0011\u0005MAD!B\u000b-\u0005\u00041\u0002\"\u0003\u001e-\u0005\u000b\u0005\t\u0015!\u00037\u0003\u0015\u001a8-\u00197bIU$\u0018\u000e\u001c\u0013FSRDWM\u001d\u0013NKJ<W-\u00192mK\u0016KG\u000f[3sI\u0011B\b\u0005C\u0003\u000fY\u0011\u0005A\b\u0006\u0002>\u007fA\u0019a\bL\u001c\u000e\u0003!BQ\u0001Q\u001eA\u0002Y\n\u0011\u0001\u001f\u0005\u0006\u00052\"\taQ\u0001\u0006[\u0016\u0014x-Z\u000b\u0002o!9Q\tLA\u0001\n\u00032\u0015\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\u001d\u0003\"A\u0003%\n\u0005%#!aA%oi\"91\nLA\u0001\n\u0003b\u0015AB3rk\u0006d7\u000f\u0006\u0002N!B\u0011!BT\u0005\u0003\u001f\u0012\u0011qAQ8pY\u0016\fg\u000eC\u0004R\u0015\u0006\u0005\t\u0019\u0001\u000e\u0002\u0007a$\u0013\u0007C\u0004TQ\u0005\u0005I1\u0001+\u0002\u001f5+'oZ3bE2,W)\u001b;iKJ,\"!\u0016-\u0015\u0005YK\u0006c\u0001 -/B\u00111\u0003\u0017\u0003\u0006+I\u0013\rA\u0006\u0005\u0006\u0001J\u0003\rA\u0017\t\u0005#\u00019vK\u0002\u0003]Q\tk&A\u0004'fMR\u0004&o\u001c6fGRLwN\\\u000b\u0004=*d7\u0003B.\n?\n\u0004\"A\u00031\n\u0005\u0005$!a\u0002)s_\u0012,8\r\u001e\t\u0003\u0015\rL!\u0001\u001a\u0003\u0003\u0019M+'/[1mSj\f'\r\\3\t\u0011\u0019\\&Q3A\u0005\u0002\u001d\f\u0011!Z\u000b\u0002QB!\u0011\u0003A5l!\t\u0019\"\u000e\u0002\u0004\u00167\u0012\u0015\rA\u0006\t\u0003'1$aaH.\u0005\u0006\u00041\u0002\u0002\u00038\\\u0005#\u0005\u000b\u0011\u00025\u0002\u0005\u0015\u0004\u0003\"\u0002\b\\\t\u0003\u0001HCA9s!\u0011q4,[6\t\u000b\u0019|\u0007\u0019\u00015\t\u000bQ\\F\u0011A;\u0002\u0007\u001d,G/F\u0001j\u0011\u001598\f\"\u0001y\u0003\u001d1wN]3bG\",2!_A\u0001)\tQ\"\u0010C\u0003|m\u0002\u0007A0A\u0001g!\u0011QQ0[@\n\u0005y$!!\u0003$v]\u000e$\u0018n\u001c82!\r\u0019\u0012\u0011\u0001\u0003\u0007\u0003\u00071(\u0019\u0001\f\u0003\u0003UCq!a\u0002\\\t\u0003\tI!A\u0005hKR|%/\u00127tKV!\u00111BA\b)\u0011\ti!!\u0006\u0011\u0007M\ty\u0001\u0002\u0005\u0002\u0012\u0005\u0015!\u0019AA\n\u0005\t\t\u0015)\u0005\u0002j5!I\u0011qCA\u0003\t\u0003\u0007\u0011\u0011D\u0001\u0003_J\u0004RACA\u000e\u0003\u001bI1!!\b\u0005\u0005!a$-\u001f8b[\u0016t\u0004bBA\u00117\u0012\u0005\u00111E\u0001\u0007M>\u0014\u0018\r\u001c7\u0015\u00075\u000b)\u0003\u0003\u0005\u0002(\u0005}\u0001\u0019AA\u0015\u0003\u0005\u0001\b\u0003\u0002\u0006~S6Cc!!\n\u0002.\u0005M\u0002c\u0001\u0006\u00020%\u0019\u0011\u0011\u0007\u0003\u0003\u001d\u0011,\u0007O]3dCR,GMT1nKF:q$!\u000e\u0002<\u0005-\u0004c\u0001\u0006\u00028%\u0019\u0011\u0011\b\u0003\u0003\rMKXNY8mc%\u0019\u0013QHA\"\u00033\n)\u0005\u0006\u0003\u00026\u0005}\u0002bBA!\r\u0001\u0007\u00111J\u0001\u0005]\u0006lW-\u0003\u0003\u0002F\u0005\u001d\u0013!B1qa2L(bAA%\t\u000511+_7c_2\u0004B!!\u0014\u0002T9\u0019!\"a\u0014\n\u0007\u0005EC!\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003+\n9F\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003#\"\u0011'C\u0012\u0002\\\u0005\u001d\u0014\u0011NA%\u001d\u0011\ti&a\u001a\u000f\t\u0005}\u0013QM\u0007\u0003\u0003CR1!a\u0019\u0007\u0003\u0019a$o\\8u}%\tQ!C\u0002\u0002J\u0011\td\u0001JA/\u0003K*\u0011'B\u0013\u0002n\u0005=tBAA8C\u0005Y\bbBA:7\u0012\u0005\u0011QO\u0001\u0007KbL7\u000f^:\u0015\u00075\u000b9\b\u0003\u0005\u0002(\u0005E\u0004\u0019AA\u0015Q\u0019\t9(!\f\u0002|E:q$!\u000e\u0002~\u0005\r\u0015'C\u0012\u0002>\u0005\r\u0013qPA#c%\u0019\u00131LA4\u0003\u0003\u000bI%\r\u0004%\u0003;\n)'B\u0019\u0006K\u00055\u0014q\u000e\u0005\b\u0003\u000f[F\u0011AAE\u0003\u001d1G.\u0019;NCB,b!a#\u0002\u0018\u0006EE\u0003BAG\u0003;\u0003b!\u0005\u0001\u0002\u0010\u0006U\u0005cA\n\u0002\u0012\u00129\u00111SAC\u0005\u00041\"!\u0001-\u0011\u0007M\t9\n\u0002\u0005\u0002\u001a\u0006\u0015%\u0019AAN\u0005\t\u0011%)\u0005\u0002l5!910!\"A\u0002\u0005}\u0005#\u0002\u0006~S\u00065\u0005bBAR7\u0012\u0005\u0011QU\u0001\u0004[\u0006\u0004X\u0003BAT\u0003g#B!!+\u00026J1\u00111V0c\u0003_3a!!,\u0001\u0001\u0005%&\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004#B\t\u0001\u0003c[\u0007cA\n\u00024\u00129\u00111SAQ\u0005\u00041\u0002bB>\u0002\"\u0002\u0007\u0011q\u0017\t\u0006\u0015uL\u0017\u0011\u0017\u0005\b\u0003w[F\u0011AA_\u0003\u00191\u0017\u000e\u001c;feV!\u0011qXAf)\u0011\t\t-a4\u0011\u000b)\t\u0019-a2\n\u0007\u0005\u0015GA\u0001\u0004PaRLwN\u001c\t\u0006#\u0001I\u0017\u0011\u001a\t\u0004'\u0005-GaBAg\u0003s\u0013\rA\u0006\u0002\u00023\"A\u0011qEA]\u0001\u0004\tI\u0003C\u0004\u0002Tn#\t!!6\u0002\u000bQ|7+Z9\u0016\u0005\u0005]\u0007#BAm\u0003?LWBAAn\u0015\r\ti\u000eB\u0001\u000bG>dG.Z2uS>t\u0017\u0002BAq\u00037\u00141aU3r\u0011\u001d\t)o\u0017C\u0001\u0003O\f\u0001\u0002^8PaRLwN\\\u000b\u0003\u0003S\u0004BACAbS\"I\u0011Q^.\u0002\u0002\u0013\u0005\u0011q^\u0001\u0005G>\u0004\u00180\u0006\u0004\u0002r\u0006]\u00181 \u000b\u0005\u0003g\fi\u0010\u0005\u0004?7\u0006U\u0018\u0011 \t\u0004'\u0005]HAB\u000b\u0002l\n\u0007a\u0003E\u0002\u0014\u0003w$aaHAv\u0005\u00041\u0002\"\u00034\u0002lB\u0005\t\u0019AA\u0000!\u0019\t\u0002!!>\u0002z\"I!1A.\u0012\u0002\u0013\u0005!QA\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0019\u00119A!\b\u0003 U\u0011!\u0011\u0002\u0016\u0004Q\n-1F\u0001B\u0007!\u0011\u0011yA!\u0007\u000e\u0005\tE!\u0002\u0002B\n\u0005+\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\t]A!\u0001\u0006b]:|G/\u0019;j_:LAAa\u0007\u0003\u0012\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\rU\u0011\tA1\u0001\u0017\t\u0019y\"\u0011\u0001b\u0001-!I!1E.\u0002\u0002\u0013\u0005#QE\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\t\u001d\u0002\u0003\u0002B\u0015\u0005gi!Aa\u000b\u000b\t\t5\"qF\u0001\u0005Y\u0006twM\u0003\u0002\u00032\u0005!!.\u0019<b\u0013\u0011\t)Fa\u000b\t\u0013\t]2,!A\u0005\u0002\te\u0012\u0001\u00049s_\u0012,8\r^!sSRLX#A$\t\u0013\tu2,!A\u0005\u0002\t}\u0012A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u00045\t\u0005\u0003\u0002C)\u0003<\u0005\u0005\t\u0019A$\t\u0013\t\u00153,!A\u0005B\t\u001d\u0013a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\t%\u0003#BAm\u0005\u0017R\u0012\u0002\u0002B'\u00037\u0014\u0001\"\u0013;fe\u0006$xN\u001d\u0005\n\u0005#Z\u0016\u0011!C\u0001\u0005'\n\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004\u001b\nU\u0003\u0002C)\u0003P\u0005\u0005\t\u0019\u0001\u000e\t\u000f\u0015[\u0016\u0011!C!\r\"I!1L.\u0002\u0002\u0013\u0005#QL\u0001\ti>\u001cFO]5oOR\u0011!q\u0005\u0005\t\u0017n\u000b\t\u0011\"\u0011\u0003bQ\u0019QJa\u0019\t\u0011E\u0013y&!AA\u0002i9\u0011Ba\u001a)\u0003\u0003E\tA!\u001b\u0002\u001d1+g\r\u001e)s_*,7\r^5p]B\u0019aHa\u001b\u0007\u0011qC\u0013\u0011!E\u0001\u0005[\u001aBAa\u001b\nE\"9aBa\u001b\u0005\u0002\tEDC\u0001B5\u0011)\u0011YFa\u001b\u0002\u0002\u0013\u0015#Q\f\u0005\u000b\u0003\u000b\u0012Y'!A\u0005\u0002\n]TC\u0002B=\u0005\u007f\u0012\u0019\t\u0006\u0003\u0003|\t\u0015\u0005C\u0002 \\\u0005{\u0012\t\tE\u0002\u0014\u0005\u007f\"a!\u0006B;\u0005\u00041\u0002cA\n\u0003\u0004\u00121qD!\u001eC\u0002YAqA\u001aB;\u0001\u0004\u00119\t\u0005\u0004\u0012\u0001\tu$\u0011\u0011\u0005\u000b\u0005\u0017\u0013Y'!A\u0005\u0002\n5\u0015aB;oCB\u0004H._\u000b\u0007\u0005\u001f\u00139Ja'\u0015\t\tE%Q\u0014\t\u0006\u0015\u0005\r'1\u0013\t\u0007#\u0001\u0011)J!'\u0011\u0007M\u00119\n\u0002\u0004\u0016\u0005\u0013\u0013\rA\u0006\t\u0004'\tmEAB\u0010\u0003\n\n\u0007a\u0003\u0003\u0006\u0003 \n%\u0015\u0011!a\u0001\u0005C\u000b1\u0001\u001f\u00131!\u0019q4L!&\u0003\u001a\"Q!Q\u0015B6\u0003\u0003%IAa*\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0003\u0005S\u0003BA!\u000b\u0003,&!!Q\u0016B\u0016\u0005\u0019y%M[3di\u001a1!\u0011\u0017\u0015C\u0005g\u0013qBU5hQR\u0004&o\u001c6fGRLwN\\\u000b\u0007\u0005k\u0013yLa1\u0014\u000b\t=\u0016b\u00182\t\u0015\u0019\u0014yK!f\u0001\n\u0003\u0011I,\u0006\u0002\u0003<B1\u0011\u0003\u0001B_\u0005\u0003\u00042a\u0005B`\t\u001d)\"q\u0016CC\u0002Y\u00012a\u0005Bb\t\u001dy\"q\u0016CC\u0002YA!B\u001cBX\u0005#\u0005\u000b\u0011\u0002B^\u0011\u001dq!q\u0016C\u0001\u0005\u0013$BAa3\u0003NB9aHa,\u0003>\n\u0005\u0007b\u00024\u0003H\u0002\u0007!1\u0018\u0005\bi\n=F\u0011\u0001Bi+\t\u0011\t\rC\u0004x\u0005_#\tA!6\u0016\t\t]'q\u001c\u000b\u00045\te\u0007bB>\u0003T\u0002\u0007!1\u001c\t\u0007\u0015u\u0014\tM!8\u0011\u0007M\u0011y\u000eB\u0004\u0002\u0004\tM'\u0019\u0001\f\t\u0011\u0005\u001d!q\u0016C\u0001\u0005G,BA!:\u0003jR!!q\u001dBw!\r\u0019\"\u0011\u001e\u0003\t\u00033\u0013\tO1\u0001\u0003lF\u0019!\u0011\u0019\u000e\t\u0013\u0005]!\u0011\u001dCA\u0002\t=\b#\u0002\u0006\u0002\u001c\t\u001d\b\u0002CA\u0011\u0005_#\tAa=\u0015\u00075\u0013)\u0010C\u0004|\u0005c\u0004\rAa>\u0011\u000b)i(\u0011Y'\t\u0011\u0005M$q\u0016C\u0001\u0005w$2!\u0014B\u007f\u0011!\t9C!?A\u0002\t]\bF\u0002B\u007f\u0003[\u0019\t!M\u0004 \u0003k\u0019\u0019a!\u00032\u0013\r\ni$a\u0011\u0004\u0006\u0005\u0015\u0013'C\u0012\u0002\\\u0005\u001d4qAA%c\u0019!\u0013QLA3\u000bE*Q%!\u001c\u0002p!A\u0011q\u0011BX\t\u0003\u0019i!\u0006\u0004\u0004\u0010\rU11\u0004\u000b\u0005\u0007#\u0019i\u0002\u0005\u0004\u0012\u0001\rM1\u0011\u0004\t\u0004'\rUA\u0001CA\t\u0007\u0017\u0011\raa\u0006\u0012\u0007\tu&\u0004E\u0002\u0014\u00077!q!!4\u0004\f\t\u0007a\u0003C\u0004|\u0007\u0017\u0001\raa\b\u0011\r)i(\u0011YB\t\u0011!\t\u0019Ka,\u0005\u0002\r\rR\u0003BB\u0013\u0007_!Baa\n\u00042I11\u0011F0c\u0007W1a!!,\u0001\u0001\r\u001d\u0002CB\t\u0001\u0005{\u001bi\u0003E\u0002\u0014\u0007_!q!!4\u0004\"\t\u0007a\u0003C\u0004|\u0007C\u0001\raa\r\u0011\r)i(\u0011YB\u0017\u0011!\tYLa,\u0005\u0002\r]R\u0003BB\u001d\u0007\u0003\"Baa\u000f\u0004DA)!\"a1\u0004>A1\u0011\u0003AB \u0005\u0003\u00042aEB!\t\u001d\t\u0019j!\u000eC\u0002YA\u0001\"a\n\u00046\u0001\u0007!q\u001f\u0005\t\u0003'\u0014y\u000b\"\u0001\u0004HU\u00111\u0011\n\t\u0007\u00033\fyN!1\t\u0011\u0005\u0015(q\u0016C\u0001\u0007\u001b*\"aa\u0014\u0011\u000b)\t\u0019M!1\t\u0015\u00055(qVA\u0001\n\u0003\u0019\u0019&\u0006\u0004\u0004V\rm3q\f\u000b\u0005\u0007/\u001a\t\u0007E\u0004?\u0005_\u001bIf!\u0018\u0011\u0007M\u0019Y\u0006\u0002\u0004\u0016\u0007#\u0012\rA\u0006\t\u0004'\r}CAB\u0010\u0004R\t\u0007a\u0003C\u0005g\u0007#\u0002\n\u00111\u0001\u0004dA1\u0011\u0003AB-\u0007;B!Ba\u0001\u00030F\u0005I\u0011AB4+\u0019\u0019Ig!\u001c\u0004pU\u001111\u000e\u0016\u0005\u0005w\u0013Y\u0001\u0002\u0004\u0016\u0007K\u0012\rA\u0006\u0003\u0007?\r\u0015$\u0019\u0001\f\t\u0015\t\r\"qVA\u0001\n\u0003\u0012)\u0003\u0003\u0006\u00038\t=\u0016\u0011!C\u0001\u0005sA!B!\u0010\u00030\u0006\u0005I\u0011AB<)\rQ2\u0011\u0010\u0005\t#\u000eU\u0014\u0011!a\u0001\u000f\"Q!Q\tBX\u0003\u0003%\tEa\u0012\t\u0015\tE#qVA\u0001\n\u0003\u0019y\bF\u0002N\u0007\u0003C\u0001\"UB?\u0003\u0003\u0005\rA\u0007\u0005\t\u000b\n=\u0016\u0011!C!\r\"Q!1\fBX\u0003\u0003%\tE!\u0018\t\u0013-\u0013y+!A\u0005B\r%EcA'\u0004\f\"A\u0011ka\"\u0002\u0002\u0003\u0007!dB\u0005\u0004\u0010\"\n\t\u0011#\u0001\u0004\u0012\u0006y!+[4iiB\u0013xN[3di&|g\u000eE\u0002?\u0007'3\u0011B!-)\u0003\u0003E\ta!&\u0014\t\rM\u0015B\u0019\u0005\b\u001d\rME\u0011ABM)\t\u0019\t\n\u0003\u0006\u0003\\\rM\u0015\u0011!C#\u0005;B!\"!\u0012\u0004\u0014\u0006\u0005I\u0011QBP+\u0019\u0019\tka*\u0004,R!11UBW!\u001dq$qVBS\u0007S\u00032aEBT\t\u0019)2Q\u0014b\u0001-A\u00191ca+\u0005\r}\u0019iJ1\u0001\u0017\u0011\u001d17Q\u0014a\u0001\u0007_\u0003b!\u0005\u0001\u0004&\u000e%\u0006B\u0003BF\u0007'\u000b\t\u0011\"!\u00044V11QWB_\u0007\u0003$Baa.\u0004DB)!\"a1\u0004:B1\u0011\u0003AB^\u0007\u007f\u00032aEB_\t\u0019)2\u0011\u0017b\u0001-A\u00191c!1\u0005\r}\u0019\tL1\u0001\u0017\u0011)\u0011yj!-\u0002\u0002\u0003\u00071Q\u0019\t\b}\t=61XB`\u0011)\u0011)ka%\u0002\u0002\u0013%!q\u0015\u0005\b\u0007\u0017DC\u0011ABg\u0003\u0011\u0019wN\u001c3\u0016\r\r=7Q[Bm)!\u0019\tna7\u0004`\u000e\u0015\bCB\t\u0001\u0007'\u001c9\u000eE\u0002\u0014\u0007+$a!FBe\u0005\u00041\u0002cA\n\u0004Z\u00121qd!3C\u0002YAqa!8\u0004J\u0002\u0007Q*\u0001\u0003uKN$\b\"CBq\u0007\u0013$\t\u0019ABr\u0003\u0015\u0011\u0018n\u001a5u!\u0015Q\u00111DBl\u0011!\t3\u0011\u001aCA\u0002\r\u001d\b#\u0002\u0006\u0002\u001c\rMw\u0001C*)\u0003\u0003E\taa;\u0011\u0007y\u001aiO\u0002\u0005.Q\u0005\u0005\t\u0012ABx'\r\u0019i/\u0003\u0005\b\u001d\r5H\u0011ABz)\t\u0019Y\u000f\u0003\u0005\u0004x\u000e5HQAB}\u0003=iWM]4fI\u0015DH/\u001a8tS>tW\u0003BB~\u0007\u007f$Ba!@\u0005\u0002A\u00191ca@\u0005\rU\u0019)P1\u0001\u0017\u0011!!\u0019a!>A\u0002\u0011\u0015\u0011!\u0002\u0013uQ&\u001c\b\u0003\u0002 -\u0007{D!\u0002\"\u0003\u0004n\u0006\u0005IQ\u0001C\u0006\u0003IA\u0017m\u001d5D_\u0012,G%\u001a=uK:\u001c\u0018n\u001c8\u0016\t\u00115AQ\u0003\u000b\u0004\r\u0012=\u0001\u0002\u0003C\u0002\t\u000f\u0001\r\u0001\"\u0005\u0011\tybC1\u0003\t\u0004'\u0011UAAB\u000b\u0005\b\t\u0007a\u0003\u0003\u0006\u0005\u001a\r5\u0018\u0011!C\u0003\t7\t\u0001#Z9vC2\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0016\t\u0011uA\u0011\u0006\u000b\u0005\t?!\u0019\u0003F\u0002N\tCA\u0001\"\u0015C\f\u0003\u0003\u0005\rA\u0007\u0005\t\t\u0007!9\u00021\u0001\u0005&A!a\b\fC\u0014!\r\u0019B\u0011\u0006\u0003\u0007+\u0011]!\u0019\u0001\f\t\u000f\r\u0005\b\u0001\"\u0001\u0005.U\u0011Aq\u0006\t\u0006I\t=&#\b\u0005\b\tg\u0001A\u0011\u0001C\u001b\u0003\u00111w\u000e\u001c3\u0016\t\u0011]B1\b\u000b\u0007\ts!i\u0004b\u0011\u0011\u0007M!Y\u0004B\u0004\u0002\u0014\u0012E\"\u0019\u0001\f\t\u0011\u0011}B\u0011\u0007a\u0001\t\u0003\n!AZ1\u0011\u000b)i(\u0003\"\u000f\t\u0011\u0011\u0015C\u0011\u0007a\u0001\t\u000f\n!A\u001a2\u0011\u000b)iX\u0004\"\u000f\t\u000f\u0011-\u0003\u0001\"\u0001\u0005N\u0005!1o^1q+\t!yE\u0005\u0004\u0005R}\u0013G1\u000b\u0004\u0007\u0003[\u0003\u0001\u0001b\u0014\u0011\tE\u0001QD\u0005\u0005\b\t/\u0002A\u0011\u0001C-\u0003%Qw.\u001b8SS\u001eDG/\u0006\u0005\u0005\\\u0011\u0005D\u0011\u0010C5)\u0011!i\u0006\"\u001c\u0011\rE\u0001Aq\fC4!\r\u0019B\u0011\r\u0003\t\tG\")F1\u0001\u0005f\t\u0011\u0011)M\t\u0003%i\u00012a\u0005C5\t\u001d!Y\u0007\"\u0016C\u0002Y\u0011\u0011a\u0011\u0005\t\t_\")\u0006q\u0001\u0005r\u0005\u0011QM\u001e\t\t\u0003\u001b\"\u0019\bb\u001e\u0005^%!AQOA,\u0005A!C.Z:tI\r|Gn\u001c8%Y\u0016\u001c8\u000fE\u0002\u0014\ts\"\u0001\u0002b\u001f\u0005V\t\u0007AQ\u0010\u0002\u0003\u0005F\n\"!\b\u000e\t\u000f\u0011\u0005\u0005\u0001\"\u0001\u0005\u0004\u0006A!n\\5o\u0019\u00164G/\u0006\u0005\u0005\u0006\u0012]Eq\u0012CF)\u0011!9\t\"%\u0011\rE\u0001A\u0011\u0012CG!\r\u0019B1\u0012\u0003\b\tW\"yH1\u0001\u0017!\r\u0019Bq\u0012\u0003\t\tw\"yH1\u0001\u0005~!AAq\u000eC@\u0001\b!\u0019\n\u0005\u0005\u0002N\u0011MDQ\u0013CD!\r\u0019Bq\u0013\u0003\t\tG\"yH1\u0001\u0005f!9A1\u0014\u0001\u0007\u0002\u0011u\u0015AB5t\u0019\u00164G/F\u0001N\u0011\u001d!\t\u000b\u0001D\u0001\t;\u000bq![:SS\u001eDG/K\u0003\u0001\tK#I+C\u0002\u0005(\n\u0011A\u0001T3gi&\u0019A1\u0016\u0002\u0003\u000bIKw\r\u001b;")
public abstract class Either<A, B> {
    public static <A, B> Either<A, B> cond(boolean bl, Function0<B> function0, Function0<A> function02) {
        return Either$.MODULE$.cond(bl, function0, function02);
    }

    public static Either MergeableEither(Either either2) {
        return Either$.MODULE$.MergeableEither(either2);
    }

    public LeftProjection<A, B> left() {
        return new LeftProjection(this);
    }

    public RightProjection<A, B> right() {
        return new RightProjection(this);
    }

    public <X> X fold(Function1<A, X> fa, Function1<B, X> fb) {
        block4: {
            X x;
            block3: {
                block2: {
                    if (!(this instanceof Left)) break block2;
                    Left left = (Left)this;
                    x = fa.apply(left.a());
                    break block3;
                }
                if (!(this instanceof Right)) break block4;
                Right right = (Right)this;
                x = fb.apply(right.b());
            }
            return x;
        }
        throw new MatchError(this);
    }

    public Product swap() {
        block4: {
            Either either2;
            block3: {
                block2: {
                    if (!(this instanceof Left)) break block2;
                    Left left = (Left)this;
                    either2 = new Right(left.a());
                    break block3;
                }
                if (!(this instanceof Right)) break block4;
                Right right = (Right)this;
                either2 = new Left(right.b());
            }
            return either2;
        }
        throw new MatchError(this);
    }

    public <A1, B1, C> Either<A1, C> joinRight(Predef$.less.colon.less<B1, Either<A1, C>> ev) {
        block4: {
            Either either2;
            block3: {
                block2: {
                    if (!(this instanceof Left)) break block2;
                    Left left = (Left)this;
                    either2 = new Left(left.a());
                    break block3;
                }
                if (!(this instanceof Right)) break block4;
                Right right = (Right)this;
                either2 = (Either)ev.apply(right.b());
            }
            return either2;
        }
        throw new MatchError(this);
    }

    public <A1, B1, C> Either<C, B1> joinLeft(Predef$.less.colon.less<A1, Either<C, B1>> ev) {
        block4: {
            Right right;
            block3: {
                block2: {
                    if (!(this instanceof Left)) break block2;
                    Left left = (Left)this;
                    right = (Right)ev.apply(left.a());
                    break block3;
                }
                if (!(this instanceof Right)) break block4;
                Right right2 = (Right)this;
                right = new Right(right2.b());
            }
            return right;
        }
        throw new MatchError(this);
    }

    public abstract boolean isLeft();

    public abstract boolean isRight();

    public static final class LeftProjection<A, B>
    implements Product,
    Serializable {
        private final Either<A, B> e;

        public Either<A, B> e() {
            return this.e;
        }

        public A get() {
            Either<A, B> either2 = this.e();
            if (either2 instanceof Left) {
                Left left = (Left)either2;
                return left.a();
            }
            if (either2 instanceof Right) {
                throw new NoSuchElementException("Either.left.value on Right");
            }
            throw new MatchError(either2);
        }

        public <U> Object foreach(Function1<A, U> f) {
            Either<A, B> either2;
            block4: {
                BoxedUnit boxedUnit;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        Left left = (Left)either2;
                        boxedUnit = f.apply(left.a());
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    boxedUnit = BoxedUnit.UNIT;
                }
                return boxedUnit;
            }
            throw new MatchError(either2);
        }

        public <AA> AA getOrElse(Function0<AA> or) {
            Either<A, B> either2;
            block4: {
                Object object;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        Left left = (Left)either2;
                        object = left.a();
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    object = or.apply();
                }
                return (AA)object;
            }
            throw new MatchError(either2);
        }

        public boolean forall(Function1<A, Object> p) {
            Either<A, B> either2;
            block4: {
                boolean bl;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        Left left = (Left)either2;
                        bl = BoxesRunTime.unboxToBoolean(p.apply(left.a()));
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    bl = true;
                }
                return bl;
            }
            throw new MatchError(either2);
        }

        public boolean exists(Function1<A, Object> p) {
            Either<A, B> either2;
            block4: {
                boolean bl;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        Left left = (Left)either2;
                        bl = BoxesRunTime.unboxToBoolean(p.apply(left.a()));
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    bl = false;
                }
                return bl;
            }
            throw new MatchError(either2);
        }

        public <BB, X> Either<X, BB> flatMap(Function1<A, Either<X, BB>> f) {
            Either<A, B> either2;
            block4: {
                Either<X, BB> either3;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        Left left = (Left)either2;
                        either3 = f.apply(left.a());
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    Right right = (Right)either2;
                    either3 = new Right<X, BB>(right.b());
                }
                return either3;
            }
            throw new MatchError(either2);
        }

        public <X> Product map(Function1<A, X> f) {
            Either<A, B> either2;
            block4: {
                Either either3;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        Left left = (Left)either2;
                        either3 = new Left(f.apply(left.a()));
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    Right right = (Right)either2;
                    either3 = new Right(right.b());
                }
                return either3;
            }
            throw new MatchError(either2);
        }

        public <Y> Option<Either<A, Y>> filter(Function1<A, Object> p) {
            Either<A, B> either2;
            block4: {
                None$ none$;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        Left left = (Left)either2;
                        none$ = BoxesRunTime.unboxToBoolean(p.apply(left.a())) ? new Some(new Left(left.a())) : None$.MODULE$;
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    none$ = None$.MODULE$;
                }
                return none$;
            }
            throw new MatchError(either2);
        }

        public Seq<A> toSeq() {
            Either<A, B> either2;
            block4: {
                Seq seq;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        Left left = (Left)either2;
                        seq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.genericWrapArray(new Object[]{left.a()}));
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    seq = (Seq)Seq$.MODULE$.empty();
                }
                return seq;
            }
            throw new MatchError(either2);
        }

        public Option<A> toOption() {
            Either<A, B> either2;
            block4: {
                Option option;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        Left left = (Left)either2;
                        option = new Some(left.a());
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    option = None$.MODULE$;
                }
                return option;
            }
            throw new MatchError(either2);
        }

        public <A, B> LeftProjection<A, B> copy(Either<A, B> e) {
            return new LeftProjection<A, B>(e);
        }

        public <A, B> Either<A, B> copy$default$1() {
            return this.e();
        }

        @Override
        public String productPrefix() {
            return "LeftProjection";
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
            return this.e();
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof LeftProjection;
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
            if (!(x$1 instanceof LeftProjection)) return false;
            boolean bl = true;
            if (!bl) return false;
            LeftProjection leftProjection = (LeftProjection)x$1;
            Either<A, B> either2 = this.e();
            Either<A, B> either3 = leftProjection.e();
            if (either2 != null) {
                if (!either2.equals(either3)) return false;
                return true;
            }
            if (either3 == null) return true;
            return false;
        }

        public LeftProjection(Either<A, B> e) {
            this.e = e;
            Product$class.$init$(this);
        }
    }

    public static final class RightProjection<A, B>
    implements Product,
    Serializable {
        private final Either<A, B> e;

        public Either<A, B> e() {
            return this.e;
        }

        public B get() {
            Either<A, B> either2 = this.e();
            if (either2 instanceof Left) {
                throw new NoSuchElementException("Either.right.value on Left");
            }
            if (either2 instanceof Right) {
                Right right = (Right)either2;
                return right.b();
            }
            throw new MatchError(either2);
        }

        public <U> Object foreach(Function1<B, U> f) {
            Either<A, B> either2;
            block4: {
                BoxedUnit boxedUnit;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        boxedUnit = BoxedUnit.UNIT;
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    Right right = (Right)either2;
                    boxedUnit = f.apply(right.b());
                }
                return boxedUnit;
            }
            throw new MatchError(either2);
        }

        public <BB> BB getOrElse(Function0<BB> or) {
            Either<A, B> either2;
            block4: {
                Object object;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        object = or.apply();
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    Right right = (Right)either2;
                    object = right.b();
                }
                return object;
            }
            throw new MatchError(either2);
        }

        public boolean forall(Function1<B, Object> f) {
            Either<A, B> either2;
            block4: {
                boolean bl;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        bl = true;
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    Right right = (Right)either2;
                    bl = BoxesRunTime.unboxToBoolean(f.apply(right.b()));
                }
                return bl;
            }
            throw new MatchError(either2);
        }

        public boolean exists(Function1<B, Object> p) {
            Either<A, B> either2;
            block4: {
                boolean bl;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        bl = false;
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    Right right = (Right)either2;
                    bl = BoxesRunTime.unboxToBoolean(p.apply(right.b()));
                }
                return bl;
            }
            throw new MatchError(either2);
        }

        public <AA, Y> Either<AA, Y> flatMap(Function1<B, Either<AA, Y>> f) {
            Either<A, B> either2;
            block4: {
                Either either3;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        Left left = (Left)either2;
                        either3 = new Left(left.a());
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    Right right = (Right)either2;
                    either3 = f.apply(right.b());
                }
                return either3;
            }
            throw new MatchError(either2);
        }

        public <Y> Product map(Function1<B, Y> f) {
            Either<A, B> either2;
            block4: {
                Either either3;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        Left left = (Left)either2;
                        either3 = new Left(left.a());
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    Right right = (Right)either2;
                    either3 = new Right(f.apply(right.b()));
                }
                return either3;
            }
            throw new MatchError(either2);
        }

        public <X> Option<Either<X, B>> filter(Function1<B, Object> p) {
            Either<A, B> either2;
            block4: {
                None$ none$;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        none$ = None$.MODULE$;
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    Right right = (Right)either2;
                    none$ = BoxesRunTime.unboxToBoolean(p.apply(right.b())) ? new Some(new Right(right.b())) : None$.MODULE$;
                }
                return none$;
            }
            throw new MatchError(either2);
        }

        public Seq<B> toSeq() {
            Either<A, B> either2;
            block4: {
                Seq seq;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        seq = (Seq)Seq$.MODULE$.empty();
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    Right right = (Right)either2;
                    seq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.genericWrapArray(new Object[]{right.b()}));
                }
                return seq;
            }
            throw new MatchError(either2);
        }

        public Option<B> toOption() {
            Either<A, B> either2;
            block4: {
                Option option;
                block3: {
                    block2: {
                        either2 = this.e();
                        if (!(either2 instanceof Left)) break block2;
                        option = None$.MODULE$;
                        break block3;
                    }
                    if (!(either2 instanceof Right)) break block4;
                    Right right = (Right)either2;
                    option = new Some(right.b());
                }
                return option;
            }
            throw new MatchError(either2);
        }

        public <A, B> RightProjection<A, B> copy(Either<A, B> e) {
            return new RightProjection<A, B>(e);
        }

        public <A, B> Either<A, B> copy$default$1() {
            return this.e();
        }

        @Override
        public String productPrefix() {
            return "RightProjection";
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
            return this.e();
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof RightProjection;
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
            if (!(x$1 instanceof RightProjection)) return false;
            boolean bl = true;
            if (!bl) return false;
            RightProjection rightProjection = (RightProjection)x$1;
            Either<A, B> either2 = this.e();
            Either<A, B> either3 = rightProjection.e();
            if (either2 != null) {
                if (!either2.equals(either3)) return false;
                return true;
            }
            if (either3 == null) return true;
            return false;
        }

        public RightProjection(Either<A, B> e) {
            this.e = e;
            Product$class.$init$(this);
        }
    }

    public static final class MergeableEither<A> {
        private final Either<A, A> scala$util$Either$MergeableEither$$x;

        public Either<A, A> scala$util$Either$MergeableEither$$x() {
            return this.scala$util$Either$MergeableEither$$x;
        }

        public A merge() {
            return Either$MergeableEither$.MODULE$.merge$extension(this.scala$util$Either$MergeableEither$$x());
        }

        public int hashCode() {
            return Either$MergeableEither$.MODULE$.hashCode$extension(this.scala$util$Either$MergeableEither$$x());
        }

        public boolean equals(Object x$1) {
            return Either$MergeableEither$.MODULE$.equals$extension(this.scala$util$Either$MergeableEither$$x(), x$1);
        }

        public MergeableEither(Either<A, A> x) {
            this.scala$util$Either$MergeableEither$$x = x;
        }
    }
}

