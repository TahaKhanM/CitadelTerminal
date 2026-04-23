/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import java.util.NoSuchElementException;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.mutable.AVLTree;
import scala.collection.mutable.Leaf$;
import scala.collection.mutable.Node;
import scala.math.Ordering;
import scala.runtime.Nothing$;
import scala.sys.package$;

public abstract class AVLTree$class {
    public static Iterator iterator(AVLTree $this) {
        return Iterator$.MODULE$.empty();
    }

    public static boolean contains(AVLTree $this, Object value, Ordering ordering) {
        return false;
    }

    public static AVLTree insert(AVLTree $this, Object value, Ordering ordering) {
        return new Node<Nothing$>((Nothing$)value, Leaf$.MODULE$, Leaf$.MODULE$);
    }

    public static AVLTree remove(AVLTree $this, Object value, Ordering ordering) {
        throw new NoSuchElementException(String.valueOf(value));
    }

    public static Tuple2 removeMin(AVLTree $this) {
        throw package$.MODULE$.error("Should not happen.");
    }

    public static Tuple2 removeMax(AVLTree $this) {
        throw package$.MODULE$.error("Should not happen.");
    }

    public static AVLTree rebalance(AVLTree $this) {
        return $this;
    }

    public static Node leftRotation(AVLTree $this) {
        throw package$.MODULE$.error("Should not happen.");
    }

    public static Node rightRotation(AVLTree $this) {
        throw package$.MODULE$.error("Should not happen.");
    }

    public static Node doubleLeftRotation(AVLTree $this) {
        throw package$.MODULE$.error("Should not happen.");
    }

    public static Node doubleRightRotation(AVLTree $this) {
        throw package$.MODULE$.error("Should not happen.");
    }

    public static void $init$(AVLTree $this) {
    }
}

