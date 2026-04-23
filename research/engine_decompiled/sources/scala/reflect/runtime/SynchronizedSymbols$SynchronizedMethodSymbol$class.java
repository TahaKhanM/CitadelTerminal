/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Serializable;
import scala.reflect.internal.Types;
import scala.reflect.runtime.SynchronizedSymbols;

public abstract class SynchronizedSymbols$SynchronizedMethodSymbol$class {
    public static Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedMethodSymbol$$typeAsMemberOfLock(SynchronizedSymbols.SynchronizedMethodSymbol $this) {
        return new Object();
    }

    public static Types.Type typeAsMemberOf(SynchronizedSymbols.SynchronizedMethodSymbol $this, Types.Type pre) {
        return $this.gilSynchronizedIfNotThreadsafe(new Serializable($this, pre){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedSymbols.SynchronizedMethodSymbol $outer;
            private final Types.Type pre$1;

            public final Types.Type apply() {
                Object object = this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedMethodSymbol$$typeAsMemberOfLock();
                synchronized (object) {
                    Types.Type type = this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedMethodSymbol$$super$typeAsMemberOf(this.pre$1);
                    return type;
                }
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.pre$1 = pre$1;
            }
        });
    }

    public static void $init$(SynchronizedSymbols.SynchronizedMethodSymbol $this) {
    }
}

