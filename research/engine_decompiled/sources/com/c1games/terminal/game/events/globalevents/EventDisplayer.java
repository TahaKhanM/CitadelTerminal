/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.events.globalevents;

import com.c1games.terminal.game.GameMain;
import com.c1games.terminal.game.events.globalevents.GlobalDisplayEvent;
import com.c1games.terminal.game.events.globalevents.GlobalMelee;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class EventDisplayer {
    Map<String, Object> p1Map;
    Map<String, Object> p2Map;
    Map<String, GlobalMelee> meleeList;
    GameMain gameMain;

    public EventDisplayer(GameMain gameMain) {
        this.gameMain = gameMain;
        this.p1Map = new HashMap<String, Object>();
        this.p2Map = new HashMap<String, Object>();
        this.meleeList = new HashMap<String, GlobalMelee>();
        this.clearEvents();
    }

    public void clearEvents() {
        this.p1Map.clear();
        this.p2Map.clear();
        this.meleeList.clear();
        this.p1Map.put("attack", new ArrayList());
        this.p1Map.put("breach", new ArrayList());
        this.p1Map.put("damage", new ArrayList());
        this.p1Map.put("death", new ArrayList());
        this.p1Map.put("melee", new ArrayList());
        this.p1Map.put("move", new ArrayList());
        this.p1Map.put("selfDestruct", new ArrayList());
        this.p1Map.put("shield", new ArrayList());
        this.p1Map.put("spawn", new ArrayList());
        this.p2Map.put("attack", new ArrayList());
        this.p2Map.put("breach", new ArrayList());
        this.p2Map.put("damage", new ArrayList());
        this.p2Map.put("death", new ArrayList());
        this.p2Map.put("melee", new ArrayList());
        this.p2Map.put("move", new ArrayList());
        this.p2Map.put("selfDestruct", new ArrayList());
        this.p2Map.put("shield", new ArrayList());
        this.p2Map.put("spawn", new ArrayList());
    }

    public Map<String, Object> getEventsForRound(int playerNumber) {
        if (playerNumber == 0) {
            return this.p1Map;
        }
        return this.p2Map;
    }

    public <T extends GlobalDisplayEvent> Consumer<T> getEventAdder() {
        return globalEvent -> {
            if (globalEvent.getClass() == GlobalMelee.class) {
                GlobalMelee gevent = (GlobalMelee)globalEvent;
                String checkExists = gevent.srcID + "," + gevent.targetLocation.x + "," + gevent.targetLocation.y;
                if (!this.meleeList.containsKey(checkExists)) {
                    this.meleeList.put(checkExists, gevent);
                    try {
                        ((ArrayList)this.p1Map.get((String)gevent.getClass().getField("name").get(null))).add(gevent.inPerspective(0));
                        ((ArrayList)this.p2Map.get((String)gevent.getClass().getField("name").get(null))).add(gevent.inPerspective(1));
                    }
                    catch (Exception e) {
                        System.out.println("Failed to handle event: " + gevent.getClass().getName());
                    }
                }
            } else {
                ((ArrayList)this.p1Map.get(globalEvent.name())).add(globalEvent.inPerspective(0));
                ((ArrayList)this.p2Map.get(globalEvent.name())).add(globalEvent.inPerspective(1));
            }
        };
    }
}

