/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.concurrent;

import scala.collection.concurrent.BasicNode;
import scala.collection.concurrent.CNode;
import scala.collection.concurrent.Gen;
import scala.collection.concurrent.INode;

public final class INode$ {
    public static final INode$ MODULE$;
    private final Object KEY_PRESENT;
    private final Object KEY_ABSENT;

    static {
        new INode$();
    }

    public Object KEY_PRESENT() {
        return this.KEY_PRESENT;
    }

    public Object KEY_ABSENT() {
        return this.KEY_ABSENT;
    }

    public <K, V> INode<K, V> newRootNode() {
        Gen gen = new Gen();
        CNode cn = new CNode(0, new BasicNode[0], gen);
        return new INode(cn, gen);
    }

    private INode$() {
        MODULE$ = this;
        this.KEY_PRESENT = new Object();
        this.KEY_ABSENT = new Object();
    }
}

