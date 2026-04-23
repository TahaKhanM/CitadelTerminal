/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Predef$any2stringadd$;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple5;
import scala.collection.AbstractIterable;
import scala.collection.LinearSeqOptimized;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.TraversableOnce;
import scala.collection.generic.Clearable;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.Set$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WeakHashMap;
import scala.collection.mutable.WeakHashMap$;
import scala.collection.mutable.WrappedArray;
import scala.reflect.ClassTag$;
import scala.reflect.api.Symbols;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.BaseTypeSeqs;
import scala.reflect.internal.Depth;
import scala.reflect.internal.Depth$;
import scala.reflect.internal.Mirrors;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$;
import scala.reflect.internal.Types$NoType$;
import scala.reflect.internal.TypesStats$;
import scala.reflect.internal.Variance;
import scala.reflect.internal.Variance$;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.tpe.TypeMaps;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;
import scala.reflect.internal.util.WeakHashSet$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;
import scala.sys.package$;

public abstract class Types$class {
    public static int skolemizationLevel(SymbolTable $this) {
        return $this.scala$reflect$internal$Types$$_skolemizationLevel();
    }

    public static void skolemizationLevel_$eq(SymbolTable $this, int value) {
        $this.scala$reflect$internal$Types$$_skolemizationLevel_$eq(value);
    }

    public static WeakHashMap intersectionWitness(SymbolTable $this) {
        return $this.scala$reflect$internal$Types$$_intersectionWitness();
    }

    public static void defineUnderlyingOfSingleType(SymbolTable $this, Types.SingleType tpe) {
        int period = tpe.underlyingPeriod();
        if (period != $this.currentPeriod()) {
            tpe.underlyingPeriod_$eq($this.currentPeriod());
            if (!$this.isValid(period)) {
                Symbols.Symbol symbol = tpe.sym();
                Symbols.NoSymbol noSymbol = $this.NoSymbol();
                tpe.underlyingCache_$eq(!(symbol != null ? !symbol.equals(noSymbol) : noSymbol != null) ? $this.ThisType().apply(((Mirrors.RootsBase)$this.rootMirror()).RootClass()) : tpe.pre().memberType(tpe.sym()).resultType());
                Serializable serializable = new Serializable($this, tpe){
                    public static final long serialVersionUID = 0L;
                    public final Types.SingleType tpe$3;

                    public final Types.SingleType apply() {
                        return this.tpe$3;
                    }
                    {
                        this.tpe$3 = tpe$3;
                    }
                };
                boolean bl = tpe.underlyingCache() != tpe;
                Predef$ predef$ = Predef$.MODULE$;
                if (!bl) {
                    throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(serializable.tpe$3).toString());
                }
            }
        }
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     */
    public static List computeBaseClasses(SymbolTable $this, Types.Type tpe) {
        List list2;
        List<Types.Type> parents2 = tpe.parents();
        if (parents2.isEmpty() || parents2.head() instanceof Types.PackageTypeRef) {
            list2 = Nil$.MODULE$;
        } else {
            Types.Type superclazz = parents2.head();
            List mixins = (List)parents2.tail();
            List<Symbols.Symbol> sbcs = superclazz.baseClasses();
            ObjectRef<List<Symbols.Symbol>> bcs = ObjectRef.create(sbcs);
            while (true) {
                void var7_6;
                void var6_5;
                void var4_3;
                List list3;
                if (mixins.isEmpty()) {
                    list2 = (List)bcs.elem;
                    break;
                }
                var7_6.elem = Types$class.addMixinBaseClasses$1($this, ((Types.Type)list3.head()).baseClasses(), (Types.Type)var4_3, (List)var6_5, (ObjectRef)var7_6);
                list3 = (List)list3.tail();
            }
        }
        Nil$ baseTail = list2;
        Symbols.Symbol symbol = tpe.typeSymbol();
        return baseTail.$colon$colon(symbol);
    }

    public static void defineBaseTypeSeqOfCompoundType(SymbolTable $this, Types.CompoundType tpe) {
        Tuple2<Object, Object> start;
        int period = tpe.baseTypeSeqPeriod();
        if (period != $this.currentPeriod()) {
            tpe.baseTypeSeqPeriod_$eq($this.currentPeriod());
            if (!$this.isValidForBaseClasses(period)) {
                if (tpe.parents().exists($this.typeContainsTypeVar())) {
                    ObjectRef<Set> tvs = ObjectRef.create((Set)Predef$.MODULE$.Set().apply(Nil$.MODULE$));
                    Serializable serializable = new Serializable($this, tvs){
                        public static final long serialVersionUID = 0L;
                        public final ObjectRef tvs$1;

                        public final void apply(Types.Type p) {
                            p.foreach((Function1<Types.Type, BoxedUnit>)((Object)new Serializable(this){
                                public static final long serialVersionUID = 0L;
                                private final /* synthetic */ Types$.anonfun.defineBaseTypeSeqOfCompoundType.1 $outer;

                                public final void apply(Types.Type t) {
                                    block0: {
                                        if (!(t instanceof Types.TypeVar)) break block0;
                                        Types.TypeVar typeVar = (Types.TypeVar)t;
                                        this.$outer.tvs$1.elem = (Set)((Set)this.$outer.tvs$1.elem).$plus(typeVar);
                                    }
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                }
                            }));
                        }
                        {
                            this.tvs$1 = tvs$1;
                        }
                    };
                    List list2 = tpe.parents();
                    while (!((AbstractIterable)list2).isEmpty()) {
                        ((Types.Type)((AbstractIterable)list2).head()).foreach((Function1<Types.Type, BoxedUnit>)((Object)new /* invalid duplicate definition of identical inner class */));
                        list2 = (List)list2.tail();
                    }
                    Map varToParamMap = $this.mapFrom(((Set)tvs.elem).toList(), new Serializable($this){
                        public static final long serialVersionUID = 0L;

                        public final Symbols.Symbol apply(Types.TypeVar x$20) {
                            return x$20.origin().typeSymbol().cloneSymbol();
                        }
                    });
                    Map paramToVarMap = varToParamMap.map(new Serializable($this){
                        public static final long serialVersionUID = 0L;

                        public final Tuple2<Symbols.Symbol, Types.Type> apply(Tuple2<Types.Type, Symbols.Symbol> x$21) {
                            return x$21.swap();
                        }
                    }, Map$.MODULE$.canBuildFrom());
                    TypeMaps.TypeMap varToParam = new TypeMaps.TypeMap($this, varToParamMap){
                        private final Map varToParamMap$1;

                        public Types.Type apply(Types.Type tp) {
                            Types.Type type;
                            Option<B> option = this.varToParamMap$1.get(tp);
                            if (option instanceof Some) {
                                Some some = (Some)option;
                                type = ((Symbols.Symbol)some.x()).tpe_$times();
                            } else {
                                type = this.mapOver(tp);
                            }
                            return type;
                        }
                        {
                            this.varToParamMap$1 = varToParamMap$1;
                            super($outer);
                        }
                    };
                    TypeMaps.TypeMap paramToVar = new TypeMaps.TypeMap($this, paramToVarMap){
                        private final Map paramToVarMap$1;

                        public Types.Type apply(Types.Type tp) {
                            Types.TypeRef typeRef;
                            Types.Type type = tp instanceof Types.TypeRef && this.paramToVarMap$1.isDefinedAt((typeRef = (Types.TypeRef)tp).sym()) ? (Types.Type)this.paramToVarMap$1.apply(typeRef.sym()) : this.mapOver(tp);
                            return type;
                        }
                        {
                            this.paramToVarMap$1 = paramToVarMap$1;
                            super($outer);
                        }
                    };
                    BaseTypeSeqs.BaseTypeSeq bts = $this.copyRefinedType((Types.RefinedType)tpe, tpe.parents().map(varToParam, List$.MODULE$.canBuildFrom()), varToParam.mapOver(tpe.decls())).baseTypeSeq();
                    tpe.baseTypeSeqCache_$eq(bts.lateMap(paramToVar));
                } else {
                    Tuple2<Object, Object> tuple2;
                    if (Statistics$.MODULE$.canEnable()) {
                        Statistics.SubCounter subCounter = TypesStats$.MODULE$.compoundBaseTypeSeqCount();
                        if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && subCounter != null) {
                            subCounter.value_$eq(subCounter.value() + 1);
                        }
                    }
                    if (Statistics$.MODULE$.canEnable()) {
                        Statistics.TimerStack timerStack = TypesStats$.MODULE$.typeOpsStack();
                        tuple2 = Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && timerStack != null ? timerStack.push(TypesStats$.MODULE$.baseTypeSeqNanos()) : null;
                    } else {
                        tuple2 = null;
                    }
                    start = tuple2;
                    tpe.baseTypeSeqCache_$eq($this.undetBaseTypeSeq());
                    tpe.baseTypeSeqCache_$eq(tpe.typeSymbol().isRefinementClass() ? tpe.memo(new Serializable($this, tpe){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ SymbolTable $outer;
                        private final Types.CompoundType tpe$4;

                        public final BaseTypeSeqs.BaseTypeSeq apply() {
                            return this.$outer.compoundBaseTypeSeq(this.tpe$4);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.tpe$4 = tpe$4;
                        }
                    }, new Serializable($this, tpe){
                        public static final long serialVersionUID = 0L;
                        private final Types.CompoundType tpe$4;

                        public final BaseTypeSeqs.BaseTypeSeq apply(Types.Type x$22) {
                            return x$22.baseTypeSeq().updateHead(this.tpe$4.typeSymbol().tpe_$times());
                        }
                        {
                            this.tpe$4 = tpe$4;
                        }
                    }) : $this.compoundBaseTypeSeq(tpe));
                }
            }
        }
        if (tpe.baseTypeSeqCache() == $this.undetBaseTypeSeq()) {
            throw new Types.TypeError($this, new StringBuilder().append((Object)"illegal cyclic inheritance involving ").append(tpe.typeSymbol()).toString());
        }
        return;
        finally {
            if (Statistics$.MODULE$.canEnable()) {
                Statistics.TimerStack timerStack = TypesStats$.MODULE$.typeOpsStack();
                if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && timerStack != null) {
                    timerStack.pop(start);
                }
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void defineBaseClassesOfCompoundType(SymbolTable $this, Types.CompoundType tpe) {
        Types.ClassInfoType classInfoType;
        if ($this.scala$reflect$internal$Types$$breakCycles() && !$this.isPastTyper()) {
            if (tpe instanceof Types.ClassInfoType && (classInfoType = (Types.ClassInfoType)tpe).parents() instanceof $colon$colon && !classInfoType.typeSymbol().isAnonOrRefinementClass()) {
                if ($this.baseClassesCycleMonitor().isOpen(classInfoType.typeSymbol())) {
                    Types$class.defineBaseClassesOfCompoundType($this, classInfoType, true);
                    return;
                }
                $this.baseClassesCycleMonitor().push(classInfoType.typeSymbol());
                Types$class.define$1($this, tpe);
                return;
            }
            Types$class.define$1($this, tpe);
            return;
        }
        Types$class.define$1($this, tpe);
        return;
        finally {
            $this.baseClassesCycleMonitor().pop(classInfoType.typeSymbol());
        }
    }

    private static void defineBaseClassesOfCompoundType(SymbolTable $this, Types.CompoundType tpe, boolean force) {
        Tuple2<Object, Object> start;
        int period = tpe.baseClassesPeriod();
        if (period == $this.currentPeriod()) {
            if (force && $this.scala$reflect$internal$Types$$breakCycles()) {
                List<Symbols.Symbol> bcs = $this.computeBaseClasses(tpe);
                tpe.baseClassesCache_$eq(bcs);
                $this.warning(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Breaking cycle in base class computation of ", " (", ")"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{Types$class.what$1($this, tpe), bcs})));
            }
        } else {
            tpe.baseClassesPeriod_$eq($this.currentPeriod());
            if (!$this.isValidForBaseClasses(period)) {
                Tuple2<Object, Object> tuple2;
                if (Statistics$.MODULE$.canEnable()) {
                    Statistics.TimerStack timerStack = TypesStats$.MODULE$.typeOpsStack();
                    tuple2 = Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && timerStack != null ? timerStack.push(TypesStats$.MODULE$.baseClassesNanos()) : null;
                } else {
                    tuple2 = null;
                }
                start = tuple2;
                tpe.baseClassesCache_$eq(null);
                tpe.baseClassesCache_$eq(tpe.memo(new Serializable($this, tpe){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SymbolTable $outer;
                    private final Types.CompoundType tpe$2;

                    public final List<Symbols.Symbol> apply() {
                        return this.$outer.computeBaseClasses(this.tpe$2);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.tpe$2 = tpe$2;
                    }
                }, new Serializable($this, tpe){
                    public static final long serialVersionUID = 0L;
                    private final Types.CompoundType tpe$2;

                    public final List<Symbols.Symbol> apply(Types.Type x$23) {
                        Symbols.Symbol symbol = this.tpe$2.typeSymbol();
                        return ((List)x$23.baseClasses().tail()).$colon$colon(symbol);
                    }
                    {
                        this.tpe$2 = tpe$2;
                    }
                }));
            }
        }
        if (tpe.baseClassesCache() == null) {
            throw new Types.TypeError($this, new StringBuilder().append((Object)"illegal cyclic reference involving ").append(tpe.typeSymbol()).toString());
        }
        return;
        finally {
            if (Statistics$.MODULE$.canEnable()) {
                Statistics.TimerStack timerStack = TypesStats$.MODULE$.typeOpsStack();
                if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && timerStack != null) {
                    timerStack.pop(start);
                }
            }
        }
    }

    public static void validateClassInfo(SymbolTable $this, Types.ClassInfoType tp) {
    }

    /*
     * Loose catch block
     */
    public static Types.Type baseTypeOfNonClassTypeRef(SymbolTable $this, Types.NonClassTypeRef tpe, Symbols.Symbol clazz) {
        Throwable throwable222;
        try {
            Types.Type type;
            $this.basetypeRecursions_$eq($this.basetypeRecursions() + 1);
            if ($this.basetypeRecursions() < 50) {
                type = tpe.scala$reflect$internal$Types$$relativeInfo().baseType(clazz);
            } else if ($this.pendingBaseTypes().contains((Types.Type)((Object)tpe))) {
                Symbols.Symbol symbol = clazz;
                Symbols.ClassSymbol classSymbol = $this.definitions().AnyClass();
                type = !(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null) ? clazz.tpe() : $this.NoType();
            } else {
                $this.pendingBaseTypes().$plus$eq(tpe);
                type = tpe.scala$reflect$internal$Types$$relativeInfo().baseType(clazz);
                $this.pendingBaseTypes().$minus$eq(tpe);
            }
            return type;
            catch (Throwable throwable222) {
                $this.pendingBaseTypes().$minus$eq(tpe);
            }
        }
        finally {
            $this.basetypeRecursions_$eq($this.basetypeRecursions() - 1);
        }
        throw throwable222;
    }

    public static Symbols.Symbol scala$reflect$internal$Types$$embeddedSymbol(SymbolTable $this, Types.Type tp, Names.Name name) {
        Symbols.Symbol symbol;
        if (tp.typeSymbol().isRefinementClass()) {
            symbol = tp.normalize().decls().lookup(name);
        } else {
            $this.debuglog((Function0<String>)((Object)new Serializable($this, tp, name){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;
                private final Types.Type tp$3;
                private final Names.Name name$2;

                public final String apply() {
                    return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"no embedded symbol ", " found in ", " --> ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.name$2, this.$outer.showRaw(this.tp$3, this.$outer.showRaw$default$2(), this.$outer.showRaw$default$3(), this.$outer.showRaw$default$4(), this.$outer.showRaw$default$5(), this.$outer.showRaw$default$6(), this.$outer.showRaw$default$7()), this.tp$3.normalize().decls().lookup(this.name$2)}));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.tp$3 = tp$3;
                    this.name$2 = name$2;
                }
            }));
            symbol = $this.NoSymbol();
        }
        return symbol;
    }

    public static void defineParentsOfTypeRef(SymbolTable $this, Types.TypeRef tpe) {
        int period = tpe.parentsPeriod();
        if (period != $this.currentPeriod()) {
            tpe.parentsPeriod_$eq($this.currentPeriod());
            if ($this.isValidForBaseClasses(period)) {
                if (tpe.parentsCache() == null) {
                    tpe.parentsCache_$eq((List<Types.Type>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{$this.definitions().AnyTpe()})));
                }
            } else {
                tpe.parentsCache_$eq(tpe.thisInfo().parents().map(new Serializable($this, tpe){
                    public static final long serialVersionUID = 0L;
                    private final Types.TypeRef tpe$6;

                    public final Types.Type apply(Types.Type tp) {
                        return this.tpe$6.transform(tp);
                    }
                    {
                        this.tpe$6 = tpe$6;
                    }
                }, List$.MODULE$.canBuildFrom()));
            }
        }
    }

