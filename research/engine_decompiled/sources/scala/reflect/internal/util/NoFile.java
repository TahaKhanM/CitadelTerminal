/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

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
import scala.reflect.internal.util.NoFile$;
import scala.reflect.io.AbstractFile;
import scala.reflect.io.VirtualFile;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001e9Q!\u0001\u0002\t\u0002-\taAT8GS2,'BA\u0002\u0005\u0003\u0011)H/\u001b7\u000b\u0005\u00151\u0011\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\u001dA\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u0013\u0005)1oY1mC\u000e\u0001\u0001C\u0001\u0007\u000e\u001b\u0005\u0011a!\u0002\b\u0003\u0011\u0003y!A\u0002(p\r&dWm\u0005\u0002\u000e!A\u0011\u0011\u0003F\u0007\u0002%)\u00111CB\u0001\u0003S>L!!\u0006\n\u0003\u0017YK'\u000f^;bY\u001aKG.\u001a\u0005\u0006/5!\t\u0001G\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003-\u0001")
public final class NoFile {
    public static Nothing$ lookupNameUnchecked(String string2, boolean bl) {
        return NoFile$.MODULE$.lookupNameUnchecked(string2, bl);
    }

    public static AbstractFile lookupName(String string2, boolean bl) {
        return NoFile$.MODULE$.lookupName(string2, bl);
    }

    public static void delete() {
        NoFile$.MODULE$.delete();
    }

    public static void create() {
        NoFile$.MODULE$.create();
    }

    public static Iterator<AbstractFile> iterator() {
        return NoFile$.MODULE$.iterator();
    }

    public static long lastModified() {
        return NoFile$.MODULE$.lastModified();
    }

    public static boolean isVirtual() {
        return NoFile$.MODULE$.isVirtual();
    }

    public static boolean isDirectory() {
        return NoFile$.MODULE$.isDirectory();
    }

    public static AbstractFile container() {
        return NoFile$.MODULE$.container();
    }

    public static OutputStream output() {
        return NoFile$.MODULE$.output();
    }

    public static InputStream input() {
        return NoFile$.MODULE$.input();
    }

    public static Option<Object> sizeOption() {
        return NoFile$.MODULE$.sizeOption();
    }

    public static File file() {
        return NoFile$.MODULE$.file();
    }

    public static VirtualFile absolute() {
        return NoFile$.MODULE$.absolute();
    }

    public static boolean equals(Object object) {
        return NoFile$.MODULE$.equals(object);
    }

    public static int hashCode() {
        return NoFile$.MODULE$.hashCode();
    }

    public static String path() {
        return NoFile$.MODULE$.path();
    }

    public static String name() {
        return NoFile$.MODULE$.name();
    }

    public static StringBuilder addString(StringBuilder stringBuilder) {
        return NoFile$.MODULE$.addString(stringBuilder);
    }

    public static StringBuilder addString(StringBuilder stringBuilder, String string2) {
        return NoFile$.MODULE$.addString(stringBuilder, string2);
    }

    public static StringBuilder addString(StringBuilder stringBuilder, String string2, String string3, String string4) {
        return NoFile$.MODULE$.addString(stringBuilder, string2, string3, string4);
    }

    public static String mkString() {
        return NoFile$.MODULE$.mkString();
    }

    public static String mkString(String string2) {
        return NoFile$.MODULE$.mkString(string2);
    }

    public static String mkString(String string2, String string3, String string4) {
        return NoFile$.MODULE$.mkString(string2, string3, string4);
    }

    public static <T, U> Map<T, U> toMap(Predef$.less.colon.less<AbstractFile, Tuple2<T, U>> less2) {
        return NoFile$.MODULE$.toMap((Predef$.less.colon.less)less2);
    }

    public static Vector<AbstractFile> toVector() {
        return NoFile$.MODULE$.toVector();
    }

    public static <B> Set<B> toSet() {
        return NoFile$.MODULE$.toSet();
    }

    public static <B> Buffer<B> toBuffer() {
        return NoFile$.MODULE$.toBuffer();
    }

    public static IndexedSeq<AbstractFile> toIndexedSeq() {
        return NoFile$.MODULE$.toIndexedSeq();
    }

    public static Seq<AbstractFile> toSeq() {
        return NoFile$.MODULE$.toSeq();
    }

    public static List<AbstractFile> toList() {
        return NoFile$.MODULE$.toList();
    }

    public static Object toArray(ClassTag classTag) {
        return NoFile$.MODULE$.toArray(classTag);
    }

    public static <B> void copyToArray(Object object) {
        NoFile$.MODULE$.copyToArray(object);
    }

    public static <B> void copyToArray(Object object, int n) {
        NoFile$.MODULE$.copyToArray(object, n);
    }

