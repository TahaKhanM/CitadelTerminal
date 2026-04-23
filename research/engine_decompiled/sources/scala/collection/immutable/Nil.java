/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.GenTraversable;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.LinearSeqLike;
import scala.collection.LinearSeqOptimized;
import scala.collection.Parallel;
import scala.collection.Seq;
import scala.collection.SeqView;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.TraversableView;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.LinearSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParSeq;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
@ScalaSignature(bytes="\u0006\u0001q;Q!\u0001\u0002\t\u0002&\t1AT5m\u0015\t\u0019A!A\u0005j[6,H/\u00192mK*\u0011QAB\u0001\u000bG>dG.Z2uS>t'\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001A\u0011!bC\u0007\u0002\u0005\u0019)AB\u0001EA\u001b\t\u0019a*\u001b7\u0014\t-qQ\u0003\u0007\t\u0004\u0015=\t\u0012B\u0001\t\u0003\u0005\u0011a\u0015n\u001d;\u0011\u0005I\u0019R\"\u0001\u0004\n\u0005Q1!a\u0002(pi\"Lgn\u001a\t\u0003%YI!a\u0006\u0004\u0003\u000fA\u0013x\u000eZ;diB\u0011!#G\u0005\u00035\u0019\u0011AbU3sS\u0006d\u0017N_1cY\u0016DQ\u0001H\u0006\u0005\u0002u\ta\u0001P5oSRtD#A\u0005\t\u000b}YA\u0011\t\u0011\u0002\u000f%\u001cX)\u001c9usV\t\u0011\u0005\u0005\u0002\u0013E%\u00111E\u0002\u0002\b\u0005>|G.Z1o\u0011\u0015)3\u0002\"\u0011'\u0003\u0011AW-\u00193\u0016\u0003EAQ\u0001K\u0006\u0005B%\nA\u0001^1jYV\ta\u0002C\u0003,\u0017\u0011\u0005C&\u0001\u0004fcV\fGn\u001d\u000b\u0003C5BQA\f\u0016A\u0002=\nA\u0001\u001e5biB\u0011!\u0003M\u0005\u0003c\u0019\u00111!\u00118z\u0011\u001d\u00194\"!A\u0005BQ\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A\u001b\u0011\u0005YZT\"A\u001c\u000b\u0005aJ\u0014\u0001\u00027b]\u001eT\u0011AO\u0001\u0005U\u00064\u0018-\u0003\u0002=o\t11\u000b\u001e:j]\u001eDqAP\u0006\u0002\u0002\u0013\u0005q(\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001A!\t\u0011\u0012)\u0003\u0002C\r\t\u0019\u0011J\u001c;\t\u000f\u0011[\u0011\u0011!C\u0001\u000b\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA\u0018G\u0011\u001d95)!AA\u0002\u0001\u000b1\u0001\u001f\u00132\u0011\u001dI5\"!A\u0005B)\u000bq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002\u0017B\u0019A*T\u0018\u000e\u0003\u0011I!A\u0014\u0003\u0003\u0011%#XM]1u_JDq\u0001U\u0006\u0002\u0002\u0013%\u0011+A\u0006sK\u0006$'+Z:pYZ,G#\u0001*\u0011\u0005Y\u001a\u0016B\u0001+8\u0005\u0019y%M[3di\"\"1BV-[!\t\u0011r+\u0003\u0002Y\r\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\t\u001b'\u0004_?OFH\u000b!\"\u0001AV-[\u0001")
public final class Nil {
    public static Iterator<Object> productIterator() {
        return Nil$.MODULE$.productIterator();
    }

    public static Object productElement(int n) {
        return Nil$.MODULE$.productElement(n);
    }

    public static int productArity() {
        return Nil$.MODULE$.productArity();
    }

    public static String productPrefix() {
        return Nil$.MODULE$.productPrefix();
    }

    public static boolean equals(Object object) {
        return Nil$.MODULE$.equals(object);
    }

    public static List<Nothing$> tail() {
        return Nil$.MODULE$.tail();
    }

    public static Nothing$ head() {
        return Nil$.MODULE$.head();
    }

    public static boolean isEmpty() {
        return Nil$.MODULE$.isEmpty();
    }

