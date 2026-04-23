/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.PartialFunction$;
import scala.Serializable;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.Tuple5;
import scala.collection.Seq;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public final class Function$ {
    public static final Function$ MODULE$;

    static {
        new Function$();
    }

    public <a> Function1<a, a> chain(Seq<Function1<a, a>> fs) {
        return new Serializable(fs){
            public static final long serialVersionUID = 0L;
            private final Seq fs$1;

            public final a apply(a x) {
                return this.fs$1.$div$colon(x, new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final a apply(a x, Function1<a, a> f) {
                        return f.apply(x);
                    }
                });
            }
            {
                this.fs$1 = fs$1;
            }
        };
    }

    public <T, U> T const(T x, U y) {
        return x;
    }

    public <T, R> PartialFunction<T, R> unlift(Function1<T, Option<R>> f) {
        return PartialFunction$.MODULE$.unlifted(f);
    }

    public <a1, a2, b> Function2<a1, a2, b> uncurried(Function1<a1, Function1<a2, b>> f) {
        return new Serializable(f){
            public static final long serialVersionUID = 0L;
            private final Function1 f$1;

            public final b apply(a1 x1, a2 x2) {
                return (b)((Function1)this.f$1.apply(x1)).apply(x2);
            }
            {
                this.f$1 = f$1;
            }
        };
    }

    public <a1, a2, a3, b> Function3<a1, a2, a3, b> uncurried(Function1<a1, Function1<a2, Function1<a3, b>>> f) {
        return new Serializable(f){
            public static final long serialVersionUID = 0L;
            private final Function1 f$2;

            public final b apply(a1 x1, a2 x2, a3 x3) {
                return (b)((Function1)((Function1)this.f$2.apply(x1)).apply(x2)).apply(x3);
            }
            {
                this.f$2 = f$2;
            }
        };
    }

    public <a1, a2, a3, a4, b> Function4<a1, a2, a3, a4, b> uncurried(Function1<a1, Function1<a2, Function1<a3, Function1<a4, b>>>> f) {
        return new Serializable(f){
            public static final long serialVersionUID = 0L;
            private final Function1 f$3;

            public final b apply(a1 x1, a2 x2, a3 x3, a4 x4) {
                return (b)((Function1)((Function1)((Function1)this.f$3.apply(x1)).apply(x2)).apply(x3)).apply(x4);
            }
            {
                this.f$3 = f$3;
            }
        };
    }

    public <a1, a2, a3, a4, a5, b> Function5<a1, a2, a3, a4, a5, b> uncurried(Function1<a1, Function1<a2, Function1<a3, Function1<a4, Function1<a5, b>>>>> f) {
        return new Serializable(f){
            public static final long serialVersionUID = 0L;
            private final Function1 f$4;

            public final b apply(a1 x1, a2 x2, a3 x3, a4 x4, a5 x5) {
                return (b)((Function1)((Function1)((Function1)((Function1)this.f$4.apply(x1)).apply(x2)).apply(x3)).apply(x4)).apply(x5);
            }
            {
                this.f$4 = f$4;
            }
        };
    }

    public <a1, a2, b> Function1<Tuple2<a1, a2>, b> tupled(Function2<a1, a2, b> f) {
        return new Serializable(f){
            public static final long serialVersionUID = 0L;
            private final Function2 f$5;

            public final b apply(Tuple2<a1, a2> x0$1) {
                if (x0$1 != null) {
                    return (b)this.f$5.apply(x0$1._1(), x0$1._2());
                }
                throw new MatchError(x0$1);
            }
            {
                this.f$5 = f$5;
            }
        };
    }

    public <a1, a2, a3, b> Function1<Tuple3<a1, a2, a3>, b> tupled(Function3<a1, a2, a3, b> f) {
        return new Serializable(f){
            public static final long serialVersionUID = 0L;
            private final Function3 f$6;

            public final b apply(Tuple3<a1, a2, a3> x0$2) {
                if (x0$2 != null) {
                    return (b)this.f$6.apply(x0$2._1(), x0$2._2(), x0$2._3());
                }
                throw new MatchError(x0$2);
            }
            {
                this.f$6 = f$6;
            }
        };
    }

    public <a1, a2, a3, a4, b> Function1<Tuple4<a1, a2, a3, a4>, b> tupled(Function4<a1, a2, a3, a4, b> f) {
        return new Serializable(f){
            public static final long serialVersionUID = 0L;
            private final Function4 f$7;

            public final b apply(Tuple4<a1, a2, a3, a4> x0$3) {
                if (x0$3 != null) {
                    return (b)this.f$7.apply(x0$3._1(), x0$3._2(), x0$3._3(), x0$3._4());
                }
                throw new MatchError(x0$3);
            }
            {
                this.f$7 = f$7;
            }
        };
    }

    public <a1, a2, a3, a4, a5, b> Function1<Tuple5<a1, a2, a3, a4, a5>, b> tupled(Function5<a1, a2, a3, a4, a5, b> f) {
        return new Serializable(f){
            public static final long serialVersionUID = 0L;
            private final Function5 f$8;

            public final b apply(Tuple5<a1, a2, a3, a4, a5> x0$4) {
                if (x0$4 != null) {
                    return (b)this.f$8.apply(x0$4._1(), x0$4._2(), x0$4._3(), x0$4._4(), x0$4._5());
                }
                throw new MatchError(x0$4);
            }
            {
                this.f$8 = f$8;
            }
        };
    }

    public <a1, a2, b> Function2<a1, a2, b> untupled(Function1<Tuple2<a1, a2>, b> f) {
        return new Serializable(f){
            public static final long serialVersionUID = 0L;
            private final Function1 f$9;

            public final b apply(a1 x1, a2 x2) {
                return (b)this.f$9.apply(new Tuple2<a1, a2>(x1, x2));
            }
            {
                this.f$9 = f$9;
            }
        };
    }

    public <a1, a2, a3, b> Function3<a1, a2, a3, b> untupled(Function1<Tuple3<a1, a2, a3>, b> f) {
        return new Serializable(f){
            public static final long serialVersionUID = 0L;
            private final Function1 f$10;

            public final b apply(a1 x1, a2 x2, a3 x3) {
                return (b)this.f$10.apply(new Tuple3<a1, a2, a3>(x1, x2, x3));
            }
            {
                this.f$10 = f$10;
            }
        };
    }

    public <a1, a2, a3, a4, b> Function4<a1, a2, a3, a4, b> untupled(Function1<Tuple4<a1, a2, a3, a4>, b> f) {
        return new Serializable(f){
            public static final long serialVersionUID = 0L;
            private final Function1 f$11;

            public final b apply(a1 x1, a2 x2, a3 x3, a4 x4) {
                return (b)this.f$11.apply(new Tuple4<a1, a2, a3, a4>(x1, x2, x3, x4));
            }
            {
                this.f$11 = f$11;
            }
        };
    }

    public <a1, a2, a3, a4, a5, b> Function5<a1, a2, a3, a4, a5, b> untupled(Function1<Tuple5<a1, a2, a3, a4, a5>, b> f) {
        return new Serializable(f){
            public static final long serialVersionUID = 0L;
            private final Function1 f$12;

            public final b apply(a1 x1, a2 x2, a3 x3, a4 x4, a5 x5) {
                return (b)this.f$12.apply(new Tuple5<a1, a2, a3, a4, a5>(x1, x2, x3, x4, x5));
            }
            {
                this.f$12 = f$12;
            }
        };
    }

    private Function$() {
        MODULE$ = this;
    }
}

