/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Predef$;
import scala.Serializable;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.generic.Clearable;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashMap$;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WrappedArray;
import scala.reflect.ClassTag$;
import scala.reflect.api.TypeTags;
import scala.reflect.internal.Names;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$WildcardType$;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;
import scala.runtime.BoxesRunTime;

public abstract class Symbols$class {
    public static int nextId(SymbolTable $this) {
        $this.ids_$eq($this.ids() + 1);
        return $this.ids();
    }

    public static Map recursionTable(SymbolTable $this) {
        return $this.scala$reflect$internal$Symbols$$_recursionTable();
    }

    public static void recursionTable_$eq(SymbolTable $this, Map value) {
        $this.scala$reflect$internal$Symbols$$_recursionTable_$eq(value);
    }

    public static int nextExistentialId(SymbolTable $this) {
        $this.scala$reflect$internal$Symbols$$existentialIds_$eq($this.scala$reflect$internal$Symbols$$existentialIds() + 1);
        return $this.scala$reflect$internal$Symbols$$existentialIds();
    }

    public static Names.TypeName freshExistentialName(SymbolTable $this, String suffix) {
        return $this.newTypeName(new StringBuilder().append((Object)"_").append(BoxesRunTime.boxToInteger($this.nextExistentialId())).append((Object)suffix).toString());
    }

    public static Symbols.ModuleSymbol connectModuleToClass(SymbolTable $this, Symbols.ModuleSymbol m, Symbols.ClassSymbol moduleClass) {
        moduleClass.sourceModule_$eq(m);
        m.setModuleClass(moduleClass);
        return m;
    }

    public static Symbols.FreeTermSymbol newFreeTermSymbol(SymbolTable $this, Names.TermName name, Function0 value, long flags, String origin) {
        return (Symbols.FreeTermSymbol)new Symbols.FreeTermSymbol($this, name, value, origin).initFlags(flags);
    }

    public static long newFreeTermSymbol$default$3(SymbolTable $this) {
        return 0L;
    }

    public static Symbols.FreeTypeSymbol newFreeTypeSymbol(SymbolTable $this, Names.TypeName name, long flags, String origin) {
        return (Symbols.FreeTypeSymbol)new Symbols.FreeTypeSymbol($this, name, origin).initFlags(flags);
    }

    public static long newFreeTypeSymbol$default$2(SymbolTable $this) {
        return 0L;
    }

    public static void saveOriginalOwner(SymbolTable $this, Symbols.Symbol sym) {
        Symbols.Symbol symbol = sym.owner();
        Symbols.NoSymbol noSymbol = $this.NoSymbol();
        if ((symbol == null ? noSymbol != null : !symbol.equals(noSymbol)) && !$this.scala$reflect$internal$Symbols$$originalOwnerMap().contains(sym)) {
            $this.defineOriginalOwner(sym, sym.rawowner());
        }
    }

    public static void defineOriginalOwner(SymbolTable $this, Symbols.Symbol sym, Symbols.Symbol owner2) {
        $this.scala$reflect$internal$Symbols$$originalOwnerMap().update(sym, owner2);
    }

    public static Symbols.TypeSymbol symbolOf(SymbolTable $this, TypeTags.WeakTypeTag evidence$1) {
        return (Symbols.TypeSymbol)((Types.Type)$this.weakTypeOf(evidence$1)).typeSymbolDirect().asType();
    }

    public static Symbols.Symbol newStubSymbol(SymbolTable $this, Symbols.Symbol owner2, Names.Name name, String missingMessage, boolean isPackage) {
        Symbols.Symbol symbol;
        if (name instanceof Names.TypeName) {
            Names.TypeName typeName = (Names.TypeName)name;
            symbol = isPackage ? new Symbols.StubPackageClassSymbol($this, owner2, typeName, missingMessage) : new Symbols.StubClassSymbol($this, owner2, typeName, missingMessage);
        } else {
            symbol = new Symbols.StubTermSymbol($this, owner2, name.toTermName(), missingMessage);
        }
        return symbol;
    }

    public static boolean newStubSymbol$default$4(SymbolTable $this) {
        return false;
    }

    public static Symbols.NoSymbol makeNoSymbol(SymbolTable $this) {
        return new Symbols.NoSymbol($this);
    }

    public static Symbols.NoSymbol NoSymbol(SymbolTable $this) {
        return $this.makeNoSymbol();
    }

    public static List deriveSymbols(SymbolTable $this, List syms, Function1 symFn) {
        List syms1 = $this.mapList(syms, symFn);
        return $this.mapList(syms1, new Serializable($this, syms1, syms){
            public static final long serialVersionUID = 0L;
            private final List syms1$2;
            private final List syms$1;

            public final Symbols.Symbol apply(Symbols.Symbol x$45) {
                return x$45.substInfo(this.syms$1, this.syms1$2);
            }
            {
                this.syms1$2 = syms1$2;
                this.syms$1 = syms$1;
            }
        });
    }