    public static Combiner<Nothing$, ParSeq<Nothing$>> parCombiner() {
        return Nil$.MODULE$.parCombiner();
    }

    public static scala.collection.immutable.Seq<Nothing$> toSeq() {
        return Nil$.MODULE$.toSeq();
    }

    public static <B> boolean corresponds(GenSeq<B> genSeq, Function2<Nothing$, B, Object> function2) {
        return Nil$.MODULE$.corresponds(genSeq, function2);
    }

    public static Iterator<Nothing$> iterator() {
        return Nil$.MODULE$.iterator();
    }

    public static int hashCode() {
        return Nil$.MODULE$.hashCode();
    }

    public static scala.collection.LinearSeq toCollection(LinearSeqLike linearSeqLike) {
        return Nil$.MODULE$.toCollection(linearSeqLike);
    }

    public static scala.collection.LinearSeq<Nothing$> thisCollection() {
        return Nil$.MODULE$.thisCollection();
    }

    public static LinearSeq<Nothing$> seq() {
        return Nil$.MODULE$.seq();
    }

    public static int lastIndexWhere(Function1<Nothing$, Object> function1, int n) {
        return Nil$.MODULE$.lastIndexWhere(function1, n);
    }

    public static int indexWhere(Function1<Nothing$, Object> function1, int n) {
        return Nil$.MODULE$.indexWhere(function1, n);
    }

    public static int segmentLength(Function1<Nothing$, Object> function1, int n) {
        return Nil$.MODULE$.segmentLength(function1, n);
    }

    public static boolean isDefinedAt(int n) {
        return Nil$.MODULE$.isDefinedAt(n);
    }

    public static int lengthCompare(int n) {
        return Nil$.MODULE$.lengthCompare(n);
    }

    public static <B> boolean sameElements(GenIterable<Object> genIterable) {
        return Nil$.MODULE$.sameElements(genIterable);
    }

    public static LinearSeqOptimized dropRight(int n) {
        return Nil$.MODULE$.dropRight(n);
    }

    public static Object last() {
        return Nil$.MODULE$.last();
    }

    public static <B> Object reduceRight(Function2<Nothing$, Object, Object> function2) {
        return Nil$.MODULE$.reduceRight(function2);
    }

    public static <B> Object reduceLeft(Function2<Object, Nothing$, Object> function2) {
        return Nil$.MODULE$.reduceLeft(function2);
    }

    public static <B> B foldLeft(B b, Function2<B, Nothing$, B> function2) {
        return Nil$.MODULE$.foldLeft(b, function2);
    }

    public static Option<Nothing$> find(Function1<Nothing$, Object> function1) {
        return Nil$.MODULE$.find(function1);
    }

    public static <A1> boolean contains(Object object) {
        return Nil$.MODULE$.contains(object);
    }

    public static boolean exists(Function1<Nothing$, Object> function1) {
        return Nil$.MODULE$.exists(function1);
    }

    public static boolean forall(Function1<Nothing$, Object> function1) {
        return Nil$.MODULE$.forall(function1);
    }

    public static Object apply(int n) {
        return Nil$.MODULE$.apply(n);
    }

    public static int length() {
        return Nil$.MODULE$.length();
    }

    public static Stream<Nothing$> toStream() {
        return Nil$.MODULE$.toStream();
    }

    public static String stringPrefix() {
        return Nil$.MODULE$.stringPrefix();
    }

    public static <B> B foldRight(B b, Function2<Nothing$, B, B> function2) {
        return Nil$.MODULE$.foldRight(b, function2);
    }

    public static List<Nothing$> reverse() {
        return Nil$.MODULE$.reverse();
    }

    public static <U> void foreach(Function1<Nothing$, U> function1) {
        Nil$.MODULE$.foreach(function1);
    }

    public static Tuple2<List<Nothing$>, List<Nothing$>> span(Function1<Nothing$, Object> function1) {
        return Nil$.MODULE$.span(function1);
    }

    public static List<Nothing$> dropWhile(Function1<Nothing$, Object> function1) {
        return Nil$.MODULE$.dropWhile(function1);
    }

    public static List<Nothing$> takeWhile(Function1<Nothing$, Object> function1) {
        return Nil$.MODULE$.takeWhile(function1);
    }

