/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import java.util.Locale;
import java.util.regex.PatternSyntaxException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Tuple2;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.GenSeqLike$class;
import scala.collection.GenTraversableOnce;
import scala.collection.IndexedSeqLike$class;
import scala.collection.IndexedSeqOptimized$class;
import scala.collection.Iterable;
import scala.collection.IterableLike$class;
import scala.collection.Iterator;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.SeqLike$class;
import scala.collection.SeqView;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.StringLike;
import scala.collection.immutable.StringLike$class;
import scala.collection.immutable.StringOps$;
import scala.collection.immutable.Vector;
import scala.collection.immutable.WrappedString;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParSeq;
import scala.math.Numeric;
import scala.math.Ordered$class;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;
import scala.util.matching.Regex;

@ScalaSignature(bytes="\u0006\u0001\u0005Ub\u0001B\u0001\u0003\u0005%\u0011\u0011b\u0015;sS:<w\n]:\u000b\u0005\r!\u0011!C5n[V$\u0018M\u00197f\u0015\t)a!\u0001\u0006d_2dWm\u0019;j_:T\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001'\r\u0001!B\u0004\t\u0003\u00171i\u0011AB\u0005\u0003\u001b\u0019\u0011a!\u00118z-\u0006d\u0007cA\b\u0011%5\t!!\u0003\u0002\u0012\u0005\tQ1\u000b\u001e:j]\u001ed\u0015n[3\u0011\u0005M1bBA\u0006\u0015\u0013\t)b!\u0001\u0004Qe\u0016$WMZ\u0005\u0003/a\u0011aa\u0015;sS:<'BA\u000b\u0007\u0011!Q\u0002A!b\u0001\n\u0003Z\u0012\u0001\u0002:faJ,\u0012A\u0005\u0005\t;\u0001\u0011\t\u0011)A\u0005%\u0005)!/\u001a9sA!)q\u0004\u0001C\u0001A\u00051A(\u001b8jiz\"\"!\t\u0012\u0011\u0005=\u0001\u0001\"\u0002\u000e\u001f\u0001\u0004\u0011\u0002B\u0002\u0013\u0001A\u0013ES%\u0001\buQ&\u001c8i\u001c7mK\u000e$\u0018n\u001c8\u0016\u0003\u0019\u0002\"aD\u0014\n\u0005!\u0012!!D,sCB\u0004X\rZ*ue&tw\r\u0003\u0004+\u0001\u0001&\tfK\u0001\ri>\u001cu\u000e\u001c7fGRLwN\u001c\u000b\u0003M1BQAG\u0015A\u0002IAaA\f\u0001!\n#z\u0013A\u00038fo\n+\u0018\u000e\u001c3feV\t\u0001\u0007\u0005\u00022i5\t!G\u0003\u00024\t\u00059Q.\u001e;bE2,\u0017BA\u001b3\u00055\u0019FO]5oO\n+\u0018\u000e\u001c3fe\")q\u0007\u0001C!q\u0005)\u0011\r\u001d9msR\u0011\u0011\b\u0010\t\u0003\u0017iJ!a\u000f\u0004\u0003\t\rC\u0017M\u001d\u0005\u0006{Y\u0002\rAP\u0001\u0006S:$W\r\u001f\t\u0003\u0017}J!\u0001\u0011\u0004\u0003\u0007%sG\u000fC\u0003C\u0001\u0011\u00053)A\u0003tY&\u001cW\rF\u0002\u0013\t\u001aCQ!R!A\u0002y\nAA\u001a:p[\")q)\u0011a\u0001}\u0005)QO\u001c;jY\")\u0011\n\u0001C!\u0015\u0006AAo\\*ue&tw\rF\u0001\u0013\u0011\u0015a\u0005\u0001\"\u0011N\u0003\u0019aWM\\4uQV\ta\bC\u0003P\u0001\u0011\u0005Q%A\u0002tKFDq!\u0015\u0001\u0002\u0002\u0013\u0005#+\u0001\u0005iCND7i\u001c3f)\u0005q\u0004b\u0002+\u0001\u0003\u0003%\t%V\u0001\u0007KF,\u0018\r\\:\u0015\u0005YK\u0006CA\u0006X\u0013\tAfAA\u0004C_>dW-\u00198\t\u000fi\u001b\u0016\u0011!a\u00017\u0006\u0019\u0001\u0010J\u0019\u0011\u0005-a\u0016BA/\u0007\u0005\r\te._\u0004\b?\n\t\t\u0011#\u0001a\u0003%\u0019FO]5oO>\u00038\u000f\u0005\u0002\u0010C\u001a9\u0011AAA\u0001\u0012\u0003\u00117CA1d!\tYA-\u0003\u0002f\r\t1\u0011I\\=SK\u001aDQaH1\u0005\u0002\u001d$\u0012\u0001\u0019\u0005\u0006S\u0006$)A[\u0001\u0019i\"L7oQ8mY\u0016\u001cG/[8oI\u0015DH/\u001a8tS>tGC\u0001\u0014l\u0011\u0015a\u0007\u000e1\u0001\"\u0003\u0015!C\u000f[5t\u0011\u0015q\u0017\r\"\u0002p\u0003Y!xnQ8mY\u0016\u001cG/[8oI\u0015DH/\u001a8tS>tGC\u00019s)\t1\u0013\u000fC\u0003\u001b[\u0002\u0007!\u0003C\u0003m[\u0002\u0007\u0011\u0005C\u0003uC\u0012\u0015Q/\u0001\u000boK^\u0014U/\u001b7eKJ$S\r\u001f;f]NLwN\u001c\u000b\u0003aYDQ\u0001\\:A\u0002\u0005BQ\u0001_1\u0005\u0006e\fq\"\u00199qYf$S\r\u001f;f]NLwN\u001c\u000b\u0003ur$\"!O>\t\u000bu:\b\u0019\u0001 \t\u000b1<\b\u0019A\u0011\t\u000by\fGQA@\u0002\u001fMd\u0017nY3%Kb$XM\\:j_:$B!!\u0001\u0002\bQ)!#a\u0001\u0002\u0006!)Q) a\u0001}!)q) a\u0001}!)A. a\u0001C!9\u00111B1\u0005\u0006\u00055\u0011A\u0005;p'R\u0014\u0018N\\4%Kb$XM\\:j_:$2ASA\b\u0011\u0019a\u0017\u0011\u0002a\u0001C!9\u00111C1\u0005\u0006\u0005U\u0011\u0001\u00057f]\u001e$\b\u000eJ3yi\u0016t7/[8o)\rq\u0014q\u0003\u0005\u0007Y\u0006E\u0001\u0019A\u0011\t\u000f\u0005m\u0011\r\"\u0002\u0002\u001e\u0005i1/Z9%Kb$XM\\:j_:$2AJA\u0010\u0011\u0019a\u0017\u0011\u0004a\u0001C!I\u00111E1\u0002\u0002\u0013\u0015\u0011QE\u0001\u0013Q\u0006\u001c\bnQ8eK\u0012*\u0007\u0010^3og&|g\u000eF\u0002S\u0003OAa\u0001\\A\u0011\u0001\u0004\t\u0003\"CA\u0016C\u0006\u0005IQAA\u0017\u0003A)\u0017/^1mg\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u00020\u0005MBc\u0001,\u00022!A!,!\u000b\u0002\u0002\u0003\u00071\f\u0003\u0004m\u0003S\u0001\r!\t")
public final class StringOps
implements StringLike<String> {
    private final String repr;

    public static boolean equals$extension(String string2, Object object) {
        return StringOps$.MODULE$.equals$extension(string2, object);
    }

    public static int hashCode$extension(String string2) {
        return StringOps$.MODULE$.hashCode$extension(string2);
    }

    public static WrappedString seq$extension(String string2) {
        return StringOps$.MODULE$.seq$extension(string2);
    }

    public static int length$extension(String string2) {
        return StringOps$.MODULE$.length$extension(string2);
    }

    public static String toString$extension(String string2) {
        return StringOps$.MODULE$.toString$extension(string2);
    }

    public static String slice$extension(String string2, int n, int n2) {
        return StringOps$.MODULE$.slice$extension(string2, n, n2);
    }

    public static char apply$extension(String string2, int n) {
        return StringOps$.MODULE$.apply$extension(string2, n);
    }

    public static StringBuilder newBuilder$extension(String string2) {
        return StringOps$.MODULE$.newBuilder$extension(string2);
    }

    public static WrappedString toCollection$extension(String string2, String string3) {
        return StringOps$.MODULE$.toCollection$extension(string2, string3);
    }

    public static WrappedString thisCollection$extension(String string2) {
        return StringOps$.MODULE$.thisCollection$extension(string2);
    }

    @Override
    public String mkString() {
        return StringLike$class.mkString(this);
    }

    @Override
    public String $times(int n) {
        return StringLike$class.$times(this, n);
    }

    @Override
    public int compare(String other) {
        return StringLike$class.compare(this, other);
    }

    @Override
    public String stripLineEnd() {
        return StringLike$class.stripLineEnd(this);
    }

    @Override
    public Iterator<String> linesWithSeparators() {
        return StringLike$class.linesWithSeparators(this);
    }

    @Override
    public Iterator<String> lines() {
        return StringLike$class.lines(this);
    }

    @Override
    public Iterator<String> linesIterator() {
        return StringLike$class.linesIterator(this);
    }

    @Override
    public String capitalize() {
        return StringLike$class.capitalize(this);
    }

    @Override
    public String stripPrefix(String prefix) {
        return StringLike$class.stripPrefix(this, prefix);
    }

    @Override
    public String stripSuffix(String suffix) {
        return StringLike$class.stripSuffix(this, suffix);
    }

    @Override
    public String replaceAllLiterally(String literal, String replacement) {
        return StringLike$class.replaceAllLiterally(this, literal, replacement);
    }

    @Override
    public String stripMargin(char marginChar) {
        return StringLike$class.stripMargin(this, marginChar);
    }

    @Override
    public String stripMargin() {
        return StringLike$class.stripMargin(this);
    }

    @Override
    public String[] split(char separator) {
        return StringLike$class.split((StringLike)this, separator);
    }

    @Override
    public String[] split(char[] separators) throws PatternSyntaxException {
        return StringLike$class.split((StringLike)this, separators);
    }

    @Override
    public Regex r() {
        return StringLike$class.r(this);
    }

    @Override
    public Regex r(Seq<String> groupNames) {
        return StringLike$class.r(this, groupNames);
    }

    @Override
    public boolean toBoolean() {
        return StringLike$class.toBoolean(this);
    }

    @Override
    public byte toByte() {
        return StringLike$class.toByte(this);
    }

    @Override
    public short toShort() {
        return StringLike$class.toShort(this);
    }

    @Override
    public int toInt() {
        return StringLike$class.toInt(this);
    }

    @Override
    public long toLong() {
        return StringLike$class.toLong(this);
    }

    @Override
    public float toFloat() {
        return StringLike$class.toFloat(this);
    }

    @Override
    public double toDouble() {
        return StringLike$class.toDouble(this);
    }

    @Override
    public <B> Object toArray(ClassTag<B> evidence$1) {
        return StringLike$class.toArray(this, evidence$1);
    }

    @Override
    public String format(Seq<Object> args) {
        return StringLike$class.format(this, args);
    }

    @Override
    public String formatLocal(Locale l, Seq<Object> args) {
        return StringLike$class.formatLocal(this, l, args);
    }

    @Override
    public boolean $less(Object that) {
        return Ordered$class.$less(this, that);
    }

    @Override
    public boolean $greater(Object that) {
        return Ordered$class.$greater(this, that);
    }

    @Override
    public boolean $less$eq(Object that) {
        return Ordered$class.$less$eq(this, that);
    }

    @Override
    public boolean $greater$eq(Object that) {
        return Ordered$class.$greater$eq(this, that);
    }

    @Override
    public int compareTo(Object that) {
        return Ordered$class.compareTo(this, that);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$reduceLeft(Function2 op) {
        return TraversableOnce$class.reduceLeft(this, op);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$reduceRight(Function2 op) {
        return IterableLike$class.reduceRight(this, op);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$zip(GenIterable that, CanBuildFrom bf) {
        return IterableLike$class.zip(this, that, bf);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$head() {
        return IterableLike$class.head(this);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$tail() {
        return TraversableLike$class.tail(this);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$last() {
        return TraversableLike$class.last(this);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$init() {
        return TraversableLike$class.init(this);
    }

    @Override
    public /* synthetic */ boolean scala$collection$IndexedSeqOptimized$$super$sameElements(GenIterable that) {
        return IterableLike$class.sameElements(this, that);
    }

    @Override
    public /* synthetic */ boolean scala$collection$IndexedSeqOptimized$$super$endsWith(GenSeq that) {
        return SeqLike$class.endsWith(this, that);
    }

    @Override
    public boolean isEmpty() {
        return IndexedSeqOptimized$class.isEmpty(this);
    }

    @Override
    public <U> void foreach(Function1<Object, U> f) {
        IndexedSeqOptimized$class.foreach(this, f);
    }

    @Override
    public boolean forall(Function1<Object, Object> p) {
        return IndexedSeqOptimized$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<Object, Object> p) {
        return IndexedSeqOptimized$class.exists(this, p);
    }

    @Override
    public Option<Object> find(Function1<Object, Object> p) {
        return IndexedSeqOptimized$class.find(this, p);
    }

    @Override
    public <B> B foldLeft(B z, Function2<B, Object, B> op) {
        return (B)IndexedSeqOptimized$class.foldLeft(this, z, op);
    }

    @Override
    public <B> B foldRight(B z, Function2<Object, B, B> op) {
        return (B)IndexedSeqOptimized$class.foldRight(this, z, op);
    }

    @Override
    public <B> B reduceLeft(Function2<B, Object, B> op) {
        return (B)IndexedSeqOptimized$class.reduceLeft(this, op);
    }

    @Override
    public <B> B reduceRight(Function2<Object, B, B> op) {
        return (B)IndexedSeqOptimized$class.reduceRight(this, op);
    }

    @Override
    public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<String, Tuple2<A1, B>, That> bf) {
        return (That)IndexedSeqOptimized$class.zip(this, that, bf);
    }

    @Override
    public <A1, That> That zipWithIndex(CanBuildFrom<String, Tuple2<A1, Object>, That> bf) {
        return (That)IndexedSeqOptimized$class.zipWithIndex(this, bf);
    }

    @Override
    public Object head() {
        return IndexedSeqOptimized$class.head(this);
    }

    @Override
    public Object tail() {
        return IndexedSeqOptimized$class.tail(this);
    }

    @Override
    public Object last() {
        return IndexedSeqOptimized$class.last(this);
    }

    @Override
    public Object init() {
        return IndexedSeqOptimized$class.init(this);
    }

    @Override
    public Object take(int n) {
        return IndexedSeqOptimized$class.take(this, n);
    }

    @Override
    public Object drop(int n) {
        return IndexedSeqOptimized$class.drop(this, n);
    }

    @Override
    public Object takeRight(int n) {
        return IndexedSeqOptimized$class.takeRight(this, n);
    }

    @Override
    public Object dropRight(int n) {
        return IndexedSeqOptimized$class.dropRight(this, n);
    }

    @Override
    public Tuple2<String, String> splitAt(int n) {
        return IndexedSeqOptimized$class.splitAt(this, n);
    }

    @Override
    public Object takeWhile(Function1 p) {
        return IndexedSeqOptimized$class.takeWhile(this, p);
    }

    @Override
    public Object dropWhile(Function1 p) {
        return IndexedSeqOptimized$class.dropWhile(this, p);
    }

    @Override
    public Tuple2<String, String> span(Function1<Object, Object> p) {
        return IndexedSeqOptimized$class.span(this, p);
    }

    @Override
    public <B> boolean sameElements(GenIterable<B> that) {
        return IndexedSeqOptimized$class.sameElements(this, that);
    }

    @Override
    public <B> void copyToArray(Object xs, int start, int len) {
        IndexedSeqOptimized$class.copyToArray(this, xs, start, len);
    }

    @Override
    public int lengthCompare(int len) {
        return IndexedSeqOptimized$class.lengthCompare(this, len);
    }

    @Override
    public int segmentLength(Function1<Object, Object> p, int from2) {
        return IndexedSeqOptimized$class.segmentLength(this, p, from2);
    }

    @Override
    public int indexWhere(Function1<Object, Object> p, int from2) {
        return IndexedSeqOptimized$class.indexWhere(this, p, from2);
    }

    @Override
    public int lastIndexWhere(Function1<Object, Object> p, int end) {
        return IndexedSeqOptimized$class.lastIndexWhere(this, p, end);
    }

    @Override
    public Object reverse() {
        return IndexedSeqOptimized$class.reverse(this);
    }

    @Override
    public Iterator<Object> reverseIterator() {
        return IndexedSeqOptimized$class.reverseIterator(this);
    }

    @Override
    public <B> boolean startsWith(GenSeq<B> that, int offset) {
        return IndexedSeqOptimized$class.startsWith(this, that, offset);
    }

    @Override
    public <B> boolean endsWith(GenSeq<B> that) {
        return IndexedSeqOptimized$class.endsWith(this, that);
    }

    @Override
    public Iterator<Object> iterator() {
        return IndexedSeqLike$class.iterator(this);
    }

    @Override
    public <A1> Buffer<A1> toBuffer() {
        return IndexedSeqLike$class.toBuffer(this);
    }

    @Override
    public Combiner<Object, ParSeq<Object>> parCombiner() {
        return SeqLike$class.parCombiner(this);
    }

    @Override
    public int size() {
        return SeqLike$class.size(this);
    }

    @Override
    public Iterator<String> permutations() {
        return SeqLike$class.permutations(this);
    }

    @Override
    public Iterator<String> combinations(int n) {
        return SeqLike$class.combinations(this, n);
    }

    @Override
    public <B, That> That reverseMap(Function1<Object, B> f, CanBuildFrom<String, B, That> bf) {
        return (That)SeqLike$class.reverseMap(this, f, bf);
    }

    @Override
    public <B> int indexOfSlice(GenSeq<B> that) {
        return SeqLike$class.indexOfSlice(this, that);
    }

    @Override
    public <B> int indexOfSlice(GenSeq<B> that, int from2) {
        return SeqLike$class.indexOfSlice(this, that, from2);
    }

    @Override
    public <B> int lastIndexOfSlice(GenSeq<B> that) {
        return SeqLike$class.lastIndexOfSlice(this, that);
    }

    @Override
    public <B> int lastIndexOfSlice(GenSeq<B> that, int end) {
        return SeqLike$class.lastIndexOfSlice(this, that, end);
    }

    @Override
    public <B> boolean containsSlice(GenSeq<B> that) {
        return SeqLike$class.containsSlice(this, that);
    }

    @Override
    public <A1> boolean contains(A1 elem) {
        return SeqLike$class.contains(this, elem);
    }

    @Override
    public <B, That> That union(GenSeq<B> that, CanBuildFrom<String, B, That> bf) {
        return (That)SeqLike$class.union(this, that, bf);
    }

    @Override
    public Object diff(GenSeq that) {
        return SeqLike$class.diff(this, that);
    }

    @Override
    public Object intersect(GenSeq that) {
        return SeqLike$class.intersect(this, that);
    }

    @Override
    public Object distinct() {
        return SeqLike$class.distinct(this);
    }

    @Override
    public <B, That> That patch(int from2, GenSeq<B> patch2, int replaced, CanBuildFrom<String, B, That> bf) {
        return (That)SeqLike$class.patch(this, from2, patch2, replaced, bf);
    }

    @Override
    public <B, That> That updated(int index, B elem, CanBuildFrom<String, B, That> bf) {
        return (That)SeqLike$class.updated(this, index, elem, bf);
    }

    @Override
    public <B, That> That $plus$colon(B elem, CanBuildFrom<String, B, That> bf) {
        return (That)SeqLike$class.$plus$colon(this, elem, bf);
    }

    @Override
    public <B, That> That $colon$plus(B elem, CanBuildFrom<String, B, That> bf) {
        return (That)SeqLike$class.$colon$plus(this, elem, bf);
    }

    @Override
    public <B, That> That padTo(int len, B elem, CanBuildFrom<String, B, That> bf) {
        return (That)SeqLike$class.padTo(this, len, elem, bf);
    }

    @Override
    public <B> boolean corresponds(GenSeq<B> that, Function2<Object, B, Object> p) {
        return SeqLike$class.corresponds(this, that, p);
    }

    @Override
    public Object sortWith(Function2 lt) {
        return SeqLike$class.sortWith(this, lt);
    }

    @Override
    public Object sortBy(Function1 f, Ordering ord) {
        return SeqLike$class.sortBy(this, f, ord);
    }

    @Override
    public Object sorted(Ordering ord) {
        return SeqLike$class.sorted(this, ord);
    }

    @Override
    public Seq<Object> toSeq() {
        return SeqLike$class.toSeq(this);
    }

    @Override
    public Range indices() {
        return SeqLike$class.indices(this);
    }

    @Override
    public Object view() {
        return SeqLike$class.view(this);
    }

    @Override
    public SeqView<Object, String> view(int from2, int until2) {
        return SeqLike$class.view(this, from2, until2);
    }

    @Override
    public boolean isDefinedAt(int idx) {
        return GenSeqLike$class.isDefinedAt(this, idx);
    }

    @Override
    public int prefixLength(Function1<Object, Object> p) {
        return GenSeqLike$class.prefixLength(this, p);
    }

    @Override
    public int indexWhere(Function1<Object, Object> p) {
        return GenSeqLike$class.indexWhere(this, p);
    }

    @Override
    public <B> int indexOf(B elem) {
        return GenSeqLike$class.indexOf(this, elem);
    }

    @Override
    public <B> int indexOf(B elem, int from2) {
        return GenSeqLike$class.indexOf(this, elem, from2);
    }

    @Override
    public <B> int lastIndexOf(B elem) {
        return GenSeqLike$class.lastIndexOf(this, elem);
    }

    @Override
    public <B> int lastIndexOf(B elem, int end) {
        return GenSeqLike$class.lastIndexOf(this, elem, end);
    }

    @Override
    public int lastIndexWhere(Function1<Object, Object> p) {
        return GenSeqLike$class.lastIndexWhere(this, p);
    }

    @Override
    public <B> boolean startsWith(GenSeq<B> that) {
        return GenSeqLike$class.startsWith(this, that);
    }

    @Override
    public Iterable<Object> toIterable() {
        return IterableLike$class.toIterable(this);
    }

    @Override
    public Iterator<Object> toIterator() {
        return IterableLike$class.toIterator(this);
    }

    @Override
    public Iterator<String> grouped(int size2) {
        return IterableLike$class.grouped(this, size2);
    }

    @Override
    public Iterator<String> sliding(int size2) {
        return IterableLike$class.sliding(this, size2);
    }

    @Override
    public Iterator<String> sliding(int size2, int step) {
        return IterableLike$class.sliding(this, size2, step);
    }

    @Override
    public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<String, Tuple2<A1, B>, That> bf) {
        return (That)IterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
    }

    @Override
    public Stream<Object> toStream() {
        return IterableLike$class.toStream(this);
    }

    @Override
    public boolean canEqual(Object that) {
        return IterableLike$class.canEqual(this, that);
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
    public <B, That> That $plus$plus(GenTraversableOnce<B> that, CanBuildFrom<String, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus(this, that, bf);
    }

    @Override
    public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<String, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
    }

    @Override
    public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<String, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
    }

    @Override
    public <B, That> That map(Function1<Object, B> f, CanBuildFrom<String, B, That> bf) {
        return (That)TraversableLike$class.map(this, f, bf);
    }

    @Override
    public <B, That> That flatMap(Function1<Object, GenTraversableOnce<B>> f, CanBuildFrom<String, B, That> bf) {
        return (That)TraversableLike$class.flatMap(this, f, bf);
    }

    @Override
    public Object filter(Function1 p) {
        return TraversableLike$class.filter(this, p);
    }

    @Override
    public Object filterNot(Function1 p) {
        return TraversableLike$class.filterNot(this, p);
    }

    @Override
    public <B, That> That collect(PartialFunction<Object, B> pf, CanBuildFrom<String, B, That> bf) {
        return (That)TraversableLike$class.collect(this, pf, bf);
    }

    @Override
    public Tuple2<String, String> partition(Function1<Object, Object> p) {
        return TraversableLike$class.partition(this, p);
    }

    @Override
    public <K> Map<K, String> groupBy(Function1<Object, K> f) {
        return TraversableLike$class.groupBy(this, f);
    }

    @Override
    public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<String, B, That> cbf) {
        return (That)TraversableLike$class.scan(this, z, op, cbf);
    }

    @Override
    public <B, That> That scanLeft(B z, Function2<B, Object, B> op, CanBuildFrom<String, B, That> bf) {
        return (That)TraversableLike$class.scanLeft(this, z, op, bf);
    }

    @Override
    public <B, That> That scanRight(B z, Function2<Object, B, B> op, CanBuildFrom<String, B, That> bf) {
        return (That)TraversableLike$class.scanRight(this, z, op, bf);
    }

    @Override
    public Option<Object> headOption() {
        return TraversableLike$class.headOption(this);
    }

    @Override
    public Option<Object> lastOption() {
        return TraversableLike$class.lastOption(this);
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
    public Iterator<String> tails() {
        return TraversableLike$class.tails(this);
    }

    @Override
    public Iterator<String> inits() {
        return TraversableLike$class.inits(this);
    }

    @Override
    public Traversable<Object> toTraversable() {
        return TraversableLike$class.toTraversable(this);
    }

    @Override
    public <Col> Col to(CanBuildFrom<Nothing$, Object, Col> cbf) {
        return (Col)TraversableLike$class.to(this, cbf);
    }

    @Override
    public String stringPrefix() {
        return TraversableLike$class.stringPrefix(this);
    }

    @Override
    public FilterMonadic<Object, String> withFilter(Function1<Object, Object> p) {
        return TraversableLike$class.withFilter(this, p);
    }

    @Override
    public Parallel par() {
        return Parallelizable$class.par(this);
    }

    @Override
    public List<Object> reversed() {
        return TraversableOnce$class.reversed(this);
    }

    @Override
    public boolean nonEmpty() {
        return TraversableOnce$class.nonEmpty(this);
    }

    @Override
    public int count(Function1<Object, Object> p) {
        return TraversableOnce$class.count(this, p);
    }

    @Override
    public <B> Option<B> collectFirst(PartialFunction<Object, B> pf) {
        return TraversableOnce$class.collectFirst(this, pf);
    }

    @Override
    public <B> B $div$colon(B z, Function2<B, Object, B> op) {
        return (B)TraversableOnce$class.$div$colon(this, z, op);
    }

    @Override
    public <B> B $colon$bslash(B z, Function2<Object, B, B> op) {
        return (B)TraversableOnce$class.$colon$bslash(this, z, op);
    }

    @Override
    public <B> Option<B> reduceLeftOption(Function2<B, Object, B> op) {
        return TraversableOnce$class.reduceLeftOption(this, op);
    }

    @Override
    public <B> Option<B> reduceRightOption(Function2<Object, B, B> op) {
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
    public <B> B aggregate(Function0<B> z, Function2<B, Object, B> seqop, Function2<B, B, B> combop) {
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
    public List<Object> toList() {
        return TraversableOnce$class.toList(this);
    }

    @Override
    public IndexedSeq<Object> toIndexedSeq() {
        return TraversableOnce$class.toIndexedSeq(this);
    }

    @Override
    public <B> Set<B> toSet() {
        return TraversableOnce$class.toSet(this);
    }

    @Override
    public Vector<Object> toVector() {
        return TraversableOnce$class.toVector(this);
    }

    @Override
    public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Object, Tuple2<T, U>> ev) {
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

    @Override
    public String repr() {
        return this.repr;
    }

    public WrappedString thisCollection() {
        return StringOps$.MODULE$.thisCollection$extension(this.repr());
    }

    public WrappedString toCollection(String repr) {
        return StringOps$.MODULE$.toCollection$extension(this.repr(), repr);
    }

    public StringBuilder newBuilder() {
        return StringOps$.MODULE$.newBuilder$extension(this.repr());
    }

    @Override
    public char apply(int index) {
        return StringOps$.MODULE$.apply$extension(this.repr(), index);
    }

    @Override
    public String slice(int from2, int until2) {
        return StringOps$.MODULE$.slice$extension(this.repr(), from2, until2);
    }

    @Override
    public String toString() {
        return StringOps$.MODULE$.toString$extension(this.repr());
    }

    @Override
    public int length() {
        return StringOps$.MODULE$.length$extension(this.repr());
    }

    public WrappedString seq() {
        return StringOps$.MODULE$.seq$extension(this.repr());
    }

    @Override
    public int hashCode() {
        return StringOps$.MODULE$.hashCode$extension(this.repr());
    }

    @Override
    public boolean equals(Object x$1) {
        return StringOps$.MODULE$.equals$extension(this.repr(), x$1);
    }

    public StringOps(String repr) {
        this.repr = repr;
        TraversableOnce$class.$init$(this);
        Parallelizable$class.$init$(this);
        TraversableLike$class.$init$(this);
        IterableLike$class.$init$(this);
        GenSeqLike$class.$init$(this);
        SeqLike$class.$init$(this);
        IndexedSeqLike$class.$init$(this);
        IndexedSeqOptimized$class.$init$(this);
        Ordered$class.$init$(this);
        StringLike$class.$init$(this);
    }
}

