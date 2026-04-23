/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Serializable;
import scala.collection.mutable.StringBuilder;
import scala.reflect.internal.MissingRequirementError$;
import scala.reflect.internal.Reporting;
import scala.reflect.internal.Required;
import scala.reflect.internal.StdAttachments;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$NoType$;
import scala.reflect.io.AbstractFile;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

public abstract class Symbols$StubSymbol$class {
    public static final Nothing$ failIfStub(Symbols.StubSymbol $this) {
        return MissingRequirementError$.MODULE$.signal($this.missingMessage());
    }

    private static Object fail(Symbols.StubSymbol $this, Object alt) {
        Object object;
        if (((Symbols.Symbol)((Object)$this)).hasFlag(0x100000000L)) {
            object = BoxedUnit.UNIT;
        } else {
            ((Reporting)((Object)$this.scala$reflect$internal$Symbols$StubSymbol$$$outer())).globalError(((StdAttachments.Attachable)((Object)$this)).pos(), $this.missingMessage());
            if (BoxesRunTime.unboxToBoolean(((Required)((Object)$this.scala$reflect$internal$Symbols$StubSymbol$$$outer())).settings().debug().value())) {
                new Throwable().printStackTrace();
            }
            object = ((Symbols.Symbol)((Object)$this)).setFlag(0x100000000L);
        }
        return alt;
    }

    public static Types$NoType$ originalInfo(Symbols.StubSymbol $this) {
        return ((Types)((Object)$this.scala$reflect$internal$Symbols$StubSymbol$$$outer())).NoType();
    }

    public static AbstractFile associatedFile(Symbols.StubSymbol $this) {
        return ((Symbols.Symbol)((Object)$this)).owner().associatedFile();
    }

    public static Types$NoType$ info(Symbols.StubSymbol $this) {
        return (Types$NoType$)Symbols$StubSymbol$class.fail($this, ((Types)((Object)$this.scala$reflect$internal$Symbols$StubSymbol$$$outer())).NoType());
    }

    public static Types$NoType$ rawInfo(Symbols.StubSymbol $this) {
        return (Types$NoType$)Symbols$StubSymbol$class.fail($this, ((Types)((Object)$this.scala$reflect$internal$Symbols$StubSymbol$$$outer())).NoType());
    }

    public static Symbols.NoSymbol companionSymbol(Symbols.StubSymbol $this) {
        return (Symbols.NoSymbol)Symbols$StubSymbol$class.fail($this, $this.scala$reflect$internal$Symbols$StubSymbol$$$outer().NoSymbol());
    }

    public static void $init$(Symbols.StubSymbol $this) {
        ((SymbolTable)$this.scala$reflect$internal$Symbols$StubSymbol$$$outer()).devWarning((Function0<String>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Symbols.StubSymbol $outer;

            public final String apply() {
                return new StringBuilder().append((Object)"creating stub symbol to defer error: ").append((Object)this.$outer.missingMessage()).toString();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
    }
}

