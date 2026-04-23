/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.gameobject.components;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.Component;
import com.c1games.terminal.game.systems.map.structure.Coords;

public class SpawnComponent
extends Component {
    public Coords spawnLocation;
    public float costMetal;
    public float costFood;

    public SpawnComponent(Game game, Gameobject host, Coords spawnLocation, float costMetal, float costFood) {
        super(game, host);
        this.spawnLocation = spawnLocation;
        this.costMetal = costMetal;
        this.costFood = costFood;
        this.initFinalizeCallEnable(true);
    }
}

