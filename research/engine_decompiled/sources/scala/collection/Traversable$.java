/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.Traversable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.TraversableFactory;
import scala.collection.mutable.Builder;
import scala.util.control.Breaks;

public final class Traversable$
extends GenTraversableFactory<Traversable>
implements TraversableFactory<Traversable> {
    public static final Traversable$ MODULE$;
    private final Breaks breaks;

    static {
        new Traversable$();
    }

    public Breaks breaks() {
        return this.breaks;
    }

    public <A> CanBuildFrom<Traversable<?>, A, Traversable<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, Traversable<A>> newBuilder() {
        return scala.collection.immutable.Traversable$.MODULE$.newBuilder();
    }

    private Traversable$() {
        MODULE$ = this;
        this.breaks = new Breaks();
    }
}

