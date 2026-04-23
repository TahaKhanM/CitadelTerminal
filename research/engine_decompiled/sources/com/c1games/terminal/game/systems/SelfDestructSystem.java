/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems;

import com.c1games.terminal.game.events.globalevents.GlobalDamaged;
import com.c1games.terminal.game.events.globalevents.GlobalDeath;
import com.c1games.terminal.game.events.globalevents.GlobalSelfDestruct;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.UniqueId;
import com.c1games.terminal.game.gameobject.components.ColliderComponent;
import com.c1games.terminal.game.gameobject.components.HealthComponent;
import com.c1games.terminal.game.gameobject.components.NavigationComponent;
import com.c1games.terminal.game.gameobject.components.PlayerInfoComponent;
import com.c1games.terminal.game.gameobject.components.PositionComponent;
import com.c1games.terminal.game.gameobject.components.SelfDestructComponent;
import com.c1games.terminal.game.gameobject.components.UnitInfoComponent;
import com.c1games.terminal.game.systems.map.structure.Coords;
import com.c1games.terminal.game.units.CategoryType;
import java.util.ArrayList;
import java.util.function.Consumer;

public class SelfDestructSystem {
    public static void processSelfDestructs(ArrayList<SelfDestructAttackerComponents> attackers, Consumer<Gameobject> gameObjectDestroy, Consumer<GlobalSelfDestruct> addSelfDestructEvent, Consumer<GlobalDamaged> addDamagedEvent, Consumer<GlobalDeath> addDeathEvent, boolean printHitStrings) {
        for (SelfDestructAttackerComponents attacker : attackers) {
            if (!attacker.nav.finishedNavigating || attacker.nav.reachedTarget) continue;
            if (attacker.nav.stepsTaken >= attacker.selfDestructComponent.minimumSteps) {
                GlobalSelfDestruct selfDestructEventWalker;
                attacker.selfDestructComponent.selfDestructed = true;
                ArrayList<Coords> attackedLocationsWalkers = new ArrayList<Coords>();
                ArrayList<Coords> attackedLocationsTowers = new ArrayList<Coords>();
                for (ColliderComponent collidedWith : attacker.selfDestructCollider.collidedWithThisTurn) {
                    SelfDestructVictimComponents victim = new SelfDestructVictimComponents(collidedWith.gameobject.getComponent(HealthComponent.class), collidedWith.gameobject.getComponent(UnitInfoComponent.class), new SelfDestructVictimComponents.ExtraVictimInfo(collidedWith.gameobject.getComponent(PositionComponent.class), collidedWith.gameobject.getGid(), collidedWith.gameobject.getComponent(PlayerInfoComponent.class)));
                    float damage = victim.unitInfo.category == CategoryType.WALKER ? attacker.selfDestructComponent.attackWalker : attacker.selfDestructComponent.attackTower;
                    boolean victimDeath = HealthComponent.dealDamageToHealthComponent(victim.targetHealth, damage, gameObjectDestroy);
                    if (victim.unitInfo.category == CategoryType.WALKER) {
                        attackedLocationsWalkers.add(victim.extraInfo.victimBody.position);
                    } else {
                        attackedLocationsTowers.add(victim.extraInfo.victimBody.position);
                    }
                    GlobalDamaged gdamage = new GlobalDamaged(victim.targetHealth, victim.extraInfo.victimBody.position, damage, victim.unitInfo.typeNumber, victim.extraInfo.victimGid.getId(), victim.extraInfo.victimPlayerInfo.playerIndex);
                    addDamagedEvent.accept(gdamage);
                    if (!victimDeath) continue;
                    GlobalDeath gdeath = new GlobalDeath(victim, victim.extraInfo.victimBody.position, victim.unitInfo.typeNumber, victim.extraInfo.victimGid.getId(), victim.extraInfo.victimPlayerInfo.playerIndex, false);
                    addDeathEvent.accept(gdeath);
                }
                if (attacker.selfDestructComponent.attackTower == attacker.selfDestructComponent.attackWalker) {
                    attackedLocationsWalkers.addAll(attackedLocationsTowers);
                    selfDestructEventWalker = new GlobalSelfDestruct(attacker.selfDestructComponent, attacker.attackerExtraInfo.attackerBody.position, attackedLocationsWalkers, attacker.selfDestructComponent.attackWalker, attacker.attackerExtraInfo.attackerUnitInfo.typeNumber, attacker.attackerExtraInfo.Gid.getId(), attacker.attackerExtraInfo.attackerPlayerInfo.playerIndex);
                    addSelfDestructEvent.accept(selfDestructEventWalker);
                } else {
                    selfDestructEventWalker = new GlobalSelfDestruct(attacker.selfDestructComponent, attacker.attackerExtraInfo.attackerBody.position, attackedLocationsWalkers, attacker.selfDestructComponent.attackWalker, attacker.attackerExtraInfo.attackerUnitInfo.typeNumber, attacker.attackerExtraInfo.Gid.getId(), attacker.attackerExtraInfo.attackerPlayerInfo.playerIndex);
                    addSelfDestructEvent.accept(selfDestructEventWalker);
                    GlobalSelfDestruct selfDestructEventTower = new GlobalSelfDestruct(attacker.selfDestructComponent, attacker.attackerExtraInfo.attackerBody.position, attackedLocationsTowers, attacker.selfDestructComponent.attackTower, attacker.attackerExtraInfo.attackerUnitInfo.typeNumber, attacker.attackerExtraInfo.Gid.getId(), attacker.attackerExtraInfo.attackerPlayerInfo.playerIndex);
                    addSelfDestructEvent.accept(selfDestructEventTower);
                }
            }
            gameObjectDestroy.accept(attacker.selfDestructComponent.gameobject);
            GlobalDeath gdeath = new GlobalDeath(attacker.selfDestructComponent.gameobject, attacker.attackerExtraInfo.attackerBody.position, attacker.attackerExtraInfo.attackerUnitInfo.typeNumber, attacker.attackerExtraInfo.Gid.getId(), attacker.attackerExtraInfo.attackerPlayerInfo.playerIndex, false);
            addDeathEvent.accept(gdeath);
            if (!printHitStrings) continue;
            System.out.println("SELF DESTRUCTING (may not damage)! " + attacker.attackerExtraInfo.Gid.getId());
        }
    }

