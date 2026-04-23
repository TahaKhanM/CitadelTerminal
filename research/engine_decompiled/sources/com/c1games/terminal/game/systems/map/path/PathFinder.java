/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems.map.path;

import com.c1games.terminal.game.systems.map.path.CoordQueue;
import java.util.List;

public final class PathFinder {
    private static final byte INVALID = 0;
    private static final byte OPEN = 1;
    private static final byte WALL = 2;
    private static final byte NONE = 0;
    private static final byte LEFT = 1;
    private static final byte RIGHT = 2;
    private static final byte DOWN = 3;
    private static final byte UP = 4;
    private static final int NUM_NEIGHBORS = 4;
    private static final int[] NEIGHBOR_X = new int[]{1, -1, 0, 0};
    private static final int[] NEIGHBOR_Y = new int[]{0, 0, 1, -1};
    private static final byte[] PARENT_DIRECTION = new byte[]{1, 2, 3, 4};
    private final int dimension;
    private final String name;
    private final int size;
    private final int[] direction;
    private final List<int[]> perfectList;
    private byte[] status;
    private short[] parentDirection;
    private short[] pathlength;
    private int[] idealness;
    private int[] visited;
    private boolean[] explored;
    private boolean[] perfectLookup;
    private CoordQueue currentQueue;
    private CoordQueue frontierQueue;
    private CoordQueue startSearchAt;
    private int searchedBestIdealness;
    private int searchedCurrPathlength;
    boolean boardInvalid;
    private CoordQueue requiresValidation;
    private CoordQueue possibleSteps;

    public PathFinder(String name, int dimension, int[] direction, List<int[]> walls, List<int[]> perfect) {
        this.name = name;
        this.dimension = dimension;
        this.size = dimension * dimension;
        this.direction = direction;
        this.perfectList = perfect;
        this.status = new byte[this.size];
        this.parentDirection = new short[this.size];
        this.pathlength = new short[this.size];
        this.idealness = new int[this.size];
        this.visited = new int[this.size];
        this.explored = new boolean[this.size];
        this.perfectLookup = new boolean[this.size];
        this.currentQueue = new CoordQueue();
        this.frontierQueue = new CoordQueue();
        this.startSearchAt = new CoordQueue();
        this.boardInvalid = true;
        this.requiresValidation = new CoordQueue();
        this.possibleSteps = new CoordQueue();
        for (int i = 0; i < dimension / 2; ++i) {
            for (int j = 0; j < dimension / 2; ++j) {
                if (i + j >= dimension / 2 - 1) continue;
                this.status[this.index((int)i, (int)j)] = 2;
                this.status[this.index((int)(dimension - i - 1), (int)j)] = 2;
                this.status[this.index((int)(dimension - i - 1), (int)(dimension - j - 1))] = 2;
                this.status[this.index((int)i, (int)(dimension - j - 1))] = 2;
            }
        }
        for (int[] coords : perfect) {
            this.perfectLookup[this.index((int)coords[0], (int)coords[1])] = true;
            this.status[this.index((int)coords[0], (int)coords[1])] = 1;
            this.pathlength[this.index((int)coords[0], (int)coords[1])] = 0;
            this.idealness[this.index((int)coords[0], (int)coords[1])] = Integer.MAX_VALUE;
        }
        for (int[] coords : walls) {
            this.status[this.index((int)coords[0], (int)coords[1])] = 2;
        }
    }

