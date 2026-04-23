/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple3;
import scala.collection.mutable.AVLTree;
import scala.collection.mutable.Node;

public final class Node$
implements Serializable {
    public static final Node$ MODULE$;

    static {
        new Node$();
    }

    public final String toString() {
        return "Node";
    }

    public <A> Node<A> apply(A data, AVLTree<A> left, AVLTree<A> right) {
        return new Node<A>(data, left, right);
    }

    public <A> Option<Tuple3<A, AVLTree<A>, AVLTree<A>>> unapply(Node<A> x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<Tuple3<A, AVLTree<A>, AVLTree<A>>>(new Tuple3<A, AVLTree<A>, AVLTree<A>>(x$0.data(), x$0.left(), x$0.right()));
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Node$() {
        MODULE$ = this;
    }
}

