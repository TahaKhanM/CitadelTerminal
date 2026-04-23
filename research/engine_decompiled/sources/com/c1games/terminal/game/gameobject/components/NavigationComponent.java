/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.gameobject.components;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.Component;
import com.c1games.terminal.game.gameobject.components.PositionComponent;
import com.c1games.terminal.game.systems.map.path.PathFinder;
import com.c1games.terminal.game.systems.map.structure.Coords;
import java.util.List;
import java.util.function.Function;

public class NavigationComponent
extends Component {
    public List<Coords> navigationTargetLocations;
    public int lastMove = 0;
    public boolean navigating = true;
    public boolean reachedTarget = false;
    public boolean finishedNavigating = false;
    public float speed;
    public float currentMoveBuildup = 0.0f;
    public PathFinder pathfinder;
    public int stepsTaken = 0;

    private static Function<Gameobject, Boolean> requirements() {
        return gameobject -> gameobject.getComponents(PositionComponent.class) != null;
    }

    public NavigationComponent(Game game, Gameobject host, PathFinder pathfinder, float speed, List<Coords> navigationTargetLocations) {
        super(game, host, NavigationComponent.requirements());
        this.pathfinder = pathfinder;
        this.speed = speed;
        this.navigationTargetLocations = navigationTargetLocations;
        this.initFinalizeCallEnable(true);
    }
}

