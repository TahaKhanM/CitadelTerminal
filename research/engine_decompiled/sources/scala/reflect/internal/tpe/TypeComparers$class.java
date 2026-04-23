/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.tpe;

import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Predef$;
import scala.Serializable;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.HashSet;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Definitions$DefinitionsClass$NothingClass$;
import scala.reflect.internal.Depth$;
import scala.reflect.internal.Names;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.TypesStats$;
import scala.reflect.internal.Variance$;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.tpe.TypeComparers;
import scala.reflect.internal.tpe.TypeConstraints;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;
import scala.reflect.internal.util.TriState$;
import scala.reflect.internal.util.package$;
import scala.runtime.BoxesRunTime;

public abstract class TypeComparers$class {
    public static HashSet pendingSubTypes(SymbolTable $this) {
        return $this.scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes();
    }

    public static int subsametypeRecursions(SymbolTable $this) {
        return $this.scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions();
    }

    public static void subsametypeRecursions_$eq(SymbolTable $this, int value) {
        $this.scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions_$eq(value);
    }

    private static boolean isUnifiable(SymbolTable $this, Types.Type pre1, Types.Type pre2) {
        return ($this.isEligibleForPrefixUnification(pre1) || $this.isEligibleForPrefixUnification(pre2)) && pre1.$eq$colon$eq(pre2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean isSameSpecializedSkolem(SymbolTable $this, Symbols.Symbol sym1, Symbols.Symbol sym2, Types.Type pre1, Types.Type pre2) {
        if (!sym1.isExistentialSkolem()) return false;
        if (!sym2.isExistentialSkolem()) return false;
        Names.Name name = sym1.name();
        Names.Name name2 = sym2.name();
        if (name == null) {
            if (name2 != null) {
                return false;
            }
        } else if (!name.equals(name2)) return false;
        if (!$this.phase().specialized()) return false;
        if (!sym1.info().$eq$colon$eq(sym2.info())) return false;
        if (!pre1.$eq$colon$eq(pre2)) return false;
        return true;
    }

    private static boolean isSubPre(SymbolTable $this, Types.Type pre1, Types.Type pre2, Symbols.Symbol sym) {
        boolean bl;
        if (pre1 != pre2 && pre1 != $this.NoPrefix() && pre2 != $this.NoPrefix() && pre1.$less$colon$less(pre2)) {
            MutableSettings.SettingValue settingValue = $this.settings().debug();
            MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
            if (BoxesRunTime.unboxToBoolean(settingValue.value())) {
                Predef$.MODULE$.println(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"new isSubPre ", ": ", " <:< ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{sym, pre1, pre2})));
            }
            bl = true;
        } else {
            bl = false;
        }
        return bl;
    }

    private static boolean equalSymsAndPrefixes(SymbolTable $this, Symbols.Symbol sym1, Types.Type pre1, Symbols.Symbol sym2, Types.Type pre2) {
        boolean bl;
        Symbols.Symbol symbol = sym1;
        if (!(symbol != null ? !symbol.equals(sym2) : sym2 != null)) {
            bl = sym1.hasPackageFlag() || sym1.owner().hasPackageFlag() || $this.phase().erasedTypes() || pre1.$eq$colon$eq(pre2);
        } else {
            Names.Name name = sym1.name();
            Names.Name name2 = sym2.name();
            bl = !(name != null ? !name.equals(name2) : name2 != null) && TypeComparers$class.isUnifiable($this, pre1, pre2);
        }
        return bl;
    }

    public static boolean isDifferentType(SymbolTable $this, Types.Type tp1, Types.Type tp2) {
        try {
            $this.subsametypeRecursions_$eq($this.subsametypeRecursions() + 1);
            return BoxesRunTime.unboxToBoolean($this.undoLog().undo(new Serializable($this, tp1, tp2){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;
                private final Types.Type tp1$4;
                private final Types.Type tp2$4;

                public final boolean apply() {
                    return this.apply$mcZ$sp();
                }

                public boolean apply$mcZ$sp() {
                    return !TypeComparers$class.scala$reflect$internal$tpe$TypeComparers$$isSameType1(this.$outer, this.tp1$4, this.tp2$4);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.tp1$4 = tp1$4;
                    this.tp2$4 = tp2$4;
                }
            }));
        }
        finally {
            $this.subsametypeRecursions_$eq($this.subsametypeRecursions() - 1);
        }
    }

    public static boolean isDifferentTypeConstructor(SymbolTable $this, Types.Type tp1, Types.Type tp2) {
        return !TypeComparers$class.isSameTypeConstructor($this, tp1, tp2);
    }

    private static boolean isSameTypeConstructor(SymbolTable $this, Types.TypeRef tr1, Types.TypeRef tr2) {
        Symbols.Symbol symbol = tr1.sym();
        Symbols.Symbol symbol2 = tr2.sym();
        return !(symbol != null ? !symbol.equals(symbol2) : symbol2 != null) && !$this.isDifferentType(tr1.pre(), tr2.pre());
    }

    private static boolean isSameTypeConstructor(SymbolTable $this, Types.Type tp1, Types.Type tp2) {
        return tp1 instanceof Types.TypeRef && tp2 instanceof Types.TypeRef && TypeComparers$class.isSameTypeConstructor($this, (Types.TypeRef)tp1, (Types.TypeRef)tp2);
    }

    /*
     * WARNING - void declaration
     */
    public static boolean isSameType(SymbolTable $this, Types.Type tp1, Types.Type tp2) {
        Throwable throwable2;
        try {
            if (Statistics$.MODULE$.canEnable()) {
                Statistics.Counter counter = TypesStats$.MODULE$.sametypeCount();
                Statistics$ statistics$ = Statistics$.MODULE$;
                if (statistics$.scala$reflect$internal$util$Statistics$$_enabled() && counter != null) {
                    counter.value_$eq(counter.value() + 1);
                }
            }
            $this.subsametypeRecursions_$eq($this.subsametypeRecursions() + 1);
            List<TypeConstraints.UndoPair<Types.TypeVar, TypeConstraints.TypeConstraint>> before = $this.undoLog().log();
            boolean result2 = false;
            try {
                result2 = TypeComparers$class.scala$reflect$internal$tpe$TypeComparers$$isSameType1($this, tp1, tp2);
                if (!result2) {
                    $this.undoLog().undoTo(before);
                }
                return result2;
            }
            catch (Throwable throwable2) {
                void var5_6;
                if (var5_6 == false) {
                    void var4_5;
                    $this.undoLog().undoTo((List<TypeConstraints.UndoPair<Types.TypeVar, TypeConstraints.TypeConstraint>>)var4_5);
                }
            }
        }
        finally {
            $this.subsametypeRecursions_$eq($this.subsametypeRecursions() - 1);
        }
        throw throwable2;
    }

    private static boolean sameAnnotatedTypes(SymbolTable $this, Types.Type tp1, Types.Type tp2) {
        return $this.annotationsConform(tp1, tp2) && $this.annotationsConform(tp2, tp1) && tp1.withoutAnnotations().$eq$colon$eq(tp2.withoutAnnotations());
    }

    public static boolean scala$reflect$internal$tpe$TypeComparers$$isSameType1(SymbolTable $this, Types.Type tp1, Types.Type tp2) {
        int n = TypeComparers$class.typeRelationPreCheck($this, tp1, tp2);
        boolean bl = TriState$.MODULE$.isKnown$extension(n) ? TriState$.MODULE$.booleanValue$extension(n) : (BoxesRunTime.unboxToBoolean($this.typeHasAnnotations().apply(tp1)) || BoxesRunTime.unboxToBoolean($this.typeHasAnnotations().apply(tp2)) ? TypeComparers$class.sameAnnotatedTypes($this, tp1, tp2) : $this.isSameType2(tp1, tp2));
        return bl;
    }

    private static boolean isSameHKTypes(SymbolTable $this, Types.Type tp1, Types.Type tp2) {
        return tp1.isHigherKinded() && tp2.isHigherKinded() && tp1.normalize().$eq$colon$eq(tp2.normalize());
    }

    private static boolean isSameTypeRef(SymbolTable $this, Types.TypeRef tr1, Types.TypeRef tr2) {
        return TypeComparers$class.equalSymsAndPrefixes($this, tr1.sym(), tr1.pre(), tr2.sym(), tr2.pre()) && (TypeComparers$class.isSameHKTypes($this, tr1, tr2) || $this.isSameTypes(tr1.args(), tr2.args()));
    }

    private static boolean isSameSingletonType(SymbolTable $this, Types.SingletonType tp1, Types.SingletonType tp2) {
        Types.Type origin1 = TypeComparers$class.chaseDealiasedUnderlying$1($this, tp1);
        Types.Type origin2 = TypeComparers$class.chaseDealiasedUnderlying$1($this, tp2);
        return (origin1 != tp1 || origin2 != tp2) && origin1.$eq$colon$eq(origin2);
    }

    private static boolean isSameMethodType(SymbolTable $this, Types.MethodType mt1, Types.MethodType mt2) {
        return $this.isSameTypes(mt1.paramTypes(), mt2.paramTypes()) && mt1.resultType().$eq$colon$eq(mt2.resultType().substSym(mt2.params(), mt1.params())) && mt1.isImplicit() == mt2.isImplicit();
    }

    private static boolean equalTypeParamsAndResult(SymbolTable $this, List tparams1, Types.Type res1, List tparams2, Types.Type res2) {
        return $this.sameLength(tparams1, tparams2) && tparams1.corresponds(tparams2, new Serializable($this, tparams1, tparams2){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final List tparams1$1;
            private final List tparams2$1;

            public final boolean apply(Symbols.Symbol p1, Symbols.Symbol p2) {
                return TypeComparers$class.scala$reflect$internal$tpe$TypeComparers$$methodHigherOrderTypeParamsSameVariance(this.$outer, p1, p2) && p1.info().$eq$colon$eq(TypeComparers$class.subst$1(this.$outer, p2.info(), this.tparams1$1, this.tparams2$1));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tparams1$1 = tparams1$1;
                this.tparams2$1 = tparams2$1;
            }
        }) && res1.$eq$colon$eq(TypeComparers$class.subst$1($this, res2, tparams1, tparams2));
    }

    public static boolean scala$reflect$internal$tpe$TypeComparers$$methodHigherOrderTypeParamsSameVariance(SymbolTable $this, Symbols.Symbol sym1, Symbols.Symbol sym2) {
        return !$this.settings().isScala211() || TypeComparers$class.ignoreVariance$1($this, sym1) || TypeComparers$class.ignoreVariance$1($this, sym2) || sym1.variance() == sym2.variance();
    }

    private static boolean methodHigherOrderTypeParamsSubVariance(SymbolTable $this, Symbols.Symbol low, Symbols.Symbol high) {
        return !$this.settings().isScala211() || TypeComparers$class.scala$reflect$internal$tpe$TypeComparers$$methodHigherOrderTypeParamsSameVariance($this, low, high) || Variance$.MODULE$.isInvariant$extension(low.variance());
    }

    public static boolean isSameType2(SymbolTable $this, Types.Type tp1, Types.Type tp2) {
        return TypeComparers$class.sameTypeAndSameCaseClass$1($this, tp1, tp2) || TypeComparers$class.sameSingletonType$1($this, tp1, tp2) || TypeComparers$class.mutateNonTypeConstructs$1($this, tp1, tp2, tp1) || TypeComparers$class.mutateNonTypeConstructs$1($this, tp2, tp1, tp1) || TypeComparers$class.retry$1($this, $this.normalizePlus(tp1), $this.normalizePlus(tp2), tp1, tp2);
    }

    /*
     * Loose catch block
     * WARNING - void declaration
     */
    public static boolean isSubType(SymbolTable $this, Types.Type tp1, Types.Type tp2, int depth) {
        Throwable throwable;
        block15: {
            try {
                boolean bl;
                TypeComparers.SubTypePair p;
                $this.subsametypeRecursions_$eq($this.subsametypeRecursions() + 1);
                List<TypeConstraints.UndoPair<Types.TypeVar, TypeConstraints.TypeConstraint>> before = $this.undoLog().log();
                boolean result2 = false;
                if ($this.subsametypeRecursions() >= 50) {
                    p = new TypeComparers.SubTypePair($this, tp1, tp2);
                    if ($this.pendingSubTypes().apply(p)) {
                        bl = false;
                    } else {
                        $this.pendingSubTypes().$plus$eq((Object)p);
                        bl = TypeComparers$class.isSubType1($this, tp1, tp2, depth);
                        $this.pendingSubTypes().$minus$eq((Object)p);
                    }
                } else {
                    bl = result2 = TypeComparers$class.isSubType1($this, tp1, tp2, depth);
                }
                if (!result2) {
                    $this.undoLog().undoTo(before);
                }
                return result2;
                catch (Throwable throwable2) {
                    void var5_4;
                    try {
                        $this.pendingSubTypes().$minus$eq((Object)p);
                        throwable = throwable2;
                    }
                    catch (Throwable throwable3) {
                        void var6_5;
                        if (var6_5 != false) break block15;
                    }
                    $this.undoLog().undoTo((List<TypeConstraints.UndoPair<Types.TypeVar, TypeConstraints.TypeConstraint>>)var5_4);
                    {
                    }
                }
            }
            finally {
                $this.subsametypeRecursions_$eq($this.subsametypeRecursions() - 1);
            }
        }
        throw throwable;
    }

    public static int isSubType$default$3(SymbolTable $this) {
        return Depth$.MODULE$.AnyDepth();
    }

    private static int typeRelationPreCheck(SymbolTable $this, Types.Type tp1, Types.Type tp2) {
        return TypeComparers$class.isTrue$1($this, tp1, tp2) ? TriState$.MODULE$.True() : (TypeComparers$class.isFalse$1($this, tp1, tp2) ? TriState$.MODULE$.False() : TriState$.MODULE$.Unknown());
    }

    private static boolean isSubType1(SymbolTable $this, Types.Type tp1, Types.Type tp2, int depth) {
        int n = TypeComparers$class.typeRelationPreCheck($this, tp1, tp2);
        boolean bl = TriState$.MODULE$.isKnown$extension(n) ? TriState$.MODULE$.booleanValue$extension(n) : (BoxesRunTime.unboxToBoolean($this.typeHasAnnotations().apply(tp1)) || BoxesRunTime.unboxToBoolean($this.typeHasAnnotations().apply(tp2)) ? $this.annotationsConform(tp1, tp2) && tp1.withoutAnnotations().$less$colon$less(tp2.withoutAnnotations()) : TypeComparers$class.isSubType2($this, tp1, tp2, depth));
        return bl;
    }

    private static boolean isPolySubType(SymbolTable $this, Types.PolyType tp1, Types.PolyType tp2) {
        if (tp1 != null) {
            Tuple2<List<Symbols.Symbol>, Types.Type> tuple2 = new Tuple2<List<Symbols.Symbol>, Types.Type>(tp1.typeParams(), tp1.resultType());
            List<Symbols.Symbol> tparams1 = tuple2._1();
            Types.Type res1 = tuple2._2();
            if (tp2 != null) {
                List<Symbols.Symbol> substitutes;
                boolean isMethod;
                Tuple2<List<Symbols.Symbol>, Types.Type> tuple22 = new Tuple2<List<Symbols.Symbol>, Types.Type>(tp2.typeParams(), tp2.resultType());
                List<Symbols.Symbol> tparams2 = tuple22._1();
                Types.Type res2 = tuple22._2();
                return $this.sameLength(tparams1, tparams2) && tparams1.corresponds(tparams2, new Serializable($this, tparams1, tparams2, isMethod, substitutes = (isMethod = tparams1.head().owner().isMethod()) ? tparams1 : $this.cloneSymbols(tparams1)){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SymbolTable $outer;
                    private final List tparams1$2;
                    private final List tparams2$2;
                    private final boolean isMethod$1;
                    private final List substitutes$1;

                    public final boolean apply(Symbols.Symbol p1, Symbols.Symbol p2) {
                        return TypeComparers$class.cmp$1(this.$outer, p1, p2, this.tparams1$2, this.tparams2$2, this.isMethod$1, this.substitutes$1);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.tparams1$2 = tparams1$2;
                        this.tparams2$2 = tparams2$2;
                        this.isMethod$1 = isMethod$1;
                        this.substitutes$1 = substitutes$1;
                    }
                }) && TypeComparers$class.sub1$1($this, res1, tparams1, isMethod, substitutes).$less$colon$less(TypeComparers$class.sub2$1($this, res2, tparams2, substitutes));
            }
            throw new MatchError(tp2);
        }
        throw new MatchError(tp1);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean isThisAndSuperSubtype(SymbolTable $this, Types.Type tp1, Types.Type tp2) {
        Tuple2<Types.Type, Types.Type> tuple2 = new Tuple2<Types.Type, Types.Type>(tp1, tp2);
        if (!(tuple2._1() instanceof Types.SingleType)) return false;
        Types.SingleType singleType = (Types.SingleType)tuple2._1();
        if (!(singleType.pre() instanceof Types.ThisType)) return false;
        Types.ThisType thisType = (Types.ThisType)singleType.pre();
        if (!(tuple2._2() instanceof Types.SingleType)) return false;
        Types.SingleType singleType2 = (Types.SingleType)tuple2._2();
        if (!(singleType2.pre() instanceof Types.SuperType)) return false;
        Types.SuperType superType = (Types.SuperType)singleType2.pre();
        if (!(superType.thistpe() instanceof Types.ThisType)) return false;
        Types.ThisType thisType2 = (Types.ThisType)superType.thistpe();
        Symbols.Symbol symbol = thisType.sym();
        Symbols.Symbol symbol2 = thisType2.sym();
        if (symbol == null) {
            if (symbol2 != null) {
                return false;
            }
        } else if (!symbol.equals(symbol2)) return false;
        if (!singleType.sym().overrideChain().contains(singleType2.sym())) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isHKSubType(SymbolTable $this, Types.Type tp1, Types.Type tp2, int depth) {
        Symbols.Symbol symbol = tp1.typeSymbol();
        Definitions$DefinitionsClass$NothingClass$ definitions$DefinitionsClass$NothingClass$ = $this.definitions().NothingClass();
        if (symbol == null) {
            if (definitions$DefinitionsClass$NothingClass$ == null) return true;
        } else if (symbol.equals(definitions$DefinitionsClass$NothingClass$)) return true;
        Symbols.Symbol symbol2 = tp2.typeSymbol();
        Symbols.ClassSymbol classSymbol = $this.definitions().AnyClass();
        if (symbol2 == null) {
            if (classSymbol == null) return true;
        } else if (symbol2.equals(classSymbol)) return true;
        if (!TypeComparers$class.isSub$1($this, tp1.normalize(), tp2.normalize(), tp1, tp2)) return false;
        if (!$this.annotationsConform(tp1, tp2)) return false;
        return true;
    }

    private static boolean isSubType2(SymbolTable $this, Types.Type tp1, Types.Type tp2, int depth) {
        if ($this.isSingleType(tp1) && $this.isSingleType(tp2) || $this.isConstantType(tp1) && $this.isConstantType(tp2)) {
            return tp1.$eq$colon$eq(tp2) || TypeComparers$class.isThisAndSuperSubtype($this, tp1, tp2) || TypeComparers$class.retry$2($this, tp1.underlying(), tp2, tp1, tp2, depth);
        }
        if (tp1.isHigherKinded() || tp2.isHigherKinded()) {
            return $this.isHKSubType(tp1, tp2, depth);
        }
        return TypeComparers$class.firstTry$1($this, tp1, tp2, depth);
    }

    /*
     * Unable to fully structure code
     */
    public static boolean isWeakSubType(SymbolTable $this, Types.Type tp1, Types.Type tp2) {
        block7: {
            block8: {
                block6: {
                    var3_3 = tp1.dealiasWiden();
                    if (!(var3_3 instanceof Types.TypeRef)) break block6;
                    var4_4 = (Types.TypeRef)var3_3;
                    if (!$this.definitions().isNumericValueClass(var4_4.sym())) break block6;
                    var6_5 = tp2.deconst().dealias();
                    if (!(var6_5 instanceof Types.TypeRef)) ** GOTO lbl-1000
                    var7_6 = (Types.TypeRef)var6_5;
                    if ($this.definitions().isNumericValueClass(var7_6.sym())) {
                        var8_7 = $this.definitions().isNumericSubClass(var4_4.sym(), var7_6.sym());
                    } else if (var6_5 instanceof Types.TypeVar) {
                        var9_8 = (Types.TypeVar)var6_5;
                        var8_7 = var9_8.registerBound(tp1, true, true);
                    } else {
                        var8_7 = $this.isSubType(tp1, tp2, $this.isSubType$default$3());
                    }
                    var5_9 = var8_7;
                    break block7;
                }
                if (!(var3_3 instanceof Types.TypeVar)) break block8;
                var10_10 = (Types.TypeVar)var3_3;
                var11_11 = tp2.deconst().dealias();
                if (!(var11_11 instanceof Types.TypeRef)) ** GOTO lbl-1000
                var12_12 = (Types.TypeRef)var11_11;
                if ($this.definitions().isNumericValueClass(var12_12.sym())) {
                    var13_13 = var10_10.registerBound(tp2, false, true);
                } else lbl-1000:
                // 2 sources

                {
                    var13_13 = $this.isSubType(tp1, tp2, $this.isSubType$default$3());
                }
                var5_9 = var13_13;
                break block7;
            }
            var5_9 = $this.isSubType(tp1, tp2, $this.isSubType$default$3());
        }
        return var5_9;
    }

    public static boolean isNumericSubType(SymbolTable $this, Types.Type tp1, Types.Type tp2) {
        return $this.definitions().isNumericSubClass(TypeComparers$class.primitiveBaseClass($this, tp1.dealiasWiden()), TypeComparers$class.primitiveBaseClass($this, tp2.dealias()));
    }

    private static Symbols.Symbol primitiveBaseClass(SymbolTable $this, Types.Type tp) {
        return TypeComparers$class.loop$1($this, tp.baseClasses());
    }

    private static final Types.Type chaseDealiasedUnderlying$1(SymbolTable $this, Types.Type tp) {
        Types.SingletonType singletonType;
        Types.Type type;
        while ((type = tp.underlying().dealias()) instanceof Types.SingletonType && tp != (singletonType = (Types.SingletonType)type)) {
            tp = singletonType;
        }
        return tp;
    }

    public static final Types.Type subst$1(SymbolTable $this, Types.Type info2, List tparams1$1, List tparams2$1) {
        return info2.substSym(tparams2$1, tparams1$1);
    }

    private static final boolean ignoreVariance$1(SymbolTable $this, Symbols.Symbol sym) {
        return !sym.isHigherOrderTypeParameter() || !sym.logicallyEnclosingMember().isMethod();
    }

    private static final boolean retry$1(SymbolTable $this, Types.Type lhs, Types.Type rhs, Types.Type tp1$5, Types.Type tp2$5) {
        return (lhs != tp1$5 || rhs != tp2$5) && $this.isSameType(lhs, rhs);
    }

    private static final boolean mutateNonTypeConstructs$1(SymbolTable $this, Types.Type lhs, Types.Type rhs, Types.Type tp1$5) {
        Types.TypeRef typeRef;
        boolean bl;
        if (lhs instanceof Types.BoundedWildcardType) {
            Types.BoundedWildcardType boundedWildcardType = (Types.BoundedWildcardType)lhs;
            bl = boundedWildcardType.bounds().containsType(rhs);
        } else if (lhs instanceof Types.TypeVar) {
            Types.TypeVar typeVar = (Types.TypeVar)lhs;
            bl = typeVar.registerTypeEquality(rhs, lhs == tp1$5);
        } else if (lhs instanceof Types.TypeRef && (typeRef = (Types.TypeRef)lhs).pre() instanceof Types.TypeVar) {
            Types.TypeVar typeVar = (Types.TypeVar)typeRef.pre();
            bl = typeVar.registerTypeSelection(typeRef.sym(), rhs);
        } else {
            bl = false;
        }
        return bl;
    }

    private static final boolean sameSingletonType$1(SymbolTable $this, Types.Type tp1$5, Types.Type tp2$5) {
        boolean bl;
        if (tp1$5 instanceof Types.SingletonType) {
            boolean bl2;
            Types.SingletonType singletonType = (Types.SingletonType)tp1$5;
            if (tp2$5 instanceof Types.SingletonType) {
                Types.SingletonType singletonType2 = (Types.SingletonType)tp2$5;
                bl2 = TypeComparers$class.isSameSingletonType($this, singletonType, singletonType2);
            } else {
                bl2 = false;
            }
            bl = bl2;
        } else {
            bl = false;
        }
        return bl;
    }

    private static final boolean sameTypeAndSameCaseClass$1(SymbolTable $this, Types.Type tp1$5, Types.Type tp2$5) {
        boolean bl;
        if (tp1$5 instanceof Types.TypeRef) {
            boolean bl2;
            Types.TypeRef typeRef = (Types.TypeRef)tp1$5;
            if (tp2$5 instanceof Types.TypeRef) {
                Types.TypeRef typeRef2 = (Types.TypeRef)tp2$5;
                bl2 = TypeComparers$class.isSameTypeRef($this, typeRef, typeRef2);
            } else {
                bl2 = false;
            }
            bl = bl2;
        } else if (tp1$5 instanceof Types.MethodType) {
            boolean bl3;
            Types.MethodType methodType = (Types.MethodType)tp1$5;
            if (tp2$5 instanceof Types.MethodType) {
                Types.MethodType methodType2 = (Types.MethodType)tp2$5;
                bl3 = TypeComparers$class.isSameMethodType($this, methodType, methodType2);
            } else {
                bl3 = false;
            }
            bl = bl3;
        } else if (tp1$5 instanceof Types.RefinedType) {
            boolean bl4;
            Types.RefinedType refinedType = (Types.RefinedType)tp1$5;
            if (tp2$5 instanceof Types.RefinedType) {
                Types.RefinedType refinedType2 = (Types.RefinedType)tp2$5;
                bl4 = $this.isSameTypes(refinedType.parents(), refinedType2.parents()) && refinedType.decls().isSameScope(refinedType2.decls());
            } else {
                bl4 = false;
            }
            bl = bl4;
        } else if (tp1$5 instanceof Types.SingleType) {
            boolean bl5;
            Types.SingleType singleType = (Types.SingleType)tp1$5;
            if (tp2$5 instanceof Types.SingleType) {
                Types.SingleType singleType2 = (Types.SingleType)tp2$5;
                bl5 = TypeComparers$class.equalSymsAndPrefixes($this, singleType.sym(), singleType.pre(), singleType2.sym(), singleType2.pre());
            } else {
                bl5 = false;
            }
            bl = bl5;
        } else if (tp1$5 instanceof Types.PolyType) {
            boolean bl6;
            Types.PolyType polyType = (Types.PolyType)tp1$5;
            if (tp2$5 instanceof Types.PolyType) {
                Types.PolyType polyType2 = (Types.PolyType)tp2$5;
                bl6 = TypeComparers$class.equalTypeParamsAndResult($this, polyType.typeParams(), polyType.resultType(), polyType2.typeParams(), polyType2.resultType());
            } else {
                bl6 = false;
            }
            bl = bl6;
        } else if (tp1$5 instanceof Types.ExistentialType) {
            boolean bl7;
            Types.ExistentialType existentialType = (Types.ExistentialType)tp1$5;
            if (tp2$5 instanceof Types.ExistentialType) {
                Types.ExistentialType existentialType2 = (Types.ExistentialType)tp2$5;
                bl7 = TypeComparers$class.equalTypeParamsAndResult($this, existentialType.quantified(), existentialType.underlying(), existentialType2.quantified(), existentialType2.underlying());
            } else {
                bl7 = false;
            }
            bl = bl7;
        } else if (tp1$5 instanceof Types.ThisType) {
            boolean bl8;
            Types.ThisType thisType = (Types.ThisType)tp1$5;
            if (tp2$5 instanceof Types.ThisType) {
                Types.ThisType thisType2 = (Types.ThisType)tp2$5;
                Symbols.Symbol symbol = thisType.sym();
                Symbols.Symbol symbol2 = thisType2.sym();
                bl8 = !(symbol != null ? !symbol.equals(symbol2) : symbol2 != null);
            } else {
                bl8 = false;
            }
            bl = bl8;
        } else if (tp1$5 instanceof Types.ConstantType) {
            boolean bl9;
            Types.ConstantType constantType = (Types.ConstantType)tp1$5;
            if (tp2$5 instanceof Types.ConstantType) {
                Types.ConstantType constantType2 = (Types.ConstantType)tp2$5;
                Constants.Constant constant = constantType.value();
                Constants.Constant constant2 = constantType2.value();
                bl9 = !(constant != null ? !((Object)constant).equals(constant2) : constant2 != null);
            } else {
                bl9 = false;
            }
            bl = bl9;
        } else if (tp1$5 instanceof Types.NullaryMethodType) {
            boolean bl10;
            Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)tp1$5;
            if (tp2$5 instanceof Types.NullaryMethodType) {
                Types.NullaryMethodType nullaryMethodType2 = (Types.NullaryMethodType)tp2$5;
                bl10 = nullaryMethodType.resultType().$eq$colon$eq(nullaryMethodType2.resultType());
            } else {
                bl10 = false;
            }
            bl = bl10;
        } else if (tp1$5 instanceof Types.TypeBounds) {
            boolean bl11;
            Types.TypeBounds typeBounds = (Types.TypeBounds)tp1$5;
            if (tp2$5 instanceof Types.TypeBounds) {
                Types.TypeBounds typeBounds2 = (Types.TypeBounds)tp2$5;
                bl11 = typeBounds.lo().$eq$colon$eq(typeBounds2.lo()) && typeBounds.hi().$eq$colon$eq(typeBounds2.hi());
            } else {
                bl11 = false;
            }
            bl = bl11;
        } else {
            bl = false;
        }
        return bl;
    }

    private static final boolean isTrue$1(SymbolTable $this, Types.Type tp1$1, Types.Type tp2$1) {
        return tp1$1 == tp2$1 || $this.isErrorOrWildcard(tp1$1) || $this.isErrorOrWildcard(tp2$1) || tp1$1 == $this.NoPrefix() && tp2$1.typeSymbol().isPackageClass() || tp2$1 == $this.NoPrefix() && tp1$1.typeSymbol().isPackageClass();
    }

    private static final boolean isFalse$1(SymbolTable $this, Types.Type tp1$1, Types.Type tp2$1) {
        return tp1$1 == $this.NoType() || tp2$1 == $this.NoType() || tp1$1 == $this.NoPrefix() || tp2$1 == $this.NoPrefix();
    }

    private static final Types.Type sub1$1(SymbolTable $this, Types.Type tp, List tparams1$2, boolean isMethod$1, List substitutes$1) {
        return isMethod$1 ? tp : tp.substSym(tparams1$2, substitutes$1);
    }

    private static final Types.Type sub2$1(SymbolTable $this, Types.Type tp, List tparams2$2, List substitutes$1) {
        return tp.substSym(tparams2$2, substitutes$1);
    }

    public static final boolean cmp$1(SymbolTable $this, Symbols.Symbol p1, Symbols.Symbol p2, List tparams1$2, List tparams2$2, boolean isMethod$1, List substitutes$1) {
        return TypeComparers$class.methodHigherOrderTypeParamsSubVariance($this, p2, p1) && TypeComparers$class.sub2$1($this, p2.info(), tparams2$2, substitutes$1).$less$colon$less(TypeComparers$class.sub1$1($this, p1.info(), tparams1$2, isMethod$1, substitutes$1));
    }

    public static final String tp_s$1(SymbolTable $this, Types.Type tp) {
        String arg$macro$2 = package$.MODULE$.shortClassOfInstance(tp);
        return new StringOps("%-20s %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{tp, arg$macro$2}));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean isSub$1(SymbolTable $this, Types.Type ntp1, Types.Type ntp2, Types.Type tp1$6, Types.Type tp2$6) {
        Types.MethodType methodType;
        Tuple2<Types.Type, Types.Type> tuple2 = new Tuple2<Types.Type, Types.Type>(ntp1.withoutAnnotations(), ntp2.withoutAnnotations());
        if (tuple2._1() instanceof Types.TypeRef) {
            Types.TypeRef typeRef = (Types.TypeRef)tuple2._1();
            Symbols.ClassSymbol classSymbol = $this.definitions().AnyClass();
            Symbols.Symbol symbol = typeRef.sym();
            if (classSymbol == null) {
                if (symbol == null) return false;
            } else if (classSymbol.equals(symbol)) {
                return false;
            }
        }
        if (tuple2._2() instanceof Types.TypeRef) {
            Types.TypeRef typeRef = (Types.TypeRef)tuple2._2();
            if ($this.definitions().NothingClass().equals(typeRef.sym())) {
                return false;
            }
        }
        if (tuple2._1() instanceof Types.PolyType) {
            Types.PolyType polyType = (Types.PolyType)tuple2._1();
            if (tuple2._2() instanceof Types.PolyType) {
                Types.PolyType polyType2 = (Types.PolyType)tuple2._2();
                return TypeComparers$class.isPolySubType($this, polyType, polyType2);
            }
        }
        if (tuple2._1() instanceof Types.PolyType && tuple2._2() instanceof Types.MethodType && (methodType = (Types.MethodType)tuple2._2()).params().exists((Function1<Symbols.Symbol, Object>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Symbols.Symbol x$3) {
                return x$3.tpe().isWildcard();
            }
        }))) {
            return false;
        }
        $this.devWarning((Function0<String>)((Object)new Serializable($this, ntp1, ntp2, tp1$6, tp2$6){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final Types.Type ntp1$1;
            private final Types.Type ntp2$1;
            private final Types.Type tp1$6;
            private final Types.Type tp2$6;

            public final String apply() {
                return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"HK subtype check on ", " and ", ", but both don't normalize to polytypes:\\n  tp1=", "\\n  tp2=", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.tp1$6, this.tp2$6, TypeComparers$class.tp_s$1(this.$outer, this.ntp1$1), TypeComparers$class.tp_s$1(this.$outer, this.ntp2$1)}));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.ntp1$1 = ntp1$1;
                this.ntp2$1 = ntp2$1;
                this.tp1$6 = tp1$6;
                this.tp2$6 = tp2$6;
            }
        }));
        return false;
    }

