/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import com.google.common.base.Preconditions;
import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.AutoValue_Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Annotation {
    private static final Map<String, AttributeValue> EMPTY_ATTRIBUTES = Collections.unmodifiableMap(Collections.emptyMap());

    public static Annotation fromDescription(String description) {
        return new AutoValue_Annotation(description, EMPTY_ATTRIBUTES);
    }

    public static Annotation fromDescriptionAndAttributes(String description, Map<String, AttributeValue> attributes) {
        return new AutoValue_Annotation(description, Collections.unmodifiableMap(new HashMap<String, AttributeValue>(Preconditions.checkNotNull(attributes, "attributes"))));
    }

    public abstract String getDescription();

    public abstract Map<String, AttributeValue> getAttributes();

    Annotation() {
    }
}

