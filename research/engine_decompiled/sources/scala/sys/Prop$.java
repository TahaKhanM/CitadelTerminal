/*
 * Decompiled with CFR 0.152.
 */
package scala.sys;

import scala.Predef$;
import scala.sys.Prop;

public final class Prop$ {
    public static final Prop$ MODULE$;

    static {
        new Prop$();
    }

    public <T> Prop<T> apply(String key, Prop.Creator<T> evidence$1) {
        Predef$ predef$ = Predef$.MODULE$;
        return evidence$1.apply(key);
    }

    private Prop$() {
        MODULE$ = this;
    }
}

