/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems.map;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.GameMain;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.PlayerInfoComponent;
import com.c1games.terminal.game.gameobject.components.SpawnComponent;
import com.c1games.terminal.game.systems.SpawnUnitsSystem;
import com.c1games.terminal.game.systems.map.structure.Coords;
import java.util.Arrays;
import java.util.List;

public class MapHelper {
    Game game;
    GameMain gameMain;

    public MapHelper(Game game, GameMain gameMain) {
        this.game = game;
        this.gameMain = gameMain;
    }

    public float[] getMetalOnBoard() {
        float[] totals = new float[2];
        for (Coords c : this.game.map.allCoords()) {
            List<Gameobject> objects = this.game.map.getObjects(c);
            for (Gameobject g : objects) {
                SpawnComponent sinfo = g.getComponent(SpawnComponent.class);
                float cost = sinfo.costMetal;
                int n = g.getComponent(PlayerInfoComponent.class).playerIndex;
                totals[n] = totals[n] + cost;
            }
        }
        return totals;
    }

    public Coords toPlayerPerspectiveMap(int x, int y, int playerID) {
        int[] ret = new int[2];
        int bs = 28 - 1;
        if (playerID == this.gameMain.p1stats.playerID) {
            ret[1] = bs - y;
            ret[0] = x;
        } else {
            ret[1] = y;
            ret[0] = bs - x;
        }
        return new Coords(ret[0], ret[1]);
    }

    public static int[] convertUserEncodePerspective(int x, int y, int playerID) {
        int[] ret = new int[2];
        int bs = 27;
        if (playerID == 0) {
            ret[0] = x;
            ret[1] = y;
        } else {
            ret[0] = bs - x;
            ret[1] = bs - y;
        }
        return ret;
    }

    public static int[] convertUserEncodePerspective(float x, float y, int playerID) {
        return MapHelper.convertUserEncodePerspective((int)x, (int)y, playerID);
    }

    public String printMapPlayerAlwaysBottom(int playerNumber, SpawnUnitsSystem.UnitConfig[] typeDefinitions) {
        Object ret = "    ";
        int i = 0;
        while (true) {
            if (i >= 28) break;
            ret = (String)ret + " " + i;
            if (i < 10) {
                ret = (String)ret + " ";
            }
            ++i;
        }
        ret = (String)ret + "\n";
        i = 0;
        while (true) {
            if (i >= 28) break;
            ret = (String)ret + " " + (28 - 1 - i) + " ";
            if (28 - 1 - i < 10) {
                ret = (String)ret + " ";
            }
            int j = 0;
            while (true) {
                if (j >= 28) break;
                Coords xy = this.toPlayerPerspectiveMap(j, i, playerNumber);
                Object v = this.game.map.getShorthand(xy, typeDefinitions);
                if (((String)v).length() == 0) {
                    v = "[ ]";
                    if (!this.game.bounds.inArena(xy)) {
                        v = "[|]";
                    }
                } else if (((String)v).length() == 1) {
                    v = "[" + (String)v + "]";
                } else if (((String)v).length() == 2) {
                    v = "." + (String)v;
                }
                ret = (String)ret + (String)v;
                ++j;
            }
            ret = (String)ret + " " + (28 - 1 - i) + " ";
            if (28 - 1 - i < 10) {
                ret = (String)ret + " ";
            }
            ret = (String)ret + "\n";
            ++i;
        }
        ret = (String)ret + "    ";
        i = 0;
        while (true) {
            if (i >= 28) break;
            ret = (String)ret + " " + i;
            if (i < 10) {
                ret = (String)ret + " ";
            }
            ++i;
        }
        ret = (String)ret + "\n";
        return ret;
    }

    public boolean isOnPlayerSideAndUnoccupiedLocation(Coords loc, int playerID) {
        boolean playerSide = this.isOnPlayerSide(loc, playerID);
        if (!playerSide) {
            return false;
        }
        return !this.game.map.isTowerAt(loc);
    }

    public boolean isOnPlayerSideAndOccupied(Coords loc, int playerID) {
        boolean playerSide = this.isOnPlayerSide(loc, playerID);
        if (!playerSide) {
            return false;
        }
        return this.game.map.isTowerAt(loc);
    }

    public boolean isOnPlayerSide(Coords loc, int playerID) {
        int ysize = this.game.map.ySize;
        return !(playerID == this.gameMain.p1stats.playerID ? loc.y >= ysize / 2 : loc.y < ysize / 2);
    }

    public List<Coords> checkIfOnEdgeAndReturnOpposite(Coords location, int playerID) {
        if (playerID == this.gameMain.p1stats.playerID) {
            for (int i = 0; i < this.gameMain.p1Edges.size(); ++i) {
                Coords v = this.gameMain.p1Edges.get(i);
                if (!v.equals(location)) continue;
                if (i < this.gameMain.p1Edges.size() / 2) {
                    return Arrays.asList(this.game.bounds.edgeLists[0]);
                }
                return Arrays.asList(this.game.bounds.edgeLists[1]);
            }
            return null;
        }
        for (int i = 0; i < this.gameMain.p2Edges.size(); ++i) {
            Coords v = this.gameMain.p2Edges.get(i);
            if (!v.equals(location)) continue;
            if (i < this.gameMain.p2Edges.size() / 2) {
                return Arrays.asList(this.game.bounds.edgeLists[3]);
            }
            return Arrays.asList(this.game.bounds.edgeLists[2]);
        }
        return null;
    }
}
