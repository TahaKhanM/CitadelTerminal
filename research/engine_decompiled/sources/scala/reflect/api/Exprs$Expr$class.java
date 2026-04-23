/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.collection.mutable.StringBuilder;
import scala.reflect.api.Exprs;
import scala.reflect.api.Mirror;
import scala.reflect.api.Trees;

public abstract class Exprs$Expr$class {
    public static boolean canEqual(Exprs.Expr $this, Object x) {
        return x instanceof Exprs.Expr;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean equals(Exprs.Expr $this, Object x) {
        if (!(x instanceof Exprs.Expr)) return false;
        Mirror mirror = $this.mirror();
        Mirror mirror2 = ((Exprs.Expr)x).mirror();
        if (mirror == null) {
            if (mirror2 != null) {
                return false;
            }
        } else if (!mirror.equals(mirror2)) return false;
        Trees.TreeApi treeApi = $this.tree();
        Trees.TreeApi treeApi2 = ((Exprs.Expr)x).tree();
        if (treeApi == null) {
            if (treeApi2 == null) return true;
            return false;
        } else {
            if (!treeApi.equals(treeApi2)) return false;
            return true;
        }
    }

    public static int hashCode(Exprs.Expr $this) {
        return $this.mirror().hashCode() * 31 + $this.tree().hashCode();
    }

    public static String toString(Exprs.Expr $this) {
        return new StringBuilder().append((Object)"Expr[").append($this.staticType()).append((Object)"](").append($this.tree()).append((Object)")").toString();
    }

    public static void $init$(Exprs.Expr $this) {
    }
}

