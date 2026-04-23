/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Function0;
import scala.Predef$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.internal.Required;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.runtime.SymbolTable;
import scala.runtime.BoxesRunTime;

public abstract class SymbolTable$class {
    public static void info(SymbolTable $this, Function0 msg) {
        MutableSettings.SettingValue settingValue = ((Required)((Object)$this)).settings().verbose();
        MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
        if (BoxesRunTime.unboxToBoolean(settingValue.value())) {
            Predef$.MODULE$.println(new StringBuilder().append((Object)"[reflect-compiler] ").append(msg.apply()).toString());
        }
    }

    public static void debugInfo(SymbolTable $this, Function0 msg) {
        MutableSettings.SettingValue settingValue = ((Required)((Object)$this)).settings().debug();
        MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
        if (BoxesRunTime.unboxToBoolean(settingValue.value())) {
            $this.info(msg);
        }
    }

    public static boolean isCompilerUniverse(SymbolTable $this) {
        return false;
    }

    public static void $init$(SymbolTable $this) {
    }
}

