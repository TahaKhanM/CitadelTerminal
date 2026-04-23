/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.MatchError;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.GenMap;
import scala.collection.GenMapLike;
import scala.runtime.BoxesRunTime;
import scala.util.hashing.MurmurHash3$;

public abstract class GenMapLike$class {
    public static int hashCode(GenMapLike $this) {
        return MurmurHash3$.MODULE$.mapHash($this.seq());
    }

    public static boolean equals(GenMapLike $this, Object that) {
        GenMap genMap;
        boolean bl = that instanceof GenMap ? $this == (genMap = (GenMap)that) || genMap.canEqual($this) && $this.size() == genMap.size() && GenMapLike$class.liftedTree1$1($this, genMap) : false;
        return bl;
    }

    private static final boolean liftedTree1$1(GenMapLike $this, GenMap x2$1) {
        boolean bl;
        try {
            bl = $this.forall(new Serializable($this, x2$1){
                public static final long serialVersionUID = 0L;
                private final GenMap x2$1;

                /*
                 * Enabled force condition propagation
                 * Lifted jumps to return sites
                 */
                public final boolean apply(Tuple2<A, B> x0$1) {
                    if (x0$1 == null) throw new MatchError(x0$1);
                    Option<B> option = this.x2$1.get(x0$1._1());
                    if (!(option instanceof Some)) return false;
                    Some some = (Some)option;
                    A a = some.x();
                    B b = x0$1._2();
                    if (b == a) {
                        return true;
                    }
                    if (b == null) {
                        return false;
                    }
                    boolean bl = b instanceof Number ? BoxesRunTime.equalsNumObject((Number)b, a) : (b instanceof Character ? BoxesRunTime.equalsCharObject((Character)b, a) : b.equals(a));
                    if (!bl) return false;
                    return true;
                }
                {
                    this.x2$1 = x2$1;
                }
            });
        }
        catch (ClassCastException classCastException) {
            bl = false;
        }
        return bl;
    }

    public static void $init$(GenMapLike $this) {
    }
}

