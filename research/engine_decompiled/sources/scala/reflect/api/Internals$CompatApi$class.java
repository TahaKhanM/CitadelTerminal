/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.reflect.ClassTag;
import scala.reflect.Manifest;
import scala.reflect.api.Internals;
import scala.reflect.api.Position;
import scala.reflect.api.Scopes;
import scala.reflect.api.Symbols;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

public abstract class Internals$CompatApi$class {
    public static Manifest typeTagToManifest(Internals.CompatApi $this, Object mirror, TypeTags.TypeTag tag, ClassTag evidence$2) {
        return $this.scala$reflect$api$Internals$CompatApi$$$outer().internal().typeTagToManifest(mirror, tag, evidence$2);
    }

    public static TypeTags.TypeTag manifestToTypeTag(Internals.CompatApi $this, Object mirror, Manifest manifest) {
        return $this.scala$reflect$api$Internals$CompatApi$$$outer().internal().manifestToTypeTag(mirror, manifest);
    }

    public static Scopes.ScopeApi newScopeWith(Internals.CompatApi $this, Seq elems) {
        return $this.scala$reflect$api$Internals$CompatApi$$$outer().internal().newScopeWith(elems);
    }

    public static Internals.CompatApi.CompatibleBuildApi CompatibleBuildApi(Internals.CompatApi $this, Internals.ReificationSupportApi api2) {
        return new Internals.CompatApi.CompatibleBuildApi($this, api2);
    }

    public static Internals.CompatApi.CompatibleTree CompatibleTree(Internals.CompatApi $this, Trees.TreeApi tree) {
        return new Internals.CompatApi.CompatibleTree($this, tree);
    }

    public static Internals.CompatApi.CompatibleSymbol CompatibleSymbol(Internals.CompatApi $this, Symbols.SymbolApi symbol) {
        return new Internals.CompatApi.CompatibleSymbol($this, symbol);
    }

    public static Types.TypeApi singleType(Internals.CompatApi $this, Types.TypeApi pre, Symbols.SymbolApi sym) {
        return $this.scala$reflect$api$Internals$CompatApi$$$outer().internal().singleType(pre, sym);
    }

    public static Types.TypeApi refinedType(Internals.CompatApi $this, List parents2, Symbols.SymbolApi owner2, Scopes.ScopeApi decls, Position pos) {
        return $this.scala$reflect$api$Internals$CompatApi$$$outer().internal().refinedType(parents2, owner2, decls, pos);
    }

    public static Types.TypeApi refinedType(Internals.CompatApi $this, List parents2, Symbols.SymbolApi owner2) {
        return $this.scala$reflect$api$Internals$CompatApi$$$outer().internal().refinedType((List<Types.TypeApi>)parents2, owner2);
    }

    public static Types.TypeApi typeRef(Internals.CompatApi $this, Types.TypeApi pre, Symbols.SymbolApi sym, List args) {
        return $this.scala$reflect$api$Internals$CompatApi$$$outer().internal().typeRef(pre, sym, args);
    }

    public static Types.TypeApi intersectionType(Internals.CompatApi $this, List tps) {
        return $this.scala$reflect$api$Internals$CompatApi$$$outer().internal().intersectionType(tps);
    }

    public static Types.TypeApi intersectionType(Internals.CompatApi $this, List tps, Symbols.SymbolApi owner2) {
        return $this.scala$reflect$api$Internals$CompatApi$$$outer().internal().intersectionType(tps, owner2);
    }

    public static Types.TypeApi polyType(Internals.CompatApi $this, List tparams2, Types.TypeApi tpe) {
        return (Types.TypeApi)((Object)$this.scala$reflect$api$Internals$CompatApi$$$outer().internal().polyType(tparams2, tpe));
    }

    public static Types.TypeApi existentialAbstraction(Internals.CompatApi $this, List tparams2, Types.TypeApi tpe0) {
        return $this.scala$reflect$api$Internals$CompatApi$$$outer().internal().existentialAbstraction(tparams2, tpe0);
    }

    public static void $init$(Internals.CompatApi $this) {
        $this.scala$reflect$api$Internals$CompatApi$_setter_$token_$eq(new Internals.CompatToken((Universe)$this.scala$reflect$api$Internals$CompatApi$$$outer()));
    }
}

