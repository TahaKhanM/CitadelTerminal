/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenIterable;
import scala.collection.GenTraversable;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.Parallel;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterable;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.io.AbstractFile;
import scala.reflect.io.NoAbstractFile$;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001a<Q!\u0001\u0002\t\u0002%\taBT8BEN$(/Y2u\r&dWM\u0003\u0002\u0004\t\u0005\u0011\u0011n\u001c\u0006\u0003\u000b\u0019\tqA]3gY\u0016\u001cGOC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"AC\u0006\u000e\u0003\t1Q\u0001\u0004\u0002\t\u00025\u0011aBT8BEN$(/Y2u\r&dWm\u0005\u0002\f\u001dA\u0011!bD\u0005\u0003!\t\u0011A\"\u00112tiJ\f7\r\u001e$jY\u0016DQAE\u0006\u0005\u0002M\ta\u0001P5oSRtD#A\u0005\t\u000bUYA\u0011\u0001\f\u0002\u0011\u0005\u00147o\u001c7vi\u0016,\u0012A\u0004\u0005\u00061-!\tAF\u0001\nG>tG/Y5oKJDQAG\u0006\u0005\u0002m\taa\u0019:fCR,G#\u0001\u000f\u0011\u0005uqR\"\u0001\u0004\n\u0005}1!\u0001B+oSRDQ!I\u0006\u0005\u0002m\ta\u0001Z3mKR,\u0007\"B\u0012\f\t\u0003!\u0013\u0001\u00024jY\u0016,\u0012!\n\t\u0003M)j\u0011a\n\u0006\u0003\u0007!R\u0011!K\u0001\u0005U\u00064\u0018-\u0003\u0002,O\t!a)\u001b7f\u0011\u0015i3\u0002\"\u0001/\u0003\u0015Ig\u000e];u+\u0005y\u0003C\u0001\u00141\u0013\t\ttEA\u0006J]B,Ho\u0015;sK\u0006l\u0007\"B\u001a\f\t\u0003!\u0014aC5t\t&\u0014Xm\u0019;pef,\u0012!\u000e\t\u0003;YJ!a\u000e\u0004\u0003\u000f\t{w\u000e\\3b]\")\u0011h\u0003C!i\u0005I\u0011n\u001d,jeR,\u0018\r\u001c\u0005\u0006w-!\t\u0001P\u0001\tSR,'/\u0019;peV\tQ\bE\u0002?\u0003:q!!H \n\u0005\u00013\u0011a\u00029bG.\fw-Z\u0005\u0003\u0005\u000e\u0013\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0003\u0001\u001aAQ!R\u0006\u0005\u0002\u0019\u000bA\u0002\\1ti6{G-\u001b4jK\u0012,\u0012a\u0012\t\u0003;!K!!\u0013\u0004\u0003\t1{gn\u001a\u0005\u0006\u0017.!\t\u0001T\u0001\u000bY>|7.\u001e9OC6,Gc\u0001\bN-\")aJ\u0013a\u0001\u001f\u0006!a.Y7f!\t\u00016K\u0004\u0002\u001e#&\u0011!KB\u0001\u0007!J,G-\u001a4\n\u0005Q+&AB*ue&twM\u0003\u0002S\r!)qK\u0013a\u0001k\u0005IA-\u001b:fGR|'/\u001f\u0005\u00063.!\tAW\u0001\u0014Y>|7.\u001e9OC6,WK\\2iK\u000e\\W\r\u001a\u000b\u0004\u001dmc\u0006\"\u0002(Y\u0001\u0004y\u0005\"B,Y\u0001\u0004)\u0004\"\u0002(\f\t\u0003qV#A(\t\u000b\u0001\\A\u0011A1\u0002\r=,H\u000f];u+\u0005\u0011\u0007C\u0001\u0014d\u0013\t!wE\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW\u000eC\u0003g\u0017\u0011\u0005a,\u0001\u0003qCRD\u0007\"\u00025\f\t\u0003J\u0017a\u0003;p\u0005f$X-\u0011:sCf,\u0012A\u001b\t\u0004;-l\u0017B\u00017\u0007\u0005\u0015\t%O]1z!\tib.\u0003\u0002p\r\t!!)\u001f;f\u0011\u0015\t8\u0002\"\u0011s\u0003!!xn\u0015;sS:<G#A:\u0011\u0005Q<X\"A;\u000b\u0005YD\u0013\u0001\u00027b]\u001eL!\u0001V;")
public final class NoAbstractFile {
    public static String toString() {
        return NoAbstractFile$.MODULE$.toString();
    }

