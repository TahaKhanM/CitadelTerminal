/*
 * Decompiled with CFR 0.152.
 */
package towersocket;

import towersocket.exception.ProtocolFailure;

public interface Serializer {
    public byte[] ser(Object var1) throws ProtocolFailure;

    public Object des(byte[] var1) throws ProtocolFailure;
}

