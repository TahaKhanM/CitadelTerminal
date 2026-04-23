/*
 * Decompiled with CFR 0.152.
 */
package org.junit.rules;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Assert;
import org.junit.internal.matchers.TypeSafeMatcher;
import org.junit.matchers.JUnitMatchers;
import org.junit.rules.TestRule;
import org.junit.runners.model.Statement;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ExpectedException
implements TestRule {
    private Matcher<Object> fMatcher = null;

    public static ExpectedException none() {
        return new ExpectedException();
    }

    private ExpectedException() {
    }

    @Override
    public Statement apply(Statement base, org.junit.runner.Description description) {
        return new ExpectedExceptionStatement(base);
    }

    public void expect(Matcher<?> matcher) {
        this.fMatcher = this.fMatcher == null ? matcher : JUnitMatchers.both(this.fMatcher).and(matcher);
    }

    public void expect(Class<? extends Throwable> type) {
        this.expect(CoreMatchers.instanceOf(type));
    }

    public void expectMessage(String substring) {
        this.expectMessage(JUnitMatchers.containsString(substring));
    }

    public void expectMessage(Matcher<String> matcher) {
        this.expect(this.hasMessage(matcher));
    }

    private Matcher<Throwable> hasMessage(final Matcher<String> matcher) {
        return new TypeSafeMatcher<Throwable>(){

            @Override
            public void describeTo(Description description) {
                description.appendText("exception with message ");
                description.appendDescriptionOf(matcher);
            }

            @Override
            public boolean matchesSafely(Throwable item) {
                return matcher.matches(item.getMessage());
            }
        };
    }

    private class ExpectedExceptionStatement
    extends Statement {
        private final Statement fNext;

        public ExpectedExceptionStatement(Statement base) {
            this.fNext = base;
        }

        public void evaluate() throws Throwable {
            try {
                this.fNext.evaluate();
            }
            catch (Throwable e) {
                if (ExpectedException.this.fMatcher == null) {
                    throw e;
                }
                Assert.assertThat(e, ExpectedException.this.fMatcher);
                return;
            }
            if (ExpectedException.this.fMatcher != null) {
                throw new AssertionError((Object)("Expected test to throw " + StringDescription.toString(ExpectedException.this.fMatcher)));
            }
        }
    }
}

