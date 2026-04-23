/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground.scheduler;

import com.c1games.terminal.playground.scheduler.PausableProcess;
import java.time.Duration;
import java.util.Optional;

public interface PausableProcessTask<T> {
    public Optional<T> run(PausableProcess var1, Duration var2) throws Exception;
}

