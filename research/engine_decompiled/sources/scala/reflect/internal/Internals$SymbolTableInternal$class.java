/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.api.Internals;
import scala.reflect.api.Internals$InternalApi$DecoratorApi$class;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.reflect.internal.CapturedVariables;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Definitions;
import scala.reflect.internal.FlagSets;
import scala.reflect.internal.Importers;
import scala.reflect.internal.Internals;
import scala.reflect.internal.Internals$SymbolTableInternal$;
import scala.reflect.internal.Internals$SymbolTableInternal$changeOwnerAndModuleClassTraverser$2$;
import scala.reflect.internal.Names;
import scala.reflect.internal.Positions;
import scala.reflect.internal.ReificationSupport;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.StdAttachments;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.Position;
import scala.reflect.macros.Attachments;
import scala.reflect.macros.Universe;
import scala.reflect.macros.Universe$MacroInternalApi$MacroDecoratorApi$class;
import scala.runtime.BoxesRunTime;
import scala.runtime.VolatileObjectRef;

public abstract class Internals$SymbolTableInternal$class {
    private static Internals$SymbolTableInternal$changeOwnerAndModuleClassTraverser$2$ changeOwnerAndModuleClassTraverser$1$lzycompute(Internals.SymbolTableInternal x$1, VolatileObjectRef x$2, Symbols.Symbol x$3, Symbols.Symbol x$4) {
        Internals.SymbolTableInternal symbolTableInternal = x$1;
        synchronized (symbolTableInternal) {
            if (x$2.elem == null) {
                x$2.elem = new Internals$SymbolTableInternal$changeOwnerAndModuleClassTraverser$2$(x$1, x$3, x$4);
            }
            // ** MonitorExit[x$1] (shouldn't be in output)
            return (Internals$SymbolTableInternal$changeOwnerAndModuleClassTraverser$2$)x$2.elem;
        }
    }

    public static Internals.ReificationSupportApi reificationSupport(Internals.SymbolTableInternal $this) {
        return ((ReificationSupport)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).build();
    }

    public static Internals.Importer createImporter(Internals.SymbolTableInternal $this, Universe from0) {
        return ((Importers)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).mkImporter(from0);
    }

    public static Scopes.Scope newScopeWith(Internals.SymbolTableInternal $this, Seq elems) {
        return ((Scopes)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).newScopeWith(elems);
    }

    public static Scopes.Scope enter(Internals.SymbolTableInternal $this, Scopes.Scope scope, Symbols.Symbol sym) {
        scope.enter(sym);
        return scope;
    }

    public static Scopes.Scope unlink(Internals.SymbolTableInternal $this, Scopes.Scope scope, Symbols.Symbol sym) {
        scope.unlink(sym);
        return scope;
    }

    public static List freeTerms(Internals.SymbolTableInternal $this, Trees.Tree tree) {
        return tree.freeTerms();
    }

    public static List freeTypes(Internals.SymbolTableInternal $this, Trees.Tree tree) {
        return tree.freeTypes();
    }

    public static Trees.Tree substituteSymbols(Internals.SymbolTableInternal $this, Trees.Tree tree, List from2, List to2) {
        return tree.substituteSymbols(from2, to2);
    }

    public static Trees.Tree substituteTypes(Internals.SymbolTableInternal $this, Trees.Tree tree, List from2, List to2) {
        return tree.substituteTypes(from2, to2);
    }

    public static Trees.Tree substituteThis(Internals.SymbolTableInternal $this, Trees.Tree tree, Symbols.Symbol clazz, Trees.Tree to2) {
        return tree.substituteThis(clazz, to2);
    }

    public static Attachments attachments(Internals.SymbolTableInternal $this, Trees.Tree tree) {
        return tree.attachments();
    }

    public static Trees.Tree updateAttachment(Internals.SymbolTableInternal $this, Trees.Tree tree, Object attachment, ClassTag evidence$1) {
        return (Trees.Tree)tree.updateAttachment(attachment, evidence$1);
    }

    public static Trees.Tree removeAttachment(Internals.SymbolTableInternal $this, Trees.Tree tree, ClassTag evidence$2) {
        return (Trees.Tree)tree.removeAttachment(evidence$2);
    }

