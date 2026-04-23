/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import io.opencensus.trace.AttributeValue;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_AttributeValue_AttributeValueBoolean
extends AttributeValue.AttributeValueBoolean {
    private final Boolean booleanValue;

    AutoValue_AttributeValue_AttributeValueBoolean(Boolean booleanValue) {
        if (booleanValue == null) {
            throw new NullPointerException("Null booleanValue");
        }
        this.booleanValue = booleanValue;
    }

    @Override
    Boolean getBooleanValue() {
        return this.booleanValue;
    }

    public String toString() {
        return "AttributeValueBoolean{booleanValue=" + this.booleanValue + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof AttributeValue.AttributeValueBoolean) {
            AttributeValue.AttributeValueBoolean that = (AttributeValue.AttributeValueBoolean)o;
            return this.booleanValue.equals(that.getBooleanValue());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.booleanValue.hashCode();
    }
}