    private static final boolean retry$2(SymbolTable $this, Types.Type lhs, Types.Type rhs, Types.Type tp1$2, Types.Type tp2$2, int depth$1) {
        return (lhs != tp1$2 || rhs != tp2$2) && $this.isSubType(lhs, rhs, depth$1);
    }

    /*
     * Unable to fully structure code
     */
    private static final boolean firstTry$1(SymbolTable $this, Types.Type tp1$2, Types.Type tp2$2, int depth$1) {
        block13: {
            block8: {
                block12: {
                    block9: {
                        block11: {
                            block10: {
                                if (!(tp2$2 instanceof Types.TypeRef)) break block8;
                                var11_4 = (Types.TypeRef)tp2$2;
                                if (!(tp1$2 instanceof Types.TypeRef)) break block9;
                                var10_5 = (Types.TypeRef)tp1$2;
                                sym1 = var10_5.sym();
                                sym2 = var11_4.sym();
                                pre1 = var10_5.pre();
                                pre2 = var11_4.pre();
                                v0 = sym1;
                                if (v0 != null ? v0.equals(sym2) == false : sym2 != null) break block10;
                                v1 = $this.phase().erasedTypes() || sym1.owner().hasPackageFlag() || $this.isSubType(pre1, pre2, depth$1);
                                break block11;
                            }
                            v2 = sym1.name();
                            var4_10 = sym2.name();
                            if (v2 != null ? v2.equals(var4_10) == false : var4_10 != null) ** GOTO lbl-1000
                            if (!sym1.isModuleClass() && !sym2.isModuleClass() && (TypeComparers$class.isUnifiable($this, pre1, pre2) || TypeComparers$class.isSameSpecializedSkolem($this, sym1, sym2, pre1, pre2) || sym2.isAbstractType() && TypeComparers$class.isSubPre($this, pre1, pre2, sym2))) {
                                v1 = true;
                            } else lbl-1000:
                            // 2 sources

                            {
                                v1 = false;
                            }
                        }
                        var12_12 = v1 != false && $this.isSubArgs(var10_5.args(), var11_4.args(), sym1.typeParams(), depth$1) != false || sym2.isClass() != false && ((base = var10_5.baseType(sym2)) != var10_5 && $this.isSubType(base, var11_4, depth$1) != false) != false || TypeComparers$class.thirdTryRef$1($this, var10_5, var11_4, tp1$2, tp2$2, depth$1) != false;
                        break block12;
                    }
                    var12_12 = TypeComparers$class.secondTry$1($this, tp1$2, tp2$2, depth$1);
                }
                var17_13 = var12_12;
                break block13;
            }
            if (tp2$2 instanceof Types.AnnotatedType) {
                var17_13 = $this.isSubType(tp1$2.withoutAnnotations(), tp2$2.withoutAnnotations(), depth$1) != false && $this.annotationsConform(tp1$2, tp2$2) != false;
            } else if (tp2$2 instanceof Types.BoundedWildcardType) {
                var13_14 = (Types.BoundedWildcardType)tp2$2;
                var17_13 = $this.isSubType(tp1$2, var13_14.bounds().hi(), depth$1);
            } else if (tp2$2 instanceof Types.TypeVar) {
                var15_15 = (Types.TypeVar)tp2$2;
                var14_16 = tp1$2 instanceof Types.AnnotatedType != false ? true : tp1$2 instanceof Types.BoundedWildcardType != false;
                var16_17 = var14_16 != false ? TypeComparers$class.secondTry$1($this, tp1$2, tp2$2, depth$1) : var15_15.registerBound(tp1$2, true, var15_15.registerBound$default$3());
                var17_13 = var16_17;
            } else {
                var17_13 = TypeComparers$class.secondTry$1($this, tp1$2, tp2$2, depth$1);
            }
        }
        return var17_13;
    }

