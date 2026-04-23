/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.reflect.api.Symbols;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.macros.blackbox.Context;

public abstract class Aliases$class {
    public static TypeTags.WeakTypeTag weakTypeTag(Context $this, TypeTags.WeakTypeTag attag) {
        return attag;
    }

    public static TypeTags.TypeTag typeTag(Context $this, TypeTags.TypeTag ttag) {
        return ttag;
    }

    public static Types.TypeApi weakTypeOf(Context $this, TypeTags.WeakTypeTag attag) {
        return attag.tpe();
    }

    public static Types.TypeApi typeOf(Context $this, TypeTags.TypeTag ttag) {
        return ttag.tpe();
    }

    public static Symbols.TypeSymbolApi symbolOf(Context $this, TypeTags.WeakTypeTag evidence$2) {
        return $this.universe().symbolOf(evidence$2);
    }

    public static void $init$(Context $this) {
        $this.scala$reflect$macros$Aliases$_setter_$Expr_$eq($this.universe().Expr());
        $this.scala$reflect$macros$Aliases$_setter_$WeakTypeTag_$eq($this.universe().WeakTypeTag());
        $this.scala$reflect$macros$Aliases$_setter_$TypeTag_$eq($this.universe().TypeTag());
    }
}

