/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Function0;
import scala.Function1;
import scala.Predef$;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.collection.Parallel;
import scala.collection.Set;
import scala.collection.Set$;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.CanCombineFrom;
import scala.collection.parallel.CompositeThrowable;
import scala.collection.parallel.FactoryOps;
import scala.collection.parallel.FactoryOps$class;
import scala.collection.parallel.ParIterable;
import scala.collection.parallel.ParSeq;
import scala.collection.parallel.ParallelCollectionImplicits$;
import scala.collection.parallel.ThrowableOps;
import scala.collection.parallel.TraversableOps;
import scala.collection.parallel.TraversableOps$class;

public final class ParallelCollectionImplicits$ {
    public static final ParallelCollectionImplicits$ MODULE$;

    static {
        new ParallelCollectionImplicits$();
    }

    public <From, Elem, To> Object factory2ops(CanBuildFrom<From, Elem, To> bf) {
        return new FactoryOps<From, Elem, To>(bf){
            private final CanBuildFrom bf$1;

            public boolean isParallel() {
                return this.bf$1 instanceof Parallel;
            }

            public CanCombineFrom<From, Elem, To> asParallel() {
                return (CanCombineFrom)this.bf$1;
            }

            public <R> Object ifParallel(Function1<CanCombineFrom<From, Elem, To>, R> isbody) {
                return new FactoryOps.Otherwise<R>(this, isbody){
                    private final /* synthetic */ anon.4 $outer;
                    private final Function1 isbody$2;

                    public R otherwise(Function0<R> notbody) {
                        return this.$outer.isParallel() ? this.isbody$2.apply(this.$outer.asParallel()) : notbody.apply();
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.isbody$2 = isbody$2;
                    }
                };
            }
            {
                this.bf$1 = bf$1;
                FactoryOps$class.$init$(this);
            }
        };
    }

    public <T> Object traversable2ops(GenTraversableOnce<T> t) {
        return new TraversableOps<T>(t){
            private final GenTraversableOnce t$1;

            public boolean isParallel() {
                return this.t$1 instanceof Parallel;
            }

            public boolean isParIterable() {
                return this.t$1 instanceof ParIterable;
            }

            public ParIterable<T> asParIterable() {
                return (ParIterable)this.t$1;
            }

            public boolean isParSeq() {
                return this.t$1 instanceof ParSeq;
            }

            public ParSeq<T> asParSeq() {
                return (ParSeq)this.t$1;
            }

            public <R> Object ifParSeq(Function1<ParSeq<T>, R> isbody) {
                return new TraversableOps.Otherwise<R>(this, isbody){
                    private final /* synthetic */ anon.2 $outer;
                    private final Function1 isbody$1;

                    public R otherwise(Function0<R> notbody) {
                        return this.$outer.isParallel() ? this.isbody$1.apply(this.$outer.asParSeq()) : notbody.apply();
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.isbody$1 = isbody$1;
                    }
                };
            }
            {
                this.t$1 = t$1;
                TraversableOps$class.$init$(this);
            }
        };
    }

    public ThrowableOps throwable2ops(Throwable self) {
        return new ThrowableOps(self){
            private final Throwable self$1;

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public Throwable alongWith(Throwable that) {
                Tuple2<Throwable, Throwable> tuple2 = new Tuple2<Throwable, Throwable>(this.self$1, that);
                if (tuple2._1() instanceof CompositeThrowable) {
                    CompositeThrowable compositeThrowable = (CompositeThrowable)tuple2._1();
                    if (tuple2._2() instanceof CompositeThrowable) {
                        CompositeThrowable compositeThrowable2 = (CompositeThrowable)tuple2._2();
                        return new CompositeThrowable((Set<Throwable>)compositeThrowable.throwables().$plus$plus(compositeThrowable2.throwables()));
                    }
                }
                if (tuple2._1() instanceof CompositeThrowable) {
                    CompositeThrowable compositeThrowable = (CompositeThrowable)tuple2._1();
                    return new CompositeThrowable((Set<Throwable>)compositeThrowable.throwables().$plus(that));
                }
                if (!(tuple2._2() instanceof CompositeThrowable)) return new CompositeThrowable((Set)Set$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Throwable[]{this.self$1, that})));
                CompositeThrowable compositeThrowable = (CompositeThrowable)tuple2._2();
                return new CompositeThrowable((Set<Throwable>)compositeThrowable.throwables().$plus(this.self$1));
            }
            {
                this.self$1 = self$1;
            }
        };
    }

    private ParallelCollectionImplicits$() {
        MODULE$ = this;
    }
}

