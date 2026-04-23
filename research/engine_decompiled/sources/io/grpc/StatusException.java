/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import io.grpc.ExperimentalApi;
import io.grpc.Metadata;
import io.grpc.Status;
import javax.annotation.Nullable;

public class StatusException
extends Exception {
    private static final long serialVersionUID = -660954903976144640L;
    private final Status status;
    private final Metadata trailers;

    public StatusException(Status status) {
        this(status, null);
    }

    @ExperimentalApi
    public StatusException(Status status, @Nullable Metadata trailers) {
        super(Status.formatThrowableMessage(status), status.getCause());
        this.status = status;
        this.trailers = trailers;
    }

    public final Status getStatus() {
        return this.status;
    }

    @ExperimentalApi
    public final Metadata getTrailers() {
        return this.trailers;
    }
}

