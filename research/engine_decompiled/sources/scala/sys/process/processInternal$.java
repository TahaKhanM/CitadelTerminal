/*
 * Decompiled with CFR 0.152.
 */
package scala.sys.process;

import java.io.IOException;
import scala.Console$;
import scala.Function0;
import scala.Function1;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.collection.Seq;
import scala.collection.mutable.StringBuilder;
import scala.sys.package$;

public final class processInternal$ {
    public static final processInternal$ MODULE$;
    private final boolean processDebug;

    static {
        new processInternal$();
    }

    public final boolean processDebug() {
        return this.processDebug;
    }

    public <T> PartialFunction<Throwable, T> onInterrupt(Function0<T> handler) {
        return new Serializable(handler){
            public static final long serialVersionUID = 0L;
            private final Function0 handler$1;

            public final <A1 extends Throwable, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                Object object = x1 instanceof InterruptedException ? this.handler$1.apply() : function1.apply(x1);
                return (B1)object;
            }

            public final boolean isDefinedAt(Throwable x1) {
                boolean bl = x1 instanceof InterruptedException;
                return bl;
            }
            {
                this.handler$1 = handler$1;
            }
        };
    }

    public <T> PartialFunction<Throwable, T> ioFailure(Function1<IOException, T> handler) {
        return new Serializable(handler){
            public static final long serialVersionUID = 0L;
            private final Function1 handler$2;

            public final <A1 extends Throwable, B1> B1 applyOrElse(A1 x2, Function1<A1, B1> function1) {
                Object object;
                if (x2 instanceof IOException) {
                    IOException iOException = (IOException)x2;
                    object = this.handler$2.apply(iOException);
                } else {
                    object = function1.apply(x2);
                }
                return object;
            }

            public final boolean isDefinedAt(Throwable x2) {
                boolean bl = x2 instanceof IOException;
                return bl;
            }
            {
                this.handler$2 = handler$2;
            }
        };
    }

    public void dbg(Seq<Object> msgs) {
        if (this.processDebug()) {
            Console$.MODULE$.println(new StringBuilder().append((Object)"[process] ").append((Object)msgs.mkString(" ")).toString());
        }
    }

    private processInternal$() {
        MODULE$ = this;
        this.processDebug = package$.MODULE$.props().contains("scala.process.debug");
        this.dbg(Predef$.MODULE$.genericWrapArray(new Object[]{"Initializing process package."}));
    }
}

