/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.MatchError;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterator;
import scala.collection.AbstractSeq;
import scala.collection.CustomParallelizable;
import scala.collection.CustomParallelizable$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversableOnce;
import scala.collection.IndexedSeq;
import scala.collection.IndexedSeqLike$class;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.Seq;
import scala.collection.SeqLike$class;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.IndexedSeq$;
import scala.collection.immutable.IndexedSeq$class;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.Seq$;
import scala.collection.immutable.Seq$class;
import scala.collection.immutable.Traversable$class;
import scala.collection.immutable.Vector$;
import scala.collection.immutable.VectorIterator;
import scala.collection.immutable.VectorPointer;
import scala.collection.immutable.VectorPointer$class;
import scala.collection.mutable.Buffer;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParVector;
import scala.compat.Platform$;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;

@ScalaSignature(bytes="\u0006\u0001\rut!B\u0001\u0003\u0011\u0003I\u0011A\u0002,fGR|'O\u0003\u0002\u0004\t\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003\u000b\u0019\t!bY8mY\u0016\u001cG/[8o\u0015\u00059\u0011!B:dC2\f7\u0001\u0001\t\u0003\u0015-i\u0011A\u0001\u0004\u0006\u0019\tA\t!\u0004\u0002\u0007-\u0016\u001cGo\u001c:\u0014\u0007-qq\bE\u0002\u0010%Qi\u0011\u0001\u0005\u0006\u0003#\u0011\tqaZ3oKJL7-\u0003\u0002\u0014!\t\t\u0012J\u001c3fq\u0016$7+Z9GC\u000e$xN]=\u0011\u0005))b!\u0002\u0007\u0003\u0005Y\tTCA\f\u001f'!)\u0002\u0004K\u0016/e}\u0012\u0005cA\r\u001b95\tA!\u0003\u0002\u001c\t\tY\u0011IY:ue\u0006\u001cGoU3r!\tib\u0004\u0004\u0001\u0005\r})BQ1\u0001!\u0005\u0005\t\u0015CA\u0011&!\t\u00113%D\u0001\u0007\u0013\t!cAA\u0004O_RD\u0017N\\4\u0011\u0005\t2\u0013BA\u0014\u0007\u0005\r\te.\u001f\t\u0004\u0015%b\u0012B\u0001\u0016\u0003\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\t\u0005\u001f1bB#\u0003\u0002.!\tQr)\u001a8fe&\u001cGK]1wKJ\u001c\u0018M\u00197f)\u0016l\u0007\u000f\\1uKB!\u0011d\f\u000f2\u0013\t\u0001DA\u0001\bJ]\u0012,\u00070\u001a3TKFd\u0015n[3\u0011\u0007))B\u0004E\u0002\u000bgUJ!\u0001\u000e\u0002\u0003\u001bY+7\r^8s!>Lg\u000e^3sU\tabgK\u00018!\tAT(D\u0001:\u0015\tQ4(A\u0005v]\u000eDWmY6fI*\u0011AHB\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001 :\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\t\u0003E\u0001K!!\u0011\u0004\u0003\u0019M+'/[1mSj\f'\r\\3\u0011\te\u0019E$R\u0005\u0003\t\u0012\u0011AcQ;ti>l\u0007+\u0019:bY2,G.\u001b>bE2,\u0007c\u0001$K95\tqI\u0003\u0002\u0004\u0011*\u0011\u0011\nB\u0001\ta\u0006\u0014\u0018\r\u001c7fY&\u00111j\u0012\u0002\n!\u0006\u0014h+Z2u_JD\u0011\"T\u000b\u0003\u0006\u0004%\t\u0001\u0002(\u0002\u0015M$\u0018M\u001d;J]\u0012,\u00070F\u0001P!\t\u0011\u0003+\u0003\u0002R\r\t\u0019\u0011J\u001c;\t\u0011M+\"\u0011!Q\u0001\n=\u000b1b\u001d;beRLe\u000eZ3yA!IQ+\u0006BC\u0002\u0013\u0005AAT\u0001\tK:$\u0017J\u001c3fq\"Aq+\u0006B\u0001B\u0003%q*A\u0005f]\u0012Le\u000eZ3yA!A\u0011,\u0006B\u0001B\u0003%q*A\u0003g_\u000e,8\u000f\u0003\u0004\\+\u0011\u0005!\u0001X\u0001\u0007y%t\u0017\u000e\u001e \u0015\tEjfl\u0018\u0005\u0006\u001bj\u0003\ra\u0014\u0005\u0006+j\u0003\ra\u0014\u0005\u00063j\u0003\ra\u0014\u0005\u0006CV!\tEY\u0001\nG>l\u0007/\u00198j_:,\u0012a\u0019\t\u0004\u001f\u0011$\u0012BA3\u0011\u0005A9UM\\3sS\u000e\u001cu.\u001c9b]&|g\u000e\u0003\u0005h+\u0001\u0007I\u0011\u0001\u0002i\u0003\u0015!\u0017N\u001d;z+\u0005I\u0007C\u0001\u0012k\u0013\tYgAA\u0004C_>dW-\u00198\t\u00115,\u0002\u0019!C\u0001\u00059\f\u0011\u0002Z5sif|F%Z9\u0015\u0005=\u0014\bC\u0001\u0012q\u0013\t\thA\u0001\u0003V]&$\bbB:m\u0003\u0003\u0005\r![\u0001\u0004q\u0012\n\u0004BB;\u0016A\u0003&\u0011.\u0001\u0004eSJ$\u0018\u0010\t\u0005\u0006oV!\tAT\u0001\u0007Y\u0016tw\r\u001e5\t\u000be,B\u0011\t>\u0002\u0007A\f'/F\u0001F\u0011\u0015aX\u0003\"\u0011~\u0003!!xNV3di>\u0014X#A\u0019\t\r},B\u0011IA\u0001\u00035aWM\\4uQ\u000e{W\u000e]1sKR\u0019q*a\u0001\t\r\u0005\u0015a\u00101\u0001P\u0003\raWM\u001c\u0005\t\u0003\u0013)BQ\u0001\u0003\u0002\f\u0005a\u0011N\\5u\u0013R,'/\u0019;peV!\u0011QBA\u000e)\ry\u0017q\u0002\u0005\t\u0003#\t9\u00011\u0001\u0002\u0014\u0005\t1\u000fE\u0003\u000b\u0003+\tI\"C\u0002\u0002\u0018\t\u0011aBV3di>\u0014\u0018\n^3sCR|'\u000fE\u0002\u001e\u00037!\u0001\"!\b\u0002\b\t\u0007\u0011q\u0004\u0002\u0002\u0005F\u0011A$\n\u0005\b\u0003G)B\u0011IA\u0013\u0003!IG/\u001a:bi>\u0014XCAA\u0014!\u0011Q\u0011Q\u0003\u000f\t\u000f\u0005-R\u0003\"\u0011\u0002.\u0005y!/\u001a<feN,\u0017\n^3sCR|'/\u0006\u0002\u00020A!\u0011$!\r\u001d\u0013\r\t\u0019\u0004\u0002\u0002\t\u0013R,'/\u0019;pe\"9\u0011qG\u000b\u0005\u0002\u0005e\u0012!B1qa2LHc\u0001\u000f\u0002<!9\u0011QHA\u001b\u0001\u0004y\u0015!B5oI\u0016D\bbBA!+\u0011%\u00111I\u0001\u0012G\",7m\u001b*b]\u001e,7i\u001c8wKJ$HcA(\u0002F!9\u0011QHA \u0001\u0004y\u0005\u0002CA%+\u0001&I!a\u0013\u0002\u0019%\u001cH)\u001a4bk2$8I\u0011$\u0016\u0011\u00055\u0013QLA1\u0003K\"2![A(\u0011!\t\t&a\u0012A\u0002\u0005M\u0013A\u00012g!%y\u0011QKA-\u0003?\n\u0019'C\u0002\u0002XA\u0011AbQ1o\u0005VLG\u000e\u001a$s_6\u0004BAC\u000b\u0002\\A\u0019Q$!\u0018\u0005\r}\t9E1\u0001!!\ri\u0012\u0011\r\u0003\b\u0003;\t9E1\u0001!!\ri\u0012Q\r\u0003\b\u0003O\n9E1\u0001!\u0005\u0011!\u0006.\u0019;)\t\u0005\u001d\u00131\u000e\t\u0004E\u00055\u0014bAA8\r\t1\u0011N\u001c7j]\u0016Dq!a\u001d\u0016\t\u0003\n)(A\u0004va\u0012\fG/\u001a3\u0016\r\u0005]\u0014QQA?)\u0019\tI(a\"\u0002\nR!\u00111PA@!\ri\u0012Q\u0010\u0003\b\u0003O\n\tH1\u0001!\u0011!\t\t&!\u001dA\u0004\u0005\u0005\u0005\u0003C\b\u0002VE\n\u0019)a\u001f\u0011\u0007u\t)\t\u0002\u0005\u0002\u001e\u0005E$\u0019AA\u0010\u0011\u001d\ti$!\u001dA\u0002=C\u0001\"a#\u0002r\u0001\u0007\u00111Q\u0001\u0005K2,W\u000eC\u0004\u0002\u0010V!\t%!%\u0002\u0017\u0011\u0002H.^:%G>dwN\\\u000b\u0007\u0003'\u000b\t+!'\u0015\t\u0005U\u00151\u0015\u000b\u0005\u0003/\u000bY\nE\u0002\u001e\u00033#q!a\u001a\u0002\u000e\n\u0007\u0001\u0005\u0003\u0005\u0002R\u00055\u00059AAO!!y\u0011QK\u0019\u0002 \u0006]\u0005cA\u000f\u0002\"\u0012A\u0011QDAG\u0005\u0004\ty\u0002\u0003\u0005\u0002\f\u00065\u0005\u0019AAP\u0011\u001d\t9+\u0006C!\u0003S\u000b1\u0002J2pY>tG\u0005\u001d7vgV1\u00111VA]\u0003c#B!!,\u0002<R!\u0011qVAZ!\ri\u0012\u0011\u0017\u0003\b\u0003O\n)K1\u0001!\u0011!\t\t&!*A\u0004\u0005U\u0006\u0003C\b\u0002VE\n9,a,\u0011\u0007u\tI\f\u0002\u0005\u0002\u001e\u0005\u0015&\u0019AA\u0010\u0011!\tY)!*A\u0002\u0005]\u0006bBA`+\u0011\u0005\u0013\u0011Y\u0001\u0005i\u0006\\W\rF\u00022\u0003\u0007Dq!!2\u0002>\u0002\u0007q*A\u0001o\u0011\u001d\tI-\u0006C!\u0003\u0017\fA\u0001\u001a:paR\u0019\u0011'!4\t\u000f\u0005\u0015\u0017q\u0019a\u0001\u001f\"9\u0011\u0011[\u000b\u0005B\u0005M\u0017!\u0003;bW\u0016\u0014\u0016n\u001a5u)\r\t\u0014Q\u001b\u0005\b\u0003\u000b\fy\r1\u0001P\u0011\u001d\tI.\u0006C!\u00037\f\u0011\u0002\u001a:paJKw\r\u001b;\u0015\u0007E\ni\u000eC\u0004\u0002F\u0006]\u0007\u0019A(\t\u000f\u0005\u0005X\u0003\"\u0011\u0002d\u0006!\u0001.Z1e+\u0005a\u0002BBAt+\u0011\u0005S0\u0001\u0003uC&d\u0007bBAv+\u0011\u0005\u00131]\u0001\u0005Y\u0006\u001cH\u000f\u0003\u0004\u0002pV!\t%`\u0001\u0005S:LG\u000fC\u0004\u0002tV!\t%!>\u0002\u000bMd\u0017nY3\u0015\u000bE\n90a?\t\u000f\u0005e\u0018\u0011\u001fa\u0001\u001f\u0006!aM]8n\u0011\u001d\ti0!=A\u0002=\u000bQ!\u001e8uS2DqA!\u0001\u0016\t\u0003\u0012\u0019!A\u0004ta2LG/\u0011;\u0015\t\t\u0015!1\u0002\t\u0006E\t\u001d\u0011'M\u0005\u0004\u0005\u00131!A\u0002+va2,'\u0007C\u0004\u0002F\u0006}\b\u0019A(\t\u000f\t=Q\u0003\"\u0011\u0003\u0012\u0005QA\u0005\u001d7vg\u0012\u0002H.^:\u0016\r\tM!\u0011\u0005B\r)\u0011\u0011)Ba\t\u0015\t\t]!1\u0004\t\u0004;\teAaBA4\u0005\u001b\u0011\r\u0001\t\u0005\t\u0003#\u0012i\u0001q\u0001\u0003\u001eAAq\"!\u00162\u0005?\u00119\u0002E\u0002\u001e\u0005C!\u0001\"!\b\u0003\u000e\t\u0007\u0011q\u0004\u0005\t\u0005K\u0011i\u00011\u0001\u0003(\u0005!A\u000f[1u!\u0015I\"\u0011\u0006B\u0010\u0013\r\u0011Y\u0003\u0002\u0002\u0013\u000f\u0016tGK]1wKJ\u001c\u0018M\u00197f\u001f:\u001cW\r\u0003\u0005\u00030U!\tA\u0001B\u0019\u0003!)\b\u000fZ1uK\u0006#X\u0003\u0002B\u001a\u0005s!bA!\u000e\u0003<\tu\u0002\u0003\u0002\u0006\u0016\u0005o\u00012!\bB\u001d\t!\tiB!\fC\u0002\u0005}\u0001bBA\u001f\u0005[\u0001\ra\u0014\u0005\t\u0003\u0017\u0013i\u00031\u0001\u00038!9!\u0011I\u000b\u0005\n\t\r\u0013aD4pi>\u0004vn],sSR\f'\r\\3\u0015\u000f=\u0014)E!\u0013\u0003N!9!q\tB \u0001\u0004y\u0015\u0001C8mI&sG-\u001a=\t\u000f\t-#q\ba\u0001\u001f\u0006Aa.Z<J]\u0012,\u0007\u0010C\u0004\u0003P\t}\u0002\u0019A(\u0002\u0007a|'\u000fC\u0004\u0003TU!IA!\u0016\u0002)\u001d|Go\u001c$sKND\u0007k\\:Xe&$\u0018M\u00197f)\u001dy'q\u000bB-\u00057BqAa\u0012\u0003R\u0001\u0007q\nC\u0004\u0003L\tE\u0003\u0019A(\t\u000f\t=#\u0011\u000ba\u0001\u001f\"A!qL\u000b\u0005\u0002\t\u0011\t'A\u0006baB,g\u000e\u001a$s_:$X\u0003\u0002B2\u0005S\"BA!\u001a\u0003lA!!\"\u0006B4!\ri\"\u0011\u000e\u0003\t\u0003;\u0011iF1\u0001\u0002 !A!Q\u000eB/\u0001\u0004\u00119'A\u0003wC2,X\r\u0003\u0005\u0003rU!\tA\u0001B:\u0003)\t\u0007\u000f]3oI\n\u000b7m[\u000b\u0005\u0005k\u0012Y\b\u0006\u0003\u0003x\tu\u0004\u0003\u0002\u0006\u0016\u0005s\u00022!\bB>\t!\tiBa\u001cC\u0002\u0005}\u0001\u0002\u0003B7\u0005_\u0002\rA!\u001f\t\u000f\t\u0005U\u0003\"\u0003\u0003\u0004\u0006i1\u000f[5giR{\u0007\u000fT3wK2$Ra\u001cBC\u0005\u0013CqAa\"\u0003\u0000\u0001\u0007q*A\u0004pY\u0012dUM\u001a;\t\u000f\t-%q\u0010a\u0001\u001f\u00069a.Z<MK\u001a$\bb\u0002BH+\u0011%!\u0011S\u0001\tu\u0016\u0014x\u000eT3giR)qNa%\u0003$\"A!Q\u0013BG\u0001\u0004\u00119*A\u0003beJ\f\u0017\u0010E\u0003#\u00053\u0013i*C\u0002\u0003\u001c\u001a\u0011Q!\u0011:sCf\u00042A\tBP\u0013\r\u0011\tK\u0002\u0002\u0007\u0003:L(+\u001a4\t\u000f\u0005u\"Q\u0012a\u0001\u001f\"9!qU\u000b\u0005\n\t%\u0016!\u0003>fe>\u0014\u0016n\u001a5u)\u0015y'1\u0016BW\u0011!\u0011)J!*A\u0002\t]\u0005bBA\u001f\u0005K\u0003\ra\u0014\u0005\b\u0005c+B\u0011\u0002BZ\u0003!\u0019w\u000e]=MK\u001a$HC\u0002BL\u0005k\u00139\f\u0003\u0005\u0003\u0016\n=\u0006\u0019\u0001BL\u0011\u001d\u0011ILa,A\u0002=\u000bQA]5hQRDqA!0\u0016\t\u0013\u0011y,A\u0005d_BL(+[4iiR1!q\u0013Ba\u0005\u0007D\u0001B!&\u0003<\u0002\u0007!q\u0013\u0005\b\u0005\u000b\u0014Y\f1\u0001P\u0003\u0011aWM\u001a;\t\u000f\t%W\u0003\"\u0003\u0003L\u0006A\u0001O]3DY\u0016\fg\u000eF\u0002p\u0005\u001bDqAa4\u0003H\u0002\u0007q*A\u0003eKB$\b\u000eC\u0004\u0003TV!IA!6\u0002\u001b\rdW-\u00198MK\u001a$X\tZ4f)\ry'q\u001b\u0005\b\u00053\u0014\t\u000e1\u0001P\u0003!\u0019W\u000f^%oI\u0016D\bb\u0002Bo+\u0011%!q\\\u0001\u000fG2,\u0017M\u001c*jO\"$X\tZ4f)\ry'\u0011\u001d\u0005\b\u00053\u0014Y\u000e1\u0001P\u0011\u001d\u0011)/\u0006C\u0005\u0005O\fQB]3rk&\u0014X\r\u001a#faRDGcA(\u0003j\"9!q\nBr\u0001\u0004y\u0005b\u0002Bw+\u0011%!q^\u0001\u000bIJ|\u0007O\u0012:p]R\u0004DcA\u0019\u0003r\"9!\u0011\u001cBv\u0001\u0004y\u0005b\u0002B{+\u0011%!q_\u0001\nIJ|\u0007OQ1dWB\"2!\rB}\u0011\u001d\u0011INa=A\u0002=CaaW\u0006\u0005\u0002\tuH#A\u0005\t\u000f\r\u00051\u0002\"\u0001\u0004\u0004\u0005Qa.Z<Ck&dG-\u001a:\u0016\t\r\u00151QC\u000b\u0003\u0007\u000f\u0001\u0002b!\u0003\u0004\u0010\rM1qC\u0007\u0003\u0007\u0017Q1a!\u0004\u0005\u0003\u001diW\u000f^1cY\u0016LAa!\u0005\u0004\f\t9!)^5mI\u0016\u0014\bcA\u000f\u0004\u0016\u00111qDa@C\u0002\u0001\u0002BAC\u000b\u0004\u0014!911D\u0006\u0005\u0004\ru\u0011\u0001D2b]\n+\u0018\u000e\u001c3Ge>lW\u0003BB\u0010\u0007[)\"a!\t\u0011\u0013=\t)fa\t\u0004,\r=\u0002\u0003BB\u0013\u0007Oi\u0011aC\u0005\u0004\u0007S!'\u0001B\"pY2\u00042!HB\u0017\t\u0019y2\u0011\u0004b\u0001AA!!\"FB\u0016\u0011)\u0019\u0019d\u0003b\u0001\n\u0003\u00111QG\u0001\u0004\u001d&cUCAB\u001c!\rQQ#\t\u0005\t\u0007wY\u0001\u0015!\u0003\u00048\u0005!a*\u0013'!\u0011\u001d\u0019yd\u0003C!\u0007\u0003\nQ!Z7qif,Baa\u0011\u0004JU\u00111Q\t\t\u0005\u0015U\u00199\u0005E\u0002\u001e\u0007\u0013\"aaHB\u001f\u0005\u0004\u0001\u0003\"CB'\u0017\t\u0007IQBB(\u0003Aaun\u001a\u001aD_:\u001c\u0017\r\u001e$bgR,'/\u0006\u0002\u0004R=\u001111K\u000f\u0002\u000b!A1qK\u0006!\u0002\u001b\u0019\t&A\tM_\u001e\u00144i\u001c8dCR4\u0015m\u001d;fe\u0002B\u0011ba\u0017\f\u0005\u0004%ia!\u0018\u0002!QKg._!qa\u0016tGMR1ti\u0016\u0014XCAB0\u001f\t\u0019\t'H\u0001\u0003\u0011!\u0019)g\u0003Q\u0001\u000e\r}\u0013!\u0005+j]f\f\u0005\u000f]3oI\u001a\u000b7\u000f^3sA!I1\u0011N\u0006\u0002\u0002\u0013%11N\u0001\fe\u0016\fGMU3t_24X\r\u0006\u0002\u0004nA!1qNB=\u001b\t\u0019\tH\u0003\u0003\u0004t\rU\u0014\u0001\u00027b]\u001eT!aa\u001e\u0002\t)\fg/Y\u0005\u0005\u0007w\u001a\tH\u0001\u0004PE*,7\r\u001e")
public final class Vector<A>
extends AbstractSeq<A>
implements scala.collection.immutable.IndexedSeq<A>,
VectorPointer<A>,
Serializable,
CustomParallelizable<A, ParVector<A>> {
    private final int startIndex;
    private final int endIndex;
    private final int focus;
    private boolean dirty;
    private int depth;
    private Object[] display0;
    private Object[] display1;
    private Object[] display2;
    private Object[] display3;
    private Object[] display4;
    private Object[] display5;

    public static <A> Vector<A> empty() {
        return Vector$.MODULE$.empty();
    }

    public static <A> CanBuildFrom<Vector<?>, A, Vector<A>> canBuildFrom() {
        return Vector$.MODULE$.canBuildFrom();
    }

    public static GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return Vector$.MODULE$.ReusableCBF();
    }

    public static Some unapplySeq(Seq seq) {
        return Vector$.MODULE$.unapplySeq(seq);
    }

    public static GenTraversable iterate(Object object, int n, Function1 function1) {
        return Vector$.MODULE$.iterate(object, n, function1);
    }

    public static GenTraversable range(Object object, Object object2, Object object3, Integral integral) {
        return Vector$.MODULE$.range(object, object2, object3, integral);
    }

    public static GenTraversable range(Object object, Object object2, Integral integral) {
        return Vector$.MODULE$.range(object, object2, integral);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, int n5, Function5 function5) {
        return Vector$.MODULE$.tabulate(n, n2, n3, n4, n5, function5);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, Function4 function4) {
        return Vector$.MODULE$.tabulate(n, n2, n3, n4, function4);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, Function3 function3) {
        return Vector$.MODULE$.tabulate(n, n2, n3, function3);
    }

    public static GenTraversable tabulate(int n, int n2, Function2 function2) {
        return Vector$.MODULE$.tabulate(n, n2, function2);
    }

    public static GenTraversable tabulate(int n, Function1 function1) {
        return Vector$.MODULE$.tabulate(n, function1);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, int n5, Function0 function0) {
        return Vector$.MODULE$.fill(n, n2, n3, n4, n5, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, Function0 function0) {
        return Vector$.MODULE$.fill(n, n2, n3, n4, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, Function0 function0) {
        return Vector$.MODULE$.fill(n, n2, n3, function0);
    }

    public static GenTraversable fill(int n, int n2, Function0 function0) {
        return Vector$.MODULE$.fill(n, n2, function0);
    }

    public static GenTraversable fill(int n, Function0 function0) {
        return Vector$.MODULE$.fill(n, function0);
    }

    public static GenTraversable concat(Seq seq) {
        return Vector$.MODULE$.concat(seq);
    }

    public static GenTraversable empty() {
        return Vector$.MODULE$.empty();
    }

    @Override
    public Combiner<A, ParVector<A>> parCombiner() {
        return CustomParallelizable$class.parCombiner(this);
    }

    @Override
    public int depth() {
        return this.depth;
    }

    @Override
    public void depth_$eq(int x$1) {
        this.depth = x$1;
    }

    @Override
    public Object[] display0() {
        return this.display0;
    }

    @Override
    public void display0_$eq(Object[] x$1) {
        this.display0 = x$1;
    }

    @Override
    public Object[] display1() {
        return this.display1;
    }

    @Override
    public void display1_$eq(Object[] x$1) {
        this.display1 = x$1;
    }

    @Override
    public Object[] display2() {
        return this.display2;
    }

    @Override
    public void display2_$eq(Object[] x$1) {
        this.display2 = x$1;
    }

    @Override
    public Object[] display3() {
        return this.display3;
    }

    @Override
    public void display3_$eq(Object[] x$1) {
        this.display3 = x$1;
    }

    @Override
    public Object[] display4() {
        return this.display4;
    }

    @Override
    public void display4_$eq(Object[] x$1) {
        this.display4 = x$1;
    }

    @Override
    public Object[] display5() {
        return this.display5;
    }

    @Override
    public void display5_$eq(Object[] x$1) {
        this.display5 = x$1;
    }

    @Override
    public final <U> void initFrom(VectorPointer<U> that) {
        VectorPointer$class.initFrom(this, that);
    }

    @Override
    public final <U> void initFrom(VectorPointer<U> that, int depth) {
        VectorPointer$class.initFrom(this, that, depth);
    }

    @Override
    public final A getElem(int index, int xor) {
        return (A)VectorPointer$class.getElem(this, index, xor);
    }

    @Override
    public final void gotoPos(int index, int xor) {
        VectorPointer$class.gotoPos(this, index, xor);
    }

    @Override
    public final void gotoNextBlockStart(int index, int xor) {
        VectorPointer$class.gotoNextBlockStart(this, index, xor);
    }

    @Override
    public final void gotoNextBlockStartWritable(int index, int xor) {
        VectorPointer$class.gotoNextBlockStartWritable(this, index, xor);
    }

    @Override
    public final Object[] copyOf(Object[] a) {
        return VectorPointer$class.copyOf(this, a);
    }

    @Override
    public final Object[] nullSlotAndCopy(Object[] array, int index) {
        return VectorPointer$class.nullSlotAndCopy(this, array, index);
    }

    @Override
    public final void stabilize(int index) {
        VectorPointer$class.stabilize(this, index);
    }

    @Override
    public final void gotoPosWritable0(int newIndex, int xor) {
        VectorPointer$class.gotoPosWritable0(this, newIndex, xor);
    }

    @Override
    public final void gotoPosWritable1(int oldIndex, int newIndex, int xor) {
        VectorPointer$class.gotoPosWritable1(this, oldIndex, newIndex, xor);
    }

    @Override
    public final Object[] copyRange(Object[] array, int oldLeft, int newLeft) {
        return VectorPointer$class.copyRange(this, array, oldLeft, newLeft);
    }

    @Override
    public final void gotoFreshPosWritable0(int oldIndex, int newIndex, int xor) {
        VectorPointer$class.gotoFreshPosWritable0(this, oldIndex, newIndex, xor);
    }

    @Override
    public final void gotoFreshPosWritable1(int oldIndex, int newIndex, int xor) {
        VectorPointer$class.gotoFreshPosWritable1(this, oldIndex, newIndex, xor);
    }

    @Override
    public void debug() {
        VectorPointer$class.debug(this);
    }

    @Override
    public scala.collection.immutable.IndexedSeq<A> toIndexedSeq() {
        return IndexedSeq$class.toIndexedSeq(this);
    }

    @Override
    public scala.collection.immutable.IndexedSeq<A> seq() {
        return IndexedSeq$class.seq(this);
    }

    @Override
    public int hashCode() {
        return IndexedSeqLike$class.hashCode(this);
    }

    @Override
    public IndexedSeq<A> thisCollection() {
        return IndexedSeqLike$class.thisCollection(this);
    }

    @Override
    public IndexedSeq toCollection(Object repr) {
        return IndexedSeqLike$class.toCollection(this, repr);
    }

    @Override
    public <A1> Buffer<A1> toBuffer() {
        return IndexedSeqLike$class.toBuffer(this);
    }

    @Override
    public scala.collection.immutable.Seq<A> toSeq() {
        return Seq$class.toSeq(this);
    }

    public int startIndex() {
        return this.startIndex;
    }

    public int endIndex() {
        return this.endIndex;
    }

    @Override
    public GenericCompanion<Vector> companion() {
        return Vector$.MODULE$;
    }

    public boolean dirty() {
        return this.dirty;
    }

    public void dirty_$eq(boolean x$1) {
        this.dirty = x$1;
    }

    @Override
    public int length() {
        return this.endIndex() - this.startIndex();
    }

    @Override
    public ParVector<A> par() {
        return new ParVector(this);
    }

    @Override
    public Vector<A> toVector() {
        return this;
    }

    @Override
    public int lengthCompare(int len) {
        return this.length() - len;
    }

    public final <B> void initIterator(VectorIterator<B> s2) {
        s2.initFrom(this);
        if (this.dirty()) {
            s2.stabilize(this.focus);
        }
        if (s2.depth() > 1) {
            s2.gotoPos(this.startIndex(), this.startIndex() ^ this.focus);
        }
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public VectorIterator<A> iterator() {
        void var1_1;
        VectorIterator s2 = new VectorIterator(this.startIndex(), this.endIndex());
        this.initIterator(s2);
        return var1_1;
    }

    @Override
    public Iterator<A> reverseIterator() {
        return new AbstractIterator<A>(this){
            private int i;
            private final /* synthetic */ Vector $outer;

            private int i() {
                return this.i;
            }

            private void i_$eq(int x$1) {
                this.i = x$1;
            }

            public boolean hasNext() {
                return 0 < this.i();
            }

            public A next() {
                Nothing$ nothing$;
                if (0 < this.i()) {
                    this.i_$eq(this.i() - 1);
                    nothing$ = this.$outer.apply(this.i());
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (A)nothing$;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.i = $outer.length();
            }
        };
    }

    @Override
    public A apply(int index) {
        int idx = this.checkRangeConvert(index);
        return this.getElem(idx, idx ^ this.focus);
    }

    private int checkRangeConvert(int index) {
        int idx = index + this.startIndex();
        if (0 <= index && idx < this.endIndex()) {
            return idx;
        }
        throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(index)).toString());
    }

    private <A, B, That> boolean isDefaultCBF(CanBuildFrom<Vector<A>, B, That> bf) {
        return bf == IndexedSeq$.MODULE$.ReusableCBF() || bf == Seq$.MODULE$.ReusableCBF() || bf == scala.collection.Seq$.MODULE$.ReusableCBF();
    }

    @Override
    public <B, That> That updated(int index, B elem, CanBuildFrom<Vector<A>, B, That> bf) {
        return (That)(bf == IndexedSeq$.MODULE$.ReusableCBF() || bf == Seq$.MODULE$.ReusableCBF() || bf == scala.collection.Seq$.MODULE$.ReusableCBF() ? this.updateAt(index, elem) : SeqLike$class.updated(this, index, elem, bf));
    }

    @Override
    public <B, That> That $plus$colon(B elem, CanBuildFrom<Vector<A>, B, That> bf) {
        return (That)(bf == IndexedSeq$.MODULE$.ReusableCBF() || bf == Seq$.MODULE$.ReusableCBF() || bf == scala.collection.Seq$.MODULE$.ReusableCBF() ? this.appendFront(elem) : SeqLike$class.$plus$colon(this, elem, bf));
    }

    @Override
    public <B, That> That $colon$plus(B elem, CanBuildFrom<Vector<A>, B, That> bf) {
        return (That)(bf == IndexedSeq$.MODULE$.ReusableCBF() || bf == Seq$.MODULE$.ReusableCBF() || bf == scala.collection.Seq$.MODULE$.ReusableCBF() ? this.appendBack(elem) : SeqLike$class.$colon$plus(this, elem, bf));
    }

    @Override
    public Vector<A> take(int n) {
        return n <= 0 ? Vector$.MODULE$.empty() : (this.startIndex() < this.endIndex() - n ? this.dropBack0(this.startIndex() + n) : this);
    }

    @Override
    public Vector<A> drop(int n) {
        return n <= 0 ? this : (this.startIndex() < this.endIndex() - n ? this.dropFront0(this.startIndex() + n) : Vector$.MODULE$.empty());
    }

    @Override
    public Vector<A> takeRight(int n) {
        return n <= 0 ? Vector$.MODULE$.empty() : (this.endIndex() - n > this.startIndex() ? this.dropFront0(this.endIndex() - n) : this);
    }

    @Override
    public Vector<A> dropRight(int n) {
        return n <= 0 ? this : (this.endIndex() - n > this.startIndex() ? this.dropBack0(this.endIndex() - n) : Vector$.MODULE$.empty());
    }

    @Override
    public A head() {
        if (this.isEmpty()) {
            throw new UnsupportedOperationException("empty.head");
        }
        return this.apply(0);
    }

    @Override
    public Vector<A> tail() {
        if (this.isEmpty()) {
            throw new UnsupportedOperationException("empty.tail");
        }
        return this.drop(1);
    }

    @Override
    public A last() {
        if (this.isEmpty()) {
            throw new UnsupportedOperationException("empty.last");
        }
        return this.apply(this.length() - 1);
    }

    @Override
    public Vector<A> init() {
        if (this.isEmpty()) {
            throw new UnsupportedOperationException("empty.init");
        }
        return this.dropRight(1);
    }

    @Override
    public Vector<A> slice(int from2, int until2) {
        return ((Vector)this.take(until2)).drop(from2);
    }

    @Override
    public Tuple2<Vector<A>, Vector<A>> splitAt(int n) {
        return new Tuple2<Object, Object>(this.take(n), this.drop(n));
    }

    @Override
    public <B, That> That $plus$plus(GenTraversableOnce<B> that, CanBuildFrom<Vector<A>, B, That> bf) {
        Object object;
        if (bf == IndexedSeq$.MODULE$.ReusableCBF() || bf == Seq$.MODULE$.ReusableCBF() || bf == scala.collection.Seq$.MODULE$.ReusableCBF()) {
            if (that.isEmpty()) {
                object = this;
            } else {
                TraversableOnce<B> again = that.isTraversableAgain() ? that.seq() : that.toVector();
                int n = again.size();
                if (n <= 2 || n < this.size() >> 5) {
                    ObjectRef<Vector> v = ObjectRef.create(this);
                    again.foreach(new Serializable(this, v){
                        public static final long serialVersionUID = 0L;
                        private final ObjectRef v$1;

                        public final void apply(B x) {
                            this.v$1.elem = ((Vector)this.v$1.elem).$colon$plus(x, Vector$.MODULE$.canBuildFrom());
                        }
                        {
                            this.v$1 = v$1;
                        }
                    });
                    object = (Vector)v.elem;
                } else if (this.size() < n >> 5 && again instanceof Vector) {
                    Vector<?> v = (Vector<?>)again;
                    Iterator<A> ri = this.reverseIterator();
                    while (ri.hasNext()) {
                        A a = ri.next();
                        v = v.$plus$colon((B)a, Vector$.MODULE$.canBuildFrom());
                    }
                    object = v;
                } else {
                    object = TraversableLike$class.$plus$plus(this, again, bf);
                }
            }
        } else {
            object = TraversableLike$class.$plus$plus(this, that.seq(), bf);
        }
        return (That)object;
    }

    public <B> Vector<B> updateAt(int index, B elem) {
        int idx = this.checkRangeConvert(index);
        Vector<A> s2 = new Vector<A>(this.startIndex(), this.endIndex(), idx);
        s2.initFrom(this);
        s2.dirty_$eq(this.dirty());
        super.gotoPosWritable(this.focus, idx, this.focus ^ idx);
        s2.display0()[idx & 0x1F] = elem;
        return s2;
    }

    private void gotoPosWritable(int oldIndex, int newIndex, int xor) {
        if (this.dirty()) {
            this.gotoPosWritable1(oldIndex, newIndex, xor);
        } else {
            this.gotoPosWritable0(newIndex, xor);
            this.dirty_$eq(true);
        }
    }

    private void gotoFreshPosWritable(int oldIndex, int newIndex, int xor) {
        if (this.dirty()) {
            this.gotoFreshPosWritable1(oldIndex, newIndex, xor);
        } else {
            this.gotoFreshPosWritable0(oldIndex, newIndex, xor);
            this.dirty_$eq(true);
        }
    }

    /*
     * WARNING - void declaration
     */
    public <B> Vector<B> appendFront(B value) {
        Vector<A> vector;
        if (this.endIndex() != this.startIndex()) {
            int blockIndex = this.startIndex() - 1 & ~31;
            int lo = this.startIndex() - 1 & 0x1F;
            if (this.startIndex() != blockIndex + 32) {
                void var2_4;
                Vector<A> s2 = new Vector<A>(this.startIndex() - 1, this.endIndex(), blockIndex);
                s2.initFrom(this);
                s2.dirty_$eq(this.dirty());
                super.gotoPosWritable(this.focus, blockIndex, this.focus ^ blockIndex);
                s2.display0()[lo] = value;
                vector = var2_4;
            } else {
                int freeSpace = (1 << 5 * this.depth()) - this.endIndex();
                int shift = freeSpace & ~((1 << 5 * (this.depth() - 1)) - 1);
                int shiftBlocks = freeSpace >>> 5 * (this.depth() - 1);
                if (shift != 0) {
                    this.debug();
                    if (this.depth() > 1) {
                        int newBlockIndex = blockIndex + shift;
                        int newFocus = this.focus + shift;
                        Vector<A> s3 = new Vector<A>(this.startIndex() - 1 + shift, this.endIndex() + shift, newBlockIndex);
                        s3.initFrom(this);
                        s3.dirty_$eq(this.dirty());
                        super.shiftTopLevel(0, shiftBlocks);
                        s3.debug();
                        super.gotoFreshPosWritable(newFocus, newBlockIndex, newFocus ^ newBlockIndex);
                        s3.display0()[lo] = value;
                        vector = s3;
                    } else {
                        int newBlockIndex = blockIndex + 32;
                        int newFocus = this.focus;
                        Vector<A> s4 = new Vector<A>(this.startIndex() - 1 + shift, this.endIndex() + shift, newBlockIndex);
                        s4.initFrom(this);
                        s4.dirty_$eq(this.dirty());
                        super.shiftTopLevel(0, shiftBlocks);
                        super.gotoPosWritable(newFocus, newBlockIndex, newFocus ^ newBlockIndex);
                        s4.display0()[shift - 1] = value;
                        s4.debug();
                        vector = s4;
                    }
                } else if (blockIndex < 0) {
                    int move = (1 << 5 * (this.depth() + 1)) - (1 << 5 * this.depth());
                    int newBlockIndex = blockIndex + move;
                    int newFocus = this.focus + move;
                    Vector<A> s5 = new Vector<A>(this.startIndex() - 1 + move, this.endIndex() + move, newBlockIndex);
                    s5.initFrom(this);
                    s5.dirty_$eq(this.dirty());
                    s5.debug();
                    super.gotoFreshPosWritable(newFocus, newBlockIndex, newFocus ^ newBlockIndex);
                    s5.display0()[lo] = value;
                    s5.debug();
                    vector = s5;
                } else {
                    int newFocus = this.focus;
                    Vector<A> s6 = new Vector<A>(this.startIndex() - 1, this.endIndex(), blockIndex);
                    s6.initFrom(this);
                    s6.dirty_$eq(this.dirty());
                    super.gotoFreshPosWritable(newFocus, blockIndex, newFocus ^ blockIndex);
                    s6.display0()[lo] = value;
                    vector = s6;
                }
            }
        } else {
            Object[] elems = new Object[32];
            elems[31] = value;
            Vector<A> s7 = new Vector<A>(31, 32, 0);
            s7.depth_$eq(1);
            s7.display0_$eq(elems);
            vector = s7;
        }
        return vector;
    }

    /*
     * WARNING - void declaration
     */
    public <B> Vector<B> appendBack(B value) {
        Vector<A> vector;
        if (this.endIndex() != this.startIndex()) {
            int blockIndex = this.endIndex() & ~31;
            int lo = this.endIndex() & 0x1F;
            if (this.endIndex() != blockIndex) {
                void var2_4;
                Vector<A> s2 = new Vector<A>(this.startIndex(), this.endIndex() + 1, blockIndex);
                s2.initFrom(this);
                s2.dirty_$eq(this.dirty());
                super.gotoPosWritable(this.focus, blockIndex, this.focus ^ blockIndex);
                s2.display0()[lo] = value;
                vector = var2_4;
            } else {
                int shift = this.startIndex() & ~((1 << 5 * (this.depth() - 1)) - 1);
                int shiftBlocks = this.startIndex() >>> 5 * (this.depth() - 1);
                if (shift != 0) {
                    this.debug();
                    if (this.depth() > 1) {
                        int newBlockIndex = blockIndex - shift;
                        int newFocus = this.focus - shift;
                        Vector<A> s3 = new Vector<A>(this.startIndex() - shift, this.endIndex() + 1 - shift, newBlockIndex);
                        s3.initFrom(this);
                        s3.dirty_$eq(this.dirty());
                        super.shiftTopLevel(shiftBlocks, 0);
                        s3.debug();
                        super.gotoFreshPosWritable(newFocus, newBlockIndex, newFocus ^ newBlockIndex);
                        s3.display0()[lo] = value;
                        s3.debug();
                        vector = s3;
                    } else {
                        int newBlockIndex = blockIndex - 32;
                        int newFocus = this.focus;
                        Vector<A> s4 = new Vector<A>(this.startIndex() - shift, this.endIndex() + 1 - shift, newBlockIndex);
                        s4.initFrom(this);
                        s4.dirty_$eq(this.dirty());
                        super.shiftTopLevel(shiftBlocks, 0);
                        super.gotoPosWritable(newFocus, newBlockIndex, newFocus ^ newBlockIndex);
                        s4.display0()[32 - shift] = value;
                        s4.debug();
                        vector = s4;
                    }
                } else {
                    int newFocus = this.focus;
                    Vector<A> s5 = new Vector<A>(this.startIndex(), this.endIndex() + 1, blockIndex);
                    s5.initFrom(this);
                    s5.dirty_$eq(this.dirty());
                    super.gotoFreshPosWritable(newFocus, blockIndex, newFocus ^ blockIndex);
                    s5.display0()[lo] = value;
                    if (s5.depth() == this.depth() + 1) {
                        s5.debug();
                    }
                    vector = s5;
                }
            }
        } else {
            Object[] elems = new Object[32];
            elems[0] = value;
            Vector<A> s6 = new Vector<A>(0, 1, 0);
            s6.depth_$eq(1);
            s6.display0_$eq(elems);
            vector = s6;
        }
        return vector;
    }

    private void shiftTopLevel(int oldLeft, int newLeft) {
        int n = this.depth() - 1;
        switch (n) {
            default: {
                throw new MatchError(BoxesRunTime.boxToInteger(n));
            }
            case 5: {
                this.display5_$eq(this.copyRange(this.display5(), oldLeft, newLeft));
                break;
            }
            case 4: {
                this.display4_$eq(this.copyRange(this.display4(), oldLeft, newLeft));
                break;
            }
            case 3: {
                this.display3_$eq(this.copyRange(this.display3(), oldLeft, newLeft));
                break;
            }
            case 2: {
                this.display2_$eq(this.copyRange(this.display2(), oldLeft, newLeft));
                break;
            }
            case 1: {
                this.display1_$eq(this.copyRange(this.display1(), oldLeft, newLeft));
                break;
            }
            case 0: {
                this.display0_$eq(this.copyRange(this.display0(), oldLeft, newLeft));
            }
        }
    }

    private void zeroLeft(Object[] array, int index) {
        for (int i = 0; i < index; ++i) {
            array[i] = null;
        }
    }

    private void zeroRight(Object[] array, int index) {
        for (int i = index; i < array.length; ++i) {
            array[i] = null;
        }
    }

    private Object[] copyLeft(Object[] array, int right) {
        Object[] a2 = new Object[array.length];
        Platform$ platform$ = Platform$.MODULE$;
        System.arraycopy(array, 0, a2, 0, right);
        return a2;
    }

    private Object[] copyRight(Object[] array, int left) {
        Object[] a2 = new Object[array.length];
        int n = a2.length - left;
        Platform$ platform$ = Platform$.MODULE$;
        System.arraycopy(array, left, a2, left, n);
        return a2;
    }

    private void preClean(int depth) {
        this.depth_$eq(depth);
        int n = depth - 1;
        switch (n) {
            default: {
                throw new MatchError(BoxesRunTime.boxToInteger(n));
            }
            case 4: {
                this.display5_$eq(null);
                break;
            }
            case 3: {
                this.display4_$eq(null);
                this.display5_$eq(null);
                break;
            }
            case 2: {
                this.display3_$eq(null);
                this.display4_$eq(null);
                this.display5_$eq(null);
                break;
            }
            case 1: {
                this.display2_$eq(null);
                this.display3_$eq(null);
                this.display4_$eq(null);
                this.display5_$eq(null);
                break;
            }
            case 0: {
                this.display1_$eq(null);
                this.display2_$eq(null);
                this.display3_$eq(null);
                this.display4_$eq(null);
                this.display5_$eq(null);
            }
            case 5: 
        }
    }

    private void cleanLeftEdge(int cutIndex) {
        block8: {
            block3: {
                block7: {
                    block6: {
                        block5: {
                            block4: {
                                block2: {
                                    if (cutIndex >= 32) break block2;
                                    this.zeroLeft(this.display0(), cutIndex);
                                    break block3;
                                }
                                if (cutIndex >= 1024) break block4;
                                this.zeroLeft(this.display0(), cutIndex & 0x1F);
                                this.display1_$eq(this.copyRight(this.display1(), cutIndex >>> 5));
                                break block3;
                            }
                            if (cutIndex >= 32768) break block5;
                            this.zeroLeft(this.display0(), cutIndex & 0x1F);
                            this.display1_$eq(this.copyRight(this.display1(), cutIndex >>> 5 & 0x1F));
                            this.display2_$eq(this.copyRight(this.display2(), cutIndex >>> 10));
                            break block3;
                        }
                        if (cutIndex >= 0x100000) break block6;
                        this.zeroLeft(this.display0(), cutIndex & 0x1F);
                        this.display1_$eq(this.copyRight(this.display1(), cutIndex >>> 5 & 0x1F));
                        this.display2_$eq(this.copyRight(this.display2(), cutIndex >>> 10 & 0x1F));
                        this.display3_$eq(this.copyRight(this.display3(), cutIndex >>> 15));
                        break block3;
                    }
                    if (cutIndex >= 0x2000000) break block7;
                    this.zeroLeft(this.display0(), cutIndex & 0x1F);
                    this.display1_$eq(this.copyRight(this.display1(), cutIndex >>> 5 & 0x1F));
                    this.display2_$eq(this.copyRight(this.display2(), cutIndex >>> 10 & 0x1F));
                    this.display3_$eq(this.copyRight(this.display3(), cutIndex >>> 15 & 0x1F));
                    this.display4_$eq(this.copyRight(this.display4(), cutIndex >>> 20));
                    break block3;
                }
                if (cutIndex >= 0x40000000) break block8;
                this.zeroLeft(this.display0(), cutIndex & 0x1F);
                this.display1_$eq(this.copyRight(this.display1(), cutIndex >>> 5 & 0x1F));
                this.display2_$eq(this.copyRight(this.display2(), cutIndex >>> 10 & 0x1F));
                this.display3_$eq(this.copyRight(this.display3(), cutIndex >>> 15 & 0x1F));
                this.display4_$eq(this.copyRight(this.display4(), cutIndex >>> 20 & 0x1F));
                this.display5_$eq(this.copyRight(this.display5(), cutIndex >>> 25));
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    private void cleanRightEdge(int cutIndex) {
        block8: {
            block3: {
                block7: {
                    block6: {
                        block5: {
                            block4: {
                                block2: {
                                    if (cutIndex > 32) break block2;
                                    this.zeroRight(this.display0(), cutIndex);
                                    break block3;
                                }
                                if (cutIndex > 1024) break block4;
                                this.zeroRight(this.display0(), (cutIndex - 1 & 0x1F) + 1);
                                this.display1_$eq(this.copyLeft(this.display1(), cutIndex >>> 5));
                                break block3;
                            }
                            if (cutIndex > 32768) break block5;
                            this.zeroRight(this.display0(), (cutIndex - 1 & 0x1F) + 1);
                            this.display1_$eq(this.copyLeft(this.display1(), (cutIndex - 1 >>> 5 & 0x1F) + 1));
                            this.display2_$eq(this.copyLeft(this.display2(), cutIndex >>> 10));
                            break block3;
                        }
                        if (cutIndex > 0x100000) break block6;
                        this.zeroRight(this.display0(), (cutIndex - 1 & 0x1F) + 1);
                        this.display1_$eq(this.copyLeft(this.display1(), (cutIndex - 1 >>> 5 & 0x1F) + 1));
                        this.display2_$eq(this.copyLeft(this.display2(), (cutIndex - 1 >>> 10 & 0x1F) + 1));
                        this.display3_$eq(this.copyLeft(this.display3(), cutIndex >>> 15));
                        break block3;
                    }
                    if (cutIndex > 0x2000000) break block7;
                    this.zeroRight(this.display0(), (cutIndex - 1 & 0x1F) + 1);
                    this.display1_$eq(this.copyLeft(this.display1(), (cutIndex - 1 >>> 5 & 0x1F) + 1));
                    this.display2_$eq(this.copyLeft(this.display2(), (cutIndex - 1 >>> 10 & 0x1F) + 1));
                    this.display3_$eq(this.copyLeft(this.display3(), (cutIndex - 1 >>> 15 & 0x1F) + 1));
                    this.display4_$eq(this.copyLeft(this.display4(), cutIndex >>> 20));
                    break block3;
                }
                if (cutIndex > 0x40000000) break block8;
                this.zeroRight(this.display0(), (cutIndex - 1 & 0x1F) + 1);
                this.display1_$eq(this.copyLeft(this.display1(), (cutIndex - 1 >>> 5 & 0x1F) + 1));
                this.display2_$eq(this.copyLeft(this.display2(), (cutIndex - 1 >>> 10 & 0x1F) + 1));
                this.display3_$eq(this.copyLeft(this.display3(), (cutIndex - 1 >>> 15 & 0x1F) + 1));
                this.display4_$eq(this.copyLeft(this.display4(), (cutIndex - 1 >>> 20 & 0x1F) + 1));
                this.display5_$eq(this.copyLeft(this.display5(), cutIndex >>> 25));
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    private int requiredDepth(int xor) {
        block8: {
            int n;
            block3: {
                block7: {
                    block6: {
                        block5: {
                            block4: {
                                block2: {
                                    if (xor >= 32) break block2;
                                    n = 1;
                                    break block3;
                                }
                                if (xor >= 1024) break block4;
                                n = 2;
                                break block3;
                            }
                            if (xor >= 32768) break block5;
                            n = 3;
                            break block3;
                        }
                        if (xor >= 0x100000) break block6;
                        n = 4;
                        break block3;
                    }
                    if (xor >= 0x2000000) break block7;
                    n = 5;
                    break block3;
                }
                if (xor >= 0x40000000) break block8;
                n = 6;
            }
            return n;
        }
        throw new IllegalArgumentException();
    }

    private Vector<A> dropFront0(int cutIndex) {
        int blockIndex = cutIndex & ~31;
        int xor = cutIndex ^ this.endIndex() - 1;
        int d = this.requiredDepth(xor);
        int shift = cutIndex & ~((1 << 5 * d) - 1);
        Vector<A> s2 = new Vector<A>(cutIndex - shift, this.endIndex() - shift, blockIndex - shift);
        s2.initFrom(this);
        s2.dirty_$eq(this.dirty());
        super.gotoPosWritable(this.focus, blockIndex, this.focus ^ blockIndex);
        super.preClean(d);
        super.cleanLeftEdge(cutIndex - shift);
        return s2;
    }

    private Vector<A> dropBack0(int cutIndex) {
        int blockIndex = cutIndex - 1 & ~31;
        int xor = this.startIndex() ^ cutIndex - 1;
        int d = this.requiredDepth(xor);
        int shift = this.startIndex() & ~((1 << 5 * d) - 1);
        Vector<A> s2 = new Vector<A>(this.startIndex() - shift, cutIndex - shift, blockIndex - shift);
        s2.initFrom(this);
        s2.dirty_$eq(this.dirty());
        super.gotoPosWritable(this.focus, blockIndex, this.focus ^ blockIndex);
        super.preClean(d);
        super.cleanRightEdge(cutIndex - shift);
        return s2;
    }

    public Vector(int startIndex, int endIndex, int focus) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.focus = focus;
        Traversable$class.$init$(this);
        Iterable$class.$init$(this);
        Seq$class.$init$(this);
        IndexedSeqLike$class.$init$(this);
        scala.collection.IndexedSeq$class.$init$(this);
        IndexedSeq$class.$init$(this);
        VectorPointer$class.$init$(this);
        CustomParallelizable$class.$init$(this);
        this.dirty = false;
    }
}

