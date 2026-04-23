/*
 * Decompiled with CFR 0.152.
 */
package org.junit.internal.runners.statements;

import java.util.ArrayList;
import java.util.List;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class RunAfters
extends Statement {
    private final Statement fNext;
    private final Object fTarget;
    private final List<FrameworkMethod> fAfters;

    public RunAfters(Statement next2, List<FrameworkMethod> afters, Object target) {
        this.fNext = next2;
        this.fAfters = afters;
        this.fTarget = target;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void evaluate() throws Throwable {
        ArrayList<Throwable> errors = new ArrayList<Throwable>();
        try {
            this.fNext.evaluate();
        }
        catch (Throwable e) {
            errors.add(e);
        }
        finally {
            for (FrameworkMethod each : this.fAfters) {
                try {
                    each.invokeExplosively(this.fTarget, new Object[0]);
                }
                catch (Throwable e) {
                    errors.add(e);
                }
            }
        }
        MultipleFailureException.assertEmpty(errors);
    }
}

