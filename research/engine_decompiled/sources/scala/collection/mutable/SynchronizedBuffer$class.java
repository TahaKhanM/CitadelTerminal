/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.GenTraversableOnce;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.SynchronizedBuffer;
import scala.collection.script.Message;

public abstract class SynchronizedBuffer$class {
    public static int length(SynchronizedBuffer $this) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            int n = $this.scala$collection$mutable$SynchronizedBuffer$$super$length();
            // ** MonitorExit[$this] (shouldn't be in output)
            return n;
        }
    }

    public static Iterator iterator(SynchronizedBuffer $this) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            Iterator iterator2 = $this.scala$collection$mutable$SynchronizedBuffer$$super$iterator();
            // ** MonitorExit[$this] (shouldn't be in output)
            return iterator2;
        }
    }

    public static Object apply(SynchronizedBuffer $this, int n) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            Object object = $this.scala$collection$mutable$SynchronizedBuffer$$super$apply(n);
            // ** MonitorExit[$this] (shouldn't be in output)
            return object;
        }
    }

    public static SynchronizedBuffer $plus$eq(SynchronizedBuffer $this, Object elem) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            SynchronizedBuffer synchronizedBuffer2 = $this.scala$collection$mutable$SynchronizedBuffer$$super$$plus$eq(elem);
            // ** MonitorExit[$this] (shouldn't be in output)
            return synchronizedBuffer2;
        }
    }

    public static Buffer $plus$plus(SynchronizedBuffer $this, GenTraversableOnce xs) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            Buffer buffer = $this.scala$collection$mutable$SynchronizedBuffer$$super$$plus$plus(xs);
            // ** MonitorExit[$this] (shouldn't be in output)
            return buffer;
        }
    }

    public static SynchronizedBuffer $plus$plus$eq(SynchronizedBuffer $this, TraversableOnce xs) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            SynchronizedBuffer synchronizedBuffer2 = $this.scala$collection$mutable$SynchronizedBuffer$$super$$plus$plus$eq(xs);
            // ** MonitorExit[$this] (shouldn't be in output)
            return synchronizedBuffer2;
        }
    }

    public static void append(SynchronizedBuffer $this, Seq elems) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            $this.scala$collection$mutable$SynchronizedBuffer$$super$$plus$plus$eq(elems);
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static void appendAll(SynchronizedBuffer $this, TraversableOnce xs) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            $this.scala$collection$mutable$SynchronizedBuffer$$super$appendAll(xs);
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static SynchronizedBuffer $plus$eq$colon(SynchronizedBuffer $this, Object elem) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            SynchronizedBuffer synchronizedBuffer2 = $this.scala$collection$mutable$SynchronizedBuffer$$super$$plus$eq$colon(elem);
            // ** MonitorExit[$this] (shouldn't be in output)
            return synchronizedBuffer2;
        }
    }

    public static SynchronizedBuffer $plus$plus$eq$colon(SynchronizedBuffer $this, TraversableOnce xs) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            SynchronizedBuffer synchronizedBuffer2 = $this.scala$collection$mutable$SynchronizedBuffer$$super$$plus$plus$eq$colon(xs);
            // ** MonitorExit[$this] (shouldn't be in output)
            return synchronizedBuffer2;
        }
    }

    public static void prepend(SynchronizedBuffer $this, Seq elems) {
        $this.prependAll(elems);
    }

    public static void prependAll(SynchronizedBuffer $this, TraversableOnce xs) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            $this.scala$collection$mutable$SynchronizedBuffer$$super$prependAll(xs);
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static void insert(SynchronizedBuffer $this, int n, Seq elems) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            $this.scala$collection$mutable$SynchronizedBuffer$$super$insertAll(n, elems);
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static void insertAll(SynchronizedBuffer $this, int n, Traversable xs) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            $this.scala$collection$mutable$SynchronizedBuffer$$super$insertAll(n, xs);
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static void update(SynchronizedBuffer $this, int n, Object newelem) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            $this.scala$collection$mutable$SynchronizedBuffer$$super$update(n, newelem);
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static Object remove(SynchronizedBuffer $this, int n) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            Object object = $this.scala$collection$mutable$SynchronizedBuffer$$super$remove(n);
            // ** MonitorExit[$this] (shouldn't be in output)
            return object;
        }
    }

    public static void clear(SynchronizedBuffer $this) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            $this.scala$collection$mutable$SynchronizedBuffer$$super$clear();
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static void $less$less(SynchronizedBuffer $this, Message cmd) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            $this.scala$collection$mutable$SynchronizedBuffer$$super$$less$less(cmd);
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static Buffer clone(SynchronizedBuffer $this) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            Buffer buffer = $this.scala$collection$mutable$SynchronizedBuffer$$super$clone();
            // ** MonitorExit[$this] (shouldn't be in output)
            return buffer;
        }
    }

    public static int hashCode(SynchronizedBuffer $this) {
        SynchronizedBuffer synchronizedBuffer = $this;
        synchronized (synchronizedBuffer) {
            int n = $this.scala$collection$mutable$SynchronizedBuffer$$super$hashCode();
            // ** MonitorExit[$this] (shouldn't be in output)
            return n;
        }
    }

    public static void $init$(SynchronizedBuffer $this) {
    }
}

