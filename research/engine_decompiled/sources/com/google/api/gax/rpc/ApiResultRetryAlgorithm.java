/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.gax.retrying.ResultRetryAlgorithm;
import com.google.api.gax.retrying.TimedAttemptSettings;
import com.google.api.gax.rpc.ApiException;
import com.google.api.gax.rpc.DeadlineExceededException;
import org.threeten.bp.Duration;

class ApiResultRetryAlgorithm<ResponseT>
implements ResultRetryAlgorithm<ResponseT> {
    public static final Duration DEADLINE_SLEEP_DURATION = Duration.ofMillis(1L);

    ApiResultRetryAlgorithm() {
    }

    @Override
    public TimedAttemptSettings createNextAttempt(Throwable prevThrowable, ResponseT prevResponse, TimedAttemptSettings prevSettings) {
        if (prevThrowable != null && prevThrowable instanceof DeadlineExceededException) {
            return TimedAttemptSettings.newBuilder().setGlobalSettings(prevSettings.getGlobalSettings()).setRetryDelay(prevSettings.getRetryDelay()).setRpcTimeout(prevSettings.getRpcTimeout()).setRandomizedRetryDelay(DEADLINE_SLEEP_DURATION).setAttemptCount(prevSettings.getAttemptCount() + 1).setFirstAttemptStartTimeNanos(prevSettings.getFirstAttemptStartTimeNanos()).build();
        }
        return null;
    }

    @Override
    public boolean shouldRetry(Throwable prevThrowable, ResponseT prevResponse) {
        return prevThrowable instanceof ApiException && ((ApiException)prevThrowable).isRetryable();
    }
}

