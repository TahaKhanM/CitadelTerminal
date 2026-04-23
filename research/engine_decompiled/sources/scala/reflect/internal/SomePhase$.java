/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.internal.NoPhase$;
import scala.reflect.internal.Phase;

public final class SomePhase$
extends Phase {
    public static final SomePhase$ MODULE$;

    static {
        new SomePhase$();
    }

    @Override
    public String name() {
        return "<some phase>";
    }

    @Override
    public void run() {
        throw new Error("SomePhase.run");
    }

    private SomePhase$() {
        super(NoPhase$.MODULE$);
        MODULE$ = this;
    }
}

