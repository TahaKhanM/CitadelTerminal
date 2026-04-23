/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.player;

import com.c1games.terminal.game.player.AlgoPlayerNull;
import com.c1games.terminal.game.player.Player;
import com.c1games.terminal.game.player.PlayerMove;
import com.c1games.terminal.game.player.PlayerType;
import com.c1games.terminal.game.player.SimpleAlgoPlayer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class PlayerManager {
    private Player[] players;
    private boolean printIOErrors;

    public PlayerManager(String[] botStartCommands, boolean printIOErrors) {
        this.players = new Player[botStartCommands.length];
        this.printIOErrors = printIOErrors;
        for (int i = 0; i < this.players.length; ++i) {
            this.players[i] = PlayerManager.setupPlayerFromCommandString(botStartCommands[i], printIOErrors, i);
        }
    }

    public PlayerManager(Player[] players, boolean printIOErrors) {
        this.players = players;
        this.printIOErrors = printIOErrors;
    }

    public boolean checkProcessCrashed(int playerIndex) {
        return this.players[playerIndex].checkCrashed();
    }

    public boolean checkIfAlgo(int playerID) {
        return this.players[playerID].type() == PlayerType.ALGO;
    }

    public void closeAllPlayers() {
        for (int i = 0; i < this.players.length; ++i) {
            this.players[i].close();
            if (!this.printIOErrors) continue;
            System.out.println("Closing playerIndex: " + i);
        }
    }

    public static String getNameFromCommand(String command) {
        String[] split2 = command.split("\\\\|/");
        return split2[split2.length - 2];
    }

    public static String getParentDirectory(String command) {
        String[] commSplit = command.split("\\s+");
        command = commSplit[commSplit.length - 1];
        String[] split2 = command.split("\\\\|/");
        String dirChar = command.contains("/") ? "/" : "\\";
        String parentDir = "";
        for (int i = 0; i < split2.length - 1; ++i) {
            parentDir = parentDir.concat(split2[i] + dirChar);
        }
        return parentDir;
    }

    public String getPlayerName(int playerIndex) {
        return this.players[playerIndex].name();
    }

    public static Player setupPlayerFromCommandString(String algoCommand, boolean printPlayerErrors, int playerIndex) {
        SimpleAlgoPlayer algoPlayer = new SimpleAlgoPlayer(algoCommand, true, printPlayerErrors, playerIndex);
        if (algoPlayer.checkCrashed()) {
            System.err.println("AlgoIndex " + playerIndex + " crashed bootup: " + algoCommand);
            return new AlgoPlayerNull();
        }
        return algoPlayer;
    }

    public void onGameEnd() {
        for (int i = 0; i < this.players.length; ++i) {
            this.players[i].gameEnd();
            if (!this.printIOErrors) continue;
            System.out.println("sent game end playerIndex: " + i);
        }
    }

    public void getPlayerErrorPrint() {
        for (int i = 0; i < this.players.length; ++i) {
            if (this.players[i].getClass() != SimpleAlgoPlayer.class) continue;
            ((SimpleAlgoPlayer)this.players[i]).receiveErrorNonBlocking();
        }
    }

    public void sendGameState(String state, int playerNumber) {
        this.players[playerNumber].send(state);
    }

    public void finishInitialization(Duration timeout) {
        for (Player player : this.players) {
            player.finishInitialization(timeout);
        }
    }

    /*
     * WARNING - void declaration
     */
    public List<PlayerMove> getPlayerInputsSimultaneous(long timeToWaitMillisecondsManual, long timeToWaitMillisecondsBot) {
        void var8_8;
        ArrayList<CompletionStage> movesFutures = new ArrayList<CompletionStage>(2);
        Player[] playerArray = this.players;
        int n = playerArray.length;
        boolean bl = false;
        while (var8_8 < n) {
            Player player = playerArray[var8_8];
            Duration timeout = player.type() == PlayerType.MANUAL ? Duration.ofMillis(timeToWaitMillisecondsManual) : Duration.ofMillis(timeToWaitMillisecondsBot);
            long startedAt = System.currentTimeMillis();
            CompletionStage future = player.makeMove(timeout).exceptionally(e -> PlayerMove.failure(System.currentTimeMillis() - startedAt));
            movesFutures.add(future);
            ++var8_8;
        }
        ArrayList<PlayerMove> moves = new ArrayList<PlayerMove>(2);
        for (CompletableFuture completableFuture : movesFutures) {
            try {
                moves.add((PlayerMove)completableFuture.get());
            }
            catch (Exception e2) {
                throw new IllegalStateException(e2);
            }
        }
        return moves;
    }
}

