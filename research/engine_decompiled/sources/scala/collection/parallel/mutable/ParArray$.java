/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.Array$;
import scala.Serializable;
import scala.collection.GenTraversableOnce;
import scala.collection.Seq;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParFactory;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ExposedArraySeq;
import scala.collection.parallel.mutable.ParArray;
import scala.collection.parallel.mutable.ResizableParArrayCombiner;
import scala.collection.parallel.mutable.package$;
import scala.reflect.ClassTag;
import scala.runtime.ScalaRunTime$;

public final class ParArray$
extends ParFactory<ParArray>
implements Serializable {
    public static final ParArray$ MODULE$;

    static {
        new ParArray$();
    }

    public <T> CanCombineFrom<ParArray<?>, T, ParArray<T>> canBuildFrom() {
        return new ParFactory.GenericCanCombineFrom(this);
    }

    @Override
    public <T> Combiner<T, ParArray<T>> newBuilder() {
        return this.newCombiner();
    }

    @Override
    public <T> Combiner<T, ParArray<T>> newCombiner() {
        return package$.MODULE$.ParArrayCombiner().apply();
    }

    public <T> ParArray<T> handoff(Object arr) {
        return this.wrapOrRebuild(arr, ScalaRunTime$.MODULE$.array_length(arr));
    }

    public <T> ParArray<T> handoff(Object arr, int sz) {
        return this.wrapOrRebuild(arr, sz);
    }

    private <T> ParArray<T> wrapOrRebuild(Object arr, int sz) {
        ParArray parArray;
        if (arr instanceof Object[]) {
            Object[] objectArray = (Object[])arr;
            parArray = new ParArray(new ExposedArraySeq(objectArray, sz));
        } else {
            parArray = new ParArray(new ExposedArraySeq(ScalaRunTime$.MODULE$.toObjectArray(arr), sz));
        }
        return parArray;
    }

    public <T> ParArray<T> createFromCopy(T[] arr, ClassTag<T> evidence$1) {
        Object[] newarr = (Object[])evidence$1.newArray(arr.length);
        Array$.MODULE$.copy(arr, 0, newarr, 0, arr.length);
        return this.handoff(newarr);
    }

    public <T> ParArray<T> fromTraversables(Seq<GenTraversableOnce<T>> xss) {
        ResizableParArrayCombiner cb = package$.MODULE$.ParArrayCombiner().apply();
        xss.foreach(new Serializable(cb){
            public static final long serialVersionUID = 0L;
            private final ResizableParArrayCombiner cb$1;

            public final ResizableParArrayCombiner<T> apply(GenTraversableOnce<T> xs) {
                return (ResizableParArrayCombiner)this.cb$1.$plus$plus$eq(xs.seq());
            }
            {
                this.cb$1 = cb$1;
            }
        });
        return (ParArray)cb.result();
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ParArray$() {
        MODULE$ = this;
    }
}

