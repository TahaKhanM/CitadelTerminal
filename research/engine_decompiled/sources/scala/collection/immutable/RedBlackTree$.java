/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import java.util.NoSuchElementException;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.Iterator;
import scala.collection.immutable.RedBlackTree;
import scala.collection.immutable.RedBlackTree$BlackTree$;
import scala.collection.immutable.RedBlackTree$NList$;
import scala.collection.immutable.RedBlackTree$RedTree$;
import scala.collection.mutable.StringBuilder;
import scala.math.Ordering;
import scala.runtime.BoxesRunTime;
import scala.sys.package$;

public final class RedBlackTree$ {
    public static final RedBlackTree$ MODULE$;

    static {
        new RedBlackTree$();
    }

    public boolean isEmpty(RedBlackTree.Tree<?, ?> tree) {
        return tree == null;
    }

    public <A> boolean contains(RedBlackTree.Tree<A, ?> tree, A x, Ordering<A> evidence$1) {
        return this.lookup(tree, x, evidence$1) != null;
    }

    public <A, B> Option<B> get(RedBlackTree.Tree<A, B> tree, A x, Ordering<A> evidence$2) {
        RedBlackTree.Tree<A, B> tree2 = this.lookup(tree, x, evidence$2);
        Option option = tree2 == null ? None$.MODULE$ : new Some<B>(tree2.value());
        return option;
    }

    public <A, B> RedBlackTree.Tree<A, B> lookup(RedBlackTree.Tree<A, B> tree, A x, Ordering<A> ordering) {
        RedBlackTree.Tree<A, B> tree2;
        block3: {
            while (true) {
                if (tree == null) {
                    tree2 = null;
                    break block3;
                }
                int cmp = ordering.compare(x, tree.key());
                if (cmp < 0) {
                    tree = tree.left();
                    continue;
                }
                if (cmp <= 0) break;
                tree = tree.right();
            }
            tree2 = tree;
        }
        return tree2;
    }

    public int count(RedBlackTree.Tree<?, ?> tree) {
        return tree == null ? 0 : tree.count();
    }

    public <A> int countInRange(RedBlackTree.Tree<A, ?> tree, Option<A> from2, Option<A> to2, Ordering<A> ordering) {
        int n;
        block5: {
            int n2;
            block6: {
                while (true) {
                    if (tree == null) {
                        n = 0;
                        break block5;
                    }
                    Tuple2<Option<A>, Option<A>> tuple2 = new Tuple2<Option<A>, Option<A>>(from2, to2);
                    if (None$.MODULE$.equals(tuple2._1()) && None$.MODULE$.equals(tuple2._2())) {
                        n2 = tree.count();
                        break block6;
                    }
                    if (tuple2._1() instanceof Some) {
                        Some some = (Some)tuple2._1();
                        if (ordering.lt(tree.key(), some.x())) {
                            tree = tree.right();
                            continue;
                        }
                    }
                    if (!(tuple2._2() instanceof Some)) break;
                    Some some = (Some)tuple2._2();
                    if (!ordering.gteq(tree.key(), some.x())) break;
                    tree = tree.left();
                }
                n2 = 1 + this.countInRange(tree.left(), from2, None$.MODULE$, ordering) + this.countInRange(tree.right(), None$.MODULE$, to2, ordering);
            }
            n = n2;
        }
        return n;
    }

    public <A, B, B1> RedBlackTree.Tree<A, B1> update(RedBlackTree.Tree<A, B> tree, A k, B1 v, boolean overwrite, Ordering<A> evidence$3) {
        return this.blacken(this.upd(tree, k, v, overwrite, evidence$3));
    }

    public <A, B> RedBlackTree.Tree<A, B> delete(RedBlackTree.Tree<A, B> tree, A k, Ordering<A> evidence$4) {
        return this.blacken(this.del(tree, k, evidence$4));
    }

    public <A, B> RedBlackTree.Tree<A, B> rangeImpl(RedBlackTree.Tree<A, B> tree, Option<A> from2, Option<A> until2, Ordering<A> evidence$5) {
        Tuple2<Option<A>, Option<A>> tuple2;
        block6: {
            RedBlackTree.Tree<A, B> tree2;
            block3: {
                block5: {
                    block4: {
                        block2: {
                            tuple2 = new Tuple2<Option<A>, Option<A>>(from2, until2);
                            if (!(tuple2._1() instanceof Some)) break block2;
                            Some some = (Some)tuple2._1();
                            if (!(tuple2._2() instanceof Some)) break block2;
                            Some some2 = (Some)tuple2._2();
                            tree2 = this.range(tree, some.x(), some2.x(), evidence$5);
                            break block3;
                        }
                        if (!(tuple2._1() instanceof Some)) break block4;
                        Some some = (Some)tuple2._1();
                        if (!None$.MODULE$.equals(tuple2._2())) break block4;
                        tree2 = this.from(tree, some.x(), evidence$5);
                        break block3;
                    }
                    if (!None$.MODULE$.equals(tuple2._1()) || !(tuple2._2() instanceof Some)) break block5;
                    Some some = (Some)tuple2._2();
                    tree2 = this.until(tree, some.x(), evidence$5);
                    break block3;
                }
                if (!None$.MODULE$.equals(tuple2._1()) || !None$.MODULE$.equals(tuple2._2())) break block6;
                tree2 = tree;
            }
            return tree2;
        }
        throw new MatchError(tuple2);
    }

    public <A, B> RedBlackTree.Tree<A, B> range(RedBlackTree.Tree<A, B> tree, A from2, A until2, Ordering<A> evidence$6) {
        return this.blacken(this.doRange(tree, from2, until2, evidence$6));
    }

