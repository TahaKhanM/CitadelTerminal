/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.handler.timeout;

import io.grpc.netty.shaded.io.netty.handler.timeout.TimeoutException;

public final class WriteTimeoutException
extends TimeoutException {
    private static final long serialVersionUID = -144786655770296065L;
    public static final WriteTimeoutException INSTANCE = new WriteTimeoutException();

    private WriteTimeoutException() {
    }
}

