/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.util;

import java.time.Duration;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class SessionBufferer {
    private final long splay;
    private final ReentrantLock nextTurnLock = new ReentrantLock();
    private long nextTurn = 0L;
    private final Semaphore semaphore;
    private final SessionBuffer withoutSemaphore;

    public SessionBufferer(Duration splay, int maxSimultaneousConnections) {
        this.splay = splay.toMillis();
        this.semaphore = new Semaphore(maxSimultaneousConnections);
        this.withoutSemaphore = new SessionBuffer(null);
    }

    public SessionBuffer waitForTurn(boolean countTowardsSemaphore) {
        long waitUntil;
        boolean wait;
        if (countTowardsSemaphore) {
            try {
                this.semaphore.acquire();
            }
            catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
        this.nextTurnLock.lock();
        long now = System.currentTimeMillis();
        if (this.nextTurn > now) {
            wait = true;
            waitUntil = this.nextTurn;
            this.nextTurn += this.splay;
        } else {
            wait = false;
            waitUntil = -1L;
            this.nextTurn = now + this.splay;
        }
        this.nextTurnLock.unlock();
        if (wait) {
            try {
                Thread.sleep(waitUntil - now);
            }
            catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
        if (countTowardsSemaphore) {
            return new SessionBuffer(this.semaphore);
        }
        return this.withoutSemaphore;
    }

    public static class SessionBuffer {
        private Semaphore semaphore;

        private SessionBuffer(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        public void end() {
            if (this.semaphore != null) {
                this.semaphore.release();
                this.semaphore = null;
            }
        }

        public boolean isLocking() {
            return this.semaphore != null;
        }

        protected void finalize() throws Throwable {
            if (this.semaphore != null) {
                System.err.println("Critical error: session buffer garbage collected while still holding lock");
                this.semaphore.release();
            }
        }
    }
}

