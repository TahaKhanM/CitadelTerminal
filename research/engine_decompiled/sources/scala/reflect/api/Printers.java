/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import java.io.PrintWriter;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Names;
import scala.reflect.api.Position;
import scala.reflect.api.Printers$BooleanFlag$;
import scala.reflect.api.Symbols;
import scala.reflect.api.Trees;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\rEc!C\u0001\u0003!\u0003\r\t!CB%\u0005!\u0001&/\u001b8uKJ\u001c(BA\u0002\u0005\u0003\r\t\u0007/\u001b\u0006\u0003\u000b\u0019\tqA]3gY\u0016\u001cGOC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0006\u0011\u0005-aQ\"\u0001\u0004\n\u000551!AB!osJ+g\rC\u0003\u0010\u0001\u0011\u0005\u0001#\u0001\u0004%S:LG\u000f\n\u000b\u0002#A\u00111BE\u0005\u0003'\u0019\u0011A!\u00168ji\u001a9Q\u0003\u0001I\u0001\u0004#1\"a\u0003+sK\u0016\u0004&/\u001b8uKJ\u001c\"\u0001\u0006\u0006\t\u000b=!B\u0011\u0001\t\t\u000be!b\u0011\u0001\u000e\u0002\u000bA\u0014\u0018N\u001c;\u0015\u0005EY\u0002\"\u0002\u000f\u0019\u0001\u0004i\u0012\u0001B1sON\u00042a\u0003\u0010!\u0013\tybA\u0001\u0006=e\u0016\u0004X-\u0019;fIz\u0002\"aC\u0011\n\u0005\t2!aA!os\"9A\u0005\u0006a\u0001\n#)\u0013A\u00039sS:$H+\u001f9fgV\ta\u0005\u0005\u0002\fO%\u0011\u0001F\u0002\u0002\b\u0005>|G.Z1o\u0011\u001dQC\u00031A\u0005\u0012-\na\u0002\u001d:j]R$\u0016\u0010]3t?\u0012*\u0017\u000f\u0006\u0002\u0012Y!9Q&KA\u0001\u0002\u00041\u0013a\u0001=%c!1q\u0006\u0006Q!\n\u0019\n1\u0002\u001d:j]R$\u0016\u0010]3tA!9\u0011\u0007\u0006a\u0001\n#)\u0013\u0001\u00039sS:$\u0018\nZ:\t\u000fM\"\u0002\u0019!C\ti\u0005a\u0001O]5oi&#7o\u0018\u0013fcR\u0011\u0011#\u000e\u0005\b[I\n\t\u00111\u0001'\u0011\u00199D\u0003)Q\u0005M\u0005I\u0001O]5oi&#7\u000f\t\u0005\bsQ\u0001\r\u0011\"\u0005&\u0003-\u0001(/\u001b8u\u001f^tWM]:\t\u000fm\"\u0002\u0019!C\ty\u0005y\u0001O]5oi>;h.\u001a:t?\u0012*\u0017\u000f\u0006\u0002\u0012{!9QFOA\u0001\u0002\u00041\u0003BB \u0015A\u0003&a%\u0001\u0007qe&tGoT<oKJ\u001c\b\u0005C\u0004B)\u0001\u0007I\u0011C\u0013\u0002\u0015A\u0014\u0018N\u001c;LS:$7\u000fC\u0004D)\u0001\u0007I\u0011\u0003#\u0002\u001dA\u0014\u0018N\u001c;LS:$7o\u0018\u0013fcR\u0011\u0011#\u0012\u0005\b[\t\u000b\t\u00111\u0001'\u0011\u00199E\u0003)Q\u0005M\u0005Y\u0001O]5oi.Kg\u000eZ:!\u0011\u001dIE\u00031A\u0005\u0012\u0015\nA\u0002\u001d:j]Rl\u0015N\u001d:peNDqa\u0013\u000bA\u0002\u0013EA*\u0001\tqe&tG/T5se>\u00148o\u0018\u0013fcR\u0011\u0011#\u0014\u0005\b[)\u000b\t\u00111\u0001'\u0011\u0019yE\u0003)Q\u0005M\u0005i\u0001O]5oi6K'O]8sg\u0002Bq!\u0015\u000bA\u0002\u0013EQ%\u0001\bqe&tG\u000fU8tSRLwN\\:\t\u000fM#\u0002\u0019!C\t)\u0006\u0011\u0002O]5oiB{7/\u001b;j_:\u001cx\fJ3r)\t\tR\u000bC\u0004.%\u0006\u0005\t\u0019\u0001\u0014\t\r]#\u0002\u0015)\u0003'\u0003=\u0001(/\u001b8u!>\u001c\u0018\u000e^5p]N\u0004\u0003\"B-\u0015\t\u0003Q\u0016!C<ji\"$\u0016\u0010]3t+\u0005YV\"\u0001\u000b\t\u000bu#B\u0011\u0001.\u0002\u0019]LG\u000f[8viRK\b/Z:\t\u000b}#B\u0011\u0001.\u0002\u000f]LG\u000f[%eg\")\u0011\r\u0006C\u00015\u0006Qq/\u001b;i_V$\u0018\nZ:\t\u000b\r$B\u0011\u0001.\u0002\u0015]LG\u000f[(x]\u0016\u00148\u000fC\u0003f)\u0011\u0005!,A\u0007xSRDw.\u001e;Po:,'o\u001d\u0005\u0006OR!\tAW\u0001\no&$\bnS5oINDQ!\u001b\u000b\u0005\u0002i\u000bAb^5uQ>,HoS5oINDQa\u001b\u000b\u0005\u0002i\u000b1b^5uQ6K'O]8sg\")Q\u000e\u0006C\u00015\u0006qq/\u001b;i_V$X*\u001b:s_J\u001c\b\"B8\u0015\t\u0003Q\u0016!D<ji\"\u0004vn]5uS>t7\u000fC\u0003r)\u0011\u0005!,\u0001\txSRDw.\u001e;Q_NLG/[8og\u001a!1\u000f\u0001!u\u0005-\u0011un\u001c7fC:4E.Y4\u0014\tITQ\u000f\u001f\t\u0003\u0017YL!a\u001e\u0004\u0003\u000fA\u0013x\u000eZ;diB\u00111\"_\u0005\u0003u\u001a\u0011AbU3sS\u0006d\u0017N_1cY\u0016D\u0001\u0002 :\u0003\u0016\u0004%\t!`\u0001\u0006m\u0006dW/Z\u000b\u0002}B\u00191b \u0014\n\u0007\u0005\u0005aA\u0001\u0004PaRLwN\u001c\u0005\n\u0003\u000b\u0011(\u0011#Q\u0001\ny\faA^1mk\u0016\u0004\u0003bBA\u0005e\u0012\u0005\u00111B\u0001\u0007y%t\u0017\u000e\u001e \u0015\t\u00055\u0011\u0011\u0003\t\u0004\u0003\u001f\u0011X\"\u0001\u0001\t\rq\f9\u00011\u0001\u007f\u0011%\t)B]A\u0001\n\u0003\t9\"\u0001\u0003d_BLH\u0003BA\u0007\u00033A\u0001\u0002`A\n!\u0003\u0005\rA \u0005\n\u0003;\u0011\u0018\u0013!C\u0001\u0003?\tabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002\")\u001aa0a\t,\u0005\u0005\u0015\u0002\u0003BA\u0014\u0003ci!!!\u000b\u000b\t\u0005-\u0012QF\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\f\u0007\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003g\tICA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016D\u0011\"a\u000es\u0003\u0003%\t%!\u000f\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\tY\u0004\u0005\u0003\u0002>\u0005\u001dSBAA \u0015\u0011\t\t%a\u0011\u0002\t1\fgn\u001a\u0006\u0003\u0003\u000b\nAA[1wC&!\u0011\u0011JA \u0005\u0019\u0019FO]5oO\"I\u0011Q\n:\u0002\u0002\u0013\u0005\u0011qJ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003#\u00022aCA*\u0013\r\t)F\u0002\u0002\u0004\u0013:$\b\"CA-e\u0006\u0005I\u0011AA.\u00039\u0001(o\u001c3vGR,E.Z7f]R$2\u0001IA/\u0011%i\u0013qKA\u0001\u0002\u0004\t\t\u0006C\u0005\u0002bI\f\t\u0011\"\u0011\u0002d\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002fA)\u0011qMA7A5\u0011\u0011\u0011\u000e\u0006\u0004\u0003W2\u0011AC2pY2,7\r^5p]&!\u0011qNA5\u0005!IE/\u001a:bi>\u0014\b\"CA:e\u0006\u0005I\u0011AA;\u0003!\u0019\u0017M\\#rk\u0006dGc\u0001\u0014\u0002x!AQ&!\u001d\u0002\u0002\u0003\u0007\u0001\u0005C\u0005\u0002|I\f\t\u0011\"\u0011\u0002~\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002R!I\u0011\u0011\u0011:\u0002\u0002\u0013\u0005\u00131Q\u0001\ti>\u001cFO]5oOR\u0011\u00111\b\u0005\n\u0003\u000f\u0013\u0018\u0011!C!\u0003\u0013\u000ba!Z9vC2\u001cHc\u0001\u0014\u0002\f\"AQ&!\"\u0002\u0002\u0003\u0007\u0001eB\u0004\u0002\u0010\u0002A\t!!%\u0002\u0017\t{w\u000e\\3b]\u001ac\u0017m\u001a\t\u0005\u0003\u001f\t\u0019J\u0002\u0004t\u0001!\u0005\u0011QS\n\u0005\u0003'S\u0001\u0010\u0003\u0005\u0002\n\u0005ME\u0011AAM)\t\t\t\n\u0003\u0005\u0002\u001e\u0006ME1AAP\u0003Q\u0011wn\u001c7fC:$vNQ8pY\u0016\fgN\u00127bOR!\u0011QBAQ\u0011\u0019a\u00181\u0014a\u0001M!A\u0011QUAJ\t\u0007\t9+A\npaRLwN\u001c+p\u0005>|G.Z1o\r2\fw\r\u0006\u0003\u0002\u000e\u0005%\u0006B\u0002?\u0002$\u0002\u0007a\u0010\u0003\u0005\u0002.\u0006ME1AAX\u0003Q\u0019X\r\u001e;j]\u001e$vNQ8pY\u0016\fgN\u00127bOR!\u0011QBAY\u0011!\t\u0019,a+A\u0002\u0005U\u0016aB:fiRLgn\u001a\t\u0005\u0003o\u000b9\r\u0005\u0003\u0002:\u0006\rWBAA^\u0015\u0011\ti,a0\u0002\u0011M,G\u000f^5oONT1!!1\u0005\u0003!Ig\u000e^3s]\u0006d\u0017\u0002BAc\u0003w\u0013q\"T;uC\ndWmU3ui&twm]\u0005\u0005\u0003\u0013\f\u0019M\u0001\bC_>dW-\u00198TKR$\u0018N\\4\t\u0015\u00055\u00171SA\u0001\n\u0003\u000by-A\u0003baBd\u0017\u0010\u0006\u0003\u0002\u000e\u0005E\u0007B\u0002?\u0002L\u0002\u0007a\u0010\u0003\u0006\u0002V\u0006M\u0015\u0011!CA\u0003/\fq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002Z\u0006m\u0007cA\u0006\u0000}\"Q\u0011Q\\Aj\u0003\u0003\u0005\r!!\u0004\u0002\u0007a$\u0003\u0007C\u0004\u0002b\u0002!\t\"a9\u0002\rI,g\u000eZ3s)I\t)/!=\u0002v\n5!q\u0002B\t\u0005'\u0011)Ba\u0006\u0011\t\u0005\u001d\u0018Q\u001e\b\u0004\u0017\u0005%\u0018bAAv\r\u00051\u0001K]3eK\u001aLA!!\u0013\u0002p*\u0019\u00111\u001e\u0004\t\u000f\u0005M\u0018q\u001ca\u0001A\u0005!q\u000f[1u\u0011!\t90a8A\u0002\u0005e\u0018!C7l!JLg\u000e^3s!\u001dY\u00111`A\u0000\u0005\u0017I1!!@\u0007\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0003\u0003\u0002\t\u001dQB\u0001B\u0002\u0015\u0011\u0011)!a\u0011\u0002\u0005%|\u0017\u0002\u0002B\u0005\u0005\u0007\u00111\u0002\u0015:j]R<&/\u001b;feB\u0019\u0011q\u0002\u000b\t\u0013\u0011\ny\u000e%AA\u0002\u00055\u0001\"C\u0019\u0002`B\u0005\t\u0019AA\u0007\u0011%I\u0014q\u001cI\u0001\u0002\u0004\ti\u0001C\u0005B\u0003?\u0004\n\u00111\u0001\u0002\u000e!I\u0011*a8\u0011\u0002\u0003\u0007\u0011Q\u0002\u0005\n#\u0006}\u0007\u0013!a\u0001\u0003\u001bAqAa\u0007\u0001\t#\u0012i\"\u0001\u0007ue\u0016,Gk\\*ue&tw\r\u0006\u0003\u0002f\n}\u0001\u0002\u0003B\u0011\u00053\u0001\rAa\t\u0002\tQ\u0014X-\u001a\t\u0005\u0003\u001f\u0011)#\u0003\u0003\u0003(\t%\"\u0001\u0002+sK\u0016L1Aa\u000b\u0003\u0005\u0015!&/Z3t\u0011\u001d\u0011y\u0003\u0001C\u0001\u0005c\tAa\u001d5poR\u0001\u0012Q\u001dB\u001a\u0005o\u0011IDa\u000f\u0003>\t}\"\u0011\t\u0005\b\u0005k\u0011i\u00031\u0001!\u0003\r\tg.\u001f\u0005\nI\t5\u0002\u0013!a\u0001\u0003\u001bA\u0011\"\rB\u0017!\u0003\u0005\r!!\u0004\t\u0013e\u0012i\u0003%AA\u0002\u00055\u0001\"C!\u0003.A\u0005\t\u0019AA\u0007\u0011%I%Q\u0006I\u0001\u0002\u0004\ti\u0001C\u0005R\u0005[\u0001\n\u00111\u0001\u0002\u000e!9!Q\t\u0001\u0007\u0012\t\u001d\u0013A\u00048foR\u0013X-\u001a)sS:$XM\u001d\u000b\u0005\u0005\u0017\u0011I\u0005\u0003\u0005\u0003L\t\r\u0003\u0019AA\u0000\u0003\ryW\u000f\u001e\u0005\b\u0005\u001f\u0002A\u0011\u0001B)\u0003!\u0019\bn\\<D_\u0012,GCDAs\u0005'\u0012)Fa\u0016\u0003Z\tm#Q\f\u0005\t\u0005C\u0011i\u00051\u0001\u0003$!IAE!\u0014\u0011\u0002\u0003\u0007\u0011Q\u0002\u0005\nc\t5\u0003\u0013!a\u0001\u0003\u001bA\u0011\"\u000fB'!\u0003\u0005\r!!\u0004\t\u0013E\u0013i\u0005%AA\u0002\u00055\u0001\"\u0003B0\u0005\u001b\u0002\n\u00111\u0001'\u00031\u0001(/\u001b8u%>|G\u000fU6h\u0011\u001d\u0011\u0019\u0007\u0001D\t\u0005K\naB\\3x\u0007>$W\r\u0015:j]R,'\u000f\u0006\u0005\u0003\f\t\u001d$\u0011\u000eB6\u0011!\u0011YE!\u0019A\u0002\u0005}\b\u0002\u0003B\u0011\u0005C\u0002\rAa\t\t\u000f\t}#\u0011\ra\u0001M!9!q\u000e\u0001\u0005\u0002\tE\u0014aB:i_^\u0014\u0016m\u001e\u000b\u0011\u0003K\u0014\u0019H!\u001e\u0003x\te$1\u0010B?\u0005\u007fBqA!\u000e\u0003n\u0001\u0007\u0001\u0005C\u0005%\u0005[\u0002\n\u00111\u0001\u0002\u000e!I\u0011G!\u001c\u0011\u0002\u0003\u0007\u0011Q\u0002\u0005\ns\t5\u0004\u0013!a\u0001\u0003\u001bA\u0011\"\u0011B7!\u0003\u0005\r!!\u0004\t\u0013%\u0013i\u0007%AA\u0002\u00055\u0001\"C)\u0003nA\u0005\t\u0019AA\u0007\u0011\u001d\u0011\u0019\t\u0001D\t\u0005\u000b\u000b\u0011C\\3x%\u0006<HK]3f!JLg\u000e^3s)\u0011\u0011YAa\"\t\u0011\t-#\u0011\u0011a\u0001\u0003\u007fDqAa\f\u0001\r\u0003\u0011Y\t\u0006\u0003\u0002f\n5\u0005\u0002\u0003BH\u0005\u0013\u0003\rA!%\u0002\t9\fW.\u001a\t\u0005\u0003\u001f\u0011\u0019*\u0003\u0003\u0003\u0016\n]%\u0001\u0002(b[\u0016L1A!'\u0003\u0005\u0015q\u0015-\\3t\u0011\u001d\u0011y\u0007\u0001C\u0001\u0005;#B!!:\u0003 \"A!q\u0012BN\u0001\u0004\u0011\t\nC\u0004\u00030\u00011\tAa)\u0015\t\u0005\u0015(Q\u0015\u0005\t\u0005O\u0013\t\u000b1\u0001\u0003*\u0006)a\r\\1hgB!\u0011q\u0002BV\u0013\u0011\u0011iKa,\u0003\u000f\u0019c\u0017mZ*fi&\u0019!\u0011\u0017\u0002\u0003\u0011\u0019c\u0017mZ*fiNDqAa\f\u0001\r\u0003\u0011)\f\u0006\u0003\u0002f\n]\u0006\u0002\u0003B]\u0005g\u0003\rAa/\u0002\u0011A|7/\u001b;j_:\u0004B!a\u0004\u0003>&!!q\u0018Ba\u0005!\u0001vn]5uS>t\u0017b\u0001Bb\u0005\tI\u0001k\\:ji&|gn\u001d\u0005\b\u0005_\u0002A\u0011\u0001Bd)\u0011\t)O!3\t\u0011\t\u001d&Q\u0019a\u0001\u0005SCqAa\u001c\u0001\t\u0003\u0011i\r\u0006\u0003\u0002f\n=\u0007\u0002\u0003B]\u0005\u0017\u0004\rAa/\t\u000f\tM\u0007A\"\u0001\u0003V\u0006A1\u000f[8x\t\u0016\u001cG\u000e\u0006\u0003\u0002f\n]\u0007\u0002\u0003Bm\u0005#\u0004\rAa7\u0002\u0007MLX\u000e\u0005\u0003\u0002\u0010\tu\u0017\u0002\u0002Bp\u0005C\u0014aaU=nE>d\u0017b\u0001Br\u0005\t91+_7c_2\u001c\b\"\u0003Bt\u0001E\u0005I\u0011\u0003Bu\u0003A\u0011XM\u001c3fe\u0012\"WMZ1vYR$3'\u0006\u0002\u0003l*\"\u0011QBA\u0012\u0011%\u0011y\u000fAI\u0001\n#\u0011I/\u0001\tsK:$WM\u001d\u0013eK\u001a\fW\u000f\u001c;%i!I!1\u001f\u0001\u0012\u0002\u0013E!\u0011^\u0001\u0011e\u0016tG-\u001a:%I\u00164\u0017-\u001e7uIUB\u0011Ba>\u0001#\u0003%\tB!;\u0002!I,g\u000eZ3sI\u0011,g-Y;mi\u00122\u0004\"\u0003B~\u0001E\u0005I\u0011\u0003Bu\u0003A\u0011XM\u001c3fe\u0012\"WMZ1vYR$s\u0007C\u0005\u0003\u0000\u0002\t\n\u0011\"\u0005\u0003j\u0006\u0001\"/\u001a8eKJ$C-\u001a4bk2$H\u0005\u000f\u0005\n\u0007\u0007\u0001\u0011\u0013!C\u0001\u0005S\fab\u001d5po\u0012\"WMZ1vYR$#\u0007C\u0005\u0004\b\u0001\t\n\u0011\"\u0001\u0003j\u0006q1\u000f[8xI\u0011,g-Y;mi\u0012\u001a\u0004\"CB\u0006\u0001E\u0005I\u0011\u0001Bu\u00039\u0019\bn\\<%I\u00164\u0017-\u001e7uIQB\u0011ba\u0004\u0001#\u0003%\tA!;\u0002\u001dMDwn\u001e\u0013eK\u001a\fW\u000f\u001c;%k!I11\u0003\u0001\u0012\u0002\u0013\u0005!\u0011^\u0001\u000fg\"|w\u000f\n3fM\u0006,H\u000e\u001e\u00137\u0011%\u00199\u0002AI\u0001\n\u0003\u0011I/\u0001\btQ><H\u0005Z3gCVdG\u000fJ\u001c\t\u0013\rm\u0001!%A\u0005\u0002\t%\u0018AE:i_^\u001cu\u000eZ3%I\u00164\u0017-\u001e7uIIB\u0011ba\b\u0001#\u0003%\tA!;\u0002%MDwn^\"pI\u0016$C-\u001a4bk2$He\r\u0005\n\u0007G\u0001\u0011\u0013!C\u0001\u0005S\f!c\u001d5po\u000e{G-\u001a\u0013eK\u001a\fW\u000f\u001c;%i!I1q\u0005\u0001\u0012\u0002\u0013\u0005!\u0011^\u0001\u0013g\"|woQ8eK\u0012\"WMZ1vYR$S\u0007C\u0005\u0004,\u0001\t\n\u0011\"\u0001\u0004.\u0005\u00112\u000f[8x\u0007>$W\r\n3fM\u0006,H\u000e\u001e\u00137+\t\u0019yCK\u0002'\u0003GA\u0011ba\r\u0001#\u0003%\tA!;\u0002#MDwn\u001e*bo\u0012\"WMZ1vYR$#\u0007C\u0005\u00048\u0001\t\n\u0011\"\u0001\u0003j\u0006\t2\u000f[8x%\u0006<H\u0005Z3gCVdG\u000fJ\u001a\t\u0013\rm\u0002!%A\u0005\u0002\t%\u0018!E:i_^\u0014\u0016m\u001e\u0013eK\u001a\fW\u000f\u001c;%i!I1q\b\u0001\u0012\u0002\u0013\u0005!\u0011^\u0001\u0012g\"|wOU1xI\u0011,g-Y;mi\u0012*\u0004\"CB\"\u0001E\u0005I\u0011\u0001Bu\u0003E\u0019\bn\\<SC^$C-\u001a4bk2$HE\u000e\u0005\n\u0007\u000f\u0002\u0011\u0013!C\u0001\u0005S\f\u0011c\u001d5poJ\u000bw\u000f\n3fM\u0006,H\u000e\u001e\u00138!\u0011\u0019Ye!\u0014\u000e\u0003\tI1aa\u0014\u0003\u0005!)f.\u001b<feN,\u0007")
public interface Printers {
    public Printers$BooleanFlag$ BooleanFlag();

