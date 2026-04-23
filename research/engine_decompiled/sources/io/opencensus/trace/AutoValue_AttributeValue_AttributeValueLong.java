/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import io.opencensus.trace.AttributeValue;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_AttributeValue_AttributeValueLong
extends AttributeValue.AttributeValueLong {
    private final Long longValue;

    AutoValue_AttributeValue_AttributeValueLong(Long longValue) {
        if (longValue == null) {
            throw new NullPointerException("Null longValue");
        }
        this.longValue = longValue;
    }

    @Override
    Long getLongValue() {
        return this.longValue;
    }

    public String toString() {
        return "AttributeValueLong{longValue=" + this.longValue + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof AttributeValue.AttributeValueLong) {
            AttributeValue.AttributeValueLong that = (AttributeValue.AttributeValueLong)o;
            return this.longValue.equals(that.getLongValue());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.longValue.hashCode();
    }
}