    public static byte[] toByteArray() {
        return NoAbstractFile$.MODULE$.toByteArray();
    }

    public static String path() {
        return NoAbstractFile$.MODULE$.path();
    }

    public static OutputStream output() {
        return NoAbstractFile$.MODULE$.output();
    }

    public static String name() {
        return NoAbstractFile$.MODULE$.name();
    }

    public static AbstractFile lookupNameUnchecked(String string2, boolean bl) {
        return NoAbstractFile$.MODULE$.lookupNameUnchecked(string2, bl);
    }

    public static AbstractFile lookupName(String string2, boolean bl) {
        return NoAbstractFile$.MODULE$.lookupName(string2, bl);
    }

    public static long lastModified() {
        return NoAbstractFile$.MODULE$.lastModified();
    }

    public static Iterator<AbstractFile> iterator() {
        return NoAbstractFile$.MODULE$.iterator();
    }

    public static boolean isVirtual() {
        return NoAbstractFile$.MODULE$.isVirtual();
    }

    public static boolean isDirectory() {
        return NoAbstractFile$.MODULE$.isDirectory();
    }

    public static InputStream input() {
        return NoAbstractFile$.MODULE$.input();
    }

    public static File file() {
        return NoAbstractFile$.MODULE$.file();
    }

    public static void delete() {
        NoAbstractFile$.MODULE$.delete();
    }

    public static void create() {
        NoAbstractFile$.MODULE$.create();
    }

    public static AbstractFile container() {
        return NoAbstractFile$.MODULE$.container();
    }

    public static AbstractFile absolute() {
        return NoAbstractFile$.MODULE$.absolute();
    }

    public static StringBuilder addString(StringBuilder stringBuilder) {
        return NoAbstractFile$.MODULE$.addString(stringBuilder);
    }

    public static StringBuilder addString(StringBuilder stringBuilder, String string2) {
        return NoAbstractFile$.MODULE$.addString(stringBuilder, string2);
    }

    public static StringBuilder addString(StringBuilder stringBuilder, String string2, String string3, String string4) {
        return NoAbstractFile$.MODULE$.addString(stringBuilder, string2, string3, string4);
    }

    public static String mkString() {
        return NoAbstractFile$.MODULE$.mkString();
    }

    public static String mkString(String string2) {
        return NoAbstractFile$.MODULE$.mkString(string2);
    }

    public static String mkString(String string2, String string3, String string4) {
        return NoAbstractFile$.MODULE$.mkString(string2, string3, string4);
    }

    public static <T, U> Map<T, U> toMap(Predef$.less.colon.less<AbstractFile, Tuple2<T, U>> less2) {
        return NoAbstractFile$.MODULE$.toMap((Predef$.less.colon.less)less2);
    }

    public static Vector<AbstractFile> toVector() {
        return NoAbstractFile$.MODULE$.toVector();
    }

    public static <B> Set<B> toSet() {
        return NoAbstractFile$.MODULE$.toSet();
    }

    public static <B> Buffer<B> toBuffer() {
        return NoAbstractFile$.MODULE$.toBuffer();
    }

    public static IndexedSeq<AbstractFile> toIndexedSeq() {
        return NoAbstractFile$.MODULE$.toIndexedSeq();
    }

    public static Seq<AbstractFile> toSeq() {
        return NoAbstractFile$.MODULE$.toSeq();
    }

