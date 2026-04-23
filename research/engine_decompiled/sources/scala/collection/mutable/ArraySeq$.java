/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Serializable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.Builder;

public final class ArraySeq$
extends SeqFactory<ArraySeq>
implements Serializable {
    public static final ArraySeq$ MODULE$;

    static {
        new ArraySeq$();
    }

    public <A> CanBuildFrom<ArraySeq<?>, A, ArraySeq<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, ArraySeq<A>> newBuilder() {
        return new ArrayBuffer().mapResult(new Serializable(){
            public static final long serialVersionUID = 0L;

            /*
             * WARNING - void declaration
             */
            public final ArraySeq<A> apply(ArrayBuffer<A> buf) {
                void var2_2;
                ArraySeq<A> result2 = new ArraySeq<A>(buf.length());
                buf.copyToArray(result2.array(), 0);
                return var2_2;
            }
        });
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ArraySeq$() {
        MODULE$ = this;
    }
}

