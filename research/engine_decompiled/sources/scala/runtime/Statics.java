/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

public final class Statics {
    public static int mix(int hash, int data) {
        int h = Statics.mixLast(hash, data);
        h = Integer.rotateLeft(h, 13);
        return h * 5 + -430675100;
    }

    public static int mixLast(int hash, int data) {
        int k = data;
        k *= -862048943;
        k = Integer.rotateLeft(k, 15);
        return hash ^ (k *= 461845907);
    }

    public static int finalizeHash(int hash, int length) {
        return Statics.avalanche(hash ^ length);
    }

    public static int avalanche(int h) {
        h ^= h >>> 16;
        h *= -2048144789;
        h ^= h >>> 13;
        h *= -1028477387;
        h ^= h >>> 16;
        return h;
    }

    public static int longHash(long lv) {
        if ((long)((int)lv) == lv) {
            return (int)lv;
        }
        return (int)(lv ^ lv >>> 32);
    }

    public static int doubleHash(double dv) {
        int iv = (int)dv;
        if ((double)iv == dv) {
            return iv;
        }
        float fv = (float)dv;
        if ((double)fv == dv) {
            return Float.floatToIntBits(fv);
        }
        long lv = (long)dv;
        if ((double)lv == dv) {
            return (int)lv;
        }
        lv = Double.doubleToLongBits(dv);
        return (int)(lv ^ lv >>> 32);
    }

    public static int floatHash(float fv) {
        int iv = (int)fv;
        if ((float)iv == fv) {
            return iv;
        }
        long lv = (long)fv;
        if ((float)lv == fv) {
            return (int)(lv ^ lv >>> 32);
        }
        return Float.floatToIntBits(fv);
    }

    public static int anyHash(Object x) {
        if (x == null) {
            return 0;
        }
        if (x instanceof Long) {
            return Statics.longHash((Long)x);
        }
        if (x instanceof Double) {
            return Statics.doubleHash((Double)x);
        }
        if (x instanceof Float) {
            return Statics.floatHash(((Float)x).floatValue());
        }
        return x.hashCode();
    }
}

