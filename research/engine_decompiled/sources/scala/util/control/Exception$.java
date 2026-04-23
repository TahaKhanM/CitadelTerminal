/*
 * Decompiled with CFR 0.152.
 */
package scala.util.control;

import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.PartialFunction$class;
import scala.Serializable;
import scala.Some;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.TraversableOnce;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.package$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.util.control.ControlThrowable;
import scala.util.control.Exception;
import scala.util.control.Exception$Catch$;
import scala.util.control.NonFatal$;

public final class Exception$ {
    public static final Exception$ MODULE$;
    private final PartialFunction<Throwable, Nothing$> nothingCatcher;
    private final Exception.Catch<Nothing$> noCatch;

    static {
        new Exception$();
    }

    public <Ex extends Throwable, T> Object mkCatcher(Function1<Ex, Object> isDef, Function1<Ex, T> f, ClassTag<Ex> evidence$1) {
        return new PartialFunction<Throwable, T>(isDef, f, evidence$1){
            private final Function1 isDef$1;
            private final Function1 f$1;
            private final ClassTag evidence$1$1;

            public <A1 extends Throwable, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> that) {
                return PartialFunction$class.orElse(this, that);
            }

            public <C> PartialFunction<Throwable, C> andThen(Function1<T, C> k) {
                return PartialFunction$class.andThen(this, k);
            }

            public Function1<Throwable, Option<T>> lift() {
                return PartialFunction$class.lift(this);
            }

            public Object applyOrElse(Object x, Function1 function1) {
                return PartialFunction$class.applyOrElse(this, x, function1);
            }

            public <U> Function1<Throwable, Object> runWith(Function1<T, U> action) {
                return PartialFunction$class.runWith(this, action);
            }

            public boolean apply$mcZD$sp(double v1) {
                return Function1$class.apply$mcZD$sp(this, v1);
            }

            public double apply$mcDD$sp(double v1) {
                return Function1$class.apply$mcDD$sp(this, v1);
            }

            public float apply$mcFD$sp(double v1) {
                return Function1$class.apply$mcFD$sp(this, v1);
            }

            public int apply$mcID$sp(double v1) {
                return Function1$class.apply$mcID$sp(this, v1);
            }

            public long apply$mcJD$sp(double v1) {
                return Function1$class.apply$mcJD$sp(this, v1);
            }

            public void apply$mcVD$sp(double v1) {
                Function1$class.apply$mcVD$sp(this, v1);
            }

            public boolean apply$mcZF$sp(float v1) {
                return Function1$class.apply$mcZF$sp(this, v1);
            }

            public double apply$mcDF$sp(float v1) {
                return Function1$class.apply$mcDF$sp(this, v1);
            }

            public float apply$mcFF$sp(float v1) {
                return Function1$class.apply$mcFF$sp(this, v1);
            }

            public int apply$mcIF$sp(float v1) {
                return Function1$class.apply$mcIF$sp(this, v1);
            }

            public long apply$mcJF$sp(float v1) {
                return Function1$class.apply$mcJF$sp(this, v1);
            }

            public void apply$mcVF$sp(float v1) {
                Function1$class.apply$mcVF$sp(this, v1);
            }

            public boolean apply$mcZI$sp(int v1) {
                return Function1$class.apply$mcZI$sp(this, v1);
            }

            public double apply$mcDI$sp(int v1) {
                return Function1$class.apply$mcDI$sp(this, v1);
            }

            public float apply$mcFI$sp(int v1) {
                return Function1$class.apply$mcFI$sp(this, v1);
            }

            public int apply$mcII$sp(int v1) {
                return Function1$class.apply$mcII$sp(this, v1);
            }

            public long apply$mcJI$sp(int v1) {
                return Function1$class.apply$mcJI$sp(this, v1);
            }

            public void apply$mcVI$sp(int v1) {
                Function1$class.apply$mcVI$sp(this, v1);
            }

            public boolean apply$mcZJ$sp(long v1) {
                return Function1$class.apply$mcZJ$sp(this, v1);
            }

            public double apply$mcDJ$sp(long v1) {
                return Function1$class.apply$mcDJ$sp(this, v1);
            }

            public float apply$mcFJ$sp(long v1) {
                return Function1$class.apply$mcFJ$sp(this, v1);
            }

            public int apply$mcIJ$sp(long v1) {
                return Function1$class.apply$mcIJ$sp(this, v1);
            }

            public long apply$mcJJ$sp(long v1) {
                return Function1$class.apply$mcJJ$sp(this, v1);
            }

            public void apply$mcVJ$sp(long v1) {
                Function1$class.apply$mcVJ$sp(this, v1);
            }

            public <A> Function1<A, T> compose(Function1<A, Throwable> g) {
                return Function1$class.compose(this, g);
            }

            public String toString() {
                return Function1$class.toString(this);
            }

            private Option<Ex> downcast(Throwable x) {
                return package$.MODULE$.classTag(this.evidence$1$1).runtimeClass().isAssignableFrom(x.getClass()) ? new Some<Throwable>(x) : None$.MODULE$;
            }

            public boolean isDefinedAt(Throwable x) {
                Function1 function1 = this.isDef$1;
                Option<Ex> option = this.downcast(x);
                return !option.isEmpty() && BoxesRunTime.unboxToBoolean(function1.apply(option.get()));
            }

            public T apply(Throwable x) {
                return (T)this.f$1.apply(this.downcast(x).get());
            }
            {
                this.isDef$1 = isDef$1;
                this.f$1 = f$1;
                this.evidence$1$1 = evidence$1$1;
                Function1$class.$init$(this);
                PartialFunction$class.$init$(this);
            }
        };
    }

    public <T> Object mkThrowableCatcher(Function1<Throwable, Object> isDef, Function1<Throwable, T> f) {
        ClassTag classTag = ClassTag$.MODULE$.apply(Throwable.class);
        return new /* invalid duplicate definition of identical inner class */;
    }

    public <Ex extends Throwable, T> Object throwableSubtypeToCatcher(PartialFunction<Ex, T> pf, ClassTag<Ex> evidence$2) {
        Serializable serializable = new Serializable(pf){
            public static final long serialVersionUID = 0L;
            private final PartialFunction pf$1;

            public final T apply(Ex v1) {
                return (T)this.pf$1.apply(v1);
            }
            {
                this.pf$1 = pf$1;
            }
        };
        Serializable serializable2 = new Serializable(pf){
            public static final long serialVersionUID = 0L;
            private final PartialFunction pf$1;

            public final boolean apply(Ex x) {
                return this.pf$1.isDefinedAt(x);
            }
            {
                this.pf$1 = pf$1;
            }
        };
        return new /* invalid duplicate definition of identical inner class */;
    }

    public boolean shouldRethrow(Throwable x) {
        boolean bl = x instanceof ControlThrowable ? true : x instanceof InterruptedException;
        return bl;
    }

    public final PartialFunction<Throwable, Nothing$> nothingCatcher() {
        return this.nothingCatcher;
    }

    public final <T> PartialFunction<Throwable, T> nonFatalCatcher() {
        return this.mkThrowableCatcher((Function1)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Throwable x0$1) {
                Option<Throwable> option = NonFatal$.MODULE$.unapply(x0$1);
                boolean bl = !option.isEmpty();
                return bl;
            }
        }), (Function1)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final Nothing$ apply(Throwable x$6) {
                throw x$6;
            }
        }));
    }

    public final <T> PartialFunction<Throwable, T> allCatcher() {
        Serializable serializable = new Serializable(){
            public static final long serialVersionUID = 0L;

            public final Nothing$ apply(Throwable x$8) {
                throw x$8;
            }
        };
        Serializable serializable2 = new Serializable(){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Throwable x$7) {
                return true;
            }
        };
        ClassTag classTag = ClassTag$.MODULE$.apply(Throwable.class);
        return new /* invalid duplicate definition of identical inner class */;
    }

    public final Exception.Catch<Nothing$> noCatch() {
        return this.noCatch;
    }

    public final <T> Exception.Catch<T> allCatch() {
        return (Exception.Catch)new Exception.Catch<T>(this.allCatcher(), Exception$Catch$.MODULE$.$lessinit$greater$default$2(), Exception$Catch$.MODULE$.$lessinit$greater$default$3()).withDesc("<everything>");
    }

    public final <T> Exception.Catch<T> nonFatalCatch() {
        return (Exception.Catch)new Exception.Catch<T>(this.nonFatalCatcher(), Exception$Catch$.MODULE$.$lessinit$greater$default$2(), Exception$Catch$.MODULE$.$lessinit$greater$default$3()).withDesc("<non-fatal>");
    }

    public <T> Exception.Catch<T> catching(Seq<Class<?>> exceptions2) {
        return (Exception.Catch)new Exception.Catch<Nothing$>(this.pfFromExceptions(exceptions2), Exception$Catch$.MODULE$.$lessinit$greater$default$2(), Exception$Catch$.MODULE$.$lessinit$greater$default$3()).withDesc(((TraversableOnce)exceptions2.map(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final String apply(Class<?> x$9) {
                return x$9.getName();
            }
        }, Seq$.MODULE$.canBuildFrom())).mkString(", "));
    }

    public <T> Exception.Catch<T> catching(PartialFunction<Throwable, T> c) {
        return new Exception.Catch<T>(c, Exception$Catch$.MODULE$.$lessinit$greater$default$2(), Exception$Catch$.MODULE$.$lessinit$greater$default$3());
    }

    public <T> Exception.Catch<T> catchingPromiscuously(Seq<Class<?>> exceptions2) {
        return this.catchingPromiscuously(this.pfFromExceptions(exceptions2));
    }

    public <T> Exception.Catch<T> catchingPromiscuously(PartialFunction<Throwable, T> c) {
        return new Exception.Catch<T>(c, None$.MODULE$, (Function1<Throwable, Object>)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Throwable x$10) {
                return false;
            }
        }));
    }

    public Exception.Catch<BoxedUnit> ignoring(Seq<Class<?>> exceptions2) {
        return this.catching(exceptions2).withApply(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final void apply(Throwable x$11) {
            }
        });
    }

    public <T> Exception.Catch<Option<T>> failing(Seq<Class<?>> exceptions2) {
        return this.catching(exceptions2).withApply(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final None$ apply(Throwable x$12) {
                return None$.MODULE$;
            }
        });
    }

    public <T> Exception.Catch<T> failAsValue(Seq<Class<?>> exceptions2, Function0<T> value) {
        return this.catching(exceptions2).withApply(new Serializable(value){
            public static final long serialVersionUID = 0L;
            private final Function0 value$1;

            public final T apply(Throwable x$13) {
                return (T)this.value$1.apply();
            }
            {
                this.value$1 = value$1;
            }
        });
    }

    public <T> Exception.By<Function1<Throwable, T>, Exception.Catch<T>> handling(Seq<Class<?>> exceptions2) {
        return new Exception.By<Function1<Throwable, T>, Exception.Catch<T>>(new Serializable(exceptions2){
            public static final long serialVersionUID = 0L;
            private final Seq exceptions$2;

            public final Exception.Catch<T> apply(Function1<Throwable, T> f) {
                return Exception$.MODULE$.scala$util$control$Exception$$fun$1(f, this.exceptions$2);
            }
            {
                this.exceptions$2 = exceptions$2;
            }
        });
    }

    public <T> Exception.Catch<T> ultimately(Function0<BoxedUnit> body2) {
        return this.noCatch().andFinally(body2);
    }

    public <T> Exception.Catch<T> unwrapping(Seq<Class<?>> exceptions2) {
        return this.catching(exceptions2).withApply(new Serializable(exceptions2){
            public static final long serialVersionUID = 0L;
            private final Seq exceptions$3;

            public final Nothing$ apply(Throwable x) {
                throw Exception$.MODULE$.scala$util$control$Exception$$unwrap$1(x, this.exceptions$3);
            }
            {
                this.exceptions$3 = exceptions$3;
            }
        });
    }

    public boolean scala$util$control$Exception$$wouldMatch(Throwable x, Seq<Class<?>> classes) {
        return classes.exists((Function1<Class<?>, Object>)((Object)new Serializable(x){
            public static final long serialVersionUID = 0L;
            private final Throwable x$15;

            public final boolean apply(Class<?> x$14) {
                return x$14.isAssignableFrom(this.x$15.getClass());
            }
            {
                this.x$15 = x$15;
            }
        }));
    }

    private PartialFunction<Throwable, Nothing$> pfFromExceptions(Seq<Class<?>> exceptions2) {
        return new Serializable(exceptions2){
            public static final long serialVersionUID = 0L;
            private final Seq exceptions$1;

            public final <A1 extends Throwable, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                if (Exception$.MODULE$.scala$util$control$Exception$$wouldMatch(x1, this.exceptions$1)) {
                    throw x1;
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(Throwable x1) {
                boolean bl = Exception$.MODULE$.scala$util$control$Exception$$wouldMatch(x1, this.exceptions$1);
                return bl;
            }
            {
                this.exceptions$1 = exceptions$1;
            }
        };
    }

    public final Exception.Catch scala$util$control$Exception$$fun$1(Function1 f, Seq exceptions$2) {
        return this.catching(exceptions$2).withApply(f);
    }

    public final Throwable scala$util$control$Exception$$unwrap$1(Throwable x, Seq exceptions$3) {
        while (this.scala$util$control$Exception$$wouldMatch(x, exceptions$3) && x.getCause() != null) {
            x = x.getCause();
        }
        return x;
    }

    private Exception$() {
        MODULE$ = this;
        Serializable serializable = new Serializable(){
            public static final long serialVersionUID = 0L;

            public final Nothing$ apply(Throwable x$5) {
                throw x$5;
            }
        };
        Serializable serializable2 = new Serializable(){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Throwable x$4) {
                return false;
            }
        };
        ClassTag classTag = ClassTag$.MODULE$.apply(Throwable.class);
        this.nothingCatcher = new /* invalid duplicate definition of identical inner class */;
        Exception$Catch$ exception$Catch$ = Exception$Catch$.MODULE$;
        Exception$Catch$ exception$Catch$2 = Exception$Catch$.MODULE$;
        this.noCatch = (Exception.Catch)new Exception.Catch<Nothing$>(this.nothingCatcher(), None$.MODULE$, (Function1<Throwable, Object>)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Throwable x) {
                return Exception$.MODULE$.shouldRethrow(x);
            }
        })).withDesc("<nothing>");
    }
}

