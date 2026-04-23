/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.gax.retrying.TimedAttemptSettings;
import java.util.concurrent.CancellationException;

public interface TimedRetryAlgorithm {
    public TimedAttemptSettings createFirstAttempt();

    public TimedAttemptSettings createNextAttempt(TimedAttemptSettings var1);

    public boolean shouldRetry(TimedAttemptSettings var1) throws CancellationException;
}