    private static final boolean secondTry$1(SymbolTable $this, Types.Type tp1$2, Types.Type tp2$2, int depth$1) {
        boolean bl;
        if (tp1$2 instanceof Types.AnnotatedType) {
            bl = $this.isSubType(tp1$2.withoutAnnotations(), tp2$2.withoutAnnotations(), depth$1) && $this.annotationsConform(tp1$2, tp2$2);
        } else if (tp1$2 instanceof Types.BoundedWildcardType) {
            bl = $this.isSubType(tp1$2.bounds().lo(), tp2$2, depth$1);
        } else if (tp1$2 instanceof Types.TypeVar) {
            Types.TypeVar typeVar = (Types.TypeVar)tp1$2;
            bl = typeVar.registerBound(tp2$2, false, typeVar.registerBound$default$3());
        } else if (tp1$2 instanceof Types.ExistentialType) {
            $this.skolemizationLevel_$eq($this.skolemizationLevel() + 1);
            bl = $this.isSubType(tp1$2.skolemizeExistential(), tp2$2, depth$1);
        } else {
            bl = TypeComparers$class.thirdTry$1($this, tp1$2, tp2$2, depth$1);
        }
        return bl;
        finally {
            $this.skolemizationLevel_$eq($this.skolemizationLevel() - 1);
        }
    }

