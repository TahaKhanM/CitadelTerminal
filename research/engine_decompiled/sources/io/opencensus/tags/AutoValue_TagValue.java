/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.tags;

import io.opencensus.tags.TagValue;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_TagValue
extends TagValue {
    private final String asString;

    AutoValue_TagValue(String asString) {
        if (asString == null) {
            throw new NullPointerException("Null asString");
        }
        this.asString = asString;
    }

    @Override
    public String asString() {
        return this.asString;
    }

    public String toString() {
        return "TagValue{asString=" + this.asString + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof TagValue) {
            TagValue that = (TagValue)o;
            return this.asString.equals(that.asString());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.asString.hashCode();
    }
}

