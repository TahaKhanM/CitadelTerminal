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

public class AttackComponent
extends Component {
    public float attackTower;
    public float attackWalker;

    private static Function<Gameobject, Boolean> requirements() {
        return gameobject -> {
            ArrayList<ColliderComponent> colliders = gameobject.getComponents(ColliderComponent.class);
            boolean hasAttackCollider = false;
            for (ColliderComponent collider : colliders) {
                if (collider.collisionType != ColliderComponent.CollisionType.Attack) continue;
                hasAttackCollider = true;
            }
            return hasAttackCollider;
        };
    }

    public AttackComponent(Game game, Gameobject host, float attackTower, float attackWalker) {
        super(game, host, AttackComponent.requirements());
        this.attackTower = attackTower;
        this.attackWalker = attackWalker;
        this.initFinalizeCallEnable(true);
    }
}

