/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.convert;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.concurrent.Map;
import scala.collection.convert.WrapAsScala;
import scala.collection.convert.WrapAsScala$class;
import scala.collection.mutable.Buffer;

public final class WrapAsScala$
implements WrapAsScala {
    public static final WrapAsScala$ MODULE$;

    static {
        new WrapAsScala$();
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
    public <A> scala.collection.mutable.Set<A> asScalaSet(Set<A> s2) {
        return WrapAsScala$class.asScalaSet(this, s2);
    }

    @Override
    public <A, B> scala.collection.mutable.Map<A, B> mapAsScalaMap(java.util.Map<A, B> m) {
        return WrapAsScala$class.mapAsScalaMap(this, m);
    }

    @Override
    public <A, B> Map<A, B> mapAsScalaConcurrentMap(ConcurrentMap<A, B> m) {
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

    private WrapAsScala$() {
        MODULE$ = this;
        WrapAsScala$class.$init$(this);
    }
}

