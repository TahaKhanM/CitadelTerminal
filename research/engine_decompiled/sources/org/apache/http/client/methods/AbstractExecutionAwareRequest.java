/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.client.methods;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.AbortableHttpRequest;
import org.apache.http.client.methods.HttpExecutionAware;
import org.apache.http.client.utils.CloneUtils;
import org.apache.http.concurrent.Cancellable;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.message.AbstractHttpMessage;

public abstract class AbstractExecutionAwareRequest
extends AbstractHttpMessage
implements HttpExecutionAware,
AbortableHttpRequest,
Cloneable,
HttpRequest {
    private final AtomicBoolean aborted = new AtomicBoolean(false);
    private final AtomicReference<Cancellable> cancellableRef = new AtomicReference<Object>(null);

    protected AbstractExecutionAwareRequest() {
    }

    @Override
    @Deprecated
    public void setConnectionRequest(final ClientConnectionRequest connRequest) {
        this.setCancellable(new Cancellable(){

            @Override
            public boolean cancel() {
                connRequest.abortRequest();
                return true;
            }
        });
    }

    @Override
    @Deprecated
    public void setReleaseTrigger(final ConnectionReleaseTrigger releaseTrigger) {
        this.setCancellable(new Cancellable(){

            @Override
            public boolean cancel() {
                try {
                    releaseTrigger.abortConnection();
                    return true;
                }
                catch (IOException ex) {
                    return false;
                }
            }
        });
    }

    @Override
    public void abort() {
        Cancellable cancellable;
        if (this.aborted.compareAndSet(false, true) && (cancellable = (Cancellable)this.cancellableRef.getAndSet(null)) != null) {
            cancellable.cancel();
        }
    }

    @Override
    public boolean isAborted() {
        return this.aborted.get();
    }

    @Override
    public void setCancellable(Cancellable cancellable) {
        if (!this.aborted.get()) {
            this.cancellableRef.set(cancellable);
        }
    }

    public Object clone() throws CloneNotSupportedException {
        AbstractExecutionAwareRequest clone2 = (AbstractExecutionAwareRequest)super.clone();
        clone2.headergroup = CloneUtils.cloneObject(this.headergroup);
        clone2.params = CloneUtils.cloneObject(this.params);
        return clone2;
    }

    public void completed() {
        this.cancellableRef.set(null);
    }

    public void reset() {
        Cancellable cancellable = this.cancellableRef.getAndSet(null);
        if (cancellable != null) {
            cancellable.cancel();
        }
        this.aborted.set(false);
    }
}

