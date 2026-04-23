/*
 * Decompiled with CFR 0.152.
 */
package org.junit.internal;

import org.junit.Assert;
import org.junit.internal.ComparisonCriteria;

public class InexactComparisonCriteria
extends ComparisonCriteria {
    public double fDelta;

    public InexactComparisonCriteria(double delta) {
        this.fDelta = delta;
    }

    protected void assertElementsEqual(Object expected, Object actual) {
        if (expected instanceof Double) {
            Assert.assertEquals((Double)expected, (double)((Double)actual), this.fDelta);
        } else {
            Assert.assertEquals(((Float)expected).floatValue(), (double)((Float)actual).floatValue(), this.fDelta);
        }
    }
}

