/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.gameobject.components;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.Component;

public class RefundComponent
extends Component {
    public int turnsRequiredToRemove;
    public int turnStartRemoval;
    public float refundPercentage;

    public RefundComponent(Game game, Gameobject host, int turnsRequiredToRemove, int turnStartRemoval, float refundPercentage) {
        super(game, host);
        this.turnsRequiredToRemove = turnsRequiredToRemove;
        this.turnStartRemoval = turnStartRemoval;
        this.refundPercentage = refundPercentage;
        this.initFinalizeCallEnable(true);
    }
}

