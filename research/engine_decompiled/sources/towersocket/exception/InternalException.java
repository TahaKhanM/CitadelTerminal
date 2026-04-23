/*
 * Decompiled with CFR 0.152.
 */
package towersocket.exception;

import towersocket.exception.TowerSocketException;

public class InternalException
extends RuntimeException
implements TowerSocketException {
    public InternalException() {
    }

    public InternalException(String message) {
        super(message);
    }

    public InternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalException(Throwable cause) {
        super(cause);
    }

    @Override
    public void get() throws InternalException {
        throw this;
    }
}

