/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import com.google.api.client.util.BackOff;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Sleeper;
import java.io.IOException;

@Beta
public final class BackOffUtils {
    public static boolean next(Sleeper sleeper, BackOff backOff) throws InterruptedException, IOException {
        long backOffTime = backOff.nextBackOffMillis();
        if (backOffTime == -1L) {
            return false;
        }
        sleeper.sleep(backOffTime);
        return true;
    }

    private BackOffUtils() {
    }
}