    public void put(int targetX, int targetY) {
        if (this.status[this.index(targetX, targetY)] == 2) {
            return;
        }
        this.status[this.index((int)targetX, (int)targetY)] = 2;
        for (int i = 0; i < 4; ++i) {
            int neighborX = NEIGHBOR_X[i] + targetX;
            int neighborY = NEIGHBOR_Y[i] + targetY;
            if (this.status[this.index(neighborX, neighborY)] != 1) continue;
            this.currentQueue.push(neighborX, neighborY);
        }
        this.zero(this.explored);
        while (this.currentQueue.next()) {
            int neighborY;
            int neighborX;
            int i;
            int cellX = this.currentQueue.currX;
            int cellY = this.currentQueue.currY;
            if (this.status[this.index(cellX, cellY)] == 1 && this.pathlength[this.index(cellX, cellY)] == 0) continue;
            boolean found = false;
            for (i = 0; i < 4; ++i) {
                neighborX = NEIGHBOR_X[i] + cellX;
                neighborY = NEIGHBOR_Y[i] + cellY;
                if (this.status[this.index(neighborX, neighborY)] != 1 || this.pathlength[this.index(neighborX, neighborY)] != this.pathlength[this.index(cellX, cellY)] - 1) continue;
                found = true;
                break;
            }
            if (found) continue;
            this.status[this.index((int)cellX, (int)cellY)] = 0;
            for (i = 0; i < 4; ++i) {
                neighborX = NEIGHBOR_X[i] + cellX;
                neighborY = NEIGHBOR_Y[i] + cellY;
                if (this.status[this.index(neighborX, neighborY)] != 1 || this.explored[this.index(neighborX, neighborY)]) continue;
                this.explored[this.index((int)neighborX, (int)neighborY)] = true;
                this.currentQueue.push(neighborX, neighborY);
            }
        }
    }

    public void remove(int targetX, int targetY) {
        if (this.status[this.index(targetX, targetY)] != 2) {
            return;
        }
        if (this.idealness(targetX, targetY) == Integer.MAX_VALUE) {
            this.status[this.index((int)targetX, (int)targetY)] = 1;
            this.pathlength[this.index((int)targetX, (int)targetY)] = 0;
            this.idealness[this.index((int)targetX, (int)targetY)] = Integer.MAX_VALUE;
        } else {
            this.status[this.index((int)targetX, (int)targetY)] = 0;
        }
        if (!this.boardInvalid) {
            this.boardInvalid = true;
            for (int i = 0; i < this.dimension; ++i) {
                for (int j = 0; j < this.dimension; ++j) {
                    if (this.status[this.index(i, j)] != 1 || this.idealness(i, j) == Integer.MAX_VALUE) continue;
                    this.status[this.index((int)i, (int)j)] = 0;
                }
            }
        }
    }

    private void validate(CoordQueue targets) {
        while (targets.next()) {
            int targetX = targets.currX;
            int targetY = targets.currY;
            if (this.status[this.index(targetX, targetY)] == 1) continue;
            this.boardInvalid = false;
            this.idealnessSearch(targetX, targetY);
            short currentPathLength = (short)this.searchedCurrPathlength;
            this.currentQueue.drain();
            this.frontierQueue.drain();
            while (this.startSearchAt.next()) {
                this.currentQueue.push(this.startSearchAt.currX, this.startSearchAt.currY);
            }
            boolean debug = false;
            this.zero(this.explored);
            while (this.currentQueue.size() != 0) {
                while (this.currentQueue.next()) {
                    int cellX = this.currentQueue.currX;
                    int cellY = this.currentQueue.currY;
                    int cellIndex = this.index(cellX, cellY);
                    if (this.status[cellIndex] == 1 && this.pathlength[cellIndex] < currentPathLength) continue;
                    this.idealness[cellIndex] = this.searchedBestIdealness;
                    this.status[cellIndex] = 1;
                    this.pathlength[cellIndex] = currentPathLength;
                    if (cellIndex == this.index(targetX, targetY)) {
                        this.currentQueue.drain();
                        this.frontierQueue.drain();
                        break;
                    }
                    short mask = this.parentDirection[this.index(cellX, cellY)];
                    if (PathFinder.isDirectionInMap(mask, (short)1)) {
                        this.handleParent(cellX - 1, cellY);
                    }
                    if (PathFinder.isDirectionInMap(mask, (short)4)) {
                        this.handleParent(cellX, cellY + 1);
                    }
                    if (PathFinder.isDirectionInMap(mask, (short)2)) {
                        this.handleParent(cellX + 1, cellY);
                    }
                    if (!PathFinder.isDirectionInMap(mask, (short)3)) continue;
                    this.handleParent(cellX, cellY - 1);
                }
                currentPathLength = (short)(currentPathLength + 1);
                this.swapQueues();
            }
        }
    }

