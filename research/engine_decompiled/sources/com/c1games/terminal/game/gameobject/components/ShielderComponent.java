/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.gameobject.components;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.ColliderComponent;
import com.c1games.terminal.game.gameobject.components.Component;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Function;

public class ShielderComponent
extends Component {
    public float shieldPerUnit;
    public float shieldDecayPerFrame;
    public float bonusShieldPerY;
    public HashSet<String> shieldedAlready = new HashSet();

    private static Function<Gameobject, Boolean> requirements() {
        return gameobject -> {
            ArrayList<ColliderComponent> colliders = gameobject.getComponents(ColliderComponent.class);
            boolean hasCorrectCollider = false;
            for (ColliderComponent collider : colliders) {
                if (collider.collisionType != ColliderComponent.CollisionType.Shield) continue;
                hasCorrectCollider = true;
            }
            return hasCorrectCollider;
        };
    }

    public ShielderComponent(Game game, Gameobject host, float shieldPerUnit, float shieldDecayPerFrame, float bonusShieldPerY) {
        super(game, host, ShielderComponent.requirements());
        this.shieldPerUnit = shieldPerUnit;
        this.shieldDecayPerFrame = shieldDecayPerFrame;
        this.bonusShieldPerY = bonusShieldPerY;
        this.initFinalizeCallEnable(true);
    }
}

