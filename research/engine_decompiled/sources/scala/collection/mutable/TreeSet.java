/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.Function2;
import scala.MatchError;
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
import scala.collection.IterableLike$class;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.Set$class;
import scala.collection.SortedSetLike$class;
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
import scala.collection.generic.Sorted;
import scala.collection.generic.Sorted$class;
import scala.collection.generic.Subtractable$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.RedBlackTree;
import scala.collection.immutable.RedBlackTree$;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.Cloneable$class;
import scala.collection.mutable.Iterable$class;
import scala.collection.mutable.SetLike$class;
import scala.collection.mutable.SortedSet;
import scala.collection.mutable.SortedSet$class;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.Traversable$class;
import scala.collection.mutable.TreeSet$;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParSet;
import scala.collection.script.Message;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;
import scala.runtime.Null$;
import scala.runtime.ObjectRef;

@ScalaSignature(bytes="\u0006\u0001\u0005\u001dv!B\u0001\u0003\u0011\u0003I\u0011a\u0002+sK\u0016\u001cV\r\u001e\u0006\u0003\u0007\u0011\tq!\\;uC\ndWM\u0003\u0002\u0006\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001\u0001\u0005\u0002\u000b\u00175\t!AB\u0003\r\u0005!\u0005QBA\u0004Ue\u0016,7+\u001a;\u0014\u0007-qQ\u0007E\u0002\u0010%Qi\u0011\u0001\u0005\u0006\u0003#\u0011\tqaZ3oKJL7-\u0003\u0002\u0014!\t9R*\u001e;bE2,7k\u001c:uK\u0012\u001cV\r\u001e$bGR|'/\u001f\t\u0003\u0015U1A\u0001\u0004\u0002\u0001-U\u0011q#I\n\b+aa\"F\f\u001a6!\tI\"$D\u0001\u0007\u0013\tYbA\u0001\u0004B]f\u0014VM\u001a\t\u0004\u0015uy\u0012B\u0001\u0010\u0003\u0005%\u0019vN\u001d;fIN+G\u000f\u0005\u0002!C1\u0001A!\u0002\u0012\u0016\u0005\u0004\u0019#!A!\u0012\u0005\u0011:\u0003CA\r&\u0013\t1cAA\u0004O_RD\u0017N\\4\u0011\u0005eA\u0013BA\u0015\u0007\u0005\r\te.\u001f\t\u0005\u0015-zR&\u0003\u0002-\u0005\t91+\u001a;MS.,\u0007c\u0001\u0006\u0016?A!q\u0006M\u0010.\u001b\u0005!\u0011BA\u0019\u0005\u00055\u0019vN\u001d;fIN+G\u000fT5lKB\u0019!bM\u0010\n\u0005Q\u0012!aA*fiB\u0011\u0011DN\u0005\u0003o\u0019\u0011AbU3sS\u0006d\u0017N_1cY\u0016D\u0001\"O\u000b\u0003\u0002\u0003\u0006IAO\u0001\biJ,WMU3g!\rYd\bQ\u0007\u0002y)\u0011QHB\u0001\beVtG/[7f\u0013\tyDHA\u0005PE*,7\r\u001e*fMB!\u0011iR\u0010K\u001d\t\u0011U)D\u0001D\u0015\t!E!A\u0005j[6,H/\u00192mK&\u0011aiQ\u0001\r%\u0016$'\t\\1dWR\u0013X-Z\u0005\u0003\u0011&\u0013A\u0001\u0016:fK*\u0011ai\u0011\t\u00033-K!\u0001\u0014\u0004\u0003\t9+H\u000e\u001c\u0005\t\u001dV\u0011\t\u0011)A\u0005\u001f\u0006!aM]8n!\rI\u0002kH\u0005\u0003#\u001a\u0011aa\u00149uS>t\u0007\u0002C*\u0016\u0005\u0003\u0005\u000b\u0011B(\u0002\u000bUtG/\u001b7\t\u0011U+\"Q1A\u0005\u0004Y\u000b\u0001b\u001c:eKJLgnZ\u000b\u0002/B\u0019\u0001lW\u0010\u000f\u0005eI\u0016B\u0001.\u0007\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001X/\u0003\u0011=\u0013H-\u001a:j]\u001eT!A\u0017\u0004\t\u0011}+\"\u0011!Q\u0001\n]\u000b\u0011b\u001c:eKJLgn\u001a\u0011\t\u000b\u0005,B\u0011\u00022\u0002\rqJg.\u001b;?)\u0011\u0019WMZ4\u0015\u00055\"\u0007\"B+a\u0001\b9\u0006\"B\u001da\u0001\u0004Q\u0004\"\u0002(a\u0001\u0004y\u0005\"B*a\u0001\u0004y\u0005\"B1\u0016\t\u0003IG#\u00016\u0015\u00055Z\u0007\"B+i\u0001\b9\u0006\"B7\u0016\t\u0003r\u0017\u0001B:ju\u0016,\u0012a\u001c\t\u00033AL!!\u001d\u0004\u0003\u0007%sG\u000fC\u0003t+\u0011\u0005C/\u0001\u0007tiJLgn\u001a)sK\u001aL\u00070F\u0001v!\t180D\u0001x\u0015\tA\u00180\u0001\u0003mC:<'\"\u0001>\u0002\t)\fg/Y\u0005\u0003y^\u0014aa\u0015;sS:<\u0007\"\u0002@\u0016\t\u0003z\u0018!B3naRLX#A\u0017\t\u000f\u0005\rQ\u0003\"\u0003\u0002\u0006\u0005I\u0001/[2l\u0005>,h\u000e\u001a\u000b\b\u001f\u0006\u001d\u0011\u0011CA\u000b\u0011!\tI!!\u0001A\u0002\u0005-\u0011AC2p[B\f'/[:p]B1\u0011$!\u0004 ?}I1!a\u0004\u0007\u0005%1UO\\2uS>t'\u0007C\u0004\u0002\u0014\u0005\u0005\u0001\u0019A(\u0002\u0011=dGMQ8v]\u0012Dq!a\u0006\u0002\u0002\u0001\u0007q*\u0001\u0005oK^\u0014u.\u001e8e\u0011\u001d\tY\"\u0006C!\u0003;\t\u0011B]1oO\u0016LU\u000e\u001d7\u0015\u000b5\ny\"a\t\t\u000f\u0005\u0005\u0012\u0011\u0004a\u0001\u001f\u00069aM]8n\u0003J<\u0007bBA\u0013\u00033\u0001\raT\u0001\tk:$\u0018\u000e\\!sO\"9\u0011\u0011F\u000b\u0005B\u0005-\u0012!\u0003\u0013nS:,8\u000fJ3r)\u0011\ti#a\f\u000e\u0003UAq!!\r\u0002(\u0001\u0007q$\u0001\u0003fY\u0016l\u0007bBA\u001b+\u0011\u0005\u0013qG\u0001\tIAdWo\u001d\u0013fcR!\u0011QFA\u001d\u0011\u001d\t\t$a\rA\u0002}Aq!!\u0010\u0016\t\u0003\ny$A\u0003dY>tW\rF\u0001.\u0011%\t\u0019%\u0006b\u0001\n\u0013\t)%A\u0007o_R\u0004&o\u001c6fGRLwN\\\u000b\u0003\u0003\u000f\u00022!GA%\u0013\r\tYE\u0002\u0002\b\u0005>|G.Z1o\u0011!\ty%\u0006Q\u0001\n\u0005\u001d\u0013A\u00048piB\u0013xN[3di&|g\u000e\t\u0005\b\u0003'*B\u0011IA+\u0003!\u0019wN\u001c;bS:\u001cH\u0003BA$\u0003/Bq!!\r\u0002R\u0001\u0007q\u0004C\u0004\u0002\\U!\t%!\u0018\u0002\u0011%$XM]1u_J,\"!a\u0018\u0011\t=\n\tgH\u0005\u0004\u0003G\"!\u0001C%uKJ\fGo\u001c:\t\u000f\u0005\u001dT\u0003\"\u0011\u0002j\u0005\u00012.Z=t\u0013R,'/\u0019;pe\u001a\u0013x.\u001c\u000b\u0005\u0003?\nY\u0007C\u0004\u0002n\u0005\u0015\u0004\u0019A\u0010\u0002\u000bM$\u0018M\u001d;\t\u000f\u0005ET\u0003\"\u0003\u0002t\u0005a\u0011\u000e^3sCR|'O\u0012:p[R!\u0011qLA;\u0011\u001d\ti'a\u001cA\u0002=Cs!FA=\u0003\u007f\n\u0019\tE\u0002\u001a\u0003wJ1!! \u0007\u0005U!W\r\u001d:fG\u0006$X\rZ%oQ\u0016\u0014\u0018\u000e^1oG\u0016\f#!!!\u0002sQ\u0013X-Z*fi\u0002J7\u000f\t8pi\u0002\"Wm]5h]\u0016$\u0007\u0005^8!K:\f'\r\\3![\u0016\fg.\u001b8hMVd\u0007e];cG2\f7o]5oO:\n#!!\"\u0002\rIr\u0013'\r\u00181\u0011\u0019\t7\u0002\"\u0001\u0002\nR\t\u0011\u0002\u0003\u0004\u007f\u0017\u0011\u0005\u0011QR\u000b\u0005\u0003\u001f\u000b)\n\u0006\u0003\u0002\u0012\u0006]\u0005\u0003\u0002\u0006\u0016\u0003'\u00032\u0001IAK\t\u0019\u0011\u00131\u0012b\u0001G!9Q+a#A\u0004\u0005e\u0005\u0003\u0002-\\\u0003'C\u0011\"!(\f\u0003\u0003%I!a(\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0003\u0003C\u00032A^AR\u0013\r\t)k\u001e\u0002\u0007\u001f\nTWm\u0019;")
public class TreeSet<A>
implements SortedSet<A>,
Serializable {
    private final ObjectRef<RedBlackTree.Tree<A, Null$>> treeRef;
    private final Option<A> from;
    private final Option<A> until;
    private final Ordering<A> ordering;
    private final boolean notProjection;

    public static <A> CanBuildFrom<TreeSet<?>, A, TreeSet<A>> newCanBuildFrom(Ordering<A> ordering) {
        return TreeSet$.MODULE$.newCanBuildFrom(ordering);
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
    public Builder<A, TreeSet<A>> newBuilder() {
        return SetLike$class.newBuilder(this);
    }

    @Override
    public Combiner<A, ParSet<A>> parCombiner() {
        return SetLike$class.parCombiner(this);
    }

    @Override
    public boolean add(A elem) {
        return SetLike$class.add(this, elem);
    }

    @Override
    public boolean remove(A elem) {
        return SetLike$class.remove(this, elem);
    }

    @Override
    public void update(A elem, boolean included) {
        SetLike$class.update(this, elem, included);
    }

    @Override
    public void retain(Function1<A, Object> p) {
        SetLike$class.retain(this, p);
    }

    @Override
    public void clear() {
        SetLike$class.clear(this);
    }

    @Override
    public scala.collection.mutable.Set result() {
        return SetLike$class.result(this);
    }

    @Override
    public scala.collection.mutable.Set $plus(Object elem) {
        return SetLike$class.$plus(this, elem);
    }

    @Override
    public scala.collection.mutable.Set $plus(Object elem1, Object elem2, Seq elems) {
        return SetLike$class.$plus(this, elem1, elem2, elems);
    }

    @Override
    public scala.collection.mutable.Set $plus$plus(GenTraversableOnce xs) {
        return SetLike$class.$plus$plus(this, xs);
    }

    @Override
    public scala.collection.mutable.Set $minus(Object elem) {
        return SetLike$class.$minus(this, elem);
    }

    @Override
    public scala.collection.mutable.Set $minus(Object elem1, Object elem2, Seq elems) {
        return SetLike$class.$minus(this, elem1, elem2, elems);
    }

    @Override
    public scala.collection.mutable.Set $minus$minus(GenTraversableOnce xs) {
        return SetLike$class.$minus$minus(this, xs);
    }

    @Override
    public void $less$less(Message<A> cmd) {
        SetLike$class.$less$less(this, cmd);
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
    public <NewTo> Builder<A, NewTo> mapResult(Function1<TreeSet<A>, NewTo> f) {
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
    public /* synthetic */ boolean scala$collection$SortedSetLike$$super$subsetOf(GenSet that) {
        return GenSetLike$class.subsetOf(this, that);
    }

    @Override
    public scala.collection.SortedSet keySet() {
        return SortedSetLike$class.keySet(this);
    }

    @Override
    public A firstKey() {
        return (A)SortedSetLike$class.firstKey(this);
    }

    @Override
    public A lastKey() {
        return (A)SortedSetLike$class.lastKey(this);
    }

    @Override
    public scala.collection.SortedSet from(Object from2) {
        return SortedSetLike$class.from(this, from2);
    }

    @Override
    public scala.collection.SortedSet until(Object until2) {
        return SortedSetLike$class.until(this, until2);
    }

    @Override
    public scala.collection.SortedSet range(Object from2, Object until2) {
        return SortedSetLike$class.range(this, from2, until2);
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
    public Sorted to(Object to2) {
        return Sorted$class.to(this, to2);
    }

    @Override
    public boolean hasAll(Iterator<A> j) {
        return Sorted$class.hasAll(this, j);
    }

    @Override
    public /* synthetic */ Object scala$collection$SetLike$$super$map(Function1 f, CanBuildFrom bf) {
        return TraversableLike$class.map(this, f, bf);
    }

    @Override
    public Seq<A> toSeq() {
        return scala.collection.SetLike$class.toSeq(this);
    }

    @Override
    public <A1> Buffer<A1> toBuffer() {
        return scala.collection.SetLike$class.toBuffer(this);
    }

    @Override
    public <B, That> That map(Function1<A, B> f, CanBuildFrom<TreeSet<A>, B, That> bf) {
        return (That)scala.collection.SetLike$class.map(this, f, bf);
    }

    @Override
    public boolean isEmpty() {
        return scala.collection.SetLike$class.isEmpty(this);
    }

    @Override
    public Set union(GenSet that) {
        return scala.collection.SetLike$class.union(this, that);
    }

    @Override
    public Set diff(GenSet that) {
        return scala.collection.SetLike$class.diff(this, that);
    }

    @Override
    public Iterator<TreeSet<A>> subsets(int len) {
        return scala.collection.SetLike$class.subsets(this, len);
    }

    @Override
    public Iterator<TreeSet<A>> subsets() {
        return scala.collection.SetLike$class.subsets(this);
    }

    @Override
    public String toString() {
        return scala.collection.SetLike$class.toString(this);
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
    public Iterable<A> thisCollection() {
        return IterableLike$class.thisCollection(this);
    }

    @Override
    public Iterable toCollection(Object repr) {
        return IterableLike$class.toCollection(this, repr);
    }

    @Override
    public <U> void foreach(Function1<A, U> f) {
        IterableLike$class.foreach(this, f);
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
    public Tuple2<TreeSet<A>, TreeSet<A>> span(Function1<A, Object> p) {
        return TraversableLike$class.span(this, p);
    }

    @Override
    public Tuple2<TreeSet<A>, TreeSet<A>> splitAt(int n) {
        return TraversableLike$class.splitAt(this, n);
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

    @Override
    public Ordering<A> ordering() {
        return this.ordering;
    }

    @Override
    public int size() {
        return RedBlackTree$.MODULE$.countInRange((RedBlackTree.Tree)this.treeRef.elem, this.from, this.until, this.ordering());
    }

    @Override
    public String stringPrefix() {
        return "TreeSet";
    }

    @Override
    public TreeSet<A> empty() {
        return TreeSet$.MODULE$.empty((Ordering)this.ordering());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private Option<A> pickBound(Function2<A, A, A> comparison, Option<A> oldBound, Option<A> newBound) {
        Tuple2<Option<A>, Option<A>> tuple2 = new Tuple2<Option<A>, Option<A>>(newBound, oldBound);
        if (tuple2._1() instanceof Some) {
            Some some = (Some)tuple2._1();
            if (tuple2._2() instanceof Some) {
                Some some2 = (Some)tuple2._2();
                return new Some<A>(comparison.apply(some.x(), some2.x()));
            }
        }
        if (!None$.MODULE$.equals(tuple2._1())) return newBound;
        return oldBound;
    }

    @Override
    public TreeSet<A> rangeImpl(Option<A> fromArg, Option<A> untilArg) {
        Option<A> newFrom = this.pickBound((Function2<A, A, A>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TreeSet $outer;

            public final A apply(A x, A y) {
                return this.$outer.ordering().max(x, y);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }), fromArg, this.from);
        Option<A> newUntil = this.pickBound((Function2<A, A, A>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TreeSet $outer;

            public final A apply(A x, A y) {
                return this.$outer.ordering().min(x, y);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }), untilArg, this.until);
        return new TreeSet<A>(this.treeRef, newFrom, newUntil, this.ordering());
    }

    @Override
    public TreeSet<A> $minus$eq(A elem) {
        this.treeRef.elem = RedBlackTree$.MODULE$.delete((RedBlackTree.Tree)this.treeRef.elem, elem, this.ordering());
        return this;
    }

    @Override
    public TreeSet<A> $plus$eq(A elem) {
        this.treeRef.elem = RedBlackTree$.MODULE$.update((RedBlackTree.Tree)this.treeRef.elem, elem, null, false, this.ordering());
        return this;
    }

    @Override
    public TreeSet<A> clone() {
        return new TreeSet<A>(new ObjectRef<RedBlackTree.Tree<A, Null$>>(this.treeRef.elem), this.from, this.until, this.ordering());
    }

    private boolean notProjection() {
        return this.notProjection;
    }

    @Override
    public boolean contains(A elem) {
        return (this.notProjection() || this.leftAcceptable$1(elem) && this.rightAcceptable$1(elem)) && RedBlackTree$.MODULE$.contains((RedBlackTree.Tree)this.treeRef.elem, elem, this.ordering());
    }

    @Override
    public Iterator<A> iterator() {
        return this.iteratorFrom(None$.MODULE$);
    }

    @Override
    public Iterator<A> keysIteratorFrom(A start) {
        return this.iteratorFrom(new Some<A>(start));
    }

    @Override
    private Iterator<A> iteratorFrom(Option<A> start) {
        Option<A> option;
        block4: {
            Iterator<A> iterator2;
            block3: {
                Iterator<A> it;
                block2: {
                    it = RedBlackTree$.MODULE$.keysIterator((RedBlackTree.Tree)this.treeRef.elem, this.pickBound((Function2<A, A, A>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ TreeSet $outer;

                        public final A apply(A x, A y) {
                            return this.$outer.ordering().max(x, y);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }), this.from, start), this.ordering());
                    option = this.until;
                    if (!None$.MODULE$.equals(option)) break block2;
                    iterator2 = it;
                    break block3;
                }
                if (!(option instanceof Some)) break block4;
                Some some = (Some)option;
                iterator2 = it.takeWhile((Function1<A, Object>)((Object)new Serializable(this, some){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TreeSet $outer;
                    private final Some x2$1;

                    public final boolean apply(A k) {
                        return this.$outer.ordering().lt(k, this.x2$1.x());
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.x2$1 = x2$1;
                    }
                }));
            }
            return iterator2;
        }
        throw new MatchError(option);
    }

    private final boolean leftAcceptable$1(Object elem$1) {
        boolean bl;
        Option<A> option = this.from;
        if (option instanceof Some) {
            Some some = (Some)option;
            bl = this.ordering().gteq(elem$1, some.x());
        } else {
            bl = true;
        }
        return bl;
    }

    private final boolean rightAcceptable$1(Object elem$1) {
        boolean bl;
        Option<A> option = this.until;
        if (option instanceof Some) {
            Some some = (Some)option;
            bl = this.ordering().lt(elem$1, some.x());
        } else {
            bl = true;
        }
        return bl;
    }

    private TreeSet(ObjectRef<RedBlackTree.Tree<A, Null$>> treeRef, Option<A> from2, Option<A> until2, Ordering<A> ordering) {
        this.treeRef = treeRef;
        this.from = from2;
        this.until = until2;
        this.ordering = ordering;
        Function1$class.$init$(this);
        TraversableOnce$class.$init$(this);
        Parallelizable$class.$init$(this);
        TraversableLike$class.$init$(this);
        GenericTraversableTemplate$class.$init$(this);
        GenTraversable$class.$init$(this);
        scala.collection.Traversable$class.$init$(this);
        GenIterable$class.$init$(this);
        IterableLike$class.$init$(this);
        scala.collection.Iterable$class.$init$(this);
        GenSetLike$class.$init$(this);
        GenericSetTemplate$class.$init$(this);
        GenSet$class.$init$(this);
        Subtractable$class.$init$(this);
        scala.collection.SetLike$class.$init$(this);
        Set$class.$init$(this);
        Sorted$class.$init$(this);
        SortedSetLike$class.$init$(this);
        scala.collection.SortedSet$class.$init$(this);
        Traversable$class.$init$(this);
        Iterable$class.$init$(this);
        Growable$class.$init$(this);
        Builder$class.$init$(this);
        Shrinkable$class.$init$(this);
        Cloneable$class.$init$(this);
        SetLike$class.$init$(this);
        scala.collection.mutable.Set$class.$init$(this);
        SortedSet$class.$init$(this);
        if (ordering == null) {
            throw new NullPointerException("ordering must not be null");
        }
        this.notProjection = !from2.isDefined() && !until2.isDefined();
    }

    public TreeSet(Ordering<A> ordering) {
        this(new ObjectRef<Object>(null), None$.MODULE$, None$.MODULE$, ordering);
    }
}

