/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.transform;

import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.transform.PostErasure;

public abstract class PostErasure$class {
    public static Types.Type transformInfo(PostErasure $this, Symbols.Symbol sym, Types.Type tp) {
        return $this.elimErasedValueType().apply(tp);
    }

    public static void $init$(PostErasure $this) {
    }
}

