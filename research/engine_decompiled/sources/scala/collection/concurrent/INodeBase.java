/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.concurrent;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import scala.collection.concurrent.BasicNode;
import scala.collection.concurrent.Gen;
import scala.collection.concurrent.MainNode;

abstract class INodeBase<K, V>
extends BasicNode {
    public static final AtomicReferenceFieldUpdater<INodeBase, MainNode> updater = AtomicReferenceFieldUpdater.newUpdater(INodeBase.class, MainNode.class, "mainnode");
    public static final Object RESTART = new Object();
    public volatile MainNode<K, V> mainnode = null;
    public final Gen gen;

    public INodeBase(Gen generation) {
        this.gen = generation;
    }

    public BasicNode prev() {
        return null;
    }
}