    public static Trees.Tree setPos(Internals.SymbolTableInternal $this, Trees.Tree tree, Position newpos) {
        return (Trees.Tree)tree.setPos(newpos);
    }

    public static Trees.Tree setType(Internals.SymbolTableInternal $this, Trees.Tree tree, Types.Type tp) {
        return tree.setType(tp);
    }

    public static Trees.Tree defineType(Internals.SymbolTableInternal $this, Trees.Tree tree, Types.Type tp) {
        return tree.defineType(tp);
    }

    public static Trees.Tree setSymbol(Internals.SymbolTableInternal $this, Trees.Tree tree, Symbols.Symbol sym) {
        return tree.setSymbol(sym);
    }

    public static Trees.TypeTree setOriginal(Internals.SymbolTableInternal $this, Trees.TypeTree tt, Trees.Tree tree) {
        return tt.setOriginal(tree);
    }

    public static void captureVariable(Internals.SymbolTableInternal $this, Symbols.Symbol vble) {
        ((CapturedVariables)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).captureVariable(vble);
    }

    public static Trees.Tree referenceCapturedVariable(Internals.SymbolTableInternal $this, Symbols.Symbol vble) {
        return ((CapturedVariables)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).referenceCapturedVariable(vble);
    }

    public static Types.Type capturedVariableType(Internals.SymbolTableInternal $this, Symbols.Symbol vble) {
        return ((CapturedVariables)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).capturedVariableType(vble);
    }

    public static Trees.ClassDef classDef(Internals.SymbolTableInternal $this, Symbols.Symbol sym, Trees.Template impl) {
        return ((Trees)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).ClassDef().apply(sym, impl);
    }

    public static Trees.ModuleDef moduleDef(Internals.SymbolTableInternal $this, Symbols.Symbol sym, Trees.Template impl) {
        return ((Trees)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).ModuleDef().apply(sym, impl);
    }

    public static Trees.ValDef valDef(Internals.SymbolTableInternal $this, Symbols.Symbol sym, Trees.Tree rhs) {
        return ((Trees)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).ValDef().apply(sym, rhs);
    }

    public static Trees.ValDef valDef(Internals.SymbolTableInternal $this, Symbols.Symbol sym) {
        return ((Trees)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).ValDef().apply(sym);
    }

    public static Trees.DefDef defDef(Internals.SymbolTableInternal $this, Symbols.Symbol sym, Trees.Modifiers mods, List vparamss, Trees.Tree rhs) {
        return ((Trees)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).DefDef().apply(sym, mods, vparamss, rhs);
    }

    public static Trees.DefDef defDef(Internals.SymbolTableInternal $this, Symbols.Symbol sym, List vparamss, Trees.Tree rhs) {
        return ((Trees)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).DefDef().apply(sym, vparamss, rhs);
    }

    public static Trees.DefDef defDef(Internals.SymbolTableInternal $this, Symbols.Symbol sym, Trees.Modifiers mods, Trees.Tree rhs) {
        return ((Trees)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).DefDef().apply(sym, mods, rhs);
    }

    public static Trees.DefDef defDef(Internals.SymbolTableInternal $this, Symbols.Symbol sym, Trees.Tree rhs) {
        return ((Trees)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).DefDef().apply(sym, rhs);
    }

    public static Trees.DefDef defDef(Internals.SymbolTableInternal $this, Symbols.Symbol sym, Function1 rhs) {
        return ((Trees)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).DefDef().apply(sym, rhs);
    }

    public static Trees.TypeDef typeDef(Internals.SymbolTableInternal $this, Symbols.Symbol sym, Trees.Tree rhs) {
        return ((Trees)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).TypeDef().apply(sym, rhs);
    }

    public static Trees.TypeDef typeDef(Internals.SymbolTableInternal $this, Symbols.Symbol sym) {
        return ((Trees)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).TypeDef().apply(sym);
    }

    public static Trees.LabelDef labelDef(Internals.SymbolTableInternal $this, Symbols.Symbol sym, List params2, Trees.Tree rhs) {
        return ((Trees)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).LabelDef().apply(sym, (List<Symbols.Symbol>)params2, rhs);
    }

