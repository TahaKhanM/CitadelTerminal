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
import scala.collection.mutable.AnyRefMap;
import scala.collection.mutable.Iterable;
import scala.math.package$;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;

public final class AnyRefMap$ {
    public static final AnyRefMap$ MODULE$;
    private final int IndexMask;
    private final int MissingBit;
    private final int VacantBit;
    private final int MissVacant;
    private final Function1<Object, Nothing$> scala$collection$mutable$AnyRefMap$$exceptionDefault;

    static {
        new AnyRefMap$();
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

    public Function1<Object, Nothing$> scala$collection$mutable$AnyRefMap$$exceptionDefault() {
        return this.scala$collection$mutable$AnyRefMap$$exceptionDefault;
    }

    public <K, V, J, U> CanBuildFrom<AnyRefMap<K, V>, Tuple2<J, U>, AnyRefMap<J, U>> canBuildFrom() {
        return new CanBuildFrom<AnyRefMap<K, V>, Tuple2<J, U>, AnyRefMap<J, U>>(){

            public AnyRefMap.AnyRefMapBuilder<J, U> apply(AnyRefMap<K, V> from2) {
                return this.apply();
            }

            public AnyRefMap.AnyRefMapBuilder<J, U> apply() {
                return new AnyRefMap.AnyRefMapBuilder<K, V>();
            }
        };
    }

    /*
     * WARNING - void declaration
     */
    public <K, V> AnyRefMap<K, V> apply(Seq<Tuple2<K, V>> elems) {
        void var3_3;
        int sz = elems.hasDefiniteSize() ? elems.size() : 4;
        AnyRefMap arm = new AnyRefMap(sz * 2);
        elems.foreach(new Serializable(arm){
            public static final long serialVersionUID = 0L;
            private final AnyRefMap arm$1;

            public final void apply(Tuple2<K, V> x0$1) {
                if (x0$1 != null) {
                    this.arm$1.update(x0$1._1(), x0$1._2());
                    return;
                }
                throw new MatchError(x0$1);
            }
            {
                this.arm$1 = arm$1;
            }
        });
        if (arm.size() < sz >> 3) {
            arm.repack();
        }
        return var3_3;
    }

    public <K, V> AnyRefMap<K, V> empty() {
        return new AnyRefMap();
    }

    public <K, V> AnyRefMap<K, V> withDefault(Function1<K, V> function1) {
        return new AnyRefMap<K, V>(function1);
    }

    public <K, V> AnyRefMap<K, V> fromZip(K[] keys, Object values) {
        int sz = package$.MODULE$.min(keys.length, ScalaRunTime$.MODULE$.array_length(values));
        AnyRefMap<K, Object> arm = new AnyRefMap<K, Object>(sz * 2);
        for (int i = 0; i < sz; ++i) {
            arm.update(keys[i], ScalaRunTime$.MODULE$.array_apply(values, i));
        }
        if (arm.size() < sz >> 3) {
            arm.repack();
        }
        return arm;
    }

    public <K, V> AnyRefMap<K, V> fromZip(Iterable<K> keys, Iterable<V> values) {
        int sz = package$.MODULE$.min(keys.size(), values.size());
        AnyRefMap arm = new AnyRefMap(sz * 2);
        Iterator ki = keys.iterator();
        Iterator vi = values.iterator();
        while (ki.hasNext() && vi.hasNext()) {
            arm.update(ki.next(), vi.next());
        }
        if (arm.size() < sz >> 3) {
            arm.repack();
        }
        return arm;
    }

    private AnyRefMap$() {
        MODULE$ = this;
        this.scala$collection$mutable$AnyRefMap$$exceptionDefault = new Serializable(){
            public static final long serialVersionUID = 0L;

            public final Nothing$ apply(Object k) {
                throw new NoSuchElementException(k == null ? "(null)" : k.toString());
            }
        };
    }
}

