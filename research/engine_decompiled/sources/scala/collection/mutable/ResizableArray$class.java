/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Array$;
import scala.Function1;
import scala.Predef$;
import scala.collection.generic.GenericCompanion;
import scala.collection.mutable.ResizableArray;
import scala.collection.mutable.ResizableArray$;
import scala.compat.Platform$;
import scala.math.package$;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

public abstract class ResizableArray$class {
    public static GenericCompanion companion(ResizableArray $this) {
        return ResizableArray$.MODULE$;
    }

    public static int initialSize(ResizableArray $this) {
        return 16;
    }

    public static int length(ResizableArray $this) {
        return $this.size0();
    }

    public static Object apply(ResizableArray $this, int idx) {
        if (idx >= $this.size0()) {
            throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(idx)).toString());
        }
        return $this.array()[idx];
    }

    public static void update(ResizableArray $this, int idx, Object elem) {
        if (idx >= $this.size0()) {
            throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(idx)).toString());
        }
        $this.array()[idx] = elem;
    }

    public static void foreach(ResizableArray $this, Function1 f) {
        int top = $this.size();
        for (int i = 0; i < top; ++i) {
            f.apply($this.array()[i]);
        }
    }

    public static void copyToArray(ResizableArray $this, Object xs, int start, int len) {
        Predef$ predef$ = Predef$.MODULE$;
        int n = RichInt$.MODULE$.min$extension(len, ScalaRunTime$.MODULE$.array_length(xs) - start);
        Predef$ predef$2 = Predef$.MODULE$;
        int len1 = RichInt$.MODULE$.min$extension(n, $this.length());
        Array$.MODULE$.copy($this.array(), 0, xs, start, len1);
    }

    public static void reduceToSize(ResizableArray $this, int sz) {
        Predef$.MODULE$.require(sz <= $this.size0());
        while ($this.size0() > sz) {
            $this.size0_$eq($this.size0() - 1);
            $this.array()[$this.size0()] = null;
        }
    }

    public static void ensureSize(ResizableArray $this, int n) {
        long arrayLength = $this.array().length;
        if ((long)n > arrayLength) {
            long newSize;
            for (newSize = arrayLength * 2L; (long)n > newSize; newSize *= 2L) {
            }
            if (newSize > Integer.MAX_VALUE) {
                newSize = Integer.MAX_VALUE;
            }
            Object[] newArray = new Object[(int)newSize];
            int n2 = $this.size0();
            Object[] objectArray = $this.array();
            Platform$ platform$ = Platform$.MODULE$;
            System.arraycopy(objectArray, 0, newArray, 0, n2);
            $this.array_$eq(newArray);
        }
    }

    public static void swap(ResizableArray $this, int a, int b) {
        Object h = $this.array()[a];
        $this.array()[a] = $this.array()[b];
        $this.array()[b] = h;
    }

    public static void copy(ResizableArray $this, int m, int n, int len) {
        Object[] objectArray = $this.array();
        Object[] objectArray2 = $this.array();
        Platform$ platform$ = Platform$.MODULE$;
        System.arraycopy(objectArray2, m, objectArray, n, len);
    }

    public static void $init$(ResizableArray $this) {
        $this.array_$eq(new Object[package$.MODULE$.max($this.initialSize(), 1)]);
        $this.size0_$eq(0);
    }
}

