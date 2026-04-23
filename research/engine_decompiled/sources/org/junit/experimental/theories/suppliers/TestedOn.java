/*
 * Decompiled with CFR 0.152.
 */
package org.junit.experimental.theories.suppliers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.junit.experimental.theories.ParametersSuppliedBy;
import org.junit.experimental.theories.suppliers.TestedOnSupplier;

@ParametersSuppliedBy(value=TestedOnSupplier.class)
@Retention(value=RetentionPolicy.RUNTIME)
public @interface TestedOn {
    public int[] ints();
}

