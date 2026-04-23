/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.transform;

import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.LinearSeqOptimized;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.StringBuilder;
import scala.math.Ordering$Int$;
import scala.reflect.internal.Names;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.transform.Erasure;
import scala.runtime.BoxesRunTime;

public abstract class Erasure$class {
    public static int unboundedGenericArrayLevel(Erasure $this, Types.Type tp) {
        int n;
        Option<Tuple2<Object, Types.Type>> option = $this.GenericArray().unapply(tp);
        if (option.isEmpty() || option.get()._2().$less$colon$less($this.global().definitions().AnyRefTpe())) {
            Types.RefinedType refinedType;
            if (tp instanceof Types.RefinedType && (refinedType = (Types.RefinedType)tp).parents().nonEmpty()) {
                Object a = ((TraversableOnce)refinedType.parents().map(new Serializable($this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Erasure $outer;

                    public final int apply(Types.Type tp) {
                        return this.$outer.unboundedGenericArrayLevel(tp);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }, List$.MODULE$.canBuildFrom())).max(Ordering$Int$.MODULE$);
                Serializable serializable = new Serializable($this, tp){
                    public static final long serialVersionUID = 0L;
                    private final Types.Type tp$1;

                    public final String apply() {
                        return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Unbounded generic level for ", " is"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.tp$1}));
                    }
                    {
                        this.tp$1 = tp$1;
                    }
                };
                SymbolTable symbolTable = $this.global();
                symbolTable.log((Function0<Object>)((Object)new Serializable(symbolTable, (Function0)((Object)serializable), a){
                    public static final long serialVersionUID = 0L;
                    private final Function0 msg$1;
                    private final Object result$1;

                    public final String apply() {
                        return new StringBuilder().append((Object)((String)this.msg$1.apply())).append((Object)": ").append(this.result$1).toString();
                    }
                    {
                        this.msg$1 = msg$1;
                        this.result$1 = result$1;
                    }
                }));
                n = BoxesRunTime.unboxToInt(a);
            } else {
                n = 0;
            }
        } else {
            n = option.get()._1$mcI$sp();
        }
        return n;
    }

    public static Types.Type rebindInnerClass(Erasure $this, Types.Type pre, Symbols.Symbol cls) {
        return cls.isTopLevel() || cls.isLocalToBlock() ? pre : cls.owner().tpe_$times();
    }

    public static Types.Type erasedValueClassArg(Erasure $this, Types.TypeRef tref) {
        Types.Type type;
        Predef$.MODULE$.assert(!$this.global().phase().erasedTypes());
        Symbols.Symbol clazz = tref.sym();
        if ($this.valueClassIsParametric(clazz)) {
            Types.Type underlying = tref.memberType(clazz.derivedValueClassUnbox()).resultType();
            type = $this.boxingErasure().apply(underlying);
        } else {
            type = $this.scalaErasure().apply($this.global().definitions().underlyingOfValueClass(clazz));
        }
        return type;
    }

    public static boolean valueClassIsParametric(Erasure $this, Symbols.Symbol clazz) {
        Predef$.MODULE$.assert(!$this.global().phase().erasedTypes());
        return clazz.typeParams().contains(clazz.derivedValueClassUnbox().tpe().resultType().typeSymbol());
    }

    public static boolean verifyJavaErasure(Erasure $this) {
        return false;
    }

    public static Erasure.ErasureMap erasure(Erasure $this, Symbols.Symbol sym) {
        Symbols.Symbol symbol = sym;
        Symbols.NoSymbol noSymbol = $this.global().NoSymbol();
        return (symbol == null ? noSymbol != null : !symbol.equals(noSymbol)) && sym.enclClass().isJavaDefined() ? ($this.verifyJavaErasure() && sym.isMethod() ? $this.verifiedJavaErasure() : $this.javaErasure()) : $this.scalaErasure();
    }

    public static Types.Type specialErasure(Erasure $this, Symbols.Symbol sym, Types.Type tp) {
        Symbols.Symbol symbol = sym;
        Symbols.NoSymbol noSymbol = $this.global().NoSymbol();
        return (symbol == null ? noSymbol != null : !symbol.equals(noSymbol)) && sym.enclClass().isJavaDefined() ? $this.erasure(sym).apply(tp) : (sym.isClassConstructor() ? $this.specialConstructorErasure(sym.owner(), tp) : $this.specialScalaErasure().apply(tp));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Types.Type specialConstructorErasure(Erasure $this, Symbols.Symbol clazz, Types.Type tpe) {
        if (tpe instanceof Types.PolyType) {
            Types.PolyType polyType = (Types.PolyType)tpe;
            return $this.specialConstructorErasure(clazz, polyType.resultType());
        }
        if (tpe instanceof Types.ExistentialType) {
            Types.ExistentialType existentialType = (Types.ExistentialType)tpe;
            return $this.specialConstructorErasure(clazz, existentialType.underlying());
        }
        if (tpe instanceof Types.MethodType) {
            Types.MethodType methodType = (Types.MethodType)tpe;
            return new Types.MethodType($this.global(), $this.global().cloneSymbolsAndModify(methodType.params(), $this.specialScalaErasure()), $this.specialConstructorErasure(clazz, methodType.resultType()));
        }
        if (tpe instanceof Types.TypeRef) {
            Types.TypeRef typeRef = (Types.TypeRef)tpe;
            Symbols.Symbol symbol = clazz;
            Symbols.Symbol symbol2 = typeRef.sym();
            if (symbol == null) {
                if (symbol2 == null) return $this.global().typeRef(typeRef.pre(), clazz, Nil$.MODULE$);
            } else if (symbol.equals(symbol2)) {
                return $this.global().typeRef(typeRef.pre(), clazz, Nil$.MODULE$);
            }
        }
        Symbols.Symbol symbol = clazz;
        Symbols.ClassSymbol classSymbol = $this.global().definitions().ArrayClass();
        if (symbol == null) {
            if (classSymbol == null) return $this.specialScalaErasure().apply(tpe);
        } else if (symbol.equals(classSymbol)) return $this.specialScalaErasure().apply(tpe);
        if (tpe.isError()) return $this.specialScalaErasure().apply(tpe);
        Symbols.Symbol symbol3 = clazz;
        Symbols.ClassSymbol classSymbol2 = $this.global().definitions().ArrayClass();
        Serializable serializable = new Serializable($this, tpe, clazz){
            public static final long serialVersionUID = 0L;
            public final Types.Type x1$1;
            public final Symbols.Symbol clazz$1;

            public final String apply() {
                return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"!!! unexpected constructor erasure ", " for ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.x1$1, this.clazz$1}));
            }
            {
                this.x1$1 = x1$1;
                this.clazz$1 = clazz$1;
            }
        };
        boolean bl = !(symbol3 == null ? classSymbol2 != null : !symbol3.equals(classSymbol2)) || tpe.isError();
        Predef$ predef$ = Predef$.MODULE$;
        if (bl) return $this.specialScalaErasure().apply(tpe);
        throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"!!! unexpected constructor erasure ", " for ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{serializable.x1$1, serializable.clazz$1}))).toString());
    }

    public static Types.Type intersectionDominator(Erasure $this, List parents2) {
        Iterator<Types.Type> cs;
        List psyms;
        return parents2.isEmpty() ? $this.global().definitions().ObjectTpe() : ((psyms = parents2.map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Symbols.Symbol apply(Types.Type x$3) {
                return x$3.typeSymbol();
            }
        }, List$.MODULE$.canBuildFrom())).contains($this.global().definitions().ArrayClass()) ? $this.global().definitions().arrayType($this.intersectionDominator(((List)parents2.filter((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Erasure $outer;

            public final boolean apply(Types.Type x$4) {
                Symbols.Symbol symbol = x$4.typeSymbol();
                Symbols.ClassSymbol classSymbol = this.$outer.global().definitions().ArrayClass();
                return !(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }))).map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Types.Type apply(Types.Type x$5) {
                return x$5.typeArgs().head();
            }
        }, List$.MODULE$.canBuildFrom()))) : ((cs = parents2.iterator().filter((Function1<Types.Type, Object>)((Object)new Serializable($this, psyms){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Erasure $outer;
            private final List psyms$1;

            public final boolean apply(Types.Type p) {
                Symbols.Symbol psym = p.typeSymbol();
                psym.initialize();
                return psym.isClass() && !psym.isTrait() && Erasure$class.isUnshadowed$1(this.$outer, psym, this.psyms$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.psyms$1 = psyms$1;
            }
        }))).hasNext() ? cs : parents2.iterator().filter((Function1<Types.Type, Object>)((Object)new Serializable($this, psyms){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Erasure $outer;
            private final List psyms$1;

            public final boolean apply(Types.Type p) {
                return Erasure$class.isUnshadowed$1(this.$outer, p.typeSymbol(), this.psyms$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.psyms$1 = psyms$1;
            }
        }))).next());
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Types.Type transformInfo(Erasure $this, Symbols.Symbol sym, Types.Type tp) {
        Types.Type type;
        block14: {
            block13: {
                Symbols.Symbol symbol = sym;
                Symbols.MethodSymbol methodSymbol = $this.global().definitions().Object_asInstanceOf();
                if (!(symbol != null ? !symbol.equals(methodSymbol) : methodSymbol != null)) {
                    type = sym.info();
                    return type;
                }
                Symbols.Symbol symbol2 = sym;
                Symbols.MethodSymbol methodSymbol2 = $this.global().definitions().Object_isInstanceOf();
                if (!(symbol2 == null ? methodSymbol2 != null : !symbol2.equals(methodSymbol2))) break block13;
                Symbols.Symbol symbol3 = sym;
                Symbols.ClassSymbol classSymbol = $this.global().definitions().ArrayClass();
                if (symbol3 != null ? !symbol3.equals(classSymbol) : classSymbol != null) break block14;
            }
            type = new Types.PolyType($this.global(), sym.info().typeParams(), $this.specialErasure(sym, sym.info().resultType()));
            return type;
        }
        if (sym.isAbstractType()) {
            type = $this.global().TypeBounds().apply($this.global().WildcardType(), $this.global().WildcardType());
            return type;
        }
        if (sym.isTerm()) {
            Symbols.Symbol symbol = sym.owner();
            Symbols.ClassSymbol classSymbol = $this.global().definitions().ArrayClass();
            if (!(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null)) {
                if (sym.isClassConstructor()) {
                    if (!(tp instanceof Types.MethodType)) throw new MatchError(tp);
                    Types.MethodType methodType = (Types.MethodType)tp;
                    if (!(methodType.resultType() instanceof Types.TypeRef)) throw new MatchError(tp);
                    Types.TypeRef typeRef = (Types.TypeRef)methodType.resultType();
                    type = new Types.MethodType($this.global(), $this.global().cloneSymbolsAndModify(methodType.params(), (Function1<Types.Type, Types.Type>)((Object)new Serializable($this, sym){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Erasure $outer;
                        private final Symbols.Symbol sym$1;

                        public final Types.Type apply(Types.Type tp) {
                            return this.$outer.specialErasure(this.sym$1, tp);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.sym$1 = sym$1;
                        }
                    })), $this.global().typeRef($this.specialErasure(sym, typeRef.pre()), typeRef.sym(), typeRef.args()));
                    return type;
                }
                Names.Name name = sym.name();
                Names.TermName termName = $this.global().nme().apply();
                if (!(name != null ? !name.equals(termName) : termName != null)) {
                    type = tp;
                    return type;
                }
                Names.Name name2 = sym.name();
                Names.TermName termName2 = $this.global().nme().update();
                if (!(name2 != null ? !name2.equals(termName2) : termName2 != null)) {
                    if (!(tp instanceof Types.MethodType)) throw new MatchError(tp);
                    Types.MethodType methodType = (Types.MethodType)tp;
                    Some<List<Symbols.Symbol>> some = List$.MODULE$.unapplySeq(methodType.params());
                    if (some.isEmpty()) throw new MatchError(tp);
                    if (some.get() == null) throw new MatchError(tp);
                    if (((LinearSeqOptimized)some.get()).lengthCompare(2) != 0) throw new MatchError(tp);
                    Symbols.Symbol index = (Symbols.Symbol)((LinearSeqOptimized)some.get()).apply(0);
                    Symbols.Symbol tvar = (Symbols.Symbol)((LinearSeqOptimized)some.get()).apply(1);
                    type = new Types.MethodType($this.global(), (List<Symbols.Symbol>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.Symbol[]{index.cloneSymbol().setInfo($this.specialErasure(sym, index.tpe())), tvar})), $this.global().definitions().UnitTpe());
                    return type;
                }
                type = $this.specialErasure(sym, tp);
                return type;
            }
        }
        Symbols.Symbol symbol = sym.owner();
        Symbols.NoSymbol noSymbol = $this.global().NoSymbol();
        if (symbol == null ? noSymbol != null : !symbol.equals(noSymbol)) {
            Symbols.Symbol symbol4 = sym.owner().owner();
            Symbols.ClassSymbol classSymbol = $this.global().definitions().ArrayClass();
            if (!(symbol4 != null ? !symbol4.equals(classSymbol) : classSymbol != null)) {
                Symbols.Symbol symbol5 = sym;
                Object a = ((LinearSeqOptimized)$this.global().definitions().Array_update().paramss().head()).apply(1);
                if (!(symbol5 != null ? !symbol5.equals(a) : a != null)) {
                    type = tp;
                    return type;
                }
            }
        }
        type = $this.specialErasure(sym, tp);
        return type;
    }

    public static final boolean isUnshadowed$1(Erasure $this, Symbols.Symbol psym, List psyms$1) {
        return !psyms$1.exists(new Serializable($this, psym){
            public static final long serialVersionUID = 0L;
            private final Symbols.Symbol psym$1;

            public final boolean apply(Symbols.Symbol qsym) {
                return this.psym$1 != qsym && qsym.isNonBottomSubClass(this.psym$1);
            }
            {
                this.psym$1 = psym$1;
            }
        });
    }

    public static void $init$(Erasure $this) {
    }
}