    public static void defineBaseTypeSeqOfTypeRef(SymbolTable $this, Types.TypeRef tpe) {
        Tuple2<Object, Object> start;
        int period = tpe.baseTypeSeqPeriod();
        if (period != $this.currentPeriod()) {
            tpe.baseTypeSeqPeriod_$eq($this.currentPeriod());
            if (!$this.isValidForBaseClasses(period)) {
                Tuple2<Object, Object> tuple2;
                if (Statistics$.MODULE$.canEnable()) {
                    Statistics.SubCounter subCounter = TypesStats$.MODULE$.typerefBaseTypeSeqCount();
                    if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && subCounter != null) {
                        subCounter.value_$eq(subCounter.value() + 1);
                    }
                }
                if (Statistics$.MODULE$.canEnable()) {
                    Statistics.TimerStack timerStack = TypesStats$.MODULE$.typeOpsStack();
                    tuple2 = Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && timerStack != null ? timerStack.push(TypesStats$.MODULE$.baseTypeSeqNanos()) : null;
                } else {
                    tuple2 = null;
                }
                start = tuple2;
                tpe.baseTypeSeqCache_$eq($this.undetBaseTypeSeq());
                tpe.baseTypeSeqCache_$eq(tpe.baseTypeSeqImpl());
            }
        }
        BaseTypeSeqs.BaseTypeSeq baseTypeSeq2 = tpe.baseTypeSeqCache();
        BaseTypeSeqs.BaseTypeSeq baseTypeSeq3 = $this.undetBaseTypeSeq();
        if (!(baseTypeSeq2 != null ? !baseTypeSeq2.equals(baseTypeSeq3) : baseTypeSeq3 != null)) {
            throw new Types.TypeError($this, new StringBuilder().append((Object)"illegal cyclic inheritance involving ").append(tpe.sym()).toString());
        }
        return;
        finally {
            if (Statistics$.MODULE$.canEnable()) {
                Statistics.TimerStack timerStack = TypesStats$.MODULE$.typeOpsStack();
                if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && timerStack != null) {
                    timerStack.pop(start);
                }
            }
        }
    }

    public static Types.Type newExistentialType(SymbolTable $this, List quantified, Types.Type underlying) {
        Types.Type type;
        if (quantified.isEmpty()) {
            type = underlying;
        } else {
            Types.Type type2;
            if (underlying instanceof Types.ExistentialType) {
                Types.ExistentialType existentialType = (Types.ExistentialType)underlying;
                type2 = $this.newExistentialType(existentialType.quantified().$colon$colon$colon(quantified), existentialType.underlying());
            } else {
                type2 = new Types.ExistentialType($this, quantified, underlying);
            }
            type = type2;
        }
        return type;
    }

    public static Types.Type overloadedType(SymbolTable $this, Types.Type pre, List alternatives) {
        $colon$colon $colon$colon;
        Types.Type type = ((Object)Nil$.MODULE$).equals(alternatives) ? $this.NoType() : (alternatives instanceof $colon$colon && ((Object)Nil$.MODULE$).equals(($colon$colon = ($colon$colon)alternatives).tl$1()) ? pre.memberType((Symbols.Symbol)$colon$colon.head()) : new Types.OverloadedType($this, pre, alternatives));
        return type;
    }

    public static Types.Type annotatedType(SymbolTable $this, List annots, Types.Type underlying) {
        return annots.isEmpty() ? underlying : new Types.AnnotatedType($this, annots, underlying);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static Symbols.Symbol rebind(SymbolTable $this, Types.Type pre, Symbols.Symbol sym) {
        Symbols.SymbolApi symbolApi;
        if (sym.isOverridableMember()) {
            Symbols.Symbol symbol = sym.owner();
            Symbols.Symbol symbol2 = pre.typeSymbol();
            if (symbol == null ? symbol2 != null : !symbol.equals(symbol2)) {
                Serializable serializable = new Serializable($this, sym){
                    public static final long serialVersionUID = 0L;
                    public final Symbols.Symbol sym$1;

                    public final Symbols.Symbol apply() {
                        return this.sym$1;
                    }
                    {
                        this.sym$1 = sym$1;
                    }
                };
                Symbols.SymbolApi symbolApi2 = pre.nonPrivateMember(sym.name()).suchThat((Function1)((Object)new Serializable($this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SymbolTable $outer;

                    public final boolean apply(Symbols.Symbol sym) {
                        boolean isModuleWithAccessor = this.$outer.phase().refChecked() && sym.isModuleNotMethod();
                        return sym.isType() || !isModuleWithAccessor && sym.isStable() && !sym.hasVolatileType();
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }));
                if (symbolApi2 != ((Symbols.Symbol)symbolApi2).scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                    symbolApi = symbolApi2;
                    return symbolApi;
                }
                symbolApi = serializable.sym$1;
                return symbolApi;
            }
        }
        symbolApi = sym;
        return symbolApi;
    }

    private static Types.Type removeSuper(SymbolTable $this, Types.Type tp, Symbols.Symbol sym) {
        Types.Type type;
        if (tp instanceof Types.SuperType) {
            Types.SuperType superType = (Types.SuperType)tp;
            type = sym.isEffectivelyFinal() || sym.isDeferred() ? superType.thistpe() : tp;
        } else {
            type = tp;
        }
        return type;
    }

    public static Types.Type singleType(SymbolTable $this, Types.Type pre, Symbols.Symbol sym) {
        Types.Type type;
        if ($this.phase().erasedTypes()) {
            type = sym.tpe().resultType();
        } else if (sym.isRootPackage()) {
            type = $this.ThisType().apply(sym.moduleClass());
        } else {
            Symbols.Symbol sym1 = Types$class.rebind($this, pre, sym);
            Types.Type pre1 = Types$class.removeSuper($this, pre, sym1);
            if (pre1 != pre) {
                sym1 = Types$class.rebind($this, pre1, sym1);
            }
            type = $this.SingleType().apply(pre1, sym1);
        }
        return type;
    }

    public static Types.Type refinedType(SymbolTable $this, List parents2, Symbols.Symbol owner2, Scopes.Scope decls, Position pos) {
        Types.Type type;
        if ($this.phase().erasedTypes()) {
            type = parents2.isEmpty() ? $this.definitions().ObjectTpe() : (Types.Type)parents2.head();
        } else {
            Symbols.RefinementClassSymbol clazz = owner2.newRefinementClass(pos);
            Types.RefinedType result2 = $this.RefinedType().apply(parents2, decls, clazz);
            clazz.setInfo(result2);
            type = result2;
        }
        return type;
    }

    public static Types.Type refinedType(SymbolTable $this, List parents2, Symbols.Symbol owner2) {
        return $this.refinedType(parents2, owner2, $this.newScope(), owner2.pos());
    }

    public static Types.Type copyRefinedType(SymbolTable $this, Types.RefinedType original, List parents2, Scopes.Scope decls) {
        Types.Type type;
        if (parents2 == original.parents() && decls == original.decls()) {
            type = original;
        } else {
            Symbols.Symbol owner2 = original.typeSymbol().owner();
            Types.Type result2 = $this.refinedType(parents2, owner2);
            List syms1 = decls.toList();
            Serializable serializable = new Serializable($this, result2){
                public static final long serialVersionUID = 0L;
                public final Types.Type result$1;

                public final Symbols.Symbol apply(Symbols.Symbol sym) {
                    return this.result$1.decls().enter(sym.cloneSymbol(this.result$1.typeSymbol()));
                }
                {
                    this.result$1 = result$1;
                }
            };
            List list2 = syms1;
            while (!((AbstractIterable)list2).isEmpty()) {
                Symbols.Symbol symbol = (Symbols.Symbol)((AbstractIterable)list2).head();
                serializable.result$1.decls().enter(symbol.cloneSymbol(serializable.result$1.typeSymbol()));
                list2 = (List)list2.tail();
            }
            List syms2 = result2.decls().toList();
            Types.Type resultThis = result2.typeSymbol().thisType();
            Serializable serializable2 = new Serializable($this, syms1, syms2, resultThis, original){
                public static final long serialVersionUID = 0L;
                public final List syms1$1;
                public final List syms2$1;
                public final Types.Type resultThis$1;
                public final Types.RefinedType original$1;

                public final Symbols.Symbol apply(Symbols.Symbol sym) {
                    return sym.modifyInfo((Function1<Types.Type, Types.Type>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ Types$.anonfun.copyRefinedType.2 $outer;

                        public final Types.Type apply(Types.Type x$52) {
                            return x$52.substThisAndSym(this.$outer.original$1.typeSymbol(), this.$outer.resultThis$1, this.$outer.syms1$1, this.$outer.syms2$1);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                }
                {
                    this.syms1$1 = syms1$1;
                    this.syms2$1 = syms2$1;
                    this.resultThis$1 = resultThis$1;
                    this.original$1 = original$1;
                }
            };
            List list3 = syms2;
            while (!((AbstractIterable)list3).isEmpty()) {
                ((Symbols.Symbol)((AbstractIterable)list3).head()).modifyInfo((Function1<Types.Type, Types.Type>)((Object)new /* invalid duplicate definition of identical inner class */));
                list3 = (List)list3.tail();
            }
            type = result2;
        }
        return type;
    }

    /*
     * Unable to fully structure code
     */
    public static Types.Type typeRef(SymbolTable $this, Types.Type pre, Symbols.Symbol sym, List args) {
        v0 = sym1 = sym.isAbstractType() != false ? Types$class.rebind($this, pre, sym) : sym;
        if (sym1.isAliasType() && $this.sameLength(sym1.info().typeParams(), args) && !sym1.lockOK()) {
            throw new Types.RecoverableCyclicReference($this, sym1);
        }
        if (!(pre instanceof Types.SuperType)) ** GOTO lbl-1000
        var4_5 = (Types.SuperType)pre;
        if (sym1.isEffectivelyFinal() || sym1.isDeferred()) {
            var5_6 = var4_5.thistpe();
        } else lbl-1000:
        // 2 sources

        {
            var5_6 = pre;
        }
        return pre == var5_6 ? $this.TypeRef().apply(pre, sym1, args) : (sym1.isAbstractType() != false && sym1.isClass() == false ? $this.typeRef(var5_6, Types$class.rebind($this, var5_6, sym1), args) : $this.typeRef(var5_6, sym1, args));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Types.Type copyTypeRef(SymbolTable $this, Types.Type tp, Types.Type pre, Symbols.Symbol sym, List args) {
        if (!(tp instanceof Types.TypeRef)) return $this.typeRef(pre, sym, args);
        Types.TypeRef typeRef = (Types.TypeRef)tp;
        Types.Type type = pre;
        Types.Type type2 = typeRef.pre();
        if (type == null) {
            if (type2 != null) {
                return $this.typeRef(pre, sym, args);
            }
        } else if (!type.equals(type2)) return $this.typeRef(pre, sym, args);
        Names.Name name = typeRef.sym().name();
        Names.Name name2 = sym.name();
        if (name == null) {
            if (name2 != null) {
                return $this.typeRef(pre, sym, args);
            }
        } else if (!name.equals(name2)) return $this.typeRef(pre, sym, args);
        if (!sym.isAliasType()) return $this.TypeRef().apply(pre, sym, args);
        if (!$this.sameLength(sym.info().typeParams(), args)) return $this.TypeRef().apply(pre, sym, args);
        if (sym.lockOK()) return $this.TypeRef().apply(pre, sym, args);
        throw new Types.RecoverableCyclicReference($this, sym);
    }

    public static Types.JavaMethodType JavaMethodType(SymbolTable $this, List params2, Types.Type resultType) {
        return new Types.JavaMethodType($this, params2, resultType);
    }

    public static Types.Type copyMethodType(SymbolTable $this, Types.Type tp, List params2, Types.Type restpe) {
        Types.MethodType methodType = tp instanceof Types.JavaMethodType ? $this.JavaMethodType(params2, restpe) : new Types.MethodType($this, params2, restpe);
        return methodType;
    }

    public static Types.Type intersectionType(SymbolTable $this, List tps, Symbols.Symbol owner2) {
        $colon$colon $colon$colon;
        Types.Type type = tps instanceof $colon$colon && ((Object)Nil$.MODULE$).equals(($colon$colon = ($colon$colon)tps).tl$1()) ? (Types.Type)$colon$colon.head() : $this.refinedType(tps, owner2);
        return type;
    }

    public static Types.Type intersectionType(SymbolTable $this, List tps) {
        $colon$colon $colon$colon;
        Types.Type type = tps instanceof $colon$colon && ((Object)Nil$.MODULE$).equals(($colon$colon = ($colon$colon)tps).tl$1()) ? (Types.Type)$colon$colon.head() : $this.refinedType(tps, $this.commonOwner(tps));
        return type;
    }

    public static Types.Type appliedType(SymbolTable $this, Types.Type tycon, List args) {
        block17: {
            Types.Type type;
            block7: {
                block16: {
                    block15: {
                        block14: {
                            block13: {
                                block12: {
                                    block11: {
                                        block10: {
                                            block9: {
                                                ObjectRef<Object> objectRef;
                                                boolean bl;
                                                block8: {
                                                    block6: {
                                                        boolean bl2;
                                                        if (args.isEmpty()) {
                                                            return tycon;
                                                        }
                                                        bl = false;
                                                        objectRef = ObjectRef.create(null);
                                                        if (!(tycon instanceof Types.TypeRef)) break block6;
                                                        bl = true;
                                                        objectRef.elem = (Types.TypeRef)tycon;
                                                        if ($this.definitions().NothingClass().equals(((Types.TypeRef)objectRef.elem).sym())) {
                                                            bl2 = true;
                                                        } else {
                                                            Symbols.ClassSymbol classSymbol = $this.definitions().AnyClass();
                                                            Symbols.Symbol symbol = ((Types.TypeRef)objectRef.elem).sym();
                                                            bl2 = !(classSymbol != null ? !classSymbol.equals(symbol) : symbol != null);
                                                        }
                                                        if (!bl2) break block6;
                                                        type = $this.copyTypeRef(tycon, ((Types.TypeRef)objectRef.elem).pre(), ((Types.TypeRef)objectRef.elem).sym(), Nil$.MODULE$);
                                                        break block7;
                                                    }
                                                    if (!bl || !((Object)Nil$.MODULE$).equals(((Types.TypeRef)objectRef.elem).args())) break block8;
                                                    type = $this.copyTypeRef(tycon, ((Types.TypeRef)objectRef.elem).pre(), ((Types.TypeRef)objectRef.elem).sym(), args);
                                                    break block7;
                                                }
                                                if (!bl) break block9;
                                                $this.devWarning((Function0<String>)((Object)new Serializable($this, objectRef, tycon){
                                                    public static final long serialVersionUID = 0L;
                                                    private final ObjectRef x2$2;
                                                    private final Types.Type tycon$1;

                                                    public final String apply() {
                                                        return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Dropping ", " from ", " in appliedType."})).s(Predef$.MODULE$.genericWrapArray(new Object[]{((Types.TypeRef)this.x2$2.elem).args(), this.tycon$1}));
                                                    }
                                                    {
                                                        this.x2$2 = x2$2;
                                                        this.tycon$1 = tycon$1;
                                                    }
                                                }));
                                                type = $this.copyTypeRef(tycon, ((Types.TypeRef)objectRef.elem).pre(), ((Types.TypeRef)objectRef.elem).sym(), args);
                                                break block7;
                                            }
                                            if (!(tycon instanceof Types.PolyType)) break block10;
                                            Types.PolyType polyType = (Types.PolyType)tycon;
                                            type = polyType.resultType().instantiateTypeParams(polyType.typeParams(), args);
                                            break block7;
                                        }
                                        if (!(tycon instanceof Types.ExistentialType)) break block11;
                                        Types.ExistentialType existentialType = (Types.ExistentialType)tycon;
                                        type = $this.newExistentialType(existentialType.quantified(), $this.appliedType(existentialType.underlying(), (List<Types.Type>)args));
                                        break block7;
                                    }
                                    if (!(tycon instanceof Types.SingletonType)) break block12;
                                    Types.SingletonType singletonType = (Types.SingletonType)tycon;
                                    type = $this.appliedType(singletonType.widen(), (List<Types.Type>)args);
                                    break block7;
                                }
                                if (!(tycon instanceof Types.RefinedType)) break block13;
                                Types.RefinedType refinedType = (Types.RefinedType)tycon;
                                type = new Types.RefinedType($this, refinedType.parents().map(new Serializable($this, args){
                                    public static final long serialVersionUID = 0L;
                                    private final /* synthetic */ SymbolTable $outer;
                                    private final List args$3;

                                    public final Types.Type apply(Types.Type x$53) {
                                        return this.$outer.appliedType(x$53, (List<Types.Type>)this.args$3);
                                    }
                                    {
                                        if ($outer == null) {
                                            throw null;
                                        }
                                        this.$outer = $outer;
                                        this.args$3 = args$3;
                                    }
                                }, List$.MODULE$.canBuildFrom()), refinedType.decls());
                                break block7;
                            }
                            if (!(tycon instanceof Types.TypeBounds)) break block14;
                            Types.TypeBounds typeBounds = (Types.TypeBounds)tycon;
                            type = $this.TypeBounds().apply($this.appliedType(typeBounds.lo(), (List<Types.Type>)args), $this.appliedType(typeBounds.hi(), (List<Types.Type>)args));
                            break block7;
                        }
                        if (!(tycon instanceof Types.TypeVar)) break block15;
                        Types.TypeVar typeVar = (Types.TypeVar)tycon;
                        type = typeVar.applyArgs(args);
                        break block7;
                    }
                    if (!(tycon instanceof Types.AnnotatedType)) break block16;
                    Types.AnnotatedType annotatedType = (Types.AnnotatedType)tycon;
                    type = new Types.AnnotatedType($this, annotatedType.annotations(), $this.appliedType(annotatedType.underlying(), (List<Types.Type>)args));
                    break block7;
                }
                boolean bl = $this.ErrorType().equals(tycon) ? true : $this.WildcardType().equals(tycon);
                if (!bl) break block17;
                type = tycon;
            }
            return type;
        }
        throw $this.abort($this.debugString(tycon));
    }

    public static Types.Type appliedType(SymbolTable $this, Types.Type tycon, Seq args) {
        return $this.appliedType(tycon, args.toList());
    }

    public static Types.Type appliedType(SymbolTable $this, Symbols.Symbol tyconSym, List args) {
        return $this.appliedType(tyconSym.typeConstructor(), (List<Types.Type>)args);
    }

    public static Types.Type appliedType(SymbolTable $this, Symbols.Symbol tyconSym, Seq args) {
        return $this.appliedType(tyconSym.typeConstructor(), args.toList());
    }

    public static Types.Type genPolyType(SymbolTable $this, List params2, Types.Type tpe) {
        return $this.GenPolyType().apply(params2, tpe);
    }

    public static Types.Type polyType(SymbolTable $this, List params2, Types.Type tpe) {
        return $this.GenPolyType().apply(params2, tpe);
    }

    public static Types.Type typeFunAnon(SymbolTable $this, List tps, Types.Type body2) {
        return $this.typeFun(tps, body2);
    }

    public static Types.Type typeFun(SymbolTable $this, List tps, Types.Type body2) {
        return new Types.PolyType($this, tps, body2);
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Types.Type existentialAbstraction(SymbolTable $this, List tparams2, Types.Type tpe0) {
        Types.Type type;
        if (tparams2.isEmpty()) {
            type = tpe0;
            return type;
        }
        Types.Type tpe = $this.normalizeAliases().apply(tpe0);
        Types.Type tpe1 = new TypeMaps.ExistentialExtrapolation($this, tparams2).extrapolate(tpe);
        List tparams0 = tparams2;
        ObjectRef<List> tparams1 = ObjectRef.create((List)tparams2.filter((Function1)((Object)new Serializable($this, tpe1){
            public static final long serialVersionUID = 0L;
            private final Types.Type tpe1$1;

            public final boolean apply(Symbols.Symbol sym) {
                return this.tpe1$1.contains(sym);
            }
            {
                this.tpe1$1 = tpe1$1;
            }
        })));
        while (true) {
            void var6_6;
            List list2 = (List)tparams1.elem;
            if (!(list2 != null ? !((Object)list2).equals(tparams0) : tparams0 != null)) {
                type = $this.newExistentialType((List)tparams1.elem, tpe1);
                return type;
            }
            List list3 = (List)var6_6.elem;
            var6_6.elem = (List)tparams2.filter((Function1)((Object)new Serializable($this, (ObjectRef)var6_6){
                public static final long serialVersionUID = 0L;
                private final ObjectRef tparams1$1;

                public final boolean apply(Symbols.Symbol p) {
                    return ((List)this.tparams1$1.elem).exists(new Serializable(this, p){
                        public static final long serialVersionUID = 0L;
                        private final Symbols.Symbol p$1;

                        public final boolean apply(Symbols.Symbol p1) {
                            Symbols.Symbol symbol = p1;
                            Symbols.Symbol symbol2 = this.p$1;
                            return !(symbol == null ? symbol2 != null : !symbol.equals(symbol2)) || p1.info().contains(this.p$1);
                        }
                        {
                            this.p$1 = p$1;
                        }
                    });
                }
                {
                    this.tparams1$1 = tparams1$1;
                }
            }));
        }
    }

    public static Types.Type unique(SymbolTable $this, Types.Type tp) {
        if (Statistics$.MODULE$.canEnable()) {
            Statistics.Counter counter = TypesStats$.MODULE$.rawTypeCount();
            if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && counter != null) {
                counter.value_$eq(counter.value() + 1);
            }
        }
        if ($this.scala$reflect$internal$Types$$uniqueRunId() != $this.currentRunId()) {
            $this.scala$reflect$internal$Types$$uniques_$eq(WeakHashSet$.MODULE$.apply($this.scala$reflect$internal$Types$$initialUniquesCapacity(), WeakHashSet$.MODULE$.apply$default$2()));
            $this.scala$reflect$internal$Types$$uniqueRunId_$eq($this.currentRunId());
        }
        return $this.scala$reflect$internal$Types$$uniques().findEntryOrUpdate(tp);
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     */
    public static Types.Type elementExtract(SymbolTable $this, Symbols.Symbol container, Types.Type tp) {
        void var10_12;
        Serializable serializable = new Serializable($this, container){
            public static final long serialVersionUID = 0L;
            public final Symbols.Symbol container$1;

            public final Symbols.Symbol apply() {
                return this.container$1;
            }
            {
                this.container$1 = container$1;
            }
        };
        boolean bl = !container.isAliasType();
        Predef$ predef$ = Predef$.MODULE$;
        if (!bl) {
            throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(serializable.container$1).toString());
        }
        Types.Type type = $this.unwrapWrapperTypes().apply(tp.baseType(container)).dealiasWiden();
        if (type instanceof Types.TypeRef) {
            Types.TypeRef typeRef = (Types.TypeRef)type;
            Symbols.Symbol symbol = container;
            Symbols.Symbol symbol2 = typeRef.sym();
            if (!(symbol != null ? !symbol.equals(symbol2) : symbol2 != null)) {
                $colon$colon $colon$colon;
                if (typeRef.args() instanceof $colon$colon && ((Object)Nil$.MODULE$).equals(($colon$colon = ($colon$colon)typeRef.args()).tl$1())) {
                    Types.Type type2 = (Types.Type)$colon$colon.head();
                    return var10_12;
                }
            }
        }
        Types$NoType$ types$NoType$ = $this.NoType();
        return var10_12;
    }

    public static Option elementExtractOption(SymbolTable $this, Symbols.Symbol container, Types.Type tp) {
        Types.Type type = $this.elementExtract(container, tp);
        Option option = $this.NoType().equals(type) ? None$.MODULE$ : new Some<Types.Type>(type);
        return option;
    }

    public static boolean elementTest(SymbolTable $this, Symbols.Symbol container, Types.Type tp, Function1 f) {
        Types.Type type = $this.elementExtract(container, tp);
        boolean bl = $this.NoType().equals(type) ? false : BoxesRunTime.unboxToBoolean(f.apply(type));
        return bl;
    }

    public static Types.Type elementTransform(SymbolTable $this, Symbols.Symbol container, Types.Type tp, Function1 f) {
        Types.Type type = $this.elementExtract(container, tp);
        Types.Type type2 = $this.NoType().equals(type) ? $this.NoType() : (Types.Type)f.apply(type);
        return type2;
    }

    public static Types.Type transparentShallowTransform(SymbolTable $this, Symbols.Symbol container, Types.Type tp, Function1 f) {
        return Types$class.loop$1($this, tp, container, f);
    }

    public static Types.Type repackExistential(SymbolTable $this, Types.Type tp) {
        Types.Type type = tp;
        Types$NoType$ types$NoType$ = $this.NoType();
        return !(type != null ? !type.equals(types$NoType$) : types$NoType$ != null) ? tp : $this.existentialAbstraction($this.existentialsInType(tp), tp);
    }

    public static boolean containsExistential(SymbolTable $this, Types.Type tpe) {
        return tpe.exists($this.typeIsExistentiallyBound());
    }

    public static List existentialsInType(SymbolTable $this, Types.Type tpe) {
        return tpe.withFilter($this.typeIsExistentiallyBound()).map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Symbols.Symbol apply(Types.Type x$55) {
                return x$55.typeSymbol();
            }
        });
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean scala$reflect$internal$Types$$isDummyOf(SymbolTable $this, Types.Type tpe, Types.Type targ) {
        Symbols.Symbol sym = targ.typeSymbol();
        if (!sym.isTypeParameter()) return false;
        Symbols.Symbol symbol = sym.owner();
        Symbols.Symbol symbol2 = tpe.typeSymbol();
        if (symbol != null) {
            if (!symbol.equals(symbol2)) return false;
            return true;
        }
        if (symbol2 == null) return true;
        return false;
    }

    public static boolean isDummyAppliedType(SymbolTable $this, Types.Type tp) {
        boolean bl;
        Types.Type type = tp.dealias();
        if (type instanceof Types.TypeRef) {
            Types.TypeRef typeRef = (Types.TypeRef)type;
            bl = typeRef.args().exists((Function1<Types.Type, Object>)((Object)new Serializable($this, typeRef){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;
                private final Types.TypeRef x2$3;

                public final boolean apply(Types.Type targ) {
                    return Types$class.scala$reflect$internal$Types$$isDummyOf(this.$outer, this.x2$3, targ);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.x2$3 = x2$3;
                }
            }));
        } else {
            bl = false;
        }
        return bl;
    }

    public static List typeParamsToExistentials(SymbolTable $this, Symbols.Symbol clazz, List tparams2) {
        List<Symbols.TypeSymbol> eparams = $this.mapWithIndex(tparams2, new Serializable($this, clazz){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final Symbols.Symbol clazz$3;

            public final Symbols.TypeSymbol apply(Symbols.Symbol tparam, int i) {
                return (Symbols.TypeSymbol)this.clazz$3.newExistential(this.$outer.newTypeName(new StringBuilder().append((Object)"?").append(BoxesRunTime.boxToInteger(i)).toString()), this.clazz$3.pos(), this.clazz$3.newExistential$default$3()).setInfo(tparam.info().bounds());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.clazz$3 = clazz$3;
            }
        });
        return eparams.map(new Serializable($this, eparams, tparams2){
            public static final long serialVersionUID = 0L;
            private final List eparams$1;
            private final List tparams$3;

            public final Symbols.TypeSymbol apply(Symbols.TypeSymbol x$56) {
                return (Symbols.TypeSymbol)x$56.substInfo(this.tparams$3, this.eparams$1);
            }
            {
                this.eparams$1 = eparams$1;
                this.tparams$3 = tparams$3;
            }
        }, List$.MODULE$.canBuildFrom());
    }

    public static List typeParamsToExistentials(SymbolTable $this, Symbols.Symbol clazz) {
        return $this.typeParamsToExistentials(clazz, clazz.typeParams());
    }

    public static boolean isRawIfWithoutArgs(SymbolTable $this, Symbols.Symbol sym) {
        return sym.isClass() && sym.typeParams().nonEmpty() && sym.isJavaDefined();
    }

    public static boolean isRawType(SymbolTable $this, Types.Type tp) {
        Types.TypeRef typeRef;
        boolean bl;
        return !$this.phase().erasedTypes() && (bl = tp instanceof Types.TypeRef && ((Object)Nil$.MODULE$).equals((typeRef = (Types.TypeRef)tp).args()) ? $this.isRawIfWithoutArgs(typeRef.sym()) : false);
    }

    public static boolean isRaw(SymbolTable $this, Symbols.Symbol sym, List args) {
        return !$this.phase().erasedTypes() && args.isEmpty() && $this.isRawIfWithoutArgs(sym);
    }

    public static Types.TypeBounds singletonBounds(SymbolTable $this, Types.Type hi) {
        return $this.TypeBounds().upper($this.intersectionType((List<Types.Type>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{hi, $this.definitions().SingletonClass().tpe()}))));
    }

    /*
     * Unable to fully structure code
     */
    public static Types.Type nestedMemberType(SymbolTable $this, Symbols.Symbol sym, Types.Type pre, Symbols.Symbol owner) {
        result = Types$class.loop$2($this, sym.tpeHK(), pre, owner);
        if (sym.isTerm()) ** GOTO lbl-1000
        v0 = result.typeSymbol();
        if (!(v0 != null ? v0.equals(sym) == false : sym != null)) lbl-1000:
        // 2 sources

        {
            v1 = true;
        } else {
            v1 = false;
        }
        var6_5 = new Serializable($this, result, sym){
            public static final long serialVersionUID = 0L;
            public final Types.Type result$2;
            public final Symbols.Symbol sym$2;

            public final String apply() {
                return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"(", ").typeSymbol = ", "; expected ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.result$2, this.result$2.typeSymbol(), this.sym$2}));
            }
            {
                this.result$2 = result$2;
                this.sym$2 = sym$2;
            }
        };
        var5_6 = v1;
        var4_7 = Predef$.MODULE$;
        if (!var5_6) {
            throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"(", ").typeSymbol = ", "; expected ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{var6_5.result$2, var6_5.result$2.typeSymbol(), var6_5.sym$2}))).toString());
        }
        return result;
    }

    public static int lubDepth(SymbolTable $this, List ts) {
        int td = Types$class.typeDepth($this, ts);
        int bd = Types$class.baseTypeSeqDepth($this, ts);
        return Types$class.lubDepthAdjust($this, td, Depth$.MODULE$.max$extension(td, bd));
    }

    private static int lubDepthAdjust(SymbolTable $this, int td, int bd) {
        int n;
        MutableSettings.SettingValue settingValue = $this.settings().XfullLubs();
        MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
        if (BoxesRunTime.unboxToBoolean(settingValue.value())) {
            n = bd;
        } else {
            Depth$ depth$ = Depth$.MODULE$;
            if (new Depth(bd).$less$eq(new Depth(3 < -3 ? depth$.AnyDepth() : 3))) {
                n = bd;
            } else {
                Depth$ depth$2 = Depth$.MODULE$;
                if (new Depth(bd).$less$eq(new Depth(5 < -3 ? depth$2.AnyDepth() : 5))) {
                    n = Depth$.MODULE$.max$extension(td, Depth$.MODULE$.decr$extension1(bd));
                } else {
                    Depth$ depth$3 = Depth$.MODULE$;
                    n = new Depth(bd).$less$eq(new Depth(7 < -3 ? depth$3.AnyDepth() : 7)) ? Depth$.MODULE$.max$extension(td, Depth$.MODULE$.decr$extension0(bd, 2)) : Depth$.MODULE$.max$extension(Depth$.MODULE$.decr$extension1(td), Depth$.MODULE$.decr$extension0(bd, 3));
                }
            }
        }
        return n;
    }

    private static int symTypeDepth(SymbolTable $this, List syms) {
        return Types$class.typeDepth($this, syms.map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Types.Type apply(Symbols.Symbol x$57) {
                return x$57.info();
            }
        }, List$.MODULE$.canBuildFrom()));
    }

    private static int typeDepth(SymbolTable $this, List tps) {
        return $this.maxDepth(tps);
    }

    private static int baseTypeSeqDepth(SymbolTable $this, List tps) {
        return $this.maxbaseTypeSeqDepth(tps);
    }

    public static boolean isPopulated(SymbolTable $this, Types.Type tp1, Types.Type tp2) {
        return Types$class.check$1($this, tp1, tp2) && Types$class.check$1($this, tp2, tp1);
    }

    public static boolean needsOuterTest(SymbolTable $this, Types.Type patType, Types.Type selType, Symbols.Symbol currentOwner) {
        Types.TypeRef typeRef;
        Types.Type pre1;
        Types.Type type = patType.dealias();
        boolean bl = type instanceof Types.TypeRef ? (pre1 = Types$class.maybeCreateDummyClone$1($this, (typeRef = (Types.TypeRef)type).pre(), typeRef.sym(), currentOwner)) != $this.NoType() && $this.isPopulated($this.copyTypeRef(patType, pre1, typeRef.sym(), typeRef.args()), selType) : false;
        return bl;
    }

    public static Types.Type normalizePlus(SymbolTable $this, Types.Type tp) {
        Types.Type type;
        if ($this.isRawType(tp)) {
            type = $this.rawToExistential().apply(tp);
        } else {
            Types.SingleType singleType;
            Types.Type type2 = tp.normalize();
            Types.Type type3 = type2 instanceof Types.SingleType && (singleType = (Types.SingleType)type2).sym().isModule() ? singleType.underlying().normalize() : tp.normalize();
            type = type3;
        }
        return type;
    }

    public static boolean isSameTypes(SymbolTable $this, List tps1, List tps2) {
        return tps1.corresponds(tps2, new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Types.Type x$60, Types.Type x$61) {
                return x$60.$eq$colon$eq(x$61);
            }
        });
    }

    public static final boolean sameLength(SymbolTable $this, List xs1, List xs2) {
        return $this.compareLengths(xs1, xs2) == 0;
    }

    public static final int compareLengths(SymbolTable $this, List xs1, List xs2) {
        while (true) {
            block6: {
                int n;
                block5: {
                    block4: {
                        if (!xs1.isEmpty()) break block4;
                        n = xs2.isEmpty() ? 0 : -1;
                        break block5;
                    }
                    if (!xs2.isEmpty()) break block6;
                    n = 1;
                }
                return n;
            }
            xs2 = (List)xs2.tail();
            xs1 = (List)xs1.tail();
        }
    }

    public static final boolean hasLength(SymbolTable $this, List xs, int len) {
        return xs.lengthCompare(len) == 0;
    }

    public static int basetypeRecursions(SymbolTable $this) {
        return $this.scala$reflect$internal$Types$$_basetypeRecursions();
    }

    public static void basetypeRecursions_$eq(SymbolTable $this, int value) {
        $this.scala$reflect$internal$Types$$_basetypeRecursions_$eq(value);
    }

    public static HashSet pendingBaseTypes(SymbolTable $this) {
        return $this.scala$reflect$internal$Types$$_pendingBaseTypes();
    }

    public static boolean isEligibleForPrefixUnification(SymbolTable $this, Types.Type tp) {
        Types.TypeVar typeVar;
        Types.SingleType singleType;
        boolean bl = tp instanceof Types.SingleType ? !(singleType = (Types.SingleType)tp).sym().hasFlag(16384) && $this.isEligibleForPrefixUnification(singleType.pre()) : (tp instanceof Types.TypeVar ? !(typeVar = (Types.TypeVar)tp).instValid() || $this.isEligibleForPrefixUnification(typeVar.constr().inst()) : tp instanceof Types.RefinedType);
        return bl;
    }

    public static boolean isErrorOrWildcard(SymbolTable $this, Types.Type tp) {
        return tp == $this.ErrorType() || tp == $this.WildcardType();
    }

    public static boolean isSingleType(SymbolTable $this, Types.Type tp) {
        boolean bl = tp instanceof Types.ThisType ? true : (tp instanceof Types.SuperType ? true : tp instanceof Types.SingleType);
        boolean bl2 = bl;
        return bl2;
    }

    public static boolean isConstantType(SymbolTable $this, Types.Type tp) {
        boolean bl = tp instanceof Types.ConstantType;
        return bl;
    }

    public static boolean isExistentialType(SymbolTable $this, Types.Type tp) {
        boolean bl = tp instanceof Types.ExistentialType ? true : (tp != null && tp.dealias() != tp ? $this.isExistentialType(tp.dealias()) : false);
        return bl;
    }

    public static boolean isImplicitMethodType(SymbolTable $this, Types.Type tp) {
        boolean bl;
        if (tp instanceof Types.MethodType) {
            Types.MethodType methodType = (Types.MethodType)tp;
            bl = methodType.isImplicit();
        } else {
            bl = false;
        }
        return bl;
    }

    public static boolean isUseableAsTypeArg(SymbolTable $this, Types.Type tp) {
        return Types$class.isInternalTypeUsedAsTypeArg($this, tp) || Types$class.isHKTypeRef($this, tp) || Types$class.isValueElseNonValue($this, tp);
    }

    private static boolean isHKTypeRef(SymbolTable $this, Types.Type tp) {
        Types.TypeRef typeRef;
        boolean bl = tp instanceof Types.TypeRef && ((Object)Nil$.MODULE$).equals((typeRef = (Types.TypeRef)tp).args()) ? tp.isHigherKinded() : false;
        return bl;
    }

    public static final boolean isUseableAsTypeArgs(SymbolTable $this, List tps) {
        block3: {
            boolean bl;
            block2: {
                while (true) {
                    if (((Object)Nil$.MODULE$).equals(tps)) {
                        bl = true;
                        break block2;
                    }
                    if (!(tps instanceof $colon$colon)) break block3;
                    $colon$colon $colon$colon = ($colon$colon)tps;
                    if (!$this.isUseableAsTypeArg((Types.Type)$colon$colon.head())) break;
                    tps = $colon$colon.tl$1();
                }
                bl = false;
            }
            return bl;
        }
        throw new MatchError(tps);
    }

    private static boolean isInternalTypeUsedAsTypeArg(SymbolTable $this, Types.Type tp) {
        boolean bl = $this.WildcardType().equals(tp) ? true : (tp instanceof Types.BoundedWildcardType ? true : ($this.ErrorType().equals(tp) ? true : tp instanceof Types.TypeVar));
        return bl;
    }

    private static boolean isAlwaysValueType(SymbolTable $this, Types.Type tp) {
        boolean bl = tp instanceof Types.RefinedType ? true : (tp instanceof Types.ExistentialType ? true : tp instanceof Types.ConstantType);
        return bl;
    }

    private static boolean isAlwaysNonValueType(SymbolTable $this, Types.Type tp) {
        Types.PolyType polyType;
        boolean bl = tp instanceof Types.OverloadedType ? true : (tp instanceof Types.NullaryMethodType ? true : (tp instanceof Types.MethodType ? true : tp instanceof Types.PolyType && (polyType = (Types.PolyType)tp).resultType() instanceof Types.MethodType));
        return bl;
    }

    private static boolean isValueElseNonValue(SymbolTable $this, Types.Type tp) {
        block11: {
            boolean bl;
            block6: {
                block10: {
                    Types.TypeRef typeRef;
                    boolean bl2;
                    block9: {
                        block8: {
                            block7: {
                                while (true) {
                                    bl2 = false;
                                    typeRef = null;
                                    if (Types$class.isAlwaysValueType($this, tp)) {
                                        bl = true;
                                        break block6;
                                    }
                                    if (Types$class.isAlwaysNonValueType($this, tp)) {
                                        bl = false;
                                        break block6;
                                    }
                                    if (!(tp instanceof Types.AnnotatedType)) break;
                                    Types.AnnotatedType annotatedType = (Types.AnnotatedType)tp;
                                    tp = annotatedType.underlying();
                                }
                                if (!(tp instanceof Types.SingleType)) break block7;
                                Types.SingleType singleType = (Types.SingleType)tp;
                                bl = singleType.sym().isValue();
                                break block6;
                            }
                            if (!(tp instanceof Types.TypeRef)) break block8;
                            bl2 = true;
                            typeRef = (Types.TypeRef)tp;
                            if (!tp.isHigherKinded()) break block8;
                            bl = false;
                            break block6;
                        }
                        if (!(tp instanceof Types.ThisType)) break block9;
                        Types.ThisType thisType = (Types.ThisType)tp;
                        bl = !thisType.sym().isPackageClass();
                        break block6;
                    }
                    if (!bl2) break block10;
                    bl = !typeRef.sym().isPackageClass();
                    break block6;
                }
                if (!(tp instanceof Types.PolyType)) break block11;
                bl = true;
            }
            return bl;
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"isValueElseNonValue called with third-way type ").append(tp).toString());
    }

    public static boolean isNonRefinementClassType(SymbolTable $this, Types.Type tpe) {
        boolean bl;
        if (tpe instanceof Types.SingleType) {
            Types.SingleType singleType = (Types.SingleType)tpe;
            bl = singleType.sym().isModuleClass();
        } else {
            Types.TypeRef typeRef;
            bl = tpe instanceof Types.TypeRef ? (typeRef = (Types.TypeRef)tpe).sym().isClass() && !typeRef.sym().isRefinementClass() : $this.ErrorType().equals(tpe);
        }
        return bl;
    }

    public static boolean isSubArgs(SymbolTable $this, List tps1, List tps2, List tparams2, int depth) {
        return $this.corresponds3(tps1, tps2, $this.mapList(tparams2, new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final int apply(Symbols.Symbol x$62) {
                return x$62.variance();
            }
        }), new Serializable($this, depth){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final int depth$1;

            public final boolean apply(Types.Type t1, Types.Type t2, int variance) {
                return Types$class.isSubArg$1(this.$outer, t1, t2, variance, this.depth$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.depth$1 = depth$1;
            }
        });
    }

    public static boolean specializesSym(SymbolTable $this, Types.Type tp, Symbols.Symbol sym, int depth) {
        return tp.typeSymbol().isBottomSubClass(sym.owner()) || Types$class.specializedBy$1($this, tp.nonPrivateMember(sym.name()), tp, sym, depth);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean specializesSym(SymbolTable $this, Types.Type preLo, Symbols.Symbol symLo, Types.Type preHi, Symbols.Symbol symHi, int depth) {
        if (!symHi.isAliasType() && !symHi.isTerm()) {
            if (!symHi.isAbstractType()) return false;
        }
        Serializable serializable = new Serializable($this, preLo, symLo, preHi, symHi, depth){
            public static final long serialVersionUID = 0L;
            public final Types.Type preLo$1;
            public final Symbols.Symbol symLo$1;
            public final Types.Type preHi$1;
            public final Symbols.Symbol symHi$1;
            public final int depth$3;

            public final Tuple5<Types.Type, Symbols.Symbol, Types.Type, Symbols.Symbol, Depth> apply() {
                return new Tuple5<Types.Type, Symbols.Symbol, Types.Type, Symbols.Symbol, Depth>(this.preLo$1, this.symLo$1, this.preHi$1, this.symHi$1, new Depth(this.depth$3));
            }
            {
                this.preLo$1 = preLo$1;
                this.symLo$1 = symLo$1;
                this.preHi$1 = preHi$1;
                this.symHi$1 = symHi$1;
                this.depth$3 = depth$3;
            }
        };
        boolean bl = symLo != $this.NoSymbol() && symHi != $this.NoSymbol();
        Predef$ predef$ = Predef$.MODULE$;
        if (!bl) {
            throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append(new Tuple5<Types.Type, Symbols.Symbol, Types.Type, Symbols.Symbol, Depth>(serializable.preLo$1, serializable.symLo$1, serializable.preHi$1, serializable.symHi$1, new Depth(serializable.depth$3))).toString());
        }
        Types.Type tpHi = preHi.memberInfo(symHi).substThis(preHi.typeSymbol(), preLo);
        Types.Type tpLo = preLo.memberType(symLo);
        $this.debuglog((Function0<String>)((Object)new Serializable($this, tpHi, preHi, symHi){
            public static final long serialVersionUID = 0L;
            private final Types.Type tpHi$1;
            private final Types.Type preHi$1;
            private final Symbols.Symbol symHi$1;

            public final String apply() {
                return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"specializesSymHi: ", " . ", " : ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.preHi$1, this.symHi$1, this.tpHi$1}));
            }
            {
                this.tpHi$1 = tpHi$1;
                this.preHi$1 = preHi$1;
                this.symHi$1 = symHi$1;
            }
        }));
        $this.debuglog((Function0<String>)((Object)new Serializable($this, tpLo, preLo, symLo){
            public static final long serialVersionUID = 0L;
            private final Types.Type tpLo$1;
            private final Types.Type preLo$1;
            private final Symbols.Symbol symLo$1;

            public final String apply() {
                return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"specializesSymLo: ", " . ", " : ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.preLo$1, this.symLo$1, this.tpLo$1}));
            }
            {
                this.tpLo$1 = tpLo$1;
                this.preLo$1 = preLo$1;
                this.symLo$1 = symLo$1;
            }
        }));
        if (symHi.isTerm()) {
            if (!$this.isSubType(tpLo, tpHi, depth)) return false;
            if (symHi.isStable()) {
                if (!symLo.isStable()) return false;
            }
            if (!symLo.hasVolatileType()) return true;
            if (symHi.hasVolatileType()) return true;
            if (!tpHi.isWildcard()) return false;
            return true;
        }
        if (symHi.isAbstractType()) {
            if (!tpHi.bounds().containsType(tpLo)) return false;
            Nil$ nil$ = Nil$.MODULE$;
            Nil$ nil$2 = Nil$.MODULE$;
            if (!$this.kindsConform(new $colon$colon<Nothing$>((Nothing$)((Object)symHi), nil$), new $colon$colon<Nothing$>((Nothing$)((Object)tpLo), nil$2), preLo, symLo.owner())) return false;
            return true;
        }
        boolean bl2 = tpLo.$eq$colon$eq(tpHi);
        if (!bl2) return false;
        return true;
    }

    public static final boolean matchesType(SymbolTable $this, Types.Type tp1, Types.Type tp2, boolean alwaysMatchSimple) {
        boolean bl;
        block30: {
            block31: {
                while (true) {
                    Types.MethodType methodType;
                    Types.TypeRef typeRef;
                    if (tp1 instanceof Types.MethodType) {
                        boolean bl2;
                        Types.MethodType methodType2 = (Types.MethodType)tp1;
                        if (tp2 instanceof Types.MethodType) {
                            Types.MethodType methodType3 = (Types.MethodType)tp2;
                            bl2 = methodType2.isImplicit() == methodType3.isImplicit() && $this.matchingParams(methodType2.params(), methodType3.params(), methodType2.isJava(), methodType3.isJava()) && Types$class.matchesQuantified$1($this, methodType2.params(), methodType3.params(), methodType2.resultType(), methodType3.resultType(), alwaysMatchSimple);
                        } else {
                            Types.TypeRef typeRef2;
                            if (tp2 instanceof Types.NullaryMethodType) {
                                Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)tp2;
                                if (methodType2.params().isEmpty()) {
                                    tp2 = nullaryMethodType.resultType();
                                    tp1 = methodType2.resultType();
                                    continue;
                                }
                                tp2 = nullaryMethodType.resultType();
                                continue;
                            }
                            if (tp2 instanceof Types.ExistentialType) {
                                Types.ExistentialType existentialType = (Types.ExistentialType)tp2;
                                if (alwaysMatchSimple) {
                                    alwaysMatchSimple = true;
                                    tp2 = existentialType.underlying();
                                    continue;
                                }
                                bl2 = false;
                            } else if (tp2 instanceof Types.TypeRef && ((Object)Nil$.MODULE$).equals((typeRef2 = (Types.TypeRef)tp2).args())) {
                                if (methodType2.params().isEmpty() && typeRef2.sym().isModuleClass()) {
                                    tp1 = methodType2.resultType();
                                    continue;
                                }
                                bl2 = false;
                            } else {
                                bl2 = false;
                            }
                        }
                        bl = bl2;
                        break block30;
                    }
                    if (tp1 instanceof Types.NullaryMethodType) {
                        Types.TypeRef typeRef3;
                        Types.MethodType methodType4;
                        Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)tp1;
                        if (tp2 instanceof Types.MethodType && ((Object)Nil$.MODULE$).equals((methodType4 = (Types.MethodType)tp2).params())) {
                            tp2 = methodType4.resultType();
                            tp1 = nullaryMethodType.resultType();
                            continue;
                        }
                        if (tp2 instanceof Types.NullaryMethodType) {
                            Types.NullaryMethodType nullaryMethodType2 = (Types.NullaryMethodType)tp2;
                            tp2 = nullaryMethodType2.resultType();
                            tp1 = nullaryMethodType.resultType();
                            continue;
                        }
                        if (tp2 instanceof Types.ExistentialType) {
                            Types.ExistentialType existentialType = (Types.ExistentialType)tp2;
                            if (alwaysMatchSimple) {
                                alwaysMatchSimple = true;
                                tp2 = existentialType.underlying();
                                continue;
                            }
                            bl = false;
                            break block30;
                        }
                        if (tp2 instanceof Types.TypeRef && ((Object)Nil$.MODULE$).equals((typeRef3 = (Types.TypeRef)tp2).args()) && typeRef3.sym().isModuleClass()) {
                            tp1 = nullaryMethodType.resultType();
                            continue;
                        }
                        tp1 = nullaryMethodType.resultType();
                        continue;
                    }
                    if (tp1 instanceof Types.PolyType) {
                        boolean bl3;
                        Types.PolyType polyType = (Types.PolyType)tp1;
                        if (tp2 instanceof Types.PolyType) {
                            Types.PolyType polyType2 = (Types.PolyType)tp2;
                            if (polyType.typeParams().corresponds(polyType2.typeParams(), new Serializable($this){
                                public static final long serialVersionUID = 0L;

                                public final boolean apply(Symbols.Symbol x$65, Symbols.Symbol x$66) {
                                    return x$65 == x$66;
                                }
                            })) {
                                tp2 = polyType2.resultType();
                                tp1 = polyType.resultType();
                                continue;
                            }
                            bl3 = Types$class.matchesQuantified$1($this, polyType.typeParams(), polyType2.typeParams(), polyType.resultType(), polyType2.resultType(), alwaysMatchSimple);
                        } else if (tp2 instanceof Types.ExistentialType) {
                            Types.ExistentialType existentialType = (Types.ExistentialType)tp2;
                            if (alwaysMatchSimple) {
                                alwaysMatchSimple = true;
                                tp2 = existentialType.underlying();
                                continue;
                            }
                            bl3 = false;
                        } else {
                            bl3 = false;
                        }
                        bl = bl3;
                        break block30;
                    }
                    if (tp1 instanceof Types.ExistentialType) {
                        boolean bl4;
                        Types.ExistentialType existentialType = (Types.ExistentialType)tp1;
                        if (tp2 instanceof Types.ExistentialType) {
                            Types.ExistentialType existentialType2 = (Types.ExistentialType)tp2;
                            bl4 = Types$class.matchesQuantified$1($this, existentialType.quantified(), existentialType2.quantified(), existentialType.underlying(), existentialType2.underlying(), alwaysMatchSimple);
                        } else {
                            if (alwaysMatchSimple) {
                                alwaysMatchSimple = true;
                                tp1 = existentialType.underlying();
                                continue;
                            }
                            bl4 = Types$class.lastTry$1($this, tp1, tp2, alwaysMatchSimple);
                        }
                        bl = bl4;
                        break block30;
                    }
                    if (!(tp1 instanceof Types.TypeRef) || !((Object)Nil$.MODULE$).equals((typeRef = (Types.TypeRef)tp1).args()) || !typeRef.sym().isModuleClass()) break block31;
                    if (tp2 instanceof Types.MethodType && ((Object)Nil$.MODULE$).equals((methodType = (Types.MethodType)tp2).params())) {
                        tp2 = methodType.resultType();
                        continue;
                    }
                    if (!(tp2 instanceof Types.NullaryMethodType)) break;
                    Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)tp2;
                    tp2 = nullaryMethodType.resultType();
                }
                bl = Types$class.lastTry$1($this, tp1, tp2, alwaysMatchSimple);
                break block30;
            }
            bl = Types$class.lastTry$1($this, tp1, tp2, alwaysMatchSimple);
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean matchingParams(SymbolTable $this, List syms1, List syms2, boolean syms1isJava, boolean syms2isJava) {
        $colon$colon $colon$colon;
        $colon$colon $colon$colon2;
        block10: {
            Types.Type tp2;
            Types.Type tp1;
            block11: {
                if (((Object)Nil$.MODULE$).equals(syms1)) {
                    return syms2.isEmpty();
                }
                if (!(syms1 instanceof $colon$colon)) throw new MatchError(syms1);
                $colon$colon2 = ($colon$colon)syms1;
                if (((Object)Nil$.MODULE$).equals(syms2)) {
                    return false;
                }
                if (!(syms2 instanceof $colon$colon)) throw new MatchError(syms2);
                $colon$colon = ($colon$colon)syms2;
                tp1 = ((Symbols.Symbol)$colon$colon2.head()).tpe();
                if (tp1.$eq$colon$eq(tp2 = ((Symbols.Symbol)$colon$colon.head()).tpe())) break block10;
                if (!syms1isJava) break block11;
                Symbols.Symbol symbol = tp2.typeSymbol();
                Symbols.ClassSymbol classSymbol = $this.definitions().ObjectClass();
                if (symbol != null ? !symbol.equals(classSymbol) : classSymbol != null) break block11;
                Symbols.Symbol symbol2 = tp1.typeSymbol();
                Symbols.ClassSymbol classSymbol2 = $this.definitions().AnyClass();
                if (!(symbol2 == null ? classSymbol2 != null : !symbol2.equals(classSymbol2))) break block10;
            }
            if (!syms2isJava) return false;
            Symbols.Symbol symbol = tp1.typeSymbol();
            Symbols.ClassSymbol classSymbol = $this.definitions().ObjectClass();
            if (symbol == null) {
                if (classSymbol != null) {
                    return false;
                }
            } else if (!symbol.equals(classSymbol)) return false;
            Symbols.Symbol symbol3 = tp2.typeSymbol();
            Symbols.ClassSymbol classSymbol3 = $this.definitions().AnyClass();
            if (symbol3 == null) {
                if (classSymbol3 != null) {
                    return false;
                }
            } else if (!symbol3.equals(classSymbol3)) return false;
        }
        if (!$this.matchingParams($colon$colon2.tl$1(), $colon$colon.tl$1(), syms1isJava, syms2isJava)) return false;
        return true;
    }

    public static boolean isWithinBounds(SymbolTable $this, Types.Type pre, Symbols.Symbol owner2, List tparams2, List targs) {
        List<Types.TypeBounds> bounds = $this.instantiatedBounds(pre, owner2, tparams2, targs);
        if (targs.exists($this.typeHasAnnotations())) {
            bounds = $this.adaptBoundsToAnnotations(bounds, tparams2, targs);
        }
        return bounds.corresponds(targs, $this.boundsContainType());
    }

    public static List instantiatedBounds(SymbolTable $this, Types.Type pre, Symbols.Symbol owner2, List tparams2, List targs) {
        return $this.mapList(tparams2, new Serializable($this, pre, owner2, tparams2, targs){
            public static final long serialVersionUID = 0L;
            private final Types.Type pre$2;
            private final Symbols.Symbol owner$3;
            private final List tparams$4;
            private final List targs$1;

            public final Types.TypeBounds apply(Symbols.Symbol x$67) {
                return x$67.info().asSeenFrom(this.pre$2, this.owner$3).instantiateTypeParams(this.tparams$4, this.targs$1).bounds();
            }
            {
                this.pre$2 = pre$2;
                this.owner$3 = owner$3;
                this.tparams$4 = tparams$4;
                this.targs$1 = targs$1;
            }
        });
    }

    public static Types.Type elimAnonymousClass(SymbolTable $this, Types.Type t) {
        Types.TypeRef typeRef;
        Types.Type type = t instanceof Types.TypeRef && ((Object)Nil$.MODULE$).equals((typeRef = (Types.TypeRef)t).args()) && typeRef.sym().isAnonymousClass() ? typeRef.sym().classBound().asSeenFrom(typeRef.pre(), typeRef.sym().owner()) : t;
        return type;
    }

    public static List typeVarsInType(SymbolTable $this, Types.Type tp) {
        ObjectRef<Nil$> tvs = ObjectRef.create(Nil$.MODULE$);
        tp.foreach((Function1<Types.Type, BoxedUnit>)((Object)new Serializable($this, tvs){
            public static final long serialVersionUID = 0L;
            private final ObjectRef tvs$2;

            public final void apply(Types.Type x0$4) {
                block0: {
                    if (!(x0$4 instanceof Types.TypeVar)) break block0;
                    Types.TypeVar typeVar = (Types.TypeVar)x0$4;
                    this.tvs$2.elem = ((List)this.tvs$2.elem).$colon$colon(typeVar);
                }
            }
            {
                this.tvs$2 = tvs$2;
            }
        }));
        return ((List)tvs.elem).reverse();
    }

    /*
     * WARNING - void declaration
     */
    public static final Object suspendingTypeVars(SymbolTable $this, List tvs, Function0 op) {
        Object r;
        List saved = tvs.map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Types.TypeVar x$68) {
                return x$68.scala$reflect$internal$Types$$suspended();
            }
        }, List$.MODULE$.canBuildFrom());
        tvs.foreach(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final void apply(Types.TypeVar x$69) {
                x$69.scala$reflect$internal$Types$$suspended_$eq(true);
            }
        });
        try {
            r = op.apply();
            $this.foreach2(tvs, saved, new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final void apply(Types.TypeVar x$70, boolean x$71) {
                    x$70.scala$reflect$internal$Types$$suspended_$eq(x$71);
                }
            });
        }
        catch (Throwable throwable) {
            void var3_3;
            $this.foreach2(tvs, var3_3, new /* invalid duplicate definition of identical inner class */);
            throw throwable;
        }
        return r;
    }

    /*
     * Loose catch block
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Types.Type mergePrefixAndArgs(SymbolTable $this, List tps, int variance, int depth) {
        Types.Type type;
        Types.Type type2;
        boolean bl = false;
        $colon$colon $colon$colon = null;
        if (tps instanceof $colon$colon) {
            bl = true;
            $colon$colon = ($colon$colon)tps;
            if (((Object)Nil$.MODULE$).equals($colon$colon.tl$1())) {
                return (Types.Type)$colon$colon.head();
            }
        }
        if (bl && $colon$colon.head() instanceof Types.TypeRef) {
            Types.TypeRef typeRef = (Types.TypeRef)$colon$colon.head();
            List<Types.Type> pres = tps.map(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final Types.Type apply(Types.Type x$72) {
                    return x$72.prefix();
                }
            }, List$.MODULE$.canBuildFrom());
            Types.Type pre2 = Variance$.MODULE$.isPositive$extension(variance) ? $this.lub(pres, depth) : $this.glb(pres, depth);
            List<List<List<Types.Type>>> argss = tps.map(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final List<Types.Type> apply(Types.Type x$73) {
                    return x$73.normalize().typeArgs();
                }
            }, List$.MODULE$.canBuildFrom());
            ListBuffer capturedParams = new ListBuffer();
            Symbols.Symbol symbol = typeRef.sym();
            Symbols.ClassSymbol classSymbol = $this.definitions().ArrayClass();
            if (!(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null) && $this.phase().erasedTypes()) {
                List<Types.Type> args;
                type2 = argss.exists($this.typeListIsEmpty()) ? $this.NoType() : (((LinearSeqOptimized)(args = argss.map(new Serializable($this){
                    public static final long serialVersionUID = 0L;

                    public final Types.Type apply(List<Types.Type> x$74) {
                        return x$74.head();
                    }
                }, List$.MODULE$.canBuildFrom())).tail()).forall(new Serializable($this, args){
                    public static final long serialVersionUID = 0L;
                    private final List args$2;

                    public final boolean apply(Types.Type x$75) {
                        return x$75.$eq$colon$eq((Types.Type)this.args$2.head());
                    }
                    {
                        this.args$2 = args$2;
                    }
                }) ? $this.typeRef(pre2, typeRef.sym(), (List<Types.Type>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{(Types.Type)args.head()}))) : (args.exists((Function1<Types.Type, Object>)((Object)new Serializable($this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SymbolTable $outer;

                    public final boolean apply(Types.Type arg) {
                        return this.$outer.definitions().isPrimitiveValueClass(arg.typeSymbol());
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                })) ? $this.definitions().ObjectTpe() : $this.typeRef(pre2, typeRef.sym(), (List<Types.Type>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{$this.lub((List)args)})))));
                return type2;
            } else {
                Types$NoType$ types$NoType$;
                Option option = $this.transposeSafe(argss);
                if (None$.MODULE$.equals(option)) {
                    $this.debuglog((Function0<String>)((Object)new Serializable($this, argss, tps){
                        public static final long serialVersionUID = 0L;
                        private final List argss$1;
                        private final List tps$1;

                        public final String apply() {
                            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"transposed irregular matrix!? tps=", " argss=", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.tps$1, this.argss$1}));
                        }
                        {
                            this.argss$1 = argss$1;
                            this.tps$1 = tps$1;
                        }
                    }));
                    types$NoType$ = $this.NoType();
                } else {
                    if (!(option instanceof Some)) throw new MatchError(option);
                    Some some = (Some)option;
                    List<Types.Type> args = $this.map2(typeRef.sym().typeParams(), (List)some.x(), new Serializable($this, capturedParams, variance, depth){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ SymbolTable $outer;
                        private final ListBuffer capturedParams$1;
                        private final int variance$1;
                        private final int depth$4;

                        public final Types.Type apply(Symbols.Symbol tparam, List<Types.Type> as0) {
                            Types.Type type;
                            List as = (List)as0.distinct();
                            if (as.size() == 1) {
                                type = (Types.Type)as.head();
                            } else if (Depth$.MODULE$.isZero$extension(this.depth$4)) {
                                this.$outer.log((Function0<Object>)((Object)new Serializable(this, as, tparam){
                                    public static final long serialVersionUID = 0L;
                                    private final List as$1;
                                    private final Symbols.Symbol tparam$3;

                                    public final String apply() {
                                        Predef$ predef$ = Predef$.MODULE$;
                                        return new StringOps("Giving up merging args: can't unify %s under %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{this.as$1.mkString(", "), this.tparam$3.fullLocationString()}));
                                    }
                                    {
                                        this.as$1 = as$1;
                                        this.tparam$3 = tparam$3;
                                    }
                                }));
                                type = this.$outer.NoType();
                            } else if (tparam.variance() == this.variance$1) {
                                type = this.$outer.lub(as, Depth$.MODULE$.decr$extension1(this.depth$4));
                            } else if (tparam.variance() == Variance$.MODULE$.flip$extension(this.variance$1)) {
                                type = this.$outer.glb(as, Depth$.MODULE$.decr$extension1(this.depth$4));
                            } else {
                                Types.Type g;
                                Types.Type l = this.$outer.lub(as, Depth$.MODULE$.decr$extension1(this.depth$4));
                                if (l.$less$colon$less(g = this.$outer.glb(as, Depth$.MODULE$.decr$extension1(this.depth$4)))) {
                                    type = l;
                                } else {
                                    Symbols.TypeSymbol qvar = (Symbols.TypeSymbol)this.$outer.commonOwner(as).freshExistential("").setInfo(this.$outer.TypeBounds().apply(g, l));
                                    this.capturedParams$1.$plus$eq(qvar);
                                    type = qvar.tpe();
                                }
                            }
                            return type;
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.capturedParams$1 = capturedParams$1;
                            this.variance$1 = variance$1;
                            this.depth$4 = depth$4;
                        }
                    });
                    types$NoType$ = args.contains($this.NoType()) ? $this.NoType() : $this.existentialAbstraction(capturedParams.toList(), $this.typeRef(pre2, typeRef.sym(), args));
                }
                type2 = types$NoType$;
            }
            return type2;
        }
        if (bl && $colon$colon.head() instanceof Types.SingleType) {
            Types.SingleType singleType = (Types.SingleType)$colon$colon.head();
            List<Types.Type> pres = tps.map(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final Types.Type apply(Types.Type x$76) {
                    return x$76.prefix();
                }
            }, List$.MODULE$.canBuildFrom());
            Types.Type pre = Variance$.MODULE$.isPositive$extension(variance) ? $this.lub(pres, depth) : $this.glb(pres, depth);
            type = $this.singleType(pre, singleType.sym());
            return type;
        } else {
            void var23_26;
            if (!bl || !($colon$colon.head() instanceof Types.ExistentialType)) throw $this.abort(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"mergePrefixAndArgs(", ", ", ", ", "): unsupported tps"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{tps, new Variance(variance), new Depth(depth)})));
            Types.ExistentialType existentialType = (Types.ExistentialType)$colon$colon.head();
            Types.Type type3 = existentialType.underlying();
            Types.Type type4 = $this.mergePrefixAndArgs($colon$colon.tl$1().$colon$colon(type3), variance, depth);
            if ($this.NoType().equals(type4)) {
                Types$NoType$ types$NoType$ = $this.NoType();
                return var23_26;
            } else {
                Types.Type type5 = $this.existentialAbstraction(existentialType.quantified(), type4);
            }
            return var23_26;
            catch (Types.MalformedType malformedType) {
                type2 = $this.NoType();
            }
            return type2;
            catch (Types.MalformedType malformedType) {
                type = $this.NoType();
            }
        }
        return type;
    }

    public static void addMember(SymbolTable $this, Types.Type thistp, Types.Type tp, Symbols.Symbol sym) {
        $this.addMember(thistp, tp, sym, Depth$.MODULE$.AnyDepth());
    }

    public static void addMember(SymbolTable $this, Types.Type thistp, Types.Type tp, Symbols.Symbol sym, int depth) {
        Symbols.Symbol symbol = sym;
        Symbols.NoSymbol noSymbol = $this.NoSymbol();
        Predef$.MODULE$.assert(symbol != null ? !symbol.equals(noSymbol) : noSymbol != null);
        if (!$this.specializesSym(thistp, sym, depth)) {
            if (sym.isTerm()) {
                List list2 = tp.nonPrivateDecl(sym.name()).alternatives();
                while (!((AbstractIterable)list2).isEmpty()) {
                    Symbols.Symbol symbol2 = (Symbols.Symbol)((AbstractIterable)list2).head();
                    if ($this.specializesSym(thistp, sym, thistp, symbol2, depth)) {
                        tp.decls().unlink(symbol2);
                    }
                    list2 = (List)list2.tail();
                }
            }
            tp.decls().enter(sym);
        }
    }

    public static boolean isJavaVarargsAncestor(SymbolTable $this, Symbols.Symbol clazz) {
        return clazz.isClass() && clazz.isJavaDefined() && clazz.info().nonPrivateDecls().exists((Function1<Symbols.Symbol, Object>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final boolean apply(Symbols.Symbol m) {
                return this.$outer.definitions().isJavaVarArgsMethod(m);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
    }

    public static boolean inheritsJavaVarArgsMethod(SymbolTable $this, Symbols.Symbol clazz) {
        return clazz.thisType().baseClasses().exists((Function1<Symbols.Symbol, Object>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final boolean apply(Symbols.Symbol clazz) {
                return this.$outer.isJavaVarargsAncestor(clazz);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
    }

    public static String indent(SymbolTable $this) {
        return $this.scala$reflect$internal$Types$$_indent();
    }

    public static void indent_$eq(SymbolTable $this, String value) {
        $this.scala$reflect$internal$Types$$_indent_$eq(value);
    }

    public static boolean explain(SymbolTable $this, String op, Function2 p, Types.Type tp1, Object arg2) {
        $this.inform(new StringBuilder().append((Object)$this.indent()).append(tp1).append((Object)" ").append((Object)op).append((Object)" ").append(arg2).append((Object)"?").toString());
        $this.indent_$eq(new StringBuilder().append((Object)$this.indent()).append((Object)"  ").toString());
        boolean result2 = BoxesRunTime.unboxToBoolean(p.apply(tp1, arg2));
        String string2 = $this.indent();
        Predef$ predef$ = Predef$.MODULE$;
        $this.indent_$eq(new StringOps(string2).stripSuffix("  "));
        $this.inform(new StringBuilder().append((Object)$this.indent()).append(BoxesRunTime.boxToBoolean(result2)).toString());
        return result2;
    }

    public static void explainTypes(SymbolTable $this, Types.Type found, Types.Type required) {
        MutableSettings.SettingValue settingValue = $this.settings().explaintypes();
        MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
        if (BoxesRunTime.unboxToBoolean(settingValue.value())) {
            $this.withTypesExplained(new Serializable($this, found, required){
                public static final long serialVersionUID = 0L;
                public final Types.Type found$1;
                public final Types.Type required$1;

                public final boolean apply() {
                    return this.found$1.$less$colon$less(this.required$1);
                }

                public boolean apply$mcZ$sp() {
                    return this.found$1.$less$colon$less(this.required$1);
                }
                {
                    this.found$1 = found$1;
                    this.required$1 = required$1;
                }
            });
        }
    }

    public static void explainTypes(SymbolTable $this, Function2 op, Types.Type found, Types.Type required) {
        MutableSettings.SettingValue settingValue = $this.settings().explaintypes();
        MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
        if (BoxesRunTime.unboxToBoolean(settingValue.value())) {
            $this.withTypesExplained(new Serializable($this, op, found, required){
                public static final long serialVersionUID = 0L;
                private final Function2 op$1;
                private final Types.Type found$2;
                private final Types.Type required$2;

                public final Object apply() {
                    return this.op$1.apply(this.found$2, this.required$2);
                }
                {
                    this.op$1 = op$1;
                    this.found$2 = found$2;
                    this.required$2 = required$2;
                }
            });
        }
    }

    /*
     * WARNING - void declaration
     */
    public static Object withTypesExplained(SymbolTable $this, Function0 op) {
        Object r;
        boolean s2 = $this.scala$reflect$internal$Types$$explainSwitch();
        try {
            $this.scala$reflect$internal$Types$$explainSwitch_$eq(true);
            r = op.apply();
            $this.scala$reflect$internal$Types$$explainSwitch_$eq(s2);
        }
        catch (Throwable throwable) {
            void var2_2;
            $this.scala$reflect$internal$Types$$explainSwitch_$eq((boolean)var2_2);
            throw throwable;
        }
        return r;
    }

    public static boolean isUnboundedGeneric(SymbolTable $this, Types.Type tp) {
        Types.TypeRef typeRef;
        boolean bl = tp instanceof Types.TypeRef ? (typeRef = (Types.TypeRef)tp).sym().isAbstractType() && !typeRef.$less$colon$less($this.definitions().AnyRefTpe()) : false;
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isBoundedGeneric(SymbolTable $this, Types.Type tp) {
        boolean bl = false;
        Types.TypeRef typeRef = null;
        if (tp instanceof Types.TypeRef) {
            bl = true;
            typeRef = (Types.TypeRef)tp;
            if (typeRef.sym().isAbstractType()) {
                return tp.$less$colon$less($this.definitions().AnyRefTpe());
            }
        }
        if (!bl) return false;
        if (!$this.definitions().isPrimitiveValueClass(typeRef.sym())) return true;
        return false;
    }

    public static List addSerializable(SymbolTable $this, Seq ps) {
        return ps.exists($this.typeIsSubTypeOfSerializable()) ? ps.toList() : ((TraversableOnce)ps.$colon$plus($this.definitions().SerializableTpe(), Seq$.MODULE$.canBuildFrom())).toList();
    }

    /*
     * Enabled aggressive block sorting
     */
    public static final Types.Type uncheckedBounds(SymbolTable $this, Types.Type tp) {
        Types.Type type;
        block3: {
            block2: {
                if (tp.typeArgs().isEmpty()) break block2;
                Symbols.Symbol symbol = $this.definitions().UncheckedBoundsClass();
                Symbols.NoSymbol noSymbol = $this.NoSymbol();
                if (symbol != null ? !symbol.equals(noSymbol) : noSymbol != null) break block3;
            }
            type = tp;
            return type;
        }
        type = (Types.Type)tp.withAnnotation($this.AnnotationInfo().marker($this.definitions().UncheckedBoundsClass().tpe()));
        return type;
    }

    public static Scopes.Scope nonTrivialMembers(SymbolTable $this, Symbols.Symbol clazz) {
        return clazz.info().members().filterNot((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final boolean apply(Symbols.Symbol sym) {
                return this.$outer.definitions().isUniversalMember(sym);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
    }

    public static Scopes.Scope importableMembers(SymbolTable $this, Types.Type pre) {
        return pre.members().filter((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final boolean apply(Symbols.Symbol sym) {
                return this.$outer.definitions().isImportable(sym);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Types.Type objToAny(SymbolTable $this, Types.Type tp) {
        Types.Type type;
        if (!$this.phase().erasedTypes()) {
            Symbols.Symbol symbol = tp.typeSymbol();
            Symbols.ClassSymbol classSymbol = $this.definitions().ObjectClass();
            if (!(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null)) {
                type = $this.definitions().AnyTpe();
                return type;
            }
        }
        type = tp;
        return type;
    }

    public static int typeDepth(SymbolTable $this, Types.Type tp) {
        int n;
        if (tp instanceof Types.TypeRef) {
            Types.TypeRef typeRef = (Types.TypeRef)tp;
            n = Depth$.MODULE$.max$extension($this.typeDepth(typeRef.pre()), Depth$.MODULE$.incr$extension1(Types$class.typeDepth($this, typeRef.args())));
        } else if (tp instanceof Types.RefinedType) {
            Types.RefinedType refinedType = (Types.RefinedType)tp;
            n = Depth$.MODULE$.max$extension(Types$class.typeDepth($this, refinedType.parents()), Depth$.MODULE$.incr$extension1(Types$class.symTypeDepth($this, refinedType.decls().toList())));
        } else if (tp instanceof Types.TypeBounds) {
            Types.TypeBounds typeBounds = (Types.TypeBounds)tp;
            n = Depth$.MODULE$.max$extension($this.typeDepth(typeBounds.lo()), $this.typeDepth(typeBounds.hi()));
        } else if (tp instanceof Types.MethodType) {
            Types.MethodType methodType = (Types.MethodType)tp;
            n = $this.typeDepth(methodType.resultType());
        } else if (tp instanceof Types.NullaryMethodType) {
            Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)tp;
            n = $this.typeDepth(nullaryMethodType.resultType());
        } else if (tp instanceof Types.PolyType) {
            Types.PolyType polyType = (Types.PolyType)tp;
            n = Depth$.MODULE$.max$extension($this.typeDepth(polyType.resultType()), Depth$.MODULE$.incr$extension1(Types$class.symTypeDepth($this, polyType.typeParams())));
        } else if (tp instanceof Types.ExistentialType) {
            Types.ExistentialType existentialType = (Types.ExistentialType)tp;
            n = Depth$.MODULE$.max$extension($this.typeDepth(existentialType.underlying()), Depth$.MODULE$.incr$extension1(Types$class.symTypeDepth($this, existentialType.quantified())));
        } else {
            Depth$ depth$ = Depth$.MODULE$;
            n = 1 < -3 ? depth$.AnyDepth() : 1;
        }
        return n;
    }

    public static int maxDepth(SymbolTable $this, List tps) {
        return Types$class.loop$3($this, tps, Depth$.MODULE$.Zero());
    }

    public static int maxbaseTypeSeqDepth(SymbolTable $this, List tps) {
        return Types$class.loop$4($this, tps, Depth$.MODULE$.Zero());
    }

    public static boolean scala$reflect$internal$Types$$typesContain(SymbolTable $this, List tps, Symbols.Symbol sym) {
        boolean bl;
        block2: {
            while (tps instanceof $colon$colon) {
                $colon$colon $colon$colon = ($colon$colon)tps;
                if (((Types.Type)$colon$colon.head()).contains(sym)) {
                    bl = true;
                    break block2;
                }
                tps = $colon$colon.tl$1();
            }
            bl = false;
        }
        return bl;
    }

    public static boolean scala$reflect$internal$Types$$areTrivialTypes(SymbolTable $this, List tps) {
        boolean bl;
        block2: {
            while (tps instanceof $colon$colon) {
                $colon$colon $colon$colon = ($colon$colon)tps;
                if (((Types.Type)$colon$colon.head()).isTrivial()) {
                    tps = $colon$colon.tl$1();
                    continue;
                }
                bl = false;
                break block2;
            }
            bl = true;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean isNew$1(SymbolTable $this, Symbols.Symbol clazz, Types.Type superclazz$1, List sbcs$1, ObjectRef bcs$1) {
        List p;
        if (superclazz$1.baseTypeIndex(clazz) >= 0) return false;
        for (p = (List)bcs$1.elem; p != sbcs$1; p = (List)p.tail()) {
            Object a = p.head();
            if (!(a == null ? clazz != null : !a.equals(clazz))) break;
        }
        if (p != sbcs$1) return false;
        return true;
    }

    private static final List addMixinBaseClasses$1(SymbolTable $this, List mbcs, Types.Type superclazz$1, List sbcs$1, ObjectRef bcs$1) {
        while (true) {
            block5: {
                List<Symbols.Symbol> list2;
                block4: {
                    block3: {
                        if (!mbcs.isEmpty()) break block3;
                        list2 = (List<Symbols.Symbol>)bcs$1.elem;
                        break block4;
                    }
                    if (!Types$class.isNew$1($this, (Symbols.Symbol)mbcs.head(), superclazz$1, sbcs$1, bcs$1)) break block5;
                    Symbols.Symbol symbol = (Symbols.Symbol)mbcs.head();
                    list2 = Types$class.addMixinBaseClasses$1($this, (List)mbcs.tail(), superclazz$1, sbcs$1, bcs$1).$colon$colon(symbol);
                }
                return list2;
            }
            mbcs = (List)mbcs.tail();
        }
    }

    private static final void define$1(SymbolTable $this, Types.CompoundType tpe$5) {
        Types$class.defineBaseClassesOfCompoundType($this, tpe$5, false);
    }

    private static final String what$1(SymbolTable $this, Types.CompoundType tpe$2) {
        return new StringBuilder().append((Object)Predef$any2stringadd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(tpe$2.typeSymbol()), " in ")).append((Object)tpe$2.typeSymbol().owner().fullNameString()).toString();
    }

    private static final Types.Type loop$1(SymbolTable $this, Types.Type tp, Symbols.Symbol container$2, Function1 f$2) {
        Types.Type type;
        if (tp instanceof Types.AnnotatedType) {
            Types.AnnotatedType annotatedType = (Types.AnnotatedType)tp;
            Types.Type x$83 = Types$class.loop$1($this, annotatedType.underlying(), container$2, f$2);
            List<AnnotationInfos.AnnotationInfo> x$84 = annotatedType.copy$default$1();
            type = annotatedType.copy(x$84, x$83);
        } else if (tp instanceof Types.ExistentialType) {
            Types.ExistentialType existentialType = (Types.ExistentialType)tp;
            Types.Type x$85 = Types$class.loop$1($this, existentialType.underlying(), container$2, f$2);
            List<Symbols.Symbol> x$86 = existentialType.copy$default$1();
            type = existentialType.copy(x$86, x$85);
        } else if (tp instanceof Types.PolyType) {
            Types.PolyType polyType = (Types.PolyType)tp;
            Types.Type x$87 = Types$class.loop$1($this, polyType.resultType(), container$2, f$2);
            List<Symbols.Symbol> x$88 = polyType.copy$default$1();
            type = polyType.copy(x$88, x$87);
        } else if (tp instanceof Types.NullaryMethodType) {
            Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)tp;
            type = nullaryMethodType.copy(Types$class.loop$1($this, nullaryMethodType.resultType(), container$2, f$2));
        } else {
            Serializable serializable = new Serializable($this, tp, f$2){
                public static final long serialVersionUID = 0L;
                public final Types.Type x1$3;
                public final Function1 f$2;

                public final Types.Type apply() {
                    return (Types.Type)this.f$2.apply(this.x1$3);
                }
                {
                    this.x1$3 = x1$3;
                    this.f$2 = f$2;
                }
            };
            Types.Type type2 = $this.elementTransform(container$2, tp, (Function1<Types.Type, Types.Type>)((Object)new Serializable($this, container$2, f$2){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;
                private final Symbols.Symbol container$2;
                private final Function1 f$2;

                public final Types.Type apply(Types.Type el) {
                    return this.$outer.appliedType(this.container$2, Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{(Types.Type)this.f$2.apply(el)}));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.container$2 = container$2;
                    this.f$2 = f$2;
                }
            }));
            type = type2 != type2.scala$reflect$internal$Types$Type$$$outer().NoType() ? type2 : (Types.Type)serializable.f$2.apply(serializable.x1$3);
        }
        return type;
    }

    private static final Types.Type loop$2(SymbolTable $this, Types.Type tp, Types.Type pre$1, Symbols.Symbol owner$2) {
        Types.Type type;
        if (tp.isTrivial()) {
            type = tp;
        } else if (tp.prefix().typeSymbol().isNonBottomSubClass(owner$2)) {
            Types.Type type2 = tp instanceof Types.ConstantType ? tp : tp.widen();
            Types.Type memType = type2.asSeenFrom(pre$1, tp.typeSymbol().owner());
            type = tp == type2 ? memType : memType.narrow();
        } else {
            type = Types$class.loop$2($this, tp.prefix(), pre$1, owner$2).memberType(tp.typeSymbol());
        }
        return type;
    }

    public static final boolean isConsistent$1(SymbolTable $this, Types.Type tp1, Types.Type tp2) {
        Tuple2<Types.Type, Types.Type> tuple2;
        block7: {
            boolean bl;
            block5: {
                block6: {
                    block4: {
                        tuple2 = new Tuple2<Types.Type, Types.Type>(tp1.dealias(), tp2.dealias());
                        if (!(tuple2._1() instanceof Types.TypeRef)) break block4;
                        Types.TypeRef typeRef = (Types.TypeRef)tuple2._1();
                        if (!(tuple2._2() instanceof Types.TypeRef)) break block4;
                        Types.TypeRef typeRef2 = (Types.TypeRef)tuple2._2();
                        Symbols.Symbol symbol = typeRef.sym();
                        Symbols.Symbol symbol2 = typeRef2.sym();
                        Serializable serializable = new Serializable($this, typeRef, typeRef2){
                            public static final long serialVersionUID = 0L;
                            public final Types.TypeRef x4$1;
                            public final Types.TypeRef x5$1;

                            public final Tuple2<Symbols.Symbol, Symbols.Symbol> apply() {
                                return new Tuple2<Symbols.Symbol, Symbols.Symbol>(this.x4$1.sym(), this.x5$1.sym());
                            }
                            {
                                this.x4$1 = x4$1;
                                this.x5$1 = x5$1;
                            }
                        };
                        boolean bl2 = !(symbol != null ? !symbol.equals(symbol2) : symbol2 != null);
                        Predef$ predef$ = Predef$.MODULE$;
                        if (!bl2) {
                            throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(new Tuple2<Symbols.Symbol, Symbols.Symbol>(serializable.x4$1.sym(), serializable.x5$1.sym())).toString());
                        }
                        bl = typeRef.pre().$eq$colon$eq(typeRef2.pre()) && $this.forall3(typeRef.args(), typeRef2.args(), typeRef.sym().typeParams(), new Serializable($this){
                            public static final long serialVersionUID = 0L;

                            public final boolean apply(Types.Type arg1, Types.Type arg2, Symbols.Symbol tparam) {
                                return Variance$.MODULE$.isInvariant$extension(tparam.variance()) ? arg1.$eq$colon$eq(arg2) : !(arg1 instanceof Types.TypeVar) || (Variance$.MODULE$.isContravariant$extension(tparam.variance()) ? arg1.$less$colon$less(arg2) : arg2.$less$colon$less(arg1));
                            }
                        });
                        break block5;
                    }
                    if (!(tuple2._1() instanceof Types.ExistentialType)) break block6;
                    Types.ExistentialType existentialType = (Types.ExistentialType)tuple2._1();
                    bl = existentialType.withTypeVars((Function1<Types.Type, Object>)((Object)new Serializable($this, tp2){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ SymbolTable $outer;
                        private final Types.Type tp2$1;

                        public final boolean apply(Types.Type x$58) {
                            return Types$class.isConsistent$1(this.$outer, x$58, this.tp2$1);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.tp2$1 = tp2$1;
                        }
                    }));
                    break block5;
                }
                if (!(tuple2._2() instanceof Types.ExistentialType)) break block7;
                Types.ExistentialType existentialType = (Types.ExistentialType)tuple2._2();
                bl = existentialType.withTypeVars((Function1<Types.Type, Object>)((Object)new Serializable($this, tp1){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SymbolTable $outer;
                    private final Types.Type tp1$1;

                    public final boolean apply(Types.Type x$59) {
                        return Types$class.isConsistent$1(this.$outer, this.tp1$1, x$59);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.tp1$1 = tp1$1;
                    }
                }));
            }
            return bl;
        }
        throw new MatchError(tuple2);
    }

    private static final boolean check$1(SymbolTable $this, Types.Type tp1, Types.Type tp2) {
        return tp1.typeSymbol().isClass() && tp1.typeSymbol().hasFlag(32) ? tp1.$less$colon$less(tp2) || $this.definitions().isNumericValueClass(tp1.typeSymbol()) && $this.definitions().isNumericValueClass(tp2.typeSymbol()) : tp1.baseClasses().forall((Function1<Symbols.Symbol, Object>)((Object)new Serializable($this, tp1, tp2){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final Types.Type tp1$2;
            private final Types.Type tp2$2;

            public final boolean apply(Symbols.Symbol bc) {
                return this.tp2$2.baseTypeIndex(bc) < 0 || Types$class.isConsistent$1(this.$outer, this.tp1$2.baseType(bc), this.tp2$2.baseType(bc));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tp1$2 = tp1$2;
                this.tp2$2 = tp2$2;
            }
        }));
    }

    private static final Types.Type createDummyClone$1(SymbolTable $this, Types.Type pre, Symbols.Symbol currentOwner$1) {
        Symbols.Symbol qual$2 = currentOwner$1.enclClass();
        Names.TermName x$89 = $this.nme().ANYname();
        Position x$90 = qual$2.newValue$default$2();
        long x$91 = qual$2.newValue$default$3();
        Symbols.TermSymbol dummy = (Symbols.TermSymbol)qual$2.newValue(x$89, x$90, x$91).setInfo(pre.widen());
        return $this.singleType($this.ThisType().apply(currentOwner$1.enclClass()), dummy);
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    private static final Types.Type maybeCreateDummyClone$1(SymbolTable $this, Types.Type pre, Symbols.Symbol sym, Symbols.Symbol currentOwner$1) {
        block5: {
            block6: {
                while (true) {
                    block7: {
                        block9: {
                            block8: {
                                if (!(pre instanceof Types.SingleType)) break block7;
                                var6_5 = (Types.SingleType)pre;
                                if (!var6_5.sym().isModule() || !var6_5.sym().isStatic()) break block8;
                                v0 /* !! */  = $this.NoType();
                                break block9;
                            }
                            if (!var6_5.sym().isModule()) ** GOTO lbl-1000
                            v1 = sym.owner();
                            var4_6 = var6_5.sym().moduleClass();
                            if (!(v1 != null ? v1.equals(var4_6) == false : var4_6 != null)) {
                                pre2 = Types$class.maybeCreateDummyClone$1($this, var6_5.pre(), var6_5.sym(), currentOwner$1);
                                v0 /* !! */  = pre2 == $this.NoType() ? pre2 : $this.singleType(pre2, var6_5.sym());
                            } else lbl-1000:
                            // 2 sources

                            {
                                v0 /* !! */  = Types$class.createDummyClone$1($this, pre, currentOwner$1);
                            }
                        }
                        var10_8 /* !! */  = v0 /* !! */ ;
                        break block5;
                    }
                    if (!(pre instanceof Types.ThisType)) break block6;
                    var8_4 = (Types.ThisType)pre;
                    if (!var8_4.sym().isModuleClass()) break;
                    pre = var8_4.sym().typeOfThis();
                }
                v2 = sym.owner();
                var7_9 = var8_4.sym();
                if (v2 != null ? v2.equals(var7_9) == false : var7_9 != null) ** GOTO lbl-1000
                if (sym.hasFlag(4)) ** GOTO lbl-1000
                v3 = sym.privateWithin();
                var9_10 = var8_4.sym();
                if (!(v3 != null ? v3.equals(var9_10) == false : var9_10 != null)) lbl-1000:
                // 2 sources

                {
                    v4 = $this.NoType();
                } else lbl-1000:
                // 2 sources

                {
                    v4 = Types$class.createDummyClone$1($this, pre, currentOwner$1);
                }
                var10_8 /* !! */  = v4;
                break block5;
            }
            var10_8 /* !! */  = $this.NoType();
        }
        return var10_8 /* !! */ ;
    }

    public static final boolean isSubArg$1(SymbolTable $this, Types.Type t1, Types.Type t2, int variance, int depth$1) {
        return !(!Variance$.MODULE$.isCovariant$extension(variance) && !$this.isSubType(t2, t1, depth$1) || !Variance$.MODULE$.isContravariant$extension(variance) && !$this.isSubType(t1, t2, depth$1));
    }

    public static final boolean directlySpecializedBy$1(SymbolTable $this, Symbols.Symbol member, Types.Type tp$5, Symbols.Symbol sym$3, int depth$2) {
        Symbols.Symbol symbol = member;
        return !(symbol == null ? sym$3 != null : !symbol.equals(sym$3)) || $this.specializesSym(tp$5.narrow(), member, sym$3.owner().thisType(), sym$3, depth$2);
    }

    private static final boolean specializedBy$1(SymbolTable $this, Symbols.Symbol member, Types.Type tp$5, Symbols.Symbol sym$3, int depth$2) {
        return member == $this.NoSymbol() ? false : (member.isOverloaded() ? member.alternatives().exists((Function1<Symbols.Symbol, Object>)((Object)new Serializable($this, tp$5, sym$3, depth$2){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final Types.Type tp$5;
            private final Symbols.Symbol sym$3;
            private final int depth$2;

            public final boolean apply(Symbols.Symbol member) {
                return Types$class.directlySpecializedBy$1(this.$outer, member, this.tp$5, this.sym$3, this.depth$2);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tp$5 = tp$5;
                this.sym$3 = sym$3;
                this.depth$2 = depth$2;
            }
        })) : Types$class.directlySpecializedBy$1($this, member, tp$5, sym$3, depth$2));
    }

    private static final boolean matchesQuantified$1(SymbolTable $this, List tparams1, List tparams2, Types.Type res1, Types.Type res2, boolean alwaysMatchSimple$1) {
        return $this.sameLength(tparams1, tparams2) && $this.matchesType(res1, res2.substSym(tparams2, tparams1), alwaysMatchSimple$1);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean lastTry$1(SymbolTable $this, Types.Type tp1$3, Types.Type tp2$3, boolean alwaysMatchSimple$1) {
        if (tp2$3 instanceof Types.ExistentialType) {
            Types.ExistentialType existentialType = (Types.ExistentialType)tp2$3;
            if (alwaysMatchSimple$1) {
                return $this.matchesType(tp1$3, existentialType.underlying(), true);
            }
        }
        if (tp2$3 instanceof Types.MethodType) {
            return false;
        }
        if (tp2$3 instanceof Types.PolyType) {
            return false;
        }
        if (alwaysMatchSimple$1) return true;
        if (!tp1$3.$eq$colon$eq(tp2$3)) return false;
        return true;
    }

    private static final int loop$3(SymbolTable $this, List tps, int acc) {
        while (tps instanceof $colon$colon) {
            $colon$colon $colon$colon = ($colon$colon)tps;
            acc = Depth$.MODULE$.max$extension(acc, $this.typeDepth((Types.Type)$colon$colon.head()));
            tps = $colon$colon.tl$1();
        }
        return acc;
    }

    private static final int loop$4(SymbolTable $this, List tps, int acc) {
        while (tps instanceof $colon$colon) {
            $colon$colon $colon$colon = ($colon$colon)tps;
            acc = Depth$.MODULE$.max$extension(acc, ((Types.Type)$colon$colon.head()).baseTypeSeqDepth());
            tps = $colon$colon.tl$1();
        }
        return acc;
    }

    public static void $init$(SymbolTable $this) {
        $this.scala$reflect$internal$Types$$explainSwitch_$eq(false);
        $this.scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$emptySymbolSet_$eq((Set)Set$.MODULE$.empty());
        $this.scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$traceTypeVars_$eq(package$.MODULE$.props().contains("scalac.debug.tvar"));
        $this.scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$breakCycles_$eq(BoxesRunTime.unboxToBoolean($this.settings().breakCycles().value()));
        $this.scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$propagateParameterBoundsToTypeVars_$eq(package$.MODULE$.props().contains("scalac.debug.prop-constraints"));
        $this.scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$sharperSkolems_$eq(package$.MODULE$.props().contains("scalac.experimental.sharper-skolems"));
        $this.scala$reflect$internal$Types$_setter_$enableTypeVarExperimentals_$eq(BoxesRunTime.unboxToBoolean($this.settings().Xexperimental().value()));
        $this.scala$reflect$internal$Types$$_skolemizationLevel_$eq(0);
        $this.scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$_intersectionWitness_$eq((WeakHashMap)$this.perRunCaches().recordCache((Clearable)WeakHashMap$.MODULE$.apply(Nil$.MODULE$)));
        $this.scala$reflect$internal$Types$$volatileRecursions_$eq(0);
        $this.scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$pendingVolatiles_$eq(new HashSet());
        $this.scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$initialUniquesCapacity_$eq(4096);
        $this.scala$reflect$internal$Types$$uniqueRunId_$eq(0);
        $this.scala$reflect$internal$Types$_setter_$missingAliasException_$eq(new Types.MissingAliasControl($this));
        $this.scala$reflect$internal$Types$$_basetypeRecursions_$eq(0);
        $this.scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$_pendingBaseTypes_$eq(new HashSet());
        $this.scala$reflect$internal$Types$$_indent_$eq("");
        $this.scala$reflect$internal$Types$_setter_$shorthands_$eq((Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"scala.collection.immutable.List", "scala.collection.immutable.Nil", "scala.collection.Seq", "scala.collection.Traversable", "scala.collection.Iterable", "scala.collection.mutable.StringBuilder", "scala.collection.IndexedSeq", "scala.collection.Iterator"})));
        $this.scala$reflect$internal$Types$_setter_$isTypeVar_$eq((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Types.Type tp) {
                return tp instanceof Types.TypeVar;
            }
        }));
        $this.scala$reflect$internal$Types$_setter_$typeContainsTypeVar_$eq((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final boolean apply(Types.Type tp) {
                return tp.exists(this.$outer.isTypeVar());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
        $this.scala$reflect$internal$Types$_setter_$typeIsNonClassType_$eq((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Types.Type tp) {
                return tp.typeSymbolDirect().isNonClassType();
            }
        }));
        $this.scala$reflect$internal$Types$_setter_$typeIsExistentiallyBound_$eq((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Types.Type tp) {
                return tp.typeSymbol().isExistentiallyBound();
            }
        }));
        $this.scala$reflect$internal$Types$_setter_$typeIsErroneous_$eq((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Types.Type tp) {
                return tp.isErroneous();
            }
        }));
        $this.scala$reflect$internal$Types$_setter_$symTypeIsError_$eq((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Symbols.Symbol sym) {
                return sym.tpe().isError();
            }
        }));
        $this.scala$reflect$internal$Types$_setter_$treeTpe_$eq((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Types.Type apply(Trees.Tree t) {
                return t.tpe();
            }
        }));
        $this.scala$reflect$internal$Types$_setter_$symTpe_$eq((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Types.Type apply(Symbols.Symbol sym) {
                return sym.tpe();
            }
        }));
        $this.scala$reflect$internal$Types$_setter_$symInfo_$eq((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Types.Type apply(Symbols.Symbol sym) {
                return sym.info();
            }
        }));
        $this.scala$reflect$internal$Types$_setter_$typeHasAnnotations_$eq((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Types.Type tp) {
                return tp.annotations() != Nil$.MODULE$;
            }
        }));
        $this.scala$reflect$internal$Types$_setter_$boundsContainType_$eq((Function2)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Types.TypeBounds bounds, Types.Type tp) {
                return bounds.containsType(tp);
            }
        }));
        $this.scala$reflect$internal$Types$_setter_$typeListIsEmpty_$eq((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(List<Types.Type> ts) {
                return ts.isEmpty();
            }
        }));
        $this.scala$reflect$internal$Types$_setter_$typeIsSubTypeOfSerializable_$eq((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final boolean apply(Types.Type tp) {
                return tp.$less$colon$less(this.$outer.definitions().SerializableTpe());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
        $this.scala$reflect$internal$Types$_setter_$typeIsNothing_$eq((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final boolean apply(Types.Type tp) {
                return tp.typeSymbolDirect() == this.$outer.definitions().NothingClass();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
        $this.scala$reflect$internal$Types$_setter_$typeIsAny_$eq((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final boolean apply(Types.Type tp) {
                return tp.typeSymbolDirect() == this.$outer.definitions().AnyClass();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
        $this.scala$reflect$internal$Types$_setter_$typeIsHigherKinded_$eq((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Types.Type tp) {
                return tp.isHigherKinded();
            }
        }));
        $this.scala$reflect$internal$Types$_setter_$AnnotatedTypeTag_$eq(ClassTag$.MODULE$.apply(Types.AnnotatedType.class));
        $this.scala$reflect$internal$Types$_setter_$BoundedWildcardTypeTag_$eq(ClassTag$.MODULE$.apply(Types.BoundedWildcardType.class));
        $this.scala$reflect$internal$Types$_setter_$ClassInfoTypeTag_$eq(ClassTag$.MODULE$.apply(Types.ClassInfoType.class));
        $this.scala$reflect$internal$Types$_setter_$CompoundTypeTag_$eq(ClassTag$.MODULE$.apply(Types.CompoundType.class));
        $this.scala$reflect$internal$Types$_setter_$ConstantTypeTag_$eq(ClassTag$.MODULE$.apply(Types.ConstantType.class));
        $this.scala$reflect$internal$Types$_setter_$ExistentialTypeTag_$eq(ClassTag$.MODULE$.apply(Types.ExistentialType.class));
        $this.scala$reflect$internal$Types$_setter_$MethodTypeTag_$eq(ClassTag$.MODULE$.apply(Types.MethodType.class));
        $this.scala$reflect$internal$Types$_setter_$NullaryMethodTypeTag_$eq(ClassTag$.MODULE$.apply(Types.NullaryMethodType.class));
        $this.scala$reflect$internal$Types$_setter_$PolyTypeTag_$eq(ClassTag$.MODULE$.apply(Types.PolyType.class));
        $this.scala$reflect$internal$Types$_setter_$RefinedTypeTag_$eq(ClassTag$.MODULE$.apply(Types.RefinedType.class));
        $this.scala$reflect$internal$Types$_setter_$SingletonTypeTag_$eq(ClassTag$.MODULE$.apply(Types.SingletonType.class));
        $this.scala$reflect$internal$Types$_setter_$SingleTypeTag_$eq(ClassTag$.MODULE$.apply(Types.SingleType.class));
        $this.scala$reflect$internal$Types$_setter_$SuperTypeTag_$eq(ClassTag$.MODULE$.apply(Types.SuperType.class));
        $this.scala$reflect$internal$Types$_setter_$ThisTypeTag_$eq(ClassTag$.MODULE$.apply(Types.ThisType.class));
        $this.scala$reflect$internal$Types$_setter_$TypeBoundsTag_$eq(ClassTag$.MODULE$.apply(Types.TypeBounds.class));
        $this.scala$reflect$internal$Types$_setter_$TypeRefTag_$eq(ClassTag$.MODULE$.apply(Types.TypeRef.class));
        $this.scala$reflect$internal$Types$_setter_$TypeTagg_$eq(ClassTag$.MODULE$.apply(Types.Type.class));
        Serializable serializable = new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final Object apply() {
                return this.$outer.scala$reflect$internal$Types$$uniques() == null ? BoxesRunTime.boxToInteger(0) : BoxesRunTime.boxToInteger(this.$outer.scala$reflect$internal$Types$$uniques().size());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
        WrappedArray<Object> wrappedArray = Predef$.MODULE$.wrapRefArray((Object[])new String[0]);
        Statistics$ statistics$ = Statistics$.MODULE$;
        new Statistics.View("#unique types", wrappedArray, (Function0<Object>)((Object)serializable));
    }
}

