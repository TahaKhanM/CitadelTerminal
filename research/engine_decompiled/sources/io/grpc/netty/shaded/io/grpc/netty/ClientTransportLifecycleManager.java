/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.Status;
import io.grpc.internal.ManagedClientTransport;

final class ClientTransportLifecycleManager {
    private final ManagedClientTransport.Listener listener;
    private boolean transportReady;
    private boolean transportShutdown;
    private boolean transportInUse;
    private Status shutdownStatus;
    private Throwable shutdownThrowable;
    private boolean transportTerminated;

    public ClientTransportLifecycleManager(ManagedClientTransport.Listener listener) {
        this.listener = listener;
    }

    public void notifyReady() {
        if (this.transportReady || this.transportShutdown) {
            return;
        }
        this.transportReady = true;
        this.listener.transportReady();
    }

    public void notifyShutdown(Status s2) {
        if (this.transportShutdown) {
            return;
        }
        this.transportShutdown = true;
        this.shutdownStatus = s2;
        this.shutdownThrowable = s2.asException();
        this.listener.transportShutdown(s2);
    }

    public void notifyInUse(boolean inUse) {
        if (inUse == this.transportInUse) {
            return;
        }
        this.transportInUse = inUse;
        this.listener.transportInUse(inUse);
    }

    public void notifyTerminated(Status s2) {
        if (this.transportTerminated) {
            return;
        }
        this.transportTerminated = true;
        this.notifyShutdown(s2);
        this.listener.transportTerminated();
    }

    public Status getShutdownStatus() {
        return this.shutdownStatus;
    }

    public Throwable getShutdownThrowable() {
        return this.shutdownThrowable;
    }
}

