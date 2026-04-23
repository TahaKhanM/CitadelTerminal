/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.gameobject.components.ColliderComponent;
import com.c1games.terminal.game.systems.proximity.Pair;
import com.c1games.terminal.game.systems.proximity.ProximityArena;
import com.c1games.terminal.game.systems.proximity.ProximitySensor;
import java.util.HashSet;
import java.util.Set;

public class CollisionSystem {
    public static boolean runCollisionSystem(ProximityArena<ColliderComponent> proximityArena, Game game) {
        HashSet<Pair<ProximitySensor<ColliderComponent>>> collisionsPerformed = new HashSet<Pair<ProximitySensor<ColliderComponent>>>();
        for (ColliderComponent c : game.getAllComponents(ColliderComponent.class)) {
            c.collidedWithThisTurn.clear();
        }
        return CollisionSystem.checkCollisions(collisionsPerformed, proximityArena);
    }

    private static boolean checkCollisions(Set<Pair<ProximitySensor<ColliderComponent>>> alreadyCollided, ProximityArena<ColliderComponent> proximityArena) {
        boolean performedCollision = false;
        for (Pair<ProximitySensor<ColliderComponent>> collision : proximityArena.getCollisions()) {
            if (alreadyCollided.contains(collision)) continue;
            ((ColliderComponent)((ProximitySensor)collision.elem1).userData).collidedWithThisTurn.add((ColliderComponent)((ProximitySensor)collision.elem2).userData);
            ((ColliderComponent)((ProximitySensor)collision.elem2).userData).collidedWithThisTurn.add((ColliderComponent)((ProximitySensor)collision.elem1).userData);
            alreadyCollided.add(collision);
            performedCollision = true;
        }
        return performedCollision;
    }
}

