/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.tags.unsafe;

import io.grpc.Context;
import io.opencensus.tags.Tag;
import io.opencensus.tags.TagContext;
import java.util.Collections;
import java.util.Iterator;
import javax.annotation.concurrent.Immutable;

public final class ContextUtils {
    private static final TagContext EMPTY_TAG_CONTEXT = new EmptyTagContext();
    public static final Context.Key<TagContext> TAG_CONTEXT_KEY = Context.keyWithDefault("opencensus-tag-context-key", EMPTY_TAG_CONTEXT);

    private ContextUtils() {
    }

    @Immutable
    private static final class EmptyTagContext
    extends TagContext {
        private EmptyTagContext() {
        }

        @Override
        protected Iterator<Tag> getIterator() {
            return Collections.emptySet().iterator();
        }
    }
}

