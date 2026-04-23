/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.convert.WrapAsJava;
import scala.collection.convert.WrapAsJava$class;
import scala.collection.convert.WrapAsScala;
import scala.collection.convert.WrapAsScala$class;
import scala.collection.mutable.Buffer;

public final class JavaConversions$
implements WrapAsScala,
WrapAsJava {
    public static final JavaConversions$ MODULE$;

    static {
        new JavaConversions$();
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

    @Override
    public <A> Iterator<A> asScalaIterator(java.util.Iterator<A> it) {
        return WrapAsScala$class.asScalaIterator(this, it);
    }

    @Override
    public <A> Iterator<A> enumerationAsScalaIterator(Enumeration<A> i) {
        return WrapAsScala$class.enumerationAsScalaIterator(this, i);
    }

    @Override
    public <A> Iterable<A> iterableAsScalaIterable(java.lang.Iterable<A> i) {
        return WrapAsScala$class.iterableAsScalaIterable(this, i);
    }

    @Override
    public <A> Iterable<A> collectionAsScalaIterable(Collection<A> i) {
        return WrapAsScala$class.collectionAsScalaIterable(this, i);
    }

    @Override
    public <A> Buffer<A> asScalaBuffer(List<A> l) {
        return WrapAsScala$class.asScalaBuffer(this, l);
    }

    @Override
    public <A> scala.collection.mutable.Set<A> asScalaSet(java.util.Set<A> s2) {
        return WrapAsScala$class.asScalaSet(this, s2);
    }

    @Override
    public <A, B> scala.collection.mutable.Map<A, B> mapAsScalaMap(java.util.Map<A, B> m) {
        return WrapAsScala$class.mapAsScalaMap(this, m);
    }

    @Override
    public <A, B> scala.collection.concurrent.Map<A, B> mapAsScalaConcurrentMap(ConcurrentMap<A, B> m) {
        return WrapAsScala$class.mapAsScalaConcurrentMap(this, m);
    }

    @Override
    public <A, B> scala.collection.mutable.Map<A, B> dictionaryAsScalaMap(Dictionary<A, B> p) {
        return WrapAsScala$class.dictionaryAsScalaMap(this, p);
    }

    @Override
    public scala.collection.mutable.Map<String, String> propertiesAsScalaMap(Properties p) {
        return WrapAsScala$class.propertiesAsScalaMap(this, p);
    }

    private JavaConversions$() {
        MODULE$ = this;
        WrapAsScala$class.$init$(this);
        WrapAsJava$class.$init$(this);
    }
}

