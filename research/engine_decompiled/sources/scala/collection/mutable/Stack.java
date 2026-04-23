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
import scala.collection.GenTraversable;
import scala.collection.IterableLike$class;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.AbstractSeq;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.Stack$;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\t\rq!B\u0001\u0003\u0011\u0003I\u0011!B*uC\u000e\\'BA\u0002\u0005\u0003\u001diW\u000f^1cY\u0016T!!\u0002\u0004\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"AC\u0006\u000e\u0003\t1Q\u0001\u0004\u0002\t\u00025\u0011Qa\u0015;bG.\u001c2a\u0003\b5!\ry!\u0003F\u0007\u0002!)\u0011\u0011\u0003B\u0001\bO\u0016tWM]5d\u0013\t\u0019\u0002C\u0001\u0006TKF4\u0015m\u0019;pef\u0004\"AC\u000b\u0007\t1\u0011\u0001AF\u000b\u0003/u\u0019r!\u0006\r(U9\nD\u0007E\u0002\u000b3mI!A\u0007\u0002\u0003\u0017\u0005\u00137\u000f\u001e:bGR\u001cV-\u001d\t\u00039ua\u0001\u0001B\u0003\u001f+\t\u0007qDA\u0001B#\t\u0001C\u0005\u0005\u0002\"E5\ta!\u0003\u0002$\r\t9aj\u001c;iS:<\u0007CA\u0011&\u0013\t1cAA\u0002B]f\u00042A\u0003\u0015\u001c\u0013\tI#AA\u0002TKF\u0004BAC\u0016\u001c[%\u0011AF\u0001\u0002\b'\u0016\fH*[6f!\rQQc\u0007\t\u0005\u001f=ZB#\u0003\u00021!\tQr)\u001a8fe&\u001cGK]1wKJ\u001c\u0018M\u00197f)\u0016l\u0007\u000f\\1uKB\u0019!BM\u0017\n\u0005M\u0012!!C\"m_:,\u0017M\u00197f!\t\tS'\u0003\u00027\r\ta1+\u001a:jC2L'0\u00192mK\"A\u0001(\u0006BA\u0002\u0013\u0005\u0011(A\u0003fY\u0016l7/F\u0001;!\rYdhG\u0007\u0002y)\u0011Q\bB\u0001\nS6lW\u000f^1cY\u0016L!a\u0010\u001f\u0003\t1K7\u000f\u001e\u0005\t\u0003V\u0011\t\u0019!C\u0001\u0005\u0006IQ\r\\3ng~#S-\u001d\u000b\u0003\u0007\u001a\u0003\"!\t#\n\u0005\u00153!\u0001B+oSRDqa\u0012!\u0002\u0002\u0003\u0007!(A\u0002yIEB\u0001\"S\u000b\u0003\u0002\u0003\u0006KAO\u0001\u0007K2,Wn\u001d\u0011\t\u000b-+B\u0011\u0002'\u0002\rqJg.\u001b;?)\tiS\nC\u00039\u0015\u0002\u0007!\bC\u0003L+\u0011\u0005q\nF\u0001.\u0011\u0015\tV\u0003\"\u0011S\u0003%\u0019w.\u001c9b]&|g.F\u0001T\u001d\tQ\u0001\u0001C\u0003V+\u0011\u0005c+A\u0004jg\u0016k\u0007\u000f^=\u0016\u0003]\u0003\"!\t-\n\u0005e3!a\u0002\"p_2,\u0017M\u001c\u0005\u00067V!\t\u0005X\u0001\u0007Y\u0016tw\r\u001e5\u0016\u0003u\u0003\"!\t0\n\u0005}3!aA%oi\")\u0011-\u0006C!E\u0006)\u0011\r\u001d9msR\u00111d\u0019\u0005\u0006I\u0002\u0004\r!X\u0001\u0006S:$W\r\u001f\u0005\u0006MV!\taZ\u0001\u0007kB$\u0017\r^3\u0015\u0007\rC'\u000eC\u0003jK\u0002\u0007Q,A\u0001o\u0011\u0015YW\r1\u0001\u001c\u0003\u001dqWm^3mK6DQ!\\\u000b\u0005\u00029\fA\u0001];tQR\u0011q\u000e]\u0007\u0002+!)\u0011\u000f\u001ca\u00017\u0005!Q\r\\3n\u0011\u0015iW\u0003\"\u0001t)\u0011yGO\u001e=\t\u000bU\u0014\b\u0019A\u000e\u0002\u000b\u0015dW-\\\u0019\t\u000b]\u0014\b\u0019A\u000e\u0002\u000b\u0015dW-\u001c\u001a\t\u000ba\u0012\b\u0019A=\u0011\u0007\u0005R8$\u0003\u0002|\r\tQAH]3qK\u0006$X\r\u001a \t\u000bu,B\u0011\u0001@\u0002\u000fA,8\u000f[!mYR\u0011qn \u0005\b\u0003\u0003a\b\u0019AA\u0002\u0003\tA8\u000fE\u0003\u0002\u0006\u0005\u001d1$D\u0001\u0005\u0013\r\tI\u0001\u0002\u0002\u0010)J\fg/\u001a:tC\ndWm\u00148dK\"9\u0011QB\u000b\u0005\u0002\u0005=\u0011a\u0001;paV\t1\u0004C\u0004\u0002\u0014U!\t!!\u0006\u0002\u0007A|\u0007\u000fF\u0001\u001c\u0011\u001d\tI\"\u0006C\u0001\u00037\tQa\u00197fCJ$\u0012a\u0011\u0005\b\u0003?)B\u0011IA\u0011\u0003!IG/\u001a:bi>\u0014XCAA\u0012!\u0015\t)!!\n\u001c\u0013\r\t9\u0003\u0002\u0002\t\u0013R,'/\u0019;pe\"B\u0011QDA\u0016\u0003o\tY\u0004\u0005\u0003\u0002.\u0005MRBAA\u0018\u0015\r\t\tDB\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u001b\u0003_\u0011\u0011\"\\5he\u0006$\u0018n\u001c8\"\u0005\u0005e\u0012a\t1ji\u0016\u0014\u0018\r^8sA\u0002\"(/\u0019<feN,7\u000fI5oA\u0019Kei\u0014\u0011pe\u0012,'OL\u0011\u0003\u0003{\tQA\r\u00189]ABa!!\u0011\u0016\t\u0003J\u0014A\u0002;p\u0019&\u001cH\u000f\u000b\u0005\u0002@\u0005-\u0012QIA\u001eC\t\t9%A\u0011ai>d\u0015n\u001d;aAQ\u0014\u0018M^3sg\u0016\u001c\b%\u001b8!\r&3u\nI8sI\u0016\u0014h\u0006C\u0004\u0002LU!\t%!\u0014\u0002\u000f\u0019|'/Z1dQV!\u0011qJA/)\r\u0019\u0015\u0011\u000b\u0005\t\u0003'\nI\u00051\u0001\u0002V\u0005\ta\r\u0005\u0004\"\u0003/Z\u00121L\u0005\u0004\u000332!!\u0003$v]\u000e$\u0018n\u001c82!\ra\u0012Q\f\u0003\b\u0003?\nIE1\u0001 \u0005\u0005)\u0006\u0006CA%\u0003W\t\u0019'a\u000f\"\u0005\u0005\u0015\u0014A\t1g_J,\u0017m\u00195aAQ\u0014\u0018M^3sg\u0016\u001c\b%\u001b8!\r&3u\nI8sI\u0016\u0014h\u0006\u0003\u0004\u0002jU!\teT\u0001\u0006G2|g.\u001a\u0005\u0007\u0017.!\t!!\u001c\u0015\u0003%1a!!\u001d\f\u0001\u0005M$\u0001D*uC\u000e\\')^5mI\u0016\u0014X\u0003BA;\u0003\u000b\u001bb!a\u001c\u0002x\u0005u\u0004cA\u0011\u0002z%\u0019\u00111\u0010\u0004\u0003\r\u0005s\u0017PU3g!\u001dQ\u0011qPAB\u0003\u000fK1!!!\u0003\u0005\u001d\u0011U/\u001b7eKJ\u00042\u0001HAC\t\u0019q\u0012q\u000eb\u0001?A!!\"FAB\u0011\u001dY\u0015q\u000eC\u0001\u0003\u0017#\"!!$\u0011\r\u0005=\u0015qNAB\u001b\u0005Y\u0001BCAJ\u0003_\u0012\r\u0011\"\u0001\u0002\u0016\u0006)ANY;gMV\u0011\u0011q\u0013\t\u0006\u0015\u0005e\u00151Q\u0005\u0004\u00037\u0013!A\u0003'jgR\u0014UO\u001a4fe\"I\u0011qTA8A\u0003%\u0011qS\u0001\u0007Y\n,hM\u001a\u0011\t\u0011\u0005\r\u0016q\u000eC\u0001\u0003K\u000b\u0001\u0002\n9mkN$S-\u001d\u000b\u0005\u0003O\u000bI+\u0004\u0002\u0002p!9\u0011/!)A\u0002\u0005\r\u0005\u0002CA\r\u0003_\"\t!a\u0007\t\u0011\u0005=\u0016q\u000eC\u0001\u0003c\u000baA]3tk2$HCAAD\u0011\u001d\t)l\u0003C\u0002\u0003o\u000bAbY1o\u0005VLG\u000e\u001a$s_6,B!!/\u0002NV\u0011\u00111\u0018\t\n\u001f\u0005u\u0016\u0011YAf\u0003\u001fL1!a0\u0011\u00051\u0019\u0015M\u001c\"vS2$gI]8n!\u0011\ty)a1\n\t\u0005\u0015\u0017q\u0019\u0002\u0005\u0007>dG.C\u0002\u0002JB\u0011\u0001cR3oKJL7mQ8na\u0006t\u0017n\u001c8\u0011\u0007q\ti\r\u0002\u0004\u001f\u0003g\u0013\ra\b\t\u0005\u0015U\tY\rC\u0004\u0002T.!\t!!6\u0002\u00159,wOQ;jY\u0012,'/\u0006\u0003\u0002X\u0006uWCAAm!\u001dQ\u0011qPAn\u0003?\u00042\u0001HAo\t\u0019q\u0012\u0011\u001bb\u0001?A!!\"FAn\u0011%\t\u0019o\u0003b\u0001\n\u0003\t)/A\u0003f[B$\u00180\u0006\u0002\u0002hB\u0019!\"\u0006\u0011\t\u0011\u0005-8\u0002)A\u0005\u0003O\fa!Z7qif\u0004\u0003\"CAx\u0017\u0005\u0005I\u0011BAy\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0005\u0005M\b\u0003BA{\u0003\u007fl!!a>\u000b\t\u0005e\u00181`\u0001\u0005Y\u0006twM\u0003\u0002\u0002~\u0006!!.\u0019<b\u0013\u0011\u0011\t!a>\u0003\r=\u0013'.Z2u\u0001")
public class Stack<A>
extends AbstractSeq<A>
implements Serializable {
    private List<A> elems;

    public static Stack<Nothing$> empty() {
        return Stack$.MODULE$.empty();
    }

    public static <A> CanBuildFrom<Stack<?>, A, Stack<A>> canBuildFrom() {
        return Stack$.MODULE$.canBuildFrom();
    }

    public static Some unapplySeq(Seq seq) {
        return Stack$.MODULE$.unapplySeq(seq);
    }

    public static GenTraversable iterate(Object object, int n, Function1 function1) {
        return Stack$.MODULE$.iterate(object, n, function1);
    }

    public static GenTraversable range(Object object, Object object2, Object object3, Integral integral) {
        return Stack$.MODULE$.range(object, object2, object3, integral);
    }

    public static GenTraversable range(Object object, Object object2, Integral integral) {
        return Stack$.MODULE$.range(object, object2, integral);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, int n5, Function5 function5) {
        return Stack$.MODULE$.tabulate(n, n2, n3, n4, n5, function5);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, Function4 function4) {
        return Stack$.MODULE$.tabulate(n, n2, n3, n4, function4);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, Function3 function3) {
        return Stack$.MODULE$.tabulate(n, n2, n3, function3);
    }

    public static GenTraversable tabulate(int n, int n2, Function2 function2) {
        return Stack$.MODULE$.tabulate(n, n2, function2);
    }

    public static GenTraversable tabulate(int n, Function1 function1) {
        return Stack$.MODULE$.tabulate(n, function1);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, int n5, Function0 function0) {
        return Stack$.MODULE$.fill(n, n2, n3, n4, n5, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, Function0 function0) {
        return Stack$.MODULE$.fill(n, n2, n3, n4, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, Function0 function0) {
        return Stack$.MODULE$.fill(n, n2, n3, function0);
    }

    public static GenTraversable fill(int n, int n2, Function0 function0) {
        return Stack$.MODULE$.fill(n, n2, function0);
    }

    public static GenTraversable fill(int n, Function0 function0) {
        return Stack$.MODULE$.fill(n, function0);
    }

    public static GenTraversable concat(Seq seq) {
        return Stack$.MODULE$.concat(seq);
    }

    public static GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return Stack$.MODULE$.ReusableCBF();
    }

    public static GenTraversable empty() {
        return Stack$.MODULE$.empty();
    }

    public List<A> elems() {
        return this.elems;
    }

    public void elems_$eq(List<A> x$1) {
        this.elems = x$1;
    }

    public Stack$ companion() {
        return Stack$.MODULE$;
    }

    @Override
    public boolean isEmpty() {
        return this.elems().isEmpty();
    }

    @Override
    public int length() {
        return this.elems().length();
    }

    @Override
    public A apply(int index) {
        return this.elems().apply(index);
    }

    @Override
    public void update(int n, A newelem) {
        if (n < 0 || n >= this.length()) {
            throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(n)).toString());
        }
        this.elems_$eq(((List)this.elems().take(n)).$plus$plus(((List)this.elems().drop(n + 1)).$colon$colon(newelem), List$.MODULE$.canBuildFrom()));
    }

    public Stack<A> push(A elem) {
        this.elems_$eq(this.elems().$colon$colon(elem));
        return this;
    }

    public Stack<A> push(A elem1, A elem2, Seq<A> elems) {
        return this.push(elem1).push(elem2).pushAll(elems);
    }

    public Stack<A> pushAll(TraversableOnce<A> xs) {
        xs.foreach(new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Stack $outer;

            public final Stack<A> apply(A elem) {
                return this.$outer.push(elem);
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

    public A top() {
        return this.elems().head();
    }

    /*
     * WARNING - void declaration
     */
    public A pop() {
        void var1_1;
        A res = this.elems().head();
        this.elems_$eq((List)this.elems().tail());
        return var1_1;
    }

    public void clear() {
        this.elems_$eq(Nil$.MODULE$);
    }

    @Override
    public Iterator<A> iterator() {
        return this.elems().iterator();
    }

    @Override
    public List<A> toList() {
        return this.elems();
    }

    @Override
    public <U> void foreach(Function1<A, U> f) {
        IterableLike$class.foreach(this, f);
    }

    @Override
    public Stack<A> clone() {
        return new Stack<A>(this.elems());
    }

    public Stack(List<A> elems) {
        this.elems = elems;
    }

    public Stack() {
        this(Nil$.MODULE$);
    }

    public static class StackBuilder<A>
    implements Builder<A, Stack<A>> {
        private final ListBuffer<A> lbuff;

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
        public <NewTo> Builder<A, NewTo> mapResult(Function1<Stack<A>, NewTo> f) {
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

        public ListBuffer<A> lbuff() {
            return this.lbuff;
        }

        @Override
        public StackBuilder<A> $plus$eq(A elem) {
            this.lbuff().$plus$eq((Object)elem);
            return this;
        }

        @Override
        public void clear() {
            this.lbuff().clear();
        }

        @Override
        public Stack<A> result() {
            return new Stack(this.lbuff().result());
        }

        public StackBuilder() {
            Growable$class.$init$(this);
            Builder$class.$init$(this);
            this.lbuff = new ListBuffer();
        }
    }
}

