/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import java.util.NoSuchElementException;
import scala.Function1;
import scala.MatchError;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.mutable.Iterable;
import scala.collection.mutable.LongMap;
import scala.math.package$;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;

public final class LongMap$
implements Serializable {
    public static final LongMap$ MODULE$;
    private final int IndexMask;
    private final int MissingBit;
    private final int VacantBit;
    private final int MissVacant;
    private final Function1<Object, Nothing$> scala$collection$mutable$LongMap$$exceptionDefault;

    static {
        new LongMap$();
    }

    private final int IndexMask() {
        return 0x3FFFFFFF;
    }

    private final int MissingBit() {
        return Integer.MIN_VALUE;
    }

    private final int VacantBit() {
        return 0x40000000;
    }

    private final int MissVacant() {
        return -1073741824;
    }

    public Function1<Object, Nothing$> scala$collection$mutable$LongMap$$exceptionDefault() {
        return this.scala$collection$mutable$LongMap$$exceptionDefault;
    }

    public <V, U> CanBuildFrom<LongMap<V>, Tuple2<Object, U>, LongMap<U>> canBuildFrom() {
        return new CanBuildFrom<LongMap<V>, Tuple2<Object, U>, LongMap<U>>(){

            public LongMap.LongMapBuilder<U> apply(LongMap<V> from2) {
                return this.apply();
            }

            public LongMap.LongMapBuilder<U> apply() {
                return new LongMap.LongMapBuilder<V>();
            }
        };
    }

    /*
     * WARNING - void declaration
     */
    public <V> LongMap<V> apply(Seq<Tuple2<Object, V>> elems) {
        void var3_3;
        int sz = elems.hasDefiniteSize() ? elems.size() : 4;
        LongMap lm = new LongMap(sz * 2);
        elems.foreach(new Serializable(lm){
            public static final long serialVersionUID = 0L;
            private final LongMap lm$1;

            public final void apply(Tuple2<Object, V> x0$1) {
                if (x0$1 != null) {
                    this.lm$1.update(x0$1._1$mcJ$sp(), x0$1._2());
                    return;
                }
                throw new MatchError(x0$1);
            }
            {
                this.lm$1 = lm$1;
            }
        });
        if (lm.size() < sz >> 3) {
            lm.repack();
        }
        return var3_3;
    }

    public <V> LongMap<V> empty() {
        return new LongMap();
    }

    public <V> LongMap<V> withDefault(Function1<Object, V> function1) {
        return new LongMap<V>(function1);
    }

    public <V> LongMap<V> fromZip(long[] keys, Object values) {
        int sz = package$.MODULE$.min(keys.length, ScalaRunTime$.MODULE$.array_length(values));
        LongMap<Object> lm = new LongMap<Object>(sz * 2);
        for (int i = 0; i < sz; ++i) {
            lm.update(keys[i], ScalaRunTime$.MODULE$.array_apply(values, i));
        }
        if (lm.size() < sz >> 3) {
            lm.repack();
        }
        return lm;
    }

    public <V> LongMap<V> fromZip(Iterable<Object> keys, Iterable<V> values) {
        int sz = package$.MODULE$.min(keys.size(), values.size());
        LongMap lm = new LongMap(sz * 2);
        Iterator ki = keys.iterator();
        Iterator vi = values.iterator();
        while (ki.hasNext() && vi.hasNext()) {
            lm.update(BoxesRunTime.unboxToLong(ki.next()), vi.next());
        }
        if (lm.size() < sz >> 3) {
            lm.repack();
        }
        return lm;
    }

    private Object readResolve() {
        return MODULE$;
    }

    private LongMap$() {
        MODULE$ = this;
        this.scala$collection$mutable$LongMap$$exceptionDefault = new Serializable(){
            public static final long serialVersionUID = 0L;

            public final Nothing$ apply(long k) {
                throw new NoSuchElementException(((Object)BoxesRunTime.boxToLong(k)).toString());
            }
        };
    }
}

