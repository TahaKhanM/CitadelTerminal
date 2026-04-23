/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.tags;

import io.opencensus.tags.AutoValue_Tag;
import io.opencensus.tags.TagKey;
import io.opencensus.tags.TagValue;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Tag {
    Tag() {
    }

    public static Tag create(TagKey key, TagValue value) {
        return new AutoValue_Tag(key, value);
    }

    public abstract TagKey getKey();

    public abstract TagValue getValue();
}

