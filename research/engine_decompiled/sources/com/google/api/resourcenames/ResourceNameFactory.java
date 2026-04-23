/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.resourcenames;

import com.google.api.resourcenames.ResourceName;

public interface ResourceNameFactory<T extends ResourceName> {
    public T parse(String var1);
}

