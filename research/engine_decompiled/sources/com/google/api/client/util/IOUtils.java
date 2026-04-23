/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import com.google.api.client.util.ByteCountingOutputStream;
import com.google.api.client.util.ByteStreams;
import com.google.api.client.util.StreamingContent;
import com.google.api.client.util.Throwables;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class IOUtils {
    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        IOUtils.copy(inputStream, outputStream, true);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void copy(InputStream inputStream, OutputStream outputStream, boolean closeInputStream) throws IOException {
        try {
            ByteStreams.copy(inputStream, outputStream);
        }
        finally {
            if (closeInputStream) {
                inputStream.close();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static long computeLength(StreamingContent content) throws IOException {
        ByteCountingOutputStream countingStream = new ByteCountingOutputStream();
        try {
            content.writeTo(countingStream);
        }
        finally {
            countingStream.close();
        }
        return countingStream.count;
    }

    public static byte[] serialize(Object value) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtils.serialize(value, out);
        return out.toByteArray();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void serialize(Object value, OutputStream outputStream) throws IOException {
        try {
            new ObjectOutputStream(outputStream).writeObject(value);
        }
        finally {
            outputStream.close();
        }
    }

    public static <S extends Serializable> S deserialize(byte[] bytes2) throws IOException {
        if (bytes2 == null) {
            return null;
        }
        return IOUtils.deserialize(new ByteArrayInputStream(bytes2));
    }

    public static <S extends Serializable> S deserialize(InputStream inputStream) throws IOException {
        try {
            Serializable serializable = (Serializable)new ObjectInputStream(inputStream).readObject();
            return (S)serializable;
        }
        catch (ClassNotFoundException exception) {
            IOException ioe = new IOException("Failed to deserialize object");
            ioe.initCause(exception);
            throw ioe;
        }
        finally {
            inputStream.close();
        }
    }

    public static boolean isSymbolicLink(File file) throws IOException {
        try {
            Class<?> filesClass = Class.forName("java.nio.file.Files");
            Class<?> pathClass = Class.forName("java.nio.file.Path");
            Object path = File.class.getMethod("toPath", new Class[0]).invoke((Object)file, new Object[0]);
            return (Boolean)filesClass.getMethod("isSymbolicLink", pathClass).invoke(null, path);
        }
        catch (InvocationTargetException exception) {
            Throwable cause = exception.getCause();
            Throwables.propagateIfPossible(cause, IOException.class);
            throw new RuntimeException(cause);
        }
        catch (ClassNotFoundException exception) {
        }
        catch (IllegalArgumentException exception) {
        }
        catch (SecurityException exception) {
        }
        catch (IllegalAccessException exception) {
        }
        catch (NoSuchMethodException exception) {
            // empty catch block
        }
        if (File.separatorChar == '\\') {
            return false;
        }
        File canonical = file;
        if (file.getParent() != null) {
            canonical = new File(file.getParentFile().getCanonicalFile(), file.getName());
        }
        return !canonical.getCanonicalFile().equals(canonical.getAbsoluteFile());
    }
}

