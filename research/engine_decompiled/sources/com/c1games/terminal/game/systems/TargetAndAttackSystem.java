/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems;

import com.c1games.terminal.game.events.globalevents.GlobalAttack;
import com.c1games.terminal.game.events.globalevents.GlobalDamaged;
import com.c1games.terminal.game.events.globalevents.GlobalDeath;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.UniqueId;
import com.c1games.terminal.game.gameobject.components.AttackComponent;
import com.c1games.terminal.game.gameobject.components.ColliderComponent;
import com.c1games.terminal.game.gameobject.components.HealthComponent;
import com.c1games.terminal.game.gameobject.components.PlayerInfoComponent;
import com.c1games.terminal.game.gameobject.components.PositionComponent;
import com.c1games.terminal.game.gameobject.components.SelfDestructComponent;
import com.c1games.terminal.game.gameobject.components.UnitInfoComponent;
import com.c1games.terminal.game.systems.map.structure.Coords;
import com.c1games.terminal.game.units.CategoryType;
import java.util.ArrayList;
import java.util.function.Consumer;

public class TargetAndAttackSystem {
    public static void processTargeting(ArrayList<AttackerRelatedComponents> attacks, Consumer<GlobalAttack> addAttackEvent, Consumer<GlobalDamaged> addDamagedEvent, Consumer<GlobalDeath> addDeathEvent, Consumer<Gameobject> gameObjectDestroy, boolean printHitStrings) {
        for (AttackerRelatedComponents attack : attacks) {
            int n;
            int n2;
            boolean attackWhenDestroyed;
            boolean bl = attackWhenDestroyed = attack.selfDestructComponent != null && attack.selfDestructComponent.selfDestructed;
            if (!attack.attackerAttack.isEnabled() && !attackWhenDestroyed) continue;
            ArrayList<Object> victimUnitInfo = new ArrayList<Object>();
            ColliderComponent collider = attack.attackerCollider;
            assert (collider.collisionType == ColliderComponent.CollisionType.Attack);
            for (ColliderComponent victimCollider : collider.collidedWithThisTurn) {
                TargetPickingComponents potentialTarget = new TargetPickingComponents(victimCollider.gameobject.getComponent(UnitInfoComponent.class), victimCollider.gameobject.getComponent(HealthComponent.class), victimCollider.gameobject.getComponent(PositionComponent.class), victimCollider.gameobject.getGid(), new TargetPickingComponents.TargetExtraInfo(victimCollider.gameobject.getComponent(PlayerInfoComponent.class)));
                victimUnitInfo.add(potentialTarget);
            }
            ArrayList<TargetPickingComponents> lowPriority = new ArrayList<TargetPickingComponents>();
            ArrayList<TargetPickingComponents> highPriority = new ArrayList<TargetPickingComponents>();
            for (TargetPickingComponents targetPickingComponents : victimUnitInfo) {
                if (targetPickingComponents.targetUnitInfo.category == CategoryType.WALKER) {
                    highPriority.add(targetPickingComponents);
                    continue;
                }
                lowPriority.add(targetPickingComponents);
            }
            TargetPickingComponents toDamage = null;
            if (!highPriority.isEmpty() && (n2 = TargetAndAttackSystem.pickUnit(highPriority, attack.attackerBody, attack.attackerPlayerInfo)) >= 0) {
                toDamage = highPriority.get(n2);
            }
            if (!lowPriority.isEmpty() && toDamage == null && (n = TargetAndAttackSystem.pickUnit(lowPriority, attack.attackerBody, attack.attackerPlayerInfo)) >= 0) {
                toDamage = lowPriority.get(n);
            }
            if (toDamage != null) {
                TargetAndAttackSystem.attackEnemyUnit(toDamage.targetHealth, toDamage.targetBody.position, toDamage.targetUnitInfo, gameObjectDestroy, attack.attackerAttack, attack.extraInfo.attackerUnitInfo, attack.extraInfo.attackerGid, toDamage.targetGid, attack.attackerBody, attack.attackerPlayerInfo, printHitStrings, addAttackEvent, toDamage.extraInfo.targetPlayerInfo, addDamagedEvent, addDeathEvent);
            }
            lowPriority.clear();
            highPriority.clear();
        }
    }

