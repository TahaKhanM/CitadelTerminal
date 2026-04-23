/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Predef$;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;

public final class SymbolsStats$ {
    public static final SymbolsStats$ MODULE$;
    private final Statistics.Counter typeSymbolCount;
    private final Statistics.Counter classSymbolCount;
    private final Statistics.Counter flagsCount;
    private final Statistics.Counter ownerCount;
    private final Statistics.Counter nameCount;

    static {
        new SymbolsStats$();
    }

    public Statistics.Counter typeSymbolCount() {
        return this.typeSymbolCount;
    }

    public Statistics.Counter classSymbolCount() {
        return this.classSymbolCount;
    }

    public Statistics.Counter flagsCount() {
        return this.flagsCount;
    }

    public Statistics.Counter ownerCount() {
        return this.ownerCount;
    }

    public Statistics.Counter nameCount() {
        return this.nameCount;
    }

    private SymbolsStats$() {
        MODULE$ = this;
        this.typeSymbolCount = Statistics$.MODULE$.newCounter("#type symbols", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
        this.classSymbolCount = Statistics$.MODULE$.newCounter("#class symbols", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
        this.flagsCount = Statistics$.MODULE$.newCounter("#flags ops", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
        this.ownerCount = Statistics$.MODULE$.newCounter("#owner ops", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
        this.nameCount = Statistics$.MODULE$.newCounter("#name ops", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
    }
}

