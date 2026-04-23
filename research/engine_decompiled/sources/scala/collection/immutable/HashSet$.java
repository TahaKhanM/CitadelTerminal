/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Predef$;
import scala.Serializable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.ImmutableSetFactory;
import scala.collection.immutable.HashSet;
import scala.collection.immutable.HashSet$EmptyHashSet$;
import scala.runtime.RichInt$;

public final class HashSet$
extends ImmutableSetFactory<HashSet>
implements Serializable {
    public static final HashSet$ MODULE$;

    static {
        new HashSet$();
    }

    public <A> CanBuildFrom<HashSet<?>, A, HashSet<A>> canBuildFrom() {
        return this.setCanBuildFrom();
    }

    @Override
    public HashSet<Object> emptyInstance() {
        return HashSet$EmptyHashSet$.MODULE$;
    }

    public <A> HashSet.HashTrieSet<A> scala$collection$immutable$HashSet$$makeHashTrieSet(int hash0, HashSet<A> elem0, int hash1, HashSet<A> elem1, int level) {
        HashSet.HashTrieSet hashTrieSet;
        int index0 = hash0 >>> level & 0x1F;
        int index1 = hash1 >>> level & 0x1F;
        if (index0 != index1) {
            int bitmap = 1 << index0 | 1 << index1;
            HashSet[] elems = new HashSet[2];
            if (index0 < index1) {
                elems[0] = elem0;
                elems[1] = elem1;
            } else {
                elems[0] = elem1;
                elems[1] = elem0;
            }
            hashTrieSet = new HashSet.HashTrieSet(bitmap, elems, elem0.size() + elem1.size());
        } else {
            HashSet.HashTrieSet<A> child;
            HashSet[] elems = new HashSet[1];
            int bitmap = 1 << index0;
            elems[0] = child = this.scala$collection$immutable$HashSet$$makeHashTrieSet(hash0, elem0, hash1, elem1, level + 5);
            hashTrieSet = new HashSet.HashTrieSet(bitmap, elems, child.size());
        }
        return hashTrieSet;
    }

    public int scala$collection$immutable$HashSet$$bufferSize(int size2) {
        return RichInt$.MODULE$.min$extension(Predef$.MODULE$.intWrapper(size2 + 6), 224);
    }

    public <A> HashSet<A> scala$collection$immutable$HashSet$$nullToEmpty(HashSet<A> s2) {
        return s2 == null ? (HashSet)this.empty() : s2;
    }

    /*
     * WARNING - void declaration
     */
    public int scala$collection$immutable$HashSet$$keepBits(int bitmap, int keep) {
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

    public boolean scala$collection$immutable$HashSet$$unsignedCompare(int i, int j) {
        return i < j ^ i < 0 ^ j < 0;
    }

    private Object readResolve() {
        return MODULE$;
    }

    private HashSet$() {
        MODULE$ = this;
    }
}

