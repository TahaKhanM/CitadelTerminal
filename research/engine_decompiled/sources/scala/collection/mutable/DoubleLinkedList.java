/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.collection.GenSeq;
import scala.collection.GenTraversable;
import scala.collection.Iterator;
import scala.collection.LinearSeq;
import scala.collection.LinearSeq$class;
import scala.collection.LinearSeqLike;
import scala.collection.LinearSeqLike$class;
import scala.collection.Seq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.GenericCompanion;
import scala.collection.mutable.AbstractSeq;
import scala.collection.mutable.Builder;
import scala.collection.mutable.DoubleLinkedList$;
import scala.collection.mutable.DoubleLinkedListLike;
import scala.collection.mutable.DoubleLinkedListLike$class;
import scala.collection.mutable.LinkedListLike$class;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001u4A!\u0001\u0002\u0001\u0013\t\u0001Bi\\;cY\u0016d\u0015N\\6fI2K7\u000f\u001e\u0006\u0003\u0007\u0011\tq!\\;uC\ndWM\u0003\u0002\u0006\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001!\u0006\u0002\u000b#M1\u0001aC\u000e\u001fK%\u00022\u0001D\u0007\u0010\u001b\u0005\u0011\u0011B\u0001\b\u0003\u0005-\t%m\u001d;sC\u000e$8+Z9\u0011\u0005A\tB\u0002\u0001\u0003\u0006%\u0001\u0011\ra\u0005\u0002\u0002\u0003F\u0011A\u0003\u0007\t\u0003+Yi\u0011AB\u0005\u0003/\u0019\u0011qAT8uQ&tw\r\u0005\u0002\u00163%\u0011!D\u0002\u0002\u0004\u0003:L\bc\u0001\u0007\u001d\u001f%\u0011QD\u0001\u0002\n\u0019&tW-\u0019:TKF\u0004Ba\b\u0012\u0010I5\t\u0001E\u0003\u0002\"\t\u00059q-\u001a8fe&\u001c\u0017BA\u0012!\u0005i9UM\\3sS\u000e$&/\u0019<feN\f'\r\\3UK6\u0004H.\u0019;f!\ta\u0001\u0001\u0005\u0003\rM=A\u0013BA\u0014\u0003\u0005Q!u.\u001e2mK2Kgn[3e\u0019&\u001cH\u000fT5lKB\u0019A\u0002A\b\u0011\u0005UQ\u0013BA\u0016\u0007\u00051\u0019VM]5bY&T\u0018M\u00197f\u0011\u0015i\u0003\u0001\"\u0001/\u0003\u0019a\u0014N\\5u}Q\t\u0001\u0006C\u0003.\u0001\u0011\u0005\u0001\u0007F\u0002)cMBQAM\u0018A\u0002=\tA!\u001a7f[\")Ag\fa\u0001Q\u0005!a.\u001a=u\u0011\u00151\u0004\u0001\"\u00118\u0003%\u0019w.\u001c9b]&|g.F\u00019!\ry\u0012\bJ\u0005\u0003u\u0001\u0012\u0001cR3oKJL7mQ8na\u0006t\u0017n\u001c8\t\u000bq\u0002A\u0011\t\u0018\u0002\u000b\rdwN\\3)\t\u0001q\u0014I\u0011\t\u0003+}J!\u0001\u0011\u0004\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g\u0004\u0003HxZ-Va)p))\t\u0001!u)\u0013\t\u0003+\u0015K!A\u0012\u0004\u0003\u0015\u0011,\u0007O]3dCR,G-I\u0001I\u0003\u0005dun^\u0017mKZ,G\u000e\t7j].,G\r\t7jgR\u001c\b%\u0019:fA\u0011,\u0007O]3dCR,G\r\t3vK\u0002\"x\u000eI5eS>\u001c\u0018P\\2sCNLWm\u001d\u0011j]\u0002Jg\u000e^3sM\u0006\u001cW\rI1oI\u0002JgnY8na2,G/\u001a\u0011gK\u0006$XO]3t]\u0005\n!*\u0001\u00043]E\nd\u0006M\u0004\u0006\u0019\nA\t!T\u0001\u0011\t>,(\r\\3MS:\\W\r\u001a'jgR\u0004\"\u0001\u0004(\u0007\u000b\u0005\u0011\u0001\u0012A(\u0014\u00079\u0003\u0016\u0006E\u0002 #\u0012J!A\u0015\u0011\u0003\u0015M+\u0017OR1di>\u0014\u0018\u0010C\u0003.\u001d\u0012\u0005A\u000bF\u0001N\u0011\u00151f\nb\u0001X\u00031\u0019\u0017M\u001c\"vS2$gI]8n+\tA\u0016-F\u0001Z!\u0015y\"\f\u00181c\u0013\tY\u0006E\u0001\u0007DC:\u0014U/\u001b7e\rJ|W\u000e\u0005\u0002^=6\ta*\u0003\u0002`s\t!1i\u001c7m!\t\u0001\u0012\rB\u0003\u0013+\n\u00071\u0003E\u0002\r\u0001\u0001DQ\u0001\u001a(\u0005\u0002\u0015\f!B\\3x\u0005VLG\u000eZ3s+\t17.F\u0001h!\u0011a\u0001N\u001b7\n\u0005%\u0014!a\u0002\"vS2$WM\u001d\t\u0003!-$QAE2C\u0002M\u00012\u0001\u0004\u0001k\u0011\u001dqg*!A\u0005\n=\f1B]3bIJ+7o\u001c7wKR\t\u0001\u000f\u0005\u0002rm6\t!O\u0003\u0002ti\u0006!A.\u00198h\u0015\u0005)\u0018\u0001\u00026bm\u0006L!a\u001e:\u0003\r=\u0013'.Z2uQ\u0011qE)_%\"\u0003i\fa\u0005T8x[1,g/\u001a7!Y&t7.\u001a3!Y&\u001cHo\u001d\u0011be\u0016\u0004C-\u001a9sK\u000e\fG/\u001a3/Q\u0011qEiR%)\t-#\u00150\u0013")
public class DoubleLinkedList<A>
extends AbstractSeq<A>
implements scala.collection.mutable.LinearSeq<A>,
DoubleLinkedListLike<A, DoubleLinkedList<A>>,
Serializable {
    public static final long serialVersionUID = -8144992287952814767L;
    private scala.collection.mutable.Seq prev;
    private Object elem;
    private scala.collection.mutable.Seq next;

    public static <A> CanBuildFrom<DoubleLinkedList<?>, A, DoubleLinkedList<A>> canBuildFrom() {
        return DoubleLinkedList$.MODULE$.canBuildFrom();
    }

    public static Some unapplySeq(Seq seq) {
        return DoubleLinkedList$.MODULE$.unapplySeq(seq);
    }

    public static GenTraversable iterate(Object object, int n, Function1 function1) {
        return DoubleLinkedList$.MODULE$.iterate(object, n, function1);
    }

    public static GenTraversable range(Object object, Object object2, Object object3, Integral integral) {
        return DoubleLinkedList$.MODULE$.range(object, object2, object3, integral);
    }

    public static GenTraversable range(Object object, Object object2, Integral integral) {
        return DoubleLinkedList$.MODULE$.range(object, object2, integral);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, int n5, Function5 function5) {
        return DoubleLinkedList$.MODULE$.tabulate(n, n2, n3, n4, n5, function5);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, Function4 function4) {
        return DoubleLinkedList$.MODULE$.tabulate(n, n2, n3, n4, function4);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, Function3 function3) {
        return DoubleLinkedList$.MODULE$.tabulate(n, n2, n3, function3);
    }

    public static GenTraversable tabulate(int n, int n2, Function2 function2) {
        return DoubleLinkedList$.MODULE$.tabulate(n, n2, function2);
    }

    public static GenTraversable tabulate(int n, Function1 function1) {
        return DoubleLinkedList$.MODULE$.tabulate(n, function1);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, int n5, Function0 function0) {
        return DoubleLinkedList$.MODULE$.fill(n, n2, n3, n4, n5, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, Function0 function0) {
        return DoubleLinkedList$.MODULE$.fill(n, n2, n3, n4, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, Function0 function0) {
        return DoubleLinkedList$.MODULE$.fill(n, n2, n3, function0);
    }

    public static GenTraversable fill(int n, int n2, Function0 function0) {
        return DoubleLinkedList$.MODULE$.fill(n, n2, function0);
    }

    public static GenTraversable fill(int n, Function0 function0) {
        return DoubleLinkedList$.MODULE$.fill(n, function0);
    }

    public static GenTraversable concat(Seq seq) {
        return DoubleLinkedList$.MODULE$.concat(seq);
    }

    public static GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return DoubleLinkedList$.MODULE$.ReusableCBF();
    }

    public static GenTraversable empty() {
        return DoubleLinkedList$.MODULE$.empty();
    }

    @Override
    public scala.collection.mutable.Seq prev() {
        return this.prev;
    }

    @Override
    public void prev_$eq(scala.collection.mutable.Seq x$1) {
        this.prev = x$1;
    }

    @Override
    public /* synthetic */ void scala$collection$mutable$DoubleLinkedListLike$$super$insert(scala.collection.mutable.Seq that) {
        LinkedListLike$class.insert(this, that);
    }

    @Override
    public scala.collection.mutable.Seq append(scala.collection.mutable.Seq that) {
        return DoubleLinkedListLike$class.append(this, that);
    }

    @Override
    public void insert(scala.collection.mutable.Seq that) {
        DoubleLinkedListLike$class.insert(this, that);
    }

    @Override
    public void remove() {
        DoubleLinkedListLike$class.remove(this);
    }

    @Override
    public scala.collection.mutable.Seq drop(int n) {
        return DoubleLinkedListLike$class.drop(this, n);
    }

    @Override
    public scala.collection.mutable.Seq tail() {
        return DoubleLinkedListLike$class.tail(this);
    }

    @Override
    public A apply(int n) {
        return (A)DoubleLinkedListLike$class.apply(this, n);
    }

    @Override
    public void update(int n, A x) {
        DoubleLinkedListLike$class.update(this, n, x);
    }

    @Override
    public Option<A> get(int n) {
        return DoubleLinkedListLike$class.get(this, n);
    }

    @Override
    public A elem() {
        return (A)this.elem;
    }

    @Override
    public void elem_$eq(A x$1) {
        this.elem = x$1;
    }

    @Override
    public scala.collection.mutable.Seq next() {
        return this.next;
    }

    @Override
    public void next_$eq(scala.collection.mutable.Seq x$1) {
        this.next = x$1;
    }

    @Override
    public boolean isEmpty() {
        return LinkedListLike$class.isEmpty(this);
    }

    @Override
    public int length() {
        return LinkedListLike$class.length(this);
    }

    @Override
    public A head() {
        return (A)LinkedListLike$class.head(this);
    }

    @Override
    public Iterator<A> iterator() {
        return LinkedListLike$class.iterator(this);
    }

    @Override
    public <U> void foreach(Function1<A, U> f) {
        LinkedListLike$class.foreach(this, f);
    }

    @Override
    public scala.collection.mutable.LinearSeq<A> seq() {
        return scala.collection.mutable.LinearSeq$class.seq(this);
    }

    @Override
    public LinearSeq<A> thisCollection() {
        return LinearSeqLike$class.thisCollection(this);
    }

    @Override
    public LinearSeq toCollection(LinearSeqLike repr) {
        return LinearSeqLike$class.toCollection(this, repr);
    }

    @Override
    public int hashCode() {
        return LinearSeqLike$class.hashCode(this);
    }

    @Override
    public final <B> boolean corresponds(GenSeq<B> that, Function2<A, B, Object> p) {
        return LinearSeqLike$class.corresponds(this, that, p);
    }

    @Override
    public GenericCompanion<DoubleLinkedList> companion() {
        return DoubleLinkedList$.MODULE$;
    }

    @Override
    public DoubleLinkedList<A> clone() {
        Builder builder = this.newBuilder();
        builder.$plus$plus$eq(this);
        return (DoubleLinkedList)builder.result();
    }

    public DoubleLinkedList() {
        LinearSeqLike$class.$init$(this);
        LinearSeq$class.$init$(this);
        scala.collection.mutable.LinearSeq$class.$init$(this);
        LinkedListLike$class.$init$(this);
        DoubleLinkedListLike$class.$init$(this);
        this.next_$eq(this);
    }

    public DoubleLinkedList(A elem, DoubleLinkedList<A> next2) {
        this();
        if (next2 != null) {
            this.elem_$eq(elem);
            this.next_$eq(next2);
            ((DoubleLinkedListLike)((Object)this.next())).prev_$eq(this);
        }
    }
}

