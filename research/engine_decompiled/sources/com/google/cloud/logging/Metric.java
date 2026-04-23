/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.api.core.ApiFuture;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.MetricInfo;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.logging.v2.LogMetric;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;

public class Metric
extends MetricInfo {
    private static final long serialVersionUID = -1549310461066853001L;
    private final LoggingOptions options;
    private transient Logging logging;

    Metric(Logging logging, MetricInfo.BuilderImpl builder) {
        super(builder);
        this.logging = Preconditions.checkNotNull(logging);
        this.options = (LoggingOptions)logging.getOptions();
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.options, super.hashCode());
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(Metric.class)) {
            return false;
        }
        Metric other = (Metric)obj;
        return this.baseEquals(other) && Objects.equals(this.options, other.options);
    }

    public Logging getLogging() {
        return this.logging;
    }

    public boolean delete() {
        return this.logging.deleteMetric(this.getName());
    }

    public ApiFuture<Boolean> deleteAsync() {
        return this.logging.deleteMetricAsync(this.getName());
    }

    public Metric reload() {
        return this.logging.getMetric(this.getName());
    }

    public ApiFuture<Metric> reloadAsync() {
        return this.logging.getMetricAsync(this.getName());
    }

    public Metric update() {
        return this.logging.update(this);
    }

    public ApiFuture<Metric> updateAsync() {
        return this.logging.updateAsync(this);
    }

    private void readObject(ObjectInputStream input2) throws IOException, ClassNotFoundException {
        input2.defaultReadObject();
        this.logging = (Logging)this.options.getService();
    }

    static Metric fromPb(Logging logging, LogMetric metricPb) {
        MetricInfo metricInfo = MetricInfo.fromPb(metricPb);
        return new Metric(logging, new MetricInfo.BuilderImpl(metricInfo));
    }

    static Function<LogMetric, Metric> fromPbFunction(final Logging logging) {
        return new Function<LogMetric, Metric>(){

            @Override
            public Metric apply(LogMetric metricPb) {
                return metricPb != null ? Metric.fromPb(logging, metricPb) : null;
            }
        };
    }

    public static final class Builder
    extends MetricInfo.Builder {
        private final Logging logging;
        private final MetricInfo.BuilderImpl delegate;

        private Builder(Metric metric) {
            this.logging = metric.logging;
            this.delegate = new MetricInfo.BuilderImpl(metric);
        }

        @Override
        public Builder setName(String name) {
            this.delegate.setName(name);
            return this;
        }

        @Override
        public Builder setDescription(String description) {
            this.delegate.setDescription(description);
            return this;
        }

        @Override
        public Builder setFilter(String filter2) {
            this.delegate.setFilter(filter2);
            return this;
        }

        @Override
        public Metric build() {
            return new Metric(this.logging, this.delegate);
        }
    }
}

