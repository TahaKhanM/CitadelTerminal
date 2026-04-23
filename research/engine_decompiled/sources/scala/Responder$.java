/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Function0;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.Responder;
import scala.Serializable;
import scala.Some;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;

public final class Responder$
implements Serializable {
    public static final Responder$ MODULE$;

    static {
        new Responder$();
    }

    public <A> Responder<A> constant(A x) {
        return new Responder<A>(x){
            private final Object x$1;

            public void respond(Function1<A, BoxedUnit> k) {
                k.apply(this.x$1);
            }
            {
                this.x$1 = x$1;
            }
        };
    }

    public <A> boolean exec(Function0<BoxedUnit> x) {
        x.apply$mcV$sp();
        return true;
    }

    public <A> Option<A> run(Responder<A> r) {
        ObjectRef<None$> result2 = ObjectRef.create(None$.MODULE$);
        r.foreach((Function1<A, BoxedUnit>)((Object)new Serializable(result2){
            public static final long serialVersionUID = 0L;
            private final ObjectRef result$1;

            public final void apply(A x) {
                this.result$1.elem = new Some<A>(x);
            }
            {
                this.result$1 = result$1;
            }
        }));
        return (Option)result2.elem;
    }

    public <A> Responder<Nothing$> loop(Responder<BoxedUnit> r) {
        return r.flatMap(new Serializable(r){
            public static final long serialVersionUID = 0L;
            private final Responder r$1;

            public final Responder<Nothing$> apply(BoxedUnit _) {
                return Responder$.MODULE$.loop(this.r$1).map(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final Nothing$ apply(Nothing$ y) {
                        return y;
                    }
                });
            }
            {
                this.r$1 = r$1;
            }
        });
    }

    public <A> Responder<BoxedUnit> loopWhile(Function0<Object> cond, Responder<BoxedUnit> r) {
        return cond.apply$mcZ$sp() ? r.flatMap(new Serializable(cond, r){
            public static final long serialVersionUID = 0L;
            private final Function0 cond$1;
            private final Responder r$2;

            public final Responder<BoxedUnit> apply(BoxedUnit _) {
                return Responder$.MODULE$.loopWhile(this.cond$1, this.r$2).map(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final void apply(BoxedUnit y) {
                    }
                });
            }
            {
                this.cond$1 = cond$1;
                this.r$2 = r$2;
            }
        }) : this.constant(BoxedUnit.UNIT);
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Responder$() {
        MODULE$ = this;
    }
}