    public static Trees.Tree changeOwner(Internals.SymbolTableInternal $this, Trees.Tree tree, Symbols.Symbol prev, Symbols.Symbol next2) {
        VolatileObjectRef<Object> changeOwnerAndModuleClassTraverser$module = VolatileObjectRef.zero();
        Internals$SymbolTableInternal$class.changeOwnerAndModuleClassTraverser$1($this, changeOwnerAndModuleClassTraverser$module, prev, next2).traverse(tree);
        return tree;
    }

    public static Universe.TreeGen gen(Internals.SymbolTableInternal $this) {
        return $this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer().treeBuild();
    }

    public static boolean isFreeTerm(Internals.SymbolTableInternal $this, Symbols.Symbol symbol) {
        return symbol.isFreeTerm();
    }

    public static Symbols.FreeTermSymbol asFreeTerm(Internals.SymbolTableInternal $this, Symbols.Symbol symbol) {
        return symbol.asFreeTerm();
    }

    public static boolean isFreeType(Internals.SymbolTableInternal $this, Symbols.Symbol symbol) {
        return symbol.isFreeType();
    }

    public static Symbols.FreeTypeSymbol asFreeType(Internals.SymbolTableInternal $this, Symbols.Symbol symbol) {
        return symbol.asFreeType();
    }

    public static Symbols.TermSymbol newTermSymbol(Internals.SymbolTableInternal $this, Symbols.Symbol symbol, Names.TermName name, Position pos, long flags) {
        return symbol.newTermSymbol(name, pos, flags);
    }

    public static Position newTermSymbol$default$3(Internals.SymbolTableInternal $this) {
        return ((Positions)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).NoPosition();
    }

    public static long newTermSymbol$default$4(Internals.SymbolTableInternal $this) {
        return ((FlagSets)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).NoFlags();
    }

    public static Tuple2 newModuleAndClassSymbol(Internals.SymbolTableInternal $this, Symbols.Symbol symbol, Names.Name name, Position pos, long flags) {
        return symbol.newModuleAndClassSymbol(name, pos, flags);
    }

    public static Position newModuleAndClassSymbol$default$3(Internals.SymbolTableInternal $this) {
        return ((Positions)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).NoPosition();
    }

    public static long newModuleAndClassSymbol$default$4(Internals.SymbolTableInternal $this) {
        return ((FlagSets)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).NoFlags();
    }

    public static Symbols.MethodSymbol newMethodSymbol(Internals.SymbolTableInternal $this, Symbols.Symbol symbol, Names.TermName name, Position pos, long flags) {
        return symbol.newMethodSymbol(name, pos, flags);
    }

    public static Position newMethodSymbol$default$3(Internals.SymbolTableInternal $this) {
        return ((Positions)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).NoPosition();
    }

    public static long newMethodSymbol$default$4(Internals.SymbolTableInternal $this) {
        return ((FlagSets)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).NoFlags();
    }

    public static Symbols.TypeSymbol newTypeSymbol(Internals.SymbolTableInternal $this, Symbols.Symbol symbol, Names.TypeName name, Position pos, long flags) {
        return symbol.newTypeSymbol(name, pos, flags);
    }

    public static Position newTypeSymbol$default$3(Internals.SymbolTableInternal $this) {
        return ((Positions)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).NoPosition();
    }

    public static long newTypeSymbol$default$4(Internals.SymbolTableInternal $this) {
        return ((FlagSets)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).NoFlags();
    }

    public static Symbols.ClassSymbol newClassSymbol(Internals.SymbolTableInternal $this, Symbols.Symbol symbol, Names.TypeName name, Position pos, long flags) {
        return symbol.newClassSymbol(name, pos, flags);
    }

    public static Position newClassSymbol$default$3(Internals.SymbolTableInternal $this) {
        return ((Positions)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).NoPosition();
    }

    public static long newClassSymbol$default$4(Internals.SymbolTableInternal $this) {
        return ((FlagSets)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).NoFlags();
    }

    public static Symbols.FreeTermSymbol newFreeTerm(Internals.SymbolTableInternal $this, String name, Function0 value, long flags, String origin) {
        return (Symbols.FreeTermSymbol)$this.reificationSupport().newFreeTerm(name, value, BoxesRunTime.boxToLong(flags), origin);
    }

    public static long newFreeTerm$default$3(Internals.SymbolTableInternal $this) {
        return ((FlagSets)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).NoFlags();
    }

