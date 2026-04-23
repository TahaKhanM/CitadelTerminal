/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.collection.mutable.StringBuilder;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;

public abstract class TypeTags$TypeTag$class {
    public static boolean canEqual(TypeTags.TypeTag $this, Object x) {
        return x instanceof TypeTags.TypeTag;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean equals(TypeTags.TypeTag $this, Object x) {
        if (!(x instanceof TypeTags.TypeTag)) return false;
        Mirror mirror = $this.mirror();
        Mirror mirror2 = ((TypeTags.TypeTag)x).mirror();
        if (mirror == null) {
            if (mirror2 != null) {
                return false;
            }
        } else if (!mirror.equals(mirror2)) return false;
        Types.TypeApi typeApi = $this.tpe();
        Types.TypeApi typeApi2 = ((TypeTags.TypeTag)x).tpe();
        if (typeApi == null) {
            if (typeApi2 == null) return true;
            return false;
        } else {
            if (!typeApi.equals(typeApi2)) return false;
            return true;
        }
    }

    public static int hashCode(TypeTags.TypeTag $this) {
        return $this.mirror().hashCode() * 31 + $this.tpe().hashCode();
    }

    public static String toString(TypeTags.TypeTag $this) {
        return new StringBuilder().append((Object)"TypeTag[").append($this.tpe()).append((Object)"]").toString();
    }

    public static void $init$(TypeTags.TypeTag $this) {
    }
}

