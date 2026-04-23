/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.tags;

import com.google.common.base.Preconditions;
import io.opencensus.common.Scope;
import io.opencensus.internal.NoopScope;
import io.opencensus.tags.Tag;
import io.opencensus.tags.TagContext;
import io.opencensus.tags.TagContextBuilder;
import io.opencensus.tags.TagKey;
import io.opencensus.tags.TagValue;
import io.opencensus.tags.Tagger;
import io.opencensus.tags.TaggingState;
import io.opencensus.tags.TagsComponent;
import io.opencensus.tags.propagation.TagContextBinarySerializer;
import io.opencensus.tags.propagation.TagPropagationComponent;
import java.util.Collections;
import java.util.Iterator;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

final class NoopTags {
    private NoopTags() {
    }

    static TagsComponent newNoopTagsComponent() {
        return new NoopTagsComponent();
    }

    static Tagger getNoopTagger() {
        return NoopTagger.INSTANCE;
    }

    static TagContextBuilder getNoopTagContextBuilder() {
        return NoopTagContextBuilder.INSTANCE;
    }

    static TagContext getNoopTagContext() {
        return NoopTagContext.INSTANCE;
    }

    static TagPropagationComponent getNoopTagPropagationComponent() {
        return NoopTagPropagationComponent.INSTANCE;
    }

    static TagContextBinarySerializer getNoopTagContextBinarySerializer() {
        return NoopTagContextBinarySerializer.INSTANCE;
    }

    @Immutable
    private static final class NoopTagContextBinarySerializer
    extends TagContextBinarySerializer {
        static final TagContextBinarySerializer INSTANCE = new NoopTagContextBinarySerializer();
        static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

        private NoopTagContextBinarySerializer() {
        }

        @Override
        public byte[] toByteArray(TagContext tags) {
            Preconditions.checkNotNull(tags, "tags");
            return EMPTY_BYTE_ARRAY;
        }

        @Override
        public TagContext fromByteArray(byte[] bytes2) {
            Preconditions.checkNotNull(bytes2, "bytes");
            return NoopTags.getNoopTagContext();
        }
    }

    @Immutable
    private static final class NoopTagPropagationComponent
    extends TagPropagationComponent {
        static final TagPropagationComponent INSTANCE = new NoopTagPropagationComponent();

        private NoopTagPropagationComponent() {
        }

        @Override
        public TagContextBinarySerializer getBinarySerializer() {
            return NoopTags.getNoopTagContextBinarySerializer();
        }
    }

    @Immutable
    private static final class NoopTagContext
    extends TagContext {
        static final TagContext INSTANCE = new NoopTagContext();

        private NoopTagContext() {
        }

        @Override
        protected Iterator<Tag> getIterator() {
            return Collections.emptySet().iterator();
        }
    }

    @Immutable
    private static final class NoopTagContextBuilder
    extends TagContextBuilder {
        static final TagContextBuilder INSTANCE = new NoopTagContextBuilder();

        private NoopTagContextBuilder() {
        }

        @Override
        public TagContextBuilder put(TagKey key, TagValue value) {
            Preconditions.checkNotNull(key, "key");
            Preconditions.checkNotNull(value, "value");
            return this;
        }

        @Override
        public TagContextBuilder remove(TagKey key) {
            Preconditions.checkNotNull(key, "key");
            return this;
        }

        @Override
        public TagContext build() {
            return NoopTags.getNoopTagContext();
        }

        @Override
        public Scope buildScoped() {
            return NoopScope.getInstance();
        }
    }

    @Immutable
    private static final class NoopTagger
    extends Tagger {
        static final Tagger INSTANCE = new NoopTagger();

        private NoopTagger() {
        }

        @Override
        public TagContext empty() {
            return NoopTags.getNoopTagContext();
        }

        @Override
        public TagContext getCurrentTagContext() {
            return NoopTags.getNoopTagContext();
        }

        @Override
        public TagContextBuilder emptyBuilder() {
            return NoopTags.getNoopTagContextBuilder();
        }

        @Override
        public TagContextBuilder toBuilder(TagContext tags) {
            Preconditions.checkNotNull(tags, "tags");
            return NoopTags.getNoopTagContextBuilder();
        }

        @Override
        public TagContextBuilder currentBuilder() {
            return NoopTags.getNoopTagContextBuilder();
        }

        @Override
        public Scope withTagContext(TagContext tags) {
            Preconditions.checkNotNull(tags, "tags");
            return NoopScope.getInstance();
        }
    }

    @ThreadSafe
    private static final class NoopTagsComponent
    extends TagsComponent {
        private volatile boolean isRead;

        private NoopTagsComponent() {
        }

        @Override
        public Tagger getTagger() {
            return NoopTags.getNoopTagger();
        }

        @Override
        public TagPropagationComponent getTagPropagationComponent() {
            return NoopTags.getNoopTagPropagationComponent();
        }

        @Override
        public TaggingState getState() {
            this.isRead = true;
            return TaggingState.DISABLED;
        }

        @Override
        @Deprecated
        public void setState(TaggingState state) {
            Preconditions.checkNotNull(state, "state");
            Preconditions.checkState(!this.isRead, "State was already read, cannot set state.");
        }
    }
}

