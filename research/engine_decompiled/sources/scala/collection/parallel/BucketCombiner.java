/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import java.io.Serializable;
import scala.Function1;
import scala.collection.Seq;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.UnrolledBuffer;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.Combiner$class;
import scala.collection.parallel.TaskSupport;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.TraitSetter;
import scala.sys.package$;

@ScalaSignature(bytes="\u0006\u0001\u0005-aAB\u0001\u0003\u0003\u0003\u0011\u0001B\u0001\bCk\u000e\\W\r^\"p[\nLg.\u001a:\u000b\u0005\r!\u0011\u0001\u00039be\u0006dG.\u001a7\u000b\u0005\u00151\u0011AC2pY2,7\r^5p]*\tq!A\u0003tG\u0006d\u0017-F\u0003\n)}q\u0013gE\u0002\u0001\u00159\u0001\"a\u0003\u0007\u000e\u0003\u0019I!!\u0004\u0004\u0003\r\u0005s\u0017PU3g!\u0011y\u0001C\u0005\u0010\u000e\u0003\tI!!\u0005\u0002\u0003\u0011\r{WNY5oKJ\u0004\"a\u0005\u000b\r\u0001\u00111Q\u0003\u0001EC\u0002]\u0011A!\u00127f[\u000e\u0001\u0011C\u0001\r\u001c!\tY\u0011$\u0003\u0002\u001b\r\t9aj\u001c;iS:<\u0007CA\u0006\u001d\u0013\tibAA\u0002B]f\u0004\"aE\u0010\u0005\r\u0001\u0002AQ1\u0001\u0018\u0005\t!v\u000e\u0003\u0005#\u0001\t\u0015\r\u0011\"\u0003$\u00031\u0011WoY6fi:,XNY3s+\u0005!\u0003CA\u0006&\u0013\t1cAA\u0002J]RD\u0001\u0002\u000b\u0001\u0003\u0002\u0003\u0006I\u0001J\u0001\u000eEV\u001c7.\u001a;ok6\u0014WM\u001d\u0011\t\u000b)\u0002A\u0011A\u0016\u0002\rqJg.\u001b;?)\taC\u0007\u0005\u0004\u0010\u0001IqR\u0006\r\t\u0003'9\"Qa\f\u0001C\u0002]\u0011AAQ;dWB\u00111#\r\u0003\u0007e\u0001!)\u0019A\u001a\u0003\u0019\r{WNY5oKJ$\u0016\u0010]3\u0012\u0005aa\u0003\"\u0002\u0012*\u0001\u0004!\u0003b\u0002\u001c\u0001\u0001\u0004%\tbN\u0001\bEV\u001c7.\u001a;t+\u0005A$FA\u001dC!\rY!\bP\u0005\u0003w\u0019\u0011Q!\u0011:sCf\u00042!\u0010!.\u001b\u0005q$BA \u0005\u0003\u001diW\u000f^1cY\u0016L!!\u0011 \u0003\u001dUs'o\u001c7mK\u0012\u0014UO\u001a4fe.\n1\t\u0005\u0002E\u00136\tQI\u0003\u0002G\u000f\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003\u0011\u001a\t!\"\u00198o_R\fG/[8o\u0013\tQUIA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016Dq\u0001\u0014\u0001A\u0002\u0013EQ*A\u0006ck\u000e\\W\r^:`I\u0015\fHC\u0001(R!\tYq*\u0003\u0002Q\r\t!QK\\5u\u0011\u001d\u00116*!AA\u0002a\n1\u0001\u001f\u00132\u0011\u0019!\u0006\u0001)Q\u0005q\u0005A!-^2lKR\u001c\b\u0005C\u0004W\u0001\u0001\u0007I\u0011C\u0012\u0002\u0005MT\bb\u0002-\u0001\u0001\u0004%\t\"W\u0001\u0007gj|F%Z9\u0015\u00059S\u0006b\u0002*X\u0003\u0003\u0005\r\u0001\n\u0005\u00079\u0002\u0001\u000b\u0015\u0002\u0013\u0002\u0007MT\b\u0005C\u0003_\u0001\u0011\u00051%\u0001\u0003tSj,\u0007\"\u00021\u0001\t\u0003\t\u0017!B2mK\u0006\u0014H#\u0001(\t\u000b\r\u0004A\u0011\u00013\u0002\u001b\t,gm\u001c:f\u0007>l'-\u001b8f+\r)'N\u001c\u000b\u0003\u001d\u001aDQa\u001a2A\u0002!\fQa\u001c;iKJ\u0004Ba\u0004\tj[B\u00111C\u001b\u0003\u0006W\n\u0014\r\u0001\u001c\u0002\u0002\u001dF\u0011\u0001D\u0005\t\u0003'9$Qa\u001c2C\u0002A\u0014QAT3x)>\f\"AH\u000e\t\u000bI\u0004A\u0011A:\u0002\u0019\u00054G/\u001a:D_6\u0014\u0017N\\3\u0016\u0007QD(\u0010\u0006\u0002Ok\")q-\u001da\u0001mB!q\u0002E<z!\t\u0019\u0002\u0010B\u0003lc\n\u0007A\u000e\u0005\u0002\u0014u\u0012)q.\u001db\u0001a\")A\u0010\u0001C\u0001{\u000691m\\7cS:,W#\u0002@\u0002\u0004\u0005\u001dAcA@\u0002\nA1q\u0002EA\u0001\u0003\u000b\u00012aEA\u0002\t\u0015Y7P1\u0001m!\r\u0019\u0012q\u0001\u0003\u0006_n\u0014\r\u0001\u001d\u0005\u0006On\u0004\ra ")
public abstract class BucketCombiner<Elem, To, Buck, CombinerType extends BucketCombiner<Elem, To, Buck, CombinerType>>
implements Combiner<Elem, To> {
    private final int bucketnumber;
    private UnrolledBuffer<Buck>[] buckets;
    private int sz;
    private volatile transient TaskSupport _combinerTaskSupport;

    @Override
    public TaskSupport _combinerTaskSupport() {
        return this._combinerTaskSupport;
    }

    @Override
    @TraitSetter
    public void _combinerTaskSupport_$eq(TaskSupport x$1) {
        this._combinerTaskSupport = x$1;
    }

    @Override
    public TaskSupport combinerTaskSupport() {
        return Combiner$class.combinerTaskSupport(this);
    }

    @Override
    public void combinerTaskSupport_$eq(TaskSupport cts) {
        Combiner$class.combinerTaskSupport_$eq(this, cts);
    }

    @Override
    public boolean canBeShared() {
        return Combiner$class.canBeShared(this);
    }

    @Override
    public To resultWithTaskSupport() {
        return (To)Combiner$class.resultWithTaskSupport(this);
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
    public <NewTo> Builder<Elem, NewTo> mapResult(Function1<To, NewTo> f) {
        return Builder$class.mapResult(this, f);
    }

    @Override
    public Growable<Elem> $plus$eq(Elem elem1, Elem elem2, Seq<Elem> elems) {
        return Growable$class.$plus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Growable<Elem> $plus$plus$eq(TraversableOnce<Elem> xs) {
        return Growable$class.$plus$plus$eq(this, xs);
    }

    private int bucketnumber() {
        return this.bucketnumber;
    }

    public UnrolledBuffer<Buck>[] buckets() {
        return this.buckets;
    }

    public void buckets_$eq(UnrolledBuffer<Buck>[] x$1) {
        this.buckets = x$1;
    }

    public int sz() {
        return this.sz;
    }

    public void sz_$eq(int x$1) {
        this.sz = x$1;
    }

    @Override
    public int size() {
        return this.sz();
    }

    @Override
    public void clear() {
        this.buckets_$eq(new UnrolledBuffer[this.bucketnumber()]);
        this.sz_$eq(0);
    }

    public <N extends Elem, NewTo> void beforeCombine(Combiner<N, NewTo> other) {
    }

    public <N extends Elem, NewTo> void afterCombine(Combiner<N, NewTo> other) {
    }

    @Override
    public <N extends Elem, NewTo> Combiner<N, NewTo> combine(Combiner<N, NewTo> other) {
        block7: {
            BucketCombiner bucketCombiner;
            block6: {
                block5: {
                    if (this != other) break block5;
                    bucketCombiner = this;
                    break block6;
                }
                if (!(other instanceof BucketCombiner)) break block7;
                this.beforeCombine(other);
                BucketCombiner that = (BucketCombiner)other;
                for (int i = 0; i < this.bucketnumber(); ++i) {
                    Serializable serializable;
                    if (this.buckets()[i] == null) {
                        this.buckets()[i] = that.buckets()[i];
                        serializable = BoxedUnit.UNIT;
                        continue;
                    }
                    serializable = that.buckets()[i] != null ? this.buckets()[i].concat(that.buckets()[i]) : BoxedUnit.UNIT;
                }
                this.sz_$eq(this.sz() + that.size());
                this.afterCombine(other);
                bucketCombiner = this;
            }
            return bucketCombiner;
        }
        throw package$.MODULE$.error("Unexpected combiner type.");
    }

    public BucketCombiner(int bucketnumber) {
        this.bucketnumber = bucketnumber;
        Growable$class.$init$(this);
        Builder$class.$init$(this);
        Combiner$class.$init$(this);
        this.buckets = new UnrolledBuffer[bucketnumber];
        this.sz = 0;
    }
}