    public static class SelfDestructAttackerComponents {
        public NavigationComponent nav;
        public SelfDestructComponent selfDestructComponent;
        public ColliderComponent selfDestructCollider;
        public ExtraAttackerInfo attackerExtraInfo;

        public SelfDestructAttackerComponents(NavigationComponent nav, SelfDestructComponent selfDestructComponent, ColliderComponent selfDestructCollider, ExtraAttackerInfo attackerExtraInfo) {
            this.nav = nav;
            this.selfDestructComponent = selfDestructComponent;
            this.selfDestructCollider = selfDestructCollider;
            this.attackerExtraInfo = attackerExtraInfo;
        }

        public static SelfDestructAttackerComponents tryCreateSelfDestructComponents(Gameobject potentialAttacker) {
            ArrayList<ColliderComponent> allColliders = potentialAttacker.getComponents(ColliderComponent.class);
            NavigationComponent nav = potentialAttacker.getComponent(NavigationComponent.class);
            PositionComponent body2 = potentialAttacker.getComponent(PositionComponent.class);
            SelfDestructComponent attack = potentialAttacker.getComponent(SelfDestructComponent.class);
            PlayerInfoComponent attackingPlayer = potentialAttacker.getComponent(PlayerInfoComponent.class);
            UnitInfoComponent attackUnitInfo = potentialAttacker.getComponent(UnitInfoComponent.class);
            UniqueId attackingGid = potentialAttacker.getGid();
            if (nav != null && body2 != null && attack != null && attackingPlayer != null && attackUnitInfo != null) {
                for (ColliderComponent collider : allColliders) {
                    if (collider.collisionType != ColliderComponent.CollisionType.SelfDestruct) continue;
                    return new SelfDestructAttackerComponents(nav, attack, collider, new ExtraAttackerInfo(body2, attackingGid, attackUnitInfo, attackingPlayer));
                }
            }
            return null;
        }

        public static class ExtraAttackerInfo {
            public PositionComponent attackerBody;
            public UniqueId Gid;
            public UnitInfoComponent attackerUnitInfo;
            public PlayerInfoComponent attackerPlayerInfo;

            public ExtraAttackerInfo(PositionComponent attackerBody, UniqueId gid, UnitInfoComponent attackerUnitInfo, PlayerInfoComponent attackerPlayerInfo) {
                this.attackerBody = attackerBody;
                this.Gid = gid;
                this.attackerUnitInfo = attackerUnitInfo;
                this.attackerPlayerInfo = attackerPlayerInfo;
            }
        }
    }

    public static class SelfDestructVictimComponents {
        public HealthComponent targetHealth;
        public UnitInfoComponent unitInfo;
        public ExtraVictimInfo extraInfo;

        public SelfDestructVictimComponents(HealthComponent targetHealth, UnitInfoComponent unitInfo, ExtraVictimInfo extraInfo) {
            this.targetHealth = targetHealth;
            this.unitInfo = unitInfo;
            this.extraInfo = extraInfo;
        }

        public static class ExtraVictimInfo {
            public PositionComponent victimBody;
            public UniqueId victimGid;
            public PlayerInfoComponent victimPlayerInfo;

            public ExtraVictimInfo(PositionComponent victimBody, UniqueId victimGid, PlayerInfoComponent victimPlayerInfo) {
                this.victimBody = victimBody;
                this.victimGid = victimGid;
                this.victimPlayerInfo = victimPlayerInfo;
            }
        }
    }
}

