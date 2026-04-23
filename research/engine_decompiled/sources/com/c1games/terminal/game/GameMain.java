/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game;

import com.c1games.terminal.game.Config;
import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.GamePhase;
import com.c1games.terminal.game.Parser;
import com.c1games.terminal.game.PlayerStats;
import com.c1games.terminal.game.ReplayFileInstance;
import com.c1games.terminal.game.TowerGame;
import com.c1games.terminal.game.events.globalevents.EventDisplayer;
import com.c1games.terminal.game.gameobject.components.UnitInfoComponent;
import com.c1games.terminal.game.player.AlgoPlayerNull;
import com.c1games.terminal.game.player.Player;
import com.c1games.terminal.game.player.PlayerManager;
import com.c1games.terminal.game.player.PlayerMove;
import com.c1games.terminal.game.systems.DisplayUnitsSystem;
import com.c1games.terminal.game.systems.map.MapHelper;
import com.c1games.terminal.game.systems.map.structure.Coords;
import com.c1games.terminal.game.units.CategoryType;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class GameMain
implements TowerGame {
    Game game;
    public static String replayPath;
    public MapHelper mapHelper;
    public GamePhase phase;
    public PlayerStats p1stats;
    public PlayerStats p2stats;
    public List<Coords> p1Edges;
    public List<Coords> p2Edges;
    public int firewallBuildTime = 0;
    public static final int boardSize = 28;
    public static final int player1ID = 0;
    public static final int player2ID = 1;
    public static final int rmTypeID = 6;
    public static final int upgradeTypeID = 7;
    public static final int totalTypes = 8;
    public static final int newConfigSeason = 5;
    public static final int totalTypesOldConfig = 7;
    public Config.ConfigVariables configVariables = new Config.ConfigVariables(8);
    public Random random;
    ReplayFileInstance p1replay = null;
    ReplayFileInstance p2replay = null;
    boolean p1Manual;
    boolean p2Manual;
    public int turn = 0;
    public int actionFrame = 0;
    public boolean gameover = false;
    public long startTime;
    public int totalActionFrames = 0;
    public int totalFrameNumber = -1;
    public Map<Integer, Object> idToObjectType = null;
    public EventDisplayer eventDisplayer;
    public float meleeMultiplier = 1.0f;
    public int stepsRequiredSelfDestruct = 5;
    private boolean exitGame = false;
    public boolean workerMode = false;
    private GameMainGameObjectKey gameMainGameObjectKey = new GameMainGameObjectKey();
    public static final String DATE_FORMAT_NOW = "dd-MM-yyyy-HH-mm-ss";

    public static void main(String[] args) throws IOException, InterruptedException {
        GameMain startGame = new GameMain();
        startGame.workerMode = true;
        Config.useConfigFile(startGame);
        Player player1 = null;
        Player player2 = null;
        Optional<String> startString = Optional.empty();
        if (args.length == 0) {
            player1 = new AlgoPlayerNull();
            player2 = new AlgoPlayerNull();
            startGame.p1Manual = true;
            startGame.p2Manual = true;
        } else {
            try {
                for (String a : args) {
                    if (!startGame.configVariables.printBotErrors) continue;
                    System.out.println(a);
                }
                if (args.length == 1) {
                    player1 = PlayerManager.setupPlayerFromCommandString(args[0], startGame.configVariables.printBotErrors, 0);
                    player2 = new AlgoPlayerNull();
                    startGame.p1Manual = true;
                    startGame.p2Manual = false;
                } else if (args.length == 2) {
                    player1 = PlayerManager.setupPlayerFromCommandString(args[0], startGame.configVariables.printBotErrors, 0);
                    player2 = PlayerManager.setupPlayerFromCommandString(args[1], startGame.configVariables.printBotErrors, 1);
                    startGame.p1Manual = false;
                    startGame.p2Manual = false;
                } else if (args.length == 3) {
                    startString = Optional.of(args[2]);
                    player1 = PlayerManager.setupPlayerFromCommandString(args[0], startGame.configVariables.printBotErrors, 0);
                    player2 = PlayerManager.setupPlayerFromCommandString(args[1], startGame.configVariables.printBotErrors, 1);
                    startGame.p1Manual = false;
                    startGame.p2Manual = false;
                } else {
                    System.out.println("Invalid args, format= '[command for ai1] [command for ai2]'");
                    System.exit(1);
                }
            }
            catch (Exception e) {
                System.out.println("Invalid args or something went wrong, format= '[command for ai1] [command for ai2]'");
                e.printStackTrace(System.out);
                throw e;
            }
        }
        startGame.startGame(player1, player2, startString);
        System.out.println("End of main");
        player1.close();
        player2.close();
    }

    public GameMain() {
        this.startTime = System.currentTimeMillis();
        this.eventDisplayer = new EventDisplayer(this);
        this.random = new Random();
    }

    @Override
    public void run(Map<String, Object> settings, Player player1, Player player2, Optional<String> startState) throws InterruptedException {
        Config.loadSettingsFromObject(this, settings);
        this.startGame(player1, player2, startState);
    }

    public void startGame(Player player1, Player player2, Optional<String> startState) throws InterruptedException {
        this.setupReplaySave();
        Player[] players = new Player[]{player1, player2};
        this.game = new Game(players, 28, 28, this.configVariables.printBotErrors);
        this.mapHelper = new MapHelper(this.game, this);
        try {
            this.runLoop(startState.orElse(""));
        }
        catch (Exception e) {
            System.out.println("Game loop crashed!");
            e.printStackTrace();
            throw e;
        }
    }

    private String getReplayFileName(int playerID) {
        String pName = playerID == 0 ? "/p1-" : "/p2-";
        return pName + GameMain.getTimeNow() + "-" + System.currentTimeMillis() + "-" + this.random.nextInt() + ".replay";
    }

    private void setupReplaySave() {
        try {
            if (this.configVariables.replaySave != 0) {
                replayPath = String.valueOf(new File("").getAbsoluteFile()) + "/replays";
                if (this.configVariables.replaySave == 1) {
                    this.p1replay = new ReplayFileInstance(replayPath, this.getReplayFileName(0), "");
                } else if (this.configVariables.replaySave == 2) {
                    this.p2replay = new ReplayFileInstance(replayPath, this.getReplayFileName(1), "");
                } else if (this.configVariables.replaySave == 3) {
                    this.p1replay = new ReplayFileInstance(replayPath, this.getReplayFileName(0), "");
                    this.p2replay = new ReplayFileInstance(replayPath, this.getReplayFileName(1), "");
                }
            }
        }
        catch (Exception e) {
            System.out.println("Couldn't setup replay files");
            e.printStackTrace();
        }
    }

    public void runLoop(String startingFirstTurn) throws InterruptedException {
        System.out.println("P1 Name: " + this.game.playerManager.getPlayerName(0) + " seasonMode:" + this.configVariables.seasonCompatibilityModeP1);
        System.out.println("P2 Name: " + this.game.playerManager.getPlayerName(1) + " seasonMode:" + this.configVariables.seasonCompatibilityModeP2);
        this.p1Edges = new ArrayList<Coords>();
        this.p1Edges.addAll(Arrays.asList(this.game.bounds.edgeLists[2]));
        this.p1Edges.addAll(Arrays.asList(this.game.bounds.edgeLists[3]));
        this.p2Edges = new ArrayList<Coords>();
        this.p2Edges.addAll(Arrays.asList(this.game.bounds.edgeLists[1]));
        this.p2Edges.addAll(Arrays.asList(this.game.bounds.edgeLists[0]));
        this.phase = GamePhase.BUILDPHASE;
        this.p1stats = new PlayerStats(this, 0, this.configVariables.startingHP, this.configVariables.startingCores, this.configVariables.startingBits, this.game.playerManager.checkIfAlgo(0) ? this.configVariables.waitTimeBotSoft : this.configVariables.waitTimeManual);
        this.p2stats = new PlayerStats(this, 1, this.configVariables.startingHP, this.configVariables.startingCores, this.configVariables.startingBits, this.game.playerManager.checkIfAlgo(1) ? this.configVariables.waitTimeBotSoft : this.configVariables.waitTimeManual);
        ArrayList<Object[]> visibles1 = new ArrayList();
        ArrayList<Object[]> visibles2 = new ArrayList();
        this.game.playerManager.getPlayerErrorPrint();
        System.out.println("Waiting for: " + this.configVariables.waitTimeStartGame + " before starting first turn.");
        Thread.sleep(this.configVariables.waitTimeStartGame);
        this.game.playerManager.getPlayerErrorPrint();
        System.out.println("Done waiting starting first turn.");
        this.game.playerManager.finishInitialization(Duration.ofMillis(this.configVariables.waitTimeStartGame));
        Gson gson = new Gson();
        this.game.playerManager.sendGameState(gson.toJson(this.configVariables.getPlayerPerspectiveConfigString(0, 5, 8, 6, 7, 7)) + "\n", 0);
        this.game.playerManager.sendGameState(gson.toJson(this.configVariables.getPlayerPerspectiveConfigString(1, 5, 8, 6, 7, 7)) + "\n", 1);
        if (this.p1replay != null) {
            this.p1replay.addToFile(gson.toJson(this.configVariables.getPlayerPerspectiveConfigString(0, 5, 8, 6, 7, 7)) + "\n");
        }
        if (this.p2replay != null) {
            this.p2replay.addToFile(gson.toJson(this.configVariables.getPlayerPerspectiveConfigString(1, 5, 8, 6, 7, 7)) + "\n");
        }
        if (startingFirstTurn != "") {
            Parser.loadTurnString(this, this.game, startingFirstTurn, this.eventDisplayer.getEventAdder(), this.game.getDestroyGameObject(this.gameMainGameObjectKey));
        }
        long[] playerTimes = new long[2];
        List<PlayerMove> moves = null;
        int i = 0;
        while (!this.exitGame) {
            String p2String;
            String p1String;
            ArrayList<DisplayUnitsSystem.DisplayUnitSystemComponents> visibleObjects;
            if (this.turn == 100 || this.gameover) {
                this.processEndGame(true);
                break;
            }
            if (this.phase == GamePhase.BUILDPHASE) {
                this.game.runRemoveOwnUnitSystem(this.turn, this.p1stats, this.p2stats, this.eventDisplayer);
                if (this.configVariables.printPlayerInputStrings) {
                    System.out.println("Starting turn: " + this.turn);
                }
                ++this.totalFrameNumber;
                visibleObjects = Game.getSystemObjects(this.game.gameObjects, DisplayUnitsSystem.DisplayUnitSystemComponents::tryCreateDisplayComponents);
                visibles1 = DisplayUnitsSystem.getDisplayUnits(0, visibleObjects, this.configVariables.UnitInfo, this.turn, 6, 7);
                visibles2 = DisplayUnitsSystem.getDisplayUnits(1, visibleObjects, this.configVariables.UnitInfo, this.turn, 6, 7);
                p1String = Parser.parseVisiblesListToJson(this, 0, visibles1, 0, playerTimes[0], playerTimes[1], null, this.configVariables.seasonCompatibilityModeP1 < 5);
                p2String = Parser.parseVisiblesListToJson(this, 1, visibles2, 0, playerTimes[0], playerTimes[1], null, this.configVariables.seasonCompatibilityModeP2 < 5);
                this.eventDisplayer.clearEvents();
                this.game.playerManager.sendGameState(p1String + "\n", 0);
                this.game.playerManager.sendGameState(p2String + "\n", 1);
                if (this.configVariables.printTStrings) {
                    System.out.println(p1String);
                    System.out.println(p2String);
                }
                if (this.p1replay != null) {
                    this.p1replay.addToFile(p1String);
                }
                if (this.p2replay != null) {
                    this.p2replay.addToFile(p2String);
                }
                if (!this.p1Manual && !this.p2Manual && this.configVariables.printMapString) {
                    System.out.println(this.mapHelper.printMapPlayerAlwaysBottom(0, this.configVariables.UnitInfo));
                }
                String[] showScreen = new String[]{this.p1Manual ? this.mapHelper.printMapPlayerAlwaysBottom(0, this.configVariables.UnitInfo) : "", this.p2Manual ? this.mapHelper.printMapPlayerAlwaysBottom(1, this.configVariables.UnitInfo) : ""};
                moves = this.game.playerManager.getPlayerInputsSimultaneous(this.configVariables.waitForever ? 99999999L : this.configVariables.waitTimeManual, this.configVariables.waitForever ? 99999999L : this.configVariables.waitTimeBotMax);
                this.p1stats.totalTimeSpent += moves.get((int)0).timeToMakeMove;
                this.p2stats.totalTimeSpent += moves.get((int)1).timeToMakeMove;
                this.p1stats.dealTimeDamage(moves.get((int)0).timeToMakeMove);
                this.p2stats.dealTimeDamage(moves.get((int)1).timeToMakeMove);
                this.p1stats.dealCrashDamage(this.game.playerManager.checkProcessCrashed(0));
                this.p2stats.dealCrashDamage(this.game.playerManager.checkProcessCrashed(1));
                playerTimes[0] = moves.get((int)0).timeToMakeMove;
                playerTimes[1] = moves.get((int)1).timeToMakeMove;
                Parser.processInputBuild(this, this.game, moves, this.eventDisplayer.getEventAdder(), this.game.getDestroyGameObject(this.gameMainGameObjectKey));
                this.game.gameEngineLoop(this.eventDisplayer, this.configVariables.printHitStrings, this.p1stats, this.p2stats);
                this.phase = GamePhase.DEPLOYPHASE;
            } else if (this.phase == GamePhase.DEPLOYPHASE) {
                Parser.processInputDeploy(this, this.game, moves, this.eventDisplayer.getEventAdder(), this.game.getDestroyGameObject(this.gameMainGameObjectKey));
                this.game.gameEngineLoop(this.eventDisplayer, this.configVariables.printHitStrings, this.p1stats, this.p2stats);
                this.actionFrame = 0;
                this.phase = GamePhase.ACTION;
            } else if (this.phase == GamePhase.ACTION) {
                ++this.totalActionFrames;
                ++this.totalFrameNumber;
                visibleObjects = Game.getSystemObjects(this.game.gameObjects, DisplayUnitsSystem.DisplayUnitSystemComponents::tryCreateDisplayComponents);
                visibles1 = DisplayUnitsSystem.getDisplayUnits(0, visibleObjects, this.configVariables.UnitInfo, this.turn, 6, 7);
                visibles2 = DisplayUnitsSystem.getDisplayUnits(1, visibleObjects, this.configVariables.UnitInfo, this.turn, 6, 7);
                p1String = Parser.parseVisiblesListToJson(this, 0, visibles1, 1, playerTimes[0], playerTimes[1], null, this.configVariables.seasonCompatibilityModeP1 < 5);
                p2String = Parser.parseVisiblesListToJson(this, 1, visibles2, 1, playerTimes[0], playerTimes[1], null, this.configVariables.seasonCompatibilityModeP2 < 5);
                this.game.playerManager.sendGameState(p1String + "\n", 0);
                this.game.playerManager.sendGameState(p2String + "\n", 1);
                this.eventDisplayer.clearEvents();
                if (this.configVariables.printActStrings) {
                    System.out.println(p1String);
                    System.out.println(p2String);
                }
                if (this.p1replay != null) {
                    this.p1replay.addToFile(p1String);
                }
                if (this.p2replay != null) {
                    this.p2replay.addToFile(p2String);
                }
                if (this.configVariables.printMapString) {
                    if (!this.p1Manual && this.p2Manual) {
                        System.out.println(this.mapHelper.printMapPlayerAlwaysBottom(1, this.configVariables.UnitInfo));
                    } else {
                        System.out.println(this.mapHelper.printMapPlayerAlwaysBottom(0, this.configVariables.UnitInfo));
                    }
                }
                boolean walkersAlive = false;
                for (UnitInfoComponent uinfo : this.game.getAllComponents(UnitInfoComponent.class)) {
                    if (uinfo.category != CategoryType.WALKER) continue;
                    walkersAlive = true;
                }
                this.processEndGame(false);
                if (!walkersAlive) {
                    this.phase = GamePhase.BUILDPHASE;
                    float food = this.calculateFoodGivenForTurn(this.turn);
                    float currentMaxFood = this.calculateFoodCap(this.turn);
                    this.p1stats.spoilFood(this.configVariables.bitDecayPerRound);
                    this.p2stats.spoilFood(this.configVariables.bitDecayPerRound);
                    this.p1stats.addToFood(food, currentMaxFood);
                    this.p2stats.addToFood(food, currentMaxFood);
                    this.p1stats.addToMetal(this.configVariables.coresPerRound);
                    this.p2stats.addToMetal(this.configVariables.coresPerRound);
                    this.game.runInvestmentSystem(this.turn, this.p1stats, this.p2stats, this.eventDisplayer);
                    ++this.turn;
                } else {
                    this.game.gameEngineLoop(this.eventDisplayer, this.configVariables.printHitStrings, this.p1stats, this.p2stats);
                    this.game.runRemoveOwnUnitSystem(this.turn, this.p1stats, this.p2stats, this.eventDisplayer);
                    ++this.actionFrame;
                }
            }
            ++i;
        }
        System.out.println("Exited for loop");
    }

    public Game getGame() {
        return this.game;
    }

    public void processEndGame(boolean forceEndGame) {
        if (this.p1stats.totalHP <= 0.0f || this.p2stats.totalHP <= 0.0f || forceEndGame) {
            this.exitGame = true;
            int winningPlayerP1 = 0;
            int winningPlayerP2 = 0;
            if (this.p1stats.totalHP > this.p2stats.totalHP) {
                winningPlayerP1 = 1;
                winningPlayerP2 = 2;
            }
            if (this.p1stats.totalHP < this.p2stats.totalHP) {
                winningPlayerP1 = 2;
                winningPlayerP2 = 1;
            }
            if (this.p1stats.totalHP <= 0.0f && this.p2stats.totalHP <= 0.0f || this.p1stats.totalHP == this.p2stats.totalHP) {
                if (this.p1stats.totalTimeSpent == this.p2stats.totalTimeSpent) {
                    if (this.random.nextBoolean()) {
                        winningPlayerP1 = 1;
                        winningPlayerP2 = 2;
                    } else {
                        winningPlayerP1 = 2;
                        winningPlayerP2 = 1;
                    }
                } else if (this.p1stats.totalTimeSpent < this.p2stats.totalTimeSpent) {
                    winningPlayerP1 = 1;
                    winningPlayerP2 = 2;
                } else {
                    winningPlayerP1 = 2;
                    winningPlayerP2 = 1;
                }
            }
            System.out.println("Winner (p1 perspective, 1 = p1 2 = p2): " + winningPlayerP1);
            float[] metalOnBoardFinal = this.mapHelper.getMetalOnBoard();
            HashMap<String, Object> endOfGameStatsP1Perspective = new HashMap<String, Object>();
            HashMap<String, Object> endOfGameStatsP2Perspective = new HashMap<String, Object>();
            HashMap<String, Object> p1EndStats = new HashMap<String, Object>();
            HashMap<String, Object> p2EndStats = new HashMap<String, Object>();
            p1EndStats.put("dynamic_resource_spent", Float.valueOf(this.p1stats.getFoodSpent()));
            p1EndStats.put("dynamic_resource_destroyed", Float.valueOf(this.p1stats.getFoodSpent() - this.p1stats.foodSurvived));
            p1EndStats.put("dynamic_resource_spoiled", Float.valueOf(this.p1stats.getFoodSpoiled()));
            p1EndStats.put("stationary_resource_spent", Float.valueOf(this.p1stats.getMetalSpent()));
            p1EndStats.put("stationary_resource_left_on_board", Float.valueOf(metalOnBoardFinal[0]));
            p1EndStats.put("points_scored", Float.valueOf(this.configVariables.startingHP - this.p2stats.totalHP));
            p1EndStats.put("crashed", this.game.playerManager.checkProcessCrashed(0) || this.p1stats.getTimeoutDeath());
            p1EndStats.put("total_computation_time", this.p1stats.totalTimeSpent);
            p1EndStats.put("name", this.game.playerManager.getPlayerName(0));
            p1EndStats.put("time_damage_taken", this.p1stats.getTimeoutDamageTaken());
            p1EndStats.put("timeout_death", this.p1stats.getTimeoutDeath());
            p2EndStats.put("dynamic_resource_spent", Float.valueOf(this.p2stats.getFoodSpent()));
            p2EndStats.put("dynamic_resource_destroyed", Float.valueOf(this.p2stats.getFoodSpent() - this.p2stats.foodSurvived));
            p2EndStats.put("dynamic_resource_spoiled", Float.valueOf(this.p2stats.getFoodSpoiled()));
            p2EndStats.put("stationary_resource_spent", Float.valueOf(this.p2stats.getMetalSpent()));
            p2EndStats.put("stationary_resource_left_on_board", Float.valueOf(metalOnBoardFinal[1]));
            p2EndStats.put("points_scored", Float.valueOf(this.configVariables.startingHP - this.p1stats.totalHP));
            p2EndStats.put("crashed", this.game.playerManager.checkProcessCrashed(1) || this.p2stats.getTimeoutDeath());
            p2EndStats.put("total_computation_time", this.p2stats.totalTimeSpent);
            p2EndStats.put("name", this.game.playerManager.getPlayerName(1));
            p2EndStats.put("time_damage_taken", this.p2stats.getTimeoutDamageTaken());
            p2EndStats.put("timeout_death", this.p2stats.getTimeoutDeath());
            long totalTime = System.currentTimeMillis() - this.startTime;
            endOfGameStatsP1Perspective.put("player1", p1EndStats);
            endOfGameStatsP1Perspective.put("player2", p2EndStats);
            endOfGameStatsP1Perspective.put("duration", totalTime);
            endOfGameStatsP1Perspective.put("turns", this.turn);
            endOfGameStatsP1Perspective.put("frames", this.totalActionFrames);
            endOfGameStatsP1Perspective.put("winner", winningPlayerP1);
            endOfGameStatsP2Perspective.put("player1", p2EndStats);
            endOfGameStatsP2Perspective.put("player2", p1EndStats);
            endOfGameStatsP2Perspective.put("duration", totalTime);
            endOfGameStatsP2Perspective.put("turns", this.turn);
            endOfGameStatsP2Perspective.put("frames", this.totalActionFrames);
            endOfGameStatsP2Perspective.put("winner", winningPlayerP2);
            ArrayList<DisplayUnitsSystem.DisplayUnitSystemComponents> visibleObjects = Game.getSystemObjects(this.game.gameObjects, DisplayUnitsSystem.DisplayUnitSystemComponents::tryCreateDisplayComponents);
            ArrayList<Object[]> visibles1 = DisplayUnitsSystem.getDisplayUnits(0, visibleObjects, this.configVariables.UnitInfo, this.turn, 6, 7);
            ArrayList<Object[]> visibles2 = DisplayUnitsSystem.getDisplayUnits(1, visibleObjects, this.configVariables.UnitInfo, this.turn, 6, 7);
            String p1String = Parser.parseVisiblesListToJson(this, 0, visibles1, 2, 0L, 0L, endOfGameStatsP1Perspective, this.configVariables.seasonCompatibilityModeP1 < 5);
            String p2String = Parser.parseVisiblesListToJson(this, 1, visibles2, 2, 0L, 0L, endOfGameStatsP2Perspective, this.configVariables.seasonCompatibilityModeP2 < 5);
            this.eventDisplayer.clearEvents();
            try {
                this.game.playerManager.sendGameState(p1String + "\n", 0);
                this.game.playerManager.sendGameState(p2String + "\n", 1);
                if (this.p1replay != null) {
                    this.p1replay.addToFile(p1String);
                }
                if (this.p2replay != null) {
                    this.p2replay.addToFile(p2String);
                }
            }
            catch (Exception e) {
                System.out.println("Couldn't send gamestate on end game");
            }
            if (winningPlayerP1 == 0 && this.configVariables.printPlayerInputStrings) {
                System.out.println("GAME OVER AND IT'S A DRAW!");
            } else if (this.configVariables.printPlayerInputStrings) {
                System.out.println("PLAYER " + winningPlayerP1 + " WINS!");
            }
            try {
                System.out.println("Waiting to exit finished game.");
                this.game.playerManager.onGameEnd();
                Thread.sleep(this.configVariables.waitTimeEndGame);
                this.game.playerManager.onGameEnd();
                System.out.println("Done waiting now exiting finished game.");
                if (this.workerMode) {
                    System.out.println("Hard Exiting Now");
                    this.game.playerManager.closeAllPlayers();
                }
            }
            catch (Exception e) {
                System.out.println("Couldn't sleep at end of game?");
                System.exit(1);
            }
        }
    }

    public static String getTimeNow() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    public float calculateFoodGivenForTurn(int turn) {
        if (turn + 1 < this.configVariables.roundStartBitRamp) {
            return this.configVariables.bitsPerRound;
        }
        int foodIntervals = (turn + 1 - this.configVariables.roundStartBitRamp) / this.configVariables.turnIntervalForBitSchedule + 1;
        return this.configVariables.bitsPerRound + (float)foodIntervals * this.configVariables.bitGrowthRate;
    }

    public float calculateFoodCap(int turn) {
        if (turn + 1 < this.configVariables.roundStartBitRamp) {
            return this.configVariables.maxBits;
        }
        int foodIntervals = (turn + 1 - this.configVariables.roundStartBitRamp) / this.configVariables.turnIntervalForBitCapSchedule + 1;
        float ret = this.configVariables.maxBits + (float)foodIntervals * this.configVariables.bitRampBitCapGrowthRate;
        return ret;
    }

    public class GameMainGameObjectKey {
        private GameMainGameObjectKey() {
        }
    }
}

