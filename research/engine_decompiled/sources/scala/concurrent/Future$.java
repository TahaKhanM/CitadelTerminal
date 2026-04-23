/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.Map;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.Future$;
import scala.concurrent.Future$InternalCallbackExecutor$;
import scala.concurrent.Promise;
import scala.concurrent.Promise$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Success;
import scala.util.Try;

public final class Future$ {
    public static final Future$ MODULE$;
    private final Map<Class<?>, Class<?>> toBoxed;

    static {
        new Future$();
    }

    public Map<Class<?>, Class<?>> toBoxed() {
        return this.toBoxed;
    }

    public <T> Future<T> failed(Throwable exception) {
        return Promise$.MODULE$.failed(exception).future();
    }

    public <T> Future<T> successful(T result2) {
        return Promise$.MODULE$.successful(result2).future();
    }

    public <T> Future<T> fromTry(Try<T> result2) {
        return Promise$.MODULE$.fromTry(result2).future();
    }

    public <T> Future<T> apply(Function0<T> body2, ExecutionContext executor) {
        return scala.concurrent.impl.Future$.MODULE$.apply(body2, executor);
    }

    public <A, M extends TraversableOnce<Object>> Future<M> sequence(M in, CanBuildFrom<M, A, M> cbf, ExecutionContext executor) {
        return in.foldLeft(this.successful(cbf.apply(in)), new Serializable(executor){
            public static final long serialVersionUID = 0L;
            public final ExecutionContext executor$1;

            public final Future<Builder<A, M>> apply(Future<Builder<A, M>> fr, Future<A> fa) {
                return fr.flatMap(new Serializable(this, fa){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ anonfun.sequence.1 $outer;
                    private final Future fa$1;

                    public final Future<Builder<A, M>> apply(Builder<A, M> r) {
                        return this.fa$1.map(new Serializable(this, r){
                            public static final long serialVersionUID = 0L;
                            private final Builder r$1;

                            public final Builder<A, M> apply(A a) {
                                return this.r$1.$plus$eq(a);
                            }
                            {
                                this.r$1 = r$1;
                            }
                        }, this.$outer.executor$1);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.fa$1 = fa$1;
                    }
                }, this.executor$1);
            }
            {
                this.executor$1 = executor$1;
            }
        }).map(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final M apply(Builder<A, M> x$3) {
                return (M)((TraversableOnce)x$3.result());
            }
        }, Future$InternalCallbackExecutor$.MODULE$);
    }

    public <T> Future<T> firstCompletedOf(TraversableOnce<Future<T>> futures, ExecutionContext executor) {
        Promise p = Promise$.MODULE$.apply();
        Serializable completeFirst = new Serializable(p){
            public static final long serialVersionUID = 0L;
            private final Promise p$10;

            public final void apply(Try<T> x$4) {
                this.p$10.tryComplete(x$4);
            }
            {
                this.p$10 = p$10;
            }
        };
        futures.foreach(new Serializable(executor, (Function1)((Object)completeFirst)){
            public static final long serialVersionUID = 0L;
            private final ExecutionContext executor$2;
            private final Function1 completeFirst$1;

            public final void apply(Future<T> x$5) {
                x$5.onComplete(this.completeFirst$1, this.executor$2);
            }
            {
                this.executor$2 = executor$2;
                this.completeFirst$1 = completeFirst$1;
            }
        });
        return p.future();
    }

    public <T> Future<Option<T>> find(TraversableOnce<Future<T>> futures, Function1<T, Object> p, ExecutionContext executor) {
        Future<Option<T>> future;
        Buffer futuresBuffer = futures.toBuffer();
        if (futuresBuffer.isEmpty()) {
            future = this.successful(None$.MODULE$);
        } else {
            Promise result2 = Promise$.MODULE$.apply();
            AtomicInteger ref = new AtomicInteger(futuresBuffer.size());
            Serializable search = new Serializable(p, result2, ref){
                public static final long serialVersionUID = 0L;
                private final Function1 p$11;
                private final Promise result$1;
                private final AtomicInteger ref$1;

                public final void apply(Try<T> v) {
                    try {
                        Success success;
                        if (v instanceof Success && BoxesRunTime.unboxToBoolean(this.p$11.apply((success = (Success)v).value()))) {
                            this.result$1.tryComplete(new Success<Some<T>>(new Some<T>(success.value())));
                        }
                        return;
                    }
                    finally {
                        if (this.ref$1.decrementAndGet() == 0) {
                            this.result$1.tryComplete(new Success<None$>(None$.MODULE$));
                        }
                    }
                }
                {
                    this.p$11 = p$11;
                    this.result$1 = result$1;
                    this.ref$1 = ref$1;
                }
            };
            futuresBuffer.foreach(new Serializable(executor, (Function1)((Object)search)){
                public static final long serialVersionUID = 0L;
                private final ExecutionContext executor$3;
                private final Function1 search$1;

                public final void apply(Future<T> x$6) {
                    x$6.onComplete(this.search$1, this.executor$3);
                }
                {
                    this.executor$3 = executor$3;
                    this.search$1 = search$1;
                }
            });
            future = result2.future();
        }
        return future;
    }

    public <T, R> Future<R> fold(TraversableOnce<Future<T>> futures, R zero, Function2<R, T, R> op, ExecutionContext executor) {
        return futures.isEmpty() ? this.successful(zero) : this.sequence(futures, TraversableOnce$.MODULE$.OnceCanBuildFrom(), executor).map(new Serializable(zero, op){
            public static final long serialVersionUID = 0L;
            private final Object zero$1;
            private final Function2 op$1;

            public final R apply(TraversableOnce<T> x$7) {
                return (R)x$7.foldLeft(this.zero$1, this.op$1);
            }
            {
                this.zero$1 = zero$1;
                this.op$1 = op$1;
            }
        }, executor);
    }

    public <T, R> Future<R> reduce(TraversableOnce<Future<T>> futures, Function2<R, T, R> op, ExecutionContext executor) {
        return futures.isEmpty() ? this.failed(new NoSuchElementException("reduce attempted on empty collection")) : this.sequence(futures, TraversableOnce$.MODULE$.OnceCanBuildFrom(), executor).map(new Serializable(op){
            public static final long serialVersionUID = 0L;
            private final Function2 op$2;

            public final R apply(TraversableOnce<T> x$8) {
                return (R)x$8.reduceLeft(this.op$2);
            }
            {
                this.op$2 = op$2;
            }
        }, executor);
    }

    public <A, B, M extends TraversableOnce<Object>> Future<M> traverse(M in, Function1<A, Future<B>> fn, CanBuildFrom<M, B, M> cbf, ExecutionContext executor) {
        return in.foldLeft(this.successful(cbf.apply(in)), new Serializable(fn, executor){
            public static final long serialVersionUID = 0L;
            private final Function1 fn$1;
            public final ExecutionContext executor$4;

            public final Future<Builder<B, M>> apply(Future<Builder<B, M>> fr, A a) {
                Future fb = (Future)this.fn$1.apply(a);
                return fr.flatMap(new Serializable(this, fb){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ anonfun.traverse.1 $outer;
                    private final Future fb$1;

                    public final Future<Builder<B, M>> apply(Builder<B, M> r) {
                        return this.fb$1.map(new Serializable(this, r){
                            public static final long serialVersionUID = 0L;
                            private final Builder r$2;

                            public final Builder<B, M> apply(B b) {
                                return this.r$2.$plus$eq(b);
                            }
                            {
                                this.r$2 = r$2;
                            }
                        }, this.$outer.executor$4);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.fb$1 = fb$1;
                    }
                }, this.executor$4);
            }
            {
                this.fn$1 = fn$1;
                this.executor$4 = executor$4;
            }
        }).map(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final M apply(Builder<B, M> x$9) {
                return (M)((TraversableOnce)x$9.result());
            }
        }, executor);
    }

    private Future$() {
        MODULE$ = this;
        Tuple2[] tuple2Array = new Tuple2[9];
        Class<Boolean> clazz = Predef$.MODULE$.ArrowAssoc(Boolean.TYPE);
        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[0] = new Tuple2<Class<Boolean>, Class<Boolean>>(clazz, Boolean.class);
        Class<Byte> clazz2 = Predef$.MODULE$.ArrowAssoc(Byte.TYPE);
        Predef$ArrowAssoc$ predef$ArrowAssoc$2 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[1] = new Tuple2<Class<Byte>, Class<Byte>>(clazz2, Byte.class);
        Class<Character> clazz3 = Predef$.MODULE$.ArrowAssoc(Character.TYPE);
        Predef$ArrowAssoc$ predef$ArrowAssoc$3 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[2] = new Tuple2<Class<Character>, Class<Character>>(clazz3, Character.class);
        Class<Short> clazz4 = Predef$.MODULE$.ArrowAssoc(Short.TYPE);
        Predef$ArrowAssoc$ predef$ArrowAssoc$4 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[3] = new Tuple2<Class<Short>, Class<Short>>(clazz4, Short.class);
        Class<Integer> clazz5 = Predef$.MODULE$.ArrowAssoc(Integer.TYPE);
        Predef$ArrowAssoc$ predef$ArrowAssoc$5 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[4] = new Tuple2<Class<Integer>, Class<Integer>>(clazz5, Integer.class);
        Class<Long> clazz6 = Predef$.MODULE$.ArrowAssoc(Long.TYPE);
        Predef$ArrowAssoc$ predef$ArrowAssoc$6 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[5] = new Tuple2<Class<Long>, Class<Long>>(clazz6, Long.class);
        Class<Float> clazz7 = Predef$.MODULE$.ArrowAssoc(Float.TYPE);
        Predef$ArrowAssoc$ predef$ArrowAssoc$7 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[6] = new Tuple2<Class<Float>, Class<Float>>(clazz7, Float.class);
        Class<Double> clazz8 = Predef$.MODULE$.ArrowAssoc(Double.TYPE);
        Predef$ArrowAssoc$ predef$ArrowAssoc$8 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[7] = new Tuple2<Class<Double>, Class<Double>>(clazz8, Double.class);
        Class<Void> clazz9 = Predef$.MODULE$.ArrowAssoc(BoxedUnit.TYPE);
        Predef$ArrowAssoc$ predef$ArrowAssoc$9 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[8] = new Tuple2<Class<Void>, Class<BoxedUnit>>(clazz9, BoxedUnit.class);
        this.toBoxed = (Map)Predef$.MODULE$.Map().apply(Predef$.MODULE$.wrapRefArray((Object[])tuple2Array));
    }
}

