/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.View;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_View_Name
extends View.Name {
    private final String asString;

    AutoValue_View_Name(String asString) {
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
        return "Name{asString=" + this.asString + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof View.Name) {
            View.Name that = (View.Name)o;
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

