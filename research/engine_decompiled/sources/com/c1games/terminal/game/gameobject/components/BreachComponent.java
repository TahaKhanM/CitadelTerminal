/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.gameobject.components;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.Component;
import com.c1games.terminal.game.gameobject.components.NavigationComponent;
import java.util.function.Function;

public class BreachComponent
extends Component {
    public float playerDamage;
    public float metalForBreach;

    private static Function<Gameobject, Boolean> requirements() {
        return gameobject -> gameobject.getComponents(NavigationComponent.class) != null;
    }

    public BreachComponent(Game game, Gameobject host, float playerDamage, float metalForBreach) {
        super(game, host, BreachComponent.requirements());
        this.playerDamage = playerDamage;
        this.metalForBreach = metalForBreach;
        this.initFinalizeCallEnable(true);
    }
}

