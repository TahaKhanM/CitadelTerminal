/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function$;
import scala.Function1;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.AbstractTraversable;
import scala.collection.Traversable;
import scala.runtime.ZippedTraversable2;

public final class ZippedTraversable2$ {
    public static final ZippedTraversable2$ MODULE$;

    static {
        new ZippedTraversable2$();
    }

    public <El1, El2> Traversable<Tuple2<El1, El2>> zippedTraversable2ToTraversable(ZippedTraversable2<El1, El2> zz) {
        return new AbstractTraversable<Tuple2<El1, El2>>(zz){
            private final ZippedTraversable2 zz$1;

            public <U> void foreach(Function1<Tuple2<El1, El2>, U> f) {
                Function$ function$ = Function$.MODULE$;
                this.zz$1.foreach(new Serializable(f){
                    public static final long serialVersionUID = 0L;
                    private final Function1 f$9;

                    public final b apply(a1 x1, a2 x2) {
                        return (b)this.f$9.apply(new Tuple2<a1, a2>(x1, x2));
                    }
                    {
                        this.f$9 = f$9;
                    }
                });
            }
            {
                this.zz$1 = zz$1;
            }
        };
    }

    private ZippedTraversable2$() {
        MODULE$ = this;
    }
}

