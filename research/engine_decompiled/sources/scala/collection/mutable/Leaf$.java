/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Product;
import scala.Product$class;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.mutable.AVLTree;
import scala.collection.mutable.AVLTree$class;
import scala.collection.mutable.Node;
import scala.math.Ordering;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;

public final class Leaf$
implements AVLTree<Nothing$>,
Product {
    public static final Leaf$ MODULE$;
    private final int balance;
    private final int depth;

    static {
        new Leaf$();
    }

    @Override
    public <B> Iterator<B> iterator() {
        return AVLTree$class.iterator(this);
    }

    @Override
    public <B> boolean contains(B value, Ordering<B> ordering) {
        return AVLTree$class.contains(this, value, ordering);
    }

    @Override
    public <B> AVLTree<B> insert(B value, Ordering<B> ordering) {
        return AVLTree$class.insert(this, value, ordering);
    }

    @Override
    public <B> AVLTree<Nothing$> remove(B value, Ordering<B> ordering) {
        return AVLTree$class.remove(this, value, ordering);
    }

    @Override
    public <B> Tuple2<B, AVLTree<B>> removeMin() {
        return AVLTree$class.removeMin(this);
    }

    @Override
    public <B> Tuple2<B, AVLTree<B>> removeMax() {
        return AVLTree$class.removeMax(this);
    }

    @Override
    public <B> AVLTree<B> rebalance() {
        return AVLTree$class.rebalance(this);
    }

    @Override
    public <B> Node<B> leftRotation() {
        return AVLTree$class.leftRotation(this);
    }

    @Override
    public <B> Node<B> rightRotation() {
        return AVLTree$class.rightRotation(this);
    }

    @Override
    public <B> Node<B> doubleLeftRotation() {
        return AVLTree$class.doubleLeftRotation(this);
    }

    @Override
    public <B> Node<B> doubleRightRotation() {
        return AVLTree$class.doubleRightRotation(this);
    }

    @Override
    public int balance() {
        return this.balance;
    }

    @Override
    public int depth() {
        return this.depth;
    }

    @Override
    public String productPrefix() {
        return "Leaf";
    }

    @Override
    public int productArity() {
        return 0;
    }

    @Override
    public Object productElement(int x$1) {
        throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof Leaf$;
    }

    public int hashCode() {
        return 2364286;
    }

    public String toString() {
        return "Leaf";
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Leaf$() {
        MODULE$ = this;
        AVLTree$class.$init$(this);
        Product$class.$init$(this);
        this.balance = 0;
        this.depth = -1;
    }
}

