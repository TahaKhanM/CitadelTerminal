/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import java.math.BigInteger;
import scala.Serializable;
import scala.math.BigInt;
import scala.util.Random;

public final class BigInt$
implements Serializable {
    public static final BigInt$ MODULE$;
    private final int minCached;
    private final int maxCached;
    private final BigInt[] cache;
    private final BigInteger scala$math$BigInt$$minusOne;

    static {
        new BigInt$();
    }

    private int minCached() {
        return this.minCached;
    }

    private int maxCached() {
        return this.maxCached;
    }

    private BigInt[] cache() {
        return this.cache;
    }

    public BigInteger scala$math$BigInt$$minusOne() {
        return this.scala$math$BigInt$$minusOne;
    }

    /*
     * WARNING - void declaration
     */
    public BigInt apply(int i) {
        BigInt bigInt;
        if (this.minCached() <= i && i <= this.maxCached()) {
            void var3_3;
            int offset = i - this.minCached();
            BigInt n = this.cache()[offset];
            if (n == null) {
                this.cache()[offset] = n = new BigInt(BigInteger.valueOf(i));
            }
            bigInt = var3_3;
        } else {
            bigInt = new BigInt(BigInteger.valueOf(i));
        }
        return bigInt;
    }

    public BigInt apply(long l) {
        return (long)this.minCached() <= l && l <= (long)this.maxCached() ? this.apply((int)l) : new BigInt(BigInteger.valueOf(l));
    }

    public BigInt apply(byte[] x) {
        return new BigInt(new BigInteger(x));
    }

    public BigInt apply(int signum, byte[] magnitude) {
        return new BigInt(new BigInteger(signum, magnitude));
    }

    public BigInt apply(int bitlength, int certainty, Random rnd) {
        return new BigInt(new BigInteger(bitlength, certainty, rnd.self()));
    }

    public BigInt apply(int numbits, Random rnd) {
        return new BigInt(new BigInteger(numbits, rnd.self()));
    }

    public BigInt apply(String x) {
        return new BigInt(new BigInteger(x));
    }

    public BigInt apply(String x, int radix) {
        return new BigInt(new BigInteger(x, radix));
    }

    public BigInt apply(BigInteger x) {
        return new BigInt(x);
    }

    public BigInt probablePrime(int bitLength, Random rnd) {
        return new BigInt(BigInteger.probablePrime(bitLength, rnd.self()));
    }

    public BigInt int2bigInt(int i) {
        return this.apply(i);
    }

    public BigInt long2bigInt(long l) {
        return this.apply(l);
    }

    public BigInt javaBigInteger2bigInt(BigInteger x) {
        return this.apply(x);
    }

    private Object readResolve() {
        return MODULE$;
    }

    private BigInt$() {
        MODULE$ = this;
        this.minCached = -1024;
        this.maxCached = 1024;
        this.cache = new BigInt[this.maxCached() - this.minCached() + 1];
        this.scala$math$BigInt$$minusOne = BigInteger.valueOf(-1L);
    }
}

