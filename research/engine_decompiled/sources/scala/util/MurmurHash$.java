/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import scala.Function1;
import scala.Serializable;
import scala.collection.AbstractIterator;
import scala.collection.Iterator$;
import scala.collection.TraversableOnce;
import scala.reflect.ClassTag$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ScalaRunTime$;

public final class MurmurHash$ {
    public static final MurmurHash$ MODULE$;
    private final int visibleMagic;
    private final int hiddenMagicA;
    private final int hiddenMagicB;
    private final int visibleMixer;
    private final int hiddenMixerA;
    private final int hiddenMixerB;
    private final int finalMixer1;
    private final int finalMixer2;
    private final int seedString;
    private final int seedArray;
    private final int[] storedMagicA;
    private final int[] storedMagicB;

    static {
        new MurmurHash$();
    }

    private final int visibleMagic() {
        return -1759636613;
    }

    private final int hiddenMagicA() {
        return -1789642873;
    }

    private final int hiddenMagicB() {
        return 718793509;
    }

    private final int visibleMixer() {
        return 1390208809;
    }

    private final int hiddenMixerA() {
        return 2071795100;
    }

    private final int hiddenMixerB() {
        return 1808688022;
    }

    private final int finalMixer1() {
        return -2048144789;
    }

    private final int finalMixer2() {
        return -1028477387;
    }

    private final int seedString() {
        return -137723950;
    }

    private final int seedArray() {
        return 1007110753;
    }

    public int[] storedMagicA() {
        return this.storedMagicA;
    }

    public int[] storedMagicB() {
        return this.storedMagicB;
    }

    public int startHash(int seed) {
        return seed ^ 0x971E137B;
    }

    public int startMagicA() {
        return -1789642873;
    }

    public int startMagicB() {
        return 718793509;
    }

    public int extendHash(int hash, int value, int magicA, int magicB) {
        return (hash ^ Integer.rotateLeft(value * magicA, 11) * magicB) * 3 + 1390208809;
    }

    public int nextMagicA(int magicA) {
        return magicA * 5 + 2071795100;
    }

    public int nextMagicB(int magicB) {
        return magicB * 5 + 1808688022;
    }

    public int finalizeHash(int hash) {
        int i = hash ^ hash >>> 16;
        i *= -2048144789;
        i = (i ^ i >>> 13) * -1028477387;
        return i ^ i >>> 16;
    }

    public <T> int arrayHash(Object a) {
        int h = this.startHash(ScalaRunTime$.MODULE$.array_length(a) * 1007110753);
        int c = -1789642873;
        int k = 718793509;
        for (int j = 0; j < ScalaRunTime$.MODULE$.array_length(a); ++j) {
            h = this.extendHash(h, ScalaRunTime$.MODULE$.hash(ScalaRunTime$.MODULE$.array_apply(a, j)), c, k);
            c = this.nextMagicA(c);
            k = this.nextMagicB(k);
        }
        return this.finalizeHash(h);
    }

    public int stringHash(String s2) {
        int h = this.startHash(s2.length() * -137723950);
        int c = -1789642873;
        int k = 718793509;
        int j = 0;
        while (j + 1 < s2.length()) {
            int i = (s2.charAt(j) << 16) + s2.charAt(j + 1);
            h = this.extendHash(h, i, c, k);
            c = this.nextMagicA(c);
            k = this.nextMagicB(k);
            j += 2;
        }
        if (j < s2.length()) {
            h = this.extendHash(h, s2.charAt(j), c, k);
        }
        return this.finalizeHash(h);
    }

    public <T> int symmetricHash(TraversableOnce<T> xs, int seed) {
        IntRef a = IntRef.create(0);
        IntRef b = IntRef.create(0);
        IntRef n = IntRef.create(0);
        IntRef c = IntRef.create(1);
        xs.seq().foreach(new Serializable(a, b, n, c){
            public static final long serialVersionUID = 0L;
            private final IntRef a$1;
            private final IntRef b$1;
            private final IntRef n$1;
            private final IntRef c$1;

            public final void apply(T i) {
                int h = ScalaRunTime$.MODULE$.hash(i);
                this.a$1.elem += h;
                this.b$1.elem ^= h;
                if (h != 0) {
                    this.c$1.elem *= h;
                }
                ++this.n$1.elem;
            }
            {
                this.a$1 = a$1;
                this.b$1 = b$1;
                this.n$1 = n$1;
                this.c$1 = c$1;
            }
        });
        int h = this.startHash(seed * n.elem);
        h = this.extendHash(h, a.elem, this.storedMagicA()[0], this.storedMagicB()[0]);
        h = this.extendHash(h, b.elem, this.storedMagicA()[1], this.storedMagicB()[1]);
        h = this.extendHash(h, c.elem, this.storedMagicA()[2], this.storedMagicB()[2]);
        return this.finalizeHash(h);
    }

    public int arrayHash$mZc$sp(boolean[] a) {
        int h = this.startHash(a.length * 1007110753);
        int c = -1789642873;
        int k = 718793509;
        for (int j = 0; j < a.length; ++j) {
            h = this.extendHash(h, a[j] ? 1231 : 1237, c, k);
            c = this.nextMagicA(c);
            k = this.nextMagicB(k);
        }
        return this.finalizeHash(h);
    }

