/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems;

import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.UniqueId;
import com.c1games.terminal.game.gameobject.components.HealthComponent;
import com.c1games.terminal.game.gameobject.components.PlayerInfoComponent;
import com.c1games.terminal.game.gameobject.components.PositionComponent;
import com.c1games.terminal.game.gameobject.components.RefundComponent;
import com.c1games.terminal.game.gameobject.components.UnitInfoComponent;
import com.c1games.terminal.game.gameobject.components.UpgradeComponent;
import com.c1games.terminal.game.systems.SpawnUnitsSystem;
import com.c1games.terminal.game.systems.map.MapHelper;
import java.util.ArrayList;

public class DisplayUnitsSystem {
    public static ArrayList<Object[]> getDisplayUnits(int playerPerspective, ArrayList<DisplayUnitSystemComponents> toDisplay, SpawnUnitsSystem.UnitConfig[] unitInfos, int turn, int rmUnitTypeID, int upgradeTypeID) {
        ArrayList<Object[]> visibles = new ArrayList<Object[]>();
        for (DisplayUnitSystemComponents d : toDisplay) {
            Object[] removal;
            if (!d.positionComponent.gameobject.isEnabled() || d.positionComponent.gameobject.getToDestroy()) continue;
            int[] xy = MapHelper.convertUserEncodePerspective(d.positionComponent.position().x, d.positionComponent.position().y, playerPerspective);
            int pnum = 2;
            if (playerPerspective == d.playerInfoComponent.playerIndex) {
                pnum = 1;
            }
            Object[] unit = new Object[]{unitInfos[d.unitInfoComponent.typeNumber].shorthand, xy[0], xy[1], Float.valueOf(d.healthComponent.getCurrentHP() + d.healthComponent.getShieldHP()), pnum, d.gid.getId()};
            visibles.add(unit);
            if (d.optionalRefundComponent != null && d.optionalRefundComponent.turnStartRemoval >= 0) {
                removal = new Object[]{unitInfos[rmUnitTypeID].shorthand, xy[0], xy[1], Float.valueOf(0.0f + (float)d.optionalRefundComponent.turnsRequiredToRemove - (float)(turn - d.optionalRefundComponent.turnStartRemoval)), pnum, d.gid.getId()};
                visibles.add(removal);
            }
            if (d.optionalUpgradeComponent == null || !d.optionalUpgradeComponent.upgraded) continue;
            removal = new Object[]{unitInfos[upgradeTypeID].shorthand, xy[0], xy[1], Float.valueOf(0.0f), pnum, d.gid.getId()};
            visibles.add(removal);
        }
        return visibles;
    }

    public static class DisplayUnitSystemComponents {
        public PositionComponent positionComponent;
        public HealthComponent healthComponent;
        public PlayerInfoComponent playerInfoComponent;
        public UnitInfoComponent unitInfoComponent;
        public RefundComponent optionalRefundComponent;
        public UpgradeComponent optionalUpgradeComponent;
        public UniqueId gid;

        public DisplayUnitSystemComponents(PositionComponent positionComponent, HealthComponent healthComponent, PlayerInfoComponent playerInfoComponent, UnitInfoComponent unitInfoComponent, RefundComponent optionalRefundComponent, UpgradeComponent optionalUpgradeComponent, UniqueId gid) {
            this.positionComponent = positionComponent;
            this.healthComponent = healthComponent;
            this.playerInfoComponent = playerInfoComponent;
            this.unitInfoComponent = unitInfoComponent;
            this.optionalRefundComponent = optionalRefundComponent;
            this.optionalUpgradeComponent = optionalUpgradeComponent;
            this.gid = gid;
        }

        public static DisplayUnitSystemComponents tryCreateDisplayComponents(Gameobject toDisplay) {
            PositionComponent positionComponent = toDisplay.getComponent(PositionComponent.class);
            HealthComponent healthComponent = toDisplay.getComponent(HealthComponent.class);
            PlayerInfoComponent playerInfoComponent = toDisplay.getComponent(PlayerInfoComponent.class);
            UnitInfoComponent unitInfoComponent = toDisplay.getComponent(UnitInfoComponent.class);
            UniqueId gid = toDisplay.getGid();
            if (positionComponent != null && healthComponent != null && playerInfoComponent != null && unitInfoComponent != null) {
                RefundComponent refundComponent = toDisplay.getComponent(RefundComponent.class);
                UpgradeComponent upgradeComponent = toDisplay.getComponent(UpgradeComponent.class);
                return new DisplayUnitSystemComponents(positionComponent, healthComponent, playerInfoComponent, unitInfoComponent, refundComponent, upgradeComponent, gid);
            }
            return null;
        }
    }
}