    public static String newFreeTerm$default$4(Internals.SymbolTableInternal $this) {
        return null;
    }

    public static Symbols.FreeTypeSymbol newFreeType(Internals.SymbolTableInternal $this, String name, long flags, String origin) {
        return (Symbols.FreeTypeSymbol)$this.reificationSupport().newFreeType(name, BoxesRunTime.boxToLong(flags), origin);
    }

    public static long newFreeType$default$2(Internals.SymbolTableInternal $this) {
        return ((FlagSets)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).NoFlags();
    }

    public static String newFreeType$default$3(Internals.SymbolTableInternal $this) {
        return null;
    }

    public static boolean isErroneous(Internals.SymbolTableInternal $this, Symbols.Symbol symbol) {
        return symbol.isErroneous();
    }

    public static boolean isSkolem(Internals.SymbolTableInternal $this, Symbols.Symbol symbol) {
        return symbol.isSkolem();
    }

    public static Symbols.Symbol deSkolemize(Internals.SymbolTableInternal $this, Symbols.Symbol symbol) {
        return symbol.deSkolemize();
    }

    public static Symbols.Symbol initialize(Internals.SymbolTableInternal $this, Symbols.Symbol symbol) {
        return symbol.initialize();
    }

    public static Symbols.Symbol fullyInitialize(Internals.SymbolTableInternal $this, Symbols.Symbol symbol) {
        return ((Definitions)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).definitions().fullyInitializeSymbol(symbol);
    }

    public static Types.Type fullyInitialize(Internals.SymbolTableInternal $this, Types.Type tp) {
        return ((Definitions)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).definitions().fullyInitializeType(tp);
    }

    public static Scopes.Scope fullyInitialize(Internals.SymbolTableInternal $this, Scopes.Scope scope) {
        return ((Definitions)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).definitions().fullyInitializeScope(scope);
    }

    public static long flags(Internals.SymbolTableInternal $this, Symbols.Symbol symbol) {
        return symbol.flags();
    }

    public static Attachments attachments(Internals.SymbolTableInternal $this, Symbols.Symbol symbol) {
        return symbol.attachments();
    }

    public static Symbols.Symbol updateAttachment(Internals.SymbolTableInternal $this, Symbols.Symbol symbol, Object attachment, ClassTag evidence$3) {
        return (Symbols.Symbol)symbol.updateAttachment(attachment, evidence$3);
    }

    public static Symbols.Symbol removeAttachment(Internals.SymbolTableInternal $this, Symbols.Symbol symbol, ClassTag evidence$4) {
        return (Symbols.Symbol)symbol.removeAttachment(evidence$4);
    }

    public static Symbols.Symbol setOwner(Internals.SymbolTableInternal $this, Symbols.Symbol symbol, Symbols.Symbol newowner) {
        symbol.owner_$eq(newowner);
        return symbol;
    }

    public static Symbols.Symbol setInfo(Internals.SymbolTableInternal $this, Symbols.Symbol symbol, Types.Type tpe) {
        return symbol.setInfo(tpe);
    }

    public static Symbols.Symbol setAnnotations(Internals.SymbolTableInternal $this, Symbols.Symbol symbol, Seq annots) {
        return symbol.setAnnotations(annots);
    }

    public static Symbols.Symbol setName(Internals.SymbolTableInternal $this, Symbols.Symbol symbol, Names.Name name) {
        return symbol.setName(name);
    }

    public static Symbols.Symbol setPrivateWithin(Internals.SymbolTableInternal $this, Symbols.Symbol symbol, Symbols.Symbol sym) {
        return symbol.setPrivateWithin(sym);
    }

    public static Symbols.Symbol setFlag(Internals.SymbolTableInternal $this, Symbols.Symbol symbol, long flags) {
        return symbol.setFlag(flags);
    }

    public static Symbols.Symbol resetFlag(Internals.SymbolTableInternal $this, Symbols.Symbol symbol, long flags) {
        return symbol.resetFlag(flags);
    }

    public static Types.Type thisType(Internals.SymbolTableInternal $this, Symbols.Symbol sym) {
        return ((Types)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).ThisType().apply(sym);
    }

    public static Types.Type singleType(Internals.SymbolTableInternal $this, Types.Type pre, Symbols.Symbol sym) {
        return ((Types)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).SingleType().apply(pre, sym);
    }

