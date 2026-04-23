/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function3;
import scala.Serializable;
import scala.Tuple3;
import scala.collection.IterableLike;
import scala.collection.Iterator;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.mutable.Builder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.NonLocalReturnControl$mcV$sp;
import scala.runtime.NonLocalReturnControl$mcZ$sp;
import scala.runtime.Tuple3Zipped;

public final class Tuple3Zipped$ {
    public static final Tuple3Zipped$ MODULE$;

    static {
        new Tuple3Zipped$();
    }

    public final <B, To, El1, Repr1, El2, Repr2, El3, Repr3> To map$extension(Tuple3<TraversableLike<El1, Repr1>, IterableLike<El2, Repr2>, IterableLike<El3, Repr3>> $this, Function3<El1, El2, El3, B> f, CanBuildFrom<Repr1, B, To> cbf) {
        NonLocalReturnControl nonLocalReturnControl;
        block2: {
            Object object;
            Object object2 = new Object();
            try {
                Builder<B, To> b = cbf.apply($this._1().repr());
                Iterator<El2> elems2 = $this._2().iterator();
                Iterator<El3> elems3 = $this._3().iterator();
                $this._1().foreach(new Serializable(b, elems2, elems3, f, object2){
                    public static final long serialVersionUID = 0L;
                    private final Builder b$1;
                    private final Iterator elems2$1;
                    private final Iterator elems3$1;
                    private final Function3 f$1;
                    private final Object nonLocalReturnKey1$1;

                    public final Builder<B, To> apply(El1 el1) {
                        if (this.elems2$1.hasNext() && this.elems3$1.hasNext()) {
                            return this.b$1.$plus$eq(this.f$1.apply(el1, this.elems2$1.next(), this.elems3$1.next()));
                        }
                        throw new NonLocalReturnControl<To>(this.nonLocalReturnKey1$1, this.b$1.result());
                    }
                    {
                        this.b$1 = b$1;
                        this.elems2$1 = elems2$1;
                        this.elems3$1 = elems3$1;
                        this.f$1 = f$1;
                        this.nonLocalReturnKey1$1 = nonLocalReturnKey1$1;
                    }
                });
                object = b.result();
            }
            catch (NonLocalReturnControl nonLocalReturnControl2) {
                nonLocalReturnControl = nonLocalReturnControl2;
                if (nonLocalReturnControl2.key() != object2) break block2;
                object = nonLocalReturnControl.value();
            }
            return (To)object;
        }
        throw nonLocalReturnControl;
    }

    public final <B, To, El1, Repr1, El2, Repr2, El3, Repr3> To flatMap$extension(Tuple3<TraversableLike<El1, Repr1>, IterableLike<El2, Repr2>, IterableLike<El3, Repr3>> $this, Function3<El1, El2, El3, TraversableOnce<B>> f, CanBuildFrom<Repr1, B, To> cbf) {
        NonLocalReturnControl nonLocalReturnControl;
        block2: {
            Object object;
            Object object2 = new Object();
            try {
                Builder<B, To> b = cbf.apply($this._1().repr());
                Iterator<El2> elems2 = $this._2().iterator();
                Iterator<El3> elems3 = $this._3().iterator();
                $this._1().foreach(new Serializable(b, elems2, elems3, f, object2){
                    public static final long serialVersionUID = 0L;
                    private final Builder b$2;
                    private final Iterator elems2$2;
                    private final Iterator elems3$2;
                    private final Function3 f$2;
                    private final Object nonLocalReturnKey2$1;

                    public final Builder<B, To> apply(El1 el1) {
                        if (this.elems2$2.hasNext() && this.elems3$2.hasNext()) {
                            return (Builder)this.b$2.$plus$plus$eq((TraversableOnce)this.f$2.apply(el1, this.elems2$2.next(), this.elems3$2.next()));
                        }
                        throw new NonLocalReturnControl<To>(this.nonLocalReturnKey2$1, this.b$2.result());
                    }
                    {
                        this.b$2 = b$2;
                        this.elems2$2 = elems2$2;
                        this.elems3$2 = elems3$2;
                        this.f$2 = f$2;
                        this.nonLocalReturnKey2$1 = nonLocalReturnKey2$1;
                    }
                });
                object = b.result();
            }
            catch (NonLocalReturnControl nonLocalReturnControl2) {
                nonLocalReturnControl = nonLocalReturnControl2;
                if (nonLocalReturnControl2.key() != object2) break block2;
                object = nonLocalReturnControl.value();
            }
            return (To)object;
        }
        throw nonLocalReturnControl;
    }

