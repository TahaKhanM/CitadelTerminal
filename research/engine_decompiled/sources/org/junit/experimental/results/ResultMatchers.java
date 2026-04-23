/*
 * Decompiled with CFR 0.152.
 */
package org.junit.experimental.results;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.experimental.results.PrintableResult;
import org.junit.internal.matchers.TypeSafeMatcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ResultMatchers {
    public static Matcher<PrintableResult> isSuccessful() {
        return ResultMatchers.failureCountIs(0);
    }

    public static Matcher<PrintableResult> failureCountIs(final int count2) {
        return new TypeSafeMatcher<PrintableResult>(){

            @Override
            public void describeTo(Description description) {
                description.appendText("has " + count2 + " failures");
            }

            @Override
            public boolean matchesSafely(PrintableResult item) {
                return item.failureCount() == count2;
            }
        };
    }

    public static Matcher<Object> hasSingleFailureContaining(final String string2) {
        return new BaseMatcher<Object>(){

            @Override
            public boolean matches(Object item) {
                return item.toString().contains(string2) && ResultMatchers.failureCountIs(1).matches(item);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("has single failure containing " + string2);
            }
        };
    }

    public static Matcher<PrintableResult> hasFailureContaining(final String string2) {
        return new BaseMatcher<PrintableResult>(){

            @Override
            public boolean matches(Object item) {
                return item.toString().contains(string2);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("has failure containing " + string2);
            }
        };
    }
}

