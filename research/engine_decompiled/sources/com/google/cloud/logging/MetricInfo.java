/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.logging.v2.LogMetric;
import java.io.Serializable;
import java.util.Objects;

public class MetricInfo
implements Serializable {
    private static final long serialVersionUID = 666208243838820325L;
    private final String name;
    private final String description;
    private final String filter;

    MetricInfo(BuilderImpl builder) {
        this.name = Preconditions.checkNotNull(builder.name);
        this.filter = Preconditions.checkNotNull(builder.filter);
        this.description = builder.description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getFilter() {
        return this.filter;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", this.name).add("description", this.description).add("filter", this.filter).toString();
    }

    final boolean baseEquals(MetricInfo metricInfo) {
        return Objects.equals(this.name, metricInfo.name) && Objects.equals(this.description, metricInfo.description) && Objects.equals(this.filter, metricInfo.filter);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(MetricInfo.class)) {
            return false;
        }
        return this.baseEquals((MetricInfo)obj);
    }

    public int hashCode() {
        return Objects.hash(this.name, this.description, this.filter);
    }

    public Builder toBuilder() {
        return new BuilderImpl(this);
    }

    public static Builder newBuilder(String name, String filter2) {
        return new BuilderImpl(name, filter2);
    }

    public static MetricInfo of(String name, String filter2) {
        return new BuilderImpl(name, filter2).build();
    }

    LogMetric toPb() {
        LogMetric.Builder builder = LogMetric.newBuilder().setName(this.name).setFilter(this.filter);
        if (this.description != null) {
            builder.setDescription(this.description);
        }
        return builder.build();
    }

    static MetricInfo fromPb(LogMetric metricPb) {
        Builder builder = MetricInfo.newBuilder(metricPb.getName(), metricPb.getFilter());
        if (!metricPb.getDescription().equals("")) {
            builder.setDescription(metricPb.getDescription());
        }
        return builder.build();
    }

    static final class BuilderImpl
    extends Builder {
        private String name;
        private String description;
        private String filter;

        BuilderImpl(String name, String filter2) {
            this.name = name;
            this.filter = filter2;
        }

        BuilderImpl(MetricInfo metric) {
            this.name = metric.name;
            this.description = metric.description;
            this.filter = metric.filter;
        }

        @Override
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        @Override
        public Builder setFilter(String filter2) {
            this.filter = filter2;
            return this;
        }

        @Override
        public MetricInfo build() {
            return new MetricInfo(this);
        }
    }

    public static abstract class Builder {
        Builder() {
        }

        public abstract Builder setName(String var1);

        public abstract Builder setDescription(String var1);

        public abstract Builder setFilter(String var1);

        public abstract MetricInfo build();
    }
}