    private static int pickUnit(ArrayList<TargetPickingComponents> units, PositionComponent attackingUnit, PlayerInfoComponent attackingUnitPlayer) {
        int closestIndex = -1;
        float closestDistance = 1.0E10f;
        float closestHealth = 1.0E10f;
        float distanceToPlayerStart = 1.0E10f;
        float distanceToCenter = 0.0f;
        float startPos = attackingUnitPlayer.playerIndex == 0 ? 0.0f : 28.0f;
        float centerPos = 13.5f;
        String targetgid = "";
        for (int i = 0; i < units.size(); ++i) {
            boolean gameObjectIDLarger;
            if (!(units.get((int)i).targetHealth.getCurrentHP() > 0.0f)) continue;
            float newDist = PositionComponent.distance(units.get((int)i).targetBody, attackingUnit);
            boolean closer = newDist < closestDistance;
            boolean equalDistance = newDist == closestDistance;
            float newHP = units.get((int)i).targetHealth.getCurrentHP() + units.get((int)i).targetHealth.getShieldHP();
            boolean lessHP = newHP < closestHealth;
            boolean equalHP = newHP == closestHealth;
            float newDistStart = Math.abs((float)units.get((int)i).targetBody.position().y - startPos);
            boolean closerToStart = newDistStart < distanceToPlayerStart;
            boolean equalToStart = newDistStart == distanceToPlayerStart;
            float newDistCenter = Math.abs((float)units.get((int)i).targetBody.position().x - centerPos);
            boolean fartherFromCenter = newDistCenter > distanceToCenter;
            boolean equalFromCenter = newDistCenter == distanceToCenter;
            String newgid = units.get((int)i).targetGid.getId();
            boolean bl = gameObjectIDLarger = targetgid.equals("") || Integer.parseInt(newgid) > Integer.parseInt(targetgid);
            if (!(closer || equalDistance && lessHP || equalDistance && equalHP && closerToStart || equalDistance && equalHP && equalToStart && fartherFromCenter) && (!equalDistance || !equalHP || !equalToStart || !equalFromCenter || !gameObjectIDLarger)) continue;
            closestIndex = i;
            closestDistance = newDist;
            closestHealth = newHP;
            distanceToPlayerStart = newDistStart;
            distanceToCenter = newDistCenter;
            targetgid = newgid;
        }
        return closestIndex;
    }

    private static void attackEnemyUnit(HealthComponent victim, Coords victimPosition, UnitInfoComponent victimUnitInfo, Consumer<Gameobject> gameObjectDestroy, AttackComponent attackerAttack, UnitInfoComponent attackerUnitInfo, UniqueId attackerGid, UniqueId victimGid, PositionComponent attackerBody, PlayerInfoComponent attackerPlayer, boolean printHitStrings, Consumer<GlobalAttack> addAttackEvent, PlayerInfoComponent victimPlayerInfo, Consumer<GlobalDamaged> addDamagedEvent, Consumer<GlobalDeath> addDeathEvent) {
        float damage;
        Coords thisPos = attackerBody.position();
        float f = damage = victimUnitInfo.category == CategoryType.WALKER ? attackerAttack.attackWalker : attackerAttack.attackTower;
        if (damage > 0.0f) {
            if (printHitStrings) {
                System.out.println("victim getting attacked oldHP:" + victim.getCurrentHP() + " shieldHP:" + victim.getShieldHP() + " dmg:" + damage);
            }
            boolean victimDeath = HealthComponent.dealDamageToHealthComponent(victim, damage, gameObjectDestroy);
            if (printHitStrings) {
                System.out.println(String.valueOf(attackerAttack.gameobject) + String.valueOf(attackerBody.position) + " Hit other: " + String.valueOf(victim.gameobject) + String.valueOf(victimPosition) + " damage:" + damage + " newHP:" + victim.getCurrentHP() + " shieldHP:" + victim.getShieldHP());
            }
            GlobalAttack gattack = new GlobalAttack(attackerAttack, thisPos, victimPosition, damage, attackerUnitInfo.typeNumber, attackerGid.getId(), victimGid.getId(), attackerPlayer.playerIndex);
            addAttackEvent.accept(gattack);
            GlobalDamaged gdamage = new GlobalDamaged(victim, victimPosition, damage, victimUnitInfo.typeNumber, victimGid.getId(), victimPlayerInfo.playerIndex);
            addDamagedEvent.accept(gdamage);
            if (victimDeath) {
                GlobalDeath gdeath = new GlobalDeath(victim, victimPosition, victimUnitInfo.typeNumber, victimGid.getId(), victimPlayerInfo.playerIndex, false);
                addDeathEvent.accept(gdeath);
            }
        }
    }