    public static <B> void copyToBuffer(Buffer<B> buffer) {
        NoFile$.MODULE$.copyToBuffer(buffer);
    }

    public static Object minBy(Function1 function1, Ordering ordering) {
        return NoFile$.MODULE$.minBy(function1, ordering);
    }

    public static Object maxBy(Function1 function1, Ordering ordering) {
        return NoFile$.MODULE$.maxBy(function1, ordering);
    }

    public static Object max(Ordering ordering) {
        return NoFile$.MODULE$.max(ordering);
    }

    public static Object min(Ordering ordering) {
        return NoFile$.MODULE$.min(ordering);
    }

    public static <B> B product(Numeric<B> numeric) {
        return NoFile$.MODULE$.product(numeric);
    }

    public static <B> B sum(Numeric<B> numeric) {
        return NoFile$.MODULE$.sum(numeric);
    }

    public static <B> B aggregate(Function0<B> function0, Function2<B, AbstractFile, B> function2, Function2<B, B, B> function22) {
        return NoFile$.MODULE$.aggregate(function0, function2, function22);
    }

    public static <A1> A1 fold(A1 A1, Function2<A1, A1, A1> function2) {
        return NoFile$.MODULE$.fold(A1, function2);
    }

    public static <A1> Option<A1> reduceOption(Function2<A1, A1, A1> function2) {
        return NoFile$.MODULE$.reduceOption(function2);
    }

    public static <A1> A1 reduce(Function2<A1, A1, A1> function2) {
        return NoFile$.MODULE$.reduce(function2);
    }

    public static <B> Option<B> reduceRightOption(Function2<AbstractFile, B, B> function2) {
        return NoFile$.MODULE$.reduceRightOption(function2);
    }

    public static <B> Option<B> reduceLeftOption(Function2<B, AbstractFile, B> function2) {
        return NoFile$.MODULE$.reduceLeftOption(function2);
    }

    public static <B> B reduceLeft(Function2<B, AbstractFile, B> function2) {
        return NoFile$.MODULE$.reduceLeft(function2);
    }

    public static <B> B foldLeft(B b, Function2<B, AbstractFile, B> function2) {
        return NoFile$.MODULE$.foldLeft(b, function2);
    }

    public static <B> B $colon$bslash(B b, Function2<AbstractFile, B, B> function2) {
        return NoFile$.MODULE$.$colon$bslash(b, function2);
    }

    public static <B> B $div$colon(B b, Function2<B, AbstractFile, B> function2) {
        return NoFile$.MODULE$.$div$colon(b, function2);
    }

    public static <B> Option<B> collectFirst(PartialFunction<AbstractFile, B> partialFunction) {
        return NoFile$.MODULE$.collectFirst(partialFunction);
    }

    public static int count(Function1<AbstractFile, Object> function1) {
        return NoFile$.MODULE$.count(function1);
    }

    public static boolean nonEmpty() {
        return NoFile$.MODULE$.nonEmpty();
    }

    public static int size() {
        return NoFile$.MODULE$.size();
    }

    public static List<AbstractFile> reversed() {
        return NoFile$.MODULE$.reversed();
    }

    public static Parallel par() {
        return NoFile$.MODULE$.par();
    }

    public static FilterMonadic<AbstractFile, Iterable<AbstractFile>> withFilter(Function1<AbstractFile, Object> function1) {
        return NoFile$.MODULE$.withFilter(function1);
    }

    public static String stringPrefix() {
        return NoFile$.MODULE$.stringPrefix();
    }

    public static <Col> Col to(CanBuildFrom<Nothing$, AbstractFile, Col> canBuildFrom) {
        return NoFile$.MODULE$.to(canBuildFrom);
    }

    public static Traversable<AbstractFile> toTraversable() {
        return NoFile$.MODULE$.toTraversable();
    }

    public static Iterator<Iterable<AbstractFile>> inits() {
        return NoFile$.MODULE$.inits();
    }

    public static Iterator<Iterable<AbstractFile>> tails() {
        return NoFile$.MODULE$.tails();
    }

    public static Tuple2<Iterable<AbstractFile>, Iterable<AbstractFile>> splitAt(int n) {
        return NoFile$.MODULE$.splitAt(n);
    }

    public static Tuple2<Iterable<AbstractFile>, Iterable<AbstractFile>> span(Function1<AbstractFile, Object> function1) {
        return NoFile$.MODULE$.span(function1);
    }

    public static Object dropWhile(Function1 function1) {
        return NoFile$.MODULE$.dropWhile(function1);
    }

    public static Object init() {
        return NoFile$.MODULE$.init();
    }

    public static Option<AbstractFile> lastOption() {
        return NoFile$.MODULE$.lastOption();
    }

    public static Object last() {
        return NoFile$.MODULE$.last();
    }

    public static Object tail() {
        return NoFile$.MODULE$.tail();
    }

