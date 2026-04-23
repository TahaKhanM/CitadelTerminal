/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.None$;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.generic.Sorted;

public abstract class Sorted$class {
    public static int compare(Sorted $this, Object k0, Object k1) {
        return $this.ordering().compare(k0, k1);
    }

    public static Sorted from(Sorted $this, Object from2) {
        return $this.rangeImpl(new Some<Object>(from2), None$.MODULE$);
    }

    public static Sorted until(Sorted $this, Object until2) {
        return $this.rangeImpl(None$.MODULE$, new Some<Object>(until2));
    }

    public static Sorted range(Sorted $this, Object from2, Object until2) {
        return $this.rangeImpl(new Some<Object>(from2), new Some<Object>(until2));
    }

    public static Sorted to(Sorted $this, Object to2) {
        Iterator i = $this.keySet().from(to2).iterator();
        if (i.isEmpty()) {
            return $this.repr();
        }
        Object next2 = i.next();
        return $this.compare(next2, to2) == 0 ? (i.isEmpty() ? $this.repr() : $this.until(i.next())) : $this.until(next2);
    }

    public static boolean hasAll(Sorted $this, Iterator j) {
        Iterator i = $this.keySet().iterator();
        if (i.isEmpty()) {
            return j.isEmpty();
        }
        Object in = i.next();
        block0: while (j.hasNext()) {
            Object jn = j.next();
            while (true) {
                boolean bl;
                int n;
                if ((n = $this.compare(jn, in)) == 0) {
                    bl = false;
                } else {
                    if (n < 0) {
                        return false;
                    }
                    if (!i.hasNext()) break;
                    bl = true;
                }
                if (!bl) continue block0;
                in = i.next();
            }
            return false;
        }
        return true;
    }

    public static void $init$(Sorted $this) {
    }
}

