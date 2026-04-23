/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import scala.Function1$mcVI$sp;
import scala.Function1$mcVI$sp$class;
import scala.util.MurmurHash;
import scala.util.MurmurHash$;

public class MurmurHash$mcI$sp
extends MurmurHash<Object>
implements Function1$mcVI$sp {
    private final int seed;

    @Override
    public void apply(int t) {
        this.apply$mcI$sp(t);
    }

    @Override
    public void apply$mcI$sp(int t) {
        this.scala$util$MurmurHash$$h_$eq(MurmurHash$.MODULE$.extendHash(this.scala$util$MurmurHash$$h(), t, this.scala$util$MurmurHash$$c(), this.scala$util$MurmurHash$$k()));
        this.scala$util$MurmurHash$$c_$eq(MurmurHash$.MODULE$.nextMagicA(this.scala$util$MurmurHash$$c()));
        this.scala$util$MurmurHash$$k_$eq(MurmurHash$.MODULE$.nextMagicB(this.scala$util$MurmurHash$$k()));
        this.scala$util$MurmurHash$$hashed_$eq(false);
    }

    public MurmurHash$mcI$sp(int seed) {
        this.seed = seed;
        super(seed);
        Function1$mcVI$sp$class.$init$(this);
    }
}

