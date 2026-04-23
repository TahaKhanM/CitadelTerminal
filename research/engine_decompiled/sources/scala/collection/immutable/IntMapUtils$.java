/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Tuple2;
import scala.collection.generic.BitOperations;
import scala.collection.generic.BitOperations$Int$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.IntMap;
import scala.collection.immutable.IntMap$Nil$;

public final class IntMapUtils$
implements BitOperations.Int {
    public static final IntMapUtils$ MODULE$;

    static {
        new IntMapUtils$();
    }

    @Override
    public boolean zero(int i, int mask) {
        return BitOperations$Int$class.zero(this, i, mask);
    }

    @Override
    public int mask(int i, int mask) {
        return BitOperations$Int$class.mask(this, i, mask);
    }

    @Override
    public boolean hasMatch(int key, int prefix, int m) {
        return BitOperations$Int$class.hasMatch(this, key, prefix, m);
    }

    @Override
    public boolean unsignedCompare(int i, int j) {
        return BitOperations$Int$class.unsignedCompare(this, i, j);
    }

    @Override
    public boolean shorter(int m1, int m2) {
        return BitOperations$Int$class.shorter(this, m1, m2);
    }

    @Override
    public int complement(int i) {
        return BitOperations$Int$class.complement(this, i);
    }

    @Override
    public IndexedSeq<Object> bits(int num) {
        return BitOperations$Int$class.bits(this, num);
    }

    @Override
    public String bitString(int num, String sep) {
        return BitOperations$Int$class.bitString(this, num, sep);
    }

    @Override
    public int highestOneBit(int j) {
        return BitOperations$Int$class.highestOneBit(this, j);
    }

    @Override
    public String bitString$default$2() {
        return BitOperations$Int$class.bitString$default$2(this);
    }

    public int branchMask(int i, int j) {
        return this.highestOneBit(i ^ j);
    }

    public <T> IntMap<T> join(int p1, IntMap<T> t1, int p2, IntMap<T> t2) {
        int m = this.branchMask(p1, p2);
        int p = this.mask(p1, m);
        return this.zero(p1, m) ? new IntMap.Bin<T>(p, m, t1, t2) : new IntMap.Bin<T>(p, m, t2, t1);
    }

    public <T> IntMap<T> bin(int prefix, int mask, IntMap<T> left, IntMap<T> right) {
        Tuple2<IntMap<T>, IntMap<T>> tuple2 = new Tuple2<IntMap<T>, IntMap<T>>(left, right);
        IntMap<T> intMap = ((Object)IntMap$Nil$.MODULE$).equals(tuple2._2()) ? tuple2._1() : (((Object)IntMap$Nil$.MODULE$).equals(tuple2._1()) ? tuple2._2() : new IntMap.Bin<T>(prefix, mask, tuple2._1(), tuple2._2()));
        return intMap;
    }

    private IntMapUtils$() {
        MODULE$ = this;
        BitOperations$Int$class.$init$(this);
    }
}

