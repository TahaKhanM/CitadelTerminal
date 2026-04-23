/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Predef$;
import scala.Predef$any2stringadd$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.SeqLike;
import scala.collection.TraversableOnce;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WrappedArray;
import scala.reflect.ClassTag$;
import scala.reflect.api.Trees;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Names;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Trees$noSelfType$;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Tuple2Zipped$;
import scala.runtime.Tuple2Zipped$Ops$;
import scala.sys.package$;

public abstract class Trees$class {
    public static String treeLine(SymbolTable $this, Trees.Tree t) {
        String string2;
        if (t.pos().isDefined() && t.pos().isRange()) {
            String string3 = t.pos().lineContent();
            Predef$ predef$ = Predef$.MODULE$;
            String string4 = (String)new StringOps(string3).drop(t.pos().column() - 1);
            Predef$ predef$2 = Predef$.MODULE$;
            string2 = (String)new StringOps(string4).take(t.pos().end() - t.pos().start() + 1);
        } else {
            string2 = t.summaryString();
        }
        return string2;
    }

    public static String treeStatus(SymbolTable $this, Trees.Tree t, Trees.Tree enclosingTree) {
        String string2;
        if (enclosingTree == null) {
            string2 = "        ";
        } else {
            Predef$ predef$ = Predef$.MODULE$;
            string2 = new StringOps(" P#%5s").format(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(enclosingTree.id())}));
        }
        String parent = string2;
        Predef$ predef$ = Predef$.MODULE$;
        return new StringOps("[L%4s%8s] #%-6s %-15s %-10s // %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(t.pos().line()), parent, BoxesRunTime.boxToInteger(t.id()), t.pos().show(), t.shortClass(), $this.treeLine(t)}));
    }

    public static Trees.Tree treeStatus$default$2(SymbolTable $this) {
        return null;
    }

    public static String treeSymStatus(SymbolTable $this, Trees.Tree t) {
        String string2;
        if (t.pos().isDefined()) {
            Predef$ predef$ = Predef$.MODULE$;
            string2 = new StringOps("line %-4s").format(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(t.pos().line())}));
        } else {
            string2 = "         ";
        }
        String line = string2;
        Predef$ predef$ = Predef$.MODULE$;
        return new StringOps("#%-5s %s %-10s // %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(t.id()), line, t.shortClass(), t.symbol() != $this.NoSymbol() ? new StringBuilder().append((Object)"(").append((Object)t.symbol().fullLocationString()).append((Object)")").toString() : $this.treeLine(t)}));
    }

    public static Trees.Apply ApplyConstructor(SymbolTable $this, Trees.Tree tpt, List args) {
        return new Trees.Apply($this, new Trees.Select($this, new Trees.New($this, tpt), $this.nme().CONSTRUCTOR()), args);
    }

    public static Trees.Apply NewFromConstructor(SymbolTable $this, Symbols.Symbol constructor, Seq args) {
        Serializable serializable = new Serializable($this, constructor){
            public static final long serialVersionUID = 0L;
            public final Symbols.Symbol constructor$1;

            public final Symbols.Symbol apply() {
                return this.constructor$1;
            }
            {
                this.constructor$1 = constructor$1;
            }
        };
        boolean bl = constructor.isConstructor();
        Predef$ predef$ = Predef$.MODULE$;
        if (!bl) {
            throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(serializable.constructor$1).toString());
        }
        Trees.New instance = new Trees.New($this, $this.TypeTree(constructor.owner().tpe_$times()));
        Trees.Select init2 = (Trees.Select)new Trees.Select($this, instance, $this.nme().CONSTRUCTOR()).setSymbol(constructor);
        return new Trees.Apply($this, init2, args.toList());
    }

    public static Trees.TypeTree TypeTree(SymbolTable $this, Types.Type tp) {
        return (Trees.TypeTree)new Trees.TypeTree($this).setType(tp);
    }

    private static Trees.TypeTree TypeTreeMemberType(SymbolTable $this, Symbols.Symbol sym) {
        Types.Type resType = (sym.isLocalToBlock() ? sym.tpe() : sym.owner().thisType().memberType(sym)).finalResultType();
        return $this.atPos(sym.pos().focus(), $this.TypeTree(resType));
    }

    public static Trees.TypeBoundsTree TypeBoundsTree(SymbolTable $this, Types.TypeBounds bounds) {
        return new Trees.TypeBoundsTree($this, $this.TypeTree(bounds.lo()), $this.TypeTree(bounds.hi()));
    }

    public static Trees.TypeBoundsTree TypeBoundsTree(SymbolTable $this, Symbols.Symbol sym) {
        return $this.atPos(sym.pos(), $this.TypeBoundsTree(sym.info().bounds()));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isReferenceToScalaMember(SymbolTable $this, Trees.Tree t, Names.Name Id) {
        boolean bl = false;
        Trees.Select select = null;
        if (t instanceof Trees.Ident) {
            Trees.Ident ident = (Trees.Ident)t;
            Names.Name name = Id;
            Names.Name name2 = ident.name();
            if (name == null) {
                if (name2 == null) return true;
            } else if (name.equals(name2)) {
                return true;
            }
        }
        if (t instanceof Trees.Select) {
            bl = true;
            select = (Trees.Select)t;
            if (select.qualifier() instanceof Trees.Ident) {
                Trees.Ident ident = (Trees.Ident)select.qualifier();
                Names.TermName termName = $this.nme().scala_();
                Names.Name name = ident.name();
                if (!(termName != null ? !termName.equals(name) : name != null)) {
                    Names.Name name3 = Id;
                    Names.Name name4 = select.name();
                    if (name3 == null) {
                        if (name4 == null) return true;
                    } else if (name3.equals(name4)) {
                        return true;
                    }
                }
            }
        }
        if (!bl) return false;
        if (!(select.qualifier() instanceof Trees.Select)) return false;
        Trees.Select select2 = (Trees.Select)select.qualifier();
        if (!(select2.qualifier() instanceof Trees.Ident)) return false;
        Trees.Ident ident = (Trees.Ident)select2.qualifier();
        Names.TermName termName = $this.nme().ROOTPKG();
        Names.Name name = ident.name();
        if (termName == null) {
            if (name != null) {
                return false;
            }
        } else if (!termName.equals(name)) return false;
        Names.TermName termName2 = $this.nme().scala_();
        Names.Name name5 = select2.name();
        if (termName2 == null) {
            if (name5 != null) {
                return false;
            }
        } else if (!termName2.equals(name5)) return false;
        Names.Name name6 = Id;
        Names.Name name7 = select.name();
        if (name6 == null) {
            if (name7 == null) return true;
            return false;
        } else {
            if (!name6.equals(name7)) return false;
            return true;
        }
    }

    public static boolean isReferenceToPredef(SymbolTable $this, Trees.Tree t) {
        return $this.isReferenceToScalaMember(t, $this.nme().Predef());
    }

    public static Trees.Template Template(SymbolTable $this, Symbols.Symbol sym, List body2) {
        Symbols.Symbol symbol = sym.thisSym();
        return $this.atPos(sym.pos(), new Trees.Template($this, sym.info().parents().map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final Trees.TypeTree apply(Types.Type tp) {
                return this.$outer.TypeTree(tp);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, List$.MODULE$.canBuildFrom()), !(symbol != null ? !symbol.equals(sym) : sym != null) ? $this.noSelfType() : $this.ValDef().apply(sym), body2));
    }

    public static Trees$noSelfType$ emptyValDef(SymbolTable $this) {
        return $this.noSelfType();
    }

    public static Trees.ValDef newValDef(SymbolTable $this, Symbols.Symbol sym, Trees.Tree rhs, Trees.Modifiers mods, Names.TermName name, Trees.Tree tpt) {
        return (Trees.ValDef)$this.atPos(sym.pos(), new Trees.ValDef($this, mods, name, tpt, rhs)).setSymbol(sym);
    }

    public static Trees.Modifiers newValDef$default$3(SymbolTable $this, Symbols.Symbol sym, Trees.Tree rhs) {
        return (Trees.Modifiers)$this.Modifiers(BoxesRunTime.boxToLong(sym.flags()));
    }

    public static Names.TermName newValDef$default$4(SymbolTable $this, Symbols.Symbol sym, Trees.Tree rhs) {
        return sym.name().toTermName();
    }

    public static Trees.Tree newValDef$default$5(SymbolTable $this, Symbols.Symbol sym, Trees.Tree rhs) {
        return Trees$class.TypeTreeMemberType($this, sym);
    }

    public static Trees.DefDef newDefDef(SymbolTable $this, Symbols.Symbol sym, Trees.Tree rhs, Trees.Modifiers mods, Names.TermName name, List tparams2, List vparamss, Trees.Tree tpt) {
        return (Trees.DefDef)$this.atPos(sym.pos(), new Trees.DefDef($this, mods, name, tparams2, vparamss, tpt, rhs)).setSymbol(sym);
    }

    public static Trees.Modifiers newDefDef$default$3(SymbolTable $this, Symbols.Symbol sym, Trees.Tree rhs) {
        return (Trees.Modifiers)$this.Modifiers(BoxesRunTime.boxToLong(sym.flags()));
    }

    public static Names.TermName newDefDef$default$4(SymbolTable $this, Symbols.Symbol sym, Trees.Tree rhs) {
        return sym.name().toTermName();
    }

    public static List newDefDef$default$5(SymbolTable $this, Symbols.Symbol sym, Trees.Tree rhs) {
        return sym.typeParams().map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final Trees.TypeDef apply(Symbols.Symbol sym) {
                return this.$outer.TypeDef().apply(sym);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, List$.MODULE$.canBuildFrom());
    }

    public static List newDefDef$default$6(SymbolTable $this, Symbols.Symbol sym, Trees.Tree rhs) {
        return $this.mapParamss(sym, new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final Trees.ValDef apply(Symbols.Symbol sym) {
                return this.$outer.ValDef().apply(sym);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Trees.Tree newDefDef$default$7(SymbolTable $this, Symbols.Symbol sym, Trees.Tree rhs) {
        return Trees$class.TypeTreeMemberType($this, sym);
    }

    public static Trees.TypeDef newTypeDef(SymbolTable $this, Symbols.Symbol sym, Trees.Tree rhs, Trees.Modifiers mods, Names.TypeName name, List tparams2) {
        return (Trees.TypeDef)$this.atPos(sym.pos(), new Trees.TypeDef($this, mods, name, tparams2, rhs)).setSymbol(sym);
    }

    public static Trees.Modifiers newTypeDef$default$3(SymbolTable $this, Symbols.Symbol sym, Trees.Tree rhs) {
        return (Trees.Modifiers)$this.Modifiers(BoxesRunTime.boxToLong(sym.flags()));
    }

    public static Names.TypeName newTypeDef$default$4(SymbolTable $this, Symbols.Symbol sym, Trees.Tree rhs) {
        return sym.name().toTypeName();
    }

    public static List newTypeDef$default$5(SymbolTable $this, Symbols.Symbol sym, Trees.Tree rhs) {
        return sym.typeParams().map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final Trees.TypeDef apply(Symbols.Symbol sym) {
                return this.$outer.TypeDef().apply(sym);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, List$.MODULE$.canBuildFrom());
    }

    public static Trees.CaseDef CaseDef(SymbolTable $this, Trees.Tree pat, Trees.Tree body2) {
        return new Trees.CaseDef($this, pat, $this.EmptyTree(), body2);
    }

    public static Trees.Bind Bind(SymbolTable $this, Symbols.Symbol sym, Trees.Tree body2) {
        return (Trees.Bind)new Trees.Bind($this, sym.name(), body2).setSymbol(sym);
    }

    public static Trees.Try Try(SymbolTable $this, Trees.Tree body2, Seq cases) {
        return new Trees.Try($this, body2, cases.toList().map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final Trees.CaseDef apply(Tuple2<Trees.Tree, Trees.Tree> x0$4) {
                if (x0$4 != null) {
                    return new Trees.CaseDef(this.$outer, x0$4._1(), this.$outer.EmptyTree(), x0$4._2());
                }
                throw new MatchError(x0$4);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, List$.MODULE$.canBuildFrom()), $this.EmptyTree());
    }

    public static Trees.Throw Throw(SymbolTable $this, Types.Type tpe, Seq args) {
        return new Trees.Throw($this, $this.New(tpe, (Seq<Trees.Tree>)args));
    }

    public static Trees.Tree Apply(SymbolTable $this, Symbols.Symbol sym, Seq args) {
        return new Trees.Apply($this, $this.Ident(sym), args.toList());
    }

    public static Trees.Tree New(SymbolTable $this, Trees.Tree tpt, List argss) {
        block4: {
            Trees.Tree tree;
            block3: {
                block2: {
                    if (!((Object)Nil$.MODULE$).equals(argss)) break block2;
                    tree = $this.ApplyConstructor(tpt, (List<Trees.Tree>)Nil$.MODULE$);
                    break block3;
                }
                if (!(argss instanceof $colon$colon)) break block4;
                $colon$colon $colon$colon = ($colon$colon)argss;
                tree = $colon$colon.tl$1().foldLeft($this.ApplyConstructor(tpt, (List<Trees.Tree>)((List)$colon$colon.head())), new Serializable($this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SymbolTable $outer;

                    public final Trees.Apply apply(Trees.Tree fun, List<Trees.Tree> args) {
                        return new Trees.Apply(this.$outer, fun, args);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }
            return tree;
        }
        throw new MatchError(argss);
    }

    public static Trees.Tree New(SymbolTable $this, Types.Type tpe, Seq args) {
        return $this.ApplyConstructor($this.TypeTree(tpe), args.toList());
    }

    public static Trees.Tree New(SymbolTable $this, Types.Type tpe, List argss) {
        return $this.New($this.TypeTree(tpe), (List<List<Trees.Tree>>)argss);
    }

    public static Trees.Tree New(SymbolTable $this, Symbols.Symbol sym, Seq args) {
        return $this.New(sym.tpe(), (Seq<Trees.Tree>)args);
    }

    public static Trees.Tree Super(SymbolTable $this, Symbols.Symbol sym, Names.TypeName mix) {
        return new Trees.Super($this, $this.This(sym), mix);
    }

    public static Trees.Tree This(SymbolTable $this, Symbols.Symbol sym) {
        return new Trees.This($this, sym.name().toTypeName()).setSymbol(sym);
    }

    public static Trees.Select Select(SymbolTable $this, Trees.Tree qualifier, String name) {
        return new Trees.Select($this, qualifier, $this.newTermName(name));
    }

    public static Trees.Select Select(SymbolTable $this, Trees.Tree qualifier, Symbols.Symbol sym) {
        return (Trees.Select)new Trees.Select($this, qualifier, sym.name()).setSymbol(sym);
    }

    public static Trees.Ident Ident(SymbolTable $this, String name) {
        return new Trees.Ident($this, $this.newTermName(name));
    }

    public static Trees.Ident Ident(SymbolTable $this, Symbols.Symbol sym) {
        return (Trees.Ident)new Trees.Ident($this, sym.name()).setSymbol(sym);
    }

    public static Trees.Block Block(SymbolTable $this, Seq stats) {
        block7: {
            Trees.Block block;
            block3: {
                Trees.Block block2;
                block5: {
                    block6: {
                        block4: {
                            Trees.Block block3;
                            Trees.Tree b;
                            block2: {
                                if (!stats.isEmpty()) break block2;
                                block = new Trees.Block($this, Nil$.MODULE$, new Trees.Literal($this, new Constants.Constant($this, BoxedUnit.UNIT)));
                                break block3;
                            }
                            Some<Seq> some = Seq$.MODULE$.unapplySeq(stats);
                            if (some.isEmpty() || some.get() == null || ((SeqLike)some.get()).lengthCompare(1) != 0 || !((b = (Trees.Tree)((SeqLike)some.get()).apply(0)) instanceof Trees.Block)) break block4;
                            block2 = block3 = (Trees.Block)b;
                            break block5;
                        }
                        Some<Seq> some = Seq$.MODULE$.unapplySeq(stats);
                        if (some.isEmpty() || some.get() == null || ((SeqLike)some.get()).lengthCompare(1) != 0) break block6;
                        block2 = new Trees.Block($this, stats.toList(), new Trees.Literal($this, new Constants.Constant($this, BoxedUnit.UNIT)));
                        break block5;
                    }
                    Some<Seq> some = Seq$.MODULE$.unapplySeq(stats);
                    if (some.isEmpty() || some.get() == null || ((SeqLike)some.get()).lengthCompare(1) < 0) break block7;
                    block2 = new Trees.Block($this, ((TraversableOnce)stats.init()).toList(), (Trees.Tree)stats.last());
                }
                block = block2;
            }
            return block;
        }
        throw new MatchError(stats);
    }

    public static Symbols.Symbol typeTreeSymbol(SymbolTable $this, Trees.TypeTree tree) {
        return tree.tpe() == null ? null : tree.tpe().typeSymbol();
    }

    public static void itraverse(SymbolTable $this, Trees.Traverser traverser, Trees.Tree tree) {
        if (tree.canHaveAttrs()) {
            if (tree instanceof Trees.PackageDef) {
                Trees.PackageDef packageDef = (Trees.PackageDef)tree;
                traverser.traverse(packageDef.pid());
                traverser.traverseStats(packageDef.stats(), Trees$class.mclass($this, tree.symbol()));
            } else if (tree instanceof Trees.ModuleDef) {
                Trees.ModuleDef moduleDef = (Trees.ModuleDef)tree;
                Trees$class.traverseMemberDef$1($this, moduleDef, Trees$class.mclass($this, tree.symbol()), traverser);
            } else if (tree instanceof Trees.MemberDef) {
                Trees.MemberDef memberDef = (Trees.MemberDef)tree;
                Trees$class.traverseMemberDef$1($this, memberDef, tree.symbol(), traverser);
            } else if (tree instanceof Trees.Function) {
                Trees.Function function = (Trees.Function)tree;
                traverser.atOwner(tree.symbol(), (Function0<BoxedUnit>)((Object)new Serializable($this, function, traverser){
                    public static final long serialVersionUID = 0L;
                    private final Trees.Function x3$1;
                    private final Trees.Traverser traverser$1;

                    public final void apply() {
                        this.apply$mcV$sp();
                    }

                    public void apply$mcV$sp() {
                        this.traverser$1.traverseParams(this.x3$1.vparams());
                        this.traverser$1.traverse(this.x3$1.body());
                    }
                    {
                        this.x3$1 = x3$1;
                        this.traverser$1 = traverser$1;
                    }
                }));
            } else {
                Trees$class.traverseComponents$1($this, traverser, tree);
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Trees.Tree itransform(SymbolTable $this, Trees.Transformer transformer, Trees.Tree tree) {
        Trees.InternalTreeCopierOps treeCopy = (Trees.InternalTreeCopierOps)transformer.treeCopy();
        if (tree instanceof Trees.Ident) {
            Trees.Ident ident = (Trees.Ident)tree;
            return (Trees.Tree)((Object)treeCopy.Ident(tree, ident.name()));
        }
        if (tree instanceof Trees.Select) {
            Trees.Select select = (Trees.Select)tree;
            return (Trees.Tree)((Object)treeCopy.Select(tree, transformer.transform(select.qualifier()), select.name()));
        }
        if (tree instanceof Trees.Apply) {
            Trees.Apply apply2 = (Trees.Apply)tree;
            return (Trees.Tree)((Object)treeCopy.Apply(tree, transformer.transform(apply2.fun()), transformer.transformTrees(apply2.args())));
        }
        if (tree instanceof Trees.TypeTree) {
            return (Trees.Tree)((Object)treeCopy.TypeTree(tree));
        }
        if (tree instanceof Trees.Literal) {
            Trees.Literal literal = (Trees.Literal)tree;
            return (Trees.Tree)((Object)treeCopy.Literal(tree, literal.value()));
        }
        if (tree instanceof Trees.This) {
            Trees.This this_ = (Trees.This)tree;
            return (Trees.Tree)((Object)treeCopy.This(tree, this_.qual()));
        }
        if (tree instanceof Trees.ValDef) {
            Trees.ValDef valDef = (Trees.ValDef)tree;
            return transformer.atOwner(tree.symbol(), new Serializable($this, treeCopy, valDef, transformer, tree){
                public static final long serialVersionUID = 0L;
                private final Trees.InternalTreeCopierOps treeCopy$1;
                private final Trees.ValDef x8$1;
                private final Trees.Transformer transformer$1;
                private final Trees.Tree tree$5;

                public final Trees.ValDef apply() {
                    return (Trees.ValDef)this.treeCopy$1.ValDef(this.tree$5, this.transformer$1.transformModifiers(this.x8$1.mods()), this.x8$1.name(), this.transformer$1.transform(this.x8$1.tpt()), this.transformer$1.transform(this.x8$1.rhs()));
                }
                {
                    this.treeCopy$1 = treeCopy$1;
                    this.x8$1 = x8$1;
                    this.transformer$1 = transformer$1;
                    this.tree$5 = tree$5;
                }
            });
        }
        if (tree instanceof Trees.DefDef) {
            Trees.DefDef defDef = (Trees.DefDef)tree;
            return transformer.atOwner(tree.symbol(), new Serializable($this, treeCopy, defDef, transformer, tree){
                public static final long serialVersionUID = 0L;
                private final Trees.InternalTreeCopierOps treeCopy$1;
                private final Trees.DefDef x9$1;
                private final Trees.Transformer transformer$1;
                private final Trees.Tree tree$5;

                public final Trees.DefDef apply() {
                    return (Trees.DefDef)this.treeCopy$1.DefDef(this.tree$5, this.transformer$1.transformModifiers(this.x9$1.mods()), this.x9$1.name(), this.transformer$1.transformTypeDefs(this.x9$1.tparams()), this.transformer$1.transformValDefss(this.x9$1.vparamss()), this.transformer$1.transform(this.x9$1.tpt()), this.transformer$1.transform(this.x9$1.rhs()));
                }
                {
                    this.treeCopy$1 = treeCopy$1;
                    this.x9$1 = x9$1;
                    this.transformer$1 = transformer$1;
                    this.tree$5 = tree$5;
                }
            });
        }
        if (tree instanceof Trees.Block) {
            Trees.Block block = (Trees.Block)tree;
            return (Trees.Tree)((Object)treeCopy.Block(tree, transformer.transformStats(block.stats(), transformer.currentOwner()), transformer.transform(block.expr())));
        }
        if (tree instanceof Trees.If) {
            Trees.If if_ = (Trees.If)tree;
            return (Trees.Tree)((Object)treeCopy.If(tree, transformer.transform(if_.cond()), transformer.transform(if_.thenp()), transformer.transform(if_.elsep())));
        }
        if (tree instanceof Trees.CaseDef) {
            Trees.CaseDef caseDef = (Trees.CaseDef)tree;
            return (Trees.Tree)((Object)treeCopy.CaseDef(tree, transformer.transform(caseDef.pat()), transformer.transform(caseDef.guard()), transformer.transform(caseDef.body())));
        }
        if (tree instanceof Trees.TypeApply) {
            Trees.TypeApply typeApply = (Trees.TypeApply)tree;
            return (Trees.Tree)((Object)treeCopy.TypeApply(tree, transformer.transform(typeApply.fun()), transformer.transformTrees(typeApply.args())));
        }
        if (tree instanceof Trees.AppliedTypeTree) {
            Trees.AppliedTypeTree appliedTypeTree = (Trees.AppliedTypeTree)tree;
            return (Trees.Tree)((Object)treeCopy.AppliedTypeTree(tree, transformer.transform(appliedTypeTree.tpt()), transformer.transformTrees(appliedTypeTree.args())));
        }
        if (tree instanceof Trees.Bind) {
            Trees.Bind bind = (Trees.Bind)tree;
            return (Trees.Tree)((Object)treeCopy.Bind(tree, bind.name(), transformer.transform(bind.body())));
        }
        if (tree instanceof Trees.Function) {
            Trees.Function function = (Trees.Function)tree;
            return transformer.atOwner(tree.symbol(), new Serializable($this, treeCopy, function, transformer, tree){
                public static final long serialVersionUID = 0L;
                private final Trees.InternalTreeCopierOps treeCopy$1;
                private final Trees.Function x16$1;
                private final Trees.Transformer transformer$1;
                private final Trees.Tree tree$5;

                public final Trees.Function apply() {
                    return (Trees.Function)this.treeCopy$1.Function(this.tree$5, this.transformer$1.transformValDefs(this.x16$1.vparams()), this.transformer$1.transform(this.x16$1.body()));
                }
                {
                    this.treeCopy$1 = treeCopy$1;
                    this.x16$1 = x16$1;
                    this.transformer$1 = transformer$1;
                    this.tree$5 = tree$5;
                }
            });
        }
        if (tree instanceof Trees.Match) {
            Trees.Match match = (Trees.Match)tree;
            return (Trees.Tree)((Object)treeCopy.Match(tree, transformer.transform(match.selector()), transformer.transformCaseDefs(match.cases())));
        }
        if (tree instanceof Trees.New) {
            Trees.New new_ = (Trees.New)tree;
            return (Trees.Tree)((Object)treeCopy.New(tree, transformer.transform(new_.tpt())));
        }
        if (tree instanceof Trees.Assign) {
            Trees.Assign assign = (Trees.Assign)tree;
            return (Trees.Tree)((Object)treeCopy.Assign(tree, transformer.transform(assign.lhs()), transformer.transform(assign.rhs())));
        }
        if (tree instanceof Trees.AssignOrNamedArg) {
            Trees.AssignOrNamedArg assignOrNamedArg = (Trees.AssignOrNamedArg)tree;
            return (Trees.Tree)((Object)treeCopy.AssignOrNamedArg(tree, transformer.transform(assignOrNamedArg.lhs()), transformer.transform(assignOrNamedArg.rhs())));
        }
        if (tree instanceof Trees.Try) {
            Trees.Try try_ = (Trees.Try)tree;
            return (Trees.Tree)((Object)treeCopy.Try(tree, transformer.transform(try_.block()), transformer.transformCaseDefs(try_.catches()), transformer.transform(try_.finalizer())));
        }
        if (((Object)$this.EmptyTree()).equals(tree)) {
            return tree;
        }
        if (tree instanceof Trees.Throw) {
            Trees.Throw throw_ = (Trees.Throw)tree;
            return (Trees.Tree)((Object)treeCopy.Throw(tree, transformer.transform(throw_.expr())));
        }
        if (tree instanceof Trees.Super) {
            Trees.Super super_ = (Trees.Super)tree;
            return (Trees.Tree)((Object)treeCopy.Super(tree, transformer.transform(super_.qual()), super_.mix()));
        }
        if (tree instanceof Trees.TypeBoundsTree) {
            Trees.TypeBoundsTree typeBoundsTree = (Trees.TypeBoundsTree)tree;
            return (Trees.Tree)((Object)treeCopy.TypeBoundsTree(tree, transformer.transform(typeBoundsTree.lo()), transformer.transform(typeBoundsTree.hi())));
        }
        if (tree instanceof Trees.Typed) {
            Trees.Typed typed = (Trees.Typed)tree;
            return (Trees.Tree)((Object)treeCopy.Typed(tree, transformer.transform(typed.expr()), transformer.transform(typed.tpt())));
        }
        if (tree instanceof Trees.Import) {
            Trees.Import import_ = (Trees.Import)tree;
            return (Trees.Tree)((Object)treeCopy.Import(tree, transformer.transform(import_.expr()), import_.selectors()));
        }
        if (tree instanceof Trees.Template) {
            Trees.Template template = (Trees.Template)tree;
            return (Trees.Tree)((Object)treeCopy.Template(tree, transformer.transformTrees(template.parents()), transformer.transformValDef(template.self()), transformer.transformStats(template.body(), tree.symbol())));
        }
        if (tree instanceof Trees.ClassDef) {
            Trees.ClassDef classDef = (Trees.ClassDef)tree;
            return transformer.atOwner(tree.symbol(), new Serializable($this, treeCopy, classDef, transformer, tree){
                public static final long serialVersionUID = 0L;
                private final Trees.InternalTreeCopierOps treeCopy$1;
                private final Trees.ClassDef x28$1;
                private final Trees.Transformer transformer$1;
                private final Trees.Tree tree$5;

                public final Trees.ClassDef apply() {
                    return (Trees.ClassDef)this.treeCopy$1.ClassDef(this.tree$5, this.transformer$1.transformModifiers(this.x28$1.mods()), this.x28$1.name(), this.transformer$1.transformTypeDefs(this.x28$1.tparams()), this.transformer$1.transformTemplate(this.x28$1.impl()));
                }
                {
                    this.treeCopy$1 = treeCopy$1;
                    this.x28$1 = x28$1;
                    this.transformer$1 = transformer$1;
                    this.tree$5 = tree$5;
                }
            });
        }
        if (tree instanceof Trees.ModuleDef) {
            Trees.ModuleDef moduleDef = (Trees.ModuleDef)tree;
            return transformer.atOwner(Trees$class.mclass($this, tree.symbol()), new Serializable($this, treeCopy, moduleDef, transformer, tree){
                public static final long serialVersionUID = 0L;
                private final Trees.InternalTreeCopierOps treeCopy$1;
                private final Trees.ModuleDef x29$1;
                private final Trees.Transformer transformer$1;
                private final Trees.Tree tree$5;

                public final Trees.ModuleDef apply() {
                    return (Trees.ModuleDef)this.treeCopy$1.ModuleDef(this.tree$5, this.transformer$1.transformModifiers(this.x29$1.mods()), this.x29$1.name(), this.transformer$1.transformTemplate(this.x29$1.impl()));
                }
                {
                    this.treeCopy$1 = treeCopy$1;
                    this.x29$1 = x29$1;
                    this.transformer$1 = transformer$1;
                    this.tree$5 = tree$5;
                }
            });
        }
        if (tree instanceof Trees.TypeDef) {
            Trees.TypeDef typeDef = (Trees.TypeDef)tree;
            return transformer.atOwner(tree.symbol(), new Serializable($this, treeCopy, typeDef, transformer, tree){
                public static final long serialVersionUID = 0L;
                private final Trees.InternalTreeCopierOps treeCopy$1;
                private final Trees.TypeDef x30$1;
                private final Trees.Transformer transformer$1;
                private final Trees.Tree tree$5;

                public final Trees.TypeDef apply() {
                    return (Trees.TypeDef)this.treeCopy$1.TypeDef(this.tree$5, this.transformer$1.transformModifiers(this.x30$1.mods()), this.x30$1.name(), this.transformer$1.transformTypeDefs(this.x30$1.tparams()), this.transformer$1.transform(this.x30$1.rhs()));
                }
                {
                    this.treeCopy$1 = treeCopy$1;
                    this.x30$1 = x30$1;
                    this.transformer$1 = transformer$1;
                    this.tree$5 = tree$5;
                }
            });
        }
        if (tree instanceof Trees.LabelDef) {
            Trees.LabelDef labelDef = (Trees.LabelDef)tree;
            return (Trees.Tree)((Object)treeCopy.LabelDef(tree, labelDef.name(), transformer.transformIdents(labelDef.params()), transformer.transform(labelDef.rhs())));
        }
        if (tree instanceof Trees.PackageDef) {
            Trees.PackageDef packageDef = (Trees.PackageDef)tree;
            return (Trees.Tree)((Object)treeCopy.PackageDef(tree, (Trees.RefTree)transformer.transform(packageDef.pid()), transformer.atOwner(Trees$class.mclass($this, tree.symbol()), new Serializable($this, packageDef, transformer){
                public static final long serialVersionUID = 0L;
                private final Trees.PackageDef x32$1;
                private final Trees.Transformer transformer$1;

                public final List<Trees.Tree> apply() {
                    return this.transformer$1.transformStats(this.x32$1.stats(), this.transformer$1.currentOwner());
                }
                {
                    this.x32$1 = x32$1;
                    this.transformer$1 = transformer$1;
                }
            })));
        }
        if (tree instanceof Trees.Annotated) {
            Trees.Annotated annotated = (Trees.Annotated)tree;
            return (Trees.Tree)((Object)treeCopy.Annotated(tree, transformer.transform(annotated.annot()), transformer.transform(annotated.arg())));
        }
        if (tree instanceof Trees.SingletonTypeTree) {
            Trees.SingletonTypeTree singletonTypeTree = (Trees.SingletonTypeTree)tree;
            return (Trees.Tree)((Object)treeCopy.SingletonTypeTree(tree, transformer.transform(singletonTypeTree.ref())));
        }
        if (tree instanceof Trees.SelectFromTypeTree) {
            Trees.SelectFromTypeTree selectFromTypeTree = (Trees.SelectFromTypeTree)tree;
            return (Trees.Tree)((Object)treeCopy.SelectFromTypeTree(tree, transformer.transform(selectFromTypeTree.qualifier()), selectFromTypeTree.name()));
        }
        if (tree instanceof Trees.CompoundTypeTree) {
            Trees.CompoundTypeTree compoundTypeTree = (Trees.CompoundTypeTree)tree;
            return (Trees.Tree)((Object)treeCopy.CompoundTypeTree(tree, transformer.transformTemplate(compoundTypeTree.templ())));
        }
        if (tree instanceof Trees.ExistentialTypeTree) {
            Trees.ExistentialTypeTree existentialTypeTree = (Trees.ExistentialTypeTree)tree;
            return (Trees.Tree)((Object)treeCopy.ExistentialTypeTree(tree, transformer.transform(existentialTypeTree.tpt()), transformer.transformMemberDefs(existentialTypeTree.whereClauses())));
        }
        if (tree instanceof Trees.Return) {
            Trees.Return return_ = (Trees.Return)tree;
            return (Trees.Tree)((Object)treeCopy.Return(tree, transformer.transform(return_.expr())));
        }
        if (tree instanceof Trees.Alternative) {
            Trees.Alternative alternative = (Trees.Alternative)tree;
            return (Trees.Tree)((Object)treeCopy.Alternative(tree, transformer.transformTrees(alternative.trees())));
        }
        if (tree instanceof Trees.Star) {
            Trees.Star star = (Trees.Star)tree;
            return (Trees.Tree)((Object)treeCopy.Star(tree, transformer.transform(star.elem())));
        }
        if (tree instanceof Trees.UnApply) {
            Trees.UnApply unApply = (Trees.UnApply)tree;
            return (Trees.Tree)((Object)treeCopy.UnApply(tree, transformer.transform(unApply.fun()), transformer.transformTrees(unApply.args())));
        }
        if (tree instanceof Trees.ArrayValue) {
            Trees.ArrayValue arrayValue = (Trees.ArrayValue)tree;
            return treeCopy.ArrayValue(tree, (Trees.Tree)transformer.transform(arrayValue.elemtpt()), transformer.transformTrees(arrayValue.elems()));
        }
        if (tree instanceof Trees.ApplyDynamic) {
            Trees.ApplyDynamic applyDynamic = (Trees.ApplyDynamic)tree;
            return treeCopy.ApplyDynamic(tree, (Trees.Tree)transformer.transform(applyDynamic.qual()), transformer.transformTrees(applyDynamic.args()));
        }
        if (!(tree instanceof Trees.ReferenceToBoxed)) return (Trees.Tree)$this.xtransform(transformer, tree);
        Trees.ReferenceToBoxed referenceToBoxed = (Trees.ReferenceToBoxed)tree;
        Trees.Tree tree2 = (Trees.Tree)transformer.transform(referenceToBoxed.ident());
        if (!(tree2 instanceof Trees.Ident)) throw new MatchError(tree2);
        Trees.Ident ident = (Trees.Ident)tree2;
        return (Trees.Tree)((Object)treeCopy.ReferenceToBoxed(tree, ident));
    }

    private static Symbols.Symbol mclass(SymbolTable $this, Symbols.Symbol sym) {
        return sym == sym.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? sym : ((Symbols.ModuleSymbol)sym.asModule()).moduleClass();
    }

    public static String scala$reflect$internal$Trees$$substituterString(SymbolTable $this, String fromStr, String toStr, List from2, List to2) {
        Predef$ predef$ = Predef$.MODULE$;
        return new StringOps("subst[%s, %s](%s)").format(Predef$.MODULE$.genericWrapArray(new Object[]{fromStr, toStr, ((TraversableOnce)Tuple2Zipped$.MODULE$.map$extension(Tuple2Zipped$Ops$.MODULE$.zipped$extension(Predef$.MODULE$.tuple2ToZippedOps(new Tuple2<List, List>(from2, to2)), Predef$.MODULE$.$conforms(), Predef$.MODULE$.$conforms()), new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final String apply(Object x$10, Object x$11) {
                return new StringBuilder().append((Object)Predef$any2stringadd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(x$10), " -> ")).append(x$11).toString();
            }
        }, List$.MODULE$.canBuildFrom())).mkString(", ")}));
    }

    public static Trees.TreeTypeSubstituter EmptyTreeTypeSubstituter(SymbolTable $this) {
        return new Trees.TreeTypeSubstituter($this, Nil$.MODULE$, Nil$.MODULE$);
    }

    public static Trees.Duplicator scala$reflect$internal$Trees$$duplicator(SymbolTable $this) {
        return new Trees.Duplicator($this, true);
    }

    public static Trees.Tree duplicateAndKeepPositions(SymbolTable $this, Trees.Tree tree) {
        return new Trees.Duplicator($this, false).transform(tree);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Trees.Tree wrappingIntoTerm(SymbolTable $this, Trees.Tree tree0, Function1 op) {
        boolean neededWrapping = !tree0.isTerm();
        Trees.TreeApi tree1 = $this.build().SyntacticBlock().apply((List)Nil$.MODULE$.$colon$colon(tree0));
        Trees.Tree tree = (Trees.Tree)op.apply(tree1);
        if (!(tree instanceof Trees.Block)) return tree;
        Trees.Block block = (Trees.Block)tree;
        if (!(block.stats() instanceof $colon$colon)) return tree;
        $colon$colon $colon$colon = ($colon$colon)block.stats();
        if (!((Object)Nil$.MODULE$).equals($colon$colon.tl$1())) return tree;
        if (!(block.expr() instanceof Trees.Literal)) return tree;
        Trees.Literal literal = (Trees.Literal)block.expr();
        if (literal.value() == null) return tree;
        BoxedUnit boxedUnit = BoxedUnit.UNIT;
        Object object = literal.value().value();
        if (boxedUnit == null) {
            if (object != null) {
                return tree;
            }
        } else if (!((Object)boxedUnit).equals(object)) return tree;
        if (!neededWrapping) return tree;
        return (Trees.Tree)$colon$colon.head();
    }

    public static Trees.DefDef copyDefDef(SymbolTable $this, Trees.Tree tree, Trees.Modifiers mods, Names.Name name, List tparams2, List vparamss, Trees.Tree tpt, Trees.Tree rhs) {
        if (tree instanceof Trees.DefDef) {
            Trees.DefDef defDef = (Trees.DefDef)tree;
            return (Trees.DefDef)$this.treeCopy().DefDef(tree, mods == null ? defDef.mods() : mods, name == null ? defDef.name() : name, tparams2 == null ? defDef.tparams() : tparams2, vparamss == null ? defDef.vparamss() : vparamss, tpt == null ? defDef.tpt() : tpt, rhs == null ? defDef.rhs() : rhs);
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"Not a DefDef: ").append(tree).append((Object)"/").append(tree.getClass()).toString());
    }

    public static Trees.Modifiers copyDefDef$default$2(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Names.Name copyDefDef$default$3(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static List copyDefDef$default$4(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static List copyDefDef$default$5(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Trees.Tree copyDefDef$default$6(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Trees.Tree copyDefDef$default$7(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Trees.ValDef copyValDef(SymbolTable $this, Trees.Tree tree, Trees.Modifiers mods, Names.Name name, Trees.Tree tpt, Trees.Tree rhs) {
        if (tree instanceof Trees.ValDef) {
            Trees.ValDef valDef = (Trees.ValDef)tree;
            return (Trees.ValDef)$this.treeCopy().ValDef(tree, mods == null ? valDef.mods() : mods, name == null ? valDef.name() : name, tpt == null ? valDef.tpt() : tpt, rhs == null ? valDef.rhs() : rhs);
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"Not a ValDef: ").append(tree).append((Object)"/").append(tree.getClass()).toString());
    }

    public static Trees.Modifiers copyValDef$default$2(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Names.Name copyValDef$default$3(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Trees.Tree copyValDef$default$4(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Trees.Tree copyValDef$default$5(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Trees.TypeDef copyTypeDef(SymbolTable $this, Trees.Tree tree, Trees.Modifiers mods, Names.Name name, List tparams2, Trees.Tree rhs) {
        if (tree instanceof Trees.TypeDef) {
            Trees.TypeDef typeDef = (Trees.TypeDef)tree;
            return (Trees.TypeDef)$this.treeCopy().TypeDef(tree, mods == null ? typeDef.mods() : mods, name == null ? typeDef.name() : name, tparams2 == null ? typeDef.tparams() : tparams2, rhs == null ? typeDef.rhs() : rhs);
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"Not a TypeDef: ").append(tree).append((Object)"/").append(tree.getClass()).toString());
    }

    public static Trees.Modifiers copyTypeDef$default$2(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Names.Name copyTypeDef$default$3(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static List copyTypeDef$default$4(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Trees.Tree copyTypeDef$default$5(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Trees.ClassDef copyClassDef(SymbolTable $this, Trees.Tree tree, Trees.Modifiers mods, Names.Name name, List tparams2, Trees.Template impl) {
        if (tree instanceof Trees.ClassDef) {
            Trees.ClassDef classDef = (Trees.ClassDef)tree;
            return (Trees.ClassDef)$this.treeCopy().ClassDef(tree, mods == null ? classDef.mods() : mods, name == null ? classDef.name() : name, tparams2 == null ? classDef.tparams() : tparams2, impl == null ? classDef.impl() : impl);
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"Not a ClassDef: ").append(tree).append((Object)"/").append(tree.getClass()).toString());
    }

    public static Trees.Modifiers copyClassDef$default$2(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Names.Name copyClassDef$default$3(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static List copyClassDef$default$4(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Trees.Template copyClassDef$default$5(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Trees.ModuleDef copyModuleDef(SymbolTable $this, Trees.Tree tree, Trees.Modifiers mods, Names.Name name, Trees.Template impl) {
        if (tree instanceof Trees.ModuleDef) {
            Trees.ModuleDef moduleDef = (Trees.ModuleDef)tree;
            return (Trees.ModuleDef)$this.treeCopy().ModuleDef(tree, mods == null ? moduleDef.mods() : mods, name == null ? moduleDef.name() : name, impl == null ? moduleDef.impl() : impl);
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"Not a ModuleDef: ").append(tree).append((Object)"/").append(tree.getClass()).toString());
    }

    public static Trees.Modifiers copyModuleDef$default$2(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Names.Name copyModuleDef$default$3(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Trees.Template copyModuleDef$default$4(SymbolTable $this, Trees.Tree tree) {
        return null;
    }

    public static Trees.DefDef deriveDefDef(SymbolTable $this, Trees.Tree ddef, Function1 applyToRhs) {
        if (ddef instanceof Trees.DefDef) {
            Trees.DefDef defDef = (Trees.DefDef)ddef;
            return (Trees.DefDef)$this.treeCopy().DefDef(ddef, defDef.mods(), defDef.name(), defDef.tparams(), defDef.vparamss(), defDef.tpt(), (Trees.TreeApi)applyToRhs.apply(defDef.rhs()));
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"Not a DefDef: ").append(ddef).append((Object)"/").append(ddef.getClass()).toString());
    }

    public static Trees.ValDef deriveValDef(SymbolTable $this, Trees.Tree vdef, Function1 applyToRhs) {
        if (vdef instanceof Trees.ValDef) {
            Trees.ValDef valDef = (Trees.ValDef)vdef;
            return (Trees.ValDef)$this.treeCopy().ValDef(vdef, valDef.mods(), valDef.name(), valDef.tpt(), (Trees.TreeApi)applyToRhs.apply(valDef.rhs()));
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"Not a ValDef: ").append(vdef).append((Object)"/").append(vdef.getClass()).toString());
    }

    public static Trees.Template deriveTemplate(SymbolTable $this, Trees.Tree templ, Function1 applyToBody) {
        if (templ instanceof Trees.Template) {
            Trees.Template template = (Trees.Template)templ;
            return (Trees.Template)$this.treeCopy().Template(templ, template.parents(), template.self(), (List)applyToBody.apply(template.body()));
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"Not a Template: ").append(templ).append((Object)"/").append(templ.getClass()).toString());
    }

    public static Trees.ClassDef deriveClassDef(SymbolTable $this, Trees.Tree cdef, Function1 applyToImpl) {
        if (cdef instanceof Trees.ClassDef) {
            Trees.ClassDef classDef = (Trees.ClassDef)cdef;
            return (Trees.ClassDef)$this.treeCopy().ClassDef(cdef, classDef.mods(), classDef.name(), classDef.tparams(), (Trees.TemplateApi)applyToImpl.apply(classDef.impl()));
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"Not a ClassDef: ").append(cdef).append((Object)"/").append(cdef.getClass()).toString());
    }

    public static Trees.ModuleDef deriveModuleDef(SymbolTable $this, Trees.Tree mdef, Function1 applyToImpl) {
        if (mdef instanceof Trees.ModuleDef) {
            Trees.ModuleDef moduleDef = (Trees.ModuleDef)mdef;
            return (Trees.ModuleDef)$this.treeCopy().ModuleDef(mdef, moduleDef.mods(), moduleDef.name(), (Trees.TemplateApi)applyToImpl.apply(moduleDef.impl()));
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"Not a ModuleDef: ").append(mdef).append((Object)"/").append(mdef.getClass()).toString());
    }

    public static Trees.CaseDef deriveCaseDef(SymbolTable $this, Trees.Tree cdef, Function1 applyToBody) {
        if (cdef instanceof Trees.CaseDef) {
            Trees.CaseDef caseDef = (Trees.CaseDef)cdef;
            return (Trees.CaseDef)$this.treeCopy().CaseDef(cdef, caseDef.pat(), caseDef.guard(), (Trees.TreeApi)applyToBody.apply(caseDef.body()));
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"Not a CaseDef: ").append(cdef).append((Object)"/").append(cdef.getClass()).toString());
    }

    public static Trees.LabelDef deriveLabelDef(SymbolTable $this, Trees.Tree ldef, Function1 applyToRhs) {
        if (ldef instanceof Trees.LabelDef) {
            Trees.LabelDef labelDef = (Trees.LabelDef)ldef;
            return (Trees.LabelDef)$this.treeCopy().LabelDef(ldef, labelDef.name(), labelDef.params(), (Trees.TreeApi)applyToRhs.apply(labelDef.rhs()));
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"Not a LabelDef: ").append(ldef).append((Object)"/").append(ldef.getClass()).toString());
    }

    public static Trees.Function deriveFunction(SymbolTable $this, Trees.Tree func, Function1 applyToRhs) {
        if (func instanceof Trees.Function) {
            Trees.Function function = (Trees.Function)func;
            return (Trees.Function)$this.treeCopy().Function(func, function.vparams(), (Trees.TreeApi)applyToRhs.apply(function.body()));
        }
        throw package$.MODULE$.error(new StringBuilder().append((Object)"Not a Function: ").append(func).append((Object)"/").append(func.getClass()).toString());
    }

    private static final void traverseMemberDef$1(SymbolTable $this, Trees.MemberDef md, Symbols.Symbol owner2, Trees.Traverser traverser$1) {
        traverser$1.atOwner(owner2, (Function0<BoxedUnit>)((Object)new Serializable($this, md, traverser$1){
            public static final long serialVersionUID = 0L;
            private final Trees.MemberDef md$1;
            private final Trees.Traverser traverser$1;

            public final void apply() {
                this.apply$mcV$sp();
            }

            public void apply$mcV$sp() {
                Trees.MemberDef memberDef;
                block7: {
                    block3: {
                        block6: {
                            block5: {
                                block4: {
                                    block2: {
                                        this.traverser$1.traverseModifiers(this.md$1.mods());
                                        this.traverser$1.traverseName(this.md$1.name());
                                        memberDef = this.md$1;
                                        if (!(memberDef instanceof Trees.ClassDef)) break block2;
                                        Trees.ClassDef classDef = (Trees.ClassDef)memberDef;
                                        this.traverser$1.traverseParams(classDef.tparams());
                                        this.traverser$1.traverse(classDef.impl());
                                        break block3;
                                    }
                                    if (!(memberDef instanceof Trees.ModuleDef)) break block4;
                                    Trees.ModuleDef moduleDef = (Trees.ModuleDef)memberDef;
                                    this.traverser$1.traverse(moduleDef.impl());
                                    break block3;
                                }
                                if (!(memberDef instanceof Trees.ValDef)) break block5;
                                Trees.ValDef valDef = (Trees.ValDef)memberDef;
                                this.traverser$1.traverseTypeAscription(valDef.tpt());
                                this.traverser$1.traverse(valDef.rhs());
                                break block3;
                            }
                            if (!(memberDef instanceof Trees.TypeDef)) break block6;
                            Trees.TypeDef typeDef = (Trees.TypeDef)memberDef;
                            this.traverser$1.traverseParams(typeDef.tparams());
                            this.traverser$1.traverse(typeDef.rhs());
                            break block3;
                        }
                        if (!(memberDef instanceof Trees.DefDef)) break block7;
                        Trees.DefDef defDef = (Trees.DefDef)memberDef;
                        this.traverser$1.traverseParams(defDef.tparams());
                        this.traverser$1.traverseParamss(defDef.vparamss());
                        this.traverser$1.traverseTypeAscription(defDef.tpt());
                        this.traverser$1.traverse(defDef.rhs());
                    }
                    return;
                }
                throw new MatchError(memberDef);
            }
            {
                this.md$1 = md$1;
                this.traverser$1 = traverser$1;
            }
        }));
    }

    private static final void traverseComponents$1(SymbolTable $this, Trees.Traverser traverser$1, Trees.Tree tree$4) {
        if (tree$4 instanceof Trees.LabelDef) {
            Trees.LabelDef labelDef = (Trees.LabelDef)tree$4;
            traverser$1.traverseName(labelDef.name());
            traverser$1.traverseParams(labelDef.params());
            traverser$1.traverse(labelDef.rhs());
        } else if (tree$4 instanceof Trees.Import) {
            Trees.Import import_ = (Trees.Import)tree$4;
            traverser$1.traverse(import_.expr());
            Serializable serializable = new Serializable($this, traverser$1){
                public static final long serialVersionUID = 0L;
                public final Trees.Traverser traverser$1;

                public final void apply(Trees.ImportSelector sel) {
                    this.traverser$1.traverseImportSelector(sel);
                }
                {
                    this.traverser$1 = traverser$1;
                }
            };
            List list2 = import_.selectors();
            while (!((AbstractIterable)list2).isEmpty()) {
                Trees.ImportSelector importSelector = (Trees.ImportSelector)((AbstractIterable)list2).head();
                serializable.traverser$1.traverseImportSelector(importSelector);
                list2 = (List)list2.tail();
            }
        } else if (tree$4 instanceof Trees.Annotated) {
            Trees.Annotated annotated = (Trees.Annotated)tree$4;
            traverser$1.traverse(annotated.annot());
            traverser$1.traverse(annotated.arg());
        } else if (tree$4 instanceof Trees.Template) {
            Trees.Template template = (Trees.Template)tree$4;
            traverser$1.traverseParents(template.parents());
            traverser$1.traverseSelfType(template.self());
            traverser$1.traverseStats(template.body(), tree$4.symbol());
        } else if (tree$4 instanceof Trees.Block) {
            Trees.Block block = (Trees.Block)tree$4;
            traverser$1.traverseTrees(block.stats());
            traverser$1.traverse(block.expr());
        } else if (tree$4 instanceof Trees.CaseDef) {
            Trees.CaseDef caseDef = (Trees.CaseDef)tree$4;
            traverser$1.traversePattern(caseDef.pat());
            traverser$1.traverseGuard(caseDef.guard());
            traverser$1.traverse(caseDef.body());
        } else if (tree$4 instanceof Trees.Alternative) {
            Trees.Alternative alternative = (Trees.Alternative)tree$4;
            traverser$1.traverseTrees(alternative.trees());
        } else if (tree$4 instanceof Trees.Star) {
            Trees.Star star = (Trees.Star)tree$4;
            traverser$1.traverse(star.elem());
        } else if (tree$4 instanceof Trees.Bind) {
            Trees.Bind bind = (Trees.Bind)tree$4;
            traverser$1.traverseName(bind.name());
            traverser$1.traverse(bind.body());
        } else if (tree$4 instanceof Trees.UnApply) {
            Trees.UnApply unApply = (Trees.UnApply)tree$4;
            traverser$1.traverse(unApply.fun());
            traverser$1.traverseTrees(unApply.args());
        } else if (tree$4 instanceof Trees.ArrayValue) {
            Trees.ArrayValue arrayValue = (Trees.ArrayValue)tree$4;
            traverser$1.traverse(arrayValue.elemtpt());
            traverser$1.traverseTrees(arrayValue.elems());
        } else if (tree$4 instanceof Trees.Assign) {
            Trees.Assign assign = (Trees.Assign)tree$4;
            traverser$1.traverse(assign.lhs());
            traverser$1.traverse(assign.rhs());
        } else if (tree$4 instanceof Trees.AssignOrNamedArg) {
            Trees.AssignOrNamedArg assignOrNamedArg = (Trees.AssignOrNamedArg)tree$4;
            traverser$1.traverse(assignOrNamedArg.lhs());
            traverser$1.traverse(assignOrNamedArg.rhs());
        } else if (tree$4 instanceof Trees.If) {
            Trees.If if_ = (Trees.If)tree$4;
            traverser$1.traverse(if_.cond());
            traverser$1.traverse(if_.thenp());
            traverser$1.traverse(if_.elsep());
        } else if (tree$4 instanceof Trees.Match) {
            Trees.Match match = (Trees.Match)tree$4;
            traverser$1.traverse(match.selector());
            traverser$1.traverseCases(match.cases());
        } else if (tree$4 instanceof Trees.Return) {
            Trees.Return return_ = (Trees.Return)tree$4;
            traverser$1.traverse(return_.expr());
        } else if (tree$4 instanceof Trees.Try) {
            Trees.Try try_ = (Trees.Try)tree$4;
            traverser$1.traverse(try_.block());
            traverser$1.traverseCases(try_.catches());
            traverser$1.traverse(try_.finalizer());
        } else if (tree$4 instanceof Trees.Throw) {
            Trees.Throw throw_ = (Trees.Throw)tree$4;
            traverser$1.traverse(throw_.expr());
        } else if (tree$4 instanceof Trees.New) {
            Trees.New new_ = (Trees.New)tree$4;
            traverser$1.traverse(new_.tpt());
        } else if (tree$4 instanceof Trees.Typed) {
            Trees.Typed typed = (Trees.Typed)tree$4;
            traverser$1.traverse(typed.expr());
            traverser$1.traverseTypeAscription(typed.tpt());
        } else if (tree$4 instanceof Trees.TypeApply) {
            Trees.TypeApply typeApply = (Trees.TypeApply)tree$4;
            traverser$1.traverse(typeApply.fun());
            traverser$1.traverseTypeArgs(typeApply.args());
        } else if (tree$4 instanceof Trees.Apply) {
            Trees.Apply apply2 = (Trees.Apply)tree$4;
            traverser$1.traverse(apply2.fun());
            traverser$1.traverseTrees(apply2.args());
        } else if (tree$4 instanceof Trees.ApplyDynamic) {
            Trees.ApplyDynamic applyDynamic = (Trees.ApplyDynamic)tree$4;
            traverser$1.traverse(applyDynamic.qual());
            traverser$1.traverseTrees(applyDynamic.args());
        } else if (tree$4 instanceof Trees.Super) {
            Trees.Super super_ = (Trees.Super)tree$4;
            traverser$1.traverse(super_.qual());
            traverser$1.traverseName(super_.mix());
        } else if (tree$4 instanceof Trees.This) {
            Trees.This this_ = (Trees.This)tree$4;
            traverser$1.traverseName(this_.qual());
        } else if (tree$4 instanceof Trees.Select) {
            Trees.Select select = (Trees.Select)tree$4;
            traverser$1.traverse(select.qualifier());
            traverser$1.traverseName(select.name());
        } else if (tree$4 instanceof Trees.Ident) {
            Trees.Ident ident = (Trees.Ident)tree$4;
            traverser$1.traverseName(ident.name());
        } else if (tree$4 instanceof Trees.ReferenceToBoxed) {
            Trees.ReferenceToBoxed referenceToBoxed = (Trees.ReferenceToBoxed)tree$4;
            traverser$1.traverse(referenceToBoxed.ident());
        } else if (tree$4 instanceof Trees.Literal) {
            Trees.Literal literal = (Trees.Literal)tree$4;
            traverser$1.traverseConstant(literal.value());
        } else if (tree$4 instanceof Trees.TypeTree) {
        } else if (tree$4 instanceof Trees.SingletonTypeTree) {
            Trees.SingletonTypeTree singletonTypeTree = (Trees.SingletonTypeTree)tree$4;
            traverser$1.traverse(singletonTypeTree.ref());
        } else if (tree$4 instanceof Trees.SelectFromTypeTree) {
            Trees.SelectFromTypeTree selectFromTypeTree = (Trees.SelectFromTypeTree)tree$4;
            traverser$1.traverse(selectFromTypeTree.qualifier());
            traverser$1.traverseName(selectFromTypeTree.name());
        } else if (tree$4 instanceof Trees.CompoundTypeTree) {
            Trees.CompoundTypeTree compoundTypeTree = (Trees.CompoundTypeTree)tree$4;
            traverser$1.traverse(compoundTypeTree.templ());
        } else if (tree$4 instanceof Trees.AppliedTypeTree) {
            Trees.AppliedTypeTree appliedTypeTree = (Trees.AppliedTypeTree)tree$4;
            traverser$1.traverse(appliedTypeTree.tpt());
            traverser$1.traverseTypeArgs(appliedTypeTree.args());
        } else if (tree$4 instanceof Trees.TypeBoundsTree) {
            Trees.TypeBoundsTree typeBoundsTree = (Trees.TypeBoundsTree)tree$4;
            traverser$1.traverse(typeBoundsTree.lo());
            traverser$1.traverse(typeBoundsTree.hi());
        } else if (tree$4 instanceof Trees.ExistentialTypeTree) {
            Trees.ExistentialTypeTree existentialTypeTree = (Trees.ExistentialTypeTree)tree$4;
            traverser$1.traverse(existentialTypeTree.tpt());
            traverser$1.traverseTrees(existentialTypeTree.whereClauses());
        } else {
            $this.xtraverse(traverser$1, tree$4);
        }
    }

    public static void $init$(SymbolTable $this) {
        $this.nodeCount_$eq(0);
        $this.scala$reflect$internal$Trees$_setter_$ModifiersTag_$eq(ClassTag$.MODULE$.apply(Trees.Modifiers.class));
        $this.scala$reflect$internal$Trees$_setter_$AlternativeTag_$eq(ClassTag$.MODULE$.apply(Trees.Alternative.class));
        $this.scala$reflect$internal$Trees$_setter_$AnnotatedTag_$eq(ClassTag$.MODULE$.apply(Trees.Annotated.class));
        $this.scala$reflect$internal$Trees$_setter_$AppliedTypeTreeTag_$eq(ClassTag$.MODULE$.apply(Trees.AppliedTypeTree.class));
        $this.scala$reflect$internal$Trees$_setter_$ApplyTag_$eq(ClassTag$.MODULE$.apply(Trees.Apply.class));
        $this.scala$reflect$internal$Trees$_setter_$AssignOrNamedArgTag_$eq(ClassTag$.MODULE$.apply(Trees.AssignOrNamedArg.class));
        $this.scala$reflect$internal$Trees$_setter_$AssignTag_$eq(ClassTag$.MODULE$.apply(Trees.Assign.class));
        $this.scala$reflect$internal$Trees$_setter_$BindTag_$eq(ClassTag$.MODULE$.apply(Trees.Bind.class));
        $this.scala$reflect$internal$Trees$_setter_$BlockTag_$eq(ClassTag$.MODULE$.apply(Trees.Block.class));
        $this.scala$reflect$internal$Trees$_setter_$CaseDefTag_$eq(ClassTag$.MODULE$.apply(Trees.CaseDef.class));
        $this.scala$reflect$internal$Trees$_setter_$ClassDefTag_$eq(ClassTag$.MODULE$.apply(Trees.ClassDef.class));
        $this.scala$reflect$internal$Trees$_setter_$CompoundTypeTreeTag_$eq(ClassTag$.MODULE$.apply(Trees.CompoundTypeTree.class));
        $this.scala$reflect$internal$Trees$_setter_$DefDefTag_$eq(ClassTag$.MODULE$.apply(Trees.DefDef.class));
        $this.scala$reflect$internal$Trees$_setter_$DefTreeTag_$eq(ClassTag$.MODULE$.apply(Trees.DefTree.class));
        $this.scala$reflect$internal$Trees$_setter_$ExistentialTypeTreeTag_$eq(ClassTag$.MODULE$.apply(Trees.ExistentialTypeTree.class));
        $this.scala$reflect$internal$Trees$_setter_$FunctionTag_$eq(ClassTag$.MODULE$.apply(Trees.Function.class));
        $this.scala$reflect$internal$Trees$_setter_$GenericApplyTag_$eq(ClassTag$.MODULE$.apply(Trees.GenericApply.class));
        $this.scala$reflect$internal$Trees$_setter_$IdentTag_$eq(ClassTag$.MODULE$.apply(Trees.Ident.class));
        $this.scala$reflect$internal$Trees$_setter_$IfTag_$eq(ClassTag$.MODULE$.apply(Trees.If.class));
        $this.scala$reflect$internal$Trees$_setter_$ImplDefTag_$eq(ClassTag$.MODULE$.apply(Trees.ImplDef.class));
        $this.scala$reflect$internal$Trees$_setter_$ImportSelectorTag_$eq(ClassTag$.MODULE$.apply(Trees.ImportSelector.class));
        $this.scala$reflect$internal$Trees$_setter_$ImportTag_$eq(ClassTag$.MODULE$.apply(Trees.Import.class));
        $this.scala$reflect$internal$Trees$_setter_$LabelDefTag_$eq(ClassTag$.MODULE$.apply(Trees.LabelDef.class));
        $this.scala$reflect$internal$Trees$_setter_$LiteralTag_$eq(ClassTag$.MODULE$.apply(Trees.Literal.class));
        $this.scala$reflect$internal$Trees$_setter_$MatchTag_$eq(ClassTag$.MODULE$.apply(Trees.Match.class));
        $this.scala$reflect$internal$Trees$_setter_$MemberDefTag_$eq(ClassTag$.MODULE$.apply(Trees.MemberDef.class));
        $this.scala$reflect$internal$Trees$_setter_$ModuleDefTag_$eq(ClassTag$.MODULE$.apply(Trees.ModuleDef.class));
        $this.scala$reflect$internal$Trees$_setter_$NameTreeTag_$eq(ClassTag$.MODULE$.apply(Trees.NameTree.class));
        $this.scala$reflect$internal$Trees$_setter_$NewTag_$eq(ClassTag$.MODULE$.apply(Trees.New.class));
        $this.scala$reflect$internal$Trees$_setter_$PackageDefTag_$eq(ClassTag$.MODULE$.apply(Trees.PackageDef.class));
        $this.scala$reflect$internal$Trees$_setter_$ReferenceToBoxedTag_$eq(ClassTag$.MODULE$.apply(Trees.ReferenceToBoxed.class));
        $this.scala$reflect$internal$Trees$_setter_$RefTreeTag_$eq(ClassTag$.MODULE$.apply(Trees.RefTree.class));
        $this.scala$reflect$internal$Trees$_setter_$ReturnTag_$eq(ClassTag$.MODULE$.apply(Trees.Return.class));
        $this.scala$reflect$internal$Trees$_setter_$SelectFromTypeTreeTag_$eq(ClassTag$.MODULE$.apply(Trees.SelectFromTypeTree.class));
        $this.scala$reflect$internal$Trees$_setter_$SelectTag_$eq(ClassTag$.MODULE$.apply(Trees.Select.class));
        $this.scala$reflect$internal$Trees$_setter_$SingletonTypeTreeTag_$eq(ClassTag$.MODULE$.apply(Trees.SingletonTypeTree.class));
        $this.scala$reflect$internal$Trees$_setter_$StarTag_$eq(ClassTag$.MODULE$.apply(Trees.Star.class));
        $this.scala$reflect$internal$Trees$_setter_$SuperTag_$eq(ClassTag$.MODULE$.apply(Trees.Super.class));
        $this.scala$reflect$internal$Trees$_setter_$SymTreeTag_$eq(ClassTag$.MODULE$.apply(Trees.SymTree.class));
        $this.scala$reflect$internal$Trees$_setter_$TemplateTag_$eq(ClassTag$.MODULE$.apply(Trees.Template.class));
        $this.scala$reflect$internal$Trees$_setter_$TermTreeTag_$eq(ClassTag$.MODULE$.apply(Trees.TermTree.class));
        $this.scala$reflect$internal$Trees$_setter_$ThisTag_$eq(ClassTag$.MODULE$.apply(Trees.This.class));
        $this.scala$reflect$internal$Trees$_setter_$ThrowTag_$eq(ClassTag$.MODULE$.apply(Trees.Throw.class));
        $this.scala$reflect$internal$Trees$_setter_$TreeTag_$eq(ClassTag$.MODULE$.apply(Trees.Tree.class));
        $this.scala$reflect$internal$Trees$_setter_$TryTag_$eq(ClassTag$.MODULE$.apply(Trees.Try.class));
        $this.scala$reflect$internal$Trees$_setter_$TypTreeTag_$eq(ClassTag$.MODULE$.apply(Trees.TypTree.class));
        $this.scala$reflect$internal$Trees$_setter_$TypeApplyTag_$eq(ClassTag$.MODULE$.apply(Trees.TypeApply.class));
        $this.scala$reflect$internal$Trees$_setter_$TypeBoundsTreeTag_$eq(ClassTag$.MODULE$.apply(Trees.TypeBoundsTree.class));
        $this.scala$reflect$internal$Trees$_setter_$TypeDefTag_$eq(ClassTag$.MODULE$.apply(Trees.TypeDef.class));
        $this.scala$reflect$internal$Trees$_setter_$TypeTreeTag_$eq(ClassTag$.MODULE$.apply(Trees.TypeTree.class));
        $this.scala$reflect$internal$Trees$_setter_$TypedTag_$eq(ClassTag$.MODULE$.apply(Trees.Typed.class));
        $this.scala$reflect$internal$Trees$_setter_$UnApplyTag_$eq(ClassTag$.MODULE$.apply(Trees.UnApply.class));
        $this.scala$reflect$internal$Trees$_setter_$ValDefTag_$eq(ClassTag$.MODULE$.apply(Trees.ValDef.class));
        $this.scala$reflect$internal$Trees$_setter_$ValOrDefDefTag_$eq(ClassTag$.MODULE$.apply(Trees.ValOrDefDef.class));
        Serializable serializable = new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SymbolTable $outer;

            public final int apply() {
                return this.$outer.nodeCount();
            }

            public int apply$mcI$sp() {
                return this.$outer.nodeCount();
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
        $this.scala$reflect$internal$Trees$_setter_$treeNodeCount_$eq(new Statistics.View("#created tree nodes", wrappedArray, (Function0<Object>)((Object)serializable)));
    }
}

