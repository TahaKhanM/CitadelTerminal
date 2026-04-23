/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Boolean$;
import scala.Byte$;
import scala.Char$;
import scala.Double$;
import scala.Float$;
import scala.Int$;
import scala.Long$;
import scala.Short$;
import scala.Specializable;
import scala.Tuple10;
import scala.Tuple4;
import scala.Tuple5;
import scala.Tuple7;
import scala.Tuple9;
import scala.Unit$;
import scala.package$;

public final class Specializable$ {
    public static final Specializable$ MODULE$;
    private final Specializable.Group<Tuple9<Byte$, Short$, Int$, Long$, Char$, Float$, Double$, Boolean$, Unit$>> Primitives;
    private final Specializable.Group<Tuple10<Byte$, Short$, Int$, Long$, Char$, Float$, Double$, Boolean$, Unit$, Object>> Everything;
    private final Specializable.Group<Tuple4<Int$, Long$, Float$, Double$>> Bits32AndUp;
    private final Specializable.Group<Tuple5<Byte$, Short$, Int$, Long$, Char$>> Integral;
    private final Specializable.Group<Tuple7<Byte$, Short$, Int$, Long$, Char$, Float$, Double$>> AllNumeric;
    private final Specializable.Group<Tuple5<Int$, Double$, Boolean$, Unit$, Object>> BestOfBreed;

    static {
        new Specializable$();
    }

    public final Specializable.Group<Tuple9<Byte$, Short$, Int$, Long$, Char$, Float$, Double$, Boolean$, Unit$>> Primitives() {
        return this.Primitives;
    }

    public final Specializable.Group<Tuple10<Byte$, Short$, Int$, Long$, Char$, Float$, Double$, Boolean$, Unit$, Object>> Everything() {
        return this.Everything;
    }

    public final Specializable.Group<Tuple4<Int$, Long$, Float$, Double$>> Bits32AndUp() {
        return this.Bits32AndUp;
    }

    public final Specializable.Group<Tuple5<Byte$, Short$, Int$, Long$, Char$>> Integral() {
        return this.Integral;
    }

    public final Specializable.Group<Tuple7<Byte$, Short$, Int$, Long$, Char$, Float$, Double$>> AllNumeric() {
        return this.AllNumeric;
    }

    public final Specializable.Group<Tuple5<Int$, Double$, Boolean$, Unit$, Object>> BestOfBreed() {
        return this.BestOfBreed;
    }

    private Specializable$() {
        MODULE$ = this;
        this.Primitives = new Specializable.Group<Tuple9<Byte$, Short$, Int$, Long$, Char$, Float$, Double$, Boolean$, Unit$>>(new Tuple9<Byte$, Short$, Int$, Long$, Char$, Float$, Double$, Boolean$, Unit$>(Byte$.MODULE$, Short$.MODULE$, Int$.MODULE$, Long$.MODULE$, Char$.MODULE$, Float$.MODULE$, Double$.MODULE$, Boolean$.MODULE$, Unit$.MODULE$));
        this.Everything = new Specializable.Group<Tuple10<Byte$, Short$, Int$, Long$, Char$, Float$, Double$, Boolean$, Unit$, Specializable>>(new Tuple10<Byte$, Short$, Int$, Long$, Char$, Float$, Double$, Boolean$, Unit$, Specializable>(Byte$.MODULE$, Short$.MODULE$, Int$.MODULE$, Long$.MODULE$, Char$.MODULE$, Float$.MODULE$, Double$.MODULE$, Boolean$.MODULE$, Unit$.MODULE$, package$.MODULE$.AnyRef()));
        this.Bits32AndUp = new Specializable.Group<Tuple4<Int$, Long$, Float$, Double$>>(new Tuple4<Int$, Long$, Float$, Double$>(Int$.MODULE$, Long$.MODULE$, Float$.MODULE$, Double$.MODULE$));
        this.Integral = new Specializable.Group<Tuple5<Byte$, Short$, Int$, Long$, Char$>>(new Tuple5<Byte$, Short$, Int$, Long$, Char$>(Byte$.MODULE$, Short$.MODULE$, Int$.MODULE$, Long$.MODULE$, Char$.MODULE$));
        this.AllNumeric = new Specializable.Group<Tuple7<Byte$, Short$, Int$, Long$, Char$, Float$, Double$>>(new Tuple7<Byte$, Short$, Int$, Long$, Char$, Float$, Double$>(Byte$.MODULE$, Short$.MODULE$, Int$.MODULE$, Long$.MODULE$, Char$.MODULE$, Float$.MODULE$, Double$.MODULE$));
        this.BestOfBreed = new Specializable.Group<Tuple5<Int$, Double$, Boolean$, Unit$, Specializable>>(new Tuple5<Int$, Double$, Boolean$, Unit$, Specializable>(Int$.MODULE$, Double$.MODULE$, Boolean$.MODULE$, Unit$.MODULE$, package$.MODULE$.AnyRef()));
    }
}

