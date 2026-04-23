/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.MatchError;
import scala.Serializable;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.Variance$;

public abstract class Variances$class {
    public static int varianceInTypes(SymbolTable $this, List tps, Symbols.Symbol tparam) {
        return Variance$.MODULE$.fold(tps.map(new Serializable($this, tparam){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final Symbols.Symbol tparam$1;

            public final int apply(Types.Type tp) {
                return this.$outer.varianceInType(tp, this.tparam$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tparam$1 = tparam$1;
            }
        }, List$.MODULE$.canBuildFrom()));
    }

    public static int varianceInType(SymbolTable $this, Types.Type tp, Symbols.Symbol tparam) {
        return Variances$class.inType$1($this, tp, tparam);
    }

    private static final int inArgs$1(SymbolTable $this, Symbols.Symbol sym, List args, Symbols.Symbol tparam$2) {
        return Variance$.MODULE$.fold($this.map2(args, sym.typeParams(), new Serializable($this, tparam$2){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final Symbols.Symbol tparam$2;

            public final int apply(Types.Type a, Symbols.Symbol p) {
                return Variance$.MODULE$.$times$extension(Variances$class.inType$1(this.$outer, a, this.tparam$2), p.variance());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tparam$2 = tparam$2;
            }
        }));
    }

    private static final int inSyms$1(SymbolTable $this, List syms, Symbols.Symbol tparam$2) {
        return Variance$.MODULE$.fold(syms.map(new Serializable($this, tparam$2){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final Symbols.Symbol tparam$2;

            public final int apply(Symbols.Symbol sym) {
                return Variances$class.inSym$1(this.$outer, sym, this.tparam$2);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tparam$2 = tparam$2;
            }
        }, List$.MODULE$.canBuildFrom()));
    }

    private static final int inTypes$1(SymbolTable $this, List tps, Symbols.Symbol tparam$2) {
        return Variance$.MODULE$.fold(tps.map(new Serializable($this, tparam$2){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final Symbols.Symbol tparam$2;

            public final int apply(Types.Type tp) {
                return Variances$class.inType$1(this.$outer, tp, this.tparam$2);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tparam$2 = tparam$2;
            }
        }, List$.MODULE$.canBuildFrom()));
    }

    public static final int inSym$1(SymbolTable $this, Symbols.Symbol sym, Symbols.Symbol tparam$2) {
        return sym.isAliasType() ? Variance$.MODULE$.cut$extension(Variances$class.inType$1($this, sym.info(), tparam$2)) : Variances$class.inType$1($this, sym.info(), tparam$2);
    }

    public static final int inType$1(SymbolTable $this, Types.Type tp, Symbols.Symbol tparam$2) {
        block19: {
            int n;
            block12: {
                block18: {
                    block17: {
                        block16: {
                            block15: {
                                block14: {
                                    block13: {
                                        Types.TypeRef typeRef;
                                        boolean bl;
                                        while (true) {
                                            bl = false;
                                            typeRef = null;
                                            boolean bl2 = $this.ErrorType().equals(tp) ? true : ($this.WildcardType().equals(tp) ? true : ($this.NoType().equals(tp) ? true : $this.NoPrefix().equals(tp)));
                                            if (bl2) {
                                                n = Variance$.MODULE$.Bivariant();
                                                break block12;
                                            }
                                            boolean bl3 = tp instanceof Types.ThisType ? true : tp instanceof Types.ConstantType;
                                            if (bl3) {
                                                n = Variance$.MODULE$.Bivariant();
                                                break block12;
                                            }
                                            if (tp instanceof Types.TypeRef) {
                                                bl = true;
                                                typeRef = (Types.TypeRef)tp;
                                                Symbols.Symbol symbol = tparam$2;
                                                Symbols.Symbol symbol2 = typeRef.sym();
                                                if (!(symbol != null ? !symbol.equals(symbol2) : symbol2 != null)) {
                                                    n = Variance$.MODULE$.Covariant();
                                                    break block12;
                                                }
                                            }
                                            if (tp instanceof Types.BoundedWildcardType) {
                                                Types.BoundedWildcardType boundedWildcardType = (Types.BoundedWildcardType)tp;
                                                tp = boundedWildcardType.bounds();
                                                continue;
                                            }
                                            if (tp instanceof Types.NullaryMethodType) {
                                                Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)tp;
                                                tp = nullaryMethodType.resultType();
                                                continue;
                                            }
                                            if (tp instanceof Types.SingleType) {
                                                Types.SingleType singleType = (Types.SingleType)tp;
                                                tp = singleType.pre();
                                                continue;
                                            }
                                            if (!bl || !tp.isHigherKinded()) break;
                                            tp = typeRef.pre();
                                        }
                                        if (!bl) break block13;
                                        n = Variance$.MODULE$.$amp$extension(Variances$class.inType$1($this, typeRef.pre(), tparam$2), Variances$class.inArgs$1($this, typeRef.sym(), typeRef.args(), tparam$2));
                                        break block12;
                                    }
                                    if (!(tp instanceof Types.TypeBounds)) break block14;
                                    Types.TypeBounds typeBounds = (Types.TypeBounds)tp;
                                    n = Variance$.MODULE$.$amp$extension(Variance$.MODULE$.flip$extension(Variances$class.inType$1($this, typeBounds.lo(), tparam$2)), Variances$class.inType$1($this, typeBounds.hi(), tparam$2));
                                    break block12;
                                }
                                if (!(tp instanceof Types.RefinedType)) break block15;
                                Types.RefinedType refinedType = (Types.RefinedType)tp;
                                n = Variance$.MODULE$.$amp$extension(Variances$class.inTypes$1($this, refinedType.parents(), tparam$2), Variances$class.inSyms$1($this, refinedType.decls().toList(), tparam$2));
                                break block12;
                            }
                            if (!(tp instanceof Types.MethodType)) break block16;
                            Types.MethodType methodType = (Types.MethodType)tp;
                            n = Variance$.MODULE$.$amp$extension(Variance$.MODULE$.flip$extension(Variances$class.inSyms$1($this, methodType.params(), tparam$2)), Variances$class.inType$1($this, methodType.resultType(), tparam$2));
                            break block12;
                        }
                        if (!(tp instanceof Types.PolyType)) break block17;
                        Types.PolyType polyType = (Types.PolyType)tp;
                        n = Variance$.MODULE$.$amp$extension(Variance$.MODULE$.flip$extension(Variances$class.inSyms$1($this, polyType.typeParams(), tparam$2)), Variances$class.inType$1($this, polyType.resultType(), tparam$2));
                        break block12;
                    }
                    if (!(tp instanceof Types.ExistentialType)) break block18;
                    Types.ExistentialType existentialType = (Types.ExistentialType)tp;
                    n = Variance$.MODULE$.$amp$extension(Variances$class.inSyms$1($this, existentialType.quantified(), tparam$2), Variances$class.inType$1($this, existentialType.underlying(), tparam$2));
                    break block12;
                }
                if (!(tp instanceof Types.AnnotatedType)) break block19;
                Types.AnnotatedType annotatedType = (Types.AnnotatedType)tp;
                n = Variance$.MODULE$.$amp$extension(Variances$class.inTypes$1($this, annotatedType.annotations().map(new Serializable($this){
                    public static final long serialVersionUID = 0L;

                    public final Types.Type apply(AnnotationInfos.AnnotationInfo x$2) {
                        return x$2.atp();
                    }
                }, List$.MODULE$.canBuildFrom()), tparam$2), Variances$class.inType$1($this, annotatedType.underlying(), tparam$2));
            }
            return n;
        }
        throw new MatchError(tp);
    }

    public static void $init$(SymbolTable $this) {
    }
}

