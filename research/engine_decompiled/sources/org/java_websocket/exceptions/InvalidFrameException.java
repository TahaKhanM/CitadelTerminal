/*
 * Decompiled with CFR 0.152.
 */
package org.java_websocket.exceptions;

import org.java_websocket.exceptions.InvalidDataException;

public class InvalidFrameException
extends InvalidDataException {
    private static final long serialVersionUID = -9016496369828887591L;

    public InvalidFrameException() {
        super(1002);
    }

    public InvalidFrameException(String s2) {
        super(1002, s2);
    }

    public InvalidFrameException(Throwable t) {
        super(1002, t);
    }

    public InvalidFrameException(String s2, Throwable t) {
        super(1002, s2, t);
    }
}

