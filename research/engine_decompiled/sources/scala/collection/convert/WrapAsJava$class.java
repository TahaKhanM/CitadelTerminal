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
import scala.collection.convert.Wrappers;
import scala.collection.convert.Wrappers$;
import scala.collection.mutable.Buffer;

public abstract class WrapAsJava$class {
    public static java.util.Iterator asJavaIterator(WrapAsJava $this, Iterator it) {
        java.util.Iterator iterator2;
        if (it instanceof Wrappers.JIteratorWrapper) {
            Wrappers.JIteratorWrapper jIteratorWrapper = (Wrappers.JIteratorWrapper)it;
            iterator2 = jIteratorWrapper.underlying();
        } else {
            iterator2 = new Wrappers.IteratorWrapper(Wrappers$.MODULE$, it);
        }
        return iterator2;
    }

    public static Enumeration asJavaEnumeration(WrapAsJava $this, Iterator it) {
        Enumeration enumeration;
        if (it instanceof Wrappers.JEnumerationWrapper) {
            Wrappers.JEnumerationWrapper jEnumerationWrapper = (Wrappers.JEnumerationWrapper)it;
            enumeration = jEnumerationWrapper.underlying();
        } else {
            enumeration = new Wrappers.IteratorWrapper(Wrappers$.MODULE$, it);
        }
        return enumeration;
    }

    public static java.lang.Iterable asJavaIterable(WrapAsJava $this, Iterable i) {
        java.lang.Iterable iterable;
        if (i instanceof Wrappers.JIterableWrapper) {
            Wrappers.JIterableWrapper jIterableWrapper = (Wrappers.JIterableWrapper)i;
            iterable = jIterableWrapper.underlying();
        } else {
            iterable = new Wrappers.IterableWrapper(Wrappers$.MODULE$, i);
        }
        return iterable;
    }

    public static Collection asJavaCollection(WrapAsJava $this, Iterable it) {
        Collection collection;
        if (it instanceof Wrappers.JCollectionWrapper) {
            Wrappers.JCollectionWrapper jCollectionWrapper = (Wrappers.JCollectionWrapper)it;
            collection = jCollectionWrapper.underlying();
        } else {
            collection = new Wrappers.IterableWrapper(Wrappers$.MODULE$, it);
        }
        return collection;
    }

    public static List bufferAsJavaList(WrapAsJava $this, Buffer b) {
        List list2;
        if (b instanceof Wrappers.JListWrapper) {
            Wrappers.JListWrapper jListWrapper = (Wrappers.JListWrapper)b;
            list2 = jListWrapper.underlying();
        } else {
            list2 = new Wrappers.MutableBufferWrapper(Wrappers$.MODULE$, b);
        }
        return list2;
    }

    public static List mutableSeqAsJavaList(WrapAsJava $this, scala.collection.mutable.Seq seq) {
        List list2;
        if (seq instanceof Wrappers.JListWrapper) {
            Wrappers.JListWrapper jListWrapper = (Wrappers.JListWrapper)seq;
            list2 = jListWrapper.underlying();
        } else {
            list2 = new Wrappers.MutableSeqWrapper(Wrappers$.MODULE$, seq);
        }
        return list2;
    }

    public static List seqAsJavaList(WrapAsJava $this, Seq seq) {
        List list2;
        if (seq instanceof Wrappers.JListWrapper) {
            Wrappers.JListWrapper jListWrapper = (Wrappers.JListWrapper)seq;
            list2 = jListWrapper.underlying();
        } else {
            list2 = new Wrappers.SeqWrapper(Wrappers$.MODULE$, seq);
        }
        return list2;
    }

    public static java.util.Set mutableSetAsJavaSet(WrapAsJava $this, scala.collection.mutable.Set s2) {
        java.util.Set set;
        if (s2 instanceof Wrappers.JSetWrapper) {
            Wrappers.JSetWrapper jSetWrapper = (Wrappers.JSetWrapper)s2;
            set = jSetWrapper.underlying();
        } else {
            set = new Wrappers.MutableSetWrapper((Wrappers)Wrappers$.MODULE$, s2);
        }
        return set;
    }

    public static java.util.Set setAsJavaSet(WrapAsJava $this, Set s2) {
        java.util.Set set;
        if (s2 instanceof Wrappers.JSetWrapper) {
            Wrappers.JSetWrapper jSetWrapper = (Wrappers.JSetWrapper)s2;
            set = jSetWrapper.underlying();
        } else {
            set = new Wrappers.SetWrapper(Wrappers$.MODULE$, s2);
        }
        return set;
    }

    public static java.util.Map mutableMapAsJavaMap(WrapAsJava $this, scala.collection.mutable.Map m) {
        java.util.Map map2;
        if (m instanceof Wrappers.JMapWrapper) {
            Wrappers.JMapWrapper jMapWrapper = (Wrappers.JMapWrapper)m;
            map2 = jMapWrapper.underlying();
        } else {
            map2 = new Wrappers.MutableMapWrapper((Wrappers)Wrappers$.MODULE$, m);
        }
        return map2;
    }

    public static Dictionary asJavaDictionary(WrapAsJava $this, scala.collection.mutable.Map m) {
        Dictionary dictionary;
        if (m instanceof Wrappers.JDictionaryWrapper) {
            Wrappers.JDictionaryWrapper jDictionaryWrapper = (Wrappers.JDictionaryWrapper)m;
            dictionary = jDictionaryWrapper.underlying();
        } else {
            dictionary = new Wrappers.DictionaryWrapper(Wrappers$.MODULE$, m);
        }
        return dictionary;
    }

    public static java.util.Map mapAsJavaMap(WrapAsJava $this, Map m) {
        java.util.Map map2;
        if (m instanceof Wrappers.JMapWrapper) {
            Wrappers.JMapWrapper jMapWrapper = (Wrappers.JMapWrapper)m;
            map2 = jMapWrapper.underlying();
        } else {
            map2 = new Wrappers.MapWrapper(Wrappers$.MODULE$, m);
        }
        return map2;
    }

    public static ConcurrentMap mapAsJavaConcurrentMap(WrapAsJava $this, scala.collection.concurrent.Map m) {
        Wrappers.ConcurrentMapWrapper concurrentMapWrapper;
        if (m instanceof Wrappers.JConcurrentMapWrapper) {
            Wrappers.JConcurrentMapWrapper jConcurrentMapWrapper = (Wrappers.JConcurrentMapWrapper)m;
            concurrentMapWrapper = jConcurrentMapWrapper.underlying();
        } else {
            concurrentMapWrapper = new Wrappers.ConcurrentMapWrapper((Wrappers)Wrappers$.MODULE$, m);
        }
        return concurrentMapWrapper;
    }

    public static void $init$(WrapAsJava $this) {
    }
}