    public static Option<AbstractFile> headOption() {
        return NoFile$.MODULE$.headOption();
    }

    public static <B, That> That scanRight(B b, Function2<AbstractFile, B, B> function2, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoFile$.MODULE$.scanRight(b, function2, canBuildFrom);
    }

    public static <B, That> That scanLeft(B b, Function2<B, AbstractFile, B> function2, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoFile$.MODULE$.scanLeft(b, function2, canBuildFrom);
    }

    public static <B, That> That scan(B b, Function2<B, B, B> function2, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoFile$.MODULE$.scan(b, function2, canBuildFrom);
    }

    public static <K> Map<K, Iterable<AbstractFile>> groupBy(Function1<AbstractFile, K> function1) {
        return NoFile$.MODULE$.groupBy((Function1)function1);
    }

    public static Tuple2<Iterable<AbstractFile>, Iterable<AbstractFile>> partition(Function1<AbstractFile, Object> function1) {
        return NoFile$.MODULE$.partition(function1);
    }

    public static <B, That> That collect(PartialFunction<AbstractFile, B> partialFunction, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoFile$.MODULE$.collect(partialFunction, canBuildFrom);
    }

    public static Object filterNot(Function1 function1) {
        return NoFile$.MODULE$.filterNot(function1);
    }

    public static Object filter(Function1 function1) {
        return NoFile$.MODULE$.filter(function1);
    }

    public static <B, That> That flatMap(Function1<AbstractFile, GenTraversableOnce<B>> function1, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoFile$.MODULE$.flatMap(function1, canBuildFrom);
    }

    public static <B, That> That map(Function1<AbstractFile, B> function1, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoFile$.MODULE$.map(function1, canBuildFrom);
    }

    public static <B, That> That $plus$plus$colon(Traversable<B> traversable, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoFile$.MODULE$.$plus$plus$colon(traversable, canBuildFrom);
    }

    public static <B, That> That $plus$plus$colon(TraversableOnce<B> traversableOnce, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoFile$.MODULE$.$plus$plus$colon(traversableOnce, canBuildFrom);
    }

    public static <B, That> That $plus$plus(GenTraversableOnce<B> genTraversableOnce, CanBuildFrom<Iterable<AbstractFile>, B, That> canBuildFrom) {
        return NoFile$.MODULE$.$plus$plus(genTraversableOnce, canBuildFrom);
    }

    public static boolean hasDefiniteSize() {
        return NoFile$.MODULE$.hasDefiniteSize();
    }

    public static Combiner<AbstractFile, ParIterable<AbstractFile>> parCombiner() {
        return NoFile$.MODULE$.parCombiner();
    }

    public static boolean isTraversableAgain() {
        return NoFile$.MODULE$.isTraversableAgain();
    }

    public static Object repr() {
        return NoFile$.MODULE$.repr();
    }

    public static GenTraversable transpose(Function1 function1) {
        return NoFile$.MODULE$.transpose(function1);
    }

    public static GenTraversable flatten(Function1 function1) {
        return NoFile$.MODULE$.flatten(function1);
    }

    public static <A1, A2, A3> Tuple3<Iterable<A1>, Iterable<A2>, Iterable<A3>> unzip3(Function1<AbstractFile, Tuple3<A1, A2, A3>> function1) {
        return NoFile$.MODULE$.unzip3(function1);
    }

    public static <A1, A2> Tuple2<Iterable<A1>, Iterable<A2>> unzip(Function1<AbstractFile, Tuple2<A1, A2>> function1) {
        return NoFile$.MODULE$.unzip(function1);
    }

    public static <B> Builder<B, Iterable<B>> genericBuilder() {
        return NoFile$.MODULE$.genericBuilder();
    }

    public static Builder<AbstractFile, Iterable<AbstractFile>> newBuilder() {
        return NoFile$.MODULE$.newBuilder();
    }

    public static IterableView<AbstractFile, Iterable<AbstractFile>> view(int n, int n2) {
        return NoFile$.MODULE$.view(n, n2);
    }

    public static Object view() {
        return NoFile$.MODULE$.view();
    }

    public static boolean canEqual(Object object) {
        return NoFile$.MODULE$.canEqual(object);
    }

    public static Stream<AbstractFile> toStream() {
        return NoFile$.MODULE$.toStream();
    }

    public static <B> boolean sameElements(GenIterable<B> genIterable) {
        return NoFile$.MODULE$.sameElements(genIterable);
    }

    public static <A1, That> That zipWithIndex(CanBuildFrom<Iterable<AbstractFile>, Tuple2<A1, Object>, That> canBuildFrom) {
        return NoFile$.MODULE$.zipWithIndex(canBuildFrom);
    }

