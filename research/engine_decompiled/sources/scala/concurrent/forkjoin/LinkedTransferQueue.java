/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.forkjoin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import scala.concurrent.forkjoin.ThreadLocalRandom;
import scala.concurrent.forkjoin.TransferQueue;
import sun.misc.Unsafe;

public class LinkedTransferQueue<E>
extends AbstractQueue<E>
implements TransferQueue<E>,
Serializable {
    private static final long serialVersionUID = -3223113410248163686L;
    private static final boolean MP = Runtime.getRuntime().availableProcessors() > 1;
    private static final int FRONT_SPINS = 128;
    private static final int CHAINED_SPINS = 64;
    static final int SWEEP_THRESHOLD = 32;
    volatile transient Node head;
    private volatile transient Node tail;
    private volatile transient int sweepVotes;
    private static final int NOW = 0;
    private static final int ASYNC = 1;
    private static final int SYNC = 2;
    private static final int TIMED = 3;
    private static final Unsafe UNSAFE;
    private static final long headOffset;
    private static final long tailOffset;
    private static final long sweepVotesOffset;

    private boolean casTail(Node cmp, Node val) {
        return UNSAFE.compareAndSwapObject(this, tailOffset, cmp, val);
    }

    private boolean casHead(Node cmp, Node val) {
        return UNSAFE.compareAndSwapObject(this, headOffset, cmp, val);
    }

    private boolean casSweepVotes(int cmp, int val) {
        return UNSAFE.compareAndSwapInt(this, sweepVotesOffset, cmp, val);
    }

    static <E> E cast(Object item) {
        return (E)item;
    }

    private E xfer(E e, boolean haveData, int how, long nanos) {
        block8: {
            Node pred;
            if (haveData && e == null) {
                throw new NullPointerException();
            }
            Node s2 = null;
            do {
                Node h;
                Node p = h = this.head;
                while (p != null) {
                    Node n;
                    boolean isData = p.isData;
                    Object item = p.item;
                    if (item != p && item != null == isData) {
                        if (isData == haveData) break;
                        if (p.casItem(item, e)) {
                            Node q = p;
                            while (q != h) {
                                Node n2 = q.next;
                                if (this.head == h && this.casHead(h, n2 == null ? q : n2)) {
                                    h.forgetNext();
                                    break;
                                }
                                h = this.head;
                                if (h != null && (q = h.next) != null && q.isMatched()) continue;
                                break;
                            }
                            LockSupport.unpark(p.waiter);
                            return LinkedTransferQueue.cast(item);
                        }
                    }
                    p = p != (n = p.next) ? n : this.head;
                }
                if (how == 0) break block8;
                if (s2 != null) continue;
                s2 = new Node(e, haveData);
            } while ((pred = this.tryAppend(s2, haveData)) == null);
            if (how != 1) {
                return this.awaitMatch(s2, pred, e, how == 3, nanos);
            }
        }
        return e;
    }

    private Node tryAppend(Node s2, boolean haveData) {
        Node t;
        Node p = t = this.tail;
        while (true) {
            if (p == null && (p = this.head) == null) {
                if (!this.casHead(null, s2)) continue;
                return s2;
            }
            if (p.cannotPrecede(haveData)) {
                return null;
            }
            Node n = p.next;
            if (n != null) {
                Node u;
                p = p != t && t != (u = this.tail) ? u : (p != n ? n : null);
                continue;
            }
            if (p.casNext(null, s2)) break;
            p = p.next;
        }
        if (p != t) {
            while (!(this.tail == t && this.casTail(t, s2) || (t = this.tail) == null || (s2 = t.next) == null || (s2 = s2.next) == null || s2 == t)) {
            }
        }
        return p;
    }

    private E awaitMatch(Node s2, Node pred, E e, boolean timed, long nanos) {
        long lastTime = timed ? System.nanoTime() : 0L;
        Thread w = Thread.currentThread();
        int spins = -1;
        Random randomYields = null;
        while (true) {
            Object item;
            if ((item = s2.item) != e) {
                s2.forgetContents();
                return LinkedTransferQueue.cast(item);
            }
            if ((w.isInterrupted() || timed && nanos <= 0L) && s2.casItem(e, s2)) {
                this.unsplice(pred, s2);
                return e;
            }
            if (spins < 0) {
                spins = LinkedTransferQueue.spinsFor(pred, s2.isData);
                if (spins <= 0) continue;
                randomYields = ThreadLocalRandom.current();
                continue;
            }
            if (spins > 0) {
                --spins;
                if (randomYields.nextInt(64) != 0) continue;
                Thread.yield();
                continue;
            }
            if (s2.waiter == null) {
                s2.waiter = w;
                continue;
            }
            if (timed) {
                long now = System.nanoTime();
                if ((nanos -= now - lastTime) > 0L) {
                    LockSupport.parkNanos(this, nanos);
                }
                lastTime = now;
                continue;
            }
            LockSupport.park(this);
        }
    }

    private static int spinsFor(Node pred, boolean haveData) {
        if (MP && pred != null) {
            if (pred.isData != haveData) {
                return 192;
            }
            if (pred.isMatched()) {
                return 128;
            }
            if (pred.waiter == null) {
                return 64;
            }
        }
        return 0;
    }

    final Node succ(Node p) {
        Node next2 = p.next;
        return p == next2 ? this.head : next2;
    }

    private Node firstOfMode(boolean isData) {
        Node p = this.head;
        while (p != null) {
            if (!p.isMatched()) {
                return p.isData == isData ? p : null;
            }
            p = this.succ(p);
        }
        return null;
    }

    private E firstDataItem() {
        Node p = this.head;
        while (p != null) {
            Object item = p.item;
            if (p.isData) {
                if (item != null && item != p) {
                    return LinkedTransferQueue.cast(item);
                }
            } else if (item == null) {
                return null;
            }
            p = this.succ(p);
        }
        return null;
    }

    private int countOfMode(boolean data) {
        int count2 = 0;
        Node p = this.head;
        while (p != null) {
            Node n;
            if (!p.isMatched()) {
                if (p.isData != data) {
                    return 0;
                }
                if (++count2 == Integer.MAX_VALUE) break;
            }
            if ((n = p.next) != p) {
                p = n;
                continue;
            }
            count2 = 0;
            p = this.head;
        }
        return count2;
    }

    final void unsplice(Node pred, Node s2) {
        block6: {
            Node n;
            s2.forgetContents();
            if (pred == null || pred == s2 || pred.next != s2 || (n = s2.next) != null && (n == s2 || !pred.casNext(s2, n) || !pred.isMatched())) break block6;
            while (true) {
                Node h;
                if ((h = this.head) == pred || h == s2 || h == null) {
                    return;
                }
                if (!h.isMatched()) break;
                Node hn = h.next;
                if (hn == null) {
                    return;
                }
                if (hn == h || !this.casHead(h, hn)) continue;
                h.forgetNext();
            }
            if (pred.next != pred && s2.next != s2) {
                while (true) {
                    int v;
                    if ((v = this.sweepVotes) < 32) {
                        if (!this.casSweepVotes(v, v + 1)) continue;
                        break block6;
                    }
                    if (this.casSweepVotes(v, 0)) break;
                }
                this.sweep();
            }
        }
    }

    private void sweep() {
        Node s2;
        Node p = this.head;
        while (p != null && (s2 = p.next) != null) {
            if (!s2.isMatched()) {
                p = s2;
                continue;
            }
            Node n = s2.next;
            if (n == null) break;
            if (s2 == n) {
                p = this.head;
                continue;
            }
            p.casNext(s2, n);
        }
    }

    private boolean findAndRemove(Object e) {
        if (e != null) {
            Node pred = null;
            Node p = this.head;
            while (p != null) {
                Object item = p.item;
                if (p.isData) {
                    if (item != null && item != p && e.equals(item) && p.tryMatchData()) {
                        this.unsplice(pred, p);
                        return true;
                    }
                } else if (item == null) break;
                if ((p = p.next) != (pred = p)) continue;
                pred = null;
                p = this.head;
            }
        }
        return false;
    }

    public LinkedTransferQueue() {
    }

    public LinkedTransferQueue(Collection<? extends E> c) {
        this();
        this.addAll(c);
    }

    @Override
    public void put(E e) {
        this.xfer(e, true, 1, 0L);
    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) {
        this.xfer(e, true, 1, 0L);
        return true;
    }

    @Override
    public boolean offer(E e) {
        this.xfer(e, true, 1, 0L);
        return true;
    }

    @Override
    public boolean add(E e) {
        this.xfer(e, true, 1, 0L);
        return true;
    }

    @Override
    public boolean tryTransfer(E e) {
        return this.xfer(e, true, 0, 0L) == null;
    }

    @Override
    public void transfer(E e) throws InterruptedException {
        if (this.xfer(e, true, 2, 0L) != null) {
            Thread.interrupted();
            throw new InterruptedException();
        }
    }

    @Override
    public boolean tryTransfer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        if (this.xfer(e, true, 3, unit.toNanos(timeout)) == null) {
            return true;
        }
        if (!Thread.interrupted()) {
            return false;
        }
        throw new InterruptedException();
    }

    @Override
    public E take() throws InterruptedException {
        E e = this.xfer(null, false, 2, 0L);
        if (e != null) {
            return e;
        }
        Thread.interrupted();
        throw new InterruptedException();
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        E e = this.xfer(null, false, 3, unit.toNanos(timeout));
        if (e != null || !Thread.interrupted()) {
            return e;
        }
        throw new InterruptedException();
    }

    @Override
    public E poll() {
        return this.xfer(null, false, 0, 0L);
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        E e;
        if (c == null) {
            throw new NullPointerException();
        }
        if (c == this) {
            throw new IllegalArgumentException();
        }
        int n = 0;
        while ((e = this.poll()) != null) {
            c.add(e);
            ++n;
        }
        return n;
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        E e;
        int n;
        if (c == null) {
            throw new NullPointerException();
        }
        if (c == this) {
            throw new IllegalArgumentException();
        }
        for (n = 0; n < maxElements && (e = this.poll()) != null; ++n) {
            c.add(e);
        }
        return n;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public E peek() {
        return this.firstDataItem();
    }

    @Override
    public boolean isEmpty() {
        Node p = this.head;
        while (p != null) {
            if (!p.isMatched()) {
                return !p.isData;
            }
            p = this.succ(p);
        }
        return true;
    }

    @Override
    public boolean hasWaitingConsumer() {
        return this.firstOfMode(false) != null;
    }

    @Override
    public int size() {
        return this.countOfMode(true);
    }

    @Override
    public int getWaitingConsumerCount() {
        return this.countOfMode(false);
    }

    @Override
    public boolean remove(Object o) {
        return this.findAndRemove(o);
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        Node p = this.head;
        while (p != null) {
            Object item = p.item;
            if (p.isData) {
                if (item != null && item != p && o.equals(item)) {
                    return true;
                }
            } else if (item == null) break;
            p = this.succ(p);
        }
        return false;
    }

    @Override
    public int remainingCapacity() {
        return Integer.MAX_VALUE;
    }

    private void writeObject(ObjectOutputStream s2) throws IOException {
        s2.defaultWriteObject();
        for (E e : this) {
            s2.writeObject(e);
        }
        s2.writeObject(null);
    }

    private void readObject(ObjectInputStream s2) throws IOException, ClassNotFoundException {
        Object item;
        s2.defaultReadObject();
        while ((item = s2.readObject()) != null) {
            this.offer(item);
        }
    }

    static Unsafe getUnsafe() {
        return scala.concurrent.util.Unsafe.instance;
    }

    static {
        try {
            UNSAFE = LinkedTransferQueue.getUnsafe();
            Class<LinkedTransferQueue> k = LinkedTransferQueue.class;
            headOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("head"));
            tailOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("tail"));
            sweepVotesOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("sweepVotes"));
        }
        catch (Exception e) {
            throw new Error(e);
        }
    }

    final class Itr
    implements Iterator<E> {
        private Node nextNode;
        private E nextItem;
        private Node lastRet;
        private Node lastPred;

        private void advance(Node prev) {
            Node r = this.lastRet;
            if (r != null && !r.isMatched()) {
                this.lastPred = r;
            } else {
                Node b = this.lastPred;
                if (b == null || b.isMatched()) {
                    this.lastPred = null;
                } else {
                    Node n;
                    Node s2;
                    while ((s2 = b.next) != null && s2 != b && s2.isMatched() && (n = s2.next) != null && n != s2) {
                        b.casNext(s2, n);
                    }
                }
            }
            this.lastRet = prev;
            Node p = prev;
            while (true) {
                Node s3;
                Node node = s3 = p == null ? LinkedTransferQueue.this.head : p.next;
                if (s3 == null) break;
                if (s3 == p) {
                    p = null;
                    continue;
                }
                Object item = s3.item;
                if (s3.isData) {
                    if (item != null && item != s3) {
                        this.nextItem = LinkedTransferQueue.cast(item);
                        this.nextNode = s3;
                        return;
                    }
                } else if (item == null) break;
                if (p == null) {
                    p = s3;
                    continue;
                }
                Node n = s3.next;
                if (n == null) break;
                if (s3 == n) {
                    p = null;
                    continue;
                }
                p.casNext(s3, n);
            }
            this.nextNode = null;
            this.nextItem = null;
        }

        Itr() {
            this.advance(null);
        }

        @Override
        public final boolean hasNext() {
            return this.nextNode != null;
        }

        @Override
        public final E next() {
            Node p = this.nextNode;
            if (p == null) {
                throw new NoSuchElementException();
            }
            Object e = this.nextItem;
            this.advance(p);
            return e;
        }

        @Override
        public final void remove() {
            Node lastRet = this.lastRet;
            if (lastRet == null) {
                throw new IllegalStateException();
            }
            this.lastRet = null;
            if (lastRet.tryMatchData()) {
                LinkedTransferQueue.this.unsplice(this.lastPred, lastRet);
            }
        }
    }

    static final class Node {
        final boolean isData;
        volatile Object item;
        volatile Node next;
        volatile Thread waiter;
        private static final long serialVersionUID = -3375979862319811754L;
        private static final Unsafe UNSAFE;
        private static final long itemOffset;
        private static final long nextOffset;
        private static final long waiterOffset;

        final boolean casNext(Node cmp, Node val) {
            return UNSAFE.compareAndSwapObject(this, nextOffset, cmp, val);
        }

        final boolean casItem(Object cmp, Object val) {
            return UNSAFE.compareAndSwapObject(this, itemOffset, cmp, val);
        }

        Node(Object item, boolean isData) {
            UNSAFE.putObject(this, itemOffset, item);
            this.isData = isData;
        }

        final void forgetNext() {
            UNSAFE.putObject(this, nextOffset, this);
        }

        final void forgetContents() {
            UNSAFE.putObject(this, itemOffset, this);
            UNSAFE.putObject(this, waiterOffset, null);
        }

        final boolean isMatched() {
            Object x = this.item;
            return x == this || x == null == this.isData;
        }

        final boolean isUnmatchedRequest() {
            return !this.isData && this.item == null;
        }

        final boolean cannotPrecede(boolean haveData) {
            Object x;
            boolean d = this.isData;
            return d != haveData && (x = this.item) != this && x != null == d;
        }

        final boolean tryMatchData() {
            Object x = this.item;
            if (x != null && x != this && this.casItem(x, null)) {
                LockSupport.unpark(this.waiter);
                return true;
            }
            return false;
        }

        static {
            try {
                UNSAFE = LinkedTransferQueue.getUnsafe();
                Class<Node> k = Node.class;
                itemOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("item"));
                nextOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("next"));
                waiterOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("waiter"));
            }
            catch (Exception e) {
                throw new Error(e);
            }
        }
    }
}