    private static final boolean retry$4(SymbolTable $this, Types.Type lhs, Types.Type rhs, int depth$1) {
        return $this.isSubType(lhs, rhs, depth$1);
    }

    private static final boolean abstractTypeOnRight$1(SymbolTable $this, Types.Type lo, int depth$1, Types.Type tp1$3, Types.TypeRef tp2$3) {
        return $this.isDifferentTypeConstructor(tp2$3, lo) && TypeComparers$class.retry$4($this, tp1$3, lo, depth$1);
    }

    private static final boolean classOnRight$1(SymbolTable $this, Types.Type tp1$2, Types.Type tp2$2, int depth$1, Types.Type tp1$3, Types.TypeRef tp2$3, Symbols.Symbol sym2$1) {
        return $this.isRawType(tp2$3) ? TypeComparers$class.retry$4($this, tp1$3, $this.rawToExistential().apply(tp2$3), depth$1) : (sym2$1.isRefinementClass() ? TypeComparers$class.retry$4($this, tp1$3, sym2$1.info(), depth$1) : TypeComparers$class.fourthTry$1($this, tp1$2, tp2$2, depth$1));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean thirdTryRef$1(SymbolTable $this, Types.Type tp1, Types.TypeRef tp2, Types.Type tp1$2, Types.Type tp2$2, int depth$1) {
        Symbols.Symbol sym2 = tp2.sym();
        boolean bl = false;
        Symbols.ClassSymbol classSymbol = $this.definitions().SingletonClass();
        if (!(classSymbol != null ? !classSymbol.equals(sym2) : sym2 != null)) {
            if (tp1.isStable()) return true;
            if (!TypeComparers$class.fourthTry$1($this, tp1$2, tp2$2, depth$1)) return false;
            return true;
        }
        if (sym2 instanceof Symbols.ClassSymbol) {
            return TypeComparers$class.classOnRight$1($this, tp1$2, tp2$2, depth$1, tp1, tp2, sym2);
        }
        if (sym2 instanceof Symbols.TypeSymbol) {
            bl = true;
            Symbols.TypeSymbol cfr_ignored_0 = (Symbols.TypeSymbol)sym2;
            if (sym2.isDeferred()) {
                if (TypeComparers$class.abstractTypeOnRight$1($this, tp2.bounds().lo(), depth$1, tp1, tp2)) return true;
                if (!TypeComparers$class.fourthTry$1($this, tp1$2, tp2$2, depth$1)) return false;
                return true;
            }
        }
        if (!bl) return TypeComparers$class.fourthTry$1($this, tp1$2, tp2$2, depth$1);
        return TypeComparers$class.retry$4($this, tp1.normalize(), tp2.normalize(), depth$1);
    }

    private static final boolean thirdTry$1(SymbolTable $this, Types.Type tp1$2, Types.Type tp2$2, int depth$1) {
        boolean bl;
        if (tp2$2 instanceof Types.TypeRef) {
            Types.TypeRef typeRef = (Types.TypeRef)tp2$2;
            bl = TypeComparers$class.thirdTryRef$1($this, tp1$2, typeRef, tp1$2, tp2$2, depth$1);
        } else if (tp2$2 instanceof Types.RefinedType) {
            Types.RefinedType refinedType = (Types.RefinedType)tp2$2;
            bl = refinedType.parents().forall((Function1<Types.Type, Object>)((Object)new Serializable($this, tp1$2, depth$1){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;
                private final Types.Type tp1$2;
                private final int depth$1;

                public final boolean apply(Types.Type x$4) {
                    return this.$outer.isSubType(this.tp1$2, x$4, this.depth$1);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.tp1$2 = tp1$2;
                    this.depth$1 = depth$1;
                }
            })) && refinedType.decls().forall((Function1<Symbols.Symbol, Object>)((Object)new Serializable($this, tp1$2, depth$1){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;
                private final Types.Type tp1$2;
                private final int depth$1;

                public final boolean apply(Symbols.Symbol x$5) {
                    return this.$outer.specializesSym(this.tp1$2, x$5, this.depth$1);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.tp1$2 = tp1$2;
                    this.depth$1 = depth$1;
                }
            }));
        } else if (tp2$2 instanceof Types.ExistentialType) {
            Types.ExistentialType existentialType = (Types.ExistentialType)tp2$2;
            bl = existentialType.withTypeVars((Function1<Types.Type, Object>)((Object)new Serializable($this, tp1$2, depth$1){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;
                private final Types.Type tp1$2;
                private final int depth$1;

                public final boolean apply(Types.Type x$6) {
                    return this.$outer.isSubType(this.tp1$2, x$6, this.depth$1);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.tp1$2 = tp1$2;
                    this.depth$1 = depth$1;
                }
            }), depth$1) || TypeComparers$class.fourthTry$1($this, tp1$2, tp2$2, depth$1);
        } else if (tp2$2 instanceof Types.MethodType) {
            boolean bl2;
            Types.MethodType methodType = (Types.MethodType)tp2$2;
            if (tp1$2 instanceof Types.MethodType) {
                Types.MethodType methodType2 = (Types.MethodType)tp1$2;
                List<Symbols.Symbol> params2 = methodType.params();
                Types.Type res2 = methodType.resultType();
                bl2 = $this.sameLength(methodType2.params(), params2) && methodType2.isImplicit() == methodType.isImplicit() && $this.matchingParams(methodType2.params(), params2, methodType2.isJava(), methodType.isJava()) && $this.isSubType(methodType2.resultType().substSym(methodType2.params(), params2), res2, depth$1);
            } else {
                bl2 = false;
            }
            bl = bl2;
        } else if (tp2$2 instanceof Types.NullaryMethodType) {
            boolean bl3;
            Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)tp2$2;
            if (tp1$2 instanceof Types.NullaryMethodType) {
                Types.NullaryMethodType nullaryMethodType2 = (Types.NullaryMethodType)tp1$2;
                bl3 = $this.isSubType(nullaryMethodType2.resultType(), nullaryMethodType.resultType(), depth$1);
            } else {
                bl3 = false;
            }
            bl = bl3;
        } else if (tp2$2 instanceof Types.TypeBounds) {
            boolean bl4;
            Types.TypeBounds typeBounds = (Types.TypeBounds)tp2$2;
            if (tp1$2 instanceof Types.TypeBounds) {
                Types.TypeBounds typeBounds2 = (Types.TypeBounds)tp1$2;
                bl4 = $this.isSubType(typeBounds.lo(), typeBounds2.lo(), depth$1) && $this.isSubType(typeBounds2.hi(), typeBounds.hi(), depth$1);
            } else {
                bl4 = false;
            }
            bl = bl4;
        } else {
            bl = TypeComparers$class.fourthTry$1($this, tp1$2, tp2$2, depth$1);
        }
        return bl;
    }

