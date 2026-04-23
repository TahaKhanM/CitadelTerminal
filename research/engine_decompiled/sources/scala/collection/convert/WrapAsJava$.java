/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.convert;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.convert.WrapAsJava;
import scala.collection.convert.WrapAsJava$class;
import scala.collection.mutable.Buffer;

public final class WrapAsJava$
implements WrapAsJava {
    public static final WrapAsJava$ MODULE$;

    static {
        new WrapAsJava$();
    }

    @Override
    public <A> java.util.Iterator<A> asJavaIterator(Iterator<A> it) {
        return WrapAsJava$class.asJavaIterator(this, it);
    }

    @Override
    public <A> Enumeration<A> asJavaEnumeration(Iterator<A> it) {
        return WrapAsJava$class.asJavaEnumeration(this, it);
    }

    @Override
    public <A> java.lang.Iterable<A> asJavaIterable(Iterable<A> i) {
        return WrapAsJava$class.asJavaIterable(this, i);
    }

    @Override
    public <A> Collection<A> asJavaCollection(Iterable<A> it) {
        return WrapAsJava$class.asJavaCollection(this, it);
    }

    @Override
    public <A> List<A> bufferAsJavaList(Buffer<A> b) {
        return WrapAsJava$class.bufferAsJavaList(this, b);
    }

    @Override
    public <A> List<A> mutableSeqAsJavaList(scala.collection.mutable.Seq<A> seq) {
        return WrapAsJava$class.mutableSeqAsJavaList(this, seq);
    }

    @Override
    public <A> List<A> seqAsJavaList(Seq<A> seq) {
        return WrapAsJava$class.seqAsJavaList(this, seq);
    }

    @Override
    public <A> java.util.Set<A> mutableSetAsJavaSet(scala.collection.mutable.Set<A> s2) {
        return WrapAsJava$class.mutableSetAsJavaSet(this, s2);
    }

    @Override
    public <A> java.util.Set<A> setAsJavaSet(Set<A> s2) {
        return WrapAsJava$class.setAsJavaSet(this, s2);
    }

    @Override
    public <A, B> java.util.Map<A, B> mutableMapAsJavaMap(scala.collection.mutable.Map<A, B> m) {
        return WrapAsJava$class.mutableMapAsJavaMap(this, m);
    }

    @Override
    public <A, B> Dictionary<A, B> asJavaDictionary(scala.collection.mutable.Map<A, B> m) {
        return WrapAsJava$class.asJavaDictionary(this, m);
    }

    @Override
    public <A, B> java.util.Map<A, B> mapAsJavaMap(Map<A, B> m) {
        return WrapAsJava$class.mapAsJavaMap(this, m);
    }

    @Override
    public <A, B> ConcurrentMap<A, B> mapAsJavaConcurrentMap(scala.collection.concurrent.Map<A, B> m) {
        return WrapAsJava$class.mapAsJavaConcurrentMap(this, m);
    }

    private WrapAsJava$() {
        MODULE$ = this;
        WrapAsJava$class.$init$(this);
    }
}

