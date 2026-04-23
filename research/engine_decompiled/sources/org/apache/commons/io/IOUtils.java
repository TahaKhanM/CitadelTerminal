/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.io.output.StringBuilderWriter;

public class IOUtils {
    public static final int EOF = -1;
    public static final char DIR_SEPARATOR_UNIX = '/';
    public static final char DIR_SEPARATOR_WINDOWS = '\\';
    public static final char DIR_SEPARATOR = File.separatorChar;
    public static final String LINE_SEPARATOR_UNIX = "\n";
    public static final String LINE_SEPARATOR_WINDOWS = "\r\n";
    public static final String LINE_SEPARATOR;
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int SKIP_BUFFER_SIZE = 2048;
    private static char[] SKIP_CHAR_BUFFER;
    private static byte[] SKIP_BYTE_BUFFER;

    public static void close(URLConnection conn) {
        if (conn instanceof HttpURLConnection) {
            ((HttpURLConnection)conn).disconnect();
        }
    }

    @Deprecated
    public static void closeQuietly(Reader input2) {
        IOUtils.closeQuietly((Closeable)input2);
    }

    @Deprecated
    public static void closeQuietly(Writer output) {
        IOUtils.closeQuietly((Closeable)output);
    }

    @Deprecated
    public static void closeQuietly(InputStream input2) {
        IOUtils.closeQuietly((Closeable)input2);
    }

    @Deprecated
    public static void closeQuietly(OutputStream output) {
        IOUtils.closeQuietly((Closeable)output);
    }

