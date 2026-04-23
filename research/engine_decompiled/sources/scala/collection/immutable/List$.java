/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function1;
import scala.Function1$class;
import scala.Serializable;
import scala.collection.Seq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;

public final class List$
extends SeqFactory<List>
implements Serializable {
    public static final List$ MODULE$;
    private final Object partialNotApplied;

    static {
        new List$();
    }

    public <A> CanBuildFrom<List<?>, A, List<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, List<A>> newBuilder() {
        return new ListBuffer();
    }

    @Override
    public <A> List<A> empty() {
        return Nil$.MODULE$;
    }

    @Override
    public <A> List<A> apply(Seq<A> xs) {
        return xs.toList();
    }

    public Object partialNotApplied() {
        return this.partialNotApplied;
    }

    private Object readResolve() {
        return MODULE$;
    }

    private List$() {
        MODULE$ = this;
        this.partialNotApplied = new Function1<Object, Object>(){

            public boolean apply$mcZD$sp(double v1) {
                return Function1$class.apply$mcZD$sp(this, v1);
            }

            public double apply$mcDD$sp(double v1) {
                return Function1$class.apply$mcDD$sp(this, v1);
            }

            public float apply$mcFD$sp(double v1) {
                return Function1$class.apply$mcFD$sp(this, v1);
            }

            public int apply$mcID$sp(double v1) {
                return Function1$class.apply$mcID$sp(this, v1);
            }

            public long apply$mcJD$sp(double v1) {
                return Function1$class.apply$mcJD$sp(this, v1);
            }

            public void apply$mcVD$sp(double v1) {
                Function1$class.apply$mcVD$sp(this, v1);
            }

            public boolean apply$mcZF$sp(float v1) {
                return Function1$class.apply$mcZF$sp(this, v1);
            }

            public double apply$mcDF$sp(float v1) {
                return Function1$class.apply$mcDF$sp(this, v1);
            }

            public float apply$mcFF$sp(float v1) {
                return Function1$class.apply$mcFF$sp(this, v1);
            }

            public int apply$mcIF$sp(float v1) {
                return Function1$class.apply$mcIF$sp(this, v1);
            }

            public long apply$mcJF$sp(float v1) {
                return Function1$class.apply$mcJF$sp(this, v1);
            }

            public void apply$mcVF$sp(float v1) {
                Function1$class.apply$mcVF$sp(this, v1);
            }

            public boolean apply$mcZI$sp(int v1) {
                return Function1$class.apply$mcZI$sp(this, v1);
            }

            public double apply$mcDI$sp(int v1) {
                return Function1$class.apply$mcDI$sp(this, v1);
            }

            public float apply$mcFI$sp(int v1) {
                return Function1$class.apply$mcFI$sp(this, v1);
            }

            public int apply$mcII$sp(int v1) {
                return Function1$class.apply$mcII$sp(this, v1);
            }

            public long apply$mcJI$sp(int v1) {
                return Function1$class.apply$mcJI$sp(this, v1);
            }

            public void apply$mcVI$sp(int v1) {
                Function1$class.apply$mcVI$sp(this, v1);
            }

            public boolean apply$mcZJ$sp(long v1) {
                return Function1$class.apply$mcZJ$sp(this, v1);
            }

            public double apply$mcDJ$sp(long v1) {
                return Function1$class.apply$mcDJ$sp(this, v1);
            }

            public float apply$mcFJ$sp(long v1) {
                return Function1$class.apply$mcFJ$sp(this, v1);
            }

            public int apply$mcIJ$sp(long v1) {
                return Function1$class.apply$mcIJ$sp(this, v1);
            }

            public long apply$mcJJ$sp(long v1) {
                return Function1$class.apply$mcJJ$sp(this, v1);
            }

            public void apply$mcVJ$sp(long v1) {
                Function1$class.apply$mcVJ$sp(this, v1);
            }

            public <A> Function1<A, Object> compose(Function1<A, Object> g) {
                return Function1$class.compose(this, g);
            }

            public <A> Function1<Object, A> andThen(Function1<Object, A> g) {
                return Function1$class.andThen(this, g);
            }

            public String toString() {
                return Function1$class.toString(this);
            }

            public Object apply(Object x) {
                return this;
            }
            {
                Function1$class.$init$(this);
            }
        };
    }
}

