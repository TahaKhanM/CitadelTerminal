/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.core.InternalApi;
import com.google.api.gax.retrying.ResultRetryAlgorithm;
import com.google.api.gax.retrying.RetryAlgorithm;
import com.google.api.gax.retrying.ServerStreamingAttemptException;
import com.google.api.gax.retrying.TimedAttemptSettings;
import com.google.api.gax.retrying.TimedRetryAlgorithm;
import java.util.concurrent.CancellationException;

@InternalApi(value="For internal use only")
public final class StreamingRetryAlgorithm<ResponseT>
extends RetryAlgorithm<ResponseT> {
    public StreamingRetryAlgorithm(ResultRetryAlgorithm<ResponseT> resultAlgorithm, TimedRetryAlgorithm timedAlgorithm) {
        super(resultAlgorithm, timedAlgorithm);
    }

    @Override
    public TimedAttemptSettings createNextAttempt(Throwable prevThrowable, ResponseT prevResponse, TimedAttemptSettings prevSettings) {
        if (prevThrowable instanceof ServerStreamingAttemptException) {
            ServerStreamingAttemptException attemptException = (ServerStreamingAttemptException)prevThrowable;
            prevThrowable = prevThrowable.getCause();
            if (attemptException.hasSeenResponses()) {
                prevSettings = this.createFirstAttempt().toBuilder().setFirstAttemptStartTimeNanos(prevSettings.getFirstAttemptStartTimeNanos()).build();
            }
        }
        return super.createNextAttempt(prevThrowable, prevResponse, prevSettings);
    }

    @Override
    public boolean shouldRetry(Throwable prevThrowable, ResponseT prevResponse, TimedAttemptSettings nextAttemptSettings) throws CancellationException {
        if (prevThrowable instanceof ServerStreamingAttemptException) {
            ServerStreamingAttemptException attemptExceptino = (ServerStreamingAttemptException)prevThrowable;
            prevThrowable = prevThrowable.getCause();
            if (!attemptExceptino.canResume()) {
                return false;
            }
        }
        return super.shouldRetry(prevThrowable, prevResponse, nextAttemptSettings);
    }
}

