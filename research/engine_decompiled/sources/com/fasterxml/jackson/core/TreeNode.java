/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public interface TreeNode {
    public JsonToken asToken();

    public JsonParser.NumberType numberType();

    public JsonParser traverse();
}

