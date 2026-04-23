/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground.player.factory;

import com.c1games.terminal.game.player.AlgoPlayerNull;
import com.c1games.terminal.game.player.Player;
import com.c1games.terminal.playground.player.ManualPlayer;
import com.c1games.terminal.playground.player.factory.PlayerFactory;
import com.c1games.terminal.playground.serializer.LazyDes;
import towersocket.Connection;
import towersocket.ConnectionBuilder;

public class ManualPlayerFactory
implements PlayerFactory {
    private final int playerNum;
    private final int onlineMode;
    private ManualPlayer player;

    public ManualPlayerFactory(int playerNum, int onlineMode) {
        this.playerNum = playerNum;
        this.onlineMode = onlineMode;
    }

    @Override
    public void begin(ConnectionBuilder builder) {
        if (this.onlineMode == 0 || this.playerNum == this.onlineMode) {
            builder.addReceiver("make_move_" + this.playerNum, message -> {
                if (this.player != null) {
                    this.player.enqueue((LazyDes<Object>)message);
                } else {
                    System.out.println("Warn: made move before com.c1games.terminal.playground.player exists");
                }
            });
        }
    }

    @Override
    public Player finish(Connection connection) {
        if (this.onlineMode == 0 || this.playerNum == this.onlineMode) {
            this.player = new ManualPlayer(this.playerNum == 1 || this.onlineMode == 2 ? connection.getSender("game_to_client") : null);
            return this.player;
        }
        return new AlgoPlayerNull();
    }

    @Override
    public boolean isAlgo() {
        return false;
    }

    @Override
    public int onlineMode() {
        return this.onlineMode;
    }
}

