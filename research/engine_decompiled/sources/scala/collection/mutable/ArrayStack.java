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
import scala.Serializable;
import scala.Some;
import scala.collection.AbstractIterator;
import scala.collection.GenTraversable;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.mutable.AbstractSeq;
import scala.collection.mutable.ArrayStack$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;
import scala.sys.package$;

@ScalaSignature(bytes="\u0006\u0001\t-r!B\u0001\u0003\u0011\u0003I\u0011AC!se\u0006L8\u000b^1dW*\u00111\u0001B\u0001\b[V$\u0018M\u00197f\u0015\t)a!\u0001\u0006d_2dWm\u0019;j_:T\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001!\tQ1\"D\u0001\u0003\r\u0015a!\u0001#\u0001\u000e\u0005)\t%O]1z'R\f7m[\n\u0004\u001799\u0004cA\b\u0013)5\t\u0001C\u0003\u0002\u0012\t\u00059q-\u001a8fe&\u001c\u0017BA\n\u0011\u0005)\u0019V-\u001d$bGR|'/\u001f\t\u0003\u0015U1A\u0001\u0004\u0002\u0001-U\u0011q#H\n\t+a9#FL\u00195oA\u0019!\"G\u000e\n\u0005i\u0011!aC!cgR\u0014\u0018m\u0019;TKF\u0004\"\u0001H\u000f\r\u0001\u0011)a$\u0006b\u0001?\t\tA+\u0005\u0002!IA\u0011\u0011EI\u0007\u0002\r%\u00111E\u0002\u0002\b\u001d>$\b.\u001b8h!\t\tS%\u0003\u0002'\r\t\u0019\u0011I\\=\u0011\u0007)A3$\u0003\u0002*\u0005\t\u00191+Z9\u0011\t)Y3$L\u0005\u0003Y\t\u0011qaU3r\u0019&\\W\rE\u0002\u000b+m\u0001BaD\u0018\u001c)%\u0011\u0001\u0007\u0005\u0002\u001b\u000f\u0016tWM]5d)J\fg/\u001a:tC\ndW\rV3na2\fG/\u001a\t\u0004\u0015Ij\u0013BA\u001a\u0003\u0005%\u0019En\u001c8fC\ndW\r\u0005\u0003\u000bkmi\u0013B\u0001\u001c\u0003\u0005\u001d\u0011U/\u001b7eKJ\u0004\"!\t\u001d\n\u0005e2!\u0001D*fe&\fG.\u001b>bE2,\u0007\u0002C\u001e\u0016\u0005\u0003\u0007I\u0011\u0002\u001f\u0002\u000bQ\f'\r\\3\u0016\u0003u\u00022!\t A\u0013\tydAA\u0003BeJ\f\u0017\u0010\u0005\u0002\"\u0003&\u0011!I\u0002\u0002\u0007\u0003:L(+\u001a4\t\u0011\u0011+\"\u00111A\u0005\n\u0015\u000b\u0011\u0002^1cY\u0016|F%Z9\u0015\u0005\u0019K\u0005CA\u0011H\u0013\tAeA\u0001\u0003V]&$\bb\u0002&D\u0003\u0003\u0005\r!P\u0001\u0004q\u0012\n\u0004\u0002\u0003'\u0016\u0005\u0003\u0005\u000b\u0015B\u001f\u0002\rQ\f'\r\\3!\u0011!qUC!a\u0001\n\u0013y\u0015!B5oI\u0016DX#\u0001)\u0011\u0005\u0005\n\u0016B\u0001*\u0007\u0005\rIe\u000e\u001e\u0005\t)V\u0011\t\u0019!C\u0005+\u0006I\u0011N\u001c3fq~#S-\u001d\u000b\u0003\rZCqAS*\u0002\u0002\u0003\u0007\u0001\u000b\u0003\u0005Y+\t\u0005\t\u0015)\u0003Q\u0003\u0019Ig\u000eZ3yA!)!,\u0006C\u00057\u00061A(\u001b8jiz\"2!\f/^\u0011\u0015Y\u0014\f1\u0001>\u0011\u0015q\u0015\f1\u0001Q\u0011\u0015QV\u0003\"\u0001`)\u0005i\u0003\"B1\u0016\t\u0003\u0011\u0017!B1qa2LHCA\u000ed\u0011\u0015!\u0007\r1\u0001Q\u0003\u0005q\u0007\"\u00024\u0016\t\u0003y\u0015A\u00027f]\u001e$\b\u000eC\u0003i+\u0011\u0005\u0013.A\u0005d_6\u0004\u0018M\\5p]V\t!N\u0004\u0002\u000b\u0001!)A.\u0006C\u0001[\u00061Q\u000f\u001d3bi\u0016$2A\u00128p\u0011\u0015!7\u000e1\u0001Q\u0011\u0015\u00018\u000e1\u0001\u001c\u0003\u001dqWm^3mK6DQA]\u000b\u0005\u0002M\fA\u0001];tQR\u0011a\t\u001e\u0005\u0006kF\u0004\raG\u0001\u0002q\")q/\u0006C\u0001q\u0006\u0019\u0001o\u001c9\u0015\u0003mAQA_\u000b\u0005\u0002m\f1\u0001^8q+\u0005Y\u0002\"B?\u0016\t\u0003q\u0018a\u00013vaR\ta\t\u0003\u0004\u0002\u0002U!\tA`\u0001\u0006G2,\u0017M\u001d\u0005\b\u0003\u000b)B\u0011AA\u0004\u0003\u0015!'/Y5o)\r1\u0015\u0011\u0002\u0005\t\u0003\u0017\t\u0019\u00011\u0001\u0002\u000e\u0005\ta\rE\u0003\"\u0003\u001fYb)C\u0002\u0002\u0012\u0019\u0011\u0011BR;oGRLwN\\\u0019\t\u000f\u0005UQ\u0003\"\u0011\u0002\u0018\u0005iA\u0005\u001d7vg\u0012\u0002H.^:%KF$B!!\u0007\u0002\u001c5\tQ\u0003\u0003\u0005\u0002\u001e\u0005M\u0001\u0019AA\u0010\u0003\tA8\u000fE\u0003\u0002\"\u0005\r2$D\u0001\u0005\u0013\r\t)\u0003\u0002\u0002\u0010)J\fg/\u001a:tC\ndWm\u00148dK\"9\u0011\u0011F\u000b\u0005\u0002\u0005-\u0012\u0001\u0003\u0013qYV\u001cH%Z9\u0015\t\u0005e\u0011Q\u0006\u0005\u0007k\u0006\u001d\u0002\u0019A\u000e\t\r\u0005ER\u0003\"\u0001`\u0003\u0019\u0011Xm];mi\"1\u0011QG\u000b\u0005\ny\fAB]3wKJ\u001cX\rV1cY\u0016Dq!!\u000f\u0016\t\u0003\tY$A\u0004d_6\u0014\u0017N\\3\u0015\u0007\u0019\u000bi\u0004\u0003\u0005\u0002\f\u0005]\u0002\u0019AA !\u0019\t\u0013\u0011I\u000e\u001c7%\u0019\u00111\t\u0004\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004bBA$+\u0011\u0005\u0011\u0011J\u0001\u000be\u0016$WoY3XSRDGc\u0001$\u0002L!A\u00111BA#\u0001\u0004\ty\u0004\u0003\u0004\u0002PU!\teT\u0001\u0005g&TX\rC\u0004\u0002TU!\t!!\u0016\u0002\u0015A\u0014Xm]3sm&tw-\u0006\u0003\u0002X\u0005mC\u0003BA-\u0003;\u00022\u0001HA.\t\u0019q\u0012\u0011\u000bb\u0001?!I\u0011qLA)\t\u0003\u0007\u0011\u0011M\u0001\u0007C\u000e$\u0018n\u001c8\u0011\u000b\u0005\n\u0019'!\u0017\n\u0007\u0005\u0015dA\u0001\u0005=Eft\u0017-\\3?\u0011\u001d\tI'\u0006C!\u0003W\nq![:F[B$\u00180\u0006\u0002\u0002nA\u0019\u0011%a\u001c\n\u0007\u0005EdAA\u0004C_>dW-\u00198\t\u000f\u0005UT\u0003\"\u0001\u0002x\u0005A\u0011\u000e^3sCR|'/\u0006\u0002\u0002zA)\u0011\u0011EA>7%\u0019\u0011Q\u0010\u0003\u0003\u0011%#XM]1u_JDq!!!\u0016\t\u0003\n\u0019)A\u0004g_J,\u0017m\u00195\u0016\t\u0005\u0015\u0015Q\u0012\u000b\u0004\r\u0006\u001d\u0005\u0002CA\u0006\u0003\u007f\u0002\r!!#\u0011\r\u0005\nyaGAF!\ra\u0012Q\u0012\u0003\b\u0003\u001f\u000byH1\u0001 \u0005\u0005)\u0006BBAJ+\u0011\u0005s,A\u0003dY>tW\rK\u0004\u0016\u0003/\u000bi*a(\u0011\u0007\u0005\nI*C\u0002\u0002\u001c\u001a\u0011\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u0002\u000bY\fG.^3\u001f\u0011Yl^Y\u001eo\u001db{CaAW\u0006\u0005\u0002\u0005\rF#A\u0005\t\u000f\u0005\u001d6\u0002b\u0001\u0002*\u0006a1-\u00198Ck&dGM\u0012:p[V!\u00111VAa+\t\ti\u000bE\u0005\u0010\u0003_\u000b\u0019,a0\u0002F&\u0019\u0011\u0011\u0017\t\u0003\u0019\r\u000bgNQ;jY\u00124%o\\7\u0011\t\u0005U\u0016qW\u0007\u0002\u0017%!\u0011\u0011XA^\u0005\u0011\u0019u\u000e\u001c7\n\u0007\u0005u\u0006C\u0001\tHK:,'/[2D_6\u0004\u0018M\\5p]B\u0019A$!1\u0005\u000f\u0005\r\u0017Q\u0015b\u0001?\t\t\u0011\t\u0005\u0003\u000b+\u0005}\u0006bBAe\u0017\u0011\u0005\u00111Z\u0001\u000b]\u0016<()^5mI\u0016\u0014X\u0003BAg\u0003',\"!a4\u0011\r))\u0014\u0011[Ak!\ra\u00121\u001b\u0003\b\u0003\u0007\f9M1\u0001 !\u0011QQ#!5\t\u000f\u0005e7\u0002\"\u0001\u0002\\\u0006)Q-\u001c9usV\u0011\u0011Q\u001c\t\u0004\u0015U\u0001\u0003BB1\f\t\u0003\t\t/\u0006\u0003\u0002d\u0006-H\u0003BAs\u0003{$B!a:\u0002nB!!\"FAu!\ra\u00121\u001e\u0003\b\u0003\u0007\fyN1\u0001 \u0011)\ty/a8\u0002\u0002\u0003\u000f\u0011\u0011_\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004CBAz\u0003s\fI/\u0004\u0002\u0002v*\u0019\u0011q\u001f\u0004\u0002\u000fI,g\r\\3di&!\u00111`A{\u0005!\u0019E.Y:t)\u0006<\u0007\u0002CA\u0000\u0003?\u0004\rA!\u0001\u0002\u000b\u0015dW-\\:\u0011\u000b\u0005\u0012\u0019!!;\n\u0007\t\u0015aA\u0001\u0006=e\u0016\u0004X-\u0019;fIzB\u0001B!\u0003\f\t\u0003\u0011!1B\u0001\nOJ|w/\u0011:sCf$2!\u0010B\u0007\u0011\u0019)(q\u0001a\u0001{!A\u00111S\u0006\u0005\u0002\t\u0011\t\u0002F\u0002>\u0005'Aa!\u001eB\b\u0001\u0004i\u0004\"\u0003B\f\u0017\u0005\u0005I\u0011\u0002B\r\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0005\tm\u0001\u0003\u0002B\u000f\u0005Oi!Aa\b\u000b\t\t\u0005\"1E\u0001\u0005Y\u0006twM\u0003\u0002\u0003&\u0005!!.\u0019<b\u0013\u0011\u0011ICa\b\u0003\r=\u0013'.Z2u\u0001")
public class ArrayStack<T>
extends AbstractSeq<T>
implements Builder<T, ArrayStack<T>>,
Serializable {
    public static final long serialVersionUID = 8565219180626620510L;
    private Object[] scala$collection$mutable$ArrayStack$$table;
    private int scala$collection$mutable$ArrayStack$$index;

    public static ArrayStack<Nothing$> empty() {
        return ArrayStack$.MODULE$.empty();
    }

    public static <A> CanBuildFrom<ArrayStack<?>, A, ArrayStack<A>> canBuildFrom() {
        return ArrayStack$.MODULE$.canBuildFrom();
    }

    public static Some unapplySeq(Seq seq) {
        return ArrayStack$.MODULE$.unapplySeq(seq);
    }

    public static GenTraversable iterate(Object object, int n, Function1 function1) {
        return ArrayStack$.MODULE$.iterate(object, n, function1);
    }

    public static GenTraversable range(Object object, Object object2, Object object3, Integral integral) {
        return ArrayStack$.MODULE$.range(object, object2, object3, integral);
    }

    public static GenTraversable range(Object object, Object object2, Integral integral) {
        return ArrayStack$.MODULE$.range(object, object2, integral);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, int n5, Function5 function5) {
        return ArrayStack$.MODULE$.tabulate(n, n2, n3, n4, n5, function5);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, Function4 function4) {
        return ArrayStack$.MODULE$.tabulate(n, n2, n3, n4, function4);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, Function3 function3) {
        return ArrayStack$.MODULE$.tabulate(n, n2, n3, function3);
    }

    public static GenTraversable tabulate(int n, int n2, Function2 function2) {
        return ArrayStack$.MODULE$.tabulate(n, n2, function2);
    }

    public static GenTraversable tabulate(int n, Function1 function1) {
        return ArrayStack$.MODULE$.tabulate(n, function1);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, int n5, Function0 function0) {
        return ArrayStack$.MODULE$.fill(n, n2, n3, n4, n5, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, Function0 function0) {
        return ArrayStack$.MODULE$.fill(n, n2, n3, n4, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, Function0 function0) {
        return ArrayStack$.MODULE$.fill(n, n2, n3, function0);
    }

    public static GenTraversable fill(int n, int n2, Function0 function0) {
        return ArrayStack$.MODULE$.fill(n, n2, function0);
    }

    public static GenTraversable fill(int n, Function0 function0) {
        return ArrayStack$.MODULE$.fill(n, function0);
    }

    public static GenTraversable concat(Seq seq) {
        return ArrayStack$.MODULE$.concat(seq);
    }

    public static GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return ArrayStack$.MODULE$.ReusableCBF();
    }

    public static GenTraversable empty() {
        return ArrayStack$.MODULE$.empty();
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
    public <NewTo> Builder<T, NewTo> mapResult(Function1<ArrayStack<T>, NewTo> f) {
        return Builder$class.mapResult(this, f);
    }

    @Override
    public Growable<T> $plus$eq(T elem1, T elem2, Seq<T> elems) {
        return Growable$class.$plus$eq(this, elem1, elem2, elems);
    }

    public Object[] scala$collection$mutable$ArrayStack$$table() {
        return this.scala$collection$mutable$ArrayStack$$table;
    }

    private void scala$collection$mutable$ArrayStack$$table_$eq(Object[] x$1) {
        this.scala$collection$mutable$ArrayStack$$table = x$1;
    }

    public int scala$collection$mutable$ArrayStack$$index() {
        return this.scala$collection$mutable$ArrayStack$$index;
    }

    private void scala$collection$mutable$ArrayStack$$index_$eq(int x$1) {
        this.scala$collection$mutable$ArrayStack$$index = x$1;
    }

    @Override
    public T apply(int n) {
        return (T)this.scala$collection$mutable$ArrayStack$$table()[this.scala$collection$mutable$ArrayStack$$index() - 1 - n];
    }

    @Override
    public int length() {
        return this.scala$collection$mutable$ArrayStack$$index();
    }

    public ArrayStack$ companion() {
        return ArrayStack$.MODULE$;
    }

    @Override
    public void update(int n, T newelem) {
        this.scala$collection$mutable$ArrayStack$$table()[this.scala$collection$mutable$ArrayStack$$index() - 1 - n] = newelem;
    }

    public void push(T x) {
        if (this.scala$collection$mutable$ArrayStack$$index() == this.scala$collection$mutable$ArrayStack$$table().length) {
            this.scala$collection$mutable$ArrayStack$$table_$eq(ArrayStack$.MODULE$.growArray(this.scala$collection$mutable$ArrayStack$$table()));
        }
        this.scala$collection$mutable$ArrayStack$$table()[this.scala$collection$mutable$ArrayStack$$index()] = x;
        this.scala$collection$mutable$ArrayStack$$index_$eq(this.scala$collection$mutable$ArrayStack$$index() + 1);
    }

    /*
     * WARNING - void declaration
     */
    public T pop() {
        void var1_1;
        if (this.scala$collection$mutable$ArrayStack$$index() == 0) {
            throw package$.MODULE$.error("Stack empty");
        }
        this.scala$collection$mutable$ArrayStack$$index_$eq(this.scala$collection$mutable$ArrayStack$$index() - 1);
        Object x = this.scala$collection$mutable$ArrayStack$$table()[this.scala$collection$mutable$ArrayStack$$index()];
        this.scala$collection$mutable$ArrayStack$$table()[this.scala$collection$mutable$ArrayStack$$index()] = null;
        return var1_1;
    }

    public T top() {
        return (T)this.scala$collection$mutable$ArrayStack$$table()[this.scala$collection$mutable$ArrayStack$$index() - 1];
    }

    public void dup() {
        this.push(this.top());
    }

    @Override
    public void clear() {
        this.scala$collection$mutable$ArrayStack$$index_$eq(0);
        this.scala$collection$mutable$ArrayStack$$table_$eq(new Object[1]);
    }

    public void drain(Function1<T, BoxedUnit> f) {
        while (!this.isEmpty()) {
            f.apply(this.pop());
        }
        return;
    }

    @Override
    public ArrayStack<T> $plus$plus$eq(TraversableOnce<T> xs) {
        xs.foreach(new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ArrayStack $outer;

            public final ArrayStack<T> apply(T x) {
                return this.$outer.$plus$eq((Object)x);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
        return this;
    }

    @Override
    public ArrayStack<T> $plus$eq(T x) {
        this.push(x);
        return this;
    }

    @Override
    public ArrayStack<T> result() {
        this.reverseTable();
        return this;
    }

    private void reverseTable() {
        int until2 = this.scala$collection$mutable$ArrayStack$$index() / 2;
        for (int i = 0; i < until2; ++i) {
            int revi = this.scala$collection$mutable$ArrayStack$$index() - i - 1;
            Object tmp = this.scala$collection$mutable$ArrayStack$$table()[i];
            this.scala$collection$mutable$ArrayStack$$table()[i] = this.scala$collection$mutable$ArrayStack$$table()[revi];
            this.scala$collection$mutable$ArrayStack$$table()[revi] = tmp;
        }
    }

    public void combine(Function2<T, T, T> f) {
        this.push(f.apply(this.pop(), this.pop()));
    }

    public void reduceWith(Function2<T, T, T> f) {
        while (this.size() > 1) {
            this.combine(f);
        }
    }

    @Override
    public int size() {
        return this.scala$collection$mutable$ArrayStack$$index();
    }

    /*
     * WARNING - void declaration
     */
    public <T> T preserving(Function0<T> action) {
        T t;
        int oldIndex = this.scala$collection$mutable$ArrayStack$$index();
        Object[] oldTable = ArrayStack$.MODULE$.clone(this.scala$collection$mutable$ArrayStack$$table());
        try {
            t = action.apply();
            this.scala$collection$mutable$ArrayStack$$index_$eq(oldIndex);
            this.scala$collection$mutable$ArrayStack$$table_$eq(oldTable);
        }
        catch (Throwable throwable) {
            void var3_3;
            void var2_2;
            this.scala$collection$mutable$ArrayStack$$index_$eq((int)var2_2);
            this.scala$collection$mutable$ArrayStack$$table_$eq((Object[])var3_3);
            throw throwable;
        }
        return t;
    }

    @Override
    public boolean isEmpty() {
        return this.scala$collection$mutable$ArrayStack$$index() == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new AbstractIterator<T>(this){
            private int currentIndex;
            private final /* synthetic */ ArrayStack $outer;

            private int currentIndex() {
                return this.currentIndex;
            }

            private void currentIndex_$eq(int x$1) {
                this.currentIndex = x$1;
            }

            public boolean hasNext() {
                return this.currentIndex() > 0;
            }

            public T next() {
                this.currentIndex_$eq(this.currentIndex() - 1);
                return (T)this.$outer.scala$collection$mutable$ArrayStack$$table()[this.currentIndex()];
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.currentIndex = $outer.scala$collection$mutable$ArrayStack$$index();
            }
        };
    }

    @Override
    public <U> void foreach(Function1<T, U> f) {
        int currentIndex = this.scala$collection$mutable$ArrayStack$$index();
        while (currentIndex > 0) {
            f.apply(this.scala$collection$mutable$ArrayStack$$table()[--currentIndex]);
        }
    }

    @Override
    public ArrayStack<T> clone() {
        return new ArrayStack<T>(ArrayStack$.MODULE$.clone(this.scala$collection$mutable$ArrayStack$$table()), this.scala$collection$mutable$ArrayStack$$index());
    }

    public ArrayStack(Object[] table, int index) {
        this.scala$collection$mutable$ArrayStack$$table = table;
        this.scala$collection$mutable$ArrayStack$$index = index;
        Growable$class.$init$(this);
        Builder$class.$init$(this);
    }

    public ArrayStack() {
        this(new Object[1], 0);
    }
}