    public static <B, That> That flatMap(Function1<Nothing$, GenTraversableOnce<B>> function1, CanBuildFrom<List<Nothing$>, B, That> canBuildFrom) {
        return Nil$.MODULE$.flatMap(function1, canBuildFrom);
    }

    public static <B, That> That collect(PartialFunction<Nothing$, B> partialFunction, CanBuildFrom<List<Nothing$>, B, That> canBuildFrom) {
        return Nil$.MODULE$.collect(partialFunction, canBuildFrom);
    }

    public static <B, That> That map(Function1<Nothing$, B> function1, CanBuildFrom<List<Nothing$>, B, That> canBuildFrom) {
        return Nil$.MODULE$.map(function1, canBuildFrom);
    }

    public static Tuple2<List<Nothing$>, List<Nothing$>> splitAt(int n) {
        return Nil$.MODULE$.splitAt(n);
    }

    public static List<Nothing$> takeRight(int n) {
        return Nil$.MODULE$.takeRight(n);
    }

    public static List<Nothing$> slice(int n, int n2) {
        return Nil$.MODULE$.slice(n, n2);
    }

    public static List<Nothing$> drop(int n) {
        return Nil$.MODULE$.drop(n);
    }

    public static List<Nothing$> take(int n) {
        return Nil$.MODULE$.take(n);
    }

    public static List<Nothing$> toList() {
        return Nil$.MODULE$.toList();
    }

    public static <B, That> That $plus$colon(B b, CanBuildFrom<List<Nothing$>, B, That> canBuildFrom) {
        return Nil$.MODULE$.$plus$colon(b, canBuildFrom);
    }

    public static <B, That> That $plus$plus(GenTraversableOnce<B> genTraversableOnce, CanBuildFrom<List<Nothing$>, B, That> canBuildFrom) {
        return Nil$.MODULE$.$plus$plus(genTraversableOnce, canBuildFrom);
    }

    public static <B> List<B> mapConserve(Function1<Nothing$, B> function1) {
        return Nil$.MODULE$.mapConserve(function1);
    }

    public static <B> List<B> reverse_$colon$colon$colon(List<B> list2) {
        return Nil$.MODULE$.reverse_$colon$colon$colon(list2);
    }

    public static <B> List<B> $colon$colon$colon(List<B> list2) {
        return Nil$.MODULE$.$colon$colon$colon(list2);
    }

    public static <B> List<B> $colon$colon(B b) {
        return Nil$.MODULE$.$colon$colon(b);
    }

    public static GenericCompanion<List> companion() {
        return Nil$.MODULE$.companion();
    }

    public static <A> Function1<A, Nothing$> compose(Function1<A, Object> function1) {
        return Nil$.MODULE$.compose(function1);
    }

    public static <U> Function1<Object, Object> runWith(Function1<Nothing$, U> function1) {
        return Nil$.MODULE$.runWith(function1);
    }

    public static Object applyOrElse(Object object, Function1 function1) {
        return Nil$.MODULE$.applyOrElse(object, function1);
    }

    public static Function1<Object, Option<Nothing$>> lift() {
        return Nil$.MODULE$.lift();
    }

    public static <C> PartialFunction<Object, C> andThen(Function1<Nothing$, C> function1) {
        return Nil$.MODULE$.andThen(function1);
    }

    public static <A1, B1> PartialFunction<Object, Object> orElse(PartialFunction<Object, Object> partialFunction) {
        return Nil$.MODULE$.orElse(partialFunction);
    }

    public static <B> boolean startsWith(GenSeq<B> genSeq) {
        return Nil$.MODULE$.startsWith(genSeq);
    }

    public static int lastIndexWhere(Function1<Nothing$, Object> function1) {
        return Nil$.MODULE$.lastIndexWhere(function1);
    }

    public static <B> int lastIndexOf(Object object, int n) {
        return Nil$.MODULE$.lastIndexOf(object, n);
    }

    public static <B> int lastIndexOf(Object object) {
        return Nil$.MODULE$.lastIndexOf(object);
    }

    public static <B> int indexOf(Object object, int n) {
        return Nil$.MODULE$.indexOf(object, n);
    }

    public static <B> int indexOf(Object object) {
        return Nil$.MODULE$.indexOf(object);
    }

    public static int indexWhere(Function1<Nothing$, Object> function1) {
        return Nil$.MODULE$.indexWhere(function1);
    }

