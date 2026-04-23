/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.core.ApiFuture;
import com.google.api.gax.retrying.TimedAttemptSettings;
import java.util.concurrent.Callable;

public interface RetryingFuture<ResponseT>
extends ApiFuture<ResponseT> {
    public void setAttemptFuture(ApiFuture<ResponseT> var1);

    public Callable<ResponseT> getCallable();

    public TimedAttemptSettings getAttemptSettings();

    public ApiFuture<ResponseT> peekAttemptResult();

    public ApiFuture<ResponseT> getAttemptResult();
}

