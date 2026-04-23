/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems.map;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.PositionComponent;
import com.c1games.terminal.game.gameobject.components.UnitInfoComponent;
import com.c1games.terminal.game.systems.SpawnUnitsSystem;
import com.c1games.terminal.game.systems.map.structure.Coords;
import com.c1games.terminal.game.systems.map.structure.Grid;
import com.c1games.terminal.game.units.CategoryType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Map {
    public final int xSize;
    public final int ySize;
    private Game game;
    private Grid<Set<Gameobject>> gameObjects;

    public Map(Game game, int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.game = game;
        this.gameObjects = new Grid(xSize, ySize);
        for (Coords c : Coords.ORIGIN.until(new Coords(xSize, ySize))) {
            this.gameObjects.set(c, new HashSet());
        }
    }

    public void addObject(PositionComponent bc, int x, int y) {
        this.gameObjects.get(new Coords(x, y)).add(bc.gameobject);
        if (this.isTowerAt(new Coords(x, y))) {
            this.game.bottomLeft.put(x, y);
            this.game.bottomRight.put(x, y);
            this.game.topRight.put(x, y);
            this.game.topLeft.put(x, y);
        }
    }

    public void removeObject(PositionComponent bc, int x, int y) {
        this.gameObjects.get(new Coords(x, y)).remove(bc.gameobject);
        if (!this.isTowerAt(new Coords(x, y))) {
            this.game.bottomLeft.remove(x, y);
            this.game.bottomRight.remove(x, y);
            this.game.topRight.remove(x, y);
            this.game.topLeft.remove(x, y);
        }
    }

    public boolean isTowerAt(Coords c) {
        for (Gameobject object : this.gameObjects.get(c)) {
            ArrayList<UnitInfoComponent> uinfos = object.getComponents(UnitInfoComponent.class);
            for (UnitInfoComponent uinfo : uinfos) {
                if (uinfo.category != CategoryType.TOWER) continue;
                return true;
            }
        }
        return false;
    }

    public List<Gameobject> getObjects(Coords c) {
        return this.gameObjects.get(c).stream().collect(Collectors.toList());
    }

    public Iterable<Coords> allCoords() {
        return this.gameObjects.allCoords();
    }

    public String getShorthand(Coords c, SpawnUnitsSystem.UnitConfig[] typeDefinitions) {
        for (Gameobject g : this.gameObjects.get(c)) {
            UnitInfoComponent uinfo = g.getComponent(UnitInfoComponent.class);
            if (uinfo == null) continue;
            return typeDefinitions[uinfo.typeNumber].shorthand;
        }
        return "";
    }
}
