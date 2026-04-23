/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.concurrent.ExecutionContext;
import scala.concurrent.ExecutionContext$;

public final class ExecutionContextTaskSupport$ {
    public static final ExecutionContextTaskSupport$ MODULE$;

    static {
        new ExecutionContextTaskSupport$();
    }

    public ExecutionContext $lessinit$greater$default$1() {
        return ExecutionContext$.MODULE$.global();
    }

    private ExecutionContextTaskSupport$() {
        MODULE$ = this;
    }
}

