/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiClock;
import com.google.api.core.InternalApi;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StateCheckingResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.api.gax.rpc.WatchdogTimeoutException;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import org.threeten.bp.Duration;

@InternalApi
public class Watchdog
implements Runnable {
    private static Object PRESENT = new Object();
    private final ConcurrentHashMap<WatchdogStream, Object> openStreams = new ConcurrentHashMap();
    private final ApiClock clock;

    public Watchdog(ApiClock clock) {
        this.clock = Preconditions.checkNotNull(clock, "clock can't be null");
    }

    public <ResponseT> ResponseObserver<ResponseT> watch(ResponseObserver<ResponseT> innerObserver, @Nonnull Duration waitTimeout, @Nonnull Duration idleTimeout) {
        Preconditions.checkNotNull(innerObserver, "innerObserver can't be null");
        Preconditions.checkNotNull(waitTimeout, "waitTimeout can't be null");
        Preconditions.checkNotNull(idleTimeout, "idleTimeout can't be null");
        if (waitTimeout.isZero() && idleTimeout.isZero()) {
            return innerObserver;
        }
        WatchdogStream<ResponseT> stream = new WatchdogStream<ResponseT>(innerObserver, waitTimeout, idleTimeout);
        this.openStreams.put(stream, PRESENT);
        return stream;
    }

    @Override
    public void run() {
        Iterator<Map.Entry<WatchdogStream, Object>> it = this.openStreams.entrySet().iterator();
        while (it.hasNext()) {
            WatchdogStream stream = it.next().getKey();
            if (!stream.cancelIfStale()) continue;
            it.remove();
        }
    }

    class WatchdogStream<ResponseT>
    extends StateCheckingResponseObserver<ResponseT> {
        private final Object lock = new Object();
        private final Duration waitTimeout;
        private final Duration idleTimeout;
        private boolean hasStarted;
        private boolean autoAutoFlowControl = true;
        private final ResponseObserver<ResponseT> outerResponseObserver;
        private StreamController innerController;
        @GuardedBy(value="lock")
        private State state = State.IDLE;
        @GuardedBy(value="lock")
        private int pendingCount = 0;
        @GuardedBy(value="lock")
        private long lastActivityAt = Watchdog.access$000(Watchdog.this).millisTime();
        private volatile Throwable error;

        WatchdogStream(ResponseObserver<ResponseT> responseObserver, Duration waitTimeout, Duration idleTimeout) {
            this.waitTimeout = waitTimeout;
            this.idleTimeout = idleTimeout;
            this.outerResponseObserver = responseObserver;
        }

        @Override
        public void onStartImpl(StreamController controller) {
            this.innerController = controller;
            this.outerResponseObserver.onStart(new StreamController(){

                @Override
                public void disableAutoInboundFlowControl() {
                    Preconditions.checkState(!WatchdogStream.this.hasStarted, "Can't disable automatic flow control after the stream has started");
                    WatchdogStream.this.autoAutoFlowControl = false;
                    WatchdogStream.this.innerController.disableAutoInboundFlowControl();
                }

                @Override
                public void request(int count2) {
                    WatchdogStream.this.onRequest(count2);
                }

                @Override
                public void cancel() {
                    WatchdogStream.this.onCancel();
                }
            });
            this.hasStarted = true;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void onRequest(int count2) {
            Preconditions.checkArgument(count2 > 0, "count must be > 0");
            Preconditions.checkState(!this.autoAutoFlowControl, "Auto flow control is enabled");
            Object object = this.lock;
            synchronized (object) {
                if (this.state == State.IDLE) {
                    this.state = State.WAITING;
                    this.lastActivityAt = Watchdog.this.clock.millisTime();
                }
                int maxIncrement = Integer.MAX_VALUE - this.pendingCount;
                count2 = Math.min(maxIncrement, count2);
                this.pendingCount += count2;
            }
            this.innerController.request(count2);
        }

        private void onCancel() {
            this.error = new CancellationException("User cancelled stream");
            this.innerController.cancel();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void onResponseImpl(ResponseT response) {
            Object object = this.lock;
            synchronized (object) {
                this.state = State.DELIVERING;
            }
            this.outerResponseObserver.onResponse(response);
            object = this.lock;
            synchronized (object) {
                --this.pendingCount;
                this.lastActivityAt = Watchdog.this.clock.millisTime();
                this.state = this.autoAutoFlowControl || this.pendingCount > 0 ? State.WAITING : State.IDLE;
            }
        }

        @Override
        public void onErrorImpl(Throwable t) {
            if (this.error != null) {
                t = this.error;
            }
            Watchdog.this.openStreams.remove(this);
            this.outerResponseObserver.onError(t);
        }

        @Override
        public void onCompleteImpl() {
            Watchdog.this.openStreams.remove(this);
            this.outerResponseObserver.onComplete();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        boolean cancelIfStale() {
            WatchdogTimeoutException myError = null;
            Object object = this.lock;
            synchronized (object) {
                long waitTime = Watchdog.this.clock.millisTime() - this.lastActivityAt;
                switch (this.state) {
                    case IDLE: {
                        if (this.idleTimeout.isZero() || waitTime < this.idleTimeout.toMillis()) break;
                        myError = new WatchdogTimeoutException("Canceled due to idle connection", false);
                        break;
                    }
                    case WAITING: {
                        if (this.waitTimeout.isZero() || waitTime < this.waitTimeout.toMillis()) break;
                        myError = new WatchdogTimeoutException("Canceled due to timeout waiting for next response", true);
                    }
                }
            }
            if (myError != null) {
                this.error = myError;
                this.innerController.cancel();
                return true;
            }
            return false;
        }
    }

    static enum State {
        IDLE,
        WAITING,
        DELIVERING;

    }
}

