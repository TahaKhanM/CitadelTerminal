/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.mutable.OpenHashMap;

public final class OpenHashMap$ {
    public static final OpenHashMap$ MODULE$;

    static {
        new OpenHashMap$();
    }

    public <K, V> OpenHashMap<K, V> apply(Seq<Tuple2<K, V>> elems) {
        return (OpenHashMap)new OpenHashMap().$plus$plus$eq(elems);
    }

    public <K, V> OpenHashMap<K, V> empty() {
        return new OpenHashMap();
    }

    public int nextPositivePowerOfTwo(int i) {
        return 1 << 32 - Integer.numberOfLeadingZeros(i - 1);
    }

    private OpenHashMap$() {
        MODULE$ = this;
    }
}

