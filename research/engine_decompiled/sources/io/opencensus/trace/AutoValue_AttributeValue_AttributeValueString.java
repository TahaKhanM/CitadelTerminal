/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import io.opencensus.trace.AttributeValue;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_AttributeValue_AttributeValueString
extends AttributeValue.AttributeValueString {
    private final String stringValue;

    AutoValue_AttributeValue_AttributeValueString(String stringValue) {
        if (stringValue == null) {
            throw new NullPointerException("Null stringValue");
        }
        this.stringValue = stringValue;
    }

    @Override
    String getStringValue() {
        return this.stringValue;
    }

    public String toString() {
        return "AttributeValueString{stringValue=" + this.stringValue + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof AttributeValue.AttributeValueString) {
            AttributeValue.AttributeValueString that = (AttributeValue.AttributeValueString)o;
            return this.stringValue.equals(that.getStringValue());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.stringValue.hashCode();
    }
}

