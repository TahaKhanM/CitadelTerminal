/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Tuple2;
import scala.collection.generic.BitOperations;
import scala.collection.generic.BitOperations$Long$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.LongMap;
import scala.collection.immutable.LongMap$Nil$;

public final class LongMapUtils$
implements BitOperations.Long {
    public static final LongMapUtils$ MODULE$;

    static {
        new LongMapUtils$();
    }

    @Override
    public boolean zero(long i, long mask) {
        return BitOperations$Long$class.zero(this, i, mask);
    }

    @Override
    public long mask(long i, long mask) {
        return BitOperations$Long$class.mask(this, i, mask);
    }

    @Override
    public boolean hasMatch(long key, long prefix, long m) {
        return BitOperations$Long$class.hasMatch(this, key, prefix, m);
    }

    @Override
    public boolean unsignedCompare(long i, long j) {
        return BitOperations$Long$class.unsignedCompare(this, i, j);
    }

    @Override
    public boolean shorter(long m1, long m2) {
        return BitOperations$Long$class.shorter(this, m1, m2);
    }

    @Override
    public long complement(long i) {
        return BitOperations$Long$class.complement(this, i);
    }

    @Override
    public IndexedSeq<Object> bits(long num) {
        return BitOperations$Long$class.bits(this, num);
    }

    @Override
    public String bitString(long num, String sep) {
        return BitOperations$Long$class.bitString(this, num, sep);
    }

    @Override
    public long highestOneBit(long j) {
        return BitOperations$Long$class.highestOneBit(this, j);
    }

    @Override
    public String bitString$default$2() {
        return BitOperations$Long$class.bitString$default$2(this);
    }

    public long branchMask(long i, long j) {
        return this.highestOneBit(i ^ j);
    }

    public <T> LongMap<T> join(long p1, LongMap<T> t1, long p2, LongMap<T> t2) {
        long m = this.branchMask(p1, p2);
        long p = this.mask(p1, m);
        return this.zero(p1, m) ? new LongMap.Bin<T>(p, m, t1, t2) : new LongMap.Bin<T>(p, m, t2, t1);
    }

    public <T> LongMap<T> bin(long prefix, long mask, LongMap<T> left, LongMap<T> right) {
        Tuple2<LongMap<T>, LongMap<T>> tuple2 = new Tuple2<LongMap<T>, LongMap<T>>(left, right);
        LongMap<T> longMap = ((Object)LongMap$Nil$.MODULE$).equals(tuple2._2()) ? tuple2._1() : (((Object)LongMap$Nil$.MODULE$).equals(tuple2._1()) ? tuple2._2() : new LongMap.Bin<T>(prefix, mask, tuple2._1(), tuple2._2()));
        return longMap;
    }

    private LongMapUtils$() {
        MODULE$ = this;
        BitOperations$Long$class.$init$(this);
    }
}

