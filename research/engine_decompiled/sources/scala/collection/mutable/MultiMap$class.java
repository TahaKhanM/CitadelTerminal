/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.collection.IterableLike;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.MultiMap;
import scala.collection.mutable.Set;
import scala.collection.mutable.SetLike;

public abstract class MultiMap$class {
    public static Set makeSet(MultiMap $this) {
        return new HashSet();
    }

    public static MultiMap addBinding(MultiMap $this, Object key, Object value) {
        Option option;
        block4: {
            block3: {
                block2: {
                    option = $this.get(key);
                    if (!None$.MODULE$.equals(option)) break block2;
                    Set set = $this.makeSet();
                    set.$plus$eq(value);
                    $this.update(key, set);
                    break block3;
                }
                if (!(option instanceof Some)) break block4;
                Some some = (Some)option;
                ((SetLike)some.x()).$plus$eq(value);
            }
            return $this;
        }
        throw new MatchError(option);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static MultiMap removeBinding(MultiMap $this, Object key, Object value) {
        Option option = $this.get(key);
        if (None$.MODULE$.equals(option)) {
            return $this;
        } else {
            if (!(option instanceof Some)) throw new MatchError(option);
            Some some = (Some)option;
            ((SetLike)some.x()).$minus$eq(value);
            if (!((scala.collection.SetLike)some.x()).isEmpty()) return $this;
            $this.$minus$eq(key);
        }
        return $this;
    }

    public static boolean entryExists(MultiMap $this, Object key, Function1 p) {
        Option option;
        block4: {
            boolean bl;
            block3: {
                block2: {
                    option = $this.get(key);
                    if (!None$.MODULE$.equals(option)) break block2;
                    bl = false;
                    break block3;
                }
                if (!(option instanceof Some)) break block4;
                Some some = (Some)option;
                bl = ((IterableLike)some.x()).exists(p);
            }
            return bl;
        }
        throw new MatchError(option);
    }

    public static void $init$(MultiMap $this) {
    }
}

