/*
 * Decompiled with CFR 0.152.
 */
package scala.util.hashing;

public final class package$ {
    public static final package$ MODULE$;

    static {
        new package$();
    }

    public int byteswap32(int v) {
        int hc = v * -1640532531;
        return Integer.reverseBytes(hc) * -1640532531;
    }

    public long byteswap64(long v) {
        long hc = v * -7046033566014671411L;
        return Long.reverseBytes(hc) * -7046033566014671411L;
    }

    private package$() {
        MODULE$ = this;
    }
}

