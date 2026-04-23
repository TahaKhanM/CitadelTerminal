/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.MatchError;
import scala.collection.immutable.Nil$;
import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.api.Universe;

public abstract class Trees$class {
    public static void itraverse(Universe $this, Trees.Traverser traverser, Trees.TreeApi tree) {
        throw new MatchError(tree);
    }

    public static void xtraverse(Universe $this, Trees.Traverser traverser, Trees.TreeApi tree) {
        throw new MatchError(tree);
    }

    public static Trees.TreeApi itransform(Universe $this, Trees.Transformer transformer, Trees.TreeApi tree) {
        throw new MatchError(tree);
    }

    public static Trees.TreeApi xtransform(Universe $this, Trees.Transformer transformer, Trees.TreeApi tree) {
        throw new MatchError(tree);
    }

    public static Trees.ModifiersApi Modifiers(Universe $this, Object flags, Names.NameApi privateWithin) {
        return $this.Modifiers().apply(flags, privateWithin, Nil$.MODULE$);
    }

    public static Trees.ModifiersApi Modifiers(Universe $this, Object flags) {
        return $this.Modifiers(flags, $this.typeNames().EMPTY());
    }

    public static Trees.ModifiersApi NoMods(Universe $this) {
        return $this.Modifiers().apply();
    }

    public static void $init$(Universe $this) {
        $this.scala$reflect$api$Trees$_setter_$treeCopy_$eq($this.newLazyTreeCopier());
    }
}