    public static List<AbstractFile> toList() {
        return NoAbstractFile$.MODULE$.toList();
    }

    public static Object toArray(ClassTag classTag) {
        return NoAbstractFile$.MODULE$.toArray(classTag);
    }

    public static <B> void copyToArray(Object object) {
        NoAbstractFile$.MODULE$.copyToArray(object);
    }

    public static <B> void copyToArray(Object object, int n) {
        NoAbstractFile$.MODULE$.copyToArray(object, n);
    }

    public static <B> void copyToBuffer(Buffer<B> buffer) {
        NoAbstractFile$.MODULE$.copyToBuffer(buffer);
    }

    public static Object minBy(Function1 function1, Ordering ordering) {
        return NoAbstractFile$.MODULE$.minBy(function1, ordering);
    }

    public static Object maxBy(Function1 function1, Ordering ordering) {
        return NoAbstractFile$.MODULE$.maxBy(function1, ordering);
    }

    public static Object max(Ordering ordering) {
        return NoAbstractFile$.MODULE$.max(ordering);
    }

    public static Object min(Ordering ordering) {
        return NoAbstractFile$.MODULE$.min(ordering);
    }

    public static <B> B product(Numeric<B> numeric) {
        return NoAbstractFile$.MODULE$.product(numeric);
    }

    public static <B> B sum(Numeric<B> numeric) {
        return NoAbstractFile$.MODULE$.sum(numeric);
    }

    public static <B> B aggregate(Function0<B> function0, Function2<B, AbstractFile, B> function2, Function2<B, B, B> function22) {
        return NoAbstractFile$.MODULE$.aggregate(function0, function2, function22);
    }

    public static <A1> A1 fold(A1 A1, Function2<A1, A1, A1> function2) {
        return NoAbstractFile$.MODULE$.fold(A1, function2);
    }

    public static <A1> Option<A1> reduceOption(Function2<A1, A1, A1> function2) {
        return NoAbstractFile$.MODULE$.reduceOption(function2);
    }

    public static <A1> A1 reduce(Function2<A1, A1, A1> function2) {
        return NoAbstractFile$.MODULE$.reduce(function2);
    }

    public static <B> Option<B> reduceRightOption(Function2<AbstractFile, B, B> function2) {
        return NoAbstractFile$.MODULE$.reduceRightOption(function2);
    }

    public static <B> Option<B> reduceLeftOption(Function2<B, AbstractFile, B> function2) {
        return NoAbstractFile$.MODULE$.reduceLeftOption(function2);
    }

    public static <B> B reduceLeft(Function2<B, AbstractFile, B> function2) {
        return NoAbstractFile$.MODULE$.reduceLeft(function2);
    }

    public static <B> B foldLeft(B b, Function2<B, AbstractFile, B> function2) {
        return NoAbstractFile$.MODULE$.foldLeft(b, function2);
    }

    public static <B> B $colon$bslash(B b, Function2<AbstractFile, B, B> function2) {
        return NoAbstractFile$.MODULE$.$colon$bslash(b, function2);
    }

    public static <B> B $div$colon(B b, Function2<B, AbstractFile, B> function2) {
        return NoAbstractFile$.MODULE$.$div$colon(b, function2);
    }

    public static <B> Option<B> collectFirst(PartialFunction<AbstractFile, B> partialFunction) {
        return NoAbstractFile$.MODULE$.collectFirst(partialFunction);
    }

    public static int count(Function1<AbstractFile, Object> function1) {
        return NoAbstractFile$.MODULE$.count(function1);
    }

    public static boolean nonEmpty() {
        return NoAbstractFile$.MODULE$.nonEmpty();
    }

    public static int size() {
        return NoAbstractFile$.MODULE$.size();
    }

    public static List<AbstractFile> reversed() {
        return NoAbstractFile$.MODULE$.reversed();
    }

    public static Parallel par() {
        return NoAbstractFile$.MODULE$.par();
    }