    public String render(Object var1, Function1<PrintWriter, TreePrinter> var2, BooleanFlag var3, BooleanFlag var4, BooleanFlag var5, BooleanFlag var6, BooleanFlag var7, BooleanFlag var8);

    public BooleanFlag render$default$3();

    public BooleanFlag render$default$4();

    public BooleanFlag render$default$5();

    public BooleanFlag render$default$6();

    public BooleanFlag render$default$7();

    public BooleanFlag render$default$8();

    public String treeToString(Trees.TreeApi var1);

    public String show(Object var1, BooleanFlag var2, BooleanFlag var3, BooleanFlag var4, BooleanFlag var5, BooleanFlag var6, BooleanFlag var7);

    public TreePrinter newTreePrinter(PrintWriter var1);

    public String showCode(Trees.TreeApi var1, BooleanFlag var2, BooleanFlag var3, BooleanFlag var4, BooleanFlag var5, boolean var6);

    public TreePrinter newCodePrinter(PrintWriter var1, Trees.TreeApi var2, boolean var3);

    public String showRaw(Object var1, BooleanFlag var2, BooleanFlag var3, BooleanFlag var4, BooleanFlag var5, BooleanFlag var6, BooleanFlag var7);

    public TreePrinter newRawTreePrinter(PrintWriter var1);

