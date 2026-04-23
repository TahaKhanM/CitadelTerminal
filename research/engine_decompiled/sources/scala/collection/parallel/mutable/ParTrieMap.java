/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.CustomParallelizable$class;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenMapLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterator;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.concurrent.BasicNode;
import scala.collection.concurrent.CNode;
import scala.collection.concurrent.INode;
import scala.collection.concurrent.LNode;
import scala.collection.concurrent.MainNode;
import scala.collection.concurrent.SNode;
import scala.collection.concurrent.TNode;
import scala.collection.concurrent.TrieMap;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericParMapCompanion;
import scala.collection.generic.GenericParMapTemplate$class;
import scala.collection.generic.GenericParTemplate$class;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.generic.Shrinkable;
import scala.collection.generic.Shrinkable$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.Cloneable$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.Combiner$class;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.ParIterableLike$ScanLeaf$;
import scala.collection.parallel.ParIterableLike$ScanNode$;
import scala.collection.parallel.ParIterableLike$class;
import scala.collection.parallel.ParMap;
import scala.collection.parallel.ParSet;
import scala.collection.parallel.Splitter;
import scala.collection.parallel.Task;
import scala.collection.parallel.Task$class;
import scala.collection.parallel.TaskSupport;
import scala.collection.parallel.mutable.ParIterable;
import scala.collection.parallel.mutable.ParIterable$class;
import scala.collection.parallel.mutable.ParMap$class;
import scala.collection.parallel.mutable.ParMapLike$class;
import scala.collection.parallel.mutable.ParSeq;
import scala.collection.parallel.mutable.ParTrieMap$;
import scala.collection.parallel.mutable.ParTrieMapCombiner;
import scala.collection.parallel.mutable.ParTrieMapCombiner$class;
import scala.collection.parallel.mutable.ParTrieMapSplitter;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;
import scala.runtime.TraitSetter;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@ScalaSignature(bytes="\u0006\u0001\t=a\u0001B\u0001\u0003\u0005-\u0011!\u0002U1s)JLW-T1q\u0015\t\u0019A!A\u0004nkR\f'\r\\3\u000b\u0005\u00151\u0011\u0001\u00039be\u0006dG.\u001a7\u000b\u0005\u001dA\u0011AC2pY2,7\r^5p]*\t\u0011\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u000719\u0012eE\u0004\u0001\u001bE\u0019#\u0006N\u001c\u0011\u00059yQ\"\u0001\u0005\n\u0005AA!AB!osJ+g\r\u0005\u0003\u0013'U\u0001S\"\u0001\u0002\n\u0005Q\u0011!A\u0002)be6\u000b\u0007\u000f\u0005\u0002\u0017/1\u0001A!\u0002\r\u0001\u0005\u0004I\"!A&\u0012\u0005ii\u0002C\u0001\b\u001c\u0013\ta\u0002BA\u0004O_RD\u0017N\\4\u0011\u00059q\u0012BA\u0010\t\u0005\r\te.\u001f\t\u0003-\u0005\"QA\t\u0001C\u0002e\u0011\u0011A\u0016\t\u0006I\u001d*\u0002%K\u0007\u0002K)\u0011aEB\u0001\bO\u0016tWM]5d\u0013\tASEA\u000bHK:,'/[2QCJl\u0015\r\u001d+f[Bd\u0017\r^3\u0011\u0005I\u0001\u0001C\u0002\n,+\u0001jc&\u0003\u0002-\u0005\tQ\u0001+\u0019:NCBd\u0015n[3\u0011\tI\u0001Q\u0003\t\t\u0005_I*\u0002%D\u00011\u0015\t\td!\u0001\u0006d_:\u001cWO\u001d:f]RL!a\r\u0019\u0003\u000fQ\u0013\u0018.Z'baB!!#N\u000b!\u0013\t1$A\u0001\nQCJ$&/[3NCB\u001cu.\u001c2j]\u0016\u0014\bC\u0001\b9\u0013\tI\u0004B\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0003\u0005<\u0001\t\u0015\r\u0011\"\u0003=\u0003\u0015\u0019GO]5f+\u0005q\u0003\u0002\u0003 \u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0018\u0002\r\r$(/[3!\u0011\u0019\u0001\u0005\u0001\"\u0001\u0007\u0003\u00061A(\u001b8jiz\"\"!\f\"\t\u000bmz\u0004\u0019\u0001\u0018\t\u000b\u0001\u0003A\u0011\u0001#\u0015\u00035BQA\u0012\u0001\u0005B\u001d\u000bA\"\\1q\u0007>l\u0007/\u00198j_:,\u0012\u0001\u0013\t\u0004I%K\u0013B\u0001&&\u0005Y9UM\\3sS\u000e\u0004\u0016M]'ba\u000e{W\u000e]1oS>t\u0007\"\u0002'\u0001\t\u0003j\u0015!B3naRLX#A\u0017\t\r=\u0003\u0001\u0015\"\u0015Q\u0003-qWm^\"p[\nLg.\u001a:\u0016\u0003E\u0003BAU*V[5\tA!\u0003\u0002U\t\tA1i\\7cS:,'\u000f\u0005\u0003\u000f-V\u0001\u0013BA,\t\u0005\u0019!V\u000f\u001d7fe!)\u0011\f\u0001C!y\u0005\u00191/Z9\t\u000bm\u0003A\u0011\u0001/\u0002\u0011M\u0004H.\u001b;uKJ,\u0012!\u0018\t\u0005%y+\u0002%\u0003\u0002`\u0005\t\u0011\u0002+\u0019:Ue&,W*\u00199Ta2LG\u000f^3s\u0011\u0015\t\u0007\u0001\"\u0011c\u0003\u0015\u0019G.Z1s)\u0005\u0019\u0007C\u0001\be\u0013\t)\u0007B\u0001\u0003V]&$\b\"B4\u0001\t\u0003!\u0015A\u0002:fgVdG\u000fC\u0003j\u0001\u0011\u0005!.A\u0002hKR$\"a\u001b8\u0011\u00079a\u0007%\u0003\u0002n\u0011\t1q\n\u001d;j_:DQa\u001c5A\u0002U\t1a[3z\u0011\u0015\t\b\u0001\"\u0001s\u0003\r\u0001X\u000f\u001e\u000b\u0004WN$\b\"B8q\u0001\u0004)\u0002\"B;q\u0001\u0004\u0001\u0013!\u0002<bYV,\u0007\"B<\u0001\t\u0003A\u0018AB;qI\u0006$X\rF\u0002dsjDQa\u001c<A\u0002UAQ!\u001e<A\u0002\u0001BQ\u0001 \u0001\u0005\u0002u\faA]3n_Z,GCA6\u007f\u0011\u0015y7\u00101\u0001\u0016\u0011\u001d\t\t\u0001\u0001C\u0001\u0003\u0007\t\u0001\u0002\n9mkN$S-\u001d\u000b\u0005\u0003\u000b\t9!D\u0001\u0001\u0011\u0019\tIa a\u0001+\u0006\u00111N\u001e\u0005\b\u0003\u001b\u0001A\u0011AA\b\u0003%!S.\u001b8vg\u0012*\u0017\u000f\u0006\u0003\u0002\u0006\u0005E\u0001BB8\u0002\f\u0001\u0007Q\u0003C\u0004\u0002\u0016\u0001!\t%a\u0006\u0002\tML'0Z\u000b\u0003\u00033\u00012ADA\u000e\u0013\r\ti\u0002\u0003\u0002\u0004\u0013:$\bbBA\u0011\u0001\u0011\u0005\u00131E\u0001\rgR\u0014\u0018N\\4Qe\u00164\u0017\u000e_\u000b\u0003\u0003K\u0001B!a\n\u000225\u0011\u0011\u0011\u0006\u0006\u0005\u0003W\ti#\u0001\u0003mC:<'BAA\u0018\u0003\u0011Q\u0017M^1\n\t\u0005M\u0012\u0011\u0006\u0002\u0007'R\u0014\u0018N\\4\u0007\r\u0005]\u0002\u0001AA\u001d\u0005\u0011\u0019\u0016N_3\u0014\u000b\u0005UR\"a\u000f\u0011\u000fI\u000bi$!\u0007\u0002B%\u0019\u0011q\b\u0003\u0003\tQ\u000b7o\u001b\t\u0005\u0003\u000b\t)\u0004C\u0006\u0002F\u0005U\"\u0011!Q\u0001\n\u0005e\u0011AB8gMN,G\u000fC\u0006\u0002J\u0005U\"\u0011!Q\u0001\n\u0005e\u0011a\u00025po6\fg.\u001f\u0005\f\u0003\u001b\n)D!A!\u0002\u0013\ty%A\u0003beJ\f\u0017\u0010E\u0003\u000f\u0003#\n)&C\u0002\u0002T!\u0011Q!\u0011:sCf\u00042aLA,\u0013\r\tI\u0006\r\u0002\n\u0005\u0006\u001c\u0018n\u0019(pI\u0016Dq\u0001QA\u001b\t\u0003\ti\u0006\u0006\u0005\u0002B\u0005}\u0013\u0011MA2\u0011!\t)%a\u0017A\u0002\u0005e\u0001\u0002CA%\u00037\u0002\r!!\u0007\t\u0011\u00055\u00131\fa\u0001\u0003\u001fB\u0011bZA\u001b\u0001\u0004%\t!a\u0006\t\u0015\u0005%\u0014Q\u0007a\u0001\n\u0003\tY'\u0001\u0006sKN,H\u000e^0%KF$2aYA7\u0011)\ty'a\u001a\u0002\u0002\u0003\u0007\u0011\u0011D\u0001\u0004q\u0012\n\u0004\"CA:\u0003k\u0001\u000b\u0015BA\r\u0003\u001d\u0011Xm];mi\u0002B\u0001\"a\u001e\u00026\u0011\u0005\u0011\u0011P\u0001\u0005Y\u0016\fg\rF\u0002d\u0003wB\u0001\"! \u0002v\u0001\u0007\u0011qP\u0001\u0005aJ,g\u000f\u0005\u0003\u000fY\u0006e\u0001\u0002CAB\u0003k!\t!!\"\u0002\u000bM\u0004H.\u001b;\u0016\u0005\u0005\u001d\u0005CBAE\u0003\u0017\u000b\t%D\u0001\u0007\u0013\r\tiI\u0002\u0002\u0004'\u0016\f\b\u0002CAI\u0003k!\t!a%\u0002%MDw.\u001e7e'Bd\u0017\u000e\u001e$veRDWM]\u000b\u0003\u0003+\u00032ADAL\u0013\r\tI\n\u0003\u0002\b\u0005>|G.Z1o\u0011!\ti*!\u000e\u0005B\u0005}\u0015!B7fe\u001e,GcA2\u0002\"\"A\u00111UAN\u0001\u0004\t\t%\u0001\u0003uQ\u0006$xaBAT\u0005!\u0005\u0011\u0011V\u0001\u000b!\u0006\u0014HK]5f\u001b\u0006\u0004\bc\u0001\n\u0002,\u001a1\u0011A\u0001E\u0001\u0003[\u001bR!a+\u00020^\u0002B\u0001JAYS%\u0019\u00111W\u0013\u0003\u001bA\u000b'/T1q\r\u0006\u001cGo\u001c:z\u0011\u001d\u0001\u00151\u0016C\u0001\u0003o#\"!!+\t\u000f1\u000bY\u000b\"\u0001\u0002<V1\u0011QXAb\u0003\u000f,\"!a0\u0011\rI\u0001\u0011\u0011YAc!\r1\u00121\u0019\u0003\u00071\u0005e&\u0019A\r\u0011\u0007Y\t9\r\u0002\u0004#\u0003s\u0013\r!\u0007\u0005\b\u001f\u0006-F\u0011AAf+\u0019\ti-!6\u0002ZV\u0011\u0011q\u001a\t\u0007%N\u000b\t.a7\u0011\r91\u00161[Al!\r1\u0012Q\u001b\u0003\u00071\u0005%'\u0019A\r\u0011\u0007Y\tI\u000e\u0002\u0004#\u0003\u0013\u0014\r!\u0007\t\u0007%\u0001\t\u0019.a6\t\u0011\u0005}\u00171\u0016C\u0002\u0003C\fAbY1o\u0005VLG\u000e\u001a$s_6,b!a9\u0002|\u0006}XCAAs!%!\u0013q]Av\u0003o\u0014\t!C\u0002\u0002j\u0016\u0012abQ1o\u0007>l'-\u001b8f\rJ|W\u000e\u0005\u0003\u0002n\u0006=XBAAV\u0013\u0011\t\t0a=\u0003\t\r{G\u000e\\\u0005\u0004\u0003k,#!D$f]6\u000b\u0007OR1di>\u0014\u0018\u0010\u0005\u0004\u000f-\u0006e\u0018Q \t\u0004-\u0005mHA\u0002\r\u0002^\n\u0007\u0011\u0004E\u0002\u0017\u0003\u007f$aAIAo\u0005\u0004I\u0002C\u0002\n\u0001\u0003s\fi\u0010\u0003\u0006\u0003\u0006\u0005-\u0016\u0011!C\u0005\u0005\u000f\t1B]3bIJ+7o\u001c7wKR\u0011!\u0011\u0002\t\u0005\u0003O\u0011Y!\u0003\u0003\u0003\u000e\u0005%\"AB(cU\u0016\u001cG\u000f")
public final class ParTrieMap<K, V>
implements scala.collection.parallel.mutable.ParMap<K, V>,
ParTrieMapCombiner<K, V>,
Serializable {
    private final TrieMap<K, V> scala$collection$parallel$mutable$ParTrieMap$$ctrie;
    private volatile transient TaskSupport _combinerTaskSupport;
    private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
    private volatile ParIterableLike$ScanNode$ ScanNode$module;
    private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;

    public static <K, V> CanCombineFrom<ParTrieMap<?, ?>, Tuple2<K, V>, ParTrieMap<K, V>> canBuildFrom() {
        return ParTrieMap$.MODULE$.canBuildFrom();
    }

    @Override
    public <N extends Tuple2<K, V>, NewTo> Combiner<N, NewTo> combine(Combiner<N, NewTo> other) {
        return ParTrieMapCombiner$class.combine(this, other);
    }

    @Override
    public boolean canBeShared() {
        return ParTrieMapCombiner$class.canBeShared(this);
    }

    @Override
    public TaskSupport _combinerTaskSupport() {
        return this._combinerTaskSupport;
    }

    @Override
    @TraitSetter
    public void _combinerTaskSupport_$eq(TaskSupport x$1) {
        this._combinerTaskSupport = x$1;
    }

    @Override
    public TaskSupport combinerTaskSupport() {
        return Combiner$class.combinerTaskSupport(this);
    }

    @Override
    public void combinerTaskSupport_$eq(TaskSupport cts) {
        Combiner$class.combinerTaskSupport_$eq(this, cts);
    }

    @Override
    public Object resultWithTaskSupport() {
        return Combiner$class.resultWithTaskSupport(this);
    }

    @Override
    public void sizeHint(int size2) {
        Builder$class.sizeHint((Builder)this, size2);
    }

    @Override
    public void sizeHint(TraversableLike<?, ?> coll) {
        Builder$class.sizeHint((Builder)this, coll);
    }

    @Override
    public void sizeHint(TraversableLike<?, ?> coll, int delta) {
        Builder$class.sizeHint(this, coll, delta);
    }

    @Override
    public void sizeHintBounded(int size2, TraversableLike<?, ?> boundingColl) {
        Builder$class.sizeHintBounded(this, size2, boundingColl);
    }

    @Override
    public <NewTo> Builder<Tuple2<K, V>, NewTo> mapResult(Function1<ParTrieMap<K, V>, NewTo> f) {
        return Builder$class.mapResult(this, f);
    }

    @Override
    public <U> scala.collection.parallel.mutable.ParMap<K, U> updated(K key, U value) {
        return ParMap$class.updated(this, key, value);
    }

    @Override
    public scala.collection.parallel.mutable.ParMap<K, V> withDefault(Function1<K, V> d) {
        return ParMap$class.withDefault(this, d);
    }

    @Override
    public scala.collection.parallel.mutable.ParMap<K, V> withDefaultValue(V d) {
        return ParMap$class.withDefaultValue(this, d);
    }

    @Override
    public <U> scala.collection.parallel.mutable.ParMap<K, U> $plus(Tuple2<K, U> kv) {
        return ParMapLike$class.$plus(this, kv);
    }

    @Override
    public scala.collection.parallel.mutable.ParMap $minus(Object key) {
        return ParMapLike$class.$minus(this, key);
    }

    @Override
    public /* synthetic */ Object scala$collection$mutable$Cloneable$$super$clone() {
        return super.clone();
    }

    @Override
    public Object clone() {
        return Cloneable$class.clone(this);
    }

    @Override
    public Shrinkable<K> $minus$eq(K elem1, K elem2, Seq<K> elems) {
        return Shrinkable$class.$minus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Shrinkable<K> $minus$minus$eq(TraversableOnce<K> xs) {
        return Shrinkable$class.$minus$minus$eq(this, xs);
    }

    @Override
    public Growable $plus$eq(Object elem1, Object elem2, Seq elems) {
        return Growable$class.$plus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Growable<Tuple2<K, V>> $plus$plus$eq(TraversableOnce<Tuple2<K, V>> xs) {
        return Growable$class.$plus$plus$eq(this, xs);
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
        return (V)scala.collection.parallel.ParMapLike$class.default(this, key);
    }

    @Override
    public V apply(K key) {
        return (V)scala.collection.parallel.ParMapLike$class.apply(this, key);
    }

    @Override
    public <U> U getOrElse(K key, Function0<U> function0) {
        return (U)scala.collection.parallel.ParMapLike$class.getOrElse(this, key, function0);
    }

    @Override
    public boolean contains(K key) {
        return scala.collection.parallel.ParMapLike$class.contains(this, key);
    }

    @Override
    public boolean isDefinedAt(K key) {
        return scala.collection.parallel.ParMapLike$class.isDefinedAt(this, key);
    }

    @Override
    public IterableSplitter<K> keysIterator() {
        return scala.collection.parallel.ParMapLike$class.keysIterator(this);
    }

    @Override
    public IterableSplitter<V> valuesIterator() {
        return scala.collection.parallel.ParMapLike$class.valuesIterator(this);
    }

    @Override
    public ParSet<K> keySet() {
        return scala.collection.parallel.ParMapLike$class.keySet(this);
    }

    @Override
    public scala.collection.parallel.ParIterable<K> keys() {
        return scala.collection.parallel.ParMapLike$class.keys(this);
    }

    @Override
    public scala.collection.parallel.ParIterable<V> values() {
        return scala.collection.parallel.ParMapLike$class.values(this);
    }

    @Override
    public ParMap<K, V> filterKeys(Function1<K, Object> p) {
        return scala.collection.parallel.ParMapLike$class.filterKeys(this, p);
    }

    @Override
    public <S> ParMap<K, S> mapValues(Function1<V, S> f) {
        return scala.collection.parallel.ParMapLike$class.mapValues(this, f);
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
        ParTrieMap parTrieMap = this;
        synchronized (parTrieMap) {
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
        ParTrieMap parTrieMap = this;
        synchronized (parTrieMap) {
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
    public <S, That> Combiner<S, That> reuse(Option<Combiner<S, That>> oldc, Combiner<S, That> newc) {
        return ParIterableLike$class.reuse(this, oldc, newc);
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
    public <S, That> Object bf2seq(CanBuildFrom<ParTrieMap<K, V>, S, That> bf) {
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
    public <S, That> That map(Function1<Tuple2<K, V>, S> f, CanBuildFrom<ParTrieMap<K, V>, S, That> bf) {
        return (That)ParIterableLike$class.map(this, f, bf);
    }

    @Override
    public <S, That> That collect(PartialFunction<Tuple2<K, V>, S> pf, CanBuildFrom<ParTrieMap<K, V>, S, That> bf) {
        return (That)ParIterableLike$class.collect(this, pf, bf);
    }

    @Override
    public <S, That> That flatMap(Function1<Tuple2<K, V>, GenTraversableOnce<S>> f, CanBuildFrom<ParTrieMap<K, V>, S, That> bf) {
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
    public <U, That> That $plus$plus(GenTraversableOnce<U> that, CanBuildFrom<ParTrieMap<K, V>, U, That> bf) {
        return (That)ParIterableLike$class.$plus$plus(this, that, bf);
    }

    @Override
    public Tuple2<ParTrieMap<K, V>, ParTrieMap<K, V>> partition(Function1<Tuple2<K, V>, Object> pred) {
        return ParIterableLike$class.partition(this, pred);
    }

    @Override
    public <K> scala.collection.parallel.immutable.ParMap<K, ParTrieMap<K, V>> groupBy(Function1<Tuple2<K, V>, K> f) {
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
    public Tuple2<ParTrieMap<K, V>, ParTrieMap<K, V>> splitAt(int n) {
        return ParIterableLike$class.splitAt(this, n);
    }

    @Override
    public <U, That> That scan(U z, Function2<U, U, U> op, CanBuildFrom<ParTrieMap<K, V>, U, That> bf) {
        return (That)ParIterableLike$class.scan(this, z, op, bf);
    }

    @Override
    public <S, That> That scanLeft(S z, Function2<S, Tuple2<K, V>, S> op, CanBuildFrom<ParTrieMap<K, V>, S, That> bf) {
        return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
    }

    @Override
    public <S, That> That scanRight(S z, Function2<Tuple2<K, V>, S, S> op, CanBuildFrom<ParTrieMap<K, V>, S, That> bf) {
        return (That)ParIterableLike$class.scanRight(this, z, op, bf);
    }

    @Override
    public scala.collection.parallel.ParIterable takeWhile(Function1 pred) {
        return ParIterableLike$class.takeWhile(this, pred);
    }

    @Override
    public Tuple2<ParTrieMap<K, V>, ParTrieMap<K, V>> span(Function1<Tuple2<K, V>, Object> pred) {
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
    public <U, S, That> That zip(GenIterable<S> that, CanBuildFrom<ParTrieMap<K, V>, Tuple2<U, S>, That> bf) {
        return (That)ParIterableLike$class.zip(this, that, bf);
    }

    @Override
    public <U, That> That zipWithIndex(CanBuildFrom<ParTrieMap<K, V>, Tuple2<U, Object>, That> bf) {
        return (That)ParIterableLike$class.zipWithIndex(this, bf);
    }

    @Override
    public <S, U, That> That zipAll(GenIterable<S> that, U thisElem, S thatElem, CanBuildFrom<ParTrieMap<K, V>, Tuple2<U, S>, That> bf) {
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
    public <K, V> scala.collection.parallel.immutable.ParMap<K, V> toMap(Predef$.less.colon.less<Tuple2<K, V>, Tuple2<K, V>> ev) {
        return ParIterableLike$class.toMap(this, ev);
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
    public Combiner<Tuple2<K, V>, ParTrieMap<K, V>> parCombiner() {
        return CustomParallelizable$class.parCombiner(this);
    }

    @Override
    public <P, Q> Combiner<Tuple2<P, Q>, ParTrieMap<P, Q>> genericMapCombiner() {
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

    public TrieMap<K, V> scala$collection$parallel$mutable$ParTrieMap$$ctrie() {
        return this.scala$collection$parallel$mutable$ParTrieMap$$ctrie;
    }

    @Override
    public GenericParMapCompanion<ParTrieMap> mapCompanion() {
        return ParTrieMap$.MODULE$;
    }

    @Override
    public ParTrieMap<K, V> empty() {
        return ParTrieMap$.MODULE$.empty();
    }

    @Override
    public Combiner<Tuple2<K, V>, ParTrieMap<K, V>> newCombiner() {
        return ParTrieMap$.MODULE$.newCombiner();
    }

    @Override
    public TrieMap<K, V> seq() {
        return this.scala$collection$parallel$mutable$ParTrieMap$$ctrie();
    }

    public ParTrieMapSplitter<K, V> splitter() {
        return new ParTrieMapSplitter(0, (TrieMap)this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().readOnlySnapshot(), true);
    }

    @Override
    public void clear() {
        this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().clear();
    }

    @Override
    public ParTrieMap<K, V> result() {
        return this;
    }

    @Override
    public Option<V> get(K key) {
        return this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().get(key);
    }

    @Override
    public Option<V> put(K key, V value) {
        return this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().put(key, value);
    }

    public void update(K key, V value) {
        this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().update(key, value);
    }

    public Option<V> remove(K key) {
        return this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().remove(key);
    }

    @Override
    public ParTrieMap<K, V> $plus$eq(Tuple2<K, V> kv) {
        this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().$plus$eq((Tuple2)kv);
        return this;
    }

    public ParTrieMap<K, V> $minus$eq(K key) {
        this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().$minus$eq((Object)key);
        return this;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public int size() {
        void var6_2;
        block5: {
            int n;
            block3: {
                MainNode<K, V> r;
                block4: {
                    block2: {
                        INode<K, V> in = this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().readRoot(this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().readRoot$default$1());
                        r = in.gcasRead(this.scala$collection$parallel$mutable$ParTrieMap$$ctrie());
                        if (!(r instanceof TNode)) break block2;
                        TNode tNode = (TNode)r;
                        n = tNode.cachedSize(this.scala$collection$parallel$mutable$ParTrieMap$$ctrie());
                        break block3;
                    }
                    if (!(r instanceof LNode)) break block4;
                    LNode lNode = (LNode)r;
                    n = lNode.cachedSize(this.scala$collection$parallel$mutable$ParTrieMap$$ctrie());
                    break block3;
                }
                if (!(r instanceof CNode)) break block5;
                CNode cNode = (CNode)r;
                this.tasksupport().executeAndWaitResult(new Size(this, 0, cNode.array().length, cNode.array()));
                n = cNode.cachedSize(this.scala$collection$parallel$mutable$ParTrieMap$$ctrie());
            }
            return n;
        }
        throw new MatchError(var6_2);
    }

    @Override
    public String stringPrefix() {
        return "ParTrieMap";
    }

    public ParTrieMap(TrieMap<K, V> ctrie) {
        this.scala$collection$parallel$mutable$ParTrieMap$$ctrie = ctrie;
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
        scala.collection.parallel.ParMapLike$class.$init$(this);
        scala.collection.parallel.ParMap$class.$init$(this);
        ParIterable$class.$init$(this);
        Growable$class.$init$(this);
        Shrinkable$class.$init$(this);
        Cloneable$class.$init$(this);
        ParMapLike$class.$init$(this);
        ParMap$class.$init$(this);
        Builder$class.$init$(this);
        Combiner$class.$init$(this);
        ParTrieMapCombiner$class.$init$(this);
    }

    public ParTrieMap() {
        this(new TrieMap());
    }

    public class Size
    implements Task<Object, Size> {
        private final int offset;
        private final int howmany;
        private final BasicNode[] array;
        private int result;
        public final /* synthetic */ ParTrieMap $outer;
        private volatile Throwable throwable;

        @Override
        public Throwable throwable() {
            return this.throwable;
        }

        @Override
        @TraitSetter
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
        public void signalAbort() {
            Task$class.signalAbort(this);
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
            int sz = 0;
            int until2 = this.offset + this.howmany;
            for (int i = this.offset; i < until2; ++i) {
                BasicNode basicNode = this.array[i];
                if (basicNode instanceof SNode) {
                    ++sz;
                    continue;
                }
                if (basicNode instanceof INode) {
                    INode iNode = (INode)basicNode;
                    sz += iNode.cachedSize(this.scala$collection$parallel$mutable$ParTrieMap$Size$$$outer().scala$collection$parallel$mutable$ParTrieMap$$ctrie());
                    continue;
                }
                throw new MatchError(basicNode);
            }
            this.result_$eq(sz);
        }

        @Override
        public Seq<Size> split() {
            int fp = this.howmany / 2;
            return (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Size[]{new Size(this.scala$collection$parallel$mutable$ParTrieMap$Size$$$outer(), this.offset, fp, this.array), new Size(this.scala$collection$parallel$mutable$ParTrieMap$Size$$$outer(), this.offset + fp, this.howmany - fp, this.array)}));
        }

        @Override
        public boolean shouldSplitFurther() {
            return this.howmany > 1;
        }

        @Override
        public void merge(Size that) {
            this.result_$eq(this.result() + that.result());
        }

        public /* synthetic */ ParTrieMap scala$collection$parallel$mutable$ParTrieMap$Size$$$outer() {
            return this.$outer;
        }

        public Size(ParTrieMap<K, V> $outer, int offset, int howmany, BasicNode[] array) {
            this.offset = offset;
            this.howmany = howmany;
            this.array = array;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            this.result = -1;
        }
    }
}

