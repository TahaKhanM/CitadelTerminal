/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Option;
import scala.Product$class;
import scala.Product10;
import scala.Product10$class;
import scala.Serializable;
import scala.Tuple10$;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\red\u0001B\u0001\u0003\u0001\u0016\u0011q\u0001V;qY\u0016\f\u0004GC\u0001\u0004\u0003\u0015\u00198-\u00197b\u0007\u0001)2B\u0002\t\u001b;\u0001\u001ac%\u000b\u00170eM)\u0001aB\u00065oA\u0011\u0001\"C\u0007\u0002\u0005%\u0011!B\u0001\u0002\u0007\u0003:L(+\u001a4\u0011\u0019!aa\"\u0007\u000f E\u0015B3FL\u0019\n\u00055\u0011!!\u0003)s_\u0012,8\r^\u00191!\ty\u0001\u0003\u0004\u0001\u0005\rE\u0001AQ1\u0001\u0013\u0005\t!\u0016'\u0005\u0002\u0014-A\u0011\u0001\u0002F\u0005\u0003+\t\u0011qAT8uQ&tw\r\u0005\u0002\t/%\u0011\u0001D\u0001\u0002\u0004\u0003:L\bCA\b\u001b\t\u0019Y\u0002\u0001\"b\u0001%\t\u0011AK\r\t\u0003\u001fu!aA\b\u0001\u0005\u0006\u0004\u0011\"A\u0001+4!\ty\u0001\u0005\u0002\u0004\"\u0001\u0011\u0015\rA\u0005\u0002\u0003)R\u0002\"aD\u0012\u0005\r\u0011\u0002AQ1\u0001\u0013\u0005\t!V\u0007\u0005\u0002\u0010M\u00111q\u0005\u0001CC\u0002I\u0011!\u0001\u0016\u001c\u0011\u0005=ICA\u0002\u0016\u0001\t\u000b\u0007!C\u0001\u0002UoA\u0011q\u0002\f\u0003\u0007[\u0001!)\u0019\u0001\n\u0003\u0005QC\u0004CA\b0\t\u0019\u0001\u0004\u0001\"b\u0001%\t\u0011A+\u000f\t\u0003\u001fI\"aa\r\u0001\u0005\u0006\u0004\u0011\"a\u0001+2aA\u0011\u0001\"N\u0005\u0003m\t\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002\tq%\u0011\u0011H\u0001\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\tw\u0001\u0011)\u001a!C\u0001y\u0005\u0011q,M\u000b\u0002\u001d!Aa\b\u0001B\tB\u0003%a\"A\u0002`c\u0001B\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!Q\u0001\u0003?J*\u0012!\u0007\u0005\t\u0007\u0002\u0011\t\u0012)A\u00053\u0005\u0019qL\r\u0011\t\u0011\u0015\u0003!Q3A\u0005\u0002\u0019\u000b!aX\u001a\u0016\u0003qA\u0001\u0002\u0013\u0001\u0003\u0012\u0003\u0006I\u0001H\u0001\u0004?N\u0002\u0003\u0002\u0003&\u0001\u0005+\u0007I\u0011A&\u0002\u0005}#T#A\u0010\t\u00115\u0003!\u0011#Q\u0001\n}\t1a\u0018\u001b!\u0011!y\u0005A!f\u0001\n\u0003\u0001\u0016AA06+\u0005\u0011\u0003\u0002\u0003*\u0001\u0005#\u0005\u000b\u0011\u0002\u0012\u0002\u0007}+\u0004\u0005\u0003\u0005U\u0001\tU\r\u0011\"\u0001V\u0003\tyf'F\u0001&\u0011!9\u0006A!E!\u0002\u0013)\u0013aA07A!A\u0011\f\u0001BK\u0002\u0013\u0005!,\u0001\u0002`oU\t\u0001\u0006\u0003\u0005]\u0001\tE\t\u0015!\u0003)\u0003\ryv\u0007\t\u0005\t=\u0002\u0011)\u001a!C\u0001?\u0006\u0011q\fO\u000b\u0002W!A\u0011\r\u0001B\tB\u0003%1&A\u0002`q\u0001B\u0001b\u0019\u0001\u0003\u0016\u0004%\t\u0001Z\u0001\u0003?f*\u0012A\f\u0005\tM\u0002\u0011\t\u0012)A\u0005]\u0005\u0019q,\u000f\u0011\t\u0011!\u0004!Q3A\u0005\u0002%\f1aX\u00191+\u0005\t\u0004\u0002C6\u0001\u0005#\u0005\u000b\u0011B\u0019\u0002\t}\u000b\u0004\u0007\t\u0005\u0006[\u0002!\tA\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0017=\u0004\u0018O]:ukZ<\b0\u001f\t\r\u0011\u0001q\u0011\u0004H\u0010#K!Zc&\r\u0005\u0006w1\u0004\rA\u0004\u0005\u0006\u00012\u0004\r!\u0007\u0005\u0006\u000b2\u0004\r\u0001\b\u0005\u0006\u00152\u0004\ra\b\u0005\u0006\u001f2\u0004\rA\t\u0005\u0006)2\u0004\r!\n\u0005\u000632\u0004\r\u0001\u000b\u0005\u0006=2\u0004\ra\u000b\u0005\u0006G2\u0004\rA\f\u0005\u0006Q2\u0004\r!\r\u0005\u0006w\u0002!\t\u0005`\u0001\ti>\u001cFO]5oOR\tQ\u0010E\u0002\u007f\u0003\u000fi\u0011a \u0006\u0005\u0003\u0003\t\u0019!\u0001\u0003mC:<'BAA\u0003\u0003\u0011Q\u0017M^1\n\u0007\u0005%qP\u0001\u0004TiJLgn\u001a\u0005\n\u0003\u001b\u0001\u0011\u0011!C\u0001\u0003\u001f\tAaY8qsV1\u0012\u0011CA\f\u00037\ty\"a\t\u0002(\u0005-\u0012qFA\u001a\u0003o\tY\u0004\u0006\f\u0002\u0014\u0005u\u0012qHA!\u0003\u0007\n)%a\u0012\u0002J\u0005-\u0013QJA(!YA\u0001!!\u0006\u0002\u001a\u0005u\u0011\u0011EA\u0013\u0003S\ti#!\r\u00026\u0005e\u0002cA\b\u0002\u0018\u00111\u0011#a\u0003C\u0002I\u00012aDA\u000e\t\u0019Y\u00121\u0002b\u0001%A\u0019q\"a\b\u0005\ry\tYA1\u0001\u0013!\ry\u00111\u0005\u0003\u0007C\u0005-!\u0019\u0001\n\u0011\u0007=\t9\u0003\u0002\u0004%\u0003\u0017\u0011\rA\u0005\t\u0004\u001f\u0005-BAB\u0014\u0002\f\t\u0007!\u0003E\u0002\u0010\u0003_!aAKA\u0006\u0005\u0004\u0011\u0002cA\b\u00024\u00111Q&a\u0003C\u0002I\u00012aDA\u001c\t\u0019\u0001\u00141\u0002b\u0001%A\u0019q\"a\u000f\u0005\rM\nYA1\u0001\u0013\u0011%Y\u00141\u0002I\u0001\u0002\u0004\t)\u0002C\u0005A\u0003\u0017\u0001\n\u00111\u0001\u0002\u001a!IQ)a\u0003\u0011\u0002\u0003\u0007\u0011Q\u0004\u0005\n\u0015\u0006-\u0001\u0013!a\u0001\u0003CA\u0011bTA\u0006!\u0003\u0005\r!!\n\t\u0013Q\u000bY\u0001%AA\u0002\u0005%\u0002\"C-\u0002\fA\u0005\t\u0019AA\u0017\u0011%q\u00161\u0002I\u0001\u0002\u0004\t\t\u0004C\u0005d\u0003\u0017\u0001\n\u00111\u0001\u00026!I\u0001.a\u0003\u0011\u0002\u0003\u0007\u0011\u0011\b\u0005\n\u0003'\u0002\u0011\u0013!C\u0001\u0003+\nabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\f\u0002X\u00055\u0014qNA9\u0003g\n)(a\u001e\u0002z\u0005m\u0014QPA@+\t\tIFK\u0002\u000f\u00037Z#!!\u0018\u0011\t\u0005}\u0013\u0011N\u0007\u0003\u0003CRA!a\u0019\u0002f\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003O\u0012\u0011AC1o]>$\u0018\r^5p]&!\u00111NA1\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0007#\u0005E#\u0019\u0001\n\u0005\rm\t\tF1\u0001\u0013\t\u0019q\u0012\u0011\u000bb\u0001%\u00111\u0011%!\u0015C\u0002I!a\u0001JA)\u0005\u0004\u0011BAB\u0014\u0002R\t\u0007!\u0003\u0002\u0004+\u0003#\u0012\rA\u0005\u0003\u0007[\u0005E#\u0019\u0001\n\u0005\rA\n\tF1\u0001\u0013\t\u0019\u0019\u0014\u0011\u000bb\u0001%!I\u00111\u0011\u0001\u0012\u0002\u0013\u0005\u0011QQ\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+Y\t9)a#\u0002\u000e\u0006=\u0015\u0011SAJ\u0003+\u000b9*!'\u0002\u001c\u0006uUCAAEU\rI\u00121\f\u0003\u0007#\u0005\u0005%\u0019\u0001\n\u0005\rm\t\tI1\u0001\u0013\t\u0019q\u0012\u0011\u0011b\u0001%\u00111\u0011%!!C\u0002I!a\u0001JAA\u0005\u0004\u0011BAB\u0014\u0002\u0002\n\u0007!\u0003\u0002\u0004+\u0003\u0003\u0013\rA\u0005\u0003\u0007[\u0005\u0005%\u0019\u0001\n\u0005\rA\n\tI1\u0001\u0013\t\u0019\u0019\u0014\u0011\u0011b\u0001%!I\u0011\u0011\u0015\u0001\u0012\u0002\u0013\u0005\u00111U\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+Y\t)+!+\u0002,\u00065\u0016qVAY\u0003g\u000b),a.\u0002:\u0006mVCAATU\ra\u00121\f\u0003\u0007#\u0005}%\u0019\u0001\n\u0005\rm\tyJ1\u0001\u0013\t\u0019q\u0012q\u0014b\u0001%\u00111\u0011%a(C\u0002I!a\u0001JAP\u0005\u0004\u0011BAB\u0014\u0002 \n\u0007!\u0003\u0002\u0004+\u0003?\u0013\rA\u0005\u0003\u0007[\u0005}%\u0019\u0001\n\u0005\rA\nyJ1\u0001\u0013\t\u0019\u0019\u0014q\u0014b\u0001%!I\u0011q\u0018\u0001\u0012\u0002\u0013\u0005\u0011\u0011Y\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135+Y\t\u0019-a2\u0002J\u0006-\u0017QZAh\u0003#\f\u0019.!6\u0002X\u0006eWCAAcU\ry\u00121\f\u0003\u0007#\u0005u&\u0019\u0001\n\u0005\rm\tiL1\u0001\u0013\t\u0019q\u0012Q\u0018b\u0001%\u00111\u0011%!0C\u0002I!a\u0001JA_\u0005\u0004\u0011BAB\u0014\u0002>\n\u0007!\u0003\u0002\u0004+\u0003{\u0013\rA\u0005\u0003\u0007[\u0005u&\u0019\u0001\n\u0005\rA\niL1\u0001\u0013\t\u0019\u0019\u0014Q\u0018b\u0001%!I\u0011Q\u001c\u0001\u0012\u0002\u0013\u0005\u0011q\\\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00136+Y\t\t/!:\u0002h\u0006%\u00181^Aw\u0003_\f\t0a=\u0002v\u0006]XCAArU\r\u0011\u00131\f\u0003\u0007#\u0005m'\u0019\u0001\n\u0005\rm\tYN1\u0001\u0013\t\u0019q\u00121\u001cb\u0001%\u00111\u0011%a7C\u0002I!a\u0001JAn\u0005\u0004\u0011BAB\u0014\u0002\\\n\u0007!\u0003\u0002\u0004+\u00037\u0014\rA\u0005\u0003\u0007[\u0005m'\u0019\u0001\n\u0005\rA\nYN1\u0001\u0013\t\u0019\u0019\u00141\u001cb\u0001%!I\u00111 \u0001\u0012\u0002\u0013\u0005\u0011Q`\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00137+Y\tyPa\u0001\u0003\u0006\t\u001d!\u0011\u0002B\u0006\u0005\u001b\u0011yA!\u0005\u0003\u0014\tUQC\u0001B\u0001U\r)\u00131\f\u0003\u0007#\u0005e(\u0019\u0001\n\u0005\rm\tIP1\u0001\u0013\t\u0019q\u0012\u0011 b\u0001%\u00111\u0011%!?C\u0002I!a\u0001JA}\u0005\u0004\u0011BAB\u0014\u0002z\n\u0007!\u0003\u0002\u0004+\u0003s\u0014\rA\u0005\u0003\u0007[\u0005e(\u0019\u0001\n\u0005\rA\nIP1\u0001\u0013\t\u0019\u0019\u0014\u0011 b\u0001%!I!\u0011\u0004\u0001\u0012\u0002\u0013\u0005!1D\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00138+Y\u0011iB!\t\u0003$\t\u0015\"q\u0005B\u0015\u0005W\u0011iCa\f\u00032\tMRC\u0001B\u0010U\rA\u00131\f\u0003\u0007#\t]!\u0019\u0001\n\u0005\rm\u00119B1\u0001\u0013\t\u0019q\"q\u0003b\u0001%\u00111\u0011Ea\u0006C\u0002I!a\u0001\nB\f\u0005\u0004\u0011BAB\u0014\u0003\u0018\t\u0007!\u0003\u0002\u0004+\u0005/\u0011\rA\u0005\u0003\u0007[\t]!\u0019\u0001\n\u0005\rA\u00129B1\u0001\u0013\t\u0019\u0019$q\u0003b\u0001%!I!q\u0007\u0001\u0012\u0002\u0013\u0005!\u0011H\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00139+Y\u0011YDa\u0010\u0003B\t\r#Q\tB$\u0005\u0013\u0012YE!\u0014\u0003P\tESC\u0001B\u001fU\rY\u00131\f\u0003\u0007#\tU\"\u0019\u0001\n\u0005\rm\u0011)D1\u0001\u0013\t\u0019q\"Q\u0007b\u0001%\u00111\u0011E!\u000eC\u0002I!a\u0001\nB\u001b\u0005\u0004\u0011BAB\u0014\u00036\t\u0007!\u0003\u0002\u0004+\u0005k\u0011\rA\u0005\u0003\u0007[\tU\"\u0019\u0001\n\u0005\rA\u0012)D1\u0001\u0013\t\u0019\u0019$Q\u0007b\u0001%!I!Q\u000b\u0001\u0012\u0002\u0013\u0005!qK\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u0013:+Y\u0011IF!\u0018\u0003`\t\u0005$1\rB3\u0005O\u0012IGa\u001b\u0003n\t=TC\u0001B.U\rq\u00131\f\u0003\u0007#\tM#\u0019\u0001\n\u0005\rm\u0011\u0019F1\u0001\u0013\t\u0019q\"1\u000bb\u0001%\u00111\u0011Ea\u0015C\u0002I!a\u0001\nB*\u0005\u0004\u0011BAB\u0014\u0003T\t\u0007!\u0003\u0002\u0004+\u0005'\u0012\rA\u0005\u0003\u0007[\tM#\u0019\u0001\n\u0005\rA\u0012\u0019F1\u0001\u0013\t\u0019\u0019$1\u000bb\u0001%!I!1\u000f\u0001\u0012\u0002\u0013\u0005!QO\u0001\u0010G>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132aU1\"q\u000fB>\u0005{\u0012yH!!\u0003\u0004\n\u0015%q\u0011BE\u0005\u0017\u0013i)\u0006\u0002\u0003z)\u001a\u0011'a\u0017\u0005\rE\u0011\tH1\u0001\u0013\t\u0019Y\"\u0011\u000fb\u0001%\u00111aD!\u001dC\u0002I!a!\tB9\u0005\u0004\u0011BA\u0002\u0013\u0003r\t\u0007!\u0003\u0002\u0004(\u0005c\u0012\rA\u0005\u0003\u0007U\tE$\u0019\u0001\n\u0005\r5\u0012\tH1\u0001\u0013\t\u0019\u0001$\u0011\u000fb\u0001%\u001111G!\u001dC\u0002IA\u0011B!%\u0001\u0003\u0003%\tEa%\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005i\b\"\u0003BL\u0001\u0005\u0005I\u0011\tBM\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XC\u0001BN!\u0015\u0011iJa)\u0017\u001b\t\u0011yJC\u0002\u0003\"\n\t!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011)Ka(\u0003\u0011%#XM]1u_JD\u0011B!+\u0001\u0003\u0003%\tAa+\u0002\u0011\r\fg.R9vC2$BA!,\u00034B\u0019\u0001Ba,\n\u0007\tE&AA\u0004C_>dW-\u00198\t\u0013\tU&qUA\u0001\u0002\u00041\u0012a\u0001=%c!I!\u0011\u0018\u0001\u0002\u0002\u0013\u0005#1X\u0001\tQ\u0006\u001c\bnQ8eKR\u0011!Q\u0018\t\u0004\u0011\t}\u0016b\u0001Ba\u0005\t\u0019\u0011J\u001c;\t\u0013\t\u0015\u0007!!A\u0005B\t\u001d\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0003.\n%\u0007\"\u0003B[\u0005\u0007\f\t\u00111\u0001\u0017Q\u001d\u0001!Q\u001aBj\u0005/\u00042\u0001\u0003Bh\u0013\r\u0011\tN\u0001\u0002\u0016I\u0016\u0004(/Z2bi\u0016$\u0017J\u001c5fe&$\u0018M\\2fC\t\u0011).\u0001\u0018UkBdWm\u001d\u0011xS2d\u0007EY3![\u0006$W\r\t4j]\u0006d\u0007%\u001b8!C\u00022W\u000f^;sK\u00022XM]:j_:t\u0013E\u0001Bm\u0003\u0019\u0011d&M\u0019/a\u001dI!Q\u001c\u0002\u0002\u0002#\u0005!q\\\u0001\b)V\u0004H.Z\u00191!\rA!\u0011\u001d\u0004\t\u0003\t\t\t\u0011#\u0001\u0003dN!!\u0011]\u00048\u0011\u001di'\u0011\u001dC\u0001\u0005O$\"Aa8\t\u0011m\u0014\t/!A\u0005FqD!B!<\u0003b\u0006\u0005I\u0011\u0011Bx\u0003\u0015\t\u0007\u000f\u001d7z+Y\u0011\tPa>\u0003|\n}81AB\u0004\u0007\u0017\u0019yaa\u0005\u0004\u0018\rmAC\u0006Bz\u0007;\u0019yb!\t\u0004$\r\u00152qEB\u0015\u0007W\u0019ica\f\u0011-!\u0001!Q\u001fB}\u0005{\u001c\ta!\u0002\u0004\n\r51\u0011CB\u000b\u00073\u00012a\u0004B|\t\u0019\t\"1\u001eb\u0001%A\u0019qBa?\u0005\rm\u0011YO1\u0001\u0013!\ry!q \u0003\u0007=\t-(\u0019\u0001\n\u0011\u0007=\u0019\u0019\u0001\u0002\u0004\"\u0005W\u0014\rA\u0005\t\u0004\u001f\r\u001dAA\u0002\u0013\u0003l\n\u0007!\u0003E\u0002\u0010\u0007\u0017!aa\nBv\u0005\u0004\u0011\u0002cA\b\u0004\u0010\u00111!Fa;C\u0002I\u00012aDB\n\t\u0019i#1\u001eb\u0001%A\u0019qba\u0006\u0005\rA\u0012YO1\u0001\u0013!\ry11\u0004\u0003\u0007g\t-(\u0019\u0001\n\t\u000fm\u0012Y\u000f1\u0001\u0003v\"9\u0001Ia;A\u0002\te\bbB#\u0003l\u0002\u0007!Q \u0005\b\u0015\n-\b\u0019AB\u0001\u0011\u001dy%1\u001ea\u0001\u0007\u000bAq\u0001\u0016Bv\u0001\u0004\u0019I\u0001C\u0004Z\u0005W\u0004\ra!\u0004\t\u000fy\u0013Y\u000f1\u0001\u0004\u0012!91Ma;A\u0002\rU\u0001b\u00025\u0003l\u0002\u00071\u0011\u0004\u0005\u000b\u0007g\u0011\t/!A\u0005\u0002\u000eU\u0012aB;oCB\u0004H._\u000b\u0017\u0007o\u0019\u0019ea\u0012\u0004L\r=31KB,\u00077\u001ayfa\u0019\u0004hQ!1\u0011HB5!\u0015A11HB \u0013\r\u0019iD\u0001\u0002\u0007\u001fB$\u0018n\u001c8\u0011-!\u00011\u0011IB#\u0007\u0013\u001aie!\u0015\u0004V\re3QLB1\u0007K\u00022aDB\"\t\u0019\t2\u0011\u0007b\u0001%A\u0019qba\u0012\u0005\rm\u0019\tD1\u0001\u0013!\ry11\n\u0003\u0007=\rE\"\u0019\u0001\n\u0011\u0007=\u0019y\u0005\u0002\u0004\"\u0007c\u0011\rA\u0005\t\u0004\u001f\rMCA\u0002\u0013\u00042\t\u0007!\u0003E\u0002\u0010\u0007/\"aaJB\u0019\u0005\u0004\u0011\u0002cA\b\u0004\\\u00111!f!\rC\u0002I\u00012aDB0\t\u0019i3\u0011\u0007b\u0001%A\u0019qba\u0019\u0005\rA\u001a\tD1\u0001\u0013!\ry1q\r\u0003\u0007g\rE\"\u0019\u0001\n\t\u0015\r-4\u0011GA\u0001\u0002\u0004\u0019y$A\u0002yIAB!ba\u001c\u0003b\u0006\u0005I\u0011BB9\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0005\rM\u0004c\u0001@\u0004v%\u00191qO@\u0003\r=\u0013'.Z2u\u0001")
public class Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>
implements Product10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>,
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

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Option<Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>> unapply(Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> tuple10) {
        return Tuple10$.MODULE$.unapply(tuple10);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> apply(T1 T1, T2 T2, T3 T3, T4 T4, T5 T5, T6 T6, T7 T7, T8 T8, T9 T9, T10 T10) {
        return Tuple10$.MODULE$.apply(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10);
    }

    @Override
    public int productArity() {
        return Product10$class.productArity(this);
    }

    @Override
    public Object productElement(int n) throws IndexOutOfBoundsException {
        return Product10$class.productElement(this, n);
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

    public String toString() {
        return new StringBuilder().append((Object)"(").append(this._1()).append((Object)",").append(this._2()).append((Object)",").append(this._3()).append((Object)",").append(this._4()).append((Object)",").append(this._5()).append((Object)",").append(this._6()).append((Object)",").append(this._7()).append((Object)",").append(this._8()).append((Object)",").append(this._9()).append((Object)",").append(this._10()).append((Object)")").toString();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> copy(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10) {
        return new Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10);
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> T1 copy$default$1() {
        return this._1();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> T2 copy$default$2() {
        return this._2();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> T3 copy$default$3() {
        return this._3();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> T4 copy$default$4() {
        return this._4();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> T5 copy$default$5() {
        return this._5();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> T6 copy$default$6() {
        return this._6();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> T7 copy$default$7() {
        return this._7();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> T8 copy$default$8() {
        return this._8();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> T9 copy$default$9() {
        return this._9();
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> T10 copy$default$10() {
        return this._10();
    }

    @Override
    public String productPrefix() {
        return "Tuple10";
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof Tuple10;
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
        if (this == x$1) return true;
        if (!(x$1 instanceof Tuple10)) return false;
        boolean bl11 = true;
        if (!bl11) return false;
        Tuple10 tuple10 = (Tuple10)x$1;
        T1 T1 = tuple10._1();
        T1 T12 = this._1();
        if (T12 == T1) {
            bl10 = true;
        } else {
            if (T12 == null) {
                return false;
            }
            bl10 = T12 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T12, T1) : (T12 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T12, T1) : T12.equals(T1));
        }
        if (!bl10) return false;
        T2 T2 = tuple10._2();
        T2 T22 = this._2();
        if (T22 == T2) {
            bl9 = true;
        } else {
            if (T22 == null) {
                return false;
            }
            bl9 = T22 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T22, T2) : (T22 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T22, T2) : T22.equals(T2));
        }
        if (!bl9) return false;
        T3 T3 = tuple10._3();
        T3 T32 = this._3();
        if (T32 == T3) {
            bl8 = true;
        } else {
            if (T32 == null) {
                return false;
            }
            bl8 = T32 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T32, T3) : (T32 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T32, T3) : T32.equals(T3));
        }
        if (!bl8) return false;
        T4 T4 = tuple10._4();
        T4 T42 = this._4();
        if (T42 == T4) {
            bl7 = true;
        } else {
            if (T42 == null) {
                return false;
            }
            bl7 = T42 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T42, T4) : (T42 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T42, T4) : T42.equals(T4));
        }
        if (!bl7) return false;
        T5 T5 = tuple10._5();
        T5 T52 = this._5();
        if (T52 == T5) {
            bl6 = true;
        } else {
            if (T52 == null) {
                return false;
            }
            bl6 = T52 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T52, T5) : (T52 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T52, T5) : T52.equals(T5));
        }
        if (!bl6) return false;
        T6 T6 = tuple10._6();
        T6 T62 = this._6();
        if (T62 == T6) {
            bl5 = true;
        } else {
            if (T62 == null) {
                return false;
            }
            bl5 = T62 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T62, T6) : (T62 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T62, T6) : T62.equals(T6));
        }
        if (!bl5) return false;
        T7 T7 = tuple10._7();
        T7 T72 = this._7();
        if (T72 == T7) {
            bl4 = true;
        } else {
            if (T72 == null) {
                return false;
            }
            bl4 = T72 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T72, T7) : (T72 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T72, T7) : T72.equals(T7));
        }
        if (!bl4) return false;
        T8 T8 = tuple10._8();
        T8 T82 = this._8();
        if (T82 == T8) {
            bl3 = true;
        } else {
            if (T82 == null) {
                return false;
            }
            bl3 = T82 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T82, T8) : (T82 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T82, T8) : T82.equals(T8));
        }
        if (!bl3) return false;
        T9 T9 = tuple10._9();
        T9 T92 = this._9();
        if (T92 == T9) {
            bl2 = true;
        } else {
            if (T92 == null) {
                return false;
            }
            bl2 = T92 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T92, T9) : (T92 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T92, T9) : T92.equals(T9));
        }
        if (!bl2) return false;
        T10 T10 = tuple10._10();
        T10 T102 = this._10();
        if (T102 == T10) {
            bl = true;
        } else {
            if (T102 == null) {
                return false;
            }
            bl = T102 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T102, T10) : (T102 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T102, T10) : T102.equals(T10));
        }
        if (!bl) return false;
        if (!tuple10.canEqual(this)) return false;
        return true;
    }

    public Tuple10(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10) {
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
        Product$class.$init$(this);
        Product10$class.$init$(this);
    }
}

