/*
 * Decompiled with CFR 0.152.
 */
package scala.util.hashing;

import scala.Product;
import scala.collection.Map;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.runtime.BoxedUnit;
import scala.util.hashing.Hashing;
import scala.util.hashing.MurmurHash3;
import scala.util.hashing.MurmurHash3$ArrayHashing$mcB$sp;
import scala.util.hashing.MurmurHash3$ArrayHashing$mcC$sp;
import scala.util.hashing.MurmurHash3$ArrayHashing$mcD$sp;
import scala.util.hashing.MurmurHash3$ArrayHashing$mcF$sp;
import scala.util.hashing.MurmurHash3$ArrayHashing$mcI$sp;
import scala.util.hashing.MurmurHash3$ArrayHashing$mcJ$sp;
import scala.util.hashing.MurmurHash3$ArrayHashing$mcS$sp;
import scala.util.hashing.MurmurHash3$ArrayHashing$mcV$sp;
import scala.util.hashing.MurmurHash3$ArrayHashing$mcZ$sp;

public final class MurmurHash3$
extends MurmurHash3 {
    public static final MurmurHash3$ MODULE$;
    private final int arraySeed;
    private final int stringSeed;
    private final int productSeed;
    private final int symmetricSeed;
    private final int traversableSeed;
    private final int seqSeed;
    private final int mapSeed;
    private final int setSeed;

    static {
        new MurmurHash3$();
    }

    @Override
    public final int arraySeed() {
        return 1007110753;
    }

    @Override
    public final int stringSeed() {
        return -137723950;
    }

    @Override
    public final int productSeed() {
        return -889275714;
    }

    @Override
    public final int symmetricSeed() {
        return -1248659538;
    }

    @Override
    public final int traversableSeed() {
        return -415593707;
    }

    @Override
    public final int seqSeed() {
        return this.seqSeed;
    }

    @Override
    public final int mapSeed() {
        return this.mapSeed;
    }

    @Override
    public final int setSeed() {
        return this.setSeed;
    }

    public <T> int arrayHash(Object a) {
        return this.arrayHash(a, 1007110753);
    }

    public int bytesHash(byte[] data) {
        return this.bytesHash(data, 1007110753);
    }

    public int orderedHash(TraversableOnce<Object> xs) {
        return this.orderedHash(xs, -1248659538);
    }

    public int productHash(Product x) {
        return this.productHash(x, -889275714);
    }

    public int stringHash(String x) {
        return this.stringHash(x, -137723950);
    }

    public int unorderedHash(TraversableOnce<Object> xs) {
        return this.unorderedHash(xs, -415593707);
    }

    @Override
    public int seqHash(Seq<?> xs) {
        int n;
        if (xs instanceof List) {
            List list2 = (List)xs;
            n = this.listHash(list2, this.seqSeed());
        } else {
            n = this.orderedHash(xs, this.seqSeed());
        }
        return n;
    }

    @Override
    public int mapHash(Map<?, ?> xs) {
        return this.unorderedHash(xs, this.mapSeed());
    }

    @Override
    public int setHash(Set<?> xs) {
        return this.unorderedHash(xs, this.setSeed());
    }

    @Override
    public <T> MurmurHash3.ArrayHashing<T> arrayHashing() {
        return new MurmurHash3.ArrayHashing();
    }

    @Override
    public Object bytesHashing() {
        return new Hashing<byte[]>(){

            public int hash(byte[] data) {
                return MurmurHash3$.MODULE$.bytesHash(data);
            }
        };
    }

    @Override
    public Object orderedHashing() {
        return new Hashing<TraversableOnce<Object>>(){

            public int hash(TraversableOnce<Object> xs) {
                return MurmurHash3$.MODULE$.orderedHash(xs);
            }
        };
    }

    @Override
    public Object productHashing() {
        return new Hashing<Product>(){

            public int hash(Product x) {
                return MurmurHash3$.MODULE$.productHash(x);
            }
        };
    }

    @Override
    public Object stringHashing() {
        return new Hashing<String>(){

            public int hash(String x) {
                return MurmurHash3$.MODULE$.stringHash(x);
            }
        };
    }

    @Override
    public Object unorderedHashing() {
        return new Hashing<TraversableOnce<Object>>(){

            public int hash(TraversableOnce<Object> xs) {
                return MurmurHash3$.MODULE$.unorderedHash(xs);
            }
        };
    }

    public int arrayHash$mZc$sp(boolean[] a) {
        return this.arrayHash$mZc$sp(a, 1007110753);
    }

    public int arrayHash$mBc$sp(byte[] a) {
        return this.arrayHash$mBc$sp(a, 1007110753);
    }

    public int arrayHash$mCc$sp(char[] a) {
        return this.arrayHash$mCc$sp(a, 1007110753);
    }

    public int arrayHash$mDc$sp(double[] a) {
        return this.arrayHash$mDc$sp(a, 1007110753);
    }

    public int arrayHash$mFc$sp(float[] a) {
        return this.arrayHash$mFc$sp(a, 1007110753);
    }

    public int arrayHash$mIc$sp(int[] a) {
        return this.arrayHash$mIc$sp(a, 1007110753);
    }

    public int arrayHash$mJc$sp(long[] a) {
        return this.arrayHash$mJc$sp(a, 1007110753);
    }

    public int arrayHash$mSc$sp(short[] a) {
        return this.arrayHash$mSc$sp(a, 1007110753);
    }

    public int arrayHash$mVc$sp(BoxedUnit[] a) {
        return this.arrayHash$mVc$sp(a, 1007110753);
    }

    public MurmurHash3.ArrayHashing<Object> arrayHashing$mZc$sp() {
        return new MurmurHash3$ArrayHashing$mcZ$sp();
    }

    public MurmurHash3.ArrayHashing<Object> arrayHashing$mBc$sp() {
        return new MurmurHash3$ArrayHashing$mcB$sp();
    }

    public MurmurHash3.ArrayHashing<Object> arrayHashing$mCc$sp() {
        return new MurmurHash3$ArrayHashing$mcC$sp();
    }

    public MurmurHash3.ArrayHashing<Object> arrayHashing$mDc$sp() {
        return new MurmurHash3$ArrayHashing$mcD$sp();
    }

    public MurmurHash3.ArrayHashing<Object> arrayHashing$mFc$sp() {
        return new MurmurHash3$ArrayHashing$mcF$sp();
    }

    public MurmurHash3.ArrayHashing<Object> arrayHashing$mIc$sp() {
        return new MurmurHash3$ArrayHashing$mcI$sp();
    }

    public MurmurHash3.ArrayHashing<Object> arrayHashing$mJc$sp() {
        return new MurmurHash3$ArrayHashing$mcJ$sp();
    }

    public MurmurHash3.ArrayHashing<Object> arrayHashing$mSc$sp() {
        return new MurmurHash3$ArrayHashing$mcS$sp();
    }

    public MurmurHash3.ArrayHashing<BoxedUnit> arrayHashing$mVc$sp() {
        return new MurmurHash3$ArrayHashing$mcV$sp();
    }

    private MurmurHash3$() {
        MODULE$ = this;
        this.seqSeed = "Seq".hashCode();
        this.mapSeed = "Map".hashCode();
        this.setSeed = "Set".hashCode();
    }
}

