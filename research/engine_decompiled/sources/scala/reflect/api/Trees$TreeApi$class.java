/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.reflect.api.Printers;
import scala.reflect.api.Trees;

public abstract class Trees$TreeApi$class {
    public static String toString(Trees.TreeApi $this) {
        return ((Printers)((Object)$this.scala$reflect$api$Trees$TreeApi$$$outer())).treeToString($this);
    }

    public static void $init$(Trees.TreeApi $this) {
    }
}

