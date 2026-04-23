/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function1;
import scala.Function2;
import scala.Predef$;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.collection.Seq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.Iterable;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$class;
import scala.collection.immutable.MapLike$class;
import scala.collection.immutable.Set;
import scala.collection.immutable.Traversable$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\u00152Q!\u0001\u0002\u0002\u0002%\u00111\"\u00112tiJ\f7\r^'ba*\u00111\u0001B\u0001\nS6lW\u000f^1cY\u0016T!!\u0002\u0004\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001)2A\u0003\t\u001c'\r\u00011\"\b\t\u0005\u00195q!$D\u0001\u0005\u0013\t\tA\u0001\u0005\u0002\u0010!1\u0001A!B\t\u0001\u0005\u0004\u0011\"!A!\u0012\u0005M9\u0002C\u0001\u000b\u0016\u001b\u00051\u0011B\u0001\f\u0007\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0006\r\n\u0005e1!aA!osB\u0011qb\u0007\u0003\u00079\u0001!)\u0019\u0001\n\u0003\u0003\t\u0003BAH\u0010\u000f55\t!!\u0003\u0002!\u0005\t\u0019Q*\u00199\t\u000b\t\u0002A\u0011A\u0012\u0002\rqJg.\u001b;?)\u0005!\u0003\u0003\u0002\u0010\u0001\u001di\u0001")
public abstract class AbstractMap<A, B>
extends scala.collection.AbstractMap<A, B>
implements Map<A, B> {
    @Override
    public Map<A, B> empty() {
        return Map$class.empty(this);
    }

    @Override
    public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Tuple2<A, B>, Tuple2<T, U>> ev) {
        return Map$class.toMap(this, ev);
    }

    @Override
    public Map<A, B> seq() {
        return Map$class.seq(this);
    }

    @Override
    public <B1> Map<A, B1> withDefault(Function1<A, B1> d) {
        return Map$class.withDefault(this, d);
    }

    @Override
    public <B1> Map<A, B1> withDefaultValue(B1 d) {
        return Map$class.withDefaultValue(this, d);
    }

    @Override
    public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
        return MapLike$class.parCombiner(this);
    }

    @Override
    public <B1> Map<A, B1> updated(A key, B1 value) {
        return MapLike$class.updated(this, key, value);
    }

    @Override
    public <B1> Map<A, B1> $plus(Tuple2<A, B1> elem1, Tuple2<A, B1> elem2, Seq<Tuple2<A, B1>> elems) {
        return MapLike$class.$plus(this, elem1, elem2, elems);
    }

    @Override
    public <B1> Map<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> xs) {
        return MapLike$class.$plus$plus(this, xs);
    }

    @Override
    public Map<A, B> filterKeys(Function1<A, Object> p) {
        return MapLike$class.filterKeys(this, p);
    }

    @Override
    public <C> Map<A, C> mapValues(Function1<B, C> f) {
        return MapLike$class.mapValues(this, f);
    }

    @Override
    public Set<A> keySet() {
        return MapLike$class.keySet(this);
    }

    @Override
    public <C, That> That transform(Function2<A, B, C> f, CanBuildFrom<Map<A, B>, Tuple2<A, C>, That> bf) {
        return (That)MapLike$class.transform(this, f, bf);
    }

    @Override
    public GenericCompanion<Iterable> companion() {
        return Iterable$class.companion(this);
    }

    public AbstractMap() {
        Traversable$class.$init$(this);
        Iterable$class.$init$(this);
        MapLike$class.$init$(this);
        Map$class.$init$(this);
    }
}