    public <A, B> RedBlackTree.Tree<A, B> from(RedBlackTree.Tree<A, B> tree, A from2, Ordering<A> evidence$7) {
        return this.blacken(this.doFrom(tree, from2, evidence$7));
    }

    public <A, B> RedBlackTree.Tree<A, B> to(RedBlackTree.Tree<A, B> tree, A to2, Ordering<A> evidence$8) {
        return this.blacken(this.doTo(tree, to2, evidence$8));
    }

    public <A, B> RedBlackTree.Tree<A, B> until(RedBlackTree.Tree<A, B> tree, A key, Ordering<A> evidence$9) {
        return this.blacken(this.doUntil(tree, key, evidence$9));
    }

    public <A, B> RedBlackTree.Tree<A, B> drop(RedBlackTree.Tree<A, B> tree, int n, Ordering<A> evidence$10) {
        return this.blacken(this.doDrop(tree, n));
    }

    public <A, B> RedBlackTree.Tree<A, B> take(RedBlackTree.Tree<A, B> tree, int n, Ordering<A> evidence$11) {
        return this.blacken(this.doTake(tree, n));
    }

    public <A, B> RedBlackTree.Tree<A, B> slice(RedBlackTree.Tree<A, B> tree, int from2, int until2, Ordering<A> evidence$12) {
        return this.blacken(this.doSlice(tree, from2, until2));
    }

    /*
     * WARNING - void declaration
     */
    public <A, B> RedBlackTree.Tree<A, B> smallest(RedBlackTree.Tree<A, B> tree) {
        void var2_2;
        if (tree == null) {
            throw new NoSuchElementException("empty map");
        }
        RedBlackTree.Tree<A, B> result2 = tree;
        while (result2.left() != null) {
            result2 = result2.left();
        }
        return var2_2;
    }

    /*
     * WARNING - void declaration
     */
    public <A, B> RedBlackTree.Tree<A, B> greatest(RedBlackTree.Tree<A, B> tree) {
        void var2_2;
        if (tree == null) {
            throw new NoSuchElementException("empty map");
        }
        RedBlackTree.Tree<A, B> result2 = tree;
        while (result2.right() != null) {
            result2 = result2.right();
        }
        return var2_2;
    }

    public <A, B, U> void foreach(RedBlackTree.Tree<A, B> tree, Function1<Tuple2<A, B>, U> f) {
        if (tree != null) {
            this._foreach(tree, f);
        }
    }

    private <A, B, U> void _foreach(RedBlackTree.Tree<A, B> tree, Function1<Tuple2<A, B>, U> f) {
        while (true) {
            if (tree.left() != null) {
                this._foreach(tree.left(), f);
            }
            f.apply(new Tuple2<A, B>(tree.key(), tree.value()));
            if (tree.right() == null) break;
            tree = tree.right();
        }
    }

    public <A, U> void foreachKey(RedBlackTree.Tree<A, ?> tree, Function1<A, U> f) {
        if (tree != null) {
            this._foreachKey(tree, f);
        }
    }

    private <A, U> void _foreachKey(RedBlackTree.Tree<A, ?> tree, Function1<A, U> f) {
        while (true) {
            if (tree.left() != null) {
                this._foreachKey(tree.left(), f);
            }
            f.apply(tree.key());
            if (tree.right() == null) break;
            tree = tree.right();
        }
    }

    public <A, B> Iterator<Tuple2<A, B>> iterator(RedBlackTree.Tree<A, B> tree, Option<A> start, Ordering<A> evidence$13) {
        return new RedBlackTree.EntriesIterator<A, B>(tree, start, evidence$13);
    }

    public <A, B> None$ iterator$default$2() {
        return None$.MODULE$;
    }

    public <A> Iterator<A> keysIterator(RedBlackTree.Tree<A, ?> tree, Option<A> start, Ordering<A> evidence$14) {
        return new RedBlackTree.KeysIterator(tree, start, evidence$14);
    }

    public <A> None$ keysIterator$default$2() {
        return None$.MODULE$;
    }

    public <A, B> Iterator<B> valuesIterator(RedBlackTree.Tree<A, B> tree, Option<A> start, Ordering<A> evidence$15) {
        return new RedBlackTree.ValuesIterator<A, B>(tree, start, evidence$15);
    }

    public <A, B> None$ valuesIterator$default$2() {
        return None$.MODULE$;
    }

    public <A, B> RedBlackTree.Tree<A, B> nth(RedBlackTree.Tree<A, B> tree, int n) {
        while (true) {
            int count2;
            if (n < (count2 = this.count(tree.left()))) {
                tree = tree.left();
                continue;
            }
            if (n <= count2) break;
            n = n - count2 - 1;
            tree = tree.right();
        }
        return tree;
    }

    public boolean isBlack(RedBlackTree.Tree<?, ?> tree) {
        return tree == null || this.scala$collection$immutable$RedBlackTree$$isBlackTree(tree);
    }

    private boolean isRedTree(RedBlackTree.Tree<?, ?> tree) {
        return tree instanceof RedBlackTree.RedTree;
    }

    public boolean scala$collection$immutable$RedBlackTree$$isBlackTree(RedBlackTree.Tree<?, ?> tree) {
        return tree instanceof RedBlackTree.BlackTree;
    }

    private <A, B> RedBlackTree.Tree<A, B> blacken(RedBlackTree.Tree<A, B> t) {
        return t == null ? null : t.black();
    }

    private <A, B> RedBlackTree.Tree<A, B> mkTree(boolean isBlack, A k, B v, RedBlackTree.Tree<A, B> l, RedBlackTree.Tree<A, B> r) {
        RedBlackTree.Tree tree;
        if (isBlack) {
            RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
            tree = new RedBlackTree.BlackTree<A, B>(k, v, l, r);
        } else {
            RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
            tree = new RedBlackTree.RedTree<A, B>(k, v, l, r);
        }
        return tree;
    }

