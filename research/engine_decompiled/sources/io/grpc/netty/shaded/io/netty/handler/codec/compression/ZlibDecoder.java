/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;

public abstract class ZlibDecoder
extends ByteToMessageDecoder {
    public abstract boolean isClosed();
}

