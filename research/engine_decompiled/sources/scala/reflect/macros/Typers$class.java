/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.reflect.api.Position;
import scala.reflect.api.Trees;
import scala.reflect.api.Types;
import scala.reflect.macros.TypecheckException$;
import scala.reflect.macros.blackbox.Context;

public abstract class Typers$class {
    public static Trees.TreeApi typeCheck(Context $this, Trees.TreeApi tree, Types.TypeApi pt, boolean silent, boolean withImplicitViewsDisabled, boolean withMacrosDisabled) {
        return $this.typecheck(tree, $this.TERMmode(), pt, silent, withImplicitViewsDisabled, withMacrosDisabled);
    }

    public static Types.TypeApi typeCheck$default$2(Context $this) {
        return $this.universe().WildcardType();
    }

    public static boolean typeCheck$default$3(Context $this) {
        return false;
    }

    public static boolean typeCheck$default$4(Context $this) {
        return false;
    }

    public static boolean typeCheck$default$5(Context $this) {
        return false;
    }

    public static Object typecheck$default$2(Context $this) {
        return $this.TERMmode();
    }

    public static Types.TypeApi typecheck$default$3(Context $this) {
        return $this.universe().WildcardType();
    }

    public static boolean typecheck$default$4(Context $this) {
        return false;
    }

    public static boolean typecheck$default$5(Context $this) {
        return false;
    }

    public static boolean typecheck$default$6(Context $this) {
        return false;
    }

    public static boolean inferImplicitValue$default$2(Context $this) {
        return true;
    }

    public static boolean inferImplicitValue$default$3(Context $this) {
        return false;
    }

    public static Position inferImplicitValue$default$4(Context $this) {
        return $this.enclosingPosition();
    }

    public static boolean inferImplicitView$default$4(Context $this) {
        return true;
    }

    public static boolean inferImplicitView$default$5(Context $this) {
        return false;
    }

    public static Position inferImplicitView$default$6(Context $this) {
        return $this.enclosingPosition();
    }

    public static void $init$(Context $this) {
        $this.scala$reflect$macros$Typers$_setter_$TypecheckException_$eq(TypecheckException$.MODULE$);
    }
}

