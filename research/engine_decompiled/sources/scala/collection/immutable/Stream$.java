/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function0;
import scala.Function1;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.collection.Seq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Stream$Empty$;
import scala.collection.immutable.Stream$cons$;
import scala.collection.mutable.Builder;
import scala.math.Integral;
import scala.runtime.BoxesRunTime;

public final class Stream$
extends SeqFactory<Stream>
implements Serializable {
    public static final Stream$ MODULE$;

    static {
        new Stream$();
    }

    public <A> CanBuildFrom<Stream<?>, A, Stream<A>> canBuildFrom() {
        return new Stream.StreamCanBuildFrom();
    }

    @Override
    public <A> Builder<A, Stream<A>> newBuilder() {
        return new Stream.StreamBuilder();
    }

    @Override
    public <A> Stream<A> empty() {
        return Stream$Empty$.MODULE$;
    }

    @Override
    public <A> Stream<A> apply(Seq<A> xs) {
        return xs.toStream();
    }

    public <A> Stream.ConsWrapper<A> consWrapper(Function0<Stream<A>> stream) {
        return new Stream.ConsWrapper<A>(stream);
    }

    public <A> Stream<A> iterate(A start, Function1<A, A> f) {
        Serializable serializable = new Serializable(start, f){
            public static final long serialVersionUID = 0L;
            private final Object start$2;
            private final Function1 f$6;

            public final Stream<A> apply() {
                return Stream$.MODULE$.iterate(this.f$6.apply(this.start$2), this.f$6);
            }
            {
                this.start$2 = start$2;
                this.f$6 = f$6;
            }
        };
        Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
        return new Stream.Cons<A>(start, serializable);
    }

    @Override
    public <A> Stream<A> iterate(A start, int len, Function1<A, A> f) {
        return this.iterate(start, f).take(len);
    }

    public Stream<Object> from(int start, int step) {
        Serializable serializable = new Serializable(start, step){
            public static final long serialVersionUID = 0L;
            private final int start$1;
            private final int step$1;

            public final Stream<Object> apply() {
                return Stream$.MODULE$.from(this.start$1 + this.step$1, this.step$1);
            }
            {
                this.start$1 = start$1;
                this.step$1 = step$1;
            }
        };
        Integer n = BoxesRunTime.boxToInteger(start);
        Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
        return new Stream.Cons<Object>(n, (Function0<Stream<Object>>)((Object)serializable));
    }

    public Stream<Object> from(int start) {
        return this.from(start, 1);
    }

    public <A> Stream<A> continually(Function0<A> elem) {
        Serializable serializable = new Serializable(elem){
            public static final long serialVersionUID = 0L;
            private final Function0 elem$3;

            public final Stream<A> apply() {
                return Stream$.MODULE$.continually(this.elem$3);
            }
            {
                this.elem$3 = elem$3;
            }
        };
        A a = elem.apply();
        Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
        return new Stream.Cons<A>(a, serializable);
    }

    @Override
    public <A> Stream<A> fill(int n, Function0<A> elem) {
        Stream stream;
        if (n <= 0) {
            stream = Stream$Empty$.MODULE$;
        } else {
            Serializable serializable = new Serializable(n, elem){
                public static final long serialVersionUID = 0L;
                private final int n$2;
                private final Function0 elem$2;

                public final Stream<A> apply() {
                    return Stream$.MODULE$.fill(this.n$2 - 1, this.elem$2);
                }
                {
                    this.n$2 = n$2;
                    this.elem$2 = elem$2;
                }
            };
            A a = elem.apply();
            Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
            stream = new Stream.Cons<A>(a, serializable);
        }
        return stream;
    }

    @Override
    public <A> Stream<A> tabulate(int n, Function1<Object, A> f) {
        return this.scala$collection$immutable$Stream$$loop$4(0, n, f);
    }

    @Override
    public <T> Stream<T> range(T start, T end, T step, Integral<T> evidence$1) {
        Stream stream;
        Predef$ predef$ = Predef$.MODULE$;
        Integral<T> num = evidence$1;
        if (num.mkOrderingOps(step).$less(num.zero()) ? num.mkOrderingOps(start).$less$eq(end) : num.mkOrderingOps(end).$less$eq(start)) {
            stream = Stream$Empty$.MODULE$;
        } else {
            Serializable serializable = new Serializable(start, end, step, evidence$1, num){
                public static final long serialVersionUID = 0L;
                private final Object start$3;
                private final Object end$1;
                private final Object step$2;
                private final Integral evidence$1$1;
                private final Integral num$1;

                public final Stream<T> apply() {
                    return Stream$.MODULE$.range((Object)this.num$1.mkNumericOps(this.start$3).$plus(this.step$2), this.end$1, this.step$2, this.evidence$1$1);
                }
                {
                    this.start$3 = start$3;
                    this.end$1 = end$1;
                    this.step$2 = step$2;
                    this.evidence$1$1 = evidence$1$1;
                    this.num$1 = num$1;
                }
            };
            Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
            stream = new Stream.Cons<T>(start, serializable);
        }
        return stream;
    }

    public <A> Stream.Cons<A> filteredTail(Stream<A> stream, Function1<A, Object> p) {
        Serializable serializable = new Serializable(stream, p){
            public static final long serialVersionUID = 0L;
            private final Stream stream$2;
            private final Function1 p$1;

            public final Stream<A> apply() {
                return ((Stream)this.stream$2.tail()).filter(this.p$1);
            }
            {
                this.stream$2 = stream$2;
                this.p$1 = p$1;
            }
        };
        A a = stream.head();
        Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
        return new Stream.Cons<A>(a, serializable);
    }

    public <A, B, That> Stream.Cons<B> collectedTail(B head2, Stream<A> stream, PartialFunction<A, B> pf, CanBuildFrom<Stream<A>, B, That> bf) {
        Serializable serializable = new Serializable(stream, pf, bf){
            public static final long serialVersionUID = 0L;
            private final Stream stream$1;
            private final PartialFunction pf$1;
            private final CanBuildFrom bf$1;

            public final Stream<B> apply() {
                return (Stream)((Stream)this.stream$1.tail()).collect(this.pf$1, this.bf$1);
            }
            {
                this.stream$1 = stream$1;
                this.pf$1 = pf$1;
                this.bf$1 = bf$1;
            }
        };
        Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
        return new Stream.Cons<B>(head2, serializable);
    }

    private Object readResolve() {
        return MODULE$;
    }

    public final Stream scala$collection$immutable$Stream$$loop$4(int i, int n$3, Function1 f$7) {
        Stream stream;
        if (i >= n$3) {
            stream = Stream$Empty$.MODULE$;
        } else {
            Serializable serializable = new Serializable(n$3, f$7, i){
                public static final long serialVersionUID = 0L;
                private final int n$3;
                private final Function1 f$7;
                private final int i$1;

                public final Stream<A> apply() {
                    return Stream$.MODULE$.scala$collection$immutable$Stream$$loop$4(this.i$1 + 1, this.n$3, this.f$7);
                }
                {
                    this.n$3 = n$3;
                    this.f$7 = f$7;
                    this.i$1 = i$1;
                }
            };
            Object r = f$7.apply(BoxesRunTime.boxToInteger(i));
            Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
            stream = new Stream.Cons(r, serializable);
        }
        return stream;
    }

    private Stream$() {
        MODULE$ = this;
    }
}

