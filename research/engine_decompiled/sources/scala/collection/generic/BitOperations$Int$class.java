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
import scala.runtime.RichInt$;

public abstract class BitOperations$Int$class {
    public static boolean zero(BitOperations.Int $this, int i, int mask) {
        return (i & mask) == 0;
    }

    public static int mask(BitOperations.Int $this, int i, int mask) {
        return i & ($this.complement(mask - 1) ^ mask);
    }

    public static boolean hasMatch(BitOperations.Int $this, int key, int prefix, int m) {
        return $this.mask(key, m) == prefix;
    }

    public static boolean unsignedCompare(BitOperations.Int $this, int i, int j) {
        return i < j ^ i < 0 ^ j < 0;
    }

    public static boolean shorter(BitOperations.Int $this, int m1, int m2) {
        return $this.unsignedCompare(m2, m1);
    }

    public static int complement(BitOperations.Int $this, int i) {
        return 0xFFFFFFFF ^ i;
    }

    public static IndexedSeq bits(BitOperations.Int $this, int num) {
        Predef$ predef$ = Predef$.MODULE$;
        return RichInt$.MODULE$.to$extension0(31, 0).by(-1).map(new Serializable($this, num){
            public static final long serialVersionUID = 0L;
            private final int num$2;

            public final boolean apply(int i) {
                return this.apply$mcZI$sp(i);
            }

            public boolean apply$mcZI$sp(int i) {
                return (this.num$2 >>> i & 1) != 0;
            }
            {
                this.num$2 = num$2;
            }
        }, IndexedSeq$.MODULE$.canBuildFrom());
    }

    public static String bitString(BitOperations.Int $this, int num, String sep) {
        return ((TraversableOnce)$this.bits(num).map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final String apply(boolean b) {
                return b ? "1" : "0";
            }
        }, IndexedSeq$.MODULE$.canBuildFrom())).mkString(sep);
    }

    public static String bitString$default$2(BitOperations.Int $this) {
        return "";
    }

    public static int highestOneBit(BitOperations.Int $this, int j) {
        int i = j | j >> 1;
        i |= i >> 2;
        i |= i >> 4;
        i |= i >> 8;
        i |= i >> 16;
        return i - (i >>> 1);
    }

    public static void $init$(BitOperations.Int $this) {
    }
}

