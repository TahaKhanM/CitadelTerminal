/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.events.globalevents;

import com.c1games.terminal.game.events.globalevents.GlobalDisplayEvent;
import com.c1games.terminal.game.systems.map.MapHelper;
import com.c1games.terminal.game.systems.map.structure.Coords;

public class GlobalShield
extends GlobalDisplayEvent {
    public Coords srcLocation;
    public Coords targetLocation;
    public float damage;
    public int typeID;
    public String srcID;
    public String targetID;
    public int srcPID;
    public static final String name = "shield";

    @Override
    public String name() {
        return name;
    }

    public GlobalShield(Object source, Coords srcLocation, Coords targetLocation, float damage, int typeID, String srcID, String targetID, int srcPID) {
        super(source);
        this.srcLocation = srcLocation;
        this.targetLocation = targetLocation;
        this.damage = damage;
        this.typeID = typeID;
        this.srcID = srcID;
        this.targetID = targetID;
        this.srcPID = srcPID;
    }

    @Override
    public Object[] inPerspective(int playerPerspective) {
        Object[] ret = new Object[]{MapHelper.convertUserEncodePerspective(this.srcLocation.x, this.srcLocation.y, playerPerspective), MapHelper.convertUserEncodePerspective(this.targetLocation.x, this.targetLocation.y, playerPerspective), Float.valueOf(this.damage), this.typeID, this.srcID, this.targetID, GlobalShield.samePlayerToPlayerID(this.srcPID == playerPerspective)};
        return ret;
    }
}

