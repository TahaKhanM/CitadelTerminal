/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.convert;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import scala.Serializable;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.convert.DecorateAsJava;
import scala.collection.convert.Decorators;
import scala.collection.convert.Decorators$;
import scala.collection.convert.WrapAsJava$;
import scala.collection.mutable.Buffer;

public abstract class DecorateAsJava$class {
    public static Decorators.AsJava asJavaIteratorConverter(DecorateAsJava $this, Iterator i) {
        return new Decorators.AsJava(Decorators$.MODULE$, new Serializable($this, i){
            public static final long serialVersionUID = 0L;
            private final Iterator i$1;

            public final java.util.Iterator<A> apply() {
                return WrapAsJava$.MODULE$.asJavaIterator(this.i$1);
            }
            {
                this.i$1 = i$1;
            }
        });
    }

    public static Decorators.AsJavaEnumeration asJavaEnumerationConverter(DecorateAsJava $this, Iterator i) {
        return new Decorators.AsJavaEnumeration(Decorators$.MODULE$, i);
    }

    public static Decorators.AsJava asJavaIterableConverter(DecorateAsJava $this, Iterable i) {
        return new Decorators.AsJava(Decorators$.MODULE$, new Serializable($this, i){
            public static final long serialVersionUID = 0L;
            private final Iterable i$2;

            public final java.lang.Iterable<A> apply() {
                return WrapAsJava$.MODULE$.asJavaIterable(this.i$2);
            }
            {
                this.i$2 = i$2;
            }
        });
    }

    public static Decorators.AsJavaCollection asJavaCollectionConverter(DecorateAsJava $this, Iterable i) {
        return new Decorators.AsJavaCollection(Decorators$.MODULE$, i);
    }

    public static Decorators.AsJava bufferAsJavaListConverter(DecorateAsJava $this, Buffer b) {
        return new Decorators.AsJava(Decorators$.MODULE$, new Serializable($this, b){
            public static final long serialVersionUID = 0L;
            private final Buffer b$1;

            public final List<A> apply() {
                return WrapAsJava$.MODULE$.bufferAsJavaList(this.b$1);
            }
            {
                this.b$1 = b$1;
            }
        });
    }

    public static Decorators.AsJava mutableSeqAsJavaListConverter(DecorateAsJava $this, scala.collection.mutable.Seq b) {
        return new Decorators.AsJava(Decorators$.MODULE$, new Serializable($this, b){
            public static final long serialVersionUID = 0L;
            private final scala.collection.mutable.Seq b$2;

            public final List<A> apply() {
                return WrapAsJava$.MODULE$.mutableSeqAsJavaList(this.b$2);
            }
            {
                this.b$2 = b$2;
            }
        });
    }

    public static Decorators.AsJava seqAsJavaListConverter(DecorateAsJava $this, Seq b) {
        return new Decorators.AsJava(Decorators$.MODULE$, new Serializable($this, b){
            public static final long serialVersionUID = 0L;
            private final Seq b$3;

            public final List<A> apply() {
                return WrapAsJava$.MODULE$.seqAsJavaList(this.b$3);
            }
            {
                this.b$3 = b$3;
            }
        });
    }

    public static Decorators.AsJava mutableSetAsJavaSetConverter(DecorateAsJava $this, scala.collection.mutable.Set s2) {
        return new Decorators.AsJava(Decorators$.MODULE$, new Serializable($this, s2){
            public static final long serialVersionUID = 0L;
            private final scala.collection.mutable.Set s$1;

            public final java.util.Set<A> apply() {
                return WrapAsJava$.MODULE$.mutableSetAsJavaSet(this.s$1);
            }
            {
                this.s$1 = s$1;
            }
        });
    }

    public static Decorators.AsJava setAsJavaSetConverter(DecorateAsJava $this, Set s2) {
        return new Decorators.AsJava(Decorators$.MODULE$, new Serializable($this, s2){
            public static final long serialVersionUID = 0L;
            private final Set s$2;

            public final java.util.Set<A> apply() {
                return WrapAsJava$.MODULE$.setAsJavaSet(this.s$2);
            }
            {
                this.s$2 = s$2;
            }
        });
    }

    public static Decorators.AsJava mutableMapAsJavaMapConverter(DecorateAsJava $this, scala.collection.mutable.Map m) {
        return new Decorators.AsJava(Decorators$.MODULE$, new Serializable($this, m){
            public static final long serialVersionUID = 0L;
            private final scala.collection.mutable.Map m$1;

            public final java.util.Map<A, B> apply() {
                return WrapAsJava$.MODULE$.mutableMapAsJavaMap(this.m$1);
            }
            {
                this.m$1 = m$1;
            }
        });
    }

    public static Decorators.AsJavaDictionary asJavaDictionaryConverter(DecorateAsJava $this, scala.collection.mutable.Map m) {
        return new Decorators.AsJavaDictionary(Decorators$.MODULE$, m);
    }

    public static Decorators.AsJava mapAsJavaMapConverter(DecorateAsJava $this, Map m) {
        return new Decorators.AsJava(Decorators$.MODULE$, new Serializable($this, m){
            public static final long serialVersionUID = 0L;
            private final Map m$2;

            public final java.util.Map<A, B> apply() {
                return WrapAsJava$.MODULE$.mapAsJavaMap(this.m$2);
            }
            {
                this.m$2 = m$2;
            }
        });
    }

    public static Decorators.AsJava mapAsJavaConcurrentMapConverter(DecorateAsJava $this, scala.collection.concurrent.Map m) {
        return new Decorators.AsJava(Decorators$.MODULE$, new Serializable($this, m){
            public static final long serialVersionUID = 0L;
            private final scala.collection.concurrent.Map m$3;

            public final ConcurrentMap<A, B> apply() {
                return WrapAsJava$.MODULE$.mapAsJavaConcurrentMap(this.m$3);
            }
            {
                this.m$3 = m$3;
            }
        });
    }

    public static void $init$(DecorateAsJava $this) {
    }
}

