/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Function1;
import scala.Serializable;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.reflect.internal.AnnotationCheckers;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;

public abstract class AnnotationCheckers$class {
    public static void addAnnotationChecker(SymbolTable $this, AnnotationCheckers.AnnotationChecker checker) {
        if (!$this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().contains(checker)) {
            $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers_$eq($this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().$colon$colon(checker));
        }
    }

    public static void removeAllAnnotationCheckers(SymbolTable $this) {
        $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers_$eq(Nil$.MODULE$);
    }

    public static boolean annotationsConform(SymbolTable $this, Types.Type tp1, Types.Type tp2) {
        return $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().isEmpty() || tp1.annotations().isEmpty() && tp2.annotations().isEmpty() ? true : $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().forall((Function1<AnnotationCheckers.AnnotationChecker, Object>)((Object)new Serializable($this, tp1, tp2){
            public static final long serialVersionUID = 0L;
            private final Types.Type tp1$1;
            private final Types.Type tp2$1;

            public final boolean apply(AnnotationCheckers.AnnotationChecker checker) {
                return !checker.isActive() || checker.annotationsConform(this.tp1$1, this.tp2$1);
            }
            {
                this.tp1$1 = tp1$1;
                this.tp2$1 = tp2$1;
            }
        }));
    }

    public static Types.Type annotationsLub(SymbolTable $this, Types.Type tpe, List ts) {
        return $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().isEmpty() ? tpe : $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().foldLeft(tpe, new Serializable($this, ts){
            public static final long serialVersionUID = 0L;
            private final List ts$1;

            public final Types.Type apply(Types.Type tpe, AnnotationCheckers.AnnotationChecker checker) {
                return checker.isActive() ? checker.annotationsLub(tpe, this.ts$1) : tpe;
            }
            {
                this.ts$1 = ts$1;
            }
        });
    }

    public static Types.Type annotationsGlb(SymbolTable $this, Types.Type tpe, List ts) {
        return $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().isEmpty() ? tpe : $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().foldLeft(tpe, new Serializable($this, ts){
            public static final long serialVersionUID = 0L;
            private final List ts$2;

            public final Types.Type apply(Types.Type tpe, AnnotationCheckers.AnnotationChecker checker) {
                return checker.isActive() ? checker.annotationsGlb(tpe, this.ts$2) : tpe;
            }
            {
                this.ts$2 = ts$2;
            }
        });
    }

    public static List adaptBoundsToAnnotations(SymbolTable $this, List bounds, List tparams2, List targs) {
        return $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().isEmpty() ? bounds : $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().foldLeft(bounds, new Serializable($this, tparams2, targs){
            public static final long serialVersionUID = 0L;
            private final List tparams$1;
            private final List targs$1;

            public final List<Types.TypeBounds> apply(List<Types.TypeBounds> bounds, AnnotationCheckers.AnnotationChecker checker) {
                return checker.isActive() ? checker.adaptBoundsToAnnotations(bounds, this.tparams$1, this.targs$1) : bounds;
            }
            {
                this.tparams$1 = tparams$1;
                this.targs$1 = targs$1;
            }
        });
    }

    public static Types.Type addAnnotations(SymbolTable $this, Trees.Tree tree, Types.Type tpe) {
        return $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().isEmpty() ? tpe : $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().foldLeft(tpe, new Serializable($this, tree){
            public static final long serialVersionUID = 0L;
            private final Trees.Tree tree$1;

            public final Types.Type apply(Types.Type tpe, AnnotationCheckers.AnnotationChecker checker) {
                return checker.isActive() ? checker.addAnnotations(this.tree$1, tpe) : tpe;
            }
            {
                this.tree$1 = tree$1;
            }
        });
    }

    public static boolean canAdaptAnnotations(SymbolTable $this, Trees.Tree tree, int mode, Types.Type pt) {
        return $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().isEmpty() ? false : $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().exists((Function1<AnnotationCheckers.AnnotationChecker, Object>)((Object)new Serializable($this, tree, mode, pt){
            public static final long serialVersionUID = 0L;
            private final Trees.Tree tree$2;
            private final int mode$1;
            private final Types.Type pt$1;

            public final boolean apply(AnnotationCheckers.AnnotationChecker checker) {
                return checker.isActive() && checker.canAdaptAnnotations(this.tree$2, this.mode$1, this.pt$1);
            }
            {
                this.tree$2 = tree$2;
                this.mode$1 = mode$1;
                this.pt$1 = pt$1;
            }
        }));
    }

    public static Trees.Tree adaptAnnotations(SymbolTable $this, Trees.Tree tree, int mode, Types.Type pt) {
        return $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().isEmpty() ? tree : $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().foldLeft(tree, new Serializable($this, mode, pt){
            public static final long serialVersionUID = 0L;
            private final int mode$2;
            private final Types.Type pt$2;

            public final Trees.Tree apply(Trees.Tree tree, AnnotationCheckers.AnnotationChecker checker) {
                return checker.isActive() ? checker.adaptAnnotations(tree, this.mode$2, this.pt$2) : tree;
            }
            {
                this.mode$2 = mode$2;
                this.pt$2 = pt$2;
            }
        });
    }

    public static Types.Type adaptTypeOfReturn(SymbolTable $this, Trees.Tree tree, Types.Type pt, Function0 function0) {
        return $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().isEmpty() ? (Types.Type)function0.apply() : $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().foldLeft(function0.apply(), new Serializable($this, tree, pt){
            public static final long serialVersionUID = 0L;
            private final Trees.Tree tree$3;
            private final Types.Type pt$3;

            public final Types.Type apply(Types.Type tpe, AnnotationCheckers.AnnotationChecker checker) {
                return checker.isActive() ? checker.adaptTypeOfReturn(this.tree$3, this.pt$3, (Function0<Types.Type>)((Object)new Serializable(this, tpe){
                    public static final long serialVersionUID = 0L;
                    private final Types.Type tpe$1;

                    public final Types.Type apply() {
                        return this.tpe$1;
                    }
                    {
                        this.tpe$1 = tpe$1;
                    }
                })) : tpe;
            }
            {
                this.tree$3 = tree$3;
                this.pt$3 = pt$3;
            }
        });
    }

    public static void $init$(SymbolTable $this) {
        $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers_$eq(Nil$.MODULE$);
    }
}

