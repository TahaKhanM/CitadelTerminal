/*
 * Decompiled with CFR 0.152.
 */
package towersocket.exception;

import towersocket.exception.InternalException;
import towersocket.exception.ProtocolFailure;
import towersocket.exception.UserException;

public interface TowerSocketException {
    public void get() throws UserException, ProtocolFailure, InternalException;
}

