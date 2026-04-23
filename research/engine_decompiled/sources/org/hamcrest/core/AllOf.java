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
public class AllOf<T>
extends BaseMatcher<T> {
    private final Iterable<Matcher<? extends T>> matchers;

    public AllOf(Iterable<Matcher<? extends T>> matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Object o) {
        for (Matcher<T> matcher : this.matchers) {
            if (matcher.matches(o)) continue;
            return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendList("(", " and ", ")", this.matchers);
    }

    @Factory
    public static <T> Matcher<T> allOf(Matcher<? extends T> ... matchers) {
        return AllOf.allOf(Arrays.asList(matchers));
    }

    @Factory
    public static <T> Matcher<T> allOf(Iterable<Matcher<? extends T>> matchers) {
        return new AllOf<T>(matchers);
    }
}

