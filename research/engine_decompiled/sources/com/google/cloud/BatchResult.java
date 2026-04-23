/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.InternalApi;
import com.google.cloud.BaseServiceException;
import com.google.common.base.Preconditions;
import java.util.LinkedList;
import java.util.List;

public abstract class BatchResult<T, E extends BaseServiceException> {
    private T result;
    private boolean completed = false;
    private E error;
    private final List<Callback<T, E>> toBeNotified = new LinkedList<Callback<T, E>>();

    @InternalApi(value="This class should only be extended within google-cloud-java")
    protected BatchResult() {
    }

    public boolean completed() {
        return this.completed;
    }

    public T get() throws E {
        Preconditions.checkState(this.completed(), "Batch has not been completed yet");
        if (this.error != null) {
            throw this.error;
        }
        return this.result;
    }

    public void notify(Callback<T, E> callback) {
        Preconditions.checkState(!this.completed, "The batch has been completed. All the calls to the notify() method should be done prior to submitting the batch.");
        this.toBeNotified.add(callback);
    }

    protected void error(E error2) {
        this.error = (BaseServiceException)Preconditions.checkNotNull(error2);
        this.completed = true;
        for (Callback<T, E> callback : this.toBeNotified) {
            callback.error(error2);
        }
    }

    protected void success(T result2) {
        this.result = result2;
        this.completed = true;
        for (Callback<T, E> callback : this.toBeNotified) {
            callback.success(result2);
        }
    }

    public static interface Callback<T, E> {
        public void success(T var1);

        public void error(E var1);
    }
}