    public int arrayHash$mBc$sp(byte[] a) {
        int h = this.startHash(a.length * 1007110753);
        int c = -1789642873;
        int k = 718793509;
        for (int j = 0; j < a.length; ++j) {
            h = this.extendHash(h, a[j], c, k);
            c = this.nextMagicA(c);
            k = this.nextMagicB(k);
        }
        return this.finalizeHash(h);
    }

    public int arrayHash$mCc$sp(char[] a) {
        int h = this.startHash(a.length * 1007110753);
        int c = -1789642873;
        int k = 718793509;
        for (int j = 0; j < a.length; ++j) {
            h = this.extendHash(h, a[j], c, k);
            c = this.nextMagicA(c);
            k = this.nextMagicB(k);
        }
        return this.finalizeHash(h);
    }

    public int arrayHash$mDc$sp(double[] a) {
        int h = this.startHash(a.length * 1007110753);
        int c = -1789642873;
        int k = 718793509;
        for (int j = 0; j < a.length; ++j) {
            h = this.extendHash(h, ScalaRunTime$.MODULE$.hash(a[j]), c, k);
            c = this.nextMagicA(c);
            k = this.nextMagicB(k);
        }
        return this.finalizeHash(h);
    }

    public int arrayHash$mFc$sp(float[] a) {
        int h = this.startHash(a.length * 1007110753);
        int c = -1789642873;
        int k = 718793509;
        for (int j = 0; j < a.length; ++j) {
            h = this.extendHash(h, ScalaRunTime$.MODULE$.hash(a[j]), c, k);
            c = this.nextMagicA(c);
            k = this.nextMagicB(k);
        }
        return this.finalizeHash(h);
    }

    public int arrayHash$mIc$sp(int[] a) {
        int h = this.startHash(a.length * 1007110753);
        int c = -1789642873;
        int k = 718793509;
        for (int j = 0; j < a.length; ++j) {
            h = this.extendHash(h, a[j], c, k);
            c = this.nextMagicA(c);
            k = this.nextMagicB(k);
        }
        return this.finalizeHash(h);
    }

    public int arrayHash$mJc$sp(long[] a) {
        int h = this.startHash(a.length * 1007110753);
        int c = -1789642873;
        int k = 718793509;
        for (int j = 0; j < a.length; ++j) {
            h = this.extendHash(h, ScalaRunTime$.MODULE$.hash(a[j]), c, k);
            c = this.nextMagicA(c);
            k = this.nextMagicB(k);
        }
        return this.finalizeHash(h);
    }

    public int arrayHash$mSc$sp(short[] a) {
        int h = this.startHash(a.length * 1007110753);
        int c = -1789642873;
        int k = 718793509;
        for (int j = 0; j < a.length; ++j) {
            h = this.extendHash(h, a[j], c, k);
            c = this.nextMagicA(c);
            k = this.nextMagicB(k);
        }
        return this.finalizeHash(h);
    }

    public int arrayHash$mVc$sp(BoxedUnit[] a) {
        int h = this.startHash(a.length * 1007110753);
        int c = -1789642873;
        int k = 718793509;
        for (int j = 0; j < a.length; ++j) {
            h = this.extendHash(h, 0, c, k);
            c = this.nextMagicA(c);
            k = this.nextMagicB(k);
        }
        return this.finalizeHash(h);
    }

    private MurmurHash$() {
        MODULE$ = this;
        Serializable serializable = new Serializable(){
            public static final long serialVersionUID = 0L;

            public final int apply(int magicA) {
                return MurmurHash$.MODULE$.nextMagicA(magicA);
            }

            public int apply$mcII$sp(int magicA) {
                return MurmurHash$.MODULE$.nextMagicA(magicA);
            }
        };
        Integer n = BoxesRunTime.boxToInteger(-1789642873);
        Iterator$ iterator$ = Iterator$.MODULE$;
        this.storedMagicA = (int[])new AbstractIterator<T>(n, (Function1)((Object)serializable)){
            private boolean first;
            private T acc;
            private final Function1 f$2;

            public boolean hasNext() {
                return true;
            }

            public T next() {
                if (this.first) {
                    this.first = false;
                } else {
                    this.acc = this.f$2.apply(this.acc);
                }
                return this.acc;
            }
            {
                this.f$2 = f$2;
                this.first = true;
                this.acc = start$2;
            }
        }.take(23).toArray(ClassTag$.MODULE$.Int());
        Serializable serializable2 = new Serializable(){
            public static final long serialVersionUID = 0L;

            public final int apply(int magicB) {
                return MurmurHash$.MODULE$.nextMagicB(magicB);
            }

            public int apply$mcII$sp(int magicB) {
                return MurmurHash$.MODULE$.nextMagicB(magicB);
            }
        };
        Integer n2 = BoxesRunTime.boxToInteger(718793509);
        Iterator$ iterator$2 = Iterator$.MODULE$;
        this.storedMagicB = (int[])new /* invalid duplicate definition of identical inner class */.take(23).toArray(ClassTag$.MODULE$.Int());
    }
}