    public <A, B, B1> RedBlackTree.Tree<A, B1> scala$collection$immutable$RedBlackTree$$balanceLeft(boolean isBlack, A z, B zv, RedBlackTree.Tree<A, B1> l, RedBlackTree.Tree<A, B1> d) {
        RedBlackTree.RedTree<A, B1> redTree;
        if (this.isRedTree(l) && this.isRedTree(l.left())) {
            RedBlackTree.Tree<A, B1> tree = l.left().right();
            RedBlackTree.Tree<A, B1> tree2 = l.left().left();
            B1 B1 = l.left().value();
            A a = l.left().key();
            RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
            RedBlackTree.Tree<A, B1> tree3 = l.right();
            RedBlackTree$BlackTree$ redBlackTree$BlackTree$2 = RedBlackTree$BlackTree$.MODULE$;
            RedBlackTree.BlackTree<A, B> blackTree = new RedBlackTree.BlackTree<A, B>(z, zv, tree3, d);
            RedBlackTree.BlackTree<A, B1> blackTree2 = new RedBlackTree.BlackTree<A, B1>(a, B1, tree2, tree);
            B1 B12 = l.value();
            A a2 = l.key();
            RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
            redTree = new RedBlackTree.RedTree<A, B1>(a2, B12, blackTree2, blackTree);
        } else if (this.isRedTree(l) && this.isRedTree(l.right())) {
            RedBlackTree.Tree<A, B1> tree = l.right().left();
            RedBlackTree.Tree<A, B1> tree4 = l.left();
            B1 B1 = l.value();
            A a = l.key();
            RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
            RedBlackTree.Tree<A, B1> tree5 = l.right().right();
            RedBlackTree$BlackTree$ redBlackTree$BlackTree$3 = RedBlackTree$BlackTree$.MODULE$;
            RedBlackTree.BlackTree<A, B> blackTree = new RedBlackTree.BlackTree<A, B>(z, zv, tree5, d);
            RedBlackTree.BlackTree<A, B1> blackTree3 = new RedBlackTree.BlackTree<A, B1>(a, B1, tree4, tree);
            B1 B13 = l.right().value();
            A a3 = l.right().key();
            RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
            redTree = new RedBlackTree.RedTree<A, B1>(a3, B13, blackTree3, blackTree);
        } else {
            redTree = this.mkTree(isBlack, z, zv, l, d);
        }
        return redTree;
    }

    public <A, B, B1> RedBlackTree.Tree<A, B1> scala$collection$immutable$RedBlackTree$$balanceRight(boolean isBlack, A x, B xv, RedBlackTree.Tree<A, B1> a, RedBlackTree.Tree<A, B1> r) {
        RedBlackTree.RedTree<A, B1> redTree;
        if (this.isRedTree(r) && this.isRedTree(r.left())) {
            RedBlackTree.Tree<A, B1> tree = r.left().left();
            RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
            RedBlackTree.Tree<A, B1> tree2 = r.right();
            RedBlackTree.Tree<A, B1> tree3 = r.left().right();
            B1 B1 = r.value();
            A a2 = r.key();
            RedBlackTree$BlackTree$ redBlackTree$BlackTree$2 = RedBlackTree$BlackTree$.MODULE$;
            RedBlackTree.BlackTree<A, B1> blackTree = new RedBlackTree.BlackTree<A, B1>(a2, B1, tree3, tree2);
            RedBlackTree.BlackTree<A, B> blackTree2 = new RedBlackTree.BlackTree<A, B>(x, xv, a, tree);
            B1 B12 = r.left().value();
            A a3 = r.left().key();
            RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
            redTree = new RedBlackTree.RedTree<A, B1>(a3, B12, blackTree2, blackTree);
        } else if (this.isRedTree(r) && this.isRedTree(r.right())) {
            RedBlackTree.Tree<A, B1> tree = r.left();
            RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
            RedBlackTree.Tree<A, B1> tree4 = r.right().right();
            RedBlackTree.Tree<A, B1> tree5 = r.right().left();
            B1 B1 = r.right().value();
            A a4 = r.right().key();
            RedBlackTree$BlackTree$ redBlackTree$BlackTree$3 = RedBlackTree$BlackTree$.MODULE$;
            RedBlackTree.BlackTree<A, B1> blackTree = new RedBlackTree.BlackTree<A, B1>(a4, B1, tree5, tree4);
            RedBlackTree.BlackTree<A, B> blackTree3 = new RedBlackTree.BlackTree<A, B>(x, xv, a, tree);
            B1 B13 = r.value();
            A a5 = r.key();
            RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
            redTree = new RedBlackTree.RedTree<A, B1>(a5, B13, blackTree3, blackTree);
        } else {
            redTree = this.mkTree(isBlack, x, xv, a, r);
        }
        return redTree;
    }

    private <A, B, B1> RedBlackTree.Tree<A, B1> upd(RedBlackTree.Tree<A, B> tree, A k, B1 v, boolean overwrite, Ordering<A> ordering) {
        RedBlackTree.Tree tree2;
        if (tree == null) {
            RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
            tree2 = new RedBlackTree.RedTree<A, B1>(k, v, null, null);
        } else {
            A a;
            int cmp = ordering.compare(k, tree.key());
            tree2 = cmp < 0 ? this.scala$collection$immutable$RedBlackTree$$balanceLeft(this.scala$collection$immutable$RedBlackTree$$isBlackTree(tree), tree.key(), tree.value(), this.upd(tree.left(), k, v, overwrite, ordering), tree.right()) : (cmp > 0 ? this.scala$collection$immutable$RedBlackTree$$balanceRight(this.scala$collection$immutable$RedBlackTree$$isBlackTree(tree), tree.key(), tree.value(), tree.left(), this.upd(tree.right(), k, v, overwrite, ordering)) : (!overwrite && (k == (a = tree.key()) ? true : (k == null ? false : (k instanceof Number ? BoxesRunTime.equalsNumObject((Number)k, a) : (k instanceof Character ? BoxesRunTime.equalsCharObject((Character)k, a) : k.equals(a))))) ? tree : this.mkTree(this.scala$collection$immutable$RedBlackTree$$isBlackTree(tree), k, v, tree.left(), tree.right())));
        }
        return tree2;
    }

