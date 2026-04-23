/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractSeq;
import scala.collection.AbstractTraversable;
import scala.collection.GenSeq;
import scala.collection.GenTraversable;
import scala.collection.Iterator;
import scala.collection.LinearSeqLike;
import scala.collection.LinearSeqLike$class;
import scala.collection.Seq;
import scala.collection.SeqLike$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.Iterable;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.LinearSeq;
import scala.collection.immutable.LinearSeq$class;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Queue$;
import scala.collection.immutable.Seq$class;
import scala.collection.immutable.Traversable$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParSeq;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\t\u001da\u0001B\u0001\u0003\u0001%\u0011Q!U;fk\u0016T!a\u0001\u0003\u0002\u0013%lW.\u001e;bE2,'BA\u0003\u0007\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u000f\u0005)1oY1mC\u000e\u0001QC\u0001\u0006\u0012'\u0019\u00011bG\u0010'UA\u0019A\"D\b\u000e\u0003\u0011I!A\u0004\u0003\u0003\u0017\u0005\u00137\u000f\u001e:bGR\u001cV-\u001d\t\u0003!Ea\u0001\u0001\u0002\u0004\u0013\u0001\u0011\u0015\ra\u0005\u0002\u0002\u0003F\u0011A\u0003\u0007\t\u0003+Yi\u0011AB\u0005\u0003/\u0019\u0011qAT8uQ&tw\r\u0005\u0002\u00163%\u0011!D\u0002\u0002\u0004\u0003:L\bc\u0001\u000f\u001e\u001f5\t!!\u0003\u0002\u001f\u0005\tIA*\u001b8fCJ\u001cV-\u001d\t\u0005A\rzQ%D\u0001\"\u0015\t\u0011C!A\u0004hK:,'/[2\n\u0005\u0011\n#AG$f]\u0016\u0014\u0018n\u0019+sCZ,'o]1cY\u0016$V-\u001c9mCR,\u0007C\u0001\u000f\u0001!\u0011aqeD\u0015\n\u0005!\"!!\u0004'j]\u0016\f'oU3r\u0019&\\W\rE\u0002\u001d\u0001=\u0001\"!F\u0016\n\u000512!\u0001D*fe&\fG.\u001b>bE2,\u0007\u0002\u0003\u0018\u0001\u0005\u000b\u0007I\u0011C\u0018\u0002\u0005%tW#\u0001\u0019\u0011\u0007q\tt\"\u0003\u00023\u0005\t!A*[:u\u0011!!\u0004A!A!\u0002\u0013\u0001\u0014aA5oA!Aa\u0007\u0001BC\u0002\u0013Eq&A\u0002pkRD\u0001\u0002\u000f\u0001\u0003\u0002\u0003\u0006I\u0001M\u0001\u0005_V$\b\u0005C\u0003;\u0001\u0011E1(\u0001\u0004=S:LGO\u0010\u000b\u0004Sqj\u0004\"\u0002\u0018:\u0001\u0004\u0001\u0004\"\u0002\u001c:\u0001\u0004\u0001\u0004\"B \u0001\t\u0003\u0002\u0015!C2p[B\fg.[8o+\u0005\t\u0005c\u0001\u0011CK%\u00111)\t\u0002\u0011\u000f\u0016tWM]5d\u0007>l\u0007/\u00198j_:DQ!\u0012\u0001\u0005B\u0019\u000bQ!\u00199qYf$\"aD$\t\u000b!#\u0005\u0019A%\u0002\u00039\u0004\"!\u0006&\n\u0005-3!aA%oi\")Q\n\u0001C!\u001d\u0006A\u0011\u000e^3sCR|'/F\u0001P!\ra\u0001kD\u0005\u0003#\u0012\u0011\u0001\"\u0013;fe\u0006$xN\u001d\u0005\u0006'\u0002!\t\u0005V\u0001\bSN,U\u000e\u001d;z+\u0005)\u0006CA\u000bW\u0013\t9fAA\u0004C_>dW-\u00198\t\u000be\u0003A\u0011\t.\u0002\t!,\u0017\rZ\u000b\u0002\u001f!)A\f\u0001C!;\u0006!A/Y5m+\u0005I\u0003\"B0\u0001\t\u0003\u0002\u0017A\u00027f]\u001e$\b.F\u0001J\u0011\u0015\u0011\u0007\u0001\"\u0011d\u0003-!\u0003\u000f\\;tI\r|Gn\u001c8\u0016\u0007\u0011|w\r\u0006\u0002feR\u0011a-\u001b\t\u0003!\u001d$Q\u0001[1C\u0002M\u0011A\u0001\u00165bi\")!.\u0019a\u0002W\u0006\u0011!M\u001a\t\u0006A1LcNZ\u0005\u0003[\u0006\u0012AbQ1o\u0005VLG\u000e\u001a$s_6\u0004\"\u0001E8\u0005\u000bA\f'\u0019A9\u0003\u0003\t\u000b\"a\u0004\r\t\u000bM\f\u0007\u0019\u00018\u0002\t\u0015dW-\u001c\u0005\u0006k\u0002!\tE^\u0001\fI\r|Gn\u001c8%a2,8/F\u0002x}j$\"\u0001_@\u0015\u0005e\\\bC\u0001\t{\t\u0015AGO1\u0001\u0014\u0011\u0015QG\u000fq\u0001}!\u0015\u0001C.K?z!\t\u0001b\u0010B\u0003qi\n\u0007\u0011\u000fC\u0003ti\u0002\u0007Q\u0010C\u0004\u0002\u0004\u0001!\t!!\u0002\u0002\u000f\u0015t\u0017/^3vKV!\u0011qAA\u0007)\u0011\tI!a\u0004\u0011\tq\u0001\u00111\u0002\t\u0004!\u00055AA\u00029\u0002\u0002\t\u0007\u0011\u000fC\u0004t\u0003\u0003\u0001\r!a\u0003\t\u000f\u0005\r\u0001\u0001\"\u0001\u0002\u0014U!\u0011QCA\u000e)\u0011\t9\"!\b\u0011\tq\u0001\u0011\u0011\u0004\t\u0004!\u0005mAA\u00029\u0002\u0012\t\u0007\u0011\u000f\u0003\u0005\u0002 \u0005E\u0001\u0019AA\u0011\u0003\u0011IG/\u001a:\u0011\u000bq\t\u0019#!\u0007\n\u0007\u0005\u0015\"A\u0001\u0005Ji\u0016\u0014\u0018M\u00197f\u0011\u001d\tI\u0003\u0001C\u0001\u0003W\tq\u0001Z3rk\u0016,X-\u0006\u0002\u0002.A)Q#a\f\u0010S%\u0019\u0011\u0011\u0007\u0004\u0003\rQ+\b\u000f\\33\u0011\u001d\t)\u0004\u0001C\u0001\u0003o\tQ\u0002Z3rk\u0016,Xm\u00149uS>tWCAA\u001d!\u0015)\u00121HA\u0017\u0013\r\tiD\u0002\u0002\u0007\u001fB$\u0018n\u001c8\t\r\u0005\u0005\u0003\u0001\"\u0001[\u0003\u00151'o\u001c8u\u0011\u001d\t)\u0005\u0001C!\u0003\u000f\n\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003\u0013\u0002B!a\u0013\u0002R9\u0019Q#!\u0014\n\u0007\u0005=c!\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003'\n)F\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003\u001f2\u0001f\u0002\u0001\u0002Z\u0005}\u00131\r\t\u0004+\u0005m\u0013bAA/\r\t)B-\u001a9sK\u000e\fG/\u001a3J]\",'/\u001b;b]\u000e,\u0017EAA1\u0003A#\u0006.\u001a\u0011j[BdW-\\3oi\u0006$\u0018n\u001c8!I\u0016$\u0018-\u001b7tA=4\u0007%[7nkR\f'\r\\3!cV,W/Z:![\u0006\\W\rI5oQ\u0016\u0014\u0018\u000e^5oO\u00022'o\\7!i\",W\u000eI;oo&\u001cXML\u0011\u0003\u0003K\naA\r\u00182c9\u0002\u0004f\u0002\u0001\u0002j\u0005=\u0014\u0011\u000f\t\u0004+\u0005-\u0014bAA7\r\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\t-W\u001a_unU,C\u001f9\u0011Q\u000f\u0002\t\u0002\u0005]\u0014!B)vKV,\u0007c\u0001\u000f\u0002z\u00191\u0011A\u0001E\u0001\u0003w\u001aR!!\u001f\u0002~)\u0002B\u0001IA@K%\u0019\u0011\u0011Q\u0011\u0003\u0015M+\u0017OR1di>\u0014\u0018\u0010C\u0004;\u0003s\"\t!!\"\u0015\u0005\u0005]\u0004\u0002CAE\u0003s\"\u0019!a#\u0002\u0019\r\fgNQ;jY\u00124%o\\7\u0016\t\u00055\u00151T\u000b\u0003\u0003\u001f\u0003\u0002\u0002\t7\u0002\u0012\u0006e\u0015Q\u0014\t\u0005\u0003'\u000b)*\u0004\u0002\u0002z%\u0019\u0011q\u0013\"\u0003\t\r{G\u000e\u001c\t\u0004!\u0005mEA\u0002\n\u0002\b\n\u00071\u0003\u0005\u0003\u001d\u0001\u0005e\u0005\u0002CAQ\u0003s\"\t!a)\u0002\u00159,wOQ;jY\u0012,'/\u0006\u0003\u0002&\u0006UVCAAT!!\tI+a,\u00024\u0006]VBAAV\u0015\r\ti\u000bB\u0001\b[V$\u0018M\u00197f\u0013\u0011\t\t,a+\u0003\u000f\t+\u0018\u000e\u001c3feB\u0019\u0001#!.\u0005\rI\tyJ1\u0001\u0014!\u0011a\u0002!a-\t\u0011\u0005m\u0016\u0011\u0010C!\u0003{\u000bQ!Z7qif,B!a0\u0002FV\u0011\u0011\u0011\u0019\t\u00059\u0001\t\u0019\rE\u0002\u0011\u0003\u000b$aAEA]\u0005\u0004\u0019\u0002bB#\u0002z\u0011\u0005\u0013\u0011Z\u000b\u0005\u0003\u0017\f\t\u000e\u0006\u0003\u0002N\u0006M\u0007\u0003\u0002\u000f\u0001\u0003\u001f\u00042\u0001EAi\t\u0019\u0011\u0012q\u0019b\u0001'!A\u0011Q[Ad\u0001\u0004\t9.\u0001\u0002ygB)Q#!7\u0002P&\u0019\u00111\u001c\u0004\u0003\u0015q\u0012X\r]3bi\u0016$gh\u0002\u0005\u0002`\u0006e\u0004\u0012BAq\u0003))U\u000e\u001d;z#V,W/\u001a\t\u0005\u0003'\u000b\u0019O\u0002\u0005\u0002f\u0006e\u0004\u0012BAt\u0005))U\u000e\u001d;z#V,W/Z\n\u0005\u0003G\fI\u000fE\u0002\u001d\u0001QAqAOAr\t\u0003\ti\u000f\u0006\u0002\u0002b\"Q\u0011\u0011_Ar\u0003\u0003%I!a=\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0003\u0003k\u0004B!a>\u0003\u00025\u0011\u0011\u0011 \u0006\u0005\u0003w\fi0\u0001\u0003mC:<'BAA\u0000\u0003\u0011Q\u0017M^1\n\t\t\r\u0011\u0011 \u0002\u0007\u001f\nTWm\u0019;\t\u0015\u0005E\u0018\u0011PA\u0001\n\u0013\t\u0019\u0010")
public class Queue<A>
extends AbstractSeq<A>
implements LinearSeq<A>,
Serializable {
    public static final long serialVersionUID = -7622936493364270175L;
    private final List<A> in;
    private final List<A> out;

    public static <A> Queue<A> empty() {
        return Queue$.MODULE$.empty();
    }

    public static <A> CanBuildFrom<Queue<?>, A, Queue<A>> canBuildFrom() {
        return Queue$.MODULE$.canBuildFrom();
    }

    public static Some unapplySeq(Seq seq) {
        return Queue$.MODULE$.unapplySeq(seq);
    }

    public static GenTraversable iterate(Object object, int n, Function1 function1) {
        return Queue$.MODULE$.iterate(object, n, function1);
    }

    public static GenTraversable range(Object object, Object object2, Object object3, Integral integral) {
        return Queue$.MODULE$.range(object, object2, object3, integral);
    }

    public static GenTraversable range(Object object, Object object2, Integral integral) {
        return Queue$.MODULE$.range(object, object2, integral);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, int n5, Function5 function5) {
        return Queue$.MODULE$.tabulate(n, n2, n3, n4, n5, function5);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, Function4 function4) {
        return Queue$.MODULE$.tabulate(n, n2, n3, n4, function4);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, Function3 function3) {
        return Queue$.MODULE$.tabulate(n, n2, n3, function3);
    }

    public static GenTraversable tabulate(int n, int n2, Function2 function2) {
        return Queue$.MODULE$.tabulate(n, n2, function2);
    }

    public static GenTraversable tabulate(int n, Function1 function1) {
        return Queue$.MODULE$.tabulate(n, function1);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, int n5, Function0 function0) {
        return Queue$.MODULE$.fill(n, n2, n3, n4, n5, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, Function0 function0) {
        return Queue$.MODULE$.fill(n, n2, n3, n4, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, Function0 function0) {
        return Queue$.MODULE$.fill(n, n2, n3, function0);
    }

    public static GenTraversable fill(int n, int n2, Function0 function0) {
        return Queue$.MODULE$.fill(n, n2, function0);
    }

    public static GenTraversable fill(int n, Function0 function0) {
        return Queue$.MODULE$.fill(n, function0);
    }

    public static GenTraversable concat(Seq seq) {
        return Queue$.MODULE$.concat(seq);
    }

    public static GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return Queue$.MODULE$.ReusableCBF();
    }

    public static GenTraversable empty() {
        return Queue$.MODULE$.empty();
    }

    @Override
    public LinearSeq<A> seq() {
        return LinearSeq$class.seq(this);
    }

    @Override
    public scala.collection.LinearSeq<A> thisCollection() {
        return LinearSeqLike$class.thisCollection(this);
    }

    @Override
    public scala.collection.LinearSeq toCollection(LinearSeqLike repr) {
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
    public scala.collection.immutable.Seq<A> toSeq() {
        return Seq$class.toSeq(this);
    }

    @Override
    public Combiner<A, ParSeq<A>> parCombiner() {
        return Seq$class.parCombiner(this);
    }

    public List<A> in() {
        return this.in;
    }

    public List<A> out() {
        return this.out;
    }

    @Override
    public GenericCompanion<Queue> companion() {
        return Queue$.MODULE$;
    }

    @Override
    public A apply(int n) {
        block4: {
            A a;
            block3: {
                int olen;
                block2: {
                    olen = this.out().length();
                    if (n >= olen) break block2;
                    a = this.out().apply(n);
                    break block3;
                }
                int m = n - olen;
                int ilen = this.in().length();
                if (m >= ilen) break block4;
                a = this.in().apply(ilen - m - 1);
            }
            return a;
        }
        throw new NoSuchElementException("index out of range");
    }

    @Override
    public Iterator<A> iterator() {
        List<A> list2 = this.out();
        return ((List)this.in().reverse()).$colon$colon$colon(list2).iterator();
    }

    @Override
    public boolean isEmpty() {
        return this.in().isEmpty() && this.out().isEmpty();
    }

    @Override
    public A head() {
        block4: {
            A a;
            block3: {
                block2: {
                    if (!this.out().nonEmpty()) break block2;
                    a = this.out().head();
                    break block3;
                }
                if (!this.in().nonEmpty()) break block4;
                a = this.in().last();
            }
            return a;
        }
        throw new NoSuchElementException("head on empty queue");
    }

    @Override
    public Queue<A> tail() {
        block4: {
            Queue<A> queue;
            block3: {
                block2: {
                    if (!this.out().nonEmpty()) break block2;
                    queue = new Queue<A>(this.in(), (List)this.out().tail());
                    break block3;
                }
                if (!this.in().nonEmpty()) break block4;
                queue = new Queue<Nothing$>(Nil$.MODULE$, (List)((AbstractTraversable)this.in().reverse()).tail());
            }
            return queue;
        }
        throw new NoSuchElementException("tail on empty queue");
    }

    @Override
    public int length() {
        return this.in().length() + this.out().length();
    }

    @Override
    public <B, That> That $plus$colon(B elem, CanBuildFrom<Queue<A>, B, That> bf) {
        Queue<A> queue = bf instanceof GenTraversableFactory.GenericCanBuildFrom ? new Queue<A>(this.in(), this.out().$colon$colon(elem)) : SeqLike$class.$plus$colon(this, elem, bf);
        return (That)queue;
    }

    @Override
    public <B, That> That $colon$plus(B elem, CanBuildFrom<Queue<A>, B, That> bf) {
        Object object = bf instanceof GenTraversableFactory.GenericCanBuildFrom ? this.enqueue(elem) : SeqLike$class.$colon$plus(this, elem, bf);
        return (That)object;
    }

    public <B> Queue<B> enqueue(B elem) {
        return new Queue<B>(this.in().$colon$colon(elem), this.out());
    }

    public <B> Queue<B> enqueue(Iterable<B> iter2) {
        List list2 = iter2.toList();
        return new Queue(this.in().reverse_$colon$colon$colon(list2), this.out());
    }

    public Tuple2<A, Queue<A>> dequeue() {
        block4: {
            Tuple2<Object, Queue<Object>> tuple2;
            block3: {
                List<A> list2;
                block2: {
                    list2 = this.out();
                    if (!((Object)Nil$.MODULE$).equals(list2) || this.in().isEmpty()) break block2;
                    Object rev = this.in().reverse();
                    tuple2 = new Tuple2(((List)rev).head(), new Queue<Nothing$>(Nil$.MODULE$, (List)((AbstractTraversable)rev).tail()));
                    break block3;
                }
                if (!(list2 instanceof $colon$colon)) break block4;
                $colon$colon $colon$colon = ($colon$colon)list2;
                tuple2 = new Tuple2($colon$colon.head(), new Queue<A>(this.in(), $colon$colon.tl$1()));
            }
            return tuple2;
        }
        throw new NoSuchElementException("dequeue on empty queue");
    }

    public Option<Tuple2<A, Queue<A>>> dequeueOption() {
        return this.isEmpty() ? None$.MODULE$ : new Some<Tuple2<A, Queue<A>>>(this.dequeue());
    }

    public A front() {
        return this.head();
    }

    @Override
    public String toString() {
        return this.mkString("Queue(", ", ", ")");
    }

    public Queue(List<A> in, List<A> out) {
        this.in = in;
        this.out = out;
        Traversable$class.$init$(this);
        Iterable$class.$init$(this);
        Seq$class.$init$(this);
        LinearSeqLike$class.$init$(this);
        scala.collection.LinearSeq$class.$init$(this);
        LinearSeq$class.$init$(this);
    }
}

