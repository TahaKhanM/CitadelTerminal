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
import scala.collection.mutable.LinkedList$;
import scala.collection.mutable.LinkedListLike;
import scala.collection.mutable.LinkedListLike$class;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u0005\u0015a\u0001B\u0001\u0003\u0001%\u0011!\u0002T5oW\u0016$G*[:u\u0015\t\u0019A!A\u0004nkR\f'\r\\3\u000b\u0005\u00151\u0011AC2pY2,7\r^5p]*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0005)\t2C\u0002\u0001\f7y)\u0013\u0006E\u0002\r\u001b=i\u0011AA\u0005\u0003\u001d\t\u00111\"\u00112tiJ\f7\r^*fcB\u0011\u0001#\u0005\u0007\u0001\t\u0015\u0011\u0002A1\u0001\u0014\u0005\u0005\t\u0015C\u0001\u000b\u0019!\t)b#D\u0001\u0007\u0013\t9bAA\u0004O_RD\u0017N\\4\u0011\u0005UI\u0012B\u0001\u000e\u0007\u0005\r\te.\u001f\t\u0004\u0019qy\u0011BA\u000f\u0003\u0005%a\u0015N\\3beN+\u0017\u000f\u0005\u0003 E=!S\"\u0001\u0011\u000b\u0005\u0005\"\u0011aB4f]\u0016\u0014\u0018nY\u0005\u0003G\u0001\u0012!dR3oKJL7\r\u0016:bm\u0016\u00148/\u00192mKR+W\u000e\u001d7bi\u0016\u0004\"\u0001\u0004\u0001\u0011\t11s\u0002K\u0005\u0003O\t\u0011a\u0002T5oW\u0016$G*[:u\u0019&\\W\rE\u0002\r\u0001=\u0001\"!\u0006\u0016\n\u0005-2!\u0001D*fe&\fG.\u001b>bE2,\u0007\"B\u0017\u0001\t\u0003q\u0013A\u0002\u001fj]&$h\bF\u0001)\u0011\u0015i\u0003\u0001\"\u00011)\rA\u0013g\r\u0005\u0006e=\u0002\raD\u0001\u0005K2,W\u000eC\u00035_\u0001\u0007\u0001&\u0001\u0003oKb$\b\"\u0002\u001c\u0001\t\u0003:\u0014!C2p[B\fg.[8o+\u0005A\u0004cA\u0010:I%\u0011!\b\t\u0002\u0011\u000f\u0016tWM]5d\u0007>l\u0007/\u00198j_:DC\u0001\u0001\u001f@\u0003B\u0011Q#P\u0005\u0003}\u0019\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3eC\u0005\u0001\u0015!\u0019'po6bWM^3mA1Lgn[3eA1L7\u000f^:!CJ,\u0007\u0005Z3qe\u0016\u001c\u0017\r^3eA\u0011,X\r\t;pA%$\u0017n\\:z]\u000e\u0014\u0018m]5fg\u0002Jg\u000eI5oi\u0016\u0014h-Y2fA\u0005tG\rI5oG>l\u0007\u000f\\3uK\u00022W-\u0019;ve\u0016\u001ch&I\u0001C\u0003\u0019\u0011d&M\u0019/a!\"\u0001\u0001R$I!\t)R)\u0003\u0002G\r\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\t5OMo9CI[$\u001e)!J\u0001E\u0001\u0017\u0006QA*\u001b8lK\u0012d\u0015n\u001d;\u0011\u00051ae!B\u0001\u0003\u0011\u0003i5c\u0001'OSA\u0019qd\u0014\u0013\n\u0005A\u0003#AC*fc\u001a\u000b7\r^8ss\")Q\u0006\u0014C\u0001%R\t1\nC\u0003U\u0019\u0012\u0005S+A\u0003f[B$\u00180\u0006\u0002W3V\tq\u000bE\u0002\r\u0001a\u0003\"\u0001E-\u0005\u000bI\u0019&\u0019A\n\t\u000bmcE1\u0001/\u0002\u0019\r\fgNQ;jY\u00124%o\\7\u0016\u0005u3W#\u00010\u0011\u000b}y\u0016-Z4\n\u0005\u0001\u0004#\u0001D\"b]\n+\u0018\u000e\u001c3Ge>l\u0007C\u00012d\u001b\u0005a\u0015B\u00013:\u0005\u0011\u0019u\u000e\u001c7\u0011\u0005A1G!\u0002\n[\u0005\u0004\u0019\u0002c\u0001\u0007\u0001K\")\u0011\u000e\u0014C\u0001U\u0006Qa.Z<Ck&dG-\u001a:\u0016\u0005-\u0004X#\u00017\u0011\t1iw.]\u0005\u0003]\n\u0011qAQ;jY\u0012,'\u000f\u0005\u0002\u0011a\u0012)!\u0003\u001bb\u0001'A\u0019A\u0002A8\t\u000fMd\u0015\u0011!C\u0005i\u0006Y!/Z1e%\u0016\u001cx\u000e\u001c<f)\u0005)\bC\u0001<|\u001b\u00059(B\u0001=z\u0003\u0011a\u0017M\\4\u000b\u0003i\fAA[1wC&\u0011Ap\u001e\u0002\u0007\u001f\nTWm\u0019;)\t1cd0Q\u0011\u0002\u007f\u00061Cj\\<.Y\u00164X\r\u001c\u0011mS:\\W\r\u001a\u0011mSN$8\u000fI1sK\u0002\"W\r\u001d:fG\u0006$X\r\u001a\u0018)\t1ct(\u0011\u0015\u0005\u0013rr\u0018\t")
public class LinkedList<A>
extends AbstractSeq<A>
implements scala.collection.mutable.LinearSeq<A>,
LinkedListLike<A, LinkedList<A>>,
Serializable {
    public static final long serialVersionUID = -7308240733518833071L;
    private Object elem;
    private scala.collection.mutable.Seq next;

    public static <A> CanBuildFrom<LinkedList<?>, A, LinkedList<A>> canBuildFrom() {
        return LinkedList$.MODULE$.canBuildFrom();
    }

    public static <A> LinkedList<A> empty() {
        return LinkedList$.MODULE$.empty();
    }

    public static Some unapplySeq(Seq seq) {
        return LinkedList$.MODULE$.unapplySeq(seq);
    }

    public static GenTraversable iterate(Object object, int n, Function1 function1) {
        return LinkedList$.MODULE$.iterate(object, n, function1);
    }

    public static GenTraversable range(Object object, Object object2, Object object3, Integral integral) {
        return LinkedList$.MODULE$.range(object, object2, object3, integral);
    }

    public static GenTraversable range(Object object, Object object2, Integral integral) {
        return LinkedList$.MODULE$.range(object, object2, integral);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, int n5, Function5 function5) {
        return LinkedList$.MODULE$.tabulate(n, n2, n3, n4, n5, function5);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, Function4 function4) {
        return LinkedList$.MODULE$.tabulate(n, n2, n3, n4, function4);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, Function3 function3) {
        return LinkedList$.MODULE$.tabulate(n, n2, n3, function3);
    }

    public static GenTraversable tabulate(int n, int n2, Function2 function2) {
        return LinkedList$.MODULE$.tabulate(n, n2, function2);
    }

    public static GenTraversable tabulate(int n, Function1 function1) {
        return LinkedList$.MODULE$.tabulate(n, function1);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, int n5, Function0 function0) {
        return LinkedList$.MODULE$.fill(n, n2, n3, n4, n5, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, Function0 function0) {
        return LinkedList$.MODULE$.fill(n, n2, n3, n4, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, Function0 function0) {
        return LinkedList$.MODULE$.fill(n, n2, n3, function0);
    }

    public static GenTraversable fill(int n, int n2, Function0 function0) {
        return LinkedList$.MODULE$.fill(n, n2, function0);
    }

    public static GenTraversable fill(int n, Function0 function0) {
        return LinkedList$.MODULE$.fill(n, function0);
    }

    public static GenTraversable concat(Seq seq) {
        return LinkedList$.MODULE$.concat(seq);
    }

    public static GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return LinkedList$.MODULE$.ReusableCBF();
    }

    public static GenTraversable empty() {
        return LinkedList$.MODULE$.empty();
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
    public scala.collection.mutable.Seq tail() {
        return LinkedListLike$class.tail(this);
    }

    @Override
    public scala.collection.mutable.Seq append(scala.collection.mutable.Seq that) {
        return LinkedListLike$class.append(this, that);
    }

    @Override
    public void insert(scala.collection.mutable.Seq that) {
        LinkedListLike$class.insert(this, that);
    }

    @Override
    public scala.collection.mutable.Seq drop(int n) {
        return LinkedListLike$class.drop(this, n);
    }

    @Override
    public A apply(int n) {
        return (A)LinkedListLike$class.apply(this, n);
    }

    @Override
    public void update(int n, A x) {
        LinkedListLike$class.update(this, n, x);
    }

    @Override
    public Option<A> get(int n) {
        return LinkedListLike$class.get(this, n);
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
    public scala.collection.mutable.Seq clone() {
        return LinkedListLike$class.clone(this);
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
    public GenericCompanion<LinkedList> companion() {
        return LinkedList$.MODULE$;
    }

    public LinkedList() {
        LinearSeqLike$class.$init$(this);
        LinearSeq$class.$init$(this);
        scala.collection.mutable.LinearSeq$class.$init$(this);
        LinkedListLike$class.$init$(this);
        this.next_$eq(this);
    }

    public LinkedList(A elem, LinkedList<A> next2) {
        this();
        if (next2 != null) {
            this.elem_$eq(elem);
            this.next_$eq(next2);
        }
    }
}

