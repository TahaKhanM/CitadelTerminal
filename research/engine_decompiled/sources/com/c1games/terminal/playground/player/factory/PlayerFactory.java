/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground.player.factory;

import com.c1games.terminal.game.player.Player;
import towersocket.Connection;
import towersocket.ConnectionBuilder;

public interface PlayerFactory {
    public void begin(ConnectionBuilder var1);

    public Player finish(Connection var1);

    public boolean isAlgo();

    public int onlineMode();
}

