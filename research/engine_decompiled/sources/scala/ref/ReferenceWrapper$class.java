/*
 * Decompiled with CFR 0.152.
 */
package scala.ref;

import java.lang.ref.Reference;
import java.util.NoSuchElementException;
import scala.Option;
import scala.Option$;
import scala.ref.ReferenceWrapper;

public abstract class ReferenceWrapper$class {
    public static Option get(ReferenceWrapper $this) {
        return Option$.MODULE$.apply($this.underlying().get());
    }

    /*
     * WARNING - void declaration
     */
    public static Object apply(ReferenceWrapper $this) {
        void var1_1;
        Object ret = $this.underlying().get();
        if (ret == null) {
            throw new NoSuchElementException();
        }
        return var1_1;
    }

    public static void clear(ReferenceWrapper $this) {
        $this.underlying().clear();
    }

    public static boolean enqueue(ReferenceWrapper $this) {
        return $this.underlying().enqueue();
    }

    public static boolean isEnqueued(ReferenceWrapper $this) {
        return $this.underlying().isEnqueued();
    }

    public static Reference self(ReferenceWrapper $this) {
        return $this.underlying();
    }

    public static void $init$(ReferenceWrapper $this) {
    }
}