    private void handleParent(int x, int y) {
        if (!this.explored[this.index(x, y)]) {
            this.explored[this.index((int)x, (int)y)] = true;
            this.frontierQueue.push(x, y);
        }
    }

    private int idealness(int x, int y) {
        if (this.perfectLookup[this.index(x, y)]) {
            return Integer.MAX_VALUE;
        }
        int xIdealness = this.direction[0] == 1 ? x : this.dimension - x - 1;
        int yIdealness = this.direction[1] == 1 ? y : this.dimension - y - 1;
        assert ((xIdealness & 0xFFFF0000) == 0);
        assert ((yIdealness & 0xFFFF0000) == 0);
        return yIdealness << 16 | xIdealness;
    }

    private void extractIdealnessCoords(int idealness, CoordQueue into) {
        if (idealness == Integer.MAX_VALUE) {
            for (int[] coords : this.perfectList) {
                into.push(coords[0], coords[1]);
            }
        } else {
            int xIdealness = idealness & 0xFFFF;
            int yIdealness = (idealness & 0xFFFF0000) >> 16;
            int x = this.direction[0] == 1 ? xIdealness : this.dimension - xIdealness;
            int y = this.direction[1] == 1 ? yIdealness : this.dimension - yIdealness;
            into.push(x, y);
        }
    }

    private int getMinDistanceFromIdealness(int idealness, int x, int y) {
        CoordQueue targets = new CoordQueue();
        this.extractIdealnessCoords(idealness, targets);
        int debugx = 0;
        int debugy = 0;
        int bestDistance = Integer.MAX_VALUE;
        int numTargets = targets.size();
        for (int i = 0; i < numTargets; ++i) {
            targets.next();
            int distance = Math.abs(targets.currX - x) + Math.abs(targets.currY - y);
            if (distance < bestDistance) {
                debugx = targets.currX;
                debugy = targets.currY;
                bestDistance = distance;
            }
            targets.push(targets.currX, targets.currY);
        }
        return bestDistance;
    }

    private void idealnessSearch(int targetX, int targetY) {
        this.zero(this.explored);
        this.zero(this.visited);
        this.zero(this.parentDirection);
        this.startSearchAt.drain();
        this.searchedBestIdealness = Integer.MIN_VALUE;
        this.searchedCurrPathlength = 0;
        this.currentQueue.push(targetX, targetY);
        this.explored[this.index((int)targetX, (int)targetY)] = true;
        int distanceExplored = 0;
        int currentBest = Integer.MAX_VALUE;
        boolean openFound = false;
        this.visited[this.index((int)targetX, (int)targetY)] = 0;
        while (this.currentQueue.size() != 0) {
            while (this.currentQueue.next()) {
                int cellY;
                int cellX;
                block7: {
                    block5: {
                        int currentPathlength;
                        block6: {
                            cellX = this.currentQueue.currX;
                            cellY = this.currentQueue.currY;
                            int currentIdealness = this.idealness[this.index(cellX, cellY)];
                            if (openFound && (this.status[this.index(cellX, cellY)] != 0 ? this.status[this.index(cellX, cellY)] == 1 && this.pathlength[this.index(cellX, cellY)] + distanceExplored > currentBest : this.getMinDistanceFromIdealness(this.searchedBestIdealness, cellX, cellY) + distanceExplored >= currentBest)) continue;
                            if (this.status[this.index(cellX, cellY)] != 1) break block5;
                            currentPathlength = this.pathlength[this.index(cellX, cellY)];
                            if (openFound && distanceExplored + currentPathlength >= currentBest) break block6;
                            openFound = true;
                            this.searchedBestIdealness = currentIdealness;
                            this.startSearchAt.drain();
                            this.startSearchAt.push(cellX, cellY);
                            this.searchedCurrPathlength = currentPathlength;
                            currentBest = currentPathlength + distanceExplored;
                            break block7;
                        }
                        if (distanceExplored + currentPathlength != currentBest) break block7;
                        this.startSearchAt.push(cellX, cellY);
                        break block7;
                    }
                    for (int i = 0; i < 4; ++i) {
                        int neighborX = NEIGHBOR_X[i] + cellX;
                        int neighborY = NEIGHBOR_Y[i] + cellY;
                        if (distanceExplored < this.visited[this.index(neighborX, neighborY)] && this.status[this.index(neighborX, neighborY)] != 2) {
                            this.parentDirection[this.index((int)neighborX, (int)neighborY)] = PathFinder.addDirectionToMask(this.parentDirection[this.index(neighborX, neighborY)], PARENT_DIRECTION[i]);
                            this.visited[this.index((int)neighborX, (int)neighborY)] = distanceExplored + 1;
                        }
                        if (this.explored[this.index(neighborX, neighborY)] || this.status[this.index(neighborX, neighborY)] == 2) continue;
                        this.frontierQueue.push(neighborX, neighborY);
                        this.explored[this.index((int)neighborX, (int)neighborY)] = true;
                    }
                }
                if (openFound) continue;
                int cellIdealness = this.idealness(cellX, cellY);
                if (cellIdealness > this.searchedBestIdealness) {
                    this.startSearchAt.drain();
                    this.startSearchAt.push(cellX, cellY);
                    this.searchedBestIdealness = cellIdealness;
                    continue;
                }
                if (cellIdealness != this.searchedBestIdealness) continue;
                this.startSearchAt.push(cellX, cellY);
            }
            ++distanceExplored;
            this.swapQueues();
        }
    }

