/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenSet;
import scala.collection.GenSet$class;
import scala.collection.GenSetLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterable$class;
import scala.collection.IterableLike$class;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.Set$class;
import scala.collection.SetLike$class;
import scala.collection.SortedSetLike$class;
import scala.collection.Traversable;
import scala.collection.Traversable$class;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericSetTemplate$class;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Sorted$class;
import scala.collection.generic.Subtractable;
import scala.collection.generic.Subtractable$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.RedBlackTree;
import scala.collection.immutable.RedBlackTree$;
import scala.collection.immutable.SortedSet;
import scala.collection.immutable.SortedSet$class;
import scala.collection.immutable.Stream;
import scala.collection.immutable.TreeSet$;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParSet;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.package$;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\tMs!B\u0001\u0003\u0011\u0003I\u0011a\u0002+sK\u0016\u001cV\r\u001e\u0006\u0003\u0007\u0011\t\u0011\"[7nkR\f'\r\\3\u000b\u0005\u00151\u0011AC2pY2,7\r^5p]*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0011\u0005)YQ\"\u0001\u0002\u0007\u000b1\u0011\u0001\u0012A\u0007\u0003\u000fQ\u0013X-Z*fiN\u00191BD\u0018\u0011\u0007=\u0011B#D\u0001\u0011\u0015\t\tB!A\u0004hK:,'/[2\n\u0005M\u0001\"!G%n[V$\u0018M\u00197f'>\u0014H/\u001a3TKR4\u0015m\u0019;pef\u0004\"AC\u000b\u0007\t1\u0011\u0001AF\u000b\u0003/\u0005\u001aR!\u0006\r\u001dU=\u0002\"!\u0007\u000e\u000e\u0003\u0019I!a\u0007\u0004\u0003\r\u0005s\u0017PU3g!\rQQdH\u0005\u0003=\t\u0011\u0011bU8si\u0016$7+\u001a;\u0011\u0005\u0001\nC\u0002\u0001\u0003\u0006EU\u0011\ra\t\u0002\u0002\u0003F\u0011Ae\n\t\u00033\u0015J!A\n\u0004\u0003\u000f9{G\u000f[5oOB\u0011\u0011\u0004K\u0005\u0003S\u0019\u00111!\u00118z!\u0011YCf\b\u0018\u000e\u0003\u0011I!!\f\u0003\u0003\u001bM{'\u000f^3e'\u0016$H*[6f!\rQQc\b\t\u00033AJ!!\r\u0004\u0003\u0019M+'/[1mSj\f'\r\\3\t\u0011M*\"\u0011!Q\u0001\nQ\nA\u0001\u001e:fKB!Q\u0007O\u0010<\u001d\tQa'\u0003\u00028\u0005\u0005a!+\u001a3CY\u0006\u001c7\u000e\u0016:fK&\u0011\u0011H\u000f\u0002\u0005)J,WM\u0003\u00028\u0005A\u0011\u0011\u0004P\u0005\u0003{\u0019\u0011A!\u00168ji\"Aq(\u0006BC\u0002\u0013\r\u0001)\u0001\u0005pe\u0012,'/\u001b8h+\u0005\t\u0005c\u0001\"F?9\u0011\u0011dQ\u0005\u0003\t\u001a\tq\u0001]1dW\u0006<W-\u0003\u0002G\u000f\nAqJ\u001d3fe&twM\u0003\u0002E\r!A\u0011*\u0006B\u0001B\u0003%\u0011)A\u0005pe\u0012,'/\u001b8hA!)1*\u0006C\u0005\u0019\u00061A(\u001b8jiz\"\"!T(\u0015\u00059r\u0005\"B K\u0001\b\t\u0005\"B\u001aK\u0001\u0004!\u0004\"B)\u0016\t\u0003\u0012\u0016\u0001D:ue&tw\r\u0015:fM&DX#A*\u0011\u0005QKV\"A+\u000b\u0005Y;\u0016\u0001\u00027b]\u001eT\u0011\u0001W\u0001\u0005U\u00064\u0018-\u0003\u0002[+\n11\u000b\u001e:j]\u001eDQ\u0001X\u000b\u0005Bu\u000bAa]5{KV\ta\f\u0005\u0002\u001a?&\u0011\u0001M\u0002\u0002\u0004\u0013:$\b\"\u00022\u0016\t\u0003\u001a\u0017\u0001\u00025fC\u0012,\u0012a\b\u0005\u0006KV!\tEZ\u0001\u000bQ\u0016\fGm\u00149uS>tW#A4\u0011\u0007eAw$\u0003\u0002j\r\t1q\n\u001d;j_:DQa[\u000b\u0005B\r\fA\u0001\\1ti\")Q.\u0006C!M\u0006QA.Y:u\u001fB$\u0018n\u001c8\t\u000b=,B\u0011\t9\u0002\tQ\f\u0017\u000e\\\u000b\u0002]!)!/\u0006C!a\u0006!\u0011N\\5u\u0011\u0015!X\u0003\"\u0011v\u0003\u0011!'o\u001c9\u0015\u000592\b\"B<t\u0001\u0004q\u0016!\u00018\t\u000be,B\u0011\t>\u0002\tQ\f7.\u001a\u000b\u0003]mDQa\u001e=A\u0002yCQ!`\u000b\u0005By\fQa\u001d7jG\u0016$BAL@\u0002\u0004!1\u0011\u0011\u0001?A\u0002y\u000bAA\u001a:p[\"1\u0011Q\u0001?A\u0002y\u000bQ!\u001e8uS2Dq!!\u0003\u0016\t\u0003\nY!A\u0005ee>\u0004(+[4iiR\u0019a&!\u0004\t\r]\f9\u00011\u0001_\u0011\u001d\t\t\"\u0006C!\u0003'\t\u0011\u0002^1lKJKw\r\u001b;\u0015\u00079\n)\u0002\u0003\u0004x\u0003\u001f\u0001\rA\u0018\u0005\b\u00033)B\u0011IA\u000e\u0003\u001d\u0019\b\u000f\\5u\u0003R$B!!\b\u0002$A)\u0011$a\b/]%\u0019\u0011\u0011\u0005\u0004\u0003\rQ+\b\u000f\\33\u0011\u00199\u0018q\u0003a\u0001=\"A\u0011qE\u000b!\n\u0013\tI#\u0001\u0006d_VtGo\u00165jY\u0016$2AXA\u0016\u0011!\ti#!\nA\u0002\u0005=\u0012!\u00019\u0011\re\t\tdHA\u001b\u0013\r\t\u0019D\u0002\u0002\n\rVt7\r^5p]F\u00022!GA\u001c\u0013\r\tID\u0002\u0002\b\u0005>|G.Z1o\u0011\u001d\ti$\u0006C!\u0003\u007f\t\u0011\u0002\u001a:pa^C\u0017\u000e\\3\u0015\u00079\n\t\u0005\u0003\u0005\u0002.\u0005m\u0002\u0019AA\u0018\u0011\u001d\t)%\u0006C!\u0003\u000f\n\u0011\u0002^1lK^C\u0017\u000e\\3\u0015\u00079\nI\u0005\u0003\u0005\u0002.\u0005\r\u0003\u0019AA\u0018\u0011\u001d\ti%\u0006C!\u0003\u001f\nAa\u001d9b]R!\u0011QDA)\u0011!\ti#a\u0013A\u0002\u0005=\u0002BB&\u0016\t\u0003\t)\u0006\u0006\u0002\u0002XQ\u0019a&!\u0017\t\r}\n\u0019\u0006q\u0001B\u0011\u001d\ti&\u0006C\u0005\u0003?\naA\\3x'\u0016$Hc\u0001\u0018\u0002b!9\u00111MA.\u0001\u0004!\u0014!\u0001;\t\r\u0005\u001dT\u0003\"\u0011q\u0003\u0015)W\u000e\u001d;z\u0011\u001d\tY'\u0006C\u0001\u0003[\nQ\u0001\n9mkN$2ALA8\u0011\u001d\t\t(!\u001bA\u0002}\tA!\u001a7f[\"9\u0011QO\u000b\u0005\u0002\u0005]\u0014AB5og\u0016\u0014H\u000fF\u0002/\u0003sBq!!\u001d\u0002t\u0001\u0007q\u0004C\u0004\u0002~U!\t!a \u0002\r\u0011j\u0017N\\;t)\rq\u0013\u0011\u0011\u0005\b\u0003c\nY\b1\u0001 \u0011\u001d\t))\u0006C\u0001\u0003\u000f\u000b\u0001bY8oi\u0006Lgn\u001d\u000b\u0005\u0003k\tI\tC\u0004\u0002r\u0005\r\u0005\u0019A\u0010\t\u000f\u00055U\u0003\"\u0001\u0002\u0010\u0006A\u0011\u000e^3sCR|'/\u0006\u0002\u0002\u0012B!1&a% \u0013\r\t)\n\u0002\u0002\t\u0013R,'/\u0019;pe\"9\u0011\u0011T\u000b\u0005B\u0005m\u0015\u0001E6fsNLE/\u001a:bi>\u0014hI]8n)\u0011\t\t*!(\t\u000f\u0005}\u0015q\u0013a\u0001?\u0005)1\u000f^1si\"9\u00111U\u000b\u0005B\u0005\u0015\u0016a\u00024pe\u0016\f7\r[\u000b\u0005\u0003O\u000b\t\fF\u0002<\u0003SC\u0001\"a+\u0002\"\u0002\u0007\u0011QV\u0001\u0002MB1\u0011$!\r \u0003_\u00032\u0001IAY\t\u001d\t\u0019,!)C\u0002\r\u0012\u0011!\u0016\u0005\b\u0003o+B\u0011IA]\u0003%\u0011\u0018M\\4f\u00136\u0004H\u000eF\u0003/\u0003w\u000bi\fC\u0004\u0002\u0002\u0005U\u0006\u0019A4\t\u000f\u0005\u0015\u0011Q\u0017a\u0001O\"9\u0011\u0011Y\u000b\u0005B\u0005\r\u0017!\u0002:b]\u001e,G#\u0002\u0018\u0002F\u0006\u001d\u0007bBA\u0001\u0003\u007f\u0003\ra\b\u0005\b\u0003\u000b\ty\f1\u0001 \u0011\u001d\t\t!\u0006C!\u0003\u0017$2ALAg\u0011\u001d\t\t!!3A\u0002}Aq!!5\u0016\t\u0003\n\u0019.\u0001\u0002u_R\u0019a&!6\t\u000f\u0005E\u0017q\u001aa\u0001?!9\u0011QA\u000b\u0005B\u0005eGc\u0001\u0018\u0002\\\"9\u0011QAAl\u0001\u0004y\u0002BBAp+\u0011\u00053-\u0001\u0005gSJ\u001cHoS3z\u0011\u0019\t\u0019/\u0006C!G\u00069A.Y:u\u0017\u0016L\bfB\u000b\u0002h\u00065\u0018\u0011\u001f\t\u00043\u0005%\u0018bAAv\r\t)B-\u001a9sK\u000e\fG/\u001a3J]\",'/\u001b;b]\u000e,\u0017EAAx\u0003M#\u0006.\u001a\u0011j[BdW-\\3oi\u0006$\u0018n\u001c8!I\u0016$\u0018-\u001b7tA=4\u0007%[7nkR\f'\r\\3!iJ,W\rI:fiN\u0004S.Y6fA%t\u0007.\u001a:ji&tw\r\t4s_6\u0004C\u000f[3nAUtw/[:f]\u0005\u0012\u00111_\u0001\u0007e9\n\u0014G\f\u0019)\u000fU\t90!@\u0002\u0000B\u0019\u0011$!?\n\u0007\u0005mhA\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKzA\u0011wF+!qmG6\u0002\u0003\u0004L\u0017\u0011\u0005!1\u0001\u000b\u0002\u0013!9!qA\u0006\u0005\u0004\t%\u0011aD5na2L7-\u001b;Ck&dG-\u001a:\u0016\t\t-!1\u0004\u000b\u0005\u0005\u001b\u0011y\u0002\u0005\u0005\u0003\u0010\tU!\u0011\u0004B\u000f\u001b\t\u0011\tBC\u0002\u0003\u0014\u0011\tq!\\;uC\ndW-\u0003\u0003\u0003\u0018\tE!a\u0002\"vS2$WM\u001d\t\u0004A\tmAA\u0002\u0012\u0003\u0006\t\u00071\u0005\u0005\u0003\u000b+\te\u0001bB \u0003\u0006\u0001\u000f!\u0011\u0005\t\u0005\u0005\u0016\u0013I\u0002C\u0004\u0003&-!\tEa\n\u0002\u00159,wOQ;jY\u0012,'/\u0006\u0003\u0003*\t=B\u0003\u0002B\u0016\u0005g\u0001\u0002Ba\u0004\u0003\u0016\t5\"\u0011\u0007\t\u0004A\t=BA\u0002\u0012\u0003$\t\u00071\u0005\u0005\u0003\u000b+\t5\u0002bB \u0003$\u0001\u000f!Q\u0007\t\u0005\u0005\u0016\u0013i\u0003C\u0004\u0002h-!\tA!\u000f\u0016\t\tm\"\u0011\t\u000b\u0005\u0005{\u0011\u0019\u0005\u0005\u0003\u000b+\t}\u0002c\u0001\u0011\u0003B\u00111!Ea\u000eC\u0002\rBqa\u0010B\u001c\u0001\b\u0011)\u0005\u0005\u0003C\u000b\n}\u0002\"\u0003B%\u0017\u0005\u0005I\u0011\u0002B&\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0005\t5\u0003c\u0001+\u0003P%\u0019!\u0011K+\u0003\r=\u0013'.Z2u\u0001")
public class TreeSet<A>
implements SortedSet<A>,
Serializable {
    public static final long serialVersionUID = -5685982407650748405L;
    private final RedBlackTree.Tree<A, BoxedUnit> tree;
    private final Ordering<A> ordering;

    public static <A> Builder<A, TreeSet<A>> implicitBuilder(Ordering<A> ordering) {
        return TreeSet$.MODULE$.implicitBuilder(ordering);
    }

    public static <A> CanBuildFrom<TreeSet<?>, A, TreeSet<A>> newCanBuildFrom(Ordering<A> ordering) {
        return TreeSet$.MODULE$.newCanBuildFrom(ordering);
    }

    @Override
    public /* synthetic */ boolean scala$collection$SortedSetLike$$super$subsetOf(GenSet that) {
        return GenSetLike$class.subsetOf(this, that);
    }

    @Override
    public scala.collection.SortedSet keySet() {
        return SortedSetLike$class.keySet(this);
    }

    @Override
    public boolean subsetOf(GenSet<A> that) {
        return SortedSetLike$class.subsetOf(this, that);
    }

    @Override
    public Iterator<A> iteratorFrom(A start) {
        return SortedSetLike$class.iteratorFrom(this, start);
    }

    @Override
    public int compare(A k0, A k1) {
        return Sorted$class.compare(this, k0, k1);
    }

    @Override
    public boolean hasAll(Iterator<A> j) {
        return Sorted$class.hasAll(this, j);
    }

    @Override
    public GenericCompanion<scala.collection.immutable.Set> companion() {
        return scala.collection.immutable.Set$class.companion(this);
    }

    @Override
    public <B> scala.collection.immutable.Set<B> toSet() {
        return scala.collection.immutable.Set$class.toSet(this);
    }

    @Override
    public scala.collection.immutable.Set<A> seq() {
        return scala.collection.immutable.Set$class.seq(this);
    }

    @Override
    public Combiner<A, ParSet<A>> parCombiner() {
        return scala.collection.immutable.Set$class.parCombiner(this);
    }

    @Override
    public /* synthetic */ Object scala$collection$SetLike$$super$map(Function1 f, CanBuildFrom bf) {
        return TraversableLike$class.map(this, f, bf);
    }

    @Override
    public Builder<A, TreeSet<A>> newBuilder() {
        return SetLike$class.newBuilder(this);
    }

    @Override
    public Seq<A> toSeq() {
        return SetLike$class.toSeq(this);
    }

    @Override
    public <A1> Buffer<A1> toBuffer() {
        return SetLike$class.toBuffer(this);
    }

    @Override
    public <B, That> That map(Function1<A, B> f, CanBuildFrom<TreeSet<A>, B, That> bf) {
        return (That)SetLike$class.map(this, f, bf);
    }

    @Override
    public Set $plus(Object elem1, Object elem2, Seq elems) {
        return SetLike$class.$plus(this, elem1, elem2, elems);
    }

    @Override
    public Set $plus$plus(GenTraversableOnce elems) {
        return SetLike$class.$plus$plus(this, elems);
    }

    @Override
    public boolean isEmpty() {
        return SetLike$class.isEmpty(this);
    }

    @Override
    public Set union(GenSet that) {
        return SetLike$class.union(this, that);
    }

    @Override
    public Set diff(GenSet that) {
        return SetLike$class.diff(this, that);
    }

    @Override
    public Iterator<TreeSet<A>> subsets(int len) {
        return SetLike$class.subsets(this, len);
    }

    @Override
    public Iterator<TreeSet<A>> subsets() {
        return SetLike$class.subsets(this);
    }

    @Override
    public String toString() {
        return SetLike$class.toString(this);
    }

    @Override
    public Subtractable $minus(Object elem1, Object elem2, Seq elems) {
        return Subtractable$class.$minus(this, elem1, elem2, elems);
    }

    @Override
    public Subtractable $minus$minus(GenTraversableOnce xs) {
        return Subtractable$class.$minus$minus(this, xs);
    }

    @Override
    public boolean apply(A elem) {
        return GenSetLike$class.apply(this, elem);
    }

    @Override
    public Object intersect(GenSet that) {
        return GenSetLike$class.intersect(this, that);
    }

    @Override
    public Object $amp(GenSet that) {
        return GenSetLike$class.$amp(this, that);
    }

    @Override
    public Object $bar(GenSet that) {
        return GenSetLike$class.$bar(this, that);
    }

    @Override
    public Object $amp$tilde(GenSet that) {
        return GenSetLike$class.$amp$tilde(this, that);
    }

    @Override
    public boolean equals(Object that) {
        return GenSetLike$class.equals(this, that);
    }

    @Override
    public int hashCode() {
        return GenSetLike$class.hashCode(this);
    }

    @Override
    public boolean apply$mcZD$sp(double v1) {
        return Function1$class.apply$mcZD$sp(this, v1);
    }

    @Override
    public double apply$mcDD$sp(double v1) {
        return Function1$class.apply$mcDD$sp(this, v1);
    }

    @Override
    public float apply$mcFD$sp(double v1) {
        return Function1$class.apply$mcFD$sp(this, v1);
    }

    @Override
    public int apply$mcID$sp(double v1) {
        return Function1$class.apply$mcID$sp(this, v1);
    }

    @Override
    public long apply$mcJD$sp(double v1) {
        return Function1$class.apply$mcJD$sp(this, v1);
    }

    @Override
    public void apply$mcVD$sp(double v1) {
        Function1$class.apply$mcVD$sp(this, v1);
    }

    @Override
    public boolean apply$mcZF$sp(float v1) {
        return Function1$class.apply$mcZF$sp(this, v1);
    }

    @Override
    public double apply$mcDF$sp(float v1) {
        return Function1$class.apply$mcDF$sp(this, v1);
    }

    @Override
    public float apply$mcFF$sp(float v1) {
        return Function1$class.apply$mcFF$sp(this, v1);
    }

    @Override
    public int apply$mcIF$sp(float v1) {
        return Function1$class.apply$mcIF$sp(this, v1);
    }

    @Override
    public long apply$mcJF$sp(float v1) {
        return Function1$class.apply$mcJF$sp(this, v1);
    }

    @Override
    public void apply$mcVF$sp(float v1) {
        Function1$class.apply$mcVF$sp(this, v1);
    }

    @Override
    public boolean apply$mcZI$sp(int v1) {
        return Function1$class.apply$mcZI$sp(this, v1);
    }

    @Override
    public double apply$mcDI$sp(int v1) {
        return Function1$class.apply$mcDI$sp(this, v1);
    }

    @Override
    public float apply$mcFI$sp(int v1) {
        return Function1$class.apply$mcFI$sp(this, v1);
    }

    @Override
    public int apply$mcII$sp(int v1) {
        return Function1$class.apply$mcII$sp(this, v1);
    }

    @Override
    public long apply$mcJI$sp(int v1) {
        return Function1$class.apply$mcJI$sp(this, v1);
    }

    @Override
    public void apply$mcVI$sp(int v1) {
        Function1$class.apply$mcVI$sp(this, v1);
    }

    @Override
    public boolean apply$mcZJ$sp(long v1) {
        return Function1$class.apply$mcZJ$sp(this, v1);
    }

    @Override
    public double apply$mcDJ$sp(long v1) {
        return Function1$class.apply$mcDJ$sp(this, v1);
    }

    @Override
    public float apply$mcFJ$sp(long v1) {
        return Function1$class.apply$mcFJ$sp(this, v1);
    }

    @Override
    public int apply$mcIJ$sp(long v1) {
        return Function1$class.apply$mcIJ$sp(this, v1);
    }

    @Override
    public long apply$mcJJ$sp(long v1) {
        return Function1$class.apply$mcJJ$sp(this, v1);
    }

    @Override
    public void apply$mcVJ$sp(long v1) {
        Function1$class.apply$mcVJ$sp(this, v1);
    }

    @Override
    public <A> Function1<A, Object> compose(Function1<A, A> g) {
        return Function1$class.compose(this, g);
    }

    @Override
    public <A> Function1<A, A> andThen(Function1<Object, A> g) {
        return Function1$class.andThen(this, g);
    }

    @Override
    public Iterable<A> thisCollection() {
        return IterableLike$class.thisCollection(this);
    }

    @Override
    public Iterable toCollection(Object repr) {
        return IterableLike$class.toCollection(this, repr);
    }

    @Override
    public boolean forall(Function1<A, Object> p) {
        return IterableLike$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<A, Object> p) {
        return IterableLike$class.exists(this, p);
    }

    @Override
    public Option<A> find(Function1<A, Object> p) {
        return IterableLike$class.find(this, p);
    }

    @Override
    public <B> B foldRight(B z, Function2<A, B, B> op) {
        return (B)IterableLike$class.foldRight(this, z, op);
    }

    @Override
    public <B> B reduceRight(Function2<A, B, B> op) {
        return (B)IterableLike$class.reduceRight(this, op);
    }

    @Override
    public Iterable<A> toIterable() {
        return IterableLike$class.toIterable(this);
    }

    @Override
    public Iterator<A> toIterator() {
        return IterableLike$class.toIterator(this);
    }

    @Override
    public Iterator<TreeSet<A>> grouped(int size2) {
        return IterableLike$class.grouped(this, size2);
    }

    @Override
    public Iterator<TreeSet<A>> sliding(int size2) {
        return IterableLike$class.sliding(this, size2);
    }

    @Override
    public Iterator<TreeSet<A>> sliding(int size2, int step) {
        return IterableLike$class.sliding(this, size2, step);
    }

    @Override
    public <B> void copyToArray(Object xs, int start, int len) {
        IterableLike$class.copyToArray(this, xs, start, len);
    }

    @Override
    public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<TreeSet<A>, Tuple2<A1, B>, That> bf) {
        return (That)IterableLike$class.zip(this, that, bf);
    }

    @Override
    public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<TreeSet<A>, Tuple2<A1, B>, That> bf) {
        return (That)IterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
    }

    @Override
    public <A1, That> That zipWithIndex(CanBuildFrom<TreeSet<A>, Tuple2<A1, Object>, That> bf) {
        return (That)IterableLike$class.zipWithIndex(this, bf);
    }

    @Override
    public <B> boolean sameElements(GenIterable<B> that) {
        return IterableLike$class.sameElements(this, that);
    }

    @Override
    public Stream<A> toStream() {
        return IterableLike$class.toStream(this);
    }

    @Override
    public boolean canEqual(Object that) {
        return IterableLike$class.canEqual(this, that);
    }

    @Override
    public Object view() {
        return IterableLike$class.view(this);
    }

    @Override
    public IterableView<A, TreeSet<A>> view(int from2, int until2) {
        return IterableLike$class.view(this, from2, until2);
    }

    @Override
    public <B> Builder<B, scala.collection.immutable.Set<B>> genericBuilder() {
        return GenericTraversableTemplate$class.genericBuilder(this);
    }

    @Override
    public <A1, A2> Tuple2<scala.collection.immutable.Set<A1>, scala.collection.immutable.Set<A2>> unzip(Function1<A, Tuple2<A1, A2>> asPair) {
        return GenericTraversableTemplate$class.unzip(this, asPair);
    }

    @Override
    public <A1, A2, A3> Tuple3<scala.collection.immutable.Set<A1>, scala.collection.immutable.Set<A2>, scala.collection.immutable.Set<A3>> unzip3(Function1<A, Tuple3<A1, A2, A3>> asTriple) {
        return GenericTraversableTemplate$class.unzip3(this, asTriple);
    }

    @Override
    public GenTraversable flatten(Function1 asTraversable) {
        return GenericTraversableTemplate$class.flatten(this, asTraversable);
    }

    @Override
    public GenTraversable transpose(Function1 asTraversable) {
        return GenericTraversableTemplate$class.transpose(this, asTraversable);
    }

    @Override
    public Object repr() {
        return TraversableLike$class.repr(this);
    }

    @Override
    public final boolean isTraversableAgain() {
        return TraversableLike$class.isTraversableAgain(this);
    }

    @Override
    public boolean hasDefiniteSize() {
        return TraversableLike$class.hasDefiniteSize(this);
    }

    @Override
    public <B, That> That $plus$plus(GenTraversableOnce<B> that, CanBuildFrom<TreeSet<A>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus(this, that, bf);
    }

    @Override
    public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<TreeSet<A>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
    }

    @Override
    public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<TreeSet<A>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
    }

    @Override
    public <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> f, CanBuildFrom<TreeSet<A>, B, That> bf) {
        return (That)TraversableLike$class.flatMap(this, f, bf);
    }

    @Override
    public Object filter(Function1 p) {
        return TraversableLike$class.filter(this, p);
    }

    @Override
    public Object filterNot(Function1 p) {
        return TraversableLike$class.filterNot(this, p);
    }

    @Override
    public <B, That> That collect(PartialFunction<A, B> pf, CanBuildFrom<TreeSet<A>, B, That> bf) {
        return (That)TraversableLike$class.collect(this, pf, bf);
    }

    @Override
    public Tuple2<TreeSet<A>, TreeSet<A>> partition(Function1<A, Object> p) {
        return TraversableLike$class.partition(this, p);
    }

    @Override
    public <K> Map<K, TreeSet<A>> groupBy(Function1<A, K> f) {
        return TraversableLike$class.groupBy(this, f);
    }

    @Override
    public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<TreeSet<A>, B, That> cbf) {
        return (That)TraversableLike$class.scan(this, z, op, cbf);
    }

    @Override
    public <B, That> That scanLeft(B z, Function2<B, A, B> op, CanBuildFrom<TreeSet<A>, B, That> bf) {
        return (That)TraversableLike$class.scanLeft(this, z, op, bf);
    }

    @Override
    public <B, That> That scanRight(B z, Function2<A, B, B> op, CanBuildFrom<TreeSet<A>, B, That> bf) {
        return (That)TraversableLike$class.scanRight(this, z, op, bf);
    }

    @Override
    public Object sliceWithKnownDelta(int from2, int until2, int delta) {
        return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
    }

    @Override
    public Object sliceWithKnownBound(int from2, int until2) {
        return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
    }

    @Override
    public Iterator<TreeSet<A>> tails() {
        return TraversableLike$class.tails(this);
    }

    @Override
    public Iterator<TreeSet<A>> inits() {
        return TraversableLike$class.inits(this);
    }

    @Override
    public Traversable<A> toTraversable() {
        return TraversableLike$class.toTraversable(this);
    }

    @Override
    public <Col> Col to(CanBuildFrom<Nothing$, A, Col> cbf) {
        return (Col)TraversableLike$class.to(this, cbf);
    }

    @Override
    public FilterMonadic<A, TreeSet<A>> withFilter(Function1<A, Object> p) {
        return TraversableLike$class.withFilter(this, p);
    }

    @Override
    public Parallel par() {
        return Parallelizable$class.par(this);
    }

    @Override
    public List<A> reversed() {
        return TraversableOnce$class.reversed(this);
    }

    @Override
    public boolean nonEmpty() {
        return TraversableOnce$class.nonEmpty(this);
    }

    @Override
    public int count(Function1<A, Object> p) {
        return TraversableOnce$class.count(this, p);
    }

    @Override
    public <B> Option<B> collectFirst(PartialFunction<A, B> pf) {
        return TraversableOnce$class.collectFirst(this, pf);
    }

    @Override
    public <B> B $div$colon(B z, Function2<B, A, B> op) {
        return (B)TraversableOnce$class.$div$colon(this, z, op);
    }

    @Override
    public <B> B $colon$bslash(B z, Function2<A, B, B> op) {
        return (B)TraversableOnce$class.$colon$bslash(this, z, op);
    }

    @Override
    public <B> B foldLeft(B z, Function2<B, A, B> op) {
        return (B)TraversableOnce$class.foldLeft(this, z, op);
    }

    @Override
    public <B> B reduceLeft(Function2<B, A, B> op) {
        return (B)TraversableOnce$class.reduceLeft(this, op);
    }

    @Override
    public <B> Option<B> reduceLeftOption(Function2<B, A, B> op) {
        return TraversableOnce$class.reduceLeftOption(this, op);
    }

    @Override
    public <B> Option<B> reduceRightOption(Function2<A, B, B> op) {
        return TraversableOnce$class.reduceRightOption(this, op);
    }

    @Override
    public <A1> A1 reduce(Function2<A1, A1, A1> op) {
        return (A1)TraversableOnce$class.reduce(this, op);
    }

    @Override
    public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
        return TraversableOnce$class.reduceOption(this, op);
    }

    @Override
    public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
        return (A1)TraversableOnce$class.fold(this, z, op);
    }

    @Override
    public <B> B aggregate(Function0<B> z, Function2<B, A, B> seqop, Function2<B, B, B> combop) {
        return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
    }

    @Override
    public <B> B sum(Numeric<B> num) {
        return (B)TraversableOnce$class.sum(this, num);
    }

    @Override
    public <B> B product(Numeric<B> num) {
        return (B)TraversableOnce$class.product(this, num);
    }

    @Override
    public <B> A min(Ordering<B> cmp) {
        return (A)TraversableOnce$class.min(this, cmp);
    }

    @Override
    public <B> A max(Ordering<B> cmp) {
        return (A)TraversableOnce$class.max(this, cmp);
    }

    @Override
    public <B> A maxBy(Function1<A, B> f, Ordering<B> cmp) {
        return (A)TraversableOnce$class.maxBy(this, f, cmp);
    }

    @Override
    public <B> A minBy(Function1<A, B> f, Ordering<B> cmp) {
        return (A)TraversableOnce$class.minBy(this, f, cmp);
    }

    @Override
    public <B> void copyToBuffer(Buffer<B> dest) {
        TraversableOnce$class.copyToBuffer(this, dest);
    }

    @Override
    public <B> void copyToArray(Object xs, int start) {
        TraversableOnce$class.copyToArray(this, xs, start);
    }

    @Override
    public <B> void copyToArray(Object xs) {
        TraversableOnce$class.copyToArray(this, xs);
    }

    @Override
    public <B> Object toArray(ClassTag<B> evidence$1) {
        return TraversableOnce$class.toArray(this, evidence$1);
    }

    @Override
    public List<A> toList() {
        return TraversableOnce$class.toList(this);
    }

    @Override
    public IndexedSeq<A> toIndexedSeq() {
        return TraversableOnce$class.toIndexedSeq(this);
    }

    @Override
    public Vector<A> toVector() {
        return TraversableOnce$class.toVector(this);
    }

    @Override
    public <T, U> Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> ev) {
        return TraversableOnce$class.toMap(this, ev);
    }

    @Override
    public String mkString(String start, String sep, String end) {
        return TraversableOnce$class.mkString(this, start, sep, end);
    }

    @Override
    public String mkString(String sep) {
        return TraversableOnce$class.mkString(this, sep);
    }

    @Override
    public String mkString() {
        return TraversableOnce$class.mkString(this);
    }

    @Override
    public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
        return TraversableOnce$class.addString(this, b, start, sep, end);
    }

    @Override
    public StringBuilder addString(StringBuilder b, String sep) {
        return TraversableOnce$class.addString(this, b, sep);
    }

    @Override
    public StringBuilder addString(StringBuilder b) {
        return TraversableOnce$class.addString(this, b);
    }

    @Override
    public Ordering<A> ordering() {
        return this.ordering;
    }

    @Override
    public String stringPrefix() {
        return "TreeSet";
    }

    @Override
    public int size() {
        return RedBlackTree$.MODULE$.count(this.tree);
    }

    @Override
    public A head() {
        return RedBlackTree$.MODULE$.smallest(this.tree).key();
    }

    @Override
    public Option<A> headOption() {
        return RedBlackTree$.MODULE$.isEmpty(this.tree) ? None$.MODULE$ : new Some<A>(this.head());
    }

    @Override
    public A last() {
        return RedBlackTree$.MODULE$.greatest(this.tree).key();
    }

    @Override
    public Option<A> lastOption() {
        return RedBlackTree$.MODULE$.isEmpty(this.tree) ? None$.MODULE$ : new Some<A>(this.last());
    }

    @Override
    public TreeSet<A> tail() {
        return new TreeSet<A>(RedBlackTree$.MODULE$.delete(this.tree, this.firstKey(), this.ordering()), this.ordering());
    }

    @Override
    public TreeSet<A> init() {
        return new TreeSet<A>(RedBlackTree$.MODULE$.delete(this.tree, this.lastKey(), this.ordering()), this.ordering());
    }

    @Override
    public TreeSet<A> drop(int n) {
        return n <= 0 ? this : (n >= this.size() ? this.empty() : this.newSet(RedBlackTree$.MODULE$.drop(this.tree, n, this.ordering())));
    }

    @Override
    public TreeSet<A> take(int n) {
        return n <= 0 ? this.empty() : (n >= this.size() ? this : this.newSet(RedBlackTree$.MODULE$.take(this.tree, n, this.ordering())));
    }

    @Override
    public TreeSet<A> slice(int from2, int until2) {
        return until2 <= from2 ? this.empty() : (from2 <= 0 ? this.take(until2) : (until2 >= this.size() ? this.drop(from2) : this.newSet(RedBlackTree$.MODULE$.slice(this.tree, from2, until2, this.ordering()))));
    }

    @Override
    public TreeSet<A> dropRight(int n) {
        return this.take(this.size() - package$.MODULE$.max(n, 0));
    }

    @Override
    public TreeSet<A> takeRight(int n) {
        return this.drop(this.size() - package$.MODULE$.max(n, 0));
    }

    @Override
    public Tuple2<TreeSet<A>, TreeSet<A>> splitAt(int n) {
        return new Tuple2<Object, Object>(this.take(n), this.drop(n));
    }

    /*
     * WARNING - void declaration
     */
    private int countWhile(Function1<A, Object> p) {
        void var2_2;
        int result2 = 0;
        Iterator<A> it = this.iterator();
        while (it.hasNext() && BoxesRunTime.unboxToBoolean(p.apply(it.next()))) {
            ++result2;
        }
        return (int)var2_2;
    }

    @Override
    public TreeSet<A> dropWhile(Function1<A, Object> p) {
        return this.drop(this.countWhile(p));
    }

    @Override
    public TreeSet<A> takeWhile(Function1<A, Object> p) {
        return this.take(this.countWhile(p));
    }

    @Override
    public Tuple2<TreeSet<A>, TreeSet<A>> span(Function1<A, Object> p) {
        return this.splitAt(this.countWhile(p));
    }

    private TreeSet<A> newSet(RedBlackTree.Tree<A, BoxedUnit> t) {
        return new TreeSet<A>(t, this.ordering());
    }

    @Override
    public TreeSet<A> empty() {
        return TreeSet$.MODULE$.empty((Ordering)this.ordering());
    }

    @Override
    public TreeSet<A> $plus(A elem) {
        return this.newSet(RedBlackTree$.MODULE$.update(this.tree, elem, BoxedUnit.UNIT, false, this.ordering()));
    }

    public TreeSet<A> insert(A elem) {
        Predef$.MODULE$.assert(!RedBlackTree$.MODULE$.contains(this.tree, elem, this.ordering()));
        return this.newSet(RedBlackTree$.MODULE$.update(this.tree, elem, BoxedUnit.UNIT, false, this.ordering()));
    }

    @Override
    public TreeSet<A> $minus(A elem) {
        return RedBlackTree$.MODULE$.contains(this.tree, elem, this.ordering()) ? this.newSet(RedBlackTree$.MODULE$.delete(this.tree, elem, this.ordering())) : this;
    }

    @Override
    public boolean contains(A elem) {
        return RedBlackTree$.MODULE$.contains(this.tree, elem, this.ordering());
    }

    @Override
    public Iterator<A> iterator() {
        return RedBlackTree$.MODULE$.keysIterator(this.tree, RedBlackTree$.MODULE$.keysIterator$default$2(), this.ordering());
    }

    @Override
    public Iterator<A> keysIteratorFrom(A start) {
        return RedBlackTree$.MODULE$.keysIterator(this.tree, new Some<A>(start), this.ordering());
    }

    @Override
    public <U> void foreach(Function1<A, U> f) {
        RedBlackTree$.MODULE$.foreachKey(this.tree, f);
    }

    @Override
    public TreeSet<A> rangeImpl(Option<A> from2, Option<A> until2) {
        return this.newSet(RedBlackTree$.MODULE$.rangeImpl(this.tree, from2, until2, this.ordering()));
    }

    @Override
    public TreeSet<A> range(A from2, A until2) {
        return this.newSet(RedBlackTree$.MODULE$.range(this.tree, from2, until2, this.ordering()));
    }

    @Override
    public TreeSet<A> from(A from2) {
        return this.newSet(RedBlackTree$.MODULE$.from(this.tree, from2, this.ordering()));
    }

    @Override
    public TreeSet<A> to(A to2) {
        return this.newSet(RedBlackTree$.MODULE$.to(this.tree, to2, this.ordering()));
    }

    @Override
    public TreeSet<A> until(A until2) {
        return this.newSet(RedBlackTree$.MODULE$.until(this.tree, until2, this.ordering()));
    }

    @Override
    public A firstKey() {
        return this.head();
    }

    @Override
    public A lastKey() {
        return this.last();
    }

    private TreeSet(RedBlackTree.Tree<A, BoxedUnit> tree, Ordering<A> ordering) {
        this.tree = tree;
        this.ordering = ordering;
        TraversableOnce$class.$init$(this);
        Parallelizable$class.$init$(this);
        TraversableLike$class.$init$(this);
        GenericTraversableTemplate$class.$init$(this);
        GenTraversable$class.$init$(this);
        Traversable$class.$init$(this);
        scala.collection.immutable.Traversable$class.$init$(this);
        GenIterable$class.$init$(this);
        IterableLike$class.$init$(this);
        Iterable$class.$init$(this);
        scala.collection.immutable.Iterable$class.$init$(this);
        Function1$class.$init$(this);
        GenSetLike$class.$init$(this);
        GenericSetTemplate$class.$init$(this);
        GenSet$class.$init$(this);
        Subtractable$class.$init$(this);
        SetLike$class.$init$(this);
        Set$class.$init$(this);
        scala.collection.immutable.Set$class.$init$(this);
        Sorted$class.$init$(this);
        SortedSetLike$class.$init$(this);
        scala.collection.SortedSet$class.$init$(this);
        SortedSet$class.$init$(this);
        if (ordering == null) {
            throw new NullPointerException("ordering must not be null");
        }
    }

    public TreeSet(Ordering<A> ordering) {
        this(null, ordering);
    }
}

