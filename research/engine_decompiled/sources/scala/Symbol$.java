/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Symbol;
import scala.UniquenessCache;

public final class Symbol$
extends UniquenessCache<String, Symbol>
implements Serializable {
    public static final Symbol$ MODULE$;

    static {
        new Symbol$();
    }

    @Override
    public Symbol apply(String name) {
        return (Symbol)super.apply(name);
    }

    @Override
    public Symbol valueFromKey(String name) {
        return new Symbol(name);
    }

    @Override
    public Option<String> keyFromValue(Symbol sym) {
        return new Some<String>(sym.name());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Symbol$() {
        MODULE$ = this;
    }
}

