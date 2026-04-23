/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.events.globalevents;

import com.c1games.terminal.game.events.globalevents.GlobalDisplayEvent;
import com.c1games.terminal.game.systems.map.MapHelper;
import com.c1games.terminal.game.systems.map.structure.Coords;

public class GlobalMove
extends GlobalDisplayEvent {
    public Coords oldLocation;
    public Coords newLocation;
    public Coords nextLocation;
    public int typeID;
    public String gid;
    public int srcPID;
    public static final String name = "move";

    @Override
    public String name() {
        return name;
    }

    public GlobalMove(Object source, Coords oldLocation, Coords newLocation, Coords nextLocation, int typeID, String gid, int srcPID) {
        super(source);
        this.oldLocation = oldLocation;
        this.newLocation = newLocation;
        this.nextLocation = nextLocation;
        this.typeID = typeID;
        this.gid = gid;
        this.srcPID = srcPID;
    }

    @Override
    public Object[] inPerspective(int playerPerspective) {
        Object[] ret = new Object[]{MapHelper.convertUserEncodePerspective(this.oldLocation.x, this.oldLocation.y, playerPerspective), MapHelper.convertUserEncodePerspective(this.newLocation.x, this.newLocation.y, playerPerspective), MapHelper.convertUserEncodePerspective(this.nextLocation.x, this.nextLocation.y, playerPerspective), this.typeID, this.gid, GlobalMove.samePlayerToPlayerID(this.srcPID == playerPerspective)};
        return ret;
    }
}

