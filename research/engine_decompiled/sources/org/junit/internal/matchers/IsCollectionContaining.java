/*
 * Decompiled with CFR 0.152.
 */
package org.junit.internal.matchers;

import java.util.ArrayList;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.IsEqual;
import org.junit.internal.matchers.TypeSafeMatcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class IsCollectionContaining<T>
extends TypeSafeMatcher<Iterable<T>> {
    private final Matcher<? extends T> elementMatcher;

    public IsCollectionContaining(Matcher<? extends T> elementMatcher) {
        this.elementMatcher = elementMatcher;
    }

    @Override
    public boolean matchesSafely(Iterable<T> collection) {
        for (T item : collection) {
            if (!this.elementMatcher.matches(item)) continue;
            return true;
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a collection containing ").appendDescriptionOf(this.elementMatcher);
    }

    @Factory
    public static <T> Matcher<Iterable<T>> hasItem(Matcher<? extends T> elementMatcher) {
        return new IsCollectionContaining<T>(elementMatcher);
    }

    @Factory
    public static <T> Matcher<Iterable<T>> hasItem(T element) {
        return IsCollectionContaining.hasItem(IsEqual.equalTo(element));
    }

    @Factory
    public static <T> Matcher<Iterable<T>> hasItems(Matcher<? extends T> ... elementMatchers) {
        ArrayList all = new ArrayList(elementMatchers.length);
        for (Matcher<? extends T> elementMatcher : elementMatchers) {
            all.add(IsCollectionContaining.hasItem(elementMatcher));
        }
        return AllOf.allOf(all);
    }

    @Factory
    public static <T> Matcher<Iterable<T>> hasItems(T ... elements) {
        ArrayList all = new ArrayList(elements.length);
        for (T element : elements) {
            all.add(IsCollectionContaining.hasItem(element));
        }
        return AllOf.allOf(all);
    }
}

