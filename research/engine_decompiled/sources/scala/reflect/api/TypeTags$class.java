/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

public abstract class TypeTags$class {
    public static TypeTags.WeakTypeTag weakTypeTag(Universe $this, TypeTags.WeakTypeTag attag) {
        return attag;
    }

    public static TypeTags.TypeTag typeTag(Universe $this, TypeTags.TypeTag ttag) {
        return ttag;
    }

    public static Types.TypeApi weakTypeOf(Universe $this, TypeTags.WeakTypeTag attag) {
        return attag.tpe();
    }

    public static Types.TypeApi typeOf(Universe $this, TypeTags.TypeTag ttag) {
        return ttag.tpe();
    }

    public static void $init$(Universe $this) {
    }
}

