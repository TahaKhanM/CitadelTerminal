/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import java.util.concurrent.atomic.AtomicInteger;
import scala.collection.generic.AtomicIndexFlag;

public abstract class AtomicIndexFlag$class {
    public static int indexFlag(AtomicIndexFlag $this) {
        return $this.scala$collection$generic$AtomicIndexFlag$$intflag().get();
    }

    public static void setIndexFlag(AtomicIndexFlag $this, int f) {
        $this.scala$collection$generic$AtomicIndexFlag$$intflag().set(f);
    }

    public static void setIndexFlagIfGreater(AtomicIndexFlag $this, int f) {
        boolean loop2 = true;
        do {
            int old;
            if (f <= (old = $this.scala$collection$generic$AtomicIndexFlag$$intflag().get())) {
                loop2 = false;
                continue;
            }
            if (!$this.scala$collection$generic$AtomicIndexFlag$$intflag().compareAndSet(old, f)) continue;
            loop2 = false;
        } while (loop2);
    }

    public static void setIndexFlagIfLesser(AtomicIndexFlag $this, int f) {
        boolean loop2 = true;
        do {
            int old;
            if (f >= (old = $this.scala$collection$generic$AtomicIndexFlag$$intflag().get())) {
                loop2 = false;
                continue;
            }
            if (!$this.scala$collection$generic$AtomicIndexFlag$$intflag().compareAndSet(old, f)) continue;
            loop2 = false;
        } while (loop2);
    }

    public static void $init$(AtomicIndexFlag $this) {
        $this.scala$collection$generic$AtomicIndexFlag$_setter_$scala$collection$generic$AtomicIndexFlag$$intflag_$eq(new AtomicInteger(-1));
    }
}

