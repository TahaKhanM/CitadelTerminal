/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.collection.Seq;
import slogging.MultiLogger;
import slogging.UnderlyingLogger;

public final class MultiLogger$ {
    public static final MultiLogger$ MODULE$;

    static {
        new MultiLogger$();
    }

    public MultiLogger apply(Seq<UnderlyingLogger> loggers) {
        return new MultiLogger(loggers);
    }

    private MultiLogger$() {
        MODULE$ = this;
    }
}

