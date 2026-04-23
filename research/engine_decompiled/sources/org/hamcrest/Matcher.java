/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest;

import org.hamcrest.SelfDescribing;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Matcher<T>
extends SelfDescribing {
    public boolean matches(Object var1);

    public void _dont_implement_Matcher___instead_extend_BaseMatcher_();
}