    public String show(Names.NameApi var1);

    public String showRaw(Names.NameApi var1);

    public String show(Object var1);

    public String show(Position var1);

    public BooleanFlag show$default$2();

    public BooleanFlag show$default$3();

    public BooleanFlag show$default$4();

    public BooleanFlag show$default$5();

    public BooleanFlag show$default$6();

    public BooleanFlag show$default$7();

    public BooleanFlag showCode$default$2();

    public BooleanFlag showCode$default$3();

    public BooleanFlag showCode$default$4();

    public BooleanFlag showCode$default$5();

    public boolean showCode$default$6();

    public String showRaw(Object var1);

    public String showRaw(Position var1);

    public BooleanFlag showRaw$default$2();

    public BooleanFlag showRaw$default$3();

    public BooleanFlag showRaw$default$4();

    public BooleanFlag showRaw$default$5();

    public BooleanFlag showRaw$default$6();

    public BooleanFlag showRaw$default$7();

    public String showDecl(Symbols.SymbolApi var1);

    public class BooleanFlag
    implements Product,
    Serializable {
        private final Option<Object> value;
        public final /* synthetic */ Universe $outer;

        public Option<Object> value() {
            return this.value;
        }

        public BooleanFlag copy(Option<Object> value) {
            return new BooleanFlag(this.scala$reflect$api$Printers$BooleanFlag$$$outer(), value);
        }

        public Option<Object> copy$default$1() {
            return this.value();
        }

        @Override
        public String productPrefix() {
            return "BooleanFlag";
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
            return this.value();
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof BooleanFlag;
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
            if (!(x$1 instanceof BooleanFlag)) return false;
            if (((BooleanFlag)x$1).scala$reflect$api$Printers$BooleanFlag$$$outer() != this.scala$reflect$api$Printers$BooleanFlag$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            BooleanFlag booleanFlag = (BooleanFlag)x$1;
            Option<Object> option = this.value();
            Option<Object> option2 = booleanFlag.value();
            if (option == null) {
                if (option2 != null) {
                    return false;
                }
            } else if (!option.equals(option2)) return false;
            if (!booleanFlag.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ Universe scala$reflect$api$Printers$BooleanFlag$$$outer() {
            return this.$outer;
        }

        public BooleanFlag(Universe $outer, Option<Object> value) {
            this.value = value;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Product$class.$init$(this);
        }
    }

    public interface TreePrinter {
        public void print(Seq<Object> var1);

        public boolean printTypes();

        @TraitSetter
        public void printTypes_$eq(boolean var1);

        public boolean printIds();

        @TraitSetter
        public void printIds_$eq(boolean var1);

        public boolean printOwners();

        @TraitSetter
        public void printOwners_$eq(boolean var1);

        public boolean printKinds();

        @TraitSetter
        public void printKinds_$eq(boolean var1);

        public boolean printMirrors();

        @TraitSetter
        public void printMirrors_$eq(boolean var1);

        public boolean printPositions();

        @TraitSetter
        public void printPositions_$eq(boolean var1);

        public TreePrinter withTypes();

        public TreePrinter withoutTypes();

        public TreePrinter withIds();

        public TreePrinter withoutIds();

        public TreePrinter withOwners();

        public TreePrinter withoutOwners();

        public TreePrinter withKinds();

        public TreePrinter withoutKinds();

        public TreePrinter withMirrors();

        public TreePrinter withoutMirrors();

        public TreePrinter withPositions();

        public TreePrinter withoutPositions();

        public /* synthetic */ Printers scala$reflect$api$Printers$TreePrinter$$$outer();
    }
}

