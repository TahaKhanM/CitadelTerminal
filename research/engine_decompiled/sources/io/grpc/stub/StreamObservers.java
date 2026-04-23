/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.stub;

import com.google.common.base.Preconditions;
import io.grpc.ExperimentalApi;
import io.grpc.stub.CallStreamObserver;
import java.util.Iterator;

@ExperimentalApi
public final class StreamObservers {
    public static <V> void copyWithFlowControl(final Iterator<V> source, final CallStreamObserver<V> target) {
        Preconditions.checkNotNull(source, "source");
        Preconditions.checkNotNull(target, "target");
        final class FlowControllingOnReadyHandler
        implements Runnable {
            FlowControllingOnReadyHandler() {
            }

            @Override
            public void run() {
                while (target.isReady() && source.hasNext()) {
                    target.onNext(source.next());
                }
                if (!source.hasNext()) {
                    target.onCompleted();
                }
            }
        }
        target.setOnReadyHandler(new FlowControllingOnReadyHandler());
    }

    public static <V> void copyWithFlowControl(Iterable<V> source, CallStreamObserver<V> target) {
        Preconditions.checkNotNull(source, "source");
        StreamObservers.copyWithFlowControl(source.iterator(), target);
    }
}

