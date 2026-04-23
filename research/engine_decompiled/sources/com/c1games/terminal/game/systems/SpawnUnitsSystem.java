/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems;

import com.c1games.terminal.game.Config;
import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.GameMain;
import com.c1games.terminal.game.PlayerStats;
import com.c1games.terminal.game.events.globalevents.GlobalSpawn;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.AttackComponent;
import com.c1games.terminal.game.gameobject.components.BreachComponent;
import com.c1games.terminal.game.gameobject.components.ColliderComponent;
import com.c1games.terminal.game.gameobject.components.HealthComponent;
import com.c1games.terminal.game.gameobject.components.InvestmentComponent;
import com.c1games.terminal.game.gameobject.components.NavigationComponent;
import com.c1games.terminal.game.gameobject.components.PlayerInfoComponent;
import com.c1games.terminal.game.gameobject.components.PositionComponent;
import com.c1games.terminal.game.gameobject.components.RefundComponent;
import com.c1games.terminal.game.gameobject.components.SelfDestructComponent;
import com.c1games.terminal.game.gameobject.components.ShielderComponent;
import com.c1games.terminal.game.gameobject.components.SpawnComponent;
import com.c1games.terminal.game.gameobject.components.UnitInfoComponent;
import com.c1games.terminal.game.gameobject.components.UpgradeComponent;
import com.c1games.terminal.game.systems.map.MapHelper;
import com.c1games.terminal.game.systems.map.path.PathFinder;
import com.c1games.terminal.game.systems.map.structure.Coords;
import com.c1games.terminal.game.systems.proximity.ProximitySensorFactory;
import com.c1games.terminal.game.units.CategoryType;
import com.c1games.terminal.game.units.UnitMask;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class SpawnUnitsSystem {
    public static void parseUnit(GameMain gameMain, Game game, int type, int x, int y, float hp, int perspectiveID, int playerID, boolean checkCost, Consumer<GlobalSpawn> addSpawnEvent, Consumer<Gameobject> destroyGameObject) {
        int[] xy = MapHelper.convertUserEncodePerspective(x, y, perspectiveID);
        Coords loc = new Coords(xy[0], xy[1]);
        if (type == 6) {
            if (gameMain.mapHelper.isOnPlayerSideAndOccupied(loc, playerID)) {
                List<Gameobject> objects = game.map.getObjects(new Coords(xy[0], xy[1]));
                if (objects.size() > 1) {
                    if (gameMain.configVariables.printBotErrors) {
                        System.out.println("More than one unit in square to remove???");
                    }
                    return;
                }
                Gameobject target = objects.get(0);
                if (target.getComponent(RefundComponent.class) != null) {
                    RefundComponent refund = target.getComponent(RefundComponent.class);
                    if (refund.turnStartRemoval >= 0) {
                        if (gameMain.configVariables.printBotErrors) {
                            System.out.println("Invalid location to remove, already removing" + xy[0] + " " + xy[1]);
                        }
                    } else {
                        refund.turnStartRemoval = gameMain.turn;
                        GlobalSpawn gspawn = new GlobalSpawn(refund, loc, type, target.getGid().getId(), playerID);
                        addSpawnEvent.accept(gspawn);
                    }
                } else if (gameMain.configVariables.printBotErrors) {
                    System.out.println("Invalid location to remove, no refund component, not refundable" + xy[0] + " " + xy[1]);
                }
            } else if (gameMain.configVariables.printBotErrors) {
                System.out.println("Invalid location to remove " + xy[0] + " " + xy[1]);
            }
        } else if (type == 7) {
            if (gameMain.mapHelper.isOnPlayerSideAndOccupied(loc, playerID)) {
                List<Gameobject> objects = game.map.getObjects(new Coords(xy[0], xy[1]));
                if (objects.size() > 1) {
                    if (gameMain.configVariables.printBotErrors) {
                        System.out.println("More than one unit in square to upgrade???");
                    }
                    return;
                }
                Gameobject target = objects.get(0);
                UnitInfoComponent uinfo = target.getComponent(UnitInfoComponent.class);
                if (uinfo != null && uinfo.category == CategoryType.TOWER) {
                    UpgradeComponent upinfo = target.getComponent(UpgradeComponent.class);
                    if (upinfo == null || !upinfo.upgraded) {
                        UnitConfig upgradeConfig = new UnitConfig(gameMain.configVariables.UnitInfo[uinfo.typeNumber].upgrade);
                        if (upgradeConfig != null) {
                            HealthComponent oldHealth = target.getComponent(HealthComponent.class);
                            if (oldHealth != null) {
                                float startHP = oldHealth.currentHP;
                                if (checkCost) {
                                    float diff2 = upgradeConfig.maxHealth - gameMain.configVariables.UnitInfo[uinfo.typeNumber].maxHealth;
                                    startHP = diff2 >= 0.0f ? (startHP += diff2) : Math.min(startHP, upgradeConfig.maxHealth);
                                }
                                UnitConfig upgraded = SpawnUnitsSystem.createUnitConfig(gameMain.configVariables, uinfo.typeNumber, loc, startHP, playerID, null, gameMain.mapHelper, true);
                                PlayerStats owner2 = PlayerStats.getPlayerStatsFromID(gameMain, playerID);
                                if (upgraded.foodCost <= owner2.getFood() && upgraded.metalCost <= owner2.getMetal()) {
                                    destroyGameObject.accept(target);
                                    SpawnUnitsSystem.attemptSpawn(game, upgraded, owner2, checkCost, addSpawnEvent, new float[]{gameMain.configVariables.UnitInfo[uinfo.typeNumber].metalCost, gameMain.configVariables.UnitInfo[uinfo.typeNumber].foodCost});
                                } else if (gameMain.configVariables.printBotErrors) {
                                    System.out.println("Invalid location to upgrade, not enough resources " + xy[0] + " " + xy[1]);
                                }
                            } else if (gameMain.configVariables.printBotErrors) {
                                System.out.println("Invalid location to upgrade, unit has no health component!? " + xy[0] + " " + xy[1]);
                            }
                        } else if (gameMain.configVariables.printBotErrors) {
                            System.out.println("Invalid location to upgrade, unit has no upgrade configured " + xy[0] + " " + xy[1]);
                        }
                    } else if (gameMain.configVariables.printBotErrors) {
                        System.out.println("Invalid location to upgrade, unit already upgraded " + xy[0] + " " + xy[1]);
                    }
                } else if (gameMain.configVariables.printBotErrors) {
                    System.out.println("Invalid location to upgrade, no unit info available " + xy[0] + " " + xy[1]);
                }
            } else if (gameMain.configVariables.printBotErrors) {
                System.out.println("Invalid location to upgrade " + xy[0] + " " + xy[1]);
            }
        } else if (game.bounds.inArena(loc) && gameMain.mapHelper.isOnPlayerSideAndUnoccupiedLocation(loc, playerID)) {
            UnitConfig unit = SpawnUnitsSystem.createUnitConfig(gameMain.configVariables, type, loc, hp, playerID, game.getPathFinderFromInitialPosition(loc), gameMain.mapHelper, false);
            if (unit != null) {
                SpawnUnitsSystem.attemptSpawn(game, unit, PlayerStats.getPlayerStatsFromID(gameMain, playerID), checkCost, addSpawnEvent);
            }
        } else if (gameMain.configVariables.printBotErrors) {
            System.out.println("Invalid location " + xy[0] + " " + xy[1]);
        }
    }

    private static UnitConfig createUnitConfig(Config.ConfigVariables config, int type, Coords position, float startHp, int playerID, PathFinder pathFinder, MapHelper mapHelper, boolean upgraded) {
        UnitConfig unit = upgraded ? new UnitConfig(config.UnitInfo[type].upgrade) : new UnitConfig(config.UnitInfo[type]);
        unit.startLocation = position;
        unit.startHealth = startHp >= 0.0f ? startHp : unit.maxHealth;
        unit.playerIndex = playerID;
        if (unit.unitCategory == CategoryType.WALKER) {
            unit.navigationTargetLocations = mapHelper.checkIfOnEdgeAndReturnOpposite(position, playerID);
            unit.pathFinder = pathFinder;
            if (unit.navigationTargetLocations == null) {
                if (config.printBotErrors) {
                    System.out.println("Invalid location, not on edge " + position.x + " " + position.y);
                }
                return null;
            }
        }
        return unit;
    }

    private static void attemptSpawn(Game game, UnitConfig toSpawn, PlayerStats player, boolean checkCosts, Consumer<GlobalSpawn> addSpawnEvent) {
        SpawnUnitsSystem.attemptSpawn(game, toSpawn, player, checkCosts, addSpawnEvent, null);
    }

    private static void attemptSpawn(Game game, UnitConfig toSpawn, PlayerStats player, boolean checkCosts, Consumer<GlobalSpawn> addSpawnEvent, float[] costsBase) {
        if (!checkCosts || player.playerUseResources(toSpawn.metalCost, toSpawn.foodCost)) {
            Gameobject newUnit = new Gameobject(game, null, true, true);
            if (toSpawn.startLocation != null) {
                ProximitySensorFactory sensorGen;
                UnitMask identity;
                new UnitInfoComponent(game, newUnit, toSpawn.unitCategory, toSpawn.unitType);
                new PositionComponent(game, newUnit, toSpawn.startLocation);
                new PlayerInfoComponent(game, newUnit, toSpawn.playerIndex);
                float totalMetal = costsBase != null ? toSpawn.metalCost + costsBase[0] : toSpawn.metalCost;
                float totalFood = costsBase != null ? toSpawn.foodCost + costsBase[1] : toSpawn.foodCost;
                new SpawnComponent(game, newUnit, toSpawn.startLocation, totalMetal, totalFood);
                if (!toSpawn.upgraded) {
                    addSpawnEvent.accept(new GlobalSpawn(newUnit, newUnit.getComponent(PositionComponent.class).position, newUnit.getComponent(UnitInfoComponent.class).typeNumber, newUnit.getGid().getId(), newUnit.getComponent(PlayerInfoComponent.class).playerIndex));
                } else {
                    addSpawnEvent.accept(new GlobalSpawn(newUnit, newUnit.getComponent(PositionComponent.class).position, 7, newUnit.getGid().getId(), newUnit.getComponent(PlayerInfoComponent.class).playerIndex));
                }
                if (toSpawn.startHealth > 0.0f) {
                    List<UnitMask> getsHitBy;
                    new HealthComponent(game, newUnit, toSpawn.maxHealth, toSpawn.startHealth);
                    if (toSpawn.playerIndex == 0) {
                        identity = toSpawn.unitCategory == CategoryType.TOWER ? UnitMask.P1TowersGetHit : UnitMask.P1WalkersGetHit;
                        getsHitBy = List.of(UnitMask.TargetBothOfP1sUnits, toSpawn.unitCategory == CategoryType.TOWER ? UnitMask.TargetP1sTowers : UnitMask.TargetP1sWalkers);
                        new ColliderComponent(game, newUnit, toSpawn.startLocation, new ProximitySensorFactory(toSpawn.getHitRadius, identity, getsHitBy), ColliderComponent.CollisionType.GetHit, true);
                    } else {
                        identity = toSpawn.unitCategory == CategoryType.TOWER ? UnitMask.P2TowersGetHit : UnitMask.P2WalkersGetHit;
                        getsHitBy = List.of(UnitMask.TargetBothOfP2sUnits, toSpawn.unitCategory == CategoryType.TOWER ? UnitMask.TargetP2sTowers : UnitMask.TargetP2sWalkers);
                        new ColliderComponent(game, newUnit, toSpawn.startLocation, new ProximitySensorFactory(toSpawn.getHitRadius, identity, getsHitBy), ColliderComponent.CollisionType.GetHit, true);
                    }
                }
                if (toSpawn.attackRange > 0.0f) {
                    if (toSpawn.attackDamageTower > 0.0f && toSpawn.attackDamageWalker > 0.0f) {
                        identity = toSpawn.playerIndex == 0 ? UnitMask.TargetBothOfP2sUnits : UnitMask.TargetBothOfP1sUnits;
                        sensorGen = new ProximitySensorFactory(toSpawn.attackRange, identity, List.of());
                        new ColliderComponent(game, newUnit, toSpawn.startLocation, sensorGen, ColliderComponent.CollisionType.Attack, true);
                    } else if (toSpawn.attackDamageTower > 0.0f) {
                        identity = toSpawn.playerIndex == 0 ? UnitMask.TargetP2sTowers : UnitMask.TargetP1sTowers;
                        sensorGen = new ProximitySensorFactory(toSpawn.attackRange, identity, List.of());
                        new ColliderComponent(game, newUnit, toSpawn.startLocation, sensorGen, ColliderComponent.CollisionType.Attack, true);
                    } else if (toSpawn.attackDamageWalker > 0.0f) {
                        identity = toSpawn.playerIndex == 0 ? UnitMask.TargetP2sWalkers : UnitMask.TargetP1sWalkers;
                        sensorGen = new ProximitySensorFactory(toSpawn.attackRange, identity, List.of());
                        new ColliderComponent(game, newUnit, toSpawn.startLocation, sensorGen, ColliderComponent.CollisionType.Attack, true);
                    }
                    if (toSpawn.attackDamageWalker + toSpawn.attackDamageTower > 0.0f) {
                        new AttackComponent(game, newUnit, toSpawn.attackDamageTower, toSpawn.attackDamageWalker);
                    }
                }
                if (toSpawn.shieldRange > 0.0f && toSpawn.shieldPerUnit + toSpawn.shieldBonusPerY > 0.0f) {
                    identity = toSpawn.playerIndex == 0 ? UnitMask.TargetP1sWalkers : UnitMask.TargetP2sWalkers;
                    sensorGen = new ProximitySensorFactory(toSpawn.shieldRange, identity, List.of());
                    new ColliderComponent(game, newUnit, toSpawn.startLocation, sensorGen, ColliderComponent.CollisionType.Shield, true);
                    new ShielderComponent(game, newUnit, toSpawn.shieldPerUnit, toSpawn.shieldDecay, toSpawn.shieldBonusPerY);
                }
                if (toSpawn.speed > 0.0f && toSpawn.navigationTargetLocations != null && toSpawn.pathFinder != null) {
                    new BreachComponent(game, newUnit, toSpawn.playerBreachDamage, toSpawn.metalForBreach);
                    new NavigationComponent(game, newUnit, toSpawn.pathFinder, toSpawn.speed, toSpawn.navigationTargetLocations);
                }
                if (toSpawn.generatesResource1 > 0.0f || toSpawn.generatesResource2 > 0.0f) {
                    new InvestmentComponent(game, newUnit, toSpawn.generatesResource1, toSpawn.generatesResource2);
                }
                if (toSpawn.selfDestructRange > 0.0f && toSpawn.selfDestructDamageTower + toSpawn.selfDestructDamageWalker > 0.0f) {
                    if (toSpawn.selfDestructDamageTower > 0.0f && toSpawn.selfDestructDamageTower > 0.0f) {
                        identity = toSpawn.playerIndex == 0 ? UnitMask.TargetBothOfP2sUnits : UnitMask.TargetBothOfP1sUnits;
                        sensorGen = new ProximitySensorFactory(toSpawn.selfDestructRange, identity, List.of());
                        new ColliderComponent(game, newUnit, toSpawn.startLocation, sensorGen, ColliderComponent.CollisionType.SelfDestruct, true);
                    } else if (toSpawn.selfDestructDamageTower > 0.0f) {
                        identity = toSpawn.playerIndex == 0 ? UnitMask.TargetP2sTowers : UnitMask.TargetP1sTowers;
                        sensorGen = new ProximitySensorFactory(toSpawn.selfDestructRange, identity, List.of());
                        new ColliderComponent(game, newUnit, toSpawn.startLocation, sensorGen, ColliderComponent.CollisionType.SelfDestruct, true);
                    } else if (toSpawn.selfDestructDamageWalker > 0.0f) {
                        identity = toSpawn.playerIndex == 0 ? UnitMask.TargetP2sWalkers : UnitMask.TargetP1sWalkers;
                        sensorGen = new ProximitySensorFactory(toSpawn.selfDestructRange, identity, List.of());
                        new ColliderComponent(game, newUnit, toSpawn.startLocation, sensorGen, ColliderComponent.CollisionType.SelfDestruct, true);
                    }
                    new SelfDestructComponent(game, newUnit, toSpawn.selfDestructDamageTower, toSpawn.selfDestructDamageWalker, toSpawn.selfDestructStepsRequired);
                }
                if (toSpawn.refundPercentage > 0.0f && toSpawn.turnsRequiredToRemove >= 0) {
                    if (toSpawn.unitCategory != CategoryType.TOWER) {
                        System.err.println("ERROR! Trying to add refund to info unit. Won't add refund, check config!");
                    } else {
                        new RefundComponent(game, newUnit, toSpawn.turnsRequiredToRemove, -1, toSpawn.refundPercentage);
                    }
                }
                if (toSpawn.upgraded) {
                    new UpgradeComponent(game, newUnit, true);
                }
            }
        }
    }

    public static class UnitConfig {
        public float metalCost = 0.0f;
        public float foodCost = 0.0f;
        public Coords startLocation = null;
        public float maxHealth = 0.0f;
        public float startHealth = 0.0f;
        public float getHitRadius = 0.0f;
        public float attackDamageWalker = 0.0f;
        public float attackDamageTower = 0.0f;
        public float attackRange = 0.0f;
        public float selfDestructDamageWalker = 0.0f;
        public float selfDestructDamageTower = 0.0f;
        public float selfDestructRange = 0.0f;
        public int selfDestructStepsRequired = 0;
        public float shieldRange = 0.0f;
        public float shieldPerUnit = 0.0f;
        public float shieldDecay = 0.0f;
        public float shieldBonusPerY = 0.0f;
        public float speed = 0.0f;
        List<Coords> navigationTargetLocations = null;
        public PathFinder pathFinder = null;
        public float playerBreachDamage = 0.0f;
        public float metalForBreach = 0.0f;
        public int turnsRequiredToRemove = -1;
        public float refundPercentage = 0.0f;
        public int unitType = -1;
        public CategoryType unitCategory = null;
        public int playerIndex = 0;
        public String shorthand = "XX";
        public String display = "Sniper";
        public float generatesResource1 = 0.0f;
        public float generatesResource2 = 0.0f;
        public String icon = "S3_ping";
        public float iconxScale = 0.5f;
        public float iconyScale = 0.5f;
        public UnitConfig upgrade = null;
        public boolean upgraded = false;

        public UnitConfig() {
        }

        public UnitConfig(UnitConfig copyFrom) {
            this.icon = copyFrom.icon;
            this.iconxScale = copyFrom.iconxScale;
            this.iconyScale = copyFrom.iconyScale;
            this.metalCost = copyFrom.metalCost;
            this.foodCost = copyFrom.foodCost;
            this.startLocation = copyFrom.startLocation;
            this.maxHealth = copyFrom.maxHealth;
            this.startHealth = copyFrom.startHealth;
            this.getHitRadius = copyFrom.getHitRadius;
            this.attackDamageWalker = copyFrom.attackDamageWalker;
            this.attackDamageTower = copyFrom.attackDamageTower;
            this.attackRange = copyFrom.attackRange;
            this.selfDestructDamageWalker = copyFrom.selfDestructDamageWalker;
            this.selfDestructDamageTower = copyFrom.selfDestructDamageTower;
            this.selfDestructRange = copyFrom.selfDestructRange;
            this.selfDestructStepsRequired = copyFrom.selfDestructStepsRequired;
            this.shieldRange = copyFrom.shieldRange;
            this.shieldPerUnit = copyFrom.shieldPerUnit;
            this.shieldDecay = copyFrom.shieldDecay;
            this.shieldBonusPerY = copyFrom.shieldBonusPerY;
            this.speed = copyFrom.speed;
            this.navigationTargetLocations = copyFrom.navigationTargetLocations;
            this.pathFinder = copyFrom.pathFinder;
            this.playerBreachDamage = copyFrom.playerBreachDamage;
            this.metalForBreach = copyFrom.metalForBreach;
            this.turnsRequiredToRemove = copyFrom.turnsRequiredToRemove;
            this.generatesResource1 = copyFrom.generatesResource1;
            this.generatesResource2 = copyFrom.generatesResource2;
            this.refundPercentage = copyFrom.refundPercentage;
            this.unitType = copyFrom.unitType;
            this.unitCategory = copyFrom.unitCategory;
            this.playerIndex = copyFrom.playerIndex;
            this.shorthand = copyFrom.shorthand;
            this.display = copyFrom.display;
            this.upgrade = copyFrom.upgrade;
            this.upgraded = copyFrom.upgraded;
        }

        public UnitConfig(UnitConfig base, UnitConfig upgraded) {
            this.icon = base.icon;
            this.iconxScale = base.iconxScale;
            this.iconyScale = base.iconyScale;
            this.metalCost = base.metalCost - upgraded.metalCost != 0.0f ? upgraded.metalCost : 0.0f;
            this.foodCost = base.foodCost - upgraded.foodCost != 0.0f ? upgraded.foodCost : 0.0f;
            this.startLocation = null;
            this.maxHealth = base.maxHealth - upgraded.maxHealth != 0.0f ? upgraded.maxHealth : 0.0f;
            this.startHealth = base.startHealth - upgraded.startHealth != 0.0f ? upgraded.startHealth : 0.0f;
            this.getHitRadius = base.getHitRadius - upgraded.getHitRadius != 0.0f ? upgraded.getHitRadius : 0.0f;
            this.attackDamageWalker = base.attackDamageWalker - upgraded.attackDamageWalker != 0.0f ? upgraded.attackDamageWalker : 0.0f;
            this.attackDamageTower = base.attackDamageTower - upgraded.attackDamageTower != 0.0f ? upgraded.attackDamageTower : 0.0f;
            this.attackRange = base.attackRange - upgraded.attackRange != 0.0f ? upgraded.attackRange : 0.0f;
            this.selfDestructDamageWalker = base.selfDestructDamageWalker - upgraded.selfDestructDamageWalker != 0.0f ? upgraded.selfDestructDamageWalker : 0.0f;
            this.selfDestructDamageTower = base.selfDestructDamageTower - upgraded.selfDestructDamageTower != 0.0f ? upgraded.selfDestructDamageTower : 0.0f;
            this.selfDestructRange = base.selfDestructRange - upgraded.selfDestructRange != 0.0f ? upgraded.selfDestructRange : 0.0f;
            this.selfDestructStepsRequired = base.selfDestructStepsRequired - upgraded.selfDestructStepsRequired != 0 ? upgraded.selfDestructStepsRequired : 0;
            this.shieldRange = base.shieldRange - upgraded.shieldRange != 0.0f ? upgraded.shieldRange : 0.0f;
            this.shieldPerUnit = base.shieldPerUnit - upgraded.shieldPerUnit != 0.0f ? upgraded.shieldPerUnit : 0.0f;
            this.shieldDecay = base.shieldDecay - upgraded.shieldDecay != 0.0f ? upgraded.shieldDecay : 0.0f;
            this.shieldBonusPerY = base.shieldBonusPerY - upgraded.shieldBonusPerY != 0.0f ? upgraded.shieldBonusPerY : 0.0f;
            this.speed = base.speed - upgraded.speed != 0.0f ? upgraded.speed : 0.0f;
            this.navigationTargetLocations = null;
            this.pathFinder = null;
            this.playerBreachDamage = base.playerBreachDamage - upgraded.playerBreachDamage != 0.0f ? upgraded.playerBreachDamage : 0.0f;
            this.metalForBreach = base.metalForBreach - upgraded.metalForBreach != 0.0f ? upgraded.metalForBreach : 0.0f;
            this.turnsRequiredToRemove = base.turnsRequiredToRemove - upgraded.turnsRequiredToRemove != 0 ? upgraded.turnsRequiredToRemove : 0;
            this.generatesResource1 = base.generatesResource1 - upgraded.generatesResource1 != 0.0f ? upgraded.generatesResource1 : 0.0f;
            this.generatesResource2 = base.generatesResource2 - upgraded.generatesResource2 != 0.0f ? upgraded.generatesResource2 : 0.0f;
            this.refundPercentage = base.refundPercentage - upgraded.refundPercentage != 0.0f ? upgraded.refundPercentage : 0.0f;
            this.unitType = base.unitType - upgraded.unitType != 0 ? upgraded.unitType : -1;
            this.unitCategory = base.unitCategory == upgraded.unitCategory ? null : upgraded.unitCategory;
            this.playerIndex = base.playerIndex - upgraded.playerIndex != 0 ? upgraded.playerIndex : 0;
            this.shorthand = base.shorthand.equals(upgraded.shorthand) ? null : upgraded.shorthand;
            this.display = base.display.equals(upgraded.display) ? null : upgraded.display;
            this.upgrade = null;
            this.upgraded = upgraded.upgraded;
        }

        public static HashMap<String, Object> serializeUnitConfig(UnitConfig unitConfig) {
            return UnitConfig.serializeUnitConfig(unitConfig, false);
        }

        private static HashMap<String, Object> serializeUnitConfig(UnitConfig unitConfig, boolean upgrade) {
            HashMap<String, Object> ret = new HashMap<String, Object>();
            ret.put("icon", unitConfig.icon);
            ret.put("iconxScale", Float.valueOf(unitConfig.iconxScale));
            ret.put("iconyScale", Float.valueOf(unitConfig.iconyScale));
            if (unitConfig.metalCost != 0.0f) {
                ret.put("cost1", Float.valueOf(unitConfig.metalCost));
            }
            if (unitConfig.foodCost != 0.0f) {
                ret.put("cost2", Float.valueOf(unitConfig.foodCost));
            }
            if (unitConfig.maxHealth != 0.0f) {
                ret.put("startHealth", Float.valueOf(unitConfig.maxHealth));
            }
            if (unitConfig.getHitRadius != 0.0f) {
                ret.put("getHitRadius", Float.valueOf(unitConfig.getHitRadius));
            }
            if (!(unitConfig.attackRange == 0.0f && !upgrade || upgrade && unitConfig.attackDamageWalker == 0.0f)) {
                ret.put("attackDamageWalker", Float.valueOf(unitConfig.attackDamageWalker));
            }
            if (!(unitConfig.attackRange == 0.0f && !upgrade || upgrade && unitConfig.attackDamageTower == 0.0f)) {
                ret.put("attackDamageTower", Float.valueOf(unitConfig.attackDamageTower));
            }
            if (unitConfig.attackRange != 0.0f) {
                ret.put("attackRange", Float.valueOf(unitConfig.attackRange));
            }
            if (!(unitConfig.selfDestructRange == 0.0f && !upgrade || upgrade && unitConfig.selfDestructDamageTower == 0.0f)) {
                ret.put("selfDestructDamageTower", Float.valueOf(unitConfig.selfDestructDamageTower));
            }
            if (!(unitConfig.selfDestructRange == 0.0f && !upgrade || upgrade && unitConfig.selfDestructDamageWalker == 0.0f)) {
                ret.put("selfDestructDamageWalker", Float.valueOf(unitConfig.selfDestructDamageWalker));
            }
            if (unitConfig.selfDestructRange != 0.0f) {
                ret.put("selfDestructRange", Float.valueOf(unitConfig.selfDestructRange));
            }
            if (!(unitConfig.selfDestructRange == 0.0f && !upgrade || upgrade && unitConfig.selfDestructStepsRequired == 0)) {
                ret.put("selfDestructStepsRequired", unitConfig.selfDestructStepsRequired);
            }
            if (unitConfig.shieldRange != 0.0f) {
                ret.put("shieldRange", Float.valueOf(unitConfig.shieldRange));
            }
            if (!(unitConfig.shieldRange == 0.0f && !upgrade || upgrade && unitConfig.shieldPerUnit == 0.0f)) {
                ret.put("shieldPerUnit", Float.valueOf(unitConfig.shieldPerUnit));
            }
            if (!(unitConfig.shieldRange == 0.0f && !upgrade || upgrade && unitConfig.shieldDecay == 0.0f)) {
                ret.put("shieldDecay", Float.valueOf(unitConfig.shieldDecay));
            }
            if (!(unitConfig.shieldRange == 0.0f && !upgrade || upgrade && unitConfig.shieldBonusPerY == 0.0f)) {
                ret.put("shieldBonusPerY", Float.valueOf(unitConfig.shieldBonusPerY));
            }
            if (unitConfig.unitCategory != null) {
                ret.put("unitCategory", unitConfig.unitCategory == CategoryType.TOWER ? 0 : 1);
            }
            if (!(unitConfig.unitCategory != CategoryType.WALKER && !upgrade || upgrade && unitConfig.speed == 0.0f)) {
                ret.put("speed", Float.valueOf(unitConfig.speed));
            }
            if (!(unitConfig.unitCategory != CategoryType.WALKER && !upgrade || upgrade && unitConfig.playerBreachDamage == 0.0f)) {
                ret.put("playerBreachDamage", Float.valueOf(unitConfig.playerBreachDamage));
            }
            if (!(unitConfig.unitCategory != CategoryType.WALKER && !upgrade || upgrade && unitConfig.metalForBreach == 0.0f)) {
                ret.put("metalForBreach", Float.valueOf(unitConfig.metalForBreach));
            }
            if (unitConfig.refundPercentage != 0.0f) {
                ret.put("refundPercentage", Float.valueOf(unitConfig.refundPercentage));
            }
            if (!(unitConfig.refundPercentage == 0.0f && !upgrade || upgrade && unitConfig.turnsRequiredToRemove == 0)) {
                ret.put("turnsRequiredToRemove", unitConfig.turnsRequiredToRemove);
            }
            if (unitConfig.generatesResource1 != 0.0f) {
                ret.put("generatesResource1", Float.valueOf(unitConfig.generatesResource1));
            }
            if (unitConfig.generatesResource2 != 0.0f) {
                ret.put("generatesResource2", Float.valueOf(unitConfig.generatesResource2));
            }
            if (unitConfig.upgrade != null) {
                ret.put("upgrade", UnitConfig.serializeUnitConfig(new UnitConfig(unitConfig, unitConfig.upgrade), true));
            }
            if (unitConfig.shorthand != null) {
                ret.put("shorthand", unitConfig.shorthand);
            }
            if (unitConfig.display != null) {
                ret.put("display", unitConfig.display);
            }
            return ret;
        }
    }
}

