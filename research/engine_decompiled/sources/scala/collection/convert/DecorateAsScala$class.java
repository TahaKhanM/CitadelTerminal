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
import scala.Function0;
import scala.Serializable;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.convert.DecorateAsScala;
import scala.collection.convert.Decorators;
import scala.collection.convert.Decorators$;
import scala.collection.convert.WrapAsScala$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Map;

public abstract class DecorateAsScala$class {
    public static Decorators.AsScala asScalaIteratorConverter(DecorateAsScala $this, java.util.Iterator i) {
        return new Decorators.AsScala(Decorators$.MODULE$, new Serializable($this, i){
            public static final long serialVersionUID = 0L;
            private final java.util.Iterator i$1;

            public final Iterator<A> apply() {
                return WrapAsScala$.MODULE$.asScalaIterator(this.i$1);
            }
            {
                this.i$1 = i$1;
            }
        });
    }

    public static Decorators.AsScala enumerationAsScalaIteratorConverter(DecorateAsScala $this, Enumeration i) {
        return new Decorators.AsScala(Decorators$.MODULE$, new Serializable($this, i){
            public static final long serialVersionUID = 0L;
            private final Enumeration i$2;

            public final Iterator<A> apply() {
                return WrapAsScala$.MODULE$.enumerationAsScalaIterator(this.i$2);
            }
            {
                this.i$2 = i$2;
            }
        });
    }

    public static Decorators.AsScala iterableAsScalaIterableConverter(DecorateAsScala $this, java.lang.Iterable i) {
        return new Decorators.AsScala(Decorators$.MODULE$, new Serializable($this, i){
            public static final long serialVersionUID = 0L;
            private final java.lang.Iterable i$3;

            public final Iterable<A> apply() {
                return WrapAsScala$.MODULE$.iterableAsScalaIterable(this.i$3);
            }
            {
                this.i$3 = i$3;
            }
        });
    }

    public static Decorators.AsScala collectionAsScalaIterableConverter(DecorateAsScala $this, Collection i) {
        return new Decorators.AsScala(Decorators$.MODULE$, new Serializable($this, i){
            public static final long serialVersionUID = 0L;
            private final Collection i$4;

            public final Iterable<A> apply() {
                return WrapAsScala$.MODULE$.collectionAsScalaIterable(this.i$4);
            }
            {
                this.i$4 = i$4;
            }
        });
    }

    public static Decorators.AsScala asScalaBufferConverter(DecorateAsScala $this, List l) {
        return new Decorators.AsScala(Decorators$.MODULE$, new Serializable($this, l){
            public static final long serialVersionUID = 0L;
            private final List l$1;

            public final Buffer<A> apply() {
                return WrapAsScala$.MODULE$.asScalaBuffer(this.l$1);
            }
            {
                this.l$1 = l$1;
            }
        });
    }

    public static Decorators.AsScala asScalaSetConverter(DecorateAsScala $this, Set s2) {
        return new Decorators.AsScala(Decorators$.MODULE$, new Serializable($this, s2){
            public static final long serialVersionUID = 0L;
            private final Set s$1;

            public final scala.collection.mutable.Set<A> apply() {
                return WrapAsScala$.MODULE$.asScalaSet(this.s$1);
            }
            {
                this.s$1 = s$1;
            }
        });
    }

    public static Decorators.AsScala mapAsScalaMapConverter(DecorateAsScala $this, java.util.Map m) {
        return new Decorators.AsScala(Decorators$.MODULE$, new Serializable($this, m){
            public static final long serialVersionUID = 0L;
            private final java.util.Map m$1;

            public final Map<A, B> apply() {
                return WrapAsScala$.MODULE$.mapAsScalaMap(this.m$1);
            }
            {
                this.m$1 = m$1;
            }
        });
    }

    public static Decorators.AsScala mapAsScalaConcurrentMapConverter(DecorateAsScala $this, ConcurrentMap m) {
        return new Decorators.AsScala(Decorators$.MODULE$, new Serializable($this, m){
            public static final long serialVersionUID = 0L;
            private final ConcurrentMap m$2;

            public final scala.collection.concurrent.Map<A, B> apply() {
                return WrapAsScala$.MODULE$.mapAsScalaConcurrentMap(this.m$2);
            }
            {
                this.m$2 = m$2;
            }
        });
    }

    public static Decorators.AsScala dictionaryAsScalaMapConverter(DecorateAsScala $this, Dictionary p) {
        return new Decorators.AsScala(Decorators$.MODULE$, new Serializable($this, p){
            public static final long serialVersionUID = 0L;
            private final Dictionary p$1;

            public final Map<A, B> apply() {
                return WrapAsScala$.MODULE$.dictionaryAsScalaMap(this.p$1);
            }
            {
                this.p$1 = p$1;
            }
        });
    }

    public static Decorators.AsScala propertiesAsScalaMapConverter(DecorateAsScala $this, Properties p) {
        return new Decorators.AsScala<Map<String, String>>(Decorators$.MODULE$, (Function0<Map<String, String>>)((Object)new Serializable($this, p){
            public static final long serialVersionUID = 0L;
            private final Properties p$2;

            public final Map<String, String> apply() {
                return WrapAsScala$.MODULE$.propertiesAsScalaMap(this.p$2);
            }
            {
                this.p$2 = p$2;
            }
        }));
    }

    public static void $init$(DecorateAsScala $this) {
    }
}

