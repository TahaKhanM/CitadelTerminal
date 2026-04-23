/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems;

import com.c1games.terminal.game.events.globalevents.GlobalMove;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.UniqueId;
import com.c1games.terminal.game.gameobject.components.ColliderComponent;
import com.c1games.terminal.game.gameobject.components.NavigationComponent;
import com.c1games.terminal.game.gameobject.components.PlayerInfoComponent;
import com.c1games.terminal.game.gameobject.components.PositionComponent;
import com.c1games.terminal.game.gameobject.components.UnitInfoComponent;
import com.c1games.terminal.game.systems.map.structure.Coords;
import java.util.ArrayList;
import java.util.function.Consumer;

public class NavigateToEdgeSystem {
    private static void teleportBody(MovementComponents toMove, Coords positionDiff, Consumer<GlobalMove> addMoveEvent) {
        Coords newPosition = new Coords(toMove.body.position.x + positionDiff.x, toMove.body.position.y + positionDiff.y);
        toMove.body.game.map.removeObject(toMove.body, toMove.body.position.x, toMove.body.position.y);
        Coords oldPosition = toMove.body.position;
        toMove.body.position = newPosition;
        ++toMove.nav.stepsTaken;
        if (toMove.body.isEnabled()) {
            toMove.body.game.map.addObject(toMove.body, toMove.body.position.x, toMove.body.position.y);
            GlobalMove newMove = new GlobalMove(toMove, oldPosition, newPosition, Coords.ORIGIN, toMove.extraInfo.unitInfo.typeNumber, toMove.extraInfo.gid.getId(), toMove.extraInfo.playerInfo.playerIndex);
            addMoveEvent.accept(newMove);
        }
        for (ColliderComponent collider : toMove.colliders) {
            ColliderComponent.updateSensor(collider, toMove.body.position);
        }
    }

    public static void moveComponents(ArrayList<MovementComponents> toMove, Consumer<GlobalMove> addMoveEvent) {
        block0: for (int i = 0; i < toMove.size(); ++i) {
            PositionComponent body2 = toMove.get((int)i).body;
            NavigationComponent nav = toMove.get((int)i).nav;
            if (!nav.navigating) continue;
            nav.currentMoveBuildup += nav.speed;
            if (!(nav.currentMoveBuildup >= 0.9999f)) continue;
            nav.currentMoveBuildup -= 1.0f;
            int[] nextTile = nav.pathfinder.getStep(body2.position.x, body2.position.y, nav.lastMove);
            nav.lastMove = nextTile[1] == body2.position.y ? 1 : 2;
            Coords targetTileDiff = new Coords(nextTile[0] - body2.position.x, nextTile[1] - body2.position.y);
            if (targetTileDiff.x == 0 && targetTileDiff.y == 0) {
                nav.navigating = false;
                nav.speed = 0.0f;
                nav.finishedNavigating = true;
                for (Coords v : nav.navigationTargetLocations) {
                    if (v.x != body2.position.x || v.y != body2.position.y) continue;
                    nav.reachedTarget = true;
                    continue block0;
                }
                continue;
            }
            for (MovementComponents newMove : toMove.get(i).getThisAndAllDescendants()) {
                NavigateToEdgeSystem.teleportBody(newMove, targetTileDiff, addMoveEvent);
            }
        }
    }

    public static class MovementComponents {
        public PositionComponent body;
        public ArrayList<ColliderComponent> colliders;
        public NavigationComponent nav;
        public ComponentsForMoveEvent extraInfo;

        public MovementComponents(PositionComponent body2, ArrayList<ColliderComponent> colliders, NavigationComponent nav, ComponentsForMoveEvent extraInfo) {
            this.body = body2;
            this.colliders = colliders;
            this.nav = nav;
            this.extraInfo = extraInfo;
        }

        public static MovementComponents tryCreateMovementComponents(Gameobject gameObject) {
            PositionComponent body2 = gameObject.getComponent(PositionComponent.class);
            ArrayList<ColliderComponent> colliders = gameObject.getComponents(ColliderComponent.class);
            NavigationComponent nav = gameObject.getComponent(NavigationComponent.class);
            PlayerInfoComponent pInfo = gameObject.getComponent(PlayerInfoComponent.class);
            UnitInfoComponent unitInfo = gameObject.getComponent(UnitInfoComponent.class);
            if (body2 == null || colliders == null || nav == null || pInfo == null || unitInfo == null) {
                return null;
            }
            return new MovementComponents(body2, colliders, nav, new ComponentsForMoveEvent(pInfo, unitInfo, body2.gameobject.getGid()));
        }

        public ArrayList<MovementComponents> getThisAndAllDescendants() {
            ArrayList<Gameobject> familyObjs = this.body.gameobject.getThisPlusNestedChildren();
            ArrayList<MovementComponents> movementComponents = new ArrayList<MovementComponents>();
            for (Gameobject gobj : familyObjs) {
                MovementComponents newMove = MovementComponents.tryCreateMovementComponents(gobj);
                if (newMove == null) continue;
                movementComponents.add(newMove);
            }
            return movementComponents;
        }

        public static class ComponentsForMoveEvent {
            public PlayerInfoComponent playerInfo;
            public UnitInfoComponent unitInfo;
            public UniqueId gid;

            public ComponentsForMoveEvent(PlayerInfoComponent playerInfo, UnitInfoComponent unitInfo, UniqueId gid) {
                this.playerInfo = playerInfo;
                this.unitInfo = unitInfo;
                this.gid = gid;
            }
        }
    }
}

