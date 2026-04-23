/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Predef$;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;

public final class SymbolTableStats$ {
    public static final SymbolTableStats$ MODULE$;
    private final Statistics.Counter phaseCounter;

    static {
        new SymbolTableStats$();
    }

    public Statistics.Counter phaseCounter() {
        return this.phaseCounter;
    }

    private SymbolTableStats$() {
        MODULE$ = this;
        this.phaseCounter = Statistics$.MODULE$.newCounter("#phase calls", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
    }
}

