/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Predef$;
import scala.Serializable;
import scala.StringContext;
import scala.collection.mutable.StringBuilder;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$class;

public abstract class Types$AliasTypeRef$class {
    public static Types.Type dealias(Types.AliasTypeRef $this) {
        return ((Types.TypeRef)((Object)$this)).typeParamsMatchArgs() ? $this.betaReduce().dealias() : $this.scala$reflect$internal$Types$AliasTypeRef$$super$dealias();
    }

    public static Types.Type narrow(Types.AliasTypeRef $this) {
        return ((Types.TypeRef)((Object)$this)).normalize().narrow();
    }

    public static Types.Type thisInfo(Types.AliasTypeRef $this) {
        return ((Types.TypeRef)((Object)$this)).normalize();
    }

    public static Types.Type prefix(Types.AliasTypeRef $this) {
        return $this != ((Types.TypeRef)((Object)$this)).normalize() ? ((Types.TypeRef)((Object)$this)).normalize().prefix() : ((Types.TypeRef)((Object)$this)).pre();
    }

    public static Symbols.Symbol termSymbol(Types.AliasTypeRef $this) {
        return $this != ((Types.TypeRef)((Object)$this)).normalize() ? ((Types.TypeRef)((Object)$this)).normalize().termSymbol() : $this.scala$reflect$internal$Types$AliasTypeRef$$super$termSymbol();
    }

    public static Symbols.Symbol typeSymbol(Types.AliasTypeRef $this) {
        return $this != ((Types.TypeRef)((Object)$this)).normalize() ? ((Types.TypeRef)((Object)$this)).normalize().typeSymbol() : ((Types.TypeRef)((Object)$this)).sym();
    }

    public static Types.Type normalizeImpl(Types.AliasTypeRef $this) {
        Types.Type type;
        if (((Types.TypeRef)((Object)$this)).typeParamsMatchArgs()) {
            type = $this.betaReduce().normalize();
        } else if (((Types.Type)((Object)$this)).isHigherKinded()) {
            type = $this.scala$reflect$internal$Types$AliasTypeRef$$super$normalizeImpl();
        } else {
            Symbols.Symbol overriddenSym;
            Symbols.Symbol symbol = overriddenSym = ((Types.TypeRef)((Object)$this)).sym().nextOverriddenSymbol();
            Symbols.NoSymbol noSymbol = ((Symbols)((Object)$this.scala$reflect$internal$Types$AliasTypeRef$$$outer())).NoSymbol();
            type = !(symbol != null ? !symbol.equals(noSymbol) : noSymbol != null) ? $this.scala$reflect$internal$Types$AliasTypeRef$$$outer().ErrorType() : ((Types.TypeRef)((Object)$this)).pre().memberType(overriddenSym).normalize();
        }
        return type;
    }

    public static Types.Type betaReduce(Types.AliasTypeRef $this) {
        return ((Types.TypeRef)((Object)$this)).transform(((Types.TypeRef)((Object)$this)).sym().info().resultType());
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Symbols.Symbol coevolveSym(Types.AliasTypeRef $this, Types.Type newPre) {
        Symbols.Symbol symbol;
        if (((Types.TypeRef)((Object)$this)).pre() != newPre) {
            Symbols.Symbol symbol2 = Types$class.scala$reflect$internal$Types$$embeddedSymbol((SymbolTable)$this.scala$reflect$internal$Types$AliasTypeRef$$$outer(), ((Types.TypeRef)((Object)$this)).pre(), ((Types.TypeRef)((Object)$this)).sym().name());
            Symbols.Symbol symbol3 = ((Types.TypeRef)((Object)$this)).sym();
            if (!(symbol2 != null ? !symbol2.equals(symbol3) : symbol3 != null)) {
                Symbols.Symbol newSym = Types$class.scala$reflect$internal$Types$$embeddedSymbol((SymbolTable)$this.scala$reflect$internal$Types$AliasTypeRef$$$outer(), newPre, ((Types.TypeRef)((Object)$this)).sym().name());
                ((SymbolTable)$this.scala$reflect$internal$Types$AliasTypeRef$$$outer()).debuglog((Function0<String>)((Object)new Serializable($this, newSym, newPre){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Types.AliasTypeRef $outer;
                    private final Symbols.Symbol newSym$1;
                    private final Types.Type newPre$1;

                    public final String apply() {
                        return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"co-evolve: ", " -> ", ", ", " : ", " -> ", " : ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{((Types.TypeRef)((Object)this.$outer)).pre(), this.newPre$1, ((Types.TypeRef)((Object)this.$outer)).sym(), ((Types.TypeRef)((Object)this.$outer)).sym().info(), this.newSym$1, this.newSym$1.info()}));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.newSym$1 = newSym$1;
                        this.newPre$1 = newPre$1;
                    }
                }));
                if (newSym != newSym.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                    symbol = newSym;
                    return symbol;
                }
                symbol = ((Types.TypeRef)((Object)$this)).sym();
                return symbol;
            }
        }
        symbol = ((Types.TypeRef)((Object)$this)).sym();
        return symbol;
    }

    public static String kind(Types.AliasTypeRef $this) {
        return "AliasTypeRef";
    }

    public static void $init$(Types.AliasTypeRef $this) {
        boolean bl = ((Types.TypeRef)((Object)$this)).sym().isAliasType();
        Predef$ predef$ = Predef$.MODULE$;
        if (!bl) {
            throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append(((Types.TypeRef)((Object)$this)).sym()).toString());
        }
    }
}

