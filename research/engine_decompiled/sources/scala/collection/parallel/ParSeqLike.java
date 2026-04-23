/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.Tuple2$mcIZ$sp;
import scala.collection.BufferedIterator;
import scala.collection.BufferedIterator$class;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.GenSeqLike;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.IterableLike;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.Iterator$class;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.SeqLike;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.DelegatedSignalling$class;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.AugmentedIterableIterator$class;
import scala.collection.parallel.AugmentedSeqIterator$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.CombinerFactory;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.IterableSplitter$class;
import scala.collection.parallel.ParIterable;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.ParIterableLike$Accessor$class;
import scala.collection.parallel.ParIterableLike$StrictSplitterCheckTask$class;
import scala.collection.parallel.ParSeq;
import scala.collection.parallel.PreciseSplitter;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.RemainsIterator$class;
import scala.collection.parallel.SeqSplitter;
import scala.collection.parallel.SeqSplitter$class;
import scala.collection.parallel.Task;
import scala.collection.parallel.Task$class;
import scala.collection.parallel.package$;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.RichInt$;

@ScalaSignature(bytes="\u0006\u0001\u0019Ue!C\u0001\u0003!\u0003\r\t!\u0003DJ\u0005)\u0001\u0016M]*fc2K7.\u001a\u0006\u0003\u0007\u0011\t\u0001\u0002]1sC2dW\r\u001c\u0006\u0003\u000b\u0019\t!bY8mY\u0016\u001cG/[8o\u0015\u00059\u0011!B:dC2\f7\u0001A\u000b\u0005\u0015Uy\"f\u0005\u0003\u0001\u0017=1\u0003C\u0001\u0007\u000e\u001b\u00051\u0011B\u0001\b\u0007\u0005\u0019\te.\u001f*fMB!\u0001#E\n\u001f\u001b\u0005!\u0011B\u0001\n\u0005\u0005)9UM\\*fc2K7.\u001a\t\u0003)Ua\u0001\u0001\u0002\u0004\u0017\u0001\u0011\u0015\ra\u0006\u0002\u0002)F\u0011\u0001d\u0007\t\u0003\u0019eI!A\u0007\u0004\u0003\u000f9{G\u000f[5oOB\u0011A\u0002H\u0005\u0003;\u0019\u00111!\u00118z!\t!r\u0004\u0002\u0004!\u0001\u0011\u0015\r!\t\u0002\u0005%\u0016\u0004(/\u0005\u0002\u0019EA\u00191\u0005J\n\u000e\u0003\tI!!\n\u0002\u0003\rA\u000b'oU3r!\u0015\u0019se\u0005\u0010*\u0013\tA#AA\bQCJLE/\u001a:bE2,G*[6f!\t!\"\u0006\u0002\u0004,\u0001\u0011\u0015\r\u0001\f\u0002\u000b'\u0016\fX/\u001a8uS\u0006d\u0017C\u0001\r.%\rq\u0003g\u000e\u0004\u0005_\u0001\u0001QF\u0001\u0007=e\u00164\u0017N\\3nK:$h\bE\u00022iMq!\u0001\u0004\u001a\n\u0005M2\u0011a\u00029bG.\fw-Z\u0005\u0003kY\u00121aU3r\u0015\t\u0019d\u0001\u0005\u0003\u0011qMI\u0013BA\u001d\u0005\u0005\u001d\u0019V-\u001d'jW\u0016DQa\u000f\u0001\u0005\u0002q\na\u0001J5oSR$C#A\u001f\u0011\u00051q\u0014BA \u0007\u0005\u0011)f.\u001b;\u0006\r\u0005\u0003\u0001\u0015!\u0005C\u0005A\u0019V\u000f]3s!\u0006\u0014\u0018\n^3sCR|'\u000fE\u0002$\u0007NI!\u0001\u0012\u0002\u0003!%#XM]1cY\u0016\u001c\u0006\u000f\\5ui\u0016\u0014\bB\u0002$\u0001\r#\u0011q)\u0001\u0005ta2LG\u000f^3s+\u0005A\u0005cA\u0012J'%\u0011!J\u0001\u0002\f'\u0016\f8\u000b\u001d7jiR,'\u000fC\u0003M\u0001\u0011\u0005S*\u0001\u0005ji\u0016\u0014\u0018\r^8s+\u0005q\u0005cA\u0012P'%\u0011\u0001K\u0001\u0002\u0010!J,7-[:f'Bd\u0017\u000e\u001e;fe\")!\u000b\u0001C!'\u0006!1/\u001b>f+\u0005!\u0006C\u0001\u0007V\u0013\t1fAA\u0002J]R4Q\u0001\u0017\u0001\u0002\u0012e\u0013\u0001\"\u00127f[\u0016tGo]\n\u0005/.A%\fE\u000227NI!\u0001\u0018\u001c\u0003!\t+hMZ3sK\u0012LE/\u001a:bi>\u0014\b\u0002\u00030X\u0005\u0003\u0005\u000b\u0011\u0002+\u0002\u000bM$\u0018M\u001d;\t\u0011\u0001<&Q1A\u0005\u0002M\u000b1!\u001a8e\u0011!\u0011wK!A!\u0002\u0013!\u0016\u0001B3oI\u0002BQ\u0001Z,\u0005\u0002\u0015\fa\u0001P5oSRtDc\u00014iSB\u0011qmV\u0007\u0002\u0001!)al\u0019a\u0001)\")\u0001m\u0019a\u0001)\"91n\u0016a\u0001\n\u0013\u0019\u0016!A5\t\u000f5<\u0006\u0019!C\u0005]\u0006)\u0011n\u0018\u0013fcR\u0011Qh\u001c\u0005\ba2\f\t\u00111\u0001U\u0003\rAH%\r\u0005\u0007e^\u0003\u000b\u0015\u0002+\u0002\u0005%\u0004\u0003\"\u0002;X\t\u0003)\u0018a\u00025bg:+\u0007\u0010^\u000b\u0002mB\u0011Ab^\u0005\u0003q\u001a\u0011qAQ8pY\u0016\fg\u000eC\u0003{/\u0012\u000510\u0001\u0003oKb$H#A\n\t\u000bu<F\u0011\u0001@\u0002\t!,\u0017\rZ\u000b\u0002'!1\u0011\u0011A,\u0005\u0006M\u000b\u0011B]3nC&t\u0017N\\4\t\u000f\u0005\u0015q\u000b\"\u0001\u0002\b\u0005\u0019A-\u001e9\u0016\u0003\u0019Dq!a\u0003X\t\u0003\ti!A\u0003ta2LG/\u0006\u0002\u0002\u0010A!\u0001#!\u0005I\u0013\t)D\u0001C\u0004\u0002\u0016]#\t!a\u0006\u0002\rA\u001c\b\u000f\\5u)\u0011\tI\"a\u0007\u0011\u0007E\"\u0004\n\u0003\u0005\u0002\u001e\u0005M\u0001\u0019AA\u0010\u0003\u0015\u0019\u0018N_3t!\u0011a\u0011\u0011\u0005+\n\u0007\u0005\rbA\u0001\u0006=e\u0016\u0004X-\u0019;fIzBq!a\nX\t\u0003\nI#\u0001\u0005u_N#(/\u001b8h)\t\tY\u0003\u0005\u0003\u0002.\u0005]RBAA\u0018\u0015\u0011\t\t$a\r\u0002\t1\fgn\u001a\u0006\u0003\u0003k\tAA[1wC&!\u0011\u0011HA\u0018\u0005\u0019\u0019FO]5oO\"9\u0011Q\b\u0001\u0005\u0002\u0005}\u0012!D:fO6,g\u000e\u001e'f]\u001e$\b\u000eF\u0003U\u0003\u0003\nY\u0005\u0003\u0005\u0002D\u0005m\u0002\u0019AA#\u0003\u0005\u0001\b#\u0002\u0007\u0002HM1\u0018bAA%\r\tIa)\u001e8di&|g.\r\u0005\b\u0003\u001b\nY\u00041\u0001U\u0003\u00111'o\\7\t\u000f\u0005E\u0003\u0001\"\u0001\u0002T\u0005Q\u0011N\u001c3fq^CWM]3\u0015\u000bQ\u000b)&a\u0016\t\u0011\u0005\r\u0013q\na\u0001\u0003\u000bBq!!\u0014\u0002P\u0001\u0007A\u000bC\u0004\u0002\\\u0001!\t!!\u0018\u0002\u001d1\f7\u000f^%oI\u0016Dx\u000b[3sKR)A+a\u0018\u0002b!A\u00111IA-\u0001\u0004\t)\u0005\u0003\u0004a\u00033\u0002\r\u0001\u0016\u0005\b\u0003K\u0002A\u0011AA4\u0003\u001d\u0011XM^3sg\u0016,\u0012A\b\u0005\b\u0003W\u0002A\u0011AA7\u0003)\u0011XM^3sg\u0016l\u0015\r]\u000b\u0007\u0003_\nY)!\u001e\u0015\t\u0005E\u0014q\u0012\u000b\u0005\u0003g\nI\bE\u0002\u0015\u0003k\"q!a\u001e\u0002j\t\u0007qC\u0001\u0003UQ\u0006$\b\u0002CA>\u0003S\u0002\u001d!! \u0002\u0005\t4\u0007#CA@\u0003\u000bs\u0012\u0011RA:\u001b\t\t\tIC\u0002\u0002\u0004\u0012\tqaZ3oKJL7-\u0003\u0003\u0002\b\u0006\u0005%\u0001D\"b]\n+\u0018\u000e\u001c3Ge>l\u0007c\u0001\u000b\u0002\f\u00129\u0011QRA5\u0005\u00049\"!A*\t\u0011\u0005E\u0015\u0011\u000ea\u0001\u0003'\u000b\u0011A\u001a\t\u0007\u0019\u0005\u001d3#!#\t\u000f\u0005]\u0005\u0001\"\u0001\u0002\u001a\u0006Q1\u000f^1siN<\u0016\u000e\u001e5\u0016\t\u0005m\u0015\u0011\u0016\u000b\u0006m\u0006u\u00151\u0016\u0005\t\u0003?\u000b)\n1\u0001\u0002\"\u0006!A\u000f[1u!\u0015\u0001\u00121UAT\u0013\r\t)\u000b\u0002\u0002\u0007\u000f\u0016t7+Z9\u0011\u0007Q\tI\u000bB\u0004\u0002\u000e\u0006U%\u0019A\f\t\u000f\u00055\u0016Q\u0013a\u0001)\u00061qN\u001a4tKRDq!!-\u0001\t\u0003\n\u0019,\u0001\u0007tC6,W\t\\3nK:$8/\u0006\u0003\u00026\u0006\u0005Gc\u0001<\u00028\"A\u0011qTAX\u0001\u0004\tI\fE\u0003\u0011\u0003w\u000by,C\u0002\u0002>\u0012\u00111bR3o\u0013R,'/\u00192mKB\u0019A#!1\u0005\u0011\u0005\r\u0017q\u0016b\u0001\u0003\u000b\u0014\u0011!V\t\u0003'mAq!!3\u0001\t\u0003\tY-\u0001\u0005f]\u0012\u001cx+\u001b;i+\u0011\ti-!6\u0015\u0007Y\fy\r\u0003\u0005\u0002 \u0006\u001d\u0007\u0019AAi!\u0015\u0001\u00121UAj!\r!\u0012Q\u001b\u0003\b\u0003\u001b\u000b9M1\u0001\u0018\u0011\u001d\tI\u000e\u0001C\u0001\u00037\fQ\u0001]1uG\",b!!8\u0002l\u0006\rH\u0003CAp\u0003[\fy/a=\u0015\t\u0005\u0005\u0018Q\u001d\t\u0004)\u0005\rHaBA<\u0003/\u0014\ra\u0006\u0005\t\u0003w\n9\u000eq\u0001\u0002hBI\u0011qPAC=\u0005%\u0018\u0011\u001d\t\u0004)\u0005-H\u0001CAb\u0003/\u0014\r!!2\t\u000f\u00055\u0013q\u001ba\u0001)\"A\u0011\u0011\\Al\u0001\u0004\t\t\u0010E\u0003\u0011\u0003G\u000bI\u000fC\u0004\u0002v\u0006]\u0007\u0019\u0001+\u0002\u0011I,\u0007\u000f\\1dK\u0012Dq!!?\u0001\t\u0013\tY0\u0001\tqCR\u001c\u0007nX:fcV,g\u000e^5bYV1\u0011Q B\u0006\u0005\u0007!\u0002\"a@\u0003\u000e\tE!Q\u0003\u000b\u0005\u0005\u0003\u0011)\u0001E\u0002\u0015\u0005\u0007!q!a\u001e\u0002x\n\u0007q\u0003\u0003\u0005\u0002|\u0005]\b9\u0001B\u0004!%\ty(!\"\u001f\u0005\u0013\u0011\t\u0001E\u0002\u0015\u0005\u0017!\u0001\"a1\u0002x\n\u0007\u0011Q\u0019\u0005\b\u0005\u001f\t9\u00101\u0001U\u0003\u001d1'o\\7be\u001eD\u0001\"!7\u0002x\u0002\u0007!1\u0003\t\u0005cQ\u0012I\u0001C\u0004\u0003\u0018\u0005]\b\u0019\u0001+\u0002\u0003IDqAa\u0007\u0001\t\u0003\u0011i\"A\u0004va\u0012\fG/\u001a3\u0016\r\t}!Q\u0006B\u0013)\u0019\u0011\tCa\f\u00034Q!!1\u0005B\u0014!\r!\"Q\u0005\u0003\b\u0003o\u0012IB1\u0001\u0018\u0011!\tYH!\u0007A\u0004\t%\u0002#CA@\u0003\u000bs\"1\u0006B\u0012!\r!\"Q\u0006\u0003\t\u0003\u0007\u0014IB1\u0001\u0002F\"9!\u0011\u0007B\r\u0001\u0004!\u0016!B5oI\u0016D\b\u0002\u0003B\u001b\u00053\u0001\rAa\u000b\u0002\t\u0015dW-\u001c\u0005\b\u0005s\u0001A\u0011\u0001B\u001e\u0003-!\u0003\u000f\\;tI\r|Gn\u001c8\u0016\r\tu\"1\nB\")\u0011\u0011yD!\u0014\u0015\t\t\u0005#Q\t\t\u0004)\t\rCaBA<\u0005o\u0011\ra\u0006\u0005\t\u0003w\u00129\u0004q\u0001\u0003HAI\u0011qPAC=\t%#\u0011\t\t\u0004)\t-C\u0001CAb\u0005o\u0011\r!!2\t\u0011\tU\"q\u0007a\u0001\u0005\u0013BqA!\u0015\u0001\t\u0003\u0011\u0019&A\u0006%G>dwN\u001c\u0013qYV\u001cXC\u0002B+\u0005G\u0012Y\u0006\u0006\u0003\u0003X\t\u0015D\u0003\u0002B-\u0005;\u00022\u0001\u0006B.\t\u001d\t9Ha\u0014C\u0002]A\u0001\"a\u001f\u0003P\u0001\u000f!q\f\t\n\u0003\u007f\n)I\bB1\u00053\u00022\u0001\u0006B2\t!\t\u0019Ma\u0014C\u0002\u0005\u0015\u0007\u0002\u0003B\u001b\u0005\u001f\u0002\rA!\u0019\t\u000f\t%\u0004\u0001\"\u0001\u0003l\u0005)\u0001/\u00193U_V1!Q\u000eB>\u0005g\"bAa\u001c\u0003~\t\u0005E\u0003\u0002B9\u0005k\u00022\u0001\u0006B:\t\u001d\t9Ha\u001aC\u0002]A\u0001\"a\u001f\u0003h\u0001\u000f!q\u000f\t\n\u0003\u007f\n)I\bB=\u0005c\u00022\u0001\u0006B>\t!\t\u0019Ma\u001aC\u0002\u0005\u0015\u0007b\u0002B@\u0005O\u0002\r\u0001V\u0001\u0004Y\u0016t\u0007\u0002\u0003B\u001b\u0005O\u0002\rA!\u001f\t\u000f\t\u0015\u0005\u0001\"\u0011\u0003\b\u0006\u0019!0\u001b9\u0016\u0011\t%%Q\u0014BQ\u0005\u001f#BAa#\u0003$R!!Q\u0012BI!\r!\"q\u0012\u0003\b\u0003o\u0012\u0019I1\u0001\u0018\u0011!\tYHa!A\u0004\tM\u0005#CA@\u0003\u000bs\"Q\u0013BG!\u001da!q\u0013BN\u0005?K1A!'\u0007\u0005\u0019!V\u000f\u001d7feA\u0019AC!(\u0005\u0011\u0005\r'1\u0011b\u0001\u0003\u000b\u00042\u0001\u0006BQ\t\u001d\tiIa!C\u0002]A\u0001\"a(\u0003\u0004\u0002\u0007!Q\u0015\t\u0006!\u0005m&q\u0014\u0005\b\u0005S\u0003A\u0011\u0001BV\u0003-\u0019wN\u001d:fgB|g\u000eZ:\u0016\t\t5&1\u0018\u000b\u0005\u0005_\u0013i\fF\u0002w\u0005cC\u0001\"a\u0011\u0003(\u0002\u0007!1\u0017\t\b\u0019\tU6C!/w\u0013\r\u00119L\u0002\u0002\n\rVt7\r^5p]J\u00022\u0001\u0006B^\t\u001d\tiIa*C\u0002]A\u0001\"a(\u0003(\u0002\u0007!q\u0018\t\u0006!\u0005\r&\u0011\u0018\u0005\b\u0005\u0007\u0004A\u0011\u0001Bc\u0003\u0011!\u0017N\u001a4\u0016\t\t\u001d'q\u001a\u000b\u0004=\t%\u0007\u0002CAP\u0005\u0003\u0004\rAa3\u0011\u000bA\t\u0019K!4\u0011\u0007Q\u0011y\r\u0002\u0005\u0002D\n\u0005'\u0019AAc\u0011\u001d\u0011\u0019\u000e\u0001C\u0001\u0005+\f\u0011\"\u001b8uKJ\u001cXm\u0019;\u0016\t\t]'q\u001c\u000b\u0004=\te\u0007\u0002CAP\u0005#\u0004\rAa7\u0011\u000bA\t\u0019K!8\u0011\u0007Q\u0011y\u000e\u0002\u0005\u0002D\nE'\u0019AAc\u0011\u001d\u0011\u0019\u000f\u0001C\u0001\u0003O\n\u0001\u0002Z5ti&t7\r\u001e\u0005\b\u0003O\u0001A\u0011\tBt)\t\u0011I\u000f\u0005\u0003\u0003l\nEhb\u0001\u0007\u0003n&\u0019!q\u001e\u0004\u0002\rA\u0013X\rZ3g\u0013\u0011\tIDa=\u000b\u0007\t=h\u0001C\u0004\u0003x\u0002!\tE!?\u0002\u000bQ|7+Z9\u0016\u0003\tBqA!@\u0001\t\u0003\u0012y0\u0001\u0003wS\u0016<XCAB\u0001%\u0015\u0019\u0019aCB\u0004\r\u0015y\u0003\u0001AB\u0001\u0013\r\u0011i\u0010\u000f\t\u0006!\r%1#K\u0005\u0004\u0007\u0017!!aB*fcZKWm\u001e\u0015\t\u0005w\u001cya!\u0006\u0004\u001aA\u0019Ab!\u0005\n\u0007\rMaA\u0001\u0006eKB\u0014XmY1uK\u0012\f#aa\u0006\u0002\u001bU\u001cX\r\t\u0018tKFtc/[3xC\t\u0019Y\"\u0001\u00043]E\nd\u0006\r\u0005\t\u0007?\u0001\u0001\u0015\"\u0005\u0004\"\u0005!Am\\<o)\rA51\u0005\u0005\t\u0003\u0007\u001ai\u00021\u0001\u0004&A\"1qEB\u0016!\u0011\u00193i!\u000b\u0011\u0007Q\u0019Y\u0003B\u0006\u0004.\r\r\u0012\u0011!A\u0001\u0006\u00039\"aA0%c\u0019I1\u0011\u0007\u0001\u0011\u0002GE11\u0007\u0002\t\u0003\u000e\u001cWm]:peV11QGB\u001f\u0007\u0007\u001aRaa\f\f\u0007o\u0001raZB\u001d\u0007w\u0019\t%C\u0002\u00042\u001d\u00022\u0001FB\u001f\t\u001d\u0019yda\fC\u0002]\u0011\u0011A\u0015\t\u0004)\r\rCaBB#\u0007_\u0011\ra\u0006\u0002\u0003)BD\u0011b!\u0013\u00040\t\u0007k\u0011C$\u0002\u0007ALGOB\u0005\u0004N\u0001\u0001\n1%\u0005\u0004P\tYAK]1og\u001a|'/\\3s+\u0019\u0019\tfa\u0016\u0004\\M911J\u0006\u0004T\ru\u0003cB4\u00040\rU3\u0011\f\t\u0004)\r]CaBB \u0007\u0017\u0012\ra\u0006\t\u0004)\rmCaBB#\u0007\u0017\u0012\ra\u0006\t\bO\u000e}3QKB-\u0013\r\u0019ie\n\u0004\t\u0007G\u0002\u0001\u0015!\u0005\u0004f\ti1+Z4nK:$H*\u001a8hi\"\u001cRa!\u0019\f\u0007O\u0002raZB\u0018\u0007S\u001aY\u0007E\u0003\r\u0005/#f\u000fE\u0002h\u0007CB1ba\u001c\u0004b\t\u0005\t\u0015!\u0003\u0002F\u0005!\u0001O]3e\u0011)\tie!\u0019\u0003\u0002\u0003\u0006I\u0001\u0016\u0005\u000b\u0007\u0013\u001a\tG!b!\n#9\u0005BCB<\u0007C\u0012\t\u0011)A\u0005\u0011\u0006!\u0001/\u001b;!\u0011\u001d!7\u0011\rC\u0001\u0007w\"\u0002ba\u001b\u0004~\r}4\u0011\u0011\u0005\t\u0007_\u001aI\b1\u0001\u0002F!9\u0011QJB=\u0001\u0004!\u0006bBB%\u0007s\u0002\r\u0001\u0013\u0005\u000b\u0007\u000b\u001b\t\u00071A\u0005\u0002\r\u001d\u0015A\u0002:fgVdG/\u0006\u0002\u0004j!Q11RB1\u0001\u0004%\ta!$\u0002\u0015I,7/\u001e7u?\u0012*\u0017\u000fF\u0002>\u0007\u001fC\u0011\u0002]BE\u0003\u0003\u0005\ra!\u001b\t\u0013\rM5\u0011\rQ!\n\r%\u0014a\u0002:fgVdG\u000f\t\u0015\u0005\u0007#\u001b9\nE\u0002\r\u00073K1aa'\u0007\u0005!1x\u000e\\1uS2,\u0007\u0002CBP\u0007C\"\ta!)\u0002\t1,\u0017M\u001a\u000b\u0004{\r\r\u0006\u0002CBS\u0007;\u0003\raa*\u0002\tA\u0014XM\u001e\t\u0006\u0019\r%6\u0011N\u0005\u0004\u0007W3!AB(qi&|g\u000eC\u0005\u00040\u000e\u0005\u0004\u0015\"\u0005\u00042\u0006Qa.Z<Tk\n$\u0018m]6\u0015\u0007a\u0019\u0019\f\u0003\u0005\u0002D\r5\u0006\u0019AB[!\t9\u0007\t\u0003\u0005\u0002\f\r\u0005D\u0011IB]+\t\u0019Y\f\u0005\u00032i\ru\u0006cB\u0012\u0004@\u000e%41N\u0005\u0004\u0007\u0003\u0014!\u0001\u0002+bg.D\u0001b!2\u0004b\u0011\u00053qY\u0001\u0006[\u0016\u0014x-\u001a\u000b\u0004{\r%\u0007\u0002CAP\u0007\u0007\u0004\raa\u001b\t\u000f\r57\u0011\rC!k\u00069\"/Z9vSJ,7o\u0015;sS\u000e$8\u000b\u001d7jiR,'o\u001d\u0004\t\u0007#\u0004\u0001\u0015!\u0005\u0004T\nQ\u0011J\u001c3fq^CWM]3\u0014\u000b\r=7b!6\u0011\r\u001d\u001cy\u0003VBl!\r97q\u001a\u0005\f\u0007_\u001ayM!A!\u0002\u0013\t)\u0005\u0003\u0006\u0002N\r='\u0011!Q\u0001\nQC!b!\u0013\u0004P\n\u0015\r\u0015\"\u0005H\u0011)\u00199ha4\u0003\u0002\u0003\u0006I\u0001\u0013\u0005\bI\u000e=G\u0011ABr)!\u00199n!:\u0004h\u000e%\b\u0002CB8\u0007C\u0004\r!!\u0012\t\u000f\u000553\u0011\u001da\u0001)\"91\u0011JBq\u0001\u0004A\u0005\"CBC\u0007\u001f\u0004\r\u0011\"\u0001T\u0011)\u0019Yia4A\u0002\u0013\u00051q\u001e\u000b\u0004{\rE\b\u0002\u00039\u0004n\u0006\u0005\t\u0019\u0001+\t\u0011\rM5q\u001aQ!\nQCCaa=\u0004\u0018\"A1qTBh\t\u0003\u0019I\u0010F\u0002>\u0007wD\u0001b!*\u0004x\u0002\u00071Q \t\u0005\u0019\r%F\u000bC\u0005\u00040\u000e=\u0007\u0015\"\u0005\u0005\u0002Q\u0019\u0001\u0004b\u0001\t\u0011\u0005\r3q a\u0001\u0007kC\u0001\"a\u0003\u0004P\u0012\u0005CqA\u000b\u0003\t\u0013\u0001B!\r\u001b\u0005\fA11ea0U\u0007/D\u0001b!2\u0004P\u0012\u0005Cq\u0002\u000b\u0004{\u0011E\u0001\u0002CAP\t\u001b\u0001\raa6\t\u000f\r57q\u001aC!k\u001aAAq\u0003\u0001!\u0002#!IB\u0001\bMCN$\u0018J\u001c3fq^CWM]3\u0014\u000b\u0011U1\u0002b\u0007\u0011\r\u001d\u001cy\u0003\u0016C\u000f!\r9GQ\u0003\u0005\f\u0007_\")B!A!\u0002\u0013\t)\u0005\u0003\u0006\u0005$\u0011U!\u0011!Q\u0001\nQ\u000b1\u0001]8t\u0011)\u0019I\u0005\"\u0006\u0003\u0006\u0004&\tb\u0012\u0005\u000b\u0007o\")B!A!\u0002\u0013A\u0005b\u00023\u0005\u0016\u0011\u0005A1\u0006\u000b\t\t;!i\u0003b\f\u00052!A1q\u000eC\u0015\u0001\u0004\t)\u0005C\u0004\u0005$\u0011%\u0002\u0019\u0001+\t\u000f\r%C\u0011\u0006a\u0001\u0011\"I1Q\u0011C\u000b\u0001\u0004%\ta\u0015\u0005\u000b\u0007\u0017#)\u00021A\u0005\u0002\u0011]BcA\u001f\u0005:!A\u0001\u000f\"\u000e\u0002\u0002\u0003\u0007A\u000b\u0003\u0005\u0004\u0014\u0012U\u0001\u0015)\u0003UQ\u0011!Yda&\t\u0011\r}EQ\u0003C\u0001\t\u0003\"2!\u0010C\"\u0011!\u0019)\u000bb\u0010A\u0002\ru\b\"CBX\t+\u0001K\u0011\u0003C$)\rAB\u0011\n\u0005\t\u0003\u0007\")\u00051\u0001\u00046\"A\u00111\u0002C\u000b\t\u0003\"i%\u0006\u0002\u0005PA!\u0011\u0007\u000eC)!\u0019\u00193q\u0018+\u0005\u001e!A1Q\u0019C\u000b\t\u0003\")\u0006F\u0002>\t/B\u0001\"a(\u0005T\u0001\u0007AQ\u0004\u0005\b\u0007\u001b$)\u0002\"\u0011v\r!!i\u0006\u0001Q\u0001\u0012\u0011}#a\u0002*fm\u0016\u00148/Z\u000b\u0007\tC\"i\u0007\"\u001d\u0014\u000b\u0011m3\u0002b\u0019\u0011\u000f\u001d\u001cY\u0005\"\u001a\u0005xA91\u0005b\u001a\u0005l\u0011=\u0014b\u0001C5\u0005\tA1i\\7cS:,'\u000fE\u0002\u0015\t[\"\u0001\"a1\u0005\\\t\u0007\u0011Q\u0019\t\u0004)\u0011ED\u0001\u0003C:\t7\u0012\r\u0001\"\u001e\u0003\tQC\u0017n]\t\u0003=m\u0001ra\u001aC.\tW\"y\u0007C\u0006\u0005|\u0011m#\u0011!Q\u0001\n\u0011u\u0014aA2cMB)A\u0002b \u0005f%\u0019A\u0011\u0011\u0004\u0003\u0013\u0019+hn\u0019;j_:\u0004\u0004BCB%\t7\u0012)\u0019)C\t\u000f\"Q1q\u000fC.\u0005\u0003\u0005\u000b\u0011\u0002%\t\u000f\u0011$Y\u0006\"\u0001\u0005\nR1Aq\u000fCF\t\u001bC\u0001\u0002b\u001f\u0005\b\u0002\u0007AQ\u0010\u0005\b\u0007\u0013\"9\t1\u0001I\u0011)\u0019)\tb\u0017A\u0002\u0013\u0005A\u0011S\u000b\u0003\tKB!ba#\u0005\\\u0001\u0007I\u0011\u0001CK)\riDq\u0013\u0005\na\u0012M\u0015\u0011!a\u0001\tKB\u0011ba%\u0005\\\u0001\u0006K\u0001\"\u001a)\t\u0011e5q\u0013\u0005\t\u0007?#Y\u0006\"\u0001\u0005 R\u0019Q\b\")\t\u0011\r\u0015FQ\u0014a\u0001\tG\u0003R\u0001DBU\tKB\u0011ba,\u0005\\\u0001&\t\u0002b*\u0015\t\u0011]D\u0011\u0016\u0005\t\u0003\u0007\")\u000b1\u0001\u00046\"A1Q\u0019C.\t\u0003\"i\u000bF\u0002>\t_C\u0001\"a(\u0005,\u0002\u0007Aq\u000f\u0004\t\tg\u0003\u0001\u0015!\u0005\u00056\nQ!+\u001a<feN,W*\u00199\u0016\r\u0011]Fq\u0018Cb'\u0015!\tl\u0003C]!\u001d971\nC^\t\u000b\u0004ra\tC4\t{#\t\rE\u0002\u0015\t\u007f#q!!$\u00052\n\u0007q\u0003E\u0002\u0015\t\u0007$q!a\u001e\u00052\n\u0007q\u0003E\u0004h\tc#i\f\"1\t\u0017\u0005EE\u0011\u0017B\u0001B\u0003%A\u0011\u001a\t\u0007\u0019\u0005\u001d3\u0003\"0\t\u0017\u00115G\u0011\u0017B\u0001B\u0003%AqZ\u0001\u0004a\n4\u0007#\u0002\u0007\u0005\u0000\u0011m\u0006BCB%\tc\u0013)\u0019)C\t\u000f\"Q1q\u000fCY\u0005\u0003\u0005\u000b\u0011\u0002%\t\u000f\u0011$\t\f\"\u0001\u0005XRAAQ\u0019Cm\t7$i\u000e\u0003\u0005\u0002\u0012\u0012U\u0007\u0019\u0001Ce\u0011!!i\r\"6A\u0002\u0011=\u0007bBB%\t+\u0004\r\u0001\u0013\u0005\u000b\u0007\u000b#\t\f1A\u0005\u0002\u0011\u0005XC\u0001C^\u0011)\u0019Y\t\"-A\u0002\u0013\u0005AQ\u001d\u000b\u0004{\u0011\u001d\b\"\u00039\u0005d\u0006\u0005\t\u0019\u0001C^\u0011%\u0019\u0019\n\"-!B\u0013!Y\f\u000b\u0003\u0005j\u000e]\u0005\u0002CBP\tc#\t\u0001b<\u0015\u0007u\"\t\u0010\u0003\u0005\u0004&\u00125\b\u0019\u0001Cz!\u0015a1\u0011\u0016C^\u0011%\u0019y\u000b\"-!\n#!9\u0010\u0006\u0003\u0005F\u0012e\b\u0002CA\"\tk\u0004\ra!.\t\u0011\r\u0015G\u0011\u0017C!\t{$2!\u0010C\u0000\u0011!\ty\nb?A\u0002\u0011\u0015g\u0001CC\u0002\u0001\u0001\u0006\t\"\"\u0002\u0003\u0019M\u000bW.Z#mK6,g\u000e^:\u0016\t\u0015\u001dQqB\n\u0006\u000b\u0003YQ\u0011\u0002\t\u0007O\u000e=b/b\u0003\u0011\u000b\u001d,\t!\"\u0004\u0011\u0007Q)y\u0001\u0002\u0005\u0002D\u0016\u0005!\u0019AAc\u0011)\u0019I%\"\u0001\u0003\u0006\u0004&\tb\u0012\u0005\u000b\u0007o*\tA!A!\u0002\u0013A\u0005bCC\f\u000b\u0003\u0011)\u0019!C\u0001\u000b3\t\u0001b\u001c;iKJ\u0004\u0018\u000e^\u000b\u0003\u000b7\u0001BaI%\u0006\u000e!YQqDC\u0001\u0005\u0003\u0005\u000b\u0011BC\u000e\u0003%yG\u000f[3sa&$\b\u0005C\u0004e\u000b\u0003!\t!b\t\u0015\r\u0015-QQEC\u0014\u0011\u001d\u0019I%\"\tA\u0002!C\u0001\"b\u0006\u0006\"\u0001\u0007Q1\u0004\u0005\n\u0007\u000b+\t\u00011A\u0005\u0002UD!ba#\u0006\u0002\u0001\u0007I\u0011AC\u0017)\riTq\u0006\u0005\ta\u0016-\u0012\u0011!a\u0001m\"A11SC\u0001A\u0003&a\u000f\u000b\u0003\u00062\r]\u0005\u0002CBP\u000b\u0003!\t!b\u000e\u0015\u0007u*I\u0004\u0003\u0005\u0004&\u0016U\u0002\u0019AC\u001e!\u0011a1\u0011\u0016<\t\u0013\r=V\u0011\u0001Q\u0005\u0012\u0015}Bc\u0001\r\u0006B!A\u00111IC\u001f\u0001\u0004\u0019)\f\u0003\u0005\u0002\f\u0015\u0005A\u0011IC#+\t)9\u0005\u0005\u00032i\u0015%\u0003CB\u0012\u0004@Z,Y\u0001\u0003\u0005\u0004F\u0016\u0005A\u0011IC')\riTq\n\u0005\t\u0003?+Y\u00051\u0001\u0006\f!91QZC\u0001\t\u0003*h\u0001CC+\u0001\u0001\u0006\t\"b\u0016\u0003\u000fU\u0003H-\u0019;fIV1Q\u0011LC1\u000bK\u001aR!b\u0015\f\u000b7\u0002raZB&\u000b;*9\u0007E\u0004$\tO*y&b\u0019\u0011\u0007Q)\t\u0007\u0002\u0005\u0002D\u0016M#\u0019AAc!\r!RQ\r\u0003\b\u0003o*\u0019F1\u0001\u0018!\u001d9W1KC0\u000bGB!\u0002b\t\u0006T\t\u0005\t\u0015!\u0003U\u0011-\u0011)$b\u0015\u0003\u0002\u0003\u0006I!b\u0018\t\u0017\u00115W1\u000bB\u0001B\u0003%Qq\u000e\t\bG\u0015ETqLC2\u0013\r)\u0019H\u0001\u0002\u0010\u0007>l'-\u001b8fe\u001a\u000b7\r^8ss\"Q1\u0011JC*\u0005\u000b\u0007K\u0011C$\t\u0015\r]T1\u000bB\u0001B\u0003%\u0001\nC\u0004e\u000b'\"\t!b\u001f\u0015\u0015\u0015\u001dTQPC@\u000b\u0003+\u0019\tC\u0004\u0005$\u0015e\u0004\u0019\u0001+\t\u0011\tUR\u0011\u0010a\u0001\u000b?B\u0001\u0002\"4\u0006z\u0001\u0007Qq\u000e\u0005\b\u0007\u0013*I\b1\u0001I\u0011)\u0019))b\u0015A\u0002\u0013\u0005QqQ\u000b\u0003\u000b;B!ba#\u0006T\u0001\u0007I\u0011ACF)\riTQ\u0012\u0005\na\u0016%\u0015\u0011!a\u0001\u000b;B\u0011ba%\u0006T\u0001\u0006K!\"\u0018)\t\u0015=5q\u0013\u0005\t\u0007?+\u0019\u0006\"\u0001\u0006\u0016R\u0019Q(b&\t\u0011\r\u0015V1\u0013a\u0001\u000b3\u0003R\u0001DBU\u000b;B\u0011ba,\u0006T\u0001&\t\"\"(\u0015\u0007a)y\n\u0003\u0005\u0002D\u0015m\u0005\u0019AB[\u0011!\tY!b\u0015\u0005B\u0015\rVCACS!\u0011\tD'b*\u0011\u000f\r\u001ay,\"\u0018\u0006h!A1QYC*\t\u0003*Y\u000bF\u0002>\u000b[C\u0001\"a(\u0006*\u0002\u0007Qq\r\u0005\b\u0007\u001b,\u0019\u0006\"\u0011v\r!)\u0019\f\u0001Q\u0001\u0012\u0015U&a\u0001.jaVAQqWCa\u000b\u000b,ImE\u0003\u00062.)I\fE\u0004h\u0007\u0017*Y,b3\u0011\u000f\r\"9'\"0\u0006HB9ABa&\u0006@\u0016\r\u0007c\u0001\u000b\u0006B\u0012A\u00111YCY\u0005\u0004\t)\rE\u0002\u0015\u000b\u000b$q!!$\u00062\n\u0007q\u0003E\u0002\u0015\u000b\u0013$q!a\u001e\u00062\n\u0007q\u0003E\u0005h\u000bc+y,b1\u0006H\"Q!qPCY\u0005\u0003\u0005\u000b\u0011\u0002+\t\u0017\u0015EW\u0011\u0017B\u0001B\u0003%Q1[\u0001\u0003G\u001a\u0004raIC9\u000b{+9\r\u0003\u0006\u0004J\u0015E&Q1Q\u0005\u0012\u001dC!ba\u001e\u00062\n\u0005\t\u0015!\u0003I\u0011-)9\"\"-\u0003\u0006\u0004%\t!b7\u0016\u0005\u0015u\u0007\u0003B\u0012J\u000b\u0007D1\"b\b\u00062\n\u0005\t\u0015!\u0003\u0006^\"9A-\"-\u0005\u0002\u0015\rHCCCf\u000bK,9/\";\u0006l\"9!qPCq\u0001\u0004!\u0006\u0002CCi\u000bC\u0004\r!b5\t\u000f\r%S\u0011\u001da\u0001\u0011\"AQqCCq\u0001\u0004)i\u000e\u0003\u0006\u0004\u0006\u0016E\u0006\u0019!C\u0001\u000b_,\"!\"=\u0011\t\u0015MXQ_\u0007\u0003\u000bcKA!b>\u0004@\n1!+Z:vYRD!ba#\u00062\u0002\u0007I\u0011AC~)\riTQ \u0005\na\u0016e\u0018\u0011!a\u0001\u000bcD\u0011ba%\u00062\u0002\u0006K!\"=)\t\u0015}8q\u0013\u0005\t\u0007?+\t\f\"\u0001\u0007\u0006Q\u0019QHb\u0002\t\u0011\r\u0015f1\u0001a\u0001\r\u0013\u0001R\u0001DBU\u000bcD\u0011ba,\u00062\u0002&\tB\"\u0004\u0015\u0007a1y\u0001\u0003\u0005\u0002D\u0019-\u0001\u0019AB[\u0011!\tY!\"-\u0005B\u0019MQC\u0001D\u000b!\u0015\u0001\u0012\u0011CCf\u0011!\u0019)-\"-\u0005B\u0019eAcA\u001f\u0007\u001c!A\u0011q\u0014D\f\u0001\u0004)YM\u0002\u0005\u0007 \u0001\u0001\u000b\u0011\u0003D\u0011\u0005-\u0019uN\u001d:fgB|g\u000eZ:\u0016\t\u0019\rb1F\n\u0006\r;YaQ\u0005\t\u0007O\u000e=bOb\n\u0011\u000b\u001d4iB\"\u000b\u0011\u0007Q1Y\u0003B\u0004\u0002\u000e\u001au!\u0019A\f\t\u0017\u0019=bQ\u0004B\u0001B\u0003%a\u0011G\u0001\u0005G>\u0014(\u000fE\u0004\r\u0005k\u001bb\u0011\u0006<\t\u0015\r%cQ\u0004BCB\u0013Eq\t\u0003\u0006\u0004x\u0019u!\u0011!Q\u0001\n!C1\"b\u0006\u0007\u001e\t\u0015\r\u0011\"\u0001\u0007:U\u0011a1\b\t\u0005G%3I\u0003C\u0006\u0006 \u0019u!\u0011!Q\u0001\n\u0019m\u0002b\u00023\u0007\u001e\u0011\u0005a\u0011\t\u000b\t\rO1\u0019E\"\u0012\u0007H!Aaq\u0006D \u0001\u00041\t\u0004C\u0004\u0004J\u0019}\u0002\u0019\u0001%\t\u0011\u0015]aq\ba\u0001\rwA\u0011b!\"\u0007\u001e\u0001\u0007I\u0011A;\t\u0015\r-eQ\u0004a\u0001\n\u00031i\u0005F\u0002>\r\u001fB\u0001\u0002\u001dD&\u0003\u0003\u0005\rA\u001e\u0005\t\u0007'3i\u0002)Q\u0005m\"\"a\u0011KBL\u0011!\u0019yJ\"\b\u0005\u0002\u0019]CcA\u001f\u0007Z!A1Q\u0015D+\u0001\u0004)Y\u0004C\u0005\u00040\u001au\u0001\u0015\"\u0005\u0007^Q\u0019\u0001Db\u0018\t\u0011\u0005\rc1\fa\u0001\u0007kC\u0001\"a\u0003\u0007\u001e\u0011\u0005c1M\u000b\u0003\rK\u0002B!\r\u001b\u0007hA11ea0w\rOA\u0001b!2\u0007\u001e\u0011\u0005c1\u000e\u000b\u0004{\u00195\u0004\u0002CAP\rS\u0002\rAb\n\t\u000f\r5gQ\u0004C!k\"qa1\u000f\u0001\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0007v\u0019E\u0015!C:va\u0016\u0014HE_5q+!19Hb\"\u0007\f\u001auD\u0003\u0002D=\r\u001b#BAb\u001f\u0007\u0000A\u0019AC\" \u0005\u000f\u0005]d\u0011\u000fb\u0001/!A\u00111\u0010D9\u0001\b1\t\tE\u0005\u0002\u0000\u0005\u0015eDb!\u0007|A9ABa&\u0007\u0006\u001a%\u0005c\u0001\u000b\u0007\b\u0012A\u00111\u0019D9\u0005\u0004\t)\rE\u0002\u0015\r\u0017#q!!$\u0007r\t\u0007q\u0003\u0003\u0005\u0002 \u001aE\u0004\u0019\u0001DH!\u0015\u0001\u00121\u0018DE\u0013\r\u0011)i\n\t\u0006G\u0001\u0019b$\u000b")
public interface ParSeqLike<T, Repr extends ParSeq<T>, Sequential extends Seq<T> & SeqLike<T, Sequential>>
extends GenSeqLike<T, Repr>,
ParIterableLike<T, Repr, Sequential> {
    public /* synthetic */ Object scala$collection$parallel$ParSeqLike$$super$zip(GenIterable var1, CanBuildFrom var2);

    @Override
    public SeqSplitter<T> splitter();

    @Override
    public PreciseSplitter<T> iterator();

    @Override
    public int size();

    @Override
    public int segmentLength(Function1<T, Object> var1, int var2);

    @Override
    public int indexWhere(Function1<T, Object> var1, int var2);

    @Override
    public int lastIndexWhere(Function1<T, Object> var1, int var2);

    @Override
    public Repr reverse();

    @Override
    public <S, That> That reverseMap(Function1<T, S> var1, CanBuildFrom<Repr, S, That> var2);

    @Override
    public <S> boolean startsWith(GenSeq<S> var1, int var2);

    @Override
    public <U> boolean sameElements(GenIterable<U> var1);

    @Override
    public <S> boolean endsWith(GenSeq<S> var1);

    @Override
    public <U, That> That patch(int var1, GenSeq<U> var2, int var3, CanBuildFrom<Repr, U, That> var4);

    @Override
    public <U, That> That updated(int var1, U var2, CanBuildFrom<Repr, U, That> var3);

    @Override
    public <U, That> That $plus$colon(U var1, CanBuildFrom<Repr, U, That> var2);

    @Override
    public <U, That> That $colon$plus(U var1, CanBuildFrom<Repr, U, That> var2);

    @Override
    public <U, That> That padTo(int var1, U var2, CanBuildFrom<Repr, U, That> var3);

    @Override
    public <U, S, That> That zip(GenIterable<S> var1, CanBuildFrom<Repr, Tuple2<U, S>, That> var2);

    @Override
    public <S> boolean corresponds(GenSeq<S> var1, Function2<T, S, Object> var2);

    @Override
    public <U> Repr diff(GenSeq<U> var1);

    @Override
    public <U> Repr intersect(GenSeq<U> var1);

    @Override
    public Repr distinct();

    @Override
    public String toString();

    @Override
    public ParSeq<T> toSeq();

    @Override
    public Object view();

    public SeqSplitter<T> down(IterableSplitter<?> var1);

    public class Zip<U, S, That>
    implements Transformer<Combiner<Tuple2<U, S>, That>, Zip<U, S, That>> {
        private final int len;
        private final CombinerFactory<Tuple2<U, S>, That> cf;
        private final SeqSplitter<T> pit;
        private final SeqSplitter<S> otherpit;
        private volatile Combiner<Tuple2<U, S>, That> result;
        public final /* synthetic */ ParSeqLike $outer;
        private volatile Throwable throwable;

        @Override
        public /* synthetic */ String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
            return super.toString();
        }

        @Override
        public boolean shouldSplitFurther() {
            return ParIterableLike$Accessor$class.shouldSplitFurther(this);
        }

        @Override
        public void signalAbort() {
            ParIterableLike$Accessor$class.signalAbort(this);
        }

        @Override
        public String toString() {
            return ParIterableLike$Accessor$class.toString(this);
        }

        @Override
        public boolean requiresStrictSplitters() {
            return ParIterableLike$StrictSplitterCheckTask$class.requiresStrictSplitters(this);
        }

        @Override
        public Throwable throwable() {
            return this.throwable;
        }

        @Override
        public void throwable_$eq(Throwable x$1) {
            this.throwable = x$1;
        }

        @Override
        public Object repr() {
            return Task$class.repr(this);
        }

        @Override
        public void forwardThrowable() {
            Task$class.forwardThrowable(this);
        }

        @Override
        public void tryLeaf(Option<Combiner<Tuple2<U, S>, That>> lastres) {
            Task$class.tryLeaf(this, lastres);
        }

        @Override
        public void tryMerge(Object t) {
            Task$class.tryMerge(this, t);
        }

        @Override
        public void mergeThrowables(Task<?, ?> that) {
            Task$class.mergeThrowables(this, that);
        }

        @Override
        public SeqSplitter<T> pit() {
            return this.pit;
        }

        public SeqSplitter<S> otherpit() {
            return this.otherpit;
        }

        @Override
        public Combiner<Tuple2<U, S>, That> result() {
            return this.result;
        }

        @Override
        public void result_$eq(Combiner<Tuple2<U, S>, That> x$1) {
            this.result = x$1;
        }

        @Override
        public void leaf(Option<Combiner<Tuple2<U, S>, That>> prev) {
            this.result_$eq(this.pit().zip2combiner(this.otherpit(), this.cf.apply()));
        }

        public Nothing$ newSubtask(IterableSplitter<T> p) {
            return package$.MODULE$.unsupported();
        }

        @Override
        public Seq<Zip<U, S, That>> split() {
            int fp = this.len / 2;
            int sp2 = this.len - this.len / 2;
            Seq pits = this.pit().psplitWithSignalling(Predef$.MODULE$.wrapIntArray(new int[]{fp, sp2}));
            Seq<SeqSplitter<S>> opits = this.otherpit().psplitWithSignalling(Predef$.MODULE$.wrapIntArray(new int[]{fp, sp2}));
            return (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Zip[]{new Zip<U, S, That>(this.scala$collection$parallel$ParSeqLike$Zip$$$outer(), fp, this.cf, (SeqSplitter)pits.apply(false), (SeqSplitter)opits.apply(false)), new Zip<U, S, That>(this.scala$collection$parallel$ParSeqLike$Zip$$$outer(), sp2, this.cf, (SeqSplitter)pits.apply(true), (SeqSplitter)opits.apply(true))}));
        }

        @Override
        public void merge(Zip<U, S, That> that) {
            this.result_$eq(this.result().combine(that.result()));
        }

        public /* synthetic */ ParSeqLike scala$collection$parallel$ParSeqLike$Zip$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$Zip$$$outer();
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$Zip$$$outer();
        }

        public Zip(ParSeqLike<T, Repr, Sequential> $outer, int len, CombinerFactory<Tuple2<U, S>, That> cf, SeqSplitter<T> pit, SeqSplitter<S> otherpit) {
            this.len = len;
            this.cf = cf;
            this.pit = pit;
            this.otherpit = otherpit;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            ParIterableLike$StrictSplitterCheckTask$class.$init$(this);
            ParIterableLike$Accessor$class.$init$(this);
            this.result = null;
        }
    }

    public class Reverse<U, This>
    implements Transformer<Combiner<U, This>, Reverse<U, This>> {
        private final Function0<Combiner<U, This>> cbf;
        private final SeqSplitter<T> pit;
        private volatile Combiner<U, This> result;
        public final /* synthetic */ ParSeqLike $outer;
        private volatile Throwable throwable;

        @Override
        public /* synthetic */ String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
            return super.toString();
        }

        @Override
        public boolean shouldSplitFurther() {
            return ParIterableLike$Accessor$class.shouldSplitFurther(this);
        }

        @Override
        public Seq<Task<Combiner<U, This>, Reverse<U, This>>> split() {
            return ParIterableLike$Accessor$class.split(this);
        }

        @Override
        public void signalAbort() {
            ParIterableLike$Accessor$class.signalAbort(this);
        }

        @Override
        public String toString() {
            return ParIterableLike$Accessor$class.toString(this);
        }

        @Override
        public boolean requiresStrictSplitters() {
            return ParIterableLike$StrictSplitterCheckTask$class.requiresStrictSplitters(this);
        }

        @Override
        public Throwable throwable() {
            return this.throwable;
        }

        @Override
        public void throwable_$eq(Throwable x$1) {
            this.throwable = x$1;
        }

        @Override
        public Object repr() {
            return Task$class.repr(this);
        }

        @Override
        public void forwardThrowable() {
            Task$class.forwardThrowable(this);
        }

        @Override
        public void tryLeaf(Option<Combiner<U, This>> lastres) {
            Task$class.tryLeaf(this, lastres);
        }

        @Override
        public void tryMerge(Object t) {
            Task$class.tryMerge(this, t);
        }

        @Override
        public void mergeThrowables(Task<?, ?> that) {
            Task$class.mergeThrowables(this, that);
        }

        @Override
        public SeqSplitter<T> pit() {
            return this.pit;
        }

        @Override
        public Combiner<U, This> result() {
            return this.result;
        }

        @Override
        public void result_$eq(Combiner<U, This> x$1) {
            this.result = x$1;
        }

        @Override
        public void leaf(Option<Combiner<U, This>> prev) {
            this.result_$eq(this.pit().reverse2combiner(this.scala$collection$parallel$ParSeqLike$Reverse$$$outer().reuse(prev, this.cbf.apply())));
        }

        @Override
        public Reverse<U, This> newSubtask(IterableSplitter<T> p) {
            return new Reverse<U, This>(this.scala$collection$parallel$ParSeqLike$Reverse$$$outer(), this.cbf, this.scala$collection$parallel$ParSeqLike$Reverse$$$outer().down(p));
        }

        @Override
        public void merge(Reverse<U, This> that) {
            this.result_$eq(that.result().combine(this.result()));
        }

        public /* synthetic */ ParSeqLike scala$collection$parallel$ParSeqLike$Reverse$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$Reverse$$$outer();
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$Reverse$$$outer();
        }

        public Reverse(ParSeqLike<T, Repr, Sequential> $outer, Function0<Combiner<U, This>> cbf, SeqSplitter<T> pit) {
            this.cbf = cbf;
            this.pit = pit;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            ParIterableLike$StrictSplitterCheckTask$class.$init$(this);
            ParIterableLike$Accessor$class.$init$(this);
            this.result = null;
        }
    }

    public class Updated<U, That>
    implements Transformer<Combiner<U, That>, Updated<U, That>> {
        public final int scala$collection$parallel$ParSeqLike$Updated$$pos;
        public final U scala$collection$parallel$ParSeqLike$Updated$$elem;
        public final CombinerFactory<U, That> scala$collection$parallel$ParSeqLike$Updated$$pbf;
        private final SeqSplitter<T> pit;
        private volatile Combiner<U, That> result;
        public final /* synthetic */ ParSeqLike $outer;
        private volatile Throwable throwable;

        @Override
        public /* synthetic */ String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
            return super.toString();
        }

        @Override
        public boolean shouldSplitFurther() {
            return ParIterableLike$Accessor$class.shouldSplitFurther(this);
        }

        @Override
        public void signalAbort() {
            ParIterableLike$Accessor$class.signalAbort(this);
        }

        @Override
        public String toString() {
            return ParIterableLike$Accessor$class.toString(this);
        }

        @Override
        public Throwable throwable() {
            return this.throwable;
        }

        @Override
        public void throwable_$eq(Throwable x$1) {
            this.throwable = x$1;
        }

        @Override
        public Object repr() {
            return Task$class.repr(this);
        }

        @Override
        public void forwardThrowable() {
            Task$class.forwardThrowable(this);
        }

        @Override
        public void tryLeaf(Option<Combiner<U, That>> lastres) {
            Task$class.tryLeaf(this, lastres);
        }

        @Override
        public void tryMerge(Object t) {
            Task$class.tryMerge(this, t);
        }

        @Override
        public void mergeThrowables(Task<?, ?> that) {
            Task$class.mergeThrowables(this, that);
        }

        @Override
        public SeqSplitter<T> pit() {
            return this.pit;
        }

        @Override
        public Combiner<U, That> result() {
            return this.result;
        }

        @Override
        public void result_$eq(Combiner<U, That> x$1) {
            this.result = x$1;
        }

        @Override
        public void leaf(Option<Combiner<U, That>> prev) {
            this.result_$eq(this.pit().updated2combiner(this.scala$collection$parallel$ParSeqLike$Updated$$pos, this.scala$collection$parallel$ParSeqLike$Updated$$elem, this.scala$collection$parallel$ParSeqLike$Updated$$pbf.apply()));
        }

        public Nothing$ newSubtask(IterableSplitter<T> p) {
            return package$.MODULE$.unsupported();
        }

        @Override
        public Seq<Task<Combiner<U, That>, Updated<U, That>>> split() {
            Seq pits = this.pit().splitWithSignalling();
            return ((TraversableLike)pits.zip(pits.scanLeft(BoxesRunTime.boxToInteger(0), new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final int apply(int x$26, SeqSplitter<T> x$27) {
                    return x$26 + x$27.remaining();
                }
            }, Seq$.MODULE$.canBuildFrom()), Seq$.MODULE$.canBuildFrom())).withFilter(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Tuple2<SeqSplitter<T>, Object> check$ifrefutable$6) {
                    boolean bl = check$ifrefutable$6 != null;
                    return bl;
                }
            }).map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Updated $outer;

                public final Updated<U, That> apply(Tuple2<SeqSplitter<T>, Object> x$28) {
                    if (x$28 != null) {
                        return new Updated<U, That>(this.$outer.scala$collection$parallel$ParSeqLike$Updated$$$outer(), this.$outer.scala$collection$parallel$ParSeqLike$Updated$$pos - x$28._2$mcI$sp(), this.$outer.scala$collection$parallel$ParSeqLike$Updated$$elem, this.$outer.scala$collection$parallel$ParSeqLike$Updated$$pbf, x$28._1());
                    }
                    throw new MatchError(x$28);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Seq$.MODULE$.canBuildFrom());
        }

        @Override
        public void merge(Updated<U, That> that) {
            this.result_$eq(this.result().combine(that.result()));
        }

        @Override
        public boolean requiresStrictSplitters() {
            return true;
        }

        public /* synthetic */ ParSeqLike scala$collection$parallel$ParSeqLike$Updated$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$Updated$$$outer();
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$Updated$$$outer();
        }

        public Updated(ParSeqLike<T, Repr, Sequential> $outer, int pos, U elem, CombinerFactory<U, That> pbf, SeqSplitter<T> pit) {
            this.scala$collection$parallel$ParSeqLike$Updated$$pos = pos;
            this.scala$collection$parallel$ParSeqLike$Updated$$elem = elem;
            this.scala$collection$parallel$ParSeqLike$Updated$$pbf = pbf;
            this.pit = pit;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            ParIterableLike$StrictSplitterCheckTask$class.$init$(this);
            ParIterableLike$Accessor$class.$init$(this);
            this.result = null;
        }
    }

    public abstract class Elements
    implements SeqSplitter<T>,
    BufferedIterator<T> {
        public final int scala$collection$parallel$ParSeqLike$Elements$$start;
        private final int end;
        private int scala$collection$parallel$ParSeqLike$Elements$$i;
        public final /* synthetic */ ParSeqLike $outer;
        private Signalling signalDelegate;

        @Override
        public BufferedIterator<T> buffered() {
            return BufferedIterator$class.buffered(this);
        }

        @Override
        public Seq<SeqSplitter<T>> splitWithSignalling() {
            return SeqSplitter$class.splitWithSignalling(this);
        }

        @Override
        public Seq<SeqSplitter<T>> psplitWithSignalling(Seq<Object> sizes) {
            return SeqSplitter$class.psplitWithSignalling(this, sizes);
        }

        @Override
        public SeqSplitter.Taken newTaken(int until2) {
            return SeqSplitter$class.newTaken(this, until2);
        }

        @Override
        public SeqSplitter<T> take(int n) {
            return SeqSplitter$class.take(this, n);
        }

        @Override
        public SeqSplitter<T> slice(int from1, int until1) {
            return SeqSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> SeqSplitter.Mapped<S> map(Function1<T, S> f) {
            return SeqSplitter$class.map(this, f);
        }

        @Override
        public <U, PI extends SeqSplitter<U>> SeqSplitter.Appended<U, PI> appendParSeq(PI that) {
            return SeqSplitter$class.appendParSeq(this, that);
        }

        @Override
        public <S> SeqSplitter.Zipped<S> zipParSeq(SeqSplitter<S> that) {
            return SeqSplitter$class.zipParSeq(this, that);
        }

        @Override
        public <S, U, R> SeqSplitter.ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
            return SeqSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
        }

        @Override
        public SeqSplitter<T> reverse() {
            return SeqSplitter$class.reverse(this);
        }

        @Override
        public <U> SeqSplitter.Patched<U> patchParSeq(int from2, SeqSplitter<U> patchElems, int replaced) {
            return SeqSplitter$class.patchParSeq(this, from2, patchElems, replaced);
        }

        @Override
        public int prefixLength(Function1<T, Object> pred) {
            return AugmentedSeqIterator$class.prefixLength(this, pred);
        }

        @Override
        public int indexWhere(Function1<T, Object> pred) {
            return AugmentedSeqIterator$class.indexWhere(this, pred);
        }

        @Override
        public int lastIndexWhere(Function1<T, Object> pred) {
            return AugmentedSeqIterator$class.lastIndexWhere(this, pred);
        }

        @Override
        public <S> boolean corresponds(Function2<T, S, Object> corr, Iterator<S> that) {
            return AugmentedSeqIterator$class.corresponds(this, corr, that);
        }

        @Override
        public <U, This> Combiner<U, This> reverse2combiner(Combiner<U, This> cb) {
            return AugmentedSeqIterator$class.reverse2combiner(this, cb);
        }

        @Override
        public <S, That> Combiner<S, That> reverseMap2combiner(Function1<T, S> f, Combiner<S, That> cb) {
            return AugmentedSeqIterator$class.reverseMap2combiner(this, f, cb);
        }

        @Override
        public <U, That> Combiner<U, That> updated2combiner(int index, U elem, Combiner<U, That> cb) {
            return AugmentedSeqIterator$class.updated2combiner(this, index, elem, cb);
        }

        @Override
        public Signalling signalDelegate() {
            return this.signalDelegate;
        }

        @Override
        public void signalDelegate_$eq(Signalling x$1) {
            this.signalDelegate = x$1;
        }

        @Override
        public <S> boolean shouldSplitFurther(ParIterable<S> coll, int parallelismLevel) {
            return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
        }

        @Override
        public String buildString(Function1<Function1<String, BoxedUnit>, BoxedUnit> closure) {
            return IterableSplitter$class.buildString(this, closure);
        }

        @Override
        public String debugInformation() {
            return IterableSplitter$class.debugInformation(this);
        }

        @Override
        public <U extends IterableSplitter.Taken> U newSliceInternal(U it, int from1) {
            return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
        }

        @Override
        public <U, PI extends IterableSplitter<U>> IterableSplitter.Appended<U, PI> appendParIterable(PI that) {
            return IterableSplitter$class.appendParIterable(this, that);
        }

        @Override
        public boolean isAborted() {
            return DelegatedSignalling$class.isAborted(this);
        }

        @Override
        public void abort() {
            DelegatedSignalling$class.abort(this);
        }

        @Override
        public int indexFlag() {
            return DelegatedSignalling$class.indexFlag(this);
        }

        @Override
        public void setIndexFlag(int f) {
            DelegatedSignalling$class.setIndexFlag(this, f);
        }

        @Override
        public void setIndexFlagIfGreater(int f) {
            DelegatedSignalling$class.setIndexFlagIfGreater(this, f);
        }

        @Override
        public void setIndexFlagIfLesser(int f) {
            DelegatedSignalling$class.setIndexFlagIfLesser(this, f);
        }

        @Override
        public int tag() {
            return DelegatedSignalling$class.tag(this);
        }

        @Override
        public int count(Function1<T, Object> p) {
            return AugmentedIterableIterator$class.count(this, p);
        }

        @Override
        public <U> U reduce(Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.reduce(this, op);
        }

        @Override
        public <U> U fold(U z, Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.fold(this, z, op);
        }

        @Override
        public <U> U sum(Numeric<U> num) {
            return (U)AugmentedIterableIterator$class.sum(this, num);
        }

        @Override
        public <U> U product(Numeric<U> num) {
            return (U)AugmentedIterableIterator$class.product(this, num);
        }

        @Override
        public <U> T min(Ordering<U> ord) {
            return AugmentedIterableIterator$class.min(this, ord);
        }

        @Override
        public <U> T max(Ordering<U> ord) {
            return AugmentedIterableIterator$class.max(this, ord);
        }

        @Override
        public <U> void copyToArray(Object array, int from2, int len) {
            AugmentedIterableIterator$class.copyToArray(this, array, from2, len);
        }

        @Override
        public <U> U reduceLeft(int howmany, Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
        }

        @Override
        public <S, That> Combiner<S, That> map2combiner(Function1<T, S> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.map2combiner(this, f, cb);
        }

        @Override
        public <S, That> Combiner<S, That> collect2combiner(PartialFunction<T, S> pf, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
        }

        @Override
        public <S, That> Combiner<S, That> flatmap2combiner(Function1<T, GenTraversableOnce<S>> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
        }

        @Override
        public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Bld b) {
            return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
        }

        @Override
        public <U, This> Combiner<U, This> filter2combiner(Function1<T, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Combiner<U, This> filterNot2combiner(Function1<T, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1<T, Object> pred, Combiner<U, This> btrue, Combiner<U, This> bfalse) {
            return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
        }

        @Override
        public <U, This> Combiner<U, This> take2combiner(int n, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.take2combiner(this, n, cb);
        }

        @Override
        public <U, This> Combiner<U, This> drop2combiner(int n, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
        }

        @Override
        public <U, This> Combiner<U, This> slice2combiner(int from2, int until2, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.slice2combiner(this, from2, until2, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner<U, This> before, Combiner<U, This> after) {
            return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1<T, Object> p, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1<T, Object> p, Combiner<U, This> before, Combiner<U, This> after) {
            return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
        }

        @Override
        public <U, A> void scanToArray(U z, Function2<U, U, U> op, Object array, int from2) {
            AugmentedIterableIterator$class.scanToArray(this, z, op, array, from2);
        }

        @Override
        public <U, That> Combiner<U, That> scanToCombiner(U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
            return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
        }

        @Override
        public <U, That> Combiner<U, That> scanToCombiner(int howmany, U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
            return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
        }

        @Override
        public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator<S> otherpit, Combiner<Tuple2<U, S>, That> cb) {
            return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
        }

        @Override
        public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator<S> that, U thiselem, S thatelem, Combiner<Tuple2<U, S>, That> cb) {
            return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
        }

        @Override
        public boolean isRemainingCheap() {
            return RemainsIterator$class.isRemainingCheap(this);
        }

        @Override
        public Iterator<T> seq() {
            return Iterator$class.seq(this);
        }

        @Override
        public boolean isEmpty() {
            return Iterator$class.isEmpty(this);
        }

        @Override
        public boolean isTraversableAgain() {
            return Iterator$class.isTraversableAgain(this);
        }

        @Override
        public boolean hasDefiniteSize() {
            return Iterator$class.hasDefiniteSize(this);
        }

        @Override
        public Iterator<T> drop(int n) {
            return Iterator$class.drop(this, n);
        }

        @Override
        public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
            return Iterator$class.$plus$plus(this, that);
        }

        @Override
        public <B> Iterator<B> flatMap(Function1<T, GenTraversableOnce<B>> f) {
            return Iterator$class.flatMap(this, f);
        }

        @Override
        public Iterator<T> filter(Function1<T, Object> p) {
            return Iterator$class.filter(this, p);
        }

        @Override
        public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<T, B, Object> p) {
            return Iterator$class.corresponds(this, that, p);
        }

        @Override
        public Iterator<T> withFilter(Function1<T, Object> p) {
            return Iterator$class.withFilter(this, p);
        }

        @Override
        public Iterator<T> filterNot(Function1<T, Object> p) {
            return Iterator$class.filterNot(this, p);
        }

        @Override
        public <B> Iterator<B> collect(PartialFunction<T, B> pf) {
            return Iterator$class.collect(this, pf);
        }

        @Override
        public <B> Iterator<B> scanLeft(B z, Function2<B, T, B> op) {
            return Iterator$class.scanLeft(this, z, op);
        }

        @Override
        public <B> Iterator<B> scanRight(B z, Function2<T, B, B> op) {
            return Iterator$class.scanRight(this, z, op);
        }

        @Override
        public Iterator<T> takeWhile(Function1<T, Object> p) {
            return Iterator$class.takeWhile(this, p);
        }

        @Override
        public Tuple2<Iterator<T>, Iterator<T>> partition(Function1<T, Object> p) {
            return Iterator$class.partition(this, p);
        }

        @Override
        public Tuple2<Iterator<T>, Iterator<T>> span(Function1<T, Object> p) {
            return Iterator$class.span(this, p);
        }

        @Override
        public Iterator<T> dropWhile(Function1<T, Object> p) {
            return Iterator$class.dropWhile(this, p);
        }

        @Override
        public <B> Iterator<Tuple2<T, B>> zip(Iterator<B> that) {
            return Iterator$class.zip(this, that);
        }

        @Override
        public <A1> Iterator<A1> padTo(int len, A1 elem) {
            return Iterator$class.padTo(this, len, elem);
        }

        @Override
        public Iterator<Tuple2<T, Object>> zipWithIndex() {
            return Iterator$class.zipWithIndex(this);
        }

        @Override
        public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
            return Iterator$class.zipAll(this, that, thisElem, thatElem);
        }

        @Override
        public <U> void foreach(Function1<T, U> f) {
            Iterator$class.foreach(this, f);
        }

        @Override
        public boolean forall(Function1<T, Object> p) {
            return Iterator$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<T, Object> p) {
            return Iterator$class.exists(this, p);
        }

        @Override
        public boolean contains(Object elem) {
            return Iterator$class.contains(this, elem);
        }

        @Override
        public Option<T> find(Function1<T, Object> p) {
            return Iterator$class.find(this, p);
        }

        @Override
        public <B> int indexOf(B elem) {
            return Iterator$class.indexOf(this, elem);
        }

        @Override
        public <B> Iterator.GroupedIterator<B> grouped(int size2) {
            return Iterator$class.grouped(this, size2);
        }

        @Override
        public <B> Iterator.GroupedIterator<B> sliding(int size2, int step) {
            return Iterator$class.sliding(this, size2, step);
        }

        @Override
        public int length() {
            return Iterator$class.length(this);
        }

        @Override
        public Tuple2<Iterator<T>, Iterator<T>> duplicate() {
            return Iterator$class.duplicate(this);
        }

        @Override
        public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
            return Iterator$class.patch(this, from2, patchElems, replaced);
        }

        @Override
        public boolean sameElements(Iterator<?> that) {
            return Iterator$class.sameElements(this, that);
        }

        @Override
        public Traversable<T> toTraversable() {
            return Iterator$class.toTraversable(this);
        }

        @Override
        public Iterator<T> toIterator() {
            return Iterator$class.toIterator(this);
        }

        @Override
        public Stream<T> toStream() {
            return Iterator$class.toStream(this);
        }

        @Override
        public <B> int sliding$default$2() {
            return Iterator$class.sliding$default$2(this);
        }

        @Override
        public List<T> reversed() {
            return TraversableOnce$class.reversed(this);
        }

        @Override
        public int size() {
            return TraversableOnce$class.size(this);
        }

        @Override
        public boolean nonEmpty() {
            return TraversableOnce$class.nonEmpty(this);
        }

        @Override
        public <B> Option<B> collectFirst(PartialFunction<T, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, T, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<T, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, T, B> op) {
            return (B)TraversableOnce$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B foldRight(B z, Function2<T, B, B> op) {
            return (B)TraversableOnce$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, T, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> B reduceRight(Function2<T, B, B> op) {
            return (B)TraversableOnce$class.reduceRight(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, T, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<T, B, B> op) {
            return TraversableOnce$class.reduceRightOption(this, op);
        }

        @Override
        public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
            return TraversableOnce$class.reduceOption(this, op);
        }

        @Override
        public <B> B aggregate(Function0<B> z, Function2<B, T, B> seqop, Function2<B, B, B> combop) {
            return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
        }

        @Override
        public <B> T maxBy(Function1<T, B> f, Ordering<B> cmp) {
            return TraversableOnce$class.maxBy(this, f, cmp);
        }

        @Override
        public <B> T minBy(Function1<T, B> f, Ordering<B> cmp) {
            return TraversableOnce$class.minBy(this, f, cmp);
        }

        @Override
        public <B> void copyToBuffer(Buffer<B> dest) {
            TraversableOnce$class.copyToBuffer(this, dest);
        }

        @Override
        public <B> void copyToArray(Object xs, int start) {
            TraversableOnce$class.copyToArray(this, xs, start);
        }

        @Override
        public <B> void copyToArray(Object xs) {
            TraversableOnce$class.copyToArray(this, xs);
        }

        @Override
        public <B> Object toArray(ClassTag<B> evidence$1) {
            return TraversableOnce$class.toArray(this, evidence$1);
        }

        @Override
        public List<T> toList() {
            return TraversableOnce$class.toList(this);
        }

        @Override
        public Iterable<T> toIterable() {
            return TraversableOnce$class.toIterable(this);
        }

        @Override
        public Seq<T> toSeq() {
            return TraversableOnce$class.toSeq(this);
        }

        @Override
        public IndexedSeq<T> toIndexedSeq() {
            return TraversableOnce$class.toIndexedSeq(this);
        }

        @Override
        public <B> Buffer<B> toBuffer() {
            return TraversableOnce$class.toBuffer(this);
        }

        @Override
        public <B> Set<B> toSet() {
            return TraversableOnce$class.toSet(this);
        }

        @Override
        public Vector<T> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, T, Col> cbf) {
            return (Col)TraversableOnce$class.to(this, cbf);
        }

        @Override
        public <T, U> Map<T, U> toMap(Predef$.less.colon.less<T, Tuple2<T, U>> ev) {
            return TraversableOnce$class.toMap(this, ev);
        }

        @Override
        public String mkString(String start, String sep, String end) {
            return TraversableOnce$class.mkString(this, start, sep, end);
        }

        @Override
        public String mkString(String sep) {
            return TraversableOnce$class.mkString(this, sep);
        }

        @Override
        public String mkString() {
            return TraversableOnce$class.mkString(this);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
            return TraversableOnce$class.addString(this, b, start, sep, end);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String sep) {
            return TraversableOnce$class.addString(this, b, sep);
        }

        @Override
        public StringBuilder addString(StringBuilder b) {
            return TraversableOnce$class.addString(this, b);
        }

        public int end() {
            return this.end;
        }

        public int scala$collection$parallel$ParSeqLike$Elements$$i() {
            return this.scala$collection$parallel$ParSeqLike$Elements$$i;
        }

        private void scala$collection$parallel$ParSeqLike$Elements$$i_$eq(int x$1) {
            this.scala$collection$parallel$ParSeqLike$Elements$$i = x$1;
        }

        @Override
        public boolean hasNext() {
            return this.scala$collection$parallel$ParSeqLike$Elements$$i() < this.end();
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public T next() {
            Nothing$ nothing$;
            if (this.scala$collection$parallel$ParSeqLike$Elements$$i() < this.end()) {
                void var1_1;
                Object x = this.scala$collection$parallel$ParSeqLike$Elements$$$outer().apply(this.scala$collection$parallel$ParSeqLike$Elements$$i());
                this.scala$collection$parallel$ParSeqLike$Elements$$i_$eq(this.scala$collection$parallel$ParSeqLike$Elements$$i() + 1);
                nothing$ = var1_1;
            } else {
                nothing$ = Iterator$.MODULE$.empty().next();
            }
            return nothing$;
        }

        @Override
        public T head() {
            return this.scala$collection$parallel$ParSeqLike$Elements$$$outer().apply(this.scala$collection$parallel$ParSeqLike$Elements$$i());
        }

        @Override
        public final int remaining() {
            return this.end() - this.scala$collection$parallel$ParSeqLike$Elements$$i();
        }

        public Elements dup() {
            return new Elements(this){};
        }

        @Override
        public Seq<SeqSplitter<T>> split() {
            return this.psplit(Predef$.MODULE$.wrapIntArray(new int[]{this.remaining() / 2, this.remaining() - this.remaining() / 2}));
        }

        @Override
        public Seq<SeqSplitter<T>> psplit(Seq<Object> sizes) {
            Seq incr = sizes.scanLeft(BoxesRunTime.boxToInteger(0), new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final int apply(int x$1, int x$2) {
                    return x$1 + x$2;
                }

                public int apply$mcIII$sp(int x$1, int x$2) {
                    return x$1 + x$2;
                }
            }, Seq$.MODULE$.canBuildFrom());
            return ((TraversableLike)((IterableLike)incr.init()).zip((GenIterable)incr.tail(), Seq$.MODULE$.canBuildFrom())).withFilter(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Tuple2<Object, Object> check$ifrefutable$1) {
                    boolean bl = check$ifrefutable$1 != null;
                    return bl;
                }
            }).map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ Elements $outer;

                public final Elements apply(Tuple2<Object, Object> x$3) {
                    if (x$3 != null) {
                        return new Elements(this, x$3){
                            {
                                int n = $outer.$outer.scala$collection$parallel$ParSeqLike$Elements$$start + ((Tuple2)((Object)x1$1))._2$mcI$sp();
                                Predef$ predef$ = Predef$.MODULE$;
                                int n2 = $outer.$outer.end();
                                scala.math.package$ package$2 = scala.math.package$.MODULE$;
                                super($outer.$outer.scala$collection$parallel$ParSeqLike$Elements$$$outer(), $outer.$outer.scala$collection$parallel$ParSeqLike$Elements$$start + ((Tuple2)((Object)x1$1))._1$mcI$sp(), Math.min(n, n2));
                            }
                        };
                    }
                    throw new MatchError(x$3);
                }

                public /* synthetic */ Elements scala$collection$parallel$ParSeqLike$Elements$$anonfun$$$outer() {
                    return this.$outer;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Seq$.MODULE$.canBuildFrom());
        }

        @Override
        public String toString() {
            return new StringBuilder().append((Object)"Elements(").append(BoxesRunTime.boxToInteger(this.scala$collection$parallel$ParSeqLike$Elements$$start)).append((Object)", ").append(BoxesRunTime.boxToInteger(this.end())).append((Object)")").toString();
        }

        public /* synthetic */ ParSeqLike scala$collection$parallel$ParSeqLike$Elements$$$outer() {
            return this.$outer;
        }

        public Elements(ParSeqLike<T, Repr, Sequential> $outer, int start, int end) {
            this.scala$collection$parallel$ParSeqLike$Elements$$start = start;
            this.end = end;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            TraversableOnce$class.$init$(this);
            Iterator$class.$init$(this);
            RemainsIterator$class.$init$(this);
            AugmentedIterableIterator$class.$init$(this);
            DelegatedSignalling$class.$init$(this);
            IterableSplitter$class.$init$(this);
            AugmentedSeqIterator$class.$init$(this);
            SeqSplitter$class.$init$(this);
            BufferedIterator$class.$init$(this);
            this.scala$collection$parallel$ParSeqLike$Elements$$i = start;
        }
    }

    public interface Accessor<R, Tp>
    extends ParIterableLike.Accessor<R, Tp> {
        @Override
        public SeqSplitter<T> pit();
    }

    public class IndexWhere
    implements Accessor<Object, IndexWhere> {
        public final Function1<T, Object> scala$collection$parallel$ParSeqLike$IndexWhere$$pred;
        private final int from;
        private final SeqSplitter<T> pit;
        private volatile int result;
        public final /* synthetic */ ParSeqLike $outer;
        private volatile Throwable throwable;

        @Override
        public /* synthetic */ String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
            return super.toString();
        }

        @Override
        public boolean shouldSplitFurther() {
            return ParIterableLike$Accessor$class.shouldSplitFurther(this);
        }

        @Override
        public void signalAbort() {
            ParIterableLike$Accessor$class.signalAbort(this);
        }

        @Override
        public String toString() {
            return ParIterableLike$Accessor$class.toString(this);
        }

        @Override
        public Throwable throwable() {
            return this.throwable;
        }

        @Override
        public void throwable_$eq(Throwable x$1) {
            this.throwable = x$1;
        }

        @Override
        public Object repr() {
            return Task$class.repr(this);
        }

        @Override
        public void forwardThrowable() {
            Task$class.forwardThrowable(this);
        }

        @Override
        public void tryLeaf(Option<Object> lastres) {
            Task$class.tryLeaf(this, lastres);
        }

        @Override
        public void tryMerge(Object t) {
            Task$class.tryMerge(this, t);
        }

        @Override
        public void mergeThrowables(Task<?, ?> that) {
            Task$class.mergeThrowables(this, that);
        }

        @Override
        public SeqSplitter<T> pit() {
            return this.pit;
        }

        @Override
        public int result() {
            return this.result;
        }

        @Override
        public void result_$eq(int x$1) {
            this.result = x$1;
        }

        @Override
        public void leaf(Option<Object> prev) {
            int r;
            if (this.from < this.pit().indexFlag() && (r = this.pit().indexWhere(this.scala$collection$parallel$ParSeqLike$IndexWhere$$pred)) != -1) {
                this.result_$eq(this.from + r);
                this.pit().setIndexFlagIfLesser(this.from);
            }
        }

        public Nothing$ newSubtask(IterableSplitter<T> p) {
            return package$.MODULE$.unsupported();
        }

        @Override
        public Seq<Task<Object, IndexWhere>> split() {
            Seq pits = this.pit().splitWithSignalling();
            return ((TraversableLike)pits.zip(pits.scanLeft(BoxesRunTime.boxToInteger(this.from), new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final int apply(int x$19, SeqSplitter<T> x$20) {
                    return x$19 + x$20.remaining();
                }
            }, Seq$.MODULE$.canBuildFrom()), Seq$.MODULE$.canBuildFrom())).withFilter(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Tuple2<SeqSplitter<T>, Object> check$ifrefutable$3) {
                    boolean bl = check$ifrefutable$3 != null;
                    return bl;
                }
            }).map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ IndexWhere $outer;

                public final IndexWhere apply(Tuple2<SeqSplitter<T>, Object> x$21) {
                    if (x$21 != null) {
                        return new IndexWhere(this.$outer.scala$collection$parallel$ParSeqLike$IndexWhere$$$outer(), this.$outer.scala$collection$parallel$ParSeqLike$IndexWhere$$pred, x$21._2$mcI$sp(), x$21._1());
                    }
                    throw new MatchError(x$21);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Seq$.MODULE$.canBuildFrom());
        }

        @Override
        public void merge(IndexWhere that) {
            int n;
            if (this.result() == -1) {
                n = that.result();
            } else if (that.result() != -1) {
                int n2 = this.result();
                Predef$ predef$ = Predef$.MODULE$;
                n = RichInt$.MODULE$.min$extension(n2, that.result());
            } else {
                n = this.result();
            }
            this.result_$eq(n);
        }

        @Override
        public boolean requiresStrictSplitters() {
            return true;
        }

        public /* synthetic */ ParSeqLike scala$collection$parallel$ParSeqLike$IndexWhere$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$IndexWhere$$$outer();
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$IndexWhere$$$outer();
        }

        public IndexWhere(ParSeqLike<T, Repr, Sequential> $outer, Function1<T, Object> pred, int from2, SeqSplitter<T> pit) {
            this.scala$collection$parallel$ParSeqLike$IndexWhere$$pred = pred;
            this.from = from2;
            this.pit = pit;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            ParIterableLike$StrictSplitterCheckTask$class.$init$(this);
            ParIterableLike$Accessor$class.$init$(this);
            this.result = -1;
        }
    }

    public class ReverseMap<S, That>
    implements Transformer<Combiner<S, That>, ReverseMap<S, That>> {
        private final Function1<T, S> f;
        private final Function0<Combiner<S, That>> pbf;
        private final SeqSplitter<T> pit;
        private volatile Combiner<S, That> result;
        public final /* synthetic */ ParSeqLike $outer;
        private volatile Throwable throwable;

        @Override
        public /* synthetic */ String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
            return super.toString();
        }

        @Override
        public boolean shouldSplitFurther() {
            return ParIterableLike$Accessor$class.shouldSplitFurther(this);
        }

        @Override
        public Seq<Task<Combiner<S, That>, ReverseMap<S, That>>> split() {
            return ParIterableLike$Accessor$class.split(this);
        }

        @Override
        public void signalAbort() {
            ParIterableLike$Accessor$class.signalAbort(this);
        }

        @Override
        public String toString() {
            return ParIterableLike$Accessor$class.toString(this);
        }

        @Override
        public boolean requiresStrictSplitters() {
            return ParIterableLike$StrictSplitterCheckTask$class.requiresStrictSplitters(this);
        }

        @Override
        public Throwable throwable() {
            return this.throwable;
        }

        @Override
        public void throwable_$eq(Throwable x$1) {
            this.throwable = x$1;
        }

        @Override
        public Object repr() {
            return Task$class.repr(this);
        }

        @Override
        public void forwardThrowable() {
            Task$class.forwardThrowable(this);
        }

        @Override
        public void tryLeaf(Option<Combiner<S, That>> lastres) {
            Task$class.tryLeaf(this, lastres);
        }

        @Override
        public void tryMerge(Object t) {
            Task$class.tryMerge(this, t);
        }

        @Override
        public void mergeThrowables(Task<?, ?> that) {
            Task$class.mergeThrowables(this, that);
        }

        @Override
        public SeqSplitter<T> pit() {
            return this.pit;
        }

        @Override
        public Combiner<S, That> result() {
            return this.result;
        }

        @Override
        public void result_$eq(Combiner<S, That> x$1) {
            this.result = x$1;
        }

        @Override
        public void leaf(Option<Combiner<S, That>> prev) {
            this.result_$eq(this.pit().reverseMap2combiner(this.f, this.pbf.apply()));
        }

        @Override
        public ReverseMap<S, That> newSubtask(IterableSplitter<T> p) {
            return new ReverseMap<S, That>(this.scala$collection$parallel$ParSeqLike$ReverseMap$$$outer(), this.f, this.pbf, this.scala$collection$parallel$ParSeqLike$ReverseMap$$$outer().down(p));
        }

        @Override
        public void merge(ReverseMap<S, That> that) {
            this.result_$eq(that.result().combine(this.result()));
        }

        public /* synthetic */ ParSeqLike scala$collection$parallel$ParSeqLike$ReverseMap$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$ReverseMap$$$outer();
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$ReverseMap$$$outer();
        }

        public ReverseMap(ParSeqLike<T, Repr, Sequential> $outer, Function1<T, S> f, Function0<Combiner<S, That>> pbf, SeqSplitter<T> pit) {
            this.f = f;
            this.pbf = pbf;
            this.pit = pit;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            ParIterableLike$StrictSplitterCheckTask$class.$init$(this);
            ParIterableLike$Accessor$class.$init$(this);
            this.result = null;
        }
    }

    public interface Transformer<R, Tp>
    extends Accessor<R, Tp>,
    ParIterableLike.Transformer<R, Tp> {
    }

    public class Corresponds<S>
    implements Accessor<Object, Corresponds<S>> {
        public final Function2<T, S, Object> scala$collection$parallel$ParSeqLike$Corresponds$$corr;
        private final SeqSplitter<T> pit;
        private final SeqSplitter<S> otherpit;
        private volatile boolean result;
        public final /* synthetic */ ParSeqLike $outer;
        private volatile Throwable throwable;

        @Override
        public /* synthetic */ String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
            return super.toString();
        }

        @Override
        public boolean shouldSplitFurther() {
            return ParIterableLike$Accessor$class.shouldSplitFurther(this);
        }

        @Override
        public void signalAbort() {
            ParIterableLike$Accessor$class.signalAbort(this);
        }

        @Override
        public String toString() {
            return ParIterableLike$Accessor$class.toString(this);
        }

        @Override
        public Throwable throwable() {
            return this.throwable;
        }

        @Override
        public void throwable_$eq(Throwable x$1) {
            this.throwable = x$1;
        }

        @Override
        public Object repr() {
            return Task$class.repr(this);
        }

        @Override
        public void forwardThrowable() {
            Task$class.forwardThrowable(this);
        }

        @Override
        public void tryLeaf(Option<Object> lastres) {
            Task$class.tryLeaf(this, lastres);
        }

        @Override
        public void tryMerge(Object t) {
            Task$class.tryMerge(this, t);
        }

        @Override
        public void mergeThrowables(Task<?, ?> that) {
            Task$class.mergeThrowables(this, that);
        }

        @Override
        public SeqSplitter<T> pit() {
            return this.pit;
        }

        public SeqSplitter<S> otherpit() {
            return this.otherpit;
        }

        @Override
        public boolean result() {
            return this.result;
        }

        @Override
        public void result_$eq(boolean x$1) {
            this.result = x$1;
        }

        @Override
        public void leaf(Option<Object> prev) {
            if (!this.pit().isAborted()) {
                this.result_$eq(this.pit().corresponds(this.scala$collection$parallel$ParSeqLike$Corresponds$$corr, this.otherpit()));
                if (!this.result()) {
                    this.pit().abort();
                }
            }
        }

        public Nothing$ newSubtask(IterableSplitter<T> p) {
            return package$.MODULE$.unsupported();
        }

        @Override
        public Seq<Task<Object, Corresponds<S>>> split() {
            int fp = this.pit().remaining() / 2;
            int sp2 = this.pit().remaining() - fp;
            return ((TraversableLike)this.pit().psplitWithSignalling(Predef$.MODULE$.wrapIntArray(new int[]{fp, sp2})).zip(this.otherpit().psplitWithSignalling(Predef$.MODULE$.wrapIntArray(new int[]{fp, sp2})), Seq$.MODULE$.canBuildFrom())).withFilter(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Tuple2<SeqSplitter<T>, SeqSplitter<S>> check$ifrefutable$7) {
                    boolean bl = check$ifrefutable$7 != null;
                    return bl;
                }
            }).map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Corresponds $outer;

                public final Corresponds<S> apply(Tuple2<SeqSplitter<T>, SeqSplitter<S>> x$29) {
                    if (x$29 != null) {
                        return new Corresponds<S>(this.$outer.scala$collection$parallel$ParSeqLike$Corresponds$$$outer(), this.$outer.scala$collection$parallel$ParSeqLike$Corresponds$$corr, x$29._1(), x$29._2());
                    }
                    throw new MatchError(x$29);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Seq$.MODULE$.canBuildFrom());
        }

        @Override
        public void merge(Corresponds<S> that) {
            this.result_$eq(this.result() && that.result());
        }

        @Override
        public boolean requiresStrictSplitters() {
            return true;
        }

        public /* synthetic */ ParSeqLike scala$collection$parallel$ParSeqLike$Corresponds$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$Corresponds$$$outer();
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$Corresponds$$$outer();
        }

        public Corresponds(ParSeqLike<T, Repr, Sequential> $outer, Function2<T, S, Object> corr, SeqSplitter<T> pit, SeqSplitter<S> otherpit) {
            this.scala$collection$parallel$ParSeqLike$Corresponds$$corr = corr;
            this.pit = pit;
            this.otherpit = otherpit;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            ParIterableLike$StrictSplitterCheckTask$class.$init$(this);
            ParIterableLike$Accessor$class.$init$(this);
            this.result = true;
        }
    }

    public class SameElements<U>
    implements Accessor<Object, SameElements<U>> {
        private final SeqSplitter<T> pit;
        private final SeqSplitter<U> otherpit;
        private volatile boolean result;
        public final /* synthetic */ ParSeqLike $outer;
        private volatile Throwable throwable;

        @Override
        public /* synthetic */ String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
            return super.toString();
        }

        @Override
        public boolean shouldSplitFurther() {
            return ParIterableLike$Accessor$class.shouldSplitFurther(this);
        }

        @Override
        public void signalAbort() {
            ParIterableLike$Accessor$class.signalAbort(this);
        }

        @Override
        public String toString() {
            return ParIterableLike$Accessor$class.toString(this);
        }

        @Override
        public Throwable throwable() {
            return this.throwable;
        }

        @Override
        public void throwable_$eq(Throwable x$1) {
            this.throwable = x$1;
        }

        @Override
        public Object repr() {
            return Task$class.repr(this);
        }

        @Override
        public void forwardThrowable() {
            Task$class.forwardThrowable(this);
        }

        @Override
        public void tryLeaf(Option<Object> lastres) {
            Task$class.tryLeaf(this, lastres);
        }

        @Override
        public void tryMerge(Object t) {
            Task$class.tryMerge(this, t);
        }

        @Override
        public void mergeThrowables(Task<?, ?> that) {
            Task$class.mergeThrowables(this, that);
        }

        @Override
        public SeqSplitter<T> pit() {
            return this.pit;
        }

        public SeqSplitter<U> otherpit() {
            return this.otherpit;
        }

        @Override
        public boolean result() {
            return this.result;
        }

        @Override
        public void result_$eq(boolean x$1) {
            this.result = x$1;
        }

        @Override
        public void leaf(Option<Object> prev) {
            if (!this.pit().isAborted()) {
                this.result_$eq(this.pit().sameElements(this.otherpit()));
                if (!this.result()) {
                    this.pit().abort();
                }
            }
        }

        public Nothing$ newSubtask(IterableSplitter<T> p) {
            return package$.MODULE$.unsupported();
        }

        @Override
        public Seq<Task<Object, SameElements<U>>> split() {
            int fp = this.pit().remaining() / 2;
            int sp2 = this.pit().remaining() - fp;
            return ((TraversableLike)this.pit().psplitWithSignalling(Predef$.MODULE$.wrapIntArray(new int[]{fp, sp2})).zip(this.otherpit().psplitWithSignalling(Predef$.MODULE$.wrapIntArray(new int[]{fp, sp2})), Seq$.MODULE$.canBuildFrom())).withFilter(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Tuple2<SeqSplitter<T>, SeqSplitter<U>> check$ifrefutable$5) {
                    boolean bl = check$ifrefutable$5 != null;
                    return bl;
                }
            }).map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SameElements $outer;

                public final SameElements<U> apply(Tuple2<SeqSplitter<T>, SeqSplitter<U>> x$25) {
                    if (x$25 != null) {
                        return new SameElements<U>(this.$outer.scala$collection$parallel$ParSeqLike$SameElements$$$outer(), x$25._1(), x$25._2());
                    }
                    throw new MatchError(x$25);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Seq$.MODULE$.canBuildFrom());
        }

        @Override
        public void merge(SameElements<U> that) {
            this.result_$eq(this.result() && that.result());
        }

        @Override
        public boolean requiresStrictSplitters() {
            return true;
        }

        public /* synthetic */ ParSeqLike scala$collection$parallel$ParSeqLike$SameElements$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$SameElements$$$outer();
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$SameElements$$$outer();
        }

        public SameElements(ParSeqLike<T, Repr, Sequential> $outer, SeqSplitter<T> pit, SeqSplitter<U> otherpit) {
            this.pit = pit;
            this.otherpit = otherpit;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            ParIterableLike$StrictSplitterCheckTask$class.$init$(this);
            ParIterableLike$Accessor$class.$init$(this);
            this.result = true;
        }
    }

    public class SegmentLength
    implements Accessor<Tuple2<Object, Object>, SegmentLength> {
        public final Function1<T, Object> scala$collection$parallel$ParSeqLike$SegmentLength$$pred;
        public final int scala$collection$parallel$ParSeqLike$SegmentLength$$from;
        private final SeqSplitter<T> pit;
        private volatile Tuple2<Object, Object> result;
        public final /* synthetic */ ParSeqLike $outer;
        private volatile Throwable throwable;

        @Override
        public /* synthetic */ String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
            return super.toString();
        }

        @Override
        public boolean shouldSplitFurther() {
            return ParIterableLike$Accessor$class.shouldSplitFurther(this);
        }

        @Override
        public void signalAbort() {
            ParIterableLike$Accessor$class.signalAbort(this);
        }

        @Override
        public String toString() {
            return ParIterableLike$Accessor$class.toString(this);
        }

        @Override
        public Throwable throwable() {
            return this.throwable;
        }

        @Override
        public void throwable_$eq(Throwable x$1) {
            this.throwable = x$1;
        }

        @Override
        public Object repr() {
            return Task$class.repr(this);
        }

        @Override
        public void forwardThrowable() {
            Task$class.forwardThrowable(this);
        }

        @Override
        public void tryLeaf(Option<Tuple2<Object, Object>> lastres) {
            Task$class.tryLeaf(this, lastres);
        }

        @Override
        public void tryMerge(Object t) {
            Task$class.tryMerge(this, t);
        }

        @Override
        public void mergeThrowables(Task<?, ?> that) {
            Task$class.mergeThrowables(this, that);
        }

        @Override
        public SeqSplitter<T> pit() {
            return this.pit;
        }

        @Override
        public Tuple2<Object, Object> result() {
            return this.result;
        }

        @Override
        public void result_$eq(Tuple2<Object, Object> x$1) {
            this.result = x$1;
        }

        @Override
        public void leaf(Option<Tuple2<Object, Object>> prev) {
            if (this.scala$collection$parallel$ParSeqLike$SegmentLength$$from < this.pit().indexFlag()) {
                int seglen;
                int itsize = this.pit().remaining();
                this.result_$eq(new Tuple2$mcIZ$sp(seglen, itsize == (seglen = this.pit().prefixLength(this.scala$collection$parallel$ParSeqLike$SegmentLength$$pred))));
                if (!((Tuple2)this.result())._2$mcZ$sp()) {
                    this.pit().setIndexFlagIfLesser(this.scala$collection$parallel$ParSeqLike$SegmentLength$$from);
                }
            } else {
                this.result_$eq(new Tuple2$mcIZ$sp(0, false));
            }
        }

        public Nothing$ newSubtask(IterableSplitter<T> p) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Seq<Task<Tuple2<Object, Object>, SegmentLength>> split() {
            Seq pits = this.pit().splitWithSignalling();
            return ((TraversableLike)pits.zip(pits.scanLeft(BoxesRunTime.boxToInteger(0), new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final int apply(int x$16, SeqSplitter<T> x$17) {
                    return x$16 + x$17.remaining();
                }
            }, Seq$.MODULE$.canBuildFrom()), Seq$.MODULE$.canBuildFrom())).withFilter(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Tuple2<SeqSplitter<T>, Object> check$ifrefutable$2) {
                    boolean bl = check$ifrefutable$2 != null;
                    return bl;
                }
            }).map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SegmentLength $outer;

                public final SegmentLength apply(Tuple2<SeqSplitter<T>, Object> x$18) {
                    if (x$18 != null) {
                        return new SegmentLength(this.$outer.scala$collection$parallel$ParSeqLike$SegmentLength$$$outer(), this.$outer.scala$collection$parallel$ParSeqLike$SegmentLength$$pred, this.$outer.scala$collection$parallel$ParSeqLike$SegmentLength$$from + x$18._2$mcI$sp(), x$18._1());
                    }
                    throw new MatchError(x$18);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Seq$.MODULE$.canBuildFrom());
        }

        @Override
        public void merge(SegmentLength that) {
            if (((Tuple2)this.result())._2$mcZ$sp()) {
                this.result_$eq(new Tuple2$mcIZ$sp(((Tuple2)this.result())._1$mcI$sp() + ((Tuple2)that.result())._1$mcI$sp(), ((Tuple2)that.result())._2$mcZ$sp()));
            }
        }

        @Override
        public boolean requiresStrictSplitters() {
            return true;
        }

        public /* synthetic */ ParSeqLike scala$collection$parallel$ParSeqLike$SegmentLength$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$SegmentLength$$$outer();
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$SegmentLength$$$outer();
        }

        public SegmentLength(ParSeqLike<T, Repr, Sequential> $outer, Function1<T, Object> pred, int from2, SeqSplitter<T> pit) {
            this.scala$collection$parallel$ParSeqLike$SegmentLength$$pred = pred;
            this.scala$collection$parallel$ParSeqLike$SegmentLength$$from = from2;
            this.pit = pit;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            ParIterableLike$StrictSplitterCheckTask$class.$init$(this);
            ParIterableLike$Accessor$class.$init$(this);
            this.result = null;
        }
    }

    public class LastIndexWhere
    implements Accessor<Object, LastIndexWhere> {
        public final Function1<T, Object> scala$collection$parallel$ParSeqLike$LastIndexWhere$$pred;
        private final int pos;
        private final SeqSplitter<T> pit;
        private volatile int result;
        public final /* synthetic */ ParSeqLike $outer;
        private volatile Throwable throwable;

        @Override
        public /* synthetic */ String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
            return super.toString();
        }

        @Override
        public boolean shouldSplitFurther() {
            return ParIterableLike$Accessor$class.shouldSplitFurther(this);
        }

        @Override
        public void signalAbort() {
            ParIterableLike$Accessor$class.signalAbort(this);
        }

        @Override
        public String toString() {
            return ParIterableLike$Accessor$class.toString(this);
        }

        @Override
        public Throwable throwable() {
            return this.throwable;
        }

        @Override
        public void throwable_$eq(Throwable x$1) {
            this.throwable = x$1;
        }

        @Override
        public Object repr() {
            return Task$class.repr(this);
        }

        @Override
        public void forwardThrowable() {
            Task$class.forwardThrowable(this);
        }

        @Override
        public void tryLeaf(Option<Object> lastres) {
            Task$class.tryLeaf(this, lastres);
        }

        @Override
        public void tryMerge(Object t) {
            Task$class.tryMerge(this, t);
        }

        @Override
        public void mergeThrowables(Task<?, ?> that) {
            Task$class.mergeThrowables(this, that);
        }

        @Override
        public SeqSplitter<T> pit() {
            return this.pit;
        }

        @Override
        public int result() {
            return this.result;
        }

        @Override
        public void result_$eq(int x$1) {
            this.result = x$1;
        }

        @Override
        public void leaf(Option<Object> prev) {
            int r;
            if (this.pos > this.pit().indexFlag() && (r = this.pit().lastIndexWhere(this.scala$collection$parallel$ParSeqLike$LastIndexWhere$$pred)) != -1) {
                this.result_$eq(this.pos + r);
                this.pit().setIndexFlagIfGreater(this.pos);
            }
        }

        public Nothing$ newSubtask(IterableSplitter<T> p) {
            return package$.MODULE$.unsupported();
        }

        @Override
        public Seq<Task<Object, LastIndexWhere>> split() {
            Seq pits = this.pit().splitWithSignalling();
            return ((TraversableLike)pits.zip(pits.scanLeft(BoxesRunTime.boxToInteger(this.pos), new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final int apply(int x$22, SeqSplitter<T> x$23) {
                    return x$22 + x$23.remaining();
                }
            }, Seq$.MODULE$.canBuildFrom()), Seq$.MODULE$.canBuildFrom())).withFilter(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Tuple2<SeqSplitter<T>, Object> check$ifrefutable$4) {
                    boolean bl = check$ifrefutable$4 != null;
                    return bl;
                }
            }).map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ LastIndexWhere $outer;

                public final LastIndexWhere apply(Tuple2<SeqSplitter<T>, Object> x$24) {
                    if (x$24 != null) {
                        return new LastIndexWhere(this.$outer.scala$collection$parallel$ParSeqLike$LastIndexWhere$$$outer(), this.$outer.scala$collection$parallel$ParSeqLike$LastIndexWhere$$pred, x$24._2$mcI$sp(), x$24._1());
                    }
                    throw new MatchError(x$24);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Seq$.MODULE$.canBuildFrom());
        }

        @Override
        public void merge(LastIndexWhere that) {
            int n;
            if (this.result() == -1) {
                n = that.result();
            } else if (that.result() != -1) {
                int n2 = this.result();
                Predef$ predef$ = Predef$.MODULE$;
                n = RichInt$.MODULE$.max$extension(n2, that.result());
            } else {
                n = this.result();
            }
            this.result_$eq(n);
        }

        @Override
        public boolean requiresStrictSplitters() {
            return true;
        }

        public /* synthetic */ ParSeqLike scala$collection$parallel$ParSeqLike$LastIndexWhere$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$LastIndexWhere$$$outer();
        }

        @Override
        public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
            return this.scala$collection$parallel$ParSeqLike$LastIndexWhere$$$outer();
        }

        public LastIndexWhere(ParSeqLike<T, Repr, Sequential> $outer, Function1<T, Object> pred, int pos, SeqSplitter<T> pit) {
            this.scala$collection$parallel$ParSeqLike$LastIndexWhere$$pred = pred;
            this.pos = pos;
            this.pit = pit;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            ParIterableLike$StrictSplitterCheckTask$class.$init$(this);
            ParIterableLike$Accessor$class.$init$(this);
            this.result = -1;
        }
    }
}

