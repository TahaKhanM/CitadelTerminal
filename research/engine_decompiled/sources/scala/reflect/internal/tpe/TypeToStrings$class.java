/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.tpe;

import scala.Function0;
import scala.Serializable;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.HashSet$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Types;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.package$;
import scala.runtime.BoxesRunTime;

public abstract class TypeToStrings$class {
    public static int toStringRecursions(SymbolTable $this) {
        return $this.scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions();
    }

    public static void toStringRecursions_$eq(SymbolTable $this, int value) {
        $this.scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions_$eq(value);
    }

    public static HashSet toStringSubjects(SymbolTable $this) {
        return $this.scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects();
    }

    public static String typeToString(SymbolTable $this, Types.Type tpe) {
        String string2;
        if ($this.toStringRecursions() >= 50) {
            $this.devWarning((Function0<String>)((Object)new Serializable($this, tpe){
                public static final long serialVersionUID = 0L;
                private final Types.Type tpe$1;

                public final String apply() {
                    return new StringBuilder().append((Object)"Exceeded recursion depth attempting to print ").append((Object)package$.MODULE$.shortClassOfInstance(this.tpe$1)).toString();
                }
                {
                    this.tpe$1 = tpe$1;
                }
            }));
            MutableSettings.SettingValue settingValue = $this.settings().debug();
            MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
            if (BoxesRunTime.unboxToBoolean(settingValue.value())) {
                new Throwable().printStackTrace();
            }
            string2 = "...";
        } else {
            $this.toStringRecursions_$eq($this.toStringRecursions() + 1);
            string2 = tpe.safeToString();
        }
        return string2;
        finally {
            $this.toStringRecursions_$eq($this.toStringRecursions() - 1);
        }
    }

    public static void $init$(SymbolTable $this) {
        $this.scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions_$eq(0);
        $this.scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects_$eq((HashSet)HashSet$.MODULE$.apply(Nil$.MODULE$));
    }
}

