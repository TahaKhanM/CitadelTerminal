/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.tags;

import io.opencensus.tags.TagKey;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_TagKey
extends TagKey {
    private final String name;

    AutoValue_TagKey(String name) {
        if (name == null) {
            throw new NullPointerException("Null name");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String toString() {
        return "TagKey{name=" + this.name + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof TagKey) {
            TagKey that = (TagKey)o;
            return this.name.equals(that.getName());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.name.hashCode();
    }
}

