/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Function0;
import scala.reflect.runtime.SymbolTable;
import scala.reflect.runtime.ThreadLocalStorage;

public abstract class ThreadLocalStorage$class {
    public static final ThreadLocalStorage.ThreadLocalStorage mkThreadLocalStorage(SymbolTable $this, Function0 x) {
        return new ThreadLocalStorage.MyThreadLocalStorage($this, x);
    }

    public static void $init$(SymbolTable $this) {
    }
}

