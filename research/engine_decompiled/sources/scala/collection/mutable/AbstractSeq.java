/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function1;
import scala.collection.generic.GenericCompanion;
import scala.collection.mutable.Cloneable$class;
import scala.collection.mutable.Iterable$class;
import scala.collection.mutable.Seq;
import scala.collection.mutable.Seq$class;
import scala.collection.mutable.SeqLike;
import scala.collection.mutable.SeqLike$class;
import scala.collection.mutable.Traversable$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\t2Q!\u0001\u0002\u0002\u0002%\u00111\"\u00112tiJ\f7\r^*fc*\u00111\u0001B\u0001\b[V$\u0018M\u00197f\u0015\t)a!\u0001\u0006d_2dWm\u0019;j_:T\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001+\tQ\u0001cE\u0002\u0001\u0017i\u00012\u0001D\u0007\u000f\u001b\u0005!\u0011BA\u0001\u0005!\ty\u0001\u0003\u0004\u0001\u0005\u000bE\u0001!\u0019\u0001\n\u0003\u0003\u0005\u000b\"aE\f\u0011\u0005Q)R\"\u0001\u0004\n\u0005Y1!a\u0002(pi\"Lgn\u001a\t\u0003)aI!!\u0007\u0004\u0003\u0007\u0005s\u0017\u0010E\u0002\u001c99i\u0011AA\u0005\u0003;\t\u00111aU3r\u0011\u0015y\u0002\u0001\"\u0001!\u0003\u0019a\u0014N\\5u}Q\t\u0011\u0005E\u0002\u001c\u00019\u0001")
public abstract class AbstractSeq<A>
extends scala.collection.AbstractSeq<A>
implements Seq<A> {
    @Override
    public GenericCompanion<Seq> companion() {
        return Seq$class.companion(this);
    }

    @Override
    public Seq<A> seq() {
        return Seq$class.seq(this);
    }

    @Override
    public Combiner<A, ParSeq<A>> parCombiner() {
        return SeqLike$class.parCombiner(this);
    }

    @Override
    public SeqLike<A, Seq<A>> transform(Function1<A, A> f) {
        return SeqLike$class.transform(this, f);
    }

    @Override
    public /* synthetic */ Object scala$collection$mutable$Cloneable$$super$clone() {
        return super.clone();
    }

    @Override
    public Object clone() {
        return Cloneable$class.clone(this);
    }

    public AbstractSeq() {
        Traversable$class.$init$(this);
        Iterable$class.$init$(this);
        Cloneable$class.$init$(this);
        SeqLike$class.$init$(this);
        Seq$class.$init$(this);
    }
}

