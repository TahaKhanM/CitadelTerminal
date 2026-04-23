/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function1;
import scala.Function2;
import scala.Predef$;
import scala.Serializable;
import scala.StringContext;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.reflect.internal.ExistentialsAndSkolems;
import scala.reflect.internal.ExistentialsAndSkolems$;
import scala.reflect.internal.Names;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.runtime.Nothing$;

public abstract class ExistentialsAndSkolems$class {
    /*
     * WARNING - void declaration
     */
    public static List deriveFreshSkolems(SymbolTable $this, List tparams2) {
        List<Symbols.TypeSkolem> list2;
        int saved = $this.skolemizationLevel();
        $this.skolemizationLevel_$eq(0);
        try {
            public class Scala_reflect_internal_ExistentialsAndSkolems$Deskolemizer$1
            extends Types.LazyType {
                private final List<Symbols.Symbol> typeParams;
                private final List<Symbols.TypeSkolem> typeSkolems;
                public final /* synthetic */ SymbolTable $outer;

                public List<Symbols.Symbol> typeParams() {
                    return this.typeParams;
                }

                public List<Symbols.TypeSkolem> typeSkolems() {
                    return this.typeSkolems;
                }

                public void complete(Symbols.Symbol sym) {
                    sym.setInfo(sym.deSkolemize().info().substSym(this.typeParams(), this.typeSkolems()));
                }

                public /* synthetic */ SymbolTable scala$reflect$internal$ExistentialsAndSkolems$Deskolemizer$$$outer() {
                    return this.$outer;
                }

                public Scala_reflect_internal_ExistentialsAndSkolems$Deskolemizer$1(SymbolTable $outer, List tparams$1) {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    super($outer);
                    this.typeParams = tparams$1;
                    this.typeSkolems = this.typeParams().map(new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Scala_reflect_internal_ExistentialsAndSkolems$Deskolemizer$1 $outer;

                        public final Symbols.TypeSkolem apply(Symbols.Symbol x$1) {
                            return (Symbols.TypeSkolem)x$1.newTypeSkolem().setInfo(this.$outer);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }, List$.MODULE$.canBuildFrom());
                }
            }
            list2 = new Scala_reflect_internal_ExistentialsAndSkolems$Deskolemizer$1($this, tparams2).typeSkolems();
            $this.skolemizationLevel_$eq(saved);
        }
        catch (Throwable throwable) {
            void var2_2;
            $this.skolemizationLevel_$eq((int)var2_2);
            throw throwable;
        }
        return list2;
    }

    public static boolean isRawParameter(SymbolTable $this, Symbols.Symbol sym) {
        return sym.isTypeParameter() && sym.owner().isJavaDefined();
    }

    private static Map existentialBoundsExcludingHidden(SymbolTable $this, List hidden) {
        return $this.mapFrom(hidden, new Serializable($this, hidden){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final List hidden$1;

            public final Types.Type apply(Symbols.Symbol s2) {
                Types.Type type;
                Types.Type type2 = s2.existentialBound();
                if (type2 instanceof Types.TypeBounds) {
                    Types.TypeBounds typeBounds = (Types.TypeBounds)type2;
                    type = this.$outer.TypeBounds().apply(typeBounds.lo(), ExistentialsAndSkolems$class.hiBound$1(this.$outer, s2, this.hidden$1));
                } else {
                    type = ExistentialsAndSkolems$class.hiBound$1(this.$outer, s2, this.hidden$1);
                }
                return type;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.hidden$1 = hidden$1;
            }
        });
    }

