/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import java.util.NoSuchElementException;
import scala.Cloneable;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterator;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.GenTraversableOnce;
import scala.collection.IndexedSeq$class;
import scala.collection.IndexedSeqLike$class;
import scala.collection.IndexedSeqOptimized$class;
import scala.collection.IterableLike$class;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.SeqLike$class;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericOrderedTraversableTemplate;
import scala.collection.generic.GenericOrderedTraversableTemplate$class;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.immutable.List;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.mutable.AbstractIterable;
import scala.collection.mutable.AbstractSeq;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.IndexedSeq;
import scala.collection.mutable.IndexedSeqView;
import scala.collection.mutable.PriorityQueue$;
import scala.collection.mutable.Queue;
import scala.collection.mutable.ResizableArray;
import scala.collection.mutable.ResizableArray$class;
import scala.math.Ordering;
import scala.math.Ordering$class;
import scala.math.PartialOrdering$class;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\t%d\u0001B\u0001\u0003\u0001%\u0011Q\u0002\u0015:j_JLG/_)vKV,'BA\u0002\u0005\u0003\u001diW\u000f^1cY\u0016T!!\u0002\u0004\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001)\"AC\t\u0014\u0013\u0001Y1DH\u0013+[A\u001a\u0004c\u0001\u0007\u000e\u001f5\t!!\u0003\u0002\u000f\u0005\t\u0001\u0012IY:ue\u0006\u001cG/\u0013;fe\u0006\u0014G.\u001a\t\u0003!Ea\u0001\u0001B\u0003\u0013\u0001\t\u00071CA\u0001B#\t!\u0002\u0004\u0005\u0002\u0016-5\ta!\u0003\u0002\u0018\r\t9aj\u001c;iS:<\u0007CA\u000b\u001a\u0013\tQbAA\u0002B]f\u00042\u0001\u0004\u000f\u0010\u0013\ti\"A\u0001\u0005Ji\u0016\u0014\u0018M\u00197f!\u0011y\"e\u0004\u0013\u000e\u0003\u0001R!!\t\u0003\u0002\u000f\u001d,g.\u001a:jG&\u00111\u0005\t\u0002\"\u000f\u0016tWM]5d\u001fJ$WM]3e)J\fg/\u001a:tC\ndW\rV3na2\fG/\u001a\t\u0003\u0019\u0001\u0001BAJ\u0014\u0010S5\tA!\u0003\u0002)\t\ta\u0011\n^3sC\ndW\rT5lKB\u0019A\u0002A\b\u0011\u0007}Ys\"\u0003\u0002-A\tAqI]8xC\ndW\r\u0005\u0003\r]=I\u0013BA\u0018\u0003\u0005\u001d\u0011U/\u001b7eKJ\u0004\"!F\u0019\n\u0005I2!\u0001D*fe&\fG.\u001b>bE2,\u0007CA\u000b5\u0013\t)dAA\u0005DY>tW-\u00192mK\"Aq\u0007\u0001BC\u0002\u0013\r\u0001(A\u0002pe\u0012,\u0012!\u000f\t\u0004uuzaBA\u000b<\u0013\tad!A\u0004qC\u000e\\\u0017mZ3\n\u0005yz$\u0001C(sI\u0016\u0014\u0018N\\4\u000b\u0005q2\u0001\u0002C!\u0001\u0005\u0003\u0005\u000b\u0011B\u001d\u0002\t=\u0014H\r\t\u0005\u0006\u0007\u0002!\t\u0001R\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0015#\"!\u000b$\t\u000b]\u0012\u00059A\u001d\u0007\t!\u0003A!\u0013\u0002\u0015%\u0016\u001c\u0018N_1cY\u0016\f%O]1z\u0003\u000e\u001cWm]:\u0016\u0005){5\u0003B$L!B\u00022\u0001\u0004'O\u0013\ti%AA\u0006BEN$(/Y2u'\u0016\f\bC\u0001\tP\t\u0015\u0011rI1\u0001\u0014!\ra\u0011KT\u0005\u0003%\n\u0011aBU3tSj\f'\r\\3BeJ\f\u0017\u0010C\u0003D\u000f\u0012\u0005A\u000bF\u0001V!\r1vIT\u0007\u0002\u0001!)\u0001l\u0012C\u00013\u00069\u0001oX:ju\u0016\u0004T#\u0001.\u0011\u0005UY\u0016B\u0001/\u0007\u0005\rIe\u000e\u001e\u0005\u0006=\u001e#\taX\u0001\fa~\u001b\u0018N_31?\u0012*\u0017\u000f\u0006\u0002aGB\u0011Q#Y\u0005\u0003E\u001a\u0011A!\u00168ji\")A-\u0018a\u00015\u0006\t1\u000fC\u0003g\u000f\u0012\u0005q-A\u0004q?\u0006\u0014(/Y=\u0016\u0003!\u00042!F5l\u0013\tQgAA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0016Y&\u0011QN\u0002\u0002\u0007\u0003:L(+\u001a4\t\u000b=<E\u0011\u00019\u0002\u0019A|VM\\:ve\u0016\u001c\u0016N_3\u0015\u0005\u0001\f\b\"\u0002:o\u0001\u0004Q\u0016!\u00018\t\u000bQ<E\u0011A;\u0002\rA|6o^1q)\r\u0001g\u000f\u001f\u0005\u0006oN\u0004\rAW\u0001\u0002C\")\u0011p\u001da\u00015\u0006\t!\r\u0003\u0004|\u0001\u0001&\t\u0006`\u0001\u000b]\u0016<()^5mI\u0016\u0014X#A\u0015\t\u000fy\u0004!\u0019!C\u0005\u007f\u00061!/Z:beJ,\"!!\u0001\u0011\u0007Y;u\u0002\u0003\u0005\u0002\u0006\u0001\u0001\u000b\u0011BA\u0001\u0003\u001d\u0011Xm]1se\u0002Ba!!\u0003\u0001\t\u0003I\u0016A\u00027f]\u001e$\b\u000e\u0003\u0004\u0002\u000e\u0001!\t%W\u0001\u0005g&TX\rC\u0004\u0002\u0012\u0001!\t%a\u0005\u0002\u000f%\u001cX)\u001c9usV\u0011\u0011Q\u0003\t\u0004+\u0005]\u0011bAA\r\r\t9!i\\8mK\u0006t\u0007BBA\u000f\u0001\u0011\u0005C0\u0001\u0003sKB\u0014\bbBA\u0011\u0001\u0011\u0005\u00111E\u0001\u0007e\u0016\u001cX\u000f\u001c;\u0015\u0003%Bq!a\n\u0001\t\u0003\nI#\u0001\tpe\u0012,'/\u001a3D_6\u0004\u0018M\\5p]V\u0011\u00111\u0006\b\u0004\u0019\u00055raBA\u0018\u0005!\u0005\u0011\u0011G\u0001\u000e!JLwN]5usF+X-^3\u0011\u00071\t\u0019D\u0002\u0004\u0002\u0005!\u0005\u0011QG\n\u0006\u0003g\t9\u0004\r\t\u0005?\u0005eB%C\u0002\u0002<\u0001\u0012\u0011d\u0014:eKJ,G\r\u0016:bm\u0016\u00148/\u00192mK\u001a\u000b7\r^8ss\"91)a\r\u0005\u0002\u0005}BCAA\u0019\u0011\u001dY\u00181\u0007C\u0001\u0003\u0007*B!!\u0012\u0002LQ!\u0011qIA'!\u0011a\u0001!!\u0013\u0011\u0007A\tY\u0005\u0002\u0004\u0013\u0003\u0003\u0012\ra\u0005\u0005\bo\u0005\u0005\u00039AA(!\u0011QT(!\u0013\t\u0011\u0005M\u00131\u0007C\u0002\u0003+\nAbY1o\u0005VLG\u000e\u001a$s_6,B!a\u0016\u0002nQ!\u0011\u0011LA9!%y\u00121LA0\u0003W\ny'C\u0002\u0002^\u0001\u0012AbQ1o\u0005VLG\u000e\u001a$s_6\u0004B!!\u0019\u0002d5\u0011\u00111G\u0005\u0005\u0003K\n9G\u0001\u0003D_2d\u0017bAA5A\t9r)\u001a8fe&\u001cwJ\u001d3fe\u0016$7i\\7qC:LwN\u001c\t\u0004!\u00055DA\u0002\n\u0002R\t\u00071\u0003\u0005\u0003\r\u0001\u0005-\u0004bB\u001c\u0002R\u0001\u000f\u00111\u000f\t\u0005uu\nY\u0007\u0003\u0006\u0002x\u0005M\u0012\u0011!C\u0005\u0003s\n1B]3bIJ+7o\u001c7wKR\u0011\u00111\u0010\t\u0005\u0003{\n9)\u0004\u0002\u0002\u0000)!\u0011\u0011QAB\u0003\u0011a\u0017M\\4\u000b\u0005\u0005\u0015\u0015\u0001\u00026bm\u0006LA!!#\u0002\u0000\t1qJ\u00196fGRDq!!$\u0001\t\u0013\ty)A\u0002u_\u0006#2aDAI\u0011\u001d\t\u0019*a#A\u0002-\f\u0011\u0001\u001f\u0005\b\u0003/\u0003A\u0011CAM\u0003\u00151\u0017\u000e_+q)\u0015\u0001\u00171TAP\u0011\u001d\ti*!&A\u0002!\f!!Y:\t\u000f\u0005\u0005\u0016Q\u0013a\u00015\u0006\tQ\u000eC\u0004\u0002&\u0002!\t\"a*\u0002\u000f\u0019L\u0007\u0010R8x]R9\u0001-!+\u0002,\u00065\u0006bBAO\u0003G\u0003\r\u0001\u001b\u0005\b\u0003C\u000b\u0019\u000b1\u0001[\u0011\u0019\u0011\u00181\u0015a\u00015\"9\u0011\u0011\u0017\u0001\u0005\u0002\u0005M\u0016\u0001\u0003\u0013qYV\u001cH%Z9\u0015\u0007Y\u000b)\fC\u0004\u00028\u0006=\u0006\u0019A\b\u0002\t\u0015dW-\u001c\u0005\b\u0003w\u0003A\u0011AA_\u0003)!\u0003\u000f\\;tIAdWo\u001d\u000b\u0004S\u0005}\u0006\u0002CAa\u0003s\u0003\r!a1\u0002\u0005a\u001c\b\u0003\u0002\u0014\u0002F>I1!a2\u0005\u0005I9UM\u001c+sCZ,'o]1cY\u0016|enY3\t\u000f\u0005-\u0007\u0001\"\u0001\u0002N\u00069QM\\9vKV,Gc\u00011\u0002P\"A\u0011\u0011[Ae\u0001\u0004\t\u0019.A\u0003fY\u0016l7\u000f\u0005\u0003\u0016\u0003+|\u0011bAAl\r\tQAH]3qK\u0006$X\r\u001a \t\u000f\u0005m\u0007\u0001\"\u0001\u0002^\u00069A-Z9vKV,G#A\b\t\u000f\u0005\u0005\b\u0001\"\u0001\u0002d\u0006QA-Z9vKV,\u0017\t\u001c7\u0016\r\u0005\u0015\u0018Q`Au)\u0011\t9/!<\u0011\u0007A\tI\u000fB\u0004\u0002l\u0006}'\u0019A\n\u0003\tQC\u0017\r\u001e\u0005\t\u0003_\fy\u000eq\u0001\u0002r\u0006\u0011!M\u001a\u0019\u0005\u0003g\f9\u0010E\u0005 \u00037\n)0a?\u0002hB\u0019\u0001#a>\u0005\u0017\u0005e\u0018Q^A\u0001\u0002\u0003\u0015\ta\u0005\u0002\u0004?\u0012\n\u0004c\u0001\t\u0002~\u0012A\u0011q`Ap\u0005\u0004\u0011\tA\u0001\u0002BcE\u0011q\u0002\u0007\u0005\b\u0005\u000b\u0001A\u0011\tB\u0004\u0003\u0011AW-\u00193\u0016\u0003=AqAa\u0003\u0001\t\u0003\u0011i!A\u0003dY\u0016\f'\u000fF\u0001a\u0011\u001d\u0011\t\u0002\u0001C!\u0005'\t\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0005+\u0001BA\nB\f\u001f%\u0019!\u0011\u0004\u0003\u0003\u0011%#XM]1u_JDaA!\b\u0001\t\u0003a\u0018a\u0002:fm\u0016\u00148/\u001a\u0005\b\u0005C\u0001A\u0011\u0001B\n\u0003=\u0011XM^3sg\u0016LE/\u001a:bi>\u0014\bb\u0002B\u0013\u0001\u0011\u0005#qE\u0001\tQ\u0006\u001c\bnQ8eKR\t!\fC\u0004\u0003,\u0001!\tA!\f\u0002\u000fQ|\u0017+^3vKV\u0011!q\u0006\t\u0005\u0019\tEr\"C\u0002\u00034\t\u0011Q!U;fk\u0016DqAa\u000e\u0001\t\u0003\u0012I$\u0001\u0005u_N#(/\u001b8h)\t\u0011Y\u0004\u0005\u0003\u0003>\t\rcbA\u000b\u0003@%\u0019!\u0011\t\u0004\u0002\rA\u0013X\rZ3g\u0013\u0011\u0011)Ea\u0012\u0003\rM#(/\u001b8h\u0015\r\u0011\tE\u0002\u0005\b\u0005\u0017\u0002A\u0011\tB'\u0003\u0019!x\u000eT5tiV\u0011!q\n\t\u0005u\tEs\"C\u0002\u0003T}\u0012A\u0001T5ti\"9!q\u000b\u0001\u0005B\u0005\r\u0012!B2m_:,\u0007f\u0002\u0001\u0003\\\t\u0005$Q\r\t\u0004+\tu\u0013b\u0001B0\r\t)B-\u001a9sK\u000e\fG/\u001a3J]\",'/\u001b;b]\u000e,\u0017E\u0001B2\u0003}\u0003&/[8sSRL\u0018+^3vK\u0002J7\u000f\t8pi\u0002Jg\u000e^3oI\u0016$\u0007\u0005^8!E\u0016\u00043/\u001e2dY\u0006\u001c8/\u001a3!IV,\u0007\u0005^8!Kb$XM\\:jm\u0016\u0004\u0003O]5wCR,\u0007%[7qY\u0016lWM\u001c;bi&|g\u000e\t3fi\u0006LGn\u001d\u0018\"\u0005\t\u001d\u0014A\u0002\u001a/cEr\u0003\u0007")
public class PriorityQueue<A>
extends AbstractIterable<A>
implements GenericOrderedTraversableTemplate<A, PriorityQueue>,
Builder<A, PriorityQueue<A>>,
Serializable,
Cloneable {
    private final Ordering<A> ord;
    private final ResizableArrayAccess<A> scala$collection$mutable$PriorityQueue$$resarr;

    public static <A> CanBuildFrom<PriorityQueue<?>, A, PriorityQueue<A>> canBuildFrom(Ordering<A> ordering) {
        return PriorityQueue$.MODULE$.canBuildFrom(ordering);
    }

    public static Traversable apply(Seq seq, Ordering ordering) {
        return PriorityQueue$.MODULE$.apply(seq, ordering);
    }

    public static Traversable empty(Ordering ordering) {
        return PriorityQueue$.MODULE$.empty(ordering);
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
    public <NewTo> Builder<A, NewTo> mapResult(Function1<PriorityQueue<A>, NewTo> f) {
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
    public <B> Builder<B, PriorityQueue<B>> genericOrderedBuilder(Ordering<B> ord) {
        return GenericOrderedTraversableTemplate$class.genericOrderedBuilder(this, ord);
    }

    @Override
    public Ordering<A> ord() {
        return this.ord;
    }

    public PriorityQueue<A> newBuilder() {
        return new PriorityQueue<A>(this.ord());
    }

    public ResizableArrayAccess<A> scala$collection$mutable$PriorityQueue$$resarr() {
        return this.scala$collection$mutable$PriorityQueue$$resarr;
    }

    public int length() {
        return this.scala$collection$mutable$PriorityQueue$$resarr().length() - 1;
    }

    @Override
    public int size() {
        return this.length();
    }

    @Override
    public boolean isEmpty() {
        return this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() < 2;
    }

    @Override
    public PriorityQueue<A> repr() {
        return this;
    }

    @Override
    public PriorityQueue<A> result() {
        return this;
    }

    public PriorityQueue$ orderedCompanion() {
        return PriorityQueue$.MODULE$;
    }

    public A scala$collection$mutable$PriorityQueue$$toA(Object x) {
        return (A)x;
    }

    public void fixUp(Object[] as, int m) {
        for (int k = m; k > 1 && this.ord().mkOrderingOps(this.scala$collection$mutable$PriorityQueue$$toA(as[k / 2])).$less(this.scala$collection$mutable$PriorityQueue$$toA(as[k])); k /= 2) {
            this.scala$collection$mutable$PriorityQueue$$resarr().p_swap(k, k / 2);
        }
    }

    public void fixDown(Object[] as, int m, int n) {
        int k = m;
        while (n >= 2 * k) {
            int j = 2 * k;
            if (j < n && this.ord().mkOrderingOps(this.scala$collection$mutable$PriorityQueue$$toA(as[j])).$less(this.scala$collection$mutable$PriorityQueue$$toA(as[j + 1]))) {
                ++j;
            }
            if (this.ord().mkOrderingOps(this.scala$collection$mutable$PriorityQueue$$toA(as[k])).$greater$eq(this.scala$collection$mutable$PriorityQueue$$toA(as[j]))) {
                return;
            }
            Object h = as[k];
            as[k] = as[j];
            as[j] = h;
            k = j;
        }
    }

    @Override
    public PriorityQueue<A> $plus$eq(A elem) {
        this.scala$collection$mutable$PriorityQueue$$resarr().p_ensureSize(this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() + 1);
        this.scala$collection$mutable$PriorityQueue$$resarr().p_array()[this.scala$collection$mutable$PriorityQueue$$resarr().p_size0()] = elem;
        this.fixUp(this.scala$collection$mutable$PriorityQueue$$resarr().p_array(), this.scala$collection$mutable$PriorityQueue$$resarr().p_size0());
        this.scala$collection$mutable$PriorityQueue$$resarr().p_size0_$eq(this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() + 1);
        return this;
    }

    public PriorityQueue<A> $plus$plus(GenTraversableOnce<A> xs) {
        return (PriorityQueue)((PriorityQueue)this.clone()).$plus$plus$eq(xs.seq());
    }

    public void enqueue(Seq<A> elems) {
        this.$plus$plus$eq(elems);
    }

    public A dequeue() {
        if (this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() > 1) {
            this.scala$collection$mutable$PriorityQueue$$resarr().p_size0_$eq(this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() - 1);
            this.scala$collection$mutable$PriorityQueue$$resarr().p_swap(1, this.scala$collection$mutable$PriorityQueue$$resarr().p_size0());
            this.fixDown(this.scala$collection$mutable$PriorityQueue$$resarr().p_array(), 1, this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() - 1);
            return this.scala$collection$mutable$PriorityQueue$$toA(this.scala$collection$mutable$PriorityQueue$$resarr().p_array()[this.scala$collection$mutable$PriorityQueue$$resarr().p_size0()]);
        }
        throw new NoSuchElementException("no element to remove from heap");
    }

    public <A1, That> That dequeueAll(CanBuildFrom<?, A1, That> bf) {
        Builder<A1, That> b = bf.apply();
        while (this.nonEmpty()) {
            b.$plus$eq((A1)this.dequeue());
        }
        return b.result();
    }

    @Override
    public A head() {
        if (this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() > 1) {
            return this.scala$collection$mutable$PriorityQueue$$toA(this.scala$collection$mutable$PriorityQueue$$resarr().p_array()[1]);
        }
        throw new NoSuchElementException("queue is empty");
    }

    @Override
    public void clear() {
        this.scala$collection$mutable$PriorityQueue$$resarr().p_size0_$eq(1);
    }

    @Override
    public Iterator<A> iterator() {
        return new AbstractIterator<A>(this){
            private int i;
            private final /* synthetic */ PriorityQueue $outer;

            private int i() {
                return this.i;
            }

            private void i_$eq(int x$1) {
                this.i = x$1;
            }

            public boolean hasNext() {
                return this.i() < this.$outer.scala$collection$mutable$PriorityQueue$$resarr().p_size0();
            }

            public A next() {
                Object n = this.$outer.scala$collection$mutable$PriorityQueue$$resarr().p_array()[this.i()];
                this.i_$eq(this.i() + 1);
                return this.$outer.scala$collection$mutable$PriorityQueue$$toA(n);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.i = 1;
            }
        };
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public PriorityQueue<A> reverse() {
        PriorityQueue<A> revq = new PriorityQueue<A>(new Ordering<A>(this){
            private final /* synthetic */ PriorityQueue $outer;

            public Some<Object> tryCompare(A x, A y) {
                return Ordering$class.tryCompare(this, x, y);
            }

            public boolean lteq(A x, A y) {
                return Ordering$class.lteq(this, x, y);
            }

            public boolean gteq(A x, A y) {
                return Ordering$class.gteq(this, x, y);
            }

            public boolean lt(A x, A y) {
                return Ordering$class.lt(this, x, y);
            }

            public boolean gt(A x, A y) {
                return Ordering$class.gt(this, x, y);
            }

            public boolean equiv(A x, A y) {
                return Ordering$class.equiv(this, x, y);
            }

            public A max(A x, A y) {
                return (A)Ordering$class.max(this, x, y);
            }

            public A min(A x, A y) {
                return (A)Ordering$class.min(this, x, y);
            }

            public Ordering<A> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, A> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(A lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public int compare(A x, A y) {
                return this.$outer.ord().compare(y, x);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        });
        Predef$ predef$ = Predef$.MODULE$;
        int n = this.scala$collection$mutable$PriorityQueue$$resarr().length();
        Range$ range$ = Range$.MODULE$;
        Range range2 = new Range(1, n, 1);
        if (range2.isEmpty()) return revq;
        int i1 = range2.start();
        while (true) {
            int n2 = i1;
            revq.$plus$eq((Object)this.scala$collection$mutable$PriorityQueue$$resarr().apply(n2));
            if (i1 == range2.lastElement()) {
                return revq;
            }
            i1 += range2.step();
        }
    }

    public Iterator<A> reverseIterator() {
        return new AbstractIterator<A>(this){
            private int i;
            private final /* synthetic */ PriorityQueue $outer;

            private int i() {
                return this.i;
            }

            private void i_$eq(int x$1) {
                this.i = x$1;
            }

            public boolean hasNext() {
                return this.i() >= 1;
            }

            public A next() {
                Object n = this.$outer.scala$collection$mutable$PriorityQueue$$resarr().p_array()[this.i()];
                this.i_$eq(this.i() - 1);
                return this.$outer.scala$collection$mutable$PriorityQueue$$toA(n);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.i = $outer.scala$collection$mutable$PriorityQueue$$resarr().p_size0() - 1;
            }
        };
    }

    public int hashCode() {
        throw new UnsupportedOperationException("unsuitable as hash key");
    }

    public Queue<A> toQueue() {
        return (Queue)new Queue<A>().$plus$plus$eq(this.iterator());
    }

    @Override
    public String toString() {
        return this.toList().mkString("PriorityQueue(", ", ", ")");
    }

    @Override
    public List<A> toList() {
        return this.iterator().toList();
    }

    public PriorityQueue<A> clone() {
        return (PriorityQueue)new PriorityQueue<A>(this.ord()).$plus$plus$eq(this.iterator());
    }

    public PriorityQueue(Ordering<A> ord) {
        this.ord = ord;
        GenericOrderedTraversableTemplate$class.$init$(this);
        Growable$class.$init$(this);
        Builder$class.$init$(this);
        this.scala$collection$mutable$PriorityQueue$$resarr = new ResizableArrayAccess(this);
        this.scala$collection$mutable$PriorityQueue$$resarr().p_size0_$eq(this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() + 1);
    }

    public static class ResizableArrayAccess<A>
    extends AbstractSeq<A>
    implements ResizableArray<A>,
    Serializable {
        public final /* synthetic */ PriorityQueue $outer;
        private Object[] array;
        private int size0;

        @Override
        public Object[] array() {
            return this.array;
        }

        @Override
        public void array_$eq(Object[] x$1) {
            this.array = x$1;
        }

        @Override
        public int size0() {
            return this.size0;
        }

        @Override
        public void size0_$eq(int x$1) {
            this.size0 = x$1;
        }

        @Override
        public GenericCompanion<ResizableArray> companion() {
            return ResizableArray$class.companion(this);
        }

        @Override
        public int initialSize() {
            return ResizableArray$class.initialSize(this);
        }

        @Override
        public int length() {
            return ResizableArray$class.length(this);
        }

        @Override
        public A apply(int idx) {
            return (A)ResizableArray$class.apply(this, idx);
        }

        @Override
        public void update(int idx, A elem) {
            ResizableArray$class.update(this, idx, elem);
        }

        @Override
        public <U> void foreach(Function1<A, U> f) {
            ResizableArray$class.foreach(this, f);
        }

        @Override
        public <B> void copyToArray(Object xs, int start, int len) {
            ResizableArray$class.copyToArray(this, xs, start, len);
        }

        @Override
        public void reduceToSize(int sz) {
            ResizableArray$class.reduceToSize(this, sz);
        }

        @Override
        public void ensureSize(int n) {
            ResizableArray$class.ensureSize(this, n);
        }

        @Override
        public void swap(int a, int b) {
            ResizableArray$class.swap(this, a, b);
        }

        @Override
        public void copy(int m, int n, int len) {
            ResizableArray$class.copy(this, m, n, len);
        }

        @Override
        public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$reduceLeft(Function2 op) {
            return TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$reduceRight(Function2 op) {
            return IterableLike$class.reduceRight(this, op);
        }

        @Override
        public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$zip(GenIterable that, CanBuildFrom bf) {
            return IterableLike$class.zip(this, that, bf);
        }

        @Override
        public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$head() {
            return IterableLike$class.head(this);
        }

        @Override
        public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$tail() {
            return TraversableLike$class.tail(this);
        }

        @Override
        public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$last() {
            return TraversableLike$class.last(this);
        }

        @Override
        public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$init() {
            return TraversableLike$class.init(this);
        }

        @Override
        public /* synthetic */ boolean scala$collection$IndexedSeqOptimized$$super$sameElements(GenIterable that) {
            return IterableLike$class.sameElements(this, that);
        }

        @Override
        public /* synthetic */ boolean scala$collection$IndexedSeqOptimized$$super$endsWith(GenSeq that) {
            return SeqLike$class.endsWith(this, that);
        }

        @Override
        public boolean isEmpty() {
            return IndexedSeqOptimized$class.isEmpty(this);
        }

        @Override
        public boolean forall(Function1<A, Object> p) {
            return IndexedSeqOptimized$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<A, Object> p) {
            return IndexedSeqOptimized$class.exists(this, p);
        }

        @Override
        public Option<A> find(Function1<A, Object> p) {
            return IndexedSeqOptimized$class.find(this, p);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, A, B> op) {
            return (B)IndexedSeqOptimized$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B foldRight(B z, Function2<A, B, B> op) {
            return (B)IndexedSeqOptimized$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, A, B> op) {
            return (B)IndexedSeqOptimized$class.reduceLeft(this, op);
        }

        @Override
        public <B> B reduceRight(Function2<A, B, B> op) {
            return (B)IndexedSeqOptimized$class.reduceRight(this, op);
        }

        @Override
        public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<ResizableArray<A>, Tuple2<A1, B>, That> bf) {
            return (That)IndexedSeqOptimized$class.zip(this, that, bf);
        }

        @Override
        public <A1, That> That zipWithIndex(CanBuildFrom<ResizableArray<A>, Tuple2<A1, Object>, That> bf) {
            return (That)IndexedSeqOptimized$class.zipWithIndex(this, bf);
        }

        @Override
        public Object slice(int from2, int until2) {
            return IndexedSeqOptimized$class.slice(this, from2, until2);
        }

        @Override
        public A head() {
            return (A)IndexedSeqOptimized$class.head(this);
        }

        @Override
        public Object tail() {
            return IndexedSeqOptimized$class.tail(this);
        }

        @Override
        public A last() {
            return (A)IndexedSeqOptimized$class.last(this);
        }

        @Override
        public Object init() {
            return IndexedSeqOptimized$class.init(this);
        }

        @Override
        public Object take(int n) {
            return IndexedSeqOptimized$class.take(this, n);
        }

        @Override
        public Object drop(int n) {
            return IndexedSeqOptimized$class.drop(this, n);
        }

        @Override
        public Object takeRight(int n) {
            return IndexedSeqOptimized$class.takeRight(this, n);
        }

        @Override
        public Object dropRight(int n) {
            return IndexedSeqOptimized$class.dropRight(this, n);
        }

        @Override
        public Tuple2<ResizableArray<A>, ResizableArray<A>> splitAt(int n) {
            return IndexedSeqOptimized$class.splitAt(this, n);
        }

        @Override
        public Object takeWhile(Function1 p) {
            return IndexedSeqOptimized$class.takeWhile(this, p);
        }

        @Override
        public Object dropWhile(Function1 p) {
            return IndexedSeqOptimized$class.dropWhile(this, p);
        }

        @Override
        public Tuple2<ResizableArray<A>, ResizableArray<A>> span(Function1<A, Object> p) {
            return IndexedSeqOptimized$class.span(this, p);
        }

        @Override
        public <B> boolean sameElements(GenIterable<B> that) {
            return IndexedSeqOptimized$class.sameElements(this, that);
        }

        @Override
        public int lengthCompare(int len) {
            return IndexedSeqOptimized$class.lengthCompare(this, len);
        }

        @Override
        public int segmentLength(Function1<A, Object> p, int from2) {
            return IndexedSeqOptimized$class.segmentLength(this, p, from2);
        }

        @Override
        public int indexWhere(Function1<A, Object> p, int from2) {
            return IndexedSeqOptimized$class.indexWhere(this, p, from2);
        }

        @Override
        public int lastIndexWhere(Function1<A, Object> p, int end) {
            return IndexedSeqOptimized$class.lastIndexWhere(this, p, end);
        }

        @Override
        public Object reverse() {
            return IndexedSeqOptimized$class.reverse(this);
        }

        @Override
        public Iterator<A> reverseIterator() {
            return IndexedSeqOptimized$class.reverseIterator(this);
        }

        @Override
        public <B> boolean startsWith(GenSeq<B> that, int offset) {
            return IndexedSeqOptimized$class.startsWith(this, that, offset);
        }

        @Override
        public <B> boolean endsWith(GenSeq<B> that) {
            return IndexedSeqOptimized$class.endsWith(this, that);
        }

        @Override
        public IndexedSeq<A> seq() {
            return scala.collection.mutable.IndexedSeq$class.seq(this);
        }

        @Override
        public IndexedSeq<A> thisCollection() {
            return scala.collection.mutable.IndexedSeqLike$class.thisCollection(this);
        }

        @Override
        public IndexedSeq toCollection(Object repr) {
            return scala.collection.mutable.IndexedSeqLike$class.toCollection(this, repr);
        }

        @Override
        public Object view() {
            return scala.collection.mutable.IndexedSeqLike$class.view(this);
        }

        @Override
        public IndexedSeqView<A, ResizableArray<A>> view(int from2, int until2) {
            return scala.collection.mutable.IndexedSeqLike$class.view(this, from2, until2);
        }

        @Override
        public int hashCode() {
            return IndexedSeqLike$class.hashCode(this);
        }

        @Override
        public Iterator<A> iterator() {
            return IndexedSeqLike$class.iterator(this);
        }

        @Override
        public <A1> Buffer<A1> toBuffer() {
            return IndexedSeqLike$class.toBuffer(this);
        }

        public int p_size0() {
            return this.size0();
        }

        public void p_size0_$eq(int s2) {
            this.size0_$eq(s2);
        }

        public Object[] p_array() {
            return this.array();
        }

        public void p_ensureSize(int n) {
            ResizableArray$class.ensureSize(this, n);
        }

        public void p_swap(int a, int b) {
            ResizableArray$class.swap(this, a, b);
        }

        public /* synthetic */ PriorityQueue scala$collection$mutable$PriorityQueue$ResizableArrayAccess$$$outer() {
            return this.$outer;
        }

        public ResizableArrayAccess(PriorityQueue<A> $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            IndexedSeqLike$class.$init$(this);
            IndexedSeq$class.$init$(this);
            scala.collection.mutable.IndexedSeqLike$class.$init$(this);
            scala.collection.mutable.IndexedSeq$class.$init$(this);
            IndexedSeqOptimized$class.$init$(this);
            ResizableArray$class.$init$(this);
        }
    }
}

