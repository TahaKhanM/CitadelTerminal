/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.gameobject.components;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.Component;
import com.c1games.terminal.game.gameobject.components.UnitInfoComponent;
import java.util.function.Function;

public class UpgradeComponent
extends Component {
    public boolean upgraded = false;

    private static Function<Gameobject, Boolean> requirements() {
        return gameobject -> gameobject.getComponents(UnitInfoComponent.class) != null;
    }

    public UpgradeComponent(Game game, Gameobject host, boolean upgraded) {
        super(game, host, UpgradeComponent.requirements());
        this.upgraded = upgraded;
        this.initFinalizeCallEnable(true);
    }
}

