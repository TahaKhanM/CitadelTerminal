/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class BaseMatcher<T>
implements Matcher<T> {
    @Override
    public final void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
    }

    public String toString() {
        return StringDescription.toString(this);
    }
}

