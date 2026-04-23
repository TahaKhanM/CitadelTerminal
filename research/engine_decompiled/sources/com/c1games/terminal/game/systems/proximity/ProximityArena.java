/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems.proximity;

import com.c1games.terminal.game.systems.map.structure.Coords;
import com.c1games.terminal.game.systems.map.structure.Grid;
import com.c1games.terminal.game.systems.proximity.Pair;
import com.c1games.terminal.game.systems.proximity.ProximitySensor;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class ProximityArena<U> {
    private final Grid<Set<ProximitySensor<U>>> sensorGrid;

    public ProximityArena(int size2) {
        this.sensorGrid = new Grid(size2, size2);
        for (Coords c : this.sensorGrid.allCoords()) {
            this.sensorGrid.set(c, new HashSet());
        }
    }

    public void addSensor(ProximitySensor<U> sensor) {
        for (Coords cRelative : sensor.gridMask.from().until(sensor.gridMask.until())) {
            Set<ProximitySensor<U>> sensorSet;
            Coords cAbsolute = cRelative.plus(sensor.location);
            if (!sensor.gridMask.get(cRelative) || (sensorSet = this.sensorGrid.getIfInBounds(cAbsolute)) == null) continue;
            sensorSet.add(sensor);
        }
    }

    public void removeSensor(ProximitySensor<U> sensor) {
        for (Coords cRelative : sensor.gridMask.from().until(sensor.gridMask.until())) {
            Set<ProximitySensor<U>> sensorSet;
            Coords cAbsolute = cRelative.plus(sensor.location);
            if (!sensor.gridMask.get(cRelative) || (sensorSet = this.sensorGrid.getIfInBounds(cAbsolute)) == null) continue;
            sensorSet.remove(sensor);
        }
    }

    public Set<Pair<ProximitySensor<U>>> getCollisions() {
        HashSet<Pair<ProximitySensor<U>>> collisions = new HashSet<Pair<ProximitySensor<U>>>();
        for (Coords c : this.sensorGrid.allCoords()) {
            for (ProximitySensor<U> sensor1 : this.sensorGrid.get(c)) {
                for (ProximitySensor<U> sensor2 : this.sensorGrid.get(c)) {
                    if (sensor1 == sensor2 || !sensor1.isHitBy(sensor2)) continue;
                    collisions.add(new Pair<ProximitySensor<U>>(sensor1, sensor2));
                }
            }
        }
        return collisions;
    }

    public void print(PrintStream out) {
        int i;
        int x;
        out.print("   ");
        for (x = 0; x < this.sensorGrid.xLen; ++x) {
            out.print(x);
            for (i = 0; i < 6 - Integer.toString(x).length(); ++i) {
                out.print(' ');
            }
        }
        out.println();
        for (int y = this.sensorGrid.yLen - 1; y >= 0; --y) {
            out.print(y);
            for (i = 0; i < 2 - Integer.toString(y).length(); ++i) {
                out.print(' ');
            }
            out.print(':');
            for (int x2 = 0; x2 < this.sensorGrid.xLen; ++x2) {
                out.print('[');
                Set<ProximitySensor<U>> set = this.sensorGrid.get(new Coords(x2, y));
                for (ProximitySensor<U> sensor : set) {
                    out.print(sensor.identityChar() + "`");
                }
                for (int i2 = 0; i2 < 4 - set.size(); ++i2) {
                    out.print(' ');
                }
                out.print(']');
            }
            out.print(':');
            out.print(y);
            for (i = 0; i < 2 - Integer.toString(y).length(); ++i) {
                out.print(' ');
            }
            out.println();
        }
        out.print("   ");
        for (x = 0; x < this.sensorGrid.xLen; ++x) {
            out.print(x);
            for (i = 0; i < 6 - Integer.toString(x).length(); ++i) {
                out.print(' ');
            }
        }
        out.println();
    }
}

