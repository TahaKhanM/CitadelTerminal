/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.PartialFunction$class;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenMapLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterable$class;
import scala.collection.IterableLike$class;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.SortedMap$class;
import scala.collection.SortedMapLike$class;
import scala.collection.Traversable;
import scala.collection.Traversable$class;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Sorted$class;
import scala.collection.generic.Subtractable;
import scala.collection.generic.Subtractable$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map$class;
import scala.collection.immutable.MapLike$class;
import scala.collection.immutable.RedBlackTree;
import scala.collection.immutable.RedBlackTree$;
import scala.collection.immutable.Set;
import scala.collection.immutable.SortedMap;
import scala.collection.immutable.SortedSet;
import scala.collection.immutable.Stream;
import scala.collection.immutable.TreeMap$;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParMap;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.package$;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@ScalaSignature(bytes="\u0006\u0001\tmx!B\u0001\u0003\u0011\u0003I\u0011a\u0002+sK\u0016l\u0015\r\u001d\u0006\u0003\u0007\u0011\t\u0011\"[7nkR\f'\r\\3\u000b\u0005\u00151\u0011AC2pY2,7\r^5p]*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0011\u0005)YQ\"\u0001\u0002\u0007\u000b1\u0011\u0001\u0012A\u0007\u0003\u000fQ\u0013X-Z'baN\u00191BD\u001b\u0011\u0007=\u0011B#D\u0001\u0011\u0015\t\tB!A\u0004hK:,'/[2\n\u0005M\u0001\"!G%n[V$\u0018M\u00197f'>\u0014H/\u001a3NCB4\u0015m\u0019;pef\u0004\"AC\u000b\u0007\t1\u0011\u0001AF\u000b\u0004/\u0005Z3CB\u000b\u001995\u0012T\u0007\u0005\u0002\u001a55\ta!\u0003\u0002\u001c\r\t1\u0011I\\=SK\u001a\u0004BAC\u000f U%\u0011aD\u0001\u0002\n'>\u0014H/\u001a3NCB\u0004\"\u0001I\u0011\r\u0001\u0011)!%\u0006b\u0001G\t\t\u0011)\u0005\u0002%OA\u0011\u0011$J\u0005\u0003M\u0019\u0011qAT8uQ&tw\r\u0005\u0002\u001aQ%\u0011\u0011F\u0002\u0002\u0004\u0003:L\bC\u0001\u0011,\t\u0019aS\u0003\"b\u0001G\t\t!\tE\u0003/_}Q\u0013'D\u0001\u0005\u0013\t\u0001DAA\u0007T_J$X\rZ'ba2K7.\u001a\t\u0005\u0015Uy\"\u0006E\u0003\u000bg}Q\u0013'\u0003\u00025\u0005\t9Q*\u00199MS.,\u0007CA\r7\u0013\t9dA\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0003\u0005:+\t\u0005\t\u0015!\u0003;\u0003\u0011!(/Z3\u0011\tmrtD\u000b\b\u0003\u0015qJ!!\u0010\u0002\u0002\u0019I+GM\u00117bG.$&/Z3\n\u0005}\u0002%\u0001\u0002+sK\u0016T!!\u0010\u0002\t\u0011\t+\"Q1A\u0005\u0004\r\u000b\u0001b\u001c:eKJLgnZ\u000b\u0002\tB\u0019Q\tS\u0010\u000f\u0005e1\u0015BA$\u0007\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0013&\u0003\u0011=\u0013H-\u001a:j]\u001eT!a\u0012\u0004\t\u00111+\"\u0011!Q\u0001\n\u0011\u000b\u0011b\u001c:eKJLgn\u001a\u0011\t\u000b9+B\u0011B(\u0002\rqJg.\u001b;?)\t\u0001&\u000b\u0006\u00022#\")!)\u0014a\u0002\t\")\u0011(\u0014a\u0001u!1A+\u0006Q\u0005RU\u000b!B\\3x\u0005VLG\u000eZ3s+\u00051\u0006\u0003B,[9Fj\u0011\u0001\u0017\u0006\u00033\u0012\tq!\\;uC\ndW-\u0003\u0002\\1\n9!)^5mI\u0016\u0014\b\u0003B\r^?)J!A\u0018\u0004\u0003\rQ+\b\u000f\\33\u0011\u0015\u0001W\u0003\"\u0011b\u0003\u0011\u0019\u0018N_3\u0016\u0003\t\u0004\"!G2\n\u0005\u00114!aA%oi\")a*\u0006C\u0001MR\tq\r\u0006\u00022Q\")!)\u001aa\u0002\t\")!.\u0006C!W\u0006I!/\u00198hK&k\u0007\u000f\u001c\u000b\u0004c1\f\b\"B7j\u0001\u0004q\u0017\u0001\u00024s_6\u00042!G8 \u0013\t\u0001hA\u0001\u0004PaRLwN\u001c\u0005\u0006e&\u0004\rA\\\u0001\u0006k:$\u0018\u000e\u001c\u0005\u0006iV!\t%^\u0001\u0006e\u0006tw-\u001a\u000b\u0004cY<\b\"B7t\u0001\u0004y\u0002\"\u0002:t\u0001\u0004y\u0002\"B7\u0016\t\u0003JHCA\u0019{\u0011\u0015i\u0007\u00101\u0001 \u0011\u0015aX\u0003\"\u0011~\u0003\t!x\u000e\u0006\u00022}\")Ap\u001fa\u0001?!1!/\u0006C!\u0003\u0003!2!MA\u0002\u0011\u0015\u0011x\u00101\u0001 \u0011\u001d\t9!\u0006C!\u0003\u0013\t\u0001BZ5sgR\\U-_\u000b\u0002?!9\u0011QB\u000b\u0005B\u0005%\u0011a\u00027bgR\\U-\u001f\u0005\b\u0003#)B\u0011IA\n\u0003\u001d\u0019w.\u001c9be\u0016$RAYA\u000b\u00033Aq!a\u0006\u0002\u0010\u0001\u0007q$\u0001\u0002la!9\u00111DA\b\u0001\u0004y\u0012AA62\u0011\u001d\ty\"\u0006C!\u0003C\tA\u0001[3bIV\tA\fC\u0004\u0002&U!\t%a\n\u0002\u0015!,\u0017\rZ(qi&|g.\u0006\u0002\u0002*A\u0019\u0011d\u001c/\t\u000f\u00055R\u0003\"\u0011\u0002\"\u0005!A.Y:u\u0011\u001d\t\t$\u0006C!\u0003O\t!\u0002\\1ti>\u0003H/[8o\u0011\u001d\t)$\u0006C!\u0003o\tA\u0001^1jYV\t\u0011\u0007C\u0004\u0002<U!\t%a\u000e\u0002\t%t\u0017\u000e\u001e\u0005\b\u0003\u007f)B\u0011IA!\u0003\u0011!'o\u001c9\u0015\u0007E\n\u0019\u0005C\u0004\u0002F\u0005u\u0002\u0019\u00012\u0002\u00039Dq!!\u0013\u0016\t\u0003\nY%\u0001\u0003uC.,GcA\u0019\u0002N!9\u0011QIA$\u0001\u0004\u0011\u0007bBA)+\u0011\u0005\u00131K\u0001\u0006g2L7-\u001a\u000b\u0006c\u0005U\u0013q\u000b\u0005\u0007[\u0006=\u0003\u0019\u00012\t\rI\fy\u00051\u0001c\u0011\u001d\tY&\u0006C!\u0003;\n\u0011\u0002\u001a:paJKw\r\u001b;\u0015\u0007E\ny\u0006C\u0004\u0002F\u0005e\u0003\u0019\u00012\t\u000f\u0005\rT\u0003\"\u0011\u0002f\u0005IA/Y6f%&<\u0007\u000e\u001e\u000b\u0004c\u0005\u001d\u0004bBA#\u0003C\u0002\rA\u0019\u0005\b\u0003W*B\u0011IA7\u0003\u001d\u0019\b\u000f\\5u\u0003R$B!a\u001c\u0002rA!\u0011$X\u00192\u0011\u001d\t)%!\u001bA\u0002\tD\u0001\"!\u001e\u0016A\u0013%\u0011qO\u0001\u000bG>,h\u000e^,iS2,Gc\u00012\u0002z!A\u00111PA:\u0001\u0004\ti(A\u0001q!\u0019I\u0012q\u0010/\u0002\u0004&\u0019\u0011\u0011\u0011\u0004\u0003\u0013\u0019+hn\u0019;j_:\f\u0004cA\r\u0002\u0006&\u0019\u0011q\u0011\u0004\u0003\u000f\t{w\u000e\\3b]\"9\u00111R\u000b\u0005B\u00055\u0015!\u00033s_B<\u0006.\u001b7f)\r\t\u0014q\u0012\u0005\t\u0003w\nI\t1\u0001\u0002~!9\u00111S\u000b\u0005B\u0005U\u0015!\u0003;bW\u0016<\u0006.\u001b7f)\r\t\u0014q\u0013\u0005\t\u0003w\n\t\n1\u0001\u0002~!9\u00111T\u000b\u0005B\u0005u\u0015\u0001B:qC:$B!a\u001c\u0002 \"A\u00111PAM\u0001\u0004\ti\bC\u0004\u0002$V!\t%a\u000e\u0002\u000b\u0015l\u0007\u000f^=\t\u000f\u0005\u001dV\u0003\"\u0011\u0002*\u00069Q\u000f\u001d3bi\u0016$W\u0003BAV\u0003c#b!!,\u00028\u0006m\u0006#\u0002\u0006\u0016?\u0005=\u0006c\u0001\u0011\u00022\u0012A\u00111WAS\u0005\u0004\t)L\u0001\u0002CcE\u0011!f\n\u0005\b\u0003s\u000b)\u000b1\u0001 \u0003\rYW-\u001f\u0005\t\u0003{\u000b)\u000b1\u0001\u00020\u0006)a/\u00197vK\"9\u0011\u0011Y\u000b\u0005B\u0005\r\u0017!\u0002\u0013qYV\u001cX\u0003BAc\u0003\u0017$B!a2\u0002NB)!\"F\u0010\u0002JB\u0019\u0001%a3\u0005\u0011\u0005M\u0016q\u0018b\u0001\u0003kC\u0001\"a4\u0002@\u0002\u0007\u0011\u0011[\u0001\u0003WZ\u0004R!G/ \u0003\u0013Dq!!1\u0016\t\u0003\n).\u0006\u0003\u0002X\u0006uG\u0003CAm\u0003?\f)/!;\u0011\u000b))r$a7\u0011\u0007\u0001\ni\u000e\u0002\u0005\u00024\u0006M'\u0019AA[\u0011!\t\t/a5A\u0002\u0005\r\u0018!B3mK6\f\u0004#B\r^?\u0005m\u0007\u0002CAt\u0003'\u0004\r!a9\u0002\u000b\u0015dW-\u001c\u001a\t\u0011\u0005-\u00181\u001ba\u0001\u0003[\fQ!\u001a7f[N\u0004R!GAx\u0003GL1!!=\u0007\u0005)a$/\u001a9fCR,GM\u0010\u0005\b\u0003k,B\u0011IA|\u0003)!\u0003\u000f\\;tIAdWo]\u000b\u0005\u0003s\fy\u0010\u0006\u0003\u0002|\n\u0005\u0001#\u0002\u0006\u0016?\u0005u\bc\u0001\u0011\u0002\u0000\u0012A\u00111WAz\u0005\u0004\t)\f\u0003\u0005\u0003\u0004\u0005M\b\u0019\u0001B\u0003\u0003\tA8\u000fE\u0003/\u0005\u000f\u0011Y!C\u0002\u0003\n\u0011\u0011!cR3o)J\fg/\u001a:tC\ndWm\u00148dKB)\u0011$X\u0010\u0002~\"9!qB\u000b\u0005\u0002\tE\u0011AB5og\u0016\u0014H/\u0006\u0003\u0003\u0014\teAC\u0002B\u000b\u00057\u0011i\u0002E\u0003\u000b+}\u00119\u0002E\u0002!\u00053!\u0001\"a-\u0003\u000e\t\u0007\u0011Q\u0017\u0005\b\u0003s\u0013i\u00011\u0001 \u0011!\tiL!\u0004A\u0002\t]\u0001b\u0002B\u0011+\u0011\u0005!1E\u0001\u0007I5Lg.^:\u0015\u0007E\u0012)\u0003C\u0004\u0002:\n}\u0001\u0019A\u0010\t\u000f\t%R\u0003\"\u0011\u0003,\u0005\u0019q-\u001a;\u0015\t\t5\"q\u0006\t\u00043=T\u0003bBA]\u0005O\u0001\ra\b\u0005\b\u0005g)B\u0011\tB\u001b\u0003!IG/\u001a:bi>\u0014XC\u0001B\u001c!\u0011q#\u0011\b/\n\u0007\tmBA\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0011\u001d\u0011y$\u0006C!\u0005\u0003\nA\"\u001b;fe\u0006$xN\u001d$s_6$BAa\u000e\u0003D!9!Q\tB\u001f\u0001\u0004y\u0012!B:uCJ$\bb\u0002B%+\u0011\u0005#1J\u0001\rW\u0016L8/\u0013;fe\u0006$xN]\u000b\u0003\u0005\u001b\u0002BA\fB\u001d?!9!\u0011K\u000b\u0005B\tM\u0013\u0001E6fsNLE/\u001a:bi>\u0014hI]8n)\u0011\u0011iE!\u0016\t\u000f\t\u0015#q\na\u0001?!9!\u0011L\u000b\u0005B\tm\u0013A\u0004<bYV,7/\u0013;fe\u0006$xN]\u000b\u0003\u0005;\u0002BA\fB\u001dU!9!\u0011M\u000b\u0005B\t\r\u0014A\u0005<bYV,7/\u0013;fe\u0006$xN\u001d$s_6$BA!\u0018\u0003f!9!Q\tB0\u0001\u0004y\u0002b\u0002B5+\u0011\u0005#1N\u0001\tG>tG/Y5ogR!\u00111\u0011B7\u0011\u001d\tILa\u001aA\u0002}AqA!\u001d\u0016\t\u0003\u0012\u0019(A\u0006jg\u0012+g-\u001b8fI\u0006#H\u0003BAB\u0005kBq!!/\u0003p\u0001\u0007q\u0004C\u0004\u0003zU!\tEa\u001f\u0002\u000f\u0019|'/Z1dQV!!Q\u0010BG)\u0011\u0011yH!\"\u0011\u0007e\u0011\t)C\u0002\u0003\u0004\u001a\u0011A!\u00168ji\"A!q\u0011B<\u0001\u0004\u0011I)A\u0001g!\u0019I\u0012q\u0010/\u0003\fB\u0019\u0001E!$\u0005\u000f\t=%q\u000fb\u0001G\t\tQ\u000bK\u0004\u0016\u0005'\u0013IJ!(\u0011\u0007e\u0011)*C\u0002\u0003\u0018\u001a\u0011Q\u0003Z3qe\u0016\u001c\u0017\r^3e\u0013:DWM]5uC:\u001cW-\t\u0002\u0003\u001c\u0006\u0019F\u000b[3!S6\u0004H.Z7f]R\fG/[8oA\u0011,G/Y5mg\u0002zg\rI5n[V$\u0018M\u00197fAQ\u0014X-\u001a\u0011nCB\u001c\b%\\1lK\u0002Jg\u000e[3sSRLgn\u001a\u0011ge>l\u0007\u0005\u001e5f[\u0002*hn^5tK:\n#Aa(\u0002\rIr\u0013'\r\u00181\u0011\u0019q5\u0002\"\u0001\u0003$R\t\u0011\u0002C\u0004\u0002$.!\tAa*\u0016\r\t%&q\u0016BZ)\u0011\u0011YK!.\u0011\r))\"Q\u0016BY!\r\u0001#q\u0016\u0003\u0007E\t\u0015&\u0019A\u0012\u0011\u0007\u0001\u0012\u0019\f\u0002\u0004-\u0005K\u0013\ra\t\u0005\t\u0005o\u0013)\u000bq\u0001\u0003:\u0006\u0019qN\u001d3\u0011\t\u0015C%Q\u0016\u0005\b\u0005{[A1\u0001B`\u00031\u0019\u0017M\u001c\"vS2$gI]8n+\u0019\u0011\tM!7\u0003^R!!1\u0019Bq!%y!Q\u0019Be\u0005+\u0014y.C\u0002\u0003HB\u0011AbQ1o\u0005VLG\u000e\u001a$s_6\u0004BAa3\u0003N6\t1\"\u0003\u0003\u0003P\nE'\u0001B\"pY2L1Aa5\u0011\u0005A\u0019vN\u001d;fI6\u000b\u0007OR1di>\u0014\u0018\u0010\u0005\u0004\u001a;\n]'1\u001c\t\u0004A\teGA\u0002\u0012\u0003<\n\u00071\u0005E\u0002!\u0005;$a\u0001\fB^\u0005\u0004\u0019\u0003C\u0002\u0006\u0016\u0005/\u0014Y\u000e\u0003\u0005\u00038\nm\u00069\u0001Br!\u0011)\u0005Ja6\t\u0013\t\u001d8\"!A\u0005\n\t%\u0018a\u0003:fC\u0012\u0014Vm]8mm\u0016$\"Aa;\u0011\t\t5(q_\u0007\u0003\u0005_TAA!=\u0003t\u0006!A.\u00198h\u0015\t\u0011)0\u0001\u0003kCZ\f\u0017\u0002\u0002B}\u0005_\u0014aa\u00142kK\u000e$\b")
public class TreeMap<A, B>
implements SortedMap<A, B>,
Serializable {
    private final RedBlackTree.Tree<A, B> tree;
    private final Ordering<A> ordering;

    public static <A, B> CanBuildFrom<TreeMap<?, ?>, Tuple2<A, B>, TreeMap<A, B>> canBuildFrom(Ordering<A> ordering) {
        return TreeMap$.MODULE$.canBuildFrom(ordering);
    }

    @Override
    public SortedSet<A> keySet() {
        return scala.collection.immutable.SortedMap$class.keySet(this);
    }

    @Override
    public SortedMap<A, B> filterKeys(Function1<A, Object> p) {
        return scala.collection.immutable.SortedMap$class.filterKeys(this, p);
    }

    @Override
    public <C> SortedMap<A, C> mapValues(Function1<B, C> f) {
        return scala.collection.immutable.SortedMap$class.mapValues(this, f);
    }

    @Override
    public boolean hasAll(Iterator<A> j) {
        return Sorted$class.hasAll(this, j);
    }

    @Override
    public <T, U> scala.collection.immutable.Map<T, U> toMap(Predef$.less.colon.less<Tuple2<A, B>, Tuple2<T, U>> ev) {
        return Map$class.toMap(this, ev);
    }

    @Override
    public scala.collection.immutable.Map<A, B> seq() {
        return Map$class.seq(this);
    }

    @Override
    public <B1> scala.collection.immutable.Map<A, B1> withDefault(Function1<A, B1> d) {
        return Map$class.withDefault(this, d);
    }

    @Override
    public <B1> scala.collection.immutable.Map<A, B1> withDefaultValue(B1 d) {
        return Map$class.withDefaultValue(this, d);
    }

    @Override
    public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
        return MapLike$class.parCombiner(this);
    }

    @Override
    public <C, That> That transform(Function2<A, B, C> f, CanBuildFrom<TreeMap<A, B>, Tuple2<A, C>, That> bf) {
        return (That)MapLike$class.transform(this, f, bf);
    }

    @Override
    public boolean isEmpty() {
        return scala.collection.MapLike$class.isEmpty(this);
    }

    @Override
    public <B1> B1 getOrElse(A key, Function0<B1> function0) {
        return (B1)scala.collection.MapLike$class.getOrElse(this, key, function0);
    }

    @Override
    public B apply(A key) {
        return (B)scala.collection.MapLike$class.apply(this, key);
    }

    @Override
    public Iterable<A> keys() {
        return scala.collection.MapLike$class.keys(this);
    }

    @Override
    public Iterable<B> values() {
        return scala.collection.MapLike$class.values(this);
    }

    @Override
    public B default(A key) {
        return (B)scala.collection.MapLike$class.default(this, key);
    }

    @Override
    public Map filterNot(Function1 p) {
        return scala.collection.MapLike$class.filterNot(this, p);
    }

    @Override
    public Seq<Tuple2<A, B>> toSeq() {
        return scala.collection.MapLike$class.toSeq(this);
    }

    @Override
    public <C> Buffer<C> toBuffer() {
        return scala.collection.MapLike$class.toBuffer(this);
    }

    @Override
    public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
        return scala.collection.MapLike$class.addString(this, b, start, sep, end);
    }

    @Override
    public String stringPrefix() {
        return scala.collection.MapLike$class.stringPrefix(this);
    }

    @Override
    public String toString() {
        return scala.collection.MapLike$class.toString(this);
    }

    @Override
    public Subtractable $minus(Object elem1, Object elem2, Seq elems) {
        return Subtractable$class.$minus(this, elem1, elem2, elems);
    }

    @Override
    public Subtractable $minus$minus(GenTraversableOnce xs) {
        return Subtractable$class.$minus$minus(this, xs);
    }

    @Override
    public <A1 extends A, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> that) {
        return PartialFunction$class.orElse(this, that);
    }

    @Override
    public <C> PartialFunction<A, C> andThen(Function1<B, C> k) {
        return PartialFunction$class.andThen(this, k);
    }

    @Override
    public Function1<A, Option<B>> lift() {
        return PartialFunction$class.lift(this);
    }

    @Override
    public <A1 extends A, B1> B1 applyOrElse(A1 x, Function1<A1, B1> function1) {
        return (B1)PartialFunction$class.applyOrElse(this, x, function1);
    }

    @Override
    public <U> Function1<A, Object> runWith(Function1<B, U> action) {
        return PartialFunction$class.runWith(this, action);
    }

    @Override
    public boolean apply$mcZD$sp(double v1) {
        return Function1$class.apply$mcZD$sp(this, v1);
    }

    @Override
    public double apply$mcDD$sp(double v1) {
        return Function1$class.apply$mcDD$sp(this, v1);
    }

    @Override
    public float apply$mcFD$sp(double v1) {
        return Function1$class.apply$mcFD$sp(this, v1);
    }

    @Override
    public int apply$mcID$sp(double v1) {
        return Function1$class.apply$mcID$sp(this, v1);
    }

    @Override
    public long apply$mcJD$sp(double v1) {
        return Function1$class.apply$mcJD$sp(this, v1);
    }

    @Override
    public void apply$mcVD$sp(double v1) {
        Function1$class.apply$mcVD$sp(this, v1);
    }

    @Override
    public boolean apply$mcZF$sp(float v1) {
        return Function1$class.apply$mcZF$sp(this, v1);
    }

    @Override
    public double apply$mcDF$sp(float v1) {
        return Function1$class.apply$mcDF$sp(this, v1);
    }

    @Override
    public float apply$mcFF$sp(float v1) {
        return Function1$class.apply$mcFF$sp(this, v1);
    }

    @Override
    public int apply$mcIF$sp(float v1) {
        return Function1$class.apply$mcIF$sp(this, v1);
    }

    @Override
    public long apply$mcJF$sp(float v1) {
        return Function1$class.apply$mcJF$sp(this, v1);
    }

    @Override
    public void apply$mcVF$sp(float v1) {
        Function1$class.apply$mcVF$sp(this, v1);
    }

    @Override
    public boolean apply$mcZI$sp(int v1) {
        return Function1$class.apply$mcZI$sp(this, v1);
    }

    @Override
    public double apply$mcDI$sp(int v1) {
        return Function1$class.apply$mcDI$sp(this, v1);
    }

    @Override
    public float apply$mcFI$sp(int v1) {
        return Function1$class.apply$mcFI$sp(this, v1);
    }

    @Override
    public int apply$mcII$sp(int v1) {
        return Function1$class.apply$mcII$sp(this, v1);
    }

    @Override
    public long apply$mcJI$sp(int v1) {
        return Function1$class.apply$mcJI$sp(this, v1);
    }

    @Override
    public void apply$mcVI$sp(int v1) {
        Function1$class.apply$mcVI$sp(this, v1);
    }

    @Override
    public boolean apply$mcZJ$sp(long v1) {
        return Function1$class.apply$mcZJ$sp(this, v1);
    }

    @Override
    public double apply$mcDJ$sp(long v1) {
        return Function1$class.apply$mcDJ$sp(this, v1);
    }

    @Override
    public float apply$mcFJ$sp(long v1) {
        return Function1$class.apply$mcFJ$sp(this, v1);
    }

    @Override
    public int apply$mcIJ$sp(long v1) {
        return Function1$class.apply$mcIJ$sp(this, v1);
    }

    @Override
    public long apply$mcJJ$sp(long v1) {
        return Function1$class.apply$mcJJ$sp(this, v1);
    }

    @Override
    public void apply$mcVJ$sp(long v1) {
        Function1$class.apply$mcVJ$sp(this, v1);
    }

    @Override
    public <A> Function1<A, B> compose(Function1<A, A> g) {
        return Function1$class.compose(this, g);
    }

    @Override
    public int hashCode() {
        return GenMapLike$class.hashCode(this);
    }

    @Override
    public boolean equals(Object that) {
        return GenMapLike$class.equals(this, that);
    }

    @Override
    public GenericCompanion<scala.collection.immutable.Iterable> companion() {
        return scala.collection.immutable.Iterable$class.companion(this);
    }

    @Override
    public Iterable<Tuple2<A, B>> thisCollection() {
        return IterableLike$class.thisCollection(this);
    }

    @Override
    public Iterable toCollection(Object repr) {
        return IterableLike$class.toCollection(this, repr);
    }

    @Override
    public boolean forall(Function1<Tuple2<A, B>, Object> p) {
        return IterableLike$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<Tuple2<A, B>, Object> p) {
        return IterableLike$class.exists(this, p);
    }

    @Override
    public Option<Tuple2<A, B>> find(Function1<Tuple2<A, B>, Object> p) {
        return IterableLike$class.find(this, p);
    }

    @Override
    public <B> B foldRight(B z, Function2<Tuple2<A, B>, B, B> op) {
        return (B)IterableLike$class.foldRight(this, z, op);
    }

    @Override
    public <B> B reduceRight(Function2<Tuple2<A, B>, B, B> op) {
        return (B)IterableLike$class.reduceRight(this, op);
    }

    @Override
    public Iterable<Tuple2<A, B>> toIterable() {
        return IterableLike$class.toIterable(this);
    }

    @Override
    public Iterator<Tuple2<A, B>> toIterator() {
        return IterableLike$class.toIterator(this);
    }

    @Override
    public Iterator<TreeMap<A, B>> grouped(int size2) {
        return IterableLike$class.grouped(this, size2);
    }

    @Override
    public Iterator<TreeMap<A, B>> sliding(int size2) {
        return IterableLike$class.sliding(this, size2);
    }

    @Override
    public Iterator<TreeMap<A, B>> sliding(int size2, int step) {
        return IterableLike$class.sliding(this, size2, step);
    }

    @Override
    public <B> void copyToArray(Object xs, int start, int len) {
        IterableLike$class.copyToArray(this, xs, start, len);
    }

    @Override
    public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<TreeMap<A, B>, Tuple2<A1, B>, That> bf) {
        return (That)IterableLike$class.zip(this, that, bf);
    }

    @Override
    public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<TreeMap<A, B>, Tuple2<A1, B>, That> bf) {
        return (That)IterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
    }

    @Override
    public <A1, That> That zipWithIndex(CanBuildFrom<TreeMap<A, B>, Tuple2<A1, Object>, That> bf) {
        return (That)IterableLike$class.zipWithIndex(this, bf);
    }

    @Override
    public <B> boolean sameElements(GenIterable<B> that) {
        return IterableLike$class.sameElements(this, that);
    }

    @Override
    public Stream<Tuple2<A, B>> toStream() {
        return IterableLike$class.toStream(this);
    }

    @Override
    public boolean canEqual(Object that) {
        return IterableLike$class.canEqual(this, that);
    }

    @Override
    public Object view() {
        return IterableLike$class.view(this);
    }

    @Override
    public IterableView<Tuple2<A, B>, TreeMap<A, B>> view(int from2, int until2) {
        return IterableLike$class.view(this, from2, until2);
    }

    @Override
    public <B> Builder<B, scala.collection.immutable.Iterable<B>> genericBuilder() {
        return GenericTraversableTemplate$class.genericBuilder(this);
    }

    @Override
    public <A1, A2> Tuple2<scala.collection.immutable.Iterable<A1>, scala.collection.immutable.Iterable<A2>> unzip(Function1<Tuple2<A, B>, Tuple2<A1, A2>> asPair) {
        return GenericTraversableTemplate$class.unzip(this, asPair);
    }

    @Override
    public <A1, A2, A3> Tuple3<scala.collection.immutable.Iterable<A1>, scala.collection.immutable.Iterable<A2>, scala.collection.immutable.Iterable<A3>> unzip3(Function1<Tuple2<A, B>, Tuple3<A1, A2, A3>> asTriple) {
        return GenericTraversableTemplate$class.unzip3(this, asTriple);
    }

    @Override
    public GenTraversable flatten(Function1 asTraversable) {
        return GenericTraversableTemplate$class.flatten(this, asTraversable);
    }

    @Override
    public GenTraversable transpose(Function1 asTraversable) {
        return GenericTraversableTemplate$class.transpose(this, asTraversable);
    }

    @Override
    public Object repr() {
        return TraversableLike$class.repr(this);
    }

    @Override
    public final boolean isTraversableAgain() {
        return TraversableLike$class.isTraversableAgain(this);
    }

    @Override
    public boolean hasDefiniteSize() {
        return TraversableLike$class.hasDefiniteSize(this);
    }

    @Override
    public <B, That> That $plus$plus(GenTraversableOnce<B> that, CanBuildFrom<TreeMap<A, B>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus(this, that, bf);
    }

    @Override
    public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<TreeMap<A, B>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
    }

    @Override
    public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<TreeMap<A, B>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
    }

    @Override
    public <B, That> That map(Function1<Tuple2<A, B>, B> f, CanBuildFrom<TreeMap<A, B>, B, That> bf) {
        return (That)TraversableLike$class.map(this, f, bf);
    }

    @Override
    public <B, That> That flatMap(Function1<Tuple2<A, B>, GenTraversableOnce<B>> f, CanBuildFrom<TreeMap<A, B>, B, That> bf) {
        return (That)TraversableLike$class.flatMap(this, f, bf);
    }

    @Override
    public Object filter(Function1 p) {
        return TraversableLike$class.filter(this, p);
    }

    @Override
    public <B, That> That collect(PartialFunction<Tuple2<A, B>, B> pf, CanBuildFrom<TreeMap<A, B>, B, That> bf) {
        return (That)TraversableLike$class.collect(this, pf, bf);
    }

    @Override
    public Tuple2<TreeMap<A, B>, TreeMap<A, B>> partition(Function1<Tuple2<A, B>, Object> p) {
        return TraversableLike$class.partition(this, p);
    }

    @Override
    public <K> scala.collection.immutable.Map<K, TreeMap<A, B>> groupBy(Function1<Tuple2<A, B>, K> f) {
        return TraversableLike$class.groupBy(this, f);
    }

    @Override
    public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<TreeMap<A, B>, B, That> cbf) {
        return (That)TraversableLike$class.scan(this, z, op, cbf);
    }

    @Override
    public <B, That> That scanLeft(B z, Function2<B, Tuple2<A, B>, B> op, CanBuildFrom<TreeMap<A, B>, B, That> bf) {
        return (That)TraversableLike$class.scanLeft(this, z, op, bf);
    }

    @Override
    public <B, That> That scanRight(B z, Function2<Tuple2<A, B>, B, B> op, CanBuildFrom<TreeMap<A, B>, B, That> bf) {
        return (That)TraversableLike$class.scanRight(this, z, op, bf);
    }

    @Override
    public Object sliceWithKnownDelta(int from2, int until2, int delta) {
        return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
    }

    @Override
    public Object sliceWithKnownBound(int from2, int until2) {
        return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
    }

    @Override
    public Iterator<TreeMap<A, B>> tails() {
        return TraversableLike$class.tails(this);
    }

    @Override
    public Iterator<TreeMap<A, B>> inits() {
        return TraversableLike$class.inits(this);
    }

    @Override
    public Traversable<Tuple2<A, B>> toTraversable() {
        return TraversableLike$class.toTraversable(this);
    }

    @Override
    public <Col> Col to(CanBuildFrom<Nothing$, Tuple2<A, B>, Col> cbf) {
        return (Col)TraversableLike$class.to(this, cbf);
    }

    @Override
    public FilterMonadic<Tuple2<A, B>, TreeMap<A, B>> withFilter(Function1<Tuple2<A, B>, Object> p) {
        return TraversableLike$class.withFilter(this, p);
    }

    @Override
    public Parallel par() {
        return Parallelizable$class.par(this);
    }

    @Override
    public List<Tuple2<A, B>> reversed() {
        return TraversableOnce$class.reversed(this);
    }

    @Override
    public boolean nonEmpty() {
        return TraversableOnce$class.nonEmpty(this);
    }

    @Override
    public int count(Function1<Tuple2<A, B>, Object> p) {
        return TraversableOnce$class.count(this, p);
    }

    @Override
    public <B> Option<B> collectFirst(PartialFunction<Tuple2<A, B>, B> pf) {
        return TraversableOnce$class.collectFirst(this, pf);
    }

    @Override
    public <B> B $div$colon(B z, Function2<B, Tuple2<A, B>, B> op) {
        return (B)TraversableOnce$class.$div$colon(this, z, op);
    }

    @Override
    public <B> B $colon$bslash(B z, Function2<Tuple2<A, B>, B, B> op) {
        return (B)TraversableOnce$class.$colon$bslash(this, z, op);
    }

    @Override
    public <B> B foldLeft(B z, Function2<B, Tuple2<A, B>, B> op) {
        return (B)TraversableOnce$class.foldLeft(this, z, op);
    }

    @Override
    public <B> B reduceLeft(Function2<B, Tuple2<A, B>, B> op) {
        return (B)TraversableOnce$class.reduceLeft(this, op);
    }

    @Override
    public <B> Option<B> reduceLeftOption(Function2<B, Tuple2<A, B>, B> op) {
        return TraversableOnce$class.reduceLeftOption(this, op);
    }

    @Override
    public <B> Option<B> reduceRightOption(Function2<Tuple2<A, B>, B, B> op) {
        return TraversableOnce$class.reduceRightOption(this, op);
    }

    @Override
    public <A1> A1 reduce(Function2<A1, A1, A1> op) {
        return (A1)TraversableOnce$class.reduce(this, op);
    }

    @Override
    public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
        return TraversableOnce$class.reduceOption(this, op);
    }

    @Override
    public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
        return (A1)TraversableOnce$class.fold(this, z, op);
    }

    @Override
    public <B> B aggregate(Function0<B> z, Function2<B, Tuple2<A, B>, B> seqop, Function2<B, B, B> combop) {
        return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
    }

    @Override
    public <B> B sum(Numeric<B> num) {
        return (B)TraversableOnce$class.sum(this, num);
    }

    @Override
    public <B> B product(Numeric<B> num) {
        return (B)TraversableOnce$class.product(this, num);
    }

    @Override
    public Object min(Ordering cmp) {
        return TraversableOnce$class.min(this, cmp);
    }

    @Override
    public Object max(Ordering cmp) {
        return TraversableOnce$class.max(this, cmp);
    }

    @Override
    public Object maxBy(Function1 f, Ordering cmp) {
        return TraversableOnce$class.maxBy(this, f, cmp);
    }

    @Override
    public Object minBy(Function1 f, Ordering cmp) {
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
    public List<Tuple2<A, B>> toList() {
        return TraversableOnce$class.toList(this);
    }

    @Override
    public IndexedSeq<Tuple2<A, B>> toIndexedSeq() {
        return TraversableOnce$class.toIndexedSeq(this);
    }

    @Override
    public <B> Set<B> toSet() {
        return TraversableOnce$class.toSet(this);
    }

    @Override
    public Vector<Tuple2<A, B>> toVector() {
        return TraversableOnce$class.toVector(this);
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
    public StringBuilder addString(StringBuilder b, String sep) {
        return TraversableOnce$class.addString(this, b, sep);
    }

    @Override
    public StringBuilder addString(StringBuilder b) {
        return TraversableOnce$class.addString(this, b);
    }

    @Override
    public Ordering<A> ordering() {
        return this.ordering;
    }

    @Override
    public Builder<Tuple2<A, B>, TreeMap<A, B>> newBuilder() {
        return TreeMap$.MODULE$.newBuilder(this.ordering());
    }

    @Override
    public int size() {
        return RedBlackTree$.MODULE$.count(this.tree);
    }

    @Override
    public TreeMap<A, B> rangeImpl(Option<A> from2, Option<A> until2) {
        return new TreeMap<A, B>(RedBlackTree$.MODULE$.rangeImpl(this.tree, from2, until2, this.ordering()), this.ordering());
    }

    @Override
    public TreeMap<A, B> range(A from2, A until2) {
        return new TreeMap<A, B>(RedBlackTree$.MODULE$.range(this.tree, from2, until2, this.ordering()), this.ordering());
    }

    @Override
    public TreeMap<A, B> from(A from2) {
        return new TreeMap<A, B>(RedBlackTree$.MODULE$.from(this.tree, from2, this.ordering()), this.ordering());
    }

    @Override
    public TreeMap<A, B> to(A to2) {
        return new TreeMap<A, B>(RedBlackTree$.MODULE$.to(this.tree, to2, this.ordering()), this.ordering());
    }

    @Override
    public TreeMap<A, B> until(A until2) {
        return new TreeMap<A, B>(RedBlackTree$.MODULE$.until(this.tree, until2, this.ordering()), this.ordering());
    }

    @Override
    public A firstKey() {
        return RedBlackTree$.MODULE$.smallest(this.tree).key();
    }

    @Override
    public A lastKey() {
        return RedBlackTree$.MODULE$.greatest(this.tree).key();
    }

    @Override
    public int compare(A k0, A k1) {
        return this.ordering().compare(k0, k1);
    }

    @Override
    public Tuple2<A, B> head() {
        RedBlackTree.Tree<A, B> smallest = RedBlackTree$.MODULE$.smallest(this.tree);
        return new Tuple2<A, B>(smallest.key(), smallest.value());
    }

    @Override
    public Option<Tuple2<A, B>> headOption() {
        return RedBlackTree$.MODULE$.isEmpty(this.tree) ? None$.MODULE$ : new Some<Object>(this.head());
    }

    @Override
    public Tuple2<A, B> last() {
        RedBlackTree.Tree<A, B> greatest = RedBlackTree$.MODULE$.greatest(this.tree);
        return new Tuple2<A, B>(greatest.key(), greatest.value());
    }

    @Override
    public Option<Tuple2<A, B>> lastOption() {
        return RedBlackTree$.MODULE$.isEmpty(this.tree) ? None$.MODULE$ : new Some<Object>(this.last());
    }

    @Override
    public TreeMap<A, B> tail() {
        return new TreeMap<A, B>(RedBlackTree$.MODULE$.delete(this.tree, this.firstKey(), this.ordering()), this.ordering());
    }

    @Override
    public TreeMap<A, B> init() {
        return new TreeMap<A, B>(RedBlackTree$.MODULE$.delete(this.tree, this.lastKey(), this.ordering()), this.ordering());
    }

    @Override
    public TreeMap<A, B> drop(int n) {
        return n <= 0 ? this : (n >= this.size() ? this.empty() : new TreeMap<A, B>(RedBlackTree$.MODULE$.drop(this.tree, n, this.ordering()), this.ordering()));
    }

    @Override
    public TreeMap<A, B> take(int n) {
        return n <= 0 ? this.empty() : (n >= this.size() ? this : new TreeMap<A, B>(RedBlackTree$.MODULE$.take(this.tree, n, this.ordering()), this.ordering()));
    }

    @Override
    public TreeMap<A, B> slice(int from2, int until2) {
        return until2 <= from2 ? this.empty() : (from2 <= 0 ? this.take(until2) : (until2 >= this.size() ? this.drop(from2) : new TreeMap<A, B>(RedBlackTree$.MODULE$.slice(this.tree, from2, until2, this.ordering()), this.ordering())));
    }

    @Override
    public TreeMap<A, B> dropRight(int n) {
        return this.take(this.size() - package$.MODULE$.max(n, 0));
    }

    @Override
    public TreeMap<A, B> takeRight(int n) {
        return this.drop(this.size() - package$.MODULE$.max(n, 0));
    }

    @Override
    public Tuple2<TreeMap<A, B>, TreeMap<A, B>> splitAt(int n) {
        return new Tuple2<Object, Object>(this.take(n), this.drop(n));
    }

    /*
     * WARNING - void declaration
     */
    private int countWhile(Function1<Tuple2<A, B>, Object> p) {
        void var2_2;
        int result2 = 0;
        Iterator<Tuple2<A, B>> it = this.iterator();
        while (it.hasNext() && BoxesRunTime.unboxToBoolean(p.apply(it.next()))) {
            ++result2;
        }
        return (int)var2_2;
    }

    @Override
    public TreeMap<A, B> dropWhile(Function1<Tuple2<A, B>, Object> p) {
        return this.drop(this.countWhile(p));
    }

    @Override
    public TreeMap<A, B> takeWhile(Function1<Tuple2<A, B>, Object> p) {
        return this.take(this.countWhile(p));
    }

    @Override
    public Tuple2<TreeMap<A, B>, TreeMap<A, B>> span(Function1<Tuple2<A, B>, Object> p) {
        return this.splitAt(this.countWhile(p));
    }

    @Override
    public TreeMap<A, B> empty() {
        return TreeMap$.MODULE$.empty((Ordering)this.ordering());
    }

    @Override
    public <B1> TreeMap<A, B1> updated(A key, B1 value) {
        return new TreeMap<A, B1>(RedBlackTree$.MODULE$.update(this.tree, key, value, true, this.ordering()), this.ordering());
    }

    @Override
    public <B1> TreeMap<A, B1> $plus(Tuple2<A, B1> kv) {
        return this.updated((Object)kv._1(), (Object)kv._2());
    }

    @Override
    public <B1> TreeMap<A, B1> $plus(Tuple2<A, B1> elem1, Tuple2<A, B1> elem2, Seq<Tuple2<A, B1>> elems) {
        return ((TreeMap)((TreeMap)this.$plus((Tuple2)elem1)).$plus((Tuple2)elem2)).$plus$plus(elems);
    }

    @Override
    public <B1> TreeMap<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> xs) {
        TreeMap treeMap = (TreeMap)this.repr();
        return xs.seq().$div$colon(treeMap, new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final TreeMap<A, B1> apply(TreeMap<A, B1> x$2, Tuple2<A, B1> x$3) {
                return x$2.$plus((Tuple2)x$3);
            }
        });
    }

    public <B1> TreeMap<A, B1> insert(A key, B1 value) {
        Predef$.MODULE$.assert(!RedBlackTree$.MODULE$.contains(this.tree, key, this.ordering()));
        return new TreeMap<A, B1>(RedBlackTree$.MODULE$.update(this.tree, key, value, true, this.ordering()), this.ordering());
    }

    @Override
    public TreeMap<A, B> $minus(A key) {
        return RedBlackTree$.MODULE$.contains(this.tree, key, this.ordering()) ? new TreeMap<A, B>(RedBlackTree$.MODULE$.delete(this.tree, key, this.ordering()), this.ordering()) : this;
    }

    @Override
    public Option<B> get(A key) {
        return RedBlackTree$.MODULE$.get(this.tree, key, this.ordering());
    }

    @Override
    public Iterator<Tuple2<A, B>> iterator() {
        return RedBlackTree$.MODULE$.iterator(this.tree, RedBlackTree$.MODULE$.iterator$default$2(), this.ordering());
    }

    @Override
    public Iterator<Tuple2<A, B>> iteratorFrom(A start) {
        return RedBlackTree$.MODULE$.iterator(this.tree, new Some<A>(start), this.ordering());
    }

    @Override
    public Iterator<A> keysIterator() {
        return RedBlackTree$.MODULE$.keysIterator(this.tree, RedBlackTree$.MODULE$.keysIterator$default$2(), this.ordering());
    }

    @Override
    public Iterator<A> keysIteratorFrom(A start) {
        return RedBlackTree$.MODULE$.keysIterator(this.tree, new Some<A>(start), this.ordering());
    }

    @Override
    public Iterator<B> valuesIterator() {
        return RedBlackTree$.MODULE$.valuesIterator(this.tree, RedBlackTree$.MODULE$.valuesIterator$default$2(), this.ordering());
    }

    @Override
    public Iterator<B> valuesIteratorFrom(A start) {
        return RedBlackTree$.MODULE$.valuesIterator(this.tree, new Some<A>(start), this.ordering());
    }

    @Override
    public boolean contains(A key) {
        return RedBlackTree$.MODULE$.contains(this.tree, key, this.ordering());
    }

    @Override
    public boolean isDefinedAt(A key) {
        return RedBlackTree$.MODULE$.contains(this.tree, key, this.ordering());
    }

    @Override
    public <U> void foreach(Function1<Tuple2<A, B>, U> f) {
        RedBlackTree$.MODULE$.foreach(this.tree, f);
    }

    private TreeMap(RedBlackTree.Tree<A, B> tree, Ordering<A> ordering) {
        this.tree = tree;
        this.ordering = ordering;
        TraversableOnce$class.$init$(this);
        Parallelizable$class.$init$(this);
        TraversableLike$class.$init$(this);
        GenericTraversableTemplate$class.$init$(this);
        GenTraversable$class.$init$(this);
        Traversable$class.$init$(this);
        scala.collection.immutable.Traversable$class.$init$(this);
        GenIterable$class.$init$(this);
        IterableLike$class.$init$(this);
        Iterable$class.$init$(this);
        scala.collection.immutable.Iterable$class.$init$(this);
        GenMapLike$class.$init$(this);
        Function1$class.$init$(this);
        PartialFunction$class.$init$(this);
        Subtractable$class.$init$(this);
        scala.collection.MapLike$class.$init$(this);
        scala.collection.Map$class.$init$(this);
        MapLike$class.$init$(this);
        Map$class.$init$(this);
        Sorted$class.$init$(this);
        SortedMapLike$class.$init$(this);
        SortedMap$class.$init$(this);
        scala.collection.immutable.SortedMap$class.$init$(this);
    }

    public TreeMap(Ordering<A> ordering) {
        this(null, ordering);
    }
}

