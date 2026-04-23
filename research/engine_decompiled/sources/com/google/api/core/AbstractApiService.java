/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.core;

import com.google.api.core.ApiService;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.AbstractService;
import com.google.common.util.concurrent.Service;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class AbstractApiService
implements ApiService {
    private static final ImmutableMap<Service.State, ApiService.State> guavaToGaxState = ImmutableMap.builder().put(Service.State.FAILED, ApiService.State.FAILED).put(Service.State.NEW, ApiService.State.NEW).put(Service.State.RUNNING, ApiService.State.RUNNING).put(Service.State.STARTING, ApiService.State.STARTING).put(Service.State.STOPPING, ApiService.State.STOPPING).put(Service.State.TERMINATED, ApiService.State.TERMINATED).build();
    private final InnerService impl = new InnerService();

    protected AbstractApiService() {
    }

    protected abstract void doStart();

    protected abstract void doStop();

    @Override
    public void addListener(final ApiService.Listener listener, Executor executor) {
        this.impl.addListener(new Service.Listener(){

            @Override
            public void failed(Service.State from2, Throwable failure) {
                listener.failed((ApiService.State)((Object)guavaToGaxState.get((Object)from2)), failure);
            }

            @Override
            public void running() {
                listener.running();
            }

            @Override
            public void starting() {
                listener.starting();
            }

            @Override
            public void stopping(Service.State from2) {
                listener.stopping((ApiService.State)((Object)guavaToGaxState.get((Object)from2)));
            }

            @Override
            public void terminated(Service.State from2) {
                listener.terminated((ApiService.State)((Object)guavaToGaxState.get((Object)from2)));
            }
        }, executor);
    }

    @Override
    public void awaitRunning() {
        this.impl.awaitRunning();
    }

    @Override
    public void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException {
        this.impl.awaitRunning(timeout, unit);
    }

    @Override
    public void awaitTerminated() {
        this.impl.awaitTerminated();
    }

    @Override
    public void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException {
        this.impl.awaitTerminated(timeout, unit);
    }

    @Override
    public Throwable failureCause() {
        return this.impl.failureCause();
    }

    @Override
    public boolean isRunning() {
        return this.impl.isRunning();
    }

    @Override
    public ApiService startAsync() {
        this.impl.startAsync();
        return this;
    }

    @Override
    public ApiService.State state() {
        return guavaToGaxState.get((Object)this.impl.state());
    }

    @Override
    public ApiService stopAsync() {
        this.impl.stopAsync();
        return this;
    }

    protected void notifyStarted() {
        this.impl.innerNotifyStarted();
    }

    protected void notifyStopped() {
        this.impl.innerNotifyStopped();
    }

    protected void notifyFailed(Throwable cause) {
        this.impl.innerNotifyFailed(cause);
    }

    private class InnerService
    extends AbstractService {
        private InnerService() {
        }

        @Override
        protected void doStart() {
            AbstractApiService.this.doStart();
        }

        @Override
        protected void doStop() {
            AbstractApiService.this.doStop();
        }

        private void innerNotifyStarted() {
            this.notifyStarted();
        }

        private void innerNotifyStopped() {
            this.notifyStopped();
        }

        private void innerNotifyFailed(Throwable cause) {
            this.notifyFailed(cause);
        }
    }
}