    public static Types.Type superType(Internals.SymbolTableInternal $this, Types.Type thistpe, Types.Type supertpe) {
        return ((Types)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).SuperType().apply(thistpe, supertpe);
    }

    public static Types.ConstantType constantType(Internals.SymbolTableInternal $this, Constants.Constant value) {
        return ((Types)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).ConstantType().apply(value);
    }

    public static Types.Type typeRef(Internals.SymbolTableInternal $this, Types.Type pre, Symbols.Symbol sym, List args) {
        return ((Types)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).TypeRef().apply(pre, sym, args);
    }

    public static Types.RefinedType refinedType(Internals.SymbolTableInternal $this, List parents2, Scopes.Scope decls) {
        return new Types.RefinedType((SymbolTable)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer(), parents2, decls);
    }

    public static Types.RefinedType refinedType(Internals.SymbolTableInternal $this, List parents2, Scopes.Scope decls, Symbols.Symbol clazz) {
        return ((Types)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).RefinedType().apply(parents2, decls, clazz);
    }

    public static Types.Type refinedType(Internals.SymbolTableInternal $this, List parents2, Symbols.Symbol owner2) {
        return ((Types)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).refinedType(parents2, owner2);
    }

    public static Types.Type refinedType(Internals.SymbolTableInternal $this, List parents2, Symbols.Symbol owner2, Scopes.Scope decls) {
        return ((Types)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).RefinedType().apply(parents2, decls, owner2);
    }

    public static Types.Type refinedType(Internals.SymbolTableInternal $this, List parents2, Symbols.Symbol owner2, Scopes.Scope decls, Position pos) {
        return ((Types)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).refinedType(parents2, owner2, decls, pos);
    }

    public static Types.Type intersectionType(Internals.SymbolTableInternal $this, List tps) {
        return ((Types)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).intersectionType(tps);
    }

    public static Types.Type intersectionType(Internals.SymbolTableInternal $this, List tps, Symbols.Symbol owner2) {
        return ((Types)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).intersectionType(tps, owner2);
    }

    public static Types.ClassInfoType classInfoType(Internals.SymbolTableInternal $this, List parents2, Scopes.Scope decls, Symbols.Symbol typeSymbol2) {
        return new Types.ClassInfoType((SymbolTable)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer(), parents2, decls, typeSymbol2);
    }

    public static Types.MethodType methodType(Internals.SymbolTableInternal $this, List params2, Types.Type resultType) {
        return new Types.MethodType((SymbolTable)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer(), params2, resultType);
    }

    public static Types.NullaryMethodType nullaryMethodType(Internals.SymbolTableInternal $this, Types.Type resultType) {
        return new Types.NullaryMethodType((SymbolTable)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer(), resultType);
    }

    public static Types.PolyType polyType(Internals.SymbolTableInternal $this, List typeParams2, Types.Type resultType) {
        return new Types.PolyType((SymbolTable)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer(), typeParams2, resultType);
    }

    public static Types.ExistentialType existentialType(Internals.SymbolTableInternal $this, List quantified, Types.Type underlying) {
        return new Types.ExistentialType((SymbolTable)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer(), quantified, underlying);
    }

    public static Types.Type existentialAbstraction(Internals.SymbolTableInternal $this, List tparams2, Types.Type tpe0) {
        return ((Types)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).existentialAbstraction(tparams2, tpe0);
    }

    public static Types.AnnotatedType annotatedType(Internals.SymbolTableInternal $this, List annotations, Types.Type underlying) {
        return new Types.AnnotatedType((SymbolTable)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer(), annotations, underlying);
    }

