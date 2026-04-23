/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.gax.retrying.ResultRetryAlgorithm;
import com.google.api.gax.retrying.TimedAttemptSettings;

public class BasicResultRetryAlgorithm<ResponseT>
implements ResultRetryAlgorithm<ResponseT> {
    @Override
    public TimedAttemptSettings createNextAttempt(Throwable prevThrowable, ResponseT prevResponse, TimedAttemptSettings prevSettings) {
        return null;
    }

    @Override
    public boolean shouldRetry(Throwable prevThrowable, ResponseT prevResponse) {
        return prevThrowable != null;
    }
}

