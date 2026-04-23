/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Array$;
import scala.Function1;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.collection.AbstractIterator;
import scala.collection.BitSet$class;
import scala.collection.BitSetLike;
import scala.collection.BitSetLike$class;
import scala.collection.GenSet;
import scala.collection.GenSetLike$class;
import scala.collection.Iterator;
import scala.collection.SortedSet;
import scala.collection.SortedSetLike$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.Sorted;
import scala.collection.generic.Sorted$class;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.mutable.AbstractSet;
import scala.collection.mutable.BitSet$;
import scala.collection.mutable.SortedSet$class;
import scala.collection.mutable.StringBuilder;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.RichInt$;

@ScalaSignature(bytes="\u0006\u0001\u00055f\u0001B\u0001\u0003\u0001%\u0011aAQ5u'\u0016$(BA\u0002\u0005\u0003\u001diW\u000f^1cY\u0016T!!\u0002\u0004\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019r\u0001\u0001\u0006\u0013+aar\u0004E\u0002\f\u00199i\u0011AA\u0005\u0003\u001b\t\u00111\"\u00112tiJ\f7\r^*fiB\u0011q\u0002E\u0007\u0002\r%\u0011\u0011C\u0002\u0002\u0004\u0013:$\bcA\u0006\u0014\u001d%\u0011AC\u0001\u0002\n'>\u0014H/\u001a3TKR\u0004\"AF\f\u000e\u0003\u0011I!!\u0001\u0003\u0011\u0007YI2$\u0003\u0002\u001b\t\tQ!)\u001b;TKRd\u0015n[3\u0011\u0005-\u0001\u0001\u0003B\u0006\u001e\u001dmI!A\b\u0002\u0003\u000fM+G\u000fT5lKB\u0011q\u0002I\u0005\u0003C\u0019\u0011AbU3sS\u0006d\u0017N_1cY\u0016D\u0001b\t\u0001\u0003\u0002\u0004%)\u0002J\u0001\u0006K2,Wn]\u000b\u0002KA\u0019qB\n\u0015\n\u0005\u001d2!!B!se\u0006L\bCA\b*\u0013\tQcA\u0001\u0003M_:<\u0007\u0002\u0003\u0017\u0001\u0005\u0003\u0007IQC\u0017\u0002\u0013\u0015dW-\\:`I\u0015\fHC\u0001\u00182!\tyq&\u0003\u00021\r\t!QK\\5u\u0011\u001d\u00114&!AA\u0002\u0015\n1\u0001\u001f\u00132\u0011!!\u0004A!A!B\u001b)\u0013AB3mK6\u001c\b\u0005C\u00037\u0001\u0011\u0005q'\u0001\u0004=S:LGO\u0010\u000b\u00037aBQaI\u001bA\u0002\u0015BQA\u000f\u0001\u0005Bm\nQ!Z7qif,\u0012a\u0007\u0005\u0006m\u0001!\t!\u0010\u000b\u00037yBQa\u0010\u001fA\u00029\t\u0001\"\u001b8jiNK'0\u001a\u0005\u0006m\u0001!\t!\u0011\u000b\u00027!)1\t\u0001C\t\t\u00061an^8sIN,\u0012A\u0004\u0015\u0005\u0005\u001aK5\n\u0005\u0002\u0010\u000f&\u0011\u0001J\u0002\u0002\u0015I\u0016\u0004(/Z2bi\u0016$wJ^3se&$\u0017N\\4\"\u0003)\u000b!*\u00138uKJt\u0017\r\u001c\u0011j[BdW-\\3oi\u0006$\u0018n\u001c8!I>,7\u000f\t8pi\u0002\nG-\\5uAM,gn]5cY\u0016\u0004sN^3se&$\u0017N\\4!_\u001a\u0004C\u000f[5tA5,G\u000f[8e]\u0005\nA*\u0001\u00043]E\nd\u0006\r\u0005\u0006\u001d\u0002!\tbT\u0001\u0005o>\u0014H\r\u0006\u0002)!\")\u0011+\u0014a\u0001\u001d\u0005\u0019\u0011\u000e\u001a=)\t53\u0015j\u0013\u0005\u0006)\u0002!)\"V\u0001\u000bkB$\u0017\r^3X_J$Gc\u0001\u0018W/\")\u0011k\u0015a\u0001\u001d!)\u0001l\u0015a\u0001Q\u0005\tq\u000fC\u0003[\u0001\u0011U1,\u0001\bf]N,(/Z\"ba\u0006\u001c\u0017\u000e^=\u0015\u00059b\u0006\"B)Z\u0001\u0004q\u0001\"\u00020\u0001\t#y\u0016!\u00054s_6\u0014\u0015\u000e^'bg.tunQ8qsR\u00111\u0004\u0019\u0005\u0006Cv\u0003\r!J\u0001\u0006o>\u0014Hm\u001d\u0005\u0006G\u0002!\t\u0005Z\u0001\u0004C\u0012$GCA3i!\tya-\u0003\u0002h\r\t9!i\\8mK\u0006t\u0007\"B5c\u0001\u0004q\u0011\u0001B3mK6DQa\u001b\u0001\u0005B1\faA]3n_Z,GCA3n\u0011\u0015I'\u000e1\u0001\u000f\u0011\u0015y\u0007\u0001\"\u0001q\u0003!!\u0003\u000f\\;tI\u0015\fHCA9s\u001b\u0005\u0001\u0001\"B5o\u0001\u0004q\u0001\u0006\u00028Gi.\u000b\u0013!^\u0001G\u001fZ,'O]5eK\u0002\nG\r\u001a\u0011u_\u0002\u0002(/\u001a<f]R\u00043&\u0010\u0011b]\u0012\u0004\u0013\r\u001a3!MJ|W\u000eI3yQ&\u0014\u0017\u000e^5oO\u0002\"\u0017N\u001a4fe\u0016tG\u000f\t2fQ\u00064\u0018n\u001c:/\u0011\u00159\b\u0001\"\u0001y\u0003%!S.\u001b8vg\u0012*\u0017\u000f\u0006\u0002rs\")\u0011N\u001ea\u0001\u001d!\"aO\u0012;L\u0011\u0015a\b\u0001\"\u0001~\u0003\u001d!#-\u0019:%KF$\"!\u001d@\t\u000b}\\\b\u0019A\u000e\u0002\u000b=$\b.\u001a:\t\u000f\u0005\r\u0001\u0001\"\u0001\u0002\u0006\u00059A%Y7qI\u0015\fHcA9\u0002\b!1q0!\u0001A\u0002mAq!a\u0003\u0001\t\u0003\ti!\u0001\u0004%kB$S-\u001d\u000b\u0004c\u0006=\u0001BB@\u0002\n\u0001\u00071\u0004C\u0004\u0002\u0014\u0001!\t!!\u0006\u0002\u001b\u0011\nW\u000e\u001d\u0013uS2$W\rJ3r)\r\t\u0018q\u0003\u0005\u0007\u007f\u0006E\u0001\u0019A\u000e\t\u000f\u0005m\u0001\u0001\"\u0011\u0002\u001e\u0005)1\r\\3beR\ta\u0006C\u0004\u0002\"\u0001!\t!a\t\u0002\u0017Q|\u0017*\\7vi\u0006\u0014G.Z\u000b\u0003\u0003K\u0001B!a\n\u0002.5\u0011\u0011\u0011\u0006\u0006\u0004\u0003W!\u0011!C5n[V$\u0018M\u00197f\u0013\r\t\u0011\u0011\u0006\u0015\t\u0003?\t\t$a\u000e\u0002<A\u0019q\"a\r\n\u0007\u0005UbA\u0001\u0006eKB\u0014XmY1uK\u0012\f#!!\u000f\u0002\u0003?Le\r\t;iSN\u0004#)\u001b;TKR\u00043m\u001c8uC&t7\u000fI1!m\u0006dW/\u001a\u0011uQ\u0006$\b%[:!cIB\u0004e\u001c:!OJ,\u0017\r^3sY\u0001\"\b.\u001a\u0011sKN,H\u000e\u001e\u0011pM\u0002\"\b.[:![\u0016$\bn\u001c3!SN\u0004\u0013M\u001c\u0011(S6lW\u000f^1cY\u0016<\u0003EQ5u'\u0016$\b\u0005\u001e5bi\u0002\u001a\b.\u0019:fg\u0002\u001aH/\u0019;fA]LG\u000f\u001b\u0011uQ&\u001c\b%\\;uC\ndW\r\t\"jiN+GO\f\u0011UQV\u001cH\u0006I5gAQDW\rI7vi\u0006\u0014G.\u001a\u0011CSR\u001cV\r\u001e\u0011jg\u0002jw\u000eZ5gS\u0016$G\u0006I5uA]LG\u000e\u001c\u0011wS>d\u0017\r^3!i\",\u0007%[7nkR\f'-\u001b7jif\u0004sN\u001a\u0011uQ\u0016\u0004#/Z:vYRt\u0013EAA\u001f\u0003\u0019\u0011d&M\u0019/m!1\u0011\u0011\t\u0001\u0005B\u0005\u000bQa\u00197p]\u0016Ds\u0001AA#\u0003\u0017\ni\u0005E\u0002\u0010\u0003\u000fJ1!!\u0013\u0007\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XM\b\u0005vuCY_\u0006R\u000e4\u0010\u001d\t\tF\u0001E\u0001\u0003'\naAQ5u'\u0016$\bcA\u0006\u0002V\u00191\u0011A\u0001E\u0001\u0003/\u001ar!!\u0016\u0002Z\u0005}s\u0004E\u0002\u0010\u00037J1!!\u0018\u0007\u0005\u0019\te.\u001f*fMB)\u0011\u0011MA475\u0011\u00111\r\u0006\u0004\u0003K\"\u0011aB4f]\u0016\u0014\u0018nY\u0005\u0005\u0003S\n\u0019GA\u0007CSR\u001cV\r\u001e$bGR|'/\u001f\u0005\bm\u0005UC\u0011AA7)\t\t\u0019\u0006\u0003\u0004;\u0003+\"\ta\u000f\u0005\t\u0003g\n)\u0006\"\u0001\u0002v\u0005Qa.Z<Ck&dG-\u001a:\u0016\u0005\u0005]\u0004#B\u0006\u0002z9Y\u0012bAA>\u0005\t9!)^5mI\u0016\u0014\b\u0002CA@\u0003+\"\u0019!!!\u0002\u0019\r\fgNQ;jY\u00124%o\\7\u0016\u0005\u0005\r\u0005cBA1\u0003\u000b[bbG\u0005\u0005\u0003\u000f\u000b\u0019G\u0001\u0007DC:\u0014U/\u001b7e\rJ|W\u000e\u0003\u0005\u0002\f\u0006UC\u0011AAG\u0003-1'o\\7CSRl\u0015m]6\u0015\u0007m\ty\t\u0003\u0004$\u0003\u0013\u0003\r!\n\u0005\b=\u0006UC\u0011AAJ)\rY\u0012Q\u0013\u0005\u0007G\u0005E\u0005\u0019A\u0013\t\u0015\u0005e\u0015QKA\u0001\n\u0013\tY*A\u0006sK\u0006$'+Z:pYZ,GCAAO!\u0011\ty*!+\u000e\u0005\u0005\u0005&\u0002BAR\u0003K\u000bA\u0001\\1oO*\u0011\u0011qU\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002,\u0006\u0005&AB(cU\u0016\u001cG\u000f")
public class BitSet
extends AbstractSet<Object>
implements scala.collection.mutable.SortedSet<Object>,
scala.collection.BitSet,
Serializable {
    public static final long serialVersionUID = 8483111450368547763L;
    private long[] elems;

    public static Object bitsetCanBuildFrom() {
        return BitSet$.MODULE$.bitsetCanBuildFrom();
    }

    public static BitSet fromBitMask(long[] lArray) {
        return BitSet$.MODULE$.fromBitMask(lArray);
    }

    public static CanBuildFrom<BitSet, Object, BitSet> canBuildFrom() {
        return BitSet$.MODULE$.canBuildFrom();
    }

    @Override
    public long[] toBitMask() {
        return BitSetLike$class.toBitMask(this);
    }

    @Override
    public int size() {
        return BitSetLike$class.size(this);
    }

    @Override
    public boolean isEmpty() {
        return BitSetLike$class.isEmpty(this);
    }

    @Override
    public Ordering<Object> ordering() {
        return BitSetLike$class.ordering(this);
    }

    @Override
    public BitSetLike rangeImpl(Option from2, Option until2) {
        return BitSetLike$class.rangeImpl(this, from2, until2);
    }

    @Override
    public Iterator<Object> iterator() {
        return BitSetLike$class.iterator(this);
    }

    @Override
    public AbstractIterator<Object> keysIteratorFrom(int start) {
        return BitSetLike$class.keysIteratorFrom(this, start);
    }

    @Override
    public <U> void foreach(Function1<Object, U> f) {
        BitSetLike$class.foreach(this, f);
    }

    @Override
    public BitSetLike $bar(scala.collection.BitSet other) {
        return BitSetLike$class.$bar(this, other);
    }

    @Override
    public BitSetLike $amp(scala.collection.BitSet other) {
        return BitSetLike$class.$amp(this, other);
    }

    @Override
    public BitSetLike $amp$tilde(scala.collection.BitSet other) {
        return BitSetLike$class.$amp$tilde(this, other);
    }

    @Override
    public BitSetLike $up(scala.collection.BitSet other) {
        return BitSetLike$class.$up(this, other);
    }

    @Override
    public boolean contains(int elem) {
        return BitSetLike$class.contains(this, elem);
    }

    @Override
    public boolean subsetOf(scala.collection.BitSet other) {
        return BitSetLike$class.subsetOf(this, other);
    }

    @Override
    public StringBuilder addString(StringBuilder sb, String start, String sep, String end) {
        return BitSetLike$class.addString(this, sb, start, sep, end);
    }

    @Override
    public String stringPrefix() {
        return BitSetLike$class.stringPrefix(this);
    }

    @Override
    public /* synthetic */ boolean scala$collection$SortedSetLike$$super$subsetOf(GenSet that) {
        return GenSetLike$class.subsetOf(this, that);
    }

    @Override
    public SortedSet keySet() {
        return SortedSetLike$class.keySet(this);
    }

    @Override
    public Object firstKey() {
        return SortedSetLike$class.firstKey(this);
    }

    @Override
    public Object lastKey() {
        return SortedSetLike$class.lastKey(this);
    }

    @Override
    public SortedSet from(Object from2) {
        return SortedSetLike$class.from(this, from2);
    }

    @Override
    public SortedSet until(Object until2) {
        return SortedSetLike$class.until(this, until2);
    }

    @Override
    public SortedSet range(Object from2, Object until2) {
        return SortedSetLike$class.range(this, from2, until2);
    }

    @Override
    public boolean subsetOf(GenSet<Object> that) {
        return SortedSetLike$class.subsetOf(this, that);
    }

    @Override
    public Iterator iteratorFrom(Object start) {
        return SortedSetLike$class.iteratorFrom(this, start);
    }

    @Override
    public int compare(Object k0, Object k1) {
        return Sorted$class.compare(this, k0, k1);
    }

    @Override
    public Sorted to(Object to2) {
        return Sorted$class.to(this, to2);
    }

    @Override
    public boolean hasAll(Iterator<Object> j) {
        return Sorted$class.hasAll(this, j);
    }

    public final long[] elems() {
        return this.elems;
    }

    public final void elems_$eq(long[] x$1) {
        this.elems = x$1;
    }

    @Override
    public BitSet empty() {
        return BitSet$.MODULE$.empty();
    }

    @Override
    public int nwords() {
        return this.elems().length;
    }

    @Override
    public long word(int idx) {
        return idx < this.nwords() ? this.elems()[idx] : 0L;
    }

    public final void updateWord(int idx, long w) {
        this.ensureCapacity(idx);
        this.elems()[idx] = w;
    }

    public final void ensureCapacity(int idx) {
        Predef$.MODULE$.require(idx < 0x2000000);
        if (idx >= this.nwords()) {
            int newlen = this.nwords();
            while (idx >= newlen) {
                int n = newlen * 2;
                Predef$ predef$ = Predef$.MODULE$;
                newlen = RichInt$.MODULE$.min$extension(n, 0x2000000);
            }
            long[] elems1 = new long[newlen];
            Array$.MODULE$.copy(this.elems(), 0, elems1, 0, this.nwords());
            this.elems_$eq(elems1);
        }
    }

    @Override
    public BitSet fromBitMaskNoCopy(long[] words) {
        return new BitSet(words);
    }

    @Override
    public boolean add(int elem) {
        boolean bl;
        Predef$.MODULE$.require(elem >= 0);
        if (this.contains(elem)) {
            bl = false;
        } else {
            int idx = elem >> 6;
            this.updateWord(idx, this.word(idx) | 1L << elem);
            bl = true;
        }
        return bl;
    }

    @Override
    public boolean remove(int elem) {
        boolean bl;
        Predef$.MODULE$.require(elem >= 0);
        if (this.contains(elem)) {
            int idx = elem >> 6;
            this.updateWord(idx, this.word(idx) & (1L << elem ^ 0xFFFFFFFFFFFFFFFFL));
            bl = true;
        } else {
            bl = false;
        }
        return bl;
    }

    public BitSet $plus$eq(int elem) {
        this.add(elem);
        return this;
    }

    public BitSet $minus$eq(int elem) {
        this.remove(elem);
        return this;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public BitSet $bar$eq(BitSet other) {
        this.ensureCapacity(other.nwords() - 1);
        Predef$ predef$ = Predef$.MODULE$;
        int n = other.nwords();
        Range$ range$ = Range$.MODULE$;
        Range range2 = new Range(0, n, 1);
        if (range2.isEmpty()) return this;
        int i1 = range2.start();
        while (true) {
            this.elems()[i1] = this.elems()[i1] | other.word(i1);
            if (i1 == range2.lastElement()) {
                return this;
            }
            i1 += range2.step();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public BitSet $amp$eq(BitSet other) {
        Predef$ predef$ = Predef$.MODULE$;
        int n = this.nwords();
        Range$ range$ = Range$.MODULE$;
        Range range2 = new Range(0, n, 1);
        if (range2.isEmpty()) return this;
        int i1 = range2.start();
        while (true) {
            this.elems()[i1] = this.elems()[i1] & other.word(i1);
            if (i1 == range2.lastElement()) {
                return this;
            }
            i1 += range2.step();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public BitSet $up$eq(BitSet other) {
        this.ensureCapacity(other.nwords() - 1);
        Predef$ predef$ = Predef$.MODULE$;
        int n = other.nwords();
        Range$ range$ = Range$.MODULE$;
        Range range2 = new Range(0, n, 1);
        if (range2.isEmpty()) return this;
        int i1 = range2.start();
        while (true) {
            this.elems()[i1] = this.elems()[i1] ^ other.word(i1);
            if (i1 == range2.lastElement()) {
                return this;
            }
            i1 += range2.step();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public BitSet $amp$tilde$eq(BitSet other) {
        this.ensureCapacity(other.nwords() - 1);
        Predef$ predef$ = Predef$.MODULE$;
        int n = other.nwords();
        Range$ range$ = Range$.MODULE$;
        Range range2 = new Range(0, n, 1);
        if (range2.isEmpty()) return this;
        int i1 = range2.start();
        while (true) {
            this.elems()[i1] = this.elems()[i1] & (other.word(i1) ^ 0xFFFFFFFFFFFFFFFFL);
            if (i1 == range2.lastElement()) {
                return this;
            }
            i1 += range2.step();
        }
    }

    @Override
    public void clear() {
        this.elems_$eq(new long[this.elems().length]);
    }

    public scala.collection.immutable.BitSet toImmutable() {
        return scala.collection.immutable.BitSet$.MODULE$.fromBitMaskNoCopy(this.elems());
    }

    @Override
    public BitSet clone() {
        long[] elems1 = new long[this.elems().length];
        Array$.MODULE$.copy(this.elems(), 0, elems1, 0, this.elems().length);
        return new BitSet(elems1);
    }

    public BitSet(long[] elems) {
        this.elems = elems;
        Sorted$class.$init$(this);
        SortedSetLike$class.$init$(this);
        scala.collection.SortedSet$class.$init$(this);
        SortedSet$class.$init$(this);
        BitSetLike$class.$init$(this);
        BitSet$class.$init$(this);
    }

    public BitSet(int initSize) {
        int n = initSize + 63 >> 6;
        Predef$ predef$ = Predef$.MODULE$;
        this(new long[RichInt$.MODULE$.max$extension(n, 1)]);
    }

    public BitSet() {
        this(0);
    }
}

