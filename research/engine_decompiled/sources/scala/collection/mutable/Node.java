/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Product$class;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.mutable.AVLIterator;
import scala.collection.mutable.AVLTree;
import scala.collection.mutable.AVLTree$class;
import scala.collection.mutable.Leaf$;
import scala.collection.mutable.Node$;
import scala.math.Ordering;
import scala.math.package$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\tub\u0001B\u0001\u0003\t&\u0011AAT8eK*\u00111\u0001B\u0001\b[V$\u0018M\u00197f\u0015\t)a!\u0001\u0006d_2dWm\u0019;j_:T\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001+\tQQcE\u0003\u0001\u0017=q\u0012\u0005\u0005\u0002\r\u001b5\ta!\u0003\u0002\u000f\r\t1\u0011I\\=SK\u001a\u00042\u0001E\t\u0014\u001b\u0005\u0011\u0011B\u0001\n\u0003\u0005\u001d\te\u000b\u0014+sK\u0016\u0004\"\u0001F\u000b\r\u0001\u0011)a\u0003\u0001b\u0001/\t\t\u0011)\u0005\u0002\u00197A\u0011A\"G\u0005\u00035\u0019\u0011qAT8uQ&tw\r\u0005\u0002\r9%\u0011QD\u0002\u0002\u0004\u0003:L\bC\u0001\u0007 \u0013\t\u0001cAA\u0004Qe>$Wo\u0019;\u0011\u00051\u0011\u0013BA\u0012\u0007\u00051\u0019VM]5bY&T\u0018M\u00197f\u0011!)\u0003A!f\u0001\n\u00031\u0013\u0001\u00023bi\u0006,\u0012a\u0005\u0005\tQ\u0001\u0011\t\u0012)A\u0005'\u0005)A-\u0019;bA!A!\u0006\u0001BK\u0002\u0013\u00051&\u0001\u0003mK\u001a$X#A\b\t\u00115\u0002!\u0011#Q\u0001\n=\tQ\u0001\\3gi\u0002B\u0001b\f\u0001\u0003\u0016\u0004%\taK\u0001\u0006e&<\u0007\u000e\u001e\u0005\tc\u0001\u0011\t\u0012)A\u0005\u001f\u00051!/[4ii\u0002BQa\r\u0001\u0005\u0002Q\na\u0001P5oSRtD\u0003B\u001b7oa\u00022\u0001\u0005\u0001\u0014\u0011\u0015)#\u00071\u0001\u0014\u0011\u0015Q#\u00071\u0001\u0010\u0011\u0015y#\u00071\u0001\u0010\u0011\u001dQ\u0004A1A\u0005Bm\nqAY1mC:\u001cW-F\u0001=!\taQ(\u0003\u0002?\r\t\u0019\u0011J\u001c;\t\r\u0001\u0003\u0001\u0015!\u0003=\u0003!\u0011\u0017\r\\1oG\u0016\u0004\u0003b\u0002\"\u0001\u0005\u0004%\teO\u0001\u0006I\u0016\u0004H\u000f\u001b\u0005\u0007\t\u0002\u0001\u000b\u0011\u0002\u001f\u0002\r\u0011,\u0007\u000f\u001e5!\u0011\u00151\u0005\u0001\"\u0011H\u0003!IG/\u001a:bi>\u0014XC\u0001%O+\u0005I\u0005c\u0001&L\u001b6\tA!\u0003\u0002M\t\tA\u0011\n^3sCR|'\u000f\u0005\u0002\u0015\u001d\u0012)q*\u0012b\u0001!\n\t!)\u0005\u0002\u00147!)!\u000b\u0001C!'\u0006A1m\u001c8uC&t7/\u0006\u0002U7R\u0019Q\u000b\u0017/\u0011\u000511\u0016BA,\u0007\u0005\u001d\u0011un\u001c7fC:DQ!W)A\u0002i\u000bQA^1mk\u0016\u0004\"\u0001F.\u0005\u000b=\u000b&\u0019\u0001)\t\u000bu\u000b\u0006\u0019\u00010\u0002\u0011=\u0014H-\u001a:j]\u001e\u00042a\u00182[\u001d\ta\u0001-\u0003\u0002b\r\u00059\u0001/Y2lC\u001e,\u0017BA2e\u0005!y%\u000fZ3sS:<'BA1\u0007\u0011\u00151\u0007\u0001\"\u0011h\u0003\u0019Ign]3siV\u0011\u0001n\u001b\u000b\u0004S2l\u0007c\u0001\t\u0012UB\u0011Ac\u001b\u0003\u0006\u001f\u0016\u0014\r\u0001\u0015\u0005\u00063\u0016\u0004\rA\u001b\u0005\u0006;\u0016\u0004\rA\u001c\t\u0004?\nT\u0007\"\u00029\u0001\t\u0003\n\u0018A\u0002:f[>4X-\u0006\u0002skR\u0019qb\u001d<\t\u000be{\u0007\u0019\u0001;\u0011\u0005Q)H!B(p\u0005\u0004\u0001\u0006\"B/p\u0001\u00049\bcA0ci\")\u0011\u0010\u0001C!u\u0006I!/Z7pm\u0016l\u0015N\\\u000b\u0004w\u0006\u0005Q#\u0001?\u0011\u000b1ix0a\u0001\n\u0005y4!A\u0002+va2,'\u0007E\u0002\u0015\u0003\u0003!Qa\u0014=C\u0002A\u00032\u0001E\t\u0000\u0011\u001d\t9\u0001\u0001C!\u0003\u0013\t\u0011B]3n_Z,W*\u0019=\u0016\t\u0005-\u0011\u0011C\u000b\u0003\u0003\u001b\u0001b\u0001D?\u0002\u0010\u0005M\u0001c\u0001\u000b\u0002\u0012\u00111q*!\u0002C\u0002A\u0003B\u0001E\t\u0002\u0010!9\u0011q\u0003\u0001\u0005B\u0005e\u0011!\u0003:fE\u0006d\u0017M\\2f+\u0011\tY\"!\t\u0016\u0005\u0005u\u0001\u0003\u0002\t\u0012\u0003?\u00012\u0001FA\u0011\t\u0019y\u0015Q\u0003b\u0001!\"9\u0011Q\u0005\u0001\u0005B\u0005\u001d\u0012\u0001\u00047fMR\u0014v\u000e^1uS>tW\u0003BA\u0015\u0003_)\"!a\u000b\u0011\tA\u0001\u0011Q\u0006\t\u0004)\u0005=BAB(\u0002$\t\u0007\u0001\u000bC\u0004\u00024\u0001!\t%!\u000e\u0002\u001bILw\r\u001b;S_R\fG/[8o+\u0011\t9$!\u0010\u0016\u0005\u0005e\u0002\u0003\u0002\t\u0001\u0003w\u00012\u0001FA\u001f\t\u0019y\u0015\u0011\u0007b\u0001!\"9\u0011\u0011\t\u0001\u0005B\u0005\r\u0013A\u00053pk\ndW\rT3giJ{G/\u0019;j_:,B!!\u0012\u0002LU\u0011\u0011q\t\t\u0005!\u0001\tI\u0005E\u0002\u0015\u0003\u0017\"aaTA \u0005\u0004\u0001\u0006bBA(\u0001\u0011\u0005\u0013\u0011K\u0001\u0014I>,(\r\\3SS\u001eDGOU8uCRLwN\\\u000b\u0005\u0003'\nI&\u0006\u0002\u0002VA!\u0001\u0003AA,!\r!\u0012\u0011\f\u0003\u0007\u001f\u00065#\u0019\u0001)\t\u0013\u0005u\u0003!!A\u0005\u0002\u0005}\u0013\u0001B2paf,B!!\u0019\u0002hQA\u00111MA5\u0003W\ny\u0007\u0005\u0003\u0011\u0001\u0005\u0015\u0004c\u0001\u000b\u0002h\u00111a#a\u0017C\u0002]A\u0011\"JA.!\u0003\u0005\r!!\u001a\t\u0013)\nY\u0006%AA\u0002\u00055\u0004\u0003\u0002\t\u0012\u0003KB\u0011bLA.!\u0003\u0005\r!!\u001c\t\u0013\u0005M\u0004!%A\u0005\u0002\u0005U\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0005\u0003o\ni)\u0006\u0002\u0002z)\u001a1#a\u001f,\u0005\u0005u\u0004\u0003BA@\u0003\u0013k!!!!\u000b\t\u0005\r\u0015QQ\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\"\u0007\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u0017\u000b\tIA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$aAFA9\u0005\u00049\u0002\"CAI\u0001E\u0005I\u0011AAJ\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*B!!&\u0002\u001aV\u0011\u0011q\u0013\u0016\u0004\u001f\u0005mDA\u0002\f\u0002\u0010\n\u0007q\u0003C\u0005\u0002\u001e\u0002\t\n\u0011\"\u0001\u0002 \u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aT\u0003BAK\u0003C#aAFAN\u0005\u00049\u0002\"CAS\u0001\u0005\u0005I\u0011IAT\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011\u0011\u0016\t\u0005\u0003W\u000b),\u0004\u0002\u0002.*!\u0011qVAY\u0003\u0011a\u0017M\\4\u000b\u0005\u0005M\u0016\u0001\u00026bm\u0006LA!a.\u0002.\n11\u000b\u001e:j]\u001eD\u0001\"a/\u0001\u0003\u0003%\taO\u0001\raJ|G-^2u\u0003JLG/\u001f\u0005\n\u0003\u007f\u0003\u0011\u0011!C\u0001\u0003\u0003\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002\u001c\u0003\u0007D\u0011\"!2\u0002>\u0006\u0005\t\u0019\u0001\u001f\u0002\u0007a$\u0013\u0007C\u0005\u0002J\u0002\t\t\u0011\"\u0011\u0002L\u0006y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002NB\u0019!jS\u000e\t\u0013\u0005E\u0007!!A\u0005\u0002\u0005M\u0017\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007U\u000b)\u000eC\u0005\u0002F\u0006=\u0017\u0011!a\u00017!I\u0011\u0011\u001c\u0001\u0002\u0002\u0013\u0005\u00131\\\u0001\tQ\u0006\u001c\bnQ8eKR\tA\bC\u0005\u0002`\u0002\t\t\u0011\"\u0011\u0002b\u0006AAo\\*ue&tw\r\u0006\u0002\u0002*\"I\u0011Q\u001d\u0001\u0002\u0002\u0013\u0005\u0013q]\u0001\u0007KF,\u0018\r\\:\u0015\u0007U\u000bI\u000fC\u0005\u0002F\u0006\r\u0018\u0011!a\u00017\u001dI\u0011Q\u001e\u0002\u0002\u0002#%\u0011q^\u0001\u0005\u001d>$W\rE\u0002\u0011\u0003c4\u0001\"\u0001\u0002\u0002\u0002#%\u00111_\n\u0005\u0003c\\\u0011\u0005C\u00044\u0003c$\t!a>\u0015\u0005\u0005=\bBCAp\u0003c\f\t\u0011\"\u0012\u0002b\"Q\u0011Q`Ay\u0003\u0003%\t)a@\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\t\u0005!q\u0001\u000b\t\u0005\u0007\u0011IAa\u0003\u0003\u0010A!\u0001\u0003\u0001B\u0003!\r!\"q\u0001\u0003\u0007-\u0005m(\u0019A\f\t\u000f\u0015\nY\u00101\u0001\u0003\u0006!9!&a?A\u0002\t5\u0001\u0003\u0002\t\u0012\u0005\u000bAqaLA~\u0001\u0004\u0011i\u0001\u0003\u0006\u0003\u0014\u0005E\u0018\u0011!CA\u0005+\tq!\u001e8baBd\u00170\u0006\u0003\u0003\u0018\t\u001dB\u0003\u0002B\r\u0005W\u0001R\u0001\u0004B\u000e\u0005?I1A!\b\u0007\u0005\u0019y\u0005\u000f^5p]BIAB!\t\u0003&\t%\"\u0011F\u0005\u0004\u0005G1!A\u0002+va2,7\u0007E\u0002\u0015\u0005O!aA\u0006B\t\u0005\u00049\u0002\u0003\u0002\t\u0012\u0005KA!B!\f\u0003\u0012\u0005\u0005\t\u0019\u0001B\u0018\u0003\rAH\u0005\r\t\u0005!\u0001\u0011)\u0003\u0003\u0006\u00034\u0005E\u0018\u0011!C\u0005\u0005k\t1B]3bIJ+7o\u001c7wKR\u0011!q\u0007\t\u0005\u0003W\u0013I$\u0003\u0003\u0003<\u00055&AB(cU\u0016\u001cG\u000f")
public class Node<A>
implements AVLTree<A>,
Product {
    private final A data;
    private final AVLTree<A> left;
    private final AVLTree<A> right;
    private final int balance;
    private final int depth;

    public static <A> Option<Tuple3<A, AVLTree<A>, AVLTree<A>>> unapply(Node<A> node) {
        return Node$.MODULE$.unapply(node);
    }

    public static <A> Node<A> apply(A a, AVLTree<A> aVLTree, AVLTree<A> aVLTree2) {
        return Node$.MODULE$.apply(a, aVLTree, aVLTree2);
    }

    public A data() {
        return this.data;
    }

    public AVLTree<A> left() {
        return this.left;
    }

    public AVLTree<A> right() {
        return this.right;
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
    public <B> Iterator<B> iterator() {
        return new AVLIterator(this);
    }

    @Override
    public <B> boolean contains(B value, Ordering<B> ordering) {
        int ord = ordering.compare(value, this.data());
        return 0 == ord ? true : (ord < 0 ? this.left().contains(value, ordering) : this.right().contains(value, ordering));
    }

    @Override
    public <B> AVLTree<B> insert(B value, Ordering<B> ordering) {
        int ord = ordering.compare(value, this.data());
        if (0 == ord) {
            throw new IllegalArgumentException();
        }
        return ord < 0 ? new Node<A>(this.data(), this.left().insert(value, ordering), this.right()).rebalance() : new Node<A>(this.data(), this.left(), this.right().insert(value, ordering)).rebalance();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public <B> AVLTree<A> remove(B value, Ordering<B> ordering) {
        AVLTree<Nothing$> aVLTree;
        int ord = ordering.compare(value, this.data());
        if (ord == 0) {
            if (Leaf$.MODULE$.equals(this.left())) {
                if (Leaf$.MODULE$.equals(this.right())) {
                    aVLTree = Leaf$.MODULE$;
                    return aVLTree;
                } else {
                    Tuple2 tuple2 = this.right().removeMin();
                    if (tuple2 == null) throw new MatchError(tuple2);
                    Tuple2 tuple22 = new Tuple2(tuple2._1(), tuple2._2());
                    Object min2 = tuple22._1();
                    AVLTree newRight = tuple22._2();
                    aVLTree = new Node(min2, this.left(), newRight).rebalance();
                }
                return aVLTree;
            } else {
                Tuple2 tuple2 = this.left().removeMax();
                if (tuple2 == null) throw new MatchError(tuple2);
                Tuple2 tuple23 = new Tuple2(tuple2._1(), tuple2._2());
                Object max2 = tuple23._1();
                AVLTree newLeft = tuple23._2();
                aVLTree = new Node(max2, newLeft, this.right()).rebalance();
            }
            return aVLTree;
        } else {
            aVLTree = ord < 0 ? new Node<A>(this.data(), this.left().remove(value, ordering), this.right()).rebalance() : new Node<A>(this.data(), this.left(), this.right().remove(value, ordering)).rebalance();
        }
        return aVLTree;
    }

    @Override
    public <B> Tuple2<B, AVLTree<B>> removeMin() {
        Tuple2 tuple2;
        block4: {
            Tuple2 tuple22;
            block3: {
                block2: {
                    if (!Leaf$.MODULE$.equals(this.left())) break block2;
                    tuple22 = new Tuple2(this.data(), this.right());
                    break block3;
                }
                tuple2 = this.left().removeMin();
                if (tuple2 == null) break block4;
                Tuple2 tuple23 = new Tuple2(tuple2._1(), tuple2._2());
                Object min2 = tuple23._1();
                AVLTree newLeft = tuple23._2();
                tuple22 = new Tuple2(min2, new Node<A>(this.data(), newLeft, this.right()).rebalance());
            }
            return tuple22;
        }
        throw new MatchError(tuple2);
    }

    @Override
    public <B> Tuple2<B, AVLTree<B>> removeMax() {
        Tuple2 tuple2;
        block4: {
            Tuple2 tuple22;
            block3: {
                block2: {
                    if (!Leaf$.MODULE$.equals(this.right())) break block2;
                    tuple22 = new Tuple2(this.data(), this.left());
                    break block3;
                }
                tuple2 = this.right().removeMax();
                if (tuple2 == null) break block4;
                Tuple2 tuple23 = new Tuple2(tuple2._1(), tuple2._2());
                Object max2 = tuple23._1();
                AVLTree newRight = tuple23._2();
                tuple22 = new Tuple2(max2, new Node<A>(this.data(), this.left(), newRight).rebalance());
            }
            return tuple22;
        }
        throw new MatchError(tuple2);
    }

    @Override
    public <B> AVLTree<B> rebalance() {
        return -2 == this.balance() ? (1 == this.left().balance() ? this.doubleRightRotation() : this.rightRotation()) : (2 == this.balance() ? (-1 == this.right().balance() ? this.doubleLeftRotation() : this.leftRotation()) : this);
    }

    @Override
    public <B> Node<B> leftRotation() {
        if (Leaf$.MODULE$.equals(this.right())) {
            throw scala.sys.package$.MODULE$.error("Should not happen.");
        }
        Node r = (Node)this.right();
        return new Node<A>(r.data(), new Node<A>(this.data(), this.left(), r.left()), r.right());
    }

    @Override
    public <B> Node<B> rightRotation() {
        if (Leaf$.MODULE$.equals(this.left())) {
            throw scala.sys.package$.MODULE$.error("Should not happen.");
        }
        Node l = (Node)this.left();
        return new Node<A>(l.data(), l.left(), new Node<A>(this.data(), l.right(), this.right()));
    }

    @Override
    public <B> Node<B> doubleLeftRotation() {
        if (Leaf$.MODULE$.equals(this.right())) {
            throw scala.sys.package$.MODULE$.error("Should not happen.");
        }
        Node r = (Node)this.right();
        Node<B> rightRotated = r.rightRotation();
        return new Node<B>(rightRotated.data(), new Node<A>(this.data(), this.left(), rightRotated.left()), rightRotated.right());
    }

    @Override
    public <B> Node<B> doubleRightRotation() {
        if (Leaf$.MODULE$.equals(this.left())) {
            throw scala.sys.package$.MODULE$.error("Should not happen.");
        }
        Node l = (Node)this.left();
        Node<B> leftRotated = l.leftRotation();
        return new Node<B>(leftRotated.data(), leftRotated.left(), new Node<A>(this.data(), leftRotated.right(), this.right()));
    }

    public <A> Node<A> copy(A data, AVLTree<A> left, AVLTree<A> right) {
        return new Node<A>(data, left, right);
    }

    public <A> A copy$default$1() {
        return this.data();
    }

    public <A> AVLTree<A> copy$default$2() {
        return this.left();
    }

    public <A> AVLTree<A> copy$default$3() {
        return this.right();
    }

    @Override
    public String productPrefix() {
        return "Node";
    }

    @Override
    public int productArity() {
        return 3;
    }

    @Override
    public Object productElement(int x$1) {
        AVLTree<A> aVLTree;
        switch (x$1) {
            default: {
                throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
            }
            case 2: {
                aVLTree = this.right();
                break;
            }
            case 1: {
                aVLTree = this.left();
                break;
            }
            case 0: {
                aVLTree = this.data();
            }
        }
        return aVLTree;
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof Node;
    }

    public int hashCode() {
        return ScalaRunTime$.MODULE$._hashCode(this);
    }

    public String toString() {
        return ScalaRunTime$.MODULE$._toString(this);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean equals(Object x$1) {
        boolean bl;
        if (this == x$1) return true;
        if (!(x$1 instanceof Node)) return false;
        boolean bl2 = true;
        if (!bl2) return false;
        Node node = (Node)x$1;
        A a = node.data();
        A a2 = this.data();
        if (a2 == a) {
            bl = true;
        } else {
            if (a2 == null) {
                return false;
            }
            bl = a2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)a2, a) : (a2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)a2, a) : a2.equals(a));
        }
        if (!bl) return false;
        AVLTree<A> aVLTree = this.left();
        AVLTree<A> aVLTree2 = node.left();
        if (aVLTree == null) {
            if (aVLTree2 != null) {
                return false;
            }
        } else if (!aVLTree.equals(aVLTree2)) return false;
        AVLTree<A> aVLTree3 = this.right();
        AVLTree<A> aVLTree4 = node.right();
        if (aVLTree3 == null) {
            if (aVLTree4 != null) {
                return false;
            }
        } else if (!aVLTree3.equals(aVLTree4)) return false;
        if (!node.canEqual(this)) return false;
        return true;
    }

    public Node(A data, AVLTree<A> left, AVLTree<A> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        AVLTree$class.$init$(this);
        Product$class.$init$(this);
        this.balance = right.depth() - left.depth();
        this.depth = package$.MODULE$.max(left.depth(), right.depth()) + 1;
    }
}

