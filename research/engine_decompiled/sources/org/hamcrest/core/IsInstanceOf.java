/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.core;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class IsInstanceOf
extends BaseMatcher<Object> {
    private final Class<?> theClass;

    public IsInstanceOf(Class<?> theClass) {
        this.theClass = theClass;
    }

    @Override
    public boolean matches(Object item) {
        return this.theClass.isInstance(item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("an instance of ").appendText(this.theClass.getName());
    }

    @Factory
    public static Matcher<Object> instanceOf(Class<?> type) {
        return new IsInstanceOf(type);
    }
}

