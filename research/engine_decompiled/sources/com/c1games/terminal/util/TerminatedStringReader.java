/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.util;

import com.c1games.terminal.game.player.Player;
import java.io.IOException;
import java.io.InputStream;

public class TerminatedStringReader
implements AutoCloseable {
    private final int terminator;
    private final InputStream in;
    private StringBuilder buffer;

    public TerminatedStringReader(InputStream in, char terminator) {
        this.terminator = terminator;
        this.in = in;
        this.buffer = new StringBuilder();
    }

    public String receive() throws IOException {
        boolean terminated = false;
        boolean exausted = false;
        while (!terminated && !exausted) {
            if (this.in.available() > 0) {
                int b = this.in.read();
                if (b == -1) {
                    throw new IOException("stream closed");
                }
                if (b == this.terminator) {
                    terminated = true;
                    continue;
                }
                this.buffer.append((char)b);
                continue;
            }
            exausted = true;
        }
        if (terminated) {
            String string2 = this.buffer.toString();
            this.buffer = new StringBuilder();
            return string2;
        }
        return null;
    }

    public String receive(long timeout) throws IOException {
        long end = System.currentTimeMillis() + timeout;
        boolean timedout = false;
        boolean exausted = false;
        StringBuilder buffer = new StringBuilder();
        while (!timedout && !exausted) {
            String string2 = this.receive();
            if (string2 == null) {
                exausted = true;
            } else {
                buffer.append(string2);
            }
            long now = System.currentTimeMillis();
            if (now < end) {
                try {
                    Thread.sleep(Math.min(end - now, 5L));
                    continue;
                }
                catch (InterruptedException e) {
                    throw new IOException(e);
                }
            }
            timedout = true;
        }
        if (exausted) {
            return buffer.length() == 0 ? null : buffer.toString();
        }
        return null;
    }

    public String awaitTimeout(long timeout, Player player, Runnable unblock) throws IOException {
        long start = System.currentTimeMillis();
        long end = start + timeout;
        boolean terminated = false;
        boolean timedout = false;
        while (!(player.checkCrashed() || terminated || timedout)) {
            unblock.run();
            long now = System.currentTimeMillis();
            if (this.in.available() > 0) {
                int b = this.in.read();
                if (b == this.terminator) {
                    terminated = true;
                    continue;
                }
                this.buffer.append((char)b);
                continue;
            }
            if (now < end) {
                try {
                    Thread.sleep(Math.min(end - now, 5L));
                    continue;
                }
                catch (InterruptedException e) {
                    throw new IOException(e);
                }
            }
            timedout = true;
        }
        if (terminated) {
            String string2 = this.buffer.toString();
            this.buffer = new StringBuilder();
            return string2;
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        this.in.close();
    }
}

