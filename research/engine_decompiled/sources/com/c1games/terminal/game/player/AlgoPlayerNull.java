/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.player;

import com.c1games.terminal.game.player.Player;
import com.c1games.terminal.game.player.PlayerMove;
import com.c1games.terminal.game.player.PlayerType;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

public class AlgoPlayerNull
implements Player {
    PlayerType playerType = PlayerType.ALGO;

    @Override
    public boolean checkCrashed() {
        return true;
    }

    @Override
    public void send(String message) {
    }

    @Override
    public void finishInitialization(Duration timeout) {
    }

    @Override
    public CompletableFuture<PlayerMove> makeMove(Duration timeout) {
        return CompletableFuture.failedFuture(new NoSuchElementException());
    }

    @Override
    public PlayerType type() {
        return this.playerType;
    }

    @Override
    public void close() {
    }

    @Override
    public void gameEnd() {
    }

    @Override
    public String name() {
        return "FailedToLoad";
    }
}

