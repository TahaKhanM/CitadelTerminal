/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground.player;

import com.c1games.terminal.game.player.Player;
import com.c1games.terminal.playground.ServerConfig;
import com.c1games.terminal.playground.poller.Poller;
import com.c1games.terminal.playground.scheduler.ProcessScheduler;
import com.c1games.terminal.playground.serializer.LazySer;
import java.nio.file.Path;
import towersocket.Sender;

public class PlaygroundPlayerBean {
    public int playerNum;
    public ServerConfig serverConfig;
    public boolean printSystemErrors;
    public boolean printPlayerErrors;
    public Sender<LazySer<String>> stateToClient;
    public Sender<LazySer<String>> errorToClient;
    public ProcessScheduler scheduler;
    public Poller poller;
    public boolean slottingEnabled;
    public boolean crashed;
    public boolean closed;
    public Player player;
    public Path directory;
    public String name;
    public Path commandPath;
    public Process process;

    public PlaygroundPlayerBean(int playerNum, ServerConfig serverConfig, boolean printErrors, boolean printPlayerErrors, Sender<LazySer<String>> stateToClient, Sender<LazySer<String>> errorToClient, ProcessScheduler scheduler, Poller poller, boolean slottingEnabled) {
        this.playerNum = playerNum;
        this.serverConfig = serverConfig;
        this.printSystemErrors = printErrors;
        this.printPlayerErrors = printPlayerErrors;
        this.stateToClient = stateToClient;
        this.errorToClient = errorToClient;
        this.scheduler = scheduler;
        this.poller = poller;
        this.slottingEnabled = slottingEnabled;
        this.crashed = false;
        this.closed = false;
    }
}

