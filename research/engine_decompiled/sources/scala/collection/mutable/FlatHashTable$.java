/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Predef$;
import scala.collection.mutable.StringBuilder;
import scala.math.package$;
import scala.util.Random;

public final class FlatHashTable$ {
    public static final FlatHashTable$ MODULE$;

    static {
        new FlatHashTable$();
    }

    public final ThreadLocal<Random> seedGenerator() {
        return new ThreadLocal<Random>(){

            public Random initialValue() {
                return new Random();
            }
        };
    }

    public int defaultLoadFactor() {
        return 450;
    }

    public final int loadFactorDenum() {
        return 1000;
    }

    public int sizeForThreshold(int size2, int _loadFactor) {
        return package$.MODULE$.max(32, (int)((long)size2 * (long)this.loadFactorDenum() / (long)_loadFactor));
    }

    public int newThreshold(int _loadFactor, int size2) {
        boolean bl = _loadFactor < 1000 / 2;
        Predef$ predef$ = Predef$.MODULE$;
        if (bl) {
            return (int)((long)size2 * (long)_loadFactor / (long)1000);
        }
        throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)"loadFactor too large; must be < 0.5").toString());
    }

    private FlatHashTable$() {
        MODULE$ = this;
    }
}

