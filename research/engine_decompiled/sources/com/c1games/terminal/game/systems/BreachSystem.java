/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems;

import com.c1games.terminal.game.PlayerStats;
import com.c1games.terminal.game.events.globalevents.GlobalBreached;
import com.c1games.terminal.game.events.globalevents.GlobalDeath;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.UniqueId;
import com.c1games.terminal.game.gameobject.components.BreachComponent;
import com.c1games.terminal.game.gameobject.components.NavigationComponent;
import com.c1games.terminal.game.gameobject.components.PlayerInfoComponent;
import com.c1games.terminal.game.gameobject.components.PositionComponent;
import com.c1games.terminal.game.gameobject.components.SpawnComponent;
import com.c1games.terminal.game.gameobject.components.UnitInfoComponent;
import java.util.ArrayList;
import java.util.function.Consumer;

public class BreachSystem {
    public static void breachProcess(ArrayList<BreachSystemComponents> breachers, PlayerStats p1stats, PlayerStats p2stats, Consumer<Gameobject> gameObjectDestroy, Consumer<Gameobject> disableGameObject, Consumer<GlobalDeath> addDeathEvent, Consumer<GlobalBreached> addBreachEvent) {
        for (BreachSystemComponents breacher : breachers) {
            if (!breacher.nav.finishedNavigating || !breacher.nav.reachedTarget) continue;
            PlayerStats enemyPlayer = breacher.breacherPlayerInfo.playerIndex == 0 ? p2stats : p1stats;
            PlayerStats breacherPlayer = breacher.breacherPlayerInfo.playerIndex == 0 ? p1stats : p2stats;
            enemyPlayer.dealDamage(breacher.breachComponent.playerDamage);
            breacherPlayer.addToMetal(breacher.breachComponent.metalForBreach);
            disableGameObject.accept(breacher.breachComponent.gameobject);
            gameObjectDestroy.accept(breacher.breachComponent.gameobject);
            float cost = breacher.spawnComponent.costFood + breacher.spawnComponent.costMetal;
            breacherPlayer.foodSurvived += cost;
            GlobalDeath gdeath = new GlobalDeath(breacher.breachComponent, breacher.extraBreacherInfo.attackerBody.position, breacher.extraBreacherInfo.attackerUnitInfo.typeNumber, breacher.extraBreacherInfo.Gid.toString(), breacher.breacherPlayerInfo.playerIndex, false);
            addDeathEvent.accept(gdeath);
            GlobalBreached gbreach = new GlobalBreached(breacher.breachComponent, breacher.extraBreacherInfo.attackerBody.position, breacher.breachComponent.playerDamage, breacher.extraBreacherInfo.attackerUnitInfo.typeNumber, breacher.extraBreacherInfo.Gid.toString(), breacher.breacherPlayerInfo.playerIndex);
            addBreachEvent.accept(gbreach);
        }
    }

    public static class BreachSystemComponents {
        public NavigationComponent nav;
        public PlayerInfoComponent breacherPlayerInfo;
        public BreachComponent breachComponent;
        public SpawnComponent spawnComponent;
        public ExtraBreacherInfo extraBreacherInfo;

        private BreachSystemComponents(NavigationComponent nav, PlayerInfoComponent breacherPlayerInfo, BreachComponent breachComponent, SpawnComponent spawnComponent, ExtraBreacherInfo extraBreacherInfo) {
            this.nav = nav;
            this.breacherPlayerInfo = breacherPlayerInfo;
            this.breachComponent = breachComponent;
            this.spawnComponent = spawnComponent;
            this.extraBreacherInfo = extraBreacherInfo;
        }

        public static BreachSystemComponents tryCreateBreachSystemComponents(Gameobject potentialBreacher) {
            NavigationComponent nav = potentialBreacher.getComponent(NavigationComponent.class);
            PlayerInfoComponent playerInfo = potentialBreacher.getComponent(PlayerInfoComponent.class);
            BreachComponent breachComponent = potentialBreacher.getComponent(BreachComponent.class);
            SpawnComponent spawnComponent = potentialBreacher.getComponent(SpawnComponent.class);
            PositionComponent body2 = potentialBreacher.getComponent(PositionComponent.class);
            UnitInfoComponent uinfo = potentialBreacher.getComponent(UnitInfoComponent.class);
            if (nav != null && playerInfo != null && breachComponent != null && spawnComponent != null && body2 != null && uinfo != null) {
                return new BreachSystemComponents(nav, playerInfo, breachComponent, spawnComponent, new ExtraBreacherInfo(body2, breachComponent.gameobject.getGid(), uinfo));
            }
            return null;
        }

        public static class ExtraBreacherInfo {
            public PositionComponent attackerBody;
            public UniqueId Gid;
            public UnitInfoComponent attackerUnitInfo;

            public ExtraBreacherInfo(PositionComponent attackerBody, UniqueId gid, UnitInfoComponent attackerUnitInfo) {
                this.attackerBody = attackerBody;
                this.Gid = gid;
                this.attackerUnitInfo = attackerUnitInfo;
            }
        }
    }
}

