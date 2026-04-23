/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.tags.propagation;

import io.opencensus.tags.propagation.TagContextBinarySerializer;

public abstract class TagPropagationComponent {
    public abstract TagContextBinarySerializer getBinarySerializer();
}