    public static int prefixLength(Function1<Nothing$, Object> function1) {
        return Nil$.MODULE$.prefixLength(function1);
    }

    public static String toString() {
        return Nil$.MODULE$.toString();
    }

    public static SeqView<Nothing$, Seq<Nothing$>> view(int n, int n2) {
        return Nil$.MODULE$.view(n, n2);
    }

    public static Object view() {
        return Nil$.MODULE$.view();
    }

    public static Range indices() {
        return Nil$.MODULE$.indices();
    }

    public static Seq<Nothing$> toSeq() {
        return Nil$.MODULE$.toSeq();
    }

    public static Object sorted(Ordering ordering) {
        return Nil$.MODULE$.sorted(ordering);
    }

    public static Object sortBy(Function1 function1, Ordering ordering) {
        return Nil$.MODULE$.sortBy(function1, ordering);
    }

    public static Object sortWith(Function2 function2) {
        return Nil$.MODULE$.sortWith(function2);
    }

    public static <B, That> Object padTo(int n, Object object, CanBuildFrom<Seq<Nothing$>, Object, Object> canBuildFrom) {
        return Nil$.MODULE$.padTo(n, object, canBuildFrom);
    }

    public static <B, That> Object $colon$plus(Object object, CanBuildFrom<Seq<Nothing$>, Object, Object> canBuildFrom) {
        return Nil$.MODULE$.$colon$plus(object, canBuildFrom);
    }

    public static <B, That> Object updated(int n, Object object, CanBuildFrom<Seq<Nothing$>, Object, Object> canBuildFrom) {
        return Nil$.MODULE$.updated(n, object, canBuildFrom);
    }

    public static <B, That> Object patch(int n, GenSeq<Object> genSeq, int n2, CanBuildFrom<Seq<Nothing$>, Object, Object> canBuildFrom) {
        return Nil$.MODULE$.patch(n, genSeq, n2, canBuildFrom);
    }

    public static Object distinct() {
        return Nil$.MODULE$.distinct();
    }

    public static Object intersect(GenSeq genSeq) {
        return Nil$.MODULE$.intersect(genSeq);
    }

    public static Object diff(GenSeq genSeq) {
        return Nil$.MODULE$.diff(genSeq);
    }

    public static <B, That> Object union(GenSeq<Object> genSeq, CanBuildFrom<Seq<Nothing$>, Object, Object> canBuildFrom) {
        return Nil$.MODULE$.union(genSeq, canBuildFrom);
    }

    public static <B> boolean containsSlice(GenSeq<B> genSeq) {
        return Nil$.MODULE$.containsSlice(genSeq);
    }

    public static <B> int lastIndexOfSlice(GenSeq<Object> genSeq, int n) {
        return Nil$.MODULE$.lastIndexOfSlice(genSeq, n);
    }

    public static <B> int lastIndexOfSlice(GenSeq<Object> genSeq) {
        return Nil$.MODULE$.lastIndexOfSlice(genSeq);
    }

    public static <B> int indexOfSlice(GenSeq<Object> genSeq, int n) {
        return Nil$.MODULE$.indexOfSlice(genSeq, n);
    }

    public static <B> int indexOfSlice(GenSeq<Object> genSeq) {
        return Nil$.MODULE$.indexOfSlice(genSeq);
    }

    public static <B> boolean endsWith(GenSeq<B> genSeq) {
        return Nil$.MODULE$.endsWith(genSeq);
    }

    public static <B> boolean startsWith(GenSeq<B> genSeq, int n) {
        return Nil$.MODULE$.startsWith(genSeq, n);
    }

    public static Iterator<Nothing$> reverseIterator() {
        return Nil$.MODULE$.reverseIterator();
    }

    public static <B, That> That reverseMap(Function1<Nothing$, B> function1, CanBuildFrom<Seq<Nothing$>, B, That> canBuildFrom) {
        return Nil$.MODULE$.reverseMap(function1, canBuildFrom);
    }

    public static Object reverse() {
        return Nil$.MODULE$.reverse();
    }

    public static Iterator<Seq<Nothing$>> combinations(int n) {
        return Nil$.MODULE$.combinations(n);
    }

    public static Iterator<Seq<Nothing$>> permutations() {
        return Nil$.MODULE$.permutations();
    }