    public final <To1, To2, To3, El1, Repr1, El2, Repr2, El3, Repr3> Tuple3<To1, To2, To3> filter$extension(Tuple3<TraversableLike<El1, Repr1>, IterableLike<El2, Repr2>, IterableLike<El3, Repr3>> $this, Function3<El1, El2, El3, Object> f, CanBuildFrom<Repr1, El1, To1> cbf1, CanBuildFrom<Repr2, El2, To2> cbf2, CanBuildFrom<Repr3, El3, To3> cbf3) {
        NonLocalReturnControl nonLocalReturnControl;
        block2: {
            Tuple3 tuple3;
            Object object = new Object();
            try {
                Builder<El1, To1> b1 = cbf1.apply($this._1().repr());
                Builder<El2, To2> b2 = cbf2.apply(((TraversableLike)$this._2()).repr());
                Builder<El3, To3> b3 = cbf3.apply(((TraversableLike)$this._3()).repr());
                Iterator<El2> elems2 = $this._2().iterator();
                Iterator<El3> elems3 = $this._3().iterator();
                $this._1().foreach(new Serializable(b1, b2, b3, elems2, elems3, f, object){
                    public static final long serialVersionUID = 0L;
                    private final Builder b1$1;
                    private final Builder b2$1;
                    private final Builder b3$1;
                    private final Iterator elems2$3;
                    private final Iterator elems3$3;
                    private final Function3 f$3;
                    private final Object nonLocalReturnKey3$1;

                    public final Object apply(El1 el1) {
                        if (this.elems2$3.hasNext() && this.elems3$3.hasNext()) {
                            Object object;
                            A el3;
                            A el2 = this.elems2$3.next();
                            if (BoxesRunTime.unboxToBoolean(this.f$3.apply(el1, el2, el3 = this.elems3$3.next()))) {
                                this.b1$1.$plus$eq(el1);
                                this.b2$1.$plus$eq(el2);
                                object = this.b3$1.$plus$eq(el3);
                            } else {
                                object = BoxedUnit.UNIT;
                            }
                            return object;
                        }
                        throw new NonLocalReturnControl<Tuple3>(this.nonLocalReturnKey3$1, Tuple3Zipped$.MODULE$.scala$runtime$Tuple3Zipped$$result$1(this.b1$1, this.b2$1, this.b3$1));
                    }
                    {
                        this.b1$1 = b1$1;
                        this.b2$1 = b2$1;
                        this.b3$1 = b3$1;
                        this.elems2$3 = elems2$3;
                        this.elems3$3 = elems3$3;
                        this.f$3 = f$3;
                        this.nonLocalReturnKey3$1 = nonLocalReturnKey3$1;
                    }
                });
                tuple3 = this.scala$runtime$Tuple3Zipped$$result$1(b1, b2, b3);
            }
            catch (NonLocalReturnControl nonLocalReturnControl2) {
                nonLocalReturnControl = nonLocalReturnControl2;
                if (nonLocalReturnControl2.key() != object) break block2;
                tuple3 = (Tuple3)nonLocalReturnControl.value();
            }
            return tuple3;
        }
        throw nonLocalReturnControl;
    }

    public final <El1, Repr1, El2, Repr2, El3, Repr3> boolean exists$extension(Tuple3<TraversableLike<El1, Repr1>, IterableLike<El2, Repr2>, IterableLike<El3, Repr3>> $this, Function3<El1, El2, El3, Object> p) {
        NonLocalReturnControl nonLocalReturnControl;
        block2: {
            boolean bl;
            Object object = new Object();
            try {
                Iterator<El2> elems2 = $this._2().iterator();
                Iterator<El3> elems3 = $this._3().iterator();
                $this._1().foreach(new Serializable(elems2, elems3, p, object){
                    public static final long serialVersionUID = 0L;
                    private final Iterator elems2$4;
                    private final Iterator elems3$4;
                    private final Function3 p$1;
                    private final Object nonLocalReturnKey4$1;

                    public final void apply(El1 el1) {
                        if (this.elems2$4.hasNext() && this.elems3$4.hasNext()) {
                            if (BoxesRunTime.unboxToBoolean(this.p$1.apply(el1, this.elems2$4.next(), this.elems3$4.next()))) {
                                throw new NonLocalReturnControl$mcZ$sp(this.nonLocalReturnKey4$1, true);
                            }
                            return;
                        }
                        throw new NonLocalReturnControl$mcZ$sp(this.nonLocalReturnKey4$1, false);
                    }
                    {
                        this.elems2$4 = elems2$4;
                        this.elems3$4 = elems3$4;
                        this.p$1 = p$1;
                        this.nonLocalReturnKey4$1 = nonLocalReturnKey4$1;
                    }
                });
                bl = false;
            }
            catch (NonLocalReturnControl nonLocalReturnControl2) {
                nonLocalReturnControl = nonLocalReturnControl2;
                if (nonLocalReturnControl2.key() != object) break block2;
                bl = nonLocalReturnControl.value$mcZ$sp();
            }
            return bl;
        }
        throw nonLocalReturnControl;
    }

