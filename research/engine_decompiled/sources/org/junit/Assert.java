/*
 * Decompiled with CFR 0.152.
 */
package org.junit;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.ComparisonFailure;
import org.junit.internal.ArrayComparisonFailure;
import org.junit.internal.ExactComparisonCriteria;
import org.junit.internal.InexactComparisonCriteria;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Assert {
    protected Assert() {
    }

    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            Assert.fail(message);
        }
    }

    public static void assertTrue(boolean condition) {
        Assert.assertTrue(null, condition);
    }

    public static void assertFalse(String message, boolean condition) {
        Assert.assertTrue(message, !condition);
    }

    public static void assertFalse(boolean condition) {
        Assert.assertFalse(null, condition);
    }

    public static void fail(String message) {
        if (message == null) {
            throw new AssertionError();
        }
        throw new AssertionError((Object)message);
    }

    public static void fail() {
        Assert.fail(null);
    }

    public static void assertEquals(String message, Object expected, Object actual) {
        if (expected == null && actual == null) {
            return;
        }
        if (expected != null && Assert.isEquals(expected, actual)) {
            return;
        }
        if (expected instanceof String && actual instanceof String) {
            String cleanMessage = message == null ? "" : message;
            throw new ComparisonFailure(cleanMessage, (String)expected, (String)actual);
        }
        Assert.failNotEquals(message, expected, actual);
    }

    private static boolean isEquals(Object expected, Object actual) {
        return expected.equals(actual);
    }

    public static void assertEquals(Object expected, Object actual) {
        Assert.assertEquals(null, expected, actual);
    }

    public static void assertArrayEquals(String message, Object[] expecteds, Object[] actuals) throws ArrayComparisonFailure {
        Assert.internalArrayEquals(message, expecteds, actuals);
    }

    public static void assertArrayEquals(Object[] expecteds, Object[] actuals) {
        Assert.assertArrayEquals(null, expecteds, actuals);
    }

    public static void assertArrayEquals(String message, byte[] expecteds, byte[] actuals) throws ArrayComparisonFailure {
        Assert.internalArrayEquals(message, expecteds, actuals);
    }

    public static void assertArrayEquals(byte[] expecteds, byte[] actuals) {
        Assert.assertArrayEquals(null, expecteds, actuals);
    }

    public static void assertArrayEquals(String message, char[] expecteds, char[] actuals) throws ArrayComparisonFailure {
        Assert.internalArrayEquals(message, expecteds, actuals);
    }

    public static void assertArrayEquals(char[] expecteds, char[] actuals) {
        Assert.assertArrayEquals(null, expecteds, actuals);
    }

    public static void assertArrayEquals(String message, short[] expecteds, short[] actuals) throws ArrayComparisonFailure {
        Assert.internalArrayEquals(message, expecteds, actuals);
    }

    public static void assertArrayEquals(short[] expecteds, short[] actuals) {
        Assert.assertArrayEquals(null, expecteds, actuals);
    }

    public static void assertArrayEquals(String message, int[] expecteds, int[] actuals) throws ArrayComparisonFailure {
        Assert.internalArrayEquals(message, expecteds, actuals);
    }

    public static void assertArrayEquals(int[] expecteds, int[] actuals) {
        Assert.assertArrayEquals(null, expecteds, actuals);
    }

    public static void assertArrayEquals(String message, long[] expecteds, long[] actuals) throws ArrayComparisonFailure {
        Assert.internalArrayEquals(message, expecteds, actuals);
    }

    public static void assertArrayEquals(long[] expecteds, long[] actuals) {
        Assert.assertArrayEquals(null, expecteds, actuals);
    }

    public static void assertArrayEquals(String message, double[] expecteds, double[] actuals, double delta) throws ArrayComparisonFailure {
        new InexactComparisonCriteria(delta).arrayEquals(message, expecteds, actuals);
    }

    public static void assertArrayEquals(double[] expecteds, double[] actuals, double delta) {
        Assert.assertArrayEquals(null, expecteds, actuals, delta);
    }

    public static void assertArrayEquals(String message, float[] expecteds, float[] actuals, float delta) throws ArrayComparisonFailure {
        new InexactComparisonCriteria(delta).arrayEquals(message, expecteds, actuals);
    }

    public static void assertArrayEquals(float[] expecteds, float[] actuals, float delta) {
        Assert.assertArrayEquals(null, expecteds, actuals, delta);
    }

    private static void internalArrayEquals(String message, Object expecteds, Object actuals) throws ArrayComparisonFailure {
        new ExactComparisonCriteria().arrayEquals(message, expecteds, actuals);
    }

    public static void assertEquals(String message, double expected, double actual, double delta) {
        if (Double.compare(expected, actual) == 0) {
            return;
        }
        if (!(Math.abs(expected - actual) <= delta)) {
            Assert.failNotEquals(message, new Double(expected), new Double(actual));
        }
    }

    public static void assertEquals(long expected, long actual) {
        Assert.assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, long expected, long actual) {
        Assert.assertEquals(message, (Object)expected, (Object)actual);
    }

    @Deprecated
    public static void assertEquals(double expected, double actual) {
        Assert.assertEquals(null, expected, actual);
    }

    @Deprecated
    public static void assertEquals(String message, double expected, double actual) {
        Assert.fail("Use assertEquals(expected, actual, delta) to compare floating-point numbers");
    }

    public static void assertEquals(double expected, double actual, double delta) {
        Assert.assertEquals(null, expected, actual, delta);
    }

    public static void assertNotNull(String message, Object object) {
        Assert.assertTrue(message, object != null);
    }

    public static void assertNotNull(Object object) {
        Assert.assertNotNull(null, object);
    }

    public static void assertNull(String message, Object object) {
        Assert.assertTrue(message, object == null);
    }

    public static void assertNull(Object object) {
        Assert.assertNull(null, object);
    }

    public static void assertSame(String message, Object expected, Object actual) {
        if (expected == actual) {
            return;
        }
        Assert.failNotSame(message, expected, actual);
    }

    public static void assertSame(Object expected, Object actual) {
        Assert.assertSame(null, expected, actual);
    }

    public static void assertNotSame(String message, Object unexpected, Object actual) {
        if (unexpected == actual) {
            Assert.failSame(message);
        }
    }

    public static void assertNotSame(Object unexpected, Object actual) {
        Assert.assertNotSame(null, unexpected, actual);
    }

    private static void failSame(String message) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }
        Assert.fail(formatted + "expected not same");
    }

    private static void failNotSame(String message, Object expected, Object actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }
        Assert.fail(formatted + "expected same:<" + expected + "> was not:<" + actual + ">");
    }

    private static void failNotEquals(String message, Object expected, Object actual) {
        Assert.fail(Assert.format(message, expected, actual));
    }

    static String format(String message, Object expected, Object actual) {
        String actualString;
        String expectedString;
        String formatted = "";
        if (message != null && !message.equals("")) {
            formatted = message + " ";
        }
        if ((expectedString = String.valueOf(expected)).equals(actualString = String.valueOf(actual))) {
            return formatted + "expected: " + Assert.formatClassAndValue(expected, expectedString) + " but was: " + Assert.formatClassAndValue(actual, actualString);
        }
        return formatted + "expected:<" + expectedString + "> but was:<" + actualString + ">";
    }

    private static String formatClassAndValue(Object value, String valueString) {
        String className = value == null ? "null" : value.getClass().getName();
        return className + "<" + valueString + ">";
    }

    @Deprecated
    public static void assertEquals(String message, Object[] expecteds, Object[] actuals) {
        Assert.assertArrayEquals(message, expecteds, actuals);
    }

    @Deprecated
    public static void assertEquals(Object[] expecteds, Object[] actuals) {
        Assert.assertArrayEquals(expecteds, actuals);
    }

    public static <T> void assertThat(T actual, Matcher<T> matcher) {
        Assert.assertThat("", actual, matcher);
    }

    public static <T> void assertThat(String reason, T actual, Matcher<T> matcher) {
        if (!matcher.matches(actual)) {
            StringDescription description = new StringDescription();
            description.appendText(reason);
            description.appendText("\nExpected: ");
            description.appendDescriptionOf(matcher);
            description.appendText("\n     got: ");
            description.appendValue(actual);
            description.appendText("\n");
            throw new AssertionError((Object)((Object)description).toString());
        }
    }
}

