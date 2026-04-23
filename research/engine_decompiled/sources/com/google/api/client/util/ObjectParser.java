/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface ObjectParser {
    public <T> T parseAndClose(InputStream var1, Charset var2, Class<T> var3) throws IOException;

    public Object parseAndClose(InputStream var1, Charset var2, Type var3) throws IOException;

    public <T> T parseAndClose(Reader var1, Class<T> var2) throws IOException;

    public Object parseAndClose(Reader var1, Type var2) throws IOException;
}

