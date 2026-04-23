/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game;

import com.c1games.terminal.game.GameMain;

public class PlayerStats {
    public int playerID;
    float totalHP;
    private int timeoutDamageTaken = 0;
    private boolean timeoutDeath = false;
    private float metal = 0.0f;
    private float food = 0.0f;
    private float foodSpent = 0.0f;
    private float foodSpoiled = 0.0f;
    private float metalSpent = 0.0f;
    public float foodSurvived = 0.0f;
    public long totalTimeSpent = 0L;
    GameMain gameMain;
    public long softTimeLimit;

    public PlayerStats(GameMain gameMain, int playerID, float startHP, float startMetal, float startFood, long softTimeLimit) {
        this.playerID = playerID;
        this.gameMain = gameMain;
        this.softTimeLimit = softTimeLimit;
        this.totalHP = startHP;
        this.metal = startMetal;
        this.food = startFood;
    }

    public void dealDamage(float damage) {
        this.totalHP -= damage;
        if (this.gameMain.configVariables.printPlayerGetHitStrings) {
            System.out.println("PLAYER " + (this.playerID + 1) + " HIT! NEW HP: " + this.totalHP);
        }
    }

    public void dealTimeDamage(long timeTaken) {
        if (!this.gameMain.configVariables.waitForever && timeTaken > this.softTimeLimit) {
            long diff2 = timeTaken - this.softTimeLimit;
            int dmg = (int)diff2 / 1000;
            if (timeTaken >= this.gameMain.configVariables.waitTimeManual) {
                dmg = (int)(this.gameMain.configVariables.startingHP + 1.0f);
            }
            if ((float)dmg >= this.gameMain.configVariables.startingHP) {
                this.timeoutDeath = true;
            }
            this.dealDamage(dmg);
            this.timeoutDamageTaken += dmg;
            System.out.println("Player" + this.playerID + " punished because took too long. Time (ms): " + timeTaken + " Dmg: " + dmg);
        }
    }

    public void dealCrashDamage(boolean crashed) {
        if (crashed) {
            this.dealDamage(this.gameMain.configVariables.startingHP + 1.0f);
        }
    }

    public void spoilFood(float percentageSpoil) {
        float oldFood = this.food;
        this.food = (1.0f - percentageSpoil) * this.food;
        this.food = this.roundDecimals(this.food);
        this.foodSpoiled += oldFood - this.food;
        this.foodSpoiled = this.roundDecimals(this.foodSpoiled);
    }

    public void addToFood(float food) {
        this.food += food;
        if (food < 0.0f) {
            this.foodSpent -= food;
        }
        this.food = this.roundDecimals(this.food);
    }

    public void addToFood(float food, float maxFood) {
        this.food += food;
        if (food < 0.0f) {
            this.foodSpent -= food;
        }
        if (this.food > maxFood) {
            this.food = maxFood;
        }
        this.food = this.roundDecimals(this.food);
    }

    public void addToMetal(float metal) {
        this.metal += metal;
        if (metal < 0.0f) {
            this.metalSpent -= metal;
        }
        this.metal = this.roundDecimals(this.metal);
    }

    public static PlayerStats getPlayerStatsFromID(GameMain gameMain, int id) {
        if (id == gameMain.p1stats.playerID) {
            return gameMain.p1stats;
        }
        if (id == gameMain.p2stats.playerID) {
            return gameMain.p2stats;
        }
        throw new IllegalArgumentException("invalid id: " + id);
    }

    public boolean playerUseResources(float metal, float food) {
        if (this.getMetal() >= metal && this.getFood() >= food) {
            this.addToMetal(-1.0f * metal);
            this.addToFood(-1.0f * food);
            return true;
        }
        return false;
    }

    public boolean getTimeoutDeath() {
        return this.timeoutDeath;
    }

    public int getTimeoutDamageTaken() {
        return this.timeoutDamageTaken;
    }

    public float getFood() {
        return this.food;
    }

    public float getMetal() {
        return this.metal;
    }

    public float getMetalSpent() {
        return this.metalSpent;
    }

    public float getFoodSpent() {
        return this.foodSpent;
    }

    public float getFoodSpoiled() {
        return this.foodSpoiled;
    }

    public void setFood(float food) {
        this.food = food;
    }

    public void setMetal(float metal) {
        this.metal = metal;
    }

    public float roundDecimals(float inp) {
        return (float)Math.round(inp * 10.0f) / 10.0f;
    }
}

