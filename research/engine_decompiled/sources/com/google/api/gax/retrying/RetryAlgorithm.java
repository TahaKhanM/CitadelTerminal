/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.gax.retrying.ResultRetryAlgorithm;
import com.google.api.gax.retrying.TimedAttemptSettings;
import com.google.api.gax.retrying.TimedRetryAlgorithm;
import com.google.common.base.Preconditions;
import java.util.concurrent.CancellationException;

public class RetryAlgorithm<ResponseT> {
    private final ResultRetryAlgorithm<ResponseT> resultAlgorithm;
    private final TimedRetryAlgorithm timedAlgorithm;

    public RetryAlgorithm(ResultRetryAlgorithm<ResponseT> resultAlgorithm, TimedRetryAlgorithm timedAlgorithm) {
        this.resultAlgorithm = Preconditions.checkNotNull(resultAlgorithm);
        this.timedAlgorithm = Preconditions.checkNotNull(timedAlgorithm);
    }

    public TimedAttemptSettings createFirstAttempt() {
        return this.timedAlgorithm.createFirstAttempt();
    }

    public TimedAttemptSettings createNextAttempt(Throwable prevThrowable, ResponseT prevResponse, TimedAttemptSettings prevSettings) {
        if (!this.resultAlgorithm.shouldRetry(prevThrowable, prevResponse)) {
            return null;
        }
        TimedAttemptSettings newSettings = this.resultAlgorithm.createNextAttempt(prevThrowable, prevResponse, prevSettings);
        if (newSettings == null) {
            newSettings = this.timedAlgorithm.createNextAttempt(prevSettings);
        }
        return newSettings;
    }

    public boolean shouldRetry(Throwable prevThrowable, ResponseT prevResponse, TimedAttemptSettings nextAttemptSettings) throws CancellationException {
        return this.resultAlgorithm.shouldRetry(prevThrowable, prevResponse) && nextAttemptSettings != null && this.timedAlgorithm.shouldRetry(nextAttemptSettings);
    }
}

