/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Predef$;
import scala.collection.Iterable;
import scala.collection.Seq;
import scala.collection.generic.TraversableForwarder;
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

public abstract class TraversableForwarder$class {
    public static void foreach(TraversableForwarder $this, Function1 f) {
        $this.underlying().foreach(f);
    }

    public static boolean isEmpty(TraversableForwarder $this) {
        return $this.underlying().isEmpty();
    }

    public static boolean nonEmpty(TraversableForwarder $this) {
        return $this.underlying().nonEmpty();
    }

    public static int size(TraversableForwarder $this) {
        return $this.underlying().size();
    }

    public static boolean hasDefiniteSize(TraversableForwarder $this) {
        return $this.underlying().hasDefiniteSize();
    }

    public static boolean forall(TraversableForwarder $this, Function1 p) {
        return $this.underlying().forall(p);
    }

    public static boolean exists(TraversableForwarder $this, Function1 p) {
        return $this.underlying().exists(p);
    }

    public static int count(TraversableForwarder $this, Function1 p) {
        return $this.underlying().count(p);
    }

    public static Option find(TraversableForwarder $this, Function1 p) {
        return $this.underlying().find(p);
    }

    public static Object foldLeft(TraversableForwarder $this, Object z, Function2 op) {
        return $this.underlying().foldLeft(z, op);
    }

    public static Object $div$colon(TraversableForwarder $this, Object z, Function2 op) {
        return $this.underlying().$div$colon(z, op);
    }

    public static Object foldRight(TraversableForwarder $this, Object z, Function2 op) {
        return $this.underlying().foldRight(z, op);
    }

    public static Object $colon$bslash(TraversableForwarder $this, Object z, Function2 op) {
        return $this.underlying().$colon$bslash(z, op);
    }

    public static Object reduceLeft(TraversableForwarder $this, Function2 op) {
        return $this.underlying().reduceLeft(op);
    }

    public static Option reduceLeftOption(TraversableForwarder $this, Function2 op) {
        return $this.underlying().reduceLeftOption(op);
    }

    public static Object reduceRight(TraversableForwarder $this, Function2 op) {
        return $this.underlying().reduceRight(op);
    }

    public static Option reduceRightOption(TraversableForwarder $this, Function2 op) {
        return $this.underlying().reduceRightOption(op);
    }

    public static Object sum(TraversableForwarder $this, Numeric num) {
        return $this.underlying().sum(num);
    }

    public static Object product(TraversableForwarder $this, Numeric num) {
        return $this.underlying().product(num);
    }

    public static Object min(TraversableForwarder $this, Ordering cmp) {
        return $this.underlying().min(cmp);
    }

    public static Object max(TraversableForwarder $this, Ordering cmp) {
        return $this.underlying().max(cmp);
    }

    public static Object head(TraversableForwarder $this) {
        return $this.underlying().head();
    }

    public static Option headOption(TraversableForwarder $this) {
        return $this.underlying().headOption();
    }

    public static Object last(TraversableForwarder $this) {
        return $this.underlying().last();
    }

    public static Option lastOption(TraversableForwarder $this) {
        return $this.underlying().lastOption();
    }

    public static void copyToBuffer(TraversableForwarder $this, Buffer dest) {
        $this.underlying().copyToBuffer(dest);
    }

    public static void copyToArray(TraversableForwarder $this, Object xs, int start, int len) {
        $this.underlying().copyToArray(xs, start, len);
    }

    public static void copyToArray(TraversableForwarder $this, Object xs, int start) {
        $this.underlying().copyToArray(xs, start);
    }

    public static void copyToArray(TraversableForwarder $this, Object xs) {
        $this.underlying().copyToArray(xs);
    }

    public static Object toArray(TraversableForwarder $this, ClassTag evidence$1) {
        return $this.underlying().toArray(evidence$1);
    }

    public static List toList(TraversableForwarder $this) {
        return $this.underlying().toList();
    }

    public static Iterable toIterable(TraversableForwarder $this) {
        return $this.underlying().toIterable();
    }

    public static Seq toSeq(TraversableForwarder $this) {
        return $this.underlying().toSeq();
    }

    public static IndexedSeq toIndexedSeq(TraversableForwarder $this) {
        return $this.underlying().toIndexedSeq();
    }

    public static Buffer toBuffer(TraversableForwarder $this) {
        return $this.underlying().toBuffer();
    }

    public static Stream toStream(TraversableForwarder $this) {
        return $this.underlying().toStream();
    }

    public static Set toSet(TraversableForwarder $this) {
        return $this.underlying().toSet();
    }

    public static Map toMap(TraversableForwarder $this, Predef$.less.colon.less ev) {
        return $this.underlying().toMap(ev);
    }

    public static String mkString(TraversableForwarder $this, String start, String sep, String end) {
        return $this.underlying().mkString(start, sep, end);
    }

    public static String mkString(TraversableForwarder $this, String sep) {
        return $this.underlying().mkString(sep);
    }

    public static String mkString(TraversableForwarder $this) {
        return $this.underlying().mkString();
    }

    public static StringBuilder addString(TraversableForwarder $this, StringBuilder b, String start, String sep, String end) {
        return $this.underlying().addString(b, start, sep, end);
    }

    public static StringBuilder addString(TraversableForwarder $this, StringBuilder b, String sep) {
        return $this.underlying().addString(b, sep);
    }

    public static StringBuilder addString(TraversableForwarder $this, StringBuilder b) {
        return $this.underlying().addString(b);
    }

    public static void $init$(TraversableForwarder $this) {
    }
}

