/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent;

import java.util.NoSuchElementException;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.mutable.StringBuilder;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.Future$;
import scala.concurrent.Future$$anonfun$zip$1$;
import scala.concurrent.Future$InternalCallbackExecutor$;
import scala.concurrent.Promise;
import scala.concurrent.Promise$;
import scala.concurrent.impl.Promise;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;
import scala.util.Try$;
import scala.util.control.NonFatal$;

public abstract class Future$class {
    public static Future$InternalCallbackExecutor$ scala$concurrent$Future$$internalExecutor(Future $this) {
        return Future$InternalCallbackExecutor$.MODULE$;
    }

    public static void onSuccess(Future $this, PartialFunction pf, ExecutionContext executor) {
        $this.onComplete(new Serializable($this, pf){
            public static final long serialVersionUID = 0L;
            private final PartialFunction pf$1;

            public final Object apply(Try<T> x0$1) {
                BoxedUnit boxedUnit;
                if (x0$1 instanceof Success) {
                    Success success = (Success)x0$1;
                    boxedUnit = this.pf$1.applyOrElse(success.value(), Predef$.MODULE$.conforms());
                } else {
                    boxedUnit = BoxedUnit.UNIT;
                }
                return boxedUnit;
            }
            {
                this.pf$1 = pf$1;
            }
        }, executor);
    }

    public static void onFailure(Future $this, PartialFunction pf, ExecutionContext executor) {
        $this.onComplete(new Serializable($this, pf){
            public static final long serialVersionUID = 0L;
            private final PartialFunction pf$2;

            public final Object apply(Try<T> x0$2) {
                BoxedUnit boxedUnit;
                if (x0$2 instanceof Failure) {
                    Failure failure = (Failure)x0$2;
                    boxedUnit = this.pf$2.applyOrElse(failure.exception(), Predef$.MODULE$.conforms());
                } else {
                    boxedUnit = BoxedUnit.UNIT;
                }
                return boxedUnit;
            }
            {
                this.pf$2 = pf$2;
            }
        }, executor);
    }

    public static Future failed(Future $this) {
        Future$InternalCallbackExecutor$ ec = Future$class.scala$concurrent$Future$$internalExecutor($this);
        Promise p = Promise$.MODULE$.apply();
        $this.onComplete(new Serializable($this, p){
            public static final long serialVersionUID = 0L;
            private final Promise p$1;

            public final Promise<Throwable> apply(Try<T> x0$3) {
                block4: {
                    Promise<Throwable> promise;
                    block3: {
                        block2: {
                            if (!(x0$3 instanceof Failure)) break block2;
                            Failure failure = (Failure)x0$3;
                            promise = this.p$1.success(failure.exception());
                            break block3;
                        }
                        if (!(x0$3 instanceof Success)) break block4;
                        promise = this.p$1.failure(new NoSuchElementException("Future.failed not completed with a throwable."));
                    }
                    return promise;
                }
                throw new MatchError(x0$3);
            }
            {
                this.p$1 = p$1;
            }
        }, ec);
        return p.future();
    }

    public static void foreach(Future $this, Function1 f, ExecutionContext executor) {
        $this.onComplete(new Serializable($this, f){
            public static final long serialVersionUID = 0L;
            private final Function1 f$1;

            public final void apply(Try<T> x$1) {
                x$1.foreach(this.f$1);
            }
            {
                this.f$1 = f$1;
            }
        }, executor);
    }

