/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import scala.Function1$mcVF$sp;
import scala.Function1$mcVF$sp$class;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;
import scala.util.MurmurHash;
import scala.util.MurmurHash$;

public class MurmurHash$mcF$sp
extends MurmurHash<Object>
implements Function1$mcVF$sp {
    private final int seed;

    @Override
    public void apply(float t) {
        this.apply$mcF$sp(t);
    }

    @Override
    public void apply$mcF$sp(float t) {
        this.scala$util$MurmurHash$$h_$eq(MurmurHash$.MODULE$.extendHash(this.scala$util$MurmurHash$$h(), ScalaRunTime$.MODULE$.hash((Object)BoxesRunTime.boxToFloat(t)), this.scala$util$MurmurHash$$c(), this.scala$util$MurmurHash$$k()));
        this.scala$util$MurmurHash$$c_$eq(MurmurHash$.MODULE$.nextMagicA(this.scala$util$MurmurHash$$c()));
        this.scala$util$MurmurHash$$k_$eq(MurmurHash$.MODULE$.nextMagicB(this.scala$util$MurmurHash$$k()));
        this.scala$util$MurmurHash$$hashed_$eq(false);
    }

    public MurmurHash$mcF$sp(int seed) {
        this.seed = seed;
        super(seed);
        Function1$mcVF$sp$class.$init$(this);
    }
}

