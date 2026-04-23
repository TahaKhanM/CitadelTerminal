/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.settings;

import scala.reflect.internal.settings.MutableSettings;
import scala.runtime.BoxesRunTime;

public final class MutableSettings$ {
    public static final MutableSettings$ MODULE$;

    static {
        new MutableSettings$();
    }

    public boolean reflectSettingToBoolean(MutableSettings.SettingValue s2) {
        return BoxesRunTime.unboxToBoolean(s2.value());
    }

    private MutableSettings$() {
        MODULE$ = this;
    }
}