    public static Future transform(Future $this, Function1 s2, Function1 f, ExecutionContext executor) {
        Promise p = Promise$.MODULE$.apply();
        $this.onComplete(new Serializable($this, p, s2, f){
            public static final long serialVersionUID = 0L;
            private final Promise p$2;
            public final Function1 s$1;
            public final Function1 f$2;

            public final Promise<S> apply(Try<T> x0$4) {
                block4: {
                    Promise<Object> promise;
                    block3: {
                        block2: {
                            if (!(x0$4 instanceof Success)) break block2;
                            Success success = (Success)x0$4;
                            promise = this.p$2.complete(Try$.MODULE$.apply(new Serializable(this, success){
                                public static final long serialVersionUID = 0L;
                                private final /* synthetic */ Future$.anonfun.transform.1 $outer;
                                private final Success x2$1;

                                public final S apply() {
                                    return (S)this.$outer.s$1.apply(this.x2$1.value());
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                    this.x2$1 = x2$1;
                                }
                            }));
                            break block3;
                        }
                        if (!(x0$4 instanceof Failure)) break block4;
                        Failure failure = (Failure)x0$4;
                        promise = this.p$2.complete(Try$.MODULE$.apply(new Serializable(this, failure){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ Future$.anonfun.transform.1 $outer;
                            private final Failure x3$1;

                            public final Nothing$ apply() {
                                throw (Throwable)this.$outer.f$2.apply(this.x3$1.exception());
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                                this.x3$1 = x3$1;
                            }
                        }));
                    }
                    return promise;
                }
                throw new MatchError(x0$4);
            }
            {
                void var4_4;
                void var3_3;
                this.p$2 = p$2;
                this.s$1 = var3_3;
                this.f$2 = var4_4;
            }
        }, executor);
        return p.future();
    }

    public static Future map(Future $this, Function1 f, ExecutionContext executor) {
        Promise p = Promise$.MODULE$.apply();
        $this.onComplete(new Serializable($this, p, f){
            public static final long serialVersionUID = 0L;
            private final Promise p$3;
            private final Function1 f$3;

            public final Promise<S> apply(Try<T> v) {
                return this.p$3.complete(v.map(this.f$3));
            }
            {
                void var3_3;
                this.p$3 = p$3;
                this.f$3 = var3_3;
            }
        }, executor);
        return p.future();
    }

    public static Future flatMap(Future $this, Function1 f, ExecutionContext executor) {
        Promise.DefaultPromise p = new Promise.DefaultPromise();
        $this.onComplete(new Serializable($this, p, f){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Future $outer;
            public final Promise.DefaultPromise p$4;
            private final Function1 f$4;

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final Object apply(Try<T> x0$5) {
                Object object;
                if (x0$5 instanceof Failure) {
                    Failure failure = (Failure)x0$5;
                    return this.p$4.complete(failure);
                }
                if (!(x0$5 instanceof Success)) throw new MatchError(x0$5);
                Success success = (Success)x0$5;
                try {
                    Future future = (Future)this.f$4.apply(success.value());
                    if (future instanceof Promise.DefaultPromise) {
                        Promise.DefaultPromise defaultPromise = (Promise.DefaultPromise)future;
                        defaultPromise.linkRootOf(this.p$4);
                    } else {
                        future.onComplete(new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ Future$.anonfun.flatMap.1 $outer;

                            public final Promise.DefaultPromise<S> apply(Try<S> result2) {
                                return (Promise.DefaultPromise)this.$outer.p$4.complete(result2);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        }, Future$class.scala$concurrent$Future$$internalExecutor(this.$outer));
                    }
                    object = BoxedUnit.UNIT;
                    return object;
                }
                catch (Throwable throwable) {
                    Option<Throwable> option = NonFatal$.MODULE$.unapply(throwable);
                    if (option.isEmpty()) {
                        throw throwable;
                    }
                    object = this.p$4.failure(option.get());
                }
                return object;
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.p$4 = p$4;
                this.f$4 = var3_3;
            }
        }, executor);
        return p.future();
    }

    public static Future filter(Future $this, Function1 p, ExecutionContext executor) {
        return $this.map(new Serializable($this, p){
            public static final long serialVersionUID = 0L;
            private final Function1 p$12;

            public final T apply(T r) {
                if (BoxesRunTime.unboxToBoolean(this.p$12.apply(r))) {
                    return r;
                }
                throw new NoSuchElementException("Future.filter predicate is not satisfied");
            }
            {
                this.p$12 = p$12;
            }
        }, executor);
    }

    public static final Future withFilter(Future $this, Function1 p, ExecutionContext executor) {
        return $this.filter(p, executor);
    }

