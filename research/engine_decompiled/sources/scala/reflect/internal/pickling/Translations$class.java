/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.pickling;

import scala.MatchError;
import scala.Option;
import scala.Predef$;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Names;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.sys.package$;

public abstract class Translations$class {
    public static boolean isTreeSymbolPickled(SymbolTable $this, int code) {
        boolean bl;
        switch (code) {
            default: {
                bl = false;
                break;
            }
            case 32: 
            case 33: 
            case 34: 
            case 35: 
            case 36: {
                bl = true;
                break;
            }
            case 9: 
            case 12: 
            case 18: 
            case 21: 
            case 25: {
                bl = true;
                break;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: {
                bl = true;
            }
        }
        return bl;
    }

    public static boolean isTreeSymbolPickled(SymbolTable $this, Trees.Tree tree) {
        return $this.isTreeSymbolPickled($this.picklerSubTag(tree));
    }

    public static int picklerTag(SymbolTable $this, Object ref) {
        block13: {
            int n;
            block3: {
                block12: {
                    Tuple2 tuple2;
                    boolean bl;
                    block11: {
                        block10: {
                            block9: {
                                block8: {
                                    block7: {
                                        block6: {
                                            block5: {
                                                block4: {
                                                    block2: {
                                                        bl = false;
                                                        tuple2 = null;
                                                        if (!(ref instanceof Types.Type) || ((Types.Type)ref).scala$reflect$internal$Types$Type$$$outer() != $this) break block2;
                                                        Types.Type type = (Types.Type)ref;
                                                        n = $this.picklerTag(type);
                                                        break block3;
                                                    }
                                                    if (!(ref instanceof Symbols.Symbol) || ((Symbols.Symbol)ref).scala$reflect$internal$Symbols$Symbol$$$outer() != $this) break block4;
                                                    Symbols.Symbol symbol = (Symbols.Symbol)ref;
                                                    n = $this.picklerTag(symbol);
                                                    break block3;
                                                }
                                                if (!(ref instanceof Constants.Constant) || ((Constants.Constant)ref).scala$reflect$internal$Constants$Constant$$$outer() != $this) break block5;
                                                Constants.Constant constant = (Constants.Constant)ref;
                                                n = 23 + constant.tag();
                                                break block3;
                                            }
                                            if (!(ref instanceof Trees.Tree) || ((Trees.Tree)ref).scala$reflect$internal$Trees$Tree$$$outer() != $this) break block6;
                                            n = 49;
                                            break block3;
                                        }
                                        if (!(ref instanceof Names.TermName) || ((Names.TermName)ref).scala$reflect$internal$Names$TermName$$$outer() != $this) break block7;
                                        n = 1;
                                        break block3;
                                    }
                                    if (!(ref instanceof Names.TypeName) || ((Names.TypeName)ref).scala$reflect$internal$Names$TypeName$$$outer() != $this) break block8;
                                    n = 2;
                                    break block3;
                                }
                                if (!(ref instanceof AnnotationInfos.ArrayAnnotArg) || ((AnnotationInfos.ArrayAnnotArg)ref).scala$reflect$internal$AnnotationInfos$ArrayAnnotArg$$$outer() != $this) break block9;
                                n = 44;
                                break block3;
                            }
                            if (!(ref instanceof AnnotationInfos.AnnotationInfo) || ((AnnotationInfos.AnnotationInfo)ref).scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer() != $this) break block10;
                            n = 43;
                            break block3;
                        }
                        if (!(ref instanceof Tuple2)) break block11;
                        bl = true;
                        tuple2 = (Tuple2)ref;
                        if (!(tuple2._1() instanceof Symbols.Symbol) || ((Symbols.Symbol)tuple2._1()).scala$reflect$internal$Symbols$Symbol$$$outer() != $this || !(tuple2._2() instanceof AnnotationInfos.AnnotationInfo) || ((AnnotationInfos.AnnotationInfo)tuple2._2()).scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer() != $this) break block11;
                        n = 40;
                        break block3;
                    }
                    if (!bl || !(tuple2._1() instanceof Symbols.Symbol) || ((Symbols.Symbol)tuple2._1()).scala$reflect$internal$Symbols$Symbol$$$outer() != $this || !(tuple2._2() instanceof List)) break block12;
                    n = 41;
                    break block3;
                }
                if (!(ref instanceof Trees.Modifiers) || ((Trees.Modifiers)ref).scala$reflect$internal$Trees$Modifiers$$$outer() != $this) break block13;
                n = 50;
            }
            return n;
        }
        throw package$.MODULE$.error(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"unpicklable entry ", " ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{scala.reflect.internal.util.package$.MODULE$.shortClassOfInstance(ref), ref})));
    }

    public static int picklerTag(SymbolTable $this, Symbols.Symbol sym) {
        block8: {
            int n;
            block3: {
                boolean bl;
                block7: {
                    block6: {
                        boolean bl2;
                        block5: {
                            block4: {
                                block2: {
                                    bl2 = false;
                                    bl = false;
                                    Symbols.NoSymbol noSymbol = $this.NoSymbol();
                                    if (noSymbol != null ? !noSymbol.equals(sym) : sym != null) break block2;
                                    n = 3;
                                    break block3;
                                }
                                if (!(sym instanceof Symbols.ClassSymbol)) break block4;
                                n = 6;
                                break block3;
                            }
                            if (!(sym instanceof Symbols.TypeSymbol)) break block5;
                            bl2 = true;
                            Symbols.TypeSymbol cfr_ignored_0 = (Symbols.TypeSymbol)sym;
                            if (!sym.isAbstractType()) break block5;
                            n = 4;
                            break block3;
                        }
                        if (!bl2) break block6;
                        n = 5;
                        break block3;
                    }
                    if (!(sym instanceof Symbols.TermSymbol)) break block7;
                    bl = true;
                    Symbols.TermSymbol cfr_ignored_1 = (Symbols.TermSymbol)sym;
                    if (!sym.isModule()) break block7;
                    n = 7;
                    break block3;
                }
                if (!bl) break block8;
                n = 8;
            }
            return n;
        }
        throw new MatchError(sym);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int picklerTag(SymbolTable $this, Types.Type tpe) {
        if ($this.NoType().equals(tpe)) {
            return 11;
        }
        if ($this.NoPrefix().equals(tpe)) {
            return 12;
        }
        if (tpe instanceof Types.ThisType) {
            return 13;
        }
        if (tpe instanceof Types.SingleType) {
            return 14;
        }
        if (tpe instanceof Types.SuperType) {
            return 46;
        }
        if (tpe instanceof Types.ConstantType) {
            return 15;
        }
        if (tpe instanceof Types.TypeBounds) {
            return 17;
        }
        if (tpe instanceof Types.TypeRef) {
            return 16;
        }
        if (tpe instanceof Types.RefinedType) {
            return 18;
        }
        if (tpe instanceof Types.ClassInfoType) {
            return 19;
        }
        if (tpe instanceof Types.MethodType) {
            return 20;
        }
        if (tpe instanceof Types.PolyType) {
            return 21;
        }
        if (tpe instanceof Types.NullaryMethodType) {
            return 21;
        }
        if (tpe instanceof Types.ExistentialType) {
            return 48;
        }
        Option<Tuple2<List<AnnotationInfos.AnnotationInfo>, Types.Type>> option = $this.StaticallyAnnotatedType().unapply(tpe);
        if (!option.isEmpty()) return 42;
        if (!(tpe instanceof Types.AnnotatedType)) throw new MatchError(tpe);
        return $this.picklerTag(tpe.underlying());
    }

    public static int picklerSubTag(SymbolTable $this, Trees.Tree tree) {
        block44: {
            int n;
            block3: {
                block43: {
                    block42: {
                        block41: {
                            block40: {
                                block39: {
                                    block38: {
                                        block37: {
                                            block36: {
                                                block35: {
                                                    block34: {
                                                        block33: {
                                                            block32: {
                                                                block31: {
                                                                    block30: {
                                                                        block29: {
                                                                            block28: {
                                                                                block27: {
                                                                                    block26: {
                                                                                        block25: {
                                                                                            block24: {
                                                                                                block23: {
                                                                                                    block22: {
                                                                                                        block21: {
                                                                                                            block20: {
                                                                                                                block19: {
                                                                                                                    block18: {
                                                                                                                        block17: {
                                                                                                                            block16: {
                                                                                                                                block15: {
                                                                                                                                    block14: {
                                                                                                                                        block13: {
                                                                                                                                            block12: {
                                                                                                                                                block11: {
                                                                                                                                                    block10: {
                                                                                                                                                        block9: {
                                                                                                                                                            block8: {
                                                                                                                                                                block7: {
                                                                                                                                                                    block6: {
                                                                                                                                                                        block5: {
                                                                                                                                                                            block4: {
                                                                                                                                                                                block2: {
                                                                                                                                                                                    if (!((Object)$this.EmptyTree()).equals(tree)) break block2;
                                                                                                                                                                                    n = 1;
                                                                                                                                                                                    break block3;
                                                                                                                                                                                }
                                                                                                                                                                                if (!(tree instanceof Trees.PackageDef)) break block4;
                                                                                                                                                                                n = 2;
                                                                                                                                                                                break block3;
                                                                                                                                                                            }
                                                                                                                                                                            if (!(tree instanceof Trees.ClassDef)) break block5;
                                                                                                                                                                            n = 3;
                                                                                                                                                                            break block3;
                                                                                                                                                                        }
                                                                                                                                                                        if (!(tree instanceof Trees.ModuleDef)) break block6;
                                                                                                                                                                        n = 4;
                                                                                                                                                                        break block3;
                                                                                                                                                                    }
                                                                                                                                                                    if (!(tree instanceof Trees.ValDef)) break block7;
                                                                                                                                                                    n = 5;
                                                                                                                                                                    break block3;
                                                                                                                                                                }
                                                                                                                                                                if (!(tree instanceof Trees.DefDef)) break block8;
                                                                                                                                                                n = 6;
                                                                                                                                                                break block3;
                                                                                                                                                            }
                                                                                                                                                            if (!(tree instanceof Trees.TypeDef)) break block9;
                                                                                                                                                            n = 7;
                                                                                                                                                            break block3;
                                                                                                                                                        }
                                                                                                                                                        if (!(tree instanceof Trees.LabelDef)) break block10;
                                                                                                                                                        n = 8;
                                                                                                                                                        break block3;
                                                                                                                                                    }
                                                                                                                                                    if (!(tree instanceof Trees.Import)) break block11;
                                                                                                                                                    n = 9;
                                                                                                                                                    break block3;
                                                                                                                                                }
                                                                                                                                                if (!(tree instanceof Trees.Template)) break block12;
                                                                                                                                                n = 12;
                                                                                                                                                break block3;
                                                                                                                                            }
                                                                                                                                            if (!(tree instanceof Trees.Block)) break block13;
                                                                                                                                            n = 13;
                                                                                                                                            break block3;
                                                                                                                                        }
                                                                                                                                        if (!(tree instanceof Trees.CaseDef)) break block14;
                                                                                                                                        n = 14;
                                                                                                                                        break block3;
                                                                                                                                    }
                                                                                                                                    if (!(tree instanceof Trees.Alternative)) break block15;
                                                                                                                                    n = 16;
                                                                                                                                    break block3;
                                                                                                                                }
                                                                                                                                if (!(tree instanceof Trees.Star)) break block16;
                                                                                                                                n = 17;
                                                                                                                                break block3;
                                                                                                                            }
                                                                                                                            if (!(tree instanceof Trees.Bind)) break block17;
                                                                                                                            n = 18;
                                                                                                                            break block3;
                                                                                                                        }
                                                                                                                        if (!(tree instanceof Trees.UnApply)) break block18;
                                                                                                                        n = 19;
                                                                                                                        break block3;
                                                                                                                    }
                                                                                                                    if (!(tree instanceof Trees.ArrayValue)) break block19;
                                                                                                                    n = 20;
                                                                                                                    break block3;
                                                                                                                }
                                                                                                                if (!(tree instanceof Trees.Function)) break block20;
                                                                                                                n = 21;
                                                                                                                break block3;
                                                                                                            }
                                                                                                            if (!(tree instanceof Trees.Assign)) break block21;
                                                                                                            n = 22;
                                                                                                            break block3;
                                                                                                        }
                                                                                                        if (!(tree instanceof Trees.If)) break block22;
                                                                                                        n = 23;
                                                                                                        break block3;
                                                                                                    }
                                                                                                    if (!(tree instanceof Trees.Match)) break block23;
                                                                                                    n = 24;
                                                                                                    break block3;
                                                                                                }
                                                                                                if (!(tree instanceof Trees.Return)) break block24;
                                                                                                n = 25;
                                                                                                break block3;
                                                                                            }
                                                                                            if (!(tree instanceof Trees.Try)) break block25;
                                                                                            n = 26;
                                                                                            break block3;
                                                                                        }
                                                                                        if (!(tree instanceof Trees.Throw)) break block26;
                                                                                        n = 27;
                                                                                        break block3;
                                                                                    }
                                                                                    if (!(tree instanceof Trees.New)) break block27;
                                                                                    n = 28;
                                                                                    break block3;
                                                                                }
                                                                                if (!(tree instanceof Trees.Typed)) break block28;
                                                                                n = 29;
                                                                                break block3;
                                                                            }
                                                                            if (!(tree instanceof Trees.TypeApply)) break block29;
                                                                            n = 30;
                                                                            break block3;
                                                                        }
                                                                        if (!(tree instanceof Trees.Apply)) break block30;
                                                                        n = 31;
                                                                        break block3;
                                                                    }
                                                                    if (!(tree instanceof Trees.ApplyDynamic)) break block31;
                                                                    n = 32;
                                                                    break block3;
                                                                }
                                                                if (!(tree instanceof Trees.Super)) break block32;
                                                                n = 33;
                                                                break block3;
                                                            }
                                                            if (!(tree instanceof Trees.This)) break block33;
                                                            n = 34;
                                                            break block3;
                                                        }
                                                        if (!(tree instanceof Trees.Select)) break block34;
                                                        n = 35;
                                                        break block3;
                                                    }
                                                    if (!(tree instanceof Trees.Ident)) break block35;
                                                    n = 36;
                                                    break block3;
                                                }
                                                if (!(tree instanceof Trees.Literal)) break block36;
                                                n = 37;
                                                break block3;
                                            }
                                            if (!(tree instanceof Trees.TypeTree)) break block37;
                                            n = 38;
                                            break block3;
                                        }
                                        if (!(tree instanceof Trees.Annotated)) break block38;
                                        n = 39;
                                        break block3;
                                    }
                                    if (!(tree instanceof Trees.SingletonTypeTree)) break block39;
                                    n = 40;
                                    break block3;
                                }
                                if (!(tree instanceof Trees.SelectFromTypeTree)) break block40;
                                n = 41;
                                break block3;
                            }
                            if (!(tree instanceof Trees.CompoundTypeTree)) break block41;
                            n = 42;
                            break block3;
                        }
                        if (!(tree instanceof Trees.AppliedTypeTree)) break block42;
                        n = 43;
                        break block3;
                    }
                    if (!(tree instanceof Trees.TypeBoundsTree)) break block43;
                    n = 44;
                    break block3;
                }
                if (!(tree instanceof Trees.ExistentialTypeTree)) break block44;
                n = 45;
            }
            return n;
        }
        throw new MatchError(tree);
    }

    public static void $init$(SymbolTable $this) {
    }
}

