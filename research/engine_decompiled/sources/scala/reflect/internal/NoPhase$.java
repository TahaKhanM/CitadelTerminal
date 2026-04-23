/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.internal.Phase;

public final class NoPhase$
extends Phase {
    public static final NoPhase$ MODULE$;

    static {
        new NoPhase$();
    }

    @Override
    public String name() {
        return "<no phase>";
    }

    @Override
    public boolean keepsTypeParams() {
        return false;
    }

    @Override
    public void run() {
        throw new Error("NoPhase.run");
    }

    private NoPhase$() {
        super(null);
        MODULE$ = this;
    }
}

