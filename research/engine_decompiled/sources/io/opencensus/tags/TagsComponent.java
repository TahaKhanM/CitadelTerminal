/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.tags;

import io.opencensus.tags.Tagger;
import io.opencensus.tags.TaggingState;
import io.opencensus.tags.propagation.TagPropagationComponent;

public abstract class TagsComponent {
    public abstract Tagger getTagger();

    public abstract TagPropagationComponent getTagPropagationComponent();

    public abstract TaggingState getState();

    @Deprecated
    public abstract void setState(TaggingState var1);
}

