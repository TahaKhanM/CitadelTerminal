/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.reflect.ClassTag;
import scala.reflect.Manifest;
import scala.reflect.api.FlagSets;
import scala.reflect.api.Internals;
import scala.reflect.api.Position;
import scala.reflect.api.Positions;
import scala.reflect.api.TypeTags;

public abstract class Internals$InternalApi$class {
    public static Manifest typeTagToManifest(Internals.InternalApi $this, Object mirror, TypeTags.TypeTag tag, ClassTag evidence$1) {
        throw new UnsupportedOperationException("This universe does not support tag -> manifest conversions. Use a JavaUniverse, e.g. the scala.reflect.runtime.universe.");
    }

    public static TypeTags.TypeTag manifestToTypeTag(Internals.InternalApi $this, Object mirror, Manifest manifest) {
        throw new UnsupportedOperationException("This universe does not support manifest -> tag conversions. Use a JavaUniverse, e.g. the scala.reflect.runtime.universe.");
    }

    public static Position newTermSymbol$default$3(Internals.InternalApi $this) {
        return ((Positions)((Object)$this.scala$reflect$api$Internals$InternalApi$$$outer())).NoPosition();
    }

    public static Object newTermSymbol$default$4(Internals.InternalApi $this) {
        return ((FlagSets)((Object)$this.scala$reflect$api$Internals$InternalApi$$$outer())).NoFlags();
    }

    public static Position newModuleAndClassSymbol$default$3(Internals.InternalApi $this) {
        return ((Positions)((Object)$this.scala$reflect$api$Internals$InternalApi$$$outer())).NoPosition();
    }

    public static Object newModuleAndClassSymbol$default$4(Internals.InternalApi $this) {
        return ((FlagSets)((Object)$this.scala$reflect$api$Internals$InternalApi$$$outer())).NoFlags();
    }

    public static Position newMethodSymbol$default$3(Internals.InternalApi $this) {
        return ((Positions)((Object)$this.scala$reflect$api$Internals$InternalApi$$$outer())).NoPosition();
    }

    public static Object newMethodSymbol$default$4(Internals.InternalApi $this) {
        return ((FlagSets)((Object)$this.scala$reflect$api$Internals$InternalApi$$$outer())).NoFlags();
    }

    public static Position newTypeSymbol$default$3(Internals.InternalApi $this) {
        return ((Positions)((Object)$this.scala$reflect$api$Internals$InternalApi$$$outer())).NoPosition();
    }

    public static Object newTypeSymbol$default$4(Internals.InternalApi $this) {
        return ((FlagSets)((Object)$this.scala$reflect$api$Internals$InternalApi$$$outer())).NoFlags();
    }

    public static Position newClassSymbol$default$3(Internals.InternalApi $this) {
        return ((Positions)((Object)$this.scala$reflect$api$Internals$InternalApi$$$outer())).NoPosition();
    }

    public static Object newClassSymbol$default$4(Internals.InternalApi $this) {
        return ((FlagSets)((Object)$this.scala$reflect$api$Internals$InternalApi$$$outer())).NoFlags();
    }

    public static Object newFreeTerm$default$3(Internals.InternalApi $this) {
        return ((FlagSets)((Object)$this.scala$reflect$api$Internals$InternalApi$$$outer())).NoFlags();
    }

    public static String newFreeTerm$default$4(Internals.InternalApi $this) {
        return null;
    }

    public static Object newFreeType$default$2(Internals.InternalApi $this) {
        return ((FlagSets)((Object)$this.scala$reflect$api$Internals$InternalApi$$$outer())).NoFlags();
    }

    public static String newFreeType$default$3(Internals.InternalApi $this) {
        return null;
    }

    public static void $init$(Internals.InternalApi $this) {
    }
}

