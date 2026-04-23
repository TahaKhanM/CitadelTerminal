/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import java.util.Locale;
import java.util.regex.PatternSyntaxException;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Predef$;
import scala.Tuple2;
import scala.collection.AbstractSeq;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.IndexedSeq$class;
import scala.collection.IndexedSeqLike$class;
import scala.collection.IndexedSeqOptimized$class;
import scala.collection.IterableLike$class;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.SeqLike$class;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.Seq$class;
import scala.collection.immutable.StringLike;
import scala.collection.immutable.StringLike$class;
import scala.collection.immutable.Traversable$class;
import scala.collection.immutable.WrappedString$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParSeq;
import scala.math.Ordered$class;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.util.matching.Regex;

@ScalaSignature(bytes="\u0006\u0001\u001d4A!\u0001\u0002\u0001\u0013\tiqK]1qa\u0016$7\u000b\u001e:j]\u001eT!a\u0001\u0003\u0002\u0013%lW.\u001e;bE2,'BA\u0003\u0007\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u000f\u0005)1oY1mC\u000e\u00011\u0003\u0002\u0001\u000b%Y\u00012a\u0003\u0007\u000f\u001b\u0005!\u0011BA\u0007\u0005\u0005-\t%m\u001d;sC\u000e$8+Z9\u0011\u0005=\u0001R\"\u0001\u0004\n\u0005E1!\u0001B\"iCJ\u00042a\u0005\u000b\u000f\u001b\u0005\u0011\u0011BA\u000b\u0003\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\t\u0004']I\u0012B\u0001\r\u0003\u0005)\u0019FO]5oO2K7.\u001a\t\u0003'\u0001A\u0001b\u0007\u0001\u0003\u0006\u0004%\t\u0001H\u0001\u0005g\u0016dg-F\u0001\u001e!\tq\u0012E\u0004\u0002\u0010?%\u0011\u0001EB\u0001\u0007!J,G-\u001a4\n\u0005\t\u001a#AB*ue&twM\u0003\u0002!\r!AQ\u0005\u0001B\u0001B\u0003%Q$A\u0003tK24\u0007\u0005C\u0003(\u0001\u0011\u0005\u0001&\u0001\u0004=S:LGO\u0010\u000b\u00033%BQa\u0007\u0014A\u0002uAaa\u000b\u0001!\n#b\u0013A\u0004;iSN\u001cu\u000e\u001c7fGRLwN\\\u000b\u00023!1a\u0006\u0001Q\u0005R=\nA\u0002^8D_2dWm\u0019;j_:$\"!\u0007\u0019\t\u000bEj\u0003\u0019A\r\u0002\tI,\u0007O\u001d\u0005\u0007g\u0001\u0001K\u0011\u000b\u001b\u0002\u00159,wOQ;jY\u0012,'/F\u00016!\u00111\u0014HD\r\u000e\u0003]R!\u0001\u000f\u0003\u0002\u000f5,H/\u00192mK&\u0011!h\u000e\u0002\b\u0005VLG\u000eZ3s\u0011\u0015a\u0004\u0001\"\u0011>\u0003\u0015\u0019H.[2f)\rIbh\u0011\u0005\u0006\u007fm\u0002\r\u0001Q\u0001\u0005MJ|W\u000e\u0005\u0002\u0010\u0003&\u0011!I\u0002\u0002\u0004\u0013:$\b\"\u0002#<\u0001\u0004\u0001\u0015!B;oi&d\u0007\"\u0002$\u0001\t\u0003:\u0015A\u00027f]\u001e$\b.F\u0001A\u0011\u0015I\u0005\u0001\"\u0011K\u0003!!xn\u0015;sS:<G#A\u000f)\t\u0001au*\u0015\t\u0003\u001f5K!A\u0014\u0004\u0003+\u0011,\u0007O]3dCR,G-\u00138iKJLG/\u00198dK\u0006\n\u0001+A\u0019J]\",'/\u001b;!MJ|W\u000eI*ue&tw\rT5lK\u0002Jgn\u001d;fC\u0012\u0004sN\u001a\u0011Xe\u0006\u0004\b/\u001a3TiJLgn\u001a\u0018\"\u0003I\u000baA\r\u00182c9\u0002t!\u0002+\u0003\u0011\u0003)\u0016!D,sCB\u0004X\rZ*ue&tw\r\u0005\u0002\u0014-\u001a)\u0011A\u0001E\u0001/N\u0011a\u000b\u0017\t\u0003\u001feK!A\u0017\u0004\u0003\r\u0005s\u0017PU3g\u0011\u00159c\u000b\"\u0001])\u0005)\u0006\"\u00020W\t\u0007y\u0016\u0001D2b]\n+\u0018\u000e\u001c3Ge>lW#\u00011\u0011\u000b\u0005$\u0017DD\r\u000e\u0003\tT!a\u0019\u0003\u0002\u000f\u001d,g.\u001a:jG&\u0011QM\u0019\u0002\r\u0007\u0006t')^5mI\u001a\u0013x.\u001c\u0005\u0006gY#\t\u0001\u000e")
public class WrappedString
extends AbstractSeq<Object>
implements IndexedSeq<Object>,
StringLike<WrappedString> {
    private final String self;

    public static CanBuildFrom<WrappedString, Object, WrappedString> canBuildFrom() {
        return WrappedString$.MODULE$.canBuildFrom();
    }

    @Override
    public char apply(int n) {
        return StringLike$class.apply(this, n);
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
    public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<WrappedString, Tuple2<A1, B>, That> bf) {
        return (That)IndexedSeqOptimized$class.zip(this, that, bf);
    }

    @Override
    public <A1, That> That zipWithIndex(CanBuildFrom<WrappedString, Tuple2<A1, Object>, That> bf) {
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
    public Tuple2<WrappedString, WrappedString> splitAt(int n) {
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
    public Tuple2<WrappedString, WrappedString> span(Function1<Object, Object> p) {
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
    public GenericCompanion<IndexedSeq> companion() {
        return scala.collection.immutable.IndexedSeq$class.companion(this);
    }

    @Override
    public IndexedSeq<Object> toIndexedSeq() {
        return scala.collection.immutable.IndexedSeq$class.toIndexedSeq(this);
    }

    @Override
    public IndexedSeq<Object> seq() {
        return scala.collection.immutable.IndexedSeq$class.seq(this);
    }

    @Override
    public int hashCode() {
        return IndexedSeqLike$class.hashCode(this);
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
    public scala.collection.immutable.Seq<Object> toSeq() {
        return Seq$class.toSeq(this);
    }

    @Override
    public Combiner<Object, ParSeq<Object>> parCombiner() {
        return Seq$class.parCombiner(this);
    }

    public String self() {
        return this.self;
    }

    @Override
    public WrappedString thisCollection() {
        return this;
    }

    public WrappedString toCollection(WrappedString repr) {
        return repr;
    }

    @Override
    public Builder<Object, WrappedString> newBuilder() {
        return WrappedString$.MODULE$.newBuilder();
    }

    @Override
    public WrappedString slice(int from2, int until2) {
        int start;
        int n = start = from2 < 0 ? 0 : from2;
        if (until2 <= start || start >= ((WrappedString)this.repr()).length()) {
            return new WrappedString("");
        }
        int end = until2 > this.length() ? this.length() : until2;
        return new WrappedString(Predef$.MODULE$.unwrapString((WrappedString)this.repr()).substring(start, end));
    }

    @Override
    public int length() {
        return this.self().length();
    }

    @Override
    public String toString() {
        return this.self();
    }

    public WrappedString(String self) {
        this.self = self;
        Traversable$class.$init$(this);
        Iterable$class.$init$(this);
        Seq$class.$init$(this);
        IndexedSeqLike$class.$init$(this);
        IndexedSeq$class.$init$(this);
        scala.collection.immutable.IndexedSeq$class.$init$(this);
        IndexedSeqOptimized$class.$init$(this);
        Ordered$class.$init$(this);
        StringLike$class.$init$(this);
    }
}

