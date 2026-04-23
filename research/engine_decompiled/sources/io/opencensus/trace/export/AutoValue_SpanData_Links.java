/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.export;

import io.opencensus.trace.Link;
import io.opencensus.trace.export.SpanData;
import java.util.List;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_SpanData_Links
extends SpanData.Links {
    private final List<Link> links;
    private final int droppedLinksCount;

    AutoValue_SpanData_Links(List<Link> links, int droppedLinksCount) {
        if (links == null) {
            throw new NullPointerException("Null links");
        }
        this.links = links;
        this.droppedLinksCount = droppedLinksCount;
    }

    @Override
    public List<Link> getLinks() {
        return this.links;
    }

    @Override
    public int getDroppedLinksCount() {
        return this.droppedLinksCount;
    }

    public String toString() {
        return "Links{links=" + this.links + ", droppedLinksCount=" + this.droppedLinksCount + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof SpanData.Links) {
            SpanData.Links that = (SpanData.Links)o;
            return this.links.equals(that.getLinks()) && this.droppedLinksCount == that.getDroppedLinksCount();
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.links.hashCode();
        h *= 1000003;
        return h ^= this.droppedLinksCount;
    }
}

