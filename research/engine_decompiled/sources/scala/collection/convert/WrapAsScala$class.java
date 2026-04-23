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
import scala.collection.convert.WrapAsScala;
import scala.collection.convert.Wrappers;
import scala.collection.convert.Wrappers$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Map;

public abstract class WrapAsScala$class {
    public static Iterator asScalaIterator(WrapAsScala $this, java.util.Iterator it) {
        Iterator iterator2;
        if (it instanceof Wrappers.IteratorWrapper) {
            Wrappers.IteratorWrapper iteratorWrapper = (Wrappers.IteratorWrapper)it;
            iterator2 = iteratorWrapper.underlying();
        } else {
            iterator2 = new Wrappers.JIteratorWrapper(Wrappers$.MODULE$, it);
        }
        return iterator2;
    }

    public static Iterator enumerationAsScalaIterator(WrapAsScala $this, Enumeration i) {
        Iterator iterator2;
        if (i instanceof Wrappers.IteratorWrapper) {
            Wrappers.IteratorWrapper iteratorWrapper = (Wrappers.IteratorWrapper)i;
            iterator2 = iteratorWrapper.underlying();
        } else {
            iterator2 = new Wrappers.JEnumerationWrapper(Wrappers$.MODULE$, i);
        }
        return iterator2;
    }

    public static Iterable iterableAsScalaIterable(WrapAsScala $this, java.lang.Iterable i) {
        Iterable iterable;
        if (i instanceof Wrappers.IterableWrapper) {
            Wrappers.IterableWrapper iterableWrapper = (Wrappers.IterableWrapper)i;
            iterable = iterableWrapper.underlying();
        } else {
            iterable = new Wrappers.JIterableWrapper(Wrappers$.MODULE$, i);
        }
        return iterable;
    }

    public static Iterable collectionAsScalaIterable(WrapAsScala $this, Collection i) {
        Iterable iterable;
        if (i instanceof Wrappers.IterableWrapper) {
            Wrappers.IterableWrapper iterableWrapper = (Wrappers.IterableWrapper)i;
            iterable = iterableWrapper.underlying();
        } else {
            iterable = new Wrappers.JCollectionWrapper(Wrappers$.MODULE$, i);
        }
        return iterable;
    }

    public static Buffer asScalaBuffer(WrapAsScala $this, List l) {
        Wrappers.JListWrapper jListWrapper;
        if (l instanceof Wrappers.MutableBufferWrapper) {
            Wrappers.MutableBufferWrapper mutableBufferWrapper = (Wrappers.MutableBufferWrapper)l;
            jListWrapper = mutableBufferWrapper.underlying();
        } else {
            jListWrapper = new Wrappers.JListWrapper(Wrappers$.MODULE$, l);
        }
        return jListWrapper;
    }

    public static scala.collection.mutable.Set asScalaSet(WrapAsScala $this, Set s2) {
        scala.collection.mutable.Set set;
        if (s2 instanceof Wrappers.MutableSetWrapper) {
            Wrappers.MutableSetWrapper mutableSetWrapper = (Wrappers.MutableSetWrapper)s2;
            set = mutableSetWrapper.underlying();
        } else {
            set = new Wrappers.JSetWrapper(Wrappers$.MODULE$, s2);
        }
        return set;
    }

    public static Map mapAsScalaMap(WrapAsScala $this, java.util.Map m) {
        Map map2;
        if (m instanceof Wrappers.MutableMapWrapper) {
            Wrappers.MutableMapWrapper mutableMapWrapper = (Wrappers.MutableMapWrapper)m;
            map2 = mutableMapWrapper.underlying();
        } else {
            map2 = new Wrappers.JMapWrapper(Wrappers$.MODULE$, m);
        }
        return map2;
    }

    public static scala.collection.concurrent.Map mapAsScalaConcurrentMap(WrapAsScala $this, ConcurrentMap m) {
        Wrappers.JConcurrentMapWrapper jConcurrentMapWrapper;
        if (m instanceof Wrappers.ConcurrentMapWrapper) {
            Wrappers.ConcurrentMapWrapper concurrentMapWrapper = (Wrappers.ConcurrentMapWrapper)m;
            jConcurrentMapWrapper = concurrentMapWrapper.underlying();
        } else {
            jConcurrentMapWrapper = new Wrappers.JConcurrentMapWrapper(Wrappers$.MODULE$, m);
        }
        return jConcurrentMapWrapper;
    }

    public static Map dictionaryAsScalaMap(WrapAsScala $this, Dictionary p) {
        Map map2;
        if (p instanceof Wrappers.DictionaryWrapper) {
            Wrappers.DictionaryWrapper dictionaryWrapper = (Wrappers.DictionaryWrapper)p;
            map2 = dictionaryWrapper.underlying();
        } else {
            map2 = new Wrappers.JDictionaryWrapper(Wrappers$.MODULE$, p);
        }
        return map2;
    }

    public static Map propertiesAsScalaMap(WrapAsScala $this, Properties p) {
        return new Wrappers.JPropertiesWrapper(Wrappers$.MODULE$, p);
    }

    public static void $init$(WrapAsScala $this) {
    }
}

