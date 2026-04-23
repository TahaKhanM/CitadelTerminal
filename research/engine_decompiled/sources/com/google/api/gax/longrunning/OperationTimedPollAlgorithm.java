/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.longrunning;

import com.google.api.core.ApiClock;
import com.google.api.core.BetaApi;
import com.google.api.core.NanoClock;
import com.google.api.gax.retrying.ExponentialRetryAlgorithm;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.retrying.TimedAttemptSettings;
import java.util.concurrent.CancellationException;

@BetaApi(value="The surface for long-running operations is not stable yet and may change in the future.")
public class OperationTimedPollAlgorithm
extends ExponentialRetryAlgorithm {
    public static OperationTimedPollAlgorithm create(RetrySettings globalSettings) {
        return new OperationTimedPollAlgorithm(globalSettings, NanoClock.getDefaultClock());
    }

    public static OperationTimedPollAlgorithm create(RetrySettings globalSettings, ApiClock clock) {
        return new OperationTimedPollAlgorithm(globalSettings, clock);
    }

    private OperationTimedPollAlgorithm(RetrySettings globalSettings, ApiClock clock) {
        super(globalSettings, clock);
    }

    @Override
    public boolean shouldRetry(TimedAttemptSettings nextAttemptSettings) throws CancellationException {
        if (super.shouldRetry(nextAttemptSettings)) {
            return true;
        }
        throw new CancellationException();
    }
}

