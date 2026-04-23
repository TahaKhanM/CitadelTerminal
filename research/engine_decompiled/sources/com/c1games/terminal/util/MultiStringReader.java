/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.util;

import com.c1games.terminal.Terminal;
import com.c1games.terminal.game.player.Player;
import com.c1games.terminal.game.player.SimpleAlgoPlayer;
import com.c1games.terminal.util.TerminatedStringReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class MultiStringReader {
    private final Queue<String> buffer = new ArrayDeque<String>();
    public final TerminatedStringReader inner;

    public MultiStringReader(TerminatedStringReader inner2) {
        this.inner = inner2;
    }

    public boolean tryRead(String[] to2, long until2, Player player, Runnable unblock) throws IOException {
        while (!player.checkCrashed() && this.buffer.size() < to2.length && System.currentTimeMillis() < until2) {
            String next2 = this.inner.awaitTimeout(until2 - System.currentTimeMillis(), player, unblock);
            if (next2 != null) {
                this.buffer.add(next2);
                continue;
            }
            System.err.println("timed out" + player.name());
            Terminal.writeToSpecialWriterln("ALGO TIMED OUT!!! " + player.name());
            System.err.println("Last state: ");
            String lastState = ((SimpleAlgoPlayer)player).lastFrameSent;
            System.err.println(lastState);
            Terminal.writeToSpecialWriterln(lastState);
            break;
        }
        if (this.buffer.size() >= to2.length) {
            for (int i = 0; i < to2.length; ++i) {
                to2[i] = this.buffer.remove();
            }
            return true;
        }
        return false;
    }
}