    public static final boolean retry$3(SymbolTable $this, Types.Type lhs, Types.Type rhs, int depth$1) {
        return $this.isSubType(lhs, rhs, depth$1);
    }

    private static final boolean abstractTypeOnLeft$1(SymbolTable $this, Types.Type hi, Types.Type tp1$2, Types.Type tp2$2, int depth$1) {
        return $this.isDifferentTypeConstructor(tp1$2, hi) && TypeComparers$class.retry$3($this, hi, tp2$2, depth$1);
    }

    private static final boolean nullOnLeft$1(SymbolTable $this, Types.Type tp1$2, Types.Type tp2$2, int depth$1, Types.TypeRef x2$1) {
        boolean bl;
        if (tp2$2 instanceof Types.TypeRef) {
            Types.TypeRef typeRef = (Types.TypeRef)tp2$2;
            bl = x2$1.sym().isBottomSubClass(typeRef.sym());
        } else {
            bl = $this.isSingleType(tp2$2) && TypeComparers$class.retry$3($this, tp1$2, tp2$2.widen(), depth$1);
        }
        return bl;
    }

    private static final boolean moduleOnLeft$1(SymbolTable $this, Types.Type tp2$2, Types.TypeRef x2$1) {
        boolean bl;
        if (tp2$2 instanceof Types.SingleType) {
            Types.SingleType singleType = (Types.SingleType)tp2$2;
            bl = TypeComparers$class.equalSymsAndPrefixes($this, x2$1.sym().sourceModule(), x2$1.pre(), singleType.sym(), singleType.pre());
        } else {
            bl = false;
        }
        return bl;
    }

