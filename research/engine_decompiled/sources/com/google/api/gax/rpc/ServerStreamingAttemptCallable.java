/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.SettableApiFuture;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.api.gax.retrying.ServerStreamingAttemptException;
import com.google.api.gax.retrying.StreamResumptionStrategy;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.StateCheckingResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.common.base.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import javax.annotation.concurrent.GuardedBy;
import org.threeten.bp.Duration;

final class ServerStreamingAttemptCallable<RequestT, ResponseT>
implements Callable<Void> {
    private final Object lock = new Object();
    private final ServerStreamingCallable<RequestT, ResponseT> innerCallable;
    private final StreamResumptionStrategy<RequestT, ResponseT> resumptionStrategy;
    private final RequestT initialRequest;
    private ApiCallContext context;
    private final ResponseObserver<ResponseT> outerObserver;
    private boolean autoFlowControl = true;
    private boolean isStarted;
    @GuardedBy(value="lock")
    private Throwable cancellationCause;
    @GuardedBy(value="lock")
    private int pendingRequests;
    private RetryingFuture<Void> outerRetryingFuture;
    private int numAttempts;
    @GuardedBy(value="lock")
    private StreamController innerController;
    private boolean seenSuccessSinceLastError;
    private SettableApiFuture<Void> innerAttemptFuture;

    ServerStreamingAttemptCallable(ServerStreamingCallable<RequestT, ResponseT> innerCallable, StreamResumptionStrategy<RequestT, ResponseT> resumptionStrategy, RequestT initialRequest, ApiCallContext context, ResponseObserver<ResponseT> outerObserver) {
        this.innerCallable = innerCallable;
        this.resumptionStrategy = resumptionStrategy;
        this.initialRequest = initialRequest;
        this.context = context;
        this.outerObserver = outerObserver;
    }

    void setExternalFuture(RetryingFuture<Void> retryingFuture) {
        Preconditions.checkState(!this.isStarted, "Can't change the RetryingFuture once the call has start");
        Preconditions.checkNotNull(retryingFuture, "RetryingFuture can't be null");
        this.outerRetryingFuture = retryingFuture;
    }

    public void start() {
        Preconditions.checkState(!this.isStarted, "Already started");
        this.outerObserver.onStart(new StreamController(){

            @Override
            public void disableAutoInboundFlowControl() {
                Preconditions.checkState(!ServerStreamingAttemptCallable.this.isStarted, "Can't disable auto flow control once the stream is started");
                ServerStreamingAttemptCallable.this.autoFlowControl = false;
            }

            @Override
            public void request(int count2) {
                ServerStreamingAttemptCallable.this.onRequest(count2);
            }

            @Override
            public void cancel() {
                ServerStreamingAttemptCallable.this.onCancel();
            }
        });
        if (this.autoFlowControl) {
            this.pendingRequests = Integer.MAX_VALUE;
        }
        this.isStarted = true;
        Duration totalTimeout = this.outerRetryingFuture.getAttemptSettings().getGlobalSettings().getTotalTimeout();
        if (totalTimeout != null && this.context != null) {
            this.context = this.context.withTimeout(totalTimeout);
        }
        this.call();
    }

    @Override
    public Void call() {
        Preconditions.checkState(this.isStarted, "Must be started first");
        RequestT request = ++this.numAttempts == 1 ? this.initialRequest : this.resumptionStrategy.getResumeRequest(this.initialRequest);
        Preconditions.checkState(request != null, "ResumptionStrategy returned a null request.");
        this.innerAttemptFuture = SettableApiFuture.create();
        this.seenSuccessSinceLastError = false;
        ApiCallContext attemptContext = this.context;
        if (!this.outerRetryingFuture.getAttemptSettings().getRpcTimeout().isZero()) {
            attemptContext = attemptContext.withStreamWaitTimeout(this.outerRetryingFuture.getAttemptSettings().getRpcTimeout());
        }
        this.innerCallable.call(request, new StateCheckingResponseObserver<ResponseT>(){

            @Override
            public void onStartImpl(StreamController controller) {
                ServerStreamingAttemptCallable.this.onAttemptStart(controller);
            }

            @Override
            public void onResponseImpl(ResponseT response) {
                ServerStreamingAttemptCallable.this.onAttemptResponse(response);
            }

            @Override
            public void onErrorImpl(Throwable t) {
                ServerStreamingAttemptCallable.this.onAttemptError(t);
            }

            @Override
            public void onCompleteImpl() {
                ServerStreamingAttemptCallable.this.onAttemptComplete();
            }
        }, attemptContext);
        this.outerRetryingFuture.setAttemptFuture(this.innerAttemptFuture);
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void onAttemptStart(StreamController controller) {
        Throwable localCancellationCause;
        if (!this.autoFlowControl) {
            controller.disableAutoInboundFlowControl();
        }
        int numToRequest = 0;
        Object object = this.lock;
        synchronized (object) {
            this.innerController = controller;
            localCancellationCause = this.cancellationCause;
            if (!this.autoFlowControl) {
                numToRequest = this.pendingRequests;
            }
        }
        if (localCancellationCause != null) {
            controller.cancel();
        } else if (numToRequest > 0) {
            controller.request(numToRequest);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void onCancel() {
        StreamController localInnerController;
        Object object = this.lock;
        synchronized (object) {
            if (this.cancellationCause != null) {
                return;
            }
            this.cancellationCause = new ServerStreamingAttemptException(new CancellationException("User cancelled stream"), this.resumptionStrategy.canResume(), this.seenSuccessSinceLastError);
            localInnerController = this.innerController;
        }
        if (localInnerController != null) {
            localInnerController.cancel();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void onRequest(int count2) {
        StreamController localInnerController;
        Preconditions.checkState(!this.autoFlowControl, "Automatic flow control is enabled");
        Preconditions.checkArgument(count2 > 0, "Count must be > 0");
        Object object = this.lock;
        synchronized (object) {
            int maxInc = Integer.MAX_VALUE - this.pendingRequests;
            count2 = Math.min(maxInc, count2);
            this.pendingRequests += count2;
            localInnerController = this.innerController;
        }
        if (localInnerController != null) {
            localInnerController.request(count2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void onAttemptResponse(ResponseT message) {
        if (!this.autoFlowControl) {
            Object object = this.lock;
            synchronized (object) {
                --this.pendingRequests;
            }
        }
        this.seenSuccessSinceLastError = true;
        message = this.resumptionStrategy.processResponse(message);
        this.outerObserver.onResponse(message);
    }

    private void onAttemptError(Throwable throwable) {
        if (this.cancellationCause != null) {
            this.innerAttemptFuture.setException(this.cancellationCause);
        } else {
            this.innerAttemptFuture.setException(new ServerStreamingAttemptException(throwable, this.resumptionStrategy.canResume(), this.seenSuccessSinceLastError));
        }
    }

    private void onAttemptComplete() {
        this.innerAttemptFuture.set(null);
    }
}

