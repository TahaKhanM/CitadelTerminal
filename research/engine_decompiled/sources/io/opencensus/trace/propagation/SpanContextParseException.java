/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.propagation;

public final class SpanContextParseException
extends Exception {
    private static final long serialVersionUID = 0L;

    public SpanContextParseException(String message) {
        super(message);
    }

    public SpanContextParseException(String message, Throwable cause) {
        super(message, cause);
    }
}