    public final <El1, Repr1, El2, Repr2, El3, Repr3> boolean forall$extension(Tuple3<TraversableLike<El1, Repr1>, IterableLike<El2, Repr2>, IterableLike<El3, Repr3>> $this, Function3<El1, El2, El3, Object> p) {
        return !this.exists$extension($this, (Function3<El1, El2, El3, Object>)((Object)new Serializable(p){
            public static final long serialVersionUID = 0L;
            private final Function3 p$2;

            public final boolean apply(El1 x, El2 y, El3 z) {
                return !BoxesRunTime.unboxToBoolean(this.p$2.apply(x, y, z));
            }
            {
                this.p$2 = p$2;
            }
        }));
    }

    public final <U, El1, Repr1, El2, Repr2, El3, Repr3> void foreach$extension(Tuple3<TraversableLike<El1, Repr1>, IterableLike<El2, Repr2>, IterableLike<El3, Repr3>> $this, Function3<El1, El2, El3, U> f) {
        NonLocalReturnControl nonLocalReturnControl;
        block2: {
            Object object = new Object();
            try {
                Iterator<El2> elems2 = $this._2().iterator();
                Iterator<El3> elems3 = $this._3().iterator();
                $this._1().foreach(new Serializable(elems2, elems3, f, object){
                    public static final long serialVersionUID = 0L;
                    private final Iterator elems2$5;
                    private final Iterator elems3$5;
                    private final Function3 f$4;
                    private final Object nonLocalReturnKey5$1;

                    public final U apply(El1 el1) {
                        if (this.elems2$5.hasNext() && this.elems3$5.hasNext()) {
                            return (U)this.f$4.apply(el1, this.elems2$5.next(), this.elems3$5.next());
                        }
                        throw new NonLocalReturnControl$mcV$sp(this.nonLocalReturnKey5$1, BoxedUnit.UNIT);
                    }
                    {
                        this.elems2$5 = elems2$5;
                        this.elems3$5 = elems3$5;
                        this.f$4 = f$4;
                        this.nonLocalReturnKey5$1 = nonLocalReturnKey5$1;
                    }
                });
            }
            catch (NonLocalReturnControl nonLocalReturnControl2) {
                nonLocalReturnControl = nonLocalReturnControl2;
                if (nonLocalReturnControl2.key() != object) break block2;
                nonLocalReturnControl.value$mcV$sp();
            }
            return;
        }
        throw nonLocalReturnControl;
    }

    public final <El1, Repr1, El2, Repr2, El3, Repr3> int hashCode$extension(Tuple3<TraversableLike<El1, Repr1>, IterableLike<El2, Repr2>, IterableLike<El3, Repr3>> $this) {
        return $this.hashCode();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final <El1, Repr1, El2, Repr2, El3, Repr3> boolean equals$extension(Tuple3<TraversableLike<El1, Repr1>, IterableLike<El2, Repr2>, IterableLike<El3, Repr3>> $this, Object x$1) {
        if (!(x$1 instanceof Tuple3Zipped)) return false;
        boolean bl = true;
        if (!bl) return false;
        Tuple3 tuple3 = x$1 == null ? null : ((Tuple3Zipped)x$1).colls();
        Tuple3<TraversableLike<El1, Repr1>, IterableLike<El2, Repr2>, IterableLike<El3, Repr3>> tuple32 = $this;
        if (tuple32 != null) {
            if (!((Object)tuple32).equals(tuple3)) return false;
            return true;
        }
        if (tuple3 == null) return true;
        return false;
    }

    public final Tuple3 scala$runtime$Tuple3Zipped$$result$1(Builder b1$1, Builder b2$1, Builder b3$1) {
        return new Tuple3(b1$1.result(), b2$1.result(), b3$1.result());
    }

    private Tuple3Zipped$() {
        MODULE$ = this;
    }
}

