/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.pickling;

public final class ByteCodecs$ {
    public static final ByteCodecs$ MODULE$;

    static {
        new ByteCodecs$();
    }

    public byte[] avoidZero(byte[] src) {
        int i;
        int srclen = src.length;
        int count2 = 0;
        for (i = 0; i < srclen; ++i) {
            if (src[i] != 127) continue;
            ++count2;
        }
        byte[] dst = new byte[srclen + count2];
        int j = 0;
        for (i = 0; i < srclen; ++i) {
            byte in = src[i];
            if (in == 127) {
                dst[j] = (byte)192;
                dst[j + 1] = (byte)128;
                j += 2;
                continue;
            }
            dst[j] = (byte)(in + 1);
            ++j;
        }
        return dst;
    }

    public int regenerateZero(byte[] src) {
        int i = 0;
        int srclen = src.length;
        int j = 0;
        while (i < srclen) {
            int in = src[i] & 0xFF;
            if (in == 192 && (src[i + 1] & 0xFF) == 128) {
                src[j] = 127;
                i += 2;
            } else if (in == 0) {
                src[j] = 127;
                ++i;
            } else {
                src[j] = (byte)(in - 1);
                ++i;
            }
            ++j;
        }
        return j;
    }

    public byte[] encode8to7(byte[] src) {
        int srclen = src.length;
        int dstlen = (srclen * 8 + 6) / 7;
        byte[] dst = new byte[dstlen];
        int i = 0;
        int j = 0;
        while (i + 6 < srclen) {
            int in = src[i] & 0xFF;
            dst[j] = (byte)(in & 0x7F);
            int out = in >>> 7;
            in = src[i + 1] & 0xFF;
            dst[j + 1] = (byte)(out | in << 1 & 0x7F);
            out = in >>> 6;
            in = src[i + 2] & 0xFF;
            dst[j + 2] = (byte)(out | in << 2 & 0x7F);
            out = in >>> 5;
            in = src[i + 3] & 0xFF;
            dst[j + 3] = (byte)(out | in << 3 & 0x7F);
            out = in >>> 4;
            in = src[i + 4] & 0xFF;
            dst[j + 4] = (byte)(out | in << 4 & 0x7F);
            out = in >>> 3;
            in = src[i + 5] & 0xFF;
            dst[j + 5] = (byte)(out | in << 5 & 0x7F);
            out = in >>> 2;
            in = src[i + 6] & 0xFF;
            dst[j + 6] = (byte)(out | in << 6 & 0x7F);
            out = in >>> 1;
            dst[j + 7] = (byte)out;
            i += 7;
            j += 8;
        }
        if (i < srclen) {
            int in = src[i] & 0xFF;
            dst[j] = (byte)(in & 0x7F);
            ++j;
            int out = in >>> 7;
            if (i + 1 < srclen) {
                in = src[i + 1] & 0xFF;
                dst[j] = (byte)(out | in << 1 & 0x7F);
                ++j;
                out = in >>> 6;
                if (i + 2 < srclen) {
                    in = src[i + 2] & 0xFF;
                    dst[j] = (byte)(out | in << 2 & 0x7F);
                    ++j;
                    out = in >>> 5;
                    if (i + 3 < srclen) {
                        in = src[i + 3] & 0xFF;
                        dst[j] = (byte)(out | in << 3 & 0x7F);
                        ++j;
                        out = in >>> 4;
                        if (i + 4 < srclen) {
                            in = src[i + 4] & 0xFF;
                            dst[j] = (byte)(out | in << 4 & 0x7F);
                            ++j;
                            out = in >>> 3;
                            if (i + 5 < srclen) {
                                in = src[i + 5] & 0xFF;
                                dst[j] = (byte)(out | in << 5 & 0x7F);
                                ++j;
                                out = in >>> 2;
                            }
                        }
                    }
                }
            }
            if (j < dstlen) {
                dst[j] = (byte)out;
            }
        }
        return dst;
    }

    public int decode7to8(byte[] src, int srclen) {
        int i = 0;
        int j = 0;
        int dstlen = (srclen * 7 + 7) / 8;
        while (i + 7 < srclen) {
            int out = src[i];
            byte in = src[i + 1];
            src[j] = (byte)(out | (in & 1) << 7);
            out = in >>> 1;
            in = src[i + 2];
            src[j + 1] = (byte)(out | (in & 3) << 6);
            out = in >>> 2;
            in = src[i + 3];
            src[j + 2] = (byte)(out | (in & 7) << 5);
            out = in >>> 3;
            in = src[i + 4];
            src[j + 3] = (byte)(out | (in & 0xF) << 4);
            out = in >>> 4;
            in = src[i + 5];
            src[j + 4] = (byte)(out | (in & 0x1F) << 3);
            out = in >>> 5;
            in = src[i + 6];
            src[j + 5] = (byte)(out | (in & 0x3F) << 2);
            out = in >>> 6;
            in = src[i + 7];
            src[j + 6] = (byte)(out | in << 1);
            i += 8;
            j += 7;
        }
        if (i < srclen) {
            int out = src[i];
            if (i + 1 < srclen) {
                byte in = src[i + 1];
                src[j] = (byte)(out | (in & 1) << 7);
                ++j;
                out = in >>> 1;
                if (i + 2 < srclen) {
                    in = src[i + 2];
                    src[j] = (byte)(out | (in & 3) << 6);
                    ++j;
                    out = in >>> 2;
                    if (i + 3 < srclen) {
                        in = src[i + 3];
                        src[j] = (byte)(out | (in & 7) << 5);
                        ++j;
                        out = in >>> 3;
                        if (i + 4 < srclen) {
                            in = src[i + 4];
                            src[j] = (byte)(out | (in & 0xF) << 4);
                            ++j;
                            out = in >>> 4;
                            if (i + 5 < srclen) {
                                in = src[i + 5];
                                src[j] = (byte)(out | (in & 0x1F) << 3);
                                ++j;
                                out = in >>> 5;
                                if (i + 6 < srclen) {
                                    in = src[i + 6];
                                    src[j] = (byte)(out | (in & 0x3F) << 2);
                                    ++j;
                                    out = in >>> 6;
                                }
                            }
                        }
                    }
                }
            }
            if (j < dstlen) {
                src[j] = (byte)out;
            }
        }
        return dstlen;
    }

    public byte[] encode(byte[] xs) {
        return this.avoidZero(this.encode8to7(xs));
    }

    public int decode(byte[] xs) {
        int len = this.regenerateZero(xs);
        return this.decode7to8(xs, len);
    }

    private ByteCodecs$() {
        MODULE$ = this;
    }
}

