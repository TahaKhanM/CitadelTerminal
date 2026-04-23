/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems;

import com.c1games.terminal.game.events.globalevents.GlobalShield;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.UniqueId;
import com.c1games.terminal.game.gameobject.components.ColliderComponent;
import com.c1games.terminal.game.gameobject.components.HealthComponent;
import com.c1games.terminal.game.gameobject.components.PlayerInfoComponent;
import com.c1games.terminal.game.gameobject.components.PositionComponent;
import com.c1games.terminal.game.gameobject.components.ShielderComponent;
import com.c1games.terminal.game.gameobject.components.UnitInfoComponent;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ShieldSystem {
    public static void processShieldDecay(ArrayList<HealthComponent> toDecay) {
        for (HealthComponent hc : toDecay) {
            for (int i = 0; i < hc.shieldHPs.size(); ++i) {
                hc.shieldHPs.get((int)i).shieldHP += hc.shieldHPs.get((int)i).shieldDecayPerFrame;
                if (!(hc.shieldHPs.get((int)i).shieldHP < 0.0f)) continue;
                hc.shieldHPs.get((int)i).shieldHP = 0.0f;
            }
        }
    }

    public static void processShieldGiveSystem(ArrayList<ShieldSystemComponents> shielders, Consumer<GlobalShield> addShieldEvent) {
        for (ShieldSystemComponents shielder : shielders) {
            if (!(shielder.shielderComponent.shieldPerUnit > 0.0f)) continue;
            float diffFromMid = (float)Math.abs(13.5 - (double)shielder.shielderPosition.position.y) - 0.5f;
            float shieldAmount = shielder.shielderComponent.shieldPerUnit + (13.0f - diffFromMid) * shielder.shielderComponent.bonusShieldPerY;
            for (ColliderComponent target : shielder.shieldCollider.collidedWithThisTurn) {
                if (shielder.shielderComponent.shieldedAlready.contains(target.gameobject.getGid().getId())) continue;
                shielder.shielderComponent.shieldedAlready.add(target.gameobject.getGid().getId());
                ShieldTargetInfo shieldTargetInfo = new ShieldTargetInfo(target.gameobject.getComponent(HealthComponent.class), target.gameobject.getGid(), new ShieldTargetInfo.ShieldTargetExtraInfo(target.gameobject.getComponent(PositionComponent.class)));
                HealthComponent.AddNewShield(shieldTargetInfo.targetHealth, shieldAmount, shielder.shielderComponent.shieldDecayPerFrame);
                GlobalShield globalShield = new GlobalShield(shielder.shielderComponent, shielder.shielderPosition.position, shieldTargetInfo.extraInfo.targetPosition.position, shieldAmount, shielder.extraInfo.shielderUnitInfo.typeNumber, shielder.extraInfo.shielderGid.getId(), shieldTargetInfo.targetGid.getId(), shielder.extraInfo.shielderPlayerInfo.playerIndex);
                addShieldEvent.accept(globalShield);
            }
        }
    }

    public static class ShieldSystemComponents {
        public ColliderComponent shieldCollider;
        public ShielderComponent shielderComponent;
        public PositionComponent shielderPosition;
        public ShieldExtraInfo extraInfo;

        private ShieldSystemComponents(PositionComponent shielderPosition, ColliderComponent shieldCollider, ShielderComponent shielderComponent, ShieldExtraInfo extraInfo) {
            this.shielderPosition = shielderPosition;
            this.shieldCollider = shieldCollider;
            this.shielderComponent = shielderComponent;
            this.extraInfo = extraInfo;
        }

        public static ShieldSystemComponents tryCreateShieldSystemComponents(Gameobject shielder) {
            PositionComponent shielderPosition = shielder.getComponent(PositionComponent.class);
            UnitInfoComponent shielderUnitInfo = shielder.getComponent(UnitInfoComponent.class);
            PlayerInfoComponent shielderPlayerInfo = shielder.getComponent(PlayerInfoComponent.class);
            ShielderComponent shielderComponent = shielder.getComponent(ShielderComponent.class);
            UniqueId shielderGid = shielder.getGid();
            if (shielderPosition != null && shielderUnitInfo != null && shielderPlayerInfo != null && shielderComponent != null) {
                for (ColliderComponent coll : shielder.getComponents(ColliderComponent.class)) {
                    if (coll.collisionType != ColliderComponent.CollisionType.Shield) continue;
                    return new ShieldSystemComponents(shielderPosition, coll, shielderComponent, new ShieldExtraInfo(shielderUnitInfo, shielderGid, shielderPlayerInfo));
                }
                return null;
            }
            return null;
        }

        public static class ShieldExtraInfo {
            public UnitInfoComponent shielderUnitInfo;
            public UniqueId shielderGid;
            public PlayerInfoComponent shielderPlayerInfo;

            public ShieldExtraInfo(UnitInfoComponent shielderUnitInfo, UniqueId shielderGid, PlayerInfoComponent shielderPlayerInfo) {
                this.shielderUnitInfo = shielderUnitInfo;
                this.shielderGid = shielderGid;
                this.shielderPlayerInfo = shielderPlayerInfo;
            }
        }
    }

    public static class ShieldTargetInfo {
        public HealthComponent targetHealth;
        public UniqueId targetGid;
        public ShieldTargetExtraInfo extraInfo;

        private ShieldTargetInfo(HealthComponent targetHealth, UniqueId targetGid, ShieldTargetExtraInfo extraInfo) {
            this.targetHealth = targetHealth;
            this.targetGid = targetGid;
            this.extraInfo = extraInfo;
        }

        public static class ShieldTargetExtraInfo {
            public PositionComponent targetPosition;

            public ShieldTargetExtraInfo(PositionComponent targetPosition) {
                this.targetPosition = targetPosition;
            }
        }
    }
}

