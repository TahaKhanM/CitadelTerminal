/*
 * Decompiled with CFR 0.152.
 */
package org.junit;

import java.util.Arrays;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.matchers.Each;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Assume {
    public static void assumeTrue(boolean b) {
        Assume.assumeThat(b, CoreMatchers.is(true));
    }

    public static void assumeNotNull(Object ... objects) {
        Assume.assumeThat(Arrays.asList(objects), Each.each(CoreMatchers.notNullValue()));
    }

    public static <T> void assumeThat(T actual, Matcher<T> matcher) {
        if (!matcher.matches(actual)) {
            throw new AssumptionViolatedException(actual, matcher);
        }
    }

    public static void assumeNoException(Throwable t) {
        Assume.assumeThat(t, CoreMatchers.nullValue());
    }
}

