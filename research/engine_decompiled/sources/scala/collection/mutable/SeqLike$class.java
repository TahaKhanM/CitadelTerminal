/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function1;
import scala.Serializable;
import scala.collection.mutable.SeqLike;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParSeq$;
import scala.runtime.IntRef;

public abstract class SeqLike$class {
    public static Combiner parCombiner(SeqLike $this) {
        return ParSeq$.MODULE$.newCombiner();
    }

    public static SeqLike transform(SeqLike $this, Function1 f) {
        IntRef i = IntRef.create(0);
        $this.foreach(new Serializable($this, i, f){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SeqLike $outer;
            private final IntRef i$1;
            private final Function1 f$1;

            public final void apply(A el) {
                this.$outer.update(this.i$1.elem, this.f$1.apply(el));
                ++this.i$1.elem;
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.i$1 = i$1;
                this.f$1 = var3_3;
            }
        });
        return $this;
    }

    public static void $init$(SeqLike $this) {
    }
}

