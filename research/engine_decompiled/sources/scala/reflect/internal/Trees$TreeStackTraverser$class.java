/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.collection.immutable.Nil$;
import scala.collection.mutable.Stack;
import scala.collection.mutable.Stack$;
import scala.reflect.internal.Trees;

public abstract class Trees$TreeStackTraverser$class {
    public static void traverse(Trees.TreeStackTraverser $this, Trees.Tree t) {
        $this.path().push(t);
        try {
            $this.scala$reflect$internal$Trees$TreeStackTraverser$$super$traverse(t);
            return;
        }
        finally {
            $this.path().pop();
        }
    }

    public static void $init$(Trees.TreeStackTraverser $this) {
        $this.scala$reflect$internal$Trees$TreeStackTraverser$_setter_$path_$eq((Stack)Stack$.MODULE$.apply(Nil$.MODULE$));
    }
}

