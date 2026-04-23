/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.Predef$;
import scala.collection.generic.SliceInterval;
import scala.runtime.RichInt$;

public final class SliceInterval$ {
    public static final SliceInterval$ MODULE$;

    static {
        new SliceInterval$();
    }

    public SliceInterval apply(int from2, int until2) {
        Predef$ predef$ = Predef$.MODULE$;
        int lo = RichInt$.MODULE$.max$extension(from2, 0);
        Predef$ predef$2 = Predef$.MODULE$;
        int hi = RichInt$.MODULE$.max$extension(until2, 0);
        return hi <= lo ? new SliceInterval(lo, lo) : new SliceInterval(lo, hi);
    }

    private SliceInterval$() {
        MODULE$ = this;
    }
}

