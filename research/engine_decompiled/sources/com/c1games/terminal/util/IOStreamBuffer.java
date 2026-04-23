/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ArrayBlockingQueue;

public class IOStreamBuffer {
    public final OutputStream out;
    public final InputStream in;

    public IOStreamBuffer(int bufferSize) {
        final ArrayBlockingQueue buffer = new ArrayBlockingQueue(bufferSize);
        this.in = new InputStream(){

            @Override
            public int read() throws IOException {
                try {
                    return (Integer)buffer.take();
                }
                catch (InterruptedException e) {
                    throw new IOException(e);
                }
            }

            @Override
            public int available() throws IOException {
                return buffer.size();
            }
        };
        this.out = new OutputStream(){

            @Override
            public void write(int b) throws IOException {
                try {
                    buffer.put(b);
                }
                catch (InterruptedException e) {
                    throw new IOException(e);
                }
            }
        };
    }
}

