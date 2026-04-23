/*
 * Decompiled with CFR 0.152.
 */
package org.junit.internal.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.IsCollectionContaining;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Each {
    public static <T> Matcher<Iterable<T>> each(final Matcher<T> individual) {
        final Matcher<Matcher<Iterable<Matcher<Matcher<T>>>>> allItemsAre = CoreMatchers.not(IsCollectionContaining.hasItem(CoreMatchers.not(individual)));
        return new BaseMatcher<Iterable<T>>(){

            @Override
            public boolean matches(Object item) {
                return allItemsAre.matches(item);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("each ");
                individual.describeTo(description);
            }
        };
    }
}