    private <A, B, B1> RedBlackTree.Tree<A, B1> updNth(RedBlackTree.Tree<A, B> tree, int idx, A k, B1 v, boolean overwrite) {
        RedBlackTree.Tree tree2;
        if (tree == null) {
            RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
            tree2 = new RedBlackTree.RedTree<A, B1>(k, v, null, null);
        } else {
            int rank = this.count(tree.left()) + 1;
            tree2 = idx < rank ? this.scala$collection$immutable$RedBlackTree$$balanceLeft(this.scala$collection$immutable$RedBlackTree$$isBlackTree(tree), tree.key(), tree.value(), this.updNth(tree.left(), idx, k, v, overwrite), tree.right()) : (idx > rank ? this.scala$collection$immutable$RedBlackTree$$balanceRight(this.scala$collection$immutable$RedBlackTree$$isBlackTree(tree), tree.key(), tree.value(), tree.left(), this.updNth(tree.right(), idx - rank, k, v, overwrite)) : (overwrite ? this.mkTree(this.scala$collection$immutable$RedBlackTree$$isBlackTree(tree), k, v, tree.left(), tree.right()) : tree));
        }
        return tree2;
    }

    private <A, B> RedBlackTree.Tree<A, B> del(RedBlackTree.Tree<A, B> tree, A k, Ordering<A> ordering) {
        int cmp;
        return tree == null ? null : ((cmp = ordering.compare(k, tree.key())) < 0 ? this.delLeft$1(tree, k, ordering) : (cmp > 0 ? this.delRight$1(tree, k, ordering) : this.append$1(tree.left(), tree.right())));
    }

    private <A, B> RedBlackTree.Tree<A, B> doFrom(RedBlackTree.Tree<A, B> tree, A from2, Ordering<A> ordering) {
        if (tree == null) {
            return null;
        }
        if (ordering.lt(tree.key(), from2)) {
            return this.doFrom(tree.right(), from2, ordering);
        }
        RedBlackTree.Tree<A, B> newLeft = this.doFrom(tree.left(), from2, ordering);
        return newLeft == tree.left() ? tree : (newLeft == null ? this.upd(tree.right(), tree.key(), tree.value(), false, ordering) : this.rebalance(tree, newLeft, tree.right()));
    }

    private <A, B> RedBlackTree.Tree<A, B> doTo(RedBlackTree.Tree<A, B> tree, A to2, Ordering<A> ordering) {
        if (tree == null) {
            return null;
        }
        if (ordering.lt(to2, tree.key())) {
            return this.doTo(tree.left(), to2, ordering);
        }
        RedBlackTree.Tree<A, B> newRight = this.doTo(tree.right(), to2, ordering);
        return newRight == tree.right() ? tree : (newRight == null ? this.upd(tree.left(), tree.key(), tree.value(), false, ordering) : this.rebalance(tree, tree.left(), newRight));
    }

    private <A, B> RedBlackTree.Tree<A, B> doUntil(RedBlackTree.Tree<A, B> tree, A until2, Ordering<A> ordering) {
        if (tree == null) {
            return null;
        }
        if (ordering.lteq(until2, tree.key())) {
            return this.doUntil(tree.left(), until2, ordering);
        }
        RedBlackTree.Tree<A, B> newRight = this.doUntil(tree.right(), until2, ordering);
        return newRight == tree.right() ? tree : (newRight == null ? this.upd(tree.left(), tree.key(), tree.value(), false, ordering) : this.rebalance(tree, tree.left(), newRight));
    }

    private <A, B> RedBlackTree.Tree<A, B> doRange(RedBlackTree.Tree<A, B> tree, A from2, A until2, Ordering<A> ordering) {
        if (tree == null) {
            return null;
        }
        if (ordering.lt(tree.key(), from2)) {
            return this.doRange(tree.right(), from2, until2, ordering);
        }
        if (ordering.lteq(until2, tree.key())) {
            return this.doRange(tree.left(), from2, until2, ordering);
        }
        RedBlackTree.Tree<A, B> newLeft = this.doFrom(tree.left(), from2, ordering);
        RedBlackTree.Tree<A, B> newRight = this.doUntil(tree.right(), until2, ordering);
        return newLeft == tree.left() && newRight == tree.right() ? tree : (newLeft == null ? this.upd(newRight, tree.key(), tree.value(), false, ordering) : (newRight == null ? this.upd(newLeft, tree.key(), tree.value(), false, ordering) : this.rebalance(tree, newLeft, newRight)));
    }

    private <A, B> RedBlackTree.Tree<A, B> doDrop(RedBlackTree.Tree<A, B> tree, int n) {
        if (n <= 0) {
            return tree;
        }
        if (n >= this.count(tree)) {
            return null;
        }
        int count2 = this.count(tree.left());
        if (n > count2) {
            return this.doDrop(tree.right(), n - count2 - 1);
        }
        RedBlackTree.Tree<A, B> newLeft = this.doDrop(tree.left(), n);
        return newLeft == tree.left() ? tree : (newLeft == null ? this.updNth(tree.right(), n - count2 - 1, tree.key(), tree.value(), false) : this.rebalance(tree, newLeft, tree.right()));
    }

