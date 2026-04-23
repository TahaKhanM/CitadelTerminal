/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.api.core.ApiFuture;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.SinkInfo;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.logging.v2.LogSink;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;

public class Sink
extends SinkInfo {
    private static final long serialVersionUID = -1549310461066853001L;
    private final LoggingOptions options;
    private transient Logging logging;

    Sink(Logging logging, SinkInfo.BuilderImpl builder) {
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
        if (obj == null || !obj.getClass().equals(Sink.class)) {
            return false;
        }
        Sink other = (Sink)obj;
        return this.baseEquals(other) && Objects.equals(this.options, other.options);
    }

    public Logging getLogging() {
        return this.logging;
    }

    public boolean delete() {
        return this.logging.deleteSink(this.getName());
    }

    public ApiFuture<Boolean> deleteAsync() {
        return this.logging.deleteSinkAsync(this.getName());
    }

    public Sink reload() {
        return this.logging.getSink(this.getName());
    }

    public ApiFuture<Sink> reloadAsync() {
        return this.logging.getSinkAsync(this.getName());
    }

    public Sink update() {
        return this.logging.update(this);
    }

    public ApiFuture<Sink> updateAsync() {
        return this.logging.updateAsync(this);
    }

    private void readObject(ObjectInputStream input2) throws IOException, ClassNotFoundException {
        input2.defaultReadObject();
        this.logging = (Logging)this.options.getService();
    }

    static Sink fromPb(Logging logging, LogSink sinkPb) {
        SinkInfo sinkInfo = SinkInfo.fromPb(sinkPb);
        return new Sink(logging, new SinkInfo.BuilderImpl(sinkInfo));
    }

    static Function<LogSink, Sink> fromPbFunction(final Logging logging) {
        return new Function<LogSink, Sink>(){

            @Override
            public Sink apply(LogSink sinkPb) {
                return sinkPb != null ? Sink.fromPb(logging, sinkPb) : null;
            }
        };
    }

    public static final class Builder
    extends SinkInfo.Builder {
        private final Logging logging;
        private final SinkInfo.BuilderImpl delegate;

        private Builder(Sink sink) {
            this.logging = sink.logging;
            this.delegate = new SinkInfo.BuilderImpl(sink);
        }

        @Override
        public Builder setName(String name) {
            this.delegate.setName(name);
            return this;
        }

        @Override
        public Builder setDestination(SinkInfo.Destination destination) {
            this.delegate.setDestination(destination);
            return this;
        }

        @Override
        public Builder setFilter(String filter2) {
            this.delegate.setFilter(filter2);
            return this;
        }

        @Override
        public Builder setVersionFormat(SinkInfo.VersionFormat versionFormat) {
            this.delegate.setVersionFormat(versionFormat);
            return this;
        }

        @Override
        public Sink build() {
            return new Sink(this.logging, this.delegate);
        }
    }
}

