/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.reflect.internal.NoPhase$;
import scala.reflect.internal.Phase;
import scala.reflect.internal.SomePhase$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.runtime.ReflectSetup;

public abstract class ReflectSetup$class {
    public static void $init$(ReflectSetup $this) {
        $this.scala$reflect$runtime$ReflectSetup$_setter_$phaseWithId_$eq((Phase[])((Object[])new Phase[]{NoPhase$.MODULE$, SomePhase$.MODULE$}));
        $this.scala$reflect$runtime$ReflectSetup$_setter_$currentRunId_$eq(1);
        ((SymbolTable)((Object)$this)).phase_$eq(SomePhase$.MODULE$);
    }
}

