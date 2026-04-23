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
import scala.collection.convert.DecorateAsJava;
import scala.collection.convert.DecorateAsJava$class;
import scala.collection.convert.DecorateAsScala;
import scala.collection.convert.DecorateAsScala$class;
import scala.collection.convert.Decorators;
import scala.collection.mutable.Buffer;

public final class JavaConverters$
implements DecorateAsJava,
DecorateAsScala {
    public static final JavaConverters$ MODULE$;

    static {
        new JavaConverters$();
    }

    @Override
    public <A> Decorators.AsScala<Iterator<A>> asScalaIteratorConverter(java.util.Iterator<A> i) {
        return DecorateAsScala$class.asScalaIteratorConverter(this, i);
    }

    @Override
    public <A> Decorators.AsScala<Iterator<A>> enumerationAsScalaIteratorConverter(Enumeration<A> i) {
        return DecorateAsScala$class.enumerationAsScalaIteratorConverter(this, i);
    }

    @Override
    public <A> Decorators.AsScala<Iterable<A>> iterableAsScalaIterableConverter(java.lang.Iterable<A> i) {
        return DecorateAsScala$class.iterableAsScalaIterableConverter(this, i);
    }

    @Override
    public <A> Decorators.AsScala<Iterable<A>> collectionAsScalaIterableConverter(Collection<A> i) {
        return DecorateAsScala$class.collectionAsScalaIterableConverter(this, i);
    }

    @Override
    public <A> Decorators.AsScala<Buffer<A>> asScalaBufferConverter(List<A> l) {
        return DecorateAsScala$class.asScalaBufferConverter(this, l);
    }

    @Override
    public <A> Decorators.AsScala<scala.collection.mutable.Set<A>> asScalaSetConverter(java.util.Set<A> s2) {
        return DecorateAsScala$class.asScalaSetConverter(this, s2);
    }

    @Override
    public <A, B> Decorators.AsScala<scala.collection.mutable.Map<A, B>> mapAsScalaMapConverter(java.util.Map<A, B> m) {
        return DecorateAsScala$class.mapAsScalaMapConverter(this, m);
    }

    @Override
    public <A, B> Decorators.AsScala<scala.collection.concurrent.Map<A, B>> mapAsScalaConcurrentMapConverter(ConcurrentMap<A, B> m) {
        return DecorateAsScala$class.mapAsScalaConcurrentMapConverter(this, m);
    }

    @Override
    public <A, B> Decorators.AsScala<scala.collection.mutable.Map<A, B>> dictionaryAsScalaMapConverter(Dictionary<A, B> p) {
        return DecorateAsScala$class.dictionaryAsScalaMapConverter(this, p);
    }

    @Override
    public Decorators.AsScala<scala.collection.mutable.Map<String, String>> propertiesAsScalaMapConverter(Properties p) {
        return DecorateAsScala$class.propertiesAsScalaMapConverter(this, p);
    }

    @Override
    public <A> Decorators.AsJava<java.util.Iterator<A>> asJavaIteratorConverter(Iterator<A> i) {
        return DecorateAsJava$class.asJavaIteratorConverter(this, i);
    }

    @Override
    public <A> Decorators.AsJavaEnumeration<A> asJavaEnumerationConverter(Iterator<A> i) {
        return DecorateAsJava$class.asJavaEnumerationConverter(this, i);
    }

    @Override
    public <A> Decorators.AsJava<java.lang.Iterable<A>> asJavaIterableConverter(Iterable<A> i) {
        return DecorateAsJava$class.asJavaIterableConverter(this, i);
    }

    @Override
    public <A> Decorators.AsJavaCollection<A> asJavaCollectionConverter(Iterable<A> i) {
        return DecorateAsJava$class.asJavaCollectionConverter(this, i);
    }

    @Override
    public <A> Decorators.AsJava<List<A>> bufferAsJavaListConverter(Buffer<A> b) {
        return DecorateAsJava$class.bufferAsJavaListConverter(this, b);
    }

    @Override
    public <A> Decorators.AsJava<List<A>> mutableSeqAsJavaListConverter(scala.collection.mutable.Seq<A> b) {
        return DecorateAsJava$class.mutableSeqAsJavaListConverter(this, b);
    }

    @Override
    public <A> Decorators.AsJava<List<A>> seqAsJavaListConverter(Seq<A> b) {
        return DecorateAsJava$class.seqAsJavaListConverter(this, b);
    }

    @Override
    public <A> Decorators.AsJava<java.util.Set<A>> mutableSetAsJavaSetConverter(scala.collection.mutable.Set<A> s2) {
        return DecorateAsJava$class.mutableSetAsJavaSetConverter(this, s2);
    }

    @Override
    public <A> Decorators.AsJava<java.util.Set<A>> setAsJavaSetConverter(Set<A> s2) {
        return DecorateAsJava$class.setAsJavaSetConverter(this, s2);
    }

    @Override
    public <A, B> Decorators.AsJava<java.util.Map<A, B>> mutableMapAsJavaMapConverter(scala.collection.mutable.Map<A, B> m) {
        return DecorateAsJava$class.mutableMapAsJavaMapConverter(this, m);
    }

    @Override
    public <A, B> Decorators.AsJavaDictionary<A, B> asJavaDictionaryConverter(scala.collection.mutable.Map<A, B> m) {
        return DecorateAsJava$class.asJavaDictionaryConverter(this, m);
    }

    @Override
    public <A, B> Decorators.AsJava<java.util.Map<A, B>> mapAsJavaMapConverter(Map<A, B> m) {
        return DecorateAsJava$class.mapAsJavaMapConverter(this, m);
    }

    @Override
    public <A, B> Decorators.AsJava<ConcurrentMap<A, B>> mapAsJavaConcurrentMapConverter(scala.collection.concurrent.Map<A, B> m) {
        return DecorateAsJava$class.mapAsJavaConcurrentMapConverter(this, m);
    }

    private JavaConverters$() {
        MODULE$ = this;
        DecorateAsJava$class.$init$(this);
        DecorateAsScala$class.$init$(this);
    }
}

