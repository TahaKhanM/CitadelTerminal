/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Option;
import scala.Product$class;
import scala.Product18;
import scala.Product18$class;
import scala.Serializable;
import scala.Tuple18$;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0019ee\u0001B\u0001\u0003\u0001\u0016\u0011q\u0001V;qY\u0016\f\u0004HC\u0001\u0004\u0003\u0015\u00198-\u00197b\u0007\u0001)2C\u0002\t\u001b;\u0001\u001ac%\u000b\u00170eUB4HP!E\u000f*\u001bR\u0001A\u0004\f\u0019>\u0003\"\u0001C\u0005\u000e\u0003\tI!A\u0003\u0002\u0003\r\u0005s\u0017PU3g!QAABD\r\u001d?\t*\u0003f\u000b\u00182i]RT\bQ\"G\u0013&\u0011QB\u0001\u0002\n!J|G-^2uca\u0002\"a\u0004\t\r\u0001\u00111\u0011\u0003\u0001CC\u0002I\u0011!\u0001V\u0019\u0012\u0005M1\u0002C\u0001\u0005\u0015\u0013\t)\"AA\u0004O_RD\u0017N\\4\u0011\u0005!9\u0012B\u0001\r\u0003\u0005\r\te.\u001f\t\u0003\u001fi!aa\u0007\u0001\u0005\u0006\u0004\u0011\"A\u0001+3!\tyQ\u0004\u0002\u0004\u001f\u0001\u0011\u0015\rA\u0005\u0002\u0003)N\u0002\"a\u0004\u0011\u0005\r\u0005\u0002AQ1\u0001\u0013\u0005\t!F\u0007\u0005\u0002\u0010G\u00111A\u0005\u0001CC\u0002I\u0011!\u0001V\u001b\u0011\u0005=1CAB\u0014\u0001\t\u000b\u0007!C\u0001\u0002UmA\u0011q\"\u000b\u0003\u0007U\u0001!)\u0019\u0001\n\u0003\u0005Q;\u0004CA\b-\t\u0019i\u0003\u0001\"b\u0001%\t\u0011A\u000b\u000f\t\u0003\u001f=\"a\u0001\r\u0001\u0005\u0006\u0004\u0011\"A\u0001+:!\ty!\u0007\u0002\u00044\u0001\u0011\u0015\rA\u0005\u0002\u0004)F\u0002\u0004CA\b6\t\u00191\u0004\u0001\"b\u0001%\t\u0019A+M\u0019\u0011\u0005=ADAB\u001d\u0001\t\u000b\u0007!CA\u0002UcI\u0002\"aD\u001e\u0005\rq\u0002AQ1\u0001\u0013\u0005\r!\u0016g\r\t\u0003\u001fy\"aa\u0010\u0001\u0005\u0006\u0004\u0011\"a\u0001+2iA\u0011q\"\u0011\u0003\u0007\u0005\u0002!)\u0019\u0001\n\u0003\u0007Q\u000bT\u0007\u0005\u0002\u0010\t\u00121Q\t\u0001CC\u0002I\u00111\u0001V\u00197!\tyq\t\u0002\u0004I\u0001\u0011\u0015\rA\u0005\u0002\u0004)F:\u0004CA\bK\t\u0019Y\u0005\u0001\"b\u0001%\t\u0019A+\r\u001d\u0011\u0005!i\u0015B\u0001(\u0003\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\u0003)\n\u0005E\u0013!\u0001D*fe&\fG.\u001b>bE2,\u0007\u0002C*\u0001\u0005+\u0007I\u0011\u0001+\u0002\u0005}\u000bT#\u0001\b\t\u0011Y\u0003!\u0011#Q\u0001\n9\t1aX\u0019!\u0011!A\u0006A!f\u0001\n\u0003I\u0016AA03+\u0005I\u0002\u0002C.\u0001\u0005#\u0005\u000b\u0011B\r\u0002\u0007}\u0013\u0004\u0005\u0003\u0005^\u0001\tU\r\u0011\"\u0001_\u0003\ty6'F\u0001\u001d\u0011!\u0001\u0007A!E!\u0002\u0013a\u0012aA04A!A!\r\u0001BK\u0002\u0013\u00051-\u0001\u0002`iU\tq\u0004\u0003\u0005f\u0001\tE\t\u0015!\u0003 \u0003\ryF\u0007\t\u0005\tO\u0002\u0011)\u001a!C\u0001Q\u0006\u0011q,N\u000b\u0002E!A!\u000e\u0001B\tB\u0003%!%A\u0002`k\u0001B\u0001\u0002\u001c\u0001\u0003\u0016\u0004%\t!\\\u0001\u0003?Z*\u0012!\n\u0005\t_\u0002\u0011\t\u0012)A\u0005K\u0005\u0019qL\u000e\u0011\t\u0011E\u0004!Q3A\u0005\u0002I\f!aX\u001c\u0016\u0003!B\u0001\u0002\u001e\u0001\u0003\u0012\u0003\u0006I\u0001K\u0001\u0004?^\u0002\u0003\u0002\u0003<\u0001\u0005+\u0007I\u0011A<\u0002\u0005}CT#A\u0016\t\u0011e\u0004!\u0011#Q\u0001\n-\n1a\u0018\u001d!\u0011!Y\bA!f\u0001\n\u0003a\u0018AA0:+\u0005q\u0003\u0002\u0003@\u0001\u0005#\u0005\u000b\u0011\u0002\u0018\u0002\u0007}K\u0004\u0005\u0003\u0006\u0002\u0002\u0001\u0011)\u001a!C\u0001\u0003\u0007\t1aX\u00191+\u0005\t\u0004\"CA\u0004\u0001\tE\t\u0015!\u00032\u0003\u0011y\u0016\u0007\r\u0011\t\u0015\u0005-\u0001A!f\u0001\n\u0003\ti!A\u0002`cE*\u0012\u0001\u000e\u0005\n\u0003#\u0001!\u0011#Q\u0001\nQ\nAaX\u00192A!Q\u0011Q\u0003\u0001\u0003\u0016\u0004%\t!a\u0006\u0002\u0007}\u000b$'F\u00018\u0011%\tY\u0002\u0001B\tB\u0003%q'\u0001\u0003`cI\u0002\u0003BCA\u0010\u0001\tU\r\u0011\"\u0001\u0002\"\u0005\u0019q,M\u001a\u0016\u0003iB\u0011\"!\n\u0001\u0005#\u0005\u000b\u0011\u0002\u001e\u0002\t}\u000b4\u0007\t\u0005\u000b\u0003S\u0001!Q3A\u0005\u0002\u0005-\u0012aA02iU\tQ\bC\u0005\u00020\u0001\u0011\t\u0012)A\u0005{\u0005!q,\r\u001b!\u0011)\t\u0019\u0004\u0001BK\u0002\u0013\u0005\u0011QG\u0001\u0004?F*T#\u0001!\t\u0013\u0005e\u0002A!E!\u0002\u0013\u0001\u0015\u0001B02k\u0001B!\"!\u0010\u0001\u0005+\u0007I\u0011AA \u0003\ry\u0016GN\u000b\u0002\u0007\"I\u00111\t\u0001\u0003\u0012\u0003\u0006IaQ\u0001\u0005?F2\u0004\u0005\u0003\u0006\u0002H\u0001\u0011)\u001a!C\u0001\u0003\u0013\n1aX\u00198+\u00051\u0005\"CA'\u0001\tE\t\u0015!\u0003G\u0003\u0011y\u0016g\u000e\u0011\t\u0015\u0005E\u0003A!f\u0001\n\u0003\t\u0019&A\u0002`ca*\u0012!\u0013\u0005\n\u0003/\u0002!\u0011#Q\u0001\n%\u000bAaX\u00199A!9\u00111\f\u0001\u0005\u0002\u0005u\u0013A\u0002\u001fj]&$h\b\u0006\u0014\u0002`\u0005\u0005\u00141MA3\u0003O\nI'a\u001b\u0002n\u0005=\u0014\u0011OA:\u0003k\n9(!\u001f\u0002|\u0005u\u0014qPAA\u0003\u0007\u0003B\u0003\u0003\u0001\u000f3qy\"%\n\u0015,]E\"tGO\u001fA\u0007\u001aK\u0005BB*\u0002Z\u0001\u0007a\u0002\u0003\u0004Y\u00033\u0002\r!\u0007\u0005\u0007;\u0006e\u0003\u0019\u0001\u000f\t\r\t\fI\u00061\u0001 \u0011\u00199\u0017\u0011\fa\u0001E!1A.!\u0017A\u0002\u0015Ba!]A-\u0001\u0004A\u0003B\u0002<\u0002Z\u0001\u00071\u0006\u0003\u0004|\u00033\u0002\rA\f\u0005\b\u0003\u0003\tI\u00061\u00012\u0011\u001d\tY!!\u0017A\u0002QBq!!\u0006\u0002Z\u0001\u0007q\u0007C\u0004\u0002 \u0005e\u0003\u0019\u0001\u001e\t\u000f\u0005%\u0012\u0011\fa\u0001{!9\u00111GA-\u0001\u0004\u0001\u0005bBA\u001f\u00033\u0002\ra\u0011\u0005\b\u0003\u000f\nI\u00061\u0001G\u0011\u001d\t\t&!\u0017A\u0002%Cq!a\"\u0001\t\u0003\nI)\u0001\u0005u_N#(/\u001b8h)\t\tY\t\u0005\u0003\u0002\u000e\u0006]UBAAH\u0015\u0011\t\t*a%\u0002\t1\fgn\u001a\u0006\u0003\u0003+\u000bAA[1wC&!\u0011\u0011TAH\u0005\u0019\u0019FO]5oO\"I\u0011Q\u0014\u0001\u0002\u0002\u0013\u0005\u0011qT\u0001\u0005G>\u0004\u00180\u0006\u0014\u0002\"\u0006\u001d\u00161VAX\u0003g\u000b9,a/\u0002@\u0006\r\u0017qYAf\u0003\u001f\f\u0019.a6\u0002\\\u0006}\u00171]At\u0003W$b%a)\u0002n\u0006=\u0018\u0011_Az\u0003k\f90!?\u0002|\u0006u\u0018q B\u0001\u0005\u0007\u0011)Aa\u0002\u0003\n\t-!Q\u0002B\b!\u0019B\u0001!!*\u0002*\u00065\u0016\u0011WA[\u0003s\u000bi,!1\u0002F\u0006%\u0017QZAi\u0003+\fI.!8\u0002b\u0006\u0015\u0018\u0011\u001e\t\u0004\u001f\u0005\u001dFAB\t\u0002\u001c\n\u0007!\u0003E\u0002\u0010\u0003W#aaGAN\u0005\u0004\u0011\u0002cA\b\u00020\u00121a$a'C\u0002I\u00012aDAZ\t\u0019\t\u00131\u0014b\u0001%A\u0019q\"a.\u0005\r\u0011\nYJ1\u0001\u0013!\ry\u00111\u0018\u0003\u0007O\u0005m%\u0019\u0001\n\u0011\u0007=\ty\f\u0002\u0004+\u00037\u0013\rA\u0005\t\u0004\u001f\u0005\rGAB\u0017\u0002\u001c\n\u0007!\u0003E\u0002\u0010\u0003\u000f$a\u0001MAN\u0005\u0004\u0011\u0002cA\b\u0002L\u001211'a'C\u0002I\u00012aDAh\t\u00191\u00141\u0014b\u0001%A\u0019q\"a5\u0005\re\nYJ1\u0001\u0013!\ry\u0011q\u001b\u0003\u0007y\u0005m%\u0019\u0001\n\u0011\u0007=\tY\u000e\u0002\u0004@\u00037\u0013\rA\u0005\t\u0004\u001f\u0005}GA\u0002\"\u0002\u001c\n\u0007!\u0003E\u0002\u0010\u0003G$a!RAN\u0005\u0004\u0011\u0002cA\b\u0002h\u00121\u0001*a'C\u0002I\u00012aDAv\t\u0019Y\u00151\u0014b\u0001%!I1+a'\u0011\u0002\u0003\u0007\u0011Q\u0015\u0005\n1\u0006m\u0005\u0013!a\u0001\u0003SC\u0011\"XAN!\u0003\u0005\r!!,\t\u0013\t\fY\n%AA\u0002\u0005E\u0006\"C4\u0002\u001cB\u0005\t\u0019AA[\u0011%a\u00171\u0014I\u0001\u0002\u0004\tI\fC\u0005r\u00037\u0003\n\u00111\u0001\u0002>\"Ia/a'\u0011\u0002\u0003\u0007\u0011\u0011\u0019\u0005\nw\u0006m\u0005\u0013!a\u0001\u0003\u000bD!\"!\u0001\u0002\u001cB\u0005\t\u0019AAe\u0011)\tY!a'\u0011\u0002\u0003\u0007\u0011Q\u001a\u0005\u000b\u0003+\tY\n%AA\u0002\u0005E\u0007BCA\u0010\u00037\u0003\n\u00111\u0001\u0002V\"Q\u0011\u0011FAN!\u0003\u0005\r!!7\t\u0015\u0005M\u00121\u0014I\u0001\u0002\u0004\ti\u000e\u0003\u0006\u0002>\u0005m\u0005\u0013!a\u0001\u0003CD!\"a\u0012\u0002\u001cB\u0005\t\u0019AAs\u0011)\t\t&a'\u0011\u0002\u0003\u0007\u0011\u0011\u001e\u0005\n\u0005'\u0001\u0011\u0013!C\u0001\u0005+\tabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0014\u0003\u0018\t5\"q\u0006B\u0019\u0005g\u0011)Da\u000e\u0003:\tm\"Q\bB \u0005\u0003\u0012\u0019E!\u0012\u0003H\t%#1\nB'\u0005\u001f*\"A!\u0007+\u00079\u0011Yb\u000b\u0002\u0003\u001eA!!q\u0004B\u0015\u001b\t\u0011\tC\u0003\u0003\u0003$\t\u0015\u0012!C;oG\",7m[3e\u0015\r\u00119CA\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002\u0002B\u0016\u0005C\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u0019\t\"\u0011\u0003b\u0001%\u001111D!\u0005C\u0002I!aA\bB\t\u0005\u0004\u0011BAB\u0011\u0003\u0012\t\u0007!\u0003\u0002\u0004%\u0005#\u0011\rA\u0005\u0003\u0007O\tE!\u0019\u0001\n\u0005\r)\u0012\tB1\u0001\u0013\t\u0019i#\u0011\u0003b\u0001%\u00111\u0001G!\u0005C\u0002I!aa\rB\t\u0005\u0004\u0011BA\u0002\u001c\u0003\u0012\t\u0007!\u0003\u0002\u0004:\u0005#\u0011\rA\u0005\u0003\u0007y\tE!\u0019\u0001\n\u0005\r}\u0012\tB1\u0001\u0013\t\u0019\u0011%\u0011\u0003b\u0001%\u00111QI!\u0005C\u0002I!a\u0001\u0013B\t\u0005\u0004\u0011BAB&\u0003\u0012\t\u0007!\u0003C\u0005\u0003T\u0001\t\n\u0011\"\u0001\u0003V\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TC\nB,\u00057\u0012iFa\u0018\u0003b\t\r$Q\rB4\u0005S\u0012YG!\u001c\u0003p\tE$1\u000fB;\u0005o\u0012IHa\u001f\u0003~U\u0011!\u0011\f\u0016\u00043\tmAAB\t\u0003R\t\u0007!\u0003\u0002\u0004\u001c\u0005#\u0012\rA\u0005\u0003\u0007=\tE#\u0019\u0001\n\u0005\r\u0005\u0012\tF1\u0001\u0013\t\u0019!#\u0011\u000bb\u0001%\u00111qE!\u0015C\u0002I!aA\u000bB)\u0005\u0004\u0011BAB\u0017\u0003R\t\u0007!\u0003\u0002\u00041\u0005#\u0012\rA\u0005\u0003\u0007g\tE#\u0019\u0001\n\u0005\rY\u0012\tF1\u0001\u0013\t\u0019I$\u0011\u000bb\u0001%\u00111AH!\u0015C\u0002I!aa\u0010B)\u0005\u0004\u0011BA\u0002\"\u0003R\t\u0007!\u0003\u0002\u0004F\u0005#\u0012\rA\u0005\u0003\u0007\u0011\nE#\u0019\u0001\n\u0005\r-\u0013\tF1\u0001\u0013\u0011%\u0011\t\tAI\u0001\n\u0003\u0011\u0019)\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016M\t\u0015%\u0011\u0012BF\u0005\u001b\u0013yI!%\u0003\u0014\nU%q\u0013BM\u00057\u0013iJa(\u0003\"\n\r&Q\u0015BT\u0005S\u0013Y+\u0006\u0002\u0003\b*\u001aADa\u0007\u0005\rE\u0011yH1\u0001\u0013\t\u0019Y\"q\u0010b\u0001%\u00111aDa C\u0002I!a!\tB@\u0005\u0004\u0011BA\u0002\u0013\u0003\u0000\t\u0007!\u0003\u0002\u0004(\u0005\u007f\u0012\rA\u0005\u0003\u0007U\t}$\u0019\u0001\n\u0005\r5\u0012yH1\u0001\u0013\t\u0019\u0001$q\u0010b\u0001%\u001111Ga C\u0002I!aA\u000eB@\u0005\u0004\u0011BAB\u001d\u0003\u0000\t\u0007!\u0003\u0002\u0004=\u0005\u007f\u0012\rA\u0005\u0003\u0007\u007f\t}$\u0019\u0001\n\u0005\r\t\u0013yH1\u0001\u0013\t\u0019)%q\u0010b\u0001%\u00111\u0001Ja C\u0002I!aa\u0013B@\u0005\u0004\u0011\u0002\"\u0003BX\u0001E\u0005I\u0011\u0001BY\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ*bEa-\u00038\ne&1\u0018B_\u0005\u007f\u0013\tMa1\u0003F\n\u001d'\u0011\u001aBf\u0005\u001b\u0014yM!5\u0003T\nU'q\u001bBm+\t\u0011)LK\u0002 \u00057!a!\u0005BW\u0005\u0004\u0011BAB\u000e\u0003.\n\u0007!\u0003\u0002\u0004\u001f\u0005[\u0013\rA\u0005\u0003\u0007C\t5&\u0019\u0001\n\u0005\r\u0011\u0012iK1\u0001\u0013\t\u00199#Q\u0016b\u0001%\u00111!F!,C\u0002I!a!\fBW\u0005\u0004\u0011BA\u0002\u0019\u0003.\n\u0007!\u0003\u0002\u00044\u0005[\u0013\rA\u0005\u0003\u0007m\t5&\u0019\u0001\n\u0005\re\u0012iK1\u0001\u0013\t\u0019a$Q\u0016b\u0001%\u00111qH!,C\u0002I!aA\u0011BW\u0005\u0004\u0011BAB#\u0003.\n\u0007!\u0003\u0002\u0004I\u0005[\u0013\rA\u0005\u0003\u0007\u0017\n5&\u0019\u0001\n\t\u0013\tu\u0007!%A\u0005\u0002\t}\u0017AD2paf$C-\u001a4bk2$H%N\u000b'\u0005C\u0014)Oa:\u0003j\n-(Q\u001eBx\u0005c\u0014\u0019P!>\u0003x\ne(1 B\u007f\u0005\u007f\u001c\taa\u0001\u0004\u0006\r\u001dQC\u0001BrU\r\u0011#1\u0004\u0003\u0007#\tm'\u0019\u0001\n\u0005\rm\u0011YN1\u0001\u0013\t\u0019q\"1\u001cb\u0001%\u00111\u0011Ea7C\u0002I!a\u0001\nBn\u0005\u0004\u0011BAB\u0014\u0003\\\n\u0007!\u0003\u0002\u0004+\u00057\u0014\rA\u0005\u0003\u0007[\tm'\u0019\u0001\n\u0005\rA\u0012YN1\u0001\u0013\t\u0019\u0019$1\u001cb\u0001%\u00111aGa7C\u0002I!a!\u000fBn\u0005\u0004\u0011BA\u0002\u001f\u0003\\\n\u0007!\u0003\u0002\u0004@\u00057\u0014\rA\u0005\u0003\u0007\u0005\nm'\u0019\u0001\n\u0005\r\u0015\u0013YN1\u0001\u0013\t\u0019A%1\u001cb\u0001%\u001111Ja7C\u0002IA\u0011ba\u0003\u0001#\u0003%\ta!\u0004\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%mU13qBB\n\u0007+\u00199b!\u0007\u0004\u001c\ru1qDB\u0011\u0007G\u0019)ca\n\u0004*\r-2QFB\u0018\u0007c\u0019\u0019d!\u000e\u0016\u0005\rE!fA\u0013\u0003\u001c\u00111\u0011c!\u0003C\u0002I!aaGB\u0005\u0005\u0004\u0011BA\u0002\u0010\u0004\n\t\u0007!\u0003\u0002\u0004\"\u0007\u0013\u0011\rA\u0005\u0003\u0007I\r%!\u0019\u0001\n\u0005\r\u001d\u001aIA1\u0001\u0013\t\u0019Q3\u0011\u0002b\u0001%\u00111Qf!\u0003C\u0002I!a\u0001MB\u0005\u0005\u0004\u0011BAB\u001a\u0004\n\t\u0007!\u0003\u0002\u00047\u0007\u0013\u0011\rA\u0005\u0003\u0007s\r%!\u0019\u0001\n\u0005\rq\u001aIA1\u0001\u0013\t\u0019y4\u0011\u0002b\u0001%\u00111!i!\u0003C\u0002I!a!RB\u0005\u0005\u0004\u0011BA\u0002%\u0004\n\t\u0007!\u0003\u0002\u0004L\u0007\u0013\u0011\rA\u0005\u0005\n\u0007s\u0001\u0011\u0013!C\u0001\u0007w\tabY8qs\u0012\"WMZ1vYR$s'\u0006\u0014\u0004>\r\u000531IB#\u0007\u000f\u001aIea\u0013\u0004N\r=3\u0011KB*\u0007+\u001a9f!\u0017\u0004\\\ru3qLB1\u0007G*\"aa\u0010+\u0007!\u0012Y\u0002\u0002\u0004\u0012\u0007o\u0011\rA\u0005\u0003\u00077\r]\"\u0019\u0001\n\u0005\ry\u00199D1\u0001\u0013\t\u0019\t3q\u0007b\u0001%\u00111Aea\u000eC\u0002I!aaJB\u001c\u0005\u0004\u0011BA\u0002\u0016\u00048\t\u0007!\u0003\u0002\u0004.\u0007o\u0011\rA\u0005\u0003\u0007a\r]\"\u0019\u0001\n\u0005\rM\u001a9D1\u0001\u0013\t\u001914q\u0007b\u0001%\u00111\u0011ha\u000eC\u0002I!a\u0001PB\u001c\u0005\u0004\u0011BAB \u00048\t\u0007!\u0003\u0002\u0004C\u0007o\u0011\rA\u0005\u0003\u0007\u000b\u000e]\"\u0019\u0001\n\u0005\r!\u001b9D1\u0001\u0013\t\u0019Y5q\u0007b\u0001%!I1q\r\u0001\u0012\u0002\u0013\u00051\u0011N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00139+\u0019\u001aYga\u001c\u0004r\rM4QOB<\u0007s\u001aYh! \u0004\u0000\r\u000551QBC\u0007\u000f\u001bIia#\u0004\u000e\u000e=5\u0011S\u000b\u0003\u0007[R3a\u000bB\u000e\t\u0019\t2Q\rb\u0001%\u001111d!\u001aC\u0002I!aAHB3\u0005\u0004\u0011BAB\u0011\u0004f\t\u0007!\u0003\u0002\u0004%\u0007K\u0012\rA\u0005\u0003\u0007O\r\u0015$\u0019\u0001\n\u0005\r)\u001a)G1\u0001\u0013\t\u0019i3Q\rb\u0001%\u00111\u0001g!\u001aC\u0002I!aaMB3\u0005\u0004\u0011BA\u0002\u001c\u0004f\t\u0007!\u0003\u0002\u0004:\u0007K\u0012\rA\u0005\u0003\u0007y\r\u0015$\u0019\u0001\n\u0005\r}\u001a)G1\u0001\u0013\t\u0019\u00115Q\rb\u0001%\u00111Qi!\u001aC\u0002I!a\u0001SB3\u0005\u0004\u0011BAB&\u0004f\t\u0007!\u0003C\u0005\u0004\u0016\u0002\t\n\u0011\"\u0001\u0004\u0018\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012JTCJBM\u0007;\u001byj!)\u0004$\u000e\u00156qUBU\u0007W\u001bika,\u00042\u000eM6QWB\\\u0007s\u001bYl!0\u0004@V\u001111\u0014\u0016\u0004]\tmAAB\t\u0004\u0014\n\u0007!\u0003\u0002\u0004\u001c\u0007'\u0013\rA\u0005\u0003\u0007=\rM%\u0019\u0001\n\u0005\r\u0005\u001a\u0019J1\u0001\u0013\t\u0019!31\u0013b\u0001%\u00111qea%C\u0002I!aAKBJ\u0005\u0004\u0011BAB\u0017\u0004\u0014\n\u0007!\u0003\u0002\u00041\u0007'\u0013\rA\u0005\u0003\u0007g\rM%\u0019\u0001\n\u0005\rY\u001a\u0019J1\u0001\u0013\t\u0019I41\u0013b\u0001%\u00111Aha%C\u0002I!aaPBJ\u0005\u0004\u0011BA\u0002\"\u0004\u0014\n\u0007!\u0003\u0002\u0004F\u0007'\u0013\rA\u0005\u0003\u0007\u0011\u000eM%\u0019\u0001\n\u0005\r-\u001b\u0019J1\u0001\u0013\u0011%\u0019\u0019\rAI\u0001\n\u0003\u0019)-A\bd_BLH\u0005Z3gCVdG\u000fJ\u00191+\u0019\u001a9ma3\u0004N\u000e=7\u0011[Bj\u0007+\u001c9n!7\u0004\\\u000eu7q\\Bq\u0007G\u001c)oa:\u0004j\u000e-8Q^\u000b\u0003\u0007\u0013T3!\rB\u000e\t\u0019\t2\u0011\u0019b\u0001%\u001111d!1C\u0002I!aAHBa\u0005\u0004\u0011BAB\u0011\u0004B\n\u0007!\u0003\u0002\u0004%\u0007\u0003\u0014\rA\u0005\u0003\u0007O\r\u0005'\u0019\u0001\n\u0005\r)\u001a\tM1\u0001\u0013\t\u0019i3\u0011\u0019b\u0001%\u00111\u0001g!1C\u0002I!aaMBa\u0005\u0004\u0011BA\u0002\u001c\u0004B\n\u0007!\u0003\u0002\u0004:\u0007\u0003\u0014\rA\u0005\u0003\u0007y\r\u0005'\u0019\u0001\n\u0005\r}\u001a\tM1\u0001\u0013\t\u0019\u00115\u0011\u0019b\u0001%\u00111Qi!1C\u0002I!a\u0001SBa\u0005\u0004\u0011BAB&\u0004B\n\u0007!\u0003C\u0005\u0004r\u0002\t\n\u0011\"\u0001\u0004t\u0006y1m\u001c9zI\u0011,g-Y;mi\u0012\n\u0014'\u0006\u0014\u0004v\u000ee81`B\u007f\u0007\u007f$\t\u0001b\u0001\u0005\u0006\u0011\u001dA\u0011\u0002C\u0006\t\u001b!y\u0001\"\u0005\u0005\u0014\u0011UAq\u0003C\r\t7)\"aa>+\u0007Q\u0012Y\u0002\u0002\u0004\u0012\u0007_\u0014\rA\u0005\u0003\u00077\r=(\u0019\u0001\n\u0005\ry\u0019yO1\u0001\u0013\t\u0019\t3q\u001eb\u0001%\u00111Aea<C\u0002I!aaJBx\u0005\u0004\u0011BA\u0002\u0016\u0004p\n\u0007!\u0003\u0002\u0004.\u0007_\u0014\rA\u0005\u0003\u0007a\r=(\u0019\u0001\n\u0005\rM\u001ayO1\u0001\u0013\t\u001914q\u001eb\u0001%\u00111\u0011ha<C\u0002I!a\u0001PBx\u0005\u0004\u0011BAB \u0004p\n\u0007!\u0003\u0002\u0004C\u0007_\u0014\rA\u0005\u0003\u0007\u000b\u000e=(\u0019\u0001\n\u0005\r!\u001byO1\u0001\u0013\t\u0019Y5q\u001eb\u0001%!IAq\u0004\u0001\u0012\u0002\u0013\u0005A\u0011E\u0001\u0010G>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132eU1C1\u0005C\u0014\tS!Y\u0003\"\f\u00050\u0011EB1\u0007C\u001b\to!I\u0004b\u000f\u0005>\u0011}B\u0011\tC\"\t\u000b\"9\u0005\"\u0013\u0016\u0005\u0011\u0015\"fA\u001c\u0003\u001c\u00111\u0011\u0003\"\bC\u0002I!aa\u0007C\u000f\u0005\u0004\u0011BA\u0002\u0010\u0005\u001e\t\u0007!\u0003\u0002\u0004\"\t;\u0011\rA\u0005\u0003\u0007I\u0011u!\u0019\u0001\n\u0005\r\u001d\"iB1\u0001\u0013\t\u0019QCQ\u0004b\u0001%\u00111Q\u0006\"\bC\u0002I!a\u0001\rC\u000f\u0005\u0004\u0011BAB\u001a\u0005\u001e\t\u0007!\u0003\u0002\u00047\t;\u0011\rA\u0005\u0003\u0007s\u0011u!\u0019\u0001\n\u0005\rq\"iB1\u0001\u0013\t\u0019yDQ\u0004b\u0001%\u00111!\t\"\bC\u0002I!a!\u0012C\u000f\u0005\u0004\u0011BA\u0002%\u0005\u001e\t\u0007!\u0003\u0002\u0004L\t;\u0011\rA\u0005\u0005\n\t\u001b\u0002\u0011\u0013!C\u0001\t\u001f\nqbY8qs\u0012\"WMZ1vYR$\u0013gM\u000b'\t#\")\u0006b\u0016\u0005Z\u0011mCQ\fC0\tC\"\u0019\u0007\"\u001a\u0005h\u0011%D1\u000eC7\t_\"\t\bb\u001d\u0005v\u0011]TC\u0001C*U\rQ$1\u0004\u0003\u0007#\u0011-#\u0019\u0001\n\u0005\rm!YE1\u0001\u0013\t\u0019qB1\nb\u0001%\u00111\u0011\u0005b\u0013C\u0002I!a\u0001\nC&\u0005\u0004\u0011BAB\u0014\u0005L\t\u0007!\u0003\u0002\u0004+\t\u0017\u0012\rA\u0005\u0003\u0007[\u0011-#\u0019\u0001\n\u0005\rA\"YE1\u0001\u0013\t\u0019\u0019D1\nb\u0001%\u00111a\u0007b\u0013C\u0002I!a!\u000fC&\u0005\u0004\u0011BA\u0002\u001f\u0005L\t\u0007!\u0003\u0002\u0004@\t\u0017\u0012\rA\u0005\u0003\u0007\u0005\u0012-#\u0019\u0001\n\u0005\r\u0015#YE1\u0001\u0013\t\u0019AE1\nb\u0001%\u001111\nb\u0013C\u0002IA\u0011\u0002b\u001f\u0001#\u0003%\t\u0001\" \u0002\u001f\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cQ*b\u0005b \u0005\u0004\u0012\u0015Eq\u0011CE\t\u0017#i\tb$\u0005\u0012\u0012MEQ\u0013CL\t3#Y\n\"(\u0005 \u0012\u0005F1\u0015CS+\t!\tIK\u0002>\u00057!a!\u0005C=\u0005\u0004\u0011BAB\u000e\u0005z\t\u0007!\u0003\u0002\u0004\u001f\ts\u0012\rA\u0005\u0003\u0007C\u0011e$\u0019\u0001\n\u0005\r\u0011\"IH1\u0001\u0013\t\u00199C\u0011\u0010b\u0001%\u00111!\u0006\"\u001fC\u0002I!a!\fC=\u0005\u0004\u0011BA\u0002\u0019\u0005z\t\u0007!\u0003\u0002\u00044\ts\u0012\rA\u0005\u0003\u0007m\u0011e$\u0019\u0001\n\u0005\re\"IH1\u0001\u0013\t\u0019aD\u0011\u0010b\u0001%\u00111q\b\"\u001fC\u0002I!aA\u0011C=\u0005\u0004\u0011BAB#\u0005z\t\u0007!\u0003\u0002\u0004I\ts\u0012\rA\u0005\u0003\u0007\u0017\u0012e$\u0019\u0001\n\t\u0013\u0011%\u0006!%A\u0005\u0002\u0011-\u0016aD2paf$C-\u001a4bk2$H%M\u001b\u0016M\u00115F\u0011\u0017CZ\tk#9\f\"/\u0005<\u0012uFq\u0018Ca\t\u0007$)\rb2\u0005J\u0012-GQ\u001aCh\t#$\u0019.\u0006\u0002\u00050*\u001a\u0001Ia\u0007\u0005\rE!9K1\u0001\u0013\t\u0019YBq\u0015b\u0001%\u00111a\u0004b*C\u0002I!a!\tCT\u0005\u0004\u0011BA\u0002\u0013\u0005(\n\u0007!\u0003\u0002\u0004(\tO\u0013\rA\u0005\u0003\u0007U\u0011\u001d&\u0019\u0001\n\u0005\r5\"9K1\u0001\u0013\t\u0019\u0001Dq\u0015b\u0001%\u001111\u0007b*C\u0002I!aA\u000eCT\u0005\u0004\u0011BAB\u001d\u0005(\n\u0007!\u0003\u0002\u0004=\tO\u0013\rA\u0005\u0003\u0007\u007f\u0011\u001d&\u0019\u0001\n\u0005\r\t#9K1\u0001\u0013\t\u0019)Eq\u0015b\u0001%\u00111\u0001\nb*C\u0002I!aa\u0013CT\u0005\u0004\u0011\u0002\"\u0003Cl\u0001E\u0005I\u0011\u0001Cm\u0003=\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE2TC\nCn\t?$\t\u000fb9\u0005f\u0012\u001dH\u0011\u001eCv\t[$y\u000f\"=\u0005t\u0012UHq\u001fC}\tw$i\u0010b@\u0006\u0002U\u0011AQ\u001c\u0016\u0004\u0007\nmAAB\t\u0005V\n\u0007!\u0003\u0002\u0004\u001c\t+\u0014\rA\u0005\u0003\u0007=\u0011U'\u0019\u0001\n\u0005\r\u0005\")N1\u0001\u0013\t\u0019!CQ\u001bb\u0001%\u00111q\u0005\"6C\u0002I!aA\u000bCk\u0005\u0004\u0011BAB\u0017\u0005V\n\u0007!\u0003\u0002\u00041\t+\u0014\rA\u0005\u0003\u0007g\u0011U'\u0019\u0001\n\u0005\rY\")N1\u0001\u0013\t\u0019IDQ\u001bb\u0001%\u00111A\b\"6C\u0002I!aa\u0010Ck\u0005\u0004\u0011BA\u0002\"\u0005V\n\u0007!\u0003\u0002\u0004F\t+\u0014\rA\u0005\u0003\u0007\u0011\u0012U'\u0019\u0001\n\u0005\r-#)N1\u0001\u0013\u0011%))\u0001AI\u0001\n\u0003)9!A\bd_BLH\u0005Z3gCVdG\u000fJ\u00198+\u0019*I!\"\u0004\u0006\u0010\u0015EQ1CC\u000b\u000b/)I\"b\u0007\u0006\u001e\u0015}Q\u0011EC\u0012\u000bK)9#\"\u000b\u0006,\u00155RqF\u000b\u0003\u000b\u0017Q3A\u0012B\u000e\t\u0019\tR1\u0001b\u0001%\u001111$b\u0001C\u0002I!aAHC\u0002\u0005\u0004\u0011BAB\u0011\u0006\u0004\t\u0007!\u0003\u0002\u0004%\u000b\u0007\u0011\rA\u0005\u0003\u0007O\u0015\r!\u0019\u0001\n\u0005\r)*\u0019A1\u0001\u0013\t\u0019iS1\u0001b\u0001%\u00111\u0001'b\u0001C\u0002I!aaMC\u0002\u0005\u0004\u0011BA\u0002\u001c\u0006\u0004\t\u0007!\u0003\u0002\u0004:\u000b\u0007\u0011\rA\u0005\u0003\u0007y\u0015\r!\u0019\u0001\n\u0005\r}*\u0019A1\u0001\u0013\t\u0019\u0011U1\u0001b\u0001%\u00111Q)b\u0001C\u0002I!a\u0001SC\u0002\u0005\u0004\u0011BAB&\u0006\u0004\t\u0007!\u0003C\u0005\u00064\u0001\t\n\u0011\"\u0001\u00066\u0005y1m\u001c9zI\u0011,g-Y;mi\u0012\n\u0004(\u0006\u0014\u00068\u0015mRQHC \u000b\u0003*\u0019%\"\u0012\u0006H\u0015%S1JC'\u000b\u001f*\t&b\u0015\u0006V\u0015]S\u0011LC.\u000b;*\"!\"\u000f+\u0007%\u0013Y\u0002\u0002\u0004\u0012\u000bc\u0011\rA\u0005\u0003\u00077\u0015E\"\u0019\u0001\n\u0005\ry)\tD1\u0001\u0013\t\u0019\tS\u0011\u0007b\u0001%\u00111A%\"\rC\u0002I!aaJC\u0019\u0005\u0004\u0011BA\u0002\u0016\u00062\t\u0007!\u0003\u0002\u0004.\u000bc\u0011\rA\u0005\u0003\u0007a\u0015E\"\u0019\u0001\n\u0005\rM*\tD1\u0001\u0013\t\u00191T\u0011\u0007b\u0001%\u00111\u0011(\"\rC\u0002I!a\u0001PC\u0019\u0005\u0004\u0011BAB \u00062\t\u0007!\u0003\u0002\u0004C\u000bc\u0011\rA\u0005\u0003\u0007\u000b\u0016E\"\u0019\u0001\n\u0005\r!+\tD1\u0001\u0013\t\u0019YU\u0011\u0007b\u0001%!IQ\u0011\r\u0001\u0002\u0002\u0013\u0005S1M\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005-\u0005\"CC4\u0001\u0005\u0005I\u0011IC5\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAC6!\u0015)i'b\u001d\u0017\u001b\t)yGC\u0002\u0006r\t\t!bY8mY\u0016\u001cG/[8o\u0013\u0011))(b\u001c\u0003\u0011%#XM]1u_JD\u0011\"\"\u001f\u0001\u0003\u0003%\t!b\u001f\u0002\u0011\r\fg.R9vC2$B!\" \u0006\u0004B\u0019\u0001\"b \n\u0007\u0015\u0005%AA\u0004C_>dW-\u00198\t\u0013\u0015\u0015UqOA\u0001\u0002\u00041\u0012a\u0001=%c!IQ\u0011\u0012\u0001\u0002\u0002\u0013\u0005S1R\u0001\tQ\u0006\u001c\bnQ8eKR\u0011QQ\u0012\t\u0004\u0011\u0015=\u0015bACI\u0005\t\u0019\u0011J\u001c;\t\u0013\u0015U\u0005!!A\u0005B\u0015]\u0015AB3rk\u0006d7\u000f\u0006\u0003\u0006~\u0015e\u0005\"CCC\u000b'\u000b\t\u00111\u0001\u0017Q\u001d\u0001QQTCR\u000bO\u00032\u0001CCP\u0013\r)\tK\u0001\u0002\u0016I\u0016\u0004(/Z2bi\u0016$\u0017J\u001c5fe&$\u0018M\\2fC\t))+\u0001\u0018UkBdWm\u001d\u0011xS2d\u0007EY3![\u0006$W\r\t4j]\u0006d\u0007%\u001b8!C\u00022W\u000f^;sK\u00022XM]:j_:t\u0013EACU\u0003\u0019\u0011d&M\u0019/a\u001dIQQ\u0016\u0002\u0002\u0002#\u0005QqV\u0001\b)V\u0004H.Z\u00199!\rAQ\u0011\u0017\u0004\t\u0003\t\t\t\u0011#\u0001\u00064N!Q\u0011W\u0004P\u0011!\tY&\"-\u0005\u0002\u0015]FCACX\u0011)\t9)\"-\u0002\u0002\u0013\u0015\u0013\u0011\u0012\u0005\u000b\u000b{+\t,!A\u0005\u0002\u0016}\u0016!B1qa2LXCJCa\u000b\u000f,Y-b4\u0006T\u0016]W1\\Cp\u000bG,9/b;\u0006p\u0016MXq_C~\u000b\u007f4\u0019Ab\u0002\u0007\fQ1S1\u0019D\u0007\r\u001f1\tBb\u0005\u0007\u0016\u0019]a\u0011\u0004D\u000e\r;1yB\"\t\u0007$\u0019\u0015bq\u0005D\u0015\rW1iCb\f\u0011M!\u0001QQYCe\u000b\u001b,\t.\"6\u0006Z\u0016uW\u0011]Cs\u000bS,i/\"=\u0006v\u0016eXQ D\u0001\r\u000b1I\u0001E\u0002\u0010\u000b\u000f$a!EC^\u0005\u0004\u0011\u0002cA\b\u0006L\u001211$b/C\u0002I\u00012aDCh\t\u0019qR1\u0018b\u0001%A\u0019q\"b5\u0005\r\u0005*YL1\u0001\u0013!\ryQq\u001b\u0003\u0007I\u0015m&\u0019\u0001\n\u0011\u0007=)Y\u000e\u0002\u0004(\u000bw\u0013\rA\u0005\t\u0004\u001f\u0015}GA\u0002\u0016\u0006<\n\u0007!\u0003E\u0002\u0010\u000bG$a!LC^\u0005\u0004\u0011\u0002cA\b\u0006h\u00121\u0001'b/C\u0002I\u00012aDCv\t\u0019\u0019T1\u0018b\u0001%A\u0019q\"b<\u0005\rY*YL1\u0001\u0013!\ryQ1\u001f\u0003\u0007s\u0015m&\u0019\u0001\n\u0011\u0007=)9\u0010\u0002\u0004=\u000bw\u0013\rA\u0005\t\u0004\u001f\u0015mHAB \u0006<\n\u0007!\u0003E\u0002\u0010\u000b\u007f$aAQC^\u0005\u0004\u0011\u0002cA\b\u0007\u0004\u00111Q)b/C\u0002I\u00012a\u0004D\u0004\t\u0019AU1\u0018b\u0001%A\u0019qBb\u0003\u0005\r-+YL1\u0001\u0013\u0011\u001d\u0019V1\u0018a\u0001\u000b\u000bDq\u0001WC^\u0001\u0004)I\rC\u0004^\u000bw\u0003\r!\"4\t\u000f\t,Y\f1\u0001\u0006R\"9q-b/A\u0002\u0015U\u0007b\u00027\u0006<\u0002\u0007Q\u0011\u001c\u0005\bc\u0016m\u0006\u0019ACo\u0011\u001d1X1\u0018a\u0001\u000bCDqa_C^\u0001\u0004))\u000f\u0003\u0005\u0002\u0002\u0015m\u0006\u0019ACu\u0011!\tY!b/A\u0002\u00155\b\u0002CA\u000b\u000bw\u0003\r!\"=\t\u0011\u0005}Q1\u0018a\u0001\u000bkD\u0001\"!\u000b\u0006<\u0002\u0007Q\u0011 \u0005\t\u0003g)Y\f1\u0001\u0006~\"A\u0011QHC^\u0001\u00041\t\u0001\u0003\u0005\u0002H\u0015m\u0006\u0019\u0001D\u0003\u0011!\t\t&b/A\u0002\u0019%\u0001B\u0003D\u001a\u000bc\u000b\t\u0011\"!\u00076\u00059QO\\1qa2LXC\nD\u001c\r\u000729Eb\u0013\u0007P\u0019Mcq\u000bD.\r?2\u0019Gb\u001a\u0007l\u0019=d1\u000fD<\rw2yHb!\u0007\bR!a\u0011\bDE!\u0015Aa1\bD \u0013\r1iD\u0001\u0002\u0007\u001fB$\u0018n\u001c8\u0011M!\u0001a\u0011\tD#\r\u00132iE\"\u0015\u0007V\u0019ecQ\fD1\rK2IG\"\u001c\u0007r\u0019Ud\u0011\u0010D?\r\u00033)\tE\u0002\u0010\r\u0007\"a!\u0005D\u0019\u0005\u0004\u0011\u0002cA\b\u0007H\u001111D\"\rC\u0002I\u00012a\u0004D&\t\u0019qb\u0011\u0007b\u0001%A\u0019qBb\u0014\u0005\r\u00052\tD1\u0001\u0013!\rya1\u000b\u0003\u0007I\u0019E\"\u0019\u0001\n\u0011\u0007=19\u0006\u0002\u0004(\rc\u0011\rA\u0005\t\u0004\u001f\u0019mCA\u0002\u0016\u00072\t\u0007!\u0003E\u0002\u0010\r?\"a!\fD\u0019\u0005\u0004\u0011\u0002cA\b\u0007d\u00111\u0001G\"\rC\u0002I\u00012a\u0004D4\t\u0019\u0019d\u0011\u0007b\u0001%A\u0019qBb\u001b\u0005\rY2\tD1\u0001\u0013!\ryaq\u000e\u0003\u0007s\u0019E\"\u0019\u0001\n\u0011\u0007=1\u0019\b\u0002\u0004=\rc\u0011\rA\u0005\t\u0004\u001f\u0019]DAB \u00072\t\u0007!\u0003E\u0002\u0010\rw\"aA\u0011D\u0019\u0005\u0004\u0011\u0002cA\b\u0007\u0000\u00111QI\"\rC\u0002I\u00012a\u0004DB\t\u0019Ae\u0011\u0007b\u0001%A\u0019qBb\"\u0005\r-3\tD1\u0001\u0013\u0011)1YI\"\r\u0002\u0002\u0003\u0007aqH\u0001\u0004q\u0012\u0002\u0004B\u0003DH\u000bc\u000b\t\u0011\"\u0003\u0007\u0012\u0006Y!/Z1e%\u0016\u001cx\u000e\u001c<f)\t1\u0019\n\u0005\u0003\u0002\u000e\u001aU\u0015\u0002\u0002DL\u0003\u001f\u0013aa\u00142kK\u000e$\b")
public class Tuple18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>
implements Product18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>,
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
    private final T17 _17;
    private final T18 _18;

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> Option<Tuple18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>> unapply(Tuple18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> tuple18) {
        return Tuple18$.MODULE$.unapply(tuple18);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> Tuple18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> apply(T1 T1, T2 T2, T3 T3, T4 T4, T5 T5, T6 T6, T7 T7, T8 T8, T9 T9, T10 T10, T11 T11, T12 T12, T13 T13, T14 T14, T15 T15, T16 T16, T17 T17, T18 T18) {
        return Tuple18$.MODULE$.apply(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18);
    }

    @Override
    public int productArity() {
        return Product18$class.productArity(this);
    }

    @Override
    public Object productElement(int n) throws IndexOutOfBoundsException {
        return Product18$class.productElement(this, n);
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

    @Override
    public T17 _17() {
        return this._17;
    }

    @Override
    public T18 _18() {
        return this._18;
    }

    public String toString() {
        return new StringBuilder().append((Object)"(").append(this._1()).append((Object)",").append(this._2()).append((Object)",").append(this._3()).append((Object)",").append(this._4()).append((Object)",").append(this._5()).append((Object)",").append(this._6()).append((Object)",").append(this._7()).append((Object)",").append(this._8()).append((Object)",").append(this._9()).append((Object)",").append(this._10()).append((Object)",").append(this._11()).append((Object)",").append(this._12()).append((Object)",").append(this._13()).append((Object)",").append(this._14()).append((Object)",").append(this._15()).append((Object)",").append(this._16()).append((Object)",").append(this._17()).append((Object)",").append(this._18()).append((Object)")").toString();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> Tuple18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> copy(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18) {
        return new Tuple18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18);
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T1 copy$default$1() {
        return this._1();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T2 copy$default$2() {
        return this._2();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T3 copy$default$3() {
        return this._3();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T4 copy$default$4() {
        return this._4();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T5 copy$default$5() {
        return this._5();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T6 copy$default$6() {
        return this._6();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T7 copy$default$7() {
        return this._7();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T8 copy$default$8() {
        return this._8();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T9 copy$default$9() {
        return this._9();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T10 copy$default$10() {
        return this._10();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T11 copy$default$11() {
        return this._11();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T12 copy$default$12() {
        return this._12();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T13 copy$default$13() {
        return this._13();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T14 copy$default$14() {
        return this._14();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T15 copy$default$15() {
        return this._15();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T16 copy$default$16() {
        return this._16();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T17 copy$default$17() {
        return this._17();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> T18 copy$default$18() {
        return this._18();
    }

    @Override
    public String productPrefix() {
        return "Tuple18";
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof Tuple18;
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
        boolean bl17;
        boolean bl18;
        if (this == x$1) return true;
        if (!(x$1 instanceof Tuple18)) return false;
        boolean bl19 = true;
        if (!bl19) return false;
        Tuple18 tuple18 = (Tuple18)x$1;
        T1 T1 = tuple18._1();
        T1 T12 = this._1();
        if (T12 == T1) {
            bl18 = true;
        } else {
            if (T12 == null) {
                return false;
            }
            bl18 = T12 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T12, T1) : (T12 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T12, T1) : T12.equals(T1));
        }
        if (!bl18) return false;
        T2 T2 = tuple18._2();
        T2 T22 = this._2();
        if (T22 == T2) {
            bl17 = true;
        } else {
            if (T22 == null) {
                return false;
            }
            bl17 = T22 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T22, T2) : (T22 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T22, T2) : T22.equals(T2));
        }
        if (!bl17) return false;
        T3 T3 = tuple18._3();
        T3 T32 = this._3();
        if (T32 == T3) {
            bl16 = true;
        } else {
            if (T32 == null) {
                return false;
            }
            bl16 = T32 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T32, T3) : (T32 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T32, T3) : T32.equals(T3));
        }
        if (!bl16) return false;
        T4 T4 = tuple18._4();
        T4 T42 = this._4();
        if (T42 == T4) {
            bl15 = true;
        } else {
            if (T42 == null) {
                return false;
            }
            bl15 = T42 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T42, T4) : (T42 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T42, T4) : T42.equals(T4));
        }
        if (!bl15) return false;
        T5 T5 = tuple18._5();
        T5 T52 = this._5();
        if (T52 == T5) {
            bl14 = true;
        } else {
            if (T52 == null) {
                return false;
            }
            bl14 = T52 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T52, T5) : (T52 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T52, T5) : T52.equals(T5));
        }
        if (!bl14) return false;
        T6 T6 = tuple18._6();
        T6 T62 = this._6();
        if (T62 == T6) {
            bl13 = true;
        } else {
            if (T62 == null) {
                return false;
            }
            bl13 = T62 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T62, T6) : (T62 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T62, T6) : T62.equals(T6));
        }
        if (!bl13) return false;
        T7 T7 = tuple18._7();
        T7 T72 = this._7();
        if (T72 == T7) {
            bl12 = true;
        } else {
            if (T72 == null) {
                return false;
            }
            bl12 = T72 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T72, T7) : (T72 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T72, T7) : T72.equals(T7));
        }
        if (!bl12) return false;
        T8 T8 = tuple18._8();
        T8 T82 = this._8();
        if (T82 == T8) {
            bl11 = true;
        } else {
            if (T82 == null) {
                return false;
            }
            bl11 = T82 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T82, T8) : (T82 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T82, T8) : T82.equals(T8));
        }
        if (!bl11) return false;
        T9 T9 = tuple18._9();
        T9 T92 = this._9();
        if (T92 == T9) {
            bl10 = true;
        } else {
            if (T92 == null) {
                return false;
            }
            bl10 = T92 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T92, T9) : (T92 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T92, T9) : T92.equals(T9));
        }
        if (!bl10) return false;
        T10 T10 = tuple18._10();
        T10 T102 = this._10();
        if (T102 == T10) {
            bl9 = true;
        } else {
            if (T102 == null) {
                return false;
            }
            bl9 = T102 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T102, T10) : (T102 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T102, T10) : T102.equals(T10));
        }
        if (!bl9) return false;
        T11 T11 = tuple18._11();
        T11 T112 = this._11();
        if (T112 == T11) {
            bl8 = true;
        } else {
            if (T112 == null) {
                return false;
            }
            bl8 = T112 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T112, T11) : (T112 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T112, T11) : T112.equals(T11));
        }
        if (!bl8) return false;
        T12 T122 = tuple18._12();
        T12 T123 = this._12();
        if (T123 == T122) {
            bl7 = true;
        } else {
            if (T123 == null) {
                return false;
            }
            bl7 = T123 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T123, T122) : (T123 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T123, T122) : T123.equals(T122));
        }
        if (!bl7) return false;
        T13 T13 = tuple18._13();
        T13 T132 = this._13();
        if (T132 == T13) {
            bl6 = true;
        } else {
            if (T132 == null) {
                return false;
            }
            bl6 = T132 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T132, T13) : (T132 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T132, T13) : T132.equals(T13));
        }
        if (!bl6) return false;
        T14 T14 = tuple18._14();
        T14 T142 = this._14();
        if (T142 == T14) {
            bl5 = true;
        } else {
            if (T142 == null) {
                return false;
            }
            bl5 = T142 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T142, T14) : (T142 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T142, T14) : T142.equals(T14));
        }
        if (!bl5) return false;
        T15 T15 = tuple18._15();
        T15 T152 = this._15();
        if (T152 == T15) {
            bl4 = true;
        } else {
            if (T152 == null) {
                return false;
            }
            bl4 = T152 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T152, T15) : (T152 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T152, T15) : T152.equals(T15));
        }
        if (!bl4) return false;
        T16 T16 = tuple18._16();
        T16 T162 = this._16();
        if (T162 == T16) {
            bl3 = true;
        } else {
            if (T162 == null) {
                return false;
            }
            bl3 = T162 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T162, T16) : (T162 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T162, T16) : T162.equals(T16));
        }
        if (!bl3) return false;
        T17 T17 = tuple18._17();
        T17 T172 = this._17();
        if (T172 == T17) {
            bl2 = true;
        } else {
            if (T172 == null) {
                return false;
            }
            bl2 = T172 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T172, T17) : (T172 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T172, T17) : T172.equals(T17));
        }
        if (!bl2) return false;
        T18 T18 = tuple18._18();
        T18 T182 = this._18();
        if (T182 == T18) {
            bl = true;
        } else {
            if (T182 == null) {
                return false;
            }
            bl = T182 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T182, T18) : (T182 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T182, T18) : T182.equals(T18));
        }
        if (!bl) return false;
        if (!tuple18.canEqual(this)) return false;
        return true;
    }

    public Tuple18(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18) {
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
        this._17 = _17;
        this._18 = _18;
        Product$class.$init$(this);
        Product18$class.$init$(this);
    }
}

