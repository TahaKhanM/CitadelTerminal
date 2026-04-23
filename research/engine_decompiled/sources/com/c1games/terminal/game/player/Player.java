/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.player;

import com.c1games.terminal.game.player.PlayerMove;
import com.c1games.terminal.game.player.PlayerType;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public interface Player {
    public void send(String var1);

    public void finishInitialization(Duration var1);

    public CompletableFuture<PlayerMove> makeMove(Duration var1);

    public PlayerType type();

    public void close();

    public void gameEnd();

    public boolean checkCrashed();

    public String name();
}

