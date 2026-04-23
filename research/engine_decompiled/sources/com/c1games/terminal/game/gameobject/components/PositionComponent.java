/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.gameobject.components;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.Component;
import com.c1games.terminal.game.gameobject.components.UnitInfoComponent;
import com.c1games.terminal.game.systems.map.structure.Coords;
import java.util.function.Function;

public class PositionComponent
extends Component {
    public Coords position;

    private static Function<Gameobject, Boolean> requirements() {
        return gameobject -> gameobject.getComponents(UnitInfoComponent.class) != null;
    }

    public PositionComponent(Game game, Gameobject host, Coords position) {
        this(game, host, position, true);
    }

    public PositionComponent(Game game, Gameobject host, Coords position, boolean enable) {
        super(game, host, PositionComponent.requirements());
        this.position = position;
        this.initFinalizeCallEnable(enable);
    }

    public final Coords position() {
        return this.position;
    }

    public static float distance(PositionComponent component1, PositionComponent component2) {
        return component1.position.toFloat().dist(component2.position.toFloat());
    }

    @Override
    protected void afterEnable() {
        this.game.map.addObject(this, this.position.x, this.position.y);
    }

    @Override
    protected void afterDisable() {
        this.game.map.removeObject(this, this.position.x, this.position.y);
    }

    @Override
    protected void onDestroy() {
        this.game.map.removeObject(this, this.position.x, this.position.y);
    }

    @Override
    protected void onToDestroy() {
        this.game.map.removeObject(this, this.position.x, this.position.y);
    }
}
