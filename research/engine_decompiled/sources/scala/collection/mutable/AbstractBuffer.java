/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.GenTraversableOnce;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.generic.Shrinkable;
import scala.collection.generic.Shrinkable$class;
import scala.collection.generic.Subtractable$class;
import scala.collection.mutable.AbstractSeq;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Buffer$class;
import scala.collection.mutable.BufferLike$class;
import scala.collection.script.Message;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\t2Q!\u0001\u0002\u0002\u0002%\u0011a\"\u00112tiJ\f7\r\u001e\"vM\u001a,'O\u0003\u0002\u0004\t\u00059Q.\u001e;bE2,'BA\u0003\u0007\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u000f\u0005)1oY1mC\u000e\u0001QC\u0001\u0006\u0012'\r\u00011b\u0007\t\u0004\u00195yQ\"\u0001\u0002\n\u00059\u0011!aC!cgR\u0014\u0018m\u0019;TKF\u0004\"\u0001E\t\r\u0001\u0011)!\u0003\u0001b\u0001'\t\t\u0011)\u0005\u0002\u00151A\u0011QCF\u0007\u0002\r%\u0011qC\u0002\u0002\b\u001d>$\b.\u001b8h!\t)\u0012$\u0003\u0002\u001b\r\t\u0019\u0011I\\=\u0011\u00071ar\"\u0003\u0002\u001e\u0005\t1!)\u001e4gKJDQa\b\u0001\u0005\u0002\u0001\na\u0001P5oSRtD#A\u0011\u0011\u00071\u0001q\u0002")
public abstract class AbstractBuffer<A>
extends AbstractSeq<A>
implements Buffer<A> {
    @Override
    public GenericCompanion<Buffer> companion() {
        return Buffer$class.companion(this);
    }

    @Override
    public void remove(int n, int count2) {
        BufferLike$class.remove(this, n, count2);
    }

    @Override
    public Buffer $minus$eq(Object x) {
        return BufferLike$class.$minus$eq(this, x);
    }

    @Override
    public Buffer $plus$plus$eq$colon(TraversableOnce xs) {
        return BufferLike$class.$plus$plus$eq$colon(this, xs);
    }

    @Override
    public void append(Seq<A> elems) {
        BufferLike$class.append(this, elems);
    }

    @Override
    public void appendAll(TraversableOnce<A> xs) {
        BufferLike$class.appendAll(this, xs);
    }

    @Override
    public void prepend(Seq<A> elems) {
        BufferLike$class.prepend(this, elems);
    }

    @Override
    public void prependAll(TraversableOnce<A> xs) {
        BufferLike$class.prependAll(this, xs);
    }

    @Override
    public void insert(int n, Seq<A> elems) {
        BufferLike$class.insert(this, n, elems);
    }

    @Override
    public void trimStart(int n) {
        BufferLike$class.trimStart(this, n);
    }

    @Override
    public void trimEnd(int n) {
        BufferLike$class.trimEnd(this, n);
    }

    @Override
    public void $less$less(Message<A> cmd) {
        BufferLike$class.$less$less(this, cmd);
    }

    @Override
    public String stringPrefix() {
        return BufferLike$class.stringPrefix(this);
    }

    @Override
    public Seq<A> readOnly() {
        return BufferLike$class.readOnly(this);
    }

    @Override
    public Buffer<A> $plus$plus(GenTraversableOnce<A> xs) {
        return BufferLike$class.$plus$plus(this, xs);
    }

    @Override
    public Buffer<A> $minus(A elem) {
        return BufferLike$class.$minus(this, elem);
    }

    @Override
    public Buffer<A> $minus(A elem1, A elem2, Seq<A> elems) {
        return BufferLike$class.$minus(this, elem1, elem2, elems);
    }

    @Override
    public Buffer<A> $minus$minus(GenTraversableOnce<A> xs) {
        return BufferLike$class.$minus$minus(this, xs);
    }

    @Override
    public Buffer<A> clone() {
        return BufferLike$class.clone(this);
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
    public Growable<A> $plus$eq(A elem1, A elem2, Seq<A> elems) {
        return Growable$class.$plus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Growable<A> $plus$plus$eq(TraversableOnce<A> xs) {
        return Growable$class.$plus$plus$eq(this, xs);
    }

    public AbstractBuffer() {
        Growable$class.$init$(this);
        Shrinkable$class.$init$(this);
        Subtractable$class.$init$(this);
        BufferLike$class.$init$(this);
        Buffer$class.$init$(this);
    }
}

