/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.pathtemplate;

import com.google.api.pathtemplate.PathTemplate;

final class AutoValue_PathTemplate_Segment
extends PathTemplate.Segment {
    private final PathTemplate.SegmentKind kind;
    private final String value;

    AutoValue_PathTemplate_Segment(PathTemplate.SegmentKind kind, String value) {
        if (kind == null) {
            throw new NullPointerException("Null kind");
        }
        this.kind = kind;
        if (value == null) {
            throw new NullPointerException("Null value");
        }
        this.value = value;
    }

    @Override
    PathTemplate.SegmentKind kind() {
        return this.kind;
    }

    @Override
    String value() {
        return this.value;
    }

    public String toString() {
        return "Segment{kind=" + (Object)((Object)this.kind) + ", value=" + this.value + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof PathTemplate.Segment) {
            PathTemplate.Segment that = (PathTemplate.Segment)o;
            return this.kind.equals((Object)that.kind()) && this.value.equals(that.value());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.kind.hashCode();
        h *= 1000003;
        return h ^= this.value.hashCode();
    }
}

