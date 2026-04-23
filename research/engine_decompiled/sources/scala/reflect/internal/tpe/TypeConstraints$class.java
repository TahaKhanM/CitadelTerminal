/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.tpe;

import scala.Function0;
import scala.Predef$;
import scala.Serializable;
import scala.StringContext;
import scala.collection.AbstractIterable;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.reflect.api.Types;
import scala.reflect.internal.Definitions$DefinitionsClass$NothingClass$;
import scala.reflect.internal.Depth$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$NoType$;
import scala.reflect.internal.Variance$;
import scala.reflect.internal.tpe.TypeConstraints;
import scala.reflect.internal.tpe.TypeConstraints$;
import scala.reflect.internal.util.package$;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;

public abstract class TypeConstraints$class {
    public static TypeConstraints.UndoLog scala$reflect$internal$tpe$TypeConstraints$$_undoLog(SymbolTable $this) {
        return new TypeConstraints.UndoLog($this);
    }

    public static TypeConstraints.UndoLog undoLog(SymbolTable $this) {
        return $this.scala$reflect$internal$tpe$TypeConstraints$$_undoLog();
    }

    public static Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericLoBound(SymbolTable $this) {
        return $this.definitions().IntTpe();
    }

    public static Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericHiBound(SymbolTable $this) {
        return $this.intersectionType((List<Types.Type>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{$this.definitions().ByteTpe(), $this.definitions().CharTpe()})), $this.definitions().ScalaPackageClass());
    }

    public static boolean solve(SymbolTable $this, List tvars, List tparams2, List variances, boolean upper, int depth) {
        $this.foreach3(tvars, tparams2, variances, new Serializable($this, tvars, tparams2, variances, upper, depth){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final List tvars$1;
            private final List tparams$1;
            private final List variances$1;
            private final boolean upper$1;
            private final int depth$1;

            public final void apply(Types.TypeVar tvar, Symbols.Symbol tparam, int variance) {
                TypeConstraints$class.solveOne$1(this.$outer, tvar, tparam, variance, this.tvars$1, this.tparams$1, this.variances$1, this.upper$1, this.depth$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tvars$1 = tvars$1;
                this.tparams$1 = tparams$1;
                this.variances$1 = variances$1;
                this.upper$1 = upper$1;
                this.depth$1 = depth$1;
            }
        });
        return tvars.forall(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean apply(Types.TypeVar tv) {
                if (tv.instWithinBounds()) return true;
                TypeConstraints$class.logBounds$1(this.$outer, tv);
                if (!package$.MODULE$.andFalse(BoxedUnit.UNIT)) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static final void solveOne$1(SymbolTable $this, Types.TypeVar tvar, Symbols.Symbol tparam, int variance, List tvars$1, List tparams$1, List variances$1, boolean upper$1, int depth$1) {
        Types.Type type = tvar.constr().inst();
        Types$NoType$ types$NoType$ = $this.NoType();
        if (!(type != null ? !type.equals(types$NoType$) : types$NoType$ != null)) {
            boolean up = Variance$.MODULE$.isContravariant$extension(variance) ? !upper$1 : upper$1;
            tvar.constr().inst_$eq(null);
            Types.Type bound = up ? tparam.info().bounds().hi() : tparam.info().bounds().lo();
            BooleanRef cyclic = BooleanRef.create(bound.contains(tparam));
            $this.foreach3(tvars$1, tparams$1, variances$1, new Serializable($this, tparam, up, bound, cyclic, tvars$1, tparams$1, variances$1, upper$1, depth$1){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;
                private final Symbols.Symbol tparam$1;
                private final boolean up$1;
                private final Types.Type bound$1;
                private final BooleanRef cyclic$1;
                private final List tvars$1;
                private final List tparams$1;
                private final List variances$1;
                private final boolean upper$1;
                private final int depth$1;

                public final void apply(Types.TypeVar tvar2, Symbols.Symbol tparam2, int variance2) {
                    boolean ok;
                    Symbols.Symbol symbol = tparam2;
                    Symbols.Symbol symbol2 = this.tparam$1;
                    boolean bl = (symbol == null ? symbol2 != null : !symbol.equals(symbol2)) && (this.bound$1.contains(tparam2) || this.up$1 && tparam2.info().bounds().lo().$eq$colon$eq(this.tparam$1.tpeHK()) || !this.up$1 && tparam2.info().bounds().hi().$eq$colon$eq(this.tparam$1.tpeHK())) ? true : (ok = false);
                    if (ok) {
                        if (tvar2.constr().inst() == null) {
                            this.cyclic$1.elem = true;
                        }
                        TypeConstraints$class.solveOne$1(this.$outer, tvar2, tparam2, variance2, this.tvars$1, this.tparams$1, this.variances$1, this.upper$1, this.depth$1);
                    }
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.tparam$1 = tparam$1;
                    this.up$1 = up$1;
                    this.bound$1 = bound$1;
                    this.cyclic$1 = cyclic$1;
                    this.tvars$1 = tvars$1;
                    this.tparams$1 = tparams$1;
                    this.variances$1 = variances$1;
                    this.upper$1 = upper$1;
                    this.depth$1 = depth$1;
                }
            });
            if (!cyclic.elem) {
                if (up) {
                    Symbols.Symbol symbol = bound.typeSymbol();
                    Symbols.ClassSymbol classSymbol = $this.definitions().AnyClass();
                    if (symbol == null ? classSymbol != null : !symbol.equals(classSymbol)) {
                        $this.debuglog((Function0<String>)((Object)new Serializable($this, tvar, bound, tvars$1, tparams$1){
                            public static final long serialVersionUID = 0L;
                            private final Types.TypeVar tvar$1;
                            private final Types.Type bound$1;
                            private final List tvars$1;
                            private final List tparams$1;

                            public final String apply() {
                                return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " addHiBound ", ".instantiateTypeParams(", ", ", ")"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.tvar$1, this.bound$1, this.tparams$1, this.tvars$1}));
                            }
                            {
                                this.tvar$1 = tvar$1;
                                this.bound$1 = bound$1;
                                this.tvars$1 = tvars$1;
                                this.tparams$1 = tparams$1;
                            }
                        }));
                        tvar.addHiBound(bound.instantiateTypeParams(tparams$1, tvars$1), tvar.addHiBound$default$2());
                    }
                    Serializable serializable = new Serializable($this, tvar, tparam, tvars$1, tparams$1){
                        public static final long serialVersionUID = 0L;
                        public final /* synthetic */ SymbolTable $outer;
                        public final Types.TypeVar tvar$1;
                        public final Symbols.Symbol tparam$1;
                        public final List tvars$1;
                        public final List tparams$1;

                        public final void apply(Symbols.Symbol tparam2) {
                            block1: {
                                Types.Type type = tparam2.info().bounds().lo().dealias();
                                if (!(type instanceof Types.TypeRef)) break block1;
                                Types.TypeRef typeRef = (Types.TypeRef)type;
                                Symbols.Symbol symbol = this.tparam$1;
                                Symbols.Symbol symbol2 = typeRef.sym();
                                if (!(symbol != null ? !symbol.equals(symbol2) : symbol2 != null)) {
                                    this.$outer.debuglog((Function0<String>)((Object)new Serializable(this, tparam2){
                                        public static final long serialVersionUID = 0L;
                                        private final /* synthetic */ TypeConstraints$.anonfun.solveOne.1.3 $outer;
                                        private final Symbols.Symbol tparam2$1;

                                        public final String apply() {
                                            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " addHiBound ", ".tpeHK.instantiateTypeParams(", ", ", ")"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.$outer.tvar$1, this.tparam2$1, this.$outer.tparams$1, this.$outer.tvars$1}));
                                        }
                                        {
                                            if ($outer == null) {
                                                throw null;
                                            }
                                            this.$outer = $outer;
                                            this.tparam2$1 = tparam2$1;
                                        }
                                    }));
                                    this.tvar$1.addHiBound(tparam2.tpeHK().instantiateTypeParams(this.tparams$1, this.tvars$1), this.tvar$1.addHiBound$default$2());
                                }
                            }
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.tvar$1 = tvar$1;
                            this.tparam$1 = tparam$1;
                            this.tvars$1 = tvars$1;
                            this.tparams$1 = tparams$1;
                        }
                    };
                    List list2 = tparams$1;
                    while (!((AbstractIterable)list2).isEmpty()) {
                        Symbols.Symbol symbol2 = (Symbols.Symbol)((AbstractIterable)list2).head();
                        Types.Type x11 = symbol2.info().bounds().lo().dealias();
                        if (x11 instanceof Types.TypeRef) {
                            Types.TypeRef x21 = (Types.TypeRef)x11;
                            Symbols.Symbol symbol3 = tparam;
                            Symbols.Symbol symbol4 = x21.sym();
                            if (!(symbol3 != null ? !symbol3.equals(symbol4) : symbol4 != null)) {
                                $this.debuglog((Function0<String>)((Object)new /* invalid duplicate definition of identical inner class */));
                                tvar.addHiBound(symbol2.tpeHK().instantiateTypeParams(tparams$1, tvars$1), tvar.addHiBound$default$2());
                            }
                        }
                        list2 = (List)list2.tail();
                    }
                } else {
                    Symbols.Symbol symbol = bound.typeSymbol();
                    Definitions$DefinitionsClass$NothingClass$ definitions$DefinitionsClass$NothingClass$ = $this.definitions().NothingClass();
                    if (symbol == null ? definitions$DefinitionsClass$NothingClass$ != null : !symbol.equals(definitions$DefinitionsClass$NothingClass$)) {
                        Symbols.Symbol symbol5 = bound.typeSymbol();
                        if (symbol5 == null ? tparam != null : !symbol5.equals(tparam)) {
                            $this.debuglog((Function0<String>)((Object)new Serializable($this, tvar, bound, tvars$1, tparams$1){
                                public static final long serialVersionUID = 0L;
                                private final Types.TypeVar tvar$1;
                                private final Types.Type bound$1;
                                private final List tvars$1;
                                private final List tparams$1;

                                public final String apply() {
                                    return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " addLoBound ", ".instantiateTypeParams(", ", ", ")"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.tvar$1, this.bound$1, this.tparams$1, this.tvars$1}));
                                }
                                {
                                    this.tvar$1 = tvar$1;
                                    this.bound$1 = bound$1;
                                    this.tvars$1 = tvars$1;
                                    this.tparams$1 = tparams$1;
                                }
                            }));
                            tvar.addLoBound(bound.instantiateTypeParams(tparams$1, tvars$1), tvar.addLoBound$default$2());
                        }
                    }
                    Serializable serializable = new Serializable($this, tvar, tparam, tvars$1, tparams$1){
                        public static final long serialVersionUID = 0L;
                        public final /* synthetic */ SymbolTable $outer;
                        public final Types.TypeVar tvar$1;
                        public final Symbols.Symbol tparam$1;
                        public final List tvars$1;
                        public final List tparams$1;

                        public final void apply(Symbols.Symbol tparam2) {
                            block1: {
                                Types.Type type = tparam2.info().bounds().hi().dealias();
                                if (!(type instanceof Types.TypeRef)) break block1;
                                Types.TypeRef typeRef = (Types.TypeRef)type;
                                Symbols.Symbol symbol = this.tparam$1;
                                Symbols.Symbol symbol2 = typeRef.sym();
                                if (!(symbol != null ? !symbol.equals(symbol2) : symbol2 != null)) {
                                    this.$outer.debuglog((Function0<String>)((Object)new Serializable(this, tparam2){
                                        public static final long serialVersionUID = 0L;
                                        private final /* synthetic */ TypeConstraints$.anonfun.solveOne.1.5 $outer;
                                        private final Symbols.Symbol tparam2$2;

                                        public final String apply() {
                                            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " addLoBound ", ".tpeHK.instantiateTypeParams(", ", ", ")"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.$outer.tvar$1, this.tparam2$2, this.$outer.tparams$1, this.$outer.tvars$1}));
                                        }
                                        {
                                            if ($outer == null) {
                                                throw null;
                                            }
                                            this.$outer = $outer;
                                            this.tparam2$2 = tparam2$2;
                                        }
                                    }));
                                    this.tvar$1.addLoBound(tparam2.tpeHK().instantiateTypeParams(this.tparams$1, this.tvars$1), this.tvar$1.addLoBound$default$2());
                                }
                            }
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.tvar$1 = tvar$1;
                            this.tparam$1 = tparam$1;
                            this.tvars$1 = tvars$1;
                            this.tparams$1 = tparams$1;
                        }
                    };
                    List list3 = tparams$1;
                    while (!((AbstractIterable)list3).isEmpty()) {
                        Symbols.Symbol symbol6 = (Symbols.Symbol)((AbstractIterable)list3).head();
                        Types.Type x12 = symbol6.info().bounds().hi().dealias();
                        if (x12 instanceof Types.TypeRef) {
                            Types.TypeRef x22 = (Types.TypeRef)x12;
                            Symbols.Symbol symbol7 = tparam;
                            Symbols.Symbol symbol8 = x22.sym();
                            if (!(symbol7 != null ? !symbol7.equals(symbol8) : symbol8 != null)) {
                                $this.debuglog((Function0<String>)((Object)new /* invalid duplicate definition of identical inner class */));
                                tvar.addLoBound(symbol6.tpeHK().instantiateTypeParams(tparams$1, tvars$1), tvar.addLoBound$default$2());
                            }
                        }
                        list3 = (List)list3.tail();
                    }
                }
            }
            tvar.constr().inst_$eq($this.NoType());
            Types.TypeApi newInst = up ? (Depth$.MODULE$.isAnyDepth$extension(depth$1) ? $this.glb((List)tvar.constr().hiBounds()) : $this.glb(tvar.constr().hiBounds(), depth$1)) : (Depth$.MODULE$.isAnyDepth$extension(depth$1) ? $this.lub((List)tvar.constr().loBounds()) : $this.lub(tvar.constr().loBounds(), depth$1));
            $this.debuglog((Function0<String>)((Object)new Serializable($this, tvar, (Types.Type)newInst){
                public static final long serialVersionUID = 0L;
                private final Types.TypeVar tvar$1;
                private final Types.Type newInst$1;

                public final String apply() {
                    return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " setInst ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.tvar$1, this.newInst$1}));
                }
                {
                    this.tvar$1 = tvar$1;
                    this.newInst$1 = newInst$1;
                }
            }));
            tvar.setInst((Types.Type)newInst);
        }
    }

    public static final void logBounds$1(SymbolTable $this, Types.TypeVar tv) {
        $this.log((Function0<Object>)((Object)new Serializable($this, tv){
            public static final long serialVersionUID = 0L;
            private final Types.TypeVar tv$1;

            public final String apply() {
                String what = this.tv$1.instValid() ? new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"does not conform to bounds: ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.tv$1.constr()})) : "is invalid";
                return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Inferred type for ", " (", ") ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.tv$1.originString(), this.tv$1.inst(), what}));
            }
            {
                this.tv$1 = tv$1;
            }
        }));
    }

    public static void $init$(SymbolTable $this) {
    }
}

