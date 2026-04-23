/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.reflect.runtime.JavaUniverse;

public final class package$ {
    public static final package$ MODULE$;
    private scala.reflect.api.JavaUniverse universe;
    private volatile boolean bitmap$0;

    static {
        new package$();
    }

    private scala.reflect.api.JavaUniverse universe$lzycompute() {
        package$ package$2 = this;
        synchronized (package$2) {
            if (!this.bitmap$0) {
                this.universe = new JavaUniverse();
                this.bitmap$0 = true;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.universe;
        }
    }

    public scala.reflect.api.JavaUniverse universe() {
        return this.bitmap$0 ? this.universe : this.universe$lzycompute();
    }

    private package$() {
        MODULE$ = this;
    }
}

