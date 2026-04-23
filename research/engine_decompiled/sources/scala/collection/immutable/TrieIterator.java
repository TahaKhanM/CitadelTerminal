/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Array$;
import scala.Function1;
import scala.MatchError;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.AbstractIterator;
import scala.collection.IndexedSeqOptimized$class;
import scala.collection.Iterator;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.HashMap;
import scala.collection.immutable.HashMap$;
import scala.collection.immutable.HashSet;
import scala.collection.immutable.HashSet$;
import scala.collection.immutable.Iterable;
import scala.collection.immutable.Iterable$;
import scala.collection.immutable.ListSet$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.immutable.TrieIterator$;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArrayBuilder$;
import scala.collection.mutable.ArrayOps;
import scala.collection.mutable.Buffer;
import scala.math.Numeric$IntIsIntegral$;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001\u0005%eaB\u0001\u0003\u0003\u0003!\u0001b\n\u0002\r)JLW-\u0013;fe\u0006$xN\u001d\u0006\u0003\u0007\u0011\t\u0011\"[7nkR\f'\r\\3\u000b\u0005\u00151\u0011AC2pY2,7\r^5p]*\tq!A\u0003tG\u0006d\u0017-\u0006\u0002\n!M\u0011\u0001A\u0003\t\u0004\u00171qQ\"\u0001\u0003\n\u00055!!\u0001E!cgR\u0014\u0018m\u0019;Ji\u0016\u0014\u0018\r^8s!\ty\u0001\u0003\u0004\u0001\u0005\rE\u0001AQ1\u0001\u0014\u0005\u0005!6\u0001A\t\u0003)a\u0001\"!\u0006\f\u000e\u0003\u0019I!a\u0006\u0004\u0003\u000f9{G\u000f[5oOB\u0011Q#G\u0005\u00035\u0019\u00111!\u00118z\u0011!a\u0002A!A!\u0002\u0013i\u0012!B3mK6\u001c\bcA\u000b\u001fA%\u0011qD\u0002\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004C\trQ\"\u0001\u0002\n\u0005\r\u0012!\u0001C%uKJ\f'\r\\3\t\u000b\u0015\u0002A\u0011\u0001\u0014\u0002\rqJg.\u001b;?)\t9\u0003\u0006E\u0002\"\u00019AQ\u0001\b\u0013A\u0002uAaA\u000b\u0001\u0007\u0002\tY\u0013aB4fi\u0016cW-\u001c\u000b\u0003\u001d1BQ!L\u0015A\u00029\n\u0011\u0001\u001f\t\u0003+=J!\u0001\r\u0004\u0003\r\u0005s\u0017PU3g\u0011\u0015\u0011\u0004\u0001\"\u00014\u0003%Ig.\u001b;EKB$\b.F\u00015!\t)R'\u0003\u00027\r\t\u0019\u0011J\u001c;\t\u000ba\u0002A\u0011A\u001d\u0002\u001d%t\u0017\u000e^!se\u0006L8\u000b^1dWV\t!\bE\u0002\u0016=m\u00022!\u0006\u0010=!\r\t#%\u0010\u0016\u0003\u001dyZ\u0013a\u0010\t\u0003\u0001\u0016k\u0011!\u0011\u0006\u0003\u0005\u000e\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u00113\u0011AC1o]>$\u0018\r^5p]&\u0011a)\u0011\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007\"\u0002%\u0001\t\u0003I\u0015\u0001D5oSR\u0004vn]*uC\u000e\\W#\u0001&\u0011\u0007UqB\u0007C\u0003M\u0001\u0011\u0005Q*\u0001\u0006j]&$\u0018I\u001d:bs\u0012+\u0012a\u000f\u0005\u0006\u001f\u0002!\taM\u0001\tS:LG\u000fU8t\t\")\u0011\u000b\u0001C\u0001%\u0006Y\u0011N\\5u'V\u0014\u0017\n^3s+\u0005\u0019\u0006cA\u0006U\u001d%\u0011Q\u000b\u0002\u0002\t\u0013R,'/\u0019;pe\"1q\u000b\u0001Q!\nQ\nQ\u0001Z3qi\"Da!\u0017\u0001!B\u0013Q\u0014AC1se\u0006L8\u000b^1dW\"11\f\u0001Q!\n)\u000b\u0001\u0002]8t'R\f7m\u001b\u0005\u0007;\u0002\u0001\u000b\u0015B\u001e\u0002\r\u0005\u0014(/Y=E\u0011\u0019y\u0006\u0001)Q\u0005i\u0005!\u0001o\\:E\u0011\u0019\t\u0007\u0001)Q\u0005'\u000691/\u001e2Ji\u0016\u0014\bBB2\u0001A\u0013%A-\u0001\u0005hKR,E.Z7t)\tiR\rC\u0003.E\u0002\u0007\u0001\u0005\u0003\u0004h\u0001\u0001&I\u0001[\u0001\u0011G>dG.[:j_:$v.\u0011:sCf$\"!H5\t\u000b52\u0007\u0019\u0001\u0011\u0006\r-\u0004\u0001\u0015!\u0003m\u00059\u0019\u0006\u000f\\5u\u0013R,'/\u0019;peN\u0004B!F7p'&\u0011aN\u0002\u0002\u0007)V\u0004H.\u001a\u001a\u0011\tUi7\u000b\u000e\u0005\u0006c\u0002!IA]\u0001\u0007SN$&/[3\u0015\u0005M4\bCA\u000bu\u0013\t)hAA\u0004C_>dW-\u00198\t\u000b5\u0002\b\u0019\u0001\u0018\t\u000ba\u0004A\u0011B=\u0002\u0017%\u001c8i\u001c8uC&tWM\u001d\u000b\u0003gjDQ!L<A\u000292A\u0001 \u0001\u0003{\nYA)\u001e9Ji\u0016\u0014\u0018\r^8s'\tYx\u0005C\u00043w\n\u0007I\u0011I\u001a\t\u0015\u0005\u00051\u0010\"A\u0001B\u0003%A'\u0001\u0006j]&$H)\u001a9uQ\u0002Bq\u0001O>C\u0002\u0013\u0005\u0013\b\u0003\u0006\u0002\bm$\t\u0011!Q\u0001\ni\nq\"\u001b8ji\u0006\u0013(/Y=Ti\u0006\u001c7\u000e\t\u0005\b\u0011n\u0014\r\u0011\"\u0011J\u0011)\tia\u001fC\u0001\u0002\u0003\u0006IAS\u0001\u000eS:LG\u000fU8t'R\f7m\u001b\u0011\t\u000f1[(\u0019!C!\u001b\"Q\u00111C>\u0005\u0002\u0003\u0005\u000b\u0011B\u001e\u0002\u0017%t\u0017\u000e^!se\u0006LH\t\t\u0005\b\u001fn\u0014\r\u0011\"\u00114\u0011)\tIb\u001fC\u0001\u0002\u0003\u0006I\u0001N\u0001\nS:LG\u000fU8t\t\u0002Bq!U>C\u0002\u0013\u0005#\u000b\u0003\u0006\u0002 m$\t\u0011!Q\u0001\nM\u000bA\"\u001b8jiN+(-\u0013;fe\u0002B\u0011\"a\t|\u0005\u0003\u0005\u000b\u0011B\u000f\u0002\u0005a\u001c\bBB\u0013|\t\u0003\t9\u0003\u0006\u0003\u0002*\u00055\u0002cAA\u0016w6\t\u0001\u0001C\u0004\u0002$\u0005\u0015\u0002\u0019A\u000f\t\r)ZHQIA\u0019)\rq\u00111\u0007\u0005\u0007[\u0005=\u0002\u0019\u0001\u0018\t\u000f\u0005]\u0002\u0001\"\u0001\u0002:\u0005YA-\u001e9Ji\u0016\u0014\u0018\r^8s+\u00059\u0003\u0002CA\u001f\u0001\u0001&I!a\u0010\u0002\u00179,w/\u0013;fe\u0006$xN\u001d\u000b\u0004O\u0005\u0005\u0003bBA\u0012\u0003w\u0001\r!\b\u0005\t\u0003\u000b\u0002\u0001\u0015\"\u0003\u0002H\u0005\u0001\u0012\u000e^3sCR|'oV5uQNK'0\u001a\u000b\u0004_\u0006%\u0003bBA&\u0003\u0007\u0002\r!H\u0001\u0004CJ\u0014\b\u0002CA(\u0001\u0001&I!!\u0015\u0002!\u0005\u0014(/Y=U_&#XM]1u_J\u001cH\u0003BA*\u0003+\u00022!a\u000bk\u0011\u001d\tY%!\u0014A\u0002uA\u0001\"!\u0017\u0001A\u0013%\u00111L\u0001\u000bgBd\u0017\u000e^!se\u0006LH\u0003BA*\u0003;Bq!a\u0018\u0002X\u0001\u0007Q$\u0001\u0002bI\"9\u00111\r\u0001\u0005\u0002\u0005\u0015\u0014a\u00025bg:+\u0007\u0010^\u000b\u0002g\"9\u0011\u0011\u000e\u0001\u0005\u0002\u0005-\u0014\u0001\u00028fqR$\u0012A\u0004\u0005\t\u0003_\u0002\u0001\u0015\"\u0003\u0002r\u0005)a.\u001a=uaQ)a\"a\u001d\u0002v!1A$!\u001cA\u0002uAq!a\u001e\u0002n\u0001\u0007A'A\u0001jQ\u0011\ti'a\u001f\u0011\t\u0005u\u0014qP\u0007\u0002\u0007&\u0019\u0011\u0011Q\"\u0003\u000fQ\f\u0017\u000e\u001c:fG\"9\u0011Q\u0011\u0001\u0005\u0002\u0005\u001d\u0015!B:qY&$XCAA*\u0001")
public abstract class TrieIterator<T>
extends AbstractIterator<T> {
    private final Iterable<T>[] elems;
    public int scala$collection$immutable$TrieIterator$$depth;
    public Iterable<T>[][] scala$collection$immutable$TrieIterator$$arrayStack;
    public int[] scala$collection$immutable$TrieIterator$$posStack;
    public Iterable<T>[] scala$collection$immutable$TrieIterator$$arrayD;
    public int scala$collection$immutable$TrieIterator$$posD;
    public Iterator<T> scala$collection$immutable$TrieIterator$$subIter;

    public abstract T getElem(Object var1);

    public int initDepth() {
        return 0;
    }

    public Iterable<T>[][] initArrayStack() {
        return new Iterable[6][];
    }

    public int[] initPosStack() {
        return new int[6];
    }

    public Iterable<T>[] initArrayD() {
        return this.elems;
    }

    public int initPosD() {
        return 0;
    }

    public Iterator<T> initSubIter() {
        return null;
    }

    private Iterable<T>[] getElems(Iterable<T> x) {
        block4: {
            AbstractIterable[] abstractIterableArray;
            block3: {
                block2: {
                    if (!(x instanceof HashMap.HashTrieMap)) break block2;
                    HashMap.HashTrieMap hashTrieMap = (HashMap.HashTrieMap)x;
                    abstractIterableArray = hashTrieMap.elems();
                    break block3;
                }
                if (!(x instanceof HashSet.HashTrieSet)) break block4;
                HashSet.HashTrieSet hashTrieSet = (HashSet.HashTrieSet)x;
                abstractIterableArray = hashTrieSet.elems();
            }
            return (Iterable[])abstractIterableArray;
        }
        throw new MatchError(x);
    }

    private Iterable<T>[] collisionToArray(Iterable<T> x) {
        block4: {
            AbstractIterable[] abstractIterableArray;
            block3: {
                block2: {
                    if (!(x instanceof HashMap.HashMapCollision1)) break block2;
                    HashMap.HashMapCollision1 hashMapCollision1 = (HashMap.HashMapCollision1)x;
                    abstractIterableArray = (AbstractIterable[])((TraversableOnce)hashMapCollision1.kvs().map((Function1<Tuple2<Object, Object>, HashMap<Object, Object>>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final HashMap<Object, Object> apply(Tuple2<Object, Object> x) {
                            return (HashMap)HashMap$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[]{x}));
                        }
                    }), Iterable$.MODULE$.canBuildFrom())).toArray(ClassTag$.MODULE$.apply(HashMap.class));
                    break block3;
                }
                if (!(x instanceof HashSet.HashSetCollision1)) break block4;
                HashSet.HashSetCollision1 hashSetCollision1 = (HashSet.HashSetCollision1)x;
                abstractIterableArray = (AbstractIterable[])((TraversableOnce)hashSetCollision1.ks().map(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final HashSet<Object> apply(Object x) {
                        return (HashSet)HashSet$.MODULE$.apply(Predef$.MODULE$.genericWrapArray(new Object[]{x}));
                    }
                }, ListSet$.MODULE$.canBuildFrom())).toArray(ClassTag$.MODULE$.apply(HashSet.class));
            }
            return (Iterable[])abstractIterableArray;
        }
        throw new MatchError(x);
    }

    private boolean isTrie(Object x) {
        boolean bl = x instanceof HashMap.HashTrieMap ? true : x instanceof HashSet.HashTrieSet;
        boolean bl2 = bl;
        return bl2;
    }

    private boolean isContainer(Object x) {
        boolean bl = x instanceof HashMap.HashMap1 ? true : x instanceof HashSet.HashSet1;
        boolean bl2 = bl;
        return bl2;
    }

    public TrieIterator<T> dupIterator() {
        return new DupIterator(this.elems);
    }

    private TrieIterator<T> newIterator(Iterable<T>[] xs) {
        return new TrieIterator<T>(this, (Iterable[])xs){
            private final /* synthetic */ TrieIterator $outer;

            public final T getElem(Object x) {
                return this.$outer.getElem(x);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((Iterable<T>[])xs$1);
            }
        };
    }

    private Tuple2<Iterator<T>, Object> iteratorWithSize(Iterable<T>[] arr) {
        return new Tuple2<Iterator<T>, Object>(this.newIterator(arr), Predef$.MODULE$.intArrayOps((int[])Predef$.MODULE$.refArrayOps((Object[])arr).map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final int apply(Iterable<T> x$1) {
                return x$1.size();
            }
        }, Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.Int()))).sum(Numeric$IntIsIntegral$.MODULE$));
    }

    private Tuple2<Tuple2<Iterator<T>, Object>, Iterator<T>> arrayToIterators(Iterable<T>[] arr) {
        Tuple2 tuple2 = Predef$.MODULE$.refArrayOps((Object[])arr).splitAt(arr.length / 2);
        if (tuple2 != null) {
            Tuple2 tuple22 = new Tuple2(tuple2._1(), tuple2._2());
            Iterable[] fst = (Iterable[])tuple22._1();
            Iterable[] snd = (Iterable[])tuple22._2();
            return new Tuple2<Tuple2<Iterator<T>, Object>, Iterator<T>>(this.iteratorWithSize(snd), this.newIterator(fst));
        }
        throw new MatchError(tuple2);
    }

    private Tuple2<Tuple2<Iterator<T>, Object>, Iterator<T>> splitArray(Iterable<T>[] ad) {
        while (true) {
            block5: {
                Tuple2<Tuple2<Iterator<T>, Object>, Iterator<T>> tuple2;
                block4: {
                    block3: {
                        if (ad.length <= 1) break block3;
                        tuple2 = this.arrayToIterators(ad);
                        break block4;
                    }
                    Iterable<T> iterable = ad[0];
                    boolean bl = iterable instanceof HashMap.HashMapCollision1 ? true : iterable instanceof HashSet.HashSetCollision1;
                    if (!bl) break block5;
                    tuple2 = this.arrayToIterators(this.collisionToArray(ad[0]));
                }
                return tuple2;
            }
            ad = this.getElems(ad[0]);
        }
    }

    @Override
    public boolean hasNext() {
        return this.scala$collection$immutable$TrieIterator$$subIter != null || this.scala$collection$immutable$TrieIterator$$depth >= 0;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public T next() {
        T t;
        if (this.scala$collection$immutable$TrieIterator$$subIter != null) {
            void var1_1;
            T el = this.scala$collection$immutable$TrieIterator$$subIter.next();
            if (!this.scala$collection$immutable$TrieIterator$$subIter.hasNext()) {
                this.scala$collection$immutable$TrieIterator$$subIter = null;
            }
            t = var1_1;
        } else {
            t = this.next0(this.scala$collection$immutable$TrieIterator$$arrayD, this.scala$collection$immutable$TrieIterator$$posD);
        }
        return t;
    }

    private T next0(Iterable<T>[] elems, int i) {
        T t;
        block7: {
            Iterable<T> m;
            while (true) {
                if (i == elems.length - 1) {
                    --this.scala$collection$immutable$TrieIterator$$depth;
                    if (this.scala$collection$immutable$TrieIterator$$depth >= 0) {
                        this.scala$collection$immutable$TrieIterator$$arrayD = this.scala$collection$immutable$TrieIterator$$arrayStack[this.scala$collection$immutable$TrieIterator$$depth];
                        this.scala$collection$immutable$TrieIterator$$posD = this.scala$collection$immutable$TrieIterator$$posStack[this.scala$collection$immutable$TrieIterator$$depth];
                        this.scala$collection$immutable$TrieIterator$$arrayStack[this.scala$collection$immutable$TrieIterator$$depth] = null;
                    } else {
                        this.scala$collection$immutable$TrieIterator$$arrayD = null;
                        this.scala$collection$immutable$TrieIterator$$posD = 0;
                    }
                } else {
                    ++this.scala$collection$immutable$TrieIterator$$posD;
                }
                m = elems[i];
                if (this.isContainer(m)) {
                    t = this.getElem(m);
                    break block7;
                }
                if (!this.isTrie(m)) break;
                if (this.scala$collection$immutable$TrieIterator$$depth >= 0) {
                    this.scala$collection$immutable$TrieIterator$$arrayStack[this.scala$collection$immutable$TrieIterator$$depth] = this.scala$collection$immutable$TrieIterator$$arrayD;
                    this.scala$collection$immutable$TrieIterator$$posStack[this.scala$collection$immutable$TrieIterator$$depth] = this.scala$collection$immutable$TrieIterator$$posD;
                }
                ++this.scala$collection$immutable$TrieIterator$$depth;
                this.scala$collection$immutable$TrieIterator$$arrayD = this.getElems(m);
                this.scala$collection$immutable$TrieIterator$$posD = 0;
                i = 0;
                elems = this.getElems(m);
            }
            this.scala$collection$immutable$TrieIterator$$subIter = m.iterator();
            t = this.next();
        }
        return t;
    }

    /*
     * Enabled aggressive block sorting
     */
    public Tuple2<Tuple2<Iterator<T>, Object>, Iterator<T>> split() {
        int szsnd;
        Iterable[] snd;
        Tuple2<Tuple2<$anon$1, Integer>, TrieIterator> tuple2;
        block6: {
            Range range2;
            block7: {
                block5: {
                    if (this.scala$collection$immutable$TrieIterator$$arrayD != null && this.scala$collection$immutable$TrieIterator$$depth == 0 && this.scala$collection$immutable$TrieIterator$$posD == 0) {
                        return this.splitArray(this.scala$collection$immutable$TrieIterator$$arrayD);
                    }
                    if (this.scala$collection$immutable$TrieIterator$$subIter != null) {
                        Buffer buff = this.scala$collection$immutable$TrieIterator$$subIter.toBuffer();
                        this.scala$collection$immutable$TrieIterator$$subIter = null;
                        tuple2 = new Tuple2<Tuple2<$anon$1, Integer>, TrieIterator>(new Tuple2(buff.iterator(), BoxesRunTime.boxToInteger(buff.length())), this);
                        return tuple2;
                    }
                    if (this.scala$collection$immutable$TrieIterator$$depth <= 0) break block5;
                    if (this.scala$collection$immutable$TrieIterator$$posStack[0] != this.scala$collection$immutable$TrieIterator$$arrayStack[0].length - 1) {
                        Object[] objectArray = this.scala$collection$immutable$TrieIterator$$arrayStack[0];
                        Predef$ predef$ = Predef$.MODULE$;
                        Tuple2 tuple22 = IndexedSeqOptimized$class.splitAt(new ArrayOps.ofRef<Object>(objectArray), this.scala$collection$immutable$TrieIterator$$arrayStack[0].length - (this.scala$collection$immutable$TrieIterator$$arrayStack[0].length - this.scala$collection$immutable$TrieIterator$$posStack[0] + 1) / 2);
                        if (tuple22 == null) throw new MatchError(tuple22);
                        Tuple2 tuple23 = new Tuple2(tuple22._1(), tuple22._2());
                        Iterable[] fst = (Iterable[])tuple23._1();
                        Iterable[] snd2 = (Iterable[])tuple23._2();
                        this.scala$collection$immutable$TrieIterator$$arrayStack[0] = fst;
                        tuple2 = new Tuple2<Tuple2<$anon$1, Integer>, TrieIterator>(this.iteratorWithSize(snd2), this);
                        return tuple2;
                    }
                    Iterable[] iterableArray = new Iterable[1];
                    Object[] objectArray = this.scala$collection$immutable$TrieIterator$$arrayStack[0];
                    Predef$ predef$ = Predef$.MODULE$;
                    iterableArray[0] = (Iterable)IndexedSeqOptimized$class.last(new ArrayOps.ofRef<Object>(objectArray));
                    snd = (Iterable[])((Object[])iterableArray);
                    szsnd = snd[0].size();
                    --this.scala$collection$immutable$TrieIterator$$depth;
                    Predef$ predef$2 = Predef$.MODULE$;
                    int n = this.scala$collection$immutable$TrieIterator$$arrayStack.length;
                    Range$ range$ = Range$.MODULE$;
                    range2 = new Range(1, n, 1);
                    if (range2.isEmpty()) break block6;
                    break block7;
                }
                if (this.scala$collection$immutable$TrieIterator$$posD != this.scala$collection$immutable$TrieIterator$$arrayD.length - 1) {
                    Object[] objectArray = this.scala$collection$immutable$TrieIterator$$arrayD;
                    Predef$ predef$ = Predef$.MODULE$;
                    Tuple2 tuple24 = IndexedSeqOptimized$class.splitAt(new ArrayOps.ofRef<Object>(objectArray), this.scala$collection$immutable$TrieIterator$$arrayD.length - (this.scala$collection$immutable$TrieIterator$$arrayD.length - this.scala$collection$immutable$TrieIterator$$posD + 1) / 2);
                    if (tuple24 == null) throw new MatchError(tuple24);
                    Tuple2 tuple25 = new Tuple2(tuple24._1(), tuple24._2());
                    Iterable[] fst = (Iterable[])tuple25._1();
                    Iterable[] snd3 = (Iterable[])tuple25._2();
                    this.scala$collection$immutable$TrieIterator$$arrayD = fst;
                    tuple2 = new Tuple2<Tuple2<$anon$1, Integer>, TrieIterator>(this.iteratorWithSize(snd3), this);
                    return tuple2;
                }
                Iterable<T> m = this.scala$collection$immutable$TrieIterator$$arrayD[this.scala$collection$immutable$TrieIterator$$posD];
                tuple2 = this.arrayToIterators(this.isTrie(m) ? this.getElems(m) : this.collisionToArray(m));
                return tuple2;
            }
            int i1 = range2.start();
            while (true) {
                this.scala$collection$immutable$TrieIterator$$arrayStack[i1 - 1] = this.scala$collection$immutable$TrieIterator$$arrayStack[i1];
                if (i1 == range2.lastElement()) break;
                i1 += range2.step();
            }
        }
        this.scala$collection$immutable$TrieIterator$$arrayStack[this.scala$collection$immutable$TrieIterator$$arrayStack.length - 1] = (Iterable[])((Object[])new Iterable[]{null});
        int[] nArray = this.scala$collection$immutable$TrieIterator$$posStack;
        Predef$ predef$ = Predef$.MODULE$;
        int[] nArray2 = (int[])IndexedSeqOptimized$class.tail(new ArrayOps.ofInt(nArray));
        Predef$ predef$3 = Predef$.MODULE$;
        int[] nArray3 = (int[])Array$.MODULE$.apply(Predef$.MODULE$.wrapIntArray(new int[]{0}), ClassTag$.MODULE$.Int());
        Predef$ predef$4 = Predef$.MODULE$;
        ClassTag<Object> classTag = ClassTag$.MODULE$.Int();
        Array$ array$ = Array$.MODULE$;
        this.scala$collection$immutable$TrieIterator$$posStack = (int[])TraversableLike$class.$plus$plus(new ArrayOps.ofInt(nArray2), new ArrayOps.ofInt(nArray3), new CanBuildFrom<Object, T, Object>(classTag){
            private final ClassTag t$1;

            public ArrayBuilder<T> apply(Object from2) {
                return ArrayBuilder$.MODULE$.make(this.t$1);
            }

            public ArrayBuilder<T> apply() {
                return ArrayBuilder$.MODULE$.make(this.t$1);
            }
            {
                this.t$1 = t$1;
            }
        });
        tuple2 = new Tuple2<Tuple2<$anon$1, Integer>, TrieIterator>(new Tuple2<$anon$1, Integer>(new /* invalid duplicate definition of identical inner class */, BoxesRunTime.boxToInteger(szsnd)), this);
        return tuple2;
    }

    public TrieIterator(Iterable<T>[] elems) {
        this.elems = elems;
        this.scala$collection$immutable$TrieIterator$$depth = this.initDepth();
        this.scala$collection$immutable$TrieIterator$$arrayStack = this.initArrayStack();
        this.scala$collection$immutable$TrieIterator$$posStack = this.initPosStack();
        this.scala$collection$immutable$TrieIterator$$arrayD = this.initArrayD();
        this.scala$collection$immutable$TrieIterator$$posD = this.initPosD();
        this.scala$collection$immutable$TrieIterator$$subIter = this.initSubIter();
    }

    public final class DupIterator
    extends TrieIterator<T> {
        private final int initDepth;
        private final Iterable<T>[][] initArrayStack;
        private final int[] initPosStack;
        private final Iterable<T>[] initArrayD;
        private final int initPosD;
        private final Iterator<T> initSubIter;

        @Override
        public int initDepth() {
            return this.initDepth;
        }

        @Override
        public Iterable<T>[][] initArrayStack() {
            return this.initArrayStack;
        }

        @Override
        public int[] initPosStack() {
            return this.initPosStack;
        }

        @Override
        public Iterable<T>[] initArrayD() {
            return this.initArrayD;
        }

        @Override
        public int initPosD() {
            return this.initPosD;
        }

        @Override
        public Iterator<T> initSubIter() {
            return this.initSubIter;
        }

        @Override
        public final T getElem(Object x) {
            return TrieIterator.this.getElem(x);
        }

        public DupIterator(Iterable<T>[] xs) {
            int initPosD;
            int initDepth;
            if (TrieIterator.this == null) {
                throw null;
            }
            this.initDepth = initDepth = TrieIterator.this.scala$collection$immutable$TrieIterator$$depth;
            Iterable<T>[][] initArrayStack = TrieIterator.this.scala$collection$immutable$TrieIterator$$arrayStack;
            this.initArrayStack = initArrayStack;
            int[] initPosStack = TrieIterator.this.scala$collection$immutable$TrieIterator$$posStack;
            this.initPosStack = initPosStack;
            Iterable<T>[] initArrayD = TrieIterator.this.scala$collection$immutable$TrieIterator$$arrayD;
            this.initArrayD = initArrayD;
            this.initPosD = initPosD = TrieIterator.this.scala$collection$immutable$TrieIterator$$posD;
            Iterator initSubIter = TrieIterator.this.scala$collection$immutable$TrieIterator$$subIter;
            this.initSubIter = initSubIter;
            super(xs);
        }
    }
}

