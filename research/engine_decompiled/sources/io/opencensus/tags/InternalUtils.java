/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.tags;

import io.opencensus.tags.Tag;
import io.opencensus.tags.TagContext;
import java.util.Iterator;

public final class InternalUtils {
    private InternalUtils() {
    }

    public static Iterator<Tag> getTags(TagContext tags) {
        return tags.getIterator();
    }
}

