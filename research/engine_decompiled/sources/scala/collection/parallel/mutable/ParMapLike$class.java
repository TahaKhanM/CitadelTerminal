/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.Tuple2;
import scala.collection.parallel.mutable.ParMap;
import scala.collection.parallel.mutable.ParMapLike;

public abstract class ParMapLike$class {
    public static ParMap $plus(ParMapLike $this, Tuple2 kv) {
        return (ParMap)((ParMap)$this.clone()).$plus$eq(kv);
    }

    public static ParMap $minus(ParMapLike $this, Object key) {
        return (ParMap)((ParMapLike)$this.clone()).$minus$eq(key);
    }

    public static void $init$(ParMapLike $this) {
    }
}