    public static Future collect(Future $this, PartialFunction pf, ExecutionContext executor) {
        return $this.map(new Serializable($this, pf){
            public static final long serialVersionUID = 0L;
            private final PartialFunction pf$3;

            public final S apply(T r) {
                return (S)this.pf$3.applyOrElse(r, new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final Nothing$ apply(T t) {
                        throw new NoSuchElementException(new StringBuilder().append((Object)"Future.collect partial function is not defined at: ").append(t).toString());
                    }
                });
            }
            {
                this.pf$3 = pf$3;
            }
        }, executor);
    }

    public static Future recover(Future $this, PartialFunction pf, ExecutionContext executor) {
        Promise p = Promise$.MODULE$.apply();
        $this.onComplete(new Serializable($this, p, pf){
            public static final long serialVersionUID = 0L;
            private final Promise p$5;
            private final PartialFunction pf$4;

            public final Promise<U> apply(Try<T> v) {
                return this.p$5.complete(v.recover(this.pf$4));
            }
            {
                void var3_3;
                this.p$5 = p$5;
                this.pf$4 = var3_3;
            }
        }, executor);
        return p.future();
    }

    public static Future recoverWith(Future $this, PartialFunction pf, ExecutionContext executor) {
        Promise p = Promise$.MODULE$.apply();
        $this.onComplete(new Serializable($this, p, pf){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ Future $outer;
            public final Promise p$6;
            private final PartialFunction pf$5;

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final Object apply(Try<T> x0$6) {
                Object object;
                if (!(x0$6 instanceof Failure)) return this.p$6.complete(x0$6);
                Failure failure = (Failure)x0$6;
                try {
                    ((Future)this.pf$5.applyOrElse(failure.exception(), new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Future$.anonfun.recoverWith.1 $outer;

                        public final Future<T> apply(Throwable x$2) {
                            return this.$outer.$outer;
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    })).onComplete(new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Future$.anonfun.recoverWith.1 $outer;

                        public final Promise<U> apply(Try<U> result2) {
                            return this.$outer.p$6.complete(result2);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }, Future$class.scala$concurrent$Future$$internalExecutor(this.$outer));
                    object = BoxedUnit.UNIT;
                    return object;
                }
                catch (Throwable throwable) {
                    Option<Throwable> option = NonFatal$.MODULE$.unapply(throwable);
                    if (option.isEmpty()) {
                        throw throwable;
                    }
                    object = this.p$6.failure(option.get());
                }
                return object;
            }

            public /* synthetic */ Future scala$concurrent$Future$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.p$6 = p$6;
                this.pf$5 = var3_3;
            }
        }, executor);
        return p.future();
    }

    public static Future zip(Future $this, Future that) {
        Future$InternalCallbackExecutor$ ec = Future$class.scala$concurrent$Future$$internalExecutor($this);
        Promise p = Promise$.MODULE$.apply();
        $this.onComplete(new Serializable($this, ec, p, that){
            public static final long serialVersionUID = 0L;
            private final Future$InternalCallbackExecutor$ ec$1;
            public final Promise p$7;
            private final Future that$1;

            public final Object apply(Try<T> x0$7) {
                block4: {
                    Object object;
                    block3: {
                        block2: {
                            if (!(x0$7 instanceof Failure)) break block2;
                            Failure failure = (Failure)x0$7;
                            object = this.p$7.complete(failure);
                            break block3;
                        }
                        if (!(x0$7 instanceof Success)) break block4;
                        Success success = (Success)x0$7;
                        this.that$1.onComplete(new Serializable(this, success){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ Future$.anonfun.zip.1 $outer;
                            public final Success x2$2;

                            public final Promise<Tuple2<T, U>> apply(Try<U> c) {
                                return this.$outer.p$7.complete(c.map(new Serializable(this){
                                    public static final long serialVersionUID = 0L;
                                    private final /* synthetic */ Future$$anonfun$zip$1$.anonfun.apply.7 $outer;

                                    public final Tuple2<T, U> apply(U s2) {
                                        return new Tuple2<T, U>(this.$outer.x2$2.value(), s2);
                                    }
                                    {
                                        if ($outer == null) {
                                            throw null;
                                        }
                                        this.$outer = $outer;
                                    }
                                }));
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                                this.x2$2 = x2$2;
                            }
                        }, this.ec$1);
                        object = BoxedUnit.UNIT;
                    }
                    return object;
                }
                throw new MatchError(x0$7);
            }
            {
                void var4_4;
                void var3_3;
                this.ec$1 = ec$1;
                this.p$7 = var3_3;
                this.that$1 = var4_4;
            }
        }, ec);
        return p.future();
    }

    public static Future fallbackTo(Future $this, Future that) {
        Future$InternalCallbackExecutor$ ec = Future$class.scala$concurrent$Future$$internalExecutor($this);
        Promise p = Promise$.MODULE$.apply();
        $this.onComplete(new Serializable($this, ec, p, that){
            public static final long serialVersionUID = 0L;
            private final Future$InternalCallbackExecutor$ ec$2;
            public final Promise p$8;
            private final Future that$2;

            public final Object apply(Try<T> x0$8) {
                block4: {
                    Object object;
                    block3: {
                        block2: {
                            if (!(x0$8 instanceof Success)) break block2;
                            Success success = (Success)x0$8;
                            object = this.p$8.complete(success);
                            break block3;
                        }
                        if (!(x0$8 instanceof Failure)) break block4;
                        Failure failure = (Failure)x0$8;
                        this.that$2.onComplete(new Serializable(this, failure){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ Future$.anonfun.fallbackTo.1 $outer;
                            private final Failure x4$1;

                            public final Promise<U> apply(Try<U> x0$9) {
                                Promise<T> promise;
                                if (x0$9 instanceof Success) {
                                    Success success = (Success)x0$9;
                                    promise = this.$outer.p$8.complete(success);
                                } else {
                                    promise = this.$outer.p$8.complete(this.x4$1);
                                }
                                return promise;
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                                this.x4$1 = x4$1;
                            }
                        }, this.ec$2);
                        object = BoxedUnit.UNIT;
                    }
                    return object;
                }
                throw new MatchError(x0$8);
            }
            {
                void var4_4;
                void var3_3;
                this.ec$2 = ec$2;
                this.p$8 = var3_3;
                this.that$2 = var4_4;
            }
        }, ec);
        return p.future();
    }

    public static Future mapTo(Future $this, ClassTag tag) {
        Future$InternalCallbackExecutor$ ec = Future$class.scala$concurrent$Future$$internalExecutor($this);
        Class c = tag.runtimeClass();
        Class boxedClass2 = c.isPrimitive() ? (Class)Future$.MODULE$.toBoxed().apply(c) : c;
        Predef$.MODULE$.require(boxedClass2 != null);
        return $this.map(new Serializable($this, boxedClass2){
            public static final long serialVersionUID = 0L;
            private final Class boxedClass$1;

            public final S apply(T s2) {
                return (S)this.boxedClass$1.cast(s2);
            }
            {
                this.boxedClass$1 = boxedClass$1;
            }
        }, ec);
    }

    public static Future andThen(Future $this, PartialFunction pf, ExecutionContext executor) {
        Promise p = Promise$.MODULE$.apply();
        $this.onComplete(new Serializable($this, p, pf){
            public static final long serialVersionUID = 0L;
            private final Promise p$9;
            private final PartialFunction pf$6;

            public final Object apply(Try<T> x0$10) {
                A a;
                Try<T> try_ = x0$10;
                try {
                    a = this.pf$6.applyOrElse(x0$10, Predef$.MODULE$.conforms());
                    this.p$9.complete(x0$10);
                }
                catch (Throwable throwable) {
                    this.p$9.complete(try_);
                    throw throwable;
                }
                return a;
            }
            {
                void var3_3;
                this.p$9 = p$9;
                this.pf$6 = var3_3;
            }
        }, executor);
        return p.future();
    }

    public static void $init$(Future $this) {
    }
}

