/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.longrunning;

import com.google.api.core.BetaApi;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.retrying.ResultRetryAlgorithm;
import com.google.api.gax.retrying.TimedAttemptSettings;

@BetaApi(value="The surface for long-running operations is not stable yet and may change in the future.")
public class OperationResponsePollAlgorithm
implements ResultRetryAlgorithm<OperationSnapshot> {
    @Override
    public TimedAttemptSettings createNextAttempt(Throwable prevThrowable, OperationSnapshot prevResponse, TimedAttemptSettings prevSettings) {
        return null;
    }

    @Override
    public boolean shouldRetry(Throwable prevThrowable, OperationSnapshot prevResponse) {
        return prevThrowable == null && prevResponse != null && !prevResponse.isDone();
    }
}

