/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.gameobject.components;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.Component;
import com.c1games.terminal.game.units.CategoryType;

public class UnitInfoComponent
extends Component {
    public CategoryType category;
    public int typeNumber;

    public UnitInfoComponent(Game game, Gameobject host, CategoryType type, int typeNumber) {
        super(game, host);
        this.category = type;
        this.typeNumber = typeNumber;
        this.initFinalizeCallEnable(true);
    }
}

