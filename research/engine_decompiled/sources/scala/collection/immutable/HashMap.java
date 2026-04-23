/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import scala.Array$;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterable;
import scala.collection.AbstractTraversable;
import scala.collection.CustomParallelizable;
import scala.collection.CustomParallelizable$class;
import scala.collection.GenMap;
import scala.collection.GenTraversable;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.Seq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.AbstractMap;
import scala.collection.immutable.HashMap$;
import scala.collection.immutable.HashMap$EmptyHashMap$;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List$;
import scala.collection.immutable.ListMap;
import scala.collection.immutable.ListMap$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.immutable.Seq$;
import scala.collection.immutable.TrieIterator;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParHashMap;
import scala.collection.parallel.immutable.ParHashMap$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;
import scala.sys.package$;

@ScalaSignature(bytes="\u0006\u0001\u0019}a\u0001B\u0001\u0003\u0001%\u0011q\u0001S1tQ6\u000b\u0007O\u0003\u0002\u0004\t\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003\u000b\u0019\t!bY8mY\u0016\u001cG/[8o\u0015\u00059\u0011!B:dC2\f7\u0001A\u000b\u0004\u0015Ea2C\u0002\u0001\f=\u0005*\u0003\u0006\u0005\u0003\r\u001b=YR\"\u0001\u0002\n\u00059\u0011!aC!cgR\u0014\u0018m\u0019;NCB\u0004\"\u0001E\t\r\u0001\u0011)!\u0003\u0001b\u0001'\t\t\u0011)\u0005\u0002\u00151A\u0011QCF\u0007\u0002\r%\u0011qC\u0002\u0002\b\u001d>$\b.\u001b8h!\t)\u0012$\u0003\u0002\u001b\r\t\u0019\u0011I\\=\u0011\u0005AaBAB\u000f\u0001\t\u000b\u00071CA\u0001C!\u0011aqdD\u000e\n\u0005\u0001\u0012!aA'baB)ABI\b\u001cI%\u00111E\u0001\u0002\b\u001b\u0006\u0004H*[6f!\u0011a\u0001aD\u000e\u0011\u0005U1\u0013BA\u0014\u0007\u00051\u0019VM]5bY&T\u0018M\u00197f!\u0011I#\u0006L\u0018\u000e\u0003\u0011I!a\u000b\u0003\u0003)\r+8\u000f^8n!\u0006\u0014\u0018\r\u001c7fY&T\u0018M\u00197f!\u0011)RfD\u000e\n\u000592!A\u0002+va2,'\u0007\u0005\u00031i=YR\"A\u0019\u000b\u0005\r\u0011$BA\u001a\u0005\u0003!\u0001\u0018M]1mY\u0016d\u0017BA\u001b2\u0005)\u0001\u0016M\u001d%bg\"l\u0015\r\u001d\u0005\u0006o\u0001!\t\u0001O\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0011BQA\u000f\u0001\u0005Bm\nAa]5{KV\tA\b\u0005\u0002\u0016{%\u0011aH\u0002\u0002\u0004\u0013:$\b\"\u0002!\u0001\t\u0003\n\u0015!B3naRLX#\u0001\u0013\t\u000b\r\u0003A\u0011\u0001#\u0002\u0011%$XM]1u_J,\u0012!\u0012\t\u0004S\u0019c\u0013BA$\u0005\u0005!IE/\u001a:bi>\u0014\b\"B%\u0001\t\u0003R\u0015a\u00024pe\u0016\f7\r[\u000b\u0003\u0017V#\"\u0001T(\u0011\u0005Ui\u0015B\u0001(\u0007\u0005\u0011)f.\u001b;\t\u000bAC\u0005\u0019A)\u0002\u0003\u0019\u0004B!\u0006*-)&\u00111K\u0002\u0002\n\rVt7\r^5p]F\u0002\"\u0001E+\u0005\u000bYC%\u0019A\n\u0003\u0003UCQ\u0001\u0017\u0001\u0005\u0002e\u000b1aZ3u)\tQV\fE\u0002\u00167nI!\u0001\u0018\u0004\u0003\r=\u0003H/[8o\u0011\u0015qv\u000b1\u0001\u0010\u0003\rYW-\u001f\u0005\u0006A\u0002!\t%Y\u0001\bkB$\u0017\r^3e+\t\u0011W\rF\u0002dQ&\u0004B\u0001\u0004\u0001\u0010IB\u0011\u0001#\u001a\u0003\u0006M~\u0013\ra\u001a\u0002\u0003\u0005F\n\"a\u0007\r\t\u000by{\u0006\u0019A\b\t\u000b)|\u0006\u0019\u00013\u0002\u000bY\fG.^3\t\u000b1\u0004A\u0011I7\u0002\u000b\u0011\u0002H.^:\u0016\u00059\fHCA8s!\u0011a\u0001a\u00049\u0011\u0005A\tH!\u00024l\u0005\u00049\u0007\"B:l\u0001\u0004!\u0018AA6w!\u0011)Rf\u00049\t\u000b1\u0004A\u0011\t<\u0016\u0005]TH#\u0002=|}\u0006\u0005\u0001\u0003\u0002\u0007\u0001\u001fe\u0004\"\u0001\u0005>\u0005\u000b\u0019,(\u0019A4\t\u000bq,\b\u0019A?\u0002\u000b\u0015dW-\\\u0019\u0011\tUis\"\u001f\u0005\u0006\u007fV\u0004\r!`\u0001\u0006K2,WN\r\u0005\b\u0003\u0007)\b\u0019AA\u0003\u0003\u0015)G.Z7t!\u0011)\u0012qA?\n\u0007\u0005%aA\u0001\u0006=e\u0016\u0004X-\u0019;fIzBq!!\u0004\u0001\t\u0003\ty!\u0001\u0004%[&tWo\u001d\u000b\u0004I\u0005E\u0001B\u00020\u0002\f\u0001\u0007q\u0002C\u0004\u0002\u0016\u0001!\t%a\u0006\u0002\r\u0019LG\u000e^3s)\r!\u0013\u0011\u0004\u0005\t\u00037\t\u0019\u00021\u0001\u0002\u001e\u0005\t\u0001\u000fE\u0003\u0016%2\ny\u0002E\u0002\u0016\u0003CI1!a\t\u0007\u0005\u001d\u0011un\u001c7fC:Dq!a\n\u0001\t\u0003\nI#A\u0005gS2$XM\u001d(piR\u0019A%a\u000b\t\u0011\u0005m\u0011Q\u0005a\u0001\u0003;Aq!a\f\u0001\t#\t\t$A\u0004gS2$XM\u001d\u0019\u0015\u0017\u0011\n\u0019$!\u000e\u0002:\u0005u\u0012Q\f\u0005\t\u00037\ti\u00031\u0001\u0002\u001e!A\u0011qGA\u0017\u0001\u0004\ty\"\u0001\u0004oK\u001e\fG/\u001a\u0005\b\u0003w\ti\u00031\u0001=\u0003\u0015aWM^3m\u0011!\ty$!\fA\u0002\u0005\u0005\u0013A\u00022vM\u001a,'\u000fE\u0003\u0016\u0003\u0007\n9%C\u0002\u0002F\u0019\u0011Q!\u0011:sCf\u0004R\u0001\u0004\u0001\u0010\u0003\u0013R3aGA&W\t\ti\u0005\u0005\u0003\u0002P\u0005eSBAA)\u0015\u0011\t\u0019&!\u0016\u0002\u0013Ut7\r[3dW\u0016$'bAA,\r\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005m\u0013\u0011\u000b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007bBA0\u0003[\u0001\r\u0001P\u0001\b_\u001a47/\u001a;1\u0011\u001d\t\u0019\u0007\u0001C\t\u0003K\nA\"\u001a7f[\"\u000b7\u000f[\"pI\u0016$2\u0001PA4\u0011\u0019q\u0016\u0011\ra\u0001\u001f!9\u00111\u000e\u0001\u0005\u0016\u00055\u0014aB5naJ|g/\u001a\u000b\u0004y\u0005=\u0004bBA9\u0003S\u0002\r\u0001P\u0001\u0006Q\u000e|G-\u001a\u0005\t\u0003k\u0002A\u0011\u0001\u0003\u0002x\u0005Y1m\\7qkR,\u0007*Y:i)\ra\u0014\u0011\u0010\u0005\u0007=\u0006M\u0004\u0019A\b\t\u0011\u0005u\u0004\u0001\"\u0001\u0005\u0003\u007f\nAaZ3uaQ9!,!!\u0002\u0004\u0006\u001d\u0005B\u00020\u0002|\u0001\u0007q\u0002C\u0004\u0002\u0006\u0006m\u0004\u0019\u0001\u001f\u0002\t!\f7\u000f\u001b\u0005\b\u0003w\tY\b1\u0001=\u0011!\tY\t\u0001C\u0001\t\u00055\u0015\u0001C;qI\u0006$X\r\u001a\u0019\u0016\t\u0005=\u0015Q\u0013\u000b\u000f\u0003#\u000b9*!'\u0002\u001c\u0006u\u0015qTAR!\u0015a\u0001aDAJ!\r\u0001\u0012Q\u0013\u0003\u0007M\u0006%%\u0019A4\t\ry\u000bI\t1\u0001\u0010\u0011\u001d\t))!#A\u0002qBq!a\u000f\u0002\n\u0002\u0007A\bC\u0004k\u0003\u0013\u0003\r!a%\t\u000fM\fI\t1\u0001\u0002\"B)Q#L\b\u0002\u0014\"A\u0011QUAE\u0001\u0004\t9+\u0001\u0004nKJ<WM\u001d\t\b\u0003S\u000b\u0019nDAJ\u001d\ra\u00111V\u0004\b\u0003[\u0013\u0001\u0012AAX\u0003\u001dA\u0015m\u001d5NCB\u00042\u0001DAY\r\u0019\t!\u0001#\u0001\u00024N9\u0011\u0011WA[\u0003\u0007,\u0003CBA\\\u0003{\u000b\t-\u0004\u0002\u0002:*\u0019\u00111\u0018\u0003\u0002\u000f\u001d,g.\u001a:jG&!\u0011qXA]\u0005MIU.\\;uC\ndW-T1q\r\u0006\u001cGo\u001c:z!\ta\u0001\u0001\u0005\u0003\u0002F\u0006-g\u0002BA\\\u0003\u000fLA!!3\u0002:\u0006i!)\u001b;Pa\u0016\u0014\u0018\r^5p]NL1APAg\u0015\u0011\tI-!/\t\u000f]\n\t\f\"\u0001\u0002RR\u0011\u0011q\u0016\u0004\n\u0003+\f\t,!\u0001\u0005\u0003/\u0014a!T3sO\u0016\u0014XCBAm\u0003W\fyo\u0005\u0003\u0002T\u0006m\u0007cA\u000b\u0002^&\u0019\u0011q\u001c\u0004\u0003\r\u0005s\u0017PU3g\u0011\u001d9\u00141\u001bC\u0001\u0003G$\"!!:\u0011\u0011\u0005\u001d\u00181[Au\u0003[l!!!-\u0011\u0007A\tY\u000f\u0002\u0004\u0013\u0003'\u0014\ra\u0005\t\u0004!\u0005=HAB\u000f\u0002T\n\u00071\u0003\u0003\u0005\u0002t\u0006Mg\u0011AA{\u0003\u0015\t\u0007\u000f\u001d7z)\u0019\t90!?\u0002~B1Q#LAu\u0003[D\u0001\"a?\u0002r\u0002\u0007\u0011q_\u0001\u0004WZ\f\u0004\u0002CA\u0000\u0003c\u0004\r!a>\u0002\u0007-4(\u0007\u0003\u0005\u0003\u0004\u0005Mg\u0011\u0001B\u0003\u0003\u0019IgN^3siV\u0011\u0011Q]\u0003\b\u0005\u0013\t\t\f\u0002B\u0006\u00055iUM]4f\rVt7\r^5p]V1!Q\u0002B\f\u0005;\u0001\u0012\"\u0006B\b\u0005'\u0011\u0019Ba\u0005\n\u0007\tEaAA\u0005Gk:\u001cG/[8oeA1Q#\fB\u000b\u00057\u00012\u0001\u0005B\f\t\u001d\u0011IBa\u0002C\u0002M\u0011!!Q\u0019\u0011\u0007A\u0011i\u0002\u0002\u0004g\u0005\u000f\u0011\ra\u0005\u0005\t\u0005C\t\t\f\"\u0003\u0003$\u0005QA.\u001b4u\u001b\u0016\u0014x-\u001a:\u0016\r\t\u0015\"1\u0006B\u0018)\u0011\u00119C!\r\u0011\u0011\u0005\u001d\u00181\u001bB\u0015\u0005[\u00012\u0001\u0005B\u0016\t\u001d\u0011IBa\bC\u0002M\u00012\u0001\u0005B\u0018\t\u00191'q\u0004b\u0001'!A!1\u0007B\u0010\u0001\u0004\u0011)$\u0001\u0004nKJ<WM\u001a\t\t\u0003O\u00149A!\u000b\u0003.!I!\u0011HAYA\u0003%!1H\u0001\u000eI\u00164\u0017-\u001e7u\u001b\u0016\u0014x-\u001a:\u0011\r\u0005\u001d\u00181\u001b\r\u0019\u0011%\u0011y$!-!\n\u0013\u0011\t%A\u0006mS\u001a$X*\u001a:hKJ\u0004TC\u0002B\"\u0005\u0013\u0012i\u0005\u0006\u0003\u0003F\t=\u0003\u0003CAt\u0003'\u00149Ea\u0013\u0011\u0007A\u0011I\u0005B\u0004\u0003\u001a\tu\"\u0019A\n\u0011\u0007A\u0011i\u0005\u0002\u0004g\u0005{\u0011\ra\u0005\u0005\t\u0005g\u0011i\u00041\u0001\u0003RAA\u0011q\u001dB\u0004\u0005\u000f\u0012Y\u0005\u0003\u0005\u0003V\u0005EF1\u0001B,\u00031\u0019\u0017M\u001c\"vS2$gI]8n+\u0019\u0011IFa\u001c\u0003tU\u0011!1\f\t\u000b\u0003o\u0013iF!\u0019\u0003l\tU\u0014\u0002\u0002B0\u0003s\u0013AbQ1o\u0005VLG\u000e\u001a$s_6\u0004B!a:\u0003d%!!Q\rB4\u0005\u0011\u0019u\u000e\u001c7\n\t\t%\u0014\u0011\u0018\u0002\u000e\u000f\u0016tW*\u00199GC\u000e$xN]=\u0011\rUi#Q\u000eB9!\r\u0001\"q\u000e\u0003\u0007%\tM#\u0019A\n\u0011\u0007A\u0011\u0019\b\u0002\u0004\u001e\u0005'\u0012\ra\u0005\t\u0007\u0019\u0001\u0011iG!\u001d\t\u000f\u0001\u000b\t\f\"\u0001\u0003zU1!1\u0010BA\u0005\u000b+\"A! \u0011\r1\u0001!q\u0010BB!\r\u0001\"\u0011\u0011\u0003\u0007%\t]$\u0019A\n\u0011\u0007A\u0011)\t\u0002\u0004\u001e\u0005o\u0012\raE\u0004\t\u0005\u0013\u000b\t\f#\u0003\u0003\f\u0006aQ)\u001c9us\"\u000b7\u000f['baB!\u0011q\u001dBG\r!\u0011y)!-\t\n\tE%\u0001D#naRL\b*Y:i\u001b\u0006\u00048\u0003\u0002BG\u0005'\u0003B\u0001\u0004\u0001\u0019)!9qG!$\u0005\u0002\t]EC\u0001BF\u0011)\u0011YJ!$\u0002\u0002\u0013%!QT\u0001\fe\u0016\fGMU3t_24X\r\u0006\u0002\u0003 B!!\u0011\u0015BV\u001b\t\u0011\u0019K\u0003\u0003\u0003&\n\u001d\u0016\u0001\u00027b]\u001eT!A!+\u0002\t)\fg/Y\u0005\u0005\u0005[\u0013\u0019K\u0001\u0004PE*,7\r\u001e\u0005\t\u0005c\u000b\t\f\"\u0003\u00034\u0006yQ.Y6f\u0011\u0006\u001c\b\u000e\u0016:jK6\u000b\u0007/\u0006\u0004\u00036\u000e55\u0011\u0013\u000b\u000f\u0005o\u001b\u0019ja&\u0004\u001e\u000e\u000561UBS!!\t9O!/\u0004\f\u000e=ea\u0002B^\u0003c\u0003!Q\u0018\u0002\f\u0011\u0006\u001c\b\u000e\u0016:jK6\u000b\u0007/\u0006\u0004\u0003@\n\u0015'1Z\n\u0005\u0005s\u0013\t\r\u0005\u0004\r\u0001\t\r'q\u0019\t\u0004!\t\u0015GA\u0002\n\u0003:\n\u00071C\u000b\u0003\u0003J\u0006-\u0003c\u0001\t\u0003L\u00129QD!/\u0005\u0006\u0004\u0019\u0002\u0002\u0004Bh\u0005s\u0013)\u0019!C\u0001\t\tE\u0017A\u00022ji6\f\u0007/\u0006\u0002\u0003TB!\u0011q\u001dBk\u0013\rq\u00141\u001a\u0005\f\u00053\u0014IL!A!\u0002\u0013\u0011\u0019.A\u0004cSRl\u0017\r\u001d\u0011\t\u0019\u0005\r!\u0011\u0018BC\u0002\u0013\u0005AA!8\u0016\u0005\t}\u0007#B\u000b\u0002D\t\u0005\u0007b\u0003Br\u0005s\u0013\t\u0011)A\u0005\u0005?\fa!\u001a7f[N\u0004\u0003\u0002\u0004Bt\u0005s\u0013)\u0019!C\u0001\t\tE\u0017!B:ju\u0016\u0004\u0004b\u0003Bv\u0005s\u0013\t\u0011)A\u0005\u0005'\faa]5{KB\u0002\u0003bB\u001c\u0003:\u0012\u0005!q\u001e\u000b\t\u0005c\u0014\u0019P!>\u0003xBA\u0011q\u001dB]\u0005\u0007\u0014I\r\u0003\u0005\u0003P\n5\b\u0019\u0001Bj\u0011!\t\u0019A!<A\u0002\t}\u0007\u0002\u0003Bt\u0005[\u0004\rAa5\t\u000fi\u0012I\f\"\u0011\u0003R\"A\u0011Q\u0010B]\t\u0003\u0012i\u0010\u0006\u0005\u0003\u0000\u000e\u000511AB\u0003!\u0011)2L!3\t\u000fy\u0013Y\u00101\u0001\u0003D\"A\u0011Q\u0011B~\u0001\u0004\u0011\u0019\u000e\u0003\u0005\u0002<\tm\b\u0019\u0001Bj\u0011%\tYI!/\u0005B\u0011\u0019I!\u0006\u0003\u0004\f\rEACDB\u0007\u0007+\u00199b!\u0007\u0004\u001c\ru1\u0011\u0005\t\u0007\u0019\u0001\u0011\u0019ma\u0004\u0011\u0007A\u0019\t\u0002B\u0004g\u0007\u000f\u0011\raa\u0005\u0012\u0007\t%\u0007\u0004C\u0004_\u0007\u000f\u0001\rAa1\t\u0011\u0005\u00155q\u0001a\u0001\u0005'D\u0001\"a\u000f\u0004\b\u0001\u0007!1\u001b\u0005\bU\u000e\u001d\u0001\u0019AB\b\u0011\u001d\u00198q\u0001a\u0001\u0007?\u0001b!F\u0017\u0003D\u000e=\u0001\u0002CAS\u0007\u000f\u0001\raa\t\u0011\u0011\u0005\u001d\u00181\u001bBb\u0007\u001fA\u0001ba\n\u0003:\u0012\u00053\u0011F\u0001\te\u0016lwN^3eaQA11FB\u0017\u0007_\u0019\t\u0004\u0005\u0004\r\u0001\t\r'\u0011\u001a\u0005\b=\u000e\u0015\u0002\u0019\u0001Bb\u0011!\t)i!\nA\u0002\tM\u0007\u0002CA\u001e\u0007K\u0001\rAa5\t\u0011\u0005=\"\u0011\u0018C)\u0007k!Bba\u000b\u00048\ru2qHB!\u0007\u0007B\u0001\"a\u0007\u00044\u0001\u00071\u0011\b\t\u0007+I\u001bY$a\b\u0011\rUi#1\u0019Be\u0011!\t9da\rA\u0002\u0005}\u0001\u0002CA\u001e\u0007g\u0001\rAa5\t\u0011\u0005}21\u0007a\u0001\u0005?D\u0001\"a\u0018\u00044\u0001\u0007!1\u001b\u0005\b\u0007\neF\u0011IB$+\t\u0019I\u0005\u0005\u0003*\r\u000em\u0002bB%\u0003:\u0012\u00053QJ\u000b\u0005\u0007\u001f\u001a9\u0006F\u0002M\u0007#Bq\u0001UB&\u0001\u0004\u0019\u0019\u0006\u0005\u0004\u0016%\u000em2Q\u000b\t\u0004!\r]CA\u0002,\u0004L\t\u00071\u0003\u0003\u0005\u0004\\\teF\u0011BB/\u0003\u0015\u0001xn](g)\u0015a4qLB2\u0011!\u0019\tg!\u0017A\u0002\tM\u0017!\u00018\t\u0011\r\u00154\u0011\fa\u0001\u0005'\f!AY7\t\u0011\r%$\u0011\u0018C!\u0007W\nQa\u001d9mSR,\"a!\u001c\u0011\u000b1\u0019yga\u000b\n\u0007\rE$AA\u0002TKFD\u0001b!\u001e\u0003:\u0012E3qO\u0001\u0007[\u0016\u0014x-\u001a\u0019\u0016\t\re4q\u0010\u000b\t\u0007w\u001a\ti!\"\u0004\bB1A\u0002\u0001Bb\u0007{\u00022\u0001EB@\t\u001d171\u000fb\u0001\u0007'A\u0001ba!\u0004t\u0001\u000711P\u0001\u0005i\"\fG\u000f\u0003\u0005\u0002<\rM\u0004\u0019\u0001Bj\u0011!\t)ka\u001dA\u0002\r%\u0005\u0003CAt\u0003'\u0014\u0019m! \u0011\u0007A\u0019i\t\u0002\u0004\u0013\u0005_\u0013\ra\u0005\t\u0004!\rEEAB\u000f\u00030\n\u00071\u0003\u0003\u0005\u0004\u0016\n=\u0006\u0019\u0001Bj\u0003\u0015A\u0017m\u001d51\u0011!\u0019IJa,A\u0002\rm\u0015!B3mK6\u0004\u0004C\u0002\u0007\u0001\u0007\u0017\u001by\t\u0003\u0005\u0004 \n=\u0006\u0019\u0001Bj\u0003\u0015A\u0017m\u001d52\u0011\u001da(q\u0016a\u0001\u00077C\u0001\"a\u000f\u00030\u0002\u0007!1\u001b\u0005\bu\t=\u0006\u0019\u0001Bj\r\u001d\u0019I+!-\u0001\u0007W\u0013\u0001\u0002S1tQ6\u000b\u0007/M\u000b\u0007\u0007[\u001b\u0019la.\u0014\t\r\u001d6q\u0016\t\u0007\u0019\u0001\u0019\tl!.\u0011\u0007A\u0019\u0019\f\u0002\u0004\u0013\u0007O\u0013\ra\u0005\t\u0004!\r]FaB\u000f\u0004(\u0012\u0015\ra\u0005\u0005\f=\u000e\u001d&Q1A\u0005\u0002\u0011\u0019Y,\u0006\u0002\u00042\"Y1qXBT\u0005\u0003\u0005\u000b\u0011BBY\u0003\u0011YW-\u001f\u0011\t\u0019\u0005\u00155q\u0015BC\u0002\u0013\u0005AA!5\t\u0017\r\u00157q\u0015B\u0001B\u0003%!1[\u0001\u0006Q\u0006\u001c\b\u000e\t\u0005\fU\u000e\u001d&Q1A\u0005\u0002\u0011\u0019I-\u0006\u0002\u0004L*\"1QWA&\u0011-\u0019yma*\u0003\u0002\u0003\u0006Iaa3\u0002\rY\fG.^3!\u0011-\u00198q\u0015BA\u0002\u0013\u0005Aaa5\u0016\u0005\rU\u0007CB\u000b.\u0007c\u001bY\r\u0003\u0007\u0004Z\u000e\u001d&\u00111A\u0005\u0002\u0011\u0019Y.\u0001\u0004lm~#S-\u001d\u000b\u0004\u0019\u000eu\u0007BCBp\u0007/\f\t\u00111\u0001\u0004V\u0006\u0019\u0001\u0010J\u0019\t\u0017\r\r8q\u0015B\u0001B\u0003&1Q[\u0001\u0004WZ\u0004\u0003bB\u001c\u0004(\u0012\u00051q\u001d\u000b\u000b\u0007S\u001cYo!<\u0004p\u000eE\b\u0003CAt\u0007O\u001b\tl!.\t\u000fy\u001b)\u000f1\u0001\u00042\"A\u0011QQBs\u0001\u0004\u0011\u0019\u000eC\u0004k\u0007K\u0004\raa3\t\u000fM\u001c)\u000f1\u0001\u0004V\"1!ha*\u0005BmB\u0011ba>\u0004(\u0012\u0005Aaa/\u0002\r\u001d,GoS3z\u0011%\u0019Ypa*\u0005\u0002\u0011\u0011\t.A\u0004hKRD\u0015m\u001d5\t\u0013\r}8q\u0015C\u0001\t\u0011\u0005\u0011AD2p[B,H/\u001a%bg\"4uN\u001d\u000b\u0004y\u0011\r\u0001\u0002\u0003C\u0003\u0007{\u0004\ra!-\u0002\u0003-D\u0001\"! \u0004(\u0012\u0005C\u0011\u0002\u000b\t\t\u0017!i\u0001b\u0004\u0005\u0012A!QcWB[\u0011\u001dqFq\u0001a\u0001\u0007cC\u0001\"!\"\u0005\b\u0001\u0007!1\u001b\u0005\t\u0003w!9\u00011\u0001\u0003T\"I\u00111RBT\t\u0003\"AQC\u000b\u0005\t/!i\u0002\u0006\b\u0005\u001a\u0011\u0005B1\u0005C\u0013\tO!I\u0003\"\f\u0011\r1\u00011\u0011\u0017C\u000e!\r\u0001BQ\u0004\u0003\bM\u0012M!\u0019\u0001C\u0010#\r\u0019)\f\u0007\u0005\b=\u0012M\u0001\u0019ABY\u0011!\t)\tb\u0005A\u0002\tM\u0007\u0002CA\u001e\t'\u0001\rAa5\t\u000f)$\u0019\u00021\u0001\u0005\u001c!91\u000fb\u0005A\u0002\u0011-\u0002CB\u000b.\u0007c#Y\u0002\u0003\u0005\u0002&\u0012M\u0001\u0019\u0001C\u0018!!\t9/a5\u00042\u0012m\u0001\u0002CB\u0014\u0007O#\t\u0005b\r\u0015\u0011\r=FQ\u0007C\u001c\tsAqA\u0018C\u0019\u0001\u0004\u0019\t\f\u0003\u0005\u0002\u0006\u0012E\u0002\u0019\u0001Bj\u0011!\tY\u0004\"\rA\u0002\tM\u0007\u0002CA\u0018\u0007O#\t\u0006\"\u0010\u0015\u0019\r=Fq\bC#\t\u000f\"I\u0005b\u0014\t\u0011\u0005mA1\ba\u0001\t\u0003\u0002b!\u0006*\u0005D\u0005}\u0001CB\u000b.\u0007c\u001b)\f\u0003\u0005\u00028\u0011m\u0002\u0019AA\u0010\u0011!\tY\u0004b\u000fA\u0002\tM\u0007\u0002CA \tw\u0001\r\u0001b\u0013\u0011\u000bU\t\u0019\u0005\"\u0014\u0011\r1\u00011\u0011WBf\u0011!\ty\u0006b\u000fA\u0002\tM\u0007bB\"\u0004(\u0012\u0005C1K\u000b\u0003\t+\u0002B!\u000b$\u0005D!9\u0011ja*\u0005B\u0011eS\u0003\u0002C.\tG\"2\u0001\u0014C/\u0011\u001d\u0001Fq\u000ba\u0001\t?\u0002b!\u0006*\u0005D\u0011\u0005\u0004c\u0001\t\u0005d\u00111a\u000bb\u0016C\u0002MA!\u0002b\u001a\u0004(\u0012\u0005\u0011\u0011\u0017C5\u0003))gn];sKB\u000b\u0017N]\u000b\u0003\t\u0007B\u0001b!\u001e\u0004(\u0012ECQN\u000b\u0005\t_\")\b\u0006\u0005\u0005r\u0011]D\u0011\u0010C>!\u0019a\u0001a!-\u0005tA\u0019\u0001\u0003\"\u001e\u0005\u000f\u0019$YG1\u0001\u0005 !A11\u0011C6\u0001\u0004!\t\b\u0003\u0005\u0002<\u0011-\u0004\u0019\u0001Bj\u0011!\t)\u000bb\u001bA\u0002\u0011u\u0004\u0003CAt\u0003'\u001c\t\fb\u001d\u0007\u0011\u0011\u0005\u0015\u0011\u0017\u0001\u0005\t\u0007\u0013\u0011\u0003S1tQ6\u000b\u0007oQ8mY&\u001c\u0018n\u001c82+\u0019!)\tb#\u0005\u0012N!Aq\u0010CD!\u0019a\u0001\u0001\"#\u0005\u000eB\u0019\u0001\u0003b#\u0005\rI!yH1\u0001\u0014U\u0011!y)a\u0013\u0011\u0007A!\t\nB\u0004\u001e\t\u007f\")\u0019A\n\t\u0019\u0005\u0015Eq\u0010BC\u0002\u0013\u0005AA!5\t\u0017\r\u0015Gq\u0010B\u0001B\u0003%!1\u001b\u0005\f\t3#yH!b\u0001\n\u0003!Y*A\u0002lmN,\"\u0001\"(\u0011\u000f1!y\n\"#\u0005\u000e&\u0019A\u0011\u0015\u0002\u0003\u000f1K7\u000f^'ba\"YAQ\u0015C@\u0005\u0003\u0005\u000b\u0011\u0002CO\u0003\u0011Ygo\u001d\u0011\t\u000f]\"y\b\"\u0001\u0005*R1A1\u0016CW\t_\u0003\u0002\"a:\u0005\u0000\u0011%Eq\u0012\u0005\t\u0003\u000b#9\u000b1\u0001\u0003T\"AA\u0011\u0014CT\u0001\u0004!i\n\u0003\u0004;\t\u007f\"\te\u000f\u0005\t\u0003{\"y\b\"\u0011\u00056RAAq\u0017C]\tw#i\f\u0005\u0003\u00167\u0012=\u0005b\u00020\u00054\u0002\u0007A\u0011\u0012\u0005\t\u0003\u000b#\u0019\f1\u0001\u0003T\"A\u00111\bCZ\u0001\u0004\u0011\u0019\u000eC\u0005\u0002\f\u0012}D\u0011\t\u0003\u0005BV!A1\u0019Ce)9!)\r\"4\u0005P\u0012EG1\u001bCk\t3\u0004b\u0001\u0004\u0001\u0005\n\u0012\u001d\u0007c\u0001\t\u0005J\u00129a\rb0C\u0002\u0011-\u0017c\u0001CH1!9a\fb0A\u0002\u0011%\u0005\u0002CAC\t\u007f\u0003\rAa5\t\u0011\u0005mBq\u0018a\u0001\u0005'DqA\u001bC`\u0001\u0004!9\rC\u0004t\t\u007f\u0003\r\u0001b6\u0011\rUiC\u0011\u0012Cd\u0011!\t)\u000bb0A\u0002\u0011m\u0007\u0003CAt\u0003'$I\tb2\t\u0011\r\u001dBq\u0010C!\t?$\u0002\u0002\"9\u0005d\u0012\u0015Hq\u001d\t\u0007\u0019\u0001!I\tb$\t\u000fy#i\u000e1\u0001\u0005\n\"A\u0011Q\u0011Co\u0001\u0004\u0011\u0019\u000e\u0003\u0005\u0002<\u0011u\u0007\u0019\u0001Bj\u0011!\ty\u0003b \u0005R\u0011-H\u0003\u0004Cq\t[$\u0019\u0010\">\u0005x\u0012m\b\u0002CA\u000e\tS\u0004\r\u0001b<\u0011\rU\u0011F\u0011_A\u0010!\u0019)R\u0006\"#\u0005\u0010\"A\u0011q\u0007Cu\u0001\u0004\ty\u0002\u0003\u0005\u0002<\u0011%\b\u0019\u0001Bj\u0011!\ty\u0004\";A\u0002\u0011e\b#B\u000b\u0002D\u0011\u001d\u0005\u0002CA0\tS\u0004\rAa5\t\u000f\r#y\b\"\u0011\u0005\u0000V\u0011Q\u0011\u0001\t\u0005S\u0019#\t\u0010C\u0004J\t\u007f\"\t%\"\u0002\u0016\t\u0015\u001dQq\u0002\u000b\u0004\u0019\u0016%\u0001b\u0002)\u0006\u0004\u0001\u0007Q1\u0002\t\u0007+I#\t0\"\u0004\u0011\u0007A)y\u0001\u0002\u0004W\u000b\u0007\u0011\ra\u0005\u0005\t\u0007S\"y\b\"\u0011\u0006\u0014U\u0011QQ\u0003\t\u0006\u0019\r=D\u0011\u001d\u0005\t\u0007k\"y\b\"\u0015\u0006\u001aU!Q1DC\u0011)!)i\"b\t\u0006&\u0015\u001d\u0002C\u0002\u0007\u0001\t\u0013+y\u0002E\u0002\u0011\u000bC!qAZC\f\u0005\u0004!Y\r\u0003\u0005\u0004\u0004\u0016]\u0001\u0019AC\u000f\u0011!\tY$b\u0006A\u0002\tM\u0007\u0002CAS\u000b/\u0001\r!\"\u000b\u0011\u0011\u0005\u001d\u00181\u001bCE\u000b?A\u0001\"\"\f\u00022\u0012%QqF\u0001\u000bEV4g-\u001a:TSj,G\u0003\u0002Bj\u000bcAqAOC\u0016\u0001\u0004\u0011\u0019\u000e\u000b\u0003\u0006,\u0015U\u0002cA\u000b\u00068%\u0019Q\u0011\b\u0004\u0003\r%tG.\u001b8f\u0011!)i$!-\u0005\n\u0015}\u0012a\u00038vY2$v.R7qif,b!\"\u0011\u0006H\u0015-C\u0003BC\"\u000b\u001b\u0002b\u0001\u0004\u0001\u0006F\u0015%\u0003c\u0001\t\u0006H\u00111!#b\u000fC\u0002M\u00012\u0001EC&\t\u0019iR1\bb\u0001'!AQqJC\u001e\u0001\u0004)\u0019%A\u0001nQ\u0011)Y$\"\u000e\t\u0011\u0015U\u0013\u0011\u0017C\u0005\u000b/\n\u0001b[3fa\nKGo\u001d\u000b\u0007\u0005',I&b\u0017\t\u0011\t=W1\u000ba\u0001\u0005'D\u0001\"\"\u0018\u0006T\u0001\u0007!1[\u0001\u0005W\u0016,\u0007OB\u0004\u0006b\u0005EF!b\u0019\u0003%M+'/[1mSj\fG/[8o!J|\u00070_\u000b\u0007\u000bK*\t(\"\u001e\u0014\u000b\u0015}\u00131\\\u0013\t\u0017\u0015%Tq\fBA\u0002\u0013%Q1N\u0001\u0005_JLw-\u0006\u0002\u0006nA1A\u0002AC8\u000bg\u00022\u0001EC9\t\u0019\u0011Rq\fb\u0001'A\u0019\u0001#\"\u001e\u0005\ru)yF1\u0001\u0014\u0011-)I(b\u0018\u0003\u0002\u0004%I!b\u001f\u0002\u0011=\u0014\u0018nZ0%KF$2\u0001TC?\u0011)\u0019y.b\u001e\u0002\u0002\u0003\u0007QQ\u000e\u0005\f\u000b\u0003+yF!A!B\u0013)i'A\u0003pe&<\u0007\u0005\u000b\u0003\u0006\u0000\u0015\u0015\u0005cA\u000b\u0006\b&\u0019Q\u0011\u0012\u0004\u0003\u0013Q\u0014\u0018M\\:jK:$\bbB\u001c\u0006`\u0011\u0005QQ\u0012\u000b\u0005\u000b\u001f+\t\n\u0005\u0005\u0002h\u0016}SqNC:\u0011!)I'b#A\u0002\u00155\u0004\u0002CCK\u000b?\"I!b&\u0002\u0017]\u0014\u0018\u000e^3PE*,7\r\u001e\u000b\u0004\u0019\u0016e\u0005\u0002CCN\u000b'\u0003\r!\"(\u0002\u0007=,H\u000f\u0005\u0003\u0006 \u0016\u0015VBACQ\u0015\u0011)\u0019Ka*\u0002\u0005%|\u0017\u0002BCT\u000bC\u0013!c\u00142kK\u000e$x*\u001e;qkR\u001cFO]3b[\"AQ1VC0\t\u0013)i+\u0001\u0006sK\u0006$wJ\u00196fGR$2\u0001TCX\u0011!)\t,\"+A\u0002\u0015M\u0016AA5o!\u0011)y*\".\n\t\u0015]V\u0011\u0015\u0002\u0012\u001f\nTWm\u0019;J]B,Ho\u0015;sK\u0006l\u0007\u0002\u0003BN\u000b?\"I!b/\u0015\u0005\u0005m\u0007fBC0\u000b\u007fSWQ\u0019\t\u0004+\u0015\u0005\u0017bACb\r\t\u00012+\u001a:jC24VM]:j_:,\u0016\n\u0012\u0010\u0002\u0005!Q!1TAY\u0003\u0003%IA!(\t\u000f\r\u001d\u0002\u0001\"\u0005\u0006LR9A%\"4\u0006P\u0016E\u0007B\u00020\u0006J\u0002\u0007q\u0002C\u0004\u0002\u0006\u0016%\u0007\u0019\u0001\u001f\t\u000f\u0005mR\u0011\u001aa\u0001y!9QQ\u001b\u0001\u0005\u0012\u0015m\u0016\u0001D<sSR,'+\u001a9mC\u000e,\u0007bBB5\u0001\u0011\u0005Q\u0011\\\u000b\u0003\u000b7\u0004B\u0001DB8I!9Qq\u001c\u0001\u0005\u0002\u0015\u0005\u0018AB7fe\u001e,G-\u0006\u0003\u0006d\u0016-H\u0003BCs\u000bc$B!b:\u0006nB)A\u0002A\b\u0006jB\u0019\u0001#b;\u0005\r\u0019,iN1\u0001h\u0011!\u0011\u0019$\"8A\u0002\u0015=\bcBAU\u0005\u000fyQ\u0011\u001e\u0005\t\u0007\u0007+i\u000e1\u0001\u0006h\"91Q\u000f\u0001\u0005\u0012\u0015UX\u0003BC|\u000b{$\u0002\"\"?\u0006\u0000\u001a\u0005a1\u0001\t\u0006\u0019\u0001yQ1 \t\u0004!\u0015uHA\u00024\u0006t\n\u0007q\r\u0003\u0005\u0004\u0004\u0016M\b\u0019AC}\u0011\u001d\tY$b=A\u0002qB\u0001\"!*\u0006t\u0002\u0007aQ\u0001\t\b\u0003S\u000b\u0019nDC~\u0011\u001d1I\u0001\u0001C!\r\u0017\t1\u0001]1s+\u0005y\u0003f\u0002\u0001\u0007\u0010\u0019Ua\u0011\u0004\t\u0004+\u0019E\u0011b\u0001D\n\r\t)B-\u001a9sK\u000e\fG/\u001a3J]\",'/\u001b;b]\u000e,\u0017E\u0001D\f\u0003M#\u0006.\u001a\u0011j[BdW-\\3oi\u0006$\u0018n\u001c8!I\u0016$\u0018-\u001b7tA=4\u0007%[7nkR\f'\r\\3!Q\u0006\u001c\b\u000eI7baN\u0004S.Y6fA%t\u0007.\u001a:ji&tw\r\t4s_6\u0004C\u000f[3nAUtw/[:f]\u0005\u0012a1D\u0001\u0007e9\n\u0014G\f\u0019)\r\u0001)yL[Cc\u0001")
public class HashMap<A, B>
extends AbstractMap<A, B>
implements Serializable,
CustomParallelizable<Tuple2<A, B>, ParHashMap<A, B>> {
    public static final long serialVersionUID = 2L;

    public static String bitString$default$2() {
        return HashMap$.MODULE$.bitString$default$2();
    }

    public static int highestOneBit(int n) {
        return HashMap$.MODULE$.highestOneBit(n);
    }

    public static String bitString(int n, String string2) {
        return HashMap$.MODULE$.bitString(n, string2);
    }

    public static IndexedSeq<Object> bits(int n) {
        return HashMap$.MODULE$.bits(n);
    }

    public static int complement(int n) {
        return HashMap$.MODULE$.complement(n);
    }

    public static boolean shorter(int n, int n2) {
        return HashMap$.MODULE$.shorter(n, n2);
    }

    public static boolean unsignedCompare(int n, int n2) {
        return HashMap$.MODULE$.unsignedCompare(n, n2);
    }

    public static boolean hasMatch(int n, int n2, int n3) {
        return HashMap$.MODULE$.hasMatch(n, n2, n3);
    }

    public static int mask(int n, int n2) {
        return HashMap$.MODULE$.mask(n, n2);
    }

    public static boolean zero(int n, int n2) {
        return HashMap$.MODULE$.zero(n, n2);
    }

    public static <A, B> CanBuildFrom<HashMap<?, ?>, Tuple2<A, B>, HashMap<A, B>> canBuildFrom() {
        return HashMap$.MODULE$.canBuildFrom();
    }

    @Override
    public Combiner<Tuple2<A, B>, ParHashMap<A, B>> parCombiner() {
        return CustomParallelizable$class.parCombiner(this);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public HashMap<A, B> empty() {
        return HashMap$.MODULE$.empty();
    }

    @Override
    public Iterator<Tuple2<A, B>> iterator() {
        return Iterator$.MODULE$.empty();
    }

    @Override
    public <U> void foreach(Function1<Tuple2<A, B>, U> f) {
    }

    @Override
    public Option<B> get(A key) {
        return this.get0(key, this.computeHash(key), 0);
    }

    @Override
    public <B1> HashMap<A, B1> updated(A key, B1 value) {
        return this.updated0(key, this.computeHash(key), 0, value, null, null);
    }

    @Override
    public <B1> HashMap<A, B1> $plus(Tuple2<A, B1> kv) {
        return this.updated0(kv._1(), this.computeHash(kv._1()), 0, kv._2(), kv, null);
    }

    @Override
    public <B1> HashMap<A, B1> $plus(Tuple2<A, B1> elem1, Tuple2<A, B1> elem2, Seq<Tuple2<A, B1>> elems) {
        return ((AbstractTraversable)((Object)((HashMap)this.$plus((Tuple2)elem1)).$plus((Tuple2)elem2))).$plus$plus(elems, HashMap$.MODULE$.canBuildFrom());
    }

    @Override
    public HashMap<A, B> $minus(A key) {
        return this.removed0(key, this.computeHash(key), 0);
    }

    @Override
    public HashMap<A, B> filter(Function1<Tuple2<A, B>, Object> p) {
        int n = this.size();
        HashMap$ hashMap$ = HashMap$.MODULE$;
        int n2 = n + 6;
        Predef$ predef$ = Predef$.MODULE$;
        HashMap[] buffer = new HashMap[RichInt$.MODULE$.min$extension(n2, 224)];
        HashMap<A, B> hashMap = this.filter0(p, false, 0, buffer, 0);
        HashMap$ hashMap$2 = HashMap$.MODULE$;
        return hashMap == null ? hashMap$2.empty() : hashMap;
    }

    @Override
    public HashMap<A, B> filterNot(Function1<Tuple2<A, B>, Object> p) {
        int n = this.size();
        HashMap$ hashMap$ = HashMap$.MODULE$;
        int n2 = n + 6;
        Predef$ predef$ = Predef$.MODULE$;
        HashMap[] buffer = new HashMap[RichInt$.MODULE$.min$extension(n2, 224)];
        HashMap<A, B> hashMap = this.filter0(p, true, 0, buffer, 0);
        HashMap$ hashMap$2 = HashMap$.MODULE$;
        return hashMap == null ? hashMap$2.empty() : hashMap;
    }

    public HashMap<A, B> filter0(Function1<Tuple2<A, B>, Object> p, boolean negate, int level, HashMap<A, B>[] buffer, int offset0) {
        return null;
    }

    public int elemHashCode(A key) {
        return ScalaRunTime$.MODULE$.hash(key);
    }

    public final int improve(int hcode) {
        int h = hcode + ~(hcode << 9);
        h ^= h >>> 14;
        h += h << 4;
        return h ^ h >>> 10;
    }

    public int computeHash(A key) {
        return this.improve(this.elemHashCode(key));
    }

    public Option<B> get0(A key, int hash, int level) {
        return None$.MODULE$;
    }

    public <B1> HashMap<A, B1> updated0(A key, int hash, int level, B1 value, Tuple2<A, B1> kv, Merger<A, B1> merger) {
        return new HashMap1<A, B1>(key, hash, value, kv);
    }

    public HashMap<A, B> removed0(A key, int hash, int level) {
        return this;
    }

    public Object writeReplace() {
        return new SerializationProxy(this);
    }

    public scala.collection.immutable.Seq<HashMap<A, B>> split() {
        return (scala.collection.immutable.Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new HashMap[]{this}));
    }

    public <B1> HashMap<A, B1> merged(HashMap<A, B1> that, Function2<Tuple2<A, B1>, Tuple2<A, B1>, Tuple2<A, B1>> mergef) {
        return this.merge0(that, 0, HashMap$.MODULE$.scala$collection$immutable$HashMap$$liftMerger(mergef));
    }

    public <B1> HashMap<A, B1> merge0(HashMap<A, B1> that, int level, Merger<A, B1> merger) {
        return that;
    }

    @Override
    public ParHashMap<A, B> par() {
        return ParHashMap$.MODULE$.fromTrie(this);
    }

    public HashMap() {
        CustomParallelizable$class.$init$(this);
    }

    public static abstract class Merger<A, B> {
        public abstract Tuple2<A, B> apply(Tuple2<A, B> var1, Tuple2<A, B> var2);

        public abstract Merger<A, B> invert();
    }

    public static class HashMap1<A, B>
    extends HashMap<A, B> {
        private final A key;
        private final int hash;
        private final B value;
        private Tuple2<A, B> kv;

        public A key() {
            return this.key;
        }

        public int hash() {
            return this.hash;
        }

        public B value() {
            return this.value;
        }

        public Tuple2<A, B> kv() {
            return this.kv;
        }

        public void kv_$eq(Tuple2<A, B> x$1) {
            this.kv = x$1;
        }

        @Override
        public int size() {
            return 1;
        }

        public A getKey() {
            return this.key();
        }

        public int getHash() {
            return this.hash();
        }

        public int computeHashFor(A k) {
            return this.computeHash(k);
        }

        @Override
        public Option<B> get0(A key, int hash, int level) {
            A a;
            return hash == this.hash() && (key == (a = this.key()) ? true : (key == null ? false : (key instanceof Number ? BoxesRunTime.equalsNumObject((Number)key, a) : (key instanceof Character ? BoxesRunTime.equalsCharObject((Character)key, a) : key.equals(a))))) ? new Some<B>(this.value()) : None$.MODULE$;
        }

        @Override
        public <B1> HashMap<A, B1> updated0(A key, int hash, int level, B1 value, Tuple2<A, B1> kv, Merger<A, B1> merger) {
            HashMap hashMap;
            A a;
            if (hash == this.hash() && (key == (a = this.key()) ? true : (key == null ? false : (key instanceof Number ? BoxesRunTime.equalsNumObject((Number)key, a) : (key instanceof Character ? BoxesRunTime.equalsCharObject((Character)key, a) : key.equals(a)))))) {
                if (merger == null) {
                    hashMap = this.value() == value ? this : new HashMap1<A, B1>(key, hash, value, kv);
                } else {
                    Tuple2<A, B1> nkv = merger.apply(this.ensurePair(), kv == null ? new Tuple2<A, B1>(key, value) : kv);
                    hashMap = new HashMap1<A, B1>(nkv._1(), hash, nkv._2(), nkv);
                }
            } else if (hash != this.hash()) {
                HashMap1<A, B1> that = new HashMap1<A, B1>(key, hash, value, kv);
                hashMap = HashMap$.MODULE$.scala$collection$immutable$HashMap$$makeHashTrieMap(this.hash(), this, hash, that, level, 2);
            } else {
                hashMap = new HashMapCollision1(hash, ((ListMap)((ListMap)ListMap$.MODULE$.empty()).updated((Object)this.key(), this.value())).updated((Object)key, (Object)value));
            }
            return hashMap;
        }

        @Override
        public HashMap<A, B> removed0(A key, int hash, int level) {
            A a;
            return hash == this.hash() && (key == (a = this.key()) ? true : (key == null ? false : (key instanceof Number ? BoxesRunTime.equalsNumObject((Number)key, a) : (key instanceof Character ? BoxesRunTime.equalsCharObject((Character)key, a) : key.equals(a))))) ? HashMap$.MODULE$.empty() : this;
        }

        @Override
        public HashMap<A, B> filter0(Function1<Tuple2<A, B>, Object> p, boolean negate, int level, HashMap<A, B>[] buffer, int offset0) {
            return negate ^ BoxesRunTime.unboxToBoolean(p.apply(this.ensurePair())) ? this : null;
        }

        @Override
        public Iterator<Tuple2<A, B>> iterator() {
            return Iterator$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[]{this.ensurePair()}));
        }

        @Override
        public <U> void foreach(Function1<Tuple2<A, B>, U> f) {
            f.apply(this.ensurePair());
        }

        public Tuple2<A, B> ensurePair() {
            Tuple2<A, B> tuple2;
            if (this.kv() != null) {
                tuple2 = this.kv();
            } else {
                this.kv_$eq(new Tuple2<A, B>(this.key(), this.value()));
                tuple2 = this.kv();
            }
            return tuple2;
        }

        @Override
        public <B1> HashMap<A, B1> merge0(HashMap<A, B1> that, int level, Merger<A, B1> merger) {
            return that.updated0(this.key(), this.hash(), level, this.value(), this.kv(), merger.invert());
        }

        public HashMap1(A key, int hash, B value, Tuple2<A, B> kv) {
            this.key = key;
            this.hash = hash;
            this.value = value;
            this.kv = kv;
        }
    }

    public static class HashTrieMap<A, B>
    extends HashMap<A, B> {
        private final int bitmap;
        private final HashMap<A, B>[] elems;
        private final int size0;

        public int bitmap() {
            return this.bitmap;
        }

        public HashMap<A, B>[] elems() {
            return this.elems;
        }

        public int size0() {
            return this.size0;
        }

        @Override
        public int size() {
            return this.size0();
        }

        @Override
        public Option<B> get0(A key, int hash, int level) {
            Option option;
            int index = hash >>> level & 0x1F;
            int mask = 1 << index;
            if (this.bitmap() == -1) {
                option = this.elems()[index & 0x1F].get0(key, hash, level + 5);
            } else if ((this.bitmap() & mask) != 0) {
                int offset = Integer.bitCount(this.bitmap() & mask - 1);
                option = this.elems()[offset].get0(key, hash, level + 5);
            } else {
                option = None$.MODULE$;
            }
            return option;
        }

        @Override
        public <B1> HashMap<A, B1> updated0(A key, int hash, int level, B1 value, Tuple2<A, B1> kv, Merger<A, B1> merger) {
            HashTrieMap hashTrieMap;
            int index = hash >>> level & 0x1F;
            int mask = 1 << index;
            int offset = Integer.bitCount(this.bitmap() & mask - 1);
            if ((this.bitmap() & mask) != 0) {
                HashMap<A, B> sub = this.elems()[offset];
                HashMap<A, B1> subNew = sub.updated0(key, hash, level + 5, value, kv, merger);
                if (subNew == sub) {
                    hashTrieMap = this;
                } else {
                    HashMap[] elemsNew = new HashMap[this.elems().length];
                    Array$.MODULE$.copy(this.elems(), 0, elemsNew, 0, this.elems().length);
                    elemsNew[offset] = subNew;
                    hashTrieMap = new HashTrieMap<A, B>(this.bitmap(), elemsNew, this.size() + (subNew.size() - sub.size()));
                }
            } else {
                HashMap[] elemsNew = new HashMap[this.elems().length + 1];
                Array$.MODULE$.copy(this.elems(), 0, elemsNew, 0, offset);
                elemsNew[offset] = new HashMap1<A, B1>(key, hash, value, kv);
                Array$.MODULE$.copy(this.elems(), offset, elemsNew, offset + 1, this.elems().length - offset);
                hashTrieMap = new HashTrieMap<A, B>(this.bitmap() | mask, elemsNew, this.size() + 1);
            }
            return hashTrieMap;
        }

        @Override
        public HashMap<A, B> removed0(A key, int hash, int level) {
            GenMap<A, B> genMap;
            int index = hash >>> level & 0x1F;
            int mask = 1 << index;
            int offset = Integer.bitCount(this.bitmap() & mask - 1);
            if ((this.bitmap() & mask) != 0) {
                HashMap<A, B> sub = this.elems()[offset];
                HashMap<A, B> subNew = sub.removed0(key, hash, level + 5);
                if (subNew == sub) {
                    genMap = this;
                } else if (subNew.isEmpty()) {
                    int bitmapNew = this.bitmap() ^ mask;
                    if (bitmapNew != 0) {
                        HashMap[] elemsNew = new HashMap[this.elems().length - 1];
                        Array$.MODULE$.copy(this.elems(), 0, elemsNew, 0, offset);
                        Array$.MODULE$.copy(this.elems(), offset + 1, elemsNew, offset, this.elems().length - offset - 1);
                        int sizeNew = this.size() - sub.size();
                        genMap = elemsNew.length == 1 && !(elemsNew[0] instanceof HashTrieMap) ? elemsNew[0] : new HashTrieMap<A, B>(bitmapNew, elemsNew, sizeNew);
                    } else {
                        genMap = HashMap$.MODULE$.empty();
                    }
                } else if (this.elems().length == 1 && !(subNew instanceof HashTrieMap)) {
                    genMap = subNew;
                } else {
                    HashMap[] elemsNew = new HashMap[this.elems().length];
                    Array$.MODULE$.copy(this.elems(), 0, elemsNew, 0, this.elems().length);
                    elemsNew[offset] = subNew;
                    int sizeNew = this.size() + (subNew.size() - sub.size());
                    genMap = new HashTrieMap<A, B>(this.bitmap(), elemsNew, sizeNew);
                }
            } else {
                genMap = this;
            }
            return genMap;
        }

        @Override
        public HashMap<A, B> filter0(Function1<Tuple2<A, B>, Object> p, boolean negate, int level, HashMap<A, B>[] buffer, int offset0) {
            HashMap hashMap;
            int offset = offset0;
            int rs = 0;
            int kept = 0;
            for (int i = 0; i < this.elems().length; ++i) {
                HashMap<A, B> result2 = this.elems()[i].filter0(p, negate, level + 5, buffer, offset);
                if (result2 == null) continue;
                buffer[offset] = result2;
                ++offset;
                rs += result2.size();
                kept |= 1 << i;
            }
            if (offset == offset0) {
                hashMap = null;
            } else if (rs == this.size0()) {
                hashMap = this;
            } else if (offset == offset0 + 1 && !(buffer[offset0] instanceof HashTrieMap)) {
                hashMap = buffer[offset0];
            } else {
                int length = offset - offset0;
                HashMap[] elems1 = new HashMap[length];
                System.arraycopy(buffer, offset0, elems1, 0, length);
                int bitmap1 = length == this.elems().length ? this.bitmap() : HashMap$.MODULE$.scala$collection$immutable$HashMap$$keepBits(this.bitmap(), kept);
                hashMap = new HashTrieMap<A, B>(bitmap1, elems1, rs);
            }
            return hashMap;
        }

        @Override
        public Iterator<Tuple2<A, B>> iterator() {
            return new TrieIterator<Tuple2<A, B>>(this){

                public final Tuple2<A, B> getElem(Object cc) {
                    return ((HashMap1)cc).ensurePair();
                }
            };
        }

        @Override
        public <U> void foreach(Function1<Tuple2<A, B>, U> f) {
            for (int i = 0; i < this.elems().length; ++i) {
                this.elems()[i].foreach(f);
            }
        }

        private int posOf(int n, int bm) {
            int left = n;
            int i = -1;
            int b = bm;
            while (left >= 0) {
                ++i;
                if ((b & 1) != 0) {
                    --left;
                }
                b >>>= 1;
            }
            return i;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public scala.collection.immutable.Seq<HashMap<A, B>> split() {
            GenTraversable<HashMap<A, B>> genTraversable;
            if (this.size() == 1) {
                genTraversable = (scala.collection.immutable.Seq<HashMap<A, B>>)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new HashTrieMap[]{this}));
                return genTraversable;
            } else {
                int nodesize = Integer.bitCount(this.bitmap());
                if (nodesize > 1) {
                    int splitpoint = nodesize / 2;
                    int bitsplitpoint = this.posOf(nodesize / 2, this.bitmap());
                    int bm1 = this.bitmap() & -1 << bitsplitpoint;
                    int bm2 = this.bitmap() & -1 >>> 32 - bitsplitpoint;
                    Tuple2 tuple2 = Predef$.MODULE$.refArrayOps((Object[])this.elems()).splitAt(splitpoint);
                    if (tuple2 == null) throw new MatchError(tuple2);
                    Tuple2 tuple22 = new Tuple2(tuple2._1(), tuple2._2());
                    HashMap[] e1 = (HashMap[])tuple22._1();
                    HashMap[] e2 = (HashMap[])tuple22._2();
                    HashTrieMap<A, B> hm1 = new HashTrieMap<A, B>(bm1, e1, BoxesRunTime.unboxToInt(Predef$.MODULE$.refArrayOps((Object[])e1).foldLeft(BoxesRunTime.boxToInteger(0), new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final int apply(int x$4, HashMap<A, B> x$5) {
                            return x$4 + x$5.size();
                        }
                    })));
                    HashTrieMap<A, B> hm2 = new HashTrieMap<A, B>(bm2, e2, BoxesRunTime.unboxToInt(Predef$.MODULE$.refArrayOps((Object[])e2).foldLeft(BoxesRunTime.boxToInteger(0), new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final int apply(int x$6, HashMap<A, B> x$7) {
                            return x$6 + x$7.size();
                        }
                    })));
                    genTraversable = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new HashTrieMap[]{hm1, hm2}));
                    return genTraversable;
                } else {
                    genTraversable = this.elems()[0].split();
                }
            }
            return genTraversable;
        }

        @Override
        public <B1> HashMap<A, B1> merge0(HashMap<A, B1> that, int level, Merger<A, B1> merger) {
            block10: {
                HashTrieMap<A, B> hashTrieMap;
                block7: {
                    block9: {
                        block8: {
                            block6: {
                                if (!(that instanceof HashMap1)) break block6;
                                HashMap1 hashMap1 = (HashMap1)that;
                                hashTrieMap = this.updated0(hashMap1.key(), hashMap1.hash(), level, hashMap1.value(), hashMap1.kv(), merger);
                                break block7;
                            }
                            if (!(that instanceof HashTrieMap)) break block8;
                            HashTrieMap hashTrieMap2 = (HashTrieMap)that;
                            HashMap<A, B>[] thiselems = this.elems();
                            HashMap<A, B>[] thatelems = hashTrieMap2.elems();
                            int thisbm = this.bitmap();
                            int thatbm = hashTrieMap2.bitmap();
                            int subcount = Integer.bitCount(thisbm | thatbm);
                            HashMap[] merged = new HashMap[subcount];
                            int thisi = 0;
                            int thati = 0;
                            int totalelems = 0;
                            for (int i = 0; i < subcount; ++i) {
                                int thislsb = thisbm ^ thisbm & thisbm - 1;
                                int thatlsb = thatbm ^ thatbm & thatbm - 1;
                                if (thislsb == thatlsb) {
                                    HashMap<A, B> m = thiselems[thisi].merge0(thatelems[thati], level + 5, merger);
                                    totalelems += m.size();
                                    merged[i] = m;
                                    thisbm &= ~thislsb;
                                    thatbm &= ~thatlsb;
                                    ++thati;
                                    ++thisi;
                                    continue;
                                }
                                if (HashMap$.MODULE$.unsignedCompare(thislsb - 1, thatlsb - 1)) {
                                    HashMap<A, B> m = thiselems[thisi];
                                    totalelems += m.size();
                                    merged[i] = m;
                                    thisbm &= ~thislsb;
                                    ++thisi;
                                    continue;
                                }
                                HashMap<A, B> m = thatelems[thati];
                                totalelems += m.size();
                                merged[i] = m;
                                thatbm &= ~thatlsb;
                                ++thati;
                            }
                            hashTrieMap = new HashTrieMap<A, B>(this.bitmap() | hashTrieMap2.bitmap(), merged, totalelems);
                            break block7;
                        }
                        if (!(that instanceof HashMapCollision1)) break block9;
                        hashTrieMap = that.merge0(this, level, merger.invert());
                        break block7;
                    }
                    if (!(that instanceof HashMap)) break block10;
                    hashTrieMap = this;
                }
                return hashTrieMap;
            }
            throw package$.MODULE$.error("section supposed to be unreachable.");
        }

        public HashTrieMap(int bitmap, HashMap<A, B>[] elems, int size0) {
            this.bitmap = bitmap;
            this.elems = elems;
            this.size0 = size0;
        }
    }

    public static class HashMapCollision1<A, B>
    extends HashMap<A, B> {
        private final int hash;
        private final ListMap<A, B> kvs;

        public int hash() {
            return this.hash;
        }

        public ListMap<A, B> kvs() {
            return this.kvs;
        }

        @Override
        public int size() {
            return this.kvs().size();
        }

        @Override
        public Option<B> get0(A key, int hash, int level) {
            return hash == this.hash() ? this.kvs().get(key) : None$.MODULE$;
        }

        @Override
        public <B1> HashMap<A, B1> updated0(A key, int hash, int level, B1 value, Tuple2<A, B1> kv, Merger<A, B1> merger) {
            HashMap hashMap;
            if (hash == this.hash()) {
                hashMap = merger != null && this.kvs().contains(key) ? new HashMapCollision1<A, B>(hash, this.kvs().$plus((Tuple2)merger.apply(new Tuple2(key, this.kvs().apply(key)), kv))) : new HashMapCollision1<A, B>(hash, this.kvs().updated((Object)key, (Object)value));
            } else {
                HashMap1<A, B1> that = new HashMap1<A, B1>(key, hash, value, kv);
                hashMap = HashMap$.MODULE$.scala$collection$immutable$HashMap$$makeHashTrieMap(this.hash(), this, hash, that, level, this.size() + 1);
            }
            return hashMap;
        }

        @Override
        public HashMap<A, B> removed0(A key, int hash, int level) {
            GenMap<Object, Object> genMap;
            if (hash == this.hash()) {
                Object kvs1 = this.kvs().$minus((Object)key);
                int n = ((ListMap)kvs1).size();
                switch (n) {
                    default: {
                        if (n == this.kvs().size()) {
                            genMap = this;
                            break;
                        }
                        genMap = new HashMapCollision1<A, B>(hash, kvs1);
                        break;
                    }
                    case 1: {
                        Tuple2 kv = (Tuple2)((AbstractIterable)kvs1).head();
                        genMap = new HashMap1(kv._1(), hash, kv._2(), kv);
                        break;
                    }
                    case 0: {
                        genMap = HashMap$.MODULE$.empty();
                        break;
                    }
                }
            } else {
                genMap = this;
            }
            return genMap;
        }

        @Override
        public HashMap<A, B> filter0(Function1<Tuple2<A, B>, Object> p, boolean negate, int level, HashMap<A, B>[] buffer, int offset0) {
            HashMap hashMap;
            ListMap kvs1 = negate ? (ListMap)this.kvs().filterNot((Function1)p) : (ListMap)this.kvs().filter(p);
            int n = kvs1.size();
            switch (n) {
                default: {
                    if (n == this.kvs().size()) {
                        hashMap = this;
                        break;
                    }
                    hashMap = new HashMapCollision1<A, B>(this.hash(), kvs1);
                    break;
                }
                case 1: {
                    Tuple2 tuple2 = (Tuple2)kvs1.head();
                    if (tuple2 != null) {
                        Tuple3 tuple3 = new Tuple3(tuple2, tuple2._1(), tuple2._2());
                        Tuple2 kv = tuple3._1();
                        Object k = tuple3._2();
                        Object v = tuple3._3();
                        hashMap = new HashMap1(k, this.hash(), v, kv);
                        break;
                    }
                    throw new MatchError(tuple2);
                }
                case 0: {
                    hashMap = null;
                }
            }
            return hashMap;
        }

        @Override
        public Iterator<Tuple2<A, B>> iterator() {
            return this.kvs().iterator();
        }

        @Override
        public <U> void foreach(Function1<Tuple2<A, B>, U> f) {
            this.kvs().foreach(f);
        }

        @Override
        public scala.collection.immutable.Seq<HashMap<A, B>> split() {
            Tuple2 tuple2 = this.kvs().splitAt(this.kvs().size() / 2);
            if (tuple2 != null) {
                Tuple2 tuple22 = new Tuple2(tuple2._1(), tuple2._2());
                ListMap x = (ListMap)tuple22._1();
                ListMap y = (ListMap)tuple22._2();
                return List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new HashMapCollision1[]{this.newhm$1(x), this.newhm$1(y)}));
            }
            throw new MatchError(tuple2);
        }

        @Override
        public <B1> HashMap<A, B1> merge0(HashMap<A, B1> that, int level, Merger<A, B1> merger) {
            ObjectRef<HashMap<A, B1>> m = ObjectRef.create(that);
            this.kvs().foreach(new Serializable(this, level, merger, m){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ HashMapCollision1 $outer;
                private final int level$1;
                private final Merger merger$1;
                private final ObjectRef m$1;

                public final void apply(Tuple2<A, B> p) {
                    this.m$1.elem = ((HashMap)this.m$1.elem).updated0(p._1(), this.$outer.hash(), this.level$1, p._2(), p, this.merger$1);
                }
                {
                    void var4_4;
                    void var3_3;
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.level$1 = level$1;
                    this.merger$1 = var3_3;
                    this.m$1 = var4_4;
                }
            });
            return (HashMap)m.elem;
        }

        private final HashMapCollision1 newhm$1(ListMap lm) {
            return new HashMapCollision1<A, B>(this.hash(), lm);
        }

        public HashMapCollision1(int hash, ListMap<A, B> kvs) {
            this.hash = hash;
            this.kvs = kvs;
        }
    }

    public static class SerializationProxy<A, B>
    implements Serializable {
        public static final long serialVersionUID = 2L;
        private transient HashMap<A, B> scala$collection$immutable$HashMap$SerializationProxy$$orig;

        public HashMap<A, B> scala$collection$immutable$HashMap$SerializationProxy$$orig() {
            return this.scala$collection$immutable$HashMap$SerializationProxy$$orig;
        }

        public void scala$collection$immutable$HashMap$SerializationProxy$$orig_$eq(HashMap<A, B> x$1) {
            this.scala$collection$immutable$HashMap$SerializationProxy$$orig = x$1;
        }

        private void writeObject(ObjectOutputStream out) {
            int s2 = this.scala$collection$immutable$HashMap$SerializationProxy$$orig().size();
            out.writeInt(s2);
            this.scala$collection$immutable$HashMap$SerializationProxy$$orig().withFilter((Function1<A, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Tuple2<A, B> check$ifrefutable$1) {
                    boolean bl = check$ifrefutable$1 != null;
                    return bl;
                }
            })).foreach(new Serializable(this, out){
                public static final long serialVersionUID = 0L;
                private final ObjectOutputStream out$1;

                public final void apply(Tuple2<A, B> x$8) {
                    if (x$8 != null) {
                        this.out$1.writeObject(x$8._1());
                        this.out$1.writeObject(x$8._2());
                        return;
                    }
                    throw new MatchError(x$8);
                }
                {
                    this.out$1 = out$1;
                }
            });
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private void readObject(ObjectInputStream in) {
            HashMap$ hashMap$ = HashMap$.MODULE$;
            this.scala$collection$immutable$HashMap$SerializationProxy$$orig_$eq(HashMap$EmptyHashMap$.MODULE$);
            int s2 = in.readInt();
            Predef$ predef$ = Predef$.MODULE$;
            Range$ range$ = Range$.MODULE$;
            Range range2 = new Range(0, s2, 1);
            if (range2.isEmpty()) return;
            int i1 = range2.start();
            while (true) {
                Object key1 = in.readObject();
                Object value1 = in.readObject();
                this.scala$collection$immutable$HashMap$SerializationProxy$$orig_$eq((HashMap<A, B>)this.scala$collection$immutable$HashMap$SerializationProxy$$orig().updated(key1, value1));
                if (i1 == range2.lastElement()) {
                    return;
                }
                i1 += range2.step();
            }
        }

        private Object readResolve() {
            return this.scala$collection$immutable$HashMap$SerializationProxy$$orig();
        }

        public SerializationProxy(HashMap<A, B> orig) {
            this.scala$collection$immutable$HashMap$SerializationProxy$$orig = orig;
        }
    }
}

