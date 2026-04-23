/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.util;

import java.io.IOException;
import java.io.InputStream;

public class ZipDecryptInputStream
extends InputStream {
    private static final int[] CRC_TABLE = new int[256];
    private static final int DECRYPT_HEADER_SIZE = 12;
    private static final int[] LFH_SIGNATURE;
    private final InputStream delegate;
    private final String password;
    private final int[] keys = new int[3];
    private State state = State.SIGNATURE;
    private int skipBytes;
    private int compressedSize;
    private int value;
    private int valuePos;
    private int valueInc;

    public ZipDecryptInputStream(InputStream stream, String password) {
        this.delegate = stream;
        this.password = password;
    }

    @Override
    public int read() throws IOException {
        int result2 = this.delegate.read();
        if (this.skipBytes == 0) {
            switch (this.state) {
                case SIGNATURE: {
                    if (result2 != LFH_SIGNATURE[this.valuePos]) {
                        this.state = State.TAIL;
                        break;
                    }
                    ++this.valuePos;
                    if (this.valuePos < LFH_SIGNATURE.length) break;
                    this.skipBytes = 2;
                    this.state = State.FLAGS;
                    break;
                }
                case FLAGS: {
                    if ((result2 & 1) == 0) {
                        throw new IllegalStateException("ZIP not password protected.");
                    }
                    if ((result2 & 0x40) == 64) {
                        throw new IllegalStateException("Strong encryption used.");
                    }
                    if ((result2 & 8) == 8) {
                        throw new IllegalStateException("Unsupported ZIP format.");
                    }
                    --result2;
                    this.compressedSize = 0;
                    this.valuePos = 0;
                    this.valueInc = 12;
                    this.state = State.COMPRESSED_SIZE;
                    this.skipBytes = 11;
                    break;
                }
                case COMPRESSED_SIZE: {
                    this.compressedSize += result2 << 8 * this.valuePos;
                    if ((result2 -= this.valueInc) < 0) {
                        this.valueInc = 1;
                        result2 += 256;
                    } else {
                        this.valueInc = 0;
                    }
                    ++this.valuePos;
                    if (this.valuePos <= 3) break;
                    this.valuePos = 0;
                    this.value = 0;
                    this.state = State.FN_LENGTH;
                    this.skipBytes = 4;
                    break;
                }
                case FN_LENGTH: 
                case EF_LENGTH: {
                    this.value += result2 << 8 * this.valuePos;
                    if (this.valuePos == 1) {
                        this.valuePos = 0;
                        if (this.state == State.FN_LENGTH) {
                            this.state = State.EF_LENGTH;
                            break;
                        }
                        this.state = State.HEADER;
                        this.skipBytes = this.value;
                        break;
                    }
                    this.valuePos = 1;
                    break;
                }
                case HEADER: {
                    this.initKeys(this.password);
                    for (int i = 0; i < 12; ++i) {
                        this.updateKeys((byte)(result2 ^ this.decryptByte()));
                        result2 = this.delegate.read();
                    }
                    this.compressedSize -= 12;
                    this.state = State.DATA;
                }
                case DATA: {
                    result2 = (result2 ^ this.decryptByte()) & 0xFF;
                    this.updateKeys((byte)result2);
                    --this.compressedSize;
                    if (this.compressedSize != 0) break;
                    this.valuePos = 0;
                    this.state = State.SIGNATURE;
                    break;
                }
            }
        } else {
            --this.skipBytes;
        }
        return result2;
    }

    @Override
    public void close() throws IOException {
        this.delegate.close();
        super.close();
    }

    private void initKeys(String password) {
        this.keys[0] = 305419896;
        this.keys[1] = 591751049;
        this.keys[2] = 878082192;
        for (int i = 0; i < password.length(); ++i) {
            this.updateKeys((byte)(password.charAt(i) & 0xFF));
        }
    }

    private void updateKeys(byte charAt) {
        this.keys[0] = this.crc32(this.keys[0], charAt);
        this.keys[1] = this.keys[1] + (this.keys[0] & 0xFF);
        this.keys[1] = this.keys[1] * 134775813 + 1;
        this.keys[2] = this.crc32(this.keys[2], (byte)(this.keys[1] >> 24));
    }

    private byte decryptByte() {
        int temp = this.keys[2] | 2;
        return (byte)(temp * (temp ^ 1) >>> 8);
    }

    private int crc32(int oldCrc, byte charAt) {
        return oldCrc >>> 8 ^ CRC_TABLE[(oldCrc ^ charAt) & 0xFF];
    }

    static {
        for (int i = 0; i < 256; ++i) {
            int r = i;
            for (int j = 0; j < 8; ++j) {
                if ((r & 1) == 1) {
                    r = r >>> 1 ^ 0xEDB88320;
                    continue;
                }
                r >>>= 1;
            }
            ZipDecryptInputStream.CRC_TABLE[i] = r;
        }
        LFH_SIGNATURE = new int[]{80, 75, 3, 4};
    }

    private static enum State {
        SIGNATURE,
        FLAGS,
        COMPRESSED_SIZE,
        FN_LENGTH,
        EF_LENGTH,
        HEADER,
        DATA,
        TAIL;

    }
}

