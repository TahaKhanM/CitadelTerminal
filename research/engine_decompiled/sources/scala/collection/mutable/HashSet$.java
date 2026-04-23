/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Serializable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.MutableSetFactory;
import scala.collection.mutable.HashSet;

public final class HashSet$
extends MutableSetFactory<HashSet>
implements Serializable {
    public static final HashSet$ MODULE$;

    static {
        new HashSet$();
    }

    public <A> CanBuildFrom<HashSet<?>, A, HashSet<A>> canBuildFrom() {
        return this.setCanBuildFrom();
    }

    @Override
    public <A> HashSet<A> empty() {
        return new HashSet();
    }

    private Object readResolve() {
        return MODULE$;
    }

    private HashSet$() {
        MODULE$ = this;
    }
}

