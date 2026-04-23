/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function1;
import scala.collection.GenSet;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.collection.mutable.Set;
import scala.collection.mutable.SynchronizedSet;
import scala.collection.script.Message;

public abstract class SynchronizedSet$class {
    public static int size(SynchronizedSet $this) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            int n = $this.scala$collection$mutable$SynchronizedSet$$super$size();
            // ** MonitorExit[$this] (shouldn't be in output)
            return n;
        }
    }

    public static boolean isEmpty(SynchronizedSet $this) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            boolean bl = $this.scala$collection$mutable$SynchronizedSet$$super$isEmpty();
            // ** MonitorExit[$this] (shouldn't be in output)
            return bl;
        }
    }

    public static boolean contains(SynchronizedSet $this, Object elem) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            boolean bl = $this.scala$collection$mutable$SynchronizedSet$$super$contains(elem);
            // ** MonitorExit[$this] (shouldn't be in output)
            return bl;
        }
    }

    public static SynchronizedSet $plus$eq(SynchronizedSet $this, Object elem) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            SynchronizedSet synchronizedSet2 = $this.scala$collection$mutable$SynchronizedSet$$super$$plus$eq(elem);
            // ** MonitorExit[$this] (shouldn't be in output)
            return synchronizedSet2;
        }
    }

    public static SynchronizedSet $plus$plus$eq(SynchronizedSet $this, TraversableOnce xs) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            SynchronizedSet synchronizedSet2 = $this.scala$collection$mutable$SynchronizedSet$$super$$plus$plus$eq(xs);
            // ** MonitorExit[$this] (shouldn't be in output)
            return synchronizedSet2;
        }
    }

    public static SynchronizedSet $minus$eq(SynchronizedSet $this, Object elem) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            SynchronizedSet synchronizedSet2 = $this.scala$collection$mutable$SynchronizedSet$$super$$minus$eq(elem);
            // ** MonitorExit[$this] (shouldn't be in output)
            return synchronizedSet2;
        }
    }

    public static SynchronizedSet $minus$minus$eq(SynchronizedSet $this, TraversableOnce xs) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            SynchronizedSet synchronizedSet2 = $this.scala$collection$mutable$SynchronizedSet$$super$$minus$minus$eq(xs);
            // ** MonitorExit[$this] (shouldn't be in output)
            return synchronizedSet2;
        }
    }

    public static void update(SynchronizedSet $this, Object elem, boolean included) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            $this.scala$collection$mutable$SynchronizedSet$$super$update(elem, included);
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static boolean add(SynchronizedSet $this, Object elem) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            boolean bl = $this.scala$collection$mutable$SynchronizedSet$$super$add(elem);
            // ** MonitorExit[$this] (shouldn't be in output)
            return bl;
        }
    }

    public static boolean remove(SynchronizedSet $this, Object elem) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            boolean bl = $this.scala$collection$mutable$SynchronizedSet$$super$remove(elem);
            // ** MonitorExit[$this] (shouldn't be in output)
            return bl;
        }
    }

    public static Set intersect(SynchronizedSet $this, GenSet that) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            Set set = $this.scala$collection$mutable$SynchronizedSet$$super$intersect(that);
            // ** MonitorExit[$this] (shouldn't be in output)
            return set;
        }
    }

    public static void clear(SynchronizedSet $this) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            $this.scala$collection$mutable$SynchronizedSet$$super$clear();
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static boolean subsetOf(SynchronizedSet $this, GenSet that) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            boolean bl = $this.scala$collection$mutable$SynchronizedSet$$super$subsetOf(that);
            // ** MonitorExit[$this] (shouldn't be in output)
            return bl;
        }
    }

    public static void foreach(SynchronizedSet $this, Function1 f) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            $this.scala$collection$mutable$SynchronizedSet$$super$foreach(f);
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static void retain(SynchronizedSet $this, Function1 p) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            $this.scala$collection$mutable$SynchronizedSet$$super$retain(p);
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static List toList(SynchronizedSet $this) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            List list2 = $this.scala$collection$mutable$SynchronizedSet$$super$toList();
            // ** MonitorExit[$this] (shouldn't be in output)
            return list2;
        }
    }

    public static String toString(SynchronizedSet $this) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            String string2 = $this.scala$collection$mutable$SynchronizedSet$$super$toString();
            // ** MonitorExit[$this] (shouldn't be in output)
            return string2;
        }
    }

    public static void $less$less(SynchronizedSet $this, Message cmd) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            $this.scala$collection$mutable$SynchronizedSet$$super$$less$less(cmd);
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static Set clone(SynchronizedSet $this) {
        SynchronizedSet synchronizedSet = $this;
        synchronized (synchronizedSet) {
            Set set = $this.scala$collection$mutable$SynchronizedSet$$super$clone();
            // ** MonitorExit[$this] (shouldn't be in output)
            return set;
        }
    }

    public static void $init$(SynchronizedSet $this) {
    }
}

