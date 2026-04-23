/*
 * Decompiled with CFR 0.152.
 */
package org.junit.internal.runners.statements;

import org.junit.internal.AssumptionViolatedException;
import org.junit.runners.model.Statement;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ExpectException
extends Statement {
    private Statement fNext;
    private final Class<? extends Throwable> fExpected;

    public ExpectException(Statement next2, Class<? extends Throwable> expected) {
        this.fNext = next2;
        this.fExpected = expected;
    }

    @Override
    public void evaluate() throws Exception {
        boolean complete2;
        block4: {
            complete2 = false;
            try {
                this.fNext.evaluate();
                complete2 = true;
            }
            catch (AssumptionViolatedException e) {
                throw e;
            }
            catch (Throwable e) {
                if (this.fExpected.isAssignableFrom(e.getClass())) break block4;
                String message = "Unexpected exception, expected<" + this.fExpected.getName() + "> but was<" + e.getClass().getName() + ">";
                throw new Exception(message, e);
            }
        }
        if (complete2) {
            throw new AssertionError((Object)("Expected exception: " + this.fExpected.getName()));
        }
    }
}

