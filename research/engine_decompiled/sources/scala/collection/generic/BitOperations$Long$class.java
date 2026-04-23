/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.Predef$;
import scala.Serializable;
import scala.collection.TraversableOnce;
import scala.collection.generic.BitOperations;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.IndexedSeq$;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichLong;

public abstract class BitOperations$Long$class {
    public static boolean zero(BitOperations.Long $this, long i, long mask) {
        return (i & mask) == 0L;
    }

    public static long mask(BitOperations.Long $this, long i, long mask) {
        return i & ($this.complement(mask - 1L) ^ mask);
    }

    public static boolean hasMatch(BitOperations.Long $this, long key, long prefix, long m) {
        return $this.mask(key, m) == prefix;
    }

    public static boolean unsignedCompare(BitOperations.Long $this, long i, long j) {
        return i < j ^ i < 0L ^ j < 0L;
    }

    public static boolean shorter(BitOperations.Long $this, long m1, long m2) {
        return $this.unsignedCompare(m2, m1);
    }

    public static long complement(BitOperations.Long $this, long i) {
        return 0xFFFFFFFFFFFFFFFFL ^ i;
    }

    public static IndexedSeq bits(BitOperations.Long $this, long num) {
        Predef$ predef$ = Predef$.MODULE$;
        return new RichLong(63L).to(BoxesRunTime.boxToLong(0L)).by(BoxesRunTime.boxToLong(-1L)).map(new Serializable($this, num){
            public static final long serialVersionUID = 0L;
            private final long num$1;

            public final boolean apply(long i) {
                return this.apply$mcZJ$sp(i);
            }

            public boolean apply$mcZJ$sp(long i) {
                return (this.num$1 >>> (int)i & 1L) != 0L;
            }
            {
                this.num$1 = num$1;
            }
        }, IndexedSeq$.MODULE$.canBuildFrom());
    }

    public static String bitString(BitOperations.Long $this, long num, String sep) {
        return ((TraversableOnce)$this.bits(num).map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final String apply(boolean b) {
                return b ? "1" : "0";
            }
        }, IndexedSeq$.MODULE$.canBuildFrom())).mkString(sep);
    }

    public static String bitString$default$2(BitOperations.Long $this) {
        return "";
    }

    public static long highestOneBit(BitOperations.Long $this, long j) {
        long i = j | j >> 1;
        i |= i >> 2;
        i |= i >> 4;
        i |= i >> 8;
        i |= i >> 16;
        i |= i >> 32;
        return i - (i >>> 1);
    }

    public static void $init$(BitOperations.Long $this) {
    }
}

