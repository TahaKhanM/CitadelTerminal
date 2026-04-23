/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game;

import com.c1games.terminal.game.Config;
import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.GameMain;
import com.c1games.terminal.game.events.globalevents.GlobalSpawn;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.player.PlayerMove;
import com.c1games.terminal.game.systems.SpawnUnitsSystem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Parser {
    public static int typeStringToId(Config.ConfigVariables config, String type) {
        for (int i = 0; i < config.UnitInfo.length; ++i) {
            String check2 = config.UnitInfo[i].shorthand;
            if (!check2.contains(type)) continue;
            return i;
        }
        return -1;
    }

    public static String parseVisiblesListToJson(GameMain gameMain, int viewBits, ArrayList<Object[]> visibles, int phase, long p1Time, long p2Time, Map<String, Object> endGameStatsPlayerPerspective, boolean displayOldCompatibility) {
        HashMap<String, Object> fullState = new HashMap<String, Object>();
        int actionFrames = -1;
        if (phase != 0) {
            actionFrames = gameMain.actionFrame;
        }
        if (displayOldCompatibility) {
            fullState.put("turnInfo", new int[]{phase, gameMain.turn, actionFrames});
        } else {
            fullState.put("turnInfo", new int[]{phase, gameMain.turn, actionFrames, gameMain.totalFrameNumber});
        }
        ArrayList<Number> p1stats = new ArrayList<Number>();
        p1stats.add(Float.valueOf(gameMain.p1stats.totalHP));
        p1stats.add(Float.valueOf(gameMain.p1stats.getMetal()));
        p1stats.add(Float.valueOf(gameMain.p1stats.getFood()));
        ArrayList<Number> p2stats = new ArrayList<Number>();
        p2stats.add(Float.valueOf(gameMain.p2stats.totalHP));
        p2stats.add(Float.valueOf(gameMain.p2stats.getMetal()));
        p2stats.add(Float.valueOf(gameMain.p2stats.getFood()));
        if (gameMain.configVariables.storeBotTimes) {
            p1stats.add(p1Time);
            p2stats.add(p2Time);
        }
        int unitTotalTypesCompatibility = displayOldCompatibility ? 7 : 8;
        ArrayList[] p1Units = new ArrayList[unitTotalTypesCompatibility];
        ArrayList[] p2Units = new ArrayList[unitTotalTypesCompatibility];
        for (int i = 0; i < p1Units.length; ++i) {
            p1Units[i] = new ArrayList();
            p2Units[i] = new ArrayList();
        }
        for (Object[] unit : visibles) {
            int arrayPos = Parser.typeStringToId(gameMain.configVariables, unit[0].toString());
            if (arrayPos >= unitTotalTypesCompatibility) continue;
            int x = (Integer)unit[1];
            int y = (Integer)unit[2];
            float HP = ((Float)unit[3]).floatValue();
            int playernum = (Integer)unit[4];
            String gid = (String)unit[5];
            Object[] unitinfo = new Object[]{x, y, Float.valueOf(HP), gid};
            if (playernum == 1) {
                p1Units[arrayPos].add(unitinfo);
                continue;
            }
            p2Units[arrayPos].add(unitinfo);
        }
        fullState.put("p1Units", p1Units);
        fullState.put("p2Units", p2Units);
        if (viewBits == 0) {
            fullState.put("p1Stats", p1stats);
            fullState.put("p2Stats", p2stats);
        } else {
            fullState.put("p1Stats", p2stats);
            fullState.put("p2Stats", p1stats);
        }
        if (endGameStatsPlayerPerspective != null) {
            fullState.put("endStats", endGameStatsPlayerPerspective);
        }
        fullState.put("events", gameMain.eventDisplayer.getEventsForRound(viewBits));
        Gson gson = new Gson();
        String end = gson.toJson(fullState);
        return end;
    }

    public static void processInputBuild(GameMain gameMain, Game game, List<PlayerMove> moves, Consumer<GlobalSpawn> addSpawnEvent, Consumer<Gameobject> destroyGameObject) {
        for (int i = 0; i < moves.size(); ++i) {
            Parser.processInputForPlayer(gameMain, game, moves.get(i).part1OrEmpty(), i, true, addSpawnEvent, destroyGameObject);
        }
    }

    private static void processInputForPlayer(GameMain gameMain, Game game, String inp, int playerID, boolean buildPhase, Consumer<GlobalSpawn> addSpawnEvent, Consumer<Gameobject> destroyGameObject) {
        block12: {
            if (inp.equals("") || inp == null) {
                return;
            }
            if (gameMain.configVariables.printPlayerInputStrings) {
                System.out.println(gameMain.turn + " P" + (playerID + 1) + " " + inp);
            }
            String phase = buildPhase ? "build" : "deploy";
            try {
                Gson gson = new Gson();
                ArrayList commands = (ArrayList)gson.fromJson(inp, new TypeToken<ArrayList<ArrayList<Object>>>(){}.getType());
                ArrayList<ArrayList> sortedCommands = new ArrayList<ArrayList>();
                for (ArrayList c : commands) {
                    if (Parser.typeStringToId(gameMain.configVariables, (String)c.get(0)) == 6) continue;
                    sortedCommands.add(c);
                }
                for (ArrayList c : commands) {
                    if (Parser.typeStringToId(gameMain.configVariables, (String)c.get(0)) != 6) continue;
                    sortedCommands.add(c);
                }
                for (ArrayList c : sortedCommands) {
                    try {
                        int typeid;
                        block13: {
                            int y;
                            int x;
                            block15: {
                                block14: {
                                    String typeName = (String)c.get(0);
                                    typeid = Parser.typeStringToId(gameMain.configVariables, typeName);
                                    x = ((Double)c.get(1)).intValue();
                                    y = ((Double)c.get(2)).intValue();
                                    if (typeid == -1) break block13;
                                    if (!buildPhase) break block14;
                                    if (typeid == 6) break block15;
                                    if (typeid == 7 || typeid < 3) break block15;
                                }
                                if (buildPhase || typeid < 3) break block13;
                                if (typeid == 6) break block13;
                            }
                            SpawnUnitsSystem.parseUnit(gameMain, game, typeid, x, y, -1.0f, playerID, playerID, true, addSpawnEvent, destroyGameObject);
                            continue;
                        }
                        System.out.println("Invalid build command for player " + playerID + " : " + String.valueOf(c) + " in phase: " + phase);
                        System.out.println("typeid = " + typeid);
                        System.out.println("buildPhase = " + buildPhase);
                    }
                    catch (Exception e) {
                        if (!gameMain.configVariables.printBotErrors) continue;
                        System.out.println("Invalid build command for player " + playerID + " : " + String.valueOf(c) + " in phase: " + phase);
                        e.printStackTrace(System.out);
                    }
                }
            }
            catch (Exception e) {
                if (!gameMain.configVariables.printBotErrors) break block12;
                System.out.println("Invalid command, couldn't json for player " + playerID + " : " + inp);
                e.printStackTrace(System.out);
            }
        }
    }

    public static void loadTurnString(GameMain gameMain, Game game, String loadstring, Consumer<GlobalSpawn> addSpawnEvent, Consumer<Gameobject> destroyGameObject) {
        float hp;
        int y;
        int x;
        Object[] unitstats;
        int j;
        ArrayList unitsByType;
        int i;
        if (loadstring == "" || loadstring == null || loadstring.length() < 1) {
            return;
        }
        if (loadstring != null && loadstring != "" && gameMain.configVariables.printPlayerInputStrings) {
            System.out.println("Loading string: " + loadstring);
        }
        Gson gson = new Gson();
        Map state = (Map)gson.fromJson(loadstring, new TypeToken<Map<String, Object>>(){}.getType());
        ArrayList p1stats = (ArrayList)state.get("p1Stats");
        ArrayList p2stats = (ArrayList)state.get("p2Stats");
        Double[] turnInfo = ((ArrayList)state.get("turnInfo")).toArray(new Double[3]);
        ArrayList[] p1Units = ((ArrayList)state.get("p1Units")).toArray(new ArrayList[8]);
        ArrayList[] p2Units = ((ArrayList)state.get("p2Units")).toArray(new ArrayList[8]);
        gameMain.turn = turnInfo[1].intValue();
        gameMain.p1stats.totalHP = ((Double)p1stats.get(0)).floatValue();
        gameMain.p1stats.setMetal(((Double)p1stats.get(1)).floatValue());
        gameMain.p1stats.setFood(((Double)p1stats.get(2)).floatValue());
        gameMain.p2stats.totalHP = ((Double)p2stats.get(0)).floatValue();
        gameMain.p2stats.setMetal(((Double)p2stats.get(1)).floatValue());
        gameMain.p2stats.setFood(((Double)p2stats.get(2)).floatValue());
        for (i = 0; i < p1Units.length; ++i) {
            unitsByType = p1Units[i];
            for (j = 0; j < unitsByType.size(); ++j) {
                unitstats = ((ArrayList)unitsByType.get(j)).toArray(new Object[4]);
                x = ((Double)unitstats[0]).intValue();
                y = ((Double)unitstats[1]).intValue();
                hp = ((Double)unitstats[2]).floatValue();
                SpawnUnitsSystem.parseUnit(gameMain, game, i, x, y, hp, 0, 0, false, addSpawnEvent, destroyGameObject);
            }
        }
        for (i = 0; i < p2Units.length; ++i) {
            unitsByType = p2Units[i];
            for (j = 0; j < unitsByType.size(); ++j) {
                unitstats = ((ArrayList)unitsByType.get(j)).toArray(new Object[4]);
                x = ((Double)unitstats[0]).intValue();
                y = ((Double)unitstats[1]).intValue();
                hp = ((Double)unitstats[2]).floatValue();
                SpawnUnitsSystem.parseUnit(gameMain, game, i, x, y, hp, 0, 1, false, addSpawnEvent, destroyGameObject);
            }
        }
    }

    public static void processInputDeploy(GameMain GameMain2, Game game, List<PlayerMove> moves, Consumer<GlobalSpawn> addSpawnEvent, Consumer<Gameobject> destroyGameObject) {
        for (int i = 0; i < moves.size(); ++i) {
            Parser.processInputForPlayer(GameMain2, game, moves.get(i).part2OrEmpty(), i, false, addSpawnEvent, destroyGameObject);
        }
    }
}

