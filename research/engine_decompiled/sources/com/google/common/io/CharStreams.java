/*
 * Decompiled with CFR 0.152.
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.io.AppendableWriter;
import com.google.common.io.LineProcessor;
import com.google.common.io.LineReader;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

@Beta
@GwtIncompatible
public final class CharStreams {
    static CharBuffer createBuffer() {
        return CharBuffer.allocate(2048);
    }

    private CharStreams() {
    }

    @CanIgnoreReturnValue
    public static long copy(Readable from2, Appendable to2) throws IOException {
        Preconditions.checkNotNull(from2);
        Preconditions.checkNotNull(to2);
        CharBuffer buf = CharStreams.createBuffer();
        long total2 = 0L;
        while (from2.read(buf) != -1) {
            buf.flip();
            to2.append(buf);
            total2 += (long)buf.remaining();
            buf.clear();
        }
        return total2;
    }

    public static String toString(Readable r) throws IOException {
        return CharStreams.toStringBuilder(r).toString();
    }

    private static StringBuilder toStringBuilder(Readable r) throws IOException {
        StringBuilder sb = new StringBuilder();
        CharStreams.copy(r, sb);
        return sb;
    }

    public static List<String> readLines(Readable r) throws IOException {
        String line;
        ArrayList<String> result2 = new ArrayList<String>();
        LineReader lineReader = new LineReader(r);
        while ((line = lineReader.readLine()) != null) {
            result2.add(line);
        }
        return result2;
    }

    @CanIgnoreReturnValue
    public static <T> T readLines(Readable readable, LineProcessor<T> processor) throws IOException {
        String line;
        Preconditions.checkNotNull(readable);
        Preconditions.checkNotNull(processor);
        LineReader lineReader = new LineReader(readable);
        while ((line = lineReader.readLine()) != null && processor.processLine(line)) {
        }
        return processor.getResult();
    }

    @CanIgnoreReturnValue
    public static long exhaust(Readable readable) throws IOException {
        long read;
        long total2 = 0L;
        CharBuffer buf = CharStreams.createBuffer();
        while ((read = (long)readable.read(buf)) != -1L) {
            total2 += read;
            buf.clear();
        }
        return total2;
    }

    public static void skipFully(Reader reader, long n) throws IOException {
        Preconditions.checkNotNull(reader);
        while (n > 0L) {
            long amt = reader.skip(n);
            if (amt == 0L) {
                throw new EOFException();
            }
            n -= amt;
        }
    }

    public static Writer nullWriter() {
        return NullWriter.INSTANCE;
    }

    public static Writer asWriter(Appendable target) {
        if (target instanceof Writer) {
            return (Writer)target;
        }
        return new AppendableWriter(target);
    }

    private static final class NullWriter
    extends Writer {
        private static final NullWriter INSTANCE = new NullWriter();

        private NullWriter() {
        }

        @Override
        public void write(int c) {
        }

        @Override
        public void write(char[] cbuf) {
            Preconditions.checkNotNull(cbuf);
        }

        @Override
        public void write(char[] cbuf, int off, int len) {
            Preconditions.checkPositionIndexes(off, off + len, cbuf.length);
        }

        @Override
        public void write(String str) {
            Preconditions.checkNotNull(str);
        }

        @Override
        public void write(String str, int off, int len) {
            Preconditions.checkPositionIndexes(off, off + len, str.length());
        }

        @Override
        public Writer append(CharSequence csq) {
            Preconditions.checkNotNull(csq);
            return this;
        }

        @Override
        public Writer append(CharSequence csq, int start, int end) {
            Preconditions.checkPositionIndexes(start, end, csq.length());
            return this;
        }

        @Override
        public Writer append(char c) {
            return this;
        }

        @Override
        public void flush() {
        }

        @Override
        public void close() {
        }

        public String toString() {
            return "CharStreams.nullWriter()";
        }
    }
}