    public static int size() {
        return Nil$.MODULE$.size();
    }

    public static Seq toCollection(Object object) {
        return Nil$.MODULE$.toCollection(object);
    }

    public static Seq<Nothing$> thisCollection() {
        return Nil$.MODULE$.thisCollection();
    }

    public static Seq<Nothing$> seq() {
        return Nil$.MODULE$.seq();
    }

    public static IterableView<Nothing$, Iterable<Nothing$>> view(int n, int n2) {
        return Nil$.MODULE$.view(n, n2);
    }

    public static Object view() {
        return Nil$.MODULE$.view();
    }

    public static boolean canEqual(Object object) {
        return Nil$.MODULE$.canEqual(object);
    }

    public static <A1, That> Object zipWithIndex(CanBuildFrom<Iterable<Nothing$>, Tuple2<Object, Object>, Object> canBuildFrom) {
        return Nil$.MODULE$.zipWithIndex(canBuildFrom);
    }

    public static <B, A1, That> Object zipAll(GenIterable<Object> genIterable, Object object, Object object2, CanBuildFrom<Iterable<Nothing$>, Tuple2<Object, Object>, Object> canBuildFrom) {
        return Nil$.MODULE$.zipAll(genIterable, object, object2, canBuildFrom);
    }

    public static <A1, B, That> Object zip(GenIterable<Object> genIterable, CanBuildFrom<Iterable<Nothing$>, Tuple2<Object, Object>, Object> canBuildFrom) {
        return Nil$.MODULE$.zip(genIterable, canBuildFrom);
    }

    public static <B> void copyToArray(Object object, int n, int n2) {
        Nil$.MODULE$.copyToArray(object, n, n2);
    }

    public static Object dropRight(int n) {
        return Nil$.MODULE$.dropRight(n);
    }

    public static Object takeRight(int n) {
        return Nil$.MODULE$.takeRight(n);
    }

    public static Iterator<Iterable<Nothing$>> sliding(int n, int n2) {
        return Nil$.MODULE$.sliding(n, n2);
    }

    public static Iterator<Iterable<Nothing$>> sliding(int n) {
        return Nil$.MODULE$.sliding(n);
    }

    public static Iterator<Iterable<Nothing$>> grouped(int n) {
        return Nil$.MODULE$.grouped(n);
    }

    public static Object takeWhile(Function1 function1) {
        return Nil$.MODULE$.takeWhile(function1);
    }

    public static Object drop(int n) {
        return Nil$.MODULE$.drop(n);
    }

    public static Object take(int n) {
        return Nil$.MODULE$.take(n);
    }

    public static Object slice(int n, int n2) {
        return Nil$.MODULE$.slice(n, n2);
    }

    public static Object head() {
        return Nil$.MODULE$.head();
    }

    public static Iterator<Nothing$> toIterator() {
        return Nil$.MODULE$.toIterator();
    }

    public static Iterable<Nothing$> toIterable() {
        return Nil$.MODULE$.toIterable();
    }

    public static Iterable toCollection(Object object) {
        return Nil$.MODULE$.toCollection(object);
    }

    public static Iterable<Nothing$> thisCollection() {
        return Nil$.MODULE$.thisCollection();
    }

    public static Iterable<Nothing$> seq() {
        return Nil$.MODULE$.seq();
    }

    public static StringBuilder addString(StringBuilder stringBuilder) {
        return Nil$.MODULE$.addString(stringBuilder);
    }

    public static StringBuilder addString(StringBuilder stringBuilder, String string2) {
        return Nil$.MODULE$.addString(stringBuilder, string2);
    }

    public static StringBuilder addString(StringBuilder stringBuilder, String string2, String string3, String string4) {
        return Nil$.MODULE$.addString(stringBuilder, string2, string3, string4);
    }

    public static String mkString() {
        return Nil$.MODULE$.mkString();
    }

    public static String mkString(String string2) {
        return Nil$.MODULE$.mkString(string2);
    }

    public static String mkString(String string2, String string3, String string4) {
        return Nil$.MODULE$.mkString(string2, string3, string4);
    }

    public static <T, U> Map<T, U> toMap(Predef$.less.colon.less<Nothing$, Tuple2<T, U>> less2) {
        return Nil$.MODULE$.toMap(less2);
    }

