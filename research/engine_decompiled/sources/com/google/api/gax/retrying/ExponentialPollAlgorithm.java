/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.core.ApiClock;
import com.google.api.gax.retrying.ExponentialRetryAlgorithm;
import com.google.api.gax.retrying.PollException;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.retrying.TimedAttemptSettings;

public class ExponentialPollAlgorithm
extends ExponentialRetryAlgorithm {
    public ExponentialPollAlgorithm(RetrySettings globalSettings, ApiClock clock) {
        super(globalSettings, clock);
    }

    @Override
    public boolean shouldRetry(TimedAttemptSettings nextAttemptSettings) throws PollException {
        if (super.shouldRetry(nextAttemptSettings)) {
            return true;
        }
        throw new PollException("total timeout or maximum number of attempts exceeded; current settings: " + nextAttemptSettings.getGlobalSettings());
    }
}

