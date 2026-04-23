/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.events.globalevents;

import com.c1games.terminal.game.events.globalevents.GlobalDisplayEvent;
import com.c1games.terminal.game.systems.map.MapHelper;
import com.c1games.terminal.game.systems.map.structure.Coords;

public class GlobalMelee
extends GlobalDisplayEvent {
    public Coords srcLocation;
    public Coords targetLocation;
    public float damage;
    public int typeID;
    public String srcID;
    public int srcPID;
    public static final String name = "melee";

    @Override
    public String name() {
        return name;
    }

    public GlobalMelee(Object source, Coords srcLocation, Coords targetLocation, float damage, int typeID, String srcID, int srcPID) {
        super(source);
        this.srcLocation = srcLocation;
        this.targetLocation = targetLocation;
        this.damage = damage;
        this.typeID = typeID;
        this.srcID = srcID;
        this.srcPID = srcPID;
    }

    @Override
    public Object[] inPerspective(int playerPerspective) {
        Object[] ret = new Object[]{MapHelper.convertUserEncodePerspective(this.srcLocation.x, this.srcLocation.y, playerPerspective), MapHelper.convertUserEncodePerspective(this.targetLocation.x, this.targetLocation.y, playerPerspective), Float.valueOf(this.damage), this.typeID, this.srcID, GlobalMelee.samePlayerToPlayerID(this.srcPID == playerPerspective)};
        return ret;
    }
}

