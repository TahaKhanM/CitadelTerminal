/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground.player.factory;

import com.c1games.terminal.Terminal;
import com.c1games.terminal.game.player.Player;
import com.c1games.terminal.playground.ServerConfig;
import com.c1games.terminal.playground.player.PlaygroundPlayer;
import com.c1games.terminal.playground.player.PlaygroundPlayerBean;
import com.c1games.terminal.playground.player.factory.PlayerFactory;
import com.c1games.terminal.playground.poller.Poller;
import com.c1games.terminal.playground.scheduler.ProcessScheduler;
import com.c1games.terminal.playground.serializer.LazyDes;
import com.c1games.terminal.playground.serializer.LazySer;
import java.util.Base64;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import towersocket.Connection;
import towersocket.ConnectionBuilder;
import towersocket.Getter;

public class PlaygroundPlayerFactory
implements PlayerFactory {
    private final ServerConfig serverConfig;
    private final ProcessScheduler scheduler;
    private final Poller poller;
    private final String name;
    private final int playerNum;
    private final boolean printPlayerErrors;
    private final boolean getAlgoFromConn;

    public PlaygroundPlayerFactory(ServerConfig serverConfig, ProcessScheduler scheduler, Poller poller, String name, int playerNum, boolean printPlayerErrors, boolean getAlgoFromConn) {
        this.serverConfig = serverConfig;
        this.scheduler = scheduler;
        this.poller = poller;
        this.name = name;
        this.playerNum = playerNum;
        this.printPlayerErrors = printPlayerErrors;
        this.getAlgoFromConn = getAlgoFromConn;
    }

    public byte[] downloadAlgoFromConnection(Connection connection, long timeOutMilli) throws InterruptedException, ExecutionException, TimeoutException {
        String algoGetter = "algo_zip";
        Getter getAlgoZip = connection.getGetter(algoGetter);
        String key = "algo_" + (this.playerNum - 1);
        System.out.println("Getting algo with key: " + key + " from: " + algoGetter);
        String algoZip64 = (String)((LazyDes)getAlgoZip.get(LazySer.unserialized(key)).get(timeOutMilli, TimeUnit.MILLISECONDS)).getObj();
        byte[] algoZip8 = Base64.getDecoder().decode(algoZip64);
        return algoZip8;
    }

    @Override
    public void begin(ConnectionBuilder builder) {
    }

    @Override
    public Player finish(Connection connection) {
        byte[] algoZip8 = new byte[]{};
        try {
            algoZip8 = this.getAlgoFromConn ? this.downloadAlgoFromConnection(connection, this.serverConfig.downloadTimeLimit.toMillis()) : Terminal.managerDownloader.downloadAlgo(this.name);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (TimeoutException e) {
            e.printStackTrace();
        }
        PlaygroundPlayerBean playerBean = new PlaygroundPlayerBean(this.playerNum, this.serverConfig, this.serverConfig.printIOErrors, this.printPlayerErrors, this.playerNum == 1 ? connection.getSender("game_to_client") : null, connection.getSender("player_" + this.playerNum + "_err"), this.scheduler, this.poller, this.serverConfig.enableSlotting);
        return new PlaygroundPlayer(playerBean, algoZip8);
    }

    @Override
    public boolean isAlgo() {
        return true;
    }

    @Override
    public int onlineMode() {
        return 0;
    }
}