    public static List deriveSymbols2(SymbolTable $this, List syms, List as, Function2 symFn) {
        List syms1 = $this.map2(syms, as, symFn);
        return $this.mapList(syms1, new Serializable($this, syms1, syms){
            public static final long serialVersionUID = 0L;
            private final List syms1$3;
            private final List syms$2;

            public final Symbols.Symbol apply(Symbols.Symbol x$46) {
                return x$46.substInfo(this.syms$2, this.syms1$3);
            }
            {
                this.syms1$3 = syms1$3;
                this.syms$2 = syms$2;
            }
        });
    }

    public static Types.Type deriveType(SymbolTable $this, List syms, Function1 symFn, Types.Type tpe) {
        List<Symbols.Symbol> syms1 = $this.deriveSymbols(syms, symFn);
        return tpe.substSym(syms, syms1);
    }

    public static Types.Type deriveType2(SymbolTable $this, List syms, List as, Function2 symFn, Types.Type tpe) {
        List<Symbols.Symbol> syms1 = $this.deriveSymbols2(syms, as, symFn);
        return tpe.substSym(syms, syms1);
    }

    public static Types.Type deriveTypeWithWildcards(SymbolTable $this, List syms, Types.Type tpe) {
        return syms.isEmpty() ? tpe : tpe.instantiateTypeParams(syms, syms.map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final Types$WildcardType$ apply(Symbols.Symbol x$47) {
                return this.$outer.WildcardType();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, List$.MODULE$.canBuildFrom()));
    }

