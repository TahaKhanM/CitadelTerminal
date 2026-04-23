/*
 * Decompiled with CFR 0.152.
 */
package scala.util.matching;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterator;
import scala.collection.BufferedIterator;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Iterator$class;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.IndexedSeq$;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.StringOps;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.RichInt$;
import scala.util.matching.Regex$;
import scala.util.matching.Regex$MatchData$class;
import scala.util.matching.Regex$Replacement$class;
import scala.util.matching.UnanchoredRegex;
import scala.util.matching.UnanchoredRegex$class;

@ScalaSignature(bytes="\u0006\u0001\r\u001de\u0001B\u0001\u0003\u0001%\u0011QAU3hKbT!a\u0001\u0003\u0002\u00115\fGo\u00195j]\u001eT!!\u0002\u0004\u0002\tU$\u0018\u000e\u001c\u0006\u0002\u000f\u0005)1oY1mC\u000e\u00011c\u0001\u0001\u000b\u001dA\u00111\u0002D\u0007\u0002\r%\u0011QB\u0002\u0002\u0007\u0003:L(+\u001a4\u0011\u0005-y\u0011B\u0001\t\u0007\u00051\u0019VM]5bY&T\u0018M\u00197f\u0011!\u0011\u0002A!b\u0001\n\u0003\u0019\u0012a\u00029biR,'O\\\u000b\u0002)A\u0011QcG\u0007\u0002-)\u0011q\u0003G\u0001\u0006e\u0016<W\r\u001f\u0006\u0003\u000beQ\u0011AG\u0001\u0005U\u00064\u0018-\u0003\u0002\u001d-\t9\u0001+\u0019;uKJt\u0007\u0002\u0003\u0010\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u000b\u0002\u0011A\fG\u000f^3s]\u0002B\u0001\u0002\t\u0001\u0003\u0002\u0003\u0006I!I\u0001\u000bOJ|W\u000f\u001d(b[\u0016\u001c\bcA\u0006#I%\u00111E\u0002\u0002\u000byI,\u0007/Z1uK\u0012t\u0004CA\u0013)\u001d\tYa%\u0003\u0002(\r\u00051\u0001K]3eK\u001aL!!\u000b\u0016\u0003\rM#(/\u001b8h\u0015\t9c\u0001\u0003\u0004-\u0001\u0011\u0005!!L\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00079\u0002\u0014\u0007\u0005\u00020\u00015\t!\u0001C\u0003\u0013W\u0001\u0007A\u0003C\u0003!W\u0001\u0007\u0011\u0005C\u0003-\u0001\u0011\u00051\u0007F\u0002/iUBQa\u0006\u001aA\u0002\u0011BQ\u0001\t\u001aA\u0002\u0005BQa\u000e\u0001\u0005\u0002a\n!\"\u001e8baBd\u0017pU3r)\tI\u0004\nE\u0002\fuqJ!a\u000f\u0004\u0003\r=\u0003H/[8o!\riT\t\n\b\u0003}\rs!a\u0010\"\u000e\u0003\u0001S!!\u0011\u0005\u0002\rq\u0012xn\u001c;?\u0013\u00059\u0011B\u0001#\u0007\u0003\u001d\u0001\u0018mY6bO\u0016L!AR$\u0003\t1K7\u000f\u001e\u0006\u0003\t\u001aAQ!\u0013\u001cA\u0002)\u000b\u0011a\u001d\t\u0003\u0017:k\u0011\u0001\u0014\u0006\u0003\u001bf\tA\u0001\\1oO&\u0011q\n\u0014\u0002\r\u0007\"\f'oU3rk\u0016t7-\u001a\u0005\u0006o\u0001!\t!\u0015\u000b\u0003%^\u00032a\u0003\u001eT!\riT\t\u0016\t\u0003\u0017UK!A\u0016\u0004\u0003\t\rC\u0017M\u001d\u0005\u00061B\u0003\r\u0001V\u0001\u0002G\")q\u0007\u0001C\u00015R\u0011\u0011h\u0017\u0005\u00069f\u0003\r!X\u0001\u0002[B\u0019a,a\u0016\u000f\u0005=zv!\u00021\u0003\u0011\u0003\t\u0017!\u0002*fO\u0016D\bCA\u0018c\r\u0015\t!\u0001#\u0001d'\r\u0011'B\u0004\u0005\u0006Y\t$\t!\u001a\u000b\u0002C\u001a9qM\u0019I\u0001\u0004\u0003A'!C'bi\u000eDG)\u0019;b'\t1'\u0002C\u0003kM\u0012\u00051.\u0001\u0004%S:LG\u000f\n\u000b\u0002YB\u00111\"\\\u0005\u0003]\u001a\u0011A!\u00168ji\"9\u0001O\u001ab\u0001\u000e\u0003\t\u0018AB:pkJ\u001cW-F\u0001K\u0011\u001d\u0001cM1A\u0007\u0002M,\u0012\u0001\u001e\t\u0004{U$\u0013B\u0001<H\u0005\r\u0019V-\u001d\u0005\u0006q\u001a4\t!_\u0001\u000bOJ|W\u000f]\"pk:$X#\u0001>\u0011\u0005-Y\u0018B\u0001?\u0007\u0005\rIe\u000e\u001e\u0005\u0006}\u001a4\t!_\u0001\u0006gR\f'\u000f\u001e\u0005\u0007}\u001a4\t!!\u0001\u0015\u0007i\f\u0019\u0001\u0003\u0004\u0002\u0006}\u0004\rA_\u0001\u0002S\"1\u0011\u0011\u00024\u0007\u0002e\f1!\u001a8e\u0011\u001d\tIA\u001aD\u0001\u0003\u001b!2A_A\b\u0011\u001d\t)!a\u0003A\u0002iDq!a\u0005g\t\u0003\t)\"A\u0004nCR\u001c\u0007.\u001a3\u0016\u0003\u0011Bq!!\u0007g\t\u0003\tY\"A\u0003he>,\b\u000fF\u0002%\u0003;Aq!!\u0002\u0002\u0018\u0001\u0007!\u0010C\u0004\u0002\"\u0019$\t!a\t\u0002\u0013M,(m\u001a:pkB\u001cX#\u0001\u001f\t\r\u0005\u001db\r\"\u0001r\u0003\u0019\u0011WMZ8sK\"9\u0011q\u00054\u0005\u0002\u0005-Bc\u0001&\u0002.!9\u0011QAA\u0015\u0001\u0004Q\bBBA\u0019M\u0012\u0005\u0011/A\u0003bMR,'\u000fC\u0004\u00022\u0019$\t!!\u000e\u0015\u0007)\u000b9\u0004C\u0004\u0002\u0006\u0005M\u0002\u0019\u0001>\t\u0015\u0005mb\r#b\u0001\n\u0013\ti$A\u0006oC6,Gk\\%oI\u0016DXCAA !\u0015)\u0013\u0011\t\u0013{\u0013\r\t\u0019E\u000b\u0002\u0004\u001b\u0006\u0004\bBCA$M\"\u0005\t\u0015)\u0003\u0002@\u0005aa.Y7f)>Le\u000eZ3yA!9\u0011\u0011\u00044\u0005\u0002\u0005-Cc\u0001\u0013\u0002N!9\u0011qJA%\u0001\u0004!\u0013AA5e\u0011\u001d\t\u0019F\u001aC!\u0003+\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002I\u00191\u0011\u0011\f2\u0001\u00037\u0012Q!T1uG\"\u001cR!a\u0016\u000b\u0003;\u00022!a\u0018g\u001b\u0005\u0011\u0007\"\u00039\u0002X\t\u0015\r\u0011\"\u0001r\u0011)\t)'a\u0016\u0003\u0002\u0003\u0006IAS\u0001\bg>,(oY3!\u00111\tI'a\u0016\u0003\u0006\u0004%\tAAA6\u0003\u001di\u0017\r^2iKJ,\"!!\u001c\u0011\u0007U\ty'C\u0002\u0002rY\u0011q!T1uG\",'\u000fC\u0006\u0002v\u0005]#\u0011!Q\u0001\n\u00055\u0014\u0001C7bi\u000eDWM\u001d\u0011\t\u0013\u0001\n9F!b\u0001\n\u0003\u0019\bBCA>\u0003/\u0012\t\u0011)A\u0005i\u0006YqM]8va:\u000bW.Z:!\u0011\u001da\u0013q\u000bC\u0001\u0003\u007f\"\u0002\"!!\u0002\u0004\u0006\u0015\u0015q\u0011\t\u0005\u0003?\n9\u0006\u0003\u0004q\u0003{\u0002\rA\u0013\u0005\t\u0003S\ni\b1\u0001\u0002n!1\u0001%! A\u0002QD\u0001B`A,\u0005\u0004%\t!\u001f\u0005\t\u0003\u001b\u000b9\u0006)A\u0005u\u000611\u000f^1si\u0002B\u0011\"!\u0003\u0002X\t\u0007I\u0011A=\t\u0011\u0005M\u0015q\u000bQ\u0001\ni\fA!\u001a8eA!1\u00010a\u0016\u0005\u0002eD1\"!'\u0002X!\u0015\r\u0011\"\u0003\u0002\u001c\u000611\u000f^1siN,\"!!(\u0011\t-\tyJ_\u0005\u0004\u0003C3!!B!se\u0006L\bbCAS\u0003/B\t\u0011)Q\u0005\u0003;\u000bqa\u001d;beR\u001c\b\u0005C\u0006\u0002*\u0006]\u0003R1A\u0005\n\u0005m\u0015\u0001B3oIND1\"!,\u0002X!\u0005\t\u0015)\u0003\u0002\u001e\u0006)QM\u001c3tA!9a0a\u0016\u0005\u0002\u0005EFc\u0001>\u00024\"9\u0011QAAX\u0001\u0004Q\b\u0002CA\u0005\u0003/\"\t!a.\u0015\u0007i\fI\fC\u0004\u0002\u0006\u0005U\u0006\u0019\u0001>\t\u0011\u0005u\u0016q\u000bC\u0001\u0003\u007f\u000bQAZ8sG\u0016,\"!!1\u000e\u0005\u0005]saBAcE\"\u0005\u0011qY\u0001\u0006\u001b\u0006$8\r\u001b\t\u0005\u0003?\nIMB\u0004\u0002Z\tD\t!a3\u0014\u0007\u0005%'\u0002C\u0004-\u0003\u0013$\t!a4\u0015\u0005\u0005\u001d\u0007\u0002CAj\u0003\u0013$\t!!6\u0002\u000fUt\u0017\r\u001d9msR!\u0011q[Ao!\u0011Y\u0011\u0011\u001c\u0013\n\u0007\u0005mgA\u0001\u0003T_6,\u0007b\u0002/\u0002R\u0002\u0007\u0011\u0011Q\u0004\b\u0003C\u0014\u0007\u0012AAr\u0003\u00199%o\\;qgB!\u0011qLAs\r\u001d\t9O\u0019E\u0001\u0003S\u0014aa\u0012:pkB\u001c8cAAs\u0015!9A&!:\u0005\u0002\u00055HCAAr\u0011\u001d9\u0014Q\u001dC\u0001\u0003c$B!a=\u0002vB\u00191B\u000f;\t\u000fq\u000by\u000f1\u0001\u0002\u0002\u001a1\u0011\u0011 2\u0001\u0003w\u0014Q\"T1uG\"LE/\u001a:bi>\u00148\u0003CA|\u0003{\u0014I!!\u0018\u0011\u000b\u0005}(Q\u0001\u0013\u000e\u0005\t\u0005!b\u0001B\u0002\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\t\u001d!\u0011\u0001\u0002\u0011\u0003\n\u001cHO]1di&#XM]1u_J\u0004B!\u0010B\u0006I%\u0019!QB$\u0003\u0011%#XM]1u_JD\u0011\u0002]A|\u0005\u000b\u0007I\u0011A9\t\u0015\u0005\u0015\u0014q\u001fB\u0001B\u0003%!\n\u0003\u0006\u0018\u0003o\u0014)\u0019!C\u0001\u0005+)\u0012A\f\u0005\u000b\u00053\t9P!A!\u0002\u0013q\u0013A\u0002:fO\u0016D\b\u0005C\u0005!\u0003o\u0014)\u0019!C\u0001g\"Q\u00111PA|\u0005\u0003\u0005\u000b\u0011\u0002;\t\u000f1\n9\u0010\"\u0001\u0003\"QA!1\u0005B\u0013\u0005O\u0011I\u0003\u0005\u0003\u0002`\u0005]\bB\u00029\u0003 \u0001\u0007!\n\u0003\u0004\u0018\u0005?\u0001\rA\f\u0005\u0007A\t}\u0001\u0019\u0001;\t\u0017\u0005%\u0014q\u001fb\u0001\n#\u0011\u00171\u000e\u0005\n\u0003k\n9\u0010)A\u0005\u0003[B!B!\r\u0002x\u0002\u0007I\u0011\u0002B\u001a\u0003!qW\r\u001f;TK\u0016tWC\u0001B\u001b!\rY!qG\u0005\u0004\u0005s1!a\u0002\"p_2,\u0017M\u001c\u0005\u000b\u0005{\t9\u00101A\u0005\n\t}\u0012\u0001\u00048fqR\u001cV-\u001a8`I\u0015\fHc\u00017\u0003B!Q!1\tB\u001e\u0003\u0003\u0005\rA!\u000e\u0002\u0007a$\u0013\u0007C\u0005\u0003H\u0005]\b\u0015)\u0003\u00036\u0005Ia.\u001a=u'\u0016,g\u000e\t\u0005\t\u0005\u0017\n9\u0010\"\u0001\u00034\u00059\u0001.Y:OKb$\b\u0002\u0003B(\u0003o$\t!!\u0016\u0002\t9,\u0007\u0010\u001e\u0005\t\u0003'\n9\u0010\"\u0011\u0003TQ\u0011!Q\u000b\t\u0004\u0017\n]\u0013BA\u0015M\u0011\u0019q\u0018q\u001fC\u0001s\"9a0a>\u0005\u0002\tuCc\u0001>\u0003`!9\u0011Q\u0001B.\u0001\u0004Q\bbBA\u0005\u0003o$\t!\u001f\u0005\t\u0003\u0013\t9\u0010\"\u0001\u0003fQ\u0019!Pa\u001a\t\u000f\u0005\u0015!1\ra\u0001u\"1\u00010a>\u0005\u0002eD\u0001B!\u001c\u0002x\u0012\u0005!qN\u0001\n[\u0006$8\r\u001b#bi\u0006,\"A!\u001d\u0011\u000bu\u0012Y!!!\t\u0013\tU\u0014q\u001fC\u0001\u0005\t]\u0014a\u0004:fa2\f7-Z7f]R$\u0015\r^1\u0016\u0005\te$C\u0002B>\u0005\u007f\u0012\tIB\u0004\u0003~\tM\u0004A!\u001f\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\r\u0005}(QAAA!\u0011\tyFa!\u0007\u0015\t\u0015%\r%A\u0002\u0002\t\u00119IA\u0006SKBd\u0017mY3nK:$8c\u0001BB\u0015!1!Na!\u0005\u0002-D\u0001\"!\u001b\u0003\u0004\u001aE\u00111\u000e\u0005\u000b\u0005\u001f\u0013\u0019I1A\u0005\n\tE\u0015AA:c+\t\u0011\u0019\nE\u0002L\u0005+K1Aa&M\u00051\u0019FO]5oO\n+hMZ3s\u0011%\u0011YJa!!\u0002\u0013\u0011\u0019*A\u0002tE\u0002B\u0001Ba(\u0003\u0004\u0012\u0005!\u0011U\u0001\te\u0016\u0004H.Y2fIV\u0011!Q\u000b\u0005\t\u0005K\u0013\u0019\t\"\u0001\u0003(\u00069!/\u001a9mC\u000e,G\u0003BA7\u0005SCqAa+\u0003$\u0002\u0007A%\u0001\u0002sg\"9!q\u00162\u0005\u0002\tE\u0016!B9v_R,Gc\u0001\u0013\u00034\"9!Q\u0017BW\u0001\u0004!\u0013\u0001\u0002;fqRDqA!/c\t\u0003\u0011Y,\u0001\trk>$XMU3qY\u0006\u001cW-\\3oiR\u0019AE!0\t\u000f\tU&q\u0017a\u0001I!I!\u0011\u00192\u0002\u0002\u0013%!1Y\u0001\fe\u0016\fGMU3t_24X\r\u0006\u0002\u0003FB\u00191Ja2\n\u0007\t%GJ\u0001\u0004PE*,7\r\u001e\u0005\u0007o\u0001!\tA!4\u0015\u0007e\u0012y\r\u0003\u0005\u0003R\n-\u0007\u0019\u0001Bj\u0003\u0019!\u0018M]4fiB\u00191B!6\n\u0007\t]gAA\u0002B]fD\u0003Ba3\u0003\\\n\u0005(Q\u001d\t\u0004\u0017\tu\u0017b\u0001Bp\r\tQA-\u001a9sK\u000e\fG/\u001a3\"\u0005\t\r\u0018!U#yiJ\f7\r^5oO\u0002\n\u0007%\\1uG\"\u0004#/Z:vYR\u0004cM]8nA\u0005t\u0017\u0010\u001e5j]\u001e\u0004#-\u001e;!C\u0002\u001a\u0005.\u0019:TKF,XM\\2fA=\u0014\b%T1uG\"\u0004\u0013n\u001d\u0011eKB\u0014XmY1uK\u0012\f#Aa:\u0002\rIr\u0013'\r\u00181\u0011\u001d\u0011Y\u000f\u0001C\t\u0005[\f!B];o\u001b\u0006$8\r[3s)\u0011\u0011)Da<\t\u000fq\u0013I\u000f1\u0001\u0002n!9!1\u001f\u0001\u0005\u0002\tU\u0018!\u00034j]\u0012\fE\u000e\\%o)\u0011\u00119P!?\u0011\u0007y\u000b9\u0010\u0003\u0004q\u0005c\u0004\rA\u0013\u0005\b\u0005{\u0004A\u0011\u0001B\u0000\u000391\u0017N\u001c3BY2l\u0015\r^2i\u0013:$Ba!\u0001\u0004\u0004A!QHa\u0003^\u0011\u0019\u0001(1 a\u0001\u0015\"91q\u0001\u0001\u0005\u0002\r%\u0011a\u00034j]\u00124\u0015N]:u\u0013:$Baa\u0003\u0004\u000eA\u00191B\u000f\u0013\t\rA\u001c)\u00011\u0001K\u0011\u001d\u0019\t\u0002\u0001C\u0001\u0007'\t\u0001CZ5oI\u001aK'o\u001d;NCR\u001c\u0007.\u00138\u0015\t\rU1q\u0003\t\u0004\u0017ij\u0006B\u00029\u0004\u0010\u0001\u0007!\nC\u0004\u0004\u001c\u0001!\ta!\b\u0002\u0019\u0019Lg\u000e\u001a)sK\u001aL\u0007p\u00144\u0015\t\r-1q\u0004\u0005\u0007a\u000ee\u0001\u0019\u0001&\t\u000f\r\r\u0002\u0001\"\u0001\u0004&\u0005\tb-\u001b8e!J,g-\u001b=NCR\u001c\u0007n\u00144\u0015\t\rU1q\u0005\u0005\u0007a\u000e\u0005\u0002\u0019\u0001&\t\u000f\r-\u0002\u0001\"\u0001\u0004.\u0005a!/\u001a9mC\u000e,\u0017\t\u001c7J]R)Aea\f\u00042!9!\u0011[B\u0015\u0001\u0004Q\u0005bBB\u001a\u0007S\u0001\r\u0001J\u0001\fe\u0016\u0004H.Y2f[\u0016tG\u000fC\u0004\u0004,\u0001!\taa\u000e\u0015\u000b\u0011\u001aIda\u000f\t\u000f\tE7Q\u0007a\u0001\u0015\"A1QHB\u001b\u0001\u0004\u0019y$\u0001\u0005sKBd\u0017mY3s!\u0015Y1\u0011I/%\u0013\r\u0019\u0019E\u0002\u0002\n\rVt7\r^5p]FBqaa\u0012\u0001\t\u0003\u0019I%A\u0007sKBd\u0017mY3T_6,\u0017J\u001c\u000b\u0006I\r-3Q\n\u0005\b\u0005#\u001c)\u00051\u0001K\u0011!\u0019id!\u0012A\u0002\r=\u0003CB\u0006\u0004Bu\u001bY\u0001C\u0004\u0004T\u0001!\ta!\u0016\u0002\u001dI,\u0007\u000f\\1dK\u001aK'o\u001d;J]R)Aea\u0016\u0004Z!9!\u0011[B)\u0001\u0004Q\u0005bBB\u001a\u0007#\u0002\r\u0001\n\u0005\b\u0007;\u0002A\u0011AB0\u0003\u0015\u0019\b\u000f\\5u)\u0011\u0019\tga\u0019\u0011\t-\ty\n\n\u0005\b\u0007K\u001aY\u00061\u0001K\u0003\u001d!xn\u00159mSRDqa!\u001b\u0001\t\u0003\u0019Y'\u0001\u0006v]\u0006t7\r[8sK\u0012,\"a!\u001c\u0011\u0007=\u001ay'C\u0002\u0004r\t\u0011q\"\u00168b]\u000eDwN]3e%\u0016<W\r\u001f\u0005\b\u0007k\u0002A\u0011\u0001B\u000b\u0003!\tgn\u00195pe\u0016$\u0007BB\f\u0001\t\u0003\t)\u0002C\u0004\u0002T\u0001!\t%!\u0016)\u000f\u0001\u0019iha!\u0004\u0006B\u00191ba \n\u0007\r\u0005eA\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKzA!=|kk=C\u0010s\u0018")
public class Regex
implements Serializable {
    public static final long serialVersionUID = -2094783597747625537L;
    private final Pattern pattern;
    public final Seq<String> scala$util$matching$Regex$$groupNames;

    public static String quoteReplacement(String string2) {
        return Regex$.MODULE$.quoteReplacement(string2);
    }

    public static String quote(String string2) {
        return Regex$.MODULE$.quote(string2);
    }

    public Pattern pattern() {
        return this.pattern;
    }

    public Option<List<String>> unapplySeq(CharSequence s2) {
        None$ none$;
        if (s2 == null) {
            none$ = None$.MODULE$;
        } else {
            Option option;
            Matcher m = this.pattern().matcher(s2);
            if (this.runMatcher(m)) {
                Predef$ predef$ = Predef$.MODULE$;
                option = new Some(RichInt$.MODULE$.to$extension0(1, m.groupCount()).toList().map(new Serializable(this, m){
                    public static final long serialVersionUID = 0L;
                    private final Matcher m$1;

                    public final String apply(int x$1) {
                        return this.m$1.group(x$1);
                    }
                    {
                        this.m$1 = m$1;
                    }
                }, List$.MODULE$.canBuildFrom()));
            } else {
                option = None$.MODULE$;
            }
            none$ = option;
        }
        return none$;
    }

    public Option<List<Object>> unapplySeq(char c) {
        Option option;
        Matcher m = this.pattern().matcher(((Object)BoxesRunTime.boxToCharacter(c)).toString());
        if (this.runMatcher(m)) {
            if (m.groupCount() > 0) {
                String string2 = m.group(1);
                Predef$ predef$ = Predef$.MODULE$;
                Some<List<Object>> some = new Some<List<Object>>(new StringOps(string2).toList());
                option = some;
            } else {
                option = new Some<Nil$>(Nil$.MODULE$);
            }
        } else {
            option = None$.MODULE$;
        }
        return option;
    }

    public Option<List<String>> unapplySeq(Match m) {
        Option option;
        if (m == null || m.matched() == null) {
            option = None$.MODULE$;
        } else {
            Pattern pattern = m.matcher().pattern();
            Pattern pattern2 = this.pattern();
            if (!(pattern != null ? !pattern.equals(pattern2) : pattern2 != null)) {
                Predef$ predef$ = Predef$.MODULE$;
                option = new Some(RichInt$.MODULE$.to$extension0(1, m.groupCount()).toList().map(new Serializable(this, m){
                    public static final long serialVersionUID = 0L;
                    private final Match m$2;

                    public final String apply(int i) {
                        return this.m$2.group(i);
                    }
                    {
                        this.m$2 = m$2;
                    }
                }, List$.MODULE$.canBuildFrom()));
            } else {
                option = this.unapplySeq(m.matched());
            }
        }
        return option;
    }

    public Option<List<String>> unapplySeq(Object target) {
        Option option;
        if (target instanceof CharSequence) {
            Option option2;
            CharSequence charSequence = (CharSequence)target;
            Matcher m = this.pattern().matcher(charSequence);
            if (this.runMatcher(m)) {
                Predef$ predef$ = Predef$.MODULE$;
                option2 = new Some(RichInt$.MODULE$.to$extension0(1, m.groupCount()).toList().map(new Serializable(this, m){
                    public static final long serialVersionUID = 0L;
                    private final Matcher m$3;

                    public final String apply(int x$1) {
                        return this.m$3.group(x$1);
                    }
                    {
                        this.m$3 = m$3;
                    }
                }, List$.MODULE$.canBuildFrom()));
            } else {
                option2 = None$.MODULE$;
            }
            option = option2;
        } else if (target instanceof Match) {
            Match match = (Match)target;
            option = this.unapplySeq(match.matched());
        } else {
            option = None$.MODULE$;
        }
        return option;
    }

    public boolean runMatcher(Matcher m) {
        return m.matches();
    }

    public MatchIterator findAllIn(CharSequence source) {
        return new MatchIterator(source, this, this.scala$util$matching$Regex$$groupNames);
    }

    public Iterator<Match> findAllMatchIn(CharSequence source) {
        MatchIterator matchIterator = this.findAllIn(source);
        return new Iterator<Match>(this, matchIterator){
            private final MatchIterator matchIterator$1;

            public Iterator<Match> seq() {
                return Iterator$class.seq(this);
            }

            public boolean isEmpty() {
                return Iterator$class.isEmpty(this);
            }

            public boolean isTraversableAgain() {
                return Iterator$class.isTraversableAgain(this);
            }

            public boolean hasDefiniteSize() {
                return Iterator$class.hasDefiniteSize(this);
            }

            public Iterator<Match> take(int n) {
                return Iterator$class.take(this, n);
            }

            public Iterator<Match> drop(int n) {
                return Iterator$class.drop(this, n);
            }

            public Iterator<Match> slice(int from2, int until2) {
                return Iterator$class.slice(this, from2, until2);
            }

            public <B> Iterator<B> map(Function1<Match, B> f) {
                return Iterator$class.map(this, f);
            }

            public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
                return Iterator$class.$plus$plus(this, that);
            }

            public <B> Iterator<B> flatMap(Function1<Match, GenTraversableOnce<B>> f) {
                return Iterator$class.flatMap(this, f);
            }

            public Iterator<Match> filter(Function1<Match, Object> p) {
                return Iterator$class.filter(this, p);
            }

            public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<Match, B, Object> p) {
                return Iterator$class.corresponds(this, that, p);
            }

            public Iterator<Match> withFilter(Function1<Match, Object> p) {
                return Iterator$class.withFilter(this, p);
            }

            public Iterator<Match> filterNot(Function1<Match, Object> p) {
                return Iterator$class.filterNot(this, p);
            }

            public <B> Iterator<B> collect(PartialFunction<Match, B> pf) {
                return Iterator$class.collect(this, pf);
            }

            public <B> Iterator<B> scanLeft(B z, Function2<B, Match, B> op) {
                return Iterator$class.scanLeft(this, z, op);
            }

            public <B> Iterator<B> scanRight(B z, Function2<Match, B, B> op) {
                return Iterator$class.scanRight(this, z, op);
            }

            public Iterator<Match> takeWhile(Function1<Match, Object> p) {
                return Iterator$class.takeWhile(this, p);
            }

            public Tuple2<Iterator<Match>, Iterator<Match>> partition(Function1<Match, Object> p) {
                return Iterator$class.partition(this, p);
            }

            public Tuple2<Iterator<Match>, Iterator<Match>> span(Function1<Match, Object> p) {
                return Iterator$class.span(this, p);
            }

            public Iterator<Match> dropWhile(Function1<Match, Object> p) {
                return Iterator$class.dropWhile(this, p);
            }

            public <B> Iterator<Tuple2<Match, B>> zip(Iterator<B> that) {
                return Iterator$class.zip(this, that);
            }

            public <A1> Iterator<A1> padTo(int len, A1 elem) {
                return Iterator$class.padTo(this, len, elem);
            }

            public Iterator<Tuple2<Match, Object>> zipWithIndex() {
                return Iterator$class.zipWithIndex(this);
            }

            public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
                return Iterator$class.zipAll(this, that, thisElem, thatElem);
            }

            public <U> void foreach(Function1<Match, U> f) {
                Iterator$class.foreach(this, f);
            }

            public boolean forall(Function1<Match, Object> p) {
                return Iterator$class.forall(this, p);
            }

            public boolean exists(Function1<Match, Object> p) {
                return Iterator$class.exists(this, p);
            }

            public boolean contains(Object elem) {
                return Iterator$class.contains(this, elem);
            }

            public Option<Match> find(Function1<Match, Object> p) {
                return Iterator$class.find(this, p);
            }

            public int indexWhere(Function1<Match, Object> p) {
                return Iterator$class.indexWhere(this, p);
            }

            public <B> int indexOf(B elem) {
                return Iterator$class.indexOf(this, elem);
            }

            public BufferedIterator<Match> buffered() {
                return Iterator$class.buffered(this);
            }

            public <B> Iterator.GroupedIterator<B> grouped(int size2) {
                return Iterator$class.grouped(this, size2);
            }

            public <B> Iterator.GroupedIterator<B> sliding(int size2, int step) {
                return Iterator$class.sliding(this, size2, step);
            }

            public int length() {
                return Iterator$class.length(this);
            }

            public Tuple2<Iterator<Match>, Iterator<Match>> duplicate() {
                return Iterator$class.duplicate(this);
            }

            public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
                return Iterator$class.patch(this, from2, patchElems, replaced);
            }

            public <B> void copyToArray(Object xs, int start, int len) {
                Iterator$class.copyToArray(this, xs, start, len);
            }

            public boolean sameElements(Iterator<?> that) {
                return Iterator$class.sameElements(this, that);
            }

            public Traversable<Match> toTraversable() {
                return Iterator$class.toTraversable(this);
            }

            public Iterator<Match> toIterator() {
                return Iterator$class.toIterator(this);
            }

            public Stream<Match> toStream() {
                return Iterator$class.toStream(this);
            }

            public String toString() {
                return Iterator$class.toString(this);
            }

            public <B> int sliding$default$2() {
                return Iterator$class.sliding$default$2(this);
            }

            public List<Match> reversed() {
                return TraversableOnce$class.reversed(this);
            }

            public int size() {
                return TraversableOnce$class.size(this);
            }

            public boolean nonEmpty() {
                return TraversableOnce$class.nonEmpty(this);
            }

            public int count(Function1<Match, Object> p) {
                return TraversableOnce$class.count(this, p);
            }

            public <B> Option<B> collectFirst(PartialFunction<Match, B> pf) {
                return TraversableOnce$class.collectFirst(this, pf);
            }

            public <B> B $div$colon(B z, Function2<B, Match, B> op) {
                return (B)TraversableOnce$class.$div$colon(this, z, op);
            }

            public <B> B $colon$bslash(B z, Function2<Match, B, B> op) {
                return (B)TraversableOnce$class.$colon$bslash(this, z, op);
            }

            public <B> B foldLeft(B z, Function2<B, Match, B> op) {
                return (B)TraversableOnce$class.foldLeft(this, z, op);
            }

            public <B> B foldRight(B z, Function2<Match, B, B> op) {
                return (B)TraversableOnce$class.foldRight(this, z, op);
            }

            public <B> B reduceLeft(Function2<B, Match, B> op) {
                return (B)TraversableOnce$class.reduceLeft(this, op);
            }

            public <B> B reduceRight(Function2<Match, B, B> op) {
                return (B)TraversableOnce$class.reduceRight(this, op);
            }

            public <B> Option<B> reduceLeftOption(Function2<B, Match, B> op) {
                return TraversableOnce$class.reduceLeftOption(this, op);
            }

            public <B> Option<B> reduceRightOption(Function2<Match, B, B> op) {
                return TraversableOnce$class.reduceRightOption(this, op);
            }

            public <A1> A1 reduce(Function2<A1, A1, A1> op) {
                return (A1)TraversableOnce$class.reduce(this, op);
            }

            public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
                return TraversableOnce$class.reduceOption(this, op);
            }

            public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
                return (A1)TraversableOnce$class.fold(this, z, op);
            }

            public <B> B aggregate(Function0<B> z, Function2<B, Match, B> seqop, Function2<B, B, B> combop) {
                return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
            }

            public <B> B sum(Numeric<B> num) {
                return (B)TraversableOnce$class.sum(this, num);
            }

            public <B> B product(Numeric<B> num) {
                return (B)TraversableOnce$class.product(this, num);
            }

            public Object min(Ordering cmp) {
                return TraversableOnce$class.min(this, cmp);
            }

            public Object max(Ordering cmp) {
                return TraversableOnce$class.max(this, cmp);
            }

            public Object maxBy(Function1 f, Ordering cmp) {
                return TraversableOnce$class.maxBy(this, f, cmp);
            }

            public Object minBy(Function1 f, Ordering cmp) {
                return TraversableOnce$class.minBy(this, f, cmp);
            }

            public <B> void copyToBuffer(Buffer<B> dest) {
                TraversableOnce$class.copyToBuffer(this, dest);
            }

            public <B> void copyToArray(Object xs, int start) {
                TraversableOnce$class.copyToArray(this, xs, start);
            }

            public <B> void copyToArray(Object xs) {
                TraversableOnce$class.copyToArray(this, xs);
            }

            public <B> Object toArray(ClassTag<B> evidence$1) {
                return TraversableOnce$class.toArray(this, evidence$1);
            }

            public List<Match> toList() {
                return TraversableOnce$class.toList(this);
            }

            public Iterable<Match> toIterable() {
                return TraversableOnce$class.toIterable(this);
            }

            public Seq<Match> toSeq() {
                return TraversableOnce$class.toSeq(this);
            }

            public IndexedSeq<Match> toIndexedSeq() {
                return TraversableOnce$class.toIndexedSeq(this);
            }

            public <B> Buffer<B> toBuffer() {
                return TraversableOnce$class.toBuffer(this);
            }

            public <B> Set<B> toSet() {
                return TraversableOnce$class.toSet(this);
            }

            public Vector<Match> toVector() {
                return TraversableOnce$class.toVector(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, Match, Col> cbf) {
                return (Col)TraversableOnce$class.to(this, cbf);
            }

            public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Match, Tuple2<T, U>> ev) {
                return TraversableOnce$class.toMap(this, ev);
            }

            public String mkString(String start, String sep, String end) {
                return TraversableOnce$class.mkString(this, start, sep, end);
            }

            public String mkString(String sep) {
                return TraversableOnce$class.mkString(this, sep);
            }

            public String mkString() {
                return TraversableOnce$class.mkString(this);
            }

            public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
                return TraversableOnce$class.addString(this, b, start, sep, end);
            }

            public StringBuilder addString(StringBuilder b, String sep) {
                return TraversableOnce$class.addString(this, b, sep);
            }

            public StringBuilder addString(StringBuilder b) {
                return TraversableOnce$class.addString(this, b);
            }

            public boolean hasNext() {
                return this.matchIterator$1.hasNext();
            }

            public Match next() {
                this.matchIterator$1.next();
                return new Match(this.matchIterator$1.source(), this.matchIterator$1.matcher(), this.matchIterator$1.groupNames()).force();
            }
            {
                this.matchIterator$1 = matchIterator$1;
                TraversableOnce$class.$init$(this);
                Iterator$class.$init$(this);
            }
        };
    }

    public Option<String> findFirstIn(CharSequence source) {
        Matcher m = this.pattern().matcher(source);
        return m.find() ? new Some<String>(m.group()) : None$.MODULE$;
    }

    public Option<Match> findFirstMatchIn(CharSequence source) {
        Matcher m = this.pattern().matcher(source);
        return m.find() ? new Some<Match>(new Match(source, m, this.scala$util$matching$Regex$$groupNames)) : None$.MODULE$;
    }

    public Option<String> findPrefixOf(CharSequence source) {
        Matcher m = this.pattern().matcher(source);
        return m.lookingAt() ? new Some<String>(m.group()) : None$.MODULE$;
    }

    public Option<Match> findPrefixMatchOf(CharSequence source) {
        Matcher m = this.pattern().matcher(source);
        return m.lookingAt() ? new Some<Match>(new Match(source, m, this.scala$util$matching$Regex$$groupNames)) : None$.MODULE$;
    }

    public String replaceAllIn(CharSequence target, String replacement) {
        Matcher m = this.pattern().matcher(target);
        return m.replaceAll(replacement);
    }

    public String replaceAllIn(CharSequence target, Function1<Match, String> replacer) {
        AbstractIterator<Match> it = new MatchIterator(target, this, this.scala$util$matching$Regex$$groupNames).replacementData();
        it.foreach(new Serializable(this, replacer, it){
            public static final long serialVersionUID = 0L;
            private final Function1 replacer$1;
            private final AbstractIterator it$1;

            public final Matcher apply(Match md) {
                return ((Replacement)((Object)this.it$1)).replace((String)this.replacer$1.apply(md));
            }
            {
                this.replacer$1 = replacer$1;
                this.it$1 = it$1;
            }
        });
        return ((Replacement)((Object)it)).replaced();
    }

    public String replaceSomeIn(CharSequence target, Function1<Match, Option<String>> replacer) {
        AbstractIterator<Match> it = new MatchIterator(target, this, this.scala$util$matching$Regex$$groupNames).replacementData();
        it.foreach(new Serializable(this, replacer, it){
            public static final long serialVersionUID = 0L;
            private final Function1 replacer$2;
            public final AbstractIterator it$2;

            public final void apply(Match matchdata) {
                Option option = (Option)this.replacer$2.apply(matchdata);
                if (!option.isEmpty()) {
                    String string2 = (String)option.get();
                    ((Replacement)((Object)this.it$2)).replace(string2);
                }
            }
            {
                this.replacer$2 = replacer$2;
                this.it$2 = it$2;
            }
        });
        return ((Replacement)((Object)it)).replaced();
    }

    public String replaceFirstIn(CharSequence target, String replacement) {
        Matcher m = this.pattern().matcher(target);
        return m.replaceFirst(replacement);
    }

    public String[] split(CharSequence toSplit) {
        return this.pattern().split(toSplit);
    }

    public UnanchoredRegex unanchored() {
        return new UnanchoredRegex(this){
            private final /* synthetic */ Regex $outer;

            public boolean runMatcher(Matcher m) {
                return UnanchoredRegex$class.runMatcher(this, m);
            }

            public UnanchoredRegex unanchored() {
                return UnanchoredRegex$class.unanchored(this);
            }

            public Regex anchored() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super($outer.pattern(), $outer.scala$util$matching$Regex$$groupNames);
                UnanchoredRegex$class.$init$(this);
            }
        };
    }

    public Regex anchored() {
        return this;
    }

    public String regex() {
        return this.pattern().pattern();
    }

    public String toString() {
        return this.regex();
    }

    public Regex(Pattern pattern, Seq<String> groupNames) {
        this.pattern = pattern;
        this.scala$util$matching$Regex$$groupNames = groupNames;
    }

    public Regex(String regex, Seq<String> groupNames) {
        this(Pattern.compile(regex), groupNames);
    }

    public static class Match
    implements MatchData {
        private final CharSequence source;
        private final Matcher matcher;
        private final Seq<String> groupNames;
        private final int start;
        private final int end;
        private int[] starts;
        private int[] ends;
        private final Map<String, Object> scala$util$matching$Regex$MatchData$$nameToIndex;
        private volatile byte bitmap$0;

        private int[] starts$lzycompute() {
            Match match = this;
            synchronized (match) {
                if ((byte)(this.bitmap$0 & 1) == 0) {
                    Predef$ predef$ = Predef$.MODULE$;
                    this.starts = (int[])((TraversableOnce)RichInt$.MODULE$.to$extension0(0, this.groupCount()).map(new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        public final /* synthetic */ Match $outer;

                        public final int apply(int x$1) {
                            return this.$outer.matcher().start(x$1);
                        }

                        public int apply$mcII$sp(int x$1) {
                            return this.$outer.matcher().start(x$1);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }, IndexedSeq$.MODULE$.canBuildFrom())).toArray(ClassTag$.MODULE$.Int());
                    this.bitmap$0 = (byte)(this.bitmap$0 | 1);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.starts;
            }
        }

        private int[] ends$lzycompute() {
            Match match = this;
            synchronized (match) {
                if ((byte)(this.bitmap$0 & 2) == 0) {
                    Predef$ predef$ = Predef$.MODULE$;
                    this.ends = (int[])((TraversableOnce)RichInt$.MODULE$.to$extension0(0, this.groupCount()).map(new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        public final /* synthetic */ Match $outer;

                        public final int apply(int x$1) {
                            return this.$outer.matcher().end(x$1);
                        }

                        public int apply$mcII$sp(int x$1) {
                            return this.$outer.matcher().end(x$1);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }, IndexedSeq$.MODULE$.canBuildFrom())).toArray(ClassTag$.MODULE$.Int());
                    this.bitmap$0 = (byte)(this.bitmap$0 | 2);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ends;
            }
        }

        private Map scala$util$matching$Regex$MatchData$$nameToIndex$lzycompute() {
            Match match = this;
            synchronized (match) {
                if ((byte)(this.bitmap$0 & 4) == 0) {
                    this.scala$util$matching$Regex$MatchData$$nameToIndex = Regex$MatchData$class.scala$util$matching$Regex$MatchData$$nameToIndex(this);
                    this.bitmap$0 = (byte)(this.bitmap$0 | 4);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.scala$util$matching$Regex$MatchData$$nameToIndex;
            }
        }

        @Override
        public Map<String, Object> scala$util$matching$Regex$MatchData$$nameToIndex() {
            return (byte)(this.bitmap$0 & 4) == 0 ? this.scala$util$matching$Regex$MatchData$$nameToIndex$lzycompute() : this.scala$util$matching$Regex$MatchData$$nameToIndex;
        }

        @Override
        public String matched() {
            return Regex$MatchData$class.matched(this);
        }

        @Override
        public String group(int i) {
            return Regex$MatchData$class.group((MatchData)this, i);
        }

        @Override
        public List<String> subgroups() {
            return Regex$MatchData$class.subgroups(this);
        }

        @Override
        public CharSequence before() {
            return Regex$MatchData$class.before(this);
        }

        @Override
        public CharSequence before(int i) {
            return Regex$MatchData$class.before(this, i);
        }

        @Override
        public CharSequence after() {
            return Regex$MatchData$class.after(this);
        }

        @Override
        public CharSequence after(int i) {
            return Regex$MatchData$class.after(this, i);
        }

        @Override
        public String group(String id) {
            return Regex$MatchData$class.group((MatchData)this, id);
        }

        @Override
        public String toString() {
            return Regex$MatchData$class.toString(this);
        }

        @Override
        public CharSequence source() {
            return this.source;
        }

        public Matcher matcher() {
            return this.matcher;
        }

        @Override
        public Seq<String> groupNames() {
            return this.groupNames;
        }

        @Override
        public int start() {
            return this.start;
        }

        @Override
        public int end() {
            return this.end;
        }

        @Override
        public int groupCount() {
            return this.matcher().groupCount();
        }

        private int[] starts() {
            return (byte)(this.bitmap$0 & 1) == 0 ? this.starts$lzycompute() : this.starts;
        }

        private int[] ends() {
            return (byte)(this.bitmap$0 & 2) == 0 ? this.ends$lzycompute() : this.ends;
        }

        @Override
        public int start(int i) {
            return this.starts()[i];
        }

        @Override
        public int end(int i) {
            return this.ends()[i];
        }

        public Match force() {
            this.starts();
            this.ends();
            return this;
        }

        public Match(CharSequence source, Matcher matcher, Seq<String> groupNames) {
            this.source = source;
            this.matcher = matcher;
            this.groupNames = groupNames;
            Regex$MatchData$class.$init$(this);
            this.start = matcher.start();
            this.end = matcher.end();
        }
    }

    public static interface MatchData {
        public CharSequence source();

        public Seq<String> groupNames();

        public int groupCount();

        public int start();

        public int start(int var1);

        public int end();

        public int end(int var1);

        public String matched();

        public String group(int var1);

        public List<String> subgroups();

        public CharSequence before();

        public CharSequence before(int var1);

        public CharSequence after();

        public CharSequence after(int var1);

        public Map<String, Object> scala$util$matching$Regex$MatchData$$nameToIndex();

        public String group(String var1);

        public String toString();
    }

    public static interface Replacement {
        public void scala$util$matching$Regex$Replacement$_setter_$scala$util$matching$Regex$Replacement$$sb_$eq(StringBuffer var1);

        public Matcher matcher();

        public StringBuffer scala$util$matching$Regex$Replacement$$sb();

        public String replaced();

        public Matcher replace(String var1);
    }

    public static class MatchIterator
    extends AbstractIterator<String>
    implements MatchData {
        private final CharSequence source;
        private final Regex regex;
        private final Seq<String> groupNames;
        private final Matcher matcher;
        private boolean nextSeen;
        private final Map<String, Object> scala$util$matching$Regex$MatchData$$nameToIndex;
        private volatile boolean bitmap$0;

        private Map scala$util$matching$Regex$MatchData$$nameToIndex$lzycompute() {
            MatchIterator matchIterator = this;
            synchronized (matchIterator) {
                if (!this.bitmap$0) {
                    this.scala$util$matching$Regex$MatchData$$nameToIndex = Regex$MatchData$class.scala$util$matching$Regex$MatchData$$nameToIndex(this);
                    this.bitmap$0 = true;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.scala$util$matching$Regex$MatchData$$nameToIndex;
            }
        }

        @Override
        public Map<String, Object> scala$util$matching$Regex$MatchData$$nameToIndex() {
            return this.bitmap$0 ? this.scala$util$matching$Regex$MatchData$$nameToIndex : this.scala$util$matching$Regex$MatchData$$nameToIndex$lzycompute();
        }

        @Override
        public String matched() {
            return Regex$MatchData$class.matched(this);
        }

        @Override
        public String group(int i) {
            return Regex$MatchData$class.group((MatchData)this, i);
        }

        @Override
        public List<String> subgroups() {
            return Regex$MatchData$class.subgroups(this);
        }

        @Override
        public CharSequence before() {
            return Regex$MatchData$class.before(this);
        }

        @Override
        public CharSequence before(int i) {
            return Regex$MatchData$class.before(this, i);
        }

        @Override
        public CharSequence after() {
            return Regex$MatchData$class.after(this);
        }

        @Override
        public CharSequence after(int i) {
            return Regex$MatchData$class.after(this, i);
        }

        @Override
        public String group(String id) {
            return Regex$MatchData$class.group((MatchData)this, id);
        }

        @Override
        public CharSequence source() {
            return this.source;
        }

        public Regex regex() {
            return this.regex;
        }

        @Override
        public Seq<String> groupNames() {
            return this.groupNames;
        }

        public Matcher matcher() {
            return this.matcher;
        }

        private boolean nextSeen() {
            return this.nextSeen;
        }

        private void nextSeen_$eq(boolean x$1) {
            this.nextSeen = x$1;
        }

        @Override
        public boolean hasNext() {
            if (!this.nextSeen()) {
                this.nextSeen_$eq(this.matcher().find());
            }
            return this.nextSeen();
        }

        @Override
        public String next() {
            if (this.hasNext()) {
                this.nextSeen_$eq(false);
                return this.matcher().group();
            }
            throw new NoSuchElementException();
        }

        @Override
        public String toString() {
            return Iterator$class.toString(this);
        }

        @Override
        public int start() {
            return this.matcher().start();
        }

        @Override
        public int start(int i) {
            return this.matcher().start(i);
        }

        @Override
        public int end() {
            return this.matcher().end();
        }

        @Override
        public int end(int i) {
            return this.matcher().end(i);
        }

        @Override
        public int groupCount() {
            return this.matcher().groupCount();
        }

        public Iterator<Match> matchData() {
            return new AbstractIterator<Match>(this){
                private final /* synthetic */ MatchIterator $outer;

                public boolean hasNext() {
                    return this.$outer.hasNext();
                }

                public Match next() {
                    this.$outer.next();
                    return new Match(this.$outer.source(), this.$outer.matcher(), this.$outer.groupNames()).force();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
        }

        public AbstractIterator<Match> replacementData() {
            return new Replacement(this){
                private final /* synthetic */ MatchIterator $outer;
                private final StringBuffer scala$util$matching$Regex$Replacement$$sb;

                public StringBuffer scala$util$matching$Regex$Replacement$$sb() {
                    return this.scala$util$matching$Regex$Replacement$$sb;
                }

                public void scala$util$matching$Regex$Replacement$_setter_$scala$util$matching$Regex$Replacement$$sb_$eq(StringBuffer x$1) {
                    this.scala$util$matching$Regex$Replacement$$sb = x$1;
                }

                public String replaced() {
                    return Regex$Replacement$class.replaced(this);
                }

                public Matcher replace(String rs) {
                    return Regex$Replacement$class.replace(this, rs);
                }

                public Matcher matcher() {
                    return this.$outer.matcher();
                }

                public boolean hasNext() {
                    return this.$outer.hasNext();
                }

                public Match next() {
                    this.$outer.next();
                    return new Match(this.$outer.source(), this.matcher(), this.$outer.groupNames()).force();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    Regex$Replacement$class.$init$(this);
                }
            };
        }

        public MatchIterator(CharSequence source, Regex regex, Seq<String> groupNames) {
            this.source = source;
            this.regex = regex;
            this.groupNames = groupNames;
            Regex$MatchData$class.$init$(this);
            this.matcher = regex.pattern().matcher(source);
            this.nextSeen = false;
        }
    }
}

