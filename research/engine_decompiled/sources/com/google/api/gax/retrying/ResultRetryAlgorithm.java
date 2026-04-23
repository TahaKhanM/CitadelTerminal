/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.gax.retrying.TimedAttemptSettings;
import java.util.concurrent.CancellationException;

public interface ResultRetryAlgorithm<ResponseT> {
    public TimedAttemptSettings createNextAttempt(Throwable var1, ResponseT var2, TimedAttemptSettings var3);

    public boolean shouldRetry(Throwable var1, ResponseT var2) throws CancellationException;
}

