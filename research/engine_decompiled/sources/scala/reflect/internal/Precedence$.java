/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function1;
import scala.Function1$class;
import scala.Predef$;
import scala.StringContext;
import scala.collection.immutable.StringOps;
import scala.reflect.internal.Chars$;
import scala.reflect.internal.Precedence;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt;

public final class Precedence$
implements Function1<Object, Precedence> {
    public static final Precedence$ MODULE$;
    private final String ErrorName;

    static {
        new Precedence$();
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
    public <A> Function1<A, Precedence> compose(Function1<A, Object> g) {
        return Function1$class.compose(this, g);
    }

    @Override
    public <A> Function1<Object, A> andThen(Function1<Precedence, A> g) {
        return Function1$class.andThen(this, g);
    }

    @Override
    public String toString() {
        return Function1$class.toString(this);
    }

    private String ErrorName() {
        return this.ErrorName;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean isAssignmentOp(String name) {
        if ("!=".equals(name)) {
            return false;
        }
        if ("<=".equals(name)) {
            return false;
        }
        if (">=".equals(name)) {
            return false;
        }
        if ("".equals(name)) {
            return false;
        }
        boolean bl = false;
        if (bl) {
            return false;
        }
        Predef$ predef$ = Predef$.MODULE$;
        if (BoxesRunTime.unboxToChar(new StringOps(name).last()) != '=') return false;
        Predef$ predef$2 = Predef$.MODULE$;
        if (BoxesRunTime.unboxToChar(new StringOps(name).head()) == '=') return false;
        Predef$ predef$3 = Predef$.MODULE$;
        if (!Chars$.MODULE$.isOperatorPart(BoxesRunTime.unboxToChar(new StringOps(name).head()))) return false;
        return true;
    }

    private int firstChar(char ch) {
        int n;
        switch (ch) {
            default: {
                if (Chars$.MODULE$.isScalaLetter(ch)) {
                    n = 1;
                    break;
                }
                n = 10;
                break;
            }
            case '%': 
            case '*': 
            case '/': {
                n = 9;
                break;
            }
            case '+': 
            case '-': {
                n = 8;
                break;
            }
            case ':': {
                n = 7;
                break;
            }
            case '<': 
            case '>': {
                n = 6;
                break;
            }
            case '!': 
            case '=': {
                n = 5;
                break;
            }
            case '&': {
                n = 4;
                break;
            }
            case '^': {
                n = 3;
                break;
            }
            case '|': {
                n = 2;
            }
        }
        return this.apply(n);
    }

    @Override
    public int apply(int level) {
        return level;
    }

    @Override
    public int apply(String name) {
        boolean bl;
        if ("".equals(name)) {
            bl = true;
        } else {
            String string2 = this.ErrorName();
            bl = !(string2 != null ? !string2.equals(name) : name != null);
        }
        int n = bl ? this.apply(-1) : (this.isAssignmentOp(name) ? this.apply(0) : this.firstChar(name.charAt(0)));
        return n;
    }

    public final int compare$extension(int $this, int that) {
        Predef$ predef$ = Predef$.MODULE$;
        return new RichInt($this).compare(BoxesRunTime.boxToInteger(that));
    }

    public final String toString$extension(int $this) {
        return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Precedence(", ")"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger($this)}));
    }

    public final int hashCode$extension(int $this) {
        return ((Object)BoxesRunTime.boxToInteger($this)).hashCode();
    }

    public final boolean equals$extension(int $this, Object x$1) {
        int n;
        boolean bl = x$1 instanceof Precedence;
        return bl && $this == (n = ((Precedence)x$1).level());
    }

    private Precedence$() {
        MODULE$ = this;
        Function1$class.$init$(this);
        this.ErrorName = "<error>";
    }
}

