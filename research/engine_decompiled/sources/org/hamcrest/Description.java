/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest;

import org.hamcrest.SelfDescribing;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Description {
    public Description appendText(String var1);

    public Description appendDescriptionOf(SelfDescribing var1);

    public Description appendValue(Object var1);

    public <T> Description appendValueList(String var1, String var2, String var3, T ... var4);

    public <T> Description appendValueList(String var1, String var2, String var3, Iterable<T> var4);

    public Description appendList(String var1, String var2, String var3, Iterable<? extends SelfDescribing> var4);
}

