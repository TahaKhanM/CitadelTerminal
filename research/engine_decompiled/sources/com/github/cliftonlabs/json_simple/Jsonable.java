/*
 * Decompiled with CFR 0.152.
 */
package com.github.cliftonlabs.json_simple;

import java.io.IOException;
import java.io.Writer;

public interface Jsonable {
    public String toJson();

    public void toJson(Writer var1) throws IOException;
}