    public static Types.TypeBounds typeBounds(Internals.SymbolTableInternal $this, Types.Type lo, Types.Type hi) {
        return ((Types)((Object)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).TypeBounds().apply(lo, hi);
    }

    public static Types.BoundedWildcardType boundedWildcardType(Internals.SymbolTableInternal $this, Types.TypeBounds bounds) {
        return new Types.BoundedWildcardType((SymbolTable)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer(), bounds);
    }

    public static Option subpatterns(Internals.SymbolTableInternal $this, Trees.Tree tree) {
        Serializable serializable = new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ Internals.SymbolTableInternal $outer;

            public final List<Trees.Tree> apply(StdAttachments.SubpatternsAttachment x$1) {
                return x$1.patterns().map(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Internals$SymbolTableInternal$.anonfun.subpatterns.1 $outer;

                    public final Trees.Tree apply(Trees.Tree tree) {
                        return ((Trees)((Object)this.$outer.$outer.scala$reflect$internal$Internals$SymbolTableInternal$$$outer())).duplicateAndKeepPositions(tree);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }, List$.MODULE$.canBuildFrom());
            }

            public /* synthetic */ Internals.SymbolTableInternal scala$reflect$internal$Internals$SymbolTableInternal$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
        Option option = tree.attachments().get(ClassTag$.MODULE$.apply(StdAttachments.SubpatternsAttachment.class));
        return !option.isEmpty() ? new Some(((StdAttachments.SubpatternsAttachment)option.get()).patterns().map(new /* invalid duplicate definition of identical inner class */, List$.MODULE$.canBuildFrom())) : None$.MODULE$;
    }

    public static Universe.MacroInternalApi.MacroDecoratorApi decorators(Internals.SymbolTableInternal $this) {
        return new Universe.MacroInternalApi.MacroDecoratorApi($this){
            private final /* synthetic */ Internals.SymbolTableInternal $outer;

            public <T extends Types.TypeApi> Internals.InternalApi.DecoratorApi.TypeDecoratorApi<T> TypeDecoratorApi(T tp) {
                return Internals$InternalApi$DecoratorApi$class.TypeDecoratorApi(this, tp);
            }

            public <T extends Scopes.Scope> Universe.MacroInternalApi.MacroDecoratorApi.MacroScopeDecoratorApi<T> scopeDecorator(T scope) {
                return new Universe.MacroInternalApi.MacroDecoratorApi.MacroScopeDecoratorApi<T>(this, scope);
            }

            public <T extends Trees.Tree> Universe.MacroInternalApi.MacroDecoratorApi.MacroTreeDecoratorApi<T> treeDecorator(T tree) {
                return new Universe.MacroInternalApi.MacroDecoratorApi.MacroTreeDecoratorApi<T>(this, tree);
            }

            public <T extends Trees.TypeTree> Universe.MacroInternalApi.MacroDecoratorApi.MacroTypeTreeDecoratorApi<T> typeTreeDecorator(T tt) {
                return new Universe.MacroInternalApi.MacroDecoratorApi.MacroTypeTreeDecoratorApi<T>(this, tt);
            }

            public <T extends Symbols.Symbol> Universe.MacroInternalApi.MacroDecoratorApi.MacroSymbolDecoratorApi<T> symbolDecorator(T symbol) {
                return new Universe.MacroInternalApi.MacroDecoratorApi.MacroSymbolDecoratorApi<T>(this, symbol);
            }

            public <T extends Types.Type> Internals.InternalApi.DecoratorApi.TypeDecoratorApi<T> typeDecorator(T tp) {
                return new Internals.InternalApi.DecoratorApi.TypeDecoratorApi<T>(this, tp);
            }

            public /* synthetic */ Universe.MacroInternalApi scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ Internals.InternalApi scala$reflect$api$Internals$InternalApi$DecoratorApi$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                Internals$InternalApi$DecoratorApi$class.$init$(this);
                Universe$MacroInternalApi$MacroDecoratorApi$class.$init$(this);
            }
        };
    }

    private static final Internals$SymbolTableInternal$changeOwnerAndModuleClassTraverser$2$ changeOwnerAndModuleClassTraverser$1(Internals.SymbolTableInternal $this, VolatileObjectRef changeOwnerAndModuleClassTraverser$module$1, Symbols.Symbol prev$1, Symbols.Symbol next$1) {
        return changeOwnerAndModuleClassTraverser$module$1.elem == null ? Internals$SymbolTableInternal$class.changeOwnerAndModuleClassTraverser$1$lzycompute($this, changeOwnerAndModuleClassTraverser$module$1, prev$1, next$1) : (Internals$SymbolTableInternal$changeOwnerAndModuleClassTraverser$2$)changeOwnerAndModuleClassTraverser$module$1.elem;
    }

    public static void $init$(Internals.SymbolTableInternal $this) {
    }
}

