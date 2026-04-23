/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Serializable;
import scala.reflect.internal.Types;
import scala.reflect.runtime.SynchronizedSymbols;

public abstract class SynchronizedSymbols$SynchronizedTypeSymbol$class {
    public static Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock(SynchronizedSymbols.SynchronizedTypeSymbol $this) {
        return new Object();
    }

    public static Types.Type tpe_$times(SynchronizedSymbols.SynchronizedTypeSymbol $this) {
        return $this.gilSynchronizedIfNotThreadsafe(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedSymbols.SynchronizedTypeSymbol $outer;

            public final Types.Type apply() {
                Object object = this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock();
                synchronized (object) {
                    Types.Type type = this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$super$tpe_$times();
                    return type;
                }
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static void $init$(SynchronizedSymbols.SynchronizedTypeSymbol $this) {
    }
}

