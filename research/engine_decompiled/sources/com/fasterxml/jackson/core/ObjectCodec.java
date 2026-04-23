/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.Iterator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class ObjectCodec {
    protected ObjectCodec() {
    }

    public abstract <T> T readValue(JsonParser var1, Class<T> var2) throws IOException, JsonProcessingException;

    public abstract <T> T readValue(JsonParser var1, TypeReference<?> var2) throws IOException, JsonProcessingException;

    public abstract <T> T readValue(JsonParser var1, ResolvedType var2) throws IOException, JsonProcessingException;

    public abstract <T extends TreeNode> T readTree(JsonParser var1) throws IOException, JsonProcessingException;

    public abstract <T> Iterator<T> readValues(JsonParser var1, Class<T> var2) throws IOException, JsonProcessingException;

    public abstract <T> Iterator<T> readValues(JsonParser var1, TypeReference<?> var2) throws IOException, JsonProcessingException;

    public abstract <T> Iterator<T> readValues(JsonParser var1, ResolvedType var2) throws IOException, JsonProcessingException;

    public abstract void writeValue(JsonGenerator var1, Object var2) throws IOException, JsonProcessingException;

    public abstract TreeNode createObjectNode();

    public abstract TreeNode createArrayNode();

    public abstract JsonParser treeAsTokens(TreeNode var1);

    public abstract <T> T treeToValue(TreeNode var1, Class<T> var2) throws JsonProcessingException;

    @Deprecated
    public abstract JsonFactory getJsonFactory();

    public JsonFactory getFactory() {
        return this.getJsonFactory();
    }
}

