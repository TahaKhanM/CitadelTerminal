/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.internal.FreshNames;
import scala.reflect.internal.Names;
import scala.reflect.internal.StdNames;
import scala.reflect.internal.util.FreshNameCreator;
import scala.reflect.internal.util.FreshNameCreator$;

public abstract class FreshNames$class {
    public static Names.TermName freshTermName(FreshNames $this, String prefix, FreshNameCreator creator) {
        return ((Names)((Object)$this)).newTermName(creator.newName(prefix));
    }

    public static String freshTermName$default$1(FreshNames $this) {
        return ((StdNames)((Object)$this)).nme().FRESH_TERM_NAME_PREFIX();
    }

    public static Names.TypeName freshTypeName(FreshNames $this, String prefix, FreshNameCreator creator) {
        return ((Names)((Object)$this)).newTypeName(creator.newName(prefix));
    }

    public static void $init$(FreshNames $this) {
        $this.scala$reflect$internal$FreshNames$_setter_$globalFreshNameCreator_$eq(new FreshNameCreator(FreshNameCreator$.MODULE$.$lessinit$greater$default$1()));
    }
}

