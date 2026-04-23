/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Set;
import scala.collection.mutable.Map;
import scala.collection.mutable.SynchronizedMap;

public abstract class SynchronizedMap$class {
    public static Option get(SynchronizedMap $this, Object key) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            Option option = $this.scala$collection$mutable$SynchronizedMap$$super$get(key);
            // ** MonitorExit[$this] (shouldn't be in output)
            return option;
        }
    }

    public static Iterator iterator(SynchronizedMap $this) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            Iterator iterator2 = $this.scala$collection$mutable$SynchronizedMap$$super$iterator();
            // ** MonitorExit[$this] (shouldn't be in output)
            return iterator2;
        }
    }

    public static SynchronizedMap $plus$eq(SynchronizedMap $this, Tuple2 kv) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            SynchronizedMap synchronizedMap2 = $this.scala$collection$mutable$SynchronizedMap$$super$$plus$eq(kv);
            // ** MonitorExit[$this] (shouldn't be in output)
            return synchronizedMap2;
        }
    }

    public static SynchronizedMap $minus$eq(SynchronizedMap $this, Object key) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            SynchronizedMap synchronizedMap2 = $this.scala$collection$mutable$SynchronizedMap$$super$$minus$eq(key);
            // ** MonitorExit[$this] (shouldn't be in output)
            return synchronizedMap2;
        }
    }

    public static int size(SynchronizedMap $this) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            int n = $this.scala$collection$mutable$SynchronizedMap$$super$size();
            // ** MonitorExit[$this] (shouldn't be in output)
            return n;
        }
    }

    public static Option put(SynchronizedMap $this, Object key, Object value) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            Option option = $this.scala$collection$mutable$SynchronizedMap$$super$put(key, value);
            // ** MonitorExit[$this] (shouldn't be in output)
            return option;
        }
    }

    public static void update(SynchronizedMap $this, Object key, Object value) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            $this.scala$collection$mutable$SynchronizedMap$$super$update(key, value);
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static Option remove(SynchronizedMap $this, Object key) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            Option option = $this.scala$collection$mutable$SynchronizedMap$$super$remove(key);
            // ** MonitorExit[$this] (shouldn't be in output)
            return option;
        }
    }

    public static void clear(SynchronizedMap $this) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            $this.scala$collection$mutable$SynchronizedMap$$super$clear();
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static Object getOrElseUpdate(SynchronizedMap $this, Object key, Function0 function0) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            Object object = $this.scala$collection$mutable$SynchronizedMap$$super$getOrElseUpdate(key, function0);
            // ** MonitorExit[$this] (shouldn't be in output)
            return object;
        }
    }

    public static SynchronizedMap transform(SynchronizedMap $this, Function2 f) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            SynchronizedMap synchronizedMap2 = $this.scala$collection$mutable$SynchronizedMap$$super$transform(f);
            // ** MonitorExit[$this] (shouldn't be in output)
            return synchronizedMap2;
        }
    }

    public static SynchronizedMap retain(SynchronizedMap $this, Function2 p) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            SynchronizedMap synchronizedMap2 = $this.scala$collection$mutable$SynchronizedMap$$super$retain(p);
            // ** MonitorExit[$this] (shouldn't be in output)
            return synchronizedMap2;
        }
    }

    public static Iterable values(SynchronizedMap $this) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            Iterable iterable = $this.scala$collection$mutable$SynchronizedMap$$super$values();
            // ** MonitorExit[$this] (shouldn't be in output)
            return iterable;
        }
    }

    public static Iterator valuesIterator(SynchronizedMap $this) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            Iterator iterator2 = $this.scala$collection$mutable$SynchronizedMap$$super$valuesIterator();
            // ** MonitorExit[$this] (shouldn't be in output)
            return iterator2;
        }
    }

    public static Map clone(SynchronizedMap $this) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            Map map2 = $this.scala$collection$mutable$SynchronizedMap$$super$clone();
            // ** MonitorExit[$this] (shouldn't be in output)
            return map2;
        }
    }

    public static void foreach(SynchronizedMap $this, Function1 f) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            $this.scala$collection$mutable$SynchronizedMap$$super$foreach(f);
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static Object apply(SynchronizedMap $this, Object key) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            Object object = $this.scala$collection$mutable$SynchronizedMap$$super$apply(key);
            // ** MonitorExit[$this] (shouldn't be in output)
            return object;
        }
    }

    public static Set keySet(SynchronizedMap $this) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            Set set = $this.scala$collection$mutable$SynchronizedMap$$super$keySet();
            // ** MonitorExit[$this] (shouldn't be in output)
            return set;
        }
    }

    public static Iterable keys(SynchronizedMap $this) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            Iterable iterable = $this.scala$collection$mutable$SynchronizedMap$$super$keys();
            // ** MonitorExit[$this] (shouldn't be in output)
            return iterable;
        }
    }

    public static Iterator keysIterator(SynchronizedMap $this) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            Iterator iterator2 = $this.scala$collection$mutable$SynchronizedMap$$super$keysIterator();
            // ** MonitorExit[$this] (shouldn't be in output)
            return iterator2;
        }
    }

    public static boolean isEmpty(SynchronizedMap $this) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            boolean bl = $this.scala$collection$mutable$SynchronizedMap$$super$isEmpty();
            // ** MonitorExit[$this] (shouldn't be in output)
            return bl;
        }
    }

    public static boolean contains(SynchronizedMap $this, Object key) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            boolean bl = $this.scala$collection$mutable$SynchronizedMap$$super$contains(key);
            // ** MonitorExit[$this] (shouldn't be in output)
            return bl;
        }
    }

    public static boolean isDefinedAt(SynchronizedMap $this, Object key) {
        SynchronizedMap synchronizedMap = $this;
        synchronized (synchronizedMap) {
            boolean bl = $this.scala$collection$mutable$SynchronizedMap$$super$isDefinedAt(key);
            // ** MonitorExit[$this] (shouldn't be in output)
            return bl;
        }
    }

    public static void $init$(SynchronizedMap $this) {
    }
}

