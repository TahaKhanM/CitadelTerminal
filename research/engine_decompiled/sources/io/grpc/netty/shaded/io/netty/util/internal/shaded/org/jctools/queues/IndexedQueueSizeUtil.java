/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues;

public final class IndexedQueueSizeUtil {
    public static int size(IndexedQueue iq) {
        long currentProducerIndex;
        long before;
        long after = iq.lvConsumerIndex();
        do {
            before = after;
            currentProducerIndex = iq.lvProducerIndex();
        } while (before != (after = iq.lvConsumerIndex()));
        long size2 = currentProducerIndex - after;
        if (size2 > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int)size2;
    }

    public static boolean isEmpty(IndexedQueue iq) {
        return iq.lvConsumerIndex() == iq.lvProducerIndex();
    }

    public static interface IndexedQueue {
        public long lvConsumerIndex();

        public long lvProducerIndex();
    }
}

