/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.events.globalevents;

import com.c1games.terminal.game.events.globalevents.GlobalDisplayEvent;
import com.c1games.terminal.game.systems.map.MapHelper;
import com.c1games.terminal.game.systems.map.structure.Coords;

public class GlobalBreached
extends GlobalDisplayEvent {
    public Coords srcLocation;
    public float damage;
    public int typeID;
    public String srcID;
    public int srcPID;
    public static final String name = "breach";

    @Override
    public String name() {
        return name;
    }

    public GlobalBreached(Object source, Coords srcLocation, float damage, int typeID, String srcID, int srcPID) {
        super(source);
        this.srcLocation = srcLocation;
        this.damage = damage;
        this.typeID = typeID;
        this.srcID = srcID;
        this.srcPID = srcPID;
    }

    @Override
    public Object[] inPerspective(int playerPerspective) {
        Object[] ret = new Object[]{MapHelper.convertUserEncodePerspective(this.srcLocation.x, this.srcLocation.y, playerPerspective), Float.valueOf(this.damage), this.typeID, this.srcID, GlobalBreached.samePlayerToPlayerID(this.srcPID == playerPerspective)};
        return ret;
    }
}

