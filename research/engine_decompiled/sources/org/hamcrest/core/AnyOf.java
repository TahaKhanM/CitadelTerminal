/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.core;

import java.util.Arrays;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AnyOf<T>
extends BaseMatcher<T> {
    private final Iterable<Matcher<? extends T>> matchers;

    public AnyOf(Iterable<Matcher<? extends T>> matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Object o) {
        for (Matcher<T> matcher : this.matchers) {
            if (!matcher.matches(o)) continue;
            return true;
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendList("(", " or ", ")", this.matchers);
    }

    @Factory
    public static <T> Matcher<T> anyOf(Matcher<? extends T> ... matchers) {
        return AnyOf.anyOf(Arrays.asList(matchers));
    }

    @Factory
    public static <T> Matcher<T> anyOf(Iterable<Matcher<? extends T>> matchers) {
        return new AnyOf<T>(matchers);
    }
}

