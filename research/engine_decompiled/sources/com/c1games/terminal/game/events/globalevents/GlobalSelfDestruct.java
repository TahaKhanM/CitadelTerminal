/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.events.globalevents;

import com.c1games.terminal.game.events.globalevents.GlobalDisplayEvent;
import com.c1games.terminal.game.systems.map.MapHelper;
import com.c1games.terminal.game.systems.map.structure.Coords;
import java.util.ArrayList;

public class GlobalSelfDestruct
extends GlobalDisplayEvent {
    public Coords srcLocation;
    public ArrayList<Coords> targetLocations;
    public float damage;
    public int typeID;
    public String srcID;
    public int srcPID;
    public static final String name = "selfDestruct";

    @Override
    public String name() {
        return name;
    }

    public GlobalSelfDestruct(Object source, Coords srcLocation, ArrayList<Coords> targetLocations, float damage, int typeID, String srcID, int srcPID) {
        super(source);
        this.srcLocation = srcLocation;
        this.targetLocations = targetLocations;
        this.damage = damage;
        this.typeID = typeID;
        this.srcID = srcID;
        this.srcPID = srcPID;
    }

    @Override
    public Object[] inPerspective(int playerPerspective) {
        Object[] ret = new Object[6];
        ret[0] = MapHelper.convertUserEncodePerspective(this.srcLocation.x, this.srcLocation.y, playerPerspective);
        ArrayList<int[]> newVecs = new ArrayList<int[]>();
        for (Coords loc : this.targetLocations) {
            newVecs.add(MapHelper.convertUserEncodePerspective(loc.x, loc.y, playerPerspective));
        }
        ret[1] = newVecs;
        ret[2] = Float.valueOf(this.damage);
        ret[3] = this.typeID;
        ret[4] = this.srcID;
        ret[5] = GlobalSelfDestruct.samePlayerToPlayerID(this.srcPID == playerPerspective);
        return ret;
    }
}

