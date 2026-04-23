/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.ClassTag$;
import scala.reflect.api.FlagSets;
import scala.reflect.internal.FlagSets;
import scala.reflect.internal.SymbolTable;

public abstract class FlagSets$class {
    public static FlagSets.FlagOps addFlagOps(SymbolTable $this, long left) {
        return new FlagSets.FlagOpsImpl($this, left);
    }

    public static void $init$(SymbolTable $this) {
        $this.scala$reflect$internal$FlagSets$_setter_$FlagSetTag_$eq(ClassTag$.MODULE$.apply(Long.TYPE));
        $this.scala$reflect$internal$FlagSets$_setter_$NoFlags_$eq(0L);
    }
}

