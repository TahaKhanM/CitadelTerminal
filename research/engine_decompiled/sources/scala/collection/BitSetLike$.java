/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Array$;
import scala.Predef$;

public final class BitSetLike$ {
    public static final BitSetLike$ MODULE$;
    private final int LogWL;
    private final int WordLength;
    private final int MaxSize;

    static {
        new BitSetLike$();
    }

    public final int LogWL() {
        return 6;
    }

    private final int WordLength() {
        return 64;
    }

    public final int MaxSize() {
        return 0x2000000;
    }

    public long[] updateArray(long[] elems, int idx, long w) {
        int len;
        for (len = elems.length; len > 0 && (elems[len - 1] == 0L || w == 0L && idx == len - 1); --len) {
        }
        int newlen = len;
        if (idx >= len && w != 0L) {
            newlen = idx + 1;
        }
        long[] newelems = new long[newlen];
        Array$.MODULE$.copy(elems, 0, newelems, 0, len);
        if (idx < newlen) {
            newelems[idx] = w;
        } else {
            Predef$.MODULE$.assert(w == 0L);
        }
        return newelems;
    }

    private BitSetLike$() {
        MODULE$ = this;
    }
}

