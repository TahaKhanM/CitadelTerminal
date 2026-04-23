/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.GenSet;
import scala.collection.GenSetLike;
import scala.collection.Set;
import scala.collection.SetLike;
import scala.collection.SetProxyLike;

public abstract class SetProxyLike$class {
    public static boolean contains(SetProxyLike $this, Object elem) {
        return ((SetLike)$this.self()).contains(elem);
    }

    public static Set $plus(SetProxyLike $this, Object elem) {
        return ((SetLike)$this.self()).$plus(elem);
    }

    public static Set $minus(SetProxyLike $this, Object elem) {
        return ((SetLike)$this.self()).$minus(elem);
    }

    public static boolean isEmpty(SetProxyLike $this) {
        return ((SetLike)$this.self()).isEmpty();
    }

    public static boolean apply(SetProxyLike $this, Object elem) {
        return ((GenSetLike)$this.self()).apply(elem);
    }

    public static Set intersect(SetProxyLike $this, GenSet that) {
        return (Set)((GenSetLike)$this.self()).intersect(that);
    }

    public static Set $amp(SetProxyLike $this, GenSet that) {
        return (Set)((GenSetLike)$this.self()).$amp(that);
    }

    public static Set union(SetProxyLike $this, GenSet that) {
        return ((SetLike)$this.self()).union(that);
    }

    public static Set $bar(SetProxyLike $this, GenSet that) {
        return (Set)((GenSetLike)$this.self()).$bar(that);
    }

    public static Set diff(SetProxyLike $this, GenSet that) {
        return ((SetLike)$this.self()).diff(that);
    }

    public static Set $amp$tilde(SetProxyLike $this, GenSet that) {
        return (Set)((GenSetLike)$this.self()).$amp$tilde(that);
    }

    public static boolean subsetOf(SetProxyLike $this, GenSet that) {
        return ((GenSetLike)$this.self()).subsetOf(that);
    }

    public static void $init$(SetProxyLike $this) {
    }
}

