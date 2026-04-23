/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.tags;

import com.google.common.annotations.VisibleForTesting;
import io.opencensus.internal.Provider;
import io.opencensus.tags.NoopTags;
import io.opencensus.tags.Tagger;
import io.opencensus.tags.TaggingState;
import io.opencensus.tags.TagsComponent;
import io.opencensus.tags.propagation.TagPropagationComponent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class Tags {
    private static final Logger logger = Logger.getLogger(Tags.class.getName());
    private static final TagsComponent tagsComponent = Tags.loadTagsComponent(TagsComponent.class.getClassLoader());

    private Tags() {
    }

    public static Tagger getTagger() {
        return tagsComponent.getTagger();
    }

    public static TagPropagationComponent getTagPropagationComponent() {
        return tagsComponent.getTagPropagationComponent();
    }

    public static TaggingState getState() {
        return tagsComponent.getState();
    }

    @Deprecated
    public static void setState(TaggingState state) {
        tagsComponent.setState(state);
    }

    @VisibleForTesting
    static TagsComponent loadTagsComponent(@Nullable ClassLoader classLoader) {
        try {
            return Provider.createInstance(Class.forName("io.opencensus.impl.tags.TagsComponentImpl", true, classLoader), TagsComponent.class);
        }
        catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Couldn't load full implementation for TagsComponent, now trying to load lite implementation.", e);
            try {
                return Provider.createInstance(Class.forName("io.opencensus.impllite.tags.TagsComponentImplLite", true, classLoader), TagsComponent.class);
            }
            catch (ClassNotFoundException e2) {
                logger.log(Level.FINE, "Couldn't load lite implementation for TagsComponent, now using default implementation for TagsComponent.", e2);
                return NoopTags.newNoopTagsComponent();
            }
        }
    }
}

