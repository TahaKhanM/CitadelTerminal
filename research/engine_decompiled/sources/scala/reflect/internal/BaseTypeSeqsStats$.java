/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Predef$;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;

public final class BaseTypeSeqsStats$ {
    public static final BaseTypeSeqsStats$ MODULE$;
    private final Statistics.Counter baseTypeSeqCount;
    private final Statistics.Counter baseTypeSeqLenTotal;

    static {
        new BaseTypeSeqsStats$();
    }

    public Statistics.Counter baseTypeSeqCount() {
        return this.baseTypeSeqCount;
    }

    public Statistics.Counter baseTypeSeqLenTotal() {
        return this.baseTypeSeqLenTotal;
    }

    private BaseTypeSeqsStats$() {
        MODULE$ = this;
        this.baseTypeSeqCount = Statistics$.MODULE$.newCounter("#base type seqs", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
        this.baseTypeSeqLenTotal = Statistics$.MODULE$.newRelCounter("avg base type seq length", this.baseTypeSeqCount());
    }
}

