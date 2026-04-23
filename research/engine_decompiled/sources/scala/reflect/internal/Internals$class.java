/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.reflect.ClassTag;
import scala.reflect.Manifest;
import scala.reflect.api.Internals;
import scala.reflect.api.Internals$CompatApi$class;
import scala.reflect.api.Internals$InternalApi$class;
import scala.reflect.api.Scopes;
import scala.reflect.api.Symbols;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Internals;
import scala.reflect.internal.Internals$;
import scala.reflect.internal.Internals$SymbolTableInternal$class;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.Position;
import scala.reflect.macros.Attachments;
import scala.reflect.macros.Universe;
import scala.reflect.macros.Universe$MacroCompatApi$class;
import scala.reflect.macros.Universe$MacroInternalApi$class;

public abstract class Internals$class {
    public static Universe.MacroInternalApi internal(SymbolTable $this) {
        return new Internals.SymbolTableInternal($this){
            private final /* synthetic */ SymbolTable $outer;
            private final Internals.ReificationSupportApi reificationSupport;
            private final Universe.TreeGen gen;
            private final Universe.MacroInternalApi.MacroDecoratorApi decorators;
            private volatile byte bitmap$0;

            private Internals.ReificationSupportApi reificationSupport$lzycompute() {
                Internals$.anon.1 var1_1 = this;
                synchronized (var1_1) {
                    if ((byte)(this.bitmap$0 & 1) == 0) {
                        this.reificationSupport = Internals$SymbolTableInternal$class.reificationSupport(this);
                        this.bitmap$0 = (byte)(this.bitmap$0 | 1);
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.reificationSupport;
                }
            }

            public Internals.ReificationSupportApi reificationSupport() {
                return (byte)(this.bitmap$0 & 1) == 0 ? this.reificationSupport$lzycompute() : this.reificationSupport;
            }

            private Universe.TreeGen gen$lzycompute() {
                Internals$.anon.1 var1_1 = this;
                synchronized (var1_1) {
                    if ((byte)(this.bitmap$0 & 2) == 0) {
                        this.gen = Internals$SymbolTableInternal$class.gen(this);
                        this.bitmap$0 = (byte)(this.bitmap$0 | 2);
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.gen;
                }
            }

            public Universe.TreeGen gen() {
                return (byte)(this.bitmap$0 & 2) == 0 ? this.gen$lzycompute() : this.gen;
            }

            private Universe.MacroInternalApi.MacroDecoratorApi decorators$lzycompute() {
                Internals$.anon.1 var1_1 = this;
                synchronized (var1_1) {
                    if ((byte)(this.bitmap$0 & 4) == 0) {
                        this.decorators = Internals$SymbolTableInternal$class.decorators(this);
                        this.bitmap$0 = (byte)(this.bitmap$0 | 4);
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.decorators;
                }
            }

            public Universe.MacroInternalApi.MacroDecoratorApi decorators() {
                return (byte)(this.bitmap$0 & 4) == 0 ? this.decorators$lzycompute() : this.decorators;
            }

            public Internals.Importer createImporter(Universe from0) {
                return Internals$SymbolTableInternal$class.createImporter(this, from0);
            }

            public Scopes.Scope newScopeWith(Seq<Symbols.Symbol> elems) {
                return Internals$SymbolTableInternal$class.newScopeWith(this, elems);
            }

            public Scopes.Scope enter(Scopes.Scope scope, Symbols.Symbol sym) {
                return Internals$SymbolTableInternal$class.enter(this, scope, sym);
            }

            public Scopes.Scope unlink(Scopes.Scope scope, Symbols.Symbol sym) {
                return Internals$SymbolTableInternal$class.unlink(this, scope, sym);
            }

            public List<Symbols.FreeTermSymbol> freeTerms(Trees.Tree tree) {
                return Internals$SymbolTableInternal$class.freeTerms(this, tree);
            }

            public List<Symbols.FreeTypeSymbol> freeTypes(Trees.Tree tree) {
                return Internals$SymbolTableInternal$class.freeTypes(this, tree);
            }

            public Trees.Tree substituteSymbols(Trees.Tree tree, List<Symbols.Symbol> from2, List<Symbols.Symbol> to2) {
                return Internals$SymbolTableInternal$class.substituteSymbols(this, tree, from2, to2);
            }

            public Trees.Tree substituteTypes(Trees.Tree tree, List<Symbols.Symbol> from2, List<Types.Type> to2) {
                return Internals$SymbolTableInternal$class.substituteTypes(this, tree, from2, to2);
            }

            public Trees.Tree substituteThis(Trees.Tree tree, Symbols.Symbol clazz, Trees.Tree to2) {
                return Internals$SymbolTableInternal$class.substituteThis(this, tree, clazz, to2);
            }

            public Attachments attachments(Trees.Tree tree) {
                return Internals$SymbolTableInternal$class.attachments((Internals.SymbolTableInternal)this, tree);
            }

            public <T> Trees.Tree updateAttachment(Trees.Tree tree, T attachment, ClassTag<T> evidence$1) {
                return Internals$SymbolTableInternal$class.updateAttachment((Internals.SymbolTableInternal)this, tree, attachment, evidence$1);
            }

            public <T> Trees.Tree removeAttachment(Trees.Tree tree, ClassTag<T> evidence$2) {
                return Internals$SymbolTableInternal$class.removeAttachment((Internals.SymbolTableInternal)this, tree, evidence$2);
            }

            public Trees.Tree setPos(Trees.Tree tree, Position newpos) {
                return Internals$SymbolTableInternal$class.setPos(this, tree, newpos);
            }

            public Trees.Tree setType(Trees.Tree tree, Types.Type tp) {
                return Internals$SymbolTableInternal$class.setType(this, tree, tp);
            }

            public Trees.Tree defineType(Trees.Tree tree, Types.Type tp) {
                return Internals$SymbolTableInternal$class.defineType(this, tree, tp);
            }

            public Trees.Tree setSymbol(Trees.Tree tree, Symbols.Symbol sym) {
                return Internals$SymbolTableInternal$class.setSymbol(this, tree, sym);
            }

            public Trees.TypeTree setOriginal(Trees.TypeTree tt, Trees.Tree tree) {
                return Internals$SymbolTableInternal$class.setOriginal(this, tt, tree);
            }

            public void captureVariable(Symbols.Symbol vble) {
                Internals$SymbolTableInternal$class.captureVariable(this, vble);
            }

            public Trees.Tree referenceCapturedVariable(Symbols.Symbol vble) {
                return Internals$SymbolTableInternal$class.referenceCapturedVariable(this, vble);
            }

            public Types.Type capturedVariableType(Symbols.Symbol vble) {
                return Internals$SymbolTableInternal$class.capturedVariableType(this, vble);
            }

            public Trees.ClassDef classDef(Symbols.Symbol sym, Trees.Template impl) {
                return Internals$SymbolTableInternal$class.classDef(this, sym, impl);
            }

            public Trees.ModuleDef moduleDef(Symbols.Symbol sym, Trees.Template impl) {
                return Internals$SymbolTableInternal$class.moduleDef(this, sym, impl);
            }

            public Trees.ValDef valDef(Symbols.Symbol sym, Trees.Tree rhs) {
                return Internals$SymbolTableInternal$class.valDef(this, sym, rhs);
            }

            public Trees.ValDef valDef(Symbols.Symbol sym) {
                return Internals$SymbolTableInternal$class.valDef(this, sym);
            }

            public Trees.DefDef defDef(Symbols.Symbol sym, Trees.Modifiers mods, List<List<Trees.ValDef>> vparamss, Trees.Tree rhs) {
                return Internals$SymbolTableInternal$class.defDef(this, sym, mods, vparamss, rhs);
            }

            public Trees.DefDef defDef(Symbols.Symbol sym, List<List<Trees.ValDef>> vparamss, Trees.Tree rhs) {
                return Internals$SymbolTableInternal$class.defDef((Internals.SymbolTableInternal)this, sym, vparamss, rhs);
            }

            public Trees.DefDef defDef(Symbols.Symbol sym, Trees.Modifiers mods, Trees.Tree rhs) {
                return Internals$SymbolTableInternal$class.defDef((Internals.SymbolTableInternal)this, sym, mods, rhs);
            }

            public Trees.DefDef defDef(Symbols.Symbol sym, Trees.Tree rhs) {
                return Internals$SymbolTableInternal$class.defDef((Internals.SymbolTableInternal)this, sym, rhs);
            }

            public Trees.DefDef defDef(Symbols.Symbol sym, Function1<List<List<Symbols.Symbol>>, Trees.Tree> rhs) {
                return Internals$SymbolTableInternal$class.defDef((Internals.SymbolTableInternal)this, sym, rhs);
            }

            public Trees.TypeDef typeDef(Symbols.Symbol sym, Trees.Tree rhs) {
                return Internals$SymbolTableInternal$class.typeDef(this, sym, rhs);
            }

            public Trees.TypeDef typeDef(Symbols.Symbol sym) {
                return Internals$SymbolTableInternal$class.typeDef(this, sym);
            }

            public Trees.LabelDef labelDef(Symbols.Symbol sym, List<Symbols.Symbol> params2, Trees.Tree rhs) {
                return Internals$SymbolTableInternal$class.labelDef(this, sym, params2, rhs);
            }

            public Trees.Tree changeOwner(Trees.Tree tree, Symbols.Symbol prev, Symbols.Symbol next2) {
                return Internals$SymbolTableInternal$class.changeOwner(this, tree, prev, next2);
            }

            public boolean isFreeTerm(Symbols.Symbol symbol) {
                return Internals$SymbolTableInternal$class.isFreeTerm(this, symbol);
            }

            public Symbols.FreeTermSymbol asFreeTerm(Symbols.Symbol symbol) {
                return Internals$SymbolTableInternal$class.asFreeTerm(this, symbol);
            }

            public boolean isFreeType(Symbols.Symbol symbol) {
                return Internals$SymbolTableInternal$class.isFreeType(this, symbol);
            }

            public Symbols.FreeTypeSymbol asFreeType(Symbols.Symbol symbol) {
                return Internals$SymbolTableInternal$class.asFreeType(this, symbol);
            }

            public Symbols.TermSymbol newTermSymbol(Symbols.Symbol symbol, Names.TermName name, Position pos, long flags) {
                return Internals$SymbolTableInternal$class.newTermSymbol(this, symbol, name, pos, flags);
            }

            public Tuple2<Symbols.ModuleSymbol, Symbols.ClassSymbol> newModuleAndClassSymbol(Symbols.Symbol symbol, Names.Name name, Position pos, long flags) {
                return Internals$SymbolTableInternal$class.newModuleAndClassSymbol(this, symbol, name, pos, flags);
            }

            public Symbols.MethodSymbol newMethodSymbol(Symbols.Symbol symbol, Names.TermName name, Position pos, long flags) {
                return Internals$SymbolTableInternal$class.newMethodSymbol(this, symbol, name, pos, flags);
            }

            public Symbols.TypeSymbol newTypeSymbol(Symbols.Symbol symbol, Names.TypeName name, Position pos, long flags) {
                return Internals$SymbolTableInternal$class.newTypeSymbol(this, symbol, name, pos, flags);
            }

            public Symbols.ClassSymbol newClassSymbol(Symbols.Symbol symbol, Names.TypeName name, Position pos, long flags) {
                return Internals$SymbolTableInternal$class.newClassSymbol(this, symbol, name, pos, flags);
            }

            public Symbols.FreeTermSymbol newFreeTerm(String name, Function0<Object> value, long flags, String origin) {
                return Internals$SymbolTableInternal$class.newFreeTerm(this, name, value, flags, origin);
            }

            public Symbols.FreeTypeSymbol newFreeType(String name, long flags, String origin) {
                return Internals$SymbolTableInternal$class.newFreeType(this, name, flags, origin);
            }

            public boolean isErroneous(Symbols.Symbol symbol) {
                return Internals$SymbolTableInternal$class.isErroneous(this, symbol);
            }

            public boolean isSkolem(Symbols.Symbol symbol) {
                return Internals$SymbolTableInternal$class.isSkolem(this, symbol);
            }

            public Symbols.Symbol deSkolemize(Symbols.Symbol symbol) {
                return Internals$SymbolTableInternal$class.deSkolemize(this, symbol);
            }

            public Symbols.Symbol initialize(Symbols.Symbol symbol) {
                return Internals$SymbolTableInternal$class.initialize(this, symbol);
            }

            public Symbols.Symbol fullyInitialize(Symbols.Symbol symbol) {
                return Internals$SymbolTableInternal$class.fullyInitialize((Internals.SymbolTableInternal)this, symbol);
            }

            public Types.Type fullyInitialize(Types.Type tp) {
                return Internals$SymbolTableInternal$class.fullyInitialize((Internals.SymbolTableInternal)this, tp);
            }

            public Scopes.Scope fullyInitialize(Scopes.Scope scope) {
                return Internals$SymbolTableInternal$class.fullyInitialize((Internals.SymbolTableInternal)this, scope);
            }

            public long flags(Symbols.Symbol symbol) {
                return Internals$SymbolTableInternal$class.flags(this, symbol);
            }

            public Attachments attachments(Symbols.Symbol symbol) {
                return Internals$SymbolTableInternal$class.attachments((Internals.SymbolTableInternal)this, symbol);
            }

            public <T> Symbols.Symbol updateAttachment(Symbols.Symbol symbol, T attachment, ClassTag<T> evidence$3) {
                return Internals$SymbolTableInternal$class.updateAttachment((Internals.SymbolTableInternal)this, symbol, attachment, evidence$3);
            }

            public <T> Symbols.Symbol removeAttachment(Symbols.Symbol symbol, ClassTag<T> evidence$4) {
                return Internals$SymbolTableInternal$class.removeAttachment((Internals.SymbolTableInternal)this, symbol, evidence$4);
            }

            public Symbols.Symbol setOwner(Symbols.Symbol symbol, Symbols.Symbol newowner) {
                return Internals$SymbolTableInternal$class.setOwner(this, symbol, newowner);
            }

            public Symbols.Symbol setInfo(Symbols.Symbol symbol, Types.Type tpe) {
                return Internals$SymbolTableInternal$class.setInfo(this, symbol, tpe);
            }

            public Symbols.Symbol setAnnotations(Symbols.Symbol symbol, Seq<AnnotationInfos.AnnotationInfo> annots) {
                return Internals$SymbolTableInternal$class.setAnnotations(this, symbol, annots);
            }

            public Symbols.Symbol setName(Symbols.Symbol symbol, Names.Name name) {
                return Internals$SymbolTableInternal$class.setName(this, symbol, name);
            }

            public Symbols.Symbol setPrivateWithin(Symbols.Symbol symbol, Symbols.Symbol sym) {
                return Internals$SymbolTableInternal$class.setPrivateWithin(this, symbol, sym);
            }

            public Symbols.Symbol setFlag(Symbols.Symbol symbol, long flags) {
                return Internals$SymbolTableInternal$class.setFlag(this, symbol, flags);
            }

            public Symbols.Symbol resetFlag(Symbols.Symbol symbol, long flags) {
                return Internals$SymbolTableInternal$class.resetFlag(this, symbol, flags);
            }

            public Types.Type thisType(Symbols.Symbol sym) {
                return Internals$SymbolTableInternal$class.thisType(this, sym);
            }

            public Types.Type singleType(Types.Type pre, Symbols.Symbol sym) {
                return Internals$SymbolTableInternal$class.singleType(this, pre, sym);
            }

            public Types.Type superType(Types.Type thistpe, Types.Type supertpe) {
                return Internals$SymbolTableInternal$class.superType(this, thistpe, supertpe);
            }

            public Types.ConstantType constantType(Constants.Constant value) {
                return Internals$SymbolTableInternal$class.constantType(this, value);
            }

            public Types.Type typeRef(Types.Type pre, Symbols.Symbol sym, List<Types.Type> args) {
                return Internals$SymbolTableInternal$class.typeRef(this, pre, sym, args);
            }

            public Types.RefinedType refinedType(List<Types.Type> parents2, Scopes.Scope decls) {
                return Internals$SymbolTableInternal$class.refinedType((Internals.SymbolTableInternal)this, parents2, decls);
            }

            public Types.RefinedType refinedType(List<Types.Type> parents2, Scopes.Scope decls, Symbols.Symbol clazz) {
                return Internals$SymbolTableInternal$class.refinedType((Internals.SymbolTableInternal)this, parents2, decls, clazz);
            }

            public Types.Type refinedType(List<Types.Type> parents2, Symbols.Symbol owner2) {
                return Internals$SymbolTableInternal$class.refinedType((Internals.SymbolTableInternal)this, parents2, owner2);
            }

            public Types.Type refinedType(List<Types.Type> parents2, Symbols.Symbol owner2, Scopes.Scope decls) {
                return Internals$SymbolTableInternal$class.refinedType((Internals.SymbolTableInternal)this, parents2, owner2, decls);
            }

            public Types.Type refinedType(List<Types.Type> parents2, Symbols.Symbol owner2, Scopes.Scope decls, Position pos) {
                return Internals$SymbolTableInternal$class.refinedType(this, parents2, owner2, decls, pos);
            }

            public Types.Type intersectionType(List<Types.Type> tps) {
                return Internals$SymbolTableInternal$class.intersectionType(this, tps);
            }

            public Types.Type intersectionType(List<Types.Type> tps, Symbols.Symbol owner2) {
                return Internals$SymbolTableInternal$class.intersectionType(this, tps, owner2);
            }

            public Types.ClassInfoType classInfoType(List<Types.Type> parents2, Scopes.Scope decls, Symbols.Symbol typeSymbol2) {
                return Internals$SymbolTableInternal$class.classInfoType(this, parents2, decls, typeSymbol2);
            }

            public Types.MethodType methodType(List<Symbols.Symbol> params2, Types.Type resultType) {
                return Internals$SymbolTableInternal$class.methodType(this, params2, resultType);
            }

            public Types.NullaryMethodType nullaryMethodType(Types.Type resultType) {
                return Internals$SymbolTableInternal$class.nullaryMethodType(this, resultType);
            }

            public Types.PolyType polyType(List<Symbols.Symbol> typeParams2, Types.Type resultType) {
                return Internals$SymbolTableInternal$class.polyType(this, typeParams2, resultType);
            }

            public Types.ExistentialType existentialType(List<Symbols.Symbol> quantified, Types.Type underlying) {
                return Internals$SymbolTableInternal$class.existentialType(this, quantified, underlying);
            }

            public Types.Type existentialAbstraction(List<Symbols.Symbol> tparams2, Types.Type tpe0) {
                return Internals$SymbolTableInternal$class.existentialAbstraction(this, tparams2, tpe0);
            }

            public Types.AnnotatedType annotatedType(List<AnnotationInfos.AnnotationInfo> annotations, Types.Type underlying) {
                return Internals$SymbolTableInternal$class.annotatedType(this, annotations, underlying);
            }

            public Types.TypeBounds typeBounds(Types.Type lo, Types.Type hi) {
                return Internals$SymbolTableInternal$class.typeBounds(this, lo, hi);
            }

            public Types.BoundedWildcardType boundedWildcardType(Types.TypeBounds bounds) {
                return Internals$SymbolTableInternal$class.boundedWildcardType(this, bounds);
            }

            public Option<List<Trees.Tree>> subpatterns(Trees.Tree tree) {
                return Internals$SymbolTableInternal$class.subpatterns(this, tree);
            }

            public Position newTermSymbol$default$3() {
                return Internals$SymbolTableInternal$class.newTermSymbol$default$3(this);
            }

            public long newTermSymbol$default$4() {
                return Internals$SymbolTableInternal$class.newTermSymbol$default$4(this);
            }

            public Position newModuleAndClassSymbol$default$3() {
                return Internals$SymbolTableInternal$class.newModuleAndClassSymbol$default$3(this);
            }

            public long newModuleAndClassSymbol$default$4() {
                return Internals$SymbolTableInternal$class.newModuleAndClassSymbol$default$4(this);
            }

            public Position newMethodSymbol$default$3() {
                return Internals$SymbolTableInternal$class.newMethodSymbol$default$3(this);
            }

            public long newMethodSymbol$default$4() {
                return Internals$SymbolTableInternal$class.newMethodSymbol$default$4(this);
            }

            public Position newTypeSymbol$default$3() {
                return Internals$SymbolTableInternal$class.newTypeSymbol$default$3(this);
            }

            public long newTypeSymbol$default$4() {
                return Internals$SymbolTableInternal$class.newTypeSymbol$default$4(this);
            }

            public Position newClassSymbol$default$3() {
                return Internals$SymbolTableInternal$class.newClassSymbol$default$3(this);
            }

            public long newClassSymbol$default$4() {
                return Internals$SymbolTableInternal$class.newClassSymbol$default$4(this);
            }

            public long newFreeTerm$default$3() {
                return Internals$SymbolTableInternal$class.newFreeTerm$default$3(this);
            }

            public String newFreeTerm$default$4() {
                return Internals$SymbolTableInternal$class.newFreeTerm$default$4(this);
            }

            public long newFreeType$default$2() {
                return Internals$SymbolTableInternal$class.newFreeType$default$2(this);
            }

            public String newFreeType$default$3() {
                return Internals$SymbolTableInternal$class.newFreeType$default$3(this);
            }

            public <T> Manifest<T> typeTagToManifest(Object mirror, TypeTags.TypeTag<T> tag, ClassTag<T> evidence$1) {
                return Internals$InternalApi$class.typeTagToManifest(this, mirror, tag, evidence$1);
            }

            public <T> TypeTags.TypeTag<T> manifestToTypeTag(Object mirror, Manifest<T> manifest) {
                return Internals$InternalApi$class.manifestToTypeTag(this, mirror, manifest);
            }

            public /* synthetic */ Internals scala$reflect$internal$Internals$SymbolTableInternal$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ scala.reflect.macros.Universe scala$reflect$macros$Universe$MacroInternalApi$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ scala.reflect.api.Internals scala$reflect$api$Internals$InternalApi$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                Internals$InternalApi$class.$init$(this);
                Universe$MacroInternalApi$class.$init$(this);
                Internals$SymbolTableInternal$class.$init$(this);
            }
        };
    }

    public static Universe.MacroCompatApi compat(SymbolTable $this) {
        return new Universe.MacroCompatApi($this){
            private final /* synthetic */ SymbolTable $outer;
            private final Internals.CompatToken token;

            public Universe.MacroCompatApi.MacroCompatibleSymbol MacroCompatibleSymbol(Symbols.SymbolApi symbol) {
                return Universe$MacroCompatApi$class.MacroCompatibleSymbol(this, symbol);
            }

            public Universe.MacroCompatApi.MacroCompatibleTree MacroCompatibleTree(Trees.TreeApi tree) {
                return Universe$MacroCompatApi$class.MacroCompatibleTree(this, tree);
            }

            public Universe.MacroCompatApi.CompatibleTypeTree CompatibleTypeTree(Trees.TypeTreeApi tt) {
                return Universe$MacroCompatApi$class.CompatibleTypeTree(this, tt);
            }

            public void captureVariable(Symbols.SymbolApi vble) {
                Universe$MacroCompatApi$class.captureVariable(this, vble);
            }

            public Trees.TreeApi referenceCapturedVariable(Symbols.SymbolApi vble) {
                return Universe$MacroCompatApi$class.referenceCapturedVariable(this, vble);
            }

            public Types.TypeApi capturedVariableType(Symbols.SymbolApi vble) {
                return Universe$MacroCompatApi$class.capturedVariableType(this, vble);
            }

            public Internals.CompatToken token() {
                return this.token;
            }

            public void scala$reflect$api$Internals$CompatApi$_setter_$token_$eq(Internals.CompatToken x$1) {
                this.token = x$1;
            }

            public <T> Manifest<T> typeTagToManifest(Object mirror, TypeTags.TypeTag<T> tag, ClassTag<T> evidence$2) {
                return Internals$CompatApi$class.typeTagToManifest(this, mirror, tag, evidence$2);
            }

            public <T> TypeTags.TypeTag<T> manifestToTypeTag(Object mirror, Manifest<T> manifest) {
                return Internals$CompatApi$class.manifestToTypeTag(this, mirror, manifest);
            }

            public Scopes.ScopeApi newScopeWith(Seq<Symbols.SymbolApi> elems) {
                return Internals$CompatApi$class.newScopeWith(this, elems);
            }

            public Internals.CompatApi.CompatibleBuildApi CompatibleBuildApi(Internals.ReificationSupportApi api2) {
                return Internals$CompatApi$class.CompatibleBuildApi(this, api2);
            }

            public Internals.CompatApi.CompatibleTree CompatibleTree(Trees.TreeApi tree) {
                return Internals$CompatApi$class.CompatibleTree(this, tree);
            }

            public Internals.CompatApi.CompatibleSymbol CompatibleSymbol(Symbols.SymbolApi symbol) {
                return Internals$CompatApi$class.CompatibleSymbol(this, symbol);
            }

            public Types.TypeApi singleType(Types.TypeApi pre, Symbols.SymbolApi sym) {
                return Internals$CompatApi$class.singleType(this, pre, sym);
            }

            public Types.TypeApi refinedType(List<Types.TypeApi> parents2, Symbols.SymbolApi owner2, Scopes.ScopeApi decls, scala.reflect.api.Position pos) {
                return Internals$CompatApi$class.refinedType(this, parents2, owner2, decls, pos);
            }

            public Types.TypeApi refinedType(List<Types.TypeApi> parents2, Symbols.SymbolApi owner2) {
                return Internals$CompatApi$class.refinedType(this, parents2, owner2);
            }

            public Types.TypeApi typeRef(Types.TypeApi pre, Symbols.SymbolApi sym, List<Types.TypeApi> args) {
                return Internals$CompatApi$class.typeRef(this, pre, sym, args);
            }

            public Types.TypeApi intersectionType(List<Types.TypeApi> tps) {
                return Internals$CompatApi$class.intersectionType(this, tps);
            }

            public Types.TypeApi intersectionType(List<Types.TypeApi> tps, Symbols.SymbolApi owner2) {
                return Internals$CompatApi$class.intersectionType(this, tps, owner2);
            }

            public Types.TypeApi polyType(List<Symbols.SymbolApi> tparams2, Types.TypeApi tpe) {
                return Internals$CompatApi$class.polyType(this, tparams2, tpe);
            }

            public Types.TypeApi existentialAbstraction(List<Symbols.SymbolApi> tparams2, Types.TypeApi tpe0) {
                return Internals$CompatApi$class.existentialAbstraction(this, tparams2, tpe0);
            }

            public /* synthetic */ scala.reflect.macros.Universe scala$reflect$macros$Universe$MacroCompatApi$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ scala.reflect.api.Internals scala$reflect$api$Internals$CompatApi$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                Internals$CompatApi$class.$init$(this);
                Universe$MacroCompatApi$class.$init$(this);
            }
        };
    }

    public static Universe.TreeGen treeBuild(SymbolTable $this) {
        return new Universe.TreeGen($this){
            private final /* synthetic */ SymbolTable $outer;

            public Trees.Tree mkAttributedQualifier(Types.Type tpe) {
                return this.$outer.gen().mkAttributedQualifier(tpe);
            }

            public Trees.Tree mkAttributedQualifier(Types.Type tpe, Symbols.Symbol termSym) {
                return this.$outer.gen().mkAttributedQualifier(tpe, termSym);
            }

            public Trees.RefTree mkAttributedRef(Types.Type pre, Symbols.Symbol sym) {
                return this.$outer.gen().mkAttributedRef(pre, sym);
            }

            public Trees.RefTree mkAttributedRef(Symbols.Symbol sym) {
                return this.$outer.gen().mkAttributedRef(sym);
            }

            public Trees.Tree stabilize(Trees.Tree tree) {
                return this.$outer.gen().stabilize(tree);
            }

            public Trees.Tree mkAttributedStableRef(Types.Type pre, Symbols.Symbol sym) {
                return this.$outer.gen().mkAttributedStableRef(pre, sym);
            }

            public Trees.Tree mkAttributedStableRef(Symbols.Symbol sym) {
                return this.$outer.gen().mkAttributedStableRef(sym);
            }

            public Trees.RefTree mkUnattributedRef(Symbols.Symbol sym) {
                return this.$outer.gen().mkUnattributedRef(sym);
            }

            public Trees.RefTree mkUnattributedRef(Names.Name fullName) {
                return this.$outer.gen().mkUnattributedRef(fullName);
            }

            public Trees.This mkAttributedThis(Symbols.Symbol sym) {
                return this.$outer.gen().mkAttributedThis(sym);
            }

            public Trees.RefTree mkAttributedIdent(Symbols.Symbol sym) {
                return this.$outer.gen().mkAttributedIdent(sym);
            }

            public Trees.RefTree mkAttributedSelect(Trees.Tree qual, Symbols.Symbol sym) {
                return this.$outer.gen().mkAttributedSelect(qual, sym);
            }

            public Trees.Tree mkMethodCall(Symbols.Symbol receiver, Names.Name methodName, List<Types.Type> targs, List<Trees.Tree> args) {
                return this.$outer.gen().mkMethodCall(receiver, methodName, targs, args);
            }

            public Trees.Tree mkMethodCall(Symbols.Symbol method, List<Types.Type> targs, List<Trees.Tree> args) {
                return this.$outer.gen().mkMethodCall(method, targs, args);
            }

            public Trees.Tree mkMethodCall(Symbols.Symbol method, List<Trees.Tree> args) {
                return this.$outer.gen().mkMethodCall(method, args);
            }

            public Trees.Tree mkMethodCall(Trees.Tree target, List<Trees.Tree> args) {
                return this.$outer.gen().mkMethodCall(target, args);
            }

            public Trees.Tree mkMethodCall(Symbols.Symbol receiver, Names.Name methodName, List<Trees.Tree> args) {
                return this.$outer.gen().mkMethodCall(receiver, methodName, args);
            }

            public Trees.Tree mkMethodCall(Trees.Tree receiver, Symbols.Symbol method, List<Types.Type> targs, List<Trees.Tree> args) {
                return this.$outer.gen().mkMethodCall(receiver, method, targs, args);
            }

            public Trees.Tree mkMethodCall(Trees.Tree target, List<Types.Type> targs, List<Trees.Tree> args) {
                return this.$outer.gen().mkMethodCall(target, targs, args);
            }

            public Trees.Tree mkNullaryCall(Symbols.Symbol method, List<Types.Type> targs) {
                return this.$outer.gen().mkNullaryCall(method, targs);
            }

            public Trees.Tree mkRuntimeUniverseRef() {
                return this.$outer.gen().mkRuntimeUniverseRef();
            }

            public Trees.Tree mkZero(Types.Type tp) {
                return this.$outer.gen().mkZero(tp);
            }

            public Trees.Tree mkCast(Trees.Tree tree, Types.Type pt) {
                return this.$outer.gen().mkCast(tree, pt);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
    }

    public static void $init$(SymbolTable $this) {
    }
}

