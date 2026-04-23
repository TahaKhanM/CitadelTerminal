/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems;

import com.c1games.terminal.game.PlayerStats;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.InvestmentComponent;
import com.c1games.terminal.game.gameobject.components.PlayerInfoComponent;
import java.util.ArrayList;

public class InvestmentSystem {
    public static void investmentProcess(ArrayList<InvestmentSystemComponents> toInvests, PlayerStats p1stats, PlayerStats p2stats) {
        for (InvestmentSystemComponents toInvest : toInvests) {
            PlayerStats myStats = toInvest.toInvestPlayerInfo.playerIndex == 0 ? p1stats : p2stats;
            myStats.addToFood(toInvest.investmentComponent.resource1);
            myStats.addToMetal(toInvest.investmentComponent.resource2);
        }
    }

    public static class InvestmentSystemComponents {
        public PlayerInfoComponent toInvestPlayerInfo;
        public InvestmentComponent investmentComponent;

        public InvestmentSystemComponents(PlayerInfoComponent toInvestPlayerInfo, InvestmentComponent investmentComponent) {
            this.toInvestPlayerInfo = toInvestPlayerInfo;
            this.investmentComponent = investmentComponent;
        }

        public static InvestmentSystemComponents tryCreateInvestmentSystemComponents(Gameobject potentialInvest) {
            PlayerInfoComponent playerInfo = potentialInvest.getComponent(PlayerInfoComponent.class);
            InvestmentComponent investmentComponent = potentialInvest.getComponent(InvestmentComponent.class);
            if (playerInfo != null && investmentComponent != null) {
                return new InvestmentSystemComponents(playerInfo, investmentComponent);
            }
            return null;
        }
    }
}

