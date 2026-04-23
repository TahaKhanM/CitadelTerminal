/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.repackaged.org.apache.commons.codec;

import com.google.api.client.repackaged.org.apache.commons.codec.Decoder;
import com.google.api.client.repackaged.org.apache.commons.codec.DecoderException;

public interface BinaryDecoder
extends Decoder {
    public byte[] decode(byte[] var1) throws DecoderException;
}

