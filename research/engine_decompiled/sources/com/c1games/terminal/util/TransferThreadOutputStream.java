/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TransferThreadOutputStream
extends OutputStream {
    private static final int FLUSH = 1001;
    private static final int CLOSE = 1002;
    private OutputStream stream;
    private BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();

    public TransferThreadOutputStream(OutputStream stream, boolean printIOException) {
        this.stream = stream;
        Thread thread = new Thread(() -> {
            block6: {
                try {
                    int b;
                    do {
                        if ((b = this.queue.take().intValue()) == 1001) {
                            stream.flush();
                            continue;
                        }
                        if (b == 1002) {
                            stream.close();
                            continue;
                        }
                        stream.write(b);
                    } while (b != 1002);
                }
                catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                catch (IOException e) {
                    if (!printIOException) break block6;
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public void write(int b) throws IOException {
        this.queue.add(b);
    }

    @Override
    public void close() throws IOException {
        this.queue.add(1002);
    }

    @Override
    public void flush() throws IOException {
        this.queue.add(1001);
    }
}

