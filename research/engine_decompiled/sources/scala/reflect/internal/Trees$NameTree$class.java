/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.internal.Names;
import scala.reflect.internal.Trees;

public abstract class Trees$NameTree$class {
    public static Names.TermName getterName(Trees.NameTree $this) {
        return ((Names)((Object)$this.scala$reflect$internal$Trees$NameTree$$$outer())).AnyNameOps($this.name()).getterName();
    }

    public static Names.TermName setterName(Trees.NameTree $this) {
        return ((Names)((Object)$this.scala$reflect$internal$Trees$NameTree$$$outer())).AnyNameOps($this.name()).setterName();
    }

    public static Names.TermName localName(Trees.NameTree $this) {
        return ((Names)((Object)$this.scala$reflect$internal$Trees$NameTree$$$outer())).AnyNameOps($this.name()).localName();
    }

    public static void $init$(Trees.NameTree $this) {
    }
}

