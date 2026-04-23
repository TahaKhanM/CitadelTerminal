/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.transform;

import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.transform.RefChecks;

public abstract class RefChecks$class {
    public static Types.Type transformInfo(RefChecks $this, Symbols.Symbol sym, Types.Type tp) {
        return sym.isModule() && !sym.isStatic() ? new Types.NullaryMethodType($this.global(), tp) : tp;
    }

    public static void $init$(RefChecks $this) {
    }
}

