/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground.player.factory;

import com.c1games.terminal.playground.ServerConfig;
import com.c1games.terminal.playground.player.factory.ManualPlayerFactory;
import com.c1games.terminal.playground.player.factory.PlayerFactory;
import com.c1games.terminal.playground.player.factory.PlaygroundPlayerFactory;
import com.c1games.terminal.playground.poller.Poller;
import com.c1games.terminal.playground.scheduler.ProcessScheduler;

public class PlayerMetaFactory {
    private final ServerConfig serverConfig;
    private final ProcessScheduler scheduler;
    private final Poller poller;

    public PlayerMetaFactory(ServerConfig serverConfig, ProcessScheduler scheduler, Poller poller) {
        this.serverConfig = serverConfig;
        this.scheduler = scheduler;
        this.poller = poller;
    }

    public PlayerFactory make(String name, int playerNum, boolean printPlayerErrors) {
        if (name.equals("manual")) {
            return new ManualPlayerFactory(playerNum, 0);
        }
        if (name.equals("hostOnline")) {
            return new ManualPlayerFactory(playerNum, 1);
        }
        if (name.equals("joinOnline")) {
            return new ManualPlayerFactory(playerNum, 2);
        }
        return new PlaygroundPlayerFactory(this.serverConfig, this.scheduler, this.poller, name, playerNum, printPlayerErrors, name.equals("sendAlgo"));
    }
}

