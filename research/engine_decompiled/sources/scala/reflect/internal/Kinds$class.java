/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Function1;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Serializable;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenTraversable;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.internal.Definitions$DefinitionsClass$NothingClass$;
import scala.reflect.internal.Kinds;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$NoType$;
import scala.reflect.internal.Types$WildcardType$;
import scala.reflect.internal.Variance$;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;

public abstract class Kinds$class {
    public static boolean kindsConform(SymbolTable $this, List tparams2, List targs, Types.Type pre, Symbols.Symbol owner2) {
        return $this.checkKindBounds0(tparams2, targs, pre, owner2, false).isEmpty();
    }

    public static boolean scala$reflect$internal$Kinds$$variancesMatch(SymbolTable $this, Symbols.Symbol sym1, Symbols.Symbol sym2) {
        return Variance$.MODULE$.isInvariant$extension(sym2.variance()) || sym1.variance() == sym2.variance();
    }

    public static List checkKindBounds0(SymbolTable $this, List tparams2, List targs, Types.Type pre, Symbols.Symbol owner2, boolean explainErrors) {
        NonLocalReturnControl nonLocalReturnControl;
        block3: {
            List<Tuple3<Types.Type, Symbols.Symbol, Kinds.KindErrors>> list2;
            Object object = new Object();
            try {
                MutableSettings.SettingValue settingValue = $this.settings().debug();
                MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
                if (BoxesRunTime.unboxToBoolean(settingValue.value()) && (tparams2.nonEmpty() || targs.nonEmpty())) {
                    $this.log((Function0<Object>)((Object)new Serializable($this, tparams2, targs, pre, owner2, explainErrors){
                        public static final long serialVersionUID = 0L;
                        private final List tparams$1;
                        private final List targs$1;
                        private final Types.Type pre$2;
                        private final Symbols.Symbol owner$1;
                        private final boolean explainErrors$1;

                        public final String apply() {
                            return new StringBuilder().append((Object)"checkKindBounds0(").append(this.tparams$1).append((Object)", ").append(this.targs$1).append((Object)", ").append(this.pre$2).append((Object)", ").append(this.owner$1).append((Object)", ").append(BoxesRunTime.boxToBoolean(this.explainErrors$1)).append((Object)")").toString();
                        }
                        {
                            this.tparams$1 = tparams$1;
                            this.targs$1 = targs$1;
                            this.pre$2 = pre$2;
                            this.owner$1 = owner$1;
                            this.explainErrors$1 = explainErrors$1;
                        }
                    }));
                }
                list2 = $this.flatMap2(tparams2, targs, new Serializable($this, object, tparams2, targs, pre, owner2, explainErrors){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SymbolTable $outer;
                    private final Object nonLocalReturnKey2$1;
                    private final List tparams$1;
                    private final List targs$1;
                    private final Types.Type pre$2;
                    private final Symbols.Symbol owner$1;
                    private final boolean explainErrors$1;

                    /*
                     * Enabled force condition propagation
                     * Lifted jumps to return sites
                     */
                    public final List<Tuple3<Types.Type, Symbols.Symbol, Kinds.KindErrors>> apply(Symbols.Symbol tparam, Types.Type targ) {
                        GenTraversable<Nothing$> genTraversable;
                        Types.Type type = targ;
                        Types$WildcardType$ types$WildcardType$ = this.$outer.WildcardType();
                        if (!(type != null ? !type.equals(types$WildcardType$) : types$WildcardType$ != null)) {
                            genTraversable = Nil$.MODULE$;
                            return genTraversable;
                        } else {
                            targ.typeSymbolDirect().info();
                            List<Symbols.Symbol> tparamsHO = targ.typeParams();
                            if (targ.isHigherKinded() || tparam.typeParams().nonEmpty()) {
                                Kinds.KindErrors kindErrors = Kinds$class.checkKindBoundsHK$1(this.$outer, tparamsHO, targ.typeSymbolDirect(), tparam, tparam.owner(), tparam.typeParams(), tparamsHO, this.tparams$1, this.targs$1, this.pre$2, this.owner$1, this.explainErrors$1);
                                if (kindErrors.isEmpty()) {
                                    genTraversable = Nil$.MODULE$;
                                    return genTraversable;
                                } else {
                                    if (!this.explainErrors$1) throw new NonLocalReturnControl<GenTraversable>(this.nonLocalReturnKey2$1, List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Tuple3[]{new Tuple3<Types$NoType$, Symbols.NoSymbol, Kinds.KindErrors>(this.$outer.NoType(), this.$outer.NoSymbol(), this.$outer.NoKindErrors())})));
                                    genTraversable = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Tuple3[]{new Tuple3<Types.Type, Symbols.Symbol, Kinds.KindErrors>(targ, tparam, kindErrors)}));
                                }
                                return genTraversable;
                            } else {
                                genTraversable = Nil$.MODULE$;
                            }
                        }
                        return genTraversable;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.nonLocalReturnKey2$1 = nonLocalReturnKey2$1;
                        this.tparams$1 = tparams$1;
                        this.targs$1 = targs$1;
                        this.pre$2 = pre$2;
                        this.owner$1 = owner$1;
                        this.explainErrors$1 = explainErrors$1;
                    }
                });
            }
            catch (NonLocalReturnControl nonLocalReturnControl2) {
                nonLocalReturnControl = nonLocalReturnControl2;
                if (nonLocalReturnControl2.key() != object) break block3;
                list2 = (List<Tuple3<Types.Type, Symbols.Symbol, Kinds.KindErrors>>)nonLocalReturnControl.value();
            }
            return list2;
        }
        throw nonLocalReturnControl;
    }

    public static final Types.Type transform$1(SymbolTable $this, Types.Type tp, Symbols.Symbol clazz, Types.Type pre$2) {
        return tp.asSeenFrom(pre$2, clazz);
    }

    public static final Types.Type bindHKParams$1(SymbolTable $this, Types.Type tp, List underHKParams$1, List withHKArgs$1) {
        return tp.substSym(underHKParams$1, withHKArgs$1);
    }

    public static final void kindCheck$1(SymbolTable $this, boolean cond, Function1 f, ObjectRef kindErrors$1) {
        if (!cond) {
            kindErrors$1.elem = (Kinds.KindErrors)f.apply((Kinds.KindErrors)kindErrors$1.elem);
        }
    }

    public static final Kinds.KindErrors checkKindBoundsHK$1(SymbolTable $this, List hkargs, Symbols.Symbol arg, Symbols.Symbol param2, Symbols.Symbol paramowner, List underHKParams, List withHKArgs, List tparams$1, List targs$1, Types.Type pre$2, Symbols.Symbol owner$1, boolean explainErrors$1) {
        NonLocalReturnControl nonLocalReturnControl;
        block5: {
            Kinds.KindErrors kindErrors;
            block4: {
                Object object = new Object();
                try {
                    ObjectRef<Kinds.KindErrors> kindErrors2;
                    block9: {
                        block7: {
                            java.io.Serializable serializable;
                            block8: {
                                block6: {
                                    kindErrors2 = ObjectRef.create($this.NoKindErrors());
                                    List<Symbols.Symbol> hkparams = param2.typeParams();
                                    MutableSettings.SettingValue settingValue = $this.settings().debug();
                                    MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
                                    if (BoxesRunTime.unboxToBoolean(settingValue.value())) {
                                        $this.log((Function0<Object>)((Object)new Serializable($this, param2, paramowner, hkparams){
                                            public static final long serialVersionUID = 0L;
                                            private final Symbols.Symbol param$1;
                                            private final Symbols.Symbol paramowner$1;
                                            private final List hkparams$1;

                                            public final String apply() {
                                                return new StringBuilder().append((Object)"checkKindBoundsHK expected: ").append(this.param$1).append((Object)" with params ").append(this.hkparams$1).append((Object)" by definition in ").append(this.paramowner$1).toString();
                                            }
                                            {
                                                this.param$1 = param$1;
                                                this.paramowner$1 = paramowner$1;
                                                this.hkparams$1 = hkparams$1;
                                            }
                                        }));
                                        $this.log((Function0<Object>)((Object)new Serializable($this, hkargs, arg, owner$1){
                                            public static final long serialVersionUID = 0L;
                                            private final List hkargs$1;
                                            private final Symbols.Symbol arg$1;
                                            private final Symbols.Symbol owner$1;

                                            public final String apply() {
                                                return new StringBuilder().append((Object)"checkKindBoundsHK supplied: ").append(this.arg$1).append((Object)" with params ").append(this.hkargs$1).append((Object)" from ").append(this.owner$1).toString();
                                            }
                                            {
                                                this.hkargs$1 = hkargs$1;
                                                this.arg$1 = arg$1;
                                                this.owner$1 = owner$1;
                                            }
                                        }));
                                        $this.log((Function0<Object>)((Object)new Serializable($this, underHKParams, withHKArgs){
                                            public static final long serialVersionUID = 0L;
                                            private final List underHKParams$1;
                                            private final List withHKArgs$1;

                                            public final String apply() {
                                                return new StringBuilder().append((Object)"checkKindBoundsHK under params: ").append(this.underHKParams$1).append((Object)" with args ").append(this.withHKArgs$1).toString();
                                            }
                                            {
                                                this.underHKParams$1 = underHKParams$1;
                                                this.withHKArgs$1 = withHKArgs$1;
                                            }
                                        }));
                                    }
                                    if (!$this.sameLength(hkargs, hkparams)) break block6;
                                    $this.foreach2(hkargs, hkparams, new Serializable($this, paramowner, underHKParams, withHKArgs, kindErrors2, object, tparams$1, targs$1, pre$2, owner$1, explainErrors$1){
                                        public static final long serialVersionUID = 0L;
                                        private final /* synthetic */ SymbolTable $outer;
                                        private final Symbols.Symbol paramowner$1;
                                        private final List underHKParams$1;
                                        private final List withHKArgs$1;
                                        private final ObjectRef kindErrors$1;
                                        private final Object nonLocalReturnKey1$1;
                                        private final List tparams$1;
                                        private final List targs$1;
                                        private final Types.Type pre$2;
                                        private final Symbols.Symbol owner$1;
                                        private final boolean explainErrors$1;

                                        public final void apply(Symbols.Symbol hkarg, Symbols.Symbol hkparam) {
                                            if (hkparam.typeParams().isEmpty() && hkarg.typeParams().isEmpty()) {
                                                Kinds$class.kindCheck$1(this.$outer, Kinds$class.scala$reflect$internal$Kinds$$variancesMatch(this.$outer, hkarg, hkparam), (Function1)((Object)new Serializable(this, hkarg, hkparam){
                                                    public static final long serialVersionUID = 0L;
                                                    private final Symbols.Symbol hkarg$1;
                                                    private final Symbols.Symbol hkparam$1;

                                                    public final Kinds.KindErrors apply(Kinds.KindErrors x$7) {
                                                        Symbols.Symbol symbol = this.hkparam$1;
                                                        Symbols.Symbol symbol2 = Predef$.MODULE$.ArrowAssoc(this.hkarg$1);
                                                        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
                                                        return x$7.varianceError(new Tuple2<Symbols.Symbol, Symbols.Symbol>(symbol2, symbol));
                                                    }
                                                    {
                                                        this.hkarg$1 = hkarg$1;
                                                        this.hkparam$1 = hkparam$1;
                                                    }
                                                }), this.kindErrors$1);
                                                Types.Type declaredBounds = Kinds$class.transform$1(this.$outer, hkparam.info().instantiateTypeParams(this.tparams$1, this.targs$1).bounds(), this.paramowner$1, this.pre$2);
                                                Types.Type declaredBoundsInst = Kinds$class.transform$1(this.$outer, Kinds$class.bindHKParams$1(this.$outer, declaredBounds, this.underHKParams$1, this.withHKArgs$1), this.owner$1, this.pre$2);
                                                Types.Type argumentBounds = Kinds$class.transform$1(this.$outer, hkarg.info().bounds(), this.owner$1, this.pre$2);
                                                Kinds$class.kindCheck$1(this.$outer, declaredBoundsInst.$less$colon$less(argumentBounds), (Function1)((Object)new Serializable(this, hkarg, hkparam){
                                                    public static final long serialVersionUID = 0L;
                                                    private final Symbols.Symbol hkarg$1;
                                                    private final Symbols.Symbol hkparam$1;

                                                    public final Kinds.KindErrors apply(Kinds.KindErrors x$8) {
                                                        Symbols.Symbol symbol = this.hkparam$1;
                                                        Symbols.Symbol symbol2 = Predef$.MODULE$.ArrowAssoc(this.hkarg$1);
                                                        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
                                                        return x$8.strictnessError(new Tuple2<Symbols.Symbol, Symbols.Symbol>(symbol2, symbol));
                                                    }
                                                    {
                                                        this.hkarg$1 = hkarg$1;
                                                        this.hkparam$1 = hkparam$1;
                                                    }
                                                }), this.kindErrors$1);
                                                this.$outer.debuglog((Function0<String>)((Object)new Serializable(this, declaredBounds, declaredBoundsInst, argumentBounds, hkarg, hkparam){
                                                    public static final long serialVersionUID = 0L;
                                                    private final Types.Type declaredBounds$1;
                                                    private final Types.Type declaredBoundsInst$1;
                                                    private final Types.Type argumentBounds$1;
                                                    private final Symbols.Symbol hkarg$1;
                                                    private final Symbols.Symbol hkparam$1;

                                                    public final String apply() {
                                                        return new StringBuilder().append((Object)"checkKindBoundsHK base case: ").append(this.hkparam$1).append((Object)" declared bounds: ").append(this.declaredBounds$1).append((Object)" after instantiating earlier hkparams: ").append(this.declaredBoundsInst$1).append((Object)"\n").append((Object)"checkKindBoundsHK base case: ").append(this.hkarg$1).append((Object)" has bounds: ").append(this.argumentBounds$1).toString();
                                                    }
                                                    {
                                                        this.declaredBounds$1 = declaredBounds$1;
                                                        this.declaredBoundsInst$1 = declaredBoundsInst$1;
                                                        this.argumentBounds$1 = argumentBounds$1;
                                                        this.hkarg$1 = hkarg$1;
                                                        this.hkparam$1 = hkparam$1;
                                                    }
                                                }));
                                            } else {
                                                hkarg.initialize();
                                                this.$outer.debuglog((Function0<String>)((Object)new Serializable(this, hkarg, hkparam){
                                                    public static final long serialVersionUID = 0L;
                                                    private final Symbols.Symbol hkarg$1;
                                                    private final Symbols.Symbol hkparam$1;

                                                    public final String apply() {
                                                        return new StringBuilder().append((Object)"checkKindBoundsHK recursing to compare params of ").append(this.hkparam$1).append((Object)" with ").append(this.hkarg$1).toString();
                                                    }
                                                    {
                                                        this.hkarg$1 = hkarg$1;
                                                        this.hkparam$1 = hkparam$1;
                                                    }
                                                }));
                                                this.kindErrors$1.elem = ((Kinds.KindErrors)this.kindErrors$1.elem).$plus$plus(Kinds$class.checkKindBoundsHK$1(this.$outer, hkarg.typeParams(), hkarg, hkparam, this.paramowner$1, this.underHKParams$1.$plus$plus(hkparam.typeParams(), List$.MODULE$.canBuildFrom()), this.withHKArgs$1.$plus$plus(hkarg.typeParams(), List$.MODULE$.canBuildFrom()), this.tparams$1, this.targs$1, this.pre$2, this.owner$1, this.explainErrors$1));
                                            }
                                            if (this.explainErrors$1 || ((Kinds.KindErrors)this.kindErrors$1.elem).isEmpty()) {
                                                return;
                                            }
                                            throw new NonLocalReturnControl<Kinds.KindErrors>(this.nonLocalReturnKey1$1, (Kinds.KindErrors)this.kindErrors$1.elem);
                                        }
                                        {
                                            if ($outer == null) {
                                                throw null;
                                            }
                                            this.$outer = $outer;
                                            this.paramowner$1 = paramowner$1;
                                            this.underHKParams$1 = underHKParams$1;
                                            this.withHKArgs$1 = withHKArgs$1;
                                            this.kindErrors$1 = kindErrors$1;
                                            this.nonLocalReturnKey1$1 = nonLocalReturnKey1$1;
                                            this.tparams$1 = tparams$1;
                                            this.targs$1 = targs$1;
                                            this.pre$2 = pre$2;
                                            this.owner$1 = owner$1;
                                            this.explainErrors$1 = explainErrors$1;
                                        }
                                    });
                                    serializable = BoxedUnit.UNIT;
                                    break block7;
                                }
                                Symbols.Symbol symbol = arg;
                                Symbols.ClassSymbol classSymbol = $this.definitions().AnyClass();
                                if (!(symbol == null ? classSymbol != null : !symbol.equals(classSymbol))) break block8;
                                Symbols.Symbol symbol2 = arg;
                                Definitions$DefinitionsClass$NothingClass$ definitions$DefinitionsClass$NothingClass$ = $this.definitions().NothingClass();
                                if (symbol2 != null ? !symbol2.equals(definitions$DefinitionsClass$NothingClass$) : definitions$DefinitionsClass$NothingClass$ != null) break block9;
                            }
                            serializable = $this.NoKindErrors();
                        }
                        kindErrors = explainErrors$1 ? (Kinds.KindErrors)kindErrors2.elem : $this.NoKindErrors();
                        break block4;
                    }
                    Symbols.Symbol symbol = Predef$.MODULE$.ArrowAssoc(arg);
                    Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
                    return ((Kinds.KindErrors)kindErrors2.elem).arityError(new Tuple2<Symbols.Symbol, Symbols.Symbol>(symbol, param2));
                }
                catch (NonLocalReturnControl nonLocalReturnControl2) {
                    nonLocalReturnControl = nonLocalReturnControl2;
                    if (nonLocalReturnControl2.key() != object) break block5;
                    kindErrors = (Kinds.KindErrors)nonLocalReturnControl.value();
                }
            }
            return kindErrors;
        }
        throw nonLocalReturnControl;
    }

    public static void $init$(SymbolTable $this) {
        $this.scala$reflect$internal$Kinds$_setter_$NoKindErrors_$eq(new Kinds.KindErrors($this, Nil$.MODULE$, Nil$.MODULE$, Nil$.MODULE$));
    }
}

