/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.gameobject.components;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.Component;

public class InvestmentComponent
extends Component {
    public float resource1;
    public float resource2;

    public InvestmentComponent(Game game, Gameobject host, float resource1, float resource2) {
        super(game, host);
        this.resource1 = resource1;
        this.resource2 = resource2;
        this.initFinalizeCallEnable(true);
    }
}

