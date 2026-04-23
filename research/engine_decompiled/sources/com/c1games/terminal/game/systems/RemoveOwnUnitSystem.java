/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems;

import com.c1games.terminal.game.PlayerStats;
import com.c1games.terminal.game.events.globalevents.GlobalDeath;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.UniqueId;
import com.c1games.terminal.game.gameobject.components.HealthComponent;
import com.c1games.terminal.game.gameobject.components.PlayerInfoComponent;
import com.c1games.terminal.game.gameobject.components.PositionComponent;
import com.c1games.terminal.game.gameobject.components.RefundComponent;
import com.c1games.terminal.game.gameobject.components.SpawnComponent;
import com.c1games.terminal.game.gameobject.components.UnitInfoComponent;
import java.util.ArrayList;
import java.util.function.Consumer;

public class RemoveOwnUnitSystem {
    public static void removeOwnUnitProcess(ArrayList<RemoveOwnUnitSystemComponents> toRemoves, int currentTurn, PlayerStats p1stats, PlayerStats p2stats, Consumer<Gameobject> gameObjectDestroy, Consumer<GlobalDeath> addDeathEvent) {
        for (RemoveOwnUnitSystemComponents toRemove : toRemoves) {
            if (toRemove.refundComponent.turnStartRemoval < 0 || toRemove.refundComponent.turnStartRemoval + toRemove.refundComponent.turnsRequiredToRemove > currentTurn || toRemove.refundComponent.gameobject.isDestroyed() || !(toRemove.healthComponent.getCurrentHP() > 0.0f)) continue;
            float hpPercent = toRemove.healthComponent.currentHP / toRemove.healthComponent.maxHP;
            float refundFood = toRemove.spawnComponent.costFood * hpPercent * toRemove.refundComponent.refundPercentage;
            float refundMetal = toRemove.spawnComponent.costMetal * hpPercent * toRemove.refundComponent.refundPercentage;
            PlayerStats mystats = toRemove.toRefundPlayerInfo.playerIndex == 0 ? p1stats : p2stats;
            mystats.addToFood(refundFood);
            mystats.addToMetal(refundMetal);
            toRemove.healthComponent.currentHP = -1.0f;
            gameObjectDestroy.accept(toRemove.refundComponent.gameobject);
            GlobalDeath gdeath = new GlobalDeath(toRemove.refundComponent, toRemove.extraRemovalInfo.attackerBody.position, toRemove.extraRemovalInfo.attackerUnitInfo.typeNumber, toRemove.extraRemovalInfo.Gid.getId(), toRemove.toRefundPlayerInfo.playerIndex, true);
            addDeathEvent.accept(gdeath);
        }
    }

    public static class RemoveOwnUnitSystemComponents {
        public PlayerInfoComponent toRefundPlayerInfo;
        public RefundComponent refundComponent;
        public SpawnComponent spawnComponent;
        public HealthComponent healthComponent;
        public ExtraRemovalInfo extraRemovalInfo;

        public RemoveOwnUnitSystemComponents(PlayerInfoComponent toRefundPlayerInfo, RefundComponent refundComponent, SpawnComponent spawnComponent, HealthComponent healthComponent, ExtraRemovalInfo extraRemovalInfo) {
            this.toRefundPlayerInfo = toRefundPlayerInfo;
            this.refundComponent = refundComponent;
            this.spawnComponent = spawnComponent;
            this.healthComponent = healthComponent;
            this.extraRemovalInfo = extraRemovalInfo;
        }

        public static RemoveOwnUnitSystemComponents tryCreateRemovalSystemComponents(Gameobject potentialRemove) {
            PlayerInfoComponent playerInfo = potentialRemove.getComponent(PlayerInfoComponent.class);
            RefundComponent refundComponent = potentialRemove.getComponent(RefundComponent.class);
            SpawnComponent spawnComponent = potentialRemove.getComponent(SpawnComponent.class);
            HealthComponent healthc = potentialRemove.getComponent(HealthComponent.class);
            PositionComponent body2 = potentialRemove.getComponent(PositionComponent.class);
            UnitInfoComponent uinfo = potentialRemove.getComponent(UnitInfoComponent.class);
            if (playerInfo != null && refundComponent != null && spawnComponent != null && healthc != null && body2 != null && uinfo != null) {
                return new RemoveOwnUnitSystemComponents(playerInfo, refundComponent, spawnComponent, healthc, new ExtraRemovalInfo(body2, refundComponent.gameobject.getGid(), uinfo));
            }
            return null;
        }

        public static class ExtraRemovalInfo {
            public PositionComponent attackerBody;
            public UniqueId Gid;
            public UnitInfoComponent attackerUnitInfo;

            public ExtraRemovalInfo(PositionComponent attackerBody, UniqueId gid, UnitInfoComponent attackerUnitInfo) {
                this.attackerBody = attackerBody;
                this.Gid = gid;
                this.attackerUnitInfo = attackerUnitInfo;
            }
        }
    }
}

