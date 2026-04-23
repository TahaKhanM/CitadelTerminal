/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Tuple2;
import scala.collection.AbstractTraversable;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.Iterable;
import scala.collection.Iterable$class;
import scala.collection.IterableLike$class;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.Stream;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\u00012Q!\u0001\u0002\u0002\u0002\u001d\u0011\u0001#\u00112tiJ\f7\r^%uKJ\f'\r\\3\u000b\u0005\r!\u0011AC2pY2,7\r^5p]*\tQ!A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0005!y1c\u0001\u0001\n3A\u0019!bC\u0007\u000e\u0003\tI!\u0001\u0004\u0002\u0003'\u0005\u00137\u000f\u001e:bGR$&/\u0019<feN\f'\r\\3\u0011\u00059yA\u0002\u0001\u0003\u0007!\u0001!)\u0019A\t\u0003\u0003\u0005\u000b\"A\u0005\f\u0011\u0005M!R\"\u0001\u0003\n\u0005U!!a\u0002(pi\"Lgn\u001a\t\u0003']I!\u0001\u0007\u0003\u0003\u0007\u0005s\u0017\u0010E\u0002\u000b55I!a\u0007\u0002\u0003\u0011%#XM]1cY\u0016DQ!\b\u0001\u0005\u0002y\ta\u0001P5oSRtD#A\u0010\u0011\u0007)\u0001Q\u0002")
public abstract class AbstractIterable<A>
extends AbstractTraversable<A>
implements Iterable<A> {
    @Override
    public GenericCompanion<Iterable> companion() {
        return Iterable$class.companion(this);
    }

    @Override
    public Iterable<A> seq() {
        return Iterable$class.seq(this);
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
    public boolean isEmpty() {
        return IterableLike$class.isEmpty(this);
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
    public Iterator<Iterable<A>> grouped(int size2) {
        return IterableLike$class.grouped(this, size2);
    }

    @Override
    public Iterator<Iterable<A>> sliding(int size2) {
        return IterableLike$class.sliding(this, size2);
    }

    @Override
    public Iterator<Iterable<A>> sliding(int size2, int step) {
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
    public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<Iterable<A>, Tuple2<A1, B>, That> bf) {
        return (That)IterableLike$class.zip(this, that, bf);
    }

    @Override
    public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<Iterable<A>, Tuple2<A1, B>, That> bf) {
        return (That)IterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
    }

    @Override
    public <A1, That> That zipWithIndex(CanBuildFrom<Iterable<A>, Tuple2<A1, Object>, That> bf) {
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
    public IterableView<A, Iterable<A>> view(int from2, int until2) {
        return IterableLike$class.view(this, from2, until2);
    }

    public AbstractIterable() {
        GenIterable$class.$init$(this);
        IterableLike$class.$init$(this);
        Iterable$class.$init$(this);
    }
}

