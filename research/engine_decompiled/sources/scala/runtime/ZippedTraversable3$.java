/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function$;
import scala.Function1;
import scala.Serializable;
import scala.Tuple3;
import scala.collection.AbstractTraversable;
import scala.collection.Traversable;
import scala.runtime.ZippedTraversable3;

public final class ZippedTraversable3$ {
    public static final ZippedTraversable3$ MODULE$;

    static {
        new ZippedTraversable3$();
    }

    public <El1, El2, El3> Traversable<Tuple3<El1, El2, El3>> zippedTraversable3ToTraversable(ZippedTraversable3<El1, El2, El3> zz) {
        return new AbstractTraversable<Tuple3<El1, El2, El3>>(zz){
            private final ZippedTraversable3 zz$1;

            public <U> void foreach(Function1<Tuple3<El1, El2, El3>, U> f) {
                Function$ function$ = Function$.MODULE$;
                this.zz$1.foreach(new Serializable(f){
                    public static final long serialVersionUID = 0L;
                    private final Function1 f$10;

                    public final b apply(a1 x1, a2 x2, a3 x3) {
                        return (b)this.f$10.apply(new Tuple3<a1, a2, a3>(x1, x2, x3));
                    }
                    {
                        this.f$10 = f$10;
                    }
                });
            }
            {
                this.zz$1 = zz$1;
            }
        };
    }

    private ZippedTraversable3$() {
        MODULE$ = this;
    }
}

