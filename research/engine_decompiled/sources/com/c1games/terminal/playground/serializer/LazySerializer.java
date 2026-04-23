/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground.serializer;

import com.c1games.terminal.playground.serializer.LazyDes;
import com.c1games.terminal.playground.serializer.LazySer;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import towersocket.Serializer;
import towersocket.exception.ProtocolFailure;

public class LazySerializer
implements Serializer {
    private Serializer backend;

    public LazySerializer(Serializer backend) {
        this.backend = backend;
    }

    @Override
    public byte[] ser(Object o) throws ProtocolFailure {
        return ((LazySer)o).serialized(this.backend).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public Object des(byte[] bytes2) throws ProtocolFailure {
        try {
            return new LazyDes(new String(bytes2, "UTF-8"), this.backend);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}

