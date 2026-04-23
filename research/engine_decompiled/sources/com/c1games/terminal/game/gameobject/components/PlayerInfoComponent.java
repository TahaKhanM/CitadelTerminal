/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.gameobject.components;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.Component;

public class PlayerInfoComponent
extends Component {
    public int playerIndex;

    public PlayerInfoComponent(Game game, Gameobject host, int playerIndex) {
        super(game, host);
        this.playerIndex = playerIndex;
        this.initFinalizeCallEnable(true);
    }
}

