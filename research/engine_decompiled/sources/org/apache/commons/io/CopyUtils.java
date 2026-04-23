/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.Charset;

@Deprecated
public class CopyUtils {
    private static final int DEFAULT_BUFFER_SIZE = 4096;

    public static void copy(byte[] input2, OutputStream output) throws IOException {
        output.write(input2);
    }

    @Deprecated
    public static void copy(byte[] input2, Writer output) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(input2);
        CopyUtils.copy((InputStream)in, output);
    }

    public static void copy(byte[] input2, Writer output, String encoding) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(input2);
        CopyUtils.copy(in, output, encoding);
    }

    public static int copy(InputStream input2, OutputStream output) throws IOException {
        byte[] buffer = new byte[4096];
        int count2 = 0;
        int n = 0;
        while (-1 != (n = input2.read(buffer))) {
            output.write(buffer, 0, n);
            count2 += n;
        }
        return count2;
    }

    public static int copy(Reader input2, Writer output) throws IOException {
        char[] buffer = new char[4096];
        int count2 = 0;
        int n = 0;
        while (-1 != (n = input2.read(buffer))) {
            output.write(buffer, 0, n);
            count2 += n;
        }
        return count2;
    }

    @Deprecated
    public static void copy(InputStream input2, Writer output) throws IOException {
        InputStreamReader in = new InputStreamReader(input2, Charset.defaultCharset());
        CopyUtils.copy((Reader)in, output);
    }

    public static void copy(InputStream input2, Writer output, String encoding) throws IOException {
        InputStreamReader in = new InputStreamReader(input2, encoding);
        CopyUtils.copy((Reader)in, output);
    }

    @Deprecated
    public static void copy(Reader input2, OutputStream output) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(output, Charset.defaultCharset());
        CopyUtils.copy(input2, (Writer)out);
        out.flush();
    }

    public static void copy(Reader input2, OutputStream output, String encoding) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(output, encoding);
        CopyUtils.copy(input2, (Writer)out);
        out.flush();
    }

    @Deprecated
    public static void copy(String input2, OutputStream output) throws IOException {
        StringReader in = new StringReader(input2);
        OutputStreamWriter out = new OutputStreamWriter(output, Charset.defaultCharset());
        CopyUtils.copy((Reader)in, (Writer)out);
        out.flush();
    }

    public static void copy(String input2, OutputStream output, String encoding) throws IOException {
        StringReader in = new StringReader(input2);
        OutputStreamWriter out = new OutputStreamWriter(output, encoding);
        CopyUtils.copy((Reader)in, (Writer)out);
        out.flush();
    }

    public static void copy(String input2, Writer output) throws IOException {
        output.write(input2);
    }
}

