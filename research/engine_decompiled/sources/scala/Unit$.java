/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.AnyValCompanion;
import scala.runtime.BoxedUnit;

public final class Unit$
implements AnyValCompanion {
    public static final Unit$ MODULE$;

    static {
        new Unit$();
    }

    public BoxedUnit box(BoxedUnit x) {
        return BoxedUnit.UNIT;
    }

    public void unbox(Object x) {
    }

    public String toString() {
        return "object scala.Unit";
    }

    private Unit$() {
        MODULE$ = this;
    }
}