    private <A, B> RedBlackTree.Tree<A, B> doTake(RedBlackTree.Tree<A, B> tree, int n) {
        if (n <= 0) {
            return null;
        }
        if (n >= this.count(tree)) {
            return tree;
        }
        int count2 = this.count(tree.left());
        if (n <= count2) {
            return this.doTake(tree.left(), n);
        }
        RedBlackTree.Tree<A, B> newRight = this.doTake(tree.right(), n - count2 - 1);
        return newRight == tree.right() ? tree : (newRight == null ? this.updNth(tree.left(), n, tree.key(), tree.value(), false) : this.rebalance(tree, tree.left(), newRight));
    }

    private <A, B> RedBlackTree.Tree<A, B> doSlice(RedBlackTree.Tree<A, B> tree, int from2, int until2) {
        if (tree == null) {
            return null;
        }
        int count2 = this.count(tree.left());
        if (from2 > count2) {
            return this.doSlice(tree.right(), from2 - count2 - 1, until2 - count2 - 1);
        }
        if (until2 <= count2) {
            return this.doSlice(tree.left(), from2, until2);
        }
        RedBlackTree.Tree<A, B> newLeft = this.doDrop(tree.left(), from2);
        RedBlackTree.Tree<A, B> newRight = this.doTake(tree.right(), until2 - count2 - 1);
        return newLeft == tree.left() && newRight == tree.right() ? tree : (newLeft == null ? this.updNth(newRight, from2 - count2 - 1, tree.key(), tree.value(), false) : (newRight == null ? this.updNth(newLeft, until2, tree.key(), tree.value(), false) : this.rebalance(tree, newLeft, newRight)));
    }

    private <A, B> Tuple4<RedBlackTree.NList<RedBlackTree.Tree<A, B>>, Object, Object, Object> compareDepth(RedBlackTree.Tree<A, B> left, RedBlackTree.Tree<A, B> right) {
        return this.unzipBoth$1(left, right, null, null, 0);
    }

    private <A, B> RedBlackTree.Tree<A, B> rebalance(RedBlackTree.Tree<A, B> tree, RedBlackTree.Tree<A, B> newLeft, RedBlackTree.Tree<A, B> newRight) {
        RedBlackTree.Tree<A, B> blkNewRight;
        RedBlackTree.Tree<A, B> blkNewLeft = this.blacken(newLeft);
        Tuple4<RedBlackTree.NList<RedBlackTree.Tree<A, B>>, Object, Object, Object> tuple4 = this.compareDepth(blkNewLeft, blkNewRight = this.blacken(newRight));
        if (tuple4 != null) {
            RedBlackTree.Tree tree2;
            Tuple4<RedBlackTree.NList<RedBlackTree.Tree<A, B>>, Object, Object, Object> tuple42 = new Tuple4<RedBlackTree.NList<RedBlackTree.Tree<A, B>>, Object, Object, Object>(tuple4._1(), tuple4._2(), tuple4._3(), tuple4._4());
            RedBlackTree.NList<RedBlackTree.Tree<A, B>> zipper = tuple42._1();
            boolean levelled = BoxesRunTime.unboxToBoolean(tuple42._2());
            boolean leftMost = BoxesRunTime.unboxToBoolean(tuple42._3());
            int smallerDepth = BoxesRunTime.unboxToInt(tuple42._4());
            if (levelled) {
                B b = tree.value();
                A a = tree.key();
                RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
                tree2 = new RedBlackTree.BlackTree<A, B>(a, b, blkNewLeft, blkNewRight);
            } else {
                RedBlackTree.RedTree<A, B> redTree;
                RedBlackTree.NList zipFrom = this.findDepth$1(zipper, smallerDepth);
                if (leftMost) {
                    RedBlackTree.Tree tree3 = (RedBlackTree.Tree)zipFrom.head();
                    B b = tree.value();
                    A a = tree.key();
                    RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
                    redTree = new RedBlackTree.RedTree<A, B>(a, b, blkNewLeft, tree3);
                } else {
                    RedBlackTree.Tree tree4 = (RedBlackTree.Tree)zipFrom.head();
                    B b = tree.value();
                    A a = tree.key();
                    RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
                    redTree = new RedBlackTree.RedTree<A, B>(a, b, tree4, blkNewRight);
                }
                RedBlackTree.RedTree<A, B> union2 = redTree;
                RedBlackTree.Tree zippedTree = RedBlackTree$NList$.MODULE$.foldLeft(zipFrom.tail(), union2, new Serializable(leftMost){
                    public static final long serialVersionUID = 0L;
                    private final boolean leftMost$1;

                    public final RedBlackTree.Tree<A, B> apply(RedBlackTree.Tree<A, B> tree, RedBlackTree.Tree<A, B> node) {
                        return this.leftMost$1 ? RedBlackTree$.MODULE$.scala$collection$immutable$RedBlackTree$$balanceLeft(RedBlackTree$.MODULE$.scala$collection$immutable$RedBlackTree$$isBlackTree(node), node.key(), node.value(), tree, node.right()) : RedBlackTree$.MODULE$.scala$collection$immutable$RedBlackTree$$balanceRight(RedBlackTree$.MODULE$.scala$collection$immutable$RedBlackTree$$isBlackTree(node), node.key(), node.value(), node.left(), tree);
                    }
                    {
                        this.leftMost$1 = leftMost$1;
                    }
                });
                tree2 = zippedTree;
            }
            return tree2;
        }
        throw new MatchError(tuple4);
    }

