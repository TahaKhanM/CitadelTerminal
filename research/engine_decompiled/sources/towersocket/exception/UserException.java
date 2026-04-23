/*
 * Decompiled with CFR 0.152.
 */
package towersocket.exception;

import towersocket.exception.TowerSocketException;

public class UserException
extends Exception
implements TowerSocketException {
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    @Override
    public void get() throws UserException {
        throw this;
    }
}

