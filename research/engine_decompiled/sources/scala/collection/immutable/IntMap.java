/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.immutable.AbstractMap;
import scala.collection.immutable.IntMap$;
import scala.collection.immutable.IntMap$Nil$;
import scala.collection.immutable.IntMapEntryIterator;
import scala.collection.immutable.IntMapKeyIterator;
import scala.collection.immutable.IntMapUtils$;
import scala.collection.immutable.IntMapValueIterator;
import scala.collection.immutable.List;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;
import scala.sys.package$;

@ScalaSignature(bytes="\u0006\u0001\u0011\u0005t!B\u0001\u0003\u0011\u0003I\u0011AB%oi6\u000b\u0007O\u0003\u0002\u0004\t\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003\u000b\u0019\t!bY8mY\u0016\u001cG/[8o\u0015\u00059\u0011!B:dC2\f7\u0001\u0001\t\u0003\u0015-i\u0011A\u0001\u0004\u0006\u0019\tA\t!\u0004\u0002\u0007\u0013:$X*\u00199\u0014\u0005-q\u0001CA\b\u0011\u001b\u00051\u0011BA\t\u0007\u0005\u0019\te.\u001f*fM\")1c\u0003C\u0001)\u00051A(\u001b8jiz\"\u0012!\u0003\u0005\u0006--!\u0019aF\u0001\rG\u0006t')^5mI\u001a\u0013x.\\\u000b\u00061\ru5QU\u000b\u00023I\u0019!D\u0004\u000f\u0007\tm)\u0002!\u0007\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\b;\u0001\u00123\u0011UBU\u001b\u0005q\"BA\u0010\u0005\u0003\u001d9WM\\3sS\u000eL!!\t\u0010\u0003\u0019\r\u000bgNQ;jY\u00124%o\\7\u0011\t)\u001931\u0014\u0004\u0006\u0019\t\t\t\u0003J\u000b\u0003KQ\u001aBa\t\u0014>\u0001B!!bJ\u00153\u0013\tA#AA\u0006BEN$(/Y2u\u001b\u0006\u0004\bC\u0001\u0016.\u001d\tQ1&\u0003\u0002-\u0005\u0005Y\u0011J\u001c;NCB,F/\u001b7t\u0013\tqsFA\u0002J]RL!A\f\u0019\u000b\u0005Er\u0012!\u0004\"ji>\u0003XM]1uS>t7\u000f\u0005\u00024i1\u0001AAB\u001b$\t\u000b\u0007aGA\u0001U#\t9$\b\u0005\u0002\u0010q%\u0011\u0011H\u0002\u0002\b\u001d>$\b.\u001b8h!\ty1(\u0003\u0002=\r\t\u0019\u0011I\\=\u0011\t)q\u0014FM\u0005\u0003\u007f\t\u00111!T1q!\u0015Q\u0011)\u000b\u001aD\u0013\t\u0011%AA\u0004NCBd\u0015n[3\u0011\u0007)\u0019#\u0007C\u0003\u0014G\u0011\u0005Q\tF\u0001D\u0011\u001595\u0005\"\u0011I\u0003\u0015)W\u000e\u001d;z+\u0005\u0019\u0005\"\u0002&$\t\u0003Z\u0015A\u0002;p\u0019&\u001cH/F\u0001M!\rQQjT\u0005\u0003\u001d\n\u0011A\u0001T5tiB!q\u0002U\u00153\u0013\t\tfA\u0001\u0004UkBdWM\r\u0005\u0006'\u000e\"\t\u0001V\u0001\tSR,'/\u0019;peV\tQ\u000bE\u0002W/>k\u0011\u0001B\u0005\u00031\u0012\u0011\u0001\"\u0013;fe\u0006$xN\u001d\u0005\u00065\u000e\")eW\u0001\bM>\u0014X-Y2i+\taf\r\u0006\u0002^AB\u0011qBX\u0005\u0003?\u001a\u0011A!\u00168ji\")\u0011-\u0017a\u0001E\u0006\ta\r\u0005\u0003\u0010G>+\u0017B\u00013\u0007\u0005%1UO\\2uS>t\u0017\u0007\u0005\u00024M\u0012)q-\u0017b\u0001m\t\tQ\u000bC\u0003jG\u0011\u0005#.\u0001\u0007lKf\u001c\u0018\n^3sCR|'/F\u0001l!\r1v+\u000b\u0005\u0006[\u000e\")A\\\u0001\u000bM>\u0014X-Y2i\u0017\u0016LHCA/p\u0011\u0015\tG\u000e1\u0001q!\u0011y1-K/\t\u000bI\u001cC\u0011I:\u0002\u001dY\fG.^3t\u0013R,'/\u0019;peV\tA\u000fE\u0002W/JBQA^\u0012\u0005\u0006]\fABZ8sK\u0006\u001c\u0007NV1mk\u0016$\"!\u0018=\t\u000b\u0005,\b\u0019A=\u0011\t=\u0019''\u0018\u0005\u0006w\u000e\"\t\u0005`\u0001\rgR\u0014\u0018N\\4Qe\u00164\u0017\u000e_\u000b\u0002{B\u0019a0a\u0002\u000e\u0003}TA!!\u0001\u0002\u0004\u0005!A.\u00198h\u0015\t\t)!\u0001\u0003kCZ\f\u0017bAA\u0005\u007f\n11\u000b\u001e:j]\u001eDq!!\u0004$\t\u0003\ny!A\u0004jg\u0016k\u0007\u000f^=\u0016\u0005\u0005E\u0001cA\b\u0002\u0014%\u0019\u0011Q\u0003\u0004\u0003\u000f\t{w\u000e\\3b]\"9\u0011\u0011D\u0012\u0005B\u0005m\u0011A\u00024jYR,'\u000fF\u0002D\u0003;Aq!YA\f\u0001\u0004\ty\u0002E\u0003\u0010G>\u000b\t\u0002C\u0004\u0002$\r\"\t!!\n\u0002\u0013Q\u0014\u0018M\\:g_JlW\u0003BA\u0014\u0003[!B!!\u000b\u00022A!!bIA\u0016!\r\u0019\u0014Q\u0006\u0003\b\u0003_\t\tC1\u00017\u0005\u0005\u0019\u0006bB1\u0002\"\u0001\u0007\u00111\u0007\t\b\u001f\u0005U\u0012FMA\u0016\u0013\r\t9D\u0002\u0002\n\rVt7\r^5p]JBq!a\u000f$\t\u000b\ni$\u0001\u0003tSj,W#A\u0015\t\u000f\u0005\u00053\u0005\"\u0002\u0002D\u0005\u0019q-\u001a;\u0015\t\u0005\u0015\u00131\n\t\u0005\u001f\u0005\u001d#'C\u0002\u0002J\u0019\u0011aa\u00149uS>t\u0007bBA'\u0003\u007f\u0001\r!K\u0001\u0004W\u0016L\bbBA)G\u0011\u0015\u00131K\u0001\nO\u0016$xJ]#mg\u0016,B!!\u0016\u0002ZQ1\u0011qKA/\u0003?\u00022aMA-\t!\ty#a\u0014C\u0002\u0005m\u0013C\u0001\u001a;\u0011\u001d\ti%a\u0014A\u0002%B\u0011\"!\u0019\u0002P\u0011\u0005\r!a\u0019\u0002\u000f\u0011,g-Y;miB)q\"!\u001a\u0002X%\u0019\u0011q\r\u0004\u0003\u0011q\u0012\u0017P\\1nKzBq!a\u001b$\t\u000b\ni'A\u0003baBd\u0017\u0010F\u00023\u0003_Bq!!\u0014\u0002j\u0001\u0007\u0011\u0006C\u0004\u0002t\r\"\t!!\u001e\u0002\u000b\u0011\u0002H.^:\u0016\t\u0005]\u0014Q\u0010\u000b\u0005\u0003s\ny\b\u0005\u0003\u000bG\u0005m\u0004cA\u001a\u0002~\u0011A\u0011qFA9\u0005\u0004\tY\u0006\u0003\u0005\u0002\u0002\u0006E\u0004\u0019AAB\u0003\tYg\u000fE\u0003\u0010!&\nY\bC\u0004\u0002\b\u000e\"\t%!#\u0002\u000fU\u0004H-\u0019;fIV!\u00111RAI)\u0019\ti)a%\u0002\u0016B!!bIAH!\r\u0019\u0014\u0011\u0013\u0003\t\u0003_\t)I1\u0001\u0002\\!9\u0011QJAC\u0001\u0004I\u0003\u0002CAL\u0003\u000b\u0003\r!a$\u0002\u000bY\fG.^3\t\u000f\u0005m5\u0005\"\u0001\u0002\u001e\u0006QQ\u000f\u001d3bi\u0016<\u0016\u000e\u001e5\u0016\t\u0005}\u0015Q\u0015\u000b\t\u0003C\u000b9+!+\u0002,B!!bIAR!\r\u0019\u0014Q\u0015\u0003\t\u0003_\tIJ1\u0001\u0002\\!9\u0011QJAM\u0001\u0004I\u0003\u0002CAL\u00033\u0003\r!a)\t\u000f\u0005\fI\n1\u0001\u0002.BAq\"!\u000e3\u0003G\u000b\u0019\u000bC\u0004\u00022\u000e\"\t!a-\u0002\r\u0011j\u0017N\\;t)\r\u0019\u0015Q\u0017\u0005\b\u0003\u001b\ny\u000b1\u0001*\u0011\u001d\tIl\tC\u0001\u0003w\u000ba\"\\8eS\u001aLxJ\u001d*f[>4X-\u0006\u0003\u0002>\u0006\rG\u0003BA`\u0003\u000b\u0004BAC\u0012\u0002BB\u00191'a1\u0005\u000f\u0005=\u0012q\u0017b\u0001m!9\u0011-a.A\u0002\u0005\u001d\u0007cB\b\u00026%\u0012\u0014\u0011\u001a\t\u0006\u001f\u0005\u001d\u0013\u0011\u0019\u0005\b\u0003\u001b\u001cC\u0011AAh\u0003%)h.[8o/&$\b.\u0006\u0003\u0002R\u0006]GCBAj\u00033\fi\u000e\u0005\u0003\u000bG\u0005U\u0007cA\u001a\u0002X\u0012A\u0011qFAf\u0005\u0004\tY\u0006\u0003\u0005\u0002\\\u0006-\u0007\u0019AAj\u0003\u0011!\b.\u0019;\t\u000f\u0005\fY\r1\u0001\u0002`BQq\"!9*\u0003+\f).!6\n\u0007\u0005\rhAA\u0005Gk:\u001cG/[8og!9\u0011q]\u0012\u0005\u0002\u0005%\u0018\u0001E5oi\u0016\u00148/Z2uS>tw+\u001b;i+\u0019\tY/a?\u0002rR1\u0011Q^A{\u0003{\u0004BAC\u0012\u0002pB\u00191'!=\u0005\u000f\u0005M\u0018Q\u001db\u0001m\t\t!\u000b\u0003\u0005\u0002\\\u0006\u0015\b\u0019AA|!\u0011Q1%!?\u0011\u0007M\nY\u0010B\u0004\u00020\u0005\u0015(\u0019\u0001\u001c\t\u000f\u0005\f)\u000f1\u0001\u0002\u0000BIq\"!9*e\u0005e\u0018q\u001e\u0005\b\u0005\u0007\u0019C\u0011\u0001B\u0003\u00031Ig\u000e^3sg\u0016\u001cG/[8o+\u0011\u00119Aa\u0004\u0015\u0007\r\u0013I\u0001\u0003\u0005\u0002\\\n\u0005\u0001\u0019\u0001B\u0006!\u0011Q1E!\u0004\u0011\u0007M\u0012y\u0001B\u0004\u0002t\n\u0005!\u0019\u0001\u001c\t\u000f\tM1\u0005\"\u0001\u0003\u0016\u0005QA\u0005\u001d7vg\u0012\u0002H.^:\u0016\t\t]!Q\u0004\u000b\u0005\u00053\u0011y\u0002\u0005\u0003\u000bG\tm\u0001cA\u001a\u0003\u001e\u0011A\u0011q\u0006B\t\u0005\u0004\tY\u0006\u0003\u0005\u0002\\\nE\u0001\u0019\u0001B\r\u0011\u001d\u0011\u0019c\tC\u0003\u0003{\t\u0001BZ5sgR\\U-\u001f\u0015\u0005\u0005C\u00119\u0003\u0005\u0003\u0003*\t=RB\u0001B\u0016\u0015\r\u0011iCB\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002\u0002B\u0019\u0005W\u0011q\u0001^1jYJ,7\rC\u0004\u00036\r\")!!\u0010\u0002\u000f1\f7\u000f^&fs\"\"!1\u0007B\u0014S\u001d\u0019#1HB\u0004\u0007k1qA!\u0010\f\u0001\n\u0011yDA\u0002CS:,BA!\u0011\u0003HMA!1\bB\"\u0005\u0013\u0012y\u0005\u0005\u0003\u000bG\t\u0015\u0003cA\u001a\u0003H\u00119QGa\u000f\u0005\u0006\u00041\u0004cA\b\u0003L%\u0019!Q\n\u0004\u0003\u000fA\u0013x\u000eZ;diB\u0019qB!\u0015\n\u0007\tMcA\u0001\u0007TKJL\u0017\r\\5{C\ndW\rC\u0006\u0003X\tm\"Q3A\u0005\u0002\u0005u\u0012A\u00029sK\u001aL\u0007\u0010\u0003\u0006\u0003\\\tm\"\u0011#Q\u0001\n%\nq\u0001\u001d:fM&D\b\u0005C\u0006\u0003`\tm\"Q3A\u0005\u0002\u0005u\u0012\u0001B7bg.D!Ba\u0019\u0003<\tE\t\u0015!\u0003*\u0003\u0015i\u0017m]6!\u0011-\u00119Ga\u000f\u0003\u0016\u0004%\tA!\u001b\u0002\t1,g\r^\u000b\u0003\u0005\u0007B1B!\u001c\u0003<\tE\t\u0015!\u0003\u0003D\u0005)A.\u001a4uA!Y!\u0011\u000fB\u001e\u0005+\u0007I\u0011\u0001B5\u0003\u0015\u0011\u0018n\u001a5u\u0011-\u0011)Ha\u000f\u0003\u0012\u0003\u0006IAa\u0011\u0002\rILw\r\u001b;!\u0011\u001d\u0019\"1\bC\u0001\u0005s\"\"Ba\u001f\u0003\u0000\t\u0005%1\u0011BC!\u0019\u0011iHa\u000f\u0003F5\t1\u0002C\u0004\u0003X\t]\u0004\u0019A\u0015\t\u000f\t}#q\u000fa\u0001S!A!q\rB<\u0001\u0004\u0011\u0019\u0005\u0003\u0005\u0003r\t]\u0004\u0019\u0001B\"\u0011!\u0011IIa\u000f\u0005\u0002\t-\u0015a\u00012j]V!!Q\u0012BJ)\u0019\u0011yI!&\u0003\u0018B!!b\tBI!\r\u0019$1\u0013\u0003\b\u0003_\u00119I1\u00017\u0011!\u00119Ga\"A\u0002\t=\u0005\u0002\u0003B9\u0005\u000f\u0003\rAa$\t\u0015\tm%1HA\u0001\n\u0003\u0011i*\u0001\u0003d_BLX\u0003\u0002BP\u0005K#\"B!)\u0003(\n%&1\u0016BX!\u0019\u0011iHa\u000f\u0003$B\u00191G!*\u0005\rU\u0012IJ1\u00017\u0011%\u00119F!'\u0011\u0002\u0003\u0007\u0011\u0006C\u0005\u0003`\te\u0005\u0013!a\u0001S!Q!q\rBM!\u0003\u0005\rA!,\u0011\t)\u0019#1\u0015\u0005\u000b\u0005c\u0012I\n%AA\u0002\t5\u0006B\u0003BZ\u0005w\t\n\u0011\"\u0001\u00036\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT\u0003\u0002B\\\u0005\u0013,\"A!/+\u0007%\u0012Yl\u000b\u0002\u0003>B!!q\u0018Bc\u001b\t\u0011\tM\u0003\u0003\u0003D\n-\u0012!C;oG\",7m[3e\u0013\u0011\u00119M!1\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\r\u0002\u00046\u0005c\u0013\rA\u000e\u0005\u000b\u0005\u001b\u0014Y$%A\u0005\u0002\t=\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0005\u0005o\u0013\t\u000e\u0002\u00046\u0005\u0017\u0014\rA\u000e\u0005\u000b\u0005+\u0014Y$%A\u0005\u0002\t]\u0017AD2paf$C-\u001a4bk2$HeM\u000b\u0005\u00053\u0014i.\u0006\u0002\u0003\\*\"!1\tB^\t\u0019)$1\u001bb\u0001m!Q!\u0011\u001dB\u001e#\u0003%\tAa9\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU!!\u0011\u001cBs\t\u0019)$q\u001cb\u0001m!I!\u0011\u001eB\u001e\u0003\u0003%\t\u0005`\u0001\u000eaJ|G-^2u!J,g-\u001b=\t\u0015\t5(1HA\u0001\n\u0003\u0011y/\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0003rB\u0019qBa=\n\u000592\u0001B\u0003B|\u0005w\t\t\u0011\"\u0001\u0003z\u0006q\u0001O]8ek\u000e$X\t\\3nK:$Hc\u0001\u001e\u0003|\"Q!Q B{\u0003\u0003\u0005\rA!=\u0002\u0007a$\u0013\u0007\u0003\u0006\u0004\u0002\tm\u0012\u0011!C!\u0007\u0007\tq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0007\u000b\u00012AV,;\r!\u0019Ia\u0003EA\u0005\r-!a\u0001(jYNA1qAB\u0007\u0005\u0013\u0012y\u0005E\u0002\u000bG]BqaEB\u0004\t\u0003\u0019\t\u0002\u0006\u0002\u0004\u0014A!!QPB\u0004\u0011!\u00199ba\u0002\u0005B\re\u0011AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0012\rm\u0001bBAn\u0007+\u0001\rA\u000f\u0005\n\u0005S\u001c9!!A\u0005BqD!B!<\u0004\b\u0005\u0005I\u0011\u0001Bx\u0011)\u00119pa\u0002\u0002\u0002\u0013\u000511\u0005\u000b\u0004u\r\u0015\u0002B\u0003B\u007f\u0007C\t\t\u00111\u0001\u0003r\"Q1\u0011AB\u0004\u0003\u0003%\tea\u0001\t\u0015\r-2qAA\u0001\n\u0013\u0019i#A\u0006sK\u0006$'+Z:pYZ,GCAB\u0018!\rq8\u0011G\u0005\u0004\u0007gy(AB(cU\u0016\u001cGOB\u0004\u00048-\u0001%a!\u000f\u0003\u0007QK\u0007/\u0006\u0003\u0004<\r\u00053\u0003CB\u001b\u0007{\u0011IEa\u0014\u0011\t)\u00193q\b\t\u0004g\r\u0005CaB\u001b\u00046\u0011\u0015\rA\u000e\u0005\f\u0003\u001b\u001a)D!f\u0001\n\u0003\ti\u0004\u0003\u0006\u0004H\rU\"\u0011#Q\u0001\n%\nAa[3zA!Y\u0011qSB\u001b\u0005+\u0007I\u0011AB&+\t\u0019y\u0004C\u0006\u0004P\rU\"\u0011#Q\u0001\n\r}\u0012A\u0002<bYV,\u0007\u0005C\u0004\u0014\u0007k!\taa\u0015\u0015\r\rU3qKB-!\u0019\u0011ih!\u000e\u0004@!9\u0011QJB)\u0001\u0004I\u0003\u0002CAL\u0007#\u0002\raa\u0010\t\u0011\ru3Q\u0007C\u0001\u0007?\n\u0011b^5uQZ\u000bG.^3\u0016\t\r\u00054\u0011\u000e\u000b\u0005\u0007G\u001aY\u0007\u0005\u0004\u0004f\rU2q\r\b\u0003\u0015\u0001\u00012aMB5\t\u001d\tyca\u0017C\u0002YB\u0001b!\u001c\u0004\\\u0001\u00071qM\u0001\u0002g\"Q!1TB\u001b\u0003\u0003%\ta!\u001d\u0016\t\rM4\u0011\u0010\u000b\u0007\u0007k\u001aYh! \u0011\r\tu4QGB<!\r\u00194\u0011\u0010\u0003\u0007k\r=$\u0019\u0001\u001c\t\u0013\u000553q\u000eI\u0001\u0002\u0004I\u0003BCAL\u0007_\u0002\n\u00111\u0001\u0004x!Q!1WB\u001b#\u0003%\ta!!\u0016\t\t]61\u0011\u0003\u0007k\r}$\u0019\u0001\u001c\t\u0015\t57QGI\u0001\n\u0003\u00199)\u0006\u0003\u0004\n\u000e5UCABFU\u0011\u0019yDa/\u0005\rU\u001a)I1\u00017\u0011%\u0011Io!\u000e\u0002\u0002\u0013\u0005C\u0010\u0003\u0006\u0003n\u000eU\u0012\u0011!C\u0001\u0005_D!Ba>\u00046\u0005\u0005I\u0011ABK)\rQ4q\u0013\u0005\u000b\u0005{\u001c\u0019*!AA\u0002\tE\bBCB\u0001\u0007k\t\t\u0011\"\u0011\u0004\u0004A\u00191g!(\u0005\r\r}UC1\u00017\u0005\u0005\t\u0005#B\bQS\r\r\u0006cA\u001a\u0004&\u001211qU\u000bC\u0002Y\u0012\u0011A\u0011\t\u0005\u0015\r\u001a\u0019\u000b\u0003\u0004H\u0017\u0011\u00051QV\u000b\u0005\u0007_\u001b),\u0006\u0002\u00042B!!bIBZ!\r\u00194Q\u0017\u0003\u0007k\r-&\u0019\u0001\u001c\t\u000f\re6\u0002\"\u0001\u0004<\u0006I1/\u001b8hY\u0016$xN\\\u000b\u0005\u0007{\u001b\u0019\r\u0006\u0004\u0004@\u000e\u00157q\u0019\t\u0005\u0015\r\u001a\t\rE\u00024\u0007\u0007$a!NB\\\u0005\u00041\u0004bBA'\u0007o\u0003\r!\u000b\u0005\t\u0003/\u001b9\f1\u0001\u0004B\"9\u00111N\u0006\u0005\u0002\r-W\u0003BBg\u0007'$Baa4\u0004VB!!bIBi!\r\u001941\u001b\u0003\u0007k\r%'\u0019\u0001\u001c\t\u0011\r]7\u0011\u001aa\u0001\u00073\fQ!\u001a7f[N\u0004RaDBn\u0007?L1a!8\u0007\u0005)a$/\u001a9fCR,GM\u0010\t\u0006\u001fAK3\u0011[\u0004\t\u0007G\\\u0001\u0012\u0011\u0002\u0004\u0014\u0005\u0019a*\u001b7\b\u0015\r\u001d8\"!A\t\u0002\t\u0019I/A\u0002USB\u0004BA! \u0004l\u001aQ1qG\u0006\u0002\u0002#\u0005!a!<\u0014\u000b\r-hBa\u0014\t\u000fM\u0019Y\u000f\"\u0001\u0004rR\u00111\u0011\u001e\u0005\u000b\u0007k\u001cY/!A\u0005F\r]\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003uD!\"a\u001b\u0004l\u0006\u0005I\u0011QB~+\u0011\u0019i\u0010b\u0001\u0015\r\r}HQ\u0001C\u0004!\u0019\u0011ih!\u000e\u0005\u0002A\u00191\u0007b\u0001\u0005\rU\u001aIP1\u00017\u0011\u001d\tie!?A\u0002%B\u0001\"a&\u0004z\u0002\u0007A\u0011\u0001\u0005\u000b\t\u0017\u0019Y/!A\u0005\u0002\u00125\u0011aB;oCB\u0004H._\u000b\u0005\t\u001f!9\u0002\u0006\u0003\u0005\u0012\u0011e\u0001#B\b\u0002H\u0011M\u0001#B\bQS\u0011U\u0001cA\u001a\u0005\u0018\u00111Q\u0007\"\u0003C\u0002YB!\u0002b\u0007\u0005\n\u0005\u0005\t\u0019\u0001C\u000f\u0003\rAH\u0005\r\t\u0007\u0005{\u001a)\u0004\"\u0006\t\u0015\r-21^A\u0001\n\u0013\u0019ic\u0002\u0006\u0005$-\t\t\u0011#\u0001\u0003\tK\t1AQ5o!\u0011\u0011i\bb\n\u0007\u0015\tu2\"!A\t\u0002\t!IcE\u0003\u0005(9\u0011y\u0005C\u0004\u0014\tO!\t\u0001\"\f\u0015\u0005\u0011\u0015\u0002BCB{\tO\t\t\u0011\"\u0012\u0004x\"Q\u00111\u000eC\u0014\u0003\u0003%\t\tb\r\u0016\t\u0011UB1\b\u000b\u000b\to!i\u0004b\u0010\u0005B\u0011\u0015\u0003C\u0002B?\u0005w!I\u0004E\u00024\tw!a!\u000eC\u0019\u0005\u00041\u0004b\u0002B,\tc\u0001\r!\u000b\u0005\b\u0005?\"\t\u00041\u0001*\u0011!\u00119\u0007\"\rA\u0002\u0011\r\u0003\u0003\u0002\u0006$\tsA\u0001B!\u001d\u00052\u0001\u0007A1\t\u0005\u000b\t\u0017!9#!A\u0005\u0002\u0012%S\u0003\u0002C&\t3\"B\u0001\"\u0014\u0005\\A)q\"a\u0012\u0005PAIq\u0002\"\u0015*S\u0011UCQK\u0005\u0004\t'2!A\u0002+va2,G\u0007\u0005\u0003\u000bG\u0011]\u0003cA\u001a\u0005Z\u00111Q\u0007b\u0012C\u0002YB!\u0002b\u0007\u0005H\u0005\u0005\t\u0019\u0001C/!\u0019\u0011iHa\u000f\u0005X!Q11\u0006C\u0014\u0003\u0003%Ia!\f")
public abstract class IntMap<T>
extends AbstractMap<Object, T> {
    public static <T> IntMap<T> singleton(int n, T t) {
        return IntMap$.MODULE$.singleton(n, t);
    }

    public static <A, B> Object canBuildFrom() {
        return IntMap$.MODULE$.canBuildFrom();
    }

    @Override
    public IntMap<T> empty() {
        return IntMap$Nil$.MODULE$;
    }

    @Override
    public List<Tuple2<Object, T>> toList() {
        ListBuffer buffer = new ListBuffer();
        this.foreach((Function1)((Object)new Serializable(this, buffer){
            public static final long serialVersionUID = 0L;
            private final ListBuffer buffer$1;

            public final ListBuffer<Tuple2<Object, T>> apply(Tuple2<Object, T> x$1) {
                return this.buffer$1.$plus$eq(x$1);
            }
            {
                this.buffer$1 = buffer$1;
            }
        }));
        return buffer.toList();
    }

    @Override
    public Iterator<Tuple2<Object, T>> iterator() {
        Iterator<Nothing$> iterator2 = ((Object)IntMap$Nil$.MODULE$).equals(this) ? Iterator$.MODULE$.empty() : new IntMapEntryIterator(this);
        return iterator2;
    }

    @Override
    public final <U> void foreach(Function1<Tuple2<Object, T>, U> f) {
        block5: {
            block4: {
                block3: {
                    while (this_ instanceof Bin) {
                        Bin bin = (Bin)this_;
                        bin.left().foreach(f);
                        IntMap this_ = bin.right();
                    }
                    if (!(this_ instanceof Tip)) break block3;
                    Tip tip = (Tip)this_;
                    f.apply(new Tuple2(BoxesRunTime.boxToInteger(tip.key()), tip.value()));
                    break block4;
                }
                if (!((Object)IntMap$Nil$.MODULE$).equals(this_)) break block5;
            }
            return;
        }
        throw new MatchError(this_);
    }

    @Override
    public Iterator<Object> keysIterator() {
        Iterator<Object> iterator2 = ((Object)IntMap$Nil$.MODULE$).equals(this) ? Iterator$.MODULE$.empty() : new IntMapKeyIterator(this);
        return iterator2;
    }

    public final void foreachKey(Function1<Object, BoxedUnit> f) {
        block5: {
            block4: {
                block3: {
                    while (this_ instanceof Bin) {
                        Bin bin = (Bin)this_;
                        bin.left().foreachKey(f);
                        IntMap this_ = bin.right();
                    }
                    if (!(this_ instanceof Tip)) break block3;
                    Tip tip = (Tip)this_;
                    f.apply$mcVI$sp(tip.key());
                    break block4;
                }
                if (!((Object)IntMap$Nil$.MODULE$).equals(this_)) break block5;
            }
            return;
        }
        throw new MatchError(this_);
    }

    @Override
    public Iterator<T> valuesIterator() {
        Iterator<Nothing$> iterator2 = ((Object)IntMap$Nil$.MODULE$).equals(this) ? Iterator$.MODULE$.empty() : new IntMapValueIterator<Nothing$>(this);
        return iterator2;
    }

    public final void foreachValue(Function1<T, BoxedUnit> f) {
        block5: {
            block4: {
                block3: {
                    while (this_ instanceof Bin) {
                        Bin bin = (Bin)this_;
                        bin.left().foreachValue(f);
                        IntMap this_ = bin.right();
                    }
                    if (!(this_ instanceof Tip)) break block3;
                    Tip tip = (Tip)this_;
                    f.apply(tip.value());
                    break block4;
                }
                if (!((Object)IntMap$Nil$.MODULE$).equals(this_)) break block5;
            }
            return;
        }
        throw new MatchError(this_);
    }

    @Override
    public String stringPrefix() {
        return "IntMap";
    }

    @Override
    public boolean isEmpty() {
        IntMap$Nil$ intMap$Nil$ = IntMap$Nil$.MODULE$;
        return ((Object)this).equals(intMap$Nil$);
    }

    @Override
    public IntMap<T> filter(Function1<Tuple2<Object, T>, Object> f) {
        block5: {
            IntMap intMap;
            block3: {
                block4: {
                    block2: {
                        if (!(this instanceof Bin)) break block2;
                        Bin bin = (Bin)this;
                        Tuple2<Object, Object> tuple2 = new Tuple2<Object, Object>(bin.left().filter((Function1)f), bin.right().filter((Function1)f));
                        Tuple2<Object, Object> tuple22 = new Tuple2<Object, Object>(tuple2._1(), tuple2._2());
                        IntMap newleft = (IntMap)tuple22._1();
                        IntMap newright = (IntMap)tuple22._2();
                        intMap = bin.left() == newleft && bin.right() == newright ? this : IntMapUtils$.MODULE$.bin(bin.prefix(), bin.mask(), newleft, newright);
                        break block3;
                    }
                    if (!(this instanceof Tip)) break block4;
                    Tip tip = (Tip)this;
                    intMap = BoxesRunTime.unboxToBoolean(f.apply(new Tuple2(BoxesRunTime.boxToInteger(tip.key()), tip.value()))) ? this : IntMap$Nil$.MODULE$;
                    break block3;
                }
                if (!((Object)IntMap$Nil$.MODULE$).equals(this)) break block5;
                intMap = IntMap$Nil$.MODULE$;
            }
            return intMap;
        }
        throw new MatchError(this);
    }

    public <S> IntMap<S> transform(Function2<Object, T, S> f) {
        block5: {
            IntMap intMap;
            block3: {
                block4: {
                    block2: {
                        if (!(this instanceof Bin)) break block2;
                        Bin bin = (Bin)this;
                        intMap = bin.bin(bin.left().transform(f), bin.right().transform(f));
                        break block3;
                    }
                    if (!(this instanceof Tip)) break block4;
                    Tip tip = (Tip)this;
                    intMap = tip.withValue(f.apply(BoxesRunTime.boxToInteger(tip.key()), tip.value()));
                    break block3;
                }
                if (!((Object)IntMap$Nil$.MODULE$).equals(this)) break block5;
                intMap = IntMap$Nil$.MODULE$;
            }
            return intMap;
        }
        throw new MatchError(this);
    }

    @Override
    public final int size() {
        block5: {
            int n;
            block3: {
                block4: {
                    block2: {
                        if (!((Object)IntMap$Nil$.MODULE$).equals(this)) break block2;
                        n = 0;
                        break block3;
                    }
                    if (!(this instanceof Tip)) break block4;
                    n = 1;
                    break block3;
                }
                if (!(this instanceof Bin)) break block5;
                Bin bin = (Bin)this;
                n = bin.left().size() + bin.right().size();
            }
            return n;
        }
        throw new MatchError(this);
    }

    @Override
    public final Option<T> get(int key) {
        block7: {
            None$ none$;
            block6: {
                block5: {
                    while (this_ instanceof Bin) {
                        IntMap this_;
                        Bin bin = (Bin)this_;
                        if (IntMapUtils$.MODULE$.zero(key, bin.mask())) {
                            this_ = bin.left();
                            continue;
                        }
                        this_ = bin.right();
                    }
                    if (!(this_ instanceof Tip)) break block5;
                    Tip tip = (Tip)this_;
                    none$ = key == tip.key() ? new Some(tip.value()) : None$.MODULE$;
                    break block6;
                }
                if (!((Object)IntMap$Nil$.MODULE$).equals(this_)) break block7;
                none$ = None$.MODULE$;
            }
            return none$;
        }
        throw new MatchError(this_);
    }

    @Override
    public final <S> S getOrElse(int key, Function0<S> function0) {
        while (true) {
            IntMap this_;
            block7: {
                Object object;
                block6: {
                    block5: {
                        if (!((Object)IntMap$Nil$.MODULE$).equals(this_)) break block5;
                        object = function0.apply();
                        break block6;
                    }
                    if (!(this_ instanceof Tip)) break block7;
                    Tip tip = (Tip)this_;
                    object = key == tip.key() ? tip.value() : function0.apply();
                }
                return object;
            }
            if (!(this_ instanceof Bin)) break;
            Bin bin = (Bin)this_;
            if (IntMapUtils$.MODULE$.zero(key, bin.mask())) {
                this_ = bin.left();
                continue;
            }
            this_ = bin.right();
        }
        throw new MatchError(this_);
    }

    @Override
    public final T apply(int key) {
        while (this_ instanceof Bin) {
            IntMap this_;
            Bin bin = (Bin)this_;
            if (IntMapUtils$.MODULE$.zero(key, bin.mask())) {
                this_ = bin.left();
                continue;
            }
            this_ = bin.right();
        }
        if (this_ instanceof Tip) {
            Tip tip = (Tip)this_;
            if (key == tip.key()) {
                return tip.value();
            }
            throw package$.MODULE$.error("Key not found");
        }
        if (((Object)IntMap$Nil$.MODULE$).equals(this_)) {
            throw package$.MODULE$.error("key not found");
        }
        throw new MatchError(this_);
    }

    public <S> IntMap<S> $plus(Tuple2<Object, S> kv) {
        return this.updated(kv._1$mcI$sp(), kv._2());
    }

    public <S> IntMap<S> updated(int key, S value) {
        block6: {
            IntMap intMap;
            block4: {
                block5: {
                    block3: {
                        if (!(this instanceof Bin)) break block3;
                        Bin bin = (Bin)this;
                        intMap = IntMapUtils$.MODULE$.hasMatch(key, bin.prefix(), bin.mask()) ? (IntMapUtils$.MODULE$.zero(key, bin.mask()) ? new Bin<S>(bin.prefix(), bin.mask(), bin.left().updated(key, value), bin.right()) : new Bin(bin.prefix(), bin.mask(), bin.left(), bin.right().updated(key, value))) : IntMapUtils$.MODULE$.join(key, new Tip<S>(key, value), bin.prefix(), this);
                        break block4;
                    }
                    if (!(this instanceof Tip)) break block5;
                    Tip tip = (Tip)this;
                    intMap = key == tip.key() ? new Tip<S>(key, value) : IntMapUtils$.MODULE$.join(key, new Tip<S>(key, value), tip.key(), this);
                    break block4;
                }
                if (!((Object)IntMap$Nil$.MODULE$).equals(this)) break block6;
                intMap = new Tip<S>(key, value);
            }
            return intMap;
        }
        throw new MatchError(this);
    }

    public <S> IntMap<S> updateWith(int key, S value, Function2<T, S, S> f) {
        block6: {
            IntMap intMap;
            block4: {
                block5: {
                    block3: {
                        if (!(this instanceof Bin)) break block3;
                        Bin bin = (Bin)this;
                        intMap = IntMapUtils$.MODULE$.hasMatch(key, bin.prefix(), bin.mask()) ? (IntMapUtils$.MODULE$.zero(key, bin.mask()) ? new Bin<S>(bin.prefix(), bin.mask(), bin.left().updateWith(key, value, f), bin.right()) : new Bin(bin.prefix(), bin.mask(), bin.left(), bin.right().updateWith(key, value, f))) : IntMapUtils$.MODULE$.join(key, new Tip<S>(key, value), bin.prefix(), this);
                        break block4;
                    }
                    if (!(this instanceof Tip)) break block5;
                    Tip tip = (Tip)this;
                    intMap = key == tip.key() ? new Tip<S>(key, f.apply(tip.value(), value)) : IntMapUtils$.MODULE$.join(key, new Tip<S>(key, value), tip.key(), this);
                    break block4;
                }
                if (!((Object)IntMap$Nil$.MODULE$).equals(this)) break block6;
                intMap = new Tip<S>(key, value);
            }
            return intMap;
        }
        throw new MatchError(this);
    }

    @Override
    public IntMap<T> $minus(int key) {
        block6: {
            IntMap intMap;
            block4: {
                block5: {
                    block3: {
                        if (!(this instanceof Bin)) break block3;
                        Bin bin = (Bin)this;
                        intMap = IntMapUtils$.MODULE$.hasMatch(key, bin.prefix(), bin.mask()) ? (IntMapUtils$.MODULE$.zero(key, bin.mask()) ? IntMapUtils$.MODULE$.bin(bin.prefix(), bin.mask(), bin.left().$minus(key), bin.right()) : IntMapUtils$.MODULE$.bin(bin.prefix(), bin.mask(), bin.left(), bin.right().$minus(key))) : this;
                        break block4;
                    }
                    if (!(this instanceof Tip)) break block5;
                    Tip tip = (Tip)this;
                    intMap = key == tip.key() ? IntMap$Nil$.MODULE$ : this;
                    break block4;
                }
                if (!((Object)IntMap$Nil$.MODULE$).equals(this)) break block6;
                intMap = IntMap$Nil$.MODULE$;
            }
            return intMap;
        }
        throw new MatchError(this);
    }

    public <S> IntMap<S> modifyOrRemove(Function2<Object, T, Option<S>> f) {
        block8: {
            IntMap intMap;
            block3: {
                block4: {
                    Option<S> option;
                    block7: {
                        IntMap intMap2;
                        block6: {
                            Tip tip;
                            block5: {
                                block2: {
                                    if (!(this instanceof Bin)) break block2;
                                    Bin bin = (Bin)((Object)this);
                                    IntMap<S> newleft = bin.left().modifyOrRemove(f);
                                    IntMap<S> newright = bin.right().modifyOrRemove(f);
                                    intMap = bin.left() == newleft && bin.right() == newright ? this : IntMapUtils$.MODULE$.bin(bin.prefix(), bin.mask(), newleft, newright);
                                    break block3;
                                }
                                if (!(this instanceof Tip)) break block4;
                                tip = this;
                                option = f.apply(BoxesRunTime.boxToInteger(tip.key()), tip.value());
                                if (!None$.MODULE$.equals(option)) break block5;
                                intMap2 = IntMap$Nil$.MODULE$;
                                break block6;
                            }
                            if (!(option instanceof Some)) break block7;
                            Some some = (Some)option;
                            intMap2 = tip.value() == some.x() ? this : new Tip(tip.key(), some.x());
                        }
                        intMap = intMap2;
                        break block3;
                    }
                    throw new MatchError(option);
                }
                if (!((Object)IntMap$Nil$.MODULE$).equals(this)) break block8;
                intMap = IntMap$Nil$.MODULE$;
            }
            return intMap;
        }
        throw new MatchError(this);
    }

    public <S> IntMap<S> unionWith(IntMap<S> that, Function3<Object, S, S, S> f) {
        Tuple2<IntMap, IntMap<S>> tuple2;
        block8: {
            IntMap intMap;
            block4: {
                block7: {
                    block6: {
                        block5: {
                            block3: {
                                tuple2 = new Tuple2<IntMap, IntMap<S>>(this, that);
                                if (!(tuple2._1() instanceof Bin)) break block3;
                                Bin bin = (Bin)tuple2._1();
                                if (!(tuple2._2() instanceof Bin)) break block3;
                                Bin bin2 = (Bin)tuple2._2();
                                intMap = IntMapUtils$.MODULE$.shorter(bin.mask(), bin2.mask()) ? (IntMapUtils$.MODULE$.hasMatch(bin2.prefix(), bin.prefix(), bin.mask()) ? (IntMapUtils$.MODULE$.zero(bin2.prefix(), bin.mask()) ? new Bin<S>(bin.prefix(), bin.mask(), bin.left().unionWith(bin2, f), bin.right()) : new Bin(bin.prefix(), bin.mask(), bin.left(), bin.right().unionWith(bin2, f))) : IntMapUtils$.MODULE$.join(bin.prefix(), this, bin2.prefix(), bin2)) : (IntMapUtils$.MODULE$.shorter(bin2.mask(), bin.mask()) ? (IntMapUtils$.MODULE$.hasMatch(bin.prefix(), bin2.prefix(), bin2.mask()) ? (IntMapUtils$.MODULE$.zero(bin.prefix(), bin2.mask()) ? new Bin(bin2.prefix(), bin2.mask(), this.unionWith(bin2.left(), f), bin2.right()) : new Bin(bin2.prefix(), bin2.mask(), bin2.left(), this.unionWith(bin2.right(), f))) : IntMapUtils$.MODULE$.join(bin.prefix(), this, bin2.prefix(), bin2)) : (bin.prefix() == bin2.prefix() ? new Bin(bin.prefix(), bin.mask(), bin.left().unionWith(bin2.left(), f), bin.right().unionWith(bin2.right(), f)) : IntMapUtils$.MODULE$.join(bin.prefix(), this, bin2.prefix(), bin2)));
                                break block4;
                            }
                            if (!(tuple2._1() instanceof Tip)) break block5;
                            Tip tip = (Tip)tuple2._1();
                            intMap = tuple2._2().updateWith(tip.key(), (S)tip.value(), (Function2<T, S, S>)new Serializable(this, f, tip){
                                public static final long serialVersionUID = 0L;
                                private final Function3 f$1;
                                private final Tip x6$1;

                                public final S apply(S x, S y) {
                                    return (S)this.f$1.apply(BoxesRunTime.boxToInteger(this.x6$1.key()), y, x);
                                }
                                {
                                    void var3_3;
                                    this.f$1 = f$1;
                                    this.x6$1 = var3_3;
                                }
                            });
                            break block4;
                        }
                        if (!(tuple2._2() instanceof Tip)) break block6;
                        Tip tip = (Tip)tuple2._2();
                        intMap = tuple2._1().updateWith(tip.key(), (S)tip.value(), (Function2<T, S, S>)((Object)new Serializable(this, f, tip){
                            public static final long serialVersionUID = 0L;
                            private final Function3 f$1;
                            private final Tip x8$1;

                            public final S apply(T x, S y) {
                                return (S)this.f$1.apply(BoxesRunTime.boxToInteger(this.x8$1.key()), x, y);
                            }
                            {
                                void var3_3;
                                this.f$1 = f$1;
                                this.x8$1 = var3_3;
                            }
                        }));
                        break block4;
                    }
                    if (!((Object)IntMap$Nil$.MODULE$).equals(tuple2._1())) break block7;
                    intMap = tuple2._2();
                    break block4;
                }
                if (!((Object)IntMap$Nil$.MODULE$).equals(tuple2._2())) break block8;
                intMap = tuple2._1();
            }
            return intMap;
        }
        throw new MatchError(tuple2);
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public <S, R> IntMap<R> intersectionWith(IntMap<S> that, Function3<Object, T, S, R> f) {
        void var12_17;
        Tuple2<IntMap, IntMap<S>> tuple2 = new Tuple2<IntMap, IntMap<S>>(this, that);
        if (tuple2._1() instanceof Bin) {
            Bin bin = (Bin)tuple2._1();
            if (tuple2._2() instanceof Bin) {
                Bin bin2 = (Bin)tuple2._2();
                return IntMapUtils$.MODULE$.shorter(bin.mask(), bin2.mask()) ? (IntMapUtils$.MODULE$.hasMatch(bin2.prefix(), bin.prefix(), bin.mask()) ? (IntMapUtils$.MODULE$.zero(bin2.prefix(), bin.mask()) ? bin.left().intersectionWith(bin2, f) : bin.right().intersectionWith(bin2, f)) : IntMap$Nil$.MODULE$) : (bin.mask() == bin2.mask() ? IntMapUtils$.MODULE$.bin(bin.prefix(), bin.mask(), bin.left().intersectionWith(bin2.left(), f), bin.right().intersectionWith(bin2.right(), f)) : (IntMapUtils$.MODULE$.hasMatch(bin.prefix(), bin2.prefix(), bin2.mask()) ? (IntMapUtils$.MODULE$.zero(bin.prefix(), bin2.mask()) ? this.intersectionWith(bin2.left(), f) : this.intersectionWith(bin2.right(), f)) : IntMap$Nil$.MODULE$));
            }
        }
        if (tuple2._1() instanceof Tip) {
            void var7_11;
            Tip tip = (Tip)tuple2._1();
            Option<S> option = tuple2._2().get(tip.key());
            if (None$.MODULE$.equals(option)) {
                IntMap$Nil$ intMap$Nil$ = IntMap$Nil$.MODULE$;
                return var7_11;
            } else {
                if (!(option instanceof Some)) throw new MatchError(option);
                Some some = (Some)option;
                Tip<R> tip2 = new Tip<R>(tip.key(), f.apply(BoxesRunTime.boxToInteger(tip.key()), tip.value(), some.x()));
            }
            return var7_11;
        }
        if (!(tuple2._2() instanceof Tip)) return IntMap$Nil$.MODULE$;
        Tip tip = (Tip)tuple2._2();
        Option<T> option = this.get(tip.key());
        if (None$.MODULE$.equals(option)) {
            IntMap$Nil$ intMap$Nil$ = IntMap$Nil$.MODULE$;
            return var12_17;
        } else {
            if (!(option instanceof Some)) throw new MatchError(option);
            Some some = (Some)option;
            Tip<R> tip3 = new Tip<R>(tip.key(), f.apply(BoxesRunTime.boxToInteger(tip.key()), some.x(), tip.value()));
        }
        return var12_17;
    }

    public <R> IntMap<T> intersection(IntMap<R> that) {
        return this.intersectionWith((IntMap)that, (Function3)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final T apply(int key, T value, R value2) {
                return value;
            }
        }));
    }

    public <S> IntMap<S> $plus$plus(IntMap<S> that) {
        return this.unionWith(that, (Function3<Object, S, S, S>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final S apply(int key, S x, S y) {
                return y;
            }
        }));
    }

    public final int firstKey() {
        while (this_ instanceof Bin) {
            Bin bin = (Bin)this_;
            IntMap this_ = bin.left();
        }
        if (this_ instanceof Tip) {
            Tip tip = (Tip)this_;
            return tip.key();
        }
        if (((Object)IntMap$Nil$.MODULE$).equals(this_)) {
            throw package$.MODULE$.error("Empty set");
        }
        throw new MatchError(this_);
    }

    public final int lastKey() {
        while (this_ instanceof Bin) {
            Bin bin = (Bin)this_;
            IntMap this_ = bin.right();
        }
        if (this_ instanceof Tip) {
            Tip tip = (Tip)this_;
            return tip.key();
        }
        if (((Object)IntMap$Nil$.MODULE$).equals(this_)) {
            throw package$.MODULE$.error("Empty set");
        }
        throw new MatchError(this_);
    }

    public static class Bin<T>
    extends IntMap<T>
    implements Product,
    Serializable {
        private final int prefix;
        private final int mask;
        private final IntMap<T> left;
        private final IntMap<T> right;

        public int prefix() {
            return this.prefix;
        }

        public int mask() {
            return this.mask;
        }

        public IntMap<T> left() {
            return this.left;
        }

        public IntMap<T> right() {
            return this.right;
        }

        public <S> IntMap<S> bin(IntMap<S> left, IntMap<S> right) {
            return this.left() == left && this.right() == right ? this : new Bin<S>(this.prefix(), this.mask(), left, right);
        }

        public <T> Bin<T> copy(int prefix, int mask, IntMap<T> left, IntMap<T> right) {
            return new Bin<T>(prefix, mask, left, right);
        }

        public <T> int copy$default$1() {
            return this.prefix();
        }

        public <T> int copy$default$2() {
            return this.mask();
        }

        public <T> IntMap<T> copy$default$3() {
            return this.left();
        }

        public <T> IntMap<T> copy$default$4() {
            return this.right();
        }

        @Override
        public String productPrefix() {
            return "Bin";
        }

        @Override
        public int productArity() {
            return 4;
        }

        @Override
        public Object productElement(int x$1) {
            Object object;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 3: {
                    object = this.right();
                    break;
                }
                case 2: {
                    object = this.left();
                    break;
                }
                case 1: {
                    object = BoxesRunTime.boxToInteger(this.mask());
                    break;
                }
                case 0: {
                    object = BoxesRunTime.boxToInteger(this.prefix());
                }
            }
            return object;
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        public Bin(int prefix, int mask, IntMap<T> left, IntMap<T> right) {
            this.prefix = prefix;
            this.mask = mask;
            this.left = left;
            this.right = right;
            Product$class.$init$(this);
        }
    }

    public static class Tip<T>
    extends IntMap<T>
    implements Product,
    Serializable {
        private final int key;
        private final T value;

        public int key() {
            return this.key;
        }

        public T value() {
            return this.value;
        }

        public <S> Tip<S> withValue(S s2) {
            return s2 == this.value() ? this : new Tip<S>(this.key(), s2);
        }

        public <T> Tip<T> copy(int key, T value) {
            return new Tip<T>(key, value);
        }

        public <T> int copy$default$1() {
            return this.key();
        }

        public <T> T copy$default$2() {
            return this.value();
        }

        @Override
        public String productPrefix() {
            return "Tip";
        }

        @Override
        public int productArity() {
            return 2;
        }

        @Override
        public Object productElement(int x$1) {
            Object object;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 1: {
                    object = this.value();
                    break;
                }
                case 0: {
                    object = BoxesRunTime.boxToInteger(this.key());
                }
            }
            return object;
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        public Tip(int key, T value) {
            this.key = key;
            this.value = value;
            Product$class.$init$(this);
        }
    }
}

