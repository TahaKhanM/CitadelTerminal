/*
 * Decompiled with CFR 0.152.
 */
package towersocket.exception;

import towersocket.exception.TowerSocketException;

public class ProtocolFailure
extends Exception
implements TowerSocketException {
    public ProtocolFailure() {
    }

    public ProtocolFailure(String message) {
        super(message);
    }

    public ProtocolFailure(String message, Throwable cause) {
        super(message, cause);
    }

    public ProtocolFailure(Throwable cause) {
        super(cause);
    }

    @Override
    public void get() throws ProtocolFailure {
        throw this;
    }
}