    public static List cloneSymbols(SymbolTable $this, List syms) {
        return $this.deriveSymbols(syms, (Function1<Symbols.Symbol, Symbols.Symbol>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Symbols.Symbol apply(Symbols.Symbol x$48) {
                return x$48.cloneSymbol();
            }
        }));
    }

    public static List cloneSymbolsAtOwner(SymbolTable $this, List syms, Symbols.Symbol owner2) {
        return $this.deriveSymbols(syms, (Function1<Symbols.Symbol, Symbols.Symbol>)((Object)new Serializable($this, owner2){
            public static final long serialVersionUID = 0L;
            private final Symbols.Symbol owner$1;

            public final Symbols.Symbol apply(Symbols.Symbol x$49) {
                return x$49.cloneSymbol(this.owner$1);
            }
            {
                this.owner$1 = owner$1;
            }
        }));
    }

    public static List cloneSymbolsAndModify(SymbolTable $this, List syms, Function1 infoFn) {
        return $this.mapList($this.cloneSymbols(syms), new Serializable($this, infoFn){
            public static final long serialVersionUID = 0L;
            private final Function1 infoFn$1;

            public final Symbols.Symbol apply(Symbols.Symbol x$50) {
                return x$50.modifyInfo(this.infoFn$1);
            }
            {
                this.infoFn$1 = infoFn$1;
            }
        });
    }

    public static List cloneSymbolsAtOwnerAndModify(SymbolTable $this, List syms, Symbols.Symbol owner2, Function1 infoFn) {
        return $this.mapList($this.cloneSymbolsAtOwner(syms, owner2), new Serializable($this, infoFn){
            public static final long serialVersionUID = 0L;
            private final Function1 infoFn$2;

            public final Symbols.Symbol apply(Symbols.Symbol x$51) {
                return x$51.modifyInfo(this.infoFn$2);
            }
            {
                this.infoFn$2 = infoFn$2;
            }
        });
    }

    public static Object createFromClonedSymbols(SymbolTable $this, List syms, Types.Type tpe, Function2 creator) {
        List<Symbols.Symbol> syms1 = $this.cloneSymbols(syms);
        return creator.apply(syms1, tpe.substSym(syms, syms1));
    }

    public static Object createFromClonedSymbolsAtOwner(SymbolTable $this, List syms, Symbols.Symbol owner2, Types.Type tpe, Function2 creator) {
        List<Symbols.Symbol> syms1 = $this.cloneSymbolsAtOwner(syms, owner2);
        return creator.apply(syms1, tpe.substSym(syms, syms1));
    }

    public static List mapParamss(SymbolTable $this, Symbols.Symbol sym, Function1 f) {
        return $this.mmap(sym.info().paramss(), f);
    }

    public static List existingSymbols(SymbolTable $this, List syms) {
        return (List)syms.filter((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final boolean apply(Symbols.Symbol s2) {
                return s2 != null && s2 != this.$outer.NoSymbol();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
    }

    public static final Symbols.Symbol closestEnclMethod(SymbolTable $this, Symbols.Symbol from2) {
        while (true) {
            block5: {
                Symbols.Symbol symbol;
                block4: {
                    block3: {
                        if (!from2.isSourceMethod()) break block3;
                        symbol = from2;
                        break block4;
                    }
                    if (!from2.isClass()) break block5;
                    symbol = $this.NoSymbol();
                }
                return symbol;
            }
            from2 = from2.owner();
        }
    }

    public static final boolean allSymbolsHaveOwner(SymbolTable $this, List syms, Symbols.Symbol owner2) {
        boolean bl;
        block2: {
            while (syms instanceof $colon$colon) {
                $colon$colon $colon$colon = ($colon$colon)syms;
                Symbols.Symbol symbol = ((Symbols.Symbol)$colon$colon.head()).owner();
                if (!(symbol != null ? !symbol.equals(owner2) : owner2 != null)) {
                    syms = $colon$colon.tl$1();
                    continue;
                }
                bl = false;
                break block2;
            }
            bl = true;
        }
        return bl;
    }

    public static Symbols.SymbolOps FlagOps(SymbolTable $this, long mask) {
        return new Symbols.SymbolOps($this, true, mask);
    }

    private static Seq relevantSymbols(SymbolTable $this, Seq syms) {
        return syms.flatMap(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final List<Symbols.Symbol> apply(Symbols.Symbol sym) {
                return List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.Symbol[]{sym, sym.moduleClass(), sym.sourceModule()}));
            }
        }, Seq$.MODULE$.canBuildFrom());
    }

    public static void markFlagsCompleted(SymbolTable $this, Seq syms, long mask) {
        Symbols$class.relevantSymbols($this, syms).foreach(new Serializable($this, mask){
            public static final long serialVersionUID = 0L;
            private final long mask$1;

            public final Symbols.Symbol apply(Symbols.Symbol x$54) {
                return x$54.markFlagsCompleted(this.mask$1);
            }
            {
                this.mask$1 = mask$1;
            }
        });
    }

    public static void markAllCompleted(SymbolTable $this, Seq syms) {
        Symbols$class.relevantSymbols($this, syms).foreach(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Symbols.Symbol apply(Symbols.Symbol x$55) {
                return x$55.markAllCompleted();
            }
        });
    }

    public static void $init$(SymbolTable $this) {
        $this.ids_$eq(0);
        $this.scala$reflect$internal$Symbols$$_recursionTable_$eq((Map<Symbols.Symbol, Object>)Map$.MODULE$.empty());
        $this.scala$reflect$internal$Symbols$$existentialIds_$eq(0);
        $this.scala$reflect$internal$Symbols$_setter_$scala$reflect$internal$Symbols$$originalOwnerMap_$eq((HashMap)$this.perRunCaches().recordCache((Clearable)HashMap$.MODULE$.apply(Nil$.MODULE$)));
        $this.scala$reflect$internal$Symbols$_setter_$SymbolTag_$eq(ClassTag$.MODULE$.apply(Symbols.Symbol.class));
        $this.scala$reflect$internal$Symbols$_setter_$TermSymbolTag_$eq(ClassTag$.MODULE$.apply(Symbols.TermSymbol.class));
        $this.scala$reflect$internal$Symbols$_setter_$ModuleSymbolTag_$eq(ClassTag$.MODULE$.apply(Symbols.ModuleSymbol.class));
        $this.scala$reflect$internal$Symbols$_setter_$MethodSymbolTag_$eq(ClassTag$.MODULE$.apply(Symbols.MethodSymbol.class));
        $this.scala$reflect$internal$Symbols$_setter_$TypeSymbolTag_$eq(ClassTag$.MODULE$.apply(Symbols.TypeSymbol.class));
        $this.scala$reflect$internal$Symbols$_setter_$ClassSymbolTag_$eq(ClassTag$.MODULE$.apply(Symbols.ClassSymbol.class));
        $this.scala$reflect$internal$Symbols$_setter_$FreeTermSymbolTag_$eq(ClassTag$.MODULE$.apply(Symbols.FreeTermSymbol.class));
        $this.scala$reflect$internal$Symbols$_setter_$FreeTypeSymbolTag_$eq(ClassTag$.MODULE$.apply(Symbols.FreeTypeSymbol.class));
        $this.scala$reflect$internal$Symbols$_setter_$symbolIsPossibleInRefinement_$eq((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Symbols.Symbol sym) {
                return sym.isPossibleInRefinement();
            }
        }));
        Serializable serializable = new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SymbolTable $outer;

            public final int apply() {
                return this.$outer.ids();
            }

            public int apply$mcI$sp() {
                return this.$outer.ids();
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
        new Statistics.View("#symbols", wrappedArray, (Function0<Object>)((Object)serializable));
        $this.scala$reflect$internal$Symbols$_setter_$AllOps_$eq(new Symbols.SymbolOps($this, false, 0L));
    }
}

