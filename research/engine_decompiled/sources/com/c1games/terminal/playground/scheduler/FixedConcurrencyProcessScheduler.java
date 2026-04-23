/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground.scheduler;

import com.c1games.terminal.playground.scheduler.PausableProcess;
import com.c1games.terminal.playground.scheduler.PausableProcessTask;
import com.c1games.terminal.playground.scheduler.ProcessScheduler;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;

public class FixedConcurrencyProcessScheduler
implements ProcessScheduler {
    private BlockingQueue<Submission<?>> submissions = new ArrayBlockingQueue(10000);
    private List<Thread> threads = new ArrayList<Thread>();

    public FixedConcurrencyProcessScheduler(int multiplicity) {
        for (int i = 0; i < multiplicity; ++i) {
            Thread thread = new Thread(() -> {
                try {
                    while (true) {
                        FixedConcurrencyProcessScheduler.run(this.submissions.take());
                    }
                }
                catch (InterruptedException e) {
                    System.out.println("PausableAlgoInterfaceOld com.c1games.terminal.playground.scheduler thread closing");
                    return;
                }
            }, "process com.c1games.terminal.playground.scheduler thread");
            this.threads.add(thread);
            thread.start();
        }
    }

    private static <T> void run(Submission<T> submission) {
        try {
            try {
                submission.algo.resume();
                Optional<Object> computed = submission.task.run(submission.algo, submission.timeout);
                computed.ifPresentOrElse(result2 -> submission.future.complete(result2), () -> submission.future.completeExceptionally(new NoSuchElementException()));
            }
            finally {
                submission.algo.pause();
            }
        }
        catch (Exception e) {
            submission.future.completeExceptionally(e);
        }
    }

    @Override
    public <T> CompletableFuture<T> submit(PausableProcess algo, PausableProcessTask<T> task, Duration timeout) {
        CompletableFuture future = new CompletableFuture();
        this.submissions.add(new Submission<T>(algo, task, timeout, future));
        return future;
    }

    private class Submission<T> {
        PausableProcess algo;
        PausableProcessTask<T> task;
        Duration timeout;
        CompletableFuture<T> future;

        public Submission(PausableProcess algo, PausableProcessTask<T> task, Duration timeout, CompletableFuture<T> future) {
            this.algo = algo;
            this.task = task;
            this.timeout = timeout;
            this.future = future;
        }
    }
}

