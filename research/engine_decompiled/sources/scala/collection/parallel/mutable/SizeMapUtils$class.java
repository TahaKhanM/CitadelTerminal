/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.Predef$;
import scala.collection.parallel.mutable.SizeMapUtils;
import scala.runtime.RichInt$;

public abstract class SizeMapUtils$class {
    public static int calcNumElems(SizeMapUtils $this, int from2, int until2, int tableLength, int sizeMapBucketSize) {
        int n;
        int fbindex = from2 / sizeMapBucketSize;
        int lbindex = until2 / sizeMapBucketSize;
        if (fbindex == lbindex) {
            n = $this.countElems(from2, until2);
        } else {
            int n2 = (fbindex + 1) * sizeMapBucketSize;
            Predef$ predef$ = Predef$.MODULE$;
            int fbuntil = RichInt$.MODULE$.min$extension(n2, tableLength);
            int fbcount = $this.countElems(from2, fbuntil);
            int lbstart = lbindex * sizeMapBucketSize;
            int lbcount = $this.countElems(lbstart, until2);
            int inbetween = $this.countBucketSizes(fbindex + 1, lbindex);
            n = fbcount + inbetween + lbcount;
        }
        return n;
    }

    public static void $init$(SizeMapUtils $this) {
    }
}

