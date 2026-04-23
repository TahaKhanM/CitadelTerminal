/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import java.util.concurrent.locks.ReentrantLock;
import scala.Function0;
import scala.reflect.runtime.SymbolTable;

public abstract class Gil$class {
    public static ReentrantLock scala$reflect$runtime$Gil$$gil(SymbolTable $this) {
        return new ReentrantLock();
    }

    public static final Object gilSynchronized(SymbolTable $this, Function0 body2) {
        Object r;
        if ($this.isCompilerUniverse()) {
            r = body2.apply();
        } else {
            $this.scala$reflect$runtime$Gil$$gil().lock();
            r = body2.apply();
        }
        return r;
        finally {
            $this.scala$reflect$runtime$Gil$$gil().unlock();
        }
    }

    public static void $init$(SymbolTable $this) {
    }
}

