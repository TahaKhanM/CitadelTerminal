/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.handler.timeout;

import io.grpc.netty.shaded.io.netty.handler.timeout.TimeoutException;

public final class ReadTimeoutException
extends TimeoutException {
    private static final long serialVersionUID = 169287984113283421L;
    public static final ReadTimeoutException INSTANCE = new ReadTimeoutException();

    private ReadTimeoutException() {
    }
}