    public static FilterMonadic<AbstractFile, Iterable<AbstractFile>> withFilter(Function1<AbstractFile, Object> function1) {
        return NoAbstractFile$.MODULE$.withFilter(function1);
    }

    public static String stringPrefix() {
        return NoAbstractFile$.MODULE$.stringPrefix();
    }

    public static <Col> Col to(CanBuildFrom<Nothing$, AbstractFile, Col> canBuildFrom) {
        return NoAbstractFile$.MODULE$.to(canBuildFrom);
    }

    public static Traversable<AbstractFile> toTraversable() {
        return NoAbstractFile$.MODULE$.toTraversable();
    }

    public static Iterator<Iterable<AbstractFile>> inits() {
        return NoAbstractFile$.MODULE$.inits();
    }

    public static Iterator<Iterable<AbstractFile>> tails() {
        return NoAbstractFile$.MODULE$.tails();
    }

    public static Tuple2<Iterable<AbstractFile>, Iterable<AbstractFile>> splitAt(int n) {
        return NoAbstractFile$.MODULE$.splitAt(n);
    }

    public static Tuple2<Iterable<AbstractFile>, Iterable<AbstractFile>> span(Function1<AbstractFile, Object> function1) {
        return NoAbstractFile$.MODULE$.span(function1);
    }

    public static Object dropWhile(Function1 function1) {
        return NoAbstractFile$.MODULE$.dropWhile(function1);
    }

    public static Object init() {
        return NoAbstractFile$.MODULE$.init();
    }

    public static Option<AbstractFile> lastOption() {
        return NoAbstractFile$.MODULE$.lastOption();
    }

    public static Object last() {
        return NoAbstractFile$.MODULE$.last();
    }

    public static Object tail() {
        return NoAbstractFile$.MODULE$.tail();
    }

    public static Option<AbstractFile> headOption() {
        return NoAbstractFile$.MODULE$.headOption();
    }

    public static <B, That> That scanRight(B b, Function2<AbstractFile, B, B> function2, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoAbstractFile$.MODULE$.scanRight(b, function2, canBuildFrom);
    }

    public static <B, That> That scanLeft(B b, Function2<B, AbstractFile, B> function2, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoAbstractFile$.MODULE$.scanLeft(b, function2, canBuildFrom);
    }

    public static <B, That> That scan(B b, Function2<B, B, B> function2, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoAbstractFile$.MODULE$.scan(b, function2, canBuildFrom);
    }

    public static <K> Map<K, Iterable<AbstractFile>> groupBy(Function1<AbstractFile, K> function1) {
        return NoAbstractFile$.MODULE$.groupBy((Function1)function1);
    }

    public static Tuple2<Iterable<AbstractFile>, Iterable<AbstractFile>> partition(Function1<AbstractFile, Object> function1) {
        return NoAbstractFile$.MODULE$.partition(function1);
    }

    public static <B, That> That collect(PartialFunction<AbstractFile, B> partialFunction, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoAbstractFile$.MODULE$.collect(partialFunction, canBuildFrom);
    }

    public static Object filterNot(Function1 function1) {
        return NoAbstractFile$.MODULE$.filterNot(function1);
    }

    public static Object filter(Function1 function1) {
        return NoAbstractFile$.MODULE$.filter(function1);
    }

    public static <B, That> That flatMap(Function1<AbstractFile, GenTraversableOnce<B>> function1, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoAbstractFile$.MODULE$.flatMap(function1, canBuildFrom);
    }

    public static <B, That> That map(Function1<AbstractFile, B> function1, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoAbstractFile$.MODULE$.map(function1, canBuildFrom);
    }

    public static <B, That> That $plus$plus$colon(Traversable<B> traversable, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoAbstractFile$.MODULE$.$plus$plus$colon(traversable, canBuildFrom);
    }

    public static <B, That> That $plus$plus$colon(TraversableOnce<B> traversableOnce, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoAbstractFile$.MODULE$.$plus$plus$colon(traversableOnce, canBuildFrom);
    }

