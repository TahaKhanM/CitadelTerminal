/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.gameobject.components;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.Component;
import java.util.ArrayList;
import java.util.function.Consumer;

public class HealthComponent
extends Component {
    public float maxHP;
    public float currentHP;
    public ArrayList<Shield> shieldHPs;

    public HealthComponent(Game game, Gameobject host, float maxHP, float startHP) {
        this(game, host, maxHP, startHP, true);
    }

    public HealthComponent(Game game, Gameobject host, float maxHP, float startHP, boolean enabled) {
        super(game, host);
        if (startHP <= 0.0f) {
            throw new IllegalArgumentException("Creating a new unit with 0 or negative HP! It won't be destroyed properly");
        }
        this.shieldHPs = new ArrayList();
        this.maxHP = maxHP;
        this.currentHP = startHP;
        this.initFinalizeCallEnable(enabled);
    }

    public float getCurrentHP() {
        return this.currentHP;
    }

    public float getMaxHP() {
        return this.maxHP;
    }

    public float getShieldHP() {
        float total2 = 0.0f;
        for (Shield shield : this.shieldHPs) {
            total2 += shield.shieldHP;
        }
        return total2;
    }

    @Override
    public void onToDestroy() {
        this.shieldHPs.clear();
        this.currentHP = 0.0f;
    }

    public void matchOther(HealthComponent other) {
        this.currentHP = other.currentHP;
        this.shieldHPs.clear();
        this.shieldHPs.addAll(other.shieldHPs);
        this.maxHP = other.getMaxHP();
    }

    public static boolean dealDamageToHealthComponent(HealthComponent victim, float damage, Consumer<Gameobject> gameObjectDestroy) {
        if (damage < 0.0f) {
            System.err.println("Trying to deal negative damage");
            return false;
        }
        float totalShield = victim.getShieldHP();
        if (totalShield > 0.0f) {
            if (totalShield >= damage) {
                victim.damageShield(victim, damage);
            } else {
                victim.shieldHPs.clear();
                float oldHP = victim.currentHP;
                victim.currentHP -= (damage -= totalShield);
                if (victim.currentHP <= 0.001f && oldHP > 0.0f) {
                    gameObjectDestroy.accept(victim.gameobject);
                    return true;
                }
            }
        } else {
            float oldHP = victim.currentHP;
            victim.currentHP -= damage;
            if (victim.currentHP <= 0.001f && oldHP > 0.0f) {
                gameObjectDestroy.accept(victim.gameobject);
                return true;
            }
        }
        return false;
    }

    public static void setHealth(HealthComponent hc, float newHp, Consumer<Gameobject> gameObjectDestroy) {
        hc.currentHP = newHp;
        if (hc.currentHP <= 0.0f) {
            gameObjectDestroy.accept(hc.gameobject);
        }
    }

    public static void AddNewShield(HealthComponent toChange, float shieldAmount, float shieldDecay) {
        if (!(shieldAmount > 0.0f)) {
            throw new IllegalArgumentException("Error creating a new shield with negative or 0 shield amount!");
        }
        toChange.shieldHPs.add(new Shield(shieldAmount, shieldDecay));
    }

    private void damageShield(HealthComponent toChange, float damage) {
        if (damage >= 0.0f) {
            for (int i = 0; i < toChange.shieldHPs.size(); ++i) {
                if (Math.abs(damage) <= toChange.shieldHPs.get((int)i).shieldHP) {
                    float newShield;
                    toChange.shieldHPs.get((int)i).shieldHP = newShield = toChange.shieldHPs.get((int)i).shieldHP - damage;
                    break;
                }
                damage -= toChange.shieldHPs.get((int)i).shieldHP;
                toChange.shieldHPs.get((int)i).shieldHP = 0.0f;
            }
        } else {
            throw new IllegalArgumentException("Error damaging shield with negative amount!");
        }
    }

    public static class Shield {
        public float shieldHP;
        public float shieldDecayPerFrame;

        public Shield(float shieldHP, float shieldDecayPerFrame) {
            this.shieldHP = shieldHP;
            this.shieldDecayPerFrame = shieldDecayPerFrame;
        }
    }
}

