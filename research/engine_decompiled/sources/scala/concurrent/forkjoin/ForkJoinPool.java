/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.forkjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import scala.concurrent.forkjoin.CountedCompleter;
import scala.concurrent.forkjoin.ForkJoinTask;
import scala.concurrent.forkjoin.ForkJoinWorkerThread;
import sun.misc.Unsafe;

public class ForkJoinPool
extends AbstractExecutorService {
    public static final ForkJoinWorkerThreadFactory defaultForkJoinWorkerThreadFactory;
    static final ThreadLocal<Submitter> submitters;
    private static final RuntimePermission modifyThreadPermission;
    static final ForkJoinPool common;
    static final int commonParallelism;
    private static int poolNumberSequence;
    private static final long IDLE_TIMEOUT = 2000000000L;
    private static final long FAST_IDLE_TIMEOUT = 200000000L;
    private static final long TIMEOUT_SLOP = 2000000L;
    private static final int MAX_HELP = 64;
    private static final int SEED_INCREMENT = 1640531527;
    private static final int AC_SHIFT = 48;
    private static final int TC_SHIFT = 32;
    private static final int ST_SHIFT = 31;
    private static final int EC_SHIFT = 16;
    private static final int SMASK = 65535;
    private static final int MAX_CAP = Short.MAX_VALUE;
    private static final int EVENMASK = 65534;
    private static final int SQMASK = 126;
    private static final int SHORT_SIGN = 32768;
    private static final int INT_SIGN = Integer.MIN_VALUE;
    private static final long STOP_BIT = 0x80000000L;
    private static final long AC_MASK = -281474976710656L;
    private static final long TC_MASK = 0xFFFF00000000L;
    private static final long TC_UNIT = 0x100000000L;
    private static final long AC_UNIT = 0x1000000000000L;
    private static final int UAC_SHIFT = 16;
    private static final int UTC_SHIFT = 0;
    private static final int UAC_MASK = -65536;
    private static final int UTC_MASK = 65535;
    private static final int UAC_UNIT = 65536;
    private static final int UTC_UNIT = 1;
    private static final int E_MASK = Integer.MAX_VALUE;
    private static final int E_SEQ = 65536;
    private static final int SHUTDOWN = Integer.MIN_VALUE;
    private static final int PL_LOCK = 2;
    private static final int PL_SIGNAL = 1;
    private static final int PL_SPINS = 256;
    static final int LIFO_QUEUE = 0;
    static final int FIFO_QUEUE = 1;
    static final int SHARED_QUEUE = -1;
    private static final int MIN_SCAN = 511;
    private static final int MAX_SCAN = 131071;
    volatile long pad00;
    volatile long pad01;
    volatile long pad02;
    volatile long pad03;
    volatile long pad04;
    volatile long pad05;
    volatile long pad06;
    volatile long stealCount;
    volatile long ctl;
    volatile int plock;
    volatile int indexSeed;
    final int config;
    WorkQueue[] workQueues;
    final ForkJoinWorkerThreadFactory factory;
    final Thread.UncaughtExceptionHandler ueh;
    final String workerNamePrefix;
    volatile Object pad10;
    volatile Object pad11;
    volatile Object pad12;
    volatile Object pad13;
    volatile Object pad14;
    volatile Object pad15;
    volatile Object pad16;
    volatile Object pad17;
    volatile Object pad18;
    volatile Object pad19;
    volatile Object pad1a;
    volatile Object pad1b;
    private static final Unsafe U;
    private static final long CTL;
    private static final long PARKBLOCKER;
    private static final int ABASE;
    private static final int ASHIFT;
    private static final long STEALCOUNT;
    private static final long PLOCK;
    private static final long INDEXSEED;
    private static final long QLOCK;

    private static void checkPermission() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkPermission(modifyThreadPermission);
        }
    }

    private static final synchronized int nextPoolId() {
        return ++poolNumberSequence;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private int acquirePlock() {
        int spins = 256;
        int r = 0;
        int nps;
        int ps;
        while (((ps = this.plock) & 2) != 0 || !U.compareAndSwapInt(this, PLOCK, ps, nps = ps + 2)) {
            if (r == 0) {
                WorkQueue w;
                Thread t = Thread.currentThread();
                if (t instanceof ForkJoinWorkerThread && (w = ((ForkJoinWorkerThread)t).workQueue) != null) {
                    r = w.seed;
                    continue;
                }
                Submitter z = submitters.get();
                if (z != null) {
                    r = z.seed;
                    continue;
                }
                r = 1;
                continue;
            }
            if (spins >= 0) {
                r ^= r << 1;
                r ^= r >>> 3;
                if ((r ^= r << 10) < 0) continue;
                --spins;
                continue;
            }
            if (!U.compareAndSwapInt(this, PLOCK, ps, ps | 1)) continue;
            ForkJoinPool forkJoinPool = this;
            synchronized (forkJoinPool) {
                if ((this.plock & 1) != 0) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ie) {
                        try {
                            Thread.currentThread().interrupt();
                        }
                        catch (SecurityException ignore) {}
                    }
                } else {
                    this.notifyAll();
                }
            }
        }
        return nps;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void releasePlock(int ps) {
        this.plock = ps;
        ForkJoinPool forkJoinPool = this;
        synchronized (forkJoinPool) {
            this.notifyAll();
        }
    }

    private void tryAddWorker() {
        long c;
        int u;
        while ((u = (int)((c = this.ctl) >>> 32)) < 0 && (u & 0x8000) != 0 && (int)c == 0) {
            long nc = (long)(u + 1 & 0xFFFF | u + 65536 & 0xFFFF0000) << 32;
            if (!U.compareAndSwapLong(this, CTL, c, nc)) continue;
            Throwable ex = null;
            ForkJoinWorkerThread wt = null;
            try {
                ForkJoinWorkerThreadFactory fac = this.factory;
                if (fac != null && (wt = fac.newThread(this)) != null) {
                    wt.start();
                    break;
                }
            }
            catch (Throwable e) {
                ex = e;
            }
            this.deregisterWorker(wt, ex);
            break;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    final WorkQueue registerWorker(ForkJoinWorkerThread wt) {
        int s2;
        wt.setDaemon(true);
        Thread.UncaughtExceptionHandler handler = this.ueh;
        if (handler != null) {
            wt.setUncaughtExceptionHandler(handler);
        }
        do {
            s2 = this.indexSeed;
        } while (!U.compareAndSwapInt(this, INDEXSEED, s2, s2 += 1640531527) || s2 == 0);
        WorkQueue w = new WorkQueue(this, wt, this.config >>> 16, s2);
        int ps = this.plock;
        if ((ps & 2) != 0 || !U.compareAndSwapInt(this, PLOCK, ps, ps += 2)) {
            ps = this.acquirePlock();
        }
        int nps = ps & Integer.MIN_VALUE | ps + 2 & Integer.MAX_VALUE;
        try {
            WorkQueue[] ws = this.workQueues;
            if (this.workQueues != null) {
                int n = ws.length;
                int m = n - 1;
                int r = s2 << 1 | 1;
                if (ws[r &= m] != null) {
                    int step;
                    int probes = 0;
                    int n2 = step = n <= 4 ? 2 : (n >>> 1 & 0xFFFE) + 2;
                    while (ws[r = r + step & m] != null) {
                        if (++probes < n) continue;
                        this.workQueues = ws = Arrays.copyOf(ws, n <<= 1);
                        m = n - 1;
                        probes = 0;
                    }
                }
                w.eventCount = w.poolIndex = r;
                ws[r] = w;
            }
        }
        finally {
            if (!U.compareAndSwapInt(this, PLOCK, ps, nps)) {
                this.releasePlock(nps);
            }
        }
        wt.setName(this.workerNamePrefix.concat(Integer.toString(w.poolIndex)));
        return w;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    final void deregisterWorker(ForkJoinWorkerThread wt, Throwable ex) {
        long c;
        WorkQueue w = null;
        if (wt != null && (w = wt.workQueue) != null) {
            long sc;
            w.qlock = -1;
            long ns = w.nsteals;
            while (!U.compareAndSwapLong(this, STEALCOUNT, sc = this.stealCount, sc + ns)) {
            }
            int ps = this.plock;
            if ((ps & 2) != 0 || !U.compareAndSwapInt(this, PLOCK, ps, ps += 2)) {
                ps = this.acquirePlock();
            }
            int nps = ps & Integer.MIN_VALUE | ps + 2 & Integer.MAX_VALUE;
            try {
                int idx = w.poolIndex;
                WorkQueue[] ws = this.workQueues;
                if (ws != null && idx >= 0 && idx < ws.length && ws[idx] == w) {
                    ws[idx] = null;
                }
            }
            finally {
                if (!U.compareAndSwapInt(this, PLOCK, ps, nps)) {
                    this.releasePlock(nps);
                }
            }
        }
        while (!U.compareAndSwapLong(this, CTL, c = this.ctl, c - 0x1000000000000L & 0xFFFF000000000000L | c - 0x100000000L & 0xFFFF00000000L | c & 0xFFFFFFFFL)) {
        }
        if (!this.tryTerminate(false, false) && w != null && w.array != null) {
            int e;
            int u;
            w.cancelAll();
            while ((u = (int)((c = this.ctl) >>> 32)) < 0 && (e = (int)c) >= 0) {
                if (e > 0) {
                    WorkQueue v;
                    int i;
                    WorkQueue[] ws = this.workQueues;
                    if (this.workQueues == null || (i = e & 0xFFFF) >= ws.length || (v = ws[i]) == null) break;
                    long nc = (long)(v.nextWait & Integer.MAX_VALUE) | (long)(u + 65536) << 32;
                    if (v.eventCount != (e | Integer.MIN_VALUE)) break;
                    if (!U.compareAndSwapLong(this, CTL, c, nc)) continue;
                    v.eventCount = e + 65536 & Integer.MAX_VALUE;
                    Thread p = v.parker;
                    if (p == null) break;
                    U.unpark(p);
                    break;
                }
                if ((short)u >= 0) break;
                this.tryAddWorker();
                break;
            }
        }
        if (ex == null) {
            ForkJoinTask.helpExpungeStaleExceptions();
        } else {
            ForkJoinTask.rethrow(ex);
        }
    }

    final void externalPush(ForkJoinTask<?> task) {
        Submitter z = submitters.get();
        if (z != null && this.plock > 0) {
            WorkQueue q;
            int m;
            WorkQueue[] ws = this.workQueues;
            if (this.workQueues != null && (m = ws.length - 1) >= 0 && (q = ws[m & z.seed & 0x7E]) != null && U.compareAndSwapInt(q, QLOCK, 0, 1)) {
                int n;
                int an;
                int b = q.base;
                int s2 = q.top;
                ForkJoinTask<?>[] a = q.array;
                if (q.array != null && (an = a.length) > (n = s2 + 1 - b)) {
                    int j = ((an - 1 & s2) << ASHIFT) + ABASE;
                    U.putOrderedObject(a, j, task);
                    q.top = s2 + 1;
                    q.qlock = 0;
                    if (n <= 2) {
                        this.signalWork(q);
                    }
                    return;
                }
                q.qlock = 0;
            }
        }
        this.fullExternalPush(task);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void fullExternalPush(ForkJoinTask<?> task) {
        int r = 0;
        Submitter z = submitters.get();
        while (true) {
            int m;
            WorkQueue[] ws;
            int ps;
            block23: {
                int nps;
                int p;
                block22: {
                    if (z == null) {
                        r = this.indexSeed;
                        if (!U.compareAndSwapInt(this, INDEXSEED, r, r += 1640531527) || r == 0) continue;
                        z = new Submitter(r);
                        submitters.set(z);
                        continue;
                    }
                    if (r == 0) {
                        r = z.seed;
                        r ^= r << 13;
                        r ^= r >>> 17;
                        z.seed = r ^ r << 5;
                        continue;
                    }
                    ps = this.plock;
                    if (ps < 0) {
                        throw new RejectedExecutionException();
                    }
                    if (ps == 0) break block22;
                    ws = this.workQueues;
                    if (this.workQueues != null && (m = ws.length - 1) >= 0) break block23;
                }
                int n = (p = this.config & 0xFFFF) > 1 ? p - 1 : 1;
                n |= n >>> 1;
                n |= n >>> 2;
                n |= n >>> 4;
                n |= n >>> 8;
                n |= n >>> 16;
                n = n + 1 << 1;
                ws = this.workQueues;
                WorkQueue[] nws = this.workQueues == null || ws.length == 0 ? new WorkQueue[n] : null;
                ps = this.plock;
                if ((ps & 2) != 0 || !U.compareAndSwapInt(this, PLOCK, ps, ps += 2)) {
                    ps = this.acquirePlock();
                }
                ws = this.workQueues;
                if ((this.workQueues == null || ws.length == 0) && nws != null) {
                    this.workQueues = nws;
                }
                if (U.compareAndSwapInt(this, PLOCK, ps, nps = ps & Integer.MIN_VALUE | ps + 2 & Integer.MAX_VALUE)) continue;
                this.releasePlock(nps);
                continue;
            }
            int k = r & m & 0x7E;
            WorkQueue q = ws[k];
            if (q != null) {
                if (q.qlock == 0 && U.compareAndSwapInt(q, QLOCK, 0, 1)) {
                    ForkJoinTask<?>[] a = q.array;
                    int s2 = q.top;
                    boolean submitted = false;
                    try {
                        if (a != null && a.length > s2 + 1 - q.base || (a = q.growArray()) != null) {
                            int j = ((a.length - 1 & s2) << ASHIFT) + ABASE;
                            U.putOrderedObject(a, j, task);
                            q.top = s2 + 1;
                            submitted = true;
                        }
                    }
                    finally {
                        q.qlock = 0;
                    }
                    if (submitted) {
                        this.signalWork(q);
                        return;
                    }
                }
                r = 0;
                continue;
            }
            ps = this.plock;
            if ((ps & 2) == 0) {
                int nps;
                q = new WorkQueue(this, null, -1, r);
                ps = this.plock;
                if ((ps & 2) != 0 || !U.compareAndSwapInt(this, PLOCK, ps, ps += 2)) {
                    ps = this.acquirePlock();
                }
                ws = this.workQueues;
                if (this.workQueues != null && k < ws.length && ws[k] == null) {
                    ws[k] = q;
                }
                if (U.compareAndSwapInt(this, PLOCK, ps, nps = ps & Integer.MIN_VALUE | ps + 2 & Integer.MAX_VALUE)) continue;
                this.releasePlock(nps);
                continue;
            }
            r = 0;
        }
    }

    final void incrementActiveCount() {
        long c;
        while (!U.compareAndSwapLong(this, CTL, c = this.ctl, c + 0x1000000000000L)) {
        }
    }

    final void signalWork(WorkQueue q) {
        long c;
        int u;
        int hint = q.poolIndex;
        while ((u = (int)((c = this.ctl) >>> 32)) < 0) {
            int e = (int)c;
            if (e > 0) {
                WorkQueue w;
                int i;
                WorkQueue[] ws = this.workQueues;
                if (this.workQueues == null || ws.length <= (i = e & 0xFFFF) || (w = ws[i]) == null || w.eventCount != (e | Integer.MIN_VALUE)) break;
                long nc = (long)(w.nextWait & Integer.MAX_VALUE) | (long)(u + 65536) << 32;
                if (U.compareAndSwapLong(this, CTL, c, nc)) {
                    w.hint = hint;
                    w.eventCount = e + 65536 & Integer.MAX_VALUE;
                    Thread p = w.parker;
                    if (p == null) break;
                    U.unpark(p);
                    break;
                }
                if (q.top - q.base > 0) continue;
                break;
            }
            if ((short)u >= 0) break;
            this.tryAddWorker();
            break;
        }
    }

    final void runWorker(WorkQueue w) {
        w.growArray();
        do {
            w.runTask(this.scan(w));
        } while (w.qlock >= 0);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final ForkJoinTask<?> scan(WorkQueue w) {
        int m;
        int ps = this.plock;
        if (w == null) return null;
        WorkQueue[] ws = this.workQueues;
        if (this.workQueues == null || (m = ws.length - 1) < 0) return null;
        int ec = w.eventCount;
        int r = w.seed;
        r ^= r << 13;
        r ^= r >>> 17;
        r ^= r << 5;
        w.seed = r;
        w.hint = -1;
        int j = (m + m + 1 | 0x1FF) & 0x1FFFF;
        do {
            int b;
            WorkQueue q;
            if ((q = ws[r + j & m]) == null || (b = q.base) - q.top >= 0) continue;
            ForkJoinTask<?>[] a = q.array;
            if (q.array == null) continue;
            int i = ((a.length - 1 & b) << ASHIFT) + ABASE;
            ForkJoinTask t = (ForkJoinTask)U.getObjectVolatile(a, i);
            if (q.base == b && ec >= 0 && t != null && U.compareAndSwapObject(a, i, t, null)) {
                q.base = b + 1;
                if (q.base - q.top >= 0) return t;
                this.signalWork(q);
                return t;
            }
            if (ec >= 0 && j >= m || (int)(this.ctl >> 48) > 0) continue;
            w.hint = r + j & m;
            break;
        } while (--j >= 0);
        int ns = w.nsteals;
        if (ns != 0) {
            long sc = this.stealCount;
            if (!U.compareAndSwapLong(this, STEALCOUNT, sc, sc + (long)ns)) return null;
            w.nsteals = 0;
            return null;
        } else {
            if (this.plock != ps) return null;
            long c = this.ctl;
            int e = (int)c;
            if (e < 0) {
                w.qlock = -1;
                return null;
            } else {
                WorkQueue v;
                int i;
                int u;
                int idleCount;
                int s2;
                WorkQueue q;
                int h = w.hint;
                if (h < 0) {
                    if (ec >= 0) {
                        long nc = (long)ec | c - 0x1000000000000L & 0xFFFFFFFF00000000L;
                        w.nextWait = e;
                        w.eventCount = ec | Integer.MIN_VALUE;
                        if (this.ctl != c || !U.compareAndSwapLong(this, CTL, c, nc)) {
                            w.eventCount = ec;
                        } else if ((int)(c >> 48) == 1 - (this.config & 0xFFFF)) {
                            this.idleAwaitWork(w, nc, c);
                        }
                    } else if (w.eventCount < 0 && this.ctl == c) {
                        Thread wt = Thread.currentThread();
                        Thread.interrupted();
                        U.putObject(wt, PARKBLOCKER, this);
                        w.parker = wt;
                        if (w.eventCount < 0) {
                            U.park(false, 0L);
                        }
                        w.parker = null;
                        U.putObject(wt, PARKBLOCKER, null);
                    }
                }
                if (h < 0 && (h = w.hint) < 0) return null;
                ws = this.workQueues;
                if (this.workQueues == null || h >= ws.length || (q = ws[h]) == null) return null;
                int n = (this.config & 0xFFFF) - 1;
                while (((s2 = (idleCount = w.eventCount < 0 ? 0 : -1) - q.base + q.top) > n || (n = s2) > 0) && (u = (int)((c = this.ctl) >>> 32)) < 0 && (e = (int)c) > 0 && m >= (i = e & 0xFFFF) && (v = ws[i]) != null) {
                    long nc = (long)(v.nextWait & Integer.MAX_VALUE) | (long)(u + 65536) << 32;
                    if (v.eventCount != (e | Integer.MIN_VALUE) || !U.compareAndSwapLong(this, CTL, c, nc)) return null;
                    v.hint = h;
                    v.eventCount = e + 65536 & Integer.MAX_VALUE;
                    Thread p = v.parker;
                    if (p != null) {
                        U.unpark(p);
                    }
                    if (--n > 0) continue;
                    return null;
                }
            }
        }
        return null;
    }

    private void idleAwaitWork(WorkQueue w, long currentCtl, long prevCtl) {
        if (w != null && w.eventCount < 0 && !this.tryTerminate(false, false) && (int)prevCtl != 0 && this.ctl == currentCtl) {
            short dc = -((short)(currentCtl >>> 32));
            long parkTime = dc < 0 ? 200000000L : (long)(dc + 1) * 2000000000L;
            long deadline = System.nanoTime() + parkTime - 2000000L;
            Thread wt = Thread.currentThread();
            while (this.ctl == currentCtl) {
                Thread.interrupted();
                U.putObject(wt, PARKBLOCKER, this);
                w.parker = wt;
                if (this.ctl == currentCtl) {
                    U.park(false, parkTime);
                }
                w.parker = null;
                U.putObject(wt, PARKBLOCKER, null);
                if (this.ctl != currentCtl) break;
                if (deadline - System.nanoTime() > 0L || !U.compareAndSwapLong(this, CTL, currentCtl, prevCtl)) continue;
                w.eventCount = w.eventCount + 65536 | Integer.MAX_VALUE;
                w.hint = -1;
                w.qlock = -1;
                break;
            }
        }
    }

    private void helpSignal(ForkJoinTask<?> task, int origin) {
        int u;
        if (task != null && task.status >= 0 && (u = (int)(this.ctl >>> 32)) < 0 && u >> 16 < 0) {
            int m;
            WorkQueue[] ws = this.workQueues;
            if (this.workQueues != null && (m = ws.length - 1) >= 0) {
                int k = origin;
                block0: for (int j = m; j >= 0; --j) {
                    WorkQueue q = ws[k++ & m];
                    int n = m;
                    while (task.status >= 0) {
                        WorkQueue w;
                        int i;
                        int e;
                        int s2;
                        if (q == null || (s2 = -q.base + q.top) <= n && (n = s2) <= 0) continue block0;
                        long c = this.ctl;
                        u = (int)(c >>> 32);
                        if (u >= 0 || (e = (int)c) <= 0 || m < (i = e & 0xFFFF) || (w = ws[i]) == null) break block0;
                        long nc = (long)(w.nextWait & Integer.MAX_VALUE) | (long)(u + 65536) << 32;
                        if (w.eventCount != (e | Integer.MIN_VALUE)) break block0;
                        if (!U.compareAndSwapLong(this, CTL, c, nc)) continue;
                        w.eventCount = e + 65536 & Integer.MAX_VALUE;
                        Thread p = w.parker;
                        if (p != null) {
                            U.unpark(p);
                        }
                        if (--n > 0) continue;
                        continue block0;
                    }
                    break block0;
                }
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    private int tryHelpStealer(WorkQueue joiner, ForkJoinTask<?> task) {
        stat = 0;
        steps = 0;
        if (joiner != null && task != null) {
            block0: while (true) {
                subtask = task;
                j = joiner;
                while (true) {
                    block9: {
                        block8: {
                            if ((s = task.status) < 0) {
                                stat = s;
                                break block0;
                            }
                            ws = this.workQueues;
                            if (this.workQueues == null || (m = ws.length - 1) <= 0) break block0;
                            h = (j.hint | 1) & m;
                            v = ws[h];
                            if (v == null || v.currentSteal != subtask) {
                                origin = h;
                                do {
                                    if (((h = h + 2 & m) & 15) == 1 && (subtask.status < 0 || j.currentJoin != subtask)) continue block0;
                                    v = ws[h];
                                    if (v == null || v.currentSteal != subtask) continue;
                                    j.hint = h;
                                    break block8;
                                } while (h != origin);
                                break block0;
                            }
                        }
                        while (true) {
                            if (subtask.status < 0) continue block0;
                            b = v.base;
                            if (b - v.top >= 0) break block9;
                            a = v.array;
                            if (v.array == null) break block9;
                            i = ((a.length - 1 & b) << ForkJoinPool.ASHIFT) + ForkJoinPool.ABASE;
                            t = (ForkJoinTask)ForkJoinPool.U.getObjectVolatile(a, i);
                            if (subtask.status < 0 || j.currentJoin != subtask || v.currentSteal != subtask) continue block0;
                            stat = 1;
                            if (t != null && v.base == b && ForkJoinPool.U.compareAndSwapObject(a, i, t, null)) {
                                v.base = b + 1;
                                joiner.runSubtask(t);
                                continue;
                            }
                            if (v.base == b && ++steps == 64) break;
                        }
                        break block0;
                    }
                    next = v.currentJoin;
                    if (subtask.status >= 0 && j.currentJoin == subtask && v.currentSteal == subtask) ** break;
                    continue block0;
                    if (next == null || ++steps == 64) break block0;
                    subtask = next;
                    j = v;
                }
                break;
            }
        }
        return stat;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int helpComplete(ForkJoinTask<?> task, int mode) {
        int j;
        int m;
        if (task == null) return 0;
        WorkQueue[] ws = this.workQueues;
        if (this.workQueues == null || (m = ws.length - 1) < 0) return 0;
        int origin = j = 1;
        int s2;
        while ((s2 = task.status) >= 0) {
            WorkQueue q = ws[j & m];
            if (q != null && q.pollAndExecCC(task)) {
                int u;
                origin = j;
                if (mode != -1 || (u = (int)(this.ctl >>> 32)) < 0 && u >> 16 < 0) continue;
                return 0;
            }
            if ((j = j + 2 & m) == origin) return 0;
        }
        return s2;
    }

    final boolean tryCompensate() {
        long c;
        int e;
        int pc = this.config & 0xFFFF;
        WorkQueue[] ws = this.workQueues;
        if (this.workQueues != null && (e = (int)(c = this.ctl)) >= 0) {
            WorkQueue w;
            int i;
            if (e != 0 && (i = e & 0xFFFF) < ws.length && (w = ws[i]) != null && w.eventCount == (e | Integer.MIN_VALUE)) {
                long nc = (long)(w.nextWait & Integer.MAX_VALUE) | c & 0xFFFFFFFF00000000L;
                if (U.compareAndSwapLong(this, CTL, c, nc)) {
                    w.eventCount = e + 65536 & Integer.MAX_VALUE;
                    Thread p = w.parker;
                    if (p != null) {
                        U.unpark(p);
                    }
                    return true;
                }
            } else {
                long nc;
                short tc = (short)(c >>> 32);
                if (tc >= 0 && (int)(c >> 48) + pc > 1) {
                    long nc2 = c - 0x1000000000000L & 0xFFFF000000000000L | c & 0xFFFFFFFFFFFFL;
                    if (U.compareAndSwapLong(this, CTL, c, nc2)) {
                        return true;
                    }
                } else if (tc + pc < Short.MAX_VALUE && U.compareAndSwapLong(this, CTL, c, nc = c + 0x100000000L & 0xFFFF00000000L | c & 0xFFFF0000FFFFFFFFL)) {
                    Throwable ex = null;
                    ForkJoinWorkerThread wt = null;
                    try {
                        ForkJoinWorkerThreadFactory fac = this.factory;
                        if (fac != null && (wt = fac.newThread(this)) != null) {
                            wt.start();
                            return true;
                        }
                    }
                    catch (Throwable rex) {
                        ex = rex;
                    }
                    this.deregisterWorker(wt, ex);
                }
            }
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    final int awaitJoin(WorkQueue joiner, ForkJoinTask<?> task) {
        int s2 = 0;
        if (joiner != null && task != null && (s2 = task.status) >= 0) {
            ForkJoinTask<?> prevJoin = joiner.currentJoin;
            joiner.currentJoin = task;
            while ((s2 = task.status) >= 0 && !joiner.isEmpty() && joiner.tryRemoveAndExec(task)) {
            }
            if (s2 >= 0 && (s2 = task.status) >= 0) {
                this.helpSignal(task, joiner.poolIndex);
                s2 = task.status;
                if (s2 >= 0 && task instanceof CountedCompleter) {
                    s2 = this.helpComplete(task, 0);
                }
            }
            while (s2 >= 0 && (s2 = task.status) >= 0) {
                long c;
                if (joiner.isEmpty() && (s2 = this.tryHelpStealer(joiner, task)) != 0 || (s2 = task.status) < 0) continue;
                this.helpSignal(task, joiner.poolIndex);
                s2 = task.status;
                if (s2 < 0 || !this.tryCompensate()) continue;
                if (task.trySetSignal() && (s2 = task.status) >= 0) {
                    ForkJoinTask<?> forkJoinTask = task;
                    synchronized (forkJoinTask) {
                        if (task.status >= 0) {
                            try {
                                task.wait();
                            }
                            catch (InterruptedException ie) {}
                        } else {
                            task.notifyAll();
                        }
                    }
                }
                while (!U.compareAndSwapLong(this, CTL, c = this.ctl, c + 0x1000000000000L)) {
                }
            }
            joiner.currentJoin = prevJoin;
        }
        return s2;
    }

    final void helpJoinOnce(WorkQueue joiner, ForkJoinTask<?> task) {
        int s2;
        if (joiner != null && task != null && (s2 = task.status) >= 0) {
            ForkJoinTask<?> prevJoin = joiner.currentJoin;
            joiner.currentJoin = task;
            while ((s2 = task.status) >= 0 && !joiner.isEmpty() && joiner.tryRemoveAndExec(task)) {
            }
            if (s2 >= 0 && (s2 = task.status) >= 0) {
                this.helpSignal(task, joiner.poolIndex);
                s2 = task.status;
                if (s2 >= 0 && task instanceof CountedCompleter) {
                    s2 = this.helpComplete(task, 0);
                }
            }
            if (s2 >= 0 && joiner.isEmpty()) {
                while (task.status >= 0 && this.tryHelpStealer(joiner, task) > 0) {
                }
            }
            joiner.currentJoin = prevJoin;
        }
    }

    private WorkQueue findNonEmptyStealQueue(int r) {
        int ps;
        do {
            int m;
            ps = this.plock;
            WorkQueue[] ws = this.workQueues;
            if (this.workQueues == null || (m = ws.length - 1) < 0) continue;
            for (int j = m + 1 << 2; j >= 0; --j) {
                WorkQueue q = ws[(r + j << 1 | 1) & m];
                if (q == null || q.base - q.top >= 0) continue;
                return q;
            }
        } while (this.plock != ps);
        return null;
    }

    final void helpQuiescePool(WorkQueue w) {
        boolean active = true;
        while (true) {
            long c;
            ForkJoinTask<?> t;
            if ((t = w.nextLocalTask()) != null) {
                if (w.base - w.top < 0) {
                    this.signalWork(w);
                }
                t.doExec();
                continue;
            }
            WorkQueue q = this.findNonEmptyStealQueue(w.nextSeed());
            if (q != null) {
                int b;
                if (!active) {
                    active = true;
                    while (!U.compareAndSwapLong(this, CTL, c = this.ctl, c + 0x1000000000000L)) {
                    }
                }
                if ((b = q.base) - q.top >= 0 || (t = q.pollAt(b)) == null) continue;
                if (q.base - q.top < 0) {
                    this.signalWork(q);
                }
                w.runSubtask(t);
                continue;
            }
            if (active) {
                c = this.ctl;
                long nc = c - 0x1000000000000L;
                if ((int)(nc >> 48) + (this.config & 0xFFFF) == 0) {
                    return;
                }
                if (!U.compareAndSwapLong(this, CTL, c, nc)) continue;
                active = false;
                continue;
            }
            c = this.ctl;
            if ((int)(c >> 48) + (this.config & 0xFFFF) == 0 && U.compareAndSwapLong(this, CTL, c, c + 0x1000000000000L)) break;
        }
    }

    final ForkJoinTask<?> nextTaskFor(WorkQueue w) {
        ForkJoinTask<?> t;
        WorkQueue q;
        int b;
        do {
            if ((t = w.nextLocalTask()) != null) {
                return t;
            }
            q = this.findNonEmptyStealQueue(w.nextSeed());
            if (q != null) continue;
            return null;
        } while ((b = q.base) - q.top >= 0 || (t = q.pollAt(b)) == null);
        if (q.base - q.top < 0) {
            this.signalWork(q);
        }
        return t;
    }

    static int getSurplusQueuedTaskCount() {
        Thread t = Thread.currentThread();
        if (t instanceof ForkJoinWorkerThread) {
            ForkJoinWorkerThread wt = (ForkJoinWorkerThread)t;
            ForkJoinPool pool = wt.pool;
            int p = pool.config & 0xFFFF;
            WorkQueue q = wt.workQueue;
            int n = q.top - q.base;
            int a = (int)(pool.ctl >> 48) + p;
            return n - (a > (p >>>= 1) ? 0 : (a > (p >>>= 1) ? 1 : (a > (p >>>= 1) ? 2 : (a > (p >>>= 1) ? 4 : 8))));
        }
        return 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Unable to fully structure code
     */
    private boolean tryTerminate(boolean now, boolean enable) {
        if (this == ForkJoinPool.common) {
            return false;
        }
        ps = this.plock;
        if (ps >= 0) {
            if (!enable) {
                return false;
            }
            if ((ps & 2) != 0 || !ForkJoinPool.U.compareAndSwapInt(this, ForkJoinPool.PLOCK, ps, ps += 2)) {
                ps = this.acquirePlock();
            }
            if (!ForkJoinPool.U.compareAndSwapInt(this, ForkJoinPool.PLOCK, ps, nps = ps + 2 & 0x7FFFFFFF | -2147483648)) {
                this.releasePlock(nps);
            }
        }
        block5: while (true) {
            if (((c = this.ctl) & 0x80000000L) != 0L) {
                if ((short)(c >>> 32) == -(this.config & 65535)) {
                    var6_6 = this;
                    synchronized (var6_6) {
                        this.notifyAll();
                    }
                }
                return true;
            }
            if (!now) {
                if ((int)(c >> 48) != -(this.config & 65535)) {
                    return false;
                }
                ws = this.workQueues;
                if (this.workQueues != null) {
                    for (i = 0; i < ws.length; ++i) {
                        w = ws[i];
                        if (w == null) continue;
                        if (!w.isEmpty()) {
                            this.signalWork(w);
                            return false;
                        }
                        if ((i & 1) == 0 || w.eventCount < 0) continue;
                        return false;
                    }
                }
            }
            if (!ForkJoinPool.U.compareAndSwapLong(this, ForkJoinPool.CTL, c, c | 0x80000000L)) continue;
            pass = 0;
            while (true) {
                if (pass < 3) ** break;
                continue block5;
                ws = this.workQueues;
                if (this.workQueues != null) {
                    n = ws.length;
                    for (i = 0; i < n; ++i) {
                        w = ws[i];
                        if (w == null) continue;
                        w.qlock = -1;
                        if (pass <= 0) continue;
                        w.cancelAll();
                        if (pass <= 1 || (wt = w.owner) == null) continue;
                        if (!wt.isInterrupted()) {
                            try {
                                wt.interrupt();
                            }
                            catch (Throwable ignore) {
                                // empty catch block
                            }
                        }
                        ForkJoinPool.U.unpark(wt);
                    }
                    while ((e = (int)(cc = this.ctl) & 0x7FFFFFFF) != 0 && (i = e & 65535) < n && i >= 0 && (w = ws[i]) != null) {
                        nc = (long)(w.nextWait & 0x7FFFFFFF) | cc + 0x1000000000000L & -281474976710656L | cc & 0xFFFF80000000L;
                        if (w.eventCount != (e | -2147483648) || !ForkJoinPool.U.compareAndSwapLong(this, ForkJoinPool.CTL, cc, nc)) continue;
                        w.eventCount = e + 65536 & 0x7FFFFFFF;
                        w.qlock = -1;
                        p = w.parker;
                        if (p == null) continue;
                        ForkJoinPool.U.unpark(p);
                    }
                }
                ++pass;
            }
            break;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static WorkQueue commonSubmitterQueue() {
        Submitter z = submitters.get();
        if (z == null) return null;
        ForkJoinPool p = common;
        if (p == null) return null;
        WorkQueue[] ws = p.workQueues;
        if (p.workQueues == null) return null;
        int m = ws.length - 1;
        if (m < 0) return null;
        WorkQueue workQueue = ws[m & z.seed & 0x7E];
        return workQueue;
    }

    static boolean tryExternalUnpush(ForkJoinTask<?> t) {
        ForkJoinPool p;
        Submitter z;
        if (t != null && (z = submitters.get()) != null && (p = common) != null) {
            int s2;
            WorkQueue q;
            int m;
            WorkQueue[] ws = p.workQueues;
            if (p.workQueues != null && (m = ws.length - 1) >= 0 && (q = ws[m & z.seed & 0x7E]) != null && (s2 = q.top) != q.base) {
                long j;
                ForkJoinTask<?>[] a = q.array;
                if (q.array != null && U.getObject(a, j = (long)(((a.length - 1 & s2 - 1) << ASHIFT) + ABASE)) == t && U.compareAndSwapInt(q, QLOCK, 0, 1)) {
                    if (q.array == a && q.top == s2 && U.compareAndSwapObject(a, j, t, null)) {
                        q.top = s2 - 1;
                        q.qlock = 0;
                        return true;
                    }
                    q.qlock = 0;
                }
            }
        }
        return false;
    }

    private void externalHelpComplete(WorkQueue q, ForkJoinTask<?> root) {
        block7: {
            int m;
            if (q == null) break block7;
            ForkJoinTask<?>[] a = q.array;
            if (q.array != null && (m = a.length - 1) >= 0 && root != null && root.status >= 0) {
                CountedCompleter<?> task;
                do {
                    int u;
                    long j;
                    Object o;
                    task = null;
                    int s2 = q.top;
                    if (s2 - q.base > 0 && (o = U.getObject(a, j = (long)(((m & s2 - 1) << ASHIFT) + ABASE))) != null && o instanceof CountedCompleter) {
                        CountedCompleter<?> t;
                        CountedCompleter<?> r = t = (CountedCompleter<?>)o;
                        do {
                            if (r != root) continue;
                            if (!U.compareAndSwapInt(q, QLOCK, 0, 1)) break;
                            if (q.array == a && q.top == s2 && U.compareAndSwapObject(a, j, t, null)) {
                                q.top = s2 - 1;
                                task = t;
                            }
                            q.qlock = 0;
                            break;
                        } while ((r = r.completer) != null);
                    }
                    if (task != null) {
                        task.doExec();
                    }
                    if (root.status < 0 || (u = (int)(this.ctl >>> 32)) >= 0 || u >> 16 >= 0) break block7;
                } while (task != null);
                this.helpSignal(root, q.poolIndex);
                if (root.status >= 0) {
                    this.helpComplete(root, -1);
                }
            }
        }
    }

    static void externalHelpJoin(ForkJoinTask<?> t) {
        ForkJoinPool p;
        Submitter z;
        if (t != null && (z = submitters.get()) != null && (p = common) != null) {
            WorkQueue q;
            int m;
            WorkQueue[] ws = p.workQueues;
            if (p.workQueues != null && (m = ws.length - 1) >= 0 && (q = ws[m & z.seed & 0x7E]) != null) {
                ForkJoinTask<?>[] a = q.array;
                if (q.array != null) {
                    long j;
                    int am = a.length - 1;
                    int s2 = q.top;
                    if (s2 != q.base && U.getObject(a, j = (long)(((am & s2 - 1) << ASHIFT) + ABASE)) == t && U.compareAndSwapInt(q, QLOCK, 0, 1)) {
                        if (q.array == a && q.top == s2 && U.compareAndSwapObject(a, j, t, null)) {
                            q.top = s2 - 1;
                            q.qlock = 0;
                            t.doExec();
                        } else {
                            q.qlock = 0;
                        }
                    }
                    if (t.status >= 0) {
                        if (t instanceof CountedCompleter) {
                            p.externalHelpComplete(q, t);
                        } else {
                            p.helpSignal(t, q.poolIndex);
                        }
                    }
                }
            }
        }
    }

    public ForkJoinPool() {
        this(Math.min(Short.MAX_VALUE, Runtime.getRuntime().availableProcessors()), defaultForkJoinWorkerThreadFactory, null, false);
    }

    public ForkJoinPool(int parallelism) {
        this(parallelism, defaultForkJoinWorkerThreadFactory, null, false);
    }

    public ForkJoinPool(int parallelism, ForkJoinWorkerThreadFactory factory, Thread.UncaughtExceptionHandler handler, boolean asyncMode) {
        ForkJoinPool.checkPermission();
        if (factory == null) {
            throw new NullPointerException();
        }
        if (parallelism <= 0 || parallelism > Short.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        this.factory = factory;
        this.ueh = handler;
        this.config = parallelism | (asyncMode ? 65536 : 0);
        long np = -parallelism;
        this.ctl = np << 48 & 0xFFFF000000000000L | np << 32 & 0xFFFF00000000L;
        int pn = ForkJoinPool.nextPoolId();
        StringBuilder sb = new StringBuilder("ForkJoinPool-");
        sb.append(Integer.toString(pn));
        sb.append("-worker-");
        this.workerNamePrefix = sb.toString();
    }

    ForkJoinPool(int parallelism, long ctl, ForkJoinWorkerThreadFactory factory, Thread.UncaughtExceptionHandler handler) {
        this.config = parallelism;
        this.ctl = ctl;
        this.factory = factory;
        this.ueh = handler;
        this.workerNamePrefix = "ForkJoinPool.commonPool-worker-";
    }

    public static ForkJoinPool commonPool() {
        return common;
    }

    public <T> T invoke(ForkJoinTask<T> task) {
        if (task == null) {
            throw new NullPointerException();
        }
        this.externalPush(task);
        return task.join();
    }

    public void execute(ForkJoinTask<?> task) {
        if (task == null) {
            throw new NullPointerException();
        }
        this.externalPush(task);
    }

    @Override
    public void execute(Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }
        ForkJoinTask job = task instanceof ForkJoinTask ? (ForkJoinTask)((Object)task) : new ForkJoinTask.AdaptedRunnableAction(task);
        this.externalPush(job);
    }

    public <T> ForkJoinTask<T> submit(ForkJoinTask<T> task) {
        if (task == null) {
            throw new NullPointerException();
        }
        this.externalPush(task);
        return task;
    }

    public <T> ForkJoinTask<T> submit(Callable<T> task) {
        ForkJoinTask.AdaptedCallable<T> job = new ForkJoinTask.AdaptedCallable<T>(task);
        this.externalPush(job);
        return job;
    }

    public <T> ForkJoinTask<T> submit(Runnable task, T result2) {
        ForkJoinTask.AdaptedRunnable<T> job = new ForkJoinTask.AdaptedRunnable<T>(task, result2);
        this.externalPush(job);
        return job;
    }

    public ForkJoinTask<?> submit(Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }
        ForkJoinTask job = task instanceof ForkJoinTask ? (ForkJoinTask)((Object)task) : new ForkJoinTask.AdaptedRunnableAction(task);
        this.externalPush(job);
        return job;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) {
        ArrayList<Future<T>> arrayList;
        block7: {
            int size2;
            ArrayList<Future<T>> futures = new ArrayList<Future<T>>(tasks.size());
            boolean done = false;
            try {
                for (Callable<T> t : tasks) {
                    ForkJoinTask.AdaptedCallable<T> f = new ForkJoinTask.AdaptedCallable<T>(t);
                    futures.add(f);
                    this.externalPush(f);
                }
                int size3 = futures.size();
                for (int i = 0; i < size3; ++i) {
                    ((ForkJoinTask)futures.get(i)).quietlyJoin();
                }
                done = true;
                arrayList = futures;
                if (done) break block7;
                size2 = futures.size();
            }
            catch (Throwable throwable) {
                if (!done) {
                    int size4 = futures.size();
                    for (int i = 0; i < size4; ++i) {
                        ((Future)futures.get(i)).cancel(false);
                    }
                }
                throw throwable;
            }
            for (int i = 0; i < size2; ++i) {
                futures.get(i).cancel(false);
            }
        }
        return arrayList;
    }

    public ForkJoinWorkerThreadFactory getFactory() {
        return this.factory;
    }

    public Thread.UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return this.ueh;
    }

    public int getParallelism() {
        return this.config & 0xFFFF;
    }

    public static int getCommonPoolParallelism() {
        return commonParallelism;
    }

    public int getPoolSize() {
        return (this.config & 0xFFFF) + (short)(this.ctl >>> 32);
    }

    public boolean getAsyncMode() {
        return this.config >>> 16 == 1;
    }

    public int getRunningThreadCount() {
        int rc = 0;
        WorkQueue[] ws = this.workQueues;
        if (this.workQueues != null) {
            for (int i = 1; i < ws.length; i += 2) {
                WorkQueue w = ws[i];
                if (w == null || !w.isApparentlyUnblocked()) continue;
                ++rc;
            }
        }
        return rc;
    }

    public int getActiveThreadCount() {
        int r = (this.config & 0xFFFF) + (int)(this.ctl >> 48);
        return r <= 0 ? 0 : r;
    }

    public boolean isQuiescent() {
        return (int)(this.ctl >> 48) + (this.config & 0xFFFF) == 0;
    }

    public long getStealCount() {
        long count2 = this.stealCount;
        WorkQueue[] ws = this.workQueues;
        if (this.workQueues != null) {
            for (int i = 1; i < ws.length; i += 2) {
                WorkQueue w = ws[i];
                if (w == null) continue;
                count2 += (long)w.nsteals;
            }
        }
        return count2;
    }

    public long getQueuedTaskCount() {
        long count2 = 0L;
        WorkQueue[] ws = this.workQueues;
        if (this.workQueues != null) {
            for (int i = 1; i < ws.length; i += 2) {
                WorkQueue w = ws[i];
                if (w == null) continue;
                count2 += (long)w.queueSize();
            }
        }
        return count2;
    }

    public int getQueuedSubmissionCount() {
        int count2 = 0;
        WorkQueue[] ws = this.workQueues;
        if (this.workQueues != null) {
            for (int i = 0; i < ws.length; i += 2) {
                WorkQueue w = ws[i];
                if (w == null) continue;
                count2 += w.queueSize();
            }
        }
        return count2;
    }

    public boolean hasQueuedSubmissions() {
        WorkQueue[] ws = this.workQueues;
        if (this.workQueues != null) {
            for (int i = 0; i < ws.length; i += 2) {
                WorkQueue w = ws[i];
                if (w == null || w.isEmpty()) continue;
                return true;
            }
        }
        return false;
    }

    protected ForkJoinTask<?> pollSubmission() {
        WorkQueue[] ws = this.workQueues;
        if (this.workQueues != null) {
            for (int i = 0; i < ws.length; i += 2) {
                ForkJoinTask<?> t;
                WorkQueue w = ws[i];
                if (w == null || (t = w.poll()) == null) continue;
                return t;
            }
        }
        return null;
    }

    protected int drainTasksTo(Collection<? super ForkJoinTask<?>> c) {
        int count2 = 0;
        WorkQueue[] ws = this.workQueues;
        if (this.workQueues != null) {
            for (int i = 0; i < ws.length; ++i) {
                ForkJoinTask<?> t;
                WorkQueue w = ws[i];
                if (w == null) continue;
                while ((t = w.poll()) != null) {
                    c.add(t);
                    ++count2;
                }
            }
        }
        return count2;
    }

    public String toString() {
        long qt = 0L;
        long qs = 0L;
        int rc = 0;
        long st = this.stealCount;
        long c = this.ctl;
        WorkQueue[] ws = this.workQueues;
        if (this.workQueues != null) {
            for (int i = 0; i < ws.length; ++i) {
                WorkQueue w = ws[i];
                if (w == null) continue;
                int size2 = w.queueSize();
                if ((i & 1) == 0) {
                    qs += (long)size2;
                    continue;
                }
                qt += (long)size2;
                st += (long)w.nsteals;
                if (!w.isApparentlyUnblocked()) continue;
                ++rc;
            }
        }
        int pc = this.config & 0xFFFF;
        int tc = pc + (short)(c >>> 32);
        int ac = pc + (int)(c >> 48);
        if (ac < 0) {
            ac = 0;
        }
        String level = (c & 0x80000000L) != 0L ? (tc == 0 ? "Terminated" : "Terminating") : (this.plock < 0 ? "Shutting down" : "Running");
        return super.toString() + "[" + level + ", parallelism = " + pc + ", size = " + tc + ", active = " + ac + ", running = " + rc + ", steals = " + st + ", tasks = " + qt + ", submissions = " + qs + "]";
    }

    @Override
    public void shutdown() {
        ForkJoinPool.checkPermission();
        this.tryTerminate(false, true);
    }

    @Override
    public List<Runnable> shutdownNow() {
        ForkJoinPool.checkPermission();
        this.tryTerminate(true, true);
        return Collections.emptyList();
    }

    @Override
    public boolean isTerminated() {
        long c = this.ctl;
        return (c & 0x80000000L) != 0L && (short)(c >>> 32) == -(this.config & 0xFFFF);
    }

    public boolean isTerminating() {
        long c = this.ctl;
        return (c & 0x80000000L) != 0L && (short)(c >>> 32) != -(this.config & 0xFFFF);
    }

    @Override
    public boolean isShutdown() {
        return this.plock < 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        if (this == common) {
            this.awaitQuiescence(timeout, unit);
            return false;
        }
        long nanos = unit.toNanos(timeout);
        if (this.isTerminated()) {
            return true;
        }
        long startTime = System.nanoTime();
        boolean terminated = false;
        ForkJoinPool forkJoinPool = this;
        synchronized (forkJoinPool) {
            long waitTime = nanos;
            long millis = 0L;
            while (!(terminated = this.isTerminated() || waitTime <= 0L || (millis = unit.toMillis(waitTime)) <= 0L)) {
                this.wait(millis);
                waitTime = nanos - (System.nanoTime() - startTime);
            }
        }
        return terminated;
    }

    public boolean awaitQuiescence(long timeout, TimeUnit unit) {
        long nanos = unit.toNanos(timeout);
        Thread thread = Thread.currentThread();
        if (thread instanceof ForkJoinWorkerThread) {
            ForkJoinWorkerThread wt = (ForkJoinWorkerThread)thread;
            if (wt.pool == this) {
                this.helpQuiescePool(wt.workQueue);
                return true;
            }
        }
        long startTime = System.nanoTime();
        int r = 0;
        boolean found = true;
        block0: while (!this.isQuiescent()) {
            int m;
            WorkQueue[] ws = this.workQueues;
            if (this.workQueues == null || (m = ws.length - 1) < 0) break;
            if (!found) {
                if (System.nanoTime() - startTime > nanos) {
                    return false;
                }
                Thread.yield();
            }
            found = false;
            for (int j = m + 1 << 2; j >= 0; --j) {
                int b;
                WorkQueue q;
                if ((q = ws[r++ & m]) == null || (b = q.base) - q.top >= 0) continue;
                found = true;
                ForkJoinTask<?> t = q.pollAt(b);
                if (t == null) continue block0;
                if (q.base - q.top < 0) {
                    this.signalWork(q);
                }
                t.doExec();
                continue block0;
            }
        }
        return true;
    }

    static void quiesceCommonPool() {
        common.awaitQuiescence(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void managedBlock(ManagedBlocker blocker) throws InterruptedException {
        Thread t = Thread.currentThread();
        if (t instanceof ForkJoinWorkerThread) {
            ForkJoinPool p = ((ForkJoinWorkerThread)t).pool;
            while (!blocker.isReleasable()) {
                int m;
                WorkQueue[] ws = p.workQueues;
                if (p.workQueues != null && (m = ws.length - 1) >= 0) {
                    for (int i = 0; i <= m; ++i) {
                        if (blocker.isReleasable()) {
                            return;
                        }
                        WorkQueue q = ws[i];
                        if (q == null || q.base - q.top >= 0) continue;
                        p.signalWork(q);
                        int u = (int)(p.ctl >>> 32);
                        if (u >= 0 || u >> 16 >= 0) break;
                    }
                }
                if (!p.tryCompensate()) continue;
                try {
                    while (!blocker.isReleasable() && !blocker.block()) {
                    }
                    break;
                }
                finally {
                    p.incrementActiveCount();
                }
            }
        } else {
            while (!blocker.isReleasable() && !blocker.block()) {
            }
        }
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return new ForkJoinTask.AdaptedRunnable<T>(runnable, value);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new ForkJoinTask.AdaptedCallable<T>(callable);
    }

    private static Unsafe getUnsafe() {
        return scala.concurrent.util.Unsafe.instance;
    }

    static {
        try {
            U = ForkJoinPool.getUnsafe();
            Class<ForkJoinPool> k = ForkJoinPool.class;
            CTL = U.objectFieldOffset(k.getDeclaredField("ctl"));
            STEALCOUNT = U.objectFieldOffset(k.getDeclaredField("stealCount"));
            PLOCK = U.objectFieldOffset(k.getDeclaredField("plock"));
            INDEXSEED = U.objectFieldOffset(k.getDeclaredField("indexSeed"));
            Class<Thread> tk = Thread.class;
            PARKBLOCKER = U.objectFieldOffset(tk.getDeclaredField("parkBlocker"));
            Class<WorkQueue> wk = WorkQueue.class;
            QLOCK = U.objectFieldOffset(wk.getDeclaredField("qlock"));
            Class<ForkJoinTask[]> ak = ForkJoinTask[].class;
            ABASE = U.arrayBaseOffset(ak);
            int scale = U.arrayIndexScale(ak);
            if ((scale & scale - 1) != 0) {
                throw new Error("data type scale not a power of two");
            }
            ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
        }
        catch (Exception e) {
            throw new Error(e);
        }
        submitters = new ThreadLocal();
        defaultForkJoinWorkerThreadFactory = new DefaultForkJoinWorkerThreadFactory();
        ForkJoinWorkerThreadFactory fac = defaultForkJoinWorkerThreadFactory;
        modifyThreadPermission = new RuntimePermission("modifyThread");
        int par2 = 0;
        Thread.UncaughtExceptionHandler handler = null;
        try {
            String pp = System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism");
            String hp = System.getProperty("java.util.concurrent.ForkJoinPool.common.exceptionHandler");
            String fp = System.getProperty("java.util.concurrent.ForkJoinPool.common.threadFactory");
            if (fp != null) {
                fac = (ForkJoinWorkerThreadFactory)ClassLoader.getSystemClassLoader().loadClass(fp).newInstance();
            }
            if (hp != null) {
                handler = (Thread.UncaughtExceptionHandler)ClassLoader.getSystemClassLoader().loadClass(hp).newInstance();
            }
            if (pp != null) {
                par2 = Integer.parseInt(pp);
            }
        }
        catch (Exception ignore) {
            // empty catch block
        }
        if (par2 <= 0) {
            par2 = Runtime.getRuntime().availableProcessors();
        }
        if (par2 > Short.MAX_VALUE) {
            par2 = Short.MAX_VALUE;
        }
        commonParallelism = par2;
        long np = -par2;
        long ct = np << 48 & 0xFFFF000000000000L | np << 32 & 0xFFFF00000000L;
        common = new ForkJoinPool(par2, ct, fac, handler);
    }

    public static interface ManagedBlocker {
        public boolean block() throws InterruptedException;

        public boolean isReleasable();
    }

    static final class WorkQueue {
        static final int INITIAL_QUEUE_CAPACITY = 8192;
        static final int MAXIMUM_QUEUE_CAPACITY = 0x4000000;
        volatile long pad00;
        volatile long pad01;
        volatile long pad02;
        volatile long pad03;
        volatile long pad04;
        volatile long pad05;
        volatile long pad06;
        int seed;
        volatile int eventCount;
        int nextWait;
        int hint;
        int poolIndex;
        final int mode;
        int nsteals;
        volatile int qlock;
        volatile int base;
        int top;
        ForkJoinTask<?>[] array;
        final ForkJoinPool pool;
        final ForkJoinWorkerThread owner;
        volatile Thread parker;
        volatile ForkJoinTask<?> currentJoin;
        ForkJoinTask<?> currentSteal;
        volatile Object pad10;
        volatile Object pad11;
        volatile Object pad12;
        volatile Object pad13;
        volatile Object pad14;
        volatile Object pad15;
        volatile Object pad16;
        volatile Object pad17;
        volatile Object pad18;
        volatile Object pad19;
        volatile Object pad1a;
        volatile Object pad1b;
        volatile Object pad1c;
        volatile Object pad1d;
        private static final Unsafe U;
        private static final long QLOCK;
        private static final int ABASE;
        private static final int ASHIFT;

        WorkQueue(ForkJoinPool pool, ForkJoinWorkerThread owner2, int mode, int seed) {
            this.pool = pool;
            this.owner = owner2;
            this.mode = mode;
            this.seed = seed;
            this.top = 4096;
            this.base = 4096;
        }

        final int queueSize() {
            int n = this.base - this.top;
            return n >= 0 ? 0 : -n;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        final boolean isEmpty() {
            int s2 = this.top;
            int n = this.base - s2;
            if (n >= 0) return true;
            if (n != -1) return false;
            ForkJoinTask<?>[] a = this.array;
            if (this.array == null) return true;
            int m = a.length - 1;
            if (m < 0) return true;
            if (U.getObject(a, (long)((m & s2 - 1) << ASHIFT) + (long)ABASE) != null) return false;
            return true;
        }

        final void push(ForkJoinTask<?> task) {
            int s2 = this.top;
            ForkJoinTask<?>[] a = this.array;
            if (this.array != null) {
                int m = a.length - 1;
                int j = ((m & s2) << ASHIFT) + ABASE;
                U.putOrderedObject(a, j, task);
                this.top = s2 + 1;
                int n = this.top - this.base;
                if (n <= 2) {
                    ForkJoinPool p = this.pool;
                    if (p != null) {
                        p.signalWork(this);
                    }
                } else if (n >= m) {
                    this.growArray();
                }
            }
        }

        final ForkJoinTask<?>[] growArray() {
            int b;
            int t;
            int oldMask;
            int size2;
            ForkJoinTask<?>[] oldA = this.array;
            int n = size2 = oldA != null ? oldA.length << 1 : 8192;
            if (size2 > 0x4000000) {
                throw new RejectedExecutionException("Queue capacity exceeded");
            }
            this.array = new ForkJoinTask[size2];
            ForkJoinTask[] a = this.array;
            if (oldA != null && (oldMask = oldA.length - 1) >= 0 && (t = this.top) - (b = this.base) > 0) {
                int mask = size2 - 1;
                do {
                    int oldj = ((b & oldMask) << ASHIFT) + ABASE;
                    int j = ((b & mask) << ASHIFT) + ABASE;
                    ForkJoinTask x = (ForkJoinTask)U.getObjectVolatile(oldA, oldj);
                    if (x == null || !U.compareAndSwapObject(oldA, oldj, x, null)) continue;
                    U.putObjectVolatile(a, j, x);
                } while (++b != t);
            }
            return a;
        }

        final ForkJoinTask<?> pop() {
            int m;
            ForkJoinTask<?>[] a = this.array;
            if (this.array != null && (m = a.length - 1) >= 0) {
                long j;
                ForkJoinTask t;
                int s2;
                while ((s2 = this.top - 1) - this.base >= 0 && (t = (ForkJoinTask)U.getObject(a, j = (long)(((m & s2) << ASHIFT) + ABASE))) != null) {
                    if (!U.compareAndSwapObject(a, j, t, null)) continue;
                    this.top = s2;
                    return t;
                }
            }
            return null;
        }

        final ForkJoinTask<?> pollAt(int b) {
            int j;
            ForkJoinTask t;
            ForkJoinTask<?>[] a = this.array;
            if (this.array != null && (t = (ForkJoinTask)U.getObjectVolatile(a, j = ((a.length - 1 & b) << ASHIFT) + ABASE)) != null && this.base == b && U.compareAndSwapObject(a, j, t, null)) {
                this.base = b + 1;
                return t;
            }
            return null;
        }

        final ForkJoinTask<?> poll() {
            int b;
            while ((b = this.base) - this.top < 0) {
                ForkJoinTask<?>[] a = this.array;
                if (this.array == null) break;
                int j = ((a.length - 1 & b) << ASHIFT) + ABASE;
                ForkJoinTask t = (ForkJoinTask)U.getObjectVolatile(a, j);
                if (t != null) {
                    if (this.base != b || !U.compareAndSwapObject(a, j, t, null)) continue;
                    this.base = b + 1;
                    return t;
                }
                if (this.base != b) continue;
                if (b + 1 == this.top) break;
                Thread.yield();
            }
            return null;
        }

        final ForkJoinTask<?> nextLocalTask() {
            return this.mode == 0 ? this.pop() : this.poll();
        }

        final ForkJoinTask<?> peek() {
            int m;
            ForkJoinTask<?>[] a = this.array;
            if (a == null || (m = a.length - 1) < 0) {
                return null;
            }
            int i = this.mode == 0 ? this.top - 1 : this.base;
            int j = ((i & m) << ASHIFT) + ABASE;
            return (ForkJoinTask)U.getObjectVolatile(a, j);
        }

        final boolean tryUnpush(ForkJoinTask<?> t) {
            int s2;
            ForkJoinTask<?>[] a = this.array;
            if (this.array != null && (s2 = this.top) != this.base && U.compareAndSwapObject(a, ((a.length - 1 & --s2) << ASHIFT) + ABASE, t, null)) {
                this.top = s2;
                return true;
            }
            return false;
        }

        final void cancelAll() {
            ForkJoinTask<?> t;
            ForkJoinTask.cancelIgnoringExceptions(this.currentJoin);
            ForkJoinTask.cancelIgnoringExceptions(this.currentSteal);
            while ((t = this.poll()) != null) {
                ForkJoinTask.cancelIgnoringExceptions(t);
            }
        }

        final int nextSeed() {
            int r = this.seed;
            r ^= r << 13;
            r ^= r >>> 17;
            r ^= r << 5;
            this.seed = r;
            return r;
        }

        private void popAndExecAll() {
            while (true) {
                long j;
                ForkJoinTask t;
                int s2;
                int m;
                ForkJoinTask<?>[] a = this.array;
                if (this.array == null || (m = a.length - 1) < 0 || (s2 = this.top - 1) - this.base < 0 || (t = (ForkJoinTask)U.getObject(a, j = (long)(((m & s2) << ASHIFT) + ABASE))) == null) break;
                if (!U.compareAndSwapObject(a, j, t, null)) continue;
                this.top = s2;
                t.doExec();
            }
        }

        private void pollAndExecAll() {
            ForkJoinTask<?> t;
            while ((t = this.poll()) != null) {
                t.doExec();
            }
        }

        final boolean tryRemoveAndExec(ForkJoinTask<?> task) {
            int b;
            int s2;
            int n;
            int m;
            boolean stat = true;
            boolean removed = false;
            boolean empty = true;
            ForkJoinTask<?>[] a = this.array;
            if (this.array != null && (m = a.length - 1) >= 0 && (n = (s2 = this.top) - (b = this.base)) > 0) {
                int j;
                ForkJoinTask t;
                while ((t = (ForkJoinTask)U.getObjectVolatile(a, j = ((--s2 & m) << ASHIFT) + ABASE)) != null) {
                    if (t == task) {
                        if (s2 + 1 == this.top) {
                            if (!U.compareAndSwapObject(a, j, task, null)) break;
                            this.top = s2;
                            removed = true;
                            break;
                        }
                        if (this.base != b) break;
                        removed = U.compareAndSwapObject(a, j, task, new EmptyTask());
                        break;
                    }
                    if (t.status >= 0) {
                        empty = false;
                    } else if (s2 + 1 == this.top) {
                        if (!U.compareAndSwapObject(a, j, t, null)) break;
                        this.top = s2;
                        break;
                    }
                    if (--n != 0) continue;
                    if (empty || this.base != b) break;
                    stat = false;
                    break;
                }
            }
            if (removed) {
                task.doExec();
            }
            return stat;
        }

        final boolean pollAndExecCC(ForkJoinTask<?> root) {
            int b;
            block0: while ((b = this.base) - this.top < 0) {
                CountedCompleter<?> t;
                long j;
                Object o;
                ForkJoinTask<?>[] a = this.array;
                if (this.array == null || (o = U.getObject(a, j = (long)(((a.length - 1 & b) << ASHIFT) + ABASE))) == null || !(o instanceof CountedCompleter)) break;
                CountedCompleter<?> r = t = (CountedCompleter<?>)o;
                do {
                    if (r != root) continue;
                    if (this.base != b || !U.compareAndSwapObject(a, j, t, null)) continue block0;
                    this.base = b + 1;
                    t.doExec();
                    return true;
                } while ((r = r.completer) != null);
                break;
            }
            return false;
        }

        final void runTask(ForkJoinTask<?> t) {
            if (t != null) {
                this.currentSteal = t;
                this.currentSteal.doExec();
                this.currentSteal = null;
                ++this.nsteals;
                if (this.base - this.top < 0) {
                    if (this.mode == 0) {
                        this.popAndExecAll();
                    } else {
                        this.pollAndExecAll();
                    }
                }
            }
        }

        final void runSubtask(ForkJoinTask<?> t) {
            if (t != null) {
                ForkJoinTask<?> ps = this.currentSteal;
                this.currentSteal = t;
                this.currentSteal.doExec();
                this.currentSteal = ps;
            }
        }

        final boolean isApparentlyUnblocked() {
            Thread.State s2;
            ForkJoinWorkerThread wt;
            return this.eventCount >= 0 && (wt = this.owner) != null && (s2 = wt.getState()) != Thread.State.BLOCKED && s2 != Thread.State.WAITING && s2 != Thread.State.TIMED_WAITING;
        }

        static {
            try {
                U = ForkJoinPool.getUnsafe();
                Class<WorkQueue> k = WorkQueue.class;
                Class<ForkJoinTask[]> ak = ForkJoinTask[].class;
                QLOCK = U.objectFieldOffset(k.getDeclaredField("qlock"));
                ABASE = U.arrayBaseOffset(ak);
                int scale = U.arrayIndexScale(ak);
                if ((scale & scale - 1) != 0) {
                    throw new Error("data type scale not a power of two");
                }
                ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
            }
            catch (Exception e) {
                throw new Error(e);
            }
        }
    }

    static final class EmptyTask
    extends ForkJoinTask<Void> {
        private static final long serialVersionUID = -7721805057305804111L;

        EmptyTask() {
            this.status = -268435456;
        }

        @Override
        public final Void getRawResult() {
            return null;
        }

        @Override
        public final void setRawResult(Void x) {
        }

        @Override
        public final boolean exec() {
            return true;
        }
    }

    static final class Submitter {
        int seed;

        Submitter(int s2) {
            this.seed = s2;
        }
    }

    static final class DefaultForkJoinWorkerThreadFactory
    implements ForkJoinWorkerThreadFactory {
        DefaultForkJoinWorkerThreadFactory() {
        }

        @Override
        public final ForkJoinWorkerThread newThread(ForkJoinPool pool) {
            return new ForkJoinWorkerThread(pool);
        }
    }

    public static interface ForkJoinWorkerThreadFactory {
        public ForkJoinWorkerThread newThread(ForkJoinPool var1);
    }
}