    public static Vector<Nothing$> toVector() {
        return Nil$.MODULE$.toVector();
    }

    public static <B> Set<Object> toSet() {
        return Nil$.MODULE$.toSet();
    }

    public static <B> Buffer<Object> toBuffer() {
        return Nil$.MODULE$.toBuffer();
    }

    public static IndexedSeq<Nothing$> toIndexedSeq() {
        return Nil$.MODULE$.toIndexedSeq();
    }

    public static Object toArray(ClassTag classTag) {
        return Nil$.MODULE$.toArray(classTag);
    }

    public static <B> void copyToArray(Object object) {
        Nil$.MODULE$.copyToArray(object);
    }

    public static <B> void copyToArray(Object object, int n) {
        Nil$.MODULE$.copyToArray(object, n);
    }

    public static <B> void copyToBuffer(Buffer<Object> buffer) {
        Nil$.MODULE$.copyToBuffer(buffer);
    }

    public static Object minBy(Function1 function1, Ordering ordering) {
        return Nil$.MODULE$.minBy(function1, ordering);
    }

    public static Object maxBy(Function1 function1, Ordering ordering) {
        return Nil$.MODULE$.maxBy(function1, ordering);
    }

    public static Object max(Ordering ordering) {
        return Nil$.MODULE$.max(ordering);
    }

    public static Object min(Ordering ordering) {
        return Nil$.MODULE$.min(ordering);
    }

    public static <B> Object product(Numeric<Object> numeric) {
        return Nil$.MODULE$.product(numeric);
    }

    public static <B> Object sum(Numeric<Object> numeric) {
        return Nil$.MODULE$.sum(numeric);
    }

    public static <B> B aggregate(Function0<B> function0, Function2<B, Nothing$, B> function2, Function2<B, B, B> function22) {
        return Nil$.MODULE$.aggregate(function0, function2, function22);
    }

    public static <A1> Object fold(Object object, Function2<Object, Object, Object> function2) {
        return Nil$.MODULE$.fold(object, function2);
    }

    public static <A1> Option<Object> reduceOption(Function2<Object, Object, Object> function2) {
        return Nil$.MODULE$.reduceOption(function2);
    }

    public static <A1> Object reduce(Function2<Object, Object, Object> function2) {
        return Nil$.MODULE$.reduce(function2);
    }

    public static <B> Option<Object> reduceRightOption(Function2<Nothing$, Object, Object> function2) {
        return Nil$.MODULE$.reduceRightOption(function2);
    }

    public static <B> Option<Object> reduceLeftOption(Function2<Object, Nothing$, Object> function2) {
        return Nil$.MODULE$.reduceLeftOption(function2);
    }

    public static <B> B $colon$bslash(B b, Function2<Nothing$, B, B> function2) {
        return Nil$.MODULE$.$colon$bslash(b, function2);
    }

    public static <B> B $div$colon(B b, Function2<B, Nothing$, B> function2) {
        return Nil$.MODULE$.$div$colon(b, function2);
    }

    public static <B> Option<B> collectFirst(PartialFunction<Nothing$, B> partialFunction) {
        return Nil$.MODULE$.collectFirst(partialFunction);
    }

    public static int count(Function1<Nothing$, Object> function1) {
        return Nil$.MODULE$.count(function1);
    }

    public static boolean nonEmpty() {
        return Nil$.MODULE$.nonEmpty();
    }

    public static List<Nothing$> reversed() {
        return Nil$.MODULE$.reversed();
    }

    public static Parallel par() {
        return Nil$.MODULE$.par();
    }

    public static FilterMonadic<Nothing$, Traversable<Nothing$>> withFilter(Function1<Nothing$, Object> function1) {
        return Nil$.MODULE$.withFilter(function1);
    }

    public static TraversableView<Nothing$, Traversable<Nothing$>> view(int n, int n2) {
        return Nil$.MODULE$.view(n, n2);
    }

    public static Object view() {
        return Nil$.MODULE$.view();
    }

    public static <Col> Col to(CanBuildFrom<Nothing$, Nothing$, Col> canBuildFrom) {
        return Nil$.MODULE$.to(canBuildFrom);
    }

    public static Traversable<Nothing$> toTraversable() {
        return Nil$.MODULE$.toTraversable();
    }

