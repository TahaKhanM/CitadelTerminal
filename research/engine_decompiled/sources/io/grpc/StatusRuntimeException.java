/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import io.grpc.ExperimentalApi;
import io.grpc.Metadata;
import io.grpc.Status;
import javax.annotation.Nullable;

public class StatusRuntimeException
extends RuntimeException {
    private static final long serialVersionUID = 1950934672280720624L;
    private final Status status;
    private final Metadata trailers;

    public StatusRuntimeException(Status status) {
        this(status, null);
    }

    @ExperimentalApi
    public StatusRuntimeException(Status status, @Nullable Metadata trailers) {
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

