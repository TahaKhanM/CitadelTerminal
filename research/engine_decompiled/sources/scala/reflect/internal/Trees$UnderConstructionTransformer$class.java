/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.collection.immutable.Nil$;
import scala.collection.mutable.Stack;
import scala.collection.mutable.Stack$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;

public abstract class Trees$UnderConstructionTransformer$class {
    public static boolean isUnderConstruction(Trees.UnderConstructionTransformer $this, Symbols.Symbol clazz) {
        return $this.scala$reflect$internal$Trees$UnderConstructionTransformer$$selfOrSuperCalls().contains(clazz);
    }

    public static Trees.Tree transform(Trees.UnderConstructionTransformer $this, Trees.Tree tree) {
        Trees.Tree tree2;
        if (((SymbolTable)$this.scala$reflect$internal$Trees$UnderConstructionTransformer$$$outer()).treeInfo().isSelfOrSuperConstrCall(tree) || ((SymbolTable)$this.scala$reflect$internal$Trees$UnderConstructionTransformer$$$outer()).treeInfo().isEarlyDef(tree)) {
            $this.scala$reflect$internal$Trees$UnderConstructionTransformer$$selfOrSuperCalls().push($this.scala$reflect$internal$Trees$UnderConstructionTransformer$$super$currentOwner().owner());
            tree2 = $this.scala$reflect$internal$Trees$UnderConstructionTransformer$$super$transform(tree);
        } else {
            tree2 = $this.scala$reflect$internal$Trees$UnderConstructionTransformer$$super$transform(tree);
        }
        return tree2;
        finally {
            $this.scala$reflect$internal$Trees$UnderConstructionTransformer$$selfOrSuperCalls().pop();
        }
    }

    public static void $init$(Trees.UnderConstructionTransformer $this) {
        $this.scala$reflect$internal$Trees$UnderConstructionTransformer$_setter_$scala$reflect$internal$Trees$UnderConstructionTransformer$$selfOrSuperCalls_$eq((Stack)Stack$.MODULE$.apply(Nil$.MODULE$));
    }
}

