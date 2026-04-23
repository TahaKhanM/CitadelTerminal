/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game;

import com.c1games.terminal.game.GameMain;
import com.c1games.terminal.game.systems.SpawnUnitsSystem;
import com.c1games.terminal.game.units.CategoryType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Config {
    public static void useConfigFile(GameMain gameMain) throws IOException {
        String path = String.valueOf(new File("").getAbsoluteFile()) + "/game-configs.json";
        System.out.println("Looking for Config file at: \n" + path);
        File f = new File(path);
        Gson gson = new Gson();
        if (f.exists() && !f.isDirectory()) {
            String fileRead = Config.readFile(path, Charset.defaultCharset());
            try {
                Map settings = (Map)gson.fromJson(fileRead, new TypeToken<Map<String, Object>>(){}.getType());
                Config.loadSettings(gameMain.configVariables, settings, gameMain.workerMode, 8);
            }
            catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
                System.out.println("Couldn't properly deserialize towerConfig.Config, check format. Using defaults for unread values instead and overwriting other settings");
                Config.saveToFileAndInMain(gameMain, path, gson);
            }
        } else {
            System.out.println("No Config file found, using default values and making file");
            Config.saveToFileAndInMain(gameMain, path, gson);
        }
    }

    protected static void saveToFileAndInMain(GameMain gameMain, String path, Gson gson) throws IOException {
        gameMain.configVariables = new ConfigVariables(8);
        HashMap<String, Object> settings = Config.getDefaultSettingsPostSeason5(gameMain.configVariables, 8, 6, 7);
        Path thepath = Paths.get(path, new String[0]);
        String jsonv = gson.toJson(settings);
        ArrayList<String> lines2 = new ArrayList<String>();
        lines2.add(jsonv);
        System.out.println("Saving Config to: " + String.valueOf(thepath));
        File f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            Files.write(thepath, lines2, Charset.defaultCharset(), StandardOpenOption.TRUNCATE_EXISTING);
        } else {
            Files.write(thepath, lines2, Charset.defaultCharset(), StandardOpenOption.CREATE);
        }
    }

    public static void loadSettingsFromString(GameMain gameMain, String config) {
        Gson gson = new Gson();
        try {
            Map settings = (Map)gson.fromJson(config, new TypeToken<Map<String, Object>>(){}.getType());
            Config.loadSettings(gameMain.configVariables, settings, gameMain.workerMode, 8);
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.out.println("Couldn't properly deserialize Config string, check format. Using defaults for unread values instead and overwriting other settings");
        }
    }

    public static void loadSettingsFromObject(GameMain gameMain, Map<String, Object> settings) {
        Gson gson = new Gson();
        try {
            Config.loadSettings(gameMain.configVariables, settings, gameMain.workerMode, 8);
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.out.println("Couldn't properly deserialize Config string, check format. Using defaults for unread values instead and overwriting other settings");
        }
    }

    public static Map<String, Object>[] loadTypeMap(GameMain gameMain, Map<String, Object>[] unitInfo) {
        Map[] ret = new Map[8];
        for (int i = 0; i < ret.length; ++i) {
            HashMap<String, Object> unitTypeDef = new HashMap<String, Object>();
            unitTypeDef.put("shorthand", unitInfo[i].get("shorthand"));
            unitTypeDef.put("display", unitInfo[i].get("display"));
            ret[i] = unitTypeDef;
        }
        return ret;
    }

    public static SpawnUnitsSystem.UnitConfig processUnitInfoInPlace(Map<String, Object> unitDict, int unitType, SpawnUnitsSystem.UnitConfig unitConfig, boolean upgrade) {
        unitConfig.unitType = unitType;
        if (unitDict.containsKey("cost1")) {
            unitConfig.metalCost = ((Double)unitDict.get("cost1")).floatValue();
        }
        if (unitDict.containsKey("cost2")) {
            unitConfig.foodCost = ((Double)unitDict.get("cost2")).floatValue();
        }
        if (unitDict.containsKey("startHealth")) {
            unitConfig.startHealth = ((Double)unitDict.get("startHealth")).floatValue();
            unitConfig.maxHealth = ((Double)unitDict.get("startHealth")).floatValue();
        }
        if (unitDict.containsKey("getHitRadius")) {
            unitConfig.getHitRadius = ((Double)unitDict.get("getHitRadius")).floatValue();
        }
        if (unitDict.containsKey("attackRange")) {
            unitConfig.attackRange = ((Double)unitDict.get("attackRange")).floatValue();
        }
        if (unitDict.containsKey("attackDamageTower")) {
            unitConfig.attackDamageTower = ((Double)unitDict.get("attackDamageTower")).floatValue();
        }
        if (unitDict.containsKey("attackDamageWalker")) {
            unitConfig.attackDamageWalker = ((Double)unitDict.get("attackDamageWalker")).floatValue();
        }
        if (unitDict.containsKey("shieldRange")) {
            unitConfig.shieldRange = ((Double)unitDict.get("shieldRange")).floatValue();
        }
        if (unitDict.containsKey("shieldPerUnit")) {
            unitConfig.shieldPerUnit = ((Double)unitDict.get("shieldPerUnit")).floatValue();
        }
        if (unitDict.containsKey("shieldDecay")) {
            unitConfig.shieldDecay = ((Double)unitDict.get("shieldDecay")).floatValue();
        }
        if (unitDict.containsKey("shieldBonusPerY")) {
            unitConfig.shieldBonusPerY = ((Double)unitDict.get("shieldBonusPerY")).floatValue();
        }
        if (unitDict.containsKey("speed")) {
            unitConfig.speed = ((Double)unitDict.get("speed")).floatValue();
        }
        if (unitDict.containsKey("playerBreachDamage")) {
            unitConfig.playerBreachDamage = ((Double)unitDict.get("playerBreachDamage")).floatValue();
        }
        if (unitDict.containsKey("metalForBreach")) {
            unitConfig.metalForBreach = ((Double)unitDict.get("metalForBreach")).floatValue();
        }
        if (unitDict.containsKey("selfDestructDamageWalker")) {
            unitConfig.selfDestructDamageWalker = ((Double)unitDict.get("selfDestructDamageWalker")).floatValue();
        }
        if (unitDict.containsKey("selfDestructDamageTower")) {
            unitConfig.selfDestructDamageTower = ((Double)unitDict.get("selfDestructDamageTower")).floatValue();
        }
        if (unitDict.containsKey("selfDestructRange")) {
            unitConfig.selfDestructRange = ((Double)unitDict.get("selfDestructRange")).floatValue();
        }
        if (unitDict.containsKey("selfDestructStepsRequired")) {
            unitConfig.selfDestructStepsRequired = ((Double)unitDict.get("selfDestructStepsRequired")).intValue();
        }
        if (unitDict.containsKey("refundPercentage")) {
            unitConfig.refundPercentage = ((Double)unitDict.get("refundPercentage")).floatValue();
        }
        if (unitDict.containsKey("turnsRequiredToRemove")) {
            unitConfig.turnsRequiredToRemove = ((Double)unitDict.get("turnsRequiredToRemove")).intValue();
        }
        if (unitDict.containsKey("generatesResource1")) {
            unitConfig.generatesResource1 = ((Double)unitDict.get("generatesResource1")).floatValue();
        }
        if (unitDict.containsKey("generatesResource2")) {
            unitConfig.generatesResource2 = ((Double)unitDict.get("generatesResource2")).floatValue();
        }
        if (unitDict.containsKey("icon")) {
            unitConfig.icon = unitDict.get("icon").toString();
        }
        if (unitDict.containsKey("iconxScale")) {
            unitConfig.iconxScale = ((Double)unitDict.get("iconxScale")).floatValue();
        }
        if (unitDict.containsKey("iconyScale")) {
            unitConfig.iconxScale = ((Double)unitDict.get("iconyScale")).floatValue();
        }
        if (!upgrade) {
            unitConfig.display = unitDict.get("display").toString();
            unitConfig.shorthand = unitDict.get("shorthand").toString();
        }
        if (!upgrade && unitType != 6 && unitType != 7 && unitDict.containsKey("unitCategory")) {
            CategoryType categoryType = unitConfig.unitCategory = ((Double)unitDict.get("unitCategory")).intValue() == 0 ? CategoryType.TOWER : CategoryType.WALKER;
        }
        if (unitDict.containsKey("upgrade")) {
            SpawnUnitsSystem.UnitConfig upgraded;
            SpawnUnitsSystem.UnitConfig copy2 = new SpawnUnitsSystem.UnitConfig(unitConfig);
            copy2.upgrade = null;
            copy2.upgraded = true;
            unitConfig.upgrade = upgraded = Config.processUnitInfoInPlace((Map)unitDict.get("upgrade"), unitType, copy2, true);
        }
        return unitConfig;
    }

    public static SpawnUnitsSystem.UnitConfig processUnitInfo(Map<String, Object> unitDict, int unitType, SpawnUnitsSystem.UnitConfig unitConfig) {
        return Config.processUnitInfoInPlace(unitDict, unitType, unitConfig, false);
    }

    public static SpawnUnitsSystem.UnitConfig processUnitInfoOld(Map<String, Object> unitDict, int unitType, SpawnUnitsSystem.UnitConfig unitConfig) {
        if (unitDict.containsKey("cost")) {
            if (unitType < 3) {
                unitConfig.metalCost = ((Double)unitDict.get("cost")).floatValue();
            } else {
                unitConfig.foodCost = ((Double)unitDict.get("cost")).floatValue();
            }
        }
        if (unitDict.containsKey("stability")) {
            unitConfig.startHealth = ((Double)unitDict.get("stability")).floatValue();
            unitConfig.maxHealth = ((Double)unitDict.get("stability")).floatValue();
            unitConfig.selfDestructDamageWalker = ((Double)unitDict.get("stability")).floatValue();
            unitConfig.selfDestructDamageTower = ((Double)unitDict.get("stability")).floatValue();
        }
        if (unitDict.containsKey("getHitRadius")) {
            unitConfig.getHitRadius = ((Double)unitDict.get("getHitRadius")).floatValue();
        }
        if (unitDict.containsKey("range")) {
            if (unitType == 1) {
                unitConfig.shieldRange = ((Double)unitDict.get("range")).floatValue();
            } else {
                unitConfig.attackRange = ((Double)unitDict.get("range")).floatValue();
            }
        }
        if (unitDict.containsKey("damage")) {
            unitConfig.attackDamageWalker = ((Double)unitDict.get("damage")).floatValue();
        }
        if (unitDict.containsKey("damageI")) {
            unitConfig.attackDamageWalker = ((Double)unitDict.get("damageI")).floatValue();
        }
        if (unitDict.containsKey("damageF")) {
            unitConfig.attackDamageTower = ((Double)unitDict.get("damageF")).floatValue();
        }
        if (unitDict.containsKey("shieldAmount")) {
            unitConfig.shieldPerUnit = ((Double)unitDict.get("shieldAmount")).floatValue();
        }
        if (unitDict.containsKey("speed")) {
            unitConfig.speed = ((Double)unitDict.get("speed")).floatValue();
        }
        unitConfig.display = unitDict.get("display").toString();
        unitConfig.shorthand = unitDict.get("shorthand").toString();
        if (unitType != 6 && unitType != 7) {
            unitConfig.unitCategory = unitType < 3 ? CategoryType.TOWER : CategoryType.WALKER;
        }
        return unitConfig;
    }

    public static void loadSettings(ConfigVariables config, Map<String, Object> settings, boolean workerMode, int totalTypes) {
        Map timingAndReplay = (Map)settings.get("timingAndReplay");
        config.waitTimeManual = ((Double)timingAndReplay.get("waitTimeManual")).longValue();
        if (workerMode) {
            config.waitTimeBotMax = ((Double)timingAndReplay.get("waitTimeBotMax")).longValue();
            config.waitTimeBotSoft = ((Double)timingAndReplay.get("waitTimeBotSoft")).longValue();
            config.replaySave = ((Double)timingAndReplay.get("replaySave")).intValue();
        } else {
            config.waitTimeBotMax = ((Double)timingAndReplay.get("playWaitTimeBotMax")).longValue();
            config.waitTimeBotSoft = ((Double)timingAndReplay.get("playWaitTimeBotSoft")).longValue();
            config.replaySave = ((Double)timingAndReplay.get("playReplaySave")).intValue();
        }
        config.waitForever = (Boolean)timingAndReplay.get("waitForever");
        config.storeBotTimes = (Boolean)timingAndReplay.get("storeBotTimes");
        if (timingAndReplay.containsKey("waitTimeStartGame")) {
            config.waitTimeStartGame = ((Double)timingAndReplay.get("waitTimeStartGame")).longValue();
        }
        if (timingAndReplay.containsKey("waitTimeEndGame")) {
            config.waitTimeEndGame = ((Double)timingAndReplay.get("waitTimeEndGame")).longValue();
        }
        Map resources = (Map)settings.get("resources");
        config.startingHP = ((Double)resources.get("startingHP")).floatValue();
        config.startingCores = ((Double)resources.get("startingCores")).floatValue();
        config.startingBits = ((Double)resources.get("startingBits")).floatValue();
        config.coresPerRound = ((Double)resources.get("coresPerRound")).floatValue();
        config.bitsPerRound = ((Double)resources.get("bitsPerRound")).floatValue();
        config.maxBits = ((Double)resources.get("maxBits")).floatValue();
        config.roundStartBitRamp = ((Double)resources.get("roundStartBitRamp")).intValue();
        config.bitRampBitCapGrowthRate = ((Double)resources.get("bitRampBitCapGrowthRate")).floatValue();
        config.turnIntervalForBitCapSchedule = ((Double)resources.get("turnIntervalForBitCapSchedule")).intValue();
        config.turnIntervalForBitSchedule = ((Double)resources.get("turnIntervalForBitSchedule")).intValue();
        config.bitGrowthRate = ((Double)resources.get("bitGrowthRate")).floatValue();
        config.bitDecayPerRound = ((Double)resources.get("bitDecayPerRound")).floatValue();
        Map debug2 = (Map)settings.get("debug");
        config.printMapString = (Boolean)debug2.get("printMapString");
        config.printActStrings = (Boolean)debug2.get("printActStrings");
        config.printTStrings = (Boolean)debug2.get("printTStrings");
        config.printHitStrings = (Boolean)debug2.get("printHitStrings");
        config.printPlayerInputStrings = (Boolean)debug2.get("printPlayerInputStrings");
        config.printPlayerGetHitStrings = (Boolean)debug2.get("printPlayerGetHitStrings");
        config.printBotErrors = (Boolean)debug2.get("printBotErrors");
        config.printBotErrors = (Boolean)debug2.get("printBotErrors");
        if (settings.containsKey("seasonCompatibilityModeP1") || settings.containsKey("seasonCompatibilityModeP2")) {
            config.seasonCompatibilityModeP1 = ((Double)settings.get("seasonCompatibilityModeP1")).intValue();
            config.seasonCompatibilityModeP2 = ((Double)settings.get("seasonCompatibilityModeP2")).intValue();
        }
        if (settings.containsKey("mechanics")) {
            Map mechanics = (Map)settings.get("mechanics");
            ArrayList unitSettings = (ArrayList)settings.get("unitInformation");
            Map[] unitInfoOld = unitSettings.toArray(new Map[unitSettings.size()]);
            for (int i = 0; i < unitInfoOld.length; ++i) {
                Map unitDict = unitInfoOld[i];
                config.UnitInfo[i] = Config.processUnitInfoOld(unitDict, i, config.UnitInfo[i]);
                if (i == 1) {
                    config.UnitInfo[i].shieldDecay = ((Double)mechanics.get("shieldDecayPerFrame")).floatValue();
                }
                if (i < 3) {
                    config.UnitInfo[i].turnsRequiredToRemove = ((Double)mechanics.get("destroyOwnUnitDelay")).intValue();
                    if (((Boolean)mechanics.get("destroyOwnUnitsEnabled")).booleanValue()) {
                        config.UnitInfo[i].refundPercentage = ((Double)mechanics.get("destroyOwnUnitRefund")).floatValue();
                    }
                }
                if (i < 3 || i >= 6) continue;
                config.UnitInfo[i].selfDestructRange = ((Double)mechanics.get("selfDestructRadius")).floatValue() - config.UnitInfo[0].getHitRadius;
                config.UnitInfo[i].playerBreachDamage = ((Double)mechanics.get("basePlayerHealthDamage")).floatValue();
                config.UnitInfo[i].selfDestructStepsRequired = ((Double)mechanics.get("stepsRequiredSelfDestruct")).intValue();
                config.UnitInfo[i].metalForBreach = ((Double)resources.get("coresForPlayerDamage")).intValue();
            }
        } else {
            Map[] unitInfoNew = ((ArrayList)settings.get("unitInformation")).toArray(new Map[totalTypes]);
            for (int i = 0; i < unitInfoNew.length; ++i) {
                Map unitDict = unitInfoNew[i];
                if (unitDict == null) continue;
                config.UnitInfo[i] = Config.processUnitInfo(unitDict, i, config.UnitInfo[i]);
            }
        }
    }

    public static HashMap<String, Object> getDefaultSettingsPostSeason5(ConfigVariables config, int totalTypes, int rmTypeID, int upgradeTypeID) {
        HashMap<String, Object> upgrade;
        HashMap<String, Object> RM;
        HashMap<String, Object> ST;
        HashMap<String, Object> SS;
        HashMap<String, Object> SI;
        HashMap<String, Object> TE;
        HashMap<String, Object> TS;
        HashMap<String, Object> TB;
        HashMap<String, Object> settings = new HashMap<String, Object>();
        Map[] unitInformation = new Map[totalTypes];
        unitInformation[0] = TB = SpawnUnitsSystem.UnitConfig.serializeUnitConfig(config.UnitInfo[0]);
        unitInformation[1] = TS = SpawnUnitsSystem.UnitConfig.serializeUnitConfig(config.UnitInfo[1]);
        int currentType = 2;
        unitInformation[currentType] = TE = SpawnUnitsSystem.UnitConfig.serializeUnitConfig(config.UnitInfo[currentType]);
        currentType = 3;
        unitInformation[currentType] = SI = SpawnUnitsSystem.UnitConfig.serializeUnitConfig(config.UnitInfo[currentType]);
        currentType = 4;
        unitInformation[currentType] = SS = SpawnUnitsSystem.UnitConfig.serializeUnitConfig(config.UnitInfo[currentType]);
        currentType = 5;
        unitInformation[currentType] = ST = SpawnUnitsSystem.UnitConfig.serializeUnitConfig(config.UnitInfo[currentType]);
        currentType = rmTypeID;
        unitInformation[rmTypeID] = RM = SpawnUnitsSystem.UnitConfig.serializeUnitConfig(config.UnitInfo[currentType]);
        currentType = upgradeTypeID;
        unitInformation[upgradeTypeID] = upgrade = SpawnUnitsSystem.UnitConfig.serializeUnitConfig(config.UnitInfo[currentType]);
        settings.put("unitInformation", unitInformation);
        HashMap<String, Serializable> timingAndReplay = new HashMap<String, Serializable>();
        timingAndReplay.put("waitTimeManual", Long.valueOf(config.waitTimeManual));
        timingAndReplay.put("waitTimeBotMax", Long.valueOf(config.waitTimeBotMax));
        timingAndReplay.put("waitTimeBotSoft", Long.valueOf(config.waitTimeBotSoft));
        timingAndReplay.put("replaySave", Integer.valueOf(config.replaySave));
        timingAndReplay.put("playWaitTimeBotMax", Long.valueOf(config.waitTimeBotMax));
        timingAndReplay.put("playWaitTimeBotSoft", Long.valueOf(config.waitTimeBotSoft));
        timingAndReplay.put("playReplaySave", Integer.valueOf(config.replaySave));
        timingAndReplay.put("waitForever", Boolean.valueOf(config.waitForever));
        timingAndReplay.put("storeBotTimes", Boolean.valueOf(config.storeBotTimes));
        timingAndReplay.put("waitTimeStartGame", Long.valueOf(config.waitTimeStartGame));
        timingAndReplay.put("waitTimeEndGame", Long.valueOf(config.waitTimeEndGame));
        settings.put("timingAndReplay", timingAndReplay);
        HashMap<String, Number> resources = new HashMap<String, Number>();
        resources.put("startingHP", Float.valueOf(config.startingHP));
        resources.put("startingCores", Float.valueOf(config.startingCores));
        resources.put("startingBits", Float.valueOf(config.startingBits));
        resources.put("coresPerRound", Float.valueOf(config.coresPerRound));
        resources.put("bitsPerRound", Float.valueOf(config.bitsPerRound));
        resources.put("maxBits", Float.valueOf(config.maxBits));
        resources.put("roundStartBitRamp", config.roundStartBitRamp);
        resources.put("bitRampBitCapGrowthRate", Float.valueOf(config.bitRampBitCapGrowthRate));
        resources.put("turnIntervalForBitCapSchedule", config.turnIntervalForBitCapSchedule);
        resources.put("turnIntervalForBitSchedule", config.turnIntervalForBitSchedule);
        resources.put("bitGrowthRate", Float.valueOf(config.bitGrowthRate));
        resources.put("bitDecayPerRound", Float.valueOf(config.bitDecayPerRound));
        settings.put("resources", resources);
        HashMap<String, Boolean> debug2 = new HashMap<String, Boolean>();
        debug2.put("printMapString", config.printMapString);
        debug2.put("printActStrings", config.printActStrings);
        debug2.put("printTStrings", config.printTStrings);
        debug2.put("printHitStrings", config.printHitStrings);
        debug2.put("printPlayerInputStrings", config.printPlayerInputStrings);
        debug2.put("printPlayerGetHitStrings", config.printPlayerGetHitStrings);
        debug2.put("printBotErrors", config.printBotErrors);
        settings.put("debug", debug2);
        settings.put("seasonCompatibilityModeP1", config.seasonCompatibilityModeP1);
        settings.put("seasonCompatibilityModeP2", config.seasonCompatibilityModeP2);
        return settings;
    }

    public static HashMap<String, Object> getDefaultSettingsPreSeason5(ConfigVariables config, int totalTypes, int rmTypeID) {
        HashMap<String, Object> settings = new HashMap<String, Object>();
        Map[] unitInformation = new Map[totalTypes];
        HashMap<String, Object> TB = new HashMap<String, Object>();
        TB.put("cost", Float.valueOf(config.UnitInfo[0].metalCost));
        TB.put("stability", Float.valueOf(config.UnitInfo[0].startHealth));
        TB.put("range", Float.valueOf(config.UnitInfo[0].attackRange));
        TB.put("damage", Float.valueOf(config.UnitInfo[0].attackDamageWalker));
        TB.put("getHitRadius", Float.valueOf(config.UnitInfo[0].getHitRadius));
        TB.put("display", config.UnitInfo[0].display);
        TB.put("shorthand", config.UnitInfo[0].shorthand);
        unitInformation[0] = TB;
        HashMap<String, Object> TS = new HashMap<String, Object>();
        TS.put("cost", Float.valueOf(config.UnitInfo[1].metalCost));
        TS.put("stability", Float.valueOf(config.UnitInfo[1].startHealth));
        TS.put("range", Float.valueOf(config.UnitInfo[1].shieldRange));
        TS.put("damage", Float.valueOf(config.UnitInfo[1].attackDamageWalker));
        TS.put("getHitRadius", Float.valueOf(config.UnitInfo[1].getHitRadius));
        TS.put("shieldAmount", Float.valueOf(config.UnitInfo[1].shieldPerUnit));
        TS.put("display", config.UnitInfo[1].display);
        TS.put("shorthand", config.UnitInfo[1].shorthand);
        unitInformation[1] = TS;
        HashMap<String, Object> TE = new HashMap<String, Object>();
        TE.put("cost", Float.valueOf(config.UnitInfo[2].metalCost));
        TE.put("stability", Float.valueOf(config.UnitInfo[2].startHealth));
        TE.put("range", Float.valueOf(config.UnitInfo[2].attackRange));
        TE.put("damage", Float.valueOf(config.UnitInfo[2].attackDamageWalker));
        TE.put("getHitRadius", Float.valueOf(config.UnitInfo[2].getHitRadius));
        TE.put("display", config.UnitInfo[2].display);
        TE.put("shorthand", config.UnitInfo[2].shorthand);
        unitInformation[2] = TE;
        HashMap<String, Object> SI = new HashMap<String, Object>();
        SI.put("cost", Float.valueOf(config.UnitInfo[3].foodCost));
        SI.put("stability", Float.valueOf(config.UnitInfo[3].startHealth));
        SI.put("range", Float.valueOf(config.UnitInfo[3].attackRange));
        SI.put("damageF", Float.valueOf(config.UnitInfo[3].attackDamageTower));
        SI.put("damageI", Float.valueOf(config.UnitInfo[3].attackDamageWalker));
        SI.put("speed", Float.valueOf(config.UnitInfo[3].speed));
        SI.put("getHitRadius", Float.valueOf(config.UnitInfo[3].getHitRadius));
        SI.put("damageToPlayer", Float.valueOf(config.UnitInfo[3].playerBreachDamage));
        SI.put("display", config.UnitInfo[3].display);
        SI.put("shorthand", config.UnitInfo[3].shorthand);
        unitInformation[3] = SI;
        HashMap<String, Object> SS = new HashMap<String, Object>();
        SS.put("cost", Float.valueOf(config.UnitInfo[4].foodCost));
        SS.put("stability", Float.valueOf(config.UnitInfo[4].startHealth));
        SS.put("range", Float.valueOf(config.UnitInfo[4].attackRange));
        SS.put("damageF", Float.valueOf(config.UnitInfo[4].attackDamageTower));
        SS.put("damageI", Float.valueOf(config.UnitInfo[4].attackDamageWalker));
        SS.put("speed", Float.valueOf(config.UnitInfo[4].speed));
        SS.put("getHitRadius", Float.valueOf(config.UnitInfo[4].getHitRadius));
        SS.put("damageToPlayer", Float.valueOf(config.UnitInfo[4].playerBreachDamage));
        SS.put("display", config.UnitInfo[4].display);
        SS.put("shorthand", config.UnitInfo[4].shorthand);
        unitInformation[4] = SS;
        HashMap<String, Object> ST = new HashMap<String, Object>();
        ST.put("cost", Float.valueOf(config.UnitInfo[5].foodCost));
        ST.put("stability", Float.valueOf(config.UnitInfo[5].startHealth));
        ST.put("range", Float.valueOf(config.UnitInfo[5].attackRange));
        ST.put("damageF", Float.valueOf(config.UnitInfo[5].attackDamageTower));
        ST.put("damageI", Float.valueOf(config.UnitInfo[5].attackDamageWalker));
        ST.put("speed", Float.valueOf(config.UnitInfo[5].speed));
        ST.put("getHitRadius", Float.valueOf(config.UnitInfo[5].getHitRadius));
        ST.put("damageToPlayer", Float.valueOf(config.UnitInfo[5].playerBreachDamage));
        ST.put("display", config.UnitInfo[5].display);
        ST.put("shorthand", config.UnitInfo[5].shorthand);
        unitInformation[5] = ST;
        HashMap<String, String> RM = new HashMap<String, String>();
        RM.put("display", "Remove");
        RM.put("shorthand", "RM");
        unitInformation[rmTypeID] = RM;
        settings.put("unitInformation", unitInformation);
        HashMap<String, Serializable> timingAndReplay = new HashMap<String, Serializable>();
        timingAndReplay.put("waitTimeManual", Long.valueOf(config.waitTimeManual));
        timingAndReplay.put("waitTimeBotMax", Long.valueOf(config.waitTimeBotMax));
        timingAndReplay.put("waitTimeBotSoft", Long.valueOf(config.waitTimeBotSoft));
        timingAndReplay.put("replaySave", Integer.valueOf(config.replaySave));
        timingAndReplay.put("playWaitTimeBotMax", Long.valueOf(config.waitTimeBotMax));
        timingAndReplay.put("playWaitTimeBotSoft", Long.valueOf(config.waitTimeBotSoft));
        timingAndReplay.put("playReplaySave", Integer.valueOf(config.replaySave));
        timingAndReplay.put("waitForever", Boolean.valueOf(config.waitForever));
        timingAndReplay.put("storeBotTimes", Boolean.valueOf(config.storeBotTimes));
        timingAndReplay.put("waitTimeStartGame", Long.valueOf(config.waitTimeStartGame));
        timingAndReplay.put("waitTimeEndGame", Long.valueOf(config.waitTimeEndGame));
        settings.put("timingAndReplay", timingAndReplay);
        HashMap<String, Number> resources = new HashMap<String, Number>();
        resources.put("startingHP", Float.valueOf(config.startingHP));
        resources.put("startingCores", Float.valueOf(config.startingCores));
        resources.put("startingBits", Float.valueOf(config.startingBits));
        resources.put("coresPerRound", Float.valueOf(config.coresPerRound));
        resources.put("bitsPerRound", Float.valueOf(config.bitsPerRound));
        resources.put("maxBits", Float.valueOf(config.maxBits));
        resources.put("roundStartBitRamp", config.roundStartBitRamp);
        resources.put("bitRampBitCapGrowthRate", Float.valueOf(config.bitRampBitCapGrowthRate));
        resources.put("turnIntervalForBitCapSchedule", config.turnIntervalForBitCapSchedule);
        resources.put("turnIntervalForBitSchedule", config.turnIntervalForBitSchedule);
        resources.put("bitGrowthRate", Float.valueOf(config.bitGrowthRate));
        resources.put("bitDecayPerRound", Float.valueOf(config.bitDecayPerRound));
        resources.put("coresForPlayerDamage", Float.valueOf(config.UnitInfo[3].playerBreachDamage));
        settings.put("resources", resources);
        HashMap<String, Serializable> mechanics = new HashMap<String, Serializable>();
        mechanics.put("selfDestructRadius", Float.valueOf(config.UnitInfo[3].selfDestructRange));
        mechanics.put("destroyOwnUnitDelay", Integer.valueOf(config.UnitInfo[0].turnsRequiredToRemove));
        mechanics.put("destroyOwnUnitsEnabled", Boolean.valueOf(true));
        mechanics.put("destroyOwnUnitRefund", Float.valueOf(config.UnitInfo[0].refundPercentage));
        mechanics.put("shieldDecayPerFrame", Float.valueOf(config.UnitInfo[1].shieldDecay));
        mechanics.put("basePlayerHealthDamage", Float.valueOf(config.UnitInfo[3].playerBreachDamage));
        mechanics.put("damageGrowthBasedOnY", Integer.valueOf(0));
        mechanics.put("meleeMultiplier", Integer.valueOf(0));
        mechanics.put("stepsRequiredSelfDestruct", Integer.valueOf(config.UnitInfo[4].selfDestructStepsRequired));
        mechanics.put("rerouteMidRound", Boolean.valueOf(true));
        mechanics.put("bitsCanStackOnDeployment", Boolean.valueOf(true));
        mechanics.put("firewallBuildTime", Integer.valueOf(0));
        settings.put("mechanics", mechanics);
        HashMap<String, Boolean> debug2 = new HashMap<String, Boolean>();
        debug2.put("printMapString", config.printMapString);
        debug2.put("printActStrings", config.printActStrings);
        debug2.put("printTStrings", config.printTStrings);
        debug2.put("printHitStrings", config.printHitStrings);
        debug2.put("printPlayerInputStrings", config.printPlayerInputStrings);
        debug2.put("printPlayerGetHitStrings", config.printPlayerGetHitStrings);
        debug2.put("printBotErrors", config.printBotErrors);
        settings.put("debug", debug2);
        return settings;
    }

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path, new String[0]));
        return new String(encoded, encoding);
    }

    public static class ConfigVariables {
        public int seasonCompatibilityModeP1 = 4;
        public int seasonCompatibilityModeP2 = 4;
        public boolean printHitStrings = false;
        public boolean printBotErrors = true;
        public SpawnUnitsSystem.UnitConfig[] UnitInfo;
        public long waitTimeManual = 600000L;
        public long waitTimeBotMax = 10000L;
        public long waitTimeBotSoft = 4000L;
        public boolean waitForever = true;
        public long waitTimeStartGame = 3000L;
        public long waitTimeEndGame = 3000L;
        public float startingHP = 30.0f;
        public float startingCores = 40.0f;
        public float startingBits = 5.0f;
        public float coresPerRound = 5.0f;
        public float bitsPerRound = 5.0f;
        public float maxBits = 150.0f;
        public int roundStartBitRamp = 10;
        public float bitRampBitCapGrowthRate = 5.0f;
        public int turnIntervalForBitCapSchedule = 10;
        public int turnIntervalForBitSchedule = 10;
        public float bitGrowthRate = 1.0f;
        public float bitDecayPerRound = 0.25f;
        public boolean printMapString = false;
        public boolean printActStrings = false;
        public boolean printTStrings = false;
        public boolean printPlayerInputStrings = false;
        public boolean printPlayerGetHitStrings = false;
        public boolean storeBotTimes = true;
        public int replaySave = 1;

        public HashMap<String, Object> getPlayerPerspectiveConfigString(int playerIndex, int newSeasonConfig, int totalTypes, int rmTypeID, int upgradeTypeID, int totalTypesOldConfig) {
            boolean useNewConfig;
            boolean bl = playerIndex == 0 ? this.seasonCompatibilityModeP1 >= newSeasonConfig : (useNewConfig = this.seasonCompatibilityModeP2 >= newSeasonConfig);
            if (useNewConfig) {
                return Config.getDefaultSettingsPostSeason5(this, totalTypes, rmTypeID, upgradeTypeID);
            }
            return Config.getDefaultSettingsPreSeason5(this, totalTypesOldConfig, rmTypeID);
        }

        public ConfigVariables(int totalTypes) {
            this.UnitInfo = new SpawnUnitsSystem.UnitConfig[totalTypes];
            SpawnUnitsSystem.UnitConfig TB = new SpawnUnitsSystem.UnitConfig();
            TB.unitType = 0;
            TB.metalCost = 1.0f;
            TB.maxHealth = TB.startHealth = 60.0f;
            TB.getHitRadius = 0.01f;
            TB.display = "Filter";
            TB.shorthand = "FF";
            TB.unitCategory = CategoryType.TOWER;
            TB.refundPercentage = 0.75f;
            TB.turnsRequiredToRemove = 1;
            SpawnUnitsSystem.UnitConfig TBup = new SpawnUnitsSystem.UnitConfig(TB);
            TBup.upgrade = null;
            TBup.upgraded = true;
            TBup.maxHealth = TBup.startHealth = 120.0f;
            TB.upgrade = TBup;
            this.UnitInfo[0] = TB;
            SpawnUnitsSystem.UnitConfig TS = new SpawnUnitsSystem.UnitConfig();
            TS.unitType = 1;
            TS.metalCost = 4.0f;
            TS.maxHealth = TS.startHealth = 30.0f;
            TS.shieldRange = 3.5f;
            TS.getHitRadius = 0.01f;
            TS.shieldPerUnit = 0.0f;
            TS.display = "Encryptor";
            TS.shorthand = "EF";
            TS.unitCategory = CategoryType.TOWER;
            TS.refundPercentage = 0.75f;
            TS.shieldBonusPerY = 0.0f;
            TS.turnsRequiredToRemove = 1;
            TS.shieldDecay = 0.0f;
            SpawnUnitsSystem.UnitConfig TSup = new SpawnUnitsSystem.UnitConfig(TS);
            TSup.upgrade = null;
            TSup.upgraded = true;
            TSup.shieldRange = 7.0f;
            TSup.shieldPerUnit = 4.0f;
            TS.upgrade = TSup;
            this.UnitInfo[1] = TS;
            SpawnUnitsSystem.UnitConfig TE = new SpawnUnitsSystem.UnitConfig();
            TE.unitType = 2;
            TE.metalCost = 6.0f;
            TE.maxHealth = TE.startHealth = 75.0f;
            TE.attackRange = 3.5f;
            TE.attackDamageWalker = 16.0f;
            TE.getHitRadius = 0.01f;
            TE.display = "Destructor";
            TE.shorthand = "DF";
            TE.unitCategory = CategoryType.TOWER;
            TE.refundPercentage = 0.75f;
            TE.turnsRequiredToRemove = 1;
            SpawnUnitsSystem.UnitConfig TEup = new SpawnUnitsSystem.UnitConfig(TE);
            TEup.upgrade = null;
            TEup.upgraded = true;
            TEup.attackDamageWalker = 32.0f;
            TE.upgrade = TEup;
            this.UnitInfo[2] = TE;
            SpawnUnitsSystem.UnitConfig SI = new SpawnUnitsSystem.UnitConfig();
            SI.unitType = 3;
            SI.foodCost = 1.0f;
            SI.maxHealth = SI.startHealth = 15.0f;
            SI.attackRange = 3.5f;
            SI.attackDamageTower = 2.0f;
            SI.attackDamageWalker = 2.0f;
            SI.speed = 1.0f;
            SI.getHitRadius = 0.01f;
            SI.playerBreachDamage = 1.0f;
            SI.display = "Ping";
            SI.shorthand = "PI";
            SI.unitCategory = CategoryType.WALKER;
            SI.selfDestructDamageWalker = 15.0f;
            SI.selfDestructDamageTower = 15.0f;
            SI.metalForBreach = 1.0f;
            SI.selfDestructRange = 1.5f;
            SI.selfDestructStepsRequired = 5;
            this.UnitInfo[3] = SI;
            SpawnUnitsSystem.UnitConfig SS = new SpawnUnitsSystem.UnitConfig();
            SS.unitType = 4;
            SS.foodCost = 3.0f;
            SS.maxHealth = SS.startHealth = 5.0f;
            SS.attackRange = 4.5f;
            SS.attackDamageTower = 8.0f;
            SS.attackDamageWalker = 8.0f;
            SS.speed = 0.5f;
            SS.getHitRadius = 0.01f;
            SS.playerBreachDamage = 1.0f;
            SS.display = "EMP";
            SS.shorthand = "EI";
            SS.unitCategory = CategoryType.WALKER;
            SS.selfDestructDamageWalker = 5.0f;
            SS.selfDestructDamageTower = 5.0f;
            SS.metalForBreach = 1.0f;
            SS.selfDestructRange = 1.5f;
            SS.selfDestructStepsRequired = 5;
            this.UnitInfo[4] = SS;
            SpawnUnitsSystem.UnitConfig ST = new SpawnUnitsSystem.UnitConfig();
            ST.unitType = 5;
            ST.foodCost = 1.0f;
            ST.maxHealth = ST.startHealth = 40.0f;
            ST.attackRange = 4.5f;
            ST.attackDamageWalker = 20.0f;
            ST.speed = 0.25f;
            ST.getHitRadius = 0.01f;
            ST.playerBreachDamage = 1.0f;
            ST.display = "Scrambler";
            ST.shorthand = "SI";
            ST.unitCategory = CategoryType.WALKER;
            ST.selfDestructDamageWalker = 40.0f;
            ST.selfDestructDamageTower = 40.0f;
            ST.metalForBreach = 1.0f;
            ST.selfDestructRange = 1.5f;
            ST.selfDestructStepsRequired = 5;
            this.UnitInfo[5] = ST;
            SpawnUnitsSystem.UnitConfig RM = new SpawnUnitsSystem.UnitConfig();
            RM.unitType = 6;
            RM.display = "Remove";
            RM.shorthand = "RM";
            this.UnitInfo[6] = RM;
            SpawnUnitsSystem.UnitConfig UP = new SpawnUnitsSystem.UnitConfig();
            UP.unitType = 7;
            UP.display = "Upgrade";
            UP.shorthand = "UP";
            this.UnitInfo[7] = UP;
        }
    }
}

