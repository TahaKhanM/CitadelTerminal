/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground.serializer;

import java.io.UnsupportedEncodingException;
import towersocket.Serializer;
import towersocket.exception.ProtocolFailure;

public class LazySer<T> {
    private T object = null;
    private String data = null;

    private LazySer() {
    }

    public static <T> LazySer<T> unserialized(T object) {
        LazySer<T> lazy = new LazySer<T>();
        lazy.object = object;
        return lazy;
    }

    public static <T> LazySer<T> serialized(String data) {
        LazySer<T> lazy = new LazySer<T>();
        lazy.data = data;
        return lazy;
    }

    public String serialized(Serializer ser) {
        if (this.data == null) {
            try {
                this.data = new String(ser.ser(this.object), "UTF-8");
            }
            catch (UnsupportedEncodingException | ProtocolFailure e) {
                throw new RuntimeException(e);
            }
        }
        return this.data;
    }
}

