/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import scala.reflect.internal.JMethodOrConstructor;

public final class JMethodOrConstructor$ {
    public static final JMethodOrConstructor$ MODULE$;

    static {
        new JMethodOrConstructor$();
    }

    public JMethodOrConstructor liftMethodToJmoc(Method m) {
        return new JMethodOrConstructor(m);
    }

    public JMethodOrConstructor liftConstructorToJmoc(Constructor<?> m) {
        return new JMethodOrConstructor(m);
    }

    private JMethodOrConstructor$() {
        MODULE$ = this;
    }
}

