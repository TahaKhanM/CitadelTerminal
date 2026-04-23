/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Proxy;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableView;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\u0011=eaB\u0001\u0003!\u0003\r\ta\u0002\u0002\u0015)J\fg/\u001a:tC\ndW\r\u0015:pqfd\u0015n[3\u000b\u0005\r!\u0011AC2pY2,7\r^5p]*\tQ!A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0007!\u0019Rd\u0005\u0003\u0001\u001351\u0003C\u0001\u0006\f\u001b\u0005!\u0011B\u0001\u0007\u0005\u0005\u0019\te.\u001f*fMB!abD\t\u001d\u001b\u0005\u0011\u0011B\u0001\t\u0003\u0005=!&/\u0019<feN\f'\r\\3MS.,\u0007C\u0001\n\u0014\u0019\u0001!a\u0001\u0006\u0001\u0005\u0006\u0004)\"!A!\u0012\u0005YI\u0002C\u0001\u0006\u0018\u0013\tABAA\u0004O_RD\u0017N\\4\u0011\u0005)Q\u0012BA\u000e\u0005\u0005\r\te.\u001f\t\u0003%u!aA\b\u0001\u0005\u0006\u0004y\"\u0001\u0002*faJ\f\"A\u0006\u0011\u0013\u0007\u0005j1E\u0002\u0003#\u0001\u0001\u0001#\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004c\u0001\b%#%\u0011QE\u0001\u0002\f)J\fg/\u001a:tC\ndW\r\u0005\u0002\u000bO%\u0011\u0001\u0006\u0002\u0002\u0006!J|\u00070\u001f\u0005\u0006U\u0001!\taK\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u00031\u0002\"AC\u0017\n\u00059\"!\u0001B+oSRDQ\u0001\r\u0001\u0007\u0002E\nAa]3mMV\tA\u0004C\u00034\u0001\u0011\u0005C'A\u0004g_J,\u0017m\u00195\u0016\u0005UbDC\u0001\u00177\u0011\u00159$\u00071\u00019\u0003\u00051\u0007\u0003\u0002\u0006:#mJ!A\u000f\u0003\u0003\u0013\u0019+hn\u0019;j_:\f\u0004C\u0001\n=\t\u0015i$G1\u0001\u0016\u0005\u0005)\u0006\"B \u0001\t\u0003\u0002\u0015aB5t\u000b6\u0004H/_\u000b\u0002\u0003B\u0011!BQ\u0005\u0003\u0007\u0012\u0011qAQ8pY\u0016\fg\u000eC\u0003F\u0001\u0011\u0005\u0003)\u0001\u0005o_:,U\u000e\u001d;z\u0011\u00159\u0005\u0001\"\u0011I\u0003\u0011\u0019\u0018N_3\u0016\u0003%\u0003\"A\u0003&\n\u0005-#!aA%oi\")Q\n\u0001C!\u0001\u0006y\u0001.Y:EK\u001aLg.\u001b;f'&TX\rC\u0003P\u0001\u0011\u0005\u0003+\u0001\u0006%a2,8\u000f\n9mkN,2!U0U)\t\u0011&\r\u0006\u0002T-B\u0011!\u0003\u0016\u0003\u0006+:\u0013\r!\u0006\u0002\u0005)\"\fG\u000fC\u0003X\u001d\u0002\u000f\u0001,\u0001\u0002cMB)\u0011\f\u0018\u000f_'6\t!L\u0003\u0002\\\u0005\u00059q-\u001a8fe&\u001c\u0017BA/[\u00051\u0019\u0015M\u001c\"vS2$gI]8n!\t\u0011r\fB\u0003a\u001d\n\u0007\u0011MA\u0001C#\t\t\u0012\u0004C\u0003d\u001d\u0002\u0007A-\u0001\u0002ygB\u0019a\"\u001a0\n\u0005\u0019\u0014!AE$f]R\u0013\u0018M^3sg\u0006\u0014G.Z(oG\u0016DQ\u0001\u001b\u0001\u0005B%\f1!\\1q+\rQ\u0017/\u001c\u000b\u0003WJ$\"\u0001\u001c8\u0011\u0005IiG!B+h\u0005\u0004)\u0002\"B,h\u0001\by\u0007#B-]9Ad\u0007C\u0001\nr\t\u0015\u0001wM1\u0001\u0016\u0011\u00159t\r1\u0001t!\u0011Q\u0011(\u00059\t\u000bU\u0004A\u0011\t<\u0002\u000f\u0019d\u0017\r^'baV\u0019qO >\u0015\u0005a|HCA=|!\t\u0011\"\u0010B\u0003Vi\n\u0007Q\u0003C\u0003Xi\u0002\u000fA\u0010E\u0003Z9ri\u0018\u0010\u0005\u0002\u0013}\u0012)\u0001\r\u001eb\u0001+!1q\u0007\u001ea\u0001\u0003\u0003\u0001RAC\u001d\u0012\u0003\u0007\u00012AD3~\u0011\u001d\t9\u0001\u0001C!\u0003\u0013\taAZ5mi\u0016\u0014Hc\u0001\u000f\u0002\f!A\u0011QBA\u0003\u0001\u0004\ty!A\u0001q!\u0011Q\u0011(E!\t\u000f\u0005M\u0001\u0001\"\u0011\u0002\u0016\u0005Ia-\u001b7uKJtu\u000e\u001e\u000b\u00049\u0005]\u0001\u0002CA\u0007\u0003#\u0001\r!a\u0004\t\u000f\u0005m\u0001\u0001\"\u0011\u0002\u001e\u000591m\u001c7mK\u000e$XCBA\u0010\u0003[\t)\u0003\u0006\u0003\u0002\"\u0005=B\u0003BA\u0012\u0003O\u00012AEA\u0013\t\u0019)\u0016\u0011\u0004b\u0001+!9q+!\u0007A\u0004\u0005%\u0002cB-]9\u0005-\u00121\u0005\t\u0004%\u00055BA\u00021\u0002\u001a\t\u0007Q\u0003\u0003\u0005\u00022\u0005e\u0001\u0019AA\u001a\u0003\t\u0001h\r\u0005\u0004\u000b\u0003k\t\u00121F\u0005\u0004\u0003o!!a\u0004)beRL\u0017\r\u001c$v]\u000e$\u0018n\u001c8\t\u000f\u0005m\u0002\u0001\"\u0011\u0002>\u0005I\u0001/\u0019:uSRLwN\u001c\u000b\u0005\u0003\u007f\t)\u0005E\u0003\u000b\u0003\u0003bB$C\u0002\u0002D\u0011\u0011a\u0001V;qY\u0016\u0014\u0004\u0002CA\u0007\u0003s\u0001\r!a\u0004\t\u000f\u0005%\u0003\u0001\"\u0011\u0002L\u00059qM]8va\nKX\u0003BA'\u0003;\"B!a\u0014\u0002bA9\u0011\u0011KA,\u00037bRBAA*\u0015\r\t)FA\u0001\nS6lW\u000f^1cY\u0016LA!!\u0017\u0002T\t\u0019Q*\u00199\u0011\u0007I\ti\u0006B\u0004\u0002`\u0005\u001d#\u0019A\u000b\u0003\u0003-CqaNA$\u0001\u0004\t\u0019\u0007E\u0003\u000bsE\tY\u0006C\u0004\u0002h\u0001!\t%!\u001b\u0002\r\u0019|'/\u00197m)\r\t\u00151\u000e\u0005\t\u0003\u001b\t)\u00071\u0001\u0002\u0010!9\u0011q\u000e\u0001\u0005B\u0005E\u0014AB3ySN$8\u000fF\u0002B\u0003gB\u0001\"!\u0004\u0002n\u0001\u0007\u0011q\u0002\u0005\b\u0003o\u0002A\u0011IA=\u0003\u0015\u0019w.\u001e8u)\rI\u00151\u0010\u0005\t\u0003\u001b\t)\b1\u0001\u0002\u0010!9\u0011q\u0010\u0001\u0005B\u0005\u0005\u0015\u0001\u00024j]\u0012$B!a!\u0002\nB!!\"!\"\u0012\u0013\r\t9\t\u0002\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u00055\u0011Q\u0010a\u0001\u0003\u001fAq!!$\u0001\t\u0003\ny)\u0001\u0005g_2$G*\u001a4u+\u0011\t\t*a&\u0015\t\u0005M\u00151\u0015\u000b\u0005\u0003+\u000bI\nE\u0002\u0013\u0003/#a\u0001YAF\u0005\u0004)\u0002\u0002CAN\u0003\u0017\u0003\r!!(\u0002\u0005=\u0004\b\u0003\u0003\u0006\u0002 \u0006U\u0015#!&\n\u0007\u0005\u0005FAA\u0005Gk:\u001cG/[8oe!A\u0011QUAF\u0001\u0004\t)*A\u0001{\u0011\u001d\tI\u000b\u0001C!\u0003W\u000b!\u0002\n3jm\u0012\u001aw\u000e\\8o+\u0011\ti+a-\u0015\t\u0005=\u0016\u0011\u0018\u000b\u0005\u0003c\u000b)\fE\u0002\u0013\u0003g#a\u0001YAT\u0005\u0004)\u0002\u0002CAN\u0003O\u0003\r!a.\u0011\u0011)\ty*!-\u0012\u0003cC\u0001\"!*\u0002(\u0002\u0007\u0011\u0011\u0017\u0005\b\u0003{\u0003A\u0011IA`\u0003%1w\u000e\u001c3SS\u001eDG/\u0006\u0003\u0002B\u0006\u001dG\u0003BAb\u0003\u001b$B!!2\u0002JB\u0019!#a2\u0005\r\u0001\fYL1\u0001\u0016\u0011!\tY*a/A\u0002\u0005-\u0007\u0003\u0003\u0006\u0002 F\t)-!2\t\u0011\u0005\u0015\u00161\u0018a\u0001\u0003\u000bDq!!5\u0001\t\u0003\n\u0019.A\u0007%G>dwN\u001c\u0013cg2\f7\u000f[\u000b\u0005\u0003+\fY\u000e\u0006\u0003\u0002X\u0006\u0005H\u0003BAm\u0003;\u00042AEAn\t\u0019\u0001\u0017q\u001ab\u0001+!A\u00111TAh\u0001\u0004\ty\u000e\u0005\u0005\u000b\u0003?\u000b\u0012\u0011\\Am\u0011!\t)+a4A\u0002\u0005e\u0007bBAs\u0001\u0011\u0005\u0013q]\u0001\u000be\u0016$WoY3MK\u001a$X\u0003BAu\u0003[$B!a;\u0002pB\u0019!#!<\u0005\r\u0001\f\u0019O1\u0001b\u0011!\tY*a9A\u0002\u0005E\b\u0003\u0003\u0006\u0002 \u0006-\u0018#a;\t\u000f\u0005U\b\u0001\"\u0011\u0002x\u0006\u0001\"/\u001a3vG\u0016dUM\u001a;PaRLwN\\\u000b\u0005\u0003s\fy\u0010\u0006\u0003\u0002|\n\u0005\u0001#\u0002\u0006\u0002\u0006\u0006u\bc\u0001\n\u0002\u0000\u00121\u0001-a=C\u0002\u0005D\u0001\"a'\u0002t\u0002\u0007!1\u0001\t\t\u0015\u0005}\u0015Q`\t\u0002~\"9!q\u0001\u0001\u0005B\t%\u0011a\u0003:fIV\u001cWMU5hQR,BAa\u0003\u0003\u0010Q!!Q\u0002B\t!\r\u0011\"q\u0002\u0003\u0007A\n\u0015!\u0019A1\t\u0011\u0005m%Q\u0001a\u0001\u0005'\u0001\u0002BCAP#\t5!Q\u0002\u0005\b\u0005/\u0001A\u0011\tB\r\u0003E\u0011X\rZ;dKJKw\r\u001b;PaRLwN\\\u000b\u0005\u00057\u0011\t\u0003\u0006\u0003\u0003\u001e\t\r\u0002#\u0002\u0006\u0002\u0006\n}\u0001c\u0001\n\u0003\"\u00111\u0001M!\u0006C\u0002\u0005D\u0001\"a'\u0003\u0016\u0001\u0007!Q\u0005\t\t\u0015\u0005}\u0015Ca\b\u0003 !9!\u0011\u0006\u0001\u0005B\t-\u0012\u0001C:dC:dUM\u001a;\u0016\r\t5\"Q\bB\u001b)\u0011\u0011yCa\u0011\u0015\t\tE\"q\b\u000b\u0005\u0005g\u00119\u0004E\u0002\u0013\u0005k!a!\u0016B\u0014\u0005\u0004)\u0002bB,\u0003(\u0001\u000f!\u0011\b\t\b3rc\"1\bB\u001a!\r\u0011\"Q\b\u0003\u0007A\n\u001d\"\u0019A\u000b\t\u0011\u0005m%q\u0005a\u0001\u0005\u0003\u0002\u0002BCAP\u0005w\t\"1\b\u0005\t\u0003K\u00139\u00031\u0001\u0003<!9!q\t\u0001\u0005B\t%\u0013!C:dC:\u0014\u0016n\u001a5u+\u0019\u0011YEa\u0017\u0003TQ!!Q\nB1)\u0011\u0011yE!\u0018\u0015\t\tE#Q\u000b\t\u0004%\tMCAB+\u0003F\t\u0007Q\u0003C\u0004X\u0005\u000b\u0002\u001dAa\u0016\u0011\u000fecFD!\u0017\u0003RA\u0019!Ca\u0017\u0005\r\u0001\u0014)E1\u0001\u0016\u0011!\tYJ!\u0012A\u0002\t}\u0003\u0003\u0003\u0006\u0002 F\u0011IF!\u0017\t\u0011\u0005\u0015&Q\ta\u0001\u00053BqA!\u001a\u0001\t\u0003\u00129'A\u0002tk6,BA!\u001b\u0003nQ!!1\u000eB8!\r\u0011\"Q\u000e\u0003\u0007A\n\r$\u0019A1\t\u0011\tE$1\ra\u0002\u0005g\n1A\\;n!\u0019\u0011)Ha\u001f\u0003l9\u0019!Ba\u001e\n\u0007\teD!A\u0004qC\u000e\\\u0017mZ3\n\t\tu$q\u0010\u0002\b\u001dVlWM]5d\u0015\r\u0011I\b\u0002\u0005\b\u0005\u0007\u0003A\u0011\tBC\u0003\u001d\u0001(o\u001c3vGR,BAa\"\u0003\fR!!\u0011\u0012BG!\r\u0011\"1\u0012\u0003\u0007A\n\u0005%\u0019A1\t\u0011\tE$\u0011\u0011a\u0002\u0005\u001f\u0003bA!\u001e\u0003|\t%\u0005b\u0002BJ\u0001\u0011\u0005#QS\u0001\u0004[&tW\u0003\u0002BL\u0005K#2!\u0005BM\u0011!\u0011YJ!%A\u0004\tu\u0015aA2naB1!Q\u000fBP\u0005GKAA!)\u0003\u0000\tAqJ\u001d3fe&tw\rE\u0002\u0013\u0005K#a\u0001\u0019BI\u0005\u0004\t\u0007b\u0002BU\u0001\u0011\u0005#1V\u0001\u0004[\u0006DX\u0003\u0002BW\u0005k#2!\u0005BX\u0011!\u0011YJa*A\u0004\tE\u0006C\u0002B;\u0005?\u0013\u0019\fE\u0002\u0013\u0005k#a\u0001\u0019BT\u0005\u0004\t\u0007b\u0002B]\u0001\u0011\u0005#1X\u0001\u0005Q\u0016\fG-F\u0001\u0012\u0011\u001d\u0011y\f\u0001C!\u0005\u0003\f!\u0002[3bI>\u0003H/[8o+\t\t\u0019\t\u0003\u0004\u0003F\u0002!\t%M\u0001\u0005i\u0006LG\u000eC\u0004\u0003J\u0002!\tEa/\u0002\t1\f7\u000f\u001e\u0005\b\u0005\u001b\u0004A\u0011\tBa\u0003)a\u0017m\u001d;PaRLwN\u001c\u0005\u0007\u0005#\u0004A\u0011I\u0019\u0002\t%t\u0017\u000e\u001e\u0005\b\u0005+\u0004A\u0011\tBl\u0003\u0011!\u0018m[3\u0015\u0007q\u0011I\u000eC\u0004\u0003\\\nM\u0007\u0019A%\u0002\u00039DqAa8\u0001\t\u0003\u0012\t/\u0001\u0003ee>\u0004Hc\u0001\u000f\u0003d\"9!1\u001cBo\u0001\u0004I\u0005b\u0002Bt\u0001\u0011\u0005#\u0011^\u0001\u0006g2L7-\u001a\u000b\u00069\t-(q\u001e\u0005\b\u0005[\u0014)\u000f1\u0001J\u0003\u00111'o\\7\t\u000f\tE(Q\u001da\u0001\u0013\u0006)QO\u001c;jY\"9!Q\u001f\u0001\u0005B\t]\u0018!\u0003;bW\u0016<\u0006.\u001b7f)\ra\"\u0011 \u0005\t\u0003\u001b\u0011\u0019\u00101\u0001\u0002\u0010!9!Q \u0001\u0005B\t}\u0018!\u00033s_B<\u0006.\u001b7f)\ra2\u0011\u0001\u0005\t\u0003\u001b\u0011Y\u00101\u0001\u0002\u0010!91Q\u0001\u0001\u0005B\r\u001d\u0011\u0001B:qC:$B!a\u0010\u0004\n!A\u0011QBB\u0002\u0001\u0004\ty\u0001C\u0004\u0004\u000e\u0001!\tea\u0004\u0002\u000fM\u0004H.\u001b;BiR!\u0011qHB\t\u0011\u001d\u0011Yna\u0003A\u0002%Cqa!\u0006\u0001\t\u0003\u001a9\"\u0001\u0007d_BLHk\u001c\"vM\u001a,'/\u0006\u0003\u0004\u001a\r5Bc\u0001\u0017\u0004\u001c!A1QDB\n\u0001\u0004\u0019y\"\u0001\u0003eKN$\bCBB\u0011\u0007O\u0019Y#\u0004\u0002\u0004$)\u00191Q\u0005\u0002\u0002\u000f5,H/\u00192mK&!1\u0011FB\u0012\u0005\u0019\u0011UO\u001a4feB\u0019!c!\f\u0005\r\u0001\u001c\u0019B1\u0001b\u0011\u001d\u0019\t\u0004\u0001C!\u0007g\t1bY8qsR{\u0017I\u001d:bsV!1QGB!)\u001da3qGB\"\u0007\u000fBqaYB\u0018\u0001\u0004\u0019I\u0004E\u0003\u000b\u0007w\u0019y$C\u0002\u0004>\u0011\u0011Q!\u0011:sCf\u00042AEB!\t\u0019\u00017q\u0006b\u0001C\"91QIB\u0018\u0001\u0004I\u0015!B:uCJ$\bbBB%\u0007_\u0001\r!S\u0001\u0004Y\u0016t\u0007bBB\u0019\u0001\u0011\u00053QJ\u000b\u0005\u0007\u001f\u001a9\u0006F\u0003-\u0007#\u001aI\u0006C\u0004d\u0007\u0017\u0002\raa\u0015\u0011\u000b)\u0019Yd!\u0016\u0011\u0007I\u00199\u0006\u0002\u0004a\u0007\u0017\u0012\r!\u0019\u0005\b\u0007\u000b\u001aY\u00051\u0001J\u0011\u001d\u0019\t\u0004\u0001C!\u0007;*Baa\u0018\u0004hQ\u0019Af!\u0019\t\u000f\r\u001cY\u00061\u0001\u0004dA)!ba\u000f\u0004fA\u0019!ca\u001a\u0005\r\u0001\u001cYF1\u0001b\u0011\u001d\u0019Y\u0007\u0001C!\u0007[\nq\u0001^8BeJ\f\u00170\u0006\u0003\u0004p\rUD\u0003BB9\u0007o\u0002RACB\u001e\u0007g\u00022AEB;\t\u0019\u00017\u0011\u000eb\u0001C\"Q1\u0011PB5\u0003\u0003\u0005\u001daa\u001f\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0004\u0004~\r\r51O\u0007\u0003\u0007\u007fR1a!!\u0005\u0003\u001d\u0011XM\u001a7fGRLAa!\"\u0004\u0000\tA1\t\\1tgR\u000bw\rC\u0004\u0004\n\u0002!\tea#\u0002\rQ|G*[:u+\t\u0019i\tE\u0003\u0003v\r=\u0015#\u0003\u0003\u0004\u0012\n}$\u0001\u0002'jgRDqa!&\u0001\t\u0003\u001a9*\u0001\u0006u_&#XM]1cY\u0016,\"a!'\u0011\t9\u0019Y*E\u0005\u0004\u0007;\u0013!\u0001C%uKJ\f'\r\\3\t\u000f\r\u0005\u0006\u0001\"\u0011\u0004$\u0006)Ao\\*fcV\u00111Q\u0015\t\u0005\u001d\r\u001d\u0016#C\u0002\u0004*\n\u00111aU3r\u0011\u001d\u0019i\u000b\u0001C!\u0007_\u000bA\u0002^8J]\u0012,\u00070\u001a3TKF,\"a!-\u0011\u000b\u0005E31W\t\n\t\rU\u00161\u000b\u0002\u000b\u0013:$W\r_3e'\u0016\f\bbBB]\u0001\u0011\u000531X\u0001\ti>\u0014UO\u001a4feV!1QXBb+\t\u0019y\f\u0005\u0004\u0004\"\r\u001d2\u0011\u0019\t\u0004%\r\rGA\u00021\u00048\n\u0007\u0011\rC\u0004\u0004H\u0002!\te!3\u0002\u0011Q|7\u000b\u001e:fC6,\"aa3\u0011\u000b\tU4QZ\t\n\t\r='q\u0010\u0002\u0007'R\u0014X-Y7\t\u000f\rM\u0007\u0001\"\u0011\u0004V\u0006)Ao\\*fiV!1q[Bq+\t\u0019I\u000e\u0005\u0004\u0002R\rm7q\\\u0005\u0005\u0007;\f\u0019FA\u0002TKR\u00042AEBq\t\u0019\u00017\u0011\u001bb\u0001C\"91Q\u001d\u0001\u0005B\r\u001d\u0018!\u0002;p\u001b\u0006\u0004XCBBu\u0007_\u001c)\u0010\u0006\u0003\u0004l\u000e]\b\u0003CA)\u0003/\u001aioa=\u0011\u0007I\u0019y\u000fB\u0004\u0004r\u000e\r(\u0019A\u000b\u0003\u0003Q\u00032AEB{\t\u0019i41\u001db\u0001+!A1\u0011`Br\u0001\b\u0019Y0\u0001\u0002fmB91Q C\u0002#\u0011%ab\u0001\u0006\u0004\u0000&\u0019A\u0011\u0001\u0003\u0002\rA\u0013X\rZ3g\u0013\u0011!)\u0001b\u0002\u0003!\u0011bWm]:%G>dwN\u001c\u0013mKN\u001c(b\u0001C\u0001\tA9!\"!\u0011\u0004n\u000eM\bb\u0002C\u0007\u0001\u0011\u0005CqB\u0001\u000ei>$&/\u0019<feN\f'\r\\3\u0016\u0003\rBq\u0001b\u0005\u0001\t\u0003\")\"\u0001\u0006u_&#XM]1u_J,\"\u0001b\u0006\u0011\t9!I\"E\u0005\u0004\t7\u0011!\u0001C%uKJ\fGo\u001c:\t\u000f\u0011}\u0001\u0001\"\u0011\u0005\"\u0005AQn[*ue&tw\r\u0006\u0005\u0005$\u0011%B1\u0006C\u0018!\u0011\u0019i\u0010\"\n\n\t\u0011\u001dBq\u0001\u0002\u0007'R\u0014\u0018N\\4\t\u0011\r\u0015CQ\u0004a\u0001\tGA\u0001\u0002\"\f\u0005\u001e\u0001\u0007A1E\u0001\u0004g\u0016\u0004\b\u0002\u0003C\u0019\t;\u0001\r\u0001b\t\u0002\u0007\u0015tG\rC\u0004\u0005 \u0001!\t\u0005\"\u000e\u0015\t\u0011\rBq\u0007\u0005\t\t[!\u0019\u00041\u0001\u0005$!9Aq\u0004\u0001\u0005B\u0011mRC\u0001C\u0012\u0011\u001d!y\u0004\u0001C!\t\u0003\n\u0011\"\u00193e'R\u0014\u0018N\\4\u0015\u0015\u0011\rC\u0011\nC'\t\u001f\"\t\u0006\u0005\u0003\u0004\"\u0011\u0015\u0013\u0002\u0002C$\u0007G\u0011Qb\u0015;sS:<')^5mI\u0016\u0014\b\u0002\u0003C&\t{\u0001\r\u0001b\u0011\u0002\u0003\tD\u0001b!\u0012\u0005>\u0001\u0007A1\u0005\u0005\t\t[!i\u00041\u0001\u0005$!AA\u0011\u0007C\u001f\u0001\u0004!\u0019\u0003C\u0004\u0005@\u0001!\t\u0005\"\u0016\u0015\r\u0011\rCq\u000bC-\u0011!!Y\u0005b\u0015A\u0002\u0011\r\u0003\u0002\u0003C\u0017\t'\u0002\r\u0001b\t\t\u000f\u0011}\u0002\u0001\"\u0011\u0005^Q!A1\tC0\u0011!!Y\u0005b\u0017A\u0002\u0011\r\u0003b\u0002C2\u0001\u0011\u0005C1H\u0001\rgR\u0014\u0018N\\4Qe\u00164\u0017\u000e\u001f\u0005\b\tO\u0002A\u0011\tC5\u0003\u00111\u0018.Z<\u0016\u0005\u0011-$#\u0002C7\u0013\u0011Ed!\u0002\u0012\u0001\u0001\u0011-\u0014b\u0001C4\u001fA)a\u0002b\u001d\u00129%\u0019AQ\u000f\u0002\u0003\u001fQ\u0013\u0018M^3sg\u0006\u0014G.\u001a,jK^Dq\u0001b\u001a\u0001\t\u0003\"I\b\u0006\u0004\u0005r\u0011mDQ\u0010\u0005\b\u0005[$9\b1\u0001J\u0011\u001d\u0011\t\u0010b\u001eA\u0002%Cs\u0001\u0001CA\t\u000f#Y\tE\u0002\u000b\t\u0007K1\u0001\"\"\u0005\u0005)!W\r\u001d:fG\u0006$X\rZ\u0011\u0003\t\u0013\u000bQ\t\u0015:pqfLgn\u001a\u0011jg\u0002\"W\r\u001d:fG\u0006$X\r\u001a\u0011ek\u0016\u0004Co\u001c\u0011mC\u000e\\\u0007e\u001c4!kN,\u0007%\u00198eA\r|W\u000e]5mKJlC.\u001a<fY\u0002\u001aX\u000f\u001d9peRt\u0013E\u0001CG\u0003\u0019\u0011d&M\u0019/a\u0001")
public interface TraversableProxyLike<A, Repr extends TraversableLike<A, Repr> & Traversable<A>>
extends TraversableLike<A, Repr>,
Proxy {
    public Repr self();

    @Override
    public <U> void foreach(Function1<A, U> var1);

    @Override
    public boolean isEmpty();

    @Override
    public boolean nonEmpty();

    @Override
    public int size();

    @Override
    public boolean hasDefiniteSize();

    @Override
    public <B, That> That $plus$plus(GenTraversableOnce<B> var1, CanBuildFrom<Repr, B, That> var2);

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
    public int count(Function1<A, Object> var1);

    @Override
    public Option<A> find(Function1<A, Object> var1);

    @Override
    public <B> B foldLeft(B var1, Function2<B, A, B> var2);

    @Override
    public <B> B $div$colon(B var1, Function2<B, A, B> var2);

    @Override
    public <B> B foldRight(B var1, Function2<A, B, B> var2);

    @Override
    public <B> B $colon$bslash(B var1, Function2<A, B, B> var2);

    @Override
    public <B> B reduceLeft(Function2<B, A, B> var1);

    @Override
    public <B> Option<B> reduceLeftOption(Function2<B, A, B> var1);

    @Override
    public <B> B reduceRight(Function2<A, B, B> var1);

    @Override
    public <B> Option<B> reduceRightOption(Function2<A, B, B> var1);

    @Override
    public <B, That> That scanLeft(B var1, Function2<B, A, B> var2, CanBuildFrom<Repr, B, That> var3);

    @Override
    public <B, That> That scanRight(B var1, Function2<A, B, B> var2, CanBuildFrom<Repr, B, That> var3);

    @Override
    public <B> B sum(Numeric<B> var1);

    @Override
    public <B> B product(Numeric<B> var1);

    @Override
    public <B> A min(Ordering<B> var1);

    @Override
    public <B> A max(Ordering<B> var1);

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

    @Override
    public Repr takeWhile(Function1<A, Object> var1);

    @Override
    public Repr dropWhile(Function1<A, Object> var1);

    @Override
    public Tuple2<Repr, Repr> span(Function1<A, Object> var1);

    @Override
    public Tuple2<Repr, Repr> splitAt(int var1);

    @Override
    public <B> void copyToBuffer(Buffer<B> var1);

    @Override
    public <B> void copyToArray(Object var1, int var2, int var3);

    @Override
    public <B> void copyToArray(Object var1, int var2);

    @Override
    public <B> void copyToArray(Object var1);

    @Override
    public <B> Object toArray(ClassTag<B> var1);

    @Override
    public List<A> toList();

    @Override
    public Iterable<A> toIterable();

    @Override
    public Seq<A> toSeq();

    @Override
    public IndexedSeq<A> toIndexedSeq();

    @Override
    public <B> Buffer<B> toBuffer();

    @Override
    public Stream<A> toStream();

    @Override
    public <B> Set<B> toSet();

    @Override
    public <T, U> Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> var1);

    @Override
    public Traversable<A> toTraversable();

    @Override
    public Iterator<A> toIterator();

    @Override
    public String mkString(String var1, String var2, String var3);

    @Override
    public String mkString(String var1);

    @Override
    public String mkString();

    @Override
    public StringBuilder addString(StringBuilder var1, String var2, String var3, String var4);

    @Override
    public StringBuilder addString(StringBuilder var1, String var2);

    @Override
    public StringBuilder addString(StringBuilder var1);

    @Override
    public String stringPrefix();

    @Override
    public Object view();

    @Override
    public TraversableView<A, Repr> view(int var1, int var2);
}

