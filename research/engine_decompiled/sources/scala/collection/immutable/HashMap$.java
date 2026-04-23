/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function2;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.generic.BitOperations;
import scala.collection.generic.BitOperations$Int$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenMapFactory;
import scala.collection.generic.ImmutableMapFactory;
import scala.collection.immutable.HashMap;
import scala.collection.immutable.HashMap$;
import scala.collection.immutable.HashMap$EmptyHashMap$;
import scala.collection.immutable.IndexedSeq;
import scala.runtime.RichInt$;

public final class HashMap$
extends ImmutableMapFactory<HashMap>
implements BitOperations.Int,
Serializable {
    public static final HashMap$ MODULE$;
    private final HashMap.Merger<Object, Object> defaultMerger;

    static {
        new HashMap$();
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

    public <A1, B1> HashMap.Merger<A1, B1> scala$collection$immutable$HashMap$$liftMerger(Function2<Tuple2<A1, B1>, Tuple2<A1, B1>, Tuple2<A1, B1>> mergef) {
        return mergef == null ? this.defaultMerger : new HashMap.Merger<A1, B1>(mergef){
            private final HashMap.Merger<A1, B1> invert;
            public final Function2 mergef$1;

            public Tuple2<A1, B1> apply(Tuple2<A1, B1> kv1, Tuple2<A1, B1> kv2) {
                return (Tuple2)this.mergef$1.apply(kv1, kv2);
            }

            public HashMap.Merger<A1, B1> invert() {
                return this.invert;
            }
            {
                this.mergef$1 = mergef$1;
                this.invert = new HashMap.Merger<A1, B1>(this){
                    private final /* synthetic */ anon.2 $outer;

                    public Tuple2<A1, B1> apply(Tuple2<A1, B1> kv1, Tuple2<A1, B1> kv2) {
                        return (Tuple2)this.$outer.mergef$1.apply(kv2, kv1);
                    }

                    public HashMap.Merger<A1, B1> invert() {
                        return this.$outer;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                };
            }
        };
    }

    private <A1, B1> HashMap.Merger<A1, B1> liftMerger0(Function2<Tuple2<A1, B1>, Tuple2<A1, B1>, Tuple2<A1, B1>> mergef) {
        return new /* invalid duplicate definition of identical inner class */;
    }

    public <A, B> CanBuildFrom<HashMap<?, ?>, Tuple2<A, B>, HashMap<A, B>> canBuildFrom() {
        return new GenMapFactory.MapCanBuildFrom();
    }

    @Override
    public <A, B> HashMap<A, B> empty() {
        return HashMap$EmptyHashMap$.MODULE$;
    }

    public <A, B> HashMap.HashTrieMap<A, B> scala$collection$immutable$HashMap$$makeHashTrieMap(int hash0, HashMap<A, B> elem0, int hash1, HashMap<A, B> elem1, int level, int size2) {
        HashMap.HashTrieMap hashTrieMap;
        int index0 = hash0 >>> level & 0x1F;
        int index1 = hash1 >>> level & 0x1F;
        if (index0 != index1) {
            int bitmap = 1 << index0 | 1 << index1;
            HashMap[] elems = new HashMap[2];
            if (index0 < index1) {
                elems[0] = elem0;
                elems[1] = elem1;
            } else {
                elems[0] = elem1;
                elems[1] = elem0;
            }
            hashTrieMap = new HashMap.HashTrieMap(bitmap, elems, size2);
        } else {
            HashMap[] elems = new HashMap[1];
            int bitmap = 1 << index0;
            elems[0] = this.scala$collection$immutable$HashMap$$makeHashTrieMap(hash0, elem0, hash1, elem1, level + 5, size2);
            hashTrieMap = new HashMap.HashTrieMap(bitmap, elems, size2);
        }
        return hashTrieMap;
    }

    public int scala$collection$immutable$HashMap$$bufferSize(int size2) {
        return RichInt$.MODULE$.min$extension(Predef$.MODULE$.intWrapper(size2 + 6), 224);
    }

    public <A, B> HashMap<A, B> scala$collection$immutable$HashMap$$nullToEmpty(HashMap<A, B> m) {
        return m == null ? this.empty() : m;
    }

    /*
     * WARNING - void declaration
     */
    public int scala$collection$immutable$HashMap$$keepBits(int bitmap, int keep) {
        void var3_3;
        int result2 = 0;
        int current = bitmap;
        for (int kept = keep; kept != 0; kept >>>= 1) {
            int lsb = current ^ current & current - 1;
            if ((kept & 1) != 0) {
                result2 |= lsb;
            }
            current &= ~lsb;
        }
        return (int)var3_3;
    }

    private Object readResolve() {
        return MODULE$;
    }

    private HashMap$() {
        MODULE$ = this;
        BitOperations$Int$class.$init$(this);
        Serializable serializable = new Serializable(){
            public static final long serialVersionUID = 0L;

            public final Tuple2<Object, Object> apply(Tuple2<Object, Object> a, Tuple2<Object, Object> b) {
                return a;
            }
        };
        this.defaultMerger = new /* invalid duplicate definition of identical inner class */;
    }
}

