/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.Array$;
import scala.Function0;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.HashMap;
import scala.collection.immutable.HashMap$;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.ListMap;
import scala.collection.immutable.ListMap$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.UnrolledBuffer;
import scala.collection.parallel.BucketCombiner;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.Task;
import scala.collection.parallel.Task$class;
import scala.collection.parallel.immutable.HashMapCombiner$;
import scala.collection.parallel.immutable.ParHashMap;
import scala.collection.parallel.package$;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u0005ehAB\u0001\u0003\u0003\u0003!!BA\bICNDW*\u00199D_6\u0014\u0017N\\3s\u0015\t\u0019A!A\u0005j[6,H/\u00192mK*\u0011QAB\u0001\ta\u0006\u0014\u0018\r\u001c7fY*\u0011q\u0001C\u0001\u000bG>dG.Z2uS>t'\"A\u0005\u0002\u000bM\u001c\u0017\r\\1\u0016\u0007-1\u0012e\u0005\u0002\u0001\u0019A1QB\u0004\t$!\u001dj\u0011\u0001B\u0005\u0003\u001f\u0011\u0011aBQ;dW\u0016$8i\\7cS:,'\u000f\u0005\u0003\u0012%Q\u0001S\"\u0001\u0005\n\u0005MA!A\u0002+va2,'\u0007\u0005\u0002\u0016-1\u0001A!B\f\u0001\u0005\u0004I\"!A&\u0004\u0001E\u0011!$\b\t\u0003#mI!\u0001\b\u0005\u0003\u000f9{G\u000f[5oOB\u0011\u0011CH\u0005\u0003?!\u00111!\u00118z!\t)\u0012\u0005B\u0003#\u0001\t\u0007\u0011DA\u0001W!\u0011!S\u0005\u0006\u0011\u000e\u0003\tI!A\n\u0002\u0003\u0015A\u000b'\u000fS1tQ6\u000b\u0007\u000f\u0005\u0003%\u0001Q\u0001\u0003\"B\u0015\u0001\t\u0003Q\u0013A\u0002\u001fj]&$h\bF\u0001(\u0011\u001da\u0003A1A\u0005\u00025\n\u0011\"Z7qif$&/[3\u0016\u00039\u0002BaL\u0019\u0015A5\t\u0001G\u0003\u0002\u0004\r%\u0011!\u0007\r\u0002\b\u0011\u0006\u001c\b.T1q\u0011\u0019!\u0004\u0001)A\u0005]\u0005QQ-\u001c9usR\u0013\u0018.\u001a\u0011\t\u000bY\u0002A\u0011A\u001c\u0002\u0011\u0011\u0002H.^:%KF$\"\u0001O\u001d\u000e\u0003\u0001AQAO\u001bA\u0002A\tA!\u001a7f[\")A\b\u0001C\u0001{\u00051!/Z:vYR$\u0012a\t\u0005\u0006\u007f\u0001!\t\u0001Q\u0001\u000bOJ|W\u000f\u001d\"z\u0017\u0016LXCA!E)\t\u0011e\t\u0005\u0003%KQ\u0019\u0005CA\u000bE\t\u0015)eH1\u0001\u001a\u0005\u0011\u0011V\r\u001d:\t\u000b\u001ds\u0004\u0019\u0001%\u0002\u0007\r\u0014g\rE\u0002\u0012\u0013.K!A\u0013\u0005\u0003\u0013\u0019+hn\u0019;j_:\u0004\u0004\u0003B\u0007MA\rK!!\u0014\u0003\u0003\u0011\r{WNY5oKJDQa\u0014\u0001\u0005BA\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002#B\u0011!kV\u0007\u0002'*\u0011A+V\u0001\u0005Y\u0006twMC\u0001W\u0003\u0011Q\u0017M^1\n\u0005a\u001b&AB*ue&twM\u0002\u0003[\u0001\u0001Y&AC\"sK\u0006$X\r\u0016:jKN\u0019\u0011\fX0\u0011\u0005Ei\u0016B\u00010\t\u0005\u0019\te.\u001f*fMB!Q\u0002\u00192f\u0013\t\tGA\u0001\u0003UCN\\\u0007CA\td\u0013\t!\u0007B\u0001\u0003V]&$\bC\u0001\u001dZ\u0011!9\u0017L!A!\u0002\u0013A\u0017!\u00022vG.\u001c\bcA\tjW&\u0011!\u000e\u0003\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004Yj\u0004bBA7x\u001d\tqWO\u0004\u0002pi:\u0011\u0001o]\u0007\u0002c*\u0011!\u000fG\u0001\u0007yI|w\u000e\u001e \n\u0003%I!a\u0002\u0005\n\u0005Y4\u0011aB7vi\u0006\u0014G.Z\u0005\u0003qf\fa\"\u00168s_2dW\r\u001a\"vM\u001a,'O\u0003\u0002w\r%\u00111\u0010 \u0002\t+:\u0014x\u000e\u001c7fI*\u0011\u00010\u001f\u0005\t}f\u0013\t\u0011)A\u0005\u007f\u0006!!o\\8u!\r\t\u0012N\f\u0005\u000b\u0003\u0007I&\u0011!Q\u0001\n\u0005\u0015\u0011AB8gMN,G\u000fE\u0002\u0012\u0003\u000fI1!!\u0003\t\u0005\rIe\u000e\u001e\u0005\u000b\u0003\u001bI&\u0011!Q\u0001\n\u0005\u0015\u0011a\u00025po6\fg.\u001f\u0005\u0007Se#\t!!\u0005\u0015\u0013\u0015\f\u0019\"!\u0006\u0002\u0018\u0005e\u0001BB4\u0002\u0010\u0001\u0007\u0001\u000e\u0003\u0004\u007f\u0003\u001f\u0001\ra \u0005\t\u0003\u0007\ty\u00011\u0001\u0002\u0006!A\u0011QBA\b\u0001\u0004\t)\u0001\u0003\u0005=3\u0002\u0007I\u0011AA\u000f+\u0005\u0011\u0007\"CA\u00113\u0002\u0007I\u0011AA\u0012\u0003)\u0011Xm];mi~#S-\u001d\u000b\u0004E\u0006\u0015\u0002\"CA\u0014\u0003?\t\t\u00111\u0001c\u0003\rAH%\r\u0005\b\u0003WI\u0006\u0015)\u0003c\u0003\u001d\u0011Xm];mi\u0002BC!!\u000b\u00020A\u0019\u0011#!\r\n\u0007\u0005M\u0002B\u0001\u0005w_2\fG/\u001b7f\u0011\u001d\t9$\u0017C\u0001\u0003s\tA\u0001\\3bMR\u0019!-a\u000f\t\u0011\u0005u\u0012Q\u0007a\u0001\u0003\u007f\tA\u0001\u001d:fmB!\u0011#!\u0011c\u0013\r\t\u0019\u0005\u0003\u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\u0005\u001d\u0013\f\"\u0003\u0002J\u0005Q1M]3bi\u0016$&/[3\u0015\u00079\nY\u0005C\u0004\u0002N\u0005\u0015\u0003\u0019A6\u0002\u000b\u0015dW-\\:\t\u000f\u0005E\u0013\f\"\u0001\u0002T\u0005)1\u000f\u001d7jiV\u0011\u0011Q\u000b\t\u0005_\u0005]S-C\u0002\u0002ZA\u0012A\u0001T5ti\"9\u0011QL-\u0005\u0002\u0005}\u0013AE:i_VdGm\u00159mSR4UO\u001d;iKJ,\"!!\u0019\u0011\u0007E\t\u0019'C\u0002\u0002f!\u0011qAQ8pY\u0016\fgN\u0002\u0004\u0002j\u0001\u0001\u00111\u000e\u0002\u0012\u0007J,\u0017\r^3He>,\b/\u001a3Ue&,W\u0003BA7\u0003k\u001aR!a\u001a]\u0003_\u0002R!\u00041c\u0003c\u0002R\u0001OA4\u0003g\u00022!FA;\t\u0019)\u0015q\rb\u00013!Qq)a\u001a\u0003\u0002\u0003\u0006I!!\u001f\u0011\tEI\u00151\u0010\t\u0006\u001b1\u0003\u00131\u000f\u0005\nO\u0006\u001d$\u0011!Q\u0001\n!D!B`A4\u0005\u0003\u0005\u000b\u0011BAA!\u0011\t\u0012.a!\u0011\t=\nD\u0003\u0018\u0005\f\u0003\u0007\t9G!A!\u0002\u0013\t)\u0001C\u0006\u0002\u000e\u0005\u001d$\u0011!Q\u0001\n\u0005\u0015\u0001bB\u0015\u0002h\u0011\u0005\u00111\u0012\u000b\r\u0003c\ni)a$\u0002\u0012\u0006M\u0015Q\u0013\u0005\b\u000f\u0006%\u0005\u0019AA=\u0011\u00199\u0017\u0011\u0012a\u0001Q\"9a0!#A\u0002\u0005\u0005\u0005\u0002CA\u0002\u0003\u0013\u0003\r!!\u0002\t\u0011\u00055\u0011\u0011\u0012a\u0001\u0003\u000bA\u0011\u0002PA4\u0001\u0004%\t!!\b\t\u0015\u0005\u0005\u0012q\ra\u0001\n\u0003\tY\nF\u0002c\u0003;C\u0011\"a\n\u0002\u001a\u0006\u0005\t\u0019\u00012\t\u0011\u0005-\u0012q\rQ!\n\tDC!a(\u00020!A\u0011qGA4\t\u0003\t)\u000bF\u0002c\u0003OC\u0001\"!\u0010\u0002$\u0002\u0007\u0011q\b\u0005\t\u0003W\u000b9\u0007\"\u0003\u0002.\u0006\t2M]3bi\u0016<%o\\;qK\u0012$&/[3\u0015\t\u0005=\u0016\u0011\u0017\t\u0006_E\"\u00121\u000f\u0005\b\u0003\u001b\nI\u000b1\u0001l\u0011!\t),a\u001a\u0005\n\u0005]\u0016!E3wC2,\u0018\r^3D_6\u0014\u0017N\\3sgR!\u0011qVA]\u0011!\tY,a-A\u0002\u0005u\u0016\u0001\u0002;sS\u0016\u0004RaL\u0019\u0015\u0003wB\u0001\"!\u0015\u0002h\u0011\u0005\u0011\u0011Y\u000b\u0003\u0003\u0007\u0004RaLA,\u0003cB\u0001\"!\u0018\u0002h\u0011\u0005\u0011qL\u0004\t\u0003\u0013\u0014\u0001\u0012\u0001\u0003\u0002L\u0006y\u0001*Y:i\u001b\u0006\u00048i\\7cS:,'\u000fE\u0002%\u0003\u001b4q!\u0001\u0002\t\u0002\u0011\tymE\u0002\u0002NrCq!KAg\t\u0003\t\u0019\u000e\u0006\u0002\u0002L\"A\u0011q[Ag\t\u0003\tI.A\u0003baBd\u00170\u0006\u0004\u0002\\\u0006\u0005\u0018Q]\u000b\u0003\u0003;\u0004b\u0001\n\u0001\u0002`\u0006\r\bcA\u000b\u0002b\u00121q#!6C\u0002e\u00012!FAs\t\u0019\u0011\u0013Q\u001bb\u00013!Y\u0011\u0011^Ag\u0005\u0004%\tAAAv\u0003!\u0011xn\u001c;cSR\u001cXCAA\u0003\u0011%\ty/!4!\u0002\u0013\t)!A\u0005s_>$(-\u001b;tA!Y\u00111_Ag\u0005\u0004%\tAAAv\u0003!\u0011xn\u001c;tSj,\u0007\"CA|\u0003\u001b\u0004\u000b\u0011BA\u0003\u0003%\u0011xn\u001c;tSj,\u0007\u0005")
public abstract class HashMapCombiner<K, V>
extends BucketCombiner<Tuple2<K, V>, ParHashMap<K, V>, Tuple2<K, V>, HashMapCombiner<K, V>> {
    private final HashMap<K, V> emptyTrie = HashMap$.MODULE$.empty();

    public static <K, V> HashMapCombiner<K, V> apply() {
        return HashMapCombiner$.MODULE$.apply();
    }

    public HashMap<K, V> emptyTrie() {
        return this.emptyTrie;
    }

    @Override
    public HashMapCombiner<K, V> $plus$eq(Tuple2<K, V> elem) {
        this.sz_$eq(this.sz() + 1);
        int hc = this.emptyTrie().computeHash(elem._1());
        int pos = hc & 0x1F;
        if (this.buckets()[pos] == null) {
            this.buckets()[pos] = new UnrolledBuffer(ClassTag$.MODULE$.apply(Tuple2.class));
        }
        this.buckets()[pos].$plus$eq(elem);
        return this;
    }

    @Override
    public ParHashMap<K, V> result() {
        ParHashMap parHashMap;
        UnrolledBuffer.Unrolled[] bucks = (UnrolledBuffer.Unrolled[])Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])this.buckets()).filter(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(UnrolledBuffer<Tuple2<K, V>> x$3) {
                return x$3 != null;
            }
        })).map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final UnrolledBuffer.Unrolled<Tuple2<K, V>> apply(UnrolledBuffer<Tuple2<K, V>> x$4) {
                return x$4.headPtr();
            }
        }, Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(UnrolledBuffer.Unrolled.class)));
        HashMap[] root = new HashMap[bucks.length];
        this.combinerTaskSupport().executeAndWaitResult(new CreateTrie(this, bucks, root, 0, bucks.length));
        int bitmap = 0;
        for (int i = 0; i < HashMapCombiner$.MODULE$.rootsize(); ++i) {
            if (this.buckets()[i] == null) continue;
            bitmap |= 1 << i;
        }
        int sz = BoxesRunTime.unboxToInt(Predef$.MODULE$.refArrayOps((Object[])root).foldLeft(BoxesRunTime.boxToInteger(0), new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final int apply(int x$5, HashMap<K, V> x$6) {
                return x$5 + x$6.size();
            }
        }));
        if (sz == 0) {
            parHashMap = new ParHashMap();
        } else if (sz == 1) {
            parHashMap = new ParHashMap(root[0]);
        } else {
            HashMap.HashTrieMap trie = new HashMap.HashTrieMap(bitmap, root, sz);
            parHashMap = new ParHashMap(trie);
        }
        return parHashMap;
    }

    public <Repr> ParHashMap<K, Repr> groupByKey(Function0<Combiner<V, Repr>> cbf) {
        ParHashMap parHashMap;
        UnrolledBuffer.Unrolled[] bucks = (UnrolledBuffer.Unrolled[])Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])this.buckets()).filter(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(UnrolledBuffer<Tuple2<K, V>> x$7) {
                return x$7 != null;
            }
        })).map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final UnrolledBuffer.Unrolled<Tuple2<K, V>> apply(UnrolledBuffer<Tuple2<K, V>> x$8) {
                return x$8.headPtr();
            }
        }, Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(UnrolledBuffer.Unrolled.class)));
        HashMap[] root = new HashMap[bucks.length];
        this.combinerTaskSupport().executeAndWaitResult(new CreateGroupedTrie<Repr>(this, cbf, bucks, root, 0, bucks.length));
        int bitmap = 0;
        for (int i = 0; i < HashMapCombiner$.MODULE$.rootsize(); ++i) {
            if (this.buckets()[i] == null) continue;
            bitmap |= 1 << i;
        }
        int sz = BoxesRunTime.unboxToInt(Predef$.MODULE$.refArrayOps((Object[])root).foldLeft(BoxesRunTime.boxToInteger(0), new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final int apply(int x$9, HashMap<K, Object> x$10) {
                return x$9 + x$10.size();
            }
        }));
        if (sz == 0) {
            parHashMap = new ParHashMap();
        } else if (sz == 1) {
            parHashMap = new ParHashMap(root[0]);
        } else {
            HashMap.HashTrieMap trie = new HashMap.HashTrieMap(bitmap, root, sz);
            parHashMap = new ParHashMap(trie);
        }
        return parHashMap;
    }

    public String toString() {
        return new StringBuilder().append((Object)"HashTrieCombiner(sz: ").append(BoxesRunTime.boxToInteger(this.size())).append((Object)")").toString();
    }

    public HashMapCombiner() {
        super(HashMapCombiner$.MODULE$.rootsize());
    }

    public class CreateTrie
    implements Task<BoxedUnit, CreateTrie> {
        private final UnrolledBuffer.Unrolled<Tuple2<K, V>>[] bucks;
        private final HashMap<K, V>[] root;
        private final int offset;
        private final int howmany;
        private volatile BoxedUnit result;
        public final /* synthetic */ HashMapCombiner $outer;
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
        public void merge(Object that) {
            Task$class.merge(this, that);
        }

        @Override
        public void forwardThrowable() {
            Task$class.forwardThrowable(this);
        }

        @Override
        public void tryLeaf(Option<BoxedUnit> lastres) {
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
        public void result() {
        }

        @Override
        public void result_$eq(BoxedUnit x$1) {
            this.result = x$1;
        }

        @Override
        public void leaf(Option<BoxedUnit> prev) {
            int until2 = this.offset + this.howmany;
            for (int i = this.offset; i < until2; ++i) {
                this.root[i] = this.createTrie(this.bucks[i]);
            }
            this.result();
            this.result_$eq(BoxedUnit.UNIT);
        }

        /*
         * WARNING - void declaration
         */
        private HashMap<K, V> createTrie(UnrolledBuffer.Unrolled<Tuple2<K, V>> elems) {
            void var2_2;
            HashMap<Object, Object> trie = new HashMap();
            int i = 0;
            for (UnrolledBuffer.Unrolled unrolled = elems; unrolled != null; unrolled = unrolled.next()) {
                Tuple2[] chunkarr = (Tuple2[])unrolled.array();
                int chunksz = unrolled.size();
                while (i < chunksz) {
                    Tuple2 kv = chunkarr[i];
                    int hc = trie.computeHash(kv._1());
                    trie = trie.updated0(kv._1(), hc, HashMapCombiner$.MODULE$.rootbits(), kv._2(), kv, null);
                    ++i;
                }
                i = 0;
            }
            return var2_2;
        }

        public List<CreateTrie> split() {
            int fp = this.howmany / 2;
            return List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new CreateTrie[]{new CreateTrie(this.scala$collection$parallel$immutable$HashMapCombiner$CreateTrie$$$outer(), this.bucks, this.root, this.offset, fp), new CreateTrie(this.scala$collection$parallel$immutable$HashMapCombiner$CreateTrie$$$outer(), this.bucks, this.root, this.offset + fp, this.howmany - fp)}));
        }

        @Override
        public boolean shouldSplitFurther() {
            return this.howmany > package$.MODULE$.thresholdFromSize(this.root.length, this.scala$collection$parallel$immutable$HashMapCombiner$CreateTrie$$$outer().combinerTaskSupport().parallelismLevel());
        }

        public /* synthetic */ HashMapCombiner scala$collection$parallel$immutable$HashMapCombiner$CreateTrie$$$outer() {
            return this.$outer;
        }

        public CreateTrie(HashMapCombiner<K, V> $outer, UnrolledBuffer.Unrolled<Tuple2<K, V>>[] bucks, HashMap<K, V>[] root, int offset, int howmany) {
            this.bucks = bucks;
            this.root = root;
            this.offset = offset;
            this.howmany = howmany;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            this.result = BoxedUnit.UNIT;
        }
    }

    public class CreateGroupedTrie<Repr>
    implements Task<BoxedUnit, CreateGroupedTrie<Repr>> {
        private final Function0<Combiner<V, Repr>> cbf;
        private final UnrolledBuffer.Unrolled<Tuple2<K, V>>[] bucks;
        private final HashMap<K, Object>[] root;
        private final int offset;
        private final int howmany;
        private volatile BoxedUnit result;
        public final /* synthetic */ HashMapCombiner $outer;
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
        public void merge(Object that) {
            Task$class.merge(this, that);
        }

        @Override
        public void forwardThrowable() {
            Task$class.forwardThrowable(this);
        }

        @Override
        public void tryLeaf(Option<BoxedUnit> lastres) {
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
        public void result() {
        }

        @Override
        public void result_$eq(BoxedUnit x$1) {
            this.result = x$1;
        }

        @Override
        public void leaf(Option<BoxedUnit> prev) {
            int until2 = this.offset + this.howmany;
            for (int i = this.offset; i < until2; ++i) {
                this.root[i] = this.createGroupedTrie(this.bucks[i]);
            }
            this.result();
            this.result_$eq(BoxedUnit.UNIT);
        }

        private HashMap<K, Repr> createGroupedTrie(UnrolledBuffer.Unrolled<Tuple2<K, V>> elems) {
            HashMap<Object, Object> trie = new HashMap();
            int i = 0;
            for (UnrolledBuffer.Unrolled unrolled = elems; unrolled != null; unrolled = unrolled.next()) {
                Tuple2[] chunkarr = (Tuple2[])unrolled.array();
                int chunksz = unrolled.size();
                while (i < chunksz) {
                    Option option;
                    block6: {
                        Combiner combiner;
                        Tuple2 kv;
                        block5: {
                            int hc;
                            block4: {
                                kv = chunkarr[i];
                                hc = trie.computeHash(kv._1());
                                option = trie.get0(kv._1(), hc, HashMapCombiner$.MODULE$.rootbits());
                                if (!(option instanceof Some)) break block4;
                                Some some = (Some)option;
                                combiner = (Combiner)some.x();
                                break block5;
                            }
                            if (!None$.MODULE$.equals(option)) break block6;
                            Combiner cmb = this.cbf.apply();
                            trie = trie.updated0(kv._1(), hc, HashMapCombiner$.MODULE$.rootbits(), cmb, null, null);
                            combiner = cmb;
                        }
                        combiner.$plus$eq((Object)kv._2());
                        ++i;
                        continue;
                    }
                    throw new MatchError(option);
                }
                i = 0;
            }
            return this.evaluateCombiners(trie);
        }

        private HashMap<K, Repr> evaluateCombiners(HashMap<K, Combiner<V, Repr>> trie) {
            HashMap hashMap;
            if (trie instanceof HashMap.HashMap1) {
                HashMap.HashMap1 hashMap1 = (HashMap.HashMap1)((Object)trie);
                Object evaledvalue = ((Builder)hashMap1.value()).result();
                hashMap = new HashMap.HashMap1(hashMap1.key(), hashMap1.hash(), evaledvalue, null);
            } else if (trie instanceof HashMap.HashMapCollision1) {
                HashMap.HashMapCollision1 hashMapCollision1 = (HashMap.HashMapCollision1)((Object)trie);
                ListMap evaledkvs = hashMapCollision1.kvs().map(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final Tuple2<Object, Repr> apply(Tuple2<Object, Object> p) {
                        return new Tuple2<Object, To>(p._1(), ((Builder)p._2()).result());
                    }
                }, ListMap$.MODULE$.canBuildFrom());
                hashMap = new HashMap.HashMapCollision1(hashMapCollision1.hash(), evaledkvs);
            } else if (trie instanceof HashMap.HashTrieMap) {
                HashMap.HashTrieMap hashTrieMap = trie;
                for (int i = 0; i < hashTrieMap.elems().length; ++i) {
                    hashTrieMap.elems()[i] = this.evaluateCombiners(hashTrieMap.elems()[i]);
                }
                hashMap = hashTrieMap;
            } else {
                hashMap = trie;
            }
            return hashMap;
        }

        public List<CreateGroupedTrie<Repr>> split() {
            int fp = this.howmany / 2;
            return List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new CreateGroupedTrie[]{new CreateGroupedTrie<Repr>(this.scala$collection$parallel$immutable$HashMapCombiner$CreateGroupedTrie$$$outer(), this.cbf, this.bucks, this.root, this.offset, fp), new CreateGroupedTrie<Repr>(this.scala$collection$parallel$immutable$HashMapCombiner$CreateGroupedTrie$$$outer(), this.cbf, this.bucks, this.root, this.offset + fp, this.howmany - fp)}));
        }

        @Override
        public boolean shouldSplitFurther() {
            return this.howmany > package$.MODULE$.thresholdFromSize(this.root.length, this.scala$collection$parallel$immutable$HashMapCombiner$CreateGroupedTrie$$$outer().combinerTaskSupport().parallelismLevel());
        }

        public /* synthetic */ HashMapCombiner scala$collection$parallel$immutable$HashMapCombiner$CreateGroupedTrie$$$outer() {
            return this.$outer;
        }

        public CreateGroupedTrie(HashMapCombiner<K, V> $outer, Function0<Combiner<V, Repr>> cbf, UnrolledBuffer.Unrolled<Tuple2<K, V>>[] bucks, HashMap<K, Object>[] root, int offset, int howmany) {
            this.cbf = cbf;
            this.bucks = bucks;
            this.root = root;
            this.offset = offset;
            this.howmany = howmany;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            this.result = BoxedUnit.UNIT;
        }
    }
}

