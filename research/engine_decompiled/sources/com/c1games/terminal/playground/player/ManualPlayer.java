/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground.player;

import com.c1games.terminal.game.player.Player;
import com.c1games.terminal.game.player.PlayerMove;
import com.c1games.terminal.game.player.PlayerType;
import com.c1games.terminal.playground.serializer.LazyDes;
import com.c1games.terminal.playground.serializer.LazySer;
import com.c1games.terminal.util.FutureQueue;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import towersocket.Sender;

public class ManualPlayer
implements Player {
    private Sender<LazySer<Object>> gameToClient;
    private FutureQueue<String> clientToGame;

    public ManualPlayer(Sender<LazySer<Object>> gameToClient) {
        this.gameToClient = gameToClient;
        this.clientToGame = new FutureQueue();
    }

    @Override
    public void finishInitialization(Duration timeout) {
    }

    public void enqueue(LazyDes<Object> message) {
        this.clientToGame.insert(message.data);
    }

    @Override
    public void send(String message) {
        if (this.gameToClient != null) {
            this.gameToClient.send(LazySer.serialized(message));
        }
    }

    @Override
    public CompletableFuture<PlayerMove> makeMove(Duration timeout) {
        long startedAt = System.currentTimeMillis();
        CompletableFuture<String> part1Fut = this.clientToGame.remove();
        CompletableFuture<String> part2Fut = this.clientToGame.remove();
        return ((CompletableFuture)part1Fut.thenCombine(part2Fut, (part1, part2) -> PlayerMove.success(part1, part2, System.currentTimeMillis() - startedAt))).orTimeout(timeout.toMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public PlayerType type() {
        return PlayerType.MANUAL;
    }

    @Override
    public boolean checkCrashed() {
        return false;
    }

    @Override
    public void close() {
    }

    @Override
    public void gameEnd() {
    }

    @Override
    public String name() {
        return "ByHand";
    }
}

