/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems.map.path;

import com.c1games.terminal.game.systems.map.path.CoordQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class CoordQueueTest {
    public static void main(String[] args) {
        ArrayList<int[]> vecs = new ArrayList<int[]>();
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 0; i < 100000; ++i) {
            vecs.add(new int[]{rand.nextInt(), rand.nextInt()});
        }
        Iterator iter2 = vecs.iterator();
        CoordQueue queue = new CoordQueue();
        int i = 0;
        for (int[] v : vecs) {
            queue.push(v[0], v[1]);
            if (i++ % 2 != 0) continue;
            System.out.println(i);
            int[] v2 = (int[])iter2.next();
            assert (queue.next());
            assert (queue.currX == v2[0]);
            assert (queue.currY == v2[1]);
        }
        while (iter2.hasNext()) {
            int[] v = (int[])iter2.next();
            System.out.println(i++);
            assert (queue.next());
            assert (queue.currX == v[0]);
            assert (queue.currY == v[1]);
        }
        assert (!queue.next());
    }
}