    private final RedBlackTree.Tree balance$1(Object x, Object xv, RedBlackTree.Tree tl, RedBlackTree.Tree tr) {
        RedBlackTree.Tree tree;
        if (this.isRedTree(tl)) {
            if (this.isRedTree(tr)) {
                RedBlackTree.Tree tree2 = tr.black();
                RedBlackTree.Tree tree3 = tl.black();
                RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
                tree = new RedBlackTree.RedTree<Object, Object>(x, xv, tree3, tree2);
            } else if (this.isRedTree(tl.left())) {
                RedBlackTree.Tree tree4 = tl.right();
                RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
                RedBlackTree.BlackTree<Object, Object> blackTree = new RedBlackTree.BlackTree<Object, Object>(x, xv, tree4, tr);
                RedBlackTree.Tree tree5 = tl.left().black();
                Object b = tl.value();
                Object a = tl.key();
                RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
                tree = new RedBlackTree.RedTree<Object, Object>(a, b, tree5, blackTree);
            } else if (this.isRedTree(tl.right())) {
                RedBlackTree.Tree tree6 = tl.right().left();
                RedBlackTree.Tree tree7 = tl.left();
                Object b = tl.value();
                Object a = tl.key();
                RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
                RedBlackTree.Tree tree8 = tl.right().right();
                RedBlackTree$BlackTree$ redBlackTree$BlackTree$2 = RedBlackTree$BlackTree$.MODULE$;
                RedBlackTree.BlackTree<Object, Object> blackTree = new RedBlackTree.BlackTree<Object, Object>(x, xv, tree8, tr);
                RedBlackTree.BlackTree blackTree2 = new RedBlackTree.BlackTree(a, b, tree7, tree6);
                Object b2 = tl.right().value();
                Object a2 = tl.right().key();
                RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
                tree = new RedBlackTree.RedTree<Object, Object>(a2, b2, blackTree2, blackTree);
            } else {
                RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
                tree = new RedBlackTree.BlackTree<Object, Object>(x, xv, tl, tr);
            }
        } else if (this.isRedTree(tr)) {
            if (this.isRedTree(tr.right())) {
                RedBlackTree.Tree tree9 = tr.left();
                RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
                RedBlackTree.Tree tree10 = tr.right().black();
                RedBlackTree.BlackTree<Object, Object> blackTree = new RedBlackTree.BlackTree<Object, Object>(x, xv, tl, tree9);
                Object b = tr.value();
                Object a = tr.key();
                RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
                tree = new RedBlackTree.RedTree<Object, Object>(a, b, blackTree, tree10);
            } else if (this.isRedTree(tr.left())) {
                RedBlackTree.Tree tree11 = tr.left().left();
                RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
                RedBlackTree.Tree tree12 = tr.right();
                RedBlackTree.Tree tree13 = tr.left().right();
                Object b = tr.value();
                Object a = tr.key();
                RedBlackTree$BlackTree$ redBlackTree$BlackTree$3 = RedBlackTree$BlackTree$.MODULE$;
                RedBlackTree.BlackTree blackTree = new RedBlackTree.BlackTree(a, b, tree13, tree12);
                RedBlackTree.BlackTree<Object, Object> blackTree3 = new RedBlackTree.BlackTree<Object, Object>(x, xv, tl, tree11);
                Object b3 = tr.left().value();
                Object a3 = tr.left().key();
                RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
                tree = new RedBlackTree.RedTree<Object, Object>(a3, b3, blackTree3, blackTree);
            } else {
                RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
                tree = new RedBlackTree.BlackTree<Object, Object>(x, xv, tl, tr);
            }
        } else {
            RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
            tree = new RedBlackTree.BlackTree<Object, Object>(x, xv, tl, tr);
        }
        return tree;
    }

