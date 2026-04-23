/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.core;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsNot;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class IsNull<T>
extends BaseMatcher<T> {
    @Override
    public boolean matches(Object o) {
        return o == null;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("null");
    }

    @Factory
    public static <T> Matcher<T> nullValue() {
        return new IsNull<T>();
    }

    @Factory
    public static <T> Matcher<T> notNullValue() {
        return IsNot.not(IsNull.<T>nullValue());
    }

    @Factory
    public static <T> Matcher<T> nullValue(Class<T> type) {
        return IsNull.nullValue();
    }

    @Factory
    public static <T> Matcher<T> notNullValue(Class<T> type) {
        return IsNull.notNullValue();
    }
}