    public int index(int x, int y) {
        if (x < 0 || y < 0 || y >= this.dimension || x >= this.dimension) {
            return 0;
        }
        return y * this.dimension + x;
    }

    private void swapQueues() {
        CoordQueue temp = this.currentQueue;
        this.currentQueue = this.frontierQueue;
        this.frontierQueue = temp;
    }

    private void zero(boolean[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = false;
        }
    }

    private void zero(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = Integer.MAX_VALUE;
        }
    }

    private void zero(short[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = 0;
        }
    }

    /*
     * WARNING - void declaration
     */
    public int[] getStep(int unitX, int unitY, int prevdirection) {
        short s;
        int stepY;
        int stepX;
        int i;
        void var7_8;
        int SPAWNED = 0;
        int HORIZONTAL = 1;
        int VERTICAL = 2;
        this.requiresValidation.drain();
        this.possibleSteps.drain();
        this.requiresValidation.push(unitX, unitY);
        this.possibleSteps.push(unitX, unitY);
        boolean bl = false;
        while (var7_8 < 4) {
            int neighborX = NEIGHBOR_X[var7_8] + unitX;
            int neighborY = NEIGHBOR_Y[var7_8] + unitY;
            if (this.status[this.index(neighborX, neighborY)] == 0) {
                this.requiresValidation.push(neighborX, neighborY);
            }
            if (this.status[this.index(neighborX, neighborY)] != 2) {
                this.possibleSteps.push(neighborX, neighborY);
            }
            ++var7_8;
        }
        this.validate(this.requiresValidation);
        int n = Integer.MAX_VALUE;
        int numPossibleSteps = this.possibleSteps.size();
        for (i = 0; i < numPossibleSteps; ++i) {
            this.possibleSteps.next();
            stepX = this.possibleSteps.currX;
            stepY = this.possibleSteps.currY;
            if (this.pathlength[this.index(stepX, stepY)] > s) continue;
            s = this.pathlength[this.index(stepX, stepY)];
            this.possibleSteps.push(stepX, stepY);
        }
        numPossibleSteps = this.possibleSteps.size();
        for (i = 0; i < numPossibleSteps; ++i) {
            this.possibleSteps.next();
            stepX = this.possibleSteps.currX;
            stepY = this.possibleSteps.currY;
            if (this.pathlength[this.index(stepX, stepY)] != s) continue;
            this.possibleSteps.push(stepX, stepY);
        }
        if (this.possibleSteps.size() > 1) {
            numPossibleSteps = this.possibleSteps.size();
            for (i = 0; i < numPossibleSteps; ++i) {
                this.possibleSteps.next();
                stepX = this.possibleSteps.currX;
                stepY = this.possibleSteps.currY;
                if (prevdirection == VERTICAL && stepX == unitX || prevdirection == HORIZONTAL && stepY == unitY || prevdirection == SPAWNED && stepY == unitY) continue;
                this.possibleSteps.push(stepX, stepY);
            }
        }
        if (this.possibleSteps.size() > 1) {
            numPossibleSteps = this.possibleSteps.size();
            for (i = 0; i < numPossibleSteps; ++i) {
                this.possibleSteps.next();
                stepX = this.possibleSteps.currX;
                stepY = this.possibleSteps.currY;
                if (this.direction[0] + unitX != stepX && this.direction[1] + unitY != stepY) continue;
                this.possibleSteps.push(stepX, stepY);
            }
        }
        this.possibleSteps.next();
        int[] result = new int[]{this.possibleSteps.currX, this.possibleSteps.currY};
        return result;
    }

    public String justifyFormat(int n, int width) {
        StringBuilder builder = new StringBuilder();
        String nAsString = Integer.toString(n);
        for (int i = 0; i < width - nAsString.length(); ++i) {
            builder.append(' ');
        }
        builder.append(nAsString);
        return builder.toString();
    }

    public void drawMap() {
        int i;
        System.out.println("direction=(" + this.direction[0] + ", " + this.direction[1] + ") name=" + this.name);
        String WALL_TEXT = " []";
        String INVALID_TEXT = "   ";
        for (i = this.dimension - 1; i >= 0; --i) {
            System.out.print(this.justifyFormat(i, 3));
            for (int j = 0; j < this.dimension; ++j) {
                if (this.status[this.index(j, i)] == 2) {
                    System.out.print(WALL_TEXT);
                    continue;
                }
                if (this.status[this.index(j, i)] == 0) {
                    System.out.print(INVALID_TEXT);
                    continue;
                }
                System.out.print(this.justifyFormat(this.pathlength[this.index(j, i)], 3));
            }
            System.out.println();
        }
        System.out.print("   ");
        for (i = 0; i < this.dimension; ++i) {
            System.out.print(this.justifyFormat(i, 3));
        }
        System.out.println();
    }

    private static short addDirectionToMask(short currentMask, byte newDirection) {
        int i;
        for (i = 0; i < 4 && (currentMask & 7 << i * 3) != 0; ++i) {
        }
        assert (i < 4);
        return (short)(currentMask | newDirection << i * 3);
    }

    private static byte getDirectionFromMask(short mask, int index) {
        return (byte)((mask & 7 << index * 3) >> index * 3);
    }

    private static boolean isDirectionInMap(short mask, short direction) {
        for (int i = 0; i < 4; ++i) {
            if (PathFinder.getDirectionFromMask(mask, i) != direction) continue;
            return true;
        }
        return false;
    }

    public static void directionMaskTest() {
        short mask = 0;
        mask = PathFinder.addDirectionToMask(mask, (byte)4);
        mask = PathFinder.addDirectionToMask(mask, (byte)3);
        mask = PathFinder.addDirectionToMask(mask, (byte)0);
        mask = PathFinder.addDirectionToMask(mask, (byte)1);
        assert (PathFinder.getDirectionFromMask(mask, 0) == 4);
        assert (PathFinder.getDirectionFromMask(mask, 1) == 3);
        assert (PathFinder.getDirectionFromMask(mask, 2) == 1);
        assert (PathFinder.getDirectionFromMask(mask, 3) == 0);
    }

    private void printForTopRight(String message, boolean drawmap) {
        if (this.direction[0] == 1 && this.direction[1] == 1) {
            System.out.println(message);
            if (drawmap) {
                this.drawMap();
            }
        }
    }
}
