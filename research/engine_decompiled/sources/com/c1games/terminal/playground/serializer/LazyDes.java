/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground.serializer;

import java.nio.charset.StandardCharsets;
import towersocket.Serializer;
import towersocket.exception.ProtocolFailure;

public class LazyDes<T> {
    public final String data;
    private final Serializer des;
    private boolean isDeserialized = false;
    private T obj = null;

    public LazyDes(String data, Serializer des) {
        this.data = data;
        this.des = des;
    }

    public T getObj() {
        if (!this.isDeserialized) {
            try {
                this.obj = this.des.des(this.data.getBytes(StandardCharsets.UTF_8));
            }
            catch (ProtocolFailure e) {
                throw new RuntimeException(e);
            }
        }
        return this.obj;
    }
}