    private static final boolean classOnLeft$1(SymbolTable $this, Types.Type tp1$2, Types.Type tp2$2, int depth$1, Types.TypeRef x2$1) {
        return $this.isRawType(tp1$2) ? TypeComparers$class.retry$3($this, $this.rawToExistential().apply(tp1$2), tp2$2, depth$1) : (x2$1.sym().isModuleClass() ? TypeComparers$class.moduleOnLeft$1($this, tp2$2, x2$1) : x2$1.sym().isRefinementClass() && TypeComparers$class.retry$3($this, x2$1.sym().info(), tp2$2, depth$1));
    }

    /*
     * Unable to fully structure code
     */
    private static final boolean fourthTry$1(SymbolTable $this, Types.Type tp1$2, Types.Type tp2$2, int depth$1) {
        block9: {
            block4: {
                block6: {
                    block8: {
                        block7: {
                            block5: {
                                if (!(tp1$2 instanceof Types.TypeRef)) break block4;
                                var5_4 = (Types.TypeRef)tp1$2;
                                var6_5 = false;
                                var4_6 = var5_4.sym();
                                if (!$this.definitions().NothingClass().equals(var4_6)) break block5;
                                var7_7 = true;
                                break block6;
                            }
                            if (!$this.definitions().NullClass().equals(var4_6)) break block7;
                            var7_7 = TypeComparers$class.nullOnLeft$1($this, tp1$2, tp2$2, depth$1, var5_4);
                            break block6;
                        }
                        if (!(var4_6 instanceof Symbols.ClassSymbol)) break block8;
                        var7_7 = TypeComparers$class.classOnLeft$1($this, tp1$2, tp2$2, depth$1, var5_4);
                        break block6;
                    }
                    if (!(var4_6 instanceof Symbols.TypeSymbol)) ** GOTO lbl-1000
                    var6_5 = true;
                    (Symbols.TypeSymbol)var4_6;
                    if (var5_4.sym().isDeferred()) {
                        var7_7 = TypeComparers$class.abstractTypeOnLeft$1($this, tp1$2.bounds().hi(), tp1$2, tp2$2, depth$1);
                    } else lbl-1000:
                    // 2 sources

                    {
                        var7_7 = var6_5 != false ? TypeComparers$class.retry$3($this, tp1$2.normalize(), tp2$2.normalize(), depth$1) : false;
                    }
                }
                var9_8 = var7_7;
                break block9;
            }
            if (tp1$2 instanceof Types.RefinedType) {
                var8_9 = (Types.RefinedType)tp1$2;
                var9_8 = var8_9.parents().exists((Function1<Types.Type, Object>)new Serializable($this, tp2$2, depth$1){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SymbolTable $outer;
                    private final Types.Type tp2$2;
                    private final int depth$1;

                    public final boolean apply(Types.Type x$7) {
                        return TypeComparers$class.retry$3(this.$outer, x$7, this.tp2$2, this.depth$1);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.tp2$2 = tp2$2;
                        this.depth$1 = depth$1;
                    }
                });
            } else {
                var9_8 = tp1$2 instanceof Types.SingletonType != false ? TypeComparers$class.retry$3($this, tp1$2.underlying(), tp2$2, depth$1) : false;
            }
        }
        return var9_8;
    }

    private static final Symbols.Symbol loop$1(SymbolTable $this, List bases) {
        while (true) {
            $colon$colon $colon$colon;
            block5: {
                Symbols.Symbol symbol;
                block4: {
                    block3: {
                        if (!((Object)Nil$.MODULE$).equals(bases)) break block3;
                        symbol = $this.NoSymbol();
                        break block4;
                    }
                    if (!(bases instanceof $colon$colon)) break;
                    $colon$colon = ($colon$colon)bases;
                    if (!$this.definitions().isPrimitiveValueClass((Symbols.Symbol)$colon$colon.head())) break block5;
                    symbol = (Symbols.Symbol)$colon$colon.head();
                }
                return symbol;
            }
            bases = $colon$colon.tl$1();
        }
        throw new MatchError(bases);
    }

    public static void $init$(SymbolTable $this) {
        $this.scala$reflect$internal$tpe$TypeComparers$_setter_$scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes_$eq(new HashSet());
        $this.scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions_$eq(0);
    }
}

