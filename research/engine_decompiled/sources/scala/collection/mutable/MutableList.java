/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterator;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.GenTraversable;
import scala.collection.IterableLike$class;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.LinearSeq;
import scala.collection.LinearSeqLike;
import scala.collection.LinearSeqLike$class;
import scala.collection.LinearSeqOptimized;
import scala.collection.LinearSeqOptimized$class;
import scala.collection.Seq;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.immutable.List;
import scala.collection.mutable.AbstractSeq;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.LinearSeq$class;
import scala.collection.mutable.LinkedList;
import scala.collection.mutable.MutableList$;
import scala.collection.mutable.Queue;
import scala.collection.mutable.StringBuilder;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u0005=g\u0001B\u0001\u0003\u0001%\u00111\"T;uC\ndW\rT5ti*\u00111\u0001B\u0001\b[V$\u0018M\u00197f\u0015\t)a!\u0001\u0006d_2dWm\u0019;j_:T\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001+\tQ\u0011cE\u0004\u0001\u0017mq2EK\u0017\u0011\u00071iq\"D\u0001\u0003\u0013\tq!AA\u0006BEN$(/Y2u'\u0016\f\bC\u0001\t\u0012\u0019\u0001!QA\u0005\u0001C\u0002M\u0011\u0011!Q\t\u0003)a\u0001\"!\u0006\f\u000e\u0003\u0019I!a\u0006\u0004\u0003\u000f9{G\u000f[5oOB\u0011Q#G\u0005\u00035\u0019\u00111!\u00118z!\raAdD\u0005\u0003;\t\u0011\u0011\u0002T5oK\u0006\u00148+Z9\u0011\t}\u0001sBI\u0007\u0002\t%\u0011\u0011\u0005\u0002\u0002\u0013\u0019&tW-\u0019:TKF|\u0005\u000f^5nSj,G\rE\u0002\r\u0001=\u0001B\u0001J\u0014\u0010S5\tQE\u0003\u0002'\t\u00059q-\u001a8fe&\u001c\u0017B\u0001\u0015&\u0005i9UM\\3sS\u000e$&/\u0019<feN\f'\r\\3UK6\u0004H.\u0019;f!\ta\u0001\u0001\u0005\u0003\rW=\u0011\u0013B\u0001\u0017\u0003\u0005\u001d\u0011U/\u001b7eKJ\u0004\"!\u0006\u0018\n\u0005=2!\u0001D*fe&\fG.\u001b>bE2,\u0007\"B\u0019\u0001\t\u0003\u0011\u0014A\u0002\u001fj]&$h\bF\u0001#\u0011\u0015!\u0004\u0001\"\u00116\u0003%\u0019w.\u001c9b]&|g.F\u00017!\r!s'K\u0005\u0003q\u0015\u0012\u0001cR3oKJL7mQ8na\u0006t\u0017n\u001c8\t\ri\u0002\u0001\u0015\"\u0015<\u0003)qWm\u001e\"vS2$WM]\u000b\u0002U!9Q\b\u0001a\u0001\n#q\u0014A\u00024jeN$\b'F\u0001@!\ra\u0001iD\u0005\u0003\u0003\n\u0011!\u0002T5oW\u0016$G*[:u\u0011\u001d\u0019\u0005\u00011A\u0005\u0012\u0011\u000b!BZ5sgR\u0004t\fJ3r)\t)\u0005\n\u0005\u0002\u0016\r&\u0011qI\u0002\u0002\u0005+:LG\u000fC\u0004J\u0005\u0006\u0005\t\u0019A \u0002\u0007a$\u0013\u0007\u0003\u0004L\u0001\u0001\u0006KaP\u0001\bM&\u00148\u000f\u001e\u0019!\u0011\u001di\u0005\u00011A\u0005\u0012y\nQ\u0001\\1tiBBqa\u0014\u0001A\u0002\u0013E\u0001+A\u0005mCN$\bg\u0018\u0013fcR\u0011Q)\u0015\u0005\b\u0013:\u000b\t\u00111\u0001@\u0011\u0019\u0019\u0006\u0001)Q\u0005\u007f\u00051A.Y:ua\u0001Bq!\u0016\u0001A\u0002\u0013Ea+A\u0002mK:,\u0012a\u0016\t\u0003+aK!!\u0017\u0004\u0003\u0007%sG\u000fC\u0004\\\u0001\u0001\u0007I\u0011\u0003/\u0002\u000f1,gn\u0018\u0013fcR\u0011Q)\u0018\u0005\b\u0013j\u000b\t\u00111\u0001X\u0011\u0019y\u0006\u0001)Q\u0005/\u0006!A.\u001a8!\u0011\u0015\t\u0007\u0001\"\u0001c\u0003\u001d!x.U;fk\u0016,\u0012a\u0019\t\u0004\u0019\u0011|\u0011BA3\u0003\u0005\u0015\tV/Z;f\u0011\u00159\u0007\u0001\"\u0011i\u0003\u001dI7/R7qif,\u0012!\u001b\t\u0003+)L!a\u001b\u0004\u0003\u000f\t{w\u000e\\3b]\")Q\u000e\u0001C!]\u0006!\u0001.Z1e+\u0005y\u0001\"\u00029\u0001\t\u0003\n\u0018\u0001\u0002;bS2,\u0012A\t\u0005\u0006g\u0002!)\u0002^\u0001\ti\u0006LG.S7qYR\u0011Q)\u001e\u0005\u0006mJ\u0004\rAI\u0001\u0003i2DQ\u0001\u001f\u0001\u0005\u0002e\fa\u0002\n9mkN$S-\u001d\u0013d_2|g\u000e\u0006\u0002{w6\t\u0001\u0001C\u0003}o\u0002\u0007q\"\u0001\u0003fY\u0016l\u0007\"\u0002@\u0001\t\u00032\u0016A\u00027f]\u001e$\b\u000eC\u0004\u0002\u0002\u0001!\t%a\u0001\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007=\t)\u0001\u0003\u0004\u0002\b}\u0004\raV\u0001\u0002]\"9\u00111\u0002\u0001\u0005\u0002\u00055\u0011AB;qI\u0006$X\rF\u0003F\u0003\u001f\t\t\u0002C\u0004\u0002\b\u0005%\u0001\u0019A,\t\u000f\u0005M\u0011\u0011\u0002a\u0001\u001f\u0005\t\u0001\u0010C\u0004\u0002\u0018\u0001!\t!!\u0007\u0002\u0007\u001d,G\u000f\u0006\u0003\u0002\u001c\u0005\u0005\u0002\u0003B\u000b\u0002\u001e=I1!a\b\u0007\u0005\u0019y\u0005\u000f^5p]\"9\u0011qAA\u000b\u0001\u00049\u0006bBA\u0013\u0001\u0011E\u0011qE\u0001\faJ,\u0007/\u001a8e\u000b2,W\u000eF\u0002F\u0003SAa\u0001`A\u0012\u0001\u0004y\u0001bBA\u0017\u0001\u0011E\u0011qF\u0001\u000bCB\u0004XM\u001c3FY\u0016lGcA#\u00022!1A0a\u000bA\u0002=Aq!!\u000e\u0001\t\u0003\n9$\u0001\u0005ji\u0016\u0014\u0018\r^8s+\t\tI\u0004\u0005\u0003 \u0003wy\u0011bAA\u001f\t\tA\u0011\n^3sCR|'\u000f\u0003\u0004\u0002B\u0001!\tE\\\u0001\u0005Y\u0006\u001cH\u000fC\u0004\u0002F\u0001!\t%a\u0012\u0002\rQ|G*[:u+\t\tI\u0005E\u0003\u0002L\u0005Es\"\u0004\u0002\u0002N)\u0019\u0011q\n\u0003\u0002\u0013%lW.\u001e;bE2,\u0017\u0002BA*\u0003\u001b\u0012A\u0001T5ti\"9\u0011q\u000b\u0001\u0005\u0002\tq\u0014\u0001\u0004;p\u0019&t7.\u001a3MSN$\bbBA.\u0001\u0011\u0005\u0011QL\u0001\tIAdWo\u001d\u0013fcR\u0019!0a\u0018\t\rq\fI\u00061\u0001\u0010\u0011\u001d\t\u0019\u0007\u0001C\u0001\u0003K\nQa\u00197fCJ$\u0012!\u0012\u0005\u0007\u0003S\u0002A\u0011\u0001\u001a\u0002\rI,7/\u001e7u\u0011\u0019\ti\u0007\u0001C!e\u0005)1\r\\8oK\":\u0001!!\u001d\u0002x\u0005e\u0004cA\u000b\u0002t%\u0019\u0011Q\u000f\u0004\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g\u0004\u0003*j=Kr\bQ\u0016a\b\u000f\u0005u$\u0001#\u0001\u0002\u0000\u0005YQ*\u001e;bE2,G*[:u!\ra\u0011\u0011\u0011\u0004\u0007\u0003\tA\t!a!\u0014\u000b\u0005\u0005\u0015QQ\u0017\u0011\t\u0011\n9)K\u0005\u0004\u0003\u0013+#AC*fc\u001a\u000b7\r^8ss\"9\u0011'!!\u0005\u0002\u00055ECAA@\u0011!\t\t*!!\u0005\u0004\u0005M\u0015\u0001D2b]\n+\u0018\u000e\u001c3Ge>lW\u0003BAK\u0003O+\"!a&\u0011\u0013\u0011\nI*!(\u0002&\u0006%\u0016bAANK\ta1)\u00198Ck&dGM\u0012:p[B!\u0011qTAQ\u001b\t\t\t)C\u0002\u0002$^\u0012AaQ8mYB\u0019\u0001#a*\u0005\rI\tyI1\u0001\u0014!\u0011a\u0001!!*\t\u000fi\n\t\t\"\u0001\u0002.V!\u0011qVA[+\t\t\t\f\u0005\u0004\rW\u0005M\u0016q\u0017\t\u0004!\u0005UFA\u0002\n\u0002,\n\u00071\u0003\u0005\u0003\r\u0001\u0005M\u0006BCA^\u0003\u0003\u000b\t\u0011\"\u0003\u0002>\u0006Y!/Z1e%\u0016\u001cx\u000e\u001c<f)\t\ty\f\u0005\u0003\u0002B\u0006-WBAAb\u0015\u0011\t)-a2\u0002\t1\fgn\u001a\u0006\u0003\u0003\u0013\fAA[1wC&!\u0011QZAb\u0005\u0019y%M[3di\u0002")
public class MutableList<A>
extends AbstractSeq<A>
implements scala.collection.mutable.LinearSeq<A>,
LinearSeqOptimized<A, MutableList<A>>,
Builder<A, MutableList<A>>,
Serializable {
    public static final long serialVersionUID = 5938451523372603072L;
    private LinkedList<A> first0;
    private LinkedList<A> last0;
    private int len;

    public static <A> CanBuildFrom<MutableList<?>, A, MutableList<A>> canBuildFrom() {
        return MutableList$.MODULE$.canBuildFrom();
    }

    public static Some unapplySeq(Seq seq) {
        return MutableList$.MODULE$.unapplySeq(seq);
    }

    public static GenTraversable iterate(Object object, int n, Function1 function1) {
        return MutableList$.MODULE$.iterate(object, n, function1);
    }

    public static GenTraversable range(Object object, Object object2, Object object3, Integral integral) {
        return MutableList$.MODULE$.range(object, object2, object3, integral);
    }

    public static GenTraversable range(Object object, Object object2, Integral integral) {
        return MutableList$.MODULE$.range(object, object2, integral);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, int n5, Function5 function5) {
        return MutableList$.MODULE$.tabulate(n, n2, n3, n4, n5, function5);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, Function4 function4) {
        return MutableList$.MODULE$.tabulate(n, n2, n3, n4, function4);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, Function3 function3) {
        return MutableList$.MODULE$.tabulate(n, n2, n3, function3);
    }

    public static GenTraversable tabulate(int n, int n2, Function2 function2) {
        return MutableList$.MODULE$.tabulate(n, n2, function2);
    }

    public static GenTraversable tabulate(int n, Function1 function1) {
        return MutableList$.MODULE$.tabulate(n, function1);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, int n5, Function0 function0) {
        return MutableList$.MODULE$.fill(n, n2, n3, n4, n5, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, Function0 function0) {
        return MutableList$.MODULE$.fill(n, n2, n3, n4, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, Function0 function0) {
        return MutableList$.MODULE$.fill(n, n2, n3, function0);
    }

    public static GenTraversable fill(int n, int n2, Function0 function0) {
        return MutableList$.MODULE$.fill(n, n2, function0);
    }

    public static GenTraversable fill(int n, Function0 function0) {
        return MutableList$.MODULE$.fill(n, function0);
    }

    public static GenTraversable concat(Seq seq) {
        return MutableList$.MODULE$.concat(seq);
    }

    public static GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return MutableList$.MODULE$.ReusableCBF();
    }

    public static GenTraversable empty() {
        return MutableList$.MODULE$.empty();
    }

    @Override
    public void sizeHint(int size2) {
        Builder$class.sizeHint((Builder)this, size2);
    }

    @Override
    public void sizeHint(TraversableLike<?, ?> coll) {
        Builder$class.sizeHint((Builder)this, coll);
    }

    @Override
    public void sizeHint(TraversableLike<?, ?> coll, int delta) {
        Builder$class.sizeHint(this, coll, delta);
    }

    @Override
    public void sizeHintBounded(int size2, TraversableLike<?, ?> boundingColl) {
        Builder$class.sizeHintBounded(this, size2, boundingColl);
    }

    @Override
    public <NewTo> Builder<A, NewTo> mapResult(Function1<MutableList<A>, NewTo> f) {
        return Builder$class.mapResult(this, f);
    }

    @Override
    public Growable<A> $plus$eq(A elem1, A elem2, Seq<A> elems) {
        return Growable$class.$plus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Growable<A> $plus$plus$eq(TraversableOnce<A> xs) {
        return Growable$class.$plus$plus$eq(this, xs);
    }

    @Override
    public /* synthetic */ boolean scala$collection$LinearSeqOptimized$$super$sameElements(GenIterable that) {
        return IterableLike$class.sameElements(this, that);
    }

    @Override
    public <U> void foreach(Function1<A, U> f) {
        LinearSeqOptimized$class.foreach(this, f);
    }

    @Override
    public boolean forall(Function1<A, Object> p) {
        return LinearSeqOptimized$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<A, Object> p) {
        return LinearSeqOptimized$class.exists(this, p);
    }

    @Override
    public <A1> boolean contains(A1 elem) {
        return LinearSeqOptimized$class.contains(this, elem);
    }

    @Override
    public Option<A> find(Function1<A, Object> p) {
        return LinearSeqOptimized$class.find(this, p);
    }

    @Override
    public <B> B foldLeft(B z, Function2<B, A, B> op) {
        return (B)LinearSeqOptimized$class.foldLeft(this, z, op);
    }

    @Override
    public <B> B foldRight(B z, Function2<A, B, B> op) {
        return (B)LinearSeqOptimized$class.foldRight(this, z, op);
    }

    @Override
    public <B> B reduceLeft(Function2<B, A, B> f) {
        return (B)LinearSeqOptimized$class.reduceLeft(this, f);
    }

    @Override
    public <B> B reduceRight(Function2<A, B, B> op) {
        return (B)LinearSeqOptimized$class.reduceRight(this, op);
    }

    @Override
    public LinearSeqOptimized take(int n) {
        return LinearSeqOptimized$class.take(this, n);
    }

    @Override
    public LinearSeqOptimized drop(int n) {
        return LinearSeqOptimized$class.drop(this, n);
    }

    @Override
    public LinearSeqOptimized dropRight(int n) {
        return LinearSeqOptimized$class.dropRight(this, n);
    }

    @Override
    public LinearSeqOptimized slice(int from2, int until2) {
        return LinearSeqOptimized$class.slice(this, from2, until2);
    }

    @Override
    public LinearSeqOptimized takeWhile(Function1 p) {
        return LinearSeqOptimized$class.takeWhile(this, p);
    }

    @Override
    public Tuple2<MutableList<A>, MutableList<A>> span(Function1<A, Object> p) {
        return LinearSeqOptimized$class.span(this, p);
    }

    @Override
    public <B> boolean sameElements(GenIterable<B> that) {
        return LinearSeqOptimized$class.sameElements(this, that);
    }

    @Override
    public int lengthCompare(int len) {
        return LinearSeqOptimized$class.lengthCompare(this, len);
    }

    @Override
    public boolean isDefinedAt(int x) {
        return LinearSeqOptimized$class.isDefinedAt(this, x);
    }

    @Override
    public int segmentLength(Function1<A, Object> p, int from2) {
        return LinearSeqOptimized$class.segmentLength(this, p, from2);
    }

    @Override
    public int indexWhere(Function1<A, Object> p, int from2) {
        return LinearSeqOptimized$class.indexWhere(this, p, from2);
    }

    @Override
    public int lastIndexWhere(Function1<A, Object> p, int end) {
        return LinearSeqOptimized$class.lastIndexWhere(this, p, end);
    }

    @Override
    public scala.collection.mutable.LinearSeq<A> seq() {
        return LinearSeq$class.seq(this);
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
    public GenericCompanion<MutableList> companion() {
        return MutableList$.MODULE$;
    }

    @Override
    public Builder<A, MutableList<A>> newBuilder() {
        return new MutableList<A>();
    }

    public LinkedList<A> first0() {
        return this.first0;
    }

    public void first0_$eq(LinkedList<A> x$1) {
        this.first0 = x$1;
    }

    public LinkedList<A> last0() {
        return this.last0;
    }

    public void last0_$eq(LinkedList<A> x$1) {
        this.last0 = x$1;
    }

    public int len() {
        return this.len;
    }

    public void len_$eq(int x$1) {
        this.len = x$1;
    }

    public Queue<A> toQueue() {
        return new Queue<A>(this.first0(), this.last0(), this.len());
    }

    @Override
    public boolean isEmpty() {
        return this.len() == 0;
    }

    @Override
    public A head() {
        if (this.nonEmpty()) {
            return this.first0().head();
        }
        throw new NoSuchElementException();
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public MutableList<A> tail() {
        void var1_1;
        MutableList<A> tl = new MutableList<A>();
        this.tailImpl(tl);
        return var1_1;
    }

    public final void tailImpl(MutableList<A> tl) {
        boolean bl = this.nonEmpty();
        Predef$ predef$ = Predef$.MODULE$;
        if (bl) {
            tl.first0_$eq((LinkedList)this.first0().tail());
            tl.len_$eq(this.len() - 1);
            tl.last0_$eq(tl.len() == 0 ? tl.first0() : this.last0());
            return;
        }
        throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append((Object)"tail of empty list").toString());
    }

    public MutableList<A> $plus$eq$colon(A elem) {
        this.prependElem(elem);
        return this;
    }

    @Override
    public int length() {
        return this.len();
    }

    @Override
    public A apply(int n) {
        return this.first0().apply(n);
    }

    @Override
    public void update(int n, A x) {
        this.first0().update(n, x);
    }

    public Option<A> get(int n) {
        return this.first0().get(n);
    }

    public void prependElem(A elem) {
        this.first0_$eq(new LinkedList<A>(elem, this.first0()));
        if (this.len() == 0) {
            this.last0_$eq(this.first0());
        }
        this.len_$eq(this.len() + 1);
    }

    public void appendElem(A elem) {
        if (this.len() == 0) {
            this.prependElem(elem);
        } else {
            this.last0().next_$eq(new LinkedList());
            this.last0_$eq((LinkedList)this.last0().next());
            this.last0().elem_$eq(elem);
            this.last0().next_$eq(new LinkedList());
            this.len_$eq(this.len() + 1);
        }
    }

    @Override
    public Iterator<A> iterator() {
        return this.isEmpty() ? Iterator$.MODULE$.empty() : new AbstractIterator<A>(this){
            private LinkedList<A> elems;
            private int count;

            private LinkedList<A> elems() {
                return this.elems;
            }

            private void elems_$eq(LinkedList<A> x$1) {
                this.elems = x$1;
            }

            private int count() {
                return this.count;
            }

            private void count_$eq(int x$1) {
                this.count = x$1;
            }

            public boolean hasNext() {
                return this.count() > 0 && this.elems().nonEmpty();
            }

            /*
             * WARNING - void declaration
             */
            public A next() {
                if (this.hasNext()) {
                    void var1_1;
                    this.count_$eq(this.count() - 1);
                    A e = this.elems().elem();
                    this.elems_$eq(this.count() == 0 ? null : (LinkedList)this.elems().next());
                    return var1_1;
                }
                throw new NoSuchElementException();
            }
            {
                this.elems = $outer.first0();
                this.count = $outer.len();
            }
        };
    }

    @Override
    public A last() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("MutableList.empty.last");
        }
        return this.last0().elem();
    }

    @Override
    public List<A> toList() {
        return this.first0().toList();
    }

    public LinkedList<A> toLinkedList() {
        return this.first0();
    }

    @Override
    public MutableList<A> $plus$eq(A elem) {
        this.appendElem(elem);
        return this;
    }

    @Override
    public void clear() {
        this.first0_$eq(new LinkedList());
        this.last0_$eq(this.first0());
        this.len_$eq(0);
    }

    @Override
    public MutableList<A> result() {
        return this;
    }

    @Override
    public MutableList<A> clone() {
        Builder<A, MutableList<A>> bf = this.newBuilder();
        bf.$plus$plus$eq(this.seq());
        return bf.result();
    }

    public MutableList() {
        LinearSeqLike$class.$init$(this);
        scala.collection.LinearSeq$class.$init$(this);
        LinearSeq$class.$init$(this);
        LinearSeqOptimized$class.$init$(this);
        Growable$class.$init$(this);
        Builder$class.$init$(this);
        this.first0 = new LinkedList();
        this.last0 = this.first0();
        this.len = 0;
    }
}

