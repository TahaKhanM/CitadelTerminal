/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.collection.immutable.List;
import scala.reflect.internal.AnnotationCheckers;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;

public abstract class AnnotationCheckers$AnnotationChecker$class {
    public static boolean isActive(AnnotationCheckers.AnnotationChecker $this) {
        return true;
    }

    public static Types.Type annotationsLub(AnnotationCheckers.AnnotationChecker $this, Types.Type tp, List ts) {
        return tp;
    }

    public static Types.Type annotationsGlb(AnnotationCheckers.AnnotationChecker $this, Types.Type tp, List ts) {
        return tp;
    }

    public static List adaptBoundsToAnnotations(AnnotationCheckers.AnnotationChecker $this, List bounds, List tparams2, List targs) {
        return bounds;
    }

    public static Types.Type addAnnotations(AnnotationCheckers.AnnotationChecker $this, Trees.Tree tree, Types.Type tpe) {
        return tpe;
    }

    public static boolean canAdaptAnnotations(AnnotationCheckers.AnnotationChecker $this, Trees.Tree tree, int mode, Types.Type pt) {
        return false;
    }

    public static Trees.Tree adaptAnnotations(AnnotationCheckers.AnnotationChecker $this, Trees.Tree tree, int mode, Types.Type pt) {
        return tree;
    }

    public static Types.Type adaptTypeOfReturn(AnnotationCheckers.AnnotationChecker $this, Trees.Tree tree, Types.Type pt, Function0 function0) {
        return (Types.Type)function0.apply();
    }

    public static void $init$(AnnotationCheckers.AnnotationChecker $this) {
    }
}

