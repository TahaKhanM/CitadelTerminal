/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.tags;

import io.opencensus.tags.Tag;
import io.opencensus.tags.TagKey;
import io.opencensus.tags.TagValue;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_Tag
extends Tag {
    private final TagKey key;
    private final TagValue value;

    AutoValue_Tag(TagKey key, TagValue value) {
        if (key == null) {
            throw new NullPointerException("Null key");
        }
        this.key = key;
        if (value == null) {
            throw new NullPointerException("Null value");
        }
        this.value = value;
    }

    @Override
    public TagKey getKey() {
        return this.key;
    }

    @Override
    public TagValue getValue() {
        return this.value;
    }

    public String toString() {
        return "Tag{key=" + this.key + ", value=" + this.value + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Tag) {
            Tag that = (Tag)o;
            return this.key.equals(that.getKey()) && this.value.equals(that.getValue());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.key.hashCode();
        h *= 1000003;
        return h ^= this.value.hashCode();
    }
}

