/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenSet;
import scala.collection.GenSet$class;
import scala.collection.GenSetLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.IndexedSeq;
import scala.collection.Iterable;
import scala.collection.IterableLike$class;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.Iterator$class;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Set$class;
import scala.collection.SetLike$class;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericSetTemplate$class;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.generic.Shrinkable;
import scala.collection.generic.Shrinkable$class;
import scala.collection.generic.Subtractable$class;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayOps;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.Cloneable$class;
import scala.collection.mutable.Iterable$class;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.Traversable$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParSet;
import scala.collection.script.Message;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Set;
import scala.reflect.internal.util.WeakHashSet$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;
import scala.runtime.RichDouble$;

@ScalaSignature(bytes="\u0006\u0001\t]c\u0001B\u0001\u0003\u0005-\u00111bV3bW\"\u000b7\u000f[*fi*\u00111\u0001B\u0001\u0005kRLGN\u0003\u0002\u0006\r\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\b\u0011\u00059!/\u001a4mK\u000e$(\"A\u0005\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011AbE\n\u0005\u00015i2\u0005E\u0002\u000f\u001fEi\u0011AA\u0005\u0003!\t\u00111aU3u!\t\u00112\u0003\u0004\u0001\u0005\u000bQ\u0001!\u0019A\u000b\u0003\u0003\u0005\u000b\"A\u0006\u000e\u0011\u0005]AR\"\u0001\u0005\n\u0005eA!a\u0002(pi\"Lgn\u001a\t\u0003/mI!\u0001\b\u0005\u0003\r\u0005s\u0017PU3g!\u00119b$\u0005\u0011\n\u0005}A!!\u0003$v]\u000e$\u0018n\u001c82!\t9\u0012%\u0003\u0002#\u0011\t9!i\\8mK\u0006t\u0007c\u0001\u0013*#5\tQE\u0003\u0002'O\u00059Q.\u001e;bE2,'B\u0001\u0015\t\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003!\u0015B\u0001b\u000b\u0001\u0003\u0006\u0004%\t\u0001L\u0001\u0010S:LG/[1m\u0007\u0006\u0004\u0018mY5usV\tQ\u0006\u0005\u0002\u0018]%\u0011q\u0006\u0003\u0002\u0004\u0013:$\b\u0002C\u0019\u0001\u0005\u0003\u0005\u000b\u0011B\u0017\u0002!%t\u0017\u000e^5bY\u000e\u000b\u0007/Y2jif\u0004\u0003\u0002C\u001a\u0001\u0005\u000b\u0007I\u0011\u0001\u001b\u0002\u00151|\u0017\r\u001a$bGR|'/F\u00016!\t9b'\u0003\u00028\u0011\t1Ai\\;cY\u0016D\u0001\"\u000f\u0001\u0003\u0002\u0003\u0006I!N\u0001\fY>\fGMR1di>\u0014\b\u0005C\u0003<\u0001\u0011\u0005A(\u0001\u0004=S:LGO\u0010\u000b\u0004{yz\u0004c\u0001\b\u0001#!)1F\u000fa\u0001[!)1G\u000fa\u0001k!)1\b\u0001C\u0001\u0003R\tQ(\u0002\u0003D\u0001\u0001i$\u0001\u0002+iSNDa!\u0012\u0001!\u0002\u00131\u0015!B9vKV,\u0007cA$O#5\t\u0001J\u0003\u0002J\u0015\u0006\u0019!/\u001a4\u000b\u0005-c\u0015\u0001\u00027b]\u001eT\u0011!T\u0001\u0005U\u00064\u0018-\u0003\u0002P\u0011\nq!+\u001a4fe\u0016t7-Z)vKV,\u0007BB)\u0001A\u0003&Q&A\u0003d_VtG\u000fC\u0003T\u0001\u0011%A&A\bd_6\u0004X\u000f^3DCB\f7-\u001b;z\u0011\u0019)\u0006\u0001)Q\u0005-\u0006)A/\u00192mKB\u0019qcV-\n\u0005aC!!B!se\u0006L\bc\u0001.c#9\u0011abW\u0004\u00069\nA\t!X\u0001\f/\u0016\f7\u000eS1tQN+G\u000f\u0005\u0002\u000f=\u001a)\u0011A\u0001E\u0001?N\u0011aL\u0007\u0005\u0006wy#\t!\u0019\u000b\u0002;\u001a!1M\u0018\u0003e\u0005\u0015)e\u000e\u001e:z+\t)'n\u0005\u0002cMB\u0019qiZ5\n\u0005!D%!D,fC.\u0014VMZ3sK:\u001cW\r\u0005\u0002\u0013U\u0012)AC\u0019b\u0001WF\u0011a\u0003\u001c\t\u0003/5L!A\u001c\u0005\u0003\u0007\u0005s\u0017\u0010\u0003\u0005qE\n\u0005\t\u0015!\u0003j\u0003\u001d)G.Z7f]RD\u0001B\u001d2\u0003\u0006\u0004%\t\u0001L\u0001\u0005Q\u0006\u001c\b\u000e\u0003\u0005uE\n\u0005\t\u0015!\u0003.\u0003\u0015A\u0017m\u001d5!\u0011!1(M!a\u0001\n\u00039\u0018\u0001\u0002;bS2,\u0012\u0001\u001f\t\u0004s\nLW\"\u00010\t\u0011m\u0014'\u00111A\u0005\u0002q\f\u0001\u0002^1jY~#S-\u001d\u000b\u0004{\u0006\u0005\u0001CA\f\u007f\u0013\ty\bB\u0001\u0003V]&$\b\u0002CA\u0002u\u0006\u0005\t\u0019\u0001=\u0002\u0007a$\u0013\u0007C\u0005\u0002\b\t\u0014\t\u0011)Q\u0005q\u0006)A/Y5mA!IQI\u0019B\u0001B\u0003%\u00111\u0002\t\u0004\u000f:K\u0007BB\u001ec\t\u0003\ty\u0001F\u0005y\u0003#\t\u0019\"!\u0006\u0002\u0018!1\u0001/!\u0004A\u0002%DaA]A\u0007\u0001\u0004i\u0003B\u0002<\u0002\u000e\u0001\u0007\u0001\u0010C\u0004F\u0003\u001b\u0001\r!a\u0003\t\u0011\u0005maL1A\u0005\u00021\na\u0003Z3gCVdG/\u00138ji&\fGnQ1qC\u000eLG/\u001f\u0005\b\u0003?q\u0006\u0015!\u0003.\u0003]!WMZ1vYRLe.\u001b;jC2\u001c\u0015\r]1dSRL\b\u0005\u0003\u0005\u0002$y\u0013\r\u0011\"\u00015\u0003E!WMZ1vYRdu.\u00193GC\u000e$xN\u001d\u0005\b\u0003Oq\u0006\u0015!\u00036\u0003I!WMZ1vYRdu.\u00193GC\u000e$xN\u001d\u0011\t\u000f\u0005-b\f\"\u0001\u0002.\u0005)\u0011\r\u001d9msV!\u0011qFA\u001b)\u0019\t\t$a\u000e\u0002:A!a\u0002AA\u001a!\r\u0011\u0012Q\u0007\u0003\u0007)\u0005%\"\u0019A\u000b\t\u0011-\nI\u0003%AA\u00025B\u0001bMA\u0015!\u0003\u0005\r!\u000e\u0005\n\u0003{q\u0016\u0013!C\u0001\u0003\u007f\tq\"\u00199qYf$C-\u001a4bk2$H%M\u000b\u0005\u0003\u0003\n9&\u0006\u0002\u0002D)\u001aQ&!\u0012,\u0005\u0005\u001d\u0003\u0003BA%\u0003'j!!a\u0013\u000b\t\u00055\u0013qJ\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u0015\t\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003+\nYEA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$a\u0001FA\u001e\u0005\u0004)\u0002\"CA.=F\u0005I\u0011AA/\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\u0012T\u0003BA0\u0003G*\"!!\u0019+\u0007U\n)\u0005\u0002\u0004\u0015\u00033\u0012\r!\u0006\u0005\t\u0003O\u0002\u0001\u0019!C\u0001Y\u0005QA\u000f\u001b:fg\"Dw\u000e\u001c3\t\u0013\u0005-\u0004\u00011A\u0005\u0002\u00055\u0014A\u0004;ie\u0016\u001c\b\u000e[8mI~#S-\u001d\u000b\u0004{\u0006=\u0004\"CA\u0002\u0003S\n\t\u00111\u0001.\u0011\u001d\t\u0019\b\u0001Q!\n5\n1\u0002\u001e5sKND\u0007n\u001c7eA!9\u0011q\u000f\u0001!\n\u0013a\u0013!E2p[B,H/\u001a+ie\u0016\u001c\b\u000eS8mI\"A\u00111\u0010\u0001!\n\u0013\ti(A\u0005ck\u000e\\W\r\u001e$peR\u0019Q&a \t\rI\fI\b1\u0001.\u0011!\t\u0019\t\u0001Q\u0005\n\u0005\u0015\u0015A\u0002:f[>4X\rF\u0004~\u0003\u000f\u000bY)a$\t\u000f\u0005%\u0015\u0011\u0011a\u0001[\u00051!-^2lKRDq!!$\u0002\u0002\u0002\u0007\u0011,A\u0005qe\u00164XI\u001c;ss\"9\u0011\u0011SAA\u0001\u0004I\u0016!B3oiJL\b\u0002CAK\u0001\u0001&I!a&\u0002%I,Wn\u001c<f'R\fG.Z#oiJLWm\u001d\u000b\u0002{\"A\u00111\u0014\u0001!\n\u0013\t9*\u0001\u0004sKNL'0\u001a\u0005\b\u0003?\u0003A\u0011IAQ\u0003%1\u0017N\u001c3F]R\u0014\u0018\u0010F\u0002\u0012\u0003GCq!!*\u0002\u001e\u0002\u0007\u0011#\u0001\u0003fY\u0016l\u0007bBAU\u0001\u0011\u0005\u00111V\u0001\u0012M&tG-\u00128uef|%/\u00169eCR,GcA\t\u0002.\"9\u0011QUAT\u0001\u0004\t\u0002bBAY\u0001\u0011\u0005\u00131W\u0001\u0006IAdWo\u001d\u000b\u0005\u0003k\u000b9,D\u0001\u0001\u0011\u001d\t)+a,A\u0002EAq!a/\u0001\t\u0003\ti,\u0001\u0005%a2,8\u000fJ3r)\u0011\t),a0\t\u000f\u0005\u0015\u0016\u0011\u0018a\u0001#!9\u00111\u0019\u0001\u0005B\u0005\u0015\u0017\u0001C1eI\u0016sGO]=\u0015\u0007u\f9\rC\u0004\u0002J\u0006\u0005\u0007\u0019A\t\u0002\u0003aDq!!4\u0001\t\u0003\ny-\u0001\u0004%[&tWo\u001d\u000b\u0005\u0003k\u000b\t\u000eC\u0004\u0002&\u0006-\u0007\u0019A\t\t\u000f\u0005U\u0007\u0001\"\u0001\u0002X\u0006IA%\\5okN$S-\u001d\u000b\u0005\u0003k\u000bI\u000eC\u0004\u0002&\u0006M\u0007\u0019A\t\t\u000f\u0005u\u0007\u0001\"\u0011\u0002\u0018\u0006)1\r\\3be\"9\u0011\u0011\u001d\u0001\u0005B\u0005\r\u0018!B3naRLXCAAs!\r\t)L\u0011\u0005\u0007\u0003S\u0004A\u0011\t\u0017\u0002\tML'0\u001a\u0005\b\u0003W\u0001A\u0011IAw)\r\u0001\u0013q\u001e\u0005\b\u0003\u0013\fY\u000f1\u0001\u0012\u0011\u001d\t\u0019\u0010\u0001C!\u0003k\fqAZ8sK\u0006\u001c\u0007.\u0006\u0003\u0002x\n\u0005AcA?\u0002z\"A\u00111`Ay\u0001\u0004\ti0A\u0001g!\u00159b$EA\u0000!\r\u0011\"\u0011\u0001\u0003\b\u0005\u0007\t\tP1\u0001l\u0005\u0005)\u0006b\u0002B\u0004\u0001\u0011\u0005#\u0011B\u0001\u0007i>d\u0015n\u001d;\u0015\u0005\t-\u0001#\u0002B\u0007\u0005'\tbbA\f\u0003\u0010%\u0019!\u0011\u0003\u0005\u0002\u000fA\f7m[1hK&!!Q\u0003B\f\u0005\u0011a\u0015n\u001d;\u000b\u0007\tE\u0001\u0002C\u0004\u0003\u001c\u0001!\tE!\b\u0002\u0011%$XM]1u_J,\"Aa\b\u0011\u000b\t5!\u0011E\t\n\t\t\r\"q\u0003\u0002\t\u0013R,'/\u0019;pe\u001a9!q\u0005\u0001\u0001\u0005\t%\"a\u0003#jC\u001etwn\u001d;jGN\u001c2A!\n\u001b\u0011\u001dY$Q\u0005C\u0001\u0005[!\"Aa\f\u0011\t\u0005U&Q\u0005\u0005\t\u0005g\u0011)\u0003\"\u0001\u00036\u0005ia-\u001e7msZ\u000bG.\u001b3bi\u0016,\u0012! \u0005\t\u0005s\u0011)\u0003\"\u0001\u0003<\u0005!A-^7q+\t\u0011i\u0004E\u0003\u0003@\t\u0005C.D\u0001(\u0013\r\u0011\u0019e\n\u0002\u000b\u0013:$W\r_3e'\u0016\f\bb\u0002B$\u0005K!\t\u0001L\u0001\u0016G>dG.[:j_:\u0014UoY6fiN\u001cu.\u001e8u\u0011\u001d\u0011YE!\n\u0005\u00021\n\u0001CZ;mY\n+8m[3ug\u000e{WO\u001c;\t\u000f\t=#Q\u0005C\u0001Y\u0005a!-^2lKR\u001c8i\\;oi\"A!1\u000b\u0001\u0005\u0002\t\u0011)&A\u0006eS\u0006<gn\\:uS\u000e\u001cXC\u0001B\u0018\u0001")
public final class WeakHashSet<A>
extends Set<A>
implements scala.collection.mutable.Set<A> {
    private final int initialCapacity;
    private final double loadFactor;
    private final ReferenceQueue<A> queue;
    public int scala$reflect$internal$util$WeakHashSet$$count;
    public Entry<A>[] scala$reflect$internal$util$WeakHashSet$$table;
    private int threshhold;

    public static <A> double apply$default$2() {
        return WeakHashSet$.MODULE$.apply$default$2();
    }

    public static <A> int apply$default$1() {
        return WeakHashSet$.MODULE$.apply$default$1();
    }

    public static double defaultLoadFactor() {
        return WeakHashSet$.MODULE$.defaultLoadFactor();
    }

    public static int defaultInitialCapacity() {
        return WeakHashSet$.MODULE$.defaultInitialCapacity();
    }

    @Override
    public GenericCompanion<scala.collection.mutable.Set> companion() {
        return scala.collection.mutable.Set$class.companion(this);
    }

    @Override
    public scala.collection.mutable.Set<A> seq() {
        return scala.collection.mutable.Set$class.seq(this);
    }

    @Override
    public Builder<A, scala.collection.mutable.Set<A>> newBuilder() {
        return scala.collection.mutable.SetLike$class.newBuilder(this);
    }

    @Override
    public Combiner<A, ParSet<A>> parCombiner() {
        return scala.collection.mutable.SetLike$class.parCombiner(this);
    }

    @Override
    public boolean add(A elem) {
        return scala.collection.mutable.SetLike$class.add(this, elem);
    }

    @Override
    public boolean remove(A elem) {
        return scala.collection.mutable.SetLike$class.remove(this, elem);
    }

    @Override
    public void update(A elem, boolean included) {
        scala.collection.mutable.SetLike$class.update(this, elem, included);
    }

    @Override
    public void retain(Function1<A, Object> p) {
        scala.collection.mutable.SetLike$class.retain(this, p);
    }

    @Override
    public scala.collection.mutable.Set<A> clone() {
        return scala.collection.mutable.SetLike$class.clone(this);
    }

    @Override
    public scala.collection.mutable.Set<A> result() {
        return scala.collection.mutable.SetLike$class.result(this);
    }

    @Override
    public scala.collection.mutable.Set<A> $plus(A elem1, A elem2, Seq<A> elems) {
        return scala.collection.mutable.SetLike$class.$plus(this, elem1, elem2, elems);
    }

    @Override
    public scala.collection.mutable.Set<A> $plus$plus(GenTraversableOnce<A> xs) {
        return scala.collection.mutable.SetLike$class.$plus$plus(this, xs);
    }

    @Override
    public scala.collection.mutable.Set<A> $minus(A elem1, A elem2, Seq<A> elems) {
        return scala.collection.mutable.SetLike$class.$minus(this, elem1, elem2, elems);
    }

    @Override
    public scala.collection.mutable.Set<A> $minus$minus(GenTraversableOnce<A> xs) {
        return scala.collection.mutable.SetLike$class.$minus$minus(this, xs);
    }

    @Override
    public void $less$less(Message<A> cmd) {
        scala.collection.mutable.SetLike$class.$less$less(this, cmd);
    }

    @Override
    public /* synthetic */ Object scala$collection$mutable$Cloneable$$super$clone() {
        return super.clone();
    }

    @Override
    public Shrinkable<A> $minus$eq(A elem1, A elem2, Seq<A> elems) {
        return Shrinkable$class.$minus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Shrinkable<A> $minus$minus$eq(TraversableOnce<A> xs) {
        return Shrinkable$class.$minus$minus$eq(this, xs);
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
    public <NewTo> Builder<A, NewTo> mapResult(Function1<scala.collection.mutable.Set<A>, NewTo> f) {
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
    public /* synthetic */ Object scala$collection$SetLike$$super$map(Function1 f, CanBuildFrom bf) {
        return TraversableLike$class.map(this, f, bf);
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
    public <B, That> That map(Function1<A, B> f, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
        return (That)SetLike$class.map(this, f, bf);
    }

    @Override
    public boolean isEmpty() {
        return SetLike$class.isEmpty(this);
    }

    @Override
    public scala.collection.Set union(GenSet that) {
        return SetLike$class.union(this, that);
    }

    @Override
    public scala.collection.Set diff(GenSet that) {
        return SetLike$class.diff(this, that);
    }

    @Override
    public Iterator<scala.collection.mutable.Set<A>> subsets(int len) {
        return SetLike$class.subsets(this, len);
    }

    @Override
    public Iterator<scala.collection.mutable.Set<A>> subsets() {
        return SetLike$class.subsets(this);
    }

    @Override
    public String stringPrefix() {
        return SetLike$class.stringPrefix(this);
    }

    @Override
    public String toString() {
        return SetLike$class.toString(this);
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
    public boolean subsetOf(GenSet<A> that) {
        return GenSetLike$class.subsetOf(this, that);
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
    public A head() {
        return (A)IterableLike$class.head(this);
    }

    @Override
    public Object slice(int from2, int until2) {
        return IterableLike$class.slice(this, from2, until2);
    }

    @Override
    public Object take(int n) {
        return IterableLike$class.take(this, n);
    }

    @Override
    public Object drop(int n) {
        return IterableLike$class.drop(this, n);
    }

    @Override
    public Object takeWhile(Function1 p) {
        return IterableLike$class.takeWhile(this, p);
    }

    @Override
    public Iterator<scala.collection.mutable.Set<A>> grouped(int size2) {
        return IterableLike$class.grouped(this, size2);
    }

    @Override
    public Iterator<scala.collection.mutable.Set<A>> sliding(int size2) {
        return IterableLike$class.sliding(this, size2);
    }

    @Override
    public Iterator<scala.collection.mutable.Set<A>> sliding(int size2, int step) {
        return IterableLike$class.sliding(this, size2, step);
    }

    @Override
    public Object takeRight(int n) {
        return IterableLike$class.takeRight(this, n);
    }

    @Override
    public Object dropRight(int n) {
        return IterableLike$class.dropRight(this, n);
    }

    @Override
    public <B> void copyToArray(Object xs, int start, int len) {
        IterableLike$class.copyToArray(this, xs, start, len);
    }

    @Override
    public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<scala.collection.mutable.Set<A>, Tuple2<A1, B>, That> bf) {
        return (That)IterableLike$class.zip(this, that, bf);
    }

    @Override
    public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<scala.collection.mutable.Set<A>, Tuple2<A1, B>, That> bf) {
        return (That)IterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
    }

    @Override
    public <A1, That> That zipWithIndex(CanBuildFrom<scala.collection.mutable.Set<A>, Tuple2<A1, Object>, That> bf) {
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
    public IterableView<A, scala.collection.mutable.Set<A>> view(int from2, int until2) {
        return IterableLike$class.view(this, from2, until2);
    }

    @Override
    public <B> Builder<B, scala.collection.mutable.Set<B>> genericBuilder() {
        return GenericTraversableTemplate$class.genericBuilder(this);
    }

    @Override
    public <A1, A2> Tuple2<scala.collection.mutable.Set<A1>, scala.collection.mutable.Set<A2>> unzip(Function1<A, Tuple2<A1, A2>> asPair) {
        return GenericTraversableTemplate$class.unzip(this, asPair);
    }

    @Override
    public <A1, A2, A3> Tuple3<scala.collection.mutable.Set<A1>, scala.collection.mutable.Set<A2>, scala.collection.mutable.Set<A3>> unzip3(Function1<A, Tuple3<A1, A2, A3>> asTriple) {
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
    public <B, That> That $plus$plus(GenTraversableOnce<B> that, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus(this, that, bf);
    }

    @Override
    public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
    }

    @Override
    public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
    }

    @Override
    public <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> f, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
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
    public <B, That> That collect(PartialFunction<A, B> pf, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
        return (That)TraversableLike$class.collect(this, pf, bf);
    }

    @Override
    public Tuple2<scala.collection.mutable.Set<A>, scala.collection.mutable.Set<A>> partition(Function1<A, Object> p) {
        return TraversableLike$class.partition(this, p);
    }

    @Override
    public <K> Map<K, scala.collection.mutable.Set<A>> groupBy(Function1<A, K> f) {
        return TraversableLike$class.groupBy(this, f);
    }

    @Override
    public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> cbf) {
        return (That)TraversableLike$class.scan(this, z, op, cbf);
    }

    @Override
    public <B, That> That scanLeft(B z, Function2<B, A, B> op, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
        return (That)TraversableLike$class.scanLeft(this, z, op, bf);
    }

    @Override
    public <B, That> That scanRight(B z, Function2<A, B, B> op, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
        return (That)TraversableLike$class.scanRight(this, z, op, bf);
    }

    @Override
    public Option<A> headOption() {
        return TraversableLike$class.headOption(this);
    }

    @Override
    public Object tail() {
        return TraversableLike$class.tail(this);
    }

    @Override
    public A last() {
        return (A)TraversableLike$class.last(this);
    }

    @Override
    public Option<A> lastOption() {
        return TraversableLike$class.lastOption(this);
    }

    @Override
    public Object init() {
        return TraversableLike$class.init(this);
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
    public Object dropWhile(Function1 p) {
        return TraversableLike$class.dropWhile(this, p);
    }

    @Override
    public Tuple2<scala.collection.mutable.Set<A>, scala.collection.mutable.Set<A>> span(Function1<A, Object> p) {
        return TraversableLike$class.span(this, p);
    }

    @Override
    public Tuple2<scala.collection.mutable.Set<A>, scala.collection.mutable.Set<A>> splitAt(int n) {
        return TraversableLike$class.splitAt(this, n);
    }

    @Override
    public Iterator<scala.collection.mutable.Set<A>> tails() {
        return TraversableLike$class.tails(this);
    }

    @Override
    public Iterator<scala.collection.mutable.Set<A>> inits() {
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
    public FilterMonadic<A, scala.collection.mutable.Set<A>> withFilter(Function1<A, Object> p) {
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
    public scala.collection.immutable.IndexedSeq<A> toIndexedSeq() {
        return TraversableOnce$class.toIndexedSeq(this);
    }

    @Override
    public <B> scala.collection.immutable.Set<B> toSet() {
        return TraversableOnce$class.toSet(this);
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

    public int initialCapacity() {
        return this.initialCapacity;
    }

    public double loadFactor() {
        return this.loadFactor;
    }

    /*
     * WARNING - void declaration
     */
    private int computeCapacity() {
        void var1_1;
        if (this.initialCapacity() < 0) {
            throw new IllegalArgumentException("initial capacity cannot be less than 0");
        }
        for (int candidate = 1; candidate < this.initialCapacity(); candidate *= 2) {
        }
        return (int)var1_1;
    }

    public int threshhold() {
        return this.threshhold;
    }

    public void threshhold_$eq(int x$1) {
        this.threshhold = x$1;
    }

    private int computeThreshHold() {
        double d = (double)Predef$.MODULE$.refArrayOps((Object[])this.scala$reflect$internal$util$WeakHashSet$$table).size() * this.loadFactor();
        Predef$ predef$ = Predef$.MODULE$;
        return (int)RichDouble$.MODULE$.ceil$extension(d);
    }

    public int scala$reflect$internal$util$WeakHashSet$$bucketFor(int hash) {
        int h = hash ^ (hash >>> 20 ^ hash >>> 12);
        return (h ^ (h >>> 7 ^ h >>> 4)) & this.scala$reflect$internal$util$WeakHashSet$$table.length - 1;
    }

    private void remove(int bucket, Entry<A> prevEntry, Entry<A> entry) {
        if (prevEntry == null) {
            this.scala$reflect$internal$util$WeakHashSet$$table[bucket] = entry.tail();
        } else {
            prevEntry.tail_$eq(entry.tail());
        }
        --this.scala$reflect$internal$util$WeakHashSet$$count;
    }

    private void removeStaleEntries() {
        this.queueLoop$1();
    }

    private void resize() {
        Entry[] oldTable = this.scala$reflect$internal$util$WeakHashSet$$table;
        this.scala$reflect$internal$util$WeakHashSet$$table = new Entry[Predef$.MODULE$.refArrayOps((Object[])oldTable).size() * 2];
        this.threshhold_$eq(this.computeThreshHold());
        this.tableLoop$1(0, oldTable);
    }

    @Override
    public A findEntry(A elem) {
        if (elem == null) {
            throw new NullPointerException("WeakHashSet cannot hold nulls");
        }
        this.removeStaleEntries();
        int hash = elem.hashCode();
        int bucket = this.scala$reflect$internal$util$WeakHashSet$$bucketFor(hash);
        return (A)this.linkedListLoop$3(this.scala$reflect$internal$util$WeakHashSet$$table[bucket], elem);
    }

    public A findEntryOrUpdate(A elem) {
        if (elem == null) {
            throw new NullPointerException("WeakHashSet cannot hold nulls");
        }
        this.removeStaleEntries();
        int hash = elem.hashCode();
        int bucket = this.scala$reflect$internal$util$WeakHashSet$$bucketFor(hash);
        Entry<A> oldHead = this.scala$reflect$internal$util$WeakHashSet$$table[bucket];
        return (A)this.linkedListLoop$4(oldHead, elem, hash, bucket, oldHead);
    }

    @Override
    public WeakHashSet<A> $plus(A elem) {
        if (elem == null) {
            throw new NullPointerException("WeakHashSet cannot hold nulls");
        }
        this.removeStaleEntries();
        int hash = elem.hashCode();
        int bucket = this.scala$reflect$internal$util$WeakHashSet$$bucketFor(hash);
        Entry<A> oldHead = this.scala$reflect$internal$util$WeakHashSet$$table[bucket];
        this.linkedListLoop$5(oldHead, elem, hash, bucket, oldHead);
        return this;
    }

    @Override
    public WeakHashSet<A> $plus$eq(A elem) {
        return this.$plus((Object)elem);
    }

    @Override
    public void addEntry(A x) {
        this.$plus$eq((Object)x);
    }

    @Override
    public WeakHashSet<A> $minus(A elem) {
        if (elem != null) {
            this.removeStaleEntries();
            int bucket = this.scala$reflect$internal$util$WeakHashSet$$bucketFor(elem.hashCode());
            this.linkedListLoop$6(null, this.scala$reflect$internal$util$WeakHashSet$$table[bucket], elem, bucket);
        }
        return this;
    }

    @Override
    public WeakHashSet<A> $minus$eq(A elem) {
        return this.$minus((Object)elem);
    }

    @Override
    public void clear() {
        this.scala$reflect$internal$util$WeakHashSet$$table = new Entry[Predef$.MODULE$.refArrayOps((Object[])this.scala$reflect$internal$util$WeakHashSet$$table).size()];
        this.threshhold_$eq(this.computeThreshHold());
        this.scala$reflect$internal$util$WeakHashSet$$count = 0;
        this.queueLoop$2();
    }

    @Override
    public WeakHashSet<A> empty() {
        return new WeakHashSet<A>(this.initialCapacity(), this.loadFactor());
    }

    @Override
    public int size() {
        this.removeStaleEntries();
        return this.scala$reflect$internal$util$WeakHashSet$$count;
    }

    @Override
    public boolean apply(A x) {
        return this.contains(x);
    }

    @Override
    public <U> void foreach(Function1<A, U> f) {
        this.iterator().foreach(f);
    }

    @Override
    public List<A> toList() {
        return this.iterator().toList();
    }

    @Override
    public Iterator<A> iterator() {
        this.removeStaleEntries();
        return new Iterator<A>(this){
            private int currentBucket;
            private Entry<A> entry;
            private A lookaheadelement;
            private final /* synthetic */ WeakHashSet $outer;

            public Iterator<A> seq() {
                return Iterator$class.seq(this);
            }

            public boolean isEmpty() {
                return Iterator$class.isEmpty(this);
            }

            public boolean isTraversableAgain() {
                return Iterator$class.isTraversableAgain(this);
            }

            public boolean hasDefiniteSize() {
                return Iterator$class.hasDefiniteSize(this);
            }

            public Iterator<A> take(int n) {
                return Iterator$class.take(this, n);
            }

            public Iterator<A> drop(int n) {
                return Iterator$class.drop(this, n);
            }

            public Iterator<A> slice(int from2, int until2) {
                return Iterator$class.slice(this, from2, until2);
            }

            public <B> Iterator<B> map(Function1<A, B> f) {
                return Iterator$class.map(this, f);
            }

            public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
                return Iterator$class.$plus$plus(this, that);
            }

            public <B> Iterator<B> flatMap(Function1<A, GenTraversableOnce<B>> f) {
                return Iterator$class.flatMap(this, f);
            }

            public Iterator<A> filter(Function1<A, Object> p) {
                return Iterator$class.filter(this, p);
            }

            public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<A, B, Object> p) {
                return Iterator$class.corresponds(this, that, p);
            }

            public Iterator<A> withFilter(Function1<A, Object> p) {
                return Iterator$class.withFilter(this, p);
            }

            public Iterator<A> filterNot(Function1<A, Object> p) {
                return Iterator$class.filterNot(this, p);
            }

            public <B> Iterator<B> collect(PartialFunction<A, B> pf) {
                return Iterator$class.collect(this, pf);
            }

            public <B> Iterator<B> scanLeft(B z, Function2<B, A, B> op) {
                return Iterator$class.scanLeft(this, z, op);
            }

            public <B> Iterator<B> scanRight(B z, Function2<A, B, B> op) {
                return Iterator$class.scanRight(this, z, op);
            }

            public Iterator<A> takeWhile(Function1<A, Object> p) {
                return Iterator$class.takeWhile(this, p);
            }

            public Tuple2<Iterator<A>, Iterator<A>> partition(Function1<A, Object> p) {
                return Iterator$class.partition(this, p);
            }

            public Tuple2<Iterator<A>, Iterator<A>> span(Function1<A, Object> p) {
                return Iterator$class.span(this, p);
            }

            public Iterator<A> dropWhile(Function1<A, Object> p) {
                return Iterator$class.dropWhile(this, p);
            }

            public <B> Iterator<Tuple2<A, B>> zip(Iterator<B> that) {
                return Iterator$class.zip(this, that);
            }

            public <A1> Iterator<A1> padTo(int len, A1 elem) {
                return Iterator$class.padTo(this, len, elem);
            }

            public Iterator<Tuple2<A, Object>> zipWithIndex() {
                return Iterator$class.zipWithIndex(this);
            }

            public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
                return Iterator$class.zipAll(this, that, thisElem, thatElem);
            }

            public <U> void foreach(Function1<A, U> f) {
                Iterator$class.foreach(this, f);
            }

            public boolean forall(Function1<A, Object> p) {
                return Iterator$class.forall(this, p);
            }

            public boolean exists(Function1<A, Object> p) {
                return Iterator$class.exists(this, p);
            }

            public boolean contains(Object elem) {
                return Iterator$class.contains(this, elem);
            }

            public Option<A> find(Function1<A, Object> p) {
                return Iterator$class.find(this, p);
            }

            public int indexWhere(Function1<A, Object> p) {
                return Iterator$class.indexWhere(this, p);
            }

            public <B> int indexOf(B elem) {
                return Iterator$class.indexOf(this, elem);
            }

            public BufferedIterator<A> buffered() {
                return Iterator$class.buffered(this);
            }

            public <B> Iterator.GroupedIterator<B> grouped(int size2) {
                return Iterator$class.grouped(this, size2);
            }

            public <B> Iterator.GroupedIterator<B> sliding(int size2, int step) {
                return Iterator$class.sliding(this, size2, step);
            }

            public int length() {
                return Iterator$class.length(this);
            }

            public Tuple2<Iterator<A>, Iterator<A>> duplicate() {
                return Iterator$class.duplicate(this);
            }

            public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
                return Iterator$class.patch(this, from2, patchElems, replaced);
            }

            public <B> void copyToArray(Object xs, int start, int len) {
                Iterator$class.copyToArray(this, xs, start, len);
            }

            public boolean sameElements(Iterator<?> that) {
                return Iterator$class.sameElements(this, that);
            }

            public Traversable<A> toTraversable() {
                return Iterator$class.toTraversable(this);
            }

            public Iterator<A> toIterator() {
                return Iterator$class.toIterator(this);
            }

            public Stream<A> toStream() {
                return Iterator$class.toStream(this);
            }

            public String toString() {
                return Iterator$class.toString(this);
            }

            public <B> int sliding$default$2() {
                return Iterator$class.sliding$default$2(this);
            }

            public List<A> reversed() {
                return TraversableOnce$class.reversed(this);
            }

            public int size() {
                return TraversableOnce$class.size(this);
            }

            public boolean nonEmpty() {
                return TraversableOnce$class.nonEmpty(this);
            }

            public int count(Function1<A, Object> p) {
                return TraversableOnce$class.count(this, p);
            }

            public <B> Option<B> collectFirst(PartialFunction<A, B> pf) {
                return TraversableOnce$class.collectFirst(this, pf);
            }

            public <B> B $div$colon(B z, Function2<B, A, B> op) {
                return (B)TraversableOnce$class.$div$colon(this, z, op);
            }

            public <B> B $colon$bslash(B z, Function2<A, B, B> op) {
                return (B)TraversableOnce$class.$colon$bslash(this, z, op);
            }

            public <B> B foldLeft(B z, Function2<B, A, B> op) {
                return (B)TraversableOnce$class.foldLeft(this, z, op);
            }

            public <B> B foldRight(B z, Function2<A, B, B> op) {
                return (B)TraversableOnce$class.foldRight(this, z, op);
            }

            public <B> B reduceLeft(Function2<B, A, B> op) {
                return (B)TraversableOnce$class.reduceLeft(this, op);
            }

            public <B> B reduceRight(Function2<A, B, B> op) {
                return (B)TraversableOnce$class.reduceRight(this, op);
            }

            public <B> Option<B> reduceLeftOption(Function2<B, A, B> op) {
                return TraversableOnce$class.reduceLeftOption(this, op);
            }

            public <B> Option<B> reduceRightOption(Function2<A, B, B> op) {
                return TraversableOnce$class.reduceRightOption(this, op);
            }

            public <A1> A1 reduce(Function2<A1, A1, A1> op) {
                return (A1)TraversableOnce$class.reduce(this, op);
            }

            public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
                return TraversableOnce$class.reduceOption(this, op);
            }

            public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
                return (A1)TraversableOnce$class.fold(this, z, op);
            }

            public <B> B aggregate(Function0<B> z, Function2<B, A, B> seqop, Function2<B, B, B> combop) {
                return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
            }

            public <B> B sum(Numeric<B> num) {
                return (B)TraversableOnce$class.sum(this, num);
            }

            public <B> B product(Numeric<B> num) {
                return (B)TraversableOnce$class.product(this, num);
            }

            public <B> A min(Ordering<B> cmp) {
                return (A)TraversableOnce$class.min(this, cmp);
            }

            public <B> A max(Ordering<B> cmp) {
                return (A)TraversableOnce$class.max(this, cmp);
            }

            public <B> A maxBy(Function1<A, B> f, Ordering<B> cmp) {
                return (A)TraversableOnce$class.maxBy(this, f, cmp);
            }

            public <B> A minBy(Function1<A, B> f, Ordering<B> cmp) {
                return (A)TraversableOnce$class.minBy(this, f, cmp);
            }

            public <B> void copyToBuffer(Buffer<B> dest) {
                TraversableOnce$class.copyToBuffer(this, dest);
            }

            public <B> void copyToArray(Object xs, int start) {
                TraversableOnce$class.copyToArray(this, xs, start);
            }

            public <B> void copyToArray(Object xs) {
                TraversableOnce$class.copyToArray(this, xs);
            }

            public <B> Object toArray(ClassTag<B> evidence$1) {
                return TraversableOnce$class.toArray(this, evidence$1);
            }

            public List<A> toList() {
                return TraversableOnce$class.toList(this);
            }

            public Iterable<A> toIterable() {
                return TraversableOnce$class.toIterable(this);
            }

            public Seq<A> toSeq() {
                return TraversableOnce$class.toSeq(this);
            }

            public scala.collection.immutable.IndexedSeq<A> toIndexedSeq() {
                return TraversableOnce$class.toIndexedSeq(this);
            }

            public <B> Buffer<B> toBuffer() {
                return TraversableOnce$class.toBuffer(this);
            }

            public <B> scala.collection.immutable.Set<B> toSet() {
                return TraversableOnce$class.toSet(this);
            }

            public Vector<A> toVector() {
                return TraversableOnce$class.toVector(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, A, Col> cbf) {
                return (Col)TraversableOnce$class.to(this, cbf);
            }

            public <T, U> Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> ev) {
                return TraversableOnce$class.toMap(this, ev);
            }

            public String mkString(String start, String sep, String end) {
                return TraversableOnce$class.mkString(this, start, sep, end);
            }

            public String mkString(String sep) {
                return TraversableOnce$class.mkString(this, sep);
            }

            public String mkString() {
                return TraversableOnce$class.mkString(this);
            }

            public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
                return TraversableOnce$class.addString(this, b, start, sep, end);
            }

            public StringBuilder addString(StringBuilder b, String sep) {
                return TraversableOnce$class.addString(this, b, sep);
            }

            public StringBuilder addString(StringBuilder b) {
                return TraversableOnce$class.addString(this, b);
            }

            public boolean hasNext() {
                boolean bl;
                block3: {
                    while (true) {
                        if (this.entry == null && this.currentBucket > 0) {
                            --this.currentBucket;
                            this.entry = this.$outer.scala$reflect$internal$util$WeakHashSet$$table[this.currentBucket];
                            continue;
                        }
                        if (this.entry == null) {
                            bl = false;
                            break block3;
                        }
                        this.lookaheadelement = this.entry.get();
                        if (this.lookaheadelement != null) break;
                        this.entry = this.entry.tail();
                    }
                    bl = true;
                }
                return bl;
            }

            /*
             * WARNING - void declaration
             */
            public A next() {
                void var1_1;
                if (this.lookaheadelement == null) {
                    throw new IndexOutOfBoundsException("next on an empty iterator");
                }
                A result2 = this.lookaheadelement;
                this.lookaheadelement = null;
                this.entry = this.entry.tail();
                return var1_1;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                TraversableOnce$class.$init$(this);
                Iterator$class.$init$(this);
                this.currentBucket = Predef$.MODULE$.refArrayOps((Object[])$outer.scala$reflect$internal$util$WeakHashSet$$table).size();
                this.entry = null;
                this.lookaheadelement = null;
            }
        };
    }

    public Diagnostics diagnostics() {
        return new Diagnostics();
    }

    private final Entry poll$1() {
        return (Entry)this.queue.poll();
    }

    private final void linkedListLoop$1(Entry prevEntry, Entry entry, Entry stale$1, int bucket$1) {
        while (true) {
            block5: {
                block4: {
                    BoxedUnit boxedUnit;
                    block3: {
                        if (stale$1 != entry) break block3;
                        this.remove(bucket$1, prevEntry, entry);
                        boxedUnit = BoxedUnit.UNIT;
                        break block4;
                    }
                    if (entry != null) break block5;
                    boxedUnit = BoxedUnit.UNIT;
                }
                return;
            }
            Entry entry2 = entry;
            entry = entry.tail();
            prevEntry = entry2;
        }
    }

    /*
     * WARNING - void declaration
     */
    private final void queueLoop$1() {
        while (true) {
            void var1_1;
            Entry stale;
            if ((stale = this.poll$1()) == null) {
                return;
            }
            int bucket = this.scala$reflect$internal$util$WeakHashSet$$bucketFor(var1_1.hash());
            this.linkedListLoop$1(null, this.scala$reflect$internal$util$WeakHashSet$$table[bucket], (Entry)var1_1, bucket);
        }
    }

    private final void linkedListLoop$2(Entry entry) {
        while (true) {
            if (entry == null) {
                return;
            }
            int bucket = this.scala$reflect$internal$util$WeakHashSet$$bucketFor(entry.hash());
            Entry<A> oldNext = entry.tail();
            entry.tail_$eq(this.scala$reflect$internal$util$WeakHashSet$$table[bucket]);
            this.scala$reflect$internal$util$WeakHashSet$$table[bucket] = entry;
            entry = oldNext;
        }
    }

    private final void tableLoop$1(int oldBucket, Entry[] oldTable$1) {
        while (oldBucket < Predef$.MODULE$.refArrayOps((Object[])oldTable$1).size()) {
            this.linkedListLoop$2(oldTable$1[oldBucket]);
            ++oldBucket;
        }
    }

    private final Object linkedListLoop$3(Entry entry, Object elem$2) {
        while (true) {
            block6: {
                Object var4_4;
                block5: {
                    block4: {
                        if (entry != null) break block4;
                        var4_4 = null;
                        break block5;
                    }
                    Object entryElem = entry.get();
                    boolean bl = elem$2 != entryElem ? (elem$2 != null ? (!(elem$2 instanceof Number) ? (!(elem$2 instanceof Character) ? elem$2.equals(entryElem) : BoxesRunTime.equalsCharObject((Character)elem$2, entryElem)) : BoxesRunTime.equalsNumObject((Number)elem$2, entryElem)) : false) : true;
                    if (!bl) break block6;
                    var4_4 = entryElem;
                }
                return var4_4;
            }
            entry = entry.tail();
        }
    }

    private final Object add$1(Object elem$1, int hash$1, int bucket$2, Entry oldHead$1) {
        this.scala$reflect$internal$util$WeakHashSet$$table[bucket$2] = new Entry<Object>(elem$1, hash$1, oldHead$1, this.queue);
        ++this.scala$reflect$internal$util$WeakHashSet$$count;
        if (this.scala$reflect$internal$util$WeakHashSet$$count > this.threshhold()) {
            this.resize();
        }
        return elem$1;
    }

    private final Object linkedListLoop$4(Entry entry, Object elem$1, int hash$1, int bucket$2, Entry oldHead$1) {
        while (true) {
            block6: {
                Object object;
                block5: {
                    block4: {
                        if (entry != null) break block4;
                        object = this.add$1(elem$1, hash$1, bucket$2, oldHead$1);
                        break block5;
                    }
                    Object entryElem = entry.get();
                    boolean bl = elem$1 != entryElem ? (elem$1 != null ? (!(elem$1 instanceof Number) ? (!(elem$1 instanceof Character) ? elem$1.equals(entryElem) : BoxesRunTime.equalsCharObject((Character)elem$1, entryElem)) : BoxesRunTime.equalsNumObject((Number)elem$1, entryElem)) : false) : true;
                    if (!bl) break block6;
                    object = entryElem;
                }
                return object;
            }
            entry = entry.tail();
        }
    }

    private final void add$2(Object elem$3, int hash$2, int bucket$3, Entry oldHead$2) {
        this.scala$reflect$internal$util$WeakHashSet$$table[bucket$3] = new Entry<Object>(elem$3, hash$2, oldHead$2, this.queue);
        ++this.scala$reflect$internal$util$WeakHashSet$$count;
        if (this.scala$reflect$internal$util$WeakHashSet$$count > this.threshhold()) {
            this.resize();
        }
    }

    private final void linkedListLoop$5(Entry entry, Object elem$3, int hash$2, int bucket$3, Entry oldHead$2) {
        while (true) {
            block6: {
                block5: {
                    block4: {
                        if (entry != null) break block4;
                        this.add$2(elem$3, hash$2, bucket$3, oldHead$2);
                        break block5;
                    }
                    Object t = entry.get();
                    boolean bl = elem$3 != t ? (elem$3 != null ? (!(elem$3 instanceof Number) ? (!(elem$3 instanceof Character) ? elem$3.equals(t) : BoxesRunTime.equalsCharObject((Character)elem$3, t)) : BoxesRunTime.equalsNumObject((Number)elem$3, t)) : false) : true;
                    if (!bl) break block6;
                }
                return;
            }
            entry = entry.tail();
        }
    }

    private final void linkedListLoop$6(Entry prevEntry, Entry entry, Object elem$4, int bucket$4) {
        while (true) {
            block6: {
                block5: {
                    block4: {
                        if (entry != null) break block4;
                        break block5;
                    }
                    Object t = entry.get();
                    boolean bl = elem$4 != t ? (elem$4 != null ? (!(elem$4 instanceof Number) ? (!(elem$4 instanceof Character) ? elem$4.equals(t) : BoxesRunTime.equalsCharObject((Character)elem$4, t)) : BoxesRunTime.equalsNumObject((Number)elem$4, t)) : false) : true;
                    if (!bl) break block6;
                    this.remove(bucket$4, prevEntry, entry);
                }
                return;
            }
            Entry entry2 = entry;
            entry = entry.tail();
            prevEntry = entry2;
        }
    }

    private final void queueLoop$2() {
        while (this.queue.poll() != null) {
        }
    }

    public WeakHashSet(int initialCapacity, double loadFactor) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        Function1$class.$init$(this);
        TraversableOnce$class.$init$(this);
        Parallelizable$class.$init$(this);
        TraversableLike$class.$init$(this);
        GenericTraversableTemplate$class.$init$(this);
        GenTraversable$class.$init$(this);
        scala.collection.Traversable$class.$init$(this);
        Traversable$class.$init$(this);
        GenIterable$class.$init$(this);
        IterableLike$class.$init$(this);
        scala.collection.Iterable$class.$init$(this);
        Iterable$class.$init$(this);
        GenSetLike$class.$init$(this);
        GenericSetTemplate$class.$init$(this);
        GenSet$class.$init$(this);
        Subtractable$class.$init$(this);
        SetLike$class.$init$(this);
        Set$class.$init$(this);
        Growable$class.$init$(this);
        Builder$class.$init$(this);
        Shrinkable$class.$init$(this);
        Cloneable$class.$init$(this);
        scala.collection.mutable.SetLike$class.$init$(this);
        scala.collection.mutable.Set$class.$init$(this);
        this.queue = new ReferenceQueue();
        this.scala$reflect$internal$util$WeakHashSet$$count = 0;
        this.scala$reflect$internal$util$WeakHashSet$$table = new Entry[this.computeCapacity()];
        this.threshhold = this.computeThreshHold();
    }

    public WeakHashSet() {
        this(WeakHashSet$.MODULE$.defaultInitialCapacity(), WeakHashSet$.MODULE$.defaultLoadFactor());
    }

    public static class Entry<A>
    extends WeakReference<A> {
        private final int hash;
        private Entry<A> tail;

        public int hash() {
            return this.hash;
        }

        public Entry<A> tail() {
            return this.tail;
        }

        public void tail_$eq(Entry<A> x$1) {
            this.tail = x$1;
        }

        public Entry(A element, int hash, Entry<A> tail, ReferenceQueue<A> queue) {
            this.hash = hash;
            this.tail = tail;
            super(element, queue);
        }
    }

    public class Diagnostics {
        /*
         * WARNING - void declaration
         */
        public void fullyValidate() {
            IntRef computedCount = IntRef.create(0);
            IntRef bucket = IntRef.create(0);
            block0: while (true) {
                Object[] objectArray = this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$table;
                Predef$ predef$ = Predef$.MODULE$;
                if (bucket.elem >= new ArrayOps.ofRef<Object>(objectArray).size()) break;
                ObjectRef entry = ObjectRef.create(this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$table[bucket.elem]);
                while (true) {
                    int computedBucket;
                    int realHash;
                    int cachedHash;
                    void var16_17;
                    if ((Entry)entry.elem == null) {
                        ++bucket.elem;
                        continue block0;
                    }
                    Serializable serializable = new Serializable(this, (ObjectRef)var16_17){
                        public static final long serialVersionUID = 0L;
                        public final ObjectRef entry$1;

                        public final String apply() {
                            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " had a null value indicated that gc activity was happening during diagnostic validation or that a null value was inserted"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{(Entry)this.entry$1.elem}));
                        }
                        {
                            this.entry$1 = entry$1;
                        }
                    };
                    boolean bl = ((Entry)var16_17.elem).get() != null;
                    Predef$ predef$2 = Predef$.MODULE$;
                    if (!bl) {
                        throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " had a null value indicated that gc activity was happening during diagnostic validation or that a null value was inserted"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{(Entry)serializable.entry$1.elem}))).toString());
                    }
                    ++computedCount.elem;
                    Serializable serializable2 = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        public final ObjectRef entry$1;
                        public final int cachedHash$1;
                        public final int realHash$1;

                        public final String apply() {
                            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"for ", " cached hash was ", " but should have been ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{(Entry)this.entry$1.elem, BoxesRunTime.boxToInteger(this.cachedHash$1), BoxesRunTime.boxToInteger(this.realHash$1)}));
                        }
                        {
                            this.entry$1 = entry$1;
                            this.cachedHash$1 = cachedHash$1;
                            this.realHash$1 = realHash$1;
                        }
                    };
                    cachedHash = ((Entry)var16_17.elem).hash();
                    boolean bl2 = cachedHash == (realHash = ((Entry)var16_17.elem).get().hashCode());
                    Predef$ predef$3 = Predef$.MODULE$;
                    if (!bl2) {
                        throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"for ", " cached hash was ", " but should have been ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{(Entry)serializable2.entry$1.elem, BoxesRunTime.boxToInteger(serializable2.cachedHash$1), BoxesRunTime.boxToInteger(serializable2.realHash$1)}))).toString());
                    }
                    Serializable serializable3 = new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        public final IntRef bucket$5;
                        public final ObjectRef entry$1;
                        public final int computedBucket$1;

                        public final String apply() {
                            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"for ", " the computed bucket was ", " but should have been ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{(Entry)this.entry$1.elem, BoxesRunTime.boxToInteger(this.computedBucket$1), BoxesRunTime.boxToInteger(this.bucket$5.elem)}));
                        }
                        {
                            this.bucket$5 = bucket$5;
                            this.entry$1 = entry$1;
                            this.computedBucket$1 = computedBucket$1;
                        }
                    };
                    computedBucket = this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$bucketFor(realHash);
                    boolean bl3 = computedBucket == bucket.elem;
                    Predef$ predef$4 = Predef$.MODULE$;
                    if (!bl3) {
                        throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"for ", " the computed bucket was ", " but should have been ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{(Entry)serializable3.entry$1.elem, BoxesRunTime.boxToInteger(serializable3.computedBucket$1), BoxesRunTime.boxToInteger(serializable3.bucket$5.elem)}))).toString());
                    }
                    var16_17.elem = ((Entry)var16_17.elem).tail();
                }
                break;
            }
            boolean bl = computedCount.elem == this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$count;
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"The computed count was ", " but should have been ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(computedCount.elem), BoxesRunTime.boxToInteger(this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$count)}))).toString());
            }
        }

        public IndexedSeq<Object> dump() {
            return Predef$.MODULE$.refArrayOps((Object[])this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$table).deep();
        }

        public int collisionBucketsCount() {
            return Predef$.MODULE$.refArrayOps((Object[])this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$table).count(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Entry<A> entry) {
                    return entry != null && entry.tail() != null;
                }
            });
        }

        public int fullBucketsCount() {
            return Predef$.MODULE$.refArrayOps((Object[])this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$table).count(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Entry<A> entry) {
                    return entry != null;
                }
            });
        }

        public int bucketsCount() {
            return Predef$.MODULE$.refArrayOps((Object[])this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$table).size();
        }

        public /* synthetic */ WeakHashSet scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer() {
            return WeakHashSet.this;
        }

        public Diagnostics() {
            if (WeakHashSet.this == null) {
                throw null;
            }
        }
    }
}

