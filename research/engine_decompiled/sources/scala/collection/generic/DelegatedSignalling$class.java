/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.collection.generic.DelegatedSignalling;

public abstract class DelegatedSignalling$class {
    public static boolean isAborted(DelegatedSignalling $this) {
        return $this.signalDelegate().isAborted();
    }

    public static void abort(DelegatedSignalling $this) {
        $this.signalDelegate().abort();
    }

    public static int indexFlag(DelegatedSignalling $this) {
        return $this.signalDelegate().indexFlag();
    }

    public static void setIndexFlag(DelegatedSignalling $this, int f) {
        $this.signalDelegate().setIndexFlag(f);
    }

    public static void setIndexFlagIfGreater(DelegatedSignalling $this, int f) {
        $this.signalDelegate().setIndexFlagIfGreater(f);
    }

    public static void setIndexFlagIfLesser(DelegatedSignalling $this, int f) {
        $this.signalDelegate().setIndexFlagIfLesser(f);
    }

    public static int tag(DelegatedSignalling $this) {
        return $this.signalDelegate().tag();
    }

    public static void $init$(DelegatedSignalling $this) {
    }
}

