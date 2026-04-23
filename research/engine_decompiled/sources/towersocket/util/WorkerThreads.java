/*
 * Decompiled with CFR 0.152.
 */
package towersocket.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Predicate;

public class WorkerThreads<C> {
    public final C conditionDat;
    private final BlockingQueue<Runnable> tasks;

    public WorkerThreads(String name, int threads, C conditionDat, Predicate<C> condition) {
        this.conditionDat = conditionDat;
        this.tasks = new LinkedBlockingQueue<Runnable>();
        for (int i = 0; i < threads; ++i) {
            new Thread(() -> {
                while (condition.test(conditionDat)) {
                    Runnable task;
                    try {
                        task = this.tasks.take();
                    }
                    catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                    try {
                        task.run();
                    }
                    catch (Exception e) {
                        System.err.println("Towersocket worker error:");
                        e.printStackTrace();
                    }
                    do {
                        if ((task = (Runnable)this.tasks.poll()) == null) continue;
                        try {
                            task.run();
                        }
                        catch (Exception e) {
                            System.err.println("Towersocket worker error:");
                            e.printStackTrace();
                        }
                    } while (task != null);
                }
            }, name).start();
        }
    }

    public void exec(Runnable task) {
        this.tasks.add(task);
    }

    public int tasksLeftCount() {
        return this.tasks.size();
    }
}

