/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.TraversableProxyLike;
import scala.collection.TraversableView;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;

public abstract class TraversableProxyLike$class {
    public static void foreach(TraversableProxyLike $this, Function1 f) {
        $this.self().foreach(f);
    }

    public static boolean isEmpty(TraversableProxyLike $this) {
        return $this.self().isEmpty();
    }

    public static boolean nonEmpty(TraversableProxyLike $this) {
        return $this.self().nonEmpty();
    }

    public static int size(TraversableProxyLike $this) {
        return $this.self().size();
    }

    public static boolean hasDefiniteSize(TraversableProxyLike $this) {
        return $this.self().hasDefiniteSize();
    }

    public static Object $plus$plus(TraversableProxyLike $this, GenTraversableOnce xs, CanBuildFrom bf) {
        return $this.self().$plus$plus(xs, bf);
    }

    public static Object map(TraversableProxyLike $this, Function1 f, CanBuildFrom bf) {
        return $this.self().map(f, bf);
    }

    public static Object flatMap(TraversableProxyLike $this, Function1 f, CanBuildFrom bf) {
        return $this.self().flatMap(f, bf);
    }

    public static Traversable filter(TraversableProxyLike $this, Function1 p) {
        return (Traversable)$this.self().filter(p);
    }

    public static Traversable filterNot(TraversableProxyLike $this, Function1 p) {
        return (Traversable)$this.self().filterNot(p);
    }

    public static Object collect(TraversableProxyLike $this, PartialFunction pf, CanBuildFrom bf) {
        return $this.self().collect(pf, bf);
    }

    public static Tuple2 partition(TraversableProxyLike $this, Function1 p) {
        return $this.self().partition(p);
    }

    public static Map groupBy(TraversableProxyLike $this, Function1 f) {
        return $this.self().groupBy(f);
    }

    public static boolean forall(TraversableProxyLike $this, Function1 p) {
        return $this.self().forall(p);
    }

    public static boolean exists(TraversableProxyLike $this, Function1 p) {
        return $this.self().exists(p);
    }

    public static int count(TraversableProxyLike $this, Function1 p) {
        return $this.self().count(p);
    }

    public static Option find(TraversableProxyLike $this, Function1 p) {
        return $this.self().find(p);
    }

    public static Object foldLeft(TraversableProxyLike $this, Object z, Function2 op) {
        return $this.self().foldLeft((Object)z, op);
    }

    public static Object $div$colon(TraversableProxyLike $this, Object z, Function2 op) {
        return $this.self().$div$colon((Object)z, op);
    }

    public static Object foldRight(TraversableProxyLike $this, Object z, Function2 op) {
        return $this.self().foldRight((Object)z, op);
    }

    public static Object $colon$bslash(TraversableProxyLike $this, Object z, Function2 op) {
        return $this.self().$colon$bslash((Object)z, op);
    }

    public static Object reduceLeft(TraversableProxyLike $this, Function2 op) {
        return $this.self().reduceLeft(op);
    }

    public static Option reduceLeftOption(TraversableProxyLike $this, Function2 op) {
        return $this.self().reduceLeftOption(op);
    }

    public static Object reduceRight(TraversableProxyLike $this, Function2 op) {
        return $this.self().reduceRight(op);
    }

    public static Option reduceRightOption(TraversableProxyLike $this, Function2 op) {
        return $this.self().reduceRightOption(op);
    }

    public static Object scanLeft(TraversableProxyLike $this, Object z, Function2 op, CanBuildFrom bf) {
        return $this.self().scanLeft((Object)z, op, bf);
    }

    public static Object scanRight(TraversableProxyLike $this, Object z, Function2 op, CanBuildFrom bf) {
        return $this.self().scanRight((Object)z, op, bf);
    }

    public static Object sum(TraversableProxyLike $this, Numeric num) {
        return $this.self().sum(num);
    }

    public static Object product(TraversableProxyLike $this, Numeric num) {
        return $this.self().product(num);
    }

    public static Object min(TraversableProxyLike $this, Ordering cmp) {
        return $this.self().min(cmp);
    }

    public static Object max(TraversableProxyLike $this, Ordering cmp) {
        return $this.self().max(cmp);
    }

    public static Object head(TraversableProxyLike $this) {
        return $this.self().head();
    }

