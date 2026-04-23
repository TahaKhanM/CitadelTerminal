/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import java.util.concurrent.atomic.AtomicInteger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.CustomParallelizable$class;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenMapLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Iterator$class;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.DelegatedSignalling$class;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericParMapCompanion;
import scala.collection.generic.GenericParMapTemplate$class;
import scala.collection.generic.GenericParTemplate$class;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Signalling;
import scala.collection.immutable.HashMap;
import scala.collection.immutable.HashMap$;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.TrieIterator;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.AugmentedIterableIterator$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.IterableSplitter$class;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.ParIterableLike$ScanLeaf$;
import scala.collection.parallel.ParIterableLike$ScanNode$;
import scala.collection.parallel.ParIterableLike$class;
import scala.collection.parallel.ParMap;
import scala.collection.parallel.ParMapLike$class;
import scala.collection.parallel.ParSet;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.RemainsIterator$class;
import scala.collection.parallel.SeqSplitter;
import scala.collection.parallel.Splitter;
import scala.collection.parallel.TaskSupport;
import scala.collection.parallel.immutable.HashMapCombiner;
import scala.collection.parallel.immutable.HashMapCombiner$;
import scala.collection.parallel.immutable.ParHashMap$;
import scala.collection.parallel.immutable.ParIterable;
import scala.collection.parallel.immutable.ParIterable$class;
import scala.collection.parallel.immutable.ParMap$class;
import scala.collection.parallel.immutable.ParSeq;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.TraitSetter;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@ScalaSignature(bytes="\u0006\u0001\tud!B\u0001\u0003\u0001-q#A\u0003)be\"\u000b7\u000f['ba*\u00111\u0001B\u0001\nS6lW\u000f^1cY\u0016T!!\u0002\u0004\u0002\u0011A\f'/\u00197mK2T!a\u0002\u0005\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\n\u0003\u0015\u00198-\u00197b\u0007\u0001)2\u0001D\f\"'\u0019\u0001Q\"E\u0012+iA\u0011abD\u0007\u0002\u0011%\u0011\u0001\u0003\u0003\u0002\u0007\u0003:L(+\u001a4\u0011\tI\u0019R\u0003I\u0007\u0002\u0005%\u0011AC\u0001\u0002\u0007!\u0006\u0014X*\u00199\u0011\u0005Y9B\u0002\u0001\u0003\u00061\u0001\u0011\r!\u0007\u0002\u0002\u0017F\u0011!$\b\t\u0003\u001dmI!\u0001\b\u0005\u0003\u000f9{G\u000f[5oOB\u0011aBH\u0005\u0003?!\u00111!\u00118z!\t1\u0012\u0005\u0002\u0004#\u0001\u0011\u0015\r!\u0007\u0002\u0002-B)AeJ\u000b!S5\tQE\u0003\u0002'\r\u00059q-\u001a8fe&\u001c\u0017B\u0001\u0015&\u0005U9UM\\3sS\u000e\u0004\u0016M]'baR+W\u000e\u001d7bi\u0016\u0004\"A\u0005\u0001\u0011\r-bS\u0003\t\u00180\u001b\u0005!\u0011BA\u0017\u0005\u0005)\u0001\u0016M]'ba2K7.\u001a\t\u0005%\u0001)\u0002\u0005\u0005\u00031eU\u0001S\"A\u0019\u000b\u0005\r1\u0011BA\u001a2\u0005\u001dA\u0015m\u001d5NCB\u0004\"AD\u001b\n\u0005YB!\u0001D*fe&\fG.\u001b>bE2,\u0007\u0002\u0003\u001d\u0001\u0005\u0003\u0005\u000b\u0011B\u0018\u0002\tQ\u0014\u0018.\u001a\u0005\u0007u\u0001!\tAA\u001e\u0002\rqJg.\u001b;?)\tqC\bC\u00039s\u0001\u0007q\u0006C\u0003;\u0001\u0011\u0005a\bF\u0001/\u0011\u0015\u0001\u0005\u0001\"\u0011B\u00031i\u0017\r]\"p[B\fg.[8o+\u0005\u0011\u0005c\u0001\u0013DS%\u0011A)\n\u0002\u0017\u000f\u0016tWM]5d!\u0006\u0014X*\u00199D_6\u0004\u0018M\\5p]\")a\t\u0001C!\u000f\u0006)Q-\u001c9usV\ta\u0006\u0003\u0004J\u0001\u0001&\tFS\u0001\f]\u0016<8i\\7cS:,'/F\u0001L!\u0011\u0011B*\u0006\u0011\n\u00055\u0013!a\u0004%bg\"l\u0015\r]\"p[\nLg.\u001a:\t\u000b=\u0003A\u0011\u0001)\u0002\u0011M\u0004H.\u001b;uKJ,\u0012!\u0015\t\u0004WI#\u0016BA*\u0005\u0005AIE/\u001a:bE2,7\u000b\u001d7jiR,'\u000f\u0005\u0003\u000f+V\u0001\u0013B\u0001,\t\u0005\u0019!V\u000f\u001d7fe!)\u0001\f\u0001C!3\u0006\u00191/Z9\u0016\u0003=BQa\u0017\u0001\u0005\u0002q\u000ba\u0001J7j]V\u001cHC\u0001\u0018^\u0011\u0015q&\f1\u0001\u0016\u0003\u0005Y\u0007\"\u00021\u0001\t\u0003\t\u0017!\u0002\u0013qYV\u001cXC\u00012f)\t\u0019\u0007\u000e\u0005\u0003\u0013\u0001U!\u0007C\u0001\ff\t\u00151wL1\u0001h\u0005\u0005)\u0016C\u0001\u0011\u001e\u0011\u0015Iw\f1\u0001k\u0003\tYg\u000f\u0005\u0003\u000f+V!\u0007\"\u00027\u0001\t\u0003i\u0017aA4fiR\u0011a.\u001d\t\u0004\u001d=\u0004\u0013B\u00019\t\u0005\u0019y\u0005\u000f^5p]\")al\u001ba\u0001+!)1\u000f\u0001C!i\u0006!1/\u001b>f+\u0005)\bC\u0001\bw\u0013\t9\bBA\u0002J]RDQ!\u001f\u0001\u0005Ri\fQA]3vg\u0016,Ra_A\u0001\u0003\u000f!R\u0001`A\u0006\u0003#\u0001RaK?\u0000\u0003\u000bI!A \u0003\u0003\u0011\r{WNY5oKJ\u00042AFA\u0001\t\u0019\t\u0019\u0001\u001fb\u00013\t\t1\u000bE\u0002\u0017\u0003\u000f!a!!\u0003y\u0005\u0004I\"\u0001\u0002+iCRDq!!\u0004y\u0001\u0004\ty!\u0001\u0003pY\u0012\u001c\u0007c\u0001\bpy\"1\u00111\u0003=A\u0002q\fAA\\3xG\u001a1\u0011q\u0003\u0001\u0001\u00033\u0011!\u0003U1s\u0011\u0006\u001c\b.T1q\u0013R,'/\u0019;peN!\u0011QC\u0007R\u0011-\ti\"!\u0006\u0003\u0002\u0004%\t!a\b\u0002\rQ\u0014\u0018\u000e^3s+\t\t\t\u0003\u0005\u0004\u0002$\u0005%\u0012q\u0006\b\u0004\u001d\u0005\u0015\u0012bAA\u0014\u0011\u00059\u0001/Y2lC\u001e,\u0017\u0002BA\u0016\u0003[\u0011\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0004\u0003OA\u0001#\u0002\bV+\u0005E\"f\u0001\u0011\u00024-\u0012\u0011Q\u0007\t\u0005\u0003o\t\t%\u0004\u0002\u0002:)!\u00111HA\u001f\u0003%)hn\u00195fG.,GMC\u0002\u0002@!\t!\"\u00198o_R\fG/[8o\u0013\u0011\t\u0019%!\u000f\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rC\u0006\u0002H\u0005U!\u00111A\u0005\u0002\u0005%\u0013A\u0003;sSR,'o\u0018\u0013fcR!\u00111JA)!\rq\u0011QJ\u0005\u0004\u0003\u001fB!\u0001B+oSRD!\"a\u0015\u0002F\u0005\u0005\t\u0019AA\u0011\u0003\rAH%\r\u0005\f\u0003/\n)B!A!B\u0013\t\t#A\u0004ue&$XM\u001d\u0011\t\u0015\u0005m\u0013Q\u0003BC\u0002\u0013\u0005A/\u0001\u0002tu\"Q\u0011qLA\u000b\u0005\u0003\u0005\u000b\u0011B;\u0002\u0007MT\b\u0005C\u0004;\u0003+!\t!a\u0019\u0015\r\u0005\u0015\u0014\u0011NA6!\u0011\t9'!\u0006\u000e\u0003\u0001A\u0001\"!\b\u0002b\u0001\u0007\u0011\u0011\u0005\u0005\b\u00037\n\t\u00071\u0001v\u0011%\ty'!\u0006A\u0002\u0013\u0005A/A\u0001j\u0011)\t\u0019(!\u0006A\u0002\u0013\u0005\u0011QO\u0001\u0006S~#S-\u001d\u000b\u0005\u0003\u0017\n9\bC\u0005\u0002T\u0005E\u0014\u0011!a\u0001k\"A\u00111PA\u000bA\u0003&Q/\u0001\u0002jA!9\u0011qPA\u000b\t\u0003\u0001\u0016a\u00013va\"A\u00111QA\u000b\t\u0013\t))A\bekB4%o\\7Ji\u0016\u0014\u0018\r^8s)\u0011\t)'a\"\t\u0011\u0005%\u0015\u0011\u0011a\u0001\u0003C\t!!\u001b;\t\u0011\u00055\u0015Q\u0003C\u0001\u0003\u001f\u000bQa\u001d9mSR,\"!!%\u0011\u000b\u0005\r\u00121S)\n\t\u0005U\u0015Q\u0006\u0002\u0004'\u0016\f\b\u0002CAM\u0003+!\t!a'\u0002\t9,\u0007\u0010\u001e\u000b\u0002)\"A\u0011qTA\u000b\t\u0003\t\t+A\u0004iCNtU\r\u001f;\u0016\u0005\u0005\r\u0006c\u0001\b\u0002&&\u0019\u0011q\u0015\u0005\u0003\u000f\t{w\u000e\\3b]\"9\u00111VA\u000b\t\u0003!\u0018!\u0003:f[\u0006Lg.\u001b8h\u0011!\ty+!\u0006\u0005B\u0005E\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005M\u0006\u0003BA[\u0003\u007fk!!a.\u000b\t\u0005e\u00161X\u0001\u0005Y\u0006twM\u0003\u0002\u0002>\u0006!!.\u0019<b\u0013\u0011\t\t-a.\u0003\rM#(/\u001b8h\u0011!\t)\r\u0001C\u0001\t\u0005\u001d\u0017A\u00049sS:$H)\u001a2vO&sgm\u001c\u000b\u0003\u0003\u0017Bs\u0001AAf\u0003#\f\u0019\u000eE\u0002\u000f\u0003\u001bL1!a4\t\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0002\u000f\u001d\t9N\u0001E\u0001\u00033\f!\u0002U1s\u0011\u0006\u001c\b.T1q!\r\u0011\u00121\u001c\u0004\u0007\u0003\tA\t!!8\u0014\u000b\u0005m\u0017q\u001c\u001b\u0011\t\u0011\n\t/K\u0005\u0004\u0003G,#!\u0004)be6\u000b\u0007OR1di>\u0014\u0018\u0010C\u0004;\u00037$\t!a:\u0015\u0005\u0005e\u0007b\u0002$\u0002\\\u0012\u0005\u00111^\u000b\u0007\u0003[\f\u00190a>\u0016\u0005\u0005=\bC\u0002\n\u0001\u0003c\f)\u0010E\u0002\u0017\u0003g$a\u0001GAu\u0005\u0004I\u0002c\u0001\f\u0002x\u00121!%!;C\u0002eAq!SAn\t\u0003\tY0\u0006\u0004\u0002~\n\u0015!\u0011B\u000b\u0003\u0003\u007f\u0004baK?\u0003\u0002\t-\u0001C\u0002\bV\u0005\u0007\u00119\u0001E\u0002\u0017\u0005\u000b!a\u0001GA}\u0005\u0004I\u0002c\u0001\f\u0003\n\u00111!%!?C\u0002e\u0001bA\u0005\u0001\u0003\u0004\t\u001d\u0001\u0002\u0003B\b\u00037$\u0019A!\u0005\u0002\u0019\r\fgNQ;jY\u00124%o\\7\u0016\r\tM!1\u0006B\u0018+\t\u0011)\u0002E\u0005%\u0005/\u0011YBa\n\u00032%\u0019!\u0011D\u0013\u0003\u001d\r\u000bgnQ8nE&tWM\u0012:p[B!!Q\u0004B\u0010\u001b\t\tY.\u0003\u0003\u0003\"\t\r\"\u0001B\"pY2L1A!\n&\u000559UM\\'ba\u001a\u000b7\r^8ssB1a\"\u0016B\u0015\u0005[\u00012A\u0006B\u0016\t\u0019A\"Q\u0002b\u00013A\u0019aCa\f\u0005\r\t\u0012iA1\u0001\u001a!\u0019\u0011\u0002A!\u000b\u0003.!A!QGAn\t\u0003\u00119$\u0001\u0005ge>lGK]5f+\u0019\u0011IDa\u0010\u0003DQ!!1\bB#!\u0019\u0011\u0002A!\u0010\u0003BA\u0019aCa\u0010\u0005\ra\u0011\u0019D1\u0001\u001a!\r1\"1\t\u0003\u0007E\tM\"\u0019A\r\t\u0011\t\u001d#1\u0007a\u0001\u0005\u0013\n\u0011\u0001\u001e\t\u0007aI\u0012iD!\u0011\t\u0015\t5\u00131\u001ca\u0001\n\u0003\u0011y%A\u0007u_R\fGnY8nE&tWm]\u000b\u0003\u0005#\u0002BAa\u0015\u0003b5\u0011!Q\u000b\u0006\u0005\u0005/\u0012I&\u0001\u0004bi>l\u0017n\u0019\u0006\u0005\u00057\u0012i&\u0001\u0006d_:\u001cWO\u001d:f]RTAAa\u0018\u0002<\u0006!Q\u000f^5m\u0013\u0011\u0011\u0019G!\u0016\u0003\u001b\u0005#x.\\5d\u0013:$XmZ3s\u0011)\u00119'a7A\u0002\u0013\u0005!\u0011N\u0001\u0012i>$\u0018\r\\2p[\nLg.Z:`I\u0015\fH\u0003BA&\u0005WB!\"a\u0015\u0003f\u0005\u0005\t\u0019\u0001B)\u0011%\u0011y'a7!B\u0013\u0011\t&\u0001\bu_R\fGnY8nE&tWm\u001d\u0011\t\u0015\tM\u00141\\A\u0001\n\u0013\u0011)(A\u0006sK\u0006$'+Z:pYZ,GC\u0001B<!\u0011\t)L!\u001f\n\t\tm\u0014q\u0017\u0002\u0007\u001f\nTWm\u0019;")
public class ParHashMap<K, V>
implements scala.collection.parallel.immutable.ParMap<K, V>,
Serializable {
    public static final long serialVersionUID = 1L;
    private final HashMap<K, V> trie;
    private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
    private volatile ParIterableLike$ScanNode$ ScanNode$module;
    private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;

    public static void totalcombines_$eq(AtomicInteger atomicInteger) {
        ParHashMap$.MODULE$.totalcombines_$eq(atomicInteger);
    }

    public static AtomicInteger totalcombines() {
        return ParHashMap$.MODULE$.totalcombines();
    }

    public static <K, V> ParHashMap<K, V> fromTrie(HashMap<K, V> hashMap) {
        return ParHashMap$.MODULE$.fromTrie(hashMap);
    }

    public static <K, V> CanCombineFrom<ParHashMap<?, ?>, Tuple2<K, V>, ParHashMap<K, V>> canBuildFrom() {
        return ParHashMap$.MODULE$.canBuildFrom();
    }

    @Override
    public String stringPrefix() {
        return ParMap$class.stringPrefix(this);
    }

    @Override
    public <P, Q> scala.collection.parallel.immutable.ParMap<P, Q> toMap(Predef$.less.colon.less<Tuple2<K, V>, Tuple2<P, Q>> ev) {
        return ParMap$class.toMap(this, ev);
    }

    @Override
    public <U> scala.collection.parallel.immutable.ParMap<K, U> updated(K key, U value) {
        return ParMap$class.updated(this, key, value);
    }

    @Override
    public <U> scala.collection.parallel.immutable.ParMap<K, U> withDefault(Function1<K, U> d) {
        return ParMap$class.withDefault(this, d);
    }

    @Override
    public <U> scala.collection.parallel.immutable.ParMap<K, U> withDefaultValue(U d) {
        return ParMap$class.withDefaultValue(this, d);
    }

    @Override
    public GenericCompanion<ParIterable> companion() {
        return ParIterable$class.companion(this);
    }

    @Override
    public ParIterable<Tuple2<K, V>> toIterable() {
        return ParIterable$class.toIterable(this);
    }

    @Override
    public ParSeq<Tuple2<K, V>> toSeq() {
        return ParIterable$class.toSeq(this);
    }

    @Override
    public V default(K key) {
        return (V)ParMapLike$class.default(this, key);
    }

    @Override
    public V apply(K key) {
        return (V)ParMapLike$class.apply(this, key);
    }

    @Override
    public <U> U getOrElse(K key, Function0<U> function0) {
        return (U)ParMapLike$class.getOrElse(this, key, function0);
    }

    @Override
    public boolean contains(K key) {
        return ParMapLike$class.contains(this, key);
    }

    @Override
    public boolean isDefinedAt(K key) {
        return ParMapLike$class.isDefinedAt(this, key);
    }

    @Override
    public IterableSplitter<K> keysIterator() {
        return ParMapLike$class.keysIterator(this);
    }

    @Override
    public IterableSplitter<V> valuesIterator() {
        return ParMapLike$class.valuesIterator(this);
    }

    @Override
    public ParSet<K> keySet() {
        return ParMapLike$class.keySet(this);
    }

    @Override
    public scala.collection.parallel.ParIterable<K> keys() {
        return ParMapLike$class.keys(this);
    }

    @Override
    public scala.collection.parallel.ParIterable<V> values() {
        return ParMapLike$class.values(this);
    }

    @Override
    public ParMap<K, V> filterKeys(Function1<K, Object> p) {
        return ParMapLike$class.filterKeys(this, p);
    }

    @Override
    public <S> ParMap<K, S> mapValues(Function1<V, S> f) {
        return ParMapLike$class.mapValues(this, f);
    }

    @Override
    public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
        return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
    }

    @Override
    @TraitSetter
    public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
        this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
    }

    private ParIterableLike$ScanNode$ ScanNode$lzycompute() {
        ParHashMap parHashMap = this;
        synchronized (parHashMap) {
            if (this.ScanNode$module == null) {
                this.ScanNode$module = new ParIterableLike$ScanNode$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ScanNode$module;
        }
    }

    @Override
    public ParIterableLike$ScanNode$ ScanNode() {
        return this.ScanNode$module == null ? this.ScanNode$lzycompute() : this.ScanNode$module;
    }

    private ParIterableLike$ScanLeaf$ ScanLeaf$lzycompute() {
        ParHashMap parHashMap = this;
        synchronized (parHashMap) {
            if (this.ScanLeaf$module == null) {
                this.ScanLeaf$module = new ParIterableLike$ScanLeaf$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ScanLeaf$module;
        }
    }

    @Override
    public ParIterableLike$ScanLeaf$ ScanLeaf() {
        return this.ScanLeaf$module == null ? this.ScanLeaf$lzycompute() : this.ScanLeaf$module;
    }

    @Override
    public void initTaskSupport() {
        ParIterableLike$class.initTaskSupport(this);
    }

    @Override
    public TaskSupport tasksupport() {
        return ParIterableLike$class.tasksupport(this);
    }

    @Override
    public void tasksupport_$eq(TaskSupport ts) {
        ParIterableLike$class.tasksupport_$eq(this, ts);
    }

    @Override
    public scala.collection.parallel.ParIterable repr() {
        return ParIterableLike$class.repr(this);
    }

    @Override
    public final boolean isTraversableAgain() {
        return ParIterableLike$class.isTraversableAgain(this);
    }

    @Override
    public boolean hasDefiniteSize() {
        return ParIterableLike$class.hasDefiniteSize(this);
    }

    @Override
    public boolean isEmpty() {
        return ParIterableLike$class.isEmpty(this);
    }

    @Override
    public boolean nonEmpty() {
        return ParIterableLike$class.nonEmpty(this);
    }

    @Override
    public Object head() {
        return ParIterableLike$class.head(this);
    }

    @Override
    public Option<Tuple2<K, V>> headOption() {
        return ParIterableLike$class.headOption(this);
    }

    @Override
    public scala.collection.parallel.ParIterable tail() {
        return ParIterableLike$class.tail(this);
    }

    @Override
    public Object last() {
        return ParIterableLike$class.last(this);
    }

    @Override
    public Option<Tuple2<K, V>> lastOption() {
        return ParIterableLike$class.lastOption(this);
    }

    @Override
    public scala.collection.parallel.ParIterable init() {
        return ParIterableLike$class.init(this);
    }

    @Override
    public Splitter<Tuple2<K, V>> iterator() {
        return ParIterableLike$class.iterator(this);
    }

    @Override
    public scala.collection.parallel.ParIterable par() {
        return ParIterableLike$class.par(this);
    }

    @Override
    public boolean isStrictSplitterCollection() {
        return ParIterableLike$class.isStrictSplitterCollection(this);
    }

    @Override
    public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask<R, Tp> tsk) {
        return ParIterableLike$class.task2ops(this, tsk);
    }

    @Override
    public <R> Object wrap(Function0<R> body2) {
        return ParIterableLike$class.wrap(this, body2);
    }

    @Override
    public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(PI it) {
        return ParIterableLike$class.delegatedSignalling2ops(this, it);
    }

    @Override
    public <Elem, To> Object builder2ops(Builder<Elem, To> cb) {
        return ParIterableLike$class.builder2ops(this, cb);
    }

    @Override
    public <S, That> Object bf2seq(CanBuildFrom<ParHashMap<K, V>, S, That> bf) {
        return ParIterableLike$class.bf2seq(this, bf);
    }

    @Override
    public scala.collection.parallel.ParIterable sequentially(Function1 b) {
        return ParIterableLike$class.sequentially(this, b);
    }

    @Override
    public String mkString(String start, String sep, String end) {
        return ParIterableLike$class.mkString(this, start, sep, end);
    }

    @Override
    public String mkString(String sep) {
        return ParIterableLike$class.mkString(this, sep);
    }

    @Override
    public String mkString() {
        return ParIterableLike$class.mkString(this);
    }

    @Override
    public String toString() {
        return ParIterableLike$class.toString(this);
    }

    @Override
    public boolean canEqual(Object other) {
        return ParIterableLike$class.canEqual(this, other);
    }

    @Override
    public <U> U reduce(Function2<U, U, U> op) {
        return (U)ParIterableLike$class.reduce(this, op);
    }

    @Override
    public <U> Option<U> reduceOption(Function2<U, U, U> op) {
        return ParIterableLike$class.reduceOption(this, op);
    }

    @Override
    public <U> U fold(U z, Function2<U, U, U> op) {
        return (U)ParIterableLike$class.fold(this, z, op);
    }

    @Override
    public <S> S aggregate(Function0<S> z, Function2<S, Tuple2<K, V>, S> seqop, Function2<S, S, S> combop) {
        return (S)ParIterableLike$class.aggregate(this, z, seqop, combop);
    }

    @Override
    public <S> S foldLeft(S z, Function2<S, Tuple2<K, V>, S> op) {
        return (S)ParIterableLike$class.foldLeft(this, z, op);
    }

    @Override
    public <S> S foldRight(S z, Function2<Tuple2<K, V>, S, S> op) {
        return (S)ParIterableLike$class.foldRight(this, z, op);
    }

    @Override
    public <U> U reduceLeft(Function2<U, Tuple2<K, V>, U> op) {
        return (U)ParIterableLike$class.reduceLeft(this, op);
    }

    @Override
    public <U> U reduceRight(Function2<Tuple2<K, V>, U, U> op) {
        return (U)ParIterableLike$class.reduceRight(this, op);
    }

    @Override
    public <U> Option<U> reduceLeftOption(Function2<U, Tuple2<K, V>, U> op) {
        return ParIterableLike$class.reduceLeftOption(this, op);
    }

    @Override
    public <U> Option<U> reduceRightOption(Function2<Tuple2<K, V>, U, U> op) {
        return ParIterableLike$class.reduceRightOption(this, op);
    }

    @Override
    public <U> void foreach(Function1<Tuple2<K, V>, U> f) {
        ParIterableLike$class.foreach(this, f);
    }

    @Override
    public int count(Function1<Tuple2<K, V>, Object> p) {
        return ParIterableLike$class.count(this, p);
    }

    @Override
    public <U> U sum(Numeric<U> num) {
        return (U)ParIterableLike$class.sum(this, num);
    }

    @Override
    public <U> U product(Numeric<U> num) {
        return (U)ParIterableLike$class.product(this, num);
    }

    @Override
    public Object min(Ordering ord) {
        return ParIterableLike$class.min(this, ord);
    }

    @Override
    public Object max(Ordering ord) {
        return ParIterableLike$class.max(this, ord);
    }

    @Override
    public Object maxBy(Function1 f, Ordering cmp) {
        return ParIterableLike$class.maxBy(this, f, cmp);
    }

    @Override
    public Object minBy(Function1 f, Ordering cmp) {
        return ParIterableLike$class.minBy(this, f, cmp);
    }

    @Override
    public <S, That> That map(Function1<Tuple2<K, V>, S> f, CanBuildFrom<ParHashMap<K, V>, S, That> bf) {
        return (That)ParIterableLike$class.map(this, f, bf);
    }

    @Override
    public <S, That> That collect(PartialFunction<Tuple2<K, V>, S> pf, CanBuildFrom<ParHashMap<K, V>, S, That> bf) {
        return (That)ParIterableLike$class.collect(this, pf, bf);
    }

    @Override
    public <S, That> That flatMap(Function1<Tuple2<K, V>, GenTraversableOnce<S>> f, CanBuildFrom<ParHashMap<K, V>, S, That> bf) {
        return (That)ParIterableLike$class.flatMap(this, f, bf);
    }

    @Override
    public boolean forall(Function1<Tuple2<K, V>, Object> p) {
        return ParIterableLike$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<Tuple2<K, V>, Object> p) {
        return ParIterableLike$class.exists(this, p);
    }

    @Override
    public Option<Tuple2<K, V>> find(Function1<Tuple2<K, V>, Object> p) {
        return ParIterableLike$class.find(this, p);
    }

    @Override
    public Object combinerFactory() {
        return ParIterableLike$class.combinerFactory(this);
    }

    @Override
    public <S, That> Object combinerFactory(Function0<Combiner<S, That>> cbf) {
        return ParIterableLike$class.combinerFactory(this, cbf);
    }

    @Override
    public scala.collection.parallel.ParIterable withFilter(Function1 pred) {
        return ParIterableLike$class.withFilter(this, pred);
    }

    @Override
    public scala.collection.parallel.ParIterable filter(Function1 pred) {
        return ParIterableLike$class.filter(this, pred);
    }

    @Override
    public scala.collection.parallel.ParIterable filterNot(Function1 pred) {
        return ParIterableLike$class.filterNot(this, pred);
    }

    @Override
    public <U, That> That $plus$plus(GenTraversableOnce<U> that, CanBuildFrom<ParHashMap<K, V>, U, That> bf) {
        return (That)ParIterableLike$class.$plus$plus(this, that, bf);
    }

    @Override
    public Tuple2<ParHashMap<K, V>, ParHashMap<K, V>> partition(Function1<Tuple2<K, V>, Object> pred) {
        return ParIterableLike$class.partition(this, pred);
    }

    @Override
    public <K> scala.collection.parallel.immutable.ParMap<K, ParHashMap<K, V>> groupBy(Function1<Tuple2<K, V>, K> f) {
        return ParIterableLike$class.groupBy(this, f);
    }

    @Override
    public scala.collection.parallel.ParIterable take(int n) {
        return ParIterableLike$class.take(this, n);
    }

    @Override
    public scala.collection.parallel.ParIterable drop(int n) {
        return ParIterableLike$class.drop(this, n);
    }

    @Override
    public scala.collection.parallel.ParIterable slice(int unc_from, int unc_until) {
        return ParIterableLike$class.slice(this, unc_from, unc_until);
    }

    @Override
    public Tuple2<ParHashMap<K, V>, ParHashMap<K, V>> splitAt(int n) {
        return ParIterableLike$class.splitAt(this, n);
    }

    @Override
    public <U, That> That scan(U z, Function2<U, U, U> op, CanBuildFrom<ParHashMap<K, V>, U, That> bf) {
        return (That)ParIterableLike$class.scan(this, z, op, bf);
    }

    @Override
    public <S, That> That scanLeft(S z, Function2<S, Tuple2<K, V>, S> op, CanBuildFrom<ParHashMap<K, V>, S, That> bf) {
        return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
    }

    @Override
    public <S, That> That scanRight(S z, Function2<Tuple2<K, V>, S, S> op, CanBuildFrom<ParHashMap<K, V>, S, That> bf) {
        return (That)ParIterableLike$class.scanRight(this, z, op, bf);
    }

    @Override
    public scala.collection.parallel.ParIterable takeWhile(Function1 pred) {
        return ParIterableLike$class.takeWhile(this, pred);
    }

    @Override
    public Tuple2<ParHashMap<K, V>, ParHashMap<K, V>> span(Function1<Tuple2<K, V>, Object> pred) {
        return ParIterableLike$class.span(this, pred);
    }

    @Override
    public scala.collection.parallel.ParIterable dropWhile(Function1 pred) {
        return ParIterableLike$class.dropWhile(this, pred);
    }

    @Override
    public <U> void copyToArray(Object xs) {
        ParIterableLike$class.copyToArray(this, xs);
    }

    @Override
    public <U> void copyToArray(Object xs, int start) {
        ParIterableLike$class.copyToArray(this, xs, start);
    }

    @Override
    public <U> void copyToArray(Object xs, int start, int len) {
        ParIterableLike$class.copyToArray(this, xs, start, len);
    }

    @Override
    public <U> boolean sameElements(GenIterable<U> that) {
        return ParIterableLike$class.sameElements(this, that);
    }

    @Override
    public <U, S, That> That zip(GenIterable<S> that, CanBuildFrom<ParHashMap<K, V>, Tuple2<U, S>, That> bf) {
        return (That)ParIterableLike$class.zip(this, that, bf);
    }

    @Override
    public <U, That> That zipWithIndex(CanBuildFrom<ParHashMap<K, V>, Tuple2<U, Object>, That> bf) {
        return (That)ParIterableLike$class.zipWithIndex(this, bf);
    }

    @Override
    public <S, U, That> That zipAll(GenIterable<S> that, U thisElem, S thatElem, CanBuildFrom<ParHashMap<K, V>, Tuple2<U, S>, That> bf) {
        return (That)ParIterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
    }

    @Override
    public <U, That> That toParCollection(Function0<Combiner<U, That>> cbf) {
        return (That)ParIterableLike$class.toParCollection(this, cbf);
    }

    @Override
    public <K, V, That> That toParMap(Function0<Combiner<Tuple2<K, V>, That>> cbf, Predef$.less.colon.less<Tuple2<K, V>, Tuple2<K, V>> ev) {
        return (That)ParIterableLike$class.toParMap(this, cbf, ev);
    }

    @Override
    public Object view() {
        return ParIterableLike$class.view(this);
    }

    @Override
    public <U> Object toArray(ClassTag<U> evidence$1) {
        return ParIterableLike$class.toArray(this, evidence$1);
    }

    @Override
    public List<Tuple2<K, V>> toList() {
        return ParIterableLike$class.toList(this);
    }

    @Override
    public IndexedSeq<Tuple2<K, V>> toIndexedSeq() {
        return ParIterableLike$class.toIndexedSeq(this);
    }

    @Override
    public Stream<Tuple2<K, V>> toStream() {
        return ParIterableLike$class.toStream(this);
    }

    @Override
    public Iterator<Tuple2<K, V>> toIterator() {
        return ParIterableLike$class.toIterator(this);
    }

    @Override
    public <U> Buffer<U> toBuffer() {
        return ParIterableLike$class.toBuffer(this);
    }

    @Override
    public GenTraversable<Tuple2<K, V>> toTraversable() {
        return ParIterableLike$class.toTraversable(this);
    }

    @Override
    public <U> scala.collection.parallel.immutable.ParSet<U> toSet() {
        return ParIterableLike$class.toSet(this);
    }

    @Override
    public Vector<Tuple2<K, V>> toVector() {
        return ParIterableLike$class.toVector(this);
    }

    @Override
    public <Col> Col to(CanBuildFrom<Nothing$, Tuple2<K, V>, Col> cbf) {
        return (Col)ParIterableLike$class.to(this, cbf);
    }

    @Override
    public int scanBlockSize() {
        return ParIterableLike$class.scanBlockSize(this);
    }

    @Override
    public <S> S $div$colon(S z, Function2<S, Tuple2<K, V>, S> op) {
        return (S)ParIterableLike$class.$div$colon(this, z, op);
    }

    @Override
    public <S> S $colon$bslash(S z, Function2<Tuple2<K, V>, S, S> op) {
        return (S)ParIterableLike$class.$colon$bslash(this, z, op);
    }

    @Override
    public String debugInformation() {
        return ParIterableLike$class.debugInformation(this);
    }

    @Override
    public Seq<String> brokenInvariants() {
        return ParIterableLike$class.brokenInvariants(this);
    }

    @Override
    public ArrayBuffer<String> debugBuffer() {
        return ParIterableLike$class.debugBuffer(this);
    }

    @Override
    public void debugclear() {
        ParIterableLike$class.debugclear(this);
    }

    @Override
    public ArrayBuffer<String> debuglog(String s2) {
        return ParIterableLike$class.debuglog(this, s2);
    }

    @Override
    public void printDebugBuffer() {
        ParIterableLike$class.printDebugBuffer(this);
    }

    @Override
    public Combiner<Tuple2<K, V>, ParHashMap<K, V>> parCombiner() {
        return CustomParallelizable$class.parCombiner(this);
    }

    @Override
    public <P, Q> Combiner<Tuple2<P, Q>, ParHashMap<P, Q>> genericMapCombiner() {
        return GenericParMapTemplate$class.genericMapCombiner(this);
    }

    @Override
    public Builder<Tuple2<K, V>, ParIterable<Tuple2<K, V>>> newBuilder() {
        return GenericParTemplate$class.newBuilder(this);
    }

    @Override
    public <B> Combiner<B, ParIterable<B>> genericBuilder() {
        return GenericParTemplate$class.genericBuilder(this);
    }

    @Override
    public <B> Combiner<B, ParIterable<B>> genericCombiner() {
        return GenericParTemplate$class.genericCombiner(this);
    }

    @Override
    public <A1, A2> Tuple2<ParIterable<A1>, ParIterable<A2>> unzip(Function1<Tuple2<K, V>, Tuple2<A1, A2>> asPair) {
        return GenericTraversableTemplate$class.unzip(this, asPair);
    }

    @Override
    public <A1, A2, A3> Tuple3<ParIterable<A1>, ParIterable<A2>, ParIterable<A3>> unzip3(Function1<Tuple2<K, V>, Tuple3<A1, A2, A3>> asTriple) {
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
    public int hashCode() {
        return GenMapLike$class.hashCode(this);
    }

    @Override
    public boolean equals(Object that) {
        return GenMapLike$class.equals(this, that);
    }

    @Override
    public GenericParMapCompanion<ParHashMap> mapCompanion() {
        return ParHashMap$.MODULE$;
    }

    @Override
    public ParHashMap<K, V> empty() {
        return new ParHashMap<K, V>();
    }

    @Override
    public HashMapCombiner<K, V> newCombiner() {
        return HashMapCombiner$.MODULE$.apply();
    }

    @Override
    public IterableSplitter<Tuple2<K, V>> splitter() {
        return new ParHashMapIterator(this, this.trie.iterator(), this.trie.size());
    }

    @Override
    public HashMap<K, V> seq() {
        return this.trie;
    }

    @Override
    public ParHashMap<K, V> $minus(K k) {
        return new ParHashMap<K, V>(this.trie.$minus((Object)k));
    }

    @Override
    public <U> ParHashMap<K, U> $plus(Tuple2<K, U> kv) {
        return new ParHashMap<K, V>(this.trie.$plus(kv));
    }

    @Override
    public Option<V> get(K k) {
        return this.trie.get(k);
    }

    @Override
    public int size() {
        return this.trie.size();
    }

    @Override
    public <S, That> Combiner<S, That> reuse(Option<Combiner<S, That>> oldc, Combiner<S, That> newc) {
        block4: {
            Combiner combiner;
            block3: {
                block2: {
                    if (!(oldc instanceof Some)) break block2;
                    Some some = (Some)oldc;
                    combiner = (Combiner)some.x();
                    break block3;
                }
                if (!None$.MODULE$.equals(oldc)) break block4;
                combiner = newc;
            }
            return combiner;
        }
        throw new MatchError(oldc);
    }

    public void printDebugInfo() {
        Predef$.MODULE$.println("Parallel hash trie");
        Predef$.MODULE$.println(new StringBuilder().append((Object)"Top level inner trie type: ").append(this.trie.getClass()).toString());
        HashMap<K, V> hashMap = this.trie;
        if (hashMap instanceof HashMap.HashMap1) {
            HashMap.HashMap1 hashMap1 = (HashMap.HashMap1)hashMap;
            Predef$.MODULE$.println("single node type");
            Predef$.MODULE$.println(new StringBuilder().append((Object)"key stored: ").append(hashMap1.getKey()).toString());
            Predef$.MODULE$.println(new StringBuilder().append((Object)"hash of key: ").append(BoxesRunTime.boxToInteger(hashMap1.getHash())).toString());
            Predef$.MODULE$.println(new StringBuilder().append((Object)"computed hash of ").append(hashMap1.getKey()).append((Object)": ").append(BoxesRunTime.boxToInteger(hashMap1.computeHashFor(hashMap1.getKey()))).toString());
            Predef$.MODULE$.println(new StringBuilder().append((Object)"trie.get(key): ").append(hashMap1.get(hashMap1.getKey())).toString());
        } else {
            Predef$.MODULE$.println("other kind of node");
        }
    }

    public ParHashMap(HashMap<K, V> trie) {
        this.trie = trie;
        Parallelizable$class.$init$(this);
        GenMapLike$class.$init$(this);
        GenericTraversableTemplate$class.$init$(this);
        GenTraversable$class.$init$(this);
        GenIterable$class.$init$(this);
        GenericParTemplate$class.$init$(this);
        GenericParMapTemplate$class.$init$(this);
        CustomParallelizable$class.$init$(this);
        ParIterableLike$class.$init$(this);
        scala.collection.parallel.ParIterable$class.$init$(this);
        ParMapLike$class.$init$(this);
        scala.collection.parallel.ParMap$class.$init$(this);
        ParIterable$class.$init$(this);
        ParMap$class.$init$(this);
    }

    public ParHashMap() {
        this((HashMap<K, V>)HashMap$.MODULE$.empty());
    }

    public class ParHashMapIterator
    implements IterableSplitter<Tuple2<K, V>> {
        private Iterator<Tuple2<K, V>> triter;
        private final int sz;
        private int i;
        public final /* synthetic */ ParHashMap $outer;
        private Signalling signalDelegate;

        @Override
        public Signalling signalDelegate() {
            return this.signalDelegate;
        }

        @Override
        @TraitSetter
        public void signalDelegate_$eq(Signalling x$1) {
            this.signalDelegate = x$1;
        }

        @Override
        public Seq<IterableSplitter<Tuple2<K, V>>> splitWithSignalling() {
            return IterableSplitter$class.splitWithSignalling(this);
        }

        @Override
        public <S> boolean shouldSplitFurther(scala.collection.parallel.ParIterable<S> coll, int parallelismLevel) {
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
        public IterableSplitter.Taken newTaken(int until2) {
            return IterableSplitter$class.newTaken(this, until2);
        }

        @Override
        public <U extends IterableSplitter.Taken> U newSliceInternal(U it, int from1) {
            return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
        }

        @Override
        public IterableSplitter<Tuple2<K, V>> take(int n) {
            return IterableSplitter$class.take(this, n);
        }

        @Override
        public IterableSplitter<Tuple2<K, V>> slice(int from1, int until1) {
            return IterableSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> IterableSplitter.Mapped<S> map(Function1<Tuple2<K, V>, S> f) {
            return IterableSplitter$class.map(this, f);
        }

        @Override
        public <U, PI extends IterableSplitter<U>> IterableSplitter.Appended<U, PI> appendParIterable(PI that) {
            return IterableSplitter$class.appendParIterable(this, that);
        }

        @Override
        public <S> IterableSplitter.Zipped<S> zipParSeq(SeqSplitter<S> that) {
            return IterableSplitter$class.zipParSeq(this, that);
        }

        @Override
        public <S, U, R> IterableSplitter.ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
            return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
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
        public int count(Function1<Tuple2<K, V>, Object> p) {
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
        public Object min(Ordering ord) {
            return AugmentedIterableIterator$class.min(this, ord);
        }

        @Override
        public Object max(Ordering ord) {
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
        public <S, That> Combiner<S, That> map2combiner(Function1<Tuple2<K, V>, S> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.map2combiner(this, f, cb);
        }

        @Override
        public <S, That> Combiner<S, That> collect2combiner(PartialFunction<Tuple2<K, V>, S> pf, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
        }

        @Override
        public <S, That> Combiner<S, That> flatmap2combiner(Function1<Tuple2<K, V>, GenTraversableOnce<S>> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
        }

        @Override
        public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Bld b) {
            return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
        }

        @Override
        public <U, This> Combiner<U, This> filter2combiner(Function1<Tuple2<K, V>, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Combiner<U, This> filterNot2combiner(Function1<Tuple2<K, V>, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1<Tuple2<K, V>, Object> pred, Combiner<U, This> btrue, Combiner<U, This> bfalse) {
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
        public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1<Tuple2<K, V>, Object> p, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1<Tuple2<K, V>, Object> p, Combiner<U, This> before, Combiner<U, This> after) {
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
        public Iterator<Tuple2<K, V>> seq() {
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
        public Iterator<Tuple2<K, V>> drop(int n) {
            return Iterator$class.drop(this, n);
        }

        @Override
        public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
            return Iterator$class.$plus$plus(this, that);
        }

        @Override
        public <B> Iterator<B> flatMap(Function1<Tuple2<K, V>, GenTraversableOnce<B>> f) {
            return Iterator$class.flatMap(this, f);
        }

        @Override
        public Iterator<Tuple2<K, V>> filter(Function1<Tuple2<K, V>, Object> p) {
            return Iterator$class.filter(this, p);
        }

        @Override
        public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<Tuple2<K, V>, B, Object> p) {
            return Iterator$class.corresponds(this, that, p);
        }

        @Override
        public Iterator<Tuple2<K, V>> withFilter(Function1<Tuple2<K, V>, Object> p) {
            return Iterator$class.withFilter(this, p);
        }

        @Override
        public Iterator<Tuple2<K, V>> filterNot(Function1<Tuple2<K, V>, Object> p) {
            return Iterator$class.filterNot(this, p);
        }

        @Override
        public <B> Iterator<B> collect(PartialFunction<Tuple2<K, V>, B> pf) {
            return Iterator$class.collect(this, pf);
        }

        @Override
        public <B> Iterator<B> scanLeft(B z, Function2<B, Tuple2<K, V>, B> op) {
            return Iterator$class.scanLeft(this, z, op);
        }

        @Override
        public <B> Iterator<B> scanRight(B z, Function2<Tuple2<K, V>, B, B> op) {
            return Iterator$class.scanRight(this, z, op);
        }

        @Override
        public Iterator<Tuple2<K, V>> takeWhile(Function1<Tuple2<K, V>, Object> p) {
            return Iterator$class.takeWhile(this, p);
        }

        @Override
        public Tuple2<Iterator<Tuple2<K, V>>, Iterator<Tuple2<K, V>>> partition(Function1<Tuple2<K, V>, Object> p) {
            return Iterator$class.partition(this, p);
        }

        @Override
        public Tuple2<Iterator<Tuple2<K, V>>, Iterator<Tuple2<K, V>>> span(Function1<Tuple2<K, V>, Object> p) {
            return Iterator$class.span(this, p);
        }

        @Override
        public Iterator<Tuple2<K, V>> dropWhile(Function1<Tuple2<K, V>, Object> p) {
            return Iterator$class.dropWhile(this, p);
        }

        @Override
        public <B> Iterator<Tuple2<Tuple2<K, V>, B>> zip(Iterator<B> that) {
            return Iterator$class.zip(this, that);
        }

        @Override
        public <A1> Iterator<A1> padTo(int len, A1 elem) {
            return Iterator$class.padTo(this, len, elem);
        }

        @Override
        public Iterator<Tuple2<Tuple2<K, V>, Object>> zipWithIndex() {
            return Iterator$class.zipWithIndex(this);
        }

        @Override
        public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
            return Iterator$class.zipAll(this, that, thisElem, thatElem);
        }

        @Override
        public <U> void foreach(Function1<Tuple2<K, V>, U> f) {
            Iterator$class.foreach(this, f);
        }

        @Override
        public boolean forall(Function1<Tuple2<K, V>, Object> p) {
            return Iterator$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<Tuple2<K, V>, Object> p) {
            return Iterator$class.exists(this, p);
        }

        @Override
        public boolean contains(Object elem) {
            return Iterator$class.contains(this, elem);
        }

        @Override
        public Option<Tuple2<K, V>> find(Function1<Tuple2<K, V>, Object> p) {
            return Iterator$class.find(this, p);
        }

        @Override
        public int indexWhere(Function1<Tuple2<K, V>, Object> p) {
            return Iterator$class.indexWhere(this, p);
        }

        @Override
        public <B> int indexOf(B elem) {
            return Iterator$class.indexOf(this, elem);
        }

        @Override
        public BufferedIterator<Tuple2<K, V>> buffered() {
            return Iterator$class.buffered(this);
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
        public Tuple2<Iterator<Tuple2<K, V>>, Iterator<Tuple2<K, V>>> duplicate() {
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
        public Traversable<Tuple2<K, V>> toTraversable() {
            return Iterator$class.toTraversable(this);
        }

        @Override
        public Iterator<Tuple2<K, V>> toIterator() {
            return Iterator$class.toIterator(this);
        }

        @Override
        public Stream<Tuple2<K, V>> toStream() {
            return Iterator$class.toStream(this);
        }

        @Override
        public <B> int sliding$default$2() {
            return Iterator$class.sliding$default$2(this);
        }

        @Override
        public List<Tuple2<K, V>> reversed() {
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
        public <B> Option<B> collectFirst(PartialFunction<Tuple2<K, V>, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, Tuple2<K, V>, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<Tuple2<K, V>, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, Tuple2<K, V>, B> op) {
            return (B)TraversableOnce$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B foldRight(B z, Function2<Tuple2<K, V>, B, B> op) {
            return (B)TraversableOnce$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, Tuple2<K, V>, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> B reduceRight(Function2<Tuple2<K, V>, B, B> op) {
            return (B)TraversableOnce$class.reduceRight(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, Tuple2<K, V>, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<Tuple2<K, V>, B, B> op) {
            return TraversableOnce$class.reduceRightOption(this, op);
        }

        @Override
        public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
            return TraversableOnce$class.reduceOption(this, op);
        }

        @Override
        public <B> B aggregate(Function0<B> z, Function2<B, Tuple2<K, V>, B> seqop, Function2<B, B, B> combop) {
            return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
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
        public List<Tuple2<K, V>> toList() {
            return TraversableOnce$class.toList(this);
        }

        @Override
        public Iterable<Tuple2<K, V>> toIterable() {
            return TraversableOnce$class.toIterable(this);
        }

        @Override
        public Seq<Tuple2<K, V>> toSeq() {
            return TraversableOnce$class.toSeq(this);
        }

        @Override
        public IndexedSeq<Tuple2<K, V>> toIndexedSeq() {
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
        public Vector<Tuple2<K, V>> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, Tuple2<K, V>, Col> cbf) {
            return (Col)TraversableOnce$class.to(this, cbf);
        }

        @Override
        public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Tuple2<K, V>, Tuple2<T, U>> ev) {
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

        public Iterator<Tuple2<K, V>> triter() {
            return this.triter;
        }

        public void triter_$eq(Iterator<Tuple2<K, V>> x$1) {
            this.triter = x$1;
        }

        public int sz() {
            return this.sz;
        }

        public int i() {
            return this.i;
        }

        public void i_$eq(int x$1) {
            this.i = x$1;
        }

        @Override
        public IterableSplitter<Tuple2<K, V>> dup() {
            ParHashMapIterator parHashMapIterator;
            Iterator iterator2 = this.triter();
            if (iterator2 instanceof TrieIterator) {
                TrieIterator trieIterator = (TrieIterator)iterator2;
                parHashMapIterator = this.dupFromIterator(trieIterator.dupIterator());
            } else {
                Buffer buff = this.triter().toBuffer();
                this.triter_$eq(buff.iterator());
                parHashMapIterator = this.dupFromIterator(buff.iterator());
            }
            return parHashMapIterator;
        }

        /*
         * WARNING - void declaration
         */
        private ParHashMapIterator dupFromIterator(Iterator<Tuple2<K, V>> it) {
            void var2_2;
            ParHashMapIterator phit = new ParHashMapIterator(this.scala$collection$parallel$immutable$ParHashMap$ParHashMapIterator$$$outer(), it, this.sz());
            phit.i_$eq(this.i());
            return var2_2;
        }

        @Override
        public Seq<IterableSplitter<Tuple2<K, V>>> split() {
            Tuple2 tuple2;
            block7: {
                Seq seq;
                block3: {
                    Seq seq2;
                    block6: {
                        block4: {
                            Tuple2 tuple22;
                            block5: {
                                block2: {
                                    if (this.remaining() >= 2) break block2;
                                    seq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new ParHashMapIterator[]{this}));
                                    break block3;
                                }
                                Iterator iterator2 = this.triter();
                                if (!(iterator2 instanceof TrieIterator)) break block4;
                                TrieIterator trieIterator = (TrieIterator)iterator2;
                                int previousRemaining = this.remaining();
                                tuple22 = trieIterator.split();
                                if (tuple22 == null || tuple22._1() == null) break block5;
                                Tuple3 tuple3 = new Tuple3(tuple22._1()._1(), BoxesRunTime.boxToInteger(tuple22._1()._2$mcI$sp()), tuple22._2());
                                Iterator fst = tuple3._1();
                                int fstlength = BoxesRunTime.unboxToInt(tuple3._2());
                                Iterator snd = tuple3._3();
                                int sndlength = previousRemaining - fstlength;
                                seq2 = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new ParHashMapIterator[]{new ParHashMapIterator(this.scala$collection$parallel$immutable$ParHashMap$ParHashMapIterator$$$outer(), fst, fstlength), new ParHashMapIterator(this.scala$collection$parallel$immutable$ParHashMap$ParHashMapIterator$$$outer(), snd, sndlength)}));
                                break block6;
                            }
                            throw new MatchError(tuple22);
                        }
                        Buffer buff = this.triter().toBuffer();
                        tuple2 = buff.splitAt(buff.length() / 2);
                        if (tuple2 == null) break block7;
                        Tuple2 tuple23 = new Tuple2(tuple2._1(), tuple2._2());
                        Buffer fp = (Buffer)tuple23._1();
                        Buffer sp2 = (Buffer)tuple23._2();
                        seq2 = ((TraversableLike)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Buffer[]{fp, sp2}))).map(new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ ParHashMapIterator $outer;

                            public final ParHashMapIterator apply(Buffer<Tuple2<K, V>> b) {
                                return new ParHashMapIterator(this.$outer.scala$collection$parallel$immutable$ParHashMap$ParHashMapIterator$$$outer(), b.iterator(), b.length());
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        }, Seq$.MODULE$.canBuildFrom());
                    }
                    seq = seq2;
                }
                return seq;
            }
            throw new MatchError(tuple2);
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public Tuple2<K, V> next() {
            void var1_1;
            this.i_$eq(this.i() + 1);
            Tuple2 r = this.triter().next();
            return var1_1;
        }

        @Override
        public boolean hasNext() {
            return this.i() < this.sz();
        }

        @Override
        public int remaining() {
            return this.sz() - this.i();
        }

        @Override
        public String toString() {
            return new StringBuilder().append((Object)"HashTrieIterator(").append(BoxesRunTime.boxToInteger(this.sz())).append((Object)")").toString();
        }

        public /* synthetic */ ParHashMap scala$collection$parallel$immutable$ParHashMap$ParHashMapIterator$$$outer() {
            return this.$outer;
        }

        public ParHashMapIterator(ParHashMap<K, V> $outer, Iterator<Tuple2<K, V>> triter, int sz) {
            this.triter = triter;
            this.sz = sz;
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
            this.i = 0;
        }
    }
}

