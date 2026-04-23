/*
 * Decompiled with CFR 0.152.
 */
package org.java_websocket.exceptions;

public class NotSendableException
extends RuntimeException {
    private static final long serialVersionUID = -6468967874576651628L;

    public NotSendableException(String s2) {
        super(s2);
    }

    public NotSendableException(Throwable t) {
        super(t);
    }

    public NotSendableException(String s2, Throwable t) {
        super(s2, t);
    }
}

