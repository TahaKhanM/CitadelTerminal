/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.gameobject.components;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.Component;
import com.c1games.terminal.game.gameobject.components.PositionComponent;
import com.c1games.terminal.game.systems.map.structure.Coords;
import com.c1games.terminal.game.systems.proximity.ProximitySensor;
import com.c1games.terminal.game.systems.proximity.ProximitySensorFactory;
import java.util.ArrayList;
import java.util.function.Function;

public class ColliderComponent
extends Component {
    public final ProximitySensorFactory sensorFactory;
    private ProximitySensor<ColliderComponent> sensor;
    public CollisionType collisionType;
    public ArrayList<ColliderComponent> collidedWithThisTurn = new ArrayList();

    private static Function<Gameobject, Boolean> requirements() {
        return gameobject -> gameobject.getComponent(PositionComponent.class) != null;
    }

    public ColliderComponent(Game game, Gameobject host, Coords position, ProximitySensorFactory sensorFactory, CollisionType collisionType, boolean enable) {
        super(game, host, ColliderComponent.requirements());
        this.collisionType = collisionType;
        this.sensorFactory = sensorFactory;
        this.sensor = null;
        ColliderComponent.updateSensor(this, position);
        this.initFinalizeCallEnable(enable);
    }

    public static void updateSensor(ColliderComponent collider, Coords position) {
        if (collider.sensor != null) {
            collider.game.proximityArena.removeSensor(collider.sensor);
        }
        collider.sensor = collider.sensorFactory.create(collider, position);
        if (collider.isEnabled()) {
            collider.game.proximityArena.addSensor(collider.sensor);
        }
    }

    @Override
    protected void afterEnable() {
        if (this.sensor != null) {
            this.game.proximityArena.addSensor(this.sensor);
        }
    }

    @Override
    protected void afterDisable() {
        if (this.sensor != null) {
            this.game.proximityArena.removeSensor(this.sensor);
        }
    }

    @Override
    protected void onDestroy() {
        this.afterDisable();
    }

    @Override
    protected void onToDestroy() {
        this.afterDisable();
    }

    public static enum CollisionType {
        Attack,
        GetHit,
        Shield,
        SelfDestruct;

    }
}

