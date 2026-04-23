/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

public final class TypeConstants$ {
    public static final TypeConstants$ MODULE$;
    private final int DefaultLogThreshhold;
    private final int LogPendingBaseTypesThreshold;
    private final int LogVolatileThreshold;

    static {
        new TypeConstants$();
    }

    public final int DefaultLogThreshhold() {
        return 50;
    }

    public final int LogPendingBaseTypesThreshold() {
        return 50;
    }

    public final int LogVolatileThreshold() {
        return 50;
    }

    private TypeConstants$() {
        MODULE$ = this;
    }
}