    public static class AttackerRelatedComponents {
        public PositionComponent attackerBody;
        public ColliderComponent attackerCollider;
        public AttackComponent attackerAttack;
        public PlayerInfoComponent attackerPlayerInfo;
        public ComponentsForAttackEvent extraInfo;
        public SelfDestructComponent selfDestructComponent;

        public AttackerRelatedComponents(PositionComponent attackerBody, ColliderComponent attackerCollider, AttackComponent attackerAttack, PlayerInfoComponent attackerPlayerInfo, ComponentsForAttackEvent extraInfo, SelfDestructComponent selfDestructComponent) {
            this.attackerBody = attackerBody;
            this.attackerCollider = attackerCollider;
            this.attackerAttack = attackerAttack;
            this.attackerPlayerInfo = attackerPlayerInfo;
            this.extraInfo = extraInfo;
            this.selfDestructComponent = selfDestructComponent;
        }

        public static ArrayList<AttackerRelatedComponents> tryCreateAttackTargetingComponents(Gameobject potentialAttacker) {
            ArrayList<AttackerRelatedComponents> attacks = new ArrayList<AttackerRelatedComponents>();
            ArrayList<ColliderComponent> allColliders = potentialAttacker.getComponents(ColliderComponent.class);
            PositionComponent body2 = potentialAttacker.getComponent(PositionComponent.class);
            AttackComponent attack = potentialAttacker.getComponent(AttackComponent.class);
            PlayerInfoComponent attackingPlayer = potentialAttacker.getComponent(PlayerInfoComponent.class);
            UniqueId attackingGid = potentialAttacker.getGid();
            UnitInfoComponent attackUnitInfo = potentialAttacker.getComponent(UnitInfoComponent.class);
            if (body2 != null && attack != null && attackingPlayer != null && attackUnitInfo != null) {
                for (ColliderComponent collider : allColliders) {
                    if (collider.collisionType != ColliderComponent.CollisionType.Attack || collider.collidedWithThisTurn.size() <= 0) continue;
                    AttackerRelatedComponents newAttack = new AttackerRelatedComponents(body2, collider, attack, attackingPlayer, new ComponentsForAttackEvent(attackingGid, attackUnitInfo), potentialAttacker.getComponent(SelfDestructComponent.class));
                    attacks.add(newAttack);
                }
            }
            return attacks;
        }

        public static class ComponentsForAttackEvent {
            public UniqueId attackerGid;
            public UnitInfoComponent attackerUnitInfo;

            public ComponentsForAttackEvent(UniqueId attackerGid, UnitInfoComponent attackerUnitInfo) {
                this.attackerGid = attackerGid;
                this.attackerUnitInfo = attackerUnitInfo;
            }
        }
    }

    public static class TargetPickingComponents {
        public UnitInfoComponent targetUnitInfo;
        public HealthComponent targetHealth;
        public PositionComponent targetBody;
        public UniqueId targetGid;
        public TargetExtraInfo extraInfo;

        public TargetPickingComponents(UnitInfoComponent targetUnitInfo, HealthComponent targetHealth, PositionComponent targetBody, UniqueId targetGid, TargetExtraInfo extraInfo) {
            this.targetUnitInfo = targetUnitInfo;
            this.targetHealth = targetHealth;
            this.targetBody = targetBody;
            this.targetGid = targetGid;
            this.extraInfo = extraInfo;
        }

        public static class TargetExtraInfo {
            public PlayerInfoComponent targetPlayerInfo;

            public TargetExtraInfo(PlayerInfoComponent targetPlayerInfo) {
                this.targetPlayerInfo = targetPlayerInfo;
            }
        }
    }
}

