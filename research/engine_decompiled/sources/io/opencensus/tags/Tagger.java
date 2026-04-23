/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.tags;

import io.opencensus.common.Scope;
import io.opencensus.tags.TagContext;
import io.opencensus.tags.TagContextBuilder;

public abstract class Tagger {
    public abstract TagContext empty();

    public abstract TagContext getCurrentTagContext();

    public abstract TagContextBuilder emptyBuilder();

    public abstract TagContextBuilder toBuilder(TagContext var1);

    public abstract TagContextBuilder currentBuilder();

    public abstract Scope withTagContext(TagContext var1);
}

