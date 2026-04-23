/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class MonitoredResource
implements Serializable {
    private static final long serialVersionUID = -4393604148752640581L;
    private final String type;
    private final Map<String, String> labels;

    MonitoredResource(Builder builder) {
        this.type = Preconditions.checkNotNull(builder.type);
        this.labels = ImmutableMap.copyOf(builder.labels);
    }

    public String getType() {
        return this.type;
    }

    public Map<String, String> getLabels() {
        return this.labels;
    }

    public int hashCode() {
        return Objects.hash(this.type, this.labels);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MonitoredResource)) {
            return false;
        }
        MonitoredResource other = (MonitoredResource)obj;
        return Objects.equals(this.type, other.type) && Objects.equals(this.labels, other.labels);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("type", this.type).add("labels", this.labels).toString();
    }

    public com.google.api.MonitoredResource toPb() {
        return com.google.api.MonitoredResource.newBuilder().setType(this.type).putAllLabels(this.labels).build();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder newBuilder(String type) {
        return new Builder(type);
    }

    public static MonitoredResource of(String type, Map<String, String> labels) {
        return MonitoredResource.newBuilder(type).setLabels(labels).build();
    }

    public static MonitoredResource fromPb(com.google.api.MonitoredResource descriptorPb) {
        return new Builder(descriptorPb.getType()).setLabels(descriptorPb.getLabelsMap()).build();
    }

    public static class Builder {
        private String type;
        private Map<String, String> labels = new HashMap<String, String>();

        Builder(String type) {
            this.type = type;
        }

        Builder(MonitoredResource monitoredResource) {
            this.type = monitoredResource.type;
            this.labels = new HashMap<String, String>(monitoredResource.labels);
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setLabels(Map<String, String> labels) {
            this.labels = new HashMap<String, String>(Preconditions.checkNotNull(labels));
            return this;
        }

        public Builder addLabel(String key, String value) {
            this.labels.put(key, value);
            return this;
        }

        public Builder clearLabels() {
            this.labels.clear();
            return this;
        }

        public MonitoredResource build() {
            return new MonitoredResource(this);
        }
    }
}

