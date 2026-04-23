/*
 * Decompiled with CFR 0.152.
 */
package org.junit.internal.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CombinableMatcher<T>
extends BaseMatcher<T> {
    private final Matcher<? extends T> fMatcher;

    public CombinableMatcher(Matcher<? extends T> matcher) {
        this.fMatcher = matcher;
    }

    @Override
    public boolean matches(Object item) {
        return this.fMatcher.matches(item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendDescriptionOf(this.fMatcher);
    }

    public CombinableMatcher<T> and(Matcher<? extends T> matcher) {
        return new CombinableMatcher(CoreMatchers.allOf(matcher, this.fMatcher));
    }

    public CombinableMatcher<T> or(Matcher<? extends T> matcher) {
        return new CombinableMatcher(CoreMatchers.anyOf(matcher, this.fMatcher));
    }
}

