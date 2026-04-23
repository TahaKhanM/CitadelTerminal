/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground.poller;

import com.c1games.terminal.playground.poller.Pollable;
import com.c1games.terminal.playground.poller.Poller;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SingleThreadedPoller
implements Poller {
    private BlockingQueue<Pollable> incoming = new ArrayBlockingQueue<Pollable>(10000);

    public SingleThreadedPoller(long sleep) {
        new Thread(() -> {
            ArrayList<Pollable> pollables = new ArrayList<Pollable>();
            block2: while (true) {
                Pollable elem;
                try {
                    Thread.sleep(sleep);
                }
                catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                while ((elem = (Pollable)this.incoming.poll()) != null) {
                    pollables.add(elem);
                }
                int i = 0;
                while (true) {
                    if (i >= pollables.size()) continue block2;
                    if (!SingleThreadedPoller.tryPoll((Pollable)pollables.get(i))) {
                        int last2 = pollables.size() - 1;
                        pollables.set(i, (Pollable)pollables.get(last2));
                        pollables.remove(last2);
                    }
                    ++i;
                }
                break;
            }
        }, "com.c1games.terminal.playground.poller thread").start();
    }

    private static boolean tryPoll(Pollable pollable) {
        try {
            return pollable.poll();
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public void add(Pollable pollable) {
        this.incoming.add(pollable);
    }
}