    public static Option headOption(TraversableProxyLike $this) {
        return $this.self().headOption();
    }

    public static Traversable tail(TraversableProxyLike $this) {
        return (Traversable)$this.self().tail();
    }

    public static Object last(TraversableProxyLike $this) {
        return $this.self().last();
    }

    public static Option lastOption(TraversableProxyLike $this) {
        return $this.self().lastOption();
    }

    public static Traversable init(TraversableProxyLike $this) {
        return (Traversable)$this.self().init();
    }

    public static Traversable take(TraversableProxyLike $this, int n) {
        return (Traversable)$this.self().take(n);
    }

    public static Traversable drop(TraversableProxyLike $this, int n) {
        return (Traversable)$this.self().drop(n);
    }

    public static Traversable slice(TraversableProxyLike $this, int from2, int until2) {
        return (Traversable)$this.self().slice(from2, until2);
    }

    public static Traversable takeWhile(TraversableProxyLike $this, Function1 p) {
        return (Traversable)$this.self().takeWhile(p);
    }

    public static Traversable dropWhile(TraversableProxyLike $this, Function1 p) {
        return (Traversable)$this.self().dropWhile(p);
    }

    public static Tuple2 span(TraversableProxyLike $this, Function1 p) {
        return $this.self().span(p);
    }

    public static Tuple2 splitAt(TraversableProxyLike $this, int n) {
        return $this.self().splitAt(n);
    }

    public static void copyToBuffer(TraversableProxyLike $this, Buffer dest) {
        $this.self().copyToBuffer(dest);
    }

    public static void copyToArray(TraversableProxyLike $this, Object xs, int start, int len) {
        $this.self().copyToArray(xs, start, len);
    }

    public static void copyToArray(TraversableProxyLike $this, Object xs, int start) {
        $this.self().copyToArray(xs, start);
    }

    public static void copyToArray(TraversableProxyLike $this, Object xs) {
        $this.self().copyToArray(xs);
    }

    public static Object toArray(TraversableProxyLike $this, ClassTag evidence$1) {
        return $this.self().toArray(evidence$1);
    }

    public static List toList(TraversableProxyLike $this) {
        return $this.self().toList();
    }

    public static Iterable toIterable(TraversableProxyLike $this) {
        return $this.self().toIterable();
    }

    public static Seq toSeq(TraversableProxyLike $this) {
        return $this.self().toSeq();
    }

    public static IndexedSeq toIndexedSeq(TraversableProxyLike $this) {
        return $this.self().toIndexedSeq();
    }

    public static Buffer toBuffer(TraversableProxyLike $this) {
        return $this.self().toBuffer();
    }

    public static Stream toStream(TraversableProxyLike $this) {
        return $this.self().toStream();
    }

    public static Set toSet(TraversableProxyLike $this) {
        return $this.self().toSet();
    }

    public static Map toMap(TraversableProxyLike $this, Predef$.less.colon.less ev) {
        return $this.self().toMap(ev);
    }

    public static Traversable toTraversable(TraversableProxyLike $this) {
        return $this.self().toTraversable();
    }

    public static Iterator toIterator(TraversableProxyLike $this) {
        return $this.self().toIterator();
    }

    public static String mkString(TraversableProxyLike $this, String start, String sep, String end) {
        return $this.self().mkString(start, sep, end);
    }

    public static String mkString(TraversableProxyLike $this, String sep) {
        return $this.self().mkString(sep);
    }

    public static String mkString(TraversableProxyLike $this) {
        return $this.self().mkString();
    }

    public static StringBuilder addString(TraversableProxyLike $this, StringBuilder b, String start, String sep, String end) {
        return $this.self().addString(b, start, sep, end);
    }

    public static StringBuilder addString(TraversableProxyLike $this, StringBuilder b, String sep) {
        return $this.self().addString(b, sep);
    }

    public static StringBuilder addString(TraversableProxyLike $this, StringBuilder b) {
        return $this.self().addString(b);
    }

    public static String stringPrefix(TraversableProxyLike $this) {
        return $this.self().stringPrefix();
    }

    public static TraversableView view(TraversableProxyLike $this) {
        return $this.self().view();
    }

    public static TraversableView view(TraversableProxyLike $this, int from2, int until2) {
        return $this.self().view(from2, until2);
    }

    public static void $init$(TraversableProxyLike $this) {
    }
}

