/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Option;
import scala.PartialFunction;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.mutable.LinkedHashMap;
import scala.reflect.internal.util.Collections;
import scala.reflect.internal.util.Collections$class;
import scala.runtime.BoxedUnit;

public final class Collections$
implements Collections {
    public static final Collections$ MODULE$;

    static {
        new Collections$();
    }

    @Override
    public final <A, B, C> boolean corresponds3(List<A> xs1, List<B> xs2, List<C> xs3, Function3<A, B, C, Object> f) {
        return Collections$class.corresponds3(this, xs1, xs2, xs3, f);
    }

    @Override
    public final <A> boolean mexists(List<List<A>> xss, Function1<A, Object> p) {
        return Collections$class.mexists(this, xss, p);
    }

    @Override
    public final <A> boolean mforall(List<List<A>> xss, Function1<A, Object> p) {
        return Collections$class.mforall(this, xss, p);
    }

    @Override
    public final <A, B> List<List<B>> mmap(List<List<A>> xss, Function1<A, B> f) {
        return Collections$class.mmap(this, xss, f);
    }

    @Override
    public final <A> Option<A> mfind(List<List<A>> xss, Function1<A, Object> p) {
        return Collections$class.mfind(this, xss, p);
    }

    @Override
    public final <A> void mforeach(List<List<A>> xss, Function1<A, BoxedUnit> f) {
        Collections$class.mforeach((Collections)this, xss, f);
    }

    @Override
    public final <A> void mforeach(Traversable<Traversable<A>> xss, Function1<A, BoxedUnit> f) {
        Collections$class.mforeach((Collections)this, xss, f);
    }

    @Override
    public final <A, B> List<B> mapList(List<A> as, Function1<A, B> f) {
        return Collections$class.mapList(this, as, f);
    }

    @Override
    public final <A, B> Option<B> collectFirst(List<A> as, PartialFunction<A, B> pf) {
        return Collections$class.collectFirst(this, as, pf);
    }

    @Override
    public final <A, B, C> List<C> map2(List<A> xs1, List<B> xs2, Function2<A, B, C> f) {
        return Collections$class.map2(this, xs1, xs2, f);
    }

    @Override
    public final <A, B> List<A> map2Conserve(List<A> xs, List<B> ys, Function2<A, B, A> f) {
        return Collections$class.map2Conserve(this, xs, ys, f);
    }

    @Override
    public final <A, B, C, D> List<D> map3(List<A> xs1, List<B> xs2, List<C> xs3, Function3<A, B, C, D> f) {
        return Collections$class.map3(this, xs1, xs2, xs3, f);
    }

    @Override
    public final <A, B, C> List<C> flatMap2(List<A> xs1, List<B> xs2, Function2<A, B, List<C>> f) {
        return Collections$class.flatMap2(this, xs1, xs2, f);
    }

    @Override
    public final <A, B> List<B> flatCollect(List<A> elems, PartialFunction<A, Traversable<B>> pf) {
        return Collections$class.flatCollect(this, elems, pf);
    }

    @Override
    public final <A, B> List<A> distinctBy(List<A> xs, Function1<A, B> f) {
        return Collections$class.distinctBy(this, xs, f);
    }

    @Override
    public final boolean flattensToEmpty(Seq<Seq<?>> xss) {
        return Collections$class.flattensToEmpty(this, xss);
    }

    @Override
    public final <A, B> void foreachWithIndex(List<A> xs, Function2<A, Object, BoxedUnit> f) {
        Collections$class.foreachWithIndex(this, xs, f);
    }

    @Override
    public final <A> A findOrElse(TraversableOnce<A> xs, Function1<A, Object> p, Function0<A> orElse) {
        return (A)Collections$class.findOrElse(this, xs, p, orElse);
    }

    @Override
    public final <A, A1, B> Map<A1, B> mapFrom(List<A> xs, Function1<A, B> f) {
        return Collections$class.mapFrom(this, xs, f);
    }

    @Override
    public final <A, A1, B> LinkedHashMap<A1, B> linkedMapFrom(List<A> xs, Function1<A, B> f) {
        return Collections$class.linkedMapFrom(this, xs, f);
    }

    @Override
    public final <A, B> List<B> mapWithIndex(List<A> xs, Function2<A, Object, B> f) {
        return Collections$class.mapWithIndex(this, xs, f);
    }

    @Override
    public final <A, B, C> Map<A, B> collectMap2(List<A> xs1, List<B> xs2, Function2<A, B, Object> p) {
        return Collections$class.collectMap2(this, xs1, xs2, p);
    }

    @Override
    public final <A, B> void foreach2(List<A> xs1, List<B> xs2, Function2<A, B, BoxedUnit> f) {
        Collections$class.foreach2(this, xs1, xs2, f);
    }

    @Override
    public final <A, B, C> void foreach3(List<A> xs1, List<B> xs2, List<C> xs3, Function3<A, B, C, BoxedUnit> f) {
        Collections$class.foreach3(this, xs1, xs2, xs3, f);
    }

    @Override
    public final <A, B> boolean exists2(List<A> xs1, List<B> xs2, Function2<A, B, Object> f) {
        return Collections$class.exists2(this, xs1, xs2, f);
    }

    @Override
    public final <A, B, C> boolean exists3(List<A> xs1, List<B> xs2, List<C> xs3, Function3<A, B, C, Object> f) {
        return Collections$class.exists3(this, xs1, xs2, xs3, f);
    }

    @Override
    public final <A, B, C> boolean forall3(List<A> xs1, List<B> xs2, List<C> xs3, Function3<A, B, C, Object> f) {
        return Collections$class.forall3(this, xs1, xs2, xs3, f);
    }

    @Override
    public final <A> Option<List<A>> sequence(List<Option<A>> as) {
        return Collections$class.sequence(this, as);
    }

    @Override
    public final <A> Option<List<List<A>>> transposeSafe(List<List<A>> ass) {
        return Collections$class.transposeSafe(this, ass);
    }

    private Collections$() {
        MODULE$ = this;
        Collections$class.$init$(this);
    }
}

