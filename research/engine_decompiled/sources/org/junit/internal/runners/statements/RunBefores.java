/*
 * Decompiled with CFR 0.152.
 */
package org.junit.internal.runners.statements;

import java.util.List;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class RunBefores
extends Statement {
    private final Statement fNext;
    private final Object fTarget;
    private final List<FrameworkMethod> fBefores;

    public RunBefores(Statement next2, List<FrameworkMethod> befores, Object target) {
        this.fNext = next2;
        this.fBefores = befores;
        this.fTarget = target;
    }

    @Override
    public void evaluate() throws Throwable {
        for (FrameworkMethod before : this.fBefores) {
            before.invokeExplosively(this.fTarget, new Object[0]);
        }
        this.fNext.evaluate();
    }
}

