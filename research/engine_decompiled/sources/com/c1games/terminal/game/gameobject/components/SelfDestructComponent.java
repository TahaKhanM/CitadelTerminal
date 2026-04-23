/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.gameobject.components;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.ColliderComponent;
import com.c1games.terminal.game.gameobject.components.Component;
import java.util.ArrayList;
import java.util.function.Function;

public class SelfDestructComponent
extends Component {
    public float attackTower;
    public float attackWalker;
    public int minimumSteps;
    public boolean selfDestructed = false;

    private static Function<Gameobject, Boolean> requirements() {
        return gameobject -> {
            ArrayList<ColliderComponent> colliders = gameobject.getComponents(ColliderComponent.class);
            boolean hasCorrectCollider = false;
            for (ColliderComponent collider : colliders) {
                if (collider.collisionType != ColliderComponent.CollisionType.SelfDestruct) continue;
                hasCorrectCollider = true;
            }
            return hasCorrectCollider;
        };
    }

    public SelfDestructComponent(Game game, Gameobject host, float attackTower, float attackWalker, int minimumSteps) {
        super(game, host, SelfDestructComponent.requirements());
        this.attackTower = attackTower;
        this.attackWalker = attackWalker;
        this.minimumSteps = minimumSteps;
        this.initFinalizeCallEnable(true);
    }
}

