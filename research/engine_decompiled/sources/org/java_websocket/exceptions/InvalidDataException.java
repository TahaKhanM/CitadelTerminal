/*
 * Decompiled with CFR 0.152.
 */
package org.java_websocket.exceptions;

public class InvalidDataException
extends Exception {
    private static final long serialVersionUID = 3731842424390998726L;
    private final int closecode;

    public InvalidDataException(int closecode) {
        this.closecode = closecode;
    }

    public InvalidDataException(int closecode, String s2) {
        super(s2);
        this.closecode = closecode;
    }

    public InvalidDataException(int closecode, Throwable t) {
        super(t);
        this.closecode = closecode;
    }

    public InvalidDataException(int closecode, String s2, Throwable t) {
        super(s2, t);
        this.closecode = closecode;
    }

    public int getCloseCode() {
        return this.closecode;
    }
}

