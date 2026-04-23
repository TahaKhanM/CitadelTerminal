/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.tags;

import io.opencensus.common.Scope;
import io.opencensus.tags.TagContext;
import io.opencensus.tags.TagKey;
import io.opencensus.tags.TagValue;

public abstract class TagContextBuilder {
    public abstract TagContextBuilder put(TagKey var1, TagValue var2);

    public abstract TagContextBuilder remove(TagKey var1);

    public abstract TagContext build();

    public abstract Scope buildScoped();
}

