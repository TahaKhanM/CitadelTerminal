/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.settings;

import scala.None$;
import scala.Option;
import scala.Some;
import scala.reflect.internal.settings.MutableSettings;

public abstract class MutableSettings$SettingValue$class {
    public static void postSetHook(MutableSettings.SettingValue $this) {
    }

    public static boolean isDefault(MutableSettings.SettingValue $this) {
        return !$this.setByUser();
    }

    public static boolean isSetByUser(MutableSettings.SettingValue $this) {
        return $this.setByUser();
    }

    public static Object value(MutableSettings.SettingValue $this) {
        return $this.v();
    }

    public static void value_$eq(MutableSettings.SettingValue $this, Object arg) {
        $this.setByUser_$eq(true);
        $this.v_$eq(arg);
        $this.postSetHook();
    }

    public static Option valueSetByUser(MutableSettings.SettingValue $this) {
        return $this.isSetByUser() ? new Some<Object>($this.value()) : None$.MODULE$;
    }

    public static void $init$(MutableSettings.SettingValue $this) {
        $this.setByUser_$eq(false);
    }
}

