/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ThreadLocalOutput {
    private static ThreadLocal<OutputStream> localOut;
    private static ThreadLocal<OutputStream> localErr;

    public static void activate() {
        PrintStream oldOut = System.out;
        PrintStream oldErr = System.err;
        localOut = ThreadLocal.withInitial(() -> oldOut);
        localErr = ThreadLocal.withInitial(() -> oldErr);
        System.setOut(new PrintStream(new OutputStream(){

            @Override
            public void write(int b) throws IOException {
                localOut.get().write(b);
            }

            @Override
            public void write(byte[] b) throws IOException {
                localOut.get().write(b);
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                localOut.get().write(b, off, len);
            }
        }));
        System.setErr(new PrintStream(new OutputStream(){

            @Override
            public void write(int b) throws IOException {
                localErr.get().write(b);
            }

            @Override
            public void write(byte[] b) throws IOException {
                localErr.get().write(b);
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                localErr.get().write(b, off, len);
            }
        }));
    }

    public static void setOut(OutputStream out) {
        if (localOut != null) {
            localOut.set(out);
        }
    }

    public static void setErr(OutputStream err) {
        if (localErr != null) {
            localErr.set(err);
        }
    }
}