    private final RedBlackTree.Tree subl$1(RedBlackTree.Tree t) {
        if (t instanceof RedBlackTree.BlackTree) {
            return t.red();
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"Defect: invariance violation; expected black, got ").append(t).toString());
    }

    private final RedBlackTree.Tree balLeft$1(Object x, Object xv, RedBlackTree.Tree tl, RedBlackTree.Tree tr) {
        block5: {
            RedBlackTree.RedTree<Object, Object> redTree;
            block3: {
                block4: {
                    block2: {
                        if (!this.isRedTree(tl)) break block2;
                        RedBlackTree.Tree tree = tl.black();
                        RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
                        redTree = new RedBlackTree.RedTree<Object, Object>(x, xv, tree, tr);
                        break block3;
                    }
                    if (!this.scala$collection$immutable$RedBlackTree$$isBlackTree(tr)) break block4;
                    redTree = this.balance$1(x, xv, tl, tr.red());
                    break block3;
                }
                if (!this.isRedTree(tr) || !this.scala$collection$immutable$RedBlackTree$$isBlackTree(tr.left())) break block5;
                RedBlackTree.Tree tree = tr.left().left();
                RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
                RedBlackTree.Tree tree2 = this.balance$1(tr.key(), tr.value(), tr.left().right(), this.subl$1(tr.right()));
                RedBlackTree.BlackTree<Object, Object> blackTree = new RedBlackTree.BlackTree<Object, Object>(x, xv, tl, tree);
                Object b = tr.left().value();
                Object a = tr.left().key();
                RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
                redTree = new RedBlackTree.RedTree<Object, Object>(a, b, blackTree, tree2);
            }
            return redTree;
        }
        throw package$.MODULE$.error("Defect: invariance violation");
    }

    private final RedBlackTree.Tree balRight$1(Object x, Object xv, RedBlackTree.Tree tl, RedBlackTree.Tree tr) {
        block5: {
            RedBlackTree.RedTree<Object, Object> redTree;
            block3: {
                block4: {
                    block2: {
                        if (!this.isRedTree(tr)) break block2;
                        RedBlackTree.Tree tree = tr.black();
                        RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
                        redTree = new RedBlackTree.RedTree<Object, Object>(x, xv, tl, tree);
                        break block3;
                    }
                    if (!this.scala$collection$immutable$RedBlackTree$$isBlackTree(tl)) break block4;
                    redTree = this.balance$1(x, xv, tl.red(), tr);
                    break block3;
                }
                if (!this.isRedTree(tl) || !this.scala$collection$immutable$RedBlackTree$$isBlackTree(tl.right())) break block5;
                RedBlackTree.Tree tree = tl.right().right();
                RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
                RedBlackTree.BlackTree<Object, Object> blackTree = new RedBlackTree.BlackTree<Object, Object>(x, xv, tree, tr);
                RedBlackTree.Tree tree2 = this.balance$1(tl.key(), tl.value(), this.subl$1(tl.left()), tl.right().left());
                Object b = tl.right().value();
                Object a = tl.right().key();
                RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
                redTree = new RedBlackTree.RedTree<Object, Object>(a, b, tree2, blackTree);
            }
            return redTree;
        }
        throw package$.MODULE$.error("Defect: invariance violation");
    }

    private final RedBlackTree.Tree delLeft$1(RedBlackTree.Tree tree$1, Object k$1, Ordering ordering$1) {
        RedBlackTree.Tree tree;
        if (this.scala$collection$immutable$RedBlackTree$$isBlackTree(tree$1.left())) {
            tree = this.balLeft$1(tree$1.key(), tree$1.value(), this.del(tree$1.left(), k$1, ordering$1), tree$1.right());
        } else {
            RedBlackTree.Tree tree2 = tree$1.right();
            RedBlackTree.Tree tree3 = this.del(tree$1.left(), k$1, ordering$1);
            Object b = tree$1.value();
            Object a = tree$1.key();
            RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
            tree = new RedBlackTree.RedTree(a, b, tree3, tree2);
        }
        return tree;
    }

    private final RedBlackTree.Tree delRight$1(RedBlackTree.Tree tree$1, Object k$1, Ordering ordering$1) {
        RedBlackTree.Tree tree;
        if (this.scala$collection$immutable$RedBlackTree$$isBlackTree(tree$1.right())) {
            tree = this.balRight$1(tree$1.key(), tree$1.value(), tree$1.left(), this.del(tree$1.right(), k$1, ordering$1));
        } else {
            RedBlackTree.Tree tree2 = this.del(tree$1.right(), k$1, ordering$1);
            RedBlackTree.Tree tree3 = tree$1.left();
            Object b = tree$1.value();
            Object a = tree$1.key();
            RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
            tree = new RedBlackTree.RedTree(a, b, tree3, tree2);
        }
        return tree;
    }

    private final RedBlackTree.Tree append$1(RedBlackTree.Tree tl, RedBlackTree.Tree tr) {
        block13: {
            RedBlackTree.Tree tree;
            block8: {
                block12: {
                    block11: {
                        block10: {
                            block9: {
                                block7: {
                                    if (tl != null) break block7;
                                    tree = tr;
                                    break block8;
                                }
                                if (tr != null) break block9;
                                tree = tl;
                                break block8;
                            }
                            if (!this.isRedTree(tl) || !this.isRedTree(tr)) break block10;
                            RedBlackTree.Tree bc = this.append$1(tl.right(), tr.left());
                            if (this.isRedTree(bc)) {
                                RedBlackTree.Tree tree2 = bc.left();
                                RedBlackTree.Tree tree3 = tl.left();
                                Object b = tl.value();
                                Object a = tl.key();
                                RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
                                RedBlackTree.Tree tree4 = tr.right();
                                RedBlackTree.Tree tree5 = bc.right();
                                Object b2 = tr.value();
                                Object a2 = tr.key();
                                RedBlackTree$RedTree$ redBlackTree$RedTree$2 = RedBlackTree$RedTree$.MODULE$;
                                RedBlackTree.RedTree redTree = new RedBlackTree.RedTree(a2, b2, tree5, tree4);
                                RedBlackTree.RedTree redTree2 = new RedBlackTree.RedTree(a, b, tree3, tree2);
                                Object b3 = bc.value();
                                Object a3 = bc.key();
                                RedBlackTree$RedTree$ redBlackTree$RedTree$3 = RedBlackTree$RedTree$.MODULE$;
                                tree = new RedBlackTree.RedTree(a3, b3, redTree2, redTree);
                            } else {
                                RedBlackTree.Tree tree6 = tr.right();
                                Object b = tr.value();
                                Object a = tr.key();
                                RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
                                RedBlackTree.RedTree redTree = new RedBlackTree.RedTree(a, b, bc, tree6);
                                RedBlackTree.Tree tree7 = tl.left();
                                Object b4 = tl.value();
                                Object a4 = tl.key();
                                RedBlackTree$RedTree$ redBlackTree$RedTree$4 = RedBlackTree$RedTree$.MODULE$;
                                tree = new RedBlackTree.RedTree(a4, b4, tree7, redTree);
                            }
                            break block8;
                        }
                        if (!this.scala$collection$immutable$RedBlackTree$$isBlackTree(tl) || !this.scala$collection$immutable$RedBlackTree$$isBlackTree(tr)) break block11;
                        RedBlackTree.Tree bc = this.append$1(tl.right(), tr.left());
                        if (this.isRedTree(bc)) {
                            RedBlackTree.Tree tree8 = bc.left();
                            RedBlackTree.Tree tree9 = tl.left();
                            Object b = tl.value();
                            Object a = tl.key();
                            RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
                            RedBlackTree.Tree tree10 = tr.right();
                            RedBlackTree.Tree tree11 = bc.right();
                            Object b5 = tr.value();
                            Object a5 = tr.key();
                            RedBlackTree$BlackTree$ redBlackTree$BlackTree$2 = RedBlackTree$BlackTree$.MODULE$;
                            RedBlackTree.BlackTree blackTree = new RedBlackTree.BlackTree(a5, b5, tree11, tree10);
                            RedBlackTree.BlackTree blackTree2 = new RedBlackTree.BlackTree(a, b, tree9, tree8);
                            Object b6 = bc.value();
                            Object a6 = bc.key();
                            RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
                            tree = new RedBlackTree.RedTree(a6, b6, blackTree2, blackTree);
                        } else {
                            RedBlackTree.Tree tree12 = tr.right();
                            Object b = tr.value();
                            Object a = tr.key();
                            RedBlackTree$BlackTree$ redBlackTree$BlackTree$ = RedBlackTree$BlackTree$.MODULE$;
                            tree = this.balLeft$1(tl.key(), tl.value(), tl.left(), new RedBlackTree.BlackTree(a, b, bc, tree12));
                        }
                        break block8;
                    }
                    if (!this.isRedTree(tr)) break block12;
                    RedBlackTree.Tree tree13 = tr.right();
                    RedBlackTree.Tree tree14 = this.append$1(tl, tr.left());
                    Object b = tr.value();
                    Object a = tr.key();
                    RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
                    tree = new RedBlackTree.RedTree(a, b, tree14, tree13);
                    break block8;
                }
                if (!this.isRedTree(tl)) break block13;
                RedBlackTree.Tree tree15 = this.append$1(tl.right(), tr);
                RedBlackTree.Tree tree16 = tl.left();
                Object b = tl.value();
                Object a = tl.key();
                RedBlackTree$RedTree$ redBlackTree$RedTree$ = RedBlackTree$RedTree$.MODULE$;
                tree = new RedBlackTree.RedTree(a, b, tree16, tree15);
            }
            return tree;
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"unmatched tree on append: ").append(tl).append((Object)", ").append(tr).toString());
    }

    private final RedBlackTree.NList unzip$1(RedBlackTree.NList zipper, boolean leftMost) {
        while (true) {
            RedBlackTree.Tree next2;
            RedBlackTree.Tree tree = next2 = leftMost ? ((RedBlackTree.Tree)zipper.head()).left() : ((RedBlackTree.Tree)zipper.head()).right();
            if (next2 == null) {
                return zipper;
            }
            zipper = RedBlackTree$NList$.MODULE$.cons(next2, zipper);
        }
    }

    private final Tuple4 unzipBoth$1(RedBlackTree.Tree left, RedBlackTree.Tree right, RedBlackTree.NList leftZipper, RedBlackTree.NList rightZipper, int smallerDepth) {
        block10: {
            Tuple4<Object, Boolean, Boolean, Integer> tuple4;
            block8: {
                block9: {
                    block7: {
                        while (true) {
                            if (this.scala$collection$immutable$RedBlackTree$$isBlackTree(left) && this.scala$collection$immutable$RedBlackTree$$isBlackTree(right)) {
                                ++smallerDepth;
                                rightZipper = RedBlackTree$NList$.MODULE$.cons(right, rightZipper);
                                leftZipper = RedBlackTree$NList$.MODULE$.cons(left, leftZipper);
                                right = right.left();
                                left = left.right();
                                continue;
                            }
                            if (this.isRedTree(left) && this.isRedTree(right)) {
                                rightZipper = RedBlackTree$NList$.MODULE$.cons(right, rightZipper);
                                leftZipper = RedBlackTree$NList$.MODULE$.cons(left, leftZipper);
                                right = right.left();
                                left = left.right();
                                continue;
                            }
                            if (this.isRedTree(right)) {
                                rightZipper = RedBlackTree$NList$.MODULE$.cons(right, rightZipper);
                                right = right.left();
                                continue;
                            }
                            if (!this.isRedTree(left)) break;
                            leftZipper = RedBlackTree$NList$.MODULE$.cons(left, leftZipper);
                            left = left.right();
                        }
                        if (left != null || right != null) break block7;
                        tuple4 = new Tuple4<Object, Boolean, Boolean, Integer>(null, BoxesRunTime.boxToBoolean(true), BoxesRunTime.boxToBoolean(false), BoxesRunTime.boxToInteger(smallerDepth));
                        break block8;
                    }
                    if (left != null || !this.scala$collection$immutable$RedBlackTree$$isBlackTree(right)) break block9;
                    tuple4 = new Tuple4<RedBlackTree.NList, Boolean, Boolean, Integer>(this.unzip$1(RedBlackTree$NList$.MODULE$.cons(right, rightZipper), true), BoxesRunTime.boxToBoolean(false), BoxesRunTime.boxToBoolean(true), BoxesRunTime.boxToInteger(smallerDepth));
                    break block8;
                }
                if (!this.scala$collection$immutable$RedBlackTree$$isBlackTree(left) || right != null) break block10;
                tuple4 = new Tuple4<RedBlackTree.NList, Boolean, Boolean, Integer>(this.unzip$1(RedBlackTree$NList$.MODULE$.cons(left, leftZipper), false), BoxesRunTime.boxToBoolean(false), BoxesRunTime.boxToBoolean(false), BoxesRunTime.boxToInteger(smallerDepth));
            }
            return tuple4;
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"unmatched trees in unzip: ").append(left).append((Object)", ").append(right).toString());
    }

    private final RedBlackTree.NList findDepth$1(RedBlackTree.NList zipper, int depth) {
        while (true) {
            if (zipper == null) {
                throw package$.MODULE$.error("Defect: unexpected empty zipper while computing range");
            }
            if (this.scala$collection$immutable$RedBlackTree$$isBlackTree((RedBlackTree.Tree)zipper.head())) {
                if (depth == 1) {
                    return zipper;
                }
                --depth;
                zipper = zipper.tail();
                continue;
            }
            zipper = zipper.tail();
        }
    }

    private RedBlackTree$() {
        MODULE$ = this;
    }
}