    public static final Object existentialTransform(SymbolTable $this, List rawSyms, Types.Type tp, Symbols.Symbol rawOwner, Function2 creator) {
        Map allBounds = ExistentialsAndSkolems$class.existentialBoundsExcludingHidden($this, rawSyms);
        List<Symbols.Symbol> typeParams2 = rawSyms.map(new Serializable($this, allBounds, rawOwner){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SymbolTable $outer;
            private final Map allBounds$1;
            public final Symbols.Symbol rawOwner$1;

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final Symbols.TypeSymbol apply(Symbols.Symbol sym) {
                Symbols.Symbol symbol;
                Names.TypeName typeName;
                Names.Name name = sym.name();
                Names.TypeName typeName2 = name instanceof Names.TypeName ? (typeName = (Names.TypeName)name) : this.$outer.tpnme().singletonName(name);
                Types.Type bound = (Types.Type)this.allBounds$1.apply(sym);
                if (this.$outer.isRawParameter(sym)) {
                    Serializable serializable = new Serializable(this, sym){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ ExistentialsAndSkolems$.anonfun.3 $outer;
                        private final Symbols.Symbol sym$1;

                        public final Nothing$ apply() {
                            return this.$outer.$outer.abort(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"no owner provided for existential transform over raw parameter: ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.sym$1})));
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.sym$1 = sym$1;
                        }
                    };
                    Symbols.Symbol symbol2 = this.rawOwner$1;
                    if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) throw serializable.apply();
                    symbol = symbol2;
                } else {
                    symbol = sym.owner();
                }
                Symbols.Symbol sowner = symbol;
                Symbols.TypeSymbol quantified = sowner.newExistential(typeName2, sym.pos(), sowner.newExistential$default$3());
                return (Symbols.TypeSymbol)quantified.setInfo(bound.cloneInfo(quantified));
            }

            public /* synthetic */ SymbolTable scala$reflect$internal$ExistentialsAndSkolems$$anonfun$$$outer() {
                return this.$outer;
            }

            private final Symbols.Symbol rawOwner0$1(Symbols.Symbol sym$1) {
                Serializable serializable = new /* invalid duplicate definition of identical inner class */;
                Symbols.Symbol symbol = this.rawOwner$1;
                if (symbol != symbol.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                    return symbol;
                }
                throw serializable.apply();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.allBounds$1 = allBounds$1;
                this.rawOwner$1 = rawOwner$1;
            }
        }, List$.MODULE$.canBuildFrom());
        List typeParamTypes = typeParams2.map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Types.Type apply(Symbols.Symbol x$2) {
                return x$2.tpeHK();
            }
        }, List$.MODULE$.canBuildFrom());
        return creator.apply(typeParams2.map(new Serializable($this, typeParamTypes, rawSyms){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SymbolTable $outer;
            public final List typeParamTypes$1;
            public final List rawSyms$1;

            public final Symbols.Symbol apply(Symbols.Symbol x$3) {
                return x$3.modifyInfo((Function1<Types.Type, Types.Type>)((Object)new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ ExistentialsAndSkolems$.anonfun.existentialTransform.1 $outer;

                    public final Types.Type apply(Types.Type info2) {
                        return ExistentialsAndSkolems$class.doSubst$1(this.$outer.$outer, info2, this.$outer.typeParamTypes$1, this.$outer.rawSyms$1);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }));
            }

            public /* synthetic */ SymbolTable scala$reflect$internal$ExistentialsAndSkolems$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.typeParamTypes$1 = typeParamTypes$1;
                this.rawSyms$1 = rawSyms$1;
            }
        }, List$.MODULE$.canBuildFrom()), ExistentialsAndSkolems$class.doSubst$1($this, tp, typeParamTypes, rawSyms));
    }

    public static final Symbols.Symbol existentialTransform$default$3(SymbolTable $this) {
        return $this.NoSymbol();
    }

    public static final Types.Type packSymbols(SymbolTable $this, List hidden, Types.Type tp, Symbols.Symbol rawOwner) {
        return hidden.isEmpty() ? tp : $this.existentialTransform(hidden, tp, rawOwner, new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final Types.Type apply(List<Symbols.Symbol> tparams2, Types.Type tpe0) {
                return this.$outer.existentialAbstraction(tparams2, tpe0);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static final Symbols.Symbol packSymbols$default$3(SymbolTable $this) {
        return $this.NoSymbol();
    }

    public static final Types.Type safeBound$1(SymbolTable $this, Types.Type t, List hidden$1) {
        while (hidden$1.contains(t.typeSymbol())) {
            t = t.typeSymbol().existentialBound().bounds().hi();
        }
        return t;
    }

    public static final Types.Type hiBound$1(SymbolTable $this, Symbols.Symbol s2, List hidden$1) {
        Types.Type type;
        Types.Type type2 = ExistentialsAndSkolems$class.safeBound$1($this, s2.existentialBound().bounds().hi(), hidden$1);
        if (type2 instanceof Types.RefinedType) {
            Types.RefinedType refinedType = (Types.RefinedType)type2;
            List<Types.Type> parents1 = refinedType.parents().mapConserve(new Serializable($this, hidden$1){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;
                private final List hidden$1;

                public final Types.Type apply(Types.Type t) {
                    return ExistentialsAndSkolems$class.safeBound$1(this.$outer, t, this.hidden$1);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.hidden$1 = hidden$1;
                }
            });
            type = refinedType.parents() == parents1 ? refinedType : $this.copyRefinedType(refinedType, parents1, refinedType.decls());
        } else {
            type = type2;
        }
        return type;
    }

    public static final Types.Type doSubst$1(SymbolTable $this, Types.Type info2, List typeParamTypes$1, List rawSyms$1) {
        return info2.subst(rawSyms$1, typeParamTypes$1);
    }

    public static void $init$(SymbolTable $this) {
    }
}

