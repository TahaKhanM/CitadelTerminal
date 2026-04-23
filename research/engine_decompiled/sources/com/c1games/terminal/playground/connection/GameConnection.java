/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground.connection;

import com.c1games.terminal.Terminal;
import com.c1games.terminal.game.GameMain;
import com.c1games.terminal.game.TowerGame;
import com.c1games.terminal.game.player.Player;
import com.c1games.terminal.playground.ServerConfig;
import com.c1games.terminal.playground.player.factory.PlayerFactory;
import com.c1games.terminal.playground.serializer.LazySer;
import com.c1games.terminal.util.SessionBufferer;
import com.c1games.terminal.util.ThreadLocalOutput;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import towersocket.Connection;
import towersocket.ConnectionBuilder;
import towersocket.exception.TowerSocketException;
import towersocket.exception.UserException;

public class GameConnection
implements Consumer<ConnectionBuilder> {
    public static final String notifyChannel = "notification";
    private final ServerConfig serverConfig;
    private final PlayerFactory player1Factory;
    private final PlayerFactory player2Factory;
    private final TowerGame game;
    private final Map<String, Object> settings;
    private final Optional<String> startState;
    private final SessionBufferer sessionBufferer;
    private Connection connection;
    private String debugName = "null";
    private PrintStream log = System.out;
    private Player player1;
    private boolean player1Closed = false;
    private Player player2;
    private boolean player2Closed = false;
    private String onlineMatchName;
    private SessionBufferer.SessionBuffer sessionBuffer;

    public GameConnection(ServerConfig serverConfig, PlayerFactory player1Factory, PlayerFactory player2Factory, Map<String, Object> settings, Optional<String> startState, SessionBufferer sessionBufferer, String onlineMatchName) {
        this.serverConfig = serverConfig;
        this.player1Factory = player1Factory;
        this.player2Factory = player2Factory;
        this.settings = settings;
        this.startState = startState;
        this.sessionBufferer = sessionBufferer;
        this.onlineMatchName = onlineMatchName;
        this.game = new GameMain();
    }

    public void setDebugName(String name) {
        this.debugName = name;
        if (this.serverConfig.algoLocalOutput) {
            try {
                new File("logs").mkdir();
                FileOutputStream logOut = new FileOutputStream(new File("logs/log_" + name + ".txt"));
                this.log = new PrintStream(logOut);
                ThreadLocalOutput.setOut(this.log);
                ThreadLocalOutput.setErr(this.log);
            }
            catch (IOException e) {
                System.err.println("Error redirecting algo output");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void accept(ConnectionBuilder builder) {
        this.sessionBuffer = this.sessionBufferer.waitForTurn(this.player1Factory.isAlgo() && this.player2Factory.isAlgo());
        this.log.println("Building " + this.debugName);
        builder.addDisconnectHandler(this::onDisconnect);
        builder.addErrorHandler(this::onError);
        this.player1Factory.begin(builder);
        this.player2Factory.begin(builder);
        builder.finish(connection -> {
            this.connection = connection;
        }, connection -> new Thread(() -> {
            try {
                this.player1 = this.player1Factory.finish((Connection)connection);
                this.player2 = this.player2Factory.finish((Connection)connection);
                if (this.player1Factory.onlineMode() == 0) {
                    this.log.println("Game happening: " + String.valueOf(this.player1) + " v. " + String.valueOf(this.player2) + " name=" + this.debugName);
                    this.game.run(this.settings, this.player1, this.player2, this.startState);
                    this.log.println("Sending game over " + this.debugName);
                    connection.getSender("game_over").send(LazySer.unserialized(null));
                } else if (this.player1Factory.onlineMode() == 1) {
                    System.out.println("Hosting Online: " + this.onlineMatchName);
                    if (Terminal.onlineMatchMaker.addMatch(this.onlineMatchName, this)) {
                        connection.getSender(notifyChannel).send(LazySer.unserialized("startOnlineSuccess"));
                    } else {
                        connection.getSender(notifyChannel).send(LazySer.unserialized("startOnlineFail"));
                    }
                } else if (this.player1Factory.onlineMode() == 2) {
                    System.out.println("Joining Online: " + this.onlineMatchName);
                    if (Terminal.onlineMatchMaker.joinMatch(this.onlineMatchName, this, this.player2)) {
                        connection.getSender(notifyChannel).send(LazySer.unserialized("joinOnlineSuccess"));
                    } else {
                        connection.getSender(notifyChannel).send(LazySer.unserialized("joinOnlineFail"));
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                this.onError(new UserException(e));
            }
            finally {
                if (this.player1Factory.onlineMode() == 0) {
                    this.onEnd();
                }
            }
        }, "run game thread").start());
    }

    private void onDisconnect(String message) {
        try {
            this.log.println("Disconnection " + String.valueOf(this.connection));
            Terminal.onlineMatchMaker.notifyDisconnect(this.onlineMatchName);
        }
        finally {
            this.onEnd();
        }
    }

    private void onError(TowerSocketException exception) {
        try {
            Terminal.onlineMatchMaker.notifyDisconnect(this.onlineMatchName);
            if (this.connection != null) {
                this.connection.close();
            }
            this.log.println("Error, disconnecting " + String.valueOf(this.connection));
            try {
                exception.get();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        finally {
            this.onEnd();
        }
    }

    private synchronized void onEnd() {
        try {
            System.out.println("Ending game " + this.debugName);
            if (!this.player1Closed && this.player1 != null) {
                this.player1.close();
                this.player1Closed = true;
            }
            if (!this.player2Closed && this.player2 != null) {
                this.player2.close();
                this.player2Closed = true;
            }
            this.connection.close();
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
        finally {
            this.sessionBuffer.end();
        }
    }

    public synchronized void startOnlineMatch(Player onlinePlayer2) {
        new Thread(() -> {
            try {
                this.log.println("Game happening: " + String.valueOf(this.player1) + " v. " + String.valueOf(this.player2) + " name=" + this.debugName);
                this.player2 = onlinePlayer2;
                this.game.run(this.settings, this.player1, this.player2, this.startState);
            }
            catch (Exception e) {
                Terminal.onlineMatchMaker.endMatch(this.onlineMatchName, e);
            }
            finally {
                Terminal.onlineMatchMaker.endMatch(this.onlineMatchName, null);
            }
        }, "run online game thread").start();
    }

    public synchronized void endOnlineMatch(Exception e) {
        if (e != null) {
            this.log.println("Error for online match, disconnecting " + String.valueOf(this.connection));
            e.printStackTrace();
        } else {
            this.log.println("Sending game over " + this.debugName);
            this.connection.getSender("game_over").send(LazySer.unserialized(null));
        }
        this.onEnd();
    }

    public synchronized void notifyDisconnect() {
        if (this.connection != null) {
            this.connection.getSender(notifyChannel).send(LazySer.unserialized("otherDisconnect"));
        }
    }
}

