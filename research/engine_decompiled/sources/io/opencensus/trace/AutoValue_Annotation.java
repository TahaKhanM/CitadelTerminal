/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import io.opencensus.trace.Annotation;
import io.opencensus.trace.AttributeValue;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_Annotation
extends Annotation {
    private final String description;
    private final Map<String, AttributeValue> attributes;

    AutoValue_Annotation(String description, Map<String, AttributeValue> attributes) {
        if (description == null) {
            throw new NullPointerException("Null description");
        }
        this.description = description;
        if (attributes == null) {
            throw new NullPointerException("Null attributes");
        }
        this.attributes = attributes;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Map<String, AttributeValue> getAttributes() {
        return this.attributes;
    }

    public String toString() {
        return "Annotation{description=" + this.description + ", attributes=" + this.attributes + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Annotation) {
            Annotation that = (Annotation)o;
            return this.description.equals(that.getDescription()) && this.attributes.equals(that.getAttributes());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.description.hashCode();
        h *= 1000003;
        return h ^= this.attributes.hashCode();
    }
}

