/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Serializable;
import scala.collection.GenSet;
import scala.collection.parallel.ParSet;
import scala.collection.parallel.ParSetLike;

public abstract class ParSetLike$class {
    public static ParSet union(ParSetLike $this, GenSet that) {
        return (ParSet)$this.sequentially(new Serializable($this, that){
            public static final long serialVersionUID = 0L;
            private final GenSet that$1;

            public final Sequential apply(Sequential x$1) {
                return (Sequential)x$1.union(this.that$1);
            }
            {
                this.that$1 = that$1;
            }
        });
    }

    public static ParSet diff(ParSetLike $this, GenSet that) {
        return (ParSet)$this.sequentially(new Serializable($this, that){
            public static final long serialVersionUID = 0L;
            private final GenSet that$2;

            public final Sequential apply(Sequential x$2) {
                return (Sequential)x$2.diff(this.that$2);
            }
            {
                this.that$2 = that$2;
            }
        });
    }

    public static void $init$(ParSetLike $this) {
    }
}

