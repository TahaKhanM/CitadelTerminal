/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Serializable;
import scala.collection.immutable.List;
import scala.reflect.internal.Variance;
import scala.runtime.BoxesRunTime;

public final class Variance$ {
    public static final Variance$ MODULE$;
    private final int Bivariant;
    private final int Covariant;
    private final int Contravariant;
    private final int Invariant;

    static {
        new Variance$();
    }

    public Variance.SbtCompat SbtCompat(int v) {
        return new Variance.SbtCompat(v);
    }

    public int fold(List<Variance> variances) {
        return variances.isEmpty() ? this.Bivariant() : variances.reduceLeft(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final int apply(int x$1, int x$2) {
                return Variance$.MODULE$.$amp$extension(x$1, x$2);
            }
        }).flags();
    }

    public int Bivariant() {
        return this.Bivariant;
    }

    public int Covariant() {
        return this.Covariant;
    }

    public int Contravariant() {
        return this.Contravariant;
    }

    public int Invariant() {
        return this.Invariant;
    }

    public final boolean isBivariant$extension(int $this) {
        return $this == 2;
    }

    public final boolean isCovariant$extension(int $this) {
        return $this == 1;
    }

    public final boolean isInvariant$extension(int $this) {
        return $this == 0;
    }

    public final boolean isContravariant$extension(int $this) {
        return $this == -1;
    }

    public final boolean isPositive$extension(int $this) {
        return $this > 0;
    }

    public final int $amp$extension(int $this, int other) {
        return $this == other ? $this : (this.isBivariant$extension($this) ? other : (this.isBivariant$extension(other) ? $this : this.Invariant()));
    }

    public final int $times$extension(int $this, int other) {
        return this.isPositive$extension(other) ? $this : (this.isContravariant$extension(other) ? this.flip$extension($this) : this.cut$extension($this));
    }

    public final int flip$extension(int $this) {
        return this.isCovariant$extension($this) ? this.Contravariant() : (this.isContravariant$extension($this) ? this.Covariant() : $this);
    }

    public final int cut$extension(int $this) {
        return this.isBivariant$extension($this) ? $this : this.Invariant();
    }

    public final String symbolicString$extension(int $this) {
        return this.isCovariant$extension($this) ? "+" : (this.isContravariant$extension($this) ? "-" : "");
    }

    public final String toString$extension(int $this) {
        return this.isContravariant$extension($this) ? "contravariant" : (this.isCovariant$extension($this) ? "covariant" : (this.isInvariant$extension($this) ? "invariant" : ""));
    }

    public final int hashCode$extension(int $this) {
        return ((Object)BoxesRunTime.boxToInteger($this)).hashCode();
    }

    public final boolean equals$extension(int $this, Object x$1) {
        int n;
        boolean bl = x$1 instanceof Variance;
        return bl && $this == (n = ((Variance)x$1).flags());
    }

    private Variance$() {
        MODULE$ = this;
        this.Bivariant = 2;
        this.Covariant = 1;
        this.Contravariant = -1;
        this.Invariant = 0;
    }
}