    public static <B, That> That $plus$plus(GenTraversableOnce<B> genTraversableOnce, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoAbstractFile$.MODULE$.$plus$plus(genTraversableOnce, canBuildFrom);
    }

    public static boolean hasDefiniteSize() {
        return NoAbstractFile$.MODULE$.hasDefiniteSize();
    }

    public static Combiner<AbstractFile, ParIterable<AbstractFile>> parCombiner() {
        return NoAbstractFile$.MODULE$.parCombiner();
    }

    public static boolean isTraversableAgain() {
        return NoAbstractFile$.MODULE$.isTraversableAgain();
    }

    public static Object repr() {
        return NoAbstractFile$.MODULE$.repr();
    }

    public static GenTraversable transpose(Function1 function1) {
        return NoAbstractFile$.MODULE$.transpose(function1);
    }

    public static GenTraversable flatten(Function1 function1) {
        return NoAbstractFile$.MODULE$.flatten(function1);
    }

    public static <A1, A2, A3> Tuple3<Iterable<A1>, Iterable<A2>, Iterable<A3>> unzip3(Function1<AbstractFile, Tuple3<A1, A2, A3>> function1) {
        return NoAbstractFile$.MODULE$.unzip3(function1);
    }

    public static <A1, A2> Tuple2<Iterable<A1>, Iterable<A2>> unzip(Function1<AbstractFile, Tuple2<A1, A2>> function1) {
        return NoAbstractFile$.MODULE$.unzip(function1);
    }

    public static <B> Builder<B, Iterable<B>> genericBuilder() {
        return NoAbstractFile$.MODULE$.genericBuilder();
    }

    public static Builder<AbstractFile, Iterable<AbstractFile>> newBuilder() {
        return NoAbstractFile$.MODULE$.newBuilder();
    }

    public static IterableView<AbstractFile, Iterable<AbstractFile>> view(int n, int n2) {
        return NoAbstractFile$.MODULE$.view(n, n2);
    }

    public static Object view() {
        return NoAbstractFile$.MODULE$.view();
    }

    public static boolean canEqual(Object object) {
        return NoAbstractFile$.MODULE$.canEqual(object);
    }

    public static Stream<AbstractFile> toStream() {
        return NoAbstractFile$.MODULE$.toStream();
    }

    public static <B> boolean sameElements(GenIterable<B> genIterable) {
        return NoAbstractFile$.MODULE$.sameElements(genIterable);
    }

    public static <A1, That> That zipWithIndex(CanBuildFrom<Iterable<AbstractFile>, Tuple2<A1, Object>, That> canBuildFrom) {
        return NoAbstractFile$.MODULE$.zipWithIndex(canBuildFrom);
    }

    public static <B, A1, That> That zipAll(GenIterable<B> genIterable, A1 A1, B b, CanBuildFrom<Iterable<AbstractFile>, Tuple2<A1, B>, That> canBuildFrom) {
        return NoAbstractFile$.MODULE$.zipAll(genIterable, A1, b, canBuildFrom);
    }

    public static <A1, B, That> That zip(GenIterable<B> genIterable, CanBuildFrom<Iterable<AbstractFile>, Tuple2<A1, B>, That> canBuildFrom) {
        return NoAbstractFile$.MODULE$.zip(genIterable, canBuildFrom);
    }

    public static <B> void copyToArray(Object object, int n, int n2) {
        NoAbstractFile$.MODULE$.copyToArray(object, n, n2);
    }

    public static Object dropRight(int n) {
        return NoAbstractFile$.MODULE$.dropRight(n);
    }

    public static Object takeRight(int n) {
        return NoAbstractFile$.MODULE$.takeRight(n);
    }

    public static Iterator<Iterable<AbstractFile>> sliding(int n, int n2) {
        return NoAbstractFile$.MODULE$.sliding(n, n2);
    }

    public static Iterator<Iterable<AbstractFile>> sliding(int n) {
        return NoAbstractFile$.MODULE$.sliding(n);
    }

