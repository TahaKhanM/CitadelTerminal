/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game;

import com.c1games.terminal.game.GameMain;
import com.c1games.terminal.game.PlayerStats;
import com.c1games.terminal.game.events.globalevents.EventDisplayer;
import com.c1games.terminal.game.gameobject.Gameobject;
import com.c1games.terminal.game.gameobject.components.ColliderComponent;
import com.c1games.terminal.game.gameobject.components.Component;
import com.c1games.terminal.game.gameobject.components.HealthComponent;
import com.c1games.terminal.game.player.Player;
import com.c1games.terminal.game.player.PlayerManager;
import com.c1games.terminal.game.systems.BreachSystem;
import com.c1games.terminal.game.systems.CollisionSystem;
import com.c1games.terminal.game.systems.InvestmentSystem;
import com.c1games.terminal.game.systems.NavigateToEdgeSystem;
import com.c1games.terminal.game.systems.RemoveOwnUnitSystem;
import com.c1games.terminal.game.systems.SelfDestructSystem;
import com.c1games.terminal.game.systems.ShieldSystem;
import com.c1games.terminal.game.systems.TargetAndAttackSystem;
import com.c1games.terminal.game.systems.map.Map;
import com.c1games.terminal.game.systems.map.MapBounds;
import com.c1games.terminal.game.systems.map.path.PathFinder;
import com.c1games.terminal.game.systems.map.structure.Coords;
import com.c1games.terminal.game.systems.proximity.ProximityArena;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.function.Consumer;

public class Game {
    public ProximityArena<ColliderComponent> proximityArena;
    private Long idCount = 0L;
    public ArrayList<Gameobject> gameObjects;
    public ArrayList<Gameobject> newGameObjects;
    private Hashtable<String, ArrayList<Component>> newComponents;
    public ArrayList<Gameobject> toDestroy;
    public ArrayList<Component> toDestroyComponents;
    public Map map;
    public final MapBounds bounds;
    public PlayerManager playerManager;
    public PathFinder topLeft;
    public PathFinder topRight;
    public PathFinder bottomLeft;
    public PathFinder bottomRight;
    private GameObjectKey gameObjectKey = new GameObjectKey();

    public Game(Player[] players, int xSize, int ySize, boolean printIOErrors) {
        this.map = new Map(this, xSize, ySize);
        assert (xSize == ySize);
        this.bounds = new MapBounds(xSize);
        this.proximityArena = new ProximityArena(xSize);
        this.gameObjects = new ArrayList();
        this.newGameObjects = new ArrayList();
        this.newComponents = new Hashtable();
        this.toDestroy = new ArrayList();
        this.toDestroyComponents = new ArrayList();
        this.playerManager = new PlayerManager(players, printIOErrors);
        int[] direction = new int[]{-1, 1};
        this.topLeft = this.initializePathfinder("topLeft", direction);
        int[] direction2 = new int[]{1, 1};
        this.topRight = this.initializePathfinder("topRight", direction2);
        int[] direction3 = new int[]{-1, -1};
        this.bottomLeft = this.initializePathfinder("bottomLeft", direction3);
        int[] direction4 = new int[]{1, -1};
        this.bottomRight = this.initializePathfinder("bottomRight", direction4);
    }

