/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.atomic;

import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.atomic.BaseLinkedAtomicQueue;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.atomic.LinkedQueueAtomicNode;

public class SpscLinkedAtomicQueue<E>
extends BaseLinkedAtomicQueue<E> {
    public SpscLinkedAtomicQueue() {
        LinkedQueueAtomicNode node = this.newNode();
        this.spProducerNode(node);
        this.spConsumerNode(node);
        node.soNext(null);
    }

    @Override
    public boolean offer(E e) {
        if (null == e) {
            throw new NullPointerException();
        }
        LinkedQueueAtomicNode<E> nextNode = this.newNode(e);
        this.lpProducerNode().soNext(nextNode);
        this.spProducerNode(nextNode);
        return true;
    }

    @Override
    public E poll() {
        return (E)this.relaxedPoll();
    }

    @Override
    public E peek() {
        return (E)this.relaxedPeek();
    }

    @Override
    public int fill(MessagePassingQueue.Supplier<E> s2) {
        long result2 = 0L;
        do {
            this.fill(s2, 4096);
        } while ((result2 += 4096L) <= 0x7FFFEFFFL);
        return (int)result2;
    }

    @Override
    public int fill(MessagePassingQueue.Supplier<E> s2, int limit) {
        LinkedQueueAtomicNode<E> tail;
        if (limit == 0) {
            return 0;
        }
        LinkedQueueAtomicNode<E> head2 = tail = this.newNode(s2.get());
        for (int i = 1; i < limit; ++i) {
            LinkedQueueAtomicNode<E> temp = this.newNode(s2.get());
            tail.soNext(temp);
            tail = temp;
        }
        LinkedQueueAtomicNode<E> oldPNode = this.lpProducerNode();
        oldPNode.soNext(head2);
        this.spProducerNode(tail);
        return limit;
    }

    @Override
    public void fill(MessagePassingQueue.Supplier<E> s2, MessagePassingQueue.WaitStrategy wait, MessagePassingQueue.ExitCondition exit) {
        LinkedQueueAtomicNode<E> chaserNode = this.producerNode;
        while (exit.keepRunning()) {
            for (int i = 0; i < 4096; ++i) {
                LinkedQueueAtomicNode<E> nextNode = this.newNode(s2.get());
                chaserNode.soNext(nextNode);
                this.producerNode = chaserNode = nextNode;
            }
        }
    }
}