    public static Iterator<Iterable<AbstractFile>> grouped(int n) {
        return NoAbstractFile$.MODULE$.grouped(n);
    }

    public static Object takeWhile(Function1 function1) {
        return NoAbstractFile$.MODULE$.takeWhile(function1);
    }

    public static Object drop(int n) {
        return NoAbstractFile$.MODULE$.drop(n);
    }

    public static Object take(int n) {
        return NoAbstractFile$.MODULE$.take(n);
    }

    public static Object slice(int n, int n2) {
        return NoAbstractFile$.MODULE$.slice(n, n2);
    }

    public static Object head() {
        return NoAbstractFile$.MODULE$.head();
    }

    public static Iterator<AbstractFile> toIterator() {
        return NoAbstractFile$.MODULE$.toIterator();
    }

    public static Iterable<AbstractFile> toIterable() {
        return NoAbstractFile$.MODULE$.toIterable();
    }

    public static <B> B reduceRight(Function2<AbstractFile, B, B> function2) {
        return NoAbstractFile$.MODULE$.reduceRight(function2);
    }

    public static <B> B foldRight(B b, Function2<AbstractFile, B, B> function2) {
        return NoAbstractFile$.MODULE$.foldRight(b, function2);
    }

    public static boolean isEmpty() {
        return NoAbstractFile$.MODULE$.isEmpty();
    }

    public static Option<AbstractFile> find(Function1<AbstractFile, Object> function1) {
        return NoAbstractFile$.MODULE$.find(function1);
    }

    public static boolean exists(Function1<AbstractFile, Object> function1) {
        return NoAbstractFile$.MODULE$.exists(function1);
    }

    public static boolean forall(Function1<AbstractFile, Object> function1) {
        return NoAbstractFile$.MODULE$.forall(function1);
    }

    public static <U> void foreach(Function1<AbstractFile, U> function1) {
        NoAbstractFile$.MODULE$.foreach(function1);
    }

    public static Iterable toCollection(Object object) {
        return NoAbstractFile$.MODULE$.toCollection(object);
    }

    public static Iterable<AbstractFile> thisCollection() {
        return NoAbstractFile$.MODULE$.thisCollection();
    }

    public static Iterable<AbstractFile> seq() {
        return NoAbstractFile$.MODULE$.seq();
    }

    public static GenericCompanion<Iterable> companion() {
        return NoAbstractFile$.MODULE$.companion();
    }

    public static AbstractFile subdirectoryNamed(String string2) {
        return NoAbstractFile$.MODULE$.subdirectoryNamed(string2);
    }

    public static AbstractFile fileNamed(String string2) {
        return NoAbstractFile$.MODULE$.fileNamed(string2);
    }

    public static AbstractFile lookupPathUnchecked(String string2, boolean bl) {
        return NoAbstractFile$.MODULE$.lookupPathUnchecked(string2, bl);
    }

    public static char[] toCharArray() throws IOException {
        return NoAbstractFile$.MODULE$.toCharArray();
    }

    public static URL toURL() {
        return NoAbstractFile$.MODULE$.toURL();
    }

    public static Option<Object> sizeOption() {
        return NoAbstractFile$.MODULE$.sizeOption();
    }

    public static BufferedOutputStream bufferedOutput() {
        return NoAbstractFile$.MODULE$.bufferedOutput();
    }

    public static boolean isClassContainer() {
        return NoAbstractFile$.MODULE$.isClassContainer();
    }

    public static boolean exists() {
        return NoAbstractFile$.MODULE$.exists();
    }

    public static Option<AbstractFile> underlyingSource() {
        return NoAbstractFile$.MODULE$.underlyingSource();
    }

    public static boolean hasExtension(String string2) {
        return NoAbstractFile$.MODULE$.hasExtension(string2);
    }

    public static String canonicalPath() {
        return NoAbstractFile$.MODULE$.canonicalPath();
    }
}