    public static Iterator<Traversable<Nothing$>> inits() {
        return Nil$.MODULE$.inits();
    }

    public static Iterator<Traversable<Nothing$>> tails() {
        return Nil$.MODULE$.tails();
    }

    public static Object dropWhile(Function1 function1) {
        return Nil$.MODULE$.dropWhile(function1);
    }

    public static Object init() {
        return Nil$.MODULE$.init();
    }

    public static Option<Nothing$> lastOption() {
        return Nil$.MODULE$.lastOption();
    }

    public static Object tail() {
        return Nil$.MODULE$.tail();
    }

    public static Option<Nothing$> headOption() {
        return Nil$.MODULE$.headOption();
    }

    public static <B, That> That scanRight(B b, Function2<Nothing$, B, B> function2, CanBuildFrom<Traversable<Nothing$>, B, That> canBuildFrom) {
        return Nil$.MODULE$.scanRight(b, function2, canBuildFrom);
    }

    public static <B, That> That scanLeft(B b, Function2<B, Nothing$, B> function2, CanBuildFrom<Traversable<Nothing$>, B, That> canBuildFrom) {
        return Nil$.MODULE$.scanLeft(b, function2, canBuildFrom);
    }

    public static <B, That> Object scan(Object object, Function2<Object, Object, Object> function2, CanBuildFrom<Traversable<Nothing$>, Object, Object> canBuildFrom) {
        return Nil$.MODULE$.scan(object, function2, canBuildFrom);
    }

    public static <K> Map<K, Traversable<Nothing$>> groupBy(Function1<Nothing$, K> function1) {
        return Nil$.MODULE$.groupBy(function1);
    }

    public static Tuple2<Traversable<Nothing$>, Traversable<Nothing$>> partition(Function1<Nothing$, Object> function1) {
        return Nil$.MODULE$.partition(function1);
    }

    public static Object filterNot(Function1 function1) {
        return Nil$.MODULE$.filterNot(function1);
    }

    public static Object filter(Function1 function1) {
        return Nil$.MODULE$.filter(function1);
    }

    public static <B, That> Object $plus$plus$colon(Traversable<Object> traversable, CanBuildFrom<Traversable<Nothing$>, Object, Object> canBuildFrom) {
        return Nil$.MODULE$.$plus$plus$colon(traversable, canBuildFrom);
    }

    public static <B, That> Object $plus$plus$colon(TraversableOnce<Object> traversableOnce, CanBuildFrom<Traversable<Nothing$>, Object, Object> canBuildFrom) {
        return Nil$.MODULE$.$plus$plus$colon(traversableOnce, canBuildFrom);
    }

    public static boolean hasDefiniteSize() {
        return Nil$.MODULE$.hasDefiniteSize();
    }

    public static Traversable toCollection(Object object) {
        return Nil$.MODULE$.toCollection(object);
    }

    public static Traversable<Nothing$> thisCollection() {
        return Nil$.MODULE$.thisCollection();
    }

    public static boolean isTraversableAgain() {
        return Nil$.MODULE$.isTraversableAgain();
    }

    public static Object repr() {
        return Nil$.MODULE$.repr();
    }

    public static GenTraversable transpose(Function1 function1) {
        return Nil$.MODULE$.transpose(function1);
    }

    public static GenTraversable flatten(Function1 function1) {
        return Nil$.MODULE$.flatten(function1);
    }

    public static <A1, A2, A3> Tuple3<Traversable<A1>, Traversable<A2>, Traversable<A3>> unzip3(Function1<Nothing$, Tuple3<A1, A2, A3>> function1) {
        return Nil$.MODULE$.unzip3(function1);
    }

    public static <A1, A2> Tuple2<Traversable<A1>, Traversable<A2>> unzip(Function1<Nothing$, Tuple2<A1, A2>> function1) {
        return Nil$.MODULE$.unzip(function1);
    }

    public static <B> Builder<B, Traversable<B>> genericBuilder() {
        return Nil$.MODULE$.genericBuilder();
    }

    public static Builder<Nothing$, Traversable<Nothing$>> newBuilder() {
        return Nil$.MODULE$.newBuilder();
    }

    public static Traversable<Nothing$> seq() {
        return Nil$.MODULE$.seq();
    }
}