    public static <B, A1, That> That zipAll(GenIterable<B> genIterable, A1 A1, B b, CanBuildFrom<Iterable<AbstractFile>, Tuple2<A1, B>, That> canBuildFrom) {
        return NoFile$.MODULE$.zipAll(genIterable, A1, b, canBuildFrom);
    }

    public static <A1, B, That> That zip(GenIterable<B> genIterable, CanBuildFrom<Iterable<AbstractFile>, Tuple2<A1, B>, That> canBuildFrom) {
        return NoFile$.MODULE$.zip(genIterable, canBuildFrom);
    }

    public static <B> void copyToArray(Object object, int n, int n2) {
        NoFile$.MODULE$.copyToArray(object, n, n2);
    }

    public static Object dropRight(int n) {
        return NoFile$.MODULE$.dropRight(n);
    }

    public static Object takeRight(int n) {
        return NoFile$.MODULE$.takeRight(n);
    }

    public static Iterator<Iterable<AbstractFile>> sliding(int n, int n2) {
        return NoFile$.MODULE$.sliding(n, n2);
    }

    public static Iterator<Iterable<AbstractFile>> sliding(int n) {
        return NoFile$.MODULE$.sliding(n);
    }

    public static Iterator<Iterable<AbstractFile>> grouped(int n) {
        return NoFile$.MODULE$.grouped(n);
    }

    public static Object takeWhile(Function1 function1) {
        return NoFile$.MODULE$.takeWhile(function1);
    }

    public static Object drop(int n) {
        return NoFile$.MODULE$.drop(n);
    }

    public static Object take(int n) {
        return NoFile$.MODULE$.take(n);
    }

    public static Object slice(int n, int n2) {
        return NoFile$.MODULE$.slice(n, n2);
    }

    public static Object head() {
        return NoFile$.MODULE$.head();
    }

    public static Iterator<AbstractFile> toIterator() {
        return NoFile$.MODULE$.toIterator();
    }

    public static Iterable<AbstractFile> toIterable() {
        return NoFile$.MODULE$.toIterable();
    }

    public static <B> B reduceRight(Function2<AbstractFile, B, B> function2) {
        return NoFile$.MODULE$.reduceRight(function2);
    }

    public static <B> B foldRight(B b, Function2<AbstractFile, B, B> function2) {
        return NoFile$.MODULE$.foldRight(b, function2);
    }

    public static boolean isEmpty() {
        return NoFile$.MODULE$.isEmpty();
    }

    public static Option<AbstractFile> find(Function1<AbstractFile, Object> function1) {
        return NoFile$.MODULE$.find(function1);
    }

    public static boolean exists(Function1<AbstractFile, Object> function1) {
        return NoFile$.MODULE$.exists(function1);
    }

    public static boolean forall(Function1<AbstractFile, Object> function1) {
        return NoFile$.MODULE$.forall(function1);
    }

    public static <U> void foreach(Function1<AbstractFile, U> function1) {
        NoFile$.MODULE$.foreach(function1);
    }

    public static Iterable toCollection(Object object) {
        return NoFile$.MODULE$.toCollection(object);
    }

    public static Iterable<AbstractFile> thisCollection() {
        return NoFile$.MODULE$.thisCollection();
    }

    public static Iterable<AbstractFile> seq() {
        return NoFile$.MODULE$.seq();
    }

    public static GenericCompanion<Iterable> companion() {
        return NoFile$.MODULE$.companion();
    }

    public static String toString() {
        return NoFile$.MODULE$.toString();
    }

    public static AbstractFile subdirectoryNamed(String string2) {
        return NoFile$.MODULE$.subdirectoryNamed(string2);
    }

    public static AbstractFile fileNamed(String string2) {
        return NoFile$.MODULE$.fileNamed(string2);
    }

    public static AbstractFile lookupPathUnchecked(String string2, boolean bl) {
        return NoFile$.MODULE$.lookupPathUnchecked(string2, bl);
    }

    public static byte[] toByteArray() throws IOException {
        return NoFile$.MODULE$.toByteArray();
    }

    public static char[] toCharArray() throws IOException {
        return NoFile$.MODULE$.toCharArray();
    }

    public static URL toURL() {
        return NoFile$.MODULE$.toURL();
    }

    public static BufferedOutputStream bufferedOutput() {
        return NoFile$.MODULE$.bufferedOutput();
    }

    public static boolean isClassContainer() {
        return NoFile$.MODULE$.isClassContainer();
    }

    public static boolean exists() {
        return NoFile$.MODULE$.exists();
    }

    public static Option<AbstractFile> underlyingSource() {
        return NoFile$.MODULE$.underlyingSource();
    }

    public static boolean hasExtension(String string2) {
        return NoFile$.MODULE$.hasExtension(string2);
    }

    public static String canonicalPath() {
        return NoFile$.MODULE$.canonicalPath();
    }
}

