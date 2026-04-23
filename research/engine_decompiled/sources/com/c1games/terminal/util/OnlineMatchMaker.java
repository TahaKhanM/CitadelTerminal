/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.util;

import com.c1games.terminal.game.player.Player;
import com.c1games.terminal.playground.connection.GameConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OnlineMatchMaker {
    private HashMap<String, List<GameConnection>> onlineMatches = new HashMap();

    public synchronized void endMatch(String matchName, Exception e) {
        for (GameConnection gameConnection : this.onlineMatches.get(matchName)) {
            gameConnection.endOnlineMatch(e);
        }
        System.out.println("Ending match " + matchName);
        this.onlineMatches.remove(matchName);
    }

    public synchronized void notifyDisconnect(String matchName) {
        if (this.onlineMatches.containsKey(matchName)) {
            for (GameConnection gameConnection : this.onlineMatches.get(matchName)) {
                gameConnection.notifyDisconnect();
            }
            this.endMatch(matchName, null);
        }
    }

    public synchronized boolean addMatch(String matchName, GameConnection hostPlayerConnection) {
        if (this.onlineMatches.containsKey(matchName)) {
            return false;
        }
        this.onlineMatches.put(matchName, new ArrayList());
        this.onlineMatches.get(matchName).add(hostPlayerConnection);
        return true;
    }

    public synchronized boolean joinMatch(String matchName, GameConnection hostPlayerConnection, Player onlinePlayer2) {
        if (this.onlineMatches.containsKey(matchName) && this.onlineMatches.get(matchName).size() == 1) {
            this.onlineMatches.get(matchName).add(hostPlayerConnection);
            this.onlineMatches.get(matchName).get(0).startOnlineMatch(onlinePlayer2);
            return true;
        }
        return false;
    }
}