    /*
     * Exception decompiling
     */
    private PathFinder initializePathfinder(String name, int[] direction) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * java.lang.UnsupportedOperationException
         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.NewAnonymousArray.getDimSize(NewAnonymousArray.java:142)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.isNewArrayLambda(LambdaRewriter.java:455)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.rewriteDynamicExpression(LambdaRewriter.java:409)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.rewriteDynamicExpression(LambdaRewriter.java:167)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.rewriteExpression(LambdaRewriter.java:105)
         *     at org.benf.cfr.reader.bytecode.analysis.parse.rewriters.ExpressionRewriterHelper.applyForwards(ExpressionRewriterHelper.java:12)
         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractMemberFunctionInvokation.applyExpressionRewriterToArgs(AbstractMemberFunctionInvokation.java:101)
         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractMemberFunctionInvokation.applyExpressionRewriter(AbstractMemberFunctionInvokation.java:88)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.rewriteExpression(LambdaRewriter.java:103)
         *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractMemberFunctionInvokation.applyExpressionRewriter(AbstractMemberFunctionInvokation.java:87)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.rewriteExpression(LambdaRewriter.java:103)
         *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredAssignment.rewriteExpressions(StructuredAssignment.java:146)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.rewrite(LambdaRewriter.java:88)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.rewriteLambdas(Op04StructuredStatement.java:1137)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:912)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    public PathFinder getPathFinderFromInitialPosition(Coords position) {
        int[] direction = new int[]{0, 0};
        direction[0] = position.x > 13 ? -1 : 1;
        direction[1] = position.y > 13 ? -1 : 1;
        PathFinder pathfinder = direction[0] == 1 && direction[1] == 1 ? this.topRight : (direction[0] == 1 && direction[1] == -1 ? this.bottomRight : (direction[0] == -1 && direction[1] == 1 ? this.topLeft : this.bottomLeft));
        return pathfinder;
    }

    public void addNewComponent(Component component) {
        if (this.newComponents.containsKey(component.gameobject.toString())) {
            this.newComponents.get(component.gameobject.toString()).add(component);
        } else {
            ArrayList<Component> ac = new ArrayList<Component>();
            ac.add(component);
            this.newComponents.put(component.gameobject.toString(), ac);
        }
    }

    public ArrayList<Component> getNewComponents(Gameobject gameobject) {
        if (this.newComponents.containsKey(gameobject.toString())) {
            return this.newComponents.get(gameobject.toString());
        }
        return new ArrayList<Component>();
    }

    public String getNewID() {
        Long l = this.idCount;
        Long l2 = this.idCount = Long.valueOf(this.idCount + 1L);
        return "" + this.idCount;
    }

    public <T> ArrayList<T> getAllComponents(Class<T> c) {
        ArrayList<T> ret = new ArrayList<T>();
        for (Gameobject g : this.gameObjects) {
            for (T v : g.getComponents(c)) {
                ret.add(v);
            }
        }
        for (Gameobject g : this.newGameObjects) {
            for (T v : g.getComponents(c)) {
                ret.add(v);
            }
        }
        return ret;
    }

    public static <T> ArrayList<T> getSystemObjects(ArrayList<Gameobject> gameObjects, ProcessGameObject processGameObject) {
        ArrayList<Object> toProcess = new ArrayList<Object>();
        for (Gameobject g : gameObjects) {
            Object t = processGameObject.processGameObject(g);
            if (t == null) continue;
            toProcess.add(t);
        }
        return toProcess;
    }

    public Consumer<Gameobject> getDestroyGameObject(GameMain.GameMainGameObjectKey key) {
        return Gameobject.destroyGameObject(this.gameObjectKey);
    }

    public void gameEngineLoop(EventDisplayer eventDisplayer, Boolean printHitStrings, PlayerStats p1stats, PlayerStats p2stats) {
        this.clearDestroyedGameObjects();
        NavigateToEdgeSystem.moveComponents(Game.getSystemObjects(this.gameObjects, NavigateToEdgeSystem.MovementComponents::tryCreateMovementComponents), eventDisplayer.getEventAdder());
        CollisionSystem.runCollisionSystem(this.proximityArena, this);
        ShieldSystem.processShieldDecay(this.getAllComponents(HealthComponent.class));
        ShieldSystem.processShieldGiveSystem(Game.getSystemObjects(this.gameObjects, ShieldSystem.ShieldSystemComponents::tryCreateShieldSystemComponents), eventDisplayer.getEventAdder());
        BreachSystem.breachProcess(Game.getSystemObjects(this.gameObjects, BreachSystem.BreachSystemComponents::tryCreateBreachSystemComponents), p1stats, p2stats, Gameobject.destroyGameObject(this.gameObjectKey), Gameobject.disableGameObject(this.gameObjectKey), eventDisplayer.getEventAdder(), eventDisplayer.getEventAdder());
        SelfDestructSystem.processSelfDestructs(Game.getSystemObjects(this.gameObjects, SelfDestructSystem.SelfDestructAttackerComponents::tryCreateSelfDestructComponents), Gameobject.destroyGameObject(this.gameObjectKey), eventDisplayer.getEventAdder(), eventDisplayer.getEventAdder(), eventDisplayer.getEventAdder(), printHitStrings);
        ArrayList<ArrayList> attacksLists = Game.getSystemObjects(this.gameObjects, TargetAndAttackSystem.AttackerRelatedComponents::tryCreateAttackTargetingComponents);
        ArrayList<TargetAndAttackSystem.AttackerRelatedComponents> attacks = new ArrayList<TargetAndAttackSystem.AttackerRelatedComponents>();
        for (ArrayList attackList : attacksLists) {
            attacks.addAll(attackList);
        }
        TargetAndAttackSystem.processTargeting(attacks, eventDisplayer.getEventAdder(), eventDisplayer.getEventAdder(), eventDisplayer.getEventAdder(), Gameobject.destroyGameObject(this.gameObjectKey), printHitStrings);
        this.clearDestroyedGameObjects();
    }

    private void clearDestroyedGameObjects() {
        for (Component c : this.toDestroyComponents) {
            c.destroyOnlyThisComponent();
        }
        for (Gameobject g : this.toDestroy) {
            g.destroyThisGameobject();
        }
        this.toDestroyComponents.clear();
        this.toDestroy.clear();
    }

    public void runRemoveOwnUnitSystem(int turn, PlayerStats p1, PlayerStats p2, EventDisplayer eventDisplayer) {
        RemoveOwnUnitSystem.removeOwnUnitProcess(Game.getSystemObjects(this.gameObjects, RemoveOwnUnitSystem.RemoveOwnUnitSystemComponents::tryCreateRemovalSystemComponents), turn, p1, p2, Gameobject.destroyGameObject(this.gameObjectKey), eventDisplayer.getEventAdder());
        this.clearDestroyedGameObjects();
    }

    public void runInvestmentSystem(int turn, PlayerStats p1, PlayerStats p2, EventDisplayer eventDisplayer) {
        InvestmentSystem.investmentProcess(Game.getSystemObjects(this.gameObjects, InvestmentSystem.InvestmentSystemComponents::tryCreateInvestmentSystemComponents), p1, p2);
    }

    private void transferNewObjects() {
        this.gameObjects.addAll(this.newGameObjects);
        this.newGameObjects.clear();
        for (ArrayList<Component> cl : this.newComponents.values()) {
            for (Component c : cl) {
                c.gameobject.addComponent(c);
            }
        }
        this.newComponents.clear();
    }

    public static interface ProcessGameObject {
        public Object processGameObject(Gameobject var1);
    }

    public class GameObjectKey {
        private GameObjectKey() {
        }
    }
}

