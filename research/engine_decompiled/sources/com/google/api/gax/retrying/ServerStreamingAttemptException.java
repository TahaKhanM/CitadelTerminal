/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.core.InternalApi;

@InternalApi
public class ServerStreamingAttemptException
extends RuntimeException {
    private final boolean canResume;
    private final boolean seenResponses;

    public ServerStreamingAttemptException(Throwable cause, boolean canResume, boolean seenResponses) {
        super(cause);
        this.canResume = canResume;
        this.seenResponses = seenResponses;
    }

    public boolean canResume() {
        return this.canResume;
    }

    public boolean hasSeenResponses() {
        return this.seenResponses;
    }
}

