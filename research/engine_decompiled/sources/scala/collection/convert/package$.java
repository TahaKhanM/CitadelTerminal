/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.convert;

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
import scala.collection.convert.WrapAsJava;
import scala.collection.convert.WrapAsJava$class;
import scala.collection.convert.WrapAsScala;
import scala.collection.convert.WrapAsScala$class;
import scala.collection.mutable.Buffer;

public final class package$ {
    public static final package$ MODULE$;
    private final DecorateAsJava decorateAsJava;
    private final DecorateAsScala decorateAsScala;
    private final DecorateAsJava decorateAll;
    private final WrapAsJava wrapAsJava;
    private final WrapAsScala wrapAsScala;
    private final WrapAsJava wrapAll;

    static {
        new package$();
    }

    public DecorateAsJava decorateAsJava() {
        return this.decorateAsJava;
    }

    public DecorateAsScala decorateAsScala() {
        return this.decorateAsScala;
    }

    public DecorateAsJava decorateAll() {
        return this.decorateAll;
    }

    public WrapAsJava wrapAsJava() {
        return this.wrapAsJava;
    }

    public WrapAsScala wrapAsScala() {
        return this.wrapAsScala;
    }

    public WrapAsJava wrapAll() {
        return this.wrapAll;
    }

    private package$() {
        MODULE$ = this;
        this.decorateAsJava = new DecorateAsJava(){

            public <A> Decorators.AsJava<java.util.Iterator<A>> asJavaIteratorConverter(Iterator<A> i) {
                return DecorateAsJava$class.asJavaIteratorConverter(this, i);
            }

            public <A> Decorators.AsJavaEnumeration<A> asJavaEnumerationConverter(Iterator<A> i) {
                return DecorateAsJava$class.asJavaEnumerationConverter(this, i);
            }

            public <A> Decorators.AsJava<java.lang.Iterable<A>> asJavaIterableConverter(Iterable<A> i) {
                return DecorateAsJava$class.asJavaIterableConverter(this, i);
            }

            public <A> Decorators.AsJavaCollection<A> asJavaCollectionConverter(Iterable<A> i) {
                return DecorateAsJava$class.asJavaCollectionConverter(this, i);
            }

            public <A> Decorators.AsJava<List<A>> bufferAsJavaListConverter(Buffer<A> b) {
                return DecorateAsJava$class.bufferAsJavaListConverter(this, b);
            }

            public <A> Decorators.AsJava<List<A>> mutableSeqAsJavaListConverter(scala.collection.mutable.Seq<A> b) {
                return DecorateAsJava$class.mutableSeqAsJavaListConverter(this, b);
            }

            public <A> Decorators.AsJava<List<A>> seqAsJavaListConverter(Seq<A> b) {
                return DecorateAsJava$class.seqAsJavaListConverter(this, b);
            }

            public <A> Decorators.AsJava<java.util.Set<A>> mutableSetAsJavaSetConverter(scala.collection.mutable.Set<A> s2) {
                return DecorateAsJava$class.mutableSetAsJavaSetConverter(this, s2);
            }

            public <A> Decorators.AsJava<java.util.Set<A>> setAsJavaSetConverter(Set<A> s2) {
                return DecorateAsJava$class.setAsJavaSetConverter(this, s2);
            }

            public <A, B> Decorators.AsJava<java.util.Map<A, B>> mutableMapAsJavaMapConverter(scala.collection.mutable.Map<A, B> m) {
                return DecorateAsJava$class.mutableMapAsJavaMapConverter(this, m);
            }

            public <A, B> Decorators.AsJavaDictionary<A, B> asJavaDictionaryConverter(scala.collection.mutable.Map<A, B> m) {
                return DecorateAsJava$class.asJavaDictionaryConverter(this, m);
            }

            public <A, B> Decorators.AsJava<java.util.Map<A, B>> mapAsJavaMapConverter(Map<A, B> m) {
                return DecorateAsJava$class.mapAsJavaMapConverter(this, m);
            }

            public <A, B> Decorators.AsJava<ConcurrentMap<A, B>> mapAsJavaConcurrentMapConverter(scala.collection.concurrent.Map<A, B> m) {
                return DecorateAsJava$class.mapAsJavaConcurrentMapConverter(this, m);
            }
            {
                DecorateAsJava$class.$init$(this);
            }
        };
        this.decorateAsScala = new DecorateAsScala(){

            public <A> Decorators.AsScala<Iterator<A>> asScalaIteratorConverter(java.util.Iterator<A> i) {
                return DecorateAsScala$class.asScalaIteratorConverter(this, i);
            }

            public <A> Decorators.AsScala<Iterator<A>> enumerationAsScalaIteratorConverter(Enumeration<A> i) {
                return DecorateAsScala$class.enumerationAsScalaIteratorConverter(this, i);
            }

            public <A> Decorators.AsScala<Iterable<A>> iterableAsScalaIterableConverter(java.lang.Iterable<A> i) {
                return DecorateAsScala$class.iterableAsScalaIterableConverter(this, i);
            }

            public <A> Decorators.AsScala<Iterable<A>> collectionAsScalaIterableConverter(Collection<A> i) {
                return DecorateAsScala$class.collectionAsScalaIterableConverter(this, i);
            }

            public <A> Decorators.AsScala<Buffer<A>> asScalaBufferConverter(List<A> l) {
                return DecorateAsScala$class.asScalaBufferConverter(this, l);
            }

            public <A> Decorators.AsScala<scala.collection.mutable.Set<A>> asScalaSetConverter(java.util.Set<A> s2) {
                return DecorateAsScala$class.asScalaSetConverter(this, s2);
            }

            public <A, B> Decorators.AsScala<scala.collection.mutable.Map<A, B>> mapAsScalaMapConverter(java.util.Map<A, B> m) {
                return DecorateAsScala$class.mapAsScalaMapConverter(this, m);
            }

            public <A, B> Decorators.AsScala<scala.collection.concurrent.Map<A, B>> mapAsScalaConcurrentMapConverter(ConcurrentMap<A, B> m) {
                return DecorateAsScala$class.mapAsScalaConcurrentMapConverter(this, m);
            }

            public <A, B> Decorators.AsScala<scala.collection.mutable.Map<A, B>> dictionaryAsScalaMapConverter(Dictionary<A, B> p) {
                return DecorateAsScala$class.dictionaryAsScalaMapConverter(this, p);
            }

            public Decorators.AsScala<scala.collection.mutable.Map<String, String>> propertiesAsScalaMapConverter(Properties p) {
                return DecorateAsScala$class.propertiesAsScalaMapConverter(this, p);
            }
            {
                DecorateAsScala$class.$init$(this);
            }
        };
        this.decorateAll = new DecorateAsJava(){

            public <A> Decorators.AsScala<Iterator<A>> asScalaIteratorConverter(java.util.Iterator<A> i) {
                return DecorateAsScala$class.asScalaIteratorConverter(this, i);
            }

            public <A> Decorators.AsScala<Iterator<A>> enumerationAsScalaIteratorConverter(Enumeration<A> i) {
                return DecorateAsScala$class.enumerationAsScalaIteratorConverter(this, i);
            }

            public <A> Decorators.AsScala<Iterable<A>> iterableAsScalaIterableConverter(java.lang.Iterable<A> i) {
                return DecorateAsScala$class.iterableAsScalaIterableConverter(this, i);
            }

            public <A> Decorators.AsScala<Iterable<A>> collectionAsScalaIterableConverter(Collection<A> i) {
                return DecorateAsScala$class.collectionAsScalaIterableConverter(this, i);
            }

            public <A> Decorators.AsScala<Buffer<A>> asScalaBufferConverter(List<A> l) {
                return DecorateAsScala$class.asScalaBufferConverter(this, l);
            }

            public <A> Decorators.AsScala<scala.collection.mutable.Set<A>> asScalaSetConverter(java.util.Set<A> s2) {
                return DecorateAsScala$class.asScalaSetConverter(this, s2);
            }

            public <A, B> Decorators.AsScala<scala.collection.mutable.Map<A, B>> mapAsScalaMapConverter(java.util.Map<A, B> m) {
                return DecorateAsScala$class.mapAsScalaMapConverter(this, m);
            }

            public <A, B> Decorators.AsScala<scala.collection.concurrent.Map<A, B>> mapAsScalaConcurrentMapConverter(ConcurrentMap<A, B> m) {
                return DecorateAsScala$class.mapAsScalaConcurrentMapConverter(this, m);
            }

            public <A, B> Decorators.AsScala<scala.collection.mutable.Map<A, B>> dictionaryAsScalaMapConverter(Dictionary<A, B> p) {
                return DecorateAsScala$class.dictionaryAsScalaMapConverter(this, p);
            }

            public Decorators.AsScala<scala.collection.mutable.Map<String, String>> propertiesAsScalaMapConverter(Properties p) {
                return DecorateAsScala$class.propertiesAsScalaMapConverter(this, p);
            }

            public <A> Decorators.AsJava<java.util.Iterator<A>> asJavaIteratorConverter(Iterator<A> i) {
                return DecorateAsJava$class.asJavaIteratorConverter(this, i);
            }

            public <A> Decorators.AsJavaEnumeration<A> asJavaEnumerationConverter(Iterator<A> i) {
                return DecorateAsJava$class.asJavaEnumerationConverter(this, i);
            }

            public <A> Decorators.AsJava<java.lang.Iterable<A>> asJavaIterableConverter(Iterable<A> i) {
                return DecorateAsJava$class.asJavaIterableConverter(this, i);
            }

            public <A> Decorators.AsJavaCollection<A> asJavaCollectionConverter(Iterable<A> i) {
                return DecorateAsJava$class.asJavaCollectionConverter(this, i);
            }

            public <A> Decorators.AsJava<List<A>> bufferAsJavaListConverter(Buffer<A> b) {
                return DecorateAsJava$class.bufferAsJavaListConverter(this, b);
            }

            public <A> Decorators.AsJava<List<A>> mutableSeqAsJavaListConverter(scala.collection.mutable.Seq<A> b) {
                return DecorateAsJava$class.mutableSeqAsJavaListConverter(this, b);
            }

            public <A> Decorators.AsJava<List<A>> seqAsJavaListConverter(Seq<A> b) {
                return DecorateAsJava$class.seqAsJavaListConverter(this, b);
            }

            public <A> Decorators.AsJava<java.util.Set<A>> mutableSetAsJavaSetConverter(scala.collection.mutable.Set<A> s2) {
                return DecorateAsJava$class.mutableSetAsJavaSetConverter(this, s2);
            }

            public <A> Decorators.AsJava<java.util.Set<A>> setAsJavaSetConverter(Set<A> s2) {
                return DecorateAsJava$class.setAsJavaSetConverter(this, s2);
            }

            public <A, B> Decorators.AsJava<java.util.Map<A, B>> mutableMapAsJavaMapConverter(scala.collection.mutable.Map<A, B> m) {
                return DecorateAsJava$class.mutableMapAsJavaMapConverter(this, m);
            }

            public <A, B> Decorators.AsJavaDictionary<A, B> asJavaDictionaryConverter(scala.collection.mutable.Map<A, B> m) {
                return DecorateAsJava$class.asJavaDictionaryConverter(this, m);
            }

            public <A, B> Decorators.AsJava<java.util.Map<A, B>> mapAsJavaMapConverter(Map<A, B> m) {
                return DecorateAsJava$class.mapAsJavaMapConverter(this, m);
            }

            public <A, B> Decorators.AsJava<ConcurrentMap<A, B>> mapAsJavaConcurrentMapConverter(scala.collection.concurrent.Map<A, B> m) {
                return DecorateAsJava$class.mapAsJavaConcurrentMapConverter(this, m);
            }
            {
                DecorateAsJava$class.$init$(this);
                DecorateAsScala$class.$init$(this);
            }
        };
        this.wrapAsJava = new WrapAsJava(){

            public <A> java.util.Iterator<A> asJavaIterator(Iterator<A> it) {
                return WrapAsJava$class.asJavaIterator(this, it);
            }

            public <A> Enumeration<A> asJavaEnumeration(Iterator<A> it) {
                return WrapAsJava$class.asJavaEnumeration(this, it);
            }

            public <A> java.lang.Iterable<A> asJavaIterable(Iterable<A> i) {
                return WrapAsJava$class.asJavaIterable(this, i);
            }

            public <A> Collection<A> asJavaCollection(Iterable<A> it) {
                return WrapAsJava$class.asJavaCollection(this, it);
            }

            public <A> List<A> bufferAsJavaList(Buffer<A> b) {
                return WrapAsJava$class.bufferAsJavaList(this, b);
            }

            public <A> List<A> mutableSeqAsJavaList(scala.collection.mutable.Seq<A> seq) {
                return WrapAsJava$class.mutableSeqAsJavaList(this, seq);
            }

            public <A> List<A> seqAsJavaList(Seq<A> seq) {
                return WrapAsJava$class.seqAsJavaList(this, seq);
            }

            public <A> java.util.Set<A> mutableSetAsJavaSet(scala.collection.mutable.Set<A> s2) {
                return WrapAsJava$class.mutableSetAsJavaSet(this, s2);
            }

            public <A> java.util.Set<A> setAsJavaSet(Set<A> s2) {
                return WrapAsJava$class.setAsJavaSet(this, s2);
            }

            public <A, B> java.util.Map<A, B> mutableMapAsJavaMap(scala.collection.mutable.Map<A, B> m) {
                return WrapAsJava$class.mutableMapAsJavaMap(this, m);
            }

            public <A, B> Dictionary<A, B> asJavaDictionary(scala.collection.mutable.Map<A, B> m) {
                return WrapAsJava$class.asJavaDictionary(this, m);
            }

            public <A, B> java.util.Map<A, B> mapAsJavaMap(Map<A, B> m) {
                return WrapAsJava$class.mapAsJavaMap(this, m);
            }

            public <A, B> ConcurrentMap<A, B> mapAsJavaConcurrentMap(scala.collection.concurrent.Map<A, B> m) {
                return WrapAsJava$class.mapAsJavaConcurrentMap(this, m);
            }
            {
                WrapAsJava$class.$init$(this);
            }
        };
        this.wrapAsScala = new WrapAsScala(){

            public <A> Iterator<A> asScalaIterator(java.util.Iterator<A> it) {
                return WrapAsScala$class.asScalaIterator(this, it);
            }

            public <A> Iterator<A> enumerationAsScalaIterator(Enumeration<A> i) {
                return WrapAsScala$class.enumerationAsScalaIterator(this, i);
            }

            public <A> Iterable<A> iterableAsScalaIterable(java.lang.Iterable<A> i) {
                return WrapAsScala$class.iterableAsScalaIterable(this, i);
            }

            public <A> Iterable<A> collectionAsScalaIterable(Collection<A> i) {
                return WrapAsScala$class.collectionAsScalaIterable(this, i);
            }

            public <A> Buffer<A> asScalaBuffer(List<A> l) {
                return WrapAsScala$class.asScalaBuffer(this, l);
            }

            public <A> scala.collection.mutable.Set<A> asScalaSet(java.util.Set<A> s2) {
                return WrapAsScala$class.asScalaSet(this, s2);
            }

            public <A, B> scala.collection.mutable.Map<A, B> mapAsScalaMap(java.util.Map<A, B> m) {
                return WrapAsScala$class.mapAsScalaMap(this, m);
            }

            public <A, B> scala.collection.concurrent.Map<A, B> mapAsScalaConcurrentMap(ConcurrentMap<A, B> m) {
                return WrapAsScala$class.mapAsScalaConcurrentMap(this, m);
            }

            public <A, B> scala.collection.mutable.Map<A, B> dictionaryAsScalaMap(Dictionary<A, B> p) {
                return WrapAsScala$class.dictionaryAsScalaMap(this, p);
            }

            public scala.collection.mutable.Map<String, String> propertiesAsScalaMap(Properties p) {
                return WrapAsScala$class.propertiesAsScalaMap(this, p);
            }
            {
                WrapAsScala$class.$init$(this);
            }
        };
        this.wrapAll = new WrapAsJava(){

            public <A> Iterator<A> asScalaIterator(java.util.Iterator<A> it) {
                return WrapAsScala$class.asScalaIterator(this, it);
            }

            public <A> Iterator<A> enumerationAsScalaIterator(Enumeration<A> i) {
                return WrapAsScala$class.enumerationAsScalaIterator(this, i);
            }

            public <A> Iterable<A> iterableAsScalaIterable(java.lang.Iterable<A> i) {
                return WrapAsScala$class.iterableAsScalaIterable(this, i);
            }

            public <A> Iterable<A> collectionAsScalaIterable(Collection<A> i) {
                return WrapAsScala$class.collectionAsScalaIterable(this, i);
            }

            public <A> Buffer<A> asScalaBuffer(List<A> l) {
                return WrapAsScala$class.asScalaBuffer(this, l);
            }

            public <A> scala.collection.mutable.Set<A> asScalaSet(java.util.Set<A> s2) {
                return WrapAsScala$class.asScalaSet(this, s2);
            }

            public <A, B> scala.collection.mutable.Map<A, B> mapAsScalaMap(java.util.Map<A, B> m) {
                return WrapAsScala$class.mapAsScalaMap(this, m);
            }

            public <A, B> scala.collection.concurrent.Map<A, B> mapAsScalaConcurrentMap(ConcurrentMap<A, B> m) {
                return WrapAsScala$class.mapAsScalaConcurrentMap(this, m);
            }

            public <A, B> scala.collection.mutable.Map<A, B> dictionaryAsScalaMap(Dictionary<A, B> p) {
                return WrapAsScala$class.dictionaryAsScalaMap(this, p);
            }

            public scala.collection.mutable.Map<String, String> propertiesAsScalaMap(Properties p) {
                return WrapAsScala$class.propertiesAsScalaMap(this, p);
            }

            public <A> java.util.Iterator<A> asJavaIterator(Iterator<A> it) {
                return WrapAsJava$class.asJavaIterator(this, it);
            }

            public <A> Enumeration<A> asJavaEnumeration(Iterator<A> it) {
                return WrapAsJava$class.asJavaEnumeration(this, it);
            }

            public <A> java.lang.Iterable<A> asJavaIterable(Iterable<A> i) {
                return WrapAsJava$class.asJavaIterable(this, i);
            }

            public <A> Collection<A> asJavaCollection(Iterable<A> it) {
                return WrapAsJava$class.asJavaCollection(this, it);
            }

            public <A> List<A> bufferAsJavaList(Buffer<A> b) {
                return WrapAsJava$class.bufferAsJavaList(this, b);
            }

            public <A> List<A> mutableSeqAsJavaList(scala.collection.mutable.Seq<A> seq) {
                return WrapAsJava$class.mutableSeqAsJavaList(this, seq);
            }

            public <A> List<A> seqAsJavaList(Seq<A> seq) {
                return WrapAsJava$class.seqAsJavaList(this, seq);
            }

            public <A> java.util.Set<A> mutableSetAsJavaSet(scala.collection.mutable.Set<A> s2) {
                return WrapAsJava$class.mutableSetAsJavaSet(this, s2);
            }

            public <A> java.util.Set<A> setAsJavaSet(Set<A> s2) {
                return WrapAsJava$class.setAsJavaSet(this, s2);
            }

            public <A, B> java.util.Map<A, B> mutableMapAsJavaMap(scala.collection.mutable.Map<A, B> m) {
                return WrapAsJava$class.mutableMapAsJavaMap(this, m);
            }

            public <A, B> Dictionary<A, B> asJavaDictionary(scala.collection.mutable.Map<A, B> m) {
                return WrapAsJava$class.asJavaDictionary(this, m);
            }

            public <A, B> java.util.Map<A, B> mapAsJavaMap(Map<A, B> m) {
                return WrapAsJava$class.mapAsJavaMap(this, m);
            }

            public <A, B> ConcurrentMap<A, B> mapAsJavaConcurrentMap(scala.collection.concurrent.Map<A, B> m) {
                return WrapAsJava$class.mapAsJavaConcurrentMap(this, m);
            }
            {
                WrapAsJava$class.$init$(this);
                WrapAsScala$class.$init$(this);
            }
        };
    }
}

