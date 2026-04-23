/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.tags.propagation;

public final class TagContextDeserializationException
extends Exception {
    private static final long serialVersionUID = 0L;

    public TagContextDeserializationException(String message) {
        super(message);
    }

    public TagContextDeserializationException(String message, Throwable cause) {
        super(message, cause);
    }
}

