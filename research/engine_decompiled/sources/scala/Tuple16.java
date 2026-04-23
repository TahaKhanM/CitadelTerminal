/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Option;
import scala.Product$class;
import scala.Product16;
import scala.Product16$class;
import scala.Serializable;
import scala.Tuple16$;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0015ef\u0001B\u0001\u0003\u0001\u0016\u0011q\u0001V;qY\u0016\fdGC\u0001\u0004\u0003\u0015\u00198-\u00197b\u0007\u0001)\u0012C\u0002\t\u001b;\u0001\u001ac%\u000b\u00170eUB4HP!E'\u0015\u0001qa\u0003$J!\tA\u0011\"D\u0001\u0003\u0013\tQ!A\u0001\u0004B]f\u0014VM\u001a\t\u0013\u00111q\u0011\u0004H\u0010#K!Zc&\r\u001b8uu\u00025)\u0003\u0002\u000e\u0005\tI\u0001K]8ek\u000e$\u0018G\u000e\t\u0003\u001fAa\u0001\u0001\u0002\u0004\u0012\u0001\u0011\u0015\rA\u0005\u0002\u0003)F\n\"a\u0005\f\u0011\u0005!!\u0012BA\u000b\u0003\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001C\f\n\u0005a\u0011!aA!osB\u0011qB\u0007\u0003\u00077\u0001!)\u0019\u0001\n\u0003\u0005Q\u0013\u0004CA\b\u001e\t\u0019q\u0002\u0001\"b\u0001%\t\u0011Ak\r\t\u0003\u001f\u0001\"a!\t\u0001\u0005\u0006\u0004\u0011\"A\u0001+5!\ty1\u0005\u0002\u0004%\u0001\u0011\u0015\rA\u0005\u0002\u0003)V\u0002\"a\u0004\u0014\u0005\r\u001d\u0002AQ1\u0001\u0013\u0005\t!f\u0007\u0005\u0002\u0010S\u00111!\u0006\u0001CC\u0002I\u0011!\u0001V\u001c\u0011\u0005=aCAB\u0017\u0001\t\u000b\u0007!C\u0001\u0002UqA\u0011qb\f\u0003\u0007a\u0001!)\u0019\u0001\n\u0003\u0005QK\u0004CA\b3\t\u0019\u0019\u0004\u0001\"b\u0001%\t\u0019A+\r\u0019\u0011\u0005=)DA\u0002\u001c\u0001\t\u000b\u0007!CA\u0002UcE\u0002\"a\u0004\u001d\u0005\re\u0002AQ1\u0001\u0013\u0005\r!\u0016G\r\t\u0003\u001fm\"a\u0001\u0010\u0001\u0005\u0006\u0004\u0011\"a\u0001+2gA\u0011qB\u0010\u0003\u0007\u007f\u0001!)\u0019\u0001\n\u0003\u0007Q\u000bD\u0007\u0005\u0002\u0010\u0003\u00121!\t\u0001CC\u0002I\u00111\u0001V\u00196!\tyA\t\u0002\u0004F\u0001\u0011\u0015\rA\u0005\u0002\u0004)F2\u0004C\u0001\u0005H\u0013\tA%AA\u0004Qe>$Wo\u0019;\u0011\u0005!Q\u0015BA&\u0003\u00051\u0019VM]5bY&T\u0018M\u00197f\u0011!i\u0005A!f\u0001\n\u0003q\u0015AA02+\u0005q\u0001\u0002\u0003)\u0001\u0005#\u0005\u000b\u0011\u0002\b\u0002\u0007}\u000b\u0004\u0005\u0003\u0005S\u0001\tU\r\u0011\"\u0001T\u0003\ty&'F\u0001\u001a\u0011!)\u0006A!E!\u0002\u0013I\u0012aA03A!Aq\u000b\u0001BK\u0002\u0013\u0005\u0001,\u0001\u0002`gU\tA\u0004\u0003\u0005[\u0001\tE\t\u0015!\u0003\u001d\u0003\ry6\u0007\t\u0005\t9\u0002\u0011)\u001a!C\u0001;\u0006\u0011q\fN\u000b\u0002?!Aq\f\u0001B\tB\u0003%q$A\u0002`i\u0001B\u0001\"\u0019\u0001\u0003\u0016\u0004%\tAY\u0001\u0003?V*\u0012A\t\u0005\tI\u0002\u0011\t\u0012)A\u0005E\u0005\u0019q,\u000e\u0011\t\u0011\u0019\u0004!Q3A\u0005\u0002\u001d\f!a\u0018\u001c\u0016\u0003\u0015B\u0001\"\u001b\u0001\u0003\u0012\u0003\u0006I!J\u0001\u0004?Z\u0002\u0003\u0002C6\u0001\u0005+\u0007I\u0011\u00017\u0002\u0005};T#\u0001\u0015\t\u00119\u0004!\u0011#Q\u0001\n!\n1aX\u001c!\u0011!\u0001\bA!f\u0001\n\u0003\t\u0018AA09+\u0005Y\u0003\u0002C:\u0001\u0005#\u0005\u000b\u0011B\u0016\u0002\u0007}C\u0004\u0005\u0003\u0005v\u0001\tU\r\u0011\"\u0001w\u0003\ty\u0016(F\u0001/\u0011!A\bA!E!\u0002\u0013q\u0013aA0:A!A!\u0010\u0001BK\u0002\u0013\u000510A\u0002`cA*\u0012!\r\u0005\t{\u0002\u0011\t\u0012)A\u0005c\u0005!q,\r\u0019!\u0011%y\bA!f\u0001\n\u0003\t\t!A\u0002`cE*\u0012\u0001\u000e\u0005\n\u0003\u000b\u0001!\u0011#Q\u0001\nQ\nAaX\u00192A!Q\u0011\u0011\u0002\u0001\u0003\u0016\u0004%\t!a\u0003\u0002\u0007}\u000b$'F\u00018\u0011%\ty\u0001\u0001B\tB\u0003%q'\u0001\u0003`cI\u0002\u0003BCA\n\u0001\tU\r\u0011\"\u0001\u0002\u0016\u0005\u0019q,M\u001a\u0016\u0003iB\u0011\"!\u0007\u0001\u0005#\u0005\u000b\u0011\u0002\u001e\u0002\t}\u000b4\u0007\t\u0005\u000b\u0003;\u0001!Q3A\u0005\u0002\u0005}\u0011aA02iU\tQ\bC\u0005\u0002$\u0001\u0011\t\u0012)A\u0005{\u0005!q,\r\u001b!\u0011)\t9\u0003\u0001BK\u0002\u0013\u0005\u0011\u0011F\u0001\u0004?F*T#\u0001!\t\u0013\u00055\u0002A!E!\u0002\u0013\u0001\u0015\u0001B02k\u0001B!\"!\r\u0001\u0005+\u0007I\u0011AA\u001a\u0003\ry\u0016GN\u000b\u0002\u0007\"I\u0011q\u0007\u0001\u0003\u0012\u0003\u0006IaQ\u0001\u0005?F2\u0004\u0005C\u0004\u0002<\u0001!\t!!\u0010\u0002\rqJg.\u001b;?)\t\ny$!\u0011\u0002D\u0005\u0015\u0013qIA%\u0003\u0017\ni%a\u0014\u0002R\u0005M\u0013QKA,\u00033\nY&!\u0018\u0002`A\u0011\u0002\u0002\u0001\b\u001a9}\u0011S\u0005K\u0016/cQ:$(\u0010!D\u0011\u0019i\u0015\u0011\ba\u0001\u001d!1!+!\u000fA\u0002eAaaVA\u001d\u0001\u0004a\u0002B\u0002/\u0002:\u0001\u0007q\u0004\u0003\u0004b\u0003s\u0001\rA\t\u0005\u0007M\u0006e\u0002\u0019A\u0013\t\r-\fI\u00041\u0001)\u0011\u0019\u0001\u0018\u0011\ba\u0001W!1Q/!\u000fA\u00029BaA_A\u001d\u0001\u0004\t\u0004BB@\u0002:\u0001\u0007A\u0007C\u0004\u0002\n\u0005e\u0002\u0019A\u001c\t\u000f\u0005M\u0011\u0011\ba\u0001u!9\u0011QDA\u001d\u0001\u0004i\u0004bBA\u0014\u0003s\u0001\r\u0001\u0011\u0005\b\u0003c\tI\u00041\u0001D\u0011\u001d\t\u0019\u0007\u0001C!\u0003K\n\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003O\u0002B!!\u001b\u0002t5\u0011\u00111\u000e\u0006\u0005\u0003[\ny'\u0001\u0003mC:<'BAA9\u0003\u0011Q\u0017M^1\n\t\u0005U\u00141\u000e\u0002\u0007'R\u0014\u0018N\\4\t\u0013\u0005e\u0004!!A\u0005\u0002\u0005m\u0014\u0001B2paf,\"%! \u0002\u0004\u0006\u001d\u00151RAH\u0003'\u000b9*a'\u0002 \u0006\r\u0016qUAV\u0003_\u000b\u0019,a.\u0002<\u0006}FCIA@\u0003\u0003\f\u0019-!2\u0002H\u0006%\u00171ZAg\u0003\u001f\f\t.a5\u0002V\u0006]\u0017\u0011\\An\u0003;\fy\u000e\u0005\u0012\t\u0001\u0005\u0005\u0015QQAE\u0003\u001b\u000b\t*!&\u0002\u001a\u0006u\u0015\u0011UAS\u0003S\u000bi+!-\u00026\u0006e\u0016Q\u0018\t\u0004\u001f\u0005\rEAB\t\u0002x\t\u0007!\u0003E\u0002\u0010\u0003\u000f#aaGA<\u0005\u0004\u0011\u0002cA\b\u0002\f\u00121a$a\u001eC\u0002I\u00012aDAH\t\u0019\t\u0013q\u000fb\u0001%A\u0019q\"a%\u0005\r\u0011\n9H1\u0001\u0013!\ry\u0011q\u0013\u0003\u0007O\u0005]$\u0019\u0001\n\u0011\u0007=\tY\n\u0002\u0004+\u0003o\u0012\rA\u0005\t\u0004\u001f\u0005}EAB\u0017\u0002x\t\u0007!\u0003E\u0002\u0010\u0003G#a\u0001MA<\u0005\u0004\u0011\u0002cA\b\u0002(\u001211'a\u001eC\u0002I\u00012aDAV\t\u00191\u0014q\u000fb\u0001%A\u0019q\"a,\u0005\re\n9H1\u0001\u0013!\ry\u00111\u0017\u0003\u0007y\u0005]$\u0019\u0001\n\u0011\u0007=\t9\f\u0002\u0004@\u0003o\u0012\rA\u0005\t\u0004\u001f\u0005mFA\u0002\"\u0002x\t\u0007!\u0003E\u0002\u0010\u0003\u007f#a!RA<\u0005\u0004\u0011\u0002\"C'\u0002xA\u0005\t\u0019AAA\u0011%\u0011\u0016q\u000fI\u0001\u0002\u0004\t)\tC\u0005X\u0003o\u0002\n\u00111\u0001\u0002\n\"IA,a\u001e\u0011\u0002\u0003\u0007\u0011Q\u0012\u0005\nC\u0006]\u0004\u0013!a\u0001\u0003#C\u0011BZA<!\u0003\u0005\r!!&\t\u0013-\f9\b%AA\u0002\u0005e\u0005\"\u00039\u0002xA\u0005\t\u0019AAO\u0011%)\u0018q\u000fI\u0001\u0002\u0004\t\t\u000bC\u0005{\u0003o\u0002\n\u00111\u0001\u0002&\"Iq0a\u001e\u0011\u0002\u0003\u0007\u0011\u0011\u0016\u0005\u000b\u0003\u0013\t9\b%AA\u0002\u00055\u0006BCA\n\u0003o\u0002\n\u00111\u0001\u00022\"Q\u0011QDA<!\u0003\u0005\r!!.\t\u0015\u0005\u001d\u0012q\u000fI\u0001\u0002\u0004\tI\f\u0003\u0006\u00022\u0005]\u0004\u0013!a\u0001\u0003{C\u0011\"a9\u0001#\u0003%\t!!:\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0013q]A\u007f\u0003\u007f\u0014\tAa\u0001\u0003\u0006\t\u001d!\u0011\u0002B\u0006\u0005\u001b\u0011yA!\u0005\u0003\u0014\tU!q\u0003B\r\u00057)\"!!;+\u00079\tYo\u000b\u0002\u0002nB!\u0011q^A}\u001b\t\t\tP\u0003\u0003\u0002t\u0006U\u0018!C;oG\",7m[3e\u0015\r\t9PA\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA~\u0003c\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u0019\t\u0012\u0011\u001db\u0001%\u001111$!9C\u0002I!aAHAq\u0005\u0004\u0011BAB\u0011\u0002b\n\u0007!\u0003\u0002\u0004%\u0003C\u0014\rA\u0005\u0003\u0007O\u0005\u0005(\u0019\u0001\n\u0005\r)\n\tO1\u0001\u0013\t\u0019i\u0013\u0011\u001db\u0001%\u00111\u0001'!9C\u0002I!aaMAq\u0005\u0004\u0011BA\u0002\u001c\u0002b\n\u0007!\u0003\u0002\u0004:\u0003C\u0014\rA\u0005\u0003\u0007y\u0005\u0005(\u0019\u0001\n\u0005\r}\n\tO1\u0001\u0013\t\u0019\u0011\u0015\u0011\u001db\u0001%\u00111Q)!9C\u0002IA\u0011Ba\b\u0001#\u0003%\tA!\t\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011#1\u0005B\u0014\u0005S\u0011YC!\f\u00030\tE\"1\u0007B\u001b\u0005o\u0011IDa\u000f\u0003>\t}\"\u0011\tB\"\u0005\u000b*\"A!\n+\u0007e\tY\u000f\u0002\u0004\u0012\u0005;\u0011\rA\u0005\u0003\u00077\tu!\u0019\u0001\n\u0005\ry\u0011iB1\u0001\u0013\t\u0019\t#Q\u0004b\u0001%\u00111AE!\bC\u0002I!aa\nB\u000f\u0005\u0004\u0011BA\u0002\u0016\u0003\u001e\t\u0007!\u0003\u0002\u0004.\u0005;\u0011\rA\u0005\u0003\u0007a\tu!\u0019\u0001\n\u0005\rM\u0012iB1\u0001\u0013\t\u00191$Q\u0004b\u0001%\u00111\u0011H!\bC\u0002I!a\u0001\u0010B\u000f\u0005\u0004\u0011BAB \u0003\u001e\t\u0007!\u0003\u0002\u0004C\u0005;\u0011\rA\u0005\u0003\u0007\u000b\nu!\u0019\u0001\n\t\u0013\t%\u0003!%A\u0005\u0002\t-\u0013AD2paf$C-\u001a4bk2$HeM\u000b#\u0005\u001b\u0012\tFa\u0015\u0003V\t]#\u0011\fB.\u0005;\u0012yF!\u0019\u0003d\t\u0015$q\rB5\u0005W\u0012iGa\u001c\u0016\u0005\t=#f\u0001\u000f\u0002l\u00121\u0011Ca\u0012C\u0002I!aa\u0007B$\u0005\u0004\u0011BA\u0002\u0010\u0003H\t\u0007!\u0003\u0002\u0004\"\u0005\u000f\u0012\rA\u0005\u0003\u0007I\t\u001d#\u0019\u0001\n\u0005\r\u001d\u00129E1\u0001\u0013\t\u0019Q#q\tb\u0001%\u00111QFa\u0012C\u0002I!a\u0001\rB$\u0005\u0004\u0011BAB\u001a\u0003H\t\u0007!\u0003\u0002\u00047\u0005\u000f\u0012\rA\u0005\u0003\u0007s\t\u001d#\u0019\u0001\n\u0005\rq\u00129E1\u0001\u0013\t\u0019y$q\tb\u0001%\u00111!Ia\u0012C\u0002I!a!\u0012B$\u0005\u0004\u0011\u0002\"\u0003B:\u0001E\u0005I\u0011\u0001B;\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ*\"Ea\u001e\u0003|\tu$q\u0010BA\u0005\u0007\u0013)Ia\"\u0003\n\n-%Q\u0012BH\u0005#\u0013\u0019J!&\u0003\u0018\neUC\u0001B=U\ry\u00121\u001e\u0003\u0007#\tE$\u0019\u0001\n\u0005\rm\u0011\tH1\u0001\u0013\t\u0019q\"\u0011\u000fb\u0001%\u00111\u0011E!\u001dC\u0002I!a\u0001\nB9\u0005\u0004\u0011BAB\u0014\u0003r\t\u0007!\u0003\u0002\u0004+\u0005c\u0012\rA\u0005\u0003\u0007[\tE$\u0019\u0001\n\u0005\rA\u0012\tH1\u0001\u0013\t\u0019\u0019$\u0011\u000fb\u0001%\u00111aG!\u001dC\u0002I!a!\u000fB9\u0005\u0004\u0011BA\u0002\u001f\u0003r\t\u0007!\u0003\u0002\u0004@\u0005c\u0012\rA\u0005\u0003\u0007\u0005\nE$\u0019\u0001\n\u0005\r\u0015\u0013\tH1\u0001\u0013\u0011%\u0011i\nAI\u0001\n\u0003\u0011y*\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0016E\t\u0005&Q\u0015BT\u0005S\u0013YK!,\u00030\nE&1\u0017B[\u0005o\u0013ILa/\u0003>\n}&\u0011\u0019Bb+\t\u0011\u0019KK\u0002#\u0003W$a!\u0005BN\u0005\u0004\u0011BAB\u000e\u0003\u001c\n\u0007!\u0003\u0002\u0004\u001f\u00057\u0013\rA\u0005\u0003\u0007C\tm%\u0019\u0001\n\u0005\r\u0011\u0012YJ1\u0001\u0013\t\u00199#1\u0014b\u0001%\u00111!Fa'C\u0002I!a!\fBN\u0005\u0004\u0011BA\u0002\u0019\u0003\u001c\n\u0007!\u0003\u0002\u00044\u00057\u0013\rA\u0005\u0003\u0007m\tm%\u0019\u0001\n\u0005\re\u0012YJ1\u0001\u0013\t\u0019a$1\u0014b\u0001%\u00111qHa'C\u0002I!aA\u0011BN\u0005\u0004\u0011BAB#\u0003\u001c\n\u0007!\u0003C\u0005\u0003H\u0002\t\n\u0011\"\u0001\u0003J\u0006q1m\u001c9zI\u0011,g-Y;mi\u00122TC\tBf\u0005\u001f\u0014\tNa5\u0003V\n]'\u0011\u001cBn\u0005;\u0014yN!9\u0003d\n\u0015(q\u001dBu\u0005W\u0014i/\u0006\u0002\u0003N*\u001aQ%a;\u0005\rE\u0011)M1\u0001\u0013\t\u0019Y\"Q\u0019b\u0001%\u00111aD!2C\u0002I!a!\tBc\u0005\u0004\u0011BA\u0002\u0013\u0003F\n\u0007!\u0003\u0002\u0004(\u0005\u000b\u0014\rA\u0005\u0003\u0007U\t\u0015'\u0019\u0001\n\u0005\r5\u0012)M1\u0001\u0013\t\u0019\u0001$Q\u0019b\u0001%\u001111G!2C\u0002I!aA\u000eBc\u0005\u0004\u0011BAB\u001d\u0003F\n\u0007!\u0003\u0002\u0004=\u0005\u000b\u0014\rA\u0005\u0003\u0007\u007f\t\u0015'\u0019\u0001\n\u0005\r\t\u0013)M1\u0001\u0013\t\u0019)%Q\u0019b\u0001%!I!\u0011\u001f\u0001\u0012\u0002\u0013\u0005!1_\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00138+\t\u0012)P!?\u0003|\nu(q`B\u0001\u0007\u0007\u0019)aa\u0002\u0004\n\r-1QBB\b\u0007#\u0019\u0019b!\u0006\u0004\u0018U\u0011!q\u001f\u0016\u0004Q\u0005-HAB\t\u0003p\n\u0007!\u0003\u0002\u0004\u001c\u0005_\u0014\rA\u0005\u0003\u0007=\t=(\u0019\u0001\n\u0005\r\u0005\u0012yO1\u0001\u0013\t\u0019!#q\u001eb\u0001%\u00111qEa<C\u0002I!aA\u000bBx\u0005\u0004\u0011BAB\u0017\u0003p\n\u0007!\u0003\u0002\u00041\u0005_\u0014\rA\u0005\u0003\u0007g\t=(\u0019\u0001\n\u0005\rY\u0012yO1\u0001\u0013\t\u0019I$q\u001eb\u0001%\u00111AHa<C\u0002I!aa\u0010Bx\u0005\u0004\u0011BA\u0002\"\u0003p\n\u0007!\u0003\u0002\u0004F\u0005_\u0014\rA\u0005\u0005\n\u00077\u0001\u0011\u0013!C\u0001\u0007;\tabY8qs\u0012\"WMZ1vYR$\u0003(\u0006\u0012\u0004 \r\r2QEB\u0014\u0007S\u0019Yc!\f\u00040\rE21GB\u001b\u0007o\u0019Ida\u000f\u0004>\r}2\u0011I\u000b\u0003\u0007CQ3aKAv\t\u0019\t2\u0011\u0004b\u0001%\u001111d!\u0007C\u0002I!aAHB\r\u0005\u0004\u0011BAB\u0011\u0004\u001a\t\u0007!\u0003\u0002\u0004%\u00073\u0011\rA\u0005\u0003\u0007O\re!\u0019\u0001\n\u0005\r)\u001aIB1\u0001\u0013\t\u0019i3\u0011\u0004b\u0001%\u00111\u0001g!\u0007C\u0002I!aaMB\r\u0005\u0004\u0011BA\u0002\u001c\u0004\u001a\t\u0007!\u0003\u0002\u0004:\u00073\u0011\rA\u0005\u0003\u0007y\re!\u0019\u0001\n\u0005\r}\u001aIB1\u0001\u0013\t\u0019\u00115\u0011\u0004b\u0001%\u00111Qi!\u0007C\u0002IA\u0011b!\u0012\u0001#\u0003%\taa\u0012\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%sU\u00113\u0011JB'\u0007\u001f\u001a\tfa\u0015\u0004V\r]3\u0011LB.\u0007;\u001ayf!\u0019\u0004d\r\u00154qMB5\u0007W*\"aa\u0013+\u00079\nY\u000f\u0002\u0004\u0012\u0007\u0007\u0012\rA\u0005\u0003\u00077\r\r#\u0019\u0001\n\u0005\ry\u0019\u0019E1\u0001\u0013\t\u0019\t31\tb\u0001%\u00111Aea\u0011C\u0002I!aaJB\"\u0005\u0004\u0011BA\u0002\u0016\u0004D\t\u0007!\u0003\u0002\u0004.\u0007\u0007\u0012\rA\u0005\u0003\u0007a\r\r#\u0019\u0001\n\u0005\rM\u001a\u0019E1\u0001\u0013\t\u0019141\tb\u0001%\u00111\u0011ha\u0011C\u0002I!a\u0001PB\"\u0005\u0004\u0011BAB \u0004D\t\u0007!\u0003\u0002\u0004C\u0007\u0007\u0012\rA\u0005\u0003\u0007\u000b\u000e\r#\u0019\u0001\n\t\u0013\r=\u0004!%A\u0005\u0002\rE\u0014aD2paf$C-\u001a4bk2$H%\r\u0019\u0016E\rM4qOB=\u0007w\u001aiha \u0004\u0002\u000e\r5QQBD\u0007\u0013\u001bYi!$\u0004\u0010\u000eE51SBK+\t\u0019)HK\u00022\u0003W$a!EB7\u0005\u0004\u0011BAB\u000e\u0004n\t\u0007!\u0003\u0002\u0004\u001f\u0007[\u0012\rA\u0005\u0003\u0007C\r5$\u0019\u0001\n\u0005\r\u0011\u001aiG1\u0001\u0013\t\u001993Q\u000eb\u0001%\u00111!f!\u001cC\u0002I!a!LB7\u0005\u0004\u0011BA\u0002\u0019\u0004n\t\u0007!\u0003\u0002\u00044\u0007[\u0012\rA\u0005\u0003\u0007m\r5$\u0019\u0001\n\u0005\re\u001aiG1\u0001\u0013\t\u0019a4Q\u000eb\u0001%\u00111qh!\u001cC\u0002I!aAQB7\u0005\u0004\u0011BAB#\u0004n\t\u0007!\u0003C\u0005\u0004\u001a\u0002\t\n\u0011\"\u0001\u0004\u001c\u0006y1m\u001c9zI\u0011,g-Y;mi\u0012\n\u0014'\u0006\u0012\u0004\u001e\u000e\u000561UBS\u0007O\u001bIka+\u0004.\u000e=6\u0011WBZ\u0007k\u001b9l!/\u0004<\u000eu6qX\u000b\u0003\u0007?S3\u0001NAv\t\u0019\t2q\u0013b\u0001%\u001111da&C\u0002I!aAHBL\u0005\u0004\u0011BAB\u0011\u0004\u0018\n\u0007!\u0003\u0002\u0004%\u0007/\u0013\rA\u0005\u0003\u0007O\r]%\u0019\u0001\n\u0005\r)\u001a9J1\u0001\u0013\t\u0019i3q\u0013b\u0001%\u00111\u0001ga&C\u0002I!aaMBL\u0005\u0004\u0011BA\u0002\u001c\u0004\u0018\n\u0007!\u0003\u0002\u0004:\u0007/\u0013\rA\u0005\u0003\u0007y\r]%\u0019\u0001\n\u0005\r}\u001a9J1\u0001\u0013\t\u0019\u00115q\u0013b\u0001%\u00111Qia&C\u0002IA\u0011ba1\u0001#\u0003%\ta!2\u0002\u001f\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cI*\"ea2\u0004L\u000e57qZBi\u0007'\u001c)na6\u0004Z\u000em7Q\\Bp\u0007C\u001c\u0019o!:\u0004h\u000e%XCABeU\r9\u00141\u001e\u0003\u0007#\r\u0005'\u0019\u0001\n\u0005\rm\u0019\tM1\u0001\u0013\t\u0019q2\u0011\u0019b\u0001%\u00111\u0011e!1C\u0002I!a\u0001JBa\u0005\u0004\u0011BAB\u0014\u0004B\n\u0007!\u0003\u0002\u0004+\u0007\u0003\u0014\rA\u0005\u0003\u0007[\r\u0005'\u0019\u0001\n\u0005\rA\u001a\tM1\u0001\u0013\t\u0019\u00194\u0011\u0019b\u0001%\u00111ag!1C\u0002I!a!OBa\u0005\u0004\u0011BA\u0002\u001f\u0004B\n\u0007!\u0003\u0002\u0004@\u0007\u0003\u0014\rA\u0005\u0003\u0007\u0005\u000e\u0005'\u0019\u0001\n\u0005\r\u0015\u001b\tM1\u0001\u0013\u0011%\u0019i\u000fAI\u0001\n\u0003\u0019y/A\bd_BLH\u0005Z3gCVdG\u000fJ\u00194+\t\u001a\tp!>\u0004x\u000ee81`B\u007f\u0007\u007f$\t\u0001b\u0001\u0005\u0006\u0011\u001dA\u0011\u0002C\u0006\t\u001b!y\u0001\"\u0005\u0005\u0014U\u001111\u001f\u0016\u0004u\u0005-HAB\t\u0004l\n\u0007!\u0003\u0002\u0004\u001c\u0007W\u0014\rA\u0005\u0003\u0007=\r-(\u0019\u0001\n\u0005\r\u0005\u001aYO1\u0001\u0013\t\u0019!31\u001eb\u0001%\u00111qea;C\u0002I!aAKBv\u0005\u0004\u0011BAB\u0017\u0004l\n\u0007!\u0003\u0002\u00041\u0007W\u0014\rA\u0005\u0003\u0007g\r-(\u0019\u0001\n\u0005\rY\u001aYO1\u0001\u0013\t\u0019I41\u001eb\u0001%\u00111Aha;C\u0002I!aaPBv\u0005\u0004\u0011BA\u0002\"\u0004l\n\u0007!\u0003\u0002\u0004F\u0007W\u0014\rA\u0005\u0005\n\t/\u0001\u0011\u0013!C\u0001\t3\tqbY8qs\u0012\"WMZ1vYR$\u0013\u0007N\u000b#\t7!y\u0002\"\t\u0005$\u0011\u0015Bq\u0005C\u0015\tW!i\u0003b\f\u00052\u0011MBQ\u0007C\u001c\ts!Y\u0004\"\u0010\u0016\u0005\u0011u!fA\u001f\u0002l\u00121\u0011\u0003\"\u0006C\u0002I!aa\u0007C\u000b\u0005\u0004\u0011BA\u0002\u0010\u0005\u0016\t\u0007!\u0003\u0002\u0004\"\t+\u0011\rA\u0005\u0003\u0007I\u0011U!\u0019\u0001\n\u0005\r\u001d\")B1\u0001\u0013\t\u0019QCQ\u0003b\u0001%\u00111Q\u0006\"\u0006C\u0002I!a\u0001\rC\u000b\u0005\u0004\u0011BAB\u001a\u0005\u0016\t\u0007!\u0003\u0002\u00047\t+\u0011\rA\u0005\u0003\u0007s\u0011U!\u0019\u0001\n\u0005\rq\")B1\u0001\u0013\t\u0019yDQ\u0003b\u0001%\u00111!\t\"\u0006C\u0002I!a!\u0012C\u000b\u0005\u0004\u0011\u0002\"\u0003C!\u0001E\u0005I\u0011\u0001C\"\u0003=\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*TC\tC#\t\u0013\"Y\u0005\"\u0014\u0005P\u0011EC1\u000bC+\t/\"I\u0006b\u0017\u0005^\u0011}C\u0011\rC2\tK\"9'\u0006\u0002\u0005H)\u001a\u0001)a;\u0005\rE!yD1\u0001\u0013\t\u0019YBq\bb\u0001%\u00111a\u0004b\u0010C\u0002I!a!\tC \u0005\u0004\u0011BA\u0002\u0013\u0005@\t\u0007!\u0003\u0002\u0004(\t\u007f\u0011\rA\u0005\u0003\u0007U\u0011}\"\u0019\u0001\n\u0005\r5\"yD1\u0001\u0013\t\u0019\u0001Dq\bb\u0001%\u001111\u0007b\u0010C\u0002I!aA\u000eC \u0005\u0004\u0011BAB\u001d\u0005@\t\u0007!\u0003\u0002\u0004=\t\u007f\u0011\rA\u0005\u0003\u0007\u007f\u0011}\"\u0019\u0001\n\u0005\r\t#yD1\u0001\u0013\t\u0019)Eq\bb\u0001%!IA1\u000e\u0001\u0012\u0002\u0013\u0005AQN\u0001\u0010G>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132mU\u0011Cq\u000eC:\tk\"9\b\"\u001f\u0005|\u0011uDq\u0010CA\t\u0007#)\tb\"\u0005\n\u0012-EQ\u0012CH\t#+\"\u0001\"\u001d+\u0007\r\u000bY\u000f\u0002\u0004\u0012\tS\u0012\rA\u0005\u0003\u00077\u0011%$\u0019\u0001\n\u0005\ry!IG1\u0001\u0013\t\u0019\tC\u0011\u000eb\u0001%\u00111A\u0005\"\u001bC\u0002I!aa\nC5\u0005\u0004\u0011BA\u0002\u0016\u0005j\t\u0007!\u0003\u0002\u0004.\tS\u0012\rA\u0005\u0003\u0007a\u0011%$\u0019\u0001\n\u0005\rM\"IG1\u0001\u0013\t\u00191D\u0011\u000eb\u0001%\u00111\u0011\b\"\u001bC\u0002I!a\u0001\u0010C5\u0005\u0004\u0011BAB \u0005j\t\u0007!\u0003\u0002\u0004C\tS\u0012\rA\u0005\u0003\u0007\u000b\u0012%$\u0019\u0001\n\t\u0013\u0011U\u0005!!A\u0005B\u0011]\u0015!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002h!IA1\u0014\u0001\u0002\u0002\u0013\u0005CQT\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011Aq\u0014\t\u0006\tC#9KF\u0007\u0003\tGS1\u0001\"*\u0003\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\tS#\u0019K\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0011%!i\u000bAA\u0001\n\u0003!y+\u0001\u0005dC:,\u0015/^1m)\u0011!\t\fb.\u0011\u0007!!\u0019,C\u0002\u00056\n\u0011qAQ8pY\u0016\fg\u000eC\u0005\u0005:\u0012-\u0016\u0011!a\u0001-\u0005\u0019\u0001\u0010J\u0019\t\u0013\u0011u\u0006!!A\u0005B\u0011}\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0011\u0005\u0007c\u0001\u0005\u0005D&\u0019AQ\u0019\u0002\u0003\u0007%sG\u000fC\u0005\u0005J\u0002\t\t\u0011\"\u0011\u0005L\u00061Q-];bYN$B\u0001\"-\u0005N\"IA\u0011\u0018Cd\u0003\u0003\u0005\rA\u0006\u0015\b\u0001\u0011EGq\u001bCn!\rAA1[\u0005\u0004\t+\u0014!!\u00063faJ,7-\u0019;fI&s\u0007.\u001a:ji\u0006t7-Z\u0011\u0003\t3\fa\u0006V;qY\u0016\u001c\be^5mY\u0002\u0012W\rI7bI\u0016\u0004c-\u001b8bY\u0002Jg\u000eI1!MV$XO]3!m\u0016\u00148/[8o]\u0005\u0012AQ\\\u0001\u0007e9\n\u0014G\f\u0019\b\u0013\u0011\u0005(!!A\t\u0002\u0011\r\u0018a\u0002+va2,\u0017G\u000e\t\u0004\u0011\u0011\u0015h\u0001C\u0001\u0003\u0003\u0003E\t\u0001b:\u0014\t\u0011\u0015x!\u0013\u0005\t\u0003w!)\u000f\"\u0001\u0005lR\u0011A1\u001d\u0005\u000b\u0003G\")/!A\u0005F\u0005\u0015\u0004B\u0003Cy\tK\f\t\u0011\"!\u0005t\u0006)\u0011\r\u001d9msV\u0011CQ\u001fC~\t\u007f,\u0019!b\u0002\u0006\f\u0015=Q1CC\f\u000b7)y\"b\t\u0006(\u0015-RqFC\u001a\u000bo!\"\u0005b>\u0006:\u0015mRQHC \u000b\u0003*\u0019%\"\u0012\u0006H\u0015%S1JC'\u000b\u001f*\t&b\u0015\u0006V\u0015]\u0003C\t\u0005\u0001\ts$i0\"\u0001\u0006\u0006\u0015%QQBC\t\u000b+)I\"\"\b\u0006\"\u0015\u0015R\u0011FC\u0017\u000bc))\u0004E\u0002\u0010\tw$a!\u0005Cx\u0005\u0004\u0011\u0002cA\b\u0005\u0000\u001211\u0004b<C\u0002I\u00012aDC\u0002\t\u0019qBq\u001eb\u0001%A\u0019q\"b\u0002\u0005\r\u0005\"yO1\u0001\u0013!\ryQ1\u0002\u0003\u0007I\u0011=(\u0019\u0001\n\u0011\u0007=)y\u0001\u0002\u0004(\t_\u0014\rA\u0005\t\u0004\u001f\u0015MAA\u0002\u0016\u0005p\n\u0007!\u0003E\u0002\u0010\u000b/!a!\fCx\u0005\u0004\u0011\u0002cA\b\u0006\u001c\u00111\u0001\u0007b<C\u0002I\u00012aDC\u0010\t\u0019\u0019Dq\u001eb\u0001%A\u0019q\"b\t\u0005\rY\"yO1\u0001\u0013!\ryQq\u0005\u0003\u0007s\u0011=(\u0019\u0001\n\u0011\u0007=)Y\u0003\u0002\u0004=\t_\u0014\rA\u0005\t\u0004\u001f\u0015=BAB \u0005p\n\u0007!\u0003E\u0002\u0010\u000bg!aA\u0011Cx\u0005\u0004\u0011\u0002cA\b\u00068\u00111Q\tb<C\u0002IAq!\u0014Cx\u0001\u0004!I\u0010C\u0004S\t_\u0004\r\u0001\"@\t\u000f]#y\u000f1\u0001\u0006\u0002!9A\fb<A\u0002\u0015\u0015\u0001bB1\u0005p\u0002\u0007Q\u0011\u0002\u0005\bM\u0012=\b\u0019AC\u0007\u0011\u001dYGq\u001ea\u0001\u000b#Aq\u0001\u001dCx\u0001\u0004))\u0002C\u0004v\t_\u0004\r!\"\u0007\t\u000fi$y\u000f1\u0001\u0006\u001e!9q\u0010b<A\u0002\u0015\u0005\u0002\u0002CA\u0005\t_\u0004\r!\"\n\t\u0011\u0005MAq\u001ea\u0001\u000bSA\u0001\"!\b\u0005p\u0002\u0007QQ\u0006\u0005\t\u0003O!y\u000f1\u0001\u00062!A\u0011\u0011\u0007Cx\u0001\u0004))\u0004\u0003\u0006\u0006\\\u0011\u0015\u0018\u0011!CA\u000b;\nq!\u001e8baBd\u00170\u0006\u0012\u0006`\u0015-TqNC:\u000bo*Y(b \u0006\u0004\u0016\u001dU1RCH\u000b'+9*b'\u0006 \u0016\rVq\u0015\u000b\u0005\u000bC*I\u000bE\u0003\t\u000bG*9'C\u0002\u0006f\t\u0011aa\u00149uS>t\u0007C\t\u0005\u0001\u000bS*i'\"\u001d\u0006v\u0015eTQPCA\u000b\u000b+I)\"$\u0006\u0012\u0016UU\u0011TCO\u000bC+)\u000bE\u0002\u0010\u000bW\"a!EC-\u0005\u0004\u0011\u0002cA\b\u0006p\u001111$\"\u0017C\u0002I\u00012aDC:\t\u0019qR\u0011\fb\u0001%A\u0019q\"b\u001e\u0005\r\u0005*IF1\u0001\u0013!\ryQ1\u0010\u0003\u0007I\u0015e#\u0019\u0001\n\u0011\u0007=)y\b\u0002\u0004(\u000b3\u0012\rA\u0005\t\u0004\u001f\u0015\rEA\u0002\u0016\u0006Z\t\u0007!\u0003E\u0002\u0010\u000b\u000f#a!LC-\u0005\u0004\u0011\u0002cA\b\u0006\f\u00121\u0001'\"\u0017C\u0002I\u00012aDCH\t\u0019\u0019T\u0011\fb\u0001%A\u0019q\"b%\u0005\rY*IF1\u0001\u0013!\ryQq\u0013\u0003\u0007s\u0015e#\u0019\u0001\n\u0011\u0007=)Y\n\u0002\u0004=\u000b3\u0012\rA\u0005\t\u0004\u001f\u0015}EAB \u0006Z\t\u0007!\u0003E\u0002\u0010\u000bG#aAQC-\u0005\u0004\u0011\u0002cA\b\u0006(\u00121Q)\"\u0017C\u0002IA!\"b+\u0006Z\u0005\u0005\t\u0019AC4\u0003\rAH\u0005\r\u0005\u000b\u000b_#)/!A\u0005\n\u0015E\u0016a\u0003:fC\u0012\u0014Vm]8mm\u0016$\"!b-\u0011\t\u0005%TQW\u0005\u0005\u000bo\u000bYG\u0001\u0004PE*,7\r\u001e")
public class Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>
implements Product16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>,
Serializable {
    private final T1 _1;
    private final T2 _2;
    private final T3 _3;
    private final T4 _4;
    private final T5 _5;
    private final T6 _6;
    private final T7 _7;
    private final T8 _8;
    private final T9 _9;
    private final T10 _10;
    private final T11 _11;
    private final T12 _12;
    private final T13 _13;
    private final T14 _14;
    private final T15 _15;
    private final T16 _16;

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> Option<Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>> unapply(Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> tuple16) {
        return Tuple16$.MODULE$.unapply(tuple16);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> apply(T1 T1, T2 T2, T3 T3, T4 T4, T5 T5, T6 T6, T7 T7, T8 T8, T9 T9, T10 T10, T11 T11, T12 T12, T13 T13, T14 T14, T15 T15, T16 T16) {
        return Tuple16$.MODULE$.apply(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16);
    }

    @Override
    public int productArity() {
        return Product16$class.productArity(this);
    }

    @Override
    public Object productElement(int n) throws IndexOutOfBoundsException {
        return Product16$class.productElement(this, n);
    }

    @Override
    public T1 _1() {
        return this._1;
    }

    @Override
    public T2 _2() {
        return this._2;
    }

    @Override
    public T3 _3() {
        return this._3;
    }

    @Override
    public T4 _4() {
        return this._4;
    }

    @Override
    public T5 _5() {
        return this._5;
    }

    @Override
    public T6 _6() {
        return this._6;
    }

    @Override
    public T7 _7() {
        return this._7;
    }

    @Override
    public T8 _8() {
        return this._8;
    }

    @Override
    public T9 _9() {
        return this._9;
    }

    @Override
    public T10 _10() {
        return this._10;
    }

    @Override
    public T11 _11() {
        return this._11;
    }

    @Override
    public T12 _12() {
        return this._12;
    }

    @Override
    public T13 _13() {
        return this._13;
    }

    @Override
    public T14 _14() {
        return this._14;
    }

    @Override
    public T15 _15() {
        return this._15;
    }

    @Override
    public T16 _16() {
        return this._16;
    }

    public String toString() {
        return new StringBuilder().append((Object)"(").append(this._1()).append((Object)",").append(this._2()).append((Object)",").append(this._3()).append((Object)",").append(this._4()).append((Object)",").append(this._5()).append((Object)",").append(this._6()).append((Object)",").append(this._7()).append((Object)",").append(this._8()).append((Object)",").append(this._9()).append((Object)",").append(this._10()).append((Object)",").append(this._11()).append((Object)",").append(this._12()).append((Object)",").append(this._13()).append((Object)",").append(this._14()).append((Object)",").append(this._15()).append((Object)",").append(this._16()).append((Object)")").toString();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> copy(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16) {
        return new Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16);
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> T1 copy$default$1() {
        return this._1();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> T2 copy$default$2() {
        return this._2();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> T3 copy$default$3() {
        return this._3();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> T4 copy$default$4() {
        return this._4();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> T5 copy$default$5() {
        return this._5();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> T6 copy$default$6() {
        return this._6();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> T7 copy$default$7() {
        return this._7();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> T8 copy$default$8() {
        return this._8();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> T9 copy$default$9() {
        return this._9();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> T10 copy$default$10() {
        return this._10();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> T11 copy$default$11() {
        return this._11();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> T12 copy$default$12() {
        return this._12();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> T13 copy$default$13() {
        return this._13();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> T14 copy$default$14() {
        return this._14();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> T15 copy$default$15() {
        return this._15();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> T16 copy$default$16() {
        return this._16();
    }

    @Override
    public String productPrefix() {
        return "Tuple16";
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof Tuple16;
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
        boolean bl;
        boolean bl2;
        boolean bl3;
        boolean bl4;
        boolean bl5;
        boolean bl6;
        boolean bl7;
        boolean bl8;
        boolean bl9;
        boolean bl10;
        boolean bl11;
        boolean bl12;
        boolean bl13;
        boolean bl14;
        boolean bl15;
        boolean bl16;
        if (this == x$1) return true;
        if (!(x$1 instanceof Tuple16)) return false;
        boolean bl17 = true;
        if (!bl17) return false;
        Tuple16 tuple16 = (Tuple16)x$1;
        T1 T1 = tuple16._1();
        T1 T12 = this._1();
        if (T12 == T1) {
            bl16 = true;
        } else {
            if (T12 == null) {
                return false;
            }
            bl16 = T12 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T12, T1) : (T12 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T12, T1) : T12.equals(T1));
        }
        if (!bl16) return false;
        T2 T2 = tuple16._2();
        T2 T22 = this._2();
        if (T22 == T2) {
            bl15 = true;
        } else {
            if (T22 == null) {
                return false;
            }
            bl15 = T22 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T22, T2) : (T22 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T22, T2) : T22.equals(T2));
        }
        if (!bl15) return false;
        T3 T3 = tuple16._3();
        T3 T32 = this._3();
        if (T32 == T3) {
            bl14 = true;
        } else {
            if (T32 == null) {
                return false;
            }
            bl14 = T32 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T32, T3) : (T32 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T32, T3) : T32.equals(T3));
        }
        if (!bl14) return false;
        T4 T4 = tuple16._4();
        T4 T42 = this._4();
        if (T42 == T4) {
            bl13 = true;
        } else {
            if (T42 == null) {
                return false;
            }
            bl13 = T42 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T42, T4) : (T42 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T42, T4) : T42.equals(T4));
        }
        if (!bl13) return false;
        T5 T5 = tuple16._5();
        T5 T52 = this._5();
        if (T52 == T5) {
            bl12 = true;
        } else {
            if (T52 == null) {
                return false;
            }
            bl12 = T52 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T52, T5) : (T52 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T52, T5) : T52.equals(T5));
        }
        if (!bl12) return false;
        T6 T6 = tuple16._6();
        T6 T62 = this._6();
        if (T62 == T6) {
            bl11 = true;
        } else {
            if (T62 == null) {
                return false;
            }
            bl11 = T62 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T62, T6) : (T62 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T62, T6) : T62.equals(T6));
        }
        if (!bl11) return false;
        T7 T7 = tuple16._7();
        T7 T72 = this._7();
        if (T72 == T7) {
            bl10 = true;
        } else {
            if (T72 == null) {
                return false;
            }
            bl10 = T72 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T72, T7) : (T72 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T72, T7) : T72.equals(T7));
        }
        if (!bl10) return false;
        T8 T8 = tuple16._8();
        T8 T82 = this._8();
        if (T82 == T8) {
            bl9 = true;
        } else {
            if (T82 == null) {
                return false;
            }
            bl9 = T82 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T82, T8) : (T82 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T82, T8) : T82.equals(T8));
        }
        if (!bl9) return false;
        T9 T9 = tuple16._9();
        T9 T92 = this._9();
        if (T92 == T9) {
            bl8 = true;
        } else {
            if (T92 == null) {
                return false;
            }
            bl8 = T92 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T92, T9) : (T92 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T92, T9) : T92.equals(T9));
        }
        if (!bl8) return false;
        T10 T10 = tuple16._10();
        T10 T102 = this._10();
        if (T102 == T10) {
            bl7 = true;
        } else {
            if (T102 == null) {
                return false;
            }
            bl7 = T102 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T102, T10) : (T102 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T102, T10) : T102.equals(T10));
        }
        if (!bl7) return false;
        T11 T11 = tuple16._11();
        T11 T112 = this._11();
        if (T112 == T11) {
            bl6 = true;
        } else {
            if (T112 == null) {
                return false;
            }
            bl6 = T112 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T112, T11) : (T112 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T112, T11) : T112.equals(T11));
        }
        if (!bl6) return false;
        T12 T122 = tuple16._12();
        T12 T123 = this._12();
        if (T123 == T122) {
            bl5 = true;
        } else {
            if (T123 == null) {
                return false;
            }
            bl5 = T123 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T123, T122) : (T123 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T123, T122) : T123.equals(T122));
        }
        if (!bl5) return false;
        T13 T13 = tuple16._13();
        T13 T132 = this._13();
        if (T132 == T13) {
            bl4 = true;
        } else {
            if (T132 == null) {
                return false;
            }
            bl4 = T132 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T132, T13) : (T132 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T132, T13) : T132.equals(T13));
        }
        if (!bl4) return false;
        T14 T14 = tuple16._14();
        T14 T142 = this._14();
        if (T142 == T14) {
            bl3 = true;
        } else {
            if (T142 == null) {
                return false;
            }
            bl3 = T142 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T142, T14) : (T142 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T142, T14) : T142.equals(T14));
        }
        if (!bl3) return false;
        T15 T15 = tuple16._15();
        T15 T152 = this._15();
        if (T152 == T15) {
            bl2 = true;
        } else {
            if (T152 == null) {
                return false;
            }
            bl2 = T152 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T152, T15) : (T152 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T152, T15) : T152.equals(T15));
        }
        if (!bl2) return false;
        T16 T16 = tuple16._16();
        T16 T162 = this._16();
        if (T162 == T16) {
            bl = true;
        } else {
            if (T162 == null) {
                return false;
            }
            bl = T162 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T162, T16) : (T162 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T162, T16) : T162.equals(T16));
        }
        if (!bl) return false;
        if (!tuple16.canEqual(this)) return false;
        return true;
    }

    public Tuple16(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
        this._5 = _5;
        this._6 = _6;
        this._7 = _7;
        this._8 = _8;
        this._9 = _9;
        this._10 = _10;
        this._11 = _11;
        this._12 = _12;
        this._13 = _13;
        this._14 = _14;
        this._15 = _15;
        this._16 = _16;
        Product$class.$init$(this);
        Product16$class.$init$(this);
    }
}

