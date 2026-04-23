/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function1;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.SetLike;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u000113A!\u0001\u0002\u0001\u0013\tQ1+\u001a;Ck&dG-\u001a:\u000b\u0005\r!\u0011aB7vi\u0006\u0014G.\u001a\u0006\u0003\u000b\u0019\t!bY8mY\u0016\u001cG/[8o\u0015\u00059\u0011!B:dC2\f7\u0001A\u000b\u0004\u0015Uy2c\u0001\u0001\f\u001fA\u0011A\"D\u0007\u0002\r%\u0011aB\u0002\u0002\u0007\u0003:L(+\u001a4\u0011\tA\t2CH\u0007\u0002\u0005%\u0011!C\u0001\u0002\b\u0005VLG\u000eZ3s!\t!R\u0003\u0004\u0001\u0005\u000bY\u0001!\u0019A\f\u0003\u0003\u0005\u000b\"\u0001G\u000e\u0011\u00051I\u0012B\u0001\u000e\u0007\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0004\u000f\n\u0005u1!aA!osB\u0011Ac\b\u0003\u0006A\u0001\u0011\r!\t\u0002\u0005\u0007>dG.\u0005\u0002\u0019EI\u00191%J\u0015\u0007\t\u0011\u0002\u0001A\t\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0004M\u001d\u001aR\"\u0001\u0003\n\u0005!\"!aA*fiB!aEK\n\u001f\u0013\tYCAA\u0004TKRd\u0015n[3\t\u00115\u0002!\u0011!Q\u0001\ny\tQ!Z7qifDQa\f\u0001\u0005\u0002A\na\u0001P5oSRtDCA\u00193!\u0011\u0001\u0002a\u0005\u0010\t\u000b5r\u0003\u0019\u0001\u0010\t\u000fQ\u0002\u0001\u0019!C\tk\u0005)Q\r\\3ngV\ta\u0004C\u00048\u0001\u0001\u0007I\u0011\u0003\u001d\u0002\u0013\u0015dW-\\:`I\u0015\fHCA\u001d=!\ta!(\u0003\u0002<\r\t!QK\\5u\u0011\u001did'!AA\u0002y\t1\u0001\u001f\u00132\u0011\u0019y\u0004\u0001)Q\u0005=\u00051Q\r\\3ng\u0002BQ!\u0011\u0001\u0005\u0002\t\u000b\u0001\u0002\n9mkN$S-\u001d\u000b\u0003\u0007\u0012k\u0011\u0001\u0001\u0005\u0006\u000b\u0002\u0003\raE\u0001\u0002q\")q\t\u0001C\u0001\u0011\u0006)1\r\\3beR\t\u0011\bC\u0003K\u0001\u0011\u00051*\u0001\u0004sKN,H\u000e\u001e\u000b\u0002=\u0001")
public class SetBuilder<A, Coll extends Set<A> & SetLike<A, Coll>>
implements Builder<A, Coll> {
    private final Coll empty;
    private Coll elems;

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
    public <NewTo> Builder<A, NewTo> mapResult(Function1<Coll, NewTo> f) {
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

    public Coll elems() {
        return this.elems;
    }

    public void elems_$eq(Coll x$1) {
        this.elems = x$1;
    }

    @Override
    public SetBuilder<A, Coll> $plus$eq(A x) {
        this.elems_$eq(this.elems().$plus(x));
        return this;
    }

    @Override
    public void clear() {
        this.elems_$eq(this.empty);
    }

    @Override
    public Coll result() {
        return this.elems();
    }

    public SetBuilder(Coll empty) {
        this.empty = empty;
        Growable$class.$init$(this);
        Builder$class.$init$(this);
        this.elems = empty;
    }
}

