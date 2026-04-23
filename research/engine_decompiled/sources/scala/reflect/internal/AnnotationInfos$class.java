/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Array$;
import scala.MatchError;
import scala.Predef$;
import scala.Predef$any2stringadd$;
import scala.Serializable;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.TraversableOnce;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ClassTag$;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.Names;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;

public abstract class AnnotationInfos$class {
    public static String completeAnnotationToString(SymbolTable $this, AnnotationInfos.AnnotationInfo annInfo) {
        String s_args = annInfo.args().isEmpty() ? "" : annInfo.args().mkString("(", ", ", ")");
        String s_assocs = annInfo.assocs().isEmpty() ? "" : ((TraversableOnce)annInfo.assocs().map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final String apply(Tuple2<Names.Name, AnnotationInfos.ClassfileAnnotArg> x0$1) {
                if (x0$1 != null) {
                    return new StringBuilder().append((Object)Predef$any2stringadd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(x0$1._1()), " = ")).append(x0$1._2()).toString();
                }
                throw new MatchError(x0$1);
            }
        }, List$.MODULE$.canBuildFrom())).mkString("(", ", ", ")");
        return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", "", "", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{annInfo.atp(), s_args, s_assocs}));
    }

    public static Trees.Tree annotationToTree(SymbolTable $this, AnnotationInfos.AnnotationInfo ann) {
        Trees.Select ctorSelection = new Trees.Select($this, new Trees.New($this, $this.TypeTree(ann.atp())), $this.nme().CONSTRUCTOR());
        return new Trees.Apply($this, ctorSelection, AnnotationInfos$class.reverseEngineerArgs$1($this, ann)).setType(ann.atp());
    }

    public static AnnotationInfos.AnnotationInfo treeToAnnotation(SymbolTable $this, Trees.Tree tree) {
        block2: {
            Types.Type atp;
            block5: {
                AnnotationInfos.AnnotationInfo annotationInfo;
                block4: {
                    Trees.Apply apply2;
                    block3: {
                        Trees.Select select;
                        if (!(tree instanceof Trees.Apply) || !((apply2 = (Trees.Apply)tree).fun() instanceof Trees.Select) || !((select = (Trees.Select)apply2.fun()).qualifier() instanceof Trees.New)) break block2;
                        Trees.New new_ = (Trees.New)select.qualifier();
                        Names.TermName termName = $this.nme().CONSTRUCTOR();
                        Names.Name name = select.name();
                        if (termName != null ? !termName.equals(name) : name != null) break block2;
                        atp = new_.tpt().tpe();
                        if (atp == null || !atp.typeSymbol().isNonBottomSubClass($this.definitions().StaticAnnotationClass())) break block3;
                        annotationInfo = $this.AnnotationInfo().apply(atp, apply2.args(), Nil$.MODULE$);
                        break block4;
                    }
                    if (atp == null || !atp.typeSymbol().isNonBottomSubClass($this.definitions().ClassfileAnnotationClass())) break block5;
                    annotationInfo = $this.AnnotationInfo().apply(atp, Nil$.MODULE$, AnnotationInfos$class.encodeJavaArgs$1($this, apply2.args()));
                }
                return annotationInfo;
            }
            throw new Exception(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"unexpected annotation type ", ": only subclasses of StaticAnnotation and ClassfileAnnotation are supported"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{atp})));
        }
        throw new Exception("unexpected tree shape: only q\"new $annType(..$args)\" is supported");
    }

    public static final Trees.Tree reverseEngineerArg$1(SymbolTable $this, AnnotationInfos.ClassfileAnnotArg jarg) {
        AnnotationInfos.NestedAnnotArg nestedAnnotArg;
        Trees.Tree tree;
        if (jarg instanceof AnnotationInfos.LiteralAnnotArg) {
            AnnotationInfos.LiteralAnnotArg literalAnnotArg = (AnnotationInfos.LiteralAnnotArg)jarg;
            Types.Type tpe = literalAnnotArg.const().tag() == 1 ? $this.definitions().UnitTpe() : $this.ConstantType().apply(literalAnnotArg.const());
            tree = new Trees.Literal($this, literalAnnotArg.const()).setType(tpe);
        } else if (jarg instanceof AnnotationInfos.ArrayAnnotArg) {
            AnnotationInfos.ArrayAnnotArg arrayAnnotArg = (AnnotationInfos.ArrayAnnotArg)jarg;
            Trees.Tree[] args = (Trees.Tree[])Predef$.MODULE$.refArrayOps((Object[])arrayAnnotArg.args()).map(new Serializable($this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;

                public final Trees.Tree apply(AnnotationInfos.ClassfileAnnotArg jarg) {
                    return AnnotationInfos$class.reverseEngineerArg$1(this.$outer, jarg);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Array$.MODULE$.canBuildFrom($this.TreeTag()));
            tree = new Trees.Apply($this, $this.Ident($this.definitions().ArrayModule()), Predef$.MODULE$.refArrayOps((Object[])args).toList());
        } else if (jarg instanceof AnnotationInfos.NestedAnnotArg && (nestedAnnotArg = (AnnotationInfos.NestedAnnotArg)jarg).annInfo() != null) {
            AnnotationInfos.AnnotationInfo annotationInfo = nestedAnnotArg.annInfo();
            tree = $this.annotationToTree(annotationInfo);
        } else {
            tree = $this.EmptyTree();
        }
        return tree;
    }

    private static final List reverseEngineerArgs$2(SymbolTable $this, List jargs) {
        block4: {
            List list2;
            block3: {
                block2: {
                    $colon$colon $colon$colon;
                    if (!(jargs instanceof $colon$colon) || ($colon$colon = ($colon$colon)jargs).head() == null) break block2;
                    Trees.AssignOrNamedArg assignOrNamedArg = new Trees.AssignOrNamedArg($this, new Trees.Ident($this, (Names.Name)((Tuple2)$colon$colon.head())._1()), AnnotationInfos$class.reverseEngineerArg$1($this, (AnnotationInfos.ClassfileAnnotArg)((Tuple2)$colon$colon.head())._2()));
                    list2 = AnnotationInfos$class.reverseEngineerArgs$2($this, $colon$colon.tl$1()).$colon$colon(assignOrNamedArg);
                    break block3;
                }
                if (!((Object)Nil$.MODULE$).equals(jargs)) break block4;
                list2 = Nil$.MODULE$;
            }
            return list2;
        }
        throw new MatchError(jargs);
    }

    private static final List reverseEngineerArgs$1(SymbolTable $this, AnnotationInfos.AnnotationInfo ann$1) {
        return ann$1.javaArgs().isEmpty() ? ann$1.scalaArgs() : AnnotationInfos$class.reverseEngineerArgs$2($this, ann$1.javaArgs().toList());
    }

    public static final AnnotationInfos.ClassfileAnnotArg encodeJavaArg$1(SymbolTable $this, Trees.Tree arg) {
        block5: {
            AnnotationInfos.ClassfileAnnotArg classfileAnnotArg;
            block3: {
                Trees.Select select;
                Trees.Apply apply2;
                boolean bl;
                block4: {
                    block2: {
                        bl = false;
                        apply2 = null;
                        if (!(arg instanceof Trees.Literal)) break block2;
                        Trees.Literal literal = (Trees.Literal)arg;
                        classfileAnnotArg = new AnnotationInfos.LiteralAnnotArg($this, literal.value());
                        break block3;
                    }
                    if (!(arg instanceof Trees.Apply)) break block4;
                    bl = true;
                    apply2 = (Trees.Apply)arg;
                    Symbols.ModuleSymbol moduleSymbol = $this.definitions().ArrayModule();
                    Trees.Tree tree = apply2.fun();
                    if (moduleSymbol != null ? !moduleSymbol.equals(tree) : tree != null) break block4;
                    classfileAnnotArg = new AnnotationInfos.ArrayAnnotArg($this, (AnnotationInfos.ClassfileAnnotArg[])((TraversableOnce)apply2.args().map(new Serializable($this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ SymbolTable $outer;

                        public final AnnotationInfos.ClassfileAnnotArg apply(Trees.Tree arg) {
                            return AnnotationInfos$class.encodeJavaArg$1(this.$outer, arg);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }, List$.MODULE$.canBuildFrom())).toArray($this.JavaArgumentTag()));
                    break block3;
                }
                if (!bl || !(apply2.fun() instanceof Trees.Select) || !((select = (Trees.Select)apply2.fun()).qualifier() instanceof Trees.New)) break block5;
                Names.TermName termName = $this.nme().CONSTRUCTOR();
                Names.Name name = select.name();
                if (termName != null ? !termName.equals(name) : name != null) break block5;
                classfileAnnotArg = new AnnotationInfos.NestedAnnotArg($this, $this.treeToAnnotation(arg));
            }
            return classfileAnnotArg;
        }
        throw new Exception(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"unexpected java argument shape ", ": literals, arrays and nested annotations are supported"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{arg})));
    }

    private static final List encodeJavaArgs$1(SymbolTable $this, List args) {
        block6: {
            List list2;
            block5: {
                $colon$colon $colon$colon;
                boolean bl;
                block4: {
                    Trees.AssignOrNamedArg assignOrNamedArg;
                    bl = false;
                    $colon$colon = null;
                    if (!(args instanceof $colon$colon)) break block4;
                    bl = true;
                    $colon$colon = ($colon$colon)args;
                    if (!($colon$colon.head() instanceof Trees.AssignOrNamedArg) || !((assignOrNamedArg = (Trees.AssignOrNamedArg)$colon$colon.head()).lhs() instanceof Trees.Ident)) break block4;
                    Trees.Ident ident = (Trees.Ident)assignOrNamedArg.lhs();
                    Tuple2<Names.Name, AnnotationInfos.ClassfileAnnotArg> tuple2 = new Tuple2<Names.Name, AnnotationInfos.ClassfileAnnotArg>(ident.name(), AnnotationInfos$class.encodeJavaArg$1($this, assignOrNamedArg.rhs()));
                    list2 = AnnotationInfos$class.encodeJavaArgs$1($this, $colon$colon.tl$1()).$colon$colon(tuple2);
                    break block5;
                }
                if (bl) {
                    throw new Exception(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"unexpected java argument shape ", ": only AssignOrNamedArg trees are supported"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{$colon$colon.head()})));
                }
                if (!((Object)Nil$.MODULE$).equals(args)) break block6;
                list2 = Nil$.MODULE$;
            }
            return list2;
        }
        throw new MatchError(args);
    }

    public static void $init$(SymbolTable $this) {
        $this.scala$reflect$internal$AnnotationInfos$_setter_$JavaArgumentTag_$eq(ClassTag$.MODULE$.apply(AnnotationInfos.ClassfileAnnotArg.class));
        $this.scala$reflect$internal$AnnotationInfos$_setter_$LiteralArgument_$eq($this.LiteralAnnotArg());
        $this.scala$reflect$internal$AnnotationInfos$_setter_$LiteralArgumentTag_$eq(ClassTag$.MODULE$.apply(AnnotationInfos.LiteralAnnotArg.class));
        $this.scala$reflect$internal$AnnotationInfos$_setter_$ArrayArgument_$eq($this.ArrayAnnotArg());
        $this.scala$reflect$internal$AnnotationInfos$_setter_$ArrayArgumentTag_$eq(ClassTag$.MODULE$.apply(AnnotationInfos.ArrayAnnotArg.class));
        $this.scala$reflect$internal$AnnotationInfos$_setter_$NestedArgument_$eq($this.NestedAnnotArg());
        $this.scala$reflect$internal$AnnotationInfos$_setter_$NestedArgumentTag_$eq(ClassTag$.MODULE$.apply(AnnotationInfos.NestedAnnotArg.class));
        $this.scala$reflect$internal$AnnotationInfos$_setter_$AnnotationTag_$eq(ClassTag$.MODULE$.apply(AnnotationInfos.AnnotationInfo.class));
    }
}

