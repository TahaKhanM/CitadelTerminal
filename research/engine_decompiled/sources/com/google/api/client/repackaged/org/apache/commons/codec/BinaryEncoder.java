/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.repackaged.org.apache.commons.codec;

import com.google.api.client.repackaged.org.apache.commons.codec.Encoder;
import com.google.api.client.repackaged.org.apache.commons.codec.EncoderException;

public interface BinaryEncoder
extends Encoder {
    public byte[] encode(byte[] var1) throws EncoderException;
}