    @Deprecated
    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    @Deprecated
    public static void closeQuietly(Closeable ... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            IOUtils.closeQuietly(closeable);
        }
    }

    @Deprecated
    public static void closeQuietly(Socket sock) {
        if (sock != null) {
            try {
                sock.close();
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    @Deprecated
    public static void closeQuietly(Selector selector) {
        if (selector != null) {
            try {
                selector.close();
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    @Deprecated
    public static void closeQuietly(ServerSocket sock) {
        if (sock != null) {
            try {
                sock.close();
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    public static InputStream toBufferedInputStream(InputStream input2) throws IOException {
        return ByteArrayOutputStream.toBufferedInputStream(input2);
    }

    public static InputStream toBufferedInputStream(InputStream input2, int size2) throws IOException {
        return ByteArrayOutputStream.toBufferedInputStream(input2, size2);
    }

    public static BufferedReader toBufferedReader(Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader);
    }

    public static BufferedReader toBufferedReader(Reader reader, int size2) {
        return reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader, size2);
    }

    public static BufferedReader buffer(Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader);
    }

    public static BufferedReader buffer(Reader reader, int size2) {
        return reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader, size2);
    }

    public static BufferedWriter buffer(Writer writer) {
        return writer instanceof BufferedWriter ? (BufferedWriter)writer : new BufferedWriter(writer);
    }

    public static BufferedWriter buffer(Writer writer, int size2) {
        return writer instanceof BufferedWriter ? (BufferedWriter)writer : new BufferedWriter(writer, size2);
    }

    public static BufferedOutputStream buffer(OutputStream outputStream) {
        if (outputStream == null) {
            throw new NullPointerException();
        }
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream)outputStream : new BufferedOutputStream(outputStream);
    }

    public static BufferedOutputStream buffer(OutputStream outputStream, int size2) {
        if (outputStream == null) {
            throw new NullPointerException();
        }
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream)outputStream : new BufferedOutputStream(outputStream, size2);
    }

    public static BufferedInputStream buffer(InputStream inputStream) {
        if (inputStream == null) {
            throw new NullPointerException();
        }
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream)inputStream : new BufferedInputStream(inputStream);
    }

    public static BufferedInputStream buffer(InputStream inputStream, int size2) {
        if (inputStream == null) {
            throw new NullPointerException();
        }
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream)inputStream : new BufferedInputStream(inputStream, size2);
    }

    public static byte[] toByteArray(InputStream input2) throws IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream();){
            IOUtils.copy(input2, (OutputStream)output);
            byte[] byArray = output.toByteArray();
            return byArray;
        }
    }

    public static byte[] toByteArray(InputStream input2, long size2) throws IOException {
        if (size2 > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Size cannot be greater than Integer max value: " + size2);
        }
        return IOUtils.toByteArray(input2, (int)size2);
    }

    public static byte[] toByteArray(InputStream input2, int size2) throws IOException {
        int offset;
        int read;
        if (size2 < 0) {
            throw new IllegalArgumentException("Size must be equal or greater than zero: " + size2);
        }
        if (size2 == 0) {
            return new byte[0];
        }
        byte[] data = new byte[size2];
        for (offset = 0; offset < size2 && (read = input2.read(data, offset, size2 - offset)) != -1; offset += read) {
        }
        if (offset != size2) {
            throw new IOException("Unexpected read size. current: " + offset + ", expected: " + size2);
        }
        return data;
    }

    @Deprecated
    public static byte[] toByteArray(Reader input2) throws IOException {
        return IOUtils.toByteArray(input2, Charset.defaultCharset());
    }

    public static byte[] toByteArray(Reader input2, Charset encoding) throws IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream();){
            IOUtils.copy(input2, (OutputStream)output, encoding);
            byte[] byArray = output.toByteArray();
            return byArray;
        }
    }

    public static byte[] toByteArray(Reader input2, String encoding) throws IOException {
        return IOUtils.toByteArray(input2, Charsets.toCharset(encoding));
    }

    @Deprecated
    public static byte[] toByteArray(String input2) throws IOException {
        return input2.getBytes(Charset.defaultCharset());
    }

    public static byte[] toByteArray(URI uri) throws IOException {
        return IOUtils.toByteArray(uri.toURL());
    }

    public static byte[] toByteArray(URL url) throws IOException {
        URLConnection conn = url.openConnection();
        try {
            byte[] byArray = IOUtils.toByteArray(conn);
            return byArray;
        }
        finally {
            IOUtils.close(conn);
        }
    }

    public static byte[] toByteArray(URLConnection urlConn) throws IOException {
        try (InputStream inputStream = urlConn.getInputStream();){
            byte[] byArray = IOUtils.toByteArray(inputStream);
            return byArray;
        }
    }

    @Deprecated
    public static char[] toCharArray(InputStream is) throws IOException {
        return IOUtils.toCharArray(is, Charset.defaultCharset());
    }

    public static char[] toCharArray(InputStream is, Charset encoding) throws IOException {
        CharArrayWriter output = new CharArrayWriter();
        IOUtils.copy(is, (Writer)output, encoding);
        return output.toCharArray();
    }

    public static char[] toCharArray(InputStream is, String encoding) throws IOException {
        return IOUtils.toCharArray(is, Charsets.toCharset(encoding));
    }

    public static char[] toCharArray(Reader input2) throws IOException {
        CharArrayWriter sw = new CharArrayWriter();
        IOUtils.copy(input2, (Writer)sw);
        return sw.toCharArray();
    }

    @Deprecated
    public static String toString(InputStream input2) throws IOException {
        return IOUtils.toString(input2, Charset.defaultCharset());
    }

    public static String toString(InputStream input2, Charset encoding) throws IOException {
        try (StringBuilderWriter sw = new StringBuilderWriter();){
            IOUtils.copy(input2, (Writer)sw, encoding);
            String string2 = sw.toString();
            return string2;
        }
    }

    public static String toString(InputStream input2, String encoding) throws IOException {
        return IOUtils.toString(input2, Charsets.toCharset(encoding));
    }

    public static String toString(Reader input2) throws IOException {
        try (StringBuilderWriter sw = new StringBuilderWriter();){
            IOUtils.copy(input2, (Writer)sw);
            String string2 = sw.toString();
            return string2;
        }
    }

    @Deprecated
    public static String toString(URI uri) throws IOException {
        return IOUtils.toString(uri, Charset.defaultCharset());
    }

    public static String toString(URI uri, Charset encoding) throws IOException {
        return IOUtils.toString(uri.toURL(), Charsets.toCharset(encoding));
    }

    public static String toString(URI uri, String encoding) throws IOException {
        return IOUtils.toString(uri, Charsets.toCharset(encoding));
    }

    @Deprecated
    public static String toString(URL url) throws IOException {
        return IOUtils.toString(url, Charset.defaultCharset());
    }

    public static String toString(URL url, Charset encoding) throws IOException {
        try (InputStream inputStream = url.openStream();){
            String string2 = IOUtils.toString(inputStream, encoding);
            return string2;
        }
    }

    public static String toString(URL url, String encoding) throws IOException {
        return IOUtils.toString(url, Charsets.toCharset(encoding));
    }

    @Deprecated
    public static String toString(byte[] input2) throws IOException {
        return new String(input2, Charset.defaultCharset());
    }

    public static String toString(byte[] input2, String encoding) throws IOException {
        return new String(input2, Charsets.toCharset(encoding));
    }

    public static String resourceToString(String name, Charset encoding) throws IOException {
        return IOUtils.resourceToString(name, encoding, null);
    }

    public static String resourceToString(String name, Charset encoding, ClassLoader classLoader) throws IOException {
        return IOUtils.toString(IOUtils.resourceToURL(name, classLoader), encoding);
    }

    public static byte[] resourceToByteArray(String name) throws IOException {
        return IOUtils.resourceToByteArray(name, null);
    }

    public static byte[] resourceToByteArray(String name, ClassLoader classLoader) throws IOException {
        return IOUtils.toByteArray(IOUtils.resourceToURL(name, classLoader));
    }

    public static URL resourceToURL(String name) throws IOException {
        return IOUtils.resourceToURL(name, null);
    }

    public static URL resourceToURL(String name, ClassLoader classLoader) throws IOException {
        URL resource;
        URL uRL = resource = classLoader == null ? IOUtils.class.getResource(name) : classLoader.getResource(name);
        if (resource == null) {
            throw new IOException("Resource not found: " + name);
        }
        return resource;
    }

    @Deprecated
    public static List<String> readLines(InputStream input2) throws IOException {
        return IOUtils.readLines(input2, Charset.defaultCharset());
    }

    public static List<String> readLines(InputStream input2, Charset encoding) throws IOException {
        InputStreamReader reader = new InputStreamReader(input2, Charsets.toCharset(encoding));
        return IOUtils.readLines(reader);
    }

    public static List<String> readLines(InputStream input2, String encoding) throws IOException {
        return IOUtils.readLines(input2, Charsets.toCharset(encoding));
    }

    public static List<String> readLines(Reader input2) throws IOException {
        BufferedReader reader = IOUtils.toBufferedReader(input2);
        ArrayList<String> list2 = new ArrayList<String>();
        String line = reader.readLine();
        while (line != null) {
            list2.add(line);
            line = reader.readLine();
        }
        return list2;
    }

    public static LineIterator lineIterator(Reader reader) {
        return new LineIterator(reader);
    }

    public static LineIterator lineIterator(InputStream input2, Charset encoding) throws IOException {
        return new LineIterator(new InputStreamReader(input2, Charsets.toCharset(encoding)));
    }

    public static LineIterator lineIterator(InputStream input2, String encoding) throws IOException {
        return IOUtils.lineIterator(input2, Charsets.toCharset(encoding));
    }

    @Deprecated
    public static InputStream toInputStream(CharSequence input2) {
        return IOUtils.toInputStream(input2, Charset.defaultCharset());
    }

    public static InputStream toInputStream(CharSequence input2, Charset encoding) {
        return IOUtils.toInputStream(input2.toString(), encoding);
    }

    public static InputStream toInputStream(CharSequence input2, String encoding) throws IOException {
        return IOUtils.toInputStream(input2, Charsets.toCharset(encoding));
    }

    @Deprecated
    public static InputStream toInputStream(String input2) {
        return IOUtils.toInputStream(input2, Charset.defaultCharset());
    }

    public static InputStream toInputStream(String input2, Charset encoding) {
        return new ByteArrayInputStream(input2.getBytes(Charsets.toCharset(encoding)));
    }

    public static InputStream toInputStream(String input2, String encoding) throws IOException {
        byte[] bytes2 = input2.getBytes(Charsets.toCharset(encoding));
        return new ByteArrayInputStream(bytes2);
    }

    public static void write(byte[] data, OutputStream output) throws IOException {
        if (data != null) {
            output.write(data);
        }
    }

    public static void writeChunked(byte[] data, OutputStream output) throws IOException {
        if (data != null) {
            int bytes2 = data.length;
            int offset = 0;
            while (bytes2 > 0) {
                int chunk = Math.min(bytes2, 4096);
                output.write(data, offset, chunk);
                bytes2 -= chunk;
                offset += chunk;
            }
        }
    }

    @Deprecated
    public static void write(byte[] data, Writer output) throws IOException {
        IOUtils.write(data, output, Charset.defaultCharset());
    }

    public static void write(byte[] data, Writer output, Charset encoding) throws IOException {
        if (data != null) {
            output.write(new String(data, Charsets.toCharset(encoding)));
        }
    }

    public static void write(byte[] data, Writer output, String encoding) throws IOException {
        IOUtils.write(data, output, Charsets.toCharset(encoding));
    }

    public static void write(char[] data, Writer output) throws IOException {
        if (data != null) {
            output.write(data);
        }
    }

    public static void writeChunked(char[] data, Writer output) throws IOException {
        if (data != null) {
            int bytes2 = data.length;
            int offset = 0;
            while (bytes2 > 0) {
                int chunk = Math.min(bytes2, 4096);
                output.write(data, offset, chunk);
                bytes2 -= chunk;
                offset += chunk;
            }
        }
    }

    @Deprecated
    public static void write(char[] data, OutputStream output) throws IOException {
        IOUtils.write(data, output, Charset.defaultCharset());
    }

    public static void write(char[] data, OutputStream output, Charset encoding) throws IOException {
        if (data != null) {
            output.write(new String(data).getBytes(Charsets.toCharset(encoding)));
        }
    }

    public static void write(char[] data, OutputStream output, String encoding) throws IOException {
        IOUtils.write(data, output, Charsets.toCharset(encoding));
    }

    public static void write(CharSequence data, Writer output) throws IOException {
        if (data != null) {
            IOUtils.write(data.toString(), output);
        }
    }

    @Deprecated
    public static void write(CharSequence data, OutputStream output) throws IOException {
        IOUtils.write(data, output, Charset.defaultCharset());
    }

    public static void write(CharSequence data, OutputStream output, Charset encoding) throws IOException {
        if (data != null) {
            IOUtils.write(data.toString(), output, encoding);
        }
    }

    public static void write(CharSequence data, OutputStream output, String encoding) throws IOException {
        IOUtils.write(data, output, Charsets.toCharset(encoding));
    }

    public static void write(String data, Writer output) throws IOException {
        if (data != null) {
            output.write(data);
        }
    }

    @Deprecated
    public static void write(String data, OutputStream output) throws IOException {
        IOUtils.write(data, output, Charset.defaultCharset());
    }

    public static void write(String data, OutputStream output, Charset encoding) throws IOException {
        if (data != null) {
            output.write(data.getBytes(Charsets.toCharset(encoding)));
        }
    }

    public static void write(String data, OutputStream output, String encoding) throws IOException {
        IOUtils.write(data, output, Charsets.toCharset(encoding));
    }

    @Deprecated
    public static void write(StringBuffer data, Writer output) throws IOException {
        if (data != null) {
            output.write(data.toString());
        }
    }

    @Deprecated
    public static void write(StringBuffer data, OutputStream output) throws IOException {
        IOUtils.write(data, output, (String)null);
    }

    @Deprecated
    public static void write(StringBuffer data, OutputStream output, String encoding) throws IOException {
        if (data != null) {
            output.write(data.toString().getBytes(Charsets.toCharset(encoding)));
        }
    }

    @Deprecated
    public static void writeLines(Collection<?> lines2, String lineEnding, OutputStream output) throws IOException {
        IOUtils.writeLines(lines2, lineEnding, output, Charset.defaultCharset());
    }

    public static void writeLines(Collection<?> lines2, String lineEnding, OutputStream output, Charset encoding) throws IOException {
        if (lines2 == null) {
            return;
        }
        if (lineEnding == null) {
            lineEnding = LINE_SEPARATOR;
        }
        Charset cs = Charsets.toCharset(encoding);
        for (Object line : lines2) {
            if (line != null) {
                output.write(line.toString().getBytes(cs));
            }
            output.write(lineEnding.getBytes(cs));
        }
    }

    public static void writeLines(Collection<?> lines2, String lineEnding, OutputStream output, String encoding) throws IOException {
        IOUtils.writeLines(lines2, lineEnding, output, Charsets.toCharset(encoding));
    }

    public static void writeLines(Collection<?> lines2, String lineEnding, Writer writer) throws IOException {
        if (lines2 == null) {
            return;
        }
        if (lineEnding == null) {
            lineEnding = LINE_SEPARATOR;
        }
        for (Object line : lines2) {
            if (line != null) {
                writer.write(line.toString());
            }
            writer.write(lineEnding);
        }
    }

    public static int copy(InputStream input2, OutputStream output) throws IOException {
        long count2 = IOUtils.copyLarge(input2, output);
        if (count2 > Integer.MAX_VALUE) {
            return -1;
        }
        return (int)count2;
    }

    public static long copy(InputStream input2, OutputStream output, int bufferSize) throws IOException {
        return IOUtils.copyLarge(input2, output, new byte[bufferSize]);
    }

    public static long copyLarge(InputStream input2, OutputStream output) throws IOException {
        return IOUtils.copy(input2, output, 4096);
    }

    public static long copyLarge(InputStream input2, OutputStream output, byte[] buffer) throws IOException {
        int n;
        long count2 = 0L;
        while (-1 != (n = input2.read(buffer))) {
            output.write(buffer, 0, n);
            count2 += (long)n;
        }
        return count2;
    }

    public static long copyLarge(InputStream input2, OutputStream output, long inputOffset, long length) throws IOException {
        return IOUtils.copyLarge(input2, output, inputOffset, length, new byte[4096]);
    }

    public static long copyLarge(InputStream input2, OutputStream output, long inputOffset, long length, byte[] buffer) throws IOException {
        int read;
        int bufferLength;
        if (inputOffset > 0L) {
            IOUtils.skipFully(input2, inputOffset);
        }
        if (length == 0L) {
            return 0L;
        }
        int bytesToRead = bufferLength = buffer.length;
        if (length > 0L && length < (long)bufferLength) {
            bytesToRead = (int)length;
        }
        long totalRead = 0L;
        while (bytesToRead > 0 && -1 != (read = input2.read(buffer, 0, bytesToRead))) {
            output.write(buffer, 0, read);
            totalRead += (long)read;
            if (length <= 0L) continue;
            bytesToRead = (int)Math.min(length - totalRead, (long)bufferLength);
        }
        return totalRead;
    }

    @Deprecated
    public static void copy(InputStream input2, Writer output) throws IOException {
        IOUtils.copy(input2, output, Charset.defaultCharset());
    }

    public static void copy(InputStream input2, Writer output, Charset inputEncoding) throws IOException {
        InputStreamReader in = new InputStreamReader(input2, Charsets.toCharset(inputEncoding));
        IOUtils.copy((Reader)in, output);
    }

    public static void copy(InputStream input2, Writer output, String inputEncoding) throws IOException {
        IOUtils.copy(input2, output, Charsets.toCharset(inputEncoding));
    }

    public static int copy(Reader input2, Writer output) throws IOException {
        long count2 = IOUtils.copyLarge(input2, output);
        if (count2 > Integer.MAX_VALUE) {
            return -1;
        }
        return (int)count2;
    }

    public static long copyLarge(Reader input2, Writer output) throws IOException {
        return IOUtils.copyLarge(input2, output, new char[4096]);
    }

    public static long copyLarge(Reader input2, Writer output, char[] buffer) throws IOException {
        int n;
        long count2 = 0L;
        while (-1 != (n = input2.read(buffer))) {
            output.write(buffer, 0, n);
            count2 += (long)n;
        }
        return count2;
    }

    public static long copyLarge(Reader input2, Writer output, long inputOffset, long length) throws IOException {
        return IOUtils.copyLarge(input2, output, inputOffset, length, new char[4096]);
    }

    public static long copyLarge(Reader input2, Writer output, long inputOffset, long length, char[] buffer) throws IOException {
        int read;
        if (inputOffset > 0L) {
            IOUtils.skipFully(input2, inputOffset);
        }
        if (length == 0L) {
            return 0L;
        }
        int bytesToRead = buffer.length;
        if (length > 0L && length < (long)buffer.length) {
            bytesToRead = (int)length;
        }
        long totalRead = 0L;
        while (bytesToRead > 0 && -1 != (read = input2.read(buffer, 0, bytesToRead))) {
            output.write(buffer, 0, read);
            totalRead += (long)read;
            if (length <= 0L) continue;
            bytesToRead = (int)Math.min(length - totalRead, (long)buffer.length);
        }
        return totalRead;
    }

    @Deprecated
    public static void copy(Reader input2, OutputStream output) throws IOException {
        IOUtils.copy(input2, output, Charset.defaultCharset());
    }

    public static void copy(Reader input2, OutputStream output, Charset outputEncoding) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(output, Charsets.toCharset(outputEncoding));
        IOUtils.copy(input2, (Writer)out);
        out.flush();
    }

    public static void copy(Reader input2, OutputStream output, String outputEncoding) throws IOException {
        IOUtils.copy(input2, output, Charsets.toCharset(outputEncoding));
    }

    public static boolean contentEquals(InputStream input1, InputStream input2) throws IOException {
        int ch2;
        if (input1 == input2) {
            return true;
        }
        if (!(input1 instanceof BufferedInputStream)) {
            input1 = new BufferedInputStream(input1);
        }
        if (!(input2 instanceof BufferedInputStream)) {
            input2 = new BufferedInputStream(input2);
        }
        int ch = input1.read();
        while (-1 != ch) {
            ch2 = input2.read();
            if (ch != ch2) {
                return false;
            }
            ch = input1.read();
        }
        ch2 = input2.read();
        return ch2 == -1;
    }

    public static boolean contentEquals(Reader input1, Reader input2) throws IOException {
        int ch2;
        if (input1 == input2) {
            return true;
        }
        input1 = IOUtils.toBufferedReader(input1);
        input2 = IOUtils.toBufferedReader(input2);
        int ch = input1.read();
        while (-1 != ch) {
            ch2 = input2.read();
            if (ch != ch2) {
                return false;
            }
            ch = input1.read();
        }
        ch2 = input2.read();
        return ch2 == -1;
    }

    public static boolean contentEqualsIgnoreEOL(Reader input1, Reader input2) throws IOException {
        if (input1 == input2) {
            return true;
        }
        BufferedReader br1 = IOUtils.toBufferedReader(input1);
        BufferedReader br2 = IOUtils.toBufferedReader(input2);
        String line1 = br1.readLine();
        String line2 = br2.readLine();
        while (line1 != null && line2 != null && line1.equals(line2)) {
            line1 = br1.readLine();
            line2 = br2.readLine();
        }
        return line1 == null ? line2 == null : line1.equals(line2);
    }

    public static long skip(InputStream input2, long toSkip) throws IOException {
        long remain;
        long n;
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        if (SKIP_BYTE_BUFFER == null) {
            SKIP_BYTE_BUFFER = new byte[2048];
        }
        for (remain = toSkip; remain > 0L && (n = (long)input2.read(SKIP_BYTE_BUFFER, 0, (int)Math.min(remain, 2048L))) >= 0L; remain -= n) {
        }
        return toSkip - remain;
    }

    public static long skip(ReadableByteChannel input2, long toSkip) throws IOException {
        long remain;
        int n;
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        ByteBuffer skipByteBuffer = ByteBuffer.allocate((int)Math.min(toSkip, 2048L));
        for (remain = toSkip; remain > 0L; remain -= (long)n) {
            skipByteBuffer.position(0);
            skipByteBuffer.limit((int)Math.min(remain, 2048L));
            n = input2.read(skipByteBuffer);
            if (n == -1) break;
        }
        return toSkip - remain;
    }

    public static long skip(Reader input2, long toSkip) throws IOException {
        long remain;
        long n;
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        if (SKIP_CHAR_BUFFER == null) {
            SKIP_CHAR_BUFFER = new char[2048];
        }
        for (remain = toSkip; remain > 0L && (n = (long)input2.read(SKIP_CHAR_BUFFER, 0, (int)Math.min(remain, 2048L))) >= 0L; remain -= n) {
        }
        return toSkip - remain;
    }

    public static void skipFully(InputStream input2, long toSkip) throws IOException {
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip);
        }
        long skipped = IOUtils.skip(input2, toSkip);
        if (skipped != toSkip) {
            throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
        }
    }

    public static void skipFully(ReadableByteChannel input2, long toSkip) throws IOException {
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip);
        }
        long skipped = IOUtils.skip(input2, toSkip);
        if (skipped != toSkip) {
            throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
        }
    }

    public static void skipFully(Reader input2, long toSkip) throws IOException {
        long skipped = IOUtils.skip(input2, toSkip);
        if (skipped != toSkip) {
            throw new EOFException("Chars to skip: " + toSkip + " actual: " + skipped);
        }
    }

    public static int read(Reader input2, char[] buffer, int offset, int length) throws IOException {
        int location;
        int remaining;
        int count2;
        if (length < 0) {
            throw new IllegalArgumentException("Length must not be negative: " + length);
        }
        for (remaining = length; remaining > 0 && -1 != (count2 = input2.read(buffer, offset + (location = length - remaining), remaining)); remaining -= count2) {
        }
        return length - remaining;
    }

    public static int read(Reader input2, char[] buffer) throws IOException {
        return IOUtils.read(input2, buffer, 0, buffer.length);
    }

    public static int read(InputStream input2, byte[] buffer, int offset, int length) throws IOException {
        int location;
        int remaining;
        int count2;
        if (length < 0) {
            throw new IllegalArgumentException("Length must not be negative: " + length);
        }
        for (remaining = length; remaining > 0 && -1 != (count2 = input2.read(buffer, offset + (location = length - remaining), remaining)); remaining -= count2) {
        }
        return length - remaining;
    }

    public static int read(InputStream input2, byte[] buffer) throws IOException {
        return IOUtils.read(input2, buffer, 0, buffer.length);
    }

    public static int read(ReadableByteChannel input2, ByteBuffer buffer) throws IOException {
        int count2;
        int length = buffer.remaining();
        while (buffer.remaining() > 0 && -1 != (count2 = input2.read(buffer))) {
        }
        return length - buffer.remaining();
    }

    public static void readFully(Reader input2, char[] buffer, int offset, int length) throws IOException {
        int actual = IOUtils.read(input2, buffer, offset, length);
        if (actual != length) {
            throw new EOFException("Length to read: " + length + " actual: " + actual);
        }
    }

    public static void readFully(Reader input2, char[] buffer) throws IOException {
        IOUtils.readFully(input2, buffer, 0, buffer.length);
    }

    public static void readFully(InputStream input2, byte[] buffer, int offset, int length) throws IOException {
        int actual = IOUtils.read(input2, buffer, offset, length);
        if (actual != length) {
            throw new EOFException("Length to read: " + length + " actual: " + actual);
        }
    }

    public static void readFully(InputStream input2, byte[] buffer) throws IOException {
        IOUtils.readFully(input2, buffer, 0, buffer.length);
    }

    public static byte[] readFully(InputStream input2, int length) throws IOException {
        byte[] buffer = new byte[length];
        IOUtils.readFully(input2, buffer, 0, buffer.length);
        return buffer;
    }

    public static void readFully(ReadableByteChannel input2, ByteBuffer buffer) throws IOException {
        int expected = buffer.remaining();
        int actual = IOUtils.read(input2, buffer);
        if (actual != expected) {
            throw new EOFException("Length to read: " + expected + " actual: " + actual);
        }
    }

    static {
        try (StringBuilderWriter buf = new StringBuilderWriter(4);
             PrintWriter out = new PrintWriter(buf);){
            out.println();
            LINE_SEPARATOR = buf.toString();
        }
    }
}

